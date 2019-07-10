package com.ison.app.message.response;

import java.math.BigInteger;

public class InventoryMappingResponse {


	private BigInteger id;

	private BigInteger inventoryCenterId;

	private BigInteger inventoryClientId;

	private BigInteger inventoryProcessId;

	private BigInteger inventoryRegionId;
	
	public InventoryMappingResponse() {}
	
	public InventoryMappingResponse(InventoryMappingResponse inventoryMappingResponse) {
		this.id = inventoryMappingResponse.id;
		this.inventoryRegionId = inventoryMappingResponse.inventoryRegionId;
		this.inventoryCenterId = inventoryMappingResponse.inventoryCenterId;
		this.inventoryClientId = inventoryMappingResponse.inventoryClientId;
		this.inventoryProcessId = inventoryMappingResponse.inventoryProcessId;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getInventoryCenterId() {
		return inventoryCenterId;
	}

	public void setInventoryCenterId(BigInteger inventoryCenterId) {
		this.inventoryCenterId = inventoryCenterId;
	}

	public BigInteger getInventoryClientId() {
		return inventoryClientId;
	}

	public void setInventoryClientId(BigInteger inventoryClientId) {
		this.inventoryClientId = inventoryClientId;
	}

	public BigInteger getInventoryProcessId() {
		return inventoryProcessId;
	}

	public void setInventoryProcessId(BigInteger inventoryProcessId) {
		this.inventoryProcessId = inventoryProcessId;
	}

	public BigInteger getInventoryRegionId() {
		return inventoryRegionId;
	}

	public void setInventoryRegionId(BigInteger inventoryRegionId) {
		this.inventoryRegionId = inventoryRegionId;
	}

	
}
