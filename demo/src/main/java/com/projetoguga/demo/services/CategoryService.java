package com.projetoguga.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoguga.demo.entities.Category;
import com.projetoguga.demo.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}

	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.get();
				
	}

	public Category registerCategory(Category category)  throws IllegalArgumentException {
        // Validar dados do usuário
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser vazio.");
        }

        // Tratar os dados
        category.setName(category.getName().trim());

        // Se você estiver usando senhas, você deve hash a senha aqui
        // Por exemplo: user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Salvar o usuário
        return repository.save(category);
		
	}
}
