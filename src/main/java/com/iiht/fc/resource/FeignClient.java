package com.iiht.fc.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.fc.ifaces.FeignInterface;
import com.iiht.fc.model.Product;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/client")
public class FeignClient {
	@Autowired
	private FeignInterface feignclient;

	@CircuitBreaker(name =  "client-cb",fallbackMethod = "generateResponse")
	@GetMapping("/allProducts")
	public List<Product> getAll() {
		return feignclient.getProducts();
	}

	@GetMapping("/products-by-id/{id}")
	public Product getProductById(@PathVariable("id") int id) {
		System.out.println(id);
		return feignclient.getProductsById(id);
	}

	@GetMapping("/products-by-category/{category}")
	public List<Product> getProductsByCategory(@PathVariable("category") String category) {
		return feignclient.getProductsByCategory(category);
	}
	
    
	public String generateResponse(Throwable throwable)
	{
		return "The server is unavailable temporarily,pls try again some time";
	}
}
