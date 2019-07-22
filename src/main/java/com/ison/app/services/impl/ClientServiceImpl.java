package com.ison.app.services.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ison.app.dao.ClientDAO;
import com.ison.app.message.response.ClientCenterResponse;
import com.ison.app.message.response.ClientCreateResponse;
import com.ison.app.message.response.ClientRegionResponse;
import com.ison.app.message.response.ClientReportMapCreateResponse;
import com.ison.app.message.response.ContactDetails;
import com.ison.app.message.response.ProcessResponse;
import com.ison.app.services.ClientService;
import com.ison.app.shared.dto.ClientDetailDto;
import com.ison.app.shared.dto.ContactDetailDto;
import com.ison.app.util.CommonUtil;

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
		List<Object[]> processResult = null;
		List<Object[]> clientReportResult = null;
		List<ClientCreateResponse> tempObjList = new ArrayList<>();
		try {
			clientDetailDto = new ClientDetailDto();
			clientDetailDto = clientDAO.getClientDetails();
			clientResult = clientDetailDto.getResultObjList();
			if (clientResult != null && !clientResult.isEmpty()) {
				for (Object[] clientObj : clientResult) {
					List<ClientRegionResponse> clientRegionResponseList = new ArrayList<>();
					if (clientObj[0] != null && "Y".equalsIgnoreCase(clientObj[0].toString())) {
						clientDetailDto = new ClientDetailDto();
						clientDetailDto.setInventoryClientId((BigInteger)clientObj[1]);
						clientDetailDto = clientDAO.getRegionDetails(clientDetailDto);
						regionResult = clientDetailDto.getResultObjList();
						for (Object[] regionObj : regionResult) {
							List<ClientCenterResponse> clientCenterList = new ArrayList<>();
							if (regionObj[0] != null && "Y".equalsIgnoreCase(regionObj[0].toString())) {
								clientDetailDto = new ClientDetailDto();
								clientDetailDto.setInventoryClientId((BigInteger)clientObj[1]);
								clientDetailDto.setInventoryRegionId((BigInteger)regionObj[1]);
								clientDetailDto = clientDAO.getCenterDetails(clientDetailDto);
								centerResult = clientDetailDto.getResultObjList();
								for (Object[] centerObj : centerResult) {
									List<ContactDetails> contactList = new ArrayList<>();
									if (centerObj[0] != null && "Y".equalsIgnoreCase(centerObj[0].toString())) {
										clientDetailDto = new ClientDetailDto();
										clientDetailDto.setAutogenClientDetailsId((BigInteger)centerObj[1]);
										clientDetailDto = clientDAO.getContactDetails(clientDetailDto);
										contactResult = clientDetailDto.getResultObjList();
										for (Object[] contactObj : contactResult) {
											List<ClientReportMapCreateResponse> clientReportMapList = new ArrayList<>();
												clientDetailDto = null;
												ContactDetailDto contactDetailDto = new ContactDetailDto();
												contactDetailDto.setAutogenContactDetailsId((BigInteger)contactObj[0]);
												clientDetailDto = clientDAO.getClientReportMapDetails(contactDetailDto);
												clientReportResult = clientDetailDto.getResultObjList();
												for (Object[] clientReportObj : clientReportResult) {
													ClientReportMapCreateResponse reportMapCreateResponse = new ClientReportMapCreateResponse();
													reportMapCreateResponse
															.setClientReportMapId((BigInteger) clientReportObj[0]);

													reportMapCreateResponse
															.setInventoryProcessId((BigInteger) clientReportObj[1]);
													reportMapCreateResponse
															.setInventoryProcessName((String) clientReportObj[2]);
													reportMapCreateResponse
															.setAutogenReportMasterId((BigInteger) clientReportObj[3]);
													reportMapCreateResponse.setReportName((String) clientReportObj[4]);
													reportMapCreateResponse.setAutogenFrequencyMasterId(
															(BigInteger) clientReportObj[5]);
													reportMapCreateResponse
															.setFrequencyName((String) clientReportObj[6]);
													clientReportMapList.add(reportMapCreateResponse);
												}
											ContactDetails contactDetails = new ContactDetails();
											if (CommonUtil.nullCheckBigInt(contactObj[0])) {
												contactDetails.setContactDetailsId((BigInteger) contactObj[0]);
											}
											contactDetails.setPersonName(CommonUtil.nullRemove(contactObj[1]));
											contactDetails.setEmail(CommonUtil.nullRemove(contactObj[2]));
											contactDetails.setMobileNumber(CommonUtil.nullRemove(contactObj[3]));
											contactDetails.setNotificationStatus(CommonUtil.nullRemove(contactObj[4]));
											contactDetails.setClientReportMaps(clientReportMapList);
											contactList.add(contactDetails);
										}
									}
									ClientCenterResponse clientCenterResponse = new ClientCenterResponse();
									
									if (CommonUtil.nullCheckBigInt(centerObj[1])) {
										clientCenterResponse.setClientDetailsId((BigInteger) centerObj[1]);
									}
									
									if (CommonUtil.nullCheckBigInt(centerObj[3])) {
										clientCenterResponse.setInventoryCenterId((BigInteger) centerObj[3]);
									}
									clientCenterResponse.setInventoryCenterName(CommonUtil.nullRemove(centerObj[4]));
									clientCenterResponse.setLogo(null);
									clientCenterResponse.setContactDetails(contactList);
									clientDetailDto = new ClientDetailDto();
									clientDetailDto.setInventoryClientId((BigInteger)clientObj[1]);
									clientDetailDto.setInventoryRegionId((BigInteger)regionObj[1]);
									clientDetailDto.setInventoryCenterId(clientCenterResponse.getInventoryCenterId());
									clientDetailDto = clientDAO.getProcessDetails(clientDetailDto);
									List<ProcessResponse> processList = new ArrayList<>();
									processResult = clientDetailDto.getResultObjList();
									for (Object[] processObj : processResult) {
										ProcessResponse process = new ProcessResponse();
										process.setInventoryProcessId((BigInteger) processObj[0]);
										process.setInventoryProcessName((String) processObj[1]);
										processList.add(process);
									}
									clientCenterResponse.setProcesses(processList);
									clientCenterList.add(clientCenterResponse);

								}
							}

							ClientRegionResponse clientRegionResponse = new ClientRegionResponse();
							clientRegionResponse.setClientCenters(clientCenterList);
							if (CommonUtil.nullCheckBigInt(regionObj[1])) {
								clientRegionResponse.setInventoryRegionId((BigInteger) clientObj[1]);
							}
							clientRegionResponse.setInventoryRegionName(CommonUtil.nullRemove(regionObj[2]));
							clientRegionResponseList.add(clientRegionResponse);

						}
					}
					ClientCreateResponse clientCreateResponse = new ClientCreateResponse();
					clientCreateResponse.setClientRegions(clientRegionResponseList);
					if (CommonUtil.nullCheckBigInt(clientObj[1])) {
						clientCreateResponse.setInventoryClientId((BigInteger) clientObj[1]);
					}
					clientCreateResponse.setInventoryClientName(CommonUtil.nullRemove(clientObj[2]));
					clientCreateResponse.setEmail(CommonUtil.nullRemove(clientObj[3]));
					clientCreateResponse.setMobileNumber(CommonUtil.nullRemove(clientObj[4]));
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
	
	@Override
	public ClientDetailDto checkClientDetailsAlreadyExists(ClientDetailDto clientDetailDto) throws Exception {
		return clientDAO.checkClientDetailsAlreadyExists(clientDetailDto);
	}

}
