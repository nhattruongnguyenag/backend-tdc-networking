package com.chatapp.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

import org.springframework.stereotype.Component;


import java.io.Serializable;
import java.util.Date;

@Component
public class TokenProvider implements Serializable {
    public static final int TOKEN_VALIDITY_IN_MILISECONDS = 3_600_000;
    public static final int TOKEN__RESET_PASSWORD_TIME = 600_000;
    public static final String JWT_SIGNING_KEY = "com.template";
    Boolean tokenExpired = false;

    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, JWT_SIGNING_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY_IN_MILISECONDS))
                .compact();
    }

    public String generateResetPasswordToken(Long subject) {
        String s = String.valueOf(subject);
        return Jwts.builder()
                .setSubject(s)
                .signWith(SignatureAlgorithm.HS256, JWT_SIGNING_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN__RESET_PASSWORD_TIME))
                .compact();
    }

    public String extractEmailFromToken(String token) {
        final Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public String extractIdFromToken(String token) {
        final Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public Date extractExpirationDateFromToken(String token) {
        final Claims claims = getClaims(token);
        return claims.getExpiration();
    }

    public Boolean isTokenNotExpired(String token) {
        final Date currentTime = new Date();
        final Date expiration = extractExpirationDateFromToken(token);
        return currentTime.before(expiration);
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token) throws Exception {
        tryGetToken(token);
        return tokenExpired;
    }

    private Claims tryGetToken(String token) throws Exception {
        try {
            Claims claims = Jwts.parser().setSigningKey(JWT_SIGNING_KEY)
                    .parseClaimsJws(token).getBody();
            tokenExpired = true;
            return claims;
        } catch (ExpiredJwtException ex) {
            DefaultClaims claims = (DefaultClaims) ex.getClaims();
            return claims;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
