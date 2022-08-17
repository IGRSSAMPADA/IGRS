package com.wipro.igrs.db;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.caveats.dao.CaveatsDAO;

public class OTPDao {

	private static Logger logger = 
		(Logger) Logger.getLogger(CaveatsDAO.class);
	
public boolean updateOTPModuleId(String referenceId,String moduleId)	
{
	DBUtility dbutil =null;
	boolean boo = false;
	try {
		String arr[] = new String[]{referenceId,moduleId};
		dbutil = new DBUtility();
		dbutil.createPreparedStatement(OTPSQL.IGRS_UPDATE_OTP);
		 boo = dbutil.executeUpdate(arr);
		
		
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}
	finally
	{
		try {
			dbutil.closeConnection();
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
	}
	
	return boo;
	
}

public boolean checkOTP(String otp) {
	DBUtility dbutil =null;
	 ArrayList li =null;
	boolean boo = false;
	try {
		String arr[] = new String[]{otp};
		dbutil = new DBUtility();
		 dbutil.createPreparedStatement(OTPSQL.IGRS_CHECK_OTP);
		
	li	= dbutil.executeQuery(arr);
		
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}
	finally
	{
		try {
			dbutil.closeConnection();
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
	}
	
	if(li.size()>0)
	return true;
	else
		return false;
	
	
	
	
}

public String getEmail(String userId) {
	DBUtility dbutil =null;
	String email="";
	try {
		String arr[] = new String[]{userId};
		dbutil = new DBUtility();
		dbutil.createPreparedStatement(OTPSQL.IGRS_GET_EMAIL);
		email = dbutil.executeQry(arr);
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}
	finally
	{
		try {
			dbutil.closeConnection();
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
	}
	return email;
}
	
public String getMobile(String userId) {
	DBUtility dbutil =null;
	String mobile="";
	try {
		String arr[] = new String[]{userId};
		dbutil = new DBUtility();
		dbutil.createPreparedStatement(OTPSQL.IGRS_GET_MOBILE);
		mobile = dbutil.executeQry(arr);
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}
	finally
	{
		try {
			dbutil.closeConnection();
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
	}
	return mobile;
}

public boolean insertIntoOtpMaster(String userId, String referenceId,
		String moduleId, String otp, String email, String mobileNo) {
	DBUtility dbutil =null;
	boolean boo = false;
	
	try {
		dbutil = new DBUtility();
		String arr[] = new String[]{referenceId,moduleId,otp,userId,mobileNo,email};
		dbutil.createPreparedStatement(OTPSQL.IGRS_INSERT_OTP);
		boo = dbutil.executeUpdate(arr);
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}
	finally
	{
		try {
			dbutil.closeConnection();
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
	}
	
	return boo;
}

public boolean updateMobileStatus(String referenceId, String moduleId,
		String userId) {
	DBUtility dbutil =null;
	boolean boo = false;
	try {
		String arr[] = new String[]{referenceId,moduleId,userId};
		dbutil = new DBUtility();
		dbutil.createPreparedStatement(OTPSQL.IGRS_UPDATE_SMS_SENT);
		boo = dbutil.executeUpdate(arr);
		
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}
	finally
	{
		try {
			dbutil.closeConnection();
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
	}
	return boo;
}
	

public boolean updateEmailStatus(String referenceId, String moduleId,
		String userId) {
	DBUtility dbutil =null;
	boolean boo = false;
	try {
		String arr[] = new String[]{referenceId,moduleId,userId};
		dbutil = new DBUtility();
		dbutil.createPreparedStatement(OTPSQL.IGRS_UPDATE_EMAIL_SENT);
		boo = dbutil.executeUpdate(arr);
		
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}
	finally
	{
		try {
			dbutil.closeConnection();
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
	}
	return boo;
}

public boolean validateOTP(String referenceId, String moduleId, String userId, String otp) {
	DBUtility dbutil =null;
	boolean boo = false;
	ArrayList li = null;
	String id="";
	String arr1[] = new String[1];
	try {
		String arr[] = new String[]{referenceId,moduleId,userId,otp};
		dbutil = new DBUtility();
		dbutil.createPreparedStatement(OTPSQL.IGRS_VALIDATE_OTP);
		li =   dbutil.executeQuery(arr);
		if(li.size()>0)
		{
			ArrayList l = (ArrayList) li.get(0);
			 id = l.get(0).toString();
			 arr1[0] = id;
			dbutil.createPreparedStatement(OTPSQL.IGRS_TIME_VALIDATE_OTP);
		String t = 	dbutil.executeQry(arr1);
		double time = Double.parseDouble(t);
		
		if(time>5 || time<0)
		{
			return false;
		}
		else 
		{
			dbutil.createPreparedStatement(OTPSQL.IGRS_CONSUME_OTP);
			boolean boos = dbutil.executeUpdate(arr1);
			if(boos)
			return true;
			else
				return false;
		}
		}
		else
		{
			return false;
		}
		
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}
	finally
	{
		try {
			dbutil.closeConnection();
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
	}
	return boo;
}
	
	
}
