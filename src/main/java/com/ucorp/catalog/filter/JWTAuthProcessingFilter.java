package com.ucorp.catalog.filter;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpMessageConverterAuthenticationSuccessHandler.AuthenticationSuccess;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.ucorp.catalog.security.model.AnonymousAuthentication;
import com.ucorp.catalog.security.model.JWTAuthenticationToken;
import com.ucorp.catalog.security.model.RawAccsessJwtToken;
import com.ucorp.catalog.util.TokenExtractor;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthProcessingFilter extends AbstractAuthenticationProcessingFilter {

	private final TokenExtractor tokenExtractor;
	
	private final AuthenticationFailureHandler failureHandler;
	
	public JWTAuthProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher,
			TokenExtractor tokenExtractor, AuthenticationFailureHandler failureHandler) {
		super(requiresAuthenticationRequestMatcher);
		// TODO Auto-generated constructor stub
		this.tokenExtractor = tokenExtractor;
		this.failureHandler = failureHandler;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		//intercept dan ambil header
		String authoriaztionHeader = request.getHeader("Authorization");
		
		//extract header tersebut untuk mendapatkan jwt-nya
		String jwt = tokenExtractor.extract(authoriaztionHeader);
		
		//bungkus object token untuk proses autentikasi
		RawAccsessJwtToken rawToken = new RawAccsessJwtToken(jwt);
		return this.getAuthenticationManager().authenticate(new JWTAuthenticationToken (rawToken));
	
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		SecurityContext ctx = SecurityContextHolder.createEmptyContext();
		ctx.setAuthentication(authResult);
		SecurityContextHolder.setContext(ctx);
		System.out.println("authorities : " + ctx.getAuthentication().getAuthorities());
		
		chain.doFilter(request, response);
		//super.successfulAuthentication(request, response, chain, authResult);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthentication());

		this.failureHandler.onAuthenticationFailure(request, response, failed);
	}

}
