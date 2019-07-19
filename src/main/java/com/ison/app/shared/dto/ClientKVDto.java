package com.ison.app.shared.dto;

import java.math.BigInteger;
import java.util.List;

public class ClientKVDto {
	
	private BigInteger id;
	private String label;
	private String status;
	List<RegionKVDto> regions;
	
	public ClientKVDto() {}

	public ClientKVDto(BigInteger id, String label, String status, List<RegionKVDto> regions) {
		this.id = id;
		this.label = label;
		this.status = status;
		this.regions = regions;
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
	public List<RegionKVDto> getRegions() {
		return regions;
	}
	public void setRegions(List<RegionKVDto> regions) {
		this.regions = regions;
	}
	
}
