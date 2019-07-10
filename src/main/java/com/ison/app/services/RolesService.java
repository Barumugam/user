package com.ison.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;

import com.ison.app.model.Roles;
import com.ison.app.model.Users;

public interface RolesService {
	
	Optional<Roles> findByName(String roleName);
	
	public List<Object[]> getUserRoles(int userId);
	
	public List<GrantedAuthority> getGrantedAuthorities(Users users, List<Object[]> roles);

}
