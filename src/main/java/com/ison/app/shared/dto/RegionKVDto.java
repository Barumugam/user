package com.ison.app.shared.dto;

import java.math.BigInteger;
import java.util.List;

public class RegionKVDto {
	
	private BigInteger id;
	private String label;
	private String status;
	List<CenterKVDto> centers;
	
	public RegionKVDto() {}
	
	public RegionKVDto(BigInteger id, String label, String status, List<CenterKVDto> centers) {
		this.id = id;
		this.label = label;
		this.status = status;
		this.centers = centers;
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
	public List<CenterKVDto> getCenters() {
		return centers;
	}
	public void setCenters(List<CenterKVDto> centers) {
		this.centers = centers;
	}

	
}
