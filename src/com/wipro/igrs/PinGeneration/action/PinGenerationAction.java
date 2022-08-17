/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :    PinGenerationAction.java
 * Author      :    Nihar Ranjan Mishra 
 * Description :    Represents the Action Class for Pin Generation.
 * ----------------------------------------------------------------------------
 * Version          Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0				Nihar R Mishra  26th Dec, 2007
 * 1.1				Imran Shaik	 	07th Oct, 2008	 
 * --------------------------------------------------------------------------------
 */


package com.wipro.igrs.PinGeneration.action;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.ODServerDetails;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.PinGeneration.bd.PinGenerationBD;
import com.wipro.igrs.PinGeneration.constant.CommonConstants;
import com.wipro.igrs.PinGeneration.dao.PinGenerationDAO;
import com.wipro.igrs.PinGeneration.dto.IGRSCountryDTO;
import com.wipro.igrs.PinGeneration.dto.PinGenerationDTO;
import com.wipro.igrs.PinGeneration.form.PinGenerationForm;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.regCompChecker.constant.RegCompCheckerConstant;
import com.wipro.igrs.regCompChecker.dto.RegCompCheckerDTO;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.util.GUIDGenerator;
import com.wipro.igrs.util.PropertiesFileReader;

/**
 * @author neegaga
 *
 */
public class PinGenerationAction extends BaseAction {

	/**
	 * forwardJsp
	 */
	String forwardJsp = new String(); 	
	private  Logger logger = 
		(Logger) Logger.getLogger(PinGenerationAction.class);
	/** 
	 * Method execute for user registration.
	 * @param mapping 
	 * @param form 
	 * @param request 
	 * @param response 
	 * @return ActionForward
	 * @throws Exception 
	 */

 
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
			HttpServletRequest request, 
			HttpServletResponse response,HttpSession session) 
		throws Exception {
		
		
	//	HttpSession session =request.getSession();
	    String roleId = (String)session.getAttribute("role");
		String funId = (String)session.getAttribute("functionId");
		String userId = (String)session.getAttribute("UserId");
		String frwdName=request.getParameter("fwdName");
		String action=request.getParameter("action");
		session.removeAttribute("pingenerror");
		String language = (String)session.getAttribute("languageLocale");
       PinGenerationForm pinform=(PinGenerationForm)form; 
       
       String actionType = request.getParameter("actionType");
       PinGenerationDTO pinGenerationDTO = pinform.getPinGenerationDTO();
		PinGenerationBD bd = new PinGenerationBD();
		IGRSCountryDTO countryDTO = pinform.getIGRSCountryDTO();
		if(action!=null&&action.equalsIgnoreCase("printPin"))
		{
			 bd.printPin(pinform.getPinGenerationDTO().getSelectedItems(), pinform.getPinGenerationDTO(), request, response);
			 
		}
		else if(action!=null&&action.equalsIgnoreCase("printPin2")){

			 //bd.printPin(pinform.getPinGenerationDTO().getSelectedItems(), pinform.getPinGenerationDTO(), request, response);

			    response.setContentType("application/zip");
			    logger.debug("Inside printPin method");
			   	OutputStream os = response.getOutputStream();
				ZipOutputStream zos = new ZipOutputStream(os);
				String regInitNumber=pinform.getPinGenerationDTO().getRegistrationNumber();
				PinGenerationDAO dao=new PinGenerationDAO();
				ArrayList pinList = dao.pinDetails(pinform.getPinGenerationDTO().getSelectedItems());
				
				try{
				for(int i = 0 ; i < pinList.size() ; i++)
				{
					RegCompCheckerDTO rrdto = (RegCompCheckerDTO)pinList.get(i);
					logger.debug("Inside printPin List >>>"+rrdto);
					String fileFolderPath=RegCompCheckerConstant.FILE_UPLOAD_PATH+regInitNumber+"/";
					 File folder = new File(fileFolderPath);
					 logger.debug("Inside printPin folder path >>>"+folder);
					    if (!folder.exists()) {
					          folder.mkdirs();
					    }
					String fileName="PinDoc"+(i+1)+"_"+rrdto.getPropertyId()+".pdf";
					File fileToDownload = new File(fileFolderPath+fileName);
					 logger.debug("Inside printPin fileToDownload path >>>"+fileToDownload);
					FileOutputStream baos=new FileOutputStream(fileToDownload);
					Document document = new Document(PageSize.A4, 20, 20, 20, 20);
			
					PdfWriter writer = PdfWriter.getInstance(document, baos);
					document.open(); 
					Cell row=new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					row.setHeader(true);
					row.setColspan(25);
					Table datatable = new Table(25);
					datatable.setWidth(100);
					datatable.setPadding(3);
					
					Cell title = new Cell(new Phrase("PIN DOCUMENT", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)));
					title.setHorizontalAlignment(Element.ALIGN_CENTER);
					title.setLeading(20);
					title.setColspan(25);
					title.setBorder(Rectangle.NO_BORDER);
					title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
					datatable.addCell(title);
					datatable.setAlignment(1);
					datatable.addCell(row); 
					
					Cell sectionheader1=new Cell(new Phrase("E- REGISTRATION NUMBER - " + regInitNumber , FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
					sectionheader1.setHorizontalAlignment(Element.ALIGN_CENTER);
					sectionheader1.setColspan(25);
					sectionheader1.setBorder(Rectangle.NO_BORDER);
					datatable.addCell(sectionheader1);
					datatable.setAlignment(1);
					
					Cell line01=new Cell(new Phrase("PROPERTY ID", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
					line01.setColspan(5);
					line01.setBorder(Rectangle.NO_BORDER);
					line01.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
					datatable.addCell(line01);
					datatable.setAlignment(1);
					
					Cell line02=new Cell(new Phrase("PROPERTY TYPE", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
					line02.setColspan(5);
					line02.setBorder(Rectangle.NO_BORDER);
					line02.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
					datatable.addCell(line02);
					datatable.setAlignment(1);
					
					Cell line03=new Cell(new Phrase("DISTRICT", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
					line03.setColspan(5);
					line03.setBorder(Rectangle.NO_BORDER);
					line03.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
					datatable.addCell(line03);
					datatable.setAlignment(1);
					
					Cell line04=new Cell(new Phrase("TEHSIL", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
					line04.setColspan(5);
					line04.setBorder(Rectangle.NO_BORDER);
					line04.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
					datatable.addCell(line04);
					datatable.setAlignment(1);
					
					Cell line05=new Cell(new Phrase("PIN NUMBER", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL)));
					line05.setColspan(5);
					line05.setBorder(Rectangle.NO_BORDER);
					line05.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
					datatable.addCell(line05);
					datatable.setAlignment(1);
					
					Cell line1=new Cell(new Phrase(rrdto.getPropertyId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
					line1.setColspan(5);
					line1.setBorder(Rectangle.NO_BORDER);
					datatable.addCell(line1);
					datatable.setAlignment(1);
					
					Cell line2=new Cell(new Phrase(rrdto.getPropertyTypeName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
					line2.setColspan(5);
					line2.setBorder(Rectangle.NO_BORDER);
					datatable.addCell(line2);
					datatable.setAlignment(1);
					
					Cell line3=new Cell(new Phrase(rrdto.getDistName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
					line3.setColspan(5);
					line3.setBorder(Rectangle.NO_BORDER);
					datatable.addCell(line3);
					datatable.setAlignment(1);
					
					Cell line4=new Cell(new Phrase(rrdto.getTehsilName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
					line4.setColspan(5);
					line4.setBorder(Rectangle.NO_BORDER);
					datatable.addCell(line4);
					datatable.setAlignment(1);
					
					Cell line5=new Cell(new Phrase(rrdto.getPinNumber(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
					line5.setColspan(5);
					line5.setBorder(Rectangle.NO_BORDER);
					datatable.addCell(line5);
					datatable.setAlignment(1);
					
					datatable.setCellsFitPage(true);
					document.add(datatable); 
					document.close();
					baos.close();
					
					ZipEntry entry = new ZipEntry("PinDoc"+(i+1)+"_"+rrdto.getPropertyId()+".pdf");	
					if(fileToDownload.getParentFile().exists()==false)
					{
						fileToDownload.getParentFile().mkdirs();
					}
					FileInputStream fileInputStream = new FileInputStream(fileToDownload);
				    byte[] buf = new byte[(int) fileToDownload.length()];
				    fileInputStream.read(buf);
				    fileInputStream.close();
				    zos.putNextEntry(entry);
				    zos.write(buf);
				    zos.closeEntry(); 
				    }
				    zos.close();
					
		 }catch(IOException ie){
			 System.out.println("File not found exception"+ie);;
		 }
				catch(Exception e ){
					e.printStackTrace();
		 }
			finally{
					if(null!=zos)
						zos.close();
					if(null!=os)os.close();
				}
				
		
		}
		else if(action!=null&&action.equalsIgnoreCase("printPin1")){
			String retPath=bd.printPin1(pinform.getPinGenerationDTO().getSelectedItems(), pinform.getPinGenerationDTO(), request, response);
			 response.setContentType("application/zip");
			 response.setHeader("Content-Disposition", "attachment; filename="
						+ URLEncoder.encode("ZipFile.zip","UTF-8"));
			 File fileToDownload = new File(retPath);
			 FileInputStream fileInputStream = new FileInputStream(retPath);
			   OutputStream out = response.getOutputStream();
			   byte[] buf = new byte[2048];
			   
			   
			   int readNum;
			   while ((readNum=fileInputStream.read(buf))!=-1)
			   {
			      out.write(buf,0,readNum);
			   }
			   fileInputStream.close();
			   out.close();
			
		}
		else if(action!=null&&action.equalsIgnoreCase("printPin3")){
			
			String fileName=bd.printPin1(pinform.getPinGenerationDTO().getSelectedItems(), pinform.getPinGenerationDTO(), request, response);
			response.setContentType("application/zip");   
		       //response.setContentLength(2048);   
		       response.setHeader("Content-Disposition","attachment;filename=\"" + fileName + "\"");   
		       try  
		       {   
		  System.out.println(fileName);
		          ByteArrayOutputStream baos = new ByteArrayOutputStream();   
		          ZipOutputStream zos = new ZipOutputStream(baos);   
		          byte bytes[] = new byte[2048];   
		  
		          FileInputStream fis = new FileInputStream(fileName);   
		          BufferedInputStream bis = new BufferedInputStream(fis);   
		          zos.putNextEntry(new ZipEntry("Zippie.zip"));   
		          int bytesRead;   
		          while ((bytesRead = bis.read(bytes)) != -1)   
		          {   
		            zos.write(bytes, 0, bytesRead);   
		          }   
		  
		           zos.closeEntry();   
		           bis.close();   
		           fis.close();    
		  
		           zos.flush();   
		           baos.flush();   
		           zos.close();   
		           baos.close();   
		  
		           ServletOutputStream op = response.getOutputStream();   
		           op.write(baos.toByteArray());   
		           op.flush();   
		            
		       }catch(IOException ioe)   
		       {   
		           ioe.printStackTrace();   
		       }   

		}
		
		else if(request.getParameter("label123")!= null)
		{
			if(request.getParameter("label123").equalsIgnoreCase("submit123"))
			{
				forwardJsp="pinGenrequest";
			}
			
		}
		else if("regIdSearchNew".equalsIgnoreCase(request.getParameter("fwdName")))
		{
			pinGenerationDTO.setRegNoSearch("");
			pinform.setSearchResultList(null);
			pinGenerationDTO.setCloneSelectedItems(null);
			request.removeAttribute("Enc_Reg_View_Details");
			forwardJsp="regIdSearch";
		}
		else if(request.getParameter("propertyTxnIDOwner")!=null)
		{
			String propertyId=request.getParameter("propertyTxnIDOwner");
			//ArrayList <PinGenerationDTO>ownerDetails=bd.getClaimaintDetails(propertyId);
			ArrayList ownerDetails = bd.getClaimaintDetails(propertyId);
			
			//pinform.getPinGenerationDTO().setOwnerDetails(ownerDetails);
			pinGenerationDTO.setOwnerDetails(ownerDetails);
			//pinform.setOwnerDetails(ownerDetails);
			//request.setAttribute("ownerDetailsList", ownerDetails);
			forwardJsp="ownerDetails";
		}
		else if(request.getParameter("propertyTxnID")!=null)
		{
			String propertyId=request.getParameter("propertyTxnID");
			RegCommonForm regForm =new RegCommonForm();
    		RegCommonBO commonBo = new RegCommonBO();
			regForm.setCommonDeed(commonBo.getCommonDeedFlag(bd.getDeedID(pinGenerationDTO.getRegNoSearch())));
			if(regForm.getDeedID()==RegInitConstant.DEED_ADOPTION || 
    				regForm.getDeedID()==RegInitConstant.DEED_TRUST ||
    				regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE || 
    				regForm.getDeedID()==RegInitConstant.DEED_CANCELLATION_OF_WILL_POA ||
    				regForm.getDeedID()==RegInitConstant.DEED_LEASE_NPV ||
    				regForm.getDeedID()==RegInitConstant.DEED_MORTGAGE_NPV ||
    				regForm.getDeedID()==RegInitConstant.DEED_POA ||
    				regForm.getDeedID()==RegInitConstant.DEED_SETTLEMENT_NPV ||
    				regForm.getDeedID()==RegInitConstant.DEED_WILL_NPV ||
    				regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_LEASE_NPV ||
    				regForm.getDeedID()==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
    				regForm.getDeedID()==RegInitConstant.DEED_SURRENDER_LEASE_NPV ||
    				regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV ||
    				regForm.getDeedID()==RegInitConstant.DEED_PARTITION_NPV ||
    				regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV ||
    				regForm.getDeedID()==RegInitConstant.DEED_COMPOSITION_NPV ||
    				regForm.getDeedID()==RegInitConstant.DEED_LETTER_OF_LICENCE_NPV ||
    				regForm.getDeedID()==RegInitConstant.DEED_SECURITY_BOND_NPV ||
    				regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV ||
    				regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV ||
    				regForm.getCommonDeed()==1){
				regForm.setPropertyId(propertyId);  		
        		regForm.setMapPropertyTransPartyDisp
        		(commonBo.getPropertyDetsForViewConfirmNonProp(regForm.getHidnRegTxnId(), propertyId,language));
        		
        		//regForm.setConfirmationFlag("01");
        			request.setAttribute("reginit", regForm);
        			forwardJsp="propertyView1";
				
			}
			else
			{
				//regForm.setHidnRegTxnId(objEncumBO.getRegTxn(ncForm.getNoEncumDTO().getRegNo()));
	    		regForm.setMapPropertyTransPartyDisp
	    		(commonBo.getPropertyDetsForViewConfirmPingeneration(pinGenerationDTO.getRegNoSearch(), propertyId,language));
	    		
	    		//regForm.setConfirmationFlag("01");
	    		request.setAttribute("reginit", regForm);
	    		forwardJsp="propertyView";
			}
		
		}
		
      
       
      

		else if("setSelectedPropIDs".equals(actionType)) {
        	
        	ArrayList searchResultList = pinform.getSearchResultList();
        	String[] selectedIndexes = request.getParameterValues("chkProperty");
        	ArrayList selectedItems = null;
        	if(selectedIndexes != null && selectedIndexes.length>0) {
        		selectedItems = new ArrayList();
				for (String selectedIndex : selectedIndexes) {
					int index = Integer.parseInt(selectedIndex);
					selectedItems.add(searchResultList.get(index-1));
				}
				pinform.setSearchResultList(null);
				pinGenerationDTO.setSelectedItems(selectedItems);
				pinGenerationDTO.setCloneSelectedItems(selectedItems);
        		//caveatDto.setSelectedItems(selectedItems);
        		session.setAttribute("cvtSearchUpdate","yes");
        	} else {
        		if(language.equalsIgnoreCase("en"))
        			request.setAttribute("error", "Please select at least one Property");
        		else
        			request.setAttribute("error", "कम से कम एक संपत्ति का चयन करें");
        	}
        	
        	pinGenerationDTO.setErrorMsg("FLAG");
        	forwardJsp = "regIdSearch";
        }
		else if (frwdName != null
				&& (frwdName.equalsIgnoreCase("regIdSearch"))) {

			request.removeAttribute("Enc_Reg_View_Details");
			//sdDTO = ncForm.getNoEncumDTO();
			//NoEncumBD sdBD = new NoEncumBD();
			ArrayList regis_view_details = bd.regIdCheckInfo(pinGenerationDTO
					.getRegNoSearch());
			
			
			
			
			if(regis_view_details.size() >0)
			{
				
				pinform.setSearchResultList(regis_view_details );
				request.setAttribute("Enc_Reg_View_Details",
						regis_view_details);
				String regTxnNo = bd.getRegTxnID(pinGenerationDTO.getRegNoSearch());
				
				pinGenerationDTO.setRegTxnNo(regTxnNo);
				
			}
			else
			{
				if(language.equalsIgnoreCase("en"))
        			request.setAttribute("error", "Please enter a valid E-Registration Number");
        		else
        			request.setAttribute("error", "एक वैध ई-पंजीकरण संख्या दर्ज करें");
			}
			
			
			forwardJsp = "regIdSearch";

		}
		else if("createselecteditem".equalsIgnoreCase(request.getParameter("pageName1")))
		{
			if("yes".equals(session.getAttribute("cvtSearchUpdate"))) {
				try {
					
					pinform.getPinGenerationDTO().setSelectedItems(
							(ArrayList) pinform.getPinGenerationDTO().getCloneSelectedItems().clone());
					pinform.getPinGenerationDTO().getCloneSelectedItems().clear();
					session.removeAttribute("cvtSearchUpdate");
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			forwardJsp="pinGenrequest";
		}
		else if("file".equalsIgnoreCase(request.getParameter("showfile"))){
		String pinReqNo=request.getParameter("id");
		
			  bd.readBLOBToFileGet(response,pinReqNo );
			  forwardJsp= "showfile";
		}
		
		else if("mfile".equalsIgnoreCase(request.getParameter("showfile"))){
			String pinReqNo=request.getParameter("id");
			
				  bd.readBLOBToFileGet1(response,pinReqNo );
				  forwardJsp = "showfile";
		}
		
		else if("request".equals(request.getParameter("label"))){
			
			session.removeAttribute("IGRSCountryForm");
			
			ArrayList countrylist = bd.getCountryList(language);
			ArrayList statelist = bd.getStateList();
			ArrayList idlist = bd.getphotoIdList(language);

			countryDTO.setCountryList(countrylist);
			countryDTO.setStateList(statelist);

			pinGenerationDTO.setIdlist(idlist);
			pinGenerationDTO.setRegistrationNumber("");
			pinGenerationDTO.setRemarks("");
			pinGenerationDTO.setDeedDocVerify("");
			pinGenerationDTO.setFirstName("");
			pinGenerationDTO.setLastName("");
			pinGenerationDTO.setMiddleName("");
			pinGenerationDTO.setSelectedItems(null);
			pinGenerationDTO.setFatherName("");
			pinGenerationDTO.setMotherName("");
			pinGenerationDTO.setAge("");
			pinGenerationDTO.setGender("");
			pinGenerationDTO.setSpouseName("");
			pinGenerationDTO.setAddress("");
			pinGenerationDTO.setPostelCode("");
			pinGenerationDTO.setPhoneNumber("");
			pinGenerationDTO.setMobileNumber("");
			pinGenerationDTO.setEmailID("");
			pinGenerationDTO.setIdProofNumber("");
			pinGenerationDTO.setRelation("");
			pinGenerationDTO.setDeathCertificate("");
			pinGenerationDTO.setMutationDocument("");
			pinform.setPinGenerationDTO(pinGenerationDTO);
			pinform.setIGRSCountryDTO(countryDTO);	
			String str=GUIDGenerator.getGUID();
			pinform.getPinGenerationDTO().setRadioApplicent("1");
			pinform.getPinGenerationDTO().setAgentProofPath("d:/upload/15/"+str+"/");
			pinform.getPinGenerationDTO().setAgentProofNameDoc("SUPPORTEDDOC.PDF");
			pinform.getPinGenerationDTO().setAgentProofNameDeath("DEATH.PDF");
			pinform.getPinGenerationDTO().setAgentProofNameMutation("MUTATION.PDF");
			pinform.getPinGenerationDTO().setAgentProofNameRelation("RELATION.PDF");
			pinform.getPinGenerationDTO().setDeathChk("N");
			pinform.getPinGenerationDTO().setRelationChk("N");
			pinform.getPinGenerationDTO().setSuppDocChk("N");
			pinform.getPinGenerationDTO().setDocumentName("");
			pinform.getPinGenerationDTO().setDocumentPath("");
			pinform.getPinGenerationDTO().setDeathName("");
			pinform.getPinGenerationDTO().setDeathPath("");
			pinform.getPinGenerationDTO().setRelationName("");
			pinform.getPinGenerationDTO().setRelationPath("");
			pinform.getPinGenerationDTO().setMutationName("");
			pinform.getPinGenerationDTO().setMutationPath("");
		//	session = request.getSession();
			session.setAttribute("IGRSCountryForm", pinform);
			
			forwardJsp="pinGenrequest";
			return mapping.findForward(forwardJsp);
		}
		
		else if("view".equals(request.getParameter("label"))){
			
			pinGenerationDTO.setOldPIN("");
			pinGenerationDTO.setConfirmPIN("");
			pinGenerationDTO.setPinNum("");
			pinGenerationDTO.setRegistrationNumber("");
			pinGenerationDTO.setPropertyTransId("");
			
			forwardJsp="pinView";
		}
		
		else if ((CommonConstants.PIN_REQUEST_DISTRICT).equalsIgnoreCase(pinform.getPinGenerationDTO().getAction())) {
			
			
			String state = request.getParameter("PINRef");
			String country = pinform.getPinGenerationDTO().getCountry();
		//	 session = request.getSession();
			session.setAttribute("country", country);
			ArrayList districtlist = bd.getDistrictList(state,language);
			countryDTO.setDistrictList(districtlist);
			pinform.setIGRSCountryDTO(countryDTO);
			

			// session.setAttribute("requestForm",requestForm);
			session.setAttribute("IGRSCountryForm", pinform);
			forwardJsp="pinGenrequest";
		}
		else if (("state").equalsIgnoreCase(pinform.getPinGenerationDTO().getAction())) {
			
			
			String country1 = request.getParameter("PINRef2");
			String country = pinform.getPinGenerationDTO().getCountry();
		//	 session = request.getSession();
			session.setAttribute("country", country);
			ArrayList stateList = bd.getStateList(country,language);
			countryDTO.setStateList(stateList);
			pinform.setIGRSCountryDTO(countryDTO);
			

			// session.setAttribute("requestForm",requestForm);
			session.setAttribute("IGRSCountryForm", pinform);
			forwardJsp="pinGenrequest";
		}	
		
			
	   
		else if ((CommonConstants.PIN_PROPERTY).equalsIgnoreCase(pinform.getPinGenerationDTO().getAction())) {
			PinGenerationDTO PinGenerationDTO = pinform.getPinGenerationDTO();
			String regno = request.getParameter("PINRef");
			PinGenerationDTO.setRegistrationNumber(regno);
			ArrayList propertylist = bd.getPropertyList(regno);
			if (propertylist.size() == 0) {
			//	 session = request.getSession();
				session.removeAttribute("pingenerror");
				if(language.equalsIgnoreCase("en"))
					session.setAttribute("pingenerror", "Enter correct Registration Number");
				else
					session.setAttribute("pingenerror", "सही पंजीकरण संख्या दर्ज करें");
				//forwardJsp= "pinGenrequest";
			}
			PinGenerationDTO.setPropertylist(propertylist);
			pinform.setPinGenerationDTO(PinGenerationDTO);
			 
			
			// session.setAttribute("requestForm",requestForm);
			session.setAttribute("IGRSCountryForm", pinform);
			forwardJsp = "pinView";
		}	
		else if ("submitPin".equalsIgnoreCase(pinform.getPinGenerationDTO().getAction())) {
			String status=bd.checkPinStatus(pinform.getPinGenerationDTO());
			if (!status.equalsIgnoreCase("A")) {
		
				session.removeAttribute("pingenerror");
				if(language.equalsIgnoreCase("en"))
					session.setAttribute("pingenerror", "Pin No is deactivated for this property");
				else
					session.setAttribute("pingenerror", "इस संपत्ति के लिए पिन निष्क्रिय कर दिया गया है");
				forwardJsp = "pinView";
			}
			else
			{
				boolean flag=bd.changePin(pinform.getPinGenerationDTO());
				if(flag==false)
				{
					session.removeAttribute("pingenerror");
					if(language.equalsIgnoreCase("en"))
						session.setAttribute("pingenerror", "Please Enter the Correct Old Pin");
					else
						session.setAttribute("pingenerror", "सही पुराना पिन दर्ज करें");
					forwardJsp = "pinView";
				}
				else
				{
					forwardJsp ="pinConfirmsuccess";
				}
			}
			
		}
		else if((CommonConstants.PINGEN1).equalsIgnoreCase(pinform.getPinGenerationDTO().getAction())){
			
			ArrayList pinGen =new ArrayList();
			 //ArrayList list= bd.check(pinform.getPinGenerationDTO().getRegistrationNumber(),pinform.getPinGenerationDTO().getPropertyTransId());
			 PinGenerationDTO dto = pinform.getPinGenerationDTO();
			// dto.setUserID((String)session.getAttribute("UserId"));
			 String bol = bd.generatePin(dto,roleId,funId,userId);
			 pinform.getPinGenerationDTO().setPinReqTxnNo(bol);
			 pinGen.add(pinform);
			// session = request.getSession();
			 session.setAttribute("pinGen", pinGen);
			 logger.debug("Return value:-"+bol);
			 if(!bol.equalsIgnoreCase("")&& bol!=null) {
				forwardJsp="pinGen1";
			 }	 
			 else {
				// session = request.getSession();
				 if(language.equalsIgnoreCase("en"))
					 session.setAttribute("pingenerror", "Your data is not inserted properly. Please contact to system administrator.");
				 else
					 session.setAttribute("pingenerror", "आपका डेटा ठीक से नहीं डाला गया है. सिस्टम प्रशासक को संपर्क करें.");
				 forwardJsp = "pinGenrequest";
			 }
		}
		else if("chkAction".equals(pinform.getPinGenerationDTO().getAction())) {
			String chk = pinform.getPinGenerationDTO().getRadioApplicent();
			String str=GUIDGenerator.getGUID();
			pinform.getPinGenerationDTO().setUniqueId("");
			if("1".equals(chk)) {
				pinform.getPinGenerationDTO().setRadioApplicent("1");
				pinform.getPinGenerationDTO().setAgentProofPath("d:/upload/15/"+str+"/");
				pinform.getPinGenerationDTO().setAgentProofNameDoc("SUPPORTEDDOC.PDF");
				pinform.getPinGenerationDTO().setSelectedItems(null);
				pinform.getPinGenerationDTO().setRegistrationNumber("");
				pinform.getPinGenerationDTO().setRemarks("");
				pinform.getPinGenerationDTO().setDeedDocVerify("");
				pinform.getPinGenerationDTO().setFirstName("");
				pinform.getPinGenerationDTO().setLastName("");
				pinform.getPinGenerationDTO().setMiddleName("");
				pinform.getPinGenerationDTO().setSelectedItems(null);
				pinform.getPinGenerationDTO().setFatherName("");
				pinform.getPinGenerationDTO().setMotherName("");
				pinform.getPinGenerationDTO().setAge("");
				pinform.getPinGenerationDTO().setGender("");
				pinform.getPinGenerationDTO().setSpouseName("");
				pinform.getPinGenerationDTO().setAddress("");
				pinform.getPinGenerationDTO().setPostelCode("");
				pinform.getPinGenerationDTO().setPhoneNumber("");
				pinform.getPinGenerationDTO().setMobileNumber("");
				pinform.getPinGenerationDTO().setEmailID("");
				pinform.getPinGenerationDTO().setIdProofNumber("");
				pinform.getPinGenerationDTO().setRelation("");
				pinform.getPinGenerationDTO().setDeathCertificate("");
				pinform.getPinGenerationDTO().setMutationDocument("");
			}
			if("2".equals(chk)) {
				pinform.getPinGenerationDTO().setAgentProofPath("d:/upload/15/"+str+"/");
				pinform.getPinGenerationDTO().setAgentProofNameDeath("DEATH.PDF");
				pinform.getPinGenerationDTO().setAgentProofNameMutation("MUTATION.PDF");
				pinform.getPinGenerationDTO().setAgentProofNameRelation("RELATION.PDF");
				pinform.getPinGenerationDTO().setRadioApplicent("2");
				pinform.getPinGenerationDTO().setSelectedItems(null);
				pinform.getPinGenerationDTO().setRegistrationNumber("");
				pinform.getPinGenerationDTO().setRemarks("");
			}
			//pinform.getPinGenerationDTO().setSelectedItems(null);
			forwardJsp = "pinGenrequest";
		}
		else if((CommonConstants.VIEW).equalsIgnoreCase(pinform.getPinGenerationDTO().getAction())){
			ArrayList viewlist =new ArrayList();
			viewlist=bd.viewDetails(pinform.getPinGenerationDTO().getPinReqTxnNo(),pinform.getDurationFrom(),pinform.getDurationTo());
		//	session =request.getSession();
			session.setAttribute("viewlist", viewlist);
			if (viewlist.size() == 0) {
				session.setAttribute("pinerror", "No data found. Enter correct id and date");
				return mapping.findForward("pinView");
			}
			forwardJsp = "view1";
		}
		else if("resetAction".equals(pinform.getPinGenerationDTO().getAction()))
		{
			String chk = pinform.getPinGenerationDTO().getRadioApplicent();
			String str=GUIDGenerator.getGUID();
			pinform.getPinGenerationDTO().setUniqueId("");
			if("1".equals(chk)) {
				pinform.getPinGenerationDTO().setRadioApplicent("1");
				pinform.getPinGenerationDTO().setAgentProofPath("d:/upload/15/"+str+"/");
				pinform.getPinGenerationDTO().setAgentProofNameDoc("SUPPORTEDDOC.PDF");
				pinform.getPinGenerationDTO().setSelectedItems(null);
				pinform.getPinGenerationDTO().setRegistrationNumber("");
				pinform.getPinGenerationDTO().setRemarks("");
				pinform.getPinGenerationDTO().setDeedDocVerify("");
				pinform.getPinGenerationDTO().setFirstName("");
				pinform.getPinGenerationDTO().setLastName("");
				pinform.getPinGenerationDTO().setMiddleName("");
				pinform.getPinGenerationDTO().setSelectedItems(null);
				pinform.getPinGenerationDTO().setFatherName("");
				pinform.getPinGenerationDTO().setMotherName("");
				pinform.getPinGenerationDTO().setAge("");
				pinform.getPinGenerationDTO().setGender("");
				pinform.getPinGenerationDTO().setSpouseName("");
				pinform.getPinGenerationDTO().setAddress("");
				pinform.getPinGenerationDTO().setPostelCode("");
				pinform.getPinGenerationDTO().setPhoneNumber("");
				pinform.getPinGenerationDTO().setMobileNumber("");
				pinform.getPinGenerationDTO().setEmailID("");
				pinform.getPinGenerationDTO().setIdProofNumber("");
				pinform.getPinGenerationDTO().setRelation("");
				pinform.getPinGenerationDTO().setDeathCertificate("");
				pinform.getPinGenerationDTO().setMutationDocument("");
			}
			if("2".equals(chk)) {
				pinform.getPinGenerationDTO().setAgentProofPath("d:/upload/15/"+str+"/");
				pinform.getPinGenerationDTO().setAgentProofNameDeath("DEATH.PDF");
				pinform.getPinGenerationDTO().setAgentProofNameMutation("MUTATION.PDF");
				pinform.getPinGenerationDTO().setAgentProofNameRelation("RELATION.PDF");
				pinform.getPinGenerationDTO().setRadioApplicent("2");
				pinform.getPinGenerationDTO().setSelectedItems(null);
				pinform.getPinGenerationDTO().setRegistrationNumber("");
				pinform.getPinGenerationDTO().setRemarks("");
			}
			//pinform.getPinGenerationDTO().setSelectedItems(null);
			forwardJsp = "pinGenrequest";
		}
		else if("upload".equalsIgnoreCase(request.getParameter("fwdName")))
        {
                        pinGenerationDTO.setCompletePath(pinGenerationDTO.getAgentProofPath());
                        
                        if("normal".equalsIgnoreCase(pinGenerationDTO.getDocumentUploaded()))
                        {
                                        pinGenerationDTO.setDocumentName("SUPPORTEDDOC.PDF");
                                        pinGenerationDTO.setDocumentPath(pinGenerationDTO.getAgentProofPath()+"SUPPORTEDDOC.PDF");
                                        pinGenerationDTO.setSuppDocChk("Y");
                        }
                        else if("relation".equalsIgnoreCase(pinGenerationDTO.getDocumentUploaded()))
                                        {
                                        pinGenerationDTO.setRelationName("RELATION.PDF");
                                        pinGenerationDTO.setRelationPath(pinGenerationDTO.getAgentProofPath()+"RELATION.PDF");
                                        pinGenerationDTO.setRelationChk("Y");
                                        }
                        else if("mutation".equalsIgnoreCase(pinGenerationDTO.getDocumentUploaded()))
                                        {
                                        pinGenerationDTO.setMutationName("MUTATION.PDF");            
                                        pinGenerationDTO.setMutationPath(pinGenerationDTO.getAgentProofPath()+"MUTATION.PDF");
                                       
                                        }
                        else  if("death".equalsIgnoreCase(pinGenerationDTO.getDocumentUploaded()))
                                        {
                                        pinGenerationDTO.setDeathName("DEATH.PDF");
                                        pinGenerationDTO.setDeathPath(pinGenerationDTO.getAgentProofPath()+"DEATH.PDF");
                                        pinGenerationDTO.setDeathChk("Y");
                                        }

                        logger.debug("complete path:"+pinGenerationDTO.getCompletePath());
                        pinform.setPinGenerationDTO(pinGenerationDTO);
                        forwardJsp="pinGenrequest";
        }
        
        else if (request.getParameter("dms")!=null)
        {
                        if(request.getParameter("dms").equalsIgnoreCase("downloadFromPath"))
        {
                        String filename = request.getParameter("path").toString();

                           // set the http content type to "APPLICATION/OCTET-STREAM
                           response.setContentType("application/download");

                           // initialize the http content-disposition header to
                           // indicate a file attachment with the default filename
                           // "myFile.txt"
                        //   String fileName = eForm.getDoc();
                           //Filename=\"myFile.txt\"";
                           response.setHeader("Content-Disposition", "attachment; filename="
                                                        + URLEncoder.encode(filename,"UTF-8"));

                           File fileToDownload = new File(filename);
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
        }
                                        forwardJsp="pinGenrequest";

        }

		
		return mapping.findForward(forwardJsp);
  }
}