package com.ison.app.services;

import com.ison.app.shared.dto.InventoryDto;

public interface InventoryService {

	public InventoryDto cretae(InventoryDto inventoryDto) throws Exception;
	
	public InventoryDto update(InventoryDto inventoryDto) throws Exception;
	
	public InventoryDto inventoryDropdown(InventoryDto inventoryDto) throws Exception;
	
	public InventoryDto inventoryDropdownMappingList(InventoryDto inventoryDto) throws Exception;
	
	public InventoryDto cretaeInventoryMapping(InventoryDto inventoryDto) throws Exception;
	
	public boolean checkInvenMapAlreadyExists(InventoryDto inventoryDto) throws Exception;
	
	public boolean checkInvenMasterAlreadyExists(InventoryDto inventoryDto) throws Exception; 

}
