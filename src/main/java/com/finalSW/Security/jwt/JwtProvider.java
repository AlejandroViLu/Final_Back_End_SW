package com.finalSW.Security.jwt;

import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.finalSW.Security.service.UserPrincipal;
import com.mongodb.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@SuppressWarnings("deprecation")
@Component
public class JwtProvider {
	private static final String secret = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";
	private static final int expiration = 36000;
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String,Object> extraClaims, UserDetails user) {
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(user.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    private Key getKey() {
       byte[] keyBytes=Decoders.BASE64.decode(secret);
       return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private Claims getAllClaims(String token)
    {
        return Jwts
            .parser()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }
	public String generatedToken(Authentication a) {
		UserPrincipal u = (UserPrincipal) a.getPrincipal();
		return Jwts.builder()
				.signWith(getKey())
				.setSubject(u.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+expiration*1000))
				.claim("roles", getRoles(u))
				.compact();
				
	}

	private List<String> getRoles(UserPrincipal u){
		return u.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).build().parseClaimsJws(token).getBody().getSubject();
			return true;
		}catch (Exception e) {
			logger.error("Fail Token");
		}
		return false;
	}

	
}
