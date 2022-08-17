package com.wipro.igrs.regcompletion.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.regcompletion.bd.RegCompMailBD;


public class RegCompMailDAO
{
	private  Logger logger = 
		(Logger) Logger.getLogger(RegCompMailDAO.class);
	DBUtility dbUtil;
	public RegCompMailDAO() throws Exception
	{
	 dbUtil=new  DBUtility();	
	}
	
	public  ArrayList getPartyTxnId() throws SQLException
	{                     
		          ArrayList mainList = new ArrayList();  
		          ArrayList subList = new ArrayList();
		          try 
		          {
		                                  
		            dbUtil.createStatement();      
		            subList = dbUtil.executeQuery("select PROPERTY_TXN_ID from IGRS_REG_TXN_PARTY_DETAILS");                                              
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
		         if(subList!=null) 
		          {
		              for(int i=0;i<subList.size();i++)  
		                {
		            	  mainList.add((ArrayList)subList.get(i));
		                }
		          }         
		       return mainList;
		 }  
	
	
	public boolean mailSendType(String param1[]) throws Exception{
	      boolean flag = false;  
	     
	      try {
	            
	          dbUtil.createPreparedStatement("update IGRS_REG_TXN_PARTY_DETAILS set NOTIFY_MAIL=? where PROPERTY_TXN_ID=?");
		        
	           flag = dbUtil.executeUpdate(param1);
	           if (flag) 
	            {
	                  dbUtil.commit();
	                  flag = true;
	            }
	          
	            }
	      catch (SQLException x)
	      {
	    	  logger.error("Exception ) :- " + x);
	      }
	      finally
	      {
	            try 
	            {
	                dbUtil.closeConnection();

	            } 
	            catch (SQLException ex) 
	            {
	            	logger.error("Exception  :-" + ex);
	            }
	        }
	        return flag;
	    }
	
	
	
}
