package com.ison.app.shared.dto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ison.app.model.KeyValueObject;

public class InventoryDto {
	
	public String autogenInventoryMasterId;

	public String createdBy;

	public String inventoryType;

	public String name;

	public Date recAddDt;

	public Date recUpdateDt;

	public String status;

	public String updatedBy;
	
	public boolean flag;
	
	public List<KeyValueObject> keyValueObjectMap = new ArrayList<>();
	
	public BigInteger inventoryCategoryId;
	
	public BigInteger inventoryCenterId;
	
	public BigInteger inventoryProcessId;
	
	public BigInteger inventoryRegionId;
	
	public BigInteger inventoryClientId;
	
	private String autogenInventoryMappingId;
	
	public InventoryDto() {}

	public InventoryDto(InventoryDto inventoryDto) {
		
	}

	public String getAutogenInventoryMasterId() {
		return autogenInventoryMasterId;
	}

	public void setAutogenInventoryMasterId(String autogenInventoryMasterId) {
		this.autogenInventoryMasterId = autogenInventoryMasterId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRecAddDt() {
		return recAddDt;
	}

	public void setRecAddDt(Date recAddDt) {
		this.recAddDt = recAddDt;
	}

	public Date getRecUpdateDt() {
		return recUpdateDt;
	}

	public void setRecUpdateDt(Date recUpdateDt) {
		this.recUpdateDt = recUpdateDt;
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

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public List<KeyValueObject> getKeyValueObjectMap() {
		return keyValueObjectMap;
	}

	public void setKeyValueObjectMap(List<KeyValueObject> keyValueObjectMap) {
		this.keyValueObjectMap = keyValueObjectMap;
	}

	public String getAutogenInventoryMappingId() {
		return autogenInventoryMappingId;
	}

	public void setAutogenInventoryMappingId(String autogenInventoryMappingId) {
		this.autogenInventoryMappingId = autogenInventoryMappingId;
	}

	public BigInteger getInventoryCategoryId() {
		return inventoryCategoryId;
	}

	public void setInventoryCategoryId(BigInteger inventoryCategoryId) {
		this.inventoryCategoryId = inventoryCategoryId;
	}

	public BigInteger getInventoryCenterId() {
		return inventoryCenterId;
	}

	public void setInventoryCenterId(BigInteger inventoryCenterId) {
		this.inventoryCenterId = inventoryCenterId;
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

	public BigInteger getInventoryClientId() {
		return inventoryClientId;
	}

	public void setInventoryClientId(BigInteger inventoryClientId) {
		this.inventoryClientId = inventoryClientId;
	}

}
