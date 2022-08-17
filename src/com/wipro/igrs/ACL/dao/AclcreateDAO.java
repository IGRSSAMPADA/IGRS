/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   AclcreateDAO.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the DAO Class for ACL.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  24th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.ACL.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.ACL.dto.AclcreateDTO;
import com.wipro.igrs.ACL.form.AclcreateForm;
import com.wipro.igrs.ACL.sql.AclcreateCommonSQL;
import com.wipro.igrs.UserRegistration.action.CryptoLibrary;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.useracctmgmt.dto.UserProfileDTO;
import com.wipro.igrs.useracctmgmt.sql.UserCommonSQL;

public class AclcreateDAO {

	private Logger logger = (Logger) Logger.getLogger(AclcreateDAO.class);
	private ArrayList userList = new ArrayList();
	private ArrayList useraclList = new ArrayList();
	DBUtility dbUtility = null;
	String sql = null;
	ArrayList subList = null;
	AclcreateDTO dto = null;
	IGRSCommon igrsCommon = null;
	String usrStus="";
	
	ArrayList officeList = new ArrayList();
	/* DAO constructor */
	public AclcreateDAO() {

		try {
			logger.debug("we are in AclcreateDAO");
			dbUtility = new DBUtility();
		} catch (Exception e) {
			logger.error("AclcreateDAO in dao start" + e.getStackTrace());
		}finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/* get UserId Details */
	public AclcreateDTO getUserId(String userid,String locale) throws Exception {
		try {
			logger.debug("we are in searchacl method");
			sql = AclcreateCommonSQL.SELECT_USER_ID;
			String uid[] = new String[3];
			dbUtility.getDBConnection();
			dbUtility.createPreparedStatement(sql);
			uid[0] = userid;
			uid[1] = userid;
			uid[2] = userid;
			ArrayList list = dbUtility.executeQuery(uid);
			ArrayList list1 = (ArrayList) list.get(0);
			dto = new AclcreateDTO();
			dto.setUserId(list1.get(0).toString());
			dto.setFirstName(list1.get(1).toString());
			dto.setMidName(list1.get(2)==null ?"":list1.get(2).toString());
			dto.setLastName(list1.get(3).toString());
			if(list1.get(4).toString()!=null){
				
				if(locale.equalsIgnoreCase("hi_IN")){
					if (list1.get(4).toString().equalsIgnoreCase("M"))
					{
						dto.setGender("पुस्र्ष");
					}	
					else{
						dto.setGender("महिला");
						}
					dto.setDesignation((String)list1.get(23));
				}else{
					if (list1.get(4).toString().equalsIgnoreCase("M"))
					{
						dto.setGender("Male");
					}	
					else{
						dto.setGender("Female");
					}
					dto.setDesignation((String)list1.get(22));
				}
			}else{
				dto.setGender("");
			}
			
			
			dto.setPhNumber(list1.get(5)==null ?"":list1.get(5).toString());
			dto.setMobNumber(list1.get(6)==null ?"":list1.get(6).toString());
			//dto.setMobNumber(list1.get(5).toString());
			dto.setEmailId(list1.get(7)==null ?"":list1.get(7).toString());
			dto.setAlterEmailId(list1.get(8)==null ?"":list1.get(8).toString());
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MMM/yyyy");
			if(list1.get(9)!=null){
				String dob = (String) list1.get(9);
				String newDt = sdf2.format(sdf1.parse(dob));
				dto.setDob(newDt);		
			}
			else {
				dto.setDob("");
			}
		//	dto.setDesignation(list1.get(10)==null ?"":list1.get(10).toString());
			dto.setAddress(list1.get(11)==null ?"":list1.get(11).toString());
			dto.setUserCountryID(list1.get(12)==null ?"":list1.get(12).toString());
			dto.setUserStateID(list1.get(13)==null ?"":list1.get(13).toString());
			dto.setUserCityID(list1.get(14)==null ?"":list1.get(14).toString());
			dto.setPostalCode(list1.get(15)==null ?"":list1.get(15).toString());
			if(locale.equalsIgnoreCase("hi_IN")){
				dto.setCntry(list1.get(19)==null ?"":list1.get(19).toString());
				dto.setState(list1.get(20)==null ?"":list1.get(20).toString());
				dto.setDstrct(list1.get(21)==null ?"":list1.get(21).toString());
			}else{
				dto.setCntry(list1.get(16)==null ?"":list1.get(16).toString());
				dto.setState(list1.get(17)==null ?"":list1.get(17).toString());
				dto.setDstrct(list1.get(18)==null ?"":list1.get(18).toString());	
			}

			
			sql = AclcreateCommonSQL.SELECT_USER_ID;

			
		} catch (Exception e) {
			e.getStackTrace();
			e.printStackTrace();
		}finally {
			dbUtility.closeConnection();
		}
		return dto;
	}

	/* get UserList Details */
	public ArrayList getUserList(String strfname, String strlname, String locale)
			throws Exception {
		try {
			sql = AclcreateCommonSQL.SELECT_FL_LIST;
			// dbUtility.createStatement();
			dbUtility.getDBConnection();
			dbUtility.createPreparedStatement(sql);
			String uname[] = new String[2];
			uname[0] = strfname;
			uname[1] = strlname;
			ArrayList list = dbUtility.executeQuery(uname);

			for (int i = 0; i < list.size(); i++) {
				ArrayList list1 = (ArrayList) list.get(i);
				dto = new AclcreateDTO();
				dto.setUserId(list1.get(0).toString());
				dto.setFirstName(list1.get(1).toString());
				dto.setLastName(list1.get(2).toString());
				usrStus=list1.get(3)==null ?"":list1.get(3).toString();
				if(locale.equalsIgnoreCase("hi_IN")){
					if(usrStus.equalsIgnoreCase("A")){
						dto.setUserStatus("सक्रिय");
				}
				else if(usrStus.equalsIgnoreCase("P")){
						dto.setUserStatus("स्वीकृति के लिए लंबित");	
				}
				else {
						dto.setUserStatus("अस्वीकृत");
				}
				}else{

						if(usrStus.equalsIgnoreCase("A")){
								dto.setUserStatus("Active");
						}
						else if(usrStus.equalsIgnoreCase("P")){
								dto.setUserStatus("Pending for Approval");	
						}
						else {
								dto.setUserStatus("Rejected");
						}
				}
				userList.add(dto);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}finally {
			dbUtility.closeConnection();
		}
		return userList;
	}
	
	public ArrayList getUserList1(String strfname, String strlname, String locale)
			throws Exception {
		try {
			sql = AclcreateCommonSQL.SELECT_FL_LIST1;
			// dbUtility.createStatement();
			dbUtility.getDBConnection();
			dbUtility.createPreparedStatement(sql);
			String uname[] = new String[2];
			uname[0] = strfname;
			uname[1] = strlname;
			ArrayList list = dbUtility.executeQuery(uname);

			for (int i = 0; i < list.size(); i++) {
				ArrayList list1 = (ArrayList) list.get(i);
				dto = new AclcreateDTO();
				dto.setUserId(list1.get(0).toString());
				dto.setFirstName(list1.get(1).toString());
				dto.setLastName(list1.get(2).toString());
				usrStus=list1.get(3)==null ?"":list1.get(3).toString();
//						if(usrStus.equalsIgnoreCase("A")){
//								dto.setUserStatus("Active");
//						}
				if(locale.equalsIgnoreCase("hi_IN")){
					dto.setUserStatus("स्वीकृति के लिए लंबित");		
				}else{

						if(usrStus.equalsIgnoreCase("P")){
								dto.setUserStatus("Pending for Approval");	
						}
				}
//						else {
//								dto.setUserStatus("Rejected");
//						}
				userList.add(dto);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}finally {
			dbUtility.closeConnection();
		}
		return userList;
	}

	/* get Useracl List Details */

	public ArrayList getUseraclList(String locale) throws Exception {
		try {
			sql = AclcreateCommonSQL.SELECT_USER_LIST;
			dbUtility.getDBConnection();
			dbUtility.createStatement();
			// dbUtility.createPreparedStatement(sql);

			ArrayList list = dbUtility.executeQuery(sql);

			for (int i = 0; i < list.size(); i++) {
				ArrayList list1 = (ArrayList) list.get(i);
				dto = new AclcreateDTO();
				dto.setUserId(list1.get(0).toString());
				dto.setFirstName(list1.get(1).toString());
				dto.setLastName(list1.get(2).toString());
				usrStus=list1.get(3)==null ?"":list1.get(3).toString();
				
				if(locale.equalsIgnoreCase("hi_IN")){
					if(usrStus.equalsIgnoreCase("A")){
						dto.setUserStatus("सक्रिय");
				}
				else if(usrStus.equalsIgnoreCase("P")){
						dto.setUserStatus("स्वीकृति के लिए लंबित");	
				}
				else {
						dto.setUserStatus("अस्वीकृत");
				}
				}else{
					
				if(usrStus.equalsIgnoreCase("A")){
						dto.setUserStatus("Active");
				}
				else if(usrStus.equalsIgnoreCase("P")){
						dto.setUserStatus("Pending for Approval");	
				}
				else {
						dto.setUserStatus("Rejected");
				}
				}
				useraclList.add(dto);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}finally {
			dbUtility.closeConnection();
		}
		return useraclList;
	}
	
	public ArrayList getUseraclList1(String locale) throws Exception {
		try {
			sql = AclcreateCommonSQL.SELECT_USER_LIST1;
			dbUtility.getDBConnection();
			dbUtility.createStatement();
			// dbUtility.createPreparedStatement(sql);

			ArrayList list = dbUtility.executeQuery(sql);

			for (int i = 0; i < list.size(); i++) {
				ArrayList list1 = (ArrayList) list.get(i);
				dto = new AclcreateDTO();
				dto.setUserId(list1.get(0).toString());
				dto.setFirstName(list1.get(1).toString());
				dto.setLastName(list1.get(2).toString());
				usrStus=list1.get(3)==null ?"":list1.get(3).toString();
//				if(usrStus.equalsIgnoreCase("A")){
//						dto.setUserStatus("Active");
//				}
				if(locale.equalsIgnoreCase("hi_IN")){
					if(usrStus.equalsIgnoreCase("P")){
						dto.setUserStatus("स्वीकृति के लिए लंबित");	
				}	
				}else{
					if(usrStus.equalsIgnoreCase("P")){
						dto.setUserStatus("Pending for Approval");	
				}	
				}

				
//				else {
//						dto.setUserStatus("Rejected");
//				}
				useraclList.add(dto);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}finally {
			dbUtility.closeConnection();
		}
		return useraclList;
	}

	/* update the details */
	public void updateLogindetails(AclcreateForm aclcreateForm, String roleId, String funId, String userId) {
		
		dto = new AclcreateDTO();
		String param[] = new String[10];
		String pparam[] = new String[11];
		String resetPwd=aclcreateForm.getDto().getResetPass();
		logger.debug("sql values in dao..." + sql);
		boolean boo=false;
		try {
			igrsCommon = new IGRSCommon();
			if("YES".equalsIgnoreCase(resetPwd))
			{
				CryptoLibrary crypt = new CryptoLibrary();		        
		        String encryptpswd = crypt.SHAencryption(aclcreateForm.getDto().getUserId());
		        pparam[0] = aclcreateForm.getDto().getPhNumber();
				pparam[1] = aclcreateForm.getDto().getMobNumber();
				pparam[2] = aclcreateForm.getDto().getEmailId();
				pparam[3] = aclcreateForm.getDto().getAlterEmailId();
				pparam[4] = aclcreateForm.getDto().getAddress();
				pparam[5] = aclcreateForm.getDto().getUserCountryID();
				pparam[6] = aclcreateForm.getDto().getUserStateID();
				pparam[7] = aclcreateForm.getDto().getUserCityID();
				pparam[8] = aclcreateForm.getDto().getPostalCode();
				pparam[9] = encryptpswd;   
				
				pparam[10] = aclcreateForm.getDto().getUserId();
				//pparam[10] = aclcreateForm.getDto().getUserId();
				
				dbUtility = new DBUtility();
				sql = AclcreateCommonSQL.UPDATE_SEARCH_PWD_DATA;
				dbUtility.createPreparedStatement(sql);
				boo = dbUtility.executeUpdate(pparam);
				
			}
			else{
				param[0] = aclcreateForm.getDto().getPhNumber();
				param[1] = aclcreateForm.getDto().getMobNumber();
				param[2] = aclcreateForm.getDto().getEmailId();
				param[3] = aclcreateForm.getDto().getAlterEmailId();
				param[4] = aclcreateForm.getDto().getAddress();
				param[5] = aclcreateForm.getDto().getUserCountryID();
				param[6] = aclcreateForm.getDto().getUserStateID();
				param[7] = aclcreateForm.getDto().getUserCityID();
				param[8] = aclcreateForm.getDto().getPostalCode();
				param[9] = aclcreateForm.getDto().getUserId();
				dbUtility = new DBUtility();
				sql = AclcreateCommonSQL.UPDATE_SEARCH_DATA;
				dbUtility.createPreparedStatement(sql);
			    boo = dbUtility.executeUpdate(param);
			}
			
			if (boo) {
				dbUtility.commit();
				igrsCommon.saveLogDet("IGRS_USER_REG_DETAILS","UPDATE","T",funId,userId,roleId);
				logger.debug("---->data updated");
			}

			else
			{
				dbUtility.rollback();
				igrsCommon.saveLogDet("IGRS_USER_REG_DETAILS","UPDATE","F",funId,userId,roleId);
				logger.debug("---->data not updated:" + sql);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList getCountry() {
		ArrayList list = null;
		IGRSCommon igrsCommon;
		try {
			igrsCommon = new IGRSCommon();
			list = igrsCommon.getCountry();
		} catch (Exception e) {
			logger.info("Exception in getCountry():" + e);
		}
		return list;
	}

public ArrayList getStateList(String countryId) {
		ArrayList list = null;
		IGRSCommon igrsCommon;

		try {
			igrsCommon = new IGRSCommon();
			list = igrsCommon.getState(countryId);
		} catch (Exception e) {
			logger.info("Exception in getState():" + e);
		}
		return list;
	}

public ArrayList getCityDist(String state) {
		ArrayList list = null;
		IGRSCommon igrsCommon;

		try {
			igrsCommon = new IGRSCommon();
			list = igrsCommon.getDistrict(state);
		} catch (Exception e) {
			logger.info("Exception in getState():" + e);
		}
		return list;
	}

public ArrayList getHintQuestions() {
		logger.info("DAO:   We are in getHintQuestions()");
		ArrayList list = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			 list = dbUtility.executeQuery("SELECT QUESTION_ID,QUESTION_TEXT " +
					 "FROM IGRS_USER_HINT_QUESTIONS WHERE QUESTION_STATUS='A'");
		} catch (Exception e) {
			logger.info("Exception in getCountry():" + e);
		}finally {
			try {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return list;
	}

public boolean approveUser(String userID) {
	String param[] = new String[1];
	param[0] = userID;
	sql = AclcreateCommonSQL.APPROVE_UPDATE_USER;
	boolean boo=false;
	try {
		dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(sql);
		boo = dbUtility.executeUpdate(param);
		if (boo) {
			dbUtility.commit();
		} else
			dbUtility.rollback();
	} catch (Exception e) {
		System.out.println(e);
		e.getStackTrace();
	} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception x) {
			x.getStackTrace();
		}
	}
	return boo;
}

public boolean rejectUser(String userID) {
	String param[] = new String[1];
	param[0] = userID;
	sql = AclcreateCommonSQL.REJECT_UPDATE_USER;
	boolean boo=false;
	try {
		dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(sql);
		boo = dbUtility.executeUpdate(param);
		if (boo) {
			dbUtility.commit();
		} else
			dbUtility.rollback();
	} catch (Exception e) {
		System.out.println(e);
		e.getStackTrace();
	} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception x) {
			x.getStackTrace();
		}
	}
	return boo;
}

public ArrayList getOfficeListing() throws Exception {
	try {
		sql = "SELECT OFFICE_ID, OFFICE_NAME FROM IGRS_OFFICE_MASTER WHERE OFFICE_STATUS = 'A'";
		dbUtility = new DBUtility();
		dbUtility.createStatement();

		ArrayList mainList1 = dbUtility.executeQuery(sql);
		for (int i = 0; i < mainList1.size(); i++) {
			subList = (ArrayList) mainList1.get(i);
			dto = new AclcreateDTO();
			dto.setName(subList.get(1).toString());
			dto.setValue(subList.get(0).toString());
			officeList.add(dto);

		}
	} catch (Exception e) {
		e.getStackTrace();

	} finally {
		dbUtility.closeConnection();
	}
	return officeList;
}

}
