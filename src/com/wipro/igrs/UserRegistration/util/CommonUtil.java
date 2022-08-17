/**
 * ===========================================================================
 * File           :   CommonUtil.java
 * Description    :   Represents the Action Class

 * Author         :   Madan  Mohan
 * Created Date   :   Nov 28, 2007

 * ===========================================================================
 */

package com.wipro.igrs.UserRegistration.util;


import java.text.Format;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 * ===========================================================================
 * File           :   CommonUtil.java
 * Description    :   Represents the Action Class

 * Author         :   Madan  Mohan
 * Created Date   :   Nov 28, 2007

 * ===========================================================================
 */
public class CommonUtil {

    private String url = null;
    private String initial = null;
    private String user = null;
    private String pwd = null;

    public CommonUtil() {
    }
    private Object getCommonHome(String jndi) throws NamingException {
        Properties props = new Properties();

        getProperties();
        props.put("java.naming.provider.url", url);
        props.put("java.naming.factory.initial", initial);
        props.put("java.naming.security.principal", user);
        props.put("java.naming.security.credentials", pwd);

        final InitialContext context = 
            new InitialContext(props); // replace with provider specific proper
                                       // ties file
        return context.lookup(jndi);
    }
    private void getProperties() {
        try {
            PropertiesFileReader pr = 
                PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
            url = pr.getValue("PROVIDER_URL");
            initial = pr.getValue("INITIAL_FACTORY");
            user = pr.getValue("JNDI_PRINCIPAL");
            pwd = pr.getValue("JNDI_CREDENTIALS");
        } catch (Exception x) {
            System.out.println(x);
        }
    }
    
   /* public String getOracleDate(String dateFormat) {
        //dateFormat dd/MM/YYYY
        Format formatter = new SimpleDateFormat("dd-MMM-yyyy");
        String strFormat;
        strFormat = formatter.format(new Date(dateFormat));
        return strFormat;
    }*/
    /*
    public static void main(String[] a) {
          getProperties();
        System.out.println(url);
    }*/
}
