package com.projetoguga.demo.services;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoguga.demo.entities.Order;
import com.projetoguga.demo.entities.OrderItem;
import com.projetoguga.demo.entities.User;
import com.projetoguga.demo.entities.enums.OrderStatus;
import com.projetoguga.demo.repositories.OrderRepository;
import com.projetoguga.demo.repositories.UserRepository;


@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
    private UserRepository userRepository;
	
	public List<Order> findAll(){
		return repository.findAll();
	}

	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
				
	}

	/**
     * Atualiza o status de um pedido.
     */
    public void updateStatus(Long orderId, OrderStatus status) {
        Order order = findById(orderId);
        order.setOrderStatus(status); // Atualiza o status
        repository.save(order); // Persiste a atualização no banco de dados
    }

    /**
     * Cria um novo pedido.
     */
    public Order createOrder(Set<OrderItem> items, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));

        // Configura o pedido
        Order newOrder = new Order();
        newOrder.setClient(user);
        newOrder.setMoment(new Date().toInstant());
        newOrder.setOrderStatus(OrderStatus.WAITING_PAYMENT); // Status inicial
        newOrder.setItems(items != null ? items : new HashSet<>());

        // Salva o pedido no banco de dados
        return repository.save(newOrder);
    }
}
