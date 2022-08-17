package com.wipro.igrs.receiptBudget.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.receiptBudget.action.ReceiptBudgetApprove;


public class BudgetDAO
{
	private  Logger logger = 
		(Logger) Logger.getLogger(BudgetDAO.class);
	DBUtility dbUtil;
	public BudgetDAO() throws Exception 
	{
	 dbUtil=new  DBUtility();	
	}
	
	public  ArrayList getOfficeNames(HttpServletRequest request) throws SQLException
	{                     
		          ArrayList officeList = new ArrayList();  
		          ArrayList officeRead = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
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
	
	

	
	
	public boolean budgetMonthReq(String param2[]) throws Exception
	{
	      boolean flag = false;  
	      try {
	            
	    	  dbUtil = new DBUtility();
	            dbUtil.createPreparedStatement("INSERT INTO IGRS_RCPT_BUDGET_REQ_MAPPING(BUDGET_RECEIPT_ID,DETAILED_HEAD_AMOUNT,MONTH_RECEIPT,DETAIILED_HEAD_ID)VALUES(?,?,?,?)");
	            flag = dbUtil.executeUpdate(param2);
	            
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
	
	
	
	
	
	public boolean receiptBudgetCreate(String param1[]) throws Exception
	{
	      boolean flag = false;  
	      try {
	            
	    	  dbUtil = new DBUtility();
	            dbUtil.createPreparedStatement("INSERT INTO IGRS_RCPT_BUDGET_REQ_DETAILS (BUDGET_RECEIPT_ID,OFFICE_ID,FISCAL_YEAR_ID,MAJOR_HEAD_ID,SUB_MAJOR_HEAD_ID,MINOR_HEAD_ID,SCHEME_HEAD_ID,SEGMENT_HEAD_ID,OBJECT_HEAD_ID,APPROVED_STATUS,CREATED_DATE)VALUES(?,?,?,?,?,?,?,?,?,?,TO_DATE(TRUNC(SYSDATE),'DD-MM-RRRR'))");
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
	

	
	
	
	public  ArrayList getFiscalYear(HttpServletRequest request) throws SQLException
	{                     
		          ArrayList officeList = new ArrayList();  
		          ArrayList officeRead = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		            //officeRead = dbUtil.executeQuery("select distinct OFFICE_NAME from IGRS_OFFICE_MASTER order by office_name");
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
	
	
	
//	public  ArrayList getBudgetReqMonthDetails(String budgetReqId) throws Exception
//	{                     
//		          ArrayList reqMonthList = new ArrayList();  
//		          ArrayList reqMonthRead = new ArrayList();
//		          try 
//		          {
//		                              
//		            dbUtil.createStatement();      
//		           // potRead = dbUtil.executeQuery("select det.BUDGET_REQUEST_ID,MAJOR_HEAD_ID,SUB_MAJOR_HEAD_ID,MINOR_HEAD_ID,MONTH_REQUESTED from IGRS_EXP_BUDGET_REQ_DETAILS det,IGRS_EXP_BUDGET_REQ_MAPPING map where det.BUDGET_REQUEST_ID=map.BUDGET_REQUEST_ID AND det.BUDGET_REQUEST_ID='"+budgetReqId+"'");
//		            reqMonthRead = dbUtil.executeQuery("select DETAILED_HEAD_AMOUNT from IGRS_RCPT_BUDGET_REQ_MAPPING where BUDGET_RECEIPT_ID='"+budgetReqId+"' order by MONTH_RECEIPT");    
//			          
//		            
//		          }
//		          catch (Exception e) {
//		            System.out.println("exception in calling at DAO Class   " +e);
//		          }
//		          finally 
//		          {
//		             try
//		              {
//		            	 dbUtil.closeConnection();                                  
//		              }
//		             catch(Exception e){
//		             
//		           } 
//		          } 
//		        
//		          if(reqMonthRead!=null) 
//		          {
//		              for(int i=0;i<reqMonthRead.size();i++)  
//		                {
//		            	  reqMonthList.add((ArrayList)reqMonthRead.get(i));
//		                }
//		          }         
//		       return reqMonthList;
//		 }
	
	
//	public boolean budgetMonthReqUpdate(String param2[]) throws Exception
//	{
//	      boolean flag = false;  
//	     
//	      try {
//	    	  System.out.println("Array Length---->  "+param2.length);
//	           // System.out.println("param2[2]="+param2[0]);
//	           // System.out.println("param2[2]="+param2[1]);
//	          //  System.out.println("param2[2]="+param2[2]);
//	           dbUtil.createPreparedStatement("update IGRS_RCPT_BUDGET_REQ_MAPPING set DETAILED_HEAD_AMOUNT=? where BUDGET_RECEIPT_ID=? and MONTH_RECEIPT=?");
//		           
//	            
//	            flag = dbUtil.executeUpdate(param2);
//	          
//	            if (flag) 
//	            {
//	                  dbUtil.commit();
//	                  flag = true;
//	            }
//	          
//	            }
//	      catch (Exception x)
//	      {
//	            System.out.println("Exception ) :- " + x);
//	      }
//	      finally
//	      {
//	            try 
//	            {
//	               // dbUtil.closeConnection();
//
//	            } 
//	            catch (Exception ex) 
//	            {
//	                System.out.println("Exception  :-" + ex);
//	            }
//	        }
//	        return flag;
//	    }
	
	
	 public  ArrayList getDHead(String userId,String ojectId,String segmentId,String schemeId,String minorId,String subMjorId,String majorId) throws SQLException
		{                     
			          ArrayList mainList = new ArrayList();  
			          ArrayList subList = new ArrayList();
			          
			          try 
			          {
			                                 
			            dbUtil.createStatement();      
			            
//			            subList = dbUtil.executeQuery("select  OFFICE_ID,OFFICE_NAME from IGRS_OFFICE_MASTER iom, IGRS_EXP_FUND_TRANSFER_DTLS ift" +
//								 " where iom.OFFICE_ID = ift.dro_office_id(+)" +
//								 " and office_id   not in (select  OFFICE_ID from IGRS_OFFICE_MASTER iom, IGRS_EXP_FUND_TRANSFER_DTLS ift" +
//								 " where iom.OFFICE_ID = ift.dro_office_id(+) and ift.transfer_status = 'D')");
//			 		   									select OBJECT_ID from igrs_exp_head_mapping where SEGMENT_ID in(select SEGMENT_ID from igrs_exp_head_mapping where SCHEME_ID in (select SCHEME_ID from igrs_exp_head_mapping where minor_head_id in (select minor_head_id from igrs_exp_head_mapping where sub_major_head_id in (select sub_major_head_id from igrs_exp_head_mapping where major_head_id in (select major_head_id from igrs_exp_head_mapping where dro_id='"+userId+"') and dro_id='"+userId+"') and dro_id='"+userId+"') and dro_id='"+userId+"') and dro_id='"+userId+"') and dro_id='"+userId+"'
			           subList = dbUtil.executeQuery("SELECT DETAILED_HEAD_ID,DETAILED_HEAD_NAME_E FROM IGRS_EXP_DETAILED_HEAD_MASTER " +
                                                "WHERE DETAILED_HEAD_ID IN (SELECT DETAILED_HEAD_ID FROM IGRS_EXP_HEAD_MAPPING " + 
                                                "WHERE OBJECT_ID='"+ojectId+"' and SEGMENT_ID='"+segmentId+"' and SCHEME_ID='"+schemeId+"' and MINOR_HEAD_ID='"+minorId+"' and SUB_MAJOR_HEAD_ID='"+subMjorId+"' and MAJOR_HEAD_ID='"+majorId+"' " +
                                                "AND DRO_ID = (SELECT OFFICE_ID FROM IGRS_EMP_OFFICE_MAPPING WHERE EMP_ID='"+userId+"'))");
				         
			            
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
	 
	 
	 
	 public ArrayList getBudgerRequestList1(String[] param) throws SQLException
		{
			
							ArrayList reqList = new ArrayList();  
				          ArrayList reqRead = new ArrayList();
				          try 
				          {
				          	  dbUtil.createPreparedStatement("select BUDGET_RECEIPT_ID,OFFICE_NAME,MAJOR_HEAD_NAME_E,SUB_MAJOR_HEAD_NAME_E,APPROVED_STATUS from IGRS_RCPT_BUDGET_REQ_DETAILS BD,IGRS_OFFICE_MASTER OM,IGRS_EXP_MAJOR_HEAD_MASTER MJ,IGRS_EXP_SUB_MAJOR_HEAD_MASTER SM where BD.OFFICE_ID=OM.OFFICE_ID AND BD.MAJOR_HEAD_ID=MJ.MAJOR_HEAD_ID AND BD.SUB_MAJOR_HEAD_ID=SM.SUB_MAJOR_HEAD_ID AND BUDGET_RECEIPT_ID=?");
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
	 
	 
	 public ArrayList getBudgerRequestList2(String durationFrom,String durationTo) throws Exception
		{
			
							ArrayList reqList2 = new ArrayList();  
				          ArrayList reqRead2 = new ArrayList();
				          try 
				          {
				        	  dbUtil = new DBUtility();
				        	  
				            dbUtil.createStatement();
				             reqRead2 = dbUtil.executeQuery("select BUDGET_RECEIPT_ID,OFFICE_NAME,MAJOR_HEAD_NAME_E,SUB_MAJOR_HEAD_NAME_E,APPROVED_STATUS from IGRS_RCPT_BUDGET_REQ_DETAILS BD,IGRS_OFFICE_MASTER OM,IGRS_EXP_MAJOR_HEAD_MASTER MJ,IGRS_EXP_SUB_MAJOR_HEAD_MASTER SM where BD.OFFICE_ID=OM.OFFICE_ID AND BD.MAJOR_HEAD_ID=MJ.MAJOR_HEAD_ID AND BD.SUB_MAJOR_HEAD_ID=SM.SUB_MAJOR_HEAD_ID AND BD.CREATED_DATE between TO_CHAR(to_date('"+durationFrom+"','DD-MM-RRRR'),'DD-MON-RRRR') and TO_CHAR(to_date('"+durationTo+"','DD-MM-RRRR'),'DD-MON-RRRR')");
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
	 
	 
	 
	 public  ArrayList getBudgetRequestDetails(String budgetReqId) throws Exception
		{                     
			          ArrayList reqDetailsList = new ArrayList();  
			          ArrayList reqDetailsRead = new ArrayList();
			          try 
			          {
			        	  dbUtil = new DBUtility();   
			                              
			            dbUtil.createStatement();      
			             reqDetailsRead = dbUtil.executeQuery("select BUDGET_RECEIPT_ID,FISCAL_YEAR,MAJOR_HEAD_NAME_E,SUB_MAJOR_HEAD_NAME_E,MINOR_HEAD_NAME_E,OFFICE_NAME,SCHEME_NAME_E,SEGMENT_NAME_E,OBJECT_NAME_E,APPROVED_STATUS from IGRS_RCPT_BUDGET_REQ_DETAILS BD,IGRS_FISCAL_YEAR FY,IGRS_EXP_MAJOR_HEAD_MASTER MJ,IGRS_EXP_SUB_MAJOR_HEAD_MASTER SM,IGRS_EXP_MINOR_HEAD_MASTER MI,IGRS_OFFICE_MASTER OM,IGRS_EXP_SCHEME_MASTER SC,IGRS_EXP_SEGMENT_MASTER SG,IGRS_EXP_OBJECT_MASTER OB where BD.FISCAL_YEAR_ID=FY.FISCAL_YEAR_ID AND BD.MAJOR_HEAD_ID=MJ.MAJOR_HEAD_ID AND BD.SUB_MAJOR_HEAD_ID=SM.SUB_MAJOR_HEAD_ID AND BD.MINOR_HEAD_ID=MI.MINOR_HEAD_ID AND BD.OFFICE_ID=OM.OFFICE_ID AND BD.SCHEME_HEAD_ID=SC.SCHEME_ID AND BD.SEGMENT_HEAD_ID=SG.SEGMENT_ID AND BD.OBJECT_HEAD_ID=OB.OBJECT_ID AND BUDGET_RECEIPT_ID='"+budgetReqId+"'");    
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
			             catch(Exception e)
			             {
			             
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
	 
	 
	 public  ArrayList getBudgetReqMonthDetails(String budgetReqId) throws Exception
		{                     
			          ArrayList reqMonthList = new ArrayList();  
			          ArrayList reqMonthRead = new ArrayList();
			          try 
			          {
			        	  dbUtil = new DBUtility();              
			            dbUtil.createStatement();      
			         //   reqMonthRead = dbUtil.executeQuery("select DETAIILED_HEAD_ID,DETAILED_HEAD_AMOUNT from IGRS_RCPT_BUDGET_REQ_MAPPING where BUDGET_RECEIPT_ID='"+budgetReqId+"' group by DETAIILED_HEAD_ID,MONTH_RECEIPT,DETAILED_HEAD_AMOUNT order by DETAIILED_HEAD_ID,MONTH_RECEIPT");
			            reqMonthRead = dbUtil.executeQuery("select DETAILED_HEAD_NAME_E,DETAILED_HEAD_AMOUNT from IGRS_RCPT_BUDGET_REQ_MAPPING MP,IGRS_EXP_DETAILED_HEAD_MASTER MS where MP.DETAIILED_HEAD_ID=MS.DETAILED_HEAD_ID AND BUDGET_RECEIPT_ID='BD1231917125817' group by DETAILED_HEAD_NAME_E,MONTH_RECEIPT,DETAILED_HEAD_AMOUNT order by DETAILED_HEAD_NAME_E,MONTH_RECEIPT");    
				     
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
	 
	 
	 
	 public boolean budgetApporve(String param5[]) throws Exception
		{
		      boolean flag = false;  
		      try {
		            
		            dbUtil = new DBUtility();
		            dbUtil.createPreparedStatement("update IGRS_RCPT_BUDGET_REQ_DETAILS set APPROVED_STATUS=?,APPROVED_DATE=TO_DATE(TRUNC(SYSDATE),'DD-MM-RRRR') where BUDGET_RECEIPT_ID=?");
		             flag = dbUtil.executeUpdate(param5);
		            
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
	 
	 
	 	
	 public boolean budgetMonthReqUpdate(String param2[]) throws Exception
		{
		      boolean flag = false;  
		     
		      try {
		    	  
		    	  dbUtil = new DBUtility();
		                dbUtil.createPreparedStatement("update IGRS_RCPT_BUDGET_REQ_MAPPING set DETAILED_HEAD_AMOUNT=? where MONTH_RECEIPT=? and DETAIILED_HEAD_ID=? and BUDGET_RECEIPT_ID=?");
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
