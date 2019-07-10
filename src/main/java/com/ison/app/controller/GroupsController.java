package com.ison.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ison.app.message.request.SearchForm;
import com.ison.app.message.request.UserGroupMappingRequest;
import com.ison.app.message.request.UserGroupRequest;
import com.ison.app.message.request.UserGroupUpdateRequest;
import com.ison.app.message.response.GenericResponse;
import com.ison.app.message.response.UserGroupResponse;
import com.ison.app.services.GroupsService;
import com.ison.app.services.RolesService;
import com.ison.app.shared.dto.UserGroupDto;

@RestController
@RequestMapping("/usergroup")
public class GroupsController {

	@Autowired
	RolesService rolesService;
	
	@Autowired
	GroupsService groupsService;
	
	 @PostMapping("/search")
	 public ResponseEntity<GenericResponse> showUserGroupList(@RequestBody SearchForm searchRequest) throws Exception {
		 
		 	List<UserGroupResponse> userGroupResList = new ArrayList<>(); 
	    	List<UserGroupDto> usergroupList = groupsService.getGroupsList(searchRequest.getStatus());
	    	for (UserGroupDto userGroupDto : usergroupList) {
	    		userGroupResList.add(new UserGroupResponse(userGroupDto));
			}
	    	GenericResponse genericResponse = new GenericResponse();
	    	if(!userGroupResList.isEmpty()) {
	    		genericResponse.setStatus(1008);
	  	        genericResponse.setError("Success");
	  	        genericResponse.setMessage("User Groups Found!.");
	  	      genericResponse.setValue(userGroupResList);
	    	} else {
	    		genericResponse.setStatus(1001);
		        genericResponse.setError("Failure");
		        genericResponse.setMessage("User Groups Not Found!.");
	    	}
	        return ResponseEntity.ok(new GenericResponse(genericResponse));
	    }   
	 
	 @PostMapping("/usergroupmapping")
	 public ResponseEntity<GenericResponse> UserGroupMapping(@RequestBody UserGroupMappingRequest userGroupMappingRequest) throws Exception {
	    	boolean usergroupList = groupsService.userGroupMapping(userGroupMappingRequest);
	    	GenericResponse genericResponse = new GenericResponse();
	    	if(usergroupList) {
	    		genericResponse.setStatus(1005);
	  	        genericResponse.setError("Success");
	  	        genericResponse.setMessage("User Groups Mapped!.");
	  	        genericResponse.setValue(usergroupList);
	    	} else {
	    		genericResponse.setStatus(1009);
		        genericResponse.setError("Failure");
		        genericResponse.setMessage("User Groups already mapped!.");
	    	}
	        return ResponseEntity.ok(new GenericResponse(genericResponse));
	    }  
	 
	 @PostMapping("/create")
	 public ResponseEntity<GenericResponse> createUserGroups(@RequestBody UserGroupRequest userGroupRequest) throws Exception {
		 
		 UserGroupDto userGroupDto = new UserGroupDto();
		 BeanUtils.copyProperties(userGroupRequest, userGroupDto);
		 groupsService.save(userGroupDto);
	    	GenericResponse genericResponse = new GenericResponse();
	    	if(!userGroupDto.getAutogenUserGroupsId().isEmpty()) {
	    		genericResponse.setStatus(1007);
	  	        genericResponse.setError("Success");
	  	        genericResponse.setMessage("User Groups Created!.");
	  	      genericResponse.setValue(userGroupDto);
	    	}
	        return ResponseEntity.ok(new GenericResponse(genericResponse));
	    }   
	 
	 @PostMapping("/update")
	public ResponseEntity<GenericResponse> updateUserGroups(@RequestBody UserGroupUpdateRequest userGroupUpdateRequest)
			throws Exception {

		UserGroupDto userGroupDto = new UserGroupDto();
		BeanUtils.copyProperties(userGroupUpdateRequest, userGroupDto);
		boolean result = groupsService.UpDateGroup(userGroupDto);
		GenericResponse genericResponse = new GenericResponse();
		if (result) {
			if (!userGroupDto.getAutogenUserGroupsId().isEmpty()) {
				genericResponse.setStatus(1007);
				genericResponse.setError("Success");
				genericResponse.setMessage("User Groups Created!.");
				genericResponse.setValue(userGroupDto);
			}
		}

		return ResponseEntity.ok(new GenericResponse(genericResponse));
	}   
	 
	
	
}
