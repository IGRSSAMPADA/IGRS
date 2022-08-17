/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI           xxxxx              
 * --------------------------------------------------------------------------------
*/
package com.wipro.igrs.caseMonitoring.action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caseMonitoring.bd.CaseMonitoringBD;
import com.wipro.igrs.caseMonitoring.constant.CommonConstant;
import com.wipro.igrs.caseMonitoring.dto.CaseMonitoringDTO;
import com.wipro.igrs.caseMonitoring.dto.PartyDetailsDTO;
import com.wipro.igrs.caseMonitoring.form.CaseMonitoringForm;
import com.wipro.igrs.common.dto.CommonDTO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.util.PropertiesFileReader;

public class CaseMonitoringAction extends BaseAction {
	private static Logger logger = org.apache.log4j.Logger.getLogger(CaseMonitoringAction.class);

	String FORWARD_JSP = new String("case");
	HashMap map = new HashMap();
	CaseMonitoringDTO dto = null;
	String caseType = "";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		logger.info("you are in action class intial step " + form);
		CaseMonitoringForm caseForm = (CaseMonitoringForm) form;
		//addition by satbir kumar
		CaseMonitoringDTO caseDTO = caseForm.getCaseDTO();
		//end of addition
		logger.info("you are in action class if part");
		CaseMonitoringBD bd = new CaseMonitoringBD();
		String roleId = (String) session.getAttribute("role");
		String funId = (String) session.getAttribute("functionId");
		String userId = (String) session.getAttribute("UserId");
		String offId= (String) session.getAttribute("loggedToOffice");
		//ADDED BY SHRUTI	-5TH FEB 2014
		String language=(String)session.getAttribute("languageLocale");
		caseForm.setLanguage(language);
		//end
		
		
		if(language.equalsIgnoreCase("en")){
			session.setAttribute("modName","Case Monitoring");
			session.setAttribute("fnName","Create");
		}else{
			session.setAttribute("modName","प्रकरण मॉनिटरिंग");
			session.setAttribute("fnName","बनायें");
		}
		

		//dto.setCurrentOffId(offId);
		ArrayList caseregdetails = bd.getCaseRegDet(userId,offId,language);
		if (caseregdetails != null) {
			for (int i = 0; i < caseregdetails.size(); i++) 
			{
				CaseMonitoringDTO dto = (CaseMonitoringDTO) caseregdetails.get(i);
				caseForm.setDistrictName(dto.getDistrictName());
				caseForm.setCaseregdate(dto.getCaseregdate());
			}
		}
		// CaseCreate Process - EStampSubType (Optional or Mandatory)
		String option = request.getParameter("optional");
		if (option != null)
		{
			if (option.equals("true")) 
			{
				dto = caseForm.getCaseDTO();
				String radioVal = request.getParameter("radioVal");
				dto.setEstampType(radioVal);
				caseForm = (CaseMonitoringForm) form;
				String from = "1";
				String to = "5";
				int diff = (Integer.parseInt(to) - Integer.parseInt(from)) + 1;
				String dif = String.valueOf(diff);
				logger.info("AGMP Different = " + dif);
				request.setAttribute("dif", dif);
				
				String source = dto.getReportId();
				caseType = request.getParameter("caseType");
				dto.setCaseType(caseType);
				String caseId = request.getParameter("case_ID");
				dto.setCaseId(caseId);
				String reportid = request.getParameter("report_ID");// report_ID
				dto.setReportId(reportid);
				if (caseType.equalsIgnoreCase("CASET_007"))
				{
					reportid = request.getParameter("complaintId");
				}
				String fdate = request.getParameter("fromDATE1");// fromDATE
				dto.setDurationFrom(fdate);
				String tdate = request.getParameter("toDATE1");// toDATE
				dto.setDurationTo(tdate);
				logger.info("I am in reportLIst action================");
				ArrayList reportlist = bd.getRefundList(caseType, reportid, fdate, tdate, from, to,radioVal,language);
				request.setAttribute("from", from);
				request.setAttribute("to", to);
				request.setAttribute("caseType", caseType);
				request.setAttribute("reportid", reportid);
				request.setAttribute("fdate", fdate);
				request.setAttribute("tdate", tdate);
				request.setAttribute("reportlist", reportlist);
				caseForm.setReportlist(reportlist);
				caseForm.setCaseDTO(dto);
				session.setAttribute("caseForm", caseForm);
				FORWARD_JSP = CommonConstant.IGRS_CREATE_CASE_ESTAMP_REFUND;
			}
		}

		String refundpagination = request.getParameter("refundpaging");
		if (refundpagination != null)
		{
			if (refundpagination.equals("true")) 
			{
				String radioVal = request.getParameter("radioVal");
				caseForm = (CaseMonitoringForm) form;
				String from = "1";
				String to = "5";
				int diff = (Integer.parseInt(to) - Integer.parseInt(from)) + 1;
				String dif = String.valueOf(diff);
				logger.info("AGMP Different = " + dif);
				request.setAttribute("dif", dif);
				dto = caseForm.getCaseDTO();
				String source = dto.getReportId();
				caseType =dto.getCaseType();
				String caseId =dto.getCaseId();
				String reportid =dto.getReportId();
				if (caseType.equalsIgnoreCase("CASET_007"))
				{
					reportid = request.getParameter("complaintId");
				}
				String fdate = dto.getDurationFrom();
				String tdate = dto.getDurationTo();
				logger.info("I am in reportLIst action================");
				ArrayList reportlist = bd.getRefundList(caseType, reportid, fdate, tdate, from, to,radioVal,language);
				request.setAttribute("from", from);
				request.setAttribute("to", to);
				request.setAttribute("caseType", caseType);
				request.setAttribute("reportid", reportid);
				request.setAttribute("fdate", fdate);
				request.setAttribute("tdate", tdate);
				request.setAttribute("reportlist", reportlist);
				caseForm.setReportlist(reportlist);
				caseForm.setCaseDTO(dto);
				session.setAttribute("caseForm", caseForm);
				FORWARD_JSP = CommonConstant.IGRS_CREATE_CASE_ESTAMP_REFUND;
			}
		}

		String estampno = request.getParameter("estampno");
		if (estampno != null) 
		{
			if (estampno.equals("true")) 
			{
				logger.info("Inside E-Stamp Find Popup");
				CaseMonitoringForm loFormone = (CaseMonitoringForm) session.getAttribute("caseForm");
				request.setAttribute("caseForm", loFormone);
				logger.info("Popup Search Form..!!");
				FORWARD_JSP = CommonConstant.IGRS_CASE_ESTAMP;
			}
		}

		String refundfee = request.getParameter("refund");
		if (refundfee != null)
		{
			caseForm.setRefundfeeflag("RF");
			if (refundfee.equalsIgnoreCase("true")) 
			{
				CaseMonitoringDTO dto1 = caseForm.getCaseDTO();
				dto1.setStampsubType(request.getParameter("radioOptMan"));
				dto1.setDrLastCmt("");//added by roopam-23feb2015-bug 2781
				dto1.setPartyName("");//added by roopam-23feb2015-bug 2781
				dto1.setPartyAddress("");//added by roopam-23feb2015-bug 2781
				
				String subtype = dto1.getStampsubType();
				logger.debug("SubType = " + subtype);
				ArrayList revList = bd.getRevheadList();
				ArrayList secList = bd.getSecheadList();
				caseForm.setRevenueList(revList);
				caseForm.setSectionList(secList);
				logger.info("Values are loaded from the database.");
				request.setAttribute("caseForm", caseForm);
				FORWARD_JSP = CommonConstant.IGRS_CASE_REG_FEE_REFUND;
			}
		}

		String view = request.getParameter("view");
		if (view != null) 
		{
			if (view.equals("true")) 
			{
				logger.info("View Form..");
				FORWARD_JSP = CommonConstant.IGRS_CASE_VIEW_BACK;
			}
		}
		// previous code for reg fee case generation
		if (CommonConstant.IGRS_CASE_SUBMIT.equalsIgnoreCase(caseForm.getCaseDTO().getCaseSubmit())) 
		{
			logger.info("Inside the submit page.......");
			CaseMonitoringForm csform = (CaseMonitoringForm) session.getAttribute("caseForm");
			logger.debug("CASE  TYPE~~~~~~~~~~~"+ request.getSession().getAttribute("caseType"));
			logger.debug("CASE HEAD ID~~~~~~~~~"+ request.getSession().getAttribute("caseHead"));
			CaseMonitoringDTO dtos = caseForm.getCaseDTO();
			dtos.setCaseHead((String) request.getSession().getAttribute("caseHead"));
			dtos.setCaseType((String) request.getSession().getAttribute("caseType"));
			if ((caseForm.getCaseDTO().getRule() != null)) 
			{
				if ((caseForm.getCaseDTO().getRule().equalsIgnoreCase("on"))) 
				{
					dtos.setRule("Y");
				}
			}
			else 
			{
				dtos.setRule("N");
			}
			String caseid = bd.insertcaseoptional(dtos, roleId, funId, userId);
			request.setAttribute("caseid", caseid);
			FORWARD_JSP = CommonConstant.IGRS_ESTAMP_CASE_SUBMIT;
		}
		if (CommonConstant.BACK.equalsIgnoreCase(caseForm.getCaseDTO().getBackbutton())) 
		{
			FORWARD_JSP = CommonConstant.PREVIOUS_PAGE;
		}
		if (CommonConstant.NOTICE_TWO_BACK.equalsIgnoreCase(caseForm.getCaseDTO().getBackbutton())) 
		{
			logger.info("Inside back");
			FORWARD_JSP = CommonConstant.NOTICE;
		}
		String formName = caseForm.getFormName();
		String actionName = caseForm.getActionName();
		// Attachment start for revenue
		if (formName != null)
		{
			if ("Attach".equals(formName)) 
			{
				CaseMonitoringDTO dto1 = caseForm.getCaseDTO();
				if ("attach".equals(actionName)) 
				{
					session.removeAttribute("mapFile");
					logger.info("attach");
					FormFile fileForm = dto1.getFileUpload();
					String fileName = fileForm.getFileName();
					String filePath = dto1.getFilePath();
					dto1.setFileByte(fileForm.getFileData());
					dto1.setFileName(fileName);
					if (!"".equals(fileName) && !"".equals(filePath))
					{
						map.put(fileName, dto1);
					}
					dto1.setFileDetails(map);
					caseForm.setCaseDTO(dto1);
					session.setAttribute("mapFile", map);
					FORWARD_JSP = CommonConstant.ATTACHMENT;
				}
				if ("remove".equals(actionName)) 
				{
					String[] floorID = dto1.getHdnFileName().split(",");
					for (int i = 0; i < floorID.length; i++) 
					{
						map.remove(floorID[i]);
					}
					dto1.setFileDetails(map);
					caseForm.setCaseDTO(dto1);
					session.setAttribute("mapFile", map);
				}
				if ("close".equals(actionName)) 
				{
					HashMap mapFile = (HashMap) session.getAttribute("mapFile");
					dto1.setFileDetails(mapFile);
					caseForm.setCaseDTO(dto1);
					session.setAttribute("mapFile", mapFile);
					FORWARD_JSP = CommonConstant.IGRS_REVENUE_DETAIL_REPORT;	
				}
			}

		}
		// End Attachment
		String viewAttachment = request.getParameter("viewAttachment");
		if (viewAttachment != null) 
		{
			if (viewAttachment.equals("viewAttach")) 
			{
				FORWARD_JSP = CommonConstant.VIEW_ATTACHMENT;
			}
		}
		String previous = (String) request.getParameter("previous");
		if (previous != null) 
		{
			if (previous.equalsIgnoreCase("INITIATE"))
			{
				logger.info("********* inside INITIATE lastAction ");
				CaseMonitoringDTO dto1 = caseForm.getCaseDTO();
				String caseId = dto1.getRadioOne();
				dto1 = bd.getInitiate(caseId);
				caseForm.setCaseDTO(dto1);
				request.setAttribute("caseForm", caseForm);
				String caseid = (String) caseForm.getCaseDTO().getCaseId();
				if (caseid == null)
				{
					FORWARD_JSP = CommonConstant.ERROR_PAGE;
				} 
				else
					FORWARD_JSP = CommonConstant.NOTICE_PAGE;
			}
			if (previous.equalsIgnoreCase("NOTICE"))
			{
				logger.info("********* inside NOTICE lastAction ");
				CaseMonitoringDTO dto1 = caseForm.getCaseDTO();
				String caseId = dto1.getRadioOne();
				dto1 = bd.getNoticeDetail(caseId);
				caseForm.setCaseDTO(dto1);
				request.setAttribute("caseForm", caseForm);
				String caseid = (String) caseForm.getCaseDTO().getCaseId();
				if (caseid == null) 
				{
					FORWARD_JSP = CommonConstant.ERROR_PAGE;
				}
				else
					FORWARD_JSP = CommonConstant.IGRS_CASE_NOTICE_DETAIL_BY_DR;
			}

			if (previous.equalsIgnoreCase("ORDER"))
			{
				logger.info("********* inside ORDER lastAction ");
				CaseMonitoringDTO dto1 = caseForm.getCaseDTO();
				String caseId = dto1.getRadioOne();
				CaseMonitoringDTO dto = bd.getOrder(caseId);
				dto.setCaseId(caseId);
				caseForm.setCaseDTO(dto);
				request.setAttribute("cForm", caseForm);
				String caseid = (String) caseForm.getCaseDTO().getCaseId();
				if (caseid == null) 
				{
					FORWARD_JSP = CommonConstant.ERROR_PAGE;
				} 
				else
					FORWARD_JSP = CommonConstant.ORDER_PAGE;
			}
		}

		String notice = (String) request.getParameter("notice");
		CaseMonitoringDTO dto1 = caseForm.getCaseDTO();
		if (CommonConstant.CASE_MONITOR_VIEW.equalsIgnoreCase(request.getParameter("caseView")))
		{
			logger.info("Actions are matched for CASE_MONITOR_VIEW Details");
			String from = "1";
			String to = "5";
			int diff = (Integer.parseInt(to) - Integer.parseInt(from)) + 1;
			String dif = String.valueOf(diff);
			request.setAttribute("dif", dif);
			CaseMonitoringDTO dto = caseForm.getCaseDTO();
			String caseHead = request.getParameter("caseMonDTO.caseHeadName");// dto.getCaseHead();
			String caseType1 = request.getParameter("caseMonDTO.caseTypeName");// dto.getCaseType1();
			String caseType2 = request.getParameter("caseMonDTO.caseTypeName");// dto.getCaseType2();
			String caseType3 = request.getParameter("caseMonDTO.caseTypeName");// dto.getCaseType3();
			String estampType = request.getParameter("radioJudi");// dto.getEstampType();
			String estampSubType = request.getParameter("radioOptMan");// dto.getEstampSubType();
			String report = request.getParameter("caseMonDTO.caseCriteria");// dto.getReport();
			String caseId = request.getParameter("caseMonDTO.caseTxnId");// dto.getCaseId();
			String fromDate = request.getParameter("caseMonDTO.fromDateSearch");// dto.getFromDate();
			String toDate = request.getParameter("caseMonDTO.toDateSearch");// dto.getToDate();
			String status = request.getParameter("caseMonDTO.caseStatus");// dto.getStatus();
			//added by shruti---4 july 2014
			caseDTO.setCaseHead(caseHead);
			caseDTO.setCaseType(caseType1);
			caseDTO.setEstampType(estampType);
			//end
			
			ArrayList list = bd.getCaseView(caseHead, caseType1, caseType2,
					caseType3, estampType, estampSubType, caseId, fromDate,
					toDate, status, from, to);
			if (list == null)
			{
				// dto.setErrorMsg("No Data Found in Search Result");
			} 
			else
			{
				// dto.setErrorMsg("");
			}
			request.setAttribute("from", from);
			request.setAttribute("to", to);
			session.setAttribute("caseForm", caseForm);
			session.setAttribute("caseId", caseId);
			session.setAttribute("fromDate", fromDate);
			session.setAttribute("toDate", toDate);
			session.setAttribute("status", status);
			session.setAttribute("report", report);
			session.setAttribute("list", list);
			FORWARD_JSP = CommonConstant.IGRS_CASE_VIEW_LIST;
		}
		if (CommonConstant.BACK.equalsIgnoreCase(caseForm.getCaseDTO().getBackView()))
		{
			FORWARD_JSP = CommonConstant.IGRS_CASE_VIEW_BACK;
		}

		if (CommonConstant.BACK_ONE.equalsIgnoreCase(caseForm.getCaseDTO().getBackView()))
		{
			logger.info("B A C K");
			FORWARD_JSP = CommonConstant.IGRS_CASE_VIEW_LIST;
		}

		String details = (String) request.getParameter("details");
		if (details != null)
		{
			logger.info("*** preaction DETAILS : " + details);
			if (details.equals("detail"))
			{
				//######added by satbir kumar#######//
				
				String caseId = request.getParameter("caseId");
				caseForm.getCaseDTO().setCaseDetailsList(bd.getcaseDetailsList(language,caseId,caseDTO));
				caseForm.getCaseDTO().setOtherDetailsList(bd.getotherDetailsList(language,caseId,caseDTO));
				if(caseDTO.getCaseHead()!=null && "CH_001".equalsIgnoreCase(caseDTO.getCaseHead()))
				{
					FORWARD_JSP = CommonConstant.IGRS_CASE_NEW_VIEW_DETAILS;
				}
				else if(caseDTO.getCaseHead()!=null && "CH_002".equalsIgnoreCase(caseDTO.getCaseHead()))
				{
					FORWARD_JSP = CommonConstant.IGRS_CASE_REFUND_VIEW_DETAILS;
				}
				else if(caseDTO.getCaseHead()!=null && "CH_003".equalsIgnoreCase(caseDTO.getCaseHead()))
				{
					FORWARD_JSP = CommonConstant.IGRS_CASE_OTHERS_VIEW_DETAILS;
				}
				else
				{
				FORWARD_JSP = CommonConstant.IGRS_CASE_NEW_VIEW_DETAILS;
				}
				/*if(caseDTO.getCaseHead()!=null)
				{
					if("CH_001".equalsIgnoreCase(caseDTO.getCaseHead()))
					{
						caseForm.getCaseDTO().setCaseDetailsList(bd.getcaseDetailsList(language,caseId,caseDTO));
						caseForm.getCaseDTO().setOtherDetailsList(bd.getotherDetailsList(language,caseId,caseDTO));
					}
					else if("CH_002".equalsIgnoreCase(caseDTO.getCaseHead()))
					{}
				}*/
				
				
				/*String lastActionID = request.getParameter("lastAction");
				String report = (String) session.getAttribute("report");
				String fromDate = (String) session.getAttribute("fromDate");
				String toDate = (String) session.getAttribute("toDate");
				logger.info("fromDate" + fromDate);
				logger.info("toDate" + toDate);
				String status = (String) request.getParameter("status");
				String preaction = (String) request.getParameter("preaction");
				logger.info("STATUS    : ---" + status);
				logger.info("*********lastAction: " + preaction);
*/
				
				
				
				
				
				
				
				
				/*
				
				if ("CASE_000".equalsIgnoreCase(lastActionID))
				{
					dto1 = bd.getCaseDetail(caseId, lastActionID);
					request.setAttribute("initiate", dto1);
				}
				if ("CASE_001".equalsIgnoreCase(lastActionID)) 
				{
					// added by shruti-for view page at notice stage
					dto1 = bd.getCaseDetail(caseId, lastActionID);
					request.setAttribute("initiate", dto1);
					// end
					dto1 = bd.getViewNotice(caseId, lastActionID);
					request.setAttribute("viewnotice", dto1);
				}
				if ("CASE_002".equals(lastActionID))
				{
					dto1 = bd.getCaseDetail(caseId, lastActionID);
					request.setAttribute("initiate", dto1);
					dto1 = bd.getViewOrder(caseId, lastActionID);
					request.setAttribute("order", dto1);
				}
				if ("CASE_003".equals(lastActionID))
				{
					dto1 = bd.getCaseDetail(caseId, lastActionID);
					request.setAttribute("initiate", dto1);
					dto1 = bd.getExPartyOrder(caseId, lastActionID);
					request.setAttribute("expartyorder", dto1);
				}
				if ("CASE_004".equals(lastActionID))
				{
					dto1 = bd.getCaseDetail(caseId, lastActionID);
					request.setAttribute("initiate", dto1);
					dto1 = bd.getViewDemandNotice(caseId, lastActionID);
					request.setAttribute("DemandNotice", dto1);
				}
				if ("CASE_005".equals(lastActionID))
				{
					dto1 = bd.getCaseDetail(caseId, lastActionID);
					request.setAttribute("initiate", dto1);
					dto1 = bd.getAttachOrder(caseId, lastActionID);
					request.setAttribute("attachOrder", dto1);
				}
				if ("CASE_006".equals(lastActionID))
				{
					dto1 = bd.getCaseDetail(caseId, lastActionID);
					request.setAttribute("initiate", dto1);
					dto1 = bd.getAuctionOrder(caseId, lastActionID);
					request.setAttribute("auctionOrder", dto1);
				}
				if ("CASE_007".equals(lastActionID))
				{
					dto1 = bd.getCaseDetail(caseId, lastActionID);
					request.setAttribute("initiate", dto1);
					dto1 = bd.getRevenueCommission(caseId, lastActionID);
					request.setAttribute("revenueCommission", dto1);
				}
				if ("CASE_008".equals(lastActionID))
				{
					dto1 = bd.getCaseDetail(caseId, lastActionID);
					request.setAttribute("initiate", dto1);
					dto1 = bd.getRevenueBoard(caseId, lastActionID);
					request.setAttribute("revenueBoard", dto1);
				}
				if ("CASE_009".equals(lastActionID))
				{
					dto1 = bd.getCaseDetail(caseId, lastActionID);
					request.setAttribute("initiate", dto1);
					dto1 = bd.getHighCourt(caseId, lastActionID);
					request.setAttribute("highCourt", dto1);
				}
				if ("CASE_010".equals(lastActionID))
				{
					dto1 = bd.getCaseDetail(caseId, lastActionID);
					request.setAttribute("initiate", dto1);
					dto1 = bd.getSupremeCourt(caseId, lastActionID);
					request.setAttribute("supremeCourt", dto1);
				}
				if (status.equalsIgnoreCase("close"))
				{
					logger.info("Inside case close **************");
					dto1 = bd.getCloseState(caseId, lastActionID);
					request.setAttribute("stateClose", dto1);
					request.setAttribute("status", status);
				}
				session.setAttribute("preaction", lastActionID);
				session.setAttribute("caseId", caseId);
				session.setAttribute("fromDate", fromDate);
				session.setAttribute("toDate", toDate);
				session.setAttribute("report", report);*/
				
			}
		}
		if (CommonConstant.CMT_LIST.equalsIgnoreCase(caseForm.getCaseDTO().getBackView()))
		{
			FORWARD_JSP = CommonConstant.ADD_COMMENT;
		}

		if (CommonConstant.ADD_CMT_BACK.equalsIgnoreCase(caseForm.getCaseDTO().getBackbutton()))
		{
			logger.info("Entered into ADD_CMT_BACK ");
			FORWARD_JSP = CommonConstant.COMMENT_LIST;
		}

		if (CommonConstant.CMT_UPDATE.equalsIgnoreCase(caseForm.getCaseDTO().getUploadFile())) 
		{
			logger.info("Compliance updated***");
			CaseMonitoringDTO cmdto = caseForm.getCaseDTO();
			String caseId = (String) session.getAttribute("caseId");
			boolean cmt_update = bd.getCmtUpdate(cmdto, caseId);
			FORWARD_JSP = CommonConstant.CMT_UPDATED;
		}
		// Revenue Module - Case Create - AGMP Receipt Audit
		String agmptype = (String) request.getParameter("agmp");
		if (agmptype != null) 
		{
			if (agmptype.equals("true")) 
			{
				logger.info("Create Case Based on Audit Reports");
				caseForm = (CaseMonitoringForm) form;
				String from = "1";
				String to = "5";
				int diff = (Integer.parseInt(to) - Integer.parseInt(from)) + 1;
				String dif = String.valueOf(diff);
				logger.info("AGMP Different = " + dif);
				request.setAttribute("dif", dif);
				dto = caseForm.getCaseDTO();
				String source = dto.getReportId();
				
				caseType = request.getParameter("caseType");
				dto.setCaseType(caseType);
				
				String reportid = request.getParameter("report_ID");// report_ID
				dto.setReportId(reportid);
				
				String fdate = request.getParameter("fromDATE");// fromDATE
				dto.setDurationFrom(fdate);
				
				String tdate = request.getParameter("toDATE");// toDATE
				dto.setDurationTo(tdate);
				
				logger.info("I am in reportLIst action================");
				ArrayList reportlist=new ArrayList();
				if(!caseType.equalsIgnoreCase(null))
				{
					if(!caseType.equalsIgnoreCase("CASET_005"))// AGMP,INTERNAL,PO,POI REPORTS
					{	
						reportlist = bd.getReportList(caseType, reportid,fdate, tdate, from, to);
						request.setAttribute("from", from);
						request.setAttribute("to", to);
						request.setAttribute("caseType", caseType);
						request.setAttribute("reportid", reportid);
						request.setAttribute("fdate", fdate);
						request.setAttribute("tdate", tdate);
						request.setAttribute("reportlist", reportlist);
						caseForm.setReportlist(reportlist);
						caseForm.setCaseDTO(dto);
						session.setAttribute("caseForm", caseForm);
						FORWARD_JSP = CommonConstant.IGRS_REVENUE_CASE_CREATE;
						
					}
					else///SEPARATE DASHBOARD FOR SR IMPOUNDING CASE
					{
						reportlist=bd.getReportListNew(caseType, reportid, fdate, tdate, from, to);
						request.setAttribute("from", from);
						request.setAttribute("to", to);
						request.setAttribute("caseType", caseType);
						request.setAttribute("reportid", reportid);
						request.setAttribute("fdate", fdate);
						request.setAttribute("tdate", tdate);
						request.setAttribute("reportlist", reportlist);
						caseForm.setReportlist(reportlist);
						caseForm.setCaseDTO(dto);
						session.setAttribute("caseForm", caseForm);
						FORWARD_JSP = CommonConstant.IGRS_IMPOUND_CASE_CREATE;
					}
				}	
			}
		}
		String agmppagination = (String) request.getParameter("agmppaging");
		if (agmppagination != null) 
		{
			if (agmppagination.equals("true")) 
			{
				logger.info("Create Case Based on Audit Reports");
				caseForm = (CaseMonitoringForm) form;
				String from = "1";
				String to = "5";
				int diff = (Integer.parseInt(to) - Integer.parseInt(from)) + 1;
				String dif = String.valueOf(diff);
				logger.info("AGMP Different = " + dif);
				request.setAttribute("dif", dif);
				dto = caseForm.getCaseDTO();
				String source = dto.getReportId();
				caseType=dto.getCaseType();
				String reportid = dto.getReportId();// report_ID
				String fdate = dto.getDurationFrom();// fromDATE
				String tdate = dto.getDurationTo();// toDATE
				logger.info("I am in reportLIst action================");
				ArrayList reportlist=new ArrayList();
				if(!caseType.equalsIgnoreCase(null))
				{
					if(!caseType.equalsIgnoreCase("CASET_005"))// AGMP,INTERNAL,PO,POI REPORTS
					{	
						reportlist = bd.getReportList(caseType, reportid,fdate, tdate, from, to);
						request.setAttribute("from", from);
						request.setAttribute("to", to);
						request.setAttribute("caseType", caseType);
						request.setAttribute("reportid", reportid);
						request.setAttribute("fdate", fdate);
						request.setAttribute("tdate", tdate);
						request.setAttribute("reportlist", reportlist);
						caseForm.setReportlist(reportlist);
						caseForm.setCaseDTO(dto);
						session.setAttribute("caseForm", caseForm);
						FORWARD_JSP = CommonConstant.IGRS_REVENUE_CASE_CREATE;
						
					}
					else///SEPARATE DASHBOARD FOR SR IMPOUNDING CASE
					{
						reportlist=bd.getReportListNew(caseType, reportid, fdate, tdate, from, to);
						request.setAttribute("from", from);
						request.setAttribute("to", to);
						request.setAttribute("caseType", caseType);
						request.setAttribute("reportid", reportid);
						request.setAttribute("fdate", fdate);
						request.setAttribute("tdate", tdate);
						request.setAttribute("reportlist", reportlist);
						caseForm.setReportlist(reportlist);
						caseForm.setCaseDTO(dto);
						session.setAttribute("caseForm", caseForm);
						FORWARD_JSP = CommonConstant.IGRS_IMPOUND_CASE_CREATE;
					}
				}	
			}
		}

		// added by shruti-17th july 2013
		String others = (String) request.getParameter("others");
		if (others != null) {
			if (others.equals("true")) {
				logger.info("Create Case Based on Others Category");
				caseForm = (CaseMonitoringForm) form;
				String from = "1";
				String to = "5";
				int diff = (Integer.parseInt(to) - Integer.parseInt(from)) + 1;
				String dif = String.valueOf(diff);
				logger.info("others Different = " + dif);
				request.setAttribute("dif", dif);
				dto = caseForm.getCaseDTO();
				String source = dto.getReportId();
				caseType = request.getParameter("caseType");
				dto.setCaseType(caseType);
				String reportid = request.getParameter("Report_ID");// report_ID
				dto.setReportId(reportid);
				String fdate = request.getParameter("rFromDate");// fromDATE
				dto.setDurationFrom(fdate);
				String tdate = request.getParameter("rToDate");// toDATE
				dto.setToDate(tdate);
				logger.info("I am in reportLIst action================");
				ArrayList reportlist = bd.getReportListNew(caseType, reportid,fdate, tdate, from, to);
				request.setAttribute("from", from);
				request.setAttribute("to", to);
				request.setAttribute("caseType", caseType);
				request.setAttribute("reportid", reportid);
				request.setAttribute("fdate", fdate);
				request.setAttribute("tdate", tdate);
				request.setAttribute("reportlist", reportlist);
				caseForm.setReportlist(reportlist);
				caseForm.setCaseDTO(dto);
				session.setAttribute("caseForm", caseForm);
			}
			FORWARD_JSP = CommonConstant.IGRS_TIME_BARRED_CASE_DASHBOARD;
		}
		String timebarredpagination = (String) request.getParameter("timebarredpaging");
		if (timebarredpagination != null) {
			if (timebarredpagination.equals("true"))
			{
				logger.info("Create Case Based on Others Category");
				caseForm = (CaseMonitoringForm) form;
				String from = "1";
				String to = "5";
				int diff = (Integer.parseInt(to) - Integer.parseInt(from)) + 1;
				String dif = String.valueOf(diff);
				logger.info("others Different = " + dif);
				request.setAttribute("dif", dif);
				dto = caseForm.getCaseDTO();
				String source = dto.getReportId();
				caseType = dto.getCaseType();
				String reportid = dto.getReportId();// report_ID
				String fdate = dto.getDurationFrom();// fromDATE
				String tdate = dto.getToDate();// toDATE
				
				logger.info("I am in reportLIst action================");
				ArrayList reportlist = bd.getReportListNew(caseType, reportid,fdate, tdate, from, to);
				request.setAttribute("from", from);
				request.setAttribute("to", to);
				request.setAttribute("caseType", caseType);
				request.setAttribute("reportid", reportid);
				request.setAttribute("fdate", fdate);
				request.setAttribute("tdate", tdate);
				request.setAttribute("reportlist", reportlist);
				caseForm.setReportlist(reportlist);
				caseForm.setCaseDTO(dto);
				session.setAttribute("caseForm", caseForm);
			}
			FORWARD_JSP = CommonConstant.IGRS_TIME_BARRED_CASE_DASHBOARD;
		}

		// end

		// *********added by shruti-19th revision of penalty by IG
		String penalty = (String) request.getParameter("penalty45");
		if (penalty != null) {
			if (penalty.equals("true")) {
				logger.info("Create Case Based on Others Category");
				caseForm = (CaseMonitoringForm) form;
				String from = "1";
				String to = "5";
				int diff = (Integer.parseInt(to) - Integer.parseInt(from)) + 1;
				String dif = String.valueOf(diff);
				logger.info("others Different = " + dif);
				request.setAttribute("dif", dif);
				dto = caseForm.getCaseDTO();
				String source = dto.getReportId();
				caseType = request.getParameter("caseType");
				dto.setCaseType(caseType);
				String caseId = request.getParameter("case_ID");
				dto.setCaseId(caseId);
				String penaltyId = request.getParameter("penalty_ID");// report_ID
				dto.setPenaltyId(penaltyId);
				String fdate = request.getParameter("fromDate");// fromDATE
				dto.setDurationFrom(fdate);
				String tdate = request.getParameter("toDate");// toDATE
				dto.setDurationTo(tdate);
				ArrayList reportlist = bd.getPenaltyList(caseType, penaltyId,caseId, fdate, tdate, from, to);
				request.setAttribute("from", from);
				request.setAttribute("to", to);
				request.setAttribute("caseType", caseType);
				request.setAttribute("fdate", fdate);
				request.setAttribute("tdate", tdate);
				request.setAttribute("reportlist", reportlist);
				caseForm.setReportlist(reportlist);
				caseForm.setCaseDTO(dto);
				session.setAttribute("caseForm", caseForm);
			}
			FORWARD_JSP = CommonConstant.IGRS_PENALTY_CASE_CREATE;
		}
		String penaltypagination = (String) request.getParameter("penaltypaging");
		if (penaltypagination != null) {
			if (penaltypagination.equals("true")) {
				logger.info("Create Case Based on Others Category");
				caseForm = (CaseMonitoringForm) form;
				String from = "1";
				String to = "5";
				int diff = (Integer.parseInt(to) - Integer.parseInt(from)) + 1;
				String dif = String.valueOf(diff);
				logger.info("others Different = " + dif);
				request.setAttribute("dif", dif);
				dto = caseForm.getCaseDTO();
				String source = dto.getReportId();
				caseType = dto.getCaseType();
				String caseId = dto.getCaseId();
				String penaltyId =dto.getPenaltyId();// report_ID
				String fdate = dto.getDurationFrom();// fromDATE
				String tdate = dto.getDurationTo();// toDATE
				ArrayList reportlist = bd.getPenaltyList(caseType, penaltyId,caseId, fdate, tdate, from, to);
				request.setAttribute("from", from);
				request.setAttribute("to", to);
				request.setAttribute("caseType", caseType);
				request.setAttribute("fdate", fdate);
				request.setAttribute("tdate", tdate);
				request.setAttribute("reportlist", reportlist);
				caseForm.setReportlist(reportlist);
				caseForm.setCaseDTO(dto);
				session.setAttribute("caseForm", caseForm);
			}
			FORWARD_JSP = CommonConstant.IGRS_PENALTY_CASE_CREATE;
		}

		// *********added by shruti-19th revision penalty by IG sec 70
		String penalty70 = (String) request.getParameter("penalty70");
		if (penalty70 != null) {
			if (penalty70.equals("true")) {
				logger.info("Create Case Based on Others Category");
				caseForm = (CaseMonitoringForm) form;
				String from = "1";
				String to = "5";
				int diff = (Integer.parseInt(to) - Integer.parseInt(from)) + 1;
				String dif = String.valueOf(diff);
				logger.info("others Different = " + dif);
				request.setAttribute("dif", dif);
				dto = caseForm.getCaseDTO();
				String source = dto.getReportId();
				caseType = request.getParameter("caseType");
				dto.setCaseType(caseType);
				String caseId = request.getParameter("case_ID");
				dto.setCaseId(caseId);
				String penaltyId = request.getParameter("penalty_ID");// report_ID
				dto.setPenaltyId(penaltyId);
				String fdate = request.getParameter("fromDate");// fromDATE
				dto.setDurationFrom(fdate);
				String tdate = request.getParameter("toDate");// toDATE
				dto.setDurationTo(tdate);
				ArrayList reportlist = bd.getPenaltyList(caseType, penaltyId,caseId, fdate, tdate, from, to);
				request.setAttribute("from", from);
				request.setAttribute("to", to);
				request.setAttribute("caseType", caseType);
				request.setAttribute("fdate", fdate);
				request.setAttribute("tdate", tdate);
				request.setAttribute("reportlist", reportlist);
				caseForm.setReportlist(reportlist);
				caseForm.setCaseDTO(dto);
				session.setAttribute("caseForm", caseForm);
			}
			FORWARD_JSP = CommonConstant.IGRS_PENALTY_CASE_CREATE;
		}
		//LICENSE CANCELLATION
		String cancelLicense = (String) request.getParameter("cancelLicense");
		if (cancelLicense != null) {
			if (cancelLicense.equals("true")) {
				logger.info("Create Case Based on Others Category");
				caseForm = (CaseMonitoringForm) form;
				String from = "1";
				String to = "5";
				int diff = (Integer.parseInt(to) - Integer.parseInt(from)) + 1;
				String dif = String.valueOf(diff);
				logger.info("others Different = " + dif);
				request.setAttribute("dif", dif);
				dto = caseForm.getCaseDTO();
				String source = dto.getReportId();
				caseType = request.getParameter("caseType");
				dto.setCaseType(caseType);
				String licenseId=request.getParameter("license_ID");
				dto.setLicenseId(licenseId);
				String fdate = request.getParameter("lFromDate");// fromDATE
				dto.setDurationFrom(fdate);
				String tdate = request.getParameter("lToDate");// toDATE
				dto.setDurationTo(tdate);
				ArrayList reportlist = bd.getlicensedUserList(caseType, licenseId, fdate, tdate, from, to,language);
				request.setAttribute("from", from);
				request.setAttribute("to", to);
				request.setAttribute("caseType", caseType);
				request.setAttribute("fdate", fdate);
				request.setAttribute("tdate", tdate);
				request.setAttribute("reportlist", reportlist);
				caseForm.setReportlist(reportlist);
				caseForm.setCaseDTO(dto);
				session.setAttribute("caseForm", caseForm);
			}
			FORWARD_JSP = CommonConstant.IGRS_LICENSE_CANCELLATION_CASE_CREATE;
		}
		String licensepagination = (String) request.getParameter("licensepaging");
		if (licensepagination != null) {
			if (licensepagination.equals("true")) {
				logger.info("Create Case Based on Others Category");
				caseForm = (CaseMonitoringForm) form;
				String from = "1";
				String to = "5";
				int diff = (Integer.parseInt(to) - Integer.parseInt(from)) + 1;
				String dif = String.valueOf(diff);
				logger.info("others Different = " + dif);
				request.setAttribute("dif", dif);
				dto = caseForm.getCaseDTO();
				String source = dto.getReportId();
				caseType =dto.getCaseType();
				String licenseId=dto.getLicenseId();
				String fdate = dto.getDurationFrom();// fromDATE
				String tdate = dto.getDurationTo();// toDATE
				ArrayList reportlist = bd.getlicensedUserList(caseType, licenseId, fdate, tdate, from, to,language);
				request.setAttribute("from", from);
				request.setAttribute("to", to);
				request.setAttribute("caseType", caseType);
				request.setAttribute("fdate", fdate);
				request.setAttribute("tdate", tdate);
				request.setAttribute("reportlist", reportlist);
				caseForm.setReportlist(reportlist);
				caseForm.setCaseDTO(dto);
				session.setAttribute("caseForm", caseForm);
			}
			FORWARD_JSP = CommonConstant.IGRS_LICENSE_CANCELLATION_CASE_CREATE;
		}
		
		String revdetail = request.getParameter("revenuedetail");
		if (revdetail != null) 
		{
			if (revdetail.equals("true")) 
			{
				logger.info("Inside Revenue Detail according to ReportId");
				if (caseType.equalsIgnoreCase("CASET_005")||caseType.equalsIgnoreCase("CASET_007")) 
				{
					caseForm.getCaseDTO().setReportId(request.getParameter("reportId"));
				}
				else if (caseType.equalsIgnoreCase("CASET_009")) 
				{
					caseForm.getCaseDTO().setLicenseId(request.getParameter("licenseId"));
				}
				else if (caseType.equalsIgnoreCase("CASET_010")) 
				{
					caseForm.getCaseDTO().setReportId(request.getParameter("reportId"));
				}
				else if ((caseType.equalsIgnoreCase("CASET_011"))|| (caseType.equalsIgnoreCase("CASET_012"))) 
				{
					String hdnParam=request.getParameter("hdnParam");
					if(!hdnParam.equalsIgnoreCase(null))
					{
					String[] param=hdnParam.split("~");
					caseForm.getCaseDTO().setReportId(param[0]);
					caseForm.getCaseDTO().setPenaltyId((param[1]));
					}
				}
				else //CASET_001,002,003,004//check roopam
				{
					//added by shruti--8 jan 2015
					caseForm.getCaseDTO().setCaseHead("CH_001");
					//end
					String hdnParam=request.getParameter("hdnParam");
					if(!hdnParam.equalsIgnoreCase(null))
					{
					String[] param=hdnParam.split("~");
					caseForm.getCaseDTO().setReportId(param[0]);
					caseForm.getCaseDTO().setParaId(param[1]);
					caseForm.getCaseDTO().setObjId(param[2]);
					if(caseType.equalsIgnoreCase("CASET_004")){//pot case-done by roopam-5feb2015
						caseForm.getCaseDTO().setEstampId(param[3]);
					}else{
					caseForm.getCaseDTO().setRegId(param[3]);
					}
					}
				}
				dto = caseForm.getCaseDTO();
				String docid = dto.getAppId();
				String repid = dto.getReportId();
				String regid=dto.getRegId();
				String estampid=dto.getEstampId();
				//modified by shruti---8 jan 2015
				ArrayList revList = new ArrayList();
				ArrayList secList=new ArrayList();
				
				if("CH_001".equalsIgnoreCase(caseForm.getCaseDTO().getCaseHead()))
				{
					revList = bd.getRevenueList(caseForm.getCaseDTO().getCaseHead());
					secList = bd.getSectionList(caseType);
				}
				else
				{
					revList = bd.getRevenueList(caseForm.getCaseDTO().getCaseHead());
					secList = bd.getSectionList(caseType);
				}
				
				
				caseForm.setRevenueList(revList);
				caseForm.setSectionList(secList);

				if ((caseType.equalsIgnoreCase("CASET_001"))|| (caseType.equalsIgnoreCase("CASET_002"))) 
				{
					ArrayList list2 = (ArrayList) bd.getAuditList(dto);
					if (list2 != null) 
					{
						for (int i = 0; i < list2.size(); i++) 
						{
							CaseMonitoringDTO dto = (CaseMonitoringDTO) list2.get(i);
							caseForm.getCaseDTO().setAuditDate(dto.getAuditDate().toString());
							caseForm.getCaseDTO().setReportId(dto.getReportId().toString());
							//COMMENTED AS OF NOW AS DATA NEEDS TO BE IN SYNC WITH AUDIT & INSPECTION
							if(dto.getValFromAgmp()!=null)
							{
								caseForm.getCaseDTO().setValFromAgmp(dto.getValFromAgmp().toString());
							}
							if(dto.getDefStampDuty()!=null)
							{
								caseForm.getCaseDTO().setDefStampDuty(dto.getDefStampDuty().toString());
							}
							if(dto.getDefRegFee()!=null)
							{
								caseForm.getCaseDTO().setDefRegFee(dto.getDefRegFee().toString());
							}		
						}
					}
					ArrayList regData=(ArrayList)bd.getRegDataList(dto);
					if(regData!=null)
					{
						for (int i = 0; i < regData.size(); i++) 
						{
							CaseMonitoringDTO dto = (CaseMonitoringDTO) regData.get(i);
							if(dto.getValAtRegTime()!=null)
							{
							caseForm.getCaseDTO().setValAtRegTime(dto.getValAtRegTime());
							}
							if(dto.getDocStampDuty()!=null)
							{
							caseForm.getCaseDTO().setDocStampDuty(dto.getDocStampDuty());
							}
							if(dto.getDocRegFee()!=null)
							{
							//caseForm.getCaseDTO().setValAtRegTime(dto.getDocRegFee());
								caseForm.getCaseDTO().setDocRegFee(dto.getDocRegFee());//DONE BY ROOPAM(26FEB2015) BUG 2790
							}
							caseForm.getCaseDTO().setSrname(dto.getSrname());
							caseForm.getCaseDTO().setSroname(dto.getSroname());
							caseForm.getCaseDTO().setRegDate(dto.getRegDate());
						}
					}
					PartyDetailsDTO partyDto= bd.getPartyList(dto,language);
					ArrayList partyList=partyDto.getPartyList();
					logger.info("SIZE OF PARTY LIST>>>>"+partyList.size());
					partyDto.setPartyList(partyList);
					caseForm.setPartyDto(partyDto);
					caseForm.setRevenueList(revList);
					caseForm.setSectionList(secList);
					
					logger.info("Values are loaded from the database.");
					session.setAttribute("caseForm", caseForm);
					session.setAttribute("repid", repid);
					FORWARD_JSP = CommonConstant.IGRS_REVENUE_DETAIL_REPORT;
				}

				if ((caseType.equalsIgnoreCase("CASET_003"))) 
				{
					ArrayList list2 = (ArrayList) bd.getAuditDate(dto);//getAuditDate created and used by Roopam-27feb2015
					if (list2 != null) 
					{
						for (int i = 0; i < list2.size(); i++) 
						{
							CaseMonitoringDTO dto = (CaseMonitoringDTO) list2.get(i);
							
							caseForm.getCaseDTO().setAuditDate(dto.getAuditDate().toString());
							caseForm.getCaseDTO().setPoName(dto.getPoName().toString());
								
						}
					}
					ArrayList list3 = (ArrayList) bd.getReqDefStampDuty(dto);//getReqDefStampDuty created and used by Roopam-27feb2015
					if (list3 != null) 
					{
						for (int i = 0; i < list3.size(); i++) 
						{
							CaseMonitoringDTO dto = (CaseMonitoringDTO) list3.get(i);
							
							caseForm.getCaseDTO().setReqStampDuty(dto.getReqStampDuty().toString());
							
								
						}
					}
					ArrayList regData=(ArrayList)bd.getRegDataList(dto);
					if(regData!=null)
					{
						for (int i = 0; i < regData.size(); i++) 
						{
							CaseMonitoringDTO dto = (CaseMonitoringDTO) regData.get(i);
							if(dto.getValAtRegTime()!=null)
							{
							caseForm.getCaseDTO().setValAtRegTime(dto.getValAtRegTime());
							}
							if(dto.getDocStampDuty()!=null)
							{
							caseForm.getCaseDTO().setDocStampDuty(dto.getDocStampDuty());
							}
							if(dto.getDocRegFee()!=null)
							{
							//caseForm.getCaseDTO().setValAtRegTime(dto.getDocRegFee());
								caseForm.getCaseDTO().setDocRegFee(dto.getDocRegFee());//CORRECTED BY ROOPAM-26FEB2015
							}
							caseForm.getCaseDTO().setSrname(dto.getSrname());
							caseForm.getCaseDTO().setSroname(dto.getSroname());
							caseForm.getCaseDTO().setRegDate(dto.getRegDate());
						}
					}
					PartyDetailsDTO partyDto= bd.getPartyList(dto,language);
					ArrayList partyList=partyDto.getPartyList();
					logger.info("SIZE OF PARTY LIST>>>>"+partyList.size());
					partyDto.setPartyList(partyList);
					caseForm.setPartyDto(partyDto);
					
					caseForm.setRevenueList(revList);
					caseForm.setSectionList(secList);
					logger.info("Values are loaded from the database.");
					session.setAttribute("caseForm", caseForm);
					session.setAttribute("docid", docid);
					session.setAttribute("repid", repid);
					FORWARD_JSP = CommonConstant.IGRS_REVENUE_DETAIL_REPORT1;
				}

				else if ((caseType.equalsIgnoreCase("CASET_004")))
				{
					
					
					
					ArrayList list2 = (ArrayList) bd.getAuditList(dto);
					logger.debug("list2:"+list2);
					if (list2 != null) 
					{
						for (int i = 0; i < list2.size(); i++) 
						{
							CaseMonitoringDTO dto = (CaseMonitoringDTO) list2.get(i);
							caseForm.getCaseDTO().setSrname(dto.getSrname().toString());
							caseForm.getCaseDTO().setSroname(dto.getSroname().toString());
							caseForm.getCaseDTO().setAuditDate(dto.getAuditDate().toString());
							caseForm.getCaseDTO().setReportId(dto.getReportId().toString());
							//COMMENTED AS OF NOW AS DATA NEEDS TO BE IN SYNC WITH AUDIT & INSPECTION
							
							if(dto.getValFromAgmp()!=null)
							{
								caseForm.getCaseDTO().setValFromAgmp(dto.getValFromAgmp().toString());
							}
							if(dto.getDefStampDuty()!=null)
							{
								caseForm.getCaseDTO().setDefStampDuty(dto.getDefStampDuty().toString());
							}
							if(dto.getDefRegFee()!=null)
							{
								caseForm.getCaseDTO().setDefRegFee(dto.getDefRegFee().toString());
							}	
						}
					}
					ArrayList regData=(ArrayList)bd.getRegDataList(dto);
					if(regData!=null)
					{
						for (int i = 0; i < regData.size(); i++) 
						{
							CaseMonitoringDTO dto = (CaseMonitoringDTO) regData.get(i);
							if(dto.getValAtRegTime()!=null)
							{
							caseForm.getCaseDTO().setValAtRegTime(dto.getValAtRegTime());
							}
							if(dto.getDocStampDuty()!=null)
							{
							caseForm.getCaseDTO().setDocStampDuty(dto.getDocStampDuty());
							}
							if(dto.getDocRegFee()!=null)
							{
							caseForm.getCaseDTO().setValAtRegTime(dto.getDocRegFee());
							}
							caseForm.getCaseDTO().setSrname(dto.getSrname());
							caseForm.getCaseDTO().setSroname(dto.getSroname());
							caseForm.getCaseDTO().setRegDate(dto.getRegDate());
						}
					}
					
					//added by shruti---9th july 2014
					PartyDetailsDTO partyDto= bd.getPartyList(dto,language);
					ArrayList partyList=partyDto.getPartyList();
					logger.info("SIZE OF PARTY LIST>>>>"+partyList.size());
					partyDto.setPartyList(partyList);
					caseForm.setPartyDto(partyDto);
					//below added by roopam-5feb2015
					String potEstampData=(String)bd.EstampDataList(dto);
					caseForm.getCaseDTO().setDocStampDuty(potEstampData);
					ArrayList POTData=(ArrayList)bd.getPOTDetails(dto,language);
					
					logger.debug("POTData?"+POTData);
					if (POTData != null) 
					{
						for (int i = 0; i < POTData.size(); i++) 
						{
							
							CaseMonitoringDTO dto = (CaseMonitoringDTO) POTData.get(i);
							caseForm.getCaseDTO().setSrname(dto.getSrname());
							caseForm.getCaseDTO().setSroname(dto.getSroname());
							caseForm.getCaseDTO().setAuditDate(dto.getAuditDate());
							//caseForm.getCaseDTO().setReportId(dto.getReportId());
							//caseForm.getCaseDTO().setEstampId(dto.getEstampId());
							caseForm.getCaseDTO().setPoComments(dto.getPoComments());
							caseForm.getCaseDTO().setReqStampDuty(dto.getReqStampDuty());
							caseForm.getCaseDTO().setDistrictName(dto.getDistrictName());
							caseForm.getCaseDTO().setRegDate(dto.getRegDate());
								
						}
					}
					//get physical stamps list here
					caseForm.getCaseDTO().setStampNo(bd.getPOTPhysicalStampDetails(repid));
					if(caseForm.getCaseDTO().getStampNo()!=null && !("").equalsIgnoreCase(caseForm.getCaseDTO().getStampNo())
							&& !("-").equalsIgnoreCase(caseForm.getCaseDTO().getStampNo())
							&& !("null").equalsIgnoreCase(caseForm.getCaseDTO().getStampNo())){
						
						partyDto= bd.getPOTPstampPartyList(dto,language);	
						caseForm.getCaseDTO().setEstampNo("");
					}else{
					partyDto= bd.getPOTPartyList(dto,language);
					caseForm.getCaseDTO().setEstampNo(caseForm.getCaseDTO().getEstampId());
					}
					partyList=partyDto.getPartyList();
					logger.info("SIZE OF PARTY LIST>>>>"+partyList.size());
					partyDto.setPartyList(partyList);
					caseForm.setPartyDto(partyDto);
					
					
					//end
					caseForm.setRevenueList(revList);
					caseForm.setSectionList(secList);
					logger.info("Values are loaded from the database.");
					session.setAttribute("caseForm", caseForm);
					session.setAttribute("docid", docid);
					session.setAttribute("repid", repid);
					FORWARD_JSP = CommonConstant.IGRS_AGMP_PO_REPORT;
				} 
				else if ((caseType.equalsIgnoreCase("CASET_005"))) 
				{
					PartyDetailsDTO partyDto= bd.getImpoundedDataList(dto);
					ArrayList partyList=partyDto.getPartyList();
					logger.info("SIZE OF PARTY LIST>>>>"+partyList.size());
					partyDto.setPartyList(partyList);
					caseForm.setPartyDto(partyDto);
					//added by shruti
					ArrayList regData=(ArrayList)bd.getRegDataListImpoundCase(dto);
					if(regData!=null)
					{
						for (int i = 0; i < regData.size(); i++) 
						{
							CaseMonitoringDTO dto = (CaseMonitoringDTO) regData.get(i);
							if(dto.getValAtRegTime()!=null)
							{
							caseForm.getCaseDTO().setValAtRegTime(dto.getValAtRegTime());
							}
							if(dto.getDocStampDuty()!=null)
							{
							caseForm.getCaseDTO().setDocStampDuty(dto.getDocStampDuty());
							}
							if(dto.getDocRegFee()!=null)
							{
							caseForm.getCaseDTO().setValAtRegTime(dto.getDocRegFee());
							}
							caseForm.getCaseDTO().setSrname(dto.getSrname());
							caseForm.getCaseDTO().setSroname(dto.getSroname());
							caseForm.getCaseDTO().setRegDate(dto.getRegDate());
						}
					}
					//end
					caseForm.setRevenueList(revList);
					caseForm.setSectionList(secList);
					logger.info("Values are loaded from the database.");
					session.setAttribute("caseForm", caseForm);
					session.setAttribute("docid", docid);
					session.setAttribute("repid", repid);
					FORWARD_JSP = CommonConstant.IGRS_AGMP_SR_IMPOUND_REPORT;
				}

				else if ((caseType.equalsIgnoreCase("CASET_007"))) 
				{
					ArrayList refunddtls = (ArrayList) bd.getEstampTxnDetails(dto);
					if (refunddtls != null) 
					{
						for (int i = 0; i < refunddtls.size(); i++) 
						{
							CaseMonitoringDTO dto = (CaseMonitoringDTO) refunddtls.get(i);
							caseForm.getCaseDTO().setEstampIssuePerson(dto.getEstampIssuePerson());
							caseForm.getCaseDTO().setEstampIssuedOffice(dto.getEstampIssuedOffice());
							caseForm.getCaseDTO().setEstampRefundPerson(dto.getEstampRefundPerson());
							caseForm.getCaseDTO().setEstampRefundPrsnAddress(dto.getEstampRefundPrsnAddress());
							//added by shruti--30 sep 2014
							caseForm.getCaseDTO().setEstampAmt(dto.getEstampAmt());
							caseForm.getCaseDTO().setEstampFactor(dto.getEstampFactor());
							//int estampAmt=Integer.parseInt(dto.getEstampAmt().toString());
							//int estampFactor=Integer.parseInt(dto.getEstampFactor().toString());
							double estampAmt=Double.parseDouble(dto.getEstampAmt().toString());
							double estampFactor=Double.parseDouble(dto.getEstampFactor().toString());
							double finalAmt=estampAmt-(estampAmt*estampFactor/100);
							logger.info("total estamp amount>>>"+dto.getEstampAmt());
							logger.info("final amount calculated for refund after deduction>>>>"+finalAmt);
							String refundAmt=String.valueOf(finalAmt) ;
							caseForm.getCaseDTO().setEstampRefundAmt(refundAmt);
							caseForm.getCaseDTO().setStampAmount(refundAmt);
						}
					}
					caseForm.setRevenueList(revList);
					caseForm.setSectionList(secList);
					logger.info("Values are loaded from the database.");
					session.setAttribute("caseForm", caseForm);
					session.setAttribute("docid", docid);
					session.setAttribute("repid", repid);
					FORWARD_JSP = CommonConstant.IGRS_CASE_ESTAMP_REFUND;
				}
				else if ((caseType.equalsIgnoreCase("CASET_009"))) {
					ArrayList refunddtls = (ArrayList) bd.getEstampTxnDetails(dto);
					dto.setReportId(dto.getLicenseId());
					caseForm.setRevenueList(revList);
					caseForm.setSectionList(secList);
					logger.info("Values are loaded from the database.");
					session.setAttribute("caseForm", caseForm);
					session.setAttribute("docid", docid);
					session.setAttribute("repid", repid);
					FORWARD_JSP = CommonConstant.IGRS_CASE_LICENSE_CANCLTN;
				}
				else if ((caseType.equalsIgnoreCase("CASET_010"))) {
					dto.setRegId(bd.getRegNumber(dto.getReportId()));
					ArrayList list2 = (ArrayList) bd.getAuditList(dto);
					logger.debug(list2);
					if (list2 != null)
					{for (int i = 0; i < list2.size(); i++) 
						{	CaseMonitoringDTO dto = (CaseMonitoringDTO) list2.get(i);
							caseForm.getCaseDTO().setSrname(dto.getSrname().toString());
							caseForm.getCaseDTO().setSroname(dto.getSroname().toString());
							caseForm.getCaseDTO().setAuditDate(dto.getAuditDate().toString());
							caseForm.getCaseDTO().setReportId(dto.getReportId().toString());
							//COMMENTED AS OF NOW AS DATA NEEDS TO BE IN SYNC WITH AUDIT & INSPECTION
							if(dto.getValFromAgmp()!=null)
							{caseForm.getCaseDTO().setValFromAgmp(dto.getValFromAgmp().toString());
							}
							if(dto.getDefStampDuty()!=null)
							{caseForm.getCaseDTO().setDefStampDuty(dto.getDefStampDuty().toString());
							}
							if(dto.getDefRegFee()!=null)
							{caseForm.getCaseDTO().setDefRegFee(dto.getDefRegFee().toString());
							}	
						}
					}
					ArrayList list3 = (ArrayList) bd.getRegPartyDetails(dto);
					logger.debug(list3);
					if (list3 != null)
					{for (int i = 0; i < 1; i++) 
						{	CaseMonitoringDTO dto = (CaseMonitoringDTO) list3.get(i);
							caseForm.getCaseDTO().setFirstName(dto.getFirstName().toString());
							caseForm.getCaseDTO().setLastName(dto.getLastName().toString());
								
						}
					}
					ArrayList regData=(ArrayList)bd.getRegDetailsTimeBarred(dto);
					//getRegDetailsTimeBarred CREATED AND USED BY ROOPAM(26FEB2015) SPECIFICALLY FOR TIME BARRED CASES BECAUSE OF ABSENCE OF REGISTRATION NUMBER IN THIS CASE.
					if(regData!=null)
					{
						for (int i = 0; i < regData.size(); i++) 
						{
							CaseMonitoringDTO dto = (CaseMonitoringDTO) regData.get(i);
							if(dto.getValAtRegTime()!=null)
							{
							caseForm.getCaseDTO().setValAtRegTime(dto.getValAtRegTime());
							}
							if(dto.getDocStampDuty()!=null)
							{
							caseForm.getCaseDTO().setDocStampDuty(dto.getDocStampDuty());
							}
							if(dto.getDocRegFee()!=null)
							{
							caseForm.getCaseDTO().setValAtRegTime(dto.getDocRegFee());
							}
							caseForm.getCaseDTO().setSrname(dto.getSrname());
							caseForm.getCaseDTO().setSroname(dto.getSroname());
							caseForm.getCaseDTO().setRegDate(dto.getRegDate());
						}
					}
					//added by shruti---14 oct 2014
					ArrayList amountData=(ArrayList)bd.getAmountDataList(dto);
					if(amountData!=null)
					{
						for (int i = 0; i < amountData.size(); i++) 
						{
							CaseMonitoringDTO dto = (CaseMonitoringDTO) amountData.get(i);
							caseForm.getCaseDTO().setPenaltyAmt(dto.getPenaltyAmt());
							caseForm.getCaseDTO().setRecPenaltyAmt(dto.getRecPenaltyAmt());
						}
					}
					
					//modified by shruti-------------------------penalty multiply check
					//to be modified by Roopam
					ArrayList durationData=(ArrayList)bd.getDurationchkAmt(dto);
					if(durationData!=null)
					{
						for (int i = 0; i < amountData.size(); i++) 
						{
							CaseMonitoringDTO dto = (CaseMonitoringDTO) durationData.get(i);
							caseForm.getCaseDTO().setDurationChk(dto.getDurationChk());
						}
					}
					String duration=caseForm.getCaseDTO().getDurationChk();
					int d=Integer.parseInt(duration);
					logger.debug("duration>>>>>"+d);
					logger.debug("penalty amount>>>>"+String.valueOf((Integer.parseInt(caseForm.getCaseDTO().getPenaltyAmt()) + (Integer.parseInt(caseForm.getCaseDTO().getPenaltyAmt())*2))));
					//below penaltyAmount variable added by Roopam on 24Jan2015 for fixing incorrect recPenaltyAmt value on screen
					String penaltyAmount=caseForm.getCaseDTO().getPenaltyAmt();
					
					if(d>0)
					{
						/*switch(d)
						{
						case 1:caseForm.getCaseDTO().setPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (Integer.parseInt(penaltyAmount)*2))));
							   caseForm.getCaseDTO().setRecPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (Integer.parseInt(penaltyAmount)*2))));
								break;
						case 2:caseForm.getCaseDTO().setPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (Integer.parseInt(penaltyAmount)*4))));
							   caseForm.getCaseDTO().setRecPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (Integer.parseInt(penaltyAmount)*4))));
								break;
						case 3:caseForm.getCaseDTO().setPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (Integer.parseInt(penaltyAmount)*6))));
							   caseForm.getCaseDTO().setRecPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (String.valueOf(Integer.parseInt(penaltyAmount)*6)))));
								break;
						case 4:caseForm.getCaseDTO().setPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (Integer.parseInt(penaltyAmount)*8))));
								caseForm.getCaseDTO().setRecPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (String.valueOf(Integer.parseInt(penaltyAmount)*8)))));
								break;
						case 5:caseForm.getCaseDTO().setPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (Integer.parseInt(penaltyAmount)*10))));
								caseForm.getCaseDTO().setRecPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (Integer.parseInt(penaltyAmount)*8))));
								break;
						default:caseForm.getCaseDTO().setPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (Integer.parseInt(penaltyAmount)*10))));
								caseForm.getCaseDTO().setRecPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (Integer.parseInt(penaltyAmount)*10))));
								break;
						}	*/		
						
						
						
						/*if(d>=120){// EXECUTED EVEN IF 1 DAY MORE THAN 4 MONTHS(120 DAYS) i.e. 5th Months started
							caseForm.getCaseDTO().setPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (Integer.parseInt(penaltyAmount)*10))));
							caseForm.getCaseDTO().setRecPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (Integer.parseInt(penaltyAmount)*10))));
						}else */
						if(d>=90){// EXECUTED EVEN IF 1 DAY MORE THAN 3 MONTHS(90 DAYS) i.e. 4th Months started
							caseForm.getCaseDTO().setPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (Integer.parseInt(penaltyAmount)*10))));
							caseForm.getCaseDTO().setRecPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (Integer.parseInt(penaltyAmount)*10))));
						}else if(d>=60){// EXECUTED EVEN IF 1 DAY MORE THAN 2 MONTHS(60 DAYS) i.e. 3rd Months started
							caseForm.getCaseDTO().setPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (Integer.parseInt(penaltyAmount)*6))));
							caseForm.getCaseDTO().setRecPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (Integer.parseInt(penaltyAmount)*6))));
						}else if(d>=30){// EXECUTED EVEN IF 1 DAY MORE THAN 1 MONTH(30 DAYS) i.e. 2nd Months started
							caseForm.getCaseDTO().setPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (Integer.parseInt(penaltyAmount)*4))));
							caseForm.getCaseDTO().setRecPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (Integer.parseInt(penaltyAmount)*4))));
						}else if(d>=0){// EXECUTED IF 1st Months started
							caseForm.getCaseDTO().setPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (Integer.parseInt(penaltyAmount)*2))));
							caseForm.getCaseDTO().setRecPenaltyAmt(String.valueOf((Integer.parseInt(penaltyAmount) + (Integer.parseInt(penaltyAmount)*2))));
						}/*else{
							caseForm.getCaseDTO().setPenaltyAmt(penaltyAmount);
							caseForm.getCaseDTO().setRecPenaltyAmt(penaltyAmount);
						}*/
							
					
					}
				
					
					//end
					caseForm.setRevenueList(revList);
					caseForm.setSectionList(secList);
					logger.info("Values are loaded from the database.");
					session.setAttribute("caseForm", caseForm);
					session.setAttribute("docid", docid);
					session.setAttribute("repid", repid);
					FORWARD_JSP = CommonConstant.IGRS_CASE_TIME_BARRED;
				}
				else if ((caseType.equalsIgnoreCase("CASET_011"))) 
				{
					caseForm.setRevenueList(revList);
					caseForm.setSectionList(secList);
					logger.info("Values are loaded from the database.");
					session.setAttribute("caseForm", caseForm);
					session.setAttribute("docid", docid);
					session.setAttribute("repid", repid);
					FORWARD_JSP = CommonConstant.IGRS_CASE_PENALTY45;
				} 
				else if ((caseType.equalsIgnoreCase("CASET_012"))) 
				{
					caseForm.setRevenueList(revList);
					caseForm.setSectionList(secList);
					logger.info("Values are loaded from the database.");
					session.setAttribute("caseForm", caseForm);
					session.setAttribute("docid", docid);
					session.setAttribute("repid", repid);
					FORWARD_JSP = CommonConstant.IGRS_CASE_PENALTY70;
				}
			}
		}

		// Revenue Module - create new revenue case id
		String submit = request.getParameter("revsubmit");
		if ("CreateRevenue".equals(formName)) {
			if ("submit".equals(actionName)) {
				logger.info("Method to generate ReportId");
				logger.info("I am in action ====");
				CaseMonitoringForm cmform = (CaseMonitoringForm) session.getAttribute("caseForm");
				String docid = (String) session.getAttribute("docid");
				String repid = (String) session.getAttribute("repid");
				String caseTypeID = (String) session.getAttribute("caseType");
				String caseTypeHeadID = (String) session.getAttribute("caseHead");

				CaseMonitoringDTO dto = caseForm.getCaseDTO();
				dto.setCaseType(caseTypeID);
				dto.setCaseHead(caseTypeHeadID);
				dto.setUserId(userId);
				//modified by shruti---9 oct 2014
				dto.setCurrentOffId(offId);
				//end
				HashMap mapAttachment = (HashMap) session.getAttribute("mapFile");

				if (mapAttachment != null) {
					logger.info("I am inside if of mapAttachment");
					Iterator I = map.entrySet().iterator();
					while (I.hasNext()) {
						Map.Entry me = (Map.Entry) I.next();
					}
				}
				//REFUND CASES
				String reportid = "";
				if ((caseTypeID.equalsIgnoreCase("CASET_007"))|| (caseTypeID.equalsIgnoreCase("CASET_008"))
						&& caseTypeHeadID.equalsIgnoreCase("CH_002")) 
				{
					if ((caseForm.getCaseDTO().getRule() != null)) 
					{
						if ((caseForm.getCaseDTO().getRule().equalsIgnoreCase("on")))
						{
							dto.setRule("Y");
						}
					} 
					else 
					{
						dto.setRule("N"); 
					}
					reportid = bd.getRefundCaseId(dto, repid);
					request.setAttribute("caseForm", cmform);
					request.setAttribute("reportid", reportid);
					session.removeAttribute("mapFile");
					FORWARD_JSP = CommonConstant.IGRS_CREATE_REVENUE_ID;
				} 
				//PENALTY 45
				else if ((caseTypeID.equalsIgnoreCase("CASET_011"))&& caseTypeHeadID.equalsIgnoreCase("CH_003"))
				{
					reportid = bd.getRevisionId(dto, repid);
					request.setAttribute("caseForm", cmform);
					request.setAttribute("reportid", reportid);
					session.removeAttribute("mapFile");
					FORWARD_JSP = CommonConstant.IGRS_CREATE_REVISION_ID;
				}
				//PENALTY 70
				else if ((caseTypeID.equalsIgnoreCase("CASET_012")))
				{
					reportid = bd.getRevisionIdSec70(dto, repid);
					request.setAttribute("caseForm", cmform);
					request.setAttribute("reportid", reportid);
					session.removeAttribute("mapFile");
					FORWARD_JSP = CommonConstant.IGRS_CREATE_REVISION_ID;
				} 
				//TIME BARRED
				else if (caseTypeID.equalsIgnoreCase("CASET_010")) 
				{
					reportid = bd.getOthersCaseId(dto, repid);
					//logger.info("ReportId=" + reportid);
					request.setAttribute("caseForm", cmform);
					request.setAttribute("reportid", reportid);
					session.removeAttribute("mapFile");
					FORWARD_JSP = CommonConstant.IGRS_CREATE_REVENUE_ID;
				} 
				//REVENUE CASES,LICENSE CANCELLATION(OTHERS)
				else if(caseTypeID.equalsIgnoreCase("CASET_005"))
				{
					reportid = bd.getRevreportIdImpound(dto, docid, repid,mapAttachment);
					request.setAttribute("caseForm", cmform);
					request.setAttribute("reportid", reportid);
					session.removeAttribute("mapFile");
					FORWARD_JSP = CommonConstant.IGRS_CREATE_REVENUE_ID;
				}
				else 
				{
					reportid = bd.getRevreportId(dto, docid, repid,mapAttachment,offId);
					request.setAttribute("caseForm", cmform);
					request.setAttribute("reportid", reportid);
					session.removeAttribute("mapFile");
					FORWARD_JSP = CommonConstant.IGRS_CREATE_REVENUE_ID;
				}
				//empty all the dtos
				dto.setRevenueHead("");
				dto.setSectionHead("");
				dto.setCaseHead("");
				dto.setCaseType("");
				dto.setReportId("");
				dto.setDrCmt("");
				dto.setAddress("");
				dto.setAuditDate("");
				dto.setValAtRegTime("");
				dto.setValFromAgmp("");
				dto.setRecStampDuty("");
				dto.setRecRegFee("");
				dto.setCaseregdate("");
				dto.setDistrictName("");
				dto.setFirstName("");
				dto.setLastName("");
				dto.setSrname("");
				dto.setSroname("");
				dto.setSpotCmt("");
				dto.setNexthrDate("");
				dto.setHearDate("");
				dto.setRevenueId("");
				dto.setSectionId("");
				dto.setDefRegFee("");
				dto.setDefStampDuty("");
				dto.setDocRegFee("");
				dto.setDocStampDuty("");
				dto.setRefundAmount("");
				dto.setRefundAmt("");
				dto.setEamount("");
				dto.setStampAmount("");
				dto.setRegFee("");
				dto.setStampAmt("");
				dto.setDrComment("");
				caseForm.setCaseregdate("");
				caseForm.setDistrictName("");
				//end
			}
			if ("attach".equals(actionName)) {
				HashMap map = (HashMap) session.getAttribute("mapFile");
				logger.info("Method to generate ReportId");
				CaseMonitoringDTO dto = caseForm.getCaseDTO();
				dto.setFileDetails(map);
				dto.setFileName("");
				dto.setFilePath("");
				caseForm.setCaseDTO(dto);
				FORWARD_JSP = CommonConstant.ATTACHMENT;
			}
		}

		if ("returnattachment".equals(actionName)) {
			ArrayList revList = bd.getRevheadList();
			ArrayList secList = bd.getSecheadList();
			caseForm.setRevenueList(revList);
			caseForm.setSectionList(secList);

			session.setAttribute("caseForm", caseForm);
			FORWARD_JSP = CommonConstant.IGRS_CASE_REFUND_OPTIONAL;
		}
		return mapping.findForward(FORWARD_JSP);
	}
}