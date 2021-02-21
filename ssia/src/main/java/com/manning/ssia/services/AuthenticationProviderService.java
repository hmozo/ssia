package com.manning.ssia.services;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.manning.ssia.security.CustomUserDetails;
import com.manning.ssia.security.JpaUserDetailsService;
import com.manning.ssia.util.EncryptionAlgorithm;

@Service
public class AuthenticationProviderService implements AuthenticationProvider{

	private final UserDetailsService userDetailsService;
	
	private final BeanFactory beanFactory;
	
	private PasswordEncoder passwordEncoder;
	
	public AuthenticationProviderService(UserDetailsService userDetailsService, BeanFactory beanFactory) {
		this.userDetailsService = userDetailsService;
		this.beanFactory = beanFactory;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username= authentication.getName();
		String password= authentication.getCredentials().toString();
		
		CustomUserDetails user= (CustomUserDetails) userDetailsService.loadUserByUsername(username);
		
		switch(user.getUser().getAlgorithm()){
			case BCRYPT:
				this.passwordEncoder= this.beanFactory.getBean("bcryptencoder", BCryptPasswordEncoder.class);
				break;
			case SCRYPT:
				this.passwordEncoder= this.beanFactory.getBean("scryptencoder", SCryptPasswordEncoder.class);
				break;
			default:
				throw new BadCredentialsException("Bad credentials");
					
		}
		
		return checkPassword(user, password, passwordEncoder);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	private Authentication checkPassword(CustomUserDetails user, String rawPassword, PasswordEncoder passwordEncoder) {
		if(passwordEncoder.matches(rawPassword, user.getPassword())) {
			return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
		}
		
		throw new BadCredentialsException("Bad credentials");
	}
	
}
