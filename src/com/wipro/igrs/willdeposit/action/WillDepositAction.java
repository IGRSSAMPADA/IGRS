/* Copyright (c) 2007-08 WIPRO INFOTECH. All Rights Reserved.
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
 * FILE NAME: WillDepositAction.java
 * @author ANUJ PATEL
 * @version 1.0 
 * INITIAL CODING: 28th DEC 2008 
 * MODIFIED ON:    12th APRIL 2008 
 * MODIFIED ON:	   24th JULY 2013
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE ACTION FOR GUIDELINE DELIEVRY UPDATION 
 */

package com.wipro.igrs.willdeposit.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.util.GUIDGenerator;
import com.wipro.igrs.util.PropertiesFileReader;
import com.wipro.igrs.willdeposit.bd.WillDepositBD;
import com.wipro.igrs.willdeposit.bd.WillUpdateBD;
import com.wipro.igrs.willdeposit.bd.WillViewBD;
import com.wipro.igrs.willdeposit.bd.WillWithdrawalBD;
import com.wipro.igrs.willdeposit.constant.CommonConstant;
import com.wipro.igrs.willdeposit.dao.WillCommonDAO;
import com.wipro.igrs.willdeposit.dto.WillDepositDTO;
import com.wipro.igrs.willdeposit.form.WillDepositForm;

/**
 * @author ANUJ PATEL.
 * 
 */
public class WillDepositAction extends BaseAction {

	String forwardJsp = new String("depositeWill");
	private Logger logger = (Logger) Logger.getLogger(WillDepositAction.class);

	/**
	 * Method execute for Will Deposition.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	/*
	 * (non-Java doc)
	 * 
	 * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		String lang=(String)session.getAttribute("languageLocale");
		ActionMessages messages = new ActionMessages();
		String frwdName = request.getParameter("fwdName");
		System.out.println(frwdName);
		WillDepositForm eForm = (WillDepositForm) form;
		WillDepositDTO formDTO = eForm.getWillDTO();
		String page = request.getParameter("page");
		String roleId = (String) session.getAttribute("roleId");
		String funId = (String) session.getAttribute("functionId");
		String userId = (String) session.getAttribute("UserId");
		String officeId = (String) session.getAttribute("loggedToOffice");
		String countryId = request.getParameter("countryId");
		if(countryId==null||"".equalsIgnoreCase(countryId)){
			countryId = formDTO.getCountry();
		}
		String stateId = request.getParameter("stateId");
		if(stateId==null||"".equalsIgnoreCase(stateId)){
			stateId = formDTO.getState();
		}
		String agentCountryId = request.getParameter("agentCountryId");
		if(agentCountryId==null||"".equalsIgnoreCase(agentCountryId)){
			agentCountryId=formDTO.getAgentCountry();
		}
		String agentStateId = request.getParameter("agentStateId");
		if(agentStateId==null||"".equalsIgnoreCase(agentStateId)){
			agentStateId=formDTO.getAgentState();
		}
		String serviceId = null;
		String filePath = getServlet().getServletContext().getRealPath("")
				+ "/temp/";

		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		String uplPath=pr.getValue("igrs_upload_path")+"/14/01/";
		WillDepositBD bd = new WillDepositBD();
		ArrayList listAgentType = bd
				.getAgentType(CommonConstant.IGRS_AGENT_TYPE);
		ArrayList listCountry = bd.getCountry(lang);
		ArrayList agentListCountry = bd.getCountry(lang);
		eForm.setWillAgentCountry(agentListCountry);
		ArrayList listState = bd.getState(countryId, lang);
		eForm.setWillState(listState);
		ArrayList listDistrict = bd.getDistrict(stateId, lang);
		eForm.setWillDistrict(listDistrict);
		ArrayList listID = bd.getIDProof(CommonConstant.IGRS_ID_TYPE, lang);
		ArrayList listAgentState = bd.getState(agentCountryId, lang);
		eForm.setWillAgentState(listAgentState);
		ArrayList listAgentDistrict = bd.getDistrict(agentStateId, lang);
		eForm.setWillAgentDistrict(listAgentDistrict);
		eForm.setWillAgent(listAgentType);
		eForm.setWillCountry(listCountry);
		ArrayList willRemarks = null;

		eForm.setWillId(listID);

		String StrFunId = funId;
		float total = 0;
		String otherFeeVal = "";
		String userType = "";
		userType = (String) session.getAttribute("loggedInRole");

		if (userType == null) {
			userType = "SRO";
		}

		String feeVal = bd.getWillFeeValue(StrFunId) == null ? "0.0" : bd
				.getWillFeeValue(StrFunId);
		if (feeVal != null)
			if (!feeVal.equalsIgnoreCase("")) {
				formDTO.setFee(feeVal);
			}
		logger.debug("fee is:" + feeVal);
		otherFeeVal = "0.0";
				if("".equalsIgnoreCase(otherFeeVal)){
				otherFeeVal="0.0";
				}
		logger.debug("Otherfee is:" + otherFeeVal);
		if (otherFeeVal != null) {
			formDTO.setOtherFee(otherFeeVal);
			total = Float.parseFloat(formDTO.getFee() == null ? "0.0" : formDTO
					.getFee())
					+ Float.parseFloat(formDTO.getOtherFee() == null ? "0.0"
							: formDTO.getOtherFee());
			formDTO.setTotalFee(String.valueOf(total));
			logger.debug("total fee is:" + total);
		}
		request.setAttribute("fee", feeVal);
		request.setAttribute("otherFee", otherFeeVal);
		request.setAttribute("totalFee", String.valueOf(total));
		session.setAttribute("formDTO", formDTO);
		// TODO SESSION

		String actionName = formDTO.getActionName();

		ArrayList errorList = new ArrayList();

		if ("willWithdrawPending".equalsIgnoreCase(request
				.getParameter("willStatus"))) {
			String s = (String) request.getParameter("willStatus");
			String will = (String) request.getParameter("will");
			request.setAttribute("willStatus", will);

			String willStat[] = will.split("~");
			if (willStat.length == 2) {
				String will_txn_id = (String) willStat[0];
				request.setAttribute("newWillId", will_txn_id);
				formDTO.setPayWillId(will_txn_id);
				String amount = (String) willStat[1];
				if ("null".equals(amount)) {
					amount = "0";
				}
				double d = Double.parseDouble(amount);
				int amt=(int)d;
				formDTO.setAmount(amt);
				if (amt <= 0) {
					request.setAttribute("depWillID", will_txn_id);
					String stat = bd.checkStatus(will_txn_id, funId);
					forwardJsp = stat;
					if ("pending".equalsIgnoreCase(stat)) {
						WillViewBD viewBD = new WillViewBD();
						formDTO = viewBD
								.getwillIdDetails(will_txn_id, StrFunId,lang);// withdrawalBD.getWillDeposit(willid1,StrFunId);
						willRemarks = bd.getWillRemarks(will_txn_id);
						formDTO.setWillRemarksList(willRemarks);
						request.setAttribute("PAGETYPE", "willWithUpdate");
						formDTO.setTotalFee(String.valueOf(total));
						eForm.setWillDTO(formDTO);
						forwardJsp = "depositIdDetailsReadOnlyPending";
					}
				} else {
					// a if 100% payment is done
					WillViewBD viewBD = new WillViewBD();
					formDTO = viewBD.getwillIdDetails(will_txn_id, StrFunId,lang);// withdrawalBD.getWillDeposit(willid1,StrFunId);
					willRemarks = bd.getWillRemarks(will_txn_id);
					formDTO.setWillRemarksList(willRemarks);
					formDTO.setTotalFee(String.valueOf(total));
					request.setAttribute("PAGETYPE", "willWithUpdate");
					eForm.setWillDTO(formDTO);
					forwardJsp = "depositIdDetailsReadOnlyPending";
				}

			}
			// coded

			// coded

			// a

		}

		// willwithdrawal
		if ("pendingPayment".equalsIgnoreCase(request.getParameter("pending"))) {

			if ("proceedPayment".equals(formDTO.getActionName())) {
				WillDepositDTO willDTO = new WillDepositDTO();
				String newWillId = "";
				logger.debug("Func Id of Will Withdrawl is:" + funId);
				if (funId == null) {
					funId = "FUN_013";
				}
				// Need to check the method...
				// willDTO=bd.getFunctionName(funId);
				WillUpdateBD willUpdateBD = new WillUpdateBD();
				String status = (String) session.getAttribute("status");
				boolean citizenUpdated = false;
				newWillId = bd.updateWillWithdrawDetails1(userId, formDTO
						.getWillId(), total, funId);

				int amount = bd.amountLeft(formDTO.getWillId(), String.valueOf(total));
				formDTO.setWithWillId(newWillId);
				if (newWillId == "aaaaaa") {
					request.setAttribute("newWillId", formDTO.getWithWillId());
					forwardJsp = "txnSuccessful";
					logger.debug("WillWithdrawalAction:: GOING TO :-       "
							+ forwardJsp);
				} else {
					WillWithdrawalBD withdrawalBD = new WillWithdrawalBD();
					ArrayList alist = withdrawalBD.getDetails(officeId);
					ArrayList rowList = (ArrayList) alist.get(0);
					String officeName = (String) rowList.get(1);
					String districtId = (String) rowList.get(2);

					String districtName = (String) rowList.get(3);
					String willRefId = formDTO.getWillId();

					logger
							.debug("WillWithdrawalAction:: inside    proceedPayment    ");
					request.setAttribute("forwardPath", "./depPayment.do?TRFS=NGI");
					request.setAttribute("parentTable",
							"IGRS_WILL_TXN_PAYMENT_DTLS");
					request
							.setAttribute("parentAmount", String
									.valueOf(amount));
					request.setAttribute("parentFunId", funId);
					request.setAttribute("parentKey", newWillId);
					request.setAttribute("parentModName", "WILL DEPOSIT");
					request.setAttribute("parentFunName", "WILL WITHDRAWL");
					request.setAttribute("parentColumnName", "WILL_PAYMENT_ID");
					session.setAttribute("formDTO", formDTO);
					request.setAttribute("parentOfficeId", officeId);
					request.setAttribute("parentOfficeName", officeName);
					request.setAttribute("parentDistrictId", districtId);
					request.setAttribute("parentDistrictName", districtName);
					request.setAttribute("parentReferenceId", willRefId);
					request.setAttribute("formName","willdeposit");
					
					// request.setAttribute("willId", newWillId);
					// session.setAttribute("user", roleId);
					// session.setAttribute("amount",
					// String.valueOf(formDTO.getTotalFee()));
					// session.setAttribute("forwardPath",
					// "./Withdraw.do?page=updateWill");
					// session.setAttribute("parentModName",
					// willDTO.getParentModName());
					// session.setAttribute("parentFunName",
					// willDTO.getParentFunName());
					// session.setAttribute("parentFunId", funId);
					// session.setAttribute("parentAmount",
					// String.valueOf(total));

					forwardJsp = CommonConstant.PROCEED_PAYMENT_PAGE;
					;
					logger.debug("WillWithdrawalAction:: GOING TO :-       "
							+ forwardJsp);
				}
			}
		}

		// willwithdrawal
		// Start photo upload
		if ("photo".equalsIgnoreCase(frwdName)) {
			if (actionName == null || "".equalsIgnoreCase(actionName)) {
				formDTO.setDocumentName(null);
				// cDto.setCaveatTypeList(refDAO.createCaveatsList());
				// cDto.setCountryMasterList(refDAO.countryList());
			} else if ("setUploadFile".equalsIgnoreCase(actionName)) {
				try {
					FormFile uploadedFile = formDTO.getFilePhoto();
					formDTO.setFilePhoto(uploadedFile);
					if ("".equals(uploadedFile.getFileName())) {
						errorList
								.add("Invalid file selection. Please try again.");
					}
					// formDTO.setDocumentName(null);
					// formDTO.setDocContents(null);
					String fileExt = getFileExtension(uploadedFile
							.getFileName());
					AuditInspectionRule rule = new AuditInspectionRule();
					rule.validateFileType(fileExt);
					int size = uploadedFile.getFileSize();
					double fileSizeInBytes = size;
					// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
					double fileSizeInKB = fileSizeInBytes / 1024.0;
					// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
					double fileSizeInMB = fileSizeInKB / 1024.0;
					DecimalFormat decim = new DecimalFormat("#.##");
					Double fileSize = Double.parseDouble(decim
							.format(fileSizeInMB));
					String photoSize = "(" + fileSize + "MB)";
					System.out.println(formDTO.getFilePhoto().getFileSize());
					// anuj
					String docName = "PHOTO." + fileExt;
					formDTO.setDocName(docName);
					formDTO.setFilePhoto(uploadedFile);
					System.out.println("file content>>>>"
							+ formDTO.getFilePhoto());

					// anuj
					// ArrayList listCountry = bd.getCountry();
					// System.out.println("formDTO.getCountryId() ="+formDTO.getCountryId());
					// System.out.println("formDTO.getCountry() ="+formDTO.getCountry());

					if ("TESTATOR".equalsIgnoreCase(formDTO.getAgentType())) {/*
						ArrayList listState1 = bd
								.getState(formDTO.getCountry(),lang);
						eForm.setWillState(listState1);
						ArrayList listDistrict1 = bd.getDistrict(formDTO
								.getState(),lang);
						eForm.setWillDistrict(listDistrict1);
						eForm.setWillAgentState(listState1);
						eForm.setWillAgentDistrict(listDistrict1);
					*/} else if ("AGENT".equalsIgnoreCase(formDTO.getAgentType())) {/*
						ArrayList listState1 = bd
								.getState(formDTO.getCountry(), lang);
						eForm.setWillState(listState1);
						ArrayList listDistrict1 = bd.getDistrict(formDTO
								.getState(),lang);
						eForm.setWillDistrict(listDistrict1);

						ArrayList listState2 = bd.getState(formDTO
								.getAgentCountry(), lang);
						eForm.setWillAgentState(listState2);
						ArrayList listDistrict2 = bd.getDistrict(formDTO
								.getAgentState(),lang);
						eForm.setWillAgentDistrict(listDistrict1);
						eForm.setWillDistrict(listDistrict2);
					*/} else {/*
						ArrayList listDistrict2 = new ArrayList();
						ArrayList listState1 = bd
								.getState(formDTO.getCountry(),lang);
						eForm.setWillState(listState1);
						ArrayList listDistrict1 = bd.getDistrict(formDTO
								.getState(),lang);
						eForm.setWillDistrict(listDistrict2);

						ArrayList listState2 = bd.getState(formDTO
								.getAgentCountry(),lang);
						eForm.setWillAgentState(listState2);
						listDistrict2 = bd.getDistrict(formDTO.getAgentState(),lang);
						eForm.setWillAgentDistrict(listDistrict1);

					*/}

					if (rule.isError()) {
						errorList
								.add("Invalid file type. Please select another file.");

						request.setAttribute("errorsList", errorList);
					} else {
						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
							errorList
									.add("File size is Greater than 10 MB. Please select another file.");
							request.setAttribute("errorsList", errorList);
						} else {
							formDTO.setDocumentName(uploadedFile.getFileName());
							formDTO.setDocContents(uploadedFile.getFileData());
							formDTO.setPhotoSize(photoSize);
							formDTO.setPhotoCheck("phloded");
						}
					}
				} catch (Exception e) {
					errorList.add("Unable to upload file. Please try again.");
					request.setAttribute("errorsList", errorList);
				}
			}

			forwardJsp = "depositeWill";
		} // end photo upload

		// Start photo upload
		if ("thumb".equalsIgnoreCase(frwdName)) {
			
			 try {
				String filename = request.getParameter("path").toString();
				
				   // set the http content type to "APPLICATION/OCTET-STREAM
				   response.setContentType("application/download");
				   String file = filename.substring(filename.lastIndexOf("/")+1, filename.length());
				   // initialize the http content-disposition header to
				   // indicate a file attachment with the default filename
				   // "myFile.txt"
				  // String fileName = (String)formDTO.getCompThumbPath();
				   //Filename=\"myFile.txt\"";
				   response.setHeader("Content-Disposition", "attachment; filename="
							+ URLEncoder.encode(file,"UTF-8"));
				   
				   File fileToDownload = new File(filename);
				   
				   if(filename.contains("PDF")){
				   FileInputStream fileInputStream = new FileInputStream(fileToDownload);
				   OutputStream out = response.getOutputStream();
				   byte[] buf = new byte[2048];

				   int readNum;
				   while ((readNum=fileInputStream.read(buf))!=-1)
				   {
				      out.write(buf,0,readNum);
				   }
				   fileInputStream.close();
				   out.close();
	
				   }else{
				   BufferedImage bi = ImageIO.read(fileToDownload);
				   OutputStream out = response.getOutputStream();
					ImageIO.write(bi, "gif", out);
					out.close();
				   }
			 
			 
			 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
			
			
			
			forwardJsp = "depositeWill";
		} // end photo upload

		// Start photo upload
		if("showBut".equalsIgnoreCase(frwdName)) {
			formDTO.setShow("show");
			forwardJsp = "depositeWill";
		}
		if ("signature".equalsIgnoreCase(frwdName)) {
			String type = request.getParameter("type");
			System.out.println(formDTO);
			if ("photo".equalsIgnoreCase(type)) {
				//DMS Integration start photo
				try {/*
					ReadPropertiesFile prop = new ReadPropertiesFile();
					PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					
					DocumentOperations dos = new DocumentOperations();
					ODServerDetails ods = new ODServerDetails();
					Dataclass dc = new Dataclass();
					//prop.readPropFile();
					
					ods.setAppServerIp(pr.getValue("AppServerIp"));
					ods.setCabinetName(pr.getValue("CabinetName"));
					ods.setAppServerUserId(pr.getValue("AppServerUserId"));
					ods.setAppServerUserPass(pr.getValue("AppServerUserPass"));
					ods.setImageServerIp(pr.getValue("ImageServerIP"));
					ods.setImageServerPort(pr.getValue("ImageServerPort"));
					
					File file = new File("D:/Upload/14/01/f3423865-e701-4d32-88c3-a7dcd6c7ef2f/Photo.gif");
					dc.setDataclassName(pr.getValue("IGRSDataclass"));
					//dc.setModule("01");
					dc.setType("Photo");
					dc.setUniqueNo(formDTO.getUniqueId());
					dos.uploadDocument(ods, file, "IGRS", true, dc);
				*/} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//DMS Integration end photo
				
				boolean rt = bd.uploadFileCheck(uplPath
						+ formDTO.getUniqueId()+"/PHOTO.GIF");

				
					System.out.println("context path--"+request.getContextPath());
					formDTO.setCompPhotoPath(uplPath
						+ formDTO.getUniqueId()+"/PHOTO.GIF");
					formDTO.setDocumentName("PHOTO.GIF");
					formDTO.setPhotoCheck("uploaded");
				 

			} else if ("thumb".equalsIgnoreCase(type)) {
				logger.debug("checking if thumbimpression is uploaded");

				boolean rt = bd.uploadFileCheck(uplPath
						+ formDTO.getUniqueId()+"/THUMB.GIF");

				
					formDTO.setCompThumbPath(uplPath
						+ formDTO.getUniqueId()+"/THUMB.GIF");
					formDTO.setThunmbName("THUMB.GIF");
					formDTO.setThumbCheck("thumb impression uploaded successfully");
				
			}
			
			else if("certi".equalsIgnoreCase(type)){
				logger.debug("checking if certificate is uploaded");
				boolean rt = bd.uploadFileCheck(uplPath
						+ formDTO.getUniqueId()+"/PROOF.PDF");
				
					formDTO.setCompCertiPath(uplPath
						+ formDTO.getUniqueId()+"/PROOF.PDF");
					formDTO.setAgentProofCheck("uploaded");
					formDTO.setAgentProofName("PROOF.PDF");
				
			}
			
			else{
				logger.debug("checking if signature is uploaded");
String result = (String)request.getParameter("signCaptured");
				boolean rt = bd.uploadFileCheck(uplPath
						+ formDTO.getUniqueId()+"/SIGNATURE.GIF");
				
					formDTO.setCompSignPath(uplPath
						+ formDTO.getUniqueId()+"/SIGNATURE.GIF");
					formDTO.setSignatureName("SIGNATURE.GIF");
					formDTO.setSignCheck("Signature uploaded successfully");
				
			}

			forwardJsp = "depositeWill";
		} // end photo upload

		if ("agentProof".equalsIgnoreCase(frwdName)) {
			if (actionName == null || "".equalsIgnoreCase(actionName)) {
				formDTO.setAgentProofName(null);
				// cDto.setCaveatTypeList(refDAO.createCaveatsList());
				// cDto.setCountryMasterList(refDAO.countryList());
			} else if ("setUploadFile".equalsIgnoreCase(actionName)) {
				try {
					FormFile uploadedFile = formDTO.getAgentProof();
					if ("".equals(uploadedFile.getFileName())) {
						errorList
								.add("Invalid file selection. Please try again.");
					}
					// formDTO.setDocumentName(null);
					// formDTO.setDocContents(null);
					String fileExt = getFileExtension(uploadedFile
							.getFileName());
					AuditInspectionRule rule = new AuditInspectionRule();
					rule.validateFileType(fileExt);
					int size = uploadedFile.getFileSize();
					double fileSizeInBytes = size;
					// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
					double fileSizeInKB = fileSizeInBytes / 1024.0;
					// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
					double fileSizeInMB = fileSizeInKB / 1024.0;
					DecimalFormat decim = new DecimalFormat("#.##");
					Double fileSize = Double.parseDouble(decim
							.format(fileSizeInMB));
					String photoSize = "(" + fileSize + "MB)";
					// ooooooooooooooooooooooooooooooooooooooooooooooooooooo
					// anuj
					String AgentProof = "AGENT." + fileExt;
					formDTO.setAgentsproof(AgentProof);
					// anuj
					// ArrayList listCountry = bd.getCountry();
					// System.out.println("formDTO.getCountryId() ="+formDTO.getCountryId());
					// System.out.println("formDTO.getCountry() ="+formDTO.getCountry());

					if ("TESTATOR".equalsIgnoreCase(formDTO.getAgentType())) {/*
						ArrayList listState1 = bd
								.getState(formDTO.getCountry(),lang);
						eForm.setWillState(listState1);
						ArrayList listDistrict1 = bd.getDistrict(formDTO
								.getState(),lang);
						eForm.setWillDistrict(listDistrict1);
						eForm.setWillAgentState(listState1);
						eForm.setWillAgentDistrict(listDistrict1);
					*/} else if ("AGENT".equalsIgnoreCase(formDTO.getAgentType())) {/*
						ArrayList listState1 = bd
								.getState(formDTO.getCountry(),lang);
						eForm.setWillState(listState1);
						ArrayList listDistrict1 = bd.getDistrict(formDTO
								.getState(),lang);
						eForm.setWillDistrict(listDistrict1);

						ArrayList listState2 = bd.getState(formDTO
								.getAgentCountry(),lang);
						eForm.setWillAgentState(listState2);
						ArrayList listDistrict2 = bd.getDistrict(formDTO
								.getAgentState(),lang);
						eForm.setWillAgentDistrict(listDistrict1);
						eForm.setWillDistrict(listDistrict2);
					*/} else {/*
						ArrayList listDistrict2 = new ArrayList();
						ArrayList listState1 = bd
								.getState(formDTO.getCountry(),lang);
						eForm.setWillState(listState1);
						ArrayList listDistrict1 = bd.getDistrict(formDTO
								.getState(),lang);
						eForm.setWillDistrict(listDistrict2);

						ArrayList listState2 = bd.getState(formDTO
								.getAgentCountry(),lang);
						eForm.setWillAgentState(listState2);
						listDistrict2 = bd.getDistrict(formDTO.getAgentState(),lang);
						eForm.setWillAgentDistrict(listDistrict1);

					*/}

					if (rule.isError()) {
						errorList
								.add("Invalid file type. Please select another file.");

						request.setAttribute("errorsList", errorList);
					} else {
						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
							errorList
									.add("File size is Greater than 10 MB. Please select another file.");
							request.setAttribute("errorsList", errorList);
						} else {
							formDTO.setAgentProofName(uploadedFile
									.getFileName());
							formDTO.setAgentProofContents(uploadedFile
									.getFileData());
							formDTO.setAgentProofSize(photoSize);
							formDTO.setAgentProofCheck("agntloded");

						}
					}
				} catch (Exception e) {
					errorList.add("Unable to upload file. Please try again.");
					request.setAttribute("errorsList", errorList);
				}
			}

			forwardJsp = "depositeWill";
		}

		if (page != null) {

			if ("depositeWill".equals(page)) {

				forwardJsp = CommonConstant.WILL_DEPOSITION_FORWARD_PAGE;
				if (countryId == null) {
					String strin = (String) request.getParameter("modName");
					/*ArrayList listState1 = bd.getState(formDTO.getCountry(),lang);
					eForm.setWillState(listState1);
					ArrayList listDistrict1 = bd
							.getDistrict(formDTO.getState(),lang);
					eForm.setWillDistrict(listDistrict1);

					ArrayList listState2 = bd.getState(formDTO
							.getAgentCountry(),lang);
					eForm.setWillAgentState(listState2);
					ArrayList listDistrict2 = bd.getDistrict(formDTO
							.getAgentState(),lang);
					eForm.setWillAgentDistrict(listDistrict2);
					eForm.setWillDistrict(listDistrict1);
*/
					if ("Will Deposition".equals(strin)) {
						WillDepositDTO willDTO = new WillDepositDTO();
						eForm.setWillDTO(willDTO);
						System.out.println("ANUJ " + willDTO.getUniqueId());
						String str = GUIDGenerator.getGUID();
						willDTO.setUniqueId(str);
						willDTO.setDocName("PHOTO.GIF");
						willDTO.setPhotoPath(uplPath);

						willDTO.setSignature("SIGNATURE.GIF");
						willDTO.setSignPath(uplPath);
						
						willDTO
								.setForwardPath("/WillDeposit.do?fwdName=signature");
						willDTO.setForwardName("/WillDeposit");

						willDTO.setThumb("THUMB.GIF");
						willDTO.setThumbPath(uplPath);

						willDTO.setAgentProofPath(uplPath);
						willDTO.setAgentProofName("PROOF.PDF");

						willDTO.setAgentsproof("CERTI.PDF");

					}

				}
				// session.setAttribute(arg0, eForm)
			} else if (page.equalsIgnoreCase("displyImage")) {
				String imgType = request.getParameter("type");
				String willID = request.getParameter("willID");
				String display = bd.displayObjectDetails(response, willID,
						imgType);

				forwardJsp = "";
			}
			if ("depositWillDashboard".equals(page)) {
				// ANUJ
				WillDepositDTO willDTO = new WillDepositDTO();
				try {
					WillViewBD view = new WillViewBD();
					String districtId = view.getDistrictId(officeId);
					ArrayList PendingWithdrawList = bd
							.pendingWithPayment(districtId, total);
					eForm.getObjDashboard().setPendingWithdrawList(
							PendingWithdrawList);
					request.setAttribute("PendingWithdrawList",
							PendingWithdrawList);
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					logger.error(e1.getMessage(), e1);
				}
				eForm.setWillDTO(willDTO);
				// ANUJ
				forwardJsp = "depositWillDashboard";
				if (countryId == null) {
					//WillDepositDTO willDTO = new WillDepositDTO();
					eForm.setWillDTO(willDTO);

				}
			}
		} else {
			if (form != null) {

				String formName = eForm.getWillDTO().getWillDepositForm();

				if (CommonConstant.WILL_DEPOSIT_FORM.equalsIgnoreCase(formName)) {

					if ("willReadOnlyNext".equals(formDTO.getActionName())) {
						// TODO Anuj changes for entry in database on click of
						// next
						String willId = "";
						String getStatus = "";
						String will = formDTO.getWillId();

						if (will == null) {

							String pending = (String) request
									.getParameter("pending");
							if (pending == null) {
								willId = bd.insertWillDetails(getStatus,
										userId, formDTO.getCity(), formDTO
												.getDepositRemarks(), funId,
										total, formDTO, officeId, lang);

							}

						} else {
							// TODO function to update will details by using
							// existing will id
							willId = bd.insertWillDetails1(getStatus, userId,
									formDTO.getCity(), formDTO
											.getDepositRemarks(), funId, total,
									formDTO, will, lang);
						}
						forwardJsp = CommonConstant.NEXT_READ_ONLY;

					}

				}
				if (CommonConstant.WILL_DEPOSITE_READONLY_FORM
						.equalsIgnoreCase(formName)) {

					if ("willGoToFirst".equals(formDTO.getActionName())) {
WillCommonDAO bdd = new WillCommonDAO();

						if ("TESTATOR".equalsIgnoreCase(formDTO.getAgentType())) {
							/*ArrayList listState1 = bd.getState(formDTO
									.getCountry(),lang);
							eForm.setWillState(listState1);
							ArrayList listDistrict1 = bd.getDistrict(formDTO
									.getState(),lang);
							eForm.setWillDistrict(listDistrict1);
							eForm.setWillAgentState(listState1);
							eForm.setWillAgentDistrict(listDistrict1);*/
							if("en".equalsIgnoreCase(lang))
							{
								if("Male".equalsIgnoreCase(formDTO.getGender()))
								{
									formDTO.setGender("M");
								}
								else
								{
									formDTO.setGender("F");
								}
							}
							else
							{
								if("पुस्र्ष".equalsIgnoreCase(formDTO.getGender()))
								{
									formDTO.setGender("M");
								}
								else
								{
									formDTO.setGender("F");
								}
							}
							formDTO.setIdProof(bdd.getTypeId(formDTO.getIdProof(), lang));
						} else if ("AGENT".equalsIgnoreCase(formDTO
								.getAgentType())) {
							/*ArrayList listState1 = bd.getState(formDTO
									.getCountry(), lang);
							eForm.setWillState(listState1);
							ArrayList listDistrict1 = bd.getDistrict(formDTO
									.getState(),lang);
							eForm.setWillDistrict(listDistrict1);

							ArrayList listState2 = bd.getState(formDTO
									.getAgentCountry(), lang);
							eForm.setWillAgentState(listState2);
							ArrayList listDistrict2 = bd.getDistrict(formDTO
									.getAgentState(),lang);
							eForm.setWillAgentDistrict(listDistrict1);
							eForm.setWillDistrict(listDistrict2);*/
							if("en".equalsIgnoreCase(lang))
							{
								if("Male".equalsIgnoreCase(formDTO.getAgentGender()))
								{
									formDTO.setAgentGender("M");
								}
								else
								{
									formDTO.setAgentGender("F");
								}
							}
							else
							{
								if("पुस्र्ष".equalsIgnoreCase(formDTO.getAgentGender()))
								{
									formDTO.setAgentGender("M");
								}
								else
								{
									formDTO.setAgentGender("F");
								}
							}
							if("en".equalsIgnoreCase(lang))
							{
								if("Male".equalsIgnoreCase(formDTO.getGender()))
								{
									formDTO.setGender("M");
								}
								else
								{
									formDTO.setGender("F");
								}
							}
							else
							{
								if("पुस्र्ष".equalsIgnoreCase(formDTO.getGender()))
								{
									formDTO.setGender("M");
								}
								else
								{
									formDTO.setGender("F");
								}
							}
							ArrayList id = new ArrayList();
							id=bdd.getTypeName(formDTO.getIdProof());
							formDTO.setIdProof(bdd.getTypeId(formDTO.getIdProof(), lang));
							formDTO.setAgentIdProof(bdd.getTypeId(formDTO.getAgentIdProof(), lang));
							
						} else {
							/*ArrayList listDistrict2 = new ArrayList();
							ArrayList listState1 = bd.getState(formDTO
									.getCountry(),lang);
							eForm.setWillState(listState1);
							ArrayList listDistrict1 = bd.getDistrict(formDTO
									.getState(),lang);
							eForm.setWillDistrict(listDistrict2);

							ArrayList listState2 = bd.getState(formDTO
									.getAgentCountry(),lang);
							eForm.setWillAgentState(listState2);
							listDistrict2 = bd.getDistrict(formDTO
									.getAgentState(),lang);
							eForm.setWillAgentDistrict(listDistrict1);*/
							if("en".equalsIgnoreCase(lang))
							{
								if("Male".equalsIgnoreCase(formDTO.getAgentGender()))
								{
									formDTO.setAgentGender("M");
								}
								else
								{
									formDTO.setAgentGender("F");
								}
							}
							else
							{
								if("पुस्र्ष".equalsIgnoreCase(formDTO.getAgentGender()))
								{
									formDTO.setAgentGender("M");
								}
								else
								{
									formDTO.setAgentGender("F");
								}
							}
							formDTO.setIdProof(bdd.getTypeId(formDTO.getIdProof(), lang));
							formDTO.setAgentIdProof(bdd.getTypeId(formDTO.getAgentIdProof(), lang));
					
						}

						forwardJsp = CommonConstant.WILL_DEPOSITION_FORWARD_PAGE;
					}

					if ("proceedPaymentPage".equals(formDTO.getActionName())) {

						logger
								.debug("inside     WillDepositAction::  proceedPayment    ");
						WillDepositDTO willDTO = new WillDepositDTO();
						WillDepositDTO willFromDTO = eForm.getWillDTO();
						logger.debug("total dfeels:"
								+ Double.parseDouble(formDTO.getTotalFee()));
						if (funId == null) {
							funId = "FUN_013";
						}

						String getStatus = "";
						getStatus = (String) session.getAttribute("status");
						String willInserted = "";
						String willId = formDTO.getWillId();
						formDTO.setPayWillId(willId);
						String pending = (String) request
								.getParameter("pending");
						willId = bd.updateWillWithdrawDetails1(userId, willId,
								total, funId);
						/*
						 * if(pending==null){ willId =
						 * bd.insertWillDetails(getStatus,
						 * userId,formDTO.getCity(),
						 * formDTO.getDepositRemarks(), funId, total, formDTO);
						 * } else{ willId=willDTO.getWillId();
						 * System.out.println(willId); }
						 */
						/*
						 * if(willId!=null){
						 * 
						 * //willInserted = bd.insertDepositionDetails(formDTO,
						 * willId); formDTO.setWillId(willId);
						 * 
						 * formDTO.setTotalFee(Float.toString(total)); }
						 */

						// to do change here int
						// amount=bd.amountLeft(formDTO.getWithWillId());
						if (Double.parseDouble(formDTO.getTotalFee()) < 1) {
							request.setAttribute("depWillID", formDTO
									.getWillId());
							forwardJsp = "paymentSuccess";
							logger
									.debug("WillWithdrawalAction:: GOING TO :-       "
											+ forwardJsp);
						} else {
							// nEED TO CHECK THE METHOD....
							// willFromDTO=bd.getFunctionName(funId);
							WillWithdrawalBD withdrawalBD = new WillWithdrawalBD();
							ArrayList alist = withdrawalBD.getDetails(officeId);
							ArrayList rowList = (ArrayList) alist.get(0);
							String officeName = (String) rowList.get(1);
							String districtId = (String) rowList.get(2);

							String districtName = (String) rowList.get(3);
							String willRefId = formDTO.getPayWillId();

							request.setAttribute("forwardPath",
									"./depPayment.do?TRFS=NGI");
							request.setAttribute("parentTable",
									"IGRS_WILL_TXN_PAYMENT_DTLS");
							request.setAttribute("parentAmount", String
									.valueOf(formDTO.getTotalFee()));
							request.setAttribute("parentFunId", funId);
							request.setAttribute("parentKey", willId);
							request.setAttribute("parentModName",
									"WILL DEPOSIT");
							request.setAttribute("parentFunName",
									"WILL DEPOSITION");
							request.setAttribute("parentColumnName",
									"WILL_PAYMENT_ID");
							session.setAttribute("formDTO", formDTO);
							request.setAttribute("willId", willId);
							request.setAttribute("parentOfficeId", officeId);
							request
									.setAttribute("parentOfficeName",
											officeName);
							request
									.setAttribute("parentDistrictId",
											districtId);
							request.setAttribute("parentDistrictName",
									districtName);
							request
									.setAttribute("parentReferenceId",
											willRefId);
							request.setAttribute("formName","willdeposit");

							forwardJsp = CommonConstant.PROCEED_PAYMENT_PAGE;
							logger
									.debug("inside     WillWithdrawalAction::  proceedPayment    "
											+ forwardJsp);
						}
					}
				}
			}
		}
		return mapping.findForward(forwardJsp);
	}

	private String getFileExtension(String fileName) {
		try {
			int pos = fileName.lastIndexOf(".");
			String strExt = fileName.substring(pos + 1, fileName.length());
			return strExt;
		} catch (Exception e) {
		}
		return "";
	}

}
