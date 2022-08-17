package com.wipro.igrs.regTime.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.regTime.bd.RegTimeingsBd;

public class RegTimeingsDao
{
	private  Logger logger = 
		(Logger) Logger.getLogger(RegTimeingsDao.class);
	DBUtility dbUtil;
	public RegTimeingsDao() throws Exception
	{
	 dbUtil=new  DBUtility();	
	}
	
	
	public  ArrayList getMoudleName() throws SQLException
	{                     
		          ArrayList officeList = new ArrayList();  
		          ArrayList officeRead = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		            //officeRead = dbUtil.executeQuery("select distinct OFFICE_NAME from IGRS_OFFICE_MASTER order by office_name");
		            officeRead = dbUtil.executeQuery("SELECT MODULE_ID,MODULE_NAME FROM IGRS_MODULE_MASTER");
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class" +e);
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
		           if(officeRead!=null) 
		          {
		              for(int i=0;i<officeRead.size();i++)  
		                {
		            	  officeList.add((ArrayList)officeRead.get(i));
		                 }
		          }         
		       return officeList;
		 }  
	
	
	public  ArrayList getSubMoudle() throws SQLException
	{                     
		          ArrayList officeList = new ArrayList();  
		          ArrayList officeRead = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		            //officeRead = dbUtil.executeQuery("select distinct OFFICE_NAME from IGRS_OFFICE_MASTER order by office_name");
		            officeRead = dbUtil.executeQuery("SELECT SUB_MODULE_ID,SUB_MODULE_NAME FROM IGRS_SUB_MODULE_MASTER");
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
		           if(officeRead!=null) 
		          {
		              for(int i=0;i<officeRead.size();i++)  
		                {
		            	  officeList.add((ArrayList)officeRead.get(i));
		                 }
		          }         
		       return officeList;
		 }  
	
	
	public  ArrayList getFunction() throws SQLException
	{                     
		          ArrayList officeList = new ArrayList();  
		          ArrayList officeRead = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		            //officeRead = dbUtil.executeQuery("select distinct OFFICE_NAME from IGRS_OFFICE_MASTER order by office_name");
		            officeRead = dbUtil.executeQuery("SELECT FUNCTION_ID,FUNCTION_NAME FROM IGRS_FUNCTION_MASTER");
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class" +e);
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
		           if(officeRead!=null) 
		          {
		              for(int i=0;i<officeRead.size();i++)  
		                {
		            	  officeList.add((ArrayList)officeRead.get(i));
		                 }
		          }         
		       return officeList;
		 }
	
	
	public boolean regCreate(String param1[]) throws Exception
	{
	      boolean flag = false;  
	     
	      try {
	            
	            dbUtil.createPreparedStatement("INSERT INTO IGRS_FN_AVAILABILITY_TIME_DTLS(MODULE_ID,SUB_MODULE_ID,FUNCTION_ID,ACCESS_START_HOUR,ACCESS_START_MINUTE,START_AM_PM,ACCESS_END_HOUR,ACCESS_END_MINUTE,END_AM_PM,STATUS_FLAG)VALUES(?,?,?,?,?,?,?,?,?,?)");
		        flag = dbUtil.executeUpdate(param1);
	          
//	            if (flag) 
//	            {
//	                  dbUtil.commit();
//	                  flag = true;
//	                
//	            }
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
	
	
	public  ArrayList getModules() throws SQLException
	{                     
		          ArrayList list = new ArrayList();  
		          ArrayList read = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		             read = dbUtil.executeQuery("SELECT MODULE_NAME,SUB_MODULE_NAME,FUNCTION_NAME,ACCESS_START_HOUR,ACCESS_START_MINUTE,START_AM_PM,ACCESS_END_HOUR,ACCESS_END_MINUTE,END_AM_PM,STATUS_FLAG FROM IGRS_FN_AVAILABILITY_TIME_DTLS FN,IGRS_MODULE_MASTER MM,IGRS_SUB_MODULE_MASTER SM,IGRS_FUNCTION_MASTER FM WHERE FN.MODULE_ID=MM.MODULE_ID AND FN.SUB_MODULE_ID=SM.SUB_MODULE_ID AND FN.FUNCTION_ID=FM.FUNCTION_ID");
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
		          if(read!=null) 
		          {
		              for(int i=0;i<read.size();i++)  
		                {
		            	list.add((ArrayList)read.get(i));
		                 }
		          }         
		       return list;
		 } 
	
	
	
	public boolean moudleUpdate(String param1[]) throws Exception
	{
	      boolean flag = false;  
	     
	      try {
	            
	                 dbUtil.createPreparedStatement("update IGRS_FN_AVAILABILITY_TIME_DTLS set " +
	                 		
	                 		"ACCESS_START_HOUR=?,ACCESS_START_MINUTE=?,START_AM_PM=?,ACCESS_END_HOUR=?,ACCESS_END_MINUTE=?,END_AM_PM=?,STATUS_FLAG=?" +
	                 		"where FUNCTION_ID=?");
		           
	            
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
	
	
	public  ArrayList getFuctionId(String function) throws SQLException
	{                     
		          ArrayList list = new ArrayList();  
		          ArrayList read = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		             read = dbUtil.executeQuery("SELECT FUNCTION_ID FROM IGRS_FUNCTION_MASTER WHERE FUNCTION_NAME='"+function+"'");
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
