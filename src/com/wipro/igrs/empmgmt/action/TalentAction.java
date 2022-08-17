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
import com.wipro.igrs.common.EmpmgmtConstant;
import com.wipro.igrs.empmgmt.bd.CommonBD;
import com.wipro.igrs.empmgmt.dto.AcademicDTO;
import com.wipro.igrs.empmgmt.dto.PrevEmpDTO;
import com.wipro.igrs.empmgmt.form.TalentForm;
import com.wipro.igrs.empmgmt.rule.EmpMgmtRule;

/**
* 
* TalentAction.java <br>
* TalentAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TalentAction extends BaseAction {
	private Logger logger = (Logger) Logger.getLogger(TalentAction.class);
	
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

		EmpMgmtRule empMgmtRule = null;
		String FORWARDPAGE = "talent";
		CommonBD commonBD = new CommonBD();
		String formName = request.getParameter("formName");
		//HttpSession session = request.getSession(true);
		String strUserId = session.getAttribute("UserId").toString();
		TalentForm talentForm = (TalentForm) form;
		String update = request.getParameter("update");
		try {
			String empId = (String) session.getAttribute("employeeId");
			ArrayList academicFormList = talentForm.getAcademicList();
			ArrayList prevEmpFormList = talentForm.getPrevEmpList();
			refreshAcademicDetails(academicFormList, request);
			refreshPrevEmployment(prevEmpFormList, request);
//			ArrayList academicList = new ArrayList();
//			if (academicFormList != null) {
//				for (int i = 0; i < academicFormList.size(); i++) {
//					AcademicDTO academicDTO1 = (AcademicDTO) academicFormList
//							.get(i);
//					academicList.add(academicDTO1);
//				}
//			}
//			ArrayList prevEmpList = new ArrayList();
//			if (prevEmpFormList != null) {
//
//				for (int i = 0; i < prevEmpFormList.size(); i++) {
//					PrevEmpDTO prevEmpDTO1 = (PrevEmpDTO) prevEmpFormList.get(i);
//					prevEmpList.add(prevEmpDTO1);
//				}
//			}
			AcademicDTO academicDTO2 = new AcademicDTO();
//			PrevEmpDTO prevEmpDTO = new PrevEmpDTO();
			if (formName != null && formName.equalsIgnoreCase("talent")) {
				try {
					ArrayList streamList = new ArrayList();
					streamList = commonBD.getStream();
					academicFormList = commonBD.getAcademicDetails(empId);
					talentForm.setAcademicList(academicFormList);
					prevEmpFormList = commonBD.getPreviousEmploymentDetails(empId);
					talentForm.setPrevEmpList(prevEmpFormList);
					academicDTO2.setQualificationList(streamList);
					
					session.setAttribute("academicList", academicFormList);
					session.setAttribute("prevEmpList", prevEmpFormList);
					session.setAttribute("streamList", streamList);
					session.setAttribute("streamBean", academicDTO2);
					session.removeAttribute("talentupdate");
					if (update != null) {
						session.setAttribute("talentupdate", update);
					}
					FORWARDPAGE = "talent";
				} catch (Exception exception) {
					logger.error(exception.getMessage(),exception);
				}
			}  else if ("addQualificationRow".equals(formName)) {
				try {
					academicFormList = talentForm.getAcademicList();
					if(academicFormList != null) {
						AcademicDTO academicBlankDTO = new AcademicDTO();
						academicBlankDTO.setQualificationList(commonBD.getStream());
						academicFormList.add(academicBlankDTO);
						talentForm.setAcademicList(academicFormList);
						session.setAttribute("academicList", academicFormList);
					}
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
				FORWARDPAGE = "talent";
					
			} else if ("delQualificationRow".equals(formName)) {
				try {
					academicFormList = talentForm.getAcademicList();
					int rowIndex = Integer.parseInt((String) request
							.getParameter("delQualifyIndex"));
					if (academicFormList != null && academicFormList.size() > 0) {
						academicFormList.remove(rowIndex);
						talentForm.setAcademicList(academicFormList);
						session.setAttribute("academicList", academicFormList);
					}
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
				FORWARDPAGE = "talent";

			} else if ("addEmploymentRow".equals(formName)) {
				try {
					prevEmpFormList = talentForm.getPrevEmpList();
					if(prevEmpFormList != null) {
						PrevEmpDTO prevDTO = new PrevEmpDTO();
						prevEmpFormList.add(prevDTO);
						talentForm.setPrevEmpList(prevEmpFormList);
						session.setAttribute("prevEmpList", prevEmpFormList);
					}
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
				FORWARDPAGE = "talent";
					
			} else if ("delEmploymentRow".equals(formName)) {
				try {
					prevEmpFormList = talentForm.getPrevEmpList();
					int rowIndex = Integer.parseInt((String) request
							.getParameter("delEmpIndex"));
					if (prevEmpFormList != null && prevEmpFormList.size() > 0) {
						prevEmpFormList.remove(rowIndex);
						talentForm.setPrevEmpList(prevEmpFormList);
						session.setAttribute("prevEmpList", prevEmpFormList);
					}
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
				FORWARDPAGE = "talent";

			}
				else if (talentForm.getActionType() != null
					&& talentForm.getActionType().equals("InsertTalentDetails")) {
				try {
					ArrayList errorList = new ArrayList();
					empMgmtRule = new EmpMgmtRule();
					errorList = empMgmtRule.validateQuali(academicFormList,
							prevEmpFormList);
					if (empMgmtRule.isError()) {
						request.setAttribute(
								EmpmgmtConstant.EMP_MGMT_ERROR_FLAG, "true");
						request.setAttribute(
								EmpmgmtConstant.EMP_MGMT_ERROR_LIST, errorList);
						FORWARDPAGE = "talent";

					} else {
						String employeeid = null;
						if (session.getAttribute("employeeId") != null) {
							employeeid = session.getAttribute("employeeId")
									.toString();
						}
						
						logger.debug("Before Save");
						boolean flag = commonBD.addTalent(academicFormList,
								prevEmpFormList, employeeid, strUserId);
						logger.debug("After Save");
						if (flag
								&& session.getAttribute("talentupdate") == null) {

							logger.debug("In decision Save");
							ArrayList list = null;
							if (session.getAttribute("tablist") != null) {
								list = (ArrayList) session
										.getAttribute("tablist");
							} else {
								list = new ArrayList();
							}
							if (!list.contains("PROPERTY")) {
								FORWARDPAGE = "property";
							} else if (!list.contains("BANK")) {
								FORWARDPAGE = "bank";
							} else if (!list.contains("OFFICE")) {
								FORWARDPAGE = "office";
							} else {
								FORWARDPAGE = "success";
							}
							list.add("TALENT");
							session.setAttribute("tablist", list);
							request
									.setAttribute("success",
											"<font color=green>Talent Information submitted successfully!</font>");
							session.removeAttribute("streamList");
							session.removeAttribute("streamBean");

							// FORWARDPAGE = "bank";
						} else if (session.getAttribute("talentupdate") != null
								&& session.getAttribute("talentupdate")
										.toString().equals("true")) {

							FORWARDPAGE = "viewpersonal";
						} else {
							request
									.setAttribute("failure",
											"<font color=red>Talent Information not submitted successfully!</font>");

							FORWARDPAGE = "talent";
						}
					}
			} catch (Exception e) {
				request.setAttribute("failure",
						"<font color=red>Talent Information not submitted successfully!</font>");
				FORWARDPAGE = "talent";
				logger.error(e.getMessage(), e);
					//logger.error(exception);
				}
			}

		} catch (Exception e) {
			request.setAttribute("failure",
			"<font color=red>Talent Information not submitted successfully!</font>");
			FORWARDPAGE = "talent";
			logger.error(e.getMessage(), e);
		}
		logger.debug("FORWARDPAGE"+FORWARDPAGE);
		return mapping.findForward(FORWARDPAGE);
	}

	/**
	 * @param academicFormList
	 * @param request
	 */
	private void refreshAcademicDetails(ArrayList academicFormList, HttpServletRequest request) {
		if(academicFormList == null) {
			return;
		}
		try {
			String prefix = "academicDTO[";
			String suffix = "].";
			String degree = "degree";
			String stream = "stream";
			String passingYear = "passingYear";
			String grade = "grade";
			String value = "";

			StringBuilder paramBldr = new StringBuilder();
			AcademicDTO academicDTO;
			for (int iLoop = 0; iLoop < academicFormList.size(); iLoop++) {
				academicDTO = (AcademicDTO) academicFormList.get(iLoop);
				// degree
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(iLoop);
				paramBldr.append(suffix);
				paramBldr.append(degree);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					academicDTO.setDegree(value);
				}
				// stream
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(iLoop);
				paramBldr.append(suffix);
				paramBldr.append(stream);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					academicDTO.setStream(value);
				}
				// passingYear
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(iLoop);
				paramBldr.append(suffix);
				paramBldr.append(passingYear);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					academicDTO.setPassingYear(value);
				}
				// grade
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(iLoop);
				paramBldr.append(suffix);
				paramBldr.append(grade);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					academicDTO.setGrade(value);
				}
				
			}
		} catch (Exception e) {
			//logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * @param prevEmpFormList
	 * @param request
	 */
	private void refreshPrevEmployment(ArrayList prevEmpFormList, HttpServletRequest request) {
		if(prevEmpFormList == null) {
			return;
		}
		try {
			String prefix = "prevEmpDTO[";
			String suffix = "].";
			String organization = "organization";
			String designation = "designation";
			String fromDate = "fromDate";
			String toDate = "toDate";
			String pfAccLocation = "pfAccLocation";
			String pfAccNo = "pfAccNo";
			String reasonForSeparation = "reasonForSeparation";
			String value = "";

			StringBuilder paramBldr = new StringBuilder();
			PrevEmpDTO prevEmpDTO;
			for (int iLoop = 0; iLoop < prevEmpFormList.size(); iLoop++) {
				prevEmpDTO = (PrevEmpDTO) prevEmpFormList.get(iLoop);
				// organization
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(iLoop);
				paramBldr.append(suffix);
				paramBldr.append(organization);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					prevEmpDTO.setOrganization(value);
				}

				// designation
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(iLoop);
				paramBldr.append(suffix);
				paramBldr.append(designation);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					prevEmpDTO.setDesignation(value);
				}

				// fromDate
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(iLoop);
				paramBldr.append(suffix);
				paramBldr.append(fromDate);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					prevEmpDTO.setFromDate(value);
				}

				// toDate
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(iLoop);
				paramBldr.append(suffix);
				paramBldr.append(toDate);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					prevEmpDTO.setToDate(value);
				}

				// pfAccLocation
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(iLoop);
				paramBldr.append(suffix);
				paramBldr.append(pfAccLocation);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					prevEmpDTO.setPfAccLocation(value);
				}
				
				// pfAccNo
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(iLoop);
				paramBldr.append(suffix);
				paramBldr.append(pfAccNo);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					prevEmpDTO.setPfAccNo(value);
				}
				
				// reasonForSeparation
				paramBldr.delete(0, paramBldr.length());
				paramBldr.append(prefix);
				paramBldr.append(iLoop);
				paramBldr.append(suffix);
				paramBldr.append(reasonForSeparation);
				value = request.getParameter(paramBldr.toString());
				if (value != null && ("".equals(value)) == false) {
					prevEmpDTO.setReasonForSeparation(value);
				}
				
			}
		} catch (Exception e) {
			//logger.error(e.getMessage(), e);
		}
	}
}



