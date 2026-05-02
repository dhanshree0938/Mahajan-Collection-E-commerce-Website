package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.models.Cart;
import com.example.demo.projections.CartProjection;

@CrossOrigin(origins = {"http://localhost:3000"})
@RepositoryRestResource(excerptProjection = CartProjection.class)
public interface CartRepository extends JpaRepository<Cart, Integer>{

}
