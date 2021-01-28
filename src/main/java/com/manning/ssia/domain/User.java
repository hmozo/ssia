package com.manning.ssia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	private final String username;
	private final String password;
	private final String authority;
}
