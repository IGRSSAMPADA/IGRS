package com.wipro.igrs.budgetRequest.dao;


import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.wipro.igrs.budgetRequest.action.OfficeMaster;
import com.wipro.igrs.budgetRequest.dto.OfficeMasterDTO;

import com.wipro.igrs.db.DBUtility;




public class BudgetDAO
{
	private  Logger logger = 
		(Logger) Logger.getLogger(BudgetDAO.class);
	
	DBUtility dbUtil;
	public BudgetDAO() throws Exception 
	{
	 dbUtil=new  DBUtility();	
	}
	
	
	
	/**
     * ===========================================================================
     * Method         :   getOfficeNames()
     * Description    :   To Get All Office Names from IGRS_OFFICE_MASTER table. 
     * return type    :   ArrayList
     * Author         :   Gopi.C
     * Created Date   :   25 Mar,2008
     * ===========================================================================
     * 
     */
	
	
	public  ArrayList getOfficeNames(HttpServletRequest request) throws SQLException
	{                     
		          ArrayList officeList = new ArrayList();  
		          ArrayList officeRead = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		            //officeRead = dbUtil.executeQuery("select distinct OFFICE_NAME from IGRS_OFFICE_MASTER order by office_name");
		            officeRead = dbUtil.executeQuery("SELECT OFFICE_ID,OFFICE_NAME FROM IGRS_OFFICE_MASTER "+
		        	    				"WHERE OFFICE_TYPE_ID = (SELECT DISTINCT OFFICE_TYPE_ID FROM IGRS_OFFICE_TYPE_MASTER WHERE OFFICE_TYPE_NAME='DRO')");
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getOfficeNames()  " +e);
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
	
	
	/**
     * ===========================================================================
     * Method         :   getFiscalYear()
     * Description    :   To Get Finacial Year from IGRS_FISCAL_YEAR table. 
     * return type    :   ArrayList
     * Author         :   Gopi.C
     * Created Date   :   25 Mar,2008
     * ===========================================================================
     * 
     */
	
	
	
	public  ArrayList getFiscalYear() throws SQLException
	{                     
		          ArrayList officeList = new ArrayList();  
		          ArrayList officeRead = new ArrayList();
		          try 
		          {
		        	 // logger.error("before create statement in getFiscalYear() in DAO ");                        
		            dbUtil.createStatement();      
		            officeRead = dbUtil.executeQuery("select FISCAL_YEAR_ID,FISCAL_YEAR from IGRS_FISCAL_YEAR");
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getFiscalYear()  " +e);
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
	
	
	
	/**
     * ===========================================================================
     * Method         :   budgetCreate()
     * Description    :   To Insert Budget Request Details. . 
     * return type    :   Boolean
     * Author         :   Gopi.C
     * Created Date   :   29 Mar,2008
     * ===========================================================================
     * 
     */
	
	
	public boolean budgetCreate(String param1[]) throws Exception
	{
	      boolean flag = false;  
	      try {
	            
	    	  dbUtil = new DBUtility();
	            dbUtil.createPreparedStatement("INSERT INTO IGRS_EXP_BUDGET_REQ_DETAILS (budget_request_id,REQUEST_TYPE,OFFICE_ID,FISCAL_YEAR_ID,MAJOR_HEAD_ID,SUB_MAJOR_HEAD_ID,MINOR_HEAD_ID,SCHEME_HEAD_ID,SEGMENT_HEAD_ID,OBJECT_HEAD_ID,APPROVED_STATUS,CREATED_DATE)VALUES(?,?,?,?,?,?,?,?,?,?,?,TO_DATE(TRUNC(SYSDATE),'DD-MM-RRRR'))");
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
	            	logger.error("Exception in budgetCreate() :-" + ex);
	            }
	        }
	        return flag;
	    }
	
	
	/**
     * ===========================================================================
     * Method         :   budgetMonthReq()
     * Description    :   To Insert Budget Request Details. . 
     * return type    :   Boolean
     * Author         :   Gopi.C
     * Created Date   :   1 Apr,2008
     * ===========================================================================
     * 
     */
	
	
	public boolean budgetMonthReq(String param2[]) throws Exception
	{
	      boolean flag = false;  
	      try {
	            
	    	  dbUtil = new DBUtility();
	            dbUtil.createPreparedStatement("INSERT INTO IGRS_EXP_BUDGET_REQ_MAPPING(BUDGET_REQUEST_ID,DETAILED_HEAD_AMOUNT,REQ_FISCAL_MONTH_ID,DETAILED_HEAD_ID)VALUES(?,?,?,?)");
	             flag = dbUtil.executeUpdate(param2);
	          
	            if (flag) 
	            {
	                  dbUtil.commit();
	            }
	            
	            }
	      catch (Exception x)
	      {
	    	  logger.error("Exception in budgetMonthReq() :- " + x);
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
	            	logger.error("Exception in budgetMonthReq() :-" + ex);
	            }
	        }
	        return flag;
	    }
	
	
	/**
     * ===========================================================================
     * Method         :   getMajorHeadId()
     * Description    :   To Get Major Head Ids. 
     * return type    :   ArrayList
     * Author         :   Gopi.C
     * Created Date   :   2 Apr,2008
     * ===========================================================================
     * 
     */
	
	
	public  ArrayList getMajorHeadId(String userId) throws SQLException
	{                     
		          ArrayList majorHeadList = new ArrayList();  
		          ArrayList majorHeadRead = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		          // majorHeadRead = dbUtil.executeQuery("select MAJOR_HEAD_ID from IGRS_EXP_HEAD_MAPPING where DRO_ID='"+userId+"'");
		            majorHeadRead = dbUtil.executeQuery("SELECT MAJOR_HEAD_ID,MAJOR_HEAD_NAME_E FROM IGRS_EXP_MAJOR_HEAD_MASTER " +
		        	    				" WHERE MAJOR_HEAD_ID IN (SELECT MAJOR_HEAD_ID FROM IGRS_EXP_HEAD_MAPPING " +
		        	    				" WHERE DRO_ID = (SELECT OFFICE_ID FROM IGRS_EMP_OFFICE_MAPPING WHERE EMP_ID='"+userId+"'))");
			           
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getMajorHeadId()  " +e);
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
	
	
	
	/**
     * ===========================================================================
     * Method         :   getSubMajorHeadId()
     * Description    :   To Get Sub Major Head Ids. 
     * return type    :   ArrayList
     * Author         :   Gopi.C
     * Created Date   :   3 Apr,2008
     * ===========================================================================
     * 
     */
	
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
	
	
	/**
     * ===========================================================================
     * Method         :   getMinorHeadId()
     * Description    :   To Get Minor Head Ids. 
     * return type    :   ArrayList
     * Author         :   Gopi.C
     * Created Date   :   4 Apr,2008
     * ===========================================================================
     * 
     */
	
	public  ArrayList getMinorHeadId(String userId,String subMjorId,String majorId) throws SQLException
	{                     
		          ArrayList minorList = new ArrayList();  
		          ArrayList minorRead = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		           // select minor_head_id from igrs_exp_head_mapping where sub_major_head_id in (select sub_major_head_id from igrs_exp_head_mapping where major_head_id in (select major_head_id from igrs_exp_head_mapping where dro_id='xxx') and dro_id='xxx')
//			           
		          minorRead = dbUtil.executeQuery("SELECT MINOR_HEAD_ID,MINOR_HEAD_NAME_E FROM IGRS_EXP_MINOR_HEAD_MASTER " +
		        	  		"WHERE MINOR_HEAD_ID IN (SELECT MINOR_HEAD_ID FROM IGRS_EXP_HEAD_MAPPING " +
		        	  		"WHERE SUB_MAJOR_HEAD_ID='"+subMjorId+"' and MAJOR_HEAD_ID='"+majorId+"' " +
		        	  		"AND DRO_ID = (SELECT OFFICE_ID FROM IGRS_EMP_OFFICE_MAPPING WHERE EMP_ID='"+userId+"'))");                                              
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getMinorHeadId()  " +e);
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
	
	
	
	
	/**
     * ===========================================================================
     * Method         :   getSchemeNames()
     * Description    :   To Get All Scheme Names from IGRS_EXP_SCHEME_MASTER table. 
     * return type    :   ArrayList
     * Author         :   Gopi.C
     * Created Date   :   25 Mar,2008
     * ===========================================================================
     * 
     */
	
	
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
	
	
	
	
	/**
     * ===========================================================================
     * Method         :   getSegmentNames()
     * Description    :   To Get All Segment Names from IGRS_EXP_SEGMENT_MASTER table. 
     * return type    :   ArrayList
     * Author         :   Gopi.C
     * Created Date   :   28 Mar,2008
     * ===========================================================================
     * 
     */
	
	
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
	
	
	
	
	/**
     * ===========================================================================
     * Method         :   getObjectNames()
     * Description    :   To Get All Object Names from IGRS_EXP_OBJECT_MASTER table. 
     * return type    :   ArrayList
     * Author         :   Gopi.C
     * Created Date   :   27 Mar,2008
     * ===========================================================================
     * 
     */
	
	
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
			        	  logger.error("exception in calling at DAO Class at getDHead()  " +e);
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
	
	
	
	
	
	/**
     * ===========================================================================
     * Method         :   getBudgerRequestList1()
     * Description    :   To Display All Head Ids. 
     * return type    :   ArrayList
     * Author         :   Gopi.C
     * Created Date   :   8 Apr,2008
     * ===========================================================================
     * 
     */
	
	
	public ArrayList getBudgerRequestList1(String[] param) throws SQLException
	{
		
						ArrayList reqList = new ArrayList();  
			          ArrayList reqRead = new ArrayList();
			          try 
			          {
			          	  dbUtil.createPreparedStatement("select BUDGET_REQUEST_ID,REQUEST_TYPE,OFFICE_NAME,MAJOR_HEAD_NAME_E,SUB_MAJOR_HEAD_NAME_E,APPROVED_STATUS from IGRS_EXP_BUDGET_REQ_DETAILS BD,IGRS_OFFICE_MASTER OM,IGRS_EXP_MAJOR_HEAD_MASTER MJ,IGRS_EXP_SUB_MAJOR_HEAD_MASTER SM where BD.OFFICE_ID=OM.OFFICE_ID AND BD.MAJOR_HEAD_ID=MJ.MAJOR_HEAD_ID AND BD.SUB_MAJOR_HEAD_ID=SM.SUB_MAJOR_HEAD_ID AND BUDGET_REQUEST_ID=?");
				     	  reqRead = dbUtil.executeQuery(param);
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
			          if(reqRead!=null) 
			          {
			              for(int i=0;i<reqRead.size();i++)  
			                {
			            	  reqList.add((ArrayList)reqRead.get(i));
			            	  
			            	 
			                }
			          }         
			       return reqList;
		} 
	
	
	/**
     * ===========================================================================
     * Method         :   getBudgerRequestList2()
     * Description    :   To Display few fields from IGRS_EXP_BUDGET_REQ_DETAILS table. 
     * return type    :   ArrayList
     * Author         :   Gopi.C
     * Created Date   :   8 Apr,2008
     * ===========================================================================
     * 
     */
	
	public ArrayList getBudgerRequestList2(String durationFrom,String durationTo) throws Exception
	{
		
						ArrayList reqList2 = new ArrayList();  
			          ArrayList reqRead2 = new ArrayList();
			          try 
			          {
			        	  dbUtil = new DBUtility();
			        	  
			            dbUtil.createStatement();
			           // reqRead2 = dbUtil.executeQuery("select transaction_id,OFFICE_ID,MAJOR_HEAD_ID,SUB_MAJOR_HEAD_ID,MINOR_HEAD_ID from IGRS_EXP_BUDGET_REQ_DETAILS where DATE_OF_OBJECTION between TO_CHAR(to_date('"+data.getDurationFrom()+"','MM-DD-RRRR'),'DD-MON-RRRR') and TO_CHAR(to_date('"+data.getDurationTo()+"','MM-DD-RRRR'),'DD-MON-RRRR')");
			            reqRead2 = dbUtil.executeQuery("select BUDGET_REQUEST_ID,REQUEST_TYPE,OFFICE_NAME,MAJOR_HEAD_NAME_E,SUB_MAJOR_HEAD_NAME_E,APPROVED_STATUS from IGRS_EXP_BUDGET_REQ_DETAILS BD,IGRS_OFFICE_MASTER OM,IGRS_EXP_MAJOR_HEAD_MASTER MJ,IGRS_EXP_SUB_MAJOR_HEAD_MASTER SM where BD.OFFICE_ID=OM.OFFICE_ID AND BD.MAJOR_HEAD_ID=MJ.MAJOR_HEAD_ID AND BD.SUB_MAJOR_HEAD_ID=SM.SUB_MAJOR_HEAD_ID AND REQUEST_TYPE='Budget Request' AND BD.CREATED_DATE between TO_CHAR(to_date('"+durationFrom+"','DD-MM-RRRR'),'DD-MON-RRRR') and TO_CHAR(to_date('"+durationTo+"','DD-MM-RRRR'),'DD-MON-RRRR')");
			            
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
			          if(reqRead2!=null) 
			          {
			              for(int i=0;i<reqRead2.size();i++)  
			                {
			            	  reqList2.add((ArrayList)reqRead2.get(i));
			                }
			          }         
			       return reqList2;
		}
	
	/**
     * ===========================================================================
     * Method         :   getBudgetRequestDetails()
     * Description    :   To Display all fields from IGRS_EXP_BUDGET_REQ_DETAILS table. 
     * return type    :   ArrayList
     * Author         :   Gopi.C
     * Created Date   :   10 Apr,2008
     * ===========================================================================
     * 
     */
	
	public  ArrayList getBudgetRequestDetails(String budgetReqId) throws Exception
	{                     
		          ArrayList reqDetailsList = new ArrayList();  
		          ArrayList reqDetailsRead = new ArrayList();
		          try 
		          {
		             dbUtil.createStatement();      
		            reqDetailsRead = dbUtil.executeQuery("select BUDGET_REQUEST_ID,REQUEST_TYPE,FISCAL_YEAR,MAJOR_HEAD_NAME_E,SUB_MAJOR_HEAD_NAME_E,MINOR_HEAD_NAME_E,OFFICE_NAME,SCHEME_NAME_E,SEGMENT_NAME_E,OBJECT_NAME_E,APPROVED_STATUS from IGRS_EXP_BUDGET_REQ_DETAILS BD,IGRS_FISCAL_YEAR FY,IGRS_EXP_MAJOR_HEAD_MASTER MJ,IGRS_EXP_SUB_MAJOR_HEAD_MASTER SM,IGRS_EXP_MINOR_HEAD_MASTER MI,IGRS_OFFICE_MASTER OM,IGRS_EXP_SCHEME_MASTER SC,IGRS_EXP_SEGMENT_MASTER SG,IGRS_EXP_OBJECT_MASTER OB where BD.FISCAL_YEAR_ID=FY.FISCAL_YEAR_ID AND BD.MAJOR_HEAD_ID=MJ.MAJOR_HEAD_ID AND BD.SUB_MAJOR_HEAD_ID=SM.SUB_MAJOR_HEAD_ID AND BD.MINOR_HEAD_ID=MI.MINOR_HEAD_ID AND BD.OFFICE_ID=OM.OFFICE_ID AND BD.SCHEME_HEAD_ID=SC.SCHEME_ID AND BD.SEGMENT_HEAD_ID=SG.SEGMENT_ID AND BD.OBJECT_HEAD_ID=OB.OBJECT_ID AND BUDGET_REQUEST_ID='"+budgetReqId+"'");    
				         
		            
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getBudgetRequestDetails()  " +e);
		        	  e.printStackTrace();
		          }
		          finally 
		          {
		             try
		             {
		            	 dbUtil.closeConnection();                                  
		              }
		             catch(Exception e)
		             {
		             e.printStackTrace();
		             } 
		          } 
		        
		          if(reqDetailsRead!=null) 
		          {
		              for(int i=0;i<reqDetailsRead.size();i++)  
		                {
		            	  reqDetailsList.add((ArrayList)reqDetailsRead.get(i));
		                 }
		          }         
		       return reqDetailsList;
		 }  
	
	/**
     * ===========================================================================
     * Method         :   getBudgetReqMonthDetails()
     * Description    :   To Display Monthly records from IGRS_EXP_BUDGET_REQ_MAPPING table. 
     * return type    :   ArrayList
     * Author         :   Gopi.C
     * Created Date   :   11 Apr,2008
     * ===========================================================================
     * 
     */
	
	public  ArrayList getBudgetReqMonthDetails(String budgetReqId) throws Exception
	{                     
		          ArrayList reqMonthList = new ArrayList();  
		          ArrayList reqMonthRead = new ArrayList();
		          try 
		          {
		        	  dbUtil = new DBUtility();              
		            dbUtil.createStatement();      
		         //  reqMonthRead = dbUtil.executeQuery("select detailed_head_id,DETAILED_HEAD_AMOUNT from IGRS_EXP_BUDGET_REQ_MAPPING where BUDGET_REQUEST_ID='"+budgetReqId+"' group by DETAILED_HEAD_ID,REQ_FISCAL_MONTH_ID ,DETAILED_HEAD_AMOUNT order by DETAILED_HEAD_ID,REQ_FISCAL_MONTH_ID");    
		            reqMonthRead = dbUtil.executeQuery("select DETAILED_HEAD_NAME_E,DETAILED_HEAD_AMOUNT from IGRS_EXP_BUDGET_REQ_MAPPING MP,IGRS_EXP_DETAILED_HEAD_MASTER MS where MP.DETAILED_HEAD_ID=MS.DETAILED_HEAD_ID AND BUDGET_REQUEST_ID='"+budgetReqId+"' group by DETAILED_HEAD_NAME_E,REQ_FISCAL_MONTH_ID,DETAILED_HEAD_AMOUNT order by DETAILED_HEAD_NAME_E,REQ_FISCAL_MONTH_ID");    
			          
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class   " +e);
		        	  e.printStackTrace();
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
		        
		          if(reqMonthRead!=null) 
		          {
		              for(int i=0;i<reqMonthRead.size();i++)  
		                {
		            	  reqMonthList.add((ArrayList)reqMonthRead.get(i));
		                }
		          }         
		       return reqMonthList;
		 }
	
	
	
	public  ArrayList getDetailedHeadId(String budgetReqId) throws Exception
	{                     
		          ArrayList reqMonthList = new ArrayList();  
		          ArrayList reqMonthRead = new ArrayList();
		          try 
		          {
		        	  dbUtil = new DBUtility();             
		            dbUtil.createStatement();      
		            reqMonthRead = dbUtil.executeQuery("select distinct detailed_head_id,DETAILED_HEAD_AMOUNT from IGRS_EXP_BUDGET_REQ_MAPPING where BUDGET_REQUEST_ID='"+budgetReqId+"'");    
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
		             
		             } 
		          } 
		        
		          if(reqMonthRead!=null) 
		          {
		              for(int i=0;i<reqMonthRead.size();i++)  
		                {
		            	  reqMonthList.add((ArrayList)reqMonthRead.get(i));
		                }
		          }         
		       return reqMonthList;
		 }
	
	
	
	/**
     * ===========================================================================
     * Method         :   budgetMonthReqUpdate()
     * Description    :   To Update Monthly records from IGRS_EXP_BUDGET_REQ_MAPPING table. 
     * return type    :   ArrayList
     * Author         :   Gopi.C
     * Created Date   :   11 Apr,2008
     * ===========================================================================
     * 
     */
	
	public boolean budgetMonthReqUpdate(String param2[]) throws Exception
	{
	      boolean flag = false;  
	     
	      try {
	    	  
	    	  dbUtil = new DBUtility();
	    	          dbUtil.createPreparedStatement("update igrs_exp_budget_req_mapping set DETAILED_HEAD_AMOUNT=? where REQ_FISCAL_MONTH_ID=? and DETAILED_HEAD_ID=? and BUDGET_REQUEST_ID=?");
		           
	            
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
	               // dbUtil.closeConnection();

	            } 
	            catch (Exception ex) 
	            {
	            	logger.error("Exception  :-" + ex);
	            }
	        }
	        return flag;
	    }
	
	
	
 public boolean budgetApporve(String param5[]) throws Exception
	{
	      boolean flag = false;  
	       try {
	            
	            dbUtil = new DBUtility();
	            dbUtil.createPreparedStatement("update IGRS_EXP_BUDGET_REQ_DETAILS set APPROVED_STATUS=?,APPROVED_DATE=TO_DATE(TRUNC(SYSDATE),'DD-MM-RRRR') where BUDGET_REQUEST_ID=?");
	            flag = dbUtil.executeUpdate(param5);
	          
	            if (flag) 
	            {
	                  dbUtil.commit();
	            }
	            
	            }
	      catch (Exception x)
	      {
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
	                logger.error("Exception in budgetCreate() :-" + ex);
	            }
	        }
	        return flag;
	    }


 public  ArrayList getMonthId(String yearId) throws SQLException
	{                
	 
		          ArrayList mainList = new ArrayList();  
		          ArrayList subList = new ArrayList();
		          
		          try 
		          {
		        	  dbUtil = new DBUtility();                 
		            dbUtil.createStatement();      
		            
		            subList = dbUtil.executeQuery("SELECT FISCAL_MONTH_ID FROM IGRS_FISCAL_MONTH_MASTER WHERE FISCAL_MONTH_ID IN (select FISCAL_MONTH_ID from IGRS_FISCAL_YEAR_MONTH_MAP where FISCAL_YEAR_ID='"+yearId+"')");
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
		                  }
		          }         
		       return mainList;
		 }
 
 
 
 public  ArrayList getMonthIdApprove(String year) throws SQLException
	{                
	 
		          ArrayList mainList = new ArrayList();  
		          ArrayList subList = new ArrayList();
		          
		          try 
		          {
		        	  dbUtil = new DBUtility();                 
		            dbUtil.createStatement();      
		            
		            subList = dbUtil.executeQuery("SELECT  FISCAL_MONTH_ID FROM IGRS_FISCAL_YEAR_MONTH_MAP WHERE FISCAL_YEAR_ID IN(SELECT FISCAL_YEAR_ID FROM IGRS_FISCAL_YEAR WHERE FISCAL_YEAR='"+year+"') ORDER BY FISCAL_MONTH_ID");
			       }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getMonthIdApprove()  " +e);
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

 			

 		public  ArrayList getDetailedHead(String DHead) throws SQLException
 		{                     
		          ArrayList officeList = new ArrayList();  
		          ArrayList officeRead = new ArrayList();
		          try 
		          {
		        	 // logger.error("before create statement in getFiscalYear() in DAO ");                        
		            dbUtil.createStatement();      
		            officeRead = dbUtil.executeQuery("SELECT DETAILED_HEAD_ID FROM IGRS_EXP_DETAILED_HEAD_MASTER WHERE DETAILED_HEAD_NAME_E='"+DHead+"'");
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getFiscalYear()  " +e);
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


	
	
	
	

}
