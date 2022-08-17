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
 * FILE NAME: WillRetrievalAction.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 28th DEC 2008 
 * MODIFIED ON:    09th MAY 2008 
 * MODIFIED ON:	   None
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
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.util.GUIDGenerator;
import com.wipro.igrs.util.PropertiesFileReader;
import com.wipro.igrs.willdeposit.bd.WillCommonBD;
import com.wipro.igrs.willdeposit.bd.WillDepositBD;
import com.wipro.igrs.willdeposit.bd.WillRetrievalBD;
import com.wipro.igrs.willdeposit.bd.WillUpdateBD;
import com.wipro.igrs.willdeposit.bd.WillViewBD;
import com.wipro.igrs.willdeposit.bd.WillWithdrawalBD;
import com.wipro.igrs.willdeposit.constant.CommonConstant;
import com.wipro.igrs.willdeposit.dto.WillDepositDTO;
import com.wipro.igrs.willdeposit.form.WillDepositForm;

/**
 * @author NIHAR M.
 * 
 */
public class WillRetrievalAction extends BaseAction {

	String forwardJsp = new String("retrieveWill");
	private Logger logger = (Logger) Logger
			.getLogger(WillRetrievalAction.class);

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
		System.out.println("In will retrieval Action ");
		String lang=(String)session.getAttribute("languageLocale");
		String page = request.getParameter("page");
		String roleId = (String) session.getAttribute("roleId");
		String funId = (String) session.getAttribute("functionId");
		String userId = (String) session.getAttribute("UserId");
		String officeId = (String) session.getAttribute("loggedToOffice");
		WillDepositForm eForm = (WillDepositForm) form;
		WillDepositDTO formDTO = eForm.getWillDTO();
		String countryId = request.getParameter("countryId");
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		String uplPath=pr.getValue("igrs_upload_path")+"/14/03/";
		String stateId = request.getParameter("stateId");
		if(countryId==null||"".equalsIgnoreCase(countryId)){
			  countryId=formDTO.getRetrieverCountry();
		  }
		  if(stateId==null||"".equalsIgnoreCase(stateId)){
			  stateId=formDTO.getRetrieverState();
		  }
		WillUpdateBD willUpdateBD = null;
		WillWithdrawalBD withdrawalBD = null;
		ArrayList willRemarks = null;
		String newWillId = "";
		WillDepositBD bd = new WillDepositBD();
		ArrayList listCountry = bd.getCountry(lang);
		ArrayList listState = bd.getState(countryId,lang);
		ArrayList listDistrict = bd.getDistrict(stateId,lang);
		ArrayList listID = bd.getIDProof(CommonConstant.IGRS_ID_TYPE, lang);
		eForm.setWillCountry(listCountry);
		eForm.setWillState(listState);
		eForm.setWillDistrict(listDistrict);
		eForm.setWillId(listID);
		String StrFunId = funId;
		
		WillDepositDTO dto = eForm.getWillDTO();
		String fwdName = request.getParameter("fwdName");
		WillRetrievalBD retBD = new WillRetrievalBD();
		String pgTitle = request.getParameter("pageTitle");
		String nextAction = request.getParameter("paymentStatus");
		request.setAttribute("PGTYP", "willRetUpdate");
		String pgTyp = "";
		if (request.getParameter("pgTyp") != null) {
			pgTyp = request.getParameter("pgTyp");
			request.setAttribute("PGTYP", pgTyp);
		}
		String userType = "";
		userType = (String) session.getAttribute("loggedInRole");
		String feeVal = bd.getWillFeeValue(StrFunId);
		if (feeVal == null)
			feeVal = "0.0";
		logger.debug("Fee Val: " + feeVal);
		dto.setFee(feeVal);

		String otherFeeVal = "0.0";
		if (otherFeeVal == null)
			otherFeeVal = "0.0";
		logger.debug("otherFeeVal: " + otherFeeVal);
		dto.setOtherFee(otherFeeVal);
		float total = 0;
		total = Float.parseFloat(dto.getFee())
				+ Float.parseFloat(dto.getOtherFee());
		logger.debug("total: " + total);
		dto.setTotalFee(Float.toString(total));
		request.setAttribute("fee", feeVal);
		request.setAttribute("otherFee", otherFeeVal);
		request.setAttribute("totalFee", String.valueOf(total));
		
		// ANUJ

		try {
			WillRetrievalBD retrievalBD = new WillRetrievalBD();
			ArrayList PendingWithdrawList = retrievalBD
					.pendingWithPayment(officeId, total);
			eForm.getObjDashboard().setPendingWithdrawList(PendingWithdrawList);
			request.setAttribute("PendingWithdrawList", PendingWithdrawList);
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage(), e1);
		}

		// view pending will details
		if ("willWithdrawPending".equalsIgnoreCase(request
				.getParameter("willStatus"))) {
			String s = (String) request.getParameter("willStatus");
			String will = (String) request.getParameter("will");

			String willStat[] = will.split("~");
			if (willStat.length == 2) {
				String will_txn_id = (String) willStat[0];
				String amount = (String) willStat[1];
				request.setAttribute("newWillId", will_txn_id);
				if ("null".equalsIgnoreCase(amount)) {
					amount = "0";
				}
				double d = Double.parseDouble(amount);
				int amt=(int)d;

				if (amt <= 0) {
					dto.setWillId(will_txn_id);
					String stat = bd.checkStatus(will_txn_id, funId);
					forwardJsp = stat;
					if ("pending".equalsIgnoreCase(stat)) {
						WillDepositDTO dto1 = eForm.getWillDTO();

						// a if 100% payment is done
						WillViewBD viewBD = new WillViewBD();
						dto1 = viewBD.getwillIdDetails(will_txn_id, StrFunId,lang);// withdrawalBD.getWillDeposit(willid1,StrFunId);
						willRemarks = bd.getWillRemarks(will_txn_id);
						dto1.setWillRemarksList(willRemarks);
						request.setAttribute("PAGETYPE", "willWithUpdate");
						dto1.setTotalFee(String.valueOf(total));
						eForm.setWillDTO(dto1);
						request.setAttribute("eForm", eForm);
						forwardJsp = "retrieveIdDetailsReadOnlyPending";
					}
				} else {

					WillDepositDTO dto1 = eForm.getWillDTO();

					// a if 100% payment is done
					WillViewBD viewBD = new WillViewBD();
					//
					WillDepositBD willBD = new WillDepositBD();

					WillDepositDTO willDto = eForm.getWillDTO();
					willDto = viewBD.getwillIdDetails(will_txn_id.trim(),
							StrFunId,lang);
					willRemarks = willBD.getWillRemarks(will_txn_id.trim());
					willDto.setWillRemarksList(willRemarks);
					eForm.setWillDTO(willDto);
					request.setAttribute("eForm", eForm);
					//
					dto1 = viewBD.getwillIdDetails(will_txn_id, StrFunId,lang);// withdrawalBD.getWillDeposit(willid1,StrFunId);
					willRemarks = bd.getWillRemarks(will_txn_id);
					dto1.setTotalFee(String.valueOf(total));
					dto1.setWillRemarksList(willRemarks);
					request.setAttribute("PAGETYPE", "willWithUpdate");
					eForm.setWillDTO(dto1);
					
					forwardJsp = "retrieveIdDetailsReadOnlyPending";
				}// a

			}
		}
		// ANUJ

		String actionName = formDTO.getActionName();
		ArrayList errorList = new ArrayList();

		if ("P".equalsIgnoreCase(nextAction)) {
			// WillDepositDTO frmDTO = eForm.getWillDTO();
			String k = formDTO.getPayWillId();
			WillRetrievalBD bd1 = new WillRetrievalBD();
			String will = bd1.getWillId(k);
			request.setAttribute("newWillId", will);
			request.setAttribute("depWillID", will);
			formDTO.setActionName("");
			request.setAttribute("PGTYP", "willRetUpdate");
			String stat = bd.checkStatus(will, funId);
			formDTO.setWillId(formDTO.getPayWillId().toString());
			forwardJsp = stat;
			formDTO.setWillIDDetailsRetrievalAction("");
		}
		if("anuj".equalsIgnoreCase(fwdName)){
			formDTO.setShow("show");
			if ("Citizen".equalsIgnoreCase(formDTO.getRetrieverType())) {
				ArrayList listState1 = bd
						.getState(formDTO.getRetrieverCountry(),lang);
				eForm.setWillState(listState1);
				ArrayList listDistrict1 = bd.getDistrict(formDTO
						.getRetrieverState(),lang);
				eForm.setWillDistrict(listDistrict1);
			} else {
				ArrayList listState1 = bd.getState(formDTO
						.getCourtCountry(),lang);
				eForm.setWillState(listState1);
				ArrayList listDistrict1 = bd.getDistrict(formDTO
						.getCourtState(),lang);
				eForm.setWillDistrict(listDistrict1);

			}
			forwardJsp = "viewIndividualIDDetails";
		}
		// Files Upload Start.....
		if ("photo".equalsIgnoreCase(fwdName)) {
			if (actionName == null || "".equalsIgnoreCase(actionName)) {
				formDTO.setRetDocumentName(null);
				// cDto.setCaveatTypeList(refDAO.createCaveatsList());
				// cDto.setCountryMasterList(refDAO.countryList());
			} else if ("setUploadFile".equalsIgnoreCase(actionName)) {
				try {
					FormFile uploadedFile = formDTO.getRetFilePhoto();
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
					// anuj
					String photo = "PHOTO." + fileExt;
					formDTO.setDocName(photo);
					// anuj
					if ("Citizen".equalsIgnoreCase(formDTO.getRetrieverType())) {
						ArrayList listState1 = bd
								.getState(formDTO.getCountry(),lang);
						eForm.setWillState(listState1);
						ArrayList listDistrict1 = bd.getDistrict(formDTO
								.getRetrieverState(),lang);
						eForm.setWillDistrict(listDistrict1);
					} else {
						ArrayList listState1 = bd.getState(formDTO
								.getCourtCountry(),lang);
						eForm.setWillState(listState1);
						ArrayList listDistrict1 = bd.getDistrict(formDTO
								.getCourtState(),lang);
						eForm.setWillDistrict(listDistrict1);

					}

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
							formDTO.setRetDocumentName(uploadedFile
									.getFileName());
							formDTO.setRetDocContents(uploadedFile
									.getFileData());
							formDTO.setRetPhotoSize(photoSize);
							formDTO.setRetPhotoCheck("phloded");
						}
					}
				} catch (Exception e) {
					errorList.add("Unable to upload file. Please try again.");
					request.setAttribute("errorsList", errorList);
				}
			}

			forwardJsp = "viewIndividualIDDetails";
		} // end photo upload

		// Start photo upload
		if ("thumb".equalsIgnoreCase(fwdName)) {/*
			if (actionName == null || "".equalsIgnoreCase(actionName)) {
				formDTO.setThunmbName(null);
				// cDto.setCaveatTypeList(refDAO.createCaveatsList());
				// cDto.setCountryMasterList(refDAO.countryList());
			} else if ("setUploadFile".equalsIgnoreCase(actionName)) {
				try {
					FormFile uploadedFile = formDTO.getRetFileThumb();
					if ("".equals(uploadedFile.getFileName())) {
						errorList
								.add("Invalid file selection. Please try again.");
					}
					// formDTO.setThunmbName(null);
					// formDTO.setThumbContents(null);
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
					String thumbSize = "(" + fileSize + "MB)";
					// anuj
					String photo = "THUMB_IMPRESSION." + fileExt;
					formDTO.setThumb(photo);
					// anuj
					if ("Citizen".equalsIgnoreCase(formDTO.getRetrieverType())) {
						ArrayList listState1 = bd
								.getState(formDTO.getCountry());
						eForm.setWillState(listState1);
						ArrayList listDistrict1 = bd.getDistrict(formDTO
								.getRetrieverState());
						eForm.setWillDistrict(listDistrict1);
					} else {
						ArrayList listState1 = bd.getState(formDTO
								.getCourtCountry());
						eForm.setWillState(listState1);
						ArrayList listDistrict1 = bd.getDistrict(formDTO
								.getCourtState());
						eForm.setWillDistrict(listDistrict1);

					}

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
							formDTO
									.setRetThunmbName(uploadedFile
											.getFileName());
							formDTO.setRetThumbContents(uploadedFile
									.getFileData());
							formDTO.setRetThumbSize(thumbSize);
							formDTO.setRetThumbCheck("thloded");
						}
					}
				} catch (Exception e) {
					errorList.add("Unable to upload file. Please try again.");
					request.setAttribute("errorsList", errorList);
				}
			}*/
			
String type = request.getParameter("type");
if ("Court".equalsIgnoreCase(formDTO.getRetrieverType())) {
ArrayList listState1 = bd.getState(formDTO
		.getCourtCountry(),lang);
eForm.setWillState(listState1);
ArrayList listDistrict1 = bd.getDistrict(formDTO
		.getCourtState(),lang);
eForm.setWillDistrict(listDistrict1);
}
			if ("photo".equalsIgnoreCase(type)) {
				boolean rt = bd.uploadFileCheck(uplPath
						+ formDTO.getUniqueId()+"/PHOTO.GIF");

				
					formDTO.setCompPhotoPath(uplPath
							+ formDTO.getUniqueId()+"/PHOTO.GIF");
					formDTO.setPhotoCheck("photo uploaded successfully");
					formDTO.setRetPhotoCheck("photo uploaded successfully");
				

			} else if ("thumb".equalsIgnoreCase(type)) {
				boolean rt = bd.uploadFileCheck(uplPath
						+ formDTO.getUniqueId()+"/THUMB.GIF");
				

				formDTO.setCompThumbPath(uplPath
						+ formDTO.getUniqueId()+"/THUMB.GIF");
					formDTO.setThumbCheck("sdasa");
					formDTO.setRetThumbCheck("thumb impression uploaded successfully");
					// FIxed for Will Retrieval Thumb capture issue - 30 MAY 2017 Rahul 
					//formDTO.setRetThunmbName("THUMB.GIF");
					formDTO.setThunmbName("THUMB.GIF");
				
			}
			else if("certi".equalsIgnoreCase(type)){
				boolean rt = bd.uploadFileCheck(uplPath
						+ formDTO.getUniqueId()+"/CERTIFICATE.PDF");
				
					formDTO.setCompCertiPath(uplPath
						+ formDTO.getUniqueId()+"/CERTIFICATE.PDF");
					//formDTO.setAgentProofName("CERTIFICATE.PDF");
					formDTO.setDeathCertCheck("Signature uploaded successfully");
				
			}
			else if("scan".equalsIgnoreCase(type)){

				boolean scanCheck = bd.uploadFileCheck(uplPath
					+ formDTO.getUniqueId()+"/COPYOFWILL.PDF");
				formDTO.setRetSignCheck("failed to upload");
					formDTO.setScanCheck("checked");
					formDTO.setCompScanPath(uplPath
					+ formDTO.getUniqueId()+"/COPYOFWILL.PDF");
			
			}
			else{
				boolean rt = bd.uploadFileCheck(uplPath
						+ formDTO.getUniqueId()+"/SIGNATURE.GIF");
				formDTO.setCompSignPath(uplPath
						+ formDTO.getUniqueId()+"/SIGNATURE.GIF");
					formDTO.setRetSignatureName("SIGNATURE.GIF");
					formDTO.setRetSignCheck("Signature uploaded successfully");
				
			}

			request.setAttribute("PGTYP", "willRetrieve");
			forwardJsp = "viewIndividualIDDetails";
		} // end photo upload

		// Start photo upload
		if ("signature".equalsIgnoreCase(fwdName)) {
			
			 try {
				String filename = request.getParameter("path").toString();
					
				   // set the http content type to "APPLICATION/OCTET-STREAM
				   response.setContentType("application/download");

				   // initialize the http content-disposition header to
				   // indicate a file attachment with the default filename
				   // "myFile.txt"
				  // String fileName = (String)formDTO.getCompThumbPath();
				   //Filename=\"myFile.txt\"";
				   response.setHeader("Content-Disposition", "attachment; filename="
							+ URLEncoder.encode(filename,"UTF-8"));
				   
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
					forwardJsp = "viewIndividualIDDetails";

		}

		if ("certificate".equalsIgnoreCase(fwdName)) {
			if (actionName == null || "".equalsIgnoreCase(actionName)) {
				formDTO.setCertificateName(null);
				// cDto.setCaveatTypeList(refDAO.createCaveatsList());
				// cDto.setCountryMasterList(refDAO.countryList());
			} else if ("setUploadFile".equalsIgnoreCase(actionName)) {
				try {
					FormFile uploadedFile = formDTO.getFileDeathCert();
					if ("".equals(uploadedFile.getFileName())) {
						errorList
								.add("Invalid file selection. Please try again.");
					}
					// formDTO.setCertificateName(null);
					// formDTO.setDeathCertDocContents(null);
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
					String certifctSize = "(" + fileSize + "MB)";
					// anuj
					String photo = "CERTIFICATE." + fileExt;
					formDTO.setDeathCerti(photo);
					// anuj
					if ("Citizen".equalsIgnoreCase(formDTO.getRetrieverType())) {
						ArrayList listState1 = bd
								.getState(formDTO.getCountry(),lang);
						eForm.setWillState(listState1);
						ArrayList listDistrict1 = bd.getDistrict(formDTO
								.getRetrieverState(),lang);
						eForm.setWillDistrict(listDistrict1);
					} else {
						ArrayList listState1 = bd.getState(formDTO
								.getCourtCountry(),lang);
						eForm.setWillState(listState1);
						ArrayList listDistrict1 = bd.getDistrict(formDTO
								.getCourtState(),lang);
						eForm.setWillDistrict(listDistrict1);

					}

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
							formDTO.setCertificateName(uploadedFile
									.getFileName());
							formDTO.setDeathCertDocContents(uploadedFile
									.getFileData());
							formDTO.setDeathCertSize(certifctSize);
							formDTO.setDeathCertCheck("dthloded");
						}
					}
				} catch (Exception e) {
					errorList.add("Unable to upload file. Please try again.");
					request.setAttribute("errorsList", errorList);
				}
			}

			forwardJsp = "viewIndividualIDDetails";
		}

		// Files Upload End...
		if (page != null) {
			if ("retrieveWill".equals(page)) {
				WillDepositDTO willDepositDTO = new WillDepositDTO();
				eForm.setWillDTO(willDepositDTO);
				forwardJsp = CommonConstant.RETRIEVE_WILL;
				logger.debug("DEPOSITE ACTION:: FORWARDED PAGE :-     "
						+ forwardJsp);
			} else if (page.equalsIgnoreCase("insertWillRetrive")) {

				willUpdateBD = new WillUpdateBD();
				withdrawalBD = new WillWithdrawalBD();
				String status = (String) session.getAttribute("status");

				if (status != null && !status.equalsIgnoreCase("fail")) {
					
				}
				forwardJsp = CommonConstant.TXN_SUCCESSFUL;
			}
		} else {
			if (form != null) {

				WillCommonBD willComBD = new WillCommonBD();
				// HttpSession sesion = request.getSession(true);
				WillViewBD viewBD = new WillViewBD();

				String formName = eForm.getWillDTO().getWillRetrievalForm();
				WillDepositBD willBD = new WillDepositBD();

				if (CommonConstant.WILL_RETRIEVAL_FORM
						.equalsIgnoreCase(formName)) {

					if ("willRetrieve".equals(formDTO.getActionName())) {
						String refID = formDTO.getWillId().trim();
						String fromDate = formDTO.getFromDepositeDate();
						String toDate = formDTO.getToDepositeDate();
						ArrayList willViewDetails = willComBD.getWillViewDetails(
								refID, fromDate, toDate, "Deposited", officeId);
						// ArrayList willViewDetails =
						// retBD.getWillRetrievalDetails(
						// refID, fromDate, toDate);
						eForm.setWillViewsList(willViewDetails);
						forwardJsp = CommonConstant.VIEW_SUCCESS;
						logger.debug("WillRetrievalAction:: GOING TO:-       "
								+ forwardJsp);
					}
					if ("willUpdateRetrieve".equals(formDTO.getActionName())) {
						String refID = formDTO.getWillId().trim();
						String fromDate = formDTO.getFromDepositeDate();
						String toDate = formDTO.getToDepositeDate();
						ArrayList willViewDetails = viewBD.getWillViewDetails(
								refID, fromDate, toDate, "Pending Retrieve",
								officeId, lang);
						// ArrayList willViewDetails =
						// retBD.getWillRetrievalDetails(
						// refID, fromDate, toDate);
						eForm.setWillViewsList(willViewDetails);
						forwardJsp = CommonConstant.VIEW_SUCCESS;
						logger.debug("WillRetrievalAction:: GOING TO:-       "
								+ forwardJsp);
					}
				}

				if ("willRetrievalID".equalsIgnoreCase(formDTO
						.getWillIDDetailsRetrievalAction())) {

					String willid1 = request.getParameter("refId");
					logger.debug("refID:-  " + willid1);

					if (willid1 != null) {

						dto = viewBD.getwillIdDetails(willid1, StrFunId,lang);// withdrawalBD.getWillDeposit(willid1,StrFunId);
						willRemarks = bd.getWillRemarks(willid1);
						dto.setWillRemarksList(willRemarks);
						String uni = (String) request.getParameter("uni");
						System.out.println("ANUJ " + dto.getUniqueId());
						if (uni == null) {

							String str = GUIDGenerator.getGUID();
							dto.setUniqueId(str);
							dto.setDsignPath(uplPath);
							dto.setSignature("SIGNATURE.GIF");
							dto.setForwardPath("/WillRetrieval.do?fwdName=thumb");
							dto.setForwardName("/WillRetrieval");
							dto.setDthumbPath(uplPath);
							dto.setThumb("THUMB.GIF");
							dto.setDphotoPath(uplPath);
							dto.setDocName("PHOTO.GIF");
							dto.setCertificateName("CERTIFICATE.PDF");
							dto.setDproofPath(uplPath);
							dto.setScanName("COPYOFWILL.PDF");
						}

						eForm.setWillDTO(dto);
					}

					/*
					 * dto = retBD.getwillIdDetails(willid1,StrFunId);
					 * 
					 * String feeVal = willComBD.getWillFeeValue(StrFunId);
					 * logger.debug("WillRetrievalAction:: feeVal:- " + feeVal +
					 * ":Check:-" + dto.getFee()); dto.setFee(feeVal); String
					 * otherFeeVal = willComBD.getWillOtherFeeValue(StrFunId);
					 * logger.debug("WillRetrievalAction:: otherFeeVal:- " +
					 * otherFeeVal); dto.setOtherFee(otherFeeVal); float total =
					 * 0;
					 *//*
						 * total = Float.parseFloat(dto.getFee()) +
						 * Float.parseFloat(dto.getOtherFee());
						 * logger.debug("WillRetrievalAction:: Total:- " +
						 * total); dto.setTotalFee(Float.toString(total));
						 */eForm.setWillDTO(dto);
					request.setAttribute("eForm", eForm);

					forwardJsp = CommonConstant.VIEW_IND_ID_DETAILS;
					logger.debug(forwardJsp);
				}
				// ANUJ on submit - has to insert query to insert al d data into
				// d database
				if ("willRetrievalSubmit".equalsIgnoreCase(formDTO
						.getWillIDDetailsRetrievalAction())) {

					// new start
					WillDepositDTO willDTO = new WillDepositDTO();
					if (funId == null) {
						funId = "FUN_015";
					}
					willDTO = bd.getFunctionName(funId);
					WillUpdateBD willUpdateBD1 = new WillUpdateBD();
					WillWithdrawalBD withdrawalBD1 = new WillWithdrawalBD();
					WillRetrievalBD bd1 = new WillRetrievalBD();
					WillDepositDTO willRetDto = eForm.getWillDTO();
					String status = (String) session.getAttribute("status");
					logger
							.debug("Testator doc:"
									+ willRetDto.getDocumentName());
					logger.debug("Testator dthump:"
							+ willRetDto.getThunmbName());
					logger.debug("Testator doc:"
							+ willRetDto.getSignatureName());
					logger.debug("Testator doc:" + willRetDto.getDocContents());
					logger.debug("Testator dthump:"
							+ willRetDto.getThumbContents());
					logger.debug("Testator doc:"
							+ willRetDto.getSignatureContents());
					String pending = (String) request.getParameter("pending");
					try {
						newWillId = withdrawalBD1.updateWillRetrivalDetails(
								willRetDto, userId, formDTO.getWillId(),
								status, formDTO.getRetrievalReason(), total,
								funId, officeId, lang);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (newWillId != null && newWillId.length() > 0) {
						willUpdateBD1.updateDeliveryDetails(formDTO
								.getRetrievalReason(), newWillId, userId);
						WillDepositDTO willDepDTO = eForm.getWillDTO();
						logger.debug("Retriever doc:"
								+ willRetDto.getDocumentName());
						logger.debug("Retriever dthump:"
								+ willRetDto.getThunmbName());
						logger.debug("Retriever doc:"
								+ willRetDto.getSignatureName());
						logger.debug("Retriever doc:"
								+ willRetDto.getDocContents());
						logger.debug("Retriever dthump:"
								+ willRetDto.getThumbContents());
						logger.debug("Retriever doc:"
								+ willRetDto.getSignatureContents());
						boolean citizenUpdated = retBD.updateCitizenDetails(
								willDepDTO, newWillId, lang);

						if (citizenUpdated) {
							logger
									.debug("................Citizen details got updated....................");
						}
					}
					formDTO.setRetWillId(newWillId);

					// new end

					formDTO.setRetrievalDueDate(formDTO.getRetrievalDueDate());
					pgTitle = "";
					forwardJsp = CommonConstant.WILL_RETRIEVER_CONFIRM;
					logger.debug("Forwarded to:-      " + forwardJsp);
				}
				if ("updtRetStatus".equals(formDTO.getActionName())) {
					pgTitle = "";
					String willId = "";
					willId = dto.getWillId();
					willUpdateBD = new WillUpdateBD();
					willUpdateBD.updateRetrieveStatus(willId);
					forwardJsp = CommonConstant.TXN_SUCCESSFUL;
				}
				// ANUJ when clickig update an update query has to be inserted
				// here to update the details
				if ("goEDIT".equalsIgnoreCase(formDTO
						.getWillIDDetailsRetrievalAction())) {

					if ("Citizen".equalsIgnoreCase(formDTO.getRetrieverType())) {

						ArrayList listState1 = bd
								.getState(formDTO.getRetrieverCountry(),lang);
						eForm.setWillState(listState1);
						ArrayList listDistrict1 = bd.getDistrict(formDTO
								.getRetrieverState(),lang);
						eForm.setWillDistrict(listDistrict1);
					} else {
						ArrayList listState1 = bd.getState(formDTO
								.getCourtCountry(),lang);
						eForm.setWillState(listState1);
						ArrayList listDistrict1 = bd.getDistrict(formDTO
								.getCourtState(),lang);
						eForm.setWillDistrict(listDistrict1);

					}

					forwardJsp = CommonConstant.VIEW_IND_ID_DETAILS;
					logger.debug("Forwarded to:-   " + forwardJsp);
				}
				// ANUJ on click of proceed to payment button
				if ("makePayment".equalsIgnoreCase(formDTO
						.getWillIDDetailsRetrievalAction())) {
					WillDepositDTO willDTO = new WillDepositDTO();
					if (funId == null) {
						funId = "FUN_015";
					}
					System.out.println(funId);
					//willDTO = bd.getFunctionName(funId);
					willUpdateBD = new WillUpdateBD();
					withdrawalBD = new WillWithdrawalBD();
					WillRetrievalBD bd1 = new WillRetrievalBD();
					WillDepositDTO willRetDto = eForm.getWillDTO();
					String status = (String) session.getAttribute("status");
					logger
							.debug("Testator doc:"
									+ willRetDto.getDocumentName());
					logger.debug("Testator dthump:"
							+ willRetDto.getThunmbName());
					logger.debug("Testator doc:"
							+ willRetDto.getSignatureName());
					logger.debug("Testator doc:" + willRetDto.getDocContents());
					logger.debug("Testator dthump:"
							+ willRetDto.getThumbContents());
					logger.debug("Testator doc:"
							+ willRetDto.getSignatureContents());
					String pending = (String) request.getParameter("pending");
					String willId = "";
					if (formDTO.getRetWillId() != null) {

						willId = formDTO.getRetWillId();

					} else {

						willId = formDTO.getWillId();

					}
					formDTO.setPayWillId(willId);
					newWillId = bd1.insertWillDetails(willId, StrFunId, userId,
							total);
					/*
					 * newWillId =
					 * withdrawalBD.updateWillRetrivalDetails(willRetDto,
					 * userId, formDTO.getWillId(), status, formDTO
					 * .getRetrievalReason(), total, funId); if (newWillId !=
					 * null && newWillId.length() > 0) {
					 * willUpdateBD.updateDeliveryDetails(formDTO
					 * .getRetrievalReason(), newWillId, userId); WillDepositDTO
					 * willDepDTO = eForm.getWillDTO();
					 * logger.debug("Retriever doc:" +
					 * willRetDto.getDocumentName());
					 * logger.debug("Retriever dthump:" +
					 * willRetDto.getThunmbName());
					 * logger.debug("Retriever doc:" +
					 * willRetDto.getSignatureName());
					 * logger.debug("Retriever doc:" +
					 * willRetDto.getDocContents());
					 * logger.debug("Retriever dthump:" +
					 * willRetDto.getThumbContents());
					 * logger.debug("Retriever doc:" +
					 * willRetDto.getSignatureContents()); boolean
					 * citizenUpdated = retBD
					 * .updateCitizenDetails(willDepDTO,newWillId);
					 * 
					 * if (citizenUpdated) { logger.debug(
					 * "................Citizen details got updated...................."
					 * ); } } formDTO.setRetWillId(newWillId);
					 */

					if (Double.parseDouble(formDTO.getTotalFee()) < 1) {
						request.setAttribute("newWillId", formDTO
								.getRetWillId());
						forwardJsp = "txnSuccessful";
						logger
								.debug("WillWithdrawalAction:: GOING TO :-       "
										+ forwardJsp);
					} else {
						
						// WillWithdrawalBD withdrawalBD = new
						// WillWithdrawalBD();
						ArrayList alist = withdrawalBD.getDetails(officeId);
						ArrayList rowList = (ArrayList) alist.get(0);
						String officeName = (String) rowList.get(1);
						String districtId = (String) rowList.get(2);
						
						String districtName = (String) rowList.get(3);
						String willRefId = formDTO.getPayWillId();
						int amount = bd.amountLeft(willRefId, String.valueOf(total));
						request.setAttribute("forwardPath",
								"./WillRetrieval.do?TRFS=NGI");
						request.setAttribute("parentTable",
								"IGRS_WILL_TXN_PAYMENT_DTLS");
						request.setAttribute("parentAmount", String.valueOf(amount));
						request.setAttribute("parentFunId", funId);
						request.setAttribute("parentKey", newWillId);
						request.setAttribute("parentModName", "WILL DEPOSIT");
						request.setAttribute("parentFunName", "WILL RETRIEVAL");
						request.setAttribute("parentColumnName",
								"WILL_PAYMENT_ID");
						session.setAttribute("formDTO", formDTO);
						request.setAttribute("parentOfficeId", officeId);
						request.setAttribute("parentOfficeName", officeName);
						request.setAttribute("parentDistrictId", districtId);
						request
								.setAttribute("parentDistrictName",
										districtName);
						request.setAttribute("parentReferenceId", willRefId);
						request.setAttribute("formName","willdeposit");


						forwardJsp = CommonConstant.PROCEED_PAYMENT_PAGE;
						pgTitle = "";
						logger.debug("WillRetrievalAction:: GOING TO :-       "
								+ forwardJsp);
					}
				}

			}
			if ("downloadphoto".equalsIgnoreCase(pgTitle)) {
				// downloadDocument(response, dto.getDocContents(),
				// dto.getDocumentName());
				try {
					response.setContentType("application/download");

					String filename = (String) dto.getPhotoPath();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition",
							"attachment; filename="

									+ URLEncoder.encode(dto.getDocumentName()
											.toString(), "UTF-8"));

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

				forwardJsp = "viewIndividualIDDetails";
			}
			if ("downloadthumb".equalsIgnoreCase(pgTitle)) {
				// downloadDocument(response, dto.getThumbContents(),
				// dto.getThunmbName());

				try {
					response.setContentType("application/download");

					String filename = (String) dto.getThumbPath();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition",
							"attachment; filename="

									+ URLEncoder.encode(dto.getThunmbName()
											.toString(), "UTF-8"));

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

				forwardJsp = "viewIndividualIDDetails";
			}
			if ("downloadsignature".equalsIgnoreCase(pgTitle)) {
				// downloadDocument(response, dto.getSignatureContents(),
				// dto.getSignatureName());

				try {
					response.setContentType("application/download");

					String filename = (String) dto.getSignPath();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition",
							"attachment; filename="

									+ URLEncoder.encode(dto.getSignatureName()
											.toString(), "UTF-8"));

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

				forwardJsp = "viewIndividualIDDetails";
			}
			if ("downloadAgntPrf".equalsIgnoreCase(pgTitle)) {
				// downloadDocument(response, dto.getAgentProofContents(),
				// dto.getAgentProofName());

				try {
					response.setContentType("application/download");

					String filename = (String) dto.getAgentProofPath();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition",
							"attachment; filename="

									+ URLEncoder.encode(dto.getAgentProofName()
											.toString(), "UTF-8"));

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

				forwardJsp = "viewIndividualIDDetails";
			}

			if ("downloadphotoCit".equalsIgnoreCase(pgTitle)) {
				// downloadDocument(response, dto.getRetDocContents(),
				// dto.getRetDocumentName());

				try {
					response.setContentType("application/download");

					String filename = (String) dto.getRetPhotoPath();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition",
							"attachment; filename="

									+ URLEncoder.encode(dto.getRetDocumentName()
											.toString(), "UTF-8"));

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

				forwardJsp = "viewIndividualIDDetails";
			}
			if ("downloadthumbCit".equalsIgnoreCase(pgTitle)) {
				// downloadDocument(response, dto.getRetThumbContents(),
				// dto.getRetThunmbName());

				try {
					response.setContentType("application/download");

					String filename = (String) dto.getRetThumbPath();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition",
							"attachment; filename="

									+ URLEncoder.encode(dto.getThunmbName()
											.toString(), "UTF-8"));

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

				forwardJsp = "viewIndividualIDDetails";
			}
			if ("downloadsignatureCit".equalsIgnoreCase(pgTitle)) {
				// downloadDocument(response, dto.getRetSignatureContents(),
				// dto.getRetSignatureName());

				try {
					response.setContentType("application/download");

					String filename = (String) dto.getRetSignPath();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition",
							"attachment; filename="

									+ URLEncoder.encode(dto.getRetSignatureName()
											.toString(), "UTF-8"));

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

				forwardJsp = "viewIndividualIDDetails";
			}
			if ("downloadDeathCertCit".equalsIgnoreCase(pgTitle)) {
				// downloadDocument(response, dto.getDeathCertDocContents(),
				// dto.getCertificateName());

				try {
					response.setContentType("application/download");

					String filename = (String) dto.getRetDeathCertiPath();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition",
							"attachment; filename="

									+ URLEncoder.encode(dto.getCertificateName()
											.toString(), "UTF-8"));

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

				forwardJsp = "viewIndividualIDDetails";
			}
			
			//scan copy download start
			if ("downloadScanCopy".equalsIgnoreCase(pgTitle)) {
				// downloadDocument(response, dto.getDeathCertDocContents(),
				// dto.getCertificateName());

				try {
					response.setContentType("application/download");

					String filename = (String) dto.getCompScanPath();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition",
							"attachment; filename="

									+ URLEncoder.encode(dto.getScanName()
											.toString(), "UTF-8"));

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

				forwardJsp = "viewIndividualIDDetails";
			}
			
			
			
			//scan copy download end
			
			
			

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

	private void downloadDocument(HttpServletResponse res, byte[] docContents,
			String fileName) {

		try {

			OutputStream os = res.getOutputStream();
			long index = 1;
			String ext = null;
			if (fileName != null) {
				ext = fileName.substring(fileName.length() - 3, fileName
						.length());
				if (ext != null) {

					if (ext.equalsIgnoreCase("doc")) {
						res.setContentType("application/download");
					} else if (ext.equalsIgnoreCase("docx")) {
						res.setContentType("application/download");
					} else if (ext.equalsIgnoreCase("ppt")) {
						res.setContentType("application/download");
					} else if (ext.equalsIgnoreCase("xls")) {
						res.setContentType("application/download");
					} else if (ext.equalsIgnoreCase("htm")) {
						res.setContentType("text/html");
					} else if (ext.equalsIgnoreCase("tml")) {
						res.setContentType("text/html");
					} else if (ext.equalsIgnoreCase("txt")) {
						res.setContentType("text/plain");
					} else if (ext.equalsIgnoreCase("PDF")) {
						res.setContentType("application/PDF");
					} else if (ext.equalsIgnoreCase("bmp")) {
						res.setContentType("image/x-bmp");
					} else if (ext.equalsIgnoreCase("GIF")) {
						res.setContentType("image/GIF");
					} else if (ext.equalsIgnoreCase("jpg")) {
						res.setContentType("image/jpeg");
					} else if (ext.equalsIgnoreCase("peg")) {
						res.setContentType("image/jpeg");
					} else {
						res.setContentType("application/download");
					}
				}
			}

			res.setContentType("application/download");
			res.setHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			// res.setHeader("Content-Disposition", "attachment; filename="+
			// URLEncoder.encode(fileName));
			os.write(docContents);
			os.close();
			os.flush();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
}
