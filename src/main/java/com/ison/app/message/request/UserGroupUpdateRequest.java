package com.ison.app.message.request;

public class UserGroupUpdateRequest {
	
	private String autogenUserGroupsId;
	private String groupId;
	private String groupName;
	private String status;
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
