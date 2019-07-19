package com.ison.app.message.request;

import java.math.BigInteger;
import java.util.List;

public class ClientCreateRequest {
	
	private BigInteger inventoryClientId;
	private String inventoryClientName;
	private String mobileNumber;
	private String email;
	private List<ClientRegionRequest> clientRegions;
	public BigInteger getInventoryClientId() {
		return inventoryClientId;
	}
	public void setInventoryClientId(BigInteger inventoryClientId) {
		this.inventoryClientId = inventoryClientId;
	}
	public String getInventoryClientName() {
		return inventoryClientName;
	}
	public void setInventoryClientName(String inventoryClientName) {
		this.inventoryClientName = inventoryClientName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<ClientRegionRequest> getClientRegions() {
		return clientRegions;
	}
	public void setClientRegions(List<ClientRegionRequest> clientRegions) {
		this.clientRegions = clientRegions;
	}

	

}
