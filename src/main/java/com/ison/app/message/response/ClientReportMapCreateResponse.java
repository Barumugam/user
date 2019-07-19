package com.ison.app.message.response;

import java.math.BigInteger;

public class ClientReportMapCreateResponse {
	
	public BigInteger clientReportMapId;
	public BigInteger autogenFrequencyMasterId;
	public BigInteger autogenReportMasterId;
	public String frequencyName;
	public BigInteger inventoryProcessId;
	public String inventoryProcessName;
	public String reportName;
	public String status;
	
	
	public BigInteger getClientReportMapId() {
		return clientReportMapId;
	}
	public void setClientReportMapId(BigInteger clientReportMapId) {
		this.clientReportMapId = clientReportMapId;
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
}
