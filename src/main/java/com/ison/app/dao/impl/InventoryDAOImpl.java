package com.ison.app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ison.app.constants.AppicationConstants;
import com.ison.app.dao.InventoryDAO;
import com.ison.app.model.InventoryMapping;
import com.ison.app.model.InventoryMaster;
import com.ison.app.shared.dto.InventoryDto;
import com.ison.app.util.CommonUtil;

@Repository("InventoryDAO")
public class InventoryDAOImpl implements InventoryDAO{

	@PersistenceContext(unitName = AppicationConstants.FIRST_PERSISTENCE_UNIT_NAME)
	public EntityManager firstEntityManager;
	
	private Logger logger = Logger.getLogger(InventoryDAOImpl.class);
	
	@Override
	@Transactional(value = "firstTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public InventoryDto cretae(InventoryDto inventoryDto) throws Exception {
		try {
			if(inventoryDto != null) {
				InventoryMaster inventoryMaster = new InventoryMaster();
				CommonUtil.copyProperties(inventoryDto, inventoryMaster);
				firstEntityManager.persist(inventoryMaster);
				if(inventoryMaster.getAutogenInventoryMasterId() != null) {
					CommonUtil.copyProperties(inventoryMaster, inventoryDto);
				}
			}
			
		} catch (Exception e) {
			logger.info("Exception :: InventoryDAOImpl :: create() : " + e);
		} finally {
			firstEntityManager.close();
		}
		
		return inventoryDto;
	}
	
	public InventoryDto checkInvenMasterAlreadyExists(InventoryDto inventoryDto) throws Exception {
		List<Object[]> result = null;
		Query qryObj = null;
		inventoryDto.setFlag(false);
		try {
			if(inventoryDto != null) {
					qryObj = firstEntityManager.createNativeQuery("SELECT 1 FROM  INVENTORY_MASTER B  WHERE B.INVENTORY_TYPE =:TYPE AND B.NAME =:NAME");
					qryObj.setParameter("TYPE", inventoryDto.getInventoryType());
					qryObj.setParameter("NAME", inventoryDto.getName());
					result = qryObj.getResultList();
					if(!result.isEmpty()) {
						inventoryDto.setFlag(true);
					}
				}
			
		} catch (Exception e) {
			logger.info("Exception :: InventoryDAOImpl :: inventoryDropdown() : " + e);
		} finally {
			firstEntityManager.close();
		}
		
		return inventoryDto;
	}

	@Override
	@Transactional(value = "firstTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public InventoryDto update(InventoryDto inventoryDto) throws Exception {
		try {
			inventoryDto.setFlag(false);
			InventoryMaster inventoryMaster = firstEntityManager.find(InventoryMaster.class, inventoryDto.getAutogenInventoryMasterId());
			if(inventoryMaster != null) {
				if(!CommonUtil.nullRemove(inventoryDto.getInventoryType()).isEmpty()) {
					inventoryMaster.setInventoryType(inventoryDto.getInventoryType());
				}
				if(!CommonUtil.nullRemove(inventoryDto.getName()).isEmpty()) {
					inventoryMaster.setName(inventoryDto.getName());
				}
				if(!CommonUtil.nullRemove(inventoryDto.getStatus()).isEmpty()) {
					inventoryMaster.setStatus(inventoryDto.getStatus());
				}
				firstEntityManager.merge(inventoryMaster);
				CommonUtil.copyProperties(inventoryMaster, inventoryDto);
				inventoryDto.setFlag(true);
				List<Integer> ss = new ArrayList<>();
			}
			
		} catch (Exception e) {
			logger.info("Exception :: InventoryDAOImpl :: create() : " + e);
		} finally {
			firstEntityManager.close();
		}
		
		return inventoryDto;
	}
	
	public List<Object[]> inventoryDropdown(InventoryDto inventoryDto) throws Exception {
		List<Object[]> result = null;
		Query qryObj = null;
		try {
			if(inventoryDto != null) {
				qryObj = firstEntityManager.createNativeQuery("SELECT AUTOGEN_INVENTORY_MASTER_ID, NAME, STATUS FROM INVENTORY_MASTER WHERE STATUS='ACTIVE' AND INVENTORY_TYPE=:TYPE");
				if("REGION".equalsIgnoreCase(inventoryDto.getInventoryType())) {
					qryObj.setParameter("TYPE", inventoryDto.getInventoryType());
				} else if("CENTER".equalsIgnoreCase(inventoryDto.getInventoryType())) {
					qryObj.setParameter("TYPE", inventoryDto.getInventoryType());
				} else if("PROCESS".equalsIgnoreCase(inventoryDto.getInventoryType())) {
					qryObj.setParameter("TYPE", inventoryDto.getInventoryType());
				} else if("CLIENT".equalsIgnoreCase(inventoryDto.getInventoryType())) {
					qryObj.setParameter("TYPE", inventoryDto.getInventoryType());
				}
			}
			result = qryObj.getResultList();
		} catch (Exception e) {
			logger.info("Exception :: InventoryDAOImpl :: inventoryDropdown() : " + e);
		} finally {
			firstEntityManager.close();
		}
		
		return result;
	}

	@Override
	public List<Object[]> inventoryDropdownMappingList(InventoryDto inventoryDto) throws Exception {
		List<Object[]> result = null;
		Query qryObj = null;
		try {
			if(inventoryDto != null) {
				if("REGION".equalsIgnoreCase(inventoryDto.getInventoryType())) {
					qryObj = firstEntityManager.createNativeQuery("SELECT DISTINCT A.AUTOGEN_INVENTORY_MASTER_ID, A.NAME, A.STATUS FROM INVENTORY_MASTER A, inventory_mapping B  WHERE A.STATUS='ACTIVE' AND A.AUTOGEN_INVENTORY_MASTER_ID =B.INVENTORY_REGION_ID ");
					result = qryObj.getResultList();
				} else if("CENTER".equalsIgnoreCase(inventoryDto.getInventoryType())) {
					qryObj = firstEntityManager.createNativeQuery("SELECT DISTINCT A.AUTOGEN_INVENTORY_MASTER_ID, A.NAME, A.STATUS  FROM INVENTORY_MASTER A, inventory_mapping B  WHERE A.STATUS='ACTIVE' AND A.AUTOGEN_INVENTORY_MASTER_ID =B.INVENTORY_CENTER_ID AND B.INVENTORY_REGION_ID =:REGIONID");
					qryObj.setParameter("REGIONID", inventoryDto.getInventoryRegionId());
					result = qryObj.getResultList();
				} else if("CLIENT".equalsIgnoreCase(inventoryDto.getInventoryType())) {
					qryObj = firstEntityManager.createNativeQuery("SELECT DISTINCT A.AUTOGEN_INVENTORY_MASTER_ID, A.NAME, A.STATUS  FROM INVENTORY_MASTER A, inventory_mapping B  WHERE A.STATUS='ACTIVE' AND A.AUTOGEN_INVENTORY_MASTER_ID =B.INVENTORY_CLIENT_ID AND B.INVENTORY_CENTER_ID =:CENTERID AND B.INVENTORY_REGION_ID =:REGIONID");
					qryObj.setParameter("REGIONID", inventoryDto.getInventoryRegionId());
					qryObj.setParameter("CENTERID", inventoryDto.getInventoryCenterId());
					result = qryObj.getResultList();
				} else if("PROCESS".equalsIgnoreCase(inventoryDto.getInventoryType())) {
					qryObj = firstEntityManager.createNativeQuery("SELECT DISTINCT A.AUTOGEN_INVENTORY_MASTER_ID, A.NAME, A.STATUS  FROM INVENTORY_MASTER A, inventory_mapping B  WHERE A.STATUS='ACTIVE' AND A.AUTOGEN_INVENTORY_MASTER_ID =B.INVENTORY_PROCESS_ID AND B.INVENTORY_CLIENT_ID =:CLIENTID AND B.INVENTORY_CENTER_ID =:CENTERID AND B.INVENTORY_REGION_ID =:REGIONID");
					qryObj.setParameter("REGIONID", inventoryDto.getInventoryRegionId());
					qryObj.setParameter("CENTERID", inventoryDto.getInventoryCenterId());
					qryObj.setParameter("CLIENTID", inventoryDto.getInventoryClientId());
					result = qryObj.getResultList();
				}
			}
			
		} catch (Exception e) {
			logger.info("Exception :: InventoryDAOImpl :: inventoryDropdown() : " + e);
		} finally {
			firstEntityManager.close();
		}
		
		return result;
	}
	
	public InventoryDto getInventoryDetList(InventoryDto inventoryDto) throws Exception{
		List<Object[]> regionObj = null;
		
		try {
			inventoryDto.setInventoryType("REGION");
			regionObj = inventoryDropdownMappingList(inventoryDto);
			//regionObj
			
			
			
		} catch (Exception e) {
			logger.info("Exception :: InventoryDAOImpl :: inventoryDropdown() : " + e);
		} finally {
			firstEntityManager.close();
		}
		
		
		return inventoryDto;
		
	}
	
	public InventoryDto checkInvenMapAlreadyExists(InventoryDto inventoryDto) throws Exception {
		List<Object[]> result = null;
		Query qryObj = null;
		inventoryDto.setFlag(false);
		try {
			if(inventoryDto != null) {
					qryObj = firstEntityManager.createNativeQuery("SELECT 1 FROM  INVENTORY_MAPPING B  WHERE B.INVENTORY_CLIENT_ID =:CLIENTID AND B.INVENTORY_CENTER_ID =:CENTERID AND B.INVENTORY_REGION_ID =:REGIONID AND B.INVENTORY_PROCESS_ID =:PROCESSID");
					qryObj.setParameter("REGIONID", inventoryDto.getInventoryRegionId());
					qryObj.setParameter("CENTERID", inventoryDto.getInventoryCenterId());
					qryObj.setParameter("CLIENTID", inventoryDto.getInventoryClientId());
					qryObj.setParameter("PROCESSID",inventoryDto.getInventoryProcessId());
					result = qryObj.getResultList();
					if(!result.isEmpty()) {
						inventoryDto.setFlag(true);
					}
				}
			
		} catch (Exception e) {
			logger.info("Exception :: InventoryDAOImpl :: inventoryDropdown() : " + e);
		} finally {
			firstEntityManager.close();
		}
		
		return inventoryDto;
	}
	
	@Transactional(value = "firstTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public InventoryDto cretaeInventoryMapping(InventoryDto inventoryDto) throws Exception{
		try {
			if(inventoryDto != null) {
				InventoryMapping inventoryMapping = new InventoryMapping();
				CommonUtil.copyProperties(inventoryDto, inventoryMapping);
				firstEntityManager.persist(inventoryMapping);
				if(inventoryMapping.getAutogenInventoryMappingId() != null) {
					CommonUtil.copyProperties(inventoryMapping, inventoryDto);
				}
			}
		} catch (Exception e) {
			logger.info("Exception :: InventoryDAOImpl :: cretaeInventoryMapping() : " + e);
		} finally {
			firstEntityManager.close();
		}
		
		return inventoryDto;
	}
	
	public InventoryMaster findInventory(InventoryDto inventoryDto) throws Exception {
		List<Object[]> result = null;
		InventoryMaster inventoryMaster = null;
		Query qryObj = null;
		try {
			if(inventoryDto != null) {
				qryObj = firstEntityManager.createNativeQuery("SELECT DISTINCT AUTOGEN_INVENTORY_MASTER_ID, NAME, STATUS FROM INVENTORY_MASTER WHERE STATUS='ACTIVE' AND INVENTORY_TYPE=:TYPE AND AUTOGEN_INVENTORY_MASTER_ID=:MASTERID");
				if("REGION".equalsIgnoreCase(inventoryDto.getInventoryType())) {
					qryObj.setParameter("TYPE", inventoryDto.getInventoryType());
				} else if("CENTER".equalsIgnoreCase(inventoryDto.getInventoryType())) {
					qryObj.setParameter("TYPE", inventoryDto.getInventoryType());
				} else if("PROCESS".equalsIgnoreCase(inventoryDto.getInventoryType())) {
					qryObj.setParameter("TYPE", inventoryDto.getInventoryType());
				} else if("CLIENT".equalsIgnoreCase(inventoryDto.getInventoryType())) {
					qryObj.setParameter("TYPE", inventoryDto.getInventoryType());
				}
				qryObj.setParameter("MASTERID", inventoryDto.getAutogenInventoryMasterId());
			}
			result = qryObj.getResultList();
			
			if(result != null && !result.isEmpty()) {
				Object[] obj = result.get(0);
				inventoryMaster = new InventoryMaster();
				inventoryMaster.setAutogenInventoryMasterId(CommonUtil.nullRemove(obj[0]));
				inventoryMaster.setName(CommonUtil.nullRemove(obj[1]));
				inventoryMaster.setStatus(CommonUtil.nullRemove(obj[2]));
			}
			
		} catch (Exception e) {
			logger.info("Exception :: InventoryDAOImpl :: findInventory() : " + e);
		} finally {
			firstEntityManager.close();
		}
		
		return inventoryMaster;
	}

}
