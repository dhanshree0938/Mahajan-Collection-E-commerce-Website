package com.example.demo.projections;

import org.springframework.data.rest.core.config.Projection;

import com.example.demo.models.Product;

@Projection(types = {Product.class})
public interface ProductProjection {
	public int getId();
	public String getName();
	public String getCategory();
	public String getDescription();
	public String getImageUrl();
	public int getPrice();
	
}
