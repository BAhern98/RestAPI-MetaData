package net.codejava.song.service;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class Token {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateAccessToken() {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000); // token will expire after 1 hour

        return Jwts.builder()
                .setIssuer("myApplication") // set the issuer claim to identify the application that generated the token
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SECRET_KEY)
                .compact();
    }
    
	public static boolean checkAuthToken(String authToken) {
	    try {
	        Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(authToken);
	        return true;
	    } catch (JwtException e) {
	        return false;
	    }
	}


}
