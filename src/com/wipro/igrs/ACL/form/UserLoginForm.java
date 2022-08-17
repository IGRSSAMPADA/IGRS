/**
 * 
 */
package com.wipro.igrs.ACL.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.ACL.dto.UserLoginDTO;

/**
 * @author neegaga
 * 
 */
public class UserLoginForm extends ActionForm {
	UserLoginDTO logindto = new UserLoginDTO();
	private ArrayList detailsList;
	private ArrayList modList;
	private ArrayList subModList;
	private ArrayList actList;
	private ArrayList grpList;
	private ArrayList roleList;
	private String userName;
	private ArrayList fncList;

	/**
	 * @return the detailsList
	 */
	public ArrayList getDetailsList() {
		return detailsList;
	}

	/**
	 * @param detailsList
	 *            the detailsList to set
	 */
	public void setDetailsList(ArrayList detailsList) {
		this.detailsList = detailsList;
	}

	/**
	 * @return the logindto
	 */
	public UserLoginDTO getLogindto() {
		return logindto;
	}

	/**
	 * @param logindto
	 *            the logindto to set
	 */
	public void setLogindto(UserLoginDTO logindto) {
		this.logindto = logindto;
	}

	/**
	 * @return the modList
	 */
	public ArrayList getModList() {
		return modList;
	}

	/**
	 * @param modList
	 *            the modList to set
	 */
	public void setModList(ArrayList modList) {
		this.modList = modList;
	}

	/**
	 * @return the subModList
	 */
	public ArrayList getSubModList() {
		return subModList;
	}

	/**
	 * @param subModList
	 *            the subModList to set
	 */
	public void setSubModList(ArrayList subModList) {
		this.subModList = subModList;
	}

	/**
	 * @return the actList
	 */
	public ArrayList getActList() {
		return actList;
	}

	/**
	 * @param actList
	 *            the actList to set
	 */
	public void setActList(ArrayList actList) {
		this.actList = actList;
	}

	/**
	 * @return the grpList
	 */
	public ArrayList getGrpList() {
		return grpList;
	}

	/**
	 * @param grpList
	 *            the grpList to set
	 */
	public void setGrpList(ArrayList grpList) {
		this.grpList = grpList;
	}

	/**
	 * @return the roleList
	 */
	public ArrayList getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList
	 *            the roleList to set
	 */
	public void setRoleList(ArrayList roleList) {
		this.roleList = roleList;
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
	 * @return the fncList
	 */
	public ArrayList getFncList() {
		return fncList;
	}

	/**
	 * @param fncList
	 *            the fncList to set
	 */
	public void setFncList(ArrayList fncList) {
		this.fncList = fncList;
	}

}
