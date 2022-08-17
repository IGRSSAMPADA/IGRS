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
 * FILE NAME: WillWithdrawalBD.java
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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.db.DataSourceLookUp;
import com.wipro.igrs.util.CommonUtil;
import com.wipro.igrs.util.PropertiesFileReader;
import com.wipro.igrs.willdeposit.constant.CommonConstant;
import com.wipro.igrs.willdeposit.dao.WillCommonDAO;
import com.wipro.igrs.willdeposit.dao.WillRetrievalDAO;
import com.wipro.igrs.willdeposit.dao.WillWithdrawalDAO;
import com.wipro.igrs.willdeposit.dto.WillDepositDTO;
import com.wipro.igrs.willdeposit.dto.WillWithdrawDashBoard;
import com.wipro.igrs.willdeposit.sql.CommonSQL;

/**
 * @author NIHAR M.
 * 
 */
public class WillWithdrawalBD {

	private Logger logger = (Logger) Logger.getLogger(WillWithdrawalBD.class);
	WillWithdrawalDAO withdrawalDAO = null;
	Connection con = null;
	DBUtility dbUtil;

	/**
	 * @param willId
	 * @param fromDate
	 * @param toDate
	 * @return ArrayList
	 * @throws Exception
	 */
	// public ArrayList getWillWIthdrawalDetails(String willId, String fromDate,
	// String toDate) throws Exception {
	//
	// withdrawalDAO = new WillWithdrawalDAO();
	// ArrayList list = withdrawalDAO.getWillWIthdrawalDetails(willId,
	// fromDate, toDate);
	//
	// ArrayList returnList = new ArrayList();
	// if (list != null) {
	// for (int i = 0; i < list.size(); i++) {
	// ArrayList lst = (ArrayList) list.get(i);
	// WillDepositDTO dto = new WillDepositDTO();
	// dto.setWillId((String) lst.get(0));
	// dto.setFirstName((String) lst.get(1));
	// dto.setMidName((String) lst.get(2));
	// dto.setLastName((String) lst.get(3));
	// dto.setAddress((String) lst.get(4));
	// dto.setDespositDate((String) lst.get(5));
	//
	// returnList.add(dto);
	// }
	// }
	// return returnList;
	// }
	//
	// /**
	// * @param willid
	// * @param flag
	// * @return WillDepositDTO
	// * @throws Exception
	// */
	// public WillDepositDTO getWillDeposit(String willid, String StrFunId)
	// throws Exception {
	// WillDepositDTO dto = new WillDepositDTO();
	// WillViewDAO dao = new WillViewDAO();
	// WillCommonDAO commonDAO = new WillCommonDAO();
	// ArrayList ret = dao.getwillIdDetails(willid);
	//
	// if (ret != null) {
	// for (int i = 0; i < ret.size(); i++) {
	// ArrayList list = (ArrayList) ret.get(i);
	//
	// dto.setWillId((String) list.get(0));
	//
	// dto.setWillStatus((String) list.get(5));
	// dto.setFirstName((String) list.get(13));
	// dto.setMidName((String) list.get(14));
	// dto.setLastName((String) list.get(15));
	// dto.setGender((String) list.get(17));
	// // dto.setAge(Integer.parseInt((String) list.get(18)));
	// dto.setFatherName((String) list.get(19));
	// dto.setMotherName((String) list.get(20));
	// dto.setDeliveryDueDate((String) list.get(10));
	//
	// String agent = (String) list.get(1);
	// if (agent.equalsIgnoreCase("A")) {
	//
	// dto.setAgent((String) list.get(1));
	// dto.setAgentFirstName((String) list.get(2));
	// dto.setAgentMidName((String) list.get(3));
	// dto.setAgentLastName((String) list.get(4));
	// dto.setGender((String) list.get(6));
	// dto.setAge((String) list.get(7));
	// dto.setFatherName((String) list.get(8));
	// dto.setMotherName((String) list.get(9));
	//
	// }
	// }
	// }
	//
	// ArrayList list = commonDAO.getWillFeeValue(StrFunId);
	// ArrayList retList1 = null;
	// String feeVal = "0.0";
	// if (retList1 != null && retList1.size() > 0) {
	// retList1 = (ArrayList) list.get(0);
	// feeVal = ((String) retList1.get(1));
	// }
	// dto.setFee(feeVal);
	//
	// return dto;
	// }
	// ANUJ start
	public String updateWillWithdrawDetails1(WillDepositDTO willDepDTO,
			String userId, String willId, String status, String remarks,
			float total, String funId) throws Exception {
		String willUpdate = withdrawalDAO.insertWillWithDrawDetails1(status,
				userId, getOracleDate(willDepDTO.getWithdrawlDueDate()),
				willId, "WI", "W", total, funId);
		return willUpdate;
	}

	public String updateWillWithdrawDetails2(WillDepositDTO willDepDTO,
			String userId, String willId, String status, String remarks,
			float total, String funId) throws Exception {
		String willUpdate = withdrawalDAO.insertWillWithDrawDetails1(status,
				userId, getOracleDate(willDepDTO.getWithdrawlDueDate()),
				willId, "RI", "W", total, funId);
		return willUpdate;
	}

	// ANUJ end

	public String updateWillWithDrawDetails(WillDepositDTO willDepDTO,
			String userId, String willId, String status, String remarks,
			float total, String funId, String officeId, String lang) throws Exception {
		WillViewBD viewBD = new WillViewBD();
		WillCommonDAO dd = new WillCommonDAO();
		String districtId = viewBD.getDistrictId(officeId);
		String rType = willDepDTO.getAgent();
		withdrawalDAO = new WillWithdrawalDAO();
		String[] depositParam = new String[22];
		String newWillId = "";
		if (willDepDTO.getWithWillId() != null) {
			newWillId = willDepDTO.getWithWillId();
			WillRetrievalDAO dao = new WillRetrievalDAO();
			dao.deleteDetails(newWillId);
			willDepDTO.setRetWillId(newWillId);
		}

		else {
			newWillId = new CommonUtil().getWillId(districtId, "02");
			willDepDTO.setWithWillId(newWillId);
			willDepDTO.setRetWillId(newWillId);

		}
		String willUpdate = withdrawalDAO.insertWillWithDrawDetails(status,
				userId, newWillId,
				getOracleDate(willDepDTO.getWithdrawlDueDate()), willId, "WI",
				"W", total, funId);

		if (willUpdate != null) {
			if (CommonConstant.WILL_AGENT.equalsIgnoreCase(rType)) {
				String[] param = new String[22];
				param[0] = "AGENT";
				param[1] = willDepDTO.getAgentFirstName();
				param[2] = willDepDTO.getAgentMidName();
				param[3] = willDepDTO.getAgentLastName();
				if (willDepDTO.getAgentGender().equalsIgnoreCase("MALE"))
					param[4] = "M";
				else
					param[4] = "F";
				param[5] = willDepDTO.getAgentAge();
				param[6] = willDepDTO.getAgentFatherName();
				param[7] = willDepDTO.getAgentMotherName();
				param[8] = willDepDTO.getAgentSpouseName();
				param[9] = willDepDTO.getAgentAddress();
				String countryID = withdrawalDAO.getCountryID(willDepDTO
						.getAgentCountry());
				param[10] = countryID;
				String stateID = withdrawalDAO.getStateID(willDepDTO
						.getAgentState());
				param[11] = stateID;
				String districtID = withdrawalDAO.getdistrictId(willDepDTO
						.getAgentCity());
				param[12] = districtID;
				param[13] = willDepDTO.getAgentPin();
				param[14] = willDepDTO.getAgentPhone();
				param[15] = willDepDTO.getAgentMPhone();
				param[16] = willDepDTO.getAgentEmail();
				
				//param[17] = willDepDTO.getAgentIdProof();
				param[17]= dd.getTypeId(willDepDTO.getAgentIdProof(), lang);
				param[18] = willDepDTO.getAgentIdProofNo();
				param[19] = willDepDTO.getAgentBankName();
				param[20] = willDepDTO.getAgentBankAddress();

				param[21] = newWillId;
				// param[22] = willId;
				boolean willUpdate1 = withdrawalDAO.insertWillWithDrawl(param,
						CommonConstant.WILL_AGENT, willDepDTO, newWillId,
						willId, lang);

				if (willUpdate1) {
					try {
						if (con == null || con.isClosed()) {
							con = dbUtil.getDBConnection();
						}

						/*
						 * String docpath="E:/Upload/99/02/"+newWillId+"/";
						 * uploadFile(willDepDTO.getRetDocContents(),
						 * willDepDTO.getDocName(), docpath);
						 * uploadFile(willDepDTO.getRetSignatureContents(),
						 * willDepDTO.getSignature(), docpath);
						 * uploadFile(willDepDTO.getRetThumbContents(),
						 * willDepDTO.getThumb(), docpath);
						 * uploadFile(willDepDTO.getDeathCertDocContents(),
						 * willDepDTO.getDeathCerti(), docpath);
						 */
						/*
						 * PreparedStatement prepStmt; int rowCount = 0; String
						 * sqlQuery =
						 * "Update IGRS_WILL_TXN_PARTY SET PHOTO_PATH=?," +
						 * "THUMB_PATH=?,SIGNATURE_PATH=?,PHOTO_NAME=?,THUMB_NAME=?, SIGNATURE_NAME=?,AGENT_PROOF_PATH=?,AGENT_PROOF_NAME=? WHERE WILL_TXN_ID=? and PARTY_TYPE_ID='AGENT'"
						 * ; prepStmt = con.prepareStatement(sqlQuery);
						 * 
						 * prepStmt.setBinaryStream(1, new
						 * ByteArrayInputStream(willDepDTO.getDocContents()),
						 * willDepDTO.getDocContents().length);
						 * prepStmt.setBinaryStream(2, new
						 * ByteArrayInputStream(willDepDTO.getThumbContents()),
						 * willDepDTO.getThumbContents().length);
						 * prepStmt.setBinaryStream(3, new
						 * ByteArrayInputStream(willDepDTO
						 * .getSignatureContents()),
						 * willDepDTO.getSignatureContents().length);
						 * 
						 * // AA prepStmt.setString(1,
						 * willDepDTO.getPhotoPath()); prepStmt.setString(2,
						 * willDepDTO.getThumbPath()); prepStmt.setString(3,
						 * willDepDTO.getSignPath());
						 * 
						 * // AA prepStmt.setString(4,
						 * willDepDTO.getDocumentName()); prepStmt.setString(5,
						 * willDepDTO.getThumb()); prepStmt.setString(6,
						 * willDepDTO.getSignature()); //
						 * prepStmt.setBinaryStream(7, new //
						 * ByteArrayInputStream
						 * (willDepDTO.getAgentProofContents()), //
						 * willDepDTO.getAgentProofContents().length);
						 * prepStmt.setString(7,
						 * willDepDTO.getAgentProofPath());
						 * 
						 * prepStmt.setString(8, willDepDTO.getDeathCerti());
						 * prepStmt.setString(9, newWillId); rowCount =
						 * prepStmt.executeUpdate();
						 * logger.debug("Will Files Deposited : RowCount " +
						 * rowCount); prepStmt.close(); con.commit();
						 */
						String prm[] = new String[9];
						prm[0] = willDepDTO.getPhotoPath();
						prm[1] = willDepDTO.getThumbPath();
						prm[2] = willDepDTO.getSignPath();
						prm[3] = willDepDTO.getDocumentName();
						prm[4] = willDepDTO.getThumb();
						prm[5] = willDepDTO.getSignature();
						prm[6] = willDepDTO.getAgentProofPath();
						prm[7] = willDepDTO.getAgentProofName();
						prm[8] = newWillId;

						withdrawalDAO
								.insertToDB(CommonSQL.UPDT_PRTY_DTLS4, prm);

					} catch (Exception e) {
						logger.debug("Exception in inserting files in Will Deposit"
								+ e);
					}
					// insert = dao.insertPhotoDetails(willDepDTO, willId);
				}
				willId = param[21];

			} else // if (CommonConstant.WILL_TESTATOR.equalsIgnoreCase(rType))
					// {
			{
				String[] param = new String[22];
				param[0] = "TESTATOR";
				param[1] = willDepDTO.getFirstName();
				param[2] = willDepDTO.getMidName();
				param[3] = willDepDTO.getLastName();
				if (willDepDTO.getGender().equalsIgnoreCase("MALE"))
					param[4] = "M";
				else
					param[4] = "F";
				param[5] = willDepDTO.getAge();
				param[6] = willDepDTO.getFatherName();
				param[7] = willDepDTO.getMotherName();
				param[8] = willDepDTO.getSpouseName();
				param[9] = willDepDTO.getAddress();
				String countryID = withdrawalDAO.getCountryID(willDepDTO
						.getCountry());
				param[10] = countryID;
				String stateID = withdrawalDAO
						.getStateID(willDepDTO.getState());
				param[11] = stateID;
				String districtID = withdrawalDAO.getdistrictId(willDepDTO
						.getCity());
				param[12] = districtID;
				// param[10] = willDepDTO.getCountry();
				// param[11] = willDepDTO.getState();
				// param[12] = willDepDTO.getCity();
				param[13] = willDepDTO.getPin();
				param[14] = willDepDTO.getPhone();
				param[15] = willDepDTO.getMphone();
				param[16] = willDepDTO.getEmail();
				param[17] = dd.getTypeId(willDepDTO.getIdProof(), lang);
				param[18] = willDepDTO.getIdProofNo();
				param[19] = willDepDTO.getBankName();
				param[20] = willDepDTO.getBankAddress();
				param[21] = newWillId;

				boolean willUpdate1 = withdrawalDAO.insertWillWithDrawl(param,
						CommonConstant.WILL_TESTATOR, willDepDTO, newWillId,
						willId, lang);
				if (willUpdate1) {
					try {
						if (con == null || con.isClosed()) {
							con = dbUtil.getDBConnection();
						}
						/*
						 * String docpath="E:/Upload/99/02/"+newWillId+"/";
						 * uploadFile(willDepDTO.getDocContents(),
						 * willDepDTO.getDocName(), docpath);
						 * uploadFile(willDepDTO.getThumbContents(),
						 * willDepDTO.getThumb(), docpath);
						 * uploadFile(willDepDTO.getSignatureContents(),
						 * willDepDTO.getSignature(), docpath);
						 */

						/*
						 * PreparedStatement prepStmt; int rowCount = 0; String
						 * sqlQuery =
						 * "Update IGRS_WILL_TXN_PARTY SET PHOTO_PATH=?," +
						 * "THUMB_PATH=?,SIGNATURE_PATH=?,PHOTO_NAME=?,THUMB_NAME=?,SIGNATURE_NAME=? WHERE WILL_TXN_ID=? and PARTY_TYPE_ID='TESTATOR'"
						 * ; prepStmt = con.prepareStatement(sqlQuery);
						 * 
						 * prepStmt.setBinaryStream(1, new
						 * ByteArrayInputStream(willDepDTO.getDocContents()),
						 * willDepDTO.getDocContents().length);
						 * prepStmt.setBinaryStream(2, new
						 * ByteArrayInputStream(willDepDTO.getThumbContents()),
						 * willDepDTO.getThumbContents().length);
						 * prepStmt.setBinaryStream(3, new
						 * ByteArrayInputStream(willDepDTO
						 * .getSignatureContents()),
						 * willDepDTO.getSignatureContents().length);
						 * 
						 * prepStmt.setString(1, willDepDTO.getPhotoPath());
						 * prepStmt.setString(2, willDepDTO.getSignPath());
						 * prepStmt.setString(3, willDepDTO.getThumbPath());
						 * 
						 * prepStmt.setString(4, willDepDTO.getDocumentName());
						 * prepStmt.setString(5, willDepDTO.getThunmbName());
						 * prepStmt.setString(6, willDepDTO.getSignatureName());
						 * prepStmt.setString(7, newWillId); rowCount =
						 * prepStmt.executeUpdate();
						 * logger.debug("Will Files Deposited : RowCount " +
						 * rowCount); prepStmt.close(); con.commit();
						 */
						String prm[] = new String[7];
						prm[0] = willDepDTO.getPhotoPath();
						prm[1] = willDepDTO.getSignPath();
						prm[2] = willDepDTO.getThumbPath();
						prm[3] = willDepDTO.getDocumentName();
						prm[4] = willDepDTO.getThunmbName();
						prm[5] = willDepDTO.getSignatureName();
						prm[6] = newWillId;

						withdrawalDAO
								.insertToDB(CommonSQL.UPDT_PRTY_DTLS5, prm);

					} catch (Exception e) {
						logger.debug("Exception in inserting files in Will Deposit"
								+ e);
					}
					// insert = dao.insertPhotoDetails(willDepDTO, willId);
				}
			}
		}

		return willUpdate;

	}

	public String updateWillRetrivalDetails(WillDepositDTO willDepDTO,
			String userId, String willId, String status, String remarks,
			float total, String funId, String officeId, String lang) throws Exception {
		String newWillId = "";
		String rType = willDepDTO.getAgent();
		withdrawalDAO = new WillWithdrawalDAO();
		String[] depositParam = new String[22];
WillCommonDAO dd = new WillCommonDAO();
		if (willDepDTO.getRetWillId() != null) {
			WillRetrievalDAO dao = new WillRetrievalDAO();
			dao.deleteDetails(willDepDTO.getRetWillId());
			newWillId = willDepDTO.getRetWillId();
		} else {
			WillViewBD viewBD = new WillViewBD();
			String districtId = viewBD.getDistrictId(officeId);

			newWillId = new CommonUtil().getWillId(districtId, "03");
		}

		willDepDTO.setRetWillId(newWillId);

		// willDepDTO.setWithWillId(newWillId);
		String willUpdate = withdrawalDAO.insertWillWithDrawDetails(status,
				userId, newWillId,
				getOracleDate(willDepDTO.getRetrievalDueDate()), willId, "RI",
				"R", total, funId);
		if (willUpdate != null) {
			if (CommonConstant.WILL_AGENT.equalsIgnoreCase(rType)) {

				String[] param = new String[22];
				param[0] = "AGENT";
				param[1] = willDepDTO.getAgentFirstName();
				param[2] = willDepDTO.getAgentMidName();
				param[3] = willDepDTO.getAgentLastName();
				if (willDepDTO.getAgentGender().equalsIgnoreCase("MALE")||willDepDTO.getAgentGender().equalsIgnoreCase("पुस्र्ष"))
					param[4] = "M";
				else
					param[4] = "F";
				param[5] = willDepDTO.getAgentAge();
				param[6] = willDepDTO.getAgentFatherName();
				param[7] = willDepDTO.getAgentMotherName();
				param[8] = willDepDTO.getAgentSpouseName();
				param[9] = willDepDTO.getAgentAddress();
				// param[10] = willDepDTO.getAgentCountry();
				// param[11] = willDepDTO.getAgentState();
				// param[12] = willDepDTO.getAgentCity();
				String countryID = withdrawalDAO.getCountryID(willDepDTO
						.getAgentCountry());
				param[10] = countryID;
				String stateID = withdrawalDAO.getStateID(willDepDTO
						.getAgentState());
				param[11] = stateID;
				String districtID = withdrawalDAO.getdistrictId(willDepDTO
						.getAgentCity());
				param[12] = districtID;
				param[13] = willDepDTO.getAgentPin();
				param[14] = willDepDTO.getAgentPhone();
				param[15] = willDepDTO.getAgentMPhone();
				param[16] = willDepDTO.getAgentEmail();
				//param[17] = willDepDTO.getAgentIdProof();
				param[17]=dd.getTypeId(willDepDTO.getAgentIdProof(), lang);
				param[18] = willDepDTO.getAgentIdProofNo();
				param[19] = willDepDTO.getAgentBankName();
				param[20] = willDepDTO.getAgentBankAddress();

				param[21] = newWillId;
				// param[22] = willId;
				boolean boo = false;
				boo = withdrawalDAO.insertWillRetirval(param,
						CommonConstant.WILL_AGENT, willDepDTO, newWillId,
						willId, lang);

				if (boo) {
					try {
						if (con == null || con.isClosed()) {
							con = dbUtil.getDBConnection();
						}

						/*
						 * String docpath="E:/Upload/99/03/"+newWillId+"/";
						 * uploadFile(willDepDTO.getDocContents(),
						 * willDepDTO.getDocName(), docpath);
						 * uploadFile(willDepDTO.getSignatureContents(),
						 * willDepDTO.getSignature(), docpath);
						 * uploadFile(willDepDTO.getThumbContents(),
						 * willDepDTO.getThumb(), docpath);
						 * uploadFile(willDepDTO.getDeathCertDocContents(),
						 * willDepDTO.getDeathCerti(), docpath);
						 */

						/*
						 * PreparedStatement prepStmt; int rowCount=0; String
						 * sqlQuery=CommonSQL.UPDT_PRTY_DTLS1; prepStmt =
						 * con.prepareStatement(sqlQuery);
						 * //prepStmt.setBinaryStream(1, new
						 * ByteArrayInputStream(willDepDTO.getDocContents()),
						 * willDepDTO.getDocContents().length);
						 * //prepStmt.setBinaryStream(2, new
						 * ByteArrayInputStream(willDepDTO.getThumbContents()),
						 * willDepDTO.getThumbContents().length);
						 * //prepStmt.setBinaryStream(3, new
						 * ByteArrayInputStream
						 * (willDepDTO.getSignatureContents()),
						 * willDepDTO.getSignatureContents().length);
						 * prepStmt.setString(1, willDepDTO.getPhotoPath());
						 * prepStmt.setString(2, willDepDTO.getSignPath());
						 * prepStmt.setString(3, willDepDTO.getThumbPath());
						 * 
						 * 
						 * prepStmt.setString(4, willDepDTO.getDocumentName());
						 * prepStmt.setString(5, willDepDTO.getThunmbName());
						 * prepStmt.setString(6, willDepDTO.getSignatureName());
						 * //prepStmt.setBinaryStream(7, new
						 * ByteArrayInputStream
						 * (willDepDTO.getAgentProofContents()),
						 * willDepDTO.getAgentProofContents().length);
						 * prepStmt.setString(7,
						 * willDepDTO.getAgentProofPath());
						 * 
						 * prepStmt.setString(8,
						 * willDepDTO.getAgentProofName());
						 * prepStmt.setString(9,newWillId); rowCount =
						 * prepStmt.executeUpdate();
						 * logger.debug("Will Files Deposited : RowCount " +
						 * rowCount); prepStmt.close(); con.commit();
						 */
						String prm[] = new String[9];
						prm[0] = willDepDTO.getPhotoPath();
						prm[1] = willDepDTO.getSignPath();
						prm[2] = willDepDTO.getThumbPath();
						prm[3] = willDepDTO.getDocumentName();
						prm[4] = willDepDTO.getThunmbName();
						prm[5] = willDepDTO.getSignatureName();
						prm[6] = willDepDTO.getAgentProofPath();
						prm[7] = willDepDTO.getAgentProofName();
						prm[8] = newWillId;

						withdrawalDAO
								.insertToDB(CommonSQL.UPDT_PRTY_DTLS1, prm);

					} catch (Exception e) {
						logger.debug("Exception in inserting files in Will Deposit"
								+ e);
					}
					// insert = dao.insertPhotoDetails(willDepDTO, willId);
				}
				willId = param[21];

			} else {// if (CommonConstant.WILL_TESTATOR.equalsIgnoreCase(rType))
					// {

				String[] param = new String[22];
				param[0] = "TESTATOR";
				param[1] = willDepDTO.getFirstName();
				param[2] = willDepDTO.getMidName();
				param[3] = willDepDTO.getLastName();
				if (willDepDTO.getGender().equalsIgnoreCase("MALE")||willDepDTO.getGender().equalsIgnoreCase("पुस्र्ष"))
					param[4] = "M";
				else
					param[4] = "F";
				param[5] = willDepDTO.getAge();
				param[6] = willDepDTO.getFatherName();
				param[7] = willDepDTO.getMotherName();
				param[8] = willDepDTO.getSpouseName();
				param[9] = willDepDTO.getAddress();
				String countryID = withdrawalDAO.getCountryID(willDepDTO
						.getCountry());
				param[10] = countryID;
				String stateID = withdrawalDAO
						.getStateID(willDepDTO.getState());
				param[11] = stateID;
				String districtID = withdrawalDAO.getdistrictId(willDepDTO
						.getCity());
				param[12] = districtID;
				// param[10] = willDepDTO.getCountry();
				// param[11] = willDepDTO.getState();
				// param[12] = willDepDTO.getCity();
				param[13] = willDepDTO.getPin();
				param[14] = willDepDTO.getPhone();
				param[15] = willDepDTO.getMphone();
				param[16] = willDepDTO.getEmail();
				//param[17] = willDepDTO.getIdProof();
				param[17] = dd.getTypeId(willDepDTO.getIdProof(), lang);
				param[18] = willDepDTO.getIdProofNo();
				param[19] = willDepDTO.getBankName();
				param[20] = willDepDTO.getBankAddress();
				param[21] = newWillId;

				boolean isTestatorInsrted = false;
				isTestatorInsrted = withdrawalDAO.insertWillRetirval(param,
						CommonConstant.WILL_TESTATOR, willDepDTO, newWillId,
						willId, lang);

				if (isTestatorInsrted) {
					try {
						if (con == null || con.isClosed()) {
							con = dbUtil.getDBConnection();
						}

						/*
						 * String docpath="E:/Upload/99/03/"+newWillId+"/";
						 * uploadFile(willDepDTO.getRetDocContents(),
						 * willDepDTO.getDocName(), docpath);
						 * uploadFile(willDepDTO.getRetSignatureContents(),
						 * willDepDTO.getSignature(), docpath);
						 * uploadFile(willDepDTO.getRetThumbContents(),
						 * willDepDTO.getThumb(), docpath);
						 */
						/*
						 * PreparedStatement prepStmt; int rowCount = 0; String
						 * sqlQuery =
						 * "Update IGRS_WILL_TXN_PARTY SET PHOTO_PATH=?," +
						 * "SIGNATURE_PATH=?,THUMB_PATH=?,PHOTO_NAME=?,THUMB_NAME=?,SIGNATURE_NAME=? WHERE WILL_TXN_ID=? and PARTY_TYPE_ID='TESTATOR'"
						 * ; prepStmt = con.prepareStatement(sqlQuery); //
						 * prepStmt.setBinaryStream(1, new //
						 * ByteArrayInputStream(willDepDTO.getDocContents()), //
						 * willDepDTO.getDocContents().length); //
						 * prepStmt.setBinaryStream(2, new //
						 * ByteArrayInputStream(willDepDTO.getThumbContents()),
						 * // willDepDTO.getThumbContents().length); //
						 * prepStmt.setBinaryStream(3, new //
						 * ByteArrayInputStream
						 * (willDepDTO.getSignatureContents()), //
						 * willDepDTO.getSignatureContents().length);
						 * 
						 * prepStmt.setString(1, willDepDTO.getPhotoPath());
						 * prepStmt.setString(2, willDepDTO.getSignPath());
						 * prepStmt.setString(3, willDepDTO.getThumbPath());
						 * 
						 * prepStmt.setString(4, willDepDTO.getDocumentName());
						 * prepStmt.setString(5, willDepDTO.getThunmbName());
						 * prepStmt.setString(6, willDepDTO.getSignatureName());
						 * prepStmt.setString(7, newWillId); rowCount =
						 * prepStmt.executeUpdate();
						 * logger.debug("Will Files Deposited : RowCount " +
						 * rowCount); prepStmt.close(); con.commit();
						 */

						String prm[] = new String[7];
						prm[0] = willDepDTO.getPhotoPath();
						prm[1] = willDepDTO.getSignPath();
						prm[2] = willDepDTO.getThumbPath();
						prm[3] = willDepDTO.getDocumentName();
						prm[4] = willDepDTO.getThunmbName();
						prm[5] = willDepDTO.getSignatureName();
						prm[6] = newWillId;
						withdrawalDAO
								.insertToDB(CommonSQL.UPDT_PRTY_DTLS2, prm);

					} catch (Exception x) {
						try {
							dbUtil.rollback();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							logger.debug(e);
						}
						logger.debug("Exception in insertDepositionDetails() :- "
								+ x);
					} finally {
						try {
							dbUtil.closeConnection();
						} catch (Exception ex) {
							logger.debug("Exception in insertWillDeposit() :-"
									+ ex);
						}
					}
					// insert = dao.insertPhotoDetails(willDepDTO, willId);
				}
			}
		}

		return willUpdate;

	}

	public static String getOracleDate(String DateFormat) {
		String finalDate = "";
		if (DateFormat != null || !DateFormat.equalsIgnoreCase("")) {
			StringTokenizer st = new StringTokenizer(DateFormat, "/");
			String day = st.nextToken();
			String month = st.nextToken();
			String year = st.nextToken();
			String inputDate = day + "-" + month + "-" + year;
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

			Date newDate;
			try {
				newDate = formatter.parse(inputDate);
				SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
				finalDate = format.format(newDate);

			} catch (Exception e) {
				System.out.print(e);
			}

		}
		return finalDate;
	}

	

	public boolean updateCitizenDetails(WillDepositDTO willDepDTO,
			String newWillId, String lang) throws Exception {

		// WillRetrievalDAO dao = new WillRetrievalDAO();

		boolean insert = false;

		String[] param = new String[22];
		param[0] = "WithAgent";
		param[1] = willDepDTO.getRetrieverFirstName();
		param[2] = willDepDTO.getRetrieverMidName();
		param[3] = willDepDTO.getRetrieverLastName();
		if (willDepDTO.getRetrieverGender().equalsIgnoreCase("MALE")||willDepDTO.getRetrieverGender().equalsIgnoreCase("पुस्र्ष")||willDepDTO.getRetrieverGender().equalsIgnoreCase("M"))
			{
			param[4] = "M";
			if("en".equalsIgnoreCase(lang)){
			willDepDTO.setShowGender("Male");
			}
			else{
				willDepDTO.setShowGender("पुस्र्ष");
			}
			}
		else{
			param[4] = "F";
			if("en".equalsIgnoreCase(lang)){
				willDepDTO.setShowGender("Female");
				}
				else{
					willDepDTO.setShowGender("स्त्री");
				}
		}
		param[5] = willDepDTO.getRetrieverAge();
		param[6] = willDepDTO.getRetrieverFatherName();
		param[7] = willDepDTO.getRetrieverMotherName();
		param[8] = willDepDTO.getRetrieverSpouseName();
		param[9] = willDepDTO.getRetrieverAddress();
		String countryID = willDepDTO.getRetrieverCountry();
		param[10] = countryID;
		String stateID = willDepDTO.getRetrieverState();
		param[11] = stateID;
		String districtID = willDepDTO.getRetrieverCity();
		param[12] = districtID;
		// param[10] = willDepDTO.getCountry();
		// param[11] = willDepDTO.getState();
		// param[12] = willDepDTO.getCity();
		param[13] = willDepDTO.getRetrieverPin();
		param[14] = willDepDTO.getRetrieverPhone();
		param[15] = willDepDTO.getRetrieverMPhone();
		param[16] = willDepDTO.getRetrieverEMail();
		param[17] = willDepDTO.getRetrieverIdProof();
		param[18] = willDepDTO.getRetrieverIdProofNo();
		param[19] = willDepDTO.getRetrieverBankName();
		param[20] = willDepDTO.getRetrieverBankAddress();
		param[21] = newWillId;
		

		dbUtil = new DBUtility();
		boolean iswithDrawn = false;
		dbUtil.createPreparedStatement(CommonSQL.WILL_DEPOSITE_AGENT_INSERT);
		iswithDrawn = dbUtil.executeUpdate(param);

		if (iswithDrawn) {
			try {
				if (con == null || con.isClosed()) {
					con = dbUtil.getDBConnection();
				}

				// commented after integration with the devices
				/*
				 * String docpath="D:/Upload/14/02/"+newWillId+"/";
				 * uploadFile(willDepDTO.getRetDocContents(),
				 * willDepDTO.getDocName(), docpath);
				 * uploadFile(willDepDTO.getRetSignatureContents(),
				 * willDepDTO.getSignature(), docpath);
				 * uploadFile(willDepDTO.getRetThumbContents(),
				 * willDepDTO.getThumb(), docpath);
				 * uploadFile(willDepDTO.getDeathCertDocContents(),
				 * willDepDTO.getDeathCerti(), docpath);
				 */
				/*
				 * PreparedStatement prepStmt; int rowCount = 0; String sqlQuery
				 * = "Update IGRS_WILL_TXN_PARTY SET PHOTO_PATH=?," +
				 * "THUMB_PATH=?,SIGNATURE_PATH=?,DEATH_CERTI_PATH=?," +
				 * "PHOTO_NAME=?,THUMB_NAME=?,SIGNATURE_NAME=?," +
				 * "DEATH_CERTIFICATE_NAME=? WHERE WILL_TXN_ID=? and " +
				 * "PARTY_TYPE_ID=?"; prepStmt = con.prepareStatement(sqlQuery);
				 * // prepStmt.setBinaryStream(1, new //
				 * ByteArrayInputStream(willDepDTO.getRetDocContents()), //
				 * willDepDTO.getRetDocContents().length); //
				 * prepStmt.setBinaryStream(2, new //
				 * ByteArrayInputStream(willDepDTO.getRetThumbContents()), //
				 * willDepDTO.getRetThumbContents().length); //
				 * prepStmt.setBinaryStream(3, new //
				 * ByteArrayInputStream(willDepDTO.getRetSignatureContents()),
				 * // willDepDTO.getRetSignatureContents().length); //
				 * prepStmt.setBinaryStream(4, new //
				 * ByteArrayInputStream(willDepDTO.getDeathCertDocContents()),
				 * // willDepDTO.getDeathCertDocContents().length);
				 * prepStmt.setString(1, willDepDTO.getSignPath() +
				 * willDepDTO.getUniqueId() + "/" + willDepDTO.getDocName());
				 * prepStmt.setString(2, willDepDTO.getSignPath() +
				 * willDepDTO.getUniqueId() + "/" + willDepDTO.getThumb());
				 * prepStmt.setString(3, willDepDTO.getSignPath() +
				 * willDepDTO.getUniqueId() + "/" + willDepDTO.getSignature());
				 * prepStmt.setString(4, willDepDTO.getSignPath() +
				 * willDepDTO.getUniqueId() + "/" +
				 * willDepDTO.getAgentProofName()); prepStmt.setString(5,
				 * willDepDTO.getDocName()); prepStmt.setString(6,
				 * willDepDTO.getThumb()); prepStmt.setString(7,
				 * willDepDTO.getSignature()); prepStmt.setString(8,
				 * willDepDTO.getAgentProofName()); prepStmt.setString(9,
				 * newWillId); prepStmt.setString(10, "Court"); rowCount =
				 * prepStmt.executeUpdate();
				 * logger.debug("Will Files Deposited : RowCount " + rowCount);
				 * prepStmt.close(); con.commit();
				 */
				String prm[] = new String[10];
				prm[0] = willDepDTO.getDsignPath() + willDepDTO.getUniqueId()
						+ "/" + willDepDTO.getDocName();
				prm[1] = willDepDTO.getDsignPath() + willDepDTO.getUniqueId()
						+ "/" + willDepDTO.getThumb();
				prm[2] = willDepDTO.getDsignPath() + willDepDTO.getUniqueId()
						+ "/" + willDepDTO.getSignature();
				prm[3] = willDepDTO.getDsignPath() + willDepDTO.getUniqueId()
						+ "/" + willDepDTO.getCertificateName();
				prm[4] = willDepDTO.getDocName();
				prm[5] = willDepDTO.getThumb();
				prm[6] = willDepDTO.getSignature();
				prm[7] = willDepDTO.getCertificateName();
				prm[8] = newWillId;
				prm[9] = "WithAgent";

				withdrawalDAO.insertToDB(CommonSQL.UPDT_PRTY_DTLS3, prm);

			} catch (Exception e) {
				logger.debug("Exception in inserting files in Will Retrieval"
						+ e);
			}finally{
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// insert = dao.insertPhotoDetails(willDepDTO, willId);
		}

		return true;
	}

	// ANUJ
	public ArrayList pendingWithPayment(String officeId, float total) throws Exception {
		int valueOfTotal = (int)total;
		ArrayList FinalList = new ArrayList();
		ArrayList list = new ArrayList();
		withdrawalDAO = new WillWithdrawalDAO();

		WillViewBD bd = new WillViewBD();

		String districtId = bd.getDistrictId(officeId);

		list = withdrawalDAO.getPendingWillWithdraw(districtId);
		// business logic
		ArrayList rowList;
		WillWithdrawDashBoard objDashboard = new WillWithdrawDashBoard();
		try {
			for (int i = 0; i < list.size(); i++) {

				rowList = (ArrayList) list.get(i);
				objDashboard.setSrNo(i + 1);
				objDashboard.setWill_txn_id((String) rowList.get(0));
				objDashboard.setNo_of_parties((String) rowList.get(1));
				if ((String) rowList.get(2) == ""||(String)rowList.get(2)==null) {
					objDashboard.setPaid_amount("0");
				} else {
					objDashboard.setPaid_amount((String) rowList.get(2));
				}

				if ((String) rowList.get(3) == ""||(String)rowList.get(3)==null) {
					objDashboard.setDue_amount(String.valueOf(valueOfTotal));
				} else {
					objDashboard.setDue_amount((String) rowList.get(3));
				}
				if ((String) rowList.get(4) == ""||(String) rowList.get(4) == null) {
				objDashboard.setCreated_date("-");
				}
				else{
					objDashboard.setCreated_date((String) rowList.get(4));
				}
				if ((String) rowList.get(5) == ""||(String) rowList.get(5) == null) {
					objDashboard.setUpdated_date(" ");
				} else {
					objDashboard.setUpdated_date((String) rowList.get(5));
				}

				objDashboard.setPayable_amount((String) rowList.get(6));
				if ((String) rowList.get(3) == ""||(String)rowList.get(3)==null) {
					objDashboard.setWillStatus((String) rowList.get(0) + "~"
							+ String.valueOf(valueOfTotal));
				} else {
					objDashboard.setWillStatus((String) rowList.get(0) + "~"
							+ (String) rowList.get(3));
				}
				FinalList.add(objDashboard);
				objDashboard = new WillWithdrawDashBoard();

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		// end of bl

		return FinalList;
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

	public ArrayList getDetails(String officeId) {
		WillWithdrawalDAO willDAO;
		ArrayList alist = new ArrayList();
		try {
			willDAO = new WillWithdrawalDAO();

			alist = willDAO.getDetails(officeId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alist;
	}

	public boolean updateCitizenDetailsCitizen(WillDepositDTO willDepDTO,
			String newWillId, String lang) throws Exception {
		boolean insert = false;
WillCommonDAO dd = new WillCommonDAO();
		String[] param = new String[22];
		param[0] = "WithTest";
		param[1] = willDepDTO.getFirstName();
		param[2] = willDepDTO.getMidName();
		param[3] = willDepDTO.getLastName();
		if (willDepDTO.getGender().equalsIgnoreCase("MALE")||willDepDTO.getGender().equalsIgnoreCase("पुस्र्ष")||willDepDTO.getGender().equalsIgnoreCase("M")){
			param[4] = "M";
			if("en".equalsIgnoreCase(lang)){
			willDepDTO.setShowGender("Male");
			}else{
				willDepDTO.setShowGender("पुस्र्ष");
			}
		}
		else{
			param[4] = "F";
			if("en".equalsIgnoreCase(lang)){
				willDepDTO.setShowGender("Female");
				}else{
					willDepDTO.setShowGender("स्त्री");
				}
		}
		param[5] = willDepDTO.getAge();
		param[6] = willDepDTO.getFatherName();
		param[7] = willDepDTO.getMotherName();
		param[8] = willDepDTO.getSpouseName();
		param[9] = willDepDTO.getAddress();
		String countryID = withdrawalDAO.getCountryID(willDepDTO
				.getCountry());
		param[10] = countryID;
		String stateID = withdrawalDAO
				.getStateID(willDepDTO.getState());
		param[11] = stateID;
		String districtID = withdrawalDAO.getdistrictId(willDepDTO
				.getCity());
		param[12] = districtID;
		// param[10] = willDepDTO.getCountry();
		// param[11] = willDepDTO.getState();
		// param[12] = willDepDTO.getCity();
		param[13] = willDepDTO.getPin();
		param[14] = willDepDTO.getPhone();
		param[15] = willDepDTO.getMphone();
		param[16] = willDepDTO.getEmail();
		param[17] = dd.getTypeId(willDepDTO.getIdProof(), lang);
		param[18] = willDepDTO.getIdProofNo();
		param[19] = willDepDTO.getBankName();
		param[20] = willDepDTO.getBankAddress();
		param[21] = newWillId;
		

		try {
			dbUtil = new DBUtility();
		
		boolean iswithDrawn = false;
		dbUtil.createPreparedStatement(CommonSQL.WILL_DEPOSITE_AGENT_INSERT);
		iswithDrawn = dbUtil.executeUpdate(param);
		
		
		
		
		String prm[] = new String[8];
		prm[0] = willDepDTO.getDsignPath() + willDepDTO.getUniqueId()
				+ "/" + willDepDTO.getDocName();
		prm[1] = willDepDTO.getDsignPath() + willDepDTO.getUniqueId()
				+ "/" + willDepDTO.getThumb();
		prm[2] = willDepDTO.getDsignPath() + willDepDTO.getUniqueId()
				+ "/" + willDepDTO.getSignature();

		prm[3] = willDepDTO.getDocName();
		prm[4] = willDepDTO.getThumb();
		prm[5] = willDepDTO.getSignature();
		
		prm[6] = newWillId;
		prm[7] = "WithTest";

		withdrawalDAO.insertToDB(CommonSQL.UPDT_PRTY_DTLS6, prm);
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	// ANUJ

}
