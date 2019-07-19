package com.ison.app.shared.dto;

import java.math.BigInteger;
import java.util.List;

import com.ison.app.model.KeyValueObject;

public class CenterKVDto {
	
	private BigInteger id;
	private String label;
	private String status;
	List<KeyValueObject> process;
	
	public CenterKVDto(BigInteger id, String label, String status, List<KeyValueObject> process) {
		this.id = id;
		this.label = label;
		this.status = status;
		this.process = process;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<KeyValueObject> getProcess() {
		return process;
	}

	public void setProcess(List<KeyValueObject> process) {
		this.process = process;
	}
	
	

}
