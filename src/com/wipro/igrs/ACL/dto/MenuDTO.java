/**
 * 
 */
package com.wipro.igrs.ACL.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author neegaga
 * 
 */
public class MenuDTO implements Serializable {
	private String userName;
	private String password;
	private String loginAction;
	private String frwdPath;
	private ArrayList modlist;

	/**
	 * @return the modlist
	 */
	public ArrayList getModlist() {
		return modlist;
	}

	/**
	 * @param modlist
	 *            the modlist to set
	 */
	public void setModlist(ArrayList modlist) {
		this.modlist = modlist;
	}

	/**
	 * @return the frwdPath
	 */
	public String getFrwdPath() {
		return frwdPath;
	}

	/**
	 * @param frwdPath
	 *            the frwdPath to set
	 */
	public void setFrwdPath(String frwdPath) {
		this.frwdPath = frwdPath;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the loginAction
	 */
	public String getLoginAction() {
		return loginAction;
	}

	/**
	 * @param loginAction
	 *            the loginAction to set
	 */
	public void setLoginAction(String loginAction) {
		this.loginAction = loginAction;
	}

}
