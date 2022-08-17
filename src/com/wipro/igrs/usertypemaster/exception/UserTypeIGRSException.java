/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserTypeAction.java
 * Author      :   Hend M. ismail
 * Description :   Represents the Exception Class for UserType  Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  12th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.usertypemaster.exception;

import com.wipro.igrs.exception.IGRSException;

public class UserTypeIGRSException extends IGRSException {
	public UserTypeIGRSException() {
	}

	/**
	 * @param str
	 */
	public UserTypeIGRSException(String str ) {
		super(str);
	}


}
