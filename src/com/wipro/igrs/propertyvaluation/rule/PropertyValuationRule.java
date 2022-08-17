package com.wipro.igrs.propertyvaluation.rule;
/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :  PropertyValuationRule.java
 * Author      :  Madan Mohan 
 * Description :   
*/


import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.propertyvaluation.constant.CommonConstant;
import com.wipro.igrs.propertyvaluation.dto.PropertyValuationDTO;
import com.wipro.igrs.propertyvaluation.form.PropertyValuationForm;
import com.wipro.igrs.propertyvaluation.util.PropertiesFileReader;


/**
 * @author Madan Mohan
 * @since 20 Feb 2008
 * Description : created for server side validation
 */
public class PropertyValuationRule {

	private boolean error;
	private PropertiesFileReader pr;
	private Logger logger = (Logger) Logger
	.getLogger(PropertyValuationRule.class);

	public PropertyValuationRule() {

	}
	protected boolean nullOrBlank(String str) {
		return ((str == null) || (str.length() == 0));
	}
	public ArrayList validatePropertyPage(Object objForm) {
		boolean flag = false;
		logger.debug("inside PropertyValuaionRule");
		PropertyValuationForm form = (PropertyValuationForm) objForm;
		PropertyValuationDTO dto = form.getPropertyDTO();

		logger.debug("*******" + dto.getAge());
		ArrayList errorList = new ArrayList();
		try {
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.propertyvaluation");
			errorList.add(pr.getValue("error.header"));
			if (CommonConstant.VALUATION_FIRST_PAGE.equalsIgnoreCase(form
				.getFormName())) {
				if (CommonConstant.HOME_NEXT_ACTION.equalsIgnoreCase
				(form.getActionName())) {


					if (dto.getFirstName() == null || dto.getFirstName().trim(
						).equals("")) {
						flag = true;
						errorList.add(pr.getValue("error.propertyvaluation"
							+ ".firstname"));
					}
					if (dto.getLastName() == null || dto.getLastName().trim()
						.equals("")) {
						flag = true;
						errorList.add(pr.getValue("error.propertyvaluation"
							+ ".lastname"));
					}
					if (dto.getSex() == null || dto.getSex().trim().equals("")
						) {
						flag = true;
						errorList.add(pr.getValue("error.propertyvaluation"
							+ ".gender"));
					}

					if (dto.getAge() == 0) {
						flag = true;
						errorList.add(pr.getValue("error.propertyvaluation"
							+ ".testageequalzero"));
					}
				
                  
					setError(flag);
				}
			}
		} catch (Exception x) {
			logger.debug("validateWillDepositRule :-" + x);
		}
		return errorList;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public boolean isError() {
		return error;
	}

}
