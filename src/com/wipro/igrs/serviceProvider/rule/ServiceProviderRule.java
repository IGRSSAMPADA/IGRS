
/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :  ServiceProviderRule.java
 * Author      :   Lavi Tripathi
 * Description :   Created on 22 July 2013. THIS CLASS REPRESENTS THE RULE FOR SERVICE PROVIDER ACTION.
*/


package com.wipro.igrs.serviceProvider.rule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.wipro.igrs.util.PropertiesFileReader;

/**
 * @author Lavi Tripathi
 *
 */
public class ServiceProviderRule {
	
	ArrayList errors = new ArrayList();
	PropertiesFileReader pr = null;
	boolean flag = false;
	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
	public static DateFormat DATE_FORMATTER = new SimpleDateFormat(
			DEFAULT_DATE_FORMAT);

	private boolean error;
	
	
	
	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public ServiceProviderRule() {

		try {
			pr = PropertiesFileReader.getInstance("resources.igrs");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList validateFileType(String fileExt) {
		String[] arrFileExt = { "jpeg", "jpg", "tiff",
				"gif", "pdf", "png" };
		boolean flagFile = false;
		try {
			for (int i = 0; i < arrFileExt.length; i++) {
				if (fileExt.equalsIgnoreCase(arrFileExt[i])) {
					flagFile = true;
					break;
				}
			}
			if (!flagFile) {
				errors.add(pr.getValue("error.audit.fileType"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}
	
	

}
