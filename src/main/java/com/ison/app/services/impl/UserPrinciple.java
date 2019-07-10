package com.ison.app.services.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ison.app.model.Client;
import com.ison.app.model.Roles;
import com.ison.app.shared.dto.UserDto;
 
public class UserPrinciple implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String autogenUsersId;	
	public String autogenUsersDetailsId;
	public BigInteger inventoryCategoryId;
	public BigInteger inventoryCenterId;
	public BigInteger inventoryProcessId;
	public BigInteger inventoryRegionId;	
	private String centerName;
	private String email;
	private String employeeId;
	private String firstName;
	private String lastName;
	private int loginAttempt;
	private String mobileNumber;
	private String password;
	private String processName;
	private Date recAddDt;
	private Date recUpdateDt;
	private String regionName;
	private String status;
	private String supervisorName;
	private Set<Roles> roles = new HashSet<>();
	private List<Client> clients = new ArrayList<>();
	private Collection<? extends GrantedAuthority> authorities;

	public UserPrinciple(UserDto userDto) {
		this.autogenUsersId = userDto.autogenUsersId;
		this.autogenUsersDetailsId = userDto.autogenUsersDetailsId;
		this.inventoryCategoryId = userDto.inventoryCategoryId;
		this.inventoryCenterId = userDto.inventoryCenterId;
		this.inventoryProcessId = userDto.inventoryProcessId;
		this.inventoryRegionId = userDto.inventoryRegionId;
		this.centerName = userDto.centerName;
		this.email = userDto.email;
		this.employeeId = userDto.employeeId;
		this.firstName = userDto.firstName;
		this.lastName = userDto.lastName;
		this.loginAttempt = userDto.loginAttempt;
		this.mobileNumber = userDto.mobileNumber;
		this.password = userDto.password;
		this.processName = userDto.processName;
		this.recAddDt = userDto.recAddDt;
		this.recUpdateDt = userDto.recUpdateDt;
		this.regionName = userDto.regionName;
		this.status = userDto.status;
		this.supervisorName = userDto.supervisorName;
		this.roles = userDto.roles;
		this.clients = userDto.clients;
		this.authorities = userDto.getAuthorities();
	}

	public static UserPrinciple build(UserDto userDto) {
		return new UserPrinciple(userDto);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserPrinciple user = (UserPrinciple) o;
		return Objects.equals(autogenUsersId, user.getAutogenUsersId());
	}

	public String getAutogenUsersId() {
		return autogenUsersId;
	}

	public void setAutogenUsersId(String autogenUsersId) {
		this.autogenUsersId = autogenUsersId;
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

	public int getLoginAttempt() {
		return loginAttempt;
	}

	public void setLoginAttempt(int loginAttempt) {
		this.loginAttempt = loginAttempt;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}


	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAutogenUsersDetailsId() {
		return autogenUsersDetailsId;
	}

	public void setAutogenUsersDetailsId(String autogenUsersDetailsId) {
		this.autogenUsersDetailsId = autogenUsersDetailsId;
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

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

}