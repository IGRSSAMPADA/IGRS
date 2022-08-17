/**
 * 
 */
package com.wipro.igrs.ACL.dto;

import java.io.Serializable;
import java.util.Set;

/**
 * @author neegaga
 * 
 */
public class UserLoginDTO implements Serializable {
	private String userName;
	private String password;
	private String loginAction;
	private String frwdPath;

	private String grpName;
	private String activity;
	private String function;
	private String module;
	private String submodule;
	private String roleName;
	private Set tmp;

	/**
	 * @return the tmp
	 */
	public Set getTmp() {
		return tmp;
	}

	/**
	 * @param tmp
	 *            the tmp to set
	 */
	public void setTmp(Set tmp) {
		this.tmp = tmp;
	}

	/**
	 * @return the grpName
	 */
	public String getGrpName() {
		return grpName;
	}

	/**
	 * @param grpName
	 *            the grpName to set
	 */
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	/**
	 * @return the activity
	 */
	public String getActivity() {
		return activity;
	}

	/**
	 * @param activity
	 *            the activity to set
	 */
	public void setActivity(String activity) {
		this.activity = activity;
	}

	/**
	 * @return the function
	 */
	public String getFunction() {
		return function;
	}

	/**
	 * @param function
	 *            the function to set
	 */
	public void setFunction(String function) {
		this.function = function;
	}

	/**
	 * @return the module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module
	 *            the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * @return the submodule
	 */
	public String getSubmodule() {
		return submodule;
	}

	/**
	 * @param submodule
	 *            the submodule to set
	 */
	public void setSubmodule(String submodule) {
		this.submodule = submodule;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
