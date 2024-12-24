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

import com.projetoguga.demo.entities.Category;
import com.projetoguga.demo.services.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryViewController {
		
			    @Autowired
			    private CategoryService service;

			    @GetMapping("/show")
			    public String showUsers(Model model) {
			        List<Category> categories = service.findAll();
			         model.addAttribute("categories", categories);
			        return "pages/categories/category-list";
			    }

			    @GetMapping("/register")
			    public String showRegistrationForm(Model model) {
			         model.addAttribute("category", new Category());
			        return "pages/categories/category-register";
			    }

			    @PostMapping("/register")
			    public String registerCategory(@ModelAttribute Category category, RedirectAttributes redirectAttributes) {
			        try {
			            service.registerCategory(category);
			            redirectAttributes.addFlashAttribute("message", "Registro realizado com sucesso!");
			            return "redirect:/categories/show";
			        } catch (IllegalArgumentException e) {
			            redirectAttributes.addFlashAttribute("error", e.getMessage());
			            return "redirect:/category-register";
			        }
			    }
			}

