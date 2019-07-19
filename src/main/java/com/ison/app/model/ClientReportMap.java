package com.ison.app.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.sql.Timestamp;
import java.util.Date;
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
	private BigInteger autogenClientReportMapId;

	@Column(name="AUTOGEN_CONTACT_DETAILS_ID")
	private BigInteger autogenContactDetailsId;

	@Column(name="AUTOGEN_FREQUENCY_MASTER_ID")
	private BigInteger autogenFrequencyMasterId;

	@Column(name="AUTOGEN_REPORT_MASTER_ID")
	private BigInteger autogenReportMasterId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="FREQUENCY_NAME")
	private String frequencyName;

	@Column(name="INVENTORY_PROCESS_ID")
	private BigInteger inventoryProcessId;

	@Column(name="INVENTORY_PROCESS_NAME")
	private String inventoryProcessName;

	@Generated(GenerationTime.INSERT)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="REC_ADD_DT")
	private Date recAddDt;

	@Generated(GenerationTime.ALWAYS)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="REC_UPDATE_DT")
	private Date recUpdateDt;

	@Column(name="REPORT_NAME")
	private String reportName;

	private String status;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	//bi-directional many-to-one association to ContactDetail
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="AUTOGEN_CLIENT_REPORT_MAP_ID", referencedColumnName="AUTOGEN_CONTACT_DETAILS_ID", insertable=false, updatable=false)
	private ContactDetail contactDetail;

	public ClientReportMap() {
	}

	public BigInteger getAutogenClientReportMapId() {
		return this.autogenClientReportMapId;
	}

	public void setAutogenClientReportMapId(BigInteger autogenClientReportMapId) {
		this.autogenClientReportMapId = autogenClientReportMapId;
	}

	public BigInteger getAutogenContactDetailsId() {
		return this.autogenContactDetailsId;
	}

	public void setAutogenContactDetailsId(BigInteger autogenContactDetailsId) {
		this.autogenContactDetailsId = autogenContactDetailsId;
	}

	public BigInteger getAutogenFrequencyMasterId() {
		return this.autogenFrequencyMasterId;
	}

	public void setAutogenFrequencyMasterId(BigInteger autogenFrequencyMasterId) {
		this.autogenFrequencyMasterId = autogenFrequencyMasterId;
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

	public String getFrequencyName() {
		return this.frequencyName;
	}

	public void setFrequencyName(String frequencyName) {
		this.frequencyName = frequencyName;
	}

	public BigInteger getInventoryProcessId() {
		return this.inventoryProcessId;
	}

	public void setInventoryProcessId(BigInteger inventoryProcessId) {
		this.inventoryProcessId = inventoryProcessId;
	}

	public String getInventoryProcessName() {
		return this.inventoryProcessName;
	}

	public void setInventoryProcessName(String inventoryProcessName) {
		this.inventoryProcessName = inventoryProcessName;
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

	public String getReportName() {
		return this.reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
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

	public ContactDetail getContactDetail() {
		return this.contactDetail;
	}

	public void setContactDetail(ContactDetail contactDetail) {
		this.contactDetail = contactDetail;
	}

}