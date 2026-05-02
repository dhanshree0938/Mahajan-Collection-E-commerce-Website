package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.models.User;

@CrossOrigin(origins = {"http://localhost:3000"})
public interface UserRepository extends JpaRepository<User, Integer> {

	@RestResource(exported = false)
	public User save(User user);
	
	public boolean existsByEmail(String email);
	public boolean existsByUsername(String username);
	
	//searching User object form database, based on email
	public Optional<User> findByEmail(String email);
}