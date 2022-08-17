/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserRegistrationRule.java
 * Author      :   Nihar Ranjan Mishra 
 * Description :   Represents the server side validations for UserRegistration
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Nihar R Mishra  28th Dec, 2007	 		 
 * --------------------------------------------------------------------------------
 */


package com.wipro.igrs.UserRegistration.rule;


import java.util.ArrayList;

import org.apache.log4j.Logger;
//import com.wipro.igrs.UserRegistration.constant.CommonConstant;
import com.wipro.igrs.UserRegistration.dto.UserRegistrationDTO;
import com.wipro.igrs.UserRegistration.util.PropertiesFileReader;
import com.wipro.igrs.UserRegistration.dao.UserRegistrationDAO;
//import com.wipro.igrs.UserRegistration.form.UserRegistrationForm;


/**
 * @author nihraa
 *
 */
public class UserRegistrationRule {

	private boolean error;
	private PropertiesFileReader pr;
	private Logger logger = 
		(Logger) Logger.getLogger(UserRegistrationRule.class);
	
	public ArrayList validateUserName(UserRegistrationDTO userObjForm)
	{
		String param[] = new String[1];
		ArrayList errorList = new ArrayList();
		boolean flag = false;

		try {
			pr = PropertiesFileReader.getInstance("resources.igrs");
			if (userObjForm != null) {
				param[0] = userObjForm.getUserId();
				logger.debug("PARAM[0]:-     "+param[0]);
				UserRegistrationDAO dao = new UserRegistrationDAO();
				ArrayList lst = dao.checkUserName(param);
				logger.debug("lst:"+lst);
				if (lst != null) {
					logger.debug("lst size:"+lst.size());
					if ((lst.size() > 0)  ) {
						
						logger.debug("if Part");
						/*String user=((ArrayList)(lst.get(0))).get(0)!=null? ((ArrayList)(lst.get(0))).get(0).toString():""; 
						
						if(user.equalsIgnoreCase(userObjForm.getUserId())){*/
						errorList.add(pr.getValue("error.isuservalid.no")); 
						flag = true;
						//}
					}
					else
					{
						logger.debug("Else Part");
						errorList.add(pr.getValue("error.isuservalid.yes")); 
						flag = false;
					}
						
				}

			}
		} catch (Exception x) {
			logger.debug(x);
		}
		setError(flag);
		 
		return errorList;
	}
	/**
	 * @param error
	 */
	public void setError(boolean error) {
		this.error = error;
	}
	/**
	 * @return boolean
	 */
	public boolean isError() {
		return error;
	}
}
