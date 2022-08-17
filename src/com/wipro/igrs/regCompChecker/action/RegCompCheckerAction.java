/* Copyright (c) 2013-14 WIPRO INFOTECH. All Rights Reserved.
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
 */
/* 
 * FILE NAME: RegCompCheckerAction.java
 * @author Simran
 * @version 1.0 
 * INITIAL CODING: March 13, 2013
 * MODIFIED ON:    
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE ACTION FOR REGISTRATION COMPLETION 
 */
package com.wipro.igrs.regCompChecker.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.Proxy;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.sk.iscribe.sdk.Device_IscribeObserver;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.RegCompMaker.bd.RegCompMkrBD;
import com.wipro.igrs.RegCompMaker.bo.RegCompMkrBO;
import com.wipro.igrs.RegCompMaker.constants.RegCompConstant;
import com.wipro.igrs.Seals.bd.SealsBD;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.baseaction.constant.Constants;
import com.wipro.igrs.clr.services.MergedFormAndSign;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.db.OTPUtility;
import com.wipro.igrs.deedDraft.bd.DeedDraftBD;
import com.wipro.igrs.login.dto.MasterListDTO;
import com.wipro.igrs.newreginit.bd.RegCommonBD;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.dto.AadharDTO;
import com.wipro.igrs.newreginit.dto.AadharDetailDTO;
import com.wipro.igrs.newreginit.dto.AadharRespDTO;
import com.wipro.igrs.newreginit.dto.CommonDTO;
import com.wipro.igrs.newreginit.dto.MetaPojo;
import com.wipro.igrs.newreginit.dto.UsesPojo;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.poa.bo.POAAuthenticationFormBO;
import com.wipro.igrs.poa.form.POAAuthenticationForm;
import com.wipro.igrs.regCompChecker.bd.RegCompCheckerBD;
import com.wipro.igrs.regCompChecker.constant.RegCompCheckerConstant;
import com.wipro.igrs.regCompChecker.dto.RegCompCheckerDTO;
import com.wipro.igrs.regCompChecker.form.RegCompCheckerForm;
import com.wipro.igrs.util.GUIDGenerator;

/**
 * ===========================================================================
 * File : RegCompCheckerAction.java Description : Represents the Action Class
 * for Registration Completion Checker
 * 
 * @author : Simran Created Date : March 13, 2013 Updated Date Version UpdatedBy
 *         ==
 *         ====================================================================
 *         ==k===
 */
public class RegCompCheckerAction extends BaseAction {
	String forwardJsp = RegCompCheckerConstant.APPLICATION_SEARCH;
	private Logger logger = Logger.getLogger(RegCompCheckerAction.class);
	private static Proxy proxy;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wipro.igrs.baseaction.action.BaseAction#execute(org.apache.struts
	 * .action.ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		String page = request.getParameter("page");
		logger.debug("page" + page);
		ActionMessages messages = new ActionMessages();
		String roleId = (String) session.getAttribute("role");
		String funId = (String) session.getAttribute("functionId");
		String userId = (String) session.getAttribute("UserId");
		String officeId = (String) session.getAttribute("loggedToOffice");
		String language = (String) session.getAttribute("languageLocale");
		MasterListDTO mDto = (MasterListDTO) session.getAttribute("userDetls");
		String userDesignation = mDto.getDesignation();
		String userSign = mDto.getSignature();
		String getparam = request.getParameter("txnPartyIdSignature");
		System.out.println("txnPartyIdSignature ====> "+getparam);
		System.out.println("forwardName +"+request.getParameter("forwardName"));
		boolean filePresent = true;// change
		// userSign="D://signature.GIF";
		File output;
		if (userSign != null && !userSign.equalsIgnoreCase("")) {
			output = new File(userSign);
			if (output.exists()) {
				filePresent = true;
			}
			// to be commented
		}
		// logger.debug("^^^^^^^^^^^^^"+officeId);
		Date createdDate = new Date();
		SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
		String cdate = sdfrmt.format(createdDate);
		Map poaCheckList = new HashMap();
		Map deathUploadList = new HashMap();
		Map govtLetterUploadMap = new HashMap();
		Map witnessPhotoUploadMap = new HashMap();
		HashMap propertyRelatedComplianceMap = new HashMap();
		HashMap partyRelatedComplianceMap = new HashMap();
		String sourceModFlag = "C";
		String filePath = "";
		PropertiesFileReader proper;
		String FILE_UPLOAD_PATH = "";
		try {
			proper = PropertiesFileReader.getInstance("resources.igrs");
			FILE_UPLOAD_PATH = proper.getValue("igrs_upload_path") + File.separator + "03" + File.separator;
		} catch (Exception e1) {
			logger.debug(e1.getStackTrace());
		}
		if (form != null) {
			RegCompCheckerForm eForm = (RegCompCheckerForm) form;
			eForm.setAllowPrint("N");
			eForm.setPrintFlag("N");
			// eForm.getRegDTO().setParentFuncID("");
			String headerimg = getServlet().getServletContext().getRealPath("") + "/images/header img.jpg";
			eForm.setHeaderImg(headerimg);
			RegCommonForm regForm = new RegCommonForm();
			eForm.setUserId(userId);
			RegCompCheckerBD bd = new RegCompCheckerBD();
			RegCommonBO commonBo = new RegCommonBO();
			RegCompCheckerDTO formDTO = eForm.getRegDTO();
			eForm.setDisableBook("FALSE");
			formDTO.setUserError("N");
			String actionName = null;
			String fwdName = null;
			String index = null;
			String regSealCheck1 = bd.checkRegSealApllied(formDTO.getRegInitNumber());
			String ecxecutionCheck = bd.checkExecutionSealApllied(formDTO.getRegInitNumber());
			String deedIdCheck = bd.getDeedId(formDTO.getRegInitNumber());
			if ("1091".equalsIgnoreCase(deedIdCheck)) {
				eForm.setCorrectionSeal("Y");
			} else {
				eForm.setCorrectionSeal("N");
			}
			eForm.setRegSealCheck(regSealCheck1);
			eForm.setExeSealCheck(ecxecutionCheck);
			ArrayList errorList = new ArrayList();
			bd.getLoggedInDistrictDetls(eForm, officeId, language);
			// logger.debug("<*******Distt"+formDTO.getDistId());
			// logger.debug("<*******Distt"+formDTO.getDistrictName());
			// logger.debug("<*******SRO"+formDTO.getSroId());
			// logger.debug("<*******SRO"+formDTO.getSroName());
			if (page != null) {
				if ("regComplete".equalsIgnoreCase(page)) {
					formDTO.setActionName("");
					formDTO.setRegInitNumber("");
					formDTO.setNewPartySignPath("");
					formDTO.setNewSignPartyTxnId("");
					eForm.getRegDTO().setParentFuncID("");
					formDTO.setSealId("");
					eForm.setMapPropertyTransParty(new HashMap());
					eForm.setMapPropertyTransPartyDisp(new HashMap());
					eForm.setMapTransactingPartiesDisp(new HashMap());
					eForm.setLinkHistoryList(new ArrayList());
					eForm.setMapLinkingDutiesDisp(new HashMap());
					eForm.setPartiesPresentList(new ArrayList());
					eForm.setWitnessDetailsList(new ArrayList());
					eForm.setPartiesDetailList(new ArrayList());
					eForm.setCheck("");
					eForm.setCheck2("");
					eForm.setRegDTO(new RegCompCheckerDTO());
					eForm.setCheckListDetailsList(new ArrayList());
					eForm.setCaveatDetailsList(new ArrayList());
					eForm.setPropList(new ArrayList());
					eForm.setUploadDthList(new HashMap());
					eForm.setUploadPOAList(new HashMap());
					request.removeAttribute("checkRegID");
					formDTO.setIsMplr("");
					eForm.setHoldChk("N");
					eForm.setOwmChk("");
					eForm.setDeathCertChk("");
					eForm.setPoaChk("");
					System.out.println("Current Directory=====" + System.getProperty("user.dir"));
					request.removeAttribute(RegCompCheckerConstant.ISADJ);
					forwardJsp = RegCompCheckerConstant.APPLICATION_SEARCH;
				}
			}
			String formName = eForm.getRegDTO().getRegCompCheckerForm();
			if (request.getParameter("actionName") != null) {
				actionName = request.getParameter("actionName");
			} else {
				actionName = eForm.getRegDTO().getActionName();
			}
			if (request.getParameter("fwdName") != null) {
				fwdName = request.getParameter("fwdName");
			} else {
				if (formDTO.getUploadAction() != null && !formDTO.getUploadAction().equalsIgnoreCase("")) {
					fwdName = formDTO.getUploadAction();
				} else {
					fwdName = formDTO.getFwdName();
				}
			}
			if (request.getParameter("index") != null) {
				index = request.getParameter("index");
			}
			logger.debug("ACTION NAME IN REG COMPLETION CHECKER--> " + actionName);
			logger.debug("FORM NAME IN REG COMPLETION CHECKER--> " + formName);
			logger.debug("FwD NAME IN REG COMPLETION CHECKER--> " + fwdName);
			logger.debug("OFFC ID" + officeId);
			Date todaysDate = new Date();
			SimpleDateFormat sdfrmt1 = new SimpleDateFormat("dd/MM/yyyy");
			String tDate = sdfrmt1.format(createdDate);
			// bd.seqChk(tDate);
			if (request.getParameter("paymentStatus") != null && request.getAttribute("eCode") == null) {
				logger.debug(" IN CHECKER ACTION---->BACK FROM PAYMENT MODULE");
				bd.insertTimeMakerStart(eForm, userId, officeId);
				String pkey = (String) request.getParameter("parentKey");
				// String table = (String)request.getParameter("parentTable");
				// logger.debug("TABLE NAME---->"+table);
				String pymntType = formDTO.getPaymentType();
				// logger.debug("<--------"+pymntType);
				String sourceMod = formDTO.getSourceMod();
				if (request.getParameter("paymentStatus").equalsIgnoreCase("P")) {
					Double paidAmtAtChecker = bd.getPaidAmtChecker(formDTO.getRegInitNumber(), sourceModFlag, pymntType);
					if (pymntType.equalsIgnoreCase("1")) {
						Double totalBal = Double.parseDouble(formDTO.getNetStampDuty());
						// logger.debug("<-----"+totalBal);
						totalBal = totalBal - paidAmtAtChecker;
						// logger.debug("<----->after calculationtotal bal"+totalBal);
						formDTO.setNetPaidAmt(paidAmtAtChecker.toString());
						formDTO.setNetBalAmt(totalBal.toString());
						if (totalBal > 0) {
							formDTO.setNetPaidAmt(paidAmtAtChecker.toString());
							formDTO.setNetBalAmt(BigDecimal.valueOf(totalBal).toPlainString());
							formDTO.setStampDutyPmt("Y");
							formDTO.setParentFuncID("FUN_023");
							forwardJsp = RegCompCheckerConstant.PROCEED_TO_PAYMENT;
						} else {
							boolean flg = bd.updtStatus(pkey, userId, cdate, sourceMod, pymntType);
							logger.debug("******payment status update****" + flg);
							String purposeOfEstamp = bd.getEstampPurposeDetails(formDTO.getRegInitNumber());
							request.setAttribute("eStampRegId", formDTO.getRegInitNumber());
							request.setAttribute("eStampRegAmnt", formDTO.getNetStampDuty());
							request.setAttribute("eStampRegDistId", commonBo.getDistIdEstamp(formDTO.getRegInitNumber()));
							request.setAttribute("eStampRegFuncId", "023");
							request.setAttribute("eStampRegPurpose", purposeOfEstamp);
							request.setAttribute("sourceModFlag", "C");
							forwardJsp = "reginitEstamp";
							return mapping.findForward(forwardJsp);
						}
					} else {
						Double totalBal = Double.parseDouble(formDTO.getNetRegFee());
						// logger.debug("<-----"+totalBal);
						totalBal = totalBal - paidAmtAtChecker;
						// logger.debug("<----->after calculationtotal bal"+totalBal);
						if (totalBal > 0) {
							formDTO.setNetPaidAmt(paidAmtAtChecker.toString());
							formDTO.setNetBalAmt(BigDecimal.valueOf(totalBal).toPlainString());
							formDTO.setRegFeePmt("Y");
							formDTO.setParentFuncID("FUN_206");
							forwardJsp = RegCompCheckerConstant.PROCEED_TO_PAYMENT;
						} else {
							String deedId = bd.getDeedId(formDTO.getRegInitNumber());
							if (deedId.equals("1053")) // ****ConveyanceDeed
							{
								formDTO.setConvynceDeedCheck("Y");
							}
							boolean flg = bd.updtStatus(pkey, userId, cdate, sourceMod, pymntType);
							logger.debug("******payment status update****" + flg);
							// String
							// instId=bd.getInstId(formDTO.getRegInitNumber());
							String bookId = bd.getbookId(formDTO.getRegInitNumber());
							if (bookId != null && !bookId.equalsIgnoreCase("")) {
								String bookName = bd.getbookName(bookId);
								if (bookName != null && !bookName.equalsIgnoreCase("")) {
									formDTO.setBookId(bookId);
									formDTO.setBookName(bookName);
									eForm.setDisableBook("TRUE");
								} else {
									formDTO.setBookId("");
									formDTO.setBookName("");
								}
							} else {
								formDTO.setBookId("");
								formDTO.setBookName("");
							}
							ArrayList bookDetails = bd.getBookDetails();
							eForm.setBookDetailsList(bookDetails);
							boolean flag1 = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), "39", cdate, userId);
							boolean flag = bd.insertFinalDuties(formDTO.getRegInitNumber(), eForm, userId, tDate);
							forwardJsp = RegCompCheckerConstant.BOOK_TYPE_SELECTION;
						}
						mapping.findForward(forwardJsp);
					}
				}
			}
			// following code will execute wen flow returns from estamping
			if (request.getAttribute("eCode") != null) {
				bd.insertTimeMakerStart(eForm, userId, officeId);
				String eStampDets = request.getAttribute("eCode").toString();
				String pdfPath = request.getAttribute("eStampPdfPath").toString();
				if (eStampDets != null && !eStampDets.equalsIgnoreCase("")) {
					String[] eStampDetsArr = eStampDets.split("~");
					// insertion code
					boolean insertEstampMapDetls = false;
					insertEstampMapDetls = bd.insertEstampMappingDetls(eStampDetsArr[1].trim(), formDTO.getRegInitNumber(), userId, pdfPath);
					if (insertEstampMapDetls) {
						eForm.setRegInitEstampCode(eStampDetsArr[0].trim());
						formDTO.setStampDutyPmt("Y");
						formDTO.setIsEstamp("Y");
						// logger.debug("ESTAMP FLAG ------>"+formDTO.getIsEstamp());
						forwardJsp = RegCompCheckerConstant.PROCEED_TO_PAYMENT;
					} else {
						if ("en".equalsIgnoreCase(language)) {
							request.setAttribute(RegCompCheckerConstant.FAILURE_MSG, "Registration Completion Failed");
						} else {
							request.setAttribute(RegCompCheckerConstant.FAILURE_MSG, "पंजीकरण पूरा होने में विफल रहा है|");
						}
						forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
					}
				}
			}
			if (request.getAttribute("noECode") != null) {
				String eCode = request.getAttribute("noECode").toString();
				eForm.setRegInitEstampCode(eCode);
				formDTO.setStampDutyPmt("Y");
				formDTO.setIsEstamp("Y");
				forwardJsp = RegCompCheckerConstant.PROCEED_TO_PAYMENT;
			}
			if (request.getParameter("dms") != null) {
				if (request.getParameter("dms").equalsIgnoreCase("retrieval")) {
					if (request.getParameter("path") != null) {
						if (request.getParameter("addUpload") != null && request.getParameter("addUpload").toString().equalsIgnoreCase("4")) {
							RegCommonForm regform = eForm.getRegInitForm();
							String id = request.getParameter("uKey");
							ArrayList<CommonDTO> listDto = regform.getListDto();
							if (id != null && !id.equalsIgnoreCase("")) {
								for (int i = 0; i < listDto.size(); i++) {
									CommonDTO dto = listDto.get(i);
									if (dto.getUniqueIdUpload().equalsIgnoreCase(id)) {
										DMSUtility.downloadDocument(response, dto.getDocumentPath(), dto.getDocContents());
										break;
									}
								}
							}
							forwardJsp = "displayConsenterDetls";
						} else {
							String partyType = "";
							String filename = request.getParameter("path").toString();
							byte[] contents = null;
							contents = DMSUtility.getDocumentBytes(filename);
							downloadDocument(response, contents, filename);
							// end of code by shruti
							String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
							if (deedInstArray != null && deedInstArray.length > 0) {
								request.setAttribute("deedId", deedInstArray[0].trim());
								request.setAttribute("instId", deedInstArray[1].trim());
							}
							actionName = "";
						}
					}
				}
				if (request.getParameter("dms").equalsIgnoreCase("retrievalSign")) {
					try {
						String filename = request.getParameter("path").toString();
						response.setContentType("application/download");
						String file = filename.substring(filename.lastIndexOf("/") + 1, filename.length());
						response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(file, "UTF-8"));
						File fileToDownload = new File(filename);
						BufferedImage bi = ImageIO.read(fileToDownload);
						OutputStream out = response.getOutputStream();
						ImageIO.write(bi, "gif", out);
						out.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					actionName = "";
				}
			}
			if (request.getParameter("label") != null) {
				if (request.getParameter("label").equalsIgnoreCase("displayConsenter")) {
					regForm.setConfirmationFlag("00");
					regForm.setPartyModifyFlag(0);
					commonBo.openConsenterView(request, regForm, language);
					forwardJsp = "displayConsenterDetls";
				}
				if (request.getParameter("label").equalsIgnoreCase("displayConsenterDuties")) {
					commonBo.getConsenterDutiesView(regForm, language);
					forwardJsp = "displayConsenterDuties";
				}
				/*	if (request.getParameter("label").equalsIgnoreCase(
							"displayCLRdata")) {
						PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					   Desktop d = Desktop.getDesktop();
					String propID ="";
					if (request.getParameter("key") != null)
						propID = request.getParameter("key");
					else if (request.getAttribute("key") != null)
						propID = (String) request.getAttribute("key");
					ArrayList validateKhasraDetls = commonBo.getKhasraToValidate(propID);
					    StringBuilder commaSepValueKhasra = new StringBuilder();
					    for ( int i = 0; i< validateKhasraDetls.size(); i++){
					    	ArrayList clrList=(ArrayList)validateKhasraDetls.get(i);
					    	commaSepValueKhasra.append(clrList.get(0));
					      if ( i != validateKhasraDetls.size()-1){
					    	  commaSepValueKhasra.append(",");
					      }
					    }
					    System.out.println("khasra list   "+commaSepValueKhasra.toString());
					    String colonyId = "";
					    colonyId= 	commonBo.getCLRcensusCode(regForm.getHidnRegTxnId(),propID);
					    System.out.println("khasra list   "+colonyId);
					  //  colonyId  = "03010100060154";
					  //  commaSepValueKhasra = new StringBuilder();
					  //  commaSepValueKhasra.append("2,9,1/11");
					    String khasraUrl6=pr.getValue("khasraUrl6");	
					    String khasraUrl7=pr.getValue("khasraUrl7");		
						
					    khasraUrl6 = khasraUrl6 + colonyId;	
					    khasraUrl7 = khasraUrl7 + commaSepValueKhasra;
					    String finalKhasraURL = khasraUrl6+khasraUrl7;
						long startTime = System.currentTimeMillis();
						logger.info("*********finalKhasraURL Time Start for khasra Id: "+ finalKhasraURL+	" is: "+startTime
										+ " Milli Seconds************");
						URL url = new URL(finalKhasraURL);
						
						d.browse(new URI(finalKhasraURL));//to open url in new tab
						
						HttpURLConnection conn;
						String proxyFlag=pr.getValue("proxyFlag");
						if(proxyFlag.equalsIgnoreCase("Y")){
							try{
								String proxyIp = pr.getValue("http.proxyHost");
								String proxyPort = pr.getValue("http.proxyPort");
								if(proxyIp != null && !proxyIp.isEmpty() && proxyPort != null && !proxyPort.isEmpty())
								{							
							Proxy proxy = new Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress(proxyIp, Integer.parseInt(proxyPort)));
								}
							}
							catch (Exception e) {
								logger.debug("Error message : "+ e.getMessage(), e);
							}					
							
							conn = (HttpURLConnection) url
							.openConnection(proxy);
						}else{
							conn = (HttpURLConnection) url
							.openConnection();
						}									
						
						conn.setRequestMethod("GET");
						conn.setRequestProperty("Accept", "text/xml");
						conn.setRequestMethod("HEAD");
						int ConnectTimeout = Integer.valueOf(pr.getValue("igrs_khasra_timeout"));
						int ReadTimeout = Integer.valueOf(pr.getValue("igrs_khasra_timeout_read"));
						conn.setConnectTimeout(ConnectTimeout*1000);
						conn.setReadTimeout(ReadTimeout*1000);									
						
						logger.debug("Response Code :- "+ conn.getResponseCode());
					 
					    if (request.getParameter("fromPage") != null) { 
					    if (request.getParameter("fromPage").equalsIgnoreCase(
						"propFlow"))
					    {
					    	forwardJsp = "propertyView";
					    }
					    
					    else if(request.getParameter("fromPage").equalsIgnoreCase(
						"nonPropFlow")){
					    	
					    	forwardJsp = "propertyViewNonPV"; 	
					    }
					    }
					    
					    else{
					    	
					    	forwardJsp = "propertyView";
					    }
					    
					    
				}*/
				if (request.getParameter("label").equalsIgnoreCase("displayExtra")) {
					regForm.setHidnRegTxnId(formDTO.getRegInitNumber());
					regForm.setDeedID(formDTO.getDeedID());
					regForm.setInstID(formDTO.getInstID());
					commonBo.getExtraDetls(regForm, language);
					request.setAttribute("deedId", regForm.getDeedID());
					request.setAttribute("instId", formDTO.getInstID());
					// logger.debug("re" +
					// "quest deed ---->"+request.getAttribute("deedId"));
					regForm.setCallingAction("regInit.do");
					forwardJsp = "displayExtraDetls";
				} else if (request.getParameter("label").equalsIgnoreCase("SrSignature")) {
					boolean flag = bd.insertSrSignDetails(formDTO, userId);
					if (flag) {
						logger.debug("data inserted");
						boolean result = bd.putSrSgnOnFinalDoc(eForm, userId, officeId);
						if (result) {
							logger.debug("sr sign on final doc------>true");
							boolean finalDocGenChk = bd.updateFinalDocGenChk(formDTO.getRegInitNumber());
							if (finalDocGenChk) {
								if (eForm.getCheck().equals("REFCHK")) {
									eForm.setCheck("");
									actionName = "";
									if ("en".equalsIgnoreCase(language)) {
										request.setAttribute(RegCompCheckerConstant.FINAL_REF_MSG, "Registration has been completed for this Registration Initiation Number");
									} else {
										request.setAttribute(RegCompCheckerConstant.FINAL_REF_MSG, "इस पंजीकरण शुरूआत संख्या के लिए पंजीकरण पूरा हो चुका है");
									}
								} else {
									request.removeAttribute(RegCompCheckerConstant.FINAL_CONF_MSG);
									actionName = "";
									if ("en".equalsIgnoreCase(language)) {
										request.setAttribute(RegCompCheckerConstant.FINAL_MSG, "Registration has been completed for this Registration Initiation Number");
									} else {
										request.setAttribute(RegCompCheckerConstant.FINAL_MSG, "इस पंजीकरण शुरूआत संख्या के लिए पंजीकरण पूरा हो चुका है");
									}
								}
								eForm.setOwmChk("");
							}
						}
					}
					forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
				}
			}
			if (request.getParameter("txnPartyIdSignature") != null) {
				String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
				formDTO.setParentPathSign(path);
				formDTO.setFileNameSign("signature.GIF");
				formDTO.setForwardPath("/regCompChecker.do?txnPartyIdSignature=");
				formDTO.setForwardName("regCompChecker");
				String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
				formDTO.setFileNameFP("FingerPrint.GIF");
				formDTO.setParentPathFP(pathFP);
				// logger.debug("************partyId"+request.getParameter("txnPartyIdSignature"));
				String reqPara = request.getParameter("txnPartyIdSignature");
				String partyArr[] = reqPara.split("_");
				// String partyIdUpload =
				// request.getParameter("txnPartyIdSignature");
				String partyIdUpload = partyArr[0];
				ArrayList partyList = eForm.getPartiesPresentList();
				Iterator itr = partyList.iterator();
				while (itr.hasNext()) {
					RegCompCheckerDTO rrdto = (RegCompCheckerDTO) itr.next();
					String partyId = rrdto.getPartyTxnId();
					if (partyIdUpload.equalsIgnoreCase(partyId)) {
						logger.debug("************MATCH");
						rrdto.setSignatureName(formDTO.getFileNameSign());
						rrdto.setSignaturePath(formDTO.getParentPathSign() + partyId + "/" + formDTO.getFileNameSign());
						rrdto.setPartySignChk("Y");
						boolean flag = bd.imageCheck(rrdto.getSignaturePath());
						if (flag) {
							flag = bd.updateSignDetails(partyId, rrdto.getSignatureName(), rrdto.getSignaturePath());
						}
						if (flag) {} else {
							rrdto.setSignatureName("");
							rrdto.setSignaturePath("");
							rrdto.setPartySignChk("N");
							ArrayList error = new ArrayList();
							error.add("Unable to upload file. Please try again.");
							request.setAttribute("ERROR_LIST", error);
							request.setAttribute("ERROR_FLAG", "true");
						}
					}
				}
				/*
				 * ArrayList partyPresentList = eForm.getPartiesPresentList();
				 * //String selectedPartyArr[] =
				 * formDTO.getSelectedPartyIds().split("_"); Iterator itr1 =
				 * partyPresentList.iterator(); //ArrayList partyDetails = new
				 * ArrayList(); while(itr1.hasNext()) {
				 * logger.debug("inside while"); RegCompCheckerDTO rDTO =
				 * (RegCompCheckerDTO)itr1.next(); String party =
				 * rDTO.getPartyTxnId(); // logger.debug("party"+party);
				 * logger.debug("selectedpart arr"+partyArr.length); for(int i =
				 * 1; i<partyArr.length;i++) { String tmpArr[] =
				 * partyArr[i].split(",");
				 * logger.debug("size of temparr"+tmpArr.length);
				 * if(party.equals(tmpArr[0])) { logger.debug("match");
				 * rDTO.setStatus("Y"); rDTO.setIsGovtOfficial(tmpArr[1]);
				 * rDTO.setGovtOfficial(tmpArr[2]); if(tmpArr.length ==3)
				 * rDTO.setReprsnName(""); else rDTO.setReprsnName(tmpArr[3]);
				 * if(tmpArr.length ==4 || tmpArr.length ==3)
				 * rDTO.setThumbRemarks(""); else
				 * rDTO.setThumbRemarks(tmpArr[4]);
				 * 
				 * 
				 * break; } else { rDTO.setStatus("N");
				 * rDTO.setGovtOfficial(" "); rDTO.setReprsnName(" "); } }
				 * 
				 * }
				 * 
				 * eForm.setPartiesPresentList(partyPresentList);
				 * formDTO.setIsUpload("Y");
				 */
				actionName = "";
				forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
			}
			if (request.getParameter("txnPartyIdBiometric") != null) {
				String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
				formDTO.setParentPathSign(path);
				formDTO.setFileNameSign("signature.GIF");
				formDTO.setForwardPath("/regCompChecker.do?txnPartyIdBiometric=");
				formDTO.setForwardName("regCompChecker");
				String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
				formDTO.setFileNameFP("FingerPrint.GIF");
				formDTO.setParentPathFP(pathFP);
				// logger.debug("************partyId"+request.getParameter("txnPartyIdBiometric"));
				String partyIdUpload = request.getParameter("txnPartyIdBiometric");
				ArrayList partyList = eForm.getPartiesDetailList();
				boolean flag = false;
				Iterator itr = partyList.iterator();
				while (itr.hasNext()) {
					RegCompCheckerDTO rrdto = (RegCompCheckerDTO) itr.next();
					String partyId = rrdto.getPartyTxnId();
					if (partyIdUpload.equalsIgnoreCase(partyId)) {
						logger.debug("************MATCH");
						rrdto.setSignaturePath(formDTO.getParentPathSign() + partyId + "/" + formDTO.getFileNameSign());
						rrdto.setSignatureName(formDTO.getFileNameSign());
						rrdto.setPartySignChk("Y");
						boolean flag1 = bd.imageCheck(rrdto.getSignaturePath());
						if (flag1) {
							flag1 = bd.updateSignDetails(partyId, rrdto.getSignatureName(), rrdto.getSignaturePath());
						}
						if (flag1) {} else {
							rrdto.setSignaturePath("");
							rrdto.setSignatureName("");
							rrdto.setPartySignChk("N");
							ArrayList error = new ArrayList();
							error.add("Unable to upload file. Please try again.");
							request.setAttribute("ERROR_LIST", error);
							request.setAttribute("ERROR_FLAG", "true");
						}
						flag = true;
					}
				}
				if (!flag) {
					ArrayList partyList2 = eForm.getWitnessDetailsList();
					Iterator itr2 = partyList2.iterator();
					while (itr2.hasNext()) {
						RegCompCheckerDTO rrdto1 = (RegCompCheckerDTO) itr2.next();
						String partyId = rrdto1.getWitnessTxnNummber();
						if (partyIdUpload.equalsIgnoreCase(partyId)) {
							logger.debug("************MATCH");
							rrdto1.setSignaturePath(formDTO.getParentPathSign() + partyId + "/" + formDTO.getFileNameSign());
							rrdto1.setSignatureName(formDTO.getFileNameSign());
							rrdto1.setWittSignChk("Y");
							boolean flag1 = bd.imageCheck(rrdto1.getSignaturePath());
							if (flag1) {
								flag1 = bd.updateSignDetailsWitness(partyId, rrdto1.getSignatureName(), rrdto1.getSignaturePath());
							}
							if (flag1) {} else {
								rrdto1.setSignaturePath("");
								rrdto1.setSignatureName("");
								rrdto1.setWittSignChk("N");
								ArrayList error = new ArrayList();
								error.add("Unable to upload file. Please try again.");
								request.setAttribute("ERROR_LIST", error);
								request.setAttribute("ERROR_FLAG", "true");
							}
						}
					}
				}
				ArrayList partyList5 = eForm.getConsenterDetailsList();
				Iterator itr2 = partyList5.iterator();
				while (itr2.hasNext()) {
					RegCompCheckerDTO rrdto2 = (RegCompCheckerDTO) itr2.next();
					String partyId = rrdto2.getConsentorTxnNumber();
					if (partyIdUpload.equalsIgnoreCase(partyId)) {
						logger.debug("************MATCH");
						rrdto2.setSignatureName(formDTO.getFileNameSign());
						rrdto2.setSignaturePath(formDTO.getParentPathSign() + partyId + "/" + formDTO.getFileNameSign());
						rrdto2.setCnsntrSignChk("Y");
						boolean flag1 = bd.imageCheck(rrdto2.getSignaturePath());
						if (flag1) {
							flag1 = bd.updateSignDetailsConsenter(partyId, rrdto2.getSignatureName(), rrdto2.getSignaturePath());
						}
						if (flag1) {} else {
							rrdto2.setSignatureName("");
							rrdto2.setSignaturePath("");
							rrdto2.setCnsntrSignChk("N");
							ArrayList error = new ArrayList();
							error.add("Unable to upload file. Please try again.");
							request.setAttribute("ERROR_LIST", error);
							request.setAttribute("ERROR_FLAG", "true");
						}
					}
				}
				actionName = "";
				logger.debug("PARTIES_BIOMETRIC_DETAILS ......1");
				forwardJsp = RegCompCheckerConstant.PARTIES_BIOMETRIC_DETAILS;
			}
			if (RegCompCheckerConstant.REG_INIT_SEARCH.equalsIgnoreCase(formName)) {
				// to confirmation page of registration initiation
				if (RegCompCheckerConstant.REG_INIT_SEARCH_ACTION.equalsIgnoreCase(actionName)) {
					// ////reg already complete/////
					// to be removed done in intitaion
					if (formDTO.getRegInitNumber().startsWith("0")) {
						formDTO.setRegInitNumber(formDTO.getRegInitNumber().substring(1));
					}
					boolean check = bd.checkStatus(formDTO.getRegInitNumber());
					boolean checkEtokenFlag = bd.checkEtokenFlag(officeId);
					String checkEtokenUserId = "";
					if (checkEtokenFlag) {
						checkEtokenUserId = bd.checkEtokenUserId(formDTO.getRegInitNumber(), userId);
					}
					if ((checkEtokenUserId == null || checkEtokenUserId == "") && checkEtokenFlag) {
						if ("en".equalsIgnoreCase(language)) {
							request.setAttribute("MSG", "For the logged in office Application number can be processed through EToken");
						} else {
							request.setAttribute("MSG", "लॉग इन ऑफिस के लिए एप्लीकेशन नंबर ईटोकेन के जरिए प्रोसेस किया जा सकता है");
						}
						eForm.setCheck("Y");
						request.setAttribute("checkRegID", eForm);
						forwardJsp = RegCompCheckerConstant.APPLICATION_SEARCH;
						return mapping.findForward(forwardJsp);
					}
					if (!checkEtokenUserId.equalsIgnoreCase(userId) && checkEtokenFlag) {
						if ("en".equalsIgnoreCase(language)) {
							request.setAttribute("MSG", "Different counter has been assigned to this initiation number.");
						} else {
							request.setAttribute("MSG", "इस  पंजीकरण संख्या को सौंपा गया  काउंटर अलग है |");
						}
						eForm.setCheck("Y");
						request.setAttribute("checkRegID", eForm);
						forwardJsp = RegCompCheckerConstant.APPLICATION_SEARCH;
						return mapping.findForward(forwardJsp);
					}
					if (check) {
						logger.debug("ALREADY REGISTERED");
						String regCompNumber = bd.getCompletionNumber(formDTO.getRegInitNumber());
						formDTO.setRegCompleteId(regCompNumber);
						String deedId = bd.getDeedId(formDTO.getRegInitNumber());
						String instId = bd.getInstId(formDTO.getRegInitNumber());
						formDTO.setDeedId(deedId);
						formDTO.setInstId(instId);
						// In which pin is required
						/*
						 * if((deedId.equals(RegCompCheckerConstant.CERTIFICATION_OF_SALE
						 * )&&
						 * instId.equals(RegCompCheckerConstant.SALE_DEED_INST_1
						 * ))||
						 * (deedId.equals(RegCompCheckerConstant.CONVEYANCE_DEED
						 * ) &&(instId.equals(RegCompCheckerConstant.
						 * CONVEYANCE_DEED_INST_1) ||
						 * instId.equals(RegCompCheckerConstant
						 * .CONVEYANCE_DEED_INST_2) ||
						 * instId.equals(RegCompCheckerConstant
						 * .CONVEYANCE_DEED_INST_3) ||
						 * instId.equals(RegCompCheckerConstant
						 * .CONVEYANCE_DEED_INST_4) ||
						 * instId.equals(RegCompCheckerConstant
						 * .CONVEYANCE_DEED_INST_5))) ||
						 * (deedId.equals(RegCompCheckerConstant.EXCHANGE_DEED)
						 * &&
						 * instId.equals(RegCompCheckerConstant.EXCAHNGE_DEED_INST_1
						 * )) ||
						 * (deedId.equals(RegCompCheckerConstant.GIFT_DEED) &&
						 * instId
						 * .equals(RegCompCheckerConstant.GIFT_DEED_INST_1)) ||
						 * (deedId.equals(RegCompCheckerConstant.LEASE_DEED) &&
						 * (
						 * instId.equals(RegCompCheckerConstant.LEASE_DEED_INST_1
						 * ) )) ||
						 * (deedId.equals(RegCompCheckerConstant.RELEASE_DEED)
						 * &&
						 * instId.equals(RegCompCheckerConstant.RELAESE_DEED_INST_1
						 * )) ||
						 * (deedId.equals(RegCompCheckerConstant.PARTITION_DEED)
						 * &&(instId.equals(RegCompCheckerConstant.
						 * PARTITION_DEED_INST_1) ||
						 * instId.equals(RegCompCheckerConstant
						 * .PARTITION_DEED_INST_2) ||
						 * instId.equals(RegCompCheckerConstant
						 * .PARTITION_DEED_INST_3)))) {
						 * formDTO.setPinPrintChk("Y"); }
						 */
						if (bd.checkFinalDocGen(formDTO.getRegInitNumber())) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute(RegCompCheckerConstant.FINAL_MSG, "Registration has been completed for this Registration Initiation Number");
							} else {
								request.setAttribute(RegCompCheckerConstant.FINAL_MSG, "इस पंजीकरण शुरूआत संख्या के लिए पंजीकरण पूरा हो चुका है|");
							}
						} else {
							String pathSr = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_SR_SIGN;
							formDTO.setParentPathSrSign(pathSr);
							formDTO.setFileNameSrSign("signature.GIF");
							formDTO.setForwardPathSr("/regCompChecker.do?label=SrSignature");
							formDTO.setGuidUpload("");
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute(RegCompCheckerConstant.FINAL_CONF_MSG, "Registration Completed Successfully");
							} else {
								request.setAttribute(RegCompCheckerConstant.FINAL_CONF_MSG, "पंजीकरण सफलतापूर्वक पूरा हो गया है|");
							}
						}
						forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
					} else if (!bd.checkRegNumber(formDTO.getRegInitNumber())) {
						// messages.add("MSG", new ActionMessage(
						// "no.records.found.for.this.application.number"));
						// saveMessages(request, messages);
						if ("en".equalsIgnoreCase(language)) {
							request.setAttribute("MSG", "No records found for this application number.");
						} else {
							request.setAttribute("MSG", "इस आवेदन नंबर के लिए कोई रिकॉर्ड नहीं मिला|");
						}
						eForm.setCheck("Y");
						request.setAttribute("checkRegID", eForm);
						forwardJsp = RegCompCheckerConstant.APPLICATION_SEARCH;
					} else if (bd.checkPinStatus(formDTO.getRegInitNumber())) {
						logger.debug("Pin generation");
						String regCompNumber = bd.getCompletionNumber(formDTO.getRegInitNumber());
						formDTO.setRegCompleteId(regCompNumber);
						formDTO.setPinGenerationCheck("Y");
						// messages.add("CONF_MSG", new ActionMessage(
						// "reg.registration.completion.confirmation"));
						if ("en".equalsIgnoreCase(language)) {
							request.setAttribute("CONF_MSG", "Registration completed successfully");
						} else {
							request.setAttribute("CONF_MSG", "पंजीकरण सफलतापूर्वक पूरा हो गया है|");
						}
						// saveMessages(request, messages);
						forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
					} else if (bd.checkRefusalStatus(formDTO.getRegInitNumber())) {
						if (bd.checkFinalDocGen(formDTO.getRegInitNumber())) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute(RegCompCheckerConstant.FINAL_REF_MSG, "Registration has been completed for this Registration Initiation Number");
							} else {
								request.setAttribute(RegCompCheckerConstant.FINAL_REF_MSG, "इस पंजीकरण शुरूआत संख्या के लिए पंजीकरण पूरा हो चुका है|");
							}
							forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
						} else {
							logger.debug("refused");
							//commented for refusal remarks
							/*String getRemarks = "";
							getRemarks=	bd.getRefusalRemarks(formDTO.getRegInitNumber());
							request.setAttribute("REFUSALMSG"," "+getRemarks);*/
							eForm.setCheck("REFCHK");
							forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
						}
					} else {
						// /////////////////HOLD RESUME////////////////////
						ArrayList estampDetls = bd.getEstampStatus(formDTO.getRegInitNumber());
						ArrayList estampRefundDetls = bd.getEstampRefundStatus(formDTO.getRegInitNumber());
						ArrayList cavList = bd.cavetsCheck(formDTO.getRegInitNumber());
						logger.debug("<----Checking estamp Details");
						RegCompMkrBD regmkrBD = new RegCompMkrBD();
						if (!bd.checkerMakerOffice(formDTO.getRegInitNumber(), officeId).equalsIgnoreCase("BOOKEDPROPER")) {
							messages.add("ERROR_MSG", new ActionMessage("diff.maker.office"));
							saveMessages(request, messages);
							eForm.setCheck("diffentOffice");
							forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
						} else if (regmkrBD.resumeLinkingProp(formDTO.getRegInitNumber())) {
							formDTO.setIsLinking("Y");
							eForm.setRegDTO(formDTO);
							forwardJsp = "confirmScreen";
						} else if (cavList != null && cavList.size() > 0) {
							eForm.setCaveatDetailsList(cavList);
							messages.add("ERROR_MSG", new ActionMessage("caveat.found"));
							messages.add("ALERT_MSG", new ActionMessage("alert.completion"));
							saveMessages(request, messages);
							eForm.setCheck("CAVCHK");
							forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
						} else if (userDesignation == null || userDesignation.equalsIgnoreCase("")) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERRORMSG", "Designation of the Employee is not Verified Please contact system adminstrator");
							} else {
								request.setAttribute("ERRORMSG", "कर्मचारी का पदनाम सत्यापित नहीं है| सिस्टम प्रशासक से संपर्क करें|");
							}
							saveMessages(request, messages);
							formDTO.setUserError("Y");
							eForm.setRegDTO(formDTO);
							forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
						} else if (userSign == null || userSign.equalsIgnoreCase("") || filePresent == false) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERRORMSG", "Signature of the Employee is not Captured. Please contact system adminstrator");
							} else {
								request.setAttribute("ERRORMSG", "कर्मचारी के हस्ताक्षर सत्यापित नहीं है| सिस्टम प्रशासक से संपर्क करें");
							}
							saveMessages(request, messages);
							formDTO.setUserError("Y");
							eForm.setRegDTO(formDTO);
							forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
						} else if (estampDetls.size() > 0 || estampRefundDetls.size() > 0) {
							if (estampDetls.size() > 0) {
								logger.debug("estamp not valid");
								for (int i = 0; i < estampDetls.size(); i++) {
									RegCompCheckerDTO rdto = (RegCompCheckerDTO) estampDetls.get(i);
									// logger.debug("Estamp Status---->"+rdto.getEstampStatus());
									// logger.debug("Estamp Id---->"+rdto.getEstampTxnId());
									formDTO.setEstampStatus(rdto.getEstampStatus());
									formDTO.setEstampTxnId(rdto.getEstampTxnId());
									if (rdto.getEstampStatus().equals("D")) {
										logger.debug("Status --Deactivated");
										// messages.add("ERRORMSG", new
										// ActionMessage(
										// "reg.Estamp.Deactivate"));
										if ("en".equalsIgnoreCase(language)) {
											request.setAttribute("ERRORMSG", "E-stamp for this Application has been De-activated.");
										} else {
											request.setAttribute("ERRORMSG", "इस आवेदन के लिए ई स्टाम्प निष्क्रिय कर दिया गया है|");
										}
									} else if (rdto.getEstampStatus().equals("E")) {
										// messages.add("ERRORMSG", new
										// ActionMessage(
										// "reg.Estamp.Expire"));
										if ("en".equalsIgnoreCase(language)) {
											request.setAttribute("ERRORMSG", "E-stamp for this application has expired.");
										} else {
											request.setAttribute("ERRORMSG", "इस आवेदन के लिए ई स्टाम्प की समय सीमा समाप्त हो गयी है|");
										}
									} else {
										// messages.add("ERRORMSG", new
										// ActionMessage(
										// "reg.Estamp.consumed"));
										if ("en".equalsIgnoreCase(language)) {
											request.setAttribute("ERRORMSG", "E-stamp for this application has already been consumed.");
										} else {
											request.setAttribute("ERRORMSG", "इस आवेदन के लिए ई स्टाम्प पहले ही उपभुक्त किया गया है|");
										}
									}
								}
							} else {
								for (int i = 0; i < estampRefundDetls.size(); i++) {
									RegCompCheckerDTO rdto = (RegCompCheckerDTO) estampRefundDetls.get(i);
									// logger.debug("Estamp Status---->"+rdto.getEstampStatus());
									// logger.debug("Estamp Id---->"+rdto.getEstampTxnId());
									formDTO.setEstampStatus(rdto.getEstampStatus());
									formDTO.setEstampTxnId(rdto.getEstampTxnId());
									// messages.add("ERRORMSG", new
									// ActionMessage(
									// "reg.Estamp.Refunded"));
									if ("en".equalsIgnoreCase(language)) {
										request.setAttribute("ERRORMSG", "E-stamp for this application has already been refunded.");
									} else {
										request.setAttribute("ERRORMSG", "इस आवेदन के लिए ई स्टाम्प पहले से ही वापस किया दिया गया है|");
									}
								}
							}
							saveMessages(request, messages);
							eForm.setRegDTO(formDTO);
							forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
						} else {
							String RegStatus = bd.getCancelledDetails(formDTO.getRegInitNumber());
							bd.insertTimeMakerStart(eForm, userId, officeId);
							if (RegStatus != "") {
								/*
								 * if(RegStatus.equals(RegCompCheckerConstant.REG_INIT_CONFIRMATION_STATUS
								 * )) { String adjNo =
								 * bd.getAdjDetails(formDTO.getRegInitNumber());
								 * if(adjNo.equals("")) { formDTO.setAdj(false);
								 * 
								 * } else { formDTO.setAdj(true);
								 * formDTO.setAdjNumber(adjNo); }
								 * 
								 * eForm.setMapPropertyTransPartyDisp(new
								 * HashMap());
								 * eForm.setMapTransactingPartiesDisp(new
								 * HashMap()); ArrayList estampDet =
								 * commonBo.getEstampDet
								 * (formDTO.getRegInitNumber());
								 * eForm.setEstampDetLst(estampDet);
								 * if(estampDet.size()!=0) { ArrayList list =
								 * new ArrayList(); list= (ArrayList)
								 * estampDet.get(0);
								 * eForm.setEstampCode((String)list.get(0));
								 * eForm.setEstampAmt((String)list.get(1));
								 * String transDate1=(String)list.get(2);
								 * SimpleDateFormat sdf1 = new SimpleDateFormat
								 * ("yyyy-mm-dd"); SimpleDateFormat sdf2 = new
								 * SimpleDateFormat ("dd/mm/yyyy"); Date d1 =
								 * sdf1.parse(transDate1); String transDate =
								 * sdf2.format(d1);
								 * eForm.setEstampDateTime(transDate); }
								 * 
								 * String[]
								 * deedInstArray=bd.getDeedInstId(formDTO
								 * .getRegInitNumber()); if(deedInstArray!=null
								 * && deedInstArray.length>0){
								 * 
								 * request.setAttribute("deedId",
								 * deedInstArray[0].trim());
								 * request.setAttribute("instId",
								 * deedInstArray[2].trim());
								 * formDTO.setDeedID(Integer
								 * .parseInt(deedInstArray[0].trim()));
								 * formDTO.setInstID
								 * (Integer.parseInt(deedInstArray[2].trim()));
								 * formDTO.setDeedType(deedInstArray[1].trim());
								 * formDTO.setInstType(deedInstArray[3].trim());
								 * logger.debug(
								 * "DEED------->"+deedInstArray[0].trim());
								 * logger.debug(
								 * "NAME----->"+deedInstArray[1].trim());
								 * logger.debug(
								 * "INST------>"+deedInstArray[2].trim());
								 * logger.debug(
								 * "NAME------>"+deedInstArray[3].trim());
								 * 
								 * }else { request.setAttribute("deedId", 0);
								 * request.setAttribute("instId", 0);
								 * formDTO.setDeedID(0); formDTO.setInstID(0); }
								 * 
								 * HashMap propMap =new HashMap();
								 * propMap=eForm.getMapPropertyTransParty();
								 * logger.debug(
								 * "in RegInitPropDetails action----------------------->"
								 * );
								 * 
								 * ArrayList propertyIdList = new ArrayList();
								 * if(formDTO.isAdj()) {
								 * propertyIdList=commonBo.
								 * getPropertyIdMarketValAdju(adjNo); } else {
								 * propertyIdList
								 * =commonBo.getPropertyIdMarketVal
								 * (formDTO.getRegInitNumber()); }
								 * 
								 * double totalMarketVal=0; for(int
								 * i=0;i<propertyIdList.size();i++){
								 * 
								 * ArrayList row_list=new ArrayList();
								 * row_list=(ArrayList)propertyIdList.get(i);
								 * String propIds=row_list.toString();
								 * propIds=propIds.substring(1,
								 * propIds.length()-1);logger.debug(
								 * "property id and market value list-->"
								 * +propIds); String
								 * propId[]=propIds.split(",");
								 * 
								 * 
								 * totalMarketVal=totalMarketVal+Double.parseDouble
								 * (propId[1].trim());
								 * 
								 * 
								 * 
								 * ArrayList
								 * transPartyDets=commonBo.getTransPartyDets
								 * (propId
								 * [0].trim(),formDTO.getRegInitNumber());
								 * 
								 * 
								 * for(int j=0;j<transPartyDets.size();j++){
								 * 
								 * CommonDTO dto=new CommonDTO();
								 * dto=(CommonDTO)transPartyDets.get(j);
								 * logger.debug
								 * ("transacting party list  role------>"
								 * +dto.getRole());logger.debug(
								 * "transacting party list  name------>"
								 * +dto.getName());
								 * logger.debug("transacting party list  id------>"
								 * +dto.getId());
								 * 
								 * }
								 * logger.debug("property id------>"+propId[0].
								 * trim());
								 * logger.debug("market value------>"+propId
								 * [1].trim());
								 * formDTO.setMarketValueTrns(propId
								 * [0].trim()+","+propId[1].trim());
								 * propMap.put(
								 * propId[0].trim()+","+propId[1].trim(),
								 * transPartyDets);
								 * 
								 * }
								 * 
								 * NumberFormat obj=new DecimalFormat("#0.00");
								 * formDTO
								 * .setTotalMarketValue(obj.format(totalMarketVal
								 * ));
								 * logger.debug("SIZE OF MAP IN ACTION"+propMap
								 * ); eForm.setMapPropertyTransParty(propMap);
								 * ArrayList dutyList = new ArrayList();
								 * ArrayList dutyListAtMaker = new ArrayList();
								 * if(formDTO.isAdj()) { dutyList =
								 * bd.getAdjudicatedDutyDetls(adjNo);
								 * dutyListAtMaker =
								 * bd.getLinkDutiesAtMaker(formDTO
								 * .getRegInitNumber(),formDTO); } else{
								 * dutyList =
								 * bd.getDutyDetls(formDTO.getRegInitNumber());
								 * dutyListAtMaker =
								 * bd.getLinkDutiesAtMaker(formDTO
								 * .getRegInitNumber(),formDTO); }
								 * 
								 * eForm.setDutyList(dutyList);
								 * eForm.setDutyListAtMaker(dutyListAtMaker);
								 * if(formDTO.isAdj()) {
								 * //formDTO.setModify(false);
								 * request.setAttribute("ADJ", "adj");
								 * forwardJsp =RegCompCheckerConstant.
								 * REG_INIT_PROP_DETAILS_READ_ONLY; } else {
								 * 
								 * forwardJsp=RegCompCheckerConstant.
								 * REG_INIT_PROP_DETAILS; } }
								 */
								if (RegStatus.equals(RegCompCheckerConstant.REG_FEE_SEAL_STATUS)) {
									String regSealCheck = bd.checkRegSealApllied(formDTO.getRegInitNumber());
									eForm.setRegSealCheck(regSealCheck);
									forwardJsp = RegCompCheckerConstant.REMAINING_SEAL;
								} else if (RegStatus.equals(RegCompCheckerConstant.LINKING_PAYMENT_STATUS)) {
									ArrayList linkingDetails = bd.getLinkingDuties(formDTO.getRegInitNumber(), eForm);
									if (linkingDetails.size() != 0) {
										eForm.setLinkingDutiesDispList(linkingDetails);
										forwardJsp = RegCompCheckerConstant.LINKING_PAYMENT_PAGE;
									} else {
										messages.add("ERRORMSG", new ActionMessage("no.linking.record.found"));
										saveMessages(request, messages);
										eForm.setCheck("L");
										eForm.setLinkingDutiesDisp(new ArrayList());
										request.setAttribute("checkRegID", eForm);
										forwardJsp = RegCompCheckerConstant.LINKING_PAYMENT_PAGE;
									}
								} else if (RegStatus.equalsIgnoreCase("45")) {
									formDTO.setParentFuncID("FUN_023");
									Double paidAmtAtChecker = bd.getPaidAmtChecker(formDTO.getRegInitNumber(), sourceModFlag, "1");
									ArrayList hmap = bd.getFinalDutiesForPayment(formDTO.getRegInitNumber(), eForm);
									ArrayList netBalance = eForm.getLinkingDutiesDisp();
									Iterator itr3 = netBalance.iterator();
									while (itr3.hasNext()) {
										RegCompCheckerDTO rgDTO = (RegCompCheckerDTO) itr3.next();
										formDTO.setNetStampDuty(rgDTO.getStampDutyPmt());
										formDTO.setNetRegFee(rgDTO.getRegFeePmt());
										// logger.debug("net stamp duty"+formDTO.getNetStampDuty());
										// logger.debug("net reg fee"+formDTO.getNetRegFee());
									}
									{
										double netStmp = Double.parseDouble(formDTO.getNetStampDuty());
										double netRegFee = Double.parseDouble(formDTO.getNetRegFee());
										formDTO.setNetPaidAmt(paidAmtAtChecker.toString());
										Double bal = netStmp - paidAmtAtChecker;
										// logger.debug("Amount Paid"+paidAmtAtChecker);
										// logger.debug("Bal"+bal);
										formDTO.setNetBalAmt(BigDecimal.valueOf(bal).toPlainString());
										if (bal <= 0) {
											if (paidAmtAtChecker > 0) {
												String estampFlag = bd.getEstampFlag(formDTO.getRegInitNumber());
												if (estampFlag == null || estampFlag.equalsIgnoreCase("")) {
													String purposeOfEstamp = bd.getEstampPurposeDetails(formDTO.getRegInitNumber());
													request.setAttribute("eStampRegId", formDTO.getRegInitNumber());
													request.setAttribute("eStampRegAmnt", formDTO.getNetStampDuty());
													request.setAttribute("eStampRegDistId", commonBo.getDistIdEstamp(formDTO.getRegInitNumber()));
													request.setAttribute("eStampRegFuncId", "023");
													request.setAttribute("eStampRegPurpose", purposeOfEstamp);
													request.setAttribute("sourceModFlag", "C");
													forwardJsp = "reginitEstamp";
													return mapping.findForward(forwardJsp);
												}
											}
											ArrayList partyDetails = bd.getPartyDet(formDTO.getRegInitNumber(), language);
											eForm.setPartiesDetailList(partyDetails);
											ArrayList witnessList = bd.getWitnessDet(formDTO.getRegInitNumber(), language);
											eForm.setWitnessDetailsList(witnessList);
											// logger.debug("<-------SIZE"+eForm.getPartiesDetailList().size());
											ArrayList consenterList = bd.getConsenterDet(formDTO.getRegInitNumber(), language);
											eForm.setConsenterDetailsList(consenterList);
											if (consenterList != null && consenterList.size() > 0)
												formDTO.setCnsntrChk("Y");
											else
												formDTO.setCnsntrChk("N");
											String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
											formDTO.setParentPathSign(path);
											formDTO.setFileNameSign("signature.GIF");
											formDTO.setForwardPath("/regCompChecker.do?txnPartyIdBiometric=");
											formDTO.setForwardName("regCompChecker");
											String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
											formDTO.setFileNameFP("FingerPrint.GIF");
											formDTO.setParentPathFP(pathFP);
											logger.debug("PARTIES_BIOMETRIC_DETAILS ......2");
											forwardJsp = RegCompCheckerConstant.PARTIES_BIOMETRIC_DETAILS;
										} else {
											formDTO.setStampDutyPmt("Y");
											// formDTO.setHoldCheck("Y");
											eForm.getRegDTO().setPreviousRemarks(bd.getHoldRemarkls(formDTO.getRegInitNumber(), "6"));
											forwardJsp = RegCompCheckerConstant.PROCEED_TO_PAYMENT;
										}
									}
								} else if (RegStatus.equals(RegCompCheckerConstant.WITNESS_DETAILS_STATUS)) {
									ArrayList witnessDetailsList = bd.getWitnessDetails(formDTO.getRegInitNumber());
									if (witnessDetailsList.size() != 0) {
										eForm.setWitnessDetailsList(witnessDetailsList);
									} else {
										logger.debug("WITNESS LIST EMPTY");
									}
									ArrayList consenterList = bd.getConsenterDetails(formDTO.getRegInitNumber());
									if (consenterList.size() != 0) {
										eForm.setConsenterDetailsList(consenterList);
									}
									forwardJsp = RegCompCheckerConstant.WITNESS_VIEW;
								} else if (RegStatus.equals(RegCompCheckerConstant.LINKING_HISTORY_STATUS)) {
									logger.debug("Next to Linking History Action");
									eForm.setCheck("");
									ArrayList linkHistoryList = bd.getLinkHstryDetails(formDTO.getRegInitNumber());
									if (linkHistoryList.size() != 0) {
										eForm.setLinkHistoryList(linkHistoryList);
									} else {
										logger.debug("Link History List Empty");
										eForm.setCheck("H");
										// eForm.setLinkingDutiesDisp(new
										// ArrayList());
										request.setAttribute("checkRegID", eForm);
									}
									forwardJsp = RegCompCheckerConstant.LINK_HISTORY_VIEW;
								} else if (RegStatus.equals(RegCompCheckerConstant.PRESENTATION_STATUS)) {
									logger.debug("BACK FROM MAKER MODIFY ACTION");
									formDTO.setSrSign("N");
									String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
									formDTO.setParentPathSign(path);
									formDTO.setFileNameSign("signature.GIF");
									formDTO.setForwardPath("/regCompChecker.do?txnPartyIdSignature=");
									formDTO.setForwardName("regCompChecker");
									formDTO.setParentPathGovtLttr(FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_GOVT_LETTER);
									formDTO.setFileNameGovtLttr(RegCompCheckerConstant.FILE_NAME_GOVT_LETTER);
									String pathSr = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_SR_SIGN;
									formDTO.setParentPathSrSign(pathSr);
									formDTO.setFileNameSrSign("signature.GIF");
									formDTO.setForwardPathSr("/regCompChecker.do?label=SrSignature");
									String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
									formDTO.setFileNameFP("FingerPrint.GIF");
									formDTO.setParentPathFP(pathFP);
									formDTO.setExecDate(bd.getExecutionDate(formDTO.getRegInitNumber()));
									ArrayList partyPresentList = bd.getPartiesPresentDetails(formDTO.getRegInitNumber(), language);
									eForm.setPartiesPresentList(partyPresentList);
									formDTO.setRemarks("");
									if (partyPresentList.size() != 0) {
										logger.debug("Parties Present");
										formDTO.setFirstCheck("Y");
										eForm.setPartiesPresentList(partyPresentList);
									} else {
										logger.debug("Parties present list EMPTY");
									}
									boolean check1 = bd.dateComparisonElevenMonths(formDTO.getExecDate());
									if (check1) {
										eForm.setCheck("ELMNCHK");
										forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
									} else {
										check = true;// bd.dateComparison(formDTO.getExecDate());
										logger.debug("check" + check);
										if (!check) {
											logger.debug("inside check failure");
											boolean caseDetails = false;// bd.updateTimeBarrdCaseDetails(formDTO,
											// cdate,
											// userId,"16");
											if (caseDetails) {
												String status = bd.emailAlertHold(formDTO.getRegInitNumber(), officeId);
												logger.debug("emailAlert---->status" + status);
												logger.debug("data Updated");
												messages.add("SUCCESS_MESSAGE", new ActionMessage("case.referred"));
												saveMessages(request, messages);
												eForm.setCheck("CMT");
												forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
											} else {
												logger.debug("data Not Updated");
												messages.add("MSG", new ActionMessage("case.referred.error"));
												saveMessages(request, messages);
												eForm.setCheck2("Y");
												request.setAttribute("checkRegID", eForm);
												forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
											}
										} else {
											forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
										}
									}
								} else if (RegStatus.equals(RegCompCheckerConstant.PRESENTATION_4_MNTH_STATUS)) {
									formDTO.setSrSign("N");
									formDTO.setRemarks("");
									String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
									formDTO.setParentPathSign(path);
									formDTO.setFileNameSign("signature.GIF");
									formDTO.setForwardPath("/regCompChecker.do?txnPartyIdSignature=");
									formDTO.setForwardName("regCompChecker");
									String pathSr = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_SR_SIGN;
									formDTO.setParentPathSrSign(pathSr);
									formDTO.setFileNameSrSign("signature.GIF");
									formDTO.setForwardPathSr("/regCompChecker.do?label=SrSignature");
									String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
									formDTO.setFileNameFP("FingerPrint.GIF");
									formDTO.setParentPathFP(pathFP);
									formDTO.setExecDate(bd.getExecutionDate(formDTO.getRegInitNumber()));
									ArrayList partyPresentList = bd.getPartiesPresentDetails(formDTO.getRegInitNumber(), language);
									// ArrayList govtOfficialList =
									// bd.getofficialList(); // commented on 10
									// june as drop down is no longer required
									// eForm.setGovtOfficialList(govtOfficialList);
									eForm.setPartiesPresentList(partyPresentList);
									if (partyPresentList.size() != 0) {
										logger.debug("Parties Present");
										formDTO.setFirstCheck("Y");
										eForm.setPartiesPresentList(partyPresentList);
									} else {
										logger.debug("Parties present list EMPTY");
									}
									formDTO.setComplaint("Y");
									formDTO.setGuidUpload(GUIDGenerator.getGUID());
									formDTO.setParentPathScan(FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PNALTY_RCPT);
									formDTO.setFileNameScan(RegCompCheckerConstant.FILE_NAME_PNALTY_RCPT);
									formDTO.setParentPathGovtLttr(FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_GOVT_LETTER);
									formDTO.setFileNameGovtLttr(RegCompCheckerConstant.FILE_NAME_GOVT_LETTER);
									forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
								} else if (RegStatus.equals(RegCompCheckerConstant.PRESENTATION_11_MNTH_STATUS)) {
									formDTO.setSrSign("N");
									formDTO.setRemarks("");
									String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
									formDTO.setParentPathSign(path);
									formDTO.setFileNameSign("signature.GIF");
									formDTO.setForwardPath("/regCompChecker.do?txnPartyIdSignature=");
									formDTO.setForwardName("regCompChecker");
									String pathSr = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_SR_SIGN;
									formDTO.setParentPathSrSign(pathSr);
									formDTO.setFileNameSrSign("signature.GIF");
									formDTO.setForwardPathSr("/regCompChecker.do?label=SrSignature");
									formDTO.setParentPathGovtLttr(FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_GOVT_LETTER);
									formDTO.setFileNameGovtLttr(RegCompCheckerConstant.FILE_NAME_GOVT_LETTER);
									String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
									formDTO.setFileNameFP("FingerPrint.GIF");
									formDTO.setParentPathFP(pathFP);
									formDTO.setExecDate(bd.getExecutionDate(formDTO.getRegInitNumber()));
									ArrayList partyPresentList = bd.getPartiesPresentDetails(formDTO.getRegInitNumber(), language);
									// ArrayList govtOfficialList =
									// bd.getofficialList(); // commented on 10
									// june as drop down is no longer required
									// eForm.setGovtOfficialList(govtOfficialList);
									eForm.setPartiesPresentList(partyPresentList);
									if (partyPresentList.size() != 0) {
										logger.debug("Parties Present");
										formDTO.setFirstCheck("Y");
										eForm.setPartiesPresentList(partyPresentList);
									} else {
										logger.debug("Parties present list EMPTY");
									}
									formDTO.setElvnMonthchk("Y");
									forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
								} else if (RegStatus.equals(RegCompCheckerConstant.COMPLIANCE_LIST_STATUS)) {
									String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
									if (deedInstArray != null && deedInstArray.length > 0) {
										request.setAttribute("deedId", deedInstArray[0].trim());
										request.setAttribute("instId", deedInstArray[1].trim());
									}
									propertyRelatedComplianceMap = bd.getPropertyRelatedComplianeList(formDTO.getRegInitNumber());
									eForm.setCompliancePropertyRelatedMap(propertyRelatedComplianceMap);
									partyRelatedComplianceMap = bd.getPartyRelatedComplianeList(formDTO.getRegInitNumber(), language);
									eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
									witnessPhotoUploadMap = bd.getWitnessDetailsForCompliance(formDTO.getRegInitNumber(), language);
									eForm.setUploadWitnessPhotograph(witnessPhotoUploadMap);
									forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
								} else if (RegStatus.equals(RegCompCheckerConstant.CHECKLIST_STATUS)) {
									ArrayList caveatList = bd.getCaveatDetails(formDTO.getRegInitNumber());
									ArrayList bankCaveatList = bd.getBnkCaveatDet(formDTO.getRegInitNumber());
									if (caveatList.size() != 0) {
										logger.debug("caveatList");
										eForm.setCaveatDetailsList(caveatList);
									}
									if (bankCaveatList.size() != 0) {
										logger.debug("caveatList");
										eForm.setCaveatBankDetailsList(bankCaveatList);
									}
									ArrayList checkList = bd.getCheckListDetails(formDTO.getRegInitNumber(), eForm);
									if (checkList.size() != 0) {
										logger.debug("CheckList");
										eForm.setCheckListDetailsList(checkList);
									} else {
										logger.debug("CheckList Empty");
									}
									ArrayList partyDetls = bd.getTransPartyDets(formDTO.getRegInitNumber());
									eForm.setPartyList(partyDetls);
									filePath = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_DEATH_CERT;
									formDTO.setDeathCert(RegCompCheckerConstant.FILE_NAME_DEATH_CERT);
									formDTO.setFilePath(filePath);
									String filePathRelation = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_RELTN_PROOF;
									formDTO.setRelationProof(RegCompCheckerConstant.FILE_NAME_RLTN_PROOF);
									formDTO.setFilePathRltn(filePathRelation);
									formDTO.setFilePathPOA(FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_POA_DOC);
									formDTO.setPoaUploadFilename(RegCompCheckerConstant.FILE_NAME_POA_DOC);
									forwardJsp = RegCompCheckerConstant.VIEW_CHECKLIST;
								} else if (RegStatus.equals(RegCompCheckerConstant.BIOMETRIC_DETAILS_STATUS)) {
									formDTO.setParentFuncID("FUN_023");
									String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
									formDTO.setParentPathSign(path);
									formDTO.setFileNameSign("signature.GIF");
									formDTO.setForwardPath("/regCompChecker.do?txnPartyIdBiometric=");
									formDTO.setForwardName("regCompChecker");
									String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
									formDTO.setFileNameFP("FingerPrint.GIF");
									formDTO.setParentPathFP(pathFP);
									ArrayList partyDetails = bd.getPartyDet(formDTO.getRegInitNumber(), language);
									eForm.setPartiesDetailList(partyDetails);
									ArrayList witnessList = bd.getWitnessDet(formDTO.getRegInitNumber(), language);
									eForm.setWitnessDetailsList(witnessList);
									ArrayList consenterList = bd.getConsenterDet(formDTO.getRegInitNumber(), language);
									eForm.setConsenterDetailsList(consenterList);
									if (consenterList != null && consenterList.size() > 0)
										formDTO.setCnsntrChk("Y");
									else
										formDTO.setCnsntrChk("N");
									// logger.debug("<-------SIZE"+eForm.getPartiesDetailList().size());
									logger.debug("PARTIES_BIOMETRIC_DETAILS ......3");
									forwardJsp = RegCompCheckerConstant.PARTIES_BIOMETRIC_DETAILS;
								} else if (RegStatus.equals(RegCompCheckerConstant.BOOK_TYPE_STATUS)) {
									formDTO.setParentFuncID("FUN_206");
									String deedId = bd.getDeedId(formDTO.getRegInitNumber());
									if (deedId.equals("1053")) // ****ConveyanceDeed
									{
										formDTO.setConvynceDeedCheck("Y");
									}
									// String
									// instId=bd.getInstId(formDTO.getRegInitNumber());
									String bookId = bd.getbookId(formDTO.getRegInitNumber());
									if (bookId != null && !bookId.equalsIgnoreCase("")) {
										String bookName = bd.getbookName(bookId);
										if (bookName != null && !bookName.equalsIgnoreCase("")) {
											formDTO.setBookId(bookId);
											formDTO.setBookName(bookName);
											eForm.setDisableBook("TRUE");
										} else {
											formDTO.setBookId("");
											formDTO.setBookName("");
										}
									} else {
										formDTO.setBookId("");
										formDTO.setBookName("");
									}
									ArrayList bookDetails = bd.getBookDetails();
									eForm.setBookDetailsList(bookDetails);
									forwardJsp = RegCompCheckerConstant.BOOK_TYPE_SELECTION;
								} else if (RegStatus.equals(RegCompCheckerConstant.ADD_SEALS_STATUS)) {
									forwardJsp = RegCompCheckerConstant.ADD_SEALS_PAGE;
								}
							} else {
								ArrayList holdList = bd.getHoldDetails(formDTO.getRegInitNumber());
								String flag = null;
								String holdPage = null;
								if (holdList.size() != 0) {
									Iterator itr = holdList.iterator();
									while (itr.hasNext()) {
										RegCompCheckerDTO rDTO = (RegCompCheckerDTO) itr.next();
										holdPage = rDTO.getHoldName();
									}
									if (holdPage.equalsIgnoreCase(RegCompCheckerConstant.LINKING_PAYMENT_PAGE)) {
										ArrayList linkingDetails = bd.getFinalDutiesForPayment(formDTO.getRegInitNumber(), eForm);
										if (linkingDetails.size() != 0) {
											eForm.setLinkingDutiesDispList(linkingDetails);
											forwardJsp = RegCompCheckerConstant.LINKING_PAYMENT_PAGE;
										} else {
											messages.add("ERRORMSG", new ActionMessage("no.linking.record.found"));
											saveMessages(request, messages);
											eForm.setCheck("L");
											eForm.setLinkingDutiesDisp(new ArrayList());
											request.setAttribute("checkRegID", eForm);
											forwardJsp = RegCompCheckerConstant.LINKING_PAYMENT_PAGE;
										}
									} else if (holdPage.equalsIgnoreCase(RegCompCheckerConstant.VIEW_COMPLIANCE_LIST)) {
										/*
										 * ArrayList complianceList =
										 * bd.getComplianceDetails
										 * (formDTO.getRegInitNumber());
										 * if(complianceList.size()!=0) {
										 * logger.debug("Compliance list---->");
										 * eForm
										 * .setComplianceListDisp(complianceList
										 * ); } else {
										 * logger.debug("Compliance list empty"
										 * ); } ArrayList partyDetails =
										 * bd.getPartyDet
										 * (formDTO.getRegInitNumber());
										 * if(partyDetails.size()!= 0) {
										 * logger.debug("party list");
										 * eForm.setPartiesDetailList
										 * (partyDetails); } else {
										 * logger.debug("party list is empty");
										 * } ArrayList witnessList =
										 * bd.getWitnessDet
										 * (formDTO.getRegInitNumber());
										 * if(witnessList.size() != 0) {
										 * logger.debug("witnessList");
										 * eForm.setWitnessDetailsList
										 * (witnessList); } else {
										 * logger.debug("witness list is empty"
										 * ); }
										 */
										String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
										if (deedInstArray != null && deedInstArray.length > 0) {
											request.setAttribute("deedId", deedInstArray[0].trim());
											request.setAttribute("instId", deedInstArray[1].trim());
										}
										propertyRelatedComplianceMap = bd.getPropertyRelatedComplianeList(formDTO.getRegInitNumber());
										eForm.setCompliancePropertyRelatedMap(propertyRelatedComplianceMap);
										partyRelatedComplianceMap = bd.getPartyRelatedComplianeList(formDTO.getRegInitNumber(), language);
										eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
										witnessPhotoUploadMap = bd.getWitnessDetailsForCompliance(formDTO.getRegInitNumber(), language);
										eForm.setUploadWitnessPhotograph(witnessPhotoUploadMap);
										eForm.getRegDTO().setHoldCheck("Y");
										eForm.getRegDTO().setPreviousRemarks(bd.getHoldRemarkls(formDTO.getRegInitNumber(), "5"));
										forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
									} else if (holdPage.equalsIgnoreCase("viewComplianceListDenial")) {
										String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
										if (deedInstArray != null && deedInstArray.length > 0) {
											request.setAttribute("deedId", deedInstArray[0].trim());
											request.setAttribute("instId", deedInstArray[1].trim());
										}
										propertyRelatedComplianceMap = bd.getPropertyRelatedComplianeList(formDTO.getRegInitNumber());
										eForm.setCompliancePropertyRelatedMap(propertyRelatedComplianceMap);
										partyRelatedComplianceMap = bd.getPartyRelatedComplianeList(formDTO.getRegInitNumber(), language);
										eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
										witnessPhotoUploadMap = bd.getWitnessDetailsForCompliance(formDTO.getRegInitNumber(), language);
										eForm.setUploadWitnessPhotograph(witnessPhotoUploadMap);
										eForm.getRegDTO().setHoldCheck("YD");
										eForm.getRegDTO().setPreviousRemarks(bd.getHoldRemarkls(formDTO.getRegInitNumber(), "11"));
										forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
									} else if (holdPage.equalsIgnoreCase(RegCompCheckerConstant.VIEW_CHECKLIST)) {
										ArrayList caveatList = bd.getCaveatDetails(formDTO.getRegInitNumber());
										ArrayList bankCaveatList = bd.getBnkCaveatDet(formDTO.getRegInitNumber());
										if (caveatList.size() != 0) {
											logger.debug("caveatList");
											eForm.setCaveatDetailsList(caveatList);
										}
										if (bankCaveatList.size() != 0) {
											logger.debug("caveatList");
											eForm.setCaveatBankDetailsList(bankCaveatList);
										}
										ArrayList checkList = bd.getCheckListDetails(formDTO.getRegInitNumber(), eForm);
										if (checkList.size() != 0) {
											logger.debug("CheckList");
											eForm.setCheckListDetailsList(checkList);
										} else {
											logger.debug("CheckList Empty");
										}
										ArrayList partyDetls = bd.getTransPartyDets(formDTO.getRegInitNumber());
										eForm.setPartyList(partyDetls);
										filePath = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_DEATH_CERT;
										formDTO.setDeathCert(RegCompCheckerConstant.FILE_NAME_DEATH_CERT);
										formDTO.setFilePath(filePath);
										String filePathRelation = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_RELTN_PROOF;
										formDTO.setRelationProof(RegCompCheckerConstant.FILE_NAME_RLTN_PROOF);
										formDTO.setFilePathRltn(filePathRelation);
										formDTO.setFilePathPOA(FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_POA_DOC);
										formDTO.setPoaUploadFilename(RegCompCheckerConstant.FILE_NAME_POA_DOC);
										eForm.getRegDTO().setHoldCheck("Y");
										eForm.getRegDTO().setPreviousRemarks(bd.getHoldRemarkls(formDTO.getRegInitNumber(), "4"));
										forwardJsp = RegCompCheckerConstant.VIEW_CHECKLIST;
									} else if (holdPage.equalsIgnoreCase(RegCompCheckerConstant.PROCEED_TO_PAYMENT)) {
										formDTO.setParentFuncID("FUN_023");
										Double paidAmtAtChecker = bd.getPaidAmtChecker(formDTO.getRegInitNumber(), sourceModFlag, "1");
										ArrayList hmap = bd.getFinalDutiesForPayment(formDTO.getRegInitNumber(), eForm);
										ArrayList netBalance = eForm.getLinkingDutiesDisp();
										Iterator itr3 = netBalance.iterator();
										while (itr3.hasNext()) {
											RegCompCheckerDTO rgDTO = (RegCompCheckerDTO) itr3.next();
											formDTO.setNetStampDuty(rgDTO.getStampDutyPmt());
											formDTO.setNetRegFee(rgDTO.getRegFeePmt());
											// logger.debug("net stamp duty"+formDTO.getNetStampDuty());
											// logger.debug("net reg fee"+formDTO.getNetRegFee());
										}
										if (bd.checkImpound(formDTO.getRegInitNumber().trim())) {
											double netStmp = Double.parseDouble(formDTO.getNetStampDuty());
											double netRegFee = Double.parseDouble(formDTO.getNetRegFee());
											formDTO.setNetPaidAmt(formDTO.getNetStampDuty());
											Double bal = netStmp - paidAmtAtChecker;
											bal = 0.0;
											formDTO.setNetBalAmt(BigDecimal.valueOf(bal).toPlainString());
											formDTO.setStampDutyPmt("Y");
											formDTO.setHoldCheck("Y");
											eForm.getRegDTO().setPreviousRemarks(bd.getHoldRemarkls(formDTO.getRegInitNumber(), "6"));
											forwardJsp = RegCompCheckerConstant.PROCEED_TO_PAYMENT;
										} else {
											double netStmp = Double.parseDouble(formDTO.getNetStampDuty());
											double netRegFee = Double.parseDouble(formDTO.getNetRegFee());
											formDTO.setNetPaidAmt(paidAmtAtChecker.toString());
											Double bal = netStmp - paidAmtAtChecker;
											// formDTO.setParentFuncID("FUN_023");
											// logger.debug("Amount Paid"+paidAmtAtChecker);
											// logger.debug("Bal"+bal);
											formDTO.setNetBalAmt(BigDecimal.valueOf(bal).toPlainString());
											if (bal <= 0) {
												ArrayList partyDetails = bd.getPartyDet(formDTO.getRegInitNumber(), language);
												eForm.setPartiesDetailList(partyDetails);
												ArrayList witnessList = bd.getWitnessDet(formDTO.getRegInitNumber(), language);
												eForm.setWitnessDetailsList(witnessList);
												// logger.debug("<-------SIZE"+eForm.getPartiesDetailList().size());
												ArrayList consenterList = bd.getConsenterDet(formDTO.getRegInitNumber(), language);
												eForm.setConsenterDetailsList(consenterList);
												if (consenterList != null && consenterList.size() > 0)
													formDTO.setCnsntrChk("Y");
												else
													formDTO.setCnsntrChk("N");
												String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
												formDTO.setParentPathSign(path);
												formDTO.setFileNameSign("signature.GIF");
												formDTO.setForwardPath("/regCompChecker.do?txnPartyIdBiometric=");
												formDTO.setForwardName("regCompChecker");
												String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
												formDTO.setFileNameFP("FingerPrint.GIF");
												formDTO.setParentPathFP(pathFP);
												logger.debug("PARTIES_BIOMETRIC_DETAILS ......4");
												// eForm.getRegDTO().setParentFuncID("FUN_023");
												forwardJsp = RegCompCheckerConstant.PARTIES_BIOMETRIC_DETAILS;
											} else {
												formDTO.setStampDutyPmt("Y");
												formDTO.setHoldCheck("Y");
												eForm.getRegDTO().setPreviousRemarks(bd.getHoldRemarkls(formDTO.getRegInitNumber(), "6"));
												forwardJsp = RegCompCheckerConstant.PROCEED_TO_PAYMENT;
											}
										}
									} else if (holdPage.equalsIgnoreCase(RegCompCheckerConstant.PROCEED_TO_PAYMENT_REG_FEE)) {
										formDTO.setParentFuncID("FUN_206");
										Double paidAmtAtChecker = bd.getPaidAmtChecker(formDTO.getRegInitNumber(), sourceModFlag, "2");
										ArrayList hmap = bd.getFinalDutiesForPayment(formDTO.getRegInitNumber(), eForm);
										ArrayList netBalance = eForm.getLinkingDutiesDisp();
										Iterator itr3 = netBalance.iterator();
										while (itr3.hasNext()) {
											RegCompCheckerDTO rgDTO = (RegCompCheckerDTO) itr3.next();
											formDTO.setNetStampDuty(rgDTO.getStampDutyPmt());
											formDTO.setNetRegFee(rgDTO.getRegFeePmt());
											// logger.debug("net stamp duty"+formDTO.getNetStampDuty());
											// logger.debug("net reg fee"+formDTO.getNetRegFee());
										}
										double netStmp = Double.parseDouble(formDTO.getNetStampDuty());
										double netRegFee = Double.parseDouble(formDTO.getNetRegFee());
										Double bal = netRegFee - paidAmtAtChecker;
										if (bal > 0.0) {
											formDTO.setRegFeePmt("Y");
											formDTO.setStampDutyPmt("");
											formDTO.setNetBalAmt(BigDecimal.valueOf(bal).toPlainString());
											formDTO.setNetPaidAmt(paidAmtAtChecker.toString());
											formDTO.setStampDutyPmt("");
											formDTO.setRegFeePmt("Y");
											formDTO.setRegFeeRadio("Y");
											forwardJsp = RegCompCheckerConstant.PROCEED_TO_PAYMENT;
										} else {
											String deedId = bd.getDeedId(formDTO.getRegInitNumber());
											if (deedId.equals("1053")) // ****ConveyanceDeed
											{
												formDTO.setConvynceDeedCheck("Y");
											}
											// String
											// instId=bd.getInstId(formDTO.getRegInitNumber());
											String bookId = bd.getbookId(formDTO.getRegInitNumber());
											if (bookId != null && !bookId.equalsIgnoreCase("")) {
												String bookName = bd.getbookName(bookId);
												if (bookName != null && !bookName.equalsIgnoreCase("")) {
													formDTO.setBookId(bookId);
													formDTO.setBookName(bookName);
													eForm.setDisableBook("TRUE");
												} else {
													formDTO.setBookId("");
													formDTO.setBookName("");
												}
											} else {
												formDTO.setBookId("");
												formDTO.setBookName("");
											}
											ArrayList bookDetails = bd.getBookDetails();
											eForm.setBookDetailsList(bookDetails);
											// eForm.getRegDTO().setParentFuncID("FUN_206");
											boolean flag2 = bd.insertFinalDuties(formDTO.getRegInitNumber(), eForm, userId, tDate);
											forwardJsp = RegCompCheckerConstant.BOOK_TYPE_SELECTION;
										}
									} else if (holdPage.equalsIgnoreCase("viewPresentPartiesmplr")) {
										formDTO.setHoldCheck("Y");
										formDTO.setRemarks("");
										formDTO.setPreviousRemarks(bd.getHoldRemarkls(formDTO.getRegInitNumber(), "10"));
										formDTO.setSrSign("N");
										logger.debug("BACK FROM MAKER MODIFY ACTION");
										// vinay
										if (bd.checkPinRequired(formDTO.getRegInitNumber())) {
											logger.debug("generate pin action");
											String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
											if (deedInstArray != null && deedInstArray.length > 0) {
												logger.debug("************deedId" + deedInstArray[0].trim());
												formDTO.setDeedId(deedInstArray[0].trim());
											}
											String regInitNumber = formDTO.getRegInitNumber();// bd.getInitiationNumber(formDTO.getRegCompleteId());
											boolean pinFlag = bd.pinGeneration(regInitNumber, formDTO.getDeedId(), userId);
											logger.debug("pin insertion  " + pinFlag);
										}
										// vinay
										// TODO:-
										// boolean flagPin =
										// bd.upadtePinDetails(eForm.getLinkHistoryList());
										// if(flagPin)
										// {
										String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
										formDTO.setParentPathSign(path);
										formDTO.setFileNameSign("signature.GIF");
										formDTO.setForwardPath("/regCompChecker.do?txnPartyIdSignature=");
										formDTO.setForwardName("regCompChecker");
										String pathSr = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_SR_SIGN;
										formDTO.setParentPathSrSign(pathSr);
										formDTO.setFileNameSrSign("signature.GIF");
										formDTO.setForwardPathSr("/regCompChecker.do?label=SrSignature");
										String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
										formDTO.setFileNameFP("FingerPrint.GIF");
										formDTO.setParentPathFP(pathFP);
										formDTO.setParentPathGovtLttr(FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_GOVT_LETTER);
										formDTO.setFileNameGovtLttr(RegCompCheckerConstant.FILE_NAME_GOVT_LETTER);
										// logger.debug("*******************partyId"+formDTO.getPartyId());
										// formDTO.setPhotoLst(new ArrayList());
										formDTO.setExecDate(bd.getExecutionDate(formDTO.getRegInitNumber()));
										ArrayList partyPresentList = bd.getPartiesPresentDetails(formDTO.getRegInitNumber(), language);
										// ArrayList govtOfficialList =
										// bd.getofficialList(); // commented on
										// 10 june as drop down is no longer
										// required
										// eForm.setGovtOfficialList(govtOfficialList);
										eForm.setPartiesPresentList(partyPresentList);
										if (partyPresentList.size() != 0) {
											logger.debug("Parties Present");
											formDTO.setFirstCheck("Y");
											eForm.setPartiesPresentList(partyPresentList);
										} else {
											logger.debug("Parties present list EMPTY");
										}
										boolean check1 = bd.dateComparisonElevenMonths(formDTO.getExecDate());
										if (check1) {
											eForm.setCheck("ELMNCHK");
											forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
										} else {
											boolean check12 = true;// bd.dateComparison(formDTO.getExecDate());
											logger.debug("check" + check12);
											if (!check12) {
												logger.debug("inside check failure");
												// messages.add("MSG", new
												// ActionMessage("execution.date.error"));
												// saveMessages(request,
												// messages);
												// eForm.setCheck2("Y");
												// request.setAttribute("checkRegID",
												// eForm);
												boolean caseDetails = false;// bd.updateTimeBarrdCaseDetails(formDTO,
												// cdate,
												// userId,"16");
												if (caseDetails) {
													String status = bd.emailAlertHold(formDTO.getRegInitNumber(), officeId);
													logger.debug("emailAlert---->status" + status);
													logger.debug("data Updated");
													messages.add("SUCCESS_MESSAGE", new ActionMessage("case.referred"));
													saveMessages(request, messages);
													eForm.setCheck("CMT");
													forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
												} else {
													logger.debug("data Not Updated");
													messages.add("MSG", new ActionMessage("case.referred.error"));
													saveMessages(request, messages);
													eForm.setCheck2("Y");
													request.setAttribute("checkRegID", eForm);
													forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
												}
											} else {
												// boolean flag36 =
												// bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(),
												// RegCompCheckerConstant.PRESENTATION_STATUS,cdate,userId);
												forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
											}
										}
										// }
										// else
										// {
										// formDTO.setCheck("LINKHIST");
										// messages.add("LINKMSG", new
										// ActionMessage("link.error"));
										// /saveMessages(request, messages);
										// forwardJsp =
										// RegCompCheckerConstant.LINK_HISTORY_VIEW;
										// }
									} else if (holdPage.equalsIgnoreCase(RegCompCheckerConstant.PRESENT_PARTIES_VIEW)) {
										// **********pending***************//get
										// last due date if it is not null & is
										// within 4 months else
										//
										formDTO.setSrSign("N");
										String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
										formDTO.setParentPathSign(path);
										formDTO.setFileNameSign("signature.GIF");
										formDTO.setForwardPath("/regCompChecker.do?txnPartyIdSignature=");
										formDTO.setForwardName("regCompChecker");
										String pathSr = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_SR_SIGN;
										formDTO.setParentPathSrSign(pathSr);
										formDTO.setFileNameSrSign("signature.GIF");
										formDTO.setForwardPathSr("/regCompChecker.do?label=SrSignature");
										String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
										formDTO.setFileNameFP("FingerPrint.GIF");
										formDTO.setParentPathFP(pathFP);
										formDTO.setParentPathGovtLttr(FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_GOVT_LETTER);
										formDTO.setFileNameGovtLttr(RegCompCheckerConstant.FILE_NAME_GOVT_LETTER);
										String lastDueDate = bd.getLastDueDate(formDTO.getRegInitNumber());
										boolean check3 = bd.dateComparison(lastDueDate);
										if (check3) {
											String penalityId = bd.getTimeBarrdStatus(formDTO.getRegInitNumber());
											boolean caseDetails = false;
											boolean flag1 = false;
											if (penalityId == "") {
												caseDetails = false;// bd.updateTimeBarrdCaseDetails(formDTO,
												// cdate,
												// userId,"18");
												String status = bd.emailAlertHold(formDTO.getRegInitNumber(), officeId);
												logger.debug("emailAlert---->status" + status);
											} else {
												formDTO.setComplaintNo(penalityId);
												flag1 = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), "18", cdate, userId);
											}
											if (caseDetails || flag1) {
												logger.debug("data Updated");
												messages.add("SUCCESS_MESSAGE", new ActionMessage("case.referred"));
												saveMessages(request, messages);
												eForm.setCheck("CMT1");
												forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
											} else {
												logger.debug("data Not Updated");
												messages.add("MSG", new ActionMessage("case.referred.error"));
												saveMessages(request, messages);
												eForm.setCheck2("Y");
												request.setAttribute("checkRegID", eForm);
												forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
											}
										} else {
											if ("en".equalsIgnoreCase(language)) {
												request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "The Application Cannot Resume ");
											} else {
												request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "आवेदन फिर से शुरू नहीं कर सकते ");
											}
											forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
										}
									}
								} else {// ************************below code
									// for Impounded Application*******//
									String status = bd.getImpundDetails(formDTO.getRegInitNumber());
									String stNum = "";
									if (!status.equals("")) {
										String stArr[] = status.split("~");
										status = stArr[1];
										stNum = stArr[0];
									}
									if (!status.equals("")) {
										if (stNum.equals("15")) {
											if (status.equals("I")) {
												// messages.add("SUCCESS_MESSAGE",
												// new ActionMessage(
												// "case.referred.I"));
												// saveMessages(request,
												// messages);
												if ("en".equalsIgnoreCase(language)) {
													request.setAttribute("SUCCESS_MESSAGE", "Case is Pending with DR");
												} else {
													request.setAttribute("SUCCESS_MESSAGE", "प्रकरण जिला पंजीयक के पास लंबित है|");
												}
												eForm.setCheck("IMI");
												forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
											} else if (status.equals("O")) {
												// messages.add("SUCCESS_MESSAGE",
												// new ActionMessage(
												// "case.referred.O"));
												// saveMessages(request,
												// messages);
												// eForm.setCheck("U");
												if ("en".equalsIgnoreCase(language)) {
													request.setAttribute("SUCCESS_MESSAGE", "Case is Pending with DR");
												} else {
													request.setAttribute("SUCCESS_MESSAGE", "प्रकरण जिला पंजीयक के पास लंबित है|");
												}
												eForm.setCheck("IMI");
												forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
											} else {
												ArrayList hmap = bd.getFinalDutiesForPayment(formDTO.getRegInitNumber(), eForm);
												ArrayList netBalance = eForm.getLinkingDutiesDisp();
												Iterator itr = netBalance.iterator();
												while (itr.hasNext()) {
													RegCompCheckerDTO rgDTO = (RegCompCheckerDTO) itr.next();
													formDTO.setNetStampDuty(rgDTO.getStampDutyPmt());
													formDTO.setNetRegFee(rgDTO.getRegFeePmt());
													// logger.debug("net stamp duty"+formDTO.getNetStampDuty());
													// logger.debug("net reg fee"+formDTO.getNetRegFee());
												}
												double netStmp = Double.parseDouble(formDTO.getNetStampDuty());
												double netRegFee = Double.parseDouble(formDTO.getNetRegFee());
												formDTO.setNetPaidAmt(formDTO.getNetStampDuty());
												Double bal = 0.0;
												// logger.debug("Bal"+bal);
												formDTO.setNetBalAmt(BigDecimal.valueOf(bal).toPlainString());
												formDTO.setStampDutyPmt("Y");
												formDTO.setComplaint("Y");
												formDTO.setGuidUpload(GUIDGenerator.getGUID());
												formDTO.setParentPathScan(FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PNALTY_RCPT);
												formDTO.setFileNameScan(RegCompCheckerConstant.FILE_NAME_PNALTY_RCPT);
												formDTO.setParentPathGovtLttr(FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_GOVT_LETTER);
												formDTO.setFileNameGovtLttr(RegCompCheckerConstant.FILE_NAME_GOVT_LETTER);
												forwardJsp = RegCompCheckerConstant.PROCEED_TO_PAYMENT;
											}
										} else if (stNum.equals("16")) {
											formDTO.setSrSign("N");
											String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
											formDTO.setParentPathSign(path);
											formDTO.setFileNameSign("signature.GIF");
											formDTO.setForwardPath("/regCompChecker.do?txnPartyIdSignature=");
											formDTO.setForwardName("regCompChecker");
											String pathSr = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_SR_SIGN;
											formDTO.setParentPathSrSign(pathSr);
											formDTO.setFileNameSrSign("signature.GIF");
											formDTO.setForwardPathSr("/regCompChecker.do?label=SrSignature");
											String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
											formDTO.setFileNameFP("FingerPrint.GIF");
											formDTO.setParentPathFP(pathFP);
											formDTO.setExecDate(bd.getExecutionDate(formDTO.getRegInitNumber()));
											check = bd.dateComparisonElevenMonths(formDTO.getExecDate());
											if (check) {
												formDTO.setExecDate(bd.getExecutionDate(formDTO.getRegInitNumber()));
												ArrayList partyPresentList = bd.getPartiesPresentDetails(formDTO.getRegInitNumber(), language);
												// ArrayList govtOfficialList =
												// bd.getofficialList(); //
												// commented on 10 june as drop
												// down is no longer required
												// eForm.setGovtOfficialList(govtOfficialList);
												eForm.setPartiesPresentList(partyPresentList);
												if (partyPresentList.size() != 0) {
													logger.debug("Parties Present");
													formDTO.setFirstCheck("Y");
													eForm.setPartiesPresentList(partyPresentList);
												} else {
													logger.debug("Parties present list EMPTY");
												}
												eForm.setCheck("ELMNCHK");
												forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
											} else {
												if (status.equals("I")) {
													// messages.add("SUCCESS_MESSAGE",
													// new ActionMessage(
													// "case.referred.I"));
													// saveMessages(request,
													// messages);
													if ("en".equalsIgnoreCase(language)) {
														request.setAttribute("SUCCESS_MESSAGE", "Case is Pending with DR");
													} else {
														request.setAttribute("SUCCESS_MESSAGE", "प्रकरण जिला पंजीयक के पास लंबित है|");
													}
													eForm.setCheck("IMI");
													forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
												} else if (status.equals("O")) {
													// messages.add("SUCCESS_MESSAGE",
													// new ActionMessage(
													// "case.referred.O"));
													// saveMessages(request,
													// messages);
													// eForm.setCheck("U");
													if ("en".equalsIgnoreCase(language)) {
														request.setAttribute("SUCCESS_MESSAGE", "Case is Pending with DR");
													} else {
														request.setAttribute("SUCCESS_MESSAGE", "प्रकरण जिला पंजीयक के पास लंबित है|");
													}
													eForm.setCheck("IMI");
													forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
												} else {
													formDTO.setExecDate(bd.getExecutionDate(formDTO.getRegInitNumber()));
													ArrayList partyPresentList = bd.getPartiesPresentDetails(formDTO.getRegInitNumber(), language);
													// ArrayList
													// govtOfficialList =
													// bd.getofficialList(); //
													// commented on 10 june as
													// drop down is no longer
													// required
													// eForm.setGovtOfficialList(govtOfficialList);
													eForm.setPartiesPresentList(partyPresentList);
													if (partyPresentList.size() != 0) {
														logger.debug("Parties Present");
														formDTO.setFirstCheck("Y");
														eForm.setPartiesPresentList(partyPresentList);
													} else {
														logger.debug("Parties present list EMPTY");
													}
													formDTO.setComplaint("Y");
													formDTO.setGuidUpload(GUIDGenerator.getGUID());
													formDTO.setParentPathScan(FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PNALTY_RCPT);
													formDTO.setFileNameScan(RegCompCheckerConstant.FILE_NAME_PNALTY_RCPT);
													formDTO.setParentPathGovtLttr(FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_GOVT_LETTER);
													formDTO.setFileNameGovtLttr(RegCompCheckerConstant.FILE_NAME_GOVT_LETTER);
													forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
												}
											}
										} else {
											if (status.equals("I")) {
												// messages.add("SUCCESS_MESSAGE",
												// new ActionMessage(
												// "case.referred.I"));
												// saveMessages(request,
												// messages);
												if ("en".equalsIgnoreCase(language)) {
													request.setAttribute("SUCCESS_MESSAGE", "Case is Pending with DR");
												} else {
													request.setAttribute("SUCCESS_MESSAGE", "प्रकरण जिला पंजीयक के पास लंबित है|");
												}
												eForm.setCheck("IMI");
												forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
											} else if (status.equals("O")) {
												// messages.add("SUCCESS_MESSAGE",
												// new ActionMessage(
												// "case.referred.O"));
												// saveMessages(request,
												// messages);
												// eForm.setCheck("U");
												if ("en".equalsIgnoreCase(language)) {
													request.setAttribute("SUCCESS_MESSAGE", "Case is Pending with DR");
												} else {
													request.setAttribute("SUCCESS_MESSAGE", "प्रकरण जिला पंजीयक के पास लंबित है|");
												}
												eForm.setCheck("IMI");
												forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
											} else {
												formDTO.setSrSign("N");
												String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
												formDTO.setParentPathSign(path);
												formDTO.setFileNameSign("signature.GIF");
												formDTO.setForwardPath("/regCompChecker.do?txnPartyIdSignature=");
												formDTO.setForwardName("regCompChecker");
												String pathSr = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_SR_SIGN;
												formDTO.setParentPathSrSign(pathSr);
												formDTO.setFileNameSrSign("signature.GIF");
												formDTO.setForwardPathSr("/regCompChecker.do?label=SrSignature");
												String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
												formDTO.setFileNameFP("FingerPrint.GIF");
												formDTO.setParentPathFP(pathFP);
												formDTO.setExecDate(bd.getExecutionDate(formDTO.getRegInitNumber()));
												ArrayList partyPresentList = bd.getPartiesPresentDetails(formDTO.getRegInitNumber(), language);
												// ArrayList govtOfficialList =
												// bd.getofficialList(); //
												// commented on 10 june as drop
												// down is no longer required
												// eForm.setGovtOfficialList(govtOfficialList);
												eForm.setPartiesPresentList(partyPresentList);
												if (partyPresentList.size() != 0) {
													logger.debug("Parties Present");
													formDTO.setFirstCheck("Y");
													eForm.setPartiesPresentList(partyPresentList);
												} else {
													logger.debug("Parties present list EMPTY");
												}
												formDTO.setElvnMonthchk("Y");
												forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
											}
										}
									} else {
										// String adjNo =
										// bd.getAdjDetails(formDTO.getRegInitNumber());
										// if(adjNo.equals(""))
										// {
										// formDTO.setAdj(false);
										// }
										// else
										// {
										// formDTO.setAdj(true);
										// formDTO.setAdjNumber(adjNo);
										// }
										eForm.setMapPropertyTransPartyDisp(new HashMap());
										eForm.setMapTransactingPartiesDisp(new HashMap());
										Boolean checkRegID = bd.checkData(formDTO.getRegInitNumber());
										//	Boolean checkEtokenFlag = bd.checkEtokenFlag(officeId);
										//	String checkEtokenUserId = "";
										//	if(checkEtokenFlag){
										//		 checkEtokenUserId = bd.checkEtokenUserId(formDTO.getRegInitNumber(),userId);
										//	}
										if (!checkRegID) {
											if ("en".equalsIgnoreCase(language)) {
												request.setAttribute("MSG", "No records found for this application number.");
											} else {
												request.setAttribute("MSG", "इस आवेदन नंबर के लिए कोई रिकॉर्ड नहीं मिला|");
											}
											eForm.setCheck("Y");
											request.setAttribute("checkRegID", eForm);
											forwardJsp = RegCompCheckerConstant.APPLICATION_SEARCH;
										}
										/*else if((checkEtokenUserId==null || checkEtokenUserId=="") && checkEtokenFlag )
											 {
												if ("en".equalsIgnoreCase(language)) {
													request.setAttribute("MSG","For the logged in office Application number can be processed through EToken");
												} else {
													request.setAttribute("MSG","लॉग इन ऑफिस के लिए एप्लीकेशन नंबर ईटोकेन के जरिए प्रोसेस किया जा सकता है");
												}
												eForm.setCheck("Y");
												request.setAttribute("checkRegID",eForm);
												forwardJsp = RegCompCheckerConstant.APPLICATION_SEARCH;
											} */
										/*else if(!checkEtokenUserId.equalsIgnoreCase(userId) && checkEtokenFlag)
										{
													if ("en".equalsIgnoreCase(language)) {
														request.setAttribute("MSG","Different counter has been assigned to this initiation number.");
													} else {
														request.setAttribute("MSG","इस  पंजीकरण संख्या को सौंपा गया  काउंटर अलग है |");
													}
													eForm.setCheck("Y");
													request.setAttribute("checkRegID",eForm);
													forwardJsp = RegCompCheckerConstant.APPLICATION_SEARCH;
												} */
										else {
											ArrayList estampDet = commonBo.getEstampDet(formDTO.getRegInitNumber());
											formDTO.setRegFeePaid(bd.getRegFeeRegInit(formDTO.getRegInitNumber()));
											eForm.setEstampDetLst(estampDet);
											if (estampDet.size() != 0) {
												ArrayList list = new ArrayList();
												list = (ArrayList) estampDet.get(0);
												eForm.setEstampCode((String) list.get(8));
												eForm.setEstampAmt((String) list.get(1));
												formDTO.setStampDutyPaid((Double.parseDouble((String) list.get(1))));
												String transDate1 = (String) list.get(2);
												SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd");
												SimpleDateFormat sdf2 = new SimpleDateFormat("dd/mm/yyyy");
												Date d1 = sdf1.parse(transDate1);
												String transDate = sdf2.format(d1);
												eForm.setEstampDateTime(transDate);
											}
											String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
											if (deedInstArray != null && deedInstArray.length > 0) {
												request.setAttribute("deedId", deedInstArray[0].trim());
												request.setAttribute("instId", deedInstArray[1].trim());
												formDTO.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
												formDTO.setInstID(Integer.parseInt(deedInstArray[1].trim()));
												// formDTO.setDeedType(deedInstArray[1].trim());
												// formDTO.setInstType(deedInstArray[3].trim());
												// logger.debug(
												// "DEED------->"+deedInstArray[0].trim());
												// logger.debug(
												// "INST----->"+deedInstArray[1].trim());
												// logger.debug(
												// "INST------>"+deedInstArray[2].trim());
												// logger.debug(
												// "NAME------>"+deedInstArray[3].trim());
												String adjFlag = deedInstArray[3].trim();
												if (adjFlag != null && adjFlag.equalsIgnoreCase("R")) {
													formDTO.setAdj(true);
												} else {
													formDTO.setAdj(false);
												}
											} else {
												request.setAttribute("deedId", 0);
												request.setAttribute("instId", 0);
												formDTO.setDeedID(0);
												formDTO.setInstID(0);
											}
											formDTO.setDeedtype1(commonBo.getDeedName(Integer.toString(formDTO.getDeedID()), language));
											formDTO.setInstType(commonBo.getInstrumentName(Integer.toString(formDTO.getInstID()), language));
											formDTO.setGuidUpload("");
											formDTO.setParentPathScan(FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_SUPP_DOC);
											formDTO.setFileNameScan(RegCompCheckerConstant.FILE_NAME_SUPP_DOC);
											formDTO.setParentPathScanDeed(FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_DEED_DOC);
											formDTO.setFileNameScanDeed(RegCompCheckerConstant.FILE_NAME_DEED_DOC);
											ArrayList deedDocList = bd.getDeedDocDetails(formDTO.getRegInitNumber());
											if (deedDocList.size() == 0) {
												formDTO.setDeedDocPath("");
												formDTO.setDeedDocName("");
												formDTO.setDeedDoc("N");
												formDTO.setPdfFlag("N");
												formDTO.setDeedStat("4");
											} else {
												for (int i = 0; i < deedDocList.size(); i++) {
													ArrayList subList = (ArrayList) deedDocList.get(i);
													String deedDocPath = (String) subList.get(1);
													String deedDocName = (String) subList.get(0);
													if (deedDocPath == null || deedDocPath.equals("")) {
														formDTO.setDeedDocName("");
														formDTO.setDeedDocPath("");
														formDTO.setDeedDoc("N");
														formDTO.setPdfFlag("N");
														formDTO.setDeedStat("4");
													} else {
														formDTO.setDeedDocName(deedDocName);
														formDTO.setDeedDocPath(deedDocPath);
														formDTO.setDeedDoc("Y");
														formDTO.setPdfFlag("Y");
														formDTO.setDeedStat("5");
													}
												}
											}
											//Added by rustam for set form link
				                        	ArrayList cyberformlist = bd.getCyberTehsilFormDetails(formDTO.getRegInitNumber());
				                        	if(cyberformlist.size() != 0){
				                        		for(int i = 0 ; i < cyberformlist.size() ;i++)
				        						{
				        							ArrayList subList = (ArrayList)cyberformlist.get(i);
				        							formDTO.setFormA1Path((String)subList.get(1));
				        							formDTO.setFormA2Path((String)subList.get(2));
				        						}
				                        	}else{
				                        		regForm.setFormA1Path("");
			        							regForm.setFormA2Path("");
				                        	}
											/*
											 * if(formDTO.getDeedID()==RegInitConstant
											 * .DEED_LEASE_PV ||
											 * formDTO.getDeedID
											 * ()==RegInitConstant.DEED_TRUST_PV
											 * ||
											 * formDTO.getDeedID()==RegInitConstant
											 * .DEED_MORTGAGE_PV){
											 * commonBo.getExtraDetls(regForm);
											 * }
											 */
											// ************CHCEK FOR DIFFERENT
											// DEEDS******************//
											int deedID = formDTO.getDeedID();
											String deedId = String.valueOf(formDTO.getDeedID());
											// logger.debug("<-----deed id----->"+deedId);
											String instId = String.valueOf(formDTO.getInstID());
											int instID = formDTO.getInstID();
											// logger.debug("<-----inst Id----->"+instId);
											int commonDeedFlag = commonBo.getCommonDeedFlag(deedId);
											formDTO.setCommonDeedFlag(commonDeedFlag);
											String dutyTxnId = bd.getDutyTxnId(formDTO.getRegInitNumber());
											if (dutyTxnId.equals(null) || dutyTxnId.equals(""))
												eForm.setDuty_txn_id(0);
											else
												eForm.setDuty_txn_id(Integer.parseInt(dutyTxnId));
											eForm = bd.getExtraDetls(eForm, language);
											bd.getAllUserEnterables(eForm, language);
											/*
											 * if(deedID==RegInitConstant.DEED_ADOPTION
											 * ||
											 * deedID==RegInitConstant.DEED_TRUST
											 * ||deedID==RegInitConstant.
											 * DEED_DEPOSIT_OF_TITLE ||
											 * deedID==RegInitConstant
											 * .DEED_CANCELLATION_OF_WILL_POA
											 * ||deedID
											 * ==RegInitConstant.DEED_LEASE_NPV
											 * ||deedID==RegInitConstant.
											 * DEED_MORTGAGE_NPV ||
											 * deedID==RegInitConstant.DEED_POA
											 * ||deedID==RegInitConstant.
											 * DEED_SETTLEMENT_NPV
											 * ||deedID==RegInitConstant
											 * .DEED_WILL_NPV ||
											 * deedID==RegInitConstant
											 * .DEED_TRANSFER_LEASE_NPV
											 * ||deedID==RegInitConstant.
											 * DEED_RECONV_MORTGAGE_NPV
											 * ||deedID==RegInitConstant.
											 * DEED_SURRENDER_LEASE_NPV ||
											 * deedID==RegInitConstant.
											 * DEED_PARTNERSHIP_NPV
											 * ||deedID==RegInitConstant
											 * .DEED_PARTITION_NPV
											 * ||deedID==RegInitConstant
											 * .DEED_AGREEMENT_MEMO_NPV ||
											 * deedID==RegInitConstant.
											 * DEED_COMPOSITION_NPV
											 * ||deedID==RegInitConstant
											 * .DEED_LETTER_OF_LICENCE_NPV
											 * ||deedID==RegInitConstant.
											 * DEED_SECURITY_BOND_NPV ||
											 * deedID==
											 * RegInitConstant.DEED_TRANSFER_NPV
											 * ||deedID==RegInitConstant.
											 * DEED_FURTHER_CHARGE_NPV ||
											 * formDTO.getCommonDeedFlag()==1){
											 * //for deeds following code set2
											 * 
											 * 
											 * if(deedID==RegInitConstant.
											 * DEED_AGREEMENT_MEMO_NPV ||
											 * deedID==
											 * RegInitConstant.DEED_TRANSFER_NPV
											 * ||deedID==RegInitConstant.
											 * DEED_FURTHER_CHARGE_NPV){
											 * 
											 * if(instID==RegInitConstant.
											 * INST_AGREEMENT_MEMO_NPV_1
											 * ||instID==RegInitConstant.
											 * INST_AGREEMENT_MEMO_NPV_2
											 * ||instID==RegInitConstant.
											 * INST_AGREEMENT_MEMO_NPV_3
											 * ||instID==RegInitConstant.
											 * INST_AGREEMENT_MEMO_NPV_4 ||
											 * instID==RegInitConstant.
											 * INST_AGREEMENT_MEMO_NPV_6
											 * ||instID==RegInitConstant.
											 * INST_AGREEMENT_MEMO_NPV_7
											 * ||instID==RegInitConstant.
											 * INST_AGREEMENT_MEMO_NPV_9 ||
											 * instID
											 * ==RegInitConstant.INST_TRANSFER_NPV_1
											 * ||instID==RegInitConstant.
											 * INST_TRANSFER_NPV_2
											 * ||instID==RegInitConstant
											 * .INST_FURTHER_CHARGE_NPV_1){
											 * 
											 * formDTO.setAgreeMemoInstFlag(
											 * RegInitConstant
											 * .NPV_PROP_NOT_REQ_CONSTANT_2);
											 * 
											 * }elseif(instID==RegInitConstant.
											 * INST_AGREEMENT_MEMO_NPV_10
											 * ||instID==RegInitConstant.
											 * INST_AGREEMENT_MEMO_NPV_5
											 * ||instID==RegInitConstant.
											 * INST_AGREEMENT_MEMO_NPV_8 ||
											 * instID
											 * ==RegInitConstant.INST_TRANSFER_NPV_3
											 * ||instID==RegInitConstant.
											 * INST_TRANSFER_NPV_4
											 * ||instID==RegInitConstant
											 * .INST_FURTHER_CHARGE_NPV_2){
											 * 
											 * formDTO.setAgreeMemoInstFlag(
											 * RegInitConstant
											 * .NPV_PROP_REQ_CONSTANT_1); }
											 * 
											 * 
											 * }
											 * 
											 * formDTO.setDeedTypeFlag(1);
											 * //regForm
											 * .setHdnDeclareShareCheck("N");
											 * 
											 * 
											 * }
											 */
											//adding gift instrument by ankit 
											if (deedID == RegInitConstant.DEED_ADOPTION || deedID == RegInitConstant.DEED_CANCELLATION_OF_WILL_POA || (deedID == RegInitConstant.DEED_TRUST && instID == RegInitConstant.INST_TRUST_NPV_P) || deedID == RegInitConstant.DEED_AGREEMENT_MEMO_NPV || deedID == RegInitConstant.DEED_AGREEMENT_MEMO_NPV_LOAN || deedID == RegInitConstant.DEED_DEPOSIT_OF_TITLE || deedID == RegInitConstant.DEED_SURRENDER_LEASE_NPV || deedID == RegInitConstant.DEED_RECONV_MORTGAGE_NPV || deedID == RegInitConstant.DEED_MORTGAGE_NPV || deedID == RegInitConstant.DEED_POA || deedID == RegInitConstant.DEED_TRANSFER_LEASE_NPV || deedID == RegInitConstant.DEED_PARTITION_NPV || deedID == RegInitConstant.DEED_PARTNERSHIP_NPV || deedID == RegInitConstant.DEED_LEASE_NPV || deedID == RegInitConstant.DEED_WILL_NPV || deedID == RegInitConstant.DEED_SETTLEMENT_NPV || deedID == RegInitConstant.DEED_DOC_AMEND || // amend added by
											// roopam-22 feb 2015
											(deedID == RegInitConstant.DEED_TRUST && instID == RegInitConstant.INST_TRUST_NPV_NP) || instID == RegInitConstant.INST_GIFT_MOVABLE_PROP || formDTO.getCommonDeedFlag() == 1) {
												// regForm.setHidnRegTxnId(eForm.getRegDTO().getRegInitNumber());
												// request.setAttribute("regForm",
												// regForm);
												String flags[] = commonBo.getInstrumentFlag(String.valueOf(instID));
												if (flags != null && flags[0] != null && flags[1] != null && flags[2] != null) {
													if (flags[2].trim().equalsIgnoreCase("Y")) {
														eForm.setCommonDeed(1);
													} else {
														eForm.setCommonDeed(0);
													}
													eForm.setPvReqFlag(flags[1].trim());
													eForm.setPropReqFlag(flags[0].trim());
												} else {
													eForm.setCommonDeed(0);
													eForm.setPvReqFlag("");
													eForm.setPropReqFlag("");
												}
												bd.landConfirmationScreen(eForm, language);
												// logger.debug("^^^^^^^"+formDTO.getDeedtype1());
												// logger.debug("^^^^^^^"+formDTO.getInstType());
												// if(regForm.getDeedID()!=0 &&
												// regForm.getInstID()!=0){
												request.setAttribute("deedId", formDTO.getDeedID());
												request.setAttribute("instId", formDTO.getInstID());
												ArrayList dutyList = new ArrayList();
												ArrayList dutyListAtMaker = new ArrayList();
												if (formDTO.isAdj()) {
													dutyList = bd.getAdjudicatedDutyDetls(formDTO.getRegInitNumber());
													dutyListAtMaker = bd.getLinkDutiesAtMaker(formDTO.getRegInitNumber(), formDTO);
												} else {
													dutyList = bd.getDutyDetls(formDTO.getRegInitNumber());
													dutyListAtMaker = bd.getLinkDutiesAtMaker(formDTO.getRegInitNumber(), formDTO);
												}
												for (int i = 0; i < dutyList.size(); i++) {
													RegCompCheckerDTO rrdto = (RegCompCheckerDTO) dutyList.get(i);
													formDTO.setExempStamp(rrdto.getExempStamp());
													formDTO.setExempReg(rrdto.getExempReg());
													formDTO.setTotalduty(rrdto.getTotalduty());
													formDTO.setRegistrationFee(rrdto.getRegistrationFee());
												}
												// eForm.setSelectedExemptionList(exemListNew);
												// to get Duties stored during
												// registration Initiation
												// Process
												// formDTO.setStampduty1(dutyListArr[0].trim());
												// formDTO.setNagarPalikaDuty(dutyListArr[2].trim());
												// formDTO.setPanchayatDuty(dutyListArr[1].trim());
												// formDTO.setUpkarDuty(dutyListArr[3].trim());
												// formDTO.setTotalduty(dutyListArr[5].trim());
												// formDTO.setRegistrationFee(dutyListArr[4].trim());
												eForm.setDutyList(dutyList);
												eForm.setDutyListAtMaker(dutyListAtMaker);
												// }
												/*
												 * if(formDTO.getDeedID()==RegInitConstant
												 * .DEED_DEPOSIT_OF_TITLE ||
												 * formDTO
												 * .getDeedID()==RegInitConstant
												 * .DEED_LEASE_NPV ||
												 * formDTO.getDeedID
												 * ()==RegInitConstant
												 * .DEED_MORTGAGE_NPV ||
												 * formDTO.
												 * getDeedID()==RegInitConstant
												 * .DEED_POA ||
												 * formDTO.getDeedID
												 * ()==RegInitConstant
												 * .DEED_SETTLEMENT_NPV ||
												 * formDTO
												 * .getDeedID()==RegInitConstant
												 * .DEED_WILL_NPV ||
												 * formDTO.getDeedID
												 * ()==RegInitConstant
												 * .DEED_TRANSFER_LEASE_NPV ||
												 * formDTO
												 * .getDeedID()==RegInitConstant
												 * .DEED_RECONV_MORTGAGE_NPV ||
												 * formDTO
												 * .getDeedID()==RegInitConstant
												 * .DEED_SURRENDER_LEASE_NPV ||
												 * formDTO
												 * .getDeedID()==RegInitConstant
												 * .DEED_COMPOSITION_NPV ||
												 * formDTO
												 * .getDeedID()==RegInitConstant
												 * .DEED_SECURITY_BOND_NPV ||
												 * formDTO
												 * .getDeedID()==RegInitConstant
												 * .DEED_LETTER_OF_LICENCE_NPV
												 * ||(formDTO.getDeedID()==
												 * RegInitConstant.DEED_TRUST &&
												 * formDTO
												 * .getInstID()==RegInitConstant
												 * .INST_TRUST_NPV_P ) ||
												 * (formDTO
												 * .getDeedID()==RegInitConstant
												 * .DEED_PARTNERSHIP_NPV //&&
												 * //(formDTO.getInstID()==
												 * RegInitConstant
												 * .INST_PARTNERSHIP_NPV_1 ||
												 * formDTO
												 * .getInstID()==RegInitConstant
												 * .INST_PARTNERSHIP_NPV_2 ) )||
												 * formDTO
												 * .getDeedID()==RegInitConstant
												 * .DEED_PARTITION_NPV ||
												 * (formDTO
												 * .getDeedID()==RegInitConstant
												 * .DEED_AGREEMENT_MEMO_NPV &&
												 * formDTO
												 * .getAgreeMemoInstFlag()
												 * ==RegInitConstant
												 * .NPV_PROP_REQ_CONSTANT_1) ||
												 * (formDTO.getDeedID()==
												 * RegInitConstant
												 * .DEED_TRANSFER_NPV &&
												 * formDTO.
												 * getAgreeMemoInstFlag()
												 * ==RegInitConstant
												 * .NPV_PROP_REQ_CONSTANT_1) ||
												 * (formDTO.getDeedID()==
												 * RegInitConstant
												 * .DEED_FURTHER_CHARGE_NPV &&
												 * formDTO
												 * .getAgreeMemoInstFlag()
												 * ==RegInitConstant
												 * .NPV_PROP_REQ_CONSTANT_1) ||
												 * formDTO
												 * .getDeedTypeFlag()==1||
												 * formDTO.getCommonDeedFlag()
												 * == 1){
												 */
												eForm.setDtlsMapConsntr(commonBo.getConsenters(formDTO.getRegInitNumber()));
												eForm.setConsnterDuteisFlag(bd.getConsenterFlag(formDTO.getRegInitNumber()));
												eForm.setConsnterAmount(bd.getConsenterAmount(formDTO.getRegInitNumber()));
												eForm.setListDtoAdju(commonBo.getAdditonalUploadsAdju(formDTO.getRegInitNumber()));// added
												// by
												// roopam-22
												// may
												// 2015
												if (eForm.getPvReqFlag().equalsIgnoreCase("N")) {
													if (formDTO.isAdj() == true) {
														request.setAttribute("ADJ", "adj");
														forwardJsp = RegCompCheckerConstant.REG_INIT_PROP_DETLS_NPV_READ_ONLY;// adju
														// jsp
													} else {
														forwardJsp = RegCompCheckerConstant.REG_INIT_PROP_DETLS_NPV;
													}
												} else {
													if (formDTO.isAdj() == true) {
														request.setAttribute("ADJ", "adj");
														forwardJsp = RegCompCheckerConstant.REG_INIT_PROP_DETAILS_READ_ONLY;// adju
														// jsp
													} else {
														forwardJsp = RegCompCheckerConstant.REG_INIT_PROP_DETAILS;
													}
												}
												request.setAttribute("reginit", eForm);
											} else {
												/*
												 * HashMap propMap =new
												 * HashMap();propMap=eForm.
												 * getMapPropertyTransParty();
												 * //logger.debug(
												 * "in RegInitPropDetails action----------------------->"
												 * );
												 * 
												 * ArrayList propertyIdList =
												 * new ArrayList();
												 * //if(formDTO.isAdj()) //{ //
												 * propertyIdList=commonBo.
												 * getPropertyIdMarketValAdju
												 * (formDTO.getRegInitNumber());
												 * //} //else //{
												 * propertyIdList=
												 * commonBo.getPropertyIdMarketVal
												 * (formDTO.getRegInitNumber());
												 * //}
												 * 
												 * double totalMarketVal=0;
												 * for(int
												 * i=0;i<propertyIdList.size
												 * ();i++){
												 * 
												 * ArrayList row_list=new
												 * ArrayList();
												 * row_list=(ArrayList
												 * )propertyIdList.get(i);
												 * //String
												 * propIds=row_list.toString();
												 * /
												 * /propIds=propIds.substring(1,
												 * propIds.length()-1); //
												 * logger.debug(
												 * "property id and market value list-->"
												 * +propIds); //String
												 * propId[]=propIds.split(",");
												 * String propId =
												 * (String)row_list.get(0);
												 * String mrktVal =
												 * (String)row_list.get(1);
												 * 
												 * mrktVal = mrktVal ==
												 * null?"0":mrktVal; mrktVal =
												 * mrktVal == ""?"0":mrktVal;
												 * totalMarketVal
												 * =totalMarketVal+
												 * Double.parseDouble
												 * (mrktVal.trim());
												 * RegCommonForm rForm = new
												 * RegCommonForm();
												 * rForm.setShareOfPropSize(0);
												 * if(formDTO.getDeedID()!=
												 * RegInitConstant
												 * .DEED_PARTITION_PV) {
												 * eForm.setPropListPVDeed
												 * (null); ArrayList
												 * transPartyDets
												 * =commonBo.getTransPartyDets
												 * (propId
												 * .trim(),formDTO.getRegInitNumber
												 * (
												 * ),language,formDTO.getDeedID(
												 * ),formDTO.getInstID(),rForm);
												 * for(int
												 * j=0;j<transPartyDets.size
												 * ();j++){
												 * 
												 * CommonDTO dto=new
												 * CommonDTO();
												 * dto=(CommonDTO)transPartyDets
												 * .get(j);logger.debug(
												 * "transacting party list  role------>"
												 * +dto.getRole());
												 * logger.debug(
												 * "transacting party list  name------>"
												 * +dto.getName());
												 * logger.debug(
												 * "transacting party list  id------>"
												 * +dto.getId());
												 * 
												 * }
												 * logger.debug("property id------>"
												 * +propId);logger.debug(
												 * "market value------>"
												 * +mrktVal.trim());
												 * propMap.put(
												 * propId.trim()+","+
												 * mrktVal.trim(),
												 * transPartyDets); } else{
												 * eForm
												 * .setPropListPVDeed(commonBo
												 * .getPropertyListPVDeed
												 * (propertyIdList)); }
												 * 
												 * }
												 * 
												 * if(formDTO.getDeedID()==
												 * RegInitConstant
												 * .DEED_PARTITION_PV){ //vinay
												 * error -- resolved(Simran)
												 * ArrayList
												 * transPartyDets=commonBo
												 * .getTransPartyDetsNonPropDeed
												 * (formDTO.getRegInitNumber(),
												 * formDTO
												 * .getCommonDeedFlag(),language
												 * ,formDTO.getDeedID(),formDTO.
												 * getInstID());
												 * eForm.setTransPartyListPVDeed
												 * (transPartyDets); }else{
												 * eForm
												 * .setTransPartyListPVDeed(
												 * null); }
												 * 
												 * NumberFormat obj=new
												 * DecimalFormat("#0.00");
												 * formDTO
												 * .setTotalMarketValue(obj
												 * .format(totalMarketVal));
												 * eForm
												 * .setMapPropertyTransParty
												 * (propMap);
												 */
												String flags[] = commonBo.getInstrumentFlag(String.valueOf(instID));
												if (flags != null && flags[0] != null && flags[1] != null && flags[2] != null) {
													if (flags[2].trim().equalsIgnoreCase("Y")) {
														eForm.setCommonDeed(1);
													} else {
														eForm.setCommonDeed(0);
													}
													eForm.setPvReqFlag(flags[1].trim());
													eForm.setPropReqFlag(flags[0].trim());
												} else {
													eForm.setCommonDeed(0);
													eForm.setPvReqFlag("");
													eForm.setPropReqFlag("");
												}
												ArrayList propertyIdList = new ArrayList();
												propertyIdList = commonBo.getPropertyIdMarketVal(formDTO.getRegInitNumber());
												HashMap propMap = new HashMap();
												propMap = eForm.getMapPropertyTransParty();
												double totalMarketVal = 0;
												int numberOfProperties = propertyIdList.size();
												/*
												 * for(int
												 * i=0;i<propertyIdList.size
												 * ();i++){
												 * 
												 * ArrayList row_list=new
												 * ArrayList();
												 * row_list=(ArrayList
												 * )propertyIdList.get(i);
												 * String
												 * propIds=row_list.toString();
												 * propIds=propIds.substring(1,
												 * propIds.length()-1);
												 * 
												 * String
												 * propId[]=propIds.split(",");
												 * 
												 * if(eForm.getPvReqFlag().
												 * equalsIgnoreCase("Y")){
												 * if(!propId
												 * [1].trim().equalsIgnoreCase
												 * ("") &&
												 * !propId[1].trim().equals
												 * (null) &&!propId[1].trim().
												 * equalsIgnoreCase("null")){
												 * totalMarketVal
												 * =totalMarketVal+
												 * Double.parseDouble
												 * (propId[1].trim()); }else{
												 * totalMarketVal=0; } }
												 * 
												 * RegCommonForm rForm = new
												 * RegCommonForm();
												 * rForm.setShareOfPropSize(0);
												 * 
												 * ArrayList
												 * transPartyDets=commonBo
												 * .getTransPartyDets
												 * (propId[0].trim
												 * (),formDTO.getRegInitNumber
												 * (),language,
												 * formDTO.getDeedID(),
												 * formDTO.getInstID(),rForm);
												 * 
												 * 
												 * if(propId[0].trim().length()==
												 * 15){
												 * propId[0]="0"+propId[0].trim
												 * (); }
												 * 
												 * if(eForm.getPvReqFlag().
												 * equalsIgnoreCase("Y"))
												 * propMap
												 * .put(propId[0].trim()+","
												 * +propId[1].trim(),
												 * transPartyDets); else{
												 * propMap.put(propId[0].trim(),
												 * transPartyDets); // without
												 * market value where valuation
												 * is not required }
												 */
												for (int i = 0; i < propertyIdList.size(); i++) {
													ArrayList row_list = new ArrayList();
													row_list = (ArrayList) propertyIdList.get(i);
													// String
													// propIds=row_list.toString();
													// propIds=propIds.substring(1,
													// propIds.length()-1);
													// String
													// propId[]=propIds.split(",");
													String propId = (String) row_list.get(0);
													String mrktVal = (String) row_list.get(1);
													mrktVal = mrktVal == null ? "0" : mrktVal;
													mrktVal = mrktVal == "" ? "0" : mrktVal;
													logger.debug(mrktVal);
													// if(mrktVal != null)
													// {
													totalMarketVal = totalMarketVal + Double.parseDouble(mrktVal.trim());
													// }
													RegCommonForm rForm = new RegCommonForm();
													rForm.setShareOfPropSize(0);
													if (formDTO.getDeedID() != RegInitConstant.DEED_PARTITION_PV) {
														regForm.setPropListPVDeed(null);
														// Updated by Rakesh for
														// PartyPropMappingDisplay:
														// Start
														ArrayList transPartyDets = null;
														String clr_flag = commonBo.getClrFlagByPropId(propId.trim());
														if (clr_flag != null && !clr_flag.isEmpty()) {
															if (clr_flag.equalsIgnoreCase("Y")) {
																transPartyDets = commonBo.getTransPartyDetsCLR(propId, formDTO.getRegInitNumber(), language, formDTO.getDeedID(), formDTO.getInstID(), rForm);
															} else {
																transPartyDets = commonBo.getTransPartyDets(propId, formDTO.getRegInitNumber(), language, formDTO.getDeedID(), formDTO.getInstID(), rForm);
															}
														} else {
															transPartyDets = commonBo.getTransPartyDets(propId, formDTO.getRegInitNumber(), language, formDTO.getDeedID(), formDTO.getInstID(), rForm);
														}
														// Updated by Rakesh for
														// PartyPropMappingDisplay:
														// End
														// ArrayList
														// transPartyDets=commonBo.getTransPartyDets(propId,formDTO.getRegInitNumber(),language,formDTO.getDeedID(),formDTO.getInstID(),rForm);
														for (int j = 0; j < transPartyDets.size(); j++) {
															CommonDTO dto = new CommonDTO();
															dto = (CommonDTO) transPartyDets.get(j);
															logger.debug("transacting party list  role------>" + dto.getRole());
															logger.debug("transacting party list  name------>" + dto.getName());
															logger.debug("transacting party list  id------>" + dto.getId());
														}
														logger.debug("property id------>" + propId);
														logger.debug("market value------>" + mrktVal);
														propMap.put(propId + "," + mrktVal, transPartyDets);
													} else {
														eForm.setPropListPVDeed(commonBo.getPropertyListPVDeed(propertyIdList));
													}
												}
												//changes by ankit for new gift instrument
												if (formDTO.getDeedID() == RegInitConstant.DEED_PARTITION_PV || formDTO.getInstID() == 2041) {
													ArrayList transPartyDets = commonBo.getTransPartyDetsNonPropDeed(formDTO.getRegInitNumber(), formDTO.getCommonDeedFlag(), language, formDTO.getDeedID(), formDTO.getInstID());
													eForm.setTransPartyListPVDeed(transPartyDets);
													eForm.setTransPartyListNonPropDeed(transPartyDets);
												} else {
													eForm.setTransPartyListPVDeed(null);
												}
												NumberFormat obj = new DecimalFormat("#0.00");
												formDTO.setTotalMarketValue(obj.format(totalMarketVal));
												eForm.setMapPropertyTransParty(propMap);
												eForm.setDtlsMapConsntr(commonBo.getConsenters(formDTO.getRegInitNumber()));
												String[] param = commonBo.getConsenterFlag(formDTO.getRegInitNumber());
												regForm.setConsenterWithConsidFlag(param[0]);
												// double
												// finalMVDouble=Double.parseDouble(finalMV);
												// finalMVDouble=Math.ceil(finalMVDouble);
												/*
												 * String
												 * totalMarketValString=BigDecimal
												 * .valueOf(totalMarketVal).
												 * toPlainString();
												 * formDTO.setTotalMarketValue
												 * (totalMarketValString);
												 * eForm.
												 * setMapPropertyTransParty
												 * (propMap);
												 */
												ArrayList dutyList = new ArrayList();
												ArrayList dutyListAtMaker = new ArrayList();
												if (formDTO.isAdj()) {
													dutyList = bd.getAdjudicatedDutyDetls(formDTO.getRegInitNumber());
													dutyListAtMaker = bd.getLinkDutiesAtMaker(formDTO.getRegInitNumber(), formDTO);
												} else {
													dutyList = bd.getDutyDetls(formDTO.getRegInitNumber());
													dutyListAtMaker = bd.getLinkDutiesAtMaker(formDTO.getRegInitNumber(), formDTO);
												}
												for (int i = 0; i < dutyList.size(); i++) {
													RegCompCheckerDTO rrdto = (RegCompCheckerDTO) dutyList.get(i);
													formDTO.setExempStamp(rrdto.getExempStamp());
													formDTO.setExempReg(rrdto.getExempReg());
													formDTO.setTotalduty(rrdto.getTotalduty());
													formDTO.setRegistrationFee(rrdto.getRegistrationFee());
												}
												// to get Duties stored during
												// registration Initiation
												// Process
												// formDTO.setStampduty1(dutyListArr[0].trim());
												// formDTO.setNagarPalikaDuty(dutyListArr[2].trim());
												// formDTO.setPanchayatDuty(dutyListArr[1].trim());
												// formDTO.setUpkarDuty(dutyListArr[3].trim());
												// formDTO.setTotalduty(dutyListArr[5].trim());
												// formDTO.setRegistrationFee(dutyListArr[4].trim());
												eForm.setDtlsMapConsntr(commonBo.getConsenters(formDTO.getRegInitNumber()));
												eForm.setDutyList(dutyList);
												eForm.setDutyListAtMaker(dutyListAtMaker);
												eForm.setConsnterDuteisFlag(bd.getConsenterFlag(formDTO.getRegInitNumber()));
												eForm.setConsnterAmount(bd.getConsenterAmount(formDTO.getRegInitNumber()));
												eForm.setListDtoAdju(commonBo.getAdditonalUploadsAdju(formDTO.getRegInitNumber()));// added
												// by
												// roopam-22
												// may
												// 2015
												if (eForm.getPvReqFlag().equalsIgnoreCase("N")) {
													if (formDTO.isAdj() == true) {
														request.setAttribute("ADJ", "adj");
														forwardJsp = RegCompCheckerConstant.REG_INIT_PROP_DETLS_NPV_READ_ONLY;
													} else {
														forwardJsp = RegCompCheckerConstant.REG_INIT_PROP_DETLS_NPV;
													}
												} else {
													if (formDTO.isAdj() == true) {
														request.setAttribute("ADJ", "adj");
														forwardJsp = RegCompCheckerConstant.REG_INIT_PROP_DETAILS_READ_ONLY;
													} else {
														forwardJsp = RegCompCheckerConstant.REG_INIT_PROP_DETAILS;
													}
												}
											}
										}
									}
								}
							}
						}
					}
					formDTO.setForwardPage(forwardJsp);
				}// end
				// added for aadhar EKYC by ankit ---- start
				/*
				 * if("Aadhar_ConsenterValidation".equalsIgnoreCase(actionName)){
				 * logger.debug("PARTIES_BIOMETRIC_DETAILS ...... "+actionName);
				 * callEKYCWebService( eForm, formDTO, bd, language,
				 * actionName); }
				 */
				if (RegCompCheckerConstant.AADHAR_PARTY_DETAILS_ACTION.equalsIgnoreCase(actionName)) {
					String forView = request.getParameter("for");
					String partyId = request.getParameter("key");
					if ("PARTY".equalsIgnoreCase(forView)) {
						regForm.setAdharDetailDTO(bd.getAadharDetailsByPartyTxnId(partyId));
					}
					if ("CONSENTER".equalsIgnoreCase(forView)) {
						regForm.setAdharDetailDTO(bd.getAadharDetailsByConstTxnNum(partyId));
					}
					File file = new File(regForm.getAdharDetailDTO().getRESIDENT_PHOTO_PATH());
					FileInputStream fis = new FileInputStream(file);
					// create FileInputStream which obtains input bytes from a
					// file in a file system
					// FileInputStream is meant for reading streams of raw bytes
					// such as image data. For reading streams of characters,
					// consider using FileReader.
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					try {
						for (int readNum; (readNum = fis.read(buf)) != -1;) {
							// Writes to this byte array output stream
							bos.write(buf, 0, readNum);
						}
					} catch (IOException ex) {
						// Logger.getLogger(ConvertImage.class.getName()).log(Level.SEVERE,
						// null, ex);
					}
					byte[] bytes = bos.toByteArray();
					BASE64Encoder encoder = new BASE64Encoder();
					String base64Encoded = encoder.encode(bytes);
					logger.debug(" resident image encoded " + base64Encoded);
					request.setAttribute("base64Encoded", base64Encoded);
					request.setAttribute("reginit", regForm);
					eForm.setRegInitForm(regForm);
					forwardJsp = "aadhaPartyView";
				}
				// added for aadhar EKYC by ankit ---- end
				if ("holdComplianceButton".equalsIgnoreCase(actionName)) {
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					if (deedInstArray != null && deedInstArray.length > 0) {
						request.setAttribute("deedId", deedInstArray[0].trim());
						request.setAttribute("instId", deedInstArray[1].trim());
						formDTO.setActionName("");
						formDTO.setUploadAction("");
						formDTO.setHoldpress("S");
						forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
					}
				}
				if ("holdComplianceButtonDenial".equalsIgnoreCase(actionName)) {
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					if (deedInstArray != null && deedInstArray.length > 0) {
						request.setAttribute("deedId", deedInstArray[0].trim());
						request.setAttribute("instId", deedInstArray[1].trim());
						formDTO.setActionName("");
						formDTO.setUploadAction("");
						formDTO.setHoldpress("D");
						forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
					}
				}
				if ("holdComplianceButtonInvalid".equalsIgnoreCase(actionName)) {
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					if (deedInstArray != null && deedInstArray.length > 0) {
						request.setAttribute("deedId", deedInstArray[0].trim());
						request.setAttribute("instId", deedInstArray[1].trim());
						formDTO.setActionName("");
						formDTO.setUploadAction("");
						formDTO.setHoldpress("I");
						forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
					}
				}
				if ("IMPOUND_ACTION_HOLD".equalsIgnoreCase(actionName)) {
					formDTO.setHoldpress("PS");
					formDTO.setActionName("");
					formDTO.setUploadAction("");
					forwardJsp = RegCompCheckerConstant.PROCEED_TO_PAYMENT;
				}
				if ("IMPOUND_ACTION_HOLD_PH".equalsIgnoreCase(actionName)) {
					formDTO.setHoldpress("PH");
					formDTO.setActionName("");
					formDTO.setUploadAction("");
					forwardJsp = RegCompCheckerConstant.PROCEED_TO_PAYMENT;
				}
				if (RegCompCheckerConstant.RESET_ACTION.equalsIgnoreCase(actionName)) {
					formDTO.setRegInitNumber("");
					eForm.setRegDTO(new RegCompCheckerDTO());
					forwardJsp = RegCompCheckerConstant.APPLICATION_SEARCH;
				}
				// To display property details
				if (RegCompCheckerConstant.REG_INIT_PROP_DETAILS_ACTION.equalsIgnoreCase(actionName)) {
					regForm.setClrRcmsFlag(""); //commented for stqc build
					String propertyId = "";
					propertyId = request.getParameter("key");
					String id = request.getParameter("id");
					// logger.debug("<____---id"+id);
					String propTypeID = "";
					propTypeID = commonBo.getPropertyTypeID(propertyId);
					regForm.setPropertyTypeID(propTypeID);
					if (regForm.getPropertyTypeID().equalsIgnoreCase("3")) {
						String clrRcmsFlag = "N";
						clrRcmsFlag = commonBo.getClrRCMSFlag(propertyId); //commented for stqc build
						if (clrRcmsFlag.equalsIgnoreCase("y")) {
							regForm.setClrRcmsFlag(clrRcmsFlag);
						}
					}
					String regInit = null;
					if (id.equals("id")) {
						regInit = formDTO.getRegInitNumber();
					} else {
						regInit = bd.getInitiationNumber(id);
					}
					regForm.setPropertyId(propertyId);//added by ankit for GIS work
					String isUpdateID  = request.getParameter("UpdateGISID");
					if("true".equalsIgnoreCase(isUpdateID)){
						logger.info("isUpdateID - "+isUpdateID);
						regForm.setGisReferenceKeyChecker(request.getParameter("gisId"));
						regForm.getRegcompletDto().setGisReferenceKey(request.getParameter("gisIdOld"));
						boolean isGISIDExist = commonBo.checkGISDuplicate(regForm.getGisReferenceKeyChecker(),regForm.getPropertyId());
						if(isGISIDExist){
							request.setAttribute(RegCompCheckerConstant.SUCCESS_MSG, RegInitConstant.gis_duplicate_error);
						}else{
									boolean updateDetails = commonBo.updateGisId(regInit,userId,regForm);
							
									if (updateDetails) {
										regForm.setGisReferenceKeyChecker("");
										logger.debug("RegCompCheckerAction--Data successfully modified");
										if ("en".equalsIgnoreCase(language)) {
											request.setAttribute(RegCompCheckerConstant.SUCCESS_MSG, "Data Successfully Modified");
										} else {
											request.setAttribute(RegCompCheckerConstant.SUCCESS_MSG, "डेटा सफलतापूर्वक संशोधित किया गया है |");
										}								
									} else {
										logger.debug("RegCompCheckerAction--Data Not Modified");
										if ("en".equalsIgnoreCase(language)) {
											request.setAttribute(RegCompCheckerConstant.SUCCESS_MSG, "Data Modification Failed");
										} else {
											request.setAttribute(RegCompCheckerConstant.SUCCESS_MSG, "डाटा संशोधन में विफल रहा है |");
										}
									}
						}
						
						
					}else{
								regForm.setGisReferenceKeyChecker("");
					}
					String[] deedInstArray = bd.getDeedInstId(regInit);
					String flags[] = commonBo.getInstrumentFlag(deedInstArray[1].trim());
					if (flags != null && flags[0] != null && flags[1] != null && flags[2] != null) {
						if (flags[2].trim().equalsIgnoreCase("Y")) {
							regForm.setCommonDeed(1);
						} else {
							regForm.setCommonDeed(0);
						}
						regForm.setPvReqFlag(flags[1].trim());
						regForm.setPropReqFlag(flags[0].trim());
					} else {
						regForm.setCommonDeed(0);
						regForm.setPvReqFlag("");
						regForm.setPropReqFlag("");
					}
					if (request.getParameter("page") != null) {
						request.setAttribute("page", "propHistory");
					}
					logger.debug("VIEW PROP DETAILS");
					// logger.debug("PROPERTY ID"+propertyId);
					if (regForm.getPvReqFlag().equalsIgnoreCase("Y")) {
						String regNumber = "";
						if (regInit.length() == 12) {
							regNumber = regInit;
						} else {
							regNumber = "0" + regInit;
						}
						regForm.setMapPropertyTransPartyDisp(commonBo.getPropertyDetsForViewConfirm(regNumber, propertyId, language));
						request.setAttribute("reginit", regForm);
						// Added by Rakesh for CLR Property View : Start
						// String propertyId=request.getParameter("key");
						System.out.println("PropID:" + request.getParameter("key"));
						ArrayList propdet = commonBo.getPropertyTypeIdAndClrFlag(propertyId);
						String propertyTypeId = "";
						String clrFlag = "";
						for (int i = 0; i < propdet.size(); i++) {
							ArrayList subList = (ArrayList) propdet.get(i);
							propertyTypeId = (String) subList.get(0);
							clrFlag = (String) subList.get(1);
						}
						if (clrFlag != null && propertyTypeId != null) {
							if (clrFlag.equalsIgnoreCase("Y") && propertyTypeId.equalsIgnoreCase("3")) {
								/*
								 * ArrayList khasraDetails=
								 * commonBo.getPropDetlsForDisplayClr
								 * (propertyId);
								 * 
								 * 
								 * ArrayList subList; for (int i = 0; i <
								 * khasraDetails.size(); i++) { subList =
								 * (ArrayList) khasraDetails.get(i);
								 * regForm.setClrFlg(subList.get(1).toString());
								 * regForm
								 * .getRegcompletDto().setRicircle(subList
								 * .get(1).toString());
								 * regForm.getRegcompletDto(
								 * ).setClrkhasraNo(subList.get(2).toString());
								 * regForm
								 * .getRegcompletDto().setClrKhasraArea(subList
								 * .get(3).toString());
								 * regForm.getRegcompletDto(
								 * ).setNorthKhasraNo(subList
								 * .get(4).toString());
								 * regForm.getRegcompletDto(
								 * ).setSouthKhasraNo(subList
								 * .get(5).toString());
								 * regForm.getRegcompletDto(
								 * ).setEastKhasraNo(subList.get(6).toString());
								 * regForm
								 * .getRegcompletDto().setWestKhasraNo(subList
								 * .get(7).toString());
								 * regForm.getRegcompletDto(
								 * ).setMapPathClr(subList.get(8).toString());
								 * regForm
								 * .getRegcompletDto().setKhasraPathClr(subList
								 * .get(9).toString()); } //clr party owner
								 * details display: Start ArrayList
								 * partyDetails=
								 * commonBo.getPartyDetlsForDisplayClr
								 * (regForm.getRegcompletDto
								 * ().getClrkhasraNo(),propertyId);
								 * regForm.getRegcompletDto
								 * ().setClrOwnerArray(partyDetails);
								 * ArrayList<RegCompletDTO> paramList1 = new
								 * ArrayList<RegCompletDTO>();
								 * 
								 * 
								 * 
								 * RegCompletDTO paramDto1 ; ArrayList subList2;
								 * for (int i = 0; i < partyDetails.size(); i++)
								 * { //row_list = (RegCompletDTO)
								 * ownerDetails.get(i); subList2 = (ArrayList)
								 * partyDetails.get(i); //Owner ow = (Owner)
								 * partyDetails.get(i); paramDto1 = new
								 * RegCompletDTO();
								 * paramDto1.setPartyDetails(subList2
								 * .get(0).toString());
								 * paramDto1.setCasteOfParty
								 * (subList2.get(1).toString());
								 * paramDto1.setShareOfParty
								 * (subList2.get(2).toString());
								 * 
								 * paramList1.add(paramDto1);
								 * 
								 * } regForm.setClrOwnerArray(paramList1); //clr
								 * party owner details display: Start
								 */
								forwardJsp = RegCompCheckerConstant.PROPERTY_VIEW_DETAILS_CLR;
							} else {
								forwardJsp = RegCompCheckerConstant.PROPERTY_VIEW_DETAILS;
							}
						} else {
							forwardJsp = RegCompCheckerConstant.PROPERTY_VIEW_DETAILS;
						}
						// forwardJsp=RegCompCheckerConstant.PROPERTY_VIEW_DETAILS;
					}
					// Added by Rakesh for CLR Property View: End
					else {
						String regNumber = "";
						if (regInit.length() == 12) {
							regNumber = regInit;
						} else {
							regNumber = "0" + regInit;
						}
						regForm.setMapPropertyTransPartyDisp(commonBo.getPropertyDetsForViewConfirm(regNumber, propertyId, language));
						request.setAttribute("reginit", regForm);
						// Added by Rakesh for CLR Property View : Start
						// String propertyId=request.getParameter("key");
						System.out.println("PropID:" + request.getParameter("key"));
						ArrayList propdet = commonBo.getPropertyTypeIdAndClrFlag(propertyId);
						String propertyTypeId = "";
						String clrFlag = "";
						for (int i = 0; i < propdet.size(); i++) {
							ArrayList subList = (ArrayList) propdet.get(i);
							propertyTypeId = (String) subList.get(0);
							clrFlag = (String) subList.get(1);
						}
						if (clrFlag != null && propertyTypeId != null) {
							if (clrFlag.equalsIgnoreCase("Y") && propertyTypeId.equalsIgnoreCase("3")) {
								/*
								 * ArrayList khasraDetails=
								 * commonBo.getPropDetlsForDisplayClr
								 * (propertyId);
								 * 
								 * 
								 * ArrayList subList; for (int i = 0; i <
								 * khasraDetails.size(); i++) { subList =
								 * (ArrayList) khasraDetails.get(i);
								 * regForm.setClrFlg(subList.get(1).toString());
								 * regForm
								 * .getRegcompletDto().setRicircle(subList
								 * .get(1).toString());
								 * regForm.getRegcompletDto(
								 * ).setClrkhasraNo(subList.get(2).toString());
								 * regForm
								 * .getRegcompletDto().setClrKhasraArea(subList
								 * .get(3).toString());
								 * regForm.getRegcompletDto(
								 * ).setNorthKhasraNo(subList
								 * .get(4).toString());
								 * regForm.getRegcompletDto(
								 * ).setSouthKhasraNo(subList
								 * .get(5).toString());
								 * regForm.getRegcompletDto(
								 * ).setEastKhasraNo(subList.get(6).toString());
								 * regForm
								 * .getRegcompletDto().setWestKhasraNo(subList
								 * .get(7).toString());
								 * regForm.getRegcompletDto(
								 * ).setMapPathClr(subList.get(8).toString());
								 * regForm
								 * .getRegcompletDto().setKhasraPathClr(subList
								 * .get(9).toString()); } //clr party owner
								 * details display: Start ArrayList
								 * partyDetails=
								 * commonBo.getPartyDetlsForDisplayClr
								 * (regForm.getRegcompletDto
								 * ().getClrkhasraNo(),propertyId);
								 * regForm.getRegcompletDto
								 * ().setClrOwnerArray(partyDetails);
								 * ArrayList<RegCompletDTO> paramList1 = new
								 * ArrayList<RegCompletDTO>();
								 * 
								 * 
								 * 
								 * RegCompletDTO paramDto1 ; ArrayList subList2;
								 * for (int i = 0; i < partyDetails.size(); i++)
								 * { //row_list = (RegCompletDTO)
								 * ownerDetails.get(i); subList2 = (ArrayList)
								 * partyDetails.get(i); //Owner ow = (Owner)
								 * partyDetails.get(i); paramDto1 = new
								 * RegCompletDTO();
								 * paramDto1.setPartyDetails(subList2
								 * .get(0).toString());
								 * paramDto1.setCasteOfParty
								 * (subList2.get(1).toString());
								 * paramDto1.setShareOfParty
								 * (subList2.get(2).toString());
								 * 
								 * paramList1.add(paramDto1);
								 * 
								 * } regForm.setClrOwnerArray(paramList1); //clr
								 * party owner details display: Start
								 */
								forwardJsp = "propertyViewNonPVClr";
							} else {
								forwardJsp = "propertyViewNonPV";
							}
						} else {
							forwardJsp = "propertyViewNonPV";
						}
						// Added by Rakesh for CLR Property View: End
						// forwardJsp="propertyViewNonPV";
					}
				}
				// to view and modify party details
				if (RegCompCheckerConstant.REG_INIT_PARTY_DETAILS_ACTION.equalsIgnoreCase(actionName)) {
					RegCommonBD comBD = new RegCommonBD();
					String partyId = request.getParameter("key");
					request.setAttribute("deedId", formDTO.getDeedID());
					request.setAttribute("instId", formDTO.getInstID());
					request.setAttribute("roleId", request.getParameter("role"));
					formDTO.setRole((String) request.getParameter("role"));
					String roleIdNew = formDTO.getRole();
					if (partyId != null && roleIdNew != null) {
						if (roleIdNew.equalsIgnoreCase("50004")) {
							String poaPartyId = comBD.getPoaPartyTxnId(partyId);
							if (poaPartyId == null || poaPartyId.isEmpty()) {
								partyId = request.getParameter("key");
							} else {
								partyId = poaPartyId;
							}
						}
					}
					// logger.debug("request deed ---->"+request.getAttribute("deedId"));
					// logger.debug("request role ---->"+request.getAttribute("roleId"));
					// logger.debug("PARTY ID"+partyId);
					if (!(formDTO.isModify())) {
						request.setAttribute(RegCompCheckerConstant.ISADJ, "false");
					}
					String flags[] = commonBo.getInstrumentFlag(String.valueOf(formDTO.getInstID()));
					if (flags != null && flags[0] != null && flags[1] != null && flags[2] != null) {
						if (flags[2].trim().equalsIgnoreCase("Y")) {
							regForm.setCommonDeed(1);
						} else {
							regForm.setCommonDeed(0);
						}
						regForm.setPvReqFlag(flags[1].trim());
						regForm.setPropReqFlag(flags[0].trim());
					} else {
						regForm.setCommonDeed(0);
						regForm.setPvReqFlag("");
						regForm.setPropReqFlag("");
					}
					String regNumber = "";
					if (formDTO.getRegInitNumber().length() == 12) {
						regNumber = formDTO.getRegInitNumber();
					} else {
						regNumber = "0" + formDTO.getRegInitNumber();
					}
					regForm.setMapTransactingPartiesDisp(bd.getPartyDetsForViewConfirm(regNumber, partyId, language, formDTO.getDeedID(), formDTO.getInstID(), eForm.getPropReqFlag()));
					request.setAttribute("reginit", regForm);
					regForm.setConfirmationFlag("01");
					forwardJsp = RegCompCheckerConstant.PARTY_VIEW_DETAILS;
				}
				if (RegCompCheckerConstant.DISPLAY_EXTRA.equalsIgnoreCase(actionName)) {
					// eForm = bd.getExtraDetls(eForm,language);
					request.setAttribute("deedId", formDTO.getDeedID());
					regForm.setHidnRegTxnId(formDTO.getRegInitNumber());
					regForm.setDeedID(formDTO.getDeedID());
					regForm.setInstID(formDTO.getInstID());
					commonBo.getExtraDetls(regForm, language);
					request.setAttribute("deedId", regForm.getDeedID());
					request.setAttribute("instId", formDTO.getInstID());
					request.setAttribute("reginit", regForm);
					// logger.debug("request deed ---->"+request.getAttribute("deedId"));
					// regForm.setCallingAction("regInit.do");
					forwardJsp = RegCompCheckerConstant.DISPLAY_EXTRA_DETLS_JSP;
				}
				if (("displayConsenter").equalsIgnoreCase(actionName)) {
					/*
					 * if (request.getParameter("fromPage") != null &&
					 * request.getParameter("fromPage").toString()
					 * .equalsIgnoreCase("consenterDetls")) {
					 */
					regForm.setConfirmationFlag("00");
					/*
					 * } else { regForm.setConfirmationFlag("01"); }
					 */
					regForm.setPartyModifyFlag(0);
					regForm.setHidnRegTxnId(formDTO.getRegInitNumber());
					String[] param = commonBo.getConsenterFlag(formDTO.getRegInitNumber());
					regForm.setConsenterWithConsidFlag(param[0]);
					commonBo.openConsenterView(request, regForm, language);
					request.setAttribute("reginit", regForm);
					eForm.setRegInitForm(regForm);
					forwardJsp = "displayConsenterDetls";
				}
				if (("displayConsenterDuties").equalsIgnoreCase(actionName)) {
					regForm.setHidnRegTxnId(formDTO.getRegInitNumber());
					commonBo.getConsenterDutiesView(regForm, language);
					request.setAttribute("reginit", regForm);
					forwardJsp = "displayConsenterDuties";
				}
				if (RegCompCheckerConstant.DISPLAY_PARTICULARS_OF_TRANSACTION.equalsIgnoreCase(actionName)) {
					logger.debug("--------particulars of transaction------");
					String particularId = request.getParameter("key");
					formDTO.setParticularTxnId(particularId);
					bd.getParticularDetails(formDTO, eForm);
					request.setAttribute("deedId", regForm.getDeedID());
					// logger.debug("request deed ---->"+request.getAttribute("deedId"));
					forwardJsp = "displayParticularDetls";
				}
				if (RegCompCheckerConstant.DISPLAY_DUTY_BREAKUP.equalsIgnoreCase(actionName)) {
					regForm.setHidnRegTxnId(formDTO.getRegInitNumber());
					regForm.setDeedID(formDTO.getDeedID());
					regForm.setDuty_txn_id(eForm.getDuty_txn_id());
					String flags[] = commonBo.getInstrumentFlag(String.valueOf(formDTO.getInstID()));
					if (flags != null && flags[0] != null && flags[1] != null && flags[2] != null) {
						if (flags[2].trim().equalsIgnoreCase("Y")) {
							regForm.setCommonDeed(1);
						} else {
							regForm.setCommonDeed(0);
						}
						regForm.setPvReqFlag(flags[1].trim());
						regForm.setPropReqFlag(flags[0].trim());
					} else {
						regForm.setCommonDeed(0);
						regForm.setPvReqFlag("");
						regForm.setPropReqFlag("");
					}
					commonBo.getDutiesView(regForm, language);
					regForm.setTotalduty(formDTO.getTotalduty());
					regForm.setRegistrationFee(formDTO.getRegistrationFee());
					regForm.setExempReg(formDTO.getExempReg());
					regForm.setExempStamp(formDTO.getExempStamp());
					request.setAttribute("reginit", regForm);
					forwardJsp = "dutyView";
				}
				if (RegCompCheckerConstant.VALIDATE_DEED_DOC.equalsIgnoreCase(actionName)) {
					logger.debug("*************************validate***********************");
					RegCompMkrBO boMaker = new RegCompMkrBO();
					String flag = boMaker.validateDeedDoc(formDTO.getDeedDraftId());
					logger.debug("flag---------->" + flag);
					if (flag.equals("0"))
						formDTO.setDeedStat("3");
					else if (flag.equals("1"))
						formDTO.setDeedStat("0");
					else if (flag.equals("3"))
						formDTO.setDeedStat("1");
					else
						formDTO.setDeedStat("2");
					formDTO.setDeedDoc("N");
					formDTO.setPdfFlag("N");
					request.setAttribute("deedId", formDTO.getDeedID());
					request.setAttribute("instId", formDTO.getInstID());
					// request.setAttribute("reginit", regForm);
					forwardJsp = formDTO.getForwardPage();
				}
				if (RegCompCheckerConstant.CHANGE_DEED_DOC.equalsIgnoreCase(actionName)) {
					logger.debug("change deed doc");
					formDTO.setDeedStat("4");
					formDTO.setDeedDoc("N");
					formDTO.setPdfFlag("N");
					formDTO.setDeedDocName("");
					formDTO.setDeedDocPath("");
					formDTO.setDeedDraftId("");
					request.setAttribute("deedId", formDTO.getDeedID());
					request.setAttribute("instId", formDTO.getInstID());
					// request.setAttribute("reginit", regForm);
					forwardJsp = formDTO.getForwardPage();
				}
				if (RegCompCheckerConstant.CONSUME_DEED_DOC.equalsIgnoreCase(actionName)) {
					logger.debug("consume deed doc");
					PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					String path = pr.getValue("pdf.location");
					RegCompMkrBO boMaker = new RegCompMkrBO();
					String flag = boMaker.validateDeedDoc(formDTO.getDeedDraftId());
					logger.debug("flag---------->" + flag);
					if (flag.equals("0"))
						formDTO.setDeedStat("3");
					else if (flag.equals("1"))
						formDTO.setDeedStat("0");
					else if (flag.equals("3"))
						formDTO.setDeedStat("1");
					else {
						boolean flag1 = boMaker.checkConsumptionOfDeedDoc(formDTO.getDeedDraftId(), RegCompConstant.FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompConstant.UPLOAD_PATH_DEED_DOC + RegCompConstant.FILE_NAME_DEED_DOC, RegCompConstant.FILE_NAME_DEED_DOC, formDTO.getRegInitNumber());
						// if(!flag1)
						// {
						// consumed
						// formDTO.setDeedStat("1");
						// }
						// else
						// {
						formDTO.setDeedStat("5");
						formDTO.setDeedDoc("Y");
						if (formDTO.getPdfFlag().equals("Y")) {
							String filename = formDTO.getDeedDocPath();
							byte[] contents = null;
							contents = DMSUtility.getDocumentBytes(filename);
							downloadDocument(response, contents, filename);
						} else {
							DeedDraftBD deedBD = new DeedDraftBD();
							//byte[] propDetlsBytes = deedBD.propDetlsPdf(regForm.getHidnRegTxnId()); //added by ankit for prop val pdf 
							//byte arr[] = deedBD.generateDeedPDF(path, formDTO.getDeedDraftId(), response,1,propDetlsBytes); //changes by ankit for prop val pdf 
							byte arr[];
							String flags[] = commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));
							String propReq = flags[0];
							String propValReq = flags[1];
							String commonDeed = flags[2];
							if (propReq.equalsIgnoreCase("Y") && (propValReq.equalsIgnoreCase("Y") || propValReq.equalsIgnoreCase("N")) && commonDeed.equalsIgnoreCase("N")) {
								arr = deedBD.newDeedPDF(regForm, path, regForm.getDeedDraftId(), response, 1, language);
							} else {
								arr = deedBD.generateDeedPDFOLD(path, regForm.getDeedDraftId(), response, 1); //changed by ankit for prop val pdf 
							}
							filePath = uploadFile(formDTO.getRegInitNumber(), arr);
							formDTO.setDeedDocPath(RegCompConstant.FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompConstant.UPLOAD_PATH_DEED_DOC + RegCompConstant.FILE_NAME_DEED_DOC);
							formDTO.setDeedDocName(RegCompConstant.FILE_NAME_DEED_DOC);
							formDTO.setPdfFlag("Y");
						}
						// }
					}
					request.setAttribute("deedId", formDTO.getDeedID());
					request.setAttribute("instId", formDTO.getInstID());
					forwardJsp = formDTO.getForwardPage();
				}
				if (RegCompCheckerConstant.VIEW_DEED_DOC.equalsIgnoreCase(actionName)) {
					PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					String path = pr.getValue("pdf.location");
					if (formDTO.getPdfFlag().equals("Y")) {
						String filename = formDTO.getDeedDocPath();
						byte[] contents = null;
						contents = DMSUtility.getDocumentBytes(filename);
						downloadDocument(response, contents, filename);
					} else {
						DeedDraftBD deedBD = new DeedDraftBD();
						//byte[] propDetlsBytes = deedBD.propDetlsPdf(regForm.getHidnRegTxnId()); //added by ankit for prop val pdf
						//byte arr[] = deedBD.generateDeedPDF(path, formDTO.getDeedDraftId(), response,0,propDetlsBytes);  // pdf will open //changes by ankit for prop val pdf
						/*String path = RegCompConstant.FILE_UPLOAD_PATH+formDTO.getRegInitNumber()+RegCompConstant.UPLOAD_PATH_DEED_DOC;*/
						byte arr[];
						String flags[] = commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));
						String propReq = flags[0];
						String propValReq = flags[1];
						String commonDeed = flags[2];
						if (propReq.equalsIgnoreCase("Y") && (propValReq.equalsIgnoreCase("Y") || propValReq.equalsIgnoreCase("N")) && commonDeed.equalsIgnoreCase("N")) {
							arr = deedBD.newDeedPDF(regForm, path, regForm.getDeedDraftId(), response, 0, language);
						} else {
							arr = deedBD.generateDeedPDFOLD(path, regForm.getDeedDraftId(), response, 0); //changed by ankit for prop val pdf 
						}
						//added by ankit for prop val pdf - end
						filePath = uploadFile(formDTO.getRegInitNumber(), arr);
						formDTO.setDeedDocPath(RegCompConstant.FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompConstant.UPLOAD_PATH_DEED_DOC + RegCompConstant.FILE_NAME_DEED_DOC);
						formDTO.setDeedDocName(RegCompConstant.FILE_NAME_DEED_DOC);
						formDTO.setPdfFlag("Y");
					}
					request.setAttribute("deedId", formDTO.getDeedID());
					request.setAttribute("instId", formDTO.getInstID());
					forwardJsp = formDTO.getForwardPage();
				}
				// to modify party details
				if (RegCompCheckerConstant.REG_INIT_PARTY_MODIFY_ACTION.equalsIgnoreCase(actionName)) {
					request.setAttribute("deedId", formDTO.getDeedID());
					request.setAttribute("roleId", formDTO.getRole());
					// logger.debug("request role ---->"+formDTO.getRole());
					forwardJsp = RegCompCheckerConstant.MODIFY_PARTY_DETAILS;
				}
				if (RegCompCheckerConstant.DISPLAY_PARTY_DETLS_NPV.equalsIgnoreCase(actionName)) {
					String propertyId = request.getParameter("key");
					regForm.setPropertyId(propertyId);
					regForm.setMapPropertyTransPartyDisp(commonBo.getPropertyDetsForViewConfirmNonProp(regForm.getHidnRegTxnId(), propertyId, language));
					// regForm.setConfirmationFlag("01");
					request.setAttribute("reginit", regForm);
					forwardJsp = RegCompCheckerConstant.DISPLAY_PROP_DETLS_NPV;
				}
				if (RegCompCheckerConstant.REG_INIT_PARTY_MODIFY_SAVE_ACTION.equalsIgnoreCase(actionName)) {
					logger.debug("<----Inside Modify Save Action--->");
					// logger.debug("Party_TXN ID"+formDTO.getPartyTxnId());
					boolean saveDetails = bd.savePartyDetails(formDTO);
					if (saveDetails) {
						logger.debug("RegCompCheckerAction--Data successfully modified");
						if ("en".equalsIgnoreCase(language)) {
							request.setAttribute(RegCompCheckerConstant.SUCCESS_MSG, "Data Successfully Modified");
						} else {
							request.setAttribute(RegCompCheckerConstant.SUCCESS_MSG, "डेटा सफलतापूर्वक संशोधित किया गया है |");
						}
						forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
					} else {
						logger.debug("RegCompCheckerAction--Data Not Modified");
						if ("en".equalsIgnoreCase(language)) {
							request.setAttribute(RegCompCheckerConstant.SUCCESS_MSG, "Data Modification Failed");
						} else {
							request.setAttribute(RegCompCheckerConstant.SUCCESS_MSG, "डाटा संशोधन में विफल रहा है |");
						}
						forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
					}
				}
				if (RegCompCheckerConstant.REG_INIT_MODIFY_MARKET_VALUE.equalsIgnoreCase(actionName)) {
					logger.debug("RegCompCheckerAction ---Modify Market Vlaue Action");
					request.setAttribute(RegCompCheckerConstant.MODIFY, "modifyMarketValue");
					request.setAttribute("deedId", formDTO.getDeedID());
					request.setAttribute("instId", formDTO.getInstID());
					forwardJsp = RegCompCheckerConstant.REG_INIT_PROP_DETAILS;
				}
				if (RegCompCheckerConstant.NEXT_TO_LINKING_PAGE.equalsIgnoreCase(actionName)) {
					ArrayList propDetls = new ArrayList();
					if (eForm.getRegDTO().getPropDetails() != null) {
						String[] propDetails = formDTO.getPropDetails().split(",");
						for (int i = 0; i < propDetails.length; i++) {
							String[] prop_market = propDetails[i].split("_");
							RegCompCheckerDTO gDTO = new RegCompCheckerDTO();
							// logger.debug("PROPERTY------>"+prop_market[0]);
							// logger.debug("MARKET VALUE---->"+prop_market[1]);
							gDTO.setPropertyId(prop_market[0]);
							if (prop_market.length == 1)
								gDTO.setMarketValueTrns("0");
							else
								gDTO.setMarketValueTrns(prop_market[1]);
							propDetls.add(gDTO);
						}
					}
					// filePath = uploadFile(formDTO.getRegInitNumber(),
					// formDTO.getSupportingDocContents(),RegCompCheckerConstant.UPLOAD_PATH_SUPP_DOC,
					// RegCompCheckerConstant.FILE_NAME_SUPP_DOC);
					// if(filePath != null)
					// {
					// formDTO.setSupportingDocPath(filePath);
					boolean modify = bd.modifyMV(propDetls, formDTO, userId, tDate);
					boolean modify1 = true;
					if ("Y".equalsIgnoreCase(eForm.getConsnterDuteisFlag())) {
						modify1 = bd.upadteConsenterDuties(eForm);
					}
					if (modify && modify1) {
						logger.debug("DATA MODIFIED");
						ArrayList linkingDetails = new ArrayList();
						ArrayList dutyAlreadyPaidList = bd.getAlreadyPaidDuties(formDTO.getRegInitNumber());
						if (dutyAlreadyPaidList.size() == 0) {
							logger.debug("SKIP LINKING");
							boolean flag = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), RegCompCheckerConstant.WITNESS_DETAILS_STATUS, cdate, userId);// linking Page has
							// to be skipped
							ArrayList witnessDetailsList = bd.getWitnessDetails(formDTO.getRegInitNumber());
							if (witnessDetailsList != null) {
								eForm.setWitnessDetailsList(witnessDetailsList);
							} else {
								logger.debug("WITNESS LIST EMPTY");
							}
							ArrayList consenterList = bd.getConsenterDetails(formDTO.getRegInitNumber());
							if (consenterList.size() != 0) {
								eForm.setConsenterDetailsList(consenterList);
							}
							forwardJsp = RegCompCheckerConstant.WITNESS_VIEW;
						} else {
							boolean flag = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), RegCompCheckerConstant.LINKING_PAYMENT_STATUS, cdate, userId);
							linkingDetails = bd.getLinkingDuties(formDTO.getRegInitNumber(), eForm);
							if (linkingDetails.size() != 0) {
								eForm.setLinkingDutiesDispList(linkingDetails);
								forwardJsp = RegCompCheckerConstant.LINKING_PAYMENT_PAGE;
							} else {
								messages.add("ERRORMSG", new ActionMessage("no.linking.record.found"));
								saveMessages(request, messages);
								eForm.setCheck("L");
								eForm.setLinkingDutiesDisp(new ArrayList());
								request.setAttribute("checkRegID", eForm);
								forwardJsp = RegCompCheckerConstant.LINKING_PAYMENT_PAGE;
							}
						}
						formDTO.setPhotoLst(new ArrayList());
					} else {
						session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
						forwardJsp = "failureChecker";
					}
					/*
					 * } else { messages.add("ERRORMSG", new ActionMessage(
					 * "upload.failed")); saveMessages(request, messages);
					 * eForm.setCheck("U"); formDTO.setSupportingDocName("");
					 * formDTO.setSupportingDocPath("");
					 * request.setAttribute("checkRegID", eForm); forwardJsp =
					 * RegCompCheckerConstant.REG_INIT_PROP_DETAILS; }
					 */
					// HashMap linkingDetails =
					// bd.getLinkingDuties(formDTO.getRegInitNumber(), eForm);
				}
				if (RegCompCheckerConstant.LINKING_PROPERTY_NPV.equalsIgnoreCase(actionName)) {
					// filePath = uploadFile(formDTO.getRegInitNumber(),
					// formDTO.getSupportingDocContents(),RegCompCheckerConstant.UPLOAD_PATH_SUPP_DOC,
					// RegCompCheckerConstant.FILE_NAME_SUPP_DOC);
					// if(filePath != null)
					// {
					// formDTO.setSupportingDocPath(filePath);
					boolean modify = bd.modifyMvNPV(formDTO, userId, tDate);
					boolean modify1 = true;
					if ("Y".equalsIgnoreCase(eForm.getConsnterDuteisFlag())) {
						modify1 = bd.upadteConsenterDuties(eForm);
					}
					if (modify && modify1) {
						logger.debug("DATA MODIFIED");
						ArrayList linkingDetails = new ArrayList();
						ArrayList dutyAlreadyPaidList = bd.getAlreadyPaidDuties(formDTO.getRegInitNumber());
						if (dutyAlreadyPaidList.size() == 0) {
							logger.debug("SKIP LINKING");
							boolean flag = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), RegCompCheckerConstant.WITNESS_DETAILS_STATUS, cdate, userId);// linking Page has
							// to be skipped
							ArrayList witnessDetailsList = bd.getWitnessDetails(formDTO.getRegInitNumber());
							if (witnessDetailsList != null) {
								eForm.setWitnessDetailsList(witnessDetailsList);
							} else {
								logger.debug("WITNESS LIST EMPTY");
							}
							ArrayList consenterList = bd.getConsenterDetails(formDTO.getRegInitNumber());
							if (consenterList.size() != 0) {
								eForm.setConsenterDetailsList(consenterList);
							}
							forwardJsp = RegCompCheckerConstant.WITNESS_VIEW;
						} else {
							boolean flag = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), RegCompCheckerConstant.LINKING_PAYMENT_STATUS, cdate, userId);
							linkingDetails = bd.getLinkingDuties(formDTO.getRegInitNumber(), eForm);
							if (linkingDetails.size() != 0) {
								eForm.setLinkingDutiesDispList(linkingDetails);
								forwardJsp = RegCompCheckerConstant.LINKING_PAYMENT_PAGE;
							} else {
								messages.add("ERRORMSG", new ActionMessage("no.linking.record.found"));
								saveMessages(request, messages);
								eForm.setCheck("L");
								eForm.setLinkingDutiesDisp(new ArrayList());
								request.setAttribute("checkRegID", eForm);
								forwardJsp = RegCompCheckerConstant.LINKING_PAYMENT_PAGE;
							}
						}
						formDTO.setPhotoLst(new ArrayList());
					} else {
						session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
						forwardJsp = "failureChecker";
					}
					// }
					/*
					 * else { messages.add("ERRORMSG", new ActionMessage(
					 * "upload.failed")); saveMessages(request, messages);
					 * eForm.setCheck("U"); formDTO.setSupportingDocName("");
					 * formDTO.setSupportingDocPath("");
					 * request.setAttribute("checkRegID", eForm); forwardJsp =
					 * RegCompCheckerConstant.REG_INIT_PROP_DETAILS; }
					 */
				}
				if (RegCompCheckerConstant.NEXT_TO_LINKING_PROPERTY_NO_MODIFY.equalsIgnoreCase(actionName)) {
					boolean insert = bd.insertDutiesNoModify(eForm.getDutyList(), formDTO.getRegInitNumber(), userId, tDate, formDTO);
					boolean insert1 = true;
					if ("Y".equalsIgnoreCase(eForm.getConsnterDuteisFlag())) {
						insert1 = bd.upadteConsenterDuties(eForm);
					}
					logger.debug("DATA MODIFIED");
					if (insert && insert1) {
						ArrayList dutyAlreadyPaidList = bd.getAlreadyPaidDuties(formDTO.getRegInitNumber());
						if (dutyAlreadyPaidList.size() == 0) {
							logger.debug("SKIP LINKING");
							boolean flag = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), RegCompCheckerConstant.WITNESS_DETAILS_STATUS, cdate, userId);// linking Page has
							// to be skipped
							if (flag) {
								ArrayList witnessDetailsList = bd.getWitnessDetails(formDTO.getRegInitNumber());
								if (witnessDetailsList != null) {
									eForm.setWitnessDetailsList(witnessDetailsList);
								} else {
									logger.debug("WITNESS LIST EMPTY");
								}
								ArrayList consenterList = bd.getConsenterDetails(formDTO.getRegInitNumber());
								if (consenterList.size() != 0) {
									eForm.setConsenterDetailsList(consenterList);
								}
								forwardJsp = RegCompCheckerConstant.WITNESS_VIEW;
							} else {
								session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
								forwardJsp = "failureChecker";
							}
						} else {
							boolean flag = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), RegCompCheckerConstant.LINKING_PAYMENT_STATUS, cdate, userId);
							ArrayList linkingDetails = bd.getLinkingDuties(formDTO.getRegInitNumber(), eForm);
							if (linkingDetails.size() != 0) {
								eForm.setLinkingDutiesDispList(linkingDetails);
								forwardJsp = RegCompCheckerConstant.LINKING_PAYMENT_PAGE;
							} else {
								messages.add("ERRORMSG", new ActionMessage("no.linking.record.found"));
								saveMessages(request, messages);
								eForm.setCheck("L");
								eForm.setLinkingDutiesDisp(new ArrayList());
								request.setAttribute("checkRegID", eForm);
								forwardJsp = RegCompCheckerConstant.LINKING_PAYMENT_PAGE;
							}
						}
					} else {
						session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
						forwardJsp = "failureChecker";
					}
				}
				if (RegCompCheckerConstant.MODIFY_LINKING.equalsIgnoreCase(actionName)) {
					logger.debug("<---------MODIFY LIKING");
					formDTO.setActionName("LINK_PAYMENT_CHECKER");
					request.setAttribute("regCompChecker", formDTO);
					forwardJsp = RegCompCheckerConstant.REDIRCT_TO_MAKER;
				}
				/**
				 * VIEW WITNESS AND HOLD PAGE IN CASE DATA IS RETURNED FROM
				 * MAKER PART
				 */
				if (RegCompCheckerConstant.VIEW_WITNESS.equalsIgnoreCase(actionName))// after duty
				// linking-roopam
				{
					logger.debug("INSIDE CHECKER VIEW WITNESS ACTION_FROM_MAKER");
					eForm.setCheck("");
					boolean flag = bd.isLinking(formDTO.getRegInitNumber());
					ArrayList linkingDetails = new ArrayList();
					if (flag) {
						linkingDetails = bd.getFinalDutiesForPayment(formDTO.getRegInitNumber(), eForm);
					} else {
						linkingDetails = bd.getLinkingDuties(formDTO.getRegInitNumber(), eForm);
					}
					if (linkingDetails.size() != 0) {
						eForm.setLinkingDutiesDispList(linkingDetails);
						forwardJsp = RegCompCheckerConstant.LINKING_PAYMENT_PAGE;
					} else {
						messages.add("ERRORMSG", new ActionMessage("no.linking.record.found"));
						saveMessages(request, messages);
						eForm.setCheck("L");
						eForm.setLinkingDutiesDisp(new ArrayList());
						request.setAttribute("checkRegID", eForm);
						forwardJsp = RegCompCheckerConstant.LINKING_PAYMENT_PAGE;
					}
				}
				if (RegCompCheckerConstant.HOLD_FROM_MAKER.equalsIgnoreCase(actionName)) {
					logger.debug("on hold returned from maker action");
					if ("en".equalsIgnoreCase(language)) {
						request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "The Application has been put on hold Successfully. ");
					} else {
						request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "आवेदन सफलतापूर्वक होल्ड पर रख दिया गया है | ");
					}
					forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
				}
				// //////////////////////////////////////////////////////////////////
				// VIEW WITNESS AND HOLD IF NO DATA HAS BEEN MODIFIED
				if (RegCompCheckerConstant.NEXT_TO_VIEW_WITNESS.equalsIgnoreCase(actionName)) {
					boolean linkDetails = bd.getLinkDetails(formDTO.getRegInitNumber());
					if (!linkDetails) {
						boolean savePropPayLink = bd.saveLinkingPropertyPaymentDetails(formDTO.getRegInitNumber(), userId, cdate);
					}
					// logger.debug("netBal"+formDTO.getNetBal());
					boolean flag = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), RegCompCheckerConstant.WITNESS_DETAILS_STATUS, cdate, userId);
					// if(savePropPayLink)
					// {
					ArrayList witnessDetailsList = bd.getWitnessDetails(formDTO.getRegInitNumber());
					if (witnessDetailsList.size() != 0) {
						eForm.setWitnessDetailsList(witnessDetailsList);
					} else {
						logger.debug("WITNESS LIST EMPTY");
					}
					ArrayList consenterList = bd.getConsenterDetails(formDTO.getRegInitNumber());
					if (consenterList.size() != 0) {
						eForm.setConsenterDetailsList(consenterList);
					}
					forwardJsp = RegCompCheckerConstant.WITNESS_VIEW;
					// }
				}
				if (RegCompCheckerConstant.DELETE_WITNESS.equalsIgnoreCase(actionName)) {
					logger.debug("INSIDE ACTION----DELETE WITNESS");
					ArrayList witnessDetails = eForm.getWitnessDetailsList();
					String witnessTxnId = formDTO.getSelectedWitnessTxnNummber();
					boolean flag = bd.updateWitnessDetails(witnessTxnId, cdate, userId);
					if (flag) {
						Iterator itr = witnessDetails.iterator();
						while (itr.hasNext()) {
							RegCompCheckerDTO rDTO = (RegCompCheckerDTO) itr.next();
							if (rDTO.getWitnessTxnNummber().equals(witnessTxnId)) {
								witnessDetails.remove(rDTO);
								break;
							}
						}
						logger.debug("size of arrayList" + witnessDetails.size());
						eForm.setWitnessDetailsList(witnessDetails);
						forwardJsp = RegCompCheckerConstant.WITNESS_VIEW;
					} else {
						session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
						forwardJsp = "failureChecker";
					}
				}
				if (RegCompCheckerConstant.EDIT_WITNESS.equals(actionName)) {
					logger.debug("IN CHECKER ACTION----->EDIT_WITNESS");
					String witnessTxnId = formDTO.getSelectedWitnessTxnNummber();
					// logger.debug("IN CHECKER ACTION selected ID----->"+witnessTxnId);
					ArrayList witnessCompList = bd.getCompleteWitnessDetails(formDTO.getRegInitNumber(), witnessTxnId);
					Iterator itr = witnessCompList.iterator();
					while (itr.hasNext()) {
						RegCompCheckerDTO rrto = (RegCompCheckerDTO) itr.next();
						String gender = rrto.getWitGender();
						formDTO.setWitFirstName(rrto.getWitFirstName());
						formDTO.setWitMiddleName(rrto.getWitMiddleName());
						formDTO.setWitLastName(rrto.getWitLastName());
						formDTO.setWitAge(rrto.getWitAge());
						formDTO.setWitFatherName(rrto.getWitFatherName());
						formDTO.setWitMotherName(rrto.getWitMotherName());
						formDTO.setWitSpouseName(rrto.getWitSpouseName());
						formDTO.setWitNationality(rrto.getWitNationality());
						formDTO.setWitAddress(rrto.getWitAddress());
						formDTO.setWitPhoneNumber(rrto.getWitPhoneNumber());
						formDTO.setRelationshipWit(rrto.getRelationshipWit());
						if (gender.equalsIgnoreCase("M"))
							formDTO.setWitGender("Male");
						else if (gender.equalsIgnoreCase("F"))
							formDTO.setWitGender("Female");
						else {
							formDTO.setWitGender("Other");
						}
						eForm.setRelationshipList(bd.getRelationshipList(gender, language));
					}
					eForm.setWitnessCompDetailsLst(witnessCompList);
					// formDTO.setWitnessTxnNummber(witnessTxnId);
					forwardJsp = RegCompCheckerConstant.EDIT_WITNESS_PAGE;
				}
				if (RegCompCheckerConstant.SAVE_WITNESS_DETAILS.equalsIgnoreCase(actionName)) {
					boolean saveWitnessDetails = bd.updateAndSaveWitnessDetails(formDTO, formDTO.getSelectedWitnessTxnNummber(), cdate, userId);
					if (saveWitnessDetails) {
						logger.debug("Data Saved Successfully");
						ArrayList witnessDetails = eForm.getWitnessDetailsList();
						Iterator itr = witnessDetails.iterator();
						while (itr.hasNext()) {
							RegCompCheckerDTO rdto = (RegCompCheckerDTO) itr.next();
							if (rdto.getWitnessTxnNummber().equals(formDTO.getSelectedWitnessTxnNummber())) {
								logger.debug("Match found");
								rdto.setFnameTrns(formDTO.getWitFirstName());
								rdto.setLnameTrns(formDTO.getWitLastName());
								rdto.setAgeTrns(formDTO.getWitAge());
								rdto.setIndaddressTrns(formDTO.getWitAddress());
								rdto.setWitnessTxnNummber(formDTO.getWitnessTxnNummber());
							}
						}
						forwardJsp = RegCompCheckerConstant.WITNESS_VIEW;
					} else {
						session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
						forwardJsp = "failureChecker";
					}
				}
				if (RegCompCheckerConstant.ADD_MORE_WITNESS.equalsIgnoreCase(actionName)) {
					formDTO.setWitFirstName("");
					formDTO.setWitMiddleName("");
					formDTO.setWitLastName("");
					formDTO.setWitGender("");
					formDTO.setWitAge("");
					formDTO.setWitFatherName("");
					formDTO.setWitMotherName("");
					formDTO.setWitSpouseName("");
					formDTO.setWitNationality("");
					formDTO.setWitAddress("");
					formDTO.setRelationshipWit("");
					formDTO.setWitPhoneNumber("");
					eForm.setWitnessCompDetailsLst(new ArrayList());
					eForm.setRelationshipList(new ArrayList());
					forwardJsp = RegCompCheckerConstant.EDIT_WITNESS_PAGE;
				}
				if (RegCompCheckerConstant.SAVE_WITNESS_DETAILS_NEW.equalsIgnoreCase(actionName)) {
					boolean saveWitnessDetails = bd.saveWitnessDetails(formDTO, cdate, userId);
					if (saveWitnessDetails) {
						logger.debug("Data Saved Successfully");
						ArrayList witnessDetails = eForm.getWitnessDetailsList();
						RegCompCheckerDTO rdto = new RegCompCheckerDTO();
						// if(rdto.getWitnessTxnNummber().equals(formDTO.getWitnessTxnNummber()))
						// {
						rdto.setFnameTrns(formDTO.getWitFirstName());
						rdto.setLnameTrns(formDTO.getWitLastName());
						rdto.setAgeTrns(formDTO.getWitAge());
						rdto.setIndaddressTrns(formDTO.getWitAddress());
						rdto.setWitnessTxnNummber(formDTO.getWitnessTxnNummber());
						witnessDetails.add(rdto);
						eForm.setWitnessDetailsList(witnessDetails);
						// }
						// }
						forwardJsp = RegCompCheckerConstant.WITNESS_VIEW;
					} else {
						session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
						forwardJsp = "failureChecker";
					}
					// Iterator itr = witnessDetails.iterator();
					// while(itr.hasNext())
					// {
				}
				if (RegCompCheckerConstant.GENDER_TRNS_ACTION.equalsIgnoreCase(actionName)) {
					String gender = "";
					if (formDTO.getWitGender().equalsIgnoreCase("Male"))
						gender = "M";
					else if (formDTO.getWitGender().equalsIgnoreCase("Female"))
						gender = "F";
					else
						gender = "O";
					eForm.setRelationshipList(bd.getRelationshipList(gender, language));
					forwardJsp = RegCompCheckerConstant.EDIT_WITNESS_PAGE;
				}
				if (RegCompCheckerConstant.RESET_ACTION_WITNESS.equalsIgnoreCase(actionName)) {
					formDTO.setWitFirstName("");
					formDTO.setWitMiddleName("");
					formDTO.setWitLastName("");
					formDTO.setWitGender("");
					formDTO.setWitAge("");
					formDTO.setWitFatherName("");
					formDTO.setWitMotherName("");
					formDTO.setWitSpouseName("");
					formDTO.setWitNationality("");
					formDTO.setWitAddress("");
					formDTO.setWitPhoneNumber("");
					formDTO.setRelationshipWit("");
					eForm.setRelationshipList(new ArrayList());
					forwardJsp = RegCompCheckerConstant.EDIT_WITNESS_PAGE;
				}
				if (RegCompCheckerConstant.CANCEL_ACTION_WITNESS.equalsIgnoreCase(actionName)) {
					forwardJsp = RegCompCheckerConstant.WITNESS_VIEW;
				}
				// ************consenter********************************//
				if (RegCompCheckerConstant.DELETE_CONSENTER.equalsIgnoreCase(actionName)) {
					logger.debug("INSIDE ACTION---- DELETE CONSENTER");
					ArrayList consenterDetails = eForm.getConsenterDetailsList();
					String witnessTxnId = formDTO.getSelectedWitnessTxnNummber(); // pending
					boolean flag = bd.updateConsenterDetails(witnessTxnId, cdate, userId);
					Iterator itr = consenterDetails.iterator();
					while (itr.hasNext()) {
						RegCompCheckerDTO rDTO = (RegCompCheckerDTO) itr.next();
						if (rDTO.getWitnessTxnNummber().equals(witnessTxnId)) {
							consenterDetails.remove(rDTO);
							break;
						}
					}
					logger.debug("size of arrayList" + consenterDetails.size());
					eForm.setConsenterDetailsList(consenterDetails);
					forwardJsp = RegCompCheckerConstant.WITNESS_VIEW;
				}
				if (RegCompCheckerConstant.EDIT_CONSENTER.equals(actionName)) {
					logger.debug("IN CHECKER ACTION----->EDIT_WITNESS");
					String witnessTxnId = formDTO.getSelectedWitnessTxnNummber();
					// logger.debug("IN CHECKER ACTION selected ID----->"+witnessTxnId);
					ArrayList consenterCompList = bd.getCompleteConsenterDetails(formDTO.getRegInitNumber(), witnessTxnId);
					Iterator itr = consenterCompList.iterator();
					while (itr.hasNext()) {
						RegCompCheckerDTO rrto = (RegCompCheckerDTO) itr.next();
						String gender = rrto.getWitGender();
						formDTO.setWitFirstName(rrto.getWitFirstName());
						formDTO.setWitMiddleName(rrto.getWitMiddleName());
						formDTO.setWitLastName(rrto.getWitLastName());
						formDTO.setWitAge(rrto.getWitAge());
						formDTO.setWitFatherName(rrto.getWitFatherName());
						formDTO.setWitMotherName(rrto.getWitMotherName());
						formDTO.setWitSpouseName(rrto.getWitSpouseName());
						formDTO.setWitNationality(rrto.getWitNationality());
						formDTO.setWitAddress(rrto.getWitAddress());
						formDTO.setWitPhoneNumber(rrto.getWitPhoneNumber());
						formDTO.setRelationshipWit(rrto.getRelationshipWit());
						if (gender.equalsIgnoreCase("M"))
							formDTO.setWitGender("Male");
						else
							formDTO.setWitGender("Female");
						eForm.setRelationshipList(bd.getRelationshipList(gender, language));
					}
					logger.debug("^^^^^^^^^^^^^^^" + formDTO.getWitMiddleName());
					// formDTO.setWitnessTxnNummber(witnessTxnId);
					eForm.setConsenterCompDetailsLst(consenterCompList);
					forwardJsp = RegCompCheckerConstant.EDIT_CONSENTER_PAGE;
				}
				if (RegCompCheckerConstant.SAVE_CONSENTER_DETAILS.equalsIgnoreCase(actionName)) {
					boolean saveConsenterDetails = bd.updateAndSaveConsenterDetails(formDTO, formDTO.getSelectedWitnessTxnNummber(), cdate, userId);
					if (saveConsenterDetails) {
						logger.debug("Data Saved Successfully");
					} else {
						logger.debug("Data not Saved");
					}
					ArrayList consenterDetails = eForm.getConsenterDetailsList();
					Iterator itr = consenterDetails.iterator();
					while (itr.hasNext()) {
						RegCompCheckerDTO rdto = (RegCompCheckerDTO) itr.next();
						// logger.debug("witness number ---"+rdto.getWitnessTxnNummber());
						if (rdto.getWitnessTxnNummber().equals(formDTO.getSelectedWitnessTxnNummber())) {
							logger.debug("Match found");
							rdto.setFnameTrns(formDTO.getWitFirstName());
							rdto.setLnameTrns(formDTO.getWitLastName());
							rdto.setAgeTrns(formDTO.getWitAge());
							rdto.setIndaddressTrns(formDTO.getWitAddress());
							rdto.setWitnessTxnNummber(formDTO.getWitnessTxnNummber());
						}
					}
					forwardJsp = RegCompCheckerConstant.WITNESS_VIEW;
				}
				if (RegCompCheckerConstant.ADD_MORE_CONSENTER.equalsIgnoreCase(actionName)) {
					formDTO.setWitFirstName("");
					formDTO.setWitMiddleName("");
					formDTO.setWitLastName("");
					formDTO.setWitGender("");
					formDTO.setWitAge("");
					formDTO.setWitFatherName("");
					formDTO.setWitMotherName("");
					formDTO.setWitSpouseName("");
					formDTO.setWitNationality("");
					formDTO.setWitAddress("");
					formDTO.setWitPhoneNumber("");
					formDTO.setRelationshipWit("");
					eForm.setConsenterCompDetailsLst(new ArrayList());
					forwardJsp = RegCompCheckerConstant.EDIT_CONSENTER_PAGE;
				}
				if (RegCompCheckerConstant.SAVE_CONSENTER_DETAILS_NEW.equalsIgnoreCase(actionName)) {
					boolean saveConsenterDetails = bd.saveConsenterDetails(formDTO, cdate, userId);
					if (saveConsenterDetails) {
						logger.debug("Data Saved Successfully");
					} else {
						logger.debug("Data not Saved");
					}
					ArrayList consenterDetails = eForm.getConsenterDetailsList();
					// Iterator itr = witnessDetails.iterator();
					// while(itr.hasNext())
					// {
					RegCompCheckerDTO rdto = new RegCompCheckerDTO();
					// if(rdto.getWitnessTxnNummber().equals(formDTO.getWitnessTxnNummber()))
					// {
					rdto.setFnameTrns(formDTO.getWitFirstName());
					rdto.setLnameTrns(formDTO.getWitLastName());
					rdto.setAgeTrns(formDTO.getWitAge());
					rdto.setIndaddressTrns(formDTO.getWitAddress());
					rdto.setWitnessTxnNummber(formDTO.getWitnessTxnNummber());
					consenterDetails.add(rdto);
					eForm.setConsenterDetailsList(consenterDetails);
					// }
					// }
					forwardJsp = RegCompCheckerConstant.WITNESS_VIEW;
				}
				if (RegCompCheckerConstant.GENDER_CONS_ACTION.equalsIgnoreCase(actionName)) {
					String gender = "";
					if (formDTO.getWitGender().equalsIgnoreCase("Male"))
						gender = "M";
					else
						gender = "F";
					eForm.setRelationshipList(bd.getRelationshipList(gender, language));
					forwardJsp = RegCompCheckerConstant.EDIT_CONSENTER_PAGE;
				}
				if (RegCompCheckerConstant.RESET_ACTION_CONSENTER.equalsIgnoreCase(actionName)) {
					formDTO.setWitFirstName("");
					formDTO.setWitMiddleName("");
					formDTO.setWitLastName("");
					formDTO.setWitGender("");
					formDTO.setWitAge("");
					formDTO.setWitFatherName("");
					formDTO.setWitMotherName("");
					formDTO.setWitSpouseName("");
					formDTO.setWitNationality("");
					formDTO.setWitAddress("");
					formDTO.setWitPhoneNumber("");
					formDTO.setRelationshipWit("");
					eForm.setRelationshipList(new ArrayList());
					forwardJsp = RegCompCheckerConstant.EDIT_CONSENTER_PAGE;
				}
				if (RegCompCheckerConstant.CANCEL_ACTION_CONSENTER.equalsIgnoreCase(actionName)) {
					forwardJsp = RegCompCheckerConstant.WITNESS_VIEW;
				}
				// *********************consenter*********************//
				if (RegCompCheckerConstant.HOLD_ACTION.equalsIgnoreCase(actionName)) {
					if (formDTO.getPage().equalsIgnoreCase(RegCompCheckerConstant.LINKING_PAYMENT_PAGE)) {
						boolean savePropPayLink = bd.saveLinkingPropertyPaymentDetails(formDTO.getRegInitNumber(), userId, cdate);
						if (savePropPayLink) {
							logger.debug("PROP_PAYMENT_LINKING DATA SAVED");
						} else {
							logger.debug("PROP_PAYMENT_LINKING DATA NOT SAVED");
						}
					} else if (formDTO.getPage().equalsIgnoreCase(RegCompCheckerConstant.VIEW_CHECKLIST)) {
						logger.debug("FLAG" + formDTO.getExecOutIndiaFlag());
						formDTO.setExecDate(bd.getExecutionDate(formDTO.getRegInitNumber()));
						String partyID = formDTO.getPartyTxnId();
						if (!(partyID.equals(null) || (partyID.equals("")))) {
							ArrayList docs = new ArrayList();
							RegCompCheckerDTO gDTO = new RegCompCheckerDTO();
							gDTO.setUpldDeathCert(formDTO.getUpldDeathCert());
							// /gDTO.setReltnProofContents(formDTO.getReltnProofContents());
							gDTO.setUplaReltnProof(formDTO.getUplaReltnProof());
							gDTO.setComments(formDTO.getComments());
							gDTO.setFilePath(formDTO.getFilePath() + "/" + formDTO.getPartyId() + "/" + formDTO.getDeathCert());
							gDTO.setFilePathRltn(formDTO.getFilePathRltn() + "/" + formDTO.getPartyId() + "/" + formDTO.getRelationProof());
							gDTO.setCheck("Y");
							docs.add(gDTO);
							deathUploadList = eForm.getUploadDthList();
							// int count = eForm.getDeathCertCount();
							// if (count == 0) {
							// count = 1;
							// }
							// logger.debug("count"+count);
							// if
							// (deathCertList.containsKey(Integer.toString(count)))
							// {
							// logger.debug("if");
							// count++;
							// deathCertList.put(Integer.toString(count), poa);
							// } else {
							// logger.debug("else");
							deathUploadList.put(partyID, docs);
							// }
							// eForm.setDeathCertCount(count);
							eForm.setUploadDthList(deathUploadList);
						}
						String authNo = formDTO.getPoaAuthNo();
						if (!(authNo.equals(null) || authNo.equals(""))) {
							RegCompCheckerDTO rdto = new RegCompCheckerDTO();
							logger.debug("<-------add more POA");
							if (formDTO.getPoaMpFlg1().equals("1")) {
								rdto.setPoaMpFlg("N");
							} else {
								rdto.setPoaMpFlg("Y");
							}
							rdto.setPoaAuthNo(formDTO.getPoaAuthNo());
							rdto.setFilePathPOA(formDTO.getFilePath() + "/" + formDTO.getPoaAuthNo() + "/" + RegCompCheckerConstant.FILE_NAME_POA_DOC);
							rdto.setPoaUploadFilename(RegCompCheckerConstant.FILE_NAME_POA_DOC);
							// rdto.setPoaUploadContents(formDTO.getPoaUploadContents());
							// rdto.setPoaUploadSize(formDTO.getPoaUploadSize());
							rdto.setUploadPoaDoc(RegCompCheckerConstant.FILE_NAME_POA_DOC);
							rdto.setCheck("Y");
							ArrayList poa = new ArrayList();
							poa.add(rdto);
							poaCheckList = eForm.getUploadPOAList();
							int count = eForm.getPoaCount();
							if (count == 0) {
								count = 1;
							}
							poaCheckList.put(formDTO.getPoaAuthNo(), poa);
							eForm.setUploadPOAList(poaCheckList);
						}
						// *******************ADDED BY
						// SIMRAN*******************//
						/*
						 * deathUploadList = new HashMap();
						 * 
						 * deathUploadList = eForm.getUploadDthList();
						 * formDTO.setDocAftrDeathFlg("");
						 * if(deathUploadList.size()!= 0 ) { String fileName =
						 * ""; formDTO.setDocAftrDeathFlg("Y"); Set mapSet =
						 * (Set)deathUploadList.entrySet(); Iterator mapIterator
						 * = mapSet.iterator(); while(mapIterator.hasNext()) {
						 * Map.Entry mapEntry = (Map.Entry)mapIterator.next();
						 * ArrayList valueList = (ArrayList)mapEntry.getValue();
						 * RegCompCheckerDTO rDTO =
						 * (RegCompCheckerDTO)valueList.get(0); String partyId =
						 * (String)mapEntry.getKey(); //
						 * logger.debug("party Id in Upload"+partyId); //
						 * logger.
						 * debug("party Id in Upload"+rDTO.getFilePath());
						 * if(rDTO.getCheck().equals("Y")) {
						 * if(rDTO.getDeatCertContents() != null) { fileName =
						 * uploadFile(formDTO.getRegInitNumber(),
						 * rDTO.getDeatCertContents
						 * (),partyId,RegCompConstant.UPLOAD_PATH_DEATH_CERT,
						 * RegCompConstant.FILE_NAME_DTH_CERTIFICATE,"2"); //
						 * logger.debug("file Path in Upload"+fileName);
						 * if(fileName != null && rDTO.getReltnProofContents()
						 * != null) { rDTO.setFilePath(fileName); fileName =
						 * uploadFile(formDTO.getRegInitNumber(),
						 * rDTO.getReltnProofContents
						 * (),partyId,RegCompConstant.UPLOAD_PATH_RELN_PROOF,
						 * RegCompConstant.FILE_NAME_RELTN_PROOF,"2");
						 * if(fileName!= null) { rDTO.setFilePathRltn(fileName);
						 * // logger.debug("file Path in Upload"+fileName); } }
						 * } }
						 * 
						 * if(fileName == null) { messages.add("MSG1", new
						 * ActionMessage( "upload_error"));
						 * saveMessages(request, messages);
						 * eForm.setErrLnkFlg("U"); forwardJsp = "checklist"; }
						 * } }
						 * 
						 * poaCheckList = new HashMap(); poaCheckList =
						 * (Map)eForm.getUploadPOAList();
						 * formDTO.setPoaFlag(""); if(poaCheckList.size()!= 0 )
						 * { formDTO.setPoaFlag("Y"); String fileName = "";
						 * 
						 * Set mapSet = (Set)poaCheckList.entrySet(); Iterator
						 * mapIterator = mapSet.iterator();
						 * while(mapIterator.hasNext()) { Map.Entry mapEntry =
						 * (Map.Entry)mapIterator.next(); ArrayList valueList =
						 * (ArrayList)mapEntry.getValue(); RegCompCheckerDTO
						 * rDTO = (RegCompCheckerDTO)valueList.get(0); String
						 * poaAuthNo = rDTO.getPoaAuthNo();
						 * if(rDTO.getCheck().equals("Y")) {
						 * if(rDTO.getPoaUploadContents() != null) { fileName =
						 * uploadFile(formDTO.getRegInitNumber(),
						 * rDTO.getPoaUploadContents
						 * (),poaAuthNo,RegCompConstant.UPLOAD_PATH_POA_DOC,
						 * RegCompConstant.FILE_NAME_POA_DOC,"2");
						 * 
						 * if(fileName != null) { rDTO.setFilePathPOA(fileName);
						 * } } } if(fileName == null) { messages.add("MSG1", new
						 * ActionMessage( "upload_error"));
						 * saveMessages(request, messages);
						 * eForm.setErrLnkFlg("U"); forwardJsp =
						 * RegCompCheckerConstant.VIEW_CHECKLIST; } } }
						 */
						boolean dataModified = bd.modifyCheckListDetails(formDTO, userId, cdate, eForm.getUploadDthList(), eForm.getUploadPOAList());
						if (dataModified) {
							logger.debug("CHECKLIST DATA MODIFIED");
						} else {
							logger.debug("Checklist data not modified");
						}
					} else if (formDTO.getPage().equalsIgnoreCase(RegCompCheckerConstant.VIEW_COMPLIANCE_LIST)) {
						logger.debug("Hold At complianceList");
						// /////******************saving compliance
						// data**************************//
						boolean flag = bd.modifyComplianceListData(eForm, formDTO.getRegInitNumber(), userId, cdate);
						logger.debug("**********Complaince list modified****" + flag);
						// boolean flag1 =
						// bd.addWitnessPhotographDetails(eForm.getUploadWitnessPhotograph(),
						// formDTO.getRegInitNumber(), userId, cdate);
						// logger.debug("**********WitnessList****"+flag1);
						// *****************************END**************************************//
					} else if (formDTO.getPage().equalsIgnoreCase(RegCompCheckerConstant.VIEW_PRESENT_PARTIES)) {}
					ArrayList holdData = bd.getHoldData();
					eForm.setHoldListDisp(holdData);
					eForm.setCheck("HD");
					forwardJsp = formDTO.getPage();
				}
				if ("SAVE_HOLD_ACTION_COMPLIANCE_2".equalsIgnoreCase(actionName)) {
					String mainReasonenglish = "";
					String mainReasonHindi = "";
					if (bd.addHoldRemarks(formDTO)) {
						if (formDTO.getHoldReason().equalsIgnoreCase("invalid")) {
							formDTO.setHoldPageId("5");// roopam
							mainReasonenglish = "Invalid Document-";
							mainReasonHindi = "अवैध दस्तावेज़";
						} else {
							formDTO.setHoldPageId("11");
							mainReasonenglish = "Denial of Execution-";
							mainReasonHindi = "निष्पादन से इंकारी";
						}
						request.setAttribute("HOLD_MESSAGE", bd.getEmailHoldContent(formDTO.getRegInitNumber(), formDTO.getRemarks(), officeId, language, officeId, mainReasonenglish, mainReasonHindi));
						boolean flag = bd.modifyComplianceListData(eForm, formDTO.getRegInitNumber(), userId, cdate);
						logger.debug("**********Complaince list modified****" + flag);
						// boolean flag1 =
						// bd.addWitnessPhotographDetails(eForm.getUploadWitnessPhotograph(),
						// formDTO.getRegInitNumber(), userId, cdate);
						String hldFlag = "Y";
						String createdBy = userId;
						Date date = new Date();
						System.out.println(date);
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						System.out.println(sdf.format(date));
						String Date = sdf.format(date);
						String fwdPage = "viewComplianceList";
						boolean flag2 = bd.isHoldData(formDTO.getRegInitNumber(), fwdPage);
						boolean holdData = bd.saveHoldDataChecker(formDTO, hldFlag, fwdPage, Date, createdBy, flag2, officeId, mainReasonenglish, mainReasonHindi);
						// End=== this code for saving the data in the hold
						// table
						if (holdData) {
							String status = bd.emailAlertHold(formDTO.getRegInitNumber(), officeId);
							logger.debug("email status----->" + status);
							forwardJsp = "CONFIRMATION_SCREEN";
						}
						if ("en".equalsIgnoreCase(language)) {
							request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "The Application has been put on hold Successfully. ");
						} else {
							request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "आवेदन सफलतापूर्वक होल्ड पर रख दिया गया है |");
						}
						/*
						 * EtokenChange eChange = new
						 * EtokenChange(formDTO.getRegInitNumber(),"14",null);
						 * Thread t = new Thread(eChange); t.start();
						 */
						forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
					}
				}
				if ("MPLR_HOLD".equalsIgnoreCase(actionName)) {
					if ("en".equalsIgnoreCase(language)) {
						request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "The Application has been put on hold Successfully. ");
					} else {
						request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "आवेदन सफलतापूर्वक होल्ड पर रख दिया गया है |");
					}
					/*
					 * EtokenChange eChange = new
					 * EtokenChange(formDTO.getRegInitNumber(),"14",null);
					 * Thread t = new Thread(eChange); t.start();
					 */
					forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
				}
				if ("SAVE_HOLD_ACTION_COMPLIANCE".equalsIgnoreCase(actionName)) {
					String mainReasonenglish = "";
					String mainReasonHindi = "";
					if (formDTO.getHoldReason().equalsIgnoreCase("invalid")) {
						formDTO.setHoldPageId("5");
						mainReasonenglish = "Invalid Document-";
						mainReasonHindi = "अवैध दस्तावेज़";
					} else {
						formDTO.setHoldPageId("11");
						mainReasonenglish = "Denial of Execution-";
						mainReasonHindi = "निष्पादन से इंकारी";
					}
					request.setAttribute("HOLD_MESSAGE", bd.getEmailHoldContent(formDTO.getRegInitNumber(), formDTO.getRemarks(), officeId, language, officeId, mainReasonenglish, mainReasonHindi));
					boolean flag = bd.modifyComplianceListData(eForm, formDTO.getRegInitNumber(), userId, cdate);
					logger.debug("**********Complaince list modified****" + flag);
					// boolean flag1 =
					// bd.addWitnessPhotographDetails(eForm.getUploadWitnessPhotograph(),
					// formDTO.getRegInitNumber(), userId, cdate);
					String hldFlag = "Y";
					String createdBy = userId;
					Date date = new Date();
					System.out.println(date);
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					System.out.println(sdf.format(date));
					String Date = sdf.format(date);
					String fwdPage = "viewComplianceList";
					boolean flag2 = bd.isHoldData(formDTO.getRegInitNumber(), fwdPage);
					boolean holdData = bd.saveHoldDataChecker(formDTO, hldFlag, fwdPage, Date, createdBy, flag2, officeId, mainReasonenglish, mainReasonHindi);
					// End=== this code for saving the data in the hold
					// table
					if (holdData) {
						String status = bd.emailAlertHold(formDTO.getRegInitNumber(), officeId);
						logger.debug("email status----->" + status);
						forwardJsp = "CONFIRMATION_SCREEN";
					}
					// request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD,
					// "The Application has been put on hold Successfully ");
					if ("en".equalsIgnoreCase(language)) {
						request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "The Application has been put on hold Successfully. ");
					} else {
						request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "आवेदन सफलतापूर्वक होल्ड पर रख दिया गया है |");
					}
					/*
					 * EtokenChange eChange = new
					 * EtokenChange(formDTO.getRegInitNumber(),"14",null);
					 * Thread t = new Thread(eChange); t.start();
					 */
					forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
				}
				if (RegCompCheckerConstant.SAVE_HOLD_ACTION.equals(actionName)) {
					String hldFlag = "Y";
					String fwdPage = formDTO.getPage();
					if (fwdPage.equalsIgnoreCase(RegCompCheckerConstant.LINKING_PAYMENT_PAGE)) {
						formDTO.setHoldPageId("3");
					} else if (fwdPage.equalsIgnoreCase(RegCompCheckerConstant.VIEW_CHECKLIST)) {
						formDTO.setHoldPageId("4");
					} else if (fwdPage.equalsIgnoreCase(RegCompCheckerConstant.VIEW_COMPLIANCE_LIST)) {
						formDTO.setHoldPageId("5");
					}
					String createdBy = userId;
					Date date = new Date();
					System.out.println(date);
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					System.out.println(sdf.format(date));
					String Date = sdf.format(date);
					boolean flag = bd.isHoldData(formDTO.getRegInitNumber(), fwdPage);
					boolean holdData = bd.saveHoldDataChecker(formDTO, hldFlag, fwdPage, Date, createdBy, flag, officeId, "", "");
					request.setAttribute("HOLD_MESSAGE", bd.getEmailHoldContent(formDTO.getRegInitNumber(), formDTO.getRemarks(), officeId, language, officeId, "", ""));
					// End=== this code for saving the data in the hold
					// table
					if (holdData) {
						String status = bd.emailAlertHold(formDTO.getRegInitNumber(), officeId);
						logger.debug("email status----->" + status);
						forwardJsp = "CONFIRMATION_SCREEN";
					}
					// request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD,
					// "The Application has been put on hold Successfully ");
					if ("en".equalsIgnoreCase(language)) {
						request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "The Application has been put on hold Successfully. ");
					} else {
						request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "आवेदन सफलतापूर्वक होल्ड पर रख दिया गया है |");
					}
					/*
					 * EtokenChange eChange = new
					 * EtokenChange(formDTO.getRegInitNumber(),"14",null);
					 * Thread t = new Thread(eChange); t.start();
					 */
					forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
				}
				// ////////////////////////////////////////////////////////////////////////////////
				if (RegCompCheckerConstant.NEXT_TO_LINK_HISTORY.equalsIgnoreCase(actionName)) {
					if (formDTO.getCancelledPage() != null && formDTO.getCancelledPage().equalsIgnoreCase("viewWitness")) {
						if (eForm.getWitnessDetailsList().size() < 2) {
							logger.debug("Witness Count < 2");
							messages.add("ERRORMSG", new ActionMessage("witness_required_checker"));
							saveMessages(request, messages);
							eForm.setCheck("WT");
							request.setAttribute("checkRegID", eForm);
							forwardJsp = RegCompCheckerConstant.WITNESS_VIEW;
						} else {
							logger.debug("Next to Linking History Action");
							// boolean sealInsertion =
							// bd.insertSealsData(formDTO.getRegInitNumber(),
							// formDTO.getSealId(), formDTO.getSealSubTypeId());
							// logger.debug("seal insertion****"+sealInsertion);
							formDTO.setSeal("");
							formDTO.setSealId("");
							formDTO.setSealSubTypeId("");
							eForm.setCheck("");
							if (formDTO.getConsenterVal().equalsIgnoreCase("Y")) {
								formDTO.setWitFirstName("");
								formDTO.setWitMiddleName("");
								formDTO.setWitLastName("");
								formDTO.setWitGender("");
								formDTO.setWitAge("");
								formDTO.setWitFatherName("");
								formDTO.setWitMotherName("");
								formDTO.setWitSpouseName("");
								formDTO.setWitNationality("");
								formDTO.setWitAddress("");
								formDTO.setWitPhoneNumber("");
								eForm.setConsenterCompDetailsLst(new ArrayList());
								forwardJsp = RegCompCheckerConstant.EDIT_CONSENTER_PAGE;
								formDTO.setConsenterVal("");
							} else {
								// *************below code for checking non
								// property deeds and to skip link history
								// page*****//
								int deedId = formDTO.getDeedID();
								// logger.debug("<-----deed id----->"+deedId);
								int instId = formDTO.getInstID();
								// logger.debug("<-----inst Id----->"+instId);
								if (deedId == RegInitConstant.DEED_ADOPTION || deedId == RegInitConstant.DEED_CANCELLATION_OF_WILL_POA || (deedId == RegInitConstant.DEED_TRUST && instId == RegInitConstant.INST_TRUST_NPV_P) || deedId == RegInitConstant.DEED_AGREEMENT_MEMO_NPV || deedId == RegInitConstant.DEED_AGREEMENT_MEMO_NPV_LOAN || formDTO.getCommonDeedFlag() == 1) {
									/*-if(deedId.equals(RegCompCheckerConstant.ADOPTION_DEED)||deedId.equals(RegCompCheckerConstant.CANCELLATION_OF_WILL_POA)||
											(deedId.equals(RegCompCheckerConstant.TRUST_DEED)&&instId.equals(RegCompCheckerConstant.INSTRUMENT_TRUST_NO_P))||
												deedId.equals(RegCompCheckerConstant.AGREEMENT_OF_MEMORANDUM)||formDTO.getCommonDeedFlag() == 1)*/
									formDTO.setSrSign("N");
									formDTO.setRemarks("");
									String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
									formDTO.setParentPathSign(path);
									formDTO.setFileNameSign("signature.GIF");
									formDTO.setForwardPath("/regCompChecker.do?txnPartyIdSignature=");
									formDTO.setForwardName("regCompChecker");
									String pathSr = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_SR_SIGN;
									formDTO.setParentPathSrSign(pathSr);
									formDTO.setFileNameSrSign("signature.GIF");
									formDTO.setForwardPathSr("/regCompChecker.do?label=SrSignature");
									String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
									formDTO.setFileNameFP("FingerPrint.GIF");
									formDTO.setParentPathFP(pathFP);
									formDTO.setParentPathGovtLttr(FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_GOVT_LETTER);
									formDTO.setFileNameGovtLttr(RegCompCheckerConstant.FILE_NAME_GOVT_LETTER);
									formDTO.setExecDate(bd.getExecutionDate(formDTO.getRegInitNumber()));
									ArrayList partyPresentList = bd.getPartiesPresentDetails(formDTO.getRegInitNumber(), language);
									// ArrayList govtOfficialList =
									// bd.getofficialList(); // commented on 10
									// june as drop down is no longer required
									// eForm.setGovtOfficialList(govtOfficialList);
									eForm.setPartiesPresentList(partyPresentList);
									if (partyPresentList.size() != 0) {
										logger.debug("Parties Present");
										formDTO.setFirstCheck("Y");
										eForm.setPartiesPresentList(partyPresentList);
									} else {
										logger.debug("Parties present list EMPTY");
									}
									boolean check1 = bd.dateComparisonElevenMonths(formDTO.getExecDate());
									if (check1) {
										eForm.setCheck("ELMNCHK");
										forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
									} else {
										boolean check = true;// bd.dateComparison(formDTO.getExecDate());
										logger.debug("check" + check);
										if (!check) {
											logger.debug("inside check failure");
											boolean caseDetails = false;// bd.updateTimeBarrdCaseDetails(formDTO,
											// cdate,
											// userId,"16");
											if (caseDetails) {
												String status = bd.emailAlertHold(formDTO.getRegInitNumber(), officeId);
												logger.debug("emailAlert---->status" + status);
												logger.debug("data Updated");
												messages.add("SUCCESS_MESSAGE", new ActionMessage("case.referred"));
												saveMessages(request, messages);
												eForm.setCheck("CMT");
												forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
											} else {
												logger.debug("data Not Updated");
												messages.add("MSG", new ActionMessage("case.referred.error"));
												saveMessages(request, messages);
												eForm.setCheck2("Y");
												request.setAttribute("checkRegID", eForm);
												forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
											}
										} else {
											boolean flag = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), RegCompCheckerConstant.PRESENTATION_STATUS, cdate, userId);
											forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
										}
									}
								} else {
									/*
									 * if(formDTO.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE
									 * ||formDTO.getDeedID()==RegInitConstant.
									 * DEED_LEASE_NPV ||
									 * formDTO.getDeedID()==RegInitConstant
									 * .DEED_MORTGAGE_NPV ||
									 * formDTO.getDeedID()==
									 * RegInitConstant.DEED_POA ||
									 * formDTO.getDeedID
									 * ()==RegInitConstant.DEED_SETTLEMENT_NPV
									 * ||formDTO.getDeedID()==RegInitConstant.
									 * DEED_WILL_NPV ||
									 * formDTO.getDeedID()==RegInitConstant
									 * .DEED_TRANSFER_LEASE_NPV ||
									 * formDTO.getDeedID
									 * ()==RegInitConstant.DEED_RECONV_MORTGAGE_NPV
									 * ||formDTO.getDeedID()==RegInitConstant.
									 * DEED_SURRENDER_LEASE_NPV ||
									 * formDTO.getDeedID
									 * ()==RegInitConstant.DEED_COMPOSITION_NPV
									 * ||formDTO.getDeedID()==RegInitConstant.
									 * DEED_SECURITY_BOND_NPV ||
									 * formDTO.getDeedID()==RegInitConstant.
									 * DEED_LETTER_OF_LICENCE_NPV ||
									 * (formDTO.getDeedID
									 * ()==RegInitConstant.DEED_TRUST &&
									 * formDTO.
									 * getInstID()==RegInitConstant.INST_TRUST_NPV_P
									 * ) ||
									 * (formDTO.getDeedID()==RegInitConstant
									 * .DEED_PARTNERSHIP_NPV &&
									 * (formDTO.getInstID
									 * ()==RegInitConstant.INST_PARTNERSHIP_NPV_1
									 * ||formDTO.getInstID()==RegInitConstant.
									 * INST_PARTNERSHIP_NPV_2 ))||
									 * formDTO.getDeedID
									 * ()==RegInitConstant.DEED_PARTITION_NPV ||
									 * (formDTO.getDeedID()==RegInitConstant.
									 * DEED_AGREEMENT_MEMO_NPV &&
									 * formDTO.getAgreeMemoInstFlag
									 * ()==RegInitConstant
									 * .NPV_PROP_REQ_CONSTANT_1) ||
									 * (formDTO.getDeedID
									 * ()==RegInitConstant.DEED_TRANSFER_NPV &&
									 * formDTO
									 * .getAgreeMemoInstFlag()==RegInitConstant
									 * .NPV_PROP_REQ_CONSTANT_1) ||
									 * (formDTO.getDeedID
									 * ()==RegInitConstant.DEED_FURTHER_CHARGE_NPV
									 * &&formDTO.getAgreeMemoInstFlag()==
									 * RegInitConstant.NPV_PROP_REQ_CONSTANT_1)
									 * || formDTO.getDeedTypeFlag()==1||
									 * formDTO.getCommonDeedFlag() == 1){
									 * 
									 * formDTO.setPropPage("N"); } else {
									 * formDTO.setPropPage("P"); }
									 */
									ArrayList linkHistoryList = bd.getLinkHstryDetails(formDTO.getRegInitNumber());
									if (linkHistoryList.size() != 0) {
										eForm.setLinkHistoryList(linkHistoryList);
									} else {
										logger.debug("Link History List Empty");
										eForm.setCheck("H");
										// eForm.setLinkingDutiesDisp(new
										// ArrayList());
										request.setAttribute("checkRegID", eForm);
									}
									boolean flag = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), RegCompCheckerConstant.LINKING_HISTORY_STATUS, cdate, userId);
									if (flag) {
										forwardJsp = RegCompCheckerConstant.LINK_HISTORY_VIEW;
									} else {
										session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
										forwardJsp = "failureChecker";
									}
								}
							}
						}
					} else {
						// *************below code for checking non property
						// deeds and to skip link history page*****//
						int deedId = formDTO.getDeedID();
						// logger.debug("<-----deed id----->"+deedId);
						int instId = formDTO.getInstID();
						// logger.debug("<-----inst Id----->"+instId);
						/*-if(deedId.equals(RegCompCheckerConstant.ADOPTION_DEED)||deedId.equals(RegCompCheckerConstant.CANCELLATION_OF_WILL_POA)||
								(deedId.equals(RegCompCheckerConstant.TRUST_DEED)&&instId.equals(RegCompCheckerConstant.INSTRUMENT_TRUST_NO_P))||
									deedId.equals(RegCompCheckerConstant.AGREEMENT_OF_MEMORANDUM)||formDTO.getCommonDeedFlag() == 1)
						{*/
						if (deedId == RegInitConstant.DEED_ADOPTION || deedId == RegInitConstant.DEED_CANCELLATION_OF_WILL_POA || (deedId == RegInitConstant.DEED_TRUST && instId == RegInitConstant.INST_TRUST_NPV_P) || deedId == RegInitConstant.DEED_AGREEMENT_MEMO_NPV || deedId == RegInitConstant.DEED_AGREEMENT_MEMO_NPV_LOAN || formDTO.getCommonDeedFlag() == 1) {
							formDTO.setSrSign("N");
							formDTO.setRemarks("");
							String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
							formDTO.setParentPathSign(path);
							formDTO.setFileNameSign("signature.GIF");
							formDTO.setForwardPath("/regCompChecker.do?txnPartyIdSignature=");
							formDTO.setForwardName("regCompChecker");
							String pathSr = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_SR_SIGN;
							formDTO.setParentPathSrSign(pathSr);
							formDTO.setFileNameSrSign("signature.GIF");
							formDTO.setForwardPathSr("/regCompChecker.do?label=SrSignature");
							String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
							formDTO.setFileNameFP("FingerPrint.GIF");
							formDTO.setParentPathFP(pathFP);
							formDTO.setParentPathGovtLttr(FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_GOVT_LETTER);
							formDTO.setFileNameGovtLttr(RegCompCheckerConstant.FILE_NAME_GOVT_LETTER);
							formDTO.setExecDate(bd.getExecutionDate(formDTO.getRegInitNumber()));
							ArrayList partyPresentList = bd.getPartiesPresentDetails(formDTO.getRegInitNumber(), language);
							// ArrayList govtOfficialList =
							// bd.getofficialList(); // commented on 10 june as
							// drop down is no longer required
							// eForm.setGovtOfficialList(govtOfficialList);
							eForm.setPartiesPresentList(partyPresentList);
							if (partyPresentList.size() != 0) {
								logger.debug("Parties Present");
								formDTO.setFirstCheck("Y");
								eForm.setPartiesPresentList(partyPresentList);
							} else {
								logger.debug("Parties present list EMPTY");
							}
							boolean check1 = bd.dateComparisonElevenMonths(formDTO.getExecDate());
							if (check1) {
								eForm.setCheck("ELMNCHK");
								forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
							} else {
								boolean check = true;// bd.dateComparison(formDTO.getExecDate());
								logger.debug("check" + check);
								if (!check) {
									logger.debug("inside check failure");
									boolean caseDetails = false;// bd.updateTimeBarrdCaseDetails(formDTO,
									// cdate,
									// userId,"16");
									if (caseDetails) {
										String status = bd.emailAlertHold(formDTO.getRegInitNumber(), officeId);
										logger.debug("emailAlert---->status" + status);
										logger.debug("data Updated");
										messages.add("SUCCESS_MESSAGE", new ActionMessage("case.referred"));
										saveMessages(request, messages);
										eForm.setCheck("CMT");
										forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
									} else {
										logger.debug("data Not Updated");
										messages.add("MSG", new ActionMessage("case.referred.error"));
										saveMessages(request, messages);
										eForm.setCheck2("Y");
										request.setAttribute("checkRegID", eForm);
										forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
									}
								} else {
									boolean flag = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), RegCompCheckerConstant.PRESENTATION_STATUS, cdate, userId);
									forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
								}
							}
						} else {
							/*
							 * if(formDTO.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE
							 * ||
							 * formDTO.getDeedID()==RegInitConstant.DEED_LEASE_NPV
							 * ||formDTO.getDeedID()==RegInitConstant.
							 * DEED_MORTGAGE_NPV ||
							 * formDTO.getDeedID()==RegInitConstant.DEED_POA ||
							 * formDTO
							 * .getDeedID()==RegInitConstant.DEED_SETTLEMENT_NPV
							 * ||
							 * formDTO.getDeedID()==RegInitConstant.DEED_WILL_NPV
							 * ||formDTO.getDeedID()==RegInitConstant.
							 * DEED_TRANSFER_LEASE_NPV ||
							 * formDTO.getDeedID()==RegInitConstant
							 * .DEED_RECONV_MORTGAGE_NPV ||
							 * formDTO.getDeedID()==
							 * RegInitConstant.DEED_SURRENDER_LEASE_NPV ||
							 * formDTO
							 * .getDeedID()==RegInitConstant.DEED_COMPOSITION_NPV
							 * ||formDTO.getDeedID()==RegInitConstant.
							 * DEED_SECURITY_BOND_NPV ||
							 * formDTO.getDeedID()==RegInitConstant
							 * .DEED_LETTER_OF_LICENCE_NPV ||
							 * (formDTO.getDeedID()==RegInitConstant.DEED_TRUST
							 * &&
							 * formDTO.getInstID()==RegInitConstant.INST_TRUST_NPV_P
							 * ) ||(formDTO.getDeedID()==RegInitConstant.
							 * DEED_PARTNERSHIP_NPV &&
							 * (formDTO.getInstID()==RegInitConstant
							 * .INST_PARTNERSHIP_NPV_1 ||
							 * formDTO.getInstID()==RegInitConstant
							 * .INST_PARTNERSHIP_NPV_2 ))||
							 * formDTO.getDeedID()==
							 * RegInitConstant.DEED_PARTITION_NPV ||
							 * (formDTO.getDeedID
							 * ()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV &&
							 * formDTO.getAgreeMemoInstFlag()==RegInitConstant.
							 * NPV_PROP_REQ_CONSTANT_1) ||
							 * (formDTO.getDeedID()==
							 * RegInitConstant.DEED_TRANSFER_NPV &&
							 * formDTO.getAgreeMemoInstFlag
							 * ()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
							 * (formDTO
							 * .getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV
							 * &&
							 * formDTO.getAgreeMemoInstFlag()==RegInitConstant
							 * .NPV_PROP_REQ_CONSTANT_1) ||
							 * formDTO.getDeedTypeFlag()==1||
							 * formDTO.getCommonDeedFlag() == 1){
							 * 
							 * formDTO.setPropPage("N"); } else {
							 * formDTO.setPropPage("P"); }
							 */
							ArrayList linkHistoryList = bd.getLinkHstryDetails(formDTO.getRegInitNumber());
							if (linkHistoryList.size() != 0) {
								eForm.setLinkHistoryList(linkHistoryList);
							} else {
								logger.debug("Link History List Empty");
								eForm.setCheck("H");
								// eForm.setLinkingDutiesDisp(new ArrayList());
								request.setAttribute("checkRegID", eForm);
							}
							boolean flag = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), RegCompCheckerConstant.LINKING_HISTORY_STATUS, cdate, userId);
							forwardJsp = RegCompCheckerConstant.LINK_HISTORY_VIEW;
						}
					}
				}
				if (RegCompCheckerConstant.MODIFY_LINK_HISTORY.equalsIgnoreCase(actionName)) {
					logger.debug("RegCompCheckerAction --> Redirect to makers part");
					formDTO.setActionName("MODIFY_LINK_HISTORY");
					request.setAttribute("LinkHistoryFromChecker", formDTO);
					forwardJsp = RegCompCheckerConstant.REDIRCT_TO_MAKER;
				}
				if (RegCompCheckerConstant.VIEW_PRESENT_PARTIES.equalsIgnoreCase(actionName)) {
					formDTO.setSrSign("N");
					formDTO.setRemarks("");
					formDTO.setNewSignPartyTxnId("");
					formDTO.setNewPartySignPath("");
					logger.debug("BACK FROM MAKER MODIFY ACTION");
					// vinay
					if (bd.checkPinRequired(formDTO.getRegInitNumber())) {
						logger.debug("generate pin action");
						String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
						if (deedInstArray != null && deedInstArray.length > 0) {
							logger.debug("************deedId" + deedInstArray[0].trim());
							formDTO.setDeedId(deedInstArray[0].trim());
						}
						String regInitNumber = formDTO.getRegInitNumber();
						// bd.getInitiationNumber(formDTO.getRegCompleteId());
						boolean pinFlag = bd.pinGeneration(regInitNumber, formDTO.getDeedId(), userId);
						logger.debug("pin insertion  " + pinFlag);
					}
					// vinay
					// TODO:-
					// boolean flagPin =
					// bd.upadtePinDetails(eForm.getLinkHistoryList());
					// if(flagPin)
					// {
					String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
					formDTO.setParentPathSign(path);
					formDTO.setFileNameSign("signature.GIF");
					formDTO.setForwardPath("/regCompChecker.do?txnPartyIdSignature=");
					formDTO.setForwardName("regCompChecker");
					String pathSr = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_SR_SIGN;
					formDTO.setParentPathSrSign(pathSr);
					formDTO.setFileNameSrSign("signature.GIF");
					formDTO.setForwardPathSr("/regCompChecker.do?label=SrSignature");
					String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
					formDTO.setFileNameFP("FingerPrint.GIF");
					formDTO.setParentPathFP(pathFP);
					formDTO.setParentPathGovtLttr(FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_GOVT_LETTER);
					formDTO.setFileNameGovtLttr(RegCompCheckerConstant.FILE_NAME_GOVT_LETTER);
					// logger.debug("*******************partyId"+formDTO.getPartyId());
					// formDTO.setPhotoLst(new ArrayList());
					formDTO.setExecDate(bd.getExecutionDate(formDTO.getRegInitNumber()));
					ArrayList partyPresentList = bd.getPartiesPresentDetails(formDTO.getRegInitNumber(), language);
					// ArrayList govtOfficialList = bd.getofficialList(); //
					// commented on 10 june as drop down is no longer required
					// eForm.setGovtOfficialList(govtOfficialList);
					eForm.setPartiesPresentList(partyPresentList);
					if (partyPresentList.size() != 0) {
						logger.debug("Parties Present");
						formDTO.setFirstCheck("Y");
						eForm.setPartiesPresentList(partyPresentList);
					} else {
						logger.debug("Parties present list EMPTY");
					}
					boolean check1 = bd.dateComparisonElevenMonths(formDTO.getExecDate());
					if (check1) {
						eForm.setCheck("ELMNCHK");
						forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
					} else {
						boolean check = true;// bd.dateComparison(formDTO.getExecDate());
						logger.debug("check" + check);
						if (!check) {
							logger.debug("inside check failure");
							// messages.add("MSG", new
							// ActionMessage("execution.date.error"));
							// saveMessages(request, messages);
							// eForm.setCheck2("Y");
							// request.setAttribute("checkRegID", eForm);
							boolean caseDetails = false;// bd.updateTimeBarrdCaseDetails(formDTO,
							// cdate, userId,"16");
							if (caseDetails) {
								String status = bd.emailAlertHold(formDTO.getRegInitNumber(), officeId);
								logger.debug("emailAlert---->status" + status);
								logger.debug("data Updated");
								messages.add("SUCCESS_MESSAGE", new ActionMessage("case.referred"));
								saveMessages(request, messages);
								eForm.setCheck("CMT");
								forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
							} else {
								logger.debug("data Not Updated");
								messages.add("MSG", new ActionMessage("case.referred.error"));
								saveMessages(request, messages);
								eForm.setCheck2("Y");
								request.setAttribute("checkRegID", eForm);
								forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
							}
						} else {
							boolean flag = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), RegCompCheckerConstant.PRESENTATION_STATUS, cdate, userId);
							if (flag) {
								forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
							} else {
								session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
								forwardJsp = "failureChecker";
							}
						}
					}
					// }
					// else
					// {
					// formDTO.setCheck("LINKHIST");
					// messages.add("LINKMSG", new ActionMessage("link.error"));
					// /saveMessages(request, messages);
					// forwardJsp = RegCompCheckerConstant.LINK_HISTORY_VIEW;
					// }
				}
				// Added by ankit for Aadhar Validation check - start
				/*
				 * if("Aadhar_Validation".equalsIgnoreCase(actionName)){
				 * 
				 * //call EKYC webservice boolean isEkyc = callEKYCWebService(
				 * eForm, formDTO, bd, language, actionName);
				 * 
				 * ArrayList partyPresentList = eForm.getPartiesPresentList();
				 * String selectedPartyArr[] =
				 * formDTO.getSelectedPartyIds().split("_"); Iterator itr =
				 * partyPresentList.iterator();
				 * 
				 * while(itr.hasNext()) { RegCompCheckerDTO rDTO =
				 * (RegCompCheckerDTO)itr.next(); String party =
				 * rDTO.getPartyTxnId();
				 * 
				 * 
				 * if(party.equalsIgnoreCase(formDTO.getPartyIdForValidation())){
				 * AadharDetailDTO aadharDts = new
				 * RegCompCheckerBD().getAadharDetailsByPartyTxnId(party);
				 * if(aadharDts.getACKID_CHECKER()!=null && aadharDts!=null){
				 * rDTO.setIsAadharValidated("true");
				 * rDTO.setAadharRespCode(""); rDTO.setAadharErrMsg(""); }else{
				 * rDTO.setIsAadharValidated("false");
				 * rDTO.setAadharRespCode(formDTO.getAadharRespCode());
				 * rDTO.setAadharErrMsg(formDTO.getAadharErrMsg()); } }
				 * 
				 * } eForm.setPartiesPresentList(partyPresentList);
				 * 
				 * forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
				 * 
				 * }
				 */
				// Added by ankit for Aadhar Validation check - end
				if (RegCompCheckerConstant.SAVE_LAST_DUE_DATE.equalsIgnoreCase(actionName)) {
					logger.debug("***********SAVE LAST DUE DATE*****ACTION");
					String lastDueDate = formDTO.getLastDueDate();
					boolean flag1 = bd.modifyLastDueDate(lastDueDate, formDTO.getRegInitNumber());
					logger.debug("LAST DUE DATE UPDATED****" + flag1);
					String hldFlag = "Y";
					String fwdPage = formDTO.getPage();
					formDTO.setHoldPageId("8");
					String createdBy = userId;
					Date date = new Date();
					System.out.println(date);
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					System.out.println(sdf.format(date));
					String Date = sdf.format(date);
					boolean flag = bd.isHoldData(formDTO.getRegInitNumber(), fwdPage);
					boolean holdData = bd.saveHoldDataChecker(formDTO, hldFlag, fwdPage, Date, createdBy, flag, officeId, "", "");
					// End=== this code for saving the data in the hold
					// table
					if (holdData) {
						forwardJsp = "CONFIRMATION_SCREEN";
					}
					// request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD,
					// "Last due date has been updated successfully");
					if ("en".equalsIgnoreCase(language)) {
						request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "Last due date has been updated successfully. ");
					} else {
						request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "अंतिम नियत तारीख को सफलतापूर्वक अद्यतन किया गया है |");
					}
					/*
					 * EtokenChange eChange = new
					 * EtokenChange(formDTO.getRegInitNumber(),"14",null);
					 * Thread t = new Thread(eChange); t.start();
					 */
					forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
				}
				/*
				 * if(RegCompCheckerConstant.PROCEED_FOR_PARTIES_DETAILS.equalsIgnoreCase
				 * (actionName)) { boolean check =
				 * bd.dateComparison(formDTO.getExecDate());
				 * logger.debug("check"+check); if(!check) {
				 * logger.debug("inside check failure"); messages.add("MSG", new
				 * ActionMessage("execution.date.error")); saveMessages(request,
				 * messages); eForm.setCheck2("Y");
				 * request.setAttribute("checkRegID", eForm); } else {
				 * formDTO.setFirstCheck("N"); ArrayList partyPresentList =
				 * bd.getPartiesPresentDetails(formDTO.getRegInitNumber());
				 * String selectedPartyArr[] =
				 * formDTO.getSelectedPartyIds().split("_"); Iterator itr =
				 * partyPresentList.iterator(); //ArrayList partyDetails = new
				 * ArrayList(); while(itr.hasNext()) {
				 * logger.debug("inside while"); RegCompCheckerDTO rDTO =
				 * (RegCompCheckerDTO)itr.next(); String party =
				 * rDTO.getPartyTxnId(); logger.debug("party"+party);
				 * logger.debug("selectedpart arr"+selectedPartyArr.length);
				 * for(int i = 0; i<selectedPartyArr.length;i++) { String
				 * tmpArr[] = selectedPartyArr[i].split(",");
				 * if(party.equals(tmpArr[0])) { logger.debug("match");
				 * rDTO.setStatus("Y"); rDTO.setIsGovtOfficial(tmpArr[1]);
				 * rDTO.setGovtOfficial(tmpArr[2]);
				 * rDTO.setReprsnName(tmpArr[2]);
				 * 
				 * break; } else { rDTO.setStatus("N"); }
				 * 
				 * } for(int i = 0; i<selectedPartyArr.length;i++) {
				 * logger.debug("selected"+selectedPartyArr[i]);
				 * if(party.equals(selectedPartyArr[i])) {
				 * logger.debug("match"); rDTO.setStatus("Y");
				 * partyDetails.add(rDTO); break; } else { rDTO.setStatus("N");
				 * }
				 * 
				 * }
				 * 
				 * } //eForm.setPartiesPresentList(partyPresentList);
				 * //eForm.setPartiesDetailList(partyDetails);
				 * 
				 * }
				 * 
				 * 
				 * }
				 */
				/*
				 * if(RegCompCheckerConstant.HOLD_PRESENT_PARTIES.equalsIgnoreCase
				 * (actionName)) {
				 * logger.debug("Inside Hold Action---parties Present"); String
				 * hldFlag = "Y"; String fwdPage =
				 * RegCompCheckerConstant.PRESENT_PARTIES_VIEW; String createdBy
				 * = userId; Date date = new Date(); System.out.println(date);
				 * SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				 * System.out.println(sdf.format(date)); String Date =
				 * sdf.format(date); boolean flag =
				 * bd.isHoldData(formDTO.getRegInitNumber()); boolean holdData =
				 * bd.saveHoldDataChecker(formDTO, hldFlag, fwdPage, Date,
				 * createdBy, flag); // End=== this code for saving the data in
				 * the hold // table if (holdData) {
				 * logger.debug("Hold data Saved"); }
				 * request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD,
				 * "The Application has been put on hold Successfully ");
				 * forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN; }
				 */
				if ("NEXT_TO_COMPLIANCE_LIST_UNHOLD".equalsIgnoreCase(actionName)) {
					if (bd.addHoldRemarks(formDTO)) {
						formDTO.setHoldCheck("");
						formDTO.setRemarks("");
						formDTO.setHoldfileName("");
						formDTO.setHoldfilePath("");
						ArrayList partyPresentList = eForm.getPartiesPresentList();
						String selectedPartyArr[] = formDTO.getSelectedPartyIds().split("_");
						Iterator itr = partyPresentList.iterator();
						// ArrayList partyDetails = new ArrayList();
						while (itr.hasNext()) {
							logger.debug("inside while");
							RegCompCheckerDTO rDTO = (RegCompCheckerDTO) itr.next();
							String party = rDTO.getPartyTxnId();
							// logger.debug("party"+party);
							logger.debug("selectedpart arr" + selectedPartyArr.length);
							for (int i = 0; i < selectedPartyArr.length; i++) {
								String tmpArr[] = selectedPartyArr[i].split(",");
								logger.debug("size of temparr" + tmpArr.length);
								if (party.equals(tmpArr[0])) {
									logger.debug("match");
									rDTO.setStatus("Y");
									rDTO.setIsGovtOfficial(tmpArr[1]);
									rDTO.setGovtOfficial(tmpArr[2]);
									rDTO.setReprsnName(tmpArr[3]);
									rDTO.setThumbRemarks(tmpArr[4]);
									break;
								} else {
									rDTO.setStatus("N");
								}
							}
						}
						eForm.setPartiesPresentList(partyPresentList);
						/*
						 * govtLetterUploadMap = eForm.getUploadGovtLetter();
						 * if(govtLetterUploadMap.size()!= 0 ) { String fileName
						 * = "";
						 * 
						 * Set mapSet = (Set)govtLetterUploadMap.entrySet();
						 * Iterator mapIterator = mapSet.iterator();
						 * while(mapIterator.hasNext()) { Map.Entry mapEntry =
						 * (Map.Entry)mapIterator.next(); RegCompCheckerDTO rDTO
						 * = (RegCompCheckerDTO)mapEntry.getValue(); String
						 * partyId = (String)mapEntry.getKey(); //
						 * logger.debug("party Id in Upload"+partyId);
						 * if(rDTO.getGovtOffLetterContents() != null) {
						 * filePath = uploadFile(formDTO.getRegInitNumber(),
						 * rDTO.getGovtOffLetterContents(),partyId,
						 * RegCompCheckerConstant.UPLOAD_PATH_GOVT_LETTER,
						 * RegCompCheckerConstant.FILE_NAME_GOVT_LETTER,"3");
						 * if(!filePath.equals(null)) {
						 * rDTO.setGovtOffLetterPath(filePath); } } if(filePath
						 * == null) { messages.add("MSG1", new ActionMessage(
						 * "upload_error")); saveMessages(request, messages);
						 * eForm.setErrLnkFlg("U"); forwardJsp =
						 * RegCompCheckerConstant.PRESENT_PARTIES_VIEW; }
						 * 
						 * } }
						 */
						// if(formDTO.getPnaltyReceiptContents() != null)
						// {
						// filePath = uploadFile(formDTO.getRegInitNumber(),
						// formDTO.getPnaltyReceiptContents(),RegCompCheckerConstant.UPLOAD_PATH_PNALTY_RCPT,
						// RegCompCheckerConstant.FILE_NAME_PNALTY_RCPT);
						// if(filePath != null)
						// {
						// formDTO.setPnaltyReceiptPath(filePath);
						if (formDTO.getComplaint() != null) {
							if (formDTO.getComplaint().equals("Y")) {
								boolean updateData = bd.modifyTimeBarrdCaseDetails(formDTO, userId, cdate);
								if (updateData) {
									logger.debug("PENALTY RECEIPT UPLOADED AND DATA INSERTED");
								} else {
									logger.debug("INSERTION OF PENALTY RECEIPT FAILED");
								}
							}
						}
						// }
						boolean partyModify = bd.modifyPartiesPresentDetails(formDTO.getRegInitNumber(), eForm.getPartiesPresentList(), eForm.getUploadGovtLetter(), userId, cdate);
						// boolean sealDataInsertion =
						// bd.insertSealsData(formDTO.getRegInitNumber(),
						// formDTO.getSealId(), formDTO.getSealSubTypeId());
						logger.debug("PARTIES MODIFIED-->" + partyModify);
						// logger.debug("SEALS-->"+sealDataInsertion);
						/*
						 * boolean srSignInsertion =
						 * bd.insertSrSignDetails(formDTO, userId);
						 * logger.debug("srSignInsertion-->"+srSignInsertion);
						 */
						logger.debug("NEXT TO COMPLIANCE LIST VIEW");
						formDTO.setSeal("");
						formDTO.setSealId("");
						formDTO.setSealSubTypeId("");
						String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
						if (deedInstArray != null && deedInstArray.length > 0) {
							// logger.debug("************"+deedInstArray[0].trim());
							request.setAttribute("deedId", deedInstArray[0].trim());
							request.setAttribute("instId", deedInstArray[1].trim());
						}
						propertyRelatedComplianceMap = bd.getPropertyRelatedComplianeList(formDTO.getRegInitNumber());
						eForm.setCompliancePropertyRelatedMap(propertyRelatedComplianceMap);
						partyRelatedComplianceMap = bd.getPartyRelatedComplianeList(formDTO.getRegInitNumber(), language);
						eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
						witnessPhotoUploadMap = bd.getWitnessDetailsForCompliance(formDTO.getRegInitNumber(), language);
						eForm.setUploadWitnessPhotograph(witnessPhotoUploadMap);
						/*
						 * ArrayList complianceList =
						 * bd.getComplianceDetails(formDTO.getRegInitNumber());
						 * if(complianceList.size() != 0) {
						 * logger.debug("Compliance list---->");
						 * eForm.setComplianceListDisp(complianceList); } else {
						 * logger.debug("Compliance list empty"); } ArrayList
						 * partyDetails =
						 * bd.getPartyDet(formDTO.getRegInitNumber());
						 * if(partyDetails.size() != 0) {
						 * logger.debug("party list");
						 * eForm.setPartiesDetailList(partyDetails); } else {
						 * logger.debug("party list is empty"); } ArrayList
						 * witnessList =
						 * bd.getWitnessDet(formDTO.getRegInitNumber());
						 * if(witnessList.size()!= 0) {
						 * logger.debug("witnessList");
						 * eForm.setWitnessDetailsList(witnessList); } else {
						 * logger.debug("witness list is empty"); }
						 */
						// boolean flag =
						// bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(),
						// RegCompCheckerConstant.COMPLIANCE_LIST_STATUS,cdate,userId);
						request.setAttribute("page", "sealForm1");
						request.setAttribute("regNumber", formDTO.getRegInitNumber());
						// request.setAttribute("regForm",eForm );
						request.setAttribute("sealId", "1");
						request.setAttribute("partyPresentArray", formDTO.getSelectedPartyIds());
						request.setAttribute("headerImagePath", eForm.getHeaderImg());
						request.setAttribute("backPage", "presentation");
						forwardJsp = "forwardToSeal";
						// forwardJsp =
						// RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
					}
				}
				if (RegCompCheckerConstant.NEXT_TO_COMPLIANCE_LIST.equalsIgnoreCase(actionName)) {
					if (formDTO.getIsMplr() != null && formDTO.getIsMplr().equalsIgnoreCase("no")) {
						formDTO.setHoldPageId("10");
						String hldFlag = "Y";
						String createdBy = userId;
						Date date = new Date();
						System.out.println(date);
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						System.out.println(sdf.format(date));
						String Date = sdf.format(date);
						String fwdPage = "viewPresentParties";
						boolean flag2 = bd.isHoldData(formDTO.getRegInitNumber(), fwdPage);
						boolean holdData = bd.saveHoldDataChecker(formDTO, hldFlag, fwdPage, Date, createdBy, flag2, officeId, "", "");
						// End=== this code for saving the data in the hold
						// table
						if (holdData) {
							String status = bd.emailAlertHold(formDTO.getRegInitNumber(), officeId);
							logger.debug("email status----->" + status);
							forwardJsp = "CONFIRMATION_SCREEN";
						}
						// request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD,
						// "The Application has been put on hold Successfully ");
						if ("en".equalsIgnoreCase(language)) {
							request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "The Application has been put on hold Successfully. ");
						} else {
							request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "आवेदन सफलतापूर्वक होल्ड पर रख दिया गया है |");
						}
						forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
					}
					// else
					// {
					ArrayList partyPresentList = eForm.getPartiesPresentList();
					String selectedPartyArr[] = null;
					if (formDTO.getSelectedPartyIds() != null && !formDTO.getSelectedPartyIds().equalsIgnoreCase("")) {
						selectedPartyArr = formDTO.getSelectedPartyIds().split("_");
					}
					if (selectedPartyArr == null || selectedPartyArr.length == 0) {
						if (bd.checkImagesPresentation(formDTO.getRegInitNumber())) {
							String partyprsent = bd.getPresentationSingleParty(formDTO.getRegInitNumber());
							formDTO.setSelectedPartyIds(partyprsent);
						} else {
							ArrayList error = new ArrayList();
							error.add("Please capture siganture and thumb impression for atleast one party");
							request.setAttribute("ERROR_LIST", error);
							request.setAttribute("ERROR_FLAG", "true");
							forwardJsp = "viewPresentParties";
							return mapping.findForward(forwardJsp);
						}
					} else {
						if (bd.checkImagesPresentationSelected(selectedPartyArr)) {} else {
							ArrayList error = new ArrayList();
							error.add("Please capture siganture and thumb impression for Selected parties");
							request.setAttribute("ERROR_LIST", error);
							request.setAttribute("ERROR_FLAG", "true");
							forwardJsp = "viewPresentParties";
							return mapping.findForward(forwardJsp);
						}
					}
					// Iterator itr = partyPresentList.iterator();
					// ArrayList partyDetails = new ArrayList();
					/*
					 * while(itr.hasNext()) { logger.debug("inside while");
					 * RegCompCheckerDTO rDTO = (RegCompCheckerDTO)itr.next();
					 * String party = rDTO.getPartyTxnId(); //
					 * logger.debug("party"+party);
					 * logger.debug("selectedpart arr"+selectedPartyArr.length);
					 * for(int i = 0; i<selectedPartyArr.length;i++) { String
					 * tmpArr[] = selectedPartyArr[i].split(",");
					 * logger.debug("size of temparr"+tmpArr.length);
					 * if(party.equals(tmpArr[0])) { logger.debug("match");
					 * rDTO.setStatus("Y"); rDTO.setIsGovtOfficial(tmpArr[1]);
					 * rDTO.setGovtOfficial(tmpArr[2]);
					 * rDTO.setReprsnName(tmpArr[3]);
					 * rDTO.setThumbRemarks(tmpArr[4]);
					 * 
					 * break; } else { rDTO.setStatus("N"); } }
					 * 
					 * }
					 */
					eForm.setPartiesPresentList(partyPresentList);
					/*
					 * govtLetterUploadMap = eForm.getUploadGovtLetter();
					 * if(govtLetterUploadMap.size()!= 0 ) { String fileName =
					 * "";
					 * 
					 * Set mapSet = (Set)govtLetterUploadMap.entrySet();
					 * Iterator mapIterator = mapSet.iterator();
					 * while(mapIterator.hasNext()) { Map.Entry mapEntry =
					 * (Map.Entry)mapIterator.next(); RegCompCheckerDTO rDTO =
					 * (RegCompCheckerDTO)mapEntry.getValue(); String partyId =
					 * (String)mapEntry.getKey(); //
					 * logger.debug("party Id in Upload"+partyId);
					 * if(rDTO.getGovtOffLetterContents() != null) { filePath =
					 * uploadFile(formDTO.getRegInitNumber(),
					 * rDTO.getGovtOffLetterContents
					 * (),partyId,RegCompCheckerConstant
					 * .UPLOAD_PATH_GOVT_LETTER,
					 * RegCompCheckerConstant.FILE_NAME_GOVT_LETTER,"3");
					 * if(!filePath.equals(null)) {
					 * rDTO.setGovtOffLetterPath(filePath); } } if(filePath ==
					 * null) { messages.add("MSG1", new ActionMessage(
					 * "upload_error")); saveMessages(request, messages);
					 * eForm.setErrLnkFlg("U"); forwardJsp =
					 * RegCompCheckerConstant.PRESENT_PARTIES_VIEW; }
					 * 
					 * } }
					 */
					// if(formDTO.getPnaltyReceiptContents() != null)
					// {
					// filePath = uploadFile(formDTO.getRegInitNumber(),
					// formDTO.getPnaltyReceiptContents(),RegCompCheckerConstant.UPLOAD_PATH_PNALTY_RCPT,
					// RegCompCheckerConstant.FILE_NAME_PNALTY_RCPT);
					// if(filePath != null)
					// {
					// formDTO.setPnaltyReceiptPath(filePath);
					if (formDTO.getComplaint() != null) {
						if (formDTO.getComplaint().equals("Y")) {
							boolean updateData = bd.modifyTimeBarrdCaseDetails(formDTO, userId, cdate);
							if (updateData) {
								logger.debug("PENALTY RECEIPT UPLOADED AND DATA INSERTED");
							} else {
								logger.debug("INSERTION OF PENALTY RECEIPT FAILED");
							}
						}
					}
					// }
					// boolean partyModify =
					// bd.modifyPartiesPresentDetails(formDTO.getRegInitNumber(),
					// eForm.getPartiesPresentList(),eForm.getUploadGovtLetter(),userId,cdate);
					// boolean sealDataInsertion =
					// bd.insertSealsData(formDTO.getRegInitNumber(),
					// formDTO.getSealId(), formDTO.getSealSubTypeId());
					// logger.debug("PARTIES MODIFIED-->"+partyModify);
					// logger.debug("SEALS-->"+sealDataInsertion);
					// boolean srSignInsertion = bd.insertSrSignDetails(formDTO,
					// userId);
					// logger.debug("srSignInsertion-->"+srSignInsertion);
					logger.debug("NEXT TO COMPLIANCE LIST VIEW");
					formDTO.setSeal("");
					formDTO.setSealId("");
					formDTO.setSealSubTypeId("");
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					if (deedInstArray != null && deedInstArray.length > 0) {
						// logger.debug("************"+deedInstArray[0].trim());
						request.setAttribute("deedId", deedInstArray[0].trim());
						request.setAttribute("instId", deedInstArray[1].trim());
					}
					propertyRelatedComplianceMap = bd.getPropertyRelatedComplianeList(formDTO.getRegInitNumber());
					eForm.setCompliancePropertyRelatedMap(propertyRelatedComplianceMap);
					partyRelatedComplianceMap = bd.getPartyRelatedComplianeList(formDTO.getRegInitNumber(), language);
					eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
					witnessPhotoUploadMap = bd.getWitnessDetailsForCompliance(formDTO.getRegInitNumber(), language);
					eForm.setUploadWitnessPhotograph(witnessPhotoUploadMap);
					/*
					 * ArrayList complianceList =
					 * bd.getComplianceDetails(formDTO.getRegInitNumber());
					 * if(complianceList.size() != 0) {
					 * logger.debug("Compliance list---->");
					 * eForm.setComplianceListDisp(complianceList); } else {
					 * logger.debug("Compliance list empty"); } ArrayList
					 * partyDetails =
					 * bd.getPartyDet(formDTO.getRegInitNumber());
					 * if(partyDetails.size() != 0) {
					 * logger.debug("party list");
					 * eForm.setPartiesDetailList(partyDetails); } else {
					 * logger.debug("party list is empty"); } ArrayList
					 * witnessList =
					 * bd.getWitnessDet(formDTO.getRegInitNumber());
					 * if(witnessList.size()!= 0) { logger.debug("witnessList");
					 * eForm.setWitnessDetailsList(witnessList); } else {
					 * logger.debug("witness list is empty"); }
					 */
					// boolean flag =
					// bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(),
					// RegCompCheckerConstant.COMPLIANCE_LIST_STATUS,cdate,userId);
					request.setAttribute("page", "sealForm1");
					request.setAttribute("regNumber", formDTO.getRegInitNumber());
					// request.setAttribute("regForm",eForm );
					request.setAttribute("sealId", "1");
					request.setAttribute("partyPresentArray", formDTO.getSelectedPartyIds());
					request.setAttribute("headerImagePath", eForm.getHeaderImg());
					request.setAttribute("backPage", "presentation");
					forwardJsp = "forwardToSeal";
					// forwardJsp =
					// RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
					// }
				}
				if (RegCompCheckerConstant.NEXT_TO_CHECK_LIST.equalsIgnoreCase(actionName)) {
					logger.debug("NEXT TO CHECK LIST ACTION");
					// /////******************saving compliance
					// data**************************//
					boolean flag = bd.modifyComplianceListData(eForm, formDTO.getRegInitNumber(), userId, cdate);
					logger.debug("**********Complaince list modified****" + flag);
					// Commented bY Simran - 6 Jan,2014
					// boolean flag1 =
					// bd.addWitnessPhotographDetails(eForm.getUploadWitnessPhotograph(),
					// formDTO.getRegInitNumber(), userId, cdate);
					// logger.debug("**********WitnessList****"+flag1);
					// boolean flag2 =
					// bd.insertSealsData(formDTO.getRegInitNumber(),
					// formDTO.getSealId(), formDTO.getSealSubTypeId());
					// logger.debug("**********execution seal****"+flag2);
					formDTO.setSeal("");
					formDTO.setSealId("");
					formDTO.setSealSubTypeId("");
					// *****************************END**************************************//
					ArrayList caveatList = bd.getCaveatDetails(formDTO.getRegInitNumber());
					ArrayList bankCaveatList = bd.getBnkCaveatDet(formDTO.getRegInitNumber());
					if (caveatList.size() != 0) {
						logger.debug("caveatList");
						eForm.setCaveatDetailsList(caveatList);
					}
					if (bankCaveatList.size() != 0) {
						logger.debug("caveatList");
						eForm.setCaveatBankDetailsList(bankCaveatList);
					}
					/*
					 * else { messages.add("MSG", new ActionMessage(
					 * "no.records.for.caveat")); saveMessages(request,
					 * messages); eForm.setCheck("CV");
					 * request.setAttribute("checkRegID", eForm);
					 * logger.debug("CaveatList is Empty"); }
					 */
					ArrayList checkList = bd.getCheckListDetails(formDTO.getRegInitNumber(), eForm);
					if (checkList.size() != 0) {
						logger.debug("CheckList");
						eForm.setCheckListDetailsList(checkList);
					} else {
						logger.debug("CheckList Empty");
					}
					ArrayList partyDetls = bd.getTransPartyDets(formDTO.getRegInitNumber());
					eForm.setPartyList(partyDetls);
					flag = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), RegCompCheckerConstant.CHECKLIST_STATUS, cdate, userId);
					filePath = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_DEATH_CERT;
					formDTO.setDeathCert(RegCompCheckerConstant.FILE_NAME_DEATH_CERT);
					formDTO.setFilePath(filePath);
					String filePathRelation = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_RELTN_PROOF;
					formDTO.setRelationProof(RegCompCheckerConstant.FILE_NAME_RLTN_PROOF);
					formDTO.setFilePathRltn(filePathRelation);
					formDTO.setFilePathPOA(FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_POA_DOC);
					formDTO.setPoaUploadFilename(RegCompCheckerConstant.FILE_NAME_POA_DOC);
					forwardJsp = RegCompCheckerConstant.VIEW_CHECKLIST;
				}
				if ("NEXT_TO_CHECK_LIST_UNHOLD".equalsIgnoreCase(actionName)) {
					logger.debug("NEXT TO CHECK LIST ACTION");
					// /////******************saving compliance
					// data**************************//
					if (bd.addHoldRemarks(formDTO)) {
						formDTO.setHoldCheck("");
						formDTO.setRemarks("");
						formDTO.setUnholdRemarks("");
						formDTO.setHoldfileName("");
						formDTO.setHoldfilePath("");
						boolean flag = bd.modifyComplianceListData(eForm, formDTO.getRegInitNumber(), userId, cdate);
						logger.debug("**********Complaince list modified****" + flag);
						// boolean flag1 =
						// bd.addWitnessPhotographDetails(eForm.getUploadWitnessPhotograph(),
						// formDTO.getRegInitNumber(), userId, cdate);
						// logger.debug("**********WitnessList****"+flag1);
						// boolean flag2 =
						// bd.insertSealsData(formDTO.getRegInitNumber(),
						// formDTO.getSealId(), formDTO.getSealSubTypeId());
						// logger.debug("**********execution seal****"+flag2);
						formDTO.setSeal("");
						formDTO.setSealId("");
						formDTO.setSealSubTypeId("");
						// *****************************END**************************************//
						ArrayList caveatList = bd.getCaveatDetails(formDTO.getRegInitNumber());
						ArrayList bankCaveatList = bd.getBnkCaveatDet(formDTO.getRegInitNumber());
						if (caveatList.size() != 0) {
							logger.debug("caveatList");
							eForm.setCaveatDetailsList(caveatList);
						}
						if (bankCaveatList.size() != 0) {
							logger.debug("caveatList");
							eForm.setCaveatBankDetailsList(bankCaveatList);
						}
						/*
						 * else { messages.add("MSG", new ActionMessage(
						 * "no.records.for.caveat")); saveMessages(request,
						 * messages); eForm.setCheck("CV");
						 * request.setAttribute("checkRegID", eForm);
						 * logger.debug("CaveatList is Empty"); }
						 */
						ArrayList checkList = bd.getCheckListDetails(formDTO.getRegInitNumber(), eForm);
						if (checkList.size() != 0) {
							logger.debug("CheckList");
							eForm.setCheckListDetailsList(checkList);
						} else {
							logger.debug("CheckList Empty");
						}
						ArrayList partyDetls = bd.getTransPartyDets(formDTO.getRegInitNumber());
						eForm.setPartyList(partyDetls);
						flag = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), RegCompCheckerConstant.CHECKLIST_STATUS, cdate, userId);
						filePath = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_DEATH_CERT;
						formDTO.setDeathCert(RegCompCheckerConstant.FILE_NAME_DEATH_CERT);
						formDTO.setFilePath(filePath);
						String filePathRelation = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_RELTN_PROOF;
						formDTO.setRelationProof(RegCompCheckerConstant.FILE_NAME_RLTN_PROOF);
						formDTO.setFilePathRltn(filePathRelation);
						formDTO.setFilePathPOA(FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_POA_DOC);
						formDTO.setPoaUploadFilename(RegCompCheckerConstant.FILE_NAME_POA_DOC);
						forwardJsp = RegCompCheckerConstant.VIEW_CHECKLIST;
					}
				}
				if (actionName.equalsIgnoreCase(RegCompConstant.CAVEAT_VIEW)) {
					String transactionId = request.getParameter("transactionId");
					if (request.getParameter("type").equalsIgnoreCase("court")) {
						eForm.setCaveatDetailList(bd.getCompleteCaveatDetails(transactionId));
						eForm.setCaveatBankDetailList(new ArrayList());
					} else if (request.getParameter("type").equalsIgnoreCase("bank")) {
						eForm.setCaveatBankDetailList(bd.getBankCaveatDetails(transactionId, language));
						eForm.setCaveatDetailList(new ArrayList());
					} else {
						logger.debug("error in Action Class");
					}
					forwardJsp = RegCompConstant.CAVEAT_DISPLAY;
				}
				if ("CAVEAT_CHECKLIST".equalsIgnoreCase(actionName)) {
					forwardJsp = RegCompCheckerConstant.VIEW_CHECKLIST;
				}
				if ("partyId".equalsIgnoreCase(actionName)) {
					logger.debug("map------>" + eForm.getDeathUploadList().size());
					logger.debug("getting Party  type Ids");
					String partyName = formDTO.getPartyFirstName();
					ArrayList partyIdsList = bd.getTransPartyIds(formDTO.getRegInitNumber(), partyName);
					eForm.setPartyIDList(partyIdsList);
					logger.debug("map------>" + eForm.getDeathUploadList().size());
					forwardJsp = RegCompCheckerConstant.VIEW_CHECKLIST;
				}
				if ("addMoreDeathCert".equalsIgnoreCase(actionName)) {
					logger.debug("addMoreDeathCert");
					String partyId = formDTO.getPartyTxnId();
					ArrayList docs = new ArrayList();
					RegCompCheckerDTO gDTO = new RegCompCheckerDTO();
					// gDTO.setDeatCertContents(formDTO.getDeatCertContents());
					gDTO.setUpldDeathCert(formDTO.getUpldDeathCert());
					// /gDTO.setReltnProofContents(formDTO.getReltnProofContents());
					gDTO.setUplaReltnProof(formDTO.getUplaReltnProof());
					gDTO.setUpldDeathCertPath(formDTO.getUpldDeathCertPath());
					gDTO.setUplaReltnProofPath(formDTO.getUplaReltnProofPath());
					if (formDTO.getComments() == null || formDTO.getComments().equals(""))
						gDTO.setComments("-");
					else
						gDTO.setComments(formDTO.getComments());
					gDTO.setFilePath(formDTO.getFilePath() + "/" + formDTO.getPartyId() + "/" + formDTO.getDeathCert());
					gDTO.setFilePathRltn(formDTO.getFilePathRltn() + "/" + formDTO.getPartyId() + "/" + formDTO.getRelationProof());
					gDTO.setCheck("Y");
					docs.add(gDTO);
					deathUploadList = eForm.getUploadDthList();
					// int count = eForm.getDeathCertCount();
					// if (count == 0) {
					// count = 1;
					// }
					// logger.debug("count"+count);
					// if (deathCertList.containsKey(Integer.toString(count))) {
					// logger.debug("if");
					// count++;
					// deathCertList.put(Integer.toString(count), poa);
					// } else {
					// logger.debug("else");
					deathUploadList.put(partyId, docs);
					// }
					// eForm.setDeathCertCount(count);
					logger.debug("<-----size of deathCertList List" + deathUploadList.size());
					eForm.setUploadDthList(deathUploadList);
					formDTO.setUplaReltnProof("");
					formDTO.setUpldDeathCert("");
					formDTO.setPartyFirstName("");
					formDTO.setPartyTxnId("");
					formDTO.setComments("");
					eForm.setDeathCertChk("Y");
					forwardJsp = RegCompCheckerConstant.VIEW_CHECKLIST;
				}
				if (("addMorePOA").equalsIgnoreCase(actionName)) {
					RegCompCheckerDTO rdto = new RegCompCheckerDTO();
					logger.debug("<-------add more POA");
					if (formDTO.getPoaMpFlg1().equals("1")) {
						rdto.setPoaMpFlg("N");
					} else {
						rdto.setPoaMpFlg("Y");
					}
					rdto.setPoaAuthNo(formDTO.getPoaAuthNo());
					rdto.setFilePathPOA(formDTO.getFilePath() + "/" + formDTO.getPoaAuthNo() + "/" + RegCompCheckerConstant.FILE_NAME_POA_DOC);
					rdto.setPoaUploadFilename(RegCompCheckerConstant.FILE_NAME_POA_DOC);
					rdto.setUploadPoaDocPath(formDTO.getUploadPoaDocPath());
					// rdto.setPoaUploadContents(formDTO.getPoaUploadContents());
					// rdto.setPoaUploadSize(formDTO.getPoaUploadSize());
					rdto.setUploadPoaDoc(RegCompCheckerConstant.FILE_NAME_POA_DOC);
					rdto.setCheck("Y");
					ArrayList poa = new ArrayList();
					poa.add(rdto);
					poaCheckList = eForm.getUploadPOAList();
					int count = eForm.getPoaCount();
					if (count == 0) {
						count = 1;
					}
					poaCheckList.put(formDTO.getPoaAuthNo(), poa);
					// logger.debug("count"+count);
					// if (poaCheckList.containsKey(Integer.toString(count))) {
					// logger.debug("if");
					// count++;
					// poaCheckList.put(Integer.toString(count), poa);
					// } else {
					// logger.debug("else");
					// }
					// eForm.setPoaCount(count);
					logger.debug("<-----size of Poa List" + poaCheckList.size());
					eForm.setUploadPOAList(poaCheckList);
					formDTO.setPoaAuthNo("");
					// formDTO.setTypeOfPoa("");
					formDTO.setUploadPoaDoc("");
					formDTO.setPoaMpFlg("");
					eForm.setPoaChk("Y");
					formDTO.setPoaMpFlg1("");
					forwardJsp = RegCompCheckerConstant.VIEW_CHECKLIST;
				}
				if ("delDthCert".equalsIgnoreCase(actionName)) {
					String dthCertKeys[] = eForm.getDthCertKeys().split(",");
					deathUploadList = eForm.getUploadDthList();
					for (int i = 0; i < dthCertKeys.length; i++) {
						if (deathUploadList.containsKey(dthCertKeys[i])) {
							deathUploadList.remove(dthCertKeys[i]);
						}
					}
					logger.debug("Size of map after deletion" + deathUploadList.size());
					if (deathUploadList.size() == 0) {
						eForm.setDeathCertChk("N");
					}
					eForm.setUploadDthList(deathUploadList);
					forwardJsp = RegCompCheckerConstant.VIEW_CHECKLIST;
				}
				if ("deletePOA".equalsIgnoreCase(actionName)) {
					String poaKeys[] = eForm.getPoaKeys().split(",");
					poaCheckList = eForm.getUploadPOAList();
					for (int i = 0; i < poaKeys.length; i++) {
						if (poaCheckList.containsKey(poaKeys[i])) {
							poaCheckList.remove(poaKeys[i]);
						}
					}
					if (poaCheckList.size() == 0) {
						eForm.setPoaChk("N");
					}
					logger.debug("Size of map after deletion" + poaCheckList.size());
					eForm.setUploadPOAList(poaCheckList);
					forwardJsp = RegCompCheckerConstant.VIEW_CHECKLIST;
				}
				if ("linkPOA".equalsIgnoreCase(actionName)) {
					logger.debug("link POA");
					formDTO.setPoaAuthNo(request.getParameter("txt"));
					boolean isPOA = bd.checkPOA(formDTO.getPoaAuthNo(), formDTO.getRegInitNumber());
					if (isPOA) {
						POAAuthenticationFormBO poaBO = new POAAuthenticationFormBO();
						POAAuthenticationForm poaForm = new POAAuthenticationForm();
						String poaId = formDTO.getPoaAuthNo();
						// logger.debug("POAID******************"+poaId);
						ArrayList poaApplicantDetls = poaBO.getPOAApplicantDetlsForView(poaId);
						poaForm.setPoaApplicntDetlsList(poaApplicantDetls);
						ArrayList poaAwardeeDetls = poaBO.getPOAAwardeeDetlsForView(poaId);
						poaForm.setPoaAwardeeDetlsList(poaAwardeeDetls);
						ArrayList poaAcceptorDetls = poaBO.getPOAAcceptorDetlsForView(poaId);
						poaForm.setPoaAcceptorList(poaAcceptorDetls);
						ArrayList poaCommonDetls = poaBO.getPOACommonDetlsForView(poaId);
						poaForm.setPoaCommonDetlsList(poaCommonDetls);
						poaForm.setActionType("");
						poaForm.setPoaAuthNo(formDTO.getPoaAuthNo());
						request.setAttribute("poaForm", poaForm);
						forwardJsp = "poaListView2";
					} else {
						/*
						 * logger.debug("in else----link POA");
						 * messages.add("MSG", new ActionMessage(
						 * "link_poa_error")); saveMessages(request, messages);
						 * eForm.setErrLnkFlg("E");
						 */
						POAAuthenticationForm poaForm = new POAAuthenticationForm();
						poaForm.setActionType("error");
						poaForm.setPoaAuthNo("");
						request.setAttribute("poaForm", poaForm);
						forwardJsp = "poaListView2";
					}
				}
				if ("NEXT_TO_PAYMENT_UNHOLD".equalsIgnoreCase(actionName)) {
					if (bd.addHoldRemarks(formDTO)) {
						formDTO.setRemarks("");
						formDTO.setHoldCheck("");
						formDTO.setHoldfileName("");
						formDTO.setHoldfilePath("");
						logger.debug("NEXT TO PAYMENT ACTION");
						logger.debug("FLAG" + formDTO.getExecOutIndiaFlag());
						String partyID = formDTO.getPartyTxnId();
						deathUploadList = eForm.getUploadDthList();
						poaCheckList = eForm.getUploadPOAList();
						if (!(partyID.equals(null) || (partyID.equals("")))) {
							ArrayList docs = new ArrayList();
							RegCompCheckerDTO gDTO = new RegCompCheckerDTO();
							// gDTO.setDeatCertContents(formDTO.getDeatCertContents());
							// gDTO.setUpldDeathCert(formDTO.getUpldDeathCert());
							// gDTO.setReltnProofContents(formDTO.getReltnProofContents());
							// gDTO.setUplaReltnProof(formDTO.getUplaReltnProof());
							gDTO.setComments(formDTO.getComments());
							gDTO.setFilePath(formDTO.getFilePath() + "/" + formDTO.getPartyId() + "/" + formDTO.getDeathCert());
							gDTO.setFilePathRltn(formDTO.getFilePathRltn() + "/" + formDTO.getPartyId() + "/" + formDTO.getRelationProof());
							gDTO.setCheck("Y");
							docs.add(gDTO);
							deathUploadList.put(partyID, docs);
							eForm.setUploadDthList(deathUploadList);
						}
						String authNo = formDTO.getPoaAuthNo();
						if (!(authNo.equals(null) || authNo.equals(""))) {
							RegCompCheckerDTO rdto = new RegCompCheckerDTO();
							logger.debug("<-------add more POA");
							if (formDTO.getPoaMpFlg1().equals("1")) {
								rdto.setPoaMpFlg("N");
							} else {
								rdto.setPoaMpFlg("Y");
							}
							rdto.setPoaAuthNo(formDTO.getPoaAuthNo());
							rdto.setFilePathPOA(formDTO.getFilePathPOA() + "/" + formDTO.getPoaAuthNo() + "/" + formDTO.getUploadPoaDoc());
							// rdto.setPoaUploadContents(formDTO.getPoaUploadContents());
							// rdto.setPoaUploadSize(formDTO.getPoaUploadSize());
							rdto.setUploadPoaDoc(formDTO.getUploadPoaDoc());
							rdto.setCheck("Y");
							ArrayList poa = new ArrayList();
							poa.add(rdto);
							int count = eForm.getPoaCount();
							if (count == 0) {
								count = 1;
							}
							poaCheckList.put(formDTO.getPoaAuthNo(), poa);
							eForm.setUploadPOAList(poaCheckList);
						}
						// *******************ADDED BY
						// SIMRAN*******************//
						/*
						 * deathUploadList = new HashMap();
						 * 
						 * deathUploadList = eForm.getUploadDthList();
						 * formDTO.setDocAftrDeathFlg("");
						 * if(deathUploadList.size()!= 0 ) { String fileName =
						 * ""; formDTO.setDocAftrDeathFlg("Y"); Set mapSet =
						 * (Set)deathUploadList.entrySet(); Iterator mapIterator
						 * = mapSet.iterator(); while(mapIterator.hasNext()) {
						 * Map.Entry mapEntry = (Map.Entry)mapIterator.next();
						 * ArrayList valueList = (ArrayList)mapEntry.getValue();
						 * RegCompCheckerDTO rDTO =
						 * (RegCompCheckerDTO)valueList.get(0); String partyId =
						 * (String)mapEntry.getKey(); //
						 * logger.debug("party Id in Upload"+partyId); //
						 * logger.
						 * debug("party Id in Upload"+rDTO.getFilePath());
						 * if(rDTO.getCheck().equals("Y")) {
						 * if(rDTO.getDeatCertContents() != null) { fileName =
						 * uploadFile(formDTO.getRegInitNumber(),
						 * rDTO.getDeatCertContents
						 * (),partyId,RegCompConstant.UPLOAD_PATH_DEATH_CERT,
						 * RegCompConstant.FILE_NAME_DTH_CERTIFICATE,"2"); //
						 * logger.debug("file Path in Upload"+fileName);
						 * if(fileName != null && rDTO.getReltnProofContents()
						 * != null) { rDTO.setFilePath(fileName); fileName =
						 * uploadFile(formDTO.getRegInitNumber(),
						 * rDTO.getReltnProofContents
						 * (),partyId,RegCompConstant.UPLOAD_PATH_RELN_PROOF,
						 * RegCompConstant.FILE_NAME_RELTN_PROOF,"2");
						 * if(fileName!= null) { rDTO.setFilePathRltn(fileName);
						 * // logger.debug("file Path in Upload"+fileName); } }
						 * } }
						 * 
						 * if(fileName == null) { messages.add("MSG1", new
						 * ActionMessage( "upload_error"));
						 * saveMessages(request, messages);
						 * eForm.setErrLnkFlg("U"); forwardJsp = "checklist"; }
						 * } }
						 * 
						 * poaCheckList = new HashMap(); poaCheckList =
						 * (Map)eForm.getUploadPOAList();
						 * formDTO.setPoaFlag(""); if(poaCheckList.size()!= 0 )
						 * { formDTO.setPoaFlag("Y"); String fileName = "";
						 * 
						 * Set mapSet = (Set)poaCheckList.entrySet(); Iterator
						 * mapIterator = mapSet.iterator();
						 * while(mapIterator.hasNext()) { Map.Entry mapEntry =
						 * (Map.Entry)mapIterator.next(); ArrayList valueList =
						 * (ArrayList)mapEntry.getValue(); RegCompCheckerDTO
						 * rDTO = (RegCompCheckerDTO)valueList.get(0); String
						 * poaAuthNo = rDTO.getPoaAuthNo();
						 * if(rDTO.getCheck().equals("Y")) {
						 * if(rDTO.getPoaUploadContents() != null) { fileName =
						 * uploadFile(formDTO.getRegInitNumber(),
						 * rDTO.getPoaUploadContents
						 * (),poaAuthNo,RegCompConstant.UPLOAD_PATH_POA_DOC,
						 * RegCompConstant.FILE_NAME_POA_DOC,"2");
						 * 
						 * if(fileName != null) { rDTO.setFilePathPOA(fileName);
						 * } } } if(fileName == null) { messages.add("MSG1", new
						 * ActionMessage( "upload_error"));
						 * saveMessages(request, messages);
						 * eForm.setErrLnkFlg("U"); forwardJsp =
						 * RegCompCheckerConstant.VIEW_CHECKLIST; } } }
						 */
						boolean dataModified = bd.modifyCheckListDetails(formDTO, userId, cdate, eForm.getUploadDthList(), eForm.getUploadPOAList());
						if (dataModified) {
							logger.debug("CHECKLIST DATA MODIFIED");
							Double paidAmtAtChecker = bd.getPaidAmtChecker(formDTO.getRegInitNumber(), sourceModFlag, "1");
							ArrayList hmap = bd.getFinalDutiesForPayment(formDTO.getRegInitNumber(), eForm);
							ArrayList netBalance = eForm.getLinkingDutiesDisp();
							Iterator itr = netBalance.iterator();
							while (itr.hasNext()) {
								RegCompCheckerDTO rgDTO = (RegCompCheckerDTO) itr.next();
								formDTO.setNetStampDuty(rgDTO.getStampDutyPmt());
								formDTO.setNetRegFee(rgDTO.getRegFeePmt());
								// logger.debug("net stamp duty"+formDTO.getNetStampDuty());
								// logger.debug("net reg fee"+formDTO.getNetRegFee());
							}
							double netStmp = Double.parseDouble(formDTO.getNetStampDuty());
							double netRegFee = Double.parseDouble(formDTO.getNetRegFee());
							formDTO.setNetPaidAmt(paidAmtAtChecker.toString());
							Double bal = netStmp - paidAmtAtChecker;
							// logger.debug("Amount Paid"+paidAmtAtChecker);
							// logger.debug("Bal"+bal);
							formDTO.setNetBalAmt(BigDecimal.valueOf(bal).toPlainString());
							if (bal <= 0) {
								// if(netRegFee <= 0)
								// {
								String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
								formDTO.setParentPathSign(path);
								formDTO.setFileNameSign("signature.GIF");
								formDTO.setForwardPath("/regCompChecker.do?txnPartyIdBiometric=");
								formDTO.setForwardName("regCompChecker");
								String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
								formDTO.setFileNameFP("FingerPrint.GIF");
								formDTO.setParentPathFP(pathFP);
								ArrayList partyDetails = bd.getPartyDet(formDTO.getRegInitNumber(), language);
								eForm.setPartiesDetailList(partyDetails);
								ArrayList witnessList = bd.getWitnessDet(formDTO.getRegInitNumber(), language);
								eForm.setWitnessDetailsList(witnessList);
								logger.debug("<-------SIZE" + eForm.getPartiesDetailList().size());
								ArrayList consenterList = bd.getConsenterDet(formDTO.getRegInitNumber(), language);
								eForm.setConsenterDetailsList(consenterList);
								if (consenterList != null && consenterList.size() > 0)
									formDTO.setCnsntrChk("Y");
								else
									formDTO.setCnsntrChk("N");
								boolean flag = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), RegCompCheckerConstant.BIOMETRIC_DETAILS_STATUS, cdate, userId);
								logger.debug("PARTIES_BIOMETRIC_DETAILS ......5");
								forwardJsp = RegCompCheckerConstant.PARTIES_BIOMETRIC_DETAILS;
							}
							// else
							// {
							// formDTO.setRegFeePmt("Y");
							// forwardJsp =
							// RegCompCheckerConstant.PROCEED_TO_PAYMENT;
							// }
							// }
							else {
								formDTO.setStampDutyPmt("Y");
								forwardJsp = RegCompCheckerConstant.PROCEED_TO_PAYMENT;
							}
						} else {
							logger.debug("data not modified");
						}
						logger.debug("FLAG" + formDTO.getCourtOrderFlag());
					}
				}
				if (RegCompCheckerConstant.NEXT_TO_PAYMENT.equalsIgnoreCase(actionName)) {
					logger.debug("NEXT TO PAYMENT ACTION");
					logger.debug("FLAG" + formDTO.getExecOutIndiaFlag());
					String partyID = formDTO.getPartyTxnId();
					if (!(partyID.equals(null) || (partyID.equals("")))) {
						ArrayList docs = new ArrayList();
						RegCompCheckerDTO gDTO = new RegCompCheckerDTO();
						// gDTO.setDeatCertContents(formDTO.getDeatCertContents());
						// gDTO.setUpldDeathCert(formDTO.getUpldDeathCert());
						// gDTO.setReltnProofContents(formDTO.getReltnProofContents());
						// gDTO.setUplaReltnProof(formDTO.getUplaReltnProof());
						gDTO.setComments(formDTO.getComments());
						gDTO.setFilePath(formDTO.getFilePath() + "/" + formDTO.getPartyId() + "/" + formDTO.getDeathCert());
						gDTO.setFilePathRltn(formDTO.getFilePathRltn() + "/" + formDTO.getPartyId() + "/" + formDTO.getRelationProof());
						gDTO.setCheck("Y");
						docs.add(gDTO);
						deathUploadList = eForm.getUploadDthList();
						deathUploadList.put(partyID, docs);
						eForm.setUploadDthList(deathUploadList);
					}
					String authNo = formDTO.getPoaAuthNo();
					if (!(authNo.equals(null) || authNo.equals(""))) {
						RegCompCheckerDTO rdto = new RegCompCheckerDTO();
						logger.debug("<-------add more POA");
						if (formDTO.getPoaMpFlg1().equals("1")) {
							rdto.setPoaMpFlg("N");
						} else {
							rdto.setPoaMpFlg("Y");
						}
						rdto.setPoaAuthNo(formDTO.getPoaAuthNo());
						rdto.setFilePathPOA(formDTO.getFilePathPOA() + "/" + formDTO.getPoaAuthNo() + "/" + formDTO.getUploadPoaDoc());
						// rdto.setPoaUploadContents(formDTO.getPoaUploadContents());
						// rdto.setPoaUploadSize(formDTO.getPoaUploadSize());
						rdto.setUploadPoaDoc(formDTO.getUploadPoaDoc());
						rdto.setCheck("Y");
						ArrayList poa = new ArrayList();
						poa.add(rdto);
						poaCheckList = eForm.getUploadPOAList();
						int count = eForm.getPoaCount();
						if (count == 0) {
							count = 1;
						}
						poaCheckList.put(formDTO.getPoaAuthNo(), poa);
						eForm.setUploadPOAList(poaCheckList);
					}
					// *******************ADDED BY SIMRAN*******************//
					/*
					 * deathUploadList = new HashMap();
					 * 
					 * deathUploadList = eForm.getUploadDthList();
					 * formDTO.setDocAftrDeathFlg("");
					 * if(deathUploadList.size()!= 0 ) { String fileName = "";
					 * formDTO.setDocAftrDeathFlg("Y"); Set mapSet =
					 * (Set)deathUploadList.entrySet(); Iterator mapIterator =
					 * mapSet.iterator(); while(mapIterator.hasNext()) {
					 * Map.Entry mapEntry = (Map.Entry)mapIterator.next();
					 * ArrayList valueList = (ArrayList)mapEntry.getValue();
					 * RegCompCheckerDTO rDTO =
					 * (RegCompCheckerDTO)valueList.get(0); String partyId =
					 * (String)mapEntry.getKey(); //
					 * logger.debug("party Id in Upload"+partyId); //
					 * logger.debug("party Id in Upload"+rDTO.getFilePath());
					 * if(rDTO.getCheck().equals("Y")) {
					 * if(rDTO.getDeatCertContents() != null) { fileName =
					 * uploadFile(formDTO.getRegInitNumber(),
					 * rDTO.getDeatCertContents
					 * (),partyId,RegCompConstant.UPLOAD_PATH_DEATH_CERT,
					 * RegCompConstant.FILE_NAME_DTH_CERTIFICATE,"2"); //
					 * logger.debug("file Path in Upload"+fileName); if(fileName
					 * != null && rDTO.getReltnProofContents() != null) {
					 * rDTO.setFilePath(fileName); fileName =
					 * uploadFile(formDTO.getRegInitNumber(),
					 * rDTO.getReltnProofContents
					 * (),partyId,RegCompConstant.UPLOAD_PATH_RELN_PROOF,
					 * RegCompConstant.FILE_NAME_RELTN_PROOF,"2"); if(fileName!=
					 * null) { rDTO.setFilePathRltn(fileName); //
					 * logger.debug("file Path in Upload"+fileName); } } } }
					 * 
					 * if(fileName == null) { messages.add("MSG1", new
					 * ActionMessage( "upload_error")); saveMessages(request,
					 * messages); eForm.setErrLnkFlg("U"); forwardJsp =
					 * "checklist"; } } }
					 * 
					 * poaCheckList = new HashMap(); poaCheckList =
					 * (Map)eForm.getUploadPOAList(); formDTO.setPoaFlag("");
					 * if(poaCheckList.size()!= 0 ) { formDTO.setPoaFlag("Y");
					 * String fileName = "";
					 * 
					 * Set mapSet = (Set)poaCheckList.entrySet(); Iterator
					 * mapIterator = mapSet.iterator();
					 * while(mapIterator.hasNext()) { Map.Entry mapEntry =
					 * (Map.Entry)mapIterator.next(); ArrayList valueList =
					 * (ArrayList)mapEntry.getValue(); RegCompCheckerDTO rDTO =
					 * (RegCompCheckerDTO)valueList.get(0); String poaAuthNo =
					 * rDTO.getPoaAuthNo(); if(rDTO.getCheck().equals("Y")) {
					 * if(rDTO.getPoaUploadContents() != null) { fileName =
					 * uploadFile(formDTO.getRegInitNumber(),
					 * rDTO.getPoaUploadContents
					 * (),poaAuthNo,RegCompConstant.UPLOAD_PATH_POA_DOC,
					 * RegCompConstant.FILE_NAME_POA_DOC,"2");
					 * 
					 * if(fileName != null) { rDTO.setFilePathPOA(fileName); } }
					 * } if(fileName == null) { messages.add("MSG1", new
					 * ActionMessage( "upload_error")); saveMessages(request,
					 * messages); eForm.setErrLnkFlg("U"); forwardJsp =
					 * RegCompCheckerConstant.VIEW_CHECKLIST; } } }
					 */
					boolean dataModified = bd.modifyCheckListDetails(formDTO, userId, cdate, eForm.getUploadDthList(), eForm.getUploadPOAList());
					if (dataModified) {
						logger.debug("CHECKLIST DATA MODIFIED");
						Double paidAmtAtChecker = bd.getPaidAmtChecker(formDTO.getRegInitNumber(), sourceModFlag, "1");
						ArrayList hmap = bd.getFinalDutiesForPayment(formDTO.getRegInitNumber(), eForm);
						ArrayList netBalance = eForm.getLinkingDutiesDisp();
						Iterator itr = netBalance.iterator();
						while (itr.hasNext()) {
							RegCompCheckerDTO rgDTO = (RegCompCheckerDTO) itr.next();
							formDTO.setNetStampDuty(rgDTO.getStampDutyPmt());
							formDTO.setNetRegFee(rgDTO.getRegFeePmt());
							// logger.debug("net stamp duty"+formDTO.getNetStampDuty());
							// logger.debug("net reg fee"+formDTO.getNetRegFee());
						}
						double netStmp = Double.parseDouble(formDTO.getNetStampDuty());
						double netRegFee = Double.parseDouble(formDTO.getNetRegFee());
						formDTO.setNetPaidAmt(paidAmtAtChecker.toString());
						Double bal = netStmp - paidAmtAtChecker;
						// logger.debug("Amount Paid"+paidAmtAtChecker);
						// logger.debug("Bal"+bal);
						formDTO.setNetBalAmt(BigDecimal.valueOf(bal).toPlainString());
						if (bal <= 0) {
							// if(netRegFee <= 0)
							// {
							String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
							formDTO.setParentPathSign(path);
							formDTO.setFileNameSign("signature.GIF");
							formDTO.setForwardPath("/regCompChecker.do?txnPartyIdBiometric=");
							formDTO.setForwardName("regCompChecker");
							String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
							formDTO.setFileNameFP("FingerPrint.GIF");
							formDTO.setParentPathFP(pathFP);
							ArrayList partyDetails = bd.getPartyDet(formDTO.getRegInitNumber(), language);
							eForm.setPartiesDetailList(partyDetails);
							ArrayList witnessList = bd.getWitnessDet(formDTO.getRegInitNumber(), language);
							eForm.setWitnessDetailsList(witnessList);
							logger.debug("<-------SIZE" + eForm.getPartiesDetailList().size());
							ArrayList consenterList = bd.getConsenterDet(formDTO.getRegInitNumber(), language);
							eForm.setConsenterDetailsList(consenterList);
							if (consenterList != null && consenterList.size() > 0)
								formDTO.setCnsntrChk("Y");
							else
								formDTO.setCnsntrChk("N");
							boolean flag = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), RegCompCheckerConstant.BIOMETRIC_DETAILS_STATUS, cdate, userId);
							logger.debug("PARTIES_BIOMETRIC_DETAILS ......6");
							forwardJsp = RegCompCheckerConstant.PARTIES_BIOMETRIC_DETAILS;
						}
						// else
						// {
						// formDTO.setRegFeePmt("Y");
						// forwardJsp =
						// RegCompCheckerConstant.PROCEED_TO_PAYMENT;
						// }
						// }
						else {
							formDTO.setStampDutyPmt("Y");
							formDTO.setParentFuncID("FUN_023");
							boolean hold = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), "45", cdate, userId);
							forwardJsp = RegCompCheckerConstant.PROCEED_TO_PAYMENT;
						}
					} else {
						logger.debug("data not modified");
					}
					logger.debug("FLAG" + formDTO.getCourtOrderFlag());
				}
				if (RegCompCheckerConstant.PAY_LATER.equals(actionName)) {
					/*
					 * String hldFlag = "Y"; String fwdPage
					 * =RegCompCheckerConstant.PROCEED_TO_PAYMENT;
					 * formDTO.setHoldPageId("6"); Date date = new Date();
					 * System.out.println(date); SimpleDateFormat sdf = new
					 * SimpleDateFormat("dd/MM/yyyy");
					 * System.out.println(sdf.format(date)); String Date =
					 * sdf.format(date); boolean flag =
					 * bd.isHoldData(formDTO.getRegInitNumber(),fwdPage);
					 * boolean hold = bd.saveHoldDataChecker(formDTO, hldFlag,
					 * fwdPage, Date, userId, flag);
					 */
					if ("Y".equalsIgnoreCase(formDTO.getHoldCheck())) {
						bd.addHoldRemarks(formDTO);
					}
					boolean hold = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), "45", cdate, userId);
					if (hold) {
						// request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD,
						// "The Application has been put on hold Successfully ");
						if ("en".equalsIgnoreCase(language)) {
							request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "The Application has been put on hold Successfully. ");
						} else {
							request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "आवेदन सफलतापूर्वक होल्ड पर रख दिया गया है |");
						}
						forwardJsp = "homePage";
					}
				}
				if (RegCompCheckerConstant.PAY_LATER_REG_FEE.equals(actionName)) {
					String hldFlag = "Y";
					String fwdPage = RegCompCheckerConstant.PROCEED_TO_PAYMENT_REG_FEE;
					formDTO.setHoldPageId("7");
					Date date = new Date();
					System.out.println(date);
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					System.out.println(sdf.format(date));
					String Date = sdf.format(date);
					boolean flag = bd.isHoldData(formDTO.getRegInitNumber(), fwdPage);
					boolean hold = bd.saveHoldDataChecker(formDTO, hldFlag, fwdPage, Date, userId, flag, officeId, "", "");
					if (hold) {
						// request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD,
						// "The Application has been put on hold Successfully ");
						if ("en".equalsIgnoreCase(language)) {
							request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "The Application has been put on hold Successfully. ");
						} else {
							request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "आवेदन सफलतापूर्वक होल्ड पर रख दिया गया है |");
						}
						forwardJsp = "homePage";
					}
				}
				if ("IMPOUND_ACTION_HOLD2".equalsIgnoreCase(actionName)) {
					logger.debug("Impound action");
					if (bd.addHoldRemarks(formDTO))
						;
					{
						if (formDTO.getIsImpound().equalsIgnoreCase("no")) {
							String reasonEnglish = "Referred under Sec-47-";
							String reasonHindi = "धारा 47 के तहत संदर्भित-";
							formDTO.setHoldPageId("6");
							boolean flag = bd.modifyComplianceListData(eForm, formDTO.getRegInitNumber(), userId, cdate);
							logger.debug("**********Complaince list modified****" + flag);
							// boolean flag1 =
							// bd.addWitnessPhotographDetails(eForm.getUploadWitnessPhotograph(),
							// formDTO.getRegInitNumber(), userId, cdate);
							String hldFlag = "Y";
							String createdBy = userId;
							Date date = new Date();
							System.out.println(date);
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							System.out.println(sdf.format(date));
							String Date = sdf.format(date);
							String fwdPage = "proceedToPayment";
							boolean flag2 = bd.isHoldData(formDTO.getRegInitNumber(), fwdPage);
							boolean holdData = bd.saveHoldDataChecker(formDTO, hldFlag, fwdPage, Date, createdBy, flag2, officeId, reasonEnglish, reasonHindi);
							request.setAttribute("HOLD_MESSAGE", bd.getEmailHoldContent(formDTO.getRegInitNumber(), formDTO.getRemarks(), officeId, language, officeId, reasonEnglish, reasonHindi));
							// End=== this code for saving the data in the hold
							// table
							if (holdData) {
								String status = bd.emailAlertHold(formDTO.getRegInitNumber(), officeId);
								logger.debug("email status----->" + status);
								forwardJsp = "CONFIRMATION_SCREEN";
							}
							// request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD,
							// "The Application has been put on hold Successfully ");
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "The Application has been put on hold Successfully. ");
							} else {
								request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "आवेदन सफलतापूर्वक होल्ड पर रख दिया गया है |");
							}
							forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
						} else {
							String englishReason = "Impound-";
							String hindiReason = "ज़ब्त-";
							boolean caseDetails = bd.updateCaseDetails(formDTO, cdate, userId, officeId, englishReason, hindiReason);
							if (caseDetails) {
								String status = bd.emailAlertHold(formDTO.getRegInitNumber(), officeId);
								logger.debug("emailAlert---->status" + status);
								logger.debug("data Updated");
								// messages.add("SUCCESS_MESSAGE", new
								// ActionMessage(
								// "case.referred"));
								// saveMessages(request, messages);
								if ("en".equalsIgnoreCase(language)) {
									request.setAttribute("SUCCESS_MESSAGE", bd.getEmailHoldContent(formDTO.getRegInitNumber(), formDTO.getRemarks(), officeId, language, officeId, englishReason, hindiReason) + " and Case referred to DR Successfully");
								} else {
									request.setAttribute("SUCCESS_MESSAGE", bd.getEmailHoldContent(formDTO.getRegInitNumber(), formDTO.getRemarks(), officeId, language, officeId, englishReason, hindiReason) + " प्रकरण को सफलतापूर्वक जिला पंजीयक के पास भेजा गया है।");
								}
								eForm.setCheck("CM");
								forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
							} else {
								logger.debug("data Updated");
								messages.add("ERROR_MSG", new ActionMessage("case.referred.error"));
								saveMessages(request, messages);
								eForm.setCheck("CME");
								forwardJsp = RegCompCheckerConstant.PROCEED_TO_PAYMENT;
							}
						}
					}
				}
				if (RegCompCheckerConstant.IMPOUND_ACTION.equalsIgnoreCase(actionName)) {
					logger.debug("Impound action");
					if (formDTO.getIsImpound().equalsIgnoreCase("no")) {
						formDTO.setHoldPageId("6");
						String reasonEnglish = "Referred under Sec-47-";
						String reasonHindi = "धारा 47 के तहत संदर्भित-";
						boolean flag = bd.modifyComplianceListData(eForm, formDTO.getRegInitNumber(), userId, cdate);
						logger.debug("**********Complaince list modified****" + flag);
						// boolean flag1 =
						// bd.addWitnessPhotographDetails(eForm.getUploadWitnessPhotograph(),
						// formDTO.getRegInitNumber(), userId, cdate);
						String hldFlag = "Y";
						String createdBy = userId;
						Date date = new Date();
						System.out.println(date);
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						System.out.println(sdf.format(date));
						String Date = sdf.format(date);
						String fwdPage = "proceedToPayment";
						boolean flag2 = bd.isHoldData(formDTO.getRegInitNumber(), fwdPage);
						boolean holdData = bd.saveHoldDataChecker(formDTO, hldFlag, fwdPage, Date, createdBy, flag2, officeId, reasonEnglish, reasonHindi);
						request.setAttribute("HOLD_MESSAGE", bd.getEmailHoldContent(formDTO.getRegInitNumber(), formDTO.getRemarks(), officeId, language, officeId, reasonEnglish, reasonHindi));
						// End=== this code for saving the data in the hold
						// table
						if (holdData) {
							String status = bd.emailAlertHold(formDTO.getRegInitNumber(), officeId);
							logger.debug("email status----->" + status);
							forwardJsp = "CONFIRMATION_SCREEN";
						}
						// request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD,
						// "The Application has been put on hold Successfully ");
						if ("en".equalsIgnoreCase(language)) {
							request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "The Application has been put on hold Successfully. ");
						} else {
							request.setAttribute(RegCompCheckerConstant.INFO_MSG_HOLD, "आवेदन सफलतापूर्वक होल्ड पर रख दिया गया है |");
						}
						forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
					} else {
						boolean flag = bd.updateRegSR(formDTO.getRegInitNumber(), mDto.getUserName(), mDto.getOfficeName());
						String englishReason = "Impound-";
						String hindiReason = "ज़ब्त-";
						boolean caseDetails = bd.updateCaseDetails(formDTO, cdate, userId, officeId, englishReason, hindiReason);
						if (caseDetails) {
							String status = bd.emailAlertHold(formDTO.getRegInitNumber(), officeId);
							logger.debug("emailAlert---->status" + status);
							logger.debug("data Updated");
							// messages.add("SUCCESS_MESSAGE", new
							// ActionMessage(
							// "case.referred"));
							// saveMessages(request, messages);
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("SUCCESS_MESSAGE", bd.getEmailHoldContent(formDTO.getRegInitNumber(), formDTO.getRemarks(), officeId, language, officeId, englishReason, hindiReason) + " and Case referred to DR Successfully");
							} else {
								request.setAttribute("SUCCESS_MESSAGE", bd.getEmailHoldContent(formDTO.getRegInitNumber(), formDTO.getRemarks(), officeId, language, officeId, englishReason, hindiReason) + " प्रकरण को सफलतापूर्वक जिला पंजीयक के पास भेजा गया है।");
							}
							eForm.setCheck("CM");
							forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
						} else {
							logger.debug("data Updated");
							messages.add("ERROR_MSG", new ActionMessage("case.referred.error"));
							saveMessages(request, messages);
							eForm.setCheck("CME");
							forwardJsp = RegCompCheckerConstant.PROCEED_TO_PAYMENT;
						}
					}
				}
				if ("REDIRECT_TO_PAYMENT_ACTION_UNHOLD".equalsIgnoreCase(actionName)) {
					if (bd.addHoldRemarks(formDTO)) {
						String a = null;
						String tableName = null;
						// logger.debug("RegFee or Stamp"+formDTO.getStmp());
						// if(formDTO.getStmp().equalsIgnoreCase("stamp"))
						// {
						a = eForm.getRegDTO().getNetStampDuty();
						String b = eForm.getRegDTO().getNetBalAmt();
						request.setAttribute("parentModName", "Registration Process");
						request.setAttribute("parentFunName", "Registration Completion");
						request.setAttribute("parentFunId", "FUN_023");
						request.setAttribute("parentAmount", a);
						// akansha
						session.setAttribute("parentFunId", "FUN_023");
						session.setAttribute("parentAmount", a);
						// Start-----done by akansha on 5thfeb for cash
						// automation issue
						session.setAttribute("parentAmountNew", b);
						// end
						// session.setAttribute("balAmountAutomation", b);
						request.setAttribute("parentTable", "IGRS_REG_PAYMENT_DETLS");
						// request.setAttribute("parentKey",eForm.getRegDTO().getRegInitNumber());
						request.setAttribute("forwardPath", "./regCompChecker.do?TRFS=NGI");
						request.setAttribute("parentColumnName", "TXN_ID");
						request.setAttribute("parentDistrictName", formDTO.getDistrictName());
						request.setAttribute("parentDistrictId", formDTO.getDistId());
						request.setAttribute("parentOfficeId", formDTO.getSroId());
						request.setAttribute("parentOfficeName", formDTO.getSroName());
						request.setAttribute("parentReferenceId", formDTO.getRegInitNumber());
						session.setAttribute("reg_id", formDTO.getRegInitNumber());
						tableName = "IGRS_REG_PAYMENT_DETLS";
						String payment_type = "1";
						formDTO.setPaymentType(payment_type);
						formDTO.setSourceMod(sourceModFlag);
						// }
						// end of addition on 13feb for new payment modality.
						// CHECK FOR PAYMENT_STATUS BEFORE REDIRECTING.
						String[] paymentArr = bd.getPaymentFlag(formDTO.getRegInitNumber(), sourceModFlag, payment_type);
						// logger.debug("----------------->payment flag:-"+paymentArr[0]);
						if (paymentArr != null) {
							if (!paymentArr[0].equalsIgnoreCase("p") && !paymentArr[0].equalsIgnoreCase("c")) {
								if (paymentArr[0].equalsIgnoreCase("I")) {
									Double paidAmtChecker = bd.getPaidAmtChecker(formDTO.getRegInitNumber(), sourceModFlag, payment_type);
									formDTO.setNetPaidAmt(paidAmtChecker.toString());
									Double totalBal = Double.parseDouble(formDTO.getNetStampDuty()) - paidAmtChecker;
									a = totalBal.toString();
									boolean insertStatus = bd.insertPaymentDetails(formDTO.getRegInitNumber(), a, userId, cdate, tableName, sourceModFlag, payment_type, formDTO);
									logger.debug("----------------->payment insertion status:-" + insertStatus);
									request.setAttribute("parentKey", formDTO.getPmtTxnId());
									// request.setAttribute("parentKey",
									// paymentArr[1]);
									request.setAttribute("parentAmount", a);
									// Start-----done by akansha on 5thfeb for
									// cash automation issue
									session.setAttribute("parentAmountNew", a);
									// end
									// following code for updating purpose
									// boolean
									// updatePurpose=commonBo.updateEStampPurpose(regForm.getHidnRegTxnId(),regForm.getPurpose());
									// if(updatePurpose)
									forwardJsp = RegCompCheckerConstant.REG_COMP_CHECKER_PAYMENT;
									// else
									// forward="failure";
								}
								/*
								 * else
								 * if(paymentArr[0].equals(null)||paymentArr
								 * [0].equalsIgnoreCase("")){
								 * 
								 * regForm.setPaymentTxnSeqId(commonBo.
								 * getPaymentTxnIdSeq());
								 * request.setAttribute("parentKey",
								 * regForm.getPaymentTxnSeqId()); //insertion
								 * code //String
								 * param[]={regForm.getHidnRegTxnId
								 * (),regForm.getTotalduty
								 * (),regForm.getHidnUserId
								 * (),regForm.getPurpose()}; boolean
								 * insertStatus
								 * =commonBo.insertPaymentDetails(regForm);
								 * logger.debug(
								 * "----------------->payment insertion status:-"
								 * +insertStatus); if(insertStatus)
								 * forward="reginitPayment"; else
								 * forward="failure";
								 * 
								 * }
								 */
							}
							// /*************************PENDING*********************************//
							else if (paymentArr[0].equalsIgnoreCase("p")) {
								Double paidAmtChecker = bd.getPaidAmtChecker(formDTO.getRegInitNumber(), sourceModFlag, payment_type);
								formDTO.setNetPaidAmt(paidAmtChecker.toString());
								Double totalBal = Double.parseDouble(formDTO.getNetStampDuty()) - paidAmtChecker;
								a = totalBal.toString();
								// String
								// param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
								boolean insertStatus = bd.insertPaymentDetails(formDTO.getRegInitNumber(), a, userId, cdate, tableName, sourceModFlag, payment_type, formDTO);
								logger.debug("----------------->payment insertion status:-" + insertStatus);
								request.setAttribute("parentKey", formDTO.getPmtTxnId());
								request.setAttribute("parentAmount", a);
								// Start-----done by akansha on 5thfeb for cash
								// automation issue
								session.setAttribute("parentAmountNew", a);
								// end
								if (insertStatus) {
									forwardJsp = RegCompCheckerConstant.REG_COMP_CHECKER_PAYMENT;
								} else {
									if ("en".equalsIgnoreCase(language)) {
										request.setAttribute(RegCompCheckerConstant.FAILURE_MSG, "Registration Completion Failed");
									} else {
										request.setAttribute(RegCompCheckerConstant.FAILURE_MSG, "पंजीकरण पूरा होने में विफल रहा है |");
									}
									forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
								}
								// ArrayList partyDetails =
								// bd.getPartyDet(formDTO.getRegInitNumber());
								// eForm.setPartiesDetailList(partyDetails);
								// ArrayList witnessList =
								// bd.getWitnessDet(formDTO.getRegInitNumber());
								// eForm.setWitnessDetailsList(witnessList);
								// logger.debug("<-------SIZE"+eForm.getPartiesDetailList().size());
								// forwardJsp=
								// RegCompCheckerConstant.PARTIES_BIOMETRIC_DETAILS;
								// regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
								// request.setAttribute("parentKey",
								// regForm.getPaymentTxnSeqId());
								// insertion code
								// String
								// param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
								// boolean
								// insertStatus=commonBo.insertPaymentDetails(regForm);
								// logger.debug("----------------->payment insertion status:-"+insertStatus);
								// if(insertStatus)
								// forward="reginitPayment";
								// else
								// forward="failure";
							} else if (paymentArr[0].equalsIgnoreCase("c")) {
								String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
								formDTO.setParentPathSign(path);
								formDTO.setFileNameSign("signature.GIF");
								formDTO.setForwardPath("/regCompChecker.do?txnPartyIdBiometric=");
								formDTO.setForwardName("regCompChecker");
								String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
								formDTO.setFileNameFP("FingerPrint.GIF");
								formDTO.setParentPathFP(pathFP);
								ArrayList partyDetails = bd.getPartyDet(formDTO.getRegInitNumber(), language);
								eForm.setPartiesDetailList(partyDetails);
								ArrayList witnessList = bd.getWitnessDet(formDTO.getRegInitNumber(), language);
								eForm.setWitnessDetailsList(witnessList);
								ArrayList consenterList = bd.getConsenterDet(formDTO.getRegInitNumber(), language);
								eForm.setConsenterDetailsList(consenterList);
								if (consenterList != null && consenterList.size() > 0)
									formDTO.setCnsntrChk("Y");
								else
									formDTO.setCnsntrChk("N");
								logger.debug("<-------SIZE" + eForm.getPartiesDetailList().size());
								logger.debug("PARTIES_BIOMETRIC_DETAILS ......7");
								forwardJsp = RegCompCheckerConstant.PARTIES_BIOMETRIC_DETAILS;
							} else {
								// request.setAttribute(RegCompCheckerConstant.FAILURE_MSG,
								// "Registration Completion Failed");
								if ("en".equalsIgnoreCase(language)) {
									request.setAttribute(RegCompCheckerConstant.FAILURE_MSG, "Registration Completion Failed");
								} else {
									request.setAttribute(RegCompCheckerConstant.FAILURE_MSG, "पंजीकरण पूरा होने में विफल रहा है |");
								}
								forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;;
							}
						} else {
							// regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
							// insertion code
							// String
							// param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
							boolean insertStatus = bd.insertPaymentDetails(formDTO.getRegInitNumber(), a, userId, cdate, tableName, sourceModFlag, payment_type, formDTO);
							logger.debug("----------------->payment insertion status:-" + insertStatus);
							request.setAttribute("parentKey", formDTO.getPmtTxnId());
							request.setAttribute("parentAmount", a);
							// //Start-----done by akansha on 5thfeb for cash
							// automation issue
							session.setAttribute("parentAmountNew", a);
							// end
							if (insertStatus) {
								forwardJsp = RegCompCheckerConstant.REG_COMP_CHECKER_PAYMENT;
							} else {
								// request.setAttribute(RegCompCheckerConstant.FAILURE_MSG,
								// "Registration Completion Failed");
								if ("en".equalsIgnoreCase(language)) {
									request.setAttribute(RegCompCheckerConstant.FAILURE_MSG, "Registration Completion Failed");
								} else {
									request.setAttribute(RegCompCheckerConstant.FAILURE_MSG, "पंजीकरण पूरा होने में विफल रहा है |");
								}
								forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
							}
						}
						/*
						 * String paymentFlag =
						 * bd.getPaymentFlag(formDTO.getRegInitNumber
						 * (),payment_type,sourceModFlag);
						 * logger.debug("----------------->payment flag:-"
						 * +paymentFlag); if(!paymentFlag.equalsIgnoreCase("p")
						 * && !paymentFlag.equalsIgnoreCase("c")){
						 * 
						 * if(paymentFlag.equalsIgnoreCase("I")){
						 * forwardJsp=RegCompCheckerConstant
						 * .REG_COMP_CHECKER_PAYMENT; } else
						 * if(paymentFlag.equals
						 * (null)||paymentFlag.equalsIgnoreCase("")){
						 * 
						 * //insertion code
						 * 
						 * boolean insertStatus =
						 * bd.insertPaymentDetails(formDTO
						 * .getRegInitNumber(),a,userId,cdate,
						 * tableName,sourceModFlag,payment_type);logger.debug(
						 * "----------------->payment insertion status:-"
						 * +insertStatus); if(insertStatus)
						 * forwardJsp=RegCompCheckerConstant
						 * .REG_COMP_CHECKER_PAYMENT; else {
						 * request.setAttribute
						 * (RegCompCheckerConstant.FAILURE_MSG,
						 * "Registration Completion Failed");
						 * forwardJsp=RegCompCheckerConstant
						 * .CONFIRMATION_SCREEN; } }
						 * 
						 * }
						 */
						/*
						 * if(tableName.equalsIgnoreCase("IGRS_REGCOMP_CHKR_REGFEE_DETLS"
						 * )) { if(paymentFlag.equalsIgnoreCase("p")){
						 * forwardJsp
						 * =RegCompCheckerConstant.REG_COMP_CHECKER_PAYMENT; }
						 * else if(paymentFlag.equalsIgnoreCase("c")){ String
						 * RegCompleteNumber = bd.getRegCompletionNumber(userId,
						 * cdate, formDTO.getDeedID());
						 * formDTO.setRegCompleteId(RegCompleteNumber);
						 * logger.debug
						 * ("Reg Comp Num in action"+RegCompleteNumber);
						 * request.
						 * setAttribute(RegCompCheckerConstant.FINAL_CONF_MSG,
						 * "Registration Completed Successfully"); forwardJsp=
						 * RegCompCheckerConstant.CONFIRMATION_SCREEN; }else {
						 * request
						 * .setAttribute(RegCompCheckerConstant.FAILURE_MSG,
						 * "Registration Failed"); forwardJsp =
						 * RegCompCheckerConstant.CONFIRMATION_SCREEN; } } else
						 * { if(paymentFlag.equalsIgnoreCase("p")){
						 * forwardJsp=RegCompCheckerConstant
						 * .REG_COMP_CHECKER_PAYMENT; } else
						 * if(paymentFlag.equalsIgnoreCase("c")){
						 * formDTO.setRegFeePmt("Y");
						 * formDTO.setStampDutyPmt("");
						 * //request.setAttribute(RegCompCheckerConstant
						 * .SUCCESS_MSG, "Registration Completed Successfully");
						 * forwardJsp=
						 * RegCompCheckerConstant.PARTIES_BIOMETRIC_DETAILS;
						 * }else {
						 * request.setAttribute(RegCompCheckerConstant.FAILURE_MSG
						 * , "Registration Failed"); forwardJsp =
						 * RegCompCheckerConstant.CONFIRMATION_SCREEN; } }
						 */
						// else if(paymentFlag.equalsIgnoreCase("p")){
						// forwardJsp=RegCompCheckerConstant.REG_COMP_CHECKER_PAYMENT;;
						// }
						// else if(paymentFlag.equalsIgnoreCase("c")){
						// request.setAttribute(RegCompCheckerConstant.FINAL_CONF_MSG,
						// "Registration Completed Successfully");
						// forwardJsp=
						// RegCompCheckerConstant.CONFIRMATION_SCREEN;
						// ArrayList partyDetails =
						// bd.getPartyDet(formDTO.getRegInitNumber());
						// eForm.setPartiesDetailList(partyDetails);
						// ArrayList witnessList =
						// bd.getWitnessDet(formDTO.getRegInitNumber());
						// eForm.setWitnessDetailsList(witnessList);
						// logger.debug("<-------SIZE"+eForm.getPartiesDetailList().size());
						// forwardJsp=
						// RegCompCheckerConstant.PARTIES_BIOMETRIC_DETAILS;
						// }else
						// {
						// request.setAttribute(RegCompCheckerConstant.FAILURE_MSG,
						// "Registration Completion Failed");
						// forwardJsp=RegCompCheckerConstant.CONFIRMATION_SCREEN;
						// }
					}
				}
				if (RegCompCheckerConstant.REDIRECT_TO_PAYMENT_ACTION.equalsIgnoreCase(actionName)) {
					String a = null;
					String tableName = null;
					// logger.debug("RegFee or Stamp"+formDTO.getStmp());
					// if(formDTO.getStmp().equalsIgnoreCase("stamp"))
					// {
					a = eForm.getRegDTO().getNetStampDuty();
					String b = eForm.getRegDTO().getNetBalAmt();
					request.setAttribute("parentModName", "Registration Process");
					request.setAttribute("parentFunName", "Registration Completion");
					request.setAttribute("parentFunId", "FUN_023");
					request.setAttribute("parentAmount", a);
					// akansha
					session.setAttribute("parentFunId", "FUN_023");
					session.setAttribute("parentAmount", a);
					// Start-----done by akansha on 5thfeb for cash automation
					// issue
					session.setAttribute("parentAmountNew", b);
					// end
					// session.setAttribute("balAmountAutomation", b);
					request.setAttribute("parentTable", "IGRS_REG_PAYMENT_DETLS");
					// request.setAttribute("parentKey",eForm.getRegDTO().getRegInitNumber());
					request.setAttribute("forwardPath", "./regCompChecker.do?TRFS=NGI");
					request.setAttribute("parentColumnName", "TXN_ID");
					request.setAttribute("parentDistrictName", formDTO.getDistrictName());
					request.setAttribute("parentDistrictId", formDTO.getDistId());
					request.setAttribute("parentOfficeId", formDTO.getSroId());
					request.setAttribute("parentOfficeName", formDTO.getSroName());
					request.setAttribute("parentReferenceId", formDTO.getRegInitNumber());
					session.setAttribute("reg_id", formDTO.getRegInitNumber());
					tableName = "IGRS_REG_PAYMENT_DETLS";
					String payment_type = "1";
					formDTO.setPaymentType(payment_type);
					formDTO.setSourceMod(sourceModFlag);
					// }
					// end of addition on 13feb for new payment modality.
					// CHECK FOR PAYMENT_STATUS BEFORE REDIRECTING.
					String[] paymentArr = bd.getPaymentFlag(formDTO.getRegInitNumber(), sourceModFlag, payment_type);
					// logger.debug("----------------->payment flag:-"+paymentArr[0]);
					if (paymentArr != null) {
						if (!paymentArr[0].equalsIgnoreCase("p") && !paymentArr[0].equalsIgnoreCase("c")) {
							if (paymentArr[0].equalsIgnoreCase("I")) {
								Double paidAmtChecker = bd.getPaidAmtChecker(formDTO.getRegInitNumber(), sourceModFlag, payment_type);
								formDTO.setNetPaidAmt(paidAmtChecker.toString());
								Double totalBal = Double.parseDouble(formDTO.getNetStampDuty()) - paidAmtChecker;
								a = totalBal.toString();
								boolean insertStatus = bd.insertPaymentDetails(formDTO.getRegInitNumber(), a, userId, cdate, tableName, sourceModFlag, payment_type, formDTO);
								logger.debug("----------------->payment insertion status:-" + insertStatus);
								request.setAttribute("parentKey", formDTO.getPmtTxnId());
								// request.setAttribute("parentKey",
								// paymentArr[1]);
								request.setAttribute("parentAmount", a);
								// Start-----done by akansha on 5thfeb for cash
								// automation issue
								session.setAttribute("parentAmountNew", a);
								// end
								// following code for updating purpose
								// boolean
								// updatePurpose=commonBo.updateEStampPurpose(regForm.getHidnRegTxnId(),regForm.getPurpose());
								// if(updatePurpose)
								forwardJsp = RegCompCheckerConstant.REG_COMP_CHECKER_PAYMENT;
								// else
								// forward="failure";
							}
							/*
							 * else
							 * if(paymentArr[0].equals(null)||paymentArr[0].
							 * equalsIgnoreCase("")){
							 * 
							 * regForm.setPaymentTxnSeqId(commonBo.
							 * getPaymentTxnIdSeq());
							 * request.setAttribute("parentKey",
							 * regForm.getPaymentTxnSeqId()); //insertion code
							 * //String
							 * param[]={regForm.getHidnRegTxnId(),regForm
							 * .getTotalduty
							 * (),regForm.getHidnUserId(),regForm.getPurpose()};
							 * boolean
							 * insertStatus=commonBo.insertPaymentDetails
							 * (regForm);logger.debug(
							 * "----------------->payment insertion status:-"
							 * +insertStatus); if(insertStatus)
							 * forward="reginitPayment"; else forward="failure";
							 * 
							 * }
							 */
						}
						// /*************************PENDING*********************************//
						else if (paymentArr[0].equalsIgnoreCase("p")) {
							Double paidAmtChecker = bd.getPaidAmtChecker(formDTO.getRegInitNumber(), sourceModFlag, payment_type);
							formDTO.setNetPaidAmt(paidAmtChecker.toString());
							Double totalBal = Double.parseDouble(formDTO.getNetStampDuty()) - paidAmtChecker;
							a = totalBal.toString();
							// String
							// param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
							boolean insertStatus = bd.insertPaymentDetails(formDTO.getRegInitNumber(), a, userId, cdate, tableName, sourceModFlag, payment_type, formDTO);
							logger.debug("----------------->payment insertion status:-" + insertStatus);
							request.setAttribute("parentKey", formDTO.getPmtTxnId());
							request.setAttribute("parentAmount", a);
							// Start-----done by akansha on 5thfeb for cash
							// automation issue
							session.setAttribute("parentAmountNew", a);
							// end
							if (insertStatus) {
								forwardJsp = RegCompCheckerConstant.REG_COMP_CHECKER_PAYMENT;
							} else {
								// request.setAttribute(RegCompCheckerConstant.FAILURE_MSG,
								// "Registration Completion Failed");
								if ("en".equalsIgnoreCase(language)) {
									request.setAttribute(RegCompCheckerConstant.FAILURE_MSG, "Registration Completion Failed");
								} else {
									request.setAttribute(RegCompCheckerConstant.FAILURE_MSG, "पंजीकरण पूरा होने में विफल रहा है |");
								}
								forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
							}
							// ArrayList partyDetails =
							// bd.getPartyDet(formDTO.getRegInitNumber());
							// eForm.setPartiesDetailList(partyDetails);
							// ArrayList witnessList =
							// bd.getWitnessDet(formDTO.getRegInitNumber());
							// eForm.setWitnessDetailsList(witnessList);
							// logger.debug("<-------SIZE"+eForm.getPartiesDetailList().size());
							// forwardJsp=
							// RegCompCheckerConstant.PARTIES_BIOMETRIC_DETAILS;
							// regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
							// request.setAttribute("parentKey",
							// regForm.getPaymentTxnSeqId());
							// insertion code
							// String
							// param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
							// boolean
							// insertStatus=commonBo.insertPaymentDetails(regForm);
							// logger.debug("----------------->payment insertion status:-"+insertStatus);
							// if(insertStatus)
							// forward="reginitPayment";
							// else
							// forward="failure";
						} else if (paymentArr[0].equalsIgnoreCase("c")) {
							String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
							formDTO.setParentPathSign(path);
							formDTO.setFileNameSign("signature.GIF");
							formDTO.setForwardPath("/regCompChecker.do?txnPartyIdBiometric=");
							formDTO.setForwardName("regCompChecker");
							String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
							formDTO.setFileNameFP("FingerPrint.GIF");
							formDTO.setParentPathFP(pathFP);
							ArrayList partyDetails = bd.getPartyDet(formDTO.getRegInitNumber(), language);
							eForm.setPartiesDetailList(partyDetails);
							ArrayList witnessList = bd.getWitnessDet(formDTO.getRegInitNumber(), language);
							eForm.setWitnessDetailsList(witnessList);
							ArrayList consenterList = bd.getConsenterDet(formDTO.getRegInitNumber(), language);
							eForm.setConsenterDetailsList(consenterList);
							if (consenterList != null && consenterList.size() > 0)
								formDTO.setCnsntrChk("Y");
							else
								formDTO.setCnsntrChk("N");
							logger.debug("<-------SIZE" + eForm.getPartiesDetailList().size());
							logger.debug("PARTIES_BIOMETRIC_DETAILS ......8");
							forwardJsp = RegCompCheckerConstant.PARTIES_BIOMETRIC_DETAILS;
						} else {
							// request.setAttribute(RegCompCheckerConstant.FAILURE_MSG,
							// "Registration Completion Failed");
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute(RegCompCheckerConstant.FAILURE_MSG, "Registration Completion Failed");
							} else {
								request.setAttribute(RegCompCheckerConstant.FAILURE_MSG, "पंजीकरण पूरा होने में विफल रहा है |");
							}
							forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;;
						}
					} else {
						// regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
						// insertion code
						// String
						// param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
						boolean insertStatus = bd.insertPaymentDetails(formDTO.getRegInitNumber(), a, userId, cdate, tableName, sourceModFlag, payment_type, formDTO);
						logger.debug("----------------->payment insertion status:-" + insertStatus);
						request.setAttribute("parentKey", formDTO.getPmtTxnId());
						request.setAttribute("parentAmount", a);
						// Start-----done by akansha on 5thfeb for cash
						// automation issue
						session.setAttribute("parentAmountNew", a);
						// end
						if (insertStatus) {
							forwardJsp = RegCompCheckerConstant.REG_COMP_CHECKER_PAYMENT;
						} else {
							// request.setAttribute(RegCompCheckerConstant.FAILURE_MSG,
							// "Registration Completion Failed");
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute(RegCompCheckerConstant.FAILURE_MSG, "Registration Completion Failed");
							} else {
								request.setAttribute(RegCompCheckerConstant.FAILURE_MSG, "पंजीकरण पूरा होने में विफल रहा है |");
							}
							forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
						}
					}
					/*
					 * String paymentFlag =
					 * bd.getPaymentFlag(formDTO.getRegInitNumber
					 * (),payment_type,sourceModFlag);
					 * logger.debug("----------------->payment flag:-"
					 * +paymentFlag); if(!paymentFlag.equalsIgnoreCase("p") &&
					 * !paymentFlag.equalsIgnoreCase("c")){
					 * 
					 * if(paymentFlag.equalsIgnoreCase("I")){
					 * forwardJsp=RegCompCheckerConstant
					 * .REG_COMP_CHECKER_PAYMENT; } else
					 * if(paymentFlag.equals(null
					 * )||paymentFlag.equalsIgnoreCase("")){
					 * 
					 * //insertion code
					 * 
					 * boolean insertStatus =
					 * bd.insertPaymentDetails(formDTO.getRegInitNumber
					 * (),a,userId,cdate, tableName,sourceModFlag,payment_type);
					 * logger
					 * .debug("----------------->payment insertion status:-"
					 * +insertStatus); if(insertStatus)
					 * forwardJsp=RegCompCheckerConstant
					 * .REG_COMP_CHECKER_PAYMENT; else {
					 * request.setAttribute(RegCompCheckerConstant.FAILURE_MSG,
					 * "Registration Completion Failed");
					 * forwardJsp=RegCompCheckerConstant.CONFIRMATION_SCREEN; }
					 * }
					 * 
					 * }
					 */
					/*
					 * if(tableName.equalsIgnoreCase("IGRS_REGCOMP_CHKR_REGFEE_DETLS"
					 * )) { if(paymentFlag.equalsIgnoreCase("p")){
					 * forwardJsp=RegCompCheckerConstant
					 * .REG_COMP_CHECKER_PAYMENT; } else
					 * if(paymentFlag.equalsIgnoreCase("c")){ String
					 * RegCompleteNumber = bd.getRegCompletionNumber(userId,
					 * cdate, formDTO.getDeedID());
					 * formDTO.setRegCompleteId(RegCompleteNumber);
					 * logger.debug("Reg Comp Num in action"+RegCompleteNumber);
					 * request
					 * .setAttribute(RegCompCheckerConstant.FINAL_CONF_MSG,
					 * "Registration Completed Successfully"); forwardJsp=
					 * RegCompCheckerConstant.CONFIRMATION_SCREEN; }else {
					 * request.setAttribute(RegCompCheckerConstant.FAILURE_MSG,
					 * "Registration Failed"); forwardJsp =
					 * RegCompCheckerConstant.CONFIRMATION_SCREEN; } } else {
					 * if(paymentFlag.equalsIgnoreCase("p")){
					 * forwardJsp=RegCompCheckerConstant
					 * .REG_COMP_CHECKER_PAYMENT; } else
					 * if(paymentFlag.equalsIgnoreCase("c")){
					 * formDTO.setRegFeePmt("Y"); formDTO.setStampDutyPmt("");
					 * //
					 * request.setAttribute(RegCompCheckerConstant.SUCCESS_MSG,
					 * "Registration Completed Successfully"); forwardJsp=
					 * RegCompCheckerConstant.PARTIES_BIOMETRIC_DETAILS; }else {
					 * request.setAttribute(RegCompCheckerConstant.FAILURE_MSG,
					 * "Registration Failed"); forwardJsp =
					 * RegCompCheckerConstant.CONFIRMATION_SCREEN; } }
					 */
					// else if(paymentFlag.equalsIgnoreCase("p")){
					// forwardJsp=RegCompCheckerConstant.REG_COMP_CHECKER_PAYMENT;;
					// }
					// else if(paymentFlag.equalsIgnoreCase("c")){
					// request.setAttribute(RegCompCheckerConstant.FINAL_CONF_MSG,
					// "Registration Completed Successfully");
					// forwardJsp= RegCompCheckerConstant.CONFIRMATION_SCREEN;
					// ArrayList partyDetails =
					// bd.getPartyDet(formDTO.getRegInitNumber());
					// eForm.setPartiesDetailList(partyDetails);
					// ArrayList witnessList =
					// bd.getWitnessDet(formDTO.getRegInitNumber());
					// eForm.setWitnessDetailsList(witnessList);
					// logger.debug("<-------SIZE"+eForm.getPartiesDetailList().size());
					// forwardJsp=
					// RegCompCheckerConstant.PARTIES_BIOMETRIC_DETAILS;
					// }else
					// {
					// request.setAttribute(RegCompCheckerConstant.FAILURE_MSG,
					// "Registration Completion Failed");
					// forwardJsp=RegCompCheckerConstant.CONFIRMATION_SCREEN;
					// }
				}
				if (RegCompCheckerConstant.ADD_SEALS_ACTION.equalsIgnoreCase(actionName)) {
					// *********update biometric details*********************//
					/*
					 * String partyRemArr[] =
					 * formDTO.getPartyRemarks().split(","); String witRemArr[]
					 * = formDTO.getWitRemarks().split(","); String cnsRemArr[]
					 * = formDTO.getCnsRemarks().split(",");
					 * 
					 * ArrayList partyList = eForm.getPartiesDetailList();
					 * ArrayList witnessList = eForm.getWitnessDetailsList();
					 * ArrayList consenterList=eForm.getConsenterDetailsList();
					 * 
					 * for(int i = 0 ; i < partyRemArr.length ;i++) { String
					 * partyArr[] = partyRemArr[i].split("_"); Iterator itr =
					 * partyList.iterator();
					 * 
					 * while(itr.hasNext()) { RegCompCheckerDTO rrdto =
					 * (RegCompCheckerDTO)itr.next(); String partyId =
					 * rrdto.getPartyTxnId(); if(partyId.equals(partyArr[0])) {
					 * logger.debug("Match");
					 * rrdto.setThumbRemarks(partyArr[1]); break; } } }
					 * 
					 * for(int i = 0 ; i < witRemArr.length ;i++) { String
					 * witArr[] = witRemArr[i].split("_"); Iterator itr =
					 * witnessList.iterator();
					 * 
					 * while(itr.hasNext()) { RegCompCheckerDTO rrdto =
					 * (RegCompCheckerDTO)itr.next(); String partyId =
					 * rrdto.getWitnessTxnNummber();
					 * if(partyId.equals(witArr[0])) {
					 * logger.debug("Match witness");
					 * rrdto.setThumbRemarks(witArr[1]); break; } } }
					 * 
					 * for(int i = 0 ; i < cnsRemArr.length ;i++) { String
					 * cnsArr[] = cnsRemArr[i].split("_"); Iterator itr =
					 * consenterList.iterator();
					 * 
					 * while(itr.hasNext()) { RegCompCheckerDTO rrdto =
					 * (RegCompCheckerDTO)itr.next(); String partyId =
					 * rrdto.getConsentorTxnNumber();
					 * if(partyId.equals(cnsArr[0])) {
					 * logger.debug("Match cnsntr");
					 * rrdto.setThumbRemarks(cnsArr[1]); break; } } }
					 */
					// boolean flagB = bd.updateBiometricDetails(eForm, userId,
					// cdate, formDTO.getRegInitNumber());
					// logger.debug("BIOMETRIC DETAILS UPDATED  "+flagB);
					// boolean flag =
					// bd.insertFinalDuties(formDTO.getRegInitNumber(),eForm,
					// userId, tDate);
					// logger.debug("FINAL DUTIES INSERTED IN CHECKER TABLE  "+flag);
					boolean nextStatus = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), RegCompCheckerConstant.ADD_SEALS_STATUS, cdate, userId);
					forwardJsp = RegCompCheckerConstant.ADD_SEALS_PAGE;
				}
				if (RegCompCheckerConstant.ADMISSION_CONSENTER_THUMB_SEAL_ACTION.equalsIgnoreCase(actionName)) {
					request.setAttribute("page", "sealForm1");
					request.setAttribute("regNumber", formDTO.getRegInitNumber());
					// request.setAttribute("regForm",eForm );
					request.setAttribute("sealId", formDTO.getSealId());
					logger.debug("deed" + formDTO.getDeedType());
					logger.debug("deed" + formDTO.getDeedID());
					logger.debug("deed" + formDTO.getDeedId());
					logger.debug("deed ID---->" + formDTO.getDeedID());
					formDTO.setDeedType(bd.getDeedName(String.valueOf(formDTO.getDeedID()), language));
					logger.debug("deed name---->" + formDTO.getDeedType());
					if (formDTO.getSealId().equals("9")) // adjudication seal
					{
						request.setAttribute("DeedType", formDTO.getDeedType());
					}
					request.setAttribute("headerImagePath", eForm.getHeaderImg());
					String sealId = formDTO.getSealId();
					if (sealId.equals("2") || sealId.equals("3") || sealId.equals("4")) {
						request.setAttribute("backPage", "admission");
					} else if (sealId.equals("12") || sealId.equals("10") || sealId.equals("7")) {
						String regCompNum = bd.getCompletionNumber(formDTO.getRegInitNumber());
						request.setAttribute("regCompNum", regCompNum);
						request.setAttribute("backPage", "regFeeSeal");
					} else {
						request.setAttribute("backPage", "bookType");
					}
					forwardJsp = "forwardToSeal";
				}
				if (RegCompCheckerConstant.REG_FEE_PAYMENT.equalsIgnoreCase(actionName)) {
					// request.setAttribute("page", "sealForm1");
					request.setAttribute("regNumber", formDTO.getRegInitNumber());
					// request.setAttribute("regForm",eForm );
					// request.setAttribute("sealId", "1");
					// request.setAttribute("partyPresentArray",formDTO.getSelectedPartyIds());
					request.setAttribute("headerImagePath", eForm.getHeaderImg());
					// request.setAttribute("backPage", "presentation");
					boolean flag1 = bd.applywitnessThumbStamp(request, session);
					if (flag1) {
						Double paidAmtAtChecker = bd.getPaidAmtChecker(formDTO.getRegInitNumber(), sourceModFlag, "2");
						ArrayList hmap = bd.getFinalDutiesForPayment(formDTO.getRegInitNumber(), eForm);
						ArrayList netBalance = eForm.getLinkingDutiesDisp();
						Iterator itr = netBalance.iterator();
						while (itr.hasNext()) {
							RegCompCheckerDTO rgDTO = (RegCompCheckerDTO) itr.next();
							formDTO.setNetStampDuty(rgDTO.getStampDutyPmt());
							formDTO.setNetRegFee(rgDTO.getRegFeePmt());
							// logger.debug("net stamp duty"+formDTO.getNetStampDuty());
							// logger.debug("net reg fee"+formDTO.getNetRegFee());
						}
						// double netStmp =
						// Double.parseDouble(formDTO.getNetStampDuty());
						// double netRegFee =
						// Double.parseDouble(formDTO.getNetRegFee());
						Double regFeeTotal = Double.parseDouble(formDTO.getNetRegFee());
						Double bal = regFeeTotal - paidAmtAtChecker;
						if (bal > 0.0) {
							formDTO.setRegFeePmt("Y");
							formDTO.setRegFeeRadio("Y");
							formDTO.setStampDutyPmt("");
							formDTO.setNetBalAmt(BigDecimal.valueOf(bal).toPlainString());
							formDTO.setNetPaidAmt(paidAmtAtChecker.toString());
							Date date = new Date();
							System.out.println(date);
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							System.out.println(sdf.format(date));
							String Date = sdf.format(date);
							formDTO.setParentFuncID("FUN_206");
							boolean flag = bd.isHoldData(formDTO.getRegInitNumber(), RegCompCheckerConstant.PROCEED_TO_PAYMENT_REG_FEE);
							formDTO.setHoldPageId("7");
							boolean hold = bd.saveHoldDataChecker(formDTO, "7", RegCompCheckerConstant.PROCEED_TO_PAYMENT_REG_FEE, Date, userId, flag, officeId, "", "");
							forwardJsp = RegCompCheckerConstant.PROCEED_TO_PAYMENT;
						} else {
							String deedId = bd.getDeedId(formDTO.getRegInitNumber());
							if (deedId.equals("1053")) // ****ConveyanceDeed
							{
								formDTO.setConvynceDeedCheck("Y");
							}
							// String
							// instId=bd.getInstId(formDTO.getRegInitNumber());
							String bookId = bd.getbookId(formDTO.getRegInitNumber());
							if (bookId != null && !bookId.equalsIgnoreCase("")) {
								String bookName = bd.getbookName(bookId);
								if (bookName != null && !bookName.equalsIgnoreCase("")) {
									formDTO.setBookId(bookId);
									formDTO.setBookName(bookName);
									eForm.setDisableBook("TRUE");
								} else {
									formDTO.setBookId("");
									formDTO.setBookName("");
								}
							} else {
								formDTO.setBookId("");
								formDTO.setBookName("");
							}
							ArrayList bookDetails = bd.getBookDetails();
							eForm.setBookDetailsList(bookDetails);
							boolean hold = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), RegCompCheckerConstant.BOOK_TYPE_STATUS, cdate, userId);
							boolean flag = bd.insertFinalDuties(formDTO.getRegInitNumber(), eForm, userId, tDate);
							forwardJsp = RegCompCheckerConstant.BOOK_TYPE_SELECTION;
						}
						// forwardJsp = RegCompCheckerConstant.ADD_SEALS_PAGE;
					} else {
						session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
						forwardJsp = "failureChecker";
					}
				}
				if (RegCompCheckerConstant.REDIRECT_TO_PAYMENT_ACTION_REG_FEE.equals(actionName)) {
					String a = eForm.getRegDTO().getNetBalAmt();
					logger.debug("paymnt amnt---->" + a);
					if ((Double.parseDouble(a) > 0.0)) {
						request.setAttribute("parentModName", "Registration Process");
						request.setAttribute("parentFunName", "REGISTRATION FEE");
						request.setAttribute("parentFunId", "FUN_206");
						// akansha
						session.setAttribute("parentFunId", "FUN_206");
						eForm.getRegDTO().setParentFuncID("FUN_206");
						request.setAttribute("parentAmount", a);
						// akansha
						session.setAttribute("parentAmount", a);
						// Start-----done by akansha on 5thfeb for cash
						// automation issue
						session.setAttribute("parentAmountNew", a);
						// end
						request.setAttribute("parentTable", "IGRS_REG_PAYMENT_DETLS");
						// request.setAttribute("parentKey",eForm.getRegDTO().getRegInitNumber());
						request.setAttribute("forwardPath", "./regCompChecker.do?TRFS=NGI");
						request.setAttribute("parentColumnName", "TXN_ID");
						request.setAttribute("parentDistrictName", formDTO.getDistrictName());
						request.setAttribute("parentDistrictId", formDTO.getDistId());
						request.setAttribute("parentOfficeId", formDTO.getSroId());
						request.setAttribute("parentOfficeName", formDTO.getSroName());
						request.setAttribute("parentReferenceId", formDTO.getRegInitNumber());
						session.setAttribute("reg_id", formDTO.getRegInitNumber());
						String tableName = "IGRS_REG_PAYMENT_DETLS";
						String paymentType = "2";
						formDTO.setPaymentType(paymentType);
						formDTO.setSourceMod(sourceModFlag);
						/*
						 * String paymentFlag =
						 * bd.getPaymentFlag(formDTO.getRegInitNumber
						 * (),paymentType,sourceModFlag);
						 * logger.debug("----------------->payment flag:-"
						 * +paymentFlag); if(!paymentFlag.equalsIgnoreCase("p")
						 * && !paymentFlag.equalsIgnoreCase("c")){
						 * 
						 * if(paymentFlag.equalsIgnoreCase("I")){
						 * forwardJsp=RegCompCheckerConstant
						 * .REG_COMP_CHECKER_PAYMENT; } else
						 * if(paymentFlag.equals
						 * (null)||paymentFlag.equalsIgnoreCase("")){
						 * 
						 * //insertion code
						 * 
						 * boolean insertStatus =
						 * bd.insertPaymentDetails(formDTO
						 * .getRegInitNumber(),a,userId,cdate,
						 * tableName,sourceModFlag,paymentType);logger.debug(
						 * "----------------->payment insertion status:-"
						 * +insertStatus); if(insertStatus)
						 * forwardJsp=RegCompCheckerConstant
						 * .REG_COMP_CHECKER_PAYMENT; else {
						 * request.setAttribute
						 * (RegCompCheckerConstant.FAILURE_MSG,
						 * "Registration Completion Failed");
						 * forwardJsp=RegCompCheckerConstant
						 * .CONFIRMATION_SCREEN; } }
						 * 
						 * } else if(paymentFlag.equalsIgnoreCase("p")){
						 * forwardJsp
						 * =RegCompCheckerConstant.REG_COMP_CHECKER_PAYMENT;; }
						 * else if(paymentFlag.equalsIgnoreCase("c")){
						 * //request.
						 * setAttribute(RegCompCheckerConstant.FINAL_CONF_MSG,
						 * "Registration Completed Successfully"); //forwardJsp=
						 * RegCompCheckerConstant.CONFIRMATION_SCREEN; ArrayList
						 * bookDetails = bd.getBookDetails();
						 * eForm.setBookDetailsList(bookDetails); forwardJsp =
						 * RegCompCheckerConstant.BOOK_TYPE_SELECTION; }else {
						 * request
						 * .setAttribute(RegCompCheckerConstant.FAILURE_MSG,
						 * "Registration Completion Failed");
						 * forwardJsp=RegCompCheckerConstant
						 * .CONFIRMATION_SCREEN; } } else { ArrayList
						 * bookDetails = bd.getBookDetails();
						 * eForm.setBookDetailsList(bookDetails); forwardJsp =
						 * RegCompCheckerConstant.BOOK_TYPE_SELECTION; }
						 */
						String[] paymentArr = bd.getPaymentFlag(formDTO.getRegInitNumber(), sourceModFlag, paymentType);
						// logger.debug("----------------->payment flag:-"+paymentArr[0]);
						if (paymentArr != null) {
							if (!paymentArr[0].equalsIgnoreCase("p") && !paymentArr[0].equalsIgnoreCase("c")) {
								if (paymentArr[0].equalsIgnoreCase("I")) {
									Double paidAmntChecker = bd.getPaidAmtChecker(formDTO.getRegInitNumber(), sourceModFlag, paymentType);
									formDTO.setNetPaidAmt(paidAmntChecker.toString());
									Double totalBal = Double.parseDouble(formDTO.getNetRegFee()) - paidAmntChecker;
									a = totalBal.toString();
									boolean insertStatus = bd.insertPaymentDetails(formDTO.getRegInitNumber(), a, userId, cdate, tableName, sourceModFlag, paymentType, formDTO);
									// request.setAttribute("parentKey",
									// paymentArr[1]);
									request.setAttribute("parentKey", formDTO.getPmtTxnId());
									request.setAttribute("parentAmount", a);
									// Start-----done by akansha on 5thfeb for
									// cash automation issue
									session.setAttribute("parentAmountNew", a);
									// end
									forwardJsp = RegCompCheckerConstant.REG_COMP_CHECKER_PAYMENT;
								}
							}
							// ********************PENDING**************************//
							else if (paymentArr[0].equalsIgnoreCase("p")) {
								Double paidAmntChecker = bd.getPaidAmtChecker(formDTO.getRegInitNumber(), sourceModFlag, paymentType);
								formDTO.setNetPaidAmt(paidAmntChecker.toString());
								Double totalBal = Double.parseDouble(formDTO.getNetRegFee()) - paidAmntChecker;
								a = totalBal.toString();
								// String
								// param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
								boolean insertStatus = bd.insertPaymentDetails(formDTO.getRegInitNumber(), a, userId, cdate, tableName, sourceModFlag, paymentType, formDTO);
								logger.debug("----------------->payment insertion status:-" + insertStatus);
								request.setAttribute("parentKey", formDTO.getPmtTxnId());
								request.setAttribute("parentAmount", a);
								// Start-----done by akansha on 5thfeb for cash
								// automation issue
								session.setAttribute("parentAmountNew", a);
								// end
								if (insertStatus) {
									forwardJsp = RegCompCheckerConstant.REG_COMP_CHECKER_PAYMENT;
								}
							} else if (paymentArr[0].equalsIgnoreCase("c")) {
								String deedId = bd.getDeedId(formDTO.getRegInitNumber());
								if (deedId.equals("1053")) // ****ConveyanceDeed
								{
									formDTO.setConvynceDeedCheck("Y");
								}
								// String
								// instId=bd.getInstId(formDTO.getRegInitNumber());
								String bookId = bd.getbookId(formDTO.getRegInitNumber());
								if (bookId != null && !bookId.equalsIgnoreCase("")) {
									String bookName = bd.getbookName(bookId);
									if (bookName != null && !bookName.equalsIgnoreCase("")) {
										formDTO.setBookId(bookId);
										formDTO.setBookName(bookName);
										eForm.setDisableBook("TRUE");
									} else {
										formDTO.setBookId("");
										formDTO.setBookName("");
									}
								} else {
									formDTO.setBookId("");
									formDTO.setBookName("");
								}
								ArrayList bookDetails = bd.getBookDetails();
								eForm.setBookDetailsList(bookDetails);
								boolean flag = bd.insertFinalDuties(formDTO.getRegInitNumber(), eForm, userId, tDate);
								forwardJsp = RegCompCheckerConstant.BOOK_TYPE_SELECTION;
							} else {
								// request.setAttribute(RegCompCheckerConstant.FAILURE_MSG,
								// "Registration Completion Failed");
								if ("en".equalsIgnoreCase(language)) {
									request.setAttribute(RegCompCheckerConstant.FAILURE_MSG, "Registration Completion Failed");
								} else {
									request.setAttribute(RegCompCheckerConstant.FAILURE_MSG, "पंजीकरण पूरा होने में विफल रहा है |");
								}
								forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;;
							}
						} else {
							boolean insertStatus = bd.insertPaymentDetails(formDTO.getRegInitNumber(), a, userId, cdate, tableName, sourceModFlag, paymentType, formDTO);
							logger.debug("----------------->payment insertion status:-" + insertStatus);
							request.setAttribute("parentKey", formDTO.getPmtTxnId());
							if (insertStatus) {
								forwardJsp = RegCompCheckerConstant.REG_COMP_CHECKER_PAYMENT;
							} else {
								// request.setAttribute(RegCompCheckerConstant.FAILURE_MSG,
								// "Registration Completion Failed");
								if ("en".equalsIgnoreCase(language)) {
									request.setAttribute(RegCompCheckerConstant.FAILURE_MSG, "Registration Completion Failed");
								} else {
									request.setAttribute(RegCompCheckerConstant.FAILURE_MSG, "पंजीकरण पूरा होने में विफल रहा है |");
								}
								forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
							}
						}
					} else {
						String deedId = bd.getDeedId(formDTO.getRegInitNumber());
						if (deedId.equals("1053")) // ****ConveyanceDeed
						{
							formDTO.setConvynceDeedCheck("Y");
						}
						// String
						// instId=bd.getInstId(formDTO.getRegInitNumber());
						String bookId = bd.getbookId(formDTO.getRegInitNumber());
						if (bookId != null && !bookId.equalsIgnoreCase("")) {
							String bookName = bd.getbookName(bookId);
							if (bookName != null && !bookName.equalsIgnoreCase("")) {
								formDTO.setBookId(bookId);
								formDTO.setBookName(bookName);
								eForm.setDisableBook("TRUE");
							} else {
								formDTO.setBookId("");
								formDTO.setBookName("");
							}
						} else {
							formDTO.setBookId("");
							formDTO.setBookName("");
						}
						ArrayList bookDetails = bd.getBookDetails();
						eForm.setBookDetailsList(bookDetails);
						boolean flag = bd.insertFinalDuties(formDTO.getRegInitNumber(), eForm, userId, tDate);
						forwardJsp = RegCompCheckerConstant.BOOK_TYPE_SELECTION;
					}
				}
				if (RegCompCheckerConstant.NEXT_TO_FINAL_CONFIRMATION_SEAL_COMP.equalsIgnoreCase(actionName)) {
					String regSealCheck = bd.checkRegSealApllied(formDTO.getRegInitNumber());
					eForm.setRegSealCheck(regSealCheck);
					forwardJsp = RegCompCheckerConstant.REMAINING_SEAL;
				}
				if (RegCompCheckerConstant.NEXT_TO_FINAL_CONFIRMATION_SEAL.equalsIgnoreCase(actionName)) {
					String deedId = bd.getDeedId(formDTO.getRegInitNumber());
					String instId = bd.getInstId(formDTO.getRegInitNumber());
					formDTO.setDeedId(deedId);
					formDTO.setInstId(instId);
					String titleCertFlag = "";
					if (formDTO.getConvynceDeedCheck().equals("Y")) {
						titleCertFlag = formDTO.getTitleCertCheck();
						logger.debug("**********TITLE CERT CHECK*******" + titleCertFlag);
					}
					String RegCompleteNumber = bd.getRegCompletionNumber(officeId, userId, cdate, formDTO.getBookId(), formDTO.getRegInitNumber(), titleCertFlag, formDTO.getSroName(), deedId, instId);
					// logger.debug("Reg Comp Num in action"+RegCompleteNumber);
					String regSealCheck = bd.checkRegSealApllied(formDTO.getRegInitNumber());
					eForm.setRegSealCheck(regSealCheck);
					formDTO.setRegCompleteId(RegCompleteNumber);
					forwardJsp = RegCompCheckerConstant.REMAINING_SEAL;
				}
				if (RegCompCheckerConstant.NEXT_TO_FINAL_CONFIRMATION.equalsIgnoreCase(actionName)) {

					String responseCode = MergedFormAndSign.mergeFormA1(formDTO.getRegInitNumber());
					String titleCertFlag = "";
					if (formDTO.getConvynceDeedCheck().equals("Y")) {
						titleCertFlag = formDTO.getTitleCertCheck();
						logger.debug("**********TITLE CERT CHECK*******" + titleCertFlag);
					}
					/*
					 * boolean sealInsertion =
					 * bd.insertSealsData(formDTO.getRegInitNumber(),
					 * formDTO.getSealId(), formDTO.getSealSubTypeId());
					 * logger.debug("reg fee seal insertion****"+sealInsertion);
					 * formDTO.setSeal(""); formDTO.setSealId("");
					 * formDTO.setSealSubTypeId("");
					 * 
					 * 
					 * boolean regsealInsertion =
					 * bd.insertSealsData(formDTO.getRegInitNumber(),
					 * formDTO.getRegSealId(), formDTO.getRegSealSubTypeId());
					 * logger.debug("reg seal insertion****"+regsealInsertion);
					 * formDTO.setRegSeal(""); formDTO.setRegSealId("");
					 * formDTO.setRegSealSubTypeId("");
					 * 
					 * boolean stampsealInsertion =
					 * bd.insertSealsData(formDTO.getRegInitNumber(),
					 * formDTO.getStampSealId(),
					 * formDTO.getStampSealSubTypeId());
					 * logger.debug("stamp seal insertion****"
					 * +stampsealInsertion); formDTO.setStampSeal("");
					 * formDTO.setStampSealId("");
					 * formDTO.setStampSealSubTypeId("");
					 */
					ArrayList list = bd.checksBeforeCompletion(formDTO.getRegInitNumber(), formDTO);
					if (formDTO.getCompChcek().equals("C")) {
						eForm.setCaveatDetailsList(list);
						messages.add("ERROR_MSG", new ActionMessage("caveat.found"));
						messages.add("ALERT_MSG", new ActionMessage("alert.completion"));
						saveMessages(request, messages);
						eForm.setCheck("CAVCHK");
						forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
					} else if (formDTO.getCompChcek().equals("L")) {
						eForm.setPropLockDetlsList(list);
						messages.add("ERROR_MSG", new ActionMessage("propLock.found"));
						messages.add("ALERT_MSG", new ActionMessage("alert.completion"));
						saveMessages(request, messages);
						eForm.setCheck("PLCHK");
						forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
					} else if (formDTO.getCompChcek().equals("CS")) {
						eForm.setCaseDetails(list);
						messages.add("ERROR_MSG", new ActionMessage("case.found"));
						messages.add("ALERT_MSG", new ActionMessage("alert.completion"));
						saveMessages(request, messages);
						eForm.setCheck("CSCHK");
						forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
					} else {
						String deedId = bd.getDeedId(formDTO.getRegInitNumber());
						String instId = bd.getInstId(formDTO.getRegInitNumber());
						formDTO.setDeedId(deedId);
						formDTO.setInstId(instId);
						String compStatus;
						/*
						 * if((deedId.equals(RegCompCheckerConstant.CERTIFICATION_OF_SALE
						 * )&&
						 * instId.equals(RegCompCheckerConstant.SALE_DEED_INST_1
						 * ))||
						 * (deedId.equals(RegCompCheckerConstant.CONVEYANCE_DEED
						 * ) &&(instId.equals(RegCompCheckerConstant.
						 * CONVEYANCE_DEED_INST_1) ||
						 * instId.equals(RegCompCheckerConstant
						 * .CONVEYANCE_DEED_INST_2) ||
						 * instId.equals(RegCompCheckerConstant
						 * .CONVEYANCE_DEED_INST_3) ||
						 * instId.equals(RegCompCheckerConstant
						 * .CONVEYANCE_DEED_INST_4) ||
						 * instId.equals(RegCompCheckerConstant
						 * .CONVEYANCE_DEED_INST_5))) ||
						 * (deedId.equals(RegCompCheckerConstant.EXCHANGE_DEED)
						 * &&
						 * instId.equals(RegCompCheckerConstant.EXCAHNGE_DEED_INST_1
						 * )) ||
						 * (deedId.equals(RegCompCheckerConstant.GIFT_DEED) &&
						 * instId
						 * .equals(RegCompCheckerConstant.GIFT_DEED_INST_1)) ||
						 * (deedId.equals(RegCompCheckerConstant.LEASE_DEED) &&
						 * (
						 * instId.equals(RegCompCheckerConstant.LEASE_DEED_INST_1
						 * ) )) ||
						 * (deedId.equals(RegCompCheckerConstant.RELEASE_DEED)
						 * &&
						 * instId.equals(RegCompCheckerConstant.RELAESE_DEED_INST_1
						 * )) ||
						 * (deedId.equals(RegCompCheckerConstant.PARTITION_DEED)
						 * &&(instId.equals(RegCompCheckerConstant.
						 * PARTITION_DEED_INST_1) ||
						 * instId.equals(RegCompCheckerConstant
						 * .PARTITION_DEED_INST_2) ||
						 * instId.equals(RegCompCheckerConstant
						 * .PARTITION_DEED_INST_3)))) { compStatus = "19"; }
						 * else
						 */
						{
							compStatus = "17";
						}
						// String RegCompleteNumber =
						// bd.getRegCompletionNumber(officeId,userId, cdate,
						// formDTO.getBookId(),
						// formDTO.getRegInitNumber(),titleCertFlag,formDTO.getSroName(),deedId,instId
						// );
						// logger.debug("Reg Comp Num in action"+RegCompleteNumber);
						String regCompNumber = bd.getCompletionNumber(formDTO.getRegInitNumber());
						bd.updateFinalDetails(formDTO.getRegInitNumber(), regCompNumber, userId, cdate, officeId, "N", mDto.getOfficeName());
						// status changes to 17
						boolean updateStatus = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), compStatus, cdate, userId);
						// done by akansha for mutation
						if (updateStatus) {
							String getClrFlag = bd.getclrFlag(formDTO.getRegInitNumber());
							if (getClrFlag != null && !getClrFlag.isEmpty()) {
								if (getClrFlag.equalsIgnoreCase("y")) {
									String mutationStatus = "1";
									boolean updateMutationStatus = bd.updateMutationStatus(formDTO.getRegInitNumber(), mutationStatus);
								}
							}
						}
						
						String RegCompleteNumber = bd.getCompletionNumber(formDTO.getRegInitNumber());
						formDTO.setRegCompleteId(RegCompleteNumber);
						// TODO :- PIN GENERATION CHECK
						/*
						 * if((deedId.equals(RegCompCheckerConstant.CERTIFICATION_OF_SALE
						 * )&&
						 * instId.equals(RegCompCheckerConstant.SALE_DEED_INST_1
						 * ))||
						 * (deedId.equals(RegCompCheckerConstant.CONVEYANCE_DEED
						 * ) &&(instId.equals(RegCompCheckerConstant.
						 * CONVEYANCE_DEED_INST_1) ||
						 * instId.equals(RegCompCheckerConstant
						 * .CONVEYANCE_DEED_INST_2) ||
						 * instId.equals(RegCompCheckerConstant
						 * .CONVEYANCE_DEED_INST_3) ||
						 * instId.equals(RegCompCheckerConstant
						 * .CONVEYANCE_DEED_INST_4) ||
						 * instId.equals(RegCompCheckerConstant
						 * .CONVEYANCE_DEED_INST_5))) ||
						 * (deedId.equals(RegCompCheckerConstant.EXCHANGE_DEED)
						 * &&
						 * instId.equals(RegCompCheckerConstant.EXCAHNGE_DEED_INST_1
						 * )) ||
						 * (deedId.equals(RegCompCheckerConstant.GIFT_DEED) &&
						 * instId
						 * .equals(RegCompCheckerConstant.GIFT_DEED_INST_1)) ||
						 * (deedId.equals(RegCompCheckerConstant.LEASE_DEED) &&
						 * (
						 * instId.equals(RegCompCheckerConstant.LEASE_DEED_INST_1
						 * ) )) ||
						 * (deedId.equals(RegCompCheckerConstant.RELEASE_DEED)
						 * &&
						 * instId.equals(RegCompCheckerConstant.RELAESE_DEED_INST_1
						 * )) ||
						 * (deedId.equals(RegCompCheckerConstant.PARTITION_DEED)
						 * &&(instId.equals(RegCompCheckerConstant.
						 * PARTITION_DEED_INST_1) ||
						 * instId.equals(RegCompCheckerConstant
						 * .PARTITION_DEED_INST_2) ||
						 * instId.equals(RegCompCheckerConstant
						 * .PARTITION_DEED_INST_3)))) {
						 * formDTO.setPinGenerationCheck("Y");
						 * //messages.add("CONF_MSG", new ActionMessage(
						 * //"reg.registration.completion.confirmation"));
						 * //saveMessages(request, messages);
						 * //messages.add("CONF_MSG", new ActionMessage(
						 * //"reg.registration.completion.confirmation"));
						 * if("en".equalsIgnoreCase(language)) {
						 * request.setAttribute
						 * ("CONF_MSG","Registration completed successfully"); }
						 * else {request.setAttribute("CONF_MSG",
						 * "पंजीकरण सफलतापूर्वक पूरा हो गया है|"); } forwardJsp
						 * = RegCompCheckerConstant.CONFIRMATION_SCREEN;
						 * 
						 * } else
						 */
						{
							/*
							 * EtokenChange eChange = new
							 * EtokenChange(formDTO.getRegInitNumber
							 * (),"17",null); Thread t = new Thread(eChange);
							 * t.start();
							 */
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute(RegCompCheckerConstant.FINAL_CONF_MSG, "Registration Completed Successfully");
							} else {
								request.setAttribute(RegCompCheckerConstant.FINAL_CONF_MSG, "पंजीकरण सफलतापूर्वक पूरा हो गया है |");
							}
							forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
						}
					}
				}
				if (RegCompCheckerConstant.SEAL_ACTION.equalsIgnoreCase(actionName)) {
					logger.debug("*****SEAL ACTION******");
					String sealId = "";
					if (formDTO.getSealCheck() != null) {
						logger.debug("check not null");
						// logger.debug(formDTO.getSealCheck());
						if (formDTO.getSealCheck().equals("R")) {
							sealId = formDTO.getRegSealId();
							ArrayList subTypeList = bd.getSealSubTypeIds(sealId);
							eForm.setRegSealSubTypeList(subTypeList);
							formDTO.setSealCheck(null);
						} else if (formDTO.getSealCheck().equals("S")) {
							sealId = formDTO.getStampSealId();
							ArrayList subTypeList = bd.getSealSubTypeIds(sealId);
							eForm.setStampSealSubTypeList(subTypeList);
							formDTO.setSealCheck(null);
						} else {
							sealId = formDTO.getSealId();
							ArrayList subTypeList = bd.getSealSubTypeIds(sealId);
							eForm.setSealSubTypeList(subTypeList);
						}
					} else {
						sealId = formDTO.getSealId();
						ArrayList subTypeList = bd.getSealSubTypeIds(sealId);
						eForm.setSealSubTypeList(subTypeList);
					}
					// logger.debug("***sealID"+sealId);
					if (sealId.equals(RegCompCheckerConstant.PRESENTATION_SEAl)) {
						forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
					} else if (sealId.equals(RegCompCheckerConstant.WITNESS_SEAl)) {
						forwardJsp = RegCompCheckerConstant.WITNESS_VIEW;
					} else if (sealId.equals(RegCompCheckerConstant.EXECUTION_SEAl)) {
						String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
						if (deedInstArray != null && deedInstArray.length > 0) {
							request.setAttribute("deedId", deedInstArray[0].trim());
							request.setAttribute("instId", deedInstArray[1].trim());
						}
						forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
					} else if (sealId.equals(RegCompCheckerConstant.STAMPDUTY_SEAl)) {
						// String
						// instId=bd.getInstId(formDTO.getRegInitNumber());
						String bookId = bd.getbookId(formDTO.getRegInitNumber());
						if (bookId != null && !bookId.equalsIgnoreCase("")) {
							String bookName = bd.getbookName(bookId);
							if (bookName != null && !bookName.equalsIgnoreCase("")) {
								formDTO.setBookId(bookId);
								formDTO.setBookName(bookName);
								eForm.setDisableBook("TRUE");
							} else {
								formDTO.setBookId("");
								formDTO.setBookName("");
							}
						} else {
							formDTO.setBookId("");
							formDTO.setBookName("");
						}
						forwardJsp = RegCompCheckerConstant.BOOK_TYPE_SELECTION;
					} else if (sealId.equals(RegCompCheckerConstant.REGISTRATIONFEE_SEAl)) {
						// String
						// instId=bd.getInstId(formDTO.getRegInitNumber());
						String bookId = bd.getbookId(formDTO.getRegInitNumber());
						if (bookId != null && !bookId.equalsIgnoreCase("")) {
							String bookName = bd.getbookName(bookId);
							if (bookName != null && !bookName.equalsIgnoreCase("")) {
								formDTO.setBookId(bookId);
								formDTO.setBookName(bookName);
								eForm.setDisableBook("TRUE");
							} else {
								formDTO.setBookId("");
								formDTO.setBookName("");
							}
						} else {
							formDTO.setBookId("");
							formDTO.setBookName("");
						}
						forwardJsp = RegCompCheckerConstant.BOOK_TYPE_SELECTION;
					} else if (sealId.equals(RegCompCheckerConstant.REGISTRATION_SEAl)) {
						// String
						// instId=bd.getInstId(formDTO.getRegInitNumber());
						String bookId = bd.getbookId(formDTO.getRegInitNumber());
						if (bookId != null && !bookId.equalsIgnoreCase("")) {
							String bookName = bd.getbookName(bookId);
							if (bookName != null && !bookName.equalsIgnoreCase("")) {
								formDTO.setBookId(bookId);
								formDTO.setBookName(bookName);
								eForm.setDisableBook("TRUE");
							} else {
								formDTO.setBookId("");
								formDTO.setBookName("");
							}
						} else {
							formDTO.setBookId("");
							formDTO.setBookName("");
						}
						forwardJsp = RegCompCheckerConstant.BOOK_TYPE_SELECTION;
					}
				}
				// 25-feb
				if (RegCompCheckerConstant.SEAL_CONTENT.equalsIgnoreCase(actionName)) {
					request.setAttribute("page", "sealForm1");
					request.setAttribute("regNumber", formDTO.getRegInitNumber());
					// request.setAttribute("regForm",eForm );
					request.setAttribute("sealId", "1");
					request.setAttribute("partyPresentArray", formDTO.getSelectedPartyIds());
					request.setAttribute("headerImagePath", eForm.getHeaderImg());
					request.setAttribute("backPage", "presentation");
					forwardJsp = "forwardToSeal";
				}
				if (RegCompCheckerConstant.PRESENTATION_SEAL_COMPLETE.equalsIgnoreCase(actionName)) {
					formDTO.setSeal("");
					formDTO.setSealId("");
					formDTO.setSealSubTypeId("");
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					if (deedInstArray != null && deedInstArray.length > 0) {
						// logger.debug("************"+deedInstArray[0].trim());
						request.setAttribute("deedId", deedInstArray[0].trim());
						request.setAttribute("instId", deedInstArray[1].trim());
					}
					propertyRelatedComplianceMap = bd.getPropertyRelatedComplianeList(formDTO.getRegInitNumber());
					eForm.setCompliancePropertyRelatedMap(propertyRelatedComplianceMap);
					partyRelatedComplianceMap = bd.getPartyRelatedComplianeList(formDTO.getRegInitNumber(), language);
					eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
					logger.debug("hello");
					witnessPhotoUploadMap = bd.getWitnessDetailsForCompliance(formDTO.getRegInitNumber(), language);
					eForm.setUploadWitnessPhotograph(witnessPhotoUploadMap);
					forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
				}
				if (RegCompCheckerConstant.ADMISSION_SEAL_COMPLETE.equals(actionName)) {
					formDTO.setSeal("");
					formDTO.setSealId("");
					formDTO.setSealSubTypeId("");
					forwardJsp = RegCompCheckerConstant.ADD_SEALS_PAGE;
				}
				if (RegCompCheckerConstant.STAMP_SEAL_COMPLETE.equals(actionName)) {
					formDTO.setSeal("");
					formDTO.setSealId("");
					formDTO.setSealSubTypeId("");
					String bookId = bd.getbookId(formDTO.getRegInitNumber());
					if (bookId != null && !bookId.equalsIgnoreCase("")) {
						String bookName = bd.getbookName(bookId);
						if (bookName != null && !bookName.equalsIgnoreCase("")) {
							formDTO.setBookId(bookId);
							formDTO.setBookName(bookName);
							eForm.setDisableBook("TRUE");
						} else {
							formDTO.setBookId("");
							formDTO.setBookName("");
						}
					} else {
						formDTO.setBookId("");
						formDTO.setBookName("");
					}
					ArrayList bookDetails = bd.getBookDetails();
					eForm.setBookDetailsList(bookDetails);
					forwardJsp = RegCompCheckerConstant.BOOK_TYPE_SELECTION;
				}
				if (RegCompCheckerConstant.GENERATE_PIN.equalsIgnoreCase(actionName)) {
					logger.debug("generate pin action");
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					if (deedInstArray != null && deedInstArray.length > 0) {
						logger.debug("************deedId" + deedInstArray[0].trim());
						formDTO.setDeedId(deedInstArray[0].trim());
					}
					String regInitNumber = bd.getInitiationNumber(formDTO.getRegCompleteId());
					boolean pinFlag = bd.pinGeneration(regInitNumber, formDTO.getDeedId(), userId);
					logger.debug("pin insertion  " + pinFlag);
					if (pinFlag) {
						boolean flag = bd.UpdateRegistrationCompletionStatus(regInitNumber, "17", cdate, userId);
					}
					formDTO.setPinGenerationCheck("N");
					formDTO.setPinPrintChk("Y");
					// request.setAttribute(RegCompCheckerConstant.FINAL_CONF_MSG,
					// "Registration Completed Successfully");
					if ("en".equalsIgnoreCase(language)) {
						request.setAttribute(RegCompCheckerConstant.FINAL_CONF_MSG, "Registration Completed Successfully");
					} else {
						request.setAttribute(RegCompCheckerConstant.FINAL_CONF_MSG, "पंजीकरण सफलतापूर्वक पूरा हो गया है |");
					}
					forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
				}
				if (RegCompCheckerConstant.PRINT_PIN.equalsIgnoreCase(actionName)) {
					logger.debug("print pin");
					bd.printPin(formDTO.getRegInitNumber(), formDTO, request, response);
				}
				if (RegCompCheckerConstant.PRINT_FINAL_DOC.equalsIgnoreCase(actionName)) {
					logger.debug("print final doc");
					if ("en".equalsIgnoreCase(language)) {
						request.setAttribute(RegCompCheckerConstant.FINAL_CONF_MSG, "Registration Completed Successfully");
					} else {
						request.setAttribute(RegCompCheckerConstant.FINAL_CONF_MSG, "पंजीकरण सफलतापूर्वक पूरा हो गया है |");
					}
					boolean flag = bd.generateFinalCertificate(eForm, userId, officeId, language);
					logger.debug("final doc flag" + flag);
					if (flag) {
						formDTO.setForwardName("regCompChecker");
						String pathSr = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_SR_SIGN;
						formDTO.setParentPathSrSign(pathSr);
						formDTO.setFileNameSrSign("signature.GIF");
						formDTO.setForwardPathSr("/regCompChecker.do?label=SrSignature");
						formDTO.setGuidUpload("");
					}
					forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
				}
				if (RegCompCheckerConstant.PRINT_PREVIEW.equalsIgnoreCase(actionName)) {
					eForm.setHeaderImg(headerimg);
					eForm.getRegDTO().setRegInitNumber(formDTO.getRegInitNumber());
					String uid = bd.getUsrId(formDTO.getRegInitNumber());
					String oid = bd.getOfcId(formDTO.getRegInitNumber());
					eForm.setUserId(uid);
					SealsBD sealbd = new SealsBD();
					String lang = sealbd.getLangauge(formDTO.getRegInitNumber());
					bd.generateFinalCertificatePrint(eForm, uid, oid, lang, response);
					forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
					// eForm.getRegDTO().setRegCompleteId(eRegNo);
				}
				if (RegCompCheckerConstant.GENERATE_OTP.equalsIgnoreCase(actionName)) {
					logger.debug("In  generate OTP in Checker Action ");
					OTPUtility otp = new OTPUtility();
					boolean flag = otp.generateOTP(userId, eForm.getRegDTO().getRegCompleteId(), "CHECKER");
					if (formDTO.getRegInitNumber().startsWith("0")) {
						formDTO.setRegInitNumber(formDTO.getRegInitNumber().substring(1));
					}
					boolean check = bd.checkStatus(formDTO.getRegInitNumber());
					if (check) {
						logger.debug("ALREADY REGISTERED");
						String regCompNumber = bd.getCompletionNumber(formDTO.getRegInitNumber());
						formDTO.setRegCompleteId(regCompNumber);
						String deedId = bd.getDeedId(formDTO.getRegInitNumber());
						String instId = bd.getInstId(formDTO.getRegInitNumber());
						formDTO.setDeedId(deedId);
						formDTO.setInstId(instId);
						// In which pin is required
						if ((deedId.equals(RegCompCheckerConstant.CERTIFICATION_OF_SALE) && instId.equals(RegCompCheckerConstant.SALE_DEED_INST_1)) || (deedId.equals(RegCompCheckerConstant.CONVEYANCE_DEED) && (instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_1) || instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_2) || instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_3) || instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_4) || instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_5))) || (deedId.equals(RegCompCheckerConstant.EXCHANGE_DEED) && instId.equals(RegCompCheckerConstant.EXCAHNGE_DEED_INST_1)) || (deedId.equals(RegCompCheckerConstant.GIFT_DEED) && instId.equals(RegCompCheckerConstant.GIFT_DEED_INST_1)) || (deedId.equals(RegCompCheckerConstant.LEASE_DEED) && (instId.equals(RegCompCheckerConstant.LEASE_DEED_INST_1))) || (deedId.equals(RegCompCheckerConstant.RELEASE_DEED) && instId.equals(RegCompCheckerConstant.RELAESE_DEED_INST_1)) || (deedId.equals(RegCompCheckerConstant.PARTITION_DEED) && (instId.equals(RegCompCheckerConstant.PARTITION_DEED_INST_1) || instId.equals(RegCompCheckerConstant.PARTITION_DEED_INST_2) || instId.equals(RegCompCheckerConstant.PARTITION_DEED_INST_3)))) {
							formDTO.setPinPrintChk("Y");
						}
						if (bd.checkFinalDocGen(formDTO.getRegInitNumber())) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute(RegCompCheckerConstant.FINAL_MSG, "Registration has been completed for this Registration Initiation Number");
							} else {
								request.setAttribute(RegCompCheckerConstant.FINAL_MSG, "इस पंजीकरण शुरूआत संख्या के लिए पंजीकरण पूरा हो चुका है|");
							}
						} else {
							String pathSr = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_SR_SIGN;
							formDTO.setParentPathSrSign(pathSr);
							formDTO.setFileNameSrSign("signature.GIF");
							formDTO.setForwardPathSr("/regCompChecker.do?label=SrSignature");
							formDTO.setGuidUpload("");
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute(RegCompCheckerConstant.FINAL_CONF_MSG, "Registration Completed Successfully");
							} else {
								request.setAttribute(RegCompCheckerConstant.FINAL_CONF_MSG, "पंजीकरण सफलतापूर्वक पूरा हो गया है|");
							}
						}
						forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
					}
				}
				if (RegCompCheckerConstant.VALIDATE_OTP.equalsIgnoreCase(actionName)) {
					logger.debug("In  Valdiate OTP in Checker Action ");
					OTPUtility otp = new OTPUtility();
					boolean flag = otp.validateOTP(eForm.getRegDTO().getRegCompleteId(), "CHECKER", userId, eForm.getOtp());
					flag = true;
					if (formDTO.getRegInitNumber().startsWith("0")) {
						formDTO.setRegInitNumber(formDTO.getRegInitNumber().substring(1));
					}
					boolean check = bd.checkStatus(formDTO.getRegInitNumber());
					if (check) {
						logger.debug("ALREADY REGISTERED");
						String regCompNumber = bd.getCompletionNumber(formDTO.getRegInitNumber());
						formDTO.setRegCompleteId(regCompNumber);
						String deedId = bd.getDeedId(formDTO.getRegInitNumber());
						String instId = bd.getInstId(formDTO.getRegInitNumber());
						formDTO.setDeedId(deedId);
						formDTO.setInstId(instId);
						// In which pin is required
						if ((deedId.equals(RegCompCheckerConstant.CERTIFICATION_OF_SALE) && instId.equals(RegCompCheckerConstant.SALE_DEED_INST_1)) || (deedId.equals(RegCompCheckerConstant.CONVEYANCE_DEED) && (instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_1) || instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_2) || instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_3) || instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_4) || instId.equals(RegCompCheckerConstant.CONVEYANCE_DEED_INST_5))) || (deedId.equals(RegCompCheckerConstant.EXCHANGE_DEED) && instId.equals(RegCompCheckerConstant.EXCAHNGE_DEED_INST_1)) || (deedId.equals(RegCompCheckerConstant.GIFT_DEED) && instId.equals(RegCompCheckerConstant.GIFT_DEED_INST_1)) || (deedId.equals(RegCompCheckerConstant.LEASE_DEED) && (instId.equals(RegCompCheckerConstant.LEASE_DEED_INST_1))) || (deedId.equals(RegCompCheckerConstant.RELEASE_DEED) && instId.equals(RegCompCheckerConstant.RELAESE_DEED_INST_1)) || (deedId.equals(RegCompCheckerConstant.PARTITION_DEED) && (instId.equals(RegCompCheckerConstant.PARTITION_DEED_INST_1) || instId.equals(RegCompCheckerConstant.PARTITION_DEED_INST_2) || instId.equals(RegCompCheckerConstant.PARTITION_DEED_INST_3)))) {
							formDTO.setPinPrintChk("Y");
						}
						if (bd.checkFinalDocGen(formDTO.getRegInitNumber())) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute(RegCompCheckerConstant.FINAL_MSG, "Registration has been completed for this Registration Initiation Number");
							} else {
								request.setAttribute(RegCompCheckerConstant.FINAL_MSG, "इस पंजीकरण शुरूआत संख्या के लिए पंजीकरण पूरा हो चुका है|");
							}
						} else {
							String pathSr = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_SR_SIGN;
							formDTO.setParentPathSrSign(pathSr);
							formDTO.setFileNameSrSign("signature.GIF");
							formDTO.setForwardPathSr("/regCompChecker.do?label=SrSignature");
							formDTO.setGuidUpload("");
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute(RegCompCheckerConstant.FINAL_CONF_MSG, "Registration Completed Successfully");
							} else {
								request.setAttribute(RegCompCheckerConstant.FINAL_CONF_MSG, "पंजीकरण सफलतापूर्वक पूरा हो गया है|");
							}
						}
						if (flag) {
							eForm.setAllowPrint("Y");
							eForm.setPrintFlag("N");
							forwardJsp = "ecodePageForZeroBal";
						} else {
							eForm.setPrintFlag("Y");
						}
						if (flag) {
							forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
						}
					}
				}
				if (RegCompCheckerConstant.REFUSAL_SEAL.equalsIgnoreCase(actionName)) {
					logger.debug("------refusal seal-------------");
					request.setAttribute("page", "sealForm1");
					request.setAttribute("regNumber", formDTO.getRegInitNumber());
					// request.setAttribute("regForm",eForm );
					request.setAttribute("sealId", "14");
					request.setAttribute("headerImagePath", eForm.getHeaderImg());
					request.setAttribute("backPage", "refusal");
					forwardJsp = "forwardToSeal";
					// boolean flag =
					// bd.UpdateRegistrationCompletionStatusRefusal(formDTO.getRegInitNumber(),
					// RegCompCheckerConstant.REFUSAL_STATUS,cdate,userId,formDTO.getRemarks());
					// /formDTO.setRemarks("");
					// eForm.setCheck("REFCHK");
					// forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
				}
				if (RegCompCheckerConstant.REFUSAL_SEAL_COMPLETE.equalsIgnoreCase(actionName)) {
					boolean flag = bd.UpdateRegistrationCompletionStatusRefusal(formDTO.getRegInitNumber(), RegCompCheckerConstant.REFUSAL_STATUS, cdate, userId, formDTO.getRemarks());
					//	eForm.setRefusalReason(formDTO.getRemarks());
					//request.setAttribute("REFUSALMSG"," "+formDTO.getRemarks());   commented for refusal seal remarks
					formDTO.setRemarks("");
					eForm.setCheck("REFCHK");
					forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
				}
				if (RegCompCheckerConstant.PRINT_FINAL_DOC_PDF.equalsIgnoreCase(actionName)) {
					boolean flag = bd.generateFinalCertificateRefusal(eForm, userId, officeId, language);
					logger.debug("final doc flag" + flag);
					logger.debug("final doc flag" + flag);
					if (flag) {
						formDTO.setForwardName("regCompChecker");
						String pathSr = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_SR_SIGN;
						formDTO.setParentPathSrSign(pathSr);
						formDTO.setFileNameSrSign("signature.GIF");
						formDTO.setForwardPathSr("/regCompChecker.do?label=SrSignature");
						formDTO.setGuidUpload("");
					}
					forwardJsp = RegCompCheckerConstant.CONFIRMATION_SCREEN;
				}
				if (RegCompCheckerConstant.PARTY_PRESENT_THUMB.equalsIgnoreCase(actionName)) {
					logger.debug("FINGER PRINT");
					logger.debug("***************" + formDTO.getPartyIdUpload());
					ArrayList partyList = eForm.getPartiesPresentList();
					Iterator itr = partyList.iterator();
					while (itr.hasNext()) {
						RegCompCheckerDTO rrdto = (RegCompCheckerDTO) itr.next();
						String partyId = rrdto.getPartyTxnId();
						logger.debug("photo id for " + partyId + " is " + rrdto.getSelectedPhotoId());
						if (formDTO.getPartyIdUpload().equalsIgnoreCase(partyId)) {
							logger.debug("************MATCH");
							rrdto.setFingerPrintName("FingerPrint.GIF");
							rrdto.setFingerPrintPath(formDTO.getParentPathFP() + "/" + partyId + "/FingerPrint.GIF");
							rrdto.setPartyThumbChk("Y");
							// String
							// remarks[]=request.getParameterValues("regDTO.thumbRemarks");
							boolean flag = bd.imageCheck(rrdto.getFingerPrintPath());
							if (flag) {
								flag = bd.updateThumbDetails(partyId, rrdto.getFingerPrintName(), rrdto.getFingerPrintPath(), "");
							}
							if (flag) { // Added by ankit for Aadhar Validation
								// check - start
								if ("7".equalsIgnoreCase(rrdto.getSelectedPhotoId())) {
									String base64 = formDTO.getBase64String();
									boolean isEkyc = callEKYCWebService(eForm, formDTO, bd, language, actionName, base64, userId);
									AadharDetailDTO aadharDts = new RegCompCheckerBD().getAadharDetailsByPartyTxnId(partyId);
									if (aadharDts.getACKID_CHECKER() != null && aadharDts != null && !aadharDts.getACKID_CHECKER().isEmpty()) {
										rrdto.setIsAadharValidated("true");
										rrdto.setAadharRespCode("");
										rrdto.setAadharErrMsg("");
									} else {
										rrdto.setIsAadharValidated("false");
										rrdto.setAadharRespCode(formDTO.getAadharRespCode());
										rrdto.setAadharErrMsg(formDTO.getAadharErrMsg());
									}
								}
								// Added by ankit for Aadhar Validation check -
								// end
							} else {
								rrdto.setFingerPrintName("");
								rrdto.setFingerPrintPath("");
								rrdto.setPartyThumbChk("N");
								ArrayList error = new ArrayList();
								error.add("Unable to upload file. Please try again.");
								request.setAttribute("ERROR_LIST", error);
								request.setAttribute("ERROR_FLAG", "true");
							}
						}
					}
					// {
					// ArrayList partyPresentList =
					// eForm.getPartiesPresentList();
					/*
					 * String selectedPartyArr[] =
					 * formDTO.getSelectedPartyIds().split("_"); Iterator itr1 =
					 * partyPresentList.iterator(); //ArrayList partyDetails =
					 * new ArrayList(); while(itr1.hasNext()) {
					 * logger.debug("inside while"); RegCompCheckerDTO rDTO =
					 * (RegCompCheckerDTO)itr1.next(); String party =
					 * rDTO.getPartyTxnId(); // logger.debug("party"+party);
					 * logger.debug("selectedpart arr"+selectedPartyArr.length);
					 * for(int i = 0; i<selectedPartyArr.length;i++) { String
					 * tmpArr[] = selectedPartyArr[i].split(",");
					 * logger.debug("size of temparr"+tmpArr.length);
					 * if(party.equals(tmpArr[0])) { logger.debug("match");
					 * rDTO.setStatus("Y"); rDTO.setIsGovtOfficial(tmpArr[1]);
					 * rDTO.setGovtOfficial(tmpArr[2]);
					 * rDTO.setReprsnName(tmpArr[3]);
					 * rDTO.setThumbRemarks(tmpArr[4]); break; } else {
					 * rDTO.setStatus("N"); rDTO.setGovtOfficial(" ");
					 * rDTO.setReprsnName(" "); } }
					 * 
					 * } eForm.setPartiesPresentList(partyPresentList);
					 * formDTO.setIsUpload("Y");
					 */
					/*
					 * ArrayList partyListtemp = eForm.getPartiesPresentList();
					 * Iterator itr2 = partyListtemp.iterator();
					 * while(itr2.hasNext()) { RegCompCheckerDTO rrdto =
					 * (RegCompCheckerDTO)itr2.next();
					 * logger.debug("party----->"+rrdto.getPartyTxnId());
					 * logger.debug("status----->"+rrdto.getStatus());
					 * logger.debug("govt----->"+rrdto.getIsGovtOfficial());
					 * logger.debug("govt name----->"+rrdto.getGovtOffId());
					 * logger.debug("rep name----->"+rrdto.getReprsnName());
					 * 
					 * 
					 * }
					 */
					forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
				}
				if("SAVE_FILE_PATH".equalsIgnoreCase(actionName)){

					String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
					
					String test = request.getParameter("uploadGUID");
					formDTO.setParentPathSign(path);
					formDTO.setFileNameSign("signature.GIF");
					formDTO.setForwardPath("/regCompChecker.do?txnPartyIdSignature=");
					formDTO.setForwardName("regCompChecker");
					//String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
					formDTO.setFileNameFP("FingerPrint.GIF");
					formDTO.setParentPathFP(path);
					// logger.debug("************partyId"+request.getParameter("txnPartyIdSignature"));
					String reqPara = formDTO.getNewPartySignPath();
					//String partyArr[] = reqPara.split("_");
					// String partyIdUpload =
					// request.getParameter("txnPartyIdSignature");
					String partyIdUpload = formDTO.getNewSignPartyTxnId();
					ArrayList partyList = eForm.getPartiesPresentList();
					Iterator itr = partyList.iterator();
					while (itr.hasNext()) {
						RegCompCheckerDTO rrdto = (RegCompCheckerDTO) itr.next();
						String partyId = rrdto.getPartyTxnId();
						if (partyIdUpload.equalsIgnoreCase(partyId)) {
							logger.debug("************MATCH");
							rrdto.setSignatureName(formDTO.getFileNameSign());
							rrdto.setSignaturePath(formDTO.getParentPathSign() + partyId + "/" + formDTO.getFileNameSign());
							rrdto.setPartySignChk("Y");
							boolean flag = bd.imageCheck(rrdto.getSignaturePath());
							System.out.println("after newgencrop method "+flag);
							if (flag) {
								flag = bd.updateSignDetails(partyId, rrdto.getSignatureName(), rrdto.getSignaturePath());

								System.out.println("after saving sign detail "+flag);
								}
							if (flag) {
								System.out.println("after saving sign detail "+flag);
								} else {
									System.out.println("after saving failed sign detail "+flag);
								rrdto.setSignatureName("");
								rrdto.setSignaturePath("");
								rrdto.setPartySignChk("N");
								ArrayList error = new ArrayList();
								error.add("Unable to upload file. Please try again.");
								request.setAttribute("ERROR_LIST", error);
								request.setAttribute("ERROR_FLAG", "true");
							}
						}
					}
					/*
					 * ArrayList partyPresentList = eForm.getPartiesPresentList();
					 * //String selectedPartyArr[] =
					 * formDTO.getSelectedPartyIds().split("_"); Iterator itr1 =
					 * partyPresentList.iterator(); //ArrayList partyDetails = new
					 * ArrayList(); while(itr1.hasNext()) {
					 * logger.debug("inside while"); RegCompCheckerDTO rDTO =
					 * (RegCompCheckerDTO)itr1.next(); String party =
					 * rDTO.getPartyTxnId(); // logger.debug("party"+party);
					 * logger.debug("selectedpart arr"+partyArr.length); for(int i =
					 * 1; i<partyArr.length;i++) { String tmpArr[] =
					 * partyArr[i].split(",");
					 * logger.debug("size of temparr"+tmpArr.length);
					 * if(party.equals(tmpArr[0])) { logger.debug("match");
					 * rDTO.setStatus("Y"); rDTO.setIsGovtOfficial(tmpArr[1]);
					 * rDTO.setGovtOfficial(tmpArr[2]); if(tmpArr.length ==3)
					 * rDTO.setReprsnName(""); else rDTO.setReprsnName(tmpArr[3]);
					 * if(tmpArr.length ==4 || tmpArr.length ==3)
					 * rDTO.setThumbRemarks(""); else
					 * rDTO.setThumbRemarks(tmpArr[4]);
					 * 
					 * 
					 * break; } else { rDTO.setStatus("N");
					 * rDTO.setGovtOfficial(" "); rDTO.setReprsnName(" "); } }
					 * 
					 * }
					 * 
					 * eForm.setPartiesPresentList(partyPresentList);
					 * formDTO.setIsUpload("Y");
					 */
					actionName = "";
					forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
				
				}
				if("SAVE_FILE_PATH_WITNESS".equalsIgnoreCase(actionName)){
					String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
					formDTO.setParentPathSign(path);
					formDTO.setFileNameSign("signature.GIF");
					formDTO.setForwardPath("/regCompChecker.do?txnPartyIdBiometric=");
					formDTO.setForwardName("regCompChecker");
					String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
					formDTO.setFileNameFP("FingerPrint.GIF");
					formDTO.setParentPathFP(pathFP);
					String reqPara = formDTO.getNewPartySignPath();
					//String partyArr[] = reqPara.split("_");
					// String partyIdUpload =
					// request.getParameter("txnPartyIdSignature");
					String partyIdUpload = formDTO.getNewSignPartyTxnId();
					ArrayList partyList = eForm.getPartiesDetailList();
					boolean flag = false;
					Iterator itr = partyList.iterator();
					while (itr.hasNext()) {
						RegCompCheckerDTO rrdto = (RegCompCheckerDTO) itr.next();
						String partyId = rrdto.getPartyTxnId();
						if (partyIdUpload.equalsIgnoreCase(partyId)) {
							logger.debug("************MATCH");
							rrdto.setSignaturePath(formDTO.getParentPathSign() + partyId + "/" + formDTO.getFileNameSign());
							rrdto.setSignatureName(formDTO.getFileNameSign());
							rrdto.setPartySignChk("Y");
							boolean flag1 = bd.imageCheck(rrdto.getSignaturePath());
							if (flag1) {
								flag1 = bd.updateSignDetails(partyId, rrdto.getSignatureName(), rrdto.getSignaturePath());
							}
							if (flag1) {} else {
								rrdto.setSignaturePath("");
								rrdto.setSignatureName("");
								rrdto.setPartySignChk("N");
								ArrayList error = new ArrayList();
								error.add("Unable to upload file. Please try again.");
								request.setAttribute("ERROR_LIST", error);
								request.setAttribute("ERROR_FLAG", "true");
							}
							flag = true;
						}
					}
					if (!flag) {
						ArrayList partyList2 = eForm.getWitnessDetailsList();
						Iterator itr2 = partyList2.iterator();
						while (itr2.hasNext()) {
							RegCompCheckerDTO rrdto1 = (RegCompCheckerDTO) itr2.next();
							String partyId = rrdto1.getWitnessTxnNummber();
							if (partyIdUpload.equalsIgnoreCase(partyId)) {
								logger.debug("************MATCH");
								rrdto1.setSignaturePath(formDTO.getParentPathSign() + partyId + "/" + formDTO.getFileNameSign());
								rrdto1.setSignatureName(formDTO.getFileNameSign());
								rrdto1.setWittSignChk("Y");
								boolean flag1 = bd.imageCheck(rrdto1.getSignaturePath());
								if (flag1) {
									flag1 = bd.updateSignDetailsWitness(partyId, rrdto1.getSignatureName(), rrdto1.getSignaturePath());
								}
								if (flag1) {} else {
									rrdto1.setSignaturePath("");
									rrdto1.setSignatureName("");
									rrdto1.setWittSignChk("N");
									ArrayList error = new ArrayList();
									error.add("Unable to upload file. Please try again.");
									request.setAttribute("ERROR_LIST", error);
									request.setAttribute("ERROR_FLAG", "true");
								}
							}
						}
					}
					ArrayList partyList5 = eForm.getConsenterDetailsList();
					Iterator itr2 = partyList5.iterator();
					while (itr2.hasNext()) {
						RegCompCheckerDTO rrdto2 = (RegCompCheckerDTO) itr2.next();
						String partyId = rrdto2.getConsentorTxnNumber();
						if (partyIdUpload.equalsIgnoreCase(partyId)) {
							logger.debug("************MATCH");
							rrdto2.setSignatureName(formDTO.getFileNameSign());
							rrdto2.setSignaturePath(formDTO.getParentPathSign() + partyId + "/" + formDTO.getFileNameSign());
							rrdto2.setCnsntrSignChk("Y");
							boolean flag1 = bd.imageCheck(rrdto2.getSignaturePath());
							if (flag1) {
								flag1 = bd.updateSignDetailsConsenter(partyId, rrdto2.getSignatureName(), rrdto2.getSignaturePath());
							}
							if (flag1) {} else {
								rrdto2.setSignatureName("");
								rrdto2.setSignaturePath("");
								rrdto2.setCnsntrSignChk("N");
								ArrayList error = new ArrayList();
								error.add("Unable to upload file. Please try again.");
								request.setAttribute("ERROR_LIST", error);
								request.setAttribute("ERROR_FLAG", "true");
							}
						}
					}
					actionName = "";
					logger.debug("PARTIES_BIOMETRIC_DETAILS ......1");
					forwardJsp = RegCompCheckerConstant.PARTIES_BIOMETRIC_DETAILS;
				}
				if ("BIOMETRIC_DETAILS_PHOTO".equalsIgnoreCase(actionName)) {
					logger.debug("in web cam action");
					if (formDTO.getPartyIdUpload() != "" && formDTO.getPartyIdUpload() != null) {
						// logger.debug("partyId----"+formDTO.getPartyIdUpload());
						ArrayList partyList = eForm.getPartiesDetailList();
						Iterator itr = partyList.iterator();
						while (itr.hasNext()) {
							RegCompCheckerDTO rrdto = (RegCompCheckerDTO) itr.next();
							String partyId = rrdto.getPartyTxnId();
							if (formDTO.getPartyIdUpload().equalsIgnoreCase(partyId)) {
								logger.debug("************MATCH");
								rrdto.setPhotoName("Photograph.JPG");
								rrdto.setPhotoPath(formDTO.getPhotoPath());
								rrdto.setPartyPicChk("Y");
								boolean flag = bd.imageCheck(rrdto.getPhotoPath());
								if (flag) {
									flag = bd.updatePhotoDetails(partyId, rrdto.getPhotoName(), rrdto.getPhotoPath());
								}
								if (flag) {} else {
									rrdto.setPhotoName("");
									rrdto.setPhotoPath("");
									rrdto.setPartyPicChk("N");
									ArrayList error = new ArrayList();
									error.add("Unable to upload file. Please try again.");
									request.setAttribute("ERROR_LIST", error);
									request.setAttribute("ERROR_FLAG", "true");
								}
							}
						}
						formDTO.setPartyIdUpload("");
					}
					if (formDTO.getWitIdUpload() != null && formDTO.getWitIdUpload() != "") {
						logger.debug("wit Id" + formDTO.getWitIdUpload());
						ArrayList partyList2 = eForm.getWitnessDetailsList();
						Iterator itr2 = partyList2.iterator();
						while (itr2.hasNext()) {
							RegCompCheckerDTO rrdto1 = (RegCompCheckerDTO) itr2.next();
							String partyId = rrdto1.getWitnessTxnNummber();
							if (formDTO.getWitIdUpload().equalsIgnoreCase(partyId)) {
								rrdto1.setPhotoName("Photograph.JPG");
								rrdto1.setPhotoPath(formDTO.getPhotoPath());
								rrdto1.setWittPicChk("Y");
								boolean flag = bd.imageCheck(rrdto1.getPhotoPath());
								if (flag) {
									flag = bd.updatePhotoDetailsWitness(partyId, rrdto1.getPhotoName(), rrdto1.getPhotoPath());
								}
								if (flag) {} else {
									rrdto1.setPhotoName("");
									rrdto1.setPhotoPath("");
									rrdto1.setWittPicChk("N");
									ArrayList error = new ArrayList();
									error.add("Unable to upload file. Please try again.");
									request.setAttribute("ERROR_LIST", error);
									request.setAttribute("ERROR_FLAG", "true");
								}
							}
						}
						formDTO.setWitIdUpload("");
					}
					if (formDTO.getConIdUpload() != null && formDTO.getConIdUpload() != "") {
						logger.debug("Con Id" + formDTO.getConIdUpload());
						ArrayList partyList3 = eForm.getConsenterDetailsList();
						Iterator itr2 = partyList3.iterator();
						while (itr2.hasNext()) {
							RegCompCheckerDTO rrdto2 = (RegCompCheckerDTO) itr2.next();
							String conid = rrdto2.getConsentorTxnNumber();
							if (formDTO.getConIdUpload().equalsIgnoreCase(conid)) {
								rrdto2.setPhotoName("Photograph.JPG");
								rrdto2.setPhotoPath(formDTO.getPhotoPath());
								rrdto2.setCnsntrPicChk("Y");
								boolean flag = bd.imageCheck(rrdto2.getPhotoPath());
								if (flag) {
									flag = bd.updatePhotoDetailsConsenter(conid, rrdto2.getPhotoName(), rrdto2.getPhotoPath());
								}
								if (flag) {} else {
									rrdto2.setPhotoName("");
									rrdto2.setPhotoPath("");
									rrdto2.setCnsntrPicChk("N");
									ArrayList error = new ArrayList();
									error.add("Unable to upload file. Please try again.");
									request.setAttribute("ERROR_LIST", error);
									request.setAttribute("ERROR_FLAG", "true");
								}
							}
						}
						formDTO.setConIdUpload("");
					}
					logger.debug("PARTIES_BIOMETRIC_DETAILS ......9");
					forwardJsp = RegCompCheckerConstant.PARTIES_BIOMETRIC_DETAILS;
				}
				if (RegCompCheckerConstant.BIOMETRIC_DETAILS_THUMB.equalsIgnoreCase(actionName)) {
					logger.debug("biomtric details -- finger print");
					if (formDTO.getPartyIdUpload() != "" && formDTO.getPartyIdUpload() != null) {
						// logger.debug("partyId----"+formDTO.getPartyIdUpload());
						ArrayList partyList = eForm.getPartiesDetailList();
						Iterator itr = partyList.iterator();
						while (itr.hasNext()) {
							RegCompCheckerDTO rrdto = (RegCompCheckerDTO) itr.next();
							String partyId = rrdto.getPartyTxnId();
							if (formDTO.getPartyIdUpload().equalsIgnoreCase(partyId)) {
								logger.debug("************MATCH");
								rrdto.setThumbPath(formDTO.getParentPathFP() + partyId + "/" + formDTO.getFileNameFP());
								rrdto.setThumbName("FingerPrint.GIF");
								rrdto.setPartyThumbChk("Y");
								boolean flag = bd.imageCheck(rrdto.getThumbPath());
								if (flag) {
									flag = bd.updateThumbDetails(partyId, rrdto.getThumbName(), rrdto.getThumbPath(), rrdto.getThumbRemarks());
								}
								if (flag) {} else {
									rrdto.setThumbPath("");
									rrdto.setThumbName("");
									rrdto.setPartyThumbChk("N");
									ArrayList error = new ArrayList();
									error.add("Unable to upload file. Please try again.");
									request.setAttribute("ERROR_LIST", error);
									request.setAttribute("ERROR_FLAG", "true");
								}
							}
						}
						formDTO.setPartyIdUpload("");
					}
					if (formDTO.getWitIdUpload() != null && formDTO.getWitIdUpload() != "") {
						logger.debug("wit Id" + formDTO.getWitIdUpload());
						ArrayList partyList2 = eForm.getWitnessDetailsList();
						Iterator itr2 = partyList2.iterator();
						while (itr2.hasNext()) {
							RegCompCheckerDTO rrdto1 = (RegCompCheckerDTO) itr2.next();
							String partyId = rrdto1.getWitnessTxnNummber();
							if (formDTO.getWitIdUpload().equalsIgnoreCase(partyId)) {
								logger.debug("************MATCH");
								rrdto1.setThumbPath(formDTO.getParentPathFP() + partyId + "/" + formDTO.getFileNameFP());
								rrdto1.setThumbName("FingerPrint.GIF");
								rrdto1.setWittThumbChk("Y");
								boolean flag = bd.imageCheck(rrdto1.getThumbPath());
								if (flag) {
									flag = bd.updateThumbDetailsWitness(partyId, rrdto1.getThumbName(), rrdto1.getThumbPath(), rrdto1.getThumbRemarksWit());
								}
								if (flag) {} else {
									rrdto1.setThumbPath("");
									rrdto1.setThumbName("");
									rrdto1.setWittThumbChk("N");
									ArrayList error = new ArrayList();
									error.add("Unable to upload file. Please try again.");
									request.setAttribute("ERROR_LIST", error);
									request.setAttribute("ERROR_FLAG", "true");
								}
							}
						}
						formDTO.setWitIdUpload("");
					}
					if (formDTO.getConIdUpload() != null && formDTO.getConIdUpload() != "") {
						logger.debug("wit Id" + formDTO.getWitIdUpload());
						ArrayList partyList3 = eForm.getConsenterDetailsList();
						Iterator itr2 = partyList3.iterator();
						while (itr2.hasNext()) {
							RegCompCheckerDTO rrdto2 = (RegCompCheckerDTO) itr2.next();
							String partyId = rrdto2.getConsentorTxnNumber();
							if (formDTO.getConIdUpload().equalsIgnoreCase(partyId)) {
								logger.debug("************MATCH");
								rrdto2.setThumbPath(formDTO.getParentPathFP() + partyId + "/" + formDTO.getFileNameFP());
								rrdto2.setThumbName("FingerPrint.GIF");
								rrdto2.setCnsntrThumbChk("Y");
								boolean flag = bd.imageCheck(rrdto2.getThumbPath());
								if (flag) {
									flag = bd.updateThumbDetailsConsenter(partyId, rrdto2.getThumbName(), rrdto2.getThumbPath(), rrdto2.getThumbRemarksCns());
								}
								if (flag) {
									// Added by ankit for Aadhar Validation
									// check - start
									if ("7".equalsIgnoreCase(rrdto2.getSelectedPhotoId())) {
										try {
											String base64 = formDTO.getBase64String();
											boolean isEkyc = callEKYCWebService(eForm, formDTO, bd, language, actionName, base64, userId);
											AadharDetailDTO aadharDts = new RegCompCheckerBD().getAadharDetailsByConstTxnNum(partyId);
											if (aadharDts.getACKID_CHECKER() != null && aadharDts != null && !aadharDts.getACKID_CHECKER().isEmpty()) {
												rrdto2.setIsAadharValidated("true");
												rrdto2.setAadharRespCode("");
												rrdto2.setAadharErrMsg("");
											} else {
												rrdto2.setIsAadharValidated("false");
												rrdto2.setAadharRespCode(formDTO.getAadharRespCode());
												rrdto2.setAadharErrMsg(formDTO.getAadharErrMsg());
											}
										} catch (Exception e) {
											logger.info(" Exception in callEKYCWebService or fetching AadharDetailsByConstTxnNum");
											rrdto2.setIsAadharValidated("false");
											rrdto2.setAadharRespCode(formDTO.getAadharRespCode());
											rrdto2.setAadharErrMsg(formDTO.getAadharErrMsg());
										}
									}
									// Added by ankit for Aadhar Validation
									// check - end
								} else {
									rrdto2.setThumbPath("");
									rrdto2.setThumbName("");
									rrdto2.setCnsntrThumbChk("N");
									ArrayList error = new ArrayList();
									error.add("Unable to upload file. Please try again.");
									request.setAttribute("ERROR_LIST", error);
									request.setAttribute("ERROR_FLAG", "true");
								}
							}
						}
						formDTO.setConIdUpload("");
					}
					logger.debug("PARTIES_BIOMETRIC_DETAILS ......10");
					forwardJsp = RegCompCheckerConstant.PARTIES_BIOMETRIC_DETAILS;
				}
				if ("PENALTY_RECEIPT_UPLOAD".equalsIgnoreCase(actionName)) {
					logger.debug("^^^^^^^^^^^^^^^^");
					ArrayList partyPresentList = eForm.getPartiesPresentList();
					String selectedPartyArr[] = formDTO.getSelectedPartyIds().split("_");
					Iterator itr = partyPresentList.iterator();
					// ArrayList partyDetails = new ArrayList();
					while (itr.hasNext()) {
						logger.debug("inside while");
						RegCompCheckerDTO rDTO = (RegCompCheckerDTO) itr.next();
						String party = rDTO.getPartyTxnId();
						// logger.debug("party"+party);
						logger.debug("selectedpart arr" + selectedPartyArr.length);
						for (int i = 0; i < selectedPartyArr.length; i++) {
							String tmpArr[] = selectedPartyArr[i].split(",");
							logger.debug("size of temparr" + tmpArr.length);
							if (party.equals(tmpArr[0])) {
								logger.debug("match");
								rDTO.setStatus("Y");
								rDTO.setIsGovtOfficial(tmpArr[1]);
								rDTO.setGovtOfficial(tmpArr[2]);
								rDTO.setReprsnName(tmpArr[3]);
								rDTO.setThumbRemarks(tmpArr[4]);
								break;
							}
							/*
							 * else { rDTO.setStatus("N");
							 * rDTO.setGovtOfficial(" ");
							 * rDTO.setReprsnName(" "); }
							 */
						}
					}
					eForm.setPartiesPresentList(partyPresentList);
					if ("holdDocMplr".equalsIgnoreCase(fwdName)) {
						formDTO.setHoldfileName(formDTO.getHoldDocumentName());
						formDTO.setHoldfilePath(formDTO.getHoldDocumentPath());
					} else {
						RegCompCheckerDTO rrdto = new RegCompCheckerDTO();
						int indx = Integer.parseInt(formDTO.getUploadIndex());
						String partyId = formDTO.getPartyIdUpload();
						if (formDTO.getUploadDoc().equalsIgnoreCase("govtLetter")) {
							formDTO.setIsUpload("Y");
							formDTO.setGovtOffLttrName(indx, RegCompCheckerConstant.FILE_NAME_GOVT_LETTER);
							formDTO.setGovtOffLttrPath(indx, FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_GOVT_LETTER + partyId + "/" + RegCompCheckerConstant.FILE_NAME_GOVT_LETTER);
							// logger.debug("^^^^^^^^^^^^^^"+formDTO.getGovtOffLttrName(indx));
							rrdto.setGovtOffLetterName(RegCompCheckerConstant.FILE_NAME_GOVT_LETTER);
							// rrdto.setGovtOffLetterContents(uploadedFile.getFileData());
							// rrdto.setGovtOffLetterSize(photoSize);
							rrdto.setGovtOffLetterPath(FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_GOVT_LETTER + partyId + "/" + RegCompCheckerConstant.FILE_NAME_GOVT_LETTER);
							eForm.setRegDTO(formDTO);
							govtLetterUploadMap = eForm.getUploadGovtLetter();
							String key = partyId;
							govtLetterUploadMap.put(key, rrdto);
							// logger.debug("Size of hashmap upload"+govtLetterUploadMap.size());
							eForm.setUploadGovtLetter(govtLetterUploadMap);
						}
					}
					forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
				}
				if ("printToPdf".equalsIgnoreCase(actionName)) {
					RegCommonForm regcommonForm = new RegCommonForm();
					commonBo.setRegCommonFormForPrint(regcommonForm, formDTO.getRegInitNumber(), language);
					commonBo.printToPdfJasper(regcommonForm, request, response, language, "Y");
				}
				if ("SUPP_DOC_UPLOAD".equalsIgnoreCase(actionName)) {
					logger.debug("^^^^^^^^^^^^^^^^");
					if (formDTO.getScannedDoc().equalsIgnoreCase("DeedDoc")) {
						formDTO.setDeedDocPath(formDTO.getParentPathScanDeed() + formDTO.getFileNameScanDeed());
						formDTO.setDeedDocName(formDTO.getFileNameScanDeed());
					} else {
						formDTO.setSupportingDocPath(formDTO.getParentPathScan() + formDTO.getFileNameScan());
						formDTO.setSupportingDocName(formDTO.getFileNameScan());
						formDTO.setSuppDocUploadCheck("Y");
					}
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					if (deedInstArray != null && deedInstArray.length > 0) {
						request.setAttribute("deedId", deedInstArray[0].trim());
						request.setAttribute("instId", deedInstArray[1].trim());
					}
					forwardJsp = RegCompCheckerConstant.REG_INIT_PROP_DETAILS;
				}
				if ("SUPP_DOC_UPLOAD_NPV".equalsIgnoreCase(actionName)) {
					logger.debug("^^^^^^^^^^^^^^^^");
					formDTO.setSupportingDocPath(formDTO.getParentPathScan() + formDTO.getFileNameScan());
					formDTO.setSupportingDocName(formDTO.getFileNameScan());
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					formDTO.setSuppDocUploadCheck("Y");
					if (deedInstArray != null && deedInstArray.length > 0) {
						request.setAttribute("deedId", deedInstArray[0].trim());
						request.setAttribute("instId", deedInstArray[1].trim());
					}
					request.setAttribute("reginit", eForm);
					forwardJsp = RegCompCheckerConstant.REG_INIT_PROP_DETLS_NPV;
				}
				if ("deathcert".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						/*
						 * try { FormFile uploadedFile =
						 * formDTO.getDeathCertificate(); if
						 * ("".equals(uploadedFile.getFileName())) {
						 * errorList.add
						 * ("Invalid file selection. Please try again."); }
						 * 
						 * String fileExt =
						 * getFileExtension(uploadedFile.getFileName());
						 * AuditInspectionRule rule = new AuditInspectionRule();
						 * rule.validateFileType(fileExt); int size =
						 * uploadedFile.getFileSize(); double fileSizeInBytes =
						 * size; double fileSizeInKB = fileSizeInBytes / 1024.0;
						 * double fileSizeInMB = fileSizeInKB / 1024.0;
						 * DecimalFormat decim = new DecimalFormat("#.##");
						 * Double fileSize = Double.parseDouble(decim
						 * .format(fileSizeInMB)); String photoSize = "(" +
						 * fileSize + "MB)"; if (rule.isError()) {
						 * errorList.add(
						 * "Invalid file type. Please select another file.");
						 * request.setAttribute("errorsList",errorList); } else
						 * { if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
						 * errorList.add(
						 * "File size is Greater than 10 MB. Please select another file."
						 * ); request.setAttribute("errorsList", errorList); }
						 * else {
						 * 
						 * formDTO.setUpldDeathCert(uploadedFile.getFileName());
						 * formDTO
						 * .setDeatCertContents(uploadedFile.getFileData());
						 * formDTO.setDeathCertSize(photoSize);
						 * eForm.setRegDTO(formDTO); } } } catch (Exception e) {
						 * errorList
						 * .add("Unable to upload file. Please try again.");
						 * request.setAttribute("errorsList", errorList); }
						 */
					}
					logger.debug("^^^^^^" + formDTO.getUploadDoc());
					if (formDTO.getUploadDoc().equalsIgnoreCase("deathCert")) {
						formDTO.setUpldDeathCert(RegCompCheckerConstant.FILE_NAME_DEATH_CERT);
						formDTO.setUpldDeathCertPath(formDTO.getFilePath() + formDTO.getPartyTxnId() + "/" + formDTO.getUpldDeathCert());
					} else if (formDTO.getUploadDoc().equalsIgnoreCase("RelationProof")) {
						formDTO.setUplaReltnProof(RegCompCheckerConstant.FILE_NAME_RLTN_PROOF);
						formDTO.setUplaReltnProofPath(formDTO.getFilePathRltn() + formDTO.getPartyTxnId() + "/" + formDTO.getUplaReltnProof());
					} else if (formDTO.getUploadDoc().equalsIgnoreCase("supportedDoc")) {
						formDTO.setHoldfileName(formDTO.getHoldDocumentName());
						formDTO.setHoldfilePath(formDTO.getHoldDocumentPath());
					} else {
						formDTO.setUploadPoaDoc(RegCompCheckerConstant.FILE_NAME_POA_DOC);
						formDTO.setUploadPoaDocPath(formDTO.getFilePathPOA() + formDTO.getPoaAuthNo() + "/" + formDTO.getUploadPoaDoc());
					}
					forwardJsp = RegCompCheckerConstant.VIEW_CHECKLIST;
				}
				if ("reltnshpproof".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						try {
							FormFile uploadedFile = formDTO.getReltnProof();
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
									formDTO.setUplaReltnProof(uploadedFile.getFileName());
									formDTO.setReltnProofContents(uploadedFile.getFileData());
									formDTO.setReltnProofSize(photoSize);
									eForm.setRegDTO(formDTO);
								}
							}
						} catch (Exception e) {
							errorList.add("Unable to upload file. Please try again.");
							request.setAttribute("errorsList", errorList);
						}
					}
					forwardJsp = RegCompCheckerConstant.VIEW_CHECKLIST;
				}
				if ("poaDoc".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						try {
							FormFile uploadedFile = formDTO.getPoaDocument();
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
									formDTO.setUploadPoaDoc(uploadedFile.getFileName());
									formDTO.setPoaUploadContents(uploadedFile.getFileData());
									formDTO.setPoaUploadSize(photoSize);
									eForm.setRegDTO(formDTO);
								}
							}
						} catch (Exception e) {
							errorList.add("Unable to upload file. Please try again.");
							request.setAttribute("errorsList", errorList);
						}
					}
					forwardJsp = RegCompCheckerConstant.VIEW_CHECKLIST;
				}
				if ("paymentReceipt".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						try {
							FormFile uploadedFile = formDTO.getPaymentReceipt();
							if ("".equals(uploadedFile.getFileName())) {
								errorList.add("Invalid file selection. Please try again.");
							}
							String fileExt = getFileExtension(uploadedFile.getFileName());
							AuditInspectionRule rule = new AuditInspectionRule();
							rule.validateFileType(fileExt);
							int size = uploadedFile.getFileSize();
							double fileSizeInBytes = size;
							// Convert the bytes to Kilobytes (1 KB = 1024
							// Bytes)
							double fileSizeInKB = fileSizeInBytes / 1024.0;
							// Convert the KB to MegaBytes (1 MB = 1024
							// KBytes)
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
									// commented by Simran
									// uploadFile(uploadedFile, uploadedFile
									// .getFileName(), dto
									// .getRegNumber());
									formDTO.setSuppDocUploadCheck("Y");
									formDTO.setPaymentReceiptName(uploadedFile.getFileName());
									formDTO.setPaymentReceiptContents(uploadedFile.getFileData());
									formDTO.setPaymentReceiptSize(photoSize);
									eForm.setRegDTO(formDTO);
								}
							}
						} catch (Exception e) {
							errorList.add("Unable to upload file. Please try again.");
							request.setAttribute("errorsList", errorList);
						}
					}
					forwardJsp = RegCompCheckerConstant.PROCEED_TO_PAYMENT;
				}
				if ("penaltyReceipt".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						ArrayList partyPresentList = eForm.getPartiesPresentList();
						String selectedPartyArr[] = formDTO.getSelectedPartyIds().split("_");
						Iterator itr = partyPresentList.iterator();
						// ArrayList partyDetails = new ArrayList();
						while (itr.hasNext()) {
							logger.debug("inside while");
							RegCompCheckerDTO rDTO = (RegCompCheckerDTO) itr.next();
							String party = rDTO.getPartyTxnId();
							// logger.debug("party"+party);
							logger.debug("selectedpart arr" + selectedPartyArr.length);
							for (int i = 0; i < selectedPartyArr.length; i++) {
								String tmpArr[] = selectedPartyArr[i].split(",");
								logger.debug("size of temparr" + tmpArr.length);
								if (party.equals(tmpArr[0])) {
									logger.debug("match");
									rDTO.setStatus("Y");
									rDTO.setIsGovtOfficial(tmpArr[1]);
									rDTO.setGovtOfficial(tmpArr[2]);
									rDTO.setReprsnName(tmpArr[3]);
									rDTO.setThumbRemarks(tmpArr[4]);
									break;
								} else {
									rDTO.setStatus("N");
									rDTO.setGovtOfficial(" ");
									rDTO.setReprsnName(" ");
								}
							}
						}
						eForm.setPartiesPresentList(partyPresentList);
						try {
							FormFile uploadedFile = formDTO.getPnaltyReceipt();
							// logger.debug("######"+uploadedFile.getFileName());
							if ("".equals(uploadedFile.getFileName())) {
								errorList.add("Invalid file selection. Please try again.");
							}
							// formDTO.setDocumentName(null);
							// formDTO.setDocContents(null);
							String fileExt = getFileExtension(uploadedFile.getFileName());
							AuditInspectionRule rule = new AuditInspectionRule();
							rule.validateFileType(fileExt);
							int size = uploadedFile.getFileSize();
							double fileSizeInBytes = size;
							// Convert the bytes to Kilobytes (1 KB = 1024
							// Bytes)
							double fileSizeInKB = fileSizeInBytes / 1024.0;
							// Convert the KB to MegaBytes (1 MB = 1024
							// KBytes)
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
									// commented by Simran
									// uploadFile(uploadedFile, uploadedFile
									// .getFileName(), dto
									// .getRegNumber());
									// formDTO.setSuppDocUploadCheck("Y");
									formDTO.setIsUpload("Y");
									formDTO.setPnaltyReceiptName(uploadedFile.getFileName());
									formDTO.setPnaltyReceiptContents(uploadedFile.getFileData());
									formDTO.setPnaltyReceiptSize(photoSize);
									eForm.setRegDTO(formDTO);
								}
							}
						} catch (Exception e) {
							errorList.add("Unable to upload file. Please try again.");
							request.setAttribute("errorsList", errorList);
						}
					}
					forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
				}
				if ("govtOfficialLttr".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						RegCompCheckerDTO rrdto = new RegCompCheckerDTO();
						ArrayList partyPresentList = eForm.getPartiesPresentList();
						String selectedPartyArr[] = formDTO.getSelectedPartyIds().split("_");
						Iterator itr = partyPresentList.iterator();
						// ArrayList partyDetails = new ArrayList();
						while (itr.hasNext()) {
							logger.debug("inside while");
							RegCompCheckerDTO rDTO = (RegCompCheckerDTO) itr.next();
							String party = rDTO.getPartyTxnId();
							// logger.debug("party"+party);
							logger.debug("selectedpart arr" + selectedPartyArr.length);
							for (int i = 0; i < selectedPartyArr.length; i++) {
								String tmpArr[] = selectedPartyArr[i].split(",");
								logger.debug("size of temparr" + tmpArr.length);
								if (party.equals(tmpArr[0])) {
									logger.debug("match");
									rDTO.setStatus("Y");
									rDTO.setIsGovtOfficial(tmpArr[1]);
									rDTO.setGovtOfficial(tmpArr[2]);
									rDTO.setReprsnName(tmpArr[3]);
									rDTO.setThumbRemarks(tmpArr[4]);
									break;
								} else {
									rDTO.setStatus("N");
									rDTO.setGovtOfficial(" ");
									rDTO.setReprsnName(" ");
								}
							}
						}
						eForm.setPartiesPresentList(partyPresentList);
						/*
						 * java.util.Hashtable
						 * files=eForm.getMultipartRequestHandler
						 * ().getFileElements(); java.util.Enumeration enum1 =
						 * files.keys(); while(enum1.hasMoreElements()) { String
						 * fileName = (String)enum1.nextElement();
						 * logger.debug("%%%%%%%%%%%%%%%%%%%%"+fileName);
						 * FormFile file =(FormFile) files.get(fileName);
						 * logger.debug("%%%%%%%%%%%%%%%%"+file.getFileName());
						 * }
						 */
						/*
						 * ActionForm form = (ActionForm)
						 * servletExternalContext.
						 * getRequest().getAttribute("actionForm"); FormFile
						 * file = (FormFile)
						 * form.getMultipartRequestHandler().getFileElements
						 * ().get
						 */
						try {
							Integer indx = Integer.parseInt(formDTO.getIndx());
							FormFile uploadFile[] = formDTO.getGovtOffLetter1();
							FormFile uploadedFile = uploadFile[indx];
							// logger.debug("######"+uploadedFile.getFileName());
							if ("".equals(uploadedFile.getFileName())) {
								errorList.add("Invalid file selection. Please try again.");
							}
							// formDTO.setDocumentName(null);
							// formDTO.setDocContents(null);
							String fileExt = getFileExtension(uploadedFile.getFileName());
							AuditInspectionRule rule = new AuditInspectionRule();
							rule.validateFileType(fileExt);
							int size = uploadedFile.getFileSize();
							double fileSizeInBytes = size;
							// Convert the bytes to Kilobytes (1 KB = 1024
							// Bytes)
							double fileSizeInKB = fileSizeInBytes / 1024.0;
							// Convert the KB to MegaBytes (1 MB = 1024
							// KBytes)
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
									// commented by Simran
									// uploadFile(uploadedFile, uploadedFile
									// .getFileName(), dto
									// .getRegNumber());
									// formDTO.setSuppDocUploadCheck("Y");
									formDTO.setIsUpload("Y");
									formDTO.setGovtOffLttrName(indx, uploadedFile.getFileName());
									// logger.debug("^^^^^^^^^^^^^^"+formDTO.getGovtOffLttrName(indx));
									rrdto.setGovtOffLetterName(uploadedFile.getFileName());
									rrdto.setGovtOffLetterContents(uploadedFile.getFileData());
									rrdto.setGovtOffLetterSize(photoSize);
									rrdto.setGovtOffLetterPath("");
									eForm.setRegDTO(formDTO);
								}
							}
						} catch (Exception e) {
							errorList.add("Unable to upload file. Please try again.");
							request.setAttribute("errorsList", errorList);
						}
						govtLetterUploadMap = eForm.getUploadGovtLetter();
						String key = formDTO.getPartyId();
						govtLetterUploadMap.put(key, rrdto);
						// logger.debug("Size of hashmap upload"+govtLetterUploadMap.size());
						eForm.setUploadGovtLetter(govtLetterUploadMap);
						forwardJsp = RegCompCheckerConstant.PRESENT_PARTIES_VIEW;
					}
				}
				// *************************************COMPLIANCE LIST
				// UPLOADS*****************************************************///
				// *******************1. WITNESS
				// PHOTOGRAPH*******************************//
				if ("witPhotograph".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						String key = formDTO.getSelectedWitnessTxnNummber();
						try {
							Integer indx = Integer.parseInt(formDTO.getIndx());
							// FormFile uploadFile[] =
							// formDTO.getWitnessPhotograph();
							// FormFile uploadedFile = uploadFile[indx];
							// logger.debug("######"+uploadedFile.getFileName());
							// if ("".equals(uploadedFile.getFileName())) {
							// errorList.add("Invalid file selection. Please try again.");
							// }
							// / String fileExt =
							// getFileExtension(uploadedFile.getFileName());
							// AuditInspectionRule rule = new
							// AuditInspectionRule();
							// rule.validateFileType(fileExt);
							// int size = uploadedFile.getFileSize();
							// double fileSizeInBytes = size;
							// double fileSizeInKB = fileSizeInBytes / 1024.0;
							// double fileSizeInMB = fileSizeInKB / 1024.0;
							// DecimalFormat decim = new DecimalFormat("#.##");
							// Double fileSize =
							// Double.parseDouble(decim.format(fileSizeInMB));
							// String photoSize = "(" + fileSize + "MB)";
							/*
							 * if (rule.isError()) {errorList.add(
							 * "Invalid file type. Please select another file."
							 * ); request.setAttribute("errorsList",errorList);
							 * } else
							 */{
								/*
								 * if (size >
								 * AuditInspectionConstant.MAX_FILE_SIZE) {
								 * errorList.add(
								 * "File size is Greater than 10 MB. Please select another file."
								 * );
								 * request.setAttribute("errorsList",errorList);
								 * } else
								 */{
									/*
									 * if(uploadedFile.getFileData() != null) {
									 * filePath =
									 * uploadFile(formDTO.getRegInitNumber
									 * (),uploadedFile
									 * .getFileData(),key,RegCompCheckerConstant
									 * .UPLOAD_PATH_PARTY_RELATED_COMPLAINCE,
									 * RegCompCheckerConstant
									 * .FILE_NAME_WIT_PHOTO,"3"); }
									 */
									// logger.debug("**************************"+filePath);
									formDTO.setWitnessDocName1(formDTO.getScanName(), indx);
									eForm.setRegDTO(formDTO);
									witnessPhotoUploadMap = eForm.getUploadWitnessPhotograph();
									Set mapSet = (Set) witnessPhotoUploadMap.entrySet();
									Iterator mapIterator = mapSet.iterator();
									while (mapIterator.hasNext()) {
										Map.Entry mapEntry = (Map.Entry) mapIterator.next();
										ArrayList valueList = (ArrayList) mapEntry.getValue();
										RegCompCheckerDTO rrdto = (RegCompCheckerDTO) valueList.get(0);
										String witnessId = (String) mapEntry.getKey();
										if (witnessId.equals(key)) {
											logger.debug("MATCH IN WITNESS");
											rrdto.setWitnessDocName(formDTO.getScanName());
											// rrdto.setWitnessDocContents(uploadedFile.getFileData());
											// rrdto.setWitnessDocSize(photoSize);
											// logger.debug("**************************"+filePath);
											rrdto.setWitnessDocPath(formDTO.getScanPath());
										}
									}
								}
							}
						} catch (Exception e) {
							errorList.add("Unable to upload file. Please try again.");
							request.setAttribute("errorsList", errorList);
						}
						// logger.debug("Size of hashmap upload"+witnessPhotoUploadMap.size());
						eForm.setUploadWitnessPhotograph(witnessPhotoUploadMap);
						String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
						if (deedInstArray != null && deedInstArray.length > 0) {
							request.setAttribute("deedId", deedInstArray[0].trim());
							request.setAttribute("instId", deedInstArray[1].trim());
						}
					}
					formDTO.setActionName("");
					formDTO.setUploadAction("");
					forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
				}
				// ///******************************end
				// 1*********************************************//
				// /******************2. Prop Photo from Angle
				// 1****************************************//
				if ("angle1Upload".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						RegCompCheckerDTO rrdto = new RegCompCheckerDTO();
						try {
							// Integer indx =
							// Integer.parseInt(formDTO.getIndx());
							String propId = formDTO.getPropId();
							boolean boo = bd.updatePropertyUpload(propId, formDTO.getRegInitNumber(), RegCompCheckerConstant.ANGLE_1_COLUMN, formDTO.getScanPath());
							if (boo) {
								propertyRelatedComplianceMap = bd.getPropertyRelatedComplianeList(formDTO.getRegInitNumber());
								eForm.setCompliancePropertyRelatedMap(propertyRelatedComplianceMap);
							} else {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							// FormFile uploadFile[] = formDTO.getPropAngle1();
							// FormFile uploadedFile = uploadFile[indx];
							// //
							// logger.debug("######"+uploadedFile.getFileName());
							// if ("".equals(uploadedFile.getFileName())) {
							// errorList.add("Invalid file selection. Please try again.");
							// }
							// String fileExt =
							// getFileExtension(uploadedFile.getFileName());
							// AuditInspectionRule rule = new
							// AuditInspectionRule();
							// rule.validateFileType(fileExt);
							// int size = uploadedFile.getFileSize();
							// double fileSizeInBytes = size;
							// double fileSizeInKB = fileSizeInBytes / 1024.0;
							// double fileSizeInMB = fileSizeInKB / 1024.0;
							// DecimalFormat decim = new DecimalFormat("#.##");
							// Double fileSize =
							// Double.parseDouble(decim.format(fileSizeInMB));
							// String photoSize = "(" + fileSize + "MB)";
							// if (rule.isError()) {
							// errorList.add("Invalid file type. Please select another file.");
							// request.setAttribute("errorsList",errorList);
							// } else
							{/*
																																																	 * // if (size >
																																																	 * AuditInspectionConstant.MAX_FILE_SIZE) { //
																																																	 * errorList.add(
																																																	 * "File size is Greater than 10 MB. Please select another file."
																																																	 * ); //
																																																	 * request.setAttribute("errorsList",errorList); //
																																																	 * } else { // if(uploadedFile.getFileData() !=
																																																	 * null) // { // filePath =
																																																	 * uploadFile(formDTO.getRegInitNumber
																																																	 * (),uploadedFile
																																																	 * .getFileData(),propId,RegCompCheckerConstant
																																																	 * .UPLOAD_PATH_PROP_RELATED_COMPLAINCE,
																																																	 * RegCompCheckerConstant.FILE_NAME_ANGLE_1,"3"); //
																																																	 * } //
																																																	 * logger.debug("**************************"+filePath
																																																	 * ); if(formDTO.getScanPath() != null) {
																																																	 * propertyRelatedComplianceMap =
																																																	 * eForm.getCompliancePropertyRelatedMap(); Set
																																																	 * mapSet =
																																																	 * (Set)propertyRelatedComplianceMap.entrySet();
																																																	 * Iterator mapIterator = mapSet.iterator();
																																																	 * while(mapIterator.hasNext()) { Map.Entry mapEntry
																																																	 * = (Map.Entry)mapIterator.next(); String
																																																	 * propertyId = (String)mapEntry.getKey();
																																																	 * if(propertyId.equals(propId)) { ArrayList
																																																	 * valueList = (ArrayList)mapEntry.getValue();
																																																	 * RegCompCheckerDTO gdto =
																																																	 * (RegCompCheckerDTO)valueList.get(0);
																																																	 * gdto.setPropImage1FilePath
																																																	 * (formDTO.getScanPath());
																																																	 * gdto.setPropImage1DocumentName
																																																	 * (formDTO.getScanName()); gdto.setIsUpload("Y"); }
																																																	 * } }eForm.setCompliancePropertyRelatedMap(
																																																	 * propertyRelatedComplianceMap);
																																																	 * 
																																																	 * }
																																																	 */
							}
						} catch (Exception e) {
							errorList.add("Unable to upload file. Please try again.");
							request.setAttribute("errorsList", errorList);
						}
					}
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					if (deedInstArray != null && deedInstArray.length > 0) {
						request.setAttribute("deedId", deedInstArray[0].trim());
						request.setAttribute("instId", deedInstArray[1].trim());
					}
					formDTO.setActionName("");
					formDTO.setUploadAction("");
					forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
				}
				// ///******************************end
				// 2*********************************************//
				// ///************************3. Prop Photo from angle
				// 2******************************************//
				if ("angle2Upload".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						RegCompCheckerDTO rrdto = new RegCompCheckerDTO();
						try {
							// Integer indx =
							// Integer.parseInt(formDTO.getIndx());
							String propId = formDTO.getPropId();
							boolean boo = bd.updatePropertyUpload(propId, formDTO.getRegInitNumber(), RegCompCheckerConstant.ANGLE_2_COLUMN, formDTO.getScanPath());
							if (boo) {
								propertyRelatedComplianceMap = bd.getPropertyRelatedComplianeList(formDTO.getRegInitNumber());
								eForm.setCompliancePropertyRelatedMap(propertyRelatedComplianceMap);
							} else {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							// FormFile uploadFile[] = formDTO.getPropAngle2();
							// FormFile uploadedFile = uploadFile[indx];
							// logger.debug("######"+uploadedFile.getFileName());
							// if ("".equals(uploadedFile.getFileName())) {
							// errorList.add("Invalid file selection. Please try again.");
							// }
							// String fileExt =
							// getFileExtension(uploadedFile.getFileName());
							// AuditInspectionRule rule = new
							// AuditInspectionRule();
							// rule.validateFileType(fileExt);
							// int size = uploadedFile.getFileSize();
							// double fileSizeInBytes = size;
							// double fileSizeInKB = fileSizeInBytes / 1024.0;
							// double fileSizeInMB = fileSizeInKB / 1024.0;
							// DecimalFormat decim = new DecimalFormat("#.##");
							// Double fileSize =
							// Double.parseDouble(decim.format(fileSizeInMB));
							// String photoSize = "(" + fileSize + "MB)";
							// if (rule.isError()) {
							// errorList.add("Invalid file type. Please select another file.");
							// request.setAttribute("errorsList",errorList);
							// } else
							{/*
																																																	 * // if (size >
																																																	 * AuditInspectionConstant.MAX_FILE_SIZE) { //
																																																	 * errorList.add(
																																																	 * "File size is Greater than 10 MB. Please select another file."
																																																	 * ); //
																																																	 * request.setAttribute("errorsList",errorList); //
																																																	 * } else { // if(uploadedFile.getFileData() !=
																																																	 * null) //{ // filePath =
																																																	 * uploadFile(formDTO.getRegInitNumber
																																																	 * (),uploadedFile
																																																	 * .getFileData(),propId,RegCompCheckerConstant
																																																	 * .UPLOAD_PATH_PROP_RELATED_COMPLAINCE,
																																																	 * RegCompCheckerConstant.FILE_NAME_ANGLE_2,"3"); //
																																																	 * } // //
																																																	 * logger.debug("**************************"+
																																																	 * filePath); if(formDTO.getScanPath() != null) {
																																																	 * propertyRelatedComplianceMap =
																																																	 * eForm.getCompliancePropertyRelatedMap(); Set
																																																	 * mapSet =
																																																	 * (Set)propertyRelatedComplianceMap.entrySet();
																																																	 * Iterator mapIterator = mapSet.iterator();
																																																	 * while(mapIterator.hasNext()) { Map.Entry mapEntry
																																																	 * = (Map.Entry)mapIterator.next(); String
																																																	 * propertyId = (String)mapEntry.getKey();
																																																	 * if(propertyId.equals(propId)) { ArrayList
																																																	 * valueList = (ArrayList)mapEntry.getValue();
																																																	 * RegCompCheckerDTO gdto =
																																																	 * (RegCompCheckerDTO)valueList.get(0);
																																																	 * gdto.setPropImage2FilePath
																																																	 * (formDTO.getScanPath());
																																																	 * gdto.setPropImage2DocumentName
																																																	 * (formDTO.getScanName()); gdto.setIsUpload("Y"); }
																																																	 * } }eForm.setCompliancePropertyRelatedMap(
																																																	 * propertyRelatedComplianceMap);
																																																	 * 
																																																	 * }
																																																	 */
							}
						} catch (Exception e) {
							errorList.add("Unable to upload file. Please try again.");
							request.setAttribute("errorsList", errorList);
						}
					}
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					if (deedInstArray != null && deedInstArray.length > 0) {
						request.setAttribute("deedId", deedInstArray[0].trim());
						request.setAttribute("instId", deedInstArray[1].trim());
					}
					formDTO.setActionName("");
					formDTO.setUploadAction("");
					forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
				}
				// ///******************************end
				// 1*********************************************//
				// ///*************************Prop photo from angle
				// 3*********************************************//
				if ("angle3Upload".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						RegCompCheckerDTO rrdto = new RegCompCheckerDTO();
						try {
							// Integer indx =
							// Integer.parseInt(formDTO.getIndx());
							String propId = formDTO.getPropId();
							boolean boo = bd.updatePropertyUpload(propId, formDTO.getRegInitNumber(), RegCompCheckerConstant.ANGLE_3_COLUMN, formDTO.getScanPath());
							if (boo) {
								propertyRelatedComplianceMap = bd.getPropertyRelatedComplianeList(formDTO.getRegInitNumber());
								eForm.setCompliancePropertyRelatedMap(propertyRelatedComplianceMap);
							} else {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							// FormFile uploadFile[] = formDTO.getPropAngle3();
							// FormFile uploadedFile = uploadFile[indx];
							// logger.debug("######"+uploadedFile.getFileName());
							// if ("".equals(uploadedFile.getFileName())) {
							// errorList.add("Invalid file selection. Please try again.");
							// }
							// String fileExt =
							// getFileExtension(uploadedFile.getFileName());
							// AuditInspectionRule rule = new
							// AuditInspectionRule();
							// rule.validateFileType(fileExt);
							// int size = uploadedFile.getFileSize();
							// double fileSizeInBytes = size;
							// double fileSizeInKB = fileSizeInBytes / 1024.0;
							// double fileSizeInMB = fileSizeInKB / 1024.0;
							// DecimalFormat decim = new DecimalFormat("#.##");
							// Double fileSize =
							// Double.parseDouble(decim.format(fileSizeInMB));
							// String photoSize = "(" + fileSize + "MB)";
							// if (rule.isError()) {
							// errorList.add("Invalid file type. Please select another file.");
							// request.setAttribute("errorsList",errorList);
							// } else
							{/*
																																																	 * // if (size >
																																																	 * AuditInspectionConstant.MAX_FILE_SIZE) { //
																																																	 * errorList.add(
																																																	 * "File size is Greater than 10 MB. Please select another file."
																																																	 * ); //
																																																	 * request.setAttribute("errorsList",errorList); //
																																																	 * } else { // if(uploadedFile.getFileData() !=
																																																	 * null) // { // filePath =
																																																	 * uploadFile(formDTO.getRegInitNumber
																																																	 * (),uploadedFile
																																																	 * .getFileData(),propId,RegCompCheckerConstant
																																																	 * .UPLOAD_PATH_PROP_RELATED_COMPLAINCE,
																																																	 * RegCompCheckerConstant.FILE_NAME_ANGLE_3,"3"); //
																																																	 * } //
																																																	 * logger.debug("**************************"+filePath
																																																	 * ); if(formDTO.getScanPath() != null) {
																																																	 * propertyRelatedComplianceMap =
																																																	 * eForm.getCompliancePropertyRelatedMap(); Set
																																																	 * mapSet =
																																																	 * (Set)propertyRelatedComplianceMap.entrySet();
																																																	 * Iterator mapIterator = mapSet.iterator();
																																																	 * while(mapIterator.hasNext()) { Map.Entry mapEntry
																																																	 * = (Map.Entry)mapIterator.next(); String
																																																	 * propertyId = (String)mapEntry.getKey();
																																																	 * if(propertyId.equals(propId)) { ArrayList
																																																	 * valueList = (ArrayList)mapEntry.getValue();
																																																	 * RegCompCheckerDTO gdto =
																																																	 * (RegCompCheckerDTO)valueList.get(0);
																																																	 * gdto.setPropImage3FilePath
																																																	 * (formDTO.getScanPath());
																																																	 * gdto.setPropImage3DocumentName
																																																	 * (formDTO.getScanName()); gdto.setIsUpload("Y"); }
																																																	 * } }eForm.setCompliancePropertyRelatedMap(
																																																	 * propertyRelatedComplianceMap);
																																																	 * 
																																																	 * }
																																																	 */
							}
						} catch (Exception e) {
							errorList.add("Unable to upload file. Please try again.");
							request.setAttribute("errorsList", errorList);
						}
					}
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					if (deedInstArray != null && deedInstArray.length > 0) {
						request.setAttribute("deedId", deedInstArray[0].trim());
						request.setAttribute("instId", deedInstArray[1].trim());
					}
					formDTO.setActionName("");
					formDTO.setUploadAction("");
					forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
				}
				// ///******************************end
				// 1*********************************************//
				// ///**********************4 prop
				// Map****************************************//
				if ("propMapUpload".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						RegCompCheckerDTO rrdto = new RegCompCheckerDTO();
						try {
							// Integer indx =
							// Integer.parseInt(formDTO.getIndx());
							String propId = formDTO.getPropId();
							boolean boo = bd.updatePropertyUpload(propId, formDTO.getRegInitNumber(), RegCompCheckerConstant.MAP_COLUMN, formDTO.getScanPath());
							if (boo) {
								propertyRelatedComplianceMap = bd.getPropertyRelatedComplianeList(formDTO.getRegInitNumber());
								eForm.setCompliancePropertyRelatedMap(propertyRelatedComplianceMap);
							} else {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							// FormFile uploadFile[] = formDTO.getPropMap();
							// FormFile uploadedFile = uploadFile[indx];
							// logger.debug("######"+uploadedFile.getFileName());
							// if ("".equals(uploadedFile.getFileName())) {
							// errorList.add("Invalid file selection. Please try again.");
							// }
							// String fileExt =
							// getFileExtension(uploadedFile.getFileName());
							// AuditInspectionRule rule = new
							// AuditInspectionRule();
							// rule.validateFileType(fileExt);
							// int size = uploadedFile.getFileSize();
							// double fileSizeInBytes = size;
							// double fileSizeInKB = fileSizeInBytes / 1024.0;
							// double fileSizeInMB = fileSizeInKB / 1024.0;
							// DecimalFormat decim = new DecimalFormat("#.##");
							// Double fileSize =
							// Double.parseDouble(decim.format(fileSizeInMB));
							// String photoSize = "(" + fileSize + "MB)";
							// if (rule.isError()) {
							// errorList.add("Invalid file type. Please select another file.");
							// request.setAttribute("errorsList",errorList);
							// } else
							{/*
																																																	 * // if (size >
																																																	 * AuditInspectionConstant.MAX_FILE_SIZE) { //
																																																	 * errorList.add(
																																																	 * "File size is Greater than 10 MB. Please select another file."
																																																	 * ); //
																																																	 * request.setAttribute("errorsList",errorList); //
																																																	 * } else { // if(uploadedFile.getFileData() !=
																																																	 * null) // { // filePath =
																																																	 * uploadFile(formDTO.getRegInitNumber
																																																	 * (),uploadedFile
																																																	 * .getFileData(),propId,RegCompCheckerConstant
																																																	 * .UPLOAD_PATH_PROP_RELATED_COMPLAINCE,
																																																	 * RegCompCheckerConstant.FILE_NAME_PROP_MAP,"3");
																																																	 * // } //
																																																	 * logger.debug("**************************"+
																																																	 * filePath); if(formDTO.getScanPath() != null) {
																																																	 * propertyRelatedComplianceMap =
																																																	 * eForm.getCompliancePropertyRelatedMap(); Set
																																																	 * mapSet =
																																																	 * (Set)propertyRelatedComplianceMap.entrySet();
																																																	 * Iterator mapIterator = mapSet.iterator();
																																																	 * while(mapIterator.hasNext()) { Map.Entry mapEntry
																																																	 * = (Map.Entry)mapIterator.next(); String
																																																	 * propertyId = (String)mapEntry.getKey();
																																																	 * if(propertyId.equals(propId)) { ArrayList
																																																	 * valueList = (ArrayList)mapEntry.getValue();
																																																	 * RegCompCheckerDTO gdto =
																																																	 * (RegCompCheckerDTO)valueList.get(0);
																																																	 * gdto.setPropMapFilePath(formDTO.getScanPath());
																																																	 * gdto
																																																	 * .setPropMapDocumentName(formDTO.getScanName());
																																																	 * gdto.setIsUpload("Y"); } } }
																																																	 * eForm.setCompliancePropertyRelatedMap
																																																	 * (propertyRelatedComplianceMap);
																																																	 * 
																																																	 * }
																																																	 */
							}
						} catch (Exception e) {
							errorList.add("Unable to upload file. Please try again.");
							request.setAttribute("errorsList", errorList);
						}
					}
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					if (deedInstArray != null && deedInstArray.length > 0) {
						request.setAttribute("deedId", deedInstArray[0].trim());
						request.setAttribute("instId", deedInstArray[1].trim());
					}
					formDTO.setActionName("");
					formDTO.setUploadAction("");
					forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
				}
				// **************************end
				// 4********************************************************//
				// ///**********************5 prop
				// RIN****************************************//
				if ("propRinUpload".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						RegCompCheckerDTO rrdto = new RegCompCheckerDTO();
						try {
							// Integer indx =
							// Integer.parseInt(formDTO.getIndx());
							String propId = formDTO.getPropId();
							boolean boo = bd.updatePropertyUpload(propId, formDTO.getRegInitNumber(), RegCompCheckerConstant.RIN_COLUMN, formDTO.getScanPath());
							if (boo) {
								propertyRelatedComplianceMap = bd.getPropertyRelatedComplianeList(formDTO.getRegInitNumber());
								eForm.setCompliancePropertyRelatedMap(propertyRelatedComplianceMap);
							} else {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							// FormFile uploadFile[] = formDTO.getPropRin();
							// FormFile uploadedFile = uploadFile[indx];
							// logger.debug("######"+uploadedFile.getFileName());
							// if ("".equals(uploadedFile.getFileName())) {
							// errorList.add("Invalid file selection. Please try again.");
							// }
							//		
							// String fileExt =
							// getFileExtension(uploadedFile.getFileName());
							// AuditInspectionRule rule = new
							// AuditInspectionRule();
							// rule.validateFileType(fileExt);
							// int size = uploadedFile.getFileSize();
							// double fileSizeInBytes = size;
							// double fileSizeInKB = fileSizeInBytes / 1024.0;
							// double fileSizeInMB = fileSizeInKB / 1024.0;
							// DecimalFormat decim = new DecimalFormat("#.##");
							// Double fileSize =
							// Double.parseDouble(decim.format(fileSizeInMB));
							// String photoSize = "(" + fileSize + "MB)";
							// if (rule.isError()) {
							// errorList.add("Invalid file type. Please select another file.");
							// request.setAttribute("errorsList",errorList);
							// } else
							{/*
																																																	 * // if (size >
																																																	 * AuditInspectionConstant.MAX_FILE_SIZE) { //
																																																	 * errorList.add(
																																																	 * "File size is Greater than 10 MB. Please select another file."
																																																	 * ); //
																																																	 * request.setAttribute("errorsList",errorList); //
																																																	 * } else { // if(uploadedFile.getFileData() !=
																																																	 * null) // { // filePath =
																																																	 * uploadFile(formDTO.getRegInitNumber
																																																	 * (),uploadedFile
																																																	 * .getFileData(),propId,RegCompCheckerConstant
																																																	 * .UPLOAD_PATH_PROP_RELATED_COMPLAINCE,
																																																	 * RegCompCheckerConstant.FILE_NAME_RIN,"3"); // }
																																																	 * //
																																																	 * logger.debug("**************************"+filePath
																																																	 * ); if(formDTO.getScanPath() != null) {
																																																	 * propertyRelatedComplianceMap =
																																																	 * eForm.getCompliancePropertyRelatedMap(); Set
																																																	 * mapSet =
																																																	 * (Set)propertyRelatedComplianceMap.entrySet();
																																																	 * Iterator mapIterator = mapSet.iterator();
																																																	 * while(mapIterator.hasNext()) { Map.Entry mapEntry
																																																	 * = (Map.Entry)mapIterator.next(); String
																																																	 * propertyId = (String)mapEntry.getKey();
																																																	 * if(propertyId.equals(propId)) { ArrayList
																																																	 * valueList = (ArrayList)mapEntry.getValue();
																																																	 * RegCompCheckerDTO gdto =
																																																	 * (RegCompCheckerDTO)valueList.get(0);
																																																	 * gdto.setPropRinFilePath(formDTO.getScanPath());
																																																	 * gdto
																																																	 * .setPropRinDocumentName(formDTO.getScanName());
																																																	 * gdto.setIsUpload("Y"); } } }
																																																	 * eForm.setCompliancePropertyRelatedMap
																																																	 * (propertyRelatedComplianceMap);
																																																	 * 
																																																	 * }
																																																	 */
							}
						} catch (Exception e) {
							errorList.add("Unable to upload file. Please try again.");
							request.setAttribute("errorsList", errorList);
						}
					}
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					if (deedInstArray != null && deedInstArray.length > 0) {
						request.setAttribute("deedId", deedInstArray[0].trim());
						request.setAttribute("instId", deedInstArray[1].trim());
					}
					formDTO.setActionName("");
					formDTO.setUploadAction("");
					forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
				}
				// **************************end
				// 5********************************************************//
				// ///**********************6 prop
				// Khasra****************************************//
				if ("propKhasraUpload".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						RegCompCheckerDTO rrdto = new RegCompCheckerDTO();
						try {
							// Integer indx =
							// Integer.parseInt(formDTO.getIndx());
							String propId = formDTO.getPropId();
							boolean boo = bd.updatePropertyUpload(propId, formDTO.getRegInitNumber(), RegCompCheckerConstant.KHASRA_COLUMN, formDTO.getScanPath());
							if (boo) {
								propertyRelatedComplianceMap = bd.getPropertyRelatedComplianeList(formDTO.getRegInitNumber());
								eForm.setCompliancePropertyRelatedMap(propertyRelatedComplianceMap);
							} else {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							// FormFile uploadFile[] = formDTO.getPropKhasra();
							// FormFile uploadedFile = uploadFile[indx];
							// //
							// logger.debug("######"+uploadedFile.getFileName());
							// if ("".equals(uploadedFile.getFileName())) {
							// errorList.add("Invalid file selection. Please try again.");
							// }
							// String fileExt =
							// getFileExtension(uploadedFile.getFileName());
							// AuditInspectionRule rule = new
							// AuditInspectionRule();
							// rule.validateFileType(fileExt);
							// int size = uploadedFile.getFileSize();
							// double fileSizeInBytes = size;
							// double fileSizeInKB = fileSizeInBytes / 1024.0;
							// double fileSizeInMB = fileSizeInKB / 1024.0;
							// DecimalFormat decim = new DecimalFormat("#.##");
							// Double fileSize =
							// Double.parseDouble(decim.format(fileSizeInMB));
							// String photoSize = "(" + fileSize + "MB)";
							// if (rule.isError()) {
							// errorList.add("Invalid file type. Please select another file.");
							// request.setAttribute("errorsList",errorList);
							// } else
							{/*
																																																	 * // if (size >
																																																	 * AuditInspectionConstant.MAX_FILE_SIZE) { //
																																																	 * errorList.add(
																																																	 * "File size is Greater than 10 MB. Please select another file."
																																																	 * ); //
																																																	 * request.setAttribute("errorsList",errorList); //
																																																	 * } else { // if(uploadedFile.getFileData() !=
																																																	 * null) // { // filePath =
																																																	 * uploadFile(formDTO.getRegInitNumber
																																																	 * (),uploadedFile
																																																	 * .getFileData(),propId,RegCompCheckerConstant
																																																	 * .UPLOAD_PATH_PROP_RELATED_COMPLAINCE,
																																																	 * RegCompCheckerConstant.FILE_NAME_KHASRA,"3"); //
																																																	 * } //
																																																	 * logger.debug("**************************"+filePath
																																																	 * ); if(formDTO.getScanPath() != null) {
																																																	 * propertyRelatedComplianceMap =
																																																	 * eForm.getCompliancePropertyRelatedMap(); Set
																																																	 * mapSet =
																																																	 * (Set)propertyRelatedComplianceMap.entrySet();
																																																	 * Iterator mapIterator = mapSet.iterator();
																																																	 * while(mapIterator.hasNext()) { Map.Entry mapEntry
																																																	 * = (Map.Entry)mapIterator.next(); String
																																																	 * propertyId = (String)mapEntry.getKey();
																																																	 * if(propertyId.equals(propId)) { ArrayList
																																																	 * valueList = (ArrayList)mapEntry.getValue();
																																																	 * RegCompCheckerDTO gdto =
																																																	 * (RegCompCheckerDTO)valueList.get(0);
																																																	 * gdto.setPropKhasraFilePath
																																																	 * (formDTO.getScanPath());
																																																	 * gdto.setPropKhasraDocumentName
																																																	 * (formDTO.getScanName()); gdto.setIsUpload("Y"); }
																																																	 * } }eForm.setCompliancePropertyRelatedMap(
																																																	 * propertyRelatedComplianceMap);
																																																	 * 
																																																	 * }
																																																	 */
							}
						} catch (Exception e) {
							errorList.add("Unable to upload file. Please try again.");
							request.setAttribute("errorsList", errorList);
						}
					}
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					if (deedInstArray != null && deedInstArray.length > 0) {
						request.setAttribute("deedId", deedInstArray[0].trim());
						request.setAttribute("instId", deedInstArray[1].trim());
					}
					formDTO.setActionName("");
					formDTO.setUploadAction("");
					forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
				}
				// **************************end
				// 6********************************************************//
				// *******************************PARTY RELATED
				// UPLOADS******************************//
				// //////////////////1. Collector
				// cert************************************************//
				if ("partyCollectorCert".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						RegCompCheckerDTO rrdto = new RegCompCheckerDTO();
						try {
							// Integer indx =
							// Integer.parseInt(formDTO.getIndx());
							String partyId = formDTO.getPartyId();
							boolean boo = bd.updatePartyUpload(partyId, formDTO.getRegInitNumber(), RegCompCheckerConstant.COLLECTER_CERTIFICATE_COLUMN, formDTO.getScanPath());
							if (boo) {
								partyRelatedComplianceMap = bd.getPartyRelatedComplianeList(formDTO.getRegInitNumber(), language);
								eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
							} else {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							// FormFile uploadFile[] =
							// formDTO.getPartyCollectorCertDoc();
							// FormFile uploadedFile = uploadFile[indx];
							// logger.debug("######"+uploadedFile.getFileName());
							// if ("".equals(uploadedFile.getFileName())) {
							// errorList.add("Invalid file selection. Please try again.");
							// }
							// String fileExt =
							// getFileExtension(uploadedFile.getFileName());
							// AuditInspectionRule rule = new
							// AuditInspectionRule();
							// rule.validateFileType(fileExt);
							// int size = uploadedFile.getFileSize();
							// double fileSizeInBytes = size;
							// double fileSizeInKB = fileSizeInBytes / 1024.0;
							// double fileSizeInMB = fileSizeInKB / 1024.0;
							// DecimalFormat decim = new DecimalFormat("#.##");
							// Double fileSize =
							// Double.parseDouble(decim.format(fileSizeInMB));
							// String photoSize = "(" + fileSize + "MB)";
							// if (rule.isError()) {
							// errorList.add("Invalid file type. Please select another file.");
							// request.setAttribute("errorsList",errorList);
							// } else
							{/*
																																																	 * // if (size >
																																																	 * AuditInspectionConstant.MAX_FILE_SIZE) { //
																																																	 * errorList.add(
																																																	 * "File size is Greater than 10 MB. Please select another file."
																																																	 * ); //
																																																	 * request.setAttribute("errorsList",errorList); //
																																																	 * } else { // if(uploadedFile.getFileData() !=
																																																	 * null) // { // filePath =
																																																	 * uploadFile(formDTO.getRegInitNumber
																																																	 * (),uploadedFile
																																																	 * .getFileData(),partyId,RegCompCheckerConstant
																																																	 * .UPLOAD_PATH_PARTY_RELATED_COMPLAINCE,
																																																	 * RegCompCheckerConstant
																																																	 * .FILE_NAME_COLLECTOR_CERT,"3"); // } //
																																																	 * logger.debug
																																																	 * ("**************************"+filePath);
																																																	 * if(formDTO.getScanPath() != null) {
																																																	 * partyRelatedComplianceMap =
																																																	 * eForm.getCompliancePartyRelatedMap(); Set mapSet
																																																	 * = (Set)partyRelatedComplianceMap.entrySet();
																																																	 * Iterator mapIterator = mapSet.iterator();
																																																	 * while(mapIterator.hasNext()) { Map.Entry mapEntry
																																																	 * = (Map.Entry)mapIterator.next(); String partyID =
																																																	 * (String)mapEntry.getKey();
																																																	 * if(partyId.equals(partyID)) { ArrayList valueList
																																																	 * = (ArrayList)mapEntry.getValue();
																																																	 * RegCompCheckerDTO gdto =
																																																	 * (RegCompCheckerDTO)valueList.get(0);
																																																	 * gdto.setCollectorCertDocPath
																																																	 * (formDTO.getScanPath());
																																																	 * gdto.setCollectorCertDocName
																																																	 * (formDTO.getScanName()); gdto.setIsUpload("Y"); }
																																																	 * } }eForm.setCompliancePartyRelatedMap(
																																																	 * partyRelatedComplianceMap);
																																																	 * 
																																																	 * }
																																																	 */
							}
						} catch (Exception e) {
							errorList.add("Unable to upload file. Please try again.");
							request.setAttribute("errorsList", errorList);
						}
					}
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					if (deedInstArray != null && deedInstArray.length > 0) {
						request.setAttribute("deedId", deedInstArray[0].trim());
						request.setAttribute("instId", deedInstArray[1].trim());
					}
					formDTO.setActionName("");
					formDTO.setUploadAction("");
					forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
				}
				// ***********************************end party
				// 1***********************************//
				// //********************************2. photo
				// proof***********************************//
				if ("partyPhotoProof".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						RegCompCheckerDTO rrdto = new RegCompCheckerDTO();
						try {
							// Integer indx =
							// Integer.parseInt(formDTO.getIndx());
							String partyId = formDTO.getPartyId();
							// String partyId = formDTO.getPartyId();
							boolean boo = bd.updatePartyUpload(partyId, formDTO.getRegInitNumber(), RegCompCheckerConstant.PHOTO_PROOF_COLUMN, formDTO.getScanPath());
							if (boo) {
								partyRelatedComplianceMap = bd.getPartyRelatedComplianeList(formDTO.getRegInitNumber(), language);
								eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
							} else {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							// FormFile uploadFile[] =
							// formDTO.getPartyPhotoProofDoc();
							// FormFile uploadedFile = uploadFile[indx];
							// logger.debug("######"+uploadedFile.getFileName());
							// if ("".equals(uploadedFile.getFileName())) {
							// errorList.add("Invalid file selection. Please try again.");
							// }
							// String fileExt =
							// getFileExtension(uploadedFile.getFileName());
							// AuditInspectionRule rule = new
							// AuditInspectionRule();
							// rule.validateFileType(fileExt);
							// int size = uploadedFile.getFileSize();
							// double fileSizeInBytes = size;
							// double fileSizeInKB = fileSizeInBytes / 1024.0;
							// double fileSizeInMB = fileSizeInKB / 1024.0;
							// DecimalFormat decim = new DecimalFormat("#.##");
							// Double fileSize =
							// Double.parseDouble(decim.format(fileSizeInMB));
							// String photoSize = "(" + fileSize + "MB)";
							// if (rule.isError()) {
							// errorList.add("Invalid file type. Please select another file.");
							// request.setAttribute("errorsList",errorList);
							// } else
							{/*
																																																	 * // if (size >
																																																	 * AuditInspectionConstant.MAX_FILE_SIZE) { //
																																																	 * errorList.add(
																																																	 * "File size is Greater than 10 MB. Please select another file."
																																																	 * ); //
																																																	 * request.setAttribute("errorsList",errorList); //
																																																	 * } else { // if(uploadedFile.getFileData() !=
																																																	 * null) // { // filePath =
																																																	 * uploadFile(formDTO.getRegInitNumber
																																																	 * (),uploadedFile
																																																	 * .getFileData(),partyId,RegCompCheckerConstant
																																																	 * .UPLOAD_PATH_PARTY_RELATED_COMPLAINCE,
																																																	 * RegCompCheckerConstant
																																																	 * .FILE_NAME_PHOTO_PROOF,"3"); // } //
																																																	 * logger.debug(
																																																	 * "**************************"+filePath);
																																																	 * if(formDTO.getScanPath() != null) {
																																																	 * partyRelatedComplianceMap =
																																																	 * eForm.getCompliancePartyRelatedMap(); Set mapSet
																																																	 * = (Set)partyRelatedComplianceMap.entrySet();
																																																	 * Iterator mapIterator = mapSet.iterator();
																																																	 * while(mapIterator.hasNext()) { Map.Entry mapEntry
																																																	 * = (Map.Entry)mapIterator.next(); String partyID =
																																																	 * (String)mapEntry.getKey();
																																																	 * if(partyId.equals(partyID)) { ArrayList valueList
																																																	 * = (ArrayList)mapEntry.getValue();
																																																	 * RegCompCheckerDTO gdto =
																																																	 * (RegCompCheckerDTO)valueList.get(0);
																																																	 * gdto.setPhotoProofDocPath(formDTO.getScanPath());
																																																	 * gdto.setPhotoProofDocName(formDTO.getScanName());
																																																	 * gdto.setIsUpload("Y"); } } }
																																																	 * eForm.setCompliancePartyRelatedMap
																																																	 * (partyRelatedComplianceMap);
																																																	 * 
																																																	 * }
																																																	 */
							}
						} catch (Exception e) {
							errorList.add("Unable to upload file. Please try again.");
							request.setAttribute("errorsList", errorList);
						}
					}
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					if (deedInstArray != null && deedInstArray.length > 0) {
						request.setAttribute("deedId", deedInstArray[0].trim());
						request.setAttribute("instId", deedInstArray[1].trim());
					}
					formDTO.setActionName("");
					formDTO.setUploadAction("");
					forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
				}
				// *****************************END
				// 2******************************************************************//
				// *******************3. Notarized Affidavit by the
				// Executant****************************//
				if ("partyNotAffdExec".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						RegCompCheckerDTO rrdto = new RegCompCheckerDTO();
						try {
							// Integer indx =
							// Integer.parseInt(formDTO.getIndx());
							String partyId = formDTO.getPartyId();
							boolean boo = bd.updatePartyUpload(partyId, formDTO.getRegInitNumber(), RegCompCheckerConstant.NOT_AFFD_EXEC_COLUMN, formDTO.getScanPath());
							if (boo) {
								partyRelatedComplianceMap = bd.getPartyRelatedComplianeList(formDTO.getRegInitNumber(), language);
								eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
							} else {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							// FormFile uploadFile[] =
							// formDTO.getPartyNotAffdExecDoc();
							// FormFile uploadedFile = uploadFile[indx];
							// logger.debug("######"+uploadedFile.getFileName());
							// if ("".equals(uploadedFile.getFileName())) {
							// errorList.add("Invalid file selection. Please try again.");
							// }
							// String fileExt =
							// getFileExtension(uploadedFile.getFileName());
							// AuditInspectionRule rule = new
							// AuditInspectionRule();
							// rule.validateFileType(fileExt);
							// int size = uploadedFile.getFileSize();
							// double fileSizeInBytes = size;
							// double fileSizeInKB = fileSizeInBytes / 1024.0;
							// double fileSizeInMB = fileSizeInKB / 1024.0;
							// DecimalFormat decim = new DecimalFormat("#.##");
							// Double fileSize =
							// Double.parseDouble(decim.format(fileSizeInMB));
							// String photoSize = "(" + fileSize + "MB)";
							// if (rule.isError()) {
							// errorList.add("Invalid file type. Please select another file.");
							// request.setAttribute("errorsList",errorList);
							// } else
							{/*
																																																	 * // if (size >
																																																	 * AuditInspectionConstant.MAX_FILE_SIZE) { //
																																																	 * errorList.add(
																																																	 * "File size is Greater than 10 MB. Please select another file."
																																																	 * ); //
																																																	 * request.setAttribute("errorsList",errorList); //
																																																	 * } else { // if(uploadedFile.getFileData() !=
																																																	 * null) // { // filePath =
																																																	 * uploadFile(formDTO.getRegInitNumber
																																																	 * (),uploadedFile
																																																	 * .getFileData(),partyId,RegCompCheckerConstant
																																																	 * .UPLOAD_PATH_PARTY_RELATED_COMPLAINCE,
																																																	 * RegCompCheckerConstant
																																																	 * .FILE_NAME_NOTARIZED_AFFIDAVIT_EXECUTANT,"3"); //
																																																	 * } // //
																																																	 * logger.debug("**************************"+
																																																	 * filePath); if(formDTO.getScanPath() != null) {
																																																	 * partyRelatedComplianceMap =
																																																	 * eForm.getCompliancePartyRelatedMap(); Set mapSet
																																																	 * = (Set)partyRelatedComplianceMap.entrySet();
																																																	 * Iterator mapIterator = mapSet.iterator();
																																																	 * while(mapIterator.hasNext()) { Map.Entry mapEntry
																																																	 * = (Map.Entry)mapIterator.next(); String partyID =
																																																	 * (String)mapEntry.getKey();
																																																	 * if(partyId.equals(partyID)) { ArrayList valueList
																																																	 * = (ArrayList)mapEntry.getValue();
																																																	 * RegCompCheckerDTO gdto =
																																																	 * (RegCompCheckerDTO)valueList.get(0);
																																																	 * gdto.setNotAffdExecDocPath
																																																	 * (formDTO.getScanPath());
																																																	 * gdto.setNotAffdExecDocName
																																																	 * (formDTO.getScanName()); gdto.setIsUpload("Y"); }
																																																	 * } }eForm.setCompliancePartyRelatedMap(
																																																	 * partyRelatedComplianceMap);
																																																	 * 
																																																	 * }
																																																	 */
							}
						} catch (Exception e) {
							errorList.add("Unable to upload file. Please try again.");
							request.setAttribute("errorsList", errorList);
						}
					}
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					if (deedInstArray != null && deedInstArray.length > 0) {
						request.setAttribute("deedId", deedInstArray[0].trim());
						request.setAttribute("instId", deedInstArray[1].trim());
					}
					formDTO.setActionName("");
					formDTO.setUploadAction("");
					forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
				}
				// *****************************END
				// 3*******************************************************//
				// ***********************4. Executant's
				// Photograph************************************************//
				if ("partyExecPhoto".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						RegCompCheckerDTO rrdto = new RegCompCheckerDTO();
						try {
							// Integer indx =
							// Integer.parseInt(formDTO.getIndx());
							String partyId = formDTO.getPartyId();
							boolean boo = bd.updatePartyUpload(partyId, formDTO.getRegInitNumber(), RegCompCheckerConstant.EXEC_PHOTO_COLUMN, formDTO.getScanPath());
							if (boo) {
								partyRelatedComplianceMap = bd.getPartyRelatedComplianeList(formDTO.getRegInitNumber(), language);
								eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
							} else {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							// FormFile uploadFile[] =
							// formDTO.getPartyExecPhoto();
							// FormFile uploadedFile = uploadFile[indx];
							// logger.debug("######"+uploadedFile.getFileName());
							// if ("".equals(uploadedFile.getFileName())) {
							// errorList.add("Invalid file selection. Please try again.");
							// }
							// String fileExt =
							// getFileExtension(uploadedFile.getFileName());
							// AuditInspectionRule rule = new
							// AuditInspectionRule();
							// rule.validateFileType(fileExt);
							// int size = uploadedFile.getFileSize();
							// double fileSizeInBytes = size;
							// double fileSizeInKB = fileSizeInBytes / 1024.0;
							// double fileSizeInMB = fileSizeInKB / 1024.0;
							// DecimalFormat decim = new DecimalFormat("#.##");
							// Double fileSize =
							// Double.parseDouble(decim.format(fileSizeInMB));
							// String photoSize = "(" + fileSize + "MB)";
							// if (rule.isError()) {
							// errorList.add("Invalid file type. Please select another file.");
							// request.setAttribute("errorsList",errorList);
							// } else
							{/*
																																																	 * // if (size >
																																																	 * AuditInspectionConstant.MAX_FILE_SIZE) { //
																																																	 * errorList.add(
																																																	 * "File size is Greater than 10 MB. Please select another file."
																																																	 * ); //
																																																	 * request.setAttribute("errorsList",errorList); //
																																																	 * } else { // if(uploadedFile.getFileData() !=
																																																	 * null) // { // filePath =
																																																	 * uploadFile(formDTO.getRegInitNumber
																																																	 * (),uploadedFile
																																																	 * .getFileData(),partyId,RegCompCheckerConstant
																																																	 * .UPLOAD_PATH_PARTY_RELATED_COMPLAINCE,
																																																	 * RegCompCheckerConstant
																																																	 * .FILE_NAME_EXECUTANT_PHOTO,"3"); // } //
																																																	 * logger.debug
																																																	 * ("**************************"+filePath);
																																																	 * if(formDTO.getScanPath() != null) {
																																																	 * partyRelatedComplianceMap =
																																																	 * eForm.getCompliancePartyRelatedMap(); Set mapSet
																																																	 * = (Set)partyRelatedComplianceMap.entrySet();
																																																	 * Iterator mapIterator = mapSet.iterator();
																																																	 * while(mapIterator.hasNext()) { Map.Entry mapEntry
																																																	 * = (Map.Entry)mapIterator.next(); String partyID =
																																																	 * (String)mapEntry.getKey();
																																																	 * if(partyId.equals(partyID)) { ArrayList valueList
																																																	 * = (ArrayList)mapEntry.getValue();
																																																	 * RegCompCheckerDTO gdto =
																																																	 * (RegCompCheckerDTO)valueList.get(0);
																																																	 * gdto.setExecPhotoPath(formDTO.getScanPath());
																																																	 * gdto.setExecPhotoName(formDTO.getScanName());
																																																	 * gdto.setIsUpload("Y"); } } }
																																																	 * eForm.setCompliancePartyRelatedMap
																																																	 * (partyRelatedComplianceMap);
																																																	 * 
																																																	 * }
																																																	 */
							}
						} catch (Exception e) {
							errorList.add("Unable to upload file. Please try again.");
							request.setAttribute("errorsList", errorList);
						}
					}
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					if (deedInstArray != null && deedInstArray.length > 0) {
						request.setAttribute("deedId", deedInstArray[0].trim());
						request.setAttribute("instId", deedInstArray[1].trim());
					}
					formDTO.setActionName("");
					formDTO.setUploadAction("");
					forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
				}
				// ************************************end
				// 4***************************************************//
				// **********************************5. Notarized Affidavit of
				// Attorney******************//
				if ("partyNotAffdAttr".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						RegCompCheckerDTO rrdto = new RegCompCheckerDTO();
						try {
							// Integer indx =
							// Integer.parseInt(formDTO.getIndx());
							String partyId = formDTO.getPartyId();
							boolean boo = bd.updatePartyUpload(partyId, formDTO.getRegInitNumber(), RegCompCheckerConstant.NOT_AFFD_ATTR_PATH, formDTO.getScanPath());
							if (boo) {
								partyRelatedComplianceMap = bd.getPartyRelatedComplianeList(formDTO.getRegInitNumber(), language);
								eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
							} else {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							// FormFile uploadFile[] =
							// formDTO.getPartyNotAffdAttr();
							// FormFile uploadedFile = uploadFile[indx];
							// logger.debug("######"+uploadedFile.getFileName());
							// if ("".equals(uploadedFile.getFileName())) {
							// errorList.add("Invalid file selection. Please try again.");
							// }
							// String fileExt =
							// getFileExtension(uploadedFile.getFileName());
							// AuditInspectionRule rule = new
							// AuditInspectionRule();
							// rule.validateFileType(fileExt);
							// int size = uploadedFile.getFileSize();
							// double fileSizeInBytes = size;
							// double fileSizeInKB = fileSizeInBytes / 1024.0;
							// double fileSizeInMB = fileSizeInKB / 1024.0;
							// DecimalFormat decim = new DecimalFormat("#.##");
							// Double fileSize =
							// Double.parseDouble(decim.format(fileSizeInMB));
							// String photoSize = "(" + fileSize + "MB)";
							// if (rule.isError()) {
							// errorList.add("Invalid file type. Please select another file.");
							// request.setAttribute("errorsList",errorList);
							// } else
							{/*
																																																	 * // if (size >
																																																	 * AuditInspectionConstant.MAX_FILE_SIZE) { //
																																																	 * errorList.add(
																																																	 * "File size is Greater than 10 MB. Please select another file."
																																																	 * ); //
																																																	 * request.setAttribute("errorsList",errorList); //
																																																	 * } else { // if(uploadedFile.getFileData() !=
																																																	 * null) // { // filePath =
																																																	 * uploadFile(formDTO.getRegInitNumber
																																																	 * (),uploadedFile
																																																	 * .getFileData(),partyId,RegCompCheckerConstant
																																																	 * .UPLOAD_PATH_PARTY_RELATED_COMPLAINCE,
																																																	 * RegCompCheckerConstant
																																																	 * .FILE_NAME_NOTARIZED_AFFIDAVIT_ATTORNEY,"3"); //
																																																	 * } //
																																																	 * logger.debug("**************************"+filePath
																																																	 * ); if(formDTO.getScanPath() != null) {
																																																	 * partyRelatedComplianceMap =
																																																	 * eForm.getCompliancePartyRelatedMap(); Set mapSet
																																																	 * = (Set)partyRelatedComplianceMap.entrySet();
																																																	 * Iterator mapIterator = mapSet.iterator();
																																																	 * while(mapIterator.hasNext()) { Map.Entry mapEntry
																																																	 * = (Map.Entry)mapIterator.next(); String partyID =
																																																	 * (String)mapEntry.getKey();
																																																	 * if(partyId.equals(partyID)) { ArrayList valueList
																																																	 * = (ArrayList)mapEntry.getValue();
																																																	 * RegCompCheckerDTO gdto =
																																																	 * (RegCompCheckerDTO)valueList.get(0);
																																																	 * gdto.setNotAffdAttrPath(formDTO.getScanPath());
																																																	 * gdto.setNotAffdAttrName(formDTO.getScanName());
																																																	 * gdto.setIsUpload("Y"); } } }
																																																	 * eForm.setCompliancePartyRelatedMap
																																																	 * (partyRelatedComplianceMap);
																																																	 * 
																																																	 * }
																																																	 */
							}
						} catch (Exception e) {
							errorList.add("Unable to upload file. Please try again.");
							request.setAttribute("errorsList", errorList);
						}
					}
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					if (deedInstArray != null && deedInstArray.length > 0) {
						request.setAttribute("deedId", deedInstArray[0].trim());
						request.setAttribute("instId", deedInstArray[1].trim());
					}
					formDTO.setActionName("");
					formDTO.setUploadAction("");
					forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
				}
				// ***************************************end
				// 5****************************************************//
				// *************************************6. Attorney
				// Photograph************************************//
				if ("partyAttrPhoto".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						RegCompCheckerDTO rrdto = new RegCompCheckerDTO();
						try {
							// Integer indx =
							// Integer.parseInt(formDTO.getIndx());
							String partyId = formDTO.getPartyId();
							boolean boo = bd.updatePartyUpload(partyId, formDTO.getRegInitNumber(), RegCompCheckerConstant.ATTR_PHOTO_PATH, formDTO.getScanPath());
							if (boo) {
								partyRelatedComplianceMap = bd.getPartyRelatedComplianeList(formDTO.getRegInitNumber(), language);
								eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
							} else {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							// FormFile uploadFile[] =
							// formDTO.getPartyAttrPhoto();
							// FormFile uploadedFile = uploadFile[indx];
							// logger.debug("######"+uploadedFile.getFileName());
							// if ("".equals(uploadedFile.getFileName())) {
							// errorList.add("Invalid file selection. Please try again.");
							// }
							//		
							// String fileExt =
							// getFileExtension(uploadedFile.getFileName());
							// AuditInspectionRule rule = new
							// AuditInspectionRule();
							// rule.validateFileType(fileExt);
							// / int size = uploadedFile.getFileSize();
							// double fileSizeInBytes = size;
							// double fileSizeInKB = fileSizeInBytes / 1024.0;
							// double fileSizeInMB = fileSizeInKB / 1024.0;
							// DecimalFormat decim = new DecimalFormat("#.##");
							// Double fileSize =
							// Double.parseDouble(decim.format(fileSizeInMB));
							// String photoSize = "(" + fileSize + "MB)";
							// if (rule.isError()) {
							// errorList.add("Invalid file type. Please select another file.");
							// request.setAttribute("errorsList",errorList);
							// } else
							{/*
																																																	 * // if (size >
																																																	 * AuditInspectionConstant.MAX_FILE_SIZE) { //
																																																	 * errorList.add(
																																																	 * "File size is Greater than 10 MB. Please select another file."
																																																	 * ); //
																																																	 * request.setAttribute("errorsList",errorList); //
																																																	 * } else { // if(uploadedFile.getFileData() !=
																																																	 * null) // { // filePath =
																																																	 * uploadFile(formDTO.getRegInitNumber
																																																	 * (),uploadedFile
																																																	 * .getFileData(),partyId,RegCompCheckerConstant
																																																	 * .UPLOAD_PATH_PARTY_RELATED_COMPLAINCE,
																																																	 * RegCompCheckerConstant
																																																	 * .FILE_NAME_ATTORNEY_PHOTO,"3"); // } //
																																																	 * logger.debug
																																																	 * ("**************************"+filePath);
																																																	 * if(formDTO.getScanPath() != null) {
																																																	 * partyRelatedComplianceMap =
																																																	 * eForm.getCompliancePartyRelatedMap(); Set mapSet
																																																	 * = (Set)partyRelatedComplianceMap.entrySet();
																																																	 * Iterator mapIterator = mapSet.iterator();
																																																	 * while(mapIterator.hasNext()) { Map.Entry mapEntry
																																																	 * = (Map.Entry)mapIterator.next(); String partyID =
																																																	 * (String)mapEntry.getKey();
																																																	 * if(partyId.equals(partyID)) { ArrayList valueList
																																																	 * = (ArrayList)mapEntry.getValue();
																																																	 * RegCompCheckerDTO gdto =
																																																	 * (RegCompCheckerDTO)valueList.get(0);
																																																	 * gdto.setAttrPhotoPath(formDTO.getScanPath());
																																																	 * gdto.setAttrPhotoName(formDTO.getScanName());
																																																	 * gdto.setIsUpload("Y"); } } }
																																																	 * eForm.setCompliancePartyRelatedMap
																																																	 * (partyRelatedComplianceMap);
																																																	 * 
																																																	 * }
																																																	 */
							}
						} catch (Exception e) {
							errorList.add("Unable to upload file. Please try again.");
							request.setAttribute("errorsList", errorList);
						}
					}
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					if (deedInstArray != null && deedInstArray.length > 0) {
						request.setAttribute("deedId", deedInstArray[0].trim());
						request.setAttribute("instId", deedInstArray[1].trim());
					}
					formDTO.setActionName("");
					formDTO.setUploadAction("");
					forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
				}
				// ***********************************************end
				// 6*****************************************//
				// ******************************7.Claimants's Photograph
				// *********************************************//
				if ("partyClaimntPhoto".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						RegCompCheckerDTO rrdto = new RegCompCheckerDTO();
						try {
							// Integer indx =
							// Integer.parseInt(formDTO.getIndx());
							String partyId = formDTO.getPartyId();
							boolean boo = bd.updatePartyUpload(partyId, formDTO.getRegInitNumber(), RegCompCheckerConstant.CLAIMAINT_PHOTO_COLUMN, formDTO.getScanPath());
							if (boo) {
								partyRelatedComplianceMap = bd.getPartyRelatedComplianeList(formDTO.getRegInitNumber(), language);
								eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
							} else {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							// FormFile uploadFile[] =
							// formDTO.getPartyClaimntPhoto();
							// FormFile uploadedFile = uploadFile[indx];
							// logger.debug("######"+uploadedFile.getFileName());
							// if ("".equals(uploadedFile.getFileName())) {
							// errorList.add("Invalid file selection. Please try again.");
							// }
							// String fileExt =
							// getFileExtension(uploadedFile.getFileName());
							// AuditInspectionRule rule = new
							// AuditInspectionRule();
							// rule.validateFileType(fileExt);
							// int size = uploadedFile.getFileSize();
							// double fileSizeInBytes = size;
							// double fileSizeInKB = fileSizeInBytes / 1024.0;
							// double fileSizeInMB = fileSizeInKB / 1024.0;
							// DecimalFormat decim = new DecimalFormat("#.##");
							// Double fileSize =
							// Double.parseDouble(decim.format(fileSizeInMB));
							// String photoSize = "(" + fileSize + "MB)";
							// if (rule.isError()) {
							// errorList.add("Invalid file type. Please select another file.");
							// request.setAttribute("errorsList",errorList);
							// } else
							{/*
																																																	 * // if (size >
																																																	 * AuditInspectionConstant.MAX_FILE_SIZE) { //
																																																	 * errorList.add(
																																																	 * "File size is Greater than 10 MB. Please select another file."
																																																	 * ); //
																																																	 * request.setAttribute("errorsList",errorList); //
																																																	 * } else { // if(uploadedFile.getFileData() !=
																																																	 * null) // { // filePath =
																																																	 * uploadFile(formDTO.getRegInitNumber
																																																	 * (),uploadedFile
																																																	 * .getFileData(),partyId,RegCompCheckerConstant
																																																	 * .UPLOAD_PATH_PARTY_RELATED_COMPLAINCE,
																																																	 * RegCompCheckerConstant
																																																	 * .FILE_NAME_CLAIMANT_PHOTO,"3"); // } //
																																																	 * logger.debug
																																																	 * ("**************************"+filePath);
																																																	 * if(formDTO.getScanPath() != null) {
																																																	 * partyRelatedComplianceMap =
																																																	 * eForm.getCompliancePartyRelatedMap(); Set mapSet
																																																	 * = (Set)partyRelatedComplianceMap.entrySet();
																																																	 * Iterator mapIterator = mapSet.iterator();
																																																	 * while(mapIterator.hasNext()) { Map.Entry mapEntry
																																																	 * = (Map.Entry)mapIterator.next(); String partyID =
																																																	 * (String)mapEntry.getKey();
																																																	 * if(partyId.equals(partyID)) { ArrayList valueList
																																																	 * = (ArrayList)mapEntry.getValue();
																																																	 * RegCompCheckerDTO gdto =
																																																	 * (RegCompCheckerDTO)valueList.get(0);
																																																	 * gdto.setClaimntPhotoPath(formDTO.getScanPath());
																																																	 * gdto.setClaimntPhotoName(formDTO.getScanName());
																																																	 * gdto.setIsUpload("Y"); } } }
																																																	 * eForm.setCompliancePartyRelatedMap
																																																	 * (partyRelatedComplianceMap);
																																																	 * 
																																																	 * }
																																																	 */
							}
						} catch (Exception e) {
							errorList.add("Unable to upload file. Please try again.");
							request.setAttribute("errorsList", errorList);
						}
					}
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					if (deedInstArray != null && deedInstArray.length > 0) {
						request.setAttribute("deedId", deedInstArray[0].trim());
						request.setAttribute("instId", deedInstArray[1].trim());
					}
					formDTO.setActionName("");
					formDTO.setUploadAction("");
					forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
				}
				if ("holdDoc".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						formDTO.setHoldfileName(formDTO.getHoldDocumentName());
						formDTO.setHoldfilePath(formDTO.getHoldDocumentPath());
						String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
						if (deedInstArray != null && deedInstArray.length > 0) {
							request.setAttribute("deedId", deedInstArray[0].trim());
							request.setAttribute("instId", deedInstArray[1].trim());
						}
					}
					formDTO.setActionName("");
					formDTO.setUploadAction("");
					forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
				}
				// ***********************************************************END
				// 7****************//
				// **********************************8.PAN or form
				// 60/61*************************************//
				if ("partyPanForm".equalsIgnoreCase(fwdName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						RegCompCheckerDTO rrdto = new RegCompCheckerDTO();
						try {
							// Integer indx =
							// Integer.parseInt(formDTO.getIndx());
							String partyId = formDTO.getPartyId();
							boolean boo = bd.updatePartyUpload(partyId, formDTO.getRegInitNumber(), RegCompCheckerConstant.PAN_FORM_COLUMN, formDTO.getScanPath());
							if (boo) {
								partyRelatedComplianceMap = bd.getPartyRelatedComplianeList(formDTO.getRegInitNumber(), language);
								eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
							} else {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							// FormFile uploadFile[] =
							// formDTO.getPartyPanForm60();
							// FormFile uploadedFile = uploadFile[indx];
							// logger.debug("######"+uploadedFile.getFileName());
							// if ("".equals(uploadedFile.getFileName())) {
							// errorList.add("Invalid file selection. Please try again.");
							// }
							// String fileExt =
							// getFileExtension(uploadedFile.getFileName());
							// AuditInspectionRule rule = new
							// AuditInspectionRule();
							// rule.validateFileType(fileExt);
							// int size = uploadedFile.getFileSize();
							// double fileSizeInBytes = size;
							// double fileSizeInKB = fileSizeInBytes / 1024.0;
							// double fileSizeInMB = fileSizeInKB / 1024.0;
							// DecimalFormat decim = new DecimalFormat("#.##");
							// Double fileSize =
							// Double.parseDouble(decim.format(fileSizeInMB));
							// String photoSize = "(" + fileSize + "MB)";
							// if (rule.isError()) {
							// errorList.add("Invalid file type. Please select another file.");
							// request.setAttribute("errorsList",errorList);
							// } else
							{/*
																																																	 * // if (size >
																																																	 * AuditInspectionConstant.MAX_FILE_SIZE) { //
																																																	 * errorList.add(
																																																	 * "File size is Greater than 10 MB. Please select another file."
																																																	 * ); //
																																																	 * request.setAttribute("errorsList",errorList); //
																																																	 * } else { // if(uploadedFile.getFileData() !=
																																																	 * null) // { // filePath =
																																																	 * uploadFile(formDTO.getRegInitNumber
																																																	 * (),uploadedFile
																																																	 * .getFileData(),partyId,RegCompCheckerConstant
																																																	 * .UPLOAD_PATH_PARTY_RELATED_COMPLAINCE,
																																																	 * RegCompCheckerConstant
																																																	 * .FILE_NAME_PAN_FORM_60,"3"); // } //
																																																	 * logger.debug(
																																																	 * "**************************"+filePath);
																																																	 * if(formDTO.getScanPath() != null) {
																																																	 * partyRelatedComplianceMap =
																																																	 * eForm.getCompliancePartyRelatedMap(); Set mapSet
																																																	 * = (Set)partyRelatedComplianceMap.entrySet();
																																																	 * Iterator mapIterator = mapSet.iterator();
																																																	 * while(mapIterator.hasNext()) { Map.Entry mapEntry
																																																	 * = (Map.Entry)mapIterator.next(); String partyID =
																																																	 * (String)mapEntry.getKey();
																																																	 * if(partyId.equals(partyID)) { ArrayList valueList
																																																	 * = (ArrayList)mapEntry.getValue();
																																																	 * RegCompCheckerDTO gdto =
																																																	 * (RegCompCheckerDTO)valueList.get(0);
																																																	 * gdto.setPanForm60Path(formDTO.getScanPath());
																																																	 * gdto.setPanForm60Name(formDTO.getScanName());
																																																	 * gdto.setIsUpload("Y"); } } }
																																																	 * eForm.setCompliancePartyRelatedMap
																																																	 * (partyRelatedComplianceMap);
																																																	 * 
																																																	 * }
																																																	 */
							}
						} catch (Exception e) {
							errorList.add("Unable to upload file. Please try again.");
							request.setAttribute("errorsList", errorList);
						}
					}
					String[] deedInstArray = bd.getDeedInstId(formDTO.getRegInitNumber());
					if (deedInstArray != null && deedInstArray.length > 0) {
						request.setAttribute("deedId", deedInstArray[0].trim());
						request.setAttribute("instId", deedInstArray[1].trim());
					}
					formDTO.setActionName("");
					formDTO.setUploadAction("");
					forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
				}
				// ********************************END
				// 8**************************************//
				if (RegCompCheckerConstant.NEXT_TO_BIOMETRIC_DETAILS.equalsIgnoreCase(actionName)) {
					logger.debug("BIOMETRIC DETAILS");
					boolean updateData = false;
					if (formDTO.getPaymentReceiptContents() != null) {
						filePath = uploadFile(formDTO.getRegInitNumber(), formDTO.getPaymentReceiptContents(), RegCompCheckerConstant.UPLOAD_PATH_PMT_RCPT, RegCompCheckerConstant.FILE_NAME_PMT_RCPT);
						if (filePath != null) {
							formDTO.setPaymentReceiptPath(filePath);
							updateData = bd.modifyImpoundDetails(formDTO, cdate, userId);
						}
						if (updateData) {
							logger.debug("DATA UPDATED");
							// boolean sealsInsertion =
							// bd.insertSealsData(formDTO.getRegInitNumber(),
							// formDTO.getSealId(), formDTO.getSealSubTypeId());
							// logger.debug("***seals insertion stamp duty"+sealsInsertion);
							formDTO.setSeal("");
							formDTO.setSealId("");
							formDTO.setSealSubTypeId("");
							ArrayList partyDetails = bd.getPartyDet(formDTO.getRegInitNumber(), language);
							eForm.setPartiesDetailList(partyDetails);
							ArrayList witnessList = bd.getWitnessDet(formDTO.getRegInitNumber(), language);
							eForm.setWitnessDetailsList(witnessList);
							ArrayList consenterList = bd.getConsenterDet(formDTO.getRegInitNumber(), language);
							eForm.setConsenterDetailsList(consenterList);
							// logger.debug("<-------SIZE"+eForm.getPartiesDetailList().size());
							String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
							formDTO.setParentPathSign(path);
							formDTO.setFileNameSign("signature.GIF");
							formDTO.setForwardPath("/regCompChecker.do?txnPartyIdBiometric=");
							formDTO.setForwardName("regCompChecker");
							String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
							formDTO.setFileNameFP("FingerPrint.GIF");
							formDTO.setParentPathFP(pathFP);
							logger.debug("PARTIES_BIOMETRIC_DETAILS ......11");
							forwardJsp = RegCompCheckerConstant.PARTIES_BIOMETRIC_DETAILS;
						} else {
							logger.debug("DATA NOT UPDATED");
							messages.add("ERROR_MSG", new ActionMessage("insertion.failed"));
							saveMessages(request, messages);
							eForm.setCheck("CME");
							forwardJsp = RegCompCheckerConstant.PROCEED_TO_PAYMENT;
						}
					}
					// boolean status =
					// bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(),
					// RegCompCheckerConstant.BIOMETRIC_DETAILS_STATUS);
					ArrayList partyDetails = bd.getPartyDet(formDTO.getRegInitNumber(), language);
					eForm.setPartiesDetailList(partyDetails);
					ArrayList witnessList = bd.getWitnessDet(formDTO.getRegInitNumber(), language);
					eForm.setWitnessDetailsList(witnessList);
					ArrayList consenterList = bd.getConsenterDet(formDTO.getRegInitNumber(), language);
					eForm.setConsenterDetailsList(consenterList);
					if (consenterList != null && consenterList.size() > 0)
						formDTO.setCnsntrChk("Y");
					else
						formDTO.setCnsntrChk("N");
					String path = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_SIGNTAURE;
					formDTO.setParentPathSign(path);
					formDTO.setFileNameSign("signature.GIF");
					formDTO.setForwardPath("/regCompChecker.do?txnPartyIdBiometric=");
					formDTO.setForwardName("regCompChecker");
					String pathFP = FILE_UPLOAD_PATH + formDTO.getRegInitNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_THUMB;
					formDTO.setFileNameFP("FingerPrint.GIF");
					formDTO.setParentPathFP(pathFP);
					// logger.debug("<-------SIZE"+eForm.getPartiesDetailList().size());
					logger.debug("PARTIES_BIOMETRIC_DETAILS ......12");
					forwardJsp = RegCompCheckerConstant.PARTIES_BIOMETRIC_DETAILS;
				}
			}
			// ///////////////////////////////////////////////UPLOAD
			// DOCS/////////////////////////////////////////////////////////
			if ("photo".equalsIgnoreCase(fwdName)) {
				if ("setUploadFile".equalsIgnoreCase(actionName)) {
					ArrayList propDetls = new ArrayList();
					if (eForm.getRegDTO().getPropDetails() != null) {
						String[] propDetails = formDTO.getPropDetails().split(",");
						for (int i = 0; i < propDetails.length; i++) {
							String[] prop_market = propDetails[i].split("_");
							RegCompCheckerDTO gDTO = new RegCompCheckerDTO();
							// logger.debug("PROPERTY------>"+prop_market[0]);
							// logger.debug("MARKET VALUE---->"+prop_market[1]);
							gDTO.setPropertyId(prop_market[0]);
							gDTO.setMarketValueTrns(prop_market[1]);
							propDetls.add(gDTO);
						}
						eForm.setPropList(propDetls);
					}
					try {
						FormFile uploadedFile = formDTO.getSupportingDoc();
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
								// commented by Simran
								// uploadFile(uploadedFile, uploadedFile
								// .getFileName(), dto
								// .getRegNumber());
								formDTO.setSupportingDocName(uploadedFile.getFileName());
								formDTO.setSupportingDocContents(uploadedFile.getFileData());
								formDTO.setSupportingDocSize(photoSize);
								eForm.setRegDTO(formDTO);
							}
						}
					} catch (Exception e) {
						errorList.add("Unable to upload file. Please try again.");
						request.setAttribute("errorsList", errorList);
					}
					formDTO.setSuppDocUploadCheck("Y");
					request.setAttribute("deedId", formDTO.getDeedID());
					request.setAttribute("instId", formDTO.getInstID());
					request.setAttribute(RegCompCheckerConstant.MODIFY, "modifyMarketValue");
					forwardJsp = RegCompCheckerConstant.REG_INIT_PROP_DETAILS;
				}
			}
			if ("photo1".equalsIgnoreCase(fwdName)) {
				if ("setUploadFile".equalsIgnoreCase(actionName)) {
					try {
						FormFile uploadedFile = formDTO.getSupportingDoc();
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
								// commented by Simran
								// uploadFile(uploadedFile, uploadedFile
								// .getFileName(), dto
								// .getRegNumber());
								formDTO.setSupportingDocName(uploadedFile.getFileName());
								formDTO.setSupportingDocContents(uploadedFile.getFileData());
								formDTO.setSupportingDocSize(photoSize);
								formDTO.setSuppDocUploadCheck("Y");
								eForm.setRegDTO(formDTO);
								request.setAttribute("reginit", eForm);
							}
						}
					} catch (Exception e) {
						errorList.add("Unable to upload file. Please try again.");
						request.setAttribute("errorsList", errorList);
					}
					request.setAttribute("deedId", formDTO.getDeedID());
					request.setAttribute("instId", formDTO.getInstID());
					request.setAttribute(RegCompCheckerConstant.MODIFY, "modifyMarketValue");
					request.setAttribute("reginit", eForm);
					forwardJsp = RegCompCheckerConstant.REG_INIT_PROP_DETLS_NPV;
				}
				/*
				 * if ("deathcert".equalsIgnoreCase(fwdName)) {
				 * 
				 * if ("setUploadFile".equalsIgnoreCase(actionName)) { try {
				 * FormFile uploadedFile = dto .getDeathCertificate(); if
				 * ("".equals(uploadedFile.getFileName())) { errorList
				 * .add("Invalid file selection. Please try again."); } //
				 * formDTO.setDocumentName(null); //
				 * formDTO.setDocContents(null); String fileExt =
				 * getFileExtension(uploadedFile .getFileName());
				 * AuditInspectionRule rule = new AuditInspectionRule();
				 * rule.validateFileType(fileExt); int size =
				 * uploadedFile.getFileSize(); double fileSizeInBytes = size; //
				 * Convert the bytes to Kilobytes (1 KB = 1024 // Bytes) double
				 * fileSizeInKB = fileSizeInBytes / 1024.0; // Convert the KB to
				 * MegaBytes (1 MB = 1024 // KBytes) double fileSizeInMB =
				 * fileSizeInKB / 1024.0; DecimalFormat decim = new
				 * DecimalFormat("#.##"); Double fileSize =
				 * Double.parseDouble(decim .format(fileSizeInMB)); String
				 * photoSize = "(" + fileSize + "MB)";
				 * 
				 * if (rule.isError()) { errorList
				 * .add("Invalid file type. Please select another file.");
				 * 
				 * request.setAttribute("errorsList", errorList); } else { if
				 * (size > AuditInspectionConstant.MAX_FILE_SIZE) { errorList
				 * .add
				 * ("File size is Greater than 10 MB. Please select another file."
				 * ); request.setAttribute("errorsList", errorList); } else {
				 * 
				 * uploadFile(uploadedFile, uploadedFile .getFileName(), dto
				 * .getRegNumber()); dto.setUpldDeathCert(uploadedFile
				 * .getFileName()); eForm.setRegDTO(dto); } } } catch (Exception
				 * e) { errorList
				 * .add("Unable to upload file. Please try again.");
				 * request.setAttribute("errorsList", errorList); } }
				 * 
				 * forwardJsp = "checklist";
				 * 
				 * }
				 * 
				 * if ("reltnshpproof".equalsIgnoreCase(frwdName)) {
				 * 
				 * if ("setUploadFile".equalsIgnoreCase(actionName)) { try {
				 * FormFile uploadedFile = dto.getRelationProof(); if
				 * ("".equals(uploadedFile.getFileName())) { errorList
				 * .add("Invalid file selection. Please try again."); } //
				 * formDTO.setDocumentName(null); //
				 * formDTO.setDocContents(null); String fileExt =
				 * getFileExtension(uploadedFile .getFileName());
				 * AuditInspectionRule rule = new AuditInspectionRule();
				 * rule.validateFileType(fileExt); int size =
				 * uploadedFile.getFileSize(); double fileSizeInBytes = size; //
				 * Convert the bytes to Kilobytes (1 KB = 1024 // Bytes) double
				 * fileSizeInKB = fileSizeInBytes / 1024.0; // Convert the KB to
				 * MegaBytes (1 MB = 1024 // KBytes) double fileSizeInMB =
				 * fileSizeInKB / 1024.0; DecimalFormat decim = new
				 * DecimalFormat("#.##"); Double fileSize =
				 * Double.parseDouble(decim .format(fileSizeInMB)); String
				 * photoSize = "(" + fileSize + "MB)";
				 * 
				 * if (rule.isError()) { errorList
				 * .add("Invalid file type. Please select another file.");
				 * 
				 * request.setAttribute("errorsList", errorList); } else { if
				 * (size > AuditInspectionConstant.MAX_FILE_SIZE) { errorList
				 * .add
				 * ("File size is Greater than 10 MB. Please select another file."
				 * ); request.setAttribute("errorsList", errorList); } else {
				 * uploadFile(uploadedFile, uploadedFile .getFileName(), dto
				 * .getRegNumber()); dto.setUplaReltnProof(uploadedFile
				 * .getFileName()); eForm.setRegDTO(dto); } } } catch (Exception
				 * e) { errorList
				 * .add("Unable to upload file. Please try again.");
				 * request.setAttribute("errorsList", errorList); } }
				 * 
				 * forwardJsp = "checklist";
				 * 
				 * }
				 */
				if ("removeUploadFile".equalsIgnoreCase(actionName)) {
					try {
						ArrayList templist = eForm.getRegDTO().getPhotoLst();
						int i = Integer.parseInt(index);
						FormFile fname = (FormFile) templist.get(i);
						fname.getFileName();
						removeFile(fname.getFileName());
						templist.remove(i);
						System.out.println(templist);
						eForm.getRegDTO().setPhotoLst(templist);
						request.setAttribute("deedId", formDTO.getDeedID());
						request.setAttribute("instId", formDTO.getInstID());
						request.setAttribute(RegCompCheckerConstant.MODIFY, "modifyMarketValue");
						forwardJsp = RegCompCheckerConstant.REG_INIT_PROP_DETAILS;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			// ******************FOR PARTIAL
			// SAVE*******************************************//
			if (RegCompCheckerConstant.CANCEL_ACTION.equalsIgnoreCase(actionName)) {
				String pageName = formDTO.getCancelledPage();
				String status = "";
				if (pageName.equalsIgnoreCase(RegCompCheckerConstant.REG_INIT_CONFIRMATION)) {
					status = RegCompCheckerConstant.REG_INIT_CONFIRMATION_STATUS;
				} else if (pageName.equalsIgnoreCase(RegCompCheckerConstant.LINKING_PAYMENT)) {
					status = RegCompCheckerConstant.LINKING_PAYMENT_STATUS;
				} else if (pageName.equalsIgnoreCase(RegCompCheckerConstant.WITNESS_DETAILS)) {
					status = RegCompCheckerConstant.WITNESS_DETAILS_STATUS;
				} else if (pageName.equalsIgnoreCase(RegCompCheckerConstant.LINKING_HISTORY)) {
					status = RegCompCheckerConstant.LINKING_HISTORY_STATUS;
				} else if (pageName.equalsIgnoreCase(RegCompCheckerConstant.PRESENTATION)) {
					status = RegCompCheckerConstant.PRESENTATION_STATUS;
				} else if (pageName.equalsIgnoreCase(RegCompCheckerConstant.PRESENTATION_4_MNTH)) {
					status = RegCompCheckerConstant.PRESENTATION_4_MNTH_STATUS;
				} else if (pageName.equalsIgnoreCase(RegCompCheckerConstant.PRESENTATION_11_MNTH)) {
					status = RegCompCheckerConstant.PRESENTATION_11_MNTH_STATUS;
				} else if (pageName.equalsIgnoreCase(RegCompCheckerConstant.COMPLIANCE_LIST)) {
					status = RegCompCheckerConstant.COMPLIANCE_LIST_STATUS;
				} else if (pageName.equalsIgnoreCase(RegCompCheckerConstant.CHECKLIST)) {
					status = RegCompCheckerConstant.CHECKLIST_STATUS;
				} else if (pageName.equalsIgnoreCase(RegCompCheckerConstant.BIOMETRIC_DETAILS)) {
					status = RegCompCheckerConstant.BIOMETRIC_DETAILS_STATUS;
				} else if (pageName.equalsIgnoreCase(RegCompCheckerConstant.BOOK_TYPE)) {
					status = RegCompCheckerConstant.BOOK_TYPE_STATUS;
				} else if (pageName.equalsIgnoreCase(RegCompCheckerConstant.ADD_SEALS)) {
					status = RegCompCheckerConstant.ADD_SEALS_STATUS;
				}
				boolean flag = bd.UpdateRegistrationCompletionStatus(formDTO.getRegInitNumber(), status, cdate, userId);
				if (flag) {
					logger.debug("STATUS UPDATED SUCCESSFULLY");
				}
				forwardJsp = RegCompCheckerConstant.USER_LOGIN_CONFIRMATION;
			}
			// ********************************END-PARTIAL
			// SAVE*****************************//
		}
		logger.debug("FORWARD PAGE ---->:::" + mapping.findForward(forwardJsp));
		return mapping.findForward(forwardJsp);
	}

	// //////////////////////COMMON METHODS FOR
	// UPLOAD/////////////////////////////////////////////
	private String getFileExtension(String fileName) {
		try {
			int pos = fileName.lastIndexOf(".");
			String strExt = fileName.substring(pos + 1, fileName.length());
			return strExt;
		} catch (Exception e) {}
		return "";
	}

	// added by shruti---2 june 2014
	public static void downloadDocument(HttpServletResponse res, byte[] docContents, String fileName) {
		try {
			OutputStream os = res.getOutputStream();
			res.setContentType("application/download");
			res.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			os.write(docContents);
			os.flush();
			os.close();
		} catch (Exception e) {}
	}

	// end
	private String uploadFile(FormFile filetobeuploaded, String fileName, String regNum) {
		String filePath = "D:/upload/";
		File folder = new File(filePath);
		if (!folder.exists()) {
			folder.mkdir();
		}
		try {
			File newFile = new File(filePath, fileName);
			if (!newFile.exists()) {
				// logger.info("NEW FILE NAME:-" + newFile);
				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(filetobeuploaded.getFileData());
				fos.close();
			} else {
				String str = fileName.substring(0, fileName.indexOf("."));
				fileName = str + "01" + ".jpg";
				newFile = new File(filePath, fileName);
				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(filetobeuploaded.getFileData());
				fos.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	private String removeFile(String fileName) {
		String sharedPath = null;
		try {
			sharedPath = PropertiesFileReader.getInstance("resources.igrs").getValue("ddrive.temp.path");
		} catch (Exception e) {
			logger.error("Error :- " + e.getMessage());
			e.printStackTrace();
		}
		String filePath = sharedPath + "/upload/";
		File newFile = new File(filePath + fileName);
		newFile.delete();
		return "";
	}

	private String uploadFile(String regTxnId, byte[] content, String fileUplaodPath, String fileName) {
		// FormFile uploadedFile = regForm.getCertificate();
		PropertiesFileReader proper;
		String FILE_UPLOAD_PATH = "";
		try {
			proper = PropertiesFileReader.getInstance("resources.igrs");
			FILE_UPLOAD_PATH = proper.getValue("igrs_upload_path") + File.separator + "03" + File.separator;
		} catch (Exception e1) {
			logger.debug(e1.getStackTrace());
		}
		String filePath = FILE_UPLOAD_PATH + regTxnId + fileUplaodPath + "/";
		// String fileName=RegInitConstant.FILE_NAME_CERTIFICATE;
		/*
		 * String filePath = getServlet().getServletContext().getRealPath("") +
		 * "/temp/";
		 */
		File folder = new File(filePath);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		try {
			File newFile = new File(filePath, fileName);
			if (!newFile.exists()) {
				// logger.info("NEW FILE NAME:-" + newFile);
				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(content);
				fos.close();
			} else {
				// String str = fileName.substring(0, fileName.indexOf("."));
				// fileName = str + "_01" + ".jpg";
				newFile = new File(filePath, fileName);
				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(content);
				fos.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return filePath + fileName;
	}

	// ************THIS METHOD IS FOR UPLOADING PARTY SPECIFIC DOCUMENTS --DEATH
	// CERTIFICATE AND RELATION PROOF***********//
	private String uploadFile(String regTxnId, byte[] content, String PartyId, String fileUplaodPath, String fileName, String modId) {
		// FormFile uploadedFile = regForm.getCertificate();
		PropertiesFileReader proper;
		String FILE_UPLOAD_PATH = "";
		try {
			proper = PropertiesFileReader.getInstance("resources.igrs");
			FILE_UPLOAD_PATH = proper.getValue("igrs_upload_path") + File.separator + "03" + File.separator;
		} catch (Exception e1) {
			logger.debug(e1.getStackTrace());
		}
		String filePath = "";
		if (modId.equals("3")) {
			filePath = FILE_UPLOAD_PATH + regTxnId + fileUplaodPath + PartyId + "/";
		} else {
			filePath = RegCompConstant.FILE_UPLOAD_PATH + regTxnId + fileUplaodPath + PartyId + "/";
		}
		// String fileName=RegInitConstant.FILE_NAME_CERTIFICATE;
		/*
		 * String filePath = getServlet().getServletContext().getRealPath("") +
		 * "/temp/";
		 */
		File folder = new File(filePath);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		try {
			File newFile = new File(filePath, fileName);
			if (!newFile.exists()) {
				// logger.info("NEW FILE NAME:-" + newFile);
				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(content);
				fos.close();
			} else {
				// String str = fileName.substring(0, fileName.indexOf("."));
				// fileName = str + "_01" + ".jpg";
				newFile = new File(filePath, fileName);
				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(content);
				fos.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return filePath + fileName;
	}

	public String uploadFile(String regTxnId, byte[] content) {
		String filePath;
		filePath = RegCompConstant.FILE_UPLOAD_PATH + regTxnId + RegCompConstant.UPLOAD_PATH_DEED_DOC;
		String fileName = RegCompConstant.FILE_NAME_DEED_DOC;
		File folder = new File(filePath);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		try {
			File newFile = new File(filePath, fileName);
			if (!newFile.exists()) {
				logger.info("NEW FILE NAME:-" + newFile);
				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(content);
				fos.close();
			} else {
				// String str = fileName.substring(0, fileName.indexOf("."));
				// fileName = str + "_01" + ".jpg";
				// newFile = new File(filePath, fileName);
				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(content);
				fos.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return filePath + fileName;
	}

	// ///////////////////////////////////////END OF COMMON METHODS FOR
	// UPLOAD//////////////////////////////////////////
	// Added by Ankit for aadhar integration - start
	public boolean callEKYCWebService(RegCompCheckerForm regForm, RegCompCheckerDTO formDTO, RegCompCheckerBD bd, String language, String actionName, String base64, String userId) {
		boolean isWebServiceCalled = true;
		String partyTxnId = null, consenterTxnId = null;
		AadharDetailDTO aadharDtsDto = null;
		AadharRespDTO adharRespDTO = null;
		String errCode = "";
		String exceptionMsg = null;
		PropertiesFileReader pr = null;
		boolean isAadharFailTxInserted = false;
		ClientResponse aadharResponse = null;
		String ERR_CODE_SEPARATOR;
		String LEFT_PARENTHESIS;
		String RIGHT_PARENTHESIS;
		String model = "";
		String serialNo = "";
		try {
			pr = PropertiesFileReader.getInstance("resources.igrs");
			ERR_CODE_SEPARATOR = pr.getValue(RegInitConstant.ERR_CODE_SEPARATOR);
			LEFT_PARENTHESIS = pr.getValue(RegInitConstant.LEFT_PARENTHESIS);
			RIGHT_PARENTHESIS = pr.getValue(RegInitConstant.RIGHT_PARENTHESIS);
			if (RegCompCheckerConstant.PARTY_PRESENT_THUMB.equalsIgnoreCase(actionName)) {
				partyTxnId = formDTO.getPartyIdUpload();
				aadharDtsDto = bd.getAadharDetailsByPartyTxnId(partyTxnId);
			}
			if (RegCompCheckerConstant.BIOMETRIC_DETAILS_THUMB.equalsIgnoreCase(actionName)) {
				consenterTxnId = formDTO.getConIdUpload();
				aadharDtsDto = bd.getAadharDetailsByConstTxnNum(consenterTxnId);
			}
			if (aadharDtsDto.getADHAR_NO() == null) {
				formDTO.setAadharErrMsg(" Party details not found in database ");
			} else {
				logger.debug(" Aadhar no : " + aadharDtsDto.getADHAR_NO() + " Aadhar Name : " + aadharDtsDto.getADHAR_NAME());
			}
			Calendar cal = Calendar.getInstance();
			Timestamp currentTimestamp = new java.sql.Timestamp(cal.getTime().getTime());
			String currentTimestampStr = currentTimestamp.toString().replaceAll("[- .^:,]", "");
			serialNo = formDTO.getSerialNo();
			model = formDTO.getModel();
			// Device serial No substring logic
			if (serialNo != null && !serialNo.isEmpty()) {
				String serialNoReverse = new StringBuffer(serialNo).reverse().toString();
				if (serialNoReverse.length() > 6) {
					serialNoReverse = serialNoReverse.substring(0, 6);
					serialNo = new StringBuffer(serialNoReverse).reverse().toString();
				} else if (serialNoReverse.length() == 6) {
					serialNo = serialNo;
				} else {
					serialNo = userId.substring(4); // userId is always 10
					// digits
				}
			}
			// Device Model substring logic
			if (model != null && !model.isEmpty()) {
				String modelReverse = new StringBuffer(model).reverse().toString();
				if (modelReverse.length() > 5) {
					modelReverse = modelReverse.substring(0, 5);
					model = new StringBuffer(modelReverse).reverse().toString();
				} else if (modelReverse.length() == 5) {
					model = model;
				} else {
					// model = "0450F";
					// model = "SD200"
					model = pr.getValue(RegInitConstant.MODEL_DEFAULT_VALUE);
				}
			}
			MetaPojo meta = new MetaPojo();
			meta.setFdc(pr.getValue(RegInitConstant.FDC_EKYC));
			meta.setIdc(pr.getValue(RegInitConstant.IDC_EKYC));
			meta.setLot(pr.getValue(RegInitConstant.LOT_EKYC));
			meta.setLov(pr.getValue(RegInitConstant.LOV_EKYC));
			meta.setPip(pr.getValue(RegInitConstant.PIP_EKYC));
			// meta.setUdc(pr.getValue(RegInitConstant.UDC_EKYC));
			meta.setUdc(pr.getValue(RegInitConstant.UDC_EKYC) + ":" + "C" + ":" + serialNo + ":" + model);
			UsesPojo uses = new UsesPojo();
			uses.setBio(pr.getValue(RegInitConstant.TRUE_VALUE));
			uses.setOtp(pr.getValue(RegInitConstant.FALSE_VALUE));
			uses.setPa(pr.getValue(RegInitConstant.FALSE_VALUE));
			uses.setPfa(pr.getValue(RegInitConstant.FALSE_VALUE));
			uses.setPi(pr.getValue(RegInitConstant.FALSE_VALUE));
			uses.setPin(pr.getValue(RegInitConstant.FALSE_VALUE));
			AadharDTO aadharDto = new AadharDTO();
			aadharDto.setDomainId(pr.getValue(RegInitConstant.DOMAIN_ID));
			// ISOimage from api -- need to implement
			//
			// aadharDto.setIsoImage(base64Image.replaceAll("\r\n", ""));\
			// String base64Image =
			// "Rk1SACAyMAAAAAEIAAABAAGQAMUAxQEAAQA3JUCFABQAAEBsABgUAEB6ABiUAIDFABtIAEBSACksAEB+ACkAAICTAClgAIC0AD5AAIDjAEM0AIAgAEpIAEBIAF1AAECKAF80AEC0AF8sAEBkAGFIAECTAG0kAEA2AHJQAEDOAHKUAICtAHkkAIAjAIVYAEB8AIn4AEBsAJ7sAEC5AKEUAICpAKMMAEBzAMnwAEBdAMtkAIAlARnsAEASASRkAECPATfwAIB6AUjsAECtAUr0AECmAVhwAIA9AVrkAECBAV/kAEDCAWv4AIDcAWt8AECMAXRgAEC5AXfwAAAMCgEACGYFjQAAAAAA";
			// aadharDto.setUid("868224899522"); //hardcode value of UID as
			// unable to verify with actual value
			aadharDto.setIsoImage(base64);
			aadharDto.setUid(aadharDtsDto.getADHAR_NO());
			aadharDto.setLk(pr.getValue(RegInitConstant.LICENSE_KEY));
			aadharDto.setSa(pr.getValue(RegInitConstant.SUB_AUA_CODE));
			aadharDto.setTid(pr.getValue(RegInitConstant.TID));
			aadharDto.setTransactionId(pr.getValue(RegInitConstant.SUB_AUA_CODE) + ":" + currentTimestampStr);
			aadharDto.setConsent(pr.getValue(RegInitConstant.CONSENT));
			if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				aadharDto.setLData(pr.getValue(RegInitConstant.FALSE_VALUE));
			}
			if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)) {
				aadharDto.setLData(pr.getValue(RegInitConstant.TRUE_VALUE));
			}
			aadharDto.setUses(uses);
			aadharDto.setMeta(meta);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			String aadharJsonString = mapper.writeValueAsString(aadharDto);
			logger.debug("aadhar JSON request : " + aadharJsonString);
			JSONObject aadharJsonObject = new JSONObject(aadharJsonString);
			Client client = Client.create();
			client.setConnectTimeout(Integer.parseInt(pr.getValue(RegInitConstant.AADHAR_CONNECTION_TIMEOUT)));
			client.setReadTimeout(Integer.parseInt(pr.getValue(RegInitConstant.AADHAR_READ_TIMEOUT)));
			WebResource webResource = client.resource(pr.getValue(RegInitConstant.AADHAR_EKYC_URL));
			if (aadharDtsDto.getADHAR_NO() != null) {
				aadharResponse = webResource.accept("application/json").post(ClientResponse.class, aadharJsonObject);
				if (aadharResponse != null) {
					if (aadharResponse.getStatus() != 200) {
						isWebServiceCalled = false;
						throw new RuntimeException("Failed : HTTP error code : " + aadharResponse.getStatus());
					}
					String respString = aadharResponse.getEntity(String.class);
					adharRespDTO = mapper.readValue(respString, AadharRespDTO.class);
					logger.debug("Aadhar Response : " + respString);
					formDTO.setAadharRespCode(adharRespDTO.getRet());
					// regForm.setAcknowledgementId(adharRespDTO.getTransactionCode());
					if ("0".equalsIgnoreCase(adharRespDTO.getRet())) {
						// if(!(adharRespDTO.getAadhaarId()==Integer.parseInt(aadharDtsDto.getADHAR_NO()))){
						if (adharRespDTO.getKsaFailureCode() != null && !("0".equalsIgnoreCase(adharRespDTO.getKsaFailureCode()))) {
							String auaErrCode = adharRespDTO.getKsaFailureCode().substring(0, adharRespDTO.getKsaFailureCode().indexOf(ERR_CODE_SEPARATOR));
							logger.debug(" AuaFailureCode : " + adharRespDTO.getKsaFailureCode() + " :: " + pr.getValue(auaErrCode));
							formDTO.setAadharErrMsg(pr.getValue(RegInitConstant.AADHAR_EKYC_GENRIC_MSG));
							errCode = "KsaFailureCode : " + adharRespDTO.getKsaFailureCode();
						}
						if (adharRespDTO.getSrdhFailureCode() != null) {
							logger.debug(" SrdhFailureCode : " + adharRespDTO.getSrdhFailureCode() + " ::  " + pr.getValue(adharRespDTO.getSrdhFailureCode()));
							if ("807".equalsIgnoreCase(adharRespDTO.getSrdhFailureCode()) || "808".equalsIgnoreCase(adharRespDTO.getSrdhFailureCode())
							// ||
							// "0".equalsIgnoreCase(adharRespDTO.getSrdhFailureCode())
							) {
								formDTO.setAadharErrMsg(pr.getValue(adharRespDTO.getSrdhFailureCode()));
							} else {
								formDTO.setAadharErrMsg(pr.getValue(RegInitConstant.AADHAR_EKYC_GENRIC_MSG));
							}
							errCode = errCode + " :: SrdhFailureCode :" + adharRespDTO.getSrdhFailureCode();
						}
						if (adharRespDTO.getActn() != null && adharRespDTO.getKsaFailureCode() != null && !("0".equalsIgnoreCase(adharRespDTO.getKsaFailureCode()))) {
							String auaCode = adharRespDTO.getKsaFailureCode();
							String auaErrCode = adharRespDTO.getKsaFailureCode().substring(0, adharRespDTO.getKsaFailureCode().indexOf(ERR_CODE_SEPARATOR));
							String actionCode = auaCode.substring(auaCode.indexOf(LEFT_PARENTHESIS) + LEFT_PARENTHESIS.length(), auaCode.indexOf(RIGHT_PARENTHESIS));
							String auaErrDesc = auaCode.substring(auaCode.indexOf(ERR_CODE_SEPARATOR) + ERR_CODE_SEPARATOR.length(), auaCode.indexOf(LEFT_PARENTHESIS));
							errCode = errCode + " :: actionCode : " + actionCode + "\n :: auaErrDesc : " + auaErrDesc;
							logger.debug(" auaErrorCode : " + auaErrCode + "\n" + " :: actionCode : " + actionCode + "\n :: auaErrDesc : " + auaErrDesc);
							formDTO.setAadharErrMsg(auaErrDesc + " Reason : " + pr.getValue(actionCode.trim()));
						}
						RegCommonBD commonBD = new RegCommonBD();
					//	isAadharFailTxInserted = commonBD.insertAadharFailTx(aadharDtsDto.getADHAR_NO().toString(), partyTxnId, formDTO.getRegInitNumber(), consenterTxnId, errCode, formDTO.getAadharErrMsg(), adharRespDTO.getTransactionCode(), "Y");
						if (!isAadharFailTxInserted) {
							logger.debug(" Error in inserting Aadhar Fail Txns .... ");
						}
					} else if ("1".equalsIgnoreCase(adharRespDTO.getRet())) {
						// }else if
						// (adharRespDTO.getAadhaarId()==Integer.parseInt(aadharDtsDto.getADHAR_NO())){
						formDTO.setAadharErrMsg(pr.getValue("1"));
						logger.debug(" return code 1 : " + pr.getValue("1"));
						// Convert base64 encoded string to image
						String imagePath = null;
						String imageName = pr.getValue(RegInitConstant.RESIDENT_PHOTO_NAME);
						String imageString = adharRespDTO.getResidentDetails().getResidentPhoto();
						if (partyTxnId != null) {
							imagePath = pr.getValue("igrs_upload_path") + "/01/" + formDTO.getRegInitNumber() + "/01/" + partyTxnId + "/";
						}
						if (consenterTxnId != null) {
							imagePath = pr.getValue("igrs_upload_path") + "/01/" + formDTO.getRegInitNumber() + "/01/" + consenterTxnId + "/";
						}
						String imgFormat = pr.getValue(RegInitConstant.RES_IMG_FORMAT);
						// resident photo at shared drive
						boolean isImgSaved = decodeToImageAndSave(imageString, imagePath, imageName, imgFormat.trim());
						if (isImgSaved) {
							logger.debug(" Image decoded and saved at : " + imagePath);
							// update aadhar details
							boolean isAadharUpadted = bd.updateAadharDetails(adharRespDTO, partyTxnId, consenterTxnId, imagePath + imageName);
							if (!isAadharUpadted) {
								logger.debug(" Error in updating Aadhar details in database ");
								formDTO.setAadharErrMsg(pr.getValue(RegInitConstant.TECHNICAL_ERROR));
							}
						} else {
							logger.debug(" Error in decoding Image or saving at : " + imagePath);
							formDTO.setAadharErrMsg(pr.getValue(RegInitConstant.TECHNICAL_ERROR));
						}
					} else {
						logger.debug("return code is neither 0 or 1 ----- NOT MATCHING");
						formDTO.setAadharErrMsg(pr.getValue(RegInitConstant.AADHAR_EKYC_GENRIC_MSG));
					}
					logger.debug(" AadharErrMsg :  " + formDTO.getAadharErrMsg());
					formDTO.setAadharDto(aadharDto);
				} else {
					formDTO.setAadharErrMsg(pr.getValue(RegInitConstant.UNABLE_PROCESS_EKYC_REQ));
				}
			}
		} catch (ClientHandlerException e) {
			isWebServiceCalled = false;
			formDTO.setAadharErrMsg(pr.getValue(RegInitConstant.EKYC_RESPONSE_TIMEOUT_ERR));
			logger.debug("SocketTimeoutException : " + e.getMessage());
		} catch (FileNotFoundException e) {
			isWebServiceCalled = false;
			formDTO.setErrAndExpMsg(formDTO.getAadharErrMsg() == null ? e.getMessage() : formDTO.getAadharErrMsg() + " :: FileNotFoundException : " + e.getMessage());
			logger.debug("FileNotFoundException : " + e.getMessage());
		} catch (IOException e) {
			isWebServiceCalled = false;
			formDTO.setErrAndExpMsg(formDTO.getAadharErrMsg() == null ? e.getMessage() : formDTO.getAadharErrMsg() + " :: IOException : " + e.getMessage());
			logger.debug("IOException : " + e.getMessage());
		} catch (JSONException e) {
			isWebServiceCalled = false;
			formDTO.setErrAndExpMsg(formDTO.getAadharErrMsg() == null ? e.getMessage() : formDTO.getAadharErrMsg() + " :: JSONException : " + e.getMessage());
			logger.debug("JSONException : " + e.getMessage());
		} catch (Exception e) {
			isWebServiceCalled = false;
			formDTO.setErrAndExpMsg(formDTO.getAadharErrMsg() == null ? e.getMessage() : formDTO.getAadharErrMsg() + " :: Exception : " + e.getMessage());
			logger.debug("Exception : " + e.getMessage());
		} finally {
			logger.info(" Aadhar EKYC finally block .....");
			try {
				if (adharRespDTO != null) {
					if ("0".equalsIgnoreCase(adharRespDTO.getRet())) {
						RegCommonBD commonBD = new RegCommonBD();
						if (aadharDtsDto.getADHAR_NO() != null && !formDTO.getErrAndExpMsg().isEmpty() && !isAadharFailTxInserted) {
							/*boolean isInserted = commonBD.insertAadharFailTx(aadharDtsDto.getADHAR_NO().toString(), partyTxnId, formDTO.getRegInitNumber(), consenterTxnId, errCode, formDTO.getErrAndExpMsg(), adharRespDTO.getTransactionCode(), "Y");
							if (!isInserted) {
								logger.debug(" Error in inserting Aadhar Fail Txns .... ");
							}*/
						}
					}
				}
				if (aadharResponse != null) {
					aadharResponse.close();
				}
			} catch (Exception e) {
				isWebServiceCalled = false;
				logger.debug("Exception in  finally block : Adhar EKYC Webservice");
			}
			return isWebServiceCalled;
		}
	}

	public static String convertImagToBase64(String urlString, String type) {
		String imageString = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			BufferedImage img = ImageIO.read(new File(urlString));
			ImageIO.write(img, type, bos);
			byte[] imageBytes = bos.toByteArray();
			BASE64Encoder encoder = new BASE64Encoder();
			imageString = encoder.encode(imageBytes);
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageString;
	}

	// Added by Ankit for aadhar integration - end
	// Added by Ankit for aadhar integration ----- start >>
	public static boolean decodeToImageAndSave(String imageString, String imagePath, String imageName, String imgFormat) {
		BufferedImage image = null;
		byte[] imageByte;
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			imageByte = decoder.decodeBuffer(imageString);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
			image = ImageIO.read(bis);
			String residentFilePath = imagePath + imageName;
			File outputfile = new File(imagePath);
			File filepath = new File(residentFilePath);
			if (outputfile.exists() && outputfile.isDirectory()) {
				ImageIO.write(image, imgFormat, filepath);
			} else {
				new File(imagePath).mkdirs();
				ImageIO.write(image, imgFormat, filepath);
			}
			bis.close();
			return true;
		} catch (IOException io) {
			io.printStackTrace();
			return false;
		}
	}
	// Added by Ankit for aadhar integration ----- end >>
}
