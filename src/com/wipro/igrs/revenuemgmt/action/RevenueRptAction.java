/* 
 * <Copyright information>
 *
 * <Customer specific copyright notice (if any) >
 *
 * ==============================================================================
 * This file contains proprietary information of Wipro Infotech Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007
 * ==============================================================================
 * 
 * File Name	   		: RevenueCltRptAction.java
 *
 * Description	   		: Struts Action to load initial pages
 *
 * Version Number  		: 1.0 
 *
 * Created Date	   		: 13 02 2008 
 *
 * Modification History	: Created
 */

package com.wipro.igrs.revenuemgmt.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.revenuemgmt.bd.RevenueMgmtBD;
import com.wipro.igrs.revenuemgmt.common.RevenueMgmtConstants;
import com.wipro.igrs.revenuemgmt.dto.RevenueMgmtDTO;
import com.wipro.igrs.revenuemgmt.form.RevenueMgmtForm;

public class RevenueRptAction extends BaseAction {

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
	 * @return ActionForward
	 * @throws IOException
	 * @throws ServletException
	 */
	private Logger logger = (Logger) Logger.getLogger(RevenueRptAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		logger.debug("you are in RevenueRptAction class if part start");
		RevenueMgmtForm revenueMgmtForm = (RevenueMgmtForm) form;
		RevenueMgmtDTO revenueMgmtDTO = new RevenueMgmtDTO();
		String returnPage = "";
		RevenueMgmtBD revenueReportsBD = new RevenueMgmtBD();
		//HttpSession session = request.getSession();
	    String roleId = (String)session.getAttribute("role");
		String funId = (String)session.getAttribute("functionId");
		String userId = (String)session.getAttribute("UserId");
		String label = request.getParameter("label");
		if (form != null) {
			if (label != null) {
				logger.debug("---------------------");

				// for revenue collection
				if (label.equalsIgnoreCase("revCltRpt")) {
					logger.debug("in revCltRpt action");
					ArrayList districtList = revenueReportsBD
							.getDistrictDetails(revenueMgmtForm);
					revenueMgmtForm.setDistrictList(districtList);
					request.setAttribute("districtlst", revenueMgmtForm);

					if (request.getParameter("myoffice") != null) {
						String districtId = (String) request
								.getParameter("myoffice");
						logger.debug("selected Distrinct is========="
								+ districtId);
						ArrayList officeTypeLst = revenueReportsBD
								.getOfficeTypeDetails(revenueMgmtForm);
						revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
						request.setAttribute("officetypelst", revenueMgmtForm);
						logger.debug("in action officetypelst:-"
								+ revenueMgmtForm.getOfficeTypeLst());
					}
					if (request.getParameter("myoffice") != null) {
						String district = (String) request
								.getParameter("myoffice");
						logger.debug("selected Distrinct is========="
								+ district);
						ArrayList officeNameList = revenueReportsBD
								.getOfficeNameDetails(revenueMgmtForm);
						logger.debug("officeNameList-------------"
								+ officeNameList);
						revenueMgmtForm.setOfficeNameList(officeNameList);
						request.setAttribute("officenamelst", revenueMgmtForm);
					}
					returnPage = RevenueMgmtConstants.REV_CLT_RPT_SUCCESS;
					logger.debug("returnpage:-" + returnPage);
				}
			}

			// for cash payments
			if (label != null) {
				if (label.equalsIgnoreCase("cashPymnt")) {
					logger.debug("in cashRpt action");
					ArrayList districtList = revenueReportsBD
							.getDistrictDetails(revenueMgmtForm);
					revenueMgmtForm.setDistrictList(districtList);
					request.setAttribute("districtlst", revenueMgmtForm);
					if (request.getParameter("myoffice") != null) {
						String districtId = (String) request
								.getParameter("myoffice");
						logger.debug("selected Distrinct is========="
								+ districtId);
						ArrayList officeTypeLst = revenueReportsBD
								.getOfficeTypeDetails(revenueMgmtForm);
						revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
						request.setAttribute("officetypelst", revenueMgmtForm);
						logger.debug("in action officetypelst:-"
								+ revenueMgmtForm.getOfficeTypeLst());
					}
					if (request.getParameter("myoffice") != null) {
						String district = (String) request
								.getParameter("myoffice");
						logger.debug("selected Distrinct is========="
								+ district);

						ArrayList officeNameList = revenueReportsBD
								.getOfficeNameDetails(revenueMgmtForm);
						logger.debug("officeNameList-------------"
								+ officeNameList);
						revenueMgmtForm.setOfficeNameList(officeNameList);
						request.setAttribute("officenamelst", revenueMgmtForm);
					}
					returnPage = RevenueMgmtConstants.CASH_PYMNT_SUCCESS;
				}
			}

			// for Challan Payments
			if (label != null) {
				if (label.equalsIgnoreCase(
						"challanPymnt")) {
					logger.debug("in challanRpt action");
					ArrayList districtList = revenueReportsBD
							.getDistrictDetails(revenueMgmtForm);
					revenueMgmtForm.setDistrictList(districtList);
					request.setAttribute("districtlst", revenueMgmtForm);
					if (request.getParameter("myoffice") != null) {
						String districtId = (String) request
								.getParameter("myoffice");
						logger.debug("selected Distrinct is========="
								+ districtId);
						ArrayList officeTypeLst = revenueReportsBD
								.getOfficeTypeDetails(revenueMgmtForm);
						revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
						request.setAttribute("officetypelst", revenueMgmtForm);
						logger.debug("in action officetypelst:-"
								+ revenueMgmtForm.getOfficeTypeLst());
					}
					if (request.getParameter("myoffice") != null) {
						String district = (String) request
								.getParameter("myoffice");
						logger.debug("selected Distrinct is========="
								+ district);

						ArrayList officeNameList = revenueReportsBD
								.getOfficeNameDetails(revenueMgmtForm);
						logger.debug("officeNameList-----" + officeNameList);
						revenueMgmtForm.setOfficeNameList(officeNameList);
						request.setAttribute("officenamelst", revenueMgmtForm);
					}
					returnPage = RevenueMgmtConstants.CHALLAN_PYMNT_SUCCESS;
				}
			}

			// for online Payments
			if (label != null) {
				if (label.equalsIgnoreCase(
						"onlinePymnt")) {
					logger.debug("in onlineRpt action");
					ArrayList districtList = revenueReportsBD
							.getDistrictDetails(revenueMgmtForm);
					revenueMgmtForm.setDistrictList(districtList);
					request.setAttribute("districtlst", revenueMgmtForm);
					if (request.getParameter("myoffice") != null) {
						String districtId = (String) request
								.getParameter("myoffice");
						logger.debug("selected Distrinct is========="
								+ districtId);
						ArrayList officeTypeLst = revenueReportsBD
								.getOfficeTypeDetails(revenueMgmtForm);
						revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
						request.setAttribute("officetypelst", revenueMgmtForm);
						logger.debug("in action officetypelst:-"
								+ revenueMgmtForm.getOfficeTypeLst());
					}
					if (request.getParameter("myoffice") != null) {
						String district = (String) request
								.getParameter("myoffice");
						logger.debug("selected Distrinct is========="
								+ district);

						ArrayList officeNameList = revenueReportsBD
								.getOfficeNameDetails(revenueMgmtForm);
						logger.debug("officeNameList-----" + officeNameList);
						revenueMgmtForm.setOfficeNameList(officeNameList);
						request.setAttribute("officenamelst", revenueMgmtForm);
					}
					returnPage = RevenueMgmtConstants.ONLINE_PYMNT_SUCCESS;
				}
			}

			// for revenue Reconciliation
			if (label != null) {
				if (label.equalsIgnoreCase("revRecon")) {
					logger.debug("in revReconRpt action");
					ArrayList districtList = revenueReportsBD
							.getDistrictDetails(revenueMgmtForm);
					revenueMgmtForm.setDistrictList(districtList);
					request.setAttribute("districtlst", revenueMgmtForm);

					if (request.getParameter("myoffice") != null) {
						String districtId = (String) request
								.getParameter("myoffice");
						logger.debug("selected Distrinct is========="
								+ districtId);
						ArrayList officeTypeLst = revenueReportsBD
								.getOfficeTypeDetails(revenueMgmtForm);
						revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
						request.setAttribute("officetypelst", revenueMgmtForm);
						logger.debug("in action officetypelst:-"
								+ revenueMgmtForm.getOfficeTypeLst());
					}
					if (request.getParameter("myoffice") != null) {
						String district = (String) request
								.getParameter("myoffice");
						logger.debug("selected Distrinct is========="
								+ district);

						ArrayList officeNameList = revenueReportsBD
								.getOfficeNameDetails(revenueMgmtForm);
						logger.debug("officeNameList------" + officeNameList);
						revenueMgmtForm.setOfficeNameList(officeNameList);
						request.setAttribute("officenamelst", revenueMgmtForm);
					}
					returnPage = RevenueMgmtConstants.REV_RECON_RPT_SUCCESS;
				}
			}
			// for Service Fee Matrix Create
			if (label != null) {
				if (label.equalsIgnoreCase(
						"serviceFeeCreate")) {
					logger.debug("in serviceFeeCreate RevRpt Action");
					returnPage = RevenueMgmtConstants.SERVICE_FEE_CREATE;
				}
			}
			// for Service Fee Matrix View
			if (label != null) {
				if (label.equalsIgnoreCase(
						"serviceFeeView")) {
					logger.debug("in serviceFeeView RevRpt Action");
					returnPage = RevenueMgmtConstants.SERVICE_FEE_SUCCESS;
				}
			}
			if (label != null) {
				if (label.equalsIgnoreCase("viewServiceParam")) {
					logger.debug("in viewServiceParam RevRpt Action");
					ArrayList  paramList = revenueReportsBD.getServiceParamList();
					revenueMgmtDTO.setServiceList(paramList);
					revenueMgmtForm.setRevenueMgmtDTO(revenueMgmtDTO);
					session.setAttribute("serviceParamList", revenueMgmtForm.getRevenueMgmtDTO().getServiceList());

					returnPage = RevenueMgmtConstants.VIEW_SERVICE_PARAM;
				}
			}
			if (label != null) {
				if (label.equalsIgnoreCase("delete")) {
					String deleteService = request.getParameter("deleteService");
					logger.debug("in delete RevRpt Action  "+deleteService);
					StringTokenizer st = new StringTokenizer(deleteService, "*");
					while (st.hasMoreTokens()) {
						boolean boo=revenueReportsBD.deleteService(st.nextToken(),roleId,funId,userId);
					}

					returnPage = RevenueMgmtConstants.SERVICE_PARAM_DELETE;
				}
			}
		}
		logger.debug("you are in action class if part end");
		return mapping.findForward(returnPage);

	}
}
