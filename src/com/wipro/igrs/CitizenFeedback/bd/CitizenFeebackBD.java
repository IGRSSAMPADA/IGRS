
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
 * FILE NAME: CitizenFeebackBD.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 23th June 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE BD FOR CITIZEN FEEDBACK ACTION.
 */

package com.wipro.igrs.CitizenFeedback.bd;

import java.util.ArrayList;

import com.wipro.igrs.CitizenFeedback.bo.CitizenFeedbackBO;
import com.wipro.igrs.CitizenFeedback.dto.CitizenFeedbackDTO;

/**
 * @author NIHAR M.
 *
 */
public class CitizenFeebackBD {

	CitizenFeedbackBO feedbackBO = new CitizenFeedbackBO();

	/**
	 * @param feedbackDTO
	 * @return boolean
	 * @throws Exception
	 */
	
	public String submitFeedbackDetails(CitizenFeedbackDTO feedbackDTO,String language) throws Exception{
		return feedbackBO.submitFeedbackDetails(feedbackDTO,language);
	}

	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getCountry() throws Exception{
		return feedbackBO.getCountry();
	}

	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getState(String con) throws Exception{
		return feedbackBO.getState(con);
	}

	/**
	 * @param con
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getDistrict(String language) throws Exception{
		return feedbackBO.getDistrict(language);
	}

	/**
	 * @param con
	 * @return ArrayList
	 * @throws Exception
	 */
/*	public ArrayList getIDProof(String con) throws Exception{
		return feedbackBO.getIDProof(con);
	}
*/

	/**
	 * @param con
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getFeedBackIDs(String language) throws Exception{
		return feedbackBO.getFeedBackIDs(language);
	}

 public ArrayList getDetails(String refId) throws Exception                              //---added by Satbir Kumar--
 {
	 return feedbackBO.getDetails(refId);
 }

public boolean mailtouser(String refId,CitizenFeedbackDTO objdto) throws Exception {
	
	boolean mailsent = feedbackBO.mailtouser(refId,objdto);
	
	return mailsent;
}


}


