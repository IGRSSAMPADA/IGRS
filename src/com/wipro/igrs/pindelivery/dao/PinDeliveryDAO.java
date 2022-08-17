package com.wipro.igrs.pindelivery.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.pindelivery.constant.CommonConstant;
import com.wipro.igrs.pindelivery.sql.CommonSQL;

 public class PinDeliveryDAO {

	
	private Logger logger = 
		(Logger) Logger.getLogger(PinDeliveryDAO.class);
	
	private DBUtility dbUtil;
	public ArrayList getPINDeliveryDetails(String regID) throws Exception{
		ArrayList list = new ArrayList();
		dbUtil = new DBUtility();
		String SQL = CommonSQL.PIN_DELIVERY_QUERY;
		try {
			
			/*if(!"".equals(regID.trim()) && regID !=null   
					 ) {
				SQL = SQL + " AND REGISTRATION_ID LIKE '%"+regID+"%'";
			}*/
			 
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
		}catch(Exception x) {
			logger.debug(x);
		}finally {
			dbUtil.closeConnection();
		}
		
		return list;
		
	}
	public boolean insertDeliveryStatus(String[] param)throws Exception{
		boolean bol = false;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.INSERT_DELIVERY_QUERY);
			
			bol = dbUtil.executeUpdate(param);
			
			if(bol){
				dbUtil.commit();
			}else {
				dbUtil.rollback();
			}
		}catch(Exception x) {
			logger.debug(x);
			dbUtil.rollback();
			bol = false;
		}finally {
			dbUtil.closeConnection();
		}
		return bol;
	}
}
