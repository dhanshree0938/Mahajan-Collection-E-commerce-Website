package com.example.demo.projections;

import org.springframework.data.rest.core.config.Projection;

import com.example.demo.models.Cart;
import com.example.demo.models.Product;

@Projection(types = {Cart.class})
public interface CartProjection {
	
	public int getId();
	public int getQuantity();
	public ProductProjection getProduct();
	
}
