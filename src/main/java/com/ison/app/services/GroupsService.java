package com.ison.app.services;

import java.util.List;

import com.ison.app.message.request.UserGroupMappingRequest;
import com.ison.app.shared.dto.UserGroupDto;

public interface GroupsService {
	
    public UserGroupDto save(UserGroupDto userGroupDto) throws Exception;
    
    public boolean UpDateGroup(UserGroupDto userGroupDto) throws Exception;

	UserGroupDto findByGroupId(String groupId) throws Exception;

	List<UserGroupDto> getGroupsList(String status) throws Exception;
	
	public boolean userGroupMapping(UserGroupMappingRequest userGroupMappingRequest) throws Exception;
	
	
}
