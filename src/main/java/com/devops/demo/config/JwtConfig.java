package com.devops.demo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;
import java.util.logging.Logger;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtConfig {

    private static final Logger logger = Logger.getLogger(JwtConfig.class.getName());

    private final SecretKey secretKey;
    private final long expirationTime;

    public JwtConfig(
            @Value("${security.jwt.secret-key-path}") String secretKeyPath,
            @Value("${security.jwt.expiration-time}") long expirationTime) {

        this.expirationTime = expirationTime;
        this.secretKey = loadSecretKey(secretKeyPath);
    }

    private SecretKey loadSecretKey(String secretKeyPath) {
        try {
            // Check if file exists
            if (!Files.exists(Paths.get(secretKeyPath))) {
                throw new IllegalStateException("❌ ERROR: JWT secret key file is missing at " + secretKeyPath);
            }

            // Read key content
            byte[] keyBytes = Files.readAllBytes(Paths.get(secretKeyPath));
            String keyContent = new String(keyBytes).trim();

            // Validate key content
            if (keyContent.isEmpty()) {
                throw new IllegalStateException("❌ ERROR: JWT secret key file is empty!");
            }

            // Decode Base64 key
            byte[] decodedKey = Base64.getDecoder().decode(keyContent);
            if (decodedKey.length < 32) {
                throw new IllegalArgumentException("❌ ERROR: JWT secret key must be at least 256 bits (32 bytes).");
            }

            logger.info("✅ JWT secret key loaded successfully from " + secretKeyPath);
            return Keys.hmacShaKeyFor(decodedKey);

        } catch (IOException e) {
            throw new IllegalStateException("❌ ERROR: Cannot read JWT secret key file at " + secretKeyPath, e);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("❌ ERROR: JWT secret key is not valid Base64", e);
        }
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
}
