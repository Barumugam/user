package com.ison.app.message.response;

import com.ison.app.shared.dto.UserGroupDto;

public class UserGroupResponse {
	
	private String autogenUserGroupsId;
	private String groupId;
	private String groupName;
	private String status;
	
	public UserGroupResponse() {}
	
	public UserGroupResponse(UserGroupDto userGroupDto) {
		this.autogenUserGroupsId = userGroupDto.getAutogenUserGroupsId();
		this.groupId = userGroupDto.getGroupId();
		this.groupName = userGroupDto.getGroupName();
		this.status = userGroupDto.getStatus();
	}

	public String getAutogenUserGroupsId() {
		return autogenUserGroupsId;
	}

	public void setAutogenUserGroupsId(String autogenUserGroupsId) {
		this.autogenUserGroupsId = autogenUserGroupsId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
