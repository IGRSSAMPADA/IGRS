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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveats.action.CaveatsViewSearchAction;
import com.wipro.igrs.empmgmt.bd.EmpmgmtViewBD;
import com.wipro.igrs.empmgmt.dto.OfficalInfoDTO;
import com.wipro.igrs.empmgmt.dto.ServiceVerificationDTO;
import com.wipro.igrs.empmgmt.form.OfficeForm;

/**
* 
* OfficeViewAction.java <br>
* OfficeViewAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "unused", "rawtypes" })
public class OfficeViewAction extends BaseAction {
	/*
	 * Generated Methods
	 */

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
			OfficeForm officeForm = (OfficeForm) form;
			OfficalInfoDTO officeDTO = new OfficalInfoDTO();
			ServiceVerificationDTO serviceDTO = new ServiceVerificationDTO();
			EmpmgmtViewBD employeeViewBD = null;
			employeeViewBD = new EmpmgmtViewBD();
			//HttpSession session = request.getSession(true);
			ArrayList officeList = null;
			ArrayList serviceList = null;
			ArrayList documentList = null;
			ArrayList reportList = null;
			String locale="";
			Locale currentLocale;
			if(session.getAttribute("org.apache.struts.action.LOCALE")!=null){
					currentLocale=(Locale)session.getAttribute("org.apache.struts.action.LOCALE");
					locale=currentLocale.toString();
					
				}
			String functionName = request.getParameter("functionName");
			if("DocDownload".equals(functionName)) {
				String attachIndex = request.getParameter("attachIndex");
				int index = Integer.parseInt(attachIndex);
				documentList = (ArrayList) session.getAttribute("documentList");
				Object item = documentList.get(index);
				OfficalInfoDTO uploadedItem = (OfficalInfoDTO) item;
				CaveatsViewSearchAction.downloadDocument(response, uploadedItem.getDocumentContents(), uploadedItem.getDocumentName());
			} else {
				if (session.getAttribute("viewemployeeId") != null) {
					String employeeid=(String)session.getAttribute("viewemployeeId");
					officeList = employeeViewBD.getOfficialDetails(employeeid,locale);
					reportList = employeeViewBD.getReportingHierarchyDetails(employeeid);
					serviceList = employeeViewBD.getServiceDetails(employeeid);
					documentList = employeeViewBD.getDocumentDetails(employeeid,locale);
					if(request.getAttribute("update")!=null){
						String update=(String)request.getAttribute("update");
						if(update.equalsIgnoreCase("updatesuccess")){
					if(locale.equalsIgnoreCase("hi_IN")){
						request
						.setAttribute("updatesuccess",
								"<font color=green>कार्यालय की सूचना सफलतापूर्वक अद्यतन की गई है </font>");	
					}else{
						request
						.setAttribute("updatesuccess",
								"<font color=green>Official Information updated successfully!</font>");	
					}
				}
				}
				}

				if (officeList != null) {
					session.setAttribute("officeList", officeList);
				}
				if (reportList != null) {
					session.setAttribute("reportList", reportList);
				}
				if (serviceList != null) {
					session.setAttribute("serviceList", serviceList);
				}
				if (documentList != null) {
					session.setAttribute("documentList", documentList);
				}
			}
		} catch (Exception e) {
		}

		FORWARD_PAGE = "ViewOfficeDetails";

		return mapping.findForward(FORWARD_PAGE);
	}

}
