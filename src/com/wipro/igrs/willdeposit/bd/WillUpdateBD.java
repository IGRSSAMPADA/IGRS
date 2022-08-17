/* Copyright (c) 2007-08 WIPRO INFOTECH. All Rights Reserved.
 * 
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *  
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE 
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE 
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF 
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS 
 * DERIVATIVES.
 * 
 */

/* 
 * FILE NAME: WillUpdateBD.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 12th April 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE BUSINESS DELEGATE FOR WILL UPDATION
 */

package com.wipro.igrs.willdeposit.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.willdeposit.dao.WillDepositDAO;
import com.wipro.igrs.willdeposit.dao.WillUpdateDAO;
import com.wipro.igrs.willdeposit.dto.WillDepositDTO;

/**
 * @author NIHAR M.
 * 
 */
public class WillUpdateBD {

	private Logger logger = (Logger) Logger.getLogger(WillUpdateBD.class);

	/**
	 * @param willId
	 * @param status
	 * @param fromDate
	 * @param toDate
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getDeliveryViewDetails(String willId, String fromDate,
			String toDate, String status,String officeId) throws Exception {
		WillUpdateDAO dao = new WillUpdateDAO();
		ArrayList list = dao.getDeliveryViewDetails(willId, fromDate, toDate,
				status,officeId);

		ArrayList returnList = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList lst = (ArrayList) list.get(i);
				WillDepositDTO dto = new WillDepositDTO();
				dto.setWillId((String) lst.get(0));
				dto.setFirstName((String) lst.get(1));
				dto.setMidName((String) lst.get(2));
				dto.setLastName((String) lst.get(3));
				dto.setDespositDate((String) lst.get(4));
				dto.setWillStatus("Deposited");
				dto.setWithdrawlDueDate((String) lst.get(6));

				returnList.add(dto);
			}
		}
		return returnList;
	}

	/**
	 * @param willid
	 * @return WillDepositDTO
	 * @throws Exception
	 */
	public WillDepositDTO getwillIdDetails(String willid, String StrFunId)
			throws Exception {
		WillDepositDTO dto = new WillDepositDTO();
		WillUpdateDAO dao = new WillUpdateDAO();
		ArrayList ret = dao.getwillIdDetails(willid);

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList list = (ArrayList) ret.get(i);
				dto.setWillId((String) list.get(0));
				dto.setWillStatus((String) list.get(5));
				dto.setFirstName((String) list.get(12));
				dto.setMidName((String) list.get(13));
				dto.setLastName((String) list.get(14));
				dto.setGender((String) list.get(16));
				dto.setFatherName((String) list.get(18));
				dto.setMotherName((String) list.get(19));
				String agent = (String) list.get(1);
				if (agent.equalsIgnoreCase("A")) {
					dto.setAgent((String) list.get(1));
					dto.setAgentFirstName((String) list.get(2));
					dto.setAgentMidName((String) list.get(3));
					dto.setAgentLastName((String) list.get(4));
					dto.setGender((String) list.get(6));
					// dto.setAge(Integer.parseInt((String) list.get(7)));
					dto.setFatherName((String) list.get(8));
					dto.setMotherName((String) list.get(9));
				}

			}
		}
		ArrayList list = new ArrayList();
		ArrayList retList1 = new ArrayList();
		String feeVal = "0.0";

		try {
			WillDepositDAO objDao = new WillDepositDAO();
			list = objDao.getWillFeeValue(StrFunId);

			if (retList1 != null) {
				retList1 = (ArrayList) list.get(0);
				feeVal = (String) retList1.get(1);
			}

		} catch (Exception e) {
			logger.debug("Error:-    " + e.getMessage());
		}
		dto.setFee(feeVal);
		return dto;
	}

	/**
	 * @param wDTO
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateDeliveryDetails(String remarks, String willid,
			String userId) throws Exception {
		WillRetrievalBD bd= new WillRetrievalBD();
		String will=bd.getWillId(willid);
		WillUpdateDAO willDAO = new WillUpdateDAO();
		boolean deliveryDone = false;
		//TODO remarks to be added by fetching willid from the txn id
		deliveryDone = willDAO.updateDeliveryDetails(remarks, will, userId);
		return deliveryDone;
	}
	
	public boolean updateTreasuryId(String treasuId, String willid,
			String userId) throws Exception {

		WillUpdateDAO willDAO = new WillUpdateDAO();
		boolean deliveryDone = false;

		deliveryDone = willDAO.updateWillTreasuryId(treasuId, willid, userId);
		return deliveryDone;
	}
	
	public boolean updateWithDrawalStatus(String WillId) throws Exception {

		WillUpdateDAO willDAO = new WillUpdateDAO();
		boolean deliveryDone = false;

		deliveryDone = willDAO.updateWithDrawalStatus(WillId);
		return deliveryDone;
	}
	public boolean updateRetrieveStatus(String WillId) throws Exception {

		WillUpdateDAO willDAO = new WillUpdateDAO();
		boolean deliveryDone = false;

		deliveryDone = willDAO.updateRetrievalStatus(WillId);
		return deliveryDone;
	}
	
}
