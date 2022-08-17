/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   MenuBD.java
 * Author      :   Neesha Aggarwal
 * Description :   Represents the Business Delegate Class for LHS Menu Creation.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Neesha Aggarwal  18th April,2008 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.ACL.bd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wipro.igrs.ACL.dao.MenuDAO;
import com.wipro.igrs.baseaction.dao.User;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.login.sql.CommonSQL;

/**
 * @author neegaga
 * 
 */
public class MenuBD {

	//DBUtility dbUtil=new DBUtility();
	ArrayList retrievedList = new ArrayList();
	private Logger logger = (Logger) Logger.getLogger(MenuBD.class);

	 public MenuBD() throws Exception{
		 
	 }
	public boolean chkAuthentication(String loginName, String pswd)
			throws Exception {
		boolean flag = false;
		MenuDAO roledao = new MenuDAO();
		try {
			String[] user = new String[2];
			user[0] = loginName;
			// CryptoLibrary crypt = new CryptoLibrary();
			user[1] = pswd;
			flag = roledao.authenticateUser(user);
			if (flag == true)
				logger
						.debug("Employee is sucessfully authenticated with the database. ");
			else
				logger.debug("Employee Credentials are not confirmed.");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return flag;
	}

/*	public HashMap getEmpRole(String uID) throws Exception {
		HashMap role = new HashMap();
		try {

			//MenuDAO mdao = new MenuDAO();
			User user = new User();
			retrievedList = user.getRoleID(uID);
			logger.debug("Retrieved Role List is ....    " + retrievedList);

			if (retrievedList != null && retrievedList.size() != 0) {
				role = (HashMap) retrievedList.get(0);
//				String roleGroupID = (String) retrievedList.get(0);
//				String roleID = (String) retrievedList.get(1);
//				if(roleID !=null) {
//					String[] roleDetail = roleID.split(";");
//					if(roleDetail !=null) {
//						for(int i=0;i<roleDetail.length;i++) {
//							if(roleDetail[i]!=null) {
//								String[] idRole = roleDetail[i].split(",");
//								if(idRole != null) {
//									for(int j =0;j<idRole.length;j++) {
//										role.put(idRole[1], idRole[2]);
//										logger.debug(idRole[1]+":"+ idRole[2]);
//									}
//								}
//							}
//						}
//					}
//				}
				logger.debug("sucessful");
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}
//		System.out.println(role);
		return role;
	}*/

	
	
	public HashMap getEmpRole(String uID, String ofcId) throws Exception {
		HashMap role = new HashMap();
		
		HashMap internalUserRole = new HashMap();
		ArrayList subList = new ArrayList();
		try {
			
				String typeOfUser=getTypeOfUser(uID);
				User user = new User();
				retrievedList = user.getRoleID(uID);
				logger.debug("Retrieved Role List is ....    " + retrievedList);

				if(typeOfUser.equalsIgnoreCase("2"))// internal user
					{
				
					if(ofcId.equalsIgnoreCase("OFC01"))
						{
							if (retrievedList != null && retrievedList.size() != 0)
							{
								role = (HashMap) retrievedList.get(0);
								logger.debug("sucessful");
							}
						}
					else
					{
							MenuDAO mdao = new MenuDAO();
							ArrayList list  = mdao.getRoleMappedToOffice(ofcId);
							if (retrievedList != null && retrievedList.size() != 0) 
							{
								internalUserRole=(HashMap) retrievedList.get(0);
								for(int i =0;i<list.size();i++)
									{
										subList=(ArrayList)list.get(i);
										String key =subList.get(0).toString();
											if(internalUserRole.containsKey(key))
											{
												String value = internalUserRole.get(key).toString();
												role.put(key, value);
											}
									}
							}
					
					
					}
				
			}
			else if (typeOfUser.equalsIgnoreCase("1"))//external user
			{
			if (retrievedList != null && retrievedList.size() != 0) {
				role = (HashMap) retrievedList.get(0);

				logger.debug("sucessful");
			}
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}
			
//		System.out.println(role);
		return role;
	}
	
	
	
	private String getTypeOfUser(String uid) throws Exception{
	// TODO Auto-generated method stub
		MenuDAO mdao = new MenuDAO();
	return mdao.getTypeOfUser(uid);
}
	/**
	 * getting the user role name
	 * 
	 * @param uID
	 * @return
	 * @throws Exception
	 *             added by pavani
	 */

	public String getEmpRoleName(String uID) throws Exception {
		String roleName = "";
		try {
			boolean success = false;

			MenuDAO mdao = new MenuDAO();
			retrievedList = mdao.getRoleName(uID);
			logger.debug("Retrieved Role List is ....    " + retrievedList);

			if (retrievedList != null && retrievedList.size() != 0) {
				ArrayList rlist = (ArrayList) retrievedList.get(0);
				roleName = (String) rlist.get(0);
				success = true;
				logger.debug("sucessful");
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return roleName;
	}

	public ArrayList getUserModules(HashMap roleId) throws Exception {

		try {
			boolean success = false;
			MenuDAO mdao = new MenuDAO();
			retrievedList = mdao.extractModules(roleId);
			logger.debug("Retrieved Modules List is ....    " + retrievedList);

			if (retrievedList != null && retrievedList.size() != 0) {
				success = true;
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return retrievedList;
	}

	public ArrayList getUserSubMods(String modId, HashMap roleId)
			throws Exception {

		try {
			boolean success = false;
			MenuDAO mdao = new MenuDAO();
			retrievedList = mdao.extractSubModules(modId, roleId);
			logger.debug("Retrieved Sub Modules List is ....    "
					+ retrievedList);

			if (retrievedList != null && retrievedList.size() != 0) {
				success = true;
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		
		return retrievedList;
	}

	public ArrayList getUserFunctions(String smodId, HashMap roleId)
			throws Exception {

		try {
			boolean success = false;
			MenuDAO mdao = new MenuDAO();
			retrievedList = mdao.extractFunctions(smodId, roleId);
			logger
					.debug("Retrieved Functions List is ....    "
							+ retrievedList);

			if (retrievedList != null && retrievedList.size() != 0) {
				success = true;
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return retrievedList;
	}

	public ArrayList getUserActivities(String funcId, HashMap roleId)
			throws Exception {

		try {
			boolean success = false;
			MenuDAO mdao = new MenuDAO();
			retrievedList = mdao.extractActivity(funcId, roleId);
			logger.debug("Retrieved Activities List is ....    "
					+ retrievedList);

			if (retrievedList != null && retrievedList.size() != 0) {
				success = true;
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return retrievedList;
	}

	public ArrayList<Map<String, String>> getCompleteMenuDetails(String[] roles) {
		ArrayList<Map<String, String>> retVal = null;
		try {
			MenuDAO mdao = new MenuDAO();
			retVal = mdao.getCompleteMenuDetails(roles);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return retVal;
	}
}