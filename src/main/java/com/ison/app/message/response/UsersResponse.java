package com.ison.app.message.response;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ison.app.model.Client;
import com.ison.app.shared.dto.UserDto;

public class UsersResponse {
	    
	public String id;
	public String usersDetailsId;
	public BigInteger inventoryCategoryId;
	public BigInteger inventoryCenterId;
	public BigInteger inventoryProcessId;
	public BigInteger inventoryRegionId;
	public String centerName;
	public String email;
	public String employeeId;
	public String firstName;
	public String lastName;
	public String mobileNumber;
	public String processName;
	public Date recAddDt;
	public Date recUpdateDt;
	public String regionName;
	public String status;
	public String supervisorName;
	public List<Client> clients = new ArrayList<>();
    private BigInteger supervisorUsersId;    
    private String categoryName;
    private BigInteger roleId;
    private String createdBy;
    private String updatedBy;
    private String roleName;
    
    public UsersResponse() {}

	public UsersResponse(UserDto userDto) {
		this.id = userDto.autogenUsersId;
		this.inventoryCategoryId = userDto.inventoryCategoryId;
		this.inventoryCenterId = userDto.inventoryCenterId;
		this.inventoryProcessId = userDto.inventoryProcessId;
		this.inventoryRegionId = userDto.inventoryRegionId;
		this.centerName = userDto.centerName;
		this.email = userDto.email;
		this.employeeId = userDto.employeeId;
		this.firstName = userDto.firstName;
		this.lastName = userDto.lastName;
		this.mobileNumber = userDto.mobileNumber;
		this.processName = userDto.processName;
		this.recAddDt = userDto.recAddDt;
		this.recUpdateDt = userDto.recUpdateDt;
		this.regionName = userDto.regionName;
		this.status = userDto.status;
		this.supervisorName = userDto.supervisorName;
		this.clients = userDto.clients;
		this.supervisorUsersId = userDto.supervisorUsersId;
		this.categoryName = userDto.categoryName;
		this.roleId = userDto.autogenRolesId;
		this.roleName = userDto.roleName;
		this.usersDetailsId = userDto.autogenUsersDetailsId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsersDetailsId() {
		return usersDetailsId;
	}

	public void setUsersDetailsId(String usersDetailsId) {
		this.usersDetailsId = usersDetailsId;
	}

	public BigInteger getInventoryCategoryId() {
		return inventoryCategoryId;
	}

	public void setInventoryCategoryId(BigInteger inventoryCategoryId) {
		this.inventoryCategoryId = inventoryCategoryId;
	}

	public BigInteger getInventoryCenterId() {
		return inventoryCenterId;
	}

	public void setInventoryCenterId(BigInteger inventoryCenterId) {
		this.inventoryCenterId = inventoryCenterId;
	}

	public BigInteger getInventoryProcessId() {
		return inventoryProcessId;
	}

	public void setInventoryProcessId(BigInteger inventoryProcessId) {
		this.inventoryProcessId = inventoryProcessId;
	}

	public BigInteger getInventoryRegionId() {
		return inventoryRegionId;
	}

	public void setInventoryRegionId(BigInteger inventoryRegionId) {
		this.inventoryRegionId = inventoryRegionId;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public Date getRecAddDt() {
		return recAddDt;
	}

	public void setRecAddDt(Date recAddDt) {
		this.recAddDt = recAddDt;
	}

	public Date getRecUpdateDt() {
		return recUpdateDt;
	}

	public void setRecUpdateDt(Date recUpdateDt) {
		this.recUpdateDt = recUpdateDt;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public BigInteger getSupervisorUsersId() {
		return supervisorUsersId;
	}

	public void setSupervisorUsersId(BigInteger supervisorUsersId) {
		this.supervisorUsersId = supervisorUsersId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public BigInteger getRoleId() {
		return roleId;
	}

	public void setRoleId(BigInteger roleId) {
		this.roleId = roleId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
