package com.manning.ssia.domain;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class User {

	private final Long id;
	private final String username;
	private final String password;
	private final String authority;
}
