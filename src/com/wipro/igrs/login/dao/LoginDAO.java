package com.wipro.igrs.login.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;

import com.wipro.igrs.UserRegistration.action.CryptoLibrary;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.login.constant.LoginConstant;
import com.wipro.igrs.login.dto.MasterListDTO;
import com.wipro.igrs.login.sql.CommonSQL;

 

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
			dbUtil = new DBUtility("");
			Connection conn=dbUtil.getDBConnection();
			Statement st=conn.createStatement();
			String timeStamp=getTimeStamp(userId);
			//dbUtil.createStatement();
			String sql =CommonSQL.GET_NUMBER_ATTEMPTS+"'"+userId+"'";
			ResultSet rset=st.executeQuery(sql);
		//	getNumberAttempts = dbUtil.executeQry(sql);
		while(rset.next())
		{
			getNumberAttempts=rset.getString(1);
		}
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
			//dbUtil.createPreparedStatement(sql1);
			PreparedStatement pst=conn.prepareStatement(sql1);
			pst.setString(1, Integer.toString(i));
			pst.setString(2, userId);
			int q=pst.executeUpdate();
			//noattempt=dbUtil.executeUpdate(temp);
			if(q>0){
				conn.commit();
			}else{
				conn.rollback();
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
			dbUtil = new DBUtility("");
			Connection conn=dbUtil.getDBConnection();
			Statement st=conn.createStatement();
			
			String sql = CommonSQL.GET_OLD_TIMESTAMP+"'"+userId+"'";
			ResultSet rset=st.executeQuery(sql);
			while(rset.next())
			{
				oldTimeStamp = rset.getString(1);
			}
			
		}
		catch(Exception e)
		{
			logger.debug(e);
			
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
			//dbUtil.createStatement();
			String sql =CommonSQL.GET_NUMBER_ATTEMPTS+"?";
			dbUtil.createPreparedStatement(sql);
			String[] arr = {userId};
			noattempt = dbUtil.executeQry(arr);
			
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
	public String[] getUserNoOfAttempts(String userId) throws Exception {
		String []noattempt = new String[2];
		DBUtility dbUtil = null;
		try
		{
			dbUtil = new DBUtility("");
			Connection conn=dbUtil.getDBConnection();
			
			Statement st=conn.createStatement();
			ResultSet rset=st.executeQuery(CommonSQL.GET_NUMBER_ATTEMPTS+"'"+userId+"'");
			while(rset.next())
			{
				noattempt[0]=rset.getString("USER_TYPE_ID");
				noattempt[1]=rset.getString("NO_OF_ATTEMPT_COUNT");
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
			dbUtil = new DBUtility("");
			Connection conn=dbUtil.getDBConnection();
			Statement st=conn.createStatement();
			sql =CommonSQL.GET_SYS_DATE;
			ResultSet rset=st.executeQuery(sql);
			while(rset.next())
			{
				timeCurrTimeStamp=rset.getString(1);
			}
			
			
			
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
						//String sql2=CommonSQL.GET_MAPPED_OFFICES+"'"+ofcId+"'";
						String[] arr = {ofcId};
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
public String[] authenticateUser(String userId, String pwd, String noattempt,String roleID)
	throws Exception {
String status[] = new String[3];
DBUtility dbUtil = null;
try {
	String roleFallBackQuery = "SELECT DISTINCT RM.ROLE_ID, RM.ROLE_NAME FROM   "+
								" IGRS_ROLE_MASTER RM, IGRS_ROLE_GROUP_MASTER GM, IGRS_RGROUP_ROLE_MAPPING GP, IGRS_USER_ROLE_GROUP_MAPPING UG "+
					" ,IGRS_OFFICE_MASTER OFF, IGRS_ROLE_OFFICE_MAPPING OFFROL  "+
					" WHERE RM.ROLE_ID = OFFROL.ROLE_ID AND GM.ROLE_GROUP_ID = GP.ROLE_GROUP_ID AND  "+
					" OFFROL.ROLE_OFFICE_MAP_ID=GP.ROLE_OFFICE_MAP_ID "+
					" AND RM.ROLE_STATUS = 'A'  AND GM.ROLE_GROUP_STATUS = 'A' AND  "+
					" UG.ROLE_GROUP_ID = GM.ROLE_GROUP_ID AND UG.USER_ID = ?  "; 
	dbUtil = new DBUtility("");
	Connection conn=dbUtil.getDBConnection();
	CallableStatement cst =conn.prepareCall("call IGRS_VALID_USER_2(?,?,?,?,?,?,?)");
	//dbUtil.createCallableStatement("call IGRS_VALID_USER_2(?,?,?,?,?,?,?)");
	cst.setString(1, userId);
	cst.setString(2, pwd);
	cst.setInt(3, Integer.parseInt(noattempt));
	cst.registerOutParameter(4, Types.VARCHAR);
     //clstmt.setString(5, roleID);
	cst.registerOutParameter(5, Types.VARCHAR);
	cst.registerOutParameter(6, Types.VARCHAR);
	cst.registerOutParameter(7, Types.VARCHAR);
	cst.execute();
	 status[0] = cst.getString(4);
     status[1] = cst.getString(5);
     status[2] = cst.getString(6);
	//status = dbUtil.authenticateUser(userId, pwd, noattempt,roleID);
	//clstmt=instance.createCallableStatement("call IGRS_VALID_USER_2(?,?,?,?,?,?,?)"); //added by roopam
	//status=instance.authenticateUser(userId, pwd, noattempt, roleID, clstmt);         //added by roopam
	String userStatus = status[0]; 
	String loggedInRole = status[1];
	String roleName = status[2];
	try {
		if (LoginConstant.DB_SUCCESSFULLY.equals(userStatus)) {
			if(loggedInRole == null) {
			PreparedStatement pst=conn.prepareStatement(roleFallBackQuery);
				//dbUtil.createPreparedStatement(roleFallBackQuery);
			pst.setString(1, userId);
			ResultSet rset=pst.executeQuery();
			/*ArrayList data = dbUtil.executeQuery(new String[] {userId});
				if(data != null || data.isEmpty() == false) {
					ArrayList row = (ArrayList)data.get(0);
					loggedInRole = (String) row.get(0);
					roleName = (String) row.get(1);
					
				}
*/			
			while(rset.next())
			{
				loggedInRole=rset.getString(1);
				roleName=rset.getString(2);
			}
			
			
			}
		}
		
	} catch (Exception e) {
		
	}
	loggedInRole = (loggedInRole == null)?"":loggedInRole;
	roleName = (roleName == null)?"":roleName;
	status[1] = loggedInRole;
	status[2] = roleName;
} catch (Exception x) {
	logger.debug(x);
} finally {
	try {
		if (dbUtil != null) {
			dbUtil.closeConnection();
		}
	}catch(Exception x) {
		logger.debug(x);
	}
}
return status;
}




public String[] authenticateUserSaltingMohit(String userId, String pwd, String noattempt,String roleID, String salt1, String salt2)
throws Exception {
String status[] = new String[3];
DBUtility dbUtil = null;
String userStatus = "";
String loggedInRole="";
String roleName="";
try {
String roleFallBackQuery = "SELECT DISTINCT RM.ROLE_ID, RM.ROLE_NAME FROM   "+
							" IGRS_ROLE_MASTER RM, IGRS_ROLE_GROUP_MASTER GM, IGRS_RGROUP_ROLE_MAPPING GP, IGRS_USER_ROLE_GROUP_MAPPING UG "+
				" ,IGRS_OFFICE_MASTER OFF, IGRS_ROLE_OFFICE_MAPPING OFFROL  "+
				" WHERE RM.ROLE_ID = OFFROL.ROLE_ID AND GM.ROLE_GROUP_ID = GP.ROLE_GROUP_ID AND  "+
				" OFFROL.ROLE_OFFICE_MAP_ID=GP.ROLE_OFFICE_MAP_ID "+
				" AND RM.ROLE_STATUS = 'A'  AND GM.ROLE_GROUP_STATUS = 'A' AND  "+
				" UG.ROLE_GROUP_ID = GM.ROLE_GROUP_ID AND UG.USER_ID = ?  "; 
dbUtil = new DBUtility("");
Connection conn=dbUtil.getDBConnection();

String[] arr = new String[1];
arr[0] = userId; 

dbUtil.createPreparedStatement("SELECT user_id FROM igrs_users_list WHERE user_id = ?");
ArrayList list = dbUtil.executeQuery(arr);

if(list!=null && !list.isEmpty())//Added by Mohit for Logging in.
{
	logger.debug("User with user_Id:- "+userId+" Exists.");
	
	dbUtil.createPreparedStatement("SELECT user_status FROM igrs_users_list WHERE user_id = ? AND nvl(user_status,   'NA') <> 'L'");
	ArrayList list1 = dbUtil.executeQuery(arr);
	if(list1!=null && !list1.isEmpty())
	{
		logger.debug("User with user_Id:- "+userId+" Exists and is not Locked.");
		dbUtil.createPreparedStatement("SELECT user_status FROM igrs_users_list  WHERE user_id = ?  AND nvl(user_status,   'NA') <> 'B'");
		ArrayList list2 = dbUtil.executeQuery(arr);
		
		if(list2!=null && !list2.isEmpty())
		{
			logger.debug("User with user_Id:- "+userId+" Exists and is not Blocked.");
			dbUtil.createPreparedStatement("SELECT user_status FROM igrs_users_list  WHERE user_id = ? AND nvl(user_status,   'NA') <> 'D'");
			ArrayList list3 = dbUtil.executeQuery(arr);
			
			if(list3!=null && !list3.isEmpty())
			{
				logger.debug("User with user_Id:- "+userId+" Exists and is not Deactivated.");
				
				dbUtil.createPreparedStatement("SELECT USER_TYPE FROM IGRS_USER_TYPE_MASTER WHERE USER_TYPE_ID =(SELECT USER_TYPE_ID FROM IGRS_USERS_LIST WHERE USER_ID =?)");
				
				
				String user_type_id = dbUtil.executeQry(arr);
				if(user_type_id!=null && !"".equalsIgnoreCase(user_type_id))
				{
				if(!user_type_id.equalsIgnoreCase("2")) //Check for internal user.
				{
					//In external Users.
					dbUtil.createPreparedStatement("SELECT user_status FROM igrs_user_reg_details  WHERE UPPER(user_id) = UPPER(?)");
					String status3 = dbUtil.executeQry(arr);
					
					if("A".equalsIgnoreCase(status3))
					{
						dbUtil.createPreparedStatement("SELECT user_password FROM igrs_user_reg_details  WHERE UPPER(user_id) = UPPER(?)");
						String password1 = dbUtil.executeQry(arr);
						
						if(password1!=null && !"".equalsIgnoreCase(password1)){
						
							//Password Checking Logic by Mohit
							String saltedPassword = salt1+password1+salt2;
							
							CryptoLibrary lib = new CryptoLibrary();
						String saltedEncyrptedPassword  =  lib.SHAencryption(saltedPassword);
						boolean check = false;
							if(pwd.equalsIgnoreCase(saltedEncyrptedPassword))
							{
								check = true ;
							}
							else
							{
								check = false;
							}
							
							
							if(check)
							{
								dbUtil.createPreparedStatement("UPDATE igrs_users_list SET LAST_ACCESS_DATE = (select sysdate from dual)  where UPPER(user_id) = UPPER(?)");
								dbUtil.executeUpdate(arr);
								
								userStatus = "IGRS_DB_USER_004";
								
								logger.debug("User with user_Id:- "+userId+" Exists And is Authenticated.");
								
							}
							else
							{
								userStatus = "IGRS_DB_USER_002";
								
								logger.debug("User with user_Id:- "+userId+" Exists but Password is Incorrect.");
							}
							
						}
						
					}
					else
					{
						
							userStatus = "IGRS_DB_USER_005";
							
							logger.debug("User with user_Id:- "+userId+" Exists but is Deactivated.");
						
					}
					
				}//End of check for internal user.
				else //For other users
				{
					dbUtil.createPreparedStatement("SELECT user_password FROM igrs_user_reg_details  WHERE UPPER(user_id) = UPPER(?)");
					String password1 = dbUtil.executeQry(arr);
					int noAttempts = Integer.parseInt(noattempt);
					
					if(noAttempts>3)//Attempts . This can be a configrable parameter //Added by Mohit
					{
						dbUtil.createPreparedStatement("UPDATE igrs_users_list  SET user_status = 'L' WHERE user_id = ?");
						boolean check1 = dbUtil.executeUpdate(arr);
						userStatus = "IGRS_DB_USER_006";
						
						logger.debug("User with user_Id:- "+userId+" Exists but its attempts are more than 3.");
					}
					else if (noAttempts==3)
					{
						
						dbUtil.createPreparedStatement("UPDATE igrs_users_list SET user_status = 'L' WHERE user_id = ?");
						boolean check2 = dbUtil.executeUpdate(arr);
						userStatus = "IGRS_DB_USER_004";
						
					}
					else
					{
						if(password1!=null && !"".equalsIgnoreCase(password1))
						{
							String saltedPassword = salt1+password1+salt2;
							
							CryptoLibrary lib = new CryptoLibrary();
						String saltedEncyrptedPassword  =  lib.SHAencryption(saltedPassword);
						boolean check = false;
							if(pwd.equalsIgnoreCase(saltedEncyrptedPassword))
							{
								check = true ;
							}
							else
							{
								check = false;
							}
							
							
							if(check)
							{
								userStatus = "IGRS_DB_USER_004";
							}
							else
							{
								userStatus = "IGRS_DB_USER_002";
							}
						}
						
						
						
					}
						
					}
					
				}
				else
				{
					userStatus = "IGRS_DB_USER_007";
					logger.debug("USER TYPE NOT DEFINED WHETHER HE/SHE IS INTERNAL OR EXTERNAL USER");
				}
			}
			else
			{
				userStatus = "IGRS_DB_USER_005";
				logger.debug("USER is Deactivated..");
			}
				
		}
			else
			{
				
				userStatus = "IGRS_DB_USER_010";
				
				logger.debug("User with user_Id:- "+userId+" Exists but is Blocked.");
				
				
				
			}
			
		}
		else
		{
			userStatus = "IGRS_DB_USER_003";
			
			logger.debug("User with user_Id:- "+userId+" Exists but is Locked.");
			
			
			
		}
		
		
	}
	

else
{
	
	userStatus = "IGRS_DB_USER_001";
	logger.debug("User with user_Id:- "+userId+"Does not Exists.");
}

if(userStatus.equalsIgnoreCase("IGRS_DB_USER_004"))
{
	dbUtil.createPreparedStatement("UPDATE IGRS_USERS_LIST SET USER_STATUS='A', NO_OF_ATTEMPT_COUNT=0 WHERE UPPER(user_id) = UPPER(?)");
	dbUtil.executeUpdate(arr);
	
}


/*CallableStatement cst =conn.prepareCall("call IGRS_VALID_USER_2(?,?,?,?,?,?,?)");
//dbUtil.createCallableStatement("call IGRS_VALID_USER_2(?,?,?,?,?,?,?)");
cst.setString(1, userId);
cst.setString(2, pwd);
cst.setInt(3, Integer.parseInt(noattempt));
cst.registerOutParameter(4, Types.VARCHAR);
 //clstmt.setString(5, roleID);
cst.registerOutParameter(5, Types.VARCHAR);  //CommentedByMohit
cst.registerOutParameter(6, Types.VARCHAR);
cst.registerOutParameter(7, Types.VARCHAR);
cst.execute();
 status[0] = cst.getString(4);
 status[1] = cst.getString(5);
 status[2] = cst.getString(6);
//status = dbUtil.authenticateUser(userId, pwd, noattempt,roleID);
//clstmt=instance.createCallableStatement("call IGRS_VALID_USER_2(?,?,?,?,?,?,?)"); //added by roopam
//status=instance.authenticateUser(userId, pwd, noattempt, roleID, clstmt);         //added by roopam
 userStatus = status[0]; 
 loggedInRole = status[1];
 roleName = status[2];*/
status[0] = userStatus;
try {
	if (LoginConstant.DB_SUCCESSFULLY.equals(userStatus)) {
		if(loggedInRole == null) {
		PreparedStatement pst=conn.prepareStatement(roleFallBackQuery);
			//dbUtil.createPreparedStatement(roleFallBackQuery);
		pst.setString(1, userId);
		ResultSet rset=pst.executeQuery();
		/*ArrayList data = dbUtil.executeQuery(new String[] {userId});
			if(data != null || data.isEmpty() == false) {
				ArrayList row = (ArrayList)data.get(0);
				loggedInRole = (String) row.get(0);
				roleName = (String) row.get(1);
				
			}
*/			
		while(rset.next())
		{
			loggedInRole=rset.getString(1);
			roleName=rset.getString(2);
		}
		
		
		}
	}
	
} catch (Exception e) {
	
}
loggedInRole = (loggedInRole == null)?"":loggedInRole;
roleName = (roleName == null)?"":roleName;
status[1] = loggedInRole;
status[2] = roleName;
} catch (Exception x) {
logger.debug(x);
} finally {
try {
	if (dbUtil != null) {
		dbUtil.closeConnection();
	}
}catch(Exception x) {
	logger.debug(x);
}
}
return status;
}


public String[] getRole(String userId)
{
	DBUtility dbUtil = null;
	String roleFallBackQuery = "SELECT DISTINCT RM.ROLE_ID, RM.ROLE_NAME FROM   "+
	" IGRS_ROLE_MASTER RM, IGRS_ROLE_GROUP_MASTER GM, IGRS_RGROUP_ROLE_MAPPING GP, IGRS_USER_ROLE_GROUP_MAPPING UG "+
" ,IGRS_OFFICE_MASTER OFF, IGRS_ROLE_OFFICE_MAPPING OFFROL  "+
" WHERE RM.ROLE_ID = OFFROL.ROLE_ID AND GM.ROLE_GROUP_ID = GP.ROLE_GROUP_ID AND  "+
" OFFROL.ROLE_OFFICE_MAP_ID=GP.ROLE_OFFICE_MAP_ID "+
" AND RM.ROLE_STATUS = 'A'  AND GM.ROLE_GROUP_STATUS = 'A' AND  "+
" UG.ROLE_GROUP_ID = GM.ROLE_GROUP_ID AND UG.USER_ID = ?  "; 
	
	dbUtil = new DBUtility("");
	Connection conn = null;
	String loggedInRole = "";
	String roleName="";
	try {
		conn = dbUtil.getDBConnection();
	
	PreparedStatement pst=conn.prepareStatement(roleFallBackQuery);
	pst.setString(1, userId);
	ResultSet rset=pst.executeQuery();
	
	while(rset.next())
	{
		loggedInRole = rset.getString(1);
		roleName = rset.getString(2);
	}
	
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally
	{
		try {
			conn.close();
			if(dbUtil!=null)
			dbUtil.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return new String[]{loggedInRole,roleName};
	
}

public ArrayList<Map<String, String>> getCompleteMenuDetails(String[] roles) {
	ArrayList<Map<String, String>> retVal = null;
	try {
		retVal = getCompleteMenuDetails1(roles);
	} catch (Exception e) {
		e.printStackTrace();
	}

	return retVal;
}
public ArrayList<Map<String, String>> getCompleteMenuDetails1(String[] roles) {
	ArrayList<Map<String, String>> retVal = null;
	try {
		Map<String, String> mdlMap = new HashMap<String, String>(50, 25);
		Map<String, String> smlMap = new HashMap<String, String>(50, 25);
		Map<String, String> fncMap = new HashMap<String, String>(50, 25);
		Map<String, String> actMap = new HashMap<String, String>(50, 25);
		retVal = new ArrayList<Map<String, String>>(4);
		StringBuilder stBldr = new StringBuilder();
		String sqlText = "";
		stBldr.append(CommonSQL.SEARCH_ROLE_MODULE);
		stBldr.append(" AND RFN.ROLE_ID IN ( ");
		stBldr.append(buildInClause(roles));
		stBldr.append(" ) ");
		sqlText = stBldr.toString();
		logger.info("Getting modules");
		logger.debug("SQL is : " + sqlText);
		buildMenuMap(mdlMap, sqlText);
		logger.info("Module Count " + mdlMap.keySet().size());
		stBldr.delete(0, stBldr.length());
		stBldr.append(CommonSQL.SEARCH_SUB_MODULE);
		stBldr.append(" AND RFN.MODULE_ID IN ( ");
		stBldr.append(buildInClause(mdlMap.keySet().toArray(new String[0])));
		stBldr.append(" ) ");
		stBldr.append(" AND RFN.ROLE_ID IN ( ");
		stBldr.append(buildInClause(roles));
		stBldr.append(" ) ");
		sqlText = stBldr.toString();
		logger.info("Getting sub modules");
		logger.debug("SQL is : " + sqlText);
		buildMenuMap(smlMap, sqlText);
		logger.info("Sub Module Count " + smlMap.keySet().size());
		stBldr.delete(0, stBldr.length());
		stBldr.append(CommonSQL.SEARCH_FUNCTION);
		stBldr.append(" AND RFN.SUB_MODULE_ID IN ( ");
		stBldr.append(buildInClause(smlMap.keySet().toArray(new String[0])));
		stBldr.append(" ) ");
		stBldr.append(" AND RFN.MODULE_ID IN ( ");
		stBldr.append(buildInClause(mdlMap.keySet().toArray(new String[0])));
		stBldr.append(" ) ");
		stBldr.append(" AND RFN.ROLE_ID IN ( ");
		stBldr.append(buildInClause(roles));
		stBldr.append(" ) ");
		sqlText = stBldr.toString();
		logger.info("Getting Function");
		logger.debug("SQL is : " + sqlText);
		buildMenuMap(fncMap, sqlText);	
		logger.info("Function Count " + fncMap.keySet().size());
		stBldr.delete(0, stBldr.length());
		stBldr.append(CommonSQL.SEARCH_ACTIVITIES);
		stBldr.append(" AND RFN.FUNCTION_ID IN ( ");
		stBldr.append(buildInClause(fncMap.keySet().toArray(new String[0])));
		stBldr.append(" ) ");
		stBldr.append(" AND RFN.SUB_MODULE_ID IN ( ");
		stBldr.append(buildInClause(smlMap.keySet().toArray(new String[0])));
		stBldr.append(" ) ");
		stBldr.append(" AND RFN.MODULE_ID IN ( ");
		stBldr.append(buildInClause(mdlMap.keySet().toArray(new String[0])));
		stBldr.append(" ) ");
		stBldr.append(" AND RFN.ROLE_ID IN ( ");
		stBldr.append(buildInClause(roles));
		stBldr.append(" ) ");
		sqlText = stBldr.toString();
		logger.info("Getting Activities");
		logger.debug("SQL is : " + sqlText);
		buildMenuMap(actMap, sqlText);	
		logger.info("Activity Count " + actMap.keySet().size());
		// MDL.MODULE_ID, MDL.MODULE_NAME,
		// SML.SUB_MODULE_ID, SML.SUB_MODULE_NAME,
		// FNC.FUNCTION_ID, FNC.FUNCTION_NAME,
		// ACT.ACTIVITY_ID, ACT.ACTIVITY_NAME
		retVal.add(mdlMap);
		retVal.add(smlMap);
		retVal.add(fncMap);
		retVal.add(actMap);
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
	}

	return retVal;
}
private String buildInClause(String[] params) {
	StringBuilder stBldr = new StringBuilder();
	for (int iLoop = 0; iLoop < params.length; iLoop++) {
		stBldr.append("\'");
		stBldr.append(params[iLoop]);
		stBldr.append("\'");
		if (iLoop < (params.length - 1)) {
			stBldr.append(", ");
		}
	}
	return stBldr.toString();
}
private void buildMenuMap(Map<String, String> genMap, String sqlText) {
	try {
		DBUtility dbUtil = null;
		Connection connection=null;
		try {
			
			dbUtil=new DBUtility(""); 
			 connection=dbUtil.getDBConnection();
			Statement st=connection.createStatement();
			ResultSet rst = st.executeQuery(sqlText);
			while (rst.next()) {
				genMap.put(rst.getString(1), rst.getString(2));
			}
			rst.close();
			st.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			dbUtil.closeConnection();
			connection.close();
//			st.close();
		}
	} catch (Exception e) {
		logger.error("Error occured while cleaning ResultSet " + e.getMessage());
	}
}

public ArrayList getRoleID(String userID) throws Exception {
	ArrayList list = new ArrayList();
	DBUtility dbUtil = null;
	try {
		dbUtil = new DBUtility("");
		Connection conn=dbUtil.getDBConnection();
		Map map1 = new HashMap();
		ResultSet rset;
		CallableStatement clstmt =conn.prepareCall("call igrs_valid_user_role_new(?,?,?)");

		//clstmt=instance.createCallableStatement("call IGRS_VALID_USER_ROLE(?,?,?)");        //added by roopam
		clstmt.setString(1, userID);

		clstmt.registerOutParameter(2, OracleTypes.CURSOR); 
		clstmt.registerOutParameter(3, OracleTypes.CURSOR);

		clstmt.execute();
		rset = (ResultSet)clstmt.getObject(2);
//		while (rset.next()) {
//			map1.put(rset.getString(1), rset.getString(2));
//		}
		rset.close();
		rset = (ResultSet)clstmt.getObject(3);
		while (rset.next()) {
			map1.put(rset.getString(2), rset.getString(3));
		}
		rset.close();
		clstmt.close();
//		if (!clstmt.execute()) {
//			logger.debug(clstmt.getString(2) + ":" + clstmt.getString(3));
//			list.add(clstmt.getString(2));
//			list.add(clstmt.getString(3));
//		}
		list.add(map1);
	} catch (Exception x) {
		logger.debug(x);
	} finally {
		try {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			
		}catch(Exception x) {
			logger.debug(x);
		}
	}
	return list;
}
public String getTypeOfUser(String uid) throws Exception{
	String typeOfUser="";
	DBUtility dbUtil = null;
	try
	{
	dbUtil= new DBUtility("");	
	Connection conn=dbUtil.getDBConnection();
	
	String sql =com.wipro.igrs.login.sql.CommonSQL.GET_USER_TYPE;
	PreparedStatement pst=conn.prepareStatement(sql);
	pst.setString(1, uid);
	/*String[] temp = new String[1];
	temp[0]=uid;*/
	//dbUtil.createPreparedStatement(sql);
	 //typeOfUser=dbUtil.executeQry(temp);
	ResultSet rset=pst.executeQuery();
	while(rset.next())
	{
		typeOfUser=rset.getString(1);
	}
	}
	catch(Exception e)
	{
		e.getStackTrace();
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
	return typeOfUser;
}

/**
 * Added by Simran for getting Sr Name, designation,office and signature details
 * @param userId
 * @return
 * @throws Exception
 */
public ArrayList getLoggedInUserDetls(String userId) throws Exception {
	ArrayList list=new ArrayList();
	DBUtility dbUtil = null;
    try {
    	dbUtil = new DBUtility();
    //	dbUtil.createStatement();
    	String sql=CommonSQL.GET_LOGGED_IN_USER_DETLS;
    	dbUtil.createPreparedStatement(sql);
		String offcArr[] = {userId};
		list = dbUtil.executeQuery(offcArr);
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

public String getLoggedInOffice(String officeId, String lang)
{
	String offcDetails = "";
	DBUtility dbUtil = null;	
	try {
		dbUtil = new DBUtility(); 
		String sql = "";
		if(lang.equalsIgnoreCase("en"))
			sql = CommonSQL.GET_OFFC_NAME;
		else
			sql = CommonSQL.GET_OFFC_NAME_HI;
		dbUtil.createPreparedStatement(sql);
		String offcArr[] = {officeId};
		offcDetails = dbUtil.executeQry(offcArr);
	}catch (Exception exception) {
		logger.debug("Exception in getSystemDate" + exception);
	}
	finally{
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			logger.error("close connection" + e.getStackTrace());
		}
	}
	return offcDetails;
}

public String getLoggedInDesignation(String userId, String lang)
{DBUtility dbUtil = null;
	String desg = "";
	try {
		dbUtil = new DBUtility(); 
		String sql = "";
		if(lang.equalsIgnoreCase("en"))
			sql = CommonSQL.GET_DESIGNATION_DETLS;
		else
			sql = CommonSQL.GET_DESIGNATION_DETLS_HI;
		dbUtil.createPreparedStatement(sql);
		String offcArr[] = {userId};
		desg = dbUtil.executeQry(offcArr);
	}catch (Exception exception) {
		logger.debug("Exception in getSystemDate" + exception);
	}
	finally{
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			logger.error("close connection" + e.getStackTrace());
		}
	}
	return desg;
}
}
