package com.wipro.igrs.conReport.dao;


/**
 * ===========================================================================
 * File           :   ConReportDAO.java
 * Description    :   Represents the DAO Class ECR
 * @author        :   Imran Shaik
 * Created Date   :   September 08, 2008
 * ===========================================================================
 */

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.conReport.dto.ConReportDTO;
import com.wipro.igrs.conReport.sql.CommonSQL;
import com.wipro.igrs.db.DBUtility;

/**
 * @author Imran Shaik
 *
 */
public class ConReportDAO {

	private Logger logger = 
		(Logger) Logger.getLogger(ConReportDAO.class);
	DBUtility dbutil = null;

	public ArrayList getFiYear() {
		ArrayList list = new ArrayList();
		try {
			dbutil = new DBUtility();
			dbutil.createStatement();
			list = dbutil.executeQuery(CommonSQL.SELECT_FISCAL_YEAR);
			dbutil.closeConnection();
		} catch (Exception e) {
			logger.debug("exception in calling at DAO Class at getFiYear()  " +e);
			e.printStackTrace();
		}
		return list;
	}

	public  ArrayList empDetails(String empId)
	{                     
		ArrayList addList = new ArrayList();  
		ArrayList readList = new ArrayList();
		try 
		{
			dbutil = new DBUtility();
			dbutil.createStatement();      
		    readList = dbutil.executeQuery(CommonSQL.SELECT_EMP_DETAILS+empId+"'");    
		}
		catch (Exception e) {
		    logger.debug("exception in calling at DAO Class at empDetails()  " +e);
		    e.printStackTrace();
		}
		finally 
		{
			try
			{
				dbutil.closeConnection();                                  
			}
			catch(Exception e){
				logger.debug("Exception in Finally Block  "+ e);   
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

	public boolean saveEmaployeeComments(String[] args) {
		boolean boo = false;
		try {
			dbutil = new DBUtility();
			String conId = "";
			dbutil.createStatement();
			ArrayList list = dbutil.executeQuery("SELECT LPAD(IGRS_CON_REPORT_SEQ.NEXTVAL,4,0) FROM DUAL");
			if(list!=null)
			if (list.size() > 0) {
				ArrayList li = (ArrayList) list.get(0);
				conId = conId + li.get(0).toString();
			}
			String arr[] = {args[0]};
			String query = CommonSQL.SELECT_SUPER_DETAILS;
			dbutil.createPreparedStatement(query);
			String superId = dbutil.executeQry(arr);
		
			query = CommonSQL.SELECT_REVIEWER_DETAILS;
			dbutil.createPreparedStatement(query);
			String reviewrId = dbutil.executeQry(arr);
			
			arr = new String[]{conId,args[0],args[1],args[3],superId,reviewrId};
			query = CommonSQL.INSERT_EMP_CON_REPORT;
			dbutil.createPreparedStatement(query);
			boo = dbutil.executeUpdate(arr);
			arr = new String[]{conId,args[2]};
			query = CommonSQL.INSERT_EMP_CON_REPORT_MAP;
			dbutil.createPreparedStatement(query);
			boo = dbutil.executeUpdate(arr);
			if(boo)
				dbutil.commit();
			else
				dbutil.rollback();
			
		}catch (Exception e) {
			logger.debug("exception in calling at DAO Class at saveEmaployeeCooments()  " +e);
			e.printStackTrace();
		}/*finally 
	    {
	    	try
	        {
	    		//dbutil.closeConnection();                                  
	        }
	    	catch(Exception e){
	    		logger.debug("Exception in Finally Block  "+ e);   
	    	} 
	    } */
		return boo;
	}  
	
	public ArrayList getEmpsList(String supervisorId, String param) {
		ArrayList list = new ArrayList();
		String sql="";
		if(param.equalsIgnoreCase("supervisor")){
			sql = CommonSQL.SELECT_EMP_CON_REP_BY_SUSERVISOR;
		} else if(param.equalsIgnoreCase("empview")){
			sql = CommonSQL.SELECT_EMP_CON_REP_BY_EMP;
		} else{
			sql = CommonSQL.SELECT_EMP_CON_REP_BY_REVIEWER;
		}
		try {
			dbutil = new DBUtility();
			String arr[] = {supervisorId};
			dbutil.createPreparedStatement(sql);
			list = dbutil.executeQuery(arr);
			dbutil.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("exception in calling at DAO Class at getEmpsList()  " +e);
		}
		return list;
	}
	
	public ArrayList getConReportInfo(String conId)
	{                     
		ArrayList addList = new ArrayList();  
		ArrayList readList = new ArrayList();
		try 
		{
			dbutil = new DBUtility();
			String arr[] = {conId};
			dbutil.createStatement();
			dbutil.createPreparedStatement(CommonSQL.SELECT_CON_DETAILS);
			readList = dbutil.executeQuery(arr);
			dbutil.closeConnection();
		}
		catch (Exception e) {
		    logger.debug("exception in calling at DAO Class at getConReportInfo()  " +e);
		}
		finally 
		{
			try
			{
				dbutil.closeConnection();                                  
			}
			catch(Exception e){
				logger.debug("Exception in Finally Block  "+ e);   
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

	public boolean saveSupervisorComments(String[] args) {
		boolean boo = false;
		try {
			dbutil = new DBUtility();
			
			String arr[] = {args[0],args[1],args[4]};
			String query = CommonSQL.INSERT_SUPER_CON_REPORT;
			dbutil.createPreparedStatement(query);
			boo = dbutil.executeUpdate(arr);
			
			arr = new String[]{args[2],args[3],args[4]};
			query = CommonSQL.INSERT_SUPER_CON_REPORT_MAP;
			dbutil.createPreparedStatement(query);
			boo = dbutil.executeUpdate(arr);
			
			if(boo)
				dbutil.commit();
			else
				dbutil.rollback();
		
		}catch (Exception e) {
			logger.debug("exception in calling at DAO Class at saveSupervisorComments()  " +e);
			e.printStackTrace();
		}finally 
	    {
	    	try
	        {
	    		dbutil.closeConnection();                                  
	        }
	    	catch(Exception e){
	    		logger.debug("Exception in Finally Block  "+ e);   
	    	} 
	    } 
		return boo;
	}

	public boolean saveReviewerComments(String[] args) {
		boolean boo = false;
		try {
			dbutil = new DBUtility();
			String arr[] = {args[0],args[1],args[4]};
			String query = CommonSQL.INSERT_REVIEWER_CON_REPORT;
			dbutil.createPreparedStatement(query);
			boo = dbutil.executeUpdate(arr);
			arr = new String[]{args[2],args[3],args[4]};
			query = CommonSQL.INSERT_REVIEWER_CON_REPORT_MAP;
			dbutil.createPreparedStatement(query);
			boo = dbutil.executeUpdate(arr);
			
			if(boo)
				dbutil.commit();
			else
				dbutil.rollback();
		
		}catch (Exception e) {
			logger.debug("exception in calling at DAO Class at saveReviewerComments()  " +e);
			e.printStackTrace();
		}finally 
	    {
	    	try
	        {
	    		dbutil.closeConnection();                                  
	        }
	    	catch(Exception e){
	    		logger.debug("Exception in Finally Block  "+ e);   
	    	} 
	    } 
		return boo;
	}

	public ArrayList getSuperEditInfo(String conId) {
		ArrayList addList = new ArrayList();  
		ArrayList readList = new ArrayList();
		try 
		{
			dbutil = new DBUtility();
			String arr[] = {conId};
			dbutil.createStatement();
			dbutil.createPreparedStatement(CommonSQL.SELECT_SUPER_CON_REPORT);
			readList = dbutil.executeQuery(arr);
		}
		catch (Exception e) {
		    logger.debug("exception in calling at DAO Class at getSuperEditInfo()  " +e);
		}
		finally 
		{
			try
			{
				dbutil.closeConnection();                                  
			}
			catch(Exception e){
				logger.debug("Exception in Finally Block  "+ e);   
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
	
	public ArrayList getReviewEditInfo(String conId) {
		ArrayList addList = new ArrayList();  
		ArrayList readList = new ArrayList();
		try 
		{
			dbutil = new DBUtility();
			String arr[] = {conId};
			dbutil.createStatement();
			dbutil.createPreparedStatement(CommonSQL.SELECT_REVIEWER_CON_REPORT);
			readList = dbutil.executeQuery(arr);
		}
		catch (Exception e) {
		    logger.debug("exception in calling at DAO Class at getReviewEditInfo()  " +e);
		}
		finally 
		{
			try
			{
				dbutil.closeConnection();                                  
			}
			catch(Exception e){
				logger.debug("Exception in Finally Block  "+ e);   
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

	public ArrayList getEmpEditInfo(String conId) {
		ArrayList addList = new ArrayList();  
		ArrayList readList = new ArrayList();
		try 
		{
			dbutil = new DBUtility();
			String arr[] = {conId};
			dbutil.createStatement();
			dbutil.createPreparedStatement(CommonSQL.SELECT_EMP_CON_REPORT);
			readList = dbutil.executeQuery(arr);
		}
		catch (Exception e) {
		    logger.debug("exception in calling at DAO Class at getReviewEditInfo()  " +e);
		}
		finally 
		{
			try
			{
				dbutil.closeConnection();                                  
			}
			catch(Exception e){
				logger.debug("Exception in Finally Block  "+ e);   
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

	public boolean updateEmaployeeComments(String[] args) {
		boolean boo = false;
		try {
			dbutil = new DBUtility();
			String arr[] = {args[0],args[1],args[3]};
			String query = CommonSQL.UPDATE_EMP_CON_REPORT;
			dbutil.createPreparedStatement(query);
			boo = dbutil.executeUpdate(arr);
			arr = new String[]{args[2],args[3]};
			query = CommonSQL.UPDATE_EMP_CON_REPORT_MAP;
			dbutil.createPreparedStatement(query);
			boo = dbutil.executeUpdate(arr);
			
			if(boo)
				dbutil.commit();
			else
				dbutil.rollback();
		
		}catch (Exception e) {
			logger.debug("exception in calling at DAO Class at updateEmaployeeComments()  " +e);
			e.printStackTrace();
		}finally 
	    {
	    	try
	        {
	    		dbutil.closeConnection();                                  
	        }
	    	catch(Exception e){
	    		logger.debug("Exception in Finally Block  updateEmaployeeComments"+ e);   
	    	} 
	    } 
		return boo;
	}
	
}
