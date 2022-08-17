/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RoleManagementBD.java
 * Author      :   Neesha Aggarwal
 * Description :   Represents the Business Delegate Class for ACL Module.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Neesha Aggarwal  25th MArch,2008 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.ACL.bd;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.wipro.igrs.ACL.dao.RoleMangementDAO;
import com.wipro.igrs.ACL.dto.ActivityDTO;
import com.wipro.igrs.ACL.dto.GroupDTO;
import com.wipro.igrs.ACL.dto.GroupsUserDTO;
import com.wipro.igrs.ACL.dto.OfficeDTO;
import com.wipro.igrs.ACL.dto.RoleCreationDTO;
import com.wipro.igrs.ACL.dto.RolesGroupDTO;
import com.wipro.igrs.UserRegistration.action.CryptoLibrary;

/**
 * @author neegaga
 * 
 */
public class RoleManagementBD {

	ArrayList retrievedList = new ArrayList();
	public static String RoleID = null;
	public static String GrpID = null;
	private Logger logger = (Logger) Logger.getLogger(RoleManagementBD.class);

	/**
	 * insertRoles Returns ArrayList in order to check the insertion.
	 * @param userId 
	 * @param funId 
	 * @param roleId2 
	 * 
	 * @param rName,rDesc,rStatus,i
	 * 
	 * @return ArrayList
	 * @throws Exception
	 * 
	 */
	public boolean insertRoles(ArrayList insertedlist, String roleId, String funId, String userId) throws Exception {
		RoleMangementDAO dao = new RoleMangementDAO();
		boolean rolesuccess = false;
		try {
			String rstatus = new String();
			String rName = new String();
			String rDesc = new String();

			if (insertedlist != null && insertedlist.size() > 0) {
				for (int k = 0; k < insertedlist.size(); k++) {
					RoleCreationDTO dto = (RoleCreationDTO) insertedlist.get(k);
					rName = dto.getRoleName();
					rDesc = dto.getRolDesc();
					rstatus = dto.getStatus();
//					if (rstatus.equalsIgnoreCase("Active")) {
//						rstatus = "P";
//					} else if (rstatus.equalsIgnoreCase("Deactive")) {
//						rstatus = "D";
//					}
					rstatus = "P";
					RoleID = roleIDgenerator();
					String[] param = new String[5];
					param[0] = RoleID;
					param[1] = rName.toUpperCase();
					param[2] = rDesc;
					param[3] = rstatus;
					param[4] = userId;//"Admin"; // Created by field as per the Role Id
										// from the session;

					rolesuccess = dao.insertRolDetails(param,roleId,funId,userId);
					logger.debug(""+rolesuccess);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rolesuccess;
	}

	public ArrayList getStoredRoles() throws Exception // main
	{
		boolean success;
		RoleMangementDAO roledao = new RoleMangementDAO();
		String flag1 = new String("A");
		String flag2 = new String("D");

		retrievedList = roledao.extractStoredRoles(flag1, flag2);
		//logger.debug("Retrieved list of Roles is:....    " + retrievedList);
		ArrayList list = new ArrayList();

		if (retrievedList != null) {
			for (int i = 0; i < retrievedList.size(); i++) {
				ArrayList lst = (ArrayList) retrievedList.get(i);
				RoleCreationDTO dto = new RoleCreationDTO();
				dto.setRoleName((String) lst.get(0));
				dto.setRolDesc((String) lst.get(1));
				if (lst.get(2).toString().equals("A"))
					dto.setStatus("Active");
				else if (lst.get(2).toString().equals("D"))
					dto.setStatus("Deactive");
				int j = i + 1;
				dto.setRoleId((String) lst.get(3));
				dto.setSno(j);
				list.add(dto);
			}
		}
		success = true;

		return list;
	}
	
	public ArrayList getStoredRoles(String pStatus) throws Exception // main
	{
		boolean success;
		RoleMangementDAO roledao = new RoleMangementDAO();

		retrievedList = roledao.extractStoredRoles(pStatus);
		//logger.debug("Retrieved list of Roles is:....    " + retrievedList);
		ArrayList list = new ArrayList();

		if (retrievedList != null) {
			for (int i = 0; i < retrievedList.size(); i++) {
				ArrayList lst = (ArrayList) retrievedList.get(i);
				RoleCreationDTO dto = new RoleCreationDTO();
				dto.setRoleName((String) lst.get(0));
				dto.setRolDesc((String) lst.get(1));
				if (lst.get(2).toString().equalsIgnoreCase("P"))
					dto.setStatus("Pending Approval");
				int j = i + 1;
				dto.setRoleId((String) lst.get(3));
				dto.setSno(j);
				list.add(dto);
			}
		}
		success = true;

		return list;
	}
	
	

	public String getRoleID() {
		return RoleID;
	}

	private String roleIDgenerator() {
		String id = "RL" + new Date().getTime();
		logger.debug("this is roleIDgenerator() & value of ID " + id);
		return id;
	}

	public boolean updateRoles(String rolname, String descRol,
			String roleStatus, String rId, String roleId, String funId, String userId) throws Exception {
		RoleMangementDAO rDAO = new RoleMangementDAO();
		String[] reg_pin = new String[4];
		reg_pin[0] = rolname;
		reg_pin[1] = descRol;
		reg_pin[2] = roleStatus;
		reg_pin[3] = userId;//"Admin";

		boolean success = rDAO.updateRoleDetails(reg_pin, rId,roleId,funId,userId);

		return success;
	}

	public RoleCreationDTO getRolesDetails(String idR) throws Exception // main
	{
		boolean success;
		RoleCreationDTO dto = new RoleCreationDTO();
		RoleMangementDAO roledao = new RoleMangementDAO();
		retrievedList = roledao.extractRoleDetail(idR);
		logger.debug("Retrieved Role Details are ....    " + retrievedList);

		if (retrievedList != null && retrievedList.size() != 0) {
			ArrayList lst = (ArrayList) retrievedList.get(0);
			dto.setRoleName((String) lst.get(0));
			dto.setRolDesc((String) lst.get(1));
			dto.setStatus((String) lst.get(2));
			dto.setRoleId((String) lst.get(3));
			int j = 1;
			dto.setSno(j);
			dto.setRoleOfficeMapping(roledao.getRoleOfficeMapping(dto.getRoleId()));
		}
		success = true;
		return dto;
	}

	/**
	 * *********************************ACL-Group
	 * *******************************************
	 */

	/**
	 * insertGroups Returns ArrayList in order to check the insertion.
	 * @param userId 
	 * @param funId 
	 * @param roleId2 
	 * 
	 * @param grpName,grpDesc,grpStatus,i
	 * 
	 * @return ArrayList
	 * @throws Exception
	 * 
	 */
	public boolean insertGroups(ArrayList insertedlist, String roleId, String funId, String userId) throws Exception {

		RoleMangementDAO dao = new RoleMangementDAO();
		boolean grpsuccess = false;
		try {
			String gstatus = new String();
			String gName = new String();
			String gDesc = new String();

			if (insertedlist != null) {
				for (int k = 0; k < insertedlist.size(); k++) {
					GroupDTO dto = (GroupDTO) insertedlist.get(k);
					gName = dto.getGrpName();
					gDesc = dto.getGrpDesc();
					gstatus = "P";
//					if (gstatus.equalsIgnoreCase("Active")) {
//						gstatus = "A";
//					} else if (gstatus.equalsIgnoreCase("Deactive")) {
//						gstatus = "D";
//					}

					GrpID = grpIDgenerator();

					String[] param = new String[5];
					param[0] = GrpID;
					param[1] = gName.toUpperCase();
					param[2] = gDesc;
					param[3] = gstatus;
					param[4] = userId; // "Admin"; // Created by field as per the Role Id
										// from the session;
					grpsuccess = dao.insertGroups(param,roleId,funId,userId);
					//System.out.println(grpsuccess);
					logger.debug("In BD-->  "+grpsuccess);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return grpsuccess;
	}

	public boolean updateGroup(String name, String desc, String status,
			String id, String roleId, String funId, String userId) throws Exception {
		RoleMangementDAO rDAO = new RoleMangementDAO();

		String[] reg_pin = new String[5];
		reg_pin[0] = name;
		reg_pin[1] = desc;
		reg_pin[2] = status;
		reg_pin[3] = userId;//"Admin";
		reg_pin[4] = id;
		boolean success = rDAO.updateGroupDetails(reg_pin,roleId,funId,userId);

		return success;
	}

	/**
	 * getStoredGroups Returns ArrayList in order to fetch the records from the
	 * database.
	 * 
	 * @return ArrayList
	 * @throws Exception
	 * 
	 */

	public ArrayList getStoredGroups() throws Exception // main
	{
		boolean success;
		RoleMangementDAO dao = new RoleMangementDAO();
		String flag1 = new String("A");
		String flag2 = new String("D");
		retrievedList = dao.getStoredGroups(flag1, flag2);
		logger.debug("Retrieved list of Groups is:....    " + retrievedList);
		ArrayList list = new ArrayList();

		if (retrievedList != null) {
			for (int i = 0; i < retrievedList.size(); i++) {
				ArrayList lst = (ArrayList) retrievedList.get(i);
				GroupDTO dto = new GroupDTO();
				dto.setGrpName((String) lst.get(0));
				dto.setGrpDesc((String) lst.get(1));
				if (lst.get(2).toString().equals("A"))
					dto.setStatus("Active");
				else if (lst.get(2).toString().equals("D"))
					dto.setStatus("Deactive");
				dto.setGrpId((String) lst.get(3));
				int j = i + 1;
				dto.setSno(j);
				list.add(dto);
			}
		}
		success = true;

		return list;
	}
	
	public ArrayList getStoredGroups(String pStatus) throws Exception // main
	{
		boolean success;
		RoleMangementDAO dao = new RoleMangementDAO();
		retrievedList = dao.getStoredGroups(pStatus);
		logger.debug("Retrieved list of Groups is:....    " + retrievedList);
		ArrayList list = new ArrayList();

		if (retrievedList != null) {
			for (int i = 0; i < retrievedList.size(); i++) {
				ArrayList lst = (ArrayList) retrievedList.get(i);
				GroupDTO dto = new GroupDTO();
				dto.setGrpName((String) lst.get(0));
				dto.setGrpDesc((String) lst.get(1));
				if (lst.get(2).toString().equalsIgnoreCase("P"))
					dto.setStatus("Pending For Approval");
				dto.setGrpId((String) lst.get(3));
				int j = i + 1;
				dto.setSno(j);
				list.add(dto);
			}
		}
		success = true;

		return list;
	}

	public String getGrpID() {
		return GrpID;
	}

	private String grpIDgenerator() {
		String id = "GR" + new Date().getTime();
		logger.debug("this is grpIDgenerator() & value of ID " + id);
		return id;
	}

	public GroupDTO getGroupDetails(String idgrp) throws Exception // main
	{
		boolean success;
		GroupDTO dto = new GroupDTO();
		RoleMangementDAO roledao = new RoleMangementDAO();
		retrievedList = roledao.extractGrpDetail(idgrp);
		logger.debug("Retrieved Group Details are ....    " + retrievedList);
		if (retrievedList != null && retrievedList.size() != 0) {
			ArrayList lst = (ArrayList) retrievedList.get(0);

			dto.setGrpName((String) lst.get(0));
			dto.setGrpDesc((String) lst.get(1));
			dto.setStatus((String) lst.get(2));
			dto.setGrpId((String) lst.get(3));
			int j = 1;
			dto.setSno(j);
		}
		success = true;

		return dto;
	}

	/**
	 * ********************************************Activity
	 * Part*********************************
	 */

	/**
	 * getDistinctRoles Returns ArrayList of distinct Roles.
	 * 
	 * @return ArrayList
	 * @throws Exception
	 * 
	 * @Exception
	 */
	public ArrayList getDistinctRoles() throws Exception {
		RoleMangementDAO roledao = new RoleMangementDAO();
		String flag1 = new String("A");
		retrievedList = roledao.extractRoles(flag1);
		logger.debug("Retrieved list of Roles is:....    " + retrievedList);
		ArrayList list = new ArrayList();

		if (retrievedList != null) {
			for (int i = 0; i < retrievedList.size(); i++) {
				ArrayList lst = (ArrayList) retrievedList.get(i);
				ActivityDTO dto = new ActivityDTO();
				dto.setRoleId((String) lst.get(0));
				dto.setRoleName((String) lst.get(1));
				list.add(dto);
			}
		}
		return list;
	}

	/**
	 * getActivity Returns ArrayList of distinct Activities.
	 * 
	 * @return ArrayList
	 * @throws Exception
	 * 
	 * @Exception
	 */
	public ArrayList getActivity() throws Exception {
		RoleMangementDAO roledao = new RoleMangementDAO();
		retrievedList = roledao.extractActivities();
		logger
				.debug("Retrieved list of Activities is:....    "
						+ retrievedList);
		ArrayList list = new ArrayList();

		if (retrievedList != null) {
			for (int i = 0; i < retrievedList.size(); i++) {
				ArrayList lst = (ArrayList) retrievedList.get(i);
				ActivityDTO dto = new ActivityDTO();
				dto.setActivityId((String) lst.get(0));
				dto.setActivityName((String) lst.get(1));
				list.add(dto);
			}
		}
		return list;
	}

	/**
	 * insertRolesActivity Returns boolean in order to check the insertion.
	 * @param userId 
	 * @param funId 
	 * @param roleId2 
	 * 
	 * @param rName,menuArray
	 * 
	 * @return ArrayList
	 * @throws Exception
	 * 
	 */
	public boolean insertRolesActivity(String rName, ArrayList menuArray, String roleId, String funId, String userId)
			throws Exception {
		RoleMangementDAO dao = new RoleMangementDAO();
		boolean rolesuccess = false;

		try {

			String id = new String();
			String actStatus = new String();

			if (menuArray != null && menuArray.size() > 0) {
				for (int g = 0; g < menuArray.size(); g++) {
					ActivityDTO ndto = (ActivityDTO) menuArray.get(g);

					id = (String) ndto.getActivityId();
					actStatus = (String) ndto.getSlctFlag();
					if (actStatus.equalsIgnoreCase("Y")) {
						rolesuccess = dao.insertDetails(id, rName,roleId,funId,userId);
					} else if (actStatus.equalsIgnoreCase("N")) {
						continue;

					}

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rolesuccess;
	}

	/**
	 * ******************************RolesGrp
	 * part******************************************
	 */

	/**
	 * getDistinctRGrps Returns ArrayList of distinct Roles Group.
	 * 
	 * @return ArrayList
	 * @throws Exception
	 * 
	 * @Exception
	 */
	public ArrayList getDistinctRGrps() throws Exception {
		RoleMangementDAO roledao = new RoleMangementDAO();
		String flag1 = new String("A");
		retrievedList = roledao.extractGroups(flag1);
		logger.debug("Retrieved list of Groups is:....    " + retrievedList);
		ArrayList list = new ArrayList();

		if (retrievedList != null) {
			for (int i = 0; i < retrievedList.size(); i++) {
				ArrayList lst = (ArrayList) retrievedList.get(i);
				RolesGroupDTO dto = new RolesGroupDTO();
				dto.setGrpId((String) lst.get(0));
				dto.setGrpName((String) lst.get(1));
				list.add(dto);
			}
		}
		return list;
	}

	/**
	 * getActivity Returns ArrayList of distinct Activities.
	 * 
	 * @return ArrayList
	 * @throws Exception
	 * 
	 * @Exception
	 */
	public ArrayList getRoles() throws Exception {
		RoleMangementDAO roledao = new RoleMangementDAO();
		retrievedList = roledao.extractRoles();
		logger.debug("Retrieved list of Roles is:....    " + retrievedList);
		ArrayList list = new ArrayList();

		if (retrievedList != null) {
			for (int i = 0; i < retrievedList.size(); i++) {
				ArrayList lst = (ArrayList) retrievedList.get(i);
				RolesGroupDTO dto = new RolesGroupDTO();
				dto.setRoleId((String) lst.get(0));
				dto.setRoleName((String) lst.get(1));
				list.add(dto);
			}
		}
		return list;
	}
	/**
	 * getActivity Returns ArrayList of distinct Activities.
	 * @author SHREERAJ
	 * @return ArrayList
	 * @throws Exception
	 * 
	 * @Exception
	 */
	public boolean extractRolesNames(ArrayList roleEntered) throws Exception {
		boolean roleFlag=false;
		RoleMangementDAO roledao = new RoleMangementDAO();
		retrievedList = roledao.extractRolesNames();
		logger.debug("Retrieved list of Roles is:....    " + retrievedList);
		for(int i=0;i<retrievedList.size();i++){
			ArrayList subList=(ArrayList)retrievedList.get(i);
			String roleName=subList.get(0).toString();
			for(int j=0;j<roleEntered.size();j++){
				RoleCreationDTO rgDTO=(RoleCreationDTO)roleEntered.get(j);
				String roleNameEntered=rgDTO.getRoleName();
				if(roleNameEntered.trim().equalsIgnoreCase(roleName.trim())){
					roleFlag=false;
					return roleFlag;
				}else{
					roleFlag=true;
				}
				
			}
			
		}
		return roleFlag;
	}
	
	/**
	 * getActivity Returns ArrayList of distinct Activities.
	 * 
	 * @return ArrayList
	 * @throws Exception
	 * 
	 * @Exception
	 */
	public ArrayList getNewRoles() throws Exception {
		RoleMangementDAO roledao = new RoleMangementDAO();
		retrievedList = roledao.extractNewRoles();
		logger.debug("Retrieved list of Roles is:....    " + retrievedList);
		ArrayList list = new ArrayList();

		if (retrievedList != null) {
			for (int i = 0; i < retrievedList.size(); i++) {
				ArrayList lst = (ArrayList) retrievedList.get(i);
				RolesGroupDTO dto = new RolesGroupDTO();
				dto.setRoleId((String) lst.get(0));
				dto.setRoleName((String) lst.get(1));
				list.add(dto);
			}
		}
		return list;
	}

	/**
	 * insertGrpRoles Returns boolean in order to check the insertion.
	 * 
	 * @param rName,menuArray
	 * 
	 * @return ArrayList
	 * @throws Exception
	 * 
	 */
	public boolean insertGrpRoles(String grpName, ArrayList menuArray)
			throws Exception {
		RoleMangementDAO dao = new RoleMangementDAO();
		boolean rolesuccess = false;
		try {

			String id = new String();
			String roleStatus = new String();

			rolesuccess=dao.updateRGDetails(grpName);
			logger.debug("updated previous roles in role group  -----"+ rolesuccess);
			rolesuccess = false;
			if (menuArray != null && menuArray.size() > 0) {
				for (int g = 0; g < menuArray.size(); g++) {
					RolesGroupDTO gdto = (RolesGroupDTO) menuArray.get(g);

					id = (String) gdto.getRoleId();
					roleStatus = (String) gdto.getSlctFlag();
					if (roleStatus.equalsIgnoreCase("Y")) {
						rolesuccess = dao.insertRGDetails(id, grpName);
					} else if (roleStatus.equalsIgnoreCase("N")) {
						//System.out.println("not inserted");
						logger.debug("not inserted");
						continue;

					}

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rolesuccess;
	}

	/**
	 * ********************Group USer Part-ACL
	 * Module******************************************
	 */
	/**
	 * insertGrpRoles Returns boolean in order to check the insertion.
	 * @param loggedInUser 
	 * @param funId 
	 * @param roleId 
	 * 
	 * @param rName,menuArray
	 * 
	 * @return ArrayList
	 * @throws Exception
	 * 
	 */
	public boolean insertGrpUserActivity(String userId, ArrayList menuArray, String roleId, String funId, String loggedInUser)
			throws Exception {
		RoleMangementDAO dao = new RoleMangementDAO();
		boolean usersuccess = false;
		try {
			dao.insertUserGroupData(userId, menuArray, roleId, funId, loggedInUser);
//			String grpid = new String();
//			String grpStatus = new String();
//
//			if (menuArray != null && menuArray.size() > 0) {
//				for (int g = 0; g < menuArray.size(); g++) {
//					GroupsUserDTO gdto = (GroupsUserDTO) menuArray.get(g);
//
//					grpid = (String) gdto.getGroupId();
//					grpStatus = (String) gdto.getSlctFlag();
//					if (grpStatus.equalsIgnoreCase("Y")) {
//						usersuccess = dao.insertGrpUserDetails(userId, grpid,roleId,funId,loggedInUser);
//					} else if (grpStatus.equalsIgnoreCase("N")) {
//						//System.out.println("not inserted");
//						logger.debug("not inserted");
//						continue;
//					}
//				}
//			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return usersuccess;
	}

	/**
	 * getDistinctRGrps Returns ArrayList of distinct Roles Group.
	 * 
	 * @return ArrayList
	 * @throws Exception
	 * 
	 * @Exception
	 */
	
	public ArrayList getDistinctGrps() throws Exception {
		RoleMangementDAO roledao = new RoleMangementDAO();
		ArrayList list = new ArrayList();
		try {
			String flag1 = new String("A");
			retrievedList = roledao.extractGroups(flag1);
			logger
					.debug("Retrieved list of Groups is:....    "
							+ retrievedList);
			if (retrievedList != null) {
				for (int i = 0; i < retrievedList.size(); i++) {
					ArrayList lst = (ArrayList) retrievedList.get(i);
					GroupsUserDTO dto = new GroupsUserDTO();
					dto.setGroupId((String) lst.get(0));
					dto.setGroupName((String) lst.get(1));
					list.add(dto);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	/**
	 * ***************************User
	 * Login*****************************************
	 */
	
	public boolean chkAuthentication(String loginName, String pswd)
			throws Exception {
		boolean flag = false;
		RoleMangementDAO roledao = new RoleMangementDAO();
		try {
			String[] user = new String[2];
			user[0] = loginName;
			CryptoLibrary crypt = new CryptoLibrary();
			user[1] = crypt.encrypt(pswd);
			flag = roledao.authenticateUser(user);
			if (flag == true)
				logger
						.debug("User is sucessfully authenticated with the database. ");
			else
				logger.debug("User Credentials are not confirmed.");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return flag;
	}

	public ArrayList extractGroup(String loginName) throws Exception {
		ArrayList userGrpList = new ArrayList();
		RoleMangementDAO roledao = new RoleMangementDAO();
		try {
			userGrpList = roledao.getUserGroup(loginName);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return userGrpList;
	}

	
	public ArrayList extractRole(String gID) throws Exception {
		ArrayList roleList = new ArrayList();
		RoleMangementDAO roledao = new RoleMangementDAO();
		try {
			roleList = roledao.getGrpRole(gID);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return roleList;
	}

	public ArrayList extractRoleActivity(String rID, String gID)
			throws Exception {
		ArrayList actList = new ArrayList();
		RoleMangementDAO roledao = new RoleMangementDAO();

		try {
			actList = roledao.getRoleActivity(rID, gID);

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return actList;
	}

	public ArrayList extractModule(String rID, String gID) throws Exception {
		ArrayList moduleList = new ArrayList();
		RoleMangementDAO roledao = new RoleMangementDAO();

		try {
			moduleList = roledao.getRoleModule(rID, gID);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return moduleList;
	}

	public ArrayList extractSubModule(String mID, String rID, String gID)
			throws Exception {
		ArrayList sModList = new ArrayList();
		RoleMangementDAO roledao = new RoleMangementDAO();

		try {
			sModList = roledao.getSubModule(mID, rID, gID);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return sModList;
	}

	public ArrayList extractFunction(String smID, String rID, String gID)
			throws Exception {
		ArrayList fnctList = new ArrayList();
		RoleMangementDAO roledao = new RoleMangementDAO();

		try {
			fnctList = roledao.getFunction(smID, rID, gID);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return fnctList;
	}
	
	public boolean checkEmployeeAvail(String empID){
		boolean ispresent=false;
		String emp_ID="";
		RoleMangementDAO roledao;
		try {
			roledao = new RoleMangementDAO();
			ArrayList list = roledao.checkEmployeeAvail(empID);
			logger.debug("Retrieved Emp id is is:....    " + list);
			if (list != null && list.size() > 0 ) {
				//System.out.println("Lis t dsfdsfdsfjsdds + value is  :" + list.get(0).toString()  );
				
//				for (int i = 0; i < list.size(); i++) {
//					emp_ID=((String) list.get(0)==null ?"":list.get(0).toString());
//				}
				ispresent=true;
				
			}
			else {
				ispresent=false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("Exception in checkEmployeeAvail() :- " + e);
		}
	
		return ispresent;
		}
	
	public boolean isEmpExist(String empId){
		boolean boo=false;
		RoleMangementDAO roledao;
		try {
			roledao = new RoleMangementDAO();
			boo = roledao.isMappingExist(empId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return boo;
	}
	
	public boolean deleteEmp(String empId){
		boolean boo=false;
		RoleMangementDAO roledao;
		try {
			roledao = new RoleMangementDAO();
			roledao.deleteMapping(empId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return boo;
	}

	
	public boolean getUserGroup(String empId){
		boolean boo=false;
		RoleMangementDAO roledao;
		ArrayList list;
		try {
			roledao = new RoleMangementDAO();
			list=roledao.getUserGroup(empId);
			if(list.size()!=0){
				boo=true;
			}
			else {
				boo=false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return boo;
	}
	
	public String getRoleGrpID(String grpName) throws Exception {
		String roleGrpName="";
		RoleMangementDAO roledao = new RoleMangementDAO();
		try {
			roleGrpName = roledao.getRoleGrpId(grpName);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return roleGrpName;
	}
	
	public String getUserRoleGrpMapping(String userId) throws Exception {
		String roleGrpId="";
		RoleMangementDAO roledao = new RoleMangementDAO();
		try {
			roleGrpId = roledao.getUserRoleGrpMapping(userId);
			logger.debug("Group Id assigned to user is: " + roleGrpId);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return roleGrpId;
	}
	
	public String getActivityCount(String roleid) throws Exception {
		String activitycount="";
		RoleMangementDAO roledao = new RoleMangementDAO();
		try {
			activitycount = roledao.activitycount(roleid);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return activitycount;
	}
	
	
	public ArrayList<OfficeDTO> getOfficeListing() {
		ArrayList<OfficeDTO> retVal = null;
		try {
			RoleMangementDAO roleDao = new RoleMangementDAO();
			OfficeDTO office;
			ArrayList data, row;
			data = roleDao.getOfficeListing();
			if(data != null && data.size() > 0) {
				retVal = new ArrayList<OfficeDTO>();
			}
			for (Object item : data) {
				row = (ArrayList) item;
				office = new OfficeDTO();
				office.setOfficeID((String) row.get(0));
				office.setOfficeName((String) row.get(1));
				retVal.add(office);
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
			RoleMangementDAO roleDao = new RoleMangementDAO();
			retVal = roleDao.updateRole(dtoObject, funId, userId, loggedInRole);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}

	public boolean deleteExistingEntries(String group) {
		boolean retVal = false;
		try {
			RoleMangementDAO roleDao = new RoleMangementDAO();
			retVal = roleDao.deleteExistingEntries(group);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}
}
