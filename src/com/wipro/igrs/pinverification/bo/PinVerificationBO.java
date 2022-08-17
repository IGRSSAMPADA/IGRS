package com.wipro.igrs.pinverification.bo;

import java.util.ArrayList;

import com.wipro.igrs.pinverification.bd.PinVerificationBD;
import com.wipro.igrs.pinverification.dto.PinVerificationDTO;
import org.apache.log4j.Logger;


public class PinVerificationBO {
	private Logger logger = 
		(Logger) Logger.getLogger(PinVerificationBO.class);
	
	public ArrayList getPropertyList(String property){
		ArrayList list = new ArrayList();
		String[] propertyList;
		int j=0;
		if(property != null ) {
			propertyList = property.split("~");
			if(propertyList !=null && !"".equals(property.trim())) {
				for(int i=0;i<propertyList.length;i++) {
					PinVerificationDTO dto = new PinVerificationDTO();
					j = i+1;
					dto.setPropertyID(propertyList[i]);
					dto.setHdnPropertyID("Property "+j+"~"+propertyList[i]);
					dto.setPropertyName("Property "+j);
					list.add(dto);
				}
			}
		}
		return list;
	}
	
	public PinVerificationDTO getPropertyAddressDetails(String propertyTxnId){
		PinVerificationDTO dto = new PinVerificationDTO();
		
		
		 
		String[] param = new String[1];
		param[0] = propertyTxnId;
		logger.debug("Property Txn ID "+ propertyTxnId);
		ArrayList list = 
				new PinVerificationBD().getPropertyAddressDetails(param);
		if(list!=null) {
			for(int i = 0; i<list.size(); i++) {
				ArrayList retList = (ArrayList)list.get(i);
				dto.setDistrictName((String)retList.get(2));
				dto.setTehsilName((String)retList.get(4));
				dto.setWardName((String)retList.get(6));
				dto.setMohallaName((String)retList.get(8));
				dto.setAreaName((String)retList.get(10));
				dto.setAddress((String)retList.get(11));
			}
		}
		
		return dto;
	}
	public ArrayList getUserDetails(String propertyID) {
		ArrayList listReturn = new ArrayList();
		String[] param = new String[1];
		param[0] = propertyID;
		logger.debug("Property Txn ID "+ propertyID);
		ArrayList list = 
				new PinVerificationBD().getUserDetails(param);
		if(list!=null) {
			for(int i = 0; i<list.size(); i++) {
				ArrayList retList = (ArrayList)list.get(i);
				PinVerificationDTO dto = new PinVerificationDTO();
				
				dto.setUserID((String)retList.get(0)+"-"+(String)retList.get(1));
				dto.setUserName((String)retList.get(1));
				dto.setPinNumber("");
				
				listReturn.add(dto);
			}
		}
		
		return listReturn;
	}
}
