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

	import com.projetoguga.demo.entities.Product;
	import com.projetoguga.demo.entities.Category;
	import com.projetoguga.demo.services.ProductService;
	import com.projetoguga.demo.services.CategoryService;

	@Controller
	@RequestMapping("/")
	public class HomeViewController {

	    @Autowired
	    private ProductService productService;

	    @Autowired
	    private CategoryService categoryService;

	    // Página inicial que mostra os produtos e categorias
	    @GetMapping
	    public String showHomePage(Model model) {
	        // Buscando todos os produtos e categorias
	        List<Product> products = productService.findAll();
	        List<Category> categories = categoryService.findAll();

	        // Adicionando as listas ao modelo
	        model.addAttribute("products", products);
	        model.addAttribute("categories", categories);

	        return "index";  // A página inicial que mostra os produtos e categorias
	    }

	    // Rota para registrar um novo produto
	    @GetMapping("/products/register")
	    public String showProductRegistrationForm(Model model) {
	        model.addAttribute("product", new Product());
	        return "pages/products/product-register";  // Página de registro de produtos
	    }

	    @PostMapping("/products/register")
	    public String registerProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
	        try {
	            productService.registerProduct(product);
	            redirectAttributes.addFlashAttribute("message", "Produto registrado com sucesso!");
	            return "redirect:/";  // Redireciona para a página inicial após o registro
	        } catch (IllegalArgumentException e) {
	            redirectAttributes.addFlashAttribute("error", e.getMessage());
	            return "redirect:/products/register";  // Redireciona de volta para o registro em caso de erro
	        }
	    }
	    
	   // Rota para editar um produto
	      @GetMapping("/products/edit/{id}")
	      public String editProduct(@PathVariable("id") Long id, Model model) {
	      // Simula a busca de um produto pelo ID
	       Product product = productService.findById(id); // productService seria o serviço que gerencia os produtos
	            
	       // Adiciona o produto encontrado ao modelo
	        model.addAttribute("product", product);
	            
	        // Retorna o nome da página de edição (por exemplo, "edit-product.html")
	        return "pages/products/product-edit";
	     }
	      
	    // Rota para atualizar produto  
	      @PostMapping("/products/update")
	      public String updateProduct(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes) {
	          try {
	              productService.update(product); // Atualiza o produto no banco de dados
	              redirectAttributes.addFlashAttribute("message", "Produto atualizado com sucesso!");
	              return "redirect:/"; // Redireciona para a página inicial ou lista de produtos
	          } catch (Exception e) {
	              redirectAttributes.addFlashAttribute("error", "Erro ao atualizar o produto: " + e.getMessage());
	              return "redirect:/products/edit/" + product.getId(); // Redireciona de volta para edição em caso de erro
	          }
	      }
 
	    // Rota para registrar uma nova categoria
	    @GetMapping("/categories/register")
	    public String showCategoryRegistrationForm(Model model) {
	        model.addAttribute("category", new Category());
	        return "pages/categories/category-register";  // Página de registro de categorias
	    }

	    @PostMapping("/categories/register")
	    public String registerCategory(@ModelAttribute Category category, RedirectAttributes redirectAttributes) {
	        try {
	            categoryService.registerCategory(category);
	            redirectAttributes.addFlashAttribute("message", "Categoria registrada com sucesso!");
	            return "redirect:/";  // Redireciona para a página inicial após o registro
	        } catch (IllegalArgumentException e) {
	            redirectAttributes.addFlashAttribute("error", e.getMessage());
	            return "redirect:/categories/register";  // Redireciona de volta para o registro em caso de erro
	        }
	    }
	
	    // Rota para editar uma categoria
	      @GetMapping("/categories/edit/{id}")
	      public String editCategory(@PathVariable("id") Long id, Model model) {
	      // Simula a busca de um produto pelo ID
	       Category category = categoryService.findById(id); // productService seria o serviço que gerencia os produtos
	            
	       // Adiciona o produto encontrado ao modelo
	        model.addAttribute("category", category);
	            
	        // Retorna o nome da página de edição (por exemplo, "edit-product.html")
	        return "pages/categories/category-edit";
	     }
	
	   // Rota para atualizar produto  
	      @PostMapping("/categories/update")
	      public String updateCategory(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
	          try {
	              categoryService.update(category); // Atualiza o produto no banco de dados
	              redirectAttributes.addFlashAttribute("message", "Produto atualizado com sucesso!");
	              return "redirect:/"; // Redireciona para a página inicial ou lista de produtos
	          } catch (Exception e) {
	        	  redirectAttributes.addFlashAttribute("error", "Erro ao atualizar a categoria: " + e.getMessage());
	        	  return "redirect:/categories/edit/" + category.getId(); // Redireciona de volta para edição de categoria
	          }
	      }
    
	}

