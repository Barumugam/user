package com.ison.app.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ison.app.constants.AppicationConstants;
import com.ison.app.dao.AbstractDao;
import com.ison.app.dao.InventoryDAO;
import com.ison.app.dao.RolesDAO;
import com.ison.app.dao.UserDAO;
import com.ison.app.model.Client;
import com.ison.app.model.InventoryMaster;
import com.ison.app.model.Roles;
import com.ison.app.model.UserClientMap;
import com.ison.app.model.Users;
import com.ison.app.model.UsersDetail;
import com.ison.app.shared.dto.InventoryDto;
import com.ison.app.shared.dto.UserDto;
import com.ison.app.util.CommonUtil;

@Repository
public class UserDAOImpl extends AbstractDao<Integer, Users>  implements UserDAO {

	@PersistenceContext(unitName = AppicationConstants.FIRST_PERSISTENCE_UNIT_NAME)
	public EntityManager firstEntityManager;

	@Autowired
	@Qualifier(AppicationConstants.FIRST_JDBC_TEMPLATE)
	JdbcTemplate firstJdbcTemplate;
	
	@Autowired
	RolesDAOImpl rolesDAOImpl;
	
	@Autowired
	InventoryDAO inventoryDAO;
	
	@Autowired
	RolesDAO rolesDAO;

	private Logger logger = Logger.getLogger(UserDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Optional<UserDto> findByUsername(String username) throws Exception{
		StringBuilder sqlQry = null;
		Optional<UserDto> userOptional = null;
		UserDto userDto = null;
		List<Object[]> resultObj = null;
		try {
			sqlQry = new StringBuilder("SELECT us.autogen_users_id, us.employee_id, us.password, us.status, rs.role_name FROM Users us, users_details usd, Roles rs  WHERE employee_Id=:USERNAME and usd.AUTOGEN_USERS_ID=us.AUTOGEN_USERS_ID and usd.AUTOGEN_ROLES_ID=rs.AUTOGEN_ROLES_ID");
			Query queryObj = firstEntityManager.createNativeQuery(sqlQry.toString());
			queryObj.setParameter("USERNAME", username);
			resultObj = (List<Object[]>) queryObj.getResultList();

			if (resultObj != null && !resultObj.isEmpty()) {
				for (Object[] objects : resultObj) {
					userDto = new UserDto();
					userDto.setAutogenUsersId(objects[0].toString());
					userDto.setEmployeeId(objects[1].toString());
					userDto.setPassword(objects[2].toString());
					userDto.setStatus(objects[3].toString());
					Set<Roles> roleset =  new HashSet<>();
					Roles roles = new Roles();
					roles.setRoleName(objects[4].toString());
					roleset.add(roles);
					userDto.setRoles(roleset);
					
				}
				/*Users users = resultObj.get(0);
				UserDto userDto = new UserDto();
				BeanUtils.copyProperties(users, userDto);
				if (users != null) {
					sqlQry = new StringBuilder("SELECT usd FROM UsersDetail usd WHERE autogenUsersId=:AUTOGENUSERSID");
					Query subQueryObj = firstEntityManager.createQuery(sqlQry.toString());
					subQueryObj.setParameter("AUTOGENUSERSID", users.getAutogenUsersId());
					List<UsersDetail> resultObj1 = null;
					resultObj1 = (List<UsersDetail>) subQueryObj.getResultList();
					if (resultObj1 != null && !resultObj1.isEmpty()) {
						UsersDetail usersDetail = resultObj1.get(0);
						BeanUtils.copyProperties(usersDetail, userDto);
					}

				}*/
				userOptional = Optional.ofNullable(userDto);
			}

		} catch (Exception e) {
			logger.info("Exception :: UserDAOImpl :: findByUsername() : " + e);
		} finally {
			firstEntityManager.close();
		}
		return userOptional;
	}

	@Override
	public Boolean existsByUsername(String username) throws Exception{
		StringBuilder sqlQry = null;
		boolean user = false;
		List<Object[]> result = null;
		try {
			sqlQry = new StringBuilder("SELECT 1 FROM USERS WHERE EMPLOYEE_ID=:USERNAME");
			Query queryObj = firstEntityManager.createNativeQuery(sqlQry.toString());
			queryObj.setParameter("USERNAME", username);
			result =  queryObj.getResultList();
			if(!result.isEmpty()) {
				user = true;
			}
		} catch (Exception e) {
			logger.info("Exception :: UserDAOImpl :: existsByUsername() : " + e);
		} finally {
			firstEntityManager.close();
		}
		return user;
	}

	@Override
	public Boolean existsByEmail(String email) throws Exception{
		StringBuilder sqlQry = null;
		boolean user = false;
		List<Object[]> result = null;
		try {
			sqlQry = new StringBuilder("SELECT 1 FROM USERS WHERE EMAIL=:EMAIL");
			Query queryObj = firstEntityManager.createNativeQuery(sqlQry.toString());
			queryObj.setParameter("EMAIL", email);
			result =  queryObj.getResultList();
			if(!result.isEmpty()) {
				user = true;
			}
		} catch (Exception e) {
			logger.info("Exception :: UserDAOImpl :: existsByEmail() : " + e);
		} finally {
			firstEntityManager.close();
		}
		return user;
	}

	@Override
	@Transactional(value = "firstTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserDto save(UserDto userDto) throws Exception{
		try {
			if(userDto != null) {
				Users users = new Users();
				BeanUtils.copyProperties(userDto, users);
				users.setStatus("NEW");
				firstEntityManager.persist(users);
				if(users.getAutogenUsersId() != null) {
					UsersDetail usersDet = new UsersDetail();
					usersDet.setAutogenUsersId(new BigInteger(users.getAutogenUsersId()));
					BeanUtils.copyProperties(userDto, usersDet);
					firstEntityManager.persist(usersDet);
					if(usersDet.getAutogenUsersDetailsId() != null && userDto.getClients() != null) {
						UserClientMap userClientMap = null;
						for (Client client : userDto.getClients()) {
							userClientMap = new UserClientMap();
							userClientMap.setAutogenUsersDetailsId(new BigInteger(usersDet.getAutogenUsersDetailsId()));
							userClientMap.setInventoryClientId(client.getId());
							firstEntityManager.persist(userClientMap);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.info("Exception :: UserDAOImpl :: save() : " + e);
		} finally {
			firstEntityManager.close();
		}
		
		return userDto;
	}

	@Override
	public List<UserDto> getUsersList() throws Exception {
		StringBuilder sqlQry = null;
		List<Users> resultObj = new ArrayList<>();
		List<UserDto> userDtoList = new ArrayList<>();
		try {
			sqlQry = new StringBuilder("SELECT us FROM Users us");
			Query queryObj = firstEntityManager.createQuery(sqlQry.toString());
			resultObj =  queryObj.getResultList();
			for (Users users : resultObj) {
				UserDto userDto = new UserDto();
				BeanUtils.copyProperties(users, userDto);
				List<UsersDetail> subResultObj = new ArrayList<>();
				sqlQry = new StringBuilder("SELECT usd FROM UsersDetail usd where usd.autogenUsersId=:USERID");
				Query subQueryObj = firstEntityManager.createQuery(sqlQry.toString());
				subQueryObj.setParameter("USERID", new BigInteger(users.getAutogenUsersId()));
				subResultObj =  subQueryObj.getResultList();
				for (UsersDetail users2 : subResultObj) {
					BeanUtils.copyProperties(users2, userDto);
					InventoryDto inventoryDto = new InventoryDto();
					InventoryMaster inventoryMaster = null;
					if(users2.getInventoryRegionId() != null) {
					inventoryDto.setAutogenInventoryMasterId(String.valueOf(users2.getInventoryRegionId()));
					inventoryDto.setInventoryType("REGION");
					inventoryMaster = inventoryDAO.findInventory(inventoryDto);
					if(inventoryMaster != null &&!inventoryMaster.getName().isEmpty()) {
					userDto.setRegionName(inventoryMaster.getName());
					}
					}
					if(users2.getInventoryCenterId() != null) {
					inventoryDto.setAutogenInventoryMasterId(String.valueOf(users2.getInventoryCenterId()));
					inventoryDto.setInventoryType("CENTER");
					inventoryMaster = null;
					inventoryMaster = inventoryDAO.findInventory(inventoryDto);
					if(inventoryMaster != null &&!inventoryMaster.getName().isEmpty()) {
						userDto.setCenterName(inventoryMaster.getName());
					}
					}
					if(users2.getInventoryProcessId() != null) {
					inventoryDto.setAutogenInventoryMasterId(String.valueOf(users2.getInventoryProcessId()));
					inventoryDto.setInventoryType("PROCESS");
					inventoryMaster = null;
					inventoryMaster = inventoryDAO.findInventory(inventoryDto);
					if(inventoryMaster != null &&!inventoryMaster.getName().isEmpty()) {
						userDto.setProcessName(inventoryMaster.getName());
					}
					}
					if(users2.getAutogenRolesId() != null){
					userDto.setRoleName(rolesDAO.getRoleById(users2.getAutogenRolesId()));
					}
					
					
					List<Object[]> subResultObj1 = new ArrayList<>();
					sqlQry = new StringBuilder("SELECT im.AUTOGEN_INVENTORY_MASTER_ID, im.NAME from INVENTORY_MASTER im, USER_CLIENT_MAP usd where im.AUTOGEN_INVENTORY_MASTER_ID = usd.INVENTORY_CLIENT_ID AND usd.AUTOGEN_USERS_DETAILS_ID=:USERDETAILSID AND usd.STATUS='ACTIVE'");
					Query subQueryObj1 = firstEntityManager.createNativeQuery(sqlQry.toString());
					subQueryObj1.setParameter("USERDETAILSID", new BigInteger(users2.getAutogenUsersDetailsId()));
					subResultObj1 =  subQueryObj1.getResultList();
					List<Client> clients = new ArrayList<>();
					for (Object[] userclientmap : subResultObj1) {
						Client client = new Client();
						client.setId(new BigInteger((userclientmap[0].toString())));
						client.setClientName(String.valueOf(userclientmap[1]));
						clients.add(client);
					}
					userDto.setClients(clients);
				}
				userDtoList.add(userDto);
			}
		} catch (Exception e) {
			logger.info("Exception :: UserDAOImpl :: existsByEmail() : " + e);
		} finally {
			firstEntityManager.close();
		}
		return userDtoList ;
	}
	
	@Transactional(value = "firstTransactionManager", propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public boolean UpdateUserStatus(UserDto userDto) throws Exception {
		boolean result = false;
		try {
			Users user = firstEntityManager.find(Users.class, userDto.getAutogenUsersId());
			if(user != null) {
				if(!CommonUtil.nullRemove(userDto.getStatus()).isEmpty()) {
					user.setStatus(userDto.getStatus());
				}
				if(!CommonUtil.nullRemove(userDto.getUpdatedBy()).isEmpty()) {
					user.setUpdatedBy(userDto.getUpdatedBy());
				}
				firstEntityManager.merge(user);
				result = true;
			}
		} catch (Exception e) {
			logger.info("Exception :: UserDAOImpl :: save() : " + e);
		} finally {
			firstEntityManager.close();
		}
		
		return result;
	}

	@Override
	@Transactional(value = "firstTransactionManager", propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public boolean UpdateUser(UserDto userDto) throws Exception {
		StringBuilder sqlQry = null;
		boolean result = false;
		List<Users> userList = null;
		try {
			Users user = firstEntityManager.find(Users.class, userDto.getAutogenUsersId());
			if(user != null) {
				if(!CommonUtil.nullRemove(userDto.getFirstName()).isEmpty()) {
					user.setEmail(userDto.getFirstName());
				}
				if(!CommonUtil.nullRemove(userDto.getLastName()).isEmpty()) {
					user.setLastName(userDto.getLastName());
				}
				if(!CommonUtil.nullRemove(userDto.getEmail()).isEmpty()) {
					user.setEmail(userDto.getEmail());
				}
				if(!CommonUtil.nullRemove(userDto.getMobileNumber()).isEmpty()) {
					user.setMobileNumber(userDto.getMobileNumber());
				}
			
				if(!CommonUtil.nullRemove(userDto.getStatus()).isEmpty()) {
					user.setStatus(userDto.getStatus());
				}
				
				firstEntityManager.merge(user);
				
				List<UsersDetail> subResultObj = new ArrayList<>();
				sqlQry = new StringBuilder("SELECT usd FROM UsersDetail usd where usd.autogenUsersId=:USERID");
				Query subQueryObj = firstEntityManager.createQuery(sqlQry.toString());
				subQueryObj.setParameter("USERID", new BigInteger(user.getAutogenUsersId()));
				subResultObj =  subQueryObj.getResultList();
				for (UsersDetail usersDetail : subResultObj) {
					if (userDto.getAutogenRolesId() != null) {
						usersDetail.setAutogenRolesId(userDto.getAutogenRolesId());
					}
					if (userDto.getInventoryRegionId() != null) {
						usersDetail.setInventoryRegionId(userDto.getInventoryRegionId());
					}

					if (userDto.getInventoryCenterId() != null) {
						usersDetail.setInventoryCenterId(userDto.getInventoryCenterId());
					}
					if (userDto.getInventoryProcessId() != null) {
						usersDetail.setInventoryProcessId(userDto.getInventoryProcessId());
					}
					
					if (userDto.getSupervisorUsersId() != null) {
						usersDetail.setSupervisorUsersId(userDto.getSupervisorUsersId());
					}
					
					if (userDto.getUpdatedBy() != null) {
						usersDetail.setUpdatedBy(userDto.getUpdatedBy());
					}
					firstEntityManager.merge(usersDetail);
					
					if(userDto.getClients() != null && usersDetail.getAutogenUsersDetailsId() != null) {
						sqlQry = new StringBuilder("UPDATE USER_CLIENT_MAP SET STATUS ='INACTIVE'  WHERE AUTOGEN_USERS_DETAILS_ID =:AUTOGENUSERSDETAILSID");
						Query updateQry = firstEntityManager.createNativeQuery(sqlQry.toString());
						updateQry.setParameter("AUTOGENUSERSDETAILSID", usersDetail.getAutogenUsersDetailsId());
						updateQry.executeUpdate();
						for (Client client : userDto.getClients()) {
						List<UserClientMap> userClientMapList = new ArrayList<>();
						sqlQry = new StringBuilder("SELECT ucm FROM UserClientMap ucm where ucm.autogenUsersDetailsId=:AUTOGENUSERSDETAILSID AND ucm.inventoryClientId =:USERCLIENTID");
						Query subQueryObj3 = firstEntityManager.createQuery(sqlQry.toString());
						subQueryObj3.setParameter("AUTOGENUSERSDETAILSID", new BigInteger(usersDetail.getAutogenUsersDetailsId()));
						subQueryObj3.setParameter("USERCLIENTID", client.getId());
						userClientMapList = subQueryObj3.getResultList();
						if(userClientMapList != null && !userClientMapList.isEmpty()) {
						UserClientMap userClientMap = userClientMapList.get(0);
						//userClientMap.setInventoryClientId(client.getId());
						userClientMap.setStatus("ACTIVE");
						userClientMap.setUpdatedBy(userDto.getUpdatedBy());	
						firstEntityManager.merge(userClientMap);
						} else {
							UserClientMap userClientMap = new UserClientMap();
							userClientMap.setAutogenUsersDetailsId(new BigInteger(usersDetail.getAutogenUsersDetailsId()));
							userClientMap.setInventoryClientId(client.getId());
							userClientMap.setStatus("ACTIVE");
							userClientMap.setUpdatedBy(userDto.getUpdatedBy());	
							firstEntityManager.persist(userClientMap);
						}
						}
					}
				}
				

				result = true;
			}
		} catch (Exception e) {
			logger.info("Exception :: UserDAOImpl :: save() : " + e);
		} finally {
			firstEntityManager.close();
		}
		
		return result;
	}
	

}
