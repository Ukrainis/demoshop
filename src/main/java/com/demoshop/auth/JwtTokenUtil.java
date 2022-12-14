package com.demoshop.auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.demoshop.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenUtil {

    private final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(String.format("%s,%s", user.getId(), user.getEmail()))
                .setIssuer("DemoIssuer")
                .claim("roles", user.getRoles().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            log.error("JWT expired. Message: " + ex.getLocalizedMessage());
        } catch (IllegalArgumentException ex) {
            log.error("Token is null, empty or only whitespace. Message: " + ex.getLocalizedMessage());
        } catch (MalformedJwtException ex) {
            log.error("JWT is invalid. Message: " + ex.getLocalizedMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("JWT is not supported. Message: " + ex.getLocalizedMessage());
        } catch (SignatureException ex) {
            log.error("Signature validation failed. Message: " + ex.getLocalizedMessage());
        }
        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
