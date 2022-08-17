package com.wipro.igrs.targeThreshold.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;

import com.wipro.igrs.targeThreshold.bd.TargetBd;

public class TargetDao
{
	private  Logger logger = 
		(Logger) Logger.getLogger(TargetDao.class);
	
	DBUtility dbUtil;
	public TargetDao() throws Exception
	{
	 dbUtil=new  DBUtility();	
	}
	
	
	
	
	public  ArrayList getOfficeId(HttpServletRequest request) throws SQLException
	{                     
		          ArrayList mainList = new ArrayList();  
		          ArrayList subList = new ArrayList();
		          try 
		          {
		                                 
		            dbUtil.createStatement();      
		            
		         //   subList = dbUtil.executeQuery("select OFFICE_ID,OFFICE_NAME from IGRS_OFFICE_MASTER");
		            subList = dbUtil.executeQuery("SELECT OFFICE_ID,OFFICE_NAME FROM IGRS_OFFICE_MASTER "+
				"WHERE OFFICE_TYPE_ID = (SELECT DISTINCT OFFICE_TYPE_ID FROM IGRS_OFFICE_TYPE_MASTER WHERE OFFICE_TYPE_NAME='DRO')");
				   
		         }
		          catch (Exception e)
		          {
		            logger.error("exception in calling at DAO Class at getOfficeId()  " +e);
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
		         if(subList!=null) 
		          {
		              for(int i=0;i<subList.size();i++)  
		                {
		            	  mainList.add((ArrayList)subList.get(i));
		                }
		          }         
		       return mainList;
		 }
	
	
	
	public  ArrayList getMonthId(HttpServletRequest request,String yearId) throws SQLException
	{                     
		          ArrayList mainList = new ArrayList();  
		          ArrayList subList = new ArrayList();
		          
		          try 
		          {
		        	 dbUtil.createStatement();      
		            subList = dbUtil.executeQuery("SELECT FISCAL_MONTH_ID,FISCAL_MONTH_NAME FROM IGRS_FISCAL_MONTH_MASTER WHERE FISCAL_MONTH_ID IN (SELECT FISCAL_MONTH_ID FROM IGRS_FISCAL_YEAR_MONTH_MAP WHERE FISCAL_YEAR_ID='"+yearId+"')");
			         
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getMonthId()  " +e);
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
		         if(subList!=null) 
		          {
		              for(int i=0;i<subList.size();i++)  
		                {
		            	  mainList.add((ArrayList)subList.get(i));
		            	  logger.error("months in DAO class is " +mainList);
		                        	 
		                }
		          }         
		       return mainList;
		 }
	
	
	public  ArrayList getYear(HttpServletRequest request) throws SQLException
	{                     
		          ArrayList mainList = new ArrayList();  
		          ArrayList subList = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		            subList = dbUtil.executeQuery("select FISCAL_YEAR_ID,FISCAL_YEAR from IGRS_FISCAL_YEAR");
			         }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getDistrictNames()  " +e);
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
		         if(subList!=null) 
		          {
		              for(int i=0;i<subList.size();i++)  
		                {
		            	  mainList.add((ArrayList)subList.get(i));
		            	
		                }
		          }         
		       return mainList;
		 }
	
	
		
	public boolean targetAnnual(String param1[]) throws Exception
	{
	      boolean flag = false;  
	     try {
	            
	            
	            dbUtil.createPreparedStatement("INSERT INTO IGRS_EXP_ANNUAL_TARGET_DTLS (TARGET_ID,OFFICE_ID,FISCAL_YEAR_ID,RRC_TARGET,REVENUE_TARGET,STAMP_CASE_TARGET,PO_INSPECTION_TARGET,SRO_INSPECTION_TARGET,DRO_INSPECTION_TARGET,APPROVAL_STATUS)VALUES(?,?,?,?,?,?,?,?,?,?)");
	             flag = dbUtil.executeUpdate(param1);
	           
	            if (flag) 
	            {
	                  dbUtil.commit();
	            }
	            
	            }
	      catch (Exception x)
	      {
	    	  logger.error("Exception in targetAnnual() :- " + x);
	            dbUtil.rollback();
	      }
	      finally
	      {
	            try 
	            {	            	
	            	if(!flag)
	            	{
	            		dbUtil.rollback();
	            	}
	               //dbUtil.closeConnection();
	            } 
	            catch (Exception ex) 
	            {
	            	logger.error("Exception in targetAnnual() :-" + ex);
	            }
	        }
	        return flag;
	    }
	
	
	
	public  ArrayList monthValues(String droId,String yearId) throws Exception
	{                     
		          ArrayList addList = new ArrayList();  
		          ArrayList readList = new ArrayList();
		          try 
		          {
		        	  dbUtil = new DBUtility();                   
		            dbUtil.createStatement();      
		             // readList = dbUtil.executeQuery("select OFFICE_NAME,REVENUE_TARGET from IGRS_EXP_ANNUAL_TARGET_DTLS an,IGRS_OFFICE_MASTER om where an.OFFICE_ID = om.OFFICE_ID and FISCAL_YEAR_ID='"+yearId+"'");    
		            readList = dbUtil.executeQuery("select REVENUE_TARGET from IGRS_EXP_ANNUAL_TARGET_DTLS  where OFFICE_ID='"+droId+"' and FISCAL_YEAR_ID='"+yearId+"' and APPROVAL_STATUS='A' ");    
			            
		            
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at monthValues()  " +e);
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
	
	
	
	public boolean monthData(String param1[]) throws Exception
	{
	      boolean flag = false;  
	     
	      try {
	            
	                 dbUtil.createPreparedStatement("INSERT INTO IGRS_EXP_MONTHLY_TARGET_DTLS(TARGET_ID,OFFICE_ID,FISCAL_YEAR_ID,REVENUE_TARGET,STAMP_CASE_TARGET,RRC_TARGET,SRO_INSPECTION_TARGET,DRO_INSPECTION_TARGET,PO_INSPECTION_TARGET,APPROVAL_STATUS,APPROVED_DATE)VALUES(?,?,?,?,?,?,?,?,?,?,TO_DATE(TRUNC(SYSDATE),'DD-MM-RRRR'))");
		        flag = dbUtil.executeUpdate(param1);
	          
	            if (flag) 
	            {
	                  dbUtil.commit();
	                  flag = true;
	                
	            }
	            }
	      catch (Exception x)
	      {
	    	  logger.error("Exception ) :- " + x);
	      }
	      finally
	      {
	            try 
	            {
	                dbUtil.closeConnection();

	            } 
	            catch (Exception ex) 
	            {
	            	logger.error("Exception  :-" + ex);
	            }
	        }
	        return flag;
	    }
	
	
				
			
	
	public  ArrayList stampCase(String droId,String yearId) throws Exception
	{                     
		          ArrayList addList = new ArrayList();  
		          ArrayList readList = new ArrayList();
		          try 
		          {
		        	  dbUtil = new DBUtility();                   
		            dbUtil.createStatement();      
		            //  readList = dbUtil.executeQuery("select STAMP_CASE_TARGET from IGRS_EXP_ANNUAL_TARGET_DTLS WHERE FISCAL_YEAR_ID='"+yearId+"'");
		        	readList = dbUtil.executeQuery("select STAMP_CASE_TARGET from IGRS_EXP_ANNUAL_TARGET_DTLS WHERE OFFICE_ID='"+droId+"' and FISCAL_YEAR_ID='"+yearId+"' and APPROVAL_STATUS='A' ");    
				   
			     }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getPotPhysicalList()  " +e);
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
	
	
	
	public  ArrayList rrcTarget(String droId,String yearId) throws Exception
	{                     
		          ArrayList addList = new ArrayList();  
		          ArrayList readList = new ArrayList();
		          try 
		          {
		        	dbUtil = new DBUtility();                   
		            dbUtil.createStatement();      
		             //  readList = dbUtil.executeQuery("select RRC_TARGET from IGRS_EXP_ANNUAL_TARGET_DTLS WHERE FISCAL_YEAR_ID='"+yearId+"'");
		            readList = dbUtil.executeQuery("select RRC_TARGET from IGRS_EXP_ANNUAL_TARGET_DTLS WHERE OFFICE_ID='"+droId+"' and FISCAL_YEAR_ID='"+yearId+"' and APPROVAL_STATUS='A' ");    
			         
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at rrcTarget()  " +e);
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
	
	
	
	public  ArrayList sroTarget(String droId,String yearId) throws Exception
	{                     
		          ArrayList addList = new ArrayList();  
		          ArrayList readList = new ArrayList();
		          try 
		          {
		        	  dbUtil = new DBUtility();                   
		            dbUtil.createStatement();      
		             //   readList = dbUtil.executeQuery("select SRO_INSPECTION_TARGET from IGRS_EXP_ANNUAL_TARGET_DTLS WHERE FISCAL_YEAR_ID='"+yearId+"'");
		            readList = dbUtil.executeQuery("select SRO_INSPECTION_TARGET from IGRS_EXP_ANNUAL_TARGET_DTLS WHERE OFFICE_ID='"+droId+"' and FISCAL_YEAR_ID='"+yearId+"' and APPROVAL_STATUS='A' ");    
			      
			             
		            
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at sroTarget()  " +e);
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
	
	
	
	public  ArrayList droTarget(String droId,String yearId) throws Exception
	{                     
		          ArrayList addList = new ArrayList();  
		          ArrayList readList = new ArrayList();
		          try 
		          {
		        	  dbUtil = new DBUtility();                   
		            dbUtil.createStatement();      
		           //  readList = dbUtil.executeQuery("select DRO_INSPECTION_TARGET from IGRS_EXP_ANNUAL_TARGET_DTLS WHERE FISCAL_YEAR_ID='"+yearId+"'");
		             readList = dbUtil.executeQuery("select DRO_INSPECTION_TARGET from IGRS_EXP_ANNUAL_TARGET_DTLS WHERE OFFICE_ID='"+droId+"' and FISCAL_YEAR_ID='"+yearId+"' and APPROVAL_STATUS='A' ");    
			         
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getPotPhysicalList()  " +e);
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
	
	
	public  ArrayList poTarget(String droId,String yearId) throws Exception
	{                     
		          ArrayList addList = new ArrayList();  
		          ArrayList readList = new ArrayList();
		          try 
		          {
		        	  dbUtil = new DBUtility();                   
		            dbUtil.createStatement();      
		            // readList = dbUtil.executeQuery("select PO_INSPECTION_TARGET from IGRS_EXP_ANNUAL_TARGET_DTLS WHERE FISCAL_YEAR_ID='"+yearId+"'");
		             readList = dbUtil.executeQuery("select PO_INSPECTION_TARGET from IGRS_EXP_ANNUAL_TARGET_DTLS WHERE OFFICE_ID='"+droId+"' and FISCAL_YEAR_ID='"+yearId+"' and APPROVAL_STATUS='A' ");    
			       
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getPotPhysicalList()  " +e);
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
	
	
		
	public  ArrayList annualView() throws SQLException
	{                     
		          ArrayList list = new ArrayList();  
		          ArrayList read = new ArrayList();
		          try 
		          {
		              dbUtil.createStatement();      
		            read = dbUtil.executeQuery("select FISCAL_YEAR,OFFICE_NAME,REVENUE_TARGET,STAMP_CASE_TARGET,RRC_TARGET,SRO_INSPECTION_TARGET,DRO_INSPECTION_TARGET,PO_INSPECTION_TARGET,TARGET_ID,APPROVAL_STATUS from IGRS_EXP_ANNUAL_TARGET_DTLS bt,IGRS_FISCAL_YEAR fy,IGRS_OFFICE_MASTER om where bt.office_id = om.office_id and bt.fiscal_year_id = fy.fiscal_year_id");
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at annualView ()  " +e);
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
		          if(read!=null) 
		          {
		              for(int i=0;i<read.size();i++)  
		                {
		            	list.add((ArrayList)read.get(i));
		                }
		          }         
		       return list;
		 } 
	
	
	
	
	public  ArrayList AnnualDetails(String targetId) throws Exception
	{                     
		          ArrayList addList = new ArrayList();  
		          ArrayList readList = new ArrayList();
		          try 
		          {
		                               
		            dbUtil.createStatement();      
		             readList = dbUtil.executeQuery("select FISCAL_YEAR,OFFICE_NAME,REVENUE_TARGET,STAMP_CASE_TARGET,RRC_TARGET,SRO_INSPECTION_TARGET,DRO_INSPECTION_TARGET,PO_INSPECTION_TARGET,TARGET_ID from IGRS_EXP_ANNUAL_TARGET_DTLS bt,IGRS_FISCAL_YEAR fy,IGRS_OFFICE_MASTER om where bt.office_id = om.office_id and bt.fiscal_year_id = fy.fiscal_year_id and TARGET_ID='"+targetId+"'");    
			         
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at AnnualDetails()  " +e);
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
	
	
	public boolean fundApprove2(String param1[]) throws Exception
	{
	      boolean flag = false;  
	     
	      try {
	            
	                 dbUtil.createPreparedStatement("update IGRS_EXP_ANNUAL_TARGET_DTLS set " +
	                 		"RRC_TARGET=?,REVENUE_TARGET=?,STAMP_CASE_TARGET=?,PO_INSPECTION_TARGET=?,SRO_INSPECTION_TARGET=?,DRO_INSPECTION_TARGET=?,APPROVAL_STATUS=?,APPROVED_DATE=trunc(SYSDATE)" +
	                 		"where TARGET_ID=?");
		           
	            
	            flag = dbUtil.executeUpdate(param1);
	          
	            if (flag) 
	            {
	                  dbUtil.commit();
	                  flag = true;
	                
	            }
	       }
	      catch (Exception x)
	      {
	    	  logger.error("Exception ) :- " + x);
	      }
	      finally
	      {
	            try 
	            {
	                dbUtil.closeConnection();

	            } 
	            catch (Exception ex) 
	            {
	            	logger.error("Exception  :-" + ex);
	            }
	        }
	        return flag;
	    }
	
	
	
	public  ArrayList monthViewList() throws SQLException
	{                     
		          ArrayList list = new ArrayList();  
		          ArrayList read = new ArrayList();
		          try 
		          {
		             dbUtil.createStatement();      
			            read = dbUtil.executeQuery("select FISCAL_YEAR,OFFICE_NAME,REVENUE_TARGET,STAMP_CASE_TARGET,RRC_TARGET,SRO_INSPECTION_TARGET,DRO_INSPECTION_TARGET,PO_INSPECTION_TARGET,TARGET_ID,APPROVAL_STATUS from IGRS_EXP_MONTHLY_TARGET_DTLS bt,IGRS_FISCAL_YEAR fy,IGRS_OFFICE_MASTER om where bt.office_id = om.office_id and bt.fiscal_year_id = fy.fiscal_year_id");
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getcashDEtails ()  " +e);
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
		          if(read!=null) 
		          {
		              for(int i=0;i<read.size();i++)  
		                {
		            	list.add((ArrayList)read.get(i));
		                }
		          }         
		       return list;
		 } 
	
	
	

}
