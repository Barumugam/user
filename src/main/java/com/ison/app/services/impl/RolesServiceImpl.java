package com.ison.app.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.ison.app.dao.RolesDAO;
import com.ison.app.model.Roles;
import com.ison.app.model.Users;
import com.ison.app.services.RolesService;

@Service
public class RolesServiceImpl implements RolesService {

	@Autowired
	RolesDAO rolesDAO;
	
	@Override
	public Optional<Roles> findByName(String roleName) {
		return rolesDAO.findByName(roleName);
	}

	@Override
	public List<Object[]> getUserRoles(int userId) {
		return rolesDAO.getUserRoles(userId);
	}
	
	public List<GrantedAuthority> getGrantedAuthorities(Users users, List<Object[]> roles){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(Object[] role : roles){
			authorities.add(new SimpleGrantedAuthority(role.toString()));
		}
		return authorities;
	}

}
