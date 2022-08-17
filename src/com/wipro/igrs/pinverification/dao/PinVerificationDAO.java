package com.wipro.igrs.pinverification.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.pinverification.dto.PinVerificationDTO;
import com.wipro.igrs.pinverification.sql.CommonSQL;

 public class PinVerificationDAO {

	
	private Logger logger = 
		(Logger) Logger.getLogger(PinVerificationDAO.class);
	
	
	public ArrayList getPropertyAddressDetails(
							String[] propertyTxnId) throws Exception {
		
		DBUtility dbUtil = new DBUtility();
		ArrayList list = new ArrayList();
		try {
			dbUtil.createPreparedStatement(CommonSQL.PROPERTYDETAILS_QUERY);
			list = dbUtil.executeQuery(propertyTxnId);
		}catch(Exception x) {
			logger.debug(x);
		}finally {
			dbUtil.closeConnection();
		}
		
		return list;
		
	}
	
	public ArrayList getUserDetails(
						String[] propertyTxnId) throws Exception {

		DBUtility dbUtil = new DBUtility();
		ArrayList list = new ArrayList();
		try {
			dbUtil.createPreparedStatement(CommonSQL.USER_DETAILS_QUERY);
			list = dbUtil.executeQuery(propertyTxnId);
		}catch(Exception x) {
			logger.debug(x);
		}finally {
			dbUtil.closeConnection();
		}

		return list;

	}
	public ArrayList getVerifiedPIN(ArrayList list) throws Exception {
		ArrayList mapVerifiedPin = new ArrayList();
		
		DBUtility dbUtil = new DBUtility();
		 
		try {
			dbUtil.createPreparedStatement(CommonSQL.VERIFIED_PIN_QUERY);
			
			if(list != null) {
				for(int i =0;i<list.size();i++) {
					PinVerificationDTO dto = (PinVerificationDTO)list.get(i);
					String[] param = new String[4];
					param[0] = dto.getPinNumber();
					param[1] = dto.getUserID();
					param[2] = dto.getPropertyID();
					param[3] = "A";
					logger.debug(param[0]+":"+param[1]+":"+param[2]+":"+param[3]);
					String strCount = dbUtil.executeQry(param);
					if(strCount != null) {
						int verified = Integer.parseInt(strCount);
						if(verified == 0) {
							dto.setPinVerified("Not Verified");
						}else if(verified > 0){
							dto.setPinVerified("Verified");
						}
					}else if(strCount == null){
						dto.setPinVerified("Not Verified");
					}
					logger.debug("Verified:-"+dto.getPinVerified());
					mapVerifiedPin.add(dto);
				}
			}
			//list = dbUtil.executeQuery(propertyTxnId);
		}catch(Exception x) {
			logger.debug(x);
		}finally {
			dbUtil.closeConnection();
		}

		return mapVerifiedPin;
		
	}
}
