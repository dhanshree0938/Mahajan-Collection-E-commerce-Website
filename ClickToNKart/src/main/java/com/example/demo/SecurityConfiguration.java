package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.services.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
@Autowired
private JwtTokenFilter jwtTokenFilter;

@Bean
public SecurityFilterChain configure(HttpSecurity http) throws Exception{
	//url public
//	http
//	.csrf(csrf-> csrf.disable())
//	.authorizeHttpRequests()
//	.anyRequest()
//	.permitAll();
//	return http.build();
	
	//role based access
//	http
//	.csrf(csrf-> csrf.disable())
//	.authorizeHttpRequests()
//	.requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
//	.requestMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
//	.requestMatchers(HttpMethod.PUT, "/products/**").hasRole("ADMIN")
//	.requestMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")
//	.requestMatchers("/carts").hasRole("USER")
//	.anyRequest()
//	.permitAll()
//	.and()
//	.httpBasic();
//	
//	return http.build();
	
	
	// role based access using, JWT token
	http
	.cors()
	.and()
	.csrf(csrf-> csrf.disable())
	.authorizeHttpRequests()
	.requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
	.requestMatchers(HttpMethod.POST, "/api/products").hasRole("ADMIN")
	.requestMatchers(HttpMethod.PUT, "/api/products/**").hasRole("ADMIN")
	.requestMatchers(HttpMethod.DELETE, "/api/products/**").hasRole("ADMIN")
	.requestMatchers("/api/carts").hasRole("USER")
	.requestMatchers("/api/carts/**").hasRole("USER")
	.anyRequest()
	.permitAll();
	
	http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
	
	return http.build();
}

@Bean
public PasswordEncoder passwoerEncoder() {
	return new BCryptPasswordEncoder();
}

@Bean
public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
	return http.getSharedObject(AuthenticationManagerBuilder.class).build();
}

//database login
//@Autowired
//private MyUserDetailsService myUserDetailsService;
}