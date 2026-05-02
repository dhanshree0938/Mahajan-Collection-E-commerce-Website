package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Product;
import com.example.demo.services.ProductService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/api/categories")
	public ResponseEntity<?> getAllCategories(){
		List<String> allCategories = service.getAllCategories();
		return new ResponseEntity<>(allCategories, HttpStatus.OK);
	}
	
	@GetMapping("/api/search-products")
	public ResponseEntity<?> getProductByCriteria(
			@RequestParam(name="name", required = false) String name,
			@RequestParam(name="category", required = false) String category,
			@RequestParam(name="maxPrice", required = false) Integer maxPrice,
			@RequestParam(name="direction", required = false) String sortDirection
			){
		List<Product> allProducts = service.getProductsByCriteria(category, name, maxPrice, sortDirection, "price");
		
		return new ResponseEntity<>(allProducts, HttpStatus.OK);
	}
}
