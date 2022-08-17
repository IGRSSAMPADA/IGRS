/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	UserProfileBD.java
 * Author		:	Rafiq Rahiman.T 
 * Date			:   14/03/2008
 */
package com.wipro.igrs.useracctmgmt.bd;

import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

import com.wipro.igrs.ACL.dto.AclcreateDTO;
import com.wipro.igrs.useracctmgmt.bo.UserProfileBO;
import com.wipro.igrs.useracctmgmt.dao.UserProfileDAO;
import com.wipro.igrs.useracctmgmt.dto.UserAccntUnlockDTO;
import com.wipro.igrs.useracctmgmt.dto.UserProfileDTO;
import com.wipro.igrs.useracctmgmt.form.UserProfileForm;

public class UserProfileBD {
	//UserProfileDAO userProfileDAO = new UserProfileDAO();
	UserProfileBO userProfileBO = new UserProfileBO();
	public ArrayList getCountryList() throws Exception
	{
		return userProfileBO.getCountryList();
	}
	public ArrayList getStateList() throws Exception
	{
		return userProfileBO.getStateList();
	}
	public ArrayList getDistrictList() throws Exception
	{
		return userProfileBO.getDistrictList();
	}
	public ArrayList getPhotoproofList(String language) throws Exception
	{
		return userProfileBO.getPhotoproofList(language);
	}
	public ArrayList getHintList(String language) throws Exception
	{
		return userProfileBO.getHintList(language);
	}
	public UserProfileDTO getUserdetails(String name, String language, UserProfileForm userProfileForm, String type) throws Exception
	{
		return userProfileBO.getUserdetails(name,language,userProfileForm,type);
	}
	public void updateuserdetails(UserProfileForm userProfileForm, String name) throws Exception
	{
		userProfileBO.updateuserdetails(userProfileForm, name);
	}
	/**
	 * getCountry  
	 * Returns ArrayList of Countries.
	 * @param language 
	 *
	 * @return ArrayList
	 * @throws Exception 
	 *
	 * @Exception
	 */
	public ArrayList getCountry(String language) throws Exception {
		UserProfileDAO acldao = new UserProfileDAO();
		ArrayList ret = acldao.getCountry();
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				UserProfileDTO dto = new UserProfileDTO();
				dto.setUserCountryID((String) lst.get(0));
				if("en".equalsIgnoreCase(language))
				{
				dto.setCountry((String) lst.get(1));
				}
				else if("hi".equalsIgnoreCase(language))
				{
					dto.setCountry((String) lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}
	
	/**
	 * getState
	 * Returns ArrayList of States.
	 * @param language 
	 * @param stateId
	 *
	 * @return ArrayList
	 * @throws Exception 
	 *
	 */
	public ArrayList getState(String countryId, String language) throws Exception {
		UserProfileDAO acldao = new UserProfileDAO();
		ArrayList ret = acldao.getStateList(countryId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				AclcreateDTO dto = new AclcreateDTO();
				dto.setUserStateID((String)lst.get(0));
				if("en".equalsIgnoreCase(language))
				{
				dto.setStateName((String)lst.get(1));
				}
				else if("hi".equalsIgnoreCase(language))
				{
					dto.setStateName((String)lst.get(2));
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
	 * @param language 
	 *
	 * @return ArrayList
	 * @throws Exception 
	 *
	 */
	public ArrayList getDistrictCity(String stateId, String language) throws Exception {
		UserProfileDAO acldao = new UserProfileDAO();
		ArrayList ret = acldao.getCityDist(stateId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				AclcreateDTO dto = new AclcreateDTO();
				dto.setUserCityID((String)lst.get(0));
				if("en".equalsIgnoreCase(language))
				{
				dto.setDistrictName((String)lst.get(1));
				}
				else if("hi".equalsIgnoreCase(language))
				{
					dto.setDistrictName((String)lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}
	public ArrayList getLockedUser(UserAccntUnlockDTO uadto,String locale) throws Exception {
		UserProfileDAO acldao = new UserProfileDAO();
		
		return acldao.getLockedUser( uadto,locale) ;
	}
	public boolean unLockUser(String userID, String loggedUserId) throws Exception {
		 UserProfileDAO acldao = new UserProfileDAO();
		
		return acldao.unLockUser( userID,loggedUserId) ;
	}
	public String userDBpassword(String name) {
		String oldpassword="";
		oldpassword = userProfileBO.userDBpassword(name);
		return oldpassword;
	}
	public boolean changepassword(String newpassword, String name) {
		boolean success = userProfileBO.changepassword(newpassword,name);
		return success;
	}
	public ArrayList getspdetails(String userid, String license) throws Exception {
		ArrayList details = new ArrayList();
		
		details = userProfileBO.getspdetails(userid,license);
		
		return details;
		
	}
	public String getrolegroup(String userid) {
		String rolegroup = userProfileBO.getrolegroup(userid);
		return rolegroup;
	}
	public boolean updaterolegroup(String userid ,String rolegroupid) throws Exception {
		boolean updatesuccess = userProfileBO.updaterolegroup(userid,rolegroupid);
		return updatesuccess;
	}
	public ArrayList getpodetails(String userid, UserProfileDTO dto, String language) throws Exception {
		
		ArrayList details = new ArrayList();
		
		details = userProfileBO.getpodetails(userid,dto,language);
		
		return details;
	}
	
	//Added By Praveen for Tehsildar rights flow
	public ArrayList getrudetails(String userid, UserProfileDTO dto, String language) throws Exception {
		System.out.println("******************* Fetching User profile In UP BD");
		ArrayList details = new ArrayList();
		
		details = userProfileBO.getrudetails(userid,dto,language);
		
		return details;
	}
	
public ArrayList getTehsilList(String userid,  String language) throws Exception {
		
		ArrayList details = new ArrayList();
		
		details = userProfileBO.getTehsilList(userid,language);
		
		return details;
	}
	
	public boolean insertrightsduration(String durationfrom, String durationto,
			String pouserid,String loggedinuserid)  throws Exception{
		boolean success = userProfileBO.insertrightsduration(durationfrom,durationto,pouserid,loggedinuserid);
		return success;
	}
	public boolean insertrightsduration(String durationfrom, String durationto,
			String pouserid,String loggedinuserid, String tehsilId, String filepath, String fileName)  throws Exception{
		boolean success = userProfileBO.insertrightsduration(durationfrom,durationto,pouserid,loggedinuserid,tehsilId,  filepath,fileName);
		return success;
	}
	

	public boolean assignporights(String pouserid) throws Exception {
		boolean rights = userProfileBO.assignporights(pouserid);
		return rights;
	}
	
	//Added By Praveen For Tehsildar flow : Start 
	
	public boolean assignTehsildarRights(String ruuserid) throws Exception {
		boolean rights = userProfileBO.assignTehsildarrights(ruuserid);
		return rights;
	}
	
	public boolean updaterightsduration(String durationfrom, String durationto,
			String pouserid,String loggedinuserid )  throws Exception{
		boolean update = userProfileBO.updaterightsduration(durationfrom,durationto,pouserid,loggedinuserid);
		return update;
	}
	
	//Added by Praveen for Tehsildar Flow
	public boolean updateTehsildarrightsduration(String durationfrom, String durationto,
			String pouserid,String loggedinuserid, String tehsilId, String filepath )  throws Exception{
		boolean update = userProfileBO.updateTehsildarrightsduration(durationfrom,durationto,pouserid,loggedinuserid,tehsilId,filepath);
		return update;
	}
	
	public String saveFile(FormFile userFile, String userid)
	{	
		return userProfileBO.saveFile(userFile, userid);	
	}
	
	public boolean revokerights(String userid) throws Exception{
		boolean flag =userProfileBO.revokerights(userid);
		return flag;
	}
	public boolean revokTehsildarRights(String userid) throws Exception{
		boolean flag =userProfileBO.revokeTehsildarRights(userid);
		return flag;
	}
	
	//Added By Praveen for Tehsildar Flow:
	public boolean revokeTehsildarRights(String userid) throws Exception{
		boolean flag =userProfileBO.revokeTehsildarRights(userid);
		return flag;
	}
	public String getUsertype(String userid) throws Exception{
		String type=null;
		type=userProfileBO.getUsertype(userid);
		return type;
	}
   // To get Logged user district - Rahul
	public String getUserDistrict(String officeId,String language)throws Exception
	{	
		return userProfileBO.getUserDistrict(officeId,language);	
	}
	

}
