/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserRegistrationAction.java
 * Author      :   Nihar Ranjan Mishra 
 * Description :   Represents the DTO Class for User Registration.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Nihar R Mishra  28th Dec, 2007	 		 
 * --------------------------------------------------------------------------------
 */


package com.wipro.igrs.newuser.util;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.wipro.igrs.db.DBUtility;
import org.apache.log4j.Logger;

/**
 * @author nihraa
 *
 */
public class CommonUtil {
	private String url = null;
	private String initial = null;
	private String user = null;
	private String pwd = null;
	/**
	 * Logger for this class
	 */
	private  final Logger logger = Logger.getLogger(CommonUtil.class);
	
	private DBUtility dbUtil;
	/**
	 * CommonUtil
	 */
	public CommonUtil() {
		try {
			dbUtil = new DBUtility();
		} catch (Exception x) {
			System.out.println(x);
		}
	}
	/**
	 * @param tableName
	 * @param colName
	 * @param prefix
	 * @return String
	 */
	public String getAutoId(String tableName, String colName, String prefix) {
		String refId = null;
		try {
			String sqlCurrentdate = "SELECT TO_CHAR(SYSDATE,'DDMMYYYY') FROM DUAL";
			dbUtil.createStatement();
			String currentDate = dbUtil.executeQry(sqlCurrentdate);
			System.out.println("current date:-"+currentDate);
			String willIdPrefix = prefix + currentDate;
			String sqlWillId = "SELECT MAX(TO_NUMBER(SUBSTR(" + colName + "," + (willIdPrefix.length() + 1) + "))) FROM " + tableName + " WHERE " + colName + "  LIKE '%" + willIdPrefix + "%'";
			String sqlId = dbUtil.executeQry(sqlWillId);
			if ("".equals(sqlId)) {
				sqlId = "0";
			}
			int id = Integer.parseInt(sqlId);
			id++;

			if(id >= 0 && id < 10)
				sqlId = "0000" + id;
			else if (id >= 10 && id < 100)
				sqlId = "000" + id;
			else if (id >= 100 && id < 1000)
				sqlId = "00" + id;
			else if (id >= 1000 && id < 10000)
				sqlId = "0" + id;
			else if (id >= 10000 && id < 100000)
				sqlId = "" + id;

			refId=willIdPrefix + sqlId;
 
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception x) {
				System.out.println(x);
			}
		}


		return refId;
	}
	private Object getCommonHome(String jndi) throws NamingException {
		Properties props = new Properties();

		getProperties();
		props.put("java.naming.provider.url", url);
		props.put("java.naming.factory.initial", initial);
		props.put("java.naming.security.principal", user);
		props.put("java.naming.security.credentials", pwd);

		final InitialContext context = 
			new InitialContext(props); // replace with provider specific properties file
		return context.lookup(jndi);
	}



	/**
	 * getProperties
	 */
	private void getProperties() {
		try {
			PropertiesFileReader pr = 
				PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
			url = pr.getValue("PROVIDER_URL");
			initial = pr.getValue("INITIAL_FACTORY");
			user = pr.getValue("JNDI_PRINCIPAL");
			pwd = pr.getValue("JNDI_CREDENTIALS");
		} catch (Exception x) {
			System.out.println(x);
		}
	}
	
	/**
	 * @param content
	 * @param fileName
	 * @return boolean
	 */
	public boolean createJavaScriptFile(String content, String fileName) {
		boolean bol = false;
		try {

	           FileOutputStream out = new FileOutputStream(fileName);
	           PrintWriter      prn = new  PrintWriter(out);

	           prn.write(content);
	           prn.close();
			bol = true;
		}catch (Exception x) {
			bol = false;
			logger.debug(x);
		}
		return bol;
	}
	

}
