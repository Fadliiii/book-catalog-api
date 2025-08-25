package com.ucorp.catalog.service.impl;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.ucorp.catalog.dto.UserDetailResponDTO;
import com.ucorp.catalog.exception.ResourceNotFoundException;
import com.ucorp.catalog.repository.AppUserRepository;
import com.ucorp.catalog.service.AppUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {

	private final AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return appUserRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid.username"));
	}

	@Override
	public UserDetailResponDTO findUserDetail() {
		SecurityContext context = SecurityContextHolder.getContext();
		UserDetailResponDTO dto = new UserDetailResponDTO();
		String username = 	context.getAuthentication().getName();
		dto.setName(username);
		return dto;
	}

}
