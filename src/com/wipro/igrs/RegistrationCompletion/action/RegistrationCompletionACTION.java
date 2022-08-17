
/* Copyright (c) 2007-08 WIPRO INFOTECH. All Rights Reserved.
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
 */ 

/* 
 * FILE NAME: RegistrationCompletionACTION.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 28th June 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE ACTION FOR REGISTRATION COMPLETION ACTION.
 */


package com.wipro.igrs.RegistrationCompletion.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.RegistrationCompletion.bd.RegistrationCompletionBD;
import com.wipro.igrs.RegistrationCompletion.constant.RegistrationCompletionCONSTANT;
import com.wipro.igrs.RegistrationCompletion.dto.RegistrationCompletionDTO;
import com.wipro.igrs.RegistrationCompletion.form.RegistrationCompletionFORM;
import com.wipro.igrs.baseaction.action.BaseAction;

/**
 * @author NIHAR M.
 *
 */
public class RegistrationCompletionACTION extends BaseAction {
	private Logger logger = (Logger) Logger.getLogger(RegistrationCompletionACTION.class);
	String forwardJsp = new String("regComplete");

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
			HttpServletRequest request,
			HttpServletResponse response,HttpSession session) 
	throws Exception {
		String page=request.getParameter("page");
		if(page!=null){
			if("regComplete".equals(page)){
				logger.debug("page:-  "+page);
				forwardJsp = RegistrationCompletionCONSTANT.REGISTRATION_COMPLETE;
				logger.debug("Page is forwarded to :-    "+forwardJsp);
			}
		}

		if (form != null) {
			logger.debug("form != null");
			RegistrationCompletionFORM eForm = (RegistrationCompletionFORM)form;
	//		HttpSession session = request.getSession(true);
			RegistrationCompletionBD regBD = new RegistrationCompletionBD();

			String formName =  eForm.getFormName();
			logger.debug("formName:-  "+formName);

			if(RegistrationCompletionCONSTANT.REG_COMPLETION_FORM.equalsIgnoreCase(formName)){		
				logger.debug("Form Values are matched for:-   "+RegistrationCompletionCONSTANT.REG_COMPLETION_FORM);
				if("searchTxnID".equalsIgnoreCase(eForm.getActionName())){

					logger.debug("Action matched for:-    "+eForm.getActionName());
					RegistrationCompletionDTO objDTO = eForm.getRegDTO();
					ArrayList txnIdDetails = regBD.getTxnIDDetails(objDTO);
					eForm.setRegIdDetails(txnIdDetails);
					session.setAttribute("txnIdDetails", txnIdDetails);

					forwardJsp = RegistrationCompletionCONSTANT.GOTO_REG_ID_DETAILS;
					logger.debug("forwardJsp:-   "+forwardJsp);
				}

				if("searchIndividualID".equalsIgnoreCase(eForm.getActionName())){

					logger.debug("Action matched for:-    "+eForm.getActionName());
					String regTxnID = request.getParameter("caseId");
					RegistrationCompletionDTO objDTO = eForm.getRegDTO();
					objDTO = regBD.getIndRegIDDetails(regTxnID);
					ArrayList allUpdateStatus = regBD.getStatusList();
					eForm.setStatusList(allUpdateStatus);
					eForm.setRegDTO(objDTO);
					forwardJsp = RegistrationCompletionCONSTANT.IND_REGID_DETAILS;
					logger.debug("forwardJsp:-   "+forwardJsp);

				}

				if("updateRegTxn".equalsIgnoreCase(eForm.getActionName())){

					logger.debug("Action matched for:-    "+eForm.getActionName());
					System.out.println("Action matched for:-    "+eForm.getActionName());
					RegistrationCompletionDTO objDTO = eForm.getRegDTO();
					boolean detailsSubmited = regBD.submitUpdatedDetails(objDTO);
					if(detailsSubmited){
						logger.debug("Inserted Successfully");
						forwardJsp = "remarksGiven";
					}
					else{
						logger.debug("Failed!");
					}
				}
			}
		}
		return mapping.findForward(forwardJsp);
	}
}