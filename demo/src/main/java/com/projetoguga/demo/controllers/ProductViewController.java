package com.projetoguga.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projetoguga.demo.entities.Product;
import com.projetoguga.demo.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductViewController {
	
		    @Autowired
		    private ProductService service;

		    @GetMapping("/show")
		    public String showUsers(Model model) {
		        List<Product> products = service.findAll();
		         model.addAttribute("products", products);
		        return "pages/products/product-list";
		    }

		    @GetMapping("/register")
		    public String showRegistrationForm(Model model) {
		         model.addAttribute("product", new Product());
		        return "pages/products/product-register";
		    }

		    @PostMapping("/register")
		    public String registerProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
		        try {
		            service.registerProduct(product);
		            redirectAttributes.addFlashAttribute("message", "Registro realizado com sucesso!");
		            return "redirect:/products/show";
		        } catch (IllegalArgumentException e) {
		            redirectAttributes.addFlashAttribute("error", e.getMessage());
		            return "redirect:/product-register";
		        }
		    }
		}
