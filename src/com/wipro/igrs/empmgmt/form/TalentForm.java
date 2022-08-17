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
package com.wipro.igrs.empmgmt.form;

import java.util.ArrayList;



import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.empmgmt.dto.AcademicDTO;
import com.wipro.igrs.empmgmt.dto.PrevEmpDTO;

/**
* 
* TalentForm.java <br>
* TalentForm <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TalentForm extends BaseForm{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -6504443191619675394L;
	private AcademicDTO academicDTO=new AcademicDTO();
	private AcademicDTO academic=new AcademicDTO();
	private PrevEmpDTO prevEmpDTO=new PrevEmpDTO();
	private ArrayList academicList=new ArrayList();
	private ArrayList prevEmpList=new ArrayList();
	private String formName;
	private String actionName;
	String userId;
	private String employeeId;
	private String actionType;
	/**
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * @param formName
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

	/**
	 * @return
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * @param actionName
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * @return
	 */
	public ArrayList getAcademicList() {
		return academicList;
	}

	/**
	 * @param academicList
	 */
	public void setAcademicList(ArrayList academicList) {
		this.academicList = academicList;
	}

	/**
	 * @return
	 */
	public ArrayList getPrevEmpList() {
		return prevEmpList;
	}

	/**
	 * @param prevEmpList
	 */
	public void setPrevEmpList(ArrayList prevEmpList) {
		this.prevEmpList = prevEmpList;
	}
	/**
	 * @param index
	 * @param value
	 */
	public void setAcademicDTO(int index, AcademicDTO value) 
	{
		
		for(; index >= academicList.size();academicList.add(new AcademicDTO()));
		academicList.add(index,value);
	}
	
	/**
	 * @param index
	 * @return
	 */
	public AcademicDTO getAcademicDTO(int index) 
	{
		for(; index >= academicList.size();academicList.add(new AcademicDTO()));
		return (AcademicDTO)academicList.get(index);
	}

	/**
	 * @param index
	 * @param value
	 */
	public void setPrevEmpDTO(int index, PrevEmpDTO value) 
	{
		
		for(; index >= prevEmpList.size();prevEmpList.add(new PrevEmpDTO()));
		prevEmpList.add(index,value);
	}
	
	/**
	 * @param index
	 * @return
	 */
	public  PrevEmpDTO getPrevEmpDTO(int index) 
	{
		for(; index >= prevEmpList.size();prevEmpList.add(new PrevEmpDTO()));
		return (PrevEmpDTO)prevEmpList.get(index);
	}

	/**
	 * @return
	 */
	public AcademicDTO getAcademicDTO() {
		return academicDTO;
	}

	/**
	 * @param academicDTO
	 */
	public void setAcademicDTO(AcademicDTO academicDTO) {
		this.academicDTO = academicDTO;
	}

	/**
	 * @return
	 */
	public PrevEmpDTO getPrevEmpDTO() {
		return prevEmpDTO;
	}

	/**
	 * @param prevEmpDTO
	 */
	public void setPrevEmpDTO(PrevEmpDTO prevEmpDTO) {
		this.prevEmpDTO = prevEmpDTO;
	}

	/**
	 * @return
	 */
	public AcademicDTO getAcademic() {
		return academic;
	}

	/**
	 * @param academic
	 */
	public void setAcademic(AcademicDTO academic) {
		this.academic = academic;
	}

	/**
	 * @return the employeeId
	 */
	/**
	 * @return
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	/**
	 * @param employeeId
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the actionType
	 */
	/* (non-Javadoc)
	 * @see com.wipro.igrs.baseaction.form.BaseForm#getActionType()
	 */
	public String getActionType() {
		return actionType;
	}

	/**
	 * @param actionType the actionType to set
	 */
	/* (non-Javadoc)
	 * @see com.wipro.igrs.baseaction.form.BaseForm#setActionType(java.lang.String)
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
}
