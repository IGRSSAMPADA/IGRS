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
 * FILE NAME: WillWithdrawalAction.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 28th DEC 2007 
 * MODIFIED ON:    12th APRIL 2008 
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
import com.wipro.igrs.common.WILLErrorConstants;
import com.wipro.igrs.util.GUIDGenerator;
import com.wipro.igrs.util.PropertiesFileReader;
import com.wipro.igrs.willdeposit.bd.WillCommonBD;
import com.wipro.igrs.willdeposit.bd.WillDepositBD;
import com.wipro.igrs.willdeposit.bd.WillRetrievalBD;
import com.wipro.igrs.willdeposit.bd.WillUpdateBD;
import com.wipro.igrs.willdeposit.bd.WillViewBD;
import com.wipro.igrs.willdeposit.bd.WillWithdrawalBD;
import com.wipro.igrs.willdeposit.constant.CommonConstant;
import com.wipro.igrs.willdeposit.dao.WillWithdrawalDAO;
import com.wipro.igrs.willdeposit.dto.WillDepositDTO;
import com.wipro.igrs.willdeposit.form.WillDepositForm;

/**
 * @author NIHAR M.
 *
 */
public class WillWithdrawalAction extends BaseAction {

	String forwardJsp = new String("withdrawWill");
	private Logger logger = (Logger) Logger.getLogger(WillWithdrawalAction.class);
	/** 
	 * Method execute for Will Deposition.
	 * @param mapping 
	 * @param form 
	 * @param request 
	 * @param response 
	 * @return ActionForward
	 * @throws Exception 
	 */
	/* (non-Java doc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) 
	throws Exception {
		String lang=(String)session.getAttribute("languageLocale");
		String nextAction = request.getParameter("paymentStatus"); //added on 18Feb13
		ActionMessages messages = new ActionMessages();
		String page=request.getParameter("page");
		String pageType="";
		String officeId = (String)session.getAttribute("loggedToOffice");
		String next=(String)request.getParameter("next");
		pageType=request.getParameter("pageType");
		if("next".equalsIgnoreCase(next)){
			pageType=request.getParameter("pageType");
			request.setAttribute("PAGETYPE", "willWithdrawal");
			request.setAttribute("pagetype", "willWithdrawal");
		}
		
		if(request.getParameter("pageType")!=null)
		{
		pageType=request.getParameter("pageType");
		request.setAttribute("PAGETYPE", pageType);
		}
		  String roleId = (String)session.getAttribute("roleId");
	      String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		  WillDepositForm eForm = (WillDepositForm) form;
		  WillWithdrawalBD withdrawalBD = new WillWithdrawalBD();
		  WillUpdateBD willUpdateBD = null;
		  ArrayList willRemarks  =null;
		  WillDepositBD bd = new WillDepositBD();
		  String newWillId="";
		  WillDepositDTO dto1 = eForm.getWillDTO();
		  String StrFunId = funId;
		  String pgTitle=request.getParameter("pageTitle");
		  WillDepositDTO dto = eForm.getWillDTO();
		  
		  ArrayList listID = bd.getIDProof(CommonConstant.IGRS_ID_TYPE, lang);
		  String countryId = request.getParameter("countryId");
		  if(countryId==null||"".equalsIgnoreCase(countryId)){
			  countryId=dto.getRetrieverCountry();
		  }
		  String stateId = request.getParameter("stateId");
		  if(stateId==null||"".equalsIgnoreCase(stateId)){
			  stateId=dto.getRetrieverState();
		  }
		  ArrayList listCountry = bd.getCountry(lang);
		  ArrayList listState = bd.getState(countryId,lang);
		  ArrayList listDistrict = bd.getDistrict(stateId,lang);
		  eForm.setWillCountry(listCountry);
			eForm.setWillState(listState);
			eForm.setWillDistrict(listDistrict);
			eForm.setWillId(listID);
			
			String userType="";
			userType = (String)session.getAttribute("loggedInRole");
			
			
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

			String uplPath=pr.getValue("igrs_upload_path")+"/14/02/";//D:/UPLOAD/14/02/
			
		  String feeVal=bd.getWillFeeValue(StrFunId);
			if(feeVal == null) feeVal="0.0";
			logger.debug("Fee Val: " + feeVal);
			dto1.setFee(feeVal);
			String otherFeeVal="0.0";
			if(otherFeeVal==null)
				otherFeeVal="0.0";
			logger.debug("otherFeeVal: " + otherFeeVal);
			dto1.setOtherFee(otherFeeVal);
			float total = 0;
			total=Float.parseFloat(dto1.getFee())+Float.parseFloat(dto1.getOtherFee());
			logger.debug("total: " + total);
			dto1.setTotalFee(Float.toString(total));
			request.setAttribute("fee",feeVal);
			request.setAttribute("otherFee",otherFeeVal);
			request.setAttribute("totalFee",String.valueOf(total));	
//START ANUJ
		try {
			ArrayList PendingWithdrawList=withdrawalBD.pendingWithPayment(officeId, total);
			eForm.getObjDashboard().setPendingWithdrawList(PendingWithdrawList);
			request.setAttribute("PendingWithdrawList", PendingWithdrawList);
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage(), e1);
		}
		String willStatus=(String)request.getParameter("willStatus");
		if("willWithdrawPending".equalsIgnoreCase(request.getParameter("willStatus"))){
				String s=(String)request.getParameter("willStatus");
				String will=(String)request.getParameter("will");
				String willStat[]= will.split("~");
				if(willStat.length==2){
					String will_txn_id=(String)willStat[0];
					request.setAttribute("newWillId", will_txn_id);

					String amount=(String)willStat[1];
					if("null".equalsIgnoreCase(amount)){
						amount="0";
					}
					double d = Double.parseDouble(amount);
					int amt=(int)d;
					
					if(amt<=0){
						String stat=bd.checkStatus(will_txn_id, funId);
						dto.setWillId(will_txn_id);
						forwardJsp = stat;
						if("pending".equalsIgnoreCase(stat)){
							forwardJsp = "withdrawIdDetailsReadOnlyPending";
							WillViewBD viewBD = new WillViewBD();

							//
							WillDepositBD willBD = new WillDepositBD();
							
							WillDepositDTO willDto = eForm.getWillDTO();
							willDto = viewBD.getwillIdDetails(will_txn_id.trim(), StrFunId,lang);
							willRemarks = willBD.getWillRemarks(will_txn_id.trim());
							willDto.setWillRemarksList(willRemarks);
							eForm.setWillDTO(willDto);
							request.setAttribute("eForm", eForm);
							//
							
							
							dto1 = viewBD.getwillIdDetails(will_txn_id, StrFunId,lang);//withdrawalBD.getWillDeposit(willid1,StrFunId);
							willRemarks = bd.getWillRemarks(will_txn_id);
							dto1.setWillRemarksList(willRemarks);
							request.setAttribute("PAGETYPE", "willWithUpdate");
							eForm.setWillDTO(dto1);
							
							forwardJsp = "withdrawIdDetailsReadOnlyPending";
						}
					}		
					
					//a if 100% payment is done
						else{
							WillViewBD viewBD = new WillViewBD();
							
							//
							WillDepositBD willBD = new WillDepositBD();
							
							WillDepositDTO willDto = eForm.getWillDTO();
							willDto = viewBD.getwillIdDetails(will_txn_id.trim(), StrFunId,lang);
							willRemarks = willBD.getWillRemarks(will_txn_id.trim());
							willDto.setWillRemarksList(willRemarks);
							willDto.setTotalFee(String.valueOf(total));
							eForm.setWillDTO(willDto);
							request.setAttribute("eForm", eForm);
							//
							
							
							dto1 = viewBD.getwillIdDetails(will_txn_id, StrFunId,lang);//withdrawalBD.getWillDeposit(willid1,StrFunId);
							willRemarks = bd.getWillRemarks(will_txn_id);
							dto1.setWillRemarksList(willRemarks);
							dto1.setTotalFee(String.valueOf(total));
							request.setAttribute("PAGETYPE", "willWithUpdate");
							eForm.setWillDTO(dto1);
							
							forwardJsp = "withdrawIdDetailsReadOnlyPending";
						}
					//a
					
					
				}
				
			}
			
			
//END ANUJ
			String fwdName=request.getParameter("fwdName");
			String actionName = dto.getActionName();
			ArrayList errorList = new ArrayList();
			
			

			
			if("P".equalsIgnoreCase(nextAction)){
				WillDepositDTO formDTO = eForm.getWillDTO();
				request.setAttribute("newWillId",formDTO.getWithWillId());
				formDTO.setActionName("");
				WillRetrievalBD bd1= new WillRetrievalBD();
				String k=formDTO.getPayWillId();
				String will=bd1.getWillId(k);
				formDTO.setActionName("");
				String stat=bd.checkStatus(k, funId);
				request.setAttribute("newWillId",will);
				request.setAttribute("depWillID",will);
				formDTO.setWillId(will);
				forwardJsp = stat;
				//forwardJsp = CommonConstant.TXN_SUCCESSFUL;
			}
			
			//
			
			if ("photo".equalsIgnoreCase(fwdName)) 
			{} //end photo upload 
		 
		 
			//Start photo upload
			if ("thumb".equalsIgnoreCase(fwdName)) 
			{
		
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
				forwardJsp = "withdrawIdDetailsRetrievalSuccess";   
			} //end photo upload 
			
			
			//Start photo upload
			if("showBut".equalsIgnoreCase(fwdName)){
				dto1.setShow("show");
				forwardJsp = "withdrawIdDetailsRetrievalSuccess";
			}
			//Start upload doc by santosh
			if("anuj".equalsIgnoreCase(fwdName)){
				dto1.setShow("show");
				forwardJsp = "withdrawIdDetailsRetrievalSuccess";
			}
			
			if ("signature".equalsIgnoreCase(fwdName)) 
			{
				request.setAttribute("PAGETYPE", "willWithdrawal");
						ArrayList listState1 = bd.getState(dto1.getCourtCountry(),lang);
						//eForm.setWillState(listState1);
						ArrayList listDistrict1 = bd.getDistrict(dto1.getCourtState(),lang);
						//eForm.setWillDistrict(listDistrict1);
						String type = request.getParameter("type");
						
						if ("photo".equalsIgnoreCase(type)) {
							boolean rt = bd.uploadFileCheck(uplPath
									+ dto1.getUniqueId()+"/PHOTO.GIF");

							
								dto1.setCompPhotoPath(uplPath
									+ dto1.getUniqueId()+"/Photo.GIF");
								dto1.setRetDocumentName("PHOTO.GIF");
								dto1.setRetPhotoCheck("uploaded");
								dto1.setRetPhotoPath(uplPath
									+ dto1.getUniqueId()+"/Photo.GIF");
							

						} else if ("thumb".equalsIgnoreCase(type)) {
							boolean rt = bd.uploadFileCheck(uplPath
									+ dto1.getUniqueId()+"/THUMB.GIF");

							
								dto1.setCompThumbPath(uplPath
									+ dto1.getUniqueId()+"/THUMB.GIF");
								dto1.setRetThunmbName("THUMB.GIF");
								dto1.setRetThumbCheck("uploaded");
								dto1.setRetThumbPath(uplPath
										+ dto1.getUniqueId()+"/THUMB.GIF");
							
						}
						else if("certi".equalsIgnoreCase(type)){
							boolean rt = bd.uploadFileCheck(uplPath
									+ dto1.getUniqueId()+"/CERTIFICATE.PDF");

							
								dto1.setAgentProofCheck("uploaded");
								dto1.setCompCertiPath(uplPath
									+ dto1.getUniqueId()+"/CERTIFICATE.PDF");
								dto1.setCertificateName("CERTIFICATE.PDF");
								dto1.setDeathCertCheck("uploaded");
								dto1.setRetDeathCertiPath(uplPath
									+ dto1.getUniqueId()+"/CERTIFICATE.PDF");
							
						}else{
							boolean rt = bd.uploadFileCheck(uplPath
									+ dto1.getUniqueId()+"/SIGNATURE.GIF");
							
								dto1.setCompSignPath(uplPath
									+ dto1.getUniqueId()+"/SIGNATURE.GIF");
								dto1.setRetSignCheck("uploaded");
								dto1.setRetSignatureName("SIGNATURE.GIF");
								dto1.setRetSignPath(uplPath
									+ dto1.getUniqueId()+"/SIGNATURE.GIF");
							
						}

				forwardJsp = "withdrawIdDetailsRetrievalSuccess";   
			}

			
			if ("certificate".equalsIgnoreCase(fwdName)) 
			{}
			//
			
			
			if ("goEDIT".equalsIgnoreCase(dto1.getActionName())) {
				
				/*ArrayList listState1 = bd.getState(dto1.getCourtCountry(),lang);
				eForm.setWillState(listState1);
				ArrayList listDistrict1 = bd.getDistrict(dto1.getCourtState(),lang);
				eForm.setWillDistrict(listDistrict1);*/
					
				forwardJsp = "withdrawIdDetailsRetrievalSuccess";
				logger.debug("Forwarded to:-   " + forwardJsp);
			}
			
			
			
			
			
		if(page!=null){
			if("withdrawWill".equals(page)){
				WillDepositDTO formDTO = new WillDepositDTO ();
				eForm.setWillDTO(formDTO);
				forwardJsp = CommonConstant.WILL_WITHDRAW_PAGE;
			}
			else if(page.equalsIgnoreCase("updateWill")){
				WillDepositDTO formDTO = eForm.getWillDTO();
				willUpdateBD = new WillUpdateBD();
				String status = (String)session.getAttribute("status");
//				if(status != null && !status.equalsIgnoreCase("fail")){
//				}
				forwardJsp = CommonConstant.TXN_SUCCESSFUL;
			}
		}
		else{
		if (form != null) {
	
			
			WillCommonBD commonBD = new WillCommonBD();
			
			WillViewBD viewBD = new WillViewBD();
			
			WillCommonBD willComBD = new WillCommonBD();
			String formName = eForm.getWillDTO().getWillWithdrawForm();
			if(CommonConstant.WIll_WITHDRAWAL_FORM.equalsIgnoreCase(formName))
			{
				WillDepositDTO formDTO = eForm.getWillDTO();
				
				if("withdrawAppDetails".equals(formDTO.getActionName()))
				{

					String refId = formDTO.getWillId().trim();
					String fromDate = formDTO.getFromDepositeDate();
					String toDate = formDTO.getToDepositeDate();
					ArrayList will_view_details = willComBD.getWillViewDetails(
							refId, fromDate, toDate, "Deposited", officeId);
					
					eForm.setWithdrawalViewList(will_view_details);

					forwardJsp = CommonConstant.WITHDRAWAL_VIEW_PAGE;
				}
				if("updateWithdrawAppDetails".equals(formDTO.getActionName()))
				{
					String refId = formDTO.getWillId().trim();
					String fromDate = formDTO.getFromDepositeDate();
					String toDate = formDTO.getToDepositeDate();
					
					ArrayList will_view_details = viewBD.getUpdateWillViewDetails(
							refId, fromDate, toDate, "Pending Withdraw", officeId);
					
					eForm.setWithdrawalViewList(will_view_details);

					forwardJsp = CommonConstant.WITHDRAWAL_VIEW_PAGE;
				}
			}
			//ID Details Retrieval.
			WillDepositDTO formDTO = eForm.getWillDTO();
			if("willWithdrawalID".equals(formDTO.getActionName())){

				String willid1 = (String) request.getParameter("refId");
				

				if (willid1 != null || dto1.getWillId()!=null) {

					try{dto1 = viewBD.getwillIdDetails(willid1, StrFunId,lang);//withdrawalBD.getWillDeposit(willid1,StrFunId);
					willRemarks = bd.getWillRemarks(willid1);
					dto1.setWillRemarksList(willRemarks);
					String uni=(String)request.getParameter("uni");
					System.out.println("ANUJ "+dto1.getUniqueId());
					if(uni==null){
						
						String str=GUIDGenerator.getGUID();
						dto1.setUniqueId(str);
						
						dto1.setDphotoPath(uplPath);
						dto1.setDocName("PHOTO.GIF");
						
						
						dto1.setDsignPath(uplPath);
						dto1.setSignature("SIGNATURE.GIF");
						
						dto1.setForwardPath("Withdraw.do?fwdName=signature");
						dto1.setForwardName("/Withdraw");
						
						dto1.setDthumbPath(uplPath);
						dto1.setThumb("THUMB.GIF");
						
						
						dto1.setCertificateName("CERTIFICATE.PDF");
						dto1.setDproofPath(uplPath);
					}
					
					System.out.println("ANUJ "+dto1.getUniqueId());
					eForm.setWillDTO(dto1);
					forwardJsp = CommonConstant.WITHDRAWAL_ID_SUCCESS;
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			

			if(CommonConstant.WIll_WITHDRAWAL_FORM.equalsIgnoreCase(formName))
			{
				if("goPreviousIDs".equals(formDTO.getActionName()))
				{
					forwardJsp = "withdrawIdDetailsReadOnly";
				}
				if("goReadOnlyIDDetails".equals(formDTO.getActionName()))
				{
					pgTitle="";
				//new
					String status = (String)session.getAttribute("status");
					newWillId = withdrawalBD.updateWillWithDrawDetails(formDTO, userId, formDTO.getWillId(), status,formDTO.getRemarks(), total, funId, officeId, lang);
					//}
					
					WillUpdateBD willUpdateBD1= new WillUpdateBD();
						if(newWillId != null && newWillId.length() > 0){
							willUpdateBD1.updateDeliveryDetails(formDTO.getRemarks(),newWillId,userId);
							WillDepositDTO willDepDTO = eForm.getWillDTO();
													
								try{
							if(formDTO.getRetrieverType().equalsIgnoreCase("Citizen")){
								boolean citizenUpdated = withdrawalBD.updateCitizenDetailsCitizen(willDepDTO,newWillId, lang);
								formDTO.setWithWillId(newWillId);
							}
							if(formDTO.getRetrieverType().equalsIgnoreCase("WithAgent"))
							{
							boolean citizenUpdated = withdrawalBD.updateCitizenDetails(willDepDTO,newWillId,lang);
							try{WillCommonBD com = new WillCommonBD();
							formDTO.setShow_photo_id_proof(com.getIdType(willDepDTO.getRetrieverIdProof(), lang));
							}catch(Exception e){
								e.printStackTrace();
							}
							formDTO.setWithWillId(newWillId);
							}}
							catch(Exception e){
							}
						}
					//new
					
					forwardJsp = CommonConstant.WITHDRWAL_IDDETAILS_READ_ONLY;
				}
				if("updtwithDrawStatus".equals(formDTO.getActionName()))
				{
					pgTitle="";
					String willId="";
					willId=dto.getWillId();
					willUpdateBD = new WillUpdateBD();
					willUpdateBD.updateWithDrawalStatus(willId);
					forwardJsp = CommonConstant.WITHDRAW_UPDATE_SUCCESS;
				}
			}

			if(CommonConstant.WIll_WITHDRAWAL_FORM.equalsIgnoreCase(formName))
			{
				if("idDetailsReadOnly".equals(formDTO.getActionName()))
				{
					forwardJsp = CommonConstant.WITHDRAWAL_ID_SUCCESS;
				}

				if("proceedPayment".equals(formDTO.getActionName()))
				{
					WillDepositDTO willDTO=new WillDepositDTO();
					logger.debug("Func Id of Will Withdrawl is:" + funId);
					if(funId==null){
						funId="FUN_014";
					}
					//Need to check the method...
					//willDTO=bd.getFunctionName(funId);
					willUpdateBD = new WillUpdateBD();
					if(formDTO.getRetWillId()==null){
					formDTO.setPayWillId(formDTO.getWillId());
					}
					else{
						formDTO.setPayWillId(formDTO.getRetWillId());
					}
					
					String status = (String)session.getAttribute("status");
					boolean citizenUpdated=false;
					String pending=(String)request.getParameter("pendingWithdraw");
					if("pendingWithdraw".equalsIgnoreCase(pending)){
						newWillId = withdrawalBD.updateWillWithdrawDetails1(formDTO, userId, formDTO.getWillId(), status,formDTO.getRemarks(), total, funId);	
					}
					else{
					
						newWillId = withdrawalBD.updateWillWithdrawDetails1(formDTO, userId, formDTO.getWithWillId(), status,formDTO.getRemarks(), total, funId);	
				}
					
					int amount;
					if("pendingWithdraw".equalsIgnoreCase(pending)){
					amount=bd.amountLeft(formDTO.getWillId(),String.valueOf(total));
					//formDTO.setWithWillId(newWillId);
					}
					else{
						amount=bd.amountLeft(formDTO.getWithWillId(), String.valueOf(total));
						//formDTO.setWithWillId(newWillId);
					}
					ArrayList alist = withdrawalBD.getDetails(officeId);
					ArrayList rowList = (ArrayList)alist.get(0);
					String officeName=(String)rowList.get(1);
					String districtId=(String)rowList.get(2);
					
					String districtName=(String)rowList.get(3);
					String willRefId=formDTO.getPayWillId();
					
					logger.debug("WillWithdrawalAction:: inside    proceedPayment    ");
					
					request.setAttribute("forwardPath", "./Withdraw.do?TRFS=NGI");
	                request.setAttribute("parentTable", "IGRS_WILL_TXN_PAYMENT_DTLS");
	                request.setAttribute("parentAmount",String.valueOf(amount));
	                request.setAttribute("parentFunId", funId);
	                request.setAttribute("parentKey", newWillId);
	                request.setAttribute("parentModName", "WILL DEPOSIT");
	                request.setAttribute("parentFunName", "WILL WITHDRAWL");
	                request.setAttribute("parentColumnName", "WILL_PAYMENT_ID");
	                session.setAttribute("formDTO",formDTO);
	                request.setAttribute("parentOfficeId", officeId);
	                request.setAttribute("parentOfficeName", officeName);
	                request.setAttribute("parentDistrictId", districtId);
	                request.setAttribute("parentDistrictName", districtName);
	                request.setAttribute("parentReferenceId", willRefId);
					request.setAttribute("formName","willdeposit");


					forwardJsp = CommonConstant.PROCEED_WITHDRAWAL_PAYMENT_PAGE;
					logger.debug("WillWithdrawalAction:: GOING TO :-       "+forwardJsp);
					
				}
			}
			if ("downloadphoto".equalsIgnoreCase(pgTitle)) 
	        {
	        	//downloadDocument(response, dto.getDocContents(), dto.getDocumentName());
				 try {
					response.setContentType("application/download");
					    
						String filename=(String)dto.getPhotoPath();
						File fileToDownload = new File(filename);

						response.setHeader("Content-Disposition", "attachment; filename="

					                                        + URLEncoder.encode(dto.getDocumentName().toString(),"UTF-8"));
						

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
	        	forwardJsp = "withdrawIdDetailsRetrievalSuccess";
	        } 
	        if ("downloadthumb".equalsIgnoreCase(pgTitle)) 
	        {
	        	//downloadDocument(response, dto.getThumbContents(), dto.getThunmbName());
	        	
	        	try {
					response.setContentType("application/download");
					
					String filename=(String)dto.getThumbPath();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition", "attachment; filename="

					                                    + URLEncoder.encode(dto.getThunmbName().toString(),"UTF-8"));
					

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
	        	
	        	forwardJsp = "withdrawIdDetailsRetrievalSuccess";
	        } 
	        if ("downloadsignature".equalsIgnoreCase(pgTitle)) 
	        {
	        	//downloadDocument(response, dto.getSignatureContents(), dto.getSignatureName());
	        	try {
					response.setContentType("application/download");
					
					String filename=(String)dto.getSignPath();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition", "attachment; filename="

					                                    + URLEncoder.encode(dto.getSignatureName().toString(),"UTF-8"));
					

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
	        	
	        	
	        	forwardJsp = "withdrawIdDetailsRetrievalSuccess";
	        } 
	        if ("downloadAgntPrf".equalsIgnoreCase(pgTitle)) 
	        {
	        	//downloadDocument(response, dto.getAgentProofContents(), dto.getAgentProofName());
	        	
	        	try {
					response.setContentType("application/download");
					
					String filename=(String)dto.getAgentProofPath();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition", "attachment; filename="

					                                    + URLEncoder.encode(dto.getAgentProofName().toString(),"UTF-8"));
					

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
	        	
	        	
	        	forwardJsp = "withdrawIdDetailsRetrievalSuccess";
	        }
	        
	        
	        if ("downloadphotoCit".equalsIgnoreCase(pgTitle)) 
	        {
	        	//downloadDocument(response, dto.getRetDocContents(), dto.getRetDocumentName());
	        	
	        	
	        	try {
					response.setContentType("application/download");
					
					String filename=(String)dto.getRetPhotoPath();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition", "attachment; filename="

					                                    + URLEncoder.encode(dto.getRetDocumentName().toString(),"UTF-8"));
					

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
	        	
	        	
	        	forwardJsp = "withdrawIdDetailsRetrievalSuccess";
	        } 
	        if ("downloadthumbCit".equalsIgnoreCase(pgTitle)) 
	        {
	        	//downloadDocument(response, dto.getRetThumbContents(), dto.getRetThunmbName());
	        	
	        	try {
					response.setContentType("application/download");
					
					String filename=(String)dto.getRetThumbPath();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition", "attachment; filename="

					                                    + URLEncoder.encode(dto.getThunmbName().toString(),"UTF-8"));
					

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
	        	
	        	forwardJsp = "withdrawIdDetailsRetrievalSuccess";
	        } 
	        if ("downloadsignatureCit".equalsIgnoreCase(pgTitle)) 
	        {
	        	//downloadDocument(response, dto.getRetSignatureContents(), dto.getRetSignatureName());
	        	try {
					response.setContentType("application/download");
					
					String filename=(String)dto.getRetSignPath();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition", "attachment; filename="

					                                    + URLEncoder.encode(dto.getRetSignatureName().toString(),"UTF-8"));
					

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
	        	
	        	
	        	forwardJsp = "withdrawIdDetailsRetrievalSuccess";
	        } 
	        if ("downloadDeathCertCit".equalsIgnoreCase(pgTitle)) 
	        {
	        	//downloadDocument(response, dto.getDeathCertDocContents(), dto.getCertificateName());
	        	
	        	try {
					response.setContentType("application/download");
					
					String filename=(String)dto.getDeathCertiPath();
					if(filename==null||filename.equalsIgnoreCase("null")||"".equalsIgnoreCase(filename)||filename==""){
						filename=(String)dto.getRetDeathCertiPath();
					}
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition", "attachment; filename="

					                                    + URLEncoder.encode(dto.getCertificateName().toString(),"UTF-8"));
					

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
	        	forwardJsp = "withdrawIdDetailsRetrievalSuccess";
	        }
	        if ("downloadphotoDevice".equalsIgnoreCase(pgTitle)) 
	        {
	        	//downloadDocument(response, dto.getDocContents(), dto.getDocumentName());
				 //OutputStream out;
				try {
					response.setContentType("application/download");
					    
						String filename=(String)dto.getCompPhotoPath();
						File fileToDownload = new File(filename);

						response.setHeader("Content-Disposition", "attachment; filename="

					                                        + URLEncoder.encode(dto.getDocumentName().toString(),"UTF-8"));
						

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

		            
	        	forwardJsp = "withdrawIdDetailsRetrievalSuccess";
	        } 
	        if ("downloadthumbDevice".equalsIgnoreCase(pgTitle)) 
	        {
	        	//downloadDocument(response, dto.getThumbContents(), dto.getThunmbName());
	        	
	        	try {
					response.setContentType("application/download");
					
					String filename=(String)dto.getCompThumbPath();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition", "attachment; filename="

					                                    + URLEncoder.encode(dto.getThunmbName().toString(),"UTF-8"));
					

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
	        	
	        	forwardJsp = "withdrawIdDetailsRetrievalSuccess";
	        } 
	        if ("downloadsignatureDevice".equalsIgnoreCase(pgTitle)) 
	        {
	        	//downloadDocument(response, dto.getSignatureContents(), dto.getSignatureName());
	        	try {
					response.setContentType("application/download");
					
					String filename=(String)dto.getCompSignPath();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition", "attachment; filename="

					                                    + URLEncoder.encode(dto.getSignatureName().toString(),"UTF-8"));
					

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
	        	
	        	
	        	forwardJsp = "withdrawIdDetailsRetrievalSuccess";
	        } 
	        if ("downloadAgntPrfDevice".equalsIgnoreCase(pgTitle)) 
	        {
	        	//downloadDocument(response, dto.getAgentProofContents(), dto.getAgentProofName());
	        	
	        	try {
					response.setContentType("application/download");
					
					String filename=(String)dto.getCompCertiPath();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition", "attachment; filename="

					                                    + URLEncoder.encode(dto.getAgentProofName().toString(),"UTF-8"));
					

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
	        	
	        	
	        	forwardJsp = "withdrawIdDetailsRetrievalSuccess";
	        }

			
		}
		}
		System.out.println(forwardJsp);
		return mapping.findForward(forwardJsp);
		
	}
	private void downloadDocument(HttpServletResponse res, byte[] docContents, String fileName) {
		
		try {			
			
			OutputStream os = res.getOutputStream();
				long index = 1;
				String ext = null;
				if(fileName!=null)
				{				
				ext = fileName.substring(fileName.length()-3, fileName.length());				
				if(ext!=null){
						
					if (ext.equalsIgnoreCase("doc")){
						res.setContentType("application/download");
					}
					else if (ext.equalsIgnoreCase("docx")){
						res.setContentType("application/download");
					}
					else if (ext.equalsIgnoreCase("ppt")){
						res.setContentType("application/download");
					}
					else if (ext.equalsIgnoreCase("xls")){
						res.setContentType("application/download");
					}
					else if (ext.equalsIgnoreCase("htm")){
						res.setContentType("text/html");
					}
					else if (ext.equalsIgnoreCase("tml")){
						res.setContentType("text/html");
					}
					else if (ext.equalsIgnoreCase("txt")){
						res.setContentType("text/plain");
					}
					else if (ext.equalsIgnoreCase("pdf")){
						res.setContentType("application/pdf");
					}
					else if (ext.equalsIgnoreCase("bmp")){
						res.setContentType("image/x-bmp");
					}
					else if (ext.equalsIgnoreCase("gif")){
						res.setContentType("image/gif");
					}
					else if (ext.equalsIgnoreCase("jpg")){
						res.setContentType("image/jpeg");
					}
					else if (ext.equalsIgnoreCase("peg")){
						res.setContentType("image/jpeg");
					}
						else
						{
							res.setContentType("application/download");
						}
				}
			}
			
			res.setContentType("application/download");
		    res.setHeader ("Content-Disposition", "attachment; filename="+fileName);
			//res.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(fileName));		 
			os.write(docContents);			
			os.close();
			os.flush();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
				
	}

	/**
	 * @param request
	 * @param messages
	 * @param willDepositionErrorBundle
	 */
	private void saveMessages(HttpServletRequest request,
			ActionMessages messages, String willDepositionErrorBundle) {

		request.setAttribute(
				WILLErrorConstants.WILL_DEPOSITION_ERROR_BUNDLE,
				willDepositionErrorBundle);

		saveMessages(request, messages);

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
