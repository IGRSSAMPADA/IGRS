/**
 * LoanForm.java
 */

package com.wipro.igrs.loanadvance.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.loanadvance.dto.LoanDTO;

/**
 * @author jagadish
 * 
 */
public class LoanForm extends BaseForm {
	/*
	 * Generated Methods
	 */

	private String pageName = null;

	private String pageAction = null;

	LoanDTO loanDTO = new LoanDTO();

	/**
	 * @return the pageName
	 */
	public String getPageName() {
		return pageName;
	}

	/**
	 * @param pageName
	 *            the pageName to set
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	/**
	 * @return the pageAction
	 */
	public String getPageAction() {
		return pageAction;
	}

	/**
	 * @param pageAction
	 *            the pageAction to set
	 */
	public void setPageAction(String pageAction) {
		this.pageAction = pageAction;
	}

	/**
	 * @return the loanDTO
	 */
	public LoanDTO getLoanDTO() {
		return loanDTO;
	}

	/**
	 * @param loanDTO
	 *            the loanDTO to set
	 */
	public void setLoanDTO(LoanDTO loanDTO) {
		this.loanDTO = loanDTO;
	}

	/**
	 * Method validate
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method reset
	 * 
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}
}