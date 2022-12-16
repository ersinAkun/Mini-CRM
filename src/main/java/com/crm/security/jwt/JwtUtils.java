package com.crm.security.jwt;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.crm.exception.message.ErrorMessage;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
@Component
public class JwtUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value( "${crm.app.jwtSecret}")// jwtSecret değerini application.yml dosyasından alıyor
	private String jwtSecret ;
	
	@Value("${crm.app.jwtExpirationMs}") // jwtExpirationMs değerini application.yml dosyasından alıyor
	private long jwtExpirationMs;
	
	// JWT token üretecek
	public String generateJwtToken(UserDetails userDetails) {
		return Jwts.builder().setSubject(userDetails.getUsername()).
				                                                             setIssuedAt(new Date()).
				                                                             setExpiration(new Date(new Date().getTime() + jwtExpirationMs)).
				                                                             signWith(SignatureAlgorithm.HS512, jwtSecret).
				                                                             compact();
	}
	
	
	// JWT token içinden kullanıcının email bilgisi alınmacak
	public String getEmailFromToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
		
	}
	
	// JWT token valide edecek
	public boolean validateJwtToken(String token) throws SignatureException {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException |  
				IllegalArgumentException e ) {
			logger.error(String.format(ErrorMessage.JWTTOKEN_ERROR_MESSAGE, e.getMessage()));
		}
		return false ;		
	}
}
