package com.wipro.igrs.conReport.form;


/**
 * ===========================================================================
 * File           :   ConReportForm.java
 * Description    :   Represents the ConReportForm Class ECR
 * @author        :   Imran Shaik
 * Created Date   :   September 08, 2008
 * ===========================================================================
 */

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.conReport.dto.ConReportDTO;

/**
 * @author Imran Shaik
 *
 */
public class ConReportForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConReportDTO dto;
	private String fiYear;
	private String comments;
	private String repStatus;
	private String remarks;
	private String conId;
	
	/**
	 * @return the dto
	 */
	public ConReportDTO getDto() {
		return dto;
	}

	/**
	 * @param dto the dto to set
	 */
	public void setDto(ConReportDTO dto) {
		this.dto = dto;
	}

	/**
	 * @return the fiYear
	 */
	public String getFiYear() {
		return fiYear;
	}

	/**
	 * @param fiYear the fiYear to set
	 */
	public void setFiYear(String fiYear) {
		this.fiYear = fiYear;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the repStatus
	 */
	public String getRepStatus() {
		return repStatus;
	}

	/**
	 * @param repStatus the repStatus to set
	 */
	public void setRepStatus(String repStatus) {
		this.repStatus = repStatus;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the conId
	 */
	public String getConId() {
		return conId;
	}

	/**
	 * @param conId the conId to set
	 */
	public void setConId(String conId) {
		this.conId = conId;
	}
}
