package com.goodfood.init;

import com.goodfood.social.user.SecurityContext;
import com.goodfood.social.user.SimpleConnectionSignUp;
import com.goodfood.social.user.SimpleSignInAdapter;
import com.goodfood.social.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.ReconnectFilter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.facebook.web.DisconnectController;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.connect.GoogleConnectionFactory;

import javax.inject.Inject;
import javax.sql.DataSource;

/**
 * Created by Yaroslav on 05.04.2015.
 */

@Configuration
@EnableSocial
public class SocialConfig {

    @Inject
    private DataSource dataSource;

    @Inject
    private Environment environment;

//    @Override
//    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
//        cfConfig.addConnectionFactory(new FacebookConnectionFactory(
//                env.getProperty("facebook.app.id"),
//                env.getProperty("facebook.app.secret")));
//        cfConfig.addConnectionFactory(new GoogleConnectionFactory(
//                env.getProperty("googlep.app.id"),
//                env.getProperty("googlep.app.secret")));
//    }
//
//    /**
//     * Callback method to enable creation of a {@link org.springframework.social.UserIdSource} that uniquely identifies the current user.
//     *
//     * @return the {@link org.springframework.social.UserIdSource}.
//     */
//    @Override
//    public UserIdSource getUserIdSource() {
//        return new UserIdSource() {
//            @Override
//            public String getUserId() {
//                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//                if (authentication == null) {
//                    throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
//                }
//                return authentication.getName();
//            }
//        };
//    }
//
//    @Override
//    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
//        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
//                connectionFactoryLocator, Encryptors.noOpText());
//        repository.setConnectionSignUp(new SimpleConnectionSignUp());
//        return repository;
//    }
    /**
     * When a new provider is added to the app, register its {@link } here.
     * @see GoogleConnectionFactory
     */
    @Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        registry.addConnectionFactory(new GoogleConnectionFactory(
                environment.getProperty("googlep.app.id"),
                environment.getProperty("googlep.app.secret")));
        registry.addConnectionFactory(
                new FacebookConnectionFactory(
                environment.getProperty("facebook.app.id"),
                environment.getProperty("facebook.app.secret"))
        );
        return registry;
    }

    /**
     * Singleton data access object providing access to connections across all users.
     */
    @Bean
    public UsersConnectionRepository usersConnectionRepository() {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator(), Encryptors.noOpText());
        repository.setConnectionSignUp(new SimpleConnectionSignUp());
        return repository;
    }

    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        ConnectController connectController = new ConnectController(connectionFactoryLocator, connectionRepository);
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
    public Facebook facebook() {
        Connection<Facebook> connection = connectionRepository().findPrimaryConnection(Facebook.class);
        return connection != null ? connection.getApi() : new FacebookTemplate();
    }

    /**
     * A proxy to a request-scoped object representing the current user's primary Facebook account.
     * @throws  if the user is not connected to facebook.
     */
    @Bean
    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
    public Google google() {
        Connection<Google> connection = connectionRepository().getPrimaryConnection(Google.class);
        return connection != null ? connection.getApi() : new GoogleTemplate();
    }

    /**
     * Request-scoped data access object providing access to the current user's connections.
     */
    @Bean
    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
    public ConnectionRepository connectionRepository() {
        User user = SecurityContext.getCurrentUser();
        return usersConnectionRepository().createConnectionRepository(user.getId());
    }

    /**
     * The Spring MVC Controller that allows users to sign-in with their provider accounts.
     */
    @Bean
    public ProviderSignInController providerSignInController() {
        return new ProviderSignInController(connectionFactoryLocator(), usersConnectionRepository(),
                new SimpleSignInAdapter());
    }
}
