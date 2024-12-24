package com.projetoguga.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoguga.demo.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
