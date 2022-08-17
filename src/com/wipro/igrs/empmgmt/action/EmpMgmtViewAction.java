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
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.EmpmgmtConstant;
import com.wipro.igrs.empmgmt.bd.CommonBD;
import com.wipro.igrs.empmgmt.bd.EmpmgmtViewBD;
import com.wipro.igrs.empmgmt.dto.DropDownDTO;
import com.wipro.igrs.empmgmt.dto.FamilyMemberDTO;
import com.wipro.igrs.empmgmt.dto.PersonalDetailsDTO;
import com.wipro.igrs.empmgmt.form.PersonalForm;
import com.wipro.igrs.empmgmt.rule.EmpMgmtRule;

/**
* 
* EmpMgmtViewAction.java <br>
* EmpMgmtViewAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "unused", "rawtypes" })
public class EmpMgmtViewAction extends BaseAction {
	/*
	 * Generated Methods
	 */
	private Logger logger = (Logger) Logger.getLogger(EmpMgmtViewAction.class);

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

		String FORWARD_PAGE = "empidsearch";
		try {
			String isMenuClick = request.getParameter("isMenuClick");
			String locale="";
			Locale currentLocale=new Locale(locale);
			if(session.getAttribute("org.apache.struts.action.LOCALE")!=null){
					currentLocale=(Locale)session.getAttribute("org.apache.struts.action.LOCALE");
					locale=currentLocale.toString();
					
				}
			if ("yes".equals(isMenuClick)) {
				PersonalForm pForm = (PersonalForm) form;
				PersonalDetailsDTO dto = pForm.getPersonalDTO();
				if (dto != null) {
					dto.setEmployeeId("");
					session.setAttribute("employeeID", dto.getEmployeeId());
					session.setAttribute("Personal", dto);
				}
				session.removeAttribute("personalForm");
				FORWARD_PAGE = "empidsearch";
//				return mapping.findForward(FORWARD_PAGE);
			} else {
				PersonalForm personForm = (PersonalForm) form;
				PersonalDetailsDTO pDTO = personForm.getPersonalDTO();
				String actiontype = personForm.getActionType();
				
				try {
					pDTO.setEmployeeId(pDTO.getEmployeeId().trim());
				} catch (Exception e) {
				}
				PersonalForm pF=populateDateLists(currentLocale);
				EmpmgmtViewBD employeeViewBD = null;
				PersonalDetailsDTO personalDetailsDTO = null;
				ArrayList childList = null;
				EmpMgmtRule empMgmtRule = null;
				// HttpSession session = request.getSession(true);
				// session.removeAttribute("viewemployeeId");
				// session.removeAttribute("tablist");
				if (actiontype != null
						&& actiontype.equalsIgnoreCase("SearchPersonalDetails")) {

					try {
						if (pDTO.getEmployeeId() != null
								&& pDTO.getEmployeeId().trim().length() != 0) {
							employeeViewBD = new EmpmgmtViewBD();
							boolean employeeid = employeeViewBD
									.getEmployeeID(pDTO.getEmployeeId());
							session.setAttribute("viewemployeeId",
									pDTO.getEmployeeId());
							if (employeeid) {

								personalDetailsDTO = employeeViewBD
										.getPersonalDetails(pDTO
												.getEmployeeId(),locale);
								childList = employeeViewBD.getChildDetails(pDTO
										.getEmployeeId());
								ArrayList<FamilyMemberDTO> familyMembers = employeeViewBD.getFamilyMemberDetails(pDTO.getEmployeeId(),locale);
								personalDetailsDTO.setFamilyMembers(familyMembers );
								personForm.setPersonalDTO(personalDetailsDTO);
								if (personalDetailsDTO != null
										|| childList != null) {
									session.setAttribute("employeeId",
											pDTO.getEmployeeId());
									session.setAttribute("personalForm",
											personForm);
									session.setAttribute("Personal",
											personalDetailsDTO);
									session.setAttribute("childList", childList);
									FORWARD_PAGE = "ViewPersonalDetails";
								}
							} else {

								empMgmtRule = new EmpMgmtRule();
								ArrayList errorList = empMgmtRule
										.validateEmpID(employeeid);

								if (empMgmtRule.isError()) {
									request.setAttribute(
											EmpmgmtConstant.EMPLOYEE_MGMT_ERROR_FLAG,
											Boolean.TRUE);
									request.setAttribute(
											EmpmgmtConstant.EMPLOYEE_MGMT_ERROR_LIST,
											errorList);
									FORWARD_PAGE = "empidsearch";
								} else {

									request.setAttribute(
											EmpmgmtConstant.EMPLOYEE_MGMT_ERROR_FLAG,
											Boolean.FALSE);
									request.setAttribute(
											EmpmgmtConstant.EMPLOYEE_MGMT_ERROR_LIST,
											null);
									FORWARD_PAGE = "ViewPersonalDetails";
								}
							}
						}

					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}

				} else if (request.getParameter("name") != null
						&& request.getParameter("name").equalsIgnoreCase(
								"personal")) {
					employeeViewBD = new EmpmgmtViewBD();

					if (session.getAttribute("viewemployeeId") != null) {
						String employeeid = (String) session
								.getAttribute("viewemployeeId");
						personalDetailsDTO = employeeViewBD
								.getPersonalDetails(employeeid,locale);
						childList = employeeViewBD.getChildDetails(employeeid);
					}
					if (personalDetailsDTO != null || childList != null) {
						session.setAttribute("employeeID", pDTO.getEmployeeId());
						session.setAttribute("Personal", personalDetailsDTO);
						session.setAttribute("childList", childList);
						FORWARD_PAGE = "ViewPersonalDetails";
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return mapping.findForward(FORWARD_PAGE);
	}
	private PersonalForm populateDateLists(Locale locale) {
		PersonalForm pForm=new PersonalForm();
		try {			
			
			ArrayList dayMasterList=pForm.getDayMasterList();
			ArrayList monthMasterList=pForm.getMonthMasterList();
			ArrayList yearMasterList=pForm.getYearMasterList();
			CommonBD delegateObject = new CommonBD();
			if (dayMasterList == null) {
				dayMasterList = new ArrayList<DropDownDTO>();
			}
			if (monthMasterList == null) {
				monthMasterList = new ArrayList<DropDownDTO>();
			}
			if (yearMasterList == null) {
				yearMasterList = new ArrayList<DropDownDTO>();
			}
			pForm=delegateObject.populateDateLists(dayMasterList, monthMasterList,
					yearMasterList,locale);
			/*pForm1.setDayMasterList(dayMasterList);
			pForm1.setMonthMasterList(monthMasterList);
			pForm1.setYearMasterList(yearMasterList);*/
		} catch (Exception e) {

		}
		return pForm;
	}
}
