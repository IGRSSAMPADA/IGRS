package com.wipro.igrs.leaveencash.dao;

import java.sql.CallableStatement;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.leaveencash.sql.CommonSQL;


public class LeaveEnCashmentDAO {

	
	

	private Logger logger = (Logger) Logger
	.getLogger(LeaveEnCashmentDAO.class);
	
	private DBUtility dbUtil ;
	
	/**
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getEmpDetails(String[] param) throws Exception{
		
		ArrayList list = new ArrayList();
		
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.EMP_DETAILS_QUERY);
			list = dbUtil.executeQuery(param);
		}catch(Exception x) {
			logger.debug(x);
		}finally {
			dbUtil.closeConnection();
		}
		return list;
	}
	
	/**
	 * 
	 * @param empID
	 * @return
	 * @throws Exception
	 */
	public String[] getEmpAvaliedLeaves(String empID) throws Exception {
		String[] returnValue = new String[4];
		
		try {
			dbUtil = new DBUtility();
			CallableStatement clstmt = 
				dbUtil.returnCallableStatement(CommonSQL.EMP_LEAVE_DETAILS);
			clstmt.setString(1, empID);

			clstmt.registerOutParameter(2, OracleTypes.NUMBER);
			clstmt.registerOutParameter(3, OracleTypes.NUMBER);
			clstmt.registerOutParameter(4, OracleTypes.NUMBER);
			clstmt.registerOutParameter(5, OracleTypes.VARCHAR);
			if (!clstmt.execute()) {
				int availed = clstmt.getInt(2);
				int earned = clstmt.getInt(3);
				int total = clstmt.getInt(4);
				returnValue[0] = String.valueOf(availed);
				returnValue[1] = String.valueOf(earned);
				returnValue[2] = String.valueOf(total);
			}
			dbUtil.createStatement();
			returnValue[3] = dbUtil.executeQry(CommonSQL.FISCAL_YEAR_ID);
			logger.debug("returnValue[4]:-"+returnValue[3]);
		}catch(Exception x) {
			logger.debug(x);
		}finally {
			dbUtil.closeConnection();
		}
		return returnValue;
	}
	/**
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String getEmpEnCashLeaveDetails(String[] param) throws Exception {
		String returnValue = "";
		
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.EMP_VALIDATE_LEAVEENCASHMENT);
			returnValue = dbUtil.executeQry(param);
		}catch(Exception x) {
			logger.debug(x);
		}finally {
			dbUtil.closeConnection();
		}
		return returnValue;
	}
	/**
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String getMaxLeaveEnCash(String[] param) throws Exception {
		String returnValue = "";
		
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.EMP_MAX_ENCASHMENT);
			returnValue = dbUtil.executeQry(param);
		}catch(Exception x) {
			logger.debug(x);
		}finally {
			dbUtil.closeConnection();
		}
		return returnValue;
	}
	/**
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public boolean insertEnCashLeave(String[] param) throws Exception {
		boolean bol = false;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.INSERT_ENCASH_LEAVE);
			bol = dbUtil.executeUpdate(param);
			if(bol) {
				dbUtil.commit();
			}else {
				dbUtil.rollback();
			}
			logger.debug("The record saved "+bol);
		}catch(Exception x) {
			logger.debug(x);
			dbUtil.rollback();
		}finally {
			dbUtil.closeConnection();
		}
		return bol;
	}
}