package com.projetoguga.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoguga.demo.entities.Cart;
import com.projetoguga.demo.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
	// Buscar um item do carrinho por carrinho e id do produto
	CartItem findByCartAndProductId(Cart cart, Long productId);

    // Excluir todos os itens de um carrinho
    void deleteAllByCart(Cart cart);
}
