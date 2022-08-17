
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
 * FILE NAME: WillRetrievalDAO.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 28th DEC 2007 
 * MODIFIED ON:    12th APRIL 2008 
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE ACTION FOR GUIDELINE DELIEVRY UPDATION 
 */

package com.wipro.igrs.willdeposit.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.willdeposit.bd.WillRetrievalBD;
import com.wipro.igrs.willdeposit.constant.CommonConstant;
import com.wipro.igrs.willdeposit.sql.CommonSQL;

/**
 * @author NIHAR M.
 * 
 */
public class WillRetrievalDAO {

	//DBUtility dbUtil=null;
	private Logger logger = (Logger) Logger.getLogger(WillRetrievalDAO.class);

	public WillRetrievalDAO() throws Exception {

		//dbUtil = new DBUtility();
	}

	/**
	 * @param willId
	 * @param fromDate
	 * @param toDate
	 * @param status
	 * @return ArrayList
	 */
	public ArrayList getWillViewDetails(String willId, String fromDate,
			String toDate) {

		// NO USE
		String status = "Deposited";

		logger.debug(willId + ":" + status + ":" + fromDate + ":" + toDate);

		String sql = CommonSQL.RET_VIEW_DTLS;
		String param[]=new String[16];
		param[0]=willId;
		param[1]=willId;
		param[2]=willId;
		param[3]=willId;
		param[4]=willId;
		param[5]=status;
		param[6]=status;
		param[7]=status;
		param[8]=fromDate;
		param[9]=fromDate;
		param[10]=fromDate;
		param[11]=toDate;
		param[12]=toDate;
		param[13]=toDate;
		param[14]=fromDate;
		param[15]=toDate;
		
		
		ArrayList list = null;
		DBUtility dbUtil=null;

		
		try {dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			logger.debug("WillViewDAO:: getWillViewDetails():: BEFORE" + sql);
			list = dbUtil.executeQuery(param);
		} catch (Exception x) {
			x.getCause();
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
	 * @param willId
	 * @param fromDate
	 * @param toDate
	 * @param status
	 * @return ArrayList
	 */
	public ArrayList getWillRetrievalDetails(String willId, String fromDate,
			String toDate) {

		// NO USE
		String status = "Deposited";

		logger.debug(willId + ":" + status + ":" + fromDate + ":" + toDate);
	//	String where = "WHERE";
		
		String param[]=new String[1];
		param[0]=status;
		String sql = CommonSQL.RET_DTLS;

		if (willId != null && willId.length() > 0) {
			
			param=new String[2];
			param[0]=status;
			param[1]=willId;
			sql = sql + " AND igrs_will_txn_party.will_txn_id =?";
					
		}
		if (fromDate != null && fromDate.length() > 0 && toDate != null
				&& toDate.length() > 0) {
			param=new String[4];
			param[0]=status;
			param[1]=willId;
			param[2]=fromDate;
			param[3]=toDate;
			
			sql = sql
					+ " AND to_char(igrs_will_txn.created_date, 'MM/DD/YYYY') >= "
					+ "?"
					+ " and to_char(igrs_will_txn.created_date, 'MM/DD/YYYY') <= "
					+ "?";
		}
		// to_char(tr.startTime, 'MM/DD/YYYY') >= '"+reportFrom+"' and
		// to_char(tr.startTime, 'MM/DD/YYYY') <= '"+reportTo+"'";

		/*
		 * "WHERE(igrs_will_txn.will_txn_id =
		 * igrs_will_txn_party.will_txn_id)"+" "+ "AND
		 * decode(nvl(SUBSTR(igrs_will_txn_party.will_txn_id, 1,
		 * LENGTH('"+willId+"')), 'NA'), '"+willId+"', '"+willId+"', 'NA') " +
		 * "LIKE nvl('"+willId+"', 'NA') || '%'"+" "+ "AND
		 * decode(igrs_will_txn.will_status, '"+status+"', '"+status+"', 'NA') =
		 * nvl('"+status+"', 'NA')"+" "+ "AND
		 * to_date(decode(to_char(created_date, 'DD-MM-YY'), '"+fromDate+"',
		 * '"+fromDate+"', to_char(created_date, 'DD-MM-YY'),"+
		 * "decode('"+fromDate+"', NULL, '1/JAN/2010', to_char(created_date,
		 * 'DD-MM-YY'))), 'DD-MM-YY') >="+ "
		 * to_date(nvl(to_char(to_date('"+toDate+"', 'DD-MM-YY'), 'DD-MM-YY'),
		 * '1/JAN/2010'), 'DD-MM-YY')"+ "AND
		 * to_date(decode(to_char(created_date, 'DD-MM-YY'), '"+toDate+"',
		 * '"+toDate+"', to_char(created_date, 'DD-MM-YY'),"+
		 * "decode('"+fromDate+"', NULL, '1/JAN/2010', to_char(created_date,
		 * 'DD-MM-YY'))), 'DD-MM-YY') <="+
		 * "to_date(nvl(to_char(to_date('"+toDate+"', 'DD-MM-YY'), 'DD-MM-YY'),
		 * '1/JAN/2010'), 'DD-MM-YY')";
		 */
		ArrayList list = null;
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			logger.debug("WillViewDAO:: getWillViewDetails():: BEFORE" + sql);
			list = dbUtil.executeQuery(param);
		} catch (Exception x) {
			x.getCause();
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
	 * @param willParamId
	 * @return ArrayList
	 */
	public ArrayList getwillIdDetails(String willParamId) {
		String sql = CommonSQL.WILL_ID_DETAILS_QUERY;
		sql += "igrs_will_txn_party.will_txn_id='" + willParamId + "'";
		logger.debug("sql1:   " + sql);

		if (willParamId != null) {

			sql += " "
					+ "UNION ALL"
					+ " "
					+ "SELECT  NULL col1, NULL col2, NULL col3, NULL col4, NULL col5, NULL col6,NULL col7, NULL col8, NULL col9, NULL col10, NULL col11,"
					+ "NULL col12, NULL col13, NULL col14, NULL col15, NULL col16, NULL col17,NULL col18, NULL col19, NULL col20,"
					+ " NULL col21, NULL col22, NULL col23,"
					+

					"igrs_will_txn_party.will_txn_id col24, igrs_will_txn_party.party_type_id col25, igrs_will_txn_party.first_name col26,"
					+ "igrs_will_txn_party.middle_name col27, igrs_will_txn_party.last_name col28, igrs_will_txn.will_status col29,"
					+ "igrs_will_txn_party.gender col30, igrs_will_txn_party.age col31, "
					+ "igrs_will_txn_party.guardian_name col32, igrs_will_txn_party.mother_name col33, igrs_will_txn.delivery_status col34,"
					+

					"to_char(igrs_will_txn.created_date) col35,"
					+ "igrs_will_txn_party.spouse_name col36, igrs_will_txn_party.address col37, igrs_will_txn_party.country_id col38,"
					+ " igrs_will_txn_party.state_id col39,"
					+ "igrs_will_txn_party.district_id col40, igrs_will_txn_party.postal_code col41, igrs_will_txn_party.phone_number col42,"
					+ " igrs_will_txn_party.mobile_number col43,"
					+ "igrs_will_txn_party.email_id col44, igrs_will_txn_party.photo_proof_type_id col45, igrs_will_txn_party.photo_proof_id col46  "
					+ " "
					+ "FROM igrs_will_txn_party, igrs_will_txn where igrs_will_txn_party.PARTY_TYPE_ID = 'T' and igrs_will_txn_party.will_txn_id =  '"
					+ willParamId + "')";
			logger.debug("after calc   " + sql);
		}

		ArrayList list = null;
		DBUtility dbUtil=null;

		
		try {dbUtil = new DBUtility();
			dbUtil.createStatement();
			logger.debug("SQL:   " + sql);
			list = dbUtil.executeQuery(sql);
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

	/**
	 * @param willId
	 * @param rType
	 * @param deposit
	 * @return boolean
	 */
	public boolean updateCitizenDetails(String[] retDetails, String rType, String[] retstatusUpdt) {
		boolean willDrawal = false;
		DBUtility dbUtil=null;

		
		try {dbUtil = new DBUtility();
			if (CommonConstant.WILL_RETRIEVE_CITIZEN.equals(rType)) {
				dbUtil
						.createPreparedStatement(CommonSQL.WILL_RETRIEVE_CITIZEN_INSERT);
				willDrawal = dbUtil.executeUpdate(retDetails);

			} else if (CommonConstant.WILL_RETRIEVE_COURT_FLAG.equals(rType)) {
				dbUtil
						.createPreparedStatement(CommonSQL.WILL_RETRIEVE_COURT_INSERT);
				willDrawal = dbUtil.executeUpdate(retDetails);
			}

			
		 if (willDrawal) {
				
			 dbUtil.createPreparedStatement(CommonSQL.WILL_RETRIEVE_UPDATE);
			 willDrawal = dbUtil.executeUpdate(retstatusUpdt); 
			  
		 }
			 

			dbUtil.commit();

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
		return willDrawal;
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
            "    AND WT.WILL_STATUS   = 'RI'" +
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
            "    WHERE WT.WILL_STATUS    = 'RI'" +
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
						String sql= CommonSQL.PENDING_RET_DTLS;
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
			}
			return pendingWithdrawalPayment;
			}
	  
	  public void deleteDetails(String willId){
		  String param[] = new String[1];
			param[0] = willId;
			String sql = CommonSQL.DEL_DTLS1;DBUtility dbUtil=null;
			try {
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(sql);
				boolean boo = dbUtil.executeUpdate(param);
				
				if (boo) {
					deleteDetails2(willId);

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
			
			String sql = CommonSQL.DEL_DETLS;
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
	  
	  public void deleteDetails2(String willId){
		  String param[] = new String[1];
			param[0] = willId;
			
			String sql = CommonSQL.DEL_DTLS2;
			DBUtility dbUtil=null;
			try {
				dbUtil = new DBUtility();
				dbUtil.setAutoCommit(false);
				dbUtil.createPreparedStatement(sql);
				boolean boo = dbUtil.executeUpdate(param);
				if (boo) {
					dbUtil.commit();
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
	  //ANUJ

}
