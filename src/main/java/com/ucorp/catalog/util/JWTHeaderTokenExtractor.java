package com.ucorp.catalog.util;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JWTHeaderTokenExtractor implements TokenExtractor {

	private static final String HEADER_PREFIX = "Bearer ";
	@Override
	public String extract(String payload) {
		if(io.micrometer.common.util.StringUtils.isBlank(payload)) {
			throw new AuthenticationServiceException("Authorization header sould be provided");
		}
		if(payload.length() < HEADER_PREFIX.length()) {
			throw new AuthenticationServiceException("invalid authorization header ");
		}
		return payload.substring(HEADER_PREFIX.length(), payload.length());
	}

}
