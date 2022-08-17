package com.wipro.igrs.DeliveryOfDocuments.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.itextpdf.text.pdf.PdfReader;
import com.newgen.burning.BurningImageAndText;
import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.ODServerDetails;
import com.newgen.burning.PresentationSeal;
import com.newgen.burning.Presenter;

import com.wipro.igrs.DeliveryOfDocuments.bd.DeliveryOfDocumentsBD;
import com.wipro.igrs.DeliveryOfDocuments.dto.DeliveryOfDocumentsDTO;
import com.wipro.igrs.DeliveryOfDocuments.form.DeliveryOfDocumentsForm;
import com.wipro.igrs.RegCompMaker.bd.RegCompMkrBD;
import com.wipro.igrs.Seals.bd.SealsBD;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.clr.services.CLRResponse;
import com.wipro.igrs.clr.services.CLRService;
import com.wipro.igrs.clr.services.PDFFormVI;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.cybertehsil.forms.util.FormVIUtil;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.rcms.bd.RevenueCaseBD;
import com.wipro.igrs.rcms.constant.RevenueCaseConstant;
import com.wipro.igrs.rcms.entity.BuyerDetails;
import com.wipro.igrs.rcms.entity.BuyerDetailsSampadaList;
import com.wipro.igrs.rcms.entity.KhasraRecord;
import com.wipro.igrs.rcms.entity.KhasraRecordList;
import com.wipro.igrs.rcms.entity.PropertyDetails;
import com.wipro.igrs.rcms.entity.RCMSDetailsSampada;
import com.wipro.igrs.rcms.entity.RCMSResponse;
import com.wipro.igrs.rcms.entity.SellerDetails;
import com.wipro.igrs.rcms.entity.SellerDetailsSampadaList;
import com.wipro.igrs.rcms.pdf.GenerateRCMSReceipt;
import com.wipro.igrs.rcms.pdf.GenerateTeritorialCourtReceipt;
import com.wipro.igrs.regCompChecker.bd.RegCompCheckerBD;
import com.wipro.igrs.regCompChecker.constant.RegCompCheckerConstant;
import com.wipro.igrs.regCompChecker.form.RegCompCheckerForm;
import com.wipro.igrs.util.GUIDGenerator;

/**
 * 
 * DeliveryOfDocuments.java <br>
 * DeliveryOfDocumentAction <br>
 * c
 * 
 * @version 1.0
 * 
 * @author <b>WIPRO JAVA TEAM</b> <br>
 *         Created on 21-June-2013 <br>
 *         Last Modified on
 */
@SuppressWarnings({"unused"})
public class DeliveryOfDocumentsAction extends BaseAction {
	private Logger logger = (Logger) Logger.getLogger(DeliveryOfDocumentsAction.class);

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

	private String forwardPage = "";

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		if (form != null) {

			DeliveryOfDocumentsForm eForm = (DeliveryOfDocumentsForm) form;
			DeliveryOfDocumentsBD dodBD = new DeliveryOfDocumentsBD();
			String userId = (String) session.getAttribute("UserId");
			String officeId = (String) session.getAttribute("loggedToOffice");
			String language = (String) session.getAttribute("languageLocale");
			eForm.setUid(userId);
			String page = (String) request.getParameter("page");
			DeliveryOfDocumentsDTO dto = eForm.getDoddto();
			String formName = eForm.getFormName();
			String actionName = eForm.getActionName();
			logger.debug("form name is----------->" + formName);
			logger.debug("Action name is---------->" + actionName);

			dto.setLanguage(language);
			dto.setSignatureChkFlag("");
			dto.setSignatureChkhand("");

			String firstPage = "";

			if (request.getParameter("pageType") != null) {
				firstPage = (String) request.getParameter("pageType");
			}
			if (!(firstPage).equalsIgnoreCase("")) {
				logger.debug("firstPage is----------->" + firstPage);
				// for SR View
				if (firstPage.equalsIgnoreCase("deliveryOfDocument")) {
					ArrayList statusList = new ArrayList();
					statusList = dodBD.getstatusList(language);
					eForm.setStatuslist(statusList);
					dto.setOwmChk("");
					dto.setValue("1");
					dto.setNoRecFound(0);
					dto.setIsrepresentative(0);
					dto.setPendingApproval(0);
					dto.setIsdestroyed(0);
					dto.setNotInsertedDestroy(0);
					dto.setPartyOrRep(0);
					dto.setIsOneMnOver(0);
					dto.setIsPartial(0);
					dto.setNotInsertedDestroyP(0);
					dto.setIsApproved(0);
					dto.setNotInsertedApprove(0);
					dto.setNotInsertedNotice(0);
					dto.setIsNoticeIssued(0);
					dto.setIsIssued(0);
					dto.setIscompletePay(0);
					dto.setIsLinkToBeShown(0);
					dto.setNotInsertedPost(0);
					dto.setIsSavedPost(0);
					dto.setSignatureChkFlag("N");
					dto.setRegNo("");
					dto.setStatusSelected("");
					dto.setSelecRegNo("");
					dto.setStatusSelectedName("");
					dto.setFromRequestDate("");
					dto.setToRequestDate("");
					dto.setRepFname("");
					dto.setRepMname("");
					dto.setRepLname("");
					dto.setRegCompDt("");
					dto.setDocStatusID("");
					dto.setDocStatusName("");
					dto.setRegCumStatus("");
					dto.setRegCumDetls(null);
					dto.setRegDetails(new ArrayList());
					dto.setPartyNm("");
					dto.setPartyNmDisplay("");
					dto.setTransDetails(new ArrayList());
					dto.setTransDetailsDisplay(new ArrayList());
					dto.setChckArry("");
					dto.setChckBx("");
					dto.setPayableFee("");
					dto.setAlrdyPaidFee("");
					dto.setTotalFee("");
					dto.setPrimKeyPymt("");
					dto.setFlg("");
					dto.setRegNoDisplay("");
					dto.setRegComDateDisplay("");
					dto.setLastDelDateDisplay("");
					dto.setLastDueDateDisplay("");
					dto.setStatusDisplay("");
					dto.setStatusDateDisplay("");
					dto.setUserNameDisplay("");
					dto.setOfficeNameDisplay("");
					dto.setDrNameDisplay("");
					dto.setDrOfficeDisplay("");
					dto.setDocDelToDisplay("");
					dto.setOtherNameDisplay("");
					dto.setAuthLetterNmDisplay("");
					dto.setRadioSelect("");
					dto.setFilePhoto2(null);
					dto.setDocPath("");
					dto.setNoticeRegNoDisplay("");
					dto.setNoticeRegComDateDisplay("");
					dto.setNoticeLastDelDateDisplay("");
					dto.setNoticeLastDueDateDisplay("");
					dto.setNoticeStatusDisplay("");
					dto.setNoticeStatusDateDisplay("");
					dto.setNoticeUserNameDisplay("");
					dto.setNoticeOfficeNameDisplay("");
					dto.setNoticeRemarks("");
					dto.setNoticePartyRole("");
					dto.setNoticePartyAdrs("");
					dto.setNoticePartyNm("");
					dto.setNoticecumdtls("");
					dto.setNoticeDetails(new ArrayList());
					dto.setPostlAddrs("");
					dto.setPostlDist("");
					dto.setPostlState("");
					dto.setPostlCntry("");
					dto.setPostlDocketNo("");

					request.removeAttribute("regDetails");
					eForm.setActionName("");
					eForm.setFormName("");
					formName = "";
					actionName = "";
					forwardPage = "dodsrsearch";
				}

				// for DR View
				if (firstPage.equalsIgnoreCase("drView")) {
					ArrayList statusList = new ArrayList();
					statusList = dodBD.getstatusList(language);
					eForm.setStatuslist(statusList);

					dto.setNoRecFound(0);
					dto.setIsrepresentative(0);
					dto.setPendingApproval(0);
					dto.setIsdestroyed(0);
					dto.setNotInsertedDestroy(0);
					dto.setPartyOrRep(0);
					dto.setIsOneMnOver(0);
					dto.setIsPartial(0);
					dto.setNotInsertedDestroyP(0);
					dto.setIsApproved(0);
					dto.setNotInsertedApprove(0);
					dto.setNotInsertedNotice(0);
					dto.setIsNoticeIssued(0);
					dto.setIsIssued(0);
					dto.setIscompletePay(0);
					dto.setIsLinkToBeShown(0);
					dto.setNotInsertedPost(0);
					dto.setIsSavedPost(0);
					dto.setSignatureChkFlag("N");
					dto.setRegNo("");
					dto.setStatusSelected("");
					dto.setSelecRegNo("");
					dto.setStatusSelectedName("");
					dto.setFromRequestDate("");
					dto.setToRequestDate("");
					dto.setRepFname("");
					dto.setRepMname("");
					dto.setRepLname("");
					dto.setRegCompDt("");
					dto.setDocStatusID("");
					dto.setDocStatusName("");
					dto.setRegCumStatus("");
					dto.setRegCumDetls(null);
					dto.setRegDetails(new ArrayList());
					dto.setPartyNm("");
					dto.setPartyNmDisplay("");
					dto.setTransDetails(new ArrayList());
					dto.setTransDetailsDisplay(new ArrayList());
					dto.setChckArry("");
					dto.setChckBx("");
					dto.setPayableFee("");
					dto.setAlrdyPaidFee("");
					dto.setTotalFee("");
					dto.setPrimKeyPymt("");
					dto.setFlg("");
					dto.setRegNoDisplay("");
					dto.setRegComDateDisplay("");
					dto.setLastDelDateDisplay("");
					dto.setLastDueDateDisplay("");
					dto.setStatusDisplay("");
					dto.setStatusDateDisplay("");
					dto.setUserNameDisplay("");
					dto.setOfficeNameDisplay("");
					dto.setDrNameDisplay("");
					dto.setDrOfficeDisplay("");
					dto.setDocDelToDisplay("");
					dto.setOtherNameDisplay("");
					dto.setAuthLetterNmDisplay("");
					dto.setRadioSelect("");
					dto.setFilePhoto2(null);
					dto.setDocPath("");
					dto.setNoticeRegNoDisplay("");
					dto.setNoticeRegComDateDisplay("");
					dto.setNoticeLastDelDateDisplay("");
					dto.setNoticeLastDueDateDisplay("");
					dto.setNoticeStatusDisplay("");
					dto.setNoticeStatusDateDisplay("");
					dto.setNoticeUserNameDisplay("");
					dto.setNoticeOfficeNameDisplay("");
					dto.setNoticeRemarks("");
					dto.setNoticePartyRole("");
					dto.setNoticePartyAdrs("");
					dto.setNoticePartyNm("");
					dto.setNoticecumdtls("");
					dto.setNoticeDetails(new ArrayList());
					dto.setPostlAddrs("");
					dto.setPostlDist("");
					dto.setPostlState("");
					dto.setPostlCntry("");
					dto.setPostlDocketNo("");

					request.removeAttribute("regDetails");
					eForm.setActionName("");
					eForm.setFormName("");
					formName = "";
					actionName = "";
					forwardPage = "dodDrsearch";
				}

				// Code Started For Print Status Change
				// if(firstPage.equalsIgnoreCase("Delivery of Document")){
				if (firstPage.equalsIgnoreCase("changeStatus")) {
					logger.debug("Print Status Change----------->" + firstPage);
					ArrayList statusList = new ArrayList();
					statusList = dodBD.getChangestatusList(language);
					eForm.setStatuslist(statusList);

					dto.setNoRecFound(0);
					dto.setIsrepresentative(0);
					dto.setPendingApproval(0);
					dto.setIsdestroyed(0);
					dto.setNotInsertedDestroy(0);
					dto.setPartyOrRep(0);
					dto.setIsOneMnOver(0);
					dto.setIsPartial(0);
					dto.setNotInsertedDestroyP(0);
					dto.setIsApproved(0);
					dto.setNotInsertedApprove(0);
					dto.setNotInsertedNotice(0);
					dto.setIsNoticeIssued(0);
					dto.setIsIssued(0);
					dto.setIscompletePay(0);
					dto.setIsLinkToBeShown(0);
					dto.setNotInsertedPost(0);
					dto.setIsSavedPost(0);
					dto.setSignatureChkFlag("N");
					dto.setRegNo("");
					dto.setStatusSelected("");
					dto.setSelecRegNo("");
					dto.setStatusSelectedName("");
					dto.setFromRequestDate("");
					dto.setToRequestDate("");
					dto.setRepFname("");
					dto.setRepMname("");
					dto.setRepLname("");
					dto.setRegCompDt("");
					dto.setDocStatusID("");
					dto.setDocStatusName("");
					dto.setRegCumStatus("");
					dto.setRegCumDetls(null);
					dto.setRegDetails(new ArrayList());
					dto.setPartyNm("");
					dto.setPartyNmDisplay("");
					dto.setTransDetails(new ArrayList());
					dto.setTransDetailsDisplay(new ArrayList());
					dto.setChckArry("");
					dto.setChckBx("");
					dto.setPayableFee("");
					dto.setAlrdyPaidFee("");
					dto.setTotalFee("");
					dto.setPrimKeyPymt("");
					dto.setFlg("");
					dto.setRegNoDisplay("");
					dto.setRegComDateDisplay("");
					dto.setLastDelDateDisplay("");
					dto.setLastDueDateDisplay("");
					dto.setStatusDisplay("");
					dto.setStatusDateDisplay("");
					dto.setUserNameDisplay("");
					dto.setOfficeNameDisplay("");
					dto.setDrNameDisplay("");
					dto.setDrOfficeDisplay("");
					dto.setDocDelToDisplay("");
					dto.setOtherNameDisplay("");
					dto.setAuthLetterNmDisplay("");
					dto.setRadioSelect("");
					dto.setFilePhoto2(null);
					dto.setDocPath("");
					dto.setNoticeRegNoDisplay("");
					dto.setNoticeRegComDateDisplay("");
					dto.setNoticeLastDelDateDisplay("");
					dto.setNoticeLastDueDateDisplay("");
					dto.setNoticeStatusDisplay("");
					dto.setNoticeStatusDateDisplay("");
					dto.setNoticeUserNameDisplay("");
					dto.setNoticeOfficeNameDisplay("");
					dto.setNoticeRemarks("");
					dto.setNoticePartyRole("");
					dto.setNoticePartyAdrs("");
					dto.setNoticePartyNm("");
					dto.setNoticecumdtls("");
					dto.setNoticeDetails(new ArrayList());
					dto.setPostlAddrs("");
					dto.setPostlDist("");
					dto.setPostlState("");
					dto.setPostlCntry("");
					dto.setPostlDocketNo("");
					dto.setOwmChk("");
					request.removeAttribute("regDetails");
					eForm.setActionName("");
					eForm.setFormName("");
					formName = "";
					actionName = "";
					forwardPage = "dodprcsearch";
				}

				// Code End For Print Status Change

				if (firstPage.equalsIgnoreCase("signatureOfDocument")) {
					// ArrayList statusList = new ArrayList();
					// statusList=dodBD.getstatusList(language);
					// eForm.setStatuslist(statusList);

					dto.setNoRecFound(0);
					dto.setIsrepresentative(0);
					dto.setPendingApproval(0);
					dto.setIsdestroyed(0);
					dto.setNotInsertedDestroy(0);
					dto.setPartyOrRep(0);
					dto.setIsOneMnOver(0);
					dto.setIsPartial(0);
					dto.setNotInsertedDestroyP(0);
					dto.setIsApproved(0);
					dto.setNotInsertedApprove(0);
					dto.setNotInsertedNotice(0);
					dto.setIsNoticeIssued(0);
					dto.setIsIssued(0);
					dto.setIscompletePay(0);
					dto.setIsLinkToBeShown(0);
					dto.setNotInsertedPost(0);
					dto.setIsSavedPost(0);
					dto.setSignatureChkFlag("N");
					dto.setRegNo("");
					dto.setStatusSelected("");
					dto.setSelecRegNo("");
					dto.setStatusSelectedName("");
					dto.setFromRequestDate("");
					dto.setToRequestDate("");
					dto.setRepFname("");
					dto.setRepMname("");
					dto.setRepLname("");
					dto.setRegCompDt("");
					dto.setDocStatusID("");
					dto.setDocStatusName("");
					dto.setRegCumStatus("");
					dto.setRegCumDetls(null);
					dto.setRegDetails(new ArrayList());
					dto.setPartyNm("");
					dto.setPartyNmDisplay("");
					dto.setTransDetails(new ArrayList());
					dto.setTransDetailsDisplay(new ArrayList());
					dto.setChckArry("");
					dto.setChckBx("");
					dto.setPayableFee("");
					dto.setAlrdyPaidFee("");
					dto.setTotalFee("");
					dto.setPrimKeyPymt("");
					dto.setFlg("");
					dto.setRegNoDisplay("");
					dto.setRegComDateDisplay("");
					dto.setLastDelDateDisplay("");
					dto.setLastDueDateDisplay("");
					dto.setStatusDisplay("");
					dto.setStatusDateDisplay("");
					dto.setUserNameDisplay("");
					dto.setOfficeNameDisplay("");
					dto.setDrNameDisplay("");
					dto.setDrOfficeDisplay("");
					dto.setDocDelToDisplay("");
					dto.setOtherNameDisplay("");
					dto.setAuthLetterNmDisplay("");
					dto.setRadioSelect("");
					dto.setFilePhoto2(null);
					dto.setDocPath("");
					dto.setNoticeRegNoDisplay("");
					dto.setNoticeRegComDateDisplay("");
					dto.setNoticeLastDelDateDisplay("");
					dto.setNoticeLastDueDateDisplay("");
					dto.setNoticeStatusDisplay("");
					dto.setNoticeStatusDateDisplay("");
					dto.setNoticeUserNameDisplay("");
					dto.setNoticeOfficeNameDisplay("");
					dto.setNoticeRemarks("");
					dto.setNoticePartyRole("");
					dto.setNoticePartyAdrs("");
					dto.setNoticePartyNm("");
					dto.setNoticecumdtls("");
					dto.setNoticeDetails(new ArrayList());
					dto.setPostlAddrs("");
					dto.setPostlDist("");
					dto.setPostlState("");
					dto.setPostlCntry("");
					dto.setPostlDocketNo("");

					dto.setSignStatus(dodBD.getSignStatus(language));
					request.removeAttribute("regDetails");
					eForm.setActionName("");
					eForm.setFormName("");
					formName = "";
					actionName = "";
					forwardPage = "sodSearch";
				}

			}

			if (request.getParameter("PrintNotice") != null) {

				if (request.getParameter("PrintNotice").equalsIgnoreCase("Y")) {
					String regcompno = dto.getNoticeRegNoDisplay();
					if (regcompno == "") {
						regcompno = dto.getRegNoDisplay();
					}
					logger.debug("inside print notice");
					ArrayList issueDetls = new ArrayList();
					ArrayList comp7 = new ArrayList();
					issueDetls = dodBD.getissueDetls(regcompno);
					if (issueDetls.size() > 0) {
						for (int i = 0; i < issueDetls.size(); i++) {
							comp7.add((ArrayList) issueDetls.get(i));
							if (comp7 != null && comp7.size() > 0) {
								for (int k = 0; k < comp7.size(); k++) {
									ArrayList compSub = (ArrayList) comp7.get(k);
									dto.setNoticeRegNoDisplay((String) compSub.get(0));
									dto.setNoticeRegComDateDisplay((String) compSub.get(1));
									dto.setNoticeLastDelDateDisplay((String) compSub.get(3));
									dto.setNoticeLastDueDateDisplay((String) compSub.get(4));
									if (language.equalsIgnoreCase("en"))
										dto.setNoticeStatusDisplay((String) compSub.get(2));
									else
										dto.setNoticeStatusDisplay((String) compSub.get(9));

									dto.setNoticeStatusDateDisplay((String) compSub.get(5));
									dto.setNoticeUserNameDisplay((String) compSub.get(6));
									if (language.equalsIgnoreCase("en"))
										dto.setNoticeOfficeNameDisplay((String) compSub.get(7));
									else
										dto.setNoticeOfficeNameDisplay((String) compSub.get(10));
									dto.setNoticeRemarks((String) compSub.get(8));
									dto.setIsNoticeIssued(1);
								}
							}
						}
					}

					ArrayList noticePartyDetl = new ArrayList();
					noticePartyDetl = dodBD.getpartyDetl(regcompno, language);
					dto.setNoticeDetails(noticePartyDetl);
					request.setAttribute("partyDetails", noticePartyDetl);
					formName = "";
					actionName = "";
					forwardPage = "issueNoticePrint";
				}
			}

			if (request.getParameter("paymentStatus") != null) {

				if (request.getParameter("paymentStatus").equalsIgnoreCase("P")) {
					String uniId = (String) request.getParameter("parentKey");
					if (uniId != null || uniId != "") {
						dto.setPrimKeyPymt(uniId);
					}
					String mainId = dodBD.getMainId(uniId);
					dto.setRegNoDisplay(mainId);

					ArrayList first = new ArrayList();
					ArrayList comp = new ArrayList();
					String uniqId = "";
					String amtToBePaid = "";
					String paidAmt = "";
					String pymtFlg = "";
					first = dodBD.getPayDtls(mainId);

					if (first.size() > 0) {
						for (int i = 0; i < first.size(); i++) {
							comp.add((ArrayList) first.get(i));
							if (comp != null && comp.size() > 0) {
								for (int k = 0; k < comp.size(); k++) {
									ArrayList compSub = (ArrayList) comp.get(k);
									uniqId = (String) compSub.get(0);
									amtToBePaid = (String) compSub.get(1);
									paidAmt = (String) compSub.get(2);
									pymtFlg = (String) compSub.get(3);
								}
							}
						}
					}
					double damtToBePaid = Double.parseDouble(amtToBePaid);
					double dpaidAmt = Double.parseDouble(paidAmt);
					// condition 1-------------------pending amount-------------
					if (damtToBePaid > dpaidAmt) {
						DecimalFormat myformatter = new DecimalFormat("############");
						String totfee = dto.getTotalFee();
						double dtotfee = Double.parseDouble(totfee);
						double bal = damtToBePaid - dpaidAmt;
						double alrdP = dtotfee - bal;
						String balfrmt = myformatter.format(bal);
						String alrdFrmt = myformatter.format(alrdP);
						dto.setPayableFee(balfrmt);
						dto.setAlrdyPaidFee(alrdFrmt);
						dto.setIsPartial(1);
						dto.setIsOneMnOver(1);
						ArrayList issueDetls = new ArrayList();
						issueDetls = dodBD.getissueDetls(mainId);
						if (issueDetls.size() > 0) {
							dto.setIsLinkToBeShown(1);
						}
						forwardPage = "undelivered";
					}

					// condition 2-------------------full payment, status change
					// and data insertion------
					if (damtToBePaid == dpaidAmt) {
						DecimalFormat myformatter = new DecimalFormat("############");
						String docDelTo = dto.getDocDelToDisplay();
						String regno = dto.getRegNoDisplay();
						userId = (String) session.getAttribute("UserId");
						officeId = (String) session.getAttribute("loggedToOffice");

						String totfee = dto.getTotalFee();
						double dtotfee = Double.parseDouble(totfee);
						double bal = damtToBePaid - dpaidAmt;
						double alrdP = dtotfee - bal;
						String balfrmt = myformatter.format(bal);
						String alrdFrmt = myformatter.format(alrdP);
						dto.setPayableFee(balfrmt);
						dto.setAlrdyPaidFee(alrdFrmt);
						dto.setIscompletePay(1);
						// String offid="OFC90";
						// dto.setNotInsertedDestroy(0);
						logger.debug("Inside undelivered change status method after payment----the reg no is-->>" + regno);

						String regNum = dto.getRegNoDisplay();
						String regTxnID = dodBD.getRegTxnID(regNum);
						RegCompMkrBD regmkrBD = new RegCompMkrBD();
						dto.setDeedID(regmkrBD.getDeedId(regTxnID));
						dto.setInstID(regmkrBD.getInstId(regTxnID));
						forwardPage = "undelivered";
						// boolean
						// flag=dodBD.insertPinToSmsHand(regTxnID,dto.getDeedID(),dto,userId,dto.getArr());
						/*
						 * if(flag){
						 * logger.debug("PIN SUCCESSFULLY SENT VIA SMS");
						 * //String docDelTo = dto.getDocDelToDisplay();
						 * 
						 * boolean ins=false;
						 * 
						 * String[] chckIDary= dto.getChckArry().split(",");
						 * ins=dodBD.statusDeliveredUpdate(regNum, userId,
						 * officeId, dto.getRecptName(),dto); if(ins){
						 * dto.setNotInsertedDestroy(2);
						 * 
						 * 
						 * logger.debug("STATUS HAS BEEN CHANGED TO DELIVERED");
						 * ArrayList deliveredDetls = new ArrayList(); ArrayList
						 * comp7 = new ArrayList();
						 * deliveredDetls=dodBD.getdeliveredDetls(regno); if
						 * (deliveredDetls.size()>0){ for(int i =0;
						 * i<deliveredDetls.size(); i++){
						 * comp7.add((ArrayList)deliveredDetls.get(i)); if(comp7
						 * != null && comp7.size()>0){ for (int k=0; k<
						 * comp7.size(); k++){ ArrayList compSub =
						 * (ArrayList)comp7.get(k); dto.setRegNoDisplay((String)
						 * compSub.get(0)); dto.setRegComDateDisplay((String)
						 * compSub.get(1)); dto.setLastDelDateDisplay((String)
						 * compSub.get(3)); dto.setLastDueDateDisplay((String)
						 * compSub.get(4)); if(language.equalsIgnoreCase("en"))
						 * dto.setStatusDisplay((String) compSub.get(2)); else
						 * dto.setStatusDisplay((String) compSub.get(8));
						 * 
						 * dto.setStatusDateDisplay((String) compSub.get(5));
						 * dto.setUserNameDisplay((String) compSub.get(6));
						 * if(language.equalsIgnoreCase("en"))
						 * dto.setOfficeNameDisplay((String) compSub.get(7));
						 * else dto.setOfficeNameDisplay((String)
						 * compSub.get(9)); if(language.equalsIgnoreCase("en"))
						 * dto.setDocDelToDisplay("Transacting Party"); else
						 * dto.setDocDelToDisplay("लेनदेन पार्टी");
						 * 
						 * } } } } forwardPage="deliveredConfrm"; }else{
						 * dto.setNotInsertedDestroy(1);
						 * forwardPage="undelivered";
						 * logger.debug("STATUS CHANGE FAILURE"); }
						 * 
						 * }else{ logger.debug("PIN FAILURE WHEN SENDING");
						 * dto.setNotInsertedDestroy(1);
						 * forwardPage="undelivered"; }
						 */

						/*
						 * if(docDelTo.equalsIgnoreCase("Transacting Party")){
						 * boolean ins=false;
						 * if(dto.getChckArry().contains(",")){ String[]
						 * chckIDary= dto.getChckArry().split(",");
						 * ins=dodBD.statusDeliveredUpdateP(regno, userId,
						 * officeId, dto.getRecptName()); }else{ //String ary[]
						 * = new String[1]; //ary[0]=dto.getChckArry();
						 * ins=dodBD.statusDeliveredUpdateP(regno, userId,
						 * officeId, dto.getRecptName());
						 * 
						 * }
						 * 
						 * if(ins){
						 * 
						 * ArrayList deliveredDetls = new ArrayList(); ArrayList
						 * comp7 = new ArrayList();
						 * deliveredDetls=dodBD.getdeliveredDetls(regno); if
						 * (deliveredDetls.size()>0){ for(int i =0;
						 * i<deliveredDetls.size(); i++){
						 * comp7.add((ArrayList)deliveredDetls.get(i)); if(comp7
						 * != null && comp7.size()>0){ for (int k=0; k<
						 * comp7.size(); k++){ ArrayList compSub =
						 * (ArrayList)comp7.get(k); dto.setRegNoDisplay((String)
						 * compSub.get(0)); dto.setRegComDateDisplay((String)
						 * compSub.get(1)); dto.setLastDelDateDisplay((String)
						 * compSub.get(3)); dto.setLastDueDateDisplay((String)
						 * compSub.get(4)); if(language.equalsIgnoreCase("en"))
						 * dto.setStatusDisplay((String) compSub.get(2)); else
						 * dto.setStatusDisplay((String) compSub.get(8));
						 * 
						 * dto.setStatusDateDisplay((String) compSub.get(5));
						 * dto.setUserNameDisplay((String) compSub.get(6));
						 * if(language.equalsIgnoreCase("en"))
						 * dto.setOfficeNameDisplay((String) compSub.get(7));
						 * else dto.setOfficeNameDisplay((String)
						 * compSub.get(9)); if(language.equalsIgnoreCase("en"))
						 * dto.setDocDelToDisplay("Transacting Party"); else
						 * dto.setDocDelToDisplay("लेनदेन पार्टी");
						 * 
						 * } } } } ArrayList repDetl = new ArrayList(); repDetl=
						 * dodBD.getrecpDetl(regno);
						 * dto.setTransDetailsDisplay(repDetl);
						 * request.setAttribute("repDetails", repDetl);
						 * dto.setPartyOrRep(1); forwardPage="deliveredConfrm";
						 * } else{ dto.setNotInsertedDestroy(1);
						 * forwardPage="undelivered"; } }//end of if transacting
						 * party
						 * 
						 * if(docDelTo.equalsIgnoreCase("Representative")){
						 * dto.setPartyOrRep(2); String fname =
						 * dto.getRepFname(); String mname = dto.getRepMname();
						 * String lname = dto.getRepLname(); String docName=
						 * eForm.getDocName(); String docPath =
						 * eForm.getDocPath(); boolean ins=false;
						 * ins=dodBD.statusDeliveredUpdateP1(regno, userId,
						 * officeId,fname,mname,lname,docName,docPath); if(ins){
						 * ArrayList deliveredDetls = new ArrayList(); ArrayList
						 * comp7 = new ArrayList();
						 * deliveredDetls=dodBD.getdeliveredDetls(regno); if
						 * (deliveredDetls.size()>0){ for(int i =0;
						 * i<deliveredDetls.size(); i++){
						 * comp7.add((ArrayList)deliveredDetls.get(i)); if(comp7
						 * != null && comp7.size()>0){ for (int k=0; k<
						 * comp7.size(); k++){ ArrayList compSub =
						 * (ArrayList)comp7.get(k); dto.setRegNoDisplay((String)
						 * compSub.get(0)); dto.setRegComDateDisplay((String)
						 * compSub.get(1)); dto.setLastDelDateDisplay((String)
						 * compSub.get(3)); dto.setLastDueDateDisplay((String)
						 * compSub.get(4)); if(language.equalsIgnoreCase("en"))
						 * dto.setStatusDisplay((String) compSub.get(2)); else
						 * dto.setStatusDisplay((String) compSub.get(8));
						 * dto.setStatusDateDisplay((String) compSub.get(5));
						 * dto.setUserNameDisplay((String) compSub.get(6));
						 * if(language.equalsIgnoreCase("en"))
						 * dto.setOfficeNameDisplay((String) compSub.get(7));
						 * else dto.setOfficeNameDisplay((String)
						 * compSub.get(9)); if(language.equalsIgnoreCase("en"))
						 * dto.setDocDelToDisplay("Representative"); else
						 * dto.setDocDelToDisplay("प्रतिनिधि"); } } } } String
						 * rprsntatvNm=""; String authLtrName=""; String
						 * authLtrPath=""; ArrayList rprsntatvDetls = new
						 * ArrayList(); ArrayList comp8 = new ArrayList();
						 * rprsntatvDetls=dodBD.getrprsntatvDetls(regno); if
						 * (rprsntatvDetls.size()>0){ for(int i =0;
						 * i<rprsntatvDetls.size(); i++){
						 * comp8.add((ArrayList)rprsntatvDetls.get(i)); if(comp7
						 * != null && comp8.size()>0){ for (int k=0; k<
						 * comp8.size(); k++){ ArrayList compSub =
						 * (ArrayList)comp8.get(k); rprsntatvNm = (String)
						 * compSub.get(0); authLtrName = (String)
						 * compSub.get(1); authLtrPath = (String)
						 * compSub.get(2); } } } } //dto.setIsrepresentative(1);
						 * if(language.equalsIgnoreCase("en"))
						 * dto.setDocDelToDisplay("Representative"); else
						 * dto.setDocDelToDisplay("प्रतिनिधि");
						 * dto.setOtherNameDisplay(rprsntatvNm);
						 * dto.setAuthLetterNmDisplay(authLtrName);
						 * dto.setDocPath(authLtrPath+"/"+authLtrName);
						 * dto.setPartyOrRep(2);
						 * 
						 * }
						 * 
						 * else{ dto.setNotInsertedDestroy(1);
						 * forwardPage="undelivered"; } }// end of if
						 * representative
						 */ArrayList issueDetls = new ArrayList();
						issueDetls = dodBD.getissueDetls(mainId);
						if (issueDetls.size() > 0) {
							dto.setIsLinkToBeShown(1);
						}
					}// end of amtToBePaid=PaidAmt

					formName = "";
					actionName = "";

				}// ---end of payment param comparison
			} // --end of payment parameter!=null.

			// ----first page coding------
			if (formName != null && formName.equalsIgnoreCase("DodHomePageSR")) {

				// --------------------action
				// 2----------------------------------
				if (request.getParameter("actionLink") != null) {
					actionName = (String) request.getParameter("actionLink");
				}
				if (!(actionName).equalsIgnoreCase("")) {
					if (actionName.equalsIgnoreCase("hyperClick")) {
						String regComNo = "";
						String regComDt = "";
						String docStatusId = "";
						String docStatusName = "";
						String oneMonthDt = "";
						String lastDelDt = "";
						String lastDueDt = "";
						String stChngeDt = "";
						String srDrName = "";
						String officeName = "";
						String receipntId = "";
						String rprsntatvNm = "";
						String authLtrName = "";
						String authLtrPath = "";
						String stCumDt = request.getParameter("regDtCumStatus");
						String stCumDtList[] = stCumDt.split("~");
						logger.debug("length of array------->" + stCumDtList.length);
						// MODIFIED BY SIMRAN
						if (stCumDtList.length == 15) {
							regComNo = stCumDtList[0];
							// logger.debug("the completion number in the param is------>"+regComNo);
							regComDt = stCumDtList[1];
							// logger.debug("the completion date in the param is------>"+regComDt);
							docStatusId = stCumDtList[2];
							// logger.debug("the Document status in the param is------>"+docStatusId);
							receipntId = stCumDtList[10];
							// logger.debug("the Receipient Id  in the param is------>"+receipntId);
						}
						// ----- if status is delivered-----
						if (docStatusId.equalsIgnoreCase("7")) {
							dto.setIsLinkToBeShown(0);

							ArrayList modedetl = new ArrayList();
							ArrayList sublst = new ArrayList();
							modedetl = dodBD.getmodDetls(regComNo);
							logger.debug("size of arraylist is :- " + modedetl.size());
							if (modedetl.size() > 0) {
								String adres = "";
								String dstrct = "";
								String state = "";
								String cntry = "";
								for (int i = 0; i < modedetl.size(); i++) {
									sublst.add((ArrayList) modedetl.get(i));
									if (sublst != null && sublst.size() > 0) {
										for (int k = 0; k < sublst.size(); k++) {
											ArrayList compSub = (ArrayList) sublst.get(k);
											adres = (String) compSub.get(0);
											if (language.equalsIgnoreCase("en")) {
												dstrct = (String) compSub.get(1);
												state = (String) compSub.get(2);
												cntry = (String) compSub.get(3);
											} else {
												dstrct = (String) compSub.get(4);
												state = (String) compSub.get(5);
												cntry = (String) compSub.get(6);
											}

										}
									}
								}
								dto.setRegNoDisplay(regComNo);
								dto.setRegComDateDisplay(regComDt);
								dto.setLastDelDateDisplay(stCumDtList[5]);
								dto.setLastDueDateDisplay(stCumDtList[6]);
								// ADDED BY SIMRAN
								if (language.equalsIgnoreCase("en"))
									dto.setStatusDisplay(stCumDtList[3]);
								else
									dto.setStatusDisplay(stCumDtList[12]);

								dto.setStatusDateDisplay(stCumDtList[7]);
								dto.setUserNameDisplay(stCumDtList[8]);
								if (language.equalsIgnoreCase("en"))
									dto.setOfficeNameDisplay(stCumDtList[9]);
								else
									dto.setOfficeNameDisplay(stCumDtList[13]);
								dto.setPostlAddrs(adres);
								dto.setPostlDist(dstrct);
								dto.setPostlState(state);
								dto.setPostlCntry(cntry);
								dto.setPostlDocketNo(stCumDtList[11]);
								dto.setNotInsertedPost(0);
								dto.setIsSavedPost(1);
								actionName = "";
								formName = "";
								forwardPage = "undeliveredPost";

							} else {

								if (receipntId.equalsIgnoreCase("1") && receipntId != null) {
									if (language.equalsIgnoreCase("en"))
										dto.setDocDelToDisplay("Transacting Party");
									else
										dto.setDocDelToDisplay("लेनदेन पार्टी");

									ArrayList repDetl = new ArrayList();
									repDetl = dodBD.getrecpDetl(regComNo);
									String filePath = dodBD.getSignFilePath(regComNo);
									dto.setHandPath(filePath);
									dto.setFileNameSign("signature.GIF");
									dto.setTransDetailsDisplay(repDetl);
									request.setAttribute("repDetails", repDetl);
									dto.setPartyOrRep(1);
								}

								else if (receipntId.equalsIgnoreCase("2") && receipntId != null) {
									ArrayList rprsntatvDetls = new ArrayList();
									ArrayList comp7 = new ArrayList();
									rprsntatvDetls = dodBD.getrprsntatvDetls(regComNo);
									if (rprsntatvDetls.size() > 0) {
										for (int i = 0; i < rprsntatvDetls.size(); i++) {
											comp7.add((ArrayList) rprsntatvDetls.get(i));
											if (comp7 != null && comp7.size() > 0) {
												for (int k = 0; k < comp7.size(); k++) {
													ArrayList compSub = (ArrayList) comp7.get(k);
													rprsntatvNm = (String) compSub.get(0);
													authLtrName = (String) compSub.get(1);
													authLtrPath = (String) compSub.get(2);
												}
											}
										}
									}
									// dto.setIsrepresentative(1);
									// Added By SIMRAN
									if (language.equalsIgnoreCase("en"))
										dto.setDocDelToDisplay("Representative");
									else
										dto.setDocDelToDisplay("प्रतिनिधि");

									dto.setOtherNameDisplay(rprsntatvNm);
									dto.setAuthLetterNmDisplay(authLtrName);
									dto.setDocPath(authLtrPath + "/" + authLtrName);
									dto.setPartyOrRep(2);
								}
								ArrayList issueDetls = new ArrayList();
								issueDetls = dodBD.getissueDetls(regComNo);
								if (issueDetls.size() > 0) {
									dto.setIsLinkToBeShown(1);
								}
								dto.setRegNoDisplay(regComNo);
								dto.setRegComDateDisplay(regComDt);
								dto.setLastDelDateDisplay(stCumDtList[5]);
								dto.setLastDueDateDisplay(stCumDtList[6]);
								// ADDED BY SIMRAN
								if (language.equalsIgnoreCase("en"))
									dto.setStatusDisplay(stCumDtList[3]);
								else
									dto.setStatusDisplay(stCumDtList[12]);

								dto.setStatusDateDisplay(stCumDtList[7]);
								dto.setUserNameDisplay(stCumDtList[8]);
								if (language.equalsIgnoreCase("en"))
									dto.setOfficeNameDisplay(stCumDtList[9]);
								else
									dto.setOfficeNameDisplay(stCumDtList[13]);

								eForm.setActionName("");
								eForm.setFormName("");

								request.setAttribute("dod", eForm);
								forwardPage = "deliveredConfrm";
							}

						}// end of if status is Delivered.

						// -----if status is destroyed------
						if (docStatusId.equalsIgnoreCase("6")) {
							ArrayList destroyDetls = new ArrayList();
							ArrayList comp7 = new ArrayList();
							destroyDetls = dodBD.getdestroyDetls(regComNo);
							if (destroyDetls.size() > 0) {
								for (int i = 0; i < destroyDetls.size(); i++) {
									comp7.add((ArrayList) destroyDetls.get(i));
									if (comp7 != null && comp7.size() > 0) {
										for (int k = 0; k < comp7.size(); k++) {
											ArrayList compSub = (ArrayList) comp7.get(k);
											dto.setRegNoDisplay((String) compSub.get(0));
											dto.setRegComDateDisplay((String) compSub.get(1));
											dto.setLastDelDateDisplay((String) compSub.get(3));
											dto.setLastDueDateDisplay((String) compSub.get(4));
											// SIMRAN
											if (language.equalsIgnoreCase("en"))
												dto.setStatusDisplay((String) compSub.get(2));
											else
												dto.setStatusDisplay((String) compSub.get(10));

											dto.setStatusDateDisplay((String) compSub.get(5));
											dto.setUserNameDisplay((String) compSub.get(6));
											if (language.equalsIgnoreCase("en"))
												dto.setOfficeNameDisplay((String) compSub.get(7));
											else
												dto.setOfficeNameDisplay((String) compSub.get(11));
											dto.setDrNameDisplay((String) compSub.get(8));
											// SIMRAN
											if (language.equalsIgnoreCase("en"))
												dto.setDrOfficeDisplay((String) compSub.get(9));
											else
												dto.setDrOfficeDisplay((String) compSub.get(12));

											dto.setIsdestroyed(1);
											forwardPage = "destroy";
										}
									}
								}
							}
						} // --end of status "destroyed"----

						// ---if status is approved for destruction----
						if (docStatusId.equalsIgnoreCase("5")) {

							ArrayList destroyDetls = new ArrayList();
							ArrayList comp7 = new ArrayList();
							destroyDetls = dodBD.getDrDetl(regComNo);
							if (destroyDetls.size() > 0) {
								for (int i = 0; i < destroyDetls.size(); i++) {
									comp7.add((ArrayList) destroyDetls.get(i));
									if (comp7 != null && comp7.size() > 0) {
										for (int k = 0; k < comp7.size(); k++) {
											ArrayList compSub = (ArrayList) comp7.get(k);
											dto.setDrNameDisplay((String) compSub.get(0));
											if (language.equalsIgnoreCase("en"))
												dto.setDrOfficeDisplay((String) compSub.get(1));
											else
												dto.setDrOfficeDisplay((String) compSub.get(2));

										}
									}
								}
							}
							dto.setRegNoDisplay(regComNo);
							dto.setRegComDateDisplay(regComDt);
							dto.setLastDelDateDisplay(stCumDtList[5]);
							dto.setLastDueDateDisplay(stCumDtList[6]);
							if (language.equalsIgnoreCase("en"))
								dto.setStatusDisplay(stCumDtList[3]);
							else
								dto.setStatusDisplay(stCumDtList[12]);

							dto.setStatusDateDisplay(stCumDtList[7]);
							eForm.setActionName("");
							eForm.setFormName("");
							request.setAttribute("dod", eForm);
							forwardPage = "destroy";

						}// --end of approved for destruction----

						// -----if status is pending for DR Approval------
						if (docStatusId.equalsIgnoreCase("4")) {
							String offcType = dodBD.getOffcTypeDetl(officeId);
							if (offcType.equals("2")) {
								dto.setRegNoDisplay(regComNo);
								dto.setRegComDateDisplay(regComDt);
								dto.setLastDelDateDisplay(stCumDtList[5]);
								dto.setLastDueDateDisplay(stCumDtList[6]);
								dto.setStatusDisplay(stCumDtList[3]);
								dto.setStatusDateDisplay(stCumDtList[7]);
								dto.setUserNameDisplay(stCumDtList[8]);
								dto.setOfficeNameDisplay(stCumDtList[9]);
								forwardPage = "drViewApproval";
							} else {
								dto.setPendingApproval(1);
								ArrayList regDetails = dto.getRegDetails();
								request.setAttribute("regDetails", regDetails);
								// logger.debug(eForm.getDoddto().getPendingApproval());
								forwardPage = "dodsrsearch";
							}

						}// --end of pending for approval

						// start----if status is notice is issued
						if (docStatusId.equalsIgnoreCase("3")) {

							dto.setRegNoDisplay(regComNo);
							dto.setRegComDateDisplay(regComDt);
							dto.setLastDelDateDisplay(stCumDtList[5]);
							dto.setLastDueDateDisplay(stCumDtList[6]);
							if (language.equalsIgnoreCase("en"))
								dto.setStatusDisplay(stCumDtList[3]);
							else
								dto.setStatusDisplay(stCumDtList[12]);
							dto.setStatusDateDisplay(stCumDtList[7]);
							dto.setUserNameDisplay(stCumDtList[8]);
							if (language.equalsIgnoreCase("en"))
								dto.setOfficeNameDisplay(stCumDtList[9]);
							else
								dto.setOfficeNameDisplay(stCumDtList[13]);
							dto.setIsOneMnOver(1);
							dto.setIsIssued(1);
							String fee = dodBD.getfee("FUN_207", null, userId);

							DecimalFormat myformatter = new DecimalFormat("############");
							ArrayList first = new ArrayList();
							first = dodBD.getPayDtls(regComNo);
							if (first.size() == 0) {
								logger.debug("inside if");
								int ind = fee.indexOf(".");
								if (ind != -1) {
									String totFee1 = fee.substring(0, fee.indexOf("."));
									dto.setTotalFee(totFee1);
									dto.setPayableFee(totFee1);
								} else {
									dto.setTotalFee(fee);
									dto.setPayableFee(fee);
								}
								dto.setAlrdyPaidFee("0");

							}
							// if pay details are not empty
							else {
								logger.debug("inside else---means record is found");
								ArrayList comp = new ArrayList();
								String uniqId = "";
								String amtToBePaid = "";
								String paidAmt = "";
								String pymtFlg = "";

								for (int i = 0; i < first.size(); i++) {
									comp.add((ArrayList) first.get(i));
									if (comp != null && comp.size() > 0) {
										for (int k = 0; k < comp.size(); k++) {
											ArrayList compSub = (ArrayList) comp.get(k);
											uniqId = (String) compSub.get(0);
											amtToBePaid = (String) compSub.get(1);
											paidAmt = (String) compSub.get(2);
											pymtFlg = (String) compSub.get(3);
											// logger.debug("unique id is ............................"+uniqId);
											// logger.debug("unpayable amt is ............................"+amtToBePaid);
											// logger.debug("paid amt is..................................."+paidAmt);
											// logger.debug("pymtFlg is....................................."+pymtFlg);
										}
									}
								}
								double damtToBePaid = Double.parseDouble(amtToBePaid);

								// ------- if payment flag is I ----
								if (pymtFlg.equalsIgnoreCase("I")) {

									int ind = fee.indexOf(".");
									if (ind != -1) {
										String totFee1 = fee.substring(0, fee.indexOf("."));
										dto.setTotalFee(totFee1);
									} else {
										dto.setTotalFee(fee);
									}

									dto.setPayableFee(amtToBePaid);
									String alrdFrmt = myformatter.format(Double.parseDouble(fee) - Double.parseDouble(amtToBePaid));
									dto.setAlrdyPaidFee(alrdFrmt);

								}// end of payment flag I

								// ----------- if payment flag is P -----
								if (pymtFlg.equalsIgnoreCase("P")) {

									double dpaidAmt = Double.parseDouble(paidAmt);
									// condition 1-------------------pending
									// amount-------------
									if (damtToBePaid > dpaidAmt) {
										int ind = fee.indexOf(".");
										if (ind != -1) {
											String totFee1 = fee.substring(0, fee.indexOf("."));
											dto.setTotalFee(totFee1);
										} else {
											dto.setTotalFee(fee);
										}
										double bal = damtToBePaid - dpaidAmt;
										String balfrmt = myformatter.format(bal);
										dto.setPayableFee(balfrmt);
										String alrdFrmt = myformatter.format(Double.parseDouble(fee) - bal);
										dto.setAlrdyPaidFee(alrdFrmt);
										dto.setIsPartial(1);

									}// end of conditon 1

									// condition
									// 2-----------------------complete payment
									if (damtToBePaid == dpaidAmt) {
										int ind = fee.indexOf(".");
										if (ind != -1) {
											String totFee1 = fee.substring(0, fee.indexOf("."));
											dto.setTotalFee(totFee1);
											dto.setAlrdyPaidFee(totFee1);
										} else {
											dto.setTotalFee(fee);
											dto.setAlrdyPaidFee(fee);
										}
										dto.setPayableFee("0");
										dto.setIscompletePay(1);
									}// end of condition 2
								}// end of payment flag=P
							}// end of pay details not empty

							forwardPage = "undelivered";
						}// end ------if status is notice issued.

						// start----if status is notice to be issued
						if (docStatusId.equalsIgnoreCase("2")) {
							dto.setNoticeRegNoDisplay(regComNo);
							dto.setNoticeRegComDateDisplay(regComDt);
							dto.setNoticeLastDelDateDisplay(stCumDtList[5]);
							dto.setNoticeLastDueDateDisplay(stCumDtList[6]);
							if (language.equalsIgnoreCase("en"))
								dto.setNoticeStatusDisplay(stCumDtList[3]);
							else
								dto.setNoticeStatusDisplay(stCumDtList[12]);

							dto.setNoticeStatusDateDisplay(stCumDtList[7]);
							dto.setNoticeUserNameDisplay(stCumDtList[8]);
							if (language.equalsIgnoreCase("en"))
								dto.setNoticeOfficeNameDisplay(stCumDtList[9]);
							else
								dto.setNoticeOfficeNameDisplay(stCumDtList[13]);
							ArrayList partyDetl = new ArrayList();
							partyDetl = dodBD.getpartyDetl(regComNo, language);
							dto.setNoticeDetails(partyDetl);
							request.setAttribute("partyDetails", partyDetl);
							forwardPage = "issueNotice";
						}// end------if status is notice to be issued

						// -----if status is "Undelivered"
						if (docStatusId.equalsIgnoreCase("8")) {
							dto.setRecptName("");
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							String oneMnDt = stCumDtList[4];
							String oneMnDtFrmt = oneMnDt.substring(0, 10);
							Date curDt = new Date();
							String curDtFrmt = sdf.format(curDt);
							// logger.debug("the one month date is----->>>"+oneMnDt);
							// logger.debug("the one month formatted date is----->>>"+oneMnDtFrmt);
							// logger.debug("the current formatted date is----->>>"+curDtFrmt);
							Date oneMnDtFinal = sdf.parse(oneMnDtFrmt);
							Date curDtFinal = sdf.parse(curDtFrmt);

							ArrayList modedetl = new ArrayList();
							ArrayList sublst = new ArrayList();
							modedetl = dodBD.getmodDetls(regComNo);
							logger.debug("size of arraylist is :- " + modedetl.size());
							if (modedetl.size() > 0) {
								String adres = "";
								String dstrct = "";
								String state = "";
								String cntry = "";
								for (int i = 0; i < modedetl.size(); i++) {
									sublst.add((ArrayList) modedetl.get(i));
									if (sublst != null && sublst.size() > 0) {
										for (int k = 0; k < sublst.size(); k++) {
											ArrayList compSub = (ArrayList) sublst.get(k);
											adres = (String) compSub.get(0);
											if (language.equalsIgnoreCase("en")) {
												dstrct = (String) compSub.get(1);
												state = (String) compSub.get(2);
												cntry = (String) compSub.get(3);
											} else {
												dstrct = (String) compSub.get(4);
												state = (String) compSub.get(5);
												cntry = (String) compSub.get(6);
											}

										}
									}
								}
								dto.setRegNoDisplay(regComNo);
								dto.setRegComDateDisplay(regComDt);
								dto.setLastDelDateDisplay(stCumDtList[5]);
								dto.setLastDueDateDisplay(stCumDtList[6]);
								if (language.equalsIgnoreCase("en"))
									dto.setStatusDisplay(stCumDtList[3]);
								else
									dto.setStatusDisplay(stCumDtList[12]);

								dto.setStatusDateDisplay(stCumDtList[7]);
								dto.setUserNameDisplay(stCumDtList[8]);
								if (language.equalsIgnoreCase("en"))
									dto.setOfficeNameDisplay(stCumDtList[9]);
								else
									dto.setOfficeNameDisplay(stCumDtList[13]);
								dto.setPostlAddrs(adres);
								dto.setPostlDist(dstrct);
								dto.setPostlState(state);
								dto.setPostlCntry(cntry);
								dto.setNotInsertedPost(0);
								dto.setIsSavedPost(0);
								// added by Shreeraj
								String regNum = dto.getRegNoDisplay();
								String regTxnID = dodBD.getRegTxnID(regNum);

								RegCompMkrBD regmkrBD = new RegCompMkrBD();
								dto.setDeedID(regmkrBD.getDeedId(regTxnID));
								dto.setInstID(regmkrBD.getInstId(regTxnID));
								if (regmkrBD.checkPinRequired(regNum)) {
									dto.setPinRequired("Y");

								} else {
									dto.setPinRequired("N");
								}
								forwardPage = "undeliveredPost";

							} else {
								if (oneMnDtFinal.compareTo(curDtFinal) > 0 || oneMnDtFinal.compareTo(curDtFinal) == 0) {
									logger.debug("the one month date has yet to come or it is today");
									dto.setRegNoDisplay(regComNo);
									dto.setRegComDateDisplay(regComDt);
									dto.setLastDelDateDisplay(stCumDtList[5]);
									dto.setLastDueDateDisplay(stCumDtList[6]);

									if (language.equalsIgnoreCase("en"))
										dto.setStatusDisplay(stCumDtList[3]);
									else
										dto.setStatusDisplay(stCumDtList[12]);

									dto.setStatusDateDisplay(stCumDtList[7]);
									dto.setUserNameDisplay(stCumDtList[8]);
									if (language.equalsIgnoreCase("en"))
										dto.setOfficeNameDisplay(stCumDtList[9]);
									else
										dto.setOfficeNameDisplay(stCumDtList[13]);
									forwardPage = "undelivered";

								} else if (oneMnDtFinal.compareTo(curDtFinal) < 0) {
									logger.debug("the one month date has been crossed");
									dto.setRegNoDisplay(regComNo);
									dto.setRegComDateDisplay(regComDt);
									dto.setLastDelDateDisplay(stCumDtList[5]);
									dto.setLastDueDateDisplay(stCumDtList[6]);
									dto.setFunID("FUN_207");
									if (language.equalsIgnoreCase("en"))
										dto.setStatusDisplay(stCumDtList[3]);
									else
										dto.setStatusDisplay(stCumDtList[12]);

									dto.setStatusDateDisplay(stCumDtList[7]);
									dto.setUserNameDisplay(stCumDtList[8]);
									if (language.equalsIgnoreCase("en"))
										dto.setOfficeNameDisplay(stCumDtList[9]);
									else
										dto.setOfficeNameDisplay(stCumDtList[13]);
									dto.setIsOneMnOver(1);
									// String fee = dodBD.getfee("FUN_207",
									// null, userId);
									String fee = dodBD.getFee("FUN_207");
									if ("".equalsIgnoreCase(fee))
										fee = "50";

									DecimalFormat myformatter = new DecimalFormat("############");
									ArrayList first = new ArrayList();
									first = dodBD.getPayDtls(regComNo);
									if (first.size() == 0) {
										logger.debug("inside if");
										int ind = fee.indexOf(".");
										if (ind != -1) {
											String totFee1 = fee.substring(0, fee.indexOf("."));
											dto.setTotalFee(totFee1);
											dto.setPayableFee(totFee1);
										} else {
											dto.setTotalFee(fee);
											dto.setPayableFee(fee);
										}
										dto.setAlrdyPaidFee("0");

									}
									// if pay details are not empty
									else {
										logger.debug("inside else---means record is found");
										ArrayList comp = new ArrayList();
										String uniqId = "";
										String amtToBePaid = "";
										String paidAmt = "";
										String pymtFlg = "";

										for (int i = 0; i < first.size(); i++) {
											comp.add((ArrayList) first.get(i));
											if (comp != null && comp.size() > 0) {
												for (int k = 0; k < comp.size(); k++) {
													ArrayList compSub = (ArrayList) comp.get(k);
													uniqId = (String) compSub.get(0);
													amtToBePaid = (String) compSub.get(1);
													paidAmt = (String) compSub.get(2);
													pymtFlg = (String) compSub.get(3);
													// logger.debug("unique id is ............................"+uniqId);
													// logger.debug("unpayable amt is ............................"+amtToBePaid);
													// logger.debug("paid amt is..................................."+paidAmt);
													// logger.debug("pymtFlg is....................................."+pymtFlg);
												}
											}
										}
										double damtToBePaid = Double.parseDouble(amtToBePaid);

										// ------- if payment flag is I ----
										if (pymtFlg.equalsIgnoreCase("I")) {

											int ind = fee.indexOf(".");
											if (ind != -1) {
												String totFee1 = fee.substring(0, fee.indexOf("."));
												dto.setTotalFee(totFee1);
											} else {
												dto.setTotalFee(fee);
											}

											dto.setPayableFee(amtToBePaid);
											String alrdFrmt = myformatter.format(Double.parseDouble(fee) - Double.parseDouble(amtToBePaid));
											dto.setAlrdyPaidFee(alrdFrmt);

										}// end of payment flag I

										// ----------- if payment flag is P
										// -----
										if (pymtFlg.equalsIgnoreCase("P")) {

											double dpaidAmt = Double.parseDouble(paidAmt);
											// condition
											// 1-------------------pending
											// amount-------------
											if (damtToBePaid > dpaidAmt) {
												int ind = fee.indexOf(".");
												if (ind != -1) {
													String totFee1 = fee.substring(0, fee.indexOf("."));
													dto.setTotalFee(totFee1);
												} else {
													dto.setTotalFee(fee);
												}
												double bal = damtToBePaid - dpaidAmt;
												String balfrmt = myformatter.format(bal);
												dto.setPayableFee(balfrmt);
												String alrdFrmt = myformatter.format(Double.parseDouble(fee) - bal);
												dto.setAlrdyPaidFee(alrdFrmt);
												dto.setIsPartial(1);

											}// end of conditon 1

											// condition
											// 2-----------------------complete
											// payment
											if (damtToBePaid == dpaidAmt || dpaidAmt > damtToBePaid) {
												int ind = fee.indexOf(".");
												if (ind != -1) {
													String totFee1 = fee.substring(0, fee.indexOf("."));
													dto.setTotalFee(totFee1);
													dto.setAlrdyPaidFee(totFee1);
												} else {
													dto.setTotalFee(fee);
													dto.setAlrdyPaidFee(fee);
												}
												dto.setPayableFee("0");
												dto.setIscompletePay(1);
											}// end of condition 2
										}// end of payment flag=P
									}// end of pay details not empty
									forwardPage = "undelivered";

								}// end of one month exceed condition
								// added by Shreeraj
								String regNum = dto.getRegNoDisplay();
								String regTxnID = dodBD.getRegTxnID(regNum);
								RegCompMkrBD regmkrBD = new RegCompMkrBD();
								dto.setDeedID(regmkrBD.getDeedId(regTxnID));
								dto.setInstID(regmkrBD.getInstId(regTxnID));

								ArrayList ret = dodBD.viewClaimantDelivery(regTxnID, dto.getDeedID(), dto, userId);
								dto.setClaimantList(ret);
								// code for capturing signatre
								String FILE_UPLOAD_PATH = "";
								PropertiesFileReader proper;
								try {
									proper = PropertiesFileReader.getInstance("resources.igrs");
									FILE_UPLOAD_PATH = proper.getValue("igrs_upload_path") + File.separator + "03" + File.separator;
								} catch (Exception e1) {
									logger.debug(e1.getStackTrace());
								}

								String path = FILE_UPLOAD_PATH + dto.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE + "hand" + File.separator;
								dto.setGuidUpload("");
								dto.setParentPathSign(path);
								dto.setHandPath(path + "signature.GIF");
								dto.setFileNameSign("signature.GIF");
								dto.setForwardPath("/dod.do?actionLink=SrSignatureHand");
								dto.setForwardName("sigantureDoc");
								// String pathSr =
								// RegCompCheckerConstant.FILE_UPLOAD_PATH+dto.getRegInitNumber()+RegCompCheckerConstant.UPLOAD_PATH_SR_SIGN;
								dto.setParentPathSrSign(path);
								dto.setFileNameSrSign("signature.GIF");
								dto.setForwardPathSr("/dod.do?actionLink=SrSignatureHand");
								// dto.setParentPathGovtLttr(RegCompCheckerConstant.FILE_UPLOAD_PATH+dto.getRegInitNumber()+RegCompCheckerConstant.UPLOAD_PATH_GOVT_LETTER);
								// dto.setFileNameGovtLttr(RegCompCheckerConstant.FILE_NAME_GOVT_LETTER);
								// String pathFP =
								// RegCompCheckerConstant.FILE_UPLOAD_PATH+dto.getRegInitNumber()+RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
								dto.setFileNameFP("FingerPrint.GIF");
								dto.setParentPathFP(path);
							}// end of mode condition

						}// --end of status is undelivered

					}// end of action 2 "hyperClick"
					if (actionName.equalsIgnoreCase("finalDocPrint")) {

						RegCompCheckerBD bd = new RegCompCheckerBD();
						// flag = true;
						String stCumDt = request.getParameter("regDtCumStatus");
						String stCumDtList[] = stCumDt.split("~");
						String eRegNo = stCumDtList[0];
						String regTxnID = dodBD.getRegTxnID(eRegNo);
						String printFlag = bd.getPrintFlag(regTxnID);
						if ("Y".equalsIgnoreCase(printFlag)) {
							return new ActionForward("/jsp/DeliveryofDocuments/fail.jsp");

						}

						else {
							RegCompCheckerForm regForm = new RegCompCheckerForm();
							String headerimg = getServlet().getServletContext().getRealPath("") + "/images/header img.jpg";
							regForm.setHeaderImg(headerimg);
							String uid = bd.getUsrId(regTxnID);
							String oid = bd.getOfcId(regTxnID);
							regForm.getRegDTO().setRegInitNumber(regTxnID);
							regForm.getRegDTO().setRegCompleteId(eRegNo);
							regForm.setUserId(uid);
							SealsBD sealbd = new SealsBD();
							String lang = sealbd.getLangauge(regTxnID);
							boolean flag = false;
							// calling rcms before
							// String regTxnID = dodBD.getRegTxnID(regNum);
							// added by saurav

							// CLRResponse.insertCLRData(regTxnID);

							// Get RCMS Flag - check for already delivered
							// document
							String rcmsFlag = dodBD.getRCMSFlag(regTxnID);

							if (!(rcmsFlag.equalsIgnoreCase("2") || rcmsFlag.equalsIgnoreCase("3") || rcmsFlag.equalsIgnoreCase("1"))) {
								// Check for deed types required by RCMS

								boolean deedType = dodBD.checkDeedTypeForRCMS(regTxnID);
								if (deedType) {
									boolean resp = false;

									// Check for agricultural land
									ArrayList checkPropList = dodBD.checkPropertyForAgriLand(regTxnID);
									if (checkPropList != null && !checkPropList.isEmpty()) {
										PDFFormVI formVI = new PDFFormVI();

										formVI.generateFormAPDF(regTxnID, response, language);

										resp = sendingXMLDataToRCMS(eRegNo, regTxnID);

										if (resp) {

											// Print Receipt
											// Get the registration number and
											// pass the same and open print
											// receipt window
											request.getSession().setAttribute("regNo", eRegNo);
											System.out.println(" Request.getAttribute  :- " + (String) request.getSession().getAttribute("regNo"));
											PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
											String finalUrl = pr.getValue("IGRS_RCMS_PRINT_URL");
											eForm.setRcmsPrintUrl(finalUrl);
											request.getSession().setAttribute("rcmsUrl", finalUrl);
											System.out.println("RCMS URL :- " + finalUrl);
										}

									}

								}
							}
							try {
								flag = bd.generateFinalCertificate(regForm, uid, oid, lang);
								if (flag) {

									bd.updatePrintFlag(regTxnID);
								}
							} catch (Exception e) {
								logger.debug("error in generating document");
								logger.debug(e.getStackTrace());
								throw new Exception();
							}
						}

						String guideId = GUIDGenerator.getGUID();
						// String filePath = regForm.getFinalDocPath();
						dto.setFileNameOwm(RegCompCheckerConstant.FILE_PATH_FOR_OWM + guideId + File.separator + eRegNo.trim() + RegCompCheckerConstant.NAME_OF_FINAL_DOC_EXETENSION);
						logger.debug("guideid--------------->" + guideId);
						String outputPath = RegCompCheckerConstant.FILE_PATH_FOR_OWM + guideId + File.separator;
						File output;
						output = new File(outputPath.toString());

						if (output.exists() == false) {
							logger.debug("Parent not found creating directory....");
							output.mkdirs();
							if (output.exists()) {
								logger.debug("path created------------------>");
							}
						}
						try {
							// BurningImageAndText burnObj = new
							// BurningImageAndText();
							PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
							String hindiFont = pr.getValue("dms_hindi_font_path");
							String englishFont = pr.getValue("dms_english_font_path");
							SealsBD sealBD = new SealsBD();
							boolean val = sealBD.validateFont(hindiFont, englishFont);
							if (!val) {
								throw new Exception();
							}
							/*
							 * String stCumDt =
							 * request.getParameter("regDtCumStatus"); String
							 * stCumDtList[]= stCumDt.split("~"); String
							 * eRegNo=stCumDtList[0]; String
							 * regTxnID=dodBD.getRegTxnID(eRegNo);
							 */
							dto.setRegInitNumber(regTxnID);

							BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);
							PresentationSeal sealContent = new PresentationSeal();
							ArrayList<Presenter> presenterList = new ArrayList();
							// ArrayList<Claimant> claimantList = new
							// ArrayList();
							// PropertiesFileReader pr =
							// PropertiesFileReader.getInstance("resources.igrs");
							DocumentOperations docOperations = new DocumentOperations();
							ODServerDetails connDetails = new ODServerDetails();
							Dataclass metaDataInfo = new Dataclass();
							connDetails.setAppServerIp(pr.getValue("AppServerIp"));
							connDetails.setCabinetName(pr.getValue("CabinetName"));
							connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
							connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
							connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
							connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
							connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
							metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
							metaDataInfo.setUniqueNo(dto.getRegInitNumber());
							metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC);
							if (null == metaDataInfo.getUniqueNo()) {
								session.setAttribute("checkStatus", "DMSError");
								return new ActionForward("/jsp/login/UserRoleValidationPage.jsp");
							}
							if (metaDataInfo.getUniqueNo().equals("")) {
								session.setAttribute("checkStatus", "DMSError");
								return new ActionForward("/jsp/login/UserRoleValidationPage.jsp");
							}
							docOperations.downloadDocument(connDetails, metaDataInfo, outputPath, "F");

							/*
							 * File folder=new File(outputPath); File[]
							 * listOfFiles = folder.listFiles(); for (int z =
							 * 0;z < listOfFiles.length; z++) { String
							 * tempPath=listOfFiles[z].getPath();
							 * dto.setFileNameOwm(tempPath); }
							 */
							logger.debug("File name --> " + dto.getFileNameOwm());

							dto.setOwmChk("Y");
							dto.setRegCompAppletNo(eRegNo);

							// check for rcms receipt ankit and saurav
							String rcmsFlag = dodBD.getRCMSFlag(regTxnID);
							String caseType = CLRService.getCyberCase(regTxnID);
							if (rcmsFlag != null) {
								if ("2".equalsIgnoreCase(rcmsFlag)) {
									File mpDirPath = new File(outputPath);
									FormVIUtil util = new FormVIUtil();
									// mpDirPath.getPath();
									File mpPdfFile = util.getLatestFilefromDir(mpDirPath.getAbsolutePath());

									PdfReader reader = new PdfReader(mpPdfFile.getAbsolutePath());
									int count = reader.getNumberOfPages();

									logger.info(" ----------- No. of pages  --------" + count);

									// File mpPdfFile =
									// mpDirPath.getAbsoluteFile();
									String mpPdfFileName = mpPdfFile.getName();

									String mergePdf = outputPath + "//" + "mergeDoc.pdf";

									logger.info(" ----------- Generating RCMS receipt --------");
									if (caseType.equalsIgnoreCase("Yes")) {
										GenerateRCMSReceipt receipt = new GenerateRCMSReceipt();
										receipt.generateRCMSReceipt(regTxnID, count);
									} else {
										GenerateTeritorialCourtReceipt gt = new GenerateTeritorialCourtReceipt();
										gt.generateRCMSReceipt(regTxnID, count);
									}

									logger.info(" -----------  RCMS receipt generated --------");

									String pdfDir = pr.getValue("RCMS_RECEIPT_PATH");
									pdfDir = pdfDir + regTxnID + "//";

									File pdfDirPath = new File(pdfDir);

									if (!pdfDirPath.exists()) {
										pdfDirPath.mkdir();
									}
									String rcmspdfFilePath = pdfDirPath + "//" + pr.getValue("RCMS_RECEIPT_FILENAME");

									PDFMergerUtility obj = new PDFMergerUtility();

									// Setting the destination file path

									obj.setDestinationFileName(mergePdf);

									// Add all source files, to be merged

									obj.addSource(mpPdfFile.getAbsolutePath());
									obj.addSource(rcmspdfFilePath);

									// Merging documents

									obj.mergeDocuments();

									logger.debug("PDF files merged......... ");

									if (mpPdfFile.delete()) {
										logger.debug("deleted - " + mpPdfFile);
									}

									File oldName = new File(mergePdf);

									if (oldName.renameTo(mpPdfFile)) {
										logger.debug("PDF renamed  successfully ");
									} else {
										logger.debug("error in renaming PDF ");
									}

								}
							}

							logger.debug("own check ---> " + dto.getOwmChk());
							logger.debug("reg number --> " + dto.getRegCompAppletNo());
							logger.debug("File name --> " + dto.getFileNameOwm());
							// dodBD.statusPrintedUpdate(eRegNo, userId,
							// officeId);

						} catch (Exception e) {
							logger.debug(e.getStackTrace());
							throw new Exception();
						}

						dto.setOwmChk("Y");
						dto.setRegCompAppletNo(eRegNo);

						eForm.setActionName("");
						eForm.setFormName("");
						formName = "";
						actionName = "";
						forwardPage = "dodsrsearch";
						logger.debug("forward page is --> " + forwardPage);
						/*
						 * response.setContentType("application/download");
						 * 
						 * 
						 * response.setHeader("Content-Disposition",
						 * "attachment; filename=" +
						 * URLEncoder.encode(filePath,"UTF-8"));
						 * 
						 * File fileToDownload = new File(filePath);
						 * FileInputStream fileInputStream = new
						 * FileInputStream(fileToDownload); OutputStream out =
						 * response.getOutputStream(); byte[] buf = new
						 * byte[2048];
						 * 
						 * int readNum; while
						 * ((readNum=fileInputStream.read(buf))!=-1) {
						 * out.write(buf,0,readNum); } fileInputStream.close();
						 * out.close();
						 */

					}

				}// end of if condition for request param
				// --action 1------
				if (actionName.equalsIgnoreCase("SearchSR")) {
					dto.setOwmChk("");
					dto.setNoRecFound(0);
					dto.setIsrepresentative(0);
					dto.setPendingApproval(0);
					dto.setIsdestroyed(0);
					dto.setNotInsertedDestroy(0);
					String regno = dto.getRegNo();

					String status = dto.getStatusSelected();
					String frmDt = dto.getFromRequestDate();
					String toDt = dto.getToRequestDate();
					// String offId="OFC90";
					officeId = (String) session.getAttribute("loggedToOffice");
					String offcType = dodBD.getOffcTypeDetl(officeId);
					ArrayList offcList = new ArrayList();
					boolean drCheck = false;
					if (offcType.equals("2")) // DR Office
					{
						offcList = dodBD.getChildOffcDetl(officeId);
						drCheck = true;
					}
					if (status.equalsIgnoreCase("select")) {
						status = "";
					}
					ArrayList regDetails = new ArrayList();

					regDetails = dodBD.getregDetl(regno, status, frmDt, toDt, officeId, language, offcList, drCheck);
					if (regDetails.size() == 0) {
						dto.setNoRecFound(1);
					}
					if (regDetails.size() > 0) {
						dto.setRegDetails(regDetails);
						request.setAttribute("regDetails", regDetails);
					}
					if ("1".equalsIgnoreCase(status)) {
						dto.setSignatureChkFlag("Y");
					} else {
						dto.setSignatureChkFlag("N");
					}
					forwardPage = "dodsrsearch";
				}// end of action 1---"SearchSR"

				if (actionName.equalsIgnoreCase("ChangePrintStatus")) {
					dto.setOwmChk("");
					dto.setNoRecFound(0);
					dto.setIsrepresentative(0);
					dto.setPendingApproval(0);
					dto.setIsdestroyed(0);
					dto.setNotInsertedDestroy(0);
					String regno = dto.getRegNo();

					String status = dto.getStatusSelected();
					String frmDt = dto.getFromRequestDate();
					String toDt = dto.getToRequestDate();
					// String offId="OFC90";
					officeId = (String) session.getAttribute("loggedToOffice");
					String offcType = dodBD.getOffcTypeDetl(officeId);
					ArrayList offcList = new ArrayList();
					boolean drCheck = false;
					if (offcType.equals("2")) // DR Office
					{
						offcList = dodBD.getChildOffcDetl(officeId);
						drCheck = true;
					}
					if (status.equalsIgnoreCase("select")) {
						status = "";
					}
					ArrayList regDetails = new ArrayList();

					// regDetails= dodBD.getregDetl(regno,status, frmDt,
					// toDt,officeId,language,offcList,drCheck );
					String flg = dodBD.getregDetforChangeStatus(regno, status, officeId, language, offcList, drCheck);
					if (flg.equals("6")) {
						dto.setNoRecFound(6);
					}
					if (flg.equals("1")) {
						dto.setNoRecFound(1);
						request.setAttribute("regDetails", regDetails);
					}
					if (flg.equals("9")) {
						dto.setNoRecFound(9);
						request.setAttribute("regDetails", regDetails);
					}
					if ("1".equalsIgnoreCase(status)) {
						dto.setSignatureChkFlag("Y");
					} else {
						dto.setSignatureChkFlag("N");
					}
					// forwardPage="dodsrsearch";
				}// end of action 1---"SearchSR"
				if (actionName.equalsIgnoreCase("ResetSrPrint")) {
					dto.setNoRecFound(0);
					dto.setIsrepresentative(0);
					dto.setPendingApproval(0);
					dto.setIsdestroyed(0);
					dto.setNotInsertedDestroy(0);
					dto.setPartyOrRep(0);
					dto.setIsOneMnOver(0);
					dto.setIsPartial(0);
					dto.setNotInsertedDestroyP(0);
					dto.setIsApproved(0);
					dto.setNotInsertedApprove(0);
					dto.setNotInsertedNotice(0);
					dto.setIsNoticeIssued(0);
					dto.setIsIssued(0);
					dto.setIscompletePay(0);
					dto.setIsLinkToBeShown(0);
					dto.setNotInsertedPost(0);
					dto.setIsSavedPost(0);

					dto.setRegNo("");
					dto.setStatusSelected("");
					dto.setSelecRegNo("");
					dto.setStatusSelectedName("");
					dto.setFromRequestDate("");
					dto.setToRequestDate("");
					dto.setRepFname("");
					dto.setRepMname("");
					dto.setRepLname("");
					dto.setRegCompDt("");
					dto.setDocStatusID("");
					dto.setDocStatusName("");
					dto.setRegCumStatus("");
					dto.setRegCumDetls(null);
					dto.setRegDetails(new ArrayList());
					dto.setPartyNm("");
					dto.setPartyNmDisplay("");
					dto.setTransDetails(new ArrayList());
					dto.setTransDetailsDisplay(new ArrayList());
					dto.setChckArry("");
					dto.setChckBx("");
					dto.setPayableFee("");
					dto.setAlrdyPaidFee("");
					dto.setTotalFee("");
					dto.setPrimKeyPymt("");
					dto.setFlg("");
					dto.setRegNoDisplay("");
					dto.setRegComDateDisplay("");
					dto.setLastDelDateDisplay("");
					dto.setLastDueDateDisplay("");
					dto.setStatusDisplay("");
					dto.setStatusDateDisplay("");
					dto.setUserNameDisplay("");
					dto.setOfficeNameDisplay("");
					dto.setDrNameDisplay("");
					dto.setDrOfficeDisplay("");
					dto.setDocDelToDisplay("");
					dto.setOtherNameDisplay("");
					dto.setAuthLetterNmDisplay("");
					dto.setRadioSelect("");
					dto.setFilePhoto2(null);
					dto.setDocPath("");
					dto.setNoticeRegNoDisplay("");
					dto.setNoticeRegComDateDisplay("");
					dto.setNoticeLastDelDateDisplay("");
					dto.setNoticeLastDueDateDisplay("");
					dto.setNoticeStatusDisplay("");
					dto.setNoticeStatusDateDisplay("");
					dto.setNoticeUserNameDisplay("");
					dto.setNoticeOfficeNameDisplay("");
					dto.setNoticeRemarks("");
					dto.setNoticePartyRole("");
					dto.setNoticePartyAdrs("");
					dto.setNoticePartyNm("");
					dto.setNoticecumdtls("");
					dto.setNoticeDetails(new ArrayList());
					dto.setPostlAddrs("");
					dto.setPostlDist("");
					dto.setPostlState("");
					dto.setPostlCntry("");
					dto.setPostlDocketNo("");
					dto.setOwmChk("");
					request.removeAttribute("regDetails");
					eForm.setActionName("");
					eForm.setFormName("");
					formName = "";
					actionName = "";
					forwardPage = "dodprcsearch";

				}

				// start of ResetSr
				if (actionName.equalsIgnoreCase("ResetSr")) {
					dto.setNoRecFound(0);
					dto.setIsrepresentative(0);
					dto.setPendingApproval(0);
					dto.setIsdestroyed(0);
					dto.setNotInsertedDestroy(0);
					dto.setPartyOrRep(0);
					dto.setIsOneMnOver(0);
					dto.setIsPartial(0);
					dto.setNotInsertedDestroyP(0);
					dto.setIsApproved(0);
					dto.setNotInsertedApprove(0);
					dto.setNotInsertedNotice(0);
					dto.setIsNoticeIssued(0);
					dto.setIsIssued(0);
					dto.setIscompletePay(0);
					dto.setIsLinkToBeShown(0);
					dto.setNotInsertedPost(0);
					dto.setIsSavedPost(0);

					dto.setRegNo("");
					dto.setStatusSelected("");
					dto.setSelecRegNo("");
					dto.setStatusSelectedName("");
					dto.setFromRequestDate("");
					dto.setToRequestDate("");
					dto.setRepFname("");
					dto.setRepMname("");
					dto.setRepLname("");
					dto.setRegCompDt("");
					dto.setDocStatusID("");
					dto.setDocStatusName("");
					dto.setRegCumStatus("");
					dto.setRegCumDetls(null);
					dto.setRegDetails(new ArrayList());
					dto.setPartyNm("");
					dto.setPartyNmDisplay("");
					dto.setTransDetails(new ArrayList());
					dto.setTransDetailsDisplay(new ArrayList());
					dto.setChckArry("");
					dto.setChckBx("");
					dto.setPayableFee("");
					dto.setAlrdyPaidFee("");
					dto.setTotalFee("");
					dto.setPrimKeyPymt("");
					dto.setFlg("");
					dto.setRegNoDisplay("");
					dto.setRegComDateDisplay("");
					dto.setLastDelDateDisplay("");
					dto.setLastDueDateDisplay("");
					dto.setStatusDisplay("");
					dto.setStatusDateDisplay("");
					dto.setUserNameDisplay("");
					dto.setOfficeNameDisplay("");
					dto.setDrNameDisplay("");
					dto.setDrOfficeDisplay("");
					dto.setDocDelToDisplay("");
					dto.setOtherNameDisplay("");
					dto.setAuthLetterNmDisplay("");
					dto.setRadioSelect("");
					dto.setFilePhoto2(null);
					dto.setDocPath("");
					dto.setNoticeRegNoDisplay("");
					dto.setNoticeRegComDateDisplay("");
					dto.setNoticeLastDelDateDisplay("");
					dto.setNoticeLastDueDateDisplay("");
					dto.setNoticeStatusDisplay("");
					dto.setNoticeStatusDateDisplay("");
					dto.setNoticeUserNameDisplay("");
					dto.setNoticeOfficeNameDisplay("");
					dto.setNoticeRemarks("");
					dto.setNoticePartyRole("");
					dto.setNoticePartyAdrs("");
					dto.setNoticePartyNm("");
					dto.setNoticecumdtls("");
					dto.setNoticeDetails(new ArrayList());
					dto.setPostlAddrs("");
					dto.setPostlDist("");
					dto.setPostlState("");
					dto.setPostlCntry("");
					dto.setPostlDocketNo("");
					dto.setOwmChk("");

					request.removeAttribute("regDetails");
					eForm.setActionName("");
					eForm.setFormName("");

					formName = "";
					actionName = "";
					forwardPage = "dodsrsearch";

				}
			}

			// added by Shreeraj
			if (request.getParameter("sodHyperAction") != null) {
				actionName = (String) request.getParameter("sodHyperAction");
				formName = (String) request.getParameter("sodHyperForm");
			}
			if (formName != null && formName.equalsIgnoreCase("sodHomePage")) {
				if (actionName.equalsIgnoreCase("SearchSR")) {

					dto.setNoRecFound(0);
					dto.setIsrepresentative(0);
					dto.setPendingApproval(0);
					dto.setIsdestroyed(0);
					dto.setNotInsertedDestroy(0);
					dto.setOwmChk("");
					String regno = dto.getRegNo();

					String status = dto.getStatusSelected();
					String frmDt = dto.getFromRequestDate();
					String toDt = dto.getToRequestDate();
					// String offId="OFC90";
					officeId = (String) session.getAttribute("loggedToOffice");
					String offcType = dodBD.getOffcTypeDetl(officeId);
					ArrayList offcList = new ArrayList();
					boolean drCheck = false;
					if (offcType.equals("2")) // DR Office
					{
						offcList = dodBD.getChildOffcDetl(officeId);
						drCheck = true;
					}
					if (status.equalsIgnoreCase("select")) {
						status = "";
					}
					ArrayList regDetails = new ArrayList();

					regDetails = dodBD.getregDetl(regno, status, frmDt, toDt, officeId, language, offcList, drCheck);
					if (regDetails.size() == 0) {
						dto.setNoRecFound(1);
					}
					if (regDetails.size() > 0) {
						dto.setRegDetails(regDetails);
						request.setAttribute("regDetails", regDetails);
					}
					eForm.setActionName("");
					eForm.setFormName("");
					formName = "";
					actionName = "";
					forwardPage = "sodSearch";
				}// end of action 1---"SearchSR"

				// start of ResetSr
				if (actionName.equalsIgnoreCase("ResetSr")) {
					dto.setNoRecFound(0);
					dto.setIsrepresentative(0);
					dto.setPendingApproval(0);
					dto.setIsdestroyed(0);
					dto.setNotInsertedDestroy(0);
					dto.setPartyOrRep(0);
					dto.setIsOneMnOver(0);
					dto.setIsPartial(0);
					dto.setNotInsertedDestroyP(0);
					dto.setIsApproved(0);
					dto.setNotInsertedApprove(0);
					dto.setNotInsertedNotice(0);
					dto.setIsNoticeIssued(0);
					dto.setIsIssued(0);
					dto.setIscompletePay(0);
					dto.setIsLinkToBeShown(0);
					dto.setNotInsertedPost(0);
					dto.setIsSavedPost(0);
					dto.setOwmChk("Y");
					dto.setRegNo("");
					dto.setStatusSelected("");
					dto.setSelecRegNo("");
					dto.setStatusSelectedName("");
					dto.setFromRequestDate("");
					dto.setToRequestDate("");
					dto.setRepFname("");
					dto.setRepMname("");
					dto.setRepLname("");
					dto.setRegCompDt("");
					dto.setDocStatusID("");
					dto.setDocStatusName("");
					dto.setRegCumStatus("");
					dto.setRegCumDetls(null);
					dto.setRegDetails(new ArrayList());
					dto.setPartyNm("");
					dto.setPartyNmDisplay("");
					dto.setTransDetails(new ArrayList());
					dto.setTransDetailsDisplay(new ArrayList());
					dto.setChckArry("");
					dto.setChckBx("");
					dto.setPayableFee("");
					dto.setAlrdyPaidFee("");
					dto.setTotalFee("");
					dto.setPrimKeyPymt("");
					dto.setFlg("");
					dto.setRegNoDisplay("");
					dto.setRegComDateDisplay("");
					dto.setLastDelDateDisplay("");
					dto.setLastDueDateDisplay("");
					dto.setStatusDisplay("");
					dto.setStatusDateDisplay("");
					dto.setUserNameDisplay("");
					dto.setOfficeNameDisplay("");
					dto.setDrNameDisplay("");
					dto.setDrOfficeDisplay("");
					dto.setDocDelToDisplay("");
					dto.setOtherNameDisplay("");
					dto.setAuthLetterNmDisplay("");
					dto.setRadioSelect("");
					dto.setFilePhoto2(null);
					dto.setDocPath("");
					dto.setNoticeRegNoDisplay("");
					dto.setNoticeRegComDateDisplay("");
					dto.setNoticeLastDelDateDisplay("");
					dto.setNoticeLastDueDateDisplay("");
					dto.setNoticeStatusDisplay("");
					dto.setNoticeStatusDateDisplay("");
					dto.setNoticeUserNameDisplay("");
					dto.setNoticeOfficeNameDisplay("");
					dto.setNoticeRemarks("");
					dto.setNoticePartyRole("");
					dto.setNoticePartyAdrs("");
					dto.setNoticePartyNm("");
					dto.setNoticecumdtls("");
					dto.setNoticeDetails(new ArrayList());
					dto.setPostlAddrs("");
					dto.setPostlDist("");
					dto.setPostlState("");
					dto.setPostlCntry("");
					dto.setPostlDocketNo("");
					dto.setOwmChk("");
					request.removeAttribute("regDetails");
					eForm.setActionName("");
					eForm.setFormName("");
					formName = "";
					actionName = "";
					forwardPage = "sodSearch";
				}

			}

			if (request.getParameter("actionLink") != null) {
				actionName = (String) request.getParameter("actionLink");
			}
			if (actionName.equalsIgnoreCase("hyperClickSigned")) {
				String FILE_UPLOAD_PATH = "";
				PropertiesFileReader proper;
				try {
					proper = PropertiesFileReader.getInstance("resources.igrs");
					FILE_UPLOAD_PATH = proper.getValue("igrs_upload_path") + File.separator + "03" + File.separator;
				} catch (Exception e1) {
					logger.debug(e1.getStackTrace());
				}

				String stCumDt = request.getParameter("regDtCumStatus");
				String stCumDtList[] = stCumDt.split("~");
				String eRegNo = stCumDtList[0];
				String regTxnID = dodBD.getRegTxnID(eRegNo);
				dto.setRegInitNumber(regTxnID);
				dto.setRegNo(eRegNo);
				String path = FILE_UPLOAD_PATH + dto.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
				dto.setGuidUpload("");
				dto.setParentPathSign(path);
				dto.setFileNameSign("signature.GIF");
				dto.setForwardPath("/dod.do?actionLink=SrSignature");
				dto.setForwardName("sigantureDoc");
				String pathSr = FILE_UPLOAD_PATH + dto.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_SR_SIGN;
				dto.setParentPathSrSign(pathSr);
				dto.setFileNameSrSign("signature.GIF");
				dto.setForwardPathSr("/dod.do?actionLink=SrSignature");
				dto.setParentPathGovtLttr(FILE_UPLOAD_PATH + dto.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_GOVT_LETTER);
				dto.setFileNameGovtLttr(RegCompCheckerConstant.FILE_NAME_GOVT_LETTER);
				String pathFP = FILE_UPLOAD_PATH + dto.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
				dto.setFileNameFP("FingerPrint.GIF");
				dto.setParentPathFP(pathFP);
				eForm.setActionName("");
				eForm.setFormName("");
				formName = "";
				actionName = "";
				forwardPage = "signPageView";
			}
			if (actionName.equalsIgnoreCase("SrSignature")) {
				boolean flag = dodBD.putSrSgnOnFinalDoc(dto, userId, officeId);
				if (flag) {
					flag = dodBD.signatureTimeUpdate(dto.getRegNo());//

					if (flag) {
						flag = dodBD.statusUndeliveredUpdate(dto.getRegNo(), userId, officeId);
						dto.setSignatureChkFlag("signed");
					}
				}
				if (flag) {
					dto.setSignatureChkFlag("signed");
				} else {
					dto.setSignatureChkFlag("notSigned");
					if ("en".equalsIgnoreCase(language)) {
						request.setAttribute("signfailure", "Signature is not captured.Please try again");
					} else {
						request.setAttribute("signfailure", "Signature is not captured.Please try again");
					}
				}
				forwardPage = "signPageView";

			}
			if (("SAVE_FILE_PATH_SR_SIGN").equalsIgnoreCase(actionName)) {
				boolean flag = dodBD.putSrSgnOnFinalDoc(dto, userId, officeId);
				if (flag) {
					flag = dodBD.signatureTimeUpdate(dto.getRegNo());//

					if (flag) {
						flag = dodBD.statusUndeliveredUpdate(dto.getRegNo(), userId, officeId);
						dto.setSignatureChkFlag("signed");
					}
				}
				if (flag) {
					dto.setSignatureChkFlag("signed");
				} else {
					dto.setSignatureChkFlag("notSigned");
					if ("en".equalsIgnoreCase(language)) {
						request.setAttribute("signfailure", "Signature is not captured.Please try again");
					} else {
						request.setAttribute("signfailure", "Signature is not captured.Please try again");
					}
				}
				forwardPage = "signPageView";

			}
			if (actionName.equalsIgnoreCase("SrSignatureHand")) {
				dto.setSignatureChkhand("signed");
				forwardPage = "undelivered";

			}
			if (actionName.equalsIgnoreCase("SR_SIGN_DOD_HAND")) {
				dto.setSignatureChkhand("signed");
				forwardPage = "undelivered";

			}
			// form DodIssueNotice
			if (formName != null && formName.equalsIgnoreCase("DodIssueNotice")) {
				boolean ins = false;
				String regcomNo = dto.getNoticeRegNoDisplay();
				String remarks = dto.getNoticeRemarks();
				String lastdt = dto.getNoticeLastDueDateDisplay();
				if (actionName.equalsIgnoreCase("issueNotice")) {
					dto.setNotInsertedNotice(0);
					dto.setIsNoticeIssued(0);
					userId = (String) session.getAttribute("UserId");
					// String offid="OFC90";
					officeId = (String) session.getAttribute("loggedToOffice");
					ins = dodBD.statusNoticeUpdate(regcomNo, userId, officeId, remarks, lastdt, dto.getNoticeDetails());

					if (ins) {

						ArrayList issueDetls = new ArrayList();
						ArrayList comp7 = new ArrayList();
						issueDetls = dodBD.getissueDetls(regcomNo);
						if (issueDetls.size() > 0) {
							for (int i = 0; i < issueDetls.size(); i++) {
								comp7.add((ArrayList) issueDetls.get(i));
								if (comp7 != null && comp7.size() > 0) {
									for (int k = 0; k < comp7.size(); k++) {
										ArrayList compSub = (ArrayList) comp7.get(k);
										dto.setNoticeRegNoDisplay((String) compSub.get(0));
										dto.setNoticeRegComDateDisplay((String) compSub.get(1));
										dto.setNoticeLastDelDateDisplay((String) compSub.get(3));
										dto.setNoticeLastDueDateDisplay((String) compSub.get(4));
										dto.setNoticeStatusDisplay((String) compSub.get(2));
										dto.setNoticeStatusDateDisplay((String) compSub.get(5));
										dto.setNoticeUserNameDisplay((String) compSub.get(6));
										dto.setNoticeOfficeNameDisplay((String) compSub.get(7));
										dto.setNoticeRemarks((String) compSub.get(8));
										dto.setIsNoticeIssued(1);
									}
								}
							}
						}

						ArrayList noticePartyDetl = new ArrayList();
						noticePartyDetl = dodBD.getpartyDetl(regcomNo, language);
						dto.setNoticeDetails(noticePartyDetl);
						request.setAttribute("partyDetails", noticePartyDetl);
						forwardPage = "issueNotice";

					} else {
						dto.setNotInsertedNotice(1);
						forwardPage = "issueNotice";

					}
				}// end of issue action

			}// end of form DodIssueNotice

			// form--dodDestroy
			if (formName != null && formName.equalsIgnoreCase("DodDestroy")) {

				if (actionName.equalsIgnoreCase("destroy")) {
					boolean boo = false;
					dto.setNotInsertedDestroy(0);
					dto.setIsdestroyed(0);
					String regcompNum = dto.getRegNoDisplay();
					userId = (String) session.getAttribute("UserId");
					// String offid="OFC90";
					officeId = (String) session.getAttribute("loggedToOffice");
					boo = dodBD.statusDestroyUpdate(regcompNum, userId, officeId);
					if (boo) {

						ArrayList destroyDetls = new ArrayList();
						ArrayList comp7 = new ArrayList();
						destroyDetls = dodBD.getdestroyDetls(regcompNum);
						if (destroyDetls.size() > 0) {
							for (int i = 0; i < destroyDetls.size(); i++) {
								comp7.add((ArrayList) destroyDetls.get(i));
								if (comp7 != null && comp7.size() > 0) {
									for (int k = 0; k < comp7.size(); k++) {
										ArrayList compSub = (ArrayList) comp7.get(k);
										dto.setRegNoDisplay((String) compSub.get(0));
										dto.setRegComDateDisplay((String) compSub.get(1));
										dto.setLastDelDateDisplay((String) compSub.get(3));
										dto.setLastDueDateDisplay((String) compSub.get(4));
										dto.setStatusDisplay((String) compSub.get(2));
										dto.setStatusDateDisplay((String) compSub.get(5));
										dto.setUserNameDisplay((String) compSub.get(6));
										dto.setOfficeNameDisplay((String) compSub.get(7));
										dto.setDrNameDisplay((String) compSub.get(8));
										dto.setDrOfficeDisplay((String) compSub.get(9));
										dto.setIsdestroyed(1);
										forwardPage = "destroy";
									}
								}
							}
						}
						// dto.setIsdestroyed(1);
					} else {
						dto.setNotInsertedDestroy(1);
						forwardPage = "destroy";
					}
				}// end of action destroy

			}// end of form "dodDestroy"

			// start of form dodUndelivered
			if (formName != null && formName.equalsIgnoreCase("DodUndelivered")) {

				if (actionName.equalsIgnoreCase("radioChk")) {
					String docDelTo = dto.getDocDelToDisplay();
					if (docDelTo.equalsIgnoreCase("Transacting Party")) {
						dto.setPartyOrRep(1);
						dto.setRadioSelect("Transacting Party");
						ArrayList transDetl = new ArrayList();
						transDetl = dodBD.getTransDetl(dto.getRegNoDisplay());
						dto.setTransDetails(transDetl);
						dto.setRepFname("");
						dto.setRepMname("");
						dto.setRepLname("");
						dto.setFilePhoto2(null);
						dto.setDocPath("");
						eForm.setDocName("");
						eForm.setDocPath("");
						request.setAttribute("transDetails", transDetl);
						forwardPage = "undelivered";
					}
					if (docDelTo.equalsIgnoreCase("Representative")) {
						dto.setPartyOrRep(2);
						dto.setRadioSelect("Representative");
						dto.setChckArry("");
						dto.setChckBx("");
						forwardPage = "undelivered";
					}
				}// end of action radio check

				if (actionName.equalsIgnoreCase("changeStatus")) {

					boolean flag = false;
					String arr[] = new String[dto.getClaimantList().size()];

					if (request.getParameterValues("claimantNumber") != null) {
						arr = request.getParameterValues("claimantNumber");
					}
					boolean vaidNumber = true;
					for (int i = 0; i < arr.length; i++) {
						String mob = arr[i];
						if (mob.length() != 10) {
							vaidNumber = false;

						}
					}
					if (arr.length == 0) {
						vaidNumber = false;
						dto.setNotInsertedDestroy(3);
						flag = true;
					}
					dto.setArr(arr);
					String regNum = dto.getRegNoDisplay();
					if (vaidNumber) {

						String regTxnID = dodBD.getRegTxnID(regNum);
						RegCompMkrBD regmkrBD = new RegCompMkrBD();
						dto.setDeedID(regmkrBD.getDeedId(regTxnID));
						dto.setInstID(regmkrBD.getInstId(regTxnID));
						flag = dodBD.insertPinToSmsHand(regTxnID, dto.getDeedID(), dto, userId, arr);
						if (flag)
							dto.setNotInsertedDestroy(2);
					} else {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(RegInitConstant.FAILURE_MSG, "Kindly enter a valid number");
						else
							request.setAttribute(RegInitConstant.FAILURE_MSG, "कृपया मान्य नंबर दर्ज करें|");

					}

					if (flag) {
						logger.debug("PIN SUCCESSFULLY SENT VIA SMS");
						String docDelTo = dto.getDocDelToDisplay();

						boolean ins = false;

						String[] chckIDary = dto.getChckArry().split(",");
						ins = dodBD.statusDeliveredUpdate(regNum, userId, officeId, dto.getRecptName(), dto);
						if (ins) {

							logger.debug("STATUS HAS BEEN CHANGED TO DELIVERED");
							ArrayList deliveredDetls = new ArrayList();
							ArrayList comp7 = new ArrayList();
							deliveredDetls = dodBD.getdeliveredDetls(regNum);
							if (deliveredDetls.size() > 0) {
								for (int i = 0; i < deliveredDetls.size(); i++) {
									comp7.add((ArrayList) deliveredDetls.get(i));
									if (comp7 != null && comp7.size() > 0) {
										for (int k = 0; k < comp7.size(); k++) {
											ArrayList compSub = (ArrayList) comp7.get(k);
											dto.setRegNoDisplay((String) compSub.get(0));
											dto.setRegComDateDisplay((String) compSub.get(1));
											dto.setLastDelDateDisplay((String) compSub.get(3));
											dto.setLastDueDateDisplay((String) compSub.get(4));
											if (language.equalsIgnoreCase("en"))
												dto.setStatusDisplay((String) compSub.get(2));
											else
												dto.setStatusDisplay((String) compSub.get(8));

											dto.setStatusDateDisplay((String) compSub.get(5));
											dto.setUserNameDisplay((String) compSub.get(6));
											if (language.equalsIgnoreCase("en"))
												dto.setOfficeNameDisplay((String) compSub.get(7));
											else
												dto.setOfficeNameDisplay((String) compSub.get(9));
											if (language.equalsIgnoreCase("en"))
												dto.setDocDelToDisplay("Transacting Party");
											else
												dto.setDocDelToDisplay("लेनदेन पार्टी");

										}
									}
								}
							}
							// added by saurav
							String regTxnID = dodBD.getRegTxnID(regNum);
							CLRResponse.insertCLRData(regTxnID);
							// Added by Neeti for RCMS

							/*
							 * String regTxnID = dodBD.getRegTxnID(regNum);
							 * 
							 * 
							 * 
							 * // Get RCMS Flag - check for already delivered //
							 * document String rcmsFlag =
							 * dodBD.getRCMSFlag(regTxnID);
							 * 
							 * if (!(rcmsFlag.equalsIgnoreCase("2") ||
							 * rcmsFlag.equalsIgnoreCase("3") ||
							 * rcmsFlag.equalsIgnoreCase("1"))) { // Check for
							 * deed types required by RCMS boolean deedType =
							 * dodBD.checkDeedTypeForRCMS(regTxnID); if
							 * (deedType) { boolean resp = false;
							 * 
							 * // Check for agricultural land ArrayList
							 * checkPropList =
							 * dodBD.checkPropertyForAgriLand(regTxnID); if
							 * (checkPropList != null &&
							 * !checkPropList.isEmpty()) { resp =
							 * sendingXMLDataToRCMS(regNum, regTxnID);
							 * 
							 * if (resp) {
							 * 
							 * // Print Receipt // Get the registration number
							 * and // pass the same and open print // receipt
							 * window request.getSession().setAttribute("regNo",
							 * regNum);
							 * System.out.println(" Request.getAttribute  :- " +
							 * (String)
							 * request.getSession().getAttribute("regNo"));
							 * PropertiesFileReader pr =
							 * PropertiesFileReader.getInstance
							 * ("resources.igrs"); String finalUrl =
							 * pr.getValue("IGRS_RCMS_PRINT_URL");
							 * eForm.setRcmsPrintUrl(finalUrl);
							 * request.getSession().setAttribute("rcmsUrl",
							 * finalUrl); System.out.println("RCMS URL :- " +
							 * finalUrl); }
							 * 
							 * }
							 * 
							 * } }
							 */
							// String CLRFlag = dodBD.getRCMSFlag(regTxnID);
							// CLRResponse.insertCLRData(regTxnID);
							forwardPage = "deliveredConfrm";
						} else {

							dto.setNotInsertedDestroy(1);
							forwardPage = "undelivered";
							logger.debug("STATUS CHANGE FAILURE");
						}

					} else {
						logger.debug("PIN FAILURE WHEN SENDING");
						dto.setNotInsertedDestroy(1);
						forwardPage = "undelivered";
					}
				}

				/*
				 * userId = (String)session.getAttribute("UserId"); //String
				 * offid="OFC90"; officeId =
				 * (String)session.getAttribute("loggedToOffice");logger.debug(
				 * "Inside undelivered change status method----the reg no is-->>"
				 * +regno);
				 */

				// old flow
				/*
				 * if(docDelTo.equalsIgnoreCase("Transacting Party")){ boolean
				 * ins=false; if(dto.getChckArry().contains(",")){ String[]
				 * chckIDary= dto.getChckArry().split(",");
				 * ins=dodBD.statusDeliveredUpdate(regno, userId, officeId,
				 * chckIDary); }else{ String ary[] = new String[1];
				 * ary[0]=dto.getChckArry();
				 * ins=dodBD.statusDeliveredUpdate(regno, userId, officeId,
				 * ary);
				 * 
				 * }
				 * 
				 * if(ins){
				 * 
				 * ArrayList deliveredDetls = new ArrayList(); ArrayList comp7 =
				 * new ArrayList();
				 * deliveredDetls=dodBD.getdeliveredDetls(regno); if
				 * (deliveredDetls.size()>0){ for(int i =0;
				 * i<deliveredDetls.size(); i++){
				 * comp7.add((ArrayList)deliveredDetls.get(i)); if(comp7 != null
				 * && comp7.size()>0){ for (int k=0; k< comp7.size(); k++){
				 * ArrayList compSub = (ArrayList)comp7.get(k);
				 * dto.setRegNoDisplay((String) compSub.get(0));
				 * dto.setRegComDateDisplay((String) compSub.get(1));
				 * dto.setLastDelDateDisplay((String) compSub.get(3));
				 * dto.setLastDueDateDisplay((String) compSub.get(4));
				 * dto.setStatusDisplay((String) compSub.get(2));
				 * dto.setStatusDateDisplay((String) compSub.get(5));
				 * dto.setUserNameDisplay((String) compSub.get(6));
				 * dto.setOfficeNameDisplay((String) compSub.get(7));
				 * dto.setDocDelToDisplay("Transacting Party");
				 * 
				 * } } } } ArrayList repDetl = new ArrayList(); repDetl=
				 * dodBD.getrecpDetl(regno);
				 * dto.setTransDetailsDisplay(repDetl);
				 * request.setAttribute("repDetails", repDetl);
				 * dto.setPartyOrRep(1); forwardPage="deliveredConfrm"; } else{
				 * dto.setNotInsertedDestroy(1); forwardPage="undelivered"; }
				 * }//end of if transacting party
				 * 
				 * if(docDelTo.equalsIgnoreCase("Representative")){
				 * dto.setPartyOrRep(2); String fname = dto.getRepFname();
				 * String mname = dto.getRepMname(); String lname =
				 * dto.getRepLname(); String docName= eForm.getDocName(); String
				 * docPath = eForm.getDocPath();
				 * 
				 * boolean ins=false; ins=dodBD.statusDeliveredUpdate1(regno,
				 * userId, officeId,fname,mname,lname,docName,docPath); if(ins){
				 * 
				 * 
				 * ArrayList deliveredDetls = new ArrayList(); ArrayList comp7 =
				 * new ArrayList();
				 * deliveredDetls=dodBD.getdeliveredDetls(regno); if
				 * (deliveredDetls.size()>0){ for(int i =0;
				 * i<deliveredDetls.size(); i++){
				 * comp7.add((ArrayList)deliveredDetls.get(i)); if(comp7 != null
				 * && comp7.size()>0){ for (int k=0; k< comp7.size(); k++){
				 * ArrayList compSub = (ArrayList)comp7.get(k);
				 * dto.setRegNoDisplay((String) compSub.get(0));
				 * dto.setRegComDateDisplay((String) compSub.get(1));
				 * dto.setLastDelDateDisplay((String) compSub.get(3));
				 * dto.setLastDueDateDisplay((String) compSub.get(4));
				 * dto.setStatusDisplay((String) compSub.get(2));
				 * dto.setStatusDateDisplay((String) compSub.get(5));
				 * dto.setUserNameDisplay((String) compSub.get(6));
				 * dto.setOfficeNameDisplay((String) compSub.get(7));
				 * dto.setDocDelToDisplay("Representative");
				 * 
				 * } } } } String rprsntatvNm=""; String authLtrName=""; String
				 * authLtrPath=""; ArrayList rprsntatvDetls = new ArrayList();
				 * ArrayList comp8 = new ArrayList();
				 * rprsntatvDetls=dodBD.getrprsntatvDetls(regno); if
				 * (rprsntatvDetls.size()>0){ for(int i =0;
				 * i<rprsntatvDetls.size(); i++){
				 * comp8.add((ArrayList)rprsntatvDetls.get(i)); if(comp7 != null
				 * && comp8.size()>0){ for (int k=0; k< comp8.size(); k++){
				 * ArrayList compSub = (ArrayList)comp8.get(k); rprsntatvNm =
				 * (String) compSub.get(0); authLtrName = (String)
				 * compSub.get(1); authLtrPath = (String) compSub.get(2); } } }
				 * } //dto.setIsrepresentative(1);
				 * dto.setDocDelToDisplay("Representative");
				 * dto.setOtherNameDisplay(rprsntatvNm);
				 * dto.setAuthLetterNmDisplay(authLtrName);
				 * dto.setDocPath(authLtrPath+"/"+authLtrName);
				 * dto.setPartyOrRep(2); forwardPage="deliveredConfrm"; }
				 * 
				 * else{ dto.setNotInsertedDestroy(1);
				 * forwardPage="undelivered"; } }// end of if representative }
				 */
				// end of action change status
				if (actionName.equalsIgnoreCase("changeStatusP")) {
					dto.setNotInsertedDestroy(0);
					String docDelTo = dto.getDocDelToDisplay();
					String regno = dto.getRegNoDisplay();
					userId = (String) session.getAttribute("UserId");
					// String offid="OFC90";
					officeId = (String) session.getAttribute("loggedToOffice");
					logger.debug("Inside undelivered change status method----the reg no is-->>" + regno);

					if (docDelTo.equalsIgnoreCase("Transacting Party")) {
						boolean ins = false;
						if (dto.getChckArry().contains(",")) {
							// String[] chckIDary= dto.getChckArry().split(",");
							ins = dodBD.statusDeliveredUpdate(regno, userId, officeId, dto.getRecptName(), dto);
						} else {
							String ary[] = new String[1];
							// ary[0]=dto.getChckArry();
							ins = dodBD.statusDeliveredUpdateP(regno, userId, officeId, dto.getRecptName());

						}

						if (ins) {

							ArrayList deliveredDetls = new ArrayList();
							ArrayList comp7 = new ArrayList();
							deliveredDetls = dodBD.getdeliveredDetls(regno);
							if (deliveredDetls.size() > 0) {
								for (int i = 0; i < deliveredDetls.size(); i++) {
									comp7.add((ArrayList) deliveredDetls.get(i));
									if (comp7 != null && comp7.size() > 0) {
										for (int k = 0; k < comp7.size(); k++) {
											ArrayList compSub = (ArrayList) comp7.get(k);
											dto.setRegNoDisplay((String) compSub.get(0));
											dto.setRegComDateDisplay((String) compSub.get(1));
											dto.setLastDelDateDisplay((String) compSub.get(3));
											dto.setLastDueDateDisplay((String) compSub.get(4));
											dto.setStatusDisplay((String) compSub.get(2));
											dto.setStatusDateDisplay((String) compSub.get(5));
											dto.setUserNameDisplay((String) compSub.get(6));
											dto.setOfficeNameDisplay((String) compSub.get(7));
											dto.setDocDelToDisplay("Transacting Party");

										}
									}
								}
							}
							ArrayList repDetl = new ArrayList();
							repDetl = dodBD.getrecpDetl(regno);
							dto.setTransDetailsDisplay(repDetl);
							request.setAttribute("repDetails", repDetl);
							dto.setPartyOrRep(1);

							// Added by Neeti for RCMS

							String regTxnID = dodBD.getRegTxnID(regno);
							CLRResponse.insertCLRData(regTxnID);
							// Get RCMS Flag - check for already delivered
							// document
							String rcmsFlag = dodBD.getRCMSFlag(regTxnID);

							// Check for deed types required by RCMS
							if (!(rcmsFlag.equalsIgnoreCase("2") || rcmsFlag.equalsIgnoreCase("3") || rcmsFlag.equalsIgnoreCase("1"))) {
								boolean deedType = dodBD.checkDeedTypeForRCMS(regTxnID);
								if (deedType) {
									boolean resp = false;

									// Check for agricultural land
									ArrayList checkPropList = dodBD.checkPropertyForAgriLand(regTxnID);
									if (checkPropList != null && !checkPropList.isEmpty()) {
										// resp = sendingXMLDataToRCMS(regno,
										// regTxnID);

										if (resp) {

											// Print Receipt
											// Get the registration number and
											// pass the same and open print
											// receipt window
											request.getSession().setAttribute("regNo", regno);
											System.out.println(" Request.getAttribute  :- " + (String) request.getSession().getAttribute("regNo"));
											PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
											String finalUrl = pr.getValue("IGRS_RCMS_PRINT_URL");
											eForm.setRcmsPrintUrl(finalUrl);
											request.getSession().setAttribute("rcmsUrl", finalUrl);
											System.out.println("RCMS URL :- " + finalUrl);
										}

									}

								}
							}

							forwardPage = "deliveredConfrm";
						} else {
							dto.setNotInsertedDestroy(1);
							forwardPage = "undelivered";
						}
					}// end of if transacting party

					if (docDelTo.equalsIgnoreCase("Representative")) {
						dto.setPartyOrRep(2);
						String fname = dto.getRepFname();
						String mname = dto.getRepMname();
						String lname = dto.getRepLname();
						String docName = eForm.getDocName();
						String docPath = eForm.getDocPath();

						boolean ins = false;
						ins = dodBD.statusDeliveredUpdateP1(regno, userId, officeId, fname, mname, lname, docName, docPath);
						if (ins) {

							ArrayList deliveredDetls = new ArrayList();
							ArrayList comp7 = new ArrayList();
							deliveredDetls = dodBD.getdeliveredDetls(regno);
							if (deliveredDetls.size() > 0) {
								for (int i = 0; i < deliveredDetls.size(); i++) {
									comp7.add((ArrayList) deliveredDetls.get(i));
									if (comp7 != null && comp7.size() > 0) {
										for (int k = 0; k < comp7.size(); k++) {
											ArrayList compSub = (ArrayList) comp7.get(k);
											dto.setRegNoDisplay((String) compSub.get(0));
											dto.setRegComDateDisplay((String) compSub.get(1));
											dto.setLastDelDateDisplay((String) compSub.get(3));
											dto.setLastDueDateDisplay((String) compSub.get(4));
											dto.setStatusDisplay((String) compSub.get(2));
											dto.setStatusDateDisplay((String) compSub.get(5));
											dto.setUserNameDisplay((String) compSub.get(6));
											dto.setOfficeNameDisplay((String) compSub.get(7));
											dto.setDocDelToDisplay("Representative");

										}
									}
								}
							}
							String rprsntatvNm = "";
							String authLtrName = "";
							String authLtrPath = "";
							ArrayList rprsntatvDetls = new ArrayList();
							ArrayList comp8 = new ArrayList();
							rprsntatvDetls = dodBD.getrprsntatvDetls(regno);
							if (rprsntatvDetls.size() > 0) {
								for (int i = 0; i < rprsntatvDetls.size(); i++) {
									comp8.add((ArrayList) rprsntatvDetls.get(i));
									if (comp7 != null && comp8.size() > 0) {
										for (int k = 0; k < comp8.size(); k++) {
											ArrayList compSub = (ArrayList) comp8.get(k);
											rprsntatvNm = (String) compSub.get(0);
											authLtrName = (String) compSub.get(1);
											authLtrPath = (String) compSub.get(2);
										}
									}
								}
							}
							// dto.setIsrepresentative(1);
							dto.setDocDelToDisplay("Representative");
							dto.setOtherNameDisplay(rprsntatvNm);
							dto.setAuthLetterNmDisplay(authLtrName);
							dto.setDocPath(authLtrPath + "/" + authLtrName);
							dto.setPartyOrRep(2);
							// Added by Neeti for RCMS

							String regTxnID = dodBD.getRegTxnID(regno);
							CLRResponse.insertCLRData(regTxnID);

							// Get RCMS Flag - check for already delivered
							// document
							String rcmsFlag = dodBD.getRCMSFlag(regTxnID);

							// Check for deed types required by RCMS
							if (!(rcmsFlag.equalsIgnoreCase("2") || rcmsFlag.equalsIgnoreCase("3") || rcmsFlag.equalsIgnoreCase("1"))) {
								boolean deedType = dodBD.checkDeedTypeForRCMS(regTxnID);
								if (deedType) {
									boolean resp = false;

									// Check for agricultural land
									ArrayList checkPropList = dodBD.checkPropertyForAgriLand(regTxnID);
									if (checkPropList != null && !checkPropList.isEmpty()) {
										// resp = sendingXMLDataToRCMS(regno,
										// regTxnID);

										if (resp) {

											// Print Receipt
											// Get the registration number and
											// pass the same and open print
											// receipt window
											request.getSession().setAttribute("regNo", regno);
											System.out.println(" Request.getAttribute  :- " + (String) request.getSession().getAttribute("regNo"));
											PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
											String finalUrl = pr.getValue("IGRS_RCMS_PRINT_URL");
											eForm.setRcmsPrintUrl(finalUrl);
											request.getSession().setAttribute("rcmsUrl", finalUrl);
											System.out.println("RCMS URL :- " + finalUrl);
										}

									}

								}
							}
							forwardPage = "deliveredConfrm";
						}

						else {
							dto.setNotInsertedDestroy(1);
							forwardPage = "undelivered";
						}
					}// end of if representative

					ArrayList issueDetls = new ArrayList();
					issueDetls = dodBD.getissueDetls(regno);
					if (issueDetls.size() > 0) {
						dto.setIsLinkToBeShown(1);
					}

				}// end of action change statusP

				if ("setUploadFile".equalsIgnoreCase(actionName)) {

					ArrayList errorList = new ArrayList();
					FormFile uploadedFile = dto.getFilePhoto2();
					if ("".equals(uploadedFile.getFileName())) {
						errorList.add("Invalid file selection. Please try again.");
					}
					String fileExt = getFileExtension(uploadedFile.getFileName());
					AuditInspectionRule rule = new AuditInspectionRule();
					rule.validateFileType(fileExt);
					int size = uploadedFile.getFileSize();
					double fileSizeInBytes = size;
					double fileSizeInKB = fileSizeInBytes / 1024.0;
					double fileSizeInMB = fileSizeInKB / 1024.0;
					DecimalFormat decim = new DecimalFormat("#.##");
					Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
					String photoSize = "(" + fileSize + "MB)";
					if (rule.isError()) {
						errorList.add("Invalid file type. Please select another file.");
						request.setAttribute("errorsList", errorList);
					} else {
						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
							errorList.add("File size is Greater than 10 MB. Please select another file.");
							request.setAttribute("errorsList", errorList);
						} else {
							PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");

							String docName = uploadedFile.getFileName();
							String docPath = proper.getValue("igrs_upload_path") + File.separator + "10" + File.separator + dto.getRegNoDisplay();
							eForm.setDocName(uploadedFile.getFileName());
							eForm.setDocPath(docPath);
							/*
							 * dto.setFilePhoto2(uploadedFile);
							 * eForm.getDoddto().setFilePhoto2(uploadedFile);
							 */
							FormFile photo = eForm.getDoddto().getFilePhoto2();
							boolean up = uploadFile(dto.getFilePhoto2(), docName, docPath);
							forwardPage = "undelivered";
						}
					}
				}// end of setUpload

				if (actionName.equalsIgnoreCase("NEXT_TO_PAYMENT_ACTION")) {

					DecimalFormat myformatter = new DecimalFormat("############");
					String docDelTo = dto.getDocDelToDisplay();
					String regno = dto.getRegNoDisplay();
					String payableAmt = dto.getPayableFee();
					userId = (String) session.getAttribute("UserId");
					dto.setNotInsertedDestroyP(0);
					dto.setIsPartial(0);
					// String offid="OFC90";
					officeId = (String) session.getAttribute("loggedToOffice");
					String officeName = "";
					String districtId = "";
					String districtName = "";
					officeId = (String) session.getAttribute("loggedToOffice");
					ArrayList alist = dodBD.getrequestDetails(officeId);
					if (alist.size() > 0) {
						ArrayList rowList = (ArrayList) alist.get(0);
						officeName = (String) rowList.get(0);
						districtId = (String) rowList.get(1);
						districtName = (String) rowList.get(2);
					}
					logger.debug("Inside undelivered change status method----the reg no is-->>" + regno);

					ArrayList first = new ArrayList();
					first = dodBD.getPayDtls(regno);
					if (first.size() == 0) {
						logger.debug("inside if");
						boolean insrt = false;
						try {
							String id = dodBD.getId();
							insrt = dodBD.insertPay(regno, payableAmt, userId, id);

							if (insrt) {
								logger.debug("inside if insrt");

								request.setAttribute("parentModName", "Registration Process");
								request.setAttribute("parentFunName", "Delivery of Document");
								request.setAttribute("parentFunId", "FUN_207");
								request.setAttribute("parentAmount", payableAmt);
								request.setAttribute("parentTable", "IGRS_DEL_DOC_PAYMENT_DETLS");
								request.setAttribute("parentKey", id);
								request.setAttribute("forwardPath", "./dod.do?TRFS=NGI");
								request.setAttribute("parentColumnName", "PAYMENT_DETL_ID");
								request.setAttribute("parentOfficeId", officeId);
								request.setAttribute("parentOfficeName", officeName);
								request.setAttribute("parentDistrictId", districtId);
								request.setAttribute("parentDistrictName", districtName);
								request.setAttribute("parentReferenceId", regno);
								logger.debug("just before redirection");
								forwardPage = "nextToPay";

							} else {
								dto.setNotInsertedDestroyP(1);
								forwardPage = "undelivered";
							}
						} catch (Exception e) {
							dto.setNotInsertedDestroyP(1);
							forwardPage = "undelivered";
						}
					}
					// if pay details are not empty
					else {
						logger.debug("inside else---means record is found");
						ArrayList comp = new ArrayList();
						String uniqId = "";
						String amtToBePaid = "";
						String paidAmt = "";
						String pymtFlg = "";

						for (int i = 0; i < first.size(); i++) {
							comp.add((ArrayList) first.get(i));
							if (comp != null && comp.size() > 0) {
								for (int k = 0; k < comp.size(); k++) {
									ArrayList compSub = (ArrayList) comp.get(k);
									uniqId = (String) compSub.get(0);
									amtToBePaid = (String) compSub.get(1);
									paidAmt = (String) compSub.get(2);
									pymtFlg = (String) compSub.get(3);
									// logger.debug("unique id is ............................"+uniqId);
									// /logger.debug("unpayable amt is ............................"+amtToBePaid);
									// logger.debug("paid amt is..................................."+paidAmt);
									// logger.debug("pymtFlg is....................................."+pymtFlg);
								}
							}
						}
						double damtToBePaid = Double.parseDouble(amtToBePaid);

						// ------- if payment flag is I ----
						if (pymtFlg.equalsIgnoreCase("I")) {

							request.setAttribute("parentModName", "Registration Process");
							request.setAttribute("parentFunName", "Delivery of Document");
							request.setAttribute("parentFunId", "FUN_207");
							request.setAttribute("parentAmount", amtToBePaid);
							request.setAttribute("parentTable", "IGRS_DEL_DOC_PAYMENT_DETLS");
							request.setAttribute("parentKey", uniqId);
							request.setAttribute("forwardPath", "./dod.do?TRFS=NGI");
							request.setAttribute("parentColumnName", "PAYMENT_DETL_ID");
							request.setAttribute("parentOfficeId", officeId);
							request.setAttribute("parentOfficeName", officeName);
							request.setAttribute("parentDistrictId", districtId);
							request.setAttribute("parentDistrictName", districtName);
							request.setAttribute("parentReferenceId", regno);

							forwardPage = "nextToPay";
						}
						// ----------- if payment flag is P -----

						if (pymtFlg.equalsIgnoreCase("P")) {

							double dpaidAmt = Double.parseDouble(paidAmt);
							// condition 1-------------------pending
							// amount-------------
							if (damtToBePaid > dpaidAmt) {
								double bal = damtToBePaid - dpaidAmt;
								String balfrmt = myformatter.format(bal);
								dto.setPayableFee(balfrmt);
								boolean insrt = false;

								try {
									String id = dodBD.getId();
									insrt = dodBD.insertPay(regno, balfrmt, userId, id);

									if (insrt) {
										logger.debug("inside if insrt-----of else condition---means record found");
										request.setAttribute("parentModName", "Registration Process");
										request.setAttribute("parentFunName", "Delivery of Document");
										request.setAttribute("parentFunId", "FUN_207");
										request.setAttribute("parentAmount", balfrmt);
										request.setAttribute("parentTable", "IGRS_DEL_DOC_PAYMENT_DETLS");
										request.setAttribute("parentKey", id);
										request.setAttribute("forwardPath", "./dod.do?TRFS=NGI");
										request.setAttribute("parentColumnName", "PAYMENT_DETL_ID");
										request.setAttribute("parentOfficeId", officeId);
										request.setAttribute("parentOfficeName", officeName);
										request.setAttribute("parentDistrictId", districtId);
										request.setAttribute("parentDistrictName", districtName);
										request.setAttribute("parentReferenceId", regno);

										forwardPage = "nextToPay";

									} else {
										dto.setNotInsertedDestroyP(1);
										forwardPage = "undelivered";
									}
								} catch (Exception e) {
									dto.setNotInsertedDestroyP(1);
									forwardPage = "undelivered";
								}

							}
						}
					}// end of pay details not empty
				}// end of next to payment action

			}// end of form dodUndelivered

			// form DodUndeliveredPost
			if (formName != null && formName.equalsIgnoreCase("DodUndeliveredPost")) {
				boolean ins = false;
				String regcomNo = dto.getRegNoDisplay();
				String docketn = dto.getPostlDocketNo();
				if (actionName.equalsIgnoreCase("changeStatusPost")) {
					dto.setNotInsertedPost(0);
					dto.setIsSavedPost(0);
					userId = (String) session.getAttribute("UserId");
					officeId = (String) session.getAttribute("loggedToOffice");
					// String offid="OFC90";
					String regNum = dto.getRegNoDisplay();
					String regTxnID = dodBD.getRegTxnID(regNum);
					if (dto.getPinRequired().equalsIgnoreCase("N")) {
						forwardPage = "undeliveredPost";
					} else {

						boolean flag = dodBD.updatePinLinking(regTxnID, dto.getDeedID(), dto, userId);
						if (flag) {
							logger.debug("PIN NUMBER SUCCESSFULLY SEND VIA SMS");
						}

					}
					ins = dodBD.statusDeliveredUpdatePost(regcomNo, userId, officeId, docketn);

					if (ins) {

						ArrayList deliveredDetls = new ArrayList();
						ArrayList comp7 = new ArrayList();
						deliveredDetls = dodBD.getdeliveredDetlsPost(regcomNo);
						if (deliveredDetls.size() > 0) {
							for (int i = 0; i < deliveredDetls.size(); i++) {
								comp7.add((ArrayList) deliveredDetls.get(i));
								if (comp7 != null && comp7.size() > 0) {
									for (int k = 0; k < comp7.size(); k++) {
										ArrayList compSub = (ArrayList) comp7.get(k);
										dto.setRegNoDisplay((String) compSub.get(0));
										dto.setRegComDateDisplay((String) compSub.get(1));
										dto.setLastDelDateDisplay((String) compSub.get(3));
										dto.setLastDueDateDisplay((String) compSub.get(4));
										dto.setStatusDisplay((String) compSub.get(2));
										dto.setStatusDateDisplay((String) compSub.get(5));
										dto.setUserNameDisplay((String) compSub.get(6));
										dto.setOfficeNameDisplay((String) compSub.get(7));
										dto.setPostlDocketNo((String) compSub.get(8));
									}
								}
							}
						}
						ArrayList modedetl = new ArrayList();
						ArrayList sublst = new ArrayList();
						modedetl = dodBD.getmodDetls(regcomNo);
						logger.debug("size of arraylist is :- " + modedetl.size());
						if (modedetl.size() > 0) {
							String adres = "";
							String dstrct = "";
							String state = "";
							String cntry = "";
							for (int i = 0; i < modedetl.size(); i++) {
								sublst.add((ArrayList) modedetl.get(i));
								if (sublst != null && sublst.size() > 0) {
									for (int k = 0; k < sublst.size(); k++) {
										ArrayList compSub = (ArrayList) sublst.get(k);
										adres = (String) compSub.get(0);
										dstrct = (String) compSub.get(1);
										state = (String) compSub.get(2);
										cntry = (String) compSub.get(3);
									}
								}
							}
							dto.setPostlAddrs(adres);
							dto.setPostlCntry(cntry);
							dto.setPostlDist(dstrct);
							dto.setPostlState(state);
							dto.setIsSavedPost(1);

							// Added by Neeti for RCMS

							// Get RCMS Flag - check for already delivered
							// document
							String rcmsFlag = dodBD.getRCMSFlag(regTxnID);

							CLRResponse.insertCLRData(regTxnID);
							if (!(rcmsFlag.equalsIgnoreCase("2") || rcmsFlag.equalsIgnoreCase("3") || rcmsFlag.equalsIgnoreCase("1"))) {
								// Check for deed types required by RCMS
								boolean deedType = dodBD.checkDeedTypeForRCMS(regTxnID);
								if (deedType) {
									boolean resp = false;

									// Check for agricultural land
									ArrayList checkPropList = dodBD.checkPropertyForAgriLand(regTxnID);
									if (checkPropList != null && !checkPropList.isEmpty()) {
										// resp = sendingXMLDataToRCMS(regcomNo,
										// regTxnID);

										if (resp) {
											// Print Receipt

											// Get the registration number and
											// pass the same and open print
											// receipt window
											request.getSession().setAttribute("regNo", regcomNo);
											System.out.println(" Request.getAttribute  :- " + (String) request.getSession().getAttribute("regNo"));
											PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
											String finalUrl = pr.getValue("IGRS_RCMS_PRINT_URL");
											eForm.setRcmsPrintUrl(finalUrl);
											request.getSession().setAttribute("rcmsUrl", finalUrl);
											System.out.println("RCMS URL :- " + finalUrl);
										}
									}

								}
							}
							forwardPage = "undeliveredPost";
						}

					} else {
						dto.setNotInsertedPost(1);
						forwardPage = "undeliveredPost";

					}
				}// end of change status post action

			}// end of form un delivered post

			// start of DodDeliveredConfirm
			if (formName != null && formName.equalsIgnoreCase("DodDeliveredConfirm")) {

				if (actionName.equalsIgnoreCase("retrieve")) {
					String partyType = "";
					String filePath = dto.getDocPath();
					// logger.debug("retrieval path-->"+filePath);
					/*
					 * DMSUtility dmsUtil=new DMSUtility();
					 * dmsUtil.readImage(filePath); actionName=""; formName="";
					 */

					// set the http content type to "APPLICATION/OCTET-STREAM
					response.setContentType("application/download");
					// initialize the http content-disposition header to
					// indicate a file attachment with the default filename
					// "myFile.txt"
					// String fileName = (String)formDTO.getCompThumbPath();
					// Filename=\"myFile.txt\"";
					response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filePath, "UTF-8"));

					File fileToDownload = new File(filePath);
					FileInputStream fileInputStream = new FileInputStream(fileToDownload);
					OutputStream out = response.getOutputStream();
					byte[] buf = new byte[2048];

					int readNum;
					while ((readNum = fileInputStream.read(buf)) != -1) {
						out.write(buf, 0, readNum);
					}
					fileInputStream.close();
					out.close();
					forwardPage = "deliveredConfrm";

				}

			}// end of DodDeliveredConfirm

			// start for DR-------------------------------------------------

			if (formName != null && formName.equalsIgnoreCase("DodHomePageDR")) {

				// --------------------action
				// 2----------------------------------
				if (request.getParameter("actionLink") != null) {
					actionName = (String) request.getParameter("actionLink");
				}
				if (!(actionName).equalsIgnoreCase("")) {
					if (actionName.equalsIgnoreCase("hyperClickDr")) {
						String regComNo = "";
						String regComDt = "";
						String docStatusId = "";
						String docStatusName = "";
						String oneMonthDt = "";
						String lastDelDt = "";
						String lastDueDt = "";
						String stChngeDt = "";
						String srDrName = "";
						String officeName = "";
						String receipntId = "";
						String rprsntatvNm = "";
						String authLtrName = "";
						String authLtrPath = "";
						String stCumDt = request.getParameter("regDtCumStatus");
						String stCumDtList[] = stCumDt.split("~");
						if (stCumDtList.length == 12) {
							regComNo = stCumDtList[0];
							// logger.debug("the completion number in the param is------>"+regComNo);
							regComDt = stCumDtList[1];
							// logger.debug("the completion date in the param is------>"+regComDt);
							docStatusId = stCumDtList[2];
							// logger.debug("the Document status in the param is------>"+docStatusId);
							receipntId = stCumDtList[10];
							// logger.debug("the Receipient Id  in the param is------>"+receipntId);
						}
						// ----- if status is delivered-----
						if (docStatusId.equalsIgnoreCase("7")) {
							dto.setIsLinkToBeShown(0);

							ArrayList modedetl = new ArrayList();
							ArrayList sublst = new ArrayList();
							modedetl = dodBD.getmodDetls(regComNo);
							logger.debug("size of arraylist is :- " + modedetl.size());
							if (modedetl.size() > 0) {
								String adres = "";
								String dstrct = "";
								String state = "";
								String cntry = "";
								for (int i = 0; i < modedetl.size(); i++) {
									sublst.add((ArrayList) modedetl.get(i));
									if (sublst != null && sublst.size() > 0) {
										for (int k = 0; k < sublst.size(); k++) {
											ArrayList compSub = (ArrayList) sublst.get(k);
											adres = (String) compSub.get(0);
											dstrct = (String) compSub.get(1);
											state = (String) compSub.get(2);
											cntry = (String) compSub.get(3);
										}
									}
								}
								dto.setRegNoDisplay(regComNo);
								dto.setRegComDateDisplay(regComDt);
								dto.setLastDelDateDisplay(stCumDtList[5]);
								dto.setLastDueDateDisplay(stCumDtList[6]);
								dto.setStatusDisplay(stCumDtList[3]);
								dto.setStatusDateDisplay(stCumDtList[7]);
								dto.setUserNameDisplay(stCumDtList[8]);
								dto.setOfficeNameDisplay(stCumDtList[9]);
								dto.setPostlAddrs(adres);
								dto.setPostlDist(dstrct);
								dto.setPostlState(state);
								dto.setPostlCntry(cntry);
								dto.setPostlDocketNo(stCumDtList[11]);
								dto.setNotInsertedPost(0);
								dto.setIsSavedPost(1);
								actionName = "";
								formName = "";
								forwardPage = "undeliveredPost";

							} else {

								if (receipntId.equalsIgnoreCase("1") && receipntId != null) {
									dto.setDocDelToDisplay("Transacting Party");
									ArrayList repDetl = new ArrayList();
									repDetl = dodBD.getrecpDetl(regComNo);
									dto.setTransDetailsDisplay(repDetl);
									request.setAttribute("repDetails", repDetl);
									dto.setPartyOrRep(1);

								}

								else if (receipntId.equalsIgnoreCase("2") && receipntId != null) {
									ArrayList rprsntatvDetls = new ArrayList();
									ArrayList comp7 = new ArrayList();
									rprsntatvDetls = dodBD.getrprsntatvDetls(regComNo);
									if (rprsntatvDetls.size() > 0) {
										for (int i = 0; i < rprsntatvDetls.size(); i++) {
											comp7.add((ArrayList) rprsntatvDetls.get(i));
											if (comp7 != null && comp7.size() > 0) {
												for (int k = 0; k < comp7.size(); k++) {
													ArrayList compSub = (ArrayList) comp7.get(k);
													rprsntatvNm = (String) compSub.get(0);
													authLtrName = (String) compSub.get(1);
													authLtrPath = (String) compSub.get(2);
												}
											}
										}
									}
									// dto.setIsrepresentative(1);
									dto.setDocDelToDisplay("Representative");
									dto.setOtherNameDisplay(rprsntatvNm);
									dto.setAuthLetterNmDisplay(authLtrName);
									dto.setDocPath(authLtrPath + "/" + authLtrName);
									dto.setPartyOrRep(2);
								}

								ArrayList issueDetls = new ArrayList();
								issueDetls = dodBD.getissueDetls(regComNo);
								if (issueDetls.size() > 0) {
									dto.setIsLinkToBeShown(1);
								}

								dto.setRegNoDisplay(regComNo);
								dto.setRegComDateDisplay(regComDt);
								dto.setLastDelDateDisplay(stCumDtList[5]);
								dto.setLastDueDateDisplay(stCumDtList[6]);
								dto.setStatusDisplay(stCumDtList[3]);
								dto.setStatusDateDisplay(stCumDtList[7]);
								dto.setUserNameDisplay(stCumDtList[8]);
								dto.setOfficeNameDisplay(stCumDtList[9]);
								eForm.setActionName("");
								eForm.setFormName("");
								request.setAttribute("dod", eForm);
								forwardPage = "deliveredConfrm";
							}// end of postal condition

						}// end of if status is Delivered.

						// -----if status is destroyed------
						if (docStatusId.equalsIgnoreCase("6")) {
							ArrayList destroyDetls = new ArrayList();
							ArrayList comp7 = new ArrayList();
							destroyDetls = dodBD.getdestroyDetls(regComNo);
							if (destroyDetls.size() > 0) {
								for (int i = 0; i < destroyDetls.size(); i++) {
									comp7.add((ArrayList) destroyDetls.get(i));
									if (comp7 != null && comp7.size() > 0) {
										for (int k = 0; k < comp7.size(); k++) {
											ArrayList compSub = (ArrayList) comp7.get(k);
											dto.setRegNoDisplay((String) compSub.get(0));
											dto.setRegComDateDisplay((String) compSub.get(1));
											dto.setLastDelDateDisplay((String) compSub.get(3));
											dto.setLastDueDateDisplay((String) compSub.get(4));
											dto.setStatusDisplay((String) compSub.get(2));
											dto.setStatusDateDisplay((String) compSub.get(5));
											dto.setUserNameDisplay((String) compSub.get(6));
											dto.setOfficeNameDisplay((String) compSub.get(7));
											dto.setDrNameDisplay((String) compSub.get(8));
											dto.setDrOfficeDisplay((String) compSub.get(9));
											dto.setIsdestroyed(1);
											forwardPage = "destroy";
										}
									}
								}
							}
						} // --end of status "destroyed"----

						// -----if status is pending for DR Approval------
						if (docStatusId.equalsIgnoreCase("4")) {
							dto.setRegNoDisplay(regComNo);
							dto.setRegComDateDisplay(regComDt);
							dto.setLastDelDateDisplay(stCumDtList[5]);
							dto.setLastDueDateDisplay(stCumDtList[6]);
							dto.setStatusDisplay(stCumDtList[3]);
							dto.setStatusDateDisplay(stCumDtList[7]);
							dto.setUserNameDisplay(stCumDtList[8]);
							dto.setOfficeNameDisplay(stCumDtList[9]);
							forwardPage = "drViewApproval";
						}// --end of pending for approval

						// ---if status is approved for destruction----
						if (docStatusId.equalsIgnoreCase("5")) {

							ArrayList destroyDetls = new ArrayList();
							ArrayList comp7 = new ArrayList();
							destroyDetls = dodBD.getDrDetl(regComNo);
							if (destroyDetls.size() > 0) {
								for (int i = 0; i < destroyDetls.size(); i++) {
									comp7.add((ArrayList) destroyDetls.get(i));
									if (comp7 != null && comp7.size() > 0) {
										for (int k = 0; k < comp7.size(); k++) {
											ArrayList compSub = (ArrayList) comp7.get(k);
											dto.setDrNameDisplay((String) compSub.get(0));
											dto.setDrOfficeDisplay((String) compSub.get(1));

										}
									}
								}
							}
							dto.setRegNoDisplay(regComNo);
							dto.setRegComDateDisplay(regComDt);
							dto.setLastDelDateDisplay(stCumDtList[5]);
							dto.setLastDueDateDisplay(stCumDtList[6]);
							dto.setStatusDisplay(stCumDtList[3]);
							dto.setStatusDateDisplay(stCumDtList[7]);
							dto.setIsApproved(1);
							eForm.setActionName("");
							eForm.setFormName("");
							request.setAttribute("dod", eForm);
							forwardPage = "drViewApproval";

						}// --end of approved for destruction----

						// -----if status is "Notice issued"
						if (docStatusId.equalsIgnoreCase("3")) {
							dto.setRegNoDisplay(regComNo);
							dto.setRegComDateDisplay(regComDt);
							dto.setLastDelDateDisplay(stCumDtList[5]);
							dto.setLastDueDateDisplay(stCumDtList[6]);
							dto.setStatusDisplay(stCumDtList[3]);
							dto.setStatusDateDisplay(stCumDtList[7]);
							dto.setUserNameDisplay(stCumDtList[8]);
							dto.setOfficeNameDisplay(stCumDtList[9]);
							dto.setIsIssued(1);
							forwardPage = "drView";
						}// --end of status is Notice issued

						// -----if status is "Notice to be issued"
						if (docStatusId.equalsIgnoreCase("2")) {
							dto.setRegNoDisplay(regComNo);
							dto.setRegComDateDisplay(regComDt);
							dto.setLastDelDateDisplay(stCumDtList[5]);
							dto.setLastDueDateDisplay(stCumDtList[6]);
							dto.setStatusDisplay(stCumDtList[3]);
							dto.setStatusDateDisplay(stCumDtList[7]);
							dto.setUserNameDisplay(stCumDtList[8]);
							dto.setOfficeNameDisplay(stCumDtList[9]);
							forwardPage = "drView";
						}// --end of status is Notice to be issued

						// -----if status is "Undelivered"
						if (docStatusId.equalsIgnoreCase("1")) {
							dto.setRegNoDisplay(regComNo);
							dto.setRegComDateDisplay(regComDt);
							dto.setLastDelDateDisplay(stCumDtList[5]);
							dto.setLastDueDateDisplay(stCumDtList[6]);
							dto.setStatusDisplay(stCumDtList[3]);
							dto.setStatusDateDisplay(stCumDtList[7]);
							dto.setUserNameDisplay(stCumDtList[8]);
							dto.setOfficeNameDisplay(stCumDtList[9]);
							forwardPage = "drView";
						}// --end of status is undelivered
					}// end of action 2 "hyperClickDr"
				}// end of if condition for request param

				// --action 1------
				if (actionName.equalsIgnoreCase("SearchDR")) {
					dto.setOwmChk("");
					dto.setNoRecFound(0);
					dto.setIsrepresentative(0);
					dto.setPendingApproval(0);
					dto.setIsdestroyed(0);
					dto.setNotInsertedDestroy(0);
					String regno = dto.getRegNo();

					String status = dto.getStatusSelected();
					String frmDt = dto.getFromRequestDate();
					String toDt = dto.getToRequestDate();
					String disId = "2";
					ArrayList offcList = dodBD.getChildOffcDetl(officeId);
					if (status.equalsIgnoreCase("select")) {
						status = "";
					}
					ArrayList regDetails = new ArrayList();

					regDetails = dodBD.getregDetlDr(regno, status, frmDt, toDt, offcList);
					if (regDetails.size() == 0) {
						dto.setNoRecFound(1);
					}
					if (regDetails.size() > 0) {
						dto.setRegDetails(regDetails);
						request.setAttribute("regDetails", regDetails);
					}
					forwardPage = "dodDrsearch";
				}// end of action 1---"SearchDR"

				// Code start for print status change Rupali
				if (actionName.equalsIgnoreCase("ptintstatusChange")) {
					dto.setOwmChk("");
					dto.setNoRecFound(0);
					dto.setIsrepresentative(0);
					dto.setPendingApproval(0);
					dto.setIsdestroyed(0);
					dto.setNotInsertedDestroy(0);
					String regno = dto.getRegNo();

					String status = dto.getStatusSelected();
					// String frmDt =dto.getFromRequestDate();
					// String toDt=dto.getToRequestDate();
					String disId = "2";
					ArrayList offcList = dodBD.getChildOffcDetl(officeId);
					if (status.equalsIgnoreCase("select")) {
						status = "";
					}
					ArrayList regDetails = new ArrayList();

					// regDetails= dodBD.getregDetlDr(regno,status, frmDt,
					// toDt,offcList );
					regDetails = dodBD.getregDetlDr(regno, offcList);
					if (regDetails.size() == 0) {
						dto.setNoRecFound(1);
					}
					if (regDetails.size() > 0) {
						dto.setRegDetails(regDetails);
						request.setAttribute("regDetails", regDetails);
					}
					forwardPage = "dodprcsearch";
				}

				// code end for print status change Rupali

				// start of resetDr
				if (actionName.equalsIgnoreCase("ResetDr")) {
					logger.debug("inside reset of dr");
					dto.setNoRecFound(0);
					dto.setIsrepresentative(0);
					dto.setPendingApproval(0);
					dto.setIsdestroyed(0);
					dto.setNotInsertedDestroy(0);
					dto.setPartyOrRep(0);
					dto.setIsOneMnOver(0);
					dto.setIsPartial(0);
					dto.setNotInsertedDestroyP(0);
					dto.setIsApproved(0);
					dto.setNotInsertedApprove(0);
					dto.setNotInsertedNotice(0);
					dto.setIsNoticeIssued(0);
					dto.setIsIssued(0);
					dto.setIscompletePay(0);
					dto.setIsLinkToBeShown(0);
					dto.setNotInsertedPost(0);
					dto.setIsSavedPost(0);

					dto.setRegNo("");
					dto.setStatusSelected("");
					dto.setSelecRegNo("");
					dto.setStatusSelectedName("");
					dto.setFromRequestDate("");
					dto.setToRequestDate("");
					dto.setRepFname("");
					dto.setRepMname("");
					dto.setRepLname("");
					dto.setRegCompDt("");
					dto.setDocStatusID("");
					dto.setDocStatusName("");
					dto.setRegCumStatus("");
					dto.setRegCumDetls(null);
					dto.setRegDetails(new ArrayList());
					dto.setPartyNm("");
					dto.setPartyNmDisplay("");
					dto.setTransDetails(new ArrayList());
					dto.setTransDetailsDisplay(new ArrayList());
					dto.setChckArry("");
					dto.setChckBx("");
					dto.setPayableFee("");
					dto.setAlrdyPaidFee("");
					dto.setTotalFee("");
					dto.setPrimKeyPymt("");
					dto.setFlg("");
					dto.setRegNoDisplay("");
					dto.setRegComDateDisplay("");
					dto.setLastDelDateDisplay("");
					dto.setLastDueDateDisplay("");
					dto.setStatusDisplay("");
					dto.setStatusDateDisplay("");
					dto.setUserNameDisplay("");
					dto.setOfficeNameDisplay("");
					dto.setDrNameDisplay("");
					dto.setDrOfficeDisplay("");
					dto.setDocDelToDisplay("");
					dto.setOtherNameDisplay("");
					dto.setAuthLetterNmDisplay("");
					dto.setRadioSelect("");
					dto.setFilePhoto2(null);
					dto.setDocPath("");
					dto.setNoticeRegNoDisplay("");
					dto.setNoticeRegComDateDisplay("");
					dto.setNoticeLastDelDateDisplay("");
					dto.setNoticeLastDueDateDisplay("");
					dto.setNoticeStatusDisplay("");
					dto.setNoticeStatusDateDisplay("");
					dto.setNoticeUserNameDisplay("");
					dto.setNoticeOfficeNameDisplay("");
					dto.setNoticeRemarks("");
					dto.setNoticePartyRole("");
					dto.setNoticePartyAdrs("");
					dto.setNoticePartyNm("");
					dto.setNoticecumdtls("");
					dto.setNoticeDetails(new ArrayList());
					dto.setPostlAddrs("");
					dto.setPostlDist("");
					dto.setPostlState("");
					dto.setPostlCntry("");
					dto.setPostlDocketNo("");

					request.removeAttribute("regDetails");
					eForm.setActionName("");
					eForm.setFormName("");
					formName = "";
					actionName = "";
					forwardPage = "dodDrsearch";

				}// end of resetDr

			}// end of form HomePageDR

			// start of DRApproval
			if (formName != null && formName.equalsIgnoreCase("DodDrViewApproval")) {

				if (actionName.equalsIgnoreCase("approve")) {
					boolean boo = false;
					dto.setNotInsertedDestroy(0);
					dto.setNotInsertedApprove(0);
					dto.setIsApproved(0);
					String regcompNum = dto.getRegNoDisplay();
					userId = (String) session.getAttribute("UserId");
					// String offid="OFC03";
					officeId = (String) session.getAttribute("loggedToOffice");
					System.out.println("coming second tym--->>>>>" + regcompNum);
					boo = dodBD.statusApproveUpdate(regcompNum, userId, officeId);
					if (boo) {

						ArrayList approveDetls = new ArrayList();
						ArrayList comp7 = new ArrayList();
						approveDetls = dodBD.getApproveDetls(regcompNum);
						if (approveDetls.size() > 0) {
							for (int i = 0; i < approveDetls.size(); i++) {
								comp7.add((ArrayList) approveDetls.get(i));
								if (comp7 != null && comp7.size() > 0) {
									for (int k = 0; k < comp7.size(); k++) {
										ArrayList compSub = (ArrayList) comp7.get(k);
										dto.setRegNoDisplay((String) compSub.get(0));
										dto.setRegComDateDisplay((String) compSub.get(1));
										dto.setLastDelDateDisplay((String) compSub.get(3));
										dto.setLastDueDateDisplay((String) compSub.get(4));
										dto.setStatusDisplay((String) compSub.get(2));
										dto.setStatusDateDisplay((String) compSub.get(5));
										// dto.setUserNameDisplay((String)
										// compSub.get(6));
										// dto.setOfficeNameDisplay((String)
										// compSub.get(7));
										dto.setDrNameDisplay((String) compSub.get(6));
										dto.setDrOfficeDisplay((String) compSub.get(7));
										dto.setIsApproved(1);
										forwardPage = "drViewApproval";
									}
								}
							}
						}
						// dto.setIsdestroyed(1);
					} else {
						dto.setNotInsertedApprove(1);
						forwardPage = "drViewApproval";
					}
				}// end of action approve

			}// end of DR Approval

		}// end of (form!=null)

		return mapping.findForward(forwardPage);

	}// end of execute method

	private String getFileExtension(String fileName) {
		try {
			int pos = fileName.lastIndexOf(".");
			String strExt = fileName.substring(pos + 1, fileName.length());
			return strExt;
		} catch (Exception e) {
		}
		return "";
	}

	private boolean uploadFile(FormFile filetobeuploaded, String fileName, String filePath) {

		try {
			File folder = new File(filePath);
			if (!folder.exists()) {
				folder.mkdirs();
			}

			File newFile = new File(filePath, fileName);
			// logger.info("NEW FILE NAME:-" + newFile);
			FileOutputStream fos = new FileOutputStream(newFile);
			fos.write(filetobeuploaded.getFileData());
			fos.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

	// Added by Neeti for RCMS

	private boolean sendingXMLDataToRCMS(String regNum, String regTxnID) {

		ArrayList propIdList = new ArrayList();
		RevenueCaseBD revenueBD = new RevenueCaseBD();
		boolean propFlag = false;
		try {

			// Check for property with beresia tehsil level in SQL query
			// ****Removed

			propIdList = new ArrayList();
			ArrayList propIDs = revenueBD.getPropIdsForRCMS(regTxnID);
			PropertiesFileReader property = PropertiesFileReader.getInstance("resources.igrs");
			String url1 = property.getValue("URL_FINAL_DOCUMENT_1");
			String url2 = property.getValue("URL_FINAL_DOCUMENT_2");
			String url3 = property.getValue("URL_FINAL_DOCUMENT_3");
			String finalCopyUrl = url1 + regTxnID + url2;
			if (propIDs != null && !propIDs.isEmpty()) {

				// Property Null check to be added
				for (int j = 0; j < propIDs.size(); j++) {
					ArrayList list2 = new ArrayList();
					list2 = (ArrayList) propIDs.get(j);
					propIdList.add(list2.get(0));
				}

				// Tehsil Check for Pilot

				// Update reg txn table with New Request status

				boolean transFlag = revenueBD.updateRegTrnStatus(regTxnID, "1");

				logger.debug("Property ID List :- " + propIdList);

				logger.debug("Get XML data to be sent to RCMS");

				int counter = 1;
				RCMSDetailsSampada rcmsds;
				ArrayList<PropertyDetails> propds;
				PropertyDetails prds;
				KhasraRecordList krList;
				ArrayList<KhasraRecord> kr;
				BuyerDetailsSampadaList bdsl;
				SellerDetailsSampadaList sdsl;
				ArrayList<SellerDetails> sdList;
				ArrayList<BuyerDetails> bdList;
				KhasraRecord krr;

				// Get common reg details
				rcmsds = new RCMSDetailsSampada();
				propds = new ArrayList<PropertyDetails>();

				ArrayList commonfields = revenueBD.getRegistrationDetails(regTxnID);
				logger.info("commonfields: " + commonfields);
				ArrayList commonfieldsNew = new ArrayList();
				for (int j = 0; j < commonfields.size(); j++) {
					ArrayList list1 = new ArrayList();
					list1 = (ArrayList) commonfields.get(j);
					commonfieldsNew.add(list1.get(0));
					commonfieldsNew.add(list1.get(1));
					commonfieldsNew.add(list1.get(2));
					commonfieldsNew.add(list1.get(3));
					commonfieldsNew.add(list1.get(4) == null ? "NA" : url1 + list1.get(4) + url3);
					commonfieldsNew.add(list1.get(5) == null ? "NA" : url1 + list1.get(5) + url3);
					commonfieldsNew.add(list1.get(6) == null ? "NA" : url1 + list1.get(6) + url3);
					commonfieldsNew.add(list1.get(7));
				}

				// Fetch RegNo,doctypeID
				String registrationNo = (String) commonfieldsNew.get(0);
				String MutationTypeId = (String) commonfieldsNew.get(2);
				String docTypeId = (String) commonfieldsNew.get(2);

				String officeId = (String) commonfieldsNew.get(3);
				logger.info("Registration No:" + commonfieldsNew.get(0));

				// Set RCMS password
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				String password = pr.getValue("RCMS_PASSWORD");

				rcmsds.setURL_Form_6A((String) commonfieldsNew.get(5));
				rcmsds.setURL_Form_6B((String) commonfieldsNew.get(6));
				rcmsds.setDistrictId((String) commonfieldsNew.get(7));
				ArrayList<String> paymentData = revenueBD.getPaymentDetail(regTxnID);
				rcmsds.setApplicationFee((paymentData.get(1) == "" ? "0" : paymentData.get(1)));
				rcmsds.setTransactionId((paymentData.get(0) == "" ? "NA" : paymentData.get(0)));
				rcmsds.setTransactionDate((paymentData.get(2) == "" ? "" : paymentData.get(2)));
				rcmsds.setFinalDocumentCopy("NA");
				rcmsds.setURL_Form_6("NA");
				if (null != (String) commonfieldsNew.get(5)) {
					if (!"".equalsIgnoreCase((String) commonfieldsNew.get(5))) {
						rcmsds.setFinalDocumentCopy(finalCopyUrl);
						rcmsds.setURL_Form_6((String) commonfieldsNew.get(4));
					} else {
						rcmsds.setFinalDocumentCopy("NA");
						rcmsds.setURL_Form_6("NA");
					}
				} else {
					rcmsds.setFinalDocumentCopy("NA");
					rcmsds.setURL_Form_6("NA");
				}
				rcmsds.setRcmsPassword(password);

				rcmsds.setDocumentNo((registrationNo != null && !registrationNo.isEmpty()) ? registrationNo : "");
				rcmsds.setMutationTypeId((MutationTypeId != null && !MutationTypeId.isEmpty()) ? MutationTypeId : "");
				rcmsds.setDocTypeId((docTypeId != null && !docTypeId.isEmpty()) ? docTypeId : "");
				rcmsds.setOfficeId((officeId != null && !officeId.isEmpty()) ? officeId : "");
				// Fetch Document Date
				String docDate = revenueBD.getDocDate(registrationNo);
				if (docDate != null && !docDate.isEmpty()) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date parsedDate = dateFormat.parse(docDate);
					String newDate = dateFormat.format(parsedDate);
					rcmsds.setDocumentDate(newDate);
				} else {
					rcmsds.setDocumentDate("");
				}
				logger.info("Document Date: " + docDate);

				for (int i = 0; i < propIdList.size(); i++) {
					double totalKhasraArea = 0.0;
					prds = new PropertyDetails();
					krList = new KhasraRecordList();

					String propId = (String) propIdList.get(i);
					prds.setPropertyId(propId);
					String clrFlag = revenueBD.getClrFlag(propId); // for
					// fetching
					// party and
					// khasra
					// detail
					// from
					// separate
					// table in
					// case of
					// CLR and
					// Non-Clr
					// Get total sellable area
					String totalArea = revenueBD.getTotalSellableArea(propId);
					prds.setTotalSellableArea(totalArea);

					kr = new ArrayList<KhasraRecord>();

					// Get khasra IDs wrt Property ID
					ArrayList khasraIdLists = revenueBD.getAllKhasraIds(propId, clrFlag);
					ArrayList khasraIdList = new ArrayList();

					// Fetch Buyer and Seller Details
					// Party Details will be same for all khasras

					ArrayList buyerSellerPartyList = revenueBD.getBuyerSellerPartyList(regTxnID, propId, clrFlag);
					ArrayList sellerPartyList = (ArrayList) buyerSellerPartyList.get(1);
					logger.info("sellerPartyList:" + sellerPartyList);

					ArrayList buyerPartyList = (ArrayList) buyerSellerPartyList.get(0);
					logger.info("buyerList:" + buyerPartyList);

					// Get district, tehsil and other area related details

					ArrayList areaList = revenueBD.getAllAreaDetails(propId);
					logger.info("areaDetails: " + areaList);

					String landArea = revenueBD.getLandAreaForProp(propId);

					for (int m = 0; m < khasraIdLists.size(); m++) {
						khasraIdList = (ArrayList) khasraIdLists.get(m);

						krr = new KhasraRecord();
						bdsl = new BuyerDetailsSampadaList();
						sdsl = new SellerDetailsSampadaList();
						sdList = new ArrayList<SellerDetails>();
						bdList = new ArrayList<BuyerDetails>();

						String khasraNo = (String) khasraIdList.get(1);
						String khasraArea = (String) khasraIdList.get(2);

						// Store area details to xml object
						for (int j = 0; j < areaList.size(); j++) {
							ArrayList list1 = new ArrayList();
							list1 = (ArrayList) areaList.get(j);
							if (((String) list1.get(1)) != null && !((String) list1.get(1)).isEmpty()) {
								krr.setDistrict((String) list1.get(1));
							} else {
								krr.setDistrict("");
							}

							if (((String) list1.get(2)) != null && !((String) list1.get(2)).isEmpty()) {
								krr.setTehsil((String) list1.get(2));
							} else {
								krr.setTehsil("");
							}

							if (((String) list1.get(3)) != null && !((String) list1.get(3)).isEmpty()) {
								krr.setRICircle((String) list1.get(3));
							} else {
								krr.setRICircle("");
							}

							if (((String) list1.get(4)) != null && !((String) list1.get(4)).isEmpty()) {
								krr.setPatwariHalka((String) list1.get(4));
							} else {
								krr.setPatwariHalka("");
							}

							if (((String) list1.get(5)) != null && !((String) list1.get(5)).isEmpty()) {
								krr.setVillage((String) list1.get(5));
							} else {
								krr.setVillage("");
							}

							if (((String) list1.get(6)) != null && !((String) list1.get(6)).isEmpty()) {
								krr.setAreaType((String) list1.get(6));
							} else {
								krr.setAreaType("");
							}

							// Khasra Sellable Area
							if (khasraArea != null && !khasraArea.isEmpty()) {
								krr.setKhasraSellableArea(khasraArea);
								totalKhasraArea = totalKhasraArea + Double.parseDouble(khasraArea);
							} else {
								krr.setKhasraSellableArea("");
							}
							if (clrFlag.equalsIgnoreCase("Y")) {
								krr.setKhasraUniqueId((String) khasraIdList.get(6));
								krr.setTransactionType((String) khasraIdList.get(8));
								krr.setUlpin((String) khasraIdList.get(7));
								krr.setLgdcode((String) khasraIdList.get(9));
								krr.setKhasraID((String) khasraIdList.get(3));
							} else {
								krr.setKhasraUniqueId("NA");
								krr.setTransactionType("NA");
								krr.setUlpin("NA");
								krr.setLgdcode("AN");
								krr.setKhasraID("NA");
							}

							krr.setKhasraNumber(khasraNo);

						}

						// Seller Details iterating loop
						if (sellerPartyList.size() != 0) {
							for (int j = 0; j < sellerPartyList.size(); j++) {
								ArrayList sellerList = revenueBD.getSellerPartyList((String) sellerPartyList.get(j));
								for (int k = 0; k < sellerList.size(); k++) {
									SellerDetails sd = new SellerDetails();
									ArrayList list1 = new ArrayList();
									list1 = (ArrayList) sellerList.get(k);

									logger.info("***************Seller Details************************");
									if (list1.get(0).equals("1")) {
										if (((String) list1.get(5)) != null && !((String) list1.get(5)).isEmpty()) {
											sd.setFirstName((String) list1.get(5));
										} else {
											sd.setFirstName("");
										}

										sd.setMiddleName("");
										sd.setLastName("");
										if (((String) list1.get(0)) != null && !((String) list1.get(0)).isEmpty()) {
											sd.setOwnershipTypeIdSeller((String) list1.get(0));
										} else {
											sd.setOwnershipTypeIdSeller("");
										}

										if (((String) list1.get(4)) != null && !((String) list1.get(4)).isEmpty()) {
											sd.setDeptCode((String) list1.get(4));
										} else {
											sd.setDeptCode("");
										}
										if (((String) list1.get(8)) != null && !((String) list1.get(8)).isEmpty()) {
											sd.setFatherName((String) list1.get(8));
										} else {
											sd.setFatherName("");
										}
										sdList.add(sd);

									}
									if (list1.get(0).equals("2")) {
										if (((String) list1.get(1)) != null && !((String) list1.get(1)).isEmpty()) {
											sd.setFirstName((String) list1.get(1));
										} else {
											sd.setFirstName("");
										}

										if (((String) list1.get(2)) != null && !((String) list1.get(2)).isEmpty()) {
											sd.setMiddleName((String) list1.get(2));
										} else {
											sd.setMiddleName("");
										}
										if (((String) list1.get(3)) != null && !((String) list1.get(3)).isEmpty()) {
											sd.setLastName((String) list1.get(3));
										} else {
											sd.setLastName("");
										}

										if (((String) list1.get(0)) != null && !((String) list1.get(0)).isEmpty()) {
											sd.setOwnershipTypeIdSeller((String) list1.get(0));
										} else {
											sd.setOwnershipTypeIdSeller("");
										}

										if (((String) list1.get(4)) != null && !((String) list1.get(4)).isEmpty()) {
											sd.setDeptCode((String) list1.get(4));
										} else {
											sd.setDeptCode("");
										}

										if (((String) list1.get(8)) != null && !((String) list1.get(8)).isEmpty()) {
											sd.setFatherName((String) list1.get(8));
										} else {
											sd.setFatherName("");
										}
										sdList.add(sd);
									}
									if (list1.get(0).equals("3")) {

										if (((String) list1.get(6)) != null && !((String) list1.get(6)).isEmpty()) {
											sd.setFirstName((String) list1.get(6));
										} else {
											sd.setFirstName("");
										}
										sd.setMiddleName("");
										sd.setLastName("");

										if (((String) list1.get(0)) != null && !((String) list1.get(0)).isEmpty()) {
											sd.setOwnershipTypeIdSeller((String) list1.get(0));
										} else {
											sd.setOwnershipTypeIdSeller("");
										}

										if (((String) list1.get(4)) != null && !((String) list1.get(4)).isEmpty()) {
											sd.setDeptCode((String) list1.get(4));
										} else {
											sd.setDeptCode("");
										}

										sd.setFatherName("fdsf");
										sdList.add(sd);
										if (((String) list1.get(8)) != null && !((String) list1.get(8)).isEmpty()) {
											sd.setFatherName((String) list1.get(8));
										} else {
											sd.setFatherName("");
										}
									}

								}

							}
						} else {
							SellerDetails sd = new SellerDetails();
							sd.setFirstName("");
							sd.setMiddleName("");
							sd.setLastName("");
							sd.setOwnershipTypeIdSeller("");
							sd.setDeptCode("");

							sd.setFatherName("");
							sdList.add(sd);
						}

						// Buyer Details iterating loop
						if (buyerPartyList.size() != 0) {
							for (int j = 0; j < buyerPartyList.size(); j++) {
								ArrayList buyerList = revenueBD.getBuyerPartyList((String) buyerPartyList.get(j));
								logger.info("***************Buyer Details************************");
								for (int k = 0; k < buyerList.size(); k++) {

									String partyTxnId = (String) buyerPartyList.get(j);
									logger.info("partyTxnId" + partyTxnId);
									String share = revenueBD.getPartyShare(partyTxnId, propId, clrFlag, khasraNo);

									ArrayList list1 = new ArrayList();
									list1 = (ArrayList) buyerList.get(k);

									// Buyer Details
									BuyerDetails bd = new BuyerDetails();
									if (share != null && !share.isEmpty()) {
										bd.setOwnerShare(share.trim());
									} else {
										bd.setOwnerShare("");
									}
									bd.setOwnerId("");
									bd.setCity("");
									if (((String) list1.get(7)) != null && !((String) list1.get(7)).isEmpty()) {
										bd.setCountry((String) list1.get(7));
									} else {
										bd.setCountry("");
									}
									if (((String) list1.get(8)) != null && !((String) list1.get(8)).isEmpty()) {
										bd.setStateId((String) list1.get(8));
									} else {
										bd.setStateId("");
									}
									if (((String) list1.get(9)) != null && !((String) list1.get(9)).isEmpty()) {
										bd.setDistrictId((String) list1.get(9));
									} else {
										bd.setDistrictId("");
									}
									if (((String) list1.get(11)) != null && !((String) list1.get(11)).isEmpty()) {
										bd.setPhoneNumber((String) list1.get(11));
									} else {
										bd.setPhoneNumber("");
									}
									if (((String) list1.get(12)) != null && !((String) list1.get(12)).isEmpty()) {
										bd.setMobileNumber((String) list1.get(12));
									} else {
										bd.setMobileNumber("");
									}
									if (((String) list1.get(13)) != null && !((String) list1.get(13)).isEmpty()) {
										bd.setEmailId((String) list1.get(13));
									} else {
										bd.setEmailId("");
									}

									if (((String) list1.get(17)) != null && !((String) list1.get(17)).isEmpty()) {
										bd.setIdNumber((String) list1.get(17));
										bd.setAadharNumber((String) list1.get(17));
									} else {
										bd.setIdNumber("");
										bd.setAadharNumber("");
									}
									if (((String) list1.get(18)) != null && !((String) list1.get(18)).isEmpty()) {
										bd.setNameAadharCard((String) list1.get(18));
									} else {
										bd.setNameAadharCard("");
									}

									if (((String) list1.get(20)) != null && !((String) list1.get(20)).isEmpty()) {
										String CasteName = revenueBD.getBuyerCasteName((String) list1.get(20));
										bd.setCaste(CasteName);
									} else {
										bd.setCaste("");
									}

									String IdTypeName = "";
									if (((String) list1.get(22)) != null && !((String) list1.get(22)).isEmpty()) {
										IdTypeName = revenueBD.getBuyerIdTypeName((String) list1.get(22));
										bd.setIdType(IdTypeName);
									}

									else {
										bd.setIdType("");
									}

									// Buyer Details
									if (list1.get(0).equals("1")) {

										// For Organization
										if (((String) list1.get(5)) != null && !((String) list1.get(5)).isEmpty()) {
											bd.setFirstName((String) list1.get(5));
										} else {
											bd.setFirstName("");
										}

										bd.setFatherName("");
										bd.setMiddleName("");
										bd.setLastName("");
										bd.setGender("");
										bd.setRelationType("");

										if (((String) list1.get(0)) != null && !((String) list1.get(0)).isEmpty()) {
											bd.setOwnershipTypeIdBuyer((String) list1.get(0));
										} else {
											bd.setOwnershipTypeIdBuyer("");
										}

										if (((String) list1.get(4)) != null && !((String) list1.get(4)).isEmpty()) {
											bd.setDeptCode((String) list1.get(4));
										} else {
											bd.setDeptCode("");
										}

										if (((String) list1.get(10)) != null && !((String) list1.get(10)).isEmpty()) {
											bd.setAddress((String) list1.get(10));
										} else {
											bd.setAddress("");
										}

									}
									if (list1.get(0).equals("2")) {

										if (((String) list1.get(1)) != null && !((String) list1.get(1)).isEmpty()) {
											bd.setFirstName((String) list1.get(1));
										} else {
											bd.setFirstName("");
										}

										if (((String) list1.get(2)) != null && !((String) list1.get(2)).isEmpty()) {
											bd.setMiddleName((String) list1.get(2));
										} else {
											bd.setMiddleName("");
										}
										if (((String) list1.get(3)) != null && !((String) list1.get(3)).isEmpty()) {
											bd.setLastName((String) list1.get(3));
										} else {
											bd.setLastName("");
										}
										if (((String) list1.get(21)) != null && !((String) list1.get(21)).isEmpty()) {
											bd.setFatherName((String) list1.get(21));
										} else {
											bd.setFatherName("");
										}
										if (((String) list1.get(14)) != null && !((String) list1.get(14)).isEmpty()) {
											bd.setGender((String) list1.get(14));
										} else {
											bd.setGender("");
										}
										if (((String) list1.get(15)) != null && !((String) list1.get(15)).isEmpty()) {
											String reletionName = revenueBD.getRelationName((String) list1.get(15));
											bd.setRelationType(reletionName);
										} else {
											bd.setRelationType("");
										}

										if (((String) list1.get(0)) != null && !((String) list1.get(0)).isEmpty()) {
											bd.setOwnershipTypeIdBuyer((String) list1.get(0));
										} else {
											bd.setOwnershipTypeIdBuyer("");
										}

										if (((String) list1.get(4)) != null && !((String) list1.get(4)).isEmpty()) {
											bd.setDeptCode((String) list1.get(4));
										} else {
											bd.setDeptCode("");
										}

										if (((String) list1.get(10)) != null && !((String) list1.get(10)).isEmpty()) {
											bd.setAddress((String) list1.get(10));
										} else {
											bd.setAddress("");
										}

									}
									if (list1.get(0).equals("3")) {
										if (((String) list1.get(6)) != null && !((String) list1.get(6)).isEmpty()) {
											bd.setFirstName((String) list1.get(6));
										} else {
											bd.setFirstName("");
										}

										bd.setMiddleName("");
										bd.setLastName("");
										bd.setFatherName("");
										bd.setRelationType("");
										if (((String) list1.get(14)) != null && !((String) list1.get(14)).isEmpty()) {
											bd.setGender((String) list1.get(14));
										} else {
											bd.setGender("");
										}

										if (((String) list1.get(0)) != null && !((String) list1.get(0)).isEmpty()) {
											bd.setOwnershipTypeIdBuyer((String) list1.get(0));
										} else {
											bd.setOwnershipTypeIdBuyer("");
										}

										if (((String) list1.get(4)) != null && !((String) list1.get(4)).isEmpty()) {
											bd.setDeptCode((String) list1.get(4));
										} else {
											bd.setDeptCode("");
										}
										if (((String) list1.get(23)) != null && !((String) list1.get(23)).isEmpty()) {
											bd.setAddress((String) list1.get(23));
										} else {
											bd.setAddress("");
										}

									}

									bdList.add(bd);
									logger.info("bdListSize:" + bdList.size());

								}

							}
							bdsl.setBuyerDetails(bdList);
							sdsl.setSellerDetails(sdList);
							krr.setBuyerDetailsSampadaList(bdsl);
							krr.setSellerDetailsSampadaList(sdsl);
							kr.add(krr);
						} else {
							// Empty buyer details

							BuyerDetails buyerDetails = new BuyerDetails();
							buyerDetails.setOwnerId("");
							buyerDetails.setOwnershipTypeIdBuyer("");
							buyerDetails.setFirstName("");
							buyerDetails.setMiddleName("");
							buyerDetails.setLastName("");
							buyerDetails.setGender("");
							buyerDetails.setFatherName("");
							buyerDetails.setRelationType("");
							buyerDetails.setCaste("");
							buyerDetails.setDistrictId("");
							buyerDetails.setOwnerShare("");
							buyerDetails.setAadharNumber("");
							buyerDetails.setNameAadharCard("");
							buyerDetails.setIdType("");
							buyerDetails.setIdNumber("");
							buyerDetails.setAddress("");
							buyerDetails.setCity("");
							buyerDetails.setStateId("");
							buyerDetails.setCountry("");
							buyerDetails.setPhoneNumber("");
							buyerDetails.setEmailId("");
							buyerDetails.setMobileNumber("");
							buyerDetails.setDeptCode("");

							bdList.add(buyerDetails);

							bdsl.setBuyerDetails(bdList);
							sdsl.setSellerDetails(sdList);
							krr.setBuyerDetailsSampadaList(bdsl);
							krr.setSellerDetailsSampadaList(sdsl);
							kr.add(krr);
						}

					}
					prds.setTotalKhasraArea(String.valueOf(totalKhasraArea));
					// prds.setTotalSellableArea("9");
					krList.setKhasraRecord(kr);
					prds.setKhasraRecordList(krList);
					propds.add(prds);

				}

				rcmsds.setProperty(propds);

				// Marshalling of object to XML
				JAXBContext jc = JAXBContext.newInstance(RCMSDetailsSampada.class);
				Marshaller ms = jc.createMarshaller();

				ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				String rootFile = pr.getValue("rcms_upload_path");
				String filePath = rootFile + RevenueCaseConstant.IGRS_RCMS_XML + "/";;
				logger.debug("Path :- " + filePath);

				File folder = new File(filePath);
				if (!folder.exists()) {
					folder.mkdirs();
				}

				filePath = filePath + RevenueCaseConstant.XML_REG_NO + registrationNo + "_" + counter + ".xml";
				// String s = "D:/RCMSRequest/SampleReQ_" + regTxnID + "_" +
				// counter + ".xml";
				ms.marshal(rcmsds, new FileOutputStream(filePath));

				StringWriter xmlStringWriter = new StringWriter();
				ms.marshal(rcmsds, xmlStringWriter);

				String xmlString = xmlStringWriter.toString().trim();
				// logger.info("xmlString:" + xmlString);
				logger.debug("Before WebService Call");

				RCMSResponse rcmsResponse = null;

				try {
					// Send request to RCMS
					logger.debug(xmlString);
					rcmsResponse = sendRCMSRequest(xmlString, registrationNo);
					// registrationNo);
				} catch (Exception e) {
					logger.debug("Exception in sendRCMSRequest Method Call");
					e.printStackTrace();
				}

				if (rcmsResponse != null) {
					logger.debug("Webservice response from RCMS...");
					if (rcmsResponse.getStatus() != null && !(rcmsResponse.getStatus().isEmpty())) {
						logger.debug("Response Status : " + rcmsResponse.getStatus());
						String status = rcmsResponse.getStatus();
						String errorDesc = rcmsResponse.getErrorType();

						// Update reg txn table with status 2 for successful
						// completion
						if (rcmsResponse.getStatus().equalsIgnoreCase("Success")) {
							boolean transFlag1 = revenueBD.updateRegTrnStatus(regTxnID, "2");
						}

						// Update reg txn table with status 2 for failed
						// transaction
						if (rcmsResponse.getStatus().equalsIgnoreCase("Failed")) {
							boolean transFlag1 = revenueBD.updateRegTrnStatus(regTxnID, "3");
						}

						boolean flag = revenueBD.updateRCMSStatus(regTxnID, propIdList, rcmsResponse, status);
					}

				}
				// return true;
			} else {
				// return false;
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return propFlag;
	}

	private RCMSResponse sendRCMSRequest(String xmlString, String registrationNo) {

		RCMSResponse response = null;
		// HttpURLConnection rc=null;
		HttpURLConnection rc = null;
		logger.debug("In sendRCMSRequest method to send data to RCMS");
		try {long startTime = System.currentTimeMillis();
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		URL url = new URL(pr.getValue("IGRS_RCMS_URL"));
		int timeout = Integer.valueOf(pr.getValue("RCMS_REQ_TIMEOUT"));
		logger.debug("Before creating connection --> URL --> "+pr.getValue("IGRS_RCMS_URL"));
		rc= (HttpURLConnection) url.openConnection();
		//logger.debug("After creating connection, response code is -->"+rc.getResponseCode());
		rc.setRequestMethod("POST");
		rc.setDoOutput(true);
		rc.setDoInput(true);
		rc.setConnectTimeout(timeout * 1000);
		rc.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		String reqStr = xmlString; // the entire payload in a single
		// String
		int len = reqStr.length();
		// rc.setRequestProperty("Content-Length", Integer.toString(len));
		rc.connect();
		OutputStreamWriter out = new OutputStreamWriter(rc.getOutputStream());
		out.write(reqStr, 0, len);
		out.flush();
		logger.debug("Request sent, reading response ");
		InputStreamReader read = new InputStreamReader(rc.getInputStream());
		StringBuilder sb = new StringBuilder();
		int ch = read.read();
			while (ch != -1) {
				sb.append((char) ch);
				ch = read.read();
			}
			String response1 = sb.toString(); // entire response ends up in
			// String
			logger.debug("Response received from RCMS --> " + response1);
			read.close();
			// rc.disconnect();
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			long elapsedTimeInSec = 0;
			if (elapsedTime < 1000) {
				elapsedTimeInSec = 0;
			} else {
				elapsedTimeInSec = elapsedTime / 1000;
			}
			logger.info("**********Total Time taken in process of " + registrationNo + " is " + elapsedTime + " MilliSeconds ************ " + elapsedTimeInSec + " Seconds");
			System.out.println(response1);

			JAXBContext jaxbContext = JAXBContext.newInstance(RCMSResponse.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(response1);
			response = (RCMSResponse) jaxbUnmarshaller.unmarshal(reader);

			logger.info(response.toString());

		} catch (Exception e) {
			try {
				e.printStackTrace();
				logger.debug("Response code if error --> " + rc.getResponseCode());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		finally {
			rc.disconnect();
		}
		return response;

	}

}
