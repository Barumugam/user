package com.ison.app.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.ison.app.model.Roles;

public interface RolesDAO {
	
	Optional<Roles> findByName(String roleName);
	
	public List<Object[]> getUserRoles(int userId);
	
	public String getRoleById(BigInteger roleId) throws Exception;
}
