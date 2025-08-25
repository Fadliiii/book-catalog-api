package com.ucorp.catalog.security.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucorp.catalog.security.model.AccessJWTToken;
import com.ucorp.catalog.security.util.JwtTokenFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UsernamePasswordAuthSucessHandler implements AuthenticationSuccessHandler {

	private final ObjectMapper objectMapper;
	
	private JwtTokenFactory jwtTokenFactory;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
	 UserDetails userDetails = (UserDetails)authentication.getPrincipal();
	AccessJWTToken token =	jwtTokenFactory.createAccessJWTToken(userDetails.getUsername(), userDetails.getAuthorities());
		Map< String,String> resultMap = new HashMap<>();
		resultMap.put("token",token.getRawToken());
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE);
		objectMapper.writeValue(response.getWriter(), resultMap);
		
	}

}
