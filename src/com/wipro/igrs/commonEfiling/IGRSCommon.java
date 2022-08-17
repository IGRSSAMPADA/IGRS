/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   IGRSCommon.java
 * Author      :   Madan Mohan
 * Description :   Represents the Common functions.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0            Madan Mohan      05/06/2008
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.commonEfiling;

import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;

import com.wipro.igrs.copyissuance.util.CommonUtil;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.payment.util.AESEncrypt;
import com.wipro.igrs.propertylock.sql.PlockSQL;
import com.wipro.igrs.util.PropertiesFileReader;

/**
 * @author nihraa
 * 
 */
public class IGRSCommon {

	/**
	 * dbUtil
	 */
	//public DBUtility dbUtil=null;
	private static Logger logger = (Logger) Logger.getLogger(IGRSCommon.class);
	private CallableStatement clstmt;

	/**
	 * @author Madan Mohan
	 */
	public final static String DATE_FORMATDDMMYYYY = "dd/MM/yyyy";
	/**
	 * @author Madan Mohan
	 */
	public final static String DATE_FORMATDDMMMYYYY = "dd/MMM/yyyy";
	/**
	 * @author Madan Mohan
	 */
	public final static String DATE_FORMATDDMMYY = "dd/MM/yy";
	/**
	 * @author Madan Mohan
	 */
	public final static String DATE_FORMATDDMMMYY = "dd/MMM/yy";
	
	public static String xssFieldName;
	
	private static String xssDataCheck;

	static{
		try {
			xssDataCheck = PropertiesFileReader.getInstance("resources.igrs").getValue("igrs.xss.check");
		} catch (Exception e) {
			xssDataCheck = ".*[<>].*";
			logger.error("Error :- "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws Exception
	 */
	public IGRSCommon() throws Exception {
		// dbUtil = new DBUtility();
	}

	/**
	 * 
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getCountry() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getCountry() - start");
		}
		DBUtility dbUtil=null;
		ArrayList list = new ArrayList();
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			logger.debug("Wipro in IGRSCommon - "
					+ "getCountry() after create statement");
			//list = dbUtil.executeQuery(CommonSQL.COUNTRY_QUERY);
			list = dbUtil.executeQuery(CommonSQL.COUNTRY_QUERY_HINDI);
			logger.debug("Wipro in IGRSCommon - "
					+ "getCountry() after execute query");

		} catch (Exception e) {
			logger.debug(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}
/**
	 * 
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getPurpose() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getPurpose() - start");
		}
		DBUtility dbUtil=null;
		ArrayList list = new ArrayList();
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			logger.debug("Wipro in IGRSCommon - "
					+ "getPurpose() after create statement");
			list = dbUtil.executeQuery(CommonSQL.PURPOSE_QUERY);
			logger.debug("Wipro in IGRSCommon - "
					+ "getPurpose() after execute query");

		} catch (Exception e) {
			logger.debug(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}


	public synchronized ArrayList getPhotoProof() throws Exception {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil
					.executeQuery("Select PHOTO_PROOF_TYPE_ID, PHOTO_PROOF_TYPE_NAME from IGRS_PHOTOID_PROOF_TYPE_MASTER");
			logger
					.debug("Wipro in IGRSCommon - getPhotoProof() after execute query");
		} catch (Exception e) {
			logger.debug(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getHintQuestions() throws Exception {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil
					.executeQuery("Select QUESTION_ID, QUESTION_TEXT from IGRS_USER_HINT_QUESTIONS");
			logger.debug("GRSCommon - getHintQuestions() after execute query");
		} catch (Exception e) {
			logger.debug(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	/**
	 * 
	 * @param countryId
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getState(String countryId) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getState(String) - start");
		}
		DBUtility dbUtil=null;
		ArrayList list = new ArrayList();
		//String SQL = CommonSQL.STATE_QUERY;
		String SQL = CommonSQL.STATE_QUERY_HINDI;
		String arry[] = new String[1];
		if (countryId != null) {
			arry[0] = countryId;
		}
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			logger.debug("Wipro in IGRSCommon - getState() "
					+ "after creating preparedstatement");
			list = dbUtil.executeQuery(arry);
			logger.debug("Wipro in IGRSCommon - "
					+ "getState() after excuteQuery");
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}
	/**
	 * 
	 *
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getHomeState() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getState(String) - start");
		}
		DBUtility dbUtil=null;
		ArrayList list = new ArrayList();
		//String SQL = CommonSQL.STATE_QUERY;
		String SQL = CommonSQL.STATE_QUERY_HINDI_INDIA;
		
		try {
			dbUtil = new DBUtility();
		
			dbUtil.createStatement();
			list = dbUtil
					.executeQuery(SQL);
			logger.debug("Wipro in IGRSCommon - "
					+ "getHomeState() after excuteQuery");
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}
	
	/**
	 * @param stateId
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getZone(String stateId) throws Exception {
		String arry[] = new String[1];
		logger.debug("State ID:-" + stateId);

		ArrayList list = new ArrayList();
		if (stateId != null) {
			arry[0] = stateId;
		}
		DBUtility dbUtil=null;

		try {
			logger.debug("Before initialize DBUtility");
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.ZONE_QUERY);
			list = dbUtil.executeQuery(arry);
			logger.debug("After initialize DBUtility");
			if (logger.isDebugEnabled()) {
				logger.debug("getDistrict(String) - end");
			}
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}
	

	/**
	 * @param stateId
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getDistrict(String stateId) throws Exception {
		String arry[] = new String[1];
		logger.debug("State ID:-" + stateId);

		ArrayList list = new ArrayList();
		if (stateId != null) {
			arry[0] = stateId;
		}
		DBUtility dbUtil=null;

		try {
			logger.debug("Before initialize DBUtility");
			dbUtil = new DBUtility();
			//if("en".equalsIgnoreCase(language))
			//{
			//}
			//else
			//{
			dbUtil.createPreparedStatement(CommonSQL.DISTRICT_QUERY_HINDI);
			//}
			list = dbUtil.executeQuery(arry);
			logger.debug("After initialize DBUtility");
			if (logger.isDebugEnabled()) {
				logger.debug("getDistrict(String) - end");
			}
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}
	//added by shruti---in use for property locking module
	public synchronized ArrayList getDistrict(String stateId,String language) throws Exception {
		String arry[] = new String[1];
		logger.debug("State ID:-" + stateId);

		ArrayList list = new ArrayList();
		if (stateId != null) {
			arry[0] = stateId;
		}
		DBUtility dbUtil=null;

		try {
			logger.debug("Before initialize DBUtility");
			dbUtil = new DBUtility();
			if("en".equalsIgnoreCase(language))
			{
				dbUtil.createPreparedStatement(CommonSQL.DISTRICT_QUERY);
			}
			else
			{
			dbUtil.createPreparedStatement(CommonSQL.DISTRICT_QUERY_H);
			}
			list = dbUtil.executeQuery(arry);
			logger.debug("After initialize DBUtility");
			if (logger.isDebugEnabled()) {
				logger.debug("getDistrict(String) - end");
			}
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}
	//end
	
	public synchronized ArrayList getRegsitrationDistrict(String stateId,String language) throws Exception {
		String arry[] = new String[1];
		logger.debug("State ID:-" + stateId);

		ArrayList list = new ArrayList();
		if (stateId != null) {
			arry[0] = stateId;
		}
		DBUtility dbUtil=null;

		try {
			logger.debug("Before initialize DBUtility");
			dbUtil = new DBUtility();
			if("en".equalsIgnoreCase(language))
			{
				dbUtil.createPreparedStatement(CommonSQL.REG_DISTRICT_QUERY);
			}
			else
			{
			dbUtil.createPreparedStatement(CommonSQL.REG_DISTRICT_QUERY_H);
			}
			list = dbUtil.executeQuery(arry);
			logger.debug("After initialize DBUtility");
			if (logger.isDebugEnabled()) {
				logger.debug("getDistrict(String) - end");
			}
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}
	//end
	
/**
	 
	 * @return ArrayList
	 * @throws Exception
	 */

public synchronized String getFee(String strPurpose) throws Exception {
		String[] arry =new String[1];
		arry[0]=strPurpose;		
		String str1="";		
		DBUtility dbUtil=null;

		try {
			logger.debug("Before initialize DBUtility");
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.FEE_QUERY);
			str1 = dbUtil.executeQry(arry);
		
			logger.debug("After initialize DBUtility");
		//	str2= Integer.parseInt(str1.get(0).toString());
			logger.debug("@@@@@@ integere value@@@@@@"+str1);
			if (logger.isDebugEnabled()) {
				logger.debug("getFee(String) - end");
			}
		} catch (Exception x) {
			//logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return str1;
	}


	/**
	 * @param distId
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getMahalla(String patwariId) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getMahalla(String) - start");
		}
		//String SQL = CommonSQL.MOHALLA_QUERY;
		String SQL = CommonSQL.MOHALLA_QUERY_HINDI;
		ArrayList list = new ArrayList();
		String arry[] = new String[1];
		if (patwariId != null) {
			arry[0] = patwariId;
		}
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			logger.debug("Wipro in IGRSCommon - "
					+ "getMahalla after PreparedStatement");
			list = dbUtil.executeQuery(arry);
			logger.debug("Wipro in IGRSCommon - "
					+ "getMahalla() after executeQuery");
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		return list;
	}

	
	/**
	 * @param distId
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getMahalla(int patwariId) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getMahalla(String) - start");
		}
		//String SQL = CommonSQL.MOHALLA_QUERY;
		String SQL = CommonSQL.MOHALLA_QUERY_HINDI;
		ArrayList list = new ArrayList();
		DBUtility dbUtil=null;

		int arry[] = new int[1];
		arry[0] = patwariId;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			logger.debug("Wipro in IGRSCommon - "
					+ "getMahalla after PreparedStatement");
			list = dbUtil.executeQuery(arry);
			logger.debug("Wipro in IGRSCommon - "
					+ "getMahalla() after executeQuery");
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		return list;
	}

	/**
	 * @param mhId
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getWard(String mhId) throws Exception {
		String SQL = "Select WARD_ID, WARD_NAME from IGRS_WARD_MASTER Where MH_ID=?";
		String arry[] = new String[1];
		ArrayList list = null;
		if (mhId != null) {
			arry[0] = mhId;
		}
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			list = dbUtil.executeQuery(arry);
		} catch (Exception e) {
			logger.debug(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}


	/**
	 * 
	 * @param commonId
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getCommon(String commonId) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getCommon(String) - start");
		}
		String SQL = CommonSQL.COMMON_QUERY;
		String arry[] = new String[1];
		ArrayList list = new ArrayList();
		if (commonId != null) {
			arry[0] = commonId;
		}
		DBUtility dbUtil=null;

		try {

			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			logger.debug("Wipro in IGRSCommon - "
					+ "getCommon() after preparedStatement");
			list = dbUtil.executeQuery(arry);
			logger.debug("Wipro in IGRSCommon - "
					+ "getCommon() after executeQuery");
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		return list;
	}
	public synchronized ArrayList getPhotoIdProof(String language) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getCommon(String) - start");
		}
		String SQL="";
		if("en".equalsIgnoreCase(language))
		{
		SQL = CommonSQL.PHOTOID_PROOF_QUERY;
		}
		else
		{
			 SQL = CommonSQL.PHOTOID_PROOF_QUERY_H;
		}
		ArrayList list = new ArrayList();
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			logger.debug("Wipro in IGRSCommon - "
					+ "getPhotoIdProof() after create statement");
			list = dbUtil.executeQuery(SQL);
			logger.debug("Wipro in IGRSCommon - "
					+ "getPhotoIdProof() after executeQuery");
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		return list;
	}

public synchronized ArrayList getAppellate(String language) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getCommon(String) - start");
		}
		String SQL="";
		if("en".equalsIgnoreCase(language))
		{
		SQL= CommonSQL.APPELLATE_QUERY;
		}
		else
		{
		SQL = CommonSQL.APPELLATE_QUERY_H;
		}
		ArrayList list = new ArrayList();
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			logger.debug("Wipro in IGRSCommon - "
					+ "getappellate() after create statement");
			list = dbUtil.executeQuery(SQL);
			logger.debug("Wipro in IGRSCommon - "
					+ "getappellate() after executeQuery");
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		return list;
	}	/*
	 * public ArrayList getDeedType() throws Exception{ String SQL="SELECT
	 * DEED_ID,DEED_NAME,OTHER_AMOUNT,STAMP_DUTY,LINK_INSTRUMENT FROM
	 * IGRS_DEED_TYPE WHERE DEED_STATUS='A'"; dbUtil.createStatement(); return
	 * dbUtil.executeQuery(SQL); }
	 */
	public synchronized ArrayList getOptionalDeedType() throws Exception {
		logger.debug("in commonsql getOptionalDeedType");
		ArrayList list = null;
		String SQL = "SELECT DEED_TYPE_ID,DEED_TYPE_NAME FROM IGRS_DEED_TYPE_MASTER WHERE DEED_STATUS='A'";
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	/**
	 * Method : getCommon Description : Retrieves the ID Proof list from the
	 * database
	 * 
	 * @return list : ArrayList
	 * @throws :
	 *             Exception
	 */
	public synchronized ArrayList getCommon() throws Exception {
		String SQL = "Select PHOTO_PROOF_TYPE_ID, PHOTO_PROOF_TYPE_NAME,H_PHOTO_PROOF_TYPE_NAME from IGRS_PHOTOID_PROOF_TYPE_MASTER WHERE UPPER(PHOTO_PROOF_TYPE_STATUS)='A' ORDER BY PHOTO_PROOF_TYPE_NAME";
		// String arry[] = new String[1];
		ArrayList list = null;
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();
			/*
			 * if (commonId != null) { arry[0] = commonId; }
			 */
			// dbUtil.createPreparedStatement(SQL);
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public synchronized ArrayList getDistrict() throws Exception {
		String SQL = "SELECT  DISTRICT_ID, DISTRICT_NAME FROM IGRS_DISTRICT_MASTER  WHERE DISTRICT_STATUS='A'";
		String arry[] = new String[1];
		ArrayList list = null;
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	/**
	 * @param _distId
	 * @return
	 * @throws Exception
	 */
	public synchronized ArrayList getTehsil(String _distId) throws Exception {
		String SQL = "SELECT TEHSIL_ID,TEHSIL_NAME FROM IGRS_TEHSIL_MASTER WHERE TEHSIL_STATUS='A' AND DISTRICT_ID=?";
		String arry[] = new String[1];
		ArrayList list = null;
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();
			if (_distId != null) {
				arry[0] = _distId;
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(arry);

			} else
				return null;
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	/**
	 * @return ArrayList
	 */
	public synchronized ArrayList getAreaType() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getAreaType() - start");
		}

		ArrayList list = new ArrayList();
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			//list = dbUtil.executeQuery(CommonSQL.AREA_TYPE_QUERY);
			list = dbUtil.executeQuery(CommonSQL.AREA_TYPE_QUERY_HINDI);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAreaType() - end");
		}
		return list;
	}
	//added by shruti--23 jan 2014-in use for doc srch module
	public synchronized ArrayList getAreaType(String language) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getAreaType() - start");
		}

		ArrayList list = new ArrayList();
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			if("en".equalsIgnoreCase(language))
			{
			list = dbUtil.executeQuery(CommonSQL.AREA_TYPE_QUERY);
			}
			else
			{
			list = dbUtil.executeQuery(CommonSQL.AREA_TYPE_QUERY_H);
			}
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAreaType() - end");
		}
		return list;
	}
	//end
	/**
	 * @param _landtypeid
	 * @return
	 * @throws Exception
	 */
	
	public ArrayList getLandType() {
		
		
	ArrayList list = null;
	DBUtility dbUtil=null;

	try {
		dbUtil = new DBUtility();
		dbUtil.createStatement();
		logger
				.debug("Wipro in IGRSCommon - getLand() a after preparedStatement");
		//list = dbUtil.executeQuery(CommonSQL.LAND_TYPE_QUERY);
		list = dbUtil.executeQuery(CommonSQL.LAND_TYPE_QUERY_HINDI);
		logger.debug("Wipro in IGRSCommon - getLand() after executeQuery");
	} catch (Exception x) {
		logger.debug(x);
	} finally {
		if (dbUtil != null) {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	return list;
	}
	/**
	 * @param _wardRpatwariId
	 * @return
	 * @throws Exception
	 */
	public synchronized ArrayList getVillage(String _wardRpatwariId)
			throws Exception { // for gram

		String SQL = "SELECT MOHALLA_VILLAGE_ID, MOHALLA_VILLAGE_NAME FROM IGRS_MOHALLA_VILLAGE_MASTER WHERE "
				+ " MOHALLA_VILLAGE_STATUS='V' AND WARD_PATWARI_ID=?";
		String arry[] = new String[1];
		ArrayList list = null;
		if (_wardRpatwariId != null) {
			arry[0] = _wardRpatwariId;
		}
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			logger
					.debug("Wipro in IGRSCommon - getWard() after preparedStatement");
			list = dbUtil.executeQuery(arry);
			logger.debug("Wipro in IGRSCommon - getWard() after executeQuery");
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	/**
	 * @param _areaTypeId
	 * @return
	 * @throws Exception
	 */
	public synchronized ArrayList getPatwarihalka(String _areaTypeId)
			throws Exception {
		//
		String SQL = "SELECT WARD_PATWARI_ID,WARD_PATWARI_NAME FROM IGRS_WARD_PATWARI_MASTER WHERE "
				+ "WARD_PATWARI_STATUS='A' AND AREA_TYPE_ID=? AND  WARD_OR_PATWARI='P'";
		String arry[] = new String[1];
		ArrayList list = null;
		if (_areaTypeId != null) {
			arry[0] = _areaTypeId;
		}
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			logger
					.debug("Wipro in IGRSCommon - getWard() after preparedStatement");
			list = dbUtil.executeQuery(arry);
			logger.debug("Wipro in IGRSCommon - getWard() after executeQuery");
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	/**
	 * @param _wardRpatwariId
	 * @return
	 * @throws Exception
	 */
	public synchronized ArrayList getMohalla(String _wardRpatwariId)
			throws Exception {
		//

		String SQL = "SELECT MOHALLA_VILLAGE_ID, MOHALLA_VILLAGE_NAME FROM IGRS_MOHALLA_VILLAGE_MASTER WHERE "
				+ " MOHALLA_VILLAGE_STATUS='M' AND WARD_PATWARI_ID=?";
		String arry[] = new String[1];
		ArrayList list = null;
		if (_wardRpatwariId != null) {
			arry[0] = _wardRpatwariId;
		}
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			logger
					.debug("Wipro in IGRSCommon - getWard() after preparedStatement");
			list = dbUtil.executeQuery(arry);
			logger.debug("Wipro in IGRSCommon - getWard() after executeQuery");
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	/**
	 * closeIGRSConnection
	 */
	public void closeIGRSConnection() {
		DBUtility dbUtil=null;

		try {
			dbUtil=new DBUtility();
			dbUtil.closeConnection();
		} catch (Exception x) {
			logger
					.debug("Wipro in IGRSCommon - closeIGRSConnection() after closeConnection"
							+ x);
		}
	}

	/**
	 * @return ArrayList
	 */
	public synchronized ArrayList getPropertyType() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getPropertyType() - start");
		}
		DBUtility dbUtil=null;
		dbUtil = new DBUtility();
		ArrayList list = new ArrayList();
		try {
			dbUtil.createStatement();
			//list = dbUtil.executeQuery(CommonSQL.PROPERTY_TYPE_SELECT_QUERY);
			list = dbUtil.executeQuery(CommonSQL.PROPERTY_TYPE_SELECT_QUERY_HINDI);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getPropertyType() - end");
		}
		return list;
	}

	/**
	 * @return ArrayList
	 */
	public synchronized ArrayList getMunicipalBody() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getMunicipalBody() - start");
		}

		ArrayList list = new ArrayList();
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			//list = dbUtil.executeQuery(CommonSQL.MUNICIPAL_BODY_SELECT_QUERY);
			list = dbUtil.executeQuery(CommonSQL.MUNICIPAL_BODY_SELECT_QUERY_HINDI);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getMunicipalBody() - end");
		}
		return list;
	}

	/**
	 * @param propertyId
	 * @return ArrayList
	 */
	public synchronized ArrayList getUsedPlot(int propertyId)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getUsedPlot(String) - start");
		}

		ArrayList list = new ArrayList();
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			//dbUtil.createPreparedStatement(CommonSQL.PLOT_USE_QUERY);
			dbUtil.createPreparedStatement(CommonSQL.PLOT_USE_QUERY_HINDI);
			int[] param = new int[1];
			param[0] = propertyId;
			list = dbUtil.executeQuery(param);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getUsedPlot(String) - end");
		}
		return list;

	}

	/**
	 * @param propertyId
	 * @return ArrayList
	 */
	public synchronized ArrayList getUsedPlot(String propertyId)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getUsedPlot(String) - start");
		}

		ArrayList list = new ArrayList();
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.PLOT_USE_QUERY);
			String[] param = new String[1];
			param[0] = propertyId;
			list = dbUtil.executeQuery(param);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getUsedPlot(String) - end");
		}
		return list;

	}
	
	/**
	 * @param propertyId
	 * @return ArrayList
	 */
	public synchronized ArrayList getBuildingType(String usageId)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getUsedPlot(String) - start");
		}

		ArrayList list = new ArrayList();
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			//dbUtil.createPreparedStatement(CommonSQL.PLOT_L2_USE_QUERY);
			dbUtil.createPreparedStatement(CommonSQL.PLOT_L2_USE_QUERY_HINDI);
			String[] param = new String[1];
			param[0] = usageId;
			list = dbUtil.executeQuery(param);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getUsedPlot(String) - end");
		}
		return list;

	}

	/**
	 * @return ArrayList
	 */
	public synchronized ArrayList getFloor(String l1ID) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getUsedPlot(String) - start");
		}

		ArrayList list = new ArrayList();
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			//dbUtil.createPreparedStatement(CommonSQL.FLOOR_MASTER_QUERY);
			dbUtil.createPreparedStatement(CommonSQL.FLOOR_MASTER_QUERY_HINDI);
			String[] param = new String[1];
			param[0] = l1ID;
			list = dbUtil.executeQuery(param);
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getUsedPlot(String) - end");
		}
		return list;

	}

	/**
	 * @param String[]
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getInstruments(int[] deedType)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getInstruments(String[]) - start");
		}
		ArrayList instrument = new ArrayList();
		DBUtility dbUtil=null;
		try {

			dbUtil = new DBUtility();
			//dbUtil.createPreparedStatement(CommonSQL.INSTRUMENTS_QUERY);
			dbUtil.createPreparedStatement(CommonSQL.INSTRUMENTS_QUERY_HINDI);
			logger.debug("Inside getInstruments");
			instrument = dbUtil.executeQuery(deedType);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getInstruments(String[]) - end");
		}
		return instrument;
	}

	/**
	 * @param String[]
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getFloorCalcType(String propertytypel1)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("GETFLOORTYPECALC (String[]) - start");
		}
		ArrayList getfloortypecalc = new ArrayList();
		DBUtility dbUtil=null;
		try {
			String propertyl1[]=new String[2];
			propertyl1[0]="A";
			propertyl1[1] =propertytypel1;
			dbUtil = new DBUtility();
			//dbUtil.createPreparedStatement(CommonSQL.FLOOR_TYPE_CALC);
			dbUtil.createPreparedStatement(CommonSQL.FLOOR_TYPE_CALC_HINDI);
			System.out.println("Inside GETFLOORTYPECALC");
			getfloortypecalc  = dbUtil.executeQuery(propertyl1);
			System.out.println(" after  GETFLOORTYPECALC");
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("GETFLOORTYPECALC(String[]) - end");
		}
		return getfloortypecalc;
	}
	
	/**
	 * @param deedType
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getExemptions(int[] deedType)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getExemptions(String[]) - start");
		}
		ArrayList exemp = new ArrayList();
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		//	dbUtil.createPreparedStatement(CommonSQL.EXEMPTIONS_QUERY);
		//	logger.debug("Inside getExemptions " + CommonSQL.EXEMPTIONS_QUERY);
			dbUtil.createPreparedStatement(CommonSQL.EXEMPTIONS_QUERY_HINDI);
			exemp = dbUtil.executeQuery(deedType);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getExemptions(String[]) - end");
		}
		return exemp;
	}
	
	/**
	 * @param deedType
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getInstLevelExemptions(int[] deedType)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getExemptions(String[]) - start");
		}
		ArrayList exemp = new ArrayList();
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			//dbUtil.createPreparedStatement(CommonSQL.EXEMPTIONS_INST_LEVEL_QUERY );
			dbUtil.createPreparedStatement(CommonSQL.EXEMPTIONS_INST_LEVEL_QUERY_HINDI );
			//logger.debug("Inside getExemptions " + CommonSQL.EXEMPTIONS_INST_LEVEL_QUERY );
			exemp = dbUtil.executeQuery(deedType);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getExemptions(String[]) - end");
		}
		return exemp;
	}

	
	/**
	 * @param String[]
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getInstruments(String[] deedType)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getInstruments(String[]) - start");
		}
		ArrayList instrument = new ArrayList();
		DBUtility dbUtil=null;
		try {

			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.INSTRUMENTS_QUERY);
			logger.debug("Inside getInstruments");
			instrument = dbUtil.executeQuery(deedType);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getInstruments(String[]) - end");
		}
		return instrument;
	}

	/**
	 * @param deedType
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getExemptions(String[] deedType)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getExemptions(String[]) - start");
		}
		ArrayList exemp = new ArrayList();
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.EXEMPTIONS_QUERY);
			logger.debug("Inside getExemptions " + CommonSQL.EXEMPTIONS_QUERY);
			exemp = dbUtil.executeQuery(deedType);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getExemptions(String[]) - end");
		}
		return exemp;
	}

	/**
	
	/**
	 * @param deed
	 * @param instrument
	 * @param exemption
	 * @return String
	 */
	public synchronized double getStampDuty(int deed, int instrument,
			String exemption, double baseValue,double annualRent,double dutyPaid ) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getStampDuty(String, String, String) - start");
		}

		double stampDuty=0.0 ;
		logger.debug("Inside getStampDuty");
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			clstmt = dbUtil
					.returnCallableStatement(CommonSQL.DUTY_CALCULATION_PROCEDURE);
			logger.debug("Duty value:-" + deed + ":" + instrument + ":"
					+ exemption + ":" + baseValue);
			clstmt.setInt(1, deed);
			clstmt.setInt(2, instrument);
			clstmt.setString(3, exemption);
			clstmt.setDouble(4, baseValue);
			clstmt.setDouble(5,annualRent);
			clstmt.setDouble(6,dutyPaid);
			clstmt.setString(7,null);
			clstmt.setString(8,null);
			clstmt.setString(9,null);
			clstmt.registerOutParameter(10, OracleTypes.NUMBER);
			clstmt.registerOutParameter(11, OracleTypes.VARCHAR);
			clstmt.registerOutParameter(12, OracleTypes.VARCHAR);
			if (!clstmt.execute()) {
				stampDuty = clstmt.getDouble(10);
				
			}
		} catch (Exception e) {
			logger.debug(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getStampDuty(String, String, String) - end");
		}
		return stampDuty;
	}
	
/* @param distId
	 * @param DEED
	 * @param INSTRUMENT
	 * @param AREA TYPE
	 * @param baseValue

	 */
	public synchronized double[] getMuncipalCalculatedDuty(int deed,int instrument,int munbodyType,
			String propertyType ,double marketValue,double stampDuty)
	{
		double calculatedDuty[]=new double[3];
	
		DBUtility dbUtil=null;
		try {
			logger.debug("Municipal Value deedID:-" + deed + ":InstrumentId:-"
					+ instrument + ":MUNBODYID" + munbodyType + "PROPERTYTYPE:-"
					+ propertyType + ":MARKET VALUE:-" + marketValue+ "STAMP DUTY" +stampDuty);
			
			dbUtil = new DBUtility();
			clstmt = dbUtil.returnCallableStatement(CommonSQL.MUNICIPAL_DUTY_CALCULATION_NEW);
			clstmt.setInt(1, deed);
			clstmt.setInt(2, instrument);
			clstmt.setInt(3, munbodyType);
			clstmt.setString(4, propertyType);
			clstmt.setDouble(5, marketValue);
			clstmt.setDouble(6, stampDuty);


			clstmt.registerOutParameter(7, OracleTypes.NUMBER);
			clstmt.registerOutParameter(8, OracleTypes.NUMBER);
			clstmt.registerOutParameter(9, OracleTypes.NUMBER);

			if (!clstmt.execute()) {
				calculatedDuty[0] = clstmt.getDouble(7);
				calculatedDuty[1] =  clstmt.getDouble(8);
				calculatedDuty[2] =  clstmt.getDouble(9);
			}
		} catch (Exception e) {
			logger.debug(e);
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return calculatedDuty;	
	}
		
	
	
	public synchronized double[] getMuncipalCalculatedDuty(int deed,int instrument,double marketValue,double stampDuty)
	{
		double calculatedDuty[]=new double[3];
		 //calculatedDuty[0]="100";
		// calculatedDuty[1]="50";
		// calculatedDuty[2]="60";
		DBUtility dbUtil=null; 
		try {
			logger.debug("Municipal Value deedID:-" + deed + ":InstrumentId:-"
					+ instrument+"MARKET VALUE:-" + marketValue+ "STAMP DUTY" +stampDuty);
			
			dbUtil = new DBUtility();
			clstmt = dbUtil.returnCallableStatement(CommonSQL.MUNICIPAL_DUTY_CALCULATION_NEW);
			clstmt.setInt(1, deed);
			clstmt.setInt(2, instrument);
			clstmt.setInt(3, 0);
			clstmt.setString(4, null);
			clstmt.setDouble(5, marketValue);
			clstmt.setDouble(6, stampDuty);


			clstmt.registerOutParameter(7, OracleTypes.NUMBER);
			clstmt.registerOutParameter(8, OracleTypes.NUMBER);
			clstmt.registerOutParameter(9, OracleTypes.NUMBER);

			if (!clstmt.execute()) {
				calculatedDuty[0] = clstmt.getDouble(7);
				calculatedDuty[1] =  clstmt.getDouble(8);
				calculatedDuty[2] =  clstmt.getDouble(9);
			}
		} catch (Exception e) {
			logger.debug(e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return calculatedDuty;	
	}
			

	
	/**
	 * with flor id
	 * @param distId
	 * @param tehsilId
	 * @param wardId
	 * @param mohallaId
	 * @param propertyTypeId
	 * @param propertyTypeL1Id
	 * @param propertyTypeL2Id
	 * @param subclauseId
	 * @param floorId
	 * @param baseValue
	 * @param sqmeterType
	 * @param totalSqlMeter
	 * @param noTrees
	 * @return
	 * @throws Exception
	 */
	public synchronized String[] getPropertyValuation(String distId,
			String tehsilId, String wardId, String mohallaId,
			String propertyTypeId, String propertyTypeL1Id,
			String propertyTypeL2Id, String subclauseId, String floorId, double baseValue,
			String sqmeterType, double totalSqlMeter, String noTrees)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getPropertyValuation(String, " + "String, "
					+ "String, String, String, String, double) - start");
		}
		DBUtility dbUtil=null;
		String[] propertyValuation = new String[3];
		logger.debug("Inside getStampDuty");
		try {
			logger.debug("Property Value distID:-" + distId + ":TehsilID:-"
					+ tehsilId + ":WardID" + wardId + ":MohallaID:-"
					+ mohallaId + ":PropertyTypeID:-" + propertyTypeId
					+ ":L1:-" + propertyTypeL1Id + ":L2:-" + propertyTypeL2Id
					+ ":SubclauseID:-" + subclauseId +":floorId:-"+ floorId + ":baseValue:-"
					+ baseValue + ":sqMeterType:-" + sqmeterType
					+ ":totalSqm:-" + totalSqlMeter + ":noTrees:-" + noTrees);
			dbUtil = new DBUtility();
			clstmt = dbUtil
					.returnCallableStatement(CommonSQL.PROPERTY_VALUATION_PROCEDURE);
			clstmt.setString(1, distId);
			clstmt.setString(2, tehsilId);
			clstmt.setString(3, wardId);
			clstmt.setString(4, mohallaId);
			clstmt.setString(5, propertyTypeId);
			clstmt.setString(6, propertyTypeL1Id);
			clstmt.setString(7, propertyTypeL2Id);
			clstmt.setString(8, subclauseId);
			clstmt.setString(9, floorId);
			clstmt.setDouble(10, baseValue);
			clstmt.setString(11, sqmeterType);
			clstmt.setDouble(12, totalSqlMeter);
			clstmt.setString(13, noTrees);

			clstmt.registerOutParameter(14, OracleTypes.NUMBER);
			clstmt.registerOutParameter(15, OracleTypes.VARCHAR);
			clstmt.registerOutParameter(16, OracleTypes.VARCHAR);

			if (!clstmt.execute()) {
				int temp = clstmt.getInt(14);
				propertyValuation[0] = String.valueOf(temp);
				propertyValuation[1] = clstmt.getString(15);
				propertyValuation[2] = clstmt.getString(16);
			}
		} catch (Exception e) {
			logger.debug(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getPropertyValuation(String, " + "String, "
					+ "String, " + "String, " + "String, "
					+ "String, double) - end");
		}
		return propertyValuation;
	}
	/**
	 * without floor id 
	 * @param distId
	 * @param tehsilId
	 * @param wardId
	 * @param mohallaId
	 * @param propertyTypeId
	 * @param propertyTypeL1Id
	 * @param propertyTypeL2Id
	 * @param subclauseId
	 * @param baseValue
	 * @param sqmeterType
	 * @param totalSqlMeter
	 * @param noTrees
	 * @return String[]
	 * @throws Exception
	 */
	// Added by VINAY And SATBIR for Property Valuation of Agricultural and Plot Land.
	public synchronized String getPropertyValuationAgriPlot(double baseValue,String unitFlag,
			int districtId,int tehsilId, int wardId, int mohallaId,
			 String subclauseId, double totalSqlMeter,String TreeFlag, String noTrees)
			throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getPropertyValuation(String, " + "String, "
					+ "String, String, String, String, double) - start");
		}
		DBUtility dbUtil=null;
		String propertyValuation = new String();
		logger.debug("Inside getStampDuty");
		try {
			/*System.out.println("Property Value distID:-" + distId + ":TehsilID:-"
					+ tehsilId + ":WardID" + wardId + ":MohallaID:-"
					+ mohallaId + ":PropertyTypeID:-" + propertyTypeId
					+ ":L1:-" + propertyTypeL1Id + ":L2:-" + propertyTypeL2Id
					+ ":SubclauseID:-" + subclauseId + ":baseValue:-"
					+ baseValue + ":sqMeterType:-" + sqmeterType
					+ ":totalSqm:-" + totalSqlMeter + ":noTrees:-" + noTrees);*/
			dbUtil = new DBUtility();
			clstmt = dbUtil
					.returnCallableStatement(CommonSQL.PROPERTY_VALUATION_PROCEDURE_AGRI);
			clstmt.setDouble(1, baseValue);
			clstmt.setString(2, unitFlag);
			clstmt.setInt(3, districtId);
			clstmt.setInt(4, tehsilId);
			clstmt.setInt(5, wardId);
			clstmt.setInt(6, mohallaId);
			clstmt.setString(7, subclauseId);
			clstmt.setDouble(8, totalSqlMeter);
			clstmt.setString(9, TreeFlag);
			clstmt.setString(10, noTrees);
			clstmt.registerOutParameter(11, OracleTypes.VARCHAR);

			int temp = 0;
			if (!clstmt.execute()) {
			    temp = clstmt.getInt(11);
				propertyValuation = String.valueOf(temp);
			}
			System.out.println("Market Value Common:-"+temp);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			System.out.println("getPropertyValuation(String, " + "String, "
					+ "String, " + "String, " + "String, "
					+ "String, double) - end");
		}
		return propertyValuation;

		
	}
	public synchronized String[] getPropertyValuation(int distId,
		int tehsilId, int wardId, int mohallaId,
		int propertyTypeId, String propertyTypeL1Id,
		String propertyTypeL2Id, String subclauseId, double baseValue,
		String sqmeterType, double totalSqlMeter, String noTrees)
		throws Exception {
	if (logger.isDebugEnabled()) {
		System.out.println("getPropertyValuation(String, " + "String, "
				+ "String, String, String, String, double) - start");
	}

	String[] propertyValuation = new String[3];
	logger.debug("Inside getStampDuty");
	DBUtility dbUtil=null;
	try {
		
		System.out.println("Property Value distID:-" + distId + ":TehsilID:-"
				+ tehsilId + ":WardID" + wardId + ":MohallaID:-"
				+ mohallaId + ":PropertyTypeID:-" + propertyTypeId
				+ ":L1:-" + propertyTypeL1Id + ":L2:-" + propertyTypeL2Id
				+ ":SubclauseID:-" + subclauseId + ":baseValue:-"
				+ baseValue + ":sqMeterType:-" + sqmeterType
				+ ":totalSqm:-" + totalSqlMeter + ":noTrees:-" + noTrees);
		dbUtil = new DBUtility();
		clstmt = dbUtil
				.returnCallableStatement(CommonSQL.PROPERTY_VALUATION_PROCEDURE);
		clstmt.setInt(1, distId);
		clstmt.setInt(2, tehsilId);
		clstmt.setInt(3, wardId);
		clstmt.setInt(4, mohallaId);
		clstmt.setInt(5, propertyTypeId);
		clstmt.setString(6, propertyTypeL1Id);
		clstmt.setString(7, propertyTypeL2Id);
		clstmt.setString(8, subclauseId);
		clstmt.setString(9, null);//floor id should be null for agri & plot 
		clstmt.setString(10, null);//floor calc id  should be null for agri & plot 
		clstmt.setDouble(11, baseValue);
		clstmt.setDouble(12, totalSqlMeter);
		clstmt.setDouble(13, 0.0);
		clstmt.setString(14, noTrees);


        
        
		clstmt.registerOutParameter(15, OracleTypes.NUMBER);
		clstmt.registerOutParameter(16, OracleTypes.VARCHAR);
		clstmt.registerOutParameter(17, OracleTypes.VARCHAR);
		int temp = 0;
		if (!clstmt.execute()) {
		    temp = clstmt.getInt(15);
			propertyValuation[0] = String.valueOf(temp);
			propertyValuation[1] = clstmt.getString(16);
			propertyValuation[2] = clstmt.getString(17);
		}
		System.out.println("Market Value Common:-"+temp);
	} catch (Exception e) {
		System.out.println(e);
	} finally {
		if (dbUtil != null) {
			dbUtil.closeConnection();
		}
	}
	if (logger.isDebugEnabled()) {
		System.out.println("getPropertyValuation(String, " + "String, "
				+ "String, " + "String, " + "String, "
				+ "String, double) - end");
	}
	return propertyValuation;
}
	
	public synchronized String[] getFloorwisePropertyValuation(int distId,
			int tehsilId, int wardId, int mohallaId,
			int propertyTypeId, String propertyTypeL1Id,
			String propertyTypeL2Id, String floorTypeId, String subclauseId,
			double baseValue, int floorCalcId, double totalSqlMeter,
			double constructedArea, String noTrees) throws Exception {

		if (logger.isDebugEnabled()) {
			System.out.println("getPropertyValuation(String, " + "String, "
					+ "String, String, String, String, double) - start");
		}

		String[] propertyValuation = new String[3];
		System.out.println("Inside getStampDuty");
		DBUtility dbUtil=null;
		try {
			System.out.println("Property Value" + distId + ":" + tehsilId + ":"
					+ wardId + ":" + mohallaId + ":" + propertyTypeId + ":"
					+ propertyTypeL1Id + ":" + propertyTypeL2Id + ":"
					+ floorTypeId + ":" + subclauseId + ":" + baseValue + ":"
					+ floorCalcId + ":" + totalSqlMeter + ":" + constructedArea
					+ "TREES:" + noTrees);
			System.out.println("SUB CLAUSE ID IS "+subclauseId);
			dbUtil = new DBUtility();
			clstmt = dbUtil
					.returnCallableStatement(CommonSQL.PROPERTY_VALUATION_PROCEDURE);
			clstmt.setInt(1, distId);
			clstmt.setInt(2, tehsilId);
			clstmt.setInt(3, wardId);
			clstmt.setInt(4, mohallaId);
			clstmt.setInt(5, propertyTypeId);
			clstmt.setString(6, propertyTypeL1Id);
			clstmt.setString(7, propertyTypeL2Id);
			clstmt.setString(8, subclauseId);
			clstmt.setString(9, floorTypeId);
			clstmt.setDouble(10, floorCalcId);
			clstmt.setDouble(11, baseValue);
			clstmt.setDouble(12, totalSqlMeter);
			clstmt.setDouble(13, constructedArea);
			clstmt.setString(14, noTrees);

			clstmt.registerOutParameter(15, OracleTypes.NUMBER);
			clstmt.registerOutParameter(16, OracleTypes.VARCHAR);
			clstmt.registerOutParameter(17, OracleTypes.VARCHAR);

			if (!clstmt.execute()) {
				int temp = clstmt.getInt(15);
				propertyValuation[0] = String.valueOf(temp);
				propertyValuation[1] = clstmt.getString(16);
				propertyValuation[2] = clstmt.getString(17);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			System.out.println("getPropertyValuation(String, " + "String, "
					+ "String, " + "String, " + "String, "
					+ "String, double) - end");
		}
		return propertyValuation;
	}

	/**
	 * @param _refcatId
	 * @param _refBaseVal
	 * @param _refUnits
	 * @return String
	 */
	public synchronized String getJudicialStampDuty(String _refcatId,
			String _refBaseVal, String _refUnits) throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getJudicialStampDuty(String, String, String) -"
					+ " start");
		}

		String stampDuty = null;
		System.out.println("Inside - getJudicialStampDuty()");
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			clstmt = dbUtil
					.returnCallableStatement(CommonSQL.JUDICIAL_DUTY_CALCULATION_PROCEDURE);

			clstmt.setString(1, _refcatId);
			clstmt.setString(2, _refBaseVal);
			clstmt.setString(3, _refUnits);
			clstmt.registerOutParameter(4, OracleTypes.NUMBER);

			if (!clstmt.execute()) {
				int temp = clstmt.getInt(4);
				stampDuty = String.valueOf(temp);
			}
		} catch (Exception e) {
			logger.error("getJudicialStampDuty(String, String, String)", e);

			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		System.out.println("stamp Duty :-" + stampDuty);
		return stampDuty;
	}


	/**
	 * @param _retFunId
	 * @param _serId
	 * @param _userType
	 * @return
	 * @throws Exception
	 */
	public ArrayList getOthersFeeDuty(String _retFunId, String _serId,
			String _userType) throws Exception {
		ArrayList othersFeeDuty = new ArrayList();
		System.out.println("Inside getOthersFeeDuty()");
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			clstmt = dbUtil
					.returnCallableStatement(CommonSQL.SERVICE_FEE_PROCEDURE);
			clstmt.setString(1, _retFunId);
			clstmt.setString(2, _serId);
			clstmt.setString(3, _userType);
			clstmt.registerOutParameter(4, OracleTypes.NUMBER);
			clstmt.registerOutParameter(5, OracleTypes.CLOB);
			clstmt.registerOutParameter(6, OracleTypes.VARCHAR);
			if (!clstmt.execute()) {
				int temp = clstmt.getInt(4);
				othersFeeDuty = new ArrayList();
				String serviceFeeStr = String.valueOf(temp);
				double totalFee = Double.parseDouble(serviceFeeStr);
				double fee = 0.0;
				double otherFee = 0.0;
				String SerVal = clstmt.getString(5);
				String[] serTokenOne = SerVal.split(";");
				if (serTokenOne.length > 0) {
					for (int i = 1; i < serTokenOne.length; i++) {
						String[] serTokenTwo = serTokenOne[i].split(",");
						if (serTokenTwo.length > 0) {
							if (i == 1) {
								fee = Double.parseDouble(serTokenTwo[4]);
							}
						}
					}
				}
				othersFeeDuty.add("" + fee);
				otherFee = totalFee - fee;
				othersFeeDuty.add("" + otherFee);
				othersFeeDuty.add("" + totalFee);

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {

			dbUtil.closeConnection();
		}
		System.out.println("othersFeeDuty:-" + othersFeeDuty);
		return othersFeeDuty;
	}

	//added by shruti---23 may 2014
	public String getOthersFeeDutyNew(String _retFunId, String _serId,
			String _userType) throws Exception {
		String othersFeeDuty="";
		DBUtility dbUtil=null;	
		try {
				System.out.println("Inside getOthersFeeDuty()");
				
				String[] param={_retFunId};
				System.out.println("Inside getOthersFeeDuty()");
				dbUtil = new DBUtility();
				if("2".equalsIgnoreCase(_userType)){
					dbUtil.createPreparedStatement(PlockSQL.GET_SERVICE_FEE_RU);
					othersFeeDuty=dbUtil.executeQry(param);
					
				}
				else if("I".equalsIgnoreCase(_userType)){
					dbUtil.createPreparedStatement(PlockSQL.GET_SERVICE_FEE_DR);
					othersFeeDuty=dbUtil.executeQry(param);
				}
				else
				{
					dbUtil.createPreparedStatement(PlockSQL.GET_SERVICE_FEE_SP);
					othersFeeDuty=dbUtil.executeQry(param);
				}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}finally {
			dbUtil.closeConnection();
		}
		System.out.println("othersFeeDuty:-" + othersFeeDuty);
		return othersFeeDuty;
	}
//ended
	
	/**
	 * for calculating stamp duty for Judicial
	 * 
	 * @param catId
	 * @param basevalue
	 * @param units
	 * @return Stamp Duty
	 */

	public synchronized String getNonJudStampDuty(String _refDeedTypeId,
			String _refInstrId, String _refExemId, String _secAmt) {
		String stampDuty = null;
		System.out.println("Inside - getJudicialStampDuty()");
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			clstmt = dbUtil
					.returnCallableStatement(CommonSQL.NONJUDICIAL_DUTY_CALCULATION_PROCEDURE);

			clstmt.setString(1, _refDeedTypeId);
			clstmt.setString(2, _refInstrId);
			clstmt.setString(3, _refExemId);
			clstmt.setString(4, _secAmt);
			clstmt.setString(5, null);
			clstmt.setString(6, null);
			clstmt.setString(7, null);
			clstmt.setString(8, null);
			clstmt.setString(9, null);
			clstmt.registerOutParameter(10, OracleTypes.NUMBER);
			clstmt.registerOutParameter(11, OracleTypes.VARCHAR);
			clstmt.registerOutParameter(12, OracleTypes.VARCHAR);

			if (!clstmt.execute()) {
				int temp = clstmt.getInt(10);
				System.out.println("enter in to temp===" + temp);
				stampDuty = String.valueOf(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception x) {
				System.out.println(x);
			}
		}
		System.out.println("stamp Duty :-" + stampDuty);
		return stampDuty;
	}

	/**
	 * 
	 * @param _seqName
	 * @param _moduleConstant
	 * @return Auto generated sequence number.
	 * @throws Exception
	 */
	  public synchronized String getAutoId(String tableName,String colName,String prefix){
	        String refId=null;
	        DBUtility dbUtil=null;
	        try
	        {
	        	dbUtil=new DBUtility();
	        	String sqlCurrentdate="SELECT TO_CHAR(SYSDATE,'DDMMYYYY') FROM DUAL";
	            dbUtil.createStatement();
	            String currentDate=dbUtil.executeQry(sqlCurrentdate);           
	            
	            String certifiedIdPrefix=prefix+currentDate;
	            String sqlCertifiedId="SELECT MAX(TO_NUMBER(SUBSTR("+colName+","+(certifiedIdPrefix.length()+1)+"))) FROM "+tableName+" WHERE "+colName+"  LIKE '%"+certifiedIdPrefix+"%'";
	            String sqlId=dbUtil.executeQry(sqlCertifiedId);
	            if ("".equals(sqlId))
	            {
	                sqlId="0";
	            }
	            int id=Integer.parseInt(sqlId);
	            id++;

	            if (id>=0 && id<10)
	                sqlId = "0000"+id;
	            else if (id>=10 && id<100)
	                sqlId = "000"+id;
	            else if (id>=100 && id<1000)
	                sqlId = "00"+id;
	            else if (id>=1000 && id<10000)
	                sqlId = "0"+id;
	            else if (id>=10000 && id<100000)
	                sqlId = ""+id;

	            refId=certifiedIdPrefix+sqlId;

	        } catch (Exception x)
	        {
	           x.printStackTrace();
	        } finally
	        {
	            try
	            {
	                dbUtil.closeConnection();
	            } catch (Exception x)
	            {
	            	x.printStackTrace();
	            }
	        }

	        return refId;
	    }
	
	public synchronized String getSequenceNumber(String _seqName,
			String _moduleConstant) throws Exception {

		String seqnumber = "";
		DBUtility dbUtil=null;
		try {
			Date date = new Date();
			Format yearformat = new SimpleDateFormat("yyyy");
			Format monthformat = new SimpleDateFormat("MM");
			Format dateformat = new SimpleDateFormat("dd");
			String dfmt = dateformat.format(date);
			String yfmt = yearformat.format(date);
			String mfmt = monthformat.format(date);
			String SQL = "select " + _seqName + ".nextval from dual";
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String number = dbUtil.executeQry(SQL);
			seqnumber = _moduleConstant + dfmt + mfmt + yfmt + number;
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return seqnumber;
	}
	/**
	 * 
	 * @param _seqName
	 * @param _moduleConstant
	 * @return Auto generated sequence number.
	 * @throws Exception
	 */
	public synchronized String getTransactionNumber(String _seqName) throws Exception {

		String seqnumber = "";
		DBUtility dbUtil=null;
		try {
			Date date = new Date();
			Format yearformat = new SimpleDateFormat("yyyy");
			Format monthformat = new SimpleDateFormat("MM");
			Format dateformat = new SimpleDateFormat("dd");
			String dfmt = dateformat.format(date);
			String yfmt = yearformat.format(date);
			String mfmt = monthformat.format(date);
			String SQL = "select " + _seqName + ".nextval from dual";
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String number = dbUtil.executeQry(SQL);
			seqnumber =  dfmt + mfmt + yfmt + number;
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return seqnumber;
	}
	
/**
	 * 
	 * @param _seqName
	 * @param _moduleConstant
	 * @return Auto generated sequence number.
	 * @throws Exception
	 * @author Aakriti
	 */
	public synchronized String getSequenceNumber1(String _seqName,
			String _moduleConstant) throws Exception {

		String seqnumber = "";
		DBUtility dbUtil=null;
		try {
			Date date = new Date();
			Format yearformat = new SimpleDateFormat("yyyy");
			Format monthformat = new SimpleDateFormat("MM");
			Format dateformat = new SimpleDateFormat("dd");
			String dfmt = dateformat.format(date);
			String yfmt = yearformat.format(date);
			String mfmt = monthformat.format(date);
			String dt = dfmt + mfmt + yfmt;
			String SQL1 = "select count(ESTAMP_CODE) from IGRS_ESTAMP_TXN_DETAILS where trunc(ESTAMP_DATE)=trunc(sysdate)";
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String number1 = dbUtil.executeQry(SQL1);
		    logger.debug("value of count is---------"+number1);
			if (number1 == "0"){
				logger.debug("in if clause");
				String drpqry = "DROP SEQUENCE IGRSTEST.IGRS_ESTAMP_SEQ_STAMP_CODE";				
				dbUtil.createStatement();
				dbUtil.executeQry(drpqry);
				String crtqry = "CREATE SEQUENCE IGRSTEST.IGRS_ESTAMP_SEQ_STAMP_CODE  START WITH 1000 INCREMENT BY 2 MAXVALUE 100000 MINVALUE 1000";
				dbUtil.createStatement();
				dbUtil.executeQry(crtqry);
			}
		
				String SQL = "select " + _seqName + ".nextval from dual";
				dbUtil = new DBUtility();
				dbUtil.createStatement();
				String number = dbUtil.executeQry(SQL);
				seqnumber = _moduleConstant + dfmt + mfmt + yfmt + number;
			
			
		
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return seqnumber;
	}


	/**
	 * @param param
	 * @return
	 */
	public synchronized ArrayList getSubClause(String[] param, String SQL)
			throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getSubClause(String[]) - start");
		}
		DBUtility dbUtil=null;
		ArrayList list = new ArrayList();
		try {
			System.out.println("Query :-" + SQL);
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			list = dbUtil.executeQuery(param);
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			System.out.println("getSubClause(String[]) - end");
		}
		return list;
	}

	/**
	 * @return String
	 */
	public String getCurrentDate() {
		String dateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {

			dateStr = format.format(new Date()).toString();
			System.out.println("Current Date:-" + dateStr);
		} catch (Exception x) {
			System.out.println(x);
		}
		return dateStr;
	}

	/**
	 * @param String
	 * @return String
	 */
	public String getCurrencyFormat(double amount) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		return formatter.format(amount).toString();

	}
	public String getCurrencyFormatBaseValue(double amount) {
		NumberFormat formatter = new DecimalFormat("#0.00000000000000");
		return formatter.format(amount).toString();

	}
	/**
	 * @param date
	 * @param dateformat
	 * @return String
	 */
	public String getDateFormat(String date, String dateformat) {
		String strDate = "";

		return strDate;
	}

	/**
	 * 
	 * @param deed
	 * @param instrument
	 * @param exemption
	 * @param baseValue
	 * @return String []
	 * @throws Exception
	 */
	public synchronized double getRegistrstionFee(int deed,
			int instrument, String exemption,double duty, double baseValue)
			throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getStampDuty(String, String, String) - start");
		}

		double regFee=0.0;
		System.out.println("Inside getStampDuty");
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			clstmt = dbUtil
					.returnCallableStatement(CommonSQL.REGISTRATION_FEE_PROCEDURE);
			System.out.println("Duty value:-" + deed + ":" + instrument + ":"
					+ exemption + ":" + baseValue);
			clstmt.setInt(1, deed);
			clstmt.setInt(2, instrument);
			clstmt.setString(3, exemption);
			clstmt.setDouble(4, duty);
			clstmt.setDouble(5, baseValue);
			clstmt.registerOutParameter(6, OracleTypes.NUMBER);
			clstmt.registerOutParameter(7, OracleTypes.VARCHAR);
			clstmt.registerOutParameter(8, OracleTypes.VARCHAR);

			if (!clstmt.execute()) {
			regFee =  clstmt.getDouble(6);
				
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			System.out.println("getStampDuty(String, String, String) - end");
		}
		return regFee;
	}

	/**
	 * @param distId
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getThesil(int distId) throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getThesil(String) - start");
		}

		//String SQL = CommonSQL.TEHSIL_QUERY;
		String SQL = CommonSQL.TEHSIL_QUERY_HINDI;
		ArrayList list = new ArrayList();
		int arry[] = new int[1];
		
			arry[0] = distId;
			DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			System.out.println("Wipro in IGRSCommon - "
					+ "getTehsil() after PreparedStatement");
			list = dbUtil.executeQuery(arry);
			System.out.println("Wipro in IGRSCommon - "
					+ "getTehsil() after executeQuery");
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		return list;
	}
	
	/**
	 * @param distId
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getGuidelineDuration(int distId) throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getGuidelineDuration(String) - start");
		}

		String SQL = CommonSQL.GUIDELINE_DURATION;
		ArrayList list = new ArrayList();
		int arry[] = new int[1];
		
			arry[0] = distId;
			DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			System.out.println("Wipro in IGRSCommon - "
					+ "getGuidelineDuration() after PreparedStatement");
			list = dbUtil.executeQuery(arry);
			System.out.println("Wipro in IGRSCommon - "
					+ "getGuidelineDuration() after executeQuery");
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		return list;
	}

	/**
	 * @param distId
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getGuidelineValue( int districtId,
			int tehsilId,int wardId, int mohallaId, int propertyId, String propertyTypeL1Id,
			String propertyTypeL2Id) throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getGuidelineDuration(String) - start");
		}
		ArrayList list = new ArrayList();
		
if(propertyTypeL2Id!=null)		
{
		String SQL = CommonSQL.GUIDELINE_VALUE_PROP_B;
//		ArrayList list = new ArrayList();
		String arry[] = new String[7];
		
			arry[0] = Integer.toString(districtId);
			arry[1] = Integer.toString(tehsilId);
			arry[2] = Integer.toString(wardId);
			arry[3] = Integer.toString(mohallaId);
			arry[4] = Integer.toString(propertyId);
			arry[5] = propertyTypeL1Id;
			arry[6] =propertyTypeL2Id;
			
			DBUtility dbUtil=null;	
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			System.out.println("Wipro in IGRSCommon - "
					+ "getGuidelineDuration() after PreparedStatement");
			list = dbUtil.executeQuery(arry);
			System.out.println("Wipro in IGRSCommon - "
					+ "getGuidelineDuration() after executeQuery");
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
}else
{
	String SQL = CommonSQL.GUIDELINE_VALUE_PROP;
	//ArrayList list = new ArrayList();
	String arry[] = new String[6];
	
		arry[0] = Integer.toString(districtId);
		arry[1] = Integer.toString(tehsilId);
		arry[2] = Integer.toString(wardId);
		arry[3] = Integer.toString(mohallaId);
		arry[4] = Integer.toString(propertyId);
		arry[5] = propertyTypeL1Id;
	//	arry[6] =propertyTypeL2Id;
		
		DBUtility dbUtil=null;
	try {
		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(SQL);
		System.out.println("Wipro in IGRSCommon - "
				+ "getGuidelineDuration() after PreparedStatement");
		list = dbUtil.executeQuery(arry);
		System.out.println("Wipro in IGRSCommon - "
				+ "getGuidelineDuration() after executeQuery");
	} catch (Exception x) {
		System.out.println(x);
	} finally {
		if (dbUtil != null) {
			dbUtil.closeConnection();
		}
	}
}
		return list;
	}
	/**
	 * @param distId
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getThesil(String distId) throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getThesil(String) - start");
		}

		//String SQL = CommonSQL.TEHSIL_QUERY;
		String SQL = CommonSQL.TEHSIL_QUERY_HINDI;
		ArrayList list = new ArrayList();
		String arry[] = new String[1];
		if (distId != null) {
			arry[0] = distId;
		}
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			System.out.println("Wipro in IGRSCommon - "
					+ "getTehsil() after PreparedStatement");
			list = dbUtil.executeQuery(arry);
			System.out.println("Wipro in IGRSCommon - "
					+ "getTehsil() after executeQuery");
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		return list;
	}

	//added by shruti--23 jan 2014 in use for doc srch module
	public synchronized ArrayList getThesil(String distId,String language) throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getThesil(String) - start");
		}
		String SQL = "";
		if ("en".equalsIgnoreCase(language)) {
			SQL = CommonSQL.TEHSIL_QUERY;
		} else {
			SQL = CommonSQL.TEHSIL_QUERY_H;
		}
		ArrayList list = new ArrayList();
		String arry[] = new String[1];
		if (distId != null) {
			arry[0] = distId;
		}
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			System.out.println("Wipro in IGRSCommon - "
					+ "getTehsil() after PreparedStatement");
			list = dbUtil.executeQuery(arry);
			System.out.println("Wipro in IGRSCommon - "
					+ "getTehsil() after executeQuery");
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		return list;
	}

	//added by shruti---25 nov 2014
	public synchronized ArrayList getRegistrationTehsil(String distId,String language) throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getTehsil(String) - start");
		}
		String SQL = "";
		if ("en".equalsIgnoreCase(language)) {
			SQL = CommonSQL.REG_TEHSIL_QUERY;
		} else {
			SQL = CommonSQL.REG_TEHSIL_QUERY_H;
		}
		ArrayList list = new ArrayList();
		String arry[] = new String[1];
		if (distId != null) {
			arry[0] = distId;
		}
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			System.out.println("Wipro in IGRSCommon - "
					+ "getRegistrationTehsil after PreparedStatement");
			list = dbUtil.executeQuery(arry);
			System.out.println("Wipro in IGRSCommon - "
					+ "getRegistrationTehsil after executeQuery");
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		return list;
	}

	
	//end
	//added by shruti---26 may 2014
	public synchronized ArrayList getSubAreaType(String areaTypeId,String language) throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getSubAreaType(String) - start");
		}
		String SQL = "";
		if ("en".equalsIgnoreCase(language)) {
			SQL = CommonSQL.SUB_AREA_NAME_EN;
		} else {
			SQL = CommonSQL.SUB_AREA_NAME_HI;
		}
		ArrayList list = new ArrayList();
		String arry[] = new String[1];
		if (areaTypeId != null) {
			arry[0] = areaTypeId;
		}
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			System.out.println("Wipro in IGRSCommon - "
					+ "getSubAreaType() after PreparedStatement");
			list = dbUtil.executeQuery(arry);
			System.out.println("Wipro in IGRSCommon - "
					+ "getSubAreaType() after executeQuery");
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		return list;
	}

	//end
	
	/**
	 * 
	 * @param option
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getDeedType(String option) throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getDeedType(String) - start");
		}
		String SQL =null;
		if (option.equals("P") ) {
			SQL = CommonSQL.DEED_QUERY_PROPERTY;
		}
		else if (option.equals("R") )
		{
			SQL = CommonSQL.DEED_QUERY_REGISTRATION;
		}
		else if (option.equals("NP") )
		{
			//SQL = CommonSQL.DEED_QUERY_REGISTRATION_NON_PROPERTY;
			SQL = CommonSQL.DEED_QUERY_DC_COMMON;
		}
		else if (option.equals("D") )
		{
			// SQL = CommonSQL.DEED_QUERY;
			SQL = CommonSQL.DEED_QUERY_DC_COMMON;
		}else{
			SQL = CommonSQL.DEED_QUERY_DC_COMMON;
		}
		ArrayList returnArrayList = new ArrayList();
		DBUtility dbUtil=null;
		try {

			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			String [] arr =new String[1];
			arr[0]=option;
			returnArrayList = dbUtil.executeQuery(arr);
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		if (logger.isDebugEnabled()) {
			System.out.println("getDeedType(String) - end");
		}
		return returnArrayList;

	}

	/**
	 * @param mhId
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getWard(int tehsilId, int areaType,
			int wardType) throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getWard(String, String, String) - start");
		}

		//String SQL = CommonSQL.WARD_QUERY;
		String SQL = CommonSQL.WARD_QUERY_HINDI;
		int arry[] = new int[3];
		ArrayList list = new ArrayList();
		arry[0] = tehsilId;
		arry[1] = areaType;
		arry[2] = wardType;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			System.out.println("Thesil"+tehsilId +" areaType: "+areaType+"wardType:"+ wardType);
			dbUtil.createPreparedStatement(SQL);
			System.out.println("Wipro in IGRSCommon - "
					+ "getWard() after preparedStatement");
			list = dbUtil.executeQuery(arry);
			System.out.println("Wipro in IGRSCommon - "
					+ "getWard() after executeQuery");
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		return list;
	}

	/**
	 * @param mhId
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getWard(String tehsilId, String areaType,
			String wardType) throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getWard(String, String, String) - start");
		}

		//String SQL = CommonSQL.WARD_QUERY;
		String SQL = CommonSQL.WARD_QUERY_HINDI;
		String arry[] = new String[3];
		ArrayList list = new ArrayList();
		if (tehsilId != null) {
			arry[0] = tehsilId;
		}

		arry[1] = areaType;
		arry[2] = wardType;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			System.out.println("Wipro in IGRSCommon - "
					+ "getWard() after preparedStatement");
			list = dbUtil.executeQuery(arry);
			System.out.println("Wipro in IGRSCommon - "
					+ "getWard() after executeQuery");
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		return list;
	}

	
	/*
	 * Method :getTeshil Description :fetch the Tehsil added by sunil oneapps
	 * 
	 */

	public ArrayList getTehsil() throws Exception {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			logger
					.debug("Wipro in IGRSCommon - getCountry() after create statement");
			list = dbUtil
					.executeQuery("Select TEHSIL_ID,TEHSIL_NAME from IGRS_TEHSIL_MASTER where TEHSIL_STATUS='A'");
			logger
					.debug("Wipro in IGRSCommon - getCountry() after execute query");
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;

	}

	/*
	 * Method :getFund Description :fetch the FundList added by sunil oneapps
	 */

	public ArrayList getFund() throws Exception {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil
					.executeQuery("select FUND_TYPE_ID,FUND_NAME from IGRS_EMP_FUND_TYPE_MASTER");
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	/*
	 * Method :getAssetType Description :fetch the Assest TypelIst added by
	 * sunil oneapps
	 */
	public ArrayList getAssetTypeList() throws Exception {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			logger
					.debug("Wipro in IGRSCommon - getCountry() after create statement");
			list = dbUtil
					.executeQuery("Select ASSET_TYPE_ID, ASSET_TYPE_NAME from IGRS_EMP_ASSET_MASTER");
			logger
					.debug("Wipro in IGRSCommon - getCountry() after execute query");
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	/*
	 * Method :getReligion Description :fetch the Assest TypelIst added by sunil
	 * oneapps
	 */
	public ArrayList getReligion() throws Exception {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			logger
					.debug("Wipro in IGRSCommon - getCountry() after create statement");
			list = dbUtil
					.executeQuery("Select RELIGION_ID, RELIGION_NAME from IGRS_RELIGION_MASTER");
			logger
					.debug("Wipro in IGRSCommon - getReligion() after execute query");
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	/*
	 * Method :getCaste Description :fetch the Assest TypelIst added by sunil
	 * oneapps
	 */
	public ArrayList getCaste() throws Exception {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			logger
					.debug("Wipro in IGRSCommon - getCountry() after create statement");
			list = dbUtil
					.executeQuery("Select CASTE_ID, CASTE_NAME from IGRS_CASTE_MASTER");
			logger
					.debug("Wipro in IGRSCommon - getCaste() after execute query");
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	public synchronized String[] getMunicipalDuty(int distId,
			int tehsilId, int wardId, int mohallaId, double marketValue)
			throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getMunicipalDuty(String, String, "
					+ "String, String, double) - start");
		}

		String[] propertyValuation = new String[3];
		System.out.println("Inside getStampDuty");
		DBUtility dbUtil=null;
		try {
			System.out.println("Property Value" + distId + ":" + tehsilId + ":"
					+ wardId + ":" + mohallaId);
			dbUtil = new DBUtility();
			clstmt = dbUtil
					.returnCallableStatement(CommonSQL.MUNICIPAL_DUTY_CALCULATION);
			clstmt.setInt(1, distId);
			clstmt.setInt(2, tehsilId);
			clstmt.setInt(3, wardId);
			clstmt.setInt(4, mohallaId);
			clstmt.setDouble(5, marketValue);

			clstmt.registerOutParameter(6, OracleTypes.VARCHAR);
			clstmt.registerOutParameter(7, OracleTypes.VARCHAR);
			clstmt.registerOutParameter(8, OracleTypes.VARCHAR);
			clstmt.registerOutParameter(9, OracleTypes.VARCHAR);
			clstmt.registerOutParameter(10, OracleTypes.VARCHAR);

			if (!clstmt.execute()) {
				propertyValuation[0] = clstmt.getString(6);
				propertyValuation[1] = clstmt.getString(7);
				propertyValuation[2] = clstmt.getString(8);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			System.out.println("getPropertyValuation(String, " + "String, "
					+ "String, " + "String, " + "String, "
					+ "String, double) - end");
		}
		return propertyValuation;
	}

	// Latchiya code
	public ArrayList getIntimationType() throws Exception {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil
					.executeQuery("Select INTIMATION_TYPE_ID, INTIMATION_TYPE_DESC from IGRS_INTIMATION_TYPE_MASTER");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}

	public ArrayList getIDProof() throws Exception {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil
					.executeQuery("Select PHOTO_PROOF_TYPE_ID, PHOTO_PROOF_TYPE_NAME,H_PHOTO_PROOF_TYPE_NAME from IGRS_PHOTOID_PROOF_TYPE_MASTER");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		return list;
	}

	/**
	 * @param _areaTypeId
	 * @return
	 * @throws Exception
	 *             Added By Hari
	 */
	// _regcmpldto.getTehsilId(),_regcmpldto.getAreaTypeId(),_regcmpldto.getWardpatwarId()
	public synchronized ArrayList getWardOrPatwari(String[] _ward,String language)
			throws Exception {//
		//
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			//MODIFIED BY SHRUTI---24 JAN 2014
			String SQL ="";
			if("en".equalsIgnoreCase(language))
			{
			SQL = CommonSQL.GETWARDPATWARILIST;
			}
			else
			{
				SQL = CommonSQL.GETWARDPATWARILIST_H;
			}
			//END
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			list = dbUtil.executeQuery(_ward);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {

				dbUtil.closeConnection();
			}

		}
		return list;
	}

	//added by shruti---26 may 2014
	public synchronized ArrayList getWardOrPatwariNew(String[] _ward,String language)
	throws Exception {//
//
ArrayList list = null;
DBUtility dbUtil=null;
try {
	//MODIFIED BY SHRUTI---24 JAN 2014
	String SQL ="";
	if("en".equalsIgnoreCase(language))
	{
	SQL = CommonSQL.WARD_PATWARI_NAME_EN;
	}
	else
	{
		SQL = CommonSQL.WARD_PATWARI_NAME_HI;
	}
	//END
	dbUtil = new DBUtility();
	dbUtil.createPreparedStatement(SQL);
	list = dbUtil.executeQuery(_ward);
} catch (Exception e) {
	logger.error(e);
} finally {
	if (dbUtil != null) {

		dbUtil.closeConnection();
	}

}
return list;
}

	//end
	/**
	 * @param _wardRpatwariId
	 * @return
	 * @throws Exception
	 *             Added By Hari
	 */
	//modified by shruti---27 jan 2014-in use for doc srch module
	public synchronized ArrayList getMohallaOrVillage(String[] _ward,String language)
			throws Exception {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			String SQL ="";
			if("en".equalsIgnoreCase(language))
			{
				SQL=CommonSQL.GETMOHALLAVILLAGELIST;
			}
			else
			{
				SQL=CommonSQL.GETMOHALLAVILLAGELIST_H;
			}
			String arry[] = new String[1];
			dbUtil = new DBUtility();

			dbUtil.createPreparedStatement(SQL);
			list = dbUtil.executeQuery(_ward);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {

				dbUtil.closeConnection();
			}

		}
		return list;
	}

	//added by shruti---26 may 2014
	public synchronized ArrayList getMohallaOrVillageNew(String[] _ward,String language)
	throws Exception {
ArrayList list = null;
DBUtility dbUtil=null;
try {
	String SQL ="";
	if("en".equalsIgnoreCase(language))
	{
		SQL=CommonSQL.COLONY_NAME_EN;
	}
	else
	{
		SQL=CommonSQL.COLONY_NAME_HI;
	}
	String arry[] = new String[1];
	dbUtil = new DBUtility();

	dbUtil.createPreparedStatement(SQL);
	list = dbUtil.executeQuery(_ward);
} catch (Exception e) {
	logger.error(e);
} finally {
	if (dbUtil != null) {

		dbUtil.closeConnection();
	}

}
return list;
}
	//end
	/**
	 * 
	 * @param option
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getDeedType() throws Exception {
		String SQL = "SELECT DEED_TYPE_ID,DEED_TYPE_NAME FROM IGRS_DEED_TYPE_MASTER WHERE DEED_STATUS='A'";
		DBUtility dbUtil=null;
		ArrayList list=null;
		dbUtil= new DBUtility();
		dbUtil.createStatement();
		list= dbUtil.executeQuery(SQL);
		dbUtil.closeConnection();
		return list;
	}

	// Method Added by latchiya
	public ArrayList getRevheadlistIgrs() throws Exception {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			/*list = dbUtil
					.executeQuery("Select REVENUE_HEAD_ID, REVENUE_HEAD_NAME from IGRS_REVENUE_HEAD_MASTER");*/
			//modified by shruti
			list = dbUtil
			.executeQuery("Select REVENUE_HEAD_ID, REVENUE_HEAD_NAME from IGRS_CASE_REVENUE_HEAD_MASTER");
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {

				dbUtil.closeConnection();
			}
		}
		return list;
	}

	// Method Added by latchiya
	public ArrayList getSecheadlistIgrs() throws Exception {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			/*list = dbUtil
					.executeQuery("Select SECTION_HEAD_ID, SECTION_HEAD_NAME from IGRS_SECTION_HEAD_MASTER");*/
			
			//modified by shruti
			list = dbUtil
			.executeQuery("Select SECTION_HEAD_ID, SECTION_HEAD_NAME from IGRS_CASE_SECTION_HEAD_MASTER");
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {

				dbUtil.closeConnection();
			}
		}
		return list;
	}

	// Method Added by latchiya
	// CaseMonitoring Module - get the revenue head from igrs_case_revenue_head_master
	public ArrayList getRevenueList(String caseHead) throws Exception {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			if("CH_001".equalsIgnoreCase(caseHead))
			{
				list = dbUtil.executeQuery("Select REVENUE_HEAD_ID, REVENUE_HEAD_NAME from IGRS_CASE_REVENUE_HEAD_MASTER where revenue_head_status='A' and REVENUE_CASE_HEAD_CHK='Y'");
			}
			else
			{
			list = dbUtil.executeQuery("Select REVENUE_HEAD_ID, REVENUE_HEAD_NAME from IGRS_CASE_REVENUE_HEAD_MASTER where revenue_head_status='A'");
			}
			} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {

				dbUtil.closeConnection();
			}
		}
		return list;
	}

	// Method Added by latchiya
	// Case Monitoring Module - get the section head from igrs_case_section_head_master
	public ArrayList getSectionList(String caseType) throws Exception {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			if("CASET_001".equalsIgnoreCase(caseType)||"CASET_002".equalsIgnoreCase(caseType)||"CASET_003".equalsIgnoreCase(caseType))
			{
				list = dbUtil.executeQuery("Select SECTION_HEAD_ID, SECTION_HEAD_NAME from IGRS_CASE_SECTION_HEAD_MASTER where section_head_status='A' and SECTION_HEAD_NAME IN('48B','47A3')");	
			}
			else if("CASET_004".equalsIgnoreCase(caseType))
			{
				list = dbUtil.executeQuery("Select SECTION_HEAD_ID, SECTION_HEAD_NAME from IGRS_CASE_SECTION_HEAD_MASTER where section_head_status='A' and SECTION_HEAD_NAME IN('48B','33')");	
			}
			else if("CASET_005".equalsIgnoreCase(caseType))
			{
				list = dbUtil.executeQuery("Select SECTION_HEAD_ID, SECTION_HEAD_NAME from IGRS_CASE_SECTION_HEAD_MASTER where section_head_status='A' and SECTION_HEAD_NAME IN('48A1','33','47A1A')");	
			}
			else
			{
			list = dbUtil
					.executeQuery("Select SECTION_HEAD_ID, SECTION_HEAD_NAME from IGRS_CASE_SECTION_HEAD_MASTER where section_head_status='A'");
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {

				dbUtil.closeConnection();
			}
		}
		return list;
	}

	/**
	 * 
	 * @param deed
	 * @param instrument
	 * @param exemption
	 * @param propTxnId
	 * @param baseValue
	 * @return
	 * @throws Exception
	 */
	public String[] getRegStampDuty(String deed, String instrument,
			String exemption, String propTxnId) throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getStampDuty(String, String, String) - start");
		}

		String[] stampDuty = new String[3];
		System.out.println("Inside getStampDuty");
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			clstmt = dbUtil
					.returnCallableStatement(CommonSQL.REG_STAMPDUTY_PROCEDURE);

			clstmt.setString(1, deed);
			clstmt.setString(2, instrument);
			clstmt.setString(3, exemption);
			clstmt.setString(4, null);
			clstmt.setString(5, propTxnId);
			clstmt.setString(6, null);
			clstmt.setString(7, null);
			clstmt.setString(8, null);
			clstmt.setString(9, null);
			clstmt.registerOutParameter(10, OracleTypes.NUMBER);
			clstmt.registerOutParameter(11, OracleTypes.VARCHAR);
			clstmt.registerOutParameter(12, OracleTypes.VARCHAR);

			if (!clstmt.execute()) {
				int temp = clstmt.getInt(10);
				stampDuty[0] = String.valueOf(temp);
				stampDuty[1] = clstmt.getString(11);
				stampDuty[2] = clstmt.getString(12);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			System.out.println("getStampDuty(String, String, String) - end");
		}
		return stampDuty;
	}

	/**
	 * getting Municipal Duty
	 * 
	 * @param districtId
	 * @param tehsil
	 * @param ward
	 * @param mohilla
	 * @param propVal
	 * @return
	 */
	public ArrayList getMunDuty(String districtId, String tehsil, String ward,
			String mohilla, String propVal) {
		ArrayList munDuty = new ArrayList();
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			double pVal = Double.parseDouble(propVal);
			clstmt = dbUtil
					.returnCallableStatement(CommonSQL.MUN_DUTY_PROCEDURE);
			clstmt.setString(1, districtId);
			clstmt.setString(2, tehsil);
			clstmt.setString(3, ward);
			clstmt.setString(4, mohilla);
			clstmt.setDouble(5, pVal);
			clstmt.registerOutParameter(6, OracleTypes.VARCHAR);
			clstmt.registerOutParameter(7, OracleTypes.VARCHAR);
			clstmt.registerOutParameter(8, OracleTypes.NUMBER);
			clstmt.registerOutParameter(9, OracleTypes.VARCHAR);
			clstmt.registerOutParameter(10, OracleTypes.VARCHAR);
			if (!clstmt.execute()) {
				String dutyId = clstmt.getString(6);
				String dutyName = clstmt.getString(7);
				int temp = clstmt.getInt(8);
				String dutyVal = String.valueOf(temp);
				String errCode = clstmt.getString(9);
				String errMsg = clstmt.getString(10);
				munDuty.add(dutyId);
				munDuty.add(dutyName);
				munDuty.add(dutyVal);
				munDuty.add(errCode);
				munDuty.add(errMsg);

			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return munDuty;
	}

	
	public String checkValidTime(String fnName) {
		String errorMsg = "";
		String status = "";
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			 
			clstmt = dbUtil
					.returnCallableStatement(CommonSQL.ACCESS_TIME_PROCEDURE);
			clstmt.setString(1, fnName);
			 
			clstmt.registerOutParameter(2, OracleTypes.VARCHAR);
			clstmt.registerOutParameter(3, OracleTypes.VARCHAR);
			 
			if (!clstmt.execute()) {
				status = clstmt.getString(2);
				errorMsg = clstmt.getString(3);
			}
			
		   
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return errorMsg;
	}
	/**
	 * getting Regtistation Fee
	 * 
	 * @param deedId
	 * @param instrument
	 * @param strexem
	 * @param propVal
	 * @return
	 */
	public String[] getRegFeeDuty(String deedId, String instrument,
			String strexem, String propVal) {
		if (logger.isDebugEnabled()) {
			System.out.println("getStampDuty(String, String, String) - start");
		}

		String[] regFeeDuty = new String[3];
		System.out.println("Inside getStampDuty");
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			clstmt = dbUtil
					.returnCallableStatement(CommonSQL.REG_FEE_DUTY_PROCEDURE);
			clstmt.setString(1, deedId);
			clstmt.setString(2, instrument);
			clstmt.setString(3, strexem);
			clstmt.setString(4, propVal);
			clstmt.registerOutParameter(5, OracleTypes.NUMBER);
			clstmt.registerOutParameter(6, OracleTypes.VARCHAR);
			clstmt.registerOutParameter(7, OracleTypes.VARCHAR);
			if (!clstmt.execute()) {
				int temp = clstmt.getInt(5);
				regFeeDuty[0] = String.valueOf(temp);
				regFeeDuty[1] = clstmt.getString(6);
				regFeeDuty[2] = clstmt.getString(7);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (logger.isDebugEnabled()) {
			System.out.println("getStampDuty(String, String, String) - end");
		}
		return regFeeDuty;
	}

	/**
	 * @param _areaTypeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPropertyTypeList() throws Exception {
		//  
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			String SQL = "SELECT  PROPERTY_TYPE_ID, PROPERTY_TYPE_NAME FROM IGRS_PROPERTY_TYPE_MASTER WHERE PROPERTY_TYPE_STATUS='A'";
			String arry[] = new String[1];

			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {

				dbUtil.closeConnection();
			}

		}
		return list;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPropertyTypeDisp(String _propertyTypeID)
			throws Exception {
		//  
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			String SQL = "SELECT PROPERTY_TYPE_L1_ID, PROPERTY_TYPE_L1_NAME FROM IGRS_PROP_TYPE_L1_MASTER where PROPERTY_TYPE_L1_STATUS='A' and PROPERTY_TYPE_ID='"
					+ _propertyTypeID + "'";
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {

				dbUtil.closeConnection();
			}

		}
		return list;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPropertySubclause(String array[], String _sqlqry)
			throws Exception {

		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(_sqlqry);
			list = dbUtil.executeQuery(array);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {

				dbUtil.closeConnection();
			}

		}
		return list;
	}

	/**
	 * @param _areaTypeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getMunicipalGovern() throws Exception {
		DBUtility dbUtil=null;
		dbUtil = new DBUtility();
		ArrayList list = null;
		try {
			String SQL = "SELECT  MUNICIPAL_BODY_ID, MUNICIPAL_BODY_NAME FROM IGRS_GOV_MUNICIPAL_BODY_MASTER where MUNICIPAL_BODY_STATUS='A'";
			// String arry[] = new String[1];

			dbUtil.createStatement();
			dbUtil.createPreparedStatement(SQL);
			list = dbUtil.executeQuery(SQL);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {

				dbUtil.closeConnection();
			}

		}
		return list;
	}

	// property floor wise data capture
	public String[] getFloorPropertyValuation(int distId, int tehsilId,
			int wardId, int mohallaId, int propertyTypeId,
			String propertyTypeL1Id, String propertyTypeL2Id,
			String subclauseId, String floorId, String floorSubclauses,
			double baseValue, double totalArea, String noOftrees)
			throws Exception {

		if (logger.isDebugEnabled()) {
			System.out.println("getPropertyValuation(String, " + "String, "
					+ "String, String, String, String, double) - start");
		}
		String[] propertyValuation = new String[3];
		System.out.println("Inside getStampDuty");
		DBUtility dbUtil=null;
		try {

			dbUtil = new DBUtility();
			clstmt = dbUtil
					.returnCallableStatement(CommonSQL.PROPERTY_FLOOR_VALUATION_PROCEDURE);

			clstmt.setInt(1, distId);
			clstmt.setInt(2, tehsilId);
			clstmt.setInt(3, wardId);
			clstmt.setInt(4, mohallaId);
			clstmt.setInt(5, propertyTypeId);
			clstmt.setString(6, propertyTypeL1Id);
			clstmt.setString(7, propertyTypeL2Id);
			clstmt.setString(8, subclauseId);
			clstmt.setString(9, floorId);
			clstmt.setString(10, floorSubclauses);
			clstmt.setDouble(11, baseValue);
			clstmt.setDouble(12, totalArea);
			clstmt.setString(13, noOftrees);

			clstmt.registerOutParameter(14, OracleTypes.NUMBER);// v
			clstmt.registerOutParameter(15, OracleTypes.VARCHAR);// e
			clstmt.registerOutParameter(16, OracleTypes.VARCHAR);// eem

			if (!clstmt.execute()) {
				int temp = clstmt.getInt(14);
				propertyValuation[0] = String.valueOf(temp);
				propertyValuation[1] = clstmt.getString(14);
				propertyValuation[2] = clstmt.getString(15);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			System.out.println("getPropertyValuation(String, " + "String, "
					+ "String, " + "String, " + "String, "
					+ "String, double) - end");
		}
		return propertyValuation;
	}

	public String[] getLogDet(HttpSession session) 
	{
		String roleId = (String)session.getAttribute("role");
		String funId = (String)session.getAttribute("functionId");
		String userId = (String)session.getAttribute("UserId");
		String logDet[] = new String[3];
		logDet[0] = roleId;
		logDet[1] = roleId;
		logDet[2] = roleId;
		return logDet;
	}
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getBookType() throws Exception {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil =new DBUtility();
			String SQL = "SELECT BOOK_TYPE_ID, BOOK_TYPE_NAME FROM IGRS_REG_BOOK_TYPE_MASTER  WHERE  BOOK_TYPE_STATUS='A'";
			String arry[] = new String[1];
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {

				dbUtil.closeConnection();
			}

		}
		return list;
	}
	
	
	public void updateUserDetails(String id,String finalValuationId,String partyLabel) throws Exception {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil =new DBUtility();
			 String SQL = "UPDATE IGRS_PROP_VAL_DETAILS SET REF_VAL_TXN_ID= '"+finalValuationId+"',PARTY_TYPE= '"+partyLabel+"'"+
			 		" WHERE VAL_TXN_ID ='"+id+"'";
			
			dbUtil.createStatement();
			 dbUtil.executeUpdate(SQL);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {

				dbUtil.closeConnection();
			}

		}
	
	}
	
	//Commented  due to function appLogIDgenerator dated 05-16-2013
	
	//Method cannot be commented---In use for various modules-(Shruti)
	//uncommenting it 21/5/2013
	
   private String appLogIDgenerator() {
        String id = new CommonUtil().getAutoNumber();
        return id;
    }
    
	public boolean saveLogDet(String tableName,
			String actionType,String Status,String userId,String funId,String roleId)
	{
		boolean boo = false;
		 String logId=appLogIDgenerator();
		String logDet[] = new String[7];
		logDet[0] = tableName;
		logDet[1] = actionType;
		logDet[2] = Status;
		logDet[3] = userId;
		logDet[4] = "FUN_022";
		logDet[5] = roleId;
		logDet[6] =logId;
		
		System.out.println("saveLogDet==boo======="+tableName+"\t"+actionType+"\t"+Status+"\t"+userId+"\t"+funId+"\t"+roleId);
		
		String sql = CommonSQL.INSERT_LOG_DETAILS;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			boo = dbUtil.executeUpdate(logDet);
			if(boo)
				dbUtil.commit();
			else
				dbUtil.rollback();
			logger.info(":saveLogDet()sql=="+sql);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {

				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					
					System.out.println("Exception in IGRS common saveLogDet()"+e);
					e.printStackTrace();
				}
			}

		}
		return boo;
	}

	
	
	public boolean saveactivitylog(String userid,String activityid) throws Exception//aa
	{
		boolean boo = false;
		
		String sql3 = CommonSQL.ACT_SEQNC;
		DBUtility dbUtil=null;
		dbUtil = new DBUtility();
		dbUtil.createStatement();
		String sqncNumber  = dbUtil.executeQry(sql3);
		
		String logDet1[] = new String[1];
		logDet1[0] = activityid;
		
		String sql4= CommonSQL.GET_FUNCTN_ID;
		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(sql4);
		String functionid = dbUtil.executeQry(logDet1);
		
		
		 //String logId=appLogIDgenerator();
		String logDet[] = new String[4];
		logDet[0] = sqncNumber;
		logDet[1] = userid;
		logDet[2] = activityid;
		logDet[3] = functionid;
		//logDet[3] = date;
		
		String sql = CommonSQL.INSERT_ACT_LOG_DETAILS;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			boo = dbUtil.executeUpdate(logDet);
			if(boo)
				dbUtil.commit();
			else
				dbUtil.rollback();
			logger.info(":saveLogDet()sql=="+sql);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {

				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					
					System.out.println("Exception in IGRS common saveLogDet()"+e);
					e.printStackTrace();
				}
			}

		}
		return boo;
	}
	
	
	
	
	public boolean savetxnlog(String paymenttxnid,String userid,String activityid,String flag,String Amount) throws Exception
	{
		boolean boo = false;
		String functionid="";
		
		
		String logDet1[] = new String[1];
		logDet1[0] = activityid;
		
		String sql4= CommonSQL.GET_FUNCTN_ID;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			dbUtil.createPreparedStatement(sql4);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			functionid = dbUtil.executeQry(logDet1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql3 = CommonSQL.TXN_SEQNC;
		//dbUtil = new DBUtility();
		try {
			dbUtil.createStatement();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sqncNumber  = dbUtil.executeQry(sql3);
		 //String logId=appLogIDgenerator();
		String logDet[] = new String[6];
		logDet[0] = sqncNumber;
		logDet[1] = functionid;
		logDet[2] = paymenttxnid;
		logDet[3] = userid;
		logDet[4] = activityid;
		logDet[5] = flag;
		logDet[6]=Amount;
		
		String sql = CommonSQL.INSERT_TXN_LOG_DETAILS;
		try {
			//dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			boo = dbUtil.executeUpdate(logDet);
			if(boo)
			{
				dbUtil.commit(); 
				logger.debug("TXN DETAILS INSERTED SUCCESSFULLY");
			}
			else
			{
				dbUtil.rollback();
			logger.debug("TXN DETAILS NOT INSERTED SUCCESSFULLY");
			logger.info(":saveLogDet()sql=="+sql);
		}
		}catch (Exception e) {
			logger.error(e);
		}finally
		{
			dbUtil.closeConnection();
		}
		return boo;
	}
	
	
	
	
	
	
	
	
	
	    public String getPension(String empId, String typeRetir,String date) 
    {
//		if (logger.isDebugEnabled()) {
//			System.out.println("getStampDuty(String, String, String) - start");
//		}

		String pensionValue = null;
	//	System.out.println("Inside getStampDuty");
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			String sql = "call IGRS_EMP_RETIRE_PENSION_PROC(?,?,?,?,?)";
			clstmt = dbUtil
					.returnCallableStatement(sql);
			
			//Date dtTmp = new SimpleDateFormat("MM/dd/yy").parse(date);
			String dtTmp = getOracleDate(date);
			clstmt.setString(1, empId);
			clstmt.setString(2, typeRetir);
			clstmt.setString(3,dtTmp);
			clstmt.registerOutParameter(4, OracleTypes.NUMBER);
			clstmt.registerOutParameter(5, OracleTypes.VARCHAR);
			if (!clstmt.execute())
			{
				int temp = clstmt.getInt(4);
				pensionValue = String.valueOf(temp);
			}
		} catch (Exception e) {
			//System.out.println(e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		/*if (logger.isDebugEnabled()) {
			System.out.println("getStampDuty(String, String, String) - end");
		}*/
		return pensionValue;
	}
		
    
    
    	
    
    public String getGratuity(String empId, String date,String typeRetir) 
    {
//		if (logger.isDebugEnabled()) {
//			System.out.println("getStampDuty(String, String, String) - start");
//		}

		String gratuityVal = null;
	//	System.out.println("Inside getStampDuty");
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			String sql = "call IGRS_EMP_RETIRE_GRATUITY_PROC(?,?,?,?,?)";

			clstmt = dbUtil
					.returnCallableStatement(sql);
				
			clstmt.setString(1, empId);
			clstmt.setString(2, getOracleDate(date));
			clstmt.setString(3, typeRetir);
			clstmt.registerOutParameter(4, OracleTypes.NUMBER);
			clstmt.registerOutParameter(5, OracleTypes.VARCHAR);
			
			if (!clstmt.execute()) {
				int temp = clstmt.getInt(4);
				gratuityVal = String.valueOf(temp);
			}
		} catch (Exception e) {
			//System.out.println(e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		/*if (logger.isDebugEnabled()) {
			System.out.println("getStampDuty(String, String, String) - end");
		}*/
		return gratuityVal;
	}
    	
    
	
    
    public String getEarnLeave(String empId,String date) 
    {
//		if (logger.isDebugEnabled()) {
//			System.out.println("getStampDuty(String, String, String) - start");
//		}

		String earnLeave = null;
	//	System.out.println("Inside getStampDuty");
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			String sql = "call IGRS_EMP_RETIRE_EARNLEAVE_PROC(?,?,?,?,?)";
			clstmt = dbUtil
					.returnCallableStatement(sql);
			clstmt.setString(1, empId);
			clstmt.setString(2, getOracleDate(date));
			clstmt.registerOutParameter(3, OracleTypes.NUMBER);
			clstmt.registerOutParameter(4, OracleTypes.NUMBER);
			clstmt.registerOutParameter(5, OracleTypes.VARCHAR);
			
			if (!clstmt.execute()) {
				int  eanLea = clstmt.getInt(3);
				//int  eanAmt = clstmt.getInt(4);
				earnLeave = String.valueOf(eanLea);
		
			
				//earnLeave = Integer.toString(temp);
				
				
			}
		} catch (Exception e) {
			//System.out.println(e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		/*if (logger.isDebugEnabled()) {
			System.out.println("getStampDuty(String, String, String) - end");
		}*/
		return earnLeave;
	}
    
 
    
    
    
    
    public String getEarnAmt(String empId,String date) 
    {
//		if (logger.isDebugEnabled()) {
//			System.out.println("getStampDuty(String, String, String) - start");
//		}

		String earnAmt = null;
	//	System.out.println("Inside getStampDuty");
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			String sql = "call IGRS_EMP_RETIRE_EARNLEAVE_PROC(?,?,?,?,?)";
			clstmt = dbUtil
					.returnCallableStatement(sql);
			clstmt.setString(1, empId);
			clstmt.setString(2, getOracleDate(date));
			clstmt.registerOutParameter(3, OracleTypes.NUMBER);
			clstmt.registerOutParameter(4, OracleTypes.NUMBER);
			clstmt.registerOutParameter(5, OracleTypes.VARCHAR);
			
			if (!clstmt.execute()) {
				int  eanLea = clstmt.getInt(4);
				//int  eanAmt = clstmt.getInt(4);
				earnAmt = String.valueOf(eanLea);
			
			}
		} catch (Exception e) {
			//System.out.println(e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		/*if (logger.isDebugEnabled()) {
			System.out.println("getStampDuty(String, String, String) - end");
		}*/
		return earnAmt;
	}
    
    
    
    /**
	 * for change date format
	 * @param date
	 * @return String
	 */
	public static String getOracleDate(String date) {

		String finalDate = "";

		if(date!= null || !date.equalsIgnoreCase("") )

		{

		StringTokenizer st = new StringTokenizer(date,"/");

		String day = st.nextToken();

		String month = st.nextToken();

		String year = st.nextToken();

		String inputDate = day + "-" + month + "-" + year;

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date newDate ;
		try 
		{
			newDate = formatter.parse(inputDate);
			SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
			finalDate = format.format(newDate);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		}
		return finalDate;
	}
	
	
				
	public void batchJobCallProc() 
    {
//		if (logger.isDebugEnabled()) {
//			System.out.println("getStampDuty(String, String, String) - start");
//		}

		String earnAmt = null;
	//	System.out.println("Inside getStampDuty");
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			String sql = "call igrs_spot_inspection_alert()";
			clstmt = dbUtil
					.returnCallableStatement(sql);

			
			if (!clstmt.execute()) {
				int  eanLea = clstmt.getInt(4);
				//int  eanAmt = clstmt.getInt(4);
				earnAmt = String.valueOf(eanLea);
			
			}
		} catch (Exception e) {
			//System.out.println(e);
		} finally {
			if (dbUtil != null) {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	
	}


	

	
	public String captureUserDetails(String fname, String mname, String lname,
			String gender,String dob,Double baseValue, int district, int tehsil,int area, int ward,
			int mohalla,int munGovBody, int property, String propertyTypeL1Id,
			String propertyTypeL2Id, double marketValue,
			String sqmeterType, double totalSqlMeter, String noTrees,String UserId,String setSystemMVDisplay) {
		// TODO Auto-generated method stub
		String logDet[]=new String[21];

		DBUtility dbUtil=null;
		try {
			String sql=CommonSQL.NEXT_USER_ID;
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createStatement();
		   logDet[0]=dbUtil.executeQry(sql);
		   System.out.println("Id is "+logDet[0]);
			} catch (Exception e) {
			logger.error(e);
		    } finally {
			if (dbUtil != null) {

				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					
					System.out.println("Exception in IGRS common saving prop value details()"+e);
					e.printStackTrace();
				}
			}
		
		logDet[1]=fname;
		logDet[2]=mname;
		logDet[3]=lname;
		logDet[4]=gender;
		logDet[5]=dob;
		logDet[6]=Double.toString(baseValue);
		logDet[7]=Integer.toString(district);
		logDet[8]=Integer.toString(tehsil);
		logDet[9]=Integer.toString(area);
		logDet[10]=Integer.toString(ward);
		logDet[11]=Integer.toString(mohalla);
		logDet[12]=Integer.toString(munGovBody);
		logDet[13]=Integer.toString(property);
		logDet[14]=propertyTypeL1Id;
		logDet[15]=propertyTypeL2Id;
		logDet[16]=sqmeterType;
		logDet[17]=Double.toString(marketValue);
		logDet[18]=UserId;
		logDet[19]=Double.toString(totalSqlMeter);
		logDet[20]=setSystemMVDisplay;
		try {boolean boo;
			String sql=CommonSQL.INSERT_USER_DETAILS;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			boo = dbUtil.executeUpdate(logDet);
			System.out.println("after executing insert : "+boo);
			if(boo){
				dbUtil.commit();
			}
			else{
				logDet[0]=null;
				dbUtil.rollback();
			}
			logger.info(":saveLogDet()sql=="+sql);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {

				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					
					System.out.println("Exception in IGRS common saving prop value details()"+e);
					e.printStackTrace();
				}
			}

		}
			}

		    return  logDet[0];
	}	

	
	public String captureExchangeValuation(double marketValue) {
		// TODO Auto-generated method stub
		String logDet[]=new String[4];

		DBUtility dbUtil=null;
		try {
			String sql=CommonSQL.NEXT_USER_ID;
			dbUtil = new DBUtility();
			dbUtil.createStatement();
		   logDet[0]=dbUtil.executeQry(sql);
		   System.out.println("Id is "+logDet[0]);
			} catch (Exception e) {
			logger.error(e);
		    } finally {
			if (dbUtil != null) {

				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					
					System.out.println("Exception in IGRS common saving prop value details()"+e);
					e.printStackTrace();
				}
			}
		
		logDet[1]=Double.toString(marketValue);
		logDet[2]="Rishab";
		logDet[3]="Rishab";
	

		
		
		
		try {boolean boo;
			String sql=CommonSQL.INSERT_EXCHANGE_USER_DETAILS;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			boo = dbUtil.executeUpdate(logDet);
			System.out.println("after executing insert : "+boo);
			if(boo)
				dbUtil.commit();
			else
				dbUtil.rollback();
			logger.info(":saveLogDet()sql=="+sql);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {

				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					
					System.out.println("Exception in IGRS common saving prop value details()"+e);
					e.printStackTrace();
				}
			}

		}
			}

		    return  logDet[0];
	}	
	
	public String captureFloorDetails(String valuationId,int floorCalcId,String floorId,double baseValue,String propertyTypeL1Id,
			String propertyTypeL2Id, double marketValue,
			 double totalSqlMeter, String noTrees,double consSqlMeter,String userId,String setSystemMVDisplay) {
		// TODO Auto-generated method stub
	String logDet[]=new String[12];

	DBUtility dbUtil=null;
		try {
			String sql=CommonSQL.NEXT_USER_ID;
			dbUtil = new DBUtility();
			dbUtil.createStatement();
		   logDet[1]=dbUtil.executeQry(sql);
		   System.out.println("Id is "+logDet[0]);
			} catch (Exception e) {
			logger.error(e);
		    } finally {
			if (dbUtil != null) {

				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					
					System.out.println("Exception in IGRS common saving prop value details()"+e);
					e.printStackTrace();
				}
			}
		
		logDet[0]=	valuationId;
		logDet[2]=Integer.toString(floorCalcId);
		logDet[3]=Double.toString(baseValue);
		logDet[4]=propertyTypeL1Id;
		logDet[5]=propertyTypeL2Id;
		logDet[6]=Double.toString(marketValue);
		logDet[7]=Double.toString(totalSqlMeter);
		logDet[8]=Double.toString(consSqlMeter);
		logDet[9]=floorId;
		logDet[10]=userId;
		logDet[11]=setSystemMVDisplay;
		
		
		try {boolean boo;
			String sql=CommonSQL.INSERT_FLOOR_DETAILS;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			boo = dbUtil.executeUpdate(logDet);
			System.out.println("after executing insert : "+boo);
			if(boo)
				dbUtil.commit();
			else
				dbUtil.rollback();
			logger.info(":saveLogDet()sql=="+sql);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {

				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					
					System.out.println("Exception in IGRS common saving prop value details()"+e);
					e.printStackTrace();
				}
			}

		}
			}

		  return logDet[1]; 
	}	

	public String subClauseDetails(String valuationId,String subclause,String noOfTree,String floorId ) {
		// TODO Auto-generated method stub
	String logDet[]=new String[5];

	DBUtility dbUtil=null;
		try {
			String sql=CommonSQL.NEXT_USER_ID;
			dbUtil = new DBUtility();
			dbUtil.createStatement();
		   logDet[0]=dbUtil.executeQry(sql);
		   System.out.println("Id is "+logDet[0]);
			} catch (Exception e) {
			logger.error(e);
		    } finally {
			if (dbUtil != null) {

				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					
					System.out.println("Exception in IGRS common saving prop value details()"+e);
					e.printStackTrace();
				}
			}
			
			if(floorId=="0")
			{
				floorId=null;
				  System.out.println("floorId Idat 0 is  "+floorId);
			}
			
			
			if(floorId.equals("0"))
			{
				floorId=null;
				  System.out.println("floorId Id at equals is  "+floorId);
			}
		logDet[1]=	valuationId;
		logDet[2]=floorId;
		logDet[3]=subclause;
		logDet[4]=noOfTree;
		
		  System.out.println("floorId Id is "+floorId);
		
		
		
		try {boolean boo;
			String sql=CommonSQL.INSERT_SUBCLAUSE_DETAILS;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			boo = dbUtil.executeUpdate(logDet);
			System.out.println("after executing insert : "+boo);
			if(boo)
				dbUtil.commit();
			else
				dbUtil.rollback();
			logger.info(":saveLogDet()sql=="+sql);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {

				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					
					System.out.println("Exception in IGRS common saving prop value details()"+e);
					e.printStackTrace();
				}
			}

		}
			}

		  return valuationId; 
	}	
	
	public String captureStampDetails(String valuationId,double stampDuty,double gramDuty,double nagarDuty,double upkar,double regFee,double marketValue,double total,int deedId,int instId,
			String exempId,double regPaid,double dutyPaid,double  shareValue,String userId) {
		// TODO Auto-generated method stub
		String logDet[]=new String[15];
		String exemdet[]=new String[2];

		DBUtility dbUtil=null;
		try {
			String sql=CommonSQL.NEXT_USER_ID;
			dbUtil = new DBUtility();
			dbUtil.createStatement();
		   logDet[1]=dbUtil.executeQry(sql);
		   System.out.println("Id is "+logDet[0]);
			} catch (Exception e) {
			logger.error(e);
		    } finally {
			if (dbUtil != null) {

				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					
					System.out.println("Exception in IGRS common saving prop value details()"+e);
					e.printStackTrace();
				}
			}
		
		logDet[0]=valuationId;
		logDet[2]=Double.toString(stampDuty);
		logDet[3]=Double.toString(gramDuty);
		logDet[4]=Double.toString(nagarDuty);
		logDet[5]=Double.toString(upkar);
		logDet[6]=Double.toString(regFee);
		logDet[7]=Double.toString(total);
		logDet[8]=Double.toString(marketValue);
		logDet[9]=Integer.toString(deedId);
		logDet[10]=Integer.toString(instId);
		logDet[11]=Double.toString(regPaid);
		logDet[12]=Double.toString(dutyPaid);
		logDet[13]=Double.toString(shareValue);
		logDet[14]=userId;
		
		
		try {boolean boo;
			String sql=CommonSQL.IGRS_PROP_STAMP_DUTY_DETAILS;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			boo = dbUtil.executeUpdate(logDet);
			System.out.println("after executing insert : "+boo);
			System.out.println("exemption id is "+exempId);
			String sql2=CommonSQL.IGRS_PROP_EXEMPTION_DETAILS;
			if(exempId!=""){
			String exemp[]=exempId.split(",");
			dbUtil.createPreparedStatement(sql2);
			for(int i=0;i<exemp.length;i++){
				exemdet[0]=logDet[1];
				exemdet[1]=exemp[i];
			boo = dbUtil.executeUpdate(exemdet);
			}
			}
			System.out.println("after executing insert : "+boo);
			
			if(boo)
				dbUtil.commit();
			else
				dbUtil.rollback();
			logger.info(":saveLogDet()sql=="+sql);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {

				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					
					System.out.println("Exception in IGRS common saving prop value details()"+e);
					e.printStackTrace();
				}
			}

		}
			}

		    return  logDet[0];
	}	

	
	public String captureNonPropStampDetails(String firstName,String middleName,String lastName,String dateofbirth,double stampDuty,double gramDuty,double nagarDuty,double upkar,double regFee,double marketValue,double annualRent,double total,int deedId,int instId,
			String exempId,double regPaid,double dutyPaid,String userId) {
		// TODO Auto-generated method stub
		String logDet[]=new String[13];
		String exemdet[]=new String[3];
		String logD[]=new String[8];

		DBUtility dbUtil=null;
		try {
			String sql=CommonSQL.NEXT_USER_ID;
			dbUtil = new DBUtility();
			dbUtil.createStatement();
		   logDet[0]=dbUtil.executeQry(sql);
		   System.out.println("Id is "+logDet[0]);
			} catch (Exception e) {
			logger.error(e);
		    } finally {
			if (dbUtil != null) {

				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					
					System.out.println("Exception in IGRS common saving prop value details()"+e);
					e.printStackTrace();
				}
			}
	
		logDet[1]=Double.toString(stampDuty);
		logDet[2]=Double.toString(gramDuty);
		logDet[3]=Double.toString(nagarDuty);
		logDet[4]=Double.toString(upkar);
		logDet[5]=Double.toString(regFee);
		logDet[6]=Double.toString(total);
	//	logDet[7]=Double.toString(marketValue);
		logDet[7]=Integer.toString(deedId);
		logDet[8]=Integer.toString(instId);
		logDet[9]=null;
		logDet[10]=Double.toString(regPaid);
		logDet[11]=Double.toString(dutyPaid);
		logDet[12]=userId;


		try {
			String sql=CommonSQL.NEXT_USER_ID;
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			logD[0]=dbUtil.executeQry(sql);
		   System.out.println("Id is "+logDet[0]);
			} catch (Exception e) {
			logger.error(e);
		    } finally {
			if (dbUtil != null) {

				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					
					System.out.println("Exception in IGRS common saving prop value details()"+e);
					e.printStackTrace();
				}
			}
		
			logD[1]=logDet[0];
			logD[2]=firstName;
			logD[3]=middleName;
			logD[4]=lastName;
			logD[5]=(dateofbirth);
			logD[6]=Double.toString(marketValue);
			logD[7]=userId;
		try {boolean boo;
			String sql=CommonSQL.IGRS_STAMP_DUTY_DETAILS;
			String sql1=CommonSQL.IGRS_NEW_STAMP_DUTY_DETAILS;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			boo = dbUtil.executeUpdate(logDet);
			System.out.println("after executing insert : "+boo);
		
			dbUtil.createPreparedStatement(sql1);
			boo = dbUtil.executeUpdate(logD);
			System.out.println("after executing insert : "+boo);
			
			String sql2=CommonSQL.IGRS_EXEMPTION_DETAILS;
			if(exempId!=""){
			String exemp[]=exempId.split(",");
			for(int i=0;i<exemp.length;i++){
				exemdet[1]=logDet[0];
				System.out.println("exemdet[1]"+exemdet[1]);
				exemdet[2]=exemp[i];
				String sql3=CommonSQL.NEXT_USER_ID;
				dbUtil = new DBUtility();
				dbUtil.createStatement();
				exemdet[0]=dbUtil.executeQry(sql3);
				dbUtil.createPreparedStatement(sql2);
			boo = dbUtil.executeUpdate(exemdet);
			}
			}
			System.out.println("after executing insert : "+boo);
			
			if(boo)
				dbUtil.commit();
			else
				dbUtil.rollback();
			logger.info(":saveLogDet()sql=="+sql);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {

				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					
					System.out.println("Exception in IGRS common saving prop value details()"+e);
					e.printStackTrace();
				}
			}

		}
			}
		    }
		    return  logDet[0];
	}	


	
	
	
	public String getcurrYear()  {
			
			String curryear = "";
			DBUtility dbUtil=null;
			try {
				dbUtil = new DBUtility();
				dbUtil.createStatement();
				curryear=	dbUtil.executeQry(CommonSQL.CURRENT_YEAR);
			}catch (Exception exception) {
				logger.info("Exception in getSPusers-SPDAo" + exception);
			}
			return curryear;
		}
	
	public String getSpotMaxDay()  {
		
		String getSpotMaxDay = "";
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			getSpotMaxDay=	dbUtil.executeQry( "SELECT  ATTRIBUTE_VALUE  FROM IGRS_CONFIG_PARAM_LIST WHERE ATTRIBUTE_ID = 'ATT_160'");
		}catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		}
		return getSpotMaxDay;
	}

	/**
	 * 
	 * @param option
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getDeedTypeOp(String option) throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getDeedType(String) - start");
		}
		String SQL =null;
		if (option.equals("P") ) {
			SQL = CommonSQL.DEED_QUERY_PROPERTY;
		}
		else if (option.equals("R") )
		{
			SQL = CommonSQL.DEED_QUERY_REGISTRATION;
		}
		else if (option.equals("NP") )
		{
			SQL = CommonSQL.DEED_QUERY_REGISTRATION_NON_PROPERTY_OP;
		}
		else
		{
			 SQL = CommonSQL.DEED_QUERY_OP;
		}
		ArrayList returnArrayList = new ArrayList();
		DBUtility dbUtil=null;
		try {

			dbUtil = new DBUtility();
			dbUtil.createStatement();
			returnArrayList = dbUtil.executeQuery(SQL);
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		if (logger.isDebugEnabled()) {
			System.out.println("getDeedType(String) - end");
		}
		return returnArrayList;

	}
	/**
	 * @param String[]
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getInstrumentsOp(int[] deedType)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getInstruments(String[]) - start");
		}
		ArrayList instrument = new ArrayList();
		DBUtility dbUtil=null;
		try {

			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.INSTRUMENTS_QUERY_OP);
			logger.debug("Inside getInstruments");
			instrument = dbUtil.executeQuery(deedType);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getInstruments(String[]) - end");
		}
		return instrument;
	}	
	
	
	
	public synchronized ArrayList getSroList(String distId,String language) throws Exception {
		String arry[] = new String[1];
		logger.debug("distId ID:-" + distId);

		ArrayList list = new ArrayList();
		if (distId != null) {
			arry[0] = distId;
		}
		DBUtility dbUtil=null;
		try {
			logger.debug("Before initialize DBUtility");
			dbUtil = new DBUtility();
			if("en".equalsIgnoreCase(language))
			{
			dbUtil.createPreparedStatement(CommonSQL.SRO_QUERY);
			}
			else
			{
				dbUtil.createPreparedStatement(CommonSQL.SRO_QUERY_H);
			}
			list = dbUtil.executeQuery(arry);
			logger.debug("After initialize DBUtility");
			if (logger.isDebugEnabled()) {
				logger.debug("getSroList(String) - end");
			}
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return list;
	}
	
	/**
	 
	 * @return ArrayList
	 * @throws Exception
	 */

public synchronized String getExemptionsLevel(int deedType) throws Exception {
		String[] arry =new String[1];
		arry[0]=(Integer.toString(deedType));		
		String str1="";		
		DBUtility dbUtil=null;
		try {
			logger.debug("Before initialize DBUtility");
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GET_EXEMPTION_LEVEL);
			str1 = dbUtil.executeQry(arry);
			
		
			logger.debug("After initialize DBUtility");
		//	str2= Integer.parseInt(str1.get(0).toString());
			logger.debug("@@@@@@ string value@@@@@@"+str1);
			if (logger.isDebugEnabled()) {
				logger.debug("getExemptionsLevel(String) - end");
			}
		} catch (Exception x) {
			//logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return str1;
	}
public synchronized ArrayList getDeedCategoryList(String langauge) throws Exception {
	logger.debug("Start of getDeedCategoryList(String langauge)");
	String SQL =null;
	if("en".equalsIgnoreCase(langauge))
	{
		SQL= CommonSQL.DEED_QUERY_DEEDCATEGORY_COMMON;
	}
	else
	{
		SQL= CommonSQL.DEED_QUERY_DEEDCATEGORY_COMMON_HINDI;
	}
	ArrayList returnArrayList = new ArrayList();
	DBUtility dbUtil=null;
	try {

		dbUtil = new DBUtility();
		dbUtil.createStatement();
		returnArrayList = dbUtil.executeQuery(SQL);
	} catch (Exception x) {
		System.out.println(x);
	} finally {
		if (dbUtil != null) {
			dbUtil.closeConnection();
		}
	}

	if (logger.isDebugEnabled()) {
		System.out.println("getDeedType(String) - end");
	}
	return returnArrayList;

}

//added by shruti---7 july 2014
public String checkYearsOfDoc(String fromDate,String toDate) {
	String value="";
	DBUtility dbUtil=null;
	try {
		dbUtil = new DBUtility();
		 
		SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
		Date fdate=new Date(fromDate);
		Date tdate=new Date(toDate);
        String currentYear = formatYear.format(tdate);
        String previousYear = formatYear.format(fdate);
        int y1=Integer.parseInt(currentYear);
        int y2=Integer.parseInt(previousYear);
        int diff=(y1-y2)+1;
        
        value= String.valueOf(diff);
        
	   
	} catch (Exception e) {
		System.out.println(e);
		e.printStackTrace();
	} finally {
		if (dbUtil != null) {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	return value;
}

//Added By Mohit Soni for Captcha
public static boolean validateCaptcha(String uniqueIdentifier, String responses)
{
	
	PropertiesFileReader prObj = null;
	String captchaTxt = "";
		try
	{
			prObj = PropertiesFileReader.getInstance("resources.igrs");
			AESEncrypt crypt = new AESEncrypt();
			String keyPath=prObj.getValue("mp_treasury_key");
			
			crypt.setSecretKey(keyPath);
			
			String id = crypt.encryptFile(uniqueIdentifier);
			
			 captchaTxt = id.substring(4, 9);
			if(responses.equals(captchaTxt))
			{
				return true;
			}
			else
			{
				return true;
			}
			
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return true;
	}
	
}

	public static boolean checkXSSData(Object obj){
		boolean retVal = false;
		if(obj != null){
			try {	
				retVal = checkXSS(obj, new ArrayList());
			} catch (Exception e) {
				//logger.error("Error message :- "+e.getMessage());
			}
		}
		return retVal;
	}
	
	private static boolean checkXSS(Object obj, ArrayList checkCycData) throws Exception {
		
		if(checkCycData.contains(obj)){
			throw new Exception("Cyclic dependency found ..");
		}else {
			checkCycData.add(obj);
		}
		
		Field[] privateFields = obj.getClass().getDeclaredFields();
		for (Field field : privateFields) {
			field.setAccessible(true);
			Object value = field.get(obj);
			if (value !=null ) {
				if(value.toString().matches(xssDataCheck)){
				  xssFieldName = field.getName();
			      return true;
				}
				if(value instanceof List){
					for (Object object : (List)value) {
						if(!object.getClass().getName().contains("java."))
							if(checkXSS(object,checkCycData))
								return true;	
					}
				} else if(!value.getClass().getName().contains("java."))
					if(checkXSS(value,checkCycData))
						return true;
		    }
		}
		
		return false;
	}

}

		
