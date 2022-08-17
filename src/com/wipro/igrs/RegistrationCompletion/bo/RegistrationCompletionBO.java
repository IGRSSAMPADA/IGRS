
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
 */ 

/* 
 * FILE NAME: RegistrationCompletionBO.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 28th June 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE BO FOR REGISTRATION COMPLETION ACTION.
 */

package com.wipro.igrs.RegistrationCompletion.bo;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.RegistrationCompletion.dao.RegistrationCompletionDAO;
import com.wipro.igrs.RegistrationCompletion.dto.RegistrationCompletionDTO;

/**
 * @author NIHAR M.
 *
 */
public class RegistrationCompletionBO {

	Logger logger = (Logger) Logger.getLogger(RegistrationCompletionBO.class);


	/**
	 * @param regDTO
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getTxnIDDetails(RegistrationCompletionDTO regDTO) throws Exception{

		RegistrationCompletionDAO regDAO = new RegistrationCompletionDAO();
		RegistrationCompletionDTO dto = new RegistrationCompletionDTO();

		String[] param = new String[11];
		param[0]  = regDTO.getRegTxnId();
		param[1]  = regDTO.getRegTxnId();
		param[2]  = regDTO.getRegTxnId();
		param[3]  = regDTO.getFromDate();
		param[4]  = regDTO.getFromDate();
		param[5]  = regDTO.getFromDate();
		param[6]  = regDTO.getFromDate();
		param[7]  = regDTO.getToDate();
		param[8]  = regDTO.getToDate();
		param[9]  = regDTO.getToDate();
		param[10] = regDTO.getToDate();

		ArrayList list = regDAO.getTxnIDDetails(param);

		ArrayList returnList = new ArrayList();
		if( list != null) {
			logger.debug("list.size():- "+list.size());
			for (int i = 0;i < list.size(); i++){
				ArrayList lst = (ArrayList) list.get(i);
				dto = new RegistrationCompletionDTO();
				dto.setRegTxnId((String) lst.get(0));
				dto.setDate((String) lst.get(1));
				dto.setStatus((String) lst.get(2));
				logger.debug(returnList);
				returnList.add(dto);
			}
		}
		else{
			logger.debug("--------------------------------LIST IS NULL");
		}
		return returnList;
	}



	/**
	 * @param txnID
	 * @return RegistrationCompletionDTO
	 * @throws Exception
	 */
	public RegistrationCompletionDTO getIndRegIDDetails(String txnID) throws Exception{

		RegistrationCompletionDAO regDAO = new RegistrationCompletionDAO();
		RegistrationCompletionDTO dto = new RegistrationCompletionDTO();
		String [] param = new String[1];
		param[0] = txnID;
		ArrayList list = regDAO.getIndRegIDDetails(param);
		ArrayList returnList = new ArrayList();
		if( list != null) {
			logger.debug("list.size():- "+list.size());
			for (int i = 0;i < list.size(); i++){
				ArrayList lst = (ArrayList) list.get(i);

				dto.setRegTxnId((String) lst.get(0));
				dto.setStatus((String) lst.get(1));

				logger.debug((String) lst.get(0));
				logger.debug((String) lst.get(1));
				returnList.add(dto);
			}
		}
		return dto;
	}


	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getStatusList() throws Exception{

		RegistrationCompletionDAO regDAO = new RegistrationCompletionDAO();
		ArrayList ret = regDAO.getStatusList();
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				RegistrationCompletionDTO regDTO = new RegistrationCompletionDTO();
				regDTO.setStatusId((String) lst.get(0));
				regDTO.setUpdateStatus((String) lst.get(1));
				list.add(regDTO);
			}
		}
		return list;
	}


	/**
	 * @return boolean
	 * @throws Exception
	 */
	public boolean submitUpdatedDetails(RegistrationCompletionDTO regDTO) throws Exception{

		System.out.println("Called:   submitUpdatedDetails");
		RegistrationCompletionDAO regDAO = new RegistrationCompletionDAO();
		boolean submitDetails = false;

		String[] param = new String[2];
		param[0] = regDTO.getStatusId();
		param[1] = regDTO.getRegTxnId();

		String[] param2 = new String[4];
		param2[0] = regDTO.getRegTxnId();
		param2[1] = regDTO.getStatusId();
		param2[2] = regDTO.getTxnRemark();
	//	param2[3] = regDTO.getCheckStatus();
		param2[3] = regDTO.getExpectedDateOfDelivery();		

		submitDetails =  regDAO.submitUpdatedDetails(param, param2);
		return(submitDetails);
	}

}
