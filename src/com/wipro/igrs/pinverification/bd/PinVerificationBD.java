package com.wipro.igrs.pinverification.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.pinverification.dao.PinVerificationDAO;


public class PinVerificationBD {
	private Logger logger = 
		(Logger) Logger.getLogger(PinVerificationBD.class);
	
	
	public ArrayList getPropertyAddressDetails(String[] param) {
		ArrayList list = new ArrayList();
		try {
			list = new PinVerificationDAO().getPropertyAddressDetails(param);
		}catch(Exception x) {
			logger.debug(x);
		}
		return list;
	}
	public ArrayList getUserDetails(String[] param) {
		ArrayList list = new ArrayList();
		try {
			list = new PinVerificationDAO().getUserDetails(param);
		}catch(Exception x) {
			logger.debug(x);
		}
		return list;
	}
}
