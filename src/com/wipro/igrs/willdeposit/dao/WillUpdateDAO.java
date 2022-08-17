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
 * FILE NAME: WillUpdateDAO.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 12th April 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE DATA ACCESS OBJECTS FOR WILL UPDATION.
 */

package com.wipro.igrs.willdeposit.dao;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.db.DataSourceLookUp;
import com.wipro.igrs.util.PropertiesFileReader;
import com.wipro.igrs.willdeposit.bd.WillViewBD;
import com.wipro.igrs.willdeposit.dto.WillDepositDTO;
import com.wipro.igrs.willdeposit.sql.CommonSQL;

/**
 * @author NIHAR M.
 * 
 */
public class WillUpdateDAO {

	//DBUtility dbUtil;
	private Logger logger = (Logger) Logger.getLogger(WillUpdateDAO.class);
	//Connection con = null;
	

	/**
	 * @throws Exception
	 */
	public WillUpdateDAO() throws Exception {
		//dbUtil = new DBUtility();
	}

	/**
	 * @param willId
	 * @param status
	 * @param fromDate
	 * @param toDate
	 * @return ArrayList
	 */
	public ArrayList getDeliveryViewDetails(String willId, String fromDate,
			String toDate, String status,String officeId) {
		WillViewBD bd= new WillViewBD();
		System.out.println("ANUJ");
		String districtId=bd.getDistrictId(officeId);
		logger.debug(willId + ":" + status + ":" + fromDate + ":" + toDate);
		String prm[]=new String[1];
		prm[0]=districtId;
		String sql = "SELECT igrs_will_txn_party.will_txn_id, igrs_will_txn_party.first_name,"
				+ "igrs_will_txn_party.middle_name,  igrs_will_txn_party.last_name,"
				+ " to_char(igrs_will_txn.created_date, 'DD/MM/YYYY'), igrs_will_txn.will_status, TO_CHAR(igrs_will_txn.withdraw_due_date, 'DD/MM/YYYY')"
				+ " "
				+ "FROM igrs_will_txn, igrs_will_txn_party "
				+ " WHERE "
				+ " igrs_will_txn.will_status="
				+ " 'Pending Deposit' "
				+ " AND "
				+ "igrs_will_txn_party.PARTY_TYPE_ID='TESTATOR' AND igrs_will_txn_party.will_txn_id= igrs_will_txn.WILL_TXN_ID  AND SUBSTR(igrs_will_txn.WILL_TXN_ID,3,2)= ?";

		if (willId != null && willId.length() > 0) {
			prm= new String[2];
			prm[0]=districtId;
			prm[1]=willId;
			System.out.println("PATEL");
			sql = sql + " " + "AND igrs_will_txn_party.will_txn_id =?";
					
		}
		if (fromDate != null && fromDate.length() > 0 && toDate != null
				&& toDate.length() > 0) {
			if (willId == null || willId.length() <= 0) {
				prm=new String[3];
				prm[0]=districtId;
				//prm[1]=willId;
				prm[1]=fromDate;
				prm[2]=toDate;
				System.out.println("Honda");
				sql = sql+" AND to_char(igrs_will_txn.created_date, 'DD/MM/YYYY') >= "
						+ "?"
						+ " and to_char(igrs_will_txn.created_date, 'DD/MM/YYYY') <= "
						+ "?";
			}
			else{
			prm=new String[4];
			prm[0]=districtId;
			prm[1]=willId;
			prm[2]=fromDate;
			prm[3]=toDate;
			System.out.println("HErO");
			sql = sql+" AND to_char(igrs_will_txn.created_date, 'DD/MM/YYYY') >= "
					+ "?"
					+ " and to_char(igrs_will_txn.created_date, 'DD/MM/YYYY') <= "
					+ "?";
			}
		}
		logger.debug("update query:" + sql);

		/*
		 * String sql = "SELECT distinct igrs_will_txn_party.will_txn_id,
		 * igrs_will_txn_party.first_name,"+ "igrs_will_txn_party.middle_name,
		 * igrs_will_txn_party.last_name,"+
		 * "to_char(igrs_will_txn.created_date), igrs_will_txn.will_status,
		 * TO_CHAR(igrs_will_txn.withdraw_due_date)"+" "+ "FROM igrs_will_txn,
		 * igrs_will_txn_party WHERE(igrs_will_txn.will_txn_id =
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
		ArrayList list = null;DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			logger.debug(sql);
			list = dbUtil.executeQuery(prm);

		} catch (Exception x) {
			x.getCause();
		}finally {
			try {
				
				 dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception while closingconnection() :-" + ex);
			}
		}
		return list;
	}

	/**
	 * @param willParamId
	 * @return ArrayList
	 */
	public ArrayList getwillIdDetails(String willParamId) { // VIEW 2nd

		String sql = CommonSQL.WILL_ID_DETAILS_QUERY_1;
		sql += "igrs_will_txn_party.will_txn_id='" + willParamId + "'";

		if (willParamId != null) {

			sql += " "
					+ "UNION ALL"
					+ " "
					+ "SELECT  NULL col1, NULL col2, NULL col3, NULL col4, NULL col5, NULL col6,NULL col7, NULL col8, NULL col9, NULL col10,"
					+ " igrs_will_txn_party.will_txn_id col11, "
					+ "igrs_will_txn_party.party_type_id col12,igrs_will_txn_party.first_name col13, igrs_will_txn_party.middle_name col14, "
					+ "igrs_will_txn_party.last_name col15, igrs_will_txn.will_status col16, igrs_will_txn_party.gender col17, igrs_will_txn_party.age col18, "
					+ "igrs_will_txn_party.guardian_name col19, igrs_will_txn_party.mother_name col20"
					+ " "
					+ "FROM igrs_will_txn_party, igrs_will_txn where igrs_will_txn_party.PARTY_TYPE_ID = 'T' and igrs_will_txn_party.will_txn_id =  '"
					+ willParamId + "')";

			logger.debug("after calc   " + sql);
		}
		DBUtility dbUtil=null;
		ArrayList list = null;
		try {dbUtil = new DBUtility();
			dbUtil.createStatement();

			list = dbUtil.executeQuery(sql);
		} catch (Exception x) {
			logger.debug("Exception in getWillDeposit() :- " + x);
		}finally {
			try {
				
				 dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception while closingconnection() :-" + ex);
			}
		}
		return list;
	}

	/**
	 * @param param
	 * @return boolean
	 */
	public boolean updateDeliveryDetails(String remarks, String willid,
			String userId) {
		boolean willDrawal = false;
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.WILL_WITHDRAWL_INSERT);

			String[] param = new String[3];
			param[0] = willid;
			param[1] = userId;
			param[2] = remarks;

			willDrawal = dbUtil.executeUpdate(param);
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
	
	public boolean updateWillTreasuryId(String treasuryId, String willid,
			String userId) {
		boolean willDrawal = false;
		DBUtility dbUtil=null;

		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.WILL_TREASURY_UPDATE);

			String[] param = new String[2];
			param[0] = treasuryId;
			param[1] = willid;

			willDrawal = dbUtil.executeUpdate(param);
			if (willDrawal){
				String prm[] = new String[2];
				
			prm[0]="Deposited";
			prm[1]=willid;
				dbUtil.createPreparedStatement("UPDATE IGRS_WILL_TXN SET WILL_STATUS =? WHERE WILL_TXN_ID=? ");
			dbUtil.executeUpdate(prm);
				dbUtil.commit();

		}} catch (Exception x) {
			logger.debug("Exception in updateWillWithDrawDStatus() :- " + x);
		} finally {
			try {
				if (!willDrawal) {
					dbUtil.rollback();
				}
				 dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in insertWillWithDrawal() :-" + ex);
			}
		}
		return willDrawal;
	}
	
	public boolean updateWithDrawalStatus(String willId){
		boolean willDrawal = false;
		DBUtility dbUtil=null;
		Connection con = null;
		PreparedStatement prepStmt=null;
		try {dbUtil = new DBUtility("");
		
			if(con == null || con.isClosed()) {
				con = dbUtil.getDBConnection();
			}
				
				int rowCount=0;
				String sqlQuery=CommonSQL.UPDATE_STATUS_W;
				prepStmt = con.prepareStatement(sqlQuery);
				prepStmt.setString(1, willId);
				rowCount = prepStmt.executeUpdate();
				logger.debug("Will Files Deposited : RowCount " + rowCount);
				prepStmt.close();
				
				con.commit();

		}
		catch(Exception e){
			logger.debug("Exception in inserting files in Will Deposit" + e);
		} finally {
			try {
				if (!willDrawal) {
					dbUtil.rollback();
				}

				
				dbUtil.closeConnection();
				
			
			} catch (Exception ex) {
				logger.error("Exception in insertWillWithDrawal() :-" + ex);
			}
		}
		
		
		
		return willDrawal;
	}
	public boolean updateRetrievalStatus(String willId){
		boolean willDrawal = false;
		DBUtility dbUtil=null;
		Connection con = null;
		PreparedStatement prepStmt=null;
		try {dbUtil = new DBUtility();
			if(con == null || con.isClosed()) {
				con = dbUtil.getDBConnection();
			}
				
				int rowCount=0;
				String sqlQuery=CommonSQL.UPDATE_STATUS_R;
				prepStmt = con.prepareStatement(sqlQuery);
				prepStmt.setString(1, willId);
				rowCount = prepStmt.executeUpdate();
				logger.debug("Will Files Deposited : RowCount " + rowCount);
				prepStmt.close();
				con.commit();

		}
		catch(Exception e){
			logger.debug("Exception in inserting files in Will Deposit" + e);
		} finally {
			try {
				if (!willDrawal) {
					dbUtil.rollback();
				}
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in insertWillWithDrawal() :-" + ex);
			}
		}
		
		
		
		return willDrawal;
	}
	

}
