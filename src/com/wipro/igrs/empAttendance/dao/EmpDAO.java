package com.wipro.igrs.empAttendance.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.regTime.dao.RegTimeingsDao;

public class EmpDAO
{
	
	
	private  Logger logger = 
		(Logger) Logger.getLogger(EmpDAO.class);
	DBUtility dbUtil;
	public EmpDAO() throws Exception
	{
	 dbUtil=new  DBUtility();	
	}
	
	
	public boolean empAttendanceCreate(String param1[]) throws Exception
	{
	      boolean flag = false;  
	     
	      try {
	            
	            dbUtil.createPreparedStatement("INSERT INTO IGRS_EMP_DAILY_ATTENDANCE(EMP_ID,ATTENDANCE_DATE,IN_TIME_HOUR,IN_TIME_AM_PM,OUT_TIME_HOUR,OUT_TIME_AM_PM,ATTENDANCE_STATUS,REMARKS)VALUES(?,TO_DATE(?,'DD-MM-RRRR'),?,?,?,?,?,?)");
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
	
	
	public  boolean checkEmpId(String employeeId) throws Exception
	{                     
		          ArrayList addList = new ArrayList();  
		          ArrayList readList = new ArrayList();
		          boolean flag = false;
		          try 
		          {
		            dbUtil.createStatement();      
		            //  readList = dbUtil.executeQuery("select DETAILED_HEAD_ID,OFFICE_NAME,district_name,FISCAL_YEAR,BUDGET_ALLOCATED,BUDGET_EXHAUSTED,BUDGET_BALANCE from IGRS_EXP_BUDGET_TRACK bt,IGRS_DISTRICT_MASTER dm,IGRS_FISCAL_YEAR fy,IGRS_OFFICE_MASTER om where bt.office_id = om.office_id and bt.district_id = dm.district_id and bt.fiscal_year_id = fy.fiscal_year_id AND DETAILED_HEAD_ID='"+detailedHeadId+"'");    
		            readList = dbUtil.executeQuery("select FIRST_NAME,MIDDLE_NAME,LAST_NAME,TO_CHAR(TO_DATE(trunc(DATE_OF_JOINING),'DD-MM-RRRR'),'DD-MM-RRRR'),TO_CHAR(TO_DATE(trunc(DATE_OF_BIRTH),'DD-MM-RRRR'),'DD-MM-RRRR'),GENDER from IGRS_EMP_MASTER where EMP_ID='"+employeeId+"'");    
			       if(readList == null || readList.size()==0)
			       {
			    	   flag = true;
			    	   
			       }
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
		         
		          if(readList!=null) 
		          {
		              for(int i=0;i<readList.size();i++)  
		                {
		            	  addList.add((ArrayList)readList.get(i));
		                 }
		          }         
		       return flag;
		 }  

}
