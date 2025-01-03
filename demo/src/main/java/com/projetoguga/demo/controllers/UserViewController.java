package com.projetoguga.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projetoguga.demo.entities.User;
import com.projetoguga.demo.services.UserService;

import org.springframework.ui.Model;


@Controller
@RequestMapping("/users")
public class UserViewController {

	    @Autowired
	    private UserService service;

	    @GetMapping("/show")
	    public String showUsers(Model model) {
	        List<User> users = service.findAll();
	         model.addAttribute("users", users);
	        return "pages/users/user-list";
	    }

	    @GetMapping("/register")
	    public String showRegistrationForm(Model model) {
	         model.addAttribute("user", new User());
	        return "pages/users/user-register";
	    }

	    @PostMapping("/register")
	    public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
	        try {
	            service.registerUser(user);
	            redirectAttributes.addFlashAttribute("message", "Registro realizado com sucesso!");
	            return "redirect:/users/show";
	        } catch (IllegalArgumentException e) {
	            redirectAttributes.addFlashAttribute("error", e.getMessage());
	            return "redirect:/user-register";
	        }
	    }
	}
