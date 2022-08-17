/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI          xxxxx              
 * --------------------------------------------------------------------------------
*/

package com.wipro.igrs.auditinspection.dto;


import java.io.Serializable;


/**
 * @author root
 * 
 */
public class PublicInspectionAuditParaDTO implements Serializable {
	private String paraId;

	private String paraType;

	private String paraName;

	private String paraObjectItemName;

	/**
	 * @return the paraId
	 */
	public String getParaId() {
		return paraId;
	}

	/**
	 * @param paraId
	 *            the paraId to set
	 */
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}

	/**
	 * @return the paraType
	 */
	public String getParaType() {
		return paraType;
	}

	/**
	 * @param paraType
	 *            the paraType to set
	 */
	public void setParaType(String paraType) {
		this.paraType = paraType;
	}

	/**
	 * @return the paraName
	 */
	public String getParaName() {
		return paraName;
	}

	/**
	 * @param paraName
	 *            the paraName to set
	 */
	public void setParaName(String paraName) {
		this.paraName = paraName;
	}

	/**
	 * @return the paraObjectItemName
	 */
	public String getParaObjectItemName() {
		return paraObjectItemName;
	}

	/**
	 * @param paraObjectItemName
	 *            the paraObjectItemName to set
	 */
	public void setParaObjectItemName(String paraObjectItemName) {
		this.paraObjectItemName = paraObjectItemName;
	}

}
