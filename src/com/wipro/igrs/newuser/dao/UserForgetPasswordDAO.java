
/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserForgetPasswordDAO.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the DAO Class for UserForgetPassword.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  13th March, 2008	 		 
 * --------------------------------------------------------------------------------
 * 2.0             Roopam Mehta   10th October, 2012	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.newuser.dao;
import java.sql.CallableStatement;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;

import com.wipro.igrs.UserRegistration.action.CryptoLibrary;
import com.wipro.igrs.UserRegistration.constant.CommonConstant;
import com.wipro.igrs.UserRegistration.sql.CommonSQL;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.newuser.dto.UserForgetPasswordDTO;
import com.wipro.igrs.newuser.sql.UserForgetPasswordCommonSQL;
//import com.wipro.igrs.Log4J.LoggerMsg;
import com.wipro.*;


public class UserForgetPasswordDAO {
	
	
	
	
	private ArrayList hquestionList = new ArrayList();
    private DBUtility dbUtility = null;
    private String sql = null;
    private ArrayList subList = null;
    private UserForgetPasswordDTO dto = null;
    private CallableStatement clstmnt = null; 
    private Logger logger = 
		(Logger) Logger.getLogger(UserForgetPasswordDAO.class);
    /*DAO constructor*/
    public UserForgetPasswordDAO() {
 
    }
    
    public String[] getUserNameSaltingByMohit(String strUserName,String strhQustion,String srtAnswer,String salt1,String salt2) throws Exception
    {
    	String param[] = new String[2];
    	param[0] = strUserName;
    	param[1] = strhQustion;
    	
    	String params[]= new String[4];
    	try{
    		//logger.info("we are in userName method");
    	 dbUtility=new DBUtility();
    	dbUtility.createPreparedStatement("SELECT EMAIL_ID, USER_HINT_ANSWER, IGRS_USER_REG_DETAILS.USER_STATUS FROM IGRS_USERS_LIST JOIN IGRS_USER_REG_DETAILS ON IGRS_USERS_LIST.USER_ID =IGRS_USER_REG_DETAILS.USER_ID AND IGRS_USER_REG_DETAILS.USER_ID=?  AND USER_HINT_QUESTION_ID=?");
    	ArrayList li = dbUtility.executeQuery(param); 
    	if(li!=null && !li.isEmpty() )
    	{
    		if(li.size()==1)
    		{
    			ArrayList lis = (ArrayList) li.get(0);
    			
    			String email = (String)lis.get(0);
    			String pass = (String)lis.get(1);
    			String status = (String)lis.get(2);
    			
    			
    			String saltpass = salt1+pass+salt2;
    			CryptoLibrary lib = new CryptoLibrary();
    			String saltPasssEncyrypted = lib.SHAencryption(saltpass);
    			
    			params[0] = email;
    			params[1] = pass;
    			params[2] = status;
    		
    			
    			if(!saltPasssEncyrypted.equalsIgnoreCase(srtAnswer))
    			{
    				
    				
    			
    		
    				params[3] = "DB_NO_DATA_FOUND";
    			
    			}
    			
    		}
    		else
    		{
    			params[3] = "TO MANY VALUES FOR THE SAME DATA";
    		}
    	
    		
    		
    	}
    	
    	
    }
   catch (Exception e) {
	logger.debug(e.getMessage(),e);
}
		return params;
   
    }
    public String[] getUserName(String strUserName,String strhQustion,String srtAnswer) throws Exception
    {
    	String param[] = new String[4];
    	try{
    		//logger.info("we are in userName method");
    	 dbUtility=new DBUtility();
    	 sql = UserForgetPasswordCommonSQL.CALL_FORGOT_PWD;
    	 clstmnt = dbUtility.returnCallableStatement(sql);
    	 clstmnt.setString(1, strUserName);
    	 clstmnt.setString(2, strhQustion);
    	 clstmnt.setString(3, srtAnswer);
    	 
    	 clstmnt.registerOutParameter(4, OracleTypes.VARCHAR);
		 clstmnt.registerOutParameter(5, OracleTypes.VARCHAR);
		 clstmnt.registerOutParameter(6, OracleTypes.VARCHAR);
		 clstmnt.registerOutParameter(7, OracleTypes.VARCHAR);

			if (!clstmnt.execute()) {
				 
				param[0] = clstmnt.getString(4);
				param[1] = clstmnt.getString(5);
				param[2] = clstmnt.getString(6);
				param[3] = clstmnt.getString(7);
			}
    	 
    	
    	 
    	}catch(Exception e){
    	        e.getStackTrace();
    	}finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
    	 return param;
    }
  

       /* GET GETHQUESTIONLIST LIST */
    
    public ArrayList GetHquestionList(String locale) throws Exception {
        try {
        	//sql = UserForgetPasswordCommonSQL.SELECT_USER_HQUESION_ID ;
        	sql = UserForgetPasswordCommonSQL.SELECT_USER_HQUESION_ID_HINDI ;
        	dbUtility=new DBUtility();
            dbUtility.createStatement();
           
            ArrayList mainList1= dbUtility.executeQuery(sql);
            for (int i=0;i<mainList1.size();i++) 
             {
            	subList = (ArrayList)mainList1.get(i);
              	dto = new UserForgetPasswordDTO();
              	if(locale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
            	dto.setName(subList.get(1).toString());
              	}else{
              		dto.setName(subList.get(2).toString());
              	}
            	
            	dto.setValue(subList.get(0).toString());
            	hquestionList.add(dto);
              }
         } catch (Exception e) {
              e.getStackTrace();
         }finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
        return hquestionList;
    }
    
    /*
     *@author : Roopam
     *@description: for inserting contents in IGRS_EMAIL_DATA_CONTENT table
     */
    
    
    public String insertEmailData(String userId, String subject, String content, String uniqueUserKey, String blockFlag)
	throws Exception {
    	
    String status = new String();
    String[] param=new String[4];
    param[0]=userId;
    param[1]=uniqueUserKey;
    param[2]=blockFlag;
    param[3]=userId;
    
    boolean userDetailsSubmit=false;
     try {
    	 logger.debug("Before inserting user details ");
    	 dbUtility = new DBUtility();
    	 dbUtility.createPreparedStatement(CommonSQL.INSERT_RESET_PASWRD_REQ_DETLS);
			userDetailsSubmit = dbUtility.executeUpdate(param);
    	 if(userDetailsSubmit)
    	 {	 
    	 dbUtility.createCallableStatement(CommonSQL.CALL_IGRS_INSERT_EMAIL_DATA);
	     status = dbUtility.insertEmailData(userId, subject, content);
	     System.out.println("STATUS --->");
	     System.out.println("STATUS --->" + status);
    	 }
    	 else{
    		 status="E";
    	 }
     } catch (Exception x) {
	logger.debug(x);
	x.printStackTrace();
     } finally {
    	 dbUtility.closeConnection();
     }
    return status;
}
    
   }
