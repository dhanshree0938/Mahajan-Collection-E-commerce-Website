package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class MyUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository repository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		//fetch the user from, database based on email
		User dbUser = repository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Please login with valid email"));
		
		// username(email) - 
		//databaseUser - id, username, email, password, roles
		
		//return the UserDetails(id, username,email, password, roles, authorities)
		//id,username,roles for frontend, sent through token subject
		//
		UserDetails user = new User(
				                dbUser.getId(),
				                dbUser.getUsername(),
				                dbUser.getEmail(),
				                dbUser.getPassword(),
				                dbUser.getRoles(),
				                dbUser.getRoles() 
				                      .stream()
				                      .map(role -> new SimpleGrantedAuthority(role))
				                      .toList()
				                      );
		return user;
	}
}
