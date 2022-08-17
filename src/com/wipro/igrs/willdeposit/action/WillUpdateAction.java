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
 * FILE NAME: WillUpdateAction.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 12th APRIL 2008 
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE ACTION FOR GUIDELINE DELIEVRY UPDATION 
 */

package com.wipro.igrs.willdeposit.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
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

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.WILLErrorConstants;
import com.wipro.igrs.willdeposit.bd.WillDepositBD;
import com.wipro.igrs.willdeposit.bd.WillUpdateBD;
import com.wipro.igrs.willdeposit.bd.WillViewBD;
import com.wipro.igrs.willdeposit.constant.CommonConstant;
import com.wipro.igrs.willdeposit.dto.WillDepositDTO;
import com.wipro.igrs.willdeposit.form.WillDepositForm;

/**
 * @author NIHAR M.
 * 
 */
public class WillUpdateAction extends BaseAction {

	String forwardJsp = new String("UpdateWillDelivery");
	private Logger logger = (Logger) Logger.getLogger(WillUpdateAction.class);

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
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		String lang=(String)session.getAttribute("languageLocale");
		ActionMessages messages = new ActionMessages();
		String page = request.getParameter("page");
		String roleId = (String) session.getAttribute("roleId");
		String funId = (String) session.getAttribute("functionId");
		String userId = (String) session.getAttribute("UserId");
		String pgTitle=request.getParameter("pageTitle");
		WillDepositForm eForm = (WillDepositForm) form;
		WillDepositDTO willDto = eForm.getWillDTO();
		String refID="";
		
		String pageType="";
		String officeId = (String)session.getAttribute("loggedToOffice");
		if(request.getParameter("pageType")!=null){
		pageType=request.getParameter("pageType");
		request.setAttribute("PAGETYPE", pageType);
		
		}

		
		if (page != null) {
			if ("delivery".equals(page)) {
				
				willDto.setFromDepositeDate("");
				willDto.setToDepositeDate("");
				willDto.setWillId("");
				forwardJsp = CommonConstant.WILL_DELIVERY_UPDATE;
			}
		} else {
			if (form != null) {
				

				WillUpdateBD willBD = new WillUpdateBD();
				WillDepositBD willDepBD = new WillDepositBD();
				String formName = eForm.getWillDTO().getWillUpdateForm();
				String StrFunId = funId;
				ArrayList willRemarks = null;
				if (CommonConstant.WILL_UPDATE.equalsIgnoreCase(formName)) {
					WillDepositDTO formDTO = eForm.getWillDTO();
					if ("willUpdateNext".equals(formDTO.getActionName())) {

						refID= formDTO.getWillId();
						String fromDate = formDTO.getFromDepositeDate();
						String toDate = formDTO.getToDepositeDate();
						String status = formDTO.getWillStatus();
						ArrayList willViewDetails = willBD
								.getDeliveryViewDetails(refID, fromDate,
										toDate, status,officeId);
						eForm.setWillViewsList(willViewDetails);
						forwardJsp = CommonConstant.WILL_UPDATE_NEXT;
						logger
								.debug("WillUpdateAction:: FORWARDED TO(WU1):-       "
										+ forwardJsp);
					}

					if ("willIDDetails".equals(formDTO.getActionName())) {
						String willid1 = request.getParameter("refId");
						WillViewBD viewBD = new WillViewBD();
						WillDepositDTO dto = eForm.getWillDTO();
						dto = viewBD.getwillIdDetails(willid1, StrFunId,lang);// willBD.getwillIdDetails(willid1,
																			// StrFunId);
						willRemarks = willDepBD.getWillRemarks(willid1);
						dto.setWillRemarksList(willRemarks);

						eForm.setWillDTO(dto);
						request.setAttribute("eForm", eForm);

						forwardJsp = CommonConstant.VIEW_IND_ID_DETAILS;
						logger
								.debug("WillUpdateAction:: GOING TO ID VIEW DETAILS:-       "
										+ forwardJsp);
					}

					if ("individualWillIDDetails".equals(formDTO
							.getActionName())) {
						pgTitle="";
						WillDepositDTO wDTO = eForm.getWillDTO();
						String treasuryId="";
						treasuryId=wDTO.getTreasuryId();
						String remarks = wDTO.getDeliveryRemark();
						String willid = wDTO.getWillId();
						boolean deliveryConfirmed = willBD
								.updateTreasuryId(treasuryId, willid, userId);
						if (deliveryConfirmed) {
						}
						forwardJsp = CommonConstant.WILL_DELIVERY_UPDATED;
						logger.debug("WillDepositeAction:: GOING DONE:-       "
								+ forwardJsp);
					}
				}
			}
			if ("downloadphoto".equalsIgnoreCase(pgTitle)) 
	        {
	        	//downloadDocument(response, willDto.getDocContents(), willDto.getDocumentName());
	        	
				 response.setContentType("application/download");
		            
					String filename=(String)willDto.getPhotoPath();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition", "attachment; filename="

		                                                + URLEncoder.encode(willDto.getDocumentName().toString(),"UTF-8"));
					
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
				
				forwardJsp = "viewIndividualIDDetails";
	        } 
	        if ("downloadthumb".equalsIgnoreCase(pgTitle)) 
	        {
	        	//downloadDocument(response, willDto.getThumbContents(), willDto.getThunmbName());
	        	
response.setContentType("application/download");
	            
				String filename=(String)willDto.getThumbPath();
				File fileToDownload = new File(filename);

				response.setHeader("Content-Disposition", "attachment; filename="

	                                                + URLEncoder.encode(willDto.getThunmbName().toString(),"UTF-8"));
				
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
	        	
	        	forwardJsp = "viewIndividualIDDetails";
	        } 
	        if ("downloadsignature".equalsIgnoreCase(pgTitle)) 
	        {
	        	//downloadDocument(response, willDto.getSignatureContents(), willDto.getSignatureName());
	        	
response.setContentType("application/download");
	            
				String filename=(String)willDto.getSignPath();
				File fileToDownload = new File(filename);

				response.setHeader("Content-Disposition", "attachment; filename="

	                                                + URLEncoder.encode(willDto.getSignatureName().toString(),"UTF-8"));
				
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
	        	
	        	forwardJsp = "viewIndividualIDDetails";
	        }
	        
	        //
	        if ("agentProof".equalsIgnoreCase(pgTitle)) 
	        {
	        	//downloadDocument(response, dto.getDeathCertDocContents(), dto.getCertificateName());
	        	
	        	response.setContentType("application/download");
	            
				String filename=(String)willDto.getAgentProofPath();
				File fileToDownload = new File(filename);

				response.setHeader("Content-Disposition", "attachment; filename="

	                                                + URLEncoder.encode(willDto.getAgentProofName().toString(),"UTF-8"));
				
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
	        	forwardJsp = "viewIndividualIDDetails";
	        }
	        //
	        
	        
	        
		}
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

		request.setAttribute(WILLErrorConstants.WILL_DEPOSITION_ERROR_BUNDLE,
				willDepositionErrorBundle);

		saveMessages(request, messages);

	}
}
