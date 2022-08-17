package com.wipro.igrs.dataEntry.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.wipro.igrs.budgetRequest.dao.BudgetDAO;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.pot.sql.CommonSQL;

public class DataEntryDao 
{
    private  Logger logger = 
	(Logger) Logger.getLogger(DataEntryDao.class);

        DBUtility dbUtil;
        public DataEntryDao() throws Exception 
        {
         dbUtil=new  DBUtility();	
        }
        
        
        
        public  ArrayList getDeedList() throws SQLException
	{                     
		          ArrayList deedMain = new ArrayList();  
		          ArrayList deedSub = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		            //officeRead = dbUtil.executeQuery("select distinct OFFICE_NAME from IGRS_OFFICE_MASTER order by office_name");
		            deedSub = dbUtil.executeQuery("SELECT DEED_TYPE_ID,DEED_TYPE_NAME FROM IGRS_DEED_TYPE_MASTER");
		          }
		          catch (Exception e) {
		        	  logger.error("exception in calling at DAO Class at getDeedList()  " +e);
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
		           if(deedSub!=null) 
		          {
		              for(int i=0;i<deedSub.size();i++)  
		                {
		            	  deedMain.add((ArrayList)deedSub.get(i));
		                 }
		          }         
		       return deedMain;
		 }  
        
        
        public  ArrayList getDistrictNames() throws SQLException
	{                     
		          ArrayList distMain = new ArrayList();  
		          ArrayList distSub = new ArrayList();
		          try 
		          {
		                                 
		            dbUtil.createStatement();   
		           String  sql = "select * from igrs_district_master where upper(DISTRICT_STATUS)='A'";
		          //  potRead = dbUtil.executeQuery("select * from igrs_district_master");   
		            distSub = dbUtil.executeQuery(sql);                                              
			        
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
		         if(distSub!=null) 
		          {
		              for(int i=0;i<distSub.size();i++)  
		                {
		            	  distMain.add((ArrayList)distSub.get(i));
		                        	 
		                }
		          }         
		       return distMain;
		 }  
    

	public  ArrayList getDeedDetails() throws SQLException
	{                     
		          ArrayList deedMain = new ArrayList();  
		          ArrayList deedSub = new ArrayList();
		          try 
		          {
		            dbUtil.createStatement();      
		            deedSub = dbUtil.executeQuery("SELECT AM.ATTRIBUTE_DATA_TYPE,DM.ATTRIBUTE_ID,LM.LABEL_NAME,AM.ATTRIBUTE_NAME FROM IGRS_DEED_FORM_LABEL_MAPPING DM,IGRS_FORM_LABEL_MASTER LM,IGRS_FORM_ATTRIBUTE_MASTER AM WHERE DM.LABEL_ID=LM.LABEL_ID AND DM.ATTRIBUTE_ID=AM.ATTRIBUTE_ID AND DM.DEED_TYPE_ID='DEED_001'");
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
		        if(deedSub!=null)
		        {
		            for(int i=0;i<deedSub.size();i++)
		            {
		        	deedMain.add((ArrayList)deedSub.get(i));
		            }
		        }
		         
		       return deedMain;
		       
		 }
	
	
	public boolean getAdoptionDetails(String param2[]) throws Exception
	{
	      boolean flag = false;  
	      try {
		  
		  
	        //   System.out.println("I am in dao===");
	           //  String sql = "INSERT INTO IGRS_ADOPTION_DEED_DETAILS (REG_TXN_ID,VOLUME_NO,BOOK_NO,REG_NO,DISTRICT_ID,REG_DATE,SRO_NAME,SR_NAME,ADJUDICATION_DETAILS,REMARKS,CAVEATS,EXEMPTION,TOT_STAMP_DUTY,REG_FEE,OTHER_FEE,PENDING_FEE,WITNESS_NAME_ONE,WITNESS_NAME_TWO,CREATED_DATE)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TRUNC(SYSDATE))";
	           //System.out.println("sql is ==========="+sql);
			
	            // System.out.println(sql);
	           dbUtil.createPreparedStatement("INSERT INTO IGRS_ADOPTION_DEED_DETAILS (REG_TXN_ID,VOLUME_NO,BOOK_NO,REG_NO,DISTRICT_ID,REG_DATE,SRO_NAME,SR_NAME,ADJUDICATION_DETAILS,REMARKS,CAVEATS,EXEMPTION,TOT_STAMP_DUTY,REG_FEE,OTHER_FEE,PENDING_FEE,WITNESS_NAME_ONE,WITNESS_NAME_TWO,CREATED_DATE)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TRUNC(SYSDATE))");
	      //    dbUtil.createPreparedStatement(sql);
	        //  System.out.println("sql is ==========="+sql);
		           
	            flag = dbUtil.executeUpdate(param2);
	            
	         System.out.println("flag is ============"+flag);
	           
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
	

}
