package com.manning.ssia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manning.ssia.model.Product;
import com.manning.ssia.services.ProductService;

@RestController
public class MainPageController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public List<Product> getProducts() {
		return productService.findAll();
	}
	
	@GetMapping("/products2")
	public String getProducts2(Authentication authentication, Model model) {
		model.addAttribute("username", authentication.getName());
		model.addAttribute("products", productService.findAll());
		
		return "product-list";
	}

}
