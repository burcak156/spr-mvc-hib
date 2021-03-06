package com.sprhib.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
		authenticationMgr.inMemoryAuthentication()
		.withUser("test")
		.password("test")
		.authorities("ROLE_USER");
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/home/**").access("hasRole('ROLE_USER')")
		.antMatchers("/application/**").access("hasRole('ROLE_USER')").and()
		.formLogin().loginPage("/loginPage")
		.defaultSuccessUrl("/home/")
		.failureUrl("/loginPage?error")
		.usernameParameter("username").passwordParameter("password")
		.and()
		.logout().logoutSuccessUrl("/loginPage?logout");
	}

}
