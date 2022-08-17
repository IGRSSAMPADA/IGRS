/* 
 * <Copyright information>
 *
 * <Customer specific copyright notice (if any) >
 *
 * ==============================================================================
 * This file contains proprietary information of Wipro Infotech Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007
 * ==============================================================================
 * 
 * File Name	   		: ReportDAO.java
 *
 * Description	   		: This class interacts with DbService for data 
 * 							persistance and data fetch
 *
 * Version Number  		: 0.0 
 *
 * Created Date	   		: 30 04 2008 
 *
 * Modification History	: Created
 */

package com.wipro.igrs.report.dao;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.report.dto.ReportingDTO;
import com.wipro.igrs.report.form.ReportingForm;
import com.wipro.igrs.report.sql.CommonSQL;

public class ReportingDAO {
	DBUtility dbutility;
	/** Creates a new instance of RevenueMgmtDAO */
	private Logger logger = 
		(Logger) Logger.getLogger(ReportingDAO.class);
	public ReportingDAO() {
		 
	}
	
	public ArrayList getDistrictDetails(ReportingForm reportForm) {
		// ...implementation goes here...
			logger.debug("in getDroNameDetails dao block start");
			ArrayList ret = new ArrayList();
			try {
				dbutility = new DBUtility();
				String sql = CommonSQL.DISTRICT_DETAILS;
				logger.debug("sql query===="+sql);
				dbutility.createStatement();
				ret = dbutility.executeQuery(sql);
			}catch(Exception err) {
				err.printStackTrace();
				logger.error("this is Exception in " +
				"getDroNameDetails DAO: " + err);
			}
			finally {
				try {
					dbutility.closeConnection();	
				}catch(Exception err) {
		  		err.getStackTrace();
		  		logger.error("this is Fianlly Try catch in " +
		  				"getDroNameDetails DAO " + err);        		
				}
			}        
		  logger.debug("in getDroNameDetails dao block end");
		  return ret;        
		}
		
	/**
	    * ...method getSroNameDetails documentation comment...
	    * @param reportForm description
	    * Description  : Retrieving values from IGRS_OFFICE_MASTER table
	    * Created Date : jun 4,2008
	    * Author       : vengs p
	    */
	public ArrayList getSroNameDetails(ReportingForm reportForm) {
	// ...implementation goes here...
		logger.debug("in getDroNameDetails dao block start");
		ArrayList ret = new ArrayList();
		try {
			dbutility = new DBUtility();
			String sql = CommonSQL.SRO_DETAILS;
			logger.debug("sql query===="+sql);
			dbutility.createStatement();
			ret = dbutility.executeQuery(sql);
		}catch(Exception err) {
			err.printStackTrace();
			logger.error("this is Exception in " +
			"getDroNameDetails DAO: " + err);
		}
		finally {
			try {
				dbutility.closeConnection();	
			}catch(Exception err) {
	  		err.getStackTrace();
	  		logger.error("this is Fianlly Try catch in " +
	  				"getDroNameDetails DAO " + err);        		
			}
		}        
	  logger.debug("in getDroNameDetails dao block end");
	  return ret;        
	}
	
	/**
	    * ...method getDroNameDetails documentation comment...
	    * @param reportForm description
	    * Description  : Retrieving values from IGRS_OFFICE_MASTER table
	    * Created Date : May 17,2008
	    * Author       : Sreelatha M
	    */
	public ArrayList getDroNameDetails(ReportingForm reportForm) {
	// ...implementation goes here...
		logger.debug("in getDroNameDetails dao block start");
		ArrayList ret = new ArrayList();
		try {
			String sql = CommonSQL.DRO_DETAILS;
			logger.debug("sql query===="+sql);
			dbutility = new DBUtility();
			dbutility.createStatement();
			ret = dbutility.executeQuery(sql);
		}catch(Exception err) {
			err.printStackTrace();
			logger.error("this is Exception in " +
			"getDroNameDetails DAO: " + err);
		}
		finally {
			try {
				dbutility.closeConnection();	
			}catch(Exception err) {
	  		err.getStackTrace();
	  		logger.error("this is Fianlly Try catch in " +
	  				"getDroNameDetails DAO " + err);        		
			}
		}        
	  logger.debug("in getDroNameDetails dao block end");
	  return ret;        
	}
	/**
     * ===========================================================================
     * Method         :  getRevenueReceiptDetails
     * Description    :   Returns list  of no of institutions ,no of docs and RevenueReceipts 
     * Arguments      :  SQL,PARAM LIST
     * return type    :   Arraylist
     * Author         :   vengamamba P
     * Created Date   :   jun 4, 2008
     * ===========================================================================
     */  
	 public ArrayList getRevenueReceiptDetails(String SQL,String[] param) {
		 ArrayList ret = new ArrayList();
		 try {
			 dbutility = new DBUtility();
			  
			 logger.debug("SQL:-"+SQL);
			 dbutility.createPreparedStatement(SQL);
			 //dbutility.createStatement();
			 ret = dbutility.executeQuery(param);
		 }catch(Exception err) {
			 logger.error(err);
	     }
	     finally {
	     	try {
	     		if(dbutility != null)
	     		dbutility.closeConnection();	
	     	}
	     	catch(Exception err) {
	     		
	     		logger.error( err);             		
	     	}
	     }
	     return ret;
	 }
}
	
	
	

