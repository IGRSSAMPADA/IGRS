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
 * FILE NAME: WillViewBD.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 12th December 2007 
 * MODIFIED ON:	   12th April 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE BUSINESS DELEGATE CLASS
 */

package com.wipro.igrs.willdeposit.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.willdeposit.dao.WillViewDAO;
import com.wipro.igrs.willdeposit.dto.WillDepositDTO;

/**
 * @author NIHAR M.
 * 
 */
public class WillViewBD {

	private Logger logger = (Logger) Logger.getLogger(WillViewBD.class);

	/**
	 * @param willId
	 * @param fromDate
	 * @param toDate
	 * @param status
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getWillViewDetails(String willId, String fromDate,
			String toDate, String status, String officeId, String lang) throws Exception {
		WillViewDAO viewDAO = new WillViewDAO();
		WillViewBD bd= new WillViewBD();
		String districtId=bd.getDistrictId(officeId);
		ArrayList list = viewDAO.getWillViewDetails(willId, fromDate, toDate,
				status, districtId);

		ArrayList returnList = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList lst = (ArrayList) list.get(i);
				WillDepositDTO dto = new WillDepositDTO();
				dto.setWillId((String) lst.get(0));
				dto.setFirstName((String) lst.get(1));
				dto.setCreatedBy((String) lst.get(2));
				dto.setMidName((String) lst.get(3));
				dto.setLastName((String) lst.get(4));
				dto.setAddress((String) lst.get(5));
				dto.setDespositDate((String) lst.get(6));
				dto.setWillStatus((String) lst.get(7));
				if("en".equalsIgnoreCase(lang)){
					if("Withdrawn".equalsIgnoreCase(status)){
						dto.setWill_stat_view("Withdrawn");
					}
					else if("Deposited".equalsIgnoreCase(status)){
						dto.setWill_stat_view("Deposited");
					}
					else if("Retrieved".equalsIgnoreCase(status)){
						dto.setWill_stat_view("Retrieved");
					}
					else if("Pending Retrieve".equalsIgnoreCase(status)){
						dto.setWill_stat_view("Pending Retrieve");
					}
					else if("Pending Withdraw".equalsIgnoreCase(status)){
						dto.setWill_stat_view("Pending Withdraw");
					}
					else if("Pending Deposit".equalsIgnoreCase(status)){
						dto.setWill_stat_view("Pending Deposit");
					}
					

				}
				else{
					if("Withdrawn".equalsIgnoreCase(status)){
						dto.setWill_stat_view("वापस लिया");
					}
					else if("Deposited".equalsIgnoreCase(status)){
						dto.setWill_stat_view("जमा");
					}
					else if("Retrieved".equalsIgnoreCase(status)){
						dto.setWill_stat_view("पुनः प्राप्त");
					}
					else if("Pending Retrieve".equalsIgnoreCase(status)){
						dto.setWill_stat_view("पुनः प्राप्त लंबित");
					}
					else if("Pending Withdraw".equalsIgnoreCase(status)){
						dto.setWill_stat_view("लंबित वापसी");
					}
					else if("Pending Deposit".equalsIgnoreCase(status)){
						dto.setWill_stat_view("लंबित जमा");
					}
				}
				dto.setDependantwillId((String) lst.get(8)==null?"":(String) lst.get(8));
				returnList.add(dto);
			}
		}
		return returnList;
	}
	
	public ArrayList getUpdateWillViewDetails(String willId, String fromDate,
			String toDate, String status, String officeId) throws Exception {
		WillViewDAO viewDAO = new WillViewDAO();
		WillViewBD viewBD = new WillViewBD();
		String districtId=viewBD.getDistrictId(officeId);
		ArrayList list = viewDAO.getUpdateWillViewDetails(willId, fromDate, toDate,
				status, districtId);

		ArrayList returnList = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList lst = (ArrayList) list.get(i);
				WillDepositDTO dto = new WillDepositDTO();
				dto.setWillId((String) lst.get(0));
				dto.setCreatedBy((String)lst.get(1));
				dto.setFirstName((String) lst.get(2));
				dto.setMidName((String) lst.get(3));
				dto.setLastName((String) lst.get(4));
				dto.setAddress((String) lst.get(5));
				dto.setDespositDate((String) lst.get(6));
				dto.setWillStatus((String) lst.get(7));

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
	public WillDepositDTO getwillIdDetails(String willid, String StrFunId, String lang)
			throws Exception {
		
	 
		WillDepositDTO dto = new WillDepositDTO();
		WillViewDAO dao = new WillViewDAO();
		dto=dao.getwillIdDetails(willid, lang);
//		ArrayList ret = dao.getwillIdDetails(willid);
//		if (ret != null) {
//			for (int i = 0; i < ret.size(); i++) {
//				ArrayList list = (ArrayList) ret.get(i);
//
//				String agent = (String) list.get(1);
//
//				if (agent.equalsIgnoreCase("TESTATOR")) {
//					dto.setWillId((String) list.get(0));
//					dto.setTotalFee((String) list.get(2));
//					dto.setDespositDate((String) list.get(3));
//					dto.setDrID((String) list.get(4));
//					dto.setDistrict((String) list.get(5));
//					dto.setFirstName((String) list.get(6));
//					dto.setMidName((String) list.get(7));
//					dto.setLastName((String) list.get(8));
//					if ((String) list.get(9) == "M")
//						dto.setGender("Male");
//					else
//						dto.setGender("Female");
//					if ((String) list.get(10) != null)
//						dto.setAge((String) list.get(10));
//					else
//						dto.setAge("");
//					dto.setFatherName((String) list.get(11));
//					dto.setMotherName((String) list.get(12));
//					dto.setSpouseName((String) list.get(13));
//					dto.setAddress((String) list.get(14));
//					dto.setCountry((String) list.get(16));
//					dto.setState((String) list.get(18));
//					dto.setDistrictId((String) list.get(19));
//					dto.setCity((String) list.get(20));
//					if ((String) list.get(21) != null)
//						dto.setPin((String) list.get(21));
//					else
//						dto.setPin("");
//
//					dto.setPhone(list.get(22)==null ?"":list.get(22).toString());
//					dto.setMphone(list.get(23)==null ?"":list.get(23).toString());
//					dto.setEmail((String) list.get(24));
//					dto.setIdProof((String) list.get(25));
//					dto.setIdProofNo((String) list.get(26));
//					dto.setWithdrawlDueDate((String) list.get(30));
//					dto.setRemarks((String) list.get(31));
//					dto.setWillStatus((String) list.get(32));
//
//				}
//
//				if (agent.equalsIgnoreCase("AGENT")) {
//
//					dto.setAgent((String) list.get(1));
//					dto.setAgentFirstName((String) list.get(6));
//					dto.setAgentMidName((String) list.get(7));
//					dto.setAgentLastName((String) list.get(8));
//					dto.setGender((String) list.get(6));
//
//					if ((String) list.get(9) == "M")
//						dto.setAgentGender("Male");
//					else
//						dto.setAgentGender("Female");
//					if ((String) list.get(10) != null)
//						dto.setAgentAge((String) list.get(10));
//					else
//						dto.setAgentAge("");
//
//					dto.setAgentFatherName((String) list.get(11));
//					dto.setAgentMotherName((String) list.get(12));
//					dto.setAgentSpouseName((String) list.get(13));
//					dto.setAgentAddress((String) list.get(14));
//					dto.setAgentCountry((String) list.get(16));
//					dto.setAgentState((String) list.get(18));
//					dto.setAgentDistrictId((String) list.get(19));
//					dto.setAgentCity((String) list.get(20));
//					dto.setAgentPin(list.get(21)==null ?"":list.get(21).toString());
//					dto.setAgentPhone(list.get(22)==null ?"":list.get(22).toString());
//					dto.setAgentMPhone(list.get(23)==null ?"":list.get(23).toString());
//					dto.setAgentEmail((String) list.get(24));
//					dto.setAgentIdProof((String) list.get(25));
//					dto.setAgentIdProofNo((String) list.get(26));
//				}
//			}
//		}

		return dto;
	}
	//anuj
	public String getDistrictId(String officeId){
		String districtId="";
		
		try {
			WillViewDAO dao= new WillViewDAO();
			districtId=dao.getDistrictId(officeId);
			if(districtId.length()<2){
				districtId="0"+districtId;
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return districtId;
	}
	
	public String getName(String userId){
		String name="";
		try {
			WillViewDAO dao = new WillViewDAO();
			
			name=dao.getName(userId);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return name;
	}
	//anuj

}
