package com.wipro.igrs.regcompletion.dao;

/**
 * ===========================================================================
 * File           :   RegCompDAO.java
 * Description    :   Represents the RegComp DAO Class
 * @author        :   Imran Shaik
 * Created Date   :   April 02, 2008
 * Updated Date			Version			UpdatedBy
 * April 14, 2008		0.0a			Imran Shaik
 * May 27, 2008			1.0				Imran Shaik 
 * ===========================================================================
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;

import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.CommonSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.regcompletion.constants.RegCompConstant;
import com.wipro.igrs.regcompletion.dto.CommonDTO;
import com.wipro.igrs.regcompletion.dto.RegInitCompleteDTO;
import com.wipro.igrs.regcompletion.dto.UploadFileDTO;
import com.wipro.igrs.regcompletion.form.ApplicantForm;
import com.wipro.igrs.regcompletion.sql.RegCommonSQL;
import com.wipro.igrs.regcompletion.util.PropertiesFileReader;

public class RegCompDAO {


	DBUtility dbUtility = null;
	String sql = null;
	ArrayList mainList = null;
	CommonDTO dto = null;
	IGRSCommon common = null;
	private Logger logger = (Logger) Logger.getLogger(RegCompDAO.class);
	private PropertiesFileReader pr;
	
	// from piyush

	// $01 Method for Retrieving Country from COUNTRY_MASTER
	public ArrayList countryStackDAO() throws ServletException,
			IOException, Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-countryStack");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str = RegCommonSQL.IGRS_COUNTRY_MASTER; // Query for Country
															// list from
															// IGRS_COUNTRY_MASTER
			dbUtil.createStatement();
			typeList = dbUtil.executeQuery(str);
			logger.info("COUNTRY_typeList:=" + typeList);
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CommonDTO type = new CommonDTO();
						type.setCountryId((String) typeTemp.get(0));
						type.setCountryName((String) typeTemp.get(1));
						ar1.add(type);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.error("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return ar1;
	}

	// $01 End
	// $02 Method for Retrieving State from STATE_MASTER
	public ArrayList stateStackDAO(String _countryIdVar)
			throws Exception {
		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-stateStack");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str = RegCommonSQL.IGRS_STATE_MASTER; // Query for state
															// list from
															// IGRS_STATEMASTER
			String param[] = new String[1];
			param[0] = "" + _countryIdVar;
			dbUtil.createPreparedStatement(str);
			typeList = dbUtil.executeQuery(param);
			logger.info("STATE_typeList:=" + typeList);
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CommonDTO type = new CommonDTO();
						type.setStateId((String) typeTemp.get(0));
						type.setStateName((String) typeTemp.get(1));
						ar1.add(type);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.error("Connection is closed using FINALLY");
			dbUtil.closeConnection();
		}
		return ar1;
	}

	// $02 End
	// $03 Method for Retrieving Districts from DISTRICT_MASTER
	public ArrayList districtStackDAO(String _stateIdVar)
			throws Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-districtStack");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str = RegCommonSQL.IGRS_DISTRICT_MASTER; // Query for
															// district list
															// from
															// IGRS_DISTRICT_MASTER
			String param[] = new String[1];
			param[0] = "" + _stateIdVar;
			dbUtil.createPreparedStatement(str);
			typeList = dbUtil.executeQuery(param);
			logger.info("DISTRICT_typeList:=" + typeList);
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CommonDTO type = new CommonDTO();
						type.setDistrictId((String) typeTemp.get(0));
						type.setDistrictName((String) typeTemp.get(1));
						ar1.add(type);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.error("DISTRICT Connection is closed using FINALLY");
			dbUtil.closeConnection();
		}
		return ar1;
	}

	// $03 End
	// $04 Method for Retrieving Photo Id Proof from PHOTO_ID_PROOF_TYPE_MASTER
	public ArrayList photoIdStackDAO() throws Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-photoIdStack");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();
			dbUtil = new DBUtility();
			String str = RegCommonSQL.IGRS_PHOTOID_PROOF_MASTER; // Query for
																	// Photo Id
																	// Proof
																	// Type list
																	// from
																	// IGRS_PHOTOID_PROOF_TYPE_MASTER
			dbUtil.createStatement();
			typeList = dbUtil.executeQuery(str);
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CommonDTO type = new CommonDTO();
						type.setPhotoProofTypeId((String) typeTemp.get(0));
						type.setPhotoProofTypeName((String) typeTemp.get(1));
						ar1.add(type);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.error("Connection is closed using FINALLY");
			dbUtil.closeConnection();
		}
		return ar1;
	}

	// $04 End
	// $05 Method for Inserting Party Details in PARTY_TXN_DETAILS
	public boolean addPartyDAO(String[] param) throws Exception {
		logger.debug("WE ARE IN DAO-addPartyDao");
		DBUtility dbUtil = null;
		boolean flag = false;
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();
			dbUtil = new DBUtility();
			String str = RegCommonSQL.IGRS_REGCOMP_INSERT_PARTY;
			dbUtil.createPreparedStatement(str);
			flag = dbUtil.executeUpdate(param);
			return flag;
		} catch (Exception e) {
			logger.error(e);
			return false;
		} finally {
			logger.error("Connection is closed using FINALLY");
			dbUtil.closeConnection();
		}
	}

	// $05 End
	// $06 Method for Inserting Organization Details in PARTY_TXN_DETAILS
	public boolean addOrgaDAO(String[] param) throws Exception {
		logger.debug("WE ARE IN DAO-addOrgaDao");
		DBUtility dbUtil = null;
		boolean flag = false;
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();
			dbUtil = new DBUtility();
			String str = RegCommonSQL.IGRS_REGCOMP_INSERT_ORGA_DETAILS;
			dbUtil.createPreparedStatement(str);
			flag = dbUtil.executeUpdate(param);
			return flag;
		} catch (Exception e) {
			logger.error(e);
			return false;
		} finally {
			logger.error("Connection is closed using FINALLY");
			dbUtil.closeConnection();
		}
	}

	// $06 End
	// $07 Method for Retrieving Caste from CASTE_MASTER
	public ArrayList casteStackDAO() throws Exception {
		logger.debug("WE ARE IN DAO-casteStack");
		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();
			dbUtil = new DBUtility();
			String str = RegCommonSQL.IGRS_CASTE_MASTER;
			dbUtil.createStatement();
			typeList = dbUtil.executeQuery(str);
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CommonDTO type = new CommonDTO();
						type.setCasteId((String) typeTemp.get(0));
						type.setCasteName((String) typeTemp.get(1));
						ar1.add(type);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.error("Connection is closed using FINALLY");
			dbUtil.closeConnection();
		}
		return ar1;
	}

	// $07 End
	// $08 Method for Retrieving RELIGION from RELIGION_MASTER
	public ArrayList religionStackDAO() throws Exception {
		logger.debug("WE ARE IN DAO-religionStack");
		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();
			dbUtil = new DBUtility();
			String str = RegCommonSQL.IGRS_RELIGION_MASTER;
			dbUtil.createStatement();
			typeList = dbUtil.executeQuery(str);
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CommonDTO type = new CommonDTO();
						type.setReligionId((String) typeTemp.get(0));
						type.setReligionName((String) typeTemp.get(1));
						ar1.add(type);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.error("Connection is closed using FINALLY");
			dbUtil.closeConnection();
		}
		return ar1;
	}

	// $08 End
	// $09 Method for retrieving recordS from PARTY_TXN
	public ArrayList selectPartiesDAO(String _appNo) {
		logger.debug("WE ARE IN DAO-selectPartiesDAO");
		ArrayList list = new ArrayList();
		CommonDTO cDTO = new CommonDTO();
		list = null;
		ArrayList list2 = new ArrayList();

		String errMessages = null;
		try {
			DBUtility dbUtil = new DBUtility();
			String param[] = new String[1];
			param[0] = "" + _appNo;
			String query = RegCommonSQL.IGRS_REGCOMP_SELECT_PARTY_DETAILS;
			dbUtil.createPreparedStatement(query);
			list = dbUtil.executeQuery(param);
			errMessages = "Not Found";
			if (list.isEmpty()) {
				cDTO.setErroMsg(errMessages);
				list2.add(cDTO);
			} else {
				try {
					ArrayList typeTemp = new ArrayList();
					if (list != null) {
						for (int i = 0; i < list.size(); i++) {
							typeTemp = new ArrayList();
							typeTemp = (ArrayList) list.get(i);
							if (typeTemp.size() > 0) {
								CommonDTO type = new CommonDTO();
								type.setPartyId((String) typeTemp.get(0));
								type.setFname((String) typeTemp.get(1));
								type.setOrganizationName((String) typeTemp
										.get(2));
								type.setSerialNo(i + 1);
								list2.add(type);
							}
						}
					}
				} catch (NullPointerException e) {
					logger.error(e);
				} catch (Exception e) {
					logger.error(e);
				} finally {
					logger.error("Connection is closed using FINALLY");
					dbUtil.closeConnection();
				}
			}
			return (list2);
		} catch (Exception e) {
			logger.error(e);
			return (list2);
		}
	}

	// $09 End
	// $10 Method for Viewing party.
	public ArrayList viewPartyDAO(String _partyId) throws Exception {
		logger.debug("WE ARE IN DAO-viewPartyDAO");
		DBUtility dbUtil = new DBUtility();
		ArrayList partyData = new ArrayList();
		try {
			String qry = RegCommonSQL.IGRS_REGCOMP_SELECT_VIEW_PARTY_DETAILS;
			dbUtil.createPreparedStatement(qry);
			String[] param = new String[1];
			param[0] = "" + _partyId;
			partyData = dbUtil.executeQuery(param);
			return partyData;
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			logger.error("Connection is closed using FINALLY");
			dbUtil.closeConnection();
		}
	}

	// $10 End
	// $11 Method for deleting Party from TXN_PARTY
	public boolean deletePartyDAO(String _partyId) throws Exception {
		logger.debug("WE ARE IN DAO-deletePartyDAO");
		DBUtility dbUtil = new DBUtility();
		try {
			String qry = RegCommonSQL.IGRS_REGCOMP_SELECT_DELETE_PARTY_DETAILS;
			dbUtil.createPreparedStatement(qry);
			String[] param = new String[1];
			param[0] = "" + _partyId;
			boolean result = dbUtil.executeUpdate(param);
			return result;
		} catch (Exception e) {
			logger.error(e);
			return false;
		} finally {
			logger.error("Connection is closed using FINALLY");
			dbUtil.closeConnection();
		}
	}

	// $11 End
	// $12 Method for Retrieving party for modification.
	public ArrayList retrievePartyDAO(String _partyId) throws Exception {
		logger.debug("WE ARE IN DAO-retrievePartyDAO");
		DBUtility dbUtil = new DBUtility();
		ArrayList partyData = new ArrayList();
		try {
			String qry = RegCommonSQL.IGRS_REGCOMP_RETRIEVE_PARTY_FOR_MODIFY_DETAILS;
			dbUtil.createPreparedStatement(qry);
			String[] param = new String[1];
			param[0] = "" + _partyId;
			partyData = dbUtil.executeQuery(param);
			return partyData;
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			logger.error("Connection is closed using FINALLY");
			dbUtil.closeConnection();
		}
	}

	// $12 End
	// $13 Method for Retrieving party for modification.
	public boolean updatePartyDAO(String _partyType, String param[])
			throws Exception {
		logger.debug("WE ARE IN DAO-updatePartyDAO");
		DBUtility dbUtil = new DBUtility();
		String qry = "";
		try {
			if (_partyType.equalsIgnoreCase("Individuals")) {
				qry = RegCommonSQL.IGRS_REGCOMP_UPDATE_PARTY;
			} else {
				qry = RegCommonSQL.IGRS_REGCOMP_UPDATE_ORGA;
			}
			dbUtil.createPreparedStatement(qry);
			boolean result = dbUtil.executeUpdate(param);
			return result;
		} catch (Exception e) {
			logger.error(e);
			return false;
		} finally {
			logger.error("Connection is closed using FINALLY");
			dbUtil.closeConnection();
		}
	} // $13 End

	// imran data
	/**
	 * for getting All Deed Details
	 * 
	 * @return ArrayList
	 */

	public ArrayList getDeedType() {
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.SELECT_DEED_TYPE;
			dbUtility.createStatement();
			ArrayList list = dbUtility.executeQuery(sql);
			mainList = new ArrayList();
			ArrayList subList = null;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonDTO();
				dto.setId(subList.get(0).toString());
				dto.setName(subList.get(1).toString());
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger.error("RegCompDAO in dao getDeedType:" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompDAO in dao getDeedType close:"
						+ e.getStackTrace());
			}
		}
		return mainList;
	}

	
	/**
	 * for getting All Deed Details
	 * 
	 * @return ArrayList
	 */

	public ArrayList getDeedTypeList() {
		ArrayList mainList = new ArrayList();
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.SELECT_DEED_TYPE;
			dbUtility.createStatement();
			mainList = dbUtility.executeQuery(sql);
		 
			 
		} catch (Exception e) {
			logger.error("RegCompDAO in dao getDeedTypeList:" 
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompDAO in dao getDeedTypeList close:"
						+ e.getStackTrace());
			}
		}
		return mainList;
	}

	/**
	 * for getting Instruments list based on deed
	 * 
	 * @param deed
	 * @return ArrayList
	 */

	public ArrayList getInstrument(String deed) {
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.SELECT_DEED_INSTRUMENT +"'"+ deed+"'";
			dbUtility.createStatement();
			ArrayList list = null;
			if (deed.equalsIgnoreCase("[Select]"))
				list = new ArrayList();
			else
				list = dbUtility.executeQuery(sql);
			mainList = new ArrayList();
			ArrayList subList = null;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonDTO();
				dto.setId(subList.get(0).toString());
				dto.setName(subList.get(1).toString());
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger
					.error("RegCompDAO in dao getInstrument:"
							+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompDAO in dao getInstrument close:"
						+ e.getStackTrace());
			}
		}
		return mainList;
	}

	/**
	 * form setting Instrument/Exemptions Details based on deed/instrument
	 * 
	 * @param deed
	 * @param deed1
	 * @param string 
	 * @return ArrayList
	 */

	public ArrayList getExemption(String deed, String deed1, String string) {
		try {
			dbUtility = new DBUtility();
			if (deed1.equalsIgnoreCase("deed"))
				sql = RegCommonSQL.SELECT_DEED_EXEMPTION_ONDEED +"'"+ deed+"'";
			else
				sql = RegCommonSQL.SELECT_DEED_EXEMPTION +"'"+ deed+"' AND DEED_TYPE_ID='"+string+"'";
			dbUtility.createStatement();
			ArrayList list = null;
			if (deed.equalsIgnoreCase("Select"))
				list = new ArrayList();
			else
				list = dbUtility.executeQuery(sql);
			mainList = new ArrayList();
			ArrayList subList = null;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonDTO();
				dto.setId(subList.get(0).toString());
				dto.setName(subList.get(1).toString());
				mainList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("RegCompDAO in dao getExemption:" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompDAO in dao getExemption close:"
						+ e.getStackTrace());
			}
		}
		return mainList;
	}

	/**
	 * returns the given application details
	 * 
	 * @param regNo
	 * @return ApplicantForm
	 */

	public RegInitCompleteDTO getRegApplicationDetails(String regNo) {
		 
		RegInitCompleteDTO dto = new RegInitCompleteDTO();
		try {
			dbUtility = new DBUtility();
			// for selecting data of the regNo
			sql = RegCommonSQL.SELECT_APP_INFO + "'" + regNo + "'";
			dbUtility.createStatement();
			mainList = new ArrayList();
			mainList = dbUtility.executeQuery(sql);
			
			if(mainList!=null) {
				ArrayList list = (ArrayList) mainList.get(0);
				if (list != null) {
					//form = new ApplicantForm();
					dto.setRegNumber(list.get(0).toString());
					// party name is 1
					logger.debug("list.get(2).toString():-"
								+list.get(2).toString());
					dto.setFname(list.get(2).toString());
					dto.setMname(list.get(3).toString());
					dto.setLname(list.get(4).toString());
					dto.setGender(list.get(5).toString());
					dto.setAge(list.get(6).toString() );
					dto.setNationality(list.get(7).toString());
					dto.setIndcountry(list.get(8).toString());
					dto.setIndstatename(list.get(9).toString());
					dto.setInddistrict(list.get(10).toString());
					dto.setIndaddress(list.get(11).toString());
					dto.setPostalCode(list.get(12).toString());
					dto.setIndphno(list.get(13).toString());
					dto.setIndmobno(list.get(14).toString());
					dto.setEmailID(list.get(15).toString());
					dto.setListID(list.get(16).toString());
					dto.setIdno(list.get(17).toString());
					dto.setBname(list.get(18).toString());
					dto.setBaddress(list.get(19).toString());
					
					// organization name is 20
					dto.setFatherName(list.get(21).toString());
					dto.setMotherName(list.get(22).toString());
		
					// selecting parties details related to that regNo
					sql = RegCommonSQL.SELECT_PARTY_DETAILS + "'" + regNo + "'";
					dbUtility.createStatement();
					mainList = new ArrayList();
		
					mainList = dbUtility.executeQuery(sql);
					list = new ArrayList();
					ArrayList subList = null;
					for (int i = 0; i < mainList.size(); i++) {
						subList = (ArrayList) mainList.get(i);
						RegInitCompleteDTO dtoParty = new RegInitCompleteDTO();
						dtoParty.setId(String.valueOf(i + 1));
						dtoParty.setName(subList.get(0).toString());
						dtoParty.setMiddleName(subList.get(1).toString());
						dtoParty.setLastName(subList.get(2).toString());
						list.add(dtoParty);
					}
					dto.setPartiesList(list);
		
					// selecting the payed stamp duty for that regNo
					sql = RegCommonSQL.SELECT_STAMP_DUTY + "'" + regNo + "'";
					dbUtility.createStatement();
					mainList = new ArrayList();
					mainList = dbUtility.executeQuery(sql);
					String paymentId="";
					if (mainList!=null && mainList.size() > 0)
						list = (ArrayList) mainList.get(0);
					if (list != null) {
						dto.setStampDuty(list.get(0).toString());
						dto.setOtherFee(list.get(1).toString());
						int j = Integer.parseInt(list.get(0).toString())
								+ Integer.parseInt(list.get(1).toString());
						dto.setTotal((String.valueOf(j)));
						dto.setEcode(list.get(3).toString());
						//for getting payment details
						paymentId = list.get(2).toString();
					}
					
					//select payment details based on paymentId
					sql = RegCommonSQL.SELECT_PAYMENT_DETAILS + "'" + paymentId + "'";
					dbUtility.createStatement();
					mainList = new ArrayList();
					mainList = dbUtility.executeQuery(sql);
					list = null;
					if (mainList!=null && mainList.size() > 0)
						list = (ArrayList) mainList.get(0);
					if (list != null) {
						dto.setPayId(list.get(0).toString());
						dto.setPayDate(list.get(1).toString());
						dto.setPayAmt(list.get(2).toString());
						dto.setBankName(list.get(3).toString());
						dto.setBankAddress(list.get(4).toString());
					}
					
					// selecting slot booking details of that regNo
					sql = RegCommonSQL.SELECT_SLOT_DETAILS + "'" + regNo + "'";
					dbUtility.createStatement();
					mainList = new ArrayList();
					mainList = dbUtility.executeQuery(sql);
					list = null;
					if (mainList!=null && mainList.size() > 0)
						list = (ArrayList) mainList.get(0);
					if (list != null) {
						dto.setSlotRefId(list.get(0).toString());
						dto.setSlotDate(list.get(1).toString());
						dto.setSlotTime(list.get(2).toString());
					}
				}
			}

		} catch (Exception e) {
			logger.error("RegCompDAO in dao getRegApplicationDetails"
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger
						.error("RegCompDAO in dao getRegApplicationDetails close:"
								+ e.getStackTrace());
			}
		}
		return dto;
	}

	/**
	 * returns the Form Fields for selected deed
	 * 
	 * @param deed
	 * @return ArrayList
	 *//*
/**
	 * returns the Form Fields for selected deed
	 * 
	 * @param deed
	 * @return ArrayList
	 */

	public ArrayList getFormFields(String deed,DBUtility dbUtility) {
		ArrayList map = new ArrayList();
		try {
			 
			sql = RegCommonSQL.SELECT_FORM_FIELDS +"'"+ deed+"'";
			dbUtility.createStatement();
			ArrayList 	list = dbUtility.executeQuery(sql);
			
			ArrayList subList = null;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				RegInitCompleteDTO dto = new  RegInitCompleteDTO();
				dto.setInputAlt(subList.get(0).toString());
				dto.setInputTypeId(subList.get(1).toString());
				dto.setInputlabel(subList.get(2).toString());
				dto.setInputType(subList.get(3).toString());
				if (dto.getInputAlt().equalsIgnoreCase("number"))
					dto.setInputScript("return MyNumeric()");
				if (dto.getInputAlt().equalsIgnoreCase("text"))
					dto.setInputScript("return Alphabetic()");
				map.add(dto);
			}
		} catch (Exception e) {
			logger
					.error("RegCompDAO in dao getFormFields:"
							+ e.getStackTrace());
		}  
		return map;
	}
	/*public ArrayList getFormFields(String deed) {
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.SELECT_FORM_FIELDS +"'"+ deed+"'";
			dbUtility.createStatement();
			ArrayList list = null;
			if (deed.equalsIgnoreCase("Select"))
				list = new ArrayList();
			else
				list = dbUtility.executeQuery(sql);
			mainList = new ArrayList();
			ArrayList subList = null;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonDTO();
				dto.setInputAlt(subList.get(0).toString());
				dto.setInputTypeId(subList.get(1).toString());
				dto.setInputlabel(subList.get(2).toString());
				dto.setInputType(subList.get(3).toString());
				if (dto.getInputAlt().equalsIgnoreCase("number"))
					dto.setInputScript("return MyNumeric()");
				if (dto.getInputAlt().equalsIgnoreCase("text"))
					dto.setInputScript("return Alphabetic()");
				mainList.add(dto);
			}
		} catch (Exception e) {
			logger
					.error("RegCompDAO in dao getFormFields:"
							+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompDAO in dao getFormFields close:"
						+ e.getStackTrace());
			}
		}
		return mainList;
	}
*/
	/**
	 * Storing deed details
	 * 
	 * @param deedDetails
	 * @return ArrayList
	 */

	/*public ArrayList saveDeedDetails(ArrayList deedDetails) {
		ApplicantForm form = null;
		ArrayList txnList = new ArrayList();
		for (int i = 0; i < deedDetails.size(); i++) {
			form = (ApplicantForm) deedDetails.get(i);
			String deedTxn = deedTxnIDgenerator();
			String param[] = new String[3];
			// inserting deed details
			param[0] = deedTxn;
			param[1] = form.getRegNo();
			param[2] = form.getDeed();
			boolean boo = insertData(RegCommonSQL.INSERT_DEED_DETAILS, param);
			// inserting exeption details
			if (boo)
			 param[1] = form.getInstrument();
				logger.info("RegCompDAO   exemption length--------->"+form.getExemption().length());
				
				String tempExem1[] = form.getExemption().split(",");
				for (int j = 0; j < tempExem1.length; j++) {
					param[2] = tempExem1[j];
					logger.info("RegCompDAO   exemption values tempExem1[j]--------->"+tempExem1[j]);
				}
				
				
				if (form.getExemption() != null
						&& form.getExemption().length()>=1) {
					param[1] = form.getInstrument();
					String tempExem[] = form.getExemption().split(",");
					for (int j = 0; j < tempExem.length; j++) {
						param[2] = tempExem[j];
						boo = insertData(RegCommonSQL.INSERT_DEED_EXEM_DETAILS,
								param);
					}
				}
			// Exemption is not mapped to instrument
				else {
				    logger.info("RegCompDAO   saveDeedDetails()  Exemption is null");
				    param[1] = form.getInstrument();
				    param[2] = null;//-- no exemtions
				    boo = insertData(RegCommonSQL.INSERT_DEED_EXEM_DETAILS,
						param);
				}

			// inserting additional attribute details
			if (boo)
				if (form.getPageTitle() != null
						&& !form.getPageTitle().equalsIgnoreCase("")) {
					String tempAttr[] = form.getPageTitle().split(",");
					for (int j = 0; j < tempAttr.length; j = j + 2) {
						param[1] = tempAttr[j];
						param[2] = tempAttr[j + 1];
						boo = insertData(RegCommonSQL.INSERT_DEED_ATTR_DETAILS,
								param);
					}
				}

			if (boo)
				txnList.add(deedTxn);
		}

		return txnList;
	}*/

	/**
	 * to insert data into database
	 * 
	 * @param query
	 * @param param
	 * @return status(boolean)
	 */

	public boolean insertData(String query, String param[],
			DBUtility dbUtility ) {
		try {
			 
			dbUtility.createPreparedStatement(query);
			return dbUtility.executeUpdate(param);
		} catch (Exception e) {
			try {
				//dbUtility.rollback();
			} catch (Exception e1) {
				logger.error("RegCompDAO in dao insertData1:"
						+ e.getStackTrace()); 
				// e.printStackTrace();
			}
			logger.error("RegCompDAO in dao insertData:" + e.getStackTrace());
			// e.printStackTrace();
		} 

		return false;
	}
	public boolean insertData(String query, String param[]
			) {
		DBUtility dbUtility  = null;
		try {
			dbUtility  = new DBUtility();
			dbUtility.createPreparedStatement(query);
			return dbUtility.executeUpdate(param);
		} catch (Exception e) {
			try {
				//dbUtility.rollback();
			} catch (Exception e1) {
				logger.error("RegCompDAO in dao insertData1:"
						+ e.getStackTrace()); 
				// e.printStackTrace();
			}
			logger.error("RegCompDAO in dao insertData:" + e.getStackTrace());
			// e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception x) {
			}
		}

		return false;
	}

	/**
	 * for generating deed txn id
	 * 
	 * @return deedTxn ID
	 */

	private String deedTxnIDgenerator() {
		return "deedTxn" + new Date().getTime();
	}

	/**
	 * getting deed details for given Deed Id
	 * 
	 * @param deedId
	 * @return ApplicantForm
	 */

	/*public ApplicantForm getDeedDetails(String deedId) {
		ApplicantForm form = new ApplicantForm();
		String deed = null;
		CommonDTO commonDTO = null;
		try {
			dbUtility = new DBUtility();
			String query = RegCommonSQL.SELECT_DEED_DETAILS + "'" + deedId
					+ "'";
			dbUtility.createStatement();
			ArrayList mainList = dbUtility.executeQuery(query);
			if (mainList.size() > 0) {
				commonDTO = new CommonDTO();
				mainList = (ArrayList) mainList.get(0);
				form.setRegNo(mainList.get(0).toString());
				deed = mainList.get(1).toString();

				form.setDeed(deed);
				form.setBname(mainList.get(2).toString());

				// Exemption values getting from database
				query = RegCommonSQL.SELECT_DEED_EXEM_DETAILS + "'" + deedId
						+ "'";
				dbUtility.createStatement();
				mainList = dbUtility.executeQuery(query);
				String temp[] = new String[mainList.size()];

				for (int i = 0; i < mainList.size(); i++) {
					ArrayList list = (ArrayList) mainList.get(i);
					form.setInstrument(list.get(0).toString());
					temp[i] = list.get(1).toString();
				}
				commonDTO.setInstList(getInstrument(deed));
				String inst = form.getInstrument();
				if (inst != null) {
					ArrayList _list = getExemption(inst, "instrument",deedId);
					commonDTO.setExmpList(getExemptions(_list, temp));
				} else {
					ArrayList exmp = getExemptions(getExemption(deed, "deed",null),
							temp);
					commonDTO.setExmpList(exmp);
				}

				// form field values getting from database
				query = RegCommonSQL.SELECT_DEED_ATTR_DETAILS + "'" + deedId
						+ "'";
				dbUtility.createStatement();
				mainList = dbUtility.executeQuery(query);
				ArrayList _tempList = getFormFields(deed);
				try {
					for (int i = 0; i < mainList.size(); i++) {
						ArrayList list1 = (ArrayList) mainList.get(i);
						_tempList = getFormFields(_tempList, (String) list1
								.get(0), (String) list1.get(1));
					}
				} catch (Exception e) {
					e.getStackTrace();
				}
				form.setFormFields(_tempList);
				form.setCommonDTO(commonDTO);
			}

		} catch (Exception e) {
			e.getStackTrace();
			logger.error("RegCompDAO in dao getDeedDetails:"
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompDAO in dao getDeedDetails close:"
						+ e.getStackTrace());
			}
		}
		return form;
	}
*/
	public RegInitCompleteDTO getDeedDetails(String deedId) throws Exception{
		RegInitCompleteDTO dto = new RegInitCompleteDTO();
		DBUtility dbUtil = new DBUtility();
		try {
			String[] deedType = new String[2];
			logger.debug("deedId:-"+deedId);
			
			String[] deedAry = deedId !="" ? deedId.split("~"):null ;
			
			deedType[0] = deedAry !=null ? deedAry[0]:""; 
			deedType[1] = "A";	
			logger.debug("deedType[0]:-"+deedType[0]);
			common = new IGRSCommon();
			ArrayList instList = common.getInstruments(deedType);
			ArrayList list = new ArrayList();
			if(instList!=null) {
				for(int i = 0;i<instList.size();i++) {
					ArrayList listInst = (ArrayList)instList.get(i);
					RegInitCompleteDTO dtoInst = new RegInitCompleteDTO();
					dtoInst.setInstrumentId(listInst.get(0).toString());
					dtoInst.setInstrumentName(listInst.get(1).toString());
					logger.debug("listInst.get(1).toString():"
							+listInst.get(1).toString());
					list.add(dtoInst);
				}
			}
			dto.setInstList(list);
			dto.setFormList(getFormFields(deedType[0], dbUtil));
		} catch (Exception e) {
			e.getStackTrace();
			logger.error("RegCompDAO in dao getDeedDetails:"
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompDAO in dao getDeedDetails close:"
						+ e.getStackTrace());
			}
		}
		return dto;
	}
	/**
	 * form setting selected Exemptions Details(in form)
	 * 
	 * @param exemption
	 * @param list
	 * @return ArrayList
	 */

	public ArrayList getExemptions(ArrayList exemption, String list[]) {
		CommonDTO dto = null;
		for (int i = 0; i < exemption.size(); i++) {
			dto = (CommonDTO) exemption.get(i);
			for (int j = 0; j < list.length; j++) {
				if (dto.getId().equalsIgnoreCase(list[j])) {
					dto.setChecked("checked");
					if (dto.getId().equalsIgnoreCase("number"))
						dto.setScript("return MyNumeric()");
					if (dto.getId().equalsIgnoreCase("text"))
						dto.setScript("return Alphabetic()");
				}
			}
		}
		return exemption;
	}

	/**
	 * for setting the dynamic fields infometion
	 * 
	 * @param fields
	 * @param id
	 * @param value
	 * @return ArrayList
	 */

	public ArrayList getFormFields(ArrayList fields, String id, String value) {
		CommonDTO dto = null;
		for (int i = 0; i < fields.size(); i++) {
			dto = (CommonDTO) fields.get(i);
			if (dto.getName().equalsIgnoreCase(id)) {
				if (value.equalsIgnoreCase("on"))
					dto.setChecked("checked");
				else
					dto.setChecked("value=" + value);
			}

		}
		return fields;
	}

	/**
	 * for updating the details of selected deed
	 * 
	 * @param appForm
	 * @return boolean(status)
	 */

	/*public boolean updateDeedDetails(ApplicantForm appForm) {
		deleteDeedDetails(appForm);
		String deedTxn = appForm.getNofProperty();
		String param[] = new String[3];
		// inserting deed details
		param[0] = deedTxn;
		param[1] = appForm.getRegNo();
		param[2] = appForm.getDeed();
		boolean boo = insertData(RegCommonSQL.INSERT_DEED_DETAILS, param);
		// inserting exeption details
		if (boo)
		    logger.info("RegCompDAO   updateDeedDetails() boo--------->"+boo);
		    logger.info("RegCompDAO   updateDeedDetails()  Exemption before if "+appForm.getExemption());
		    
			if (appForm.getExemption() != null
					&& !appForm.getExemption().equalsIgnoreCase("")) {
			    logger.info("RegCompDAO   updateDeedDetails()  Exemption inside not null if "+appForm.getExemption());
				param[1] = appForm.getInstrument();
				String tempExem[] = appForm.getExemption().split(",");
				for (int j = 0; j < tempExem.length; j++) {
					param[2] = tempExem[j];
					boo = insertData(RegCommonSQL.INSERT_DEED_EXEM_DETAILS,
							param);
				}
			}
		// Exemption is not mapped to instrument
			else if (appForm.getExemption() == null
				&& appForm.getExemption().equalsIgnoreCase("")) {
			    logger.info("RegCompDAO   updateDeedDetails()  Exemption is null");
			    param[1] = appForm.getInstrument();
			    param[2] = null;//-- no exemtions
			    boo = insertData(RegCommonSQL.INSERT_DEED_EXEM_DETAILS,
					param);
			}

		// inserting additional attribute details
		if (boo)
			if (appForm.getFname() != null
					&& !appForm.getFname().equalsIgnoreCase("")) {
				String tempAttr[] = appForm.getFname().split(",");
				for (int j = 0; j < tempAttr.length; j = j + 2) {
					param[1] = tempAttr[j];
					param[2] = tempAttr[j + 1];
					boo = insertData(RegCommonSQL.INSERT_DEED_ATTR_DETAILS,
							param);
				}
			}

		return boo;
	}*/

	/**
	 * for deleting the selected deed details
	 * 
	 * @param appForm
	 * @return boolean(status)
	 */

	public ArrayList deleteDeedDetails(RegInitCompleteDTO dto) {
		String deedID = dto.getDeedTxnId();
		boolean boo = false;
		String arr[] = { deedID };
		ArrayList listRetun = new ArrayList();
		try {
			dbUtility = new DBUtility();
			String query = RegCommonSQL.DELETE_DEED_ATTR_DETAILS;
			dbUtility.createPreparedStatement(query);
			boo = dbUtility.executeUpdate(arr);
			logger.debug("After Deed Attr:-"+boo);
		    //if (boo) {
			query = RegCommonSQL.DELETE_DEED_EXEM_DETAILS;
			dbUtility.createPreparedStatement(query);
			boo = dbUtility.executeUpdate(arr);
			logger.debug("After Deed Exem:-"+boo);
			//}
			//if (boo) {
			query = RegCommonSQL.DELETE_DEED_DETAILS;
			dbUtility.createPreparedStatement(query);
			boo = dbUtility.executeUpdate(arr);
			logger.debug("After Deed:-"+boo);
			//}
			if (boo) {
				dbUtility.commit();
			}else {
				dbUtility.rollback();
			}
			String[] regParam = new String[1] ;
			regParam[0] = dto.getRegNo();
			dbUtility.createPreparedStatement(RegCommonSQL.
						SELECT_REG_DEED_DETAIL);
			ArrayList deedTxnList = dbUtility.executeQuery(regParam);
			
			if(deedTxnList != null ) {
				for(int i =0;i<deedTxnList.size();i++) {
					ArrayList list = (ArrayList) deedTxnList.get(i);
					RegInitCompleteDTO dtoList = new RegInitCompleteDTO();
					dtoList.setDeedTxnId(list.get(0).toString());
					listRetun.add(dtoList);
				}
			}
		} catch (Exception e) {
			logger.error("RegCompDAO in dao deleteDeedDetails close:"
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompDAO in dao deleteDeedDetails close:"
						+ e.getStackTrace());
			}
		}
		return listRetun;
	}

	/**
	 * for save Deed details
	 * 
	 * @param appForm
	 * @return ArrayList (txn Ids)
	 */

	
	
	public ArrayList saveDeedDetail(RegInitCompleteDTO dto) 
					throws Exception {

		ArrayList txnList = new ArrayList();
		String deedTxn = deedTxnIDgenerator();
		logger.debug("deedTxn:-"+deedTxn);
		String param[] = new String[3];
		DBUtility dbUtil = new DBUtility();
		try {
			// inserting deed details
			param[0] = deedTxn;
			param[1] = dto.getRegNo();
			
			String deedId = dto.getDeedId();
			String[] deedAry = deedId !="" ? deedId.split("~"):null ;
			
			param[2] = deedAry !=null ? deedAry[0]:""; 
			
			
			boolean boo = insertData(RegCommonSQL.INSERT_DEED_DETAILS,
						param,dbUtil);
			
			// inserting exeption details
			if (boo) {
				param[1] = dto.getInstrumentId();
				
			    if (dto.getHdnExemption() != null
			    		&& !dto.getHdnExemption().equalsIgnoreCase("")) {
			    	String tempExem[] = dto.getHdnExemption().split(",");
					for (int j = 0; j < tempExem.length; j++) {
						param[2] = tempExem[j];
						boo = insertData(RegCommonSQL.INSERT_DEED_EXEM_DETAILS,
								param,dbUtil);
					}
			    }else {
			    	param[2] = "";
			    	boo = insertData(RegCommonSQL.INSERT_DEED_EXEM_DETAILS,
							param,dbUtil);
			    }

			    //inserting additional attribute details
			    if (boo) {
			    		
			    	 HashMap map = dto.getMapForm();
			    	 Iterator it = map.entrySet().iterator();
			    	 while(it.hasNext()) {
						Map.Entry me = (Map.Entry) it.next();
						param[1] = me.getKey().toString();
						logger.debug("form Value:-"+ me.getValue());
						param[2] = me.getValue().toString();
						boo = insertData(RegCommonSQL.INSERT_DEED_ATTR_DETAILS,
										param, dbUtil);
			    	 }
			    	 
				}
			} 
			 
			if(boo) {
			  	dbUtil.commit();
			}else {
			   	dbUtil.rollback();
			}
			
			String[] regParam = new String[1] ;
			regParam[0] = dto.getRegNo();
			dbUtil.createPreparedStatement(RegCommonSQL.
						SELECT_REG_DEED_DETAIL);
			ArrayList deedTxnList = dbUtil.executeQuery(regParam);
			
			if(deedTxnList != null ) {
				for(int i =0;i<deedTxnList.size();i++) {
					ArrayList list = (ArrayList) deedTxnList.get(i);
					RegInitCompleteDTO dtoList = new RegInitCompleteDTO();
					dtoList.setDeedTxnId(list.get(0).toString());
					txnList.add(dtoList);
				}
			}
		}catch(Exception x) {
			logger.debug(x);
			dbUtil.rollback();
		}finally {
			dbUtil.closeConnection();
		}
		
		return txnList;
	}

	/**
	 * for getting Single Deed Details
	 * 
	 * @return CommonDTO
	 */

	public CommonDTO getDeed(String deedId) {
		try {
			dbUtility = new DBUtility();
			sql = RegCommonSQL.SELECT_DEED + "'" + deedId + "'";
			dbUtility.createStatement();
			ArrayList list = dbUtility.executeQuery(sql);
			mainList = new ArrayList();
			ArrayList subList = null;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonDTO();
				dto.setId(subList.get(0).toString());
				dto.setName(subList.get(1).toString());
			}
		} catch (Exception e) {
			logger.error("RegCompDAO in dao getDeed:" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompDAO in dao getDeed close:"
						+ e.getStackTrace());
			}
		}
		return dto;
	}

	/**
	 * returns the selected deeds for given Reg Id
	 * 
	 * @param regId
	 * @return ArrayList
	 */

	public HashMap getDeedsList(String regId) {
		HashMap mainMap = new HashMap();
		try {
			dbUtility = new DBUtility();
			String arr[] = { regId };
			dbUtility
					.createPreparedStatement(RegCommonSQL.SELECT_DEED_BY_REGID);
			ArrayList list = dbUtility.executeQuery(arr);
			
			
			
			ArrayList subList = null;
			for (int i = 0; i < list.size(); i++) {
				subList = (ArrayList) list.get(i);
				dto = new CommonDTO();
				dto.setName(subList.get(1).toString() + " - " + subList.get(2).toString());
				dto.setId(subList.get(2).toString());
				
				mainMap.put(subList.get(2).toString(),dto);
			}
		} catch (Exception e) {
			logger.error("RegCompDAO in dao getDeedsList:" + e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompDAO in dao getDeedsList close:"
						+ e.getStackTrace());
			}
		}
		return mainMap;
	}

	/**
	 * returns the selected Properties list for given Reg Id
	 * 
	 * @param regId
	 * @return ArrayList
	 */

	public ArrayList getPropertyListByRegId(String[] param) {
		try {
			dbUtility = new DBUtility();
			//String arr[] = { regId };
			dbUtility
					.createPreparedStatement(RegCommonSQL.SELECT_PROP_BY_REGID);
			ArrayList list = dbUtility.executeQuery(param);
			mainList = new ArrayList();
			ArrayList subList = new  ArrayList();
			if(list!=null) {
				for (int i = 0; i < list.size(); i++) {
					subList = (ArrayList) list.get(i);
					// Added By Aruna
					if(!isPropertyMapped((String)subList.get(0),dbUtility))
					{
					dto = new CommonDTO();
					dto.setPropertyTxnId(subList.get(0).toString());
					mainList.add(dto);
					}
				}
			} 
		} catch (Exception e) {
			logger.error("RegCompDAO in dao getPropertyListByRegId:"
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompDAO in dao getPropertyListByRegId close:"
						+ e.getStackTrace());
			}
		}
		return mainList;
	}
	public ArrayList getPropertyList(String[] param) {
		try {
			dbUtility = new DBUtility();
			//String arr[] = { regId };
			dbUtility
					.createPreparedStatement(
							RegCommonSQL.SELECT_PROP_BY_REGID_LIST);
			ArrayList list = dbUtility.executeQuery(param);
			mainList = new ArrayList();
			ArrayList subList = new  ArrayList();
			if(list!=null) {
				for (int i = 0; i < list.size(); i++) {
					subList = (ArrayList) list.get(i);
					dto = new CommonDTO();
					dto.setPropertyTxnId(subList.get(0).toString());
					mainList.add(dto);
				}
			} 
		} catch (Exception e) {
			logger.error("RegCompDAO in dao getPropertyListByRegId:"
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompDAO in dao getPropertyListByRegId close:"
						+ e.getStackTrace());
			}
		}
		return mainList;
	}

	/**
	 * based on deed txn it will returns the number of dependent properties
	 * 
	 * @param deed
	 * @return NofProperty
	 */

	public String getNofProperty(String deed) {
		try {
			dbUtility = new DBUtility();
			ArrayList list;
			String configParamAttrID = "ATT_136";
			String configParamStatus = "A";
			//ATT_005 IS FOR No.of PROPERTIES Attribute ID
			dbUtility.createPreparedStatement(RegCommonSQL.SELECT_PROP_DEED_ATTR);
			list = dbUtility.executeQuery(new String[]{configParamAttrID, configParamStatus});
			String propDeedAttr;
			if (list.size() > 0) {
				propDeedAttr = (String) ((ArrayList)list.get(0)).get(0);
			} else {
				propDeedAttr = "ATT_005";
			}
			
			String arr[] = { propDeedAttr,deed };
			dbUtility
					.createPreparedStatement(RegCommonSQL.SELECT_NO_PROP_BY_DEED);
			list = dbUtility.executeQuery(arr);
			ArrayList subList = null;
			if (list.size() > 0) {
				subList = (ArrayList) list.get(0);
				return subList.get(0).toString();
			}
		} catch (Exception e) {
			logger.error("RegCompDAO in dao getNofProperty:"
					+ e.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCompDAO in dao getNofProperty close:"
						+ e.getStackTrace());
			}
		}
		return "0";
	}

	/**
	 * for updating the mapping details
	 * 
	 * @param regNo
	 */
	public void deleteDeedToPropAndTranMap(String regNo) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility
					.createPreparedStatement(RegCommonSQL.DELETE_DEED_MAP_BY_REGID);
			String arr[] = { regNo };
			boo = dbUtility.executeUpdate(arr);
			if (boo) {
				dbUtility.commit();
			}else {
				dbUtility.rollback();
			}
		} catch (Exception e2) {
			try {
				dbUtility.rollback();
			} catch (Exception e) {
				logger
						.error("RegCompDAO in dao saveDeedToPropAndTranMap with params:"
								+ e.getStackTrace());
			}
			logger
					.error("RegCompDAO in dao saveDeedToPropAndTranMap with params:"
							+ e2.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger
						.error("RegCompDAO in dao saveDeedToPropAndTranMap with params close:"
								+ e.getStackTrace());
			}
		}

	}

	/**
	 * save Deed To Property or Transacting Party Mapping
	 * 
	 * @param params
	 * @return boolean
	 */

	public boolean saveDeedToPropAndTranMap(String[] params) {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			dbUtility
					.createPreparedStatement(RegCommonSQL.SAVE_MAPPING_DETAILS);
			boo = dbUtility.executeUpdate(params);
			if (boo) {
				dbUtility.commit();
			}
			else {
				dbUtility.rollback();
			}
		} catch (Exception e2) {
			try {
				dbUtility.rollback();
			} catch (Exception e) {
				logger
						.error("RegCompDAO in dao saveDeedToPropAndTranMap with params:"
								+ e.getStackTrace());
			}
			logger
					.error("RegCompDAO in dao saveDeedToPropAndTranMap with params:"
							+ e2.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger
						.error("RegCompDAO in dao saveDeedToPropAndTranMap with params close:"
								+ e.getStackTrace());
			}
		}
		return boo;
	}

	public boolean saveDeedToPropAndTranMap(String[] params,
			DBUtility dbUtility	) {
		boolean boo = false;
		try {
			 
			dbUtility
					.createPreparedStatement(
							RegCommonSQL.SAVE_MAPPING_DETAILS);
			boo = dbUtility.executeUpdate(params);
			 
		} catch (Exception e2) {
			 
			logger.debug(e2);
		}  
		return boo;
	}

	
	/**
	 * save Deed To Property or Transacting Party Mapping
	 * 
	 * @param params
	 * @param nofProperty
	 * @param size
	 * @return boolean
	 */

	public HashMap saveDeedToPropAndTranMap(CommonDTO dto,
			String nofProperty,HashMap mapDeed) {
		boolean boo = false;
		 
		try {
			dbUtility = new DBUtility();
			int no = 0;
			if (nofProperty != null)
				no = Integer.parseInt(nofProperty);
			
			String[] params = new String[3];
			System.out.println("no.------>"+no);
			if (no > 0) {
				System.out.println("no------>"+no);
				dbUtility
					.createPreparedStatement(RegCommonSQL.INSERT_DEED_TRAN_WITH_REGID);
			}
			else {
				dbUtility
					.createPreparedStatement(RegCommonSQL.INSERT_DEED_PROP_WITH_REGID);
			}	

			String deedId = dto.getDeed();
			String propertyId = dto.getHdnPropertyTxnId();
			if(propertyId!=null) {
				String[] property = propertyId.split(",");
				if(property!=null) {
					for(int i=0;i<property.length;i++) {
						params[0] = dto.getRegNo();
						params[1] = deedId;
						params[2] = property[i];
						boo = dbUtility.executeUpdate(params);
					}
				}
			}
			
			//boo = dbUtility.executeUpdate(params);
				
			logger.debug("deedId:-"+deedId);
			if (boo) {
				mapDeed.remove(deedId);
				dbUtility.commit();
			}else{
				dbUtility.rollback();
			}

		} catch (Exception e2) {
			try {
				dbUtility.rollback();
			} catch (Exception e) {
				logger.error("RegCompDAO in dao saveDeedToPropAndTranMap:"
						+ e.getStackTrace());
			}
			logger.error("RegCompDAO in dao saveDeedToPropAndTranMap:"
					+ e2.getStackTrace());
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger
						.error("RegCompDAO in dao saveDeedToPropAndTranMap close:"
								+ e.getStackTrace());
			}
		}
		return mapDeed;
	}

	/**
	 * to get the details of Deed , Property and Transaction Parties details
	 * 
	 * @param regNo
	 * @return ArrayList
	 */

	public ArrayList getDeedList(String regNo) throws Exception {
		ArrayList dList = new ArrayList();
		DBUtility dbUtil = new DBUtility();
		try {
			String arr[] = { regNo };
			// Edited By aruna
			System.out.println("qr:"+RegCommonSQL.SELECT_DEED_TAN_BY_REGID);
			dbUtil.createPreparedStatement(RegCommonSQL.SELECT_DEED_TAN_BY_REGID);
			ArrayList deedList = dbUtil.executeQuery(arr);
			
			if(deedList!=null) {
				for(int i=0;i<deedList.size();i++) {
					ArrayList list = (ArrayList)deedList.get(i);
					CommonDTO dto = new CommonDTO();
					dto.setDeed((String)list.get(0));
					// Added By Aruna
					dto.setCheckProperty((String)list.get(1));
					dList.add(dto);
				}
			}	
		}catch(Exception x) {
			logger.debug(x);
		}finally {
			dbUtil.closeConnection();
		}
		
		return dList;
	}
	public ArrayList getPropertyList(String deedTxnId,String regNo) throws Exception {
		ArrayList dList = new ArrayList();
		DBUtility dbUtil = new DBUtility();
		try {
			String arr[] = new String[2];
			arr[0] = regNo;
			arr[1] = deedTxnId;
			
			dbUtil.createPreparedStatement(RegCommonSQL.SELECT_PROP_TAN_BY_REGID);
			ArrayList deedList = dbUtil.executeQuery(arr);
			
			if(deedList!=null) {
				for(int i=0;i<deedList.size();i++) {
					ArrayList list = (ArrayList)deedList.get(i);					
						CommonDTO dto = new CommonDTO();
						logger.debug("PropertyList :-"+list.get(0));
						dto.setPropId((String)list.get(0));
						dList.add(dto);					
				}
			}
			 
			 
		}catch(Exception x) {
			logger.debug(x);
			x.printStackTrace();
		}finally {
			dbUtil.closeConnection();
		}
		
		return dList;
	}
	public ArrayList getPartyList(String deedTxnId,String regNo) throws Exception {
		ArrayList dList = new ArrayList();
		DBUtility dbUtil = new DBUtility();
		try {
			String arr[] = new String[1];
			arr[0] = regNo;
			//arr[1] = deedTxnId;
			
			
			dbUtil.createPreparedStatement(RegCommonSQL.SELECT_PARTY_BY_REGID);
			ArrayList deedList = dbUtil.executeQuery(arr);
			
			if(deedList!=null) {
				for(int i=0;i<deedList.size();i++) {
					ArrayList list = (ArrayList)deedList.get(i);
					CommonDTO dto = new CommonDTO();
					logger.debug("partyTxnId:-"+list.get(0));
					dto.setPartyTxnID((String)list.get(0));
					dList.add(dto);
					
				}
			}
			 
			 
		}catch(Exception x) {
			logger.debug(x);
			x.printStackTrace();
		}finally {
			dbUtil.closeConnection();
		}
		
		return dList;
	}
	public ArrayList getPartyTypeList(String deedTxnId
			,String regNo ) throws Exception {
		ArrayList dList = new ArrayList();
		DBUtility dbUtil = new DBUtility();
		try {
			String arr[] = new String[2];
			arr[0] = regNo;
			arr[1] = deedTxnId;
			
			
			dbUtil.createPreparedStatement(RegCommonSQL.SELECT_PARTY_TYPE);
			String sql=RegCommonSQL.SELECT_PARTY_TYPE;
			ArrayList deedList = dbUtil.executeQuery(arr);
			
			if(deedList!=null) {
				for(int i=0;i<deedList.size();i++) {
					ArrayList list = (ArrayList)deedList.get(i);
					CommonDTO dto = new CommonDTO();
					dto.setPartyType((String)list.get(0));
					dto.setPartyTypeId((String)list.get(0)
							+"#"+(String)list.get(1)
							+"#"+(String)list.get(2));
					 
					dList.add(dto);
				}
			}
			 
			 
		}catch(Exception x) {
			logger.debug(x);
		}finally {
			dbUtil.closeConnection();
		}
		
		return dList;
	}
	
	public ArrayList getDeedPropAndTreanMapping(String regNo) throws Exception{
		ArrayList mainList = new ArrayList();
		DBUtility dbUtility = new DBUtility();
		try {
			String arr[] = { regNo };
			//dbUtility = new DBUtility();
			ArrayList masterList = new ArrayList();
			CommonDTO dto = null;
			CommonDTO dto1 = null;
			dbUtility
					.createPreparedStatement(RegCommonSQL.SELECT_DEED_PROP_TAN_BY_REGID);
			ArrayList list = dbUtility.executeQuery(arr);
			dbUtility
					.createPreparedStatement(RegCommonSQL.SELECT_PARTY_BY_REGID);
			ArrayList tranList = dbUtility.executeQuery(arr);
			for (int i = 0; list.size() > i; i++) {
				ArrayList list1 = (ArrayList) list.get(i);
				if (list1.get(3) == null) {
					list1.remove(3);
					list1.add(3, tranList);
				}
				masterList.add(list1);
			}
			for (int i = 0; i < masterList.size(); i++) {
				list = new ArrayList();
				list = (ArrayList) masterList.get(i);
				dto = new CommonDTO();
				dto.setRegNo((String) list.get(0));
				dto.setDeed((String) list.get(1));
				dto.setPropId((String) list.get(2));
				if (dto.getPropId() == null)
					dto.setPropId("");
				ArrayList subList = new ArrayList();
				try {
					subList = (ArrayList) list.get(3);
				} catch (Exception e) {
					ArrayList t = new ArrayList();
					t.add(list.get(3));
					subList.add(t);
				}
				ArrayList tlist = new ArrayList();
				if (subList != null)
					for (int j = 0; j < subList.size(); j++) {
						dto1 = new CommonDTO();
						ArrayList t = (ArrayList) subList.get(j);
						dto1.setPartyId((String) t.get(0));
						tlist.add(dto1);
					}
				dto.setDeedList(tlist);
				tranList = new ArrayList();
				String query = RegCommonSQL.SELECT_DEED_BY_DEEDTXN_ID + "'"
						+ dto.getDeed() + "'";
				dbUtility.createStatement();
				String deed = dbUtility.executeQry(query);
				arr[0] = deed;
				dbUtility
						.createPreparedStatement(RegCommonSQL.SELECT_PARTY_BY_DEEDTXN_ID);
				ArrayList plist = dbUtility.executeQuery(arr);
				tlist = new ArrayList();
				if (plist != null)
					for (int j = 0; j < plist.size(); j++) {
						dto1 = new CommonDTO();
						ArrayList t = (ArrayList) plist.get(j);
						dto1.setPartyType((String) t.get(1));
						dto1.setPartyId((String) t.get(2));
						tlist.add(dto1);
					}
				dto.setInstList(tlist);
				mainList.add(dto);
			}
		} catch (Exception e2) {
			 logger.debug(e2);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.debug(e);
			}
		}
		return mainList;
	}

	/**
	 * pav Code getting the compliance list.
	 * 
	 * @return
	 * @throws Exception
	 */

	public ArrayList getComplList() {
		ArrayList compList = new ArrayList();
		String SQL;
		logger
				.info("Wipro in RegCompDAO - getComplList() before EXCUTIN QUERY");
		SQL = RegCommonSQL.COMPLIANCE_LIST_QUERY;
		try {
			DBUtility dbUtility = new DBUtility();
			dbUtility.createStatement();
			compList = dbUtility.executeQuery(SQL);
			logger
					.info("Wipro in RegCompDAO - getComplList() AFTER EXCUTIN QUERY"
							+ SQL);
			logger
					.info("Wipro in RegCompDAO - getComplList() AFTER EXCUTIN QUERY list values"
							+ compList);
		} catch (Exception e) {
			logger
					.error("Wipro in RegCompDAO -Exception in calling at DAO Class at getComplList ()  "
							+ e);
		}

		return compList;
	}

	/**
	 * inserting compliance details.
	 * 
	 * @param form
	 * @return
	 */
	public boolean insComplDet(String[] param) {
		boolean boo = false;
		String SQL;
		SQL = RegCommonSQL.COMPLIANCE_INS_QUERY;
		try {
			DBUtility dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			boo = dbUtility.executeUpdate(param);

			if (boo) {
				dbUtility.commit();
			}
		} catch (Exception e) {
			logger.error("Wipro in RegCompDAO -Exception in  insComplDet ()  "
					+ e);
		}

		return boo;
	}

	public ArrayList getPrDeedDet(String regNO) {
		ArrayList pDeedList = new ArrayList();
		String SQL;
		// Edited By Aruna
		//SQL = RegCommonSQL.PROP_DEED_LIST_QUERY + "'" + regNO + "'";
		SQL = RegCommonSQL.PROP_DEED_LIST_QUERY + "'" + regNO + "'" + " order by reg_deed_txn_id,property_txn_id ";
		// logger.debug(SQL);
		try {
			DBUtility dbUtility = new DBUtility();
			dbUtility.createStatement();
			pDeedList = dbUtility.executeQuery(SQL);
		} catch (Exception e) {
			logger
					.error("Wipro in RegCompDAO -Exception in calling at DAO Class at getPrDeedDet ()  "
							+ e);
		}

		return pDeedList;
	}

	public ArrayList getDeedTypeId(String deedId) {
		ArrayList deedTypeList = new ArrayList();
		String SQL;
		SQL = RegCommonSQL.DEED_TYPE_LIST_QUERY + "'" + deedId + "'";
		try {
			DBUtility dbUtility = new DBUtility();
			dbUtility.createStatement();
			deedTypeList = dbUtility.executeQuery(SQL);
			// logger.debug(SQL);
		} catch (Exception e) {
			logger
					.error("Wipro in RegCompDAO -Exception in calling at DAO Class at getDeedTypeId ()  "
							+ e);
		}

		return deedTypeList;
	}

	public ArrayList getInsExemList(String deed) {
		ArrayList insExemList = new ArrayList();
		String SQL;
		SQL = RegCommonSQL.INS_EXEM_LIST_QUERY + "'" + deed + "'";
		try {
			DBUtility dbUtility = new DBUtility();
			dbUtility.createStatement();
			insExemList = dbUtility.executeQuery(SQL);
			// logger.debug(SQL);
			// logger.debug("RegCompDAO -- getInsExemList"+insExemList);
		} catch (Exception e) {
			logger
					.error("Wipro in RegCompDAO -Exception in calling at DAO Class at getInsExemList ()  "
							+ e);
		}

		return insExemList;
	}

	/**
	 * 
	 * @param deed
	 * @param instrument
	 * @param exemption
	 * @param propId
	 * @return
	 */

	public String[] getStampDuty(String deed, String instrument,
			String exemption, String propId) {

		String stampDuty[] = null;
		try {
			common = new IGRSCommon();
			stampDuty = common.getRegStampDuty(deed, instrument, exemption,
					propId);
		} catch (Exception e) {
			logger.info("Wipro in RegCompDAO -Exception  getStampDuty()");
		}

		return stampDuty;

	}

	public ArrayList getOthersDuty(String functionId, String serviceId,
			String userId) {

		ArrayList othersFee = new ArrayList();
		try {
			common = new IGRSCommon();
			othersFee = common.getOthersFeeDuty(functionId, serviceId, userId);
		} catch (Exception e) {
			logger.info("Wipro in RegCompDAO -Exception  getStampDuty()");
		}

		return othersFee;

	}

	/**
	 * Hari venkata
	 */

	/**
	 * 
	 * @param _propDetails
	 * @return
	 * @throws Exception
	 */
	// property
	public boolean storePropertyDetails(
			String[] _propDetails, DBUtility dbUtility)
			throws Exception {

		boolean status = false;
		try {

			String insertPropDetails = RegCommonSQL.INSERT_REG_PROPERTY_DETAILS;
			logger.info(insertPropDetails);
			dbUtility.createPreparedStatement(insertPropDetails);
			status = dbUtility.executeUpdate(_propDetails);
			logger.info("--status of property details--<><>" + status);
		} catch (Exception e) {
			dbUtility.rollback();
			logger.error(e);

		}
		return status;
	}

	public boolean storePropertyFloorDetails(
			String[] _floorDetails, DBUtility dbUtility)
			throws Exception {

		boolean status = false;
		try {

			String insertPropDetails = RegCommonSQL.INSERT_REG_PROPERTY_FLOOR_DTLS;
			logger.debug(insertPropDetails);
			dbUtility.createPreparedStatement(insertPropDetails);
			status = dbUtility.executeUpdate(_floorDetails);
			logger.debug("--status of property details--<><>" + status);

		} catch (Exception e) {
			dbUtility.rollback();
			logger.error(e);

		}  
		return status;
	}

	public boolean storePropertyValueDetails(
				String[] _propValues, DBUtility dbUtility)
			throws Exception {

		boolean status = false;
		try {
			String insertPropValues = RegCommonSQL.INSERT_REG_PROPERTY_VALUE_DTLS;
			logger.debug(insertPropValues);
			dbUtility.createPreparedStatement(insertPropValues);
			status = dbUtility.executeUpdate(_propValues);
			logger.debug("--status of property values--<><>" + status);

		} catch (Exception e) {
			logger.error(e);

		}
		return status;
	}

	public boolean storePropertySubclauseDetails(
				String[] _propSubclausese, DBUtility dbUtility)
			throws Exception {

		boolean status = false;
		try {

			 
			String insertPropSubclauses = RegCommonSQL.INSERT_REG_PROP_SUBCLAUSE_MAP;
			logger.debug(insertPropSubclauses);
			dbUtility.createPreparedStatement(insertPropSubclauses);
			status = dbUtility.executeUpdate(_propSubclausese);
			logger.debug("--status of property subclause details--<><>"
					+ status);

		} catch (Exception e) {
			//dbUtility.rollback();
			logger.error(e);

		} 		return status;
	}

	public ArrayList getPropertyTxnIdList(String _regTxnId) throws Exception {
		ArrayList returnList = new ArrayList();

		try {
			dbUtility = new DBUtility();
			String selectSQL = "SELECT PROPERTY_TXN_ID FROM IGRS_REG_PROPERTY_DETAILS where REG_TXN_ID='"
					+ _regTxnId + "' order by PROPERTY_TXN_ID asc";
			dbUtility.createStatement();
			returnList = dbUtility.executeQuery(selectSQL);
		} catch (Exception e) {
			//dbUtility.rollback();
			logger.error(e);

		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
			dbUtility = null;
		}
		return returnList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPropertyDetailsList(
				String _propTxnNo,DBUtility dbUtility) throws Exception {
		ArrayList returnList = new ArrayList();
		try {	 
			String selectSQL = RegCommonSQL.SELECT_REG_PROPERTY_DETAILS_TMP;					
			logger.debug(selectSQL);
			String[] param = new String[1];
			param[0] = _propTxnNo;
			dbUtility.createPreparedStatement(selectSQL);
			returnList = dbUtility.executeQuery(param);
		} catch (Exception e) {
			logger.debug(e);
		}  
		return returnList;
	}
	/**
	 * getting system values and consideration amount.
	 * 
	 * @param _propTxnNo
	 * @return
	 */

	public ArrayList getFloorTxnID(String _propTxnNo,
				DBUtility dbUtility) throws Exception {
		ArrayList returnList = new ArrayList();
		 
		try {
			
			String selectSQL = RegCommonSQL.SELECT_REG_PROPERTY_FLOORID_DTLS;
					 
			logger.debug(selectSQL);
			String[] param = new String[1];
			param[0] = _propTxnNo;
			dbUtility.createPreparedStatement(selectSQL);
			returnList = dbUtility.executeQuery(param);
		} catch (Exception e) {
			logger.debug(e);
		}  
		return returnList;
	}
	public ArrayList getPropertyValueDetailsList(
			String _propTxnNo,DBUtility dbUtility) throws Exception {
		ArrayList returnList = new ArrayList();
		 
		try {
			
			String selectSQL = RegCommonSQL.SELECT_REG_PROPERTY_VALUE_DTLS;
					 
			logger.debug(selectSQL);
			String[] param = new String[1];
			param[0] = _propTxnNo;
			dbUtility.createPreparedStatement(selectSQL);
			returnList = dbUtility.executeQuery(param);
		} catch (Exception e) {
			logger.debug(e);
		}  
		return returnList;
	}
	/**
	 * getting system values and consideration amount.
	 * 
	 * @param _propTxnNo
	 * @return
	 */

	public ArrayList getPropertyValueDetailsList(
			String _propTxnNo) throws Exception {
		ArrayList returnList = new ArrayList();
		DBUtility dbUtility = new DBUtility();
		try {
			
			String selectSQL = RegCommonSQL.SELECT_REG_PROPERTY_VALUE_DTLS;
					 
			logger.debug(selectSQL);
			String[] param = new String[1];
			param[0] = _propTxnNo;
			dbUtility.createPreparedStatement(selectSQL);
			returnList = dbUtility.executeQuery(param);
		} catch (Exception e) {
			logger.debug(e);
		}  finally {
			dbUtility.closeConnection();
		}
		return returnList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPropertySubclauseList(String _propTxnNo,
			 DBUtility dbUtility) throws Exception {
		ArrayList returnList = new ArrayList();

		try {

			 
			String selectSQL = RegCommonSQL.SELECT_REG_PROP_SUBCLAUSE_MAP;
					 
			logger.debug(selectSQL);
			String[] param = new String[1];
			param[0] = _propTxnNo;
			dbUtility.createPreparedStatement(selectSQL);
			returnList = dbUtility.executeQuery(param);

		} catch (Exception e) {
			logger.debug(e);
		}  

		return returnList;
	}
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getFloorSubclauseList(String _propTxnNo,
			String _floorTxnNo,DBUtility dbUtility) throws Exception {
		ArrayList returnList = new ArrayList();

		try {

			 
			String selectSQL = 
				RegCommonSQL.SELECT_REG_PROPERTY_SUBCLAUSE_FLOOR_MAP;
					 
			logger.debug(selectSQL);
			String[] param = new String[2];
			param[0] = _floorTxnNo;
			param[1] = _propTxnNo;
			dbUtility.createPreparedStatement(selectSQL);
			returnList = dbUtility.executeQuery(param);

		} catch (Exception e) {
			logger.debug(e);
		}  

		return returnList;
	}
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPropertyFloorDetailsList(String _propTxnNo,
			String _floorTxnNo,
			 DBUtility dbUtility) throws Exception {
		ArrayList returnList = new ArrayList();

		try {

			
			String selectSQL = RegCommonSQL.SELECT_REG_PROPERTY_FLOOR_DTLS;
			logger.debug(selectSQL);
			String[] param = new String[2];
			param[0] = _propTxnNo;
			param[1] = _floorTxnNo;
			dbUtility.createPreparedStatement(selectSQL);
			returnList = dbUtility.executeQuery(param);

		} catch (Exception e) {
			logger.debug(e);
		}  
		return returnList;
	}

	/**
	 * @param _blkstatus
	 * @param _dto
	 * @return
	 * @throws Exception
	 */
	public boolean updatePropertyDetails(String[] _propDetailsArr)
			throws Exception {

		dbUtility = new DBUtility();
		boolean flg = false;
		try {

			String updatestr = RegCommonSQL.UPDATE_REG_PROPERTY_DETAILS;
			logger.debug(updatestr);
			dbUtility.createPreparedStatement(updatestr);
			flg = dbUtility.executeUpdate(_propDetailsArr);

			logger.debug("dao update prop details status-->" + flg);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
			dbUtility = null;
		}
		return flg;
	}

	/**
	 * @param _blkstatus
	 * @param _dto
	 * @return
	 * @throws Exception
	 */
	public boolean updatePropertyValueDetails(String[] _propDetails)
			throws Exception {

		dbUtility = new DBUtility();
		boolean flg = false;
		try {
			String updatestr = RegCommonSQL.UPDATE_REG_PROPERTY_VALUE_DTLS;
			logger.debug(updatestr);
			dbUtility.createPreparedStatement(updatestr);
			flg = dbUtility.executeUpdate(_propDetails);

			logger.debug("dao update prop value details status-->" + flg);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
			dbUtility = null;
		}
		return flg;
	}

	/**
	 * @param _blkstatus
	 * @param _dto
	 * @return
	 * @throws Exception
	 */
	public boolean updatePropertyFloorValueDetails(String[] _propDetails)
			throws Exception {

		dbUtility = new DBUtility();
		boolean flg = false;
		try {
			String updatestr = RegCommonSQL.UPDATE_REG_PROPERTY_FLR_VALUE_DTLS;
			logger.debug(updatestr);
			dbUtility.createPreparedStatement(updatestr);
			flg = dbUtility.executeUpdate(_propDetails);

			logger.debug("dao update prop value details status-->" + flg);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
			dbUtility = null;
		}
		return flg;
	}

	/**
	 * @param _blkstatus
	 * @param _dto
	 * @return
	 * @throws Exception
	 */
	public boolean updatePropertySubclauseDetails(String[] _propDetails)
			throws Exception {

		dbUtility = new DBUtility();
		boolean flg = false;
		try {

			String updatestr = RegCommonSQL.UPDATE_REG_PROP_SUBCLAUSE_MAP;
			logger.debug(updatestr);
			dbUtility.createPreparedStatement(updatestr);
			flg = dbUtility.executeUpdate(_propDetails);
			logger.debug("dao update prop subcaluse value details status-->"
					+ flg);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
			dbUtility = null;
		}
		return flg;
	}

	/**
	 * @param _blkstatus
	 * @param _dto
	 * @return
	 * @throws Exception
	 */
	public boolean updatePropertyFloorSubclauseDetails(String[] _propDetails)
			throws Exception {

		dbUtility = new DBUtility();
		boolean flg = false;
		try {

			String updatestr = RegCommonSQL.UPDATE_REG_PROP_FLR_SUBCLAUSE_MAP;
			logger.debug(updatestr);
			dbUtility.createPreparedStatement(updatestr);
			flg = dbUtility.executeUpdate(_propDetails);
			logger.debug("dao update prop subcaluse value details status-->"
					+ flg);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
			dbUtility = null;
		}
		return flg;
	}

	/**
	 * @param _blkstatus
	 * @param _dto
	 * @return
	 * @throws Exception
	 */
	public boolean updatePropertyFloorDetails(String[] _propDetails)
			throws Exception {

		dbUtility = new DBUtility();
		boolean flg = false;
		try {

			String updatestr = RegCommonSQL.UPDATE_REG_PROPERTY_FLOOR_DTLS;
			logger.debug(updatestr);
			dbUtility.createPreparedStatement(updatestr);
			flg = dbUtility.executeUpdate(_propDetails);

			logger.debug("dao update prop subcaluse value details status-->"
					+ flg);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
			dbUtility = null;
		}
		return flg;
	}

	public String getPropertyTypeID (String _propTxnNo,
					DBUtility dbUtil) throws Exception {
		String propertyID = "";
		try {
			String SQL = RegCommonSQL.SELECT_PROPERTY_TYPE_ID;
			logger.debug(SQL);
			dbUtil.createPreparedStatement(SQL);
			String[] param = new String[1];
			param[0] = _propTxnNo;
			propertyID = dbUtil.executeQry(param);
			logger.debug("propertyID RegDAOCompletion:-"+propertyID);
		}catch(Exception x) {
			logger.debug(x);
		}
		return propertyID;
	}
	
	/**
	 * @param _blkstatus
	 * @param _dto
	 * @return
	 * @throws Exception
	 */
	public boolean deletePropertyDetails(String _propTxnNo,
						String propertyType,DBUtility dbUtil
						) throws Exception {

		boolean value = false;
		//DBUtility dbUtil = new DBUtility();
		try {
			
			
			 
			if("PROPERTY_TYPE_BUILDING".equals(propertyType)) {
				
				value = 
					deletePropertySubclauseDetails(_propTxnNo,dbUtil);
				logger.debug("Property Floor Delete:-"+value);
				if(value) {
					
					value = deletePropertyValueDetails(_propTxnNo,dbUtil);
					logger.debug("Property Subclause Delete:-"+value);
					if(value) {
						value = deletePropertyFloorDetails(_propTxnNo,dbUtil);
						logger.debug("Property Value Delete:-"+value);
						if(value) {
							String updatestr = 
								RegCommonSQL.DELETE_REG_PROPERTY_DETAILS;
							logger.debug(updatestr);
							
							dbUtil.createPreparedStatement(updatestr);
							String[] strarr = new String[1];
							strarr[0] = _propTxnNo;
							value = dbUtil.executeUpdate(strarr);
							logger.debug("Property Delete:-"+value);
						}
					}
				}	
				

			}
			if(!"PROPERTY_TYPE_BUILDING".equals(propertyType)) {
				value = 
						deletePropertySubclauseDetails(_propTxnNo,dbUtil);
				logger.debug("Property Subclause Delete:-"+value);	
				if(value) {
						value = deletePropertyValueDetails(_propTxnNo,dbUtil);
						logger.debug("Property Value Delete:-"+value);
					if(value) {
						String updatestr = RegCommonSQL.DELETE_REG_PROPERTY_DETAILS;
						logger.debug(updatestr);
							
						dbUtil.createPreparedStatement(updatestr);
						String[] strarr = new String[1];
						strarr[0] = _propTxnNo;
						value = dbUtil.executeUpdate(strarr);
						logger.debug("Property Delete:-"+value);
					}
				}
					
				

			}
			logger.debug("value:-"+value);
			
			/*if(value) {
				dbUtil.commit();
			}else{
				dbUtil.rollback();
			}*/
		} catch (Exception e) {

			e.printStackTrace();
			
		}
		/*finally {
			
			if (dbUtil != null) {
				dbUtil.closeConnection();

			}
			dbUtility = null;
		}*/
		return value;
	}

	/**
	 * @param _blkstatus
	 * @param _dto
	 * @return
	 * @throws Exception
	 */
	private boolean deletePropertyValueDetails(
			String _propTxnNo,DBUtility dbUtility)
			throws Exception {

		boolean flg = true;
		try {

			String updatestr = RegCommonSQL.DELETE_REG_PROPERTY_VALUE_DTLS;
			logger.debug(updatestr);
			//dbUtility.createStatement();
			String[] strarr = new String[1];
			strarr[0] = _propTxnNo;
			dbUtility.createPreparedStatement(updatestr);
			flg = dbUtility.executeUpdate(strarr);

			logger.debug("dao delete prop value details status-->" + flg);

		} catch (Exception e) {

			logger.debug(e);
		} 
		/*finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
			dbUtility = null;
		}*/
		return flg;
	}

	/**
	 * @param _blkstatus
	 * @param _dto
	 * @return
	 * @throws Exception
	 */
	private boolean deletePropertySubclauseDetails(
				String _propTxnNo,DBUtility dbUtility)
			throws Exception {

		
		boolean flg = true;
		try {
            // Added By Aruna
			if(getPropertySubclauseListIfExist(_propTxnNo,dbUtility)){
			String updatestr = RegCommonSQL.DELETE_REG_PROP_SUBCLAUSE_MAP;
			logger.debug(updatestr);
			//dbUtility.createStatement();
			String[] strarr = new String[1];
			strarr[0] = _propTxnNo;
			dbUtility.createPreparedStatement(updatestr);
			flg = dbUtility.executeUpdate(strarr);

			logger.debug("dao delete prop subcaluse value details status-->"
					+ flg);
			}else{
				flg=true;
			}

		} catch (Exception e) {

			logger.debug(e);
		} 
		/*finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
			dbUtility = null;
		}*/
		return flg;
	}

	/**
	 * @param _blkstatus
	 * @param _dto
	 * @return
	 * @throws Exception
	 */
	private boolean deletePropertyFloorDetails(
					String _propTxnNo,DBUtility dbUtility)
			throws Exception {

		//dbUtility = new DBUtility();
		boolean flg = true;
		try {

			String updatestr = RegCommonSQL.DELETE_REG_PROPERTY_FLOOR_DTLS;
			logger.debug(updatestr);
			 
			String[] strarr = new String[1];
			strarr[0] = _propTxnNo;
			dbUtility.createPreparedStatement(updatestr);
			flg = dbUtility.executeUpdate(strarr);

			logger.debug("dao update prop subcaluse value details status-->"
					+ flg);

		} catch (Exception e) {

			logger.debug(e);
		} 
		/*finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
			dbUtility = null;
		}*/
		return flg;
	}

	/**
	 * This method is used for validating the AdjudicationId in DB for getting
	 * the property transaction id
	 * 
	 * @return propertyTxnId or null
	 * @throws Exception
	 */
	public String validateRegComplAdjudicationId(String _adjudiTxnId)
			throws Exception {

		String propertyTxnId = "";
		try {

			dbUtility = new DBUtility();
			String selectSQL = "SELECT distinct (SELECT PROPERTY_TXN_ID FROM IGRS_REG_ADJUDCN_PROP_DTLS where  "
					+ "ADJUDCN_TXN_ID=ADJ.ADJUDICATION_ID) PROPERTY_TXN_ID  FROM "
					+ "IGRS_REG_ADJUDCN_DETAILS ADJ where ADJ.ADJUDICATION_ID='"
					+ _adjudiTxnId + "'";
			logger.debug(selectSQL);
			dbUtility.createStatement();
			propertyTxnId = dbUtility.executeQry(selectSQL);
			logger.debug("--status of aud id --<><>" + propertyTxnId);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (propertyTxnId == null || propertyTxnId.equals("")) {
				return null;

			}
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
			dbUtility = null;
		}
		return propertyTxnId;

	}

	public String validateRegComplPoaTxnId(String _poaTxnId) throws Exception {
		try {
			dbUtility = new DBUtility();
			String selectSQL = "SELECT distinct POA_TXN_ID FROM IGRS_REG_POA_DETAILS where POA_TXN_ID='"
					+ _poaTxnId + "'";
			logger.debug(selectSQL);
			dbUtility.createStatement();
			_poaTxnId = dbUtility.executeQry(selectSQL);
			if (_poaTxnId == null && !_poaTxnId.equals("")) {
				return null;
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
			dbUtility = null;
		}
		return _poaTxnId;
	}

	/**
	 * @author Imran Shaik store registration documents
	 * @param attachments
	 */
	public void storeRegDocuments(ArrayList attachments) {
		try {
			dbUtility = new DBUtility();
			for (int i = 0; i < attachments.size(); i++) {
				UploadFileDTO dto = (UploadFileDTO) attachments.get(i);
				File file = dto.getFile();
				// documents type is DOCUMENTS
				String docTypeId = "3";
				String fileName = file.getName();
				String fileExtn = getFileExtension(fileName);
				String docTxnId = getDocTxnId();
				upLoadDocIns(docTxnId, docTypeId, getDocExnId(fileExtn),
						fileName, file);
			}
		} catch (Exception e) {
			logger.equals(e);
			e.printStackTrace();
		}
	}

	/**
	 * for getting the File Extension
	 * 
	 * @param fileName
	 * @return String
	 */
	private String getFileExtension(String fileName) {
		int pos = fileName.lastIndexOf(".");
		String strExt = fileName.substring(pos + 1, fileName.length());
		return strExt;
	}

	/**
	 * store registration documents it's a sub method
	 * 
	 * @param docTxnId
	 * @param docTypeId
	 * @param docExtId
	 * @param fileName
	 * @param file
	 * @return boolean
	 * @throws Exception
	 */
	public boolean upLoadDocIns(String docTxnId, String docTypeId,
			String docExtId, String fileName, File file) throws Exception {
		boolean boo = false;
		try {
			dbUtility = new DBUtility();
			String SQL = "INSERT INTO NEWGEN.NEWGEN_REG_DOC_DETAILS D (D.DOC_TXN_ID,D.DOC_TYPE_ID,D.DOC_EXTN_ID,D.DOC_NAME,D.DOC_OBJ,D.DOC_STATUS) VALUES (?,?,?,?,?,'A')";
			FileInputStream fis = new FileInputStream(file);
			PreparedStatement pst = dbUtility.returnPreparedStatement(SQL);
			pst.setString(1, docTxnId);
			pst.setString(2, docTypeId);
			pst.setString(3, docExtId);
			pst.setString(4, fileName);
			pst.setBinaryStream(5, fis, (int) fis.available());
			boo = pst.execute();
			if (!boo)
				dbUtility.commit();
			else
				dbUtility.rollback();

		} catch (Exception x) {
			x.printStackTrace();
		}
		return boo;
	}

	/**
	 * get the Document Transaction ID
	 * 
	 * @return String
	 */
	private String getDocTxnId() {
		ArrayList list = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil
					.executeQuery("SELECT 'DOC'||LPAD(IGRS_REG_DOC_SEQ.NEXTVAL,4,0) FROM DUAL");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		ArrayList l = (ArrayList) list.get(0);
		return l.get(0).toString();
	}

	/**
	 * get the Document Extension ID
	 * 
	 * @param fileExtn
	 * @return String
	 */
	private String getDocExnId(String fileExtn) {
		ArrayList list = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil
					.executeQuery("SELECT EXTN_TYPE_ID FROM NEWGEN.NEWGEN_DOC_EXTN_TYPE_MASTER WHERE EXTN_TYPE_NAME='"
							+ fileExtn.toUpperCase() + "'");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		ArrayList l = (ArrayList) list.get(0);
		return l.get(0).toString();
	}

	/**
	 * getting property Details for Duty calculation.
	 * 
	 * @param refId
	 * @return
	 */
	public ArrayList getPropDetDuty(String propId) {

		ArrayList list = null;

		String sql = RegCommonSQL.SELECT_DUTY_PROP_DET + "'" + propId + "'";
		try {
			DBUtility dbUtility = new DBUtility();
			dbUtility.createStatement();
			list = dbUtility.executeQuery(sql);
		} catch (Exception e) {
			logger.error("-- Excepti0n in getPropDetDuty() " + e);
		}

		return list;
	}

	public ArrayList getMunDuty(String districtId, String tehsil, String ward,
			String mohilla, String propVal) {
		ArrayList munDuty = null;
		try {
			common = new IGRSCommon();
			munDuty = common.getMunDuty(districtId, tehsil, ward, mohilla,
					propVal);
		} catch (Exception e) {
			logger.info("Wipro in RegCompDAO -Exception  getStampDuty()");
		}

		return munDuty;

	}

	public String[] getRegFeeDuty(String deedId, String instrument,
			String strexem, String propVal) {

		String regFee[] = null;
		try {
			common = new IGRSCommon();
			regFee = common.getRegFeeDuty(deedId, instrument, strexem, propVal);
		} catch (Exception e) {
			logger.info("Wipro in RegCompDAO -Exception  getRegFeeDuty()");
		}

		return regFee;

	}

	/**
	 * inserting registration details
	 * 
	 * @param params
	 * @return Registration Id
	 */
	public String insertRegDetails(String[] params)   {
		params[0] = getRegCompletionId(params[2]);
		 
		boolean boo = insertData(RegCommonSQL.INSERT_REG_DETAILS, 
					params);
		// inserted registration id
		if (boo)
			return params[0];
		return null;
	}

	/**
	 * MP-XX(DISTRICT_ID)-AAA(SRO OFFICE_ID)-YYYY(YEAR)-BB(BOOK TYPE)-SSSSS
	 * (SERIAL NUMBER)
	 * 
	 * @param userId
	 * @return registration completion id(BB(BOOK TYPE) excluded)
	 */
	public String getRegCompletionId(String userId) {
		String regId = "MP";
		try {
			String query = RegCommonSQL.SELECT_USER_DIST_ID;
			dbUtility = new DBUtility();
			query = query + "'" + userId + "'";
			dbUtility.createStatement();
			ArrayList list = dbUtility.executeQuery(query);
			if (list.size() > 0) {
				ArrayList li = (ArrayList) list.get(0);
				regId = regId + li.get(0).toString();
			}
			query = RegCommonSQL.SELECT_USER_OFFICE_ID;
			query = query + "'" + userId + "'";
			dbUtility.createStatement();
			list = new ArrayList();
			list = dbUtility.executeQuery(query);
			if (list.size() > 0) {
				ArrayList li = (ArrayList) list.get(0);
				regId = regId + li.get(0).toString();
			}
			Date dt = new Date();
			int d = dt.getYear() + 1900;
			regId = regId + String.valueOf(d);
			list = new ArrayList();
			list = dbUtility
					.executeQuery("SELECT LPAD(IGRS_REG_DOC_SEQ.NEXTVAL,5,0) FROM DUAL");
			if (list.size() > 0) {
				ArrayList li = (ArrayList) list.get(0);
				regId = regId + li.get(0).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return regId;
	}

	/**
	 * getting stamp Duty paid value
	 * 
	 * @param regNo
	 * @return stampDuty
	 */

	public ArrayList getDutyPaid(String regNo) {
		ArrayList list = null;

		String sql = RegCommonSQL.SELECT_DUTY_PAID + "'" + regNo + "'";
		try {
			DBUtility dbUtility = new DBUtility();
			dbUtility.createStatement();
			list = dbUtility.executeQuery(sql);
		} catch (Exception e) {
			logger.error("-- Exception in getDutyPaid() " + e);
		}

		return list;
	}

	public boolean updateRegDetails(String regCompId,String regStatus) {
		boolean boo = false;
		// Edited By Aruna
		String regStatusID=getRegistrationStatus(regStatus);
		//String query = RegCommonSQL.UPDATE_REG_DETAILS + "'" + regCompId + "'";
		String  query = RegCommonSQL.UPDATE_REG_DETAILS;
		String param[]=new String[2];
		param[0]=regStatusID;
		param[1]=regCompId;		
		try {
			 dbUtility = new DBUtility();			
			 dbUtility.createPreparedStatement(query);			
			 boo=dbUtility.executeUpdate(param);			
		} catch (Exception e) {
			logger.error("-- Exception in updateRegDetails() " + e);
			e.printStackTrace();
		}
		return boo;
	}
	public String verifyPin(CommonDTO dtoMap) throws Exception{
		String returnValue = "";
		dbUtility = new DBUtility();
		CallableStatement clstmt;
		try {
			clstmt = dbUtility
					.returnCallableStatement(
							RegCommonSQL.IGRS_REG_PIN_VERIFY_PROC);
			clstmt.setString(1, dtoMap.getOldRegNo());
			clstmt.setString(2, dtoMap.getOldPropertyId());
			clstmt.setString(3, dtoMap.getOldPartyId());
			clstmt.setString(4, dtoMap.getFname());
			clstmt.setString(5, dtoMap.getMname());
			clstmt.setString(6, dtoMap.getLname());
			clstmt.setString(7, dtoMap.getOrgName());
			clstmt.setString(8, dtoMap.getPinNumber());
			clstmt.registerOutParameter(9, OracleTypes.VARCHAR);
			if (!clstmt.execute()) {
				returnValue = clstmt.getString(9);
			}
		}catch(Exception x){
			
		}finally {
			dbUtility.closeConnection();
		}
		return returnValue;
	}
	
	// Added By Aruna
	public ArrayList getCompDeedList(String regNumber) throws Exception
	{
		ArrayList deedList=new ArrayList();
		String[] regParam = new String[1] ;
		regParam[0] = regNumber;
		DBUtility dbUtil = new DBUtility();
		ArrayList deedTxnList;
		try{
		dbUtil.createPreparedStatement(RegCommonSQL.
					SELECT_REG_DEED_DETAIL);
		 deedTxnList = dbUtil.executeQuery(regParam);
		
		if(deedTxnList != null ) {
			for(int i =0;i<deedTxnList.size();i++) {
				ArrayList list = (ArrayList) deedTxnList.get(i);
				RegInitCompleteDTO regInitComDTO = new RegInitCompleteDTO();
				regInitComDTO.setDeedTxnId(list.get(0).toString());
				deedList.add(regInitComDTO);
			}
		}
		}catch(Exception x) {
			logger.debug(x);
			dbUtil.commit();
		}finally {
			dbUtil.closeConnection();
		}
		return deedList;
	}
	
	public RegInitCompleteDTO getCompleteDeedList(String deedTxnID) throws Exception
	{
		ArrayList deedList=new ArrayList();
		String[] regParam = new String[2] ;
		regParam[0] = deedTxnID;
		regParam[1] = deedTxnID;
		DBUtility dbUtil = new DBUtility();
		ArrayList deedTxnList;
		ArrayList deedAttrList ;
		ArrayList exemList=new ArrayList();
		RegInitCompleteDTO regInitComDTO = new RegInitCompleteDTO();
		try{
			
		dbUtil.createPreparedStatement(RegCommonSQL.
				GET_COMPLETE_DEED_DTLS);
		 deedTxnList = dbUtil.executeQuery(regParam);
		//StringBuffer exemptionNames=new StringBuffer();
		if(deedTxnList != null ) {
			
			for(int i =0;i<deedTxnList.size();i++) {
				ArrayList list = (ArrayList) deedTxnList.get(i);
				if(i==0)
				{
				regInitComDTO.setInstrumentName((String)list.get(0));
				}else{
				//exemptionNames.append((String)list.get(0)+ ",");
				RegInitCompleteDTO regInitComDTO2 = new RegInitCompleteDTO();
				regInitComDTO2.setExemptionName((String)list.get(0));
				exemList.add(regInitComDTO2);
				//regInitComDTO.setExemptionName(exemptionNames.toString());
				}
			}
		}
		
		String[] regParam1 = new String[1] ;
		regParam1[0] = deedTxnID;
		dbUtil.createPreparedStatement(RegCommonSQL.
				GET_DEED_ATTIBUTE_DTLS);
		
		 deedAttrList = dbUtil.executeQuery(regParam1);
		 ArrayList attributesList=new ArrayList();
		 if(deedAttrList != null ) {
				
				for(int j =0;j<deedAttrList.size();j++) {
					ArrayList list = (ArrayList) deedAttrList.get(j);
					if(list!=null )
					{
						RegInitCompleteDTO regInitComDTO1 = new RegInitCompleteDTO();
						regInitComDTO1.setInputTypeId((String)list.get(0));		
						regInitComDTO1.setInputlabel((String)list.get(1));
						attributesList.add(regInitComDTO1);						
					}
				}
			}
		 regInitComDTO.setAttributeList(attributesList);
		 
		}catch(Exception x) {
			logger.debug(x);
			dbUtil.commit();
		}finally {
			dbUtil.closeConnection();
		}
		regInitComDTO.setDeedTxnId(deedTxnID);
		regInitComDTO.setExmpList(exemList);
		return regInitComDTO;
	}
	
	private boolean getPropertySubclauseListIfExist(
			String _propTxnNo,DBUtility dbUtility)
		throws Exception {
	boolean isExist=false;
	try {
        // Added By Aruna		
		String str="select * FROM IGRS_REG_PROP_SUBCLAUSE_MAP WHERE PROPERTY_TXN_ID=?";
		logger.debug(str);		
		//dbUtility.createStatement();
		String[] strarr = new String[1];
		strarr[0] = _propTxnNo;
		dbUtility.createPreparedStatement(str);
		ArrayList list = dbUtility.executeQuery(strarr);
		if(list!=null & list.size()>0)
		{
			isExist=true;
		}

	} catch (Exception e) {

		logger.debug(e);
	}
	return isExist;
}
	
	public ArrayList getPartyIdList(String[] regParam) throws Exception
	{
		ArrayList partyIdList=new ArrayList();		
		DBUtility dbUtil = new DBUtility();
		try{
			ArrayList partyList=new ArrayList();
			if(regParam[2]!=null & regParam[2].length()>0)
			{
			dbUtil.createPreparedStatement(RegCommonSQL.
					GET_PARTY_IDS);
			 partyList = dbUtil.executeQuery(regParam);
			}else{
				dbUtil.createPreparedStatement(RegCommonSQL.
						GET_PARTY_IDS_WITH_NOPROPERTY);
				String[] newRegparm=new String[2];
				newRegparm[0]=regParam[0];
				newRegparm[1]=regParam[1];
				partyList = dbUtil.executeQuery(newRegparm);
			}
			ArrayList partiesList=new ArrayList();
			if(partyList != null ) {
				
				for(int i =0;i<partyList.size();i++) {
					partiesList = (ArrayList) partyList.get(i);	
					CommonDTO commonDTO=new CommonDTO();
					commonDTO.setPartyId((String)partiesList.get(0));
					commonDTO.setPartyTypeName((String)partiesList.get(1));
					commonDTO.setPartyType((String)partiesList.get(2));
					partyIdList.add(commonDTO);
				}
			}	
		}
		catch (Exception e) {

			logger.debug(e);
		}
				return partyIdList;
}
	// Added By Aruna
	public boolean isPropertyMapped(String propertyTxnID,DBUtility dbUtil) throws Exception {
		ArrayList dList = new ArrayList();
		//DBUtility dbUtil = new DBUtility();
		boolean propertyMapped=false;
		try {
			String arr[] = new String[1];
			arr[0] = propertyTxnID;
			String sql=" select count(0) from IGRS_REG_DEED_PROP_MAP m where m.PROPERTY_TXN_ID =? ";
			dbUtil.createPreparedStatement(sql);
			ArrayList countList = dbUtil.executeQuery(arr);
			
			if(countList!=null) {
				for(int i=0;i<countList.size();i++) {
					ArrayList list = (ArrayList)countList.get(i);
					String counter=(String) list.get(0);
					int count=Integer.parseInt(counter);
					if(count>0)
					{
						propertyMapped=true;
					}
				}
			}			 
			 
		}catch(Exception x) {
			logger.debug(x);
		}finally {
			//dbUtil.closeConnection();
		}
		
		return propertyMapped;
	}
	public boolean shouldPropertyBeAdded(String _regTxnId) throws Exception {
		boolean shouldPropertyBeAdded=false;
		ArrayList returnList=new ArrayList(); 

		try {
			dbUtility = new DBUtility();
			
			String selectSQL = " SELECT DTM.PROPERTY_RELATED_DEED  "
				+ " FROM IGRS_REG_COMP_DEED_DETAILS RCDD,IGRS_DEED_TYPE_MASTER DTM "
				+ " WHERE RCDD.DEED_TYPE_ID=DTM.DEED_TYPE_ID "
				+ " AND RCDD.REG_TXN_ID='"
				+ _regTxnId + "' ";
			dbUtility.createStatement();
			returnList = dbUtility.executeQuery(selectSQL);
			if(returnList!=null) {
				for(int i=0;i<returnList.size();i++) {
					ArrayList list = (ArrayList)returnList.get(i);					
					
					String propertyRelatedDeed=(String) list.get(0);
					
					if(propertyRelatedDeed!=null && propertyRelatedDeed.equalsIgnoreCase("Y"))
					{
						shouldPropertyBeAdded=true;
						break;
					}
				}
			}		
		} catch (Exception e) {			
			logger.error(e);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
			dbUtility = null;
		}
		return shouldPropertyBeAdded;
	}
	public String  getRegistrationStatus(String regStatus) {
		String regStatusID="";     
		String query = RegCommonSQL.SELECT_REG_STATUS_ID + "'" + regStatus + "'";
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			list = dbUtility.executeQuery(query);
			if (list.size() > 0) {
				ArrayList li = (ArrayList) list.get(0);
				regStatusID =  li.get(0).toString();
			}
		} catch (Exception e) {
			logger.error("-- Exception in getRegistrationStatus() " + e);
			e.printStackTrace();
		}			
		
		return regStatusID;
	}
	
	public boolean insertOldAndNewRegIDS(String oldRegID,String newRegID) {
		boolean boo = false;
		String SQL;
		String[] param=new String[2];
		param[0]=oldRegID;
		param[1]=newRegID;
		SQL = RegCommonSQL.OLD_REG_IDS_INS_QUERY;
		try {
			DBUtility dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			boo = dbUtility.executeUpdate(param);

			if (boo) {
				dbUtility.commit();
			}
		} catch (Exception e) {
			logger.error("Wipro in RegCompDAO -Exception in  insertOldAndNewRegIDS ()  "
					+ e);
		}

		return boo;
	}
	
	public StringBuffer updateDeedRORDtls(ArrayList propDeedList) {
		boolean boo = false;
		int size=propDeedList.size();
		StringBuffer rorIDS=new StringBuffer(); 
		String query = RegCommonSQL.UPDATE_DEED_DTS;
		try {
			 dbUtility = new DBUtility();
			for(int i=0;i<size;i++)
			 {
				 CommonDTO commonDTO=(CommonDTO)propDeedList.get(i);
				 String param[]=new String[2];
				 param[0]=rorIDgenerator();
				 rorIDS.append(param[0]);
				 logger.debug("Deed ID in updateDeedRORDtls() method:"+commonDTO.getDeed());
				 if(i!=(size-1))
			     {
					 rorIDS.append(",  ");
			     }
				 param[1]=commonDTO.getDeed();	
				 dbUtility.createPreparedStatement(query);
				 boo=dbUtility.executeUpdate(param);		
			 }
		} catch (Exception e) {
			logger.error("-- Exception in updateDeedRORDtls() " + e);
			e.printStackTrace();
		}
		return rorIDS;
	}
	
	private String rorIDgenerator() {
		String rorDeedID="";
		String query = RegCommonSQL.ROR_SEQ_NUMBER;
		ArrayList list=new ArrayList();
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			list = dbUtility.executeQuery(query);
			if (list.size() > 0) {
				ArrayList li = (ArrayList) list.get(0);
				rorDeedID =  li.get(0).toString();
			}
		} catch (Exception e) {
			logger.error("-- Exception in rorIDgenerator() " + e);
			e.printStackTrace();
		}		
		return "ROR" + new Date().getTime() + rorDeedID;
	}
	
	public String getRegInitDate(String[] param) {
		String regInitDate="";		
		ArrayList list=new ArrayList();		
		try {			
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(RegCommonSQL.REG_INIT_DATE);		
			list = dbUtility.executeQuery(param);
			if (list.size() > 0) {
				ArrayList li = (ArrayList) list.get(0);
				regInitDate =  li.get(0).toString();
			}
		} catch (Exception e) {
			logger.error("-- Exception in getRegInitDate() " + e);
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception x) {
				logger.debug(x);
			}
		}		
		return regInitDate;
	}
	
}
