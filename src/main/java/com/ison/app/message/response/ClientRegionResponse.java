package com.ison.app.message.response;

import java.math.BigInteger;
import java.util.List;

public class ClientRegionResponse {
	
	private BigInteger inventoryRegionId;
	private String inventoryRegionName;
	private List<ClientCenterResponse> clientCenters;
	public BigInteger getInventoryRegionId() {
		return inventoryRegionId;
	}
	public void setInventoryRegionId(BigInteger inventoryRegionId) {
		this.inventoryRegionId = inventoryRegionId;
	}
	public String getInventoryRegionName() {
		return inventoryRegionName;
	}
	public void setInventoryRegionName(String inventoryRegionName) {
		this.inventoryRegionName = inventoryRegionName;
	}
	public List<ClientCenterResponse> getClientCenters() {
		return clientCenters;
	}
	public void setClientCenters(List<ClientCenterResponse> clientCenters) {
		this.clientCenters = clientCenters;
	} 

	
}
