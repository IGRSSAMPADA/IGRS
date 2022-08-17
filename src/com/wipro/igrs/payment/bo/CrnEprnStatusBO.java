package com.wipro.igrs.payment.bo;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.estamping.bd.DutyCalculationBD;
import com.wipro.igrs.estamping.bd.EstampingBD;
import com.wipro.igrs.estamping.bo.DutyCalculationBO;
import com.wipro.igrs.estamping.dto.DashBoardDTO;
import com.wipro.igrs.payment.bd.CrnEprnStatusBD;
import com.wipro.igrs.payment.dto.CrnEprnStatusDTO;
import com.wipro.igrs.propertyvaluation.dto.DutyCalculationDTO;

public class CrnEprnStatusBO {
	/**
	 * @author Thilak
	 */
	private Logger logger = (Logger) Logger.getLogger(CrnEprnStatusBO.class);
	
	/**
	 * @author Thilak
	 */
	public CrnEprnStatusBO() {
		
	}
	
		
	/*     Method Name : getPaymentDetails()
	    *  Arguments   : tableName, parentColumn and parentKey
	    *  Return      : if it success return ArrayList
	    *                other wise return Fail
	    *  Exception   : Exception*/
	
	public ArrayList getPaymentDetails(String paymentModeId,String crnEprnNo)throws Exception
	{
		CrnEprnStatusBD crnEprnStatusBD = new CrnEprnStatusBD();
		ArrayList crnEprnDetailsList = new ArrayList();
				
		ArrayList paymentDetailsList = crnEprnStatusBD.getPaymentDetails(paymentModeId, crnEprnNo);                           //---added by satbir kumar---
		
		
		
		if(paymentDetailsList!=null && paymentDetailsList.size()>0){
			
			ArrayList rowList = new ArrayList();
			
            try{
			for (int i = 0; i < paymentDetailsList.size(); i++) {
				
				CrnEprnStatusDTO crnEprnStatusDTO = new CrnEprnStatusDTO();
				
				rowList = (ArrayList)paymentDetailsList.get(i);
				String paymentFlag = (String)rowList.get(0);
				if("D".equalsIgnoreCase(paymentFlag))
				{
					crnEprnStatusDTO.setPaymentFlag((String)rowList.get(0));
					crnEprnStatusDTO.setAmount(rowList.get(1).toString());
					crnEprnStatusDTO.setDateOfPayment(rowList.get(2));
					crnEprnStatusDTO.setCreatedBy((String)rowList.get(3));
					crnEprnDetailsList.add(crnEprnStatusDTO);
				}
				else
				{
					crnEprnStatusDTO.setPaymentFlag((String)rowList.get(0));
					crnEprnStatusDTO.setAmount("");
					crnEprnStatusDTO.setDateOfPayment(null);
					crnEprnStatusDTO.setCreatedBy("");
					crnEprnDetailsList.add(crnEprnStatusDTO);
				}
				
					
			}	
			
				
			}
            catch(Exception e){
            	e.printStackTrace();
            	
            }
	
		
		}
		return crnEprnDetailsList;
		}
	
}
