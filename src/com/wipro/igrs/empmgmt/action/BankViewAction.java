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
package com.wipro.igrs.empmgmt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.empmgmt.bd.EmpmgmtViewBD;
import com.wipro.igrs.empmgmt.dto.BankDTO;
import com.wipro.igrs.empmgmt.form.BankForm;



/**
* 
* BankViewAction.java <br>
* BankViewAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
public class BankViewAction extends BaseAction {

	
	/**
	 * This is the main action called from the Struts framework.
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance.
	 * @param form
	 *            The optional ActionForm bean for this request.
	 * @param request
	 *            The HTTP Request we are processing.
	 * @param response
	 *            The HTTP Response we are processing.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		String FORWARD_PAGE = null;
		BankForm bankForm = (BankForm) form;
		BankDTO bankDTO = null;
		bankDTO = bankForm.getBankDTO();
		EmpmgmtViewBD employeeViewBD = new EmpmgmtViewBD();
		session.removeAttribute("tablist");
		if (session.getAttribute("viewemployeeId") != null) {
			session.removeAttribute("bankList");
			session.removeAttribute("fundList");
			session.removeAttribute("nomineeList");
			String employeeid=(String)session.getAttribute("viewemployeeId");
			bankDTO = employeeViewBD.getEmpBankDetails(employeeid);
			if(bankDTO != null) {
				bankForm.setBankDetailsPresent(true);
				bankForm.setBankDTO(bankDTO);
				bankForm.setNameAsInBank(bankDTO.getNameAsInBank());
				bankForm.setPanNo(bankDTO.getPanNo());
				bankForm.setBankAccountNo(bankDTO.getBankAccountNo());
				bankForm.setBankName(bankDTO.getBankName());
				bankForm.setBankNameLabel(bankDTO.getBankNameLabel());
				bankForm.setBankBranch(bankDTO.getBankBranch());
				bankForm.setBankAddress(bankDTO.getBankAddress());
				bankForm.setBankIFSC(bankDTO.getBankIFSC());
				bankForm.setBankFundList(bankDTO.getFundList());
			}
		}

		session.setAttribute("bankForm", bankForm);
		FORWARD_PAGE = "ViewBankDetails";

		return mapping.findForward(FORWARD_PAGE);

	}

}
