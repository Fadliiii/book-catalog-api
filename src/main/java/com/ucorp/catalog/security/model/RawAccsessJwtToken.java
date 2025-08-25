package com.ucorp.catalog.security.model;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class RawAccsessJwtToken implements Token{
	
	private String token;
	
	public Jws<Claims> parseClaims(SecretKey secret){
		return Jwts.parser().verifyWith(secret).build().parseSignedClaims(token);
	}
	
	@Override
	public String getToken() {

		return this.token;
	}

	public RawAccsessJwtToken(String token) {
		super();
		this.token = token;
	}

}
