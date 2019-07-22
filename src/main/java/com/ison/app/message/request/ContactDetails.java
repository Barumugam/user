package com.ison.app.message.request;

import java.math.BigInteger;
import java.util.List;

public class ContactDetails {
	
	private BigInteger contactDetailsId;
	private String email;
	private String mobileNumber;
	private String notificationStatus;
	private String personName;
	private String status;
	private List<ClientReportMapCreateRequest> clientReportMaps;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ClientReportMapCreateRequest> getClientReportMaps() {
		return clientReportMaps;
	}
	public void setClientReportMaps(List<ClientReportMapCreateRequest> clientReportMaps) {
		this.clientReportMaps = clientReportMaps;
	}
	public BigInteger getContactDetailsId() {
		return contactDetailsId;
	}
	public void setContactDetailsId(BigInteger contactDetailsId) {
		this.contactDetailsId = contactDetailsId;
	}

}
