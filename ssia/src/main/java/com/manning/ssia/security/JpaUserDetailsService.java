package com.manning.ssia.security;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.manning.ssia.model.User;
import com.manning.ssia.repositories.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Supplier<UsernameNotFoundException> supplier= ()->new UsernameNotFoundException("Problem during authentication");
		
		User user= userRepository.findByUsername(username)
				.orElseThrow(supplier);
		
		return new CustomUserDetails(user);
	}

}
