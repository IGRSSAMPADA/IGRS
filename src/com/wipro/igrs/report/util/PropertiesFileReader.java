package com.wipro.igrs.report.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;


public class PropertiesFileReader {

    static ResourceBundle rb = null;

    static PropertiesFileReader dbPropReader = null;
    private static Logger logger = (Logger) Logger.getLogger(PropertiesFileReader.class);
    private PropertiesFileReader() {
    }

    /**
     * Method 		:  PropertiesFileReader
     * Description	:  Class constructor to initialize the ResourceBundle for the database.properties file
     * @throws  Exception
     */
    public static synchronized PropertiesFileReader getInstance(String fileName) throws Exception {

        try {
            if (dbPropReader == null) {
                dbPropReader = new PropertiesFileReader();
                rb = ResourceBundle.getBundle(fileName);
            }
        } catch (MissingResourceException e) {
            logger.debug(e);
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
            logger.debug(e);
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
    /*
    public static void main(String[] a ){
           try {
                      PropertiesFileReader pr=PropertiesFileReader.getInstance();
                      logger.debug(pr.getValue("PROVIDER_URL"));
           }catch(Exception x){
                 logger.debug(x);
           }
    }*/
}
