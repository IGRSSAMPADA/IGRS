/**
 * Copyright (c) 2009-10 WIPRO INFOTECH. All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 *
 * Customer specific copyright notice - NA
 */
package com.wipro.igrs.empmgmt.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
* 
* AssetDTO.java <br>
* AssetDTO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes" })
public class AssetDTO implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 2279105926757662121L;
private String assettype;
private String assestdetails;
private String assetValue;
private String assetStatus;

private String employeeId;

private String assetTypeId;
private String assetTypeName;
private ArrayList assetList;
public String getAssestdetails() {
	return assestdetails;
}
public void setAssestdetails(String assestdetails) {
	this.assestdetails = assestdetails;
}
public String getAssettype() {
	return assettype;
}
public void setAssettype(String assettype) {
	this.assettype = assettype;
}
public String getAssetStatus() {
	return assetStatus;
}
public void setAssetStatus(String assetStatus) {
	this.assetStatus = assetStatus;
}
public String getAssetValue() {
	return assetValue;
}
public void setAssetValue(String assetValue) {
	this.assetValue = assetValue;
}
public ArrayList getAssetList() {
	return assetList;
}
public void setAssetList(ArrayList assetList) {
	this.assetList = assetList;
}
public String getAssetTypeId() {
	return assetTypeId;
}
public void setAssetTypeId(String assetTypeId) {
	this.assetTypeId = assetTypeId;
}
public String getAssetTypeName() {
	return assetTypeName;
}
public void setAssetTypeName(String assetTypeName) {
	this.assetTypeName = assetTypeName;
}
/**
 * @return the employeeId
 */
public String getEmployeeId() {
	return employeeId;
}
/**
 * @param employeeId the employeeId to set
 */
public void setEmployeeId(String employeeId) {
	this.employeeId = employeeId;
}
}
