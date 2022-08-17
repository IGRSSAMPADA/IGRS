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


package com.wipro.igrs.util;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;

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
	private static final Logger logger = Logger.getLogger(CommonUtil.class);
	
	private DBUtility dbUtil;
	/**
	 * CommonUtil
	 */
	public CommonUtil() {
	 
	}
	
	public String getCurrentTime(){
		String currentDate ="";
		try{
		dbUtil = new DBUtility();
		String sqlCurrentdate = "SELECT TO_CHAR(SYSDATE,'MMYYYY') FROM DUAL";
		dbUtil.createStatement();
		currentDate = dbUtil.executeQry(sqlCurrentdate);
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception x) {
				System.out.println(x);
			}			
		}	
		return currentDate;
	}
	
	public String getWillId(String distID, String refType){
		String sqlId="";
		if(distID.length()<2){
			distID="0"+distID;
		}
		String willIdPrefix = "MP"+distID+getCurrentTime()+refType;
				
		try{
		String sqlWillId = "SELECT MAX(TO_NUMBER(SUBSTR(WILL_TXN_ID,"+(willIdPrefix.length()+1)+"))) FROM IGRS_WILL_TXN  WHERE WILL_TXN_ID LIKE '%" + willIdPrefix + "%'";
		dbUtil = new DBUtility();
		dbUtil.createStatement();
		sqlId = dbUtil.executeQry(sqlWillId);

		if(sqlId == null || sqlId.equals(""))
			sqlId="0";

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

		sqlId=willIdPrefix+sqlId;

	} catch (Exception x) {
		logger.debug(x);
	} finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception x) {
			logger.debug(x);
		}
	}
	logger.debug("new Will Id------------>  "+willIdPrefix);
		return sqlId;
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
			dbUtil = new DBUtility();
			String sqlCurrentdate = "SELECT TO_CHAR(SYSDATE,'DDMMYYYY') FROM DUAL";
			dbUtil.createStatement();
			String currentDate = dbUtil.executeQry(sqlCurrentdate);
			dbUtil.closeConnection();
			String willIdPrefix = prefix+currentDate ;
			String sqlWillId = "SELECT MAX(TO_NUMBER(SUBSTR(" + colName + "," + (12 + 1) + "))) FROM " + tableName;// + " WHERE " + colName + "  LIKE '%" + willIdPrefix + "%'";
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String sqlId = dbUtil.executeQry(sqlWillId);
		//	ArrayList sqlId1 = dbUtil.executeQuery(sqlWillId);
			if(sqlId == null || sqlId.equals(""))
				sqlId="0";
		//	if ("".equals(sqlId)) {
		//		sqlId = "0";
			//}
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

			refId= sqlId;
 
			refId=willIdPrefix+refId;
			logger.debug("refId------------------>  "+refId);
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
			logger.debug(x);
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
	
	/**
	 * getConvertedDate
	 * Returns String which returns a parsed date format.
	 * @param dFromDate
	 * @return String
	 *  Added by OneApps
	 */
	public static String getConvertedDate(String dFromDate) {
		String inputDate = dFromDate;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");		
		String finalDate = "";
		Date newDate = new Date();
		try {
			newDate = formatter.parse(inputDate);
			SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
			finalDate = format.format(newDate);			
		} catch (ParseException e) {
			System.out.print(e);
		}
		return  finalDate;
	}
	
    /**
     * @param inputDate
     * @param dateFormat
     * @return
     * Added by OneApps
     */
    public static java.util.Date getDate(String inputDate, 
            java.text.DateFormat dateFormat) {

		if (inputDate == null)
		return null;
		
		try {
		
		dateFormat.setLenient(false);
		Date theDate = dateFormat.parse(inputDate);
		java.util.Calendar calendar = Calendar.getInstance();
		calendar.setLenient(false);
		calendar.setTime(theDate);
		if (calendar.get(Calendar.YEAR) < 1900)
		return null;
		
		return theDate;
		} catch (Exception e) {
		}
		return null;
}
	/**
	 * @param stringDate
	 * @return
	 */
	public static java.util.Date getJavaUtilDate( String stringDate)
	{
		try {
				 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				 sdf.setLenient(false);
				 return sdf.parse(stringDate);
			} catch(Exception e) {  return null;  }
	}


	/**
	 * @param strStartDate
	 * @param strEndDate
	 * @return
	 */
	public static boolean isGreater(String strStartDate,String strEndDate)
	{
		java.util.Date startDate = getJavaUtilDate(strStartDate);
		java.util.Date endDate = getJavaUtilDate(strEndDate);
		if(startDate.after(endDate))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	 /**
     *  One Apps END
     */
	
	public String getCurrencyFormat(String number) {
		String returnNumber="";
		try {
			NumberFormat formatter = new DecimalFormat("0.00");
			returnNumber = formatter.format(Double.parseDouble(number));
			logger.debug("returnNumber:-"+returnNumber);
		}catch(Exception x) {
			logger.debug(x);
		}
		return returnNumber;
	}
	
	public boolean convertPdfHindi(HashMap hm,String reportPath,HttpServletResponse response)
	{
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		Connection conn=null;
		byte[] pdf=null;
               try{
            	//database connection to get connection object
	            String dbUrl = "jdbc:oracle:thin:@10.105.115.74:1521:ora11g";
				String dbDriver = "oracle.jdbc.driver.OracleDriver";
				String dbUname = "igrssit";
				String dbPwd = "igrssit";
				Class.forName(dbDriver);
				conn = DriverManager.getConnection(dbUrl, dbUname, dbPwd);
            	System.out.println("jasper compiling start");
            	ByteArrayOutputStream  pdfReport = new ByteArrayOutputStream();
            	//Generate Jasper Report after compilation
                jasperReport = JasperCompileManager.compileReport(reportPath);
                System.out.println("jasper compiling end");
                // Generate jasper print
                jasperPrint = JasperFillManager.fillReport(jasperReport,hm,conn);
        		//conversion to pdf representation of jsprint file using JRPdfEporter
        		JRPdfExporter exporter = new JRPdfExporter();
        		System.out.println("OPENOFFICE_PATH = "+System.getenv("OPENOFFICE_PATH"));
        		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        		exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
        		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
        		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReport);
        		exporter.exportReport();
        		//added by shruti
        		ByteArrayOutputStream os = (ByteArrayOutputStream)exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
        		response.setContentType("application/pdf");
        		response.setHeader("Content-Disposition", "attachment; filename=\"report.pdf");
        		response.setContentLength(pdfReport.size());
        		ServletOutputStream out = response.getOutputStream();
        		os.writeTo(out);
        		out.flush();
        		out.close();
        		//end
               }
               catch (JRException ex){
            	   ex.printStackTrace();
               }
               catch ( Exception exe) 
       		{
       		exe.printStackTrace();
       		}
		return true;
}
}
