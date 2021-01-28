package com.manning.ssia.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.manning.ssia.domain.SecurityUser;
import com.manning.ssia.domain.User;

@Configuration
public class ProjectConfig{
	
	@Bean
	public UserDetailsService userDetailsService() {
		User user= new User(1L, "john", "123456", "read");
		UserDetails securityUser= new SecurityUser(user);
		List<UserDetails> users= List.of(securityUser);
		
		return new InMemoryUserDetailsManager(users);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}
