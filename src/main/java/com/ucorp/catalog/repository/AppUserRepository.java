package com.ucorp.catalog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ucorp.catalog.domain.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {

	public Optional<AppUser>findByUsername(String username);
	
}
