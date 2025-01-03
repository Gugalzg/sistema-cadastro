package com.projetoguga.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoguga.demo.entities.Cart;
import com.projetoguga.demo.entities.User;

public interface CartRepository extends JpaRepository<Cart, Long> {

	Cart findByUser(User user);
}
