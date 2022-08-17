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
import java.util.ArrayList;

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
import com.wipro.igrs.common.IGRSCommon;

/**
* 
* CaveatRelsBankDetailAction.java <br>
* CaveatRelsBankDetailAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CaveatRelsBankDetailAction extends BaseAction {
	
	private Logger logger = (Logger) Logger
			.getLogger(CaveatRelsBankDetailAction.class);

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
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException, ServletException,
			Exception {
		logger.debug("WE ARE IN CaveatRelsBankDetailAction Debug");
		logger.info("WE ARE IN  CaveatRelsBankDetailAction INFO");
		String FORWAD_SUCCESS = "failure";
	
		String userId = (String) session.getAttribute("UserId");
		String activityid = request.getParameter("ACTID");
		if(activityid!=null)
		{
		IGRSCommon save=null;
		try {
			save = new IGRSCommon();
			save.saveactivitylog(userId, activityid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
		try {
			CaveatsForm caveatfrm = (CaveatsForm) form;
			CaveatsDTO dto = caveatfrm.getCaveatsDTO();
			String isMenuClick = request.getParameter("isMenuClick");
			ArrayList errorList = new ArrayList();
			if ("yes".equals(isMenuClick)) {
				dto = new CaveatsDTO();
				dto.setActionName("init");
				caveatfrm.setCaveatsDTO(dto);
				FORWAD_SUCCESS = "init";
			} else {
				String actionName = dto.getActionName();
				CaveatsDelegate cavBD = new CaveatsDelegate();
				String loggedIn = (String) session.getAttribute("UserId");
				if (loggedIn == null || "".equalsIgnoreCase(loggedIn)) {
					loggedIn = "igrs";
				}
				dto.setLoggedIn(loggedIn);
				if ("search".equals(actionName)) {
					boolean flag = cavBD.searchBankCaveat(dto);
					if (flag) {
						FORWAD_SUCCESS = "search";
					} else {
						errorList
								.add("Reference ID is not available for Release/संदर्भ आईडी रिलीज के लिए उपलब्ध नहीं है");
						request.setAttribute("errorMessage", "yes");
						request.setAttribute("errorList", errorList);
						FORWAD_SUCCESS = "init";
					}
				} else if ("release".equals(actionName)) {
					boolean log = cavBD.relBankUpdation(dto);
					if (log) {
						FORWAD_SUCCESS = "success";
					} else {
						FORWAD_SUCCESS = "failure";
					}
				}

			}
			return mapping.findForward(FORWAD_SUCCESS);
		} catch (Exception e) {
			//logger.error(e);
			return mapping.findForward("failure");
		}
	}
}
