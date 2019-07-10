package com.ison.app.message.request;

import java.math.BigInteger;

import javax.validation.constraints.NotBlank;

public class InventoryUpdateRequest {

	@NotBlank
	private BigInteger id;
	
	private String inventoryType;
	
	private String name;
	
    @NotBlank
	private String status;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
