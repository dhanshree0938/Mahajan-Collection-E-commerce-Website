package com.example.demo;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.example.demo.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {
	
	//generate the below key once at begining 
//	Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private final String jwtSecret = "YhPCzFiJ5rYtjDgASrOBsUS9r05ZK8pR7bIbhVR7ZIs="; // key
	private final SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	private final String jwtIssuer = "example.com";// issuer, its optional
		
//	public JwtTokenUtil() {
//		System.out.println("KEY: "+Encoders.BASE64.encode(key.getEncoded()));
//		System.out.println();
//	}
	
	// called at the time of login
	public String generateToken(User user) {
		return Jwts.builder()
				.setSubject(user.getId()+","+user.getEmail()+","+user.getUsername()+","+user.getRoles())
				.setIssuer(jwtIssuer)
				.setIssuedAt(new Date())
//				.setExpiration(new Date(System.currentTimeMillis()+(60*60*1000))) // 1 hr
				.signWith(key)
				.compact();		
	}
	
	// called everytime after login, on receiving any request from client
	public String getUserid(String token) {
		Claims claims = Jwts.parser()
						.setSigningKey(key)
						.build()
						.parseClaimsJws(token)
						.getBody();
		return claims.getSubject().split(",")[0];
	}
	
	public String getUsername(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject().split(",")[1];
	}
	
	public boolean validate(String token) {
		try {
			Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		}
		catch (MalformedJwtException e) {
			System.out.println("Invalid Jwt token - %s"+e.getMessage());
		}
		catch (ExpiredJwtException e) {
			System.out.println("Expired Jwt token - %s"+e.getMessage());
		}
		catch (UnsupportedJwtException e) {
			System.out.println("Unsupported Jwt token - %s"+e.getMessage());
		}
		catch (IllegalArgumentException e) {
			System.out.println("Jwt claims string is empty - %s"+e.getMessage());
		}
		return false;
	}
}