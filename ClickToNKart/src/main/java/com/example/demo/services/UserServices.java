package com.example.demo.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Dto.UserDto;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserServices {
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private UserRepository repository;
	public User registeruser(UserDto dto) {
		boolean status1 =repository.existsByUsername(dto.getUsername());
		if(status1==true) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
		}
		boolean status2 =repository.existsByEmail(dto.getEmail());
		if(status1==true) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
	}
		User user = new User();
		user.setEmail(dto.getEmail());
		user.setUsername(dto.getUsername());
		user.setMobile(dto.getMobile());
		if(dto.getRoles()==null)
			user.setRoles(Arrays.asList("ROLE_USER"));
		else
			user.setRoles(dto.getRoles());
		user.setPassword(encoder.encode(dto.getPassword()));
		User dbUser =repository.save(user);
		return dbUser;
		}
}