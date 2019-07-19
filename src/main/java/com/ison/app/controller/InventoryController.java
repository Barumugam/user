package com.ison.app.controller;

import java.math.BigInteger;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ison.app.message.request.GenericeRequest;
import com.ison.app.message.request.InventoryMappingRequest;
import com.ison.app.message.request.InventoryRequest;
import com.ison.app.message.request.InventoryUpdateRequest;
import com.ison.app.message.response.GenericResponse;
import com.ison.app.message.response.InventoryMappingResponse;
import com.ison.app.message.response.InventoryResponse;
import com.ison.app.services.InventoryService;
import com.ison.app.shared.dto.InventoryDto;
import com.ison.app.util.CommonUtil;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
	
	@Autowired
	InventoryService inventoryService;
	
	@PostMapping("/create")
    public ResponseEntity<GenericResponse> createInventory(@Valid @RequestBody InventoryRequest inventoryRequest) throws Exception {
		GenericResponse genericResponse = null;
		InventoryDto inventoryDto = new InventoryDto();
    	CommonUtil.copyProperties(inventoryRequest, inventoryDto);
    	if (inventoryService.checkInvenMasterAlreadyExists(inventoryDto)) {
			genericResponse = new GenericResponse();
			genericResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			genericResponse.setError("Failure");
			genericResponse.setMessage("Failure ->"+inventoryDto.getInventoryType()+" "+inventoryDto.getName()+" inventory already exists!");
			return new ResponseEntity<GenericResponse>(new GenericResponse(genericResponse), HttpStatus.OK);

		}
    	
    	inventoryDto = inventoryService.cretae(inventoryDto);
    	if(inventoryDto.getAutogenInventoryMasterId() != null) {
    		InventoryResponse inventoryResponse = new InventoryResponse();
    		CommonUtil.copyProperties(inventoryDto, inventoryResponse);
    		inventoryResponse.setId(new BigInteger(inventoryDto.getAutogenInventoryMasterId()));
    		genericResponse = new GenericResponse();
    		genericResponse.setStatus(1007);
    		genericResponse.setMessage("Inventory Created Successfully!.");
    		genericResponse.setError("Success");
    		genericResponse.setValue(inventoryResponse);
    	}
    	 return ResponseEntity.ok().body(new GenericResponse(genericResponse));
	}
	
	@PostMapping("/update")
    public ResponseEntity<GenericResponse> updateInventory(@Valid @RequestBody InventoryUpdateRequest inventoryRequest) throws Exception {
		GenericResponse genericResponse = null;
		InventoryDto inventoryDto = new InventoryDto();
    	CommonUtil.copyProperties(inventoryRequest, inventoryDto);
    	inventoryDto.setAutogenInventoryMasterId(String.valueOf(inventoryRequest.getId()));
    	inventoryDto = inventoryService.update(inventoryDto);
    	if(inventoryDto.flag) {
    		InventoryResponse inventoryResponse = new InventoryResponse();
    		CommonUtil.copyProperties(inventoryDto, inventoryResponse);
    		inventoryResponse.setId(new BigInteger(inventoryDto.getAutogenInventoryMasterId()));
    		genericResponse = new GenericResponse();
    		genericResponse.setStatus(1005);
    		genericResponse.setMessage("Inventory Update Successfully!.");
    		genericResponse.setError("Success");
    		genericResponse.setValue(inventoryResponse);
    	} else {
    		genericResponse = new GenericResponse();
    		genericResponse.setStatus(1001);
    		genericResponse.setMessage("Inventory not updated.Please contact admin team.");
    		genericResponse.setError("Failure");
    	}
    	 return ResponseEntity.ok().body(new GenericResponse(genericResponse));
	}
	
	@PostMapping("/dropdownlist")
    public ResponseEntity<GenericResponse> inventoryDropDownList(@Valid @NotBlank @RequestBody GenericeRequest  genericeRequest) throws Exception {
		GenericResponse genericResponse = null;
		InventoryDto inventoryDto = new InventoryDto();
		if(genericeRequest.getInventoryType() != null && !genericeRequest.getInventoryType().isEmpty()) {
    		genericResponse = new GenericResponse();
    		genericResponse.setStatus(400);
    		genericResponse.setMessage("inventoryType Field missing.!");
    		genericResponse.setError("Bad Request");
		}
    	inventoryDto.setInventoryType(genericeRequest.getInventoryType());
    	inventoryDto = inventoryService.inventoryDropdown(inventoryDto);
    	if(inventoryDto.getKeyValueObjectMap() != null && !inventoryDto.getKeyValueObjectMap().isEmpty()) {
    		genericResponse = new GenericResponse();
    		genericResponse.setStatus(1010);
    		genericResponse.setMessage(inventoryDto.getInventoryType().toUpperCase()+" selected Successfully!.");
    		genericResponse.setError("Success");
    		genericResponse.setValue(inventoryDto.getKeyValueObjectMap());
    	} else {
    		genericResponse = new GenericResponse();
    		genericResponse.setStatus(1001);
    		genericResponse.setMessage(inventoryDto.getInventoryType().toLowerCase() + " not found!");
    		genericResponse.setError("Failure");
    	}
    	 return ResponseEntity.ok().body(new GenericResponse(genericResponse));
	}

	@PostMapping("/inventorymapping/dropdownlist")
    public ResponseEntity<GenericResponse> inventoryMappingDropDownList(@Valid @RequestBody GenericeRequest  genericeRequest) throws Exception {
		GenericResponse genericResponse = null;
		if(genericeRequest.getId() != null && !genericeRequest.getId().isEmpty()) {
    		genericResponse = new GenericResponse();
    		genericResponse.setStatus(400);
    		genericResponse.setMessage("id Field missing.!");
    		genericResponse.setError("Bad Request");
		}
		if(genericeRequest.getInventoryType() != null && !genericeRequest.getInventoryType().isEmpty()) {
    		genericResponse = new GenericResponse();
    		genericResponse.setStatus(400);
    		genericResponse.setMessage("inventoryType Field missing.!");
    		genericResponse.setError("Bad Request");
		}
		InventoryDto inventoryDto = new InventoryDto();
    	CommonUtil.copyProperties(genericeRequest, inventoryDto);
    	inventoryDto = inventoryService.inventoryDropdownMappingList(inventoryDto);
    	if(inventoryDto.getKeyValueObjectMap() != null && !inventoryDto.getKeyValueObjectMap().isEmpty()) {
    		genericResponse = new GenericResponse();
    		genericResponse.setStatus(1010);
    		genericResponse.setMessage(inventoryDto.getInventoryType().toUpperCase()+" selected Successfully!.");
    		genericResponse.setError("Success");
    		genericResponse.setValue(inventoryDto.getKeyValueObjectMap());
    	} else {
    		genericResponse = new GenericResponse();
    		genericResponse.setStatus(1001);
    		genericResponse.setMessage(inventoryDto.getInventoryType().toLowerCase() + " not found!");
    		genericResponse.setError("Failure");
    	}
    	 return ResponseEntity.ok().body(new GenericResponse(genericResponse));
	}
	
	@PostMapping("/inventorymapping/create")
    public ResponseEntity<GenericResponse> createInventoryMapping(@Valid @RequestBody InventoryMappingRequest inventoryMappingRequest) throws Exception {
		GenericResponse genericResponse = null;
		InventoryDto inventoryDto = new InventoryDto();
    	CommonUtil.copyProperties(inventoryMappingRequest, inventoryDto);
		if (inventoryService.checkInvenMapAlreadyExists(inventoryDto)) {
			genericResponse = new GenericResponse();
			genericResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			genericResponse.setError("Failure");
			genericResponse.setMessage("Failure -> This inventory map already exists!");
			return new ResponseEntity<GenericResponse>(new GenericResponse(genericResponse), HttpStatus.OK);

		}
    	
    	inventoryDto = inventoryService.cretaeInventoryMapping(inventoryDto);
    	if(inventoryDto.getAutogenInventoryMappingId() != null) {
    		InventoryMappingResponse inventoryMappingResponse = new InventoryMappingResponse();
    		CommonUtil.copyProperties(inventoryDto, inventoryMappingResponse);
    		inventoryMappingResponse.setId(new BigInteger(inventoryDto.getAutogenInventoryMappingId()));
    		genericResponse = new GenericResponse();
    		genericResponse.setStatus(1007);
    		genericResponse.setMessage("Inventory Mapped Successfully!.");
    		genericResponse.setError("Success");
    		genericResponse.setValue(new InventoryMappingResponse(inventoryMappingResponse));
    	}
    	 return ResponseEntity.ok().body(new GenericResponse(genericResponse));
	}
	
	@PostMapping("/inventorymapping/client/showall")
    public ResponseEntity<GenericResponse> getClientInventoryMappingList() throws Exception {
		GenericResponse genericResponse = null;
		InventoryDto inventoryDto = new InventoryDto();
    	inventoryDto = inventoryService.getMappedInventoryList();
    	if(inventoryDto.getResultList() != null && !inventoryDto.getResultList().isEmpty()) {
    		genericResponse = new GenericResponse();
    		genericResponse.setStatus(1010);
    		genericResponse.setMessage("show All Client!");
    		genericResponse.setError("Success");
    		genericResponse.setValue(inventoryDto.getResultList());
    	} else {
    		genericResponse = new GenericResponse();
    		genericResponse.setStatus(1001);
    		genericResponse.setMessage("Clients not found!");
    		genericResponse.setError("Failure");
    	}
    	 return ResponseEntity.ok().body(new GenericResponse(genericResponse));
	}
	

}
