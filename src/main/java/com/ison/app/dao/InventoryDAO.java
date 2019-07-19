package com.ison.app.dao;

import java.util.List;

import com.ison.app.model.InventoryMaster;
import com.ison.app.shared.dto.InventoryDto;

public interface InventoryDAO {
	
	public InventoryDto cretae(InventoryDto inventoryDto) throws Exception;
	
	public InventoryDto update(InventoryDto inventoryDto) throws Exception;
	
	public List<Object[]> inventoryDropdown(InventoryDto inventoryDto) throws Exception;
	
	public List<Object[]>  inventoryDropdownMappingList(InventoryDto inventoryDto) throws Exception; 
	
	public InventoryDto cretaeInventoryMapping(InventoryDto inventoryDto) throws Exception;
	
	public InventoryDto checkInvenMapAlreadyExists(InventoryDto inventoryDto) throws Exception;
	
	public InventoryDto checkInvenMasterAlreadyExists(InventoryDto inventoryDto) throws Exception; 
	
	public InventoryMaster findInventory(InventoryDto inventoryDto) throws Exception;
	
	public InventoryDto getMappedInventoryList() throws Exception; 

}
