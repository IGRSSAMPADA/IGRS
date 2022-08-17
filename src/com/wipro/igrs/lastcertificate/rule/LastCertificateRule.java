package com.wipro.igrs.lastcertificate.rule;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.lastcertificate.dao.LastCertificateDAO;
import com.wipro.igrs.lastcertificate.util.PropertiesFileReader;

public class LastCertificateRule {
	private boolean error;
	//private PropertiesFileReader pr;
	private Logger logger = (Logger) Logger
	.getLogger(LastCertificateRule.class);

 
	 
	public ArrayList validateLastPay(String empID, PropertiesFileReader pr) {
		boolean flag = false;
		logger.debug("inside validateLeaveEnCash");
	 
		logger.debug("*******" + empID);
		ArrayList errorList = new ArrayList();
		try {
			//pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
			errorList.add(pr.getValue("error.header"));
			 
		    LastCertificateDAO dao = new LastCertificateDAO();
		    String[] param = new String[1];
		    
		    param[0] = empID;
		    String[] lastPay = dao.getEmpCount(param);
		     
		    
		    if(lastPay !=null) {
		    	logger.debug(lastPay[0]+":"+lastPay[1]);
		    	if(lastPay[0]!=null && Integer.parseInt(lastPay[0])==0 )  {
		    		errorList.add(pr.getValue("error.lastpay.empid"));
		    		
					flag = true;
		    	}
		    	if(lastPay[1]!=null && Integer.parseInt(lastPay[1])==0 )  {
		    		errorList.add(pr.getValue("error.lastpay.fund"));
					flag = true;
		    	}
		    }
			 
			setError(flag);
				 
			 
		} catch (Exception x) {
			logger.debug("Last Pay Certificate :-" + x);
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
