package com.ison.app.shared.dto;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import com.ison.app.model.ClientReportMap;

public class ContactDetailDto {
	
	public BigInteger autogenContactDetailsId;
	public String createdBy;
	public String email;
	public String mobileNumber;
	public String notificationStatus;
	public String personName;
	public Timestamp recAddDt;
	public Timestamp recUpdateDt;
	public String updatedBy;
	public String status;
	//bi-directional many-to-one association to ClientReportMap
	public List<ClientReportMap> clientReportMaps;
	public BigInteger getAutogenContactDetailsId() {
		return autogenContactDetailsId;
	}
	public void setAutogenContactDetailsId(BigInteger autogenContactDetailsId) {
		this.autogenContactDetailsId = autogenContactDetailsId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getNotificationStatus() {
		return notificationStatus;
	}
	public void setNotificationStatus(String notificationStatus) {
		this.notificationStatus = notificationStatus;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public Timestamp getRecAddDt() {
		return recAddDt;
	}
	public void setRecAddDt(Timestamp recAddDt) {
		this.recAddDt = recAddDt;
	}
	public Timestamp getRecUpdateDt() {
		return recUpdateDt;
	}
	public void setRecUpdateDt(Timestamp recUpdateDt) {
		this.recUpdateDt = recUpdateDt;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ClientReportMap> getClientReportMaps() {
		return clientReportMaps;
	}
	public void setClientReportMaps(List<ClientReportMap> clientReportMaps) {
		this.clientReportMaps = clientReportMaps;
	}
	
	
	
}
