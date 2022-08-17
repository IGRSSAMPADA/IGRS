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
 */

/* 
 * FILE NAME: WillRetrievalBD.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 28th DEC 2007 
 * MODIFIED ON:    12th APRIL 2008 
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE ACTION FOR GUIDELINE DELIEVRY UPDATION 
 */

package com.wipro.igrs.willdeposit.bd;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.db.DataSourceLookUp;
import com.wipro.igrs.util.CommonUtil;
import com.wipro.igrs.util.PropertiesFileReader;
import com.wipro.igrs.willdeposit.constant.CommonConstant;
import com.wipro.igrs.willdeposit.dao.WillCommonDAO;
import com.wipro.igrs.willdeposit.dao.WillRetrievalDAO;
import com.wipro.igrs.willdeposit.dao.WillViewDAO;
import com.wipro.igrs.willdeposit.dao.WillWithdrawalDAO;
import com.wipro.igrs.willdeposit.dto.WillDepositDTO;
import com.wipro.igrs.willdeposit.dto.WillWithdrawDashBoard;

/**
 * @author NIHAR M.
 * 
 */
public class WillRetrievalBD {

	private Logger logger = (Logger) Logger.getLogger(WillRetrievalBD.class);
	Connection con = null;
	DBUtility dbUtil;

	/**
	 * @param willId
	 * @param fromDate
	 * @param toDate
	 * @param status
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getWillViewDetails(String willId, String fromDate,
			String toDate) throws Exception {
		WillRetrievalDAO retDAO = new WillRetrievalDAO();
		ArrayList list = retDAO.getWillViewDetails(willId, fromDate, toDate);

		ArrayList returnList = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList lst = (ArrayList) list.get(i);
				WillDepositDTO dto = new WillDepositDTO();
				dto.setWillId((String) lst.get(0));
				dto.setFirstName((String) lst.get(1));
				dto.setMidName((String) lst.get(2));
				dto.setLastName((String) lst.get(3));
				dto.setAddress((String) lst.get(4));
				dto.setDespositDate((String) lst.get(5));
				dto.setWillStatus((String) lst.get(6));

				returnList.add(dto);
			}
		}
		return returnList;
	}

	/**
	 * @param willId
	 * @param fromDate
	 * @param toDate
	 * @param status
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getWillRetrievalDetails(String willId, String fromDate,
			String toDate) throws Exception {
		// WillRetrievalDAO retDAO = new WillRetrievalDAO();
		WillViewDAO viewDAO = new WillViewDAO();
		/*
		 * ArrayList list = retDAO.getWillRetrievalDetails(willId, fromDate,
		 * toDate);
		 */
		String userId = "";
		ArrayList list = viewDAO.getWillViewDetails(willId, fromDate, toDate,
				"Deposited", userId);

		ArrayList returnList = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList lst = (ArrayList) list.get(i);
				WillDepositDTO dto = new WillDepositDTO();
				dto.setWillId((String) lst.get(0));
				dto.setFirstName((String) lst.get(1));
				dto.setMidName((String) lst.get(2));
				dto.setLastName((String) lst.get(3));
				dto.setAddress((String) lst.get(4));
				dto.setDespositDate((String) lst.get(5));
				dto.setWillStatus((String) lst.get(6));

				returnList.add(dto);
			}
		}
		return returnList;
	}

	/**
	 * @param willid
	 * @return WillDepositDTO
	 * @throws Exception
	 */
	public WillDepositDTO getwillIdDetails(String willid, String StrFunId)
			throws Exception {
		WillDepositDTO dto = new WillDepositDTO();
		WillRetrievalDAO retDAO = new WillRetrievalDAO();
		WillCommonDAO commonDAO = new WillCommonDAO();
		ArrayList ret = retDAO.getwillIdDetails(willid);

		ArrayList list = commonDAO.getWillFeeValue(StrFunId);
		ArrayList retList1 = null;
		String feeVal = "0.0";
		if (retList1 != null && retList1.size() > 0) {
			retList1 = (ArrayList) list.get(0);
			feeVal = ((String) retList1.get(1));
		}
		dto.setFee(feeVal);

		return dto;
	}

	/**
	 * @param willDepDTO
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateCitizenDetails(WillDepositDTO willDepDTO,
			String newWillId, String lang) throws Exception {

		String rType = willDepDTO.getRetrieverType();
		WillRetrievalDAO dao = new WillRetrievalDAO();
		// String[] depositParam = new String[4];
		String[] retstatusUpdt = new String[3];

		if (CommonConstant.WILL_RETRIEVE_CITIZEN.equalsIgnoreCase(rType)) {
			Boolean insert = false;

			String[] param = new String[23];
			param[0] = willDepDTO.getRetrieverType();
			param[1] = willDepDTO.getRetrieverFirstName();
			param[2] = willDepDTO.getRetrieverMidName();
			param[3] = willDepDTO.getRetrieverLastName();
			param[4] = willDepDTO.getRetrieverGender();
			if ("en".equalsIgnoreCase(lang)) {
				if ("M".equalsIgnoreCase(willDepDTO.getRetrieverGender())) {
					willDepDTO.setShowGender("Male");
				} else {
					willDepDTO.setShowGender("Female");
				}
			} else {
				if ("M".equalsIgnoreCase(willDepDTO.getRetrieverGender())) {
					willDepDTO.setShowGender("पुस्र्ष");
				} else {
					willDepDTO.setShowGender("स्त्री");
				}
			}
			param[5] = willDepDTO.getRetrieverAge();
			param[6] = willDepDTO.getRetrieverFatherName();
			param[7] = willDepDTO.getRetrieverMotherName();
			param[8] = willDepDTO.getRetrieverSpouseName();
			param[9] = willDepDTO.getRetrieverAddress();
			param[10] = willDepDTO.getRetrieverCountry();
			param[11] = willDepDTO.getRetrieverState();
			param[12] = willDepDTO.getRetrieverCity();
			param[13] = willDepDTO.getRetrieverPin();
			param[14] = willDepDTO.getRetrieverPhone();
			param[15] = willDepDTO.getRetrieverMPhone();
			param[16] = willDepDTO.getRetrieverEMail();
			param[17] = willDepDTO.getRetrieverIdProof();
			WillCommonBD dd = new WillCommonBD();
			willDepDTO.setRetrieverIdProofName(dd.getIdType(willDepDTO.getRetrieverIdProof(), lang));
			param[18] = willDepDTO.getRetrieverIdProofNo();
			// param[19] = willDepDTO.getRetrievalDueDate();
			// param[19] = willDepDTO.getWillRetrievalReason();
			// aram[19] = CommonConstant.WILL_RETRIEVE_FLAG;
			param[19] = newWillId;
			param[20] = willDepDTO.getRetrieverBankName();
			param[21] = willDepDTO.getRetrieverAddress();
			param[22] = willDepDTO.getTestatorRelationship();

			// int i = 0;
			// logger.debug("param[i]:-           " + param[i]);
			retstatusUpdt[0] = getDateFromString(willDepDTO
					.getRetrievalDueDate());
			retstatusUpdt[1] = willDepDTO.getTestatorRelationship();
			retstatusUpdt[2] = willDepDTO.getWillId();

			insert = dao.updateCitizenDetails(param,
					CommonConstant.WILL_RETRIEVE_CITIZEN, retstatusUpdt);

			if (insert) {
				try {
					if (con == null || con.isClosed()) {
						con = dbUtil.getDBConnection();
					}

					// commented after integration with devices
					/*
					 * String docpath="D:/Upload/14/03/"+newWillId+"/";
					 * uploadFile(willDepDTO.getRetDocContents(),
					 * willDepDTO.getDocName(), docpath);
					 * uploadFile(willDepDTO.getRetSignatureContents(),
					 * willDepDTO.getSignature(), docpath);
					 * uploadFile(willDepDTO.getRetThumbContents(),
					 * willDepDTO.getThumb(), docpath);
					 * uploadFile(willDepDTO.getDeathCertDocContents(),
					 * willDepDTO.getDeathCerti(), docpath);
					 */

					PreparedStatement prepStmt;
					int rowCount = 0;
					String sqlQuery = "Update IGRS_WILL_TXN_PARTY SET PHOTO_PATH=?,"
							+ "THUMB_PATH=?, SIGNATURE_PATH=?, DEATH_CERTI_PATH=?,"
							+ "PHOTO_NAME=?,THUMB_NAME=?,SIGNATURE_NAME=?,"
							+ "DEATH_CERTIFICATE_NAME=?, WILL_COPY_NAME=?, WILL_COPY_PATH=? WHERE WILL_TXN_ID=? and "
							+ "PARTY_TYPE_ID=?";
					prepStmt = con.prepareStatement(sqlQuery);
					// prepStmt.setBinaryStream(1, new
					// ByteArrayInputStream(willDepDTO.getRetDocContents()),
					// willDepDTO.getRetDocContents().length);
					// prepStmt.setBinaryStream(2, new
					// ByteArrayInputStream(willDepDTO.getRetThumbContents()),
					// willDepDTO.getRetThumbContents().length);
					// prepStmt.setBinaryStream(3, new
					// ByteArrayInputStream(willDepDTO.getRetSignatureContents()),
					// willDepDTO.getRetSignatureContents().length);
					// prepStmt.setBinaryStream(4, new
					// ByteArrayInputStream(willDepDTO.getDeathCertDocContents()),
					// willDepDTO.getDeathCertDocContents().length);
					prepStmt.setString(1, willDepDTO.getDsignPath()
							+ willDepDTO.getUniqueId() + "/"
							+ willDepDTO.getDocName());
					prepStmt.setString(2, willDepDTO.getDsignPath()
							+ willDepDTO.getUniqueId() + "/"
							+ willDepDTO.getThumb());
					prepStmt.setString(3, willDepDTO.getDsignPath()
							+ willDepDTO.getUniqueId() + "/"
							+ willDepDTO.getSignature());
					prepStmt.setString(4, willDepDTO.getDsignPath()
							+ willDepDTO.getUniqueId() + "/"
							+ willDepDTO.getCertificateName());
					System.out.println("in retrvl bd"
							+ willDepDTO.getDsignPath());
					prepStmt.setString(5, willDepDTO.getDocName());
					prepStmt.setString(6, willDepDTO.getThumb());
					prepStmt.setString(7, willDepDTO.getSignature());
					prepStmt.setString(8, willDepDTO.getCertificateName());
					prepStmt.setString(9, willDepDTO.getScanName());
					prepStmt.setString(10, willDepDTO.getDsignPath()
							+ willDepDTO.getUniqueId() + "/"
							+ willDepDTO.getScanName());
					prepStmt.setString(11, newWillId);
					prepStmt.setString(12, willDepDTO.getRetrieverType());

					rowCount = prepStmt.executeUpdate();
					logger.debug("Will Files Deposited : RowCount " + rowCount);
					prepStmt.close();
					con.commit();

				} catch (Exception e) {
					logger
							.debug("Exception in inserting files in Will Retrieval"
									+ e);
				}
				// insert = dao.insertPhotoDetails(willDepDTO, willId);
			}

		} else if (CommonConstant.WILL_RETRIEVE_COURT.equalsIgnoreCase(rType)) {

			boolean insert = false;

			String[] param = new String[13];
			param[0] = willDepDTO.getRetrieverType();
			param[1] = willDepDTO.getCourtName();
			param[2] = willDepDTO.getCourtAddress();
			param[3] = willDepDTO.getCourtCountry();
			param[4] = willDepDTO.getCourtState();
			param[5] = willDepDTO.getCourtCity();
			param[6] = willDepDTO.getCourtPin();
			param[7] = willDepDTO.getCourtPhone();
			param[8] = willDepDTO.getRepFirstName();
			param[9] = willDepDTO.getRepMiddleName();
			param[10] = willDepDTO.getRepLastName();
			param[11] = willDepDTO.getRepDesg();
			param[12] = newWillId;

			retstatusUpdt[0] = getDateFromString(willDepDTO
					.getRetrievalDueDate());
			retstatusUpdt[1] = willDepDTO.getTestatorRelationship();
			retstatusUpdt[2] = willDepDTO.getWillId();

			insert = dao.updateCitizenDetails(param,
					CommonConstant.WILL_RETRIEVE_COURT_FLAG, retstatusUpdt);

			if (insert) {
				try {
					if (con == null || con.isClosed()) {
						con = dbUtil.getDBConnection();
					}

					// commented after integration with devices
					/*
					 * String docpath="D:/Upload/14/03/"+newWillId+"/";
					 * uploadFile(willDepDTO.getRetDocContents(),
					 * willDepDTO.getDocName(), docpath);
					 * uploadFile(willDepDTO.getRetSignatureContents(),
					 * willDepDTO.getSignature(), docpath);
					 * uploadFile(willDepDTO.getRetThumbContents(),
					 * willDepDTO.getThumb(), docpath);
					 * uploadFile(willDepDTO.getDeathCertDocContents(),
					 * willDepDTO.getDeathCerti(), docpath);
					 */

					PreparedStatement prepStmt;
					int rowCount = 0;
					String sqlQuery = "Update IGRS_WILL_TXN_PARTY SET PHOTO_PATH=?,"
							+ "THUMB_PATH=?,SIGNATURE_PATH=?,DEATH_CERTI_PATH=?,"
							+ "PHOTO_NAME=?,THUMB_NAME=?,SIGNATURE_NAME=?,"
							+ "DEATH_CERTIFICATE_NAME=?, WILL_COPY_NAME=?, WILL_COPY_PATH=? WHERE WILL_TXN_ID=? and "
							+ "PARTY_TYPE_ID=?";
					prepStmt = con.prepareStatement(sqlQuery);
					/*
					 * prepStmt.setBinaryStream(1, new
					 * ByteArrayInputStream(willDepDTO.getRetDocContents()),
					 * willDepDTO.getRetDocContents().length);
					 * prepStmt.setBinaryStream(2, new
					 * ByteArrayInputStream(willDepDTO.getRetThumbContents()),
					 * willDepDTO.getRetThumbContents().length);
					 * prepStmt.setBinaryStream(3, new
					 * ByteArrayInputStream(willDepDTO
					 * .getRetSignatureContents()),
					 * willDepDTO.getRetSignatureContents().length);
					 * prepStmt.setBinaryStream(4, new
					 * ByteArrayInputStream(willDepDTO
					 * .getDeathCertDocContents()),
					 * willDepDTO.getDeathCertDocContents().length);
					 */

					prepStmt.setString(1, willDepDTO.getDsignPath()
							+ willDepDTO.getUniqueId() + "/"
							+ willDepDTO.getDocName());
					prepStmt.setString(2, willDepDTO.getDsignPath()
							+ willDepDTO.getUniqueId() + "/"
							+ willDepDTO.getThumb());
					prepStmt.setString(3, willDepDTO.getDsignPath()
							+ willDepDTO.getUniqueId() + "/"
							+ willDepDTO.getSignature());
					prepStmt.setString(4, willDepDTO.getDsignPath()
							+ willDepDTO.getUniqueId() + "/"
							+ willDepDTO.getCertificateName());
					System.out.println("in retrvl bd"
							+ willDepDTO.getDsignPath());
					prepStmt.setString(5, willDepDTO.getDocName());
					prepStmt.setString(6, willDepDTO.getThumb());
					prepStmt.setString(7, willDepDTO.getSignature());
					prepStmt.setString(8, willDepDTO.getCertificateName());

					/*
					 * prepStmt.setString(5, willDepDTO.getRetDocumentName());
					 * prepStmt.setString(6, willDepDTO.getRetThunmbName());
					 * prepStmt.setString(7, willDepDTO.getRetSignatureName());
					 * prepStmt.setString(8, willDepDTO.getCertificateName());
					 */
					prepStmt.setString(11, newWillId);
					prepStmt.setString(12, willDepDTO.getRetrieverType());
					prepStmt.setString(9, willDepDTO.getScanName());
					prepStmt.setString(10, willDepDTO.getDsignPath()
							+ willDepDTO.getUniqueId() + "/"
							+ willDepDTO.getScanName());
					rowCount = prepStmt.executeUpdate();
					logger.debug("Will Files Deposited : RowCount " + rowCount);
					prepStmt.close();
					con.commit();

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
						logger
								.debug("Exception in insertWillDeposit() :-"
										+ ex);
					}
				}
				// insert = dao.insertPhotoDetails(willDepDTO, willId);
			}
		}
		return true;
	}

	/**
	 * @return String
	 */
	public String willIDgenerator() {
		String id = new CommonUtil().getAutoId("IGRS_WILL_TXN", "WILL_TXN_ID",
				"MPW");
		return id;
	}

	
	private String getDateFromString(String dateString) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		try {
			date = simpleDateFormat.parse(dateString);
			logger.debug("date=" + date);
		} catch (ParseException pe) {
			logger.debug("ParseException: " + pe);
		}
		return getStringFromDate(date);
	}

	private String getStringFromDate(Date date) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String dateString = null;
		try {
			if (date != null)
				dateString = dateFormat.format(date);
		} catch (Exception e) {
			logger
					.debug("Error getting date1 field of Newattendanceoutput1 in 'dd-MMM-yy' format: "
							+ e);
		}
		return dateString;
	}

	// ANUJ
	public ArrayList pendingWithPayment(String officeId, float total)
			throws Exception {
		int valueOfTotal = (int)total;
		ArrayList FinalList = new ArrayList();
		ArrayList list = new ArrayList();
		WillRetrievalDAO withdrawalDAO = new WillRetrievalDAO();
		WillViewBD viewBD = new WillViewBD();
		String districtId = viewBD.getDistrictId(officeId);
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
				objDashboard = new WillWithdrawDashBoard();

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		// end of bl

		return FinalList;
	}

	public String getWillId(String willId) {
		String status = "";
		ArrayList statusList = new ArrayList();
		String[] param = { willId };

		try {
			dbUtil = new DBUtility();

			String sql = "SELECT WILL_TXN_ID FROM igrs_will_txn_payment_dtls WHERE will_payment_id=? ";
			dbUtil.createPreparedStatement(sql);

			statusList = dbUtil.executeQuery(new String[] { willId });
			ArrayList rowList = (ArrayList) statusList.get(0);
			willId = (String) rowList.get(0);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return willId;

	}

	public String insertWillDetails(String willId, String funId, String userId,
			float total) {
		String k = "";
		try {
			dbUtil = new DBUtility();

			dbUtil.createStatement();

			ArrayList alist = dbUtil
					.executeQuery("SELECT IGRS_WILL_PAYMENT_SEQ.NEXTVAL FROM DUAL");

			ArrayList s = (ArrayList) alist.get(0);
			k = (String) s.get(0);
			// for next query
			dbUtil
					.createPreparedStatement("INSERT INTO IGRS_WILL_TXN_PAYMENT_DTLS (WILL_PAYMENT_ID, WILL_TXN_ID,  FUNCTION_ID,  PAYABLE_AMOUNT, PAID_AMOUNT, PAYMENT_FLAG, CREATED_BY, CREATED_DATE) VALUES (?, ?, ?, ?, ?, ?, ?, SYSDATE)");
			Float f = total;
			String s1 = f.toString();
			String param1[] = new String[7];
			param1[0] = k;
			param1[1] = willId;
			param1[2] = funId;
			param1[3] = s1;
			param1[4] = "";
			param1[5] = "I";
			param1[6] = userId;
			// param[6] is SYSDATE
			dbUtil.executeUpdate(param1);
			dbUtil.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in insertWillDeposit() :-" + ex);
			}
		}

		return k;
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

	// ANUJ

}
