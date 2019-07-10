package com.ison.app.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the report_master database table.
 * 
 */
@Entity
@Table(name="report_master")
@NamedQuery(name="ReportMaster.findAll", query="SELECT r FROM ReportMaster r")
public class ReportMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="AUTOGEN_REPORT_MASTER_ID")
	private String autogenReportMasterId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="REC_ADD_DT")
	private Timestamp recAddDt;

	@Column(name="REC_UPDATE_DT")
	private Timestamp recUpdateDt;

	@Column(name="REPORT_NAME")
	private String reportName;

	private String status;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	public ReportMaster() {
	}

	public String getAutogenReportMasterId() {
		return this.autogenReportMasterId;
	}

	public void setAutogenReportMasterId(String autogenReportMasterId) {
		this.autogenReportMasterId = autogenReportMasterId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

}