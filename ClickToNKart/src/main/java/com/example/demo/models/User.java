package com.example.demo.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class User implements UserDetails{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
@Column(nullable = false , unique = true )
	private String username;
@Column(nullable = false , unique = true )
	private String email;
@Column(nullable = false)
  private String password ; 
  private String mobile ;
	
  @JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Cart> cartList;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Orders> myorders;
	
	public List<Cart> getCartList() {
		return cartList;
	}
	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	} 
	
	//role based access
	@ElementCollection
	private List<String> roles;
	
	public List<String> getRoles() {
		return roles;
	}
	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	//UserDetails methods for database security
	@Transient // wont create db column
	
	public Collection<? extends GrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	//constructors for spring security
	public User() {}
	
//	public User(String email, String password, Collection<? extends GrantedAuthority> authorities) {
//		super();
//		this.email = email;
//		this.password = password;
//		this.authorities = authorities;
//	}
	

	public User(int id, String username, String email, String password, List<String> roles,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.authorities = authorities;
	}
}
