package com.projetoguga.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoguga.demo.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
