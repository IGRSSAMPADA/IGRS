

/* 
 * Copyright Notice 
 *==============================================================================
 * This file contains proprietary information of
 * IGRS, Govt. Of MadhyaPradesh
 *
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007
 *==============================================================================
 *
 * File Name   :   Configuration.java
 * Author      :   Nihar R Mishra 
 * Description :   This file contains the code for Configuration.java
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Nihar R Mishra  22nd Dec, 2007	   	 
 * --------------------------------------------------------------------------------
 */


package com.wipro.igrs.Log4J;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author nihraa
 *
 */
public class Configuration {

	private static Configuration configuration; //Singleton instance
	private Properties properties; // Configuration key/value holder
	private Properties pdfProperties; // Configuration key/value holder
	private Properties helpProperties; // Action sevlet - help html mapping
	/**
	 * Private Constructor which cannot be accessed by outside of the class
	 *
	 */
	private Configuration() { 

		properties = new Properties();
		pdfProperties = new Properties();
		helpProperties = new Properties();
		//Load all the configuration parameters from file into object
		try {

			FileInputStream fin = new FileInputStream(Configurations.DCR_CONFIG);
			properties.load(fin);
			fin.close();
			System.out.println("loaded proeprly");


		} catch (Exception e) {
			e.printStackTrace();
			LoggerMsg.error(e);

		}

	}
	/**
	 * getInstance - Create single instance of configuration property object
	 * @return Configuration
	 *
	 */
	public static Configuration getInstance() {

		if (configuration == null) {
			configuration = new Configuration();
		}
		return configuration;
	}

	/**
	 * getConfig - This method will return the configuration value corresponding to the key
	 * @param key 
	 * @return String
	 */

	public String getConfig(String key) {
		return properties.getProperty(key);
	}

	/**
	 * getConfig - This method will return the configuration value corresponding to the key
	 * @param key 
	 * @return String
	 */

	public String getPdfConfig(String key) {
		return pdfProperties.getProperty(key);
	}


	/**
	 * getHelpPath - This method will return the help html path corresponding to the action servlet mapping
	 * @param key 
	 * @return String
	 */

	public String getHelpPath(String key) {

		return helpProperties.getProperty(key);
	}

}

