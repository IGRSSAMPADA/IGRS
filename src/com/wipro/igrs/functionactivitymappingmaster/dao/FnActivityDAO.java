/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   URolegroupDAO.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the DAO Class for Rolefunctionactivitymapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  19th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.functionactivitymappingmaster.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

//import sun.security.krb5.internal.crypto.t;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.estamping.dao.EstampDAO;
import com.wipro.igrs.functionactivitymappingmaster.dto.FnActivityDTO;
import com.wipro.igrs.functionactivitymappingmaster.form.FnActivityForm;
import com.wipro.igrs.functionactivitymappingmaster.sql.FnActivityCommonSQL;

//import com.wipro.igrs.Log4J.LoggerMsg;

public class FnActivityDAO {

	private ArrayList functionList = new ArrayList();
	private ArrayList roleList = new ArrayList();
	private ArrayList activityList = new ArrayList();
	private ArrayList moduleList = new ArrayList();
	private ArrayList submoduleList = new ArrayList();
	private ArrayList fnactivityList = new ArrayList();
	DBUtility dbUtility = null;
	String sql = null;
	ArrayList subList = null;
	FnActivityDTO dto = null;
	private Logger logger = (Logger) Logger.getLogger(FnActivityDAO.class);

	/* DAO constructor */
	public FnActivityDAO() {

	}

	/* ADD USER ROLE_FUNCTION_ACTIVITY MAPPING MASTER */

	public void addFnactivitymappingmaster(FnActivityForm fnactivityForm) {
		boolean boo = false;

		sql = FnActivityCommonSQL.INSERT_ROLE_FN_ACTIVITY_MAPPING_MASTER;
		String param[] = new String[5];
		String selectActVal[] = fnactivityForm.getSelectedActVal();
		try {
			dbUtility = new DBUtility();
			for (int i = 0; i < selectActVal.length; i++) {
				param[0] = fnactivityForm.getFunctionId();
				param[1] = selectActVal[i];
				param[2] = fnactivityForm.getRoleId();
				param[3] = fnactivityForm.getModuleId();
				param[4] = fnactivityForm.getSubmoduleId();
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(param);
			}

			if (boo) {
				dbUtility.commit();
			}

			else {
				dbUtility.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception x) {
				x.getStackTrace();
			}
		}
	}

	/* GET FONCTION LIST */
	// public ArrayList getFunctionList()throws Exception
	// {
	// try {
	// sql = FnActivityCommonSQL.SELECT_FUCTION_MASTER;
	// dbUtility = new DBUtility();
	// dbUtility.createStatement();
	//
	// ArrayList mainList1= dbUtility.executeQuery(sql);
	// for (int i=0;i<mainList1.size();i++)
	// {
	// subList = (ArrayList)mainList1.get(i);
	// dto = new FnActivityDTO();
	// dto.setValue(subList.get(0).toString());
	// dto.setName(subList.get(1).toString());
	// functionList.add(dto);
	// //dbUtility.commit();
	// }
	// } catch (Exception e) {
	// e.getStackTrace();
	//
	// }finally {
	// dbUtility.closeConnection();
	// }
	// return functionList;
	//
	// }
	/* GET ROLE LIST */
	public ArrayList getRoleList() throws Exception {
		try {
			sql = FnActivityCommonSQL.SELECT_ROLE_MASTER;
			dbUtility = new DBUtility();
			dbUtility.createStatement();

			ArrayList mainList1 = dbUtility.executeQuery(sql);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				dto = new FnActivityDTO();
				dto.setValue(subList.get(0).toString());
				dto.setName(subList.get(1).toString());
				roleList.add(dto);
				// dbUtility.commit();
			}
		} catch (Exception e) {
			e.getStackTrace();
			dbUtility.rollback();
		} finally {
			dbUtility.closeConnection();
		}
		return roleList;

	}

	/* GET ACTIVITYLIST */
	// public ArrayList getActivityList()throws Exception
	// {
	// try {
	// sql = FnActivityCommonSQL.SELECT_ACTIVITY_MASTER;
	// dbUtility = new DBUtility();
	// dbUtility.createStatement();
	//
	// ArrayList mainList1= dbUtility.executeQuery(sql);
	// for (int i=0;i<mainList1.size();i++)
	// {
	// subList = (ArrayList)mainList1.get(i);
	// dto = new FnActivityDTO();
	// dto.setValue(subList.get(0).toString());
	// dto.setName(subList.get(1).toString());
	// activityList.add(dto);
	// //dbUtility.commit();
	// }
	// } catch (Exception e) {
	// e.getStackTrace();
	// }finally {
	// dbUtility.closeConnection();
	// }
	// return activityList;
	//
	// }
	/* GET MODULE LIST */
	public ArrayList getModuleList() throws Exception {
		try {
			sql = FnActivityCommonSQL.SELECT_MODULE_MASTER;
			dbUtility = new DBUtility();
			dbUtility.createStatement();

			ArrayList mainList1 = dbUtility.executeQuery(sql);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				dto = new FnActivityDTO();
				dto.setModuleId(subList.get(0).toString());
				dto.setModuleName(subList.get(1).toString());
				moduleList.add(dto);
				// dbUtility.commit();
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return moduleList;

	}

	// public ArrayList getSubmoduleList()throws Exception
	// {
	// try {
	// dbUtility = new DBUtility();
	// sql = FnActivityCommonSQL.SELECT_SUB_MODULE_MASTER;
	// dbUtility.createStatement();
	//
	// ArrayList mainList1= dbUtility.executeQuery(sql);
	// for (int i=0;i<mainList1.size();i++)
	// {
	// subList = (ArrayList)mainList1.get(i);
	// dto = new FnActivityDTO();
	// dto.setValue(subList.get(0).toString());
	// dto.setName(subList.get(1).toString());
	// submoduleList.add(dto);
	// //dbUtility.commit();
	// }
	// } catch (Exception e) {
	// e.getStackTrace();
	// }finally {
	// dbUtility.closeConnection();
	// }
	// return submoduleList;
	//
	// }

	/* GET FNACTIVITYLIST */

	public ArrayList getFnactivityList() throws Exception {
		try {
			sql = FnActivityCommonSQL.SELECT_ROLE_FN__ACTIVITY_MAPPING_MASTER;
			dbUtility = new DBUtility();
			dbUtility.createStatement();

			ArrayList mainList1 = dbUtility.executeQuery(sql);

			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				dto = new FnActivityDTO();
				dto.setFunctionId(subList.get(0).toString());
				dto.setActivityId(subList.get(1).toString());
				dto.setRoleId(subList.get(2).toString());
				dto.setModuleId(subList.get(3).toString());
				dto.setSubmoduleId(subList.get(4).toString());
				// -- payement flag and activity flag is not present in table
				/*
				 * dto.setActiveFlag(subList.get(2).toString());
				 * dto.setPaymentFlag(subList.get(4).toString());
				 */

				fnactivityList.add(dto);
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return fnactivityList;
	}

	/* UPDATE FNACTIVITYMAPPING MASTER */

	public void updateFnactivitymappingmaster(FnActivityForm fnactivityForm) {

		dto = new FnActivityDTO();
		String param[] = new String[3];
		param[0] = fnactivityForm.getDto().getActiveFlag();
		param[1] = fnactivityForm.getDto().getPaymentFlag();
		param[2] = fnactivityForm.getDto().getFunctionId();
		sql = FnActivityCommonSQL.UPDATE_ROLE_FN_ACTIVITY_MAPPING_MASTER;
		// LoggerMsg.debug("sql values in dao..."+sql);

		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
			}

			else {
				dbUtility.rollback();
			}

		} catch (Exception e) {
			e.getStackTrace();

		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception x) {
				x.getStackTrace();
			}
		}

	}

	/* GET FUNCTION ID */
	public FnActivityDTO getFunctionId(String fnid) throws Exception {
		try {
			sql = FnActivityCommonSQL.SELECT_ROLE_FN_ACTIVITY_MAPPING_MASTER_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = fnid;
			ArrayList list = dbUtility.executeQuery(sd);
			ArrayList list1 = (ArrayList) list.get(0);
			dto = new FnActivityDTO();
			dto.setFunctionId(subList.get(0).toString());
			dto.setActivityId(subList.get(1).toString());
			dto.setActiveFlag(subList.get(2).toString());
			dto.setRoleId(subList.get(3).toString());
			dto.setPaymentFlag(subList.get(4).toString());
			dto.setModuleId(subList.get(5).toString());
			dto.setSubmoduleId(subList.get(6).toString());

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return dto;
	}

	public ArrayList getSubmoduleMasterList(String moduleID) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getSubModule(String) - start");
		}

		ArrayList list = new ArrayList();
		String SQL = FnActivityCommonSQL.SELECT_SUB_MODULE_LIST;
		String arry[] = new String[1];
		if (moduleID != null) {
			arry[0] = moduleID;
		}
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			logger.debug("In Sub Module List"
					+ "after creating preparedstatement");
			list = dbUtility.executeQuery(arry);
			logger.debug("Wipro in FnActivityDAO - "
					+ "getSubmodulelist() after excuteQuery");
		} catch (Exception x) {
			// x.printStackTrace();
			logger.debug(x);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
		return list;

	}

	/* GET FONCTION LIST */
	public ArrayList getFunctionMasterList(String subModuleID) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getFunctionList(String) - start");
		}

		ArrayList list = new ArrayList();
		String SQL = FnActivityCommonSQL.SELECT_FUNCTION_LIST;
		String arry[] = new String[1];
		if (subModuleID != null) {
			arry[0] = subModuleID;
		}
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			logger.debug("In Sub Module List"
					+ "after creating preparedstatement");
			list = dbUtility.executeQuery(arry);
			logger.debug("Wipro in FnActivityDAO - "
					+ "getFunctionList() after excuteQuery");
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}

		return list;
	}

	/* GET ACTIVITYLIST */
	public ArrayList getActivityMasterList(String functionID) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getActivityList(String) - start");
		}

		ArrayList list = new ArrayList();
		String SQL = FnActivityCommonSQL.SELECT_ACTIVITY_LIST;
		String arry[] = new String[1];
		if (functionID != null) {
			arry[0] = functionID;
		}
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			logger.debug("In Sub Module List"
					+ "after creating preparedstatement");
			list = dbUtility.executeQuery(arry);
			logger.debug("Wipro in FnActivityDAO - "
					+ "getActivityList() after excuteQuery");
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}

		return list;

	}

	public ArrayList getActivityMappedList(String functionID, String roleID) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getActivityList(String) - start");
		}

		ArrayList list = new ArrayList();
		String SQL = FnActivityCommonSQL.SELECT_MAPPED_ACTIVITY_LIST;
		String arry[] = new String[2];
		
			arry[0] = functionID;
			arry[1] = roleID;
		
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			logger.debug("In Sub Module List"
					+ "after creating preparedstatement");
			list = dbUtility.executeQuery(arry);
			logger.debug("Wipro in FnActivityDAO - "
					+ "getActivityList() after excuteQuery");
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}

		return list;

	}

	public boolean getcheckedinsertedid(String[] checkedid, String roleid, String modid, String submid, String fxnid) throws Exception {
		boolean inserted = false;
		ArrayList actids=null;
		ArrayList containedactvitiyid=new ArrayList();
		String str=FnActivityCommonSQL.INSERT_ROLE_FN_ACTIVITY_MAPPING;
		String presentEntry=FnActivityCommonSQL.SELECT_CONTAINS_ACTIVITY_IDS;
		String arry[]= new String[4];
		
		arry[0]=roleid;
		arry[1]=modid;
		arry[2]=submid;
		arry[3]=fxnid;
				
		dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(presentEntry);
		logger.debug("In Sub Module List"
				+ "after creating preparedstatement");
		actids = dbUtility.executeQuery(arry);
		logger.debug("Wipro in FnActivityDAO - "
				+ "getActivityList() after excuteQuery");
		                         
		
		if(actids!=null)
		{
			for (int i = 0; i < actids.size(); i++) {
				ArrayList rowList=(ArrayList) actids.get(i);
				containedactvitiyid.add((String)rowList.get(0));
			}
		}
		
		
		
		for(int i=0;i<checkedid.length;i++){
			
			
			if(!containedactvitiyid.contains(checkedid[i]))
			{
			String param[]={roleid,modid,submid,fxnid,checkedid[i]};
			dbUtility.createPreparedStatement(str);
			inserted = dbUtility.executeUpdate(param);
			}
      	  }
		
		
		return inserted;
	}

	public boolean getdeleteuncheckedid(String[] uncheckedid, String roleid,
			String modid, String submid, String fxnid)  {
		String str=FnActivityCommonSQL.DELETE_CONTAINS_ACTIVITY_IDS;
		boolean deleted = false;
		try {
		dbUtility = new DBUtility();
		for(int i=0;i<uncheckedid.length;i++){
		
			String param[]={roleid,modid,submid,fxnid,uncheckedid[i]};
			
				dbUtility.createPreparedStatement(str);
				deleted = dbUtility.executeUpdate(param);
			
			
				
	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return deleted;
		
	}

}
