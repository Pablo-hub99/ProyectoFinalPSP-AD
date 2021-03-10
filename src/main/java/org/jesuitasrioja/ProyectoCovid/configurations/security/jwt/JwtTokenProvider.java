package org.jesuitasrioja.ProyectoCovid.configurations.security.jwt;

import java.util.Date;
import java.util.stream.Collectors;

import org.jesuitasrioja.ProyectoCovid.modelo.user.UserEntity;
import org.jesuitasrioja.ProyectoCovid.modelo.user.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String TOKEN_TYPE = "JWT";
	@Value("${jwt.secret:zrvx3dv3Nu9j0Haf8bnTfBQBQSgAASgkAQCkBACglAAClBACglAAAlBIAgFICAFBKAABKCQBAKQEAKCUAAKUEAKCUAA}")
	private String jwtSecreto;
	@Value("${jwt.token-expiration:100000}") 
	private int jwtDuracionTokenEnSegundos;
		public String generateToken(Authentication authentication) {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		Date tokenExpirationDate = new Date(System.currentTimeMillis() + (jwtDuracionTokenEnSegundos * 1000));
		return Jwts.builder()
				.signWith(Keys.hmacShaKeyFor(jwtSecreto.getBytes()), SignatureAlgorithm.HS512)
				.setHeaderParam("typ", TOKEN_TYPE)				
				.setSubject(((Claims) user).getId())				
				.setIssuedAt(new Date())				
				.setExpiration(tokenExpirationDate)			
				.claim("username", user.getUsername())
				.claim("roles", user.getRoles().stream().map(UserRole::name).collect(Collectors.joining(", ")))
				.compact();
		}	
	public String getUserIdFromJWT(String token) {		
		Claims claims = Jwts.parser()				
				.setSigningKey(Keys.hmacShaKeyFor(jwtSecreto.getBytes()))				
				.parseClaimsJws(token).getBody();	
		return claims.getSubject();
		}	
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecreto.getBytes()).parseClaimsJws(authToken);
			return true;
		} catch (Exception ex) { 
			ex.printStackTrace();
		}
		return false;
	}
}