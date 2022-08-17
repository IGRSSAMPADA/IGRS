
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
 * FILE NAME: CitizenFeedbackViewBO.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 23th June 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE BO FOR CITIZEN VIEW ACTION.
 */

package com.wipro.igrs.CitizenFeedback.bo;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.wipro.igrs.CitizenFeedback.dao.CitizenFeedbackViewDAO;
import com.wipro.igrs.CitizenFeedback.dto.CitizenFeedbackDTO;

/**
 * @author NIHAR M.
 *
 */
public class CitizenFeedbackViewBO {


	/**
	 * @param userId 
	 * @param feedbackID
	 * @return CitizenFeedbackDTO
	 * @throws Exception 
	 */
	
	public ArrayList getPendingEstampApps(String id, String userId,String language) throws Exception
	{
		
		
		CitizenFeedbackViewDAO dao = new CitizenFeedbackViewDAO();
		ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list=dao.getPendingEstampApps(id,userId,language);
		
		System.out.println("in bo"+list);
		if(list!=null && list.size()>0){
			
			try{
			
				for (int i = 0; i < list.size(); i++) {
				ArrayList rowList = (ArrayList)list.get(i);
				CitizenFeedbackDTO objDashBoardDTO = new CitizenFeedbackDTO();
				
				objDashBoardDTO.setHdnreferenceId((String) rowList.get(0));
				objDashBoardDTO.setReferenceId((String) rowList.get(0));
				objDashBoardDTO.setFirstName((String) rowList.get(1));
				objDashBoardDTO.setEmailID((String) rowList.get(2));
				objDashBoardDTO.setCreatedDate((String) rowList.get(3));
				if("en".equalsIgnoreCase(language))
				{
				objDashBoardDTO.setType((String) rowList.get(4));
				}
				else if("hi".equalsIgnoreCase(language))
				{
				objDashBoardDTO.setType (rowList.get(4).toString().equalsIgnoreCase("FEEDBACK")?"प्रतिक्रिया ":"शिकायत ");
				}
				if("en".equalsIgnoreCase(language))
				{
				objDashBoardDTO.setFeedbackFunctionName((String) rowList.get(5));
				}
				else if("hi".equalsIgnoreCase(language))
				{
					objDashBoardDTO.setFeedbackFunctionName((String) rowList.get(7));
				}
				if("en".equalsIgnoreCase(language))
				{
				objDashBoardDTO.setStatus("Pending");
				}
				else if("hi".equalsIgnoreCase(language))
				{
					objDashBoardDTO.setStatus("लंबित");
				}
				
				pendingAppListFinal.add(objDashBoardDTO);
		
			}
				
			
				
			}
            catch(Exception e){
            	e.printStackTrace();
            	
            }
		}
		return pendingAppListFinal;
	}
	
	public ArrayList getDetails(String id, String language) throws Exception
	{
		CitizenFeedbackViewDAO dao = new CitizenFeedbackViewDAO();
		
		ArrayList ret = dao.getDetails(id);
		System.out.println(ret);
		ArrayList list = new ArrayList();
		
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				CitizenFeedbackDTO dto = new CitizenFeedbackDTO();
				dto.setFirstName((String) lst.get(0));
				System.out.println("at o index" + lst.get(0));
				dto.setEmailID((String) lst.get(1));
				String districtId=(String) lst.get(2);
				ArrayList listD =(ArrayList) dao.getDistrictName(districtId).get(0);
				if("hi".equalsIgnoreCase(language)){
					dto.setCityDistrict((String) listD.get(0));
				}
				else{
					dto.setCityDistrict((String) listD.get(1));
				}
				
				//dto.setCityDistrict((String) lst.get(2));
				
				if("hi".equalsIgnoreCase(language)){
					dto.setFeedbackFunctionName((String) lst.get(7));
				}
				else
				{
					dto.setFeedbackFunctionName((String) lst.get(3));
				}
				
				dto.setFeedback((String) lst.get(4));
				dto.setStatus((String) lst.get(5));
				dto.setDrcomments((String) lst.get(6));
				list.add(dto);
			}
		}
		return list;
	}
	
	public CitizenFeedbackDTO viewCitizenFeedback(String feedbackID) throws Exception{

		CitizenFeedbackDTO dto = new CitizenFeedbackDTO();
		CitizenFeedbackViewDAO dao = new CitizenFeedbackViewDAO();
		
		String[] param = new String[1];
		param[0] = feedbackID;
		ArrayList ret = dao.viewCitizenFeedback(param);

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList list = (ArrayList) ret.get(i);

				dto.setCitizenFeedbackId((String) list.get(0));
				dto.setFirstName((String) list.get(1));
				dto.setMiddleName((String) list.get(2));
				dto.setLastName((String) list.get(3));
				dto.setGender((String) list.get(4));
				dto.setAge((String) list.get(5));
				dto.setNationality((String) list.get(6));
				dto.setCountry((String) list.get(7));
				dto.setState((String) list.get(8));
				dto.setCityDistrict((String) list.get(9));
				dto.setAddress((String) list.get(10));
				dto.setPostalCode((String) list.get(11));
				dto.setPhoneNumber((String) list.get(12));
				dto.setMobileNumber((String) list.get(13));
				dto.setEmailID((String) list.get(14));
				dto.setIdProofType((String) list.get(15));
				dto.setIdProofNumber((String) list.get(16));
				dto.setBankName((String) list.get(17));
				dto.setBankAddress((String) list.get(18));
				dto.setFatherName((String) list.get(19));
				dto.setMotherName((String) list.get(20));
				//left for 4 
				dto.setBankName((String) list.get(25));
				dto.setBankAddress((String) list.get(26));
				
		}
	}
	return dto;
}

	public boolean email(CitizenFeedbackDTO citizenDTO) throws Exception{
		boolean pass;
		CitizenFeedbackViewDAO dao = new CitizenFeedbackViewDAO();
		pass = dao.getemail(citizenDTO);
		return pass;
	}

	public boolean updatestatus(String id, String language) throws Exception {
		
		CitizenFeedbackViewDAO dao = new CitizenFeedbackViewDAO();
		
		boolean update= dao.updatestatus(id,language);
		return update;
	}

	public ArrayList getFilteredresults(String param[], String userId, String language) throws Exception{
		
		CitizenFeedbackViewDAO dao = new CitizenFeedbackViewDAO();
		
		
		
		ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list = dao.getFilteredresults(param,userId,language);
		
		
	
			return list;
	}
	
}
