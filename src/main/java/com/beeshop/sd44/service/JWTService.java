package com.beeshop.sd44.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
    private long accessExpirationSeconds;
    @Value("${jwt.refresh}")
    private long refreshExpirationSeconds;

    public SecretKey encodeSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(String role, String id) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .subject(id + "")
                .issuer("BL Hieu")
                .issuedAt(new Date(now))
                .expiration(new Date(now + accessExpirationSeconds * 1000))
                .claim("role", role)
                .signWith(encodeSecretKey(), Jwts.SIG.HS256)
                .compact();
    }

    public String createRefreshToken(String role, String id) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .subject(id + "")
                .issuer("BL Hieu")
                .issuedAt(new Date(now))
                .expiration(new Date(now + refreshExpirationSeconds * 1000))
                .claim("role", role)
                .signWith(encodeSecretKey(), Jwts.SIG.HS256)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(encodeSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public long getRefreshExpirationSeconds() {
        return refreshExpirationSeconds;
    }
}
