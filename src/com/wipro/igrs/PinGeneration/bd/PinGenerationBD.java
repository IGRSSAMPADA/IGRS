/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   PinGenerationBD.java
 * Author      :   Samuel Prabhakaran
 * Description :   Represents the BD Class for Generate Pin.
 * ----------------------------------------------------------------------------
 * Version         Modified By      Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0            Samuel Prabhakaran   26th Dec, 2007	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.PinGeneration.bd;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wipro.igrs.PinGeneration.bo.PinGenerationBO;
import com.wipro.igrs.PinGeneration.dto.PinGenerationDTO;
import com.wipro.igrs.noEncumbrance.dao.NoEncumDAO;
import com.wipro.igrs.noEncumbrance.dto.NoEncumDTO;

/**
 * @author neegaga
 * 
 */

public class PinGenerationBD {
	boolean success = false;
	ArrayList pinsList = new ArrayList();

	PinGenerationBO bo = new PinGenerationBO();

	/**
	 * @param totPins
	 * @return
	 * @throws Exception
	 */

	/**
	 * ===========================================================================
	 * Method : getStateList() Description : Selecting all State from
	 * IGRS_STATE_MASTER . return type : Arraylist Author : Samuel Prabhakaran
	 * Created Date : 18 Feb,2008
	 * ===========================================================================
	 */

	public ArrayList getStateList() {
		return bo.getStateList();
	}

	/**
	 * ===========================================================================
	 * Method : getCountryList() Description : Selecting all Country from
	 * IGRS_COUNTRY_MASTER . return type : Arraylist Author : Samuel Prabhakaran
	 * Created Date : 18 Feb,2008
	 * ===========================================================================
	 */
	public ArrayList getCountryList(String lang) {
		return bo.getCountryList(lang);
	}

	/**
	 * ===========================================================================
	 * Method : getDistrictList() Description : Selecting all District from
	 * IGRS_DISTRICT_MASTER . return type : Arraylist Author : Samuel
	 * Prabhakaran Created Date : 18 Feb,2008
	 * ===========================================================================
	 */

	public ArrayList getDistrictList(String state, String lang) {
		return bo.getDistrictList(state,lang);
	}
	public ArrayList getStateList(String state, String lang) {
		return bo.getStateList(state,lang);
	}
	/**
	 * ===========================================================================
	 * Method : getphotoIdList() Description : Selecting all ID TYPE from
	 * IGRS_PHOTOID_PROOF_TYPE_MASTER . return type : Arraylist Author : Samuel
	 * Prabhakaran Created Date : 18 Feb,2008
	 * ===========================================================================
	 * 
	 */
	public ArrayList getphotoIdList(String lang) {
		return bo.getphotoIdList(lang);
	}

	public ArrayList getPropertyList(String property) {
		return bo.getPropertyList(property);
	}

	public String generatePin(PinGenerationDTO pingen, String roleId, String funId, String userId) {
		return bo.generatePin(pingen,roleId,funId,userId);
	}

	public ArrayList viewDetails(String reqId, String fromDate, String toDate) {
		return bo.viewDetails(reqId, fromDate, toDate);
	}

	public ArrayList check(String regNo, String propertyNo) {
		return bo.check(regNo, propertyNo);
	}

	public ArrayList getUpdateDate(String pinReqNo) {
		return bo.getUpdateDate(pinReqNo);
	}

	public ArrayList getOfficeId(String pinReqNo) {

		return bo.getOfficeId(pinReqNo);
	}

	public void readBLOBToFileGet(HttpServletResponse res, String pinReqNo) {
		bo.readBLOBToFileGet(res, pinReqNo);
	}

	public void readBLOBToFileGet1(HttpServletResponse res, String pinReqNo) {
		bo.readBLOBToFileGet1(res, pinReqNo);
	}

	/**
	 * getConvertedDate Returns String which returns a parsed date format.
	 * 
	 * @param dFromDate
	 * 
	 * @return String
	 * 
	 * @Exception
	 */
	public static String getConvertedDate(String dFromDate) {
		String inputDate = dFromDate;
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		System.out.print("  " + inputDate + " parses as ");
		String finalDate = "";
		Date newDate = new Date();
		try {
			newDate = formatter.parse(inputDate);
			SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
			finalDate = format.format(newDate);
		} catch (ParseException e) {
			System.out.print(e);
		}
		return finalDate;
	}

	/**
	 * getOracleDate Returns the parsed date.
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * 
	 * @return String
	 * 
	 * @ParseException
	 */
	public static String getOracleDate(String day, String month, String year) {
		String inputDate = day + "-" + month + "-" + year;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		System.out.print("  " + inputDate + " parses as ");
		String finalDate = "";
		Date newDate = new Date();
		try {
			newDate = formatter.parse(inputDate);
			SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
			finalDate = format.format(newDate);

			System.out.print(finalDate);
		} catch (ParseException e) {
			System.out.print(e);
		}
		return finalDate;
	}

	private String refIDgenerator() {
		String id = "SPL" + new Date().getTime();
		return id;
	}




public ArrayList regIdCheckInfo(String regisId) throws Exception{
	ArrayList dataSet, row;
	ArrayList retList = new ArrayList();
	try {
		
		dataSet = bo.regIdCheckInfo(regisId); 
		if (dataSet != null && dataSet.size() > 0) {
			PinGenerationDTO ndto;
			for (int iLoop = 0; iLoop < dataSet.size(); iLoop++) {
				row = (ArrayList) dataSet.get(iLoop);
				ndto = new PinGenerationDTO();
				ndto.setRegNoSearch(row.get(0).toString());
				ndto.setPropertyTxnId(row.get(1).toString());
				ndto.setPropertyTypeId(row.get(2).toString());
				ndto.setPropertyTypeLabel(row.get(3).toString());
				ndto.setStatus((String)row.get(4));
				retList.add(ndto);
			}
			dataSet.clear();
		}
	} catch (Exception e) {
	//	logger.error(e);
	}
	return retList;
}

public ArrayList<PinGenerationDTO> getClaimaintDetails(String propId)throws ServletException,IOException,SQLException,Exception{
	ArrayList dataSet, row;
	ArrayList <PinGenerationDTO>retList = new ArrayList<PinGenerationDTO>();
	try {
		
		dataSet = bo.getClaimaintDetails(propId); 
		if (dataSet != null && dataSet.size() > 0) {
			PinGenerationDTO ndto;
			for (int iLoop = 0; iLoop < dataSet.size(); iLoop++) {
				row = (ArrayList) dataSet.get(iLoop);
				ndto = new PinGenerationDTO();
				if(row.get(11)!=null && row.get(11).toString().equalsIgnoreCase("1"))
				{
					ndto.setOwnerType("ORGANISATION");
					ndto.setOrganisationName(row.get(8)!=null?row.get(8).toString():"-");
					ndto.setAutherisedName(row.get(9)!=null?row.get(9).toString():"-");
					ndto.setAddress(row.get(5)!=null?row.get(5).toString():"-");
					ndto.setMobileNumber(row.get(12)!=null?row.get(12).toString():"NA");
					ndto.setPhoneNumber(row.get(6)!=null?row.get(6).toString():"NA");
					ndto.setEmailID(row.get(7)!=null?row.get(7).toString():"NA");
				}
				if(row.get(11)!=null && row.get(11).toString().equalsIgnoreCase("2"))
				{
					ndto.setOwnerType("INDIVIDUAL");
					ndto.setFirstName(row.get(0)!=null?row.get(0).toString():"-");
					ndto.setMiddleName(row.get(1)!=null?row.get(1).toString():"-");
					ndto.setLastName(row.get(2)!=null?row.get(2).toString():"-");
					ndto.setGender(row.get(3)!=null?row.get(3).toString():"-");
					ndto.setAge(row.get(4)!=null?row.get(4).toString():"-");
					ndto.setFatherName(row.get(8)!=null?row.get(8).toString():"-");
					ndto.setAddress(row.get(5)!=null?row.get(5).toString():"-");
					ndto.setMobileNumber(row.get(12)!=null?row.get(12).toString():"NA");
					ndto.setPhoneNumber(row.get(6)!=null?row.get(6).toString():"NA");
					ndto.setEmailID(row.get(7)!=null?row.get(7).toString():"NA");
				
				}
				retList.add(ndto);
			}
			dataSet.clear();
		}
	} catch (Exception e) {
	//	logger.error(e);
	}
	return retList;

}

public String getRegTxnID(String pingen)
{
	return bo.getRegTxnID(pingen);
}

public String getDeedID(String getDeedID) throws ServletException,IOException,SQLException,Exception {
	return bo.getDeedID(getDeedID);
}
public void printPin(ArrayList propIds, PinGenerationDTO pingen,HttpServletRequest request,  HttpServletResponse response) throws Exception{
	 bo.printPin(propIds, pingen, request, response);
}
public String printPin1(ArrayList propIds, PinGenerationDTO pingen,HttpServletRequest request,  HttpServletResponse response) throws Exception{
	 return bo.printPin1(propIds, pingen, request, response);
}
public String checkPinStatus(PinGenerationDTO pingen)
{
	return bo.checkPinStatus(pingen);
}
public boolean changePin(PinGenerationDTO pingen)
{
	return bo.changePin(pingen);
}
}