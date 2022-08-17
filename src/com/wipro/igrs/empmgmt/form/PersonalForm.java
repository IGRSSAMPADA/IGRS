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

import java.util.Locale;
import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.empmgmt.bd.CommonBD;
import com.wipro.igrs.empmgmt.dto.AddressDTO;
import com.wipro.igrs.empmgmt.dto.ChildDetailsDTO;
import com.wipro.igrs.empmgmt.dto.DropDownDTO;
import com.wipro.igrs.empmgmt.dto.PersonalDetailsDTO;
import com.wipro.igrs.empmgmt.dto.RelativeTypeDTO;

/**
* 
* PersonalForm.java <br>
* PersonalForm <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PersonalForm extends BaseForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1089419491134603743L;
	private AddressDTO addressDTO = new AddressDTO();
	private PersonalDetailsDTO personalDTO = new PersonalDetailsDTO();
	private ChildDetailsDTO childDTO = new ChildDetailsDTO();

	private ArrayList childList = new ArrayList();

	private ArrayList<RelativeTypeDTO> relativeMasterList = new ArrayList<RelativeTypeDTO>();

	private ArrayList<DropDownDTO> dayMasterList = new ArrayList<DropDownDTO>();
	private ArrayList<DropDownDTO> monthMasterList = new ArrayList<DropDownDTO>();
	private ArrayList<DropDownDTO> yearMasterList = new ArrayList<DropDownDTO>();
	private Locale locale;
	private String formName;
	private String actionName;
	private String chkreferenceName;
	private ArrayList userHintQuestion = new ArrayList();

	private AddressDTO permAddressDTO = new AddressDTO();
	private AddressDTO currAddressDTO = new AddressDTO();

	public PersonalForm() {
		//populateDateLists();

	}

	/**
	 * 
	 */
	private void populateDateLists() {
		try {
			CommonBD delegateObject = new CommonBD();
			if (dayMasterList == null) {
				dayMasterList = new ArrayList<DropDownDTO>();
			}
			if (monthMasterList == null) {
				monthMasterList = new ArrayList<DropDownDTO>();
			}
			if (yearMasterList == null) {
				yearMasterList = new ArrayList<DropDownDTO>();
			}
			delegateObject.populateDateLists(dayMasterList, monthMasterList,
					yearMasterList,locale);
		} catch (Exception e) {

		}
	}

	public ArrayList getUserHintQuestion() {
		return userHintQuestion;
	}

	public void setUserHintQuestion(ArrayList userHintQuestion) {
		this.userHintQuestion = userHintQuestion;
	}


	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public PersonalDetailsDTO getPersonalDTO() {
		return personalDTO;
	}

	public void setPersonalDTO(PersonalDetailsDTO personalDTO) {
		this.personalDTO = personalDTO;
	}

	public AddressDTO getAddressDTO() {
		return addressDTO;
	}

	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}

	public ChildDetailsDTO getChildDTO() {
		return childDTO;
	}

	public void setChildDTO(ChildDetailsDTO childDTO) {
		this.childDTO = childDTO;
	}

	public ArrayList getChildList() {
		return childList;
	}

	public void setChildList(ArrayList childList) {
		this.childList = childList;
	}

	public void setChildArrDTO(int index, ChildDetailsDTO value) {

		for (; index >= childList.size(); childList.add(new ChildDetailsDTO()))
			;
		childList.add(index, value);
	}

	public ChildDetailsDTO getChildArrDTO(int index) {
		for (; index >= childList.size(); childList.add(new ChildDetailsDTO()))
			;
		return (ChildDetailsDTO) childList.get(index);
	}

	/**
	 * @return the chkreferenceName
	 */
	public String getChkreferenceName() {
		return chkreferenceName;
	}

	/**
	 * @param chkreferenceName
	 *            the chkreferenceName to set
	 */
	public void setChkreferenceName(String chkreferenceName) {
		this.chkreferenceName = chkreferenceName;
	}

	/**
	 * @return the permAddressDTO
	 */
	public AddressDTO getPermAddressDTO() {
		return permAddressDTO;
	}

	/**
	 * @param permAddressDTO
	 *            the permAddressDTO to set
	 */
	public void setPermAddressDTO(AddressDTO permAddressDTO) {
		this.permAddressDTO = permAddressDTO;
	}

	/**
	 * @return the currAddressDTO
	 */
	public AddressDTO getCurrAddressDTO() {
		return currAddressDTO;
	}

	/**
	 * @param currAddressDTO
	 *            the currAddressDTO to set
	 */
	public void setCurrAddressDTO(AddressDTO currAddressDTO) {
		this.currAddressDTO = currAddressDTO;
	}

	/**
	 * @return the relativeMasterList
	 */
	public ArrayList<RelativeTypeDTO> getRelativeMasterList() {
		return relativeMasterList;
	}

	/**
	 * @param relativeMasterList
	 *            the relativeMasterList to set
	 */
	public void setRelativeMasterList(
			ArrayList<RelativeTypeDTO> relativeMasterList) {
		this.relativeMasterList = relativeMasterList;
	}

	/**
	 * @return the dayMasterList
	 */
	public ArrayList<DropDownDTO> getDayMasterList() {
		return dayMasterList;
	}

	/**
	 * @param dayMasterList
	 *            the dayMasterList to set
	 */
	public void setDayMasterList(ArrayList<DropDownDTO> dayMasterList) {
		this.dayMasterList = dayMasterList;
	}

	/**
	 * @return the monthMasterList
	 */
	public ArrayList<DropDownDTO> getMonthMasterList() {
		return monthMasterList;
	}

	/**
	 * @param monthMasterList
	 *            the monthMasterList to set
	 */
	public void setMonthMasterList(ArrayList<DropDownDTO> monthMasterList) {
		this.monthMasterList = monthMasterList;
	}

	/**
	 * @return the yearMasterList
	 */
	public ArrayList<DropDownDTO> getYearMasterList() {
		return yearMasterList;
	}

	/**
	 * @param yearMasterList
	 *            the yearMasterList to set
	 */
	public void setYearMasterList(ArrayList<DropDownDTO> yearMasterList) {
		this.yearMasterList = yearMasterList;
	}

}
