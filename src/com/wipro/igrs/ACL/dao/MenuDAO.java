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

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wipro.igrs.UserRegistration.sql.CommonSQL;
import com.wipro.igrs.db.DBUtility;
//import com.wipro.igrs.db.DBUtils;

/**
 * @author neegaga
 * 
 */
public class MenuDAO {
	
	DBUtility dbUtil=new DBUtility();
	//DBUtils instance = DBUtils.getInstance(); //added by roopam
	CallableStatement clstmt;                 //added by roopam
	PreparedStatement pstmt;                  //added by roopam

	//DBUtility dbUtil;
	@SuppressWarnings("unchecked")
	ArrayList list = null;
	private Logger logger = (Logger) Logger.getLogger(MenuDAO.class);

	/**
	 * @throws Exception
	 */
	public MenuDAO() throws Exception {

		
	}

	/** *****************************User Login**************************** */

	/**
	 * @param genPinparam
	 * @return
	 */
	public boolean authenticateUser(String[] param) {
		boolean searched = true;

		try {
			//dbUtil = new DBUtility();
			//logger.info("Before searching the User Details for the User Logging In ");
			dbUtil.createPreparedStatement(CommonSQL.SEARCH_EMP_DETAILS);
			//pstmt=instance.createPreparedStatement(CommonSQL.SEARCH_EMP_DETAILS);
			//logger.info("SQL:" + CommonSQL.SEARCH_EMP_DETAILS);
			//searched = instance.executeUpdate(param,pstmt);
			searched = dbUtil.executeUpdate(param);
			//logger
			//		.info("After searching the Employee Details for the Employee Logging In");
			if(searched){
				dbUtil.commit();
				//add commit statement here.
			}	
		} catch (Exception x) {
			//logger.info("Exception in authenticateUser() :- " + x);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return searched;
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList getRole(String userID) {
		try {
			//dbUtil = new DBUtility();
			
			String sql = " Select Role_ID from IGRS_EMP_ROLE_CADRE_GRADE_MAP where EMP_ID=? ";
			dbUtil.createPreparedStatement(sql);
			//pstmt=instance.createPreparedStatement(sql);
			logger.debug("SQL:" + sql);
			String[] param = new String[1];
			param[0] = userID;

			list = dbUtil.executeQuery(param);
			//list = instance.executeQuery(param,pstmt);
			logger.debug("After getting the RoleID for the logged In User... ");
		} catch (Exception x) {
			logger.error("Exception in getRole() :- " + x);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return list;
	}

	/**
	 * getting role name
	 * 
	 * @param userID
	 * @return added by pavani
	 */

	@SuppressWarnings("unchecked")
	public ArrayList getRoleName(String userID) {
		try {
			//dbUtil = new DBUtility();
			
			String sql = "SELECT ROLE_NAME FROM IGRS_EMP_ROLE_CADRE_GRADE_MAP RMAP,"
					+ "IGRS_ROLE_MASTER RM WHERE RMAP.ROLE_ID = RM.ROLE_ID AND RMAP.EMP_ID=?";
			dbUtil.createPreparedStatement(sql);
			//pstmt=instance.createPreparedStatement(sql);
			logger.debug("SQL:" + sql);
			String[] param = new String[1];
			param[0] = userID;

			list = dbUtil.executeQuery(param);
			//list = instance.executeQuery(param,pstmt);
			logger.debug("After getting the RoleID for the logged In User... ");
		} catch (Exception x) {
			logger.error("Exception in getRole() :- " + x);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return list;
	}

	/**
	 * @return
	 */
	
	 
	
	@SuppressWarnings("unchecked")
	private String[] getRoleID(HashMap rID) {
		String[] param = null;
		if(rID != null ) {
			 param = new String[rID.size()];
			
			Iterator I = rID.entrySet().iterator();
			int i = 0;
			while (I.hasNext()) { 
				Map.Entry me = (Map.Entry) I.next();
				logger.debug("key:-"+me.getKey());
				param[i] = (String) me.getKey();
				i++;
			}
			
		}
		return param;
	}
	@SuppressWarnings("unchecked")
	public ArrayList extractModules(HashMap rID) {
		try {
			//dbUtil = new DBUtility();   
			
			String[] param = getRoleID(rID);
			
			String SQL = CommonSQL.SEARCH_ROLE_MODULE;
			dbUtil.createStatement();
			//instance.createStatement();
			SQL  = SQL + "AND RFN.ROLE_ID IN ";
			
			String paramSQL = "";

			
			if(param!=null) {
				for(int i =0;i<param.length;i++) {
					if(param.length == 0) {
						SQL = SQL + "('"+param[i]+"')";
						break;
					}else {
						paramSQL = paramSQL + "'"+param[i]+"'";
						
						if(i != param.length-1) {
							paramSQL =paramSQL+",";
	                   }
					}
				}
				if(param.length > 0 ){
					paramSQL ="(" +paramSQL+")";
					SQL = SQL +paramSQL;
				}
				
			}
			logger.debug("SQL:" + SQL);
			
			list = dbUtil.executeQuery(SQL);
			//list = instance.executeQuery(SQL);
			logger
					.debug("After getting the Modules , the logged In User is authorised to access... ");
		} catch (Exception x) {
			logger.error("Exception in extractModules() :- " + x);
		}//finally added by roopam 
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return list;
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList extractSubModules(String mID, HashMap rID) {
		try {
			
			//dbUtil = new DBUtility();
			
			String SQL = CommonSQL.SEARCH_SUB_MODULE;
			
			dbUtil.createStatement();
			//instance.createStatement();
			String[] param = getRoleID(rID);
			
			 
			SQL  = SQL +"AND RFN.MODULE_ID ='"+mID+ "' AND RFN.ROLE_ID IN ";
			
			String paramSQL = "";

			
			if(param!=null) {
				for(int i =0;i<param.length;i++) {
					if(param.length == 0) {
						SQL = SQL + "('"+param[i]+"')";
						break;
					}else {
						paramSQL = paramSQL + "'"+param[i]+"'";
						
						if(i != param.length-1) {
							paramSQL =paramSQL+",";
	                   }
					}
				}
				if(param.length > 0 ){
					paramSQL ="(" +paramSQL+")";
					SQL = SQL +paramSQL;
				}
				
			}
			logger.debug("SQL:" + SQL);


			list = dbUtil.executeQuery(SQL);
			//list = instance.executeQuery(SQL);
			logger
					.debug("After getting the SubModules for the modules , the logged In User is"
							+ " authorised to access... ");
		} catch (Exception x) {
			logger.error("Exception in extractSubModules() :- " + x);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return list;
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList extractFunctions(String smID, HashMap rID) {
		try {
			//dbUtil = new DBUtility();
			
			String SQL = CommonSQL.SEARCH_FUNCTION;
			dbUtil.createStatement();
			//instance.createStatement();
			 
			 
			String[] param = getRoleID(rID);
			
			 
			SQL  = SQL +" AND RFN.SUB_MODULE_ID ='"+smID+ "' AND RFN.ROLE_ID IN ";
			
			String paramSQL = "";

			
			if(param!=null) {
				for(int i =0;i<param.length;i++) {
					if(param.length == 0) {
						SQL = SQL + "('"+param[i]+"')";
						break;
					}else {
						paramSQL = paramSQL + "'"+param[i]+"'";
						
						if(i != param.length-1) {
							paramSQL =paramSQL+",";
	                   }
					}
				}
				if(param.length > 0 ){
					paramSQL ="(" +paramSQL+")";
					SQL = SQL +paramSQL;
				}
				
			}
			
			logger.debug("Function SQL:-"+SQL);
			list = dbUtil.executeQuery(SQL);
			//list = instance.executeQuery(SQL);
			logger
					.debug("After getting the functions for the Submodules , the logged In User is"
							+ " authorised to access... ");
		} catch (Exception x) {
			logger.error("Exception in extractFunctions() :- " + x);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return list;
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList extractActivity(String fncID, HashMap rID) {
		try {
			//dbUtil = new DBUtility();
			
			String SQL = CommonSQL.SEARCH_ACTIVITIES;
			dbUtil.createStatement();
			//instance.createStatement();
			 
			 
			String[] param = getRoleID(rID);
			
			 
			SQL  = SQL +" AND RFN.Function_ID ='"+fncID+ "' AND RFN.ROLE_ID IN ";
			
			String paramSQL = "";

			
			if(param!=null) {
				for(int i =0;i<param.length;i++) {
					if(param.length == 0) {
						SQL = SQL + "('"+param[i]+"')";
						break;
					}else {
						paramSQL = paramSQL + "'"+param[i]+"'";
						
						if(i != param.length-1) {
							paramSQL =paramSQL+",";
	                   }
					}
				}
				if(param.length > 0 ){
					paramSQL ="(" +paramSQL+")";
					SQL = SQL +paramSQL;
				}
				/*for(int i=0; i<=500;i++){
					System.out.println("dddd"+SQL);
				}*/
				
			}
			
			list = dbUtil.executeQuery(SQL);
			//list = instance.executeQuery(SQL);
			
			logger
					.debug("After getting the activities for the functions , the logged In User is"
							+ " authorised to access... ");
		} catch (Exception x) {
			logger.error(x);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return list;
	}
	
	public ArrayList<Map<String, String>> getCompleteMenuDetails(String[] roles) {
		ArrayList<Map<String, String>> retVal = null;
		try {
			retVal = dbUtil.getCompleteMenuDetails(roles);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retVal;
		
	}

	public String getTypeOfUser(String uid) throws Exception{
		String typeOfUser="";
		try
		{
		//dbUtil= new DBUtility();	
		dbUtil.createStatement();
		String sql =com.wipro.igrs.login.sql.CommonSQL.GET_USER_TYPE;
		String[] temp = new String[1];
		temp[0]=uid;
		dbUtil.createPreparedStatement(sql);
		 typeOfUser=dbUtil.executeQry(temp);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return typeOfUser;
	}

	public ArrayList getRoleMappedToOffice(String ofcId) throws Exception {
		// TODO Auto-generated method stub
		ArrayList mainList = new ArrayList();
		
		try
		{
			//dbUtil=new DBUtility();
			dbUtil.createStatement();
			String sql =CommonSQL.GET_ROLE_MAPPED_TO_OFFICE+"'"+ofcId+"'";
			mainList= dbUtil.executeQuery(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		
		return mainList;
	}
	
}
	

