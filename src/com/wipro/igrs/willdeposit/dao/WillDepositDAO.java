/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   WillDepositDAO.java
 * Author      :   Madan Mohan Mohanty
 * Description :   Represents the DAO Class for User Registration.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Nihar R Mishra       28th Dec, 2007	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.willdeposit.dao;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import oracle.jdbc.OracleResultSet;
import oracle.sql.BLOB;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.db.DataSourceLookUp;
import com.wipro.igrs.userrolegroupmapping.sql.UserRGroupMappingCommonSQL;
import com.wipro.igrs.util.PropertiesFileReader;
import com.wipro.igrs.willdeposit.bd.WillCommonBD;
import com.wipro.igrs.willdeposit.constant.CommonConstant;
import com.wipro.igrs.willdeposit.dto.WillDepositDTO;
import com.wipro.igrs.willdeposit.sql.CommonSQL;

/**
 * @author NIHAR M.
 * 
 */
public class WillDepositDAO {

	//DBUtility dbUtil;
	private Logger logger = (Logger) Logger.getLogger(WillDepositDAO.class);
	//IGRSCommon igrsCommon = null;
	//Connection con = null;

	/**
	 * @throws Exception
	 */
	public WillDepositDAO() throws Exception {

	}

	/**
	 * @return ArrayList
	 */
	public ArrayList getCountry() {
		ArrayList list = null;
		IGRSCommon igrsCommon;
		try {
			igrsCommon = new IGRSCommon();
			list = igrsCommon.getCountry();
		} catch (Exception e) {
			logger.debug("Exception in getCountry():" + e);
		}
		return list;
	}

	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getWillFeeValue(String StrFunId) throws Exception {

		ArrayList list = new ArrayList();
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement("SELECT IMAP.FEE_DR FROM IGRS_FUNCTION_SERVICE_MAPPING IMAP WHERE  FUNCTION_ID   =?");
			String[] param = new String[1];
			param[0] = StrFunId;
			list = dbUtil.executeQuery(param);
			logger.debug(" Fee List size: " + list.size());

		} catch (Exception e) {
			logger.debug("Error::    " + e.getMessage());
		}finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * for calculating othersFeeDuty
	 * 
	 * @param _refFunId
	 * @throws Exception
	 * @return othersFeeDuty
	 */
	public String getWillOtherFeeValue(String _refFunId, String serviceId, String userType)
			throws Exception {
		ArrayList othersFeeDuty = null;
		IGRSCommon igrsCommon;
		try {
			// dbUtil = new DBUtility();
			igrsCommon = new IGRSCommon();
			othersFeeDuty = igrsCommon
					.getOthersFeeDuty(_refFunId, serviceId, userType);// dbUtil.getWillOtherFeeValue(_refFunId);
		} catch (Exception e) {
			logger.debug("Error:-      " + e.getMessage());
		}
		return (String) othersFeeDuty.get(2);

	}

	/**
	 * @param countryId
	 * @return ArrayList
	 */
	public ArrayList getState(String countryId) {
		ArrayList list = null;
		IGRSCommon igrsCommon;
		try {
			/*
			 * if (countryId == null) { countryId = "INDIA"; }
			 */
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
	public ArrayList getDistrict(String stateId) {

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
	public ArrayList getType(String type) {
		ArrayList list = null;
		IGRSCommon igrsCommon;
		try {
			igrsCommon = new IGRSCommon();
			list = igrsCommon.getCommon(type);
		} catch (Exception e) {
			logger.debug("Exception in getCommon():" + e);
		}
		return list;
	}

	/**
	 * @return ArrayList
	 */
	public ArrayList getFee() {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String fee = dbUtil.executeQry(CommonSQL.REQUISITE_FEE_QUERY);
			String otherfee = dbUtil
					.executeQry(CommonSQL.REQUISITE_OTHER_FEE_QUERY);
			list = new ArrayList();
			list.add(fee);
			list.add(otherfee);
		} catch (Exception x) {
			logger.debug("Exception in getFee():" + x);
		}finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @param depositparam
	 * @param agentparam
	 * @return boolean
	 */
	public boolean insertWillDeposit(String[] depositparam, String[] agentparam) {
		boolean willDeposit = false;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.WILL_DEPOSIT_INSERT);
			willDeposit = dbUtil.executeUpdate(depositparam);
			if (willDeposit) {
				if (agentparam != null) {
					if (agentparam.length > 0) {
						dbUtil
								.createPreparedStatement(CommonSQL.WILL_DEPOST_AGENT_INSERT);
						willDeposit = dbUtil.executeUpdate(agentparam);
					}
				}
			}
			dbUtil.commit();

		} catch (Exception x) {
			logger.debug("Exception in insertWillDeposit() :- " + x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in insertWillDeposit() :-" + ex);
			}
		}
		return willDeposit;
	}

	/**
	 * @param param
	 * @return boolean
	 */
	public boolean insertWillWithDrawal(String[] param) {
		boolean willDrawal = false;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.WILL_WITHDRAWAL_INSERT);
			willDrawal = dbUtil.executeUpdate(param);
			dbUtil.commit();
		} catch (Exception x) {
			logger.debug("Exception in insertWillDeposit() :- " + x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in insertWillDeposit() :-" + ex);
			}
		}
		return willDrawal;
	}

	// Challan and Payment
	/**
	 * @param param
	 * @return boolean
	 */
	public boolean insertChallanPayment(String[] param) {
		boolean chalanPayment = false;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil
					.createPreparedStatement(CommonSQL.WILL_CHALLAN_PAYMENT_INSERT);
			chalanPayment = dbUtil.executeUpdate(param);
			dbUtil.commit();
		} catch (Exception x) {
			logger.debug("Exception in insertChallanPayment() :- " + x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in insertWillDeposit() :-" + ex);
			}
		}
		return chalanPayment;
	}

	/**
	 * @param param
	 * @return boolean
	 */
	public boolean insertCashPayment(String[] param) {
		boolean cashPayment = false;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.WILL_CASH_PAYMENT_INSERT);
			cashPayment = dbUtil.executeUpdate(param);
			dbUtil.commit();
		} catch (Exception x) {
			x.printStackTrace();
			logger.debug("Exception in insertChallanPayment() :- " + x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in insertWillDeposit() :-" + ex);
			}
		}
		return cashPayment;
	}

	/**
	 * @param willid
	 * @return ArrayList
	 */
	public ArrayList getWillDeposit(String[] willid) {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.WILL_DEPOSIT_QUERY);
			list = dbUtil.executeQuery(willid);
		} catch (Exception x) {
			logger.debug("Exception in getWillDeposit() :- " + x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in insertWillDeposit() :-" + ex);
			}
		}
		return list;
	}

	/**
	 * @param willParamId
	 * @return ArrayList
	 */
	public ArrayList getwillIdDetails(String willParamId) { // VIEW 2nd
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.WILL_ID_DETAILS_QUERY);

			String[] param = new String[1];
			param[0] = willParamId;
			list = dbUtil.executeQuery(param);
		} catch (Exception x) {
			logger.debug("Exception in getWillDeposit() :- " + x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in insertWillDeposit() :-" + ex);
			}
		}
		return list;
	}

	/**
	 * @param willId
	 * @param rType
	 * @param deposit
	 * @return boolean
	 */
	public boolean insertRetrieve(String[] willId, String rType,
			String[] deposit) {
		boolean willDrawal = false;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			if (CommonConstant.WILL_RETRIEVE_REP_FLAG.equals(rType)) {
				dbUtil
						.createPreparedStatement(CommonSQL.WILL_RETRIEVE_CITIZEN_INSERT);
				willDrawal = dbUtil.executeUpdate(willId);
			} else if (CommonConstant.WILL_RETRIEVE_COURT_FLAG.equals(rType)) {
				dbUtil
						.createPreparedStatement(CommonSQL.WILL_RETRIEVE_COURT_INSERT);
				willDrawal = dbUtil.executeUpdate(willId);
			}
			if (willDrawal) {
				dbUtil.createPreparedStatement(CommonSQL.WILL_RETRIEVE_UPDATE);
				willDrawal = dbUtil.executeUpdate(deposit);
			}
			dbUtil.commit();
		} catch (Exception x) {
			logger.debug("Exception in insertRetrieve() :- " + x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in insertWillDeposit() :-" + ex);
			}
		}
		return willDrawal;
	}

	/**
	 * @param willId
	 * @param testator
	 * @param fromDate
	 * @param toDate
	 * @return ArrayList
	 */
	public ArrayList getWillRetrieveSearch(String willId, String testator,
			String fromDate, String toDate) {
		String sql = CommonSQL.WILL_RETRIEVE_QUERY;

		if (willId != null || testator != null || fromDate != null
				|| toDate != null) {
			if (willId != null && !"".equals(willId.trim())) {
				sql += " AND WILL_ID='" + willId + "'";
			}
			if (testator != null && !"".equals(testator.trim())) {
				sql += " AND UPPER(FIRST_NAME) LIKE UPPER('%" + testator + "%'"
						+ ")";
			}
			if ((fromDate != null && toDate != null)
					&& (!"".equals(fromDate.trim()) && !""
							.equals(toDate.trim()))) {
				sql += " AND TO_CHAR(DEPOSIT_DATE,'dd/mm/yy') BETWEEN TO_CHAR"
						+ "(TO_DATE('" + fromDate
						+ "','mm/dd/yy')) AND TO_CHAR(TO_DATE('" + toDate
						+ "','mm/dd/yy'))";
			}
		}
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(sql);
		} catch (Exception x) {
			logger.debug("Exception in getWillDeposit() :- " + x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in insertWillDeposit() :-" + ex);
			}
		}
		return list;
	}

	/**
	 * @param willId
	 * @param status
	 * @param fromDate
	 * @param toDate
	 * @return ArrayList
	 */
	public ArrayList getWillViewDetails(String willId, String fromDate,
			String toDate, String status) { // VIEW 1

		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.WILL_VIEW_QUERY);
			String param[] = new String[4];
			param[0] = willId;
			param[1] = fromDate;
			param[2] = toDate;
			param[3] = status;

			list = dbUtil.executeQuery(param);

		} catch (Exception x) {
			x.getCause();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in insertWillDeposit() :-" + ex);
			}
		}
		return list;
	}

	/**
	 * @param willId
	 * @param fromDate
	 * @param toDate
	 * @return ArrayList
	 */
	public ArrayList getWillWIthdrawalDetails(String willId, String fromDate,
			String toDate) {
		String sql = CommonSQL.WILL_WITHDRAWAL_VIEW_QUERY;

		if (willId != null || fromDate != null || toDate != null) {
			if (willId != null && !"".equals(willId.trim())) {
				sql += " AND WILL_ID='" + willId + "'";
			}
			if ((fromDate != null && toDate != null)
					&& (!"".equals(fromDate.trim()) && !""
							.equals(toDate.trim()))) {
				sql += " AND TO_CHAR(DEPOSIT_DATE,'dd/mm/yy') BETWEEN TO_CHAR"
						+ "(TO_DATE('" + fromDate
						+ "','mm/dd/yy')) AND TO_CHAR(TO_DATE('" + toDate
						+ "','mm/dd/yy'))";
			}
		}
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(sql);
		} catch (Exception x) {
			logger.debug("Exception in getWillDeposit() :- " + x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in insertWillDeposit() :-" + ex);
			}
		}
		return list;
	}

	/**
	 * @param willId
	 * @param rType
	 * @param deposit
	 * @return boolean
	 */
	public boolean insertDepositionDetails(String[] retDetails, String rType,
			WillDepositDTO wDTO, String willId, String lang) {

		boolean willDrawal = false;
		PreparedStatement pst = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			if (CommonConstant.WILL_AGENT.equals(rType)) {
				dbUtil
						.createPreparedStatement(CommonSQL.WILL_DEPOSITE_AGENT_INSERT);
				willDrawal = dbUtil.executeUpdate(retDetails);

				if (willDrawal) {
					insertTestatorDetails(wDTO, willId, lang);
				}

			} else if (CommonConstant.WILL_TESTATOR.equals(rType)) {

				dbUtil
						.createPreparedStatement(CommonSQL.WILL_DEPOSITE_TESTATOR_INSERT);
				willDrawal = dbUtil.executeUpdate(retDetails);
			}
			dbUtil.commit();

		} catch (Exception x) {
			logger.debug("Exception in insertDepositionDetails() :- " + x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in insertWillDeposit() :-" + ex);
			}
		}
		return willDrawal;
	}

	/**
	 * @param willDepDTO
	 * @return boolean
	 */
	public boolean insertTestatorDetails(WillDepositDTO willDepDTO,
			String willId, String lang) {
		DBUtility dbUtil=null;
		boolean mohallaInserted = false;
		try {
			dbUtil = new DBUtility();
			dbUtil
					.createPreparedStatement(CommonSQL.WILL_DEPOSITE_TESTATOR_INSERT);

			String[] param = new String[22];
			param[0] = "TESTATOR";
			param[1] = willDepDTO.getFirstName();
			param[2] = willDepDTO.getMidName();
			param[3] = willDepDTO.getLastName();
			param[4] = willDepDTO.getGender();
			if ("M".equalsIgnoreCase(willDepDTO.getGender())) {
				if ("hi".equalsIgnoreCase(lang)) {
					willDepDTO.setGender("पुस्र्ष");
				} else {
					willDepDTO.setGender("Male");
				}
			} else {
				if ("hi".equalsIgnoreCase(lang)) {
					willDepDTO.setGender("महिला");
				} else {
					willDepDTO.setGender("Female");
				}
			}
			param[5] = willDepDTO.getAge();
			param[6] = willDepDTO.getFatherName();
			param[7] = willDepDTO.getMotherName();
			param[8] = willDepDTO.getSpouseName();
			param[9] = willDepDTO.getAddress();
			param[10] = willDepDTO.getCountry();
			param[11] = willDepDTO.getState();
			param[12] = willDepDTO.getCity();
			param[13] = willDepDTO.getPin();
			param[14] = willDepDTO.getPhone();
			param[15] = willDepDTO.getMphone();
			param[16] = willDepDTO.getEmail();
			param[17] = willDepDTO.getIdProof();
			WillCommonBD bdd = new WillCommonBD();
			willDepDTO.setIdProof(bdd.getIdType(willDepDTO.getIdProof(),lang));
			param[18] = willDepDTO.getIdProofNo();
			param[19] = willDepDTO.getBankName();
			param[20] = willDepDTO.getBankAddress();
			param[21] = willId;

			mohallaInserted = dbUtil.executeUpdate(param);
		} catch (Exception x) {
			logger.debug("" + x.getMessage());
		} finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		finally {
//			try {
//				dbUtil.closeConnection();
//			} catch (Exception ex) {
//				logger.debug("Exception in insertWillDeposit() :-" + ex);
//			}
//		}

		return mohallaInserted;
	}

	public String insertWillDetails(String status, String userId, String willId, String remarks, String funId, float total) {
		boolean insert = false;
		String k="";
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.WILL_DEPOSITION_INSERT);
			String[] param = new String[5];
			param[0] = willId;
			param[1] = status;
			param[2] = "DI";
			param[3] = userId;
			param[4] = remarks;

			insert = dbUtil.executeUpdate(param);
			/*if (insert)
				//ANUJ
				System.out.println("entered the area");
			dbUtil.createStatement();
			ArrayList alist=dbUtil.executeQuery("SELECT IGRS_WILL_PAYMENT_SEQ.NEXTVAL FROM DUAL");
		
			System.out.println("returned");
			ArrayList s=(ArrayList)alist.get(0);
			k=(String)s.get(0);
			System.out.println("value of s= "+k);
		//for next query
			dbUtil.createPreparedStatement("INSERT INTO IGRS_WILL_TXN_PAYMENT_DTLS (WILL_PAYMENT_ID, WILL_TXN_ID,  FUNCTION_ID,  PAYABLE_AMOUNT, PAID_AMOUNT, PAYMENT_FLAG, CREATED_BY, CREATED_DATE) VALUES (?, ?, ?, ?, ?, ?, ?, SYSDATE)");
			Float f=total;
			String s1=f.toString();
			System.out.println("value of s1 "+s1);
			String param1[]=new String[7];
			param1[0]=k;
			param1[1]=willId;
			param1[2]=funId;
			param1[3]=s1;
			param1[4]="";
			param1[5]="I";
			param1[6]=userId;
			//param[6] is SYSDATE
			dbUtil.executeUpdate(param1);*/
			dbUtil.commit();
				//ANUJ
				dbUtil.commit();
		} catch (Exception x) {
			logger.debug("" + x.getMessage());
			try {
				dbUtil.rollback();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in insertWillDeposit() :-" + ex);
			}
		}
		return k;

	}

	public ArrayList getWillRemarks(String[] willid) {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.RETRIVE_WILL_REMARKS);
			list = dbUtil.executeQuery(willid);
		} catch (Exception x) {
			logger.debug("Exception in getWillDeposit() :- " + x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in insertWillDeposit() :-" + ex);
			}
		}
		return list;
	}

	public boolean insertPhotoDetails(WillDepositDTO wDTO, String willId) {
		boolean check = true;
		String sqlText = null;
		BLOB image1 = null;
		BLOB image2 = null;
		BLOB image3 = null;
		int chunkSize;
		byte[] binaryBuffer;
		long position;
		int bytesRead = 0;
		int bytesWritten = 0;
		int totbytesRead = 0;
		int totbytesWritten = 0;
		File file1 = null;
		File file2 = null;
		File file3 = null;
		ResultSet rst = null;
		Statement st = null;
		Connection con = null;
		boolean willDrawal = true;
		DBUtility dbUtil=null;
		try {
			file1 = new File(wDTO.getFilePath() + wDTO.getFilePhotoName());
			InputStream ioFile1 = new FileInputStream(file1);

			file2 = new File(wDTO.getFilePath() + wDTO.getFileThumbName());
			InputStream ioFile2 = new FileInputStream(file2);

			file3 = new File(wDTO.getFilePath() + wDTO.getFileSignatureName());
			InputStream ioFile3 = new FileInputStream(file3);
//TODO preparedStatement
			sqlText = "SELECT PARTY_THUMB_IMPRESSION, PARTY_PHOTO, PARTY_SIGNATURE FROM IGRS_WILL_TXN_PARTY "
					+ "WHERE WILL_TXN_ID='"
					+ willId
					+ "' AND PARTY_TYPE_ID='TESTATOR' FOR UPDATE";

			dbUtil = new DBUtility();
			con = dbUtil.getDBConnection();
			st = con.createStatement();
			rst = st.executeQuery(sqlText);
			rst.next();
			image1 = ((OracleResultSet) rst).getBLOB("PARTY_THUMB_IMPRESSION");
			image2 = ((OracleResultSet) rst).getBLOB("PARTY_PHOTO");
			image3 = ((OracleResultSet) rst).getBLOB("PARTY_SIGNATURE");

			chunkSize = image1.getChunkSize();
			binaryBuffer = new byte[chunkSize];
			// image3.
			position = 1;
			while ((bytesRead = ioFile1.read(binaryBuffer)) != -1) {
				bytesWritten = image1.putBytes(position, binaryBuffer,
						bytesRead);
				position += bytesRead;
				totbytesRead += bytesRead;
				totbytesWritten += bytesWritten;
			}
			chunkSize = image2.getChunkSize();
			binaryBuffer = new byte[chunkSize];
			position = 1;
			while ((bytesRead = ioFile2.read(binaryBuffer)) != -1) {
				bytesWritten = image2.putBytes(position, binaryBuffer,
						bytesRead);
				position += bytesRead;
				totbytesRead += bytesRead;
				totbytesWritten += bytesWritten;
			}
			chunkSize = image3.getChunkSize();
			binaryBuffer = new byte[chunkSize];

			position = 1;
			while ((bytesRead = ioFile3.read(binaryBuffer)) != -1) {
				bytesWritten = image3.putBytes(position, binaryBuffer,
						bytesRead);
				position += bytesRead;
				totbytesRead += bytesRead;
				totbytesWritten += bytesWritten;
			}

			ioFile1.close();
			ioFile2.close();
			ioFile3.close();
			dbUtil.commit();
			logger
					.debug("==========================================================\n"
							+ "  PUT METHOD\n"
							+ "==========================================================\n"
							+ "Wrote file  to BLOB column.\n"
							+ totbytesRead
							+ " bytes read.\n"
							+ totbytesWritten
							+ " bytes written.\n");
			file1.delete();
			file2.delete();
			file3.delete();
		} catch (Exception ee) {
			willDrawal = false;
			logger.debug("Error in inserting images--> " + ee.getMessage());
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return willDrawal;
	}

	public String readBLOBToFileGet(HttpServletResponse res, String willId,
			String type) throws IOException, SQLException {
		ResultSet rst = null;
		Statement st = null;
		Connection con = null;
		BLOB image = null;
		OutputStream os = null;
		String ext = "";
		String sqlText = "";
		String docObject = "";
		String returnStr = "success";
		DBUtility dbUtil=null;
		try {
			try {
				dbUtil = new DBUtility();
				con = dbUtil.getDBConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug(e);
			}
			st = con.createStatement();
			if (type.equalsIgnoreCase("photo"))
				docObject = "PARTY_PHOTO";
			else if (type.equalsIgnoreCase("thumb"))
				docObject = "PARTY_THUMB_IMPRESSION";
			else if (type.equalsIgnoreCase("signature"))
				docObject = "PARTY_SIGNATURE";

			sqlText = "SELECT " + docObject
					+ " FROM IGRS_WILL_TXN_PARTY WHERE WILL_TXN_ID ='" + willId
					+ "' AND PARTY_TYPE_ID='TESTATOR'";

			rst = st.executeQuery(sqlText);

			while (rst.next()) {
				image = ((OracleResultSet) rst).getBLOB(docObject);
			}
			os = res.getOutputStream();
			long index = 1;

			res.setContentType("application/download");
			res.setHeader("Content-Disposition",
					"attachment; filename=Photo.gif");

			// Loop through while reading a data from the BLOB
			// column using the getBytes() method. This data will be stored
			// in a BLOB column and writting in OutPutStream.
			while (index < image.length()) {
				os.write(image.getBytes(index, 10000));
				os.flush();
				index += 10000;
			}

		} catch (IOException e) {
			logger.debug(e);
			// throw e;
		} catch (SQLException e) {
			// e.printStackTrace();
			logger
					.error(" DBUtility:downloadContent:finally :General Exception Clause"
							+ e.toString());
		}finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return returnStr;
	}
	
	 public ArrayList getFunctionName(String[] funId) {
	        ArrayList list = null;
	        DBUtility dbUtil=null;
	        try
	        {
	            dbUtil.createPreparedStatement(CommonSQL.GET_FUNID_QUERY);
	          
	            list = dbUtil.executeQuery(funId);
	        } catch (Exception x)
	        {
	            logger.debug("Exception in getWill Deposit() :- " + x);
	        }finally{
	    		try {
	    			dbUtil.closeConnection();
	    		} catch (Exception e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    	}
	        return list;
	    } 
	 
	 
	    //ANUJ
		  public ArrayList getPendingWillWithdraw(String districtId)
			{
				ArrayList pendingWithdrawalPayment = new ArrayList();
				ArrayList pendingListFinal = new ArrayList();
				
				
				
				ArrayList list1 = new ArrayList();
				
				String param[]=new String[1];
				param[0]=districtId;
				String query="SELECT WILL_TXN_ID," +
                "  NO_OF_PARTIES," +
                "  PAID_AMOUNT," +
                "  (PAYABLE_AMOUNT-PAID_AMOUNT) DUE_AMOUNT," +
                " UPDATED_DATE," +
                " CREATED_DATE," +
                "  PAYABLE_AMOUNT " +
                "FROM" +
                "  (SELECT A.WILL_TXN_ID," +
                "    A.NO_OF_PARTIES," +
                "    A.CREATED_DATE," +
                "    NVL(A.PAID_AMOUNT, 0.00) PAID_AMOUNT," +
                "    A.DUE_AMOUNT," +
                "    A.UPDATED_DATE," +
                "    A.PAYABLE_AMOUNT" +
                "  FROM" +
                "    (SELECT WT.WILL_TXN_ID," +
                "      WY.NO_OF_PARTIES," +
                "      WP.PAID_AMOUNT," +
                "      (WP.PAYABLE_AMOUNT-WP.PAID_AMOUNT) DUE_AMOUNT," +
                "      WP.UPDATED_DATE," +
                "      TO_CHAR((WT.CREATED_DATE),'dd/mm/yyyy') CREATED_DATE," +
                "      WP.PAYABLE_AMOUNT" +
                "    FROM IGRS_WILL_TXN WT," +
                "      (SELECT IGRS_WILL_TXN_PAYMENT_DTLS.WILL_TXN_ID," +
                "        SUM(IGRS_WILL_TXN_PAYMENT_DTLS.PAID_AMOUNT) PAID_AMOUNT," +
                "        MAX(IGRS_WILL_TXN_PAYMENT_DTLS.PAYABLE_AMOUNT) PAYABLE_AMOUNT," +
                "        TO_CHAR(MAX(IGRS_WILL_TXN_PAYMENT_DTLS.UPDATE_DATE), 'dd/mm/yyyy') UPDATED_DATE" +
                "      FROM IGRS_WILL_TXN_PAYMENT_DTLS" +
                "      GROUP BY IGRS_WILL_TXN_PAYMENT_DTLS.WILL_TXN_ID" +
                "      ) WP," +
                "      (SELECT IGRS_WILL_TXN_PARTY.WILL_TXN_ID," +
                "        COUNT(IGRS_WILL_TXN_PARTY.PARTY_TYPE_ID) NO_OF_PARTIES" +
                "      FROM IGRS_WILL_TXN_PARTY" +
                "      GROUP BY WILL_TXN_ID" +
                "      ) WY" +
                "    WHERE WP.WILL_TXN_ID = WT.WILL_TXN_ID" +
                "    AND WT.WILL_TXN_ID   = WY.WILL_TXN_ID" +
                "    AND WT.WILL_STATUS   = 'DI'" +
                "    ) A" +
                "  " +
                "  UNION ALL" +
                "  " +
                "  SELECT B.WILL_TXN_ID," +
                "    B.NO_OF_PARTIES," +
                "    B.CREATED_DATE," +
                "    NVL(B.PAID_AMOUNT, 0.00)," +
                "    B.DUE_AMOUNT," +
                "    B.UPDATED_DATE," +
                "    B.DUE_AMOUNT AS PAYABLE_AMOUNT" +
                "  FROM" +
                "    (SELECT WT.WILL_TXN_ID," +
                "      TO_CHAR((WT.CREATED_DATE),'dd/mm/yyyy') CREATED_DATE," +
                "      (SELECT 0.00 FROM DUAL" +
                "      ) AS PAID_AMOUNT," +
                "      (SELECT NULL FROM DUAL" +
                "      ) AS UPDATED_DATE," +
                "      (SELECT null FROM DUAL" +
                "      )  AS DUE_AMOUNT," +
                "      WY.NO_OF_PARTIES" +
                "    FROM IGRS_WILL_TXN WT," +
                "      (SELECT IGRS_WILL_TXN_PARTY.WILL_TXN_ID," +
                "        COUNT(IGRS_WILL_TXN_PARTY.PARTY_TYPE_ID) NO_OF_PARTIES" +
                "      FROM IGRS_WILL_TXN_PARTY" +
                "      GROUP BY WILL_TXN_ID" +
                "      ) WY" +
                "    WHERE WT.WILL_STATUS    = 'DI'" +
                "    AND WT.WILL_TXN_ID      = WY.WILL_TXN_ID" +
                "    AND WT.WILL_TXN_ID NOT IN" +
                "      (SELECT DISTINCT(WILL_TXN_ID) FROM IGRS_WILL_TXN_PAYMENT_DTLS" +
                "      )" +
                "    ) B" +
                "  ) where SUBSTR(WILL_TXN_ID,3,2)=?" +
                " ORDER BY to_date(CREATED_DATE, 'dd/mm/yyyy') desc";
				DBUtility dbUtil=null;

				try {
					dbUtil = new DBUtility();
					//dbUtil.createStatement();
							String sql= CommonSQL.GET_PENDING_DEPOSIT;
							dbUtil.createPreparedStatement(query);
						//System.out.println("working till here anuj"+userId);
							
							try
							{	
								pendingWithdrawalPayment = dbUtil.executeQuery(param);
						           
					
				} catch (Exception x) {
					logger.debug(x);
					x.printStackTrace();
				} finally {
					try {
						
						dbUtil.closeConnection();
					} catch (Exception ex) {
						logger.debug(ex);
						ex.printStackTrace();
					}
				}
				
			}
				catch(Exception e){
					logger.debug(e);
				}finally{
					try {
						dbUtil.closeConnection();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return pendingWithdrawalPayment;
				}
		  
		  
		  public String insertWillWithDrawDetails1(String userId
					,  String willId,
					  float total, String funId){
				String k="";
				DBUtility dbUtil=null;
				try{
					dbUtil = new DBUtility();
				
					String seqCall=CommonSQL.seqCall;
				dbUtil.createStatement();
				ArrayList alist=dbUtil.executeQuery(seqCall);
			
				ArrayList s=(ArrayList)alist.get(0);
				k=(String)s.get(0);
			//for next query
				
				dbUtil.createPreparedStatement(CommonSQL.INSERT_PYMNT_TBL);
				Float f=total;
				String s1=f.toString();
				String param1[]=new String[7];
				param1[0]=k;
				param1[1]=willId;
				param1[2]=funId;
				param1[3]=s1;
				param1[4]="";
				param1[5]="I";
				param1[6]=userId;
				//param[6] is SYSDATE
				dbUtil.executeUpdate(param1);
				dbUtil.commit();
				
				}
				catch(Exception e){
					e.printStackTrace();
				}
				finally{
					try {
						dbUtil.closeConnection();
					}catch(Exception x){
						x.getStackTrace();
					}
				}
				
				return k;
			}
		  
		  public ArrayList checkStatus(String willId){
			  String status="";
			  ArrayList statusList= new ArrayList();
				String[] param={willId};
				DBUtility dbUtil=null;
			  try{
					dbUtil = new DBUtility();
				

				String sql=CommonSQL.CHECK_BAL;
				dbUtil.createPreparedStatement(sql);				
			
				statusList= dbUtil.executeQuery(new String[]{willId});
			  }
			  catch(Exception e){
				  e.printStackTrace();
			  }
			  finally{
				  try {
						dbUtil.closeConnection();
					}catch(Exception x){
						x.getStackTrace();
					}
			  }
			  return statusList;
		  }
		  
		  public String updateStatus(String willId, String funId){
				String[] param={willId};
				String sql="";
				DBUtility dbUtil=null;
				try{
				  dbUtil = new DBUtility();
				 if("03".equalsIgnoreCase(willId.substring(10, 12))) {
			  sql=CommonSQL.UPDATE_RET_STATUS;
				 }
				 else if("02".equalsIgnoreCase(willId.substring(10, 12))) {
			  sql=CommonSQL.UPDATE_WITH_STATUS;
				 }
				 else if("01".equalsIgnoreCase(willId.substring(10, 12))) {
					 sql=CommonSQL.UPDATE_DEP_STATUS;
				 }
			  dbUtil.createPreparedStatement(sql);
			  
			  dbUtil.executeUpdate(new String[]{willId});
			  
			  
			  
			  }
			  catch(Exception e){
				  e.printStackTrace();
			  }
			  finally{
				  try {
						dbUtil.closeConnection();
					}catch(Exception x){
						x.getStackTrace();
					}
			  }
			  
			  return willId;
		  }
		  
		  public void deleteDetails(String willId){
			  String param[] = new String[1];
				param[0] = willId;
				DBUtility dbUtil=null;
				String sql = CommonSQL.DEL_DETLS;
				try {
					dbUtil = new DBUtility();
					
					
					
					dbUtil.createPreparedStatement(sql);
					boolean boo = dbUtil.executeUpdate(param);
					
					if (boo) {
						deleteDetails1(willId);

					}else {
						dbUtil.rollback();
					}	
				} catch (Exception e) {
					e.getStackTrace();

				}finally {
					try {
						dbUtil.closeConnection();
					}catch(Exception x){
						x.getStackTrace();
					}
				}

		  }
		  
		  public void deleteDetails1(String willId){
			  String param[] = new String[1];
				param[0] = willId;
				
				String sql = CommonSQL.DEL_DTLS1;
				DBUtility dbUtil=null;
				try {
					dbUtil = new DBUtility();
					dbUtil.setAutoCommit(false);
					dbUtil.createPreparedStatement(sql);
					boolean boo = dbUtil.executeUpdate(param);
					if (boo) {
					}else {
						dbUtil.rollback();
					}	
				} catch (Exception e) {
					e.getStackTrace();

				}finally {
					try {
						dbUtil.closeConnection();
					}catch(Exception x){
						x.getStackTrace();
					}
				}

		  }
		  //ANUJ
}