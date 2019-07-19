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
				ClientDetail clientDetail = new ClientDetail();
				for (ClientDetailDto clientDetailDto : clientDetailDtoList) {
					BeanUtils.copyProperties(clientDetailDto, clientDetail);
					firstEntityManager.persist(clientDetail);
					List<ContactDetail> contactDetailList = new ArrayList<>();
					for (ContactDetailDto contactDetailLoop : clientDetailDto.getContactDetails()) {
						ContactDetail contactDetail = new ContactDetail();
						BeanUtils.copyProperties(contactDetailLoop, contactDetail);
						contactDetail.setAutogenClientDetailsId(clientDetail.getAutogenClientDetailsId());
						firstEntityManager.persist(contactDetail);
						List<ClientReportMap> clientReportMapList = new ArrayList<>();
						for (ClientReportMap clientReportMapLoop : contactDetailLoop.getClientReportMaps()) {
							ClientReportMap clientReportMap = new ClientReportMap();
							BeanUtils.copyProperties(clientReportMapLoop, clientReportMap);
							clientReportMap.setAutogenContactDetailsId(contactDetail.getAutogenContactDetailsId());
							firstEntityManager.persist(clientReportMap);
							clientReportMapList.add(clientReportMap);
						}
						contactDetail.setClientReportMaps(clientReportMapList);
						contactDetailList.add(contactDetail);
					}
					clientDetail.setContactDetails(contactDetailList);
					//firstEntityManager.persist(clientDetail);
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
			sqlQry = new StringBuilder(
					"SELECT DISTINCT INVENTORY_CLIENT_ID, INVENTORY_CLIENT_NAME FROM CLIENT_DETAILS ORDER BY INVENTORY_CLIENT_NAME");
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
	
	public ClientDetailDto getRegionDetails(ClientDetailDto clientDetailDto) throws Exception {
		StringBuilder sqlQry = null;
		Query qryObj = null;
		List<Object[]> regionResult = null;
		try {
			sqlQry = new StringBuilder(
					"SELECT DISTINCT INVENTORY_REGION_ID, INVENTORY_REGION_NAME FROM CLIENT_DETAILS WHERE INVENTORY_CLIENT_ID=:CLIENTID ORDER BY INVENTORY_REGION_NAME");
			qryObj = firstEntityManager.createNativeQuery(sqlQry.toString());
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
		StringBuilder sqlQry = null;
		Query qryObj = null;
		List<Object[]> centerResult = null;
		try {
			sqlQry = new StringBuilder(
					"SELECT DISTINCT AUTOGEN_CLIENT_DETAILS_ID, INVENTORY_CENTER_ID, INVENTORY_CENTER_NAME FROM CLIENT_DETAILS WHERE INVENTORY_CLIENT_ID=:CLIENTID AND INVENTORY_REGION_ID=:REGIONID ORDER BY INVENTORY_CENTER_NAME");
			qryObj = firstEntityManager.createNativeQuery(sqlQry.toString());
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
		StringBuilder sqlQry = null;
		Query qryObj = null;
		List<Object[]> contactResult = null;
		try {
			sqlQry = new StringBuilder(
					"SELECT AUTOGEN_CONTACT_DETAILS_ID, PERSON_NAME, EMAIL, MOBILE_NUMBER, NOTIFICATION_STATUS FROM CONTACT_DETAILS WHERE AUTOGEN_CLIENT_DETAILS_ID=:CLIENTDETAILID AND STATUS='ACTIVE'");
			qryObj = firstEntityManager.createNativeQuery(sqlQry.toString());
			qryObj.setParameter("CLIENTDETAILID", clientDetailDto.getAutogenClientDetailsId());
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
		StringBuilder sqlQry = null;
		Query qryObj = null;
		List<Object[]> reportResult = null;
		try {
			sqlQry = new StringBuilder(
					"SELECT AUTOGEN_CLIENT_REPORT_MAP_ID, INVENTORY_PROCESS_ID, INVENTORY_PROCESS_NAME, AUTOGEN_REPORT_MASTER_ID, REPORT_NAME, AUTOGEN_FREQUENCY_MASTER_ID, FREQUENCY_NAME FROM CLIENT_REPORT_MAP WHERE AUTOGEN_CONTACT_DETAILS_ID=:CONTACTDETAILID AND STATUS='ACTIVE'");
			qryObj = firstEntityManager.createNativeQuery(sqlQry.toString());
			qryObj.setParameter("CONTACTDETAILID", contactDetailDto.getAutogenContactDetailsId());
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
	
	
	public ClientDetailDto  getAllClients() throws Exception {
		List<Object[]> clientResult = null;
		List<Object[]> regionResult = null;
		List<Object[]> centerResult = null;
		List<Object[]> contactResult = null;
		List<Object[]> clientReportResult = null;
		StringBuilder sqlQry = null;
		Query qryObj = null;
		List<ClientCreateResponse> tempObjList = new ArrayList<>();
		try {
			sqlQry = new StringBuilder("SELECT DISTINCT INVENTORY_CLIENT_ID, INVENTORY_CLIENT_NAME FROM CLIENT_DETAILS ORDER BY INVENTORY_CLIENT_NAME");
			qryObj = firstEntityManager.createNativeQuery(sqlQry.toString());
			clientResult = (List<Object[]>) qryObj.getResultList();
			if(clientResult != null && !clientResult.isEmpty()) {
				for (Object[] clientObj : clientResult) {
					qryObj = null;
					qryObj = firstEntityManager.createNativeQuery("SELECT DISTINCT INVENTORY_REGION_ID, INVENTORY_REGION_NAME FROM CLIENT_DETAILS WHERE INVENTORY_CLIENT_ID=:CLIENTID ORDER BY INVENTORY_REGION_NAME");
					qryObj.setParameter("CLIENTID", (BigInteger)clientObj[0]);
					regionResult = qryObj.getResultList();
					List<ClientRegionResponse> clientRegionResponseList = new ArrayList<>();
					for (Object[] regionObj : regionResult) {
						
						qryObj = null;
						qryObj = firstEntityManager.createNativeQuery("SELECT DISTINCT AUTOGEN_CLIENT_DETAILS_ID, INVENTORY_CENTER_ID, INVENTORY_CENTER_NAME FROM CLIENT_DETAILS WHERE INVENTORY_CLIENT_ID=:CLIENTID AND INVENTORY_REGION_ID=:REGIONID ORDER BY INVENTORY_CENTER_NAME");
						qryObj.setParameter("CLIENTID", (BigInteger)clientObj[0]);
						qryObj.setParameter("REGIONID", (BigInteger)regionObj[0]);
						centerResult = qryObj.getResultList();
						List<ClientCenterResponse> clientCenterList = new ArrayList<>();
						for (Object[] centerObj : centerResult) {
							qryObj = null;
							qryObj = firstEntityManager.createNativeQuery("SELECT AUTOGEN_CONTACT_DETAILS_ID, PERSON_NAME, EMAIL, MOBILE_NUMBER, NOTIFICATION_STATUS FROM CONTACT_DETAILS WHERE AUTOGEN_CLIENT_DETAILS_ID=:CLIENTDETAILID AND STATUS='ACTIVE'");
							qryObj.setParameter("CLIENTDETAILID", (BigInteger)centerObj[0]);
							contactResult = qryObj.getResultList();
							List<ContactDetails> contactList = new ArrayList<>();
							for (Object[] contactObj : contactResult) {
								qryObj = null;
								qryObj = firstEntityManager.createNativeQuery("SELECT AUTOGEN_CLIENT_REPORT_MAP_ID, INVENTORY_PROCESS_ID, INVENTORY_PROCESS_NAME, AUTOGEN_REPORT_MASTER_ID, REPORT_NAME, AUTOGEN_FREQUENCY_MASTER_ID, FREQUENCY_NAME FROM CLIENT_REPORT_MAP WHERE AUTOGEN_CONTACT_DETAILS_ID=:CONTACTDETAILID AND STATUS='ACTIVE'");
								qryObj.setParameter("CONTACTDETAILID", (BigInteger)contactObj[0]);
								clientReportResult = qryObj.getResultList();
								List<ClientReportMapCreateResponse> clientReportMapList = new ArrayList<>();
								for(Object[] clientReportObj:clientReportResult){
									ClientReportMapCreateResponse reportMapCreateResponse = new ClientReportMapCreateResponse();
									reportMapCreateResponse.setClientReportMapId((BigInteger)clientReportObj[0]);
									reportMapCreateResponse.setInventoryProcessName((String)clientReportObj[1]);
									reportMapCreateResponse.setInventoryProcessId((BigInteger)clientReportObj[2]);
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
					clientCreateResponse.setEmail((String)clientObj[2]);
					clientCreateResponse.setMobileNumber((String)clientObj[3]);
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

	@Override
	public List<ClientDetailDto> findByClient(ClientDetailDto clientDetailDto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
