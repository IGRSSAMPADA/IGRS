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
package com.wipro.igrs.adminConfig.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.adminConfig.bd.AttributeBD;
import com.wipro.igrs.adminConfig.form.AdminAttrForm;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.empmgmt.dto.DropDownDTO;

/**
* 
* AdminAttributeAction.java <br>
* AdminAttributeAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
public class AdminAttributeAction extends BaseAction {
	
	private Logger logger = (Logger) Logger
			.getLogger(AdminAttributeAction.class);

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

		String FORWARD_JSP = "display";
		try {
			
			
			String reqFormName = request.getParameter("formName");
			String reqActionVaue = request.getParameter("actionValue");
			String reqModID = request.getParameter("modID");
			String reqSubID = request.getParameter("subID");
			String reqFunID = request.getParameter("funId");
			String roleId = (String) session.getAttribute("loggedInRole");
			String funId = (String) session.getAttribute("functionId");
			String userId = (String) session.getAttribute("UserId");
			String isMenuClick = request.getParameter("isMenuClick");
			AdminAttrForm adminForm = (AdminAttrForm) form;
			AttributeBD atBD = new AttributeBD();
			String mid = adminForm.getModuleID();
			if (mid == null || ("".equals(mid))) {
				// adminForm.setModuleID(reqModID);
			}
			String smid = adminForm.getSubModuleID();
			if (smid == null || ("".equals(smid))) {
				// adminForm.setSubModuleID(reqSubID);
			}
			String fid = adminForm.getFunctionID();
			if (smid == null || ("".equals(smid))) {
				// adminForm.setFunctionID(reqFunID);
			}
			
			if("yes".equals(isMenuClick)) {
//				System.out.println("isMenuClick");
				adminForm.setModuleList(new ArrayList<DropDownDTO>());
				adminForm.setModuleID("");
			}
			
			session.setAttribute("adminForm", adminForm);
			ArrayList displayList = atBD.displayData(adminForm);
			ArrayList<DropDownDTO> moduleList = adminForm.getModuleList();
			if(moduleList == null || moduleList.isEmpty()) {
				moduleList = atBD.getModuleList();
			}
			adminForm.setModuleList(moduleList);
			ArrayList adminAttrFormList = new ArrayList();
			
			if (adminForm.getFormName() == null
					|| ("".equals(adminForm.getFormName()))) {
				logger.debug("Request Parameter formName : " + request.getParameter("formName"));
				logger.debug("Request Parameter actionValue : " + request.getParameter("actionValue"));
				
				adminForm.setFormName(reqFormName);
				adminForm.setActionValue(reqActionVaue);
				
			}
			
			
			if ("selectionForm".equalsIgnoreCase(adminForm.getFormName())) {
				String actionValue = adminForm.getActionValue();

				// action belong to next button
				if ("display".equalsIgnoreCase(actionValue)) {
//					ArrayList displayList = atBD.displayData(adform);
//					ArrayList adminAttrFormList = new ArrayList();

					populateAttrFormList(displayList, adminAttrFormList);

					session = request.getSession();
					session.setAttribute("displayList1", adminAttrFormList);
					FORWARD_JSP = "display";
				}
				if ("Cancel".equalsIgnoreCase(actionValue)) {
					FORWARD_JSP = "home";
				}
			} // end of selection form
			if ("displayForm".equalsIgnoreCase(adminForm.getFormName())) {
				String actionValue = adminForm.getActionValue();

				// action belong to add button
				if ("Add".equalsIgnoreCase(actionValue)) {
					adminForm.setModuleID("");
					adminForm.setAttributeName("");
					adminForm.setAttributeValue("");
					adminForm.setRemarks("");
					
					adminForm.setFunctionList(new ArrayList<DropDownDTO>());
					FORWARD_JSP = "add";
				}
				if ("Edit".equalsIgnoreCase(actionValue)) {
//					ArrayList displayList = atBD.gettingData(adform);
//					ArrayList adminAttrFormList = new ArrayList();
					
					session = request.getSession(true);
					ArrayList list = atBD.getDataById(adminForm.getDelAttribID());
					if(list != null && list.size() > 0) {
						ArrayList temp = (ArrayList) list.get(0);
						adminForm.setAttributeId((temp .get(0).toString()));
						adminForm.setAttributeName((temp.get(1).toString()));
						adminForm.setAttributeValue((temp.get(2).toString()));
						Object rem = temp.get(3);
						rem = rem == null ? "" : rem.toString();
						adminForm.setRemarks(rem.toString());
						adminForm.setStatusVal(temp.get(4).toString());
						adminForm.setStatusLabel(temp.get(5).toString());
						adminForm.setModuleID(temp.get(6).toString());
						adminForm.setFunctionID(temp.get(7).toString());
						adminForm.setModuleName(temp.get(8).toString());
						adminForm.setFunctionName(temp.get(9).toString());
						
					}
					session.setAttribute("adminForm", adminForm);
					session.setAttribute("displayList1", adminAttrFormList);
					populateAttrFormList(displayList, adminAttrFormList);
					FORWARD_JSP = "editDisplay";
				}
				if ("Delete".equalsIgnoreCase(actionValue)) {
//					ArrayList displayList = atBD.gettingData(adform);
//					ArrayList adminAttrFormList = new ArrayList();
					atBD.deleteDataById(adminForm.getDelAttribID(), roleId, funId, userId);
					displayList = atBD.displayData(adminForm);
					populateAttrFormList(displayList, adminAttrFormList);
					session = request.getSession(true);
					session.setAttribute("displayList1", adminAttrFormList);
					FORWARD_JSP = "display";
				}
				if ("Cancel".equalsIgnoreCase(actionValue)) {
					FORWARD_JSP = "home";
				}
			}// end of display form
			if ("addingForm".equalsIgnoreCase(adminForm.getFormName())) {
				String actionValue = adminForm.getActionValue();

				// action belong to save button
				if ("Save".equalsIgnoreCase(actionValue)) {
					ArrayList<String> errorList = new ArrayList<String>();
					boolean isError = validate(adminForm, errorList, atBD);
					if(isError) {
						request.setAttribute("errorList", errorList);
						session.setAttribute("adminForm", adminForm);
						FORWARD_JSP = "add";
					} else {
						boolean iflag = atBD.addingData(adminForm, roleId,
								funId, userId);
						if (iflag) {

							displayList = atBD.displayData(adminForm);
							populateAttrFormList(displayList, adminAttrFormList);
							session.setAttribute("displayList1",
									adminAttrFormList);
							adminForm.setFormName("selectionForm");
							adminForm.setActionValue("display");
							session.setAttribute("adminForm", adminForm);
							FORWARD_JSP = "display";
						}
					}
				}
				if ("SaveAdd".equalsIgnoreCase(actionValue)) {
					boolean iflag = atBD.addingData(adminForm, roleId, funId,
							userId);
					if (iflag) {
						FORWARD_JSP = "add";
					}
				}
				if ("Cancel".equalsIgnoreCase(actionValue)) {
					populateAttrFormList(displayList, adminAttrFormList);
					session = request.getSession(true);
					session.setAttribute("displayList1", adminAttrFormList);
					FORWARD_JSP = "display";
				}
				if ("moduleChanged".equalsIgnoreCase(actionValue)) {
					ArrayList<DropDownDTO> functionList = new ArrayList<DropDownDTO>();
					functionList = atBD.getFunctionList(adminForm.getModuleID());
					adminForm.setFunctionList(functionList);
					FORWARD_JSP = "add";
				}
			}// end of adding form
			if ("editingForm".equalsIgnoreCase(adminForm.getFormName())) {
				String actionValue = adminForm.getActionValue();

				// action belong to save button
				if ("Edit".equalsIgnoreCase(actionValue)) {
					boolean uflag = atBD.updateData(adminForm, roleId, funId,
							userId);
					if (uflag) {
						displayList = atBD.displayData(adminForm);
						populateAttrFormList(displayList, adminAttrFormList);
						session = request.getSession(true);
						session.setAttribute("displayList1", adminAttrFormList);
					}
					FORWARD_JSP = "display";
				}

				if ("Cancel".equalsIgnoreCase(actionValue)) {
					populateAttrFormList(displayList, adminAttrFormList);
					session = request.getSession(true);
					session.setAttribute("displayList1", adminAttrFormList);
					FORWARD_JSP = "display";
				}
			}// end of editing form
			if ("deletingForm".equalsIgnoreCase(adminForm.getFormName())) {
				String actionValue = adminForm.getActionValue();
				if ("Cancel".equalsIgnoreCase(actionValue)) {
					populateAttrFormList(displayList, adminAttrFormList);
					session = request.getSession(true);
					session.setAttribute("displayList1", adminAttrFormList);
					FORWARD_JSP = "display";
				}
			}// end of deleting form
			session.setAttribute("adminForm", adminForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(FORWARD_JSP);

	}// end of execute
	
	/**
	 * Validate the Admin Form fields
	 * @param adminForm
	 * @param errorList
	 * @param atBD
	 * @return
	 */
	private boolean validate(AdminAttrForm adminForm,
			ArrayList<String> errorList, AttributeBD atBD) {
		boolean retVal;
		try {
			boolean check = atBD.checkAttribute(adminForm);
			if(check == false) {
				errorList.add("Attribute Name must be unique with respect to module. Please specify another name for the attribute");
			}
		} catch (Exception e) {
		} finally {
			retVal = (errorList.isEmpty() == false);
		}
		return retVal;
	}
	/**
	 * Populate the display list for Admin Form
	 * @param displayList
	 * @param adminAttrFormList
	 */
	private void populateAttrFormList(ArrayList displayList,
			ArrayList adminAttrFormList) {
		String rem = "";
		if (displayList.size() > 0) {
			for (int i = 0; i < displayList.size(); i++) {
				ArrayList temp = new ArrayList();
				temp = (ArrayList) displayList.get(i);
				AdminAttrForm admAttrForm = new AdminAttrForm();
				admAttrForm.setAttributeId((temp.get(0).toString()));
				admAttrForm.setAttributeName((temp.get(1).toString()));
				admAttrForm.setAttributeValue((temp.get(2).toString()));
				admAttrForm.setStatusVal((temp.get(4).toString()));
				admAttrForm.setStatusLabel((temp.get(5).toString()));
				admAttrForm.setModuleID((temp.get(6).toString()));
				admAttrForm.setFunctionID((temp.get(7).toString()));
				admAttrForm.setModuleName((temp.get(8).toString()));
				admAttrForm.setFunctionName((temp.get(9).toString()));
				admAttrForm.setUpdateDate((temp.get(10).toString()));
				rem = (String) temp.get(3);
				rem = rem == null ? "NA" : rem;
				rem = rem.trim().equals("") ? "NA" : rem;
				admAttrForm.setRemarks(rem.toString());
				adminAttrFormList.add(admAttrForm);

			}
		}

	}
}
