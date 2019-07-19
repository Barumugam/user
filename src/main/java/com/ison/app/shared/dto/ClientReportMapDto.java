package com.ison.app.shared.dto;

import java.math.BigInteger;
import java.sql.Timestamp;

public class ClientReportMapDto {

	public BigInteger autogenClientReportMapId;
	public BigInteger autogenContactDetailsId;
	public BigInteger autogenFrequencyMasterId;
	public BigInteger autogenReportMasterId;
	public String createdBy;
	public String frequencyName;
	public BigInteger inventoryProcessId;
	public String inventoryProcessName;
	public Timestamp recAddDt;
	public Timestamp recUpdateDt;
	public String reportName;
	public String status;
	public String updatedBy;
	public BigInteger getAutogenClientReportMapId() {
		return autogenClientReportMapId;
	}
	public void setAutogenClientReportMapId(BigInteger autogenClientReportMapId) {
		this.autogenClientReportMapId = autogenClientReportMapId;
	}
	public BigInteger getAutogenContactDetailsId() {
		return autogenContactDetailsId;
	}
	public void setAutogenContactDetailsId(BigInteger autogenContactDetailsId) {
		this.autogenContactDetailsId = autogenContactDetailsId;
	}
	public BigInteger getAutogenFrequencyMasterId() {
		return autogenFrequencyMasterId;
	}
	public void setAutogenFrequencyMasterId(BigInteger autogenFrequencyMasterId) {
		this.autogenFrequencyMasterId = autogenFrequencyMasterId;
	}
	public BigInteger getAutogenReportMasterId() {
		return autogenReportMasterId;
	}
	public void setAutogenReportMasterId(BigInteger autogenReportMasterId) {
		this.autogenReportMasterId = autogenReportMasterId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getFrequencyName() {
		return frequencyName;
	}
	public void setFrequencyName(String frequencyName) {
		this.frequencyName = frequencyName;
	}
	public BigInteger getInventoryProcessId() {
		return inventoryProcessId;
	}
	public void setInventoryProcessId(BigInteger inventoryProcessId) {
		this.inventoryProcessId = inventoryProcessId;
	}
	public String getInventoryProcessName() {
		return inventoryProcessName;
	}
	public void setInventoryProcessName(String inventoryProcessName) {
		this.inventoryProcessName = inventoryProcessName;
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
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	
}
