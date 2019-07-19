package com.ison.app.services.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ison.app.dao.ClientDAO;
import com.ison.app.dao.impl.UserDAOImpl;
import com.ison.app.message.response.ClientCenterResponse;
import com.ison.app.message.response.ClientCreateResponse;
import com.ison.app.message.response.ClientRegionResponse;
import com.ison.app.message.response.ClientReportMapCreateResponse;
import com.ison.app.message.response.ContactDetails;
import com.ison.app.services.ClientService;
import com.ison.app.shared.dto.ClientDetailDto;
import com.ison.app.shared.dto.ContactDetailDto;

@Service
public class ClientServiceImpl implements ClientService {
	
	private Logger logger = Logger.getLogger(ClientServiceImpl.class);
	
	@Autowired
	ClientDAO  clientDAO;

	@Override
	public ClientDetailDto create(List<ClientDetailDto> clientDetailDtoList) throws Exception {
		return clientDAO.create(clientDetailDtoList);
	}
	
	public ClientDetailDto showAllClients() throws Exception {

		ClientDetailDto clientDetailDto = null;
		List<Object[]> clientResult = null;
		List<Object[]> regionResult = null;
		List<Object[]> centerResult = null;
		List<Object[]> contactResult = null;
		List<Object[]> clientReportResult = null;
		StringBuilder sqlQry = null;
		Query qryObj = null;
		List<ClientCreateResponse> tempObjList = new ArrayList<>();
		try {
			clientDetailDto = new ClientDetailDto();
			clientDetailDto = clientDAO.getClientDetails();
			clientResult = clientDetailDto.getResultObjList();
			if(clientResult != null && !clientResult.isEmpty()) {
				for (Object[] clientObj : clientResult) {
					clientDetailDto = new ClientDetailDto();
					clientDetailDto.setInventoryClientId((BigInteger)clientObj[0]);
					clientDetailDto = clientDAO.getRegionDetails(clientDetailDto);
					regionResult = clientDetailDto.getResultObjList();
					List<ClientRegionResponse> clientRegionResponseList = new ArrayList<>();
					for (Object[] regionObj : regionResult) {
						clientDetailDto = new ClientDetailDto();
						clientDetailDto.setInventoryClientId((BigInteger)clientObj[0]);
						clientDetailDto.setInventoryRegionId((BigInteger)regionObj[0]);
						clientDetailDto = clientDAO.getCenterDetails(clientDetailDto);
						centerResult = clientDetailDto.getResultObjList();
						List<ClientCenterResponse> clientCenterList = new ArrayList<>();
						for (Object[] centerObj : centerResult) {
							clientDetailDto = new ClientDetailDto();
							clientDetailDto.setAutogenClientDetailsId((BigInteger)centerObj[0]);
							clientDetailDto = clientDAO.getContactDetails(clientDetailDto);
							contactResult = clientDetailDto.getResultObjList();
							List<ContactDetails> contactList = new ArrayList<>();
							for (Object[] contactObj : contactResult) {
								clientDetailDto = null;
								ContactDetailDto contactDetailDto = new ContactDetailDto();
								contactDetailDto.setAutogenContactDetailsId((BigInteger)contactObj[0]);
								clientDetailDto = clientDAO.getClientReportMapDetails(contactDetailDto);
								clientReportResult = clientDetailDto.getResultObjList();
								List<ClientReportMapCreateResponse> clientReportMapList = new ArrayList<>();
								for(Object[] clientReportObj:clientReportResult){
									ClientReportMapCreateResponse reportMapCreateResponse = new ClientReportMapCreateResponse();
									reportMapCreateResponse.setClientReportMapId((BigInteger)clientReportObj[0]);
									reportMapCreateResponse.setInventoryProcessName((String)clientReportObj[2]);
									reportMapCreateResponse.setInventoryProcessId((BigInteger)clientReportObj[1]);
									reportMapCreateResponse.setAutogenReportMasterId((BigInteger)clientReportObj[3]);
									reportMapCreateResponse.setReportName((String)clientReportObj[4]);
									reportMapCreateResponse.setAutogenFrequencyMasterId((BigInteger)clientReportObj[5]);
									reportMapCreateResponse.setFrequencyName((String)clientReportObj[6]);
									clientReportMapList.add(reportMapCreateResponse);
								}
								ContactDetails contactDetails = new ContactDetails();
								contactDetails.setContactDetailId((BigInteger)contactObj[0]);
								contactDetails.setPersonName((String)contactObj[1]);
								contactDetails.setEmail((String)contactObj[2]);
								contactDetails.setMobileNumber((String)contactObj[3]);
								contactDetails.setNotificationStatus((String)contactObj[4]);
								contactDetails.setClientReportMaps(clientReportMapList);
								contactList.add(contactDetails);
							}
							ClientCenterResponse clientCenterResponse = new ClientCenterResponse();
							clientCenterResponse.setContactDetailsId((BigInteger)centerObj[0]);
							clientCenterResponse.setInventoryCenterId((BigInteger)centerObj[1]);
							clientCenterResponse.setInventoryCenterName((String)centerObj[2]);
							clientCenterResponse.setContactDetails(contactList);
							clientCenterList.add(clientCenterResponse);
						
						}
						
						ClientRegionResponse clientRegionResponse = new ClientRegionResponse();
						clientRegionResponse.setClientCenters(clientCenterList);
						clientRegionResponse.setInventoryRegionId((BigInteger)regionObj[0]);
						clientRegionResponse.setInventoryRegionName((String)regionObj[1]);
						clientRegionResponseList.add(clientRegionResponse);
						
					}
					ClientCreateResponse clientCreateResponse = new ClientCreateResponse();
					clientCreateResponse.setClientRegions(clientRegionResponseList);
					clientCreateResponse.setInventoryClientId((BigInteger)clientObj[0]);
					clientCreateResponse.setInventoryClientName((String)clientObj[1]);
					//clientCreateResponse.setEmail((String)clientObj[2]);
					//clientCreateResponse.setMobileNumber((String)clientObj[3]);
					tempObjList.add(clientCreateResponse);
				}
				
			}
		} catch (Exception e) {
			logger.info("Exception :: showAllClients :: create() : " + e);
		}
		Object[] result = new Object[1];
		result[0] = tempObjList;
		clientDetailDto.setResultObj(result); 
		return clientDetailDto;
	
	}

}
