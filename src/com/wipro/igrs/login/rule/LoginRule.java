/**
 * 
 */
package com.wipro.igrs.login.rule;


import java.util.ArrayList;

import org.apache.log4j.Logger;
import com.wipro.igrs.baseaction.dto.UserDTO;
import com.wipro.igrs.login.constant.LoginConstant;
import com.wipro.igrs.util.PropertiesFileReader;


/**
 * @since   :   Dec 12, 2007
 * File           :   LoginRule.java
 * Description    :   validate the user
 * @author  :   Madan  Mohan
 */
public class LoginRule {
	
	/**
	 * @author Madan Mohan
	 */
	private Logger logger = 
		(Logger) Logger.getLogger(LoginRule.class);
	/**
	 * @author Madan Mohan
	 */
	private PropertiesFileReader pr;
	/**
	 * @author Madan Mohan
	 */
    private boolean error;
    
    /**
     * @author Madan Mohan
     */
	public LoginRule() {
		try {	
			pr = PropertiesFileReader.getInstance("resources.igrs");
		}catch(Exception x){
			logger.debug(x);
		}
	}
	/**
	 * @param status
	 * @param userDTO
	 * @return ArrayList
	 */
	public ArrayList validateUser(String status, UserDTO userDTO) {
		ArrayList list = new ArrayList();
		boolean bolError = false;
		try {
			//list.add(pr.getValue("error.header"));	
			
			//ADDED BY ROOPAM
			logger.debug("userId login action:-"+userDTO.getUserId());
			if("".equals(userDTO.getUserId()) 
					|| userDTO.getUserId()==null ){
				
				list.add(pr.getValue("DB_ENTER_THE_USER_NAME"));
				bolError = true;
			}
			if("".equals(userDTO.getPassword()) 
					|| userDTO.getPassword()==null ){
				list.add(pr.getValue("DB_ENTER_THE_PASSWORD"));
				bolError = true;
			}
			if(userDTO.getUserId()!= null && !userDTO.getUserId().equalsIgnoreCase("") 
					&& userDTO.getPassword()!=null && !userDTO.getPassword().equalsIgnoreCase(""))
			{
			if(LoginConstant.DB_INVALID_USER.equals(status)){
				//list.add(pr.getValue("DB_INVALID_USER")); 
				list.add(pr.getValue("DB_INVALID_USER_PWD"));  //for bug id 752. common message for invalid username or password.
				bolError = true;
			}
			if(LoginConstant.DB_INVALID_PWD.equals(status)){
				//list.add(pr.getValue("DB_INVALID_PWD"));
				list.add(pr.getValue("DB_INVALID_USER_PWD"));  //for bug id 752. common message for invalid username or password.
				bolError = true;
			}
			if(LoginConstant.DB_LOCK_USER.equals(status)){
				list.add(pr.getValue("DB_LOCK_PWD"));
				bolError = true;
			}
			if(LoginConstant.DB_USER_DEACTIVATED.equals(status)){
				list.add(pr.getValue("DB_DISABLED_USER"));
				bolError = true;
			}
			if(LoginConstant.DB_INVALID_CAPTCHA.equals(status))
			{
				list.add(pr.getValue("DB_INVALID_CAPTCHA"));
				bolError = true;
			}
			if(LoginConstant.DB_USER_LOCKED.equals(status)){
				list.add(pr.getValue("DB_LOCK_PWD"));
				bolError = true;
			}
			if(LoginConstant.DB_NOT_FOUND.equals(status)){
				list.add(pr.getValue("DB_USER_NOT_DEFINED"));
				bolError = true;
			}
			if(LoginConstant.DB_USER_ALREADY_LOGIN.equals(status)){
				list.add(pr.getValue("DB_USER_ALREADY_LOGIN"));
				bolError = true;
			}
			if(LoginConstant.DB_USER_ROLE_NOT_MAP.equals(status)){
				list.add("<li>"+userDTO.getUserId()+ " "+pr.getValue("DB_USER_USER_ROLE_MAP")+ "</li></br>");
				bolError = true;
			}
			//ADDED BY ROOPAM
			if(LoginConstant.DB_USER_BLOCKED.equals(status)){
				list.add(pr.getValue("DB_USER_BLOCKED"));
				bolError = true;
			}
			}
			/*//ADDED BY ROOPAM
			logger.debug("userId login action:-"+userDTO.getUserId());
			if("".equals(userDTO.getUserId()) 
					|| userDTO.getUserId()==null ){
				
				list.add(pr.getValue("DB_ENTER_THE_USER_NAME"));
				bolError = true;
			}
			if("".equals(userDTO.getPassword()) 
					|| userDTO.getPassword()==null ){
				list.add(pr.getValue("DB_ENTER_THE_PASSWORD"));
				bolError = true;
			}*/
		}catch(Exception x) {
			logger.debug(x);
		}
		setError(bolError);
		return list;
	}
	/**
	 * @return boolean
	 */
	public boolean isError() {
		return error;
	}
	/**
	 * @param boolean
	 */
	public void setError(boolean error) {
		this.error = error;
	}
}
