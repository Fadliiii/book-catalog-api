package com.ucorp.catalog.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ucorp.catalog.dto.UserDetailResponDTO;

public interface AppUserService extends UserDetailsService{
	
	public UserDetailResponDTO findUserDetail();
}
