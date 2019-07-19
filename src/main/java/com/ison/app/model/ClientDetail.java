package com.ison.app.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;


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
	private BigInteger autogenClientDetailsId;

	@Column(name="CREATED_BY")
	private String createdBy;

	private String email;

	@Column(name="INVENTORY_CENTER_ID")
	private BigInteger inventoryCenterId;

	@Column(name="INVENTORY_CENTER_NAME")
	private String inventoryCenterName;

	@Column(name="INVENTORY_CLIENT_ID")
	private BigInteger inventoryClientId;

	@Column(name="INVENTORY_CLIENT_NAME")
	private String inventoryClientName;

	@Column(name="INVENTORY_REGION_ID")
	private BigInteger inventoryRegionId;

	@Column(name="INVENTORY_REGION_NAME")
	private String inventoryRegionName;

	@Lob
	private byte[] logo;

	@Column(name="MOBILE_NUMBER")
	private String mobileNumber;

	@Generated(GenerationTime.INSERT)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="REC_ADD_DT")
	private Date recAddDt;

	@Generated(GenerationTime.ALWAYS)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="REC_UPDATE_DT")
	private Date recUpdateDt;

	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	private String status;

	//bi-directional many-to-one association to ContactDetail
	@OneToMany(mappedBy="clientDetail")
	private List<ContactDetail> contactDetails;

	public ClientDetail() {
	}

	public BigInteger getAutogenClientDetailsId() {
		return this.autogenClientDetailsId;
	}

	public void setAutogenClientDetailsId(BigInteger autogenClientDetailsId) {
		this.autogenClientDetailsId = autogenClientDetailsId;
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

	public String getInventoryCenterName() {
		return this.inventoryCenterName;
	}

	public void setInventoryCenterName(String inventoryCenterName) {
		this.inventoryCenterName = inventoryCenterName;
	}

	public BigInteger getInventoryClientId() {
		return this.inventoryClientId;
	}

	public void setInventoryClientId(BigInteger inventoryClientId) {
		this.inventoryClientId = inventoryClientId;
	}

	public String getInventoryClientName() {
		return this.inventoryClientName;
	}

	public void setInventoryClientName(String inventoryClientName) {
		this.inventoryClientName = inventoryClientName;
	}

	public BigInteger getInventoryRegionId() {
		return this.inventoryRegionId;
	}

	public void setInventoryRegionId(BigInteger inventoryRegionId) {
		this.inventoryRegionId = inventoryRegionId;
	}

	public String getInventoryRegionName() {
		return this.inventoryRegionName;
	}

	public void setInventoryRegionName(String inventoryRegionName) {
		this.inventoryRegionName = inventoryRegionName;
	}

	public byte[] getLogo() {
		return this.logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Date getRecAddDt() {
		return this.recAddDt;
	}

	public void setRecAddDt(Date recAddDt) {
		this.recAddDt = recAddDt;
	}

	public Date getRecUpdateDt() {
		return this.recUpdateDt;
	}

	public void setRecUpdateDt(Date recUpdateDt) {
		this.recUpdateDt = recUpdateDt;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
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

	public List<ContactDetail> getContactDetails() {
		return this.contactDetails;
	}

	public void setContactDetails(List<ContactDetail> contactDetails) {
		this.contactDetails = contactDetails;
	}

	public ContactDetail addContactDetail(ContactDetail contactDetail) {
		getContactDetails().add(contactDetail);
		contactDetail.setClientDetail(this);

		return contactDetail;
	}

	public ContactDetail removeContactDetail(ContactDetail contactDetail) {
		getContactDetails().remove(contactDetail);
		contactDetail.setClientDetail(null);

		return contactDetail;
	}

}