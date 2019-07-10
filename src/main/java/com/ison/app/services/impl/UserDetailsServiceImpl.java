package com.ison.app.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ison.app.dao.RolesDAO;
import com.ison.app.dao.UserDAO;
import com.ison.app.model.Roles;
import com.ison.app.shared.dto.UserDto;
 
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    UserDAO userDAO;
    
    @Autowired
    RolesDAO rolesDAO;
 
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
    	 UserDto userDto = null;
		try {
			userDto = userDAO.findByUsername(username).orElseThrow(() -> 
			                new UsernameNotFoundException("User account does not exists. please contact your system administrator.")
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if("NEW".equalsIgnoreCase(userDto.getStatus())) {
			UserDto userDtoUpdate = new UserDto();
			userDtoUpdate.setAutogenUsersId(userDto.getAutogenUsersId());
			try {
				userDAO.UpdateUserStatus(userDtoUpdate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		userDto.setAuthorities(getAuthorities(userDto.getRoles()));
        return UserPrinciple.build(new UserDto(userDto));
    }
    
    public Boolean existsByUsername(String username) throws Exception {
    	return userDAO.existsByUsername(username);
    }
    
    public Boolean existsByEmail(String email) throws Exception {
    	return userDAO.existsByEmail(email);
    }
    
    private Collection<? extends GrantedAuthority> getAuthorities(Set<Roles> privileges) {
  	  
        return getGrantedAuthorities(privileges);
    }
 
private List<GrantedAuthority> getGrantedAuthorities(Set<Roles> privileges) {
List<GrantedAuthority> authorities = new ArrayList<>();
for (Roles privilege : privileges) {
    authorities.add(new SimpleGrantedAuthority(privilege.getRoleName()));
}
return authorities;
}

}
