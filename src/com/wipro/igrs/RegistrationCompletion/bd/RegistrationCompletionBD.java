
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
 * FILE NAME: RegistrationCompletionBD.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 28th June 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE BD FOR REGISTRATION COMPLETION ACTION.
 */


package com.wipro.igrs.RegistrationCompletion.bd;

import java.util.ArrayList;

import com.wipro.igrs.RegistrationCompletion.bo.RegistrationCompletionBO;
import com.wipro.igrs.RegistrationCompletion.dto.RegistrationCompletionDTO;

/**
 * @author NIHAR M.
 *
 */
public class RegistrationCompletionBD {

	RegistrationCompletionBO regBO = new RegistrationCompletionBO();


	/**
	 * @param regDTO
	 * @return ArrayList
	 * @throws Exception 
	 */
	public ArrayList getTxnIDDetails(RegistrationCompletionDTO regDTO) throws Exception{
		return regBO.getTxnIDDetails(regDTO);
	}


	/**
	 * @param id
	 * @return RegistrationCompletionDTO
	 * @throws Exception
	 */
	public RegistrationCompletionDTO getIndRegIDDetails(String id) throws Exception{
		return regBO.getIndRegIDDetails(id);
	}


	/**
	 * @return ArrayList
	 * @throws Exception 
	 */
	public ArrayList getStatusList() throws Exception{
		return regBO.getStatusList();
	}
	
	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public boolean submitUpdatedDetails(RegistrationCompletionDTO regDTO) throws Exception{
		return regBO.submitUpdatedDetails(regDTO);
	}
}
