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

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.empmgmt.bd.EmpmgmtViewBD;
import com.wipro.igrs.empmgmt.dto.PersonalDetailsDTO;
import com.wipro.igrs.empmgmt.form.PersonalForm;

/**
* 
* EmpMgmtListSearchAction.java <br>
* EmpMgmtListSearchAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
public class EmpMgmtListSearchAction extends BaseAction {

	private static Logger logger = (Logger) Logger
			.getLogger(EmpMgmtListSearchAction.class);

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
			HttpSession session) throws Exception {
		logger.debug("Entering method EmpMgmtListSearchAction.execute");
		String FORWARD_PATH = "empidsearch";
		try {
			PersonalForm pForm = (PersonalForm) form;
			PersonalDetailsDTO personalDTO = pForm.getPersonalDTO();
			String isMenuClick = request.getParameter("isMenuClick");
			String actionType = pForm.getActionType();
			EmpmgmtViewBD viewDelegate = new EmpmgmtViewBD();
			ArrayList<PersonalDetailsDTO> searchList = personalDTO.getSearchResultList();
			if("yes".equals(isMenuClick)) {
				if(searchList != null) {
					searchList.clear();
				}
				pForm.setPersonalDTO(new PersonalDetailsDTO());
			} else {
				if ("GoSearchEmpList".equals(actionType)) {
					if (searchList != null) {
						searchList.clear();
					}
					searchList = viewDelegate.searchEmployees(
							personalDTO.getFirstNameSearch(),
							personalDTO.getLastNameSearch());
					if(searchList.isEmpty() == false) {	
						personalDTO.setSearchResultList(searchList);
					} else {
						personalDTO.setSearchResultList(new ArrayList<PersonalDetailsDTO>());
					}
				}
			}
			session.setAttribute("personalForm", pForm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.debug("Leaving method EmpMgmtListSearchAction.execute");

		return mapping.findForward(FORWARD_PATH);
	}

}
