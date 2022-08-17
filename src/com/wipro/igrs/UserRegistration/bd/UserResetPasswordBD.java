package com.wipro.igrs.UserRegistration.bd;



import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.UserRegistration.action.CryptoLibrary;
import com.wipro.igrs.UserRegistration.dao.UserResetPasswordDAO;
import com.wipro.igrs.baseaction.bd.LoginBD;


public class UserResetPasswordBD {
private Logger logger = (Logger) Logger.getLogger(LoginBD.class);
	
	public UserResetPasswordBD()
	{
		//LoggerMsg.info("we are in UserForgetPasswordBD");
		}
	UserResetPasswordDAO userresetdao = new UserResetPasswordDAO();
	 /**
	* update new password in database
	* @throws Exception
	* @author ROOPAM
	*/
	
	public boolean updateUserPassword(String strUserName,String strNewPswd, String srtPhotoIdProof) throws Exception
	{
		CryptoLibrary crypt = new CryptoLibrary();
		String encryptpswd = crypt.SHAencryption(strNewPswd);
		logger.debug("encrypted new password:-"+encryptpswd);
		return userresetdao.UpdateUserPaswrd(strUserName,encryptpswd,srtPhotoIdProof);
	}
	
	public String getUserId(String key)throws Exception{
		logger.debug("fffff----inside BD---"+key);
		String user=new String();
		int length;
		ArrayList userId=userresetdao.getUserId(key);
		if(userId!=null && userId.size()==1){
			user=userId.get(0).toString();
			length=user.length();
			logger.debug("fffff----inside BD---USERID1"+user);
			
			user=user.substring(1, length-1);
			logger.debug("fffff----inside BD---USERID2"+user);
		}
		return user;
	}
	 
	

}
