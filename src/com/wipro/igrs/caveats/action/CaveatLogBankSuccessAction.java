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
package com.wipro.igrs.caveats.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveats.bd.CaveatsDelegate;
import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.caveats.form.CaveatsForm;

/**
* 
* CaveatLogBankSuccessAction.java <br>
* CaveatLogBankSuccessAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
public class CaveatLogBankSuccessAction extends BaseAction {
   
	private Logger logger = 
		(Logger) Logger.getLogger(CaveatLogBankSuccessAction.class);

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
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws IOException, 
                                                                      ServletException,Exception {
    	logger.debug("WE ARE IN CaveatLogBankSuccessAction Debug");
		logger.info("WE ARE IN  CaveatLogBankSuccessAction INFO");
		try {
			CaveatsForm formBean = (CaveatsForm) form;
			CaveatsDTO dtobank = formBean.getCaveatsDTO();
			CaveatsDelegate cavBD = new CaveatsDelegate();
			String FORWAD_SUCCESS = "failure";
			String loggedIn = (String) session.getAttribute("UserId");
			if (loggedIn == null || "".equalsIgnoreCase(loggedIn))
				loggedIn = "igrs";
			dtobank.setLoggedIn(loggedIn);
			boolean result = cavBD.logBankCaveat(dtobank);
			if (result)
				FORWAD_SUCCESS = "success";
			return mapping.findForward(FORWAD_SUCCESS);
		} catch (Exception e) {
			//logger.error(e);
			return (mapping.findForward("failure"));
		}
    }
}

