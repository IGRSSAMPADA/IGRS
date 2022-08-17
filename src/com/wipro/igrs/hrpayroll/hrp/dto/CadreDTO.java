

package com.wipro.igrs.hrpayroll.hrp.dto;


import java.io.Serializable;


public class CadreDTO implements Serializable {
	private String	cadreId;
	
	private String	cadreName;
	
	private String	cadrePosts;
	
	private String	cadreStatus;
	
	private String	chkCadre[];
	
	private String	editCadresIds[];
	
	private String	editCadresPosts[];
	
	private String	editCadreNames[];
	
	/**
	 * 
	 */
	public CadreDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the cadreId
	 */
	public String getCadreId() {
		return cadreId;
	}
	
	/**
	 * @param cadreId
	 *            the cadreId to set
	 */
	public void setCadreId(String cadreId) {
		this.cadreId = cadreId;
	}
	
	/**
	 * @return the cadreName
	 */
	public String getCadreName() {
		return cadreName;
	}
	
	/**
	 * @param cadreName
	 *            the cadreName to set
	 */
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	
	/**
	 * @return the cadrePosts
	 */
	public String getCadrePosts() {
		return cadrePosts;
	}
	
	/**
	 * @param cadrePosts
	 *            the cadrePosts to set
	 */
	public void setCadrePosts(String cadrePosts) {
		this.cadrePosts = cadrePosts;
	}
	
	/**
	 * @return the cadreStatus
	 */
	public String getCadreStatus() {
		return cadreStatus;
	}
	
	/**
	 * @param cadreStatus
	 *            the cadreStatus to set
	 */
	public void setCadreStatus(String cadreStatus) {
		this.cadreStatus = cadreStatus;
	}
	
	/**
	 * @return the chkCadre
	 */
	public String[] getChkCadre() {
		return chkCadre;
	}
	
	/**
	 * @param chkCadre
	 *            the chkCadre to set
	 */
	public void setChkCadre(String[] chkCadre) {
		this.chkCadre = chkCadre;
	}
	
	/**
	 * @return the editCadresIds
	 */
	public String[] getEditCadresIds() {
		return editCadresIds;
	}
	
	/**
	 * @param editCadresIds
	 *            the editCadresIds to set
	 */
	public void setEditCadresIds(String[] editCadresIds) {
		this.editCadresIds = editCadresIds;
	}
	
	/**
	 * @return the editCadresPosts
	 */
	public String[] getEditCadresPosts() {
		return editCadresPosts;
	}
	
	/**
	 * @param editCadresPosts
	 *            the editCadresPosts to set
	 */
	public void setEditCadresPosts(String[] editCadresPosts) {
		this.editCadresPosts = editCadresPosts;
	}
	
	/**
	 * @return the editCadreNames
	 */
	public String[] getEditCadreNames() {
		return editCadreNames;
	}
	
	/**
	 * @param editCadreNames
	 *            the editCadreNames to set
	 */
	public void setEditCadreNames(String[] editCadreNames) {
		this.editCadreNames = editCadreNames;
	}
	
}
