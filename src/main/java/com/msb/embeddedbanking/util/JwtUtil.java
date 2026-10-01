package com.msb.embeddedbanking.util;
import com.msb.embeddedbanking.enums.TokenType;
import com.msb.embeddedbanking.repository.entity.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.access-token-expiration}")
    private long jwtAccessTokenExpiration;

    @Value("${jwt.refresh-token-expiration}")
    private long jwtRefreshTokenExpiration;


    public boolean isAccessToken(String token) {
        TokenType tokenType = getTokenTypeFromToken(token);
        return tokenType.equals(TokenType.ACCESS);
    }
    public boolean isRefreshToken(String token) {
        TokenType tokenType = getTokenTypeFromToken(token);
        return tokenType.equals(TokenType.REFRESH);
    }
    public String generateAccessToken(User user) {
        return generateToken(TokenType.ACCESS,jwtAccessTokenExpiration,user);
    }
    public String generateRefreshToken(User user) {
        return generateToken(TokenType.REFRESH,jwtRefreshTokenExpiration,user);
    }
    public boolean isValidToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJwt(token);
            return true;
        }catch (MalformedJwtException e){
            log.error("Invalid JWT token");
        }catch(ExpiredJwtException e){
            log.error("Expired JWT token");
        }catch(UnsupportedJwtException e){
            log.error("Unsupported JWT token");
        }catch(IllegalArgumentException e){
            log.error("JWT claims string is empty");
        }

        return false;
    }
    private String generateToken(TokenType tokenType, Long expiration, User user) {
        Date now = new Date();
        Date expiryAt = new Date(now.getTime() + expiration);
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId",user.getId())
                .claim(AppConstant.TOKEN_TYPE,tokenType)
                .setIssuedAt(now)
                .setExpiration(expiryAt)
                .signWith(getSigningKey())
                .compact();
    }
    public String getUserNameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }
    public Long getUserIdFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("userId",Long.class);
    }
    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }
    private TokenType getTokenTypeFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return TokenType.valueOf(claims.get(AppConstant.TOKEN_TYPE,String.class));
    }
    private Key getSigningKey(){
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public Long getRefreshTokenExpirationInSeconds() {
        return jwtRefreshTokenExpiration / 1000;
    }
    public Long getAccessTokenExpirationInSeconds() {
        return jwtAccessTokenExpiration / 1000;
    }
}
