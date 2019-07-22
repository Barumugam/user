package com.ison.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ison.app.constants.StatusCodeConstants;
import com.ison.app.message.request.ClientCenterRequest;
import com.ison.app.message.request.ClientCreateRequest;
import com.ison.app.message.request.ClientRegionRequest;
import com.ison.app.message.request.ClientReportMapCreateRequest;
import com.ison.app.message.request.ContactDetails;
import com.ison.app.message.response.ClientCreateResponse;
import com.ison.app.message.response.GenericResponse;
import com.ison.app.services.ClientService;
import com.ison.app.shared.dto.ClientDetailDto;
import com.ison.app.shared.dto.ClientReportMapDto;
import com.ison.app.shared.dto.ContactDetailDto;
import com.ison.app.util.CommonUtil;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	ClientService clientService;
	
	@PostMapping("/create")
	public ResponseEntity<GenericResponse> create(@Valid @RequestBody ClientCreateRequest clientCreateRequest) throws Exception {

		GenericResponse genericResponse = new GenericResponse();
		if(clientCreateRequest != null) {
			List<ClientDetailDto> clientDetailDtoList = new ArrayList<>(); 
			for (ClientRegionRequest clientRegionRequest : clientCreateRequest.getClientRegions()) {
				for (ClientCenterRequest clientCenterRequest : clientRegionRequest.getClientCenters()) {
					ClientDetailDto clientDetailDto = new ClientDetailDto();
					CommonUtil.copyProperties(clientCreateRequest, clientDetailDto);
					CommonUtil.copyProperties(clientRegionRequest, clientDetailDto);
					CommonUtil.copyProperties(clientCenterRequest, clientDetailDto);
					if(clientCenterRequest.getClientDetailsId() != null) {
						clientDetailDto.setAutogenClientDetailsId(clientCenterRequest.getClientDetailsId());
					}
					List<ContactDetailDto> contactDetailDtos = new ArrayList<>();
					for (ContactDetails contactDetails : clientCenterRequest.getContactDetails()) {
						ContactDetailDto contactDetailDto = new ContactDetailDto();
						CommonUtil.copyProperties(contactDetails, contactDetailDto);
						
						if(contactDetails.getContactDetailsId() != null) {
							contactDetailDto.setAutogenContactDetailsId(contactDetails.getContactDetailsId());	
						}
						
						List<ClientReportMapDto> clientReportMapsList = new ArrayList<>();
						for (ClientReportMapCreateRequest clientReportMapCreateRequest : contactDetails.getClientReportMaps()) {
							ClientReportMapDto  clientReportMap = new ClientReportMapDto();
							CommonUtil.copyProperties(clientReportMapCreateRequest, clientReportMap);	
							if(clientReportMapCreateRequest.getClientReportMapId() != null) {
								clientReportMap.setAutogenClientReportMapId(clientReportMapCreateRequest.getClientReportMapId());
							}
							clientReportMapsList.add(clientReportMap);
						}
						contactDetailDto.setClientReportMaps(clientReportMapsList);
						contactDetailDtos.add(contactDetailDto);
					}
					clientDetailDto.setContactDetails(contactDetailDtos);
					clientDetailDtoList.add(clientDetailDto);
				}
			}
			
			if(clientDetailDtoList != null && !clientDetailDtoList.isEmpty()) {
			/*ClientDetailDto clientDetailDto = clientService.checkClientDetailsAlreadyExists(clientDetailDtoList.get(0));
			if(clientDetailDto.flag && clientDetailDtoList.get(0).autogenClientDetailsId !=null) {
			    	genericResponse.setStatus(StatusCodeConstants.EXIST);
			    	genericResponse.setError("Failure");
			    	genericResponse.setMessage("Failure -> Client Details already configured!");
			    	return ResponseEntity.ok().body(new GenericResponse(genericResponse));
			}*/
			
				ClientDetailDto clientDetailDto = clientService.create(clientDetailDtoList);
			if(clientDetailDto != null && clientDetailDto.getResultObj().length > 0) {
				Object[] result = clientDetailDto.getResultObj();
				boolean flag = (boolean) result[0];
				if(flag) {
				    	genericResponse.setStatus(StatusCodeConstants.CREATE);
				    	genericResponse.setError("Success");
				    	genericResponse.setMessage("Success -> Client Details Configured successfully!");
				} else {
					genericResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			    	genericResponse.setError("Failure");
			    	genericResponse.setMessage("Success -> Client Details Configuration Failed!");
				}
		        return ResponseEntity.ok().body(new GenericResponse(genericResponse));
			}
			}
		} else {
			genericResponse.setStatus(HttpStatus.BAD_REQUEST.value());
	    	genericResponse.setError("Failure");
	    	genericResponse.setMessage("Success -> Client Detail Request fields missing!");
		}
		
		return ResponseEntity.ok().body(new GenericResponse(genericResponse));
	}
	
	@PostMapping("/showall")
	public ResponseEntity<GenericResponse> showallClients()
			throws Exception {
		GenericResponse genericResponse = new GenericResponse();
		ClientDetailDto clientDetailDto = clientService.showAllClients();
		if (clientDetailDto != null && clientDetailDto.getResultObj().length > 0) {
			Object[] result = clientDetailDto.getResultObj();
			List<ClientCreateResponse> clientCreateResponseList = (List<ClientCreateResponse>) result[0];
			if (clientCreateResponseList != null && !clientCreateResponseList.isEmpty()) {
				genericResponse.setValue(clientCreateResponseList);
				genericResponse.setStatus(StatusCodeConstants.SELECT);
				genericResponse.setError("Success");
				genericResponse.setMessage("Success -> clients detail selected!");
			} else {
				genericResponse.setStatus(StatusCodeConstants.DATA_NOT_FOUND);
				genericResponse.setError("Failure");
				genericResponse.setMessage("Failure -> Clients Detail not found!");
			}

		} else {
			genericResponse.setStatus(StatusCodeConstants.DATA_NOT_FOUND);
			genericResponse.setError("Failure");
			genericResponse.setMessage("Failure -> Clients Detail not found!");
		}
		return ResponseEntity.ok().body(new GenericResponse(genericResponse));
	}
	
	//@PostMapping("/client")
	public ResponseEntity<GenericResponse> getClientDetails()
			throws Exception {
		GenericResponse genericResponse = new GenericResponse();
		ClientDetailDto clientDetailDto = clientService.showAllClients();
		if (clientDetailDto != null && clientDetailDto.getResultObj().length > 0) {
			Object[] result = clientDetailDto.getResultObj();
			List<ClientCreateResponse> clientCreateResponseList = (List<ClientCreateResponse>) result[0];
			if (clientCreateResponseList != null && !clientCreateResponseList.isEmpty()) {
				genericResponse.setValue(clientCreateResponseList);
				genericResponse.setStatus(StatusCodeConstants.SELECT);
				genericResponse.setError("Success");
				genericResponse.setMessage("Success -> clients detail selected!");
			} else {
				genericResponse.setStatus(StatusCodeConstants.DATA_NOT_FOUND);
				genericResponse.setError("Failure");
				genericResponse.setMessage("Failure -> Clients Detail not found!");
			}

		} else {
			genericResponse.setStatus(StatusCodeConstants.DATA_NOT_FOUND);
			genericResponse.setError("Failure");
			genericResponse.setMessage("Failure -> Clients Detail not found!");
		}
		return ResponseEntity.ok().body(new GenericResponse(genericResponse));
	}

	

}
