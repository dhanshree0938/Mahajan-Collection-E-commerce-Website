package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.JwtTokenUtil;
import com.example.demo.Dto.UserDto;
import com.example.demo.models.Token;
import com.example.demo.models.User;
import com.example.demo.services.UserServices;

import jakarta.validation.Valid;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class UserController { 
	@Autowired
private	UserServices service;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/api/register")
	public ResponseEntity<?> registerUser(@RequestBody @Valid UserDto dto ){
		User dbUser = service.registeruser(dto);
		return new ResponseEntity<>(dbUser , HttpStatus.CREATED); 
	}
	
	@PostMapping("/api/login")
	public ResponseEntity<?> authenticateUser(@RequestBody User user) {
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(
							user.getEmail(), user.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			User authUser = (User) authentication.getPrincipal();
//			System.out.println("User Controller:"+user);
			Token t = new Token();
			t.jwtToken = jwtTokenUtil.generateToken(authUser);
			return new ResponseEntity<>(t,HttpStatus.OK);
		}
		catch(BadCredentialsException e) {
			return new ResponseEntity<>("Error logging in !!",HttpStatus.UNAUTHORIZED);
		}
	}
}
