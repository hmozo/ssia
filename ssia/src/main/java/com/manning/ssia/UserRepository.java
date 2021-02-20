package com.manning.ssia;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manning.ssia.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username);
}
