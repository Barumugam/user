package com.ison.app.message.request;

import java.math.BigInteger;

public class InventoryMappingRequest {

	private BigInteger id;

	private BigInteger inventoryCenterId;

	private BigInteger inventoryClientId;

	private BigInteger inventoryProcessId;

	private BigInteger inventoryRegionId;
	
	public String inventoryRegionName;
	
	public String inventoryCenterName;
	
	public String inventoryClientName;
	
	public String inventoryProcessName;
	
	private String status;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getInventoryRegionName() {
		return inventoryRegionName;
	}

	public void setInventoryRegionName(String inventoryRegionName) {
		this.inventoryRegionName = inventoryRegionName;
	}

	public String getInventoryCenterName() {
		return inventoryCenterName;
	}

	public void setInventoryCenterName(String inventoryCenterName) {
		this.inventoryCenterName = inventoryCenterName;
	}

	public String getInventoryClientName() {
		return inventoryClientName;
	}

	public void setInventoryClientName(String inventoryClientName) {
		this.inventoryClientName = inventoryClientName;
	}

	public String getInventoryProcessName() {
		return inventoryProcessName;
	}

	public void setInventoryProcessName(String inventoryProcessName) {
		this.inventoryProcessName = inventoryProcessName;
	}
	
	
}
