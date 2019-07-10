package com.ison.app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ison.app.constants.AppicationConstants;
import com.ison.app.dao.AbstractDao;
import com.ison.app.dao.GroupDAO;
import com.ison.app.message.request.UserGroupMappingRequest;
import com.ison.app.model.UserGroups;
import com.ison.app.shared.dto.UserGroupDto;
import com.ison.app.util.CommonUtil;

@Repository
public class GroupDAOImpl extends AbstractDao<Integer, UserGroups>  implements GroupDAO {

	@PersistenceContext(unitName = AppicationConstants.FIRST_PERSISTENCE_UNIT_NAME)
	public EntityManager firstEntityManager;

	@Autowired
	@Qualifier(AppicationConstants.FIRST_JDBC_TEMPLATE)
	JdbcTemplate firstJdbcTemplate;

	private Logger logger = Logger.getLogger(GroupDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public UserGroupDto findByGroupId(String groupId) throws Exception{
		UserGroupDto userGroupDto = new UserGroupDto();
		try {
			Criteria crit = createEntityCriteria();
			crit.add(Restrictions.eq("groupId", groupId));
			UserGroups userGroups = firstEntityManager.find(UserGroups.class, groupId);
			if(userGroups != null) {
				BeanUtils.copyProperties(userGroups, userGroupDto);
			}
		} catch (Exception e) {
			logger.info("Exception :: GroupDAOImpl :: findByUsername() : " + e);
		} finally {
			firstEntityManager.close();
		}
		return userGroupDto;
	}


	@Override
	@Transactional(value = "firstTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserGroupDto save(UserGroupDto userGroupDto) throws Exception{
		try {
			UserGroups userGroups = new UserGroups();
			BeanUtils.copyProperties(userGroupDto, userGroups);
			if(userGroups != null) {
				firstEntityManager.persist(userGroups);
			}
			if(userGroups != null) {
				BeanUtils.copyProperties(userGroups, userGroupDto);
			}
		} catch (Exception e) {
			logger.info("Exception :: GroupDAOImpl :: save() : " + e);
		} finally {
			firstEntityManager.close();
		}
		
		return userGroupDto;
	}

	@Override
	public List<UserGroupDto> getGroupsList(String status) throws Exception {

		List<UserGroupDto> userGroupDtoList = new ArrayList<>();
		List<UserGroups> result = new ArrayList<>();
		try {
			String condition = "";
			if(!status.isEmpty()) {
				condition = " WHERE ups.STATUS =:STATUS";
			}
			StringBuilder sqlQry = new StringBuilder("SELECT ugs FROM user_groups ups");
			Query queryObj = firstEntityManager.createQuery(sqlQry.toString());
			if(!status.isEmpty()) {
				queryObj.setParameter("STATUS", status);
			}
			result = (List<UserGroups>) queryObj.getResultList();
			if(result != null) {
				for (UserGroups obj : result) {
				UserGroupDto userGroupDto = new UserGroupDto();
				BeanUtils.copyProperties(obj, userGroupDto);
					userGroupDtoList.add(userGroupDto);
				}
			}
		} catch (Exception e) {
			logger.info("Exception :: GroupDAOImpl :: getGroupsList() : " + e);
		} finally {
			firstEntityManager.close();
		}
		
		return userGroupDtoList;
	}


	@Override
	@Transactional(value = "firstTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean UpDateGroup(UserGroupDto userGroupDto) throws Exception {
		boolean result = false;
		try {
			UserGroups userGroup = firstEntityManager.find(UserGroups.class, userGroupDto.getAutogenUserGroupsId());
			if(userGroup != null) {
				if(!CommonUtil.nullRemove(userGroupDto.getGroupName()).isEmpty()) {
					userGroup.setGroupName(userGroupDto.getGroupName());
				}
				if(!CommonUtil.nullRemove(userGroupDto.getGroupId()).isEmpty()) {
					userGroup.setGroupId(userGroupDto.getGroupId());
				}
				if(!CommonUtil.nullRemove(userGroupDto.getStatus()).isEmpty()){
					userGroup.setStatus(userGroupDto.getStatus());
				}
				firstEntityManager.merge(userGroup);
				result = true;
			}
		} catch (Exception e) {
			logger.info("Exception :: UserDAOImpl :: save() : " + e);
		} finally {
			firstEntityManager.close();
		}
		return result;
	}
	
	public String objNullCheck(Object obj) {
		return obj != null?String.valueOf(obj):"";
	}


	@Override
	@Transactional(value = "firstTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean userGroupMapping(UserGroupMappingRequest userGroupMappingRequest) throws Exception {
		List<Object[]> result = null;
		boolean profileFlag = false;
		try {
			StringBuilder sqlQry = new StringBuilder("SELECT * FROM user_group_profile where autogen_user_group_id=:GROUPID and autogen_user_id =:USERID");
			Query queryObj = firstEntityManager.createNativeQuery(sqlQry.toString());
			queryObj.setParameter("GROUPID", userGroupMappingRequest.getGroupId());
			queryObj.setParameter("USERID", userGroupMappingRequest.getUserId());
				result = (List<Object[]>) queryObj.getResultList();
				if(result == null || result.isEmpty()) {/*
					UserGroupProfile ugProfile = new UserGroupProfile();
					UserGroupProfilePK ugProfilePK = new UserGroupProfilePK();
					ugProfilePK.setAutogenUserGroupId(userGroupMappingRequest.getGroupId());
					ugProfilePK.setAutogenUserId(userGroupMappingRequest.getUserId());
					ugProfile.setId(ugProfilePK);
					ugProfile.setStatus("ACTIVE");
					firstEntityManager.persist(ugProfile);
				*/} else {/*
					UserGroupProfile ugProfile = new UserGroupProfile();
					UserGroupProfilePK ugProfilePK = new UserGroupProfilePK();
					ugProfilePK.setAutogenUserGroupId(userGroupMappingRequest.getGroupId());
					ugProfilePK.setAutogenUserId(userGroupMappingRequest.getUserId());
					ugProfile.setId(ugProfilePK);
					ugProfile.setStatus(userGroupMappingRequest.getStatus());
					firstEntityManager.merge(ugProfile);
				*/}
		profileFlag = true;
		} catch (Exception e) {
			logger.info("Exception :: GroupDAOImpl :: findByUsername() : " + e);
		} finally {
			firstEntityManager.close();
		}
		return profileFlag;
	}
	
	@Override
	public List<UserGroupDto> getActiveUserGroupsList() throws Exception {

		List<UserGroupDto> userGroupDtoList = new ArrayList<>();
		List<Object[]> result = new ArrayList<>();
		try {
			StringBuilder sqlQry = new StringBuilder("SELECT AUTOGEN_USER_GROUP_ID, GROUP_ID, NAME, DESCRIPTION, STATUS FROM USERMANAGEMENTDB.USER_GROUP WHERE STATUS='ACTIVE' AND AUTOGEN_USER_GROUP_ID IN(SELECT AUTOGEN_USER_GROUP_ID FROM USERMANAGEMENTDB.USER_GROUP_PROFILE WHERE AUTOGEN_USER_ID ='1' AND STATUS='ACTIVE'");
			Query queryObj = firstEntityManager.createNativeQuery(sqlQry.toString());
				result = (List<Object[]>) queryObj.getResultList();
			if(result != null) {
				for (Object[] obj : result) {/*
				UserGroupDto userGroupDto = new UserGroupDto();
				userGroupDto.setAutogenUserGroupId(Integer.parseInt(objNullCheck(obj[0])));
				userGroupDto.setDescription(objNullCheck(obj[3]));
				userGroupDto.setGroupId(objNullCheck(obj[1]));
				userGroupDto.setName(objNullCheck(obj[2]));
				userGroupDto.setStatus(objNullCheck(obj[4]));
					userGroupDtoList.add(userGroupDto);
				*/}
				
			}
		} catch (Exception e) {
			logger.info("Exception :: GroupDAOImpl :: save() : " + e);
		} finally {
			firstEntityManager.close();
		}
		
		return userGroupDtoList;
	}

	
}
