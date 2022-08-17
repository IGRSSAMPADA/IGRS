package com.wipro.igrs.reconciliation.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.reconciliation.bd.ReconciliationBd;

public class ReconciliationDao
{
	private  Logger logger = 
		(Logger) Logger.getLogger(ReconciliationDao.class);
	DBUtility dbUtil;
	public ReconciliationDao() throws Exception
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
		            logger.error("exception in calling at DAO Class at getDistrict()  " +e);
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
	
	
	public  ArrayList getDro(HttpServletRequest request) throws SQLException
	{                     
		          ArrayList mainList = new ArrayList();  
		          ArrayList subList = new ArrayList();
		          try 
		          {
		                                 
		            dbUtil.createStatement();      
		          // subList = dbUtil.executeQuery("select OFFICE_ID,OFFICE_NAME from IGRS_OFFICE_MASTER iom, IGRS_EXP_FUND_TRANSFER_DTLS ift where iom.OFFICE_ID = ift.dro_office_id and transer_status in ('A','R'");
		           // select distinct OFFICE_ID,OFFICE_NAME from IGRS_OFFICE_MASTER iom, IGRS_EXP_FUND_TRANSFER_DTLS ift
		          //  where iom.OFFICE_ID = ift.dro_office_id and ift.transfer_status in ('A','R');
		            
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
	
		
	public boolean reconCreate(String param1[]) throws Exception
	{
	      boolean flag = false;  
	     
	      try {
	            
	            dbUtil.createPreparedStatement("INSERT INTO IGRS_EXP_BUDGET_TRACK(DISTRICT_ID,OFFICE_ID,FISCAL_YEAR_ID,DETAILED_HEAD_ID,BUDGET_ALLOCATED,BUDGET_EXHAUSTED,BUDGET_BALANCE)VALUES(?,?,?,?,?,?,?)");
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
	    	  x.printStackTrace();
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
	
	public boolean isPrimaryKeyExists(String param1[]) throws Exception
	{
	      boolean flag = false;  
	      ArrayList recordList = new ArrayList();
	      ArrayList record = new ArrayList();
	     
	      try {
		    	   //  dbUtil.createStatement();  
		    	     dbUtil.createPreparedStatement("SELECT OFFICE_ID,FISCAL_YEAR_ID,DETAILED_HEAD_ID FROM IGRS_EXP_BUDGET_TRACK WHERE OFFICE_ID=? AND FISCAL_YEAR_ID=? AND DETAILED_HEAD_ID=?");
		       	     recordList = dbUtil.executeQuery(param1);
		      if(recordList != null)
	           {
	        	   if(recordList.size()>0)
	        	   {   
	        	   flag = true;
	        	   }
	        	 for(int i=0;i<recordList.size();i++)
	        	 {
	        		 record = (ArrayList)recordList.get(i);
	        	 }
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
	              //  dbUtil.closeConnection();

	            } 
	            catch (Exception ex) 
	            {
	            	 logger.error("Exception  :-" + ex);
	            }
	        }
	         return flag;
	    }
	
			
	public  ArrayList getAllHeads(String dstrctNm, String financYr, String duratnFrm, String duratnTo) throws SQLException
	{                     
		          ArrayList list = new ArrayList();  
		          ArrayList read = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
//		             read = dbUtil.executeQuery("select DETAILED_HEAD_ID,OFFICE_NAME,district_name,FISCAL_YEAR from " +
//		             		"IGRS_EXP_BUDGET_TRACK bt,IGRS_DISTRICT_MASTER dm,IGRS_FISCAL_YEAR fy,IGRS_OFFICE_MASTER om " +
//		             		"where bt.office_id = om.office_id and bt.district_id = dm.district_id and " +
//		             		"bt.fiscal_year_id = fy.fiscal_year_id and bt.district_id='"+dstrctNm+"' and " +
//		             		"fy.fiscal_year_id='"+financYr+"' " +
//		             		"AND bt.CREATED_DATE between TO_CHAR(to_date('"+duratnFrm+"','DD-MM-RRRR'),'DD-MON-RRRR') and TO_CHAR(to_date('"+duratnTo+"','DD-MM-RRRR'),'DD-MON-RRRR')");

		             read = dbUtil.executeQuery("select DETAILED_HEAD_ID,OFFICE_NAME,district_name,FISCAL_YEAR from " +
			             		"IGRS_EXP_BUDGET_TRACK bt,IGRS_DISTRICT_MASTER dm,IGRS_FISCAL_YEAR fy,IGRS_OFFICE_MASTER om " +
			             		"where bt.office_id = om.office_id and bt.district_id = dm.district_id and " +
			             		"bt.fiscal_year_id = fy.fiscal_year_id");
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
	
	
			
	public  ArrayList headDetails(String detailedHeadId) throws Exception
	{                     
		          ArrayList addList = new ArrayList();  
		          ArrayList readList = new ArrayList();
		          try 
		          {
		                               
		            dbUtil.createStatement();      
		              readList = dbUtil.executeQuery("select district_name,OFFICE_NAME,FISCAL_YEAR,dm.DETAILED_HEAD_NAME_E,BUDGET_ALLOCATED,BUDGET_EXHAUSTED,BUDGET_BALANCE from IGRS_EXP_BUDGET_TRACK bt,IGRS_DISTRICT_MASTER dm,IGRS_FISCAL_YEAR fy,IGRS_OFFICE_MASTER om,IGRS_EXP_DETAILED_HEAD_MASTER dm where bt.DETAILED_HEAD_ID = dm.DETAILED_HEAD_ID and bt.office_id = om.office_id and bt.district_id = dm.district_id and bt.fiscal_year_id = fy.fiscal_year_id AND bt.DETAILED_HEAD_ID='"+detailedHeadId+"'");    
			         
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at headDetails()  " +e);
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
		            	 e.printStackTrace();
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
	
	
	
	public  ArrayList getScheme(String userId,String minorId,String subMjorId,String majorId) throws SQLException
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
		        	  logger.error("exception in calling at DAO Class at getScheme()  " +e);
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
	
	

}
