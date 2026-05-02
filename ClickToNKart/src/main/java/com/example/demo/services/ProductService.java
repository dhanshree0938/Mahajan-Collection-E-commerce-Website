package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.models.Product;
import com.example.demo.models.ProductSpecification;
import com.example.demo.repositories.ProductRepository;

@Service
public class ProductService {
	
@Autowired
private ProductRepository productRepository;

public List<String> getAllCategories(){
	return productRepository.findDistinctCategory();
	
}

public List<Product> getProductsByCriteria(String category, String name, Integer maxPrice, String sortDirection, String sortField){
	Specification<Product> spec = Specification.where(null);
	
	if(category!=null) {
		spec = spec.and(ProductSpecification.hasCategory(category));
	}
	
	if(name!=null) {
		spec = spec.and(ProductSpecification.containingName(name));
	}
	
	if(maxPrice!=null) {
		spec = spec.and(ProductSpecification.priceLessThan(maxPrice));
	}
	
	Sort sort=Sort.unsorted();
	if(sortDirection!=null && sortField!=null) {
		Sort.Direction dir = sortDirection.equalsIgnoreCase("desc")?
				Sort.Direction.DESC : Sort.Direction.ASC;
		sort = Sort.by(new Sort.Order(dir, sortField));
	}
	List<Product> products = productRepository.findAll(spec, sort);
	return products;
	
}
}