package com.wipro.igrs.payment.dao;
import org.apache.log4j.Logger;
import com.wipro.igrs.common.IGRSCommon;

import com.wipro.igrs.db.DBUtility;

import com.wipro.igrs.payment.sql.CommonSQL;

import com.wipro.igrs.payment.constant.CommonConstant;

import java.util.ArrayList;
/**
 * ===========================================================================
 * File           :   ChallanGenDAO.java
 * Description    :   Represents the Challan Generation DAO Class
 * Author         :   vengamamba P
 * Created Date   :    Mar13, 2008

 * ===========================================================================
 */
public class ChallanGenDAO {
	ArrayList list = null;
	String query;
	DBUtility dbUtil=null; // obj for dbutility
	private Logger logger = (Logger) Logger.getLogger(ChallanGenDAO.class);
	public ChallanGenDAO() throws Exception {
		//dbUtil = new DBUtility(); // creating connection to db

	}

	/**
     * ===========================================================================
     * Method         :   getOfficeNameList()
     * Description    :   Returns list  of OfficeName and its id values. 
     * Arguments      :   
     * return type    :   Arraylist
     
     * ===========================================================================
     */  
	public ArrayList getOfficeNameList() {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.OFFICE_NAME_LIST;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in getOfficeNameList(): " + x);
			
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
     * Method 		: getChallanGendata
     * Description	: returns list of CHALANGEN DETAILS 
     * @param query :  string 
     * return Type  :ArrayList
     */
	
	public ArrayList getChallanGendata(String[] p) {

		try {
			dbUtil = new DBUtility();
			//getting challangen details from igrs_challan_gen_details
			query = CommonSQL.CHALLAN_GEN_LIST;
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
	/**
     * Method 		: getstatus
     * Description	: returns status and transactionid 
     * @param query :  string array
     * return Type  :ArrayList
     */
	
	public ArrayList getStatus(String[] p) {

		try {
			dbUtil = new DBUtility();
			//getting challangen details from igrs_challan_gen_details
			query = CommonSQL.CHALLAN_STATUS_LIST;
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