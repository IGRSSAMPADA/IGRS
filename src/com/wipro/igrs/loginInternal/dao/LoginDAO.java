package com.wipro.igrs.loginInternal.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.wipro.igrs.baseaction.dto.UserDTO;
import com.wipro.igrs.db.DBUtility;
//import com.wipro.igrs.db.DBUtils;
import com.wipro.igrs.login.dto.MasterListDTO;
import com.wipro.igrs.login.sql.CommonSQL;
import com.wipro.igrs.reginit.sql.RegCommonSQL;

 

public class LoginDAO {
	
	//DBUtility dbUtil = null;
	//DBUtils instance = DBUtils.getInstance(); //added by roopam
	CallableStatement clstmt;
	PreparedStatement pstmt;
	
	/**
	 * @author Madan Mohan
	 */
	private Logger logger = 
		(Logger) Logger.getLogger(LoginDAO.class);
	
	/**
	 * @throws Exception
	 */
     public LoginDAO() throws Exception{
	}
	
	public synchronized void updateLoginStatus(String[] param) throws Exception{
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.UPDATE_USER_LIST_LOGIN);
			//pstmt=instance.createPreparedStatement(CommonSQL.UPDATE_USER_LIST_LOGIN);
			boolean flag = dbUtil.executeUpdate(param);
			//boolean flag = instance.executeUpdate(param, pstmt);
			if(flag) {
				//add code for commit here.
			}
		}catch(Exception x) {
			logger.debug(x);
			x.printStackTrace();
			dbUtil.rollback();
		}finally {
			 
			if(dbUtil !=null) {
				dbUtil.closeConnection();
			}
		 if (pstmt!=null)
			 pstmt.close();
		}
	}
	@SuppressWarnings("unchecked")
	public ArrayList getRoleName() throws Exception {
		ArrayList list = new ArrayList ();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(CommonSQL.SELECT_ROLE_NAME);
			
			//instance.createStatement();
			//list=instance.executeQuery(CommonSQL.SELECT_ROLE_NAME);
			 
		}catch(Exception x) {
			logger.debug(x);
			x.printStackTrace();
			dbUtil.rollback();
		}finally {
			 
			if(dbUtil !=null) {
				dbUtil.closeConnection();
			}
		 
		}
		return list;
	}
	/**
	 * getOfficeIdOfLoggedInUser
	 * for getting office id of logged in user from db.
	 * @param String
	 * @return String
	 * @author ROOPAM
	 * @date 11 April 2013
	 */
	/*public String getOfficeIdOfLoggedInUser(String userId) throws Exception {
        String officeId="";
        try {
        	
        	dbUtil.createStatement();
        	String sql=CommonSQL.GET_OFFICE_ID+"'"+userId+"'";
        	officeId = dbUtil.executeQry(sql);
       } catch (Exception exception) {
         System.out.println("Exception in getOfficeIdOfLoggedInUser-LoginDAO" + exception);
         }finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("LoginDAO in dao start" + e.getStackTrace());
			}
		}
       return officeId;
       }*/
	/**
	 * getRoleIds
	 * for getting ROLE ID LIST of logged in user from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 * @date 11 April 2013
	 */
	public ArrayList getRoleIds(String userId) throws Exception {
		ArrayList list=new ArrayList();
		DBUtility dbUtil = null;
        try {
        	dbUtil = new DBUtility();
        	dbUtil.createStatement();
        	String sql=CommonSQL.GET_ROLE_ID_LIST+"'"+userId+"'";
        	list = dbUtil.executeQuery(sql);
       } catch (Exception exception) {
         System.out.println("Exception in getRoleIds-LoginDAO" + exception);
         }finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("LoginDAO in dao start" + e.getStackTrace());
			}
		}
       return list;
       }
	/**
	 * getOfficeDistIds
	 * for getting ROLE ID LIST of logged in user from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 * @date 11 April 2013
	 */
	public ArrayList getOfficeDistIds(String roleId) throws Exception {
		ArrayList list=new ArrayList();
		DBUtility dbUtil = null;
        try {
        	dbUtil = new DBUtility();
        	dbUtil.createStatement();
        	String sql=CommonSQL.GET_OFFICE_DIST_ID_LIST+"'"+roleId+"'";
        	list = dbUtil.executeQuery(sql);
       } catch (Exception exception) {
         System.out.println("Exception in getOfficeDistIds-LoginDAO" + exception);
         }finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("LoginDAO in dao start" + e.getStackTrace());
			}
		}
       return list;
       }

	/*
	 * Added by Ankita on 24/7/2013
	 * */
	public int addNoAttempt(String userId, String getCurrTimeStamp) throws Exception {
		
		Boolean noattempt = false;
		String getNumberAttempts ="";
		int i=0;
		DBUtility dbUtil = null;
		try
		{
			dbUtil = new DBUtility();
			String timeStamp=getTimeStamp(userId);
			dbUtil.createStatement();
			String sql =CommonSQL.GET_NUMBER_ATTEMPTS+"'"+userId+"'";
			getNumberAttempts = dbUtil.executeQry(sql);
			long timeDiff = getTimeStampdiff(timeStamp,getCurrTimeStamp);
			if(!(getNumberAttempts.equalsIgnoreCase("")))
			{
			i = Integer.parseInt(getNumberAttempts);
			if (timeDiff<15)
			{
			i=i+1;
			}
			else if(timeDiff>15 && i<=2)
			{
				i=1;// reset number of attempts to 1 if number of attempts taken< 3 after 15 min
				//above changed from 0 to 1 for fixing bug number 2081
			}
			//dbUtil = new DBUtility();
			//dbUtil.createStatement();
			String[] temp = new String[2];
			temp[0]=Integer.toString(i);
			temp[1]=userId;
			String sql1= CommonSQL.INCREMENT_NUMBER_ATTEMPT;
			dbUtil.createPreparedStatement(sql1);
			noattempt=dbUtil.executeUpdate(temp);
			if(noattempt){
				dbUtil.commit();
			}else{
				dbUtil.rollback();
			}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		logger.error("error in LoginDAO"+e.getStackTrace());
		}
		finally
		{
			try
			{
				dbUtil.closeConnection();
			}catch(Exception e)
			{
				logger.debug("Error in Closing connection In LoginDAO"+e.getStackTrace());
			}
		}
		
		return i;
	}

	private String getTimeStamp(String userId) throws Exception {
		
		String oldTimeStamp ="";
		DBUtility dbUtil = null;
			try
		{
				//dbUtil = new DBUtility();
			dbUtil.createStatement();
			String sql = CommonSQL.GET_OLD_TIMESTAMP+"'"+userId+"'";
			oldTimeStamp = dbUtil.executeQry(sql);
		}
		catch(Exception e)
		{
			logger.debug(e);
		}
		finally
		{
			dbUtil.closeConnection();
		}
		/*finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}*/
		
		// TODO Auto-generated method stub
		return oldTimeStamp;
	}

	private long getTimeStampdiff(String timeStamp, String getCurrTimeStamp) throws Exception {
		// TODO Auto-generated method stub
		long diffMinutes=0;
		long millisecondsOld=0;
		long millisecondsNew=0;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			if(!(timeStamp.equalsIgnoreCase("")))
			{	
				Date tmStamp = sdf.parse(timeStamp);
				millisecondsOld = tmStamp.getTime();
			}
			
			Date currTmStamp = sdf.parse(getCurrTimeStamp);
			
			millisecondsNew=currTmStamp.getTime();
			long diff = millisecondsNew - millisecondsOld;
			long diffSeconds = diff / 1000;
			diffMinutes = diff / (60 * 1000);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return diffMinutes;
	}

	public String getNoOfAttempts(String userId) throws Exception {
		String noattempt = "";
		DBUtility dbUtil = null;
		try
		{
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String sql =CommonSQL.GET_NUMBER_ATTEMPTS+"'"+userId+"'";
			noattempt = dbUtil.executeQry(sql);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		logger.error("error in LoginDAO"+e.getStackTrace());
		}
		finally
		{
			try
			{
				dbUtil.closeConnection();
			}catch(Exception e)
			{
				logger.debug("Error in Closing connection In LoginDAO"+e.getStackTrace());
			}
		}
		
		return noattempt;
	}

	public boolean lockAccount(String userId) throws Exception {
		// TODO Auto-generated method stub
		boolean Flag = false;
		DBUtility dbUtil = null;
		try
		{
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String sql =CommonSQL.GET_USER_TYPE;
			String[] temp = new String[1];
			temp[0]=userId;
			dbUtil.createPreparedStatement(sql);
			String typeOfUser=dbUtil.executeQry(temp);
			if(typeOfUser.equalsIgnoreCase("2"))// internal user
			{
				String sql1 = CommonSQL.LOCK_ACCOUNT;
				dbUtil = new DBUtility();
				dbUtil.createStatement();
				dbUtil.createPreparedStatement(sql1);
				
				Flag=dbUtil.executeUpdate(temp);
				if(Flag){
					dbUtil.commit();
				}else{
					dbUtil.rollback();
				}
				
			}
			
		}
		catch(Exception e)
		{
			
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		
		return Flag;
	}

	public String getCurrTimeStamp() throws Exception {
		// TODO Auto-generated method stub
		String timeCurrTimeStamp ="";
		String sql;
		DBUtility dbUtil = null;
		try
		{
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			sql =CommonSQL.GET_SYS_DATE;
			
			timeCurrTimeStamp=dbUtil.executeQry(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("error in getCurrTimeStamp() in LogonDAO");
		}
		finally
		{
			try
			{
				dbUtil.closeConnection();
			}
			catch(Exception e)
			{
				logger.error("error in getCurrTimeStamp() in closing connection in LogonDAO");
			}
		}
		return timeCurrTimeStamp;
	}

	public ArrayList getInternalUsersOffice(String userId) throws Exception {
		boolean Flag = false;
		ArrayList mainList = new ArrayList();
		ArrayList subList = new ArrayList();
		ArrayList list = new ArrayList();
		String getOfcName ="";
		DBUtility dbUtil = null;
		try
		{
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String sql =CommonSQL.GET_USER_TYPE;
			String[] temp = new String[1];
			temp[0]=userId;
			dbUtil.createPreparedStatement(sql);
			String typeOfUser=dbUtil.executeQry(temp);
			if(typeOfUser.equalsIgnoreCase("2"))// internal user
			{
				String sql1 = CommonSQL.GET_MAPPED_OFFICE_ID;
				//dbUtil = new DBUtility();
				//dbUtil.createStatement();
				dbUtil.createPreparedStatement(sql1);
				mainList = dbUtil.executeQuery(temp);
				
				if(mainList.size()>0 && mainList!=null)
				{
					for(int i=0;i<mainList.size();i++)
					{
						subList= (ArrayList) mainList.get(i);
						String ofcId = (String) subList.get(0);
						//dbUtil = new DBUtility();
						//dbUtil.createStatement();
						String[] arr = {ofcId};
						//String sql2=CommonSQL.GET_MAPPED_OFFICES+"'"+ofcId+"'";
						String sql2=CommonSQL.GET_MAPPED_OFFICES;
						dbUtil.createPreparedStatement(sql2);
						getOfcName=dbUtil.executeQry(arr);
						
						MasterListDTO udto = new MasterListDTO();
						udto.setOfficeID(ofcId);
						udto.setOfficeName(getOfcName);
						list.add(udto);
						
						
					}
				}
				
			}
			/*else if(typeOfUser.equalsIgnoreCase("1"))//External User
			{
				
				dbUtil = new DBUtility();
				dbUtil.createStatement();
				String sql2=CommonSQL.GET_MAPPED_OFFICES+"'OFC01'";// ofc01 id for no office required
				getOfcName=dbUtil.executeQry(sql2);
				MasterListDTO udto = new MasterListDTO();
				udto.setOfficeID("OFC01");
				udto.setOfficeName(getOfcName);
				list.add(udto);
			}*/
		// TODO Auto-generated method stub
		
	}
		catch(Exception e)
		{
			logger.debug(e);
			e.printStackTrace();
		}
		finally
		{
			logger.debug("In final block of getInternalUsersOffice");
			try
			{
				dbUtil.closeConnection();
			}catch(Exception e)
			{
				logger.debug("error in close connection");
			}
			}
		return list;
		}
	//below created by roopam for resetting attempt count and status
	public boolean resetAttemptCount(String userId) throws Exception {
		// TODO Auto-generated method stub
		boolean Flag = false;
		DBUtility dbUtil = null;
		try
		{	
			String[] temp = new String[1];
			temp[0]=userId;
								
				String sql1 = CommonSQL.RESET_ATTEMPT_COUNT;
				dbUtil = new DBUtility();
				//dbUtil.createStatement();
				dbUtil.createPreparedStatement(sql1);
				
				dbUtil.executeQry(temp);
			
			
		}
		catch(Exception e)
		{
			
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		
		return Flag;
	}
	
	public ArrayList getLoggedInUserDetls(String userId) throws Exception {
		ArrayList list=new ArrayList();
		DBUtility dbUtil = null;
	    try {
	    	dbUtil = new DBUtility();
	    	//dbUtil.createStatement();
	    	String sql=CommonSQL.GET_ROLE_ID_LIST+"'"+userId+"'";
	    	list = dbUtil.executeQuery(sql);
	   } catch (Exception exception) {
	     System.out.println("Exception in getRoleIds-LoginDAO" + exception);
	     }finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("LoginDAO in dao start" + e.getStackTrace());
			}
		}
	   return list;
	   }
}
