
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
 * FILE NAME: CitizenFeedbackBO.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 23th June 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE BO FOR CITIZEN FEEDBACK ACTION.
 */

package com.wipro.igrs.CitizenFeedback.bo;

import java.util.ArrayList;

import com.wipro.igrs.CitizenFeedback.dao.CitizenFeedbackDAO;
import com.wipro.igrs.CitizenFeedback.dto.CitizenFeedbackDTO;

/**
 * @author NIHAR M.
 *
 */
public class CitizenFeedbackBO {


	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getCountry() throws Exception{

		CitizenFeedbackDAO dao = new CitizenFeedbackDAO();
		ArrayList ret = dao.getCountry();
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				CitizenFeedbackDTO dto = new CitizenFeedbackDTO();
				dto.setCountryId((String) lst.get(0));
				dto.setCountry((String) lst.get(1));
				list.add(dto);
			}
		}
		return list;
	}



	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getState(String con) throws Exception{

		CitizenFeedbackDAO dao = new CitizenFeedbackDAO();
		ArrayList ret = dao.getState(con);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				CitizenFeedbackDTO dto = new CitizenFeedbackDTO();
				dto.setStateId((String) lst.get(0));
				dto.setState((String) lst.get(1));
				list.add(dto);
			}
		}
		return list;
	}

	/**
	 * @param stateId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getDistrict(String language) throws Exception{

		CitizenFeedbackDAO dao = new CitizenFeedbackDAO();
		ArrayList ret = dao.getFdistrict();
		System.out.println(ret);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				CitizenFeedbackDTO dto = new CitizenFeedbackDTO();
				dto.setCityDistrictId((String) lst.get(0));
				System.out.println("at o index" + lst.get(0));
				if("hi".equalsIgnoreCase(language)){
				dto.setCityDistrict((String) lst.get(1));
				}
				else{
					dto.setCityDistrict((String) lst.get(2));
				}
				list.add(dto);
			}
		
		}
			return list;
		
	}
	

	/**
	 * @param commonId
	 * @return ArrayList
	 * @throws Exception
	 */
/*
	public ArrayList getIDProof(String commonId) throws Exception {
		CitizenFeedbackDAO dao = new CitizenFeedbackDAO();
		ArrayList ret = dao.getType(commonId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				CitizenFeedbackDTO dto = new CitizenFeedbackDTO();
				dto.setIdProofNumber((String) lst.get(0));
				dto.setIdProofType((String) lst.get(1));
				list.add(dto);
			}
		}
		return list;
	}
*/

	/**
	 * @param commonId
	 * @return ArrayList
	 * @throws Exception
	 */

	public ArrayList getFeedBackIDs(String language) throws Exception {
		
		CitizenFeedbackDAO dao = new CitizenFeedbackDAO();
		ArrayList ret = dao.getFServices();
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				CitizenFeedbackDTO dto = new CitizenFeedbackDTO();
				dto.setFeedbackFunctionID((String) lst.get(0));
				if("en".equalsIgnoreCase(language))
				{
				dto.setFeedbackFunctionName((String) lst.get(1));
				}
				else if("hi".equalsIgnoreCase(language))
				{
					dto.setFeedbackFunctionName((String) lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}

	/**
	 * @param feedbackDTO
	 * @return boolean
	 * @throws Exception
	 */
	public String submitFeedbackDetails(CitizenFeedbackDTO feedbackDTO,String language) throws Exception {

		CitizenFeedbackDAO feedbackDAO = new CitizenFeedbackDAO();
		String submitDetails = "";

		/*String[] param = new String[25];
		param[0]  = feedbackDTO.getFirstName();
		param[1]  = feedbackDTO.getMiddleName();
		param[2]  = feedbackDTO.getLastName();
		param[3]  = feedbackDTO.getGender();
		param[4]  = feedbackDTO.getAge();
		//if("INDIA".equalsIgnoreCase(feedbackDTO.getCountry())){
		param[5] = feedbackDTO.getCountry();
		
		param[6]  = feedbackDTO.getCountry();
		param[7]  = feedbackDTO.getState();
		param[8]  = feedbackDTO.getCityDistrict();
		param[9]  = feedbackDTO.getAddress();
		param[10] = feedbackDTO.getPostalCode();
		param[11] = feedbackDTO.getPhoneNumber();
		param[12] = feedbackDTO.getMobileNumber();
		param[13] = feedbackDTO.getEmailID();
		param[14] = feedbackDTO.getIdProofType();
		param[15] = feedbackDTO.getIdProofNumber();
		param[16] = feedbackDTO.getBankName();
		param[17] = feedbackDTO.getBankAddress();
		param[18] = feedbackDTO.getFatherName();
		param[19] = feedbackDTO.getMotherName();
		param[20] = feedbackDTO.getFeedbackFunctionID();
		param[21] = feedbackDTO.getTypeOfService();
		param[22] = feedbackDTO.getFeedback();
		param[23] = feedbackDTO.getServiceRefNumber();
		param[24] = feedbackDTO.getFirstName();
*/
		submitDetails =  feedbackDAO.getFinsertValue(feedbackDTO,language);
		return(submitDetails);
	}

public ArrayList getDetails(String refId) throws Exception
{
	CitizenFeedbackDAO dao = new CitizenFeedbackDAO();
	ArrayList ret = dao.getDetails(refId);
	ArrayList list = new ArrayList();

	if (ret != null) {
		for (int i = 0; i < ret.size(); i++) {
			ArrayList lst = (ArrayList) ret.get(i);
			CitizenFeedbackDTO dto = new CitizenFeedbackDTO();
			dto.setFirstName((String) lst.get(0));
			System.out.println(lst.get(0));
			dto.setEmailID((String) lst.get(1));
			System.out.println(lst.get(1));
			list.add(dto);
		}
	}
	return list;
}



public boolean mailtouser(String refId,CitizenFeedbackDTO objdto) throws Exception {
	
	CitizenFeedbackDAO dao = new CitizenFeedbackDAO();
	
	boolean mailsent = dao.mailtouser(refId,objdto);
	
			return mailsent;
}

}
