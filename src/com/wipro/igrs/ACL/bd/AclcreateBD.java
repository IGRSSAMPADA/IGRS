/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   AclcreateBD.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the BD Class for ACL.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  24th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.ACL.bd;

import java.util.ArrayList;

import com.wipro.igrs.ACL.dao.AclcreateDAO;
import com.wipro.igrs.ACL.dto.AclcreateDTO;
import com.wipro.igrs.ACL.form.AclcreateForm;
import com.wipro.igrs.UserRegistration.dao.UserRegistrationDAO;
import com.wipro.igrs.UserRegistration.dto.UserRegistrationDTO;

public class AclcreateBD {

	public AclcreateBD() {
	}

	AclcreateDAO acldao = new AclcreateDAO();

	/**
	 * Gets the list of UserId
	 * 
	 * @param String
	 *            of userid
	 * @return AclcreateDTO
	 * @throws Exception
	 */

	public AclcreateDTO getUserId(String userid,String locale) throws Exception {
		return acldao.getUserId(userid,locale);
	}

	/**
	 * Gets the list of UserList
	 * 
	 * @param String
	 *            of strfname
	 * @param String
	 *            of strlname
	 * @return ArrayList of AclcreateDTO
	 * @throws Exception
	 */
	public ArrayList getUserList(String strfname, String strlname,String language)
			throws Exception {
		return acldao.getUserList(strfname, strlname, language);
	}
	
	public ArrayList getUserList1(String strfname, String strlname, String language)
			throws Exception {
		return acldao.getUserList1(strfname, strlname, language);
	}

	/**
	 * update the Logindetails
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * 
	 * @throws Exception
	 */
	public void updateLogindetails(AclcreateForm aclcreateForm, String roleId, String funId, String userId) {
		acldao.updateLogindetails(aclcreateForm,roleId,funId,userId);

	}

	/**
	 * Gets the list of UseraclList
	 * 
	 * @return ArrayList of AclcreateDTO
	 * @throws Exception
	 */

	public ArrayList getUseraclList(String locale) throws Exception {

		return acldao.getUseraclList(locale);
	}
	
	public ArrayList getUseraclList1(String locale) throws Exception {

		return acldao.getUseraclList1(locale);
	}
	
	/**
	 * getCountry  
	 * Returns ArrayList of Countries.
	 *
	 * @return ArrayList
	 * @throws Exception 
	 *
	 * @Exception
	 */
	public ArrayList getCountry(String locale) throws Exception {
		AclcreateDAO acldao = new AclcreateDAO();
		ArrayList ret = acldao.getCountry();
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				AclcreateDTO dto = new AclcreateDTO();
				dto.setUserCountryID((String) lst.get(0));
				if(locale.equalsIgnoreCase("hi_IN")){
					dto.setCountry((String) lst.get(2));
				
				}else{
					dto.setCountry((String) lst.get(1));
			
				}

				list.add(dto);
			}
		}
		return list;
	}
	
	/**
	 * getState
	 * Returns ArrayList of States.
	 * @param stateId
	 *
	 * @return ArrayList
	 * @throws Exception 
	 *
	 */
	public ArrayList getState(String countryId,String locale) throws Exception {
		AclcreateDAO acldao = new AclcreateDAO();
		ArrayList ret = acldao.getStateList(countryId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				AclcreateDTO dto = new AclcreateDTO();
				dto.setUserStateID((String)lst.get(0));
				if(locale.equalsIgnoreCase("hi_IN")){
					dto.setStateName((String)lst.get(2));
					
			
				}else{
					dto.setStateName((String)lst.get(1));
				
				}

				list.add(dto);
			}
		}
		return list;
	}
	
	/**
	 * getState
	 * Returns ArrayList of Districts or Cities under the State chosen
	 * @param stateId
	 *
	 * @return ArrayList
	 * @throws Exception 
	 *
	 */
	public ArrayList getDistrictCity(String stateId,String locale) throws Exception {
		AclcreateDAO acldao = new AclcreateDAO();
		ArrayList ret = acldao.getCityDist(stateId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				AclcreateDTO dto = new AclcreateDTO();
				dto.setUserCityID((String)lst.get(0));
				if(locale.equalsIgnoreCase("hi_IN")){
					dto.setDistrictName((String)lst.get(2));
			
				}else{
					dto.setDistrictName((String)lst.get(1));
				
				}

				list.add(dto);
			}
		}
		return list;
	}
	public ArrayList getHintQuestions() throws Exception{
		
		AclcreateDAO acldao = null;
		try {
			acldao = new AclcreateDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList ret = acldao.getHintQuestions();
		
		ArrayList list = new ArrayList();

		if (ret != null) {
			
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				AclcreateDTO dto = new AclcreateDTO();
				dto.setHintQuestID((String) lst.get(0));
				dto.setHintQuestion((String) lst.get(1));
				list.add(dto);
				
			}
		}
		return list;
	}
	
	public boolean approveUser(String userid)throws Exception{
		boolean boo=false;
		boo=acldao.approveUser(userid);
		return boo;
		
	}
	public boolean rejectUser(String userid){
		boolean boo=false;
		boo=acldao.rejectUser(userid);
		return boo;
		
	}
	
	public ArrayList getOfficeListing() throws Exception
	{
		return acldao.getOfficeListing();
	}
}
