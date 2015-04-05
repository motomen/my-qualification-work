package com.goodfood.init;

import javax.sql.DataSource;

import com.goodfood.social.SimpleSocialUsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.UserIdSource;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserDetailsService customUserDetailsService;

	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.jdbcAuthentication()
				.dataSource(dataSource)
				.passwordEncoder(new Md5PasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.userDetailsService(customUserDetailsService)
				.csrf().disable()
			.authorizeRequests()
			.antMatchers("/").permitAll()
				.antMatchers("/control/**").hasRole("ADMIN")
				.antMatchers("/acount/**").authenticated()
			.and()
				.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/j_spring_security_check")
				.defaultSuccessUrl("/")
				.failureUrl("/login?err=1")
				.usernameParameter("username")
				.passwordParameter("password")
				.and()
			.logout()
				.logoutUrl("/j_spring_security_logout")
				.logoutSuccessUrl("/")
		.deleteCookies("JSESSIONID")
		.invalidateHttpSession(true)
				.and()
				.rememberMe()
				.and()
				.apply(
						new SpringSocialConfigurer());
	}

//	@Bean
//	public DelegatingFilterProxy springSecurityFileterChain() {
//		DelegatingFilterProxy filterProxy = new DelegatingFilterProxy();
//		return filterProxy;
//	}

	@Bean
	public SocialUserDetailsService socialUsersDetailService() {
		return new SimpleSocialUsersDetailService(userDetailsService());
	}

	@Bean
	public UserIdSource userIdSource() {
		return new AuthenticationNameUserIdSource();
	}
}
