package com.wipro.igrs.pindelivery.rule;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.wipro.igrs.pinverification.dao.PinVerificationDAO;
import com.wipro.igrs.pinverification.dto.PinVerificationDTO;
import com.wipro.igrs.pinverification.util.PropertiesFileReader;

 
public class PinDeliveryRule {

	
	private boolean error;
	private PropertiesFileReader pr;
	private ArrayList listVerifiedPIN = new ArrayList();
	private Logger logger = (Logger) Logger
	.getLogger(PinDeliveryRule.class);
	
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	
	public ArrayList getInsertedPIN(boolean bol,String regID) {
		ArrayList errorList = new ArrayList();
		boolean flag = false;
		logger.debug("inside PinVerificationRule");
		try {
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
			errorList.add(pr.getValue("error.header"));
			 
			setError(true);
			errorList.add(regID+" "+pr.getValue("error.pindelivery.insert"));
				 
		}catch(Exception x) {
			logger.debug(x);
		}
		
		return errorList;
	} 
}
