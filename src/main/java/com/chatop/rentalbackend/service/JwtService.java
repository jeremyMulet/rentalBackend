package com.chatop.rentalbackend.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Service for JWT token generation and validation.
 *  *
 *  * <p>This service provides methods to handle JWT (JSON Web Token) for the application.
 *  * It allows the generation of tokens, extraction of information from tokens and validation of tokens.</p>
 *
 * @author Jérémy MULET
 * @since 16/08/2023
 */
@Component
public class JwtService {

    private static final String SECRET_KEY = "dd6e8177c7f91192ca9888cccf2029fd5bb4463f53fce121fbc59e8f0d1602b4";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a claim from the given token.
     *
     * @param <T> the type of the claim value.
     * @param token the JWT token.
     * @param claimsResolver function to extract the claim.
     * @return the extracted claim value.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a JWT token with additional claims for the given user details.
     *
     * @param extaClaims additional claims to be included in the token.
     * @param userDetails the user details.
     * @return a JWT token.
     */
    public String generateToken(Map<String, Object> extaClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extaClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        System.out.println("token : " + token);
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
