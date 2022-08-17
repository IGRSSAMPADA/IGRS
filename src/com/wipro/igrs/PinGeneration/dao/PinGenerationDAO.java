/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   PinGenerationDAO.java
 * Author      :   Samuel Prabhakaran
 * Description :   Represents the DAO Class for User Registration.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Neesha   		16Jan,2008		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.PinGeneration.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.wipro.igrs.PinGeneration.dto.PinGenerationDTO;
import com.wipro.igrs.PinGeneration.sql.CommonSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.regCompChecker.dto.RegCompCheckerDTO;

/**
 * @author Samuel Prabhakaran
 * 
 */
public class PinGenerationDAO {
	//DBUtility dbUtil;
	private Logger logger = (Logger) Logger.getLogger(PinGenerationDAO.class);
	PreparedStatement pst = null;

	/**
	 * @throws Exception
	 */
	public PinGenerationDAO() {

	}

	/**
	 * 
	 * ===========================================================================
	 * 
	 * Method : getSequenceNumber()
	 * 
	 * Description : Generate RTI Sequence Id .
	 * 
	 * return type : String
	 * 
	 * Author : Samuel Prabhakaran
	 * 
	 * Created Date : 6 June,2008
	 * 
	 * ===========================================================================
	 * 
	 * 
	 * 
	 */

	public String getSequenceNumber(String _seqName, String _moduleConstant)
			throws Exception {

		String seqnumber = "";
		DBUtility dbUtil=null;
		try {

			dbUtil = new DBUtility();

			Date date = new Date();

			Format yearformat = new SimpleDateFormat("yyyy");

			Format monthformat = new SimpleDateFormat("MM");

			Format dateformat = new SimpleDateFormat("dd");

			String dfmt = dateformat.format(date);

			String yfmt = yearformat.format(date);

			String mfmt = monthformat.format(date);

			String SQL = "select " + _seqName + ".nextval from dual";

			dbUtil.createStatement();

			String number = dbUtil.executeQry(SQL);

			seqnumber = _moduleConstant + dfmt + mfmt + yfmt + number;

		} catch (Exception e)

		{

			logger.debug("Exception occur in PIN Generation: " + e);

		} finally {

			if (dbUtil != null) {

				dbUtil.closeConnection();

			}

		}

		return seqnumber;

	}

	/**
	 * 
	 * ===========================================================================
	 * 
	 * Method : getPropertyList()
	 * 
	 * Description : Get the property list.
	 * 
	 * return type : ArrayList
	 * 
	 * Author : Samuel Prabhakaran
	 * 
	 * Created Date : 6 June,2008
	 * 
	 * ===========================================================================
	 * 
	 * 
	 * 
	 */

	public ArrayList getPropertyList(String regproperty) {
		ArrayList propertyList = new ArrayList();
		DBUtility dbUtil=null;

		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();

		try {
			dbUtil = new DBUtility();
			PinGenerationDTO property;
			dbUtil.createStatement();

			list2 = dbUtil.executeQuery(CommonSQL.PROPERTY + "'" + regproperty
					+ "'");

			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList) list2.get(i);

				property = new PinGenerationDTO();
				property.setPropertyTransName((String) list1.get(0));

				propertyList.add(property);

			}

		} catch (Exception ex) {
			logger.debug("Exception occur in PIN Generation: " + ex);

			ex.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception occur in PIN Generation: " + ex);

				ex.printStackTrace();
			}
		}

		return propertyList;
	}

	/**
	 * 
	 * ===========================================================================
	 * 
	 * Method : getDistrictList()
	 * 
	 * Description : Selecting all District from IGRS_DISTRICT_MASTER .
	 * 
	 * return type : Arraylist
	 * 
	 * Author : Samuel Prabhakaran
	 * 
	 * Created Date : 18 Feb,2008
	 * 
	 * ===========================================================================
	 * 
	 * 
	 * 
	 */

	public ArrayList getDistrictList(String state,String lang) {
		ArrayList didtrictList = new ArrayList();

		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();
			PinGenerationDTO country;
			dbUtil.createStatement();

			list2 = dbUtil.executeQuery(CommonSQL.DISTRICT + "STATE_ID='"
					+ state + "'");

			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList) list2.get(i);

				country = new PinGenerationDTO();
				country.setDistrictId((String) list1.get(0));
				if(lang.equalsIgnoreCase("en"))
					country.setDistrictName((String) list1.get(1));
				else
					country.setDistrictName((String) list1.get(2));

				didtrictList.add(country);

			}

		} catch (Exception ex) {
			logger.debug("Exception occur in PIN Generation: " + ex);

			ex.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception occur in PIN Generation: " + ex);

				ex.printStackTrace();
			}
		}

		return didtrictList;
	}
	public ArrayList getStateList(String state, String lang) {
		ArrayList didtrictList = new ArrayList();

		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();
			PinGenerationDTO country;
			dbUtil.createStatement();

			list2 = dbUtil.executeQuery(CommonSQL.STATE 
					+ state + "'");

			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList) list2.get(i);

				country = new PinGenerationDTO();
				country.setStateId((String) list1.get(0));
				if(lang.equalsIgnoreCase("en"))
					country.setStateName((String) list1.get(1));
				else
					country.setStateName((String) list1.get(2));

				didtrictList.add(country);

			}

		} catch (Exception ex) {
			logger.debug("Exception occur in PIN Generation: " + ex);

			ex.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception occur in PIN Generation: " + ex);

				ex.printStackTrace();
			}
		}

		return didtrictList;
	}

	/**
	 * 
	 * ===========================================================================
	 * 
	 * Method : getStateList()
	 * 
	 * Description : Selecting all State from IGRS_STATE_MASTER .
	 * 
	 * return type : Arraylist
	 * 
	 * Author : Samuel Prabhakaran
	 * 
	 * Created Date : 18 Feb,2008
	 * 
	 * ===========================================================================
	 * 
	 * 
	 * 
	 */

	public ArrayList getStateList() {
		ArrayList stateList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();
			PinGenerationDTO country;
			dbUtil.createStatement();

			list2 = dbUtil.executeQuery(CommonSQL.STATE);

			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList) list2.get(i);

				country = new PinGenerationDTO();
				country.setStateId((String) list1.get(0));
				country.setStateName((String) list1.get(1));
				stateList.add(country);

			}

		} catch (Exception ex) {
			logger.debug("Exception occur in PIN Generation: " + ex);

			ex.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception occur in PIN Generation: " + ex);

				ex.printStackTrace();
			}
		}

		return stateList;
	}

	/**
	 * 
	 * ===========================================================================
	 * 
	 * Method : getCountryList()
	 * 
	 * Description : Selecting all Country from IGRS_COUNTRY_MASTER .
	 * 
	 * return type : Arraylist
	 * 
	 * Author : Samuel Prabhakaran
	 * 
	 * Created Date : 18 Feb,2008
	 * 
	 * ===========================================================================
	 * 
	 * 
	 * 
	 */

	public ArrayList getCountryList(String lang) {
		ArrayList countryList = new ArrayList();

		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();
			PinGenerationDTO country;
			dbUtil.createStatement();

			list2 = dbUtil.executeQuery(CommonSQL.COUNTRY);

			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList) list2.get(i);

				country = new PinGenerationDTO();
				country.setCountryId((String) list1.get(0));
				if(lang.equalsIgnoreCase("en"))
					country.setCountry((String) list1.get(1));
				else
					country.setCountry((String) list1.get(2));
				countryList.add(country);

			}

		} catch (Exception ex) {
			logger.debug("Exception occur in PIN Generation: " + ex);

			ex.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception occur in PIN Generation: " + ex);

				ex.printStackTrace();
			}
		}

		return countryList;
	}

	/**
	 * 
	 * ===========================================================================
	 * 
	 * Method : getphotoIdList()
	 * 
	 * Description : Selecting all ID TYPE from IGRS_PHOTOID_PROOF_TYPE_MASTER .
	 * 
	 * return type : Arraylist
	 * 
	 * Author : Samuel Prabhakaran
	 * 
	 * Created Date : 18 Feb,2008
	 * 
	 * ===========================================================================
	 * 
	 * 
	 * 
	 */

	public ArrayList getphotoIdList(String lang) {

		ArrayList idlist = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		PinGenerationDTO dto;
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();

			dbUtil.createStatement();
			list2 = dbUtil.executeQuery(CommonSQL.IGRS_ID_TYPE);
			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList) list2.get(i);

				dto = new PinGenerationDTO();
				dto.setCommonID((String) list1.get(0));
				if(lang.equalsIgnoreCase("en"))
					dto.setIdName((String) list1.get(1));
				else
					dto.setIdName((String) list1.get(2));
				idlist.add(dto);

			}

		} catch (Exception ex) {
			logger.debug("Exception occur in PIN Generation: " + ex);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception occur in PIN Generation: " + ex);

				ex.printStackTrace();
			}
		}

		return idlist;
	}

	/**
	 * 
	 * @param pinCreate
	 * @param pinReq
	 * @param pinReqParty
	 * @param deathfile
	 * @param mutationfile
	 * @return
	 */
	public boolean generatePin(//String[] pinCreate, String[] pinReq,
			String[] pinReqParty)// File deathfile, File mutationfile,
			//String applicantType,String roleId, String funId, String userId) {
	{	
	boolean bol = true;
	DBUtility dbUtil=null;

		try{
			dbUtil = new DBUtility();
			if(pinReqParty.length>10)
			{
				String query=CommonSQL.PINREQPARTYDETAIL;
				dbUtil.createPreparedStatement(query);
				return dbUtil.executeUpdate(pinReqParty);
			}
			else
			{
				String query=CommonSQL.PINREQPARTYOWNERDETAIL;
				dbUtil.createPreparedStatement(query);
				return dbUtil.executeUpdate(pinReqParty);
			}
		}
		catch(Exception e){
			
				e.printStackTrace();
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.debug("pin req not inserted");
				e.printStackTrace();
			}
		}
		return true;
	/*try {
			int j = 0;
			dbUtil = new DBUtility();
			IGRSCommon igrsCommon = new IGRSCommon();
			FileInputStream fis = null;
			FileInputStream fis1 = null;

			if (deathfile != null && deathfile.length() > 0
					&& mutationfile != null && mutationfile.length() > 0) {

				fis = new FileInputStream(deathfile);
				fis1 = new FileInputStream(mutationfile);

			}
			dbUtil.createPreparedStatement(CommonSQL.PINCREATE);
			bol = dbUtil.executeUpdate(pinCreate);
			if(bol)
			{
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_REG_PIN_DETAILS","INSERT","T",funId,userId,roleId);
				
			}
			else
			{
				dbUtil.rollback();
				igrsCommon.saveLogDet("IGRS_REG_PIN_DETAILS","INSERT","F",funId,userId,roleId);
			}
			if (bol) {
				dbUtil.createPreparedStatement(CommonSQL.PINREQDETAIL);
				bol = dbUtil.executeUpdate(pinReq);
				if(bol)
				{
					dbUtil.commit();
					igrsCommon.saveLogDet("IGRS_REG_PIN_REQ_DETAILS","INSERT","T",funId,userId,roleId);
					
				}
				else
				{
					dbUtil.rollback();
					igrsCommon.saveLogDet("IGRS_REG_PIN_REQ_DETAILS","INSERT","F",funId,userId,roleId);
				}
				if (bol && "2".equals(applicantType)) {
					pst = dbUtil
							.returnPreparedStatement(CommonSQL.PINDOCDETAILS);
					pst.setString(1, pinReq[0]);
					pst.setString(2, "DEATH_CERTIFICATE");
					pst.setString(3, deathfile.getName().substring(
							deathfile.getName().length() - 3,
							deathfile.getName().length()));
					if (fis != null) {
						pst.setBinaryStream(4, fis, (int) fis.available());
					} else {
						pst.setBinaryStream(4, fis, 0);
					}

					j = pst.executeUpdate();
					logger
							.info("Wipro in PinGeneration - generatePin() after creating preparedst");
					if (j > 0) {
						bol = true;
					} else {
						bol = false;
					}
					if(bol)
					{
						dbUtil.commit();
						igrsCommon.saveLogDet("IGRS_REG_PIN_DOC_DETAILS","INSERT","T",funId,userId,roleId);
						
					}
					else
					{
						dbUtil.rollback();
						igrsCommon.saveLogDet("IGRS_REG_PIN_DOC_DETAILS","INSERT","F",funId,userId,roleId);
					}
					if (bol) {
						pst = dbUtil.returnPreparedStatement(CommonSQL.PINDOCDETAILS);
						pst.setString(1, pinReq[0]);
						pst.setString(2, "MUTATION_FILE");
						pst.setString(3, mutationfile.getName().substring(
								mutationfile.getName().length() - 3,
								mutationfile.getName().length()));
						if (fis != null) {
							pst
									.setBinaryStream(4, fis1, (int) fis1
											.available());
						} else {
							pst.setBinaryStream(4, fis1, 0);
						}

						j = pst.executeUpdate();
						logger
								.info("Wipro in PinGeneration - generatePin() after creating preparedst");
						if (j > 0) {
							bol = true;
						} else {
							bol = false;
						}
						
					}
					if(bol)
					{
						dbUtil.commit();
						igrsCommon.saveLogDet("IGRS_REG_PIN_DOC_DETAILS","INSERT","T",funId,userId,roleId);
						
					}
					else
					{
						dbUtil.rollback();
						igrsCommon.saveLogDet("IGRS_REG_PIN_DOC_DETAILS","INSERT","F",funId,userId,roleId);
					}
					
				}
				if (bol) {
					dbUtil.createPreparedStatement(CommonSQL.PINREQPARTYDETAIL);
					bol = dbUtil.executeUpdate(pinReqParty);
				}
			}

			if(bol)
			{
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_REG_PIN_REQ_PARTY_DTLS","INSERT","T",funId,userId,roleId);
				
			}
			else
			{
				dbUtil.rollback();
				igrsCommon.saveLogDet("IGRS_REG_PIN_REQ_PARTY_DTLS","INSERT","F",funId,userId,roleId);
			}

		} catch (Exception ex) {
			logger.error("Exception occur in PIN Generation: " + ex);
			bol = false;
			try {
				dbUtil.rollback();

			} catch (Exception x) {
				logger.error("Exception occur in PIN Generation: " + x);

				ex.printStackTrace();
			}
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception occur in PIN Generation: " + ex);

				ex.printStackTrace();
			}
		}
		return bol;
*/
	}

	public ArrayList viewDetails(String reqId, String fromDate, String toDate) {

		ArrayList list2 = new ArrayList();
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();
			PinGenerationDTO property;
			dbUtil.createStatement();
			String SQL = CommonSQL.VIEW;

			if (reqId != null && !"".equals(reqId.trim())) {
				SQL = SQL + " AND B.PIN_REQ_TXN_ID='" + reqId.trim() + "'";
			}

			if (fromDate != null && !"".equals(fromDate.trim())
					&& toDate != null && !"".equals(toDate.trim())) {

				SQL = SQL + " AND TRUNC(B.REQ_DATE) BETWEEN TO_CHAR(TO_DATE('"
						+ fromDate + "','dd/mm/yyyy')) AND TO_CHAR(TO_DATE('"
						+ toDate + "','dd/mm/yyyy'))";

			} else if (fromDate != null && !"".equals(fromDate.trim())
					&& (toDate == null || "".equals(toDate.trim()))) {

				SQL = SQL + " AND TRUNC(B.REQ_DATE) >= TO_CHAR(TO_DATE('"
						+ fromDate + "','dd/mm/yyyy'))";

			} else if ((fromDate == null || "".equals(fromDate.trim()))
					&& toDate != null && !"".equals(toDate.trim())) {

				SQL = SQL + " AND TRUNC(B.REQ_DATE) <= TO_CHAR(TO_DATE('"
						+ toDate + "','dd/mm/yyyy'))";

			}

			list2 = dbUtil.executeQuery(SQL);
		} catch (Exception ex) {
			logger.error("Exception occur in PIN Generation: " + ex);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception occur in PIN Generation: " + ex);

				ex.printStackTrace();
			}
		}

		return list2;

	}

	/**
	 * 
	 * @param regNo
	 * @param propertyNo
	 * @return ArrayList
	 */

	public ArrayList check(String regNo, String propertyNo) {

		ArrayList list2 = null;
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();

			dbUtil.createStatement();
			String SQL = CommonSQL.CHECK;
			SQL = SQL + " WHERE REGISTRATION_ID='" + regNo
					+ "' AND PROPERTY_TXN_ID='" + propertyNo + "'";
			list2 = dbUtil.executeQuery(SQL);
		} catch (Exception ex) {
			logger.debug("Exception occur in PIN Generation: " + ex);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception occur in PIN Generation: " + ex);

				ex.printStackTrace();
			}
		}

		return list2;

	}

	/**
	 * 
	 * ===========================================================================
	 * 
	 * Method : getUpdateDate()
	 * 
	 * Description : Get the updatedate of the pin.
	 * 
	 * return type : ArrayList
	 * 
	 * Author : Samuel Prabhakaran
	 * 
	 * Created Date : 6 June,2008
	 * 
	 * ===========================================================================
	 * 
	 * 
	 * 
	 */

	public ArrayList getUpdateDate(String pinReqNo) {
		ArrayList list = null;
		String regNo = null, proTxnNo = null;
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();

			dbUtil.createStatement();

			ArrayList list2 = dbUtil
					.executeQuery("SELECT REGISTRATION_ID,PROPERTY_TXN_ID FROM IGRS_REG_PIN_REQ_DETAILS WHERE PIN_REQ_TXN_ID='"
							+ pinReqNo + "'");
			if (list2 != null) {
				ArrayList list1 = (ArrayList) list2.get(0);
				regNo = (String) list1.get(0);
				proTxnNo = (String) list1.get(1);
			}
			if (regNo != null && proTxnNo != null) {
				dbUtil.createStatement();
				list = dbUtil
						.executeQuery("select TO_CHAR(MAX(REQ_DATE),'DD/MM/YYYY') FROM IGRS_REG_PIN_REQ_DETAILS  WHERE REQ_DATE <(select MAX(REQ_DATE) FROM IGRS_REG_PIN_REQ_DETAILS  WHERE PROPERTY_TXN_ID = '"
								+ proTxnNo
								+ "' AND  REGISTRATION_ID ='"
								+ regNo
								+ "' AND PIN_REQ_TXN_ID='"
								+ pinReqNo
								+ "') AND PROPERTY_TXN_ID = '"
								+ proTxnNo
								+ "' AND  REGISTRATION_ID ='" + regNo + "'");
			}

		} catch (Exception ex) {
			logger.debug("Exception occur in PIN Generation: " + ex);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception occur in PIN Generation: " + ex);

				ex.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * 
	 * ===========================================================================
	 * 
	 * Method : getOfficeId()
	 * 
	 * Description : Get the office id.
	 * 
	 * return type : ArrayList
	 * 
	 * Author : Samuel Prabhakaran
	 * 
	 * Created Date : 6 June,2008
	 * 
	 * ===========================================================================
	 * 
	 * 
	 * 
	 */

	public ArrayList getOfficeId(String pinReqNo) {
		ArrayList list = null;
		String userId = null;
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();

			dbUtil.createStatement();

			ArrayList list2 = dbUtil
					.executeQuery("SELECT USER_ID FROM IGRS_REG_PIN_REQ_DETAILS WHERE PIN_REQ_TXN_ID='"
							+ pinReqNo + "'");
			if (list2 != null) {
				ArrayList list1 = (ArrayList) list2.get(0);
				userId = (String) list1.get(0);

			}
			if (userId != null && userId.trim() != "") {
				dbUtil.createStatement();
				list = dbUtil
						.executeQuery("select OFFICE_ID FROM IGRS_EMP_OFFICE_MAPPING  WHERE EMP_ID='"
								+ userId + "'");
			}

		} catch (Exception ex) {
			logger.debug("Exception occur in PIN Generation: " + ex);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception occur in PIN Generation: " + ex);

				ex.printStackTrace();
			}
		}

		return list;

	}

	/**
	 * 
	 * ===========================================================================
	 * 
	 * Method : readBLOBToFileGet()
	 * 
	 * Description : Get the BLOB File(death file).
	 * 
	 * return type : ResultSet
	 * 
	 * Author : Samuel Prabhakaran
	 * 
	 * Created Date : 6 June,2008
	 * 
	 * ===========================================================================
	 * 
	 * 
	 * 
	 */

	public ResultSet readBLOBToFileGet(String pinReqNo) {
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();

			return dbUtil.readBLOBToFileGet(pinReqNo);
		} catch (Exception e) {
			logger.error("Exception occur in PIN Generation: " + e);
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 
	 * ===========================================================================
	 * 
	 * Method : readBLOBToFileGet1()
	 * 
	 * Description : Get the BLOB File(mutation file).
	 * 
	 * return type : ResultSet
	 * 
	 * Author : Samuel Prabhakaran
	 * 
	 * Created Date : 6 June,2008
	 * 
	 * ===========================================================================
	 * 
	 * 
	 * 
	 */

	public ResultSet readBLOBToFileGet1(String pinReqNo) {
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();

			return dbUtil.readBLOBToFileGet1(pinReqNo);
		} catch (Exception e) {
			logger.error("Exception occur in PIN Generation: " + e);
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @param sParam
	 * @return boolean
	 */
	public boolean insertPINGenerationDetails(String[] genPinparam,String roleId, String funId, String userId) {
		boolean pinDetailsSubmit = true;
		DBUtility dbUtil=null;

		try {

			dbUtil = new DBUtility();
			IGRSCommon igrsCommon = new IGRSCommon();
			dbUtil.createPreparedStatement(CommonSQL.PIN_CREDENTIALS_INSERT);
			pinDetailsSubmit = dbUtil.executeUpdate(genPinparam);

			if (pinDetailsSubmit) {
				dbUtil.commit();
				igrsCommon.saveLogDet("PIN_GENERATION","INSERT","T",funId,userId,roleId);
			} else {
				dbUtil.rollback();
				igrsCommon.saveLogDet("PIN_GENERATION","INSERT","F",funId,userId,roleId);
			}

		} catch (Exception x) {
			logger.debug("Exception in insertPINGenerationDetails() :- " + x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception x) {
				logger.debug(x);
			}
		}
		return pinDetailsSubmit;
	}

	/**
	 * @param genPinparam
	 * @return
	 */
	public boolean updateGeneratedPINDetails(String[] param,String roleId, String funId, String userId) {
		boolean pinDetailsUpdated = true;
		DBUtility dbUtil=null;

		try {

			dbUtil.createPreparedStatement(CommonSQL.UPDATE_PIN_CREDENTIALS);
			pinDetailsUpdated = dbUtil.executeUpdate(param);
			 IGRSCommon igrsCommon = new IGRSCommon();
			if(pinDetailsUpdated)
			{
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_REG_PIN_DETAILS","UPDATE","T",funId,userId,roleId);
				
			}
			else
			{
				dbUtil.rollback();
				igrsCommon.saveLogDet("IGRS_REG_PIN_DETAILS","UPDATE","F",funId,userId,roleId);
			}

		} catch (Exception x) {
			logger.debug("Exception in updateGeneratedPINDetails() :- " + x);
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pinDetailsUpdated;
	}

	/**
	 * @param genPinparam
	 * @return
	 */
	public ArrayList extractStoredPins(String paramRegId) {
		ArrayList list = null;
		DBUtility dbUtil=null;

		try {
			logger.debug("Before getting PIN numbers... ");
			dbUtil.createPreparedStatement(CommonSQL.SEARCH_PIN_CREDENTIALS);
			logger.debug("SQL:" + CommonSQL.SEARCH_PIN_CREDENTIALS);

			String param[] = new String[1];
			param[0] = paramRegId;
			list = dbUtil.executeQuery(param);

		} catch (Exception x) {
			logger.debug("Exception in getUserDetails() :- " + x);
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @param ref
	 * @return ArrayList
	 */
	public String getPINs() {
		char[] pinValue = null;
		try {
			int pinLength = 8;
			pinValue = new char[pinLength];
			int c = 'A';
			for (int count = 0; count < pinLength; count++) {
				int pinTemp = 0 + (int) (Math.random() * pinLength);
				switch (pinTemp) {
				case 0:
					c = '0' + (int) (Math.random() * 10);
					break;
				case 1:
					c = 'A' + (int) (Math.random() * 26);
					break;
				}
				pinValue[count] = (char) c;
			}

		} catch (Exception x) {
			logger.info("Exception in getPINs() :- " + x);
		}
		return new String(pinValue);
	}
	 public ArrayList regIdCheckInfo(String _regNo)throws ServletException,IOException,SQLException,Exception{
	    	logger.debug("WE ARE IN DAO-regIdCheckDAO");
	        DBUtility dbUtil=null;
	        ArrayList typeList=null;
	        try {
	            dbUtil=new DBUtility();
	            String str=CommonSQL.IGRS_NOENCUM_REGID_SEARCH;
	            dbUtil.createPreparedStatement(str);
	            String param[]=new String[1];
	            param[0]=_regNo;
	            typeList=dbUtil.executeQuery(param);
	            return typeList;
		    }catch(IOException ie){
	        	logger.error(ie);
	        }
	        catch(SQLException se){
	        	logger.error(se);
	        }
	        catch(Exception e){
		    	logger.error(e);
		    }
		    finally {
		    	logger.error("Connection is closed using FINALLY");
		        dbUtil.closeConnection(); 
		    }
		    return typeList;
		}
	    
	 public ArrayList getClaimaintDetails(String propId)throws ServletException,IOException,SQLException,Exception{
	    	logger.debug("WE ARE IN DAO-regIdCheckDAO");
	        DBUtility dbUtil=null;
	        ArrayList typeList=null;
	        try {
	            dbUtil=new DBUtility();
	            String str=CommonSQL.CLAIMAINT_DETAILS;
	            dbUtil.createPreparedStatement(str);
	            String param[]=new String[1];
	            param[0]=propId;
	            typeList=dbUtil.executeQuery(param);
	            return typeList;
		    }catch(IOException ie){
	        	logger.error(ie);
	        }
	        catch(SQLException se){
	        	logger.error(se);
	        }
	        catch(Exception e){
		    	logger.error(e);
		    }
		    finally {
		    	logger.error("Connection is closed using FINALLY");
		        dbUtil.closeConnection(); 
		    }
		    return typeList;
		}


public String getDeedID(String regNo)throws ServletException,IOException,SQLException,Exception{
	logger.debug("WE ARE IN DAO-regIdCheckDAO");
    DBUtility dbUtil=null;
   String typeList=null;
    try {
        dbUtil=new DBUtility();
        String str=CommonSQL.GET_DEED_ID;
        dbUtil.createPreparedStatement(str);
        String param[]=new String[1];
        param[0]=regNo;
        typeList=dbUtil.executeQry(param);
        return typeList;
    }catch(IOException ie){
    	logger.error(ie);
    }
    catch(SQLException se){
    	logger.error(se);
    }
    catch(Exception e){
    	logger.error(e);
    }
    finally {
    	logger.error("Connection is closed using FINALLY");
        dbUtil.closeConnection(); 
    }
    return typeList;
}

public boolean pinGeneration(ArrayList propIds, PinGenerationDTO pingen, String userId)
{
	ArrayList list = new ArrayList();
	ArrayList subList = new ArrayList();
	boolean flag = false;
	DBUtility dbUtil=null;
	String pinNo ="";
	String propId = "";
	try {
		dbUtil = new DBUtility();
		String query=CommonSQL.GET_PROPRTY_DETIALS_FOR_PIN;
		for(int i=0;i<propIds.size();i++)
		{
			if(i==0)
			{
				query=query+"?";
			}
			else
			{
				query=query+",?";
			}
		}
		query=query+")";
		String s="";
		logger.debug("query pin generation"+query);
		/*for(int i=0;i<propIds.size();i++)
		{
			if(i==0)
			{
				s=s+propIds.get(i);
			}
			else
			{
				s=s+","+(propIds.get(i));
			}
		}*/
		  for (int i = 0; i < propIds.size(); i++) {
		        if (i == 0) {
		          s = s + ((PinGenerationDTO)propIds.get(i)).getPropertyTxnId();
		        } else {
		          s = s + "," + ((PinGenerationDTO)propIds.get(i)).getPropertyTxnId();
		        }
		      }
		dbUtil.createPreparedStatement(query);
		//System.out.println(propIds.size());
		//System.out.println(s.length());
		if(propIds.size()>1){
		String param[]=s.split(",");
		list = dbUtil.executeQuery(param);
		}
		else{
			String param[]={s};
			list = dbUtil.executeQuery(param);
			
		}
		
		
		
		for(int i = 0 ; i < list.size() ; i++)
		{
			subList = (ArrayList)list.get(i);
			String disttId = (String)subList.get(0);
			String tehId = (String)subList.get(1);
			String propTypeId = (String)subList.get(2);
			 propId = (String)subList.get(3);
			
			if(disttId.length() == 1)
			{
				disttId = "0"+disttId;
			}
			if(tehId.length() == 1)
				tehId = "00"+tehId;
			else if(tehId.length() == 2)
				tehId = "0"+tehId;
			else
				tehId = tehId;
			
			int randomNumber = (int)(Math.random()*9000)+1000;
			logger.debug("^^^^^^^^^^^^ randomNumber"+randomNumber);
			
			String random = String.valueOf(randomNumber);
			
			pinNo = disttId+tehId+propTypeId+random;
			logger.debug("^^^^^^^PIN NUMBER"+pinNo);
			
			String sql = CommonSQL.UPADTE_PIN_DETAILS;
			dbUtil.createPreparedStatement(sql);
			String pinArr[] = {pinNo,
					"A",
					propId};
			flag = dbUtil.executeUpdate(pinArr);
			
			if(flag){
				logger.debug("^^^^^^^flag true for pin generation");
					String content = "Your pin number for property Id "+propId+" is: "+pinNo;
					logger.debug("****** sms content ********"+content);
					String sqlSms = CommonSQL.INSERT_SMS_DETAILS;
					dbUtil.createPreparedStatement(sqlSms);
					String smsArr[] = {pingen.getMobileNumber(),
							userId,
							content};
					flag = dbUtil.executeUpdate(smsArr);
					logger.debug("sms flag*******************"+flag);
				
				
			}
			
		}
		
	} catch (Exception exception) {
		exception.printStackTrace();
		logger.debug("Exception in pinGeneration" + exception);
	}
	finally
	{
		try
		{
			dbUtil.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection pinGeneration"+e.getStackTrace());
		}
		
	}
	return flag;
}
public ArrayList pinDetails(ArrayList propIds) throws Exception
{
	ArrayList list = new ArrayList();
	ArrayList subList = new ArrayList();
	ArrayList mainList = new ArrayList();
	boolean flag = false;
	DBUtility dbUtil=null;

	try {
		dbUtil = new DBUtility();
		String query=CommonSQL.GET_PIN_DETAILS_FOR_PRINT;
		
		for(int i=0;i<propIds.size();i++)
		{
			if(i==0)
			{
				query=query+"?";
			}
			else
			{
				query=query+",?";
			}
		}
		query=query+")"+CommonSQL.GET_PIN_DETAILS;
		dbUtil.createPreparedStatement(query);
		String s="";
		for(int i=0;i<propIds.size();i++)
		{
			if(i==0)
			{
				s=s+((PinGenerationDTO)propIds.get(i)).getPropertyTxnId();
			}
			else
			{
				s=s+","+((PinGenerationDTO)propIds.get(i)).getPropertyTxnId();
			}
		}
		String param[]=s.split(",");
		list = dbUtil.executeQuery(param);
		
		for(int i = 0 ; i < list.size() ;i++)
		{
			subList = (ArrayList)list.get(i);
			RegCompCheckerDTO rdto = new RegCompCheckerDTO();
			rdto.setDistName((String)subList.get(0));
			rdto.setTehsilName((String)subList.get(1));
			rdto.setPropertyTypeName((String)subList.get(2));
			rdto.setPropertyId((String)subList.get(3));
			rdto.setPinNumber((String)subList.get(4));
			mainList.add(rdto);
		}
		
	} catch (Exception exception) {
		logger.debug("Exception in pinDetails" + exception);
	}
	finally
	{
		try
		{
			dbUtil.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection pinDetails"+e.getStackTrace());
		}
		
	}
	return mainList;
}
public boolean changePin(PinGenerationDTO dto)
{
	boolean flag = false;
	int i=0;
	DBUtility dbUtil=null;

	try {
		dbUtil = new DBUtility();
		logger.debug("Before getting PIN numbers... ");
		dbUtil.createPreparedStatement(CommonSQL.CHANGE_PIN);
		logger.debug("SQL:" + CommonSQL.CHANGE_PIN);

		String param[] = new String[3];
		param[0] = dto.getConfirmPIN();
		param[1] = dto.getPropertyTransId();
		param[2] = dto.getOldPIN();
		flag = dbUtil.executeUpdate(param);
		
		

	} catch (Exception x) {
		logger.debug("Exception in getUserDetails() :- " + x);
	}
	finally{
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	return flag;
}


public String getRegTxnID(String regNo)
{
	String flag = "";
	int i=0;
	DBUtility dbUtil=null;

	try {
		dbUtil = new DBUtility();
		
		dbUtil.createPreparedStatement(CommonSQL.GET_REG_TXN_NO);
		logger.debug("SQL:" + CommonSQL.CHECK_PIN);

		String param[] = new String[1];
		
		param[0] = regNo;
		
		flag = dbUtil.executeQry(param);

	} catch (Exception x) {
		logger.debug("Exception in getUserDetails() :- " + x);
	}
	finally{
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	return flag;
}

public String checkPinStatus(PinGenerationDTO dto)
{
	String flag = "";
	int i=0;
	DBUtility dbUtil=null;

	try {
		dbUtil = new DBUtility();
		logger.debug("Before getting PIN numbers... ");
		dbUtil.createPreparedStatement(CommonSQL.CHECK_PIN);
		logger.debug("SQL:" + CommonSQL.CHECK_PIN);

		String param[] = new String[1];
		
		param[0] = dto.getPropertyTransId();
		
		flag = dbUtil.executeQry(param);

	} catch (Exception x) {
		logger.debug("Exception in getUserDetails() :- " + x);
	}
	finally{
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	return flag;
}
}