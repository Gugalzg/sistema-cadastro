package com.projetoguga.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoguga.demo.entities.Product;
import com.projetoguga.demo.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll(){
		return repository.findAll();
	}

	public Product findById(Long id) {
	    Optional<Product> obj = repository.findById(id);
	    
	    if (obj.isPresent()) {
	        return obj.get();
	    } else {
	        throw new IllegalArgumentException("Produto não encontrado para o ID: " + id);
	    }
	}
		
	public Product registerProduct(Product product)  throws IllegalArgumentException {
	        // Validar dados do usuário
	        if (product.getName() == null || product.getName().trim().isEmpty()) {
	            throw new IllegalArgumentException("Nome do produto não pode ser vazio.");
	        }
	        if (product.getPrice() == null || product.getPrice() <= 0) {
	            throw new IllegalArgumentException("Preço não pode ser vazio ou zero.");
	        }

	        // Tratar os dados
	        product.setName(product.getName().trim());
	        product.setDescription(product.getDescription().toLowerCase().trim());
	        product.setPrice(product.getPrice());

	        // Se você estiver usando senhas, você deve hash a senha aqui
	        // Por exemplo: user.setPassword(passwordEncoder.encode(user.getPassword()));

	        // Salvar o usuário
	        return repository.save(product);

		}	
	public void update(Product product) {
        // Certifique-se de que o produto tem um ID válido antes de salvar
        if (product.getId() != null) {
            repository.save(product); // Atualiza o registro no banco
        }
    }
  }

