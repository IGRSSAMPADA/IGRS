package com.wipro.igrs.payment.action;

/**
 * ===========================================================================
 * File           :   PaymentServiceAction.java
 * Description    :   Represents the Payment Service Action Class
 * Author         :   Shreeraj Khare
 * Created Date   :   Jan 12, 2017

 * ===========================================================================
 */
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.payment.bd.PaymentServiceBD;
import com.wipro.igrs.payment.constant.CommonConstant;
import com.wipro.igrs.payment.dto.PaymentServiceDTO;
import com.wipro.igrs.payment.form.PaymentServiceForm;
import com.wipro.igrs.report.common.MarketTrendReportConstants;
import com.wipro.igrs.util.JrxmlExportUtility;
import com.wipro.igrs.util.PropertiesFileReader;

public class PaymentServiceAction extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		String FORWARD_JSP = "";
		String userId = (String) session.getAttribute("UserId");
		String officeId = (String) session.getAttribute("loggedToOffice");
		String languageLocale = "hi";
		if (session.getAttribute("languageLocale") != null) {
			languageLocale = (String) session.getAttribute("languageLocale");
		}
		Logger logger = (Logger) Logger.getLogger(PaymentServiceAction.class);
		PaymentServiceForm pForm = new PaymentServiceForm();
		PaymentServiceBD bd = new PaymentServiceBD();
		PaymentServiceDTO pDTO = pForm.getPDTO();
		String param = (String) request.getParameter("param");
		String actionName;
		if (form != null) {

			pForm = (PaymentServiceForm) form;
			//actionName = pForm.getActionName();
			pForm.setUserID(userId);
			if (param != null && param.equalsIgnoreCase("PaymentServiceHome")) {
				ArrayList serviceList = bd.getServiceList(languageLocale);
				ArrayList offServiceList = bd.getOfflineServiceList(languageLocale,userId);
				ArrayList fromYear = bd.getYearRange(languageLocale);
				ArrayList toYear = bd.getYearRange(languageLocale);
				pForm.setServiceList(serviceList);
				pForm.setOfflineServiceList(offServiceList);
				pForm.setFromYear(fromYear);
				pForm.setToYear(toYear);
				pForm.setRefAmount("");
				pForm.setRefID("");
				pForm.setActionName("");
				pForm.setPayableAmt("");
				pForm.setServiceID("");
				pForm.setWithSampada("Y");
				pForm.setOffServiceID("");
				pForm.setFinalFee("0");
				pForm.setUserValue("");
				pForm.setUserkey("");
				
				FORWARD_JSP = CommonConstant.PAY_SERVICE_LABEL;
			}
			else if (param != null && param.equalsIgnoreCase("ViewReceiptHome")) {
				ArrayList serviceList = bd.getServiceList(languageLocale);
				ArrayList offServiceList = bd.getOfflineServiceList(languageLocale,userId);
				
				pForm.setServiceList(serviceList);
				pForm.setOfflineServiceList(offServiceList);
				pForm.setRefAmount("");
				pForm.setRefID("");
				pForm.setActionName("");
				pForm.setPayableAmt("");
				pForm.setServiceID("");
				pForm.setWithSampada("Y");
				pForm.setWithService("Y");
				pForm.setPaymentView(new ArrayList());
				pForm.setOffServiceID("");
				pForm.setFinalFee("0");
				pForm.setUserValue("");
				pForm.setUserkey("");
				pForm.setPaidAmt("");
				pForm.setPaymentID("");
				pForm.setServiceName("");
				pForm.setPaymentMode("");
				pForm.setPaymentDate("");
				pForm.setUserName("");
				pForm.setFromDate("");
				pForm.setToDate("");
				pForm.setPaymentView(new ArrayList());
				FORWARD_JSP = CommonConstant.VIEW_RECEIPT_LABEL;
			}
			else if (param != null && param.equalsIgnoreCase("ConsumeReceiptHome")) {
				
				ArrayList offServiceList = bd.getOfflineServiceList(languageLocale,userId);
				pForm.setOfflineServiceList(offServiceList);
				pForm.setRefAmount("");
				pForm.setRefID("");
				pForm.setActionName("");
				pForm.setPayableAmt("");
				pForm.setServiceID("");
				pForm.setWithSampada("Y");
				pForm.setWithService("Y");
				pForm.setPaymentView(new ArrayList());
				pForm.setOffServiceID("");
				pForm.setFinalFee("0");
				pForm.setUserValue("");
				pForm.setUserkey("");
				pForm.setPaidAmt("");
				pForm.setPaymentID("");
				pForm.setServiceName("");
				pForm.setPaymentMode("");
				pForm.setPaymentDate("");
				pForm.setUserName("");
				pForm.setFromDate("");
				pForm.setToDate("");
				pForm.setPaymentID("");
				pForm.setPaymentView(new ArrayList());
				FORWARD_JSP = CommonConstant.CONSUME_RECEIPT_LABEL;
			}
			if (pForm.getActionName() != null
					&& "VALIDATE_REFID".equalsIgnoreCase(pForm.getActionName())) {
				try {
					String list[] = bd.validateRefID(pForm.getRefID(), pForm
							.getServiceID());
					
					pForm.setPayableAmt(BigDecimal.valueOf(
							Float.parseFloat(list[2]))
							.toPlainString());
					// pForm.setUniqueID(list[3]);
				} catch (Exception e) {
					String msg = e.getMessage();
					logger.error(" Exception at validateRefID in action " + e);
					if (msg.equalsIgnoreCase("10001")) {
						pForm.setPayableAmt(null);
						if (languageLocale.equalsIgnoreCase("en"))
							request.setAttribute(CommonConstant.FAILURE_MSG,
									"Invalid Reference ID!!");
						else
							request.setAttribute(CommonConstant.FAILURE_MSG,
									"अमान्य संदर्भ आईडी!");
						// FORWARD_JSP=CommonConstant.PAY_SERVICE_LABEL;
					}
					if (msg.equalsIgnoreCase("10002")) {

						if (languageLocale.equalsIgnoreCase("en"))
							request
									.setAttribute(CommonConstant.FAILURE_MSG,
											"Payment has already been completed for this reference ID. ");
						else
							request
							.setAttribute(CommonConstant.FAILURE_MSG,
									"भुगतान पहले से ही इस संदर्भ आईडी के लिए पूरा हो चुका है।");
						// FORWARD_JSP=CommonConstant.PAY_SERVICE_LABEL;
					}
					if (msg.equalsIgnoreCase("10003")) {
						pForm.setPayableAmt(null);
						if (languageLocale.equalsIgnoreCase("en"))
							request.setAttribute(CommonConstant.FAILURE_MSG,"No Record Found!!");
						else
							request.setAttribute(CommonConstant.FAILURE_MSG,"कोई रिकॉर्ड नहीं मिला!!");
					}
					// TODO: handle exception
				}
				FORWARD_JSP = CommonConstant.PAY_SERVICE_LABEL;
			}
			if (pForm.getActionName() != null
					&& "VALIDATE_REFID_VIEW".equalsIgnoreCase(pForm.getActionName())) {
				try {
					String list[] = bd.validateRefIDView(pForm,languageLocale,userId);
				} catch (Exception e) {
					pForm.setPaymentID("");
					String msg = e.getMessage();
					logger.error(" Exception at validateRefID in action " + e);
					if (msg.equalsIgnoreCase("10001")) {

						if (languageLocale.equalsIgnoreCase("en"))
							request.setAttribute(CommonConstant.FAILURE_MSG,
									"Invalid Reference ID!!");
						else
							request.setAttribute(CommonConstant.FAILURE_MSG,
									"अमान्य संदर्भ आईडी!");
					}
					if (msg.equalsIgnoreCase("10002")) {

						if (languageLocale.equalsIgnoreCase("en"))
							request
									.setAttribute(CommonConstant.FAILURE_MSG,
											"Payment has already been completed for this reference ID. ");
						else
							request
							.setAttribute(CommonConstant.FAILURE_MSG,
									"भुगतान पहले से ही इस संदर्भ आईडी के लिए पूरा हो चुका है।");
					}
					if (msg.equalsIgnoreCase("10003")) {

						if (languageLocale.equalsIgnoreCase("en"))
							request
									.setAttribute(CommonConstant.FAILURE_MSG,
											"No Record Found!!");
						else
							request
							.setAttribute(CommonConstant.FAILURE_MSG,
									"कोई रिकॉर्ड नहीं मिला!!");
					}
				}
				FORWARD_JSP = CommonConstant.VIEW_RECEIPT_LABEL;
			}
			if (pForm.getActionName() != null
					&& "PAYMENT_SERVICES_RESET".equalsIgnoreCase(pForm.getActionName())) {
				pForm.setRefID("");
				pForm.setPayableAmt(null);
				FORWARD_JSP = CommonConstant.PAY_SERVICE_LABEL;
			}
			if (pForm.getActionName() != null
					&& "PAYMENT_SERVICES_VIEW_RESET".equalsIgnoreCase(pForm.getActionName())) {
				pForm.setRefID("");
				pForm.setPaymentID(null);
				FORWARD_JSP = CommonConstant.VIEW_RECEIPT_LABEL;
			}
			if (pForm.getActionName() != null
					&& "GENERATE_RECEIPT".equalsIgnoreCase(pForm.getActionName())) {
				try {
				logger.debug("GENERATE_RECEIPT entered");
				JrxmlExportUtility export = new JrxmlExportUtility();
				PropertiesFileReader prop = PropertiesFileReader
						.getInstance("resources.igrs");

				String path = prop.getValue("pdf.location");
				String reportPath = path.concat(CommonConstant.USER_GENERATED_PAYMENT_RECEIPT);
				HashMap jasperMap = new HashMap();
				jasperMap.put("ServiceID", pForm.getServiceID());
				jasperMap.put("refID", pForm.getRefID());
				jasperMap.put("lang", languageLocale);
				jasperMap.put("MOP", pForm.getPaymentMode());
				jasperMap.put("Amount", pForm.getPaidAmt());
				jasperMap.put("Date", pForm.getPaymentDate());
				jasperMap.put("UserID", pForm.getUserName());
				jasperMap.put("path", path);
				jasperMap.put("ptid", pForm.getPaymentID());	
							/*
				 * if("en".equalsIgnoreCase(language))
				 * export.generatePDF(jasperMap, reportPath,
				 * "RegistrationReport", response); else
				 */
				export.getJasperPdfviewerFromImg(jasperMap, reportPath,
						pForm.getPaymentID(), response, path, "P");

				logger.debug("GENERATE_RECEIPT_REPORT exit" );
				} catch (Exception e) {
					
				}
				FORWARD_JSP = CommonConstant.VIEW_RECEIPT_LABEL;
			}
			if (pForm.getActionName() != null
					&& "GENERATE_RECEIPT_OFF".equalsIgnoreCase(pForm.getActionName())) {
				try {
				logger.debug("GENERATE_RECEIPT_OFF entered");
				JrxmlExportUtility export = new JrxmlExportUtility();
				PropertiesFileReader prop = PropertiesFileReader
						.getInstance("resources.igrs");

				String path = prop.getValue("pdf.location");
				String reportPath = path
						.concat(CommonConstant.USER_GENERATED_PAYMENT_RECEIPT_OFFLINE);
				HashMap jasperMap = new HashMap();
				jasperMap.put("ServiceID", pForm.getOfflineRefID());
				jasperMap.put("lang", languageLocale);
				jasperMap.put("path", path);
				export.getJasperPdfviewerFromImg(jasperMap, reportPath,
						pForm.getOfflineRefID(), response, path, "P");

				logger.debug("GENERATE_RECEIPT_REPORT exit" );
				} catch (Exception e) {
					
				}
				FORWARD_JSP = CommonConstant.VIEW_RECEIPT_LABEL;
			}
			if (pForm.getActionName() != null
					&& "PAYMENT_NOW_ACTION".equalsIgnoreCase(pForm.getActionName())) {
				try {
					String parentKey = null;
					ArrayList payList = bd.getPaymentDetails(pForm
							.getServiceID());
					if (payList != null && payList.size() > 0) {
						parentKey = bd.insertPaymentDetls(pForm);
						if (parentKey == null || parentKey.isEmpty())
							throw new Exception("10001");
						else
							pForm.setUniqueID(parentKey);
					} else {
						throw new Exception("10001");

					}
					String type1 = bd.gettype(userId);
					logger.debug(type1);

					if ("3".equalsIgnoreCase(type1)
							|| "4".equalsIgnoreCase(type1)
							|| "7".equalsIgnoreCase(type1)
							|| "5".equalsIgnoreCase(type1)) {
						ArrayList spdetails = bd.getDetails(userId, pDTO);

						if (spdetails != null) {
							logger.debug(pDTO.getDistrictid());
							logger.debug(pDTO.getDistrictname());

							request.setAttribute("parentDistrictId", pDTO
									.getDistrictid());

							request.setAttribute("parentDistrictName", pDTO
									.getDistrictname());
							request.setAttribute("parentOfficeId", "NA");
							request.setAttribute("parentOfficeName", "NA");
							request.setAttribute("parentReferenceId", pForm
									.getRefID());
						}
					}

					if ("2".equalsIgnoreCase(type1)) {
						String txnId = pForm.getRefID();
						ArrayList rudetails = bd.getruDetails(userId, pDTO,
								txnId, languageLocale);

						if (rudetails != null) {
							logger.debug(pDTO.getDistrictid());
							logger.debug(pDTO.getDistrictname());

							request.setAttribute("parentDistrictId", pDTO
									.getDistrictid());

							request.setAttribute("parentDistrictName", pDTO
									.getDistrictname());
							request.setAttribute("parentOfficeId", "NA");
							request.setAttribute("parentOfficeName", "NA");
							request.setAttribute("parentReferenceId", pForm
									.getRefID());
						}

					}
					logger
							.debug("inside if insrt-----of else condition---means record found");
					ArrayList list = (ArrayList) payList.get(0);

					DecimalFormat myformatter = new DecimalFormat(
							"############");
					request.setAttribute("parentModName", list.get(7)
							.toString());
					request.setAttribute("parentFunName", list.get(8)
							.toString());
					request.setAttribute("parentFunId", list.get(9).toString());
					request.setAttribute("parentAmount", myformatter
							.format(Double.parseDouble(pForm.getPayableAmt())));
					request.setAttribute("parentTable", list.get(0).toString());
					request.setAttribute("parentKey", pForm.getUniqueID());

					request.setAttribute("forwardPath", "./PaymentService.do?fwdName=challanCommon&TRFS=NGI");

					/*
					 * request .setAttribute("forwardPath",
					 * "./EstampDutyCalculation.do?TRFS=NGI");
					 */
					request.setAttribute("parentColumnName", list.get(4)
							.toString());
					 request.setAttribute("formName", "payServiceForm"); 

					logger.debug("just before redirection");
					FORWARD_JSP = CommonConstant.PAYMENT_PAGE;
				} catch (Exception e) {
					String msg = e.getMessage();
					logger.error(" Exception at validateRefID in action " + e);
					if (msg.equalsIgnoreCase("10001")) {

						if (languageLocale.equalsIgnoreCase("en"))
							request
									.setAttribute(CommonConstant.FAILURE_MSG,
											"The Payment cannot me initiated for this Activity");
						else
							request
							.setAttribute(CommonConstant.FAILURE_MSG,
									"भुगतान इस गतिविधि के लिए शुरू नहीं कर सकते हैं।");
						FORWARD_JSP = CommonConstant.PAY_SERVICE_LABEL;
					}
					// TODO: handle exception
				}
			}
			if (pForm.getActionName() != null
					&& "OFFLINE_SERVICE".equalsIgnoreCase(pForm.getActionName())) {
				pForm.setWithSampada("N");
				for(PaymentServiceDTO pto: pForm.getOfflineServiceList()){
					if(pForm.getOffServiceID().equalsIgnoreCase(pto.getOffServiceID())){
						if(pto.getUserEnterableParam()!=null){
						pForm.setUserkey(pto.getUserEnterableParam());										
						}
						pForm.setServiceID(pto.getOffServiceID());
						pForm.setFinalFee(pto.getFees());
						pForm.setUserValue("");
						pDTO.setServiceDesc(pto.getServiceDesc());
						pForm.setCaseNo("");
						pForm.setPartyName("");
					    pForm.setfYear("");
					    pForm.settYear("");
						
					}
				}
				FORWARD_JSP = CommonConstant.PAY_SERVICE_LABEL;
			}
			if (pForm.getActionName() != null
					&& "FETCH_REFERENCE_CONSUME".equalsIgnoreCase(pForm.getActionName())) {
				for(PaymentServiceDTO pto: pForm.getOfflineServiceList()){
					if(pForm.getOffServiceID().equalsIgnoreCase(pto.getOffServiceID()) || "".equalsIgnoreCase(pForm.getOffServiceID())){
						if(pto.getReferenceName()!=null && !"".equalsIgnoreCase(pForm.getOffServiceID())){
						pForm.setUserkey(pto.getReferenceName());										
						}else{
						pForm.setUserkey("");
						}
						pForm.setUserValue("");
						pForm.setPaymentID("");
						pForm.setRefID("");
						pDTO.setServiceDesc(pto.getServiceDesc());
					}
				}
				FORWARD_JSP = CommonConstant.CONSUME_RECEIPT_LABEL;
			}
			if (pForm.getActionName() != null
					&& "VALIDATE_CONSUME_REFID".equalsIgnoreCase(pForm.getActionName())) {
				try{	
				boolean isValid=bd.validateConsumedReff(pForm, languageLocale);
				
				}catch (Exception e) {
					// TODO: handle exception
					String msg = e.getMessage();
					logger.error(" Exception at validateConsumedReff in action " + e);
					if (msg.equalsIgnoreCase("10003")) {

						if (languageLocale.equalsIgnoreCase("en"))
							request.setAttribute(CommonConstant.FAILURE_MSG_OFF,
									"Invalid Reference ID!!");
						else
							request.setAttribute(CommonConstant.FAILURE_MSG_OFF,
									"अमान्य संदर्भ आईडी!");
						// FORWARD_JSP=CommonConstant.PAY_SERVICE_LABEL;
					}
					if (msg.equalsIgnoreCase("10004")) {

						if (languageLocale.equalsIgnoreCase("en"))
							request.setAttribute(CommonConstant.FAILURE_MSG_OFF,
									"Payment has not been made against the Reference ID. Please make payment");
						else
							request.setAttribute(CommonConstant.FAILURE_MSG_OFF,
									"संदर्भ आईडी के खिलाफ भुगतान नहीं किया गया है| \n कृपया भुगतान करें");
						// FORWARD_JSP=CommonConstant.PAY_SERVICE_LABEL;
					}
				}
				
				FORWARD_JSP = CommonConstant.CONSUME_RECEIPT_LABEL;
			}
			if (pForm.getActionName() != null
					&& "OFFLINE_PAYMENT_INIT".equalsIgnoreCase(pForm.getActionName())) {
				try{	
				String referenceID= bd.insertOfflinePaymentDetls(pForm,userId);
			
				if(referenceID!=null && !referenceID.isEmpty()){
					//proceed to Payment Pages
					String type1 = bd.gettype(userId);
					logger.debug(type1);

					if ("3".equalsIgnoreCase(type1)
							|| "4".equalsIgnoreCase(type1)
							|| "7".equalsIgnoreCase(type1)
							|| "5".equalsIgnoreCase(type1)) {
						ArrayList spdetails = bd.getDetails(userId, pDTO);

						if (spdetails != null) {
							logger.debug(pDTO.getDistrictid());
							logger.debug(pDTO.getDistrictname());

							request.setAttribute("parentDistrictId", pDTO
									.getDistrictid());

							request.setAttribute("parentDistrictName", pDTO
									.getDistrictname());
							request.setAttribute("parentOfficeId", "NA");
							request.setAttribute("parentOfficeName", "NA");
							request.setAttribute("parentReferenceId", pForm
									.getRefID());
						}
					}

					if ("2".equalsIgnoreCase(type1)) {
						String txnId = pForm.getRefID();
						ArrayList rudetails = bd.getruDetails(userId, pDTO,
								txnId, languageLocale);

						if (rudetails != null) {
							logger.debug(pDTO.getDistrictid());
							logger.debug(pDTO.getDistrictname());

							request.setAttribute("parentDistrictId", pDTO
									.getDistrictid());

							request.setAttribute("parentDistrictName", pDTO
									.getDistrictname());
							request.setAttribute("parentOfficeId", "NA");
							request.setAttribute("parentOfficeName", "NA");
							request.setAttribute("parentReferenceId", pForm
									.getRefID());
						}

					}
					String parentModName=null,parentFunId=null,serviceId=null;
					logger
							.debug("inside if insrt-----of else condition---means record found");
					//ArrayList list = (ArrayList) payList.get(0);
					for(PaymentServiceDTO pto: pForm.getOfflineServiceList()){
						if(pForm.getOffServiceID().equalsIgnoreCase(pto.getOffServiceID())){

							parentModName=pto.getName();
							parentFunId=pto.getFunID();
							serviceId=pto.getOffServiceID();
							
						}
					}
					DecimalFormat myformatter = new DecimalFormat(
							"############");
					request.setAttribute("parentModName", parentModName);
					request.setAttribute("parentFunName", "OFFLINE_SAMPADA");
					request.setAttribute("parentFunId", parentFunId);
					request.setAttribute("parentAmount", myformatter
							.format(Double.parseDouble(pForm.getFinalFee())));
					request.setAttribute("parentTable", "IGRS_OFFLINE_PAYMENT_DETLS");
					request.setAttribute("parentKey", referenceID);
					request.setAttribute("parentFunId", parentFunId);
					request.setAttribute("serviceId", serviceId);

					request.setAttribute("forwardPath", "./PaymentService.do?fwdName=challanCommon&TRFS=NGI");

					/*
					 * request .setAttribute("forwardPath",
					 * "./EstampDutyCalculation.do?TRFS=NGI");
					 */
					request.setAttribute("parentColumnName", "PAYMENT_REF_ID");
					 request.setAttribute("formName", "payServiceForm"); 

					logger.debug("just before redirection");
					FORWARD_JSP = CommonConstant.PAYMENT_PAGE;
				}else{
					if (languageLocale.equalsIgnoreCase("en"))
						request
								.setAttribute(CommonConstant.FAILURE_MSG_OFF,
										"The Payment cannot me initiated for this Activity");
					else
						request
						.setAttribute(CommonConstant.FAILURE_MSG_OFF,
								"भुगतान इस गतिविधि के लिए शुरू नहीं कर सकते हैं।");
					FORWARD_JSP = CommonConstant.PAY_SERVICE_LABEL;
				}
			} catch (Exception e) {
				String msg = e.getMessage();
				logger.error(" Exception at validateRefID in action " + e);
				if (msg.equalsIgnoreCase("10001")) {

					if (languageLocale.equalsIgnoreCase("en"))
						request.setAttribute(CommonConstant.FAILURE_MSG_OFF,
								"Invalid Reference ID!!");
					else
						request.setAttribute(CommonConstant.FAILURE_MSG_OFF,
								"अमान्य संदर्भ आईडी!");
					// FORWARD_JSP=CommonConstant.PAY_SERVICE_LABEL;
				}
				FORWARD_JSP = CommonConstant.PAY_SERVICE_LABEL;
			}
				
			}
			if (pForm.getActionName() != null
					&& "VIEW_OFFLINE_PAYMENT".equalsIgnoreCase(pForm.getActionName())) {
				if(pForm.getWithService().equalsIgnoreCase("Y"))
				{
					pForm.setFromDate("");
					pForm.setToDate("");
				}else{
					pForm.setOffServiceID("");
				}
				pForm.setOfflineRefID("");
				try{
			ArrayList viewList=bd.getOfflinePaymentList(languageLocale, pForm.getOffServiceID(), pForm.getFromDate(), pForm.getToDate(),userId,officeId);
			
			pForm.setPaymentView(viewList);
				}catch(Exception e){
					String noOfDays=null;
					pForm.setPaymentView(new ArrayList<PaymentServiceDTO>());
					String msg=e.getMessage();
					noOfDays=bd.getNumberOfDays();
					int numberOfDays = Integer.parseInt(noOfDays);
					
					//int dateDiff=bd.getDifferenceDays(pForm.getFromDate(), pForm.getToDate());
				    if (msg.equalsIgnoreCase("10004")) {
						
                        if (languageLocale.equalsIgnoreCase("en"))
							request.setAttribute(CommonConstant.FAILURE_MSG_OFF,
									"Please select duration of "+ noOfDays + " days or less than that.");
						else
							request.setAttribute(CommonConstant.FAILURE_MSG_OFF,
									"सर्च की अवधी "+ noOfDays +" दिन या उस से कम की रखें।");
						// FORWARD_JSP=CommonConstant.PAY_SERVICE_LABEL;
					
					}
					if (msg.equalsIgnoreCase("10003")) {

						if (languageLocale.equalsIgnoreCase("en"))
							request
									.setAttribute(CommonConstant.FAILURE_MSG_OFF,
											"No Record Found!!");
						else
							request
							.setAttribute(CommonConstant.FAILURE_MSG_OFF,
									"कोई रिकॉर्ड नहीं मिला!!");
						// FORWARD_JSP=CommonConstant.PAY_SERVICE_LABEL;
					}
				}
				FORWARD_JSP = CommonConstant.VIEW_RECEIPT_LABEL;
			}
			if (pForm.getActionName() != null
					&& "CONSUME_PAYMENT".equalsIgnoreCase(pForm.getActionName())) {
				
				//START | outside sampada service consumption status check by santosh
				boolean boo = false;			
				boolean isValid = false;
				PaymentServiceAction paymentAction = new PaymentServiceAction();
				synchronized (paymentAction) {
					
					try{	
						 isValid=bd.validateConsumedReff(pForm, languageLocale);
						
						}catch (Exception e) {
							// TODO: handle exception
							String msg = e.getMessage();
							logger.error(" Exception at validateConsumedReff in action " + e);
							if (msg.equalsIgnoreCase("10003")) {

								if (languageLocale.equalsIgnoreCase("en"))
									request.setAttribute(CommonConstant.FAILURE_MSG_OFF,
											"Invalid Reference ID!!");
								else
									request.setAttribute(CommonConstant.FAILURE_MSG_OFF,
											"अमान्य संदर्भ आईडी!");
								// FORWARD_JSP=CommonConstant.PAY_SERVICE_LABEL;
							}
							if (msg.equalsIgnoreCase("10004")) {

								if (languageLocale.equalsIgnoreCase("en"))
									request.setAttribute(CommonConstant.FAILURE_MSG_OFF,
											"Payment has not been made against the Reference ID. Please make payment");
								else
									request.setAttribute(CommonConstant.FAILURE_MSG_OFF,
											"संदर्भ आईडी के खिलाफ भुगतान नहीं किया गया है| \n कृपया भुगतान करें");
								// FORWARD_JSP=CommonConstant.PAY_SERVICE_LABEL;
							}
						}
					
					if(!isValid){
						 // START | to add case number and party name for service id 105 & 106 by santosh
						// START | to add case number and party name for service id 114 by SANJEEV
				    	 if("105".equalsIgnoreCase(pForm.getOffServiceID()) || "114".equalsIgnoreCase(pForm.getOffServiceID()) || "106".equalsIgnoreCase(pForm.getOffServiceID())){
				    		 if(null!=pForm.getOfflineRefValue() && !"".equalsIgnoreCase(pForm.getOfflineRefValue())){
				    			 pForm.setUserValue(pForm.getOfflineRefValue()); 
				    		 }
				    	 }
				    	// END | to add case number and party name for service id 114 by SANJEEV
				    	// END | to add case number and party name for service id 105 & 106 by santosh
						boo=bd.updateConsume(pForm.getRefID(), pForm.getUserValue(), userId, officeId);
					}else if(isValid){
						ActionMessages message = new ActionMessages();
						message.add("message", new ActionMessage("payment.outside.sampada.consume.status"));
						saveMessages(request,message);
						return mapping.findForward("consumeHome");
					}
				}
				
				//END | outside sampada service consumption status check by santosh
				
				if(boo){
					if(pForm.getUserkey()!=null && !pForm.getUserkey().equalsIgnoreCase("")){
						if (languageLocale.equalsIgnoreCase("en"))
							request.setAttribute(CommonConstant.SUCCESS_MSG,
									"Payment Receipt with reference ID "+pForm.getRefID()+" has been consumed against the "+pForm.getUserkey()+" :"+pForm.getUserValue());
						else
							request.setAttribute(CommonConstant.SUCCESS_MSG,
									"भुगतान रसीद संख्या "+pForm.getRefID()+" का उपयोग "+pForm.getUserkey()+" :"+pForm.getUserValue()+" के खिलाफ हो गया है |");
						
					}else{
						if (languageLocale.equalsIgnoreCase("en"))
							request.setAttribute(CommonConstant.SUCCESS_MSG,
									"Payment Receipt with reference ID "+pForm.getRefID()+" has been consumed.");
						else
							request.setAttribute(CommonConstant.SUCCESS_MSG,
									"भुगतान रसीद संख्या "+pForm.getRefID()+" का उपयोग  किया गया है");
					
					}
				}else{

					if(pForm.getUserkey()!=null && !pForm.getUserkey().equalsIgnoreCase("")){
						if (languageLocale.equalsIgnoreCase("en"))
							request.setAttribute(CommonConstant.FAILURE_MSG,
									"Payment Receipt with reference ID "+pForm.getRefID()+" has not been consumed against the "+pForm.getUserkey()+" :"+pForm.getUserValue());
						else
							request.setAttribute(CommonConstant.FAILURE_MSG,
									"भुगतान रसीद संख्या "+pForm.getRefID()+" का उपयोग "+pForm.getUserkey()+" :"+pForm.getUserValue()+" के खिलाफ नहीं हो सका |");
						
					}else{
						if (languageLocale.equalsIgnoreCase("en"))
							request.setAttribute(CommonConstant.FAILURE_MSG,
									"Payment Receipt with reference ID "+pForm.getRefID()+" has not been been consumed.");
						else
							request.setAttribute(CommonConstant.FAILURE_MSG,
									"भुगतान रसीद संख्या "+pForm.getRefID()+" का उपयोग नहीं हो सका  है");
					
					}
				
				}
					
						
				
				FORWARD_JSP = CommonConstant.CONSUME_RECEIPT_VIEW;
			}
			String refID = (String) request.getParameter("referenceID");
			String funcID = (String) request.getParameter("payFuncID");
			String fwdName = (String) request.getParameter("fwdName");
			String payableAmt = (String) request.getParameter("payableAmt");

			if (fwdName != null
					&& fwdName.equalsIgnoreCase("paymentCommonReceipt")) {
				if (refID != null && funcID != null) {

					ArrayList payList = bd.getPaymentRecord(refID, funcID);
					if (payList.size() > 0) {
						ArrayList subList = (ArrayList) payList.get(0);
						if (languageLocale.equalsIgnoreCase("en"))
						pForm.setPayMode(subList.get(0).toString());
						else
					    pForm.setPayMode(subList.get(5).toString());
						pForm.setPaidAmt(subList.get(1).toString());
						pForm.setPayDate(subList.get(2).toString());
						pForm.setTxnID(subList.get(3).toString());
						pForm.setRefID(refID);
					} else {

						if (languageLocale.equalsIgnoreCase("en"))
							//request.setAttribute(CommonConstant.FAILURE_MSG,"No Cashless Payment has been made against "+ refID);
							request.setAttribute(CommonConstant.FAILURE_MSG," Verify the payment if it is required "+ refID);
							
						else
							//request.setAttribute(CommonConstant.FAILURE_MSG,"à¤•à¥ˆà¤¶à¤²à¥‡à¤¸ à¤­à¥�à¤—à¤¤à¤¾à¤¨ à¤‡à¤¸ à¤—à¤¤à¤¿à¤µà¤¿à¤§à¤¿ à¤•à¥‡ à¤²à¤¿à¤� à¤¶à¥�à¤°à¥‚ à¤¨à¤¹à¥€à¤‚ à¤•à¤° à¤¸à¤•à¤¤à¥‡ à¤¹à¥ˆà¤‚à¥¤");
							request.setAttribute(CommonConstant.FAILURE_MSG,"यदि भुगतान किया गया हो तो जांचें  "+refID);
						FORWARD_JSP = CommonConstant.PAY_SERVICE_LABEL;

					}
					FORWARD_JSP = CommonConstant.COMMON_PAYMENT_PAGE;
					return mapping.findForward(FORWARD_JSP);
				}
			}

			
			if (fwdName != null
					&& fwdName.equalsIgnoreCase("paymentModuleInsertion")) {
				if (refID != null && funcID != null && payableAmt != null) {
					try{
					boolean insertionDone = bd.insertPayable(refID, funcID,
							payableAmt, userId);
					if (insertionDone) {
						pForm.setParentModRefID(refID);
						pForm.setParentModFunID(funcID);
						pForm.setParentModAmount(payableAmt);
						
						logger.debug("GENERATE_RECEIPT_REPORT exit" );
						
						if (languageLocale.equalsIgnoreCase("en"))
							request
									.setAttribute(
											CommonConstant.SUCCESS_MSG,
											"Your payment for "+refID+" was Initiated successfully . \n Proceed to Pay via Payment of Services module from your login.");
						else
							request
							.setAttribute(
									CommonConstant.SUCCESS_MSG,
									"रसीद सफलतापूर्वक डाउनलोड हो चुकि है।  \n भुगतान करने के लिए सेवा के भुगतान मॉड्यूल से आगे बढ़ें |");


					} else {

						if (languageLocale.equalsIgnoreCase("en"))
							request
									.setAttribute(CommonConstant.FAILURE_MSG,
											"Receipt could not be generated. Please try again!!");
						else
							request
							.setAttribute(CommonConstant.FAILURE_MSG,
									"रसीद उत्पन्न नहीं हो पाई है। कृपया पुनः प्रयास करें!!");
					}
				}catch(Exception e){
					String  msg=e.getMessage();
					if (msg.equalsIgnoreCase("10003")) {

						if (languageLocale.equalsIgnoreCase("en"))
							request
									.setAttribute(CommonConstant.FAILURE_MSG,
											"Receipt of zero amount cannot be downloaded");
						else
							request
							.setAttribute(CommonConstant.FAILURE_MSG,
									"शून्य राशि की रसीद डाउनलोड नहीं हो सकती है।");
						// FORWARD_JSP=CommonConstant.PAY_SERVICE_LABEL;
					}
				}
				}
				FORWARD_JSP = CommonConstant.COMMON_PAYMENT_INIT_PAGE;
			}
			if (fwdName != null
					&& fwdName.equalsIgnoreCase("paymentReceiptDownload")) {

				// code to download PDF
				try{
				logger.debug("GENERATE_RECEIPT entered");
				JrxmlExportUtility export = new JrxmlExportUtility();
				PropertiesFileReader prop = PropertiesFileReader
						.getInstance("resources.igrs");

				String path = prop.getValue("pdf.location");
				String reportPath = path
						.concat(CommonConstant.OFFICER_GENERATED_PAYMENT_RECEIPT);
				HashMap jasperMap = new HashMap();
				jasperMap.put("ServiceID", pForm.getParentModFunID());
				jasperMap.put("refID", pForm.getParentModRefID());
				jasperMap.put("lang", languageLocale);
				jasperMap.put("Amount", pForm.getParentModAmount());
				jasperMap.put("UserID", userId);
				jasperMap.put("path", path);
				jasperMap.put("OfficeId", officeId);
				
				export.getJasperPdfviewerFromImg(jasperMap, reportPath,
						pForm.getParentModRefID(), response, path, "P");
				
			}catch(Exception e){
				e.printStackTrace();
				
			}
			}if (fwdName != null
					&& fwdName.equalsIgnoreCase("challanCommon")) {
								
					if (languageLocale.equalsIgnoreCase("en"))
						request
								.setAttribute(CommonConstant.SUCCESS_MSG,
										"The Payment was successful.");
					else
						request
						.setAttribute(CommonConstant.SUCCESS_MSG,
								"भुगतान सफल रहा ।");
	
			
				
				FORWARD_JSP = CommonConstant.COMMON_PAYMENT_REDIRECT;
			}if (fwdName != null
					&& fwdName.equalsIgnoreCase("standaloneModuleInsert")) {
				logger.debug("standaloneModuleInsert insertion begin..........");
				 refID = (String) request.getAttribute("referenceID");
				 funcID = (String) request.getAttribute("payFuncID");
				 payableAmt = (String) request.getAttribute("payableAmt");

				if (refID != null && funcID != null && payableAmt != null) {
					try{
					boolean insertionDone = bd.insertPayable(refID, funcID,
							payableAmt, userId);
					if (insertionDone) {
						pForm.setParentModRefID(refID);
						pForm.setParentModFunID(funcID);
						pForm.setParentModAmount(payableAmt);
						
						logger.debug("GENERATE_RECEIPT_REPORT exit" );
						
						if (languageLocale.equalsIgnoreCase("en"))
							request
									.setAttribute(
											CommonConstant.SUCCESS_MSG,
											"Your payment for "+refID+" was Initiated successfully . \n Proceed to Pay via Payment of Services module from your login.");
						else
							request
							.setAttribute(
									CommonConstant.SUCCESS_MSG,
									"रसीद सफलतापूर्वक डाउनलोड हो चुकि है।  \n भुगतान करने के लिए सेवा के भुगतान मॉड्यूल से आगे बढ़ें |");

					} else {

						if (languageLocale.equalsIgnoreCase("en"))
							request
									.setAttribute(CommonConstant.FAILURE_MSG,
											"Receipt could not be generated. Please try again!!");
						else
							request
							.setAttribute(CommonConstant.FAILURE_MSG,
									"रसीद उत्पन्न नहीं हो पाई है। कृपया पुनः प्रयास करें!!");
					}
				}catch(Exception e){
					String  msg=e.getMessage();
					if (msg.equalsIgnoreCase("10003")) {

						if (languageLocale.equalsIgnoreCase("en"))
							request
									.setAttribute(CommonConstant.FAILURE_MSG,
											"Receipt of zero amount cannot be downloaded");
						else
							request
							.setAttribute(CommonConstant.FAILURE_MSG,
									"शून्य राशि की रसीद डाउनलोड नहीं हो सकती है।");
						// FORWARD_JSP=CommonConstant.PAY_SERVICE_LABEL;
					}
				}
				}
				FORWARD_JSP = CommonConstant.COMMON_PAYMENT_INIT_PAGE;
			}
			}
		// TODO Auto-generated method stub
		return mapping.findForward(FORWARD_JSP);
	}
	
	

}
