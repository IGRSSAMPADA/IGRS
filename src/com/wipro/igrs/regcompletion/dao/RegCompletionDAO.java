package com.wipro.igrs.regcompletion.dao;

import java.sql.CallableStatement;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.regcompletion.sql.CommonSQL;
import com.wipro.igrs.regcompletion.sql.RegCommonSQL;

public class RegCompletionDAO {

	
	DBUtility dbUtil;
	ArrayList list = null;
	private Logger logger = (Logger) Logger.getLogger(RegCompletionDAO.class);
	CallableStatement clstmt ;
	public RegCompletionDAO() {
		
	}
	public String checkSpotInspection(String regID ) throws Exception {
		String regNumber = "";
		dbUtil = new DBUtility();
		try {
			dbUtil.createStatement();
			String sql = RegCommonSQL.SELECT_SPOT_INSPECTION 
				+ "'" + regID + "'";
			regNumber = dbUtil.executeQry(sql);
			
		}catch(Exception x) {
			logger.debug(x);
		}finally{
			dbUtil.closeConnection();
		}
		return regNumber;
	}
	public String checkValidRegistration(String regID ) throws Exception {
		String regNumber = "";
		dbUtil = new DBUtility();
		try {
			dbUtil.createStatement();
			String sql = RegCommonSQL.SELECT_CHECK_INFO + "'" + regID + "'";
			regNumber = dbUtil.executeQry(sql);
			
		}catch(Exception x) {
			logger.debug(x);
		}finally{
			dbUtil.closeConnection();
		}
		return regNumber;
	}
	public String[] checkRegistration(String regID) throws Exception {
		String[] param= new String[6];
		try {
			dbUtil = new DBUtility();
			clstmt = dbUtil.returnCallableStatement(CommonSQL.IGRS_COMPLETION_CHECK_FLAG);
			clstmt.setString(1, regID);
			clstmt.registerOutParameter(2, OracleTypes.VARCHAR);
			clstmt.registerOutParameter(3, OracleTypes.VARCHAR);
			clstmt.registerOutParameter(4, OracleTypes.VARCHAR);
			clstmt.registerOutParameter(5, OracleTypes.VARCHAR);
			clstmt.registerOutParameter(6, OracleTypes.VARCHAR);
			clstmt.registerOutParameter(7, OracleTypes.VARCHAR);
			System.out.println("sql is"+clstmt);
			if (!clstmt.execute()) {
				param[0] = clstmt.getString(2);//Caveat Flag
				param[1] = clstmt.getString(3);//Property Lock Flag
				param[2] = clstmt.getString(4);//Case Flag
				param[3] = clstmt.getString(5);//Court Order Flag
				param[4] = clstmt.getString(6);//POA Flag
				param[5] = clstmt.getString(7);//Error Msg
				System.out.println(param[0]+param[1]+param[2]+param[3]+param[4]+param[5]);
			}
		} catch (Exception x) {
			logger.error("Exception in insertRolDetails() :- " + x);
			System.out.println("Exception in insertRolDetails() :- " + x);
		} finally {
			dbUtil.closeConnection();
		}
		return param;
	}
	
	public boolean generatePIN(String[] param) {
		boolean bol = false;
		
		try {
			dbUtil = new DBUtility();
			
			
			
			
			//clstmt = dbUtil.createPreparedStatement(CommonSQL.PINCREATE);
			//bol = dbUtil.executeUpdate(param);

			
		} catch (Exception x) {
			logger.error("Exception in insertRolDetails() :- " + x);
		} finally {
			//dbUtil.closeConnection();
		}
		 
		return bol;
	}
}

