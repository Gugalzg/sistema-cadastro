package com.projetoguga.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projetoguga.demo.entities.Order;
import com.projetoguga.demo.entities.enums.OrderStatus;
import com.projetoguga.demo.services.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderViewController {

	@Autowired
    private OrderService orderService;

    // Exibir todos os pedidos
    @GetMapping("/show")
    public String showOrders(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "pages/orders/order-list";
    }

    // Exibir formulário para criar novo pedido (simulado)
    @GetMapping("/register")
    public String showOrderForm(Model model) {
        Order newOrder = new Order();
        model.addAttribute("order", newOrder);
        model.addAttribute("statuses", OrderStatus.values()); // Envia os status possíveis
        return "pages/orders/order-register";
    }

    // Criar novo pedido
    @PostMapping("/register")
    public String registerOrder(@ModelAttribute Order order, RedirectAttributes redirectAttributes) {
        try {
            orderService.createOrder(order.getItems(), order.getClient().getId()); // Simula a criação
            redirectAttributes.addFlashAttribute("message", "Pedido criado com sucesso!");
            return "redirect:/orders/show";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/orders/register";
        }
    }

    // Atualizar o status de um pedido
    @PostMapping("/update-status/{id}")
    public String updateOrderStatus(@PathVariable Long id, @ModelAttribute("status") OrderStatus status, RedirectAttributes redirectAttributes) {
        try {
            orderService.updateStatus(id, status);
            redirectAttributes.addFlashAttribute("message", "Status do pedido atualizado com sucesso!");
            return "redirect:/orders/show";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/orders/show";
        }
    }

    // Exibir detalhes de um pedido (opcional)
    @GetMapping("/details/{id}")
    public String showOrderDetails(@PathVariable Long id, Model model) {
        try {
            Order order = orderService.findById(id);
            model.addAttribute("order", order);
            return "pages/orders/order-details";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/orders/show";
        }
    }
}
