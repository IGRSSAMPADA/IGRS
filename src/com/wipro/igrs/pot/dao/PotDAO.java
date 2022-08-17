package com.wipro.igrs.pot.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;

import com.wipro.igrs.pot.bd.PotBD;
import com.wipro.igrs.pot.dto.PotDistrictDTO;
import com.wipro.igrs.pot.dto.potDTO;
import com.wipro.igrs.pot.form.PotFORM;

import com.wipro.igrs.pot.sql.CommonSQL;
import com.wipro.igrs.suppleDoc.dto.SuppleDocDTO;

public class PotDAO
{
	
	private  Logger logger = 
		(Logger) Logger.getLogger(PotDAO.class);
	
	
	public PotDAO() throws Exception
	{
	 
	}

	String sql = null;
	/**
     * ===========================================================================
     * Method         :   getDistrictNames()
     * Description    :   To display Districts  from the igrs_district_master table. 
     * return type    :   ArrayList
     * Author         :   Gopi.C
     * Created Date   :   4 feb,2008
     * ===========================================================================
     * 
     */
	
	public  ArrayList getDistrictNames(String language) throws SQLException
	{               
		
		          ArrayList potList = new ArrayList();  
		          ArrayList potRead = new ArrayList();
		          DBUtility dbUtil = null;
		          try 
		          {
		                 dbUtil = new DBUtility();                
		            dbUtil.createStatement();   
		            if(language.equalsIgnoreCase("en"))
		            {
		            sql = CommonSQL.SELECT_DISTRICTS;
		          //  potRead = dbUtil.executeQuery("select * from igrs_district_master");  
		            potRead = dbUtil.executeQuery(sql);   
		            }
		            
		            else
		          {
		            	sql = CommonSQL.SELECT_DISTRICTS_hi;
		          
		            potRead = dbUtil.executeQuery(sql);    
		          }
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
		         if(potRead!=null) 
		          {
		              for(int i=0;i<potRead.size();i++)  
		                {
		            	  potList.add((ArrayList)potRead.get(i));
		                        	 
		                }
		          }         
		       return potList;
		 }  
	
	
	
	//
	
	
	public  ArrayList getDistrictId(String language) throws SQLException
	{                     
		          ArrayList potList = new ArrayList();  
		          ArrayList potRead = new ArrayList();
		          DBUtility dbUtil = null;
		          try 
		          {
		                 dbUtil = new DBUtility();     
		                                 
		            dbUtil.createStatement();   
		            if(language.equalsIgnoreCase("en"))
		            {
		            sql = CommonSQL.SELECT_DISTRICTS_name;
		          //  potRead = dbUtil.executeQuery("select * from igrs_district_master");  
		            potRead = dbUtil.executeQuery(sql);   
		            }
		            
		            else
		          {
		            	sql = CommonSQL.SELECT_DISTRICTS_name;
		          
		            potRead = dbUtil.executeQuery(sql);    
		          }
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
		         if(potRead!=null) 
		          {
		              for(int i=0;i<potRead.size();i++)  
		                {
		            	  potList.add((ArrayList)potRead.get(i));
		                        	 
		                }
		          }         
		       return potList;
		 }  
	
	//
	
	
	
	
	/**
     * ===========================================================================
     * Method         :   potCreate()
     * Description    :   Inserting all values into IGRS_PUBLIC_OFFICER_TOOL_TXN table. 
     * return type    :   boolean
     * Author         :   Gopi.C
     * Created Date   :   6 Feb,2008
     * ===========================================================================
     * 
     */
	
	
	public boolean potCreate(String param[]) throws Exception
	{
	      boolean flag = false;  
	      DBUtility dbUtil = null;
          try 
          {
                 dbUtil = new DBUtility();     
	             sql = CommonSQL.INSERT_POT_CREATE;
	    	  
	    	  
	            // System.out.println(sql);
	          //  dbUtil.createPreparedStatement("INSERT INTO IGRS_PUBLIC_OFFICER_TOOL_TXN(FIRST_NAME,MIDDLE_NAME,LAST_NAME,DISTRICT_ID,TRANSACTION_ID,OLD_NEW,REGISTRATION_NUMBER,ESTAMP_CODE,STAMP_FEE_IN_DOC,STAMP_FEE_REQUIRED,DOC_IMPOUNDED,PO_COMMENTS,STATUS_FLAG,DATE_OF_OBJECTION)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,'A',TRUNC(SYSDATE))");
	             dbUtil.createPreparedStatement(sql);
		           
	            flag = dbUtil.executeUpdate(param);
	           
	            if (flag) 
	            {
	                  dbUtil.commit();
	            }
	           
	            }
	      catch (Exception x)
	      {
	    	  logger.error("Exception in potCreate() :- " + x);
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
	                dbUtil.closeConnection();
	            } 
	            catch (Exception ex) 
	            {
	            	logger.error("Exception in potCreate() :-" + ex);
	            }
	        }
	        return flag;
	    }
	
	
	/**
     * ===========================================================================
     * Method         :   potCreateOld()
     * Description    :   Inserting all values into IGRS_POT_OLD_STAMP_MAPPING table. 
     * return type    :   boolean
     * Author         :   Gopi.C
     * Created Date   :   8 Feb,2008
     * ===========================================================================
     * 
     */
	
	
	public boolean potCreateOld(String param2[]) throws Exception
	{
	      boolean flag = false;  
	      DBUtility dbUtil = null;
          try 
          {
                 dbUtil = new DBUtility();     
	            sql = CommonSQL.INSERT_POT_CREATE_OLD;
	          //  dbUtil.createPreparedStatement("INSERT INTO IGRS_POT_OLD_STAMP_MAPPING(STAMP_NUMBER,STAMP_FEE,PO_TXN_ID)VALUES(?,?,?)");
	            dbUtil.createPreparedStatement(sql);
	            
	            flag = dbUtil.executeUpdate(param2);
	           
	            if (flag) 
	            {
	                  dbUtil.commit();
	            }
	            
	            }
	      catch (Exception x)
	      {
	    	  logger.error("Exception in potCreateOld() :- " + x);
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
	               dbUtil.closeConnection();
	            } 
	            catch (Exception ex) 
	            {
	            	logger.error("Exception in potCreateOld() :-" + ex);
	            }
	        }
	        return flag;
	    }
	
	
	
	/**
     * ===========================================================================
     * Method         :   potCreateOld22()
     * Description    :   Inserting all values into IGRS_PUBLIC_OFFICER_TOOL_TXN table. 
     * return type    :   boolean
     * Author         :   Gopi.C
     * Created Date   :   11 Feb,2008
     * ===========================================================================
     * 
     */
	
	public boolean potCreateOld22(String param22[]) throws Exception
	{
	      boolean flag = false;  
	      DBUtility dbUtil = null;
          try 
          {
                 dbUtil = new DBUtility();     
	    	  sql = CommonSQL.INSERT_POT_OLD;
	            //dbUtil.createPreparedStatement("INSERT INTO IGRS_PUBLIC_OFFICER_TOOL_TXN(FIRST_NAME,MIDDLE_NAME,LAST_NAME,DISTRICT_ID,TRANSACTION_ID,OLD_NEW,STAMP_FEE_IN_DOC,STAMP_FEE_REQUIRED,OLD_REG_VOLUME_NO,OLD_REG_BOOK_NO,OLD_REG_NO,DOC_IMPOUNDED,PO_COMMENTS,STATUS_FLAG,DATE_OF_OBJECTION,OLD_REG_DATE)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,'A',TRUNC(SYSDATE),TRUNC(SYSDATE))");
	    	  dbUtil.createPreparedStatement(sql);
	           
	            for(int i=0;i<param22.length;i++)
	            {
	            	//logger.error("param is *******************************"+param22[i]);
	            }
	            flag = dbUtil.executeUpdate(param22);
	           
	            if (flag) 
	            {
	                  dbUtil.commit();
	            }
	          
	            }
	      catch (Exception x)
	      {
	    	  logger.error("Exception in potCreateOld22() :- " + x);
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
	               dbUtil.closeConnection();
	            } 
	            catch (Exception ex) 
	            {
	            	logger.error("Exception in potCreateOld22() :-" + ex);
	            }
	        }
	        return flag;
	    }
	
	
	/**
     * ===========================================================================
     * Method         :   getPotList()
     * Description    :   To Display few fields from the IGRS_PUBLIC_OFFICER_TOOL_TXN table. 
     * return type    :   Arraylist
     * Author         :   Gopi.C
     * Created Date   :   13 Feb,2008
     * ===========================================================================
     * 
     */
	@SuppressWarnings("deprecation")
	public ArrayList getPotList(String[] param,String language) throws SQLException
	{
		String[] a = new String[1];
		a[0] = param[0];
			
			  ArrayList potList = new ArrayList();  
			          ArrayList potRead = new ArrayList();
			          DBUtility dbUtil = null;
			          try 
			          {
			                 dbUtil = new DBUtility();     
			        	  if(language.equalsIgnoreCase("en"))
			        	  {
			        	  sql = CommonSQL.SELECT_POT_LIST;
			        	  }
			        	  else
			        	  {
			        		  sql = CommonSQL.SELECT_POT_LIST_hi;
			        	  }
			        	//  dbUtil.createPreparedStatement("select transaction_id,old_new,first_name,TO_CHAR(to_DATE(trunc(DATE_OF_OBJECTION),'DD-MM-RRRR'),'DD-MM-RRRR'),DISTRICT_NAME from IGRS_PUBLIC_OFFICER_TOOL_TXN po,IGRS_DISTRICT_MASTER dm where po.DISTRICT_ID=dm.DISTRICT_ID and transaction_id=?");
			        	  dbUtil.createPreparedStatement(sql);
					        
			        	potRead = dbUtil.executeQuery(a);
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
			                
			             } 
			          } 
			          if(potRead!=null) 
			          {
			              for(int i=0;i<potRead.size();i++)  
			                {
			            	  potList.add((ArrayList)potRead.get(i));
			                }
			          }         
			       return potList;
		} 
	
	
	
	
	public ArrayList getPotListUp(String[] param,String language) throws SQLException
	{
		String[] a = new String[1];
		a[0] = param[0];
			
			  ArrayList potList = new ArrayList();  
			          ArrayList potRead = new ArrayList();
			          DBUtility dbUtil = null;
			          try 
			          {
			                 dbUtil = new DBUtility();     
			        	  if(language.equalsIgnoreCase("en"))
			        	  {
			        	  sql = CommonSQL.SELECT_POT_LIST_U;
			        	  }
			        	  else
			        	  {
			        		  sql = CommonSQL.SELECT_POT_LIST_hi_U;
			        	  }
			        	//  dbUtil.createPreparedStatement("select transaction_id,old_new,first_name,TO_CHAR(to_DATE(trunc(DATE_OF_OBJECTION),'DD-MM-RRRR'),'DD-MM-RRRR'),DISTRICT_NAME from IGRS_PUBLIC_OFFICER_TOOL_TXN po,IGRS_DISTRICT_MASTER dm where po.DISTRICT_ID=dm.DISTRICT_ID and transaction_id=?");
			        	  dbUtil.createPreparedStatement(sql);
					        
			        	potRead = dbUtil.executeQuery(param);
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
			                
			             } 
			          } 
			          if(potRead!=null) 
			          {
			              for(int i=0;i<potRead.size();i++)  
			                {
			            	  potList.add((ArrayList)potRead.get(i));
			                }
			          }         
			       return potList;
		} 
	
	/**
     * ===========================================================================
     * Method         :   getPotList2()
     * Description    :   Selecting  all records from igrs_public_officer_tool_txnin where between given dates . 
     * return type    :   Arraylist
     * Author         :   Gopi.C
     * Created Date   :   15 Feb,2008
     * ===========================================================================
	 * @param dis 
     * 
     */
	
	public ArrayList getPotList2(String durationFrom,String durationTo,String dis,String language) throws Exception
	{
		
					ArrayList potList = new ArrayList();  
			          ArrayList potRead = new ArrayList();
			          DBUtility dbUtil = null;
			          try 
			          {
			                 dbUtil = new DBUtility();     
			        	  
			            dbUtil.createStatement();
			            
			            if(language.equalsIgnoreCase("en")){
			            	potRead = dbUtil.executeQuery("select distinct po.transaction_id,po.old_new,po.first_name,po.LAST_NAME,TO_CHAR(to_DATE(trunc(po.DATE_OF_OBJECTION),'DD-MM-RRRR'),'DD-MM-RRRR'),(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER DM WHERE PO.DISTRICT_ID=DM.DISTRICT_ID)FROM igrs_public_officer_tool_txn po,IGRS_DISTRICT_MASTER dm WHERE po.district_id ='"+dis+"' and to_DATE(po.CREATED_DATE,'DD-MM-RRRR') between to_date('"+durationFrom+"','DD-MM-RRRR') and to_date('"+durationTo+"','DD-MM-RRRR')");
				        }else{
				        	   potRead = dbUtil.executeQuery("select distinct po.transaction_id,po.old_new,po.first_name,po.LAST_NAME,TO_CHAR(to_DATE(trunc(po.DATE_OF_OBJECTION),'DD-MM-RRRR'),'DD-MM-RRRR'),(SELECT H_DISTRICT_NAME FROM IGRS_DISTRICT_MASTER DM WHERE PO.DISTRICT_ID=DM.DISTRICT_ID)FROM igrs_public_officer_tool_txn po,IGRS_DISTRICT_MASTER dm WHERE po.district_id ='"+dis+"' and to_DATE(po.CREATED_DATE,'DD-MM-RRRR') between to_date('"+durationFrom+"','DD-MM-RRRR') and to_date('"+durationTo+"','DD-MM-RRRR')");
				          }
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
			              
			             } 
			          } 
			          if(potRead!=null) 
			          {
			              for(int i=0;i<potRead.size();i++)  
			                {
			            	  potList.add((ArrayList)potRead.get(i));
			                }
			          }         
			       return potList;
		}   
	
	public ArrayList getPotList3(String durationFrom,String durationTo, String userId,String language) throws Exception
	{
		
						ArrayList potList = new ArrayList();  
			          ArrayList potRead = new ArrayList();
			          DBUtility dbUtil = null;
			          try 
			          {
			                 dbUtil = new DBUtility();     
			        	  
			            dbUtil.createStatement();
			              	//	 potRead = dbUtil.executeQuery("select transaction_id,old_new,first_name,TO_CHAR(to_DATE(trunc(DATE_OF_OBJECTION),'DD-MM-RRRR'),'DD-MM-RRRR'),DISTRICT_NAME from igrs_public_officer_tool_txn po,IGRS_DISTRICT_MASTER dm where po.DISTRICT_ID=dm.DISTRICT_ID and DATE_OF_OBJECTION between to_date('"+data.getDurationFrom()+"','DD-MM-RRRR') and to_date('"+data.getDurationTo()+"','DD-MM-RRRR')");
			           //potRead = dbUtil.executeQuery("SELECT DISTINCT transaction_id,old_new,first_name,LAST_NAME,TO_CHAR(to_DATE(TRUNC(DATE_OF_OBJECTION),'DD-MM-RRRR'),'DD-MM-RRRR'),(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER DM WHERE PO.DISTRICT_ID=DM.DISTRICT_ID FROM igrs_public_officer_tool_txn po,IGRS_DISTRICT_MASTER dm WHERE DATE_OF_OBJECTION between to_date('"+durationFrom+"','DD-MM-RRRR') and to_date('"+durationTo+"','DD-MM-RRRR')");
			            
			            // START | Changing query to fetch details on basis of created_date | POT Tool Changes | Santosh
			           //potRead = dbUtil.executeQuery("SELECT DISTINCT transaction_id,old_new,first_name, LAST_NAME,TO_CHAR(to_DATE(TRUNC(DATE_OF_OBJECTION),'DD-MM-RRRR'),'DD-MM-RRRR'),(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER DM WHERE PO.DISTRICT_ID=DM.DISTRICT_ID)FROM igrs_public_officer_tool_txn po,IGRS_DISTRICT_MASTER dm WHERE po.created_by ='"+userId+"' and DATE_OF_OBJECTION between to_date('"+durationFrom+"','DD-MM-RRRR') and to_date('"+durationTo+"','DD-MM-RRRR')");
			           if(language.equalsIgnoreCase("en")){
			        	   potRead = dbUtil.executeQuery("select distinct po.transaction_id,po.old_new,po.first_name,po.LAST_NAME,TO_CHAR(to_DATE(trunc(po.DATE_OF_OBJECTION),'DD-MM-RRRR'),'DD-MM-RRRR'),(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER DM WHERE PO.DISTRICT_ID=DM.DISTRICT_ID)FROM igrs_public_officer_tool_txn po,IGRS_DISTRICT_MASTER dm WHERE po.created_by ='"+userId+"' and to_DATE(po.CREATED_DATE,'DD-MM-RRRR') between to_date('"+durationFrom+"','DD-MM-RRRR') and to_date('"+durationTo+"','DD-MM-RRRR')");
			           }else{
			        	   potRead = dbUtil.executeQuery("select distinct po.transaction_id,po.old_new,po.first_name,po.LAST_NAME,TO_CHAR(to_DATE(trunc(po.DATE_OF_OBJECTION),'DD-MM-RRRR'),'DD-MM-RRRR'),(SELECT H_DISTRICT_NAME FROM IGRS_DISTRICT_MASTER DM WHERE PO.DISTRICT_ID=DM.DISTRICT_ID)FROM igrs_public_officer_tool_txn po,IGRS_DISTRICT_MASTER dm WHERE po.created_by ='"+userId+"' and to_DATE(po.CREATED_DATE,'DD-MM-RRRR') between to_date('"+durationFrom+"','DD-MM-RRRR') and to_date('"+durationTo+"','DD-MM-RRRR')");
			           }
			            
			        // END | Changing query to fetch details on basis of created_date | POT Tool Changes | Santosh
			           
			           // potRead = dbUtil.executeQuery("select transaction_id,old_new,first_name,LAST_NAME,TO_CHAR(to_DATE(trunc(DATE_OF_OBJECTION),'DD-MM-RRRR'),'DD-MM-RRRR'),(SELECT DISTRICT_ID FROM IGRS_PUBLIC_OFFICER_TOOL_TXN WHERE DISTRICT_ID=DM.DISTRICT_NAME ) from igrs_public_officer_tool_txn po,IGRS_DISTRICT_MASTER dm where  DATE_OF_OBJECTION between to_date('"+durationFrom+"','DD-MM-RRRR') and to_date('"+durationTo+"','DD-MM-RRRR')");
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
			              
			             } 
			          } 
			          if(potRead!=null) 
			          {
			              for(int i=0;i<potRead.size();i++)  
			                {
			            	  potList.add((ArrayList)potRead.get(i));
			                }
			          }         
			       return potList;
		}   
	
	/**
     * ===========================================================================
     * Method         :   getPotDetails()
     * Description    :   Selecting  all records from igrs_public_officer_tool_txnin where between given dates . 
     * return type    :   Arraylist
     * Author         :   Gopi.C
     * Created Date   :   18 Feb,2008
     * ===========================================================================
     * 
     */
	
	public  ArrayList getPotDetails(String potId) throws Exception
	{                     
		          ArrayList potList = new ArrayList();  
		          ArrayList potRead = new ArrayList();
		          DBUtility dbUtil = null;
		          try 
		          {
		                 dbUtil = new DBUtility();            
		            dbUtil.createStatement();  
		          //  sql = CommonSQL.SELECT_POTDETAILS;
		            // START | Fetching FLAG Value as part of POT Tool changes to enable DR update only once | Santosh
		            potRead = dbUtil.executeQuery("SELECT FIRST_NAME, MIDDLE_NAME, LAST_NAME,DISTRICT_ID, TO_CHAR(DATE_OF_OBJECTION,'DD-MM-YYYY'), TRANSACTION_ID, REGISTRATION_NUMBER,  ESTAMP_CODE,    STAMP_FEE_IN_DOC,    STAMP_FEE_REQUIRED,    DOC_IMPOUNDED,   PO_COMMENTS,  CASE_ELIGIBILITY,  DR_COMMENTS,   STATUS_FLAG, FLAG,  DEED_UPLOAD_PATH, DEED_UPLOAD_NAME, RECOMMEND_BY_PO_FLAG, IMPOUND_BY_DR_FLAG, TO_CHAR(CREATED_DATE,'DD-MM-YYYY')  FROM igrs_public_officer_tool_txn WHERE TRANSACTION_ID='"+potId+"'");         
		         // END | Fetching FLAG Value as part of POT Tool changes to enable DR update only once | Santosh
		         
		          
		            
		         //potRead = dbUtil.executeQuery("select FIRST_NAME,MIDDLE_NAME,LAST_NAME,DISTRICT_NAME,to_char(to_date(DATE_OF_OBJECTION,'DD-MM-RRRR'),'DD-MM-RRRR'),TRANSACTION_ID,OLD_NEW,REGISTRATION_NUMBER,OLD_REG_VOLUME_NO,OLD_REG_BOOK_NO,OLD_REG_NO,ESTAMP_CODE,STAMP_FEE_IN_DOC,STAMP_FEE_REQUIRED,DOC_IMPOUNDED,PO_COMMENTS,CASE_ELIGIBILITY,DR_COMMENTS from igrs_public_officer_tool_txn po,IGRS_DISTRICT_MASTER dm where po.DISTRICT_ID=dm.DISTRICT_ID and TRANSACTION_ID='"+potId+"'");    
		         //   potRead = dbUtil.executeQuery(sql));    
			          
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getPotDetails()  " +e);
		          }
		          finally 
		          {
		             try
		             {
		            	 dbUtil.closeConnection();                                  
		              }
		             catch(Exception e){
		             
		             } 
		          } 
		        
		          if(potRead!=null) 
		          {
		              for(int i=0;i<potRead.size();i++)  
		                {
		            	  potList.add((ArrayList)potRead.get(i));
		                }
		          }         
		       return potList;
		 }  
	
	public String getDistrict(String id, String language)
	{
		String district = "";
		String sql = "";
		DBUtility dbUtil = null;
        try 
        {
               dbUtil = new DBUtility();     
			if(language.equalsIgnoreCase("en"))
			sql = "select district_name from IGRS_DISTRICT_MASTER where district_id="+id+"";
			else
				sql = "select h_district_name from IGRS_DISTRICT_MASTER where district_id="+id+"";
			
			dbUtil.createStatement();
			district = dbUtil.executeQry(sql);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
		return district;
		
	}
	
	public  ArrayList getPotPhysicalList(String txnId) throws Exception
	{                     
		          ArrayList potList = new ArrayList();  
		          ArrayList potRead = new ArrayList();
		          DBUtility dbUtil = null;
		          try 
		          {
		                 dbUtil = new DBUtility();             
		            dbUtil.createStatement();      
		            potRead = dbUtil.executeQuery("select STAMP_FEE_IN_DOC,STAMP_FEE_REQUIRED from igrs_public_officer_tool_txn where TRANSACTION_ID='"+txnId+"'");    
		            
		              
		            
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
		         
		          if(potRead!=null) 
		          {
		              for(int i=0;i<potRead.size();i++)  
		                {
		            	  potList.add((ArrayList)potRead.get(i));
		                 }
		          }         
		       return potList;
		 }  
	
	
	/**
     * ===========================================================================
     * Method         :   potUpdate()
     * Description    :   Selecting  all records from igrs_public_officer_tool_txnin where between given dates and update particular record. 
     * return type    :   boolean
     * Author         :   Gopi.C
     * Created Date   :   20 Feb,2008
     * ===========================================================================
     * 
     */
	
	
	public boolean potUpdate1(String param[]) throws Exception{
	      boolean flag = false;  
	     
	      DBUtility dbUtil = null;
          try 
          {
                 dbUtil = new DBUtility();     
	            System.out.println("In in try of actionDAO");
	                 //dbUtil.createPreparedStatement("update igrs_public_officer_tool_txn set FIRST_NAME=?,MIDDLE_NAME=?,LAST_NAME=?,REGISTRATION_NUMBER=?,ESTAMP_CODE=?,STAMP_FEE_IN_DOC=?,STAMP_FEE_REQUIRED=?,DOC_IMPOUNDED=?,OLD_REG_VOLUME_NO=?,OLD_REG_BOOK_NO=?,PO_COMMENTS=?,CASE_ELIGIBILITY=?,DR_COMMENTS=?,UPDATE_BY=?,UPDATE_DATE=TRUNC(SYSDATE) where TRANSACTION_ID=?");
	                 dbUtil.createPreparedStatement("UPDATE igrs_public_officer_tool_txn SET  PO_COMMENTS        =?, CASE_ELIGIBILITY   =?, DR_COMMENTS        =?, UPDATE_BY          =?, " +
 " UPDATE_DATE        =TRUNC(SYSDATE) "+
 " WHERE TRANSACTION_ID =? ");
			         
	            flag = dbUtil.executeUpdate(param);
	           if (flag) 
	            {
	                  dbUtil.commit();
	                  flag = true;
	            }
	          
	            }
	      catch (SQLException ex)
	      {
	    	  logger.error("Exception ) :- " + ex);
	    	// System.out.println(ex);
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
	            	//System.out.println(ex);
	            }
	        }
	        return flag;
	    }
	
	
	public boolean potUpdate2(String param[]) throws Exception{
	      boolean flag = false;  
	     
	      DBUtility dbUtil = null;
          try 
          {
                 dbUtil = new DBUtility();     
	                 dbUtil.createPreparedStatement("INSERT INTO IGRS_PUBLIC_OFFICER_TOOL_RMKS(PO_COMMENTS,POT_TXN_ID,PO_COMMENTS_BY,PO_COMMENTS_DATE)VALUES(?,?,?,TRUNC(SYSDATE))");
		           
	            
	            flag = dbUtil.executeUpdate(param);
	           if (flag) 
	            {
	                  dbUtil.commit();
	                  flag = true;
	            }
	          
	            }
	      catch (SQLException x)
	      {
	    	  logger.error("Exception ) :- " + x);
	    	  System.out.println(x);
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
	            	  System.out.println(ex);
	            }
	        }
	        return flag;
	    }
	
	
/*	public boolean potUpdate1(String param[]) throws Exception{
	      boolean flag = false;  
	     
	      try {
	            
	              //   dbUtil.createPreparedStatement("update igrs_public_officer_tool_txn set FIRST_NAME=?,MIDDLE_NAME=?,LAST_NAME=?,REGISTRATION_NUMBER=?,ESTAMP_CODE=?,STAMP_FEE_IN_DOC=?,STAMP_FEE_REQUIRED=?,DOC_IMPOUNDED=?,OLD_REG_VOLUME_NO=?,OLD_REG_BOOK_NO=?,PO_COMMENTS=?,CASE_ELIGIBILITY=?,DR_COMMENTS=?,UPDATE_BY=?,UPDATE_DATE=TRUNC(SYSDATE) where TRANSACTION_ID=?");
	                 dbUtil.createPreparedStatement("update igrs_public_officer_tool_txn set FIRST_NAME=?,MIDDLE_NAME=?,LAST_NAME=?,REGISTRATION_NUMBER=?,ESTAMP_CODE=?,STAMP_FEE_IN_DOC=?,STAMP_FEE_REQUIRED=?,DOC_IMPOUNDED=?,OLD_REG_VOLUME_NO=?,OLD_REG_BOOK_NO=?,PO_COMMENTS=?,CASE_ELIGIBILITY=?,DR_COMMENTS=?,UPDATE_BY=?,UPDATE_DATE=TRUNC(SYSDATE) where TRANSACTION_ID=?");
			           
	            
	            flag = dbUtil.executeUpdate(param);
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
	
	
	public boolean potUpdate2(String param[]) throws Exception{
	      boolean flag = false;  
	     
	      try {
	            dbUtil = new DBUtility();
	                 dbUtil.createPreparedStatement("INSERT INTO IGRS_PUBLIC_OFFICER_TOOL_RMKS(PO_COMMENTS,POT_TXN_ID,PO_COMMENTS_BY,PO_COMMENTS_DATE)VALUES(?,?,?,TRUNC(SYSDATE))");
		           
	            
	            flag = dbUtil.executeUpdate(param);
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
	*/
	
	public  ArrayList getPotComments(String txnId) throws Exception
	{                     
		          ArrayList potList = new ArrayList();  
		          ArrayList potRead = new ArrayList();
		          DBUtility dbUtil = null;
		          try 
		          {
		                 dbUtil = new DBUtility();            
		            dbUtil.createStatement();      
		            potRead = dbUtil.executeQuery("select PO_COMMENTS from IGRS_PUBLIC_OFFICER_TOOL_RMKS where POT_TXN_ID='"+txnId+"'");    
		            
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getPotComments()  " +e);
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
		         
		          if(potRead!=null) 
		          {
		              for(int i=0;i<potRead.size();i++)  
		                {
		            	  potList.add((ArrayList)potRead.get(i));
		                }
		          }         
		       return potList;
		 }  
	
	///
	
	
	public ArrayList searchRegIdDao(String RegNo)throws IOException,SQLException,Exception
    {
    	logger.debug("IN DAO searchRegIdDao");
        ArrayList list=null;
        DBUtility dbUtil = null;
        try 
        {
               dbUtil = new DBUtility();     
        	String []arr={RegNo.trim()};
        	System.out.println(arr[0].toString());
             String query = CommonSQL.GET_POT_REGID;
	        dbUtil.createPreparedStatement(query );
	        list = dbUtil.executeQuery(arr);
	        
	        
	        
	        return (list);
        }catch(IOException ie){
	    	logger.error(ie);
	    }
	    catch(SQLException se){
	    	logger.error(se);
	    }
	    catch(Exception e){
	        logger.error(e);
	    }
	    finally {
			logger.debug("using FINALLY Connection is closed");
	        dbUtil.closeConnection(); 
	    }
	    return list;
    }
	
	
	public ArrayList searchRegIdDao1(String RegNo)throws IOException,SQLException,Exception
    {
    	logger.debug("IN DAO searchRegIdDao");
        ArrayList list=null;
        DBUtility dbUtil = null;
        try 
        {
               dbUtil = new DBUtility();     
        	String []arr={RegNo.trim()};
        	System.out.println(arr[0].toString());
             String query = CommonSQL.GET_POT_REG_ID;
	        dbUtil.createPreparedStatement(query );
	        list = dbUtil.executeQuery(arr);
	        
	        
	        
	        
	        return (list);
        }catch(IOException ie){
	    	logger.error(ie);
	    }
	    catch(SQLException se){
	    	logger.error(se);
	    }
	    catch(Exception e){
	        logger.error(e);
	    }
	    finally {
			logger.debug("using FINALLY Connection is closed");
	        dbUtil.closeConnection(); 
	    }
	    return list;
    }
///
	
	
	public ArrayList getPendingApps(String userId)
	{
		ArrayList pendingAppList = new ArrayList();
		ArrayList pendingAppListFinal = new ArrayList();
		
		
		
		ArrayList list1 = new ArrayList();
		
		String[] param={userId};
		
		DBUtility dbUtil = null;
        try 
        {
               dbUtil = new DBUtility();     
					String SQL = CommonSQL.GET_POT_DASHBOARD;
					dbUtil.createPreparedStatement(SQL);
					//dbUtil.createStatement();
					
					try
					{	
						
					
						pendingAppList=dbUtil.executeQuery(param);
				            logger.debug("Wipro in potDAO - getPendingApps() after dbUtil.executeQuery(SQL)");
				           
				            pendingAppList.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return pendingAppList;
		}
	
	
	public ArrayList getPotNameDAO(String userId)throws IOException,SQLException,Exception
    {
    	logger.debug("IN DAO getPotName");
        ArrayList list=null;
        DBUtility dbUtil = null;
        try 
        {
               dbUtil = new DBUtility();     
        	String []arr={userId};
        	System.out.println(arr[0].toString());
             String query = CommonSQL.SELECT_PO_NAME;
	        dbUtil.createPreparedStatement(query );
	        list = dbUtil.executeQuery(arr);
	        
	        return (list);
        }catch(IOException ie){
	    	logger.error(ie);
	    }
	    catch(SQLException se){
	    	logger.error(se);
	    }
	    catch(Exception e){
	        logger.error(e);
	    }
	    finally {
			logger.debug("using FINALLY Connection is closed");
	        dbUtil.closeConnection(); 
	    }
	    return list;
    }



	public String getsequenceNumber() {
		String id ="";
		
		DBUtility dbUtil = null;
        try 
        {
               dbUtil = new DBUtility();     
		
		dbUtil.createStatement();
	 id = dbUtil.executeQry("select  igrs_pot_sequence.nextval from dual");
		
	
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally
	{
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return id;
	}



	public void getEstampDEtails(PotFORM formData) {
		
		DBUtility dbUtil = null;
		ArrayList list1;
		ArrayList list;
        try 
        {
               dbUtil = new DBUtility();     
			String arr[] = {formData.getEstampCode()};
			
			dbUtil.createPreparedStatement("select CREATED_BY,ESTAMP_AMOUNT,CONSMPTN_STATUS from IGRS_ESTAMP_DETLS where SOURCE_MOD_FLAG='E' and estamp_code=?");
				list = dbUtil.executeQuery(arr);
			

			if(list.size()>0)
			{
			
				ArrayList li = (ArrayList) list.get(0);
				formData.setCreatedBy((String)li.get(0));
				formData.setEstampAmountch((String)li.get(1));
				String status = (String) li.get(2);
				
			 if(status.equalsIgnoreCase("Consumed"))
				{
					formData.setStatus("Already Consumed / इ-स्टाम्प का उपयोग हो चुका है ");
					formData.setImpundCheck("N");
					formData.setCaseStat("S");
					formData.setErrorMsg("");
				}
				else if(status.equalsIgnoreCase("Impound"))
				{
					
					formData.setStatus("Impounded / जब्त हो गई");
					formData.setImpundCheck("N");
					formData.setCaseStat("S");
					formData.setErrorMsg("");
				}
				else
				{
				  formData.setStatus("Not Consumed / इ-स्टाम्प का उपयोग नहीं हुआ  है ");
				  formData.setImpundCheck("Y");
				  formData.setCaseStat("S");
				  formData.setErrorMsg("");
				}
			}
			else
			{
				formData.setCaseStat("N");
				formData.setErrorMsg("Invalid E-Stamp Code / अमान्य ई-स्टाम्प कोड");
			} 
		
		} catch (Exception e) {
		logger.debug(e.getMessage(),e);	
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
public void fetchEstampDEtails(PotFORM formData, String pagename) {
		
		DBUtility dbUtil = null;
		ArrayList list1;
		ArrayList list3;
		ArrayList list;
		String impoundStatus = null;
        try 
        {
               dbUtil = new DBUtility();     
			String arr[] = {formData.getEstampCode()};
			String arr1[] = {formData.getTransactionId()};
			// START | POT Tool Changes | santosh
			String pageName = pagename;
			if(pageName!=null && pageName!="" && pageName.equalsIgnoreCase("potUpdate")){
				dbUtil.createPreparedStatement("SELECT IMPOUND_BY_DR_FLAG FROM IGRS_PUBLIC_OFFICER_TOOL_TXN WHERE TRANSACTION_ID=?");
				list3 = dbUtil.executeQuery(arr1);
			}else{
				dbUtil.createPreparedStatement("SELECT IMPOUND_BY_DR_FLAG FROM IGRS_PUBLIC_OFFICER_TOOL_TXN WHERE ESTAMP_CODE=?");
				list3 = dbUtil.executeQuery(arr);
			}
			dbUtil.createPreparedStatement("SELECT DEACT_REQUEST_ID FROM IGRS_ESTAMP_DEACTIVATION_DETLS WHERE ESTAMP_CODE=?");
			
			 list1 = dbUtil.executeQuery(arr);
			
			dbUtil.createPreparedStatement("select CREATED_BY,ESTAMP_AMOUNT,CONSMPTN_STATUS from IGRS_ESTAMP_DETLS where SOURCE_MOD_FLAG='E' and estamp_code=?");
				list = dbUtil.executeQuery(arr);
				
			if(list.size()>0)
			{
				ArrayList li = (ArrayList) list.get(0);
				formData.setCreatedBy((String)li.get(0));
				formData.setEstampAmountch((String)li.get(1));
				String deactReqId = null;
				String status = (String) li.get(2);
				if(list3.size()>0){
					ArrayList list4 = (ArrayList) list3.get(0);
					impoundStatus = (String)list4.get(0);
				}
				if(list1.size()>0){
					ArrayList list2 = (ArrayList) list1.get(0);
					deactReqId = (String) list2.get(0);
				}
				if(null!=deactReqId && deactReqId!=""){
					formData.setStatus("Refund has been applied for this e-stamp with Refund id "+deactReqId+" / "+" इस  ई-टिकट  के  लिए  रिफंड  लागू  किया  गया  है  रिफंड आईडी  के  साथ   "+deactReqId);
					formData.setImpundCheck("N");
					formData.setCaseStat("S");
					formData.setErrorMsg("");
				}
				else if(null!=impoundStatus && impoundStatus!="" ){ 
					formData.setImpundCheck("N");
					formData.setCaseStat("S");
					formData.setErrorMsg("");
					if(impoundStatus.equalsIgnoreCase("Yes")){
						formData.setStatus("The E-stamp is already impounded by DR. / ई-स्टांप पहले से ही जिला रजिस्ट्रार द्वारा जब्त कर लिया है   "); 
					}else{
						formData.setStatus("Already Consumed / इ-स्टाम्प का उपयोग हो चुका है "); 
					}
				}
			  else if(status.equalsIgnoreCase("Consumed") ){
				  	formData.setStatus("Already Consumed / इ-स्टाम्प का उपयोग हो चुका है ");
					formData.setCaseStat("S");
					formData.setErrorMsg("");
					if(pageName!=null && pageName.equalsIgnoreCase("potUpdate")){
						formData.setImpundCheck("Y");
					}else{
						formData.setImpundCheck("N");
					}
				}
				// END | POT Tool Changes | santosh
				else
				{
				  formData.setStatus("Not Consumed / इ-स्टाम्प का उपयोग नहीं हुआ  है ");
				  formData.setImpundCheck("Y");
				  formData.setCaseStat("S");
				  formData.setErrorMsg("");
				}
			}
			else
			{
				formData.setCaseStat("N");
				formData.setErrorMsg("Invalid E-Stamp Code / अमान्य ई-स्टाम्प कोड");
			} 
		
		} catch (Exception e) {
		logger.debug(e.getMessage(),e);	
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	public boolean updateEstamp(String code,String status)
	{
		boolean boo = false;
		DBUtility dbUtil = null;
		String Remarks = null;

        try 
        {
               dbUtil = new DBUtility();     
			String arr[] ={status,code};
			dbUtil.createPreparedStatement("Update IGRS_ESTAMP_DETLS set CONSMPTN_STATUS=? where ESTAMP_CODE=?");
			
			 boo = dbUtil.executeUpdate(arr);
			
		}
		catch (Exception e) {
		e.printStackTrace();	// TODO: handle exception
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return boo;
	}



	public boolean updatePOT(PotFORM formData, String RID) {
		boolean boo = false;
		formData.setTransactionId(RID);
		DBUtility dbUtil = null;
        try 
        {
               dbUtil = new DBUtility();     
			String arr[] = new String[10];
			arr[0] = formData.getEstampCode();
			arr[1] = formData.getPoFirstName();
		
			arr[2] = formData.getPoMiddleName();
			arr[3] = formData.getPoLastName();
			arr[4] = formData.getDistrict();
			arr[5] = formData.getDateOfObjection();
			arr[6] = RID;
			arr[7] = formData.getPoComments();
			arr[8] = formData.getStampRequired();
			arr[9] = formData.getEstampAmountch();
		
			dbUtil.createPreparedStatement("INSERT into IGRS_PUBLIC_OFFICER_TOOL_TXN(ESTAMP_CODE,FIRST_NAME,MIDDLE_NAME,LAST_NAME,DISTRICT_ID,DATE_OF_OBJECTION,TRANSACTION_ID,PO_COMMENTS,STATUS_FLAG,STAMP_FEE_REQUIRED,STAMP_FEE_IN_DOC)values(?,?,?,?,?,  to_date(?,'dd-mm-yy'),?,?,'A',?,?)");
			 boo = dbUtil.executeUpdate(arr);
			
		}
		catch (Exception e) {
		e.printStackTrace();	// TODO: handle exception
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return boo;
		
	}



	public String getDistrict(String officeId) {
		String id="";
		String arr[] = {officeId};
		DBUtility dbUtil = null;
        try 
        {
               dbUtil = new DBUtility();     
			dbUtil.createPreparedStatement("select district_id from IGRS_USER_REG_DETAILS where user_id=?");
			id = dbUtil.executeQry(arr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return id;
	}
	
	public String getDistrict1(String officeId) {
		String id="";
		String arr[] = {officeId};
		DBUtility dbUtil = null;
        try 
        {
               dbUtil = new DBUtility();     
			dbUtil.createPreparedStatement("select DISTRICT_ID from IGRS_OFFICE_MASTER where OFFICE_ID=?");
			id = dbUtil.executeQry(arr);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return id;
	}



	public boolean insertRegNumberDetailsDao(PotFORM formData, String string) {
		boolean check=false;
		DBUtility dbUtil = null;
        try 
        {
               dbUtil = new DBUtility();     
			 formData.setTransactionId(string);
			//dbUtil = new DBUtility();
			String arr[] = new String[16];
			arr[0] = formData.getRegNumber();
			arr[1] = formData.getStampRequired();
			arr[2] = formData.getPoComments();
			arr[3] = formData.getPoFirstName();
			arr[4] = formData.getPoMiddleName();
			arr[5] = formData.getPoLastName();
			arr[6] = formData.getImpound();
			arr[7] = formData.getCreatedBy();
			arr[8] = formData.getDistrict();
			arr[9] = formData.getPotDTO().getDocumentPath();
			arr[10] = formData.getPotDTO().getDocumentName();
			arr[11] = string;
			arr[12] = "Y";
			arr[13] = formData.getDateOfObjection();
			arr[14] = formData.getCreatedBy();
			arr[15] = formData.getLoggedOffice();
			dbUtil.createPreparedStatement("insert into IGRS_PUBLIC_OFFICER_TOOL_TXN (REGISTRATION_NUMBER,STAMP_FEE_REQUIRED,PO_COMMENTS,FIRST_NAME,MIDDLE_NAME,LAST_NAME,CASE_ELIGIBILITY,CREATED_BY,CREATED_DATE,DISTRICT_ID,DEED_UPLOAD_PATH,DEED_UPLOAD_NAME,TRANSACTION_ID,STATUS_FLAG,DATE_OF_OBJECTION,SR,SRO)values(?,?,?,?,?,?,?,?,sysdate,?,?,?,?,?,TO_DATE(?,'DD/MM/YYYY'),?,?)");
		check = dbUtil.executeUpdate(arr);
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return check;
	}



	public boolean insertEstampDetialsDao(PotFORM formData, String string) {
		boolean check=false;
		DBUtility dbUtil = null;
        try 
        {
               dbUtil = new DBUtility();     
			formData.setTransactionId(string);
			//START | POT Tool Changes | Consume document on click of submit button | santosh
			String arr[] = new String[17];
			arr[0] = formData.getEstampCode();
			arr[1] = String.valueOf(formData.getPotDTO().getReqStampDuties());
			arr[2] = formData.getPoComments();
			arr[3] = formData.getPoFirstName();
			arr[4] = formData.getPoMiddleName();
			arr[5] = formData.getPoLastName();
			arr[6] = formData.getImpound();
			arr[7] = formData.getCreatedBy();
			arr[8] = formData.getDistrict();
			arr[9] = formData.getPotDTO().getDocumentPath();
			arr[10] = formData.getPotDTO().getDocumentName();
			arr[11] =string;
			arr[12] = "N";
			arr[13] = formData.getDateOfObjection();
			arr[14] = formData.getCreatedBy();
			arr[15] = formData.getLoggedOffice();
			arr[16] = formData.getImpound();
			dbUtil.createPreparedStatement("insert into IGRS_PUBLIC_OFFICER_TOOL_TXN (ESTAMP_CODE,STAMP_FEE_REQUIRED,PO_COMMENTS,FIRST_NAME,MIDDLE_NAME,LAST_NAME,CASE_ELIGIBILITY,CREATED_BY,CREATED_DATE,DISTRICT_ID,DEED_UPLOAD_PATH,DEED_UPLOAD_NAME,TRANSACTION_ID,STATUS_FLAG,DATE_OF_OBJECTION,SR,SRO,RECOMMEND_BY_PO_FLAG)values(?,?,?,?,?,?,?,?,sysdate,?,?,?,?,?,TO_DATE(?,'DD/MM/YYYY'),?,?,?)");
			//END | POT Tool Changes | Consume document on click of submit button | santosh
			check = dbUtil.executeUpdate(arr);
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return check;
	}



	public boolean insertPstampDetailsDao(PotFORM formData, String potDgenerator) throws Exception {
		boolean check=false;
		DBUtility dbUtil = null;
        try 
        {
        	try{
            dbUtil = new DBUtility();     
			formData.setTransactionId(potDgenerator);
			HashMap<String, potDTO> entries = formData.getPstampDetails();
			potDTO[] values = entries.values().toArray(new potDTO[]{});
		//	dbUtil = new DBUtility();
			String param1[] = new String[4];
			dbUtil.createPreparedStatement("insert into IGRS_POT_PSTAMP_DETAILS(POT_ID,PSTAMP_CODE,PSTAMP_AMOUNT,CREATED_BY,CREATED_DATE)values(?,?,?,?,sysdate)");
			for(int i =0;i<values.length;i++)
			{
				param1[0] = potDgenerator;
				param1[1] = values[i].getCodeStamps();
				param1[2] = String.valueOf(values[i].getDenominationStamps());
				param1[3] = formData.getCreatedBy();
				//param1[4] = formData.getImpound();
				
				check = dbUtil.executeUpdate(param1);
				if(!check)
				{
					break;
				}
		
			}
        	}catch(Exception e){
        		dbUtil.rollback();
				check = false;
				formData.setErrorMsg("Please enter the correct details");
				formData.setTransactionId("");
				e.printStackTrace();
        	}
			if(check)
			{
				try{
				String arr1[] = new String[15];
				arr1[0] = potDgenerator;
				arr1[1] = formData.getPstampFirstName();
				arr1[2] = formData.getPstampMiddleName();
				arr1[3] = formData.getPstampLastName();
				arr1[4] = formData.getPstampGender();
				arr1[5] = formData.getPstampAge();
				arr1[6] = formData.getPstampAddress();
				arr1[7] = formData.getPstampPincode();
				arr1[8] = formData.getPstampPhoneNo();
				arr1[9] = formData.getPstampMobileNo();
				arr1[10] = formData.getPstampEmailId();
				arr1[11] =formData.getPstampMothersName();
				arr1[12] =formData.getCreatedBy();
				arr1[13] = formData.getPstampFathersName();
				arr1[14] = formData.getPstampNoOfPersons();
				dbUtil.createPreparedStatement("insert into IGRS_POT_PSTAMP_PARTY_DETAILS (REFERENCE_ID,PARTY_FIRST_NAME_H,PARTY_MIDDLE_NAME_H,PARTY_LAST_NAME_H,PARTY_GENDER,PARTY_AGE,PARTY_ADDRESS,POSTAL_CODE,PHONE_NUMBER,MOBILE_NUMBER,EMAIL_ID,MOTHER_NAME,CREATED_BY,GUARDIAN_NAME,CREATED_DATE,NUMBER_OF_PERSONS)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?)");
				check = dbUtil.executeUpdate(arr1);
				}catch (Exception e) {
					dbUtil.rollback();
					check = false;
					//formData.setErrorMsg("Please enter the correct details");
					formData.setTransactionId("");
					e.printStackTrace();
				}
				
				
			}
			if(check)
			{
				try{
					
				String arr1[] = new String[15];
				arr1[0] = potDgenerator;
				arr1[1] = formData.getPstampFirstName1();
				arr1[2] = formData.getPstampMiddleName1();
				arr1[3] = formData.getPstampLastName1();
				arr1[4] = formData.getPstampGender1();
				arr1[5] = formData.getPstampAge1();
				arr1[6] = formData.getPstampAddress1();
				arr1[7] = formData.getPstampPincode1();
				arr1[8] = formData.getPstampPhoneNo1();
				arr1[9] = formData.getPstampMobileNo1();
				arr1[10] = formData.getPstampEmailId1();
				arr1[11] =formData.getPstampMothersName1();
				arr1[12] =formData.getCreatedBy();
				arr1[13] = formData.getPstampFathersName1();
				arr1[14] = formData.getPstampNoOfPersons1();
				dbUtil.createPreparedStatement("insert into IGRS_POT_PSTAMP_PARTY_DETAILS (REFERENCE_ID,PARTY_FIRST_NAME_H,PARTY_MIDDLE_NAME_H,PARTY_LAST_NAME_H,PARTY_GENDER,PARTY_AGE,PARTY_ADDRESS,POSTAL_CODE,PHONE_NUMBER,MOBILE_NUMBER,EMAIL_ID,MOTHER_NAME,CREATED_BY,GUARDIAN_NAME,CREATED_DATE,NUMBER_OF_PERSONS)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?)");
				check = dbUtil.executeUpdate(arr1);
				
				}catch(Exception e){
					dbUtil.rollback();
					check = false;
					//formData.setErrorMsg("Please enter the correct details");
					formData.setTransactionId("");
					e.printStackTrace();
				}
			}
			
			if(check)
			{
			//START | POT Tool Changes | Consume document on click of submit button | santosh
			try{
			String arr[] = new String[16];
			
			arr[0] = String.valueOf(formData.getPotDTO().getReqStampDuties());
			arr[1] = formData.getPoComments();
			arr[2] = formData.getPoFirstName();
			arr[3] = formData.getPoMiddleName();
			arr[4] = formData.getPoLastName();
			arr[5] = formData.getImpound();
			arr[6] = formData.getCreatedBy();
			arr[7] = formData.getDistrict();
			arr[8] = formData.getPotDTO().getDocumentPath();
			arr[9] = formData.getPotDTO().getDocumentName();
			arr[10] =potDgenerator;
			arr[11] = "N";
			arr[12] = formData.getDateOfObjection();
			arr[13] = formData.getCreatedBy();
			arr[14] = formData.getLoggedOffice();
			arr[15] = formData.getImpound();
			dbUtil.createPreparedStatement("insert into IGRS_PUBLIC_OFFICER_TOOL_TXN (STAMP_FEE_REQUIRED,PO_COMMENTS,FIRST_NAME,MIDDLE_NAME,LAST_NAME,CASE_ELIGIBILITY,CREATED_BY,CREATED_DATE,DISTRICT_ID,DEED_UPLOAD_PATH,DEED_UPLOAD_NAME,TRANSACTION_ID,STATUS_FLAG,DATE_OF_OBJECTION,SR,SRO,RECOMMEND_BY_PO_FLAG)values(?,?,?,?,?,?,?,sysdate,?,?,?,?,?,TO_DATE(?,'DD/MM/YYYY'),?,?,?)");
			//END | POT Tool Changes | Consume document on click of submit button | santosh
			check = dbUtil.executeUpdate(arr);
			}catch(Exception e){
				dbUtil.rollback();
				check = false;
				//formData.setErrorMsg("Please enter the correct details");
				formData.setTransactionId("");
				e.printStackTrace();
			}
			}	
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			check = false;
			//formData.setErrorMsg("Please enter the correct details");
			formData.setTransactionId("");
			try {
				dbUtil.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				logger.debug(e1.getMessage(),e1);
			}
			
		}
		finally
		{
			try{
				dbUtil.closeConnection();
			}catch(Exception e){
				
			}
			
		}
		
		return check;
	}



	public ArrayList getPstampDetails(String potId) {
		String id="";
		String arr[] = {potId};
		ArrayList l = null;
		DBUtility dbUtil = null;
        try 
        {
               dbUtil = new DBUtility();     
			dbUtil.createPreparedStatement("select PSTAMP_CODE,PSTAMP_AMOUNT from IGRS_POT_PSTAMP_DETAILS where POT_ID=?");
			l = dbUtil.executeQuery(arr);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return l;
		
	}


	public ArrayList getPartyPstampDetails(String potId) {
		String id="";
		String arr[] = {potId};
		ArrayList l = null;
		DBUtility dbUtil = null;
        try 
        {
               dbUtil = new DBUtility();     
			dbUtil.createPreparedStatement("select PARTY_FIRST_NAME_H,PARTY_MIDDLE_NAME_H,PARTY_LAST_NAME_H,PARTY_GENDER,PARTY_AGE,PARTY_ADDRESS,POSTAL_CODE,PHONE_NUMBER,MOBILE_NUMBER,EMAIL_ID,MOTHER_NAME,GUARDIAN_NAME,NUMBER_OF_PERSONS from IGRS_POT_PSTAMP_PARTY_DETAILS where REFERENCE_ID=?");
			l = dbUtil.executeQuery(arr);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return l;
		
	}
	
	

	public boolean potUpdate12(PotFORM data) {
		String id="";
		// START | POT Tool Changes | santosh
		String arr[] = {data.getCaseOpend(),data.getDrComments(),data.getImpound(),data.getTransactionId()};
		boolean l = false;
		DBUtility dbUtil = null;
        try 
        {
               dbUtil = new DBUtility();     
			dbUtil.createPreparedStatement("update IGRS_PUBLIC_OFFICER_TOOL_TXN set FLAG=?,DR_COMMENTS=?,UPDATE_DATE=sysdate,IMPOUND_BY_DR_FLAG=? where TRANSACTION_ID=?");
			l = dbUtil.executeUpdate(arr);
			// END | POT Tool Changes | santosh
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return l;
	}



	



}
