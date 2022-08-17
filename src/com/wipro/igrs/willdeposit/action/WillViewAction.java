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
 * FILE NAME: WillViewAction.java
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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.willdeposit.bd.WillCommonBD;
import com.wipro.igrs.willdeposit.bd.WillDepositBD;
import com.wipro.igrs.willdeposit.bd.WillViewBD;
import com.wipro.igrs.willdeposit.constant.CommonConstant;
import com.wipro.igrs.willdeposit.dto.WillDepositDTO;
import com.wipro.igrs.willdeposit.form.WillDepositForm;

/**
 * @author NIHAR M.
 * 
 */
public class WillViewAction extends BaseAction {

	String forwardJsp = new String("viewWill");
	private Logger logger = (Logger) Logger.getLogger(WillViewAction.class);

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
		String page = request.getParameter("page");
		String roleId = (String) session.getAttribute("roleId");
		String funId = (String) session.getAttribute("functionId");
		String userId = (String) session.getAttribute("UserId");
		WillDepositForm eForm = (WillDepositForm) form;
		String pgTitle=request.getParameter("pageTitle");
		WillDepositDTO dto = eForm.getWillDTO();
		
		String officeId = (String)session.getAttribute("loggedToOffice");
		
		if (page != null) {
			if ("viewWill".equals(page)) {
				forwardJsp = CommonConstant.WILL_VIEW;
				WillDepositDTO willDepositDTO = new WillDepositDTO();
				eForm.setWillDTO(willDepositDTO);
				logger.debug("DEPOSITE ACTION:: FORWARDED PAGE :-     "
						+ forwardJsp);
			}
		} else {
			if (form != null) {

				WillViewBD viewBD = new WillViewBD();
				WillCommonBD willComBD = new WillCommonBD();
				ArrayList willRemarks = null;
				String StrFunId = funId;
				viewBD.getDistrictId(officeId);
				String formName = eForm.getWillDTO().getWillViewForm();
				WillDepositBD willBD = new WillDepositBD();
				WillDepositDTO formDTO = eForm.getWillDTO();
				if (CommonConstant.WILL_VIEW_FORM.equalsIgnoreCase(formName)) {

					if ("willViewNext".equals(formDTO.getActionName())) {
						String refID = formDTO.getWillId().trim();
						String fromDate = formDTO.getFromDepositeDate();
						String toDate = formDTO.getToDepositeDate();
						String status = formDTO.getWillStatus();
						ArrayList willViewDetails = viewBD.getWillViewDetails(
								refID, fromDate, toDate, status, officeId, lang);
						
						eForm.setWillViewsList(willViewDetails);
						forwardJsp = CommonConstant.VIEW_SUCCESS;
						logger.debug("WillViewAction:: GOING TO:-       "
								+ forwardJsp);
					}
				}

				if ("willViewlID".equalsIgnoreCase(formDTO
						.getWillViewDetailsAction())) {

					String willid1 = request.getParameter("refId");

					WillDepositDTO willDto = eForm.getWillDTO();
					willDto = viewBD.getwillIdDetails(willid1.trim(), StrFunId,lang);
					if("en".equalsIgnoreCase(lang)){
						if("Withdrawn".equalsIgnoreCase(willDto.getWillStatus())){
							willDto.setWill_stat_view("Withdrawn");
						}
						else if("Deposited".equalsIgnoreCase(willDto.getWillStatus())){
							willDto.setWill_stat_view("Deposited");
						}
						else if("Retrieved".equalsIgnoreCase(willDto.getWillStatus())){
							willDto.setWill_stat_view("Retrieved");
						}
						else if("Pending Retrieve".equalsIgnoreCase(willDto.getWillStatus())){
							willDto.setWill_stat_view("Pending Retrieve");
						}
						else if("Pending Withdraw".equalsIgnoreCase(willDto.getWillStatus())){
							willDto.setWill_stat_view("Pending Withdraw");
						}
						else if("Pending Deposit".equalsIgnoreCase(willDto.getWillStatus())){
							willDto.setWill_stat_view("Pending Deposit");
						}
						

					}
					else{
						if("Withdrawn".equalsIgnoreCase(willDto.getWillStatus())){
							willDto.setWill_stat_view("वापस लिया");
						}
						else if("Deposited".equalsIgnoreCase(willDto.getWillStatus())){
							willDto.setWill_stat_view("जमा");
						}
						else if("Retrieved".equalsIgnoreCase(willDto.getWillStatus())){
							willDto.setWill_stat_view("पुनः प्राप्त");
						}
						else if("Pending Retrieve".equalsIgnoreCase(willDto.getWillStatus())){
							willDto.setWill_stat_view("पुनः प्राप्त लंबित");
						}
						else if("Pending Withdraw".equalsIgnoreCase(willDto.getWillStatus())){
							willDto.setWill_stat_view("लंबित वापसी");
						}
						else if("Pending Deposit".equalsIgnoreCase(willDto.getWillStatus())){
							willDto.setWill_stat_view("लंबित जमा");
						}
					}
					willRemarks = willBD.getWillRemarks(willid1.trim());
					willDto.setWillRemarksList(willRemarks);
					eForm.setWillDTO(willDto);
					request.setAttribute("eForm", eForm);
					willDto.setWillId(willid1);

					forwardJsp = "viewIndividualIDDetails";
					logger.debug(forwardJsp);
				}
			}
		}
		
		if ("downloadphoto".equalsIgnoreCase(pgTitle)) 
        {
        	//downloadDocument(response, dto.getDocContents(), dto.getPhotoPath());
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
			
			forwardJsp = "viewIndividualIDDetails";
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
        	
        	
        	
        	forwardJsp = "viewIndividualIDDetails";
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
        	
        	
        	
        	forwardJsp = "viewIndividualIDDetails";
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
        	
        	forwardJsp = "viewIndividualIDDetails";
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
        	
        	forwardJsp = "viewIndividualIDDetails";
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
        	
        	
        	forwardJsp = "viewIndividualIDDetails";
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
        	
        	
        	forwardJsp = "viewIndividualIDDetails";
        } 
        if ("downloadDeathCertCit".equalsIgnoreCase(pgTitle)) 
        {
        	//downloadDocument(response, dto.getDeathCertDocContents(), dto.getCertificateName());
        	
        	
        	try {
				response.setContentType("application/download");
				
				String filename=(String)dto.getRetDeathCertiPath();
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
        	
        	
        	
        	
        	forwardJsp = "viewIndividualIDDetails";
        }
        String par = request.getParameter("Prnt");

        System.out.println("the page is :::"+par);
		if("Y".equalsIgnoreCase(par)){
			WillViewBD viewBD = new WillViewBD();
			WillCommonBD willComBD = new WillCommonBD();
			ArrayList willRemarks = null;
			String StrFunId = funId;

			String formName = eForm.getWillDTO().getWillViewForm();
			WillDepositBD willBD = new WillDepositBD();
			WillDepositDTO formDTO = eForm.getWillDTO();
			String willid1 = formDTO.getWillId();

			WillDepositDTO willDto = eForm.getWillDTO();
			willDto = viewBD.getwillIdDetails(willid1.trim(), StrFunId,lang);
			if("en".equalsIgnoreCase(lang)){
				if("Withdrawn".equalsIgnoreCase(willDto.getWillStatus())){
					willDto.setWill_stat_view("Withdrawn");
				}
				else if("Deposited".equalsIgnoreCase(willDto.getWillStatus())){
					willDto.setWill_stat_view("Deposited");
				}
				else if("Retrieved".equalsIgnoreCase(willDto.getWillStatus())){
					willDto.setWill_stat_view("Retrieved");
				}
				else if("Pending Retrieve".equalsIgnoreCase(willDto.getWillStatus())){
					willDto.setWill_stat_view("Pending Retrieve");
				}
				else if("Pending Withdraw".equalsIgnoreCase(willDto.getWillStatus())){
					willDto.setWill_stat_view("Pending Withdraw");
				}
				else if("Pending Deposit".equalsIgnoreCase(willDto.getWillStatus())){
					willDto.setWill_stat_view("Pending Deposit");
				}
				

			}
			else{
				if("Withdrawn".equalsIgnoreCase(willDto.getWillStatus())){
					willDto.setWill_stat_view("वापस लिया");
				}
				else if("Deposited".equalsIgnoreCase(willDto.getWillStatus())){
					willDto.setWill_stat_view("जमा");
				}
				else if("Retrieved".equalsIgnoreCase(willDto.getWillStatus())){
					willDto.setWill_stat_view("पुनः प्राप्त");
				}
				else if("Pending Retrieve".equalsIgnoreCase(willDto.getWillStatus())){
					willDto.setWill_stat_view("पुनः प्राप्त लंबित");
				}
				else if("Pending Withdraw".equalsIgnoreCase(willDto.getWillStatus())){
					willDto.setWill_stat_view("लंबित वापसी");
				}
				else if("Pending Deposit".equalsIgnoreCase(willDto.getWillStatus())){
					willDto.setWill_stat_view("लंबित जमा");
				}
			}
			willRemarks = willBD.getWillRemarks(willid1.trim());
			willDto.setWillRemarksList(willRemarks);
			eForm.setWillDTO(willDto);
			request.setAttribute("eForm", eForm);

			forwardJsp="printPop";	
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
}
