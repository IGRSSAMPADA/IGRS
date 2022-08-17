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
package com.wipro.igrs.adminConfig.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
* 
* AdminAttrDTO.java <br>
* AdminAttrDTO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 07-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes" })
public class AdminAttrDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4526396410750337679L;
	private String name;
	private String value;
	private ArrayList moduleIDList;
	private ArrayList subModuleIDList;
	private ArrayList functionIDList;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the moduleIDList
	 */
	public ArrayList getModuleIDList() {
		return moduleIDList;
	}
	/**
	 * @param moduleIDList the moduleIDList to set
	 */
	public void setModuleIDList(ArrayList moduleIDList) {
		this.moduleIDList = moduleIDList;
	}
	/**
	 * @return the subModuleIDList
	 */
	public ArrayList getSubModuleIDList() {
		return subModuleIDList;
	}
	/**
	 * @param subModuleIDList the subModuleIDList to set
	 */
	public void setSubModuleIDList(ArrayList subModuleIDList) {
		this.subModuleIDList = subModuleIDList;
	}
	/**
	 * @return the functionIDList
	 */
	public ArrayList getFunctionIDList() {
		return functionIDList;
	}
	/**
	 * @param functionIDList the functionIDList to set
	 */
	public void setFunctionIDList(ArrayList functionIDList) {
		this.functionIDList = functionIDList;
	}

	

}
