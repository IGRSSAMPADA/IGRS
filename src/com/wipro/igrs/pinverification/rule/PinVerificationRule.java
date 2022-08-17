package com.wipro.igrs.pinverification.rule;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.wipro.igrs.pinverification.dao.PinVerificationDAO;
import com.wipro.igrs.pinverification.dto.PinVerificationDTO;
import com.wipro.igrs.pinverification.util.PropertiesFileReader;

 
public class PinVerificationRule {

	
	private boolean error;
	private PropertiesFileReader pr;
	private ArrayList listVerifiedPIN = new ArrayList();
	private Logger logger = (Logger) Logger
	.getLogger(PinVerificationRule.class);
	
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	
	public ArrayList getVerifiedPIN(ArrayList list) {
		ArrayList errorList = new ArrayList();
		boolean flag = false;
		logger.debug("inside PinVerificationRule");
		try {
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
			errorList.add(pr.getValue("error.header"));
			ArrayList listReturn = new PinVerificationDAO().getVerifiedPIN(list);
			int sizeReturn =0;
			if(listReturn !=null) {
				
				sizeReturn = listReturn.size();
				for(int i =0;i<sizeReturn;i++) {
					PinVerificationDTO dto = (PinVerificationDTO)listReturn.get(i);
					if("Not Verified".equals(dto.getPinVerified())){
						setError(true);
						errorList.add(dto.getUserName()+" "+dto.getPinVerified());
					}
					
				}
			}
		}catch(Exception x) {
			logger.debug(x);
		}
		
		return errorList;
	}
	 
	public ArrayList checkedVerifiedPIN(ArrayList list,HashMap map) {
		ArrayList errorList = new ArrayList();
		boolean flag = false;
		logger.debug("inside PinVerificationRule");
		try {
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
			errorList.add(pr.getValue("error.header"));
			if(list!=null) {
				for(int i =0;i<list.size();i++) {
					PinVerificationDTO dtoList = (PinVerificationDTO)list.get(i);
					logger.debug("Property ID:-"+dtoList.getPropertyID());
					PinVerificationDTO dtoMap = (PinVerificationDTO)
											map.get(dtoList.getPropertyID());
					
					if(dtoMap==null){
						setError(true);
						errorList.add(dtoList.getPropertyName()+" not Verified.");
					}
				}
			}
		}catch(Exception x) {
			logger.debug(x);
		}
		
		return errorList;
	}
}
