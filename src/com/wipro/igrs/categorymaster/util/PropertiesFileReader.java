/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   PropertiesFileReader.java
 * Author      :   Nihar Ranjan Mishra 
 * Description :   Represents a properties file reader.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Nihar R Mishra  28th Dec, 2007	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.categorymaster.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
 * @author nihraa
 *
 */
public class PropertiesFileReader {
	static ResourceBundle rb = null;
	static PropertiesFileReader dbPropReader = null;
	private PropertiesFileReader() {
	}

	/**
	 * Method 		:  PropertiesFileReader
	 * Description	:  Class constructor to initialize the ResourceBundle for the database.properties file
	 * @param fileName 
	 * @return PropertiesFileReader
	 * @throws  Exception
	 */
	public static synchronized PropertiesFileReader getInstance(String fileName) throws Exception {
		try {
			if (dbPropReader == null) {
				dbPropReader = new PropertiesFileReader();
				rb = ResourceBundle.getBundle(fileName);
			}
		} catch (MissingResourceException e) {
			System.out.println(e);
		}
		return dbPropReader;
	}

	/**
	 * Method 		:	getValue
	 * Description	:	This method is used to get the value of the specified key from the database.properties file
	 * @param key String
	 * @return value String
	 * @throws Exception
	 */
	public String getValue(String key) throws Exception {
		String value = null;
		try {
			value = rb.getString(key);
			if (value == null) {
				value = "";
			}
		} catch (MissingResourceException e) {
			System.out.println(e);
		}
		return value;
	}

	/**
	 * Method 		:	getKeys
	 * Description	:	This method is used to get the list of keys available in the database.properties file
	 * @return keyList ArrayList
	 */
	public ArrayList getKeys() {
		Enumeration enm = rb.getKeys();
		ArrayList keyList = new ArrayList();
		while (enm.hasMoreElements()) {
			keyList.add(enm.nextElement());
		}
		return keyList;
	}
}
