package com.wipro.igrs.payment.dao;

import org.apache.log4j.Logger;
import com.wipro.igrs.common.IGRSCommon;

import com.wipro.igrs.db.DBUtility;

import com.wipro.igrs.payment.sql.CommonSQL;

import com.wipro.igrs.payment.constant.CommonConstant;

import java.util.ArrayList;

/**
 * ===========================================================================
 * File : FileUploadDAO.java 
 * Description : Represents the excel rading and storeinto db
 * Author : vengamamba P 
 * Created Date : Mar 29, 2008
 * ===========================================================================
 */
public class FileUploadDAO {
	ArrayList list = null;
	String query;
	DBUtility dbUtil=null; // obj for dbutility
	private Logger logger = (Logger) Logger.getLogger(FileUploadDAO.class);
	public FileUploadDAO() throws Exception {
		//dbUtil = new DBUtility(); // creating connection to db

	}

	/**
	 * Method : insertExcelData Description : returns boolean gives success of
	 * insert or not
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 *  @param query :array of string 
	 *  return Type :boolean
	 */
	public boolean insertExcelData(String[] param, String roleId, String funId, String userId) throws Exception {

		boolean insertflag = false;

		try {
			dbUtil = new DBUtility();
			logger.debug("before inserting cash data");
			//DBUtility db = new DBUtility();
			IGRSCommon igrsCommon = new IGRSCommon();
			dbUtil.createPreparedStatement(CommonSQL.CHALLAN_INSERT);
			insertflag = dbUtil.executeUpdate(param);
			if(insertflag){
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_PAYMENT_MODE_DETAILS","INSERT","T",funId,userId,roleId);
			}
			else{
				dbUtil.rollback();
				igrsCommon.saveLogDet("IGRS_PAYMENT_MODE_DETAILS","INSERT","F",funId,userId,roleId);
			}
			logger.debug("after inserting ");

		} catch (Exception x) {
			logger.error("Exception in insertExceldata(): " + x);

		}finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}

		return insertflag;
	}

	/**
	 * Method : saveTxn() 
	 * Description : commiting whole trans success
	 * @param query :
	 * @throws :Exception
	 * return Type :void
	 */

	public void saveTxn() throws Exception {
		try {
			
			logger.debug("before commiting whole transaction ");
			dbUtil.commit();
		} catch (Exception x) {
			logger.error("Exception in commit(): " + x);

		}
	}

	/**
	 * Method : cancelTxn() 
	 * Description : rollbacking whole trans if anyone fail
	 * @param query :
	 * @throws :Exception
	 * return Type :void
	 */

	public void cancelTxn() throws Exception {
		try {
			
			logger.debug("before canceling whole transaction ");
			dbUtil.rollback();
		} catch (Exception x) {
			logger.error("Exception in rollback(): " + x);

		}
	}

	/**
	 * Method : closeConnection()
	 * Description : closing connection to db
	 * @throws :Exception
	 * return Type :void
	 */

	public void closeConnection() throws Exception {
		try {
			logger.debug("before closing db connection ");
			dbUtil.closeConnection();
		} catch (Exception x) {
			logger.error("Exception in closing connection(): " + x);

		}
	}
	/**
     * Method 		: getChallanGendata
     * Description	: returns list of CHALANGEN DETAILS 
     * @param query :  string 
     * return Type  :ArrayList
     */
	
	public ArrayList displayData() {

		try {
			dbUtil = new DBUtility();
			//getting challangen details from igrs_challan_gen_details
			query = CommonSQL.CHALLAN_EXCEL_LIST;
			query=query+"'USER001'";
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in challandisplayData(): " + x);
			
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
