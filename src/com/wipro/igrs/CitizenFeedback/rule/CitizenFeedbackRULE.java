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


package com.wipro.igrs.CitizenFeedback.rule;


import java.util.ArrayList;

import com.wipro.igrs.CitizenFeedback.dao.CitizenFeedbackViewDAO;
import com.wipro.igrs.CitizenFeedback.dto.CitizenFeedbackDTO;
import com.wipro.igrs.util.PropertiesFileReader;


/**
 * @author NIHAR M.
 *
 */
public class CitizenFeedbackRULE {

	private boolean error;
	PropertiesFileReader pr;

	public ArrayList validateFeedbackID(CitizenFeedbackDTO objDTO)
	{
		String param[] = new String[1];
		ArrayList errorList = new ArrayList();
		boolean flag = false;

		try {
			pr = PropertiesFileReader.getInstance("resources.igrs");
			
			System.out.println(objDTO.getFirstName());
			errorList.add(pr.getValue("error.header"));
			if ("satbir".equalsIgnoreCase(objDTO.getFirstName())) {
						errorList.add(pr.getValue("error.firstname")); 
						flag = false;
						System.out.println(pr.getValue("error.firstname"));
					}
			
			
					
		} catch (Exception x) {
			System.out.println(x);
			
			
		}
		setError(flag);
		System.out.println(flag);
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
