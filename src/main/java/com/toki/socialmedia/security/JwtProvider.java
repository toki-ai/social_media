package com.toki.socialmedia.security;

import com.toki.socialmedia.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtProvider {
    private final Environment env;
    private final SecretKey secretKey;

    @Autowired
    public JwtProvider(Environment env) {
        this.env = env;
        String secret = env.getProperty("JWT_SECRET");
        if (secret == null) {
            throw new IllegalArgumentException("JWT_SECRET property is not set in the environment");
        }
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setIssuer("Toki")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 864000000))
                .claim("email", authentication.getName())
                .claim("role", ((User) userDetails).getRole())
                .signWith(secretKey)
                .compact();
    }

    public String getEmailFromToken(String jwt) {
        jwt = jwt.substring(7);
        Claims claims = Jwts.parser().setSigningKey(secretKey).build()
                .parseClaimsJws(jwt).getBody();
        return String.valueOf(claims.get("email"));
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.err.println("Invalid JWT token: " + e.getMessage());
            return false;
        }
    }

    public String getRoleFromToken(String jwt) {
        jwt = jwt.substring(7);
        Claims claims = Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();
        return String.valueOf(claims.get("role"));
    }
}