
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
 * FILE NAME: CitizenFeebackViewBD.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 23th June 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE BD FOR CITIZEN VIEW ACTION.
 */


package com.wipro.igrs.CitizenFeedback.bd;


import java.util.ArrayList;

import com.wipro.igrs.CitizenFeedback.bo.CitizenFeedbackViewBO;
import com.wipro.igrs.CitizenFeedback.dto.CitizenFeedbackDTO;

/**
 * @author NIHAR M.
 *
 */

public class CitizenFeebackViewBD {

	CitizenFeedbackViewBO feedbackViewBO = new CitizenFeedbackViewBO();

	
	
	public boolean email(CitizenFeedbackDTO citizenDTO) throws Exception
	{
		boolean pass;
		pass= feedbackViewBO.email(citizenDTO);
		return pass;
	}
	
	public ArrayList getPendingEstampApps(String id, String userId, String language) throws Exception
	{
			ArrayList list =  feedbackViewBO.getPendingEstampApps(id,userId,language);
			System.out.println("in bd" + list);
			
			return list;
	
	}
	
	public ArrayList getDetails(String id, String language) throws Exception

	{
		ArrayList list = feedbackViewBO.getDetails(id, language);
		return list;
	}
	public CitizenFeedbackDTO  viewCitizenFeedback(String id) throws Exception{
		return feedbackViewBO.viewCitizenFeedback(id);
	}

	public boolean updatestatus(String id, String language) throws Exception {
		
		boolean update = feedbackViewBO.updatestatus(id,language);
		
		
		return update;
	}

	public ArrayList getFilteredresults(String durationFrom, String durationTo,
			String refId, String status,String offid, String userId,String language)  throws Exception{
		
		ArrayList listOfCaveats = new ArrayList();
		String searchFields[] = new String[5];
		searchFields[0] = "" + offid;
		searchFields[1] = "" + refId;
		searchFields[2] = "" + status;
		searchFields[3] = "" + durationFrom;
		searchFields[4] = "" + durationTo;
		
		listOfCaveats = feedbackViewBO.getFilteredresults(searchFields,userId,language);
		
		
		return listOfCaveats;
	}

	
	
}


