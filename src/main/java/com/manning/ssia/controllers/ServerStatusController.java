package com.manning.ssia.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerStatusController {

	@GetMapping("/test")
	public String getStatus() {
	return "Server OK!";
	}
}
