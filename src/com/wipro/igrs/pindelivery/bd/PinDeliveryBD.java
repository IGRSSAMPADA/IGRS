package com.wipro.igrs.pindelivery.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.pindelivery.dao.PinDeliveryDAO;
import com.wipro.igrs.pindelivery.dto.PinDeliveryDTO;
import com.wipro.igrs.pinverification.dao.PinVerificationDAO;


public class PinDeliveryBD {
	private Logger logger = 
		(Logger) Logger.getLogger(PinDeliveryBD.class);
	
	
	public ArrayList getPINDeliveryDetails(String regID) {
		ArrayList list = new ArrayList();
		try {
			list = new PinDeliveryDAO().getPINDeliveryDetails(regID);
		}catch(Exception x) {
			logger.debug(x);
		}
		return list;
	}
	
	public boolean insertPINDeliveryDetails(String[] param) {
		boolean bol = false;
		try {
			bol = new PinDeliveryDAO().insertDeliveryStatus(param);
		}catch(Exception x) {
			logger.debug(x);
		}
		return bol;
	}
}
