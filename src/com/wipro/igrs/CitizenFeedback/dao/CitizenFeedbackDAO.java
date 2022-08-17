
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
 * FILE NAME: CitizenFeedbackDAO.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 23th June 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE DAO FOR CITIZEN FEEDBACK ACTION.
 */

package com.wipro.igrs.CitizenFeedback.dao;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import com.wipro.igrs.CitizenFeedback.dto.CitizenFeedbackDTO;
import com.wipro.igrs.CitizenFeedback.form.CitizenFeedbackForm;
import com.wipro.igrs.CitizenFeedback.sql.CitizenFeedbackSQL;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;

/**
 * @author NIHAR M.
 *
 */
public class CitizenFeedbackDAO {


	Logger logger = (Logger) Logger.getLogger(CitizenFeedbackDAO.class);
	//DBUtility dbUtil;

	/**
	 * @throws Exception
	 */
	public CitizenFeedbackDAO() throws Exception{
	//	dbUtil = new DBUtility();
	}
	public ArrayList getFServices() throws Exception {
		  
		DBUtility	 dbUtil = new DBUtility();
		ArrayList list = null;
		 
		try {
			
			String arr[]=new String[1];
	        //System.out.println("1");
	        arr[0]= "Y";
	        
	        /*String sql ="select module_id, module_name from igrs_module_master where FEEDBACK_FLAG = ?";
			*/
	        dbUtil.createPreparedStatement(CitizenFeedbackSQL.SELECT_MODULE_NAME);
			 //System.out.println("heyyyyyy");
			 list = dbUtil.executeQuery(arr);
			 //System.out.println(list);
			 //System.out.println("just printed query");
		} catch (Exception e) {
			//System.out.println("Exception in getFServices():" + e);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				
				}
			}catch(Exception x) {
				//System.out.println(x);
			}
		}
		//System.out.println("returning value");
		return list;
	}
	






	public ArrayList getFdistrict() throws Exception {
		{				DBUtility	 dbUtil = new DBUtility();
			ArrayList list = null;
			 
			try {
				 dbUtil.createStatement();
				 list = dbUtil.executeQuery(CitizenFeedbackSQL.SELECT_DISTRICT);
			} catch (Exception e) {
				//System.out.println("Exception in getFdistrict():" + e);
			}finally {
				try {
					if (dbUtil != null) {
						dbUtil.closeConnection();
					}
				}catch(Exception x) {
					//System.out.println(x);
				}
			}
			//System.out.println("Returning from FDistrict");
			return list;
		}
		
	}

	public String getFinsertValue( CitizenFeedbackDTO  insertForm,String language ) throws Exception {
		{	DBUtility	 dbUtil = new DBUtility();
			String list="" ;
			boolean up=false;
			  
			//System.out.println(insertForm.getCityDistrictId());
			try {                                                                                    //-----Added by Satbir Kumar-----
				
					
					
					
					Date date = new Date();
					Format yearformat  = new SimpleDateFormat("yy");
			        Format monthformat = new SimpleDateFormat("MM");
			        Format dateformat  = new SimpleDateFormat("dd");
			        String dfmt = dateformat.format(date);
			        String yfmt = yearformat.format(date);
			        String mfmt = monthformat.format(date);
			        String ref_no=dfmt+mfmt+yfmt;
					
					
					String val=insertForm.getOption();
					//System.out.println("val>>>>>"+val);
					 
					 
				    //System.out.println("in function");
 
					String name = insertForm.getFirstName();
					//System.out.println("after name");
			        String EmailID = insertForm.getEmailID();
			       // System.out.println("after email"+EmailID);
			        String Services=insertForm.getFeedbackFunctionID();
			        //System.out.println("after services"+Services);
			        String District=insertForm.getCityDistrictId();
			        //System.out.println("after services"+District);
			        String FeedComp=insertForm.getFeedback();
			        //System.out.println("before query"+FeedComp);
			        String created_name = insertForm.getFirstName();
					//System.out.println("after Created By name"+created_name);
			        				   
				   /* String SQL1 = "select IGRS_BANKCAVEAT_SEQ.NEXTVAL from dual";
				   */ 
					dbUtil.createStatement();
				    				      
				   				    
				    String number2 = dbUtil.executeQry(CitizenFeedbackSQL.SELECT_SEQUENCE1);
		              if(number2.length()==1){
		                    number2="00000"+number2;  
		                    }else
		                    if(number2.length()==2){
		                          number2="0000"+number2;  
		                    }else
		                    if(number2.length()==3){
		                          number2="000"+number2;  
		                    }else
		                    if(number2.length()==4){
		                          number2="00"+number2;  
		                    }
		                    else
			                    if(number2.length()==5){
			                          number2="0"+number2;  
			                    }
		              
				      ref_no=val+dfmt+mfmt+yfmt+number2;
					
				     /* String SQL2 = "select IGRS_FEED_COMP_PRI_KEY.nextval from dual";
					 */
				      
				      dbUtil.createStatement();
					    				      
					   				   
					  String number3 = dbUtil.executeQry(CitizenFeedbackSQL.SELECT_SEQUENCE2);
				      
			        String arr[]=new String[10];
			        //System.out.println("1");
			        arr[0]=insertForm.getFirstName();
			        //System.out.println("2");
			        arr[1]=insertForm.getEmailID();
			        //System.out.println("3");
			        arr[2]=insertForm.getFeedbackFunctionID();
			        //System.out.println("4");
			        arr[3]=insertForm.getCityDistrictId();
			        //System.out.println("5");
			        arr[4]=insertForm.getFeedback();
			        //System.out.println("6");
			        arr[5]=insertForm.getFirstName();
			        //System.out.println("7");
			        arr[6]=ref_no;
			        //System.out.println("8");
			        arr[7]=number3;
			        //System.out.println("9");
			       
			        arr[8]="Pending";
			
			        
			       
			        //System.out.println("10");
			        
			       if(val.equalsIgnoreCase("F"))
			       {
			    	  
			    	   arr[9]="FEEDBACK";
						
			    	  
			       }
			        
			       else
			       {
			    	   
			    	   arr[9]="COMPLAINT";
						
			    	   
			       }
	              
				 /*String sql="insert into IGRS_FEEDBACK_COMPLAINT_MASTER"+
				 "(CLIENT_NAME,CLIENT_EMAIL_ID,SERVICE_NAME,DISTRICT_NAME,FEED_COMP_COMMENTS,CREATED_DATE,CREATED_BY,CASE_REF_NO,F_C_SEQ_NO,F_C_SATUS,FEEDBACK_COMPLAINT_FLAG)"+
				 "VALUES (?,?,?,?,?,SYSDATE,?,?,?,?,?)";*/
			     dbUtil.createPreparedStatement(CitizenFeedbackSQL.INSERT_REQUESTS);
			     up=dbUtil.executeUpdate(arr);
			     //System.out.println("after prep stmt");
			if(up){
				list=ref_no;
			}
			} catch (Exception e) {
				//System.out.println("Exception in getFinsertValue():" + e);
			}finally {
				try {
					if (dbUtil != null) {
						dbUtil.closeConnection();
					}
				}catch(Exception x) {
					//System.out.println(x);
				}
			}
			//System.out.println("Returning from FInsert");
			
			return list;
		}
		
	}
	
	public ArrayList getDetails(String refId) throws Exception{                                    //----added by Satbir Kumar-----
	{
		DBUtility	 dbUtil = new DBUtility();
		ArrayList list = null;
		 
		try {
			 String arr[]=new String[1];
		        //System.out.println("1");
		        arr[0]= refId;
			/*String sql="select CLIENT_NAME,CLIENT_EMAIL_ID from IGRS_FEEDBACK_COMPLAINT_MASTER where CASE_REF_NO = ?";
			*/ 
			dbUtil.createPreparedStatement(CitizenFeedbackSQL.GET_CLIENT_NAME);
			 
			 list = dbUtil.executeQuery(arr);
		} catch (Exception e) {
			//System.out.println("Exception in getDetails():" + e);
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
		//System.out.println("Returning from getDetails");
		return list;
	}
		
	}
	
	/**
	 * @return ArrayList
	 */
	public ArrayList getCountry(){

		ArrayList list = null;
		IGRSCommon igrsCommon;
		try {
			igrsCommon = new IGRSCommon();
			list = igrsCommon.getCountry();
		} 
		catch (Exception e) {
			logger.debug("DAO:: Exception in getCountry():" + e);
		}
		return list;
	}


	/**
	 * @return ArrayList
	 */
	public ArrayList getState(String countryId){

		ArrayList list = null;
		IGRSCommon igrsCommon;
		try {
			if (countryId == null) {
				countryId = "INDIA";
			}
			igrsCommon = new IGRSCommon();
			list = igrsCommon.getState(countryId);
		} catch (Exception e) {
			logger.debug("Exception in getState():" + e);
		}
		return list;
	}


	/**
	 * @param stateId
	 * @return ArrayList
	 */
	
	
	public ArrayList getDistrict(String stateId){

		ArrayList list = null;
		IGRSCommon igrsCommon;
		try {
			igrsCommon = new IGRSCommon();
			list = igrsCommon.getDistrict(stateId);
		} catch (Exception e) {
			logger.debug("Exception in getDistrict():" + e);
		}
		return list;
	}


	/**
	 * @param type
	 * @return ArrayList
	 */
	
	
/*	public ArrayList getType(String type) {
		ArrayList list = null;
		IGRSCommon igrsCommon;
		try {
			igrsCommon = new IGRSCommon();
			list = igrsCommon.getPhotoIdProof();
		} catch (Exception e) {
			logger.debug("Exception in getCommon():" + e);
		}
		return list;
	}*/


	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getFeedBackIDs() throws Exception {
		ArrayList list=null;
		DBUtility dbUtil=null;
		try{
		dbUtil.createStatement();
		//System.out.println("getFeedBackIDs() after create");
		list = dbUtil.executeQuery(CitizenFeedbackSQL.GET_FUNCTION_NAME);
		
		//System.out.println("getFeedBackIDs() after execute"
				//+ " query");

		logger.debug("listttttttttt:----------  "+list);
		}catch(Exception e)
		{
			
		}finally
		{
			dbUtil.closeConnection();
		}
		return list;

	}


	/**
	 * @param param
	 * @return boolean
	 */
	public boolean submitFeedbackDetails(String[] param){

		logger.debug("Called: DAO:: submitFeedbackDetails()");
		boolean docsInserted = false;
		DBUtility dbUtil=null;
		try {
			logger.debug("BEFORE ");
			dbUtil= new DBUtility();
			dbUtil.createPreparedStatement(CitizenFeedbackSQL.INSERT_CITIZEN_FFEDBACK_DETAILS);
			logger.debug("Query:- "+CitizenFeedbackSQL.INSERT_CITIZEN_FFEDBACK_DETAILS);
			docsInserted = dbUtil.executeUpdate(param);
			logger.debug("AFTER ");
		} catch (Exception x) {
			logger.debug("Exception in submitFeedbackDetails() :-   " + x);
			x.printStackTrace();
		}

		if(docsInserted){
			logger.debug("DAO:: submitFeedbackDetails:: - docsInserted:-  "+docsInserted);
			try {
				dbUtil.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return docsInserted;

	}
	public boolean mailtouser(String refId, CitizenFeedbackDTO objdto) throws Exception {
		
		boolean mailsent=false;
		
		DBUtility	 dbUtil = new DBUtility();
		
		String servicename=null;
		
		String id = objdto.getFeedbackFunctionID();
		
		      logger.debug("service id is"+ id);
		      
		      try
		      {
			  
		      String temp[] = new String[1];
		      
		      temp[0] = id;
		      
		     /* String SQL1 = "select MODULE_NAME from IGRS_MODULE_MASTER where MODULE_ID ='"+id+"'";
		     */ 
			    dbUtil.createPreparedStatement(CitizenFeedbackSQL.GET_MODULE_NAME);
			    				      
			   				    
			     servicename = dbUtil.executeQry(temp);
			     
			     logger.debug("service name is"+servicename);
		      
		      
		
		    String arr[]=new String[3];
	        //System.out.println("1");
	        arr[0]=objdto.getEmailID();
	        //System.out.println("2");
	        arr[1]="Your RequestID :"+ refId;
	        //System.out.println("3");
	        arr[2]= "Your filed query regarding service  "  +  servicename + "  has been sent to the respective D.R and will be processed accordingly";
	      
	        
	        
	        /*String sql = "INSERT INTO IGRS_EMAIL_DATA_CONTENT(EMAIL_DISPATCH_ID, EMAIL_TO_ADDRESS,"+
	        	    "EMAIL_SUBJECT, EMAIL_CONTENT, EMAIL_HEADER, EMAIL_FOOTER, EMAIL_SENT_FLAG, CREATED_BY,"+ 
	        		"CREATED_DATE, UPDATE_BY, UPDATE_DATE, EMAIL_FROM_ADDRESS)"+
	        	    "VALUES(IGRS_EMAIL_SENT_SEQ1.NEXTVAL,?,?,?,'Dear Sir/Madam','Thanks & Regards','N','System',SYSDATE,'System',SYSDATE,'igrs@mp.gov')";
	        */
	        dbUtil.createPreparedStatement(CitizenFeedbackSQL.INSERT_FIRST_EMAIL_CONTENT);
	        mailsent =dbUtil.executeUpdate(arr); 
		
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
		return mailsent;
	}
}
