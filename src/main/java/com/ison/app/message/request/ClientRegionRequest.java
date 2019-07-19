package com.ison.app.message.request;

import java.math.BigInteger;
import java.util.List;

public class ClientRegionRequest {
	
	private BigInteger inventoryRegionId;
	private String inventoryRegionName;
	private List<ClientCenterRequest> clientCenters;
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
	public List<ClientCenterRequest> getClientCenters() {
		return clientCenters;
	}
	public void setClientCenters(List<ClientCenterRequest> clientCenters) {
		this.clientCenters = clientCenters;
	} 

	
}
