/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007
 *==============================================================================
 * File Name   :     RevenueMgmtDAO.java
 * Author      :     @author srenaag
 * Description :     This file contains helper methods for the Database interactions 
 * -----------------------------------------------------------------------------
 * Version         Modified By     Modified On   Modification
 * -----------------------------------------------------------------------------
 * 1.0            M Sreelatha     Dec 24,2007   Created.
 * -----------------------------------------------------------------------------
 */
package com.wipro.igrs.revenuemgmt.dao;

import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.revenuemgmt.dto.RevenueMgmtDTO;
import com.wipro.igrs.revenuemgmt.form.RevenueMgmtForm;
import com.wipro.igrs.revenuemgmt.sql.CommonSQL;

public class RevenueMgmtDAO {
	private  Logger logger = 
		(Logger) Logger.getLogger(RevenueMgmtDAO.class);
	
	DBUtility dbutility = null;
	IGRSCommon igrsCommon = null;

	public RevenueMgmtDAO() {
		try{
		
			
		}
		catch(Exception e){
			e.getStackTrace();
			logger.error(e);
		}
	}
	//DAO for Revenue Collect Rpt District Details
	public ArrayList getDistrictDetails(RevenueMgmtForm revenueMgmtForm){
	
		ArrayList ret = new ArrayList();
		try{
			dbutility = new DBUtility();
		
			String sql = CommonSQL.DISTRICT_DETAILS;
	
        	dbutility.createStatement();
        	ret = dbutility.executeQuery(sql);
       
		}
        catch(Exception e){
    		e.printStackTrace();
    		logger.error(e);
        }
        finally{
        	try{
        		dbutility.closeConnection();	
        	}
        	catch(Exception e){
        		e.getStackTrace();
        		logger.error(e);       		
        	}
        }        
        
        return ret;        
    }
	//DAO for Revenue Collect Rpt Office Type Details
	public ArrayList getOfficeTypeDetails(RevenueMgmtForm revenueMgmtForm){
		
		ArrayList ret = new ArrayList();
		try{
			dbutility = new DBUtility();
			
			String sql = CommonSQL.OFFICE_TYPE_DETAILS;
			
        	dbutility.createStatement();
        	ret = dbutility.executeQuery(sql);
        	
		}
        catch(Exception e){
    		e.printStackTrace();
    		logger.error(e);
        }
        finally
        {
        	try{
        		dbutility.closeConnection();	
        	}
        	catch(Exception e){
        		e.getStackTrace();
        		logger.error(e);       		
        	}
        }        
        
        return ret;        
    }
	//DAO for Revenue Collect Rpt Office Name Details
	public ArrayList getOfficeNameDetails(RevenueMgmtForm revenueMgmtForm){
		
		String districtName = revenueMgmtForm.getRevenueMgmtDTO().getDistrictName();
		
		String districtId = revenueMgmtForm.getRevenueMgmtDTO().getDistrictId();
		
		String officeTypeId = revenueMgmtForm.getRevenueMgmtDTO().getOfficeTypeId();
		
		ArrayList ret = new ArrayList();		
		try{
			dbutility = new DBUtility();
			
			String	sql = CommonSQL.OFFICE_NAME_DETAILS;
			
        	dbutility.createStatement();
        	ret = dbutility.executeQuery(sql+"'"+officeTypeId+"' AND OFFICE_STATUS = 'A' ");
     
			
		}
        catch(Exception e){
    		e.printStackTrace();
    		logger.error(e);
        }
        finally
        {
        	try{
        		dbutility.closeConnection();	
        	}
        	catch(Exception e){
        		e.getStackTrace();
        		logger.error(e);  		
        	}
        }        
       
        return ret;        
    }
	//DAO for Revenue Collection Reports
	public ArrayList getRevenueDetails(RevenueMgmtForm revenueMgmtForm) {
       
        String fromDate = revenueMgmtForm.getFromDate();
        String toDate = revenueMgmtForm.getToDate();
 
        String districtId = revenueMgmtForm.getRevenueMgmtDTO().getDistrictId();
	
		String officeId = revenueMgmtForm.getRevenueMgmtDTO().getOfficeId();
		
        ArrayList ret = new ArrayList();
        try{
        	dbutility = new DBUtility();
        	
        	String sql = CommonSQL.REVENUE_COLLECTION_REPORTS; 
        	
        	dbutility.createStatement();
    		ret = dbutility.executeQuery(sql+ " WHERE ipmd.CHALLAN_ONLINE_DATE BETWEEN TO_CHAR(TO_DATE('"+fromDate+"','dd/mm/yyyy'),'dd-mon-yyyy') and TO_CHAR(TO_DATE('"+toDate+"','dd/mm/yyyy'),'dd-mon-yyyy') " +
					"AND ipmd.DISTRICT_ID = '"+districtId+"' "+        										
					"AND iom.DISTRICT_ID = ipmd.DISTRICT_ID "+
					"AND iom.OFFICE_ID = '"+officeId+"' "+
					"AND ipmd.PAYMENT_TYPE_ID = iptm.PAYMENT_TYPE_ID "+
					"AND ipmd.FUNCTION_ID = ifm.FUNCTION_ID");    	
      System.out.println("IN DAO_--------getRevenueDetails ret = "+ret);  	
        }
        catch(Exception e){
        	e.printStackTrace();
        	logger.error(e);
        }
        finally
        {
        	try{
        		dbutility.closeConnection();	
        	}
        	catch(Exception e){
        		e.getStackTrace();
        		logger.error(e);       		
        	}
        }     
     
        return ret;        
    }
	//DAO for Advance Payments
	public ArrayList  getAdvancePaymentsCreditDetails(RevenueMgmtForm revenueMgmtForm){
	   
        String offId = revenueMgmtForm.getRevenueMgmtDTO().getOffiId();
      //  offId = "prakash";
    	String param[] = new String[1];
    	param[0] = offId;
    	String sql = CommonSQL.GET_SERVICE_PROVIDER_DETAILS_1;
        ArrayList ret = null;        
        try{
        	dbutility = new DBUtility();
      
        	dbutility.createPreparedStatement(sql);
        	ret= dbutility.executeQuery(param);

        }
        catch(Exception e){
    		e.printStackTrace();
    		logger.error(e);
        }
        finally
        {
        	try{
        		dbutility.closeConnection();	
        	}
        	catch(Exception e){
        		e.getStackTrace();
        		logger.error(e);        		
        	}
        }        
       
        return ret;        
    }
	public ArrayList  getAdvancePaymentsDebitDetails(RevenueMgmtForm revenueMgmtForm){
		   
        String offId = revenueMgmtForm.getRevenueMgmtDTO().getOffiId();
     //   offId = "prakash";
    	String param[] = new String[1];
    	param[0] = offId;
    	String sql = CommonSQL.GET_SERVICE_PROVIDER_DETAILS_2;
        ArrayList ret = null;        
        try{
        	dbutility = new DBUtility();
      
        	dbutility.createPreparedStatement(sql);
        	ret= dbutility.executeQuery(param);

        }
        catch(Exception e){
    		e.printStackTrace();
    		logger.error(e);
        }
        finally
        {
        	try{
        		dbutility.closeConnection();	
        	}
        	catch(Exception e){
        		e.getStackTrace();
        		logger.error(e);        		
        	}
        }        
       
        return ret;        
    }

	//DAO for Advance Payments Service Provider Details
	public ArrayList getspDetails(RevenueMgmtForm revenueMgmtForm){
		
		ArrayList ret = new ArrayList();
		try{
			dbutility = new DBUtility();
		
			String sql = CommonSQL.SP_DETAILS;
			
        	dbutility.createStatement();
        	ret = dbutility.executeQuery(sql);
        
		}
        catch(Exception e){
    		e.printStackTrace();
    		logger.error(e);
        }
        finally
        {
        	try{
        		dbutility.closeConnection();	
        	}
        	catch(Exception e){
        		e.getStackTrace();
        		logger.error(e);       		
        	}
        }        
   
        return ret;        
    }
	//DAO for Daily Cash Payments
	public ArrayList getCashPaymentDetails(RevenueMgmtForm revenueMgmtForm){
			
		String date = revenueMgmtForm.getUserDate();
		
		String fromDate = revenueMgmtForm.getFromDate();
	    String toDate = revenueMgmtForm.getToDate();
	  
	    String month = revenueMgmtForm.getMonth();
	 
        String districtId = revenueMgmtForm.getRevenueMgmtDTO().getDistrictId();
		
		String officeId = revenueMgmtForm.getRevenueMgmtDTO().getOfficeId();
		
		String radio = revenueMgmtForm.getRadiodate();
		
		ArrayList ret = new ArrayList();
		try{
			dbutility = new DBUtility();
			
        	String sql = CommonSQL.CASH_ONLINE_PAYMENTS;
        	    	
        	dbutility.createStatement();
        	if(radio.equalsIgnoreCase("date")){
        		sql = sql+ " WHERE ipmd.CREATED_DATE = TO_CHAR(TO_DATE('"+date+"','dd/mm/yyyy'),'dd-mon-yyyy') "; 
        	}
        	else if(radio.equalsIgnoreCase("week")){
        		sql = sql+ " WHERE ipmd.CREATED_DATE BETWEEN TO_CHAR(TO_DATE('"+fromDate+"','dd/mm/yyyy'),'dd-mon-yyyy') and TO_CHAR(TO_DATE('"+toDate+"','dd/mm/yyyy'),'dd-mon-yyyy') ";
        	}
        	else if(radio.equalsIgnoreCase("month")){
        		sql = sql+ " WHERE TRIM(TO_CHAR(ipmd.CREATED_DATE,'MONTH')) = '"+month+"' ";
        	}
        	ret = dbutility.executeQuery(sql+"AND ipmd.DISTRICT_ID = '"+districtId+"' "+        										
					"AND iom.DISTRICT_ID = ipmd.DISTRICT_ID "+
					"AND iom.OFFICE_ID = '"+officeId+"' "+
        			"AND iom.OFFICE_ID = ipmd.OFFICE_ID "+
        			"AND iptm.PAYMENT_TYPE_ID = '01' "+
					"AND ipmd.PAYMENT_TYPE_ID = iptm.PAYMENT_TYPE_ID "+
					"AND ipmd.FUNCTION_ID = ifm.FUNCTION_ID"
					); 
					
        }
        catch(Exception e){
        	e.printStackTrace();
        	logger.error(e);
        }
        finally
        {
        	try{
        		dbutility.closeConnection();	
        	}
        	catch(Exception e){
        		e.getStackTrace();
        		logger.error(e);        		
        	}
        }        
        
        return ret;        
    }
	//DAO for Daily Challan Payments
	public ArrayList getChallanPaymentDetails(RevenueMgmtForm revenueMgmtForm){
		
		String date = revenueMgmtForm.getUserDate();
		
		String fromDate = revenueMgmtForm.getFromDate();
	    String toDate = revenueMgmtForm.getToDate();
	   
	    String month = revenueMgmtForm.getMonth();
	   
        String districtId = revenueMgmtForm.getRevenueMgmtDTO().getDistrictId();
		
		String officeId = revenueMgmtForm.getRevenueMgmtDTO().getOfficeId();
		
		String radio = revenueMgmtForm.getRadiodate();
		
		ArrayList ret = new ArrayList();
		try{
			dbutility = new DBUtility();
			
        	String sql = CommonSQL.CHALLAN_PAYMENTS;
        	
        	dbutility.createStatement();
        	if(radio.equalsIgnoreCase("date")){
        		sql = sql+ " WHERE ipmd.CHALLAN_ONLINE_DATE = TO_CHAR(TO_DATE('"+date+"','dd/mm/yyyy'),'dd-mon-yyyy') "; 
        	}
        	else if(radio.equalsIgnoreCase("week")){
        		sql = sql+ " WHERE ipmd.CHALLAN_ONLINE_DATE BETWEEN TO_CHAR(TO_DATE('"+fromDate+"','dd/mm/yyyy'),'dd-mon-yyyy') and TO_CHAR(TO_DATE('"+toDate+"','dd/mm/yyyy'),'dd-mon-yyyy') ";
        	}
        	else if(radio.equalsIgnoreCase("month")){
        		sql = sql+ " WHERE TRIM(TO_CHAR(CHALLAN_ONLINE_DATE,'MONTH')) = '"+month+"' ";
        	}
        	ret = dbutility.executeQuery(sql+"AND ipmd.DISTRICT_ID = '"+districtId+"' "+        										
					"AND iom.DISTRICT_ID = ipmd.DISTRICT_ID "+
					"AND iom.OFFICE_ID = '"+officeId+"' "+
        			"AND iom.OFFICE_ID = ipmd.OFFICE_ID "+
        			"AND iptm.PAYMENT_TYPE_ID = '02' "+
					"AND ipmd.PAYMENT_TYPE_ID = iptm.PAYMENT_TYPE_ID "+
					"AND ipmd.FUNCTION_ID = ifm.FUNCTION_ID"
					);        	
        	
        }
        catch(Exception e){
        	e.printStackTrace();
        	logger.error(e);
        }
        finally
        {
        	try{
        		dbutility.closeConnection();	
        	}
        	catch(Exception e){
        		e.getStackTrace();
        		logger.error(e);        		
        	}
        }        
        
        return ret;        
    }
	//DAO for DailyOnline Payments
	public ArrayList getOnlinePaymentDetails(RevenueMgmtForm revenueMgmtForm){
		
		String date = revenueMgmtForm.getUserDate();
	
		String fromDate = revenueMgmtForm.getFromDate();
	    String toDate = revenueMgmtForm.getToDate();
	   
	    String month = revenueMgmtForm.getMonth();
	    
        String districtId = revenueMgmtForm.getRevenueMgmtDTO().getDistrictId();
		
		String officeId = revenueMgmtForm.getRevenueMgmtDTO().getOfficeId();
		
		String radio = revenueMgmtForm.getRadiodate();
		
		ArrayList ret = new ArrayList();
		try{
			dbutility = new DBUtility();
			
			String sql = CommonSQL.CASH_ONLINE_PAYMENTS;
        	
        	dbutility.createStatement();
        	if(radio.equalsIgnoreCase("date")){
        		sql = sql+ " WHERE ipmd.CHALLAN_ONLINE_DATE = TO_CHAR(TO_DATE('"+date+"','dd/mm/yyyy'),'dd-mon-yyyy') "; 
        	}
        	else if(radio.equalsIgnoreCase("week")){
        		sql = sql+ " WHERE ipmd.CHALLAN_ONLINE_DATE BETWEEN TO_CHAR(TO_DATE('"+fromDate+"','dd/mm/yyyy'),'dd-mon-yyyy') and TO_CHAR(TO_DATE('"+toDate+"','dd/mm/yyyy'),'dd-mon-yyyy') ";
        	}
        	else if(radio.equalsIgnoreCase("month")){
        		sql = sql+ " WHERE TRIM(TO_CHAR(CHALLAN_ONLINE_DATE,'MONTH')) = '"+month+"' ";
        	}
        	String temp=sql+"AND ipmd.DISTRICT_ID = '"+districtId+"' "+        										
			"AND iom.DISTRICT_ID = ipmd.DISTRICT_ID "+
			"AND iom.OFFICE_ID = '"+officeId+"' "+
			"AND iom.OFFICE_ID = ipmd.OFFICE_ID "+
			"AND iptm.PAYMENT_TYPE_ID = '03' "+
			"AND ipmd.PAYMENT_TYPE_ID = iptm.PAYMENT_TYPE_ID "+
			"AND ipmd.FUNCTION_ID = ifm.FUNCTION_ID";
        	System.out.println("sql-------------------->"+temp);
        	ret = dbutility.executeQuery(sql+"AND ipmd.DISTRICT_ID = '"+districtId+"' "+        										
					"AND iom.DISTRICT_ID = ipmd.DISTRICT_ID "+
					"AND iom.OFFICE_ID = '"+officeId+"' "+
        			"AND iom.OFFICE_ID = ipmd.OFFICE_ID "+
        			"AND iptm.PAYMENT_TYPE_ID = '03' "+
					"AND ipmd.PAYMENT_TYPE_ID = iptm.PAYMENT_TYPE_ID "+
					"AND ipmd.FUNCTION_ID = ifm.FUNCTION_ID"
					);        	
		
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		finally{
			try{
				dbutility.closeConnection();
			}
			catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
			
		}
		return ret;		
	}
	
	//DAO for Revenue Reconciliation Report
	public ArrayList getRevReconDetails(RevenueMgmtForm revenueMgmtForm){
		
		String date = revenueMgmtForm.getUserDate();
		
		String fromDate = revenueMgmtForm.getFromDate();
	    String toDate = revenueMgmtForm.getToDate();
	    
	    String month = revenueMgmtForm.getMonth();
	   
        String districtId = revenueMgmtForm.getRevenueMgmtDTO().getDistrictId();
        
		String officeId = revenueMgmtForm.getRevenueMgmtDTO().getOfficeId();
		
		String officeTypeId = revenueMgmtForm.getRevenueMgmtDTO().getOfficeTypeId();
		
		String radio = revenueMgmtForm.getRadiodate();
		
        ArrayList ret = new ArrayList();
        String retval = "";
        RevenueMgmtDTO revMgmtDTO = new RevenueMgmtDTO();
		try{
			dbutility = new DBUtility();
			
        	String sql = CommonSQL.REV_RECON_REPORT;
        	
        	dbutility.createStatement();
        	if(radio.equalsIgnoreCase("date")){
	        	if(officeTypeId.equalsIgnoreCase("SRO")){	        		
	        		retval = dbutility.executeQry(sql+ ",IGRS_OFFICE_MASTER iom "+
	        				"WHERE ipmd.CHALLAN_ONLINE_DATE = TO_CHAR(TO_DATE('"+date+"','dd/mm/yyyy'),'dd-mon-yyyy') " +
	        				"AND ipmd.DISTRICT_ID = '"+districtId+"' "+
	        				"AND iom.DISTRICT_ID = ipmd.DISTRICT_ID "+
	        				"AND ipmd.OFFICE_ID = '"+officeId+"' "+
	        				"AND iom.OFFICE_ID = ipmd.OFFICE_ID");	
	        		
	        			if(retval.length() == 0) retval="0";
	        		revMgmtDTO.setCashAtSRO(Float.parseFloat(retval));
	        	}
	        	else{					
	        		retval = dbutility.executeQry(sql+ ",IGRS_OFFICE_MASTER iom "+
	        				"WHERE ipmd.CHALLAN_ONLINE_DATE = TO_CHAR(TO_DATE('"+date+"','dd/mm/yyyy'),'dd-mon-yyyy') " +
	        				"AND ipmd.DISTRICT_ID = '"+districtId+"' "+
	        				"AND iom.DISTRICT_ID = ipmd.DISTRICT_ID "+
	        				"AND ipmd.OFFICE_ID = '"+officeId+"' "+
	        				"AND iom.OFFICE_ID = ipmd.OFFICE_ID");	       
	        		if(retval.length() == 0) retval="0";
	        		revMgmtDTO.setCashAtDRO(Float.parseFloat(retval));
	        	}
        		/*retval = dbutility.executeQry(sql+ ",IGRS_PAYMENT_TYPE_MASTER iptm WHERE ipmd.CHALLAN_ONLINE_DATE = TO_CHAR(TO_DATE('"+date+"','dd/mm/yyyy'),'dd-mon-yyyy') " +
												"AND iptm.PAYMENT_TYPE_NAME = 'Online' AND iptm.PAYMENT_TYPE_ID = ipmd.PAYMENT_TYPE_ID"); 
				revMgmtDTO.setOnlinePayments((Float.parseFloat(retval)));*/
				//revMgmtDTO.setCreditLimitPayments((Float.parseFloat((String)ret.get(3))));
				retval = dbutility.executeQry(sql+ ",IGRS_PAYMENT_TYPE_MASTER iptm WHERE ipmd.CHALLAN_ONLINE_DATE = TO_CHAR(TO_DATE('"+date+"','dd/mm/yyyy'),'dd-mon-yyyy') " +
												"AND iptm.PAYMENT_TYPE_NAME = 'Challan' AND iptm.PAYMENT_TYPE_ID = ipmd.PAYMENT_TYPE_ID");
				if(retval.length() == 0) retval="0";
				
				revMgmtDTO.setChallanPayments((Float.parseFloat(retval)));
				if(retval.length() == 0) retval="0";
				retval = dbutility.executeQry(sql+ ",IGRS_FUNCTION_MASTER ifm WHERE ipmd.CHALLAN_ONLINE_DATE = TO_CHAR(TO_DATE('"+date+"','dd/mm/yyyy'),'dd-mon-yyyy') " +
												"AND ipmd.FUNCTION_ID = ifm.FUNCTION_ID AND ifm.FUNCTION_NAME = 'Registration Fee'");
				if(retval.toString().trim().length()!=0){
					revMgmtDTO.setRegistrationFee((Float.parseFloat(retval)));
				}else{
					revMgmtDTO.setRegistrationFee(0);
				}
				
				retval = dbutility.executeQry(sql+ ",IGRS_FUNCTION_MASTER ifm WHERE ipmd.CHALLAN_ONLINE_DATE = TO_CHAR(TO_DATE('"+date+"','dd/mm/yyyy'),'dd-mon-yyyy') " +
												"AND ipmd.FUNCTION_ID = ifm.FUNCTION_ID AND ifm.FUNCTION_NAME = 'Stamp Duty '");
				if(retval.toString().trim().length()!=0){
					revMgmtDTO.setStampDuty((Float.parseFloat(retval)));
				}else{
					revMgmtDTO.setStampDuty(0);
				}
				
				retval = dbutility.executeQry(sql+ ",IGRS_FUNCTION_MASTER ifm WHERE ipmd.CHALLAN_ONLINE_DATE = TO_CHAR(TO_DATE('"+date+"','dd/mm/yyyy'),'dd-mon-yyyy') " +
												"AND ipmd.FUNCTION_ID = ifm.FUNCTION_ID AND ifm.FUNCTION_NAME = 'Refunds'");
				if(retval.toString().trim().length()!=0){
					revMgmtDTO.setRefunds((Float.parseFloat(retval)));
				}else{
					revMgmtDTO.setRefunds(0);
				}
				retval = dbutility.executeQry(sql+ ",IGRS_FUNCTION_MASTER ifm WHERE ipmd.CHALLAN_ONLINE_DATE = TO_CHAR(TO_DATE('"+date+"','dd/mm/yyyy'),'dd-mon-yyyy') " +
												"AND ipmd.FUNCTION_ID = ifm.FUNCTION_ID AND ifm.FUNCTION_NAME = 'Stamp Cases'");
				if(retval.toString().trim().length()!=0){
					revMgmtDTO.setStampCases((Float.parseFloat(retval)));
				}else{
					revMgmtDTO.setStampCases(0);
				}
				retval = dbutility.executeQry(sql+ ",IGRS_FUNCTION_MASTER ifm WHERE ipmd.CHALLAN_ONLINE_DATE = TO_CHAR(TO_DATE('"+date+"','dd/mm/yyyy'),'dd-mon-yyyy') " +
												"AND ipmd.FUNCTION_ID = ifm.FUNCTION_ID AND ifm.FUNCTION_NAME = 'RRC'");
				if(retval.toString().trim().length()!=0){
					revMgmtDTO.setRrc((Float.parseFloat(retval)));
				}else{
					revMgmtDTO.setRrc(0);
				}
        	}
        	if(radio.equalsIgnoreCase("week")){
	        	if(officeTypeId.equalsIgnoreCase("SRO")){	        		
	        		retval = dbutility.executeQry(sql+ ",IGRS_OFFICE_MASTER iom "+
	        				"WHERE ipmd.CHALLAN_ONLINE_DATE BETWEEN TO_CHAR(TO_DATE('"+fromDate+"','dd/mm/yyyy'),'dd-mon-yyyy') and TO_CHAR(TO_DATE('"+toDate+"','dd/mm/yyyy'),'dd-mon-yyyy') " +
	        				"AND ipmd.DISTRICT_ID = '"+districtId+"' "+
	        				"AND iom.DISTRICT_ID = ipmd.DISTRICT_ID "+
	        				"AND ipmd.OFFICE_ID = '"+officeId+"' "+
	        				"AND iom.OFFICE_ID = ipmd.OFFICE_ID");	        	
	        		if(retval.length() == 0) retval="0";
	        		revMgmtDTO.setCashAtSRO(Float.parseFloat(retval));
	        	}
	        	else{					
	        		retval = dbutility.executeQry(sql+ ",IGRS_OFFICE_MASTER iom "+
	        				"WHERE ipmd.CHALLAN_ONLINE_DATE BETWEEN TO_CHAR(TO_DATE('"+fromDate+"','dd/mm/yyyy'),'dd-mon-yyyy') and TO_CHAR(TO_DATE('"+toDate+"','dd/mm/yyyy'),'dd-mon-yyyy') " +
	        				"AND ipmd.DISTRICT_ID = '"+districtId+"' "+
	        				"AND iom.DISTRICT_ID = ipmd.DISTRICT_ID "+
	        				"AND ipmd.OFFICE_ID = '"+officeId+"' "+
	        				"AND iom.OFFICE_ID = ipmd.OFFICE_ID");	  
	        		if(retval.length() == 0) retval="0";
	        		revMgmtDTO.setCashAtDRO(Float.parseFloat(retval));
	        	}
        		/*retval = dbutility.executeQry(sql+ ",IGRS_PAYMENT_TYPE_MASTER iptm WHERE ipmd.CHALLAN_ONLINE_DATE BETWEEN TO_CHAR(TO_DATE('"+fromDate+"','dd/mm/yyyy'),'dd-mon-yyyy') and TO_CHAR(TO_DATE('"+toDate+"','dd/mm/yyyy'),'dd-mon-yyyy') " +
												"AND iptm.PAYMENT_TYPE_NAME = 'Online' AND iptm.PAYMENT_TYPE_ID = ipmd.PAYMENT_TYPE_ID"); 
				revMgmtDTO.setOnlinePayments((Float.parseFloat(retval)));*/
				//revMgmtDTO.setCreditLimitPayments((Float.parseFloat((String)ret.get(3))));
				retval = dbutility.executeQry(sql+ ",IGRS_PAYMENT_TYPE_MASTER iptm WHERE ipmd.CHALLAN_ONLINE_DATE BETWEEN TO_CHAR(TO_DATE('"+fromDate+"','dd/mm/yyyy'),'dd-mon-yyyy') and TO_CHAR(TO_DATE('"+toDate+"','dd/mm/yyyy'),'dd-mon-yyyy') " +
												"AND iptm.PAYMENT_TYPE_NAME = 'Challan' AND iptm.PAYMENT_TYPE_ID = ipmd.PAYMENT_TYPE_ID");				
				if(retval.length() == 0) retval="0";
				revMgmtDTO.setChallanPayments((Float.parseFloat(retval)));
			
				retval = dbutility.executeQry(sql+ ",IGRS_FUNCTION_MASTER ifm WHERE ipmd.CHALLAN_ONLINE_DATE BETWEEN TO_CHAR(TO_DATE('"+fromDate+"','dd/mm/yyyy'),'dd-mon-yyyy') and TO_CHAR(TO_DATE('"+toDate+"','dd/mm/yyyy'),'dd-mon-yyyy') " +
												"AND ipmd.FUNCTION_ID = ifm.FUNCTION_ID AND ifm.FUNCTION_NAME = 'Registration Fee'");
				if(retval.toString().trim().length()!=0){
					revMgmtDTO.setRegistrationFee((Float.parseFloat(retval)));
				}else{
					revMgmtDTO.setRegistrationFee(0);
				}
				
				retval = dbutility.executeQry(sql+ ",IGRS_FUNCTION_MASTER ifm WHERE ipmd.CHALLAN_ONLINE_DATE BETWEEN TO_CHAR(TO_DATE('"+fromDate+"','dd/mm/yyyy'),'dd-mon-yyyy') and TO_CHAR(TO_DATE('"+toDate+"','dd/mm/yyyy'),'dd-mon-yyyy') " +
												"AND ipmd.FUNCTION_ID = ifm.FUNCTION_ID AND ifm.FUNCTION_NAME = 'Stamp Duty '");
				if(retval.toString().trim().length()!=0){
					revMgmtDTO.setStampDuty((Float.parseFloat(retval)));
				}else{
					revMgmtDTO.setStampDuty(0);
				}
				
				retval = dbutility.executeQry(sql+ ",IGRS_FUNCTION_MASTER ifm WHERE ipmd.CHALLAN_ONLINE_DATE BETWEEN TO_CHAR(TO_DATE('"+fromDate+"','dd/mm/yyyy'),'dd-mon-yyyy') and TO_CHAR(TO_DATE('"+toDate+"','dd/mm/yyyy'),'dd-mon-yyyy') " +
												"AND ipmd.FUNCTION_ID = ifm.FUNCTION_ID AND ifm.FUNCTION_NAME = 'Refunds'");
				if(retval.toString().trim().length()!=0){
					revMgmtDTO.setRefunds((Float.parseFloat(retval)));
				}else{
					revMgmtDTO.setRefunds(0);
				}
				retval = dbutility.executeQry(sql+ ",IGRS_FUNCTION_MASTER ifm WHERE ipmd.CHALLAN_ONLINE_DATE BETWEEN TO_CHAR(TO_DATE('"+fromDate+"','dd/mm/yyyy'),'dd-mon-yyyy') and TO_CHAR(TO_DATE('"+toDate+"','dd/mm/yyyy'),'dd-mon-yyyy') " +
												"AND ipmd.FUNCTION_ID = ifm.FUNCTION_ID AND ifm.FUNCTION_NAME = 'Stamp Cases'");
				if(retval.toString().trim().length()!=0){
					revMgmtDTO.setStampCases((Float.parseFloat(retval)));
				}else{
					revMgmtDTO.setStampCases(0);
				}
				retval = dbutility.executeQry(sql+ ",IGRS_FUNCTION_MASTER ifm WHERE ipmd.CHALLAN_ONLINE_DATE BETWEEN TO_CHAR(TO_DATE('"+fromDate+"','dd/mm/yyyy'),'dd-mon-yyyy') and TO_CHAR(TO_DATE('"+toDate+"','dd/mm/yyyy'),'dd-mon-yyyy') " +
												"AND ipmd.FUNCTION_ID = ifm.FUNCTION_ID AND ifm.FUNCTION_NAME = 'RRC'");
				if(retval.toString().trim().length()!=0){
					revMgmtDTO.setRrc((Float.parseFloat(retval)));
				}else{
					revMgmtDTO.setRrc(0);
				}
        	}
        	if(radio.equalsIgnoreCase("month")){
	        	if(officeTypeId.equalsIgnoreCase("SRO")){	        		
	        		retval = dbutility.executeQry(sql+ ",IGRS_OFFICE_MASTER iom "+
	        				"WHERE TRIM(TO_CHAR(CHALLAN_ONLINE_DATE,'MONTH')) = '"+month+"' " +
	        				"AND ipmd.DISTRICT_ID = '"+districtId+"' "+
	        				"AND iom.DISTRICT_ID = ipmd.DISTRICT_ID "+
	        				"AND ipmd.OFFICE_ID = '"+officeId+"' "+
	        				"AND iom.OFFICE_ID = ipmd.OFFICE_ID");	   
	        		if(retval.length() == 0) retval="0";
	        		revMgmtDTO.setCashAtSRO(Float.parseFloat(retval));
	        	}
	        	else{					
	        		retval = dbutility.executeQry(sql+ ",IGRS_OFFICE_MASTER iom "+
	        				"WHERE TRIM(TO_CHAR(CHALLAN_ONLINE_DATE,'MONTH')) = '"+month+"' " +
	        				"AND ipmd.DISTRICT_ID = '"+districtId+"' "+
	        				"AND iom.DISTRICT_ID = ipmd.DISTRICT_ID "+
	        				"AND ipmd.OFFICE_ID = '"+officeId+"' "+
	        				"AND iom.OFFICE_ID = ipmd.OFFICE_ID");	   
	        		if(retval.length() == 0) retval="0";
	        		revMgmtDTO.setCashAtDRO(Float.parseFloat(retval));
	        	}
        		/*retval = dbutility.executeQry(sql+ ",IGRS_PAYMENT_TYPE_MASTER iptm WHERE TRIM(TO_CHAR(CHALLAN_ONLINE_DATE,'MONTH')) = '"+month+"' " +
												"AND iptm.PAYMENT_TYPE_NAME = 'Online' AND iptm.PAYMENT_TYPE_ID = ipmd.PAYMENT_TYPE_ID"); 
				revMgmtDTO.setOnlinePayments((Float.parseFloat(retval)));*/
				//revMgmtDTO.setCreditLimitPayments((Float.parseFloat((String)ret.get(3))));
				retval = dbutility.executeQry(sql+ ",IGRS_PAYMENT_TYPE_MASTER iptm WHERE TRIM(TO_CHAR(CHALLAN_ONLINE_DATE,'MONTH')) = '"+month+"' " +
												"AND iptm.PAYMENT_TYPE_NAME = 'Challan' AND iptm.PAYMENT_TYPE_ID = ipmd.PAYMENT_TYPE_ID");				
				if(retval.length() == 0) retval="0";
				revMgmtDTO.setChallanPayments((Float.parseFloat(retval)));
				
				retval = dbutility.executeQry(sql+ ",IGRS_FUNCTION_MASTER ifm WHERE TRIM(TO_CHAR(CHALLAN_ONLINE_DATE,'MONTH')) = '"+month+"' " +
												"AND ipmd.FUNCTION_ID = ifm.FUNCTION_ID AND ifm.FUNCTION_NAME = 'Registration Fee'");
				if(retval.toString().trim().length()!=0){
					revMgmtDTO.setRegistrationFee((Float.parseFloat(retval)));
				}else{
					revMgmtDTO.setRegistrationFee(0);
				}
				
				retval = dbutility.executeQry(sql+ ",IGRS_FUNCTION_MASTER ifm WHERE TRIM(TO_CHAR(CHALLAN_ONLINE_DATE,'MONTH')) = '"+month+"' " +
												"AND ipmd.FUNCTION_ID = ifm.FUNCTION_ID AND ifm.FUNCTION_NAME = 'Stamp Duty '");
				if(retval.toString().trim().length()!=0){
					revMgmtDTO.setStampDuty((Float.parseFloat(retval)));
				}else{
					revMgmtDTO.setStampDuty(0);
				}
				
				
				retval = dbutility.executeQry(sql+ ",IGRS_FUNCTION_MASTER ifm WHERE TRIM(TO_CHAR(CHALLAN_ONLINE_DATE,'MONTH')) = '"+month+"' " +
												"AND ipmd.FUNCTION_ID = ifm.FUNCTION_ID AND ifm.FUNCTION_NAME = 'Refunds'");
				if(retval.toString().trim().length()!=0){
					revMgmtDTO.setRefunds((Float.parseFloat(retval)));
				}else{
					revMgmtDTO.setRefunds(0);
				}
				
				retval = dbutility.executeQry(sql+ ",IGRS_FUNCTION_MASTER ifm WHERE TRIM(TO_CHAR(CHALLAN_ONLINE_DATE,'MONTH')) = '"+month+"' " +
												"AND ipmd.FUNCTION_ID = ifm.FUNCTION_ID AND ifm.FUNCTION_NAME = 'Stamp Cases'");
				if(retval.toString().trim().length()!=0){
					revMgmtDTO.setStampCases((Float.parseFloat(retval)));
				}else{
					revMgmtDTO.setStampCases(0);
				}
				
				retval = dbutility.executeQry(sql+ ",IGRS_FUNCTION_MASTER ifm WHERE TRIM(TO_CHAR(CHALLAN_ONLINE_DATE,'MONTH')) = '"+month+"' " +
												"AND ipmd.FUNCTION_ID = ifm.FUNCTION_ID AND ifm.FUNCTION_NAME = 'RRC'");
				if(retval.toString().trim().length()!=0){
					revMgmtDTO.setRrc((Float.parseFloat(retval)));
				}else{
					revMgmtDTO.setRrc(0);
				}
				
        	}
        	ret.add(revMgmtDTO);
        }
        catch(Exception e){
        	e.printStackTrace();
        	logger.error(e);
        }
        finally
        {
        	try{
        		dbutility.closeConnection();	
        	}
        	catch(Exception e){
        		e.getStackTrace();
        		logger.error(e);        		
        	}
        }        
       
        return ret; 
	}	
	/**
     * ===========================================================================
     * Method         :   insertServiceFeeDetails()
     * Description    :   Inserting all values into IGRS_SERVICE_FEE_PARAM_MASTER table. 
     * Arguments      :   RevenueMgmtForm revenueMgmtForm 
     * return type    :   void
     * Author         :   Sreelatha M
     * Created Date   :   Mar 18,2008
     * ===========================================================================
	 * @param userId 
	 * @param funId 
	 * @param roleId 
     */
	/*private String paramIDGenerator() 
	 {
		String id = "PARAM" + new Date().getTime();
		return id;
	 }*/
	public boolean insertServiceFeeDetails(RevenueMgmtForm revenueMgmtForm, String roleId, String funId, String userId)
	                     throws Exception,SQLException{
		String paramName = revenueMgmtForm.getRevenueMgmtDTO().getParamName();
		String paramDesc = revenueMgmtForm.getRevenueMgmtDTO().getParamDescription();
		boolean returnflag = false;
		String seqnumber="";
			
		try{
			dbutility = new DBUtility();
			igrsCommon = new IGRSCommon();
			Date date=new Date();  
	        Format yearformat= new SimpleDateFormat("yyyy");   
	        Format monthformat= new SimpleDateFormat("MM");  
	        Format dateformat= new SimpleDateFormat("dd"); 
	        String dfmt=  dateformat.format(date);
	        String yfmt=  yearformat.format(date);
	        String mfmt=  monthformat.format(date);    
	        String SQL =  CommonSQL.PARAM_ID_GENERATION;
	        dbutility.createStatement();	        
	        String number= dbutility.executeQry(SQL);
	        seqnumber = "PARAM"+dfmt+mfmt+yfmt+number;

	        logger.debug("Wipro in IGRSCommon - getState() after excuteQuery"+seqnumber); 

        	String sql = CommonSQL.INSERT_SERVICE_FEE_PARAM;
        	
        	String[] feeDetails = new String[5];
        	feeDetails[0] = seqnumber;
        	feeDetails[1] = paramName;
        	feeDetails[2] = "A";
        	feeDetails[3] = userId;
        	//feeDetails[4] = "ADMIN";
        	feeDetails[4] = paramDesc;
        	revenueMgmtForm.getRevenueMgmtDTO().setParamId(feeDetails[0]);
        	dbutility.createPreparedStatement(sql);
        	returnflag = dbutility.executeUpdate(feeDetails);
        	if(returnflag)
        	{
        	dbutility.commit();
        	igrsCommon.saveLogDet("IGRS_SERVICE_FEE_PARAM_MASTER","INSERT","T",funId,userId,roleId);
        	}
        	else
        	{
        	dbutility.rollback();
        	igrsCommon.saveLogDet("IGRS_SERVICE_FEE_PARAM_MASTER","INSERT","F",funId,userId,roleId);
        	}
		}
		catch(Exception e){
        	e.printStackTrace();
        	logger.error(e);
        	dbutility.rollback();
        	igrsCommon.saveLogDet("IGRS_SERVICE_FEE_PARAM_MASTER","INSERT","F",funId,userId,roleId);
        }
        finally
        {
        	try{
        		dbutility.closeConnection();	
        	}
        	catch(Exception e){
        		e.getStackTrace();
        		logger.error(e);       		
        	}
        }        
       
        return returnflag;
	}
	/**
     * ===========================================================================
     * Method         :   getServiceFeeDetails()
     * Description    :   Retrieving  all values from IGRS_SERVICE_FEE_PARAM_MASTER table. 
     * Arguments      :   RevenueMgmtForm revenueMgmtForm 
     * return type    :   ArrayList
     * Author         :   Sreelatha M
     * Created Date   :   Mar 18,2008
     * ===========================================================================
     */
	public ArrayList getServiceFeeDetails(RevenueMgmtForm revenueMgmtForm){
		String paramId = revenueMgmtForm.getRevenueMgmtDTO().getParamId();
		String paramStatus = revenueMgmtForm.getRevenueMgmtDTO().getParamStatus();
		
		String fromDate = revenueMgmtForm.getFromDate();
	    String toDate = revenueMgmtForm.getToDate();
	    
		String radio = revenueMgmtForm.getRadiodate();
	
	    
		ArrayList ret = new ArrayList();
		try{
			dbutility = new DBUtility();
			
			String sql = CommonSQL.VIEW_SERVICE_FEE_DETAILS;
        	
        	dbutility.createStatement();
        	if(radio.equalsIgnoreCase("date")){
            	ret = dbutility.executeQuery(sql+" WHERE PARAM_ID = '"+paramId+"' ");  
        	}
        	if(radio.equalsIgnoreCase("week")){
        		ret = dbutility.executeQuery(sql+" WHERE CREATED_DATE BETWEEN TO_DATE('"+fromDate+"','DD/MM/YYYY') AND " +
        				"TO_DATE('"+toDate+"','DD/MM/YYYY')");
        	}
        	if(radio.equalsIgnoreCase("month")){
        		ret = dbutility.executeQuery(sql+" WHERE PARAM_STATUS = '"+paramStatus+"' ");
        	}   	
		
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e);		}
		finally{
			try{
				dbutility.closeConnection();
			}
			catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
			
		}
		return ret;		
	}
	/**
     * ===========================================================================
     * Method         :   getServiceFeeDetails()
     * Description    :   Retrieving  all values from IGRS_SERVICE_FEE_PARAM_MASTER table. 
     * Arguments      :   RevenueMgmtForm revenueMgmtForm 
     * return type    :   ArrayList
     * Author         :   Sreelatha M
     * Created Date   :   Mar 18,2008
     * ===========================================================================
     */
	public ArrayList getParamIdDetails(String paramId){
		   
		ArrayList ret = new ArrayList();
		try{
			dbutility = new DBUtility();
		
			String sql = CommonSQL.VIEW_SERVICE_FEE_DETAILS;
        	
        	dbutility.createStatement();
        	ret = dbutility.executeQuery(sql+" WHERE PARAM_ID = '"+paramId+"' ");          	   	
		
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		finally{
			try{
				dbutility.closeConnection();
			}
			catch (Exception e) {
				e.printStackTrace();
				
			}
			
		}
		return ret;		
	}
	/**
     * ===========================================================================
     * Method         :   updateServiceFeeDetails()
     * Description    :   Retrieving  all values from IGRS_SERVICE_FEE_PARAM_MASTER table. 
     * Arguments      :   RevenueMgmtForm revenueMgmtForm 
     * return type    :   ArrayList
     * Author         :   Sreelatha M
     * Created Date   :   Mar 18,2008
     * ===========================================================================
	 * @param userId 
	 * @param funId 
	 * @param roleId 
     */
	public boolean updateServiceFeeDetails(RevenueMgmtForm revenueMgmtForm, String roleId, String funId, String userId){
		
		String paramId = revenueMgmtForm.getRevenueMgmtDTO().getParamId();
		String paramName = revenueMgmtForm.getRevenueMgmtDTO().getParamName();
		String paramDesc = revenueMgmtForm.getRevenueMgmtDTO().getParamDescription();
		String paramStatus = revenueMgmtForm.getRevenueMgmtDTO().getParamStatus();
		
		boolean boo = false;
		String param[] = new String[4];
		param[0] = paramName;
		param[1] = paramDesc;
		param[2] = paramStatus;
		param[3] = paramId;
		
		try{
			dbutility = new DBUtility();
		
			String sql = CommonSQL.UPDATE_SERVICE_FEE_DETAILS;
        
        	dbutility.createPreparedStatement(sql); 

        	boo = dbutility.executeUpdate(param);
        	
            if (boo){            	
            	dbutility.commit();
            	igrsCommon.saveLogDet("IGRS_SERVICE_FEE_PARAM_MASTER","UPDATE","T",funId,userId,roleId);
             }    
            else
            {
            dbutility.rollback();
            igrsCommon.saveLogDet("IGRS_SERVICE_FEE_PARAM_MASTER","UPDATE","F",funId,userId,roleId);
		}
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		finally{
			try{
				dbutility.closeConnection();
			}
			catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}			
		}
		return boo;
	}
	
	public ArrayList getServiceParamList() throws Exception {
		ArrayList list = null;
		try {
			dbutility = new DBUtility();
			String query = CommonSQL.VIEW_SERVICE_PARAM;
			logger.debug("the query is  in DAO   " + query);
			dbutility.createStatement();
			list = new ArrayList();
			list = dbutility.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in getServiceParamList(): " + x);

		} finally {
			try {
				dbutility.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);

			}
		}
		return list;
	}

	public boolean deleteService(String serviceId, String roleId, String funId, String userId) throws Exception {
		boolean success = true;
    	String param[] = new String[1];
    	param[0] = serviceId;
    	
    	String sql=CommonSQL.DELETE_SERVICE_MASTER; 
    try {
    	igrsCommon = new IGRSCommon();
    	dbutility = new DBUtility();
    	dbutility.createPreparedStatement(sql);
        boolean boo = dbutility.executeUpdate(param);
        if (boo){
        	dbutility.commit();
        	igrsCommon.saveLogDet("IGRS_SERVICE_MASTER","DELETE","T",funId,userId,roleId);
         }    
        else
        {	
        	dbutility.rollback();
        igrsCommon.saveLogDet("IGRS_SERVICE_MASTER","DELETE","F",funId,userId,roleId);
        }
    	} catch (Exception e) {
    		logger.error("Exception in deleteService" + e);
    	}
    	finally {
    		try {
    			dbutility.closeConnection();
    		} catch (Exception e) {
    			logger.error("Exception in closing connection" + e);
        	}
        
   	 	}

		return success;
	}
	
}