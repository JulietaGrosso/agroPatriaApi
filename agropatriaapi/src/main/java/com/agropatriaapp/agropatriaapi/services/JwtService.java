package com.agropatriaapp.agropatriaapi.services;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private static String secret = "f8sgCVEMjbL_ISPN63$3MAM0123456789";

    public static String generateToken(int userId) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        long nowMillis = System.currentTimeMillis();

        Date issuedAt = new Date(nowMillis);
        Date expiration = new Date(nowMillis + (14L * 24 * 60 * 60 * 1000)); // 14 días

        return Jwts.builder()
                .subject(String.valueOf(userId))
                .issuedAt(issuedAt)
                .expiration(expiration)
                .claim("id", userId)
                .signWith(key)
                .compact();
    }

        // Get username from JWT token
    public String getUsernameFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    // Validate JWT token
    public boolean validateJwtToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);

            return true;
        } catch (SecurityException e) {
            System.out.println("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }
        return false;
    }

}
