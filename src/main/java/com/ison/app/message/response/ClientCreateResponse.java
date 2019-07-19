package com.ison.app.message.response;

import java.math.BigInteger;
import java.util.List;

public class ClientCreateResponse {
	
	private BigInteger inventoryClientId;
	private String inventoryClientName;
	private String mobileNumber;
	private String email;
	private List<ClientRegionResponse> clientRegions;
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
	public List<ClientRegionResponse> getClientRegions() {
		return clientRegions;
	}
	public void setClientRegions(List<ClientRegionResponse> clientRegions) {
		this.clientRegions = clientRegions;
	}

	

}
