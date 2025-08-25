package com.ucorp.catalog.domain;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "app_user",indexes ={
		@Index(name="uk_app_user_secure_id",columnList = "secureId"),
		@Index(name="uk_username",columnList = "username")
})
public class AppUser extends AbstarctBaseEntity implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1004930043500706680L;

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username",nullable = false)
	private String username;
	
	@Column(name = "password",nullable = false)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)//default fetch lazy
	@JoinTable(name = "user_role",joinColumns = {
			@JoinColumn(name="user_id",referencedColumnName = "id")
	},inverseJoinColumns = {
			@JoinColumn(name="role_id",referencedColumnName ="id")
	})
	private Set<Role>roles;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	

}
