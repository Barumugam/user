package com.ison.app.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ison.app.constants.AppicationConstants;
import com.ison.app.dao.ClientDAO;
import com.ison.app.message.response.ClientCenterResponse;
import com.ison.app.message.response.ClientCreateResponse;
import com.ison.app.message.response.ClientRegionResponse;
import com.ison.app.message.response.ClientReportMapCreateResponse;
import com.ison.app.message.response.ContactDetails;
import com.ison.app.model.ClientDetail;
import com.ison.app.model.ClientReportMap;
import com.ison.app.model.ContactDetail;
import com.ison.app.shared.dto.ClientDetailDto;
import com.ison.app.shared.dto.ClientReportMapDto;
import com.ison.app.shared.dto.ContactDetailDto;
import com.ison.app.util.CommonQueryConstant;
import com.ison.app.util.CommonUtil;

@Repository
public class ClientDAOImpl implements ClientDAO {
	
	private Logger logger = Logger.getLogger(UserDAOImpl.class);

	@PersistenceContext(unitName = AppicationConstants.FIRST_PERSISTENCE_UNIT_NAME)
	public EntityManager firstEntityManager;

	@Override
	@Transactional(value = "firstTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ClientDetailDto create(List<ClientDetailDto> clientDetailDtoList) throws Exception {
		Object[] result = new Object[2];
		ClientDetailDto clientDetailResult = new ClientDetailDto();
		try {
			if (clientDetailDtoList != null) {
				
				for (ClientDetailDto clientDetailDto : clientDetailDtoList) {
					ClientDetail clientDetail = new ClientDetail();
					if(clientDetailDto.getAutogenClientDetailsId() != null) {
						ClientDetail findClientDetail = firstEntityManager.find(ClientDetail.class, clientDetailDto.getAutogenClientDetailsId());
						if(findClientDetail != null) {
							if(clientDetailDto.getMobileNumber() != null) {
								findClientDetail.setMobileNumber(clientDetailDto.getMobileNumber());
							}
							if(clientDetailDto.getEmail() != null) {
								findClientDetail.setEmail(clientDetailDto.getEmail());
							}
							firstEntityManager.merge(findClientDetail);
						}
					}else {
						BeanUtils.copyProperties(clientDetailDto, clientDetail);
						clientDetail.setUpdatedBy(null);
						clientDetail.setRecUpdateDt(null);
						firstEntityManager.persist(clientDetail);
					}
					for (ContactDetailDto contactDetailLoop : clientDetailDto.getContactDetails()) {
						ContactDetail contactDetail = new ContactDetail();
						if(contactDetailLoop.getAutogenContactDetailsId() != null) {
							ContactDetail findContactDetail = firstEntityManager.find(ContactDetail.class, contactDetailLoop.getAutogenContactDetailsId());
							if(findContactDetail != null) {
								if(contactDetailLoop.getPersonName() != null) {
									findContactDetail.setPersonName(contactDetailLoop.getPersonName());
								}
								if(contactDetailLoop.getEmail() != null) {
									findContactDetail.setEmail(contactDetailLoop.getEmail());
								}
								if(contactDetailLoop.getMobileNumber() != null) {
									findContactDetail.setMobileNumber(contactDetailLoop.getMobileNumber());
								}
								if(contactDetailLoop.getNotificationStatus() != null) {
									findContactDetail.setNotificationStatus(contactDetailLoop.getNotificationStatus());
								}
								if(contactDetailLoop.getStatus() != null) {
									findContactDetail.setStatus(contactDetailLoop.getStatus());
								}
								if(contactDetailLoop.getUpdatedBy() != null) {
									findContactDetail.setUpdatedBy(contactDetailLoop.getUpdatedBy());
								}
								firstEntityManager.merge(findContactDetail);
							}
						}else {
							BeanUtils.copyProperties(contactDetailLoop, contactDetail);
							contactDetail.setAutogenClientDetailsId(clientDetail.getAutogenClientDetailsId());
							contactDetail.setUpdatedBy(null);
							contactDetail.setRecUpdateDt(null);
							firstEntityManager.persist(contactDetail);
						}
						for (ClientReportMapDto clientReportMapLoop : contactDetailLoop.getClientReportMaps()) {
							ClientReportMap clientReportMap = new ClientReportMap();
							if(clientReportMapLoop.getAutogenClientReportMapId() != null) {
								ClientReportMap findClientReportMap = firstEntityManager.find(ClientReportMap.class, clientReportMapLoop.getAutogenClientReportMapId());
								if(findClientReportMap != null) {
									BeanUtils.copyProperties(clientDetailDto, findClientReportMap);
									if(clientReportMapLoop.getAutogenReportMasterId() != null) {
										findClientReportMap.setAutogenReportMasterId(clientReportMapLoop.getAutogenReportMasterId());
									}
									if(clientReportMapLoop.getReportName() != null) {
										findClientReportMap.setReportName(clientReportMapLoop.getReportName());
									}
									
									if(clientReportMapLoop.getAutogenFrequencyMasterId() != null) {
										findClientReportMap.setAutogenFrequencyMasterId(clientReportMapLoop.getAutogenFrequencyMasterId());
									}
									if(clientReportMapLoop.getFrequencyName() != null) {
										findClientReportMap.setFrequencyName(clientReportMapLoop.getFrequencyName());
									}
									
									if(clientReportMapLoop.getInventoryProcessId() != null) {
										findClientReportMap.setInventoryProcessId(clientReportMapLoop.getInventoryProcessId());
									}
									if(clientReportMapLoop.getInventoryProcessName() != null) {
										findClientReportMap.setInventoryProcessName(clientReportMapLoop.getInventoryProcessName());
									}
									
									if(clientReportMapLoop.getStatus() != null) {
										findClientReportMap.setStatus(clientReportMapLoop.getStatus());
									}
									if(clientReportMapLoop.getUpdatedBy() != null) {
										findClientReportMap.setUpdatedBy(clientReportMapLoop.getUpdatedBy());
									}
									firstEntityManager.merge(findClientReportMap);
								}
							}else {
								BeanUtils.copyProperties(clientReportMapLoop, clientReportMap);
								clientReportMap.setAutogenContactDetailsId(contactDetail.getAutogenContactDetailsId());
								clientReportMap.setUpdatedBy(null);
								clientReportMap.setRecUpdateDt(null);
								firstEntityManager.persist(clientReportMap);
							}
						}
					}

					result[0] = true;
				}

			}
		} catch (Exception e) {
			logger.info("Exception :: ClientDAOImpl :: create() : " + e);
		} finally {
			firstEntityManager.close();
		}
		clientDetailResult.setResultObj(result);
		return clientDetailResult;
	}
	
	public ClientDetailDto getClientDetails() throws Exception {
		ClientDetailDto clientDetailDto = null;
		StringBuilder sqlQry = null;
		Query qryObj = null;
		List<Object[]> clientResult = null;
		try {
			sqlQry = new StringBuilder(CommonQueryConstant.CLIENT_DET_QRY);
			qryObj = firstEntityManager.createNativeQuery(sqlQry.toString());
			clientResult = (List<Object[]>) qryObj.getResultList();
		} catch (Exception e) {
			logger.info("Exception :: ClientDAOImpl :: getClientDetails() : " + e);
		} finally {
			firstEntityManager.close();
		}
		if (clientResult != null && !clientResult.isEmpty()) {
			clientDetailDto = new ClientDetailDto();
			clientDetailDto.setResultObjList(clientResult);
		}
		return clientDetailDto;

	}
	
		
	public ClientDetailDto getAllClientDetails() throws Exception {
		List<Object[]> clientResult = null;
		List<Object[]> regionResult = null;
		List<Object[]> centerResult = null;
		List<Object[]> contactResult = null;
		List<Object[]> clientReportResult = null;
		StringBuilder sqlQry = null;
		Query qryObj = null;
		List<ClientCreateResponse> tempObjList = new ArrayList<>();
		try {
			sqlQry = new StringBuilder(CommonQueryConstant.CLIENT_DET_QRY);
			qryObj = firstEntityManager.createNativeQuery(sqlQry.toString());
			clientResult = (List<Object[]>) qryObj.getResultList();
			if (clientResult != null && !clientResult.isEmpty()) {
				for (Object[] clientObj : clientResult) {

					List<ClientRegionResponse> clientRegionResponseList = new ArrayList<>();
					if (clientObj[0] != null && "Y".equalsIgnoreCase(clientObj[0].toString())) {

						qryObj = null;
						qryObj = firstEntityManager.createNativeQuery(CommonQueryConstant.CLIENT_DET_REGION_QRY);
						qryObj.setParameter("CLIENTID", (BigInteger) clientObj[1]);
						regionResult = qryObj.getResultList();
						for (Object[] regionObj : regionResult) {
							List<ClientCenterResponse> clientCenterList = new ArrayList<>();
							if (regionObj[0] != null && "Y".equalsIgnoreCase(regionObj[0].toString())) {
								qryObj = null;
								qryObj = firstEntityManager
										.createNativeQuery(CommonQueryConstant.CLIENT_DET_CENTER_QRY);
								qryObj.setParameter("CLIENTID", (BigInteger) clientObj[1]);
								qryObj.setParameter("REGIONID", (BigInteger) regionObj[1]);
								centerResult = qryObj.getResultList();
								for (Object[] centerObj : centerResult) {
									List<ContactDetails> contactList = new ArrayList<>();
									if (centerObj[0] != null && "Y".equalsIgnoreCase(centerObj[0].toString())) {
										qryObj = null;
										qryObj = firstEntityManager
												.createNativeQuery(CommonQueryConstant.CLIENT_DET_CONTACT_DET_QRY);
										qryObj.setParameter("CLIENTDETID", (BigInteger) centerObj[1]);
										contactResult = qryObj.getResultList();
										for (Object[] contactObj : contactResult) {
											List<ClientReportMapCreateResponse> clientReportMapList = new ArrayList<>();
											if (centerObj[0] != null && "Y".equalsIgnoreCase(centerObj[0].toString())) {
												qryObj = null;
												qryObj = firstEntityManager
														.createNativeQuery(CommonQueryConstant.CLIENT_REPORT_MAP_QRY);
												qryObj.setParameter("CONTACTDETID", (BigInteger) contactObj[0]);
												clientReportResult = qryObj.getResultList();
												for (Object[] clientReportObj : clientReportResult) {
													ClientReportMapCreateResponse reportMapCreateResponse = new ClientReportMapCreateResponse();
													reportMapCreateResponse
															.setClientReportMapId((BigInteger) clientReportObj[0]);
													reportMapCreateResponse
															.setInventoryProcessName((String) clientReportObj[1]);
													reportMapCreateResponse
															.setInventoryProcessId((BigInteger) clientReportObj[2]);
													reportMapCreateResponse
															.setAutogenReportMasterId((BigInteger) clientReportObj[3]);
													reportMapCreateResponse.setReportName((String) clientReportObj[4]);
													reportMapCreateResponse.setAutogenFrequencyMasterId(
															(BigInteger) clientReportObj[5]);
													reportMapCreateResponse
															.setFrequencyName((String) clientReportObj[6]);
													clientReportMapList.add(reportMapCreateResponse);
												}
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
									if (CommonUtil.nullCheckBigInt(centerObj[0])) {
										clientCenterResponse.setClientDetailsId((BigInteger) centerObj[0]);
									}
									if (CommonUtil.nullCheckBigInt(centerObj[1])) {
										clientCenterResponse.setInventoryCenterId((BigInteger) centerObj[1]);
									}
									clientCenterResponse.setInventoryCenterName(CommonUtil.nullRemove(centerObj[2]));
									clientCenterResponse.setLogo(null);
									clientCenterList.add(clientCenterResponse);

								}
							}

							ClientRegionResponse clientRegionResponse = new ClientRegionResponse();
							clientRegionResponse.setClientCenters(clientCenterList);
							if (CommonUtil.nullCheckBigInt(regionObj[0])) {
								clientRegionResponse.setInventoryRegionId((BigInteger) regionObj[0]);
							}
							clientRegionResponse.setInventoryRegionName(CommonUtil.nullRemove(regionObj[1]));
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
			logger.info("Exception :: ClientDAOImpl :: create() : " + e);
		} finally {
			firstEntityManager.close();
		}
		ClientDetailDto clientDetailDto = new ClientDetailDto();
		Object[] result = new Object[2];
		result[0] =  tempObjList;
		clientDetailDto.setResultObj(result); 
		return clientDetailDto;
	}
	

	
	
	public ClientDetailDto getRegionDetails(ClientDetailDto clientDetailDto) throws Exception {
		Query qryObj = null;
		List<Object[]> regionResult = null;
		try {
			qryObj = null;
			qryObj = firstEntityManager.createNativeQuery(CommonQueryConstant.CLIENT_DET_REGION_QRY);
			qryObj.setParameter("CLIENTID", clientDetailDto.getInventoryClientId());
			regionResult = (List<Object[]>) qryObj.getResultList();
		} catch (Exception e) {
			logger.info("Exception :: ClientDAOImpl :: regionDetails() : " + e);
		} finally {
			firstEntityManager.close();
		}
		if (regionResult != null && !regionResult.isEmpty()) {
			clientDetailDto.setResultObjList(regionResult);
		}
		return clientDetailDto;

	}
	
	public ClientDetailDto getCenterDetails(ClientDetailDto clientDetailDto) throws Exception {
		Query qryObj = null;
		List<Object[]> centerResult = null;
		try {
			qryObj = firstEntityManager.createNativeQuery(CommonQueryConstant.CLIENT_DET_CENTER_QRY);
			qryObj.setParameter("CLIENTID", clientDetailDto.getInventoryClientId());
			qryObj.setParameter("REGIONID", clientDetailDto.getInventoryRegionId());
			centerResult = (List<Object[]>) qryObj.getResultList();
		} catch (Exception e) {
			logger.info("Exception :: ClientDAOImpl :: centerDetails() : " + e);
		} finally {
			firstEntityManager.close();
		}
		if (centerResult != null && !centerResult.isEmpty()) {
			clientDetailDto.setResultObjList(centerResult);
		}
		return clientDetailDto;
	}
	
	public ClientDetailDto getContactDetails(ClientDetailDto clientDetailDto) throws Exception {
		Query qryObj = null;
		List<Object[]> contactResult = null;
		try {
			qryObj = firstEntityManager.createNativeQuery(CommonQueryConstant.CLIENT_DET_CONTACT_DET_QRY);
			qryObj.setParameter("CLIENTDETID", clientDetailDto.getAutogenClientDetailsId());
			contactResult = (List<Object[]>) qryObj.getResultList();
		} catch (Exception e) {
			logger.info("Exception :: ClientDAOImpl :: getContactDetails() : " + e);
		} finally {
			firstEntityManager.close();
		}
		if (contactResult != null && !contactResult.isEmpty()) {
			clientDetailDto.setResultObjList(contactResult);
		}
		return clientDetailDto;
	}
	
	public ClientDetailDto getClientReportMapDetails(ContactDetailDto contactDetailDto) throws Exception {
		ClientDetailDto clientDetailDto = null;
		Query qryObj = null;
		List<Object[]> reportResult = null;
		try {
			qryObj = firstEntityManager.createNativeQuery(CommonQueryConstant.CLIENT_REPORT_MAP_QRY);
			qryObj.setParameter("CONTACTDETID", contactDetailDto.getAutogenContactDetailsId());
			reportResult = (List<Object[]>) qryObj.getResultList();
		} catch (Exception e) {
			logger.info("Exception :: ClientDAOImpl :: getClientReportMapDetails() : " + e);
		} finally {
			firstEntityManager.close();
		}
		if (reportResult != null && !reportResult.isEmpty()) {
			clientDetailDto = new ClientDetailDto();
			clientDetailDto.setResultObjList(reportResult);
		}
		return clientDetailDto;
	}
	
	public ClientDetailDto getProcessDetails(ClientDetailDto ClientDetailDto) throws Exception {
		ClientDetailDto clientDetailDto = null;
		Query qryObj = null;
		List<Object[]> processResult = null;
		try {
			qryObj = firstEntityManager.createNativeQuery(CommonQueryConstant.CLIENT_DET_PROCESS_QRY);
			qryObj.setParameter("CLIENTID", ClientDetailDto.getInventoryClientId());
			qryObj.setParameter("REGIONID", ClientDetailDto.getInventoryRegionId());
			qryObj.setParameter("CENTERID", ClientDetailDto.getInventoryCenterId());
			processResult = (List<Object[]>) qryObj.getResultList();
		} catch (Exception e) {
			logger.info("Exception :: ClientDAOImpl :: getClientReportMapDetails() : " + e);
		} finally {
			firstEntityManager.close();
		}
		if (processResult != null && !processResult.isEmpty()) {
			clientDetailDto = new ClientDetailDto();
			clientDetailDto.setResultObjList(processResult);
		}
		return clientDetailDto;
	}


	public ClientDetailDto checkClientDetailsAlreadyExists(ClientDetailDto clientDetailDto) throws Exception {
		List<Object[]> result = null;
		Query qryObj = null;
		clientDetailDto.setFlag(false);
		try {
			if(clientDetailDto != null) {
					qryObj = firstEntityManager.createNativeQuery("SELECT 1 FROM  CLIENT_DETAILS B  WHERE B.INVENTORY_CLIENT_ID =:CLIENTID AND B.INVENTORY_CENTER_ID =:CENTERID AND B.INVENTORY_REGION_ID =:REGIONID");
					qryObj.setParameter("REGIONID", clientDetailDto.getInventoryRegionId());
					qryObj.setParameter("CENTERID", clientDetailDto.getInventoryCenterId());
					qryObj.setParameter("CLIENTID", clientDetailDto.getInventoryClientId());
					result = qryObj.getResultList();
					if(!result.isEmpty()) {
						clientDetailDto.setFlag(true);
					}
				}
			
		} catch (Exception e) {
			logger.info("Exception :: ClientDAOImpl :: checkClientDetailsAlreadyExists() : " + e);
		} finally {
			firstEntityManager.close();
		}
		
		return clientDetailDto;
	}

	@Override
	public List<ClientDetailDto> findByClient(ClientDetailDto clientDetailDto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
