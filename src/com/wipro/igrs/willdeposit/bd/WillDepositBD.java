/* Copyright (c) 2007-08 WIPRO INFOTECH. All Rights Reserved.
 * 
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *  
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE 
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE 
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF 
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS 
 * DERIVATIVES.
 * 
 */

/* 
 * FILE NAME: WillDepositBD.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 12th December 2007 
 * MODIFIED ON:	   12th April 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE BUSINESS DELEGATE CLASS
 */

package com.wipro.igrs.willdeposit.bd;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.db.DataSourceLookUp;
import com.wipro.igrs.util.CommonUtil;
import com.wipro.igrs.util.PropertiesFileReader;
import com.wipro.igrs.willdeposit.constant.CommonConstant;
import com.wipro.igrs.willdeposit.dao.WillCommonDAO;
import com.wipro.igrs.willdeposit.dao.WillDepositDAO;
import com.wipro.igrs.willdeposit.dto.WillDepositDTO;
import com.wipro.igrs.willdeposit.dto.WillDepositDashBoard;

/**
 * @author NIHAR M.
 * 
 */
public class WillDepositBD {

	private Logger logger = (Logger) Logger.getLogger(WillDepositBD.class);
	/**
	 * @return ArrayList
	 * @throws Exception
	 */

	Connection con = null;
	DBUtility dbUtil;

	public ArrayList getCountry(String language) throws Exception {
		WillDepositDAO dao = new WillDepositDAO();
		ArrayList ret = dao.getCountry();
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				WillDepositDTO dto = new WillDepositDTO();
				dto.setCountryId((String) lst.get(0));
				if ("hi".equalsIgnoreCase(language)) {
					dto.setCountry((String) lst.get(2));
				} else {
					dto.setCountry((String) lst.get(1));
				}
				// dto.setCountry((String) lst.get(1));
				list.add(dto);
			}
		}
		return list;
	}

	/**
	 * @return String
	 * @throws Exception
	 */
	public String getWillFeeValue(String StrFunId) throws Exception {
		ArrayList list = new ArrayList();
		ArrayList retList1 = new ArrayList();
		String feeVal = "0.0";

		try {
			WillDepositDAO objDao = new WillDepositDAO();
			list = objDao.getWillFeeValue(StrFunId);

			if (retList1 != null) {
				retList1 = (ArrayList) list.get(0);
				feeVal = (String) retList1.get(0);
				logger.debug("Will DepositBD in getFee(): feeVal=" + feeVal);
			}

		} catch (Exception e) {
			logger.debug("Error:-    " + e.getMessage());
		}
		return feeVal;
	}

	/**
	 * @param _retFunId
	 * @return String
	 * @throws Exception
	 */
	public String getWillOtherFeeValue(String _retFunId, String serviceId,
			String userType) throws Exception {
		String OthersDuty = "0.0";
		try {
			WillDepositDAO objDao = new WillDepositDAO();
			OthersDuty = objDao.getWillOtherFeeValue(_retFunId, serviceId,
					userType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("Exception in  getVisitOthersDuty()  " + e);
		}
		return OthersDuty;
	}

	/**
	 * @param stateId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getState(String stateId, String lang) throws Exception {
		WillDepositDAO dao = new WillDepositDAO();
		ArrayList ret = dao.getState(stateId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				WillDepositDTO dto = new WillDepositDTO();
				dto.setStateId((String) lst.get(0));
				if ("hi".equalsIgnoreCase(lang)) {
					dto.setState((String) lst.get(2));
				} else {
					dto.setState((String) lst.get(1));
				}
				// dto.setState((String) lst.get(1));
				list.add(dto);
			}
		}
		return list;
	}

	/**
	 * @param stateId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getDistrict(String stateId, String lang) throws Exception {

		WillDepositDAO dao = new WillDepositDAO();
		ArrayList ret = dao.getDistrict(stateId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				WillDepositDTO dto = new WillDepositDTO();
				dto.setDistrictId((String) lst.get(0));
				if ("hi".equalsIgnoreCase(lang)) {
					dto.setDistrict((String) lst.get(2));
				} else {
					dto.setDistrict((String) lst.get(1));
				}
				// dto.setDistrict((String) lst.get(1));
				list.add(dto);
			}
		}
		return list;
	}

	/**
	 * @param commonId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getAgentType(String commonId) throws Exception {
		WillDepositDAO dao = new WillDepositDAO();
		ArrayList ret = dao.getType(commonId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				WillDepositDTO dto = new WillDepositDTO();
				dto.setAgentType((String) lst.get(1));
				list.add(dto);
			}
		}
		return list;
	}

	/**
	 * @param commonId
	 * @return ArrayList
	 * @throws Exception
	 */

	public ArrayList getIDProof(String commonId, String lang) throws Exception {
		WillDepositDAO dao = new WillDepositDAO();
		ArrayList ret = dao.getType(commonId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				WillDepositDTO dto = new WillDepositDTO();
				dto.setIdProofNo((String) lst.get(0));
				if ("en".equalsIgnoreCase(lang)) {
					dto.setIdProof((String) lst.get(1));
				} else {
					dto.setIdProof((String) lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}

	/**
	 * @return WillDepositDTO
	 * @throws Exception
	 */
	public WillDepositDTO getFee() throws Exception {
		WillDepositDAO dao = new WillDepositDAO();
		ArrayList list = dao.getFee();
		WillDepositDTO dto = new WillDepositDTO();
		dto.setFee((String) list.get(0));
		dto.setOtherFee((String) list.get(1));
		int total = Integer.parseInt((String) list.get(0))
				+ Integer.parseInt((String) list.get(1));
		dto.setTotalFee(String.valueOf(total));
		return dto;
	}

	/**
	 * @param dto
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertWillWithDrawal(WillDepositDTO dto) throws Exception {
		WillDepositDAO dao = new WillDepositDAO();
		boolean willwithdrawal = false;
		String[] param = new String[6];
		param[0] = CommonConstant.WILL_WITHDRAWN_FLAG;
		param[1] = "Y";
		param[2] = "Y";
		param[3] = dto.getWithDrawalReason();
		param[4] = dto.getWithDrawalRemark();
		param[5] = dto.getWillId();
		willwithdrawal = dao.insertWillWithDrawal(param);
		return willwithdrawal;
	}

	/**
	 * @param al
	 * @return boolean
	 * @throws Exception
	 */
	// Challan Payment
	// public boolean insertChallanPayment(ArrayList al) throws Exception {
	// WillDepositDAO dao = new WillDepositDAO();
	// boolean challanPayment = false;
	// for(int i = 0; i < al.size(); i++){
	// PaymentDTO dto = (PaymentDTO) al.get(i);
	// String[] param = new String[2];
	// param[0] = dto.getChallanNo();
	// param[1] = dto.getAmount();
	// challanPayment = dao.insertChallanPayment(param);
	// }
	// return challanPayment;
	// }
	// Cash Payment
	/**
	 * @param dto
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertCashPayment(WillDepositDTO dto) throws Exception {

		WillDepositDAO dao = new WillDepositDAO();
		boolean cashPayment;
		String[] cashParam = new String[1];
		cashParam[0] = dto.getCashReceiptNo();
		cashPayment = dao.insertCashPayment(cashParam);

		return cashPayment;
	}

	/**
	 * @param dto
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertWillDeposit(WillDepositDTO dto) throws Exception {

		WillDepositDAO dao = new WillDepositDAO();
		boolean willdeposit = false;
		// dto.setFee("250.00");
		// dto.setOtherFee("0.00");

		String[] depositparam = new String[27];
		depositparam[0] = willIDgenerator(dto.getDistrict(), "");
		// logger.debug("BD: willID:-    "+willIDgenerator());

		depositparam[1] = "D1111"; // DR id
		depositparam[2] = dto.getAgentType();
		depositparam[3] = dto.getFirstName();
		depositparam[4] = dto.getMidName();
		depositparam[5] = dto.getLastName();
		depositparam[6] = dto.getGender();
		depositparam[7] = dto.getAge();
		depositparam[8] = dto.getFatherName();
		depositparam[9] = dto.getMotherName();
		depositparam[10] = dto.getSpouseName();
		depositparam[11] = dto.getAddress();
		depositparam[12] = dto.getCity();
		depositparam[13] = dto.getState();
		depositparam[14] = dto.getCountry();
		depositparam[15] = dto.getPin();
		depositparam[16] = dto.getPhone();
		depositparam[17] = dto.getMphone();
		depositparam[18] = dto.getEmail();
		depositparam[19] = dto.getIdProofNo();
		depositparam[20] = "Y";
		depositparam[21] = "Y";
		depositparam[22] = "Y";
		depositparam[23] = dto.getFee();
		depositparam[24] = dto.getOtherFee();
		depositparam[25] = "C"; // dto.getPaymentType();
		// depositparam[26] = "Y";
		// depositparam[27] = "21-sep-2007";
		depositparam[26] = CommonConstant.WILL_DEPOSIT_FLAG;

		String[] agentparam = new String[18];
		agentparam[0] = willIDgenerator(dto.getDistrict(), "");
		agentparam[1] = dto.getAgentFirstName();
		agentparam[2] = dto.getAgentMidName();
		agentparam[3] = dto.getAgentLastName();
		agentparam[4] = dto.getAgentGender();
		agentparam[5] = dto.getAgentAge();
		agentparam[6] = dto.getAgentFatherName();
		agentparam[7] = dto.getAgentMotherName();
		agentparam[8] = dto.getAgentSpouseName();
		agentparam[9] = dto.getAgentAddress();
		agentparam[10] = dto.getAgentCity();
		agentparam[11] = dto.getAgentState();
		agentparam[12] = dto.getAgentCountry();
		agentparam[13] = dto.getAgentPin();
		agentparam[14] = dto.getAgentPhone();
		agentparam[15] = dto.getAgentMPhone();
		agentparam[16] = dto.getAgentEmail();
		agentparam[17] = dto.getAgentIdProofNo();
		willdeposit = dao.insertWillDeposit(depositparam, agentparam);
		return willdeposit;
	}

	/**
	 * @param willId
	 * @param testator
	 * @param fromDate
	 * @param toDate
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getWillRetrieveSearch(String willId, String testator,
			String fromDate, String toDate) throws Exception {
		WillDepositDAO dao = new WillDepositDAO();
		ArrayList list = dao.getWillRetrieveSearch(willId, testator, fromDate,
				toDate);
		ArrayList returnList = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList lst = (ArrayList) list.get(i);
				WillDepositDTO dto = new WillDepositDTO();
				dto.setFirstName((String) lst.get(0));
				dto.setMidName((String) lst.get(1));
				dto.setLastName((String) lst.get(2));
				dto.setAddress((String) lst.get(3));
				dto.setDespositDate((String) lst.get(4));
				dto.setWillId((String) lst.get(5));
				dto.setWillStatus((String) lst.get(6));

				logger.debug("BD:-   " + (String) lst.get(6));
				returnList.add(dto);
			}
		}
		return returnList;
	}

	/**
	 * @param dto
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertRetrieve(WillDepositDTO dto) throws Exception {

		String rType = dto.getRetrieverType();
		WillDepositDAO dao = new WillDepositDAO();
		String[] depositParam = new String[5];
		if (CommonConstant.WILL_RETRIEVE_CITIZEN.equalsIgnoreCase(rType)) {

			String[] param = new String[23];
			depositParam[0] = CommonConstant.WILL_RETRIEVE_FLAG;
			depositParam[1] = CommonConstant.WILL_RETRIEVE_REP_FLAG;
			depositParam[2] = dto.getWillRetrievalReason();
			depositParam[3] = dto.getWillRetrievalRemark();
			depositParam[4] = dto.getWillId();

			param[0] = dto.getWillId();
			param[1] = dto.getRetrieverFirstName();
			param[2] = dto.getRetrieverMidName();
			param[3] = dto.getRetrieverLastName();
			param[4] = dto.getRetrieverGender();
			param[5] = dto.getRetrieverAge();
			param[6] = dto.getRetrieverFatherName();
			param[7] = dto.getRetrieverMotherName();
			param[8] = dto.getRetrieverSpouseName();
			param[9] = dto.getRetrieverAddress();
			param[10] = dto.getRetrieverCity();
			param[11] = dto.getRetrieverState();
			param[12] = dto.getRetrieverCountry();
			param[13] = dto.getRetrieverPin();
			param[14] = dto.getRetrieverPhone();
			param[15] = dto.getRetrieverMPhone();
			param[16] = dto.getRetrieverEMail();
			param[17] = dto.getRetrieverIdProof();
			param[18] = "Y";
			param[19] = "Y";
			param[20] = "Y";
			param[21] = "Y";
			param[22] = willIDgenerator(dto.getDistrict(), "");
			dao.insertRetrieve(param, CommonConstant.WILL_RETRIEVE_REP_FLAG,
					depositParam);

		} else if (CommonConstant.WILL_RETRIEVE_COURT.equalsIgnoreCase(rType)) {
			depositParam[0] = CommonConstant.WILL_RETRIEVE_FLAG;
			depositParam[1] = CommonConstant.WILL_RETRIEVE_COURT_FLAG;
			depositParam[2] = dto.getWithDrawalReason();
			depositParam[3] = dto.getWithDrawalRemark();
			depositParam[4] = dto.getWillId();

			String[] param = new String[12];
			param[0] = dto.getWillId();
			param[1] = dto.getCourtName();
			param[2] = dto.getRepName();
			param[3] = dto.getRepDesg();
			param[4] = "Y";
			param[5] = "Y";
			param[6] = dto.getCourtAddress();
			param[7] = dto.getCourtCity();
			param[8] = dto.getCourtState();
			param[9] = dto.getCourtCountry();
			param[10] = dto.getCourtPin();
			param[11] = dto.getCourtPhone();
			dao.insertRetrieve(param, CommonConstant.WILL_RETRIEVE_COURT_FLAG,
					depositParam);
		}
		return true;
	}

	/**
	 * @param willid
	 * @param flag
	 * @return WillDepositDTO
	 * @throws Exception
	 *             //willwithdrawal BD
	 */
	public WillDepositDTO getWillDeposit(String willid, String flag)
			throws Exception {
		WillDepositDTO dto = new WillDepositDTO();
		WillDepositDAO dao = new WillDepositDAO();
		String[] param = new String[2];
		param[0] = willid;
		param[1] = flag;
		ArrayList ret = dao.getWillDeposit(param);

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList list = (ArrayList) ret.get(i);
				dto.setFirstName((String) list.get(0));
				dto.setMidName((String) list.get(1));
				dto.setLastName((String) list.get(2));
				dto.setGender((String) list.get(3));
				dto.setAge(((String) list.get(4)));
				dto.setFatherName((String) list.get(5));
				dto.setMotherName((String) list.get(6));
				dto.setSpouseName((String) list.get(7));
				dto.setAddress((String) list.get(8));
				dto.setCity((String) list.get(9));
				dto.setState((String) list.get(10));
				dto.setCountry((String) list.get(11));
				dto.setPin((String) list.get(12));
				dto.setPhone((String) list.get(13));
				dto.setMphone((String) list.get(14));
				dto.setEmail((String) list.get(15));
				dto.setIdProofNo((String) list.get(16));
				dto.setDespositDate((String) list.get(20));
				dto.setWillId((String) list.get(21));
				dto.setWillStatus((String) list.get(22));
				dto.setIdProofNo((String) list.get(23));
			}
		}
		return dto;
	}

	/**
	 * @param willid
	 * @return WillDepositDTO
	 * @throws Exception
	 */
	public WillDepositDTO getwillIdDetails(String willid, String StrFunId)
			throws // remove and use it in delivery dao
			Exception {
		WillDepositDTO dto = new WillDepositDTO();
		WillDepositDAO dao = new WillDepositDAO();
		ArrayList ret = dao.getwillIdDetails(willid);

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList list = (ArrayList) ret.get(i);
				dto.setWillId((String) list.get(0));
				dto.setFirstName((String) list.get(1));
				dto.setMidName((String) list.get(2));
				dto.setLastName((String) list.get(3));
				dto.setGuardianName((String) list.get(4));
				dto.setDespositDate((String) list.get(5));
				dto.setAddress((String) list.get(6));
				dto.setWillStatus((String) list.get(7));
				dto.setGender((String) list.get(8));
				dto.setAge((String) list.get(9));
				dto.setCountry((String) list.get(10));
				dto.setState((String) list.get(11));
				dto.setDistrict((String) list.get(12));
				dto.setPin((String) list.get(13));
				dto.setPhone((String) list.get(14));
				dto.setMphone((String) list.get(15));
				dto.setEmail((String) list.get(16));
				dto.setIdProof((String) list.get(17));
				dto.setWithdrawlDueDate((String) list.get(22));
			}
		}

		ArrayList list = dao.getWillFeeValue(StrFunId);
		ArrayList retList1 = (ArrayList) list.get(0);
		String feeVal = ((String) retList1.get(1));
		dto.setFee(feeVal);

		return dto;
	}

	/**
	 * @param willId
	 * @param fromDate
	 * @param toDate
	 * @return ArrayList
	 * @throws Exception
	 *             //for withdrawal BD
	 */
	public ArrayList getWillWIthdrawalDetails(String willId, String fromDate,
			String toDate) throws Exception {
		WillDepositDAO dao = new WillDepositDAO();

		ArrayList list = dao.getWillWIthdrawalDetails(willId, fromDate, toDate);

		ArrayList returnList = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList lst = (ArrayList) list.get(i);
				WillDepositDTO dto = new WillDepositDTO();
				dto.setFirstName((String) lst.get(0));
				dto.setMidName((String) lst.get(1));
				dto.setLastName((String) lst.get(2));
				dto.setAddress((String) lst.get(3));
				dto.setDespositDate((String) lst.get(4));
				dto.setWillId((String) lst.get(5));

				returnList.add(dto);
			}
		}
		return returnList;
	}

	/**
	 * @param willDepDTO
	 * @return boolean
	 * @throws Exception
	 */
	public String insertDepositionDetails(WillDepositDTO willDepDTO,
			String willId, String lang) throws Exception {

		String rType = willDepDTO.getAgentType();
		WillDepositDAO dao = new WillDepositDAO();
		String[] depositParam = new String[22];
		boolean insert = false;
		// String willId="";
		logger.debug(rType);

		if (CommonConstant.WILL_AGENT.equalsIgnoreCase(rType)) {

			String[] param = new String[22];
			param[0] = willDepDTO.getAgentType();
			param[1] = willDepDTO.getAgentFirstName();
			param[2] = willDepDTO.getAgentMidName();
			param[3] = willDepDTO.getAgentLastName();
			param[4] = willDepDTO.getAgentGender();
			if ("M".equalsIgnoreCase(willDepDTO.getAgentGender())) {
				if ("hi".equalsIgnoreCase(lang)) {
					willDepDTO.setAgentGender("पुस्र्ष");
				} else {
					willDepDTO.setAgentGender("Male");
				}
			} else {
				if ("hi".equalsIgnoreCase(lang)) {
					willDepDTO.setAgentGender("महिला");
				} else {
					willDepDTO.setAgentGender("Female");
				}
			}
			param[5] = willDepDTO.getAgentAge();
			param[6] = willDepDTO.getAgentFatherName();
			param[7] = willDepDTO.getAgentMotherName();
			param[8] = willDepDTO.getAgentSpouseName();
			param[9] = willDepDTO.getAgentAddress();
			param[10] = willDepDTO.getAgentCountry();
			param[11] = willDepDTO.getAgentState();
			param[12] = willDepDTO.getAgentCity();
			param[13] = willDepDTO.getAgentPin();
			param[14] = willDepDTO.getAgentPhone();
			param[15] = willDepDTO.getAgentMPhone();
			param[16] = willDepDTO.getAgentEmail();
			param[17] = willDepDTO.getAgentIdProof();
			WillCommonBD bdd = new WillCommonBD();
			willDepDTO.setAgentIdProof(bdd.getIdType(willDepDTO
					.getAgentIdProof(), lang));

			param[18] = willDepDTO.getAgentIdProofNo();
			param[19] = willDepDTO.getAgentBankName();
			param[20] = willDepDTO.getAgentBankAddress();

			param[21] = willId;
			boolean isinserted = false;
			isinserted = dao.insertDepositionDetails(param,
					CommonConstant.WILL_AGENT, willDepDTO, willId, lang);
			willId = param[21];

			if (isinserted) {
				try {
					if (con == null || con.isClosed()) {
						con = dbUtil.getDBConnection();
					}
					// commented after integration with devices
					/*
					 * String
					 * docpath=willDepDTO.getSignPath()+willDepDTO.getSignatureName
					 * (); uploadFile(willDepDTO.getDocContents(),
					 * willDepDTO.getDocName(), docpath);
					 * uploadFile(willDepDTO.getSignatureContents(),
					 * willDepDTO.getSignature(), docpath);
					 * uploadFile(willDepDTO.getThumbContents(),
					 * willDepDTO.getThumb(), docpath);
					 * uploadFile(willDepDTO.getAgentProofContents(),
					 * willDepDTO.getAgentsproof(), docpath);
					 */

					PreparedStatement prepStmt;
					int rowCount = 0;
					String sqlQuery = "Update IGRS_WILL_TXN_PARTY SET PHOTO_PATH=?,"
							+ "THUMB_PATH=?,SIGNATURE_PATH=?,PHOTO_NAME=?,THUMB_NAME=?, SIGNATURE_NAME=?,AGENT_PROOF_PATH=?,AGENT_PROOF_NAME=? WHERE WILL_TXN_ID=? and PARTY_TYPE_ID='AGENT'";
					prepStmt = con.prepareStatement(sqlQuery);
					/*
					 * prepStmt.setBinaryStream(1, new
					 * ByteArrayInputStream(willDepDTO.getDocContents()),
					 * willDepDTO.getDocContents().length);
					 * prepStmt.setBinaryStream(2, new
					 * ByteArrayInputStream(willDepDTO.getThumbContents()),
					 * willDepDTO.getThumbContents().length);
					 * prepStmt.setBinaryStream(3, new
					 * ByteArrayInputStream(willDepDTO.getSignatureContents()),
					 * willDepDTO.getSignatureContents().length);
					 */
					// AA
					prepStmt.setString(1, willDepDTO.getSignPath()
							+ willDepDTO.getUniqueId() + "/"
							+ willDepDTO.getDocName());
					prepStmt.setString(2, willDepDTO.getSignPath()
							+ willDepDTO.getUniqueId() + "/"
							+ willDepDTO.getThumb());
					prepStmt.setString(3, willDepDTO.getSignPath()
							+ willDepDTO.getUniqueId() + "/"
							+ willDepDTO.getSignature());

					// AA
					prepStmt.setString(4, willDepDTO.getDocName());
					prepStmt.setString(5, willDepDTO.getThumb());
					prepStmt.setString(6, willDepDTO.getSignature());
					// prepStmt.setBinaryStream(7, new
					// ByteArrayInputStream(willDepDTO.getAgentProofContents()),
					// willDepDTO.getAgentProofContents().length);
					prepStmt.setString(7, willDepDTO.getSignPath()
							+ willDepDTO.getUniqueId() + "/"
							+ willDepDTO.getAgentProofName());

					prepStmt.setString(8, willDepDTO.getAgentProofName());
					prepStmt.setString(9, willId);
					rowCount = prepStmt.executeUpdate();
					logger.debug("Will Files Deposited : RowCount " + rowCount);
					prepStmt.close();
					con.commit();
					/*
					 * String docpath="E:/Upload/99/01/"+willId+"/";
					 * uploadFile(willDepDTO.getDocContents(),
					 * willDepDTO.getDocName(), docpath);
					 * uploadFile(willDepDTO.getSignatureContents(),
					 * willDepDTO.getSignature(), docpath);
					 * uploadFile(willDepDTO.getThumbContents(),
					 * willDepDTO.getThumb(), docpath);
					 * uploadFile(willDepDTO.getAgentProofContents(),
					 * willDepDTO.getAgentsproof(), docpath);
					 */

				} catch (Exception e) {
					logger.debug("Exception in inserting files in Will Deposit"
							+ e);
				}

				// insert = dao.insertPhotoDetails(willDepDTO, willId);
			}

		} else if (CommonConstant.WILL_TESTATOR.equalsIgnoreCase(rType)) {

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
			willDepDTO.setIdProof(bdd.getIdType(willDepDTO.getIdProof(), lang));
			param[18] = willDepDTO.getIdProofNo();
			param[19] = willDepDTO.getBankName();
			param[20] = willDepDTO.getBankAddress();

			// param[19] = willDepDTO.getRetrievalDueDate();
			// param[19] = willDepDTO.getWillRetrievalReason();
			// aram[19] = CommonConstant.WILL_RETRIEVE_FLAG;
			param[21] = willId;

			insert = dao.insertDepositionDetails(param,
					CommonConstant.WILL_TESTATOR, willDepDTO, willId, lang);
			if (insert) {
				try {
					if (con == null || con.isClosed()) {
						con = dbUtil.getDBConnection();
					}
					// commented after integration with devices
					/*
					 * String docpath="D:/Upload/14/01/"+willId+"/";
					 * uploadFile(willDepDTO.getDocContents(),
					 * willDepDTO.getDocName(), docpath);
					 * uploadFile(willDepDTO.getSignatureContents(),
					 * willDepDTO.getSignature(), docpath);
					 * uploadFile(willDepDTO.getThumbContents(),
					 * willDepDTO.getThumb(), docpath);
					 */
					PreparedStatement prepStmt;
					int rowCount = 0;
					String sqlQuery = "Update IGRS_WILL_TXN_PARTY SET PHOTO_PATH=?,"
							+ "THUMB_PATH=?,SIGNATURE_PATH=?,PHOTO_NAME=?,THUMB_NAME=?,SIGNATURE_NAME=? WHERE WILL_TXN_ID=? and PARTY_TYPE_ID='TESTATOR'";
					prepStmt = con.prepareStatement(sqlQuery);
					/*
					 * prepStmt.setBinaryStream(1, new
					 * ByteArrayInputStream(willDepDTO.getDocContents()),
					 * willDepDTO.getDocContents().length);
					 * prepStmt.setBinaryStream(2, new
					 * ByteArrayInputStream(willDepDTO.getThumbContents()),
					 * willDepDTO.getThumbContents().length);
					 * prepStmt.setBinaryStream(3, new
					 * ByteArrayInputStream(willDepDTO.getSignatureContents()),
					 * willDepDTO.getSignatureContents().length);
					 */
					prepStmt.setString(1, willDepDTO.getSignPath()
							+ willDepDTO.getUniqueId() + "/"
							+ willDepDTO.getDocName());
					prepStmt.setString(2, willDepDTO.getSignPath()
							+ willDepDTO.getUniqueId() + "/"
							+ willDepDTO.getThumb());
					prepStmt.setString(3, willDepDTO.getSignPath()
							+ willDepDTO.getUniqueId() + "/"
							+ willDepDTO.getSignature());

					prepStmt.setString(4, willDepDTO.getDocName());
					prepStmt.setString(5, willDepDTO.getThumb());
					prepStmt.setString(6, willDepDTO.getSignature());
					prepStmt.setString(7, willId);
					rowCount = prepStmt.executeUpdate();
					logger.debug("Will Files Deposited : RowCount " + rowCount);
					prepStmt.close();
					con.commit();

				} catch (Exception x) {
					logger.debug("" + x.getMessage());
					try {
						dbUtil.rollback();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						logger.debug("" + e.getMessage());
					}
				}finally{
					try {
						dbUtil.closeConnection();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// finally {
				// try {
				// dbUtil.closeConnection();
				// } catch (Exception ex) {
				// logger.debug("Exception in insertWillDeposit() :-" + ex);
				// }
				// }
				// insert = dao.insertPhotoDetails(willDepDTO, willId);
			}
			willId = param[21];
		}
		return willId;
	}

	public String insertWillDetails(String status, String userId, String city,
			String remarks, String funId, Float total, WillDepositDTO formDTO,
			String officeId, String lang) throws Exception {
		String will = "";

		String willId = "";
		WillViewBD viewBD = new WillViewBD();
		String districtId = viewBD.getDistrictId(officeId);
		WillDepositDAO dao = new WillDepositDAO();
		willId = willIDgenerator(districtId, "01");

		if (willId != null) {

			insertDepositionDetails(formDTO, willId, lang);
			formDTO.setWillId(willId);

			formDTO.setTotalFee(Float.toString(total));
		}

		WillViewBD view = new WillViewBD();
		userId = view.getName(userId);

		will = dao.insertWillDetails(status, userId, willId, remarks, funId,
				total);

		return willId;

	}

	public String insertWillDetails1(String status, String userId, String city,
			String remarks, String funId, Float total, WillDepositDTO formDTO,
			String willId, String lang) throws Exception {
		String will = "";

		WillDepositDAO dao = new WillDepositDAO();

		if (willId != null) {
			deleteDetails(willId);
			insertDepositionDetails(formDTO, willId, lang);
			formDTO.setWillId(willId);

			formDTO.setTotalFee(Float.toString(total));
		}

		will = dao.insertWillDetails(status, userId, willId, remarks, funId,
				total);

		return willId;

	}

	// delete will details action
	public void deleteDetails(String willId) throws Exception {
		WillDepositDAO dao = new WillDepositDAO();
		dao.deleteDetails(willId);
	}

	/**
	 * @return String
	 */
	public String willIDgenerator(String distID, String refType) {
		String willId = new CommonUtil().getWillId(distID, refType);
		return willId;
	}

	public ArrayList getWillRemarks(String willid) throws Exception {

		WillDepositDAO dao = new WillDepositDAO();
		String[] param = new String[1];
		param[0] = willid;
		ArrayList ret = dao.getWillRemarks(param);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				WillDepositDTO dto = new WillDepositDTO();
				dto.setRemarks((String) lst.get(0));
				dto.setWillCommentsUser((String) lst.get(1));
				dto.setWillCommentsDate((String) lst.get(2));
				list.add(dto);
			}
		}
		return list;

	}

	public String displayObjectDetails(HttpServletResponse res, String willId,
			String type) throws Exception {
		WillDepositDAO dao = new WillDepositDAO();
		String status = dao.readBLOBToFileGet(res, willId, type);
		return status;
	}

	public WillDepositDTO getFunctionName(String funId) throws Exception {
		WillDepositDTO dto = new WillDepositDTO();
		WillDepositDAO dao = new WillDepositDAO();

		String[] param = new String[1];
		param[0] = funId;

		ArrayList ret = dao.getFunctionName(param);

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList list = (ArrayList) ret.get(0);
				String pFunName = (String) list.get(0);
				String pModName = (String) list.get(1);
				logger.debug("pFunName = " + pFunName + "pModName = "
						+ pModName);
				dto.setParentFunName((String) list.get(0));
				dto.setParentModName((String) list.get(1));

			}
		}

		return dto;
	}

	

	// ANUJ
	public ArrayList pendingWithPayment(String districtId, float total)
			throws Exception {
		int valueOfTotal = (int)total;
		ArrayList FinalList = new ArrayList();
		ArrayList list = new ArrayList();
		WillDepositDAO WillDepositDAO = new WillDepositDAO();

		list = WillDepositDAO.getPendingWillWithdraw(districtId);
		// business logic
		ArrayList rowList;
		WillDepositDashBoard objDashboard = new WillDepositDashBoard();
		try {
			for (int i = 0; i < list.size(); i++) {

				rowList = (ArrayList) list.get(i);
				objDashboard.setSrNo(i + 1);

				objDashboard.setWill_txn_id((String) rowList.get(0));
				objDashboard.setNo_of_parties((String) rowList.get(1));

				if ((String) rowList.get(2) == ""
						|| (String) rowList.get(2) == null) {
					objDashboard.setPaid_amount("0");
				} else {
					objDashboard.setPaid_amount((String) rowList.get(2));
				}

				if ((String) rowList.get(3) == ""
						|| (String) rowList.get(3) == null) {
					objDashboard.setDue_amount(String.valueOf(valueOfTotal));
				} else {
					objDashboard.setDue_amount((String) rowList.get(3));
				}
				if ((String) rowList.get(4) == ""
						|| (String) rowList.get(4) == null) {
					objDashboard.setCreated_date("-");
				} else {
					objDashboard.setCreated_date((String) rowList.get(4));
				}

				if ((String) rowList.get(5) == ""
						|| (String) rowList.get(5) == null) {
					objDashboard.setUpdated_date("-");
				} else {
					objDashboard.setUpdated_date((String) rowList.get(5));
				}

				objDashboard.setPayable_amount((String) rowList.get(6));

				if ((String) rowList.get(3) == "") {
					objDashboard.setWillStatus((String) rowList.get(0) + "~"
							+ String.valueOf(valueOfTotal));
				} else {
					objDashboard.setWillStatus((String) rowList.get(0) + "~"
							+ (String) rowList.get(3));
				}

				FinalList.add(objDashboard);
				objDashboard = new WillDepositDashBoard();

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		// end of bl

		return FinalList;
	}

	public String updateWillWithdrawDetails1(String userId, String willId,
			float total, String funId) throws Exception {
		WillDepositDAO dao = new WillDepositDAO();

		String willUpdate = dao.insertWillWithDrawDetails1(userId, willId,
				total, funId);
		return willUpdate;
	}

	public String checkStatus(String willId, String funId) throws Exception {
		WillDepositDAO dao = new WillDepositDAO();
		String willId1 = willId;
		String status = "";
		ArrayList alist = new ArrayList();
		alist = dao.checkStatus(willId);
		try {
			ArrayList rowList = (ArrayList) alist.get(0);

			String dt = (String) rowList.get(0);
			String paid = (String) rowList.get(1);
			if ("null".equalsIgnoreCase(dt)) {
				dt = "300";
			}
			if (dt == null) {
				dt = "300";
			}
			if (dt == "0") {
				dt = "300";

			}
			if ("0".equalsIgnoreCase(dt)) {
				dt = "300";

			}

			if ("null".equalsIgnoreCase(paid)) {
				paid = "0";
			}
			if (paid == null) {
				paid = "0";

			}
			int i = Integer.parseInt(dt);
			int k = Integer.parseInt(paid);
			int f = i - k;

			if (f <= 0) {

				dao.updateStatus(willId1, funId);
				if ("03".equalsIgnoreCase(willId1.substring(10, 12))) {
					status = "paymentSuccessRet";
				} else if ("02".equalsIgnoreCase(willId1.substring(10, 12))) {
					status = "paymentSuccessWith";
				} else if ("01".equalsIgnoreCase(willId.substring(10, 12))) {
					status = "paymentSuccess";
				}
			} else {
				status = "pending";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;

	}

	public int amountLeft(String willId, String total) {
		int f = 0;
		try {
			WillDepositDAO dao = new WillDepositDAO();
			ArrayList alist = new ArrayList();
			alist = dao.checkStatus(willId);

			ArrayList rowList = (ArrayList) alist.get(0);

			String dt = (String) rowList.get(0);
			String paid = (String) rowList.get(1);
			if ("null".equalsIgnoreCase(dt)) {
				dt = total;
			}
			if (dt == null) {
				dt = total;
			}
			if ("0".equalsIgnoreCase(dt)) {
				dt = total;
			}
			if (dt == "0") {
				dt = total;
			}

			if ("null".equalsIgnoreCase(paid)) {
				paid = "0";
			}
			if (paid == null) {
				paid = "0";

			}
			if ("".equalsIgnoreCase(paid)) {
				paid = "0";
			}
			int i = Integer.parseInt(dt);
			int k = Integer.parseInt(paid);
			f = i - k;
		} catch (Exception e) {

		}
		return f;
	}

	private boolean uploadFile(byte[] abc, String fileName, String filePath) {

		try {
			File folder = new File(filePath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			File newFile = new File(filePath, fileName);
			// FileInputStream fis= new FileInputStream(newFile);

			logger.info("NEW FILE NAME:-" + newFile);
			FileOutputStream fos = new FileOutputStream(newFile);
			fos.write(abc);
			fos.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

	public boolean uploadFileCheck(String filePath) {
		logger.debug("fileoath is>>" + filePath);
		boolean returnType = false;

		try {
			File folder = new File(filePath);
			if (!folder.exists()) {
				// folder.mkdirs();
				logger.debug("file doesnt exist");
				return false;
			} else {
				logger.debug("file exists");
				return true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return returnType;
	}

	// for fees start
	public String getFees() throws Exception {
		HttpServletRequest request = null;
		HttpSession session = request.getSession(true);
		String StrFunId = (String) session.getAttribute("functionId");
		String otherFeeVal;
		String fees = "";
		float total = 0;
		String userType = "SRO";
		String serviceId = null;
		String feeVal = getWillFeeValue(StrFunId) == null ? "0.0"
				: getWillFeeValue(StrFunId);
		if (feeVal != null)
			if (!feeVal.equalsIgnoreCase("")) {
				// formDTO.setFee(feeVal);
			}
		logger.debug("fee is:" + feeVal);
		otherFeeVal = getWillOtherFeeValue(StrFunId, serviceId, userType) == null ? "0.0"
				: getWillOtherFeeValue(StrFunId, serviceId, userType);
		logger.debug("Otherfee is:" + otherFeeVal);
		if (otherFeeVal != null) {
			// formDTO.setOtherFee(otherFeeVal);
			total = Float.parseFloat(feeVal == null ? "0.0" : feeVal)
					+ Float.parseFloat(otherFeeVal == null ? "0.0"
							: otherFeeVal);
			fees = String.valueOf(total);
			logger.debug("total fee is:" + fees);

		}
		return fees;
	}
	// for fees end
}
