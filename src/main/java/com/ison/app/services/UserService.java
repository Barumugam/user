package com.ison.app.services;

import java.util.List;

import com.ison.app.message.response.UsersResponse;
import com.ison.app.shared.dto.UserDto;

public interface UserService {
	
	public UserDto save(UserDto userDto) throws Exception;

	public List<UsersResponse> getUsersList() throws Exception;
	
	public boolean UpDateUser(UserDto userDto) throws Exception;
	
	
}
