/**
 * EmpMgmtSearchForm.java
 */


package com.wipro.igrs.empmgmt.form;


import org.apache.struts.action.ActionForm;
import com.wipro.igrs.empmgmt.dto.*;


/**
 * @author admin
 *
 */
public class EmpMgmtSearchForm extends ActionForm {

	private TalentDetailsDTO talentDTO = new TalentDetailsDTO();

	private static String strEmployeeName;

	public EmpMgmtSearchForm() {

	}

	/**
	 * @return the strEmployeeName
	 */
	public static String getStrEmployeeName() {
		return strEmployeeName;
	}

	/**
	 * @param strEmployeeName the strEmployeeName to set
	 */
	public static void setStrEmployeeName(String strEmployeeName) {
		EmpMgmtSearchForm.strEmployeeName = strEmployeeName;
	}

	/**
	 * @return the talentDTO
	 */
	public TalentDetailsDTO getTalentDTO() {
		return talentDTO;
	}

	/**
	 * @param talentDTO the talentDTO to set
	 */
	public void setTalentDTO(TalentDetailsDTO talentDTO) {
		this.talentDTO = talentDTO;
	}

}
