package com.ison.app.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ison.app.dao.UserDAO;
import com.ison.app.message.response.UsersResponse;
import com.ison.app.model.Users;
import com.ison.app.services.UserService;
import com.ison.app.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO userDAO;
	
	@Override
	public UserDto save(UserDto userDto) throws Exception {
		return userDAO.save(userDto);
	}

	@Override
	public List<UsersResponse> getUsersList() throws Exception {
		List<UserDto> userList = userDAO.getUsersList();
		List<UsersResponse> userResList = new ArrayList<>();
		for (UserDto userDto : userList) {/*
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(users, userDto);*/
			userResList.add(new UsersResponse(userDto));
		}
		
		return userResList;
	}

	@Override
	public boolean UpDateUser(UserDto userDto) throws Exception {
		return userDAO.UpdateUser(userDto);
	}

}
