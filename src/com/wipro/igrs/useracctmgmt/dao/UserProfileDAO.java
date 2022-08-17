/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	UserProfileDAO.java
 * Author		:	Rafiq Rahiman.T 
 * Date			:   18/03/2008
 */
package com.wipro.igrs.useracctmgmt.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.PropertyResourceBundle;

import org.apache.struts.upload.FormFile;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.useracctmgmt.dto.UserAccntUnlockDTO;
import com.wipro.igrs.useracctmgmt.dto.UserProfileDTO;
import com.wipro.igrs.useracctmgmt.form.UserProfileForm;
import com.wipro.igrs.officearea.dto.TehsilDTO;
import com.wipro.igrs.useracctmgmt.sql.UserCommonSQL;

public class UserProfileDAO {
	ArrayList countryList = new ArrayList();
	ArrayList stateList = new ArrayList();
	ArrayList districtList = new ArrayList();
	ArrayList photoproofList = new ArrayList();
	ArrayList hintList = new ArrayList();
	
	String sql = null;
	ArrayList subList = null;
	//UserProfileDTO dto = null;

	public UserProfileDAO() {

	}

	/**
	 * ===========================================================================
	 * Method : getCountryList() Description : Get All the Country List from the
	 * IGRS_COUNTRY_MASTER table. return type : ArrayList Author : Rafiq
	 * Rahiman.T Created Date : 18 March,2008
	 * ====================================================================
	 */
	public ArrayList getCountryList() throws Exception {
		DBUtility dbUtility = null;
		try {
			sql = UserCommonSQL.SELECT_COUNTRY_LIST;
			dbUtility = new DBUtility();
			dbUtility.createStatement();

			ArrayList mainList1 = dbUtility.executeQuery(sql);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				UserProfileDTO dto = new UserProfileDTO();
				dto.setName(subList.get(0).toString());
				dto.setValue(subList.get(0).toString());
				countryList.add(dto);
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return countryList;
	}

	/**
	 * ===========================================================================
	 * Method : getStateList() Description : Get All the State List from the
	 * IGRS_STATE_MASTER table. return type : ArrayList Author : Rafiq Rahiman.T
	 * Created Date : 18 March,2008
	 * ====================================================================
	 */
	public ArrayList getStateList() throws Exception {
		DBUtility dbUtility = null;
		try {
			sql = UserCommonSQL.SELECT_STATE_LIST;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			ArrayList mainList1 = dbUtility.executeQuery(sql);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				UserProfileDTO dto = new UserProfileDTO();
				dto.setName(subList.get(1).toString());
				dto.setValue(subList.get(0).toString());
				stateList.add(dto);
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return stateList;
	}

	/**
	 * ===========================================================================
	 * Method : getDistrictList() Description : Get All the District List from
	 * the IGRS_DISTRICT_MASTER table. return type : ArrayList Author : Rafiq
	 * Rahiman.T Created Date : 18 March,2008
	 * ====================================================================
	 */
	public ArrayList getDistrictList() throws Exception {
		DBUtility dbUtility = null;
		try {
			sql = UserCommonSQL.SELECT_DISTRICT_LIST;
			dbUtility = new DBUtility();
			dbUtility.createStatement();

			ArrayList mainList1 = dbUtility.executeQuery(sql);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				UserProfileDTO dto = new UserProfileDTO();
				dto.setName(subList.get(1).toString());
				dto.setValue(subList.get(0).toString());
				districtList.add(dto);

			}
		} catch (Exception e) {
			e.getStackTrace();

		} finally {
			dbUtility.closeConnection();
		}
		return districtList;
	}

	/**
	 * ===========================================================================
	 * Method : getPhotoproofList() Description : Get All the PhotoId Proof List
	 * from the IGRS_PHOTOID_PROOF_TYPE_MASTER table. return type : ArrayList
	 * Author : Rafiq Rahiman.T Created Date : 18 March,2008
	 * ====================================================================
	 * @param language 
	 */
	public ArrayList getPhotoproofList(String language) throws Exception {
		DBUtility dbUtility = null;
		try {
			sql = UserCommonSQL.SELECT_PHOTO_PROOF_LIST;
			dbUtility = new DBUtility();
			dbUtility.createStatement();

			ArrayList mainList1 = dbUtility.executeQuery(sql);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				UserProfileDTO dto = new UserProfileDTO();
				if("en".equalsIgnoreCase(language))
				{
				dto.setName(subList.get(1).toString());
				}
				else if("hi".equalsIgnoreCase(language))
				{
					dto.setName(subList.get(2).toString());
				}
				dto.setValue(subList.get(0).toString());
				photoproofList.add(dto);

			}
		} catch (Exception e) {
			e.getStackTrace();

		} finally {
			dbUtility.closeConnection();
		}
		return photoproofList;
	}

	/**
	 * ===========================================================================
	 * Method : getHintList() Description : Get All the Hint Questions from
	 * IGRS_USER_HINT_QUESTIONS table. return type : UserProfileDTO Author :
	 * Rafiq Rahiman.T Created Date : 20 March,2008
	 * ====================================================================
	 * @param language 
	 */
	public ArrayList getHintList(String language) throws Exception {
		DBUtility dbUtility = null;
		try {
			sql = UserCommonSQL.SELECT_HINT_LIST;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			ArrayList mainList1 = dbUtility.executeQuery(sql);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				UserProfileDTO dto = new UserProfileDTO();
				if("en".equalsIgnoreCase(language))
				{
				dto.setName(subList.get(1).toString());
				}
				else if("hi".equalsIgnoreCase(language))
				{
					dto.setName(subList.get(2).toString());
				}
				dto.setValue(subList.get(0).toString());
				hintList.add(dto);

			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return hintList;
	}

	/**
	 * ===========================================================================
	 * Method : getUserdetails() Description : Get All the User Details from
	 * IGRS_USER_REG_DETAILS table. return type : UserProfileDTO Author : Rafiq
	 * Rahiman.T Created Date : 18 March,2008
	 * ====================================================================
	 * @param language 
	 * @param userProfileForm 
	 * @param type 
	 */
	public UserProfileDTO getUserdetails(String name, String language, UserProfileForm userProfileForm, String type) throws Exception {
		UserProfileDTO dto = new UserProfileDTO();
		
		
		DBUtility dbUtility = null;
		try 
		{
			dbUtility = new DBUtility();
			
			String Dob="";
			
			if("I".equalsIgnoreCase(type))
			{
				String sql1 = UserCommonSQL.SELECT_DOB_INTERNAL;
				dbUtility.createPreparedStatement(sql1);
				String sd1[] = new String[1];
				sd1[0] = name;;
				Dob=dbUtility.executeQry(sd1);
			}
			
			sql = UserCommonSQL.SELECT_APPL_DETAILS;
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[3];
			sd[0] = name;
			sd[1] = name;
			sd[2] = name;
			ArrayList list = dbUtility.executeQuery(sd);
			ArrayList list1 = (ArrayList) list.get(0);
			
			dto.setFirstName(list1.get(0).toString());
			dto.setMiddleName(list1.get(1)==null ?" ":list1.get(1).toString());
			dto.setLastName(list1.get(2).toString());
			dto.setGender(list1.get(3).toString());
			if("en".equalsIgnoreCase(language))
			{
				dto.setOccupation(list1.get(4)==null ?"":list1.get(4).toString());
			}
			else if("hi".equalsIgnoreCase(language))
			{
				dto.setOccupation(list1.get(25)==null ?"":list1.get(25).toString());
			}
			//dto.setOccupation(list1.get(4).toString());
			dto.setFatherName(list1.get(5).toString());
			dto.setMotherName(list1.get(6)==null ?"":list1.get(6).toString());
			//dto.setMotherName(list1.get(6).toString());
			dto.setSpouseName(list1.get(7)==null ?"":list1.get(7).toString());
			//dto.setSpouseName(list1.get(7).toString());
			dto.setAddress(list1.get(8).toString());
			//dto.setCountry(list1.get(9).toString());
			dto.setUserCountryID(list1.get(9).toString());
			//dto.setState(list1.get(10).toString());
			dto.setUserStateID(list1.get(10).toString());
			//dto.setCity(list1.get(11).toString());
			dto.setUserCityID(list1.get(11).toString());
			dto.setPostalCode(list1.get(12)==null ?"":list1.get(12).toString());
			dto.setPhoneNumber(list1.get(13)==null ?"":list1.get(13).toString());
			//dto.setPhoneNumber(list1.get(13).toString());
			dto.setMobileNumber(list1.get(14)==null ?"":list1.get(14).toString());
			//dto.setMobileNumber(list1.get(14).toString());
			dto.setPrimaryEmail(list1.get(15)==null ?"":list1.get(15).toString());
			//dto.setPrimaryEmail(list1.get(15).toString());
			dto.setAlternateEmail(list1.get(16)==null ?"":list1.get(16).toString());
			//dto.setAlternateEmail(list1.get(16).toString());
			dto.setPhotoProof(list1.get(17)==null ?"":list1.get(17).toString());
			//dto.setPhotoProof(list1.get(17).toString());
			dto.setIdNo(list1.get(18)==null ?"":list1.get(18).toString());
			//dto.setIdNo(list1.get(18).toString());
			dto.setBankName(list1.get(19)==null ?"":list1.get(19).toString());
			//dto.setBankName(list1.get(19).toString());
			dto.setBankAddr(list1.get(20)==null ?"":list1.get(20).toString());
			//dto.setBankAddr(list1.get(20).toString());
			if("I".equalsIgnoreCase(type))
			{
				dto.setDob(Dob);
			}
			else
			{
				dto.setDob(list1.get(21)==null ?"":list1.get(21).toString());
			}
			
			//dto.setDob(list1.get(21).toString());
			dto.setHintQuestion(list1.get(22)==null ?"":list1.get(22).toString());
			//dto.setHintQuestion(list1.get(22).toString());
			dto.setHintAnswer(list1.get(23)==null ?"":list1.get(23).toString());
			//dto.setHintAnswer(list1.get(23).toString());
			dto.setRegNo(list1.get(24)==null ?"":list1.get(24).toString());
			dto.setSaveUrl(list1.get(26)!=null && !list1.get(26).toString().equalsIgnoreCase("") ?list1.get(26).toString():"");
			
			//dto.setRegNo(list1.get(24).toString());
			/*
			 * String dob[] = list1.get(21).toString().split("/");
			 * dto.setDay(dob[0]); dto.setMonth(dob[1].trim());
			 * dto.setYear(dob[2]);
			 */
		} catch (Exception e) {
			//System.out.println("Caugut in Exception: " + e);
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return dto;
	}

	/**
	 * ===========================================================================
	 * Method : updateuserdetails() Description : Update the User Details into
	 * IGRS_USER_REG_DETAILS table. return type : Void Author : Rafiq Rahiman.T
	 * Created Date : 18 March,2008
	 * ====================================================================
	 */
	public void updateuserdetails(UserProfileForm userProfileForm, String name) {
		if(userProfileForm.getUsrProDTO().getSaveUrl()!=null && userProfileForm.getUsrProDTO().getSaveUrl()!="")
		{
		
		String param[] = new String[22];
		param[0] = userProfileForm.getUsrProDTO().getFirstName();
		param[1] = userProfileForm.getUsrProDTO().getLastName();
		param[2] = userProfileForm.getUsrProDTO().getGender();
		param[3] = userProfileForm.getUsrProDTO().getAddress();
		param[4] = userProfileForm.getUsrProDTO().getFatherName();
		param[5] = userProfileForm.getUsrProDTO().getUserStateID();
		param[6] = userProfileForm.getUsrProDTO().getUserCityID();
		param[7] = userProfileForm.getUsrProDTO().getPostalCode();
		param[8] = userProfileForm.getUsrProDTO().getPhoneNumber();
		param[9] = userProfileForm.getUsrProDTO().getMobileNumber();
		param[10] = userProfileForm.getUsrProDTO().getPrimaryEmail();
		param[11] = userProfileForm.getUsrProDTO().getAlternateEmail();
		param[12] = userProfileForm.getUsrProDTO().getIdNo();
		param[13] = userProfileForm.getUsrProDTO().getBankName();
		param[14] = userProfileForm.getUsrProDTO().getBankAddr();
		param[15] = userProfileForm.getUsrProDTO().getHintQuestion();
		param[16] = userProfileForm.getUsrProDTO().getHintAnswer();
		param[17] = userProfileForm.getUsrProDTO().getRegNo();
		param[18] = userProfileForm.getUsrProDTO().getSpouseName();
		param[19] = userProfileForm.getUsrProDTO().getPhotoProof();
		param[20] = userProfileForm.getUsrProDTO().getUserCountryID();
		param[21] = name;
		
		sql = UserCommonSQL.UPDATE_APPL_DETAILS_URL;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			//if (boo) {
				dbUtility.commit();
			//} else
				//dbUtility.rollback();
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
	}
		else
		{
			String param[] = new String[23];
			param[0] = userProfileForm.getUsrProDTO().getFirstName();
			param[1] = userProfileForm.getUsrProDTO().getLastName();
			param[2] = userProfileForm.getUsrProDTO().getGender();
			param[3] = userProfileForm.getUsrProDTO().getAddress();
			param[4] = userProfileForm.getUsrProDTO().getFatherName();
			param[5] = userProfileForm.getUsrProDTO().getUserStateID();
			param[6] = userProfileForm.getUsrProDTO().getUserCityID();
			param[7] = userProfileForm.getUsrProDTO().getPostalCode();
			param[8] = userProfileForm.getUsrProDTO().getPhoneNumber();
			param[9] = userProfileForm.getUsrProDTO().getMobileNumber();
			param[10] = userProfileForm.getUsrProDTO().getPrimaryEmail();
			param[11] = userProfileForm.getUsrProDTO().getAlternateEmail();
			param[12] = userProfileForm.getUsrProDTO().getIdNo();
			param[13] = userProfileForm.getUsrProDTO().getBankName();
			param[14] = userProfileForm.getUsrProDTO().getBankAddr();
			param[15] = userProfileForm.getUsrProDTO().getHintQuestion();
			param[16] = userProfileForm.getUsrProDTO().getHintAnswer();
			param[17] = userProfileForm.getUsrProDTO().getRegNo();
			param[18] = userProfileForm.getUsrProDTO().getSpouseName();
			param[19] = userProfileForm.getUsrProDTO().getPhotoProof();
			param[20] = userProfileForm.getUsrProDTO().getUserCountryID();
			
			String path=userProfileForm.getUsrProDTO().getSignPath()+userProfileForm.getUsrProDTO().getUniqueId()+"/"+userProfileForm.getUsrProDTO().getSignature();
			
			System.out.println(path);
			param[21] = path;
			param[22] = name;
			
			sql = UserCommonSQL.UPDATE_APPL_DETAILS;
			DBUtility dbUtility = null;
			try {
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(sql);
				boolean boo = dbUtility.executeUpdate(param);
				//if (boo) {
					dbUtility.commit();
				//} else
					//dbUtility.rollback();
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
		}
	}
	
	public ArrayList getCountry() {
		ArrayList list = null;
		IGRSCommon igrsCommon;
		try {
			igrsCommon = new IGRSCommon();
			list = igrsCommon.getCountry();
		} catch (Exception e) {
			 e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return list;
	}

public ArrayList getLockedUser(UserAccntUnlockDTO uadto,String locale) throws Exception {
	
	ArrayList mainList = new ArrayList();
	ArrayList SubList = new ArrayList();
	ArrayList userDetailList = new ArrayList();
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String sql = UserCommonSQL.GET_LOCKED_USER;
		String temp[] = new String[1];
		temp[0]=uadto.getUserId();
		dbUtility.createPreparedStatement(sql);
		mainList=dbUtility.executeQuery(temp);
		if(mainList.size()!=0 && mainList!=null)
		{
			for(int i=0; i<mainList.size() ;i++)
			{
				subList = (ArrayList) mainList.get(i);
				UserAccntUnlockDTO dto = new UserAccntUnlockDTO();
				dto.setLockedUserId(subList.get(0).toString());
				String status = subList.get(1).toString();
				
					if(status.equalsIgnoreCase("L"))
					{
						if(locale.equalsIgnoreCase("hi_IN")){
									dto.setLockedUserStatus("लॉक");
									}else{
										dto.setLockedUserStatus("Locked");
									}
					}
				
				userDetailList.add(dto);
				
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		dbUtility.closeConnection();
	}
	
	return userDetailList;
}

public boolean unLockUser(String userID, String loggedUserId) throws Exception {
	
	boolean flag=false;
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String sql =UserCommonSQL.UNLOCK_USER;
		String temp[] = new String[2];
		temp[0]=loggedUserId;// updated by logged in user
		temp[1]=userID;//locked user Id
		dbUtility.createPreparedStatement(sql);
		flag=dbUtility.executeUpdate(temp);
		
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	return flag;
}

public String userDBpassword(String name) {
	
	
	String userDBpassword="";
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String sql =UserCommonSQL.SELECT_USER_PASSWORD;
		String temp[] = new String[1];
		temp[0]=name;// logged in userid
		dbUtility.createPreparedStatement(sql);
		userDBpassword=dbUtility.executeQry(temp);
						
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	return userDBpassword;
}

public boolean changepassword(String newpassword, String name) {
	
	boolean success=false;
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String sql =UserCommonSQL.UPDATE_USER_PASSWORD;
		String temp[] = new String[2];
		temp[0]=newpassword;// updated by logged in user
		temp[1]=name;//locked user Id
		dbUtility.createPreparedStatement(sql);
		success=dbUtility.executeUpdate(temp);
		
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	return success;
}

public ArrayList getspdetails(String userid, String license) throws Exception {
	
	ArrayList details = new ArrayList();
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String sql =UserCommonSQL.SELECT_SP_DETAILS;
		String temp[] = new String[2];
		temp[0]=license;
		temp[1]=userid;
		dbUtility.createPreparedStatement(sql);
		details=dbUtility.executeQuery(temp);
		
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally {
		dbUtility.closeConnection();
	}
	return details;
}

public String getrolegroup(String userid) {
	String rolegroup=null;
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String sql =UserCommonSQL.SELECT_ROLE_GROUP;
		String temp[] = new String[1];
		temp[0]=userid;
		dbUtility.createPreparedStatement(sql);
		rolegroup=dbUtility.executeQry(temp);
		
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return rolegroup;
}

public boolean updaterolegroup(String userid,String rolegroupid) throws Exception {
	boolean updatesucess = false;
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String sql =UserCommonSQL.UPDATE_ROLEGROUP;
		String temp[] = new String[2];
		temp[0]=rolegroupid;
		temp[1]=userid;
		dbUtility.createPreparedStatement(sql);
		updatesucess=dbUtility.executeUpdate(temp);
		
		if(updatesucess==true)
		{
			String sql1 =UserCommonSQL.UPDATE_USERLIST;
			String temp1[] = new String[2];
			temp1[0]="3";
			temp1[1] = userid;
			dbUtility.createPreparedStatement(sql1);
		boolean userlist=dbUtility.executeUpdate(temp1);
		
		if(userlist=true)
		{
			System.out.println("userlist and rolegroup updated");
		}
		else
		{
			System.out.println("failed to update userlist");
		}
		}
		
		else
		{
			System.out.println("update rolegroup total fail");
		}
	
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally {
		dbUtility.closeConnection();
	}
	return updatesucess;
}

public ArrayList getpodetails(String userid, UserProfileDTO dto, String language) throws Exception {
	ArrayList details = new ArrayList();
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String sql14=null;
		if("en".equalsIgnoreCase(language))
		{
			sql14 =UserCommonSQL.SELECT_EN_PO_DETAILS;
		}
		else if("hi".equalsIgnoreCase(language))
		{
			sql14=UserCommonSQL.SELECT_HI_PO_DETAILS;
		}
		
		String temp[] = new String[1];
		temp[0]=userid;
		dbUtility.createPreparedStatement(sql14);
		details=dbUtility.executeQuery(temp);
		
		
		String sql2 = UserCommonSQL.SELECT_DATES_DETAILS ;
		String temp1[] = new String[2];
		temp1[0]=userid;
		temp1[1]="A";
		ArrayList dates = new ArrayList();
		dbUtility.createPreparedStatement(sql2);
		dates=dbUtility.executeQuery(temp1);
		
		System.out.println("the checked dates" + dates);
		
		if(dates!=null  && dates.size()!=0  )
			{
			
			for (int i = 0; i < dates.size(); i++) {
				ArrayList rowList = (ArrayList)dates.get(i);
				dto.setPorightsfromdate((String) rowList.get(0));
				dto.setPorightstodate((String) rowList.get(1));
				dto.setPoupdate("0");
			}
			}
			
		else
		{
			dto.setPorightsfromdate("NA");
			dto.setPorightstodate("NA");
			dto.setPoupdate("1");
		}
	}
	
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally {
		dbUtility.closeConnection();
	}
	return details;
}

//Added By Praveen For Tehsildar Flow : Start
public ArrayList getTehsilList(String userid, String language) throws Exception {
	ArrayList details = new ArrayList();
	ArrayList tehsilRowList = new ArrayList();
	
	ArrayList tehsilList = new ArrayList();
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String sql14=null;
		if("en".equalsIgnoreCase(language))
		{
			sql14 =UserCommonSQL.Tehsil_list;
		}
		else if("hi".equalsIgnoreCase(language))
		{
			sql14=UserCommonSQL.Tehsil_list_hindi;
		}
		
		String temp[] = new String[1];
		temp[0]=userid;
		dbUtility.createPreparedStatement(sql14);
		details=dbUtility.executeQuery(temp);
		
		if (details !=null && details.size()!=0){
			for (int i=0; i<details.size(); i++){
			tehsilRowList=(ArrayList)details.get(i);
			TehsilDTO tehsilDto=new TehsilDTO();
			tehsilDto.setId ( (String)tehsilRowList.get(0)); 
			tehsilDto.setName((String)tehsilRowList.get(1));
			
					
			
			tehsilList.add(tehsilDto);
			
			
			
			}
		}
		
		
		
	}	
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally {
		dbUtility.closeConnection();
	}
	return tehsilList;
}

public ArrayList getrudetails(String userid, UserProfileDTO dto, String language) throws Exception {
	ArrayList details = new ArrayList();
	DBUtility dbUtility = null;
	try
	{
		
		System.out.println("Fetching RU Details");
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String sql14=null;
		if("en".equalsIgnoreCase(language))
		{
			sql14 =UserCommonSQL.Registered_User_Details;
		}
		else if("hi".equalsIgnoreCase(language))
		{
			sql14=UserCommonSQL.Registered_User_Details_hindi;
		}
		
		String temp[] = new String[2];
		temp[0]="1";
		temp[1]=userid;
		
		dbUtility.createPreparedStatement(sql14);
		details=dbUtility.executeQuery(temp);
		
		
		String sql2 = UserCommonSQL.SELECT_DATES_DETAILS_RU ;
		String temp1[] = new String[3];
		temp1[0]=userid;
		temp1[1]="A";
		temp1[2]="1";
		ArrayList dates = new ArrayList();
		dbUtility.createPreparedStatement(sql2);
		dates=dbUtility.executeQuery(temp1);
		
		System.out.println("the checked dates" + dates);
		
		if(dates!=null  && dates.size()!=0  )
			{
			
			for (int i = 0; i < dates.size(); i++) {
				ArrayList rowList = (ArrayList)dates.get(i);
				dto.setRightsFromDate((String) rowList.get(0));
				dto.setRightsToDate((String) rowList.get(1));
				// Added tehsil id,filepath ,filename
				dto.setTehsilId((String) rowList.get(2));
				dto.setFilePath((String) rowList.get(3));
				//dto.setUploadDocument((String) rowList.get(4));
				dto.setDocumentName((String) rowList.get(4));
				dto.setPoupdate("0");
			}
			}
			
		else
		{
			dto.setRightsFromDate("NA");
			dto.setRightsToDate("NA");
			
			dto.setPoupdate("1");
		}
	}
	
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally {
		dbUtility.closeConnection();
	}
	return details;
}

//End

public boolean insertrightsduration(String durationfrom, String durationto,
		String pouserid,String loggedinuserid) throws Exception {
boolean updatesucess = false;
DBUtility dbUtility = null;
	try
	{
		
			dbUtility = new DBUtility();
		   String SQL1 = "select IGRS_RIGHTS_SEQ.NEXTVAL from dual";
		    dbUtility.createStatement();
		    				      
		   				    
		    String number2 = dbUtility.executeQry(SQL1);
		    
		    
		    
		
		dbUtility.createStatement();
		String sql =UserCommonSQL.INSERT_PO_DURATION;
		String temp[] = new String[6];
		temp[0] = number2;
		temp[1]=durationfrom;
		temp[2]=durationto;
		temp[3]=pouserid;
		temp[4]="A";
		temp[5]=loggedinuserid;
		dbUtility.createPreparedStatement(sql);
		updatesucess=dbUtility.executeUpdate(temp);
		
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally {
		dbUtility.closeConnection();
	}
	return updatesucess;
}


//Added By Praveen for Tehsildar flow
public boolean insertrightsduration(String durationfrom, String durationto,
		String pouserid,String loggedinuserid, String tehsilId, String filepath, String fileName) throws Exception {
	System.out.println("***********inside insertrightsduration DAO ");
boolean updatesucess = false;
DBUtility dbUtility = null;
	try
	{
		
			dbUtility = new DBUtility();
		   String SQL1 = "select IGRS_RIGHTS_SEQ.NEXTVAL from dual";
		    dbUtility.createStatement();
		    String number2 = dbUtility.executeQry(SQL1);
		    
		    
		    
		
		dbUtility.createStatement();
		String sql =UserCommonSQL.INSERT_Tehsildar_DURATION;
		String temp[] = new String[10];
		temp[0] = number2;
		temp[1]=durationfrom;
		temp[2]=durationto;
		temp[3]=pouserid;
		temp[4]="A";
		temp[5]=loggedinuserid;
		temp[6]="1";
		temp[7]=tehsilId;
		temp[8]=filepath;
		temp[9]=fileName;
		dbUtility.createPreparedStatement(sql);
		updatesucess=dbUtility.executeUpdate(temp);
		
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally {
		dbUtility.closeConnection();
	}
	return updatesucess;
}


public boolean assignporights(String pouserid) throws Exception {
	boolean rights =false;
	DBUtility dbUtility = null;
	dbUtility = new DBUtility();
	try
	{
		String temp2[] = new String[1];
		temp2[0]="Public Office Role Group";
		String sql2 = UserCommonSQL.GET_ROLEGROUP_ID;
		dbUtility.createPreparedStatement(sql2);
		String rolegroupid=dbUtility.executeQry(temp2);
		
		
		
		String sql =UserCommonSQL.UPDATE_ROLEGROUP;
		String temp[] = new String[2];
		temp[0]=rolegroupid;
		temp[1]=pouserid;
		dbUtility.createPreparedStatement(sql);
		rights=dbUtility.executeUpdate(temp);
		
		if(rights==true)
		{
			String temp4[] = new String[1];
			temp4[0]="PUB_OFF";
			String sql4 = UserCommonSQL.GET_USERTYPE_ID;
			dbUtility.createPreparedStatement(sql4);
			String usertypeid=dbUtility.executeQry(temp4);
			
			String sql1 =UserCommonSQL.UPDATE_USERLIST;
			String temp1[] = new String[2];
			temp1[0] = usertypeid;
			temp1[1] = pouserid;
			dbUtility.createPreparedStatement(sql1);
			dbUtility.executeUpdate(temp1);
		}
		else
		{
			System.out.println("failed to update userlist");
		}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally {
		dbUtility.closeConnection();
	}
	
	return rights;
}


//Added By Praveen For Tehsildar Flow
public boolean assignTehsildarRights(String userid) throws Exception {
	boolean rights =false;
	DBUtility dbUtility = null;
	dbUtility = new DBUtility();
	try
	{
		String temp2[] = new String[1];
		//temp2[0]="Tehsildar gole group"; As per DB role group master 
		temp2[0]="TEHSILDAR ROLE GROUP";
		String sql2 = UserCommonSQL.GET_ROLEGROUP_ID;
		dbUtility.createPreparedStatement(sql2);
		String rolegroupid=dbUtility.executeQry(temp2);
		
		
		
		String sql =UserCommonSQL.UPDATE_ROLEGROUP;
		String temp[] = new String[2];
		temp[0]=rolegroupid;
		temp[1]=userid;
		dbUtility.createPreparedStatement(sql);
		rights=dbUtility.executeUpdate(temp);
		
		if(rights==true)
		{
			String temp4[] = new String[1];
			temp4[0]="TEHSILDAR";
			String sql4 = UserCommonSQL.GET_USERTYPE_ID;
			dbUtility.createPreparedStatement(sql4);
			String usertypeid=dbUtility.executeQry(temp4);
			
			String sql1 =UserCommonSQL.UPDATE_USERLIST;
			String temp1[] = new String[2];
			temp1[0] = usertypeid;
			temp1[1] = userid;
			dbUtility.createPreparedStatement(sql1);
			dbUtility.executeUpdate(temp1);
		}
		else
		{
			System.out.println("failed to update userlist");
		}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally {
		dbUtility.closeConnection();
	}
	
	return rights;
}



public boolean updaterightsduration(String durationfrom, String durationto,
		String pouserid,String loggedinuserid) throws Exception {
	
	boolean updatesuccess= false;
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String sql =UserCommonSQL. UPDATE_RIGHTS_DURATION;
		String temp[] = new String[4];
		temp[0]=durationfrom;
		temp[1]=durationto;
		temp[2]=loggedinuserid;
		
		temp[3]=pouserid;
		dbUtility.createPreparedStatement(sql);
		updatesuccess=dbUtility.executeUpdate(temp);
		
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally {
		dbUtility.closeConnection();
	}
	return updatesuccess;
	
}


//Added By Praveen for Tehsildar flow
public boolean updateTehsildarrightsduration(String durationfrom, String durationto,
		String pouserid,String loggedinuserid, String tehsilId, String filepath) throws Exception {
	System.out.println("updateTehsildarrightsduration DAO");
	boolean updatesuccess= false;
	DBUtility dbUtility = null;
	try
	{
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String sql =UserCommonSQL. UPDATE_Tehsildar_RIGHTS_DURATION;
		String temp[] = new String[8];
		temp[0]=durationfrom;
		temp[1]=durationto;
		temp[2]=loggedinuserid;
		temp[3]=tehsilId;
		// changes by Rahul 
		temp[4]=filepath;
		temp[5]=pouserid;
		temp[6]="1";
		temp[7]="A";
		/*temp[4]=pouserid;
		temp[5]="1";
		temp[6]="A";
		temp[7]=filepath;*/
		
		dbUtility.createPreparedStatement(sql);
		updatesuccess=dbUtility.executeUpdate(temp);
		
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally {
		dbUtility.closeConnection();
	}
	return updatesuccess;
	
}


public String SaveFile(FormFile doc, String userid){
	
	Properties prop = new Properties();
	InputStream input = null;
String commonPath=null;
String path=null;
FileOutputStream outputStream = null;
FormFile formFile = null;
	try {

		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		commonPath=pr.getValue("upload.location");

	//For File Upload :Start
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd_HH-mm-ss");
	Date date = new Date();
	String datetime=dateFormat.format(date);
	System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
	//FormFile doc//=userProfileForm.getCertificate();
	
     
       
       System.out.println("file name is = " +doc.getFileName());
    
      
        path=commonPath +  File.separator+ "Tehsildar"+ File.separator+userid+File.separator+ datetime+doc.getFileName();
        File newfile=new File(path);
        	
        	if(newfile.getParentFile().mkdirs() ){
        		newfile.createNewFile();
        	}
        	else{
        		throw new IOException("Failed to create directory " + newfile.getParent());
        	}
        	outputStream = new FileOutputStream(newfile);
        	outputStream.write(doc.getFileData());
        
        
	
	
	}
        //END
        	
       catch(Exception e){
    	   
    	   e.printStackTrace();
    	  return "";
       }
        	
        	
	   finally{
      	 if (outputStream != null) {
             try{
          	   outputStream.close();    
          	    
             }
      		 catch(Exception e){
      			       			 System.err.println("Exception while closing output stream = "+e);
      			       			 return "";
      		 }
      		 
           }
     
	   }
	
	return path;
}

	public boolean revokerights(String userid) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {

			String sql = UserCommonSQL.REVOKE_PROCEDURE;
			dbUtility = new DBUtility();
			CallableStatement clstmt = dbUtility.createCallableStatement(sql);
			clstmt.setString(1, userid);
			 flag = clstmt.execute();
			System.out.println(flag);
			
			
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
return flag;
	}
	//Added by Praveen For Tehsildar flow
	public boolean revokeTehsildarRights(String userid) throws Exception {
		boolean flag = false;
		DBUtility dbUtility = null;
		try {

			String sql = UserCommonSQL.REVOKE_TEHSILDAR_RIGHTS;
			dbUtility = new DBUtility();
			CallableStatement clstmt = dbUtility.createCallableStatement(sql);
			clstmt.setString(1, userid);
			 flag = clstmt.execute();
			System.out.println(flag);
			
			
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
return flag;
	}
	
	
	public String getUsertype(String userid) throws Exception {
		String type = null;
		DBUtility dbUtility = null;
		try {

			String sql = UserCommonSQL.GET_USER_TYPE;
			dbUtility = new DBUtility();
			String temp[] = new String[1];
			temp[0]=userid;
				dbUtility.createPreparedStatement(sql);
			 type = dbUtility.executeQry(temp);
			System.out.println(type);
			
			
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
return type;
		
	}
	
	public String getUserDistrict(String officeId,String language) throws Exception {
		String type = null;
		DBUtility dbUtility = null;
		try {
			String sql="";
			if("en".equalsIgnoreCase(language))
			{
				sql =UserCommonSQL.GET_LOGGED_USER_DISTRICT;
			}
			else if("hi".equalsIgnoreCase(language))
			{
				sql=UserCommonSQL.GET_LOGGED_USER_DISTRICT_HINDI;
			}			
			//String sql = UserCommonSQL.GET_LOGGED_USER_DISTRICT;
			dbUtility = new DBUtility();
			String temp[] = new String[1];
			temp[0]=officeId;
				dbUtility.createPreparedStatement(sql);
			 type = dbUtility.executeQry(temp);
			System.out.println(type);
			
			
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
return type;
		
	}
	
}
