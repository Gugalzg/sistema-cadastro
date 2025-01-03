package com.projetoguga.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projetoguga.demo.entities.Cart;
import com.projetoguga.demo.entities.Product;
import com.projetoguga.demo.services.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")
public class CartViewController {

	@Autowired
    private ProductService productService;

    @ModelAttribute("cart")
    public Cart getCart() {
        return new Cart(); // Cria um novo carrinho, se não existir na sessão
    }

    // Método para adicionar um produto ao carrinho
    @PostMapping("/add")
    public String addToCart(@ModelAttribute("cart") Cart cart,
                            @RequestParam Long productId,
                            @RequestParam int quantity,
                            RedirectAttributes redirectAttributes,
                            HttpSession session) {
        Product product = productService.findById(productId);
        if (product != null) {
            cart.addItem(product, quantity);
            session.setAttribute("cart", cart);  // Garante que o carrinho seja atualizado na sessão
            redirectAttributes.addFlashAttribute("message", "Produto adicionado ao carrinho!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Produto não encontrado.");
        }
        return "redirect:/";  // Redireciona para a página inicial
    }


    // Método para visualizar o carrinho
    @GetMapping
    public String viewCart(@ModelAttribute("cart") Cart cart, Model model) {
        model.addAttribute("cart", cart);  // Adiciona o carrinho ao modelo
        return "pages/cart/cart";  // Retorna a view de carrinho (cart.html)
    }

    // Método para remover um produto do carrinho
    @DeleteMapping("/remove/{id}")
    public String removeFromCart(@ModelAttribute("cart") Cart cart,
                                 @PathVariable Long id,
                                 RedirectAttributes redirectAttributes) {
        cart.removeItem(id);
        redirectAttributes.addFlashAttribute("message", "Produto removido do carrinho!");
        return "redirect:/cart"; // Redireciona para a página do carrinho
    }

    // Método para finalizar a compra (checkout)
    @GetMapping("/checkout")
    public String checkout(@ModelAttribute("cart") Cart cart, Model model) {
        double total = cart.getTotal();
        cart.getItems().clear(); // Limpa o carrinho após o checkout
        model.addAttribute("total", total);  // Adiciona o total ao modelo
        return "pages/cart/checkout"; // Retorna a view de checkout (checkout.html)
    }
}