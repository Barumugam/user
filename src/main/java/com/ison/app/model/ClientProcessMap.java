package com.ison.app.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the client_process_map database table.
 * 
 */
@Entity
@Table(name="client_process_map")
@NamedQuery(name="ClientProcessMap.findAll", query="SELECT c FROM ClientProcessMap c")
public class ClientProcessMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="AUTOGEN_CLIENT_PROCESS_MAP_ID")
	private String autogenClientProcessMapId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="INVENTORY_CLIENT_ID")
	private BigInteger inventoryClientId;

	@Column(name="INVENTORY_PROCESS_ID")
	private BigInteger inventoryProcessId;

	@Column(name="REC_ADD_DT")
	private Timestamp recAddDt;

	@Column(name="REC_UPDATE_DT")
	private Timestamp recUpdateDt;

	private String status;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	public ClientProcessMap() {
	}

	public String getAutogenClientProcessMapId() {
		return this.autogenClientProcessMapId;
	}

	public void setAutogenClientProcessMapId(String autogenClientProcessMapId) {
		this.autogenClientProcessMapId = autogenClientProcessMapId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public BigInteger getInventoryClientId() {
		return this.inventoryClientId;
	}

	public void setInventoryClientId(BigInteger inventoryClientId) {
		this.inventoryClientId = inventoryClientId;
	}

	public BigInteger getInventoryProcessId() {
		return this.inventoryProcessId;
	}

	public void setInventoryProcessId(BigInteger inventoryProcessId) {
		this.inventoryProcessId = inventoryProcessId;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}