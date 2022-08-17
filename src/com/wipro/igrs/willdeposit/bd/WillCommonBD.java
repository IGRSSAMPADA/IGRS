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
 * FILE NAME: WillCommonBD.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING:	   9th May 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE BUSINESS DELEGATE CLASS
 */

package com.wipro.igrs.willdeposit.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.CitizenFeedback.dto.CitizenFeedbackDTO;
import com.wipro.igrs.willdeposit.dao.WillCommonDAO;
import com.wipro.igrs.willdeposit.dao.WillDepositDAO;
import com.wipro.igrs.willdeposit.dao.WillViewDAO;
import com.wipro.igrs.willdeposit.dto.WillDepositDTO;
import com.wipro.igrs.willdeposit.sql.CommonSQL;

/**
 * @author NIHAR M.
 * 
 */
public class WillCommonBD {

	private Logger logger = (Logger) Logger.getLogger(WillCommonBD.class);

	/**
	 * @return String
	 * @throws Exception
	 */
	public String getWillFeeValue(String StrFunId) throws Exception {
		ArrayList list = new ArrayList();
		ArrayList retList1 = new ArrayList();
		String feeVal = "";

		try {
			WillCommonDAO objCommonDao = new WillCommonDAO();
			list = objCommonDao.getWillFeeValue(StrFunId);
			retList1 = (ArrayList) list.get(0);
			feeVal = ((String) retList1.get(1));

		} catch (Exception e) {
			logger.debug("Error:-    " + e.getMessage());
		}
		return feeVal;
	}

	/**
	 * @param _retFunId
	 * @return String
	 * @throws Exception
	 */
	public String getWillOtherFeeValue(String _retFunId) throws Exception {
		String OthersDuty = "";
		try {
			WillCommonDAO objCommonDao = new WillCommonDAO();
			OthersDuty = objCommonDao.getWillOtherFeeValue(_retFunId);
		} catch (Exception e) {
			logger.debug("Exception in  getVisitOthersDuty()  " + e);
		}

		return OthersDuty;
	}
	public ArrayList getWillViewDetails(String willId, String fromDate,
			String toDate, String status, String officeId) throws Exception {
		WillCommonDAO viewDAO = new WillCommonDAO();
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
				dto.setDependantwillId((String) lst.get(8)==null?"":(String) lst.get(8));
				returnList.add(dto);
			}
		}
		return returnList;
	}
	
	//methods to display country, state and district names in HINDI or ENGLISH based on the language selected, start
	public String countryName(String countryId, String lang) throws Exception{
		String name="";
		
		WillCommonDAO dao = new WillCommonDAO();
		ArrayList names=dao.getCountry(countryId);
		if (names != null) {
			for (int i = 0; i < names.size(); i++) {
				ArrayList lst = (ArrayList) names.get(i);
				if("hi".equalsIgnoreCase(lang)){
					name=(String)lst.get(1);
				}
				else{
					name=(String)lst.get(0);
				}
			}
		}
		return name;
	}
	public String stateName(String stateId, String countryId, String lang) throws Exception{
		String name="";
		WillCommonDAO dao = new WillCommonDAO();
		ArrayList names=dao.getState(stateId, countryId);
		if (names != null) {
			for (int i = 0; i < names.size(); i++) {
				ArrayList lst = (ArrayList) names.get(i);
				if("hi".equalsIgnoreCase(lang)){
					name=(String)lst.get(1);
				}
				else{
					name=(String)lst.get(0);
				}
			}
		}
		return name;
	}
	public String districtName(String stateId, String districtId, String lang) throws Exception{
		String name="";
		WillCommonDAO dao = new WillCommonDAO();
		ArrayList names=dao.getDistrict(stateId, districtId);
		if (names != null) {
			for (int i = 0; i < names.size(); i++) {
				ArrayList lst = (ArrayList) names.get(i);
				if("hi".equalsIgnoreCase(lang)){
					name=(String)lst.get(1);
				}
				else{
					name=(String)lst.get(0);
				}
			}
		}
		return name;
	}
	//methods to display country, state and district names in HINDI or ENGLISH based on the language selected, end

//to get PHOTO ID PROOF NAME, base on the language, start
public String getIdType(String typeId, String lang)throws Exception{
	WillCommonDAO dao = new WillCommonDAO();
	ArrayList ret = dao.getTypeName(typeId);
	ArrayList list = new ArrayList();
	String idName="";
	if (ret != null) {
		for (int i = 0; i < ret.size(); i++) {
			ArrayList lst = (ArrayList) ret.get(i);
			
			
			if("hi".equalsIgnoreCase(lang)){
				idName=(String) lst.get(1);
			}
			else{
				idName=(String) lst.get(0);
			}
			//dto.setState((String) lst.get(1));
			
		}
	}
	return idName;
}
}
//to get PHOTO ID PROOF NAME, base on the language, end

