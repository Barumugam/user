package com.ison.app.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the client_details database table.
 * 
 */
@Entity
@Table(name="client_details")
@NamedQuery(name="ClientDetail.findAll", query="SELECT c FROM ClientDetail c")
public class ClientDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="AUTOGEN_CLIENT_DETAILS_ID")
	private String autogenClientDetailsId;

	@Column(name="CONTACT_NUMBER")
	private String contactNumber;

	@Column(name="CONTACT_PERSON_NAME")
	private String contactPersonName;

	@Column(name="CREATED_BY")
	private String createdBy;

	private String email;

	@Column(name="INVENTORY_CENTER_ID")
	private BigInteger inventoryCenterId;

	@Column(name="INVENTORY_CLIENT_ID")
	private BigInteger inventoryClientId;

	@Column(name="NOTIFICATION_STATUS")
	private String notificationStatus;

	@Column(name="REC_ADD_DT")
	private Timestamp recAddDt;

	@Column(name="REC_UPDATE_DT")
	private Timestamp recUpdateDt;

	@Column(name="REPORT_FREQUENCY_ID")
	private BigInteger reportFrequencyId;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	public ClientDetail() {
	}

	public String getAutogenClientDetailsId() {
		return this.autogenClientDetailsId;
	}

	public void setAutogenClientDetailsId(String autogenClientDetailsId) {
		this.autogenClientDetailsId = autogenClientDetailsId;
	}

	public String getContactNumber() {
		return this.contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContactPersonName() {
		return this.contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigInteger getInventoryCenterId() {
		return this.inventoryCenterId;
	}

	public void setInventoryCenterId(BigInteger inventoryCenterId) {
		this.inventoryCenterId = inventoryCenterId;
	}

	public BigInteger getInventoryClientId() {
		return this.inventoryClientId;
	}

	public void setInventoryClientId(BigInteger inventoryClientId) {
		this.inventoryClientId = inventoryClientId;
	}

	public String getNotificationStatus() {
		return this.notificationStatus;
	}

	public void setNotificationStatus(String notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	public Timestamp getRecAddDt() {
		return this.recAddDt;
	}

	public void setRecAddDt(Timestamp recAddDt) {
		this.recAddDt = recAddDt;
	}

	public Timestamp getRecUpdateDt() {
		return this.recUpdateDt;
	}

	public void setRecUpdateDt(Timestamp recUpdateDt) {
		this.recUpdateDt = recUpdateDt;
	}

	public BigInteger getReportFrequencyId() {
		return this.reportFrequencyId;
	}

	public void setReportFrequencyId(BigInteger reportFrequencyId) {
		this.reportFrequencyId = reportFrequencyId;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}