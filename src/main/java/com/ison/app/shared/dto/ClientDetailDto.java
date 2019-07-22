package com.ison.app.shared.dto;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

public class ClientDetailDto {
	
	public BigInteger autogenClientDetailsId;
	public String createdBy;
	public String email;
	public BigInteger inventoryCenterId;
	public String inventoryCenterName;
	public BigInteger inventoryClientId;
	public String inventoryClientName;
	public BigInteger inventoryRegionId;
	public String inventoryRegionName;
	public byte[] logo;
	public String mobileNumber;
	/*public Timestamp recAddDt;
	public Timestamp recUpdateDt;
	*/
	public String updatedBy;
	public String status;
	//bi-directional many-to-one association to ContactDetail
	public List<ContactDetailDto> contactDetails;
	public boolean flag;
	
	public Object[] resultObj;
	
	public List<Object[]> resultObjList;
	
	public BigInteger getAutogenClientDetailsId() {
		return autogenClientDetailsId;
	}
	public void setAutogenClientDetailsId(BigInteger autogenClientDetailsId) {
		this.autogenClientDetailsId = autogenClientDetailsId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public BigInteger getInventoryCenterId() {
		return inventoryCenterId;
	}
	public void setInventoryCenterId(BigInteger inventoryCenterId) {
		this.inventoryCenterId = inventoryCenterId;
	}
	public String getInventoryCenterName() {
		return inventoryCenterName;
	}
	public void setInventoryCenterName(String inventoryCenterName) {
		this.inventoryCenterName = inventoryCenterName;
	}
	public BigInteger getInventoryClientId() {
		return inventoryClientId;
	}
	public void setInventoryClientId(BigInteger inventoryClientId) {
		this.inventoryClientId = inventoryClientId;
	}
	public String getInventoryClientName() {
		return inventoryClientName;
	}
	public void setInventoryClientName(String inventoryClientName) {
		this.inventoryClientName = inventoryClientName;
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
	public byte[] getLogo() {
		return logo;
	}
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	/*public Timestamp getRecAddDt() {
		return recAddDt;
	}
	public void setRecAddDt(Timestamp recAddDt) {
		this.recAddDt = recAddDt;
	}
	public Timestamp getRecUpdateDt() {
		return recUpdateDt;
	}
	public void setRecUpdateDt(Timestamp recUpdateDt) {
		this.recUpdateDt = recUpdateDt;
	}
	*/
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ContactDetailDto> getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(List<ContactDetailDto> contactDetails) {
		this.contactDetails = contactDetails;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public Object[] getResultObj() {
		return resultObj;
	}
	public void setResultObj(Object[] resultObj) {
		this.resultObj = resultObj;
	}
	public List<Object[]> getResultObjList() {
		return resultObjList;
	}
	public void setResultObjList(List<Object[]> resultObjList) {
		this.resultObjList = resultObjList;
	}
	
	
	
}
