package com.beeshop.sd44.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTService {
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private int accessToken;

    public SecretKey encodeSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(String role, String id) {
        return Jwts.builder()
                .subject(id + "")
                .issuer("BL Hieu")
                .audience().add("localhost:8080").and()
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessToken))
                .claim("role", role)
                .signWith(encodeSecretKey(), Jwts.SIG.HS256)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(encodeSecretKey())
                .build().parseSignedClaims(token)
                .getPayload();
    }

    public String refresh(String token) {
        String subject = getClaims(token).getSubject();
        String role = getClaims(token).get("role", String.class);
        return createToken(role, subject);
    }
}
