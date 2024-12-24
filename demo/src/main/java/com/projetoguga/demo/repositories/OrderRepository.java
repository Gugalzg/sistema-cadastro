package com.projetoguga.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoguga.demo.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
}
