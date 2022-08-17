package com.wipro.igrs.regcompletion.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wipro.igrs.regcompletion.constants.CommonConstant;
import com.wipro.igrs.regcompletion.dao.RegCompletionDAO;
import com.wipro.igrs.regcompletion.dto.CommonDTO;
import com.wipro.igrs.regcompletion.util.PropertiesFileReader;

public class RegCompletionRule {
	
	private boolean error;
	private PropertiesFileReader pr;
	private ArrayList listVerifiedPIN = new ArrayList();
	private Logger logger = (Logger) Logger
	.getLogger(RegCompletionRule.class);
	
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	
	public ArrayList verifyPIN(HashMap mapPin,String partyTxn) {
		ArrayList errorList = new ArrayList();
		boolean flag = false;
		try {
		 
			Iterator It = mapPin.entrySet().iterator();
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
			
			while(It.hasNext()) {
				Map.Entry me = (Map.Entry) It.next();
				CommonDTO dtoMap = (CommonDTO) me.getValue();
				if("TRUE".equalsIgnoreCase(dtoMap.getPinStatus())) {
					flag = true;
					errorList.add(pr.getValue("error.regcomplete.pinverify"));
					break ;
				}
			}
			String[] partyTxnId = partyTxn.split(",");
			if(partyTxnId !=null && !"".equals(partyTxn.trim())) {
				for(int i=0;i<partyTxnId.length;i++){
					String[] partyId = partyTxnId[i].split("~");
					String partyI = "";
					if(partyId[0]!=null && !"".equals(
								partyId[0].trim())){
						logger.debug("partyId:-"+partyId[0]);
						partyI  = partyId[0];
					}
					if(partyId[1]!=null&& !"".equals(
							partyId[0].trim())){
						logger.debug("partyId:-"+partyId[1]);
						String[] partyType = partyId[1].split("#");
						 
						if("Y".equals(
								partyType[2])) {
							if(!mapPin.containsKey(partyI)) {
								errorList.add("<font color='red'>"
											+partyI+"&nbsp; is not verified.</font>");
								flag = true;
							}
						}
					}
					 
				}
			}
			setError(flag);
		}catch(Exception x) {
			logger.debug(x);
		}
		
		return errorList;
		
	}
	public ArrayList checkRegistrationNumber(String regID) {
		ArrayList errorList = new ArrayList();
		boolean flag = false;
		try {
			pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
			errorList.add(pr.getValue("error.header"));
			RegCompletionDAO dao = new RegCompletionDAO();
			String count = dao.checkValidRegistration(regID);
			
			int rCount = count == null ? 0 :Integer.parseInt(count);
			if(rCount <1) {
				errorList.add(pr.getValue("error.regcomplete.regid"));
				flag = true;
			}else if(rCount >1 ) {
				String spotCount = dao.checkSpotInspection(regID);
				logger.debug("spotCount:-"+spotCount);
				int sCount = spotCount == null ? 0 :Integer.parseInt(spotCount);
				if(sCount >0) {
					errorList.add(pr.getValue("error.regcomplete.spotregid"));
					flag = true;
				}
			}
			setError(flag);
			
			 
		}catch(Exception x) {
			logger.debug(x);
		}
		
		return errorList;
	}
	public ArrayList checkRegID(String regID) {
		ArrayList errorList = new ArrayList();
		boolean flag = false;
		logger.debug("inside PinVerificationRule");
		try {
			pr = PropertiesFileReader.getInstance("resources.igrs");
			errorList.add(pr.getValue("error.header"));
			 
			RegCompletionDAO dao = new RegCompletionDAO();
			String [] error = dao.checkRegistration(regID);
			
			if(error !=null ) {
				if(error[0] != null && 
							CommonConstant.CAVET_FLAG_ACTIVE.equals(error[0])) {
					flag = true;
					errorList.add(pr.getValue("error.regflag.cavetflag"));
				}
				if(error[1] != null && 
							CommonConstant.PROPERTYLOCK_FLAG_ACTIVE.equals(error[1])) {
					flag = true;
					errorList.add(pr.getValue("error.regflag.propertylockflag"));
					
				}
				if(error[2] != null && 
						CommonConstant.CASE_FLAG_ACTIVE.equals(error[2])) {
					flag = true;
					errorList.add(pr.getValue("error.regflag.caseflag"));
				}
				if(error[3] != null && 
						CommonConstant.COURTORDER_FLAG_ACTIVE.equals(error[3])) {
					errorList.add(pr.getValue("error.regflag.courtorderflag"));
					flag = true;
				}
				if(error[4] != null && 
						CommonConstant.POA_FLAG_ACTIVE.equals(error[4])) {
					errorList.add(pr.getValue("error.regflag.cavetflag"));
					flag = true;
				}
				if(error[5] != null && 
						CommonConstant.REGISTRATION_NOT_FOUND.equals(error[5])) {
					errorList.add(pr.getValue("error.regflag.noregistration"));
					flag = true;
				}
				
			}
			setError(flag);
			
				 
		}catch(Exception x) {
			logger.debug(x);
			System.out.println("error details"+x);
		}
		
		return errorList;
	} 
}
