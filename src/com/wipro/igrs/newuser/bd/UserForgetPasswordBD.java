/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserForgetPasswordBD.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the BD Class for UserForgetPassword.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  13th March, 2008	 		 
 * --------------------------------------------------------------------------------
 * 2.0             Roopam Mehta   10th October, 2012	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.newuser.bd;

import java.util.ArrayList;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.wipro.igrs.baseaction.bd.LoginBD;
import com.wipro.igrs.baseaction.dao.User;
import com.wipro.igrs.newuser.dao.UserForgetPasswordDAO;
import com.wipro.igrs.newuser.dto.UserForgetPasswordDTO;
import com.wipro.igrs.newuser.form.UserForgetPasswordForm;
//import com.wipro.igrs.Log4J.LoggerMsg;

public class UserForgetPasswordBD {
	private Logger logger = (Logger) Logger.getLogger(LoginBD.class);
	
	public UserForgetPasswordBD()
	{
		//LoggerMsg.info("we are in UserForgetPasswordBD");
		}
	UserForgetPasswordDAO userforgetdao = new UserForgetPasswordDAO();
	 /**
	* get the list of selected user
	* @throws Exception
	*/
	
	public String[] getUserName(String strUserName,String strhQustion,String srtAnswer,String salt1,String salt2) throws Exception
	{
		return userforgetdao.getUserNameSaltingByMohit(strUserName,strhQustion,srtAnswer, salt1, salt2);
	}
	 /**
	* Gets the list of HintquestionList
    * @param  ArrayList of HintquestionList
	* @return ArrayList of UserForgetPasswordDTO
	* @throws Exception
    */
	public ArrayList GetHquestionList(String locale) throws Exception
	{
		return userforgetdao.GetHquestionList(locale);
	}
	
	public String insertEmailData(String userId, String subject, String content, String uniqueUserKey, String blockFlag)
	throws Exception {
        String status = new String();
        try {
	           
	           status  = userforgetdao.insertEmailData(userId, subject, content, uniqueUserKey, blockFlag);

             } catch (Exception x) {
	               logger.error(x);
               }
           return status;
        }
	
}
