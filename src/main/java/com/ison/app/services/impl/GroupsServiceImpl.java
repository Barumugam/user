package com.ison.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ison.app.dao.GroupDAO;
import com.ison.app.message.request.UserGroupMappingRequest;
import com.ison.app.services.GroupsService;
import com.ison.app.shared.dto.UserGroupDto;

@Service
public class GroupsServiceImpl implements GroupsService{

	@Autowired
	GroupDAO groupDAO;
	
	@Override
	public UserGroupDto save(UserGroupDto userGroupDto) throws Exception {
		return groupDAO.save(userGroupDto);
	}

	public boolean UpDateGroup(UserGroupDto userGroupDto) throws Exception {
		return groupDAO.UpDateGroup(userGroupDto);
	}

	@Override
	public UserGroupDto findByGroupId(String groupId) throws Exception {
		return groupDAO.findByGroupId(groupId);
	}

	@Override
	public List<UserGroupDto> getGroupsList(String status) throws Exception {
		return groupDAO.getGroupsList(status);
	}
	
	public boolean userGroupMapping(UserGroupMappingRequest userGroupMappingRequest) throws Exception {
		return groupDAO.userGroupMapping(userGroupMappingRequest);
	}

}
