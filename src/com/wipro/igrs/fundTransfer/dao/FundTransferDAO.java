package com.wipro.igrs.fundTransfer.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.fundTransfer.bd.FundTransferBD;




public class FundTransferDAO
{
	private  Logger logger = 
		(Logger) Logger.getLogger(FundTransferDAO.class);
	DBUtility dbUtil;
	public FundTransferDAO() throws Exception
	{
	 dbUtil=new  DBUtility();	
	}
	
	
	public  ArrayList getDistrict(HttpServletRequest request) throws SQLException
		{                     
			          ArrayList mainList = new ArrayList();  
			          ArrayList subList = new ArrayList();
			          try 
			          {
			                                 
			            dbUtil.createStatement();      
			            subList = dbUtil.executeQuery("select * from igrs_district_master");                                              
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
	
	
	public  ArrayList getFinYear(HttpServletRequest request) throws SQLException
	{                     
		          ArrayList mainList = new ArrayList();  
		          ArrayList subList = new ArrayList();
		          try 
		          {
		                                 
		            dbUtil.createStatement();      
		            subList = dbUtil.executeQuery("select FISCAL_YEAR_ID,FISCAL_YEAR from IGRS_FISCAL_YEAR");                                              
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getFinYear()  " +e);
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
	
	
	
	public  ArrayList getObjectFrom(HttpServletRequest request) throws SQLException
	{                     
		          ArrayList mainList = new ArrayList();  
		          ArrayList subList = new ArrayList();
		          try 
		          {
		                                 
		            dbUtil.createStatement();      
		            subList = dbUtil.executeQuery("select OBJECT_ID,OBJECT_NAME_E from IGRS_EXP_OBJECT_MASTER");                                              
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getObjectFrom()  " +e);
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
	
	
	
	public  ArrayList getObjectTo(HttpServletRequest request) throws SQLException
	{                     
		          ArrayList mainList = new ArrayList();  
		          ArrayList subList = new ArrayList();
		          try 
		          {
		                                 
		            dbUtil.createStatement();      
		            subList = dbUtil.executeQuery("select OBJECT_ID,OBJECT_NAME_E from IGRS_EXP_OBJECT_MASTER");                                              
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getObjectTo()  " +e);
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
	
	
	
	public  ArrayList getDetailedFrom(HttpServletRequest request) throws SQLException
	{                     
		          ArrayList mainList = new ArrayList();  
		          ArrayList subList = new ArrayList();
		          try 
		          {
		                                 
		            dbUtil.createStatement();      
		            subList = dbUtil.executeQuery("select DETAILED_HEAD_ID,DETAILED_HEAD_NAME_E from IGRS_EXP_DETAILED_HEAD_MASTER");                                              
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getDetailedFrom()  " +e);
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
	
	
	
	public  ArrayList getDetailedTo(HttpServletRequest request) throws SQLException
	{                     
		          ArrayList mainList = new ArrayList();  
		          ArrayList subList = new ArrayList();
		          try 
		          {
		                                 
		            dbUtil.createStatement();      
		            subList = dbUtil.executeQuery("select DETAILED_HEAD_ID,DETAILED_HEAD_NAME_E from IGRS_EXP_DETAILED_HEAD_MASTER");                                              
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getDetailedTo()  " +e);
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
	
	
	
	
	public boolean fundTransfer1(String param1[]) throws Exception
	{
	      boolean flag = false;  
	      try {
	            
	            
	            dbUtil.createPreparedStatement("INSERT INTO IGRS_EXP_FUND_TRANSFER_DTLS (DRO_OFFICE_ID,DISTRICT_ID,FISCAL_YEAR_ID,FROM_OBJECT_ID,TO_OBJECT_ID,AMOUNT_TRANSFERED,TRANSFER_STATUS,FUND_TYPE,TRASFERED_DATE)VALUES(?,?,?,?,?,?,?,?,trunc(SYSDATE))");
	            flag = dbUtil.executeUpdate(param1);
	            if (flag) 
	            {
	                  dbUtil.commit();
	            }
	            
	            }
	      catch (Exception x)
	      {
	    	  logger.error("Exception in budgetCreate() :- " + x);
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
	            	logger.error("Exception in fundTransfer1() :-" + ex);
	            }
	        }
	        return flag;
	    }
	
	public boolean fundTransfer2(String param2[]) throws Exception
	{
	      boolean flag = false;  
	      try {
	            
	            
	            dbUtil.createPreparedStatement("INSERT INTO IGRS_EXP_FUND_TRANSFER_DTLS (DRO_OFFICE_ID,DISTRICT_ID,FISCAL_YEAR_ID,FROM_DH_ID,TO_DH_ID,AMOUNT_TRANSFERED,TRANSFER_STATUS,TRASFERED_DATE)VALUES(?,?,?,?,?,?,?,trunc(SYSDATE))");
	            flag = dbUtil.executeUpdate(param2);
	            if (flag) 
	            {
	                  dbUtil.commit();
	            }
	            
	            }
	      catch (Exception x)
	      {
	    	  logger.error("Exception in fundTransfer2() :- " + x);
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
	            	logger.error("Exception in fundTransfer2() :-" + ex);
	            }
	        }
	        return flag;
	    }
	
	
	public  ArrayList getDro(HttpServletRequest request) throws SQLException
	{                     
		          ArrayList mainList = new ArrayList();  
		          ArrayList subList = new ArrayList();
		          try 
		          {
		                                 
		            dbUtil.createStatement();      
//		             subList = dbUtil.executeQuery("select  OFFICE_ID,OFFICE_NAME from IGRS_OFFICE_MASTER iom, IGRS_EXP_FUND_TRANSFER_DTLS ift" +
//							 " where iom.OFFICE_ID = ift.dro_office_id(+)" +
//							 " and office_id   not in (select  OFFICE_ID from IGRS_OFFICE_MASTER iom, IGRS_EXP_FUND_TRANSFER_DTLS ift" +
//							 " where iom.OFFICE_ID = ift.dro_office_id(+) and ift.transfer_status = 'D')");
		            
		            
		            subList = dbUtil.executeQuery("SELECT OFFICE_ID,OFFICE_NAME FROM IGRS_OFFICE_MASTER "+
 				"WHERE OFFICE_TYPE_ID = (SELECT DISTINCT OFFICE_TYPE_ID FROM IGRS_OFFICE_TYPE_MASTER WHERE OFFICE_TYPE_NAME='DRO')");

		             
		            
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getDro()  " +e);
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
	
	public  ArrayList getAllHeads() throws SQLException
	{                     
		          ArrayList list = new ArrayList();  
		          ArrayList read = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		             	read = dbUtil.executeQuery("select  DRO_OFFICE_ID,district_name,FISCAL_YEAR from IGRS_EXP_FUND_TRANSFER_DTLS fu,IGRS_DISTRICT_MASTER dm,IGRS_FISCAL_YEAR fy where fu.district_id = dm.district_id and fu.fiscal_year_id = fy.fiscal_year_id and TRANSFER_STATUS='D'");
			         
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
		            	  //System.out.println("revenueList---------------------->" + revenueList);
		                }
		          }         
		       return list;
		 } 
	
	
	public  ArrayList headDetails(String droId) throws Exception
	{                     
		          ArrayList addList = new ArrayList();  
		          ArrayList readList = new ArrayList();
		          try 
		          {
		                               
		            dbUtil.createStatement();      
		               
		             readList = dbUtil.executeQuery("SELECT office_name,TRUNC (trasfered_date), district_name, fiscal_year,FROM_OBJECT_ID,TO_OBJECT_ID,FROM_DH_ID,TO_DH_ID,AMOUNT_TRANSFERED,al1.dro_office_id FROM igrs_exp_fund_transfer_dtls al1,igrs_office_master al2,igrs_district_master al3,igrs_fiscal_year al4 WHERE al1.dro_office_id = al2.office_id AND al1.district_id = al3.district_id AND al1.fiscal_year_id = al4.fiscal_year_id AND DRO_OFFICE_ID='"+droId+"'");    
			         
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
	
	
	public boolean fundApprove1(String param2[]) throws Exception
	{
	      boolean flag = false;  
	     
	      try {
	            
	                 dbUtil.createPreparedStatement("update IGRS_EXP_FUND_TRANSFER_DTLS set " +
	                 		
	                 		"FROM_DH_ID=?,TO_DH_ID=?,AMOUNT_TRANSFERED=?,TRANSFER_STATUS=?" +
	                 		"where DRO_OFFICE_ID=?");
		           
	            
	            flag = dbUtil.executeUpdate(param2);
	          
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
	
	
	public boolean fundApprove2(String param1[]) throws Exception
	{
	      boolean flag = false;  
	     
	      try {
	            
	                 dbUtil.createPreparedStatement("update IGRS_EXP_FUND_TRANSFER_DTLS set " +
	                 		"FROM_OBJECT_ID=?,TO_OBJECT_ID=?,AMOUNT_TRANSFERED=?,TRANSFER_STATUS=?" +
	                 		"where DRO_OFFICE_ID=?");
		           
	            
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
	
	
	
	public  ArrayList fundType() throws SQLException
	{                     
		          ArrayList list = new ArrayList();  
		          ArrayList read = new ArrayList();
		          try 
		          {
		             dbUtil.createStatement();      
		           read = dbUtil.executeQuery("select  DRO_OFFICE_ID,district_name,FISCAL_YEAR from IGRS_EXP_FUND_TRANSFER_DTLS fu,IGRS_DISTRICT_MASTER dm,IGRS_FISCAL_YEAR fy where fu.district_id = dm.district_id and fu.fiscal_year_id = fy.fiscal_year_id and FUND_TYPE='DH'");                                              
		               }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at fundType ()  " +e);
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
		            	  //System.out.println("revenueList---------------------->" + revenueList);
		                }
		          }         
		       return list;
		 } 
	
	
	
	public  ArrayList viewDroDH() throws SQLException
	{                     
		          ArrayList list = new ArrayList();  
		          ArrayList read = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		             read = dbUtil.executeQuery("select  DRO_OFFICE_ID,district_name,FISCAL_YEAR from IGRS_EXP_FUND_TRANSFER_DTLS fu,IGRS_DISTRICT_MASTER dm,IGRS_FISCAL_YEAR fy where fu.district_id = dm.district_id and fu.fiscal_year_id = fy.fiscal_year_id and FUND_TYPE='DH'");
		            
		               }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at viewDroDH ()  " +e);
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
	
	
	
	public  ArrayList getHeadList(String droId) throws SQLException
	{                     
		          ArrayList list = new ArrayList();  
		          ArrayList read = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		             	read = dbUtil.executeQuery("select  DRO_OFFICE_ID,district_name,FISCAL_YEAR from IGRS_EXP_FUND_TRANSFER_DTLS fu,IGRS_DISTRICT_MASTER dm,IGRS_FISCAL_YEAR fy where fu.district_id = dm.district_id and fu.fiscal_year_id = fy.fiscal_year_id and DRO_OFFICE_ID='"+droId+"'");
			         
		               }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getHeadList ()  " +e);
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
	
	
	
	public  ArrayList droStatusDetails(String droId) throws Exception
	{                     
		          ArrayList addList = new ArrayList();  
		          ArrayList readList = new ArrayList();
		          try 
		          {
		                               
		            dbUtil.createStatement();      
		                readList = dbUtil.executeQuery("SELECT office_name,TRUNC (trasfered_date), district_name, fiscal_year,FROM_OBJECT_ID,TO_OBJECT_ID,FROM_DH_ID,TO_DH_ID,AMOUNT_TRANSFERED,TRANSFER_STATUS,al1.dro_office_id FROM igrs_exp_fund_transfer_dtls al1,igrs_office_master al2,igrs_district_master al3,igrs_fiscal_year al4 WHERE al1.dro_office_id = al2.office_id AND al1.district_id = al3.district_id AND al1.fiscal_year_id = al4.fiscal_year_id AND DRO_OFFICE_ID='"+droId+"'");    
			         
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at droStatusDetails()  " +e);
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
	
	
	
	public boolean fundReqReject2(String param1[]) throws Exception
	{
	      boolean flag = false;  
	     
	      try {
	            
	                 dbUtil.createPreparedStatement("update IGRS_EXP_FUND_TRANSFER_DTLS set " +
	                 		"FROM_OBJECT_ID=?,TO_OBJECT_ID=?,AMOUNT_TRANSFERED=?,TRANSFER_STATUS=?" +
	                 		"where DRO_OFFICE_ID=?");
		           
	            
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
	
	
	public boolean fundReqReject1(String param2[]) throws Exception
	{
	      boolean flag = false;  
	     
	      try {
	            
	                 dbUtil.createPreparedStatement("update IGRS_EXP_FUND_TRANSFER_DTLS set " +
	                 		
	                 		"FROM_DH_ID=?,TO_DH_ID=?,AMOUNT_TRANSFERED=?,TRANSFER_STATUS=?" +
	                 		"where DRO_OFFICE_ID=?");
		         flag = dbUtil.executeUpdate(param2);
	          
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
	
	
	
	public  ArrayList getDroIds(HttpServletRequest request) throws SQLException
	{                     
		          ArrayList mainList = new ArrayList();  
		          ArrayList subList = new ArrayList();
		          try 
		          {
		                                 
		            dbUtil.createStatement();      
		          subList = dbUtil.executeQuery("select OFFICE_ID,OFFICE_NAME from IGRS_OFFICE_MASTER");
		                
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
	
	
	public  ArrayList getMajorHeadId(String userId) throws SQLException
	{                     
		          ArrayList majorHeadList = new ArrayList();  
		          ArrayList majorHeadRead = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		            majorHeadRead = dbUtil.executeQuery("SELECT MAJOR_HEAD_ID,MAJOR_HEAD_NAME_E FROM IGRS_EXP_MAJOR_HEAD_MASTER " +
		        	    				" WHERE MAJOR_HEAD_ID IN (SELECT MAJOR_HEAD_ID FROM IGRS_EXP_HEAD_MAPPING " +
		        	    				" WHERE DRO_ID = (SELECT OFFICE_ID FROM IGRS_EMP_OFFICE_MAPPING WHERE EMP_ID='"+userId+"'))");
			           
		          }
		          catch (Exception e) {
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
		           if(majorHeadRead!=null) 
		          {
		              for(int i=0;i<majorHeadRead.size();i++)  
		                {
		            	  majorHeadList.add((ArrayList)majorHeadRead.get(i));
		                 }
		          }         
		       return majorHeadList;
		 } 
	
	
	public  ArrayList getSubMajorHeadId(String userId,String majorId) throws SQLException
	{                     
		          ArrayList subMajorList = new ArrayList();  
		          ArrayList subMajorRead = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		         		subMajorRead = dbUtil.executeQuery("SELECT SUB_MAJOR_HEAD_ID,SUB_MAJOR_HEAD_NAME_E FROM IGRS_EXP_SUB_MAJOR_HEAD_MASTER " +
		       				"WHERE SUB_MAJOR_HEAD_ID IN (SELECT SUB_MAJOR_HEAD_ID FROM IGRS_EXP_HEAD_MAPPING " +
		       				"WHERE MAJOR_HEAD_ID='"+majorId+"' " +
		       				"AND DRO_ID = (SELECT OFFICE_ID FROM IGRS_EMP_OFFICE_MAPPING WHERE EMP_ID='"+userId+"'))");
			      
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getSubMinorHeadId()  " +e);
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
		          if(subMajorRead!=null) 
		          {
		              for(int i=0;i<subMajorRead.size();i++)  
		                {
		            	  subMajorList.add((ArrayList)subMajorRead.get(i));
		                 }
		          }         
		       return subMajorList;
		 } 
	
	
	public  ArrayList getMinorHeadId(String userId,String subMjorId,String majorId) throws SQLException
	{                     
		          ArrayList minorList = new ArrayList();  
		          ArrayList minorRead = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
			           
		          minorRead = dbUtil.executeQuery("SELECT MINOR_HEAD_ID,MINOR_HEAD_NAME_E FROM IGRS_EXP_MINOR_HEAD_MASTER " +
		        	  		"WHERE MINOR_HEAD_ID IN (SELECT MINOR_HEAD_ID FROM IGRS_EXP_HEAD_MAPPING " +
		        	  		"WHERE SUB_MAJOR_HEAD_ID='"+subMjorId+"' and MAJOR_HEAD_ID='"+majorId+"' " +
		        	  		"AND DRO_ID = (SELECT OFFICE_ID FROM IGRS_EMP_OFFICE_MAPPING WHERE EMP_ID='"+userId+"'))");                                              
		          }
		          catch (Exception e) {
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
		          if(minorRead!=null) 
		          {
		              for(int i=0;i<minorRead.size();i++)  
		                {
		            	  minorList.add((ArrayList)minorRead.get(i));
		                 }
		          }         
		       return minorList;
		 } 
	
	
		
	public  ArrayList getSchemeNames(String userId,String minorId,String subMjorId,String majorId) throws SQLException
	{                     
		          ArrayList schemeList = new ArrayList();  
		          ArrayList schmeRead = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
			          schmeRead = dbUtil.executeQuery("SELECT SCHEME_ID,SCHEME_NAME_E FROM IGRS_EXP_SCHEME_MASTER " +
                                                       "WHERE SCHEME_ID IN (SELECT SCHEME_ID FROM IGRS_EXP_HEAD_MAPPING " + 
                                                        "WHERE MINOR_HEAD_ID='"+minorId+"' and SUB_MAJOR_HEAD_ID='"+subMjorId+"' and MAJOR_HEAD_ID='"+majorId+"' " +
                                                        "AND DRO_ID = (SELECT OFFICE_ID FROM IGRS_EMP_OFFICE_MAPPING WHERE EMP_ID='"+userId+"'))");                                              
			         
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getSchemeNames()  " +e);
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
		           if(schmeRead!=null) 
		          {
		              for(int i=0;i<schmeRead.size();i++)  
		                {
		            	  schemeList.add((ArrayList)schmeRead.get(i));
		                 }
		          }         
		       return schemeList;
		 } 
	
	
	public  ArrayList getSegmentNames(String userId,String schemeId,String minorId,String subMjorId,String majorId) throws SQLException
	{                     
		          ArrayList segmentList = new ArrayList();  
		          ArrayList segmentRead = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		           segmentRead = dbUtil.executeQuery("SELECT SEGMENT_ID,SEGMENT_NAME_E FROM IGRS_EXP_SEGMENT_MASTER " +
                                                     "WHERE SEGMENT_ID IN (SELECT SEGMENT_ID FROM IGRS_EXP_HEAD_MAPPING " + 
                                                     "WHERE SCHEME_ID='"+schemeId+"' and MINOR_HEAD_ID='"+minorId+"' and SUB_MAJOR_HEAD_ID='"+subMjorId+"' and MAJOR_HEAD_ID='"+majorId+"' " +
                                                     "AND DRO_ID = (SELECT OFFICE_ID FROM IGRS_EMP_OFFICE_MAPPING WHERE EMP_ID='"+userId+"'))");                                              
				       
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getSegmentNames()  " +e);
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
		          if(segmentRead!=null) 
		          {
		              for(int i=0;i<segmentRead.size();i++)  
		                {
		            	  segmentList.add((ArrayList)segmentRead.get(i));
		            	 }
		          }         
		       return segmentList;
		 } 
	
	
	
	public  ArrayList getObjectNames(String userId,String segmentId,String schemeId,String minorId,String subMjorId,String majorId) throws SQLException
	{                     
		          ArrayList objectList = new ArrayList();  
		          ArrayList objectRead = new ArrayList();
		          try 
		          {
		             dbUtil.createStatement();      
		          objectRead = dbUtil.executeQuery("SELECT OBJECT_ID,OBJECT_NAME_E FROM IGRS_EXP_OBJECT_MASTER " +
                                            "WHERE OBJECT_ID IN (SELECT OBJECT_ID FROM IGRS_EXP_HEAD_MAPPING " + 
                                            "WHERE SEGMENT_ID='"+segmentId+"' and SCHEME_ID='"+schemeId+"' and MINOR_HEAD_ID='"+minorId+"' and SUB_MAJOR_HEAD_ID='"+subMjorId+"' and MAJOR_HEAD_ID='"+majorId+"' " +
                                            "AND DRO_ID = (SELECT OFFICE_ID FROM IGRS_EMP_OFFICE_MAPPING WHERE EMP_ID='"+userId+"'))");                                              
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getObjectNames()  " +e);
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
		          if(objectRead!=null) 
		          {
		              for(int i=0;i<objectRead.size();i++)  
		                {
		            	  objectList.add((ArrayList)objectRead.get(i));
		                }
		          }         
		       return objectList;
		 } 
	
	
	
	 public  ArrayList getDHead(String userId,String ojectId,String segmentId,String schemeId,String minorId,String subMjorId,String majorId) throws SQLException
		{                     
			          ArrayList mainList = new ArrayList();  
			          ArrayList subList = new ArrayList();
			          
			          try 
			          {
			            dbUtil.createStatement();      
			            
			         subList = dbUtil.executeQuery("SELECT DETAILED_HEAD_ID,DETAILED_HEAD_NAME_E FROM IGRS_EXP_DETAILED_HEAD_MASTER " +
                                                "WHERE DETAILED_HEAD_ID IN (SELECT DETAILED_HEAD_ID FROM IGRS_EXP_HEAD_MAPPING " + 
                                                "WHERE OBJECT_ID='"+ojectId+"' and SEGMENT_ID='"+segmentId+"' and SCHEME_ID='"+schemeId+"' and MINOR_HEAD_ID='"+minorId+"' and SUB_MAJOR_HEAD_ID='"+subMjorId+"' and MAJOR_HEAD_ID='"+majorId+"' " +
                                                "AND DRO_ID = (SELECT OFFICE_ID FROM IGRS_EMP_OFFICE_MAPPING WHERE EMP_ID='"+userId+"'))");
			          }
			          catch (Exception e) {
			        	  logger.error("exception in calling at DAO Class at getDistrictNames()  " +e);
			        	  e.printStackTrace();
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
			            	//  System.out.println("ArrayList)subList.get(i)---"+(ArrayList)subList.get(i));
			                }
			          }         
			       return mainList;
			 }
	 
	 
	 
	 public  ArrayList getDHead2(String userId,String dhId) throws SQLException
		{                     
			          ArrayList mainList = new ArrayList();  
			          ArrayList subList = new ArrayList();
			          
			          try 
			          {
			                                 
			            dbUtil.createStatement();      
			            
			         subList = dbUtil.executeQuery("SELECT DETAILED_HEAD_ID,DETAILED_HEAD_NAME_E FROM IGRS_EXP_DETAILED_HEAD_MASTER WHERE DETAILED_HEAD_ID IN (select DETAILED_HEAD_ID from igrs_exp_head_mapping where OBJECT_ID IN(select OBJECT_ID from igrs_exp_head_mapping where SEGMENT_ID in(select SEGMENT_ID from igrs_exp_head_mapping where SCHEME_ID in (select SCHEME_ID from igrs_exp_head_mapping where minor_head_id in (select minor_head_id from igrs_exp_head_mapping where sub_major_head_id in (select sub_major_head_id from igrs_exp_head_mapping where major_head_id in (select major_head_id from igrs_exp_head_mapping where dro_id='"+userId+"') and dro_id='"+userId+"') and dro_id='"+userId+"') and dro_id='"+userId+"') and dro_id='"+userId+"') and dro_id='"+userId+"') and dro_id='"+userId+"')and detailed_head_id not in '"+dhId+"'");
				         
			            
			          }
			          catch (Exception e) {
			        	  logger.error("exception in calling at DAO Class at getDHead2()  " +e);
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
	
	
		
}


