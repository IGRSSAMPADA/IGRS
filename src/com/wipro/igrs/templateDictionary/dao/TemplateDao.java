package com.wipro.igrs.templateDictionary.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.templateDictionary.bo.TemplateBo;

public class TemplateDao
{
	private  Logger logger = 
		(Logger) Logger.getLogger(TemplateDao.class);
	DBUtility dbUtil;
	public TemplateDao() throws Exception
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
		            officeRead = dbUtil.executeQuery("SELECT MODULE_ID,MODULE_NAME FROM IGRS_MODULE_MASTER");
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class   " +e);
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
		        	  logger.error("exception in calling at DAO Class   " +e);
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
		          catch (Exception e)
		          {
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
	
	public boolean tempCreate(String param1[]) throws Exception
	{
	      boolean flag = false;  
	     
	      try {
	            
	            dbUtil.createPreparedStatement("INSERT INTO IGRS_TEMPLATE_DICTIONARY(MODULE_ID,SUB_MODULE_ID,FUNCTION_ID,TEMPLATE_STATUS,TEMPLATE_NAME,TEMPLATE_CONTENT,TEMPLATE_HEADER,TEMPLATE_FOOTER,TEMPLATE_ID,CREATED_DATE)VALUES(?,?,?,?,?,?,?,?,?,trunc(SYSDATE))");
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
		             read = dbUtil.executeQuery("SELECT TEMPLATE_ID,TEMPLATE_NAME,MODULE_NAME,SUB_MODULE_NAME,FUNCTION_NAME,TEMPLATE_STATUS FROM IGRS_TEMPLATE_DICTIONARY TD,IGRS_MODULE_MASTER MM,IGRS_SUB_MODULE_MASTER SM,IGRS_FUNCTION_MASTER FM WHERE TD.MODULE_ID=MM.MODULE_ID AND TD.SUB_MODULE_ID=SM.SUB_MODULE_ID AND TD.FUNCTION_ID=FM.FUNCTION_ID");
			      }
		          catch (Exception e) {
		        	 logger.error("exception in calling at DAO Class at getAllHeads ()  " +e);
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
		 
		 
		 public  ArrayList getTemplateContent(String tempId) throws SQLException
			{                     
				          ArrayList list = new ArrayList();  
				          ArrayList read = new ArrayList();
				          try 
				          {
				            dbUtil.createStatement();      
				             read = dbUtil.executeQuery("SELECT TEMPLATE_NAME,TEMPLATE_CONTENT,TEMPLATE_HEADER,TEMPLATE_FOOTER FROM IGRS_TEMPLATE_DICTIONARY WHERE TEMPLATE_ID='"+tempId+"'");
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
		 
		 
		 public boolean templateUpdate(String param1[]) throws Exception
			{
			      boolean flag = false;  
			     
			      try {
			            
			                 dbUtil.createPreparedStatement("update IGRS_TEMPLATE_DICTIONARY set " +
			                 		"TEMPLATE_CONTENT=?,TEMPLATE_HEADER=?,TEMPLATE_FOOTER=?" +
			                 		"where TEMPLATE_ID=?");
				           
			            
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

}
