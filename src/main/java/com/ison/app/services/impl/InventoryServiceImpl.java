package com.ison.app.services.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ison.app.dao.InventoryDAO;
import com.ison.app.model.KeyValueObject;
import com.ison.app.services.InventoryService;
import com.ison.app.shared.dto.InventoryDto;
import com.ison.app.util.CommonUtil;

@Service
public class InventoryServiceImpl implements InventoryService{
	
	@Autowired
	InventoryDAO inventoryDAO;

	@Override
	public InventoryDto cretae(InventoryDto inventoryDto) throws Exception {
		return inventoryDAO.cretae(inventoryDto);
	}

	@Override
	public InventoryDto update(InventoryDto inventoryDto) throws Exception {
		return inventoryDAO.update(inventoryDto);
	}

	@Override
	public InventoryDto inventoryDropdown(InventoryDto inventoryDto) throws Exception {
		List<Object[]> result = inventoryDAO.inventoryDropdown(inventoryDto);
		if(result != null && !result.isEmpty()) {
			getMapResultObj(result, inventoryDto.getKeyValueObjectMap());
		}
		return inventoryDto;
	}

	@Override
	public InventoryDto inventoryDropdownMappingList(InventoryDto inventoryDto) throws Exception {
		List<Object[]> result = inventoryDAO.inventoryDropdownMappingList(inventoryDto);
		if(result != null && !result.isEmpty()) {
			getMapResultObj(result, inventoryDto.getKeyValueObjectMap());
		}
		return inventoryDto;
	}
	
	
	public void getMapResultObj(List<Object[]> resultObjList, List<KeyValueObject> keyValueObjectMap) {
		if (resultObjList != null && !resultObjList.isEmpty()) {
			for (Object[] obj : resultObjList) {
				keyValueObjectMap.add(new KeyValueObject(new BigInteger(CommonUtil.nullRemove(obj[0])), CommonUtil.nullRemove(obj[1]), CommonUtil.nullRemove(obj[2])));
			}
		}
	}

	@Override
	public InventoryDto cretaeInventoryMapping(InventoryDto inventoryDto) throws Exception {
		return inventoryDAO.cretaeInventoryMapping(inventoryDto);
	}

	@Override
	public boolean checkInvenMapAlreadyExists(InventoryDto inventoryDto) throws Exception {
		inventoryDto = inventoryDAO.checkInvenMapAlreadyExists(inventoryDto);
		return inventoryDto.isFlag();
	}

	@Override
	public boolean checkInvenMasterAlreadyExists(InventoryDto inventoryDto) throws Exception {
		inventoryDto = inventoryDAO.checkInvenMasterAlreadyExists(inventoryDto);
		return inventoryDto.isFlag();
	}

}
