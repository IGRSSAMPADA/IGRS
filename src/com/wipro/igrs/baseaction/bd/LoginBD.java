package com.wipro.igrs.baseaction.bd;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.wipro.igrs.ACL.dao.MenuDAO;
import com.wipro.igrs.UserRegistration.action.CryptoLibrary;
import com.wipro.igrs.baseaction.dao.User;
import com.wipro.igrs.login.dao.LoginDAO;
import com.wipro.igrs.login.dto.MasterListDTO;

/**
 * ===========================================================================
 * File : LoginBD.java Description : Represents Business delegate for User Login
 * 
 * Author : Madan Mohan Created Date : Dec 11, 2007
 * 
 * ===========================================================================
 */
public class

LoginBD {

	private String className = this.getClass().getName();
	// DBServices dbServices = null;
	private Logger logger = (Logger) Logger.getLogger(LoginBD.class);

	public LoginBD() {
		// dbServices = new DBServices();
	}

	public ArrayList getRoleName() {
		ArrayList list = new ArrayList();
		try {
			LoginDAO dao = new LoginDAO();
			ArrayList returnList = dao.getRoleName();
			if(returnList !=null) {
				for(int i = 0;i<returnList.size();i++){ 
					ArrayList listReturn = (ArrayList)returnList.get(i);
					MasterListDTO dto = new MasterListDTO();
					dto.setRoleID((String)listReturn.get(0)
							+"~"+(String)listReturn.get(1));
					dto.setRoleName((String)listReturn.get(1));
					//logger.debug((String)listReturn.get(0)
					//		+"~"+(String)listReturn.get(1));
					list.add(dto);
				}
					
			}
		}catch (Exception x) {
			logger.debug(x);
		}
		return list;
	}
	public String[] authenticateUser(String userId, String pwd, String noattempt,String roleID)
			throws Exception {
		String status[] = new String[3];
		try {
			LoginDAO dao = new LoginDAO();
			status  = dao.authenticateUser(userId, pwd, noattempt,roleID);

		} catch (Exception x) {
			logger.error(x.getMessage(),x);
		}
		return status;
	}
	
	public String[] authenticateUserSalting(String userId, String pwd, String noattempt,String roleID, String salt1, String salt2)
	throws Exception {
String status[] = new String[3];
try {
	LoginDAO dao = new LoginDAO();
	status  = dao.authenticateUserSaltingMohit(userId, pwd, noattempt,roleID,salt1,salt2);

} catch (Exception x) {
	logger.error(x.getMessage(),x);
}
return status;
}
	
	
	
	/*public String[] authenticateUser(String userId, String pwd, String noattempt,String roleID,String ofcID)
	throws Exception {
		String status[] = new String[3];
		try {
			User user = new User();
			status  = user.authenticateUser(userId, pwd, noattempt,roleID,ofcID);

		} catch (Exception x) {
			logger.error(x.getMessage(),x);
		}
		return status;
		}
*/
	// Getting the user type as Internal or External
	public String getusertype(String userId) throws Exception {

		ArrayList usrTypeList = new ArrayList();
		String userType = "";
		try {
			User user = new User();
			usrTypeList = user.getTypeOfUser(userId);
			if (usrTypeList != null && usrTypeList.size() > 0) {
				for (int r = 0; r < usrTypeList.size(); r++) {
					ArrayList tmp = (ArrayList) usrTypeList.get(0);
					userType = (String) tmp.get(0);
				}
			}
		} catch (Exception x) {
			logger.debug(x);
		}
		return userType;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public String getuserLockStatus(String uType, String userId)
			throws Exception {
		ArrayList statusList = new ArrayList();
		String lockStatus = "";

		try {
			User user = new User();
			statusList = user.getUserLStatus(uType, userId);
			if (statusList != null && statusList.size() > 0) {
				for (int r = 0; r < statusList.size(); r++) {
					ArrayList tmp = (ArrayList) statusList.get(0);
					lockStatus = (String) tmp.get(0);

				}
			}
		} catch (Exception x) {
			logger.debug(x);
		}
		return lockStatus;
	}

	public String[] authenticateUser(String userType, String userId, String pwd,
			String noattempt,String roleID) throws Exception {
		String status[] = new String[3];
		try {
			User user = new User();

			// Added by neesha to verify the user password entered to login
			// matches
			// or not with the encrypted password for the same user in the
			// database
			if (userType.equalsIgnoreCase("I")) {
				status = user.authenticateUser(userId, pwd, noattempt,roleID);
				logger.debug("user status:" + status);
			} else if (userType.equalsIgnoreCase("E")) {
				CryptoLibrary crypt = new CryptoLibrary();
				String encryptpswd = crypt.encrypt(pwd);
				logger.debug("password encrypted as:  " + encryptpswd);
				status = user.authenticateUser(userId, pwd, noattempt,roleID);
				logger.debug("user status:" + status);
			}

		} catch (Exception x) {
			status[0] = "DB_CONNECTION_NOT_FOUND";
			logger.debug(x);
		}
		return status;
	}
	
	public int validateRole(String roleId,
							String funId) {
		int status = 0;
		try {
			User user = new User();

			// Added by neesha to verify the user password entered to login
			// matches
			// or not with the encrypted password for the same user in the
			// database
			String check = user.validateRole(roleId,funId);
			if(check != null) {
				status = Integer.parseInt(check);
			}

		} catch (Exception x) {
			
			logger.debug(x);
		}
		return status;
	}
	public String checkProbation(String userID) throws Exception{
		String status="";
		try {
			User user = new User();
			status  = user.checkProbation(userID);

		} catch (Exception x) {
			logger.error(x.getMessage(),x);
		}
		return status;
	}

	public HashMap<String, String> getUserEmpIDAndDesignation(String userID) {
		HashMap<String, String> retVal = new HashMap<String, String>();
		try {
			User user = new User();
			user.getUserEmpIDAndDesignation(userID, retVal);
		} catch (Exception ex) {
			
		}
		return retVal;
	}
	
	public HashMap offcAndActivityDetails(String userID) {
		HashMap retVal = new HashMap();
		try {
			User user = new User();
			user.offcAndActivityDetails(userID, retVal);
		} catch (Exception ex) {
			
		}
		return retVal;
	}
	/**
	 * getOfficeIdOfLoggedInUser
	 * for getting office id of logged in user from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 * @date 11 April 2013
	 */
	public ArrayList getOfficeIdOfLoggedInUser(String userId) throws Exception {
			
		LoginDAO dao = new LoginDAO();
		
		
		ArrayList roleList=dao.getRoleIds(userId);
		ArrayList finalList=new ArrayList();
		MasterListDTO finalDto;
		
		if(roleList!=null && roleList.size()>0){
			
			ArrayList rowList;
			String str;
			
			
			for(int i=0; i<roleList.size();i++){
				finalDto=new MasterListDTO();
				rowList=(ArrayList)roleList.get(i);
				str=rowList.toString();
				str=str.substring(1, str.length()-1);
				finalDto.setRoleID(str);
				finalDto.setOfficeDistList(getOfficeDistIds(str));
				
				finalList.add(finalDto);
				
			}
					
			
		}
				
		
		return finalList;
	}
	/**
	 * getOfficeDistIds
	 * for getting ROLE ID LIST of logged in user from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 * @date 11 April 2013
	 */
	public ArrayList getOfficeDistIds(String roleId) throws Exception {
		
		LoginDAO dao = new LoginDAO();
		ArrayList innerList=new ArrayList();
		
		ArrayList officeDistlist=dao.getOfficeDistIds(roleId);
		
		MasterListDTO innerDto;
		
		if(officeDistlist!=null && officeDistlist.size()>0){
			
			ArrayList rowList;
			String str;
			
			for(int i=0; i<officeDistlist.size();i++){
				innerDto=new MasterListDTO();
				rowList=(ArrayList)officeDistlist.get(i);
				str=rowList.toString();
				str=str.substring(1, str.length()-1);
				
				String[] strArray=str.split(",");
				
				
				innerDto.setOfficeID(strArray[0].trim());
				innerDto.setDistID(strArray[1].trim());
				
				innerList.add(innerDto);
			}	
			
					
			
		}else{
			innerList.add(null);
		}
		
	
		return innerList;
	}

	public String getNoAttempt(String userId) throws Exception
	{
		LoginDAO dao = new LoginDAO();
		return dao.getNoOfAttempts(userId);
	}
	public String[] getUserTypeNoAttempt(String userId) throws Exception
	{
		LoginDAO dao = new LoginDAO();
		return dao.getUserNoOfAttempts(userId);
	}
	public int addNoAttempt(String userId, String getCurrTimeStamp) throws Exception {
		// TODO Auto-generated method stub
		LoginDAO dao = new LoginDAO();
		return dao.addNoAttempt(userId,getCurrTimeStamp);
	}

	public boolean lockAccount(String userId) throws Exception {
		// TODO Auto-generated method stub
		LoginDAO dao = new LoginDAO();
		return dao.lockAccount(userId);
		
	}

	public String getCurrTimeStamp() throws Exception{
		// TODO Auto-generated method stub
		LoginDAO dao = new LoginDAO();
		return dao.getCurrTimeStamp();
	}

	public ArrayList getInternalUsersOffice(String userId) throws Exception{
		// TODO Auto-generated method stub
		LoginDAO dao = new LoginDAO();
		return dao.getInternalUsersOffice(userId);
	}
	public boolean resetAttemptCount(String userId) throws Exception {
		// TODO Auto-generated method stub
		LoginDAO dao = new LoginDAO();
		return dao.resetAttemptCount(userId);
		
	}
	public HashMap getEmpRole(String uID, String ofcId) throws Exception {
		HashMap role = new HashMap();
		ArrayList retrievedList = new ArrayList();
		HashMap internalUserRole = new HashMap();
		ArrayList subList = new ArrayList();
		try {
			
				String typeOfUser=getTypeOfUser(uID);
				LoginDAO ldao = new LoginDAO();
				retrievedList = ldao.getRoleID(uID);
				logger.debug("Retrieved Role List is ....    " + retrievedList);

				if(typeOfUser.equalsIgnoreCase("2"))// internal user
					{
				
					if(ofcId.equalsIgnoreCase("OFC01"))
						{
							if (retrievedList != null && retrievedList.size() != 0)
							{
								role = (HashMap) retrievedList.get(0);
								logger.debug("sucessful");
							}
						}
					else
					{
							MenuDAO mdao = new MenuDAO();
							ArrayList list  = mdao.getRoleMappedToOffice(ofcId);
							if (retrievedList != null && retrievedList.size() != 0) 
							{
								internalUserRole=(HashMap) retrievedList.get(0);
								for(int i =0;i<list.size();i++)
									{
										subList=(ArrayList)list.get(i);
										String key =subList.get(0).toString();
											if(internalUserRole.containsKey(key))
											{
												String value = internalUserRole.get(key).toString();
												role.put(key, value);
											}
									}
							}
					
					
					}
				
			}
			else if (typeOfUser.equalsIgnoreCase("1"))//external user
			{
			if (retrievedList != null && retrievedList.size() != 0) {
				role = (HashMap) retrievedList.get(0);

				logger.debug("sucessful");
			}
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}
			
//		System.out.println(role);
		return role;
	}
	private String getTypeOfUser(String uid) throws Exception{
		// TODO Auto-generated method stub
			LoginDAO ldao = new LoginDAO();
		return ldao.getTypeOfUser(uid);
	}
	
	public MasterListDTO getLoggedInUserDetls(String userId,String lang) throws Exception {
		LoginDAO dao = new LoginDAO();
		MasterListDTO userDTO = new MasterListDTO();
		ArrayList list = dao.getLoggedInUserDetls(userId);
		logger.debug(list);
		for(int i= 0 ; i < list.size();i++)
		{
			ArrayList subList = (ArrayList)list.get(i);
			userDTO = new MasterListDTO();
			String fName = (String)subList.get(0);
			String mName = (String)subList.get(1);
			String lName = (String)subList.get(2);
			String name = "";
			if(mName != null && !mName.equals(""))
				name = fName+" "+mName+" "+lName;
			else
				name = fName+" "+lName;
			
			userDTO.setUserName(name);
			if(lang.equals("en"))
				userDTO.setDesignation("Service Provider");
			else
				userDTO.setDesignation("सेवा प्रदाता");
			userDTO.setOfficeName("NA");
			String sign = (String)subList.get(3);
			if(sign!= null && !sign.equals(""))
				userDTO.setSignature((String)subList.get(3));
			else
				userDTO.setSignature(null);
			userDTO.setPenType((String)subList.get(4));
			
		}
		return userDTO;
	}
	
	public MasterListDTO getLoggedInUserDetlsInternal(String userId,String officeId,String lang) throws Exception {
		LoginDAO dao = new LoginDAO();
		MasterListDTO userDTO = new MasterListDTO();
		ArrayList list = dao.getLoggedInUserDetls(userId);
		logger.debug("Size of the list returned from loggedInUserDetls method is "+list.size());
		for(int i= 0 ; i < list.size();i++)
		{
			ArrayList subList = (ArrayList)list.get(i);
			userDTO = new MasterListDTO();
			String fName = (String)subList.get(0);
			String mName = (String)subList.get(1);
			String lName = (String)subList.get(2);
			logger.debug("Name of logged in user -->"+fName +" "+mName+" "+lName);
			String name = "";
			if(mName != null && !mName.equals(""))
				name = fName+" "+mName+" "+lName;
			else
				name = fName+" "+lName;
			userDTO.setUserName(name);
			String sign = (String)subList.get(3);
			if(sign!= null && !sign.equals(""))
				userDTO.setSignature((String)subList.get(3));
			else
				userDTO.setSignature(null);
			String desg = dao.getLoggedInDesignation(userId, lang);
			logger.debug("Designation of logged in user -->"+desg);
			if(desg != null && !desg.equals(""))
				userDTO.setDesignation(desg);
			else
				userDTO.setDesignation(null);
			String offc = dao.getLoggedInOffice(officeId, lang);
			logger.debug("office of logged in user -->"+((null==offc)?"NA":offc));
			if(offc != null && !offc.equals(""))
				userDTO.setOfficeName(offc);
			else
				userDTO.setOfficeName(null);
			userDTO.setPenType((String)subList.get(4));
			
		}
		logger.debug("user dto all value set");
		return userDTO;
	}
}
