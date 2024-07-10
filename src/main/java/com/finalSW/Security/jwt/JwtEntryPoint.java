package com.finalSW.Security.jwt;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalSW.CRUD.exceptions.MessageDto;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.error("Tokken no encontrado o invalido");
		//response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado");
		MessageDto dto = new MessageDto(HttpStatus.UNAUTHORIZED, "Token no encontrado o invalido");
		response.setContentType("application/json");
		response.setStatus(dto.getStatus().value());
		response.getWriter().write(new ObjectMapper().writeValueAsString(dto));
		response.getWriter().flush();
		response.getWriter().close();
	}
}
