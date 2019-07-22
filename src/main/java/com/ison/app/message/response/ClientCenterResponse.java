package com.ison.app.message.response;

import java.math.BigInteger;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ClientCenterResponse {
	
	private BigInteger clientDetailsId;
	private BigInteger inventoryCenterId;
	private String inventoryCenterName;
	private MultipartFile file;
	private byte[] logo;
	private List<ContactDetails>  contactDetails;
	private List<ProcessResponse> processes;
	
	
	public BigInteger getClientDetailsId() {
		return clientDetailsId;
	}
	public void setClientDetailsId(BigInteger clientDetailsId) {
		this.clientDetailsId = clientDetailsId;
	}
	public BigInteger getInventoryCenterId() {
		return inventoryCenterId;
	}
	public void setInventoryCenterId(BigInteger inventoryCenterId) {
		this.inventoryCenterId = inventoryCenterId;
	}
	public String getInventoryCenterName() {
		return inventoryCenterName;
	}
	public void setInventoryCenterName(String inventoryCenterName) {
		this.inventoryCenterName = inventoryCenterName;
	}
	public byte[] getLogo() {
		return logo;
	}
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}
	public List<ContactDetails> getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(List<ContactDetails> contactDetails) {
		this.contactDetails = contactDetails;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public List<ProcessResponse> getProcesses() {
		return processes;
	}
	public void setProcesses(List<ProcessResponse> processes) {
		this.processes = processes;
	}

}
