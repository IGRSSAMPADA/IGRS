package com.wipro.igrs.copyissuance.action;

/**
 * ===========================================================================
 * File           :   IssuanceCopyAction.java
 * Description    :   Represents the Action Class

 * Author         :   Vinay Sharma
 * Created Date   :   Mar 05, 2014

 * ===========================================================================
 */



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentDetails;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.ODServerDetails;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveats.action.CaveatsViewSearchAction;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.copyissuance.bd.CertifiedBD;
import com.wipro.igrs.copyissuance.constant.CommonConstant;
import com.wipro.igrs.copyissuance.dto.CertifiedCopyDetailsDTO;
import com.wipro.igrs.copyissuance.dto.PaymentDTO;
import com.wipro.igrs.copyissuance.form.CertifiedActionForm;
import com.wipro.igrs.copyissuance.rule.CopyIssuanceRule;
import com.wipro.igrs.noEncumbrance.bd.NoEncumBD;
import com.wipro.igrs.util.GUIDGenerator;


public class IssuanceCopyAction extends BaseAction
{
	private static Logger log = org.apache.log4j.Logger.getLogger(IssuanceCopyAction.class);
    String FORWARD_JSP = "";


    /** 
     * @see org.apache.struts.action.Action#execute(ActionMapping, ActionForm, 
     * HttpServletRequest, HttpServletResponse)
     */
    
    
    private String getFileSize(int length) {
		double fileSizeInBytes = length;
		// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
		double fileSizeInKB = fileSizeInBytes / 1024.0;
		// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
		double fileSizeInMB = fileSizeInKB / 1024.0;

		//System.out.println("fileSizeInBytes = "+fileSizeInBytes+" fileSizeInKB = "+fileSizeInKB+" fileSizeInMB = "+fileSizeInMB);
		DecimalFormat decim = new DecimalFormat("#.##");
		Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
		String photoSize="("+fileSize+" MB)";
		return photoSize;
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
	
		private boolean uploadFile(FormFile filetobeuploaded,String fileName,String filePath) {
			 
	        
	        
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

    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws Exception {



         boolean errorFlag = false;
         //String roleId = (String)session.getAttribuute("role"); //Ramesh commented on 12 dec 12
         String roleId = (String)session.getAttribute("loggedInRole");
         //String roleId ="11223344";
         String radio=(String)request.getParameter("radio");
         String funId = (String)session.getAttribute("functionId");
         String userId = (String)session.getAttribute("UserId");
         String officeId=(String)session.getAttribute("loggedToOffice");
         String language = (String)session.getAttribute("languageLocale");
         CertifiedBD objcopy=new CertifiedBD();
         CertifiedActionForm copyform =(CertifiedActionForm)form;
         PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		   String dms=pr.getValue("ImageServerIP");
		   String port=pr.getValue("port");
		   String path = pr.getValue("igrs_upload_path");
		   copyform.getIssuanceDTO().setDms(dms);
		   copyform.getIssuanceDTO().setPort(port);
         String page=null;
	     String certifiedId=null;
	     String radioSelect=null;
	     String radioUpdate=null;
	     radioSelect=request.getParameter("radioSelect");
	     radioUpdate=request.getParameter("radioUpdate");
	     certifiedId=request.getParameter("certifiedIdDash");
	     page=request.getParameter("pageName");
	     String frdName=null;
	     frdName=request.getParameter("fwdName");
	     String modName = (String) request.getParameter("modName");
	     String propertyId=null;
	     propertyId=(String)request.getParameter("propertyTxnID");
	     String acname=null;
	     acname=(String)request.getParameter("actioname");
	     ArrayList errorList1 = new ArrayList();
	     String languageLocale="hi";
			if(session.getAttribute("languageLocale")!=null){
				languageLocale=(String)session.getAttribute("languageLocale");
			}
	//   if (request.getParameter("modName") != null) {
		//	if (request.getParameter("modName").equalsIgnoreCase("Certified copy") && request.getParameter("page").equalsIgnoreCase("dashboard"))
	     /* if(propertyId!=null)
	      {
	    	  ArrayList partyDetails=objcopy.transactingPartyDetails(propertyId);
	    	  request.setAttribute("partyDetails",partyDetails);
	    	  copyform.getIssuanceDTO().setPartyDetailList(partyDetails);
	    	  request.setAttribute("copyForm",copyform);
	    	  return mapping.findForward("TransactingParty");
	      }*/
			
			
			
			
			
			
			 
			
			
			
			
	     if(page!= null )
	        
	      {
	    	 if(page.equalsIgnoreCase("dashboard"))
	        	{

	        		ArrayList pendingApplicationList = objcopy.getPendingApps(session.getAttribute("UserId").toString());
	        			if(pendingApplicationList.size()==0)
						copyform.getObjcopyDet().setPendingApps(null);
					else
						copyform.getObjcopyDet().setPendingApps(pendingApplicationList);
					request.setAttribute("pendingApplicationList", pendingApplicationList);
					return mapping.findForward("DashSceencert");
	        	
	        	
	        	}
	    	
	      else if(page.equalsIgnoreCase("dashboardOnline"))
	      {
	    	  CertifiedActionForm copyForm = (CertifiedActionForm)form;
	     	 CertifiedCopyDetailsDTO dto = copyForm.getIssuanceDTO();   
          	dto.setCopyIssuanceForm("");
          	dto.setIssuanceOnlineAction("");
          	//dto.setDocumentType("");
          	dto.setOnlineBackAction("");
          	dto.setCertifiedId("");
          	dto.setDocumentId("");
          	dto.setRegNo("");
          	dto.setVolume("");
          	dto.setBookNo("");
          	dto.setNum("");
          	dto.setFromRequestDate("");
          	dto.setToRequestDate("");
          	dto.setIssuanceOnlineAction("");
          	//request.removeAttribute("onlineCopyDashList");
              log.debug("Online Form..!!");
             CertifiedBD bd = new CertifiedBD(); 
              
              ArrayList pendingAppAtSR=bd.srDashboardCertified(officeId,dto,languageLocale);
             ArrayList pendingAppAtSRNoencum=bd.srDashboardNoEcumbrance(officeId, dto,languageLocale);
             String searchType=request.getParameter("radioSelect");
            
              copyForm.getIssuanceDTO().setSrDashBoardList(pendingAppAtSR);
              request.setAttribute("onlineCopyDashList",pendingAppAtSR);
              request.setAttribute("onlineCopyDashList1",pendingAppAtSRNoencum);
              FORWARD_JSP = CommonConstant.ONLINE_REQUEST;       
              return mapping.findForward(FORWARD_JSP);
	      }
	      else if(page.equalsIgnoreCase("dashboardStatus"))
	      {
	    	  CertifiedActionForm copyForm = (CertifiedActionForm)form;
	     	 CertifiedCopyDetailsDTO dto = copyForm.getIssuanceDTO();   
          	dto.setCopyIssuanceForm("");
          	dto.setIssuanceOnlineAction("");
          	//dto.setDocumentType("");
          	dto.setOnlineBackAction("");
          	dto.setCertifiedId("");
          	dto.setDocumentId("");
          	dto.setRegNo("");
          	dto.setVolume("");
          	dto.setBookNo("");
          	dto.setNum("");
          	dto.setFromRequestDate("");
          	dto.setToRequestDate("");
          	dto.setIssuanceOnlineAction("");
          	//request.removeAttribute("onlineCopyDashList");
              log.debug("Online Form..!!");
             CertifiedBD bd = new CertifiedBD(); 
              
             ArrayList pendingAppAtSR=bd.srDashboardCertifiedUpdate(officeId,dto);
             ArrayList pendingAppAtSRNoencum=bd.srDashboardNoEcumbranceUpdate(officeId, dto);
             String searchType=request.getParameter("radioUpdate");
            
              copyForm.getIssuanceDTO().setSrDashBoardList(pendingAppAtSR);
              request.setAttribute("onlineCopyDashList",pendingAppAtSR);
              request.setAttribute("onlineCopyDashList1",pendingAppAtSRNoencum);
              FORWARD_JSP = CommonConstant.ISSUANCE_STATUS_SUCCESS;  
              FORWARD_JSP = CommonConstant.REQUEST_STATUS;      
                return mapping.findForward(FORWARD_JSP);
	      }
				}
	     if(radioSelect!=null)
	      {
	    	  CertifiedActionForm copyForm = (CertifiedActionForm)form;
	    	  CertifiedCopyDetailsDTO dto = copyForm.getIssuanceDTO();   
   		 copyform.setRadio(radioSelect);
   		copyForm.getIssuanceDTO().setDocumentType(radioSelect);
   	   CertifiedBD bd = new CertifiedBD(); 
   	
       ArrayList pendingAppAtSR=bd.srDashboardCertified(officeId,dto,languageLocale);
      ArrayList pendingAppAtSRNoencum=bd.srDashboardNoEcumbrance(officeId, dto,languageLocale);
      String searchType=request.getParameter("radioSelect");
     
       copyForm.getIssuanceDTO().setSrDashBoardList(pendingAppAtSR);
       request.setAttribute("onlineCopyDashList",pendingAppAtSR);
       request.setAttribute("onlineCopyDashList1",pendingAppAtSRNoencum);
   		 FORWARD_JSP = CommonConstant.ONLINE_REQUEST;       
             return mapping.findForward(FORWARD_JSP);
	      }
	     if(radioUpdate!=null)
	      {
	    	  CertifiedActionForm copyForm = (CertifiedActionForm)form;
	    	  CertifiedCopyDetailsDTO dto = copyForm.getIssuanceDTO();   
  		 copyform.setRadio(radioUpdate);
  		copyForm.getIssuanceDTO().setDocumentType(radioUpdate);
  	   CertifiedBD bd = new CertifiedBD(); 
  	 ArrayList pendingAppAtSR=bd.srDashboardCertifiedUpdate(officeId,dto);
     ArrayList pendingAppAtSRNoencum=bd.srDashboardNoEcumbranceUpdate(officeId, dto);
      // request.setAttribute("pendingApplication", pendingAppAtSR);
    
      copyForm.getIssuanceDTO().setSrDashBoardList(pendingAppAtSR);
      request.setAttribute("onlineCopyDashList",pendingAppAtSR);
      request.setAttribute("onlineCopyDashList1",pendingAppAtSRNoencum);
      
  		
  		  FORWARD_JSP = CommonConstant.ISSUANCE_STATUS_SUCCESS;  
          FORWARD_JSP = CommonConstant.REQUEST_STATUS;      
            return mapping.findForward(FORWARD_JSP);
	      }
	  if(certifiedId!=null&&!certifiedId.equalsIgnoreCase(""))
	  {	
		  CertifiedCopyDetailsDTO dto=objcopy.getCertifiedCopyDetails(certifiedId,language);	
		  
		  request.setAttribute("refId",certifiedId);
         // request.setAttribute("copyForm",coForm);
          dto.setCopyIssuanceSuccessAction("");
          copyform.setIssuanceDTO(dto);
		  request.setAttribute("copyForm",copyform);
		 // request.setAttribute("PartialPayment","true");
          return mapping.findForward("idClick");
       }
	  
	  if(form!=null)
      {
    	 CertifiedActionForm copyForm = (CertifiedActionForm)form;
    	 CertifiedCopyDetailsDTO dto = copyForm.getIssuanceDTO(); 
    	 
         String pymntParamStatus = (String)request.getParameter("paymentStatus");
        log.debug("you are in action class if part");
        // HttpSession session = request.getSession();
        String feeVal = null;
        String otherFeeVal = null;
        String userType = null;
        String serviceId = null;
        float total = 0;
        CertifiedBD bd = new CertifiedBD(); 
        userType=bd.getUserType(userId);
		if(funId ==  null)
			   {funId = "FUN_011"; }
			   //userType = (String)session.getAttribute("role");//Ramesh commented on 12 dec 12
			 //  userType= (String)session.getAttribute("loggedInRole");
		if(userType ==  null)
			  { userType = "I";   }
	    	   
	    NoEncumBD sdBD=new NoEncumBD();
             
        feeVal = bd.getFee(funId, userType); 
        if(feeVal != null)
			if(!feeVal.equalsIgnoreCase(""))
				 copyForm.getIssuanceDTO().setFee(feeVal);
        	
        otherFeeVal ="0.0"; 
        //bd.getOthersFeeDuty(funId, serviceId, userType)==null?"0.0":bd.getOthersFeeDuty(funId, serviceId, userType);
		log.debug("IssuanceCopyAction -- otherFeeVal ="+otherFeeVal);	
		if(otherFeeVal != null)
		{ 
		copyForm.getIssuanceDTO().setPostalFee(otherFeeVal);		
		total = Float.parseFloat(copyForm.getIssuanceDTO().getFee()==null?"0.0":copyForm.getIssuanceDTO().getFee())+ Float.parseFloat(copyForm.getIssuanceDTO().getPostalFee()==null?"0.0":copyForm.getIssuanceDTO().getPostalFee());
		copyForm.getIssuanceDTO().setTotalFee(String.valueOf(total));
		log.debug("IssuanceCopyAction -- total ="+total);		
		}
		request.setAttribute("fee",feeVal);
		request.setAttribute("otherFee",otherFeeVal);
		request.setAttribute("totalFee",String.valueOf(total));
        
        log.debug("Values are loaded from the database.");
        String refId = null;
        
        FORWARD_JSP = "copyissuance";
        
        
        //from payment igrs action
        //String fromPayment = (String)session.getAttribute("forwardPath");
		if("P".equalsIgnoreCase(pymntParamStatus)){
		     //return from payment action		  
			 copyForm.getIssuanceDTO().setCopyIssuanceSuccessAction(CommonConstant.COPY_ISSUANCE_SUCCESS_ACTION);
		     session.setAttribute("forwardPath","");
		}	 
		
		String frwdName=request.getParameter("fwdName");
	     
	     String pgTitle="";
	     pgTitle= request.getParameter("pageTitle");
	     if(acname!=null)
	     {
	    	 //System.out.println(copyForm.getIssuanceDTO().getRegNo());
 	    	ArrayList property = bd.searchRegId(dto.getTxtReg(),language);
 	    	copyForm.getIssuanceDTO().setSelectedItems(property);
 	    //	System.out.println(property.size());
 	    	request.setAttribute("propertydetails", property);
 	    	FORWARD_JSP="copyissuance";
	     }
	     else if(propertyId!=null)
	      {
	    	  ArrayList partyDetails=objcopy.transactingPartyDetails(propertyId);
	    	 request.setAttribute("partyDetails",partyDetails);
	    	  copyForm.getIssuanceDTO().setPartyDetailList(partyDetails);
	    	  request.setAttribute("copyForm",copyForm);
	    	  FORWARD_JSP="TransactingParty";
	    	  //return mapping.findForward("TransactingParty");
	    	 
	      }
	     else if("clear".equalsIgnoreCase(frwdName))
 	     {    	
	    	
	    	session.removeAttribute("copyForm");
			session.removeAttribute("amount");		    
			session.removeAttribute("refId");
			//Session removed
			session.removeAttribute("bankList");
			session.removeAttribute("challanList");
			session.removeAttribute("functionId");
			session.removeAttribute("commision");
			session.removeAttribute("totAmt");
			session.removeAttribute("checkFlag");
			session.removeAttribute("rowsize");
			session.removeAttribute("status");		    
			session.removeAttribute("failflag");			
			session.removeAttribute("copyForm");
			session.removeAttribute("amount");		    
			session.removeAttribute("refId");
			session.removeAttribute("payId");
			session.removeAttribute("issuance_view_details");
			session.removeAttribute("copy_online");		   
			request.removeAttribute("copy_change");			
	        dto.setAddress("");
	    	 dto.setAge("");
	    	 dto.setAppStatus("");
	    	 dto.setBankAddress("");
	    	 dto.setBankName("");
	    	 dto.setBookNo("");
	    	 dto.setCertifiedId("");
	    	 dto.setChallanAmount("");
	    	 dto.setChallanDate("");
	    	 dto.setRegNo("");
	    	 dto.setRegDate("");
	    	 dto.setVolume("");
	    	 dto.setNum("");
	    	 dto.setNumberDate("");
	    	 dto.setTransPartyFirstName("");
	    	 dto.setTransPartyMidName("");
	    	 dto.setTransPartyLastName("");
	    	 dto.setTransPartySpouseName("");
	    	 dto.setAppSopuse("");
	    	 dto.setTransPartyFGHName("");
	    	 dto.setTransPartyMotherName("");
	    	 dto.setFirstName("");
	    	 dto.setMiddleName("");
	    	 dto.setLastName("");
	    	 dto.setAge("");
	    	 dto.setFatherName("");
	    	 dto.setMotherName("");
	    	 dto.setAddress("");
	    	 dto.setCountryName("");
	    	 dto.setState("");
	    	 dto.setDistrictName("");
	    	 dto.setDistrictName2("");
	    	 dto.setPin("");
	    	 dto.setPhone("");
	    	 dto.setMphone("");
	    	 dto.setEmail("");
	    	 dto.setTypeReq("");
	    	 dto.setPurposeReq("");
	    	 dto.setIssuanceRemark("");
	    	 dto.setIdProof("");
	    	 dto.setIdProofNo("");
	    	 dto.setGender("");
	    	 dto.setGender1("");
	    	 dto.setGender2("");
	    	 dto.setCopyIssuanceSuccessAction("");
	    	 dto.setPaymentSuccessAction("");
	    	 dto.setCopyIssuanceForm("");
	    	 dto.setCopyIssuanceDisplayAction("");
	    	 dto.setIssuanceModifyAction("");
	    	 dto.setCopyIssuanceDisplayAction("");
	    	 dto.setDocumentId("");
	    	 ArrayList country=new ArrayList();		   
			 dto.setCountryList(sdBD.countryBD(language));
		     ArrayList listID = bd.getIDProof(language);
		     copyForm.setIssuanceProof(listID);
		     dto.setStateList(null);
		     dto.setDistrictList(null); 
		     dto.setPinNo("");
		     dto.setValidPin("");
	    	 
 		    FORWARD_JSP = "copyissuance";
 	     }
	     else if (frwdName != null
					&& (frwdName.equalsIgnoreCase("downloadCopyFinal")))
	        {
	        	
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
	             metaDataInfo.setUniqueNo(copyForm.getRegNumberDownloadFinal());
	             metaDataInfo.setType("F");
	             metaDataInfo.setLatestFlag("L");
	             ArrayList<DocumentDetails> docList = docOperations.searchDocs(connDetails, metaDataInfo, null);
	             DocumentDetails docDetails = new DocumentDetails();
	             byte[] bytes =null;
	             for(int i = 0; i < docList.size(); i ++){
	            	 
	            	 String uniqueNumber=copyForm.getRegNumberDownloadFinal();
	            	 String tempDownloadPath=pr.getValue("igrs_upload_path")+File.separator+"IGRS"+File.separator+"CERTIFIED"+File.separator+uniqueNumber+File.separator;
	                 connDetails.setJbossPath(tempDownloadPath);
	               
	                 docDetails = docList.get(i);
	                 System.out.println("Doc Name: "+docDetails.getDocumentName());
	                 if (null==metaDataInfo.getUniqueNo()) {
							session.setAttribute("checkStatus", "DMSError");
							return new ActionForward("/jsp/login/UserRoleValidationPage.jsp");
	     			}
	     			if (metaDataInfo.getUniqueNo().equals("")) {
						session.setAttribute("checkStatus", "DMSError");
						return new ActionForward("/jsp/login/UserRoleValidationPage.jsp");
	     			}
	                if("en".equalsIgnoreCase(language))
	                {	
	                 bytes = docOperations.downloadDocumentWithReqId(connDetails, docDetails, copyForm.getPrintNumber(), "CertifiedCopy", "English");
	                }
	                else
	                {
		                 bytes = docOperations.downloadDocumentWithReqId(connDetails, docDetails, copyForm.getPrintNumber(), "CertifiedCopy", "Hindi");
	
	                }
	                 String filePath=tempDownloadPath+"CERCOPY.PDF";
	                 File downloadedpdf=new File(tempDownloadPath+"CERCOPY.PDF");
	                 try {
	         			FileOutputStream fis=new FileOutputStream(downloadedpdf);
	         			fis.write(bytes);
	         			fis.close();
	         			
	         		} catch (FileNotFoundException e) {
	         			log.debug("Exception while downloading certified copy");
	         			e.printStackTrace();
	         		} catch (IOException e) {
	         			log.debug("Exception while downloading certified copy");
	         			e.printStackTrace();
	         		}
	         		response.setContentType("application/download");

	    			  
	   			   response.setHeader("Content-Disposition", "attachment; filename="
	   						+ URLEncoder.encode(filePath));
	   			try{
	    			  File fileToDownload = new File(filePath);
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
	    			 catch(Exception e)
	    			 {
	    				
	    			 }
	             }
	        }
	     else if("Create".equalsIgnoreCase(frwdName))
 	     {
	    	 dto.setSelectedItems(null);
	    	 dto.setAddress("");
	    	 dto.setAge("");
	    	 dto.setAppStatus("");
	    	 dto.setBankAddress("");
	    	 dto.setBankName("");
	    	 dto.setBookNo("");
	    	 dto.setCertifiedId("");
	    	 dto.setChallanAmount("");
	    	 dto.setChallanDate("");
	    	 dto.setRegNo("");
	    	 dto.setRegDate("");
	    	 dto.setVolume("");
	    	 dto.setNum("");
	    	 dto.setNumberDate("");
	    	 dto.setTransPartyFirstName("");
	    	 dto.setTransPartyMidName("");
	    	 dto.setTransPartyLastName("");
	    	 dto.setTransPartySpouseName("");
	    	 dto.setAppSopuse("");
	    	 dto.setTransPartyFGHName("");
	    	 dto.setTransPartyMotherName("");
	    	 dto.setFirstName("");
	    	 dto.setMiddleName("");
	    	 dto.setLastName("");
	    	 dto.setAge("");
	    	 dto.setFatherName("");
	    	 dto.setMotherName("");
	    	 dto.setAddress("");
	    	 dto.setCountryName("");
	    	 dto.setState("");
	    	 dto.setDistrictName("");
	    	 dto.setDistrictName2("");
	    	 dto.setPin("");
	    	 dto.setPhone("");
	    	 dto.setMphone("");
	    	 dto.setEmail("");
	    	 dto.setTypeReq("");
	    	 dto.setPurposeReq("");
	    	 dto.setIssuanceRemark("");
	    	 dto.setIdProof("");
	    	 dto.setIdProofNo("");
	    	 dto.setCopyIssuanceSuccessAction("");
	    	 dto.setPaymentSuccessAction("");
	    	 dto.setCopyIssuanceForm("");
	    	 dto.setCopyIssuanceDisplayAction("");
	    	 dto.setIssuanceModifyAction("");
	    	 dto.setCopyIssuanceDisplayAction("");
	    	 dto.setGender("");
	    	 dto.setGender1("");
	    	 dto.setGender2("");
	    	 dto.setDocumentId("");
			 ArrayList country=new ArrayList();		   
			 dto.setCountryList(sdBD.countryBD(language));
		     ArrayList listID = bd.getIDProof(language);
		     copyForm.setIssuanceProof(listID);
		     dto.setStateList(null);
		     dto.setDistrictList(null);
		     dto.setPinNo("");
		     dto.setValidPin("");
	 		 FORWARD_JSP = "copyissuance";
 		
 	    }
	 
	     
	 else if(frwdName!=null&&(frwdName.equalsIgnoreCase("regNoSearch")))
	 {
		 CertifiedActionForm coForm = (CertifiedActionForm) session.getAttribute("copyForm");
		 dto.setOldRegNo("");
		 dto.setOldRegDate("");
         request.setAttribute("copyForm",coForm);
		 FORWARD_JSP = new String("RegNoSearch");  
	 }
	     
	 else if(frwdName!=null&&(frwdName.equalsIgnoreCase("OldRegNoSearch")))
	 {
		 CertifiedActionForm coForm = (CertifiedActionForm) session.getAttribute("copyForm");
		 dto.setRegNo("");
         request.setAttribute("copyForm",coForm);
		 FORWARD_JSP = new String("oldregnosearch");  
	 }
	     
	 else if(frwdName!=null&&(frwdName.equalsIgnoreCase("docSearch")))
	 {
		 CertifiedActionForm coForm = (CertifiedActionForm) session.getAttribute("copyForm");
         request.setAttribute("copyForm",coForm);
		 FORWARD_JSP = new String("documentSearch");  
	 }
	 else if(frwdName!=null&&(frwdName.equalsIgnoreCase("State")))
 	 {
		 ArrayList state=new ArrayList();
		 String countryId=request.getParameter("countryId");		 
 		 dto.setStateList(sdBD.stateBD(dto.getCountryId(),language)); 		
 		 dto.setDistrictList(null);
 		 FORWARD_JSP = new String("copyissuance"); 		
 	 }
	 else if(frwdName!=null&&(frwdName.equalsIgnoreCase("District")))
	 	{
		 	 ArrayList district=new ArrayList();
			 String sateId=request.getParameter("countryId");			
			 dto.setDistrictList(sdBD.districtBD(dto.getState(),language));
			 dto.setSubmitstatus("submitting");
	 		 FORWARD_JSP = new String("copyissuance");	 		
	 	}
	 else if(frwdName!=null&&(frwdName.equalsIgnoreCase("genReceipt")))
	 	{
		 CertifiedActionForm coForm = (CertifiedActionForm) session.getAttribute("copyForm");
         request.setAttribute("copyForm",coForm);
         request.setAttribute("refId",dto.getCertifiedId());
         dto.setPaymentSuccessAction("");
         dto.setCopyIssuanceSuccessAction("");	
         FORWARD_JSP = CommonConstant.POPUP;   
	 	}
	 else if(frwdName!=null&&(frwdName.equalsIgnoreCase("validatepinnumber")))
	 	{
		 dto.setCopyIssuanceDisplayAction("");
		 dto.setIssuanceModifyAction(""); 
		 String regNo="";
		 if("Old".equalsIgnoreCase(dto.getDocumentId()))
		 {regNo=dto.getNum();}
		 if("New".equalsIgnoreCase(dto.getDocumentId()))
		 {regNo=dto.getRegNo();}
		 ArrayList pinDetails = bd.getPinSearch(regNo,dto.getPinNo()); 
			 if(pinDetails.size()>0)
			 {
				 dto.setValidPin("V");
			 request.setAttribute("PinStatus", "V");
			 }
			 else
			 {
				 dto.setValidPin("I");
			 request.setAttribute("PinStatus", "I");
			 }
		 FORWARD_JSP = new String("copyissuance");	 	
	 	}
		
	 else if(frwdName!=null&&(frwdName.equalsIgnoreCase("UpdateValpinnumber")))
	 	{
		 dto.setCopyIssuanceDisplayAction("");
		 dto.setIssuanceModifyAction(""); 
		 ArrayList pinDetails = bd.getPinSearch(dto.getRegNo(),dto.getPinNo()); 
			 if(pinDetails.size()>0)
			 {
				 dto.setValidPin("V");
			 request.setAttribute("PinStatus", "V");
			 }
			 else
			 {
				 dto.setValidPin("I");
			 request.setAttribute("PinStatus", "I");
			 }
			 FORWARD_JSP = CommonConstant.MODIFY;	 	
	 	}
	 
	 else if(frwdName==null||"".equalsIgnoreCase(frwdName))  {

        //To Save the Certified Copy Of Issuance into the DB. 		

if("partialPaymentSuccess".equalsIgnoreCase(copyForm.getIssuanceDTO().getPaymentSuccessAction()))
		 {
		CertifiedActionForm coForm = (CertifiedActionForm) session.getAttribute("copyForm");
		dto = coForm.getIssuanceDTO();
	//	System.out.println(dto.getAge());
		String copyRefId = "";
		copyRefId = dto.getCertifiedId();	
		//System.out.println(dto.getBalanceAmount());
		float balance =Float.parseFloat(dto.getBalanceAmount());
		if(copyRefId!="")
        {
            if(balance>0)
            {
            	 
            	String reg_no=dto.getRegNo();
	            String office_id1="";
	            String office_name1="";
	            String district_id1="";
	            String district_name1="";
	            ArrayList parameter=bd.getPaymentParameter(reg_no);
	            if(parameter!=null&&parameter.size()>0)
	            {
	            	ArrayList list=(ArrayList) parameter.get(0);
	            	office_id1=(String) list.get(0);
	            	office_name1=(String) list.get(1);
	            	district_id1=(String) list.get(2);
	            	if(district_id1.length()==1)
	            	{
	            		district_id1="0"+district_id1;
	            	}
	            	district_name1=(String)list.get(3);
	            }
            	CertifiedCopyDetailsDTO cerDTO = coForm.getIssuanceDTO();
 	            String parentKey=bd.setPaymentDetails(dto,funId,userId);
            	cerDTO=bd.getFunctionName(funId);
 	            request.setAttribute("forwardPath", "./CopyIssuance.do?TRFS=NGI");
 	            request.setAttribute("parentTable", "IGRS_CERT_COPY_PAYMENT_DTLS");
 	            request.setAttribute("parentAmount",String.valueOf(balance));
 	           // System.out.println(funId);
 	            request.setAttribute("parentFunId", funId);
 	        //    System.out.println(copyRefId);
 	            request.setAttribute("parentKey",parentKey);
 	       //     System.out.println(cerDTO.getParentModName());
 	            request.setAttribute("parentModName", cerDTO.getParentModName());
 	      //      System.out.println(cerDTO.getParentFunName());
 	            request.setAttribute("parentFunName", cerDTO.getParentFunName());
 	            request.setAttribute("parentColumnName","CERT_PAYMENT_ID");	
 	            //Ramesh added attributes for payment integration on 15 Feb 13
 	            request.setAttribute("parentOfficeId", office_id1);

 	           	request.setAttribute("parentOfficeName", office_name1);

 	           	request.setAttribute("parentDistrictId", district_id1);

               	request.setAttribute("parentDistrictName", district_name1);
               	request.setAttribute("parentReferenceId",copyRefId );
 	            request.setAttribute("refId",copyRefId);
 	            request.setAttribute("copyForm",coForm);
 	           request.setAttribute("formName","copyissuance");
 	            
 	            dto.setPaymentSuccessAction("");
 	            
 	            FORWARD_JSP = CommonConstant.PROCEED_PAYMENT;
 	            return mapping.findForward(FORWARD_JSP);
            }
            else
            {
            	String succMsg="";
                succMsg=bd.updatePaymentInfo(dto);
                if("succ".equalsIgnoreCase(succMsg))
    			{
    				request.setAttribute("refId",dto.getCertifiedId());
	                request.setAttribute("copyForm",coForm);
	                dto.setPaymentSuccessAction("");
	                dto.setCopyIssuanceSuccessAction("");	                
	                //dto.setCopyIssuanceSuccessAction(CommonConstant.COPY_ISSUANCE_SUCCESS_ACTION);
	            	FORWARD_JSP = CommonConstant.CONFIRM;           
                }
    			else
                {
                	  String msg="Data could not saved.";
                	  dto.setCopyIssuanceSuccessAction("");
    	           	  request.setAttribute("CopyStatus", msg);	           	
    	           	  FORWARD_JSP = "copyissuance";
                }
            }
        
        }
		 
		 }
if (CommonConstant.CERTIFIED_ACTION_FORM.equalsIgnoreCase(copyForm.getIssuanceDTO().getCopyIssuanceForm()))
        {
            String actionForm = copyForm.getIssuanceDTO().getCopyIssuanceInsertAction();
            dto = copyForm.getIssuanceDTO();
            PaymentDTO pdto = copyForm.getPayDTO();
            if ("copyInsert".equalsIgnoreCase(actionForm))
            {
                CopyIssuanceRule rule = new CopyIssuanceRule();
                ArrayList errorList = rule.validateCopyIssuanceRule(copyForm);
                log.debug("Outside error....");
                rule.setError(false);
                if (rule.isError())
                {
                    FORWARD_JSP = "copyissuance";
                    request.setAttribute(CommonConstant.COPY_ISSUANCE_ERROR_FLAG, "true");
                    request.setAttribute(CommonConstant.COPY_ISSUANCE_ERROR_LIST, errorList);
                    log.debug("Inside error....");
                } else
                {
                    log.debug("Getting DTO Objects....");
                    boolean issuance_challan = bd.insertChallanPayment(copyForm.getPaymentList(),roleId,funId,userId);
                    request.setAttribute(CommonConstant.COPY_ISSUANCE_ERROR_FLAG, "false");
                    request.setAttribute(CommonConstant.COPY_ISSUANCE_ERROR_LIST, null);
                    FORWARD_JSP = "confirm";
                    log.debug("Successfully inserted into the database.");
                }
            }
        }

        
        if (CommonConstant.ICOPY_REG_VAL_SRCH.equalsIgnoreCase(copyForm.getIssuanceDTO().getSearchAction()))
        {
        	dto.setCopyIssuanceDisplayAction("");
        	request.removeAttribute("Copy_Reg_View_Details");//session.removeAttribute("Copy_Reg_View_Details");        	
        	request.removeAttribute("copyMsg");
        	dto = copyForm.getIssuanceDTO();              
            dto.setSearchAction("");            
            ArrayList regis_view_details = bd.getRegistrationSearch(dto.getTxtReg());           
            request.setAttribute("Copy_Reg_View_Details",regis_view_details);//session.setAttribute("Copy_Reg_View_Details",regis_view_details); 
           
            if(officeId==null || officeId.equalsIgnoreCase(""))
            {
            if(regis_view_details.size()>0)
            {
            
            	CertifiedCopyDetailsDTO   dto1=(CertifiedCopyDetailsDTO) regis_view_details.get(0);
            	 int deedid=Integer.parseInt(dto1.getDeedId());
            	if(deedid == 1084 ||deedid == 1014 ||deedid == 1003 ||deedid == 1037 ||deedid == 1048 ||deedid ==1038 ||deedid ==1064)	
            	{
            		request.setAttribute("copyMsg","Online request is disabled for " +dto1.getDeedType());
            	}
            /* else
            	{
            		System.out.println(copyForm.getIssuanceDTO().getRegNo());
        	    	ArrayList property = bd.searchRegId(dto.getTxtReg());
        	    	copyForm.getIssuanceDTO().setSelectedItems(property);
        	    	System.out.println(property.size());
        	    	request.setAttribute("propertydetails", property);
            	}*/
            }
            }
            
         FORWARD_JSP = CommonConstant.ICOPY_REG_VIEWDTLS;
        }
        if (CommonConstant.ICOPY_OLD_REG_VAL_SRCH.equalsIgnoreCase(copyForm.getIssuanceDTO().getSearchAction()))
        {
        	dto.setCopyIssuanceDisplayAction("");
        	request.removeAttribute("Copy_OldReg_View_Details");
        	//session.removeAttribute("Copy_OldReg_View_Details");        	
            dto = copyForm.getIssuanceDTO();              
            dto.setSearchAction("");            
            ArrayList regis_view_details = bd.getOldRegistrationSearch(dto.getTxtReg());           
           // session.setAttribute("Copy_OldReg_View_Details",regis_view_details); 
            request.setAttribute("Copy_OldReg_View_Details",regis_view_details); 
           
           if(officeId==null || officeId.equalsIgnoreCase(""))
           { if(regis_view_details.size()>0)
            {
            
            dto=(CertifiedCopyDetailsDTO) regis_view_details.get(0);
            if(dto.getDeedType().equalsIgnoreCase("Adoption Deed" )||dto.getDeedType().equalsIgnoreCase("Partnership "))	
            	{
            		request.setAttribute("copyMsg","Online request is disabled for   " +dto.getDeedType());
            	}
            }
            
           }
            FORWARD_JSP = CommonConstant.ICOPY_OLD_REG_VIEWDTLS;
        }
        
        if (CommonConstant.ICOPY_DOCUMENT_VAL_SRCH.equalsIgnoreCase(copyForm.getIssuanceDTO().getSearchAction()))
        {
        	dto.setCopyIssuanceDisplayAction("");
        	request.removeAttribute("Document_View_Details");
        	//session.removeAttribute("Document_View_Details");        	
            dto = copyForm.getIssuanceDTO();              
            dto.setSearchAction("");            
            ArrayList regis_view_details = bd.getRegistrationSearch(dto.getDocReg());           
           request.setAttribute("Document_View_Details",regis_view_details);
            // session.setAttribute("Document_View_Details",regis_view_details);             
            FORWARD_JSP = CommonConstant.ICOPY_DOCUMENT_VAL_DETAILS;
        }

        if (CommonConstant.COPY_ISSUANCE_DISPLAY.equalsIgnoreCase(copyForm.getIssuanceDTO().getCopyIssuanceDisplayAction()))
        {
            dto = copyForm.getIssuanceDTO();
            String copyRefId = "";
            //copyRefId=dto.getCertifiedId();
         //   System.out.println(dto.getCertifiedId());
            if(dto.getCertifiedId().equals(""))
            {
            copyRefId = bd.submitCertifiedInfo(dto,roleId,funId,userId);
            dto.setCertifiedId(copyRefId);
            }
            else
            {
            	copyRefId=dto.getCertifiedId();
            	bd.modifyDetails(dto);
            }
            
            //String countryArr[]=dto.getCountry().split("~");	
            // dto.setCountryId(countryArr[0]);
            // dto.setCountry(countryArr[1]);
            //String stateArr[]=dto.getState().split("~");	
            //dto.setStateId(stateArr[0]);
            //dto.setState(stateArr[1]);
            //String cityArr[]=dto.getCity().split("~");	
            //dto.setCityId(cityArr[0]);
            //dto.setCity(cityArr[1]);
       //    System.out.println(dto.getCountryId());
       //    System.out.println(dto.getStateId());
       //    System.out.println(dto.getDistrictName());
           String coun=bd.getCountryName(dto.getCountryId(),language);
           dto.setCountry(bd.getCountryName(dto.getCountryId(),language));
            dto.setStateName(bd.getStateName(dto.getStateId(),language));
            dto.setDistrictName2(bd.getDistrictName(dto.getDistrictName(),language));
            if("M".equalsIgnoreCase(dto.getHidGender())){
            	if("en".equalsIgnoreCase(language))
            	{	
            	dto.setGender("Male");
            	}
            	else
            	{
                dto.setGender("पुरुष");
 		
            	}
            	}            
            if("F".equalsIgnoreCase(dto.getHidGender())){
            	if("en".equalsIgnoreCase(language))
            	{	
            	dto.setGender("Female");
            	}
            	else
            	{
                dto.setGender("महिला");
 		
            	}
        
            	}
            String reId = request.getParameter("reId");
            request.setAttribute("copyForm",copyForm); //Ramesh comented on 17Dec12
            dto.setCopyIssuanceDisplayAction("");
            FORWARD_JSP = CommonConstant.DISPLAY_SUCCESS;
            
        }


        // To modify the copy issuance details.
        if (CommonConstant.COPY_ISSUANCE_MODIFY_FORM.equalsIgnoreCase(copyForm.getIssuanceDTO().getIssuanceModifyAction()))
        {
            log.debug("Inside Modify");
            //dto = copyForm.getIssuanceDTO();
            String reId = request.getParameter("reId");
            dto.setCountryList(sdBD.countryBD(language));
            dto.setStateList(sdBD.stateBD(dto.getCountryId(),language));
            dto.setDistrictList(sdBD.districtBD(dto.getState(),language));
            if("".equalsIgnoreCase(dto.getDocumetId1()))
            		{dto.setDocumentId("Old");}
            if("N".equalsIgnoreCase(dto.getDocumetId1()))
            {dto.setDocumentId("New");}
            //HttpSession requestSession=request.getSession(true);
            CertifiedActionForm coForm = (CertifiedActionForm) session.getAttribute("copyForm");
            session.setAttribute("copyForm",coForm);
            dto.setIssuanceModifyAction("");
           FORWARD_JSP = CommonConstant.MODIFY;
            //FORWARD_JSP ="copyissuance";
        }


        if (CommonConstant.PAYMENT_SUCCESS_ACTION.equalsIgnoreCase(copyForm.getIssuanceDTO().getPaymentSuccessAction()))
        {
        	CertifiedActionForm coForm = (CertifiedActionForm) session.getAttribute("copyForm");
            dto = coForm.getIssuanceDTO();
            String copyRefId = "";
            copyRefId=dto.getCertifiedId();
            //copyRefId = bd.submitCertifiedInfo(dto,roleId,funId,userId);
            if(copyRefId!="")
            {
	            if(total>0)
	            {
		            CertifiedCopyDetailsDTO cerDTO = coForm.getIssuanceDTO();
		            String reg_no=dto.getRegNo();
		            String office_id1="";
		            String office_name1="";
		            String district_id1="";
		            String district_name1="";
		            ArrayList parameter=bd.getPaymentParameter(reg_no);
		            if(parameter!=null&&parameter.size()>0)
		            {
		            	ArrayList list=(ArrayList) parameter.get(0);
		            	office_id1=(String) list.get(0);
		            	office_name1=(String) list.get(1);
		            	district_id1=(String) list.get(2);
		            	if(district_id1.length()==1)
		            	{
		            		district_id1="0"+district_id1;
		            	}
		            	district_name1=(String)list.get(3);
		            }
		            cerDTO=bd.getFunctionName(funId);
		            cerDTO.setCertifiedId(copyRefId);
		            cerDTO.setBalanceAmount(String.valueOf(total));
		            String parentKey=bd.setPaymentDetails(cerDTO,funId,userId);
		             //Ramesh added attributes for payment integration on 15 Feb 13
		            request.setAttribute("forwardPath", "./CopyIssuance.do?TRFS=NGI");
		            request.setAttribute("parentTable", "IGRS_CERT_COPY_PAYMENT_DTLS");
		            request.setAttribute("parentAmount",String.valueOf(total));
		            request.setAttribute("parentFunId", funId);
		            request.setAttribute("parentKey", parentKey);
		            request.setAttribute("parentModName", cerDTO.getParentModName());
		            request.setAttribute("parentFunName", cerDTO.getParentFunName());
		            request.setAttribute("parentColumnName", "CERT_PAYMENT_ID");	
		            //Ramesh added attributes for payment integration on 15 Feb 13
		            request.setAttribute("parentOfficeId", office_id1);

                    request.setAttribute("parentOfficeName", office_name1);

                    request.setAttribute("parentDistrictId", district_id1);

                    request.setAttribute("parentDistrictName", district_name1);
                    request.setAttribute("parentReferenceId",copyRefId );


		            request.setAttribute("refId",copyRefId);
		            request.setAttribute("copyForm",coForm);
		 	        request.setAttribute("formName","copyissuance");

		            dto.setPaymentSuccessAction("");
		            FORWARD_JSP = CommonConstant.PROCEED_PAYMENT;
	            }
	            else
	            {     
	            	
	            	String succMsg="";
	                succMsg=bd.updatePaymentInfo(dto);
	               
	    			if("succ".equalsIgnoreCase(succMsg))
	    			{
	    				request.setAttribute("refId",dto.getCertifiedId());
		                request.setAttribute("copyForm",coForm);
		                dto.setPaymentSuccessAction("");
		                dto.setCopyIssuanceSuccessAction("");	                
		                //dto.setCopyIssuanceSuccessAction(CommonConstant.COPY_ISSUANCE_SUCCESS_ACTION);
		            	FORWARD_JSP = CommonConstant.CONFIRM;           
	                }
	    			else
	                {
	                	  String msg="Data could not saved.";
	                	  dto.setCopyIssuanceSuccessAction("");
	    	           	  request.setAttribute("CopyStatus", msg);	           	
	    	           	  FORWARD_JSP = "copyissuance";
	                }
	    			
	    			
	                
	            }
            
             }
            else //failed to insert into DB
	         {
            	    dto.setPaymentSuccessAction("");
	        	    String msg="There is some network problem,Please try after some time.";
	            	request.setAttribute("CopyStatus", msg); 
	            	FORWARD_JSP = "copyissuance";
	         }
        }



        if (CommonConstant.PREVIOUS_ACTION.equalsIgnoreCase(copyForm.getIssuanceDTO().getBackAction()))
        {
            log.debug("Inside Previous Payment");
            //HttpSession requestSession=request.getSession(true);
            CertifiedActionForm coForm = (CertifiedActionForm) session.getAttribute("copyForm");
            request.setAttribute("copyForm",coForm);
            FORWARD_JSP = CommonConstant.PREVIOUS_PAYMENT;
        }



        if (CommonConstant.PAYMENT_VALIDATE.equalsIgnoreCase(copyForm.getPayDTO().getValidPayment()))
        {
            log.debug("Inside Payment Validate");
            PaymentDTO dto1 = copyForm.getPayDTO();
           // HttpSession requestSession=request.getSession(true);
            CertifiedActionForm coForm = (CertifiedActionForm) session.getAttribute("copyForm");
            String challanNo = dto1.getChallanNo();
            String challanDt = dto1.getChallanDate();
            String challanAmt = dto1.getAmount();
            String bnkName = dto1.getBankName();
            SimpleDateFormat stringFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            try
            {
                date = stringFormat.parse(challanDt);
                log.debug("Date="+date);
            } catch (ParseException pe)
            {
                log.debug("ParseException: " + pe);
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
            String challanDate = null;
            try
            {
                challanDate = dateFormat.format(date);
            } catch (Exception e)
            {
                log.debug("Error getting date field in 'M/d/yyyy' format: " +e); 
            }
            ArrayList issuance_payment = bd.validatePayment(challanNo,challanDate,challanAmt,bnkName);
            Iterator itr = issuance_payment.iterator();
            log.debug("LIST="+issuance_payment.size());
            while (itr.hasNext())
            {
                log.debug("Inside While");
                dto1 = (PaymentDTO) itr.next();
            }   
            if (issuance_payment.size()!=0)
            {
                log.debug("SIZE");
                coForm.setPayDTO(dto1);
                FORWARD_JSP = CommonConstant.VIEW_DISPLAY;
            } else
            {
                String[] params = {challanNo,challanDt,challanAmt,bnkName};
                request.setAttribute("paymtParam",params);
                FORWARD_JSP = CommonConstant.ERROR;
            }
            request.setAttribute("copyForm",coForm);        
        }



        if (CommonConstant.COPY_ISSUANCE_CHALLAN.equalsIgnoreCase(copyForm.getIssuanceDTO().getCopyChallanAction()))
        {
           // HttpSession requestSession=request.getSession(true);
            CertifiedActionForm coForm = (CertifiedActionForm) session.getAttribute("copyForm");
            request.setAttribute("copyForm",coForm);
            FORWARD_JSP = CommonConstant.CHALLAN;
        }


        // To get the reference Id in confirm page.                
        if (CommonConstant.COPY_ISSUANCE_SUCCESS_ACTION.equalsIgnoreCase(copyForm.getIssuanceDTO().getCopyIssuanceSuccessAction()))
        {
            //HttpSession requestSession=request.getSession(true);
            CertifiedActionForm coForm = (CertifiedActionForm) session.getAttribute("copyForm");
            //dto = coForm.getIssuanceDTO();
            //PaymentDTO payDTO = coForm.getPayDTO();
            //payDTO.setPaymentTxnId((String)session.getAttribute("status"));
            //refId = bd.submitCertifiedInfo(dto,payDTO,roleId,funId,userId,coForm.getFilePhoto(),coForm.getFileSignature(),coForm.getFileThumb());
            //refId = bd.submitCertifiedInfo(dto,payDTO,roleId,funId,userId);
                      
         
          //  String succMsg="";
          //  succMsg=bd.updateICopyInfo(dto,pymntParamStatus);
            	double balance=bd.isCompletePayment(dto.getCertifiedId());
            if(balance<=0)
			{
            		String succMsg="";
            		succMsg=bd.updateICopyInfo(dto,pymntParamStatus);   
            		if("succ".equalsIgnoreCase(succMsg))
        			{
        				    request.setAttribute("refId",dto.getCertifiedId());
        		            request.setAttribute("copyForm",coForm);
        		            String msg="";
        		            if("en".equalsIgnoreCase(language))
        		            {	
        		            msg="Your Transaction is Complete";
        		            }
        		            else
        		            {
        		            msg="भुगतान  की प्रक्रिया सम्पन्न हो गयी है। ";	
        		            }
        		            request.setAttribute("copyStatus", msg);
        		            dto.setCopyIssuanceSuccessAction("");
        		            FORWARD_JSP = CommonConstant.CONFIRM;	            
                    }
            		else
                    {
        				//String msg="Payment done balance left is:";
        			      String msg="Payment status could not updated.";
                    	  dto.setCopyIssuanceSuccessAction("");
        	           	  request.setAttribute("CopyStatus", msg);	           	
        	           	  FORWARD_JSP = CommonConstant.CONFIRM;	
                    }	            
            		
			}
          	else
            {
          			String msg="Payment done balance left is:"+balance;
          			dto.setCopyIssuanceSuccessAction("");
          			request.setAttribute("copyStatus", msg);	 
          			request.setAttribute("refId",dto.getCertifiedId());
          			request.setAttribute("copyForm",coForm);
	            	dto.setCopyIssuanceSuccessAction("");
	            	FORWARD_JSP = CommonConstant.CONFIRM;	 
          	}
			
			
        }


        	/*

        // To View the Copy Issuance Popup page
        String pop = request.getParameter("pop");
        if (pop!=null)
        {
            if (pop.equals("true"))
            {
              //  HttpSession requestSession=request.getSession(true);
                CertifiedActionForm coForm = (CertifiedActionForm) session.getAttribute("copyForm");
                request.setAttribute("copyForm",coForm);
                request.setAttribute("refId",session.getAttribute("refId"));
                log.debug("Popup Form..!!");
                FORWARD_JSP = CommonConstant.POPUP;   
            }
        }

 		*/ 								//Ramesh commented on 21 Dec12
        
        
        // To View the Copy Issuance Input page
        String copyview = request.getParameter("copyview");
        if (copyview!=null)
        {
            if (copyview.equals("true"))
            {
            	dto.setIssuanceViewDetailsAction("");
               // session.removeAttribute("copyViewList");
                request.removeAttribute("copyViewList");
                dto.setCertifiedId("");
                dto.setAppStatus("");
                dto.setFromRequestDate("");
                dto.setToRequestDate("");
                dto.setRegNo("");
                dto.setCopyIssuanceForm("");
                FORWARD_JSP = CommonConstant.COPY_VIEW;       
            }
        }



        // To get the copy issuance details from the DB for a particular Reference Id.				 
        if (CommonConstant.ISSUANCE_VIEW_ID_DETAILS.equalsIgnoreCase(pgTitle))
        {
            log.debug("Actions are matched for CERTIFIED ID Details");
            log.debug("CertifiedID is matched.....");
            CertifiedCopyDetailsDTO dto1 = copyForm.getIssuanceDTO();
            String certifiedid1 = (String)request.getParameter("certifiedId");            
            session.setAttribute("refId",certifiedid1);
            String pay = (String)request.getParameter("payId");
          //  HttpSession requestSession=request.getSession(true);
            session.setAttribute("refId",certifiedid1);
            session.setAttribute("payId",pay);
            log.debug("Request certified Id:-"+certifiedid1);
            if (certifiedid1 != null)
            {
                dto1 = bd.getCopyIssuance(certifiedid1,language);                
                copyForm.setIssuanceDTO(dto1);  
                request.setAttribute("copyForm",copyForm);
                FORWARD_JSP = CommonConstant.VIEW_DETAILS_SUBMIT;
            }
        }



        // To get the copy issuance view list corresponding to the refid,status,fromdate and todate.				 
        if (CommonConstant.ISSUANCE_VIEW_DETAILS.equalsIgnoreCase(copyForm.getIssuanceDTO().getCopyIssuanceForm()))
        {
           
        	
      	log.debug("Forms are matched.......");
            String issuance_view = copyForm.getIssuanceDTO().getIssuanceViewDetailsAction();
            log.debug(issuance_view);
            if ("issuanceViewDetails".equals(issuance_view))
            {
		//session=request.getSession(true);
            	
            	log.debug("Actions are matched for Copy Issuance View Details w:-  "+issuance_view);
                dto = copyForm.getIssuanceDTO();
                String referId = dto.getCertifiedId();
                String tName = dto.getAppStatus();
                String fromDate = dto.getFromRequestDate();
                String toDate = dto.getToRequestDate();
                String reNo = dto.getRegNo();
                
                copyForm = bd.getIssuanceViewSearch(copyForm,dto,languageLocale);
                //copyForm = bd.getIssuanceOnlineSearch(copyForm,fromDate,toDate,referId,regNo);
                request.setAttribute("copyViewList", copyForm.getCopyViewList());
               // session.setAttribute("copyViewList", copyForm.getCopyViewList());
                log.debug("view List:"+copyForm.getCopyViewList().size());
                String chk = "cpview";
                String oldregno1 = dto.getVolume();
                String oldregno2 = dto.getBookNo();   
                String oldregno3 = dto.getNum();
               
              
               
			FORWARD_JSP = CommonConstant.ISSUANCE_VIEW_SUCCESS;  
			
               
            }
        }

        if ("issuanceViewFormback".equalsIgnoreCase(copyForm.getIssuanceDTO().getCopyIssuanceForm()))
        {
        	
        	//System.out.println("");
        	FORWARD_JSP = CommonConstant.ISSUANCE_VIEW_SUCCESS;   
        }

        // To get the request status
        String issuance = request.getParameter("issuance");
        if (issuance!=null)
        {
            if (issuance.equals("true"))
            {
                log.debug("Forms are matched..!!");
                ArrayList issuanceStatusList = bd.getIssuanceStatus();
                copyForm.setCopyStatus(issuanceStatusList);
                request.setAttribute("issuanceStatusList",issuanceStatusList);
                     
            }
        }

        

        // To View the Copy Issuance Online Request Input 
        String onlineRequest = request.getParameter("onlineRequest");
        if (onlineRequest!=null)
        {
            if (onlineRequest.equals("true"))
            {
            	dto.setCopyIssuanceForm("");
            	dto.setIssuanceOnlineAction("");
            	dto.setDocumentType("");
            	dto.setOnlineBackAction("");
            	dto.setCertifiedId("");
            	dto.setDocumentId("");
            	dto.setRegNo("");
            	dto.setVolume("");
            	dto.setBookNo("");
            	dto.setNum("");
            	dto.setFromRequestDate("");
            	dto.setToRequestDate("");
            	dto.setIssuanceOnlineAction("");
            	request.removeAttribute("onlineCopyDashList");
                log.debug("Online Form..!!");
               /* ArrayList pendingAppAtSR=bd.srDashboardCertified(officeId,dto);
               ArrayList pendingAppAtSRNoencum=bd.srDashboardNoEcumbrance(officeId, dto);
                
              
                copyForm.getIssuanceDTO().setSrDashBoardList(pendingAppAtSR);
                request.setAttribute("onlineCopyDashList",pendingAppAtSR);
                request.setAttribute("onlineCopyDashList1",pendingAppAtSRNoencum);*/
                FORWARD_JSP = CommonConstant.ONLINE_REQUEST;       
            }
        }


        // To get the copy issuance list according to the fromdate and todate.					  
        if (CommonConstant.COPY_ISSUANCE_ONLINE_FORM.equalsIgnoreCase(copyForm.getIssuanceDTO().getCopyIssuanceForm()) && pgTitle==null)
        {
            log.debug("Actions are matched!!!!");
            String issuance_online = copyForm.getIssuanceDTO().getIssuanceOnlineAction();
            log.debug(issuance_online);
           // if ("issuanceOnline".equals(issuance_online))
            //{
              //  session=request.getSession(true);
                log.debug("Actions are matched....");
                dto = copyForm.getIssuanceDTO();
                dto.setIssuanceOnlineAction("");
                dto.setIssuanceOnlineAction("");
                copyForm.setOnlineCopyViewList(null);
                copyForm.setOnlineCopyViewList1(null);
                String fromDate = dto.getFromRequestDate();
                String toDate = dto.getToRequestDate();
                String referId = dto.getCertifiedId();
                String regNo = dto.getRegNo();
                String appstatus=dto.getAppStatus();
                String type=dto.getDocumentType();
                //ArrayList copy_online = bd.getIssuanceOnlineSearch(fromDate,toDate,referId,regNo); //Ramesh commented on 19 Jan13
               if(dto.getDocumentType().equalsIgnoreCase("Yes"))
                {
            	   copyForm = bd.getIssuanceOnlineSearch1(copyForm,fromDate,toDate,referId,regNo,officeId,language);
            	   request.setAttribute("onlineCopyList", copyForm.getOnlineCopyViewList());
            	   String chk = "online";
                   String oldregno1 = dto.getVolume();
                   String oldregno2 = dto.getBookNo();   
                   String oldregno3 = dto.getNum();
            	   dto.setIssuanceOnlineAction("");
                   dto.setIssuanceOnlineAction("");               
                   FORWARD_JSP = CommonConstant.ONLINE_SUCCESS;
                }
               else
               {
            	   copyForm = bd.getIssuanceOnlineSearchNoEncum1(copyForm,fromDate,toDate,referId,regNo,officeId,language);
            	   request.setAttribute("onlineCopyList1", copyForm.getOnlineCopyViewList1());
            	   String chk = "online";
                   String oldregno1 = dto.getVolume();
                   String oldregno2 = dto.getBookNo();   
                   String oldregno3 = dto.getNum();
            	   dto.setIssuanceOnlineAction("");
                   dto.setIssuanceOnlineAction("");               
                   FORWARD_JSP ="requestNoEncum";
               } 
                String chk = "online";
                String oldregno1 = dto.getVolume();
                String oldregno2 = dto.getBookNo();   
                String oldregno3 = dto.getNum();
                /*if(copy_online.size()==0)
                {
                  request.setAttribute("referId",referId);
                  request.setAttribute("reNo",regNo);
                  request.setAttribute("che",chk);
                  request.setAttribute("fromDate",fromDate);
                  request.setAttribute("toDate",toDate);
                  request.setAttribute("oldregno1",oldregno1);
                  request.setAttribute("oldregno2",oldregno2);
                  request.setAttribute("oldregno3",oldregno3);
                  FORWARD_JSP = CommonConstant.ISSUANCE_ERROR; 
                } 

                CopyIssuanceRule rRule = new CopyIssuanceRule();
                ArrayList errorList = rRule.validateIssuanceRecord(copy_online);
                if (rRule.isError())
                {
                    request.setAttribute(CommonConstant.COPY_ISSUANCE_ERROR_FLAG, "true");
                    request.setAttribute(CommonConstant.COPY_ISSUANCE_ERROR_LIST, errorList);
                } else
                {
                    if (copy_online.size()!=0)
                    {
                        for (int i=0;i<copy_online.size();i++)
                        {
                            CertifiedCopyDetailsDTO dt = (CertifiedCopyDetailsDTO) copy_online.get(i);
                            log.debug("first Name:-"+dt.getFirstName());
                        }
                    }
                    
                    if (copy_online.size()!=0)
                    {
                        for (int i=0;i<copy_online.size();i++)
                        {
                            CertifiedCopyDetailsDTO d = (CertifiedCopyDetailsDTO)copy_online.get(i);
                            log.debug("Action:-"+d.getCertifiedId()+":"+copy_online.get(i));
                        }
                        session.setAttribute("copy_online",copy_online);
                        log.debug("Values are set to the DT objects...");
                        FORWARD_JSP = CommonConstant.ONLINE_SUCCESS;

                    }
                    
                }*/
                dto.setIssuanceOnlineAction("");
                dto.setIssuanceOnlineAction("");               
               /* FORWARD_JSP = CommonConstant.ONLINE_SUCCESS;*/
            
        }



        // To get the copy issuance details with the remarks field to update.
        //if (CommonConstant.COPY_ISSUANCE_UPDATE_FORM.equalsIgnoreCase(copyForm.getIssuanceDTO().getIssuanceUpdateAction()))
        if (CommonConstant.COPY_ISSUANCE_UPDATE_FORM.equalsIgnoreCase(pgTitle))
        {
            CertifiedCopyDetailsDTO dto1 = copyForm.getIssuanceDTO();
            //String certifiedid1 = (String)request.getParameter("refId");
           // HttpSession requestSession = request.getSession(true);
            String certifiedid1 = (String)request.getParameter("certifiedId");            
            session.setAttribute("refId",certifiedid1);          
           
            if (certifiedid1 != null)
            {
            	 
            	 
            	 dto1 = bd.getCopyIssuance(certifiedid1,language);
                 copyForm.setIssuanceDTO(dto1);
               
                
                /*copyForm.setCertifiedId(dto.getCertifiedId());  
                copyForm.setFirstName(dto.getFirstName());
                copyForm.setMiddleName(dto.getMiddleName());
                copyForm.setLastName(dto.getLastName());
                copyForm.setGender(dto.getGender());
                copyForm.setAge(dto.getAge());
                copyForm.setFatherName(dto.getFatherName());
                copyForm.setMotherName(dto.getMotherName());
                copyForm.setAddress(dto.getAddress());
                copyForm.setCity(dto.getCity());
                copyForm.setCountry(dto.getCountry());;
                copyForm.setState(dto.getState());
                copyForm.setPin(dto.getPin());
                copyForm.setPhone(dto.getPhone());
                copyForm.setMphone(dto.getMphone());
                copyForm.setEmail(dto.getEmail());
                copyForm.setIdProof(dto.getIdProof());
                copyForm.setIdProofNo(dto.getIdProofNo());
                copyForm.setFee(dto.getFee());
                copyForm.setPostalFee(dto.getPostalFee());
                copyForm.setTotalFee(dto.getTotalFee());
                copyForm.setIssuanceRemark(dto.getIssuanceRemark());
                copyForm.setRegNo(dto.getRegNo());
                copyForm.setTypeReq(dto.getTypeReq());
                copyForm.setPurposeReq(dto.getPurposeReq());
                copyForm.setCreatedDt(dto.getCreatedDt());
                copyForm.setAppStatus(dto.getAppStatus());
                copyForm.setModifiedDt(dto.getModifiedDt());*/

                request.setAttribute("copyForm",copyForm);
                FORWARD_JSP = CommonConstant.ID_DETAILS_SUCCESS;
           
            }
        }


        
         
        
       if("copyOnlineDownload".equalsIgnoreCase(copyForm.getIssuanceDTO().getOnlineDownloadAction()))
       {
    	   
    	   dto = copyForm.getIssuanceDTO();
    	   
    	   if(bd.UpdateDownloadStatus(dto))
    	   {
    		   //dto.setDocumentType("");
    		   dto.setOnlineDownloadAction("");
    		   PropertiesFileReader pr1 = PropertiesFileReader.getInstance("resources.igrs");
    		   String dms1=pr.getValue("ImageServerIP");
    		   String port1=pr.getValue("port");
    		   dto.setDms(dms1);
    		   dto.setPort(port1);
    		   copyForm.setIssuanceDTO(dto);
    		   copyForm.setRegNumberDownload(dto.getRegNo1());
    		   FORWARD_JSP = "downsuccess"; 
    		   //Code for Pdf to be written
    	   }
    	  
       }
        // Online request previous page
        if (CommonConstant.COPY_ONLINE_PREVIOUS.equalsIgnoreCase(copyForm.getIssuanceDTO().getOnlineBackAction()))
        {
            log.debug("COPY ISSUANCE ONLINE BACK ACTION");
          //  HttpSession requestSession=request.getSession(true);
            CertifiedActionForm coForm = (CertifiedActionForm) session.getAttribute("copyForm");
            request.setAttribute("copyForm",coForm);
            FORWARD_JSP = CommonConstant.ONLINE_PREVIOUS;
        }


        //if (CommonConstant.COPY_ISSUANCE_UPDATE.equalsIgnoreCase(copyForm.getIssuanceDTO().getIssuanceUpdateAction())) //Ramesh commented on 19 Jan13
        if (CommonConstant.COPY_ISSUANCE_UPDATE.equalsIgnoreCase(pgTitle)) 
        {
            log.debug("COPY ISSUANCE ACTION STARTS***********");
            dto = copyForm.getIssuanceDTO();
            String certified_id = dto.getIssuanceViewId();
           // session=request.getSession(true);
            session.removeAttribute("certified_id");
            FORWARD_JSP = CommonConstant.COPY_UPDATE;
        }


        // To View the Copy Issuance Request Update Input 
        String requestStatus = request.getParameter("requestStatus");
        if (requestStatus!=null)
        {
            if (requestStatus.equals("true"))
            {
                log.debug("Update Form..!!");                
                dto.setCertifiedId("");
                dto.setDocumentType("");
                dto.setDocumentId("");
                dto.setRegNo("");
                dto.setVolume("");
                dto.setBookNo("");
                dto.setNum("");
                copyForm.setDateOfReq("");
                dto.setIssuanceRemark("");
                dto.setUpdtdRemarks("");
                dto.setDisDate("");
                dto.setFromRequestDate("");
                dto.setToRequestDate("");
                session.removeAttribute("spotVRList");
                dto.setIssuanceRequestUpdateAction("");
                dto.setIssuanceRequestUpdateAction("");
                dto.setRequestBackAction("");
                dto.setCopyIssuanceForm("");
               /* ArrayList pendingAppAtSR=bd.srDashboardCertifiedUpdate(officeId,dto);
                ArrayList pendingAppAtSRNoencum=bd.srDashboardNoEcumbranceUpdate(officeId, dto);
                 // request.setAttribute("pendingApplication", pendingAppAtSR);
               
                 copyForm.getIssuanceDTO().setSrDashBoardList(pendingAppAtSR);
                 request.setAttribute("onlineCopyDashList",pendingAppAtSR);
                 request.setAttribute("onlineCopyDashList1",pendingAppAtSRNoencum); */
                FORWARD_JSP = CommonConstant.ISSUANCE_STATUS_SUCCESS;  
                FORWARD_JSP = CommonConstant.REQUEST_STATUS;       
            }
        }



        // To view the request update according to the reference id, reg.no, status 
       
            log.debug("Actions are matched!!!!");
            String issuance_update = copyForm.getIssuanceDTO().getCopyIssuanceForm();
         //   log.debug(issuance_update);
            if ("issuanceRequestUpdate".equals(issuance_update) && pgTitle==null)
            {
             //   session=request.getSession(true);
                log.debug("Actions are matched....");
                copyForm.setDateOfReq("");
                dto.setIssuanceRemark("");
                dto.setUpdtdRemarks("");
                dto.setDisDate("");
                //session.removeAttribute("certifiedId");
                dto = copyForm.getIssuanceDTO();
                String referId = dto.getCertifiedId();
                String regNo = dto.getRegNo();
                String fromDate = dto.getFromRequestDate();
                String toDate = dto.getToRequestDate();
                if(dto.getDocumentType().equalsIgnoreCase("yes"))
                {	
               
                	copyForm = bd.getIssuanceRequestSearch(copyForm,referId,regNo,fromDate,toDate,officeId);
                	request.setAttribute("spotVRList", copyForm.getCopReqViewList());
                	  String chk = "reqstat";
                      String oldregno1 = dto.getVolume();
                      String oldregno2 = dto.getBookNo();   
                      String oldregno3 = dto.getNum();
                      FORWARD_JSP = CommonConstant.REQUEST_SUCCESS;
                }
                else
                {
                	copyForm = bd.getIssuanceRequestSearchNoEncum(copyForm,referId,regNo,fromDate,toDate,officeId);
                    request.setAttribute("spotVRList", copyForm.getCopReqViewList1());
                    String chk = "reqstat";
                    String oldregno1 = dto.getVolume();
                    String oldregno2 = dto.getBookNo();   
                    String oldregno3 = dto.getNum();
                    FORWARD_JSP = "requestSuccessNoEncum";
                }
                //ArrayList copy_online = bd.getIssuanceRequestSearch(referId,regNo,fromDate,toDate);
              
               /* if(copy_online.size()==0)
                {
                  request.setAttribute("referId",referId);
                  request.setAttribute("reNo",regNo);
                  request.setAttribute("che",chk);
                  request.setAttribute("fromDate",fromDate);
                  request.setAttribute("toDate",toDate);
                  request.setAttribute("oldregno1",oldregno1);
                  request.setAttribute("oldregno2",oldregno2);
                  request.setAttribute("oldregno3",oldregno3);
                  FORWARD_JSP = CommonConstant.ISSUANCE_ERROR; 
                } 

                CopyIssuanceRule rRule = new CopyIssuanceRule();
                ArrayList errorList = rRule.validateIssuanceRecord(copyForm);
                if (rRule.isError())
                {
                    request.setAttribute(CommonConstant.COPY_ISSUANCE_ERROR_FLAG, "true");
                    request.setAttribute(CommonConstant.COPY_ISSUANCE_ERROR_LIST, errorList);
                } 
                FORWARD_JSP = CommonConstant.REQUEST_SUCCESS;
                else
                {
                    if (copy_online.size()!=0)
                    {
                        for (int i=0;i<copy_online.size();i++)
                        {
                            CertifiedCopyDetailsDTO dt = (CertifiedCopyDetailsDTO) copy_online.get(i);
                            log.debug("first Name:-"+dt.getFirstName());
                        }
                    }
                    
                    if (copy_online.size()!=0)
                    {
                        for (int i=0;i<copy_online.size();i++)
                        {
                            CertifiedCopyDetailsDTO d = (CertifiedCopyDetailsDTO)copy_online.get(i);
                            log.debug("Action:-"+d.getCertifiedId()+":"+copy_online.get(i));
                        }
                            session.setAttribute("copy_online",copy_online);
                            log.debug("Values are set to the DT objects...");
                            FORWARD_JSP = CommonConstant.REQUEST_SUCCESS;
                    }
                    
                }*/
                
               
            }
        



        // To get the copy issuance request update details with the remarks field to update.
        //if (CommonConstant.COPY_ISSUANCE_REQUPDATE_FORM.equalsIgnoreCase(copyForm.getIssuanceDTO().getIssuanceRemarkUpdateAction()))
        if ("copyIssuanceRequestUpdate".equalsIgnoreCase(pgTitle))
        
        {
            //CertifiedCopyDetailsDTO dto1 = copyForm.getIssuanceDTO();
            //String certifiedid1 = (String)request.getParameter("refId");
            String certifiedid1 = (String)request.getParameter("certifiedId");
        
            session.setAttribute("refId",certifiedid1);
            log.debug("Request certified Id:-"+certifiedid1);
            if (certifiedid1 != null)
            {
            	dto= bd.getCopyIssuanceRemarks(certifiedid1,languageLocale);
                copyForm.setIssuanceDTO(dto);
                
                
               /* copyForm.setCertifiedId(dto.getCertifiedId());  
                copyForm.setFirstName(dto.getFirstName());
                copyForm.setMiddleName(dto.getMiddleName());
                copyForm.setLastName(dto.getLastName());
                copyForm.setGender(dto.getGender());
                copyForm.setAge(dto.getAge());
                copyForm.setFatherName(dto.getFatherName());
                copyForm.setMotherName(dto.getMotherName());
                copyForm.setAddress(dto.getAddress());
                copyForm.setCity(dto.getCity());
                copyForm.setCountry(dto.getCountry());
                copyForm.setState(dto.getState());
                copyForm.setPin(dto.getPin());
                copyForm.setPhone(dto.getPhone());
                copyForm.setMphone(dto.getMphone());
                copyForm.setEmail(dto.getEmail());
                copyForm.setIdProof(dto.getIdProof());
                copyForm.setIdProofNo(dto.getIdProofNo());                
                copyForm.setIssuanceRemark(dto.getIssuanceRemark());
                copyForm.setRegNo(dto.getRegNo());
                copyForm.setTypeReq(dto.getTypeReq());
                copyForm.setPurposeReq(dto.getPurposeReq());
                copyForm.setCreatedDt(dto.getCreatedDt());
                copyForm.setAppStatus(dto.getAppStatus());
                copyForm.setIssuanceRemark(dto.getIssuanceRemark());

                */
                dto.setCopyIssuanceForm("");
                dto.setDispatchType("M");
                request.setAttribute("copyForm",copyForm);
                FORWARD_JSP = CommonConstant.REMARKS_DETAIL_SUCCESS;
            }
        }


        // Request update previous page
        if (CommonConstant.REQUEST_UPDATE_PREVIOUS.equalsIgnoreCase(copyForm.getIssuanceDTO().getRequestBackAction()))
        {
            log.debug("COPY ISSUANCE REQUEST BACK ACTION");
            copyForm.setDateOfReq("");
            dto.setIssuanceRemark("");
            dto.setUpdtdRemarks("");
            dto.setDisDate("");
            FORWARD_JSP = CommonConstant.REQUEST_PREVIOUS;
        }


        // To update the remarks field into the DB.	
        //if (CommonConstant.ISSUANCE_REQUPDATE.equalsIgnoreCase(copyForm.getIssuanceDTO().getIssuanceAction())) //Commented by rames on 13 Jan13
        if ("issuanceReqUpdate".equalsIgnoreCase(pgTitle))
        {
        	 //copyForm.setIssuanceDTO(dto);
            dto = copyForm.getIssuanceDTO();
            //String certified_id = dto.getIssuanceViewId();
            String appStatus =(String)session.getAttribute("appStatus");
            String certified_id =(String)session.getAttribute("certifiedId");
            String remarks=request.getParameter("issuanceRemark");
            String disDate=dto.getDisDate();
            String trackingNum=dto.getPostalTrakingNum();
            //status = bd.getLockStatus(dto.getRegistrationId()); 
            String status = bd.getcopyStatus(certified_id); 
            String updtdRemarks=dto.getUpdtdRemarks();
            if("Un-delivered".equalsIgnoreCase(status))
            {            
	            boolean copyIssuanceSubmitvalue = bd.updateIssuance(dto,roleId,funId,userId,status);
	            log.debug("Return Value:-"+copyIssuanceSubmitvalue);
	            if (copyIssuanceSubmitvalue)
	            {
	                log.debug("inserted into the database.");
	                refId = dto.getCertifiedId();
	                request.setAttribute("refId",refId);
	                FORWARD_JSP = CommonConstant.REMARKS_UPDATE;
	            }
            }
            else
            {
            	String msg="Request update already done for the reference id :- "+certified_id;
            	request.setAttribute("statusupdateMsg",msg);
            	FORWARD_JSP = CommonConstant.REQUEST_SUCCESS;
            }
        }


        // To View the Copy Issuance Status Change Input 
        String statusChange = request.getParameter("statusChange");
        if (statusChange!=null)
        {
            if (statusChange.equals("true"))
            {
                log.debug("Change Form..!!");
                FORWARD_JSP = CommonConstant.CHANGE_STATUS;       
            }
        }



        // To View the copy issuance list according to the given input data	
        if (CommonConstant.COPY_ISSUANCE_STATUS_FORM.equalsIgnoreCase(copyForm.getIssuanceDTO().getIssuanceStatusAction()))
        {
            String issuance_status = copyForm.getIssuanceDTO().getIssuanceStatusAction();
            log.debug(issuance_status);
            if ("copyIssuanceStatus".equals(issuance_status))
            {
              //  session=request.getSession(true);
                dto = copyForm.getIssuanceDTO();
                String durationFrom = dto.getFromRequestDate();
                String durationTo = dto.getToRequestDate();
                String referenceId = dto.getCertifiedId();
                String regisNo = dto.getRegNo();
                ArrayList copy_change = bd.getIssuanceStatusSearch(durationFrom,durationTo,referenceId,regisNo);
                String chk = "statchange";
                String oldregno1 = dto.getVolume();
                String oldregno2 = dto.getBookNo();   
                String oldregno3 = dto.getNum();
		if(copy_change.size()==0)
                {
			request.setAttribute("referId",referenceId);
			request.setAttribute("reNo",regisNo);
                        request.setAttribute("che",chk);
                        request.setAttribute("fromDate",durationFrom);
                        request.setAttribute("toDate",durationTo);
                        request.setAttribute("oldregno1",oldregno1);
                        request.setAttribute("oldregno2",oldregno2);
                        request.setAttribute("oldregno3",oldregno3);
			FORWARD_JSP = CommonConstant.ISSUANCE_ERROR; 
		}

                CopyIssuanceRule rRule = new CopyIssuanceRule();
                ArrayList errorList = rRule.validateIssuanceRecord(copy_change);
                if (rRule.isError())
                {
                    request.setAttribute(CommonConstant.COPY_ISSUANCE_ERROR_FLAG, "true");
                    request.setAttribute(CommonConstant.COPY_ISSUANCE_ERROR_LIST, errorList);
                } else
                {
                    if (copy_change.size()!=0)
                    {
                        for (int i=0;i<copy_change.size();i++)
                        {
                            CertifiedCopyDetailsDTO dt = (CertifiedCopyDetailsDTO) copy_change.get(i);
                            log.debug("first Name:-"+dt.getFirstName());
                        }
                    }
                    
                    if (copy_change.size()!=0)
                    {
                        for (int i=0;i<copy_change.size();i++)
                        {
                            CertifiedCopyDetailsDTO d = (CertifiedCopyDetailsDTO)copy_change.get(i);
                            log.debug("Action:-"+d.getCertifiedId()+":"+copy_change.get(i));
                        }
                            request.setAttribute("copy_change",copy_change);
                            log.debug("Values are set to the DT objects...");
                            FORWARD_JSP = CommonConstant.STATUS_SUCCESS;
                    }
                    
                }
            }
        }



        // To get the copy issuance status change details with the remarks field to update.
        if (CommonConstant.COPY_ISSUANCE_STATUS_CHANGE.equalsIgnoreCase(copyForm.getIssuanceDTO().getIssuanceStatusChangeAction()))
        {
            CertifiedCopyDetailsDTO dto1 = copyForm.getIssuanceDTO();
            String certifiedid1 = (String)request.getParameter("refId");
          //  HttpSession requestSession = request.getSession(true);
            session.setAttribute("refId",certifiedid1);
            log.debug("Request certified Id:-"+certifiedid1);
            if (certifiedid1 != null)
            {
                dto1 = bd.getCopyIssuanceRemarks(certifiedid1,languageLocale);
                copyForm.setIssuanceDTO(dto1);
                request.setAttribute("copyForm",copyForm);
                FORWARD_JSP = CommonConstant.STATUS_DETAILS_SUCCESS;
            }
        }


        // Status change previous page
        if (CommonConstant.STATUS_CHANGE_PREVIOUS.equalsIgnoreCase(copyForm.getIssuanceDTO().getStatusBackAction()))
        {
            log.debug("COPY ISSUANCE STATUS BACK ACTION");
            FORWARD_JSP = CommonConstant.STATUS_PREVIOUS;
        }


        // To update the remarks field into the DB for status change.	
        if (CommonConstant.ISSUANCE_STATUS_UPDATE.equalsIgnoreCase(copyForm.getIssuanceDTO().getCopyStatusAction()))
        {
            dto = copyForm.getIssuanceDTO();
            String certified_id = dto.getIssuanceViewId();
           // session=request.getSession(true);
            session.removeAttribute("certified_id");
            boolean copyIssuanceSubmitvalue = bd.updateIssuanceStatus(dto,roleId,funId,userId);
            log.debug("Return Value:-"+copyIssuanceSubmitvalue);
            if (copyIssuanceSubmitvalue)
            {
                log.debug("inserted into the database.");
                FORWARD_JSP = CommonConstant.STATUS_UPDATE;
            }
        }
       
	 }//end else
	     
	     if ("setUploadFile".equalsIgnoreCase(acname)) {
				try {
					FormFile uploadedFile = copyform.getIssuanceDTO().getAttachedDoc(); //copyform.getIssuanceDTO()
					byte contents[] = uploadedFile.getFileData();
					
					copyform.getIssuanceDTO().setPhoto(contents); //copyform.getIssuanceDTO()
					if ("".equals(uploadedFile.getFileName())) {
//						clearDoc(cDto);
						errorList1
						.add("Invalid file selection. Please try again./अवैध फ़ाइल चयन. पुन: प्रयास करें.");
					}
					
					String fileExt = getFileExtension(uploadedFile
							.getFileName());
					AuditInspectionRule rule = new AuditInspectionRule();
					rule.validateFileType(fileExt);
					int size = uploadedFile.getFileSize();
					/*if (rule.isError()) {
//						clearDoc(cDto);
						errorList1
								.add("Invalid file type. Please select another file./अमान्य फ़ाइल प्रकार. एक और फ़ाइल का चयन करें.");
						request.setAttribute("errorsList", errorList1);
					} else {*/
						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
//							clearDoc(cDto);
							errorList1
									.add("File size is Greater than 10 MB. Please select another file./फ़ाइल का आकार 10 एमबी से अधिक है. एक और फाइल चुनें");
							request.setAttribute("errorsList", errorList1);
						} else {
							 String docName = "Certified_Copy_Document."+fileExt;
							 String str = GUIDGenerator.getGUID();
							 String docPath = pr.getValue("igrs_upload_path")+File.separator+"UPLOAD/26/CertCopy"+str;
							 
							 //copyform.getIssuanceDTO().setUploaded_doc_path(docPath); //copyform.getIssuanceDTO()
							 copyform.getIssuanceDTO().setDocumentName(uploadedFile.getFileName()); //copyform.getIssuanceDTO()
							 copyform.getIssuanceDTO().setDocContents(uploadedFile.getFileData()); //copyform.getIssuanceDTO()
							 copyform.getIssuanceDTO().setDocFileSize(getFileSize(uploadedFile.getFileData().length));
							 boolean up=uploadFile(uploadedFile,docName,docPath);
							 docPath=docPath+"/"+docName;
							 copyform.getIssuanceDTO().setUploaded_doc_path(docPath);
							 request.setAttribute("copyForm",copyform);
						}
					//}
				} catch (Exception e) {
					errorList1.add("Unable to upload file. Please try again./फ़ाइल अपलोड करने में असमर्थ. पुन: प्रयास करें.");
					request.setAttribute("errorsList", errorList1);
				}
				
				FORWARD_JSP = CommonConstant.REMARKS_DETAIL_SUCCESS;  
				
			} 
	     
	     
	     
	     
	      if ("download".equalsIgnoreCase(acname)) {
				try {
					byte[] content = copyform.getIssuanceDTO().getPhoto();
					String filename =  copyform.getIssuanceDTO().getDocumentName();
					if(content != null) {
						CaveatsViewSearchAction.downloadDocument(response, content, filename);
					}
					acname=null;
					FORWARD_JSP = CommonConstant.REMARKS_DETAIL_SUCCESS;
				} catch (Exception e) {
				}			
			}
	     
	      if(frdName!=null &&frdName.equalsIgnoreCase("signdownload"))
			{
			try {
				String filename = request.getParameter("path").toString();
				
				   
				   response.setContentType("application/download");
				   String file = filename.substring(filename.lastIndexOf("/")+1, filename.length());
				   response.setHeader("Content-Disposition", "attachment; filename="
							+ URLEncoder.encode(file,"UTF-8"));
				   
				   File fileToDownload = new File(filename);
				   
				   
				   BufferedImage bi = ImageIO.read(fileToDownload);
				   OutputStream out = response.getOutputStream();
					ImageIO.write(bi, "jpg", out);
					out.close();
					FORWARD_JSP = CommonConstant.VIEW_DETAILS_SUBMIT;
					
			 
			 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 
			 
			}
	     
	     
	     
        //session.setAttribute("copyForm", copyForm);
	     if(copyForm.getIssuanceDTO().getPartyDetailList()!=null)
	     	  log.debug("list size "+copyForm.getIssuanceDTO().getPartyDetailList().size());
         }

      
	  
	  
        return mapping.findForward(FORWARD_JSP);
    }
}









