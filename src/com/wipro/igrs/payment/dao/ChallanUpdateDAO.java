package com.wipro.igrs.payment.dao;
import org.apache.log4j.Logger;
import com.wipro.igrs.common.IGRSCommon;

import com.wipro.igrs.db.DBUtility;

import com.wipro.igrs.payment.sql.CommonSQL;

import com.wipro.igrs.payment.constant.CommonConstant;
import com.wipro.igrs.payment.form.ChallanGenForm;

import java.util.ArrayList;
/**
 * ===========================================================================
 * File           :   ChallanUpdateDAO.java
 * Description    :   Represents the Challan Generation update DAO Class
 * Author         :   vengamamba P
 * Created Date   :    Mar17, 2008

 * ===========================================================================
 */
public class ChallanUpdateDAO {
	ArrayList list = null;
	String query;
	DBUtility dbUtil=null; // obj for dbutility
	private Logger logger = (Logger) Logger.getLogger(ChallanUpdateDAO.class);
	public ChallanUpdateDAO() throws Exception {
		//dbUtil = new DBUtility(); // creating connection to db

	}

	/**
     * ===========================================================================
     * Method         :   getUseridList()
     * Description    :   Returns list  of userids  . 
     * Arguments      :   
     * return type    :   Arraylist
     
     * ===========================================================================
     */  
	public ArrayList getUseridList() {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.USER_ID_LIST;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in getUseridList(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return list;
	}
	
	/**
     * ===========================================================================
     * Method         :   getbankidList()
     * Description    :   Returns list  of bankids and its names  . 
     * Arguments      :   
     * return type    :   Arraylist
     
     * ===========================================================================
     */  
	public ArrayList getBankidList() {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.BANK_ID_LIST;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in getBankidList(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return list;
	}
	/**
     * Method 		: updateChallan
     * Description	: returns boolean of updation or not 
     * @param query :  array of string 
     * return Type  :boolean 
     */
	public boolean updateChallan(String[] param)throws Exception{
		
    	boolean chupdate = false;	
    
    	try {
    		dbUtil = new DBUtility();
    		logger.debug("before updating challan data ");
    		dbUtil = new DBUtility();
    		dbUtil.createPreparedStatement(CommonSQL.CHALLAN_GEN_UPDATE);
    		logger.debug(CommonSQL.CHALLAN_GEN_UPDATE);
    		chupdate = dbUtil.executeUpdate(param);
    		logger.debug("after updating challan data "+chupdate);
    	  		
    		}
    	catch (Exception x) {
    		
    		logger.error("Exception in updatechallan() :- " + x);
    	}
		finally {
        	try {
        		if(!chupdate) {
                    
            		dbUtil.rollback();
            	}	
        		else
        			dbUtil.commit();
            	dbUtil.closeConnection();
        	} catch (Exception ex) {
        		logger.error("Exception in connection() :-" + ex);
        	}
    	}
    return chupdate;
    }

	/**
     * Method 		: getChallanStatus
     * Description	: RETURNING CHALANGEN delivery status BASED ON txnid
     * @param query : String
     * @throws 		: 
     * return Type  :String 
     */
 
 public String getChallanStatus(String txnid) {
	 String status="";
	 try {
		 dbUtil = new DBUtility();
		 query = CommonSQL.CHALLAN_GEN_UPDATE_STATUS;
		 logger.debug("the query is  in DAO   " + query);
		 String query1=query+"'"+txnid+"'";
		 logger.debug("the query is  in DAO   " + query1);
		 dbUtil.createStatement(); 
		 status=dbUtil.executeQry(query1);
		 logger.debug("the values in status is  " + status);
	 	} catch (Exception x) {
			logger.error("Exception in getChallanGenData(): " + x);
			
	 		} finally {
	 			try {
	 				dbUtil.closeConnection();
	 			} catch (Exception ex) {
	 				logger.debug("Exception in closing connection :-" + ex);
	 				}
	 		}
		return status;
	
 } 
	 /**
     * Method 		: getChallandata
     * Description	: returns list of CHALANGEN DETAILS 
     * @param query :  string 
     * return Type  :ArrayList
     */
	
	public ArrayList getChallandata(String[] p) {

		try {
			dbUtil = new DBUtility();
			//getting challangen details from igrs_challan_gen_details
			query = CommonSQL.CHALLAN_GEN_UPDATE_LIST;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createPreparedStatement(query);
			list = new ArrayList();
			list = dbUtil.executeQuery(p);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in getChallanGenData(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in closing connection :-" + ex);
			}
		}
		return list;
	}
	
}