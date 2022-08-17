/*
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RoleMangementDAO.java
 * Author      :   Neesha Aggarwal
 * Description :   Represents the mapped resources for ACL Module.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Neesha Aggarwal  25th MArch,2008 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.ACL.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.ACL.dto.GroupsUserDTO;
import com.wipro.igrs.ACL.dto.RoleCreationDTO;
import com.wipro.igrs.UserRegistration.sql.CommonSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;

/**
 * @author neegaga
 * 
 */
@SuppressWarnings({"rawtypes","unused"})
public class RoleMangementDAO {
	DBUtility dbUtil;
	ArrayList list = null;
	IGRSCommon  igrsCommon = null;
	private Logger logger = (Logger) Logger.getLogger(RoleMangementDAO.class);

	/**
	 * @throws Exception
	 */
	public RoleMangementDAO() throws Exception {

		dbUtil = new DBUtility();
	}

	public boolean insertRolDetails(String[] param, String roleId, String funId, String userId) throws Exception {
		boolean roleDetailsSubmit = false;

		try {
			igrsCommon = new IGRSCommon();
			dbUtil.getDBConnection();
			dbUtil.createPreparedStatement(CommonSQL.INSERT_ROLES);
			roleDetailsSubmit = dbUtil.executeUpdate(param);
			logger.debug("After inserting role details ");
			if (roleDetailsSubmit) {
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_ROLE_MASTER","INSERT","T",funId,userId,roleId);
			}

			logger.debug("Role Credential Details Inserted ");
		} catch (Exception x) {
			logger.error("Exception in insertRolDetails() :- " + x);
			dbUtil.rollback();
			 igrsCommon.saveLogDet("IGRS_ROLE_MASTER","INSERT","F",funId,userId,roleId);
		} finally {
			try {
				if (!roleDetailsSubmit) {
					dbUtil.rollback();
				}
			} catch (Exception ex) {
				logger.error("Exception in insertRolDetails() :-" + ex);
			}	finally
			{
				dbUtil.closeConnection();
			}
		}
		return roleDetailsSubmit;
	}

	/**
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * @param genPinparam
	 * @return
	 */
	public boolean updateRoleDetails(String[] param, String criteria, String roleId, String funId, String userId) {
		boolean updated = true;

		try {
			igrsCommon = new IGRSCommon();
			logger.debug("Before updating role master details ");
			dbUtil.getDBConnection();
			dbUtil
					.createPreparedStatement(CommonSQL.UPDATE_ROLE_MASTER_DETAILS);

			String param1[] = new String[5];
			param1[0] = param[0];
			param1[1] = param[1];
			param1[2] = param[2];
			param1[3] = param[3];
			param1[4] =criteria;// param[4];
			//param1[5] = criteria;
			logger.debug("SQL:" + CommonSQL.UPDATE_ROLE_MASTER_DETAILS);
			updated = dbUtil.executeUpdate(param1);

			logger.debug("After updating rle master details ");
			if(updated)
			{
			dbUtil.commit();
			igrsCommon.saveLogDet("IGRS_ROLE_MASTER","UPDATE","T",funId,userId,roleId);
			}
			else
			{
				dbUtil.rollback();
				igrsCommon.saveLogDet("IGRS_ROLE_MASTER","UPDATE","F",funId,userId,roleId);
			}
			logger.debug("Role Master Details updated ");

		} catch (Exception x) {
			logger.debug("Exception in updateRoleDetails() :- " + x);
		}	finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return updated;
	}

	/**
	 * @return
	 */
	public ArrayList extractStoredRoles(String status1, String status2) {

		try {
			dbUtil.getDBConnection();
			dbUtil.createPreparedStatement(CommonSQL.SEARCH_ROLES_DETAILS);
			logger.debug("SQL:" + CommonSQL.SEARCH_ROLES_DETAILS);

			String param[] = new String[2];
			param[0] = status1;
			param[1] = status2;
			list = dbUtil.executeQuery(param);
			logger.debug("After getting Role Details... ");
		} catch (Exception x) {
			logger.error("Exception in extractStoredRoles() :- " + x);
		}	finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList extractStoredRoles(String pStatus) {

		try {
			dbUtil.getDBConnection();
			dbUtil.createPreparedStatement(CommonSQL.SEARCH_PENDING_ROLES_DETAILS);
			logger.debug("SQL:" + CommonSQL.SEARCH_PENDING_ROLES_DETAILS);
			String param[] = new String[1];
			param[0] = pStatus;
			list = dbUtil.executeQuery(param);
			logger.debug("After getting Role Details... ");
		} catch (Exception x) {
			logger.error("Exception in extractStoredRoles() :- " + x);
		}	finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	

	/**
	 * @return
	 */
	public ArrayList extractRoleDetail(String role) {

		try {
			dbUtil.getDBConnection();
			dbUtil.createPreparedStatement(CommonSQL.SEARCH_ROLE_DETAILS);
			logger.debug("SQL:" + CommonSQL.SEARCH_ROLE_DETAILS);

			String param[] = new String[1];
			param[0] = role;
			list = dbUtil.executeQuery(param);
			logger
					.debug("After getting the Role Detail for the selected role... ");
		} catch (Exception x) {
			logger.error("Exception in extractRoleDetail() :- " + x);
		}	finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * ******************************ACL-
	 * Group**********************************
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 */

	public boolean insertGroups(String[] param, String roleId, String funId, String userId) throws Exception {
		boolean grpSubmit = false;

		try {
			dbUtil.getDBConnection();
			igrsCommon = new IGRSCommon();
			logger.debug("Before inserting user details ");
			dbUtil.createPreparedStatement(CommonSQL.INSERT_GROUPS);
			grpSubmit = dbUtil.executeUpdate(param);
			if (grpSubmit) {
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_ROLE_GROUP_MASTER","INSERT","T",funId,userId,roleId);
			}

			logger.debug("Role Credential Details Inserted ");
		} catch (Exception x) {
			logger.error("Exception in insertGroups() :- " + x);
			dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_ROLE_GROUP_MASTER","INSERT","F",funId,userId,roleId);
		} finally {
			try {
				if (!grpSubmit) {
					dbUtil.rollback();
					igrsCommon.saveLogDet("IGRS_ROLE_GROUP_MASTER","INSERT","F",funId,userId,roleId);
				}
			} catch (Exception ex) {
				logger.error("Exception in insertGroups() :-" + ex);
			}
			dbUtil.closeConnection();
		}
		return grpSubmit;
	}

	/**
	 * @param param
	 * @return
	 */
	public boolean updateGroupDetails(String[] param ,String roleId, String funId, String userId) {
		boolean updated = true;

		try {
			igrsCommon = new IGRSCommon();
			dbUtil.getDBConnection();
			dbUtil
					.createPreparedStatement(CommonSQL.UPDATE_ROLE_GRP_MASTER_DETLS);

			String param1[] = new String[5];
			param1[0] = param[0];
			param1[1] = param[1];
			param1[2] = param[2];
			param1[3] = param[3];
			param1[4] = param[4];
			updated = dbUtil.executeUpdate(param1);
			
			dbUtil.commit();
			//igrsCommon.saveLogDet("IGRS_ROLE_GROUP_MASTER","UPDATE","T",funId,userId,roleId);

			logger.debug("Role Group Master Details updated ");

		} catch (Exception x) {
			logger.debug("Exception in updateGroupDetails() :- " + x);
			updated=false;
			//igrsCommon.saveLogDet("IGRS_ROLE_GROUP_MASTER","UPDATE","F",funId,userId,roleId);
		}	finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return updated;
	}

	/**
	 * @return
	 */
	public ArrayList extractGrpDetail(String groupID) {

		try {
			dbUtil.getDBConnection();
			dbUtil.createPreparedStatement(CommonSQL.SEARCH_GRP_DETAILS);
			logger.debug("SQL:" + CommonSQL.SEARCH_GRP_DETAILS);

			String param[] = new String[1];
			param[0] = groupID;
			list = dbUtil.executeQuery(param);
			logger
					.debug("After getting the Group Detail for the selected groupID... ");
		} catch (Exception x) {
			logger.error("Exception in extractGrpDetail() :- " + x);
		}	finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @return
	 */
	public ArrayList getStoredGroups(String status1, String status2) {
		try {
			dbUtil.getDBConnection();
			dbUtil.createPreparedStatement(CommonSQL.SEARCH_GROUPS_DETAILS);
			logger.debug("SQL:" + CommonSQL.SEARCH_GROUPS_DETAILS);

			String param[] = new String[2];
			param[0] = status1;
			param[1] = status2;
			list = dbUtil.executeQuery(param);
			logger.debug("After getting Role Details... ");
		} catch (Exception x) {
			logger.error("Exception in getStoredGroups() :- " + x);
		}	finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList getStoredGroups(String pStatus) {
		try {
			dbUtil.getDBConnection();
			dbUtil.createPreparedStatement(CommonSQL.SEARCH_PENDING_GROUPS_DETAILS);
			logger.debug("SQL:" + CommonSQL.SEARCH_PENDING_GROUPS_DETAILS);

			String param[] = new String[1];
			param[0] = pStatus;
			
			list = dbUtil.executeQuery(param);
			logger.debug("After getting Role Details... ");
		} catch (Exception x) {
			logger.error("Exception in getStoredGroups() :- " + x);
		}	finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * ****************************************Activity
	 * PArt**************************
	 */

	/**
	 * @return ArrayList
	 */
	public ArrayList extractRoles(String status1) {

		try {
			dbUtil.getDBConnection();
			dbUtil.createPreparedStatement(CommonSQL.SEARCH_ACTIVE_ROLES);
			logger.debug("SQL:" + CommonSQL.SEARCH_ACTIVE_ROLES);

			String param[] = new String[1];
			param[0] = status1;

			list = dbUtil.executeQuery(param);
			logger.debug("After getting Roles List... ");
		} catch (Exception x) {
			logger.error("Exception in extractRoles() :- " + x);
		}	finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public boolean insertDetails(String aId, String rName, String roleId, String funId, String userId) throws Exception {
		boolean grpSubmit = false;
		String rid = new String();
		try {
			igrsCommon = new IGRSCommon();
			String param1[] = new String[1];
			param1[0] = rName;
			dbUtil.getDBConnection();
			dbUtil
					.createPreparedStatement("Select ROLE_ID, ROLE_NAME from IGRS_ROLE_MASTER WHERE ROLE_NAME = ? ");
			list = dbUtil.executeQuery(param1);

			logger.debug(" after execute query");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					ArrayList lst = (ArrayList) list.get(i);
					rid = (String) lst.get(0);
					logger.debug("Value of role id : " + rid);
				}
			}

			logger.debug("Before inserting user details ");
			dbUtil
					.createPreparedStatement(CommonSQL.INSERT_ROLE_ACTIVITY_MAPPING);
			String param[] = new String[2];
			param[0] = rid;
			param[1] = aId;
			grpSubmit = dbUtil.executeUpdate(param);

			if (grpSubmit) {
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_ROLE_FN_ACTIVITY_MAPPING","INSERT","T",funId,userId,roleId);
			}

			logger.debug("Role Activity Details Inserted ");
		} catch (Exception x) {
			logger.error("Exception in insertDetails() :- " + x);
			dbUtil.rollback();
		} finally {
			try {
				if (!grpSubmit) {
					dbUtil.rollback();
					igrsCommon.saveLogDet("IGRS_ROLE_FN_ACTIVITY_MAPPING","INSERT","T",funId,userId,roleId);
				}
			} catch (Exception ex) {
				logger.error("Exception in insertDetails() :-" + ex);
			}
			dbUtil.closeConnection();
		}
		return grpSubmit;
	}

	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList extractActivities() throws Exception {
		try{
			dbUtil.getDBConnection();
		dbUtil.createStatement();
		list = dbUtil
				.executeQuery("Select ACTIVITY_ID, ACTIVITY_NAME from IGRS_ACTIVITY_MASTER where ACTIVITY_STATUS='A' ");
		logger
				.info("Wipro in IGRSCommon - extractActivities() after execute query");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
				dbUtil.closeConnection();
		
		}
		return list;
	}

	/**
	 * ***********************************RolesGroup
	 * PArt***************************************
	 */

	/**
	 * @return ArrayList
	 */
	public ArrayList extractGroups(String status1) {
		try {
			dbUtil.getDBConnection();
			dbUtil.createPreparedStatement(CommonSQL.SEARCH_ACTIVE_ROLEGROUPS);
			logger.debug("SQL:" + CommonSQL.SEARCH_ACTIVE_ROLEGROUPS);

			String param[] = new String[1];
			param[0] = status1;

			list = dbUtil.executeQuery(param);
			logger.debug("After getting Roles Group List... ");
		} catch (Exception x) {
			logger.error("Exception in extractGroups() :- " + x);
		}finally {
			
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
		return list;
	}

	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList extractRoles() throws Exception {
		try{
			dbUtil.getDBConnection();
		dbUtil.createStatement();
		list = dbUtil
				.executeQuery(" select   ROLE_ID,ROLE_NAME FROM  IGRS_ROLE_MASTER   where ROLE_STATUS='A' ");
		logger
				.info("Wipro in IGRSCommon - extractActivities() after execute query");
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		
			dbUtil.closeConnection();
	
	}
		return list;
	}
	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList extractRolesNames() throws Exception {
		try{dbUtil.getDBConnection();
		dbUtil.createStatement();
		list = dbUtil
				.executeQuery("SELECT ROLE_NAME FROM IGRS_ROLE_MASTER");
		logger
				.info("Wipro in IGRSCommon - extractRolesNames() after execute query");
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		
			dbUtil.closeConnection();
	
	}
		return list;
	}
	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList extractNewRoles() throws Exception {
		try{
			dbUtil.getDBConnection();
		dbUtil.createStatement();
		list = dbUtil
				.executeQuery(" select   roloff.ROLE_OFFICE_MAP_ID,UPPER(rol.ROLE_NAME)||' - '||offc.OFFICE_NAME" +
						" from IGRS_ROLE_OFFICE_MAPPING roloff, IGRS_ROLE_MASTER rol ,IGRS_OFFICE_MASTER offc "+
                       " where roloff.role_id=rol.role_id and offc.office_id=roloff.office_id and rol.role_status='A' AND roloff.STATUS='A' ORDER BY UPPER(rol.ROLE_NAME)");
		//AND roloff.STATUS='A' ADDED BY ROOPAM-10MARCH2015 IN ABOVE QUERY
		logger
				.info("Wipro in IGRSCommon - extractActivities() after execute query");
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		
			dbUtil.closeConnection();
	
	}
		return list;
	}
	
	public boolean updateRGDetails(String grpName) throws Exception {
		boolean grpSubmit = false;
		String grpid = new String();
		try {
			dbUtil.getDBConnection();
			dbUtil.setAutoCommit(false);
			
			String param1[] = new String[1];
			param1[0] = grpName;
			dbUtil
					.createPreparedStatement("Select ROLE_GROUP_ID, ROLE_GROUP_NAME from IGRS_ROLE_GROUP_MASTER WHERE ROLE_GROUP_NAME = ? ");
			list = dbUtil.executeQuery(param1);

			
			
			logger.debug(" after execute query");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					ArrayList lst = (ArrayList) list.get(i);
					grpid = (String) lst.get(0);
					logger.debug("Value of role id : " + grpid);
				}
			}
			
			param1[0]=grpid;
			try{
			logger.debug("Before UPDATE GROUP USER  details ");
    		//String detailsInsert="UPDATE IGRS_RGROUP_ROLE_MAPPING SET STATUS= 'D' WHERE ROLE_GROUP_ID=? and STATUS='A'";
			String detailsInsert="DELETE FROM IGRS_RGROUP_ROLE_MAPPING WHERE  ROLE_GROUP_ID=? and STATUS='A'";
    		dbUtil.createPreparedStatement(detailsInsert);
    		grpSubmit=	dbUtil.executeUpdate(param1);
    		logger.debug("After UPDATE GROUP USER  details "+grpSubmit);
			}catch(Exception  e)
			{
				logger.debug("Exception "+e);
			}
			
			logger.debug("Role-Group Details Inserted ");
		} catch (Exception x) {
			logger.error("Exception in UPDATERGDetails() :- " + x);
			dbUtil.rollback();
		} finally {
			try {
				if (!grpSubmit) {
					dbUtil.rollback();
				}
			} catch (Exception ex) {
				logger.error("Exception in UPDATERGDetails() :-" + ex);
			}
			dbUtil.closeConnection();
		}
		return grpSubmit;
	}
	
	public boolean insertRGDetails(String aId, String grpName) throws Exception {
		boolean grpSubmit = false;
		String grpid = new String();
		try {
			dbUtil.getDBConnection();
			dbUtil.setAutoCommit(false);
			String param1[] = new String[1];
			param1[0] = grpName;
			dbUtil
					.createPreparedStatement("Select ROLE_GROUP_ID, ROLE_GROUP_NAME from IGRS_ROLE_GROUP_MASTER WHERE ROLE_GROUP_NAME = ? ");
			list = dbUtil.executeQuery(param1);

			
			
			logger.debug(" after execute query");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					ArrayList lst = (ArrayList) list.get(i);
					grpid = (String) lst.get(0);
					logger.debug("Value of role id : " + grpid);
				}
			}
			
					
			logger.debug("Before inserting user details ");
			dbUtil.createPreparedStatement(CommonSQL.INSERT_GRP_ROLE_MAPPING);
			String param[] = new String[2];
			param[0] = grpid;
			param[1] = aId;
			grpSubmit = dbUtil.executeUpdate(param);

			if (grpSubmit) {
				dbUtil.commit();
			}

			logger.debug("Role-Group Details Inserted ");
		} catch (Exception x) {
			logger.error("Exception in insertRGDetails() :- " + x);
			dbUtil.rollback();
		} finally {
			try {
				if (!grpSubmit) {
					dbUtil.rollback();
				}
			} catch (Exception ex) {
				logger.error("Exception in insertRGDetails() :-" + ex);
			}
			dbUtil.closeConnection();
		}
		return grpSubmit;
	}

	/**
	 * **************************************Group User
	 * Part******************************
	 * @param userId2 
	 * @param funId 
	 * @param roleId 
	 */
	public boolean insertGrpUserDetails(String userId, String grpId, String roleId, String funId, String userId2)
			throws Exception {
		boolean grpSubmit = false;
		boolean ifmappedDelete=false;
		String grpid = new String();
		try {
			
			//isMappingExist(userId);
			//deleteMapping(userId);
			igrsCommon = new IGRSCommon();
			logger.debug("Before inserting user details ");
			dbUtil.getDBConnection();
			dbUtil.createPreparedStatement(CommonSQL.INSERT_GRP_USER_MAPPING);
			String param[] = new String[2];
			param[0] = userId;
			param[1] = grpId;
			grpSubmit = dbUtil.executeUpdate(new String[]{userId, grpId, "A"});
			if (grpSubmit) {
				dbUtil.commit();
				 igrsCommon.saveLogDet("IGRS_USER_ROLE_GROUP_MAPPING","INSERT","T",funId,userId,roleId);
			}
			logger.debug("Grp User Details Inserted ");
		} catch (Exception x) {
			logger.error("Exception in insertGrpUserDetails() :- " + x);
			dbUtil.rollback();
			 igrsCommon.saveLogDet("IGRS_USER_ROLE_GROUP_MAPPING","INSERT","F",funId,userId,roleId);
		} finally {
			try {
				if (!grpSubmit) {
					dbUtil.rollback();
					 igrsCommon.saveLogDet("IGRS_USER_ROLE_GROUP_MAPPING","INSERT","F",funId,userId,roleId);
				}
			} catch (Exception ex) {
				logger.error("Exception in insertGrpUserDetails() :-" + ex);
			}
			dbUtil.closeConnection();
		}
		return grpSubmit;
	}

	/** *****************************User Login**************************** */

	/**
	 * @param genPinparam
	 * @return
	 */
	public boolean authenticateUser(String[] param) {
		boolean searched = true;

		try {
			logger
					.info("Before searching the User Details for the User Logging In ");
			dbUtil.getDBConnection();
			dbUtil.createPreparedStatement(CommonSQL.SEARCH_USER_DETAILS);
			logger.debug("SQL:" + CommonSQL.SEARCH_USER_DETAILS);
			searched = dbUtil.executeUpdate(param);

			logger
					.info("After searching the User Details for the User Logging In");
			dbUtil.commit();

		} catch (Exception x) {
			logger.debug("Exception in authenticateUser() :- " + x);
		}finally {
		
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
		return searched;
	}

	/**
	 * @return
	 */
	public ArrayList getUserGroup(String usrId) {
		try {dbUtil.getDBConnection();
			dbUtil.createPreparedStatement(CommonSQL.SEARCH_USER_GROUP);
			logger.debug("SQL:" + CommonSQL.SEARCH_USER_GROUP);
			String[] param = new String[1];
			param[0] = usrId;
			list = dbUtil.executeQuery(param);
			dbUtil.setAutoCommit(false);
			logger
					.debug("After getting the Group Id for the logged In User... ");
		} catch (Exception x) {
			logger.error("Exception in getUserGroup() :- " + x);
		}finally {
		
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
		return list;
	}

	/**
	 * @return
	 */
	public ArrayList getGrpRole(String grpId) {
		try {dbUtil.getDBConnection();
			dbUtil.createPreparedStatement(CommonSQL.SEARCH_ROLE_GROUP);
			logger.debug("SQL:" + CommonSQL.SEARCH_ROLE_GROUP);
			String[] param = new String[1];
			param[0] = grpId;
			list = dbUtil.executeQuery(param);
			logger
					.debug("After getting the Role Id and Role NAme for the logged In User... ");
		} catch (Exception x) {
			logger.error("Exception in getGrpRole() :- " + x);
		}finally {
		
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
		return list;
	}

	/**
	 * @return
	 */
	public ArrayList getRoleActivity(String roleId, String grpID) {
		try {dbUtil.getDBConnection();
			dbUtil.createPreparedStatement(CommonSQL.SEARCH_ROLE_ACTIVITY);
			logger.debug("SQL:" + CommonSQL.SEARCH_ROLE_ACTIVITY);
			String[] param = new String[2];
			param[0] = roleId;
			param[1] = grpID;
			list = dbUtil.executeQuery(param);
			logger
					.debug("After getting the Activity ID and Activity NAme for the logged In User... ");
		} catch (Exception x) {
			logger.error("Exception in getRoleActivity() :- " + x);
		}finally {
		
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
		return list;
	}

	/**
	 * @return
	 */
	public ArrayList getRoleModule(String roleId, String gID) {
		try {dbUtil.getDBConnection();
			dbUtil.createPreparedStatement(CommonSQL.SEARCH_ROLE_MODULE);
			logger.debug("SQL:" + CommonSQL.SEARCH_ROLE_MODULE);
			String[] param = new String[2];
			param[0] = roleId;
			param[1] = gID;
			list = dbUtil.executeQuery(param);
			logger
					.debug("After getting the Module ID and Module NAme for the logged In User... ");
		} catch (Exception x) {
			logger.error("Exception in getRoleModule() :- " + x);
		}finally {
		
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
		return list;
	}

	/**
	 * @return
	 */
	public ArrayList getSubModule(String modId, String roleId, String gID) {
		try {dbUtil.getDBConnection();
			dbUtil.createPreparedStatement(CommonSQL.SEARCH_SUB_MODULE);
			logger.debug("SQL:" + CommonSQL.SEARCH_SUB_MODULE);
			String[] param = new String[3];
			param[0] = modId;
			param[1] = roleId;
			param[2] = gID;
			list = dbUtil.executeQuery(param);
			logger
					.debug("After getting the Submodule ID and Sub-Module NAme for the logged In User... ");
		} catch (Exception x) {
			logger.error("Exception in getSubModule() :- " + x);
		}finally {
		
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
		return list;
	}

	/**
	 * @return
	 */
	public ArrayList getFunction(String smodId, String roleId, String gID) {
		try {dbUtil.getDBConnection();
			dbUtil.createPreparedStatement(CommonSQL.SEARCH_FUNCTION);
			logger.debug("SQL:" + CommonSQL.SEARCH_FUNCTION);
			String[] param = new String[3];
			param[0] = smodId;
			param[1] = roleId;
			param[2] = gID;
			list = dbUtil.executeQuery(param);
			logger
					.debug("After getting the FunctionId and the Function Name for the logged In User... ");
		} catch (Exception x) {
			logger.error("Exception in getFunction() :- " + x);
		}finally {
		
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
		return list;
	}
	
	public ArrayList checkEmployeeAvail(String empID){
		boolean ispresent=false;
		try {
			dbUtil.getDBConnection();
			dbUtil.createPreparedStatement(CommonSQL.IS_EMP_AVAIL);
			logger.debug("SQL:" + CommonSQL.IS_EMP_AVAIL);
			String[] param = new String[1];
			param[0] = empID;
			list = dbUtil.executeQuery(param);
			logger
					.debug("After getting the FunctionId and the Function Name for the logged In User... ");
		} catch (Exception x) {
			logger.error("Exception in checkEMployee Avail() :- " + x);
		}finally {
		
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
		
		
		return list;
		
	}
	
	public boolean isMappingExist(String empID){
		boolean ispresent=false;
		try {
			dbUtil.getDBConnection();
//			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(CommonSQL.IS_EMP_MAPPED);
			logger.debug("SQL:" + CommonSQL.IS_EMP_MAPPED);
			String[] param = new String[1];
			param[0] = empID;
			ArrayList data = dbUtil.executeQuery(param);
			if(data.size() > 0) {
				ArrayList row = (ArrayList) data.get(0);
				String count = (String) row.get(0);
				if ("0".equals(count )){	
					ispresent=false;
				}
				else {
					ispresent=true;
				}	
			}
			
			logger.debug("Is EMP Mapped.... " + ispresent);
		} catch (Exception x) {
			logger.error("Exception in isMappingExists :- " + x);
		}finally {
		
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
		
		return ispresent;
		
	}
	
	public boolean deleteMapping(String empID){
		boolean ispresent=false;
		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(CommonSQL.DLT_EMP_MAPPED);
			logger.debug("SQL:" + CommonSQL.DLT_EMP_MAPPED);
			String[] param = new String[1];
			param[0] = empID;
			ispresent=dbUtil.executeUpdate(param);
			logger
					.debug("deleteMapping... ");
			ispresent=true;
			
		} catch (Exception x) {
			ispresent=false;
			logger.error("Exception in deleteMapping :- " + x);
		}finally {
		
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
		return ispresent;
	}

	public boolean insertUserGroupData(String userId, ArrayList menuArray,
			String roleId, String funId, String loggedInUser) {
		boolean retVal = false;
		try {
			DBUtility util = new DBUtility();
			util.setAutoCommit(false);
			util.createPreparedStatement(CommonSQL.IS_EMP_MAPPED);
			String count = util.executeQry(new String[]{userId});
			if("0".equals(count)==false) {
				util.createPreparedStatement(CommonSQL.DLT_EMP_MAPPED);
				util.executeUpdate(new String[]{userId});
			}
			util.createPreparedStatement(CommonSQL.INSERT_GRP_USER_MAPPING);
			for (Object item : menuArray) {
				GroupsUserDTO gdto = (GroupsUserDTO) item;
				if("Y".equalsIgnoreCase(gdto.getSlctFlag())) {
					retVal = util.executeUpdate(new String[]{userId, gdto.getGroupId(),"A"});
					//userId, gdto.getGroupId(),roleId,funId,loggedInUser
					if(retVal) {
						util.commit();
					}else {
						util.rollback();
					}
					
				}
				util.closeConnection();
				break;
			}
			igrsCommon = new IGRSCommon();
			util = new DBUtility();
			if(retVal) {
				retVal = igrsCommon.saveLogDet("IGRS_USER_ROLE_GROUP_MAPPING","INSERT","T",funId,loggedInUser,roleId);
			}else {
				retVal = igrsCommon.saveLogDet("IGRS_USER_ROLE_GROUP_MAPPING","INSERT","F",funId,loggedInUser,roleId);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
		
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
		return retVal;
		
	}
	
	
	public ArrayList getRoleMapped(String grpId) {
		try {
			dbUtil.getDBConnection();
			dbUtil.createPreparedStatement(CommonSQL.ROLES_UNDER_GROUP);
			logger.debug("SQL:" + CommonSQL.ROLES_UNDER_GROUP);
			String[] param = new String[1];
			param[0] = grpId;
			list = dbUtil.executeQuery(param);
			logger
					.debug("After getting the Role Id and Role NAme for the logged In User... ");
		} catch (Exception x) {
			logger.error("Exception in getGrpRole() :- " + x);
		}finally {
		
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
		return list;
	}
	
	public String getRoleGrpId(String grpName) {
		String roleGrpID="";
		try {
			dbUtil.getDBConnection();
			dbUtil.createPreparedStatement(CommonSQL.SEARCH_ROLE_GROUP_ID);
			logger.debug("SQL:" + CommonSQL.SEARCH_ROLE_GROUP_ID);
			String[] param = new String[1];
			param[0] = grpName;
			roleGrpID = dbUtil.executeQry(new String[]{grpName});
			logger
					.debug("After getting the Group ID ... " + roleGrpID);
		} catch (Exception x) {
			logger.error("Exception in getGrpRole() :- " + x);
		}finally {
		
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
		return roleGrpID;
	}
	
	public String getUserRoleGrpMapping(String userId){
		DBUtility util;
		String roleGrpId="";
		try {
			util = new DBUtility();
			util.createPreparedStatement(CommonSQL.ROLE_GROUP_UNDER_USER);
			roleGrpId = util.executeQry(new String[]{userId});
			logger
			.debug("Group Id assigned to user is: " + roleGrpId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
		return roleGrpId;	
	}

	public String activitycount(String roleid) {
		String roleGrpID="";
		try {
			dbUtil.getDBConnection();
			logger
			.debug("ROLE  id ... " + roleid);
			dbUtil.createPreparedStatement(CommonSQL.ACTIVITY_COUNT_ROLE);
			logger.debug("SQL:" + CommonSQL.ACTIVITY_COUNT_ROLE);
			String[] param = new String[1];
			param[0] = roleid;
			roleGrpID = dbUtil.executeQry(new String[]{roleid});
			logger
					.debug("roleGrpID  ... " + roleid);
		} catch (Exception x) {
			logger.error("Exception in ACTIVITY_COUNT_ROLE() :- " + x);
		}finally {
		
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
		return roleGrpID;
	}
	
	public ArrayList getOfficeListing() {
		ArrayList retVal = null;
		DBUtility util;
		try {
			util = new DBUtility();
			try {
				util.createStatement();
				retVal = util.executeQuery(CommonSQL.GET_ALL_OFFICE_LIST);
				//retVal.clear();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				util.closeConnection();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}

	public boolean updateRole(RoleCreationDTO dtoObject, String funId,
			String userId, String loggedInRole) {
		boolean retVal = false;
		try {
			DBUtility util = new DBUtility();
			String[] offices = dtoObject.getRoleOfficeMapping();
			try {
				String roleName = dtoObject.getRoleName();
				String roleDesc = dtoObject.getRolDesc();
				String roleStatus = dtoObject.getStatus();
				String roleID = dtoObject.getRoleId();
				String[] params = new String[] {
					roleName,
					roleDesc,
					roleStatus,
					userId,
					roleID
				};
				util.setAutoCommit(false);
				String sqlQuery;
				sqlQuery = CommonSQL.UPDATE_ROLE_MASTER_DETAILS;
				util.createPreparedStatement(sqlQuery);
				util.executeUpdate(params);
				params = new String[] { roleID };
				
				
				
				//util.createPreparedStatement(CommonSQL.DELETE_ROLE_OFFICE_MAPPING);// STATEMENT COMMENTED BY ROOPAM-10MARCH2015
				//util.executeUpdate(params);										 // STATEMENT COMMENTED BY ROOPAM-10MARCH2015
				// MODIFICATION  BY ROOPAM-10MARCH2015
				String officeParam="update IGRS_ROLE_OFFICE_MAPPING set STATUS='D' where ROLE_ID='"+roleID+"' and OFFICE_ID not in (";
				if (offices != null) {
					
					for (int i=0;i<offices.length;i++) {
						
						//added by roopam
						String office = offices[i];
						if(i==(offices.length-1)){
						officeParam=officeParam+"?";
						}else{
							officeParam=officeParam+"?,";
						}
						params = new String[] { roleID, office};
						util.createPreparedStatement(CommonSQL.SELECT_ROLE_OFFICE_MAPPING);
						ArrayList idList=util.executeQuery(params);
						
						if(idList!=null && idList.size()>0)
						{
							//do nothing if entry already exists- re-use old entry
						}else{
						//else insert new entry in db
						util.createPreparedStatement(CommonSQL.INSERT_ROLE_OFFICE_MAPPING);
						params = new String[] { roleID, office, userId };
						util.executeUpdate(params);
						}
						
						
						
						
					}
					
					officeParam=officeParam+")";
					util.createPreparedStatement(officeParam);
					util.executeUpdate(offices);
					//de-activate all other mapping
					
					
					
					
				}
				//END OF MODIFICATION BY ROOPAM-10MARCH2015 
				util.commit();
				retVal = true;
			} catch (NullPointerException e) {
				util.rollback();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				util.rollback();
			} finally {
				util.closeConnection();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}

	public String[] getRoleOfficeMapping(String roleId) {
		String[] retVal = null;
		try {
			DBUtility util = new DBUtility();
			
			ArrayList data, row;
			try {
				util.createPreparedStatement(CommonSQL.GET_ROLE_OFFICE_MAPPING);
				data = util.executeQuery(new String[]{roleId});
				if(data != null && data.size() > 0) {
					retVal = new String[data.size()];
					for (int iLoop = 0; iLoop < data.size(); iLoop++) {
						row = (ArrayList) data.get(iLoop);
						retVal[iLoop] = (String) row.get(0);
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				util.closeConnection();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}

	public boolean deleteExistingEntries(String group) throws Exception {
		String grpid = new String();
		boolean flag=false;
			dbUtil.getDBConnection();
			String param1[] = new String[1];
			param1[0] = group;
			dbUtil.createPreparedStatement("Select ROLE_GROUP_ID, ROLE_GROUP_NAME from IGRS_ROLE_GROUP_MASTER WHERE ROLE_GROUP_NAME = ? ");
			list = dbUtil.executeQuery(param1);

			
			
			logger.debug(" after execute query");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					ArrayList lst = (ArrayList) list.get(i);
					grpid = (String) lst.get(0);
					logger.debug("Value of role id : " + grpid);
				}
				
				}
			String param[] = new String[1];
			param[0] = grpid;
			logger.debug("Before UPDATE GROUP USER  details ");
    		//String detailsInsert="UPDATE IGRS_RGROUP_ROLE_MAPPING SET STATUS= 'D' WHERE ROLE_GROUP_ID=? and STATUS='A'";
			String detailsInsert=CommonSQL.DELETE_GROUP_ENTIRES;
    		dbUtil.createPreparedStatement(detailsInsert);
    		flag=	dbUtil.executeUpdate(param);
    		dbUtil.commit();
    		dbUtil.commit();
    		logger.debug("After delete existing GROUP USER  details "+flag);
    		
		return flag;
	}

}
