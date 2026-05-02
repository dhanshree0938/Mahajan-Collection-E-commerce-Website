package com.example.demo.models;

import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
	
	public static Specification<Product> hasCategory(String category){
		return(root, query, builder)-> builder.equal(root.get("category"), category);
	}
	
	public static Specification<Product> containingName(String name){
		return(root, query, builder)-> builder.like(root.get("name"), "%"+name+"%");
	}
	
	public static Specification<Product> priceLessThan(int maxPrice){
		return(root, query, builder)-> builder.lessThanOrEqualTo(root.get("price"), maxPrice);
	}
}
