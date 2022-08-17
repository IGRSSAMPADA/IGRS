package com.wipro.igrs.payment.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.estamping.dao.DutyCalculationDAO;
import com.wipro.igrs.payment.dao.ChallanGenDAO;
import com.wipro.igrs.payment.dao.CrnEprnStatusDAO;
import com.wipro.igrs.payment.dto.ChallanGenDTO;
import com.wipro.igrs.payment.dto.CrnEprnStatusDTO;

public class CrnEprnStatusBD {
	private Logger logger = (Logger) Logger.getLogger(CrnEprnStatusBD.class);
	 public CrnEprnStatusBD() {
	    }
	    
	  /**
	     * Method 		: getPaymentDetails
	     * Description	: RETURNING PAYMENT DETAILS BASED ON DATE AND PAYMENT MODE
	     * @param query : string ,
	     * @throws 		: Exception
	     * return Type  :ArrayList 
	     */
	 public ArrayList getPaymentDetails(String paymentModeId,String crnEprnNo)throws Exception {
		    CrnEprnStatusDAO crnEprnStatusDAO = new CrnEprnStatusDAO();
		    ArrayList paymentDetails=crnEprnStatusDAO.getPaymentDetails(paymentModeId,crnEprnNo); 
			return paymentDetails;
		}
	  

}
