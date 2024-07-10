package com.finalSW.Security.jwt;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.finalSW.Security.service.UserDetailsServiceImpl;
@Component
public class JwtFilter extends OncePerRequestFilter{
	@Autowired
	JwtProvider jp;
	
	@Autowired
	UserDetailsServiceImpl udsi;
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = getToken(req);
			if(token != null && jp.validateToken(token)) {
				String username = jp.getUsernameFromToken(token);
				UserDetails userdetail = udsi.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken a = new UsernamePasswordAuthenticationToken(userdetail.getUsername(), null, userdetail.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(a);
			}
		}catch (UsernameNotFoundException e) {
			logger.error("Bloqueo de filtro request");
		}
		filterChain.doFilter(req, res);
	}

	private String getToken(HttpServletRequest req) {
		String header = req.getHeader("Authorization");
		if(header != null && header.startsWith("Bearer ")) {
			return header.replace("Bearer ", "");
		}
		return null;
	}
	
}
