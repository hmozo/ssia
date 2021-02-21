package com.manning.ssia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manning.ssia.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
