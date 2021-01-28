package com.manning.ssia.config;

import java.util.Arrays;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username= authentication.getName();
		String password= String.valueOf(authentication.getCredentials());
		
		if("john".equals(username) && "123456".equals(password)) {
			return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList());
		}else {
			throw new AuthenticationCredentialsNotFoundException("Credentials error!");
		}
	}

	@Override
	public boolean supports(Class<?> authenticationType) {
		return UsernamePasswordAuthenticationToken.class
				.isAssignableFrom(authenticationType);
	}

}
