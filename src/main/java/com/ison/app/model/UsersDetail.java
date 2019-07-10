package com.ison.app.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the users_details database table.
 * 
 */
@Entity
@Table(name="users_details")
@NamedQuery(name="UsersDetail.findAll", query="SELECT u FROM UsersDetail u")
public class UsersDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="AUTOGEN_USERS_DETAILS_ID")
	private String autogenUsersDetailsId;

	@Column(name="AUTOGEN_USERS_ID")
	private BigInteger autogenUsersId;
	
	@Column(name="AUTOGEN_ROLES_ID")
	private BigInteger autogenRolesId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="INVENTORY_CATEGORY_ID")
	private BigInteger inventoryCategoryId;

	@Column(name="INVENTORY_CENTER_ID")
	private BigInteger inventoryCenterId;

	@Column(name="INVENTORY_PROCESS_ID")
	private BigInteger inventoryProcessId;

	@Column(name="INVENTORY_REGION_ID")
	private BigInteger inventoryRegionId;

	@Generated(GenerationTime.INSERT)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="REC_ADD_DT")
	private Date recAddDt;

	@Generated(GenerationTime.ALWAYS)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="REC_UPDATE_DT")
	private Date recUpdateDt;

	@Column(name="SUPERVISOR_USERS_ID")
	private BigInteger supervisorUsersId;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	public UsersDetail() {
	}

	public String getAutogenUsersDetailsId() {
		return this.autogenUsersDetailsId;
	}

	public void setAutogenUsersDetailsId(String autogenUsersDetailsId) {
		this.autogenUsersDetailsId = autogenUsersDetailsId;
	}

	public BigInteger getAutogenUsersId() {
		return this.autogenUsersId;
	}

	public void setAutogenUsersId(BigInteger autogenUsersId) {
		this.autogenUsersId = autogenUsersId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public BigInteger getInventoryCategoryId() {
		return this.inventoryCategoryId;
	}

	public void setInventoryCategoryId(BigInteger inventoryCategoryId) {
		this.inventoryCategoryId = inventoryCategoryId;
	}

	public BigInteger getInventoryCenterId() {
		return this.inventoryCenterId;
	}

	public void setInventoryCenterId(BigInteger inventoryCenterId) {
		this.inventoryCenterId = inventoryCenterId;
	}

	public BigInteger getInventoryProcessId() {
		return this.inventoryProcessId;
	}

	public void setInventoryProcessId(BigInteger inventoryProcessId) {
		this.inventoryProcessId = inventoryProcessId;
	}

	public BigInteger getInventoryRegionId() {
		return this.inventoryRegionId;
	}

	public void setInventoryRegionId(BigInteger inventoryRegionId) {
		this.inventoryRegionId = inventoryRegionId;
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

	public BigInteger getSupervisorUsersId() {
		return this.supervisorUsersId;
	}

	public void setSupervisorUsersId(BigInteger supervisorUsersId) {
		this.supervisorUsersId = supervisorUsersId;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public BigInteger getAutogenRolesId() {
		return autogenRolesId;
	}

	public void setAutogenRolesId(BigInteger autogenRolesId) {
		this.autogenRolesId = autogenRolesId;
	}

	
}