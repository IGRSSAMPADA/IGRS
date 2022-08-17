/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserRGroupMappingDAO.java
 * Author      :   Hend M. ismail
 * Description :   Represents DAO Class for UserRoleGroup Mapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  4th September, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.userrolegroupmapping.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

 
import com.wipro.igrs.userrolegroupmapping.dto.UserRGroupMappingDTO;
import com.wipro.igrs.userrolegroupmapping.sql.UserRGroupMappingCommonSQL;
import com.wipro.igrs.db.DBUtility;

public class UserRGroupMappingDAO {

	private ArrayList userList = new ArrayList();
	private ArrayList roleGroupList = new ArrayList();
	private ArrayList userRGroupList = new ArrayList();

	
    DBUtility dbUtility = null;
    String sql = null;
    ArrayList subList = null;
    
    UserRGroupMappingDTO userRGroupDTO=null;
  

	private Logger logger = (Logger) Logger.getLogger(UserRGroupMappingDAO.class);

	/* DAO constructor */
	public UserRGroupMappingDAO() {

		 
	}

	
	/*GET USER ROLE MAPPING ID*/
	public UserRGroupMappingDTO getUserRGroupMappingId(String userRGroupId) throws Exception {
		try {
			sql = UserRGroupMappingCommonSQL.SELECT_USER_ROLE_GROUP_MAPPING_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = userRGroupId;
			ArrayList list = dbUtility.executeQuery(sd);
			ArrayList subList = (ArrayList) list.get(0);
			userRGroupDTO = new UserRGroupMappingDTO();
			userRGroupDTO.setUserRGroupId(subList.get(0).toString());
			userRGroupDTO.setUserId(subList.get(1).toString());
			userRGroupDTO.setUserName(subList.get(2).toString());
			userRGroupDTO.setRoleGroupId(subList.get(3).toString());
			userRGroupDTO.setRoleGroupName(subList.get(4).toString());

		} catch (Exception e) {
		//	e.getStackTrace();
			e.printStackTrace();
		}finally{
			dbUtility.closeConnection();
		}
		return userRGroupDTO;
	}
	
	/*GET USER ROLE MAPPING ID BY USER ID*/
	public ArrayList getUserRGroupMappingUserID(String userId) throws Exception {
		try {
			sql = UserRGroupMappingCommonSQL.SELECT_USER_ROLE_GROUP_MAPPING_USER_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = userId;
	        ArrayList mainList1= dbUtility.executeQuery(sd);
	        for (int i=0;i<mainList1.size();i++) 
	         {
	         subList = (ArrayList)mainList1.get(i);
	         userRGroupDTO = new UserRGroupMappingDTO();
	         userRGroupDTO.setUserRGroupId(subList.get(0).toString());
	         userRGroupDTO.setUserId(subList.get(1).toString());
	         userRGroupDTO.setUserName(subList.get(2).toString());
	         userRGroupDTO.setRoleGroupId(subList.get(3).toString());
	         userRGroupDTO.setRoleGroupName(subList.get(4).toString());
	         
	         userRGroupList.add(userRGroupDTO);
	         //dbUtility.commit();
	         }
		} catch (Exception e) {
		//	e.getStackTrace();
			e.printStackTrace();
		}finally{
			dbUtility.closeConnection();
		}
		return userRGroupList;
	}
	
	/*GET USER ROLE MAPPING BY Role ID*/
	public ArrayList getUserRGroupMappingRoleID(String userId) throws Exception {
		try {
			sql = UserRGroupMappingCommonSQL.SELECT_USER_ROLE_GROUP_MAPPING_Role_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = userId;
	        ArrayList mainList1= dbUtility.executeQuery(sd);
	        for (int i=0;i<mainList1.size();i++) 
	         {
	         subList = (ArrayList)mainList1.get(i);
	         userRGroupDTO = new UserRGroupMappingDTO();
	         userRGroupDTO.setUserRGroupId(subList.get(0).toString());
	         userRGroupDTO.setUserId(subList.get(1).toString());
	         userRGroupDTO.setUserName(subList.get(2).toString());
	         userRGroupDTO.setRoleGroupId(subList.get(3).toString());
	         userRGroupDTO.setRoleGroupName(subList.get(4).toString());
	         
	         userRGroupList.add(userRGroupDTO);
	         //dbUtility.commit();
	         }
		} catch (Exception e) {
		//	e.getStackTrace();
			e.printStackTrace();
		}finally{
			dbUtility.closeConnection();
		}
		return userRGroupList;
	}
	/* GET EXP HEAD MAPPING LIST  */
	public ArrayList getUserRGroupList()throws Exception
	{
		try {
			dbUtility = new DBUtility();
	        sql = UserRGroupMappingCommonSQL.SELECT_USER_ROLE_GROUP_MAPPING_JOIN;
	        dbUtility.createStatement();
	       
	        ArrayList mainList1= dbUtility.executeQuery(sql);
	        for (int i=0;i<mainList1.size();i++) 
	         {
	         subList = (ArrayList)mainList1.get(i);
	         userRGroupDTO = new UserRGroupMappingDTO();
	         userRGroupDTO.setUserRGroupId(subList.get(0).toString());
	         userRGroupDTO.setUserId(subList.get(1).toString());
	         userRGroupDTO.setUserName(subList.get(2).toString());
	         userRGroupDTO.setRoleGroupId(subList.get(3).toString());
	         userRGroupDTO.setRoleGroupName(subList.get(4).toString());
	         
	         userRGroupList.add(userRGroupDTO);
	         //dbUtility.commit();
	         }
	        } catch (Exception e) {
	         // e.getStackTrace();
	        	e.printStackTrace();
	        }finally {
	        	dbUtility.closeConnection();
	        }
	    return userRGroupList;
		
	}
	
	/* GET USER ROLE GROUP LIST MASTER */
	public ArrayList getRoleGroupList()throws Exception
	{
		try {
			dbUtility = new DBUtility();
	        sql = UserRGroupMappingCommonSQL.SELECT_ROLE_GROUP_MASTER;
	        dbUtility.createStatement();
	       
	        ArrayList mainList1= dbUtility.executeQuery(sql);
	        for (int i=0;i<mainList1.size();i++) 
	         {
	         subList = (ArrayList)mainList1.get(i);
	         userRGroupDTO = new UserRGroupMappingDTO();
	         userRGroupDTO.setRoleGroupId(subList.get(0).toString());
	         userRGroupDTO.setRoleGroupName(subList.get(1).toString());
	
	         
	         roleGroupList.add(userRGroupDTO);
	         //dbUtility.commit();
	         }
	        } catch (Exception e) {
	          e.getStackTrace();
	        }finally {
	        	dbUtility.closeConnection();
	        }
	    return roleGroupList;
		
	}

	/* GET USER LIST */
	public ArrayList getUsersList()throws Exception
	{
		try {
			dbUtility = new DBUtility();
	        sql = UserRGroupMappingCommonSQL.SELECT_USERS_LIST_JOIN;
	        dbUtility.createStatement();
	       
	        ArrayList mainList1= dbUtility.executeQuery(sql);
	        for (int i=0;i<mainList1.size();i++) 
	         {
	         subList = (ArrayList)mainList1.get(i);
	         userRGroupDTO = new UserRGroupMappingDTO();
	         userRGroupDTO.setUserId(subList.get(0).toString());
	         userRGroupDTO.setUserName(subList.get(1).toString());
	         userList.add(userRGroupDTO);
	         //dbUtility.commit();
	         }
	        } catch (Exception e) {
	          //e.getStackTrace();
	        	e.printStackTrace();
	        }finally {
	        	dbUtility.closeConnection();
	        }
	    return userList;
		
	}

	/* INSERT USER ROLE GROUP MAPPING*/
	public void insertUserRGroupMappingMaster(UserRGroupMappingDTO dto) {
		
		String param[] = new String[2];
		param[0] = dto.getUserId();
		param[1] = dto.getRoleGroupId();

		sql = UserRGroupMappingCommonSQL.INSERT_USER_ROLE_GROUP_MAPPING;

		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
			}else {
				dbUtility.rollback();
			}	
		} catch (Exception e) {
			//e.getStackTrace();
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception x){
				x.getStackTrace();
			}
		}
	}
	
	/* UPDATE USER ROLE GROUP MAPPING */

	public void updateUserRGroupMapping(UserRGroupMappingDTO dto) {
		
		String param[] = new String[3];
		param[0] = dto.getUserId();
		param[1] = dto.getRoleGroupId();
		param[2] = dto.getUserRGroupId();
		
		
		sql = UserRGroupMappingCommonSQL.UPDATE_USER_ROLE_GROUP_MAPPING;

		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
			}else {
				dbUtility.rollback();
			}	
		} catch (Exception e) {
			//e.getStackTrace();
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception x){
				x.getStackTrace();
			}
		}
	}
	/* DELETE USER ROLE GROUP MAPPING */
	
	public void deleteUserRGroup(String id){
		
		String param[] = new String[1];
		param[0] = id;

		sql = UserRGroupMappingCommonSQL.DELETE_USER_ROLE_GROUP_MAPPING;

		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
			}else {
				dbUtility.rollback();
			}	
		} catch (Exception e) {
			e.getStackTrace();

		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception x){
				x.getStackTrace();
			}
		}
}
}
