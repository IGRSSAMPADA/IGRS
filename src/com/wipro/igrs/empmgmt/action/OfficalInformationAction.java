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

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveats.action.CaveatsViewSearchAction;
import com.wipro.igrs.common.EmpmgmtConstant;
import com.wipro.igrs.empmgmt.bd.EmpmgmtViewBD;
import com.wipro.igrs.empmgmt.bd.OfficalInfoBD;
import com.wipro.igrs.empmgmt.dto.DropDownDTO;
import com.wipro.igrs.empmgmt.dto.EmpmgmtUploadDTO;
import com.wipro.igrs.empmgmt.dto.OfficalInfoDTO;
import com.wipro.igrs.empmgmt.dto.ServiceVerificationDTO;
import com.wipro.igrs.empmgmt.form.OfficeForm;
import com.wipro.igrs.empmgmt.rule.EmpMgmtRule;


/**
* 
* OfficalInformationAction.java <br>
* OfficalInformationAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
public class OfficalInformationAction extends BaseAction {
	private Logger logger = (Logger) Logger.getLogger(OfficalInformationAction.class);
	
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
		String FORWARDPAGE = null;
	//	HttpSession session = request.getSession(true);
		String strFilePath = (String) session.getAttribute("FilePath");
		OfficalInfoBD officalBD = null;
		String actionpath = request.getParameter("name");
		String locale="";
		Locale currentLocale;
		if(session.getAttribute("org.apache.struts.action.LOCALE")!=null){
				currentLocale=(Locale)session.getAttribute("org.apache.struts.action.LOCALE");
				locale=currentLocale.toString();
				
			}
		ArrayList<DropDownDTO> offSubList = new ArrayList<DropDownDTO>();
		ArrayList<DropDownDTO> empStatusMasterList = new ArrayList<DropDownDTO>();
		ArrayList<DropDownDTO> cadreMasterList = new ArrayList<DropDownDTO>();
		ArrayList gradelist = null;
		ArrayList cadrelist = null;
		ArrayList officatinglist = null;
		ArrayList repoteelist = null;
		OfficalInfoDTO officalInfo = null;
		ArrayList serviceVerificationList = null;
		ArrayList documentarraylist = null;
		ArrayList errorList = null;
		ArrayList errorServiceList = null;
		OfficalInfoDTO officialDTO = null;
		ArrayList errorlistFileNames = null;
		OfficeForm officeForm = (OfficeForm) form;
		String update = request.getParameter("update");
		/**
		 * Loding offical information page
		 */
		try {

//			if (oForm.getServiceVerificationList() != null) {
//				ArrayList serviceList = oForm.getServiceVerificationList();
//
//				servicelist = new ArrayList();
//				for (int i = 0; i < serviceList.size(); i++) {
//					ServiceVerificationDTO servicedto = (ServiceVerificationDTO) serviceList
//							.get(i);
//					if ((servicedto != null && servicedto
//							.getVerifyingAuthority() == null)
//							|| (servicedto != null && servicedto
//									.getVerifyingAuthority().equals(""))) {
//					} else {
//						servicelist.add(servicedto);
//					}
//
//				}
//
//				session.setAttribute("serviceList", servicelist);
//			}

			if (actionpath != null && actionpath.equals("official")) {
				officalBD = new OfficalInfoBD();
				empStatusMasterList = officalBD.getEmpStatusMasterList(locale);
				gradelist = officalBD.getAllgradeList(locale);
				offSubList = officalBD.getOfficatingSubstantingList(locale);
				cadreMasterList = officalBD.getAllCadres(locale);
				officeForm.setOfficalInfoDTO(new OfficalInfoDTO());
				officialDTO = officeForm.getOfficalInfoDTO();
				//officialDTO.setDateFirstGovtService("");
			//officialDTO.setEmpStatusMasterList(null);
				
				
				officialDTO.setEmpStatusMasterList(empStatusMasterList);
				officialDTO.setOffSubList(offSubList);
				officialDTO.setGradeMasterList(gradelist);
				officialDTO.setCadreMasterList(cadreMasterList);
				
				session.setAttribute("gradelist", gradelist);
				session.removeAttribute("officialupdate");

				if (update != null) {
					session.setAttribute("officialupdate", update);
				}
				session.removeAttribute("cadrelist");
				session.removeAttribute("officalInfoDTO");
				session.removeAttribute("ReportingDTO");
				session.removeAttribute("attachment1");
				session.removeAttribute("repoteelist");
				ArrayList officeList = null;
				ArrayList reportList = null;
				ArrayList documentList = null;
				ArrayList listFileNames = null;
				if (session.getAttribute("viewemployeeId") != null) {
					EmpmgmtViewBD employeeViewBD = null;
					employeeViewBD = new EmpmgmtViewBD();
					String employeeid=(String)session.getAttribute("viewemployeeId");
					officeList = employeeViewBD.getOfficialDetails(employeeid,locale);
					if(officeList != null && officeList.size()>0) {
					
						officialDTO = (OfficalInfoDTO) officeList.get(0);
						officeForm.setOfficalInfoDTO(officialDTO);
						officialDTO = officeForm.getOfficalInfoDTO();
						officialDTO.setOffSubList(offSubList);
						officialDTO.setGradeMasterList(gradelist);
						cadrelist = officalBD.getCadresForGrade(officialDTO.getClass1(),locale);
						officialDTO.setCadreMapList(cadrelist);
						empStatusMasterList = officalBD.getEmpStatusMasterList(locale);
						officialDTO.setEmpStatusMasterList(empStatusMasterList);
						cadreMasterList = officalBD.getAllCadres(locale);
						officialDTO.setCadreMasterList(cadreMasterList);
						session.setAttribute("cadrelist", cadrelist);
						officatinglist = officalBD.getAllOfficating(officialDTO.getDesignation());
						session.setAttribute("officatinglist", officatinglist);
						repoteelist = officalBD.getReportingHirachy(officialDTO.getDesignation());
						session.setAttribute("repoteelist", repoteelist);
						OfficalInfoDTO reportDTO = officalBD
								.getRepoteeList(officialDTO.getSupervisorID());
						session.setAttribute("ReportingDTO", reportDTO);
						serviceVerificationList = employeeViewBD.getServiceDetails(employeeid);
						//officeForm.getOfficalInfoDTO().setDesignation(officialDTO.getDesignationText());
						//officeForm.getOfficalInfoDTO().setDesiOffic(officialDTO.getDesiOffictText());
//						if(servicelist.size()==0) {
//							//
//							ServiceVerificationDTO temp = new ServiceVerificationDTO();
//							servicelist.add(temp);
//						}
						officeForm.setServiceVerificationList(serviceVerificationList);
						session.setAttribute("serviceVerificationList", serviceVerificationList);
						
						listFileNames = officalBD.getDocumentDetails(employeeid,locale);
						session.setAttribute("attachment1",listFileNames);
						
					}
					reportList = repoteelist;
//					servicelist = employeeViewBD.getServiceDetails(employeeid);
					documentList = listFileNames;
				}

				if (officeList != null) {
					session.setAttribute("officeList", officeList);
					
				}
				if (reportList != null) {
					session.setAttribute("reportList", reportList);
				}
				if (serviceVerificationList != null) {
					session.setAttribute("serviceVerificationList", serviceVerificationList);
				}
				if (documentList != null) {
					session.setAttribute("documentList", documentList);
				}
				session.setAttribute("officeForm", officeForm);
				FORWARDPAGE = "official";

			} else if (officeForm.getActionType() != null
					&& officeForm.getActionType().equals("loadCadre")) {
				try {
					officalBD = new OfficalInfoBD();
					officialDTO = officeForm.getOfficalInfoDTO();
					String grade = request.getParameter("grade");
					cadrelist = officalBD.getCadresForGrade(grade,locale);
					officialDTO.setCadreMapList(cadrelist);
					session.setAttribute("cadrelist", cadrelist);
//					session.removeAttribute("attachemnt1");

					FORWARDPAGE = "official";
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
			} else if (officeForm.getActionType() != null
					&& officeForm.getActionType().equals("loadofficiating")) {
				try {
					officalBD = new OfficalInfoBD();
					session.removeAttribute("ReportingDTO");
					String cadre = request.getParameter("cadre");
					officatinglist = officalBD.getAllOfficating(cadre);
					session.setAttribute("officatinglist", officatinglist);
					repoteelist = officalBD.getReportingHirachy(cadre);
					session.setAttribute("repoteelist", repoteelist);
					FORWARDPAGE = "official";
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
			} else if (officeForm.getActionType() != null
					&& officeForm.getActionType().equals("ReportingHirachy")) {
				try {
					officalBD = new OfficalInfoBD();
					if (request.getParameter("empid") != null) {
						String empid = request.getParameter("empid");
						OfficalInfoDTO officalInfoDTO = officalBD
								.getRepoteeList(empid);
						session.setAttribute("ReportingDTO", officalInfoDTO);
						
					}
					FORWARDPAGE = "official";
				} catch (Exception e) {
					logger.error(e.getMessage(),e);

				}

			} else if (officeForm.getActionType() != null
					&& officeForm.getActionType().equals("addServiceRow")) { 
				try {
					serviceVerificationList = (ArrayList) session.getAttribute("serviceVerificationList");
					try {
						if(serviceVerificationList == null) {
							serviceVerificationList = new ArrayList();
						}
						if (serviceVerificationList.size() > 0) {
							ServiceVerificationDTO prev = (ServiceVerificationDTO) serviceVerificationList
									.get(serviceVerificationList.size() - 1);
							prev.setVerifyingAuthority(officeForm.getVerifyName());
							prev.setDateOfVerivication(officeForm.getVerifyDate());
							prev.setComments(officeForm.getVerifyComments());
						}
					} catch (Exception e) {
						logger.error(e.getMessage(),e);
					}
					serviceVerificationList.add(new ServiceVerificationDTO());
					officeForm.setServiceVerificationList(serviceVerificationList);
					session.setAttribute("serviceVerificationList", serviceVerificationList);
					FORWARDPAGE = "official";
				} catch (Exception e) {
					logger.error(e.getMessage(),e);

				}
			} else if (officeForm.getActionType() != null
					&& officeForm.getActionType().equals("delServiceRow")) { 
				try {
					serviceVerificationList = (ArrayList) session.getAttribute("serviceVerificationList");
					try {
						int index = Integer.parseInt(officeForm.getDelServiceIndex());
						serviceVerificationList.remove(index);
					} catch (Exception e) {
						logger.error(e.getMessage(),e);
					}
					officeForm.setServiceVerificationList(serviceVerificationList);
					session.setAttribute("serviceVerificationList", serviceVerificationList);
					FORWARDPAGE = "official";
				} catch (Exception e) {
					logger.error(e.getMessage(),e);

				}
			}
			else if (officeForm.getActionType() != null
					&& officeForm.getActionType().equals("returnattachment")) { 
				try {
					officialDTO = officeForm.getOfficalInfoDTO();
					ArrayList listFileNames = null;
					if (session.getAttribute("attachment1") != null) {
						listFileNames = (ArrayList) session
								.getAttribute("attachment1");
					}
					session.setAttribute("attachment1", listFileNames);
					FORWARDPAGE = "official";
				} catch (Exception e) {
					logger.error(e.getMessage(),e);

				}
			}
			
			else if (officeForm.getActionType() != null
					&& officeForm.getActionType().equals("downloadDoc")) { 
				try {
					officialDTO = officeForm.getOfficalInfoDTO();
					ArrayList listFileNames = null;
					if (session.getAttribute("attachment1") != null) {
						listFileNames = (ArrayList) session
								.getAttribute("attachment1");
						String attachIndex = request.getParameter("attachIndex");
						int index = Integer.parseInt(attachIndex);
						Object item = listFileNames.get(index);
						EmpmgmtUploadDTO uploadedItem = (EmpmgmtUploadDTO) item;
						CaveatsViewSearchAction.downloadDocument(response, uploadedItem.getFileContents(), uploadedItem.getFileName());
					}
					session.setAttribute("attachment1", listFileNames);
					FORWARDPAGE = "official";
				} catch (Exception e) {
					logger.error(e.getMessage(),e);

				}
			}
			
			else if (officeForm.getActionType() != null
					&& officeForm.getActionType().equals("submitOffice")) {

				try {
					ArrayList listFileNames = null;
					if (session.getAttribute("attachment1") != null) {
						listFileNames = (ArrayList) session
								.getAttribute("attachment1");
						String userid = (String) session
						.getAttribute("employeeId");
					}
					officalBD = new OfficalInfoBD();

					OfficalInfoDTO officalInfoDTO = officeForm.getOfficalInfoDTO();
					documentarraylist = officeForm.getDocumentList();
					empMgmtRule = new EmpMgmtRule();
					officalInfo = officeForm.getOfficalInfoDTO();
					serviceVerificationList = officeForm
							.getServiceVerificationList();
					errorList = empMgmtRule.validateOfficalInfoDTORule(
							officalInfo, listFileNames, officalBD);
//					errorServiceList = empMgmtRule
//							.validateServiceList(serviceVerificationList);
					session.setAttribute("serviceList", serviceVerificationList);

					session.setAttribute("attachment1", listFileNames);
//					if (errorServiceList != null) {
//						for (int i = 0; i < errorServiceList.size(); i++) {
//							errorList.add(errorServiceList.get(i));
//						}
//						
//					}
//					if (errorlistFileNames != null) {
//							for (int j = 0; j < errorlistFileNames.size(); j++) {
//								errorList.add(errorlistFileNames.get(j));
//							}
//						}
					if (empMgmtRule.isError()) {
						request.setAttribute(
								EmpmgmtConstant.EMP_MGMT_ERROR_FLAG, "true");
						request.setAttribute(
								EmpmgmtConstant.EMP_MGMT_ERROR_LIST, errorList);
						FORWARDPAGE = "official";
					} else {
						request.setAttribute(
								EmpmgmtConstant.EMP_MGMT_ERROR_FLAG, "false");
						request.setAttribute(
								EmpmgmtConstant.EMP_MGMT_ERROR_LIST, null);
						String strEmployeeId = (String) session
								.getAttribute("employeeId");
						String strUserId = (String) session
								.getAttribute("UserId");
						String userid = (String) session
								.getAttribute("employeeId");
						if(strFilePath!=null && strEmployeeId!=null){
						 File folder = new File(strFilePath.substring(0, strFilePath.length()-2).concat("\\").concat(strEmployeeId.concat("\\")));
						 File folder1 = new File(strFilePath);
						 folder1.renameTo(folder);
						}
						OfficalInfoDTO officialDto = officeForm.getOfficalInfoDTO();
						String docType = officialDto.getDocumenttype();
						boolean submitOfficalDetails = officalBD
								.submitOfficalInfo(officalInfoDTO, serviceVerificationList,
										strUserId, strEmployeeId,
										listFileNames, strFilePath, docType,
										userid,locale);
						if (submitOfficalDetails
								&& session.getAttribute("officialupdate") == null) {
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
							} else if (!list.contains("TALENT")) {
								FORWARDPAGE = "talent";
							} else {
								FORWARDPAGE = "success";
							}
							list.add("OFFICE");
							

							session.setAttribute("tablist", list);
							if(locale.equalsIgnoreCase("hi_IN")){
								request
								.setAttribute("success",
										"<font color=green>कार्यालय की सूचना सफलतापूर्वक प्रस्तुत की गई है </font>");	
							}else{
								request
								.setAttribute("success",
										"<font color=green>Office Information submitted successfully!</font>");	
							}
							
							session.removeAttribute("attachment1");
							session.removeAttribute("FilePath");

						} else if (session.getAttribute("officialupdate") != null
								&& session.getAttribute("officialupdate")
										.toString().equals("true")) {
							ArrayList list = null;
							if (session.getAttribute("tablist") != null) {
								list = (ArrayList) session
										.getAttribute("tablist");
							} else {
								list = new ArrayList();
							}
							if (!list.contains("PROPERTY")) {
								request.setAttribute("update", "updatesuccess");
								FORWARDPAGE = "property";
							} else if (!list.contains("BANK")) {
								FORWARDPAGE = "bank";
							} else if (!list.contains("TALENT")) {
								FORWARDPAGE = "talent";
							} else {
								FORWARDPAGE = "success";
							}
							list.add("OFFICE");
							session.setAttribute("tablist", list);
							if(locale.equalsIgnoreCase("hi_IN")){
								request
								.setAttribute("success",
										"<font color=red>कार्यालय की सूचना सफलतापूर्वक प्रस्तुत की गई है </font>");	
							}else{
								request
								.setAttribute("success",
										"<font color=red>Office Information submitted successfully!</font>");	
							}
							
							session.removeAttribute("attachment1");
							session.removeAttribute("FilePath");
							FORWARDPAGE = "viewpersonal";
						} else {
							if(locale.equalsIgnoreCase("hi_IN")){
								request
								.setAttribute("failure",
										"<font color=red>कार्यालय की सूचना सफलतापूर्वक प्रस्तुत नही हो पाई है </font>");	
							}else{
								request
								.setAttribute("failure",
										"<font color=red>Office Information not submitted successfully!</font>");	
							}
							

							FORWARDPAGE = "official";
						}

					}
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

		return mapping.findForward(FORWARDPAGE);
	}

}