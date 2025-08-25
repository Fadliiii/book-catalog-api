package com.ucorp.catalog.security.model;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JWTAuthenticationToken extends AbstractAuthenticationToken{

	private RawAccsessJwtToken rawAccsessJwtToken;
	
	private UserDetails userDetails;
	
	public JWTAuthenticationToken(RawAccsessJwtToken rawAccsessJwtToken) {
		super(null);
		this.rawAccsessJwtToken = rawAccsessJwtToken;
		super.setAuthenticated(false);
	}
	
	
	public JWTAuthenticationToken(UserDetails userDetails,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.eraseCredentials();
		this.userDetails = userDetails;
		this.setAuthenticated(true);
		
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		this.rawAccsessJwtToken = null;
	}


	@Override
	public Object getCredentials() {
		return this.rawAccsessJwtToken;
	}

	@Override
	public Object getPrincipal() {
		return this.userDetails;
	}

}
