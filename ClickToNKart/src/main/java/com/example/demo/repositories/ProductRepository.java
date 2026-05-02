package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.models.Product;
import com.example.demo.projections.ProductProjection;

@CrossOrigin(origins = {"http://localhost:3000"})
@RepositoryRestResource(excerptProjection = ProductProjection.class)
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product>{
	
	@Query("select distinct p.category from Product p") //jpql
	public List<String> findDistinctCategory();
	
//	public List<Product> findByCategoryAndContainingNameAndPriceLessThan(String category, String name, int maxPrice);
}
