package com.wipro.igrs.empRetirement.dao;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import oracle.jdbc.OracleTypes;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.empRetirement.bd.EmpBd;

public class EmpDAO
{
	private  Logger logger = 
		(Logger) Logger.getLogger(EmpDAO.class);
	
	DBUtility dbUtil;
	public EmpDAO() throws Exception
	{
	 dbUtil=new  DBUtility();	
	}

	
	public  ArrayList empDetails(String empId) throws Exception
	{                     
		          ArrayList addList = new ArrayList();  
		          ArrayList readList = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		            //  readList = dbUtil.executeQuery("select DETAILED_HEAD_ID,OFFICE_NAME,district_name,FISCAL_YEAR,BUDGET_ALLOCATED,BUDGET_EXHAUSTED,BUDGET_BALANCE from IGRS_EXP_BUDGET_TRACK bt,IGRS_DISTRICT_MASTER dm,IGRS_FISCAL_YEAR fy,IGRS_OFFICE_MASTER om where bt.office_id = om.office_id and bt.district_id = dm.district_id and bt.fiscal_year_id = fy.fiscal_year_id AND DETAILED_HEAD_ID='"+detailedHeadId+"'");    
		            readList = dbUtil.executeQuery("select FIRST_NAME,MIDDLE_NAME,LAST_NAME,TO_CHAR(TO_DATE(trunc(DATE_OF_JOINING),'DD-MM-RRRR'),'DD-MM-RRRR'),TO_CHAR(TO_DATE(trunc(DATE_OF_BIRTH),'DD-MM-RRRR'),'DD-MM-RRRR'),GENDER from IGRS_EMP_MASTER where EMP_ID='"+empId+"'");    
		          }
		          catch (Exception e) {
		         	  logger.error("exception in calling at DAO Class  " +e);
		          }
		          finally 
		          {
		             try
		             {
		            	 dbUtil.closeConnection();                                  
		              }
		             catch(Exception e){
		             	 logger.error("Exception in Finally Block  "+ e);   
		             } 
		          } 
		         
		          if(readList!=null) 
		          {
		              for(int i=0;i<readList.size();i++)  
		                {
		            	  addList.add((ArrayList)readList.get(i));
		                }
		          }         
		       return addList;
		 }  
	
	
		
	public  boolean checkEmpId(String empId) throws Exception
	{                     
		          ArrayList addList = new ArrayList();  
		          ArrayList readList = new ArrayList();
		          boolean flag = false;
		          try 
		          {
		            dbUtil.createStatement();      
		            //  readList = dbUtil.executeQuery("select DETAILED_HEAD_ID,OFFICE_NAME,district_name,FISCAL_YEAR,BUDGET_ALLOCATED,BUDGET_EXHAUSTED,BUDGET_BALANCE from IGRS_EXP_BUDGET_TRACK bt,IGRS_DISTRICT_MASTER dm,IGRS_FISCAL_YEAR fy,IGRS_OFFICE_MASTER om where bt.office_id = om.office_id and bt.district_id = dm.district_id and bt.fiscal_year_id = fy.fiscal_year_id AND DETAILED_HEAD_ID='"+detailedHeadId+"'");    
		            readList = dbUtil.executeQuery("select FIRST_NAME,MIDDLE_NAME,LAST_NAME,TO_CHAR(TO_DATE(trunc(DATE_OF_JOINING),'DD-MM-RRRR'),'DD-MM-RRRR'),TO_CHAR(TO_DATE(trunc(DATE_OF_BIRTH),'DD-MM-RRRR'),'DD-MM-RRRR'),GENDER from IGRS_EMP_MASTER where EMP_ID='"+empId+"'");    
			       if(readList == null || readList.size()==0)
			       {
			    	   flag = true;
			    	   
			       }
			      }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class  " +e);
		          }
		          finally 
		          {
		             try
		             {
		            	 dbUtil.closeConnection();                                  
		              }
		             catch(Exception e){
		           	 logger.error("Exception in Finally Block  "+ e);   
		             } 
		          } 
		         
		          if(readList!=null) 
		          {
		              for(int i=0;i<readList.size();i++)  
		                {
		            	  addList.add((ArrayList)readList.get(i));
		                 }
		          }         
		       return flag;
		 }  
	
	
	public  ArrayList empDesg(String empId) throws Exception
	{                     
		          ArrayList addList = new ArrayList();  
		          ArrayList readList = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		            //  readList = dbUtil.executeQuery("select DETAILED_HEAD_ID,OFFICE_NAME,district_name,FISCAL_YEAR,BUDGET_ALLOCATED,BUDGET_EXHAUSTED,BUDGET_BALANCE from IGRS_EXP_BUDGET_TRACK bt,IGRS_DISTRICT_MASTER dm,IGRS_FISCAL_YEAR fy,IGRS_OFFICE_MASTER om where bt.office_id = om.office_id and bt.district_id = dm.district_id and bt.fiscal_year_id = fy.fiscal_year_id AND DETAILED_HEAD_ID='"+detailedHeadId+"'");    
		            readList = dbUtil.executeQuery("SELECT DESIG_NAME FROM IGRS_EMP_DESIGNATION_MASTER WHERE DESIG_ID IN (SELECT EMP_DESIGNATION_ID FROM IGRS_EMP_OFFICIAL_DETAILS WHERE EMP_ID='"+empId+"')");    
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class " +e);
		          }
		          finally 
		          {
		             try
		             {
		            	 dbUtil.closeConnection();                                  
		              }
		             catch(Exception e){
		            	 logger.error("Exception in Finally Block  "+ e);   
		             } 
		          } 
		         
		          if(readList!=null) 
		          {
		              for(int i=0;i<readList.size();i++)  
		                {
		            	  addList.add((ArrayList)readList.get(i));
		                }
		          }         
		       return addList;
		 }  
	
	
	
	
	
	public String getPension(String empId, String typeRetir,String date) 
	{

		IGRSCommon common = null;
		String pensionVal = null;
		try {
			common = new IGRSCommon();
			pensionVal = common.getPension(empId,typeRetir,date);
		} catch (Exception e) {
			logger.info("Wipro in RegCompDAO -Exception  ");
		}

		return pensionVal;

	}
	
	public String getGratuity(String empId,String date, String typeRetir) 
	{

		IGRSCommon common = null;
		String gratuityVal = null;
		try
		{
			common = new IGRSCommon();
			gratuityVal = common.getGratuity(empId,date,typeRetir);
		} catch (Exception e) {
			logger.info("Wipro in RegCompDAO -Exception  getRegFeeDuty()");
		}

		return gratuityVal;

	}
	
	
	public  ArrayList getGis(String empId) throws Exception
	{                     
		          ArrayList addList = new ArrayList();  
		          ArrayList readList = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		            //  readList = dbUtil.executeQuery("select DETAILED_HEAD_ID,OFFICE_NAME,district_name,FISCAL_YEAR,BUDGET_ALLOCATED,BUDGET_EXHAUSTED,BUDGET_BALANCE from IGRS_EXP_BUDGET_TRACK bt,IGRS_DISTRICT_MASTER dm,IGRS_FISCAL_YEAR fy,IGRS_OFFICE_MASTER om where bt.office_id = om.office_id and bt.district_id = dm.district_id and bt.fiscal_year_id = fy.fiscal_year_id AND DETAILED_HEAD_ID='"+detailedHeadId+"'");    
		            readList = dbUtil.executeQuery("SELECT GIS_AMT FROM IGRS_EMP_GIS_ACCUMULATED WHERE EMP_ID='"+empId+"'");    
		          }
		          catch (Exception e) {
		         	  logger.error("exception in calling at DAO Class at " +e);
		          }
		          finally 
		          {
		             try
		             {
		            	 dbUtil.closeConnection();                                  
		              }
		             catch(Exception e){
		            	 System.out.println("I am in exception"+e);
		            	 logger.error("Exception in Finally Block  "+ e);   
		             } 
		          } 
		         
		          if(readList!=null) 
		          {
		              for(int i=0;i<readList.size();i++)  
		                {
		            	  addList.add((ArrayList)readList.get(i));
		                }
		          }         
		       return addList;
		 }  
	
	public  ArrayList getPf(String empId) throws Exception
	{                     
		          ArrayList addList = new ArrayList();  
		          ArrayList readList = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		            //  readList = dbUtil.executeQuery("select DETAILED_HEAD_ID,OFFICE_NAME,district_name,FISCAL_YEAR,BUDGET_ALLOCATED,BUDGET_EXHAUSTED,BUDGET_BALANCE from IGRS_EXP_BUDGET_TRACK bt,IGRS_DISTRICT_MASTER dm,IGRS_FISCAL_YEAR fy,IGRS_OFFICE_MASTER om where bt.office_id = om.office_id and bt.district_id = dm.district_id and bt.fiscal_year_id = fy.fiscal_year_id AND DETAILED_HEAD_ID='"+detailedHeadId+"'");    
		            readList = dbUtil.executeQuery("SELECT PF_AMT FROM IGRS_EMP_PF_ACCUMULATED WHERE EMP_ID='"+empId+"'");    
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at headDetails()  " +e);
		        	  
		          }
		          finally 
		          {
		             try
		             {
		            	 dbUtil.closeConnection();                                  
		              }
		             catch(Exception e){
		             	 logger.error("Exception in Finally Block  "+ e);   
		             } 
		          } 
		         
		          if(readList!=null) 
		          {
		              for(int i=0;i<readList.size();i++)  
		                {
		            	  addList.add((ArrayList)readList.get(i));
		                }
		          }         
		       return addList;
		 }  
	
	
	
	
	
	
	public String getEarnLeave(String empId,String date) 
	{

		IGRSCommon common = null;
		String earnLeave = null;
		try {
			common = new IGRSCommon();
			earnLeave = common.getEarnLeave(empId,date);
		} catch (Exception e) {
			logger.info("Wipro in RegCompDAO -Exception  getRegFeeDuty()");
		}

		return earnLeave;

	}
	
	public String getEarnAmt(String empId,String date) 
	{

		IGRSCommon common = null;
		String earnAmt = null;
		try
		{
			common = new IGRSCommon();
			earnAmt = common.getEarnAmt(empId,date);
		} catch (Exception e) {
			logger.info("Wipro in RegCompDAO -Exception  getRegFeeDuty()");
		}

		return earnAmt;

	}
	
	
}
