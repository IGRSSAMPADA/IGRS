package com.wipro.igrs.pindelivery.bo;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.pindelivery.bd.PinDeliveryBD;
import com.wipro.igrs.pindelivery.dto.PinDeliveryDTO;


public class PinDeliveryBO {
	private Logger logger = 
		(Logger) Logger.getLogger(PinDeliveryBO.class);
	
	public ArrayList getPINDeliveryDetails(){
		ArrayList list = new PinDeliveryBD().getPINDeliveryDetails(null);
		ArrayList listDTO = new ArrayList();
		
		if(list!=null) {
			for(int i = 0;i<list.size();i++) {
				PinDeliveryDTO dto = new PinDeliveryDTO();
				ArrayList listReturn = (ArrayList)list.get(i);
				dto.setRegID((String)listReturn.get(0));
				listDTO.add(dto);
			}
		}
		
		return listDTO;
	}
	public boolean insertPINStatusDetails(PinDeliveryDTO dto) {
		boolean bol = false;
		
		String[] param= new String[3] ;
		
		param[0] = dto.getDeliveryComment();
		param[1] = dto.getUserID();
		param[2] = dto.getRegistrationID();
		
		bol = new PinDeliveryBD().insertPINDeliveryDetails(param);
		
		logger.debug("PinDelivery Return value:-"+bol);
		return bol;
	}
	
	
}
