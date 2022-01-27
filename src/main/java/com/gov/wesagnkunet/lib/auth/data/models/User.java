package com.gov.wesagnkunet.lib.auth.data.models;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lib_auth_user")
public class User implements UserDetails {
	
	@Id
	
	private String username;

	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(
			new SimpleGrantedAuthority("ROLE_"+getRole().toString())
		);
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


	public static enum Role{
		CLIENT, ADMIN
	}

}
