
/* Copyright (c) 2007-08 WIPRO INFOTECH. All Rights Reserved.
 * 
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *  
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE 
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE 
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF 
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS 
 * DERIVATIVES.
 */ 

/* 
 * FILE NAME: CitizenFeedbackViewDAO.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 23th June 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE DAO FOR CITIZEN VIEW ACTION.
 */

package com.wipro.igrs.CitizenFeedback.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.wipro.igrs.CitizenFeedback.dto.CitizenFeedbackDTO;
import com.wipro.igrs.CitizenFeedback.sql.CitizenFeedbackSQL;
import com.wipro.igrs.caveats.sql.CommonSQL;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.CitizenFeedback.sql.CitizenFeedbackSQL;

/**
 * @author NIHAR M.
 *
 */
public class CitizenFeedbackViewDAO {


	Logger logger = (Logger) Logger.getLogger(CitizenFeedbackDAO.class);
	//DBUtility dbUtil;

	/**
	 * @throws Exception
	 */
	public CitizenFeedbackViewDAO() throws Exception{
		//dbUtil = new DBUtility();
	}


	/**
	 * @param userId 
	 * @param uname
	 * @return ArrayList
	 */
	
	public ArrayList getPendingEstampApps(String officeid, String userId,String language)
	{
		ArrayList pendingAppList = new ArrayList();
		//ArrayList pendingAppListFinal = new ArrayList();
		
		
		
		//ArrayList list1 = new ArrayList();
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			
			String sql1 = CitizenFeedbackSQL.SELECT_ROLEGROUP_NAME;
		 	String arr1[]=new String[1];
	        arr1[0]=userId;
	        
	        dbUtil.createPreparedStatement(sql1);
			String rolegroup = dbUtil.executeQry(arr1);
				
					
					try
					{
						if(rolegroup.equalsIgnoreCase("IGRSAdminRoleGroup")==false)
						{
						String arr[]=new String[2];
						
						arr[0]="Pending";
						
						
						arr[1]=officeid;
						
						/*String sql = "Select F.CASE_REF_NO,F.CLIENT_NAME,F.CLIENT_EMAIL_ID,to_char(F.CREATED_DATE,'DD/MM/YYYY'),F.FEEDBACK_COMPLAINT_FLAG" +
								",M.MODULE_NAME,F.F_C_SATUS from IGRS_FEEDBACK_COMPLAINT_MASTER F,igrs_module_master M where F.F_C_SATUS = ? AND " +
								"F.DISTRICT_NAME IN(select DISTRICT_ID from IGRS_OFFICE_MASTER where OFFICE_ID = ?) AND F.SERVICE_NAME = M.MODULE_ID ORDER BY F.CREATED_DATE DESC ";*/
						//----common sql get district wise pending feebackdetails
						dbUtil.createPreparedStatement(CitizenFeedbackSQL.GET_DISTRICT_WISE_PENDING_FEEDBACK_DETAILS);

						pendingAppList=dbUtil.executeQuery(arr);
				            //logger.debug("Wipro in EstampDAO - getPendingEstampApps() after dbUtil.executeQuery(SQL)");
				           
				            //pendingAppList.trimToSize();
						
						//System.out.println(pendingAppList);
					}
		
						else
						{
							String arr[]=new String[1];
							//arr[0]="Pending";
							if("en".equalsIgnoreCase(language))
							{
							arr[0]="Pending";
							}
							else if("hi".equalsIgnoreCase(language))
							{
								arr[0]="लंबित";
							}
							
							/*String sql = "Select F.CASE_REF_NO,F.CLIENT_NAME,F.CLIENT_EMAIL_ID,to_char(F.CREATED_DATE,'DD/MM/YYYY'),F.FEEDBACK_COMPLAINT_FLAG" +
									",M.MODULE_NAME,F.F_C_SATUS from IGRS_FEEDBACK_COMPLAINT_MASTER F,igrs_module_master M where F.F_C_SATUS = ? AND F.SERVICE_NAME = M.MODULE_ID ORDER BY F.CREATED_DATE DESC ";*/
							//----common sql get pending feebackdetails
							dbUtil.createPreparedStatement(CitizenFeedbackSQL.GET_PENDING_FEEDBACK_DETAILS);

							pendingAppList=dbUtil.executeQuery(arr);
					            //logger.debug("Wipro in EstampDAO - getPendingEstampApps() after dbUtil.executeQuery(SQL)");
					           
					            //pendingAppList.trimToSize();
							
							//System.out.println(pendingAppList);
						}
					
					
					} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return pendingAppList;
		}
	
	
	public ArrayList getDetails(String id)
	{
	
		ArrayList details = new ArrayList();
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			
					 
				
					
					try
					{
						String arr[]=new String[1];
						arr[0]=id;
						
						/*String sql = "SELECT F.CLIENT_NAME ,F.CLIENT_EMAIL_ID,D.DISTRICT_NAME,M.MODULE_NAME,F.FEED_COMP_COMMENTS,F.F_C_SATUS,F.DR_EMAIL_CONTENT FROM IGRS_FEEDBACK_COMPLAINT_MASTER F, IGRS_DISTRICT_MASTER D,"+

									"IGRS_MODULE_MASTER M WHERE F.CASE_REF_NO = ?  AND F.DISTRICT_NAME= D.DISTRICT_ID AND F.SERVICE_NAME = M.MODULE_ID";*/
						//----common sql get view details
						dbUtil.createPreparedStatement(CitizenFeedbackSQL.GET_VIEW_DETAILS);

						details=dbUtil.executeQuery(arr);
				            
						
						//System.out.println(details);
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return details;
		}
		
		
	public ArrayList getDistrictName(String districtId){	
		ArrayList alist = new ArrayList();
		String param[]=new String[1];
		param[0]=districtId;
		DBUtility dbUtil=null;
		
		try {
			dbUtil=new DBUtility();
			String sql=CitizenFeedbackSQL.SELECT_DISTRICT_NAME_H_E;
			dbUtil.createPreparedStatement(sql);
			alist = dbUtil.executeQuery(param);
			logger
					.debug("After getting the country ID ... " + districtId);
		} catch (Exception x) {
			logger.error("Exception in getCountryId :- " + x);
		}finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	
		
		return alist;
	}
		
	
	
	public ArrayList validateFeedbackID(String[] uname)
	{
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil= new DBUtility();
			dbUtil.createPreparedStatement(CitizenFeedbackSQL.CHECK_FEEDBACK_ID);
			list = dbUtil.executeQuery(uname);
			//System.out.println("DAO :-  "+list);
		} catch (Exception x) {
			logger.debug("Exception in validateFeedbackID() :- " + x);
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;

	}
	
	
	/**
	 * @param feedbackID
	 * @return
	 */
	public ArrayList viewCitizenFeedback(String[] feedbackID) {

		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			logger.debug("before getting desposit ");
			dbUtil= new DBUtility();
			dbUtil.createPreparedStatement(CitizenFeedbackSQL.VIEW_CITIZEN_FEEDBACK_DETAILS);
			logger.debug("SQL:" + CitizenFeedbackSQL.VIEW_CITIZEN_FEEDBACK_DETAILS);
			list = dbUtil.executeQuery(feedbackID);
			logger.debug("after getting agent ");
		} catch (Exception x) {
			logger.debug("Exception in getWillDeposit() :- " + x);
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return list;
		
	}
	
	
	public boolean getemail(CitizenFeedbackDTO citizenDTO) throws Exception {
		
		boolean success=false;
		DBUtility dbUtil=null;
		dbUtil=new DBUtility();
		try
		{
		String Id= citizenDTO.getReferenceId();
		
		//System.out.println(citizenDTO.getFeedbackFunctionName());
		
		//System.out.println(citizenDTO.getFeedbackFunctionID());
		
		//System.out.println(Id);
		
		 String arr[]=new String[3];
	        //System.out.println("1");
	        arr[0]=citizenDTO.getEmailID();
	        //System.out.println("2");
	        arr[1]= "Query processing completed Regarding RequestID :" +Id;
	       // System.out.println("3");
	        arr[2]=citizenDTO.getComments();
	        
	        
	        
	       /* String sql = "INSERT INTO IGRS_EMAIL_DATA_CONTENT(EMAIL_DISPATCH_ID, EMAIL_TO_ADDRESS,"+
	        	    "EMAIL_SUBJECT, EMAIL_CONTENT, EMAIL_HEADER, EMAIL_FOOTER, EMAIL_SENT_FLAG, CREATED_BY,"+ 
	        		"CREATED_DATE, UPDATE_BY, UPDATE_DATE, EMAIL_FROM_ADDRESS)"+
	        	    "VALUES(IGRS_EMAIL_SENT_SEQ1.NEXTVAL,?,?,"+
	        		"?,'Dear Sir/Madam','Thanks & Regards','N','System',SYSDATE,'System',SYSDATE,'igrs@mp.gov')";*/
	      //----common sql insert mail
	        
	        
	        dbUtil.createPreparedStatement(CitizenFeedbackSQL.INSERT_EMAIL_CONTENT);
	        success =dbUtil.executeUpdate(arr);
	        
	        String ary[]=new String[2];
	        ary[0]=citizenDTO.getComments();
	        ary[1] = Id;		
	      /*  String sql2 = "UPDATE IGRS_FEEDBACK_COMPLAINT_MASTER SET DR_EMAIL_CONTENT = ? WHERE CASE_REF_NO = ?";*/
	      //----common sql update comments
	        dbUtil.createPreparedStatement(CitizenFeedbackSQL.INSERT_UPDATE_COMMENTS);
	        dbUtil.executeUpdate(ary);
	        
	       /* String ar1[]= new String[1];
	        ar1[0]= citizenDTO.getComments();
	        dbUtil.createPreparedStatement(CitizenFeedbackSQL.INSERT);
	        dbUtil.executeUpdate(ar1);*/
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				//System.out.println(x);
			}
		}
		
	        		return success;
	}


	public boolean updatestatus(String id, String language) throws Exception {
		
		boolean update=false;
		DBUtility dbUtil=null;
		dbUtil=new DBUtility();
		
		try
		{
		String arr[]=new String[2];
        //System.out.println("1");
		
        arr[0]="Completed";
			
		
			 // System.out.println("2");
        arr[1]=id;
        //System.out.println("3");
		
		/*String sql = "update IGRS_FEEDBACK_COMPLAINT_MASTER SET F_C_SATUS = ? WHERE CASE_REF_NO=?";*/
		//----common sql set status
		dbUtil.createPreparedStatement(CitizenFeedbackSQL.UPDATE_SET_STATUS);
		update = dbUtil.executeUpdate(arr);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				//System.out.println(x);
			}
		}
		return update;
	}


	public ArrayList getFilteredresults(String param[], String userId,String language) throws Exception {
		
		
		     String sql = "";//CitizenFeedbackSQL.GET_SEARCH_DETAILS_QUERY;
		     DBUtility dbUtil=null;
		     dbUtil= new DBUtility();
		     String sql1 = CitizenFeedbackSQL.SELECT_ROLEGROUP_NAME;
		 	String arr[]=new String[1];
	        arr[0]=userId;
	        
	        dbUtil.createPreparedStatement(sql1);
			String rolegroup = dbUtil.executeQry(arr);
	        
	        

		 	ArrayList retVal = new ArrayList();
			ArrayList queryResult;
			StringBuilder stbr = new StringBuilder(CitizenFeedbackSQL.GET_SEARCH_DETAILS_QUERY);
//			param[0] = _refid;
//			param[1] = _RegNo;
//			param[2] = _status;
//			param[3] = _frDate;
//			param[4] = _toDate;
			
			 
			
			
			TreeMap<String, String> paramsMap = new TreeMap<String, String>();
			try {
				if(rolegroup.equalsIgnoreCase("IGRSAdminRoleGroup")==false)
				{	stbr.append(CitizenFeedbackSQL.FEEDBACK_QUERY_OFFCID_CLAUSE);
					paramsMap.put("0Offcid", param[0]);
				}
				if ("".equals(param[1]) == false) {
					stbr.append(CitizenFeedbackSQL.FEEDBACK_QUERY_REFID_CLAUSE);
					paramsMap.put("1refID", param[1]);
				}
				if ("".equals(param[2]) == false) {
					stbr.append(CitizenFeedbackSQL.FEEDBACK_QUERY_STATUS_CLAUSE);
					paramsMap.put("2status", param[2]);
				}
				if (("".equals(param[3]) == false)
						&& ("".equals(param[4]) == false)) {
					stbr.append(CitizenFeedbackSQL.FEEDBACK_QUERY_DATE_BOTH_CLAUSE);
					
					
					paramsMap.put("3fromDate", param[3]);
					paramsMap.put("4toDate", param[4]);
				} 
				
				stbr.append(CitizenFeedbackSQL.FEEDBACK_ORDER_BY_DATE_CLAUSE);
				
				logger.info("Final build query : " + stbr.toString());
				logger.info("Params mapping : " + paramsMap);
				try {
					
					
					Collection<String> values = paramsMap.values();
					ArrayList<String> tmp = new ArrayList(values);
//					Collections.reverse(tmp);
					String[] params = tmp.toArray(new String[]{}); 
					logger.info("query params : " + params);
					queryResult = dbUtil.getSearchDetails(stbr.toString(), params,language);
					retVal.addAll(queryResult);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			finally
			{
				dbUtil.closeConnection();
			}
			return retVal;
		}
}