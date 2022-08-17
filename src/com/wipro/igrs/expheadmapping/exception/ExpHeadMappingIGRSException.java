/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ExpHeadMappingIGRSException.java
 * Author      :   Hend M. ismail
 * Description :   Represents the Exception Class for UserType  Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  20th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.expheadmapping.exception;

import com.wipro.igrs.exception.IGRSException;

public class ExpHeadMappingIGRSException extends IGRSException {
	public ExpHeadMappingIGRSException() {
	}

	/**
	 * @param str
	 */
	public ExpHeadMappingIGRSException(String str ) {
		super(str);
	}


}
