/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI           xxxxx              
 * --------------------------------------------------------------------------------
*/
package com.wipro.igrs.caseMonitoring.util;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;

public class CommonUtil {
	
	private  Logger logger = Logger.getLogger(CommonUtil.class);

	private DBUtility dbUtil;

	/**
	 * Method   :  CommonUtil
	 * Description  :Default Constructor
	 */
	public CommonUtil() {
		try {
			dbUtil=new DBUtility();
		} catch (Exception x) {
			logger.debug(x);
		}
	}

	/**
   * Method            : getAutoId
   * Description       : This method gets Id value by Auto-generetae
   * @param tableName  : String
   * @param colName    : String
   * @param prefix     : String
   * @return  refId    : String
   * @throws           : Exception
   */
	public synchronized String getAutoId(String tableName,String colName,String prefix){
		String refId=null;
		try {
			String sqlCurrentdate="SELECT TO_CHAR(SYSDATE,'DDMMYYYY') FROM DUAL";
			dbUtil.createStatement();
			String currentDate=dbUtil.executeQry(sqlCurrentdate);
			logger.debug("current date:-"+currentDate);
			String intimationTxnRefIdPrefix=prefix+currentDate;
			String sqlIntimationTxnRefId="SELECT MAX(TO_NUMBER(SUBSTR("+colName+","+(intimationTxnRefIdPrefix.length()+1)+"))) FROM "+tableName+" WHERE "+colName+"  LIKE '%"+intimationTxnRefIdPrefix+"%'";
			String sqlId=dbUtil.executeQry(sqlIntimationTxnRefId);
			if ("".equals(sqlId)) {
				sqlId="0";
			}
			int id=Integer.parseInt(sqlId);
			id++;

			if (id>=0 && id<10)
				sqlId = "0000"+id;
			else if (id>=10 && id<100)
				sqlId = "000"+id;
			else if (id>=100 && id<1000)
				sqlId = "00"+id;
			else if (id>=1000 && id<10000)
				sqlId = "0"+id;
			else if (id>=10000 && id<100000)
				sqlId = ""+id;

			refId=intimationTxnRefIdPrefix+sqlId;

		} catch (Exception x) {
			logger.debug(x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception x) {
				logger.debug(x);
			}
		}


		return refId;
	}

	//added by shruti
	/**
	   * Method            : getAutoId
	   * Description       : This method gets Id value by Auto-generetae
	   * @param tableName  : String
	   * @param colName    : String
	   * @param prefix     : String
	   * @return  refId    : String
	   * @throws           : Exception
	   */
		public synchronized String getAutoIdNew(String tableName,String colName,String prefix){
			String refId=null;
			try {
				String sqlCurrentdate="SELECT TO_CHAR(SYSDATE,'MMYYYY') FROM DUAL";
				dbUtil.createStatement();
				String currentDate=dbUtil.executeQry(sqlCurrentdate);
				logger.debug("current date:-"+currentDate);
				String intimationTxnRefIdPrefix=prefix+currentDate;
				logger.debug("PREFIX-----<"+intimationTxnRefIdPrefix);
				String sqlIntimationTxnRefId="SELECT MAX(SUBSTR("+colName+","+(intimationTxnRefIdPrefix.length()+1)+")) FROM "+tableName+" WHERE "+colName+"  LIKE '%"+intimationTxnRefIdPrefix+"%'";
				logger.debug("parameters>>>>>"+sqlIntimationTxnRefId);
				
				String sqlId=dbUtil.executeQry(sqlIntimationTxnRefId);
				logger.debug("SQLID>>>>>>>>>"+sqlId);
				if ("".equals(sqlId)) {
					sqlId="0";
				}
				int id=Integer.parseInt(sqlId);
				id++;

				if (id>=0 && id<10)
					sqlId = "0000"+id;
				else if (id>=10 && id<100)
					sqlId = "000"+id;
				else if (id>=100 && id<1000)
					sqlId = "00"+id;
				else if (id>=1000 && id<10000)
					sqlId = "0"+id;
				else if (id>=10000 && id<100000)
					sqlId = ""+id;
 
				refId=intimationTxnRefIdPrefix+sqlId;

			} catch (Exception x) {
				logger.debug(x);
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception x) {
					logger.debug(x);
				}
			}


			return refId;
		}

}
