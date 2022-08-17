

package com.wipro.igrs.hrpayroll.hrpl.dto;


import java.io.Serializable;


public class MappingDTO implements Serializable {
	private String leavetypeid;

	private String leavetypename;

	private String gradetypeid;

	private String gradetypename;

	private String cadretypeid;

	private String cadretypename;

	private String mappingform;

	private String leavetyperadio;

	private String[] leavetypeids;

	/**
	 * @return the leavetypeid
	 */
	public String getLeavetypeid() {
		return leavetypeid;
	}

	/**
	 * @param leavetypeid
	 *            the leavetypeid to set
	 */
	public void setLeavetypeid(String leavetypeid) {
		this.leavetypeid = leavetypeid;
	}

	/**
	 * @return the leavetypename
	 */
	public String getLeavetypename() {
		return leavetypename;
	}

	/**
	 * @param leavetypename
	 *            the leavetypename to set
	 */
	public void setLeavetypename(String leavetypename) {
		this.leavetypename = leavetypename;
	}

	/**
	 * @return the gradetypeid
	 */
	public String getGradetypeid() {
		return gradetypeid;
	}

	/**
	 * @param gradetypeid
	 *            the gradetypeid to set
	 */
	public void setGradetypeid(String gradetypeid) {
		this.gradetypeid = gradetypeid;
	}

	/**
	 * @return the gradetypename
	 */
	public String getGradetypename() {
		return gradetypename;
	}

	/**
	 * @param gradetypename
	 *            the gradetypename to set
	 */
	public void setGradetypename(String gradetypename) {
		this.gradetypename = gradetypename;
	}

	/**
	 * @return the cadretypeid
	 */
	public String getCadretypeid() {
		return cadretypeid;
	}

	/**
	 * @param cadretypeid
	 *            the cadretypeid to set
	 */
	public void setCadretypeid(String cadretypeid) {
		this.cadretypeid = cadretypeid;
	}

	/**
	 * @return the cadretypename
	 */
	public String getCadretypename() {
		return cadretypename;
	}

	/**
	 * @param cadretypename
	 *            the cadretypename to set
	 */
	public void setCadretypename(String cadretypename) {
		this.cadretypename = cadretypename;
	}

	/**
	 * @return the mappingform
	 */
	public String getMappingform() {
		return mappingform;
	}

	/**
	 * @param mappingform
	 *            the mappingform to set
	 */
	public void setMappingform(String mappingform) {
		this.mappingform = mappingform;
	}

	/**
	 * @return the leavetyperadio
	 */
	public String getLeavetyperadio() {
		return leavetyperadio;
	}

	/**
	 * @param leavetyperadio
	 *            the leavetyperadio to set
	 */
	public void setLeavetyperadio(String leavetyperadio) {
		this.leavetyperadio = leavetyperadio;
	}

	/**
	 * @return the leavetypeids
	 */
	public String[] getLeavetypeids() {
		return leavetypeids;
	}

	/**
	 * @param leavetypeids the leavetypeids to set
	 */
	public void setLeavetypeids(String[] leavetypeids) {
		this.leavetypeids = leavetypeids;
	}

}
