package com.sstof.auth.tools;

import com.sstof.auth.exception.AuthenticateException;
import com.sstof.users.domain.User;
import com.sstof.users.domain.UserRole;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    private final int REFRESH_TOKEN_EXPIRATION = 86400000 * 7;
    private final int ACCESS_TOKEN_EXPIRATION = 86400000;

    public UserRole extractUserRole(String token) {
        return extractClaim(token, this::getUserRole);
    }

    private UserRole getUserRole(Claims claim) {
        String roleString = claim.get("role", String.class);
        if (roleString == null) throw new AuthenticateException("Jwt Claim에 role이 없습니다.");
        return UserRole.valueOf(roleString);
    }

    public int extractUserId(String token) {
        return extractClaim(token, this::getUserId);
    }

    private int getUserId(Claims claim) {
        Integer userId = claim.get("userId", Integer.class);
        if (userId == null) throw new AuthenticateException("Jwt Claim에 userId가 없습니다.");
        return userId;
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException expiredJwtException) {
            throw new AuthenticateException("Jwt 토큰이 만료되었습니다.");
        } catch (UnsupportedJwtException unsupportedJwtException) {
            throw new AuthenticateException("지원되지 않는 Jwt 토큰입니다.");
        } catch (MalformedJwtException malformedJwtException) {
            throw new AuthenticateException("잘못된 형식의 Jwt 토큰입니다.");
        } catch (SignatureException signatureException) {
            throw new AuthenticateException("Jwt Signature이 잘못된 값입니다.");
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new AuthenticateException("Jwt 헤더 값이 잘못되었습니다.");
        }
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateAccessToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("role", user.getRole());
        return createToken(claims, ACCESS_TOKEN_EXPIRATION);
    }

    public String generateAccessToken(Integer userId, UserRole role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("role", role);
        return createToken(claims, ACCESS_TOKEN_EXPIRATION);
    }

    public String generateRefreshToken(Integer userId, UserRole role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("role", role);
        return createToken(claims, REFRESH_TOKEN_EXPIRATION);
    }

    private String createToken(Map<String, Object> claims, int expirationTimeInMs) {
        return Jwts.builder().setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
