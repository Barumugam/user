package com.ison.app.dao;

import java.util.List;
import java.util.Optional;

import com.ison.app.shared.dto.UserDto;

public interface UserDAO {
	
	Optional<UserDto> findByUsername(String username) throws Exception;
	
	Boolean existsByUsername(String username) throws Exception;
	
    Boolean existsByEmail(String email) throws Exception;
    
    public UserDto save(UserDto userDto) throws Exception;
    
    public List<UserDto> getUsersList() throws Exception;
    
    public boolean UpdateUser(UserDto userDto) throws Exception;
    
    public boolean UpdateUserStatus(UserDto userDto) throws Exception; 
}
