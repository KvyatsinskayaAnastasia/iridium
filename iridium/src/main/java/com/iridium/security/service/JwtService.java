package com.iridium.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    /**
     * Extract username from jwt token.
     * @param token jwt token
     * @return username
     */
    public final String extractUsername(final String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extract claims from jwt token.
     * @param token jwt token
     * @param claimsResolver function to get claims
     * @param <T> type of claim
     * @return claims
     */
    public final <T> T extractClaim(final String token, final Function<? super Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

    /**
     * Generate jwt token.
     * @param userDetails user details
     * @return jwt token
     */
    public final String generateToken(final UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generate jwt token.
     * @param extraClaims extra claims
     * @param userDetails user details
     * @return jwt token
     */
    public final String generateToken(final Map<String, Object> extraClaims, final UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    /**
     * Get jwt token expiration time.
     * @return jwt token expiration time
     */
    public final long getExpirationTime() {
        return jwtExpiration;
    }

    /**
     * Build jwt token.
     * @param extraClaims extra claims
     * @param userDetails user details
     * @param expiration expiration time
     * @return built jwt token
     */
    private String buildToken(final Map<String, Object> extraClaims, final UserDetails userDetails,
                              final long expiration) {
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    /**
     * Check if jwt token is valid.
     * @param token jwt token
     * @param userDetails user details
     * @return true, if jwt token is valid
     */
    public final boolean isTokenValid(final String token, final UserDetails userDetails) {
        return (extractUsername(token).equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Check if jwt token is expired.
     * @param token jwt token
     * @return true, if jwt token is expired
     */
    private boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extract jxt token expiration date.
     * @param token jwt token
     * @return jwt token expiration date
     */
    private Date extractExpiration(final String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extract all claims from jwt token.
     * @param token jwt token
     * @return extracted claims
     */
    private Claims extractAllClaims(final String token) {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    /**
     * Get sign in key.
     * @return sign in key
     */
    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
}
