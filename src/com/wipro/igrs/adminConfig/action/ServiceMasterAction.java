/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	ServiceMasterAction.java
 * Author		:	vengamamba
 * Date			: 	21/06/2008
 */
package com.wipro.igrs.adminConfig.action;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.adminConfig.bd.ServiceMasterBD;
import com.wipro.igrs.adminConfig.dto.ServiceMasterDTO;
import com.wipro.igrs.adminConfig.form.ServiceMasterForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class ServiceMasterAction extends BaseAction {
	private Logger logger = (Logger) Logger
			.getLogger(ServiceMasterAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		logger.info("inside MohallaMasterAction class...");
		String forward = "ServiceMaster";
	//	HttpSession session = request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		String userId1 = null;
		userId1 = "ADMIN";
		ServiceMasterForm partyForm = (ServiceMasterForm) form;
		ServiceMasterBD partybd = new ServiceMasterBD();
		ServiceMasterDTO dto = new ServiceMasterDTO();
		String typeofopr = request.getParameter("typeofaction");
		if (typeofopr != null) {
			if (typeofopr.equals("create")) {
				forward = "ServiceMaster";
			} else if (typeofopr.equals("view")) {
				ArrayList  partyList = partybd
						.getServiceList();
				dto.setServiceList(partyList);
				forward = "ServiceMasterView";
			}else if (typeofopr.equalsIgnoreCase("delete")) {
				String deleteService = request.getParameter("deleteService");
				StringTokenizer st = new StringTokenizer(deleteService, "*");
				while (st.hasMoreTokens()) {
					boolean boo=partybd.deleteService(st.nextToken(),roleId,funId,userId);

				}
				forward = "ServiceMasterView";
			}
		}
		if (partyForm.getServicePageName() != null) {
			if (partyForm.getServicePageName()
					.equalsIgnoreCase("ServiceCreate")) {
				if (partyForm.getActionValue().equalsIgnoreCase("Submit")) {

					String params[] = new String[4];
					params[0] = partyForm.getServiceName();
					params[1] = partyForm.getServiceDesc();
					params[3] = userId1;
					params[2] = partyForm.getServiceStatus();

					boolean flag = partybd.addServiceMaster(params,roleId,funId,userId);
					if (flag)
						forward = "success";
					else
						forward = "fail";
				} else
					forward = "start";
			} else {
				if (partyForm.getServicePageName().equalsIgnoreCase(
						"ServiceUpdate")) {
					if (partyForm.getActionValue().equalsIgnoreCase("Submit")) {

						String params[] = new String[5];
						params[0] = partyForm.getServicedto().getServiceName();
						params[1] = partyForm.getServicedto().getServiceDesc();
						params[3] = userId1;

						params[2] = partyForm.getServicedto()
								.getServiceStatus();
						params[4] = partyForm.getServicedto().getServiceId();
						boolean flag = partybd.updateServicemaster(params,roleId,funId,userId);
						if (flag)
							forward = "success";
						else
							forward = "fail";
					} else
						forward = "start";
				}
			}
		}

		String partyid = request.getParameter("serviceId");
		if (partyid != null) {
			forward = "ServiceMasterUpdate";
			dto = partybd.getServiceIdList(partyid);
		}
		partyForm.setServicedto(dto);

		session.setAttribute("serviceList", partyForm.getServicedto()
				.getServiceList());
		session.setAttribute("serviceMasterForm", partyForm);
		partyForm.setServicePageName(null);
		return mapping.findForward(forward);
	}
}
