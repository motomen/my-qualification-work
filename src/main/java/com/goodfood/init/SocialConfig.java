package com.goodfood.init;

import com.goodfood.model.User;
import com.goodfood.social.PostToWallAfterConnectInterceptor;
import com.goodfood.social.user.SecurityContext;
import com.goodfood.social.user.SimpleConnectionSignUp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ReconnectFilter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.facebook.web.DisconnectController;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.connect.GoogleConnectionFactory;

import javax.inject.Inject;
import javax.sql.DataSource;

/**
 * Created by Yaroslav on 05.04.2015.
 */

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

    @Inject
    private DataSource dataSource;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        cfConfig.addConnectionFactory(new FacebookConnectionFactory(
                env.getProperty("facebook.app.id"),
                env.getProperty("facebook.app.secret")));
        cfConfig.addConnectionFactory(new GoogleConnectionFactory(
                env.getProperty("googlep.app.id"),
                env.getProperty("googlep.app.secret")));
    }

    /**
     * Callback method to enable creation of a {@link org.springframework.social.UserIdSource} that uniquely identifies the current user.
     *
     * @return the {@link org.springframework.social.UserIdSource}.
     */
    @Override
    public UserIdSource getUserIdSource() {
        return new UserIdSource() {
            @Override
            public String getUserId() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication == null) {
                    throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
                }
                return authentication.getName();
            }
        };
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
    }

    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        ConnectController connectController = new ConnectController(connectionFactoryLocator, connectionRepository);
        connectController.addInterceptor(new PostToWallAfterConnectInterceptor());
        return connectController;
    }

    @Bean
    public DisconnectController disconnectController(UsersConnectionRepository usersConnectionRepository, Environment environment) {
        return new DisconnectController(usersConnectionRepository, environment.getProperty("facebook.appSecret"));
    }

    @Bean
    public ReconnectFilter apiExceptionHandler(UsersConnectionRepository usersConnectionRepository, UserIdSource userIdSource) {
        return new ReconnectFilter(usersConnectionRepository, userIdSource);
    }

    @Bean
    @Scope(value="request", proxyMode= ScopedProxyMode.INTERFACES)
    public Facebook facebook(ConnectionRepository repository) {
        Connection<Facebook> connection = repository.findPrimaryConnection(Facebook.class);
        return connection != null ? connection.getApi() : null;
    }

    /**
     * A proxy to a request-scoped object representing the current user's primary Facebook account.
     * @throws  if the user is not connected to facebook.
     */
    @Bean
    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
    public Google google(ConnectionRepository repository) {
        Connection<Google> connection = repository.getPrimaryConnection(Google.class);
        return connection != null ? connection.getApi() : null;
    }

}
