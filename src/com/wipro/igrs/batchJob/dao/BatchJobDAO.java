package com.wipro.igrs.batchJob.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;


public class BatchJobDAO
{
	
	private  Logger logger = 
		(Logger) Logger.getLogger(BatchJobDAO.class);
	DBUtility dbUtil;
	public BatchJobDAO() throws Exception
	{
	 dbUtil=new  DBUtility();	
	}
	
	
	public  ArrayList getJobNames() throws SQLException
	{                     
		          ArrayList list = new ArrayList();  
		          ArrayList read = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		             read = dbUtil.executeQuery("select RUN_DATE,BATCH_JOB_NAME,APPLICATION_ERROR_MESSAGE from IGRS_BATCH_JOB_ERROR_LOG where TRUNC(RUN_DATE)=TRUNC(SYSDATE)");
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
	
	
	
	public void batchJobCallProc() 
	{

		IGRSCommon common = null;
		String earnAmt = null;
		try
		{
			common = new IGRSCommon();
			 common.batchJobCallProc();
		} catch (Exception e) {
			//logger.info("Wipro in RegCompDAO -Exception  getRegFeeDuty()");
		}

		//return earnAmt;

	}

}
