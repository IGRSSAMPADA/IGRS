package com.wipro.igrs.common;

import java.io.FileInputStream;

import java.util.Properties;


public class Configuration {

    public static final String HSBC_CONFIG = "c://igrs_config.properties";
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

            FileInputStream fin = new FileInputStream(HSBC_CONFIG);
            properties.load(fin);
            fin.close();
            System.out.println("loaded proeprly");


        } catch (Exception e) {
            e.printStackTrace();
            //LoggerMsg.error(e);

        }

    }

    /**
     * getInstance - Create single instance of configuration property object
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
     */
    public

    String getConfig(String key) {
        return properties.getProperty(key);
    }

    /**
     * getConfig - This method will return the configuration value corresponding to the key
     */
    public

    String getPdfConfig(String key) {
        return pdfProperties.getProperty(key);
    }


    /**
     * getHelpPath - This method will return the help html path corresponding to the action servlet mapping
     */
    public

    String getHelpPath(String key) {

        return helpProperties.getProperty(key);
    }

}
