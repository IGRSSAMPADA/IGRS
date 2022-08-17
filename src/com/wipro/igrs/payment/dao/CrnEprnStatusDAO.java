package com.wipro.igrs.payment.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.payment.sql.CommonSQL;

public class CrnEprnStatusDAO {
	ArrayList list = null;
	String query;
	 // obj for dbutility
	private Logger logger = (Logger) Logger.getLogger(CrnEprnStatusDAO.class);
	public CrnEprnStatusDAO() throws Exception {
		//dbUtil = new DBUtility(); // creating connection to db

	}
	
	
	/**
     * ===========================================================================
     * Method         :   getPaymentDetails()
     * Description    :   Returns list  of PaymentDetails  . 
     * Arguments      :   
     * return type    :   Arraylist
     
     * ===========================================================================
     */  
	
	public ArrayList getPaymentDetails(String paymentModeId,String crnEprnNo) {

		ArrayList paymentDetails=null;
		String[] param = {crnEprnNo};
		DBUtility dbUtil=null;
		try {
				dbUtil = new DBUtility();
				String query=null;
				if("2".equalsIgnoreCase(paymentModeId))
				query=CommonSQL.GET_EPRN_PAYMENT_DETAILS;
				if("3".equalsIgnoreCase(paymentModeId))
				query=CommonSQL.GET_CRN_PAYMENT_DETAILS;
				dbUtil.createPreparedStatement(query);
				paymentDetails = dbUtil.executeQuery(param);
				
			} catch (Exception exception) {
				logger.debug("Exception in getInstId" + exception);
			}
			finally
			{
				try
				{
					dbUtil.closeConnection();
				}
				catch(Exception e)
				{
					logger.error("error in close connection getInstId"+e.getStackTrace());
				}
				
			}
			return paymentDetails;
	}
	


}
