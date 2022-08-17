
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
 * FILE NAME: WillCommonDAO.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING:	   9th May 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE BUSINESS DELEGATE CLASS
 */

package com.wipro.igrs.willdeposit.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.willdeposit.sql.CommonSQL;


/**
 * @author NIHAR M.
 *
 */
public class WillCommonDAO {

	private Logger logger = (Logger) Logger.getLogger(WillCommonDAO.class);
	//DBUtility dbUtil;
	public WillCommonDAO() throws Exception{
		
	}


	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getWillFeeValue(String StrFunId) throws Exception {

		ArrayList list = new ArrayList();
		DBUtility dbUtil=null;
		try{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.VISIT_BOOK_FEE_QUERY);
			String [] param = new String [1];
			param[0]=StrFunId;
			list = dbUtil.executeQuery(param);
			logger.debug("WillDepositDAO ::  getWillFeeValue()::  Query:-   "+CommonSQL.VISIT_BOOK_FEE_QUERY);
		} catch (Exception e) {
			logger.debug("Error::    "+e.getMessage());
		}
		finally{
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
	 * for calculating  othersFeeDuty
	 * @param _refFunId
	 * @throws Exception
	 * @return othersFeeDuty
	 */
	public String getWillOtherFeeValue(String _refFunId) throws Exception
	{
		String  othersFeeDuty="";
		DBUtility dbUtil=null;
		try{
			dbUtil = new DBUtility();
			othersFeeDuty=dbUtil.getWillOtherFeeValue(_refFunId);
		}
		catch(Exception e){
			logger.debug("Error:-      "+e.getMessage());
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return othersFeeDuty;

	}  
	
	public ArrayList getWillViewDetails(String willId, String fromDate,
			String toDate, String status, String districtId) { // VIEW 1
		//TO DO line 412 inserted igrs_will_txn.created_by='"+userId+"
	String sql="SELECT igrs_will_txn.will_txn_id, igrs_will_txn_party.first_name,igrs_will_txn.created_by, igrs_will_txn_party.middle_name, " +
	"igrs_will_txn_party.last_name, igrs_will_txn_party.address, to_char(igrs_will_txn.created_date,'dd/mm/yyyy'), igrs_will_txn.will_status, igrs_will_txn.DEPENDANT_WILL_TXN_ID " +
	"FROM igrs_will_txn LEFT OUTER JOIN igrs_will_txn_party ON (igrs_will_txn.will_txn_id = igrs_will_txn_party.will_txn_id ) " +
	"WHERE(igrs_will_txn.will_status = '"+status+"' AND igrs_will_txn_party.PARTY_TYPE_ID='TESTATOR') AND " +
	"((igrs_will_txn.will_txn_id NOT IN  (SELECT igrs_will_txn.dependant_will_txn_id  FROM igrs_will_txn WHERE " +
	"igrs_will_txn.dependant_will_txn_id IS NOT NULL))) AND decode(igrs_will_txn.will_txn_id,  '"+willId+"', '"+willId+"',   'NA') = nvl('"+willId+"',   'NA')  AND " +
	"to_date(decode(to_char(igrs_will_txn.created_date,   'DD-MM-YY'),   '"+fromDate+"',  '"+fromDate+"',   to_char(igrs_will_txn.created_date,   'DD-MM-YY'),   " +
	"decode('"+fromDate+"',   NULL,   '1/JAN/2010',   to_char(igrs_will_txn.created_date,   'DD-MM-YY'))),   'DD-MM-YY') >= " +
	"to_date(nvl(to_char(to_date('"+fromDate+"',  'DD-MM-YY'),   'DD-MM-YY'),   '1/JAN/2010'),   'DD-MM-YY')  AND " +
	"to_date(decode(to_char(igrs_will_txn.created_date,   'DD-MM-YY'), '"+toDate+"', '"+toDate+"',   to_char(igrs_will_txn.created_date,   'DD-MM-YY'),  " +
	" decode('"+toDate+"',   NULL,   '1/JAN/2010',   to_char(igrs_will_txn.created_date,   'DD-MM-YY'))),   'DD-MM-YY') <= " +
	"to_date(nvl(to_char(to_date('"+toDate+"',   'DD-MM-YY'),   'DD-MM-YY'),   '1/JAN/2010'),   'DD-MM-YY') and substr(igrs_will_txn.will_txn_id,3,2) = '"+districtId+"' order by igrs_will_txn.created_date" ;
logger.debug("Sql for will deposit..." + sql);
		logger.debug("Sql for will deposit..." + sql);
		
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(sql);
			logger.debug("WillViewDAO:: getWillViewDetails():: AFTER" + sql);

		} catch (Exception x) {
			x.getCause();
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

public ArrayList getCountry(String countryId){
	ArrayList name= new ArrayList();
	DBUtility dbUtil=null;
	try {
		String param[]=new String[1];
		param[0]=countryId;
		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(CommonSQL.COUNTRY_QUERY_HINDI_WILL);
		name=dbUtil.executeQuery(param);
	} catch (Exception e) {
		logger.debug(e.getCause());
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
	return name;
}
public ArrayList getState(String stateId, String countryId){
	ArrayList name=new ArrayList();
	DBUtility dbUtil=null;
	try {
		String param[]=new String[2];
		param[0]=stateId;
		param[1]=countryId;
		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(CommonSQL.STATE_QUERY_HINDI_WILL);
		name=dbUtil.executeQuery(param);
	} catch (Exception e) {
		logger.debug(e.getCause());
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
	return name;
}

public ArrayList getDistrict(String stateId, String districtId){
	ArrayList name=new ArrayList();
	DBUtility dbUtil=null;
	try {
		String param[]=new String[2];
		param[0]=stateId;
		param[1]=districtId;
		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(CommonSQL.DISTRICT_QUERY_HINDI_WILL);
		name=dbUtil.executeQuery(param);
	} catch (Exception e) {
		logger.debug(e.getCause());
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
	return name;
}
public ArrayList getTypeName(String typeId){
	ArrayList ret = new ArrayList();
	DBUtility dbUtil=null;
	try{
		dbUtil = new DBUtility();
		String param[]=new String[1];
		param[0]=typeId;
		String sql = CommonSQL.PHOTOID_PROOF_NAME;
		dbUtil.createPreparedStatement(CommonSQL.PHOTOID_PROOF_NAME);
		ret=dbUtil.executeQuery(param);
	}
	catch(Exception e){
		logger.debug(e.getMessage());
	}
	finally{
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return ret;
}

public String getTypeId(String typeName, String lang){
	String typeId="";
	DBUtility dbUtil=null;
	try{
		dbUtil = new DBUtility();
		String param[]= new String[1];
		param[0] = typeName;
		if("hi".equalsIgnoreCase(lang)){
			dbUtil.createPreparedStatement(CommonSQL.PHOTO_ID_PROOF_TYPE_H);
		}
		else{
			dbUtil.createPreparedStatement(CommonSQL.PHOTO_ID_PROOF_TYPE_E);
		}
		typeId=dbUtil.executeQry(param);
	}
	catch(Exception e){
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
	
	
	return typeId;
}
}

