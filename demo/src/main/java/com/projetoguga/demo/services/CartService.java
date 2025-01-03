package com.projetoguga.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoguga.demo.entities.Cart;
import com.projetoguga.demo.entities.CartItem;
import com.projetoguga.demo.entities.Product;
import com.projetoguga.demo.entities.User;
import com.projetoguga.demo.repositories.CartItemRepository;
import com.projetoguga.demo.repositories.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    // Obtém o carrinho do usuário
    public Cart getCartForUser(User user) {
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            // Se o carrinho não existir, cria um novo carrinho
            cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }
        return cart;
    }

    // Adiciona um item ao carrinho. Se o item já existir, aumenta a quantidade
    public void addItemToCart(Cart cart, Product product, int quantity) {
        CartItem existingItem = cartItemRepository.findByCartAndProductId(cart, product.getId());
        if (existingItem != null) {
            // Se o item já existir, aumenta a quantidade
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItemRepository.save(existingItem);  // Atualiza o item no banco
        } else {
            // Caso o item não exista, cria um novo item no carrinho
            CartItem newItem = new CartItem(cart, product, quantity);
            cartItemRepository.save(newItem);  // Adiciona o novo item no banco
        }
    }

    // Remove um item do carrinho
    public void removeItemFromCart(Cart cart, Long productId) {
        CartItem cartItem = cartItemRepository.findByCartAndProductId(cart, productId);
        if (cartItem != null) {
            cartItemRepository.delete(cartItem);  // Remove o item do carrinho
        }
    }

    // Atualiza o carrinho no banco de dados
    public void updateCart(Cart cart) {
        cartRepository.save(cart);  // Atualiza o carrinho no banco de dados
    }
}
