package com.ison.app.message.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class InventoryRequest {
	
	private String id;
	
	@NotBlank
    @Size(min = 3)
	private String inventoryType;
	
    @Size(min = 3)
	private String name;
	
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
