package com.projetoguga.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoguga.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
