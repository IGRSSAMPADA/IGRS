package com.wipro.igrs.RegCompMaker.dao;

import java.sql.CallableStatement;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;

import com.wipro.igrs.RegCompMaker.sql.CommonMkrSQL;
import com.wipro.igrs.RegCompMaker.sql.RegCommonMkrSQL;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.newreginit.sql.RegCommonSQL;




public class RegCompletionMkrDAO {

	
	
	ArrayList list = null;
	private Logger logger = (Logger) Logger.getLogger(RegCompletionMkrDAO.class);
	CallableStatement clstmt ;
	public RegCompletionMkrDAO() {
		
	}
	
	

	public String[] checkRegistration(String regID) throws Exception {
		String[] param= new String[6];
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			clstmt = dbUtil.returnCallableStatement(CommonMkrSQL.IGRS_COMPLETION_CHECK_FLAG);
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



	public String checkValidRegistration(String regNumber) throws Exception {
		String regID = "";
		DBUtility dbUtil=null;

		dbUtil = new DBUtility();
		try {
			dbUtil.createStatement();
			String sql = RegCommonMkrSQL.SELECT_CHECK_INFO + "'" + regNumber + "'";
			regID = dbUtil.executeQry(sql);
			
		}catch(Exception x) {
			logger.debug(x);
		}finally{
			dbUtil.closeConnection();
		}
		return regID;
	}



	public String checkSpotInspection(String regNumber) throws Exception {
		String regID = "";
		DBUtility dbUtil=null;

		dbUtil = new DBUtility();
		try {
			dbUtil.createStatement();
			String sql = RegCommonMkrSQL.SELECT_SPOT_INSPECTION 
				+ "'" + regNumber + "'";
			regID = dbUtil.executeQry(sql);
			
		}catch(Exception x) {
			logger.debug(x);
		}finally{
			dbUtil.closeConnection();
		}
		return regID;
	}
	
	
		
	}
	


