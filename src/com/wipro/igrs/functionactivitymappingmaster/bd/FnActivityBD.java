/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   FnActivityBD.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the BD Class for Rolefunctionactivitymapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  18th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.functionactivitymappingmaster.bd;

import java.util.ArrayList;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.empmgmt.dto.AddressDTO;
import com.wipro.igrs.functionactivitymappingmaster.dao.FnActivityDAO;
import com.wipro.igrs.functionactivitymappingmaster.dto.FnActivityDTO;
import com.wipro.igrs.functionactivitymappingmaster.form.FnActivityForm;



public class FnActivityBD {

	public FnActivityBD() {
		// LoggerMsg.info("we are in FnActivityBD");
	}

	FnActivityDAO fndao = new FnActivityDAO();

	/**
	 * Add the list of Fnactivitymappingmaster
	 * 
	 * @throws Exception
	 */

	public void addFnactivitymappingmaster(FnActivityForm fnactivityForm)
			throws Exception {
		System.out.println("Inside add... in BD..");
		fndao.addFnactivitymappingmaster(fnactivityForm);
	}

	/**
	 * Gets the list of FunctionList
	 * 
	 * @param ArrayList
	 *            of functionList
	 * @return ArrayList of FnActivityDTO
	 * @throws Exception
	 */
//	public ArrayList getFunctionList() throws Exception {
//		return fndao.getFunctionList();
//	}

	/**
	 * Gets the list of RoleList
	 * 
	 * @param ArrayList
	 *            of roleList
	 * @return ArrayList of FnActivityDTO
	 * @throws Exception
	 */
	public ArrayList getRoleList() throws Exception {
		return fndao.getRoleList();
	}

	/**
	 * Gets the list of ActivityList
	 * 
	 * @param ArrayList
	 *            of activityList
	 * @return ArrayList of FnActivityDTO
	 * @throws Exception
	 */
//	public ArrayList getActivityList() throws Exception {
//		return fndao.getActivityList();
//	}

	/**
	 * Gets the list of ModuleList
	 * 
	 * @param ArrayList
	 *            of moduleList
	 * @return ArrayList of FnActivityDTO
	 * @throws Exception
	 */
	public ArrayList getModuleList() throws Exception {
		return fndao.getModuleList();
	}

	/**
	 * Gets the list of SubmoduleList
	 * 
	 * @param ArrayList
	 *            of submoduleList
	 * @return ArrayList of FnActivityDTO
	 * @throws Exception
	 */
//	public ArrayList getSubmoduleList() throws Exception {
//		return fndao.getSubmoduleList();
//	}

	/**
	 * Gets the list of FnactivityList
	 * 
	 * @param ArrayList
	 *            of fnactivityList
	 * @return ArrayList of FnActivityDTO
	 * @throws Exception
	 */
	public ArrayList getFnactivityList() throws Exception {
		return fndao.getFnactivityList();
	}

	/**
	 * Update the list of Fnactivitymappingmaster
	 * 
	 * @throws Exception
	 */
	public void updateFnactivitymappingmaster(FnActivityForm fnactivityForm)
			throws Exception {
		fndao.updateFnactivitymappingmaster(fnactivityForm);
	}

	/**
	 * Gets the list of FunctionId
	 * 
	 * @param FnActivityDTO
	 *            of functionId
	 * @return FnActivityDTO
	 * @throws Exception
	 */
	public FnActivityDTO getFunctionId(String fnid) throws Exception {
		return fndao.getFunctionId(fnid);
	}
	
	
	public ArrayList getSubModuleMasterList(String moduleID) throws Exception {
		FnActivityDAO fnActDAO = new FnActivityDAO();
		ArrayList subModlist = fnActDAO.getSubmoduleMasterList(moduleID);
		ArrayList subModuleList = new ArrayList();
		if (subModlist != null) {
			for (int i = 0; i < subModlist.size(); i++) {
				ArrayList smlist = (ArrayList) subModlist.get(i);
				if (smlist != null) {
					FnActivityDTO dto = new FnActivityDTO();
					dto.setSubmoduleId((String) smlist.get(0));
					dto.setSubModuleName((String) smlist.get(1));
					subModuleList.add(dto);
				}
			}
		}
		return subModuleList;
	}
	public ArrayList getFunctionMasterList(String subModuleID) throws Exception {
		FnActivityDAO fnActDAO = new FnActivityDAO();
		ArrayList functionlist = fnActDAO.getFunctionMasterList(subModuleID);
		ArrayList funList = new ArrayList();
		if (functionlist != null) {
			for (int i = 0; i < functionlist.size(); i++) {
				ArrayList funtlist = (ArrayList) functionlist.get(i);
				if (funtlist != null) {
					FnActivityDTO dto = new FnActivityDTO();
					dto.setFunctionId((String) funtlist.get(0));
					dto.setFunctionName((String) funtlist.get(1));
					funList.add(dto);
				}
			}
		}
		return funList;
	}
	public ArrayList getActivityMasterList(String functionID, String roleID) throws Exception {
		FnActivityDAO fnActDAO = new FnActivityDAO();
		ArrayList activitylist = fnActDAO.getActivityMasterList(functionID);
		ArrayList mappedactivitylist = new ArrayList();
		mappedactivitylist = fnActDAO.getActivityMappedList(functionID,roleID);
		ArrayList actList = new ArrayList();
		ArrayList mappedactviti1=new ArrayList();
		if(mappedactivitylist!=null)
		{
			for (int i = 0; i < mappedactivitylist.size(); i++) {
				ArrayList rowList=(ArrayList) mappedactivitylist.get(i);
				mappedactviti1.add((String)rowList.get(0));
			}
		}
			
		
		if (activitylist != null) {
			for (int i = 0; i < activitylist.size(); i++) {
				ArrayList actvtlist = (ArrayList) activitylist.get(i);
				if (actvtlist != null) {
					FnActivityDTO dto = new FnActivityDTO();
					dto.setActivityId((String) actvtlist.get(0));
					dto.setActivityName((String) actvtlist.get(1));
					if(mappedactviti1.contains((String) actvtlist.get(0)))
					{
					dto.setStatus("Mapped");
					
					}
					else
					{
						dto.setStatus("Not Mapped");
					}
					actList.add(dto);
				
				
				}
				
			}
		}
		return actList;
	}

	public boolean getcheckedinsertedid(String[] checkedid, String roleid, String modid, String submid, String fxnid) throws Exception {
		boolean inserted=false;
		FnActivityDAO fnActDAO = new FnActivityDAO();
		inserted = fnActDAO.getcheckedinsertedid(checkedid,roleid,modid,submid,fxnid);
		return inserted;
		
		
	}

	public boolean getdeleteuncheckedid(String[] uncheckedid, String roleid,
			String modid, String submid, String fxnid) throws Exception {
		boolean deleted=false;
		FnActivityDAO fnActDAO = new FnActivityDAO();
		deleted = fnActDAO.getdeleteuncheckedid(uncheckedid,roleid,modid,submid,fxnid);
		return deleted;
	}


}
