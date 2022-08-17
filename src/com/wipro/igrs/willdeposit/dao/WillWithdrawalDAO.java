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
 * FILE NAME: WillWithdrawalDAO.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 12th December 2007 
 * MODIFIED ON:	   12th April 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE BUSINESS DELEGATE CLASS
 */

package com.wipro.igrs.willdeposit.dao;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import oracle.jdbc.OracleResultSet;
import oracle.sql.BLOB;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.db.DataSourceLookUp;
import com.wipro.igrs.util.PropertiesFileReader;
import com.wipro.igrs.willdeposit.bd.WillViewBD;
import com.wipro.igrs.willdeposit.constant.CommonConstant;
import com.wipro.igrs.willdeposit.dto.WillDepositDTO;
import com.wipro.igrs.willdeposit.sql.CommonSQL;

/**
 * @author NIHAR M.
 * 
 */
public class WillWithdrawalDAO {

	//DBUtility dbUtil;
	private Logger logger = (Logger) Logger.getLogger(WillWithdrawalDAO.class);
	//Connection con = null;

	/**
	 * @throws Exception
	 */
	public WillWithdrawalDAO() throws Exception {
		//dbUtil = new DBUtility();
	}

	/**
	 * @param willId
	 * @param fromDate
	 * @param toDate
	 * @return ArrayList
	 */
	public ArrayList getWillWIthdrawalDetails(String willId, String fromDate,
			String toDate) {

		String status = "";
		String param[]=new String[14];
		param[0]=willId;
		param[1]=willId;
		param[2]=willId;
		param[3]=willId;
		param[4]=status;
		param[5]=status;
		param[6]=status;
		param[7]=fromDate;
		param[8]=fromDate;
		param[9]=fromDate;
		param[10]=toDate;
		param[11]=toDate;
		param[12]=toDate;
		param[13]=fromDate;
		param[14]=toDate;
		
		String sql = CommonSQL.WILL_WITH_DTLS;
		ArrayList list = null;DBUtility dbUtil=null;
		try {dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			
			logger.debug("WILLDAO:: Before getting withdrawal Details " + sql);
			dbUtil.createStatement();
			list = dbUtil.executeQuery(param);
		} catch (Exception x) {
			logger
					.debug("WillWithdrawalDAO::   Exception in getWillWIthdrawalDetails() :- "
							+ x);
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
	 * @param willid
	 * @return ArrayList
	 */
	public ArrayList getWillDeposit(String willParamId) {
		
		String param[]=new String[1];
		param[0]=willParamId;
		
		
		String sql = CommonSQL.WILL_WITHDRAWAL_ID_DETAILS_QUERY;
		

		if (willParamId != null) {
			param=new String[2];
			param[0]=willParamId;
			param[1]=willParamId;
			
			sql += " "
					+ "UNION ALL"
					+ " "
					+ "SELECT  NULL col1, NULL col2, NULL col3, NULL col4, NULL col5, NULL col6,NULL col7, NULL col8, NULL col9, NULL col10, NULL col11,"
					+ "igrs_will_txn_party.will_txn_id col12, "
					+ "igrs_will_txn_party.party_type_id col13,igrs_will_txn_party.first_name col14, igrs_will_txn_party.middle_name col15, "
					+ "igrs_will_txn_party.last_name col16, igrs_will_txn.will_status col17, igrs_will_txn_party.gender col8, igrs_will_txn_party.age col19, "
					+ "igrs_will_txn_party.guardian_name col20, igrs_will_txn_party.mother_name col21, igrs_will_txn.delivery_status col22"
					+ " "
					+ "FROM igrs_will_txn_party, igrs_will_txn where igrs_will_txn_party.PARTY_TYPE_ID = 'T' and igrs_will_txn_party.will_txn_id =  '"
					+ " ?)";

			logger.debug("after calc::   " + sql);
		}

		ArrayList list = null;DBUtility dbUtil=null;
		try {dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);

			list = dbUtil.executeQuery(param);
		} catch (Exception x) {
			logger.debug("Exception in getWillDeposit() :- " + x);
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

	public boolean updateWillWithDrawDStatus(WillDepositDTO formDTO,
			String userId) {
		boolean willDrawal = false;DBUtility dbUtil=null;
		try {dbUtil = new DBUtility();
			dbUtil
					.createPreparedStatement(CommonSQL.WILL_WITHDRAWL_STATUS_UPDATE);

			String[] param = new String[4];
			param[0] = "Withdrawal";
			param[1] = userId;
			param[2] = formDTO.getWithdrawlDueDate();
			param[3] = formDTO.getWillId();

			willDrawal = dbUtil.executeUpdate(param);
			if (willDrawal)
				dbUtil.commit();

		} catch (Exception x) {
			logger.debug("Exception in updateWillWithDrawDStatus() :- " + x);
		} finally {
			try {
				if (!willDrawal) {
					dbUtil.rollback();
				}
				// dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in insertWillWithDrawal() :-" + ex);
			}finally{
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return willDrawal;
	}

	public void updateWillWithDrawDetails(WillDepositDTO formDTO, String userId) {
		boolean willDrawal = false;DBUtility dbUtil=null;
		try {dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.WILL_WITHDRAWL_INSERT);

			String[] param = new String[3];
			param[0] = formDTO.getWillId();
			param[1] = userId;
			param[2] = formDTO.getRemarks();

			willDrawal = dbUtil.executeUpdate(param);
			if (willDrawal)
				dbUtil.commit();

		} catch (Exception x) {
			logger.debug("Exception in updateWillWithDrawDetails() :- " + x);
		} finally {
			try {
				if (!willDrawal) {
					dbUtil.rollback();
				}
				// dbUtil.closeConnection();
			} catch (Exception ex) {
				logger
						.error("Exception in updateWillWithDrawDetails() :-"
								+ ex);
			}finally{
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public boolean insertWillWithDrawl(String[] retDetails, String rType,
			WillDepositDTO wDTO, String willId, String oldWillId, String lang) {
		boolean willDrawal = false;DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			if (CommonConstant.WILL_AGENT.equals(rType)) {
				dbUtil
						.createPreparedStatement(CommonSQL.WILL_DEPOSITE_AGENT_INSERT);
				willDrawal = dbUtil.executeUpdate(retDetails);

				if (willDrawal) {
					insertTestatorDetails(wDTO, willId, oldWillId, lang);
				}

			} else {// if (CommonConstant.WILL_TESTATOR.equals(rType)) {
				logger
						.debug("WillDepositeDAO:-    insertDepositionDetails()::  rtype"
								+ rType + "  retDetails  " + retDetails[0]);

				dbUtil
						.createPreparedStatement(CommonSQL.WILL_DEPOSITE_TESTATOR_INSERT);
				willDrawal = dbUtil.executeUpdate(retDetails);
//				if (willDrawal) {
//					dbUtil.commit();
//					willDrawal = insertPhotoDetails(willId, oldWillId);
//				}
			}
			dbUtil.commit();

		} catch (Exception x) {
			try {
				dbUtil.rollback();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug(e);
			}
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

	public boolean insertTestatorDetails(WillDepositDTO willDepDTO,
			String willId, String oldWillId, String lang) throws Exception {
WillCommonDAO dd = new WillCommonDAO();
		boolean mohallaInserted = false;DBUtility dbUtil=null;
		try {
			
			String[] param = new String[22];
			param[0] = "TESTATOR";
			param[1] = willDepDTO.getFirstName();
			//willDepDTO.getMidName()==null ?"":willDepDTO.getMidName();
			param[2] = willDepDTO.getMidName()==null ?"":willDepDTO.getMidName();
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
			String countryID=getCountryID(willDepDTO.getCountry());
			param[10] = countryID;
			String stateID=getStateID(willDepDTO.getState());
			param[11] = stateID;
			String districtID=getdistrictId(willDepDTO.getCity());
			param[12] = districtID;
			param[13] = willDepDTO.getPin();
			param[14] = willDepDTO.getPhone();
			param[15] = willDepDTO.getMphone();
			param[16] = willDepDTO.getEmail();
			param[17] = willDepDTO.getIdProof();
			param[17] = dd.getTypeId(willDepDTO.getIdProof(), lang);
			param[18] = willDepDTO.getIdProofNo();
			param[19] = willDepDTO.getBankName();
			param[20] = willDepDTO.getBankAddress();
			param[21] = willId;
			dbUtil = new DBUtility();
			dbUtil
					.createPreparedStatement(CommonSQL.WILL_DEPOSITE_TESTATOR_INSERT);
			mohallaInserted = dbUtil.executeUpdate(param);


		} catch (Exception x) {
			logger.debug("" + x.getMessage());
			try {
				dbUtil.rollback();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug("" + e.getMessage());
			}
		} finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return mohallaInserted;
	}
	
	
	//ANUJ start
	public String insertWillWithDrawDetails1(String status, String userId
			, String withDrawDate, String willId,
			String updateStatus, String updateFlag, float total, String funId){
		String k="";DBUtility dbUtil=null;
		try{
			dbUtil = new DBUtility();
		

		
		dbUtil.createStatement();
		
		ArrayList alist=dbUtil.executeQuery(CommonSQL.seqCall);
	
		ArrayList s=(ArrayList)alist.get(0);
		k=(String)s.get(0);
	//for next query
		dbUtil.createPreparedStatement(CommonSQL.INSERT_WITH_PYMNT);
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
		logger.debug("insertWillWithDrawDetails   " + updateStatus);
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in insertWillDeposit() :-" + ex);
			}
		}
		
		return k;
	}
	//ANUJ end

	public String insertWillWithDrawDetails(String status, String userId,
			String newWillId, String withDrawDate, String willId,
			String updateStatus, String updateFlag, float total, String funId) {
		boolean willUpdate = false;
		String k="";DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			if (updateStatus.equalsIgnoreCase("WI")) {
				dbUtil
						.createPreparedStatement(CommonSQL.WILL_WITHDRAWL_RECORD_INSERT);
			} else if (updateStatus.equalsIgnoreCase("RI")) {
				dbUtil
						.createPreparedStatement(CommonSQL.WILL_RETRIVAL_RECORD_INSERT);
			}
			WillViewBD view = new WillViewBD();
			userId = view.getName(userId);
			
			
			String[] param = new String[7];
			param[0] = newWillId;
			param[1] = status;
			logger.debug("test : " + updateStatus.equalsIgnoreCase("WI"));
			if (updateStatus.equalsIgnoreCase("WI")) {
			param[2]="WI";
			}
			else {
			param[2]="RI";	
			}
			//param[2] = updateStatus;
			param[3] = userId;
			param[4] = withDrawDate;
			param[5] = updateFlag;
			param[6] = willId;
try{
			willUpdate = dbUtil.executeUpdate(param);
}
catch(Exception e){
	e.printStackTrace();
}
			if (willUpdate) {
				/*System.out.println("entered the area");
				dbUtil.createStatement();
				ArrayList alist=dbUtil.executeQuery("SELECT IGRS_WILL_PAYMENT_SEQ.NEXTVAL FROM DUAL");
			
				System.out.println("returned");
				ArrayList s=(ArrayList)alist.get(0);
				k=(String)s.get(0);
				System.out.println("value of s = "+k);
			//for next query
				dbUtil.createPreparedStatement("INSERT INTO IGRS_WILL_TXN_PAYMENT_DTLS (WILL_PAYMENT_ID, WILL_TXN_ID,  FUNCTION_ID,  PAYABLE_AMOUNT, PAID_AMOUNT, PAYMENT_FLAG, CREATED_BY, CREATED_DATE) VALUES (?, ?, ?, ?, ?, ?, ?, SYSDATE)");
				Float f=total;
				String s1=f.toString();
				System.out.println("value of s1 "+s1);
				String param1[]=new String[7];
				param1[0]=k;
				param1[1]=newWillId;
				param1[2]=funId;
				param1[3]=s1;
				param1[4]="";
				param1[5]="I";
				param1[6]=userId;
				//param[6] is SYSDATE
				dbUtil.executeUpdate(param1);*/
				dbUtil.commit();
				logger.debug("insertWillWithDrawDetails   " + updateStatus);
			}

		} catch (Exception x) {
			logger.debug("" + x.getMessage());
			try {
				dbUtil.rollback();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug("" + e.getMessage());
			}
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in insertWillDeposit() :-" + ex);
			}
		}
		return newWillId;
	}

	public boolean insertWillRetirval(String[] retDetails, String rType,
			WillDepositDTO wDTO, String willId, String oldWillId, String lang) {
		boolean willDrawal = false;DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();

			if (CommonConstant.WILL_AGENT.equals(rType)) {
				dbUtil
						.createPreparedStatement(CommonSQL.WILL_DEPOSITE_AGENT_INSERT);
				willDrawal = dbUtil.executeUpdate(retDetails);

				if (willDrawal) {
					willDrawal = insertTestatorDetails(wDTO, willId, oldWillId, lang);
				//	if (willDrawal) {
						//willDrawal = insertRetrieverDetails(wDTO, willId);
					//}
				}

			} else {// if (CommonConstant.WILL_TESTATOR.equals(rType)) {

				dbUtil
						.createPreparedStatement(CommonSQL.WILL_DEPOSITE_TESTATOR_INSERT);
				willDrawal = dbUtil.executeUpdate(retDetails);
				if (willDrawal) {
					//willDrawal = insertPhotoDetails(willId, oldWillId);
				}
			//	if (willDrawal) {
					//willDrawal = insertRetrieverDetails(wDTO, willId);
				//}
			}
			if (willDrawal)
				dbUtil.commit();

			if (!willDrawal)
				dbUtil.rollback();

		} catch (Exception x) {
			try {
				dbUtil.rollback();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

	public boolean insertRetrieverDetails(WillDepositDTO willDepDTO,
			String willId) {

		boolean mohallaInserted = false;DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil
					.createPreparedStatement(CommonSQL.WILL_DEPOSITE_TESTATOR_INSERT);

			String[] param = new String[22];
			param[0] = willDepDTO.getRetrieverType();

			if (willDepDTO.getRetrieverType() != null
					&& willDepDTO.getRetrieverType()
							.equalsIgnoreCase("Citizen")) {
				param[1] = willDepDTO.getRetrieverFirstName();
				param[2] = willDepDTO.getRetrieverMidName();
				param[3] = willDepDTO.getRetrieverLastName();
				if (willDepDTO.getRetrieverGender().equalsIgnoreCase("MALE"))
					param[4] = "M";
				else
					param[4] = "F";

				param[5] = "" + willDepDTO.getRetrieverAge();
				param[6] = willDepDTO.getRetrieverFatherName();
				param[7] = willDepDTO.getRetrieverMotherName();
				param[8] = willDepDTO.getRetrieverSpouseName();
				param[9] = willDepDTO.getRetrieverAddress(); // testatorRelationship
				param[10] = willDepDTO.getRetrieverCountry();
				param[11] = willDepDTO.getRetrieverState();
				param[12] = willDepDTO.getRetrieverCity();
				param[13] = "" + willDepDTO.getRetrieverPin();
				param[14] = "" + willDepDTO.getRetrieverPhone();
				param[15] = "" + willDepDTO.getRetrieverMPhone();
				param[16] = willDepDTO.getRetrieverEMail();
				param[17] = willDepDTO.getRetrieverIdProof();
				param[18] = willDepDTO.getRetrieverIdProofNo();
				param[19] = willDepDTO.getRetrieverBankName();
				param[20] = willDepDTO.getRetrieverBankAddress();
			} else if (willDepDTO.getRetrieverType() != null
					&& willDepDTO.getRetrieverType().equalsIgnoreCase("Court")) {
				param[1] = willDepDTO.getCourtName();
				param[2] = willDepDTO.getRepFirstName();
				param[3] = willDepDTO.getRepMiddleName();
				/*
				 * if(willDepDTO.getGender().equalsIgnoreCase("MALE")) param[4] =
				 * "M"; else param[4] = "F";
				 */
				// param[5] = "" + willDepDTO.getAge();
				param[6] = willDepDTO.getRepLastName();
				param[7] = willDepDTO.getRepDesg();
				// param[8] = willDepDTO.getSpouseName();
				param[9] = willDepDTO.getCourtAddress();
				param[10] = willDepDTO.getCourtCountry();
				param[11] = willDepDTO.getCourtState();
				param[12] = willDepDTO.getCourtCity();
				param[13] = "" + willDepDTO.getCourtPin();
				param[14] = "" + willDepDTO.getCourtPhone();
				param[15] = "" + willDepDTO.getMphone();
				param[16] = willDepDTO.getEmail();
				param[17] = willDepDTO.getIdProof();
				param[18] = willDepDTO.getIdProofNo();
				param[19] = willDepDTO.getBankName();
				param[20] = willDepDTO.getBankAddress();
			}

			param[21] = willId;

			mohallaInserted = dbUtil.executeUpdate(param);
		} catch (Exception x) {
			logger.debug("" + x.getMessage());
			try {
				dbUtil.rollback();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug("" + e.getMessage());
			}
		} finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mohallaInserted;
	}

/*	public boolean insertPhotoDetails(String willId, String oldWillId) {
		boolean check = true;
		String sqlText = null;
		BLOB image1 = null;
		BLOB image2 = null;
		BLOB image3 = null;
		BLOB image11 = null;
		BLOB image22 = null;
		BLOB image33 = null;
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
		String sqlTextNew = "";

		sqlText = "SELECT PARTY_THUMB_IMPRESSION, PARTY_PHOTO, PARTY_SIGNATURE FROM IGRS_WILL_TXN_PARTY "
				+ "WHERE WILL_TXN_ID='"
				+ oldWillId
				+ "' AND PARTY_TYPE_ID='TESTATOR'";

		sqlTextNew = "SELECT PARTY_THUMB_IMPRESSION, PARTY_PHOTO, PARTY_SIGNATURE FROM IGRS_WILL_TXN_PARTY "
				+ "WHERE WILL_TXN_ID='"
				+ willId
				+ "' AND PARTY_TYPE_ID='TESTATOR' FOR UPDATE";

		try {
			dbUtil = new DBUtility();
			con = dbUtil.returnConnection();
			st = con.createStatement();
			rst = st.executeQuery(sqlText);
			rst.next();
			image1 = ((OracleResultSet) rst).getBLOB("PARTY_THUMB_IMPRESSION");
			image2 = ((OracleResultSet) rst).getBLOB("PARTY_PHOTO");
			image3 = ((OracleResultSet) rst).getBLOB("PARTY_SIGNATURE");
			chunkSize = image1.getChunkSize();
			binaryBuffer = new byte[chunkSize];
			InputStream ioFile1 = image1.getBinaryStream();
			InputStream ioFile2 = image2.getBinaryStream();
			InputStream ioFile3 = image3.getBinaryStream();
			rst = st.executeQuery(sqlTextNew);
			rst.next();

			image11 = ((OracleResultSet) rst).getBLOB("PARTY_THUMB_IMPRESSION");
			image22 = ((OracleResultSet) rst).getBLOB("PARTY_PHOTO");
			image33 = ((OracleResultSet) rst).getBLOB("PARTY_SIGNATURE");
			position = 1;
			while ((bytesRead = ioFile1.read(binaryBuffer)) != -1) {
				bytesWritten = image11.putBytes(position, binaryBuffer,
						bytesRead);
				position += bytesRead;
				totbytesRead += bytesRead;
				totbytesWritten += bytesWritten;
			}

			chunkSize = image2.getChunkSize();
			binaryBuffer = new byte[chunkSize];
			position = 1;
			while ((bytesRead = ioFile2.read(binaryBuffer)) != -1) {
				bytesWritten = image22.putBytes(position, binaryBuffer,
						bytesRead);
				position += bytesRead;
				totbytesRead += bytesRead;
				totbytesWritten += bytesWritten;
			}

			chunkSize = image3.getChunkSize();
			binaryBuffer = new byte[chunkSize];

			position = 1;
			while ((bytesRead = ioFile3.read(binaryBuffer)) != -1) {
				bytesWritten = image33.putBytes(position, binaryBuffer,
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

		return willDrawal;
	}
*/	
	public String getCountryID(String countryName) {
		String countryId="";DBUtility dbUtil=null;
		try {
			dbUtil=new DBUtility();
			String sql=CommonSQL.COUNTRY;
			
			dbUtil.createPreparedStatement(sql);
			String[] param = new String[1];
			param[0] = countryName;
			countryId = dbUtil.executeQry(new String[]{countryName});
			if(countryId==null||"null".equalsIgnoreCase(countryId)||countryId==""||"".equalsIgnoreCase(countryId)){
				sql=CommonSQL.COUNTRY_H;
				dbUtil.createPreparedStatement(sql);
				countryId = dbUtil.executeQry(new String[]{countryName});
			}
			logger
					.debug("After getting the Group ID ... " + countryId);
		} catch (Exception x) {
			try {
				dbUtil.rollback();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug(e);
			}
			logger.debug("Exception in insertDepositionDetails() :- " + x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in insertWillDeposit() :-" + ex);
			}
		}
		return countryId;
	}
	
	public String getdistrictId(String districtName) {
		String districtId="";DBUtility dbUtil=null;
		try {
			dbUtil=new DBUtility();
			String sql=CommonSQL.DISTRICT;
			dbUtil.createPreparedStatement(sql);
			String[] param = new String[1];
			param[0] = districtName;
			districtId = dbUtil.executeQry(new String[]{districtName});
			if(districtId==null||"null".equalsIgnoreCase(districtId)||districtId==""||"".equalsIgnoreCase(districtId)){
				sql=CommonSQL.DISTRICT_H;
				dbUtil.createPreparedStatement(sql);
				districtId = dbUtil.executeQry(new String[]{districtName});
			}
			
			logger
					.debug("After getting the country ID ... " + districtId);
		} catch (Exception x) {
			logger.error("Exception in getCountryId :- " + x);
		}finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return districtId;
	}
	
	public String getStateID(String stateName) {
		String stateId="";DBUtility dbUtil=null;
		try {
			dbUtil=new DBUtility();
			String sql=CommonSQL.SELECT_OFFICE;
			dbUtil.createPreparedStatement(sql);
			String[] param = new String[1];
			param[0] = stateName;
			stateId = dbUtil.executeQry(new String[]{stateName});
			if(stateId==null||"null".equalsIgnoreCase(stateId)||stateId==""||"".equalsIgnoreCase(stateId)){
				sql=CommonSQL.SELECT_OFFICE_H;
				dbUtil.createPreparedStatement(sql);
				stateId = dbUtil.executeQry(new String[]{stateName});
			}
			logger
					.debug("After getting the state ID ... " + stateId);
		} catch (Exception x) {
			logger.error("Exception in getstateId :- " + x);
		}finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return stateId;
	}
	 
	  
	  
	  //ANUJ
	  public ArrayList getPendingWillWithdraw(String districtId)
		{
			ArrayList pendingWithdrawalPayment = new ArrayList();
			ArrayList pendingListFinal = new ArrayList();
			
			
			
			ArrayList list1 = new ArrayList();
			
			String[] param={districtId};
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
            "    AND WT.WILL_STATUS   = 'WI'" +
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
            "    WHERE WT.WILL_STATUS    = 'WI'" +
            "    AND WT.WILL_TXN_ID      = WY.WILL_TXN_ID" +
            "    AND WT.WILL_TXN_ID NOT IN" +
            "      (SELECT DISTINCT(WILL_TXN_ID) FROM IGRS_WILL_TXN_PAYMENT_DTLS" +
            "      )" +
            "    ) B" +
            "  ) where SUBSTR(WILL_TXN_ID,3,2)=?" +
            " ORDER BY to_Date(CREATED_DATE, 'dd/mm/yyyy') desc";
			DBUtility dbUtil=null;
			try {
				dbUtil = new DBUtility();
				//dbUtil.createStatement();
						String sql= CommonSQL.PENDING_WITH_LIST;
						dbUtil.createPreparedStatement(query);
						
						try
						{	
							pendingWithdrawalPayment = dbUtil.executeQuery(new String[]{districtId});
					           
				
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
	  
	  public ArrayList getDetails(String officeId){
		  ArrayList alist= new ArrayList();
		  String param[]= new String[1];
		  param[0]=officeId;
		  String sql=CommonSQL.OFC_DTLS;
		  DBUtility dbUtil=null;
		  try {
			dbUtil= new DBUtility();
			dbUtil.createPreparedStatement(sql);
			alist=dbUtil.executeQuery(param);
			
			
			
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
		  
		  return alist;
		  
		  }
	  
	 public boolean insertToDB(String sql, String param[]){
		 boolean abc=false;
		// System.out.println("ANUJ");
		 DBUtility dbUtil=null;
		 try {
			dbUtil= new DBUtility();
			
			dbUtil.createPreparedStatement(sql);
			abc=dbUtil.executeUpdate(param);
			
			
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
		 
		 return abc;
	 }
	  
	  //ANUJ
}
