/*
 * SuspensionForm.java
 */

package com.wipro.igrs.departmentalenquiry.form;


import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.departmentalenquiry.dto.CriminalCaseEnquiryDTO;
import com.wipro.igrs.departmentalenquiry.dto.EmployeeAcceptChanrgesDTO;
import com.wipro.igrs.departmentalenquiry.dto.EmployeeSearchDTO;
import com.wipro.igrs.departmentalenquiry.dto.PreliminaryEnquiryDTO;
import com.wipro.igrs.departmentalenquiry.dto.SuspensionOrderDTO;


public class SuspensionForm extends ActionForm {
	private String	               formName;

	private CriminalCaseEnquiryDTO	criminalDTO;

	private PreliminaryEnquiryDTO	prelimDTO;

	private SuspensionOrderDTO	   suspenseDTO;
	
	private EmployeeAcceptChanrgesDTO peAcceptsDTO;

	private EmployeeSearchDTO empSearchDTO; 
	
	private FormFile theFile1	= null;
	private FormFile theFile2	= null;
	/**
	 * SuspensionForm Constructor
	 */
	public SuspensionForm() {
		criminalDTO = new CriminalCaseEnquiryDTO();
		prelimDTO = new PreliminaryEnquiryDTO();
		suspenseDTO = new SuspensionOrderDTO();
		peAcceptsDTO = new EmployeeAcceptChanrgesDTO();
		empSearchDTO = new EmployeeSearchDTO();
	}

	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * @param formName
	 *            the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

	/**
	 * @return the criminalDTO
	 */
	public CriminalCaseEnquiryDTO getCriminalDTO() {
		return criminalDTO;
	}

	/**
	 * @param criminalDTO
	 *            the criminalDTO to set
	 */
	public void setCriminalDTO(CriminalCaseEnquiryDTO criminalDTO) {
		this.criminalDTO = criminalDTO;
	}

	/**
	 * @return the prelimDTO
	 */
	public PreliminaryEnquiryDTO getPrelimDTO() {
		return prelimDTO;
	}

	/**
	 * @param prelimDTO
	 *            the prelimDTO to set
	 */
	public void setPrelimDTO(PreliminaryEnquiryDTO prelimDTO) {
		this.prelimDTO = prelimDTO;
	}

	/**
	 * @return the suspenseDTO
	 */
	public SuspensionOrderDTO getSuspenseDTO() {
		return suspenseDTO;
	}

	/**
	 * @param suspenseDTO
	 *            the suspenseDTO to set
	 */
	public void setSuspenseDTO(SuspensionOrderDTO suspenseDTO) {
		this.suspenseDTO = suspenseDTO;
	}

	/**
	 * @return the peAcceptsDTO
	 */
	public EmployeeAcceptChanrgesDTO getPeAcceptsDTO() {
		return peAcceptsDTO;
	}

	/**
	 * @param peAcceptsDTO the peAcceptsDTO to set
	 */
	public void setPeAcceptsDTO(EmployeeAcceptChanrgesDTO peAcceptsDTO) {
		this.peAcceptsDTO = peAcceptsDTO;
	}

	/**
	 * @return the empSearchDTO
	 */
	public EmployeeSearchDTO getEmpSearchDTO() {
		return empSearchDTO;
	}

	/**
	 * @param empSearchDTO the empSearchDTO to set
	 */
	public void setEmpSearchDTO(EmployeeSearchDTO empSearchDTO) {
		this.empSearchDTO = empSearchDTO;
	}

	/**
	 * @return the theFile1
	 */
	public FormFile getTheFile1() {
		return theFile1;
	}

	/**
	 * @param theFile1 the theFile1 to set
	 */
	public void setTheFile1(FormFile theFile1) {
		this.theFile1 = theFile1;
	}

	/**
	 * @return the theFile2
	 */
	public FormFile getTheFile2() {
		return theFile2;
	}

	/**
	 * @param theFile2 the theFile2 to set
	 */
	public void setTheFile2(FormFile theFile2) {
		this.theFile2 = theFile2;
	}

	

	
	
	

}