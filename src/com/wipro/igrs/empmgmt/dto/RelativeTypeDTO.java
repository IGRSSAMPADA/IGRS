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

/**
* 
* RelativeTypeDTO.java <br>
* RelativeTypeDTO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
public class RelativeTypeDTO implements Serializable {
	private static final long serialVersionUID = -5474444414897964958L;
	private String relativeID;
	private String relativeType;
	

	/**
	 * @return the relativeID
	 */
	public String getRelativeID() {
		return relativeID;
	}

	/**
	 * @param relativeID
	 *            the relativeID to set
	 */
	public void setRelativeID(String relativeID) {
		this.relativeID = relativeID;
	}

	/**
	 * @return the relativeType
	 */
	public String getRelativeType() {
		return relativeType;
	}

	/**
	 * @param relativeType
	 *            the relativeType to set
	 */
	public void setRelativeType(String relativeType) {
		this.relativeType = relativeType;
	}
}
