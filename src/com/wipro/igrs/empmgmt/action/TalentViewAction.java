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

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.empmgmt.bd.EmpmgmtViewBD;
import com.wipro.igrs.empmgmt.dto.AcademicDTO;
import com.wipro.igrs.empmgmt.dto.PrevEmpDTO;
import com.wipro.igrs.empmgmt.form.TalentForm;

/**
* 
* TalentViewAction.java <br>
* TalentViewAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "unused", "rawtypes" })
public class TalentViewAction extends BaseAction {

	
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
		try {
			TalentForm talentForm = (TalentForm) form;
			AcademicDTO academicDTO = null;
			PrevEmpDTO prevDTO = null;
			String actiontype = talentForm.getActionType();
			EmpmgmtViewBD employeeViewBD = null;
			ArrayList academicList = null;
			ArrayList prevempList = null;
			academicDTO = talentForm.getAcademicDTO();
			prevDTO = talentForm.getPrevEmpDTO();
			employeeViewBD = new EmpmgmtViewBD();
			//	HttpSession session = request.getSession(true);
			if (session.getAttribute("viewemployeeId") != null) {
				String employeeid = (String) session
						.getAttribute("viewemployeeId");
				academicList = employeeViewBD.getAcademicDetails(employeeid);
				prevempList = employeeViewBD
						.getPreviousEmploymentDetails(employeeid);
			}
			if (academicList != null) {
				session.setAttribute("academic", academicList);
			}
			if (prevempList != null) {
				session.setAttribute("prevempList", prevempList);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		FORWARD_PAGE = "ViewTalentDetails";

		return mapping.findForward(FORWARD_PAGE);

	}
}
