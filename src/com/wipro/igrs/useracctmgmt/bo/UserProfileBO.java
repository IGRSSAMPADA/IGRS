/**
 * 
 */
package com.wipro.igrs.useracctmgmt.bo;

import java.util.ArrayList;


import com.wipro.igrs.useracctmgmt.dao.UserProfileDAO;
import com.wipro.igrs.useracctmgmt.dto.UserProfileDTO;
import com.wipro.igrs.useracctmgmt.form.UserProfileForm;

/**
 * @author rafnah
 *
 */
public class UserProfileBO
{
	UserProfileDAO userProfileDAO = new UserProfileDAO();
	public ArrayList getCountryList() throws Exception
	{
		return userProfileDAO.getCountryList();
	}
	public ArrayList getStateList() throws Exception
	{
		return userProfileDAO.getStateList();
	}
	public ArrayList getDistrictList() throws Exception
	{
		return userProfileDAO.getDistrictList();
	}
	public ArrayList getPhotoproofList(String language) throws Exception
	{
		return userProfileDAO.getPhotoproofList(language);
	}
	public ArrayList getHintList(String language) throws Exception
	{
		return userProfileDAO.getHintList(language);
	}
	public UserProfileDTO getUserdetails(String name, String language, UserProfileForm userProfileForm, String type) throws Exception
	{
		return userProfileDAO.getUserdetails(name,language,userProfileForm,type);
	}
	public void updateuserdetails(UserProfileForm userProfileForm, String name) throws Exception
	{
		userProfileDAO.updateuserdetails(userProfileForm, name);
	}
	public String userDBpassword( String name) {
		
		String oldpassword="";
		oldpassword = userProfileDAO.userDBpassword(name);
		return oldpassword;
	}
	public boolean changepassword(String newpassword, String name) {
		boolean success = userProfileDAO.changepassword(newpassword,name);
		return success;
	}
	public ArrayList getspdetails(String userid, String license) throws Exception {
		
		ArrayList details = new ArrayList();
		
		details = userProfileDAO.getspdetails(userid,license);
		
		return details;
	}
	public String getrolegroup(String userid) {
		String rolegroup = userProfileDAO.getrolegroup(userid);
		return rolegroup;
	}
	public boolean updaterolegroup(String userid,String rolegroupid) throws Exception {
		boolean updatesuccess = userProfileDAO.updaterolegroup(userid,rolegroupid);
		return updatesuccess;
	}
	public ArrayList getpodetails(String userid, UserProfileDTO dto, String language) throws Exception {
	
		ArrayList details = new ArrayList();
		
		details = userProfileDAO.getpodetails(userid,dto,language);
		
		return details;
	}
	
	//Added By Praveen for Tehsildar rights flow.
	public ArrayList getrudetails(String userid, UserProfileDTO dto, String language) throws Exception {
		
		ArrayList details = new ArrayList();
		
		details = userProfileDAO.getrudetails(userid,dto,language);
		
		return details;
	}
	
	
	
public ArrayList getTehsilList(String userid,  String language) throws Exception {
		
		ArrayList details = new ArrayList();
		
		details = userProfileDAO.getTehsilList(userid,language);
		
		return details;
	}
	
	public boolean insertrightsduration(String durationfrom, String durationto,
			String pouserid,String loggedinuserid) throws Exception {
		boolean success = userProfileDAO.insertrightsduration(durationfrom,durationto,pouserid,loggedinuserid);
		return success;
	}
	

	public boolean insertrightsduration(String durationfrom, String durationto,
			String pouserid,String loggedinuserid, String tehsilId,String filepath,String fileName) throws Exception {
		boolean success = userProfileDAO.insertrightsduration(durationfrom,durationto,pouserid,loggedinuserid,tehsilId, filepath,fileName);
		return success;
	}
	public boolean assignporights(String pouserid) throws Exception {
		boolean rights = userProfileDAO.assignporights(pouserid);
		return rights;
	}
	
	//Added By Praveen for Tehsildar Rights 
	public boolean assignTehsildarrights(String userid) throws Exception {
		boolean rights = userProfileDAO.assignTehsildarRights(userid);
		return rights;
	}
	
	public boolean updateTehsildarrightsduration(String durationfrom, String durationto,
			String pouserid,String loggedinuserid, String tehsilid,String filepath) throws Exception {
		boolean update = userProfileDAO.updateTehsildarrightsduration(durationfrom,durationto,pouserid,loggedinuserid,tehsilid, filepath);
		return update;
	}
	public boolean updaterightsduration(String durationfrom, String durationto,
			String pouserid,String loggedinuserid) throws Exception {
		boolean update = userProfileDAO.updaterightsduration(durationfrom,durationto,pouserid,loggedinuserid);
		return update;
	}
	public boolean revokerights(String userid) throws Exception {
		
		boolean flag =userProfileDAO.revokerights(userid);
		return flag;
	}
	//Added By Praveen for Tehsildar Rights 
	public boolean revokeTehsildarRights(String userid) throws Exception {
		
		boolean flag =userProfileDAO.revokeTehsildarRights(userid);
		return flag;
	}
	public String saveFile(org.apache.struts.upload.FormFile userFile, String userid)
	{
		
		return userProfileDAO.SaveFile(userFile, userid);
		
	}
	
	public String getUsertype(String userid) throws Exception {
		
		String type=null;
		type=userProfileDAO.getUsertype(userid);
		return type;
	}
	// To get Logged user district - Rahul
	public String getUserDistrict(String officeId,String language) throws Exception
	{	
		return userProfileDAO.getUserDistrict(officeId,language);	
	}
	
}
