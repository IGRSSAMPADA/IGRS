/**
 * ServiceProviderAccountRule.java
 */


package com.wipro.igrs.sp.rule;


import java.util.ArrayList;

import com.wipro.igrs.util.PropertiesFileReader;


public class ServiceProviderAccountRule {

	private boolean error;

	PropertiesFileReader pr = null;

	ArrayList errorList = null;

	/**Constructor name : ServiceProviderAccountRule
	 * 
	 */

	public ServiceProviderAccountRule() {
		try {
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
		} catch (Exception e) {
			System.out.println("Not Able to Initilize the property File" + e);
		}
	}

	/** 
	 * Method name		:nullOrBlank
	 * @param str
	 * @return
	 *  
	 */
	protected boolean nullOrBlank(String str) {
		return ((str == null) || (str.trim().length() == 0));
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public boolean isError() {
		return error;
	}

}
