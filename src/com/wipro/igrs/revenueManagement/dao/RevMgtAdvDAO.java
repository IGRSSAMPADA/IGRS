/**
 * 
 */
package com.wipro.igrs.revenueManagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.reginit.constant.RegInitConstant;
import com.wipro.igrs.revenueManagement.bd.RevMgtAdvBD;
import com.wipro.igrs.revenueManagement.dto.RevMgtAdvDTO;
import com.wipro.igrs.revenueManagement.sql.RevenueMgtSQL;

/**
 * @author SHREERAJ KHARE
 *
 */


public class RevMgtAdvDAO {

	RevMgtAdvBD rmBd = null;
	DBUtility dbUtility = null;
	Connection connTest;
	String sql = null;
	RevMgtAdvDTO dto = null;
	ArrayList mainList = null;
	IGRSCommon igrsCommon = null;
	PreparedStatement pst = null;
	private  Logger logger = 
		(Logger) Logger.getLogger(DBUtility.class);
	
	public RevMgtAdvDAO(){
		 try {
	            igrsCommon = new IGRSCommon();
	        } catch (Exception e) {
	        	logger.error("RevMgtAdvDAO in dao start" + e.getStackTrace());
	        }
	}
	
	/**
	 * for getting license details
	 * @param param 
	 * @return ArrayList
	 * @author SH836413
	 */
	public ArrayList fetchSearchDetails(String license) throws Exception {
		ArrayList list=new ArrayList();
		try {
		
			String param[]={license};
			dbUtility = new DBUtility();
			//String query=RevenueMgtSQL.LICENSE_NUMBER_DETAILS;
			String query=RevenueMgtSQL.LICENSE_NUMBER_DETAILS_HINDI;
			dbUtility.createPreparedStatement(query);
			
			list = dbUtility.executeQuery(param);
			System.out.println("size is"+list.size());
		} catch (Exception exception) {
			logger.debug("Exception in fetchSearchDetails" + exception);
		}finally {
			 try{	    
				 if (dbUtility != null) {
					 dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return list;

	}
	/**
	 * for getting license details from durations
	 * @param param 
	 * @return ArrayList
	 * @author SH836413
	 */
	public ArrayList fetchSearchDetails(String fromDate,String toDate) throws Exception {
		ArrayList list=new ArrayList();
		try {
			
			String param[]={fromDate,toDate};
			dbUtility = new DBUtility();
			//String query=RevenueMgtSQL.LICENSE_NUMBER_DETAILS_DURATION;
			String query=RevenueMgtSQL.LICENSE_NUMBER_DETAILS_DURATION_HINDI;
			dbUtility.createPreparedStatement(query);
			
			list = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in fetchSearchDetails" + exception);
		}finally {
			 try{	    
				 if (dbUtility != null) {
					 dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return list;

	}
	
	/**
	 * form fetching full name of an individual from user_reg
	 * getIndvdualName
	 * @param userID
	 * @return String
	 * @author SHREERAJ KHARE
	 */
	public String getIndvdualName(String licNo) throws Exception {
		//ArrayList list=new ArrayList();
		String name="";
		try {
			
			String param[]={licNo};
			dbUtility = new DBUtility();
			
			String query=RevenueMgtSQL.FULL_NAME_INDIVIDUAL;
			dbUtility.createPreparedStatement(query);
			
			name = dbUtility.executeQry(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getIndvdualName" + exception);
		}finally {
			 try{	    
				 if (dbUtility != null) {
					 dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return name;

	}
	
	/**
	 * form fetching full name of the bank from user_reg
	 * getIndvdualName
	 * @param userID
	 * @return String
	 * @author SHREERAJ KHARE
	 *//*
	public String getBankName(String licNo) throws Exception {
		//ArrayList list=new ArrayList();
		String name="";
		try {
			
			String param[]={licNo};
			dbUtility = new DBUtility();
			String query=RevenueMgtSQL.FULL_NAME_BANK;
			dbUtility.createPreparedStatement(query);
			
			name = dbUtility.executeQry(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getBankName" + exception);
		}
		return name;

	}*/
	
	/**
	 * form fetching full name of an individual from user_reg
	 * getIndvdualName
	 * @param userID
	 * @return String
	 * @author SHREERAJ KHARE
	 */
	public String getOthersName(String licNo,String languageLocale) throws Exception {
		//ArrayList list=new ArrayList();
		String name="";
		try {
			
			String param[]={licNo};
			dbUtility = new DBUtility();
			String query=RevenueMgtSQL.AUTHO_NAME_SP;
			dbUtility.createPreparedStatement(query);
			
			name = dbUtility.executeQry(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getOthersName" + exception);
		}finally {
			 try{	    
				 if (dbUtility != null) {
					 dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return name;

	}
	
	/**
	 * form fetching account balance of SP
	 * getAccountBal
	 * @param userID
	 * @return String
	 * @author SHREERAJ KHARE
	 */
	public String getAccountBal(String licNo,String flag) throws Exception {
		//ArrayList list=new ArrayList();
		String name="";
		try {
			String query="";
			String param[]={licNo,licNo};
			dbUtility = new DBUtility();
			if(flag.equals("eStamp")){
			query=RevenueMgtSQL.ACC_BAL_SP_ESATMP;
			}
			else{
			query=RevenueMgtSQL.ACC_BAL_SP_OTHERS;
			}
			dbUtility.createPreparedStatement(query);
			
			name = dbUtility.executeQry(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getIndvdualName" + exception);
		}finally {
			 try{	    
				 if (dbUtility != null) {
					 dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return name;

	}
	
	/**
	 * form fetching details of account statement of SP E-Stamp
	 * getIndvdualName
	 * @param userID
	 * @return String
	 * @author SHREERAJ KHARE
	 */
	public ArrayList getSPStmtEstamp(String licNo,String flag) throws Exception {
		ArrayList list=new ArrayList();
		try {
			String query="";
			String param[] = {licNo};
			dbUtility = new DBUtility();
			if(flag.equalsIgnoreCase("eStamp")){
			//query=RevenueMgtSQL.SELECT_SP_ACCOUNT_STMT_ESTAMP;
				query=RevenueMgtSQL.SELECT_SP_ACCOUNT_STMT_ESTAMP_HINDI;
			}
			else{
			//query=RevenueMgtSQL.SELECT_SP_ACCOUNT_STMT_OTHERS;
				query=RevenueMgtSQL.SELECT_SP_ACCOUNT_STMT_OTHERS_HINDI;
			}
			dbUtility.createPreparedStatement(query);
			
			list = dbUtility.executeQuery(param);
			//logger.info("STATEMENT LIST:----"+list);
			//logger.info("QUERY RUN FOR----"+flag);
		} catch (Exception exception) {
			logger.info("Exception in getSPStmtEstamp" + exception);
		}finally {
			 try{	    
				 if (dbUtility != null) {
					 dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return list;
	}
}
