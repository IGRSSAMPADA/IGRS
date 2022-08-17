/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserRGroupMappingIGRSException.java
 * Author      :   Hend M. ismail
 * Description :   Represents the Exception Class for User RoleGroup Mappings Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  20th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.userrolegroupmapping.exception;

import com.wipro.igrs.exception.IGRSException;

public class UserRGroupMappingIGRSException extends IGRSException {
	public UserRGroupMappingIGRSException() {
	}

	/**
	 * @param str
	 */
	public UserRGroupMappingIGRSException(String str ) {
		super(str);
	}


}
