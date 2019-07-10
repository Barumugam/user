package com.ison.app.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the client_report_map database table.
 * 
 */
@Entity
@Table(name="client_report_map")
@NamedQuery(name="ClientReportMap.findAll", query="SELECT c FROM ClientReportMap c")
public class ClientReportMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="AUTOGEN_CLIENT_REPORT_MAP_ID")
	private String autogenClientReportMapId;

	@Column(name="AUTOGEN_REPORT_MASTER_ID")
	private BigInteger autogenReportMasterId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="INVENTORY_CLIENT_ID")
	private BigInteger inventoryClientId;

	@Column(name="REC_ADD_DT")
	private Timestamp recAddDt;

	@Column(name="REC_UPDATE_DT")
	private Timestamp recUpdateDt;

	private String status;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	public ClientReportMap() {
	}

	public String getAutogenClientReportMapId() {
		return this.autogenClientReportMapId;
	}

	public void setAutogenClientReportMapId(String autogenClientReportMapId) {
		this.autogenClientReportMapId = autogenClientReportMapId;
	}

	public BigInteger getAutogenReportMasterId() {
		return this.autogenReportMasterId;
	}

	public void setAutogenReportMasterId(BigInteger autogenReportMasterId) {
		this.autogenReportMasterId = autogenReportMasterId;
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