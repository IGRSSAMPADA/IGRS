package com.wipro.igrs.propertylock.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.propertylock.bd.PropertyLockBD;
import com.wipro.igrs.propertylock.dto.PropertyLockDTO;
import com.wipro.igrs.propertylock.form.PropertyLockForm;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.dto.RegCompletDTO;
import com.wipro.igrs.util.GUIDGenerator;
import com.wipro.igrs.util.PropertiesFileReader;

public class PropertyLockAction extends BaseAction{
private Logger logger =(Logger)Logger.getLogger(PropertyLockAction.class);
private String forwardPage="";

public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response,HttpSession session) throws Exception {
	
	if (form!=null){

		 PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		PropertyLockForm eForm = (PropertyLockForm)form;
		PropertyLockBD  lockBD = new PropertyLockBD();
		PropertyLockDTO dto = eForm.getLockdto();
		
		String userId = (String)session.getAttribute("UserId");
		String officeId = (String)session.getAttribute("loggedToOffice");
		String language=(String)session.getAttribute("languageLocale");
		eForm.setLanguage(language);
		dto.setUserTypeId(lockBD.chkUser(userId));
		if(dto.getUserTypeId().equalsIgnoreCase("I"))
		{
			ArrayList dtlList=(ArrayList)lockBD.getInternalUserDtls(officeId);
			dto.setParentOfficeId(officeId);
			for(int i=0;i<dtlList.size();i++)
			{
			ArrayList list=(ArrayList)dtlList.get(0);
			dto.setParentOfficeName((String)list.get(0));
			dto.setParentDistrictId((String)list.get(1));
			dto.setParentDistrictName((String)list.get(2));
			}	
		}
		else if(dto.getUserTypeId().equalsIgnoreCase("2"))
		{
			dto.setParentDistrictName("NA");
			dto.setParentDistrictId("NA");
			dto.setParentOfficeId("NA");
			dto.setParentOfficeName("NA");
		}
		else
		{
			ArrayList dtlList=lockBD.getExternalUserDtls(userId);
			for(int i=0;i<dtlList.size();i++)
			{
				ArrayList list=(ArrayList)dtlList.get(0);
				dto.setParentDistrictName((String)list.get(0));
				dto.setParentDistrictId((String)list.get(1));
			}
			dto.setParentOfficeId("NA");
			dto.setParentOfficeName("NA");
		}
		if("hi".equalsIgnoreCase(language))
		{
			String moduleName=(String)session.getAttribute("modName");
			if("Property Locking".equalsIgnoreCase(moduleName))
			{
				session.setAttribute("modName","संपत्ति लॉकिंग");
			}
			else if("Property release".equalsIgnoreCase(moduleName))
			{
				session.setAttribute("modName", "संपत्ति रिलीज");
			}
			String fucntionName=(String)session.getAttribute("fnName");
			if("Lock".equalsIgnoreCase(fucntionName))
			{
				session.setAttribute("fnName", "लॉक");
			}
			else if("Release".equalsIgnoreCase(fucntionName))
			{
				session.setAttribute("fnName", "रिलीज");
			}
			else if("View".equalsIgnoreCase(fucntionName))
			{
				session.setAttribute("fnName", "देखें");	
			}
			
		}
		//END
		String formName = eForm.getFormName();
		String actionName = eForm.getActionName();
		eForm.setUid(userId);
		logger.debug("form name is----------->"+formName);
		logger.debug("Action name is---------->"+actionName);
		String startPage="";
		String nextPage="";
		dto.setCountryList(lockBD.getCountry(language));
		dto.setRcountryList(lockBD.getCountry(language));
		dto.setIdProofs(lockBD.getPhoto(language));
		dto.setAppellate(lockBD.getAppellate(language));
		
		
		if(request.getParameter("funId")!=null)
		{
			nextPage = (String)request.getParameter("funId");
			if(!(nextPage).equalsIgnoreCase(""))
			{
				dto.setFuncId(nextPage);
			}	
		}
		
		String funid = dto.getFuncId();
		String fee="";
		if(funid.equalsIgnoreCase("FUN_017"))
		{
			/*fee = lockBD.getfee("FUN_017", null, userId);*/
			fee = lockBD.getfee("FUN_017", null, dto.getUserTypeId());
			 int ind = fee.indexOf(".");
			  if (ind!= -1)
			  {
			  String totFee1 = fee.substring(0,fee.indexOf("."));
			  dto.setTotalFee(totFee1);
			  }
			  else 
			  {
			  dto.setTotalFee(fee);
			  }		
		}
		if(funid.equalsIgnoreCase("FUN_201"))
		{
				/*fee = lockBD.getfee("FUN_201", null, userId);*/
				fee = lockBD.getfee("FUN_201", null, dto.getUserTypeId());
				 int ind = fee.indexOf(".");
				  if (ind!= -1)
				  {
				  String totFee1 = fee.substring(0,fee.indexOf("."));
				  dto.setTotalFee(totFee1);
				  }
				  else 
				  {
				  dto.setTotalFee(fee);
				  }			
		}
		
		
		//------------------From Menu----------------------------
		if(request.getParameter("fwdName")!=null)
		{
			startPage = (String)request.getParameter("fwdName");
		
		if(!(startPage).equalsIgnoreCase("")){
			if(startPage.equalsIgnoreCase("firstPagePlock")){
				
				 
				 dto.setIsPayment(0);
				 dto.setIsRegDetlEmpty(0);
				 dto.setIsAlrdyLocked(0);
				 dto.setNotInsertedLock(0);
				 dto.setNotInsertedRel(0);
				 dto.setIsLockSuccess(0);
				 dto.setIsRelSuccess(0);
				 dto.setIsLockSave(0);
				 dto.setIsReleaseSave(0);
				 dto.setIsPayCompl(0);
				 dto.setIsPartial(0);
				 dto.setNotInsertedP(0);
				 dto.setNotUpdatedAfterPay(0);
				 dto.setNoRecFound(0);
				 
				 dto.setForwrdAftrPrprtyDisp("");
				 dto.setAppType("");
				 dto.setAppTypeName("");
				 dto.setOrgName("");
				 dto.setAuthName("");
				 dto.setOrgCountry("");
				 dto.setOrgCountryName("");
				 dto.setOrgState("");
				 dto.setOrgStateName("");
				 dto.setOrgDistrict("");
				 dto.setOrgDistrictName("");
				 dto.setOrgAddress("");
				 dto.setOrgMobno("");
				 dto.setOrgPhno("");
				 dto.setIdProofName("");
				 dto.setPropertyLockid("");
				 dto.setAlrdyLockId("");
				 dto.setMapPropertyTransPartyDisp(new HashMap());
				 dto.setPropertyReleaseId("");
				 dto.setLockDetailsRelSearch(new ArrayList());
				 dto.setLockComb("");
				 dto.setRelationId("");
				 dto.setRegCompNumber("");
				 dto.setRegCompDate("");
				 dto.setPropId("");
				 dto.setPropStatus("");
				 dto.setPropDeed("");
				 dto.setPropAry("");
				 dto.setChckBx("");
				 dto.setPropComb("");
				 dto.setIsDisable("");
				 dto.setRadioSelect("");
				 dto.setRegDetls(new ArrayList());
				 dto.setPropList(new ArrayList());
				 dto.setPayableFee("");
				 dto.setAlrdyPaidFee("");
				 dto.setPrimKeyPymt("");
				 dto.setFlg("");
				 dto.setPhotoPath("");
				 dto.setThumbPath("");
				 dto.setSignPath("");
				 dto.setRegistrationId("");
				 dto.setRegistrationDate("");
				 dto.setFirstName("");
				 dto.setMidName("");
				 dto.setLastName("");
				 dto.setGender("");
				 dto.setGenClick("");
				 dto.setAge("");
				 dto.setGuardianName("");
				 dto.setMotherName("");
				 dto.setAddress("");
				 dto.setPin("");
				 dto.setPhone("");
				 dto.setMphone("");
				 dto.setEmail("");
				 dto.setIdProof("");
				 dto.setIdProofName("");
				 dto.setIdProofNo("");
				 dto.setBankName("");
				 dto.setBankAddress("");
				 dto.setFilePhoto(null);
				 dto.setDocumentName("");
				 dto.setFileThumb(null);
				 dto.setThunmbName("");
				 dto.setFileSignature(null);
				 dto.setSignatureName("");
				 dto.setPurpose("");
				 dto.setPoaRegDate("");
				 dto.setPoaRegNo("");
				 dto.setFromRequestDate("");
				 dto.setToRequestDate("");
				 dto.setDateLocking("");
				 dto.setLockStatus("");
				 dto.setPersonType("");
				 dto.setReleaserType("");
				 dto.setReleaserName("");
				 dto.setRelation("");
				 dto.setRelationId("");
				 dto.setRcountryId("");
				 dto.setRcountryName("");
				 dto.setRstate("");
				 dto.setRstateId("");
				 dto.setRstateName("");
				 dto.setRdistrictId("");
				 dto.setRdistrictName("");
				 dto.setRelFatherName("");
				 dto.setRelMotherName("");
				 dto.setRelphone("");
				 dto.setRelMobNo("");
				 dto.setRelEmail("");
				 dto.setRelAddress("");
				 dto.setRelAttachDoc(null);
				 dto.setRelPhotoName("");
				 dto.setRelPhotoPath("");
				 dto.setRelAttachthumb(null);
				 dto.setRelThumbName("");
				 dto.setRelThumbPath("");
				 dto.setRelAttaSig(null);
				 dto.setRelSignName("");
				 dto.setRelSignPath("");
				 dto.setRelDeathCerAttach(null);
				 dto.setRelDeathCerName("");
				 dto.setRelDeathCrtPath("");
				 dto.setReasonForRelease("");
				 dto.setRemarksForRelease("");
				 dto.setViewComb("");
				 dto.setViewResultList(new ArrayList());
				 dto.setPropertyTxnId("");
				 dto.setAppStatus("");
				 dto.setCreatedDt("");
				 dto.setIsViewEmpty(0);
				 dto.setIsFromView(0);	
				   
				//added by shruti--8 MAY 2014---- LATEST CHANGES
				 	dto.setGuidUpload(GUIDGenerator.getGUID());
					dto.setParentPathFP(pr.getValue("igrs_upload_path")+"/11/Lock");
					dto.setFileNameFP("FingerPrint.GIF");
					dto.setParentPathScan(pr.getValue("igrs_upload_path")+"/11/Lock");
					dto.setFileNameScan("CapturedDocument.PDF");
					dto.setParentPathSign(pr.getValue("igrs_upload_path")+"/11/Lock");
					dto.setFileNameSign("CapturedScan.GIF");
					dto.setForwardPath("/lockAction.do?uploadSign=true");
					dto.setForwardName("lockFirst");
					dto.setPhotoName("Photo.JPG");
					dto.setWebcameraPath(pr.getValue("igrs_upload_path")+"/11/Lock");
					//end
				 
				    ArrayList pendingDetails = new ArrayList();
					pendingDetails= lockBD.getPendingDetls(userId,language);
					 if (pendingDetails.size()==0){
						 dto.setNoRecFound(1);
						  }
					 if (pendingDetails.size()>0){
						 dto.setPendingDetails(pendingDetails);
						 request.setAttribute("pendingDetails", pendingDetails);
						}
				formName="";
				actionName="";
				forwardPage="plockdashbrd";
				}
			
			if(startPage.equalsIgnoreCase("firstPagePrelease")){
				
				 dto.setIsPayment(0);
				 dto.setIsRegDetlEmpty(0);
				 dto.setIsAlrdyLocked(0);
				 dto.setNotInsertedLock(0);
				 dto.setNotInsertedRel(0);
				 dto.setIsLockSuccess(0);
				 dto.setIsRelSuccess(0);
				 dto.setIsLockSave(0);
				 dto.setIsReleaseSave(0);
				 dto.setIsPayCompl(0);
				 dto.setIsPartial(0);
				 dto.setNotInsertedP(0);
				 dto.setNotUpdatedAfterPay(0);
				 dto.setNoRecFound(0);
				 dto.setIsViewEmpty(0);
				 dto.setIsFromView(0);
				 
				 
				 dto.setForwrdAftrPrprtyDisp("");
				 dto.setAppType("");
				 dto.setAppTypeName("");
				 dto.setOrgName("");
				 dto.setAuthName("");
				 dto.setOrgCountry("");
				 dto.setOrgCountryName("");
				 dto.setOrgState("");
				 dto.setOrgStateName("");
				 dto.setOrgDistrict("");
				 dto.setOrgDistrictName("");
				 dto.setOrgAddress("");
				 dto.setOrgMobno("");
				 dto.setOrgPhno("");
				 dto.setIdProofName("");
				 dto.setPropertyLockid("");
				 dto.setAlrdyLockId("");
				 dto.setMapPropertyTransPartyDisp(new HashMap());
				 dto.setPropertyReleaseId("");
				 dto.setLockDetailsRelSearch(new ArrayList());
				 dto.setLockComb("");
				 dto.setRelationId("");
				 dto.setRegCompNumber("");
				 dto.setRegCompDate("");
				 dto.setPropId("");
				 dto.setPropStatus("");
				 dto.setPropDeed("");
				 dto.setPropAry("");
				 dto.setChckBx("");
				 dto.setPropComb("");
				 dto.setIsDisable("");
				 dto.setRadioSelect("");
				 dto.setRegDetls(new ArrayList());
				 dto.setPropList(new ArrayList());
				 dto.setPayableFee("");
				 dto.setAlrdyPaidFee("");
				 dto.setPrimKeyPymt("");
				 dto.setFlg("");
				 dto.setPhotoPath("");
				 dto.setThumbPath("");
				 dto.setSignPath("");
				 dto.setRegistrationId("");
				 dto.setRegistrationDate("");
				 dto.setFirstName("");
				 dto.setMidName("");
				 dto.setLastName("");
				 dto.setGender("");
				 dto.setGenClick("");
				 dto.setAge("");
				 dto.setGuardianName("");
				 dto.setMotherName("");
				 dto.setAddress("");
				 dto.setPin("");
				 dto.setPhone("");
				 dto.setMphone("");
				 dto.setEmail("");
				 dto.setIdProof("");
				 dto.setIdProofName("");
				 dto.setIdProofNo("");
				 dto.setBankName("");
				 dto.setBankAddress("");
				 dto.setFilePhoto(null);
				 dto.setDocumentName("");
				 dto.setFileThumb(null);
				 dto.setThunmbName("");
				 dto.setFileSignature(null);
				 dto.setSignatureName("");
				 dto.setPurpose("");
				 dto.setPoaRegDate("");
				 dto.setPoaRegNo("");
				 dto.setFromRequestDate("");
				 dto.setToRequestDate("");
				 dto.setDateLocking("");
				 dto.setLockStatus("");
				 dto.setPersonType("");
				 dto.setReleaserType("");
				 dto.setReleaserName("");
				 dto.setRelation("");
				 dto.setRelationId("");
				 dto.setRcountryId("");
				 dto.setRcountryName("");
				 dto.setRstate("");
				 dto.setRstateId("");
				 dto.setRstateName("");
				 dto.setRdistrictId("");
				 dto.setRdistrictName("");
				 dto.setRelFatherName("");
				 dto.setRelMotherName("");
				 dto.setRelphone("");
				 dto.setRelMobNo("");
				 dto.setRelEmail("");
				 dto.setRelAddress("");
				 dto.setRelAttachDoc(null);
				 dto.setRelPhotoName("");
				 dto.setRelPhotoPath("");
				 dto.setRelAttachthumb(null);
				 dto.setRelThumbName("");
				 dto.setRelThumbPath("");
				 dto.setRelAttaSig(null);
				 dto.setRelSignName("");
				 dto.setRelSignPath("");
				 dto.setRelDeathCerAttach(null);
				 dto.setRelDeathCerName("");
				 dto.setRelDeathCrtPath("");
				 dto.setReasonForRelease("");
				 dto.setRemarksForRelease("");
				 dto.setViewComb("");
				 dto.setViewResultList(new ArrayList());
				 dto.setPropertyTxnId("");
				 dto.setAppStatus("");
				 dto.setCreatedDt("");
				 dto.setIsViewEmpty(0);
				 dto.setIsFromView(0);
				
				ArrayList pendingDetailsR = new ArrayList();
				pendingDetailsR= lockBD.getPendingDetlsR(userId,language);
				 if (pendingDetailsR.size()==0){
					 dto.setNoRecFound(1);
					  }
				 if (pendingDetailsR.size()>0){
					 dto.setPendingDetailsR(pendingDetailsR);
					 request.setAttribute("pendingDetailsR", pendingDetailsR);
					}
				formName="";
				actionName="";
				forwardPage="plockdashbrdRelease";
				}
			
			if(startPage.equalsIgnoreCase("firstPagePview")){
				
				 dto.setIsPayment(0);
				 dto.setIsRegDetlEmpty(0);
				 dto.setIsAlrdyLocked(0);
				 dto.setNotInsertedLock(0);
				 dto.setNotInsertedRel(0);
				 dto.setIsLockSuccess(0);
				 dto.setIsRelSuccess(0);
				 dto.setIsLockSave(0);
				 dto.setIsReleaseSave(0);
				 dto.setIsPayCompl(0);
				 dto.setIsPartial(0);
				 dto.setNotInsertedP(0);
				 dto.setNotUpdatedAfterPay(0);
				 dto.setNoRecFound(0);
				 
				 dto.setForwrdAftrPrprtyDisp("");
				 dto.setAppType("");
				 dto.setAppTypeName("");
				 dto.setOrgName("");
				 dto.setAuthName("");
				 dto.setOrgCountry("");
				 dto.setOrgCountryName("");
				 dto.setOrgState("");
				 dto.setOrgStateName("");
				 dto.setOrgDistrict("");
				 dto.setOrgDistrictName("");
				 dto.setOrgAddress("");
				 dto.setOrgMobno("");
				 dto.setOrgPhno("");
				 dto.setIdProofName("");
				 dto.setPropertyLockid("");
				 dto.setAlrdyLockId("");
				 dto.setMapPropertyTransPartyDisp(new HashMap());
				 dto.setPropertyReleaseId("");
				 dto.setLockDetailsRelSearch(new ArrayList());
				 dto.setLockComb("");
				 dto.setRelationId("");
				 dto.setRegCompNumber("");
				 dto.setRegCompDate("");
				 dto.setPropId("");
				 dto.setPropStatus("");
				 dto.setPropDeed("");
				 dto.setPropAry("");
				 dto.setChckBx("");
				 dto.setPropComb("");
				 dto.setIsDisable("");
				 dto.setRadioSelect("");
				 dto.setRegDetls(new ArrayList());
				 dto.setPropList(new ArrayList());
				 dto.setPayableFee("");
				 dto.setAlrdyPaidFee("");
				 dto.setPrimKeyPymt("");
				 dto.setFlg("");
				 dto.setPhotoPath("");
				 dto.setThumbPath("");
				 dto.setSignPath("");
				 dto.setRegistrationId("");
				 dto.setRegistrationDate("");
				 dto.setFirstName("");
				 dto.setMidName("");
				 dto.setLastName("");
				 dto.setGender("");
				 dto.setGenClick("");
				 dto.setAge("");
				 dto.setGuardianName("");
				 dto.setMotherName("");
				 dto.setAddress("");
				 dto.setPin("");
				 dto.setPhone("");
				 dto.setMphone("");
				 dto.setEmail("");
				 dto.setIdProof("");
				 dto.setIdProofName("");
				 dto.setIdProofNo("");
				 dto.setBankName("");
				 dto.setBankAddress("");
				 dto.setFilePhoto(null);
				 dto.setDocumentName("");
				 dto.setFileThumb(null);
				 dto.setThunmbName("");
				 dto.setFileSignature(null);
				 dto.setSignatureName("");
				 dto.setPurpose("");
				 dto.setPoaRegDate("");
				 dto.setPoaRegNo("");
				 dto.setFromRequestDate("");
				 dto.setToRequestDate("");
				 dto.setDateLocking("");
				 dto.setLockStatus("");
				 dto.setPersonType("");
				 dto.setReleaserType("");
				 dto.setReleaserName("");
				 dto.setRelation("");
				 dto.setRelationId("");
				 dto.setRcountryId("");
				 dto.setRcountryName("");
				 dto.setRstate("");
				 dto.setRstateId("");
				 dto.setRstateName("");
				 dto.setRdistrictId("");
				 dto.setRdistrictName("");
				 dto.setRelFatherName("");
				 dto.setRelMotherName("");
				 dto.setRelphone("");
				 dto.setRelMobNo("");
				 dto.setRelEmail("");
				 dto.setRelAddress("");
				 dto.setRelAttachDoc(null);
				 dto.setRelPhotoName("");
				 dto.setRelPhotoPath("");
				 dto.setRelAttachthumb(null);
				 dto.setRelThumbName("");
				 dto.setRelThumbPath("");
				 dto.setRelAttaSig(null);
				 dto.setRelSignName("");
				 dto.setRelSignPath("");
				 dto.setRelDeathCerAttach(null);
				 dto.setRelDeathCerName("");
				 dto.setRelDeathCrtPath("");
				 dto.setReasonForRelease("");
				 dto.setRemarksForRelease("");
				 dto.setViewComb("");
				 dto.setViewResultList(new ArrayList());
				 dto.setPropertyTxnId("");
				 dto.setAppStatus("");
				 dto.setCreatedDt("");
				 dto.setIsViewEmpty(0);
				 dto.setIsFromView(0);
				
				formName="";
				actionName="";
				forwardPage="plockView1";
				}
			}
		}
		
		if(request.getParameter("Nextpage")!=null){
			nextPage = (String)request.getParameter("Nextpage");
		
		if(!(nextPage).equalsIgnoreCase("")){
			if(nextPage.equalsIgnoreCase("createNew")){
				
				if((Double.parseDouble(fee)!= 0)|| (Double.parseDouble(fee)!= 0.0)){
					dto.setIsPayment(1);
					dto.setAlrdyPaidFee("0");
					dto.setPayableFee(dto.getTotalFee());
				}				
				formName="";
				actionName="";
				forwardPage="lockFirst";
				}
			
			
                if(nextPage.equalsIgnoreCase("createNewRelease")){
				
				if((Double.parseDouble(fee)!= 0)|| (Double.parseDouble(fee)!= 0.0)){
					dto.setIsPayment(1);
					dto.setAlrdyPaidFee("0");
					dto.setPayableFee(dto.getTotalFee());
				}				
				formName="";
				actionName="";
				forwardPage="releaseSearch";
				}
			}	
		}
		
		
		if(request.getParameter("PropLabel")!=null){
			nextPage = (String)request.getParameter("PropLabel");
		
		if(!(nextPage).equalsIgnoreCase("")){
			if(nextPage.equalsIgnoreCase("displayProperty")){
				//latest changes as per new duty calculation and valuation
				RegCommonBO commonBo = new RegCommonBO();
				String propertyId="";
        		if(request.getParameter("key")!=null)
        			propertyId=request.getParameter("key");
        		else if(request.getAttribute("key")!=null)
        			propertyId=(String)request.getAttribute("key");
				if(propertyId.length()==15){
					propertyId="0"+propertyId;
					
					
				}
        		dto.setPropId(propertyId);
        		String reginitId="";
        		reginitId=lockBD.getReginitId(dto.getRegistrationId());
        		String instrumentFlag="";
        		instrumentFlag=lockBD.getInstrumentFlag(reginitId);
        		RegCommonForm regForm=new RegCommonForm();
        		if("Y".equalsIgnoreCase(instrumentFlag))
        		{	
        			
        			regForm.setMapPropertyTransPartyDisp(commonBo.getPropertyDetsForViewConfirm(reginitId, propertyId,language));
            		String parentPage = request.getParameter("ParentJspAfterDisp");
            		dto.setForwrdAftrPrprtyDisp(parentPage);
    				formName="";
    				actionName="";
    				request.setAttribute("reginit", regForm);
    				forwardPage="propertyView";     		
        		}
        		else
        		{
        			regForm.setMapPropertyTransPartyDisp(commonBo.getPropertyDetsForViewConfirmNonProp(reginitId, propertyId,language));
            		String parentPage = request.getParameter("ParentJspAfterDisp");
            		dto.setForwrdAftrPrprtyDisp(parentPage);
    				formName="";
    				actionName="";
    				request.setAttribute("reginit", regForm);
    				forwardPage="propertyView1";
        		}	
				}
			}	
		}
		//ADDED BY SHRUTI 21 FEB 2014---PROPERTY DETAILS VIEW
		 
        if(request.getParameter("dms")!=null){
        	if(request.getParameter("dms").equalsIgnoreCase("retrieval")){
        		
        		if(request.getParameter("path")!=null){
        			
        			String partyType="";
        			String filePath=request.getParameter("path").toString();
        			logger.debug("retrieval path-->"+filePath);
        			int indexOfDot=filePath.lastIndexOf(".");
        			String key=request.getParameter("key");
        			String fileName="";
        			RegCompletDTO dtoObj=(RegCompletDTO)eForm.getLockdto().getMapPropertyTransPartyDisp().get(key);
        			if(filePath!=null && !filePath.equalsIgnoreCase("null"))
        			{
    				fileName=filePath.substring(indexOfDot-2, indexOfDot)+".";
    				
        			}
        			//MODIFIED BY SHRUTI TO GET CONTENT OF UPLOADED DOC
        			byte[] docContents=DMSUtility.getDocumentBytes(filePath.trim());
   				//END
        			//PropertyLockDTO dtoObj=new PropertyLockDTO();
        			//dtoObj=(PropertyLockDTO)eForm.getLockdto().getMapPropertyTransPartyDisp().get(key);
        				if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_ANGLE_1)){
        					dtoObj.setPropImage1DocContents(docContents);
                			DMSUtility.downloadDocument(response, filePath,dtoObj.getPropImage1DocContents());
            			}
        				if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_ANGLE_2)){
        					dtoObj.setPropImage2DocContents(docContents);
                			DMSUtility.downloadDocument(response, filePath,dtoObj.getPropImage2DocContents());
            			}
        				if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_ANGLE_3)){
        					dtoObj.setPropImage3DocContents(docContents);
                			DMSUtility.downloadDocument(response, filePath,dtoObj.getPropImage3DocContents());
            			}
        				if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_MAP)){
        					dtoObj.setPropMapDocContents(docContents);
                			DMSUtility.downloadDocument(response, filePath,dtoObj.getPropMapDocContents());
            			}
        				if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_RIN)){
        					dtoObj.setPropRinDocContents(docContents);
                			DMSUtility.downloadDocument(response, filePath,dtoObj.getPropRinDocContents());
            			}
        				if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_KHASRA)){
        					dtoObj.setPropKhasraDocContents(docContents);
                			DMSUtility.downloadDocument(response, filePath,dtoObj.getPropKhasraDocContents());
            			}
        				forwardPage="propertyView";	
        		}
        		
        	}
        }
		//END
		
		if(request.getParameter("PrintLock")!=null){
			nextPage = (String)request.getParameter("PrintLock");
		
		if(!(nextPage).equalsIgnoreCase("")){
			if(nextPage.equalsIgnoreCase("Y")){
				
				ArrayList txnDetls = new ArrayList();
				ArrayList comp = new ArrayList();
				txnDetls=lockBD.getTxnDetls(dto.getRegistrationId(), dto.getPropId(),dto.getPropertyLockid(),language);
				 if (txnDetls.size()>0){
						for(int i =0; i<txnDetls.size(); i++){
							comp.add((ArrayList)txnDetls.get(i));
							  if(comp != null && comp.size()>0){ 
							  for (int k=0; k< comp.size(); k++){
								  ArrayList compSub = (ArrayList)comp.get(k);
								  dto.setPropertyLockid((String) compSub.get(0));
								  dto.setRegistrationId((String) compSub.get(1));
								  dto.setRegistrationDate((String) compSub.get(2));
								  String prId = (String) compSub.get(3);
								  if (prId.length()==15){
									  prId = "0"+prId;
								  }
								  dto.setPropId(prId);
								  dto.setPoaRegNo((String) compSub.get(4));
								  dto.setPoaRegDate((String) compSub.get(5));
								  dto.setLockStatus((String) compSub.get(6));
								  dto.setPurpose((String) compSub.get(7));
								  dto.setDateLocking((String) compSub.get(8));
								 }
							  }
							  }
						}
				 //--party details
				    ArrayList partyDetls = new ArrayList();
					ArrayList comp2 = new ArrayList();
					partyDetls=lockBD.getPartyDetls(dto.getRegistrationId(),dto.getPropertyLockid(),language);
					if (partyDetls.size()>0){
							for(int i =0; i<partyDetls.size(); i++){
								comp2.add((ArrayList)partyDetls.get(i));
								  if(comp2 != null && comp2.size()>0){ 
								  for (int k=0; k< comp2.size(); k++){
									  ArrayList compSub = (ArrayList)comp2.get(k);
									  String apptype=(String) compSub.get(0);
									  dto.setAppType((String) compSub.get(0));
									  dto.setAppTypeName((String) compSub.get(1));
									  if(apptype.equalsIgnoreCase("2")){
										  dto.setFirstName((String) compSub.get(2));
										  dto.setMidName((String) compSub.get(3));
										  dto.setLastName((String) compSub.get(4));
										  dto.setGender((String) compSub.get(5));
										  dto.setAge((String) compSub.get(6));
										  dto.setAddress((String) compSub.get(10));
									      dto.setPhone((String) compSub.get(12));
									      dto.setMphone((String) compSub.get(13));
										  dto.setPin((String) compSub.get(11));
										  dto.setEmail((String) compSub.get(14));
										  /*dto.setIdProofName((String) compSub.get(15));
										  dto.setIdProofNo((String) compSub.get(16));*/
										  dto.setBankName((String) compSub.get(15));
										  dto.setBankAddress((String) compSub.get(16));
										  dto.setGuardianName((String) compSub.get(19));
										  dto.setMotherName((String) compSub.get(20));
										 }else
											 if(apptype.equalsIgnoreCase("1")){
												 dto.setOrgName((String) compSub.get(17));
												 dto.setAuthName((String) compSub.get(18));
												 dto.setOrgAddress((String) compSub.get(10));
												 dto.setOrgPhno((String) compSub.get(12));
												 dto.setOrgMobno((String) compSub.get(13));
											 }
									  dto.setOrgCountryName((String) compSub.get(7));
									  dto.setOrgStateName((String) compSub.get(8));
									  dto.setOrgDistrictName((String) compSub.get(9));
									  dto.setDocumentName((String) compSub.get(21));
									  dto.setThunmbName((String) compSub.get(22));
									  dto.setSignatureName((String) compSub.get(23));
									  dto.setPhotoPath((String) compSub.get(24));
									  dto.setThumbPath((String) compSub.get(25));
									  dto.setSignPath((String) compSub.get(26));
									
									 }
								  }
								  }
							}
					   ArrayList photoDetls = new ArrayList();
						ArrayList comp5 = new ArrayList();
						photoDetls=lockBD.getPhotoDetl(dto.getPropertyLockid(),language);
						if (photoDetls.size()>0){
							for(int i =0; i<photoDetls.size(); i++){
								comp5.add((ArrayList)photoDetls.get(i));
								  if(comp5 != null && comp5.size()>0){ 
								  for (int k=0; k< comp5.size(); k++){
									  ArrayList compSub = (ArrayList)comp5.get(k);
									  dto.setIdProofName((String) compSub.get(1));
									  dto.setIdProofNo((String) compSub.get(0));
								  }
								  }
								  }
							}
					
					
					formName="";
				    actionName="";
				    forwardPage="lockPrint";
				}
			}	
		
		}// end of print lock parameter
		
		if(request.getParameter("PrintLockP")!=null){
			nextPage = (String)request.getParameter("PrintLockP");
		
		if(!(nextPage).equalsIgnoreCase("")){
			if(nextPage.equalsIgnoreCase("Y")){
				
				ArrayList txnDetls = new ArrayList();
				ArrayList comp = new ArrayList();
				txnDetls=lockBD.getTxnDetlsAP(dto.getRegistrationId(), dto.getPropId(),dto.getPropertyLockid(),language);
				 if (txnDetls.size()>0){
						for(int i =0; i<txnDetls.size(); i++){
							comp.add((ArrayList)txnDetls.get(i));
							  if(comp != null && comp.size()>0){ 
							  for (int k=0; k< comp.size(); k++){
								  ArrayList compSub = (ArrayList)comp.get(k);
								  dto.setPropertyLockid((String) compSub.get(0));
								  dto.setRegistrationId((String) compSub.get(1));
								  dto.setRegistrationDate((String) compSub.get(2));
								  String prId = (String) compSub.get(3);
								  if (prId.length()==15){
									  prId = "0"+prId;
								  }
								  dto.setPropId(prId);
								  dto.setPoaRegNo((String) compSub.get(4));
								  dto.setPoaRegDate((String) compSub.get(5));
								  dto.setLockStatus((String) compSub.get(6));
								  dto.setPurpose((String) compSub.get(7));
								  dto.setDateLocking((String) compSub.get(8));
								 }
							  }
							  }
						}
				 //--party details
				    ArrayList partyDetls = new ArrayList();
					ArrayList comp2 = new ArrayList();
					partyDetls=lockBD.getPartyDetls(dto.getRegistrationId(),dto.getPropertyLockid(),language);
					if (partyDetls.size()>0){
							for(int i =0; i<partyDetls.size(); i++){
								comp2.add((ArrayList)partyDetls.get(i));
								  if(comp2 != null && comp2.size()>0){ 
								  for (int k=0; k< comp2.size(); k++){
									  ArrayList compSub = (ArrayList)comp2.get(k);
									  String apptype=(String) compSub.get(0);
									  dto.setAppType((String) compSub.get(0));
									  dto.setAppTypeName((String) compSub.get(1));
									  if(apptype.equalsIgnoreCase("2")){
										  dto.setFirstName((String) compSub.get(2));
										  dto.setMidName((String) compSub.get(3));
										  dto.setLastName((String) compSub.get(4));
										  dto.setGender((String) compSub.get(5));
										  dto.setAge((String) compSub.get(6));
										  dto.setAddress((String) compSub.get(10));
									      dto.setPhone((String) compSub.get(12));
									      dto.setMphone((String) compSub.get(13));
										  dto.setPin((String) compSub.get(11));
										  dto.setEmail((String) compSub.get(14));
										  /*dto.setIdProofName((String) compSub.get(15));
										  dto.setIdProofNo((String) compSub.get(16));*/
										  dto.setBankName((String) compSub.get(15));
										  dto.setBankAddress((String) compSub.get(16));
										  dto.setGuardianName((String) compSub.get(19));
										  dto.setMotherName((String) compSub.get(20));
										 }else
											 if(apptype.equalsIgnoreCase("1")){
												 dto.setOrgName((String) compSub.get(17));
												 dto.setAuthName((String) compSub.get(18));
												 dto.setOrgAddress((String) compSub.get(10));
												 dto.setOrgPhno((String) compSub.get(12));
												 dto.setOrgMobno((String) compSub.get(13));
											 }
									  dto.setOrgCountryName((String) compSub.get(7));
									  dto.setOrgStateName((String) compSub.get(8));
									  dto.setOrgDistrictName((String) compSub.get(9));
									  dto.setDocumentName((String) compSub.get(21));
									  dto.setThunmbName((String) compSub.get(22));
									  dto.setSignatureName((String) compSub.get(23));
									  dto.setPhotoPath((String) compSub.get(24));
									  dto.setThumbPath((String) compSub.get(25));
									  dto.setSignPath((String) compSub.get(26));
									
									 }
								  }
								  }
							}
					   ArrayList photoDetls = new ArrayList();
						ArrayList comp5 = new ArrayList();
						photoDetls=lockBD.getPhotoDetl(dto.getPropertyLockid(),language);
						if (photoDetls.size()>0){
							for(int i =0; i<photoDetls.size(); i++){
								comp5.add((ArrayList)photoDetls.get(i));
								  if(comp5 != null && comp5.size()>0){ 
								  for (int k=0; k< comp5.size(); k++){
									  ArrayList compSub = (ArrayList)comp5.get(k);
									  dto.setIdProofName((String) compSub.get(1));
									  dto.setIdProofNo((String) compSub.get(0));
								  }
								  }
								  }
							}
					
					
					formName="";
				    actionName="";
				    forwardPage="lockPrint";
				}
			}	
		
		}// end of print lockP parameter
	
		
		if(request.getParameter("PrintRelease")!=null){
			nextPage = (String)request.getParameter("PrintRelease");
		
		if(!(nextPage).equalsIgnoreCase("")){
			if(nextPage.equalsIgnoreCase("Y")){
				
				    ArrayList txnDetls = new ArrayList();
					ArrayList comp = new ArrayList();
					txnDetls=lockBD.getTxnDetlsR(dto.getRegistrationId(), dto.getPropId(),dto.getPropertyReleaseId(),language);
					 if (txnDetls.size()>0){
							for(int i =0; i<txnDetls.size(); i++){
								comp.add((ArrayList)txnDetls.get(i));
								  if(comp != null && comp.size()>0){ 
								  for (int k=0; k< comp.size(); k++){
									  ArrayList compSub = (ArrayList)comp.get(k);
									  dto.setPropertyReleaseId((String) compSub.get(0));
									  dto.setRegistrationId((String) compSub.get(1));
									  dto.setRegistrationDate((String) compSub.get(2));
									  String prId = (String) compSub.get(3);
									  if (prId.length()==15){
										  prId = "0"+prId;
									  }
									  dto.setPropId(prId);
									  dto.setPoaRegNo((String) compSub.get(4));
									  dto.setPoaRegDate((String) compSub.get(5));
									  dto.setLockStatus((String) compSub.get(6));
									  dto.setReasonForRelease((String) compSub.get(7));
									  dto.setReleaseDate((String) compSub.get(8));
									  dto.setRemarksForRelease((String) compSub.get(9));
									  dto.setReleaserType((String) compSub.get(10));
									 }
								  }
								  }
							}
					 //--party details
					if(dto.getReleaserType().equalsIgnoreCase("1")){
					ArrayList partyDetls = new ArrayList();
					ArrayList comp2 = new ArrayList();
					partyDetls=lockBD.getPartyDetls(dto.getRegistrationId(),dto.getPropertyReleaseId(),language);
					if (partyDetls.size()>0){
							for(int i =0; i<partyDetls.size(); i++){
								comp2.add((ArrayList)partyDetls.get(i));
								  if(comp2 != null && comp2.size()>0){ 
								  for (int k=0; k< comp2.size(); k++){
									  ArrayList compSub = (ArrayList)comp2.get(k);
									  String apptype=(String) compSub.get(0);
									  dto.setAppType((String) compSub.get(0));
									  dto.setAppTypeName((String) compSub.get(1));
									 if(apptype.equalsIgnoreCase("2")){
										  dto.setFirstName((String) compSub.get(2));
										  dto.setMidName((String) compSub.get(3));
										  dto.setLastName((String) compSub.get(4));
										  dto.setGender((String) compSub.get(5));
										  dto.setAge((String) compSub.get(6));
										  dto.setAddress((String) compSub.get(10));
									      dto.setPhone((String) compSub.get(12));
									      dto.setMphone((String) compSub.get(13));
										  dto.setPin((String) compSub.get(11));
										  dto.setEmail((String) compSub.get(14));
										  /*dto.setIdProofName((String) compSub.get(15));
										  dto.setIdProofNo((String) compSub.get(16));*/
										  dto.setBankName((String) compSub.get(15));
										  dto.setBankAddress((String) compSub.get(16));
										  dto.setGuardianName((String) compSub.get(19));
										  dto.setMotherName((String) compSub.get(20));
										 }else
											 if(apptype.equalsIgnoreCase("1")){
												 dto.setOrgName((String) compSub.get(17));
												 dto.setAuthName((String) compSub.get(18));
												 dto.setOrgAddress((String) compSub.get(10));
												 dto.setOrgPhno((String) compSub.get(12));
												 dto.setOrgMobno((String) compSub.get(13));
											 }
									  dto.setOrgCountryName((String) compSub.get(7));
									  dto.setOrgStateName((String) compSub.get(8));
									  dto.setOrgDistrictName((String) compSub.get(9));
									  dto.setDocumentName((String) compSub.get(21));
									  dto.setThunmbName((String) compSub.get(22));
									  dto.setSignatureName((String) compSub.get(23));
									  dto.setPhotoPath((String) compSub.get(24));
									  dto.setThumbPath((String) compSub.get(25));
									  dto.setSignPath((String) compSub.get(26));
									
									 }
								  }
								  }
							}
		         
					        ArrayList photoDetls = new ArrayList();
	 						ArrayList comp6 = new ArrayList();
	 						photoDetls=lockBD.getPhotoDetl(dto.getPropertyReleaseId(),language);
	 						if (photoDetls.size()>0){
	 							for(int i =0; i<photoDetls.size(); i++){
	 								comp6.add((ArrayList)photoDetls.get(i));
	 								  if(comp6 != null && comp6.size()>0){ 
	 								  for (int k=0; k< comp6.size(); k++){
	 									  ArrayList compSub = (ArrayList)comp6.get(k);
	 									  dto.setIdProofName((String) compSub.get(1));
	 									  dto.setIdProofNo((String) compSub.get(0));
	 								  }
	 								  }
	 								  }
	 							}
	 						if("en".equalsIgnoreCase(language))
	 						{
	 						dto.setPersonType("Applicant");
	 						}
	 						else
	 						{
	 							dto.setPersonType("आवेदक");
	 						}
	 						}// end of releaser type=1
					
					if(dto.getReleaserType().equalsIgnoreCase("2")){
						ArrayList relativeDetls = new ArrayList();
						ArrayList comp2 = new ArrayList();
						relativeDetls=lockBD.getRelativeDetls(dto.getRegistrationId(),dto.getPropertyReleaseId(),language);
						if (relativeDetls.size()>0){
								for(int i =0; i<relativeDetls.size(); i++){
									comp2.add((ArrayList)relativeDetls.get(i));
									  if(comp2 != null && comp2.size()>0){ 
									  for (int k=0; k< comp2.size(); k++){
										  ArrayList compSub = (ArrayList)comp2.get(k);
										  
										 dto.setReleaserName((String) compSub.get(0));
										 dto.setRelation((String) compSub.get(1));
										 dto.setRelFatherName((String) compSub.get(2));
										 dto.setRelMotherName((String) compSub.get(3));
										 dto.setRcountryName((String) compSub.get(4));
										 dto.setRstateName((String) compSub.get(5));
										 dto.setRdistrictName((String) compSub.get(6));
										 dto.setRelAddress((String) compSub.get(7));
										 dto.setRelMobNo((String) compSub.get(8));
										 dto.setRelphone((String) compSub.get(9));
										 dto.setRelEmail((String) compSub.get(10));
										 dto.setRelDeathCerName((String) compSub.get(11));
										 dto.setRelPhotoName((String) compSub.get(12));
										 dto.setRelThumbName((String) compSub.get(13));
										 dto.setRelSignName((String) compSub.get(14));
										 dto.setRelDeathCrtPath((String) compSub.get(15));
										 dto.setRelPhotoPath((String) compSub.get(16));
										 dto.setRelThumbPath((String) compSub.get(17));
										 dto.setRelSignPath((String) compSub.get(18));
										 if("en".equalsIgnoreCase(language))
										 {
										 dto.setPersonType("Relative");
										 }
										 else
										 {
											 dto.setPersonType("संबंधी");
										 }
										
										 }
									  }
									  }
								}
						}// end of releaser type=2
					
					
					
					formName="";
				    actionName="";
				    forwardPage="ReleasePrint";
				}
			}	
		
		}// end of print Release parameter
		
		
		
		
		
		
		
		
		
		
		// to open popup for registration details
		
		if(request.getParameter("popSearch")!=null){
		 String popSearch = request.getParameter("popSearch");
	        if (popSearch!=null)
	        {
	            if (popSearch.equals("true"))
	            {   
	            	 //dto.setIsPayment(0);
					 dto.setIsRegDetlEmpty(0);
					 dto.setIsAlrdyLocked(0);
					 dto.setNotInsertedLock(0);
					 dto.setNotInsertedRel(0);
					 dto.setIsLockSuccess(0);
					 dto.setIsRelSuccess(0);
					 dto.setIsLockSave(0);
					 dto.setIsReleaseSave(0);
					 dto.setIsPayCompl(0);
					 dto.setIsPartial(0);
					 dto.setNotInsertedP(0);
					 dto.setNotUpdatedAfterPay(0);
					 dto.setNoRecFound(0);
					 
					 dto.setForwrdAftrPrprtyDisp("");
					 dto.setAppType("");
					 dto.setAppTypeName("");
					 dto.setOrgName("");
					 dto.setAuthName("");
					 dto.setOrgCountry("");
					 dto.setOrgCountryName("");
					 dto.setOrgState("");
					 dto.setOrgStateName("");
					 dto.setOrgDistrict("");
					 dto.setOrgDistrictName("");
					 dto.setOrgAddress("");
					 dto.setOrgMobno("");
					 dto.setOrgPhno("");
					 dto.setIdProofName("");
					 dto.setPropertyLockid("");
					 dto.setAlrdyLockId("");
					 dto.setMapPropertyTransPartyDisp(new HashMap());
					 dto.setPropertyReleaseId("");
					 dto.setLockDetailsRelSearch(new ArrayList());
					 dto.setLockComb("");
					 dto.setRelationId("");
					 dto.setRegCompNumber("");
					 dto.setRegCompDate("");
					 dto.setPropId("");
					 dto.setPropStatus("");
					 dto.setPropDeed("");
					 dto.setPropAry("");
					 dto.setChckBx("");
					 dto.setPropComb("");
					 dto.setIsDisable("");
					 dto.setRadioSelect("");
					 dto.setRegDetls(new ArrayList());
					 dto.setPropList(new ArrayList());
					 //dto.setPayableFee("");
					 //dto.setAlrdyPaidFee("");
					// dto.setPrimKeyPymt("");
					 dto.setFlg("");
					 dto.setPhotoPath("");
					 dto.setThumbPath("");
					 dto.setSignPath("");
					 dto.setRegistrationId("");
					 dto.setRegistrationDate("");
					 dto.setFirstName("");
					 dto.setMidName("");
					 dto.setLastName("");
					 dto.setGender("");
					 dto.setGenClick("");
					 dto.setAge("");
					 dto.setGuardianName("");
					 dto.setMotherName("");
					 dto.setAddress("");
					 dto.setPin("");
					 dto.setPhone("");
					 dto.setMphone("");
					 dto.setEmail("");
					 dto.setIdProof("");
					 dto.setIdProofName("");
					 dto.setIdProofNo("");
					 dto.setBankName("");
					 dto.setBankAddress("");
					 dto.setFilePhoto(null);
					 dto.setDocumentName("");
					 dto.setFileThumb(null);
					 dto.setThunmbName("");
					 dto.setFileSignature(null);
					 dto.setSignatureName("");
					 dto.setPurpose("");
					 dto.setPoaRegDate("");
					 dto.setPoaRegNo("");
					 dto.setFromRequestDate("");
					 dto.setToRequestDate("");
					 dto.setDateLocking("");
					 dto.setLockStatus("");
					 dto.setPersonType("");
					 dto.setReleaserType("");
					 dto.setReleaserName("");
					 dto.setRelation("");
					 dto.setRelationId("");
					 dto.setRcountryId("");
					 dto.setRcountryName("");
					 dto.setRstate("");
					 dto.setRstateId("");
					 dto.setRstateName("");
					 dto.setRdistrictId("");
					 dto.setRdistrictName("");
					 dto.setRelFatherName("");
					 dto.setRelMotherName("");
					 dto.setRelphone("");
					 dto.setRelMobNo("");
					 dto.setRelEmail("");
					 dto.setRelAddress("");
					 dto.setRelAttachDoc(null);
					 dto.setRelPhotoName("");
					 dto.setRelPhotoPath("");
					 dto.setRelAttachthumb(null);
					 dto.setRelThumbName("");
					 dto.setRelThumbPath("");
					 dto.setRelAttaSig(null);
					 dto.setRelSignName("");
					 dto.setRelSignPath("");
					 dto.setRelDeathCerAttach(null);
					 dto.setRelDeathCerName("");
					 dto.setRelDeathCrtPath("");
					 dto.setReasonForRelease("");
					 dto.setRemarksForRelease("");
					 dto.setViewComb("");
					 dto.setViewResultList(new ArrayList());
					 dto.setPropertyTxnId("");
					 dto.setAppStatus("");
					 dto.setCreatedDt("");
					 dto.setIsViewEmpty(0);
					 dto.setIsFromView(0);	
	            	
					 //uncommented for testing scenario
	            	dto = eForm.getLockdto(); 
	            	 dto.setPropertyLockDisplayAction("");
	            	 eForm.getLockdto().setLockSearchAction("");
	            	 request.setAttribute("searchid","");
	                 request.setAttribute("lockstatus","");
	                 PropertyLockForm loForm = (PropertyLockForm) session.getAttribute("lockForm");
	                 request.setAttribute("lockForm",loForm);
	                 //end
	                 request.removeAttribute("popSearch");
	                 formName="";
	                 actionName="";
	                 //ADDED BY SHRUTI--4 APRIL 2014
	                 eForm.setActionName(actionName);
	                forwardPage = "popupSearch";   
	            }
	        }
	        }
		
		   // after payment-------
		
		
		
		//PropLock=lock
		 if(request.getParameter("PropLock")!=null){
			 if (request.getParameter("PropLock").equalsIgnoreCase("lock")){
			
            if(request.getParameter("paymentStatus")!=null){
			
			if (request.getParameter("paymentStatus").equalsIgnoreCase("P")){
				String uniId =  (String)request.getParameter("parentKey");
				if(uniId!=null||uniId!=""){
					dto.setPrimKeyPymt(uniId);
					}
				String mainId = lockBD.getMainId(uniId);
				dto.setPropertyLockid(mainId);
				
				
				ArrayList first = new ArrayList();
				ArrayList comp = new ArrayList();
				String uniqId="";
			    String amtToBePaid="";
			    String paidAmt="";
			    String pymtFlg="";
				first = lockBD.getPayDtls(mainId);
				
				if (first.size()>0){
				for(int i =0; i<first.size(); i++){
					comp.add((ArrayList)first.get(i));
					  if(comp != null && comp.size()>0){ 
					  for (int k=0; k< comp.size(); k++){
						  ArrayList compSub = (ArrayList)comp.get(k);
						  uniqId =      (String) compSub.get(0);
						  amtToBePaid = (String) compSub.get(1);
						  paidAmt =     (String) compSub.get(2);
						  pymtFlg =     (String) compSub.get(3);
						   }
					  }
					  }
				}
				double damtToBePaid = Double.parseDouble(amtToBePaid);
				double dpaidAmt = Double.parseDouble(paidAmt);
				//condition 1-------------------pending amount-------------
				if (damtToBePaid>dpaidAmt){
					DecimalFormat myformatter=new DecimalFormat("############");
					String totfee=dto.getTotalFee();
					double dtotfee=Double.parseDouble(totfee);
					double bal= damtToBePaid-dpaidAmt;
					double alrdP = dtotfee-bal;
					String balfrmt = myformatter.format(bal);
					String alrdFrmt = myformatter.format(alrdP);
					dto.setPayableFee(balfrmt);
					dto.setAlrdyPaidFee(alrdFrmt);
					dto.setIsPartial(1);
					forwardPage="lockPayView";
                     }
				
				//condition 2-------------------full payment, status change and data insertion------
				if (damtToBePaid==dpaidAmt){
					
					String lockId=dto.getPropertyLockid();
					userId = (String)session.getAttribute("UserId");
					
					dto.setNotUpdatedAfterPay(0);
					logger.debug("Inside change status method after payment----the lock id is-->>"+lockId);
					boolean ins=false;
					ins=lockBD.statusUpdateAfterP(lockId, userId, officeId);
					if(ins){
						dto.setPropertyLockid(lockId);
			        	// transaction details---
			        	ArrayList txnDetls = new ArrayList();
						ArrayList comp5 = new ArrayList();
						txnDetls=lockBD.getTxnDetlsAP(dto.getRegistrationId(), dto.getPropId(),lockId,language);
						 if (txnDetls.size()>0){
								for(int i =0; i<txnDetls.size(); i++){
									comp5.add((ArrayList)txnDetls.get(i));
									  if(comp5 != null && comp5.size()>0){ 
									  for (int k=0; k< comp5.size(); k++){
										  ArrayList compSub = (ArrayList)comp5.get(k);
										  dto.setPropertyLockid((String) compSub.get(0));
										  dto.setRegistrationId((String) compSub.get(1));
										  dto.setRegistrationDate((String) compSub.get(2));
										  String prId = (String) compSub.get(3);
										  if (prId.length()==15){
											  prId = "0"+prId;
										  }
										  dto.setPropId(prId);
										  dto.setPoaRegNo((String) compSub.get(4));
										  dto.setPoaRegDate((String) compSub.get(5));
										  dto.setLockStatus((String) compSub.get(6));
										  dto.setPurpose((String) compSub.get(7));
										  dto.setDateLocking((String) compSub.get(8));
										 }
									  }
									  }
								}
						 //--party details
						ArrayList partyDetls = new ArrayList();
 						ArrayList comp2 = new ArrayList();
 						partyDetls=lockBD.getPartyDetls(dto.getRegistrationId(),lockId,language);
 						if (partyDetls.size()>0){
 								for(int i =0; i<partyDetls.size(); i++){
 									comp2.add((ArrayList)partyDetls.get(i));
 									  if(comp2 != null && comp2.size()>0){ 
 									  for (int k=0; k< comp2.size(); k++){
 										  ArrayList compSub = (ArrayList)comp2.get(k);
 										  String apptype=(String) compSub.get(0);
 										  dto.setAppType((String) compSub.get(0));
 										  dto.setAppTypeName((String) compSub.get(1));
 										 if(apptype.equalsIgnoreCase("2")){
 											  dto.setFirstName((String) compSub.get(2));
 											  dto.setMidName((String) compSub.get(3));
 											  dto.setLastName((String) compSub.get(4));
 											  dto.setGender((String) compSub.get(5));
 											  dto.setAge((String) compSub.get(6));
 											  dto.setAddress((String) compSub.get(10));
 										      dto.setPhone((String) compSub.get(12));
 										      dto.setMphone((String) compSub.get(13));
 											  dto.setPin((String) compSub.get(11));
 											  dto.setEmail((String) compSub.get(14));
 											  /*dto.setIdProofName((String) compSub.get(15));
 											  dto.setIdProofNo((String) compSub.get(16));*/
 											  dto.setBankName((String) compSub.get(15));
 											  dto.setBankAddress((String) compSub.get(16));
 											  dto.setGuardianName((String) compSub.get(19));
 											  dto.setMotherName((String) compSub.get(20));
 											 }else
 												 if(apptype.equalsIgnoreCase("1")){
 													 dto.setOrgName((String) compSub.get(17));
 													 dto.setAuthName((String) compSub.get(18));
 													 dto.setOrgAddress((String) compSub.get(10));
 													 dto.setOrgPhno((String) compSub.get(12));
 													 dto.setOrgMobno((String) compSub.get(13));
 												 }
 										  dto.setOrgCountryName((String) compSub.get(7));
 										  dto.setOrgStateName((String) compSub.get(8));
 										  dto.setOrgDistrictName((String) compSub.get(9));
 										  dto.setDocumentName((String) compSub.get(21));
 										  dto.setThunmbName((String) compSub.get(22));
 										  dto.setSignatureName((String) compSub.get(23));
 										  dto.setPhotoPath((String) compSub.get(24));
 										  dto.setThumbPath((String) compSub.get(25));
 										  dto.setSignPath((String) compSub.get(26));
 										
 										 }
 									  }
 									  }
 								}
 						 ArrayList photoDetls = new ArrayList();
 						ArrayList comp6 = new ArrayList();
 						photoDetls=lockBD.getPhotoDetl(dto.getPropertyLockid(),language);
 						if (photoDetls.size()>0){
 							for(int i =0; i<photoDetls.size(); i++){
 								comp6.add((ArrayList)photoDetls.get(i));
 								  if(comp6 != null && comp6.size()>0){ 
 								  for (int k=0; k< comp6.size(); k++){
 									  ArrayList compSub = (ArrayList)comp6.get(k);
 									  dto.setIdProofName((String) compSub.get(1));
 									  dto.setIdProofNo((String) compSub.get(0));
 								  }
 								  }
 								  }
 							}
						formName="";
						actionName="";
						dto.setIsLockSuccess(1);
						dto.setIsLockSave(0);
						dto.setIsPartial(0);
						dto.setIsPayCompl(1);
							
							   forwardPage="lockPayView";
							}
						else{
							dto.setNotUpdatedAfterPay(1);
							forwardPage="lockPayView";
						}
					}// end of amtToBePaid=PaidAmt
				
				
				formName="";
				actionName="";
		
			}
			} //--end of payment parameter!=null.
			 }
		 }// end of payment param for lock
		
		 
		 
		 
		 // start of payment param for PropRelease=release
		 if(request.getParameter("PropRelease")!=null){
			 if (request.getParameter("PropRelease").equalsIgnoreCase("release")){
			
            if(request.getParameter("paymentStatus")!=null){
			
			if (request.getParameter("paymentStatus").equalsIgnoreCase("P")){
				String uniId =  (String)request.getParameter("parentKey");
				if(uniId!=null||uniId!=""){
					dto.setPrimKeyPymt(uniId);
					}
				String mainId = lockBD.getMainId(uniId);
				dto.setPropertyReleaseId(mainId);
				
				
				ArrayList first = new ArrayList();
				ArrayList comp = new ArrayList();
				String uniqId="";
			    String amtToBePaid="";
			    String paidAmt="";
			    String pymtFlg="";
				first = lockBD.getPayDtls(mainId);
				
				if (first.size()>0){
				for(int i =0; i<first.size(); i++){
					comp.add((ArrayList)first.get(i));
					  if(comp != null && comp.size()>0){ 
					  for (int k=0; k< comp.size(); k++){
						  ArrayList compSub = (ArrayList)comp.get(k);
						  uniqId =      (String) compSub.get(0);
						  amtToBePaid = (String) compSub.get(1);
						  paidAmt =     (String) compSub.get(2);
						  pymtFlg =     (String) compSub.get(3);
						   }
					  }
					  }
				}
				double damtToBePaid = Double.parseDouble(amtToBePaid);
				double dpaidAmt = Double.parseDouble(paidAmt);
				//condition 1-------------------pending amount-------------
				if (damtToBePaid>dpaidAmt){
					DecimalFormat myformatter=new DecimalFormat("############");
					String totfee=dto.getTotalFee();
					double dtotfee=Double.parseDouble(totfee);
					double bal= damtToBePaid-dpaidAmt;
					double alrdP = dtotfee-bal;
					String balfrmt = myformatter.format(bal);
					String alrdFrmt = myformatter.format(alrdP);
					dto.setPayableFee(balfrmt);
					dto.setAlrdyPaidFee(alrdFrmt);
					dto.setIsPartial(1);
					forwardPage="releasePayView";
                     }
				
				//condition 2-------------------full payment, status change and data insertion------
				if (damtToBePaid==dpaidAmt){
					
					String releaseId=dto.getPropertyReleaseId();
					userId = (String)session.getAttribute("UserId");
					
					dto.setNotUpdatedAfterPay(0);
					logger.debug("Inside change status method after payment----the release id is-->>"+releaseId);
					boolean ins=false;
					ins=lockBD.statusUpdateAfterPR(releaseId, userId, officeId);
					if(ins){
						dto.setPropertyReleaseId(releaseId);
			        	// transaction details---
			        	ArrayList txnDetls = new ArrayList();
						ArrayList comp5 = new ArrayList();
						txnDetls=lockBD.getTxnDetlsR(dto.getRegistrationId(), dto.getPropId(),releaseId,language);
						 if (txnDetls.size()>0){
								for(int i =0; i<txnDetls.size(); i++){
									comp5.add((ArrayList)txnDetls.get(i));
									  if(comp5 != null && comp5.size()>0){ 
									  for (int k=0; k< comp5.size(); k++){
										  ArrayList compSub = (ArrayList)comp5.get(k);
										  dto.setPropertyReleaseId((String) compSub.get(0));
										  dto.setRegistrationId((String) compSub.get(1));
										  dto.setRegistrationDate((String) compSub.get(2));
										  String prId = (String) compSub.get(3);
										  if (prId.length()==15){
											  prId = "0"+prId;
										  }
										  dto.setPropId(prId);
										  dto.setPoaRegNo((String) compSub.get(4));
										  dto.setPoaRegDate((String) compSub.get(5));
										  dto.setLockStatus((String) compSub.get(6));
										  dto.setReasonForRelease((String) compSub.get(7));
										  dto.setReleaseDate((String) compSub.get(8));
										  dto.setRemarksForRelease((String) compSub.get(9));
										  dto.setReleaserType((String) compSub.get(10));
										 }
									  }
									  }
								}
						 //--party details
						 if(dto.getReleaserType().equalsIgnoreCase("1")){
	     						ArrayList partyDetls = new ArrayList();
	      						ArrayList comp2 = new ArrayList();
	      						partyDetls=lockBD.getPartyDetls(dto.getRegistrationId(),releaseId,language);
	      						if (partyDetls.size()>0){
	      								for(int i =0; i<partyDetls.size(); i++){
	      									comp2.add((ArrayList)partyDetls.get(i));
	      									  if(comp2 != null && comp2.size()>0){ 
	      									  for (int k=0; k< comp2.size(); k++){
	      										  ArrayList compSub = (ArrayList)comp2.get(k);
	      										  String apptype=(String) compSub.get(0);
	      										  dto.setAppType((String) compSub.get(0));
	      										  dto.setAppTypeName((String) compSub.get(1));
	      										 if(apptype.equalsIgnoreCase("2")){
	      											  dto.setFirstName((String) compSub.get(2));
	      											  dto.setMidName((String) compSub.get(3));
	      											  dto.setLastName((String) compSub.get(4));
	      											  dto.setGender((String) compSub.get(5));
	      											  dto.setAge((String) compSub.get(6));
	      											  dto.setAddress((String) compSub.get(10));
	      										      dto.setPhone((String) compSub.get(12));
	      										      dto.setMphone((String) compSub.get(13));
	      											  dto.setPin((String) compSub.get(11));
	      											  dto.setEmail((String) compSub.get(14));
	      											  /*dto.setIdProofName((String) compSub.get(15));
	      											  dto.setIdProofNo((String) compSub.get(16));*/
	      											  dto.setBankName((String) compSub.get(15));
	      											  dto.setBankAddress((String) compSub.get(16));
	      											  dto.setGuardianName((String) compSub.get(19));
	      											  dto.setMotherName((String) compSub.get(20));
	      											 }else
	      												 if(apptype.equalsIgnoreCase("1")){
	      													 dto.setOrgName((String) compSub.get(17));
	      													 dto.setAuthName((String) compSub.get(18));
	      													 dto.setOrgAddress((String) compSub.get(10));
	      													 dto.setOrgPhno((String) compSub.get(12));
	      													 dto.setOrgMobno((String) compSub.get(13));
	      												 }
	      										  dto.setOrgCountryName((String) compSub.get(7));
	      										  dto.setOrgStateName((String) compSub.get(8));
	      										  dto.setOrgDistrictName((String) compSub.get(9));
	      										  dto.setDocumentName((String) compSub.get(21));
	      										  dto.setThunmbName((String) compSub.get(22));
	      										  dto.setSignatureName((String) compSub.get(23));
	      										  dto.setPhotoPath((String) compSub.get(24));
	      										  dto.setThumbPath((String) compSub.get(25));
	      										  dto.setSignPath((String) compSub.get(26));
	      										
	      										 }
	      									  }
	      									  }
	      								}
	     			         
	      						        ArrayList photoDetls = new ArrayList();
	      		 						ArrayList comp6 = new ArrayList();
	      		 						photoDetls=lockBD.getPhotoDetl(releaseId,language);
	      		 						if (photoDetls.size()>0){
	      		 							for(int i =0; i<photoDetls.size(); i++){
	      		 								comp6.add((ArrayList)photoDetls.get(i));
	      		 								  if(comp6 != null && comp6.size()>0){ 
	      		 								  for (int k=0; k< comp6.size(); k++){
	      		 									  ArrayList compSub = (ArrayList)comp6.get(k);
	      		 									  dto.setIdProofName((String) compSub.get(1));
	      		 									  dto.setIdProofNo((String) compSub.get(0));
	      		 								  }
	      		 								  }
	      		 								  }
	      		 							}
	      		 						if("en".equalsIgnoreCase(language))
	      		 						{
	      		 						dto.setPersonType("Applicant");
	      		 						}
	      		 						else
	      		 						{
	      		 							dto.setPersonType("आवेदक");
	      		 						}
	      		 						}// end of releaser type=1
	     						
	     						if(dto.getReleaserType().equalsIgnoreCase("2")){
	     							ArrayList relativeDetls = new ArrayList();
		      						ArrayList comp2 = new ArrayList();
		      						relativeDetls=lockBD.getRelativeDetls(dto.getRegistrationId(),releaseId,language);
		      						if (relativeDetls.size()>0){
		      								for(int i =0; i<relativeDetls.size(); i++){
		      									comp2.add((ArrayList)relativeDetls.get(i));
		      									  if(comp2 != null && comp2.size()>0){ 
		      									  for (int k=0; k< comp2.size(); k++){
		      										  ArrayList compSub = (ArrayList)comp2.get(k);
		      										  
		      										 dto.setReleaserName((String) compSub.get(0));
		      										 dto.setRelation((String) compSub.get(1));
		      										 dto.setRelFatherName((String) compSub.get(2));
		      										 dto.setRelMotherName((String) compSub.get(3));
		      										 dto.setRcountryName((String) compSub.get(4));
		      										 dto.setRstateName((String) compSub.get(5));
		      										 dto.setRdistrictName((String) compSub.get(6));
		      										 dto.setRelAddress((String) compSub.get(7));
		      										 dto.setRelMobNo((String) compSub.get(8));
		      										 dto.setRelphone((String) compSub.get(9));
		      										 dto.setRelEmail((String) compSub.get(10));
		      										 dto.setRelDeathCerName((String) compSub.get(11));
		      										 dto.setRelPhotoName((String) compSub.get(12));
		      										 dto.setRelThumbName((String) compSub.get(13));
		      										 dto.setRelSignName((String) compSub.get(14));
		      										 dto.setRelDeathCrtPath((String) compSub.get(15));
		      										 dto.setRelPhotoPath((String) compSub.get(16));
		      										 dto.setRelThumbPath((String) compSub.get(17));
		      										 dto.setRelSignPath((String) compSub.get(18));
		      										 
		      										if("en".equalsIgnoreCase(language))
		   										 {
		   										 dto.setPersonType("Relative");
		   										 }
		   										 else
		   										 {
		   											 dto.setPersonType("संबंधी");
		   										 }
		      										
		      										 }
		      									  }
		      									  }
		      								}
		      						}// end of releaser type=2
						 
						formName="";
						actionName="";
						dto.setIsRelSuccess(1);
						dto.setIsReleaseSave(0);
						dto.setIsPartial(0);
						dto.setIsPayCompl(1);
							
						forwardPage="releasePayView";
							}
						else{
							dto.setNotUpdatedAfterPay(1);
							forwardPage="releasePayView";
						}
					}// end of amtToBePaid=PaidAmt
				
				
				formName="";
				actionName="";
		
			}
			} //--end of payment parameter!=null.
			 }
		 }// end of payment param for PropRelease=release
		 
		 
		 
		 
		 
		 
// start of dashboard Lock-------
   		if(request.getParameter("actionLinkLock")!=null){
   			startPage = (String)request.getParameter("actionLinkLock");
   		
   		if(!(startPage).equalsIgnoreCase("")){
   			if(startPage.equalsIgnoreCase("hyperClickLock")){
   				String combDetl = request.getParameter("pendingDtCumStatus");
   				String stCumDtList[]= combDetl.split("~");
   				DecimalFormat myformatter=new DecimalFormat("############");
   				String lockid="";
   				String propId="";
   				String regno="";
   				String regdt="";
   				String status="";
   				if(stCumDtList.length == 5)
				{
   					lockid= stCumDtList[0];
   					propId= stCumDtList[1];
   					if(propId.length()==15){
   						propId="0"+propId;
   					}
   					regno= stCumDtList[2];
   					regdt= stCumDtList[3];
   					status= stCumDtList[4];
   					
				}
   				
   				// payment details------
   				ArrayList first = new ArrayList();
				first = lockBD.getPayDtls(lockid);
				if (first.size()==0){
					logger.debug("inside if");
					dto.setIsPayment(1);
					dto.setAlrdyPaidFee("0");
					dto.setPayableFee(dto.getTotalFee());
					
					// transaction details---
		        	ArrayList txnDetls = new ArrayList();
					ArrayList comp5 = new ArrayList();
					txnDetls=lockBD.getTxnDetlsP(regno,propId,lockid,language);
					 if (txnDetls.size()>0){
							for(int i =0; i<txnDetls.size(); i++){
								comp5.add((ArrayList)txnDetls.get(i));
								  if(comp5 != null && comp5.size()>0){ 
								  for (int k=0; k< comp5.size(); k++){
									  ArrayList compSub = (ArrayList)comp5.get(k);
									  dto.setPropertyLockid((String) compSub.get(0));
									  dto.setRegistrationId((String) compSub.get(1));
									  dto.setRegistrationDate((String) compSub.get(2));
									  String prId = (String) compSub.get(3);
									  if (prId.length()==15){
										  prId = "0"+prId;
									  }
									  dto.setPropId(prId);
									  dto.setPoaRegNo((String) compSub.get(4));
									  dto.setPoaRegDate((String) compSub.get(5));
									  dto.setLockStatus((String) compSub.get(6));
									  dto.setPurpose((String) compSub.get(7));
									  dto.setDateLocking((String) compSub.get(8));
									 }
								  }
								  }
							}
					 //--party details
					ArrayList partyDetls = new ArrayList();
						ArrayList comp2 = new ArrayList();
						partyDetls=lockBD.getPartyDetls(regno,lockid,language);
						if (partyDetls.size()>0){
								for(int i =0; i<partyDetls.size(); i++){
									comp2.add((ArrayList)partyDetls.get(i));
									  if(comp2 != null && comp2.size()>0){ 
									  for (int k=0; k< comp2.size(); k++){
										  ArrayList compSub = (ArrayList)comp2.get(k);
										  String apptype=(String) compSub.get(0);
										  dto.setAppType((String) compSub.get(0));
										  dto.setAppTypeName((String) compSub.get(1));
										  if(apptype.equalsIgnoreCase("2")){
											  dto.setFirstName((String) compSub.get(2));
											  dto.setMidName((String) compSub.get(3));
											  dto.setLastName((String) compSub.get(4));
											  dto.setGender((String) compSub.get(5));
											  dto.setAge((String) compSub.get(6));
											  dto.setAddress((String) compSub.get(10));
										      dto.setPhone((String) compSub.get(12));
										      dto.setMphone((String) compSub.get(13));
											  dto.setPin((String) compSub.get(11));
											  dto.setEmail((String) compSub.get(14));
											  /*dto.setIdProofName((String) compSub.get(15));
											  dto.setIdProofNo((String) compSub.get(16));*/
											  dto.setBankName((String) compSub.get(15));
											  dto.setBankAddress((String) compSub.get(16));
											  dto.setGuardianName((String) compSub.get(19));
											  dto.setMotherName((String) compSub.get(20));
											 }else
												 if(apptype.equalsIgnoreCase("1")){
													 dto.setOrgName((String) compSub.get(17));
													 dto.setAuthName((String) compSub.get(18));
													 dto.setOrgAddress((String) compSub.get(10));
													 dto.setOrgPhno((String) compSub.get(12));
													 dto.setOrgMobno((String) compSub.get(13));
												 }
										  dto.setOrgCountryName((String) compSub.get(7));
										  dto.setOrgStateName((String) compSub.get(8));
										  dto.setOrgDistrictName((String) compSub.get(9));
										  dto.setDocumentName((String) compSub.get(21));
										  dto.setThunmbName((String) compSub.get(22));
										  dto.setSignatureName((String) compSub.get(23));
										  dto.setPhotoPath((String) compSub.get(24));
										  dto.setThumbPath((String) compSub.get(25));
										  dto.setSignPath((String) compSub.get(26));
										
										 }
									  }
									  }
								}
						ArrayList photoDetls = new ArrayList();
 						ArrayList comp6 = new ArrayList();
 						photoDetls=lockBD.getPhotoDetl(dto.getPropertyLockid(),language);
 						if (photoDetls.size()>0){
 							for(int i =0; i<photoDetls.size(); i++){
 								comp6.add((ArrayList)photoDetls.get(i));
 								  if(comp6 != null && comp6.size()>0){ 
 								  for (int k=0; k< comp6.size(); k++){
 									  ArrayList compSub = (ArrayList)comp6.get(k);
 									  dto.setIdProofName((String) compSub.get(1));
 									  dto.setIdProofNo((String) compSub.get(0));
 								  }
 								  }
 								  }
 							}
					dto.setIsLockSave(1);
					dto.setNotInsertedP(0);
					dto.setNotUpdatedAfterPay(0);
					dto.setIsPartial(0);
					dto.setIsPayCompl(0);
					forwardPage = "lockPayView";
					}
				// if pay details are not empty
				else{
						logger.debug("inside else---means record is found");
						ArrayList comp = new ArrayList();
						String uniqId="";
					    String amtToBePaid="";
					    String paidAmt="";
					    String pymtFlg="";
						
						for(int i =0; i<first.size(); i++){
							comp.add((ArrayList)first.get(i));
							  if(comp != null && comp.size()>0){ 
							  for (int k=0; k< comp.size(); k++){
								  ArrayList compSub = (ArrayList)comp.get(k);
								  uniqId =      (String) compSub.get(0);
								  amtToBePaid = (String) compSub.get(1);
								  paidAmt =     (String) compSub.get(2);
								  pymtFlg =     (String) compSub.get(3);
								    logger.debug("unique id is ............................"+uniqId);
									logger.debug("unpayable amt is ............................"+amtToBePaid);
									logger.debug("paid amt is..................................."+paidAmt);
									logger.debug("pymtFlg is....................................."+pymtFlg);
								 }
							  }
							  }
						double damtToBePaid = Double.parseDouble(amtToBePaid);
						
						//------- if payment flag is I ----
						if(pymtFlg.equalsIgnoreCase("I")){
							String totfee=dto.getTotalFee();
							double dtotfee=Double.parseDouble(totfee);
							double alrdP = dtotfee-damtToBePaid;
							String balfrmt = myformatter.format(damtToBePaid);
							String alrdFrmt = myformatter.format(alrdP);
							dto.setPayableFee(balfrmt);
							dto.setAlrdyPaidFee(alrdFrmt);
							
							// transaction details---
				        	ArrayList txnDetls = new ArrayList();
							ArrayList comp5 = new ArrayList();
							txnDetls=lockBD.getTxnDetlsP(regno,propId,lockid,language);
							 if (txnDetls.size()>0){
									for(int i =0; i<txnDetls.size(); i++){
										comp5.add((ArrayList)txnDetls.get(i));
										  if(comp5 != null && comp5.size()>0){ 
										  for (int k=0; k< comp5.size(); k++){
											  ArrayList compSub = (ArrayList)comp5.get(k);
											  dto.setPropertyLockid((String) compSub.get(0));
											  dto.setRegistrationId((String) compSub.get(1));
											  dto.setRegistrationDate((String) compSub.get(2));
											  String prId = (String) compSub.get(3);
											  if (prId.length()==15){
												  prId = "0"+prId;
											  }
											  dto.setPropId(prId);
											  dto.setPoaRegNo((String) compSub.get(4));
											  dto.setPoaRegDate((String) compSub.get(5));
											  dto.setLockStatus((String) compSub.get(6));
											  dto.setPurpose((String) compSub.get(7));
											  dto.setDateLocking((String) compSub.get(8));
											 }
										  }
										  }
									}
							 //--party details
							ArrayList partyDetls = new ArrayList();
	 						ArrayList comp2 = new ArrayList();
	 						partyDetls=lockBD.getPartyDetls(regno,lockid,language);
	 						if (partyDetls.size()>0){
	 								for(int i =0; i<partyDetls.size(); i++){
	 									comp2.add((ArrayList)partyDetls.get(i));
	 									  if(comp2 != null && comp2.size()>0){ 
	 									  for (int k=0; k< comp2.size(); k++){
	 										  ArrayList compSub = (ArrayList)comp2.get(k);
	 										  String apptype=(String) compSub.get(0);
	 										  dto.setAppType((String) compSub.get(0));
	 										  dto.setAppTypeName((String) compSub.get(1));
	 										 if(apptype.equalsIgnoreCase("2")){
	 											  dto.setFirstName((String) compSub.get(2));
	 											  dto.setMidName((String) compSub.get(3));
	 											  dto.setLastName((String) compSub.get(4));
	 											  dto.setGender((String) compSub.get(5));
	 											  dto.setAge((String) compSub.get(6));
	 											  dto.setAddress((String) compSub.get(10));
	 										      dto.setPhone((String) compSub.get(12));
	 										      dto.setMphone((String) compSub.get(13));
	 											  dto.setPin((String) compSub.get(11));
	 											  dto.setEmail((String) compSub.get(14));
	 											  /*dto.setIdProofName((String) compSub.get(15));
	 											  dto.setIdProofNo((String) compSub.get(16));*/
	 											  dto.setBankName((String) compSub.get(15));
	 											  dto.setBankAddress((String) compSub.get(16));
	 											  dto.setGuardianName((String) compSub.get(19));
	 											  dto.setMotherName((String) compSub.get(20));
	 											 }else
	 												 if(apptype.equalsIgnoreCase("1")){
	 													 dto.setOrgName((String) compSub.get(17));
	 													 dto.setAuthName((String) compSub.get(18));
	 													 dto.setOrgAddress((String) compSub.get(10));
	 													 dto.setOrgPhno((String) compSub.get(12));
	 													 dto.setOrgMobno((String) compSub.get(13));
	 												 }
	 										  dto.setOrgCountryName((String) compSub.get(7));
	 										  dto.setOrgStateName((String) compSub.get(8));
	 										  dto.setOrgDistrictName((String) compSub.get(9));
	 										  dto.setDocumentName((String) compSub.get(21));
	 										  dto.setThunmbName((String) compSub.get(22));
	 										  dto.setSignatureName((String) compSub.get(23));
	 										  dto.setPhotoPath((String) compSub.get(24));
	 										  dto.setThumbPath((String) compSub.get(25));
	 										  dto.setSignPath((String) compSub.get(26));
	 										
	 										 }
	 									  }
	 									  }
	 								}ArrayList photoDetls = new ArrayList();
	 		 						ArrayList comp6 = new ArrayList();
	 		 						photoDetls=lockBD.getPhotoDetl(dto.getPropertyLockid(),language);
	 		 						if (photoDetls.size()>0){
	 		 							for(int i =0; i<photoDetls.size(); i++){
	 		 								comp6.add((ArrayList)photoDetls.get(i));
	 		 								  if(comp6 != null && comp6.size()>0){ 
	 		 								  for (int k=0; k< comp6.size(); k++){
	 		 									  ArrayList compSub = (ArrayList)comp6.get(k);
	 		 									  dto.setIdProofName((String) compSub.get(1));
	 		 									  dto.setIdProofNo((String) compSub.get(0));
	 		 								  }
	 		 								  }
	 		 								  }
	 		 							}
							dto.setIsLockSave(1);
							dto.setNotInsertedP(0);
							dto.setNotUpdatedAfterPay(0);
							dto.setIsPartial(1);
							dto.setIsPayCompl(0);
							forwardPage = "lockPayView";
							
						}
						//----------- if payment flag is P -----
						
						if(pymtFlg.equalsIgnoreCase("P")){
							
							double dpaidAmt = Double.parseDouble(paidAmt);
							//condition 1-------------------pending amount-------------
							if (damtToBePaid>dpaidAmt){
								
								String totfee=dto.getTotalFee();
								double dtotfee=Double.parseDouble(totfee);
								double bal= damtToBePaid-dpaidAmt;
								double alrdP = dtotfee-bal;
								String balfrmt = myformatter.format(bal);
								String alrdFrmt = myformatter.format(alrdP);
								dto.setPayableFee(balfrmt);
								dto.setAlrdyPaidFee(alrdFrmt);
								
								
								// transaction details---
					        	ArrayList txnDetls = new ArrayList();
								ArrayList comp5 = new ArrayList();
								txnDetls=lockBD.getTxnDetlsP(regno,propId,lockid,language);
								 if (txnDetls.size()>0){
										for(int i =0; i<txnDetls.size(); i++){
											comp5.add((ArrayList)txnDetls.get(i));
											  if(comp5 != null && comp5.size()>0){ 
											  for (int k=0; k< comp5.size(); k++){
												  ArrayList compSub = (ArrayList)comp5.get(k);
												  dto.setPropertyLockid((String) compSub.get(0));
												  dto.setRegistrationId((String) compSub.get(1));
												  dto.setRegistrationDate((String) compSub.get(2));
												  String prId = (String) compSub.get(3);
												  if (prId.length()==15){
													  prId = "0"+prId;
												  }
												  dto.setPropId(prId);
												  dto.setPoaRegNo((String) compSub.get(4));
												  dto.setPoaRegDate((String) compSub.get(5));
												  dto.setLockStatus((String) compSub.get(6));
												  dto.setPurpose((String) compSub.get(7));
												  dto.setDateLocking((String) compSub.get(8));
												 }
											  }
											  }
										}
								 //--party details
								ArrayList partyDetls = new ArrayList();
		 						ArrayList comp2 = new ArrayList();
		 						partyDetls=lockBD.getPartyDetls(regno,lockid,language);
		 						if (partyDetls.size()>0){
		 								for(int i =0; i<partyDetls.size(); i++){
		 									comp2.add((ArrayList)partyDetls.get(i));
		 									  if(comp2 != null && comp2.size()>0){ 
		 									  for (int k=0; k< comp2.size(); k++){
		 										  ArrayList compSub = (ArrayList)comp2.get(k);
		 										  String apptype=(String) compSub.get(0);
		 										  dto.setAppType((String) compSub.get(0));
		 										  dto.setAppTypeName((String) compSub.get(1));
		 										 if(apptype.equalsIgnoreCase("2")){
		 											  dto.setFirstName((String) compSub.get(2));
		 											  dto.setMidName((String) compSub.get(3));
		 											  dto.setLastName((String) compSub.get(4));
		 											  dto.setGender((String) compSub.get(5));
		 											  dto.setAge((String) compSub.get(6));
		 											  dto.setAddress((String) compSub.get(10));
		 										      dto.setPhone((String) compSub.get(12));
		 										      dto.setMphone((String) compSub.get(13));
		 											  dto.setPin((String) compSub.get(11));
		 											  dto.setEmail((String) compSub.get(14));
		 											  /*dto.setIdProofName((String) compSub.get(15));
		 											  dto.setIdProofNo((String) compSub.get(16));*/
		 											  dto.setBankName((String) compSub.get(15));
		 											  dto.setBankAddress((String) compSub.get(16));
		 											  dto.setGuardianName((String) compSub.get(19));
		 											  dto.setMotherName((String) compSub.get(20));
		 											 }else
		 												 if(apptype.equalsIgnoreCase("1")){
		 													 dto.setOrgName((String) compSub.get(17));
		 													 dto.setAuthName((String) compSub.get(18));
		 													 dto.setOrgAddress((String) compSub.get(10));
		 													 dto.setOrgPhno((String) compSub.get(12));
		 													 dto.setOrgMobno((String) compSub.get(13));
		 												 }
		 										  dto.setOrgCountryName((String) compSub.get(7));
		 										  dto.setOrgStateName((String) compSub.get(8));
		 										  dto.setOrgDistrictName((String) compSub.get(9));
		 										  dto.setDocumentName((String) compSub.get(21));
		 										  dto.setThunmbName((String) compSub.get(22));
		 										  dto.setSignatureName((String) compSub.get(23));
		 										  dto.setPhotoPath((String) compSub.get(24));
		 										  dto.setThumbPath((String) compSub.get(25));
		 										  dto.setSignPath((String) compSub.get(26));
		 										
		 										
		 										 }
		 									  }
		 									  }
		 								}ArrayList photoDetls = new ArrayList();
		 		 						ArrayList comp6 = new ArrayList();
		 		 						photoDetls=lockBD.getPhotoDetl(dto.getPropertyLockid(),language);
		 		 						if (photoDetls.size()>0){
		 		 							for(int i =0; i<photoDetls.size(); i++){
		 		 								comp6.add((ArrayList)photoDetls.get(i));
		 		 								  if(comp6 != null && comp6.size()>0){ 
		 		 								  for (int k=0; k< comp6.size(); k++){
		 		 									  ArrayList compSub = (ArrayList)comp6.get(k);
		 		 									  dto.setIdProofName((String) compSub.get(1));
		 		 									  dto.setIdProofNo((String) compSub.get(0));
		 		 								  }
		 		 								  }
		 		 								  }
		 		 							}
		 						dto.setIsLockSave(1);
								dto.setNotInsertedP(0);
								dto.setNotUpdatedAfterPay(0);
								dto.setIsPartial(1);
								dto.setIsPayCompl(0);
								forwardPage="lockPayView";
								}
							if (damtToBePaid==dpaidAmt){
								userId = (String)session.getAttribute("UserId");
								
								dto.setNotUpdatedAfterPay(0);
								logger.debug("Inside change status method after payment----the lock id is-->>"+lockid);
								dto.setPropertyLockid(lockid);
								boolean ins=false;
								ins=lockBD.statusUpdateAfterP(lockid, userId, officeId);
								if(ins){
						        	// transaction details---
						        	ArrayList txnDetls = new ArrayList();
									ArrayList comp5 = new ArrayList();
									txnDetls=lockBD.getTxnDetlsAP(regno,propId,lockid,language);
									 if (txnDetls.size()>0){
											for(int i =0; i<txnDetls.size(); i++){
												comp5.add((ArrayList)txnDetls.get(i));
												  if(comp5 != null && comp5.size()>0){ 
												  for (int k=0; k< comp5.size(); k++){
													  ArrayList compSub = (ArrayList)comp5.get(k);
													  dto.setPropertyLockid((String) compSub.get(0));
													  dto.setRegistrationId((String) compSub.get(1));
													  dto.setRegistrationDate((String) compSub.get(2));
													  String prId = (String) compSub.get(3);
													  if (prId.length()==15){
														  prId = "0"+prId;
													  }
													  dto.setPropId(prId);
													  dto.setPoaRegNo((String) compSub.get(4));
													  dto.setPoaRegDate((String) compSub.get(5));
													  dto.setLockStatus((String) compSub.get(6));
													  dto.setPurpose((String) compSub.get(7));
													  dto.setDateLocking((String) compSub.get(8));
													 }
												  }
												  }
											}
									 //--party details
									ArrayList partyDetls = new ArrayList();
			 						ArrayList comp2 = new ArrayList();
			 						partyDetls=lockBD.getPartyDetls(regno,lockid,language);
			 						if (partyDetls.size()>0){
			 								for(int i =0; i<partyDetls.size(); i++){
			 									comp2.add((ArrayList)partyDetls.get(i));
			 									  if(comp2 != null && comp2.size()>0){ 
			 									  for (int k=0; k< comp2.size(); k++){
			 										  ArrayList compSub = (ArrayList)comp2.get(k);
			 										  String apptype=(String) compSub.get(0);
			 										  dto.setAppType((String) compSub.get(0));
			 										  dto.setAppTypeName((String) compSub.get(1));
			 										 if(apptype.equalsIgnoreCase("2")){
			 											  dto.setFirstName((String) compSub.get(2));
			 											  dto.setMidName((String) compSub.get(3));
			 											  dto.setLastName((String) compSub.get(4));
			 											  dto.setGender((String) compSub.get(5));
			 											  dto.setAge((String) compSub.get(6));
			 											  dto.setAddress((String) compSub.get(10));
			 										      dto.setPhone((String) compSub.get(12));
			 										      dto.setMphone((String) compSub.get(13));
			 											  dto.setPin((String) compSub.get(11));
			 											  dto.setEmail((String) compSub.get(14));
			 											  /*dto.setIdProofName((String) compSub.get(15));
			 											  dto.setIdProofNo((String) compSub.get(16));*/
			 											  dto.setBankName((String) compSub.get(15));
			 											  dto.setBankAddress((String) compSub.get(16));
			 											  dto.setGuardianName((String) compSub.get(19));
			 											  dto.setMotherName((String) compSub.get(20));
			 											 }else
			 												 if(apptype.equalsIgnoreCase("1")){
			 													 dto.setOrgName((String) compSub.get(17));
			 													 dto.setAuthName((String) compSub.get(18));
			 													 dto.setOrgAddress((String) compSub.get(10));
			 													 dto.setOrgPhno((String) compSub.get(12));
			 													 dto.setOrgMobno((String) compSub.get(13));
			 												 }
			 										  dto.setOrgCountryName((String) compSub.get(7));
			 										  dto.setOrgStateName((String) compSub.get(8));
			 										  dto.setOrgDistrictName((String) compSub.get(9));
			 										  dto.setDocumentName((String) compSub.get(21));
			 										  dto.setThunmbName((String) compSub.get(22));
			 										  dto.setSignatureName((String) compSub.get(23));
			 										  dto.setPhotoPath((String) compSub.get(24));
			 										  dto.setThumbPath((String) compSub.get(25));
			 										  dto.setSignPath((String) compSub.get(26));
			 										
			 										
			 										 }
			 									  }
			 									  }
			 								}ArrayList photoDetls = new ArrayList();
			 		 						ArrayList comp6 = new ArrayList();
			 		 						photoDetls=lockBD.getPhotoDetl(dto.getPropertyLockid(),language);
			 		 						if (photoDetls.size()>0){
			 		 							for(int i =0; i<photoDetls.size(); i++){
			 		 								comp6.add((ArrayList)photoDetls.get(i));
			 		 								  if(comp6 != null && comp6.size()>0){ 
			 		 								  for (int k=0; k< comp6.size(); k++){
			 		 									  ArrayList compSub = (ArrayList)comp6.get(k);
			 		 									  dto.setIdProofName((String) compSub.get(1));
			 		 									  dto.setIdProofNo((String) compSub.get(0));
			 		 								  }
			 		 								  }
			 		 								  }
			 		 							}
									formName="";
									actionName="";
									dto.setIsLockSuccess(1);
									dto.setIsLockSave(0);
									dto.setIsPartial(0);
									dto.setIsPayCompl(1);
								   forwardPage="lockPayView";
								}else{
									dto.setNotUpdatedAfterPay(1);
									forwardPage="lockPayView";
									}
								}
							}// end of Flag =P
				}// end of pay details not empty
   				
   				formName="";
   				actionName="";
   				forwardPage="lockPayView";
   				}
   			}
   		}// end of dash board lock
   		
   		//start of Release Dash board
   		if(request.getParameter("actionLinkReleaseDashbrd")!=null){
   			startPage = (String)request.getParameter("actionLinkReleaseDashbrd");
   		
   		if(!(startPage).equalsIgnoreCase("")){
   			if(startPage.equalsIgnoreCase("hyperClickReleaseDashbrd")){
   				String combDetl = request.getParameter("pendingDtCumStatusDashbrd");
   				String stCumDtList[]= combDetl.split("~");
   				DecimalFormat myformatter=new DecimalFormat("############");
   				String relId="";
   				String propId="";
   				String regno="";
   				String regdt="";
   				String status="";
   				if(stCumDtList.length == 5)
				{
   					relId= stCumDtList[0];
   					propId= stCumDtList[1];
   					if(propId.length()==15){
   						propId="0"+propId;
   					}
   					regno= stCumDtList[2];
   					regdt= stCumDtList[3];
   					status= stCumDtList[4];
   					
				}
   				
   				// payment details------
   				ArrayList first = new ArrayList();
				first = lockBD.getPayDtls(relId);
				if (first.size()==0){
					logger.debug("inside if");
					dto.setIsPayment(1);
					dto.setAlrdyPaidFee("0");
					dto.setPayableFee(dto.getTotalFee());
					
					// transaction details---
		        	ArrayList txnDetls = new ArrayList();
					ArrayList comp5 = new ArrayList();
					txnDetls=lockBD.getTxnDetlsRP(regno,propId,relId,language);
					 if (txnDetls.size()>0){
							for(int i =0; i<txnDetls.size(); i++){
								comp5.add((ArrayList)txnDetls.get(i));
								  if(comp5 != null && comp5.size()>0){ 
								  for (int k=0; k< comp5.size(); k++){
									  ArrayList compSub = (ArrayList)comp5.get(k);
									      dto.setPropertyReleaseId((String) compSub.get(0));
										  dto.setRegistrationId((String) compSub.get(1));
										  dto.setRegistrationDate((String) compSub.get(2));
										  String prId = (String) compSub.get(3);
										  if (prId.length()==15){
											  prId = "0"+prId;
										  }
										  dto.setPropId(prId);
										  dto.setPoaRegNo((String) compSub.get(4));
										  dto.setPoaRegDate((String) compSub.get(5));
										  dto.setLockStatus((String) compSub.get(6));
										  dto.setReasonForRelease((String) compSub.get(7));
										  dto.setReleaseDate((String) compSub.get(8));
										  dto.setRemarksForRelease((String) compSub.get(9));
										  dto.setReleaserType((String) compSub.get(10));
									 }
								  }
								  }
							}
					 //--party details
					 if(dto.getReleaserType().equalsIgnoreCase("1")){
  						ArrayList partyDetls = new ArrayList();
   						ArrayList comp2 = new ArrayList();
   						partyDetls=lockBD.getPartyDetls(dto.getRegistrationId(),relId,language);
   						if (partyDetls.size()>0){
   								for(int i =0; i<partyDetls.size(); i++){
   									comp2.add((ArrayList)partyDetls.get(i));
   									  if(comp2 != null && comp2.size()>0){ 
   									  for (int k=0; k< comp2.size(); k++){
   										  ArrayList compSub = (ArrayList)comp2.get(k);
   										  String apptype=(String) compSub.get(0);
   										  dto.setAppType((String) compSub.get(0));
   										  dto.setAppTypeName((String) compSub.get(1));
   										 if(apptype.equalsIgnoreCase("2")){
   											  dto.setFirstName((String) compSub.get(2));
   											  dto.setMidName((String) compSub.get(3));
   											  dto.setLastName((String) compSub.get(4));
   											  dto.setGender((String) compSub.get(5));
   											  dto.setAge((String) compSub.get(6));
   											  dto.setAddress((String) compSub.get(10));
   										      dto.setPhone((String) compSub.get(12));
   										      dto.setMphone((String) compSub.get(13));
   											  dto.setPin((String) compSub.get(11));
   											  dto.setEmail((String) compSub.get(14));
   											  /*dto.setIdProofName((String) compSub.get(15));
   											  dto.setIdProofNo((String) compSub.get(16));*/
   											  dto.setBankName((String) compSub.get(15));
   											  dto.setBankAddress((String) compSub.get(16));
   											  dto.setGuardianName((String) compSub.get(19));
   											  dto.setMotherName((String) compSub.get(20));
   											 }else
   												 if(apptype.equalsIgnoreCase("1")){
   													 dto.setOrgName((String) compSub.get(17));
   													 dto.setAuthName((String) compSub.get(18));
   													 dto.setOrgAddress((String) compSub.get(10));
   													 dto.setOrgPhno((String) compSub.get(12));
   													 dto.setOrgMobno((String) compSub.get(13));
   												 }
   										  dto.setOrgCountryName((String) compSub.get(7));
   										  dto.setOrgStateName((String) compSub.get(8));
   										  dto.setOrgDistrictName((String) compSub.get(9));
   										  dto.setDocumentName((String) compSub.get(21));
   										  dto.setThunmbName((String) compSub.get(22));
   										  dto.setSignatureName((String) compSub.get(23));
   										  dto.setPhotoPath((String) compSub.get(24));
   										  dto.setThumbPath((String) compSub.get(25));
   										  dto.setSignPath((String) compSub.get(26));
   										
   										 }
   									  }
   									  }
   								}
  			         
   						        ArrayList photoDetls = new ArrayList();
   		 						ArrayList comp6 = new ArrayList();
   		 						photoDetls=lockBD.getPhotoDetl(relId,language);
   		 						if (photoDetls.size()>0){
   		 							for(int i =0; i<photoDetls.size(); i++){
   		 								comp6.add((ArrayList)photoDetls.get(i));
   		 								  if(comp6 != null && comp6.size()>0){ 
   		 								  for (int k=0; k< comp6.size(); k++){
   		 									  ArrayList compSub = (ArrayList)comp6.get(k);
   		 									  dto.setIdProofName((String) compSub.get(1));
   		 									  dto.setIdProofNo((String) compSub.get(0));
   		 								  }
   		 								  }
   		 								  }
   		 							}
   		 						if("en".equalsIgnoreCase(language))
   		 						{
   		 						dto.setPersonType("Applicant");
   		 						}
   		 						else
   		 						{
   		 						dto.setPersonType("आवेदक");
   		 						}
   		 						}// end of releaser type=1
  						
  						if(dto.getReleaserType().equalsIgnoreCase("2")){
  							ArrayList relativeDetls = new ArrayList();
	      					ArrayList comp2 = new ArrayList();
	      					relativeDetls=lockBD.getRelativeDetls(dto.getRegistrationId(),relId,language);
	      						if (relativeDetls.size()>0){
	      								for(int i =0; i<relativeDetls.size(); i++){
	      									comp2.add((ArrayList)relativeDetls.get(i));
	      									  if(comp2 != null && comp2.size()>0){ 
	      									  for (int k=0; k< comp2.size(); k++){
	      										  ArrayList compSub = (ArrayList)comp2.get(k);
	      										  
	      										 dto.setReleaserName((String) compSub.get(0));
	      										 dto.setRelation((String) compSub.get(1));
	      										 dto.setRelFatherName((String) compSub.get(2));
	      										 dto.setRelMotherName((String) compSub.get(3));
	      										 dto.setRcountryName((String) compSub.get(4));
	      										 dto.setRstateName((String) compSub.get(5));
	      										 dto.setRdistrictName((String) compSub.get(6));
	      										 dto.setRelAddress((String) compSub.get(7));
	      										 dto.setRelMobNo((String) compSub.get(8));
	      										 dto.setRelphone((String) compSub.get(9));
	      										 dto.setRelEmail((String) compSub.get(10));
	      										 dto.setRelDeathCerName((String) compSub.get(11));
	      										 dto.setRelPhotoName((String) compSub.get(12));
	      										 dto.setRelThumbName((String) compSub.get(13));
	      										 dto.setRelSignName((String) compSub.get(14));
	      										 dto.setRelDeathCrtPath((String) compSub.get(15));
	      										 dto.setRelPhotoPath((String) compSub.get(16));
	      										 dto.setRelThumbPath((String) compSub.get(17));
	      										 dto.setRelSignPath((String) compSub.get(18));
	      										 
	      										if("en".equalsIgnoreCase(language))
	   										 {
	   										 dto.setPersonType("Relative");
	   										 }
	   										 else
	   										 {
	   											 dto.setPersonType("संबंधी");
	   										 }
	      										
	      										 }
	      									  }
	      									  }
	      								}
	      						}// end of releaser type=2
					 
					dto.setIsReleaseSave(1);
					dto.setNotInsertedP(0);
					dto.setNotUpdatedAfterPay(0);
					dto.setIsPartial(0);
					dto.setIsPayCompl(0);
					forwardPage = "releasePayView";
					}
				// if pay details are not empty
				else{
						logger.debug("inside else---means record is found");
						ArrayList comp = new ArrayList();
						String uniqId="";
					    String amtToBePaid="";
					    String paidAmt="";
					    String pymtFlg="";
						
						for(int i =0; i<first.size(); i++){
							comp.add((ArrayList)first.get(i));
							  if(comp != null && comp.size()>0){ 
							  for (int k=0; k< comp.size(); k++){
								  ArrayList compSub = (ArrayList)comp.get(k);
								  uniqId =      (String) compSub.get(0);
								  amtToBePaid = (String) compSub.get(1);
								  paidAmt =     (String) compSub.get(2);
								  pymtFlg =     (String) compSub.get(3);
								    logger.debug("unique id is ............................"+uniqId);
									logger.debug("unpayable amt is ............................"+amtToBePaid);
									logger.debug("paid amt is..................................."+paidAmt);
									logger.debug("pymtFlg is....................................."+pymtFlg);
								 }
							  }
							  }
						double damtToBePaid = Double.parseDouble(amtToBePaid);
						
						//------- if payment flag is I ----
						if(pymtFlg.equalsIgnoreCase("I")){
							String totfee=dto.getTotalFee();
							double dtotfee=Double.parseDouble(totfee);
							double alrdP = dtotfee-damtToBePaid;
							String balfrmt = myformatter.format(damtToBePaid);
							String alrdFrmt = myformatter.format(alrdP);
							dto.setPayableFee(balfrmt);
							dto.setAlrdyPaidFee(alrdFrmt);
							
							// transaction details---
							ArrayList txnDetls = new ArrayList();
							ArrayList comp5 = new ArrayList();
							txnDetls=lockBD.getTxnDetlsRP(regno,propId,relId,language);
							 if (txnDetls.size()>0){
									for(int i =0; i<txnDetls.size(); i++){
										comp5.add((ArrayList)txnDetls.get(i));
										  if(comp5 != null && comp5.size()>0){ 
										  for (int k=0; k< comp5.size(); k++){
											  ArrayList compSub = (ArrayList)comp5.get(k);
											      dto.setPropertyReleaseId((String) compSub.get(0));
												  dto.setRegistrationId((String) compSub.get(1));
												  dto.setRegistrationDate((String) compSub.get(2));
												  String prId = (String) compSub.get(3);
												  if (prId.length()==15){
													  prId = "0"+prId;
												  }
												  dto.setPropId(prId);
												  dto.setPoaRegNo((String) compSub.get(4));
												  dto.setPoaRegDate((String) compSub.get(5));
												  dto.setLockStatus((String) compSub.get(6));
												  dto.setReasonForRelease((String) compSub.get(7));
												  dto.setReleaseDate((String) compSub.get(8));
												  dto.setRemarksForRelease((String) compSub.get(9));
												  dto.setReleaserType((String) compSub.get(10));
											 }
										  }
										  }
									}
							 //--party details
							 if(dto.getReleaserType().equalsIgnoreCase("1")){
		  						ArrayList partyDetls = new ArrayList();
		   						ArrayList comp2 = new ArrayList();
		   						partyDetls=lockBD.getPartyDetls(dto.getRegistrationId(),relId,language);
		   						if (partyDetls.size()>0){
		   								for(int i =0; i<partyDetls.size(); i++){
		   									comp2.add((ArrayList)partyDetls.get(i));
		   									  if(comp2 != null && comp2.size()>0){ 
		   									  for (int k=0; k< comp2.size(); k++){
		   										  ArrayList compSub = (ArrayList)comp2.get(k);
		   										  String apptype=(String) compSub.get(0);
		   										  dto.setAppType((String) compSub.get(0));
		   										  dto.setAppTypeName((String) compSub.get(1));
		   										 if(apptype.equalsIgnoreCase("2")){
		   											  dto.setFirstName((String) compSub.get(2));
		   											  dto.setMidName((String) compSub.get(3));
		   											  dto.setLastName((String) compSub.get(4));
		   											  dto.setGender((String) compSub.get(5));
		   											  dto.setAge((String) compSub.get(6));
		   											  dto.setAddress((String) compSub.get(10));
		   										      dto.setPhone((String) compSub.get(12));
		   										      dto.setMphone((String) compSub.get(13));
		   											  dto.setPin((String) compSub.get(11));
		   											  dto.setEmail((String) compSub.get(14));
		   											  /*dto.setIdProofName((String) compSub.get(15));
		   											  dto.setIdProofNo((String) compSub.get(16));*/
		   											  dto.setBankName((String) compSub.get(15));
		   											  dto.setBankAddress((String) compSub.get(16));
		   											  dto.setGuardianName((String) compSub.get(19));
		   											  dto.setMotherName((String) compSub.get(20));
		   											 }else
		   												 if(apptype.equalsIgnoreCase("1")){
		   													 dto.setOrgName((String) compSub.get(17));
		   													 dto.setAuthName((String) compSub.get(18));
		   													 dto.setOrgAddress((String) compSub.get(10));
		   													 dto.setOrgPhno((String) compSub.get(12));
		   													 dto.setOrgMobno((String) compSub.get(13));
		   												 }
		   										  dto.setOrgCountryName((String) compSub.get(7));
		   										  dto.setOrgStateName((String) compSub.get(8));
		   										  dto.setOrgDistrictName((String) compSub.get(9));
		   										  dto.setDocumentName((String) compSub.get(21));
		   										  dto.setThunmbName((String) compSub.get(22));
		   										  dto.setSignatureName((String) compSub.get(23));
		   										  dto.setPhotoPath((String) compSub.get(24));
		   										  dto.setThumbPath((String) compSub.get(25));
		   										  dto.setSignPath((String) compSub.get(26));
		   										
		   										 }
		   									  }
		   									  }
		   								}
		  			         
		   						        ArrayList photoDetls = new ArrayList();
		   		 						ArrayList comp6 = new ArrayList();
		   		 						photoDetls=lockBD.getPhotoDetl(relId,language);
		   		 						if (photoDetls.size()>0){
		   		 							for(int i =0; i<photoDetls.size(); i++){
		   		 								comp6.add((ArrayList)photoDetls.get(i));
		   		 								  if(comp6 != null && comp6.size()>0){ 
		   		 								  for (int k=0; k< comp6.size(); k++){
		   		 									  ArrayList compSub = (ArrayList)comp6.get(k);
		   		 									  dto.setIdProofName((String) compSub.get(1));
		   		 									  dto.setIdProofNo((String) compSub.get(0));
		   		 								  }
		   		 								  }
		   		 								  }
		   		 							}
		   		 						if("en".equalsIgnoreCase(language))
		   		 						{
		   		 						dto.setPersonType("Applicant");}
		   		 						else
		   		 						{
		   		 						dto.setPersonType("आवेदक");	
		   		 						}
		   		 						}// end of releaser type=1
		  						
		  						if(dto.getReleaserType().equalsIgnoreCase("2")){
		  							ArrayList relativeDetls = new ArrayList();
			      					ArrayList comp2 = new ArrayList();
			      					relativeDetls=lockBD.getRelativeDetls(dto.getRegistrationId(),relId,language);
			      						if (relativeDetls.size()>0){
			      								for(int i =0; i<relativeDetls.size(); i++){
			      									comp2.add((ArrayList)relativeDetls.get(i));
			      									  if(comp2 != null && comp2.size()>0){ 
			      									  for (int k=0; k< comp2.size(); k++){
			      										  ArrayList compSub = (ArrayList)comp2.get(k);
			      										  
			      										 dto.setReleaserName((String) compSub.get(0));
			      										 dto.setRelation((String) compSub.get(1));
			      										 dto.setRelFatherName((String) compSub.get(2));
			      										 dto.setRelMotherName((String) compSub.get(3));
			      										 dto.setRcountryName((String) compSub.get(4));
			      										 dto.setRstateName((String) compSub.get(5));
			      										 dto.setRdistrictName((String) compSub.get(6));
			      										 dto.setRelAddress((String) compSub.get(7));
			      										 dto.setRelMobNo((String) compSub.get(8));
			      										 dto.setRelphone((String) compSub.get(9));
			      										 dto.setRelEmail((String) compSub.get(10));
			      										 dto.setRelDeathCerName((String) compSub.get(11));
			      										 dto.setRelPhotoName((String) compSub.get(12));
			      										 dto.setRelThumbName((String) compSub.get(13));
			      										 dto.setRelSignName((String) compSub.get(14));
			      										 dto.setRelDeathCrtPath((String) compSub.get(15));
			      										 dto.setRelPhotoPath((String) compSub.get(16));
			      										 dto.setRelThumbPath((String) compSub.get(17));
			      										 dto.setRelSignPath((String) compSub.get(18));
			      										if("en".equalsIgnoreCase(language))
			   										 {
			   										 dto.setPersonType("Relative");
			   										 }
			   										 else
			   										 {
			   											 dto.setPersonType("संबंधी");
			   										 }
			      										
			      										 }
			      									  }
			      									  }
			      								}
			      						}// end of releaser type=2
							 
							
							
							
							dto.setIsReleaseSave(1);
							dto.setNotInsertedP(0);
							dto.setNotUpdatedAfterPay(0);
							dto.setIsPartial(1);
							dto.setIsPayCompl(0);
							forwardPage = "releasePayView";
							
						}
						//----------- if payment flag is P -----
						
						if(pymtFlg.equalsIgnoreCase("P")){
							
							double dpaidAmt = Double.parseDouble(paidAmt);
							//condition 1-------------------pending amount-------------
							if (damtToBePaid>dpaidAmt){
								
								String totfee=dto.getTotalFee();
								double dtotfee=Double.parseDouble(totfee);
								double bal= damtToBePaid-dpaidAmt;
								double alrdP = dtotfee-bal;
								String balfrmt = myformatter.format(bal);
								String alrdFrmt = myformatter.format(alrdP);
								dto.setPayableFee(balfrmt);
								dto.setAlrdyPaidFee(alrdFrmt);
								
								// transaction details---
								ArrayList txnDetls = new ArrayList();
								ArrayList comp5 = new ArrayList();
								txnDetls=lockBD.getTxnDetlsRP(regno,propId,relId,language);
								 if (txnDetls.size()>0){
										for(int i =0; i<txnDetls.size(); i++){
											comp5.add((ArrayList)txnDetls.get(i));
											  if(comp5 != null && comp5.size()>0){ 
											  for (int k=0; k< comp5.size(); k++){
												  ArrayList compSub = (ArrayList)comp5.get(k);
												      dto.setPropertyReleaseId((String) compSub.get(0));
													  dto.setRegistrationId((String) compSub.get(1));
													  dto.setRegistrationDate((String) compSub.get(2));
													  String prId = (String) compSub.get(3);
													  if (prId.length()==15){
														  prId = "0"+prId;
													  }
													  dto.setPropId(prId);
													  dto.setPoaRegNo((String) compSub.get(4));
													  dto.setPoaRegDate((String) compSub.get(5));
													  dto.setLockStatus((String) compSub.get(6));
													  dto.setReasonForRelease((String) compSub.get(7));
													  dto.setReleaseDate((String) compSub.get(8));
													  dto.setRemarksForRelease((String) compSub.get(9));
													  dto.setReleaserType((String) compSub.get(10));
												 }
											  }
											  }
										}
								 //--party details
								 if(dto.getReleaserType().equalsIgnoreCase("1")){
			  						ArrayList partyDetls = new ArrayList();
			   						ArrayList comp2 = new ArrayList();
			   						partyDetls=lockBD.getPartyDetls(dto.getRegistrationId(),relId,language);
			   						if (partyDetls.size()>0){
			   								for(int i =0; i<partyDetls.size(); i++){
			   									comp2.add((ArrayList)partyDetls.get(i));
			   									  if(comp2 != null && comp2.size()>0){ 
			   									  for (int k=0; k< comp2.size(); k++){
			   										  ArrayList compSub = (ArrayList)comp2.get(k);
			   										  String apptype=(String) compSub.get(0);
			   										  dto.setAppType((String) compSub.get(0));
			   										  dto.setAppTypeName((String) compSub.get(1));
			   										 if(apptype.equalsIgnoreCase("2")){
			   											  dto.setFirstName((String) compSub.get(2));
			   											  dto.setMidName((String) compSub.get(3));
			   											  dto.setLastName((String) compSub.get(4));
			   											  dto.setGender((String) compSub.get(5));
			   											  dto.setAge((String) compSub.get(6));
			   											  dto.setAddress((String) compSub.get(10));
			   										      dto.setPhone((String) compSub.get(12));
			   										      dto.setMphone((String) compSub.get(13));
			   											  dto.setPin((String) compSub.get(11));
			   											  dto.setEmail((String) compSub.get(14));
			   											  /*dto.setIdProofName((String) compSub.get(15));
			   											  dto.setIdProofNo((String) compSub.get(16));*/
			   											  dto.setBankName((String) compSub.get(15));
			   											  dto.setBankAddress((String) compSub.get(16));
			   											  dto.setGuardianName((String) compSub.get(19));
			   											  dto.setMotherName((String) compSub.get(20));
			   											 }else
			   												 if(apptype.equalsIgnoreCase("1")){
			   													 dto.setOrgName((String) compSub.get(17));
			   													 dto.setAuthName((String) compSub.get(18));
			   													 dto.setOrgAddress((String) compSub.get(10));
			   													 dto.setOrgPhno((String) compSub.get(12));
			   													 dto.setOrgMobno((String) compSub.get(13));
			   												 }
			   										  dto.setOrgCountryName((String) compSub.get(7));
			   										  dto.setOrgStateName((String) compSub.get(8));
			   										  dto.setOrgDistrictName((String) compSub.get(9));
			   										  dto.setDocumentName((String) compSub.get(21));
			   										  dto.setThunmbName((String) compSub.get(22));
			   										  dto.setSignatureName((String) compSub.get(23));
			   										  dto.setPhotoPath((String) compSub.get(24));
			   										  dto.setThumbPath((String) compSub.get(25));
			   										  dto.setSignPath((String) compSub.get(26));
			   										
			   										 }
			   									  }
			   									  }
			   								}
			  			         
			   						        ArrayList photoDetls = new ArrayList();
			   		 						ArrayList comp6 = new ArrayList();
			   		 						photoDetls=lockBD.getPhotoDetl(relId,language);
			   		 						if (photoDetls.size()>0){
			   		 							for(int i =0; i<photoDetls.size(); i++){
			   		 								comp6.add((ArrayList)photoDetls.get(i));
			   		 								  if(comp6 != null && comp6.size()>0){ 
			   		 								  for (int k=0; k< comp6.size(); k++){
			   		 									  ArrayList compSub = (ArrayList)comp6.get(k);
			   		 									  dto.setIdProofName((String) compSub.get(1));
			   		 									  dto.setIdProofNo((String) compSub.get(0));
			   		 								  }
			   		 								  }
			   		 								  }
			   		 							}
			   		 						
			   		 						if("en".equalsIgnoreCase(language))
			   		 						{
			   		 						dto.setPersonType("Applicant");}
			   		 						else
			   		 						{
			   		 						dto.setPersonType("आवेदक");
			   		 						}
			   		 						}// end of releaser type=1
			  						
			  						if(dto.getReleaserType().equalsIgnoreCase("2")){
			  							ArrayList relativeDetls = new ArrayList();
				      					ArrayList comp2 = new ArrayList();
				      					relativeDetls=lockBD.getRelativeDetls(dto.getRegistrationId(),relId,language);
				      						if (relativeDetls.size()>0){
				      								for(int i =0; i<relativeDetls.size(); i++){
				      									comp2.add((ArrayList)relativeDetls.get(i));
				      									  if(comp2 != null && comp2.size()>0){ 
				      									  for (int k=0; k< comp2.size(); k++){
				      										  ArrayList compSub = (ArrayList)comp2.get(k);
				      										  
				      										 dto.setReleaserName((String) compSub.get(0));
				      										 dto.setRelation((String) compSub.get(1));
				      										 dto.setRelFatherName((String) compSub.get(2));
				      										 dto.setRelMotherName((String) compSub.get(3));
				      										 dto.setRcountryName((String) compSub.get(4));
				      										 dto.setRstateName((String) compSub.get(5));
				      										 dto.setRdistrictName((String) compSub.get(6));
				      										 dto.setRelAddress((String) compSub.get(7));
				      										 dto.setRelMobNo((String) compSub.get(8));
				      										 dto.setRelphone((String) compSub.get(9));
				      										 dto.setRelEmail((String) compSub.get(10));
				      										 dto.setRelDeathCerName((String) compSub.get(11));
				      										 dto.setRelPhotoName((String) compSub.get(12));
				      										 dto.setRelThumbName((String) compSub.get(13));
				      										 dto.setRelSignName((String) compSub.get(14));
				      										 dto.setRelDeathCrtPath((String) compSub.get(15));
				      										 dto.setRelPhotoPath((String) compSub.get(16));
				      										 dto.setRelThumbPath((String) compSub.get(17));
				      										 dto.setRelSignPath((String) compSub.get(18));
				      										if("en".equalsIgnoreCase(language))
				   										 {
				   										 dto.setPersonType("Relative");
				   										 }
				   										 else
				   										 {
				   											 dto.setPersonType("संबंधी");
				   										 }
				      										
				      										 }
				      									  }
				      									  }
				      								}
				      						}// end of releaser type=2
								
		 						dto.setIsReleaseSave(1);
								dto.setNotInsertedP(0);
								dto.setNotUpdatedAfterPay(0);
								dto.setIsPartial(1);
								dto.setIsPayCompl(0);
								forwardPage="releasePayView";
								}
							if (damtToBePaid==dpaidAmt){
								userId = (String)session.getAttribute("UserId");
								
								dto.setNotUpdatedAfterPay(0);
								logger.debug("Inside change status method after payment----the release id is-->>"+relId);
								dto.setPropertyReleaseId(relId);
								boolean ins=false;
								ins=lockBD.statusUpdateAfterPR(relId, userId, officeId);
								if(ins){
						        	
									dto.setPropertyReleaseId(relId);
						        	// transaction details---
						        	ArrayList txnDetls = new ArrayList();
									ArrayList comp5 = new ArrayList();
									txnDetls=lockBD.getTxnDetlsR(dto.getRegistrationId(), dto.getPropId(),relId,language);
									 if (txnDetls.size()>0){
											for(int i =0; i<txnDetls.size(); i++){
												comp5.add((ArrayList)txnDetls.get(i));
												  if(comp5 != null && comp5.size()>0){ 
												  for (int k=0; k< comp5.size(); k++){
													  ArrayList compSub = (ArrayList)comp5.get(k);
													  dto.setPropertyReleaseId((String) compSub.get(0));
													  dto.setRegistrationId((String) compSub.get(1));
													  dto.setRegistrationDate((String) compSub.get(2));
													  String prId = (String) compSub.get(3);
													  if (prId.length()==15){
														  prId = "0"+prId;
													  }
													  dto.setPropId(prId);
													  dto.setPoaRegNo((String) compSub.get(4));
													  dto.setPoaRegDate((String) compSub.get(5));
													  dto.setLockStatus((String) compSub.get(6));
													  dto.setReasonForRelease((String) compSub.get(7));
													  dto.setReleaseDate((String) compSub.get(8));
													  dto.setRemarksForRelease((String) compSub.get(9));
													  dto.setReleaserType((String) compSub.get(10));
													 }
												  }
												  }
											}
									 //--party details
									 if(dto.getReleaserType().equalsIgnoreCase("1")){
				     						ArrayList partyDetls = new ArrayList();
				      						ArrayList comp2 = new ArrayList();
				      						partyDetls=lockBD.getPartyDetls(dto.getRegistrationId(),relId,language);
				      						if (partyDetls.size()>0){
				      								for(int i =0; i<partyDetls.size(); i++){
				      									comp2.add((ArrayList)partyDetls.get(i));
				      									  if(comp2 != null && comp2.size()>0){ 
				      									  for (int k=0; k< comp2.size(); k++){
				      										  ArrayList compSub = (ArrayList)comp2.get(k);
				      										  String apptype=(String) compSub.get(0);
				      										  dto.setAppType((String) compSub.get(0));
				      										  dto.setAppTypeName((String) compSub.get(1));
				      										 if(apptype.equalsIgnoreCase("2")){
				      											  dto.setFirstName((String) compSub.get(2));
				      											  dto.setMidName((String) compSub.get(3));
				      											  dto.setLastName((String) compSub.get(4));
				      											  dto.setGender((String) compSub.get(5));
				      											  dto.setAge((String) compSub.get(6));
				      											  dto.setAddress((String) compSub.get(10));
				      										      dto.setPhone((String) compSub.get(12));
				      										      dto.setMphone((String) compSub.get(13));
				      											  dto.setPin((String) compSub.get(11));
				      											  dto.setEmail((String) compSub.get(14));
				      											  /*dto.setIdProofName((String) compSub.get(15));
				      											  dto.setIdProofNo((String) compSub.get(16));*/
				      											  dto.setBankName((String) compSub.get(15));
				      											  dto.setBankAddress((String) compSub.get(16));
				      											  dto.setGuardianName((String) compSub.get(19));
				      											  dto.setMotherName((String) compSub.get(20));
				      											 }else
				      												 if(apptype.equalsIgnoreCase("1")){
				      													 dto.setOrgName((String) compSub.get(17));
				      													 dto.setAuthName((String) compSub.get(18));
				      													 dto.setOrgAddress((String) compSub.get(10));
				      													 dto.setOrgPhno((String) compSub.get(12));
				      													 dto.setOrgMobno((String) compSub.get(13));
				      												 }
				      										  dto.setOrgCountryName((String) compSub.get(7));
				      										  dto.setOrgStateName((String) compSub.get(8));
				      										  dto.setOrgDistrictName((String) compSub.get(9));
				      										  dto.setDocumentName((String) compSub.get(21));
				      										  dto.setThunmbName((String) compSub.get(22));
				      										  dto.setSignatureName((String) compSub.get(23));
				      										  dto.setPhotoPath((String) compSub.get(24));
				      										  dto.setThumbPath((String) compSub.get(25));
				      										  dto.setSignPath((String) compSub.get(26));
				      										
				      										 }
				      									  }
				      									  }
				      								}
				     			         
				      						        ArrayList photoDetls = new ArrayList();
				      		 						ArrayList comp6 = new ArrayList();
				      		 						photoDetls=lockBD.getPhotoDetl(relId,language);
				      		 						if (photoDetls.size()>0){
				      		 							for(int i =0; i<photoDetls.size(); i++){
				      		 								comp6.add((ArrayList)photoDetls.get(i));
				      		 								  if(comp6 != null && comp6.size()>0){ 
				      		 								  for (int k=0; k< comp6.size(); k++){
				      		 									  ArrayList compSub = (ArrayList)comp6.get(k);
				      		 									  dto.setIdProofName((String) compSub.get(1));
				      		 									  dto.setIdProofNo((String) compSub.get(0));
				      		 								  }
				      		 								  }
				      		 								  }
				      		 							}
				      		 						if("en".equalsIgnoreCase(language))
				      		 						{
				      		 						dto.setPersonType("Applicant");
				      		 						}
				      		 						else
				      		 						{
				      		 							dto.setPersonType("आवेदक");
				      		 						}
				      		 						}// end of releaser type=1
				     						
				     						if(dto.getReleaserType().equalsIgnoreCase("2")){
				     							ArrayList relativeDetls = new ArrayList();
					      						ArrayList comp2 = new ArrayList();
					      						relativeDetls=lockBD.getRelativeDetls(dto.getRegistrationId(),relId,language);
					      						if (relativeDetls.size()>0){
					      								for(int i =0; i<relativeDetls.size(); i++){
					      									comp2.add((ArrayList)relativeDetls.get(i));
					      									  if(comp2 != null && comp2.size()>0){ 
					      									  for (int k=0; k< comp2.size(); k++){
					      										  ArrayList compSub = (ArrayList)comp2.get(k);
					      										  
					      										 dto.setReleaserName((String) compSub.get(0));
					      										 dto.setRelation((String) compSub.get(1));
					      										 dto.setRelFatherName((String) compSub.get(2));
					      										 dto.setRelMotherName((String) compSub.get(3));
					      										 dto.setRcountryName((String) compSub.get(4));
					      										 dto.setRstateName((String) compSub.get(5));
					      										 dto.setRdistrictName((String) compSub.get(6));
					      										 dto.setRelAddress((String) compSub.get(7));
					      										 dto.setRelMobNo((String) compSub.get(8));
					      										 dto.setRelphone((String) compSub.get(9));
					      										 dto.setRelEmail((String) compSub.get(10));
					      										 dto.setRelDeathCerName((String) compSub.get(11));
					      										 dto.setRelPhotoName((String) compSub.get(12));
					      										 dto.setRelThumbName((String) compSub.get(13));
					      										 dto.setRelSignName((String) compSub.get(14));
					      										 dto.setRelDeathCrtPath((String) compSub.get(15));
					      										 dto.setRelPhotoPath((String) compSub.get(16));
					      										 dto.setRelThumbPath((String) compSub.get(17));
					      										 dto.setRelSignPath((String) compSub.get(18));
					      										if("en".equalsIgnoreCase(language))
					   										 {
					   										 dto.setPersonType("Relative");
					   										 }
					   										 else
					   										 {
					   											 dto.setPersonType("संबंधी");
					   										 }
					      										
					      										 }
					      									  }
					      									  }
					      								}
					      						}// end of releaser type=2
									
									
									
									
									
									formName="";
									actionName="";
									dto.setIsRelSuccess(1);
									dto.setIsReleaseSave(0);
									dto.setIsPartial(0);
									dto.setIsPayCompl(1);
								   forwardPage="releasePayView";
								}else{
									dto.setNotUpdatedAfterPay(1);
									forwardPage="releasePayView";
									}
								}
							}// end of Flag =P
				}// end of pay details not empty
   				
   				formName="";
   				actionName="";
   				forwardPage="releasePayView";
   				}
   			}
   		}// end of Release Dash board
		
		// start of release from search page
   		if(request.getParameter("actionLinkRelease")!=null){
			nextPage = (String)request.getParameter("actionLinkRelease");
		
		if(!(nextPage).equalsIgnoreCase("")){
			if(nextPage.equalsIgnoreCase("hyperClickRelease")){
				
				String comDet=request.getParameter("pendingDtCumStatus");
				String stCumDtList[]= comDet.split("~");
				String propiId="";
				String regno="";
				String regdt="";
				String lockId="";
				if(stCumDtList.length == 4)
				{
					propiId= stCumDtList[0];
					logger.debug("the property id in the param is------>"+propiId);
					regno= stCumDtList[1];
					logger.debug("the completion number in the param is------>"+regno);
					regdt = stCumDtList[2];
					logger.debug("the completion date in the param is------>"+regdt);
					lockId = stCumDtList[3];
					logger.debug("the lock Id  in the param is------>"+lockId);
				}
				
				
				
				dto.setPropertyLockid(lockId);
	        	// transaction details---
	        	ArrayList txnDetls = new ArrayList();
				ArrayList comp = new ArrayList();
				txnDetls=lockBD.getTxnDetls(regno, propiId,lockId,language);
				 if (txnDetls.size()>0){
						for(int i =0; i<txnDetls.size(); i++){
							comp.add((ArrayList)txnDetls.get(i));
							  if(comp != null && comp.size()>0){ 
							  for (int k=0; k< comp.size(); k++){
								  ArrayList compSub = (ArrayList)comp.get(k);
								  dto.setPropertyLockid((String) compSub.get(0));
								  dto.setRegistrationId((String) compSub.get(1));
								  dto.setRegistrationDate((String) compSub.get(2));
								  String prId = (String) compSub.get(3);
								  if (prId.length()==15){
									  prId = "0"+prId;
								  }
								  dto.setPropId(prId);
								  dto.setPoaRegNo((String) compSub.get(4));
								  dto.setPoaRegDate((String) compSub.get(5));
								  dto.setLockStatus((String) compSub.get(6));
								  dto.setPurpose((String) compSub.get(7));
								  dto.setDateLocking((String) compSub.get(8));
								 }
							  }
							  }
						}
				 //--party details
				    ArrayList partyDetls = new ArrayList();
					ArrayList comp2 = new ArrayList();
					partyDetls=lockBD.getPartyDetls(regno,lockId,language);
					if (partyDetls.size()>0){
							for(int i =0; i<partyDetls.size(); i++){
								comp2.add((ArrayList)partyDetls.get(i));
								  if(comp2 != null && comp2.size()>0){ 
								  for (int k=0; k< comp2.size(); k++){
									  ArrayList compSub = (ArrayList)comp2.get(k);
									  String apptype=(String) compSub.get(0);
									  dto.setAppType((String) compSub.get(0));
									  dto.setAppTypeName((String) compSub.get(1));
									 if(apptype.equalsIgnoreCase("2")){
										  dto.setFirstName((String) compSub.get(2));
										  dto.setMidName((String) compSub.get(3));
										  dto.setLastName((String) compSub.get(4));
										  dto.setGender((String) compSub.get(5));
										  dto.setAge((String) compSub.get(6));
										  dto.setAddress((String) compSub.get(10));
									      dto.setPhone((String) compSub.get(12));
									      dto.setMphone((String) compSub.get(13));
										  dto.setPin((String) compSub.get(11));
										  dto.setEmail((String) compSub.get(14));
										  /*dto.setIdProofName((String) compSub.get(15));
										  dto.setIdProofNo((String) compSub.get(16));*/
										  dto.setBankName((String) compSub.get(15));
										  dto.setBankAddress((String) compSub.get(16));
										  dto.setGuardianName((String) compSub.get(19));
										  dto.setMotherName((String) compSub.get(20));
										 }else
											 if(apptype.equalsIgnoreCase("1")){
												 dto.setOrgName((String) compSub.get(17));
												 dto.setAuthName((String) compSub.get(18));
												 dto.setOrgAddress((String) compSub.get(10));
												 dto.setOrgPhno((String) compSub.get(12));
												 dto.setOrgMobno((String) compSub.get(13));
											 }
									  dto.setOrgCountryName((String) compSub.get(7));
									  dto.setOrgStateName((String) compSub.get(8));
									  dto.setOrgDistrictName((String) compSub.get(9));
									  dto.setDocumentName((String) compSub.get(21));
									  dto.setThunmbName((String) compSub.get(22));
									  dto.setSignatureName((String) compSub.get(23));
									  dto.setPhotoPath((String) compSub.get(24));
									  dto.setThumbPath((String) compSub.get(25));
									  dto.setSignPath((String) compSub.get(26));
									  dto.setOrgCountry((String) compSub.get(27));
									  dto.setOrgState((String) compSub.get(28));
									  dto.setOrgDistrict((String) compSub.get(29));
									 }
								  }
								  }
							}ArrayList photoDetls = new ArrayList();
	 						ArrayList comp6 = new ArrayList();
	 						photoDetls=lockBD.getPhotoDetl(lockId,language);
	 						if (photoDetls.size()>0){
	 							for(int i =0; i<photoDetls.size(); i++){
	 								comp6.add((ArrayList)photoDetls.get(i));
	 								  if(comp6 != null && comp6.size()>0){ 
	 								  for (int k=0; k< comp6.size(); k++){
	 									  ArrayList compSub = (ArrayList)comp6.get(k);
	 									  dto.setIdProofName((String) compSub.get(1));
	 									  dto.setIdProofNo((String) compSub.get(0));
	 									  dto.setIdProof((String) compSub.get(2));
	 								  }
	 								  }
	 								  }
	 							}
	 						 ArrayList relationList=new ArrayList();		
	 			    		 relationList = lockBD.getRelationType();
	 			    		 dto.setRelationList(relationList); 
	 			    		if((Double.parseDouble(fee)!= 0)|| (Double.parseDouble(fee)!= 0.0)){
	 							dto.setIsPayment(1);
	 							dto.setAlrdyPaidFee("0");
	 							dto.setPayableFee(dto.getTotalFee());
	 						}	
				formName="";
				actionName="";
				//commented by shruti---- as discussed with Anuj
				//added by shruti--12 th nov 2013
				
				//uncommented for release---- 7 june 2014
				dto.setGuidUpload(GUIDGenerator.getGUID());
				dto.setParentPathFP(pr.getValue("igrs_upload_path")+"/11/Release/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"ThumbPrint");
				dto.setFileNameFP("FingerPrint.GIF");
				dto.setParentPathScan(pr.getValue("igrs_upload_path")+"/11/Release/Scan");
				dto.setFileNameScan("CapturedDocument.PDF");
				dto.setParentPathSign(pr.getValue("igrs_upload_path")+"/11/Release/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"Sign");
				dto.setFileNameSign("CapturedScan.GIF");
				dto.setForwardPath("/lockAction.do?uploadSign=true");
				dto.setForwardName("lockFirst");
				dto.setPhotoName("Photo.JPG");
				dto.setWebcameraPath(pr.getValue("igrs_upload_path")+"/11/Release/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"Photo");
				forwardPage="releaseFirst";
				}
			}	
		}//end of release from search page
		
		// start of actionLinkView=hyperClickView
   		if(request.getParameter("actionLinkView")!=null){
			nextPage = (String)request.getParameter("actionLinkView");
		
		if(!(nextPage).equalsIgnoreCase("")){
			if(nextPage.equalsIgnoreCase("hyperClickView")){
				
				String comDet=request.getParameter("combSt");
				String stCumDtList[]= comDet.split("~");
				String propiId="";
				String regno="";
				String status="";
				String refId="";
				if(stCumDtList.length == 4)
				{
					refId = stCumDtList[0];
					regno = stCumDtList[1];
					propiId = stCumDtList[2];
					status = stCumDtList[3];
				}
				// released
				if(status.equalsIgnoreCase("1"))
				{
					// transaction details---
			        	ArrayList txnDetls = new ArrayList();
						ArrayList comp = new ArrayList();
						txnDetls=lockBD.getTxnDetlsRV(regno, propiId,refId);
						 if (txnDetls.size()>0){
								for(int i =0; i<txnDetls.size(); i++){
									comp.add((ArrayList)txnDetls.get(i));
									  if(comp != null && comp.size()>0){ 
									  for (int k=0; k< comp.size(); k++){
										  ArrayList compSub = (ArrayList)comp.get(k);
										  dto.setPropertyReleaseId((String) compSub.get(0));
										  dto.setRegistrationId((String) compSub.get(1));
										  dto.setRegistrationDate((String) compSub.get(2));
										  String prId = (String) compSub.get(3);
										  if (prId.length()==15){
											  prId = "0"+prId;
										  }
										  dto.setPropId(prId);
										  dto.setPoaRegNo((String) compSub.get(4));
										  dto.setPoaRegDate((String) compSub.get(5));
										  dto.setLockStatus((String) compSub.get(6));
										  dto.setReasonForRelease((String) compSub.get(7));
										  dto.setReleaseDate((String) compSub.get(8));
										  dto.setRemarksForRelease((String) compSub.get(9));
										  dto.setReleaserType((String) compSub.get(10));
										 }
									  }
									  }
								}
						 //--party details
						if(dto.getReleaserType().equalsIgnoreCase("1")){
						ArrayList partyDetls = new ArrayList();
						ArrayList comp2 = new ArrayList();
						partyDetls=lockBD.getPartyDetls(regno,refId,language);
						if (partyDetls.size()>0){
								for(int i =0; i<partyDetls.size(); i++){
									comp2.add((ArrayList)partyDetls.get(i));
									  if(comp2 != null && comp2.size()>0){ 
									  for (int k=0; k< comp2.size(); k++){
										  ArrayList compSub = (ArrayList)comp2.get(k);
										  String apptype=(String) compSub.get(0);
										  dto.setAppType((String) compSub.get(0));
										  dto.setAppTypeName((String) compSub.get(1));
										 if(apptype.equalsIgnoreCase("2")){
											  dto.setFirstName((String) compSub.get(2));
											  dto.setMidName((String) compSub.get(3));
											  dto.setLastName((String) compSub.get(4));
											  dto.setGender((String) compSub.get(5));
											  dto.setAge((String) compSub.get(6));
											  dto.setAddress((String) compSub.get(10));
										      dto.setPhone((String) compSub.get(12));
										      dto.setMphone((String) compSub.get(13));
											  dto.setPin((String) compSub.get(11));
											  dto.setEmail((String) compSub.get(14));
											  /*dto.setIdProofName((String) compSub.get(15));
											  dto.setIdProofNo((String) compSub.get(16));*/
											  dto.setBankName((String) compSub.get(15));
											  dto.setBankAddress((String) compSub.get(16));
											  dto.setGuardianName((String) compSub.get(19));
											  dto.setMotherName((String) compSub.get(20));
											 }else
												 if(apptype.equalsIgnoreCase("1")){
													 dto.setOrgName((String) compSub.get(17));
													 dto.setAuthName((String) compSub.get(18));
													 dto.setOrgAddress((String) compSub.get(10));
													 dto.setOrgPhno((String) compSub.get(12));
													 dto.setOrgMobno((String) compSub.get(13));
												 }
										  dto.setOrgCountryName((String) compSub.get(7));
										  dto.setOrgStateName((String) compSub.get(8));
										  dto.setOrgDistrictName((String) compSub.get(9));
										  dto.setDocumentName((String) compSub.get(21));
										  dto.setThunmbName((String) compSub.get(22));
										  dto.setSignatureName((String) compSub.get(23));
										  dto.setPhotoPath((String) compSub.get(24));
										  dto.setThumbPath((String) compSub.get(25));
										  dto.setSignPath((String) compSub.get(26));
										
										 }
									  }
									  }
								}
			         
						        ArrayList photoDetls = new ArrayList();
		 						ArrayList comp6 = new ArrayList();
		 						photoDetls=lockBD.getPhotoDetl(refId,language);
		 						if (photoDetls.size()>0){
		 							for(int i =0; i<photoDetls.size(); i++){
		 								comp6.add((ArrayList)photoDetls.get(i));
		 								  if(comp6 != null && comp6.size()>0){ 
		 								  for (int k=0; k< comp6.size(); k++){
		 									  ArrayList compSub = (ArrayList)comp6.get(k);
		 									  dto.setIdProofName((String) compSub.get(1));
		 									  dto.setIdProofNo((String) compSub.get(0));
		 								  }
		 								  }
		 								  }
		 							}
		 						if("en".equalsIgnoreCase(language))
		 						{
		 						dto.setPersonType("Applicant");
		 						}
		 						else
		 						{
		 							dto.setPersonType("आवेदक");
		 						}
		 						}// end of releaser type=1
						
						if(dto.getReleaserType().equalsIgnoreCase("2")){
							ArrayList relativeDetls = new ArrayList();
  						ArrayList comp2 = new ArrayList();
  						relativeDetls=lockBD.getRelativeDetls(regno,refId,language);
  						if (relativeDetls.size()>0){
  								for(int i =0; i<relativeDetls.size(); i++){
  									comp2.add((ArrayList)relativeDetls.get(i));
  									  if(comp2 != null && comp2.size()>0){ 
  									  for (int k=0; k< comp2.size(); k++){
  										  ArrayList compSub = (ArrayList)comp2.get(k);
  										  
  										 dto.setReleaserName((String) compSub.get(0));
  										 dto.setRelation((String) compSub.get(1));
  										 dto.setRelFatherName((String) compSub.get(2));
  										 dto.setRelMotherName((String) compSub.get(3));
  										 dto.setRcountryName((String) compSub.get(4));
  										 dto.setRstateName((String) compSub.get(5));
  										 dto.setRdistrictName((String) compSub.get(6));
  										 dto.setRelAddress((String) compSub.get(7));
  										 dto.setRelMobNo((String) compSub.get(8));
  										 dto.setRelphone((String) compSub.get(9));
  										 dto.setRelEmail((String) compSub.get(10));
  										 dto.setRelDeathCerName((String) compSub.get(11));
  										 dto.setRelPhotoName((String) compSub.get(12));
  										 dto.setRelThumbName((String) compSub.get(13));
  										 dto.setRelSignName((String) compSub.get(14));
  										 dto.setRelDeathCrtPath((String) compSub.get(15));
  										 dto.setRelPhotoPath((String) compSub.get(16));
  										 dto.setRelThumbPath((String) compSub.get(17));
  										 dto.setRelSignPath((String) compSub.get(18));
  										if("en".equalsIgnoreCase(language))
										 {
										 dto.setPersonType("Relative");
										 }
										 else
										 {
											 dto.setPersonType("संबंधी");
										 }
  										
  										 }
  									  }
  									  }
  								}
  						}// end of releaser type=2
						// payment details
						ArrayList paydtl=new ArrayList();
						ArrayList subPayDtl=new ArrayList();
						paydtl = lockBD.getPayDtlsV(refId);
						if (paydtl.size()>0){
								for(int i =0; i<paydtl.size(); i++){
									subPayDtl.add((ArrayList)paydtl.get(i));
									  if(subPayDtl != null && subPayDtl.size()>0){ 
									  for (int k=0; k< subPayDtl.size(); k++){
										  ArrayList compSub = (ArrayList)subPayDtl.get(k);
										  dto.setTotalFee((String) compSub.get(0));
										  if(dto.getTotalFee()==null)
											{
												dto.setTotalFee("0");
											}
									  }
									  }
								}
						}else{
							dto.setTotalFee("0");
						    }
						
						formName="";
						actionName="";
						dto.setIsFromView(1);
						forwardPage = "releaseFirstView"; 
					}
				//locked
				if(status.equalsIgnoreCase("2"))
				{
					
					// transaction details---
		        	ArrayList txnDetls = new ArrayList();
					ArrayList comp = new ArrayList();
					txnDetls=lockBD.getTxnDetlsV(regno,propiId,refId,language);
					 if (txnDetls.size()>0){
							for(int i =0; i<txnDetls.size(); i++){
								comp.add((ArrayList)txnDetls.get(i));
								  if(comp != null && comp.size()>0){ 
								  for (int k=0; k< comp.size(); k++){
									  ArrayList compSub = (ArrayList)comp.get(k);
									  dto.setPropertyLockid((String) compSub.get(0));
									  dto.setRegistrationId((String) compSub.get(1));
									  dto.setRegistrationDate((String) compSub.get(2));
									  String prId = (String) compSub.get(3);
									  if (prId.length()==15){
										  prId = "0"+prId;
									  }
									  dto.setPropId(prId);
									  dto.setPoaRegNo((String) compSub.get(4));
									  dto.setPoaRegDate((String) compSub.get(5));
									  dto.setLockStatus((String) compSub.get(6));
									  dto.setPurpose((String) compSub.get(7));
									  dto.setDateLocking((String) compSub.get(8));
									 }
								  }
								  }
							}
					 
					   //--party details
					  ArrayList partyDetls = new ArrayList();
						ArrayList comp2 = new ArrayList();
						partyDetls=lockBD.getPartyDetls(regno,refId,language);
						if (partyDetls.size()>0){
								for(int i =0; i<partyDetls.size(); i++){
									comp2.add((ArrayList)partyDetls.get(i));
									  if(comp2 != null && comp2.size()>0){ 
									  for (int k=0; k< comp2.size(); k++){
										  ArrayList compSub = (ArrayList)comp2.get(k);
										  String apptype=(String) compSub.get(0);
										  dto.setAppType((String) compSub.get(0));
										  dto.setAppTypeName((String) compSub.get(1));
										 if(apptype.equalsIgnoreCase("2")){
											  dto.setFirstName((String) compSub.get(2));
											  dto.setMidName((String) compSub.get(3));
											  dto.setLastName((String) compSub.get(4));
											  dto.setGender((String) compSub.get(5));
											  dto.setAge((String) compSub.get(6));
											  dto.setAddress((String) compSub.get(10));
										      dto.setPhone((String) compSub.get(12));
										      dto.setMphone((String) compSub.get(13));
											  dto.setPin((String) compSub.get(11));
											  dto.setEmail((String) compSub.get(14));
											  /*dto.setIdProofName((String) compSub.get(15));
											  dto.setIdProofNo((String) compSub.get(16));*/
											  dto.setBankName((String) compSub.get(15));
											  dto.setBankAddress((String) compSub.get(16));
											  dto.setGuardianName((String) compSub.get(19));
											  dto.setMotherName((String) compSub.get(20));
											 }else
												 if(apptype.equalsIgnoreCase("1")){
													 dto.setOrgName((String) compSub.get(17));
													 dto.setAuthName((String) compSub.get(18));
													 dto.setOrgAddress((String) compSub.get(10));
													 dto.setOrgPhno((String) compSub.get(12));
													 dto.setOrgMobno((String) compSub.get(13));
												 }
										  dto.setOrgCountryName((String) compSub.get(7));
										  dto.setOrgStateName((String) compSub.get(8));
										  dto.setOrgDistrictName((String) compSub.get(9));
										  dto.setDocumentName((String) compSub.get(21));
										  dto.setThunmbName((String) compSub.get(22));
										  dto.setSignatureName((String) compSub.get(23));
										  dto.setPhotoPath((String) compSub.get(24));
										  dto.setThumbPath((String) compSub.get(25));
										  dto.setSignPath((String) compSub.get(26));
										
										 }
									  }
									  }
								} 
								ArrayList photoDetls = new ArrayList();
		 						ArrayList comp6 = new ArrayList();
		 						photoDetls=lockBD.getPhotoDetl(refId,language);
		 						if (photoDetls.size()>0){
		 							for(int i =0; i<photoDetls.size(); i++){
		 								comp6.add((ArrayList)photoDetls.get(i));
		 								  if(comp6 != null && comp6.size()>0){ 
		 								  for (int k=0; k< comp6.size(); k++){
		 									  ArrayList compSub = (ArrayList)comp6.get(k);
		 									  dto.setIdProofName((String) compSub.get(1));
		 									  dto.setIdProofNo((String) compSub.get(0));
		 								  }
		 								  }
		 								  }
		 							}
		 					// payment details
								ArrayList paydtl=new ArrayList();
								ArrayList subPayDtl=new ArrayList();
								paydtl = lockBD.getPayDtlsV(refId);
								if (paydtl.size()>0){
										for(int i =0; i<paydtl.size(); i++){
											subPayDtl.add((ArrayList)paydtl.get(i));
											  if(subPayDtl != null && subPayDtl.size()>0){ 
											  for (int k=0; k< subPayDtl.size(); k++){
												  ArrayList compSub = (ArrayList)subPayDtl.get(k);
												  dto.setTotalFee((String) compSub.get(0));
												  //ADDED BY SHRUTI---8 JUNE 2014
												  if(dto.getTotalFee()==null)
												  {
													  dto.setTotalFee("0");
												  }
											  }
											  }
										}
								}else{
									dto.setTotalFee("0");
								    }
					formName="";
					actionName="";
					dto.setIsFromView(1);
					forwardPage = "lockFirstView"; 
					}	
				formName="";
				actionName="";
				
				}
			}	
		}// end of actionLinkView=hyperClickView
   		
   		
		// start of form dash board
		if (formName!=null && formName.equalsIgnoreCase("dashboardForm")){
			
			// create new hyper link action
			if(request.getParameter("Nextpage")!=null){
				nextPage = (String)request.getParameter("Nextpage");
			}
			if(!(nextPage).equalsIgnoreCase("")){
				if(nextPage.equalsIgnoreCase("createNew")){
					forwardPage="lockFirst";
					}
				}	
		}// end of dash board form
		
		
		
		// start of form propertyDisplay
		if (formName!=null && formName.equalsIgnoreCase("propertyDisplay")){
			if(actionName.equalsIgnoreCase("CONFIRMATION_OK_ACTION")){
				formName="";
                actionName="";
               forwardPage = dto.getForwrdAftrPrprtyDisp();
			}
			
		}// end of propertyDisplay
		
		
		
		
		// start of form PlockRegSearchPopup
		if (formName!=null && formName.equalsIgnoreCase("PlockRegSearchPopup")){
			
			if(actionName.equalsIgnoreCase("regSearch")){
				//dto.setRegDetls(new ArrayList());
				dto.setIsRegDetlEmpty(0);
			    String regno = dto.getRegCompNumber();
			    logger.debug("inside popup page, reg no is = "+regno);
			    ArrayList transDetl = new ArrayList();
				try{
			    transDetl= lockBD.getTransDetl(dto.getRegCompNumber(),language);
				if(transDetl.size()>0){
				logger.debug("inside popup page, reg detl is not empty");
		        dto.setRegDetls(transDetl);
		        //MODIFIED BY SHRUTI---21 FEB 2014
		        
		       dto.setIsDisable("Y");
		        //END
		        formName="";
		        actionName="";
		        forwardPage = "popupSearch";
			     }else{
				logger.debug("inside popup page, reg detl is empty");
				dto.setIsRegDetlEmpty(1);
				formName="";
		        actionName="";
				forwardPage = "popupSearch";
			   }
				}catch(Exception e){
				logger.debug("inside popup page1, reg detl is empty");
				dto.setIsRegDetlEmpty(1);
				formName="";
		        actionName="";
				forwardPage = "popupSearch";
			}
			}// end of action regsearch
			
			if(actionName.equalsIgnoreCase("addPropDet")){
				logger.debug("inside addpropDet action");
				//modified by shruti--8 june 2014
				if(dto.getPropAry()!=null &&dto.getPropAry().contains(",")){
					String[] chckIDary=  dto.getPropAry().split(",");
					ArrayList list1 = new ArrayList();
					PropertyLockDTO dtonew = new PropertyLockDTO();
					for(int i = 0; i<chckIDary.length; i++){
						String[]chckIDary2 = chckIDary[i].split("~");
						dtonew.setRegistrationId(chckIDary2[1]);
						logger.debug("inside addpropDet action, registration number is = "+chckIDary2[1]);
						dtonew.setRegistrationDate(chckIDary2[2]);
						logger.debug("inside addpropDet action, registration date is = "+chckIDary2[2]);
						dtonew.setPropId(chckIDary2[0]);
						logger.debug("inside addpropDet action, property id is = "+chckIDary2[0]);
						dto.setRegistrationId(chckIDary2[1]);
						dto.setRegistrationDate(chckIDary2[2]);
						list1.add(i,dtonew);
					    dtonew = new PropertyLockDTO();
					    }
					dto.setPropList(list1);
					}else if(dto.getPropAry()!=null && !dto.getPropAry().equals("") && dto.getPropAry().contains("~")){//modified by shruti
						String chckIDary[] = new String[1];
						chckIDary[0]=dto.getPropAry();
					    String[] chckIDary2 = chckIDary[0].split("~");
					    ArrayList list1 = new ArrayList();
					    dto.setRegistrationId(chckIDary2[1]);
					    logger.debug("inside addpropDet action else condition, registration number  is = "+chckIDary2[1]);
						dto.setRegistrationDate(chckIDary2[2]);
						logger.debug("inside addpropDet action else condition, registration date is = "+chckIDary2[2]);
						 String prId = chckIDary2[0];
						  if (prId.length()==15){
							  prId = "0"+prId;
						  }
						 dto.setPropId(prId);
						//dto.setPropId(chckIDary2[0]);
						logger.debug("inside addpropDet action else condition, property id is = "+chckIDary2[0]);
						list1.add(dto);
						dto.setPropList(list1);
					}
				dto.setAppType("");
				dto.setGenClick("");
				dto.setDocumentName("");
				dto.setThunmbName("");
				dto.setSignatureName("");
				dto.setPurpose("");
				dto.setPoaRegDate("");
				dto.setPoaRegNo("");
				formName="";
				actionName="";
				//commented by shruti----as discussed with Anuj
				//added by shruti--12 th nov 2013
				/*dto.setGuidUpload(GUIDGenerator.getGUID());
				dto.setParentPathFP("D:/Upload/11/Lock/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"ThumbPrint");
				dto.setFileNameFP("FingerPrint.BMP");
				dto.setParentPathScan("D:/Upload/11/Lock/Scan");
				dto.setFileNameScan("CapturedDocument.PDF");
				dto.setParentPathSign("D:/Upload/11/Lock/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"Sign");
				dto.setFileNameSign("CapturedScan.GIF");
				dto.setForwardPath("/lockAction.do?uploadSign=true");
				dto.setForwardName("lockFirst");
				dto.setPhotoName("Photo.GIF");
				dto.setWebcameraPath("D:/Upload/11/Lock/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"Photo");
				//end
*/				forwardPage = "lockFirst";
				}// end of action add prop det
			
			//modified by shruti----4 april 2014
			
		}// end of form PlockRegSearchPopup
		
		
		
		
		
		
	     // start of lockMain page
		if (formName!=null && formName.equalsIgnoreCase("lockMain")){
			
			//issue in searching reg number--fixing
			if(request.getParameter("actionName")!=null)
			{
			if(("addPropDet").equalsIgnoreCase(request.getParameter("actionName").toString())){
				logger.debug("inside addpropDet action");
				if(dto.getPropAry().contains(",")){
					String[] chckIDary=  dto.getPropAry().split(",");
					ArrayList list1 = new ArrayList();
					PropertyLockDTO dtonew = new PropertyLockDTO();
					for(int i = 0; i<chckIDary.length; i++){
						String[]chckIDary2 = chckIDary[i].split("~");
						dtonew.setRegistrationId(chckIDary2[1]);
						logger.debug("inside addpropDet action, registration number is = "+chckIDary2[1]);
						dtonew.setRegistrationDate(chckIDary2[2]);
						logger.debug("inside addpropDet action, registration date is = "+chckIDary2[2]);
						dtonew.setPropId(chckIDary2[0]);
						logger.debug("inside addpropDet action, property id is = "+chckIDary2[0]);
						dto.setRegistrationId(chckIDary2[1]);
						dto.setRegistrationDate(chckIDary2[2]);
						list1.add(i,dtonew);
					    dtonew = new PropertyLockDTO();
					    }
					dto.setPropList(list1);
					}else if(dto.getPropAry()!=null && !dto.getPropAry().equalsIgnoreCase("") && dto.getPropAry().contains("~")){
						String chckIDary[] = new String[1];
						chckIDary[0]=dto.getPropAry();
					    String[] chckIDary2 = chckIDary[0].split("~");
					    ArrayList list1 = new ArrayList();
					    dto.setRegistrationId(chckIDary2[1]);
					    logger.debug("inside addpropDet action else condition, registration number  is = "+chckIDary2[1]);
						dto.setRegistrationDate(chckIDary2[2]);
						logger.debug("inside addpropDet action else condition, registration date is = "+chckIDary2[2]);
						 String prId = chckIDary2[0];
						  if (prId.length()==15){
							  prId = "0"+prId;
						  }
						 dto.setPropId(prId);
						//dto.setPropId(chckIDary2[0]);
						logger.debug("inside addpropDet action else condition, property id is = "+chckIDary2[0]);
						list1.add(dto);
						dto.setPropList(list1);
					}
				dto.setAppType("");
				dto.setGenClick("");
				dto.setDocumentName("");
				dto.setThunmbName("");
				dto.setSignatureName("");
				dto.setPurpose("");
				dto.setPoaRegDate("");
				dto.setPoaRegNo("");
				formName="";
				actionName="";
				eForm.setActionName(actionName);
				forwardPage = "lockFirst";
				}
			// start of action downloadPhoto
			  if("downloadPhoto".equalsIgnoreCase(actionName)){
				        String filePath=dto.getPhotoPath()+"/"+dto.getDocumentName();
		    			logger.debug("retrieval path of photo-->"+filePath);
		    			DMSUtility dmsUtil=new DMSUtility();
		    			byte[] docContents=dmsUtil.getDocumentBytes(filePath);
		    			dmsUtil.downloadDocument(response, filePath, docContents);
		    			//dmsUtil.readImage(filePath);
		    			actionName="";
		    			formName="";
		    			forwardPage="lockFirst";
					}// end of downloadPhoto
			
			// start of action downloadSign
			  if("downloadSign".equalsIgnoreCase(actionName)){
				    String filePath=dto.getSignPath()+"/"+dto.getSignatureName();
	    			logger.debug("retrieval path of sign-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			byte[] docContents=dmsUtil.getDocumentBytes(filePath);
	    			dmsUtil.downloadDocument(response, filePath, docContents);
	    			//dmsUtil.readImage(filePath);
	    			actionName="";
	    			formName="";
	    			forwardPage="lockFirst";
			  }// end of downloadSign
			  
			// start of action downloadThumb
			  if("downloadThumb".equalsIgnoreCase(actionName)){
				    String filePath=dto.getThumbPath()+"/"+dto.getThunmbName();
	    			logger.debug("retrieval path of thumb-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			byte[] docContents=dmsUtil.getDocumentBytes(filePath);
	    			dmsUtil.downloadDocument(response, filePath, docContents);
	    			//dmsUtil.readImage(filePath);
	    			actionName="";
	    			formName="";
	    			forwardPage="lockFirst"; 
			  }// end of downloadThumb
			  if("downloadPhoto1".equalsIgnoreCase(actionName)){
			        String filePath=dto.getPhotoPath()+"/"+dto.getDocumentName();
	    			logger.debug("retrieval path of photo-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			byte[] docContents=dmsUtil.getDocumentBytes(filePath);
	    			dmsUtil.downloadDocument(response, filePath, docContents);
	    			//dmsUtil.readImage(filePath);
	    			actionName="";
	    			formName="";
	    			forwardPage="lockFirst";
				}// end of downloadPhoto
		
		// start of action downloadSign
		  if("downloadSign1".equalsIgnoreCase(actionName)){
			    String filePath=dto.getSignPath()+"/"+dto.getSignatureName();
  			logger.debug("retrieval path of sign-->"+filePath);
  			DMSUtility dmsUtil=new DMSUtility();
  			byte[] docContents=dmsUtil.getDocumentBytes(filePath);
  			dmsUtil.downloadDocument(response, filePath, docContents);
  			//dmsUtil.readImage(filePath);
  			actionName="";
  			formName="";
  			forwardPage="lockFirstView";
		  }// end of downloadSign
		  
		// start of action downloadThumb
		  if("downloadThumb1".equalsIgnoreCase(actionName)){
			    String filePath=dto.getThumbPath()+"/"+dto.getThunmbName();
  			logger.debug("retrieval path of thumb-->"+filePath);
  			DMSUtility dmsUtil=new DMSUtility();
  			byte[] docContents=dmsUtil.getDocumentBytes(filePath);
  			dmsUtil.downloadDocument(response, filePath, docContents);
  			//dmsUtil.readImage(filePath);
  			actionName="";
  			formName="";
  			forwardPage="lockFirstView"; 
		  }// end of downloadThumb
		  
			  
			  
			  
			  
			}// end of action add prop det
			//end
			
			
			//added by shruti--12th nov 2013
	        String uploadChk=(String)request.getParameter("uploadSign");
	        if(uploadChk!=null)
	        {
	      	  if(uploadChk.equals("true"))
	      	  {
	      		  //modified by shruti---18 july 2014
	      		String completePathFP = dto.getParentPathSign() +"/"+ dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"Sign"+"/"+ dto.getGuidUpload();
	      		dto.setSignPath(completePathFP);
	      		dto.setSignatureName(dto.getFileNameSign());
					logger.debug("UploadSignRead completePathFP : " + completePathFP);
					logger.debug("UploadSignRead dto.getFileNameFP() : " + dto.getSignatureName());
					formName = "";
					actionName = "";
					forwardPage = "lockFirst";
	      	  }
	        }
	        
	        //end
			// start of action apptype
			if(actionName.equalsIgnoreCase("appType")){
				String apptype = dto.getAppType();
				if (apptype.equalsIgnoreCase("1")){
					 dto.setOrgName("");
					 dto.setAuthName("");
					 dto.setOrgCountry("");
					 dto.setOrgCountryName("");
					 dto.setOrgState("");
					 dto.setOrgStateName("");
					 dto.setOrgDistrict("");
					 dto.setOrgDistrictName("");
					 dto.setOrgAddress("");
					 dto.setOrgMobno("");
					 dto.setOrgPhno("");
					 dto.setIdProofName("");
					 dto.setFirstName("");
					 dto.setMidName("");
					 dto.setLastName("");
					 dto.setGender("");
					 dto.setGenClick("");
					 dto.setAge("");
					 dto.setGuardianName("");
					 dto.setMotherName("");
					 dto.setAddress("");
					 dto.setPin("");
					 dto.setPhone("");
					 dto.setMphone("");
					 dto.setEmail("");
					 dto.setIdProof("");
					 dto.setIdProofName("");
					 dto.setIdProofNo("");
					 dto.setBankName("");
					 dto.setBankAddress("");
					
				}
                 if (apptype.equalsIgnoreCase("2")){
					
                	 dto.setOrgName("");
					 dto.setAuthName("");
					 dto.setOrgCountry("");
					 dto.setOrgCountryName("");
					 dto.setOrgState("");
					 dto.setOrgStateName("");
					 dto.setOrgDistrict("");
					 dto.setOrgDistrictName("");
					 dto.setOrgAddress("");
					 dto.setOrgMobno("");
					 dto.setOrgPhno("");
					 dto.setIdProofName("");
					 dto.setFirstName("");
					 dto.setMidName("");
					 dto.setLastName("");
					 dto.setGender("");
					 dto.setGenClick("");
					 dto.setAge("");
					 dto.setGuardianName("");
					 dto.setMotherName("");
					 dto.setAddress("");
					 dto.setPin("");
					 dto.setPhone("");
					 dto.setMphone("");
					 dto.setEmail("");
					 dto.setIdProof("");
					 dto.setIdProofName("");
					 dto.setIdProofNo("");
					 dto.setBankName("");
					 dto.setBankAddress("");
                	}
                 formName="";
                 actionName="";
                 //added by shruti----4 april 2014
                 eForm.setActionName(actionName);
                 //end
                 forwardPage="lockFirst";
                 }// end of action apptype
			
			//start of action resetLock1
			if(actionName.equalsIgnoreCase("resetLock1")){
				 //dto.setIsPayment(0);
				 dto.setIsRegDetlEmpty(0);
				 dto.setIsAlrdyLocked(0);
				 dto.setNotInsertedLock(0);
				 dto.setNotInsertedRel(0);
				 dto.setIsLockSuccess(0);
				 dto.setIsRelSuccess(0);
				 dto.setIsLockSave(0);
				 dto.setIsReleaseSave(0);
				 dto.setIsPayCompl(0);
				 dto.setIsPartial(0);
				 dto.setNotInsertedP(0);
				 dto.setNotUpdatedAfterPay(0);
				 dto.setNoRecFound(0);
				 
				 dto.setForwrdAftrPrprtyDisp("");
				 dto.setAppType("");
				 dto.setAppTypeName("");
				 dto.setOrgName("");
				 dto.setAuthName("");
				 dto.setOrgCountry("");
				 dto.setOrgCountryName("");
				 dto.setOrgState("");
				 dto.setOrgStateName("");
				 dto.setOrgDistrict("");
				 dto.setOrgDistrictName("");
				 dto.setOrgAddress("");
				 dto.setOrgMobno("");
				 dto.setOrgPhno("");
				 dto.setIdProofName("");
				 dto.setPropertyLockid("");
				 dto.setAlrdyLockId("");
				 dto.setMapPropertyTransPartyDisp(new HashMap());
				 dto.setPropertyReleaseId("");
				 dto.setLockDetailsRelSearch(new ArrayList());
				 dto.setLockComb("");
				 dto.setRelationId("");
				 dto.setRegCompNumber("");
				 dto.setRegCompDate("");
				 dto.setPropId("");
				 dto.setPropStatus("");
				 dto.setPropDeed("");
				 dto.setPropAry("");
				 dto.setChckBx("");
				 dto.setPropComb("");
				 dto.setIsDisable("");
				 dto.setRadioSelect("");
				 dto.setRegDetls(new ArrayList());
				 dto.setPropList(new ArrayList());
				 //dto.setPayableFee("");
				 //dto.setAlrdyPaidFee("");
				 dto.setPrimKeyPymt("");
				 dto.setFlg("");
				 dto.setPhotoPath("");
				 dto.setThumbPath("");
				 dto.setSignPath("");
				 dto.setRegistrationId("");
				 dto.setRegistrationDate("");
				 dto.setFirstName("");
				 dto.setMidName("");
				 dto.setLastName("");
				 dto.setGender("");
				 dto.setGenClick("");
				 dto.setAge("");
				 dto.setGuardianName("");
				 dto.setMotherName("");
				 dto.setAddress("");
				 dto.setPin("");
				 dto.setPhone("");
				 dto.setMphone("");
				 dto.setEmail("");
				 dto.setIdProof("");
				 dto.setIdProofName("");
				 dto.setIdProofNo("");
				 dto.setBankName("");
				 dto.setBankAddress("");
				 dto.setFilePhoto(null);
				 dto.setDocumentName("");
				 dto.setFileThumb(null);
				 dto.setThunmbName("");
				 dto.setFileSignature(null);
				 dto.setSignatureName("");
				 dto.setPurpose("");
				 dto.setPoaRegDate("");
				 dto.setPoaRegNo("");
				 dto.setFromRequestDate("");
				 dto.setToRequestDate("");
				 dto.setDateLocking("");
				 dto.setLockStatus("");
				 dto.setPersonType("");
				 dto.setReleaserType("");
				 dto.setReleaserName("");
				 dto.setRelation("");
				 dto.setRelationId("");
				 dto.setRcountryId("");
				 dto.setRcountryName("");
				 dto.setRstate("");
				 dto.setRstateId("");
				 dto.setRstateName("");
				 dto.setRdistrictId("");
				 dto.setRdistrictName("");
				 dto.setRelFatherName("");
				 dto.setRelMotherName("");
				 dto.setRelphone("");
				 dto.setRelMobNo("");
				 dto.setRelEmail("");
				 dto.setRelAddress("");
				 dto.setRelAttachDoc(null);
				 dto.setRelPhotoName("");
				 dto.setRelPhotoPath("");
				 dto.setRelAttachthumb(null);
				 dto.setRelThumbName("");
				 dto.setRelThumbPath("");
				 dto.setRelAttaSig(null);
				 dto.setRelSignName("");
				 dto.setRelSignPath("");
				 dto.setRelDeathCerAttach(null);
				 dto.setRelDeathCerName("");
				 dto.setRelDeathCrtPath("");
				 dto.setReasonForRelease("");
				 dto.setRemarksForRelease("");
				 dto.setViewComb("");
				 dto.setViewResultList(new ArrayList());
				 dto.setPropertyTxnId("");
				 dto.setAppStatus("");
				 dto.setCreatedDt("");
				 dto.setIsViewEmpty(0);
				 dto.setIsFromView(0);	
				 formName="";
                 actionName="";
                 eForm.setActionName(actionName);
                 forwardPage="lockFirst";
				
				
			}//end of action resetLock1
			
			
			// start of action stateload
			if(actionName.equalsIgnoreCase("stateload")){
				
				String countryId = dto.getOrgCountry();
				if (!countryId.equals(null) || countryId != null
						|| countryId != "") {
					
					dto.setStateList(lockBD.getState(countryId,language));
				} /*else {
					String countryId2 = eForm.getPartyCountry();
					eForm.setStateList(bo.getState(countryId2));
				}*/
				formName="";
				actionName="";
				forwardPage="lockFirst";
                 }// end of action state load
			
			
			
			
			// start of action districtLoad
              if(actionName.equalsIgnoreCase("districtLoad")){
				
				String countryId = dto.getOrgState();
				if (!countryId.equals(null) || countryId != null
						|| countryId != "") {
					dto.setDistrictList(lockBD.getDistrict(countryId,language));
				} /*else {
					String countryId2 = eForm.getPartyCountry();
					eForm.setStateList(bo.getState(countryId2));
				}*/
				formName="";
				actionName="";
				forwardPage="lockFirst";
                 }// end of action districtLoad
              
              
              
              
              // start of action setUploadPhoto
              if(actionName.equalsIgnoreCase("setUploadPhoto")){
              
            	  ArrayList	errorList = new ArrayList();
				  FormFile uploadedFile = dto.getFilePhoto();
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
						request.setAttribute("errorsList",errorList);
					} else {
						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
							errorList.add("File size is Greater than 10 MB. Please select another file.");
							request.setAttribute("errorsList",errorList);
						} else {
							  Date date = new Date();
							  Format yearformat  = new SimpleDateFormat("yy");
							  Format monthformat = new SimpleDateFormat("MM");
							  Format dateformat  = new SimpleDateFormat("dd");
							  Format hourformat  = new SimpleDateFormat("hh");
							  Format minformat  = new SimpleDateFormat("mm");
							  Format secformat  = new SimpleDateFormat("ss");
							  Format ampmformat  = new SimpleDateFormat("a");
							  String dfmt = dateformat.format(date);
							  String yfmt = yearformat.format(date);
							  String mfmt = monthformat.format(date);
							  String hfmt = hourformat.format(date);
							  String mifmt = minformat.format(date);
							  String sfmt = secformat.format(date);
							  String ampmfmt = ampmformat.format(date);
							  String fldrnm = dfmt+mfmt+yfmt+hfmt+mifmt+sfmt+ampmfmt;
							  logger.debug("the new date in concatenated format in Photo upload is----------------------- >"+fldrnm);
							String docName = uploadedFile.getFileName();
							String docPath = pr.getValue("igrs_upload_path")+"/11/Lock/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/Photo/"+fldrnm;
							boolean up=uploadFile(dto.getFilePhoto(),docName,docPath);
							dto.setDocumentName(uploadedFile.getFileName());
							dto.setPhotoPath(docPath);
							dto.setDocContents(uploadedFile.getFileData());
							dto.setPhotoSize(photoSize);
							dto.setPhotoCheck("phloded");
							forwardPage="lockFirst";
							} 
						}
					formName="";
					actionName="";
					forwardPage="lockFirst";
					}// end of action setUploadPhoto
              
              //added by shruti--12th nov 2013
              if(actionName.equalsIgnoreCase("retrieveUploadPhoto")){
            	  //commented by shruti--as discussed with Anuj
            	//added by shruti--12 th nov 2013
  				/*dto.setGuidUpload(GUIDGenerator.getGUID());
  				dto.setParentPathFP("D:/Upload/11/Lock/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"ThumbPrint");
  				dto.setFileNameFP("FingerPrint.BMP");
  				dto.setParentPathScan("D:/Upload/11/Lock/Scan");
  				dto.setFileNameScan("CapturedDocument.PDF");
  				dto.setParentPathSign("D:/Upload/11/Lock/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"Sign");
  				dto.setFileNameSign("CapturedScan.GIF");
  				dto.setForwardPath("/lockAction.do?uploadSign=true");
  				dto.setForwardName("lockFirst");
  				dto.setPhotoName("Photo.GIF");
  				dto.setWebcameraPath("D:/Upload/11/Lock/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"Photo");
  				//end
*/            	  
          	  	String completePathFP = dto.getWebcameraPath() + "/"+ dto.getGuidUpload();
          	  		//dto.setWebcameraPath(completePathFP);
          	  		dto.setPhotoPath(completePathFP);
					dto.setDocumentName(dto.getPhotoName());
					logger.debug("PhotoUploadRead completePathFP : " + completePathFP);
					logger.debug("PhotoUploadRead dto.getFileNameFP() : " + dto.getPhotoName());
					formName = "";
					actionName = "";
					eForm.setActionName("");
					forwardPage = "lockFirst";
            }
              //end
              
              if(actionName.equalsIgnoreCase("retrieveUploadThumb")){
            	  //commented by shruti---- as discussed with Anuj
            	//added by shruti--12 th nov 2013
  				/*dto.setGuidUpload(GUIDGenerator.getGUID());
  				dto.setParentPathFP("D:/Upload/11/Lock/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"ThumbPrint");
  				dto.setFileNameFP("FingerPrint.BMP");
  				dto.setParentPathScan("D:/Upload/11/Lock/Scan");
  				dto.setFileNameScan("CapturedDocument.PDF");
  				dto.setParentPathSign("D:/Upload/11/Lock/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"Sign");
  				dto.setFileNameSign("CapturedScan.GIF");
  				dto.setForwardPath("/lockAction.do?uploadSign=true");
  				dto.setForwardName("lockFirst");
  				dto.setPhotoName("Photo.GIF");
  				dto.setWebcameraPath("D:/Upload/11/Lock/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"Photo");
  				//end
*/            	  	String completePathFP = dto.getParentPathFP() + "/"+ dto.getGuidUpload();
					dto.setThumbPath(completePathFP);
					dto.setThunmbName(dto.getFileNameFP());
					logger.debug("FingerPrintRead completePathFP : " + completePathFP);
					logger.debug("FingerPrintRead dto.getFileNameFP() : " + dto.getFileNameFP());
					formName = "";
					actionName = "";
					forwardPage = "lockFirst";
              }
              
           // start of action setUploadThumb
              if(actionName.equalsIgnoreCase("setUploadThumb")){
              
            	  ArrayList	errorList = new ArrayList();
				  FormFile uploadedFile = dto.getFileThumb();
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
					String thumbSize = "(" + fileSize + "MB)";
					if (rule.isError()) {
						errorList.add("Invalid file type. Please select another file.");
						request.setAttribute("errorsList",errorList);
					} else {
						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
							errorList.add("File size is Greater than 10 MB. Please select another file.");
							request.setAttribute("errorsList",errorList);
						} else {
							 Date date = new Date();
							  Format yearformat  = new SimpleDateFormat("yy");
							  Format monthformat = new SimpleDateFormat("MM");
							  Format dateformat  = new SimpleDateFormat("dd");
							  Format hourformat  = new SimpleDateFormat("hh");
							  Format minformat  = new SimpleDateFormat("mm");
							  Format secformat  = new SimpleDateFormat("ss");
							  Format ampmformat  = new SimpleDateFormat("a");
							  String dfmt = dateformat.format(date);
							  String yfmt = yearformat.format(date);
							  String mfmt = monthformat.format(date);
							  String hfmt = hourformat.format(date);
							  String mifmt = minformat.format(date);
							  String sfmt = secformat.format(date);
							  String ampmfmt = ampmformat.format(date);
							  String fldrnm = dfmt+mfmt+yfmt+hfmt+mifmt+sfmt+ampmfmt;
							logger.debug("the new date in upload thumb is----------------------- >"+fldrnm);
							String thumbName = uploadedFile.getFileName();
							String thumbPath = pr.getValue("igrs_upload_path")+"/11/Lock/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/Thumb/"+fldrnm;
							boolean up=uploadFile(dto.getFileThumb(),thumbName,thumbPath);
							
							dto.setThunmbName(uploadedFile.getFileName());
							dto.setThumbPath(thumbPath);
							dto.setThumbContents(uploadedFile.getFileData());
							dto.setThumbSize(thumbSize);
							dto.setThumbCheck("thloded");
							
							forwardPage="lockFirst";
							} 
						}
					formName="";
					actionName="";
					forwardPage="lockFirst";
					}// end of action setUploadThumb
              
             
              
              
           // start of action setUploadSign
              if(actionName.equalsIgnoreCase("setUploadSign")){
                  ArrayList	errorList = new ArrayList();
				  FormFile uploadedFile = dto.getFileSignature();
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
					String signSize = "(" + fileSize + "MB)";
					if (rule.isError()) {
						errorList.add("Invalid file type. Please select another file.");
						request.setAttribute("errorsList",errorList);
					} else {
						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
							errorList.add("File size is Greater than 10 MB. Please select another file.");
							request.setAttribute("errorsList",errorList);
						} else {
							Date date = new Date();
							  Format yearformat  = new SimpleDateFormat("yy");
							  Format monthformat = new SimpleDateFormat("MM");
							  Format dateformat  = new SimpleDateFormat("dd");
							  Format hourformat  = new SimpleDateFormat("hh");
							  Format minformat  = new SimpleDateFormat("mm");
							  Format secformat  = new SimpleDateFormat("ss");
							  Format ampmformat  = new SimpleDateFormat("a");
							  String dfmt = dateformat.format(date);
							  String yfmt = yearformat.format(date);
							  String mfmt = monthformat.format(date);
							  String hfmt = hourformat.format(date);
							  String mifmt = minformat.format(date);
							  String sfmt = secformat.format(date);
							  String ampmfmt = ampmformat.format(date);
							  String fldrnm = dfmt+mfmt+yfmt+hfmt+mifmt+sfmt+ampmfmt;
							logger.debug("the new date in signature upload is----------------------- >"+fldrnm);
							String signName = uploadedFile.getFileName();
							String signPath = pr.getValue("igrs_upload_path")+"/11/Lock/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/Signature/"+fldrnm;
							boolean up=uploadFile(dto.getFileSignature(),signName,signPath);
							
							dto.setSignatureName(uploadedFile.getFileName());
							dto.setSignPath(signPath);
							dto.setSignatureContents(uploadedFile.getFileData());
							dto.setSignatureSize(signSize);
							dto.setSignCheck("signloded");
														
							forwardPage="lockFirst";
							} 
						}
					formName="";
					actionName="";
					forwardPage="lockFirst";
            	 }// end of action setUploadSign
              
              
              
              
              // start of action lockProp
              if(actionName.equalsIgnoreCase("lockProp")){
            	
            	  
            	 dto.setPropertyLockDisplayAction("");
             	 dto.setPropertyLockDisplayAction("");
             	 dto.setPaymentSuccessAction("");
             	 
             	 ArrayList lockinfo=new ArrayList();
             	 ArrayList comp7 = new ArrayList();
             	 String st="";
             	 String refId="";
             	 lockinfo = lockBD.getLockStatus(dto.getRegistrationId(), dto.getPropId()); 
             	 
             	 if(lockinfo.size()>0){
             		 
             		 for(int i=0; i<lockinfo.size(); i++){
             			comp7.add((ArrayList)lockinfo.get(i));
						  if(comp7 != null && comp7.size()>0){ 
						  for (int k=0; k< comp7.size(); k++){
							  ArrayList compSub = (ArrayList)comp7.get(k);
							  st = (String) compSub.get(0);
							  refId = (String) compSub.get(1);
							 }
						  } 
             		 }
             		 
             		 
             	 if("1".equalsIgnoreCase(st))
                  {
             			 String retUnLockId ="";              
    	                 retUnLockId = lockBD.insertLockDetls(dto,userId,officeId );  
    	            	 if(!retUnLockId.equalsIgnoreCase(""))
    			         {
    			        	dto.setPropertyLockid(retUnLockId);
    			        	// transaction details---
    			        	ArrayList txnDetls = new ArrayList();
    						ArrayList comp = new ArrayList();
    						txnDetls=lockBD.getTxnDetls(dto.getRegistrationId(), dto.getPropId(),retUnLockId,language);
    						 if (txnDetls.size()>0){
    								for(int i =0; i<txnDetls.size(); i++){
    									comp.add((ArrayList)txnDetls.get(i));
    									  if(comp != null && comp.size()>0){ 
    									  for (int k=0; k< comp.size(); k++){
    										  ArrayList compSub = (ArrayList)comp.get(k);
    										  dto.setPropertyLockid((String) compSub.get(0));
    										  dto.setRegistrationId((String) compSub.get(1));
    										  dto.setRegistrationDate((String) compSub.get(2));
    										  String prId = (String) compSub.get(3);
											  if (prId.length()==15){
												  prId = "0"+prId;
											  }
											  dto.setPropId(prId);
    										  dto.setPoaRegNo((String) compSub.get(4));
    										  dto.setPoaRegDate((String) compSub.get(5));
    										  dto.setLockStatus((String) compSub.get(6));
    										  dto.setPurpose((String) compSub.get(7));
    										  dto.setDateLocking((String) compSub.get(8));
    										 }
    									  }
    									  }
    								}
    						 //--party details
    						ArrayList partyDetls = new ArrayList();
     						ArrayList comp2 = new ArrayList();
     						partyDetls=lockBD.getPartyDetls(dto.getRegistrationId(),retUnLockId,language);
     						if (partyDetls.size()>0){
     								for(int i =0; i<partyDetls.size(); i++){
     									comp2.add((ArrayList)partyDetls.get(i));
     									  if(comp2 != null && comp2.size()>0){ 
     									  for (int k=0; k< comp2.size(); k++){
     										  ArrayList compSub = (ArrayList)comp2.get(k);
     										  String apptype=(String) compSub.get(0);
     										  dto.setAppType((String) compSub.get(0));
     										  dto.setAppTypeName((String) compSub.get(1));
     										 if(apptype.equalsIgnoreCase("2")){
     											  dto.setFirstName((String) compSub.get(2));
     											  dto.setMidName((String) compSub.get(3));
     											  dto.setLastName((String) compSub.get(4));
     											  
     											  dto.setGender((String) compSub.get(5));
     											  //added by shruti---7 june 2014
     											 /* if("M".equalsIgnoreCase(dto.getGender()))
     											  {
     												  if("en".equalsIgnoreCase(language))
     												  {
     													 dto.setGender("male");
     												  }
     												  else
     												  {
     													 dto.setGender("पुस्र्ष");
     												  }
     												 
     												 
     											  }
     											  else
     											  {
     												 if("en".equalsIgnoreCase(language))
    												  {
    													 dto.setGender("female");
    												  }
    												  else
    												  {
    													 dto.setGender("महिला");
    												  }  
     											  }
     											  */
     											  dto.setAge((String) compSub.get(6));
     											  dto.setAddress((String) compSub.get(10));
     										      dto.setPhone((String) compSub.get(12));
     										      dto.setMphone((String) compSub.get(13));
     											  dto.setPin((String) compSub.get(11));
     											  dto.setEmail((String) compSub.get(14));
     											  /*dto.setIdProofName((String) compSub.get(15));
     											  dto.setIdProofNo((String) compSub.get(16));*/
     											  dto.setBankName((String) compSub.get(15));
     											  dto.setBankAddress((String) compSub.get(16));
     											  dto.setGuardianName((String) compSub.get(19));
     											  dto.setMotherName((String) compSub.get(20));
     											 }else
     												 if(apptype.equalsIgnoreCase("1")){
     													 dto.setOrgName((String) compSub.get(17));
     													 dto.setAuthName((String) compSub.get(18));
     													 dto.setOrgAddress((String) compSub.get(10));
     													 dto.setOrgPhno((String) compSub.get(12));
     													 dto.setOrgMobno((String) compSub.get(13));
     												 }
     										  dto.setOrgCountryName((String) compSub.get(7));
     										  dto.setOrgStateName((String) compSub.get(8));
     										  dto.setOrgDistrictName((String) compSub.get(9));
     										  dto.setDocumentName((String) compSub.get(21));
     										  dto.setThunmbName((String) compSub.get(22));
     										  dto.setSignatureName((String) compSub.get(23));
     										  dto.setPhotoPath((String) compSub.get(24));
     										  dto.setThumbPath((String) compSub.get(25));
     										  dto.setSignPath((String) compSub.get(26));
     										
     										 }
     									  }
     									  }
     								}ArrayList photoDetls = new ArrayList();
     		 						ArrayList comp6 = new ArrayList();
     		 						photoDetls=lockBD.getPhotoDetl(dto.getPropertyLockid(),language);
     		 						if (photoDetls.size()>0){
     		 							for(int i =0; i<photoDetls.size(); i++){
     		 								comp6.add((ArrayList)photoDetls.get(i));
     		 								  if(comp6 != null && comp6.size()>0){ 
     		 								  for (int k=0; k< comp6.size(); k++){
     		 									  ArrayList compSub = (ArrayList)comp6.get(k);
     		 									  dto.setIdProofName((String) compSub.get(1));
     		 									  dto.setIdProofNo((String) compSub.get(0));
     		 								  }
     		 								  }
     		 								  }
     		 							}
    						formName="";
    						actionName="";
    						dto.setIsLockSuccess(1);
     						forwardPage = "lockFirstView";  
    			           }//end insertion
    		            	 else //failed to insert into DB
    				         {
    				        	    dto.setNotInsertedLock(1);
    				            	forwardPage = "lockFirst";  
    				         }
             		 
             		 
                 	
                  }
                  else if("2".equalsIgnoreCase(st)||"3".equalsIgnoreCase(st)||"4".equalsIgnoreCase(st))
                  {
                	  dto.setIsAlrdyLocked(1); 
                	  dto.setAlrdyLockId(refId);
                	  forwardPage = "lockFirst";                    
                  }
                             
             	 }
             	 }// end of action lockProp
              
              // start of lockSaveP
              if(actionName.equalsIgnoreCase("lockSaveP")){
                 dto.setPropertyLockDisplayAction("");
              	 dto.setPropertyLockDisplayAction("");
              	 dto.setPaymentSuccessAction("");
              	 
              	 ArrayList lockinfo=new ArrayList();
              	 ArrayList comp7 = new ArrayList();
              	 String st="";
              	 String refId="";
              	 lockinfo = lockBD.getLockStatus(dto.getRegistrationId(), dto.getPropId()); 
              	 
              	 if(lockinfo.size()>0){
              		 
              		 for(int i=0; i<lockinfo.size(); i++){
              			comp7.add((ArrayList)lockinfo.get(i));
 						  if(comp7 != null && comp7.size()>0){ 
 						  for (int k=0; k< comp7.size(); k++){
 							  ArrayList compSub = (ArrayList)comp7.get(k);
 							  st = (String) compSub.get(0);
 							  refId = (String) compSub.get(1);
 							 }
 						  } 
              		 }
              		 
              		 
              	 if("1".equalsIgnoreCase(st))
                   {
              			 String retUnLockId ="";
              			 String completePathFP = dto.getParentPathFP() + "/" + dto.getGuidUpload() + "/" + dto.getFileNameFP();
              			 dto.setThumbPath(completePathFP);
              			 dto.setThunmbName(dto.getFileNameFP());
     	                 retUnLockId = lockBD.insertLockDetlsP(dto,userId,officeId );  
     	            	 if(!retUnLockId.equalsIgnoreCase(""))
     			         {
     			        	dto.setPropertyLockid(retUnLockId);
     			        	// transaction details---
     			        	ArrayList txnDetls = new ArrayList();
     						ArrayList comp = new ArrayList();
     						txnDetls=lockBD.getTxnDetlsP(dto.getRegistrationId(), dto.getPropId(),retUnLockId,language);
     						 if (txnDetls.size()>0){
     								for(int i =0; i<txnDetls.size(); i++){
     									comp.add((ArrayList)txnDetls.get(i));
     									  if(comp != null && comp.size()>0){ 
     									  for (int k=0; k< comp.size(); k++){
     										  ArrayList compSub = (ArrayList)comp.get(k);
     										  dto.setPropertyLockid((String) compSub.get(0));
     										  dto.setRegistrationId((String) compSub.get(1));
     										  dto.setRegistrationDate((String) compSub.get(2));
     										 String prId = (String) compSub.get(3);
											  if (prId.length()==15){
												  prId = "0"+prId;
											  }
											  dto.setPropId(prId);
     										  dto.setPoaRegNo((String) compSub.get(4));
     										  dto.setPoaRegDate((String) compSub.get(5));
     										  dto.setLockStatus((String) compSub.get(6));
     										  dto.setPurpose((String) compSub.get(7));
     										  dto.setDateLocking((String) compSub.get(8));
     										 }
     									  }
     									  }
     								}
     						 //--party details
     						ArrayList partyDetls = new ArrayList();
      						ArrayList comp2 = new ArrayList();
      						partyDetls=lockBD.getPartyDetls(dto.getRegistrationId(),retUnLockId,language);
      						if (partyDetls.size()>0){
      								for(int i =0; i<partyDetls.size(); i++){
      									comp2.add((ArrayList)partyDetls.get(i));
      									  if(comp2 != null && comp2.size()>0){ 
      									  for (int k=0; k< comp2.size(); k++){
      										  ArrayList compSub = (ArrayList)comp2.get(k);
      										  String apptype=(String) compSub.get(0);
      										  dto.setAppType((String) compSub.get(0));
      										  dto.setAppTypeName((String) compSub.get(1));
      										 if(apptype.equalsIgnoreCase("2")){
      											  dto.setFirstName((String) compSub.get(2));
      											  dto.setMidName((String) compSub.get(3));
      											  dto.setLastName((String) compSub.get(4));
      											  //added by shruti
      											 /* if(("F".equalsIgnoreCase((String) compSub.get(5))))
      											{
      												  if("en".equalsIgnoreCase(language))
      												  {
      													dto.setGender("Female");
      												  }
      												  else
      												  {
      													dto.setGender("महिला");
      												  }
      												
      											}
      											if(("M".equalsIgnoreCase((String) compSub.get(5))))
      											{
      												  if("en".equalsIgnoreCase(language))
      												  {
      													dto.setGender("Male");
      												  }
      												  else
      												  {
      													dto.setGender("पुरुष");
      												  }
      												
      											}*/
      											  //dto.setGender((String) compSub.get(5));
      											  dto.setAge((String) compSub.get(6));
      											  dto.setAddress((String) compSub.get(10));
      										      dto.setPhone((String) compSub.get(12));
      										      dto.setMphone((String) compSub.get(13));
      											  dto.setPin((String) compSub.get(11));
      											  dto.setEmail((String) compSub.get(14));
      											  /*dto.setIdProofName((String) compSub.get(15));
      											  dto.setIdProofNo((String) compSub.get(16));*/
      											  dto.setBankName((String) compSub.get(15));
      											  dto.setBankAddress((String) compSub.get(16));
      											  dto.setGuardianName((String) compSub.get(19));
      											  dto.setMotherName((String) compSub.get(20));
      											 }else
      												 if(apptype.equalsIgnoreCase("1")){
      													 dto.setOrgName((String) compSub.get(17));
      													 dto.setAuthName((String) compSub.get(18));
      													 dto.setOrgAddress((String) compSub.get(10));
      													 dto.setOrgPhno((String) compSub.get(12));
      													 dto.setOrgMobno((String) compSub.get(13));
      												 }
      										  dto.setOrgCountryName((String) compSub.get(7));
      										  dto.setOrgStateName((String) compSub.get(8));
      										  dto.setOrgDistrictName((String) compSub.get(9));
      										  dto.setDocumentName((String) compSub.get(21));
      										  dto.setThunmbName((String) compSub.get(22));
      										  dto.setSignatureName((String) compSub.get(23));
      										  dto.setPhotoPath((String) compSub.get(24));
      										  dto.setThumbPath((String) compSub.get(25));
      										  dto.setSignPath((String) compSub.get(26));
      										
      										
      										 }
      									  }
      									  }
      								}ArrayList photoDetls = new ArrayList();
      		 						ArrayList comp6 = new ArrayList();
      		 						photoDetls=lockBD.getPhotoDetl(dto.getPropertyLockid(),language);
      		 						if (photoDetls.size()>0){
      		 							for(int i =0; i<photoDetls.size(); i++){
      		 								comp6.add((ArrayList)photoDetls.get(i));
      		 								  if(comp6 != null && comp6.size()>0){ 
      		 								  for (int k=0; k< comp6.size(); k++){
      		 									  ArrayList compSub = (ArrayList)comp6.get(k);
      		 									  dto.setIdProofName((String) compSub.get(1));
      		 									  dto.setIdProofNo((String) compSub.get(0));
      		 								  }
      		 								  }
      		 								  }
      		 							}
     						formName="";
     						actionName="";
     						dto.setIsLockSave(1);
      						forwardPage = "lockPayView";  
     			           }//end insertion
     		            	 else //failed to insert into DB
     				         {
     				        	    dto.setNotInsertedLock(1);
     				            	forwardPage = "lockFirst";  
     				         }
              		 
              		 
                  	
                   }
                   else if("2".equalsIgnoreCase(st)||"3".equalsIgnoreCase(st)||"4".equalsIgnoreCase(st))
                   {
                 	  dto.setIsAlrdyLocked(1); 
                 	  dto.setAlrdyLockId(refId);
                 	  forwardPage = "lockFirst";                    
                   }
                              
              	 }
              	 }//end of lockSaveP
              
              
              
              }// end of lockMain page
		
		
		
		
		 // start of lockMainView page
		if (formName!=null && formName.equalsIgnoreCase("lockMainView")){
			
			// start of action downloadPhoto
			  if(actionName.equalsIgnoreCase("downloadPhoto")){
				        String filePath=dto.getPhotoPath()+"/"+dto.getDocumentName();
		    			logger.debug("retrieval path of photo-->"+filePath);
		    			DMSUtility dmsUtil=new DMSUtility();
		    			byte contents[] = dmsUtil.getDocumentBytes(filePath);
						 downloadDocument(response, contents, filePath);
		    			//dmsUtil.readImage(filePath);
		    			actionName="";
		    			formName="";
		    			forwardPage="lockFirstView";
					}// end of downloadPhoto
			
			// start of action downloadSign
			  if(actionName.equalsIgnoreCase("downloadSign")){
				    String filePath=dto.getSignPath()+"/"+dto.getSignatureName();
	    			logger.debug("retrieval path of sign-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			byte contents[] = dmsUtil.getDocumentBytes(filePath);
					 downloadDocument(response, contents, filePath);
	    			//dmsUtil.readImage(filePath);
	    			actionName="";
	    			formName="";
	    			forwardPage="lockFirstView";
			  }// end of downloadSign
			  
			// start of action downloadThumb
			  if(actionName.equalsIgnoreCase("downloadThumb")){
				    String filePath=dto.getThumbPath()+"/"+dto.getThunmbName();
	    			logger.debug("retrieval path of thumb-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			byte contents[] = dmsUtil.getDocumentBytes(filePath);
					 downloadDocument(response, contents, filePath);
	    			//dmsUtil.readImage(filePath);
	    			actionName="";
	    			formName="";
	    			forwardPage="lockFirstView"; 
			  }// end of downloadThumb
			
			//added by shruti---7 june 2014
			  if(request.getParameter("actionName")!=null)
			  {
				  actionName=request.getParameter("actionName");
				  if(("downloadPhoto1").equalsIgnoreCase(actionName)){
				        String filePath=dto.getPhotoPath()+"/"+dto.getDocumentName();
		    			logger.debug("retrieval path of photo-->"+filePath);
		    			DMSUtility dmsUtil=new DMSUtility();
		    			byte contents[] = dmsUtil.getDocumentBytes(filePath);
						downloadDocument(response, contents, filePath);
		    			actionName="";
		    			formName="";
		    			forwardPage="lockFirstView";
					}// end of downloadPhoto
				  if(("downloadSign1").equalsIgnoreCase(actionName)){
						String filePath=dto.getSignPath()+"/"+dto.getSignatureName();
			  			logger.debug("retrieval path of sign-->"+filePath);
			  			DMSUtility dmsUtil=new DMSUtility();
			  			byte contents[] = dmsUtil.getDocumentBytes(filePath);
					    downloadDocument(response, contents, filePath);
			  			actionName="";
			  			formName="";
			  			forwardPage="lockFirstView";
					}// end of downloadPhoto
				  if(("downloadThumb1").equalsIgnoreCase(actionName)){
					  String filePath=dto.getThumbPath()+"/"+dto.getThunmbName();
			  			logger.debug("retrieval path of thumb-->"+filePath);
			  			DMSUtility dmsUtil=new DMSUtility();
			  			byte contents[] = dmsUtil.getDocumentBytes(filePath);
					    downloadDocument(response, contents, filePath);
			  			actionName="";
			  			formName="";
			  			forwardPage="lockFirstView"; 
					}// end of downloadPhoto
			  }	
		}// end of form lockMainView
		
		
		//start of lockMainViewP
		if (formName!=null && formName.equalsIgnoreCase("lockMainViewP")){
			
			// start of action downloadPhoto
			  if(actionName.equalsIgnoreCase("downloadPhoto")){
				        String filePath=dto.getPhotoPath()+"/"+dto.getDocumentName();
		    			logger.debug("retrieval path of photo-->"+filePath);
		    			DMSUtility dmsUtil=new DMSUtility();
		    			dmsUtil.readImage(filePath);
		    			actionName="";
		    			formName="";
		    			forwardPage="lockPayView";
					}// end of downloadPhoto
			
			// start of action downloadSign
			  if(actionName.equalsIgnoreCase("downloadSign")){
				    String filePath=dto.getSignPath()+"/"+dto.getSignatureName();
	    			logger.debug("retrieval path of sign-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			dmsUtil.readImage(filePath);
	    			actionName="";
	    			formName="";
	    			forwardPage="lockPayView";
			  }// end of downloadSign
			  
			// start of action downloadThumb
			  if(actionName.equalsIgnoreCase("downloadThumb")){
				    //String filePath=dto.getThumbPath()+"/"+dto.getThunmbName();
				  String filePath=dto.getThumbPath();
	    			logger.debug("retrieval path of thumb-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			dmsUtil.readImage(filePath);
	    			actionName="";
	    			formName="";
	    			forwardPage="lockPayView"; 
			  }// end of downloadThumb
			
			// start of action NEXT_TO_PAYMENT_ACTION
			  if(actionName.equalsIgnoreCase("NEXT_TO_PAYMENT_ACTION")){
				  
					DecimalFormat myformatter=new DecimalFormat("############");    
					String lockId=dto.getPropertyLockid();
					String payableAmt = dto.getPayableFee();
					userId = (String)session.getAttribute("UserId");
					dto.setNotInsertedP(0);
					dto.setIsPartial(0);
					
					String officeName="";
					String districtId="";
					String districtName="";
					officeId = (String)session.getAttribute("loggedToOffice");
					ArrayList alist = lockBD.getrequestDetails(officeId);
					if(alist.size()>0){
					ArrayList rowList = (ArrayList)alist.get(0);
					officeName=(String)rowList.get(0);
					districtId=(String)rowList.get(1);
					districtName=(String)rowList.get(2);
					}
					logger.debug("Inside payment method----the lock id is-->>"+lockId);
					
					ArrayList first = new ArrayList();
					first = lockBD.getPayDtls(lockId);
					if (first.size()==0){
						logger.debug("inside if");
						boolean insrt = false;
						try{
							String id = lockBD.getId();
							insrt = lockBD.insertPay(lockId,payableAmt,userId,id, "FUN_017");
						
						     if(insrt){
						    logger.debug("inside if insrt");
							
							request.setAttribute("parentModName", "Property Locking");
							request.setAttribute("parentFunName", "Property Locking");
							request.setAttribute("parentFunId", "FUN_017");
							request.setAttribute("parentAmount", payableAmt);
							request.setAttribute("parentTable", "IGRS_PROP_LOCK_PAYMENT_DETLS");
							request.setAttribute("parentKey", id);
							request.setAttribute("forwardPath", "./lockAction.do?PropLock=lock&TRFS=NGI");
							request.setAttribute("parentColumnName", "PAYMENT_DETL_ID");
							request.setAttribute("parentOfficeId", officeId);
							request.setAttribute("parentOfficeName", officeName);
							request.setAttribute("parentDistrictId", districtId);
							request.setAttribute("parentDistrictName", districtName);
							request.setAttribute("parentReferenceId", lockId);
							request.setAttribute("formName", "lockForm");
							logger.debug("just before redirection");
							formName="";
							actionName="";
							forwardPage = "nextToPay";
							
							}
						     else{
						    	 dto.setNotInsertedP(1);
						    	 forwardPage = "lockPayView";
						          }
						}catch(Exception e){
							dto.setNotInsertedP(1);
							forwardPage = "lockPayView";
						}
						}
					// if pay details are not empty
					else{
							logger.debug("inside else---means record is found");
							ArrayList comp = new ArrayList();
							String uniqId="";
						    String amtToBePaid="";
						    String paidAmt="";
						    String pymtFlg="";
							
							for(int i =0; i<first.size(); i++){
								comp.add((ArrayList)first.get(i));
								  if(comp != null && comp.size()>0){ 
								  for (int k=0; k< comp.size(); k++){
									  ArrayList compSub = (ArrayList)comp.get(k);
									  uniqId =      (String) compSub.get(0);
									  amtToBePaid = (String) compSub.get(1);
									  paidAmt =     (String) compSub.get(2);
									  pymtFlg =     (String) compSub.get(3);
									    logger.debug("unique id is ............................"+uniqId);
										logger.debug("unpayable amt is ............................"+amtToBePaid);
										logger.debug("paid amt is..................................."+paidAmt);
										logger.debug("pymtFlg is....................................."+pymtFlg);
									 }
								  }
								  }
							double damtToBePaid = Double.parseDouble(amtToBePaid);
							
							//------- if payment flag is I ----
							if(pymtFlg.equalsIgnoreCase("I")){
								
								request.setAttribute("parentModName", "Property Locking");
								request.setAttribute("parentFunName", "Property Locking");
								request.setAttribute("parentFunId", "FUN_017");
								request.setAttribute("parentAmount", amtToBePaid);
								request.setAttribute("parentTable", "IGRS_PROP_LOCK_PAYMENT_DETLS");
								request.setAttribute("parentKey", uniqId);
								request.setAttribute("forwardPath", "./lockAction.do?PropLock=lock&TRFS=NGI");
								request.setAttribute("parentColumnName", "PAYMENT_DETL_ID");
								request.setAttribute("parentOfficeId", officeId);
								request.setAttribute("parentOfficeName", officeName);
								request.setAttribute("parentDistrictId", districtId);
								request.setAttribute("parentDistrictName", districtName);
								request.setAttribute("parentReferenceId", lockId);
								request.setAttribute("formName", "lockForm");
								formName="";
								actionName="";						
								forwardPage = "nextToPay";
							}
							//----------- if payment flag is P -----
							
							if(pymtFlg.equalsIgnoreCase("P")){
								
								double dpaidAmt = Double.parseDouble(paidAmt);
								//condition 1-------------------pending amount-------------
								if (damtToBePaid>dpaidAmt){
									double bal= damtToBePaid-dpaidAmt;
									String balfrmt = myformatter.format(bal);
									dto.setPayableFee(balfrmt);
									boolean insrt = false;
									
									try{
										String id = lockBD.getId();
										insrt = lockBD.insertPay(lockId,balfrmt,userId,id,"FUN_017");
									
									 if(insrt){
								     logger.debug("inside if insrt-----of else condition---means record found");
								        request.setAttribute("parentModName", "Property Locking");
										request.setAttribute("parentFunName", "Property Locking");
										request.setAttribute("parentFunId", "FUN_017");
										request.setAttribute("parentAmount", balfrmt);
										request.setAttribute("parentTable", "IGRS_PROP_LOCK_PAYMENT_DETLS");
										request.setAttribute("parentKey", id);
										request.setAttribute("forwardPath", "./lockAction.do?PropLock=lock&TRFS=NGI");
										request.setAttribute("parentColumnName", "PAYMENT_DETL_ID");
										request.setAttribute("parentOfficeId", officeId);
										request.setAttribute("parentOfficeName", officeName);
										request.setAttribute("parentDistrictId", districtId);
										request.setAttribute("parentDistrictName", districtName);
										request.setAttribute("parentReferenceId", lockId);
										request.setAttribute("formName", "lockForm");
										formName="";
										actionName="";						
										forwardPage = "nextToPay";
								     
									}
									  else{
									    	 dto.setNotInsertedP(1);
									    	 forwardPage = "lockPayView";
									          }
									}catch(Exception e){
										dto.setNotInsertedP(1);
										forwardPage = "lockPayView";
									}
									
								}
								}
					}// end of pay details not empty
					
			  }// end of NEXT_TO_PAYMENT_ACTION
			
			
		}// end of form lockMainViewP
		
		// start of form releaseSearchForm
		if (formName!=null && formName.equalsIgnoreCase("releaseSearchForm")){
			
			
			// start of action getLockDetlsForRel
			if(actionName.equalsIgnoreCase("getLockDetlsForRel")){
			dto.setLockReleaseDetail("");
        	dto.setLockReleaseDetailsAction("");
        	String referId =  dto.getPropertyLockid();              
            String fromDate = dto.getFromRequestDate();                
            String toDate =   dto.getToRequestDate();
            String che="release";
            dto.setIsRegDetlEmpty(0);
            dto.setLockDetailsRelSearch(new ArrayList());
		    ArrayList lockDetailsRelSearch = new ArrayList();
			try{
				   lockDetailsRelSearch= lockBD.getTransDetlR1(referId,fromDate,toDate,language);
					if(lockDetailsRelSearch.size()>0){
						dto.setLockDetailsRelSearch(lockDetailsRelSearch);
						request.setAttribute("lockDetailsRelSearch", lockDetailsRelSearch);
						formName="";
				        actionName="";
						forwardPage = "releaseSearch";
						
					}else{
						dto.setIsRegDetlEmpty(1);
						formName="";
				        actionName="";
						forwardPage = "releaseSearch";
					   }
				
		    
			}catch(Exception e){
			logger.debug("inside popup page1, reg detl is empty");
			dto.setIsRegDetlEmpty(1);
			formName="";
	        actionName="";
			forwardPage = "releaseSearch";
		}
			formName="";
			actionName="";						
		}// end of action getLockDetlsForRel
			 
	   // start of resetRelSearch
			 if(actionName.equalsIgnoreCase("resetRelSearch")){
				 dto.setPropertyLockid("");
				 dto.setFromRequestDate("");
				 dto.setToRequestDate("");
				 dto.setIsRegDetlEmpty(0);
				 dto.setLockDetailsRelSearch(new ArrayList());
				 formName="";
				 actionName="";
				 forwardPage = "releaseSearch";
			 }//end of resetRelSearch
			 
		}//end of form releaseSearchForm
		
		
		
		
		// start of lockRelease1 form
		if (formName!=null && formName.equalsIgnoreCase("lockRelease1")){
			
			 //added by shruti--12th nov 2013
	        String uploadChk=(String)request.getParameter("uploadSign");
	        if(uploadChk!=null)
	        {
	      	  if(uploadChk.equals("true"))
	      	  {
	      		String completePathFP = dto.getParentPathSign() +"/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"Sign"+ "/"+ dto.getGuidUpload();
	      		dto.setRelSignPath(completePathFP);
	      		dto.setRelSignName(dto.getFileNameSign());
					logger.debug("UploadSignRead completePathFP : " + completePathFP);
					logger.debug("UploadSignRead dto.getFileNameFP() : " + dto.getFileNameSign());
					formName = "";
					actionName = "";
					forwardPage = "releaseFirst";
	      	  }
	        }
	        
	        //end
	        
	        if(request.getParameter("actionName")!=null)
	        {
	        	if("downloadRelPhoto".equalsIgnoreCase(actionName))
	        	{
	        		String filePath=dto.getRelPhotoPath()+"/"+dto.getRelPhotoName();
	    			logger.debug("retrieval path of photo-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			byte[] docContents=dmsUtil.getDocumentBytes(filePath);
	    			dmsUtil.downloadDocument(response, filePath, docContents);
	    			actionName="";
	    			formName="";
	    			forwardPage = "releaseFirst";
	        	}
	        	if("downloadRelSign".equalsIgnoreCase(actionName))
    			{
	        		String filePath=dto.getRelSignPath()+"/"+dto.getRelSignName();
	    			logger.debug("retrieval path of photo-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			byte[] docContents=dmsUtil.getDocumentBytes(filePath);
	    			dmsUtil.downloadDocument(response, filePath, docContents);
	    			actionName="";
	    			formName="";
	    			forwardPage = "releaseFirst";
    			}
	        	if("downloadRelThumb".equalsIgnoreCase(actionName))
    			{
	        		String filePath=dto.getRelThumbPath()+"/"+dto.getRelThumbName();
	    			logger.debug("retrieval path of photo-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			byte[] docContents=dmsUtil.getDocumentBytes(filePath);
	    			dmsUtil.downloadDocument(response, filePath, docContents);
	    			actionName="";
	    			formName="";
	    			forwardPage = "releaseFirst";
    			}
	        	if("downloadDeathCer".equalsIgnoreCase(actionName))
    			{
	        		String filePath=dto.getRelDeathCrtPath()+"/"+dto.getRelDeathCerName();
	    			logger.debug("retrieval path of photo-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			byte[] docContents=dmsUtil.getDocumentBytes(filePath);
	    			dmsUtil.downloadDocument(response, filePath, docContents);
	    			actionName="";
	    			formName="";
	    			forwardPage = "releaseFirst";
    			}
	        	
	        }
	        
			// start of action downloadPhoto
			  if(actionName.equalsIgnoreCase("downloadPhoto")){
				        String filePath=dto.getPhotoPath()+"/"+dto.getDocumentName();
		    			logger.debug("retrieval path of photo-->"+filePath);
		    			DMSUtility dmsUtil=new DMSUtility();
		    			byte[] docContents=dmsUtil.getDocumentBytes(filePath);
		    			dmsUtil.downloadDocument(response, filePath, docContents);
		    			actionName="";
		    			formName="";
		    			forwardPage = "releaseFirst";
					}// end of downloadPhoto
			
			// start of action downloadSign
			  if(actionName.equalsIgnoreCase("downloadSign")){
				    String filePath=dto.getSignPath()+"/"+dto.getSignatureName();
	    			logger.debug("retrieval path of sign-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			byte[] docContents=dmsUtil.getDocumentBytes(filePath);
	    			dmsUtil.downloadDocument(response, filePath, docContents);
	    			actionName="";
	    			formName="";
	    			forwardPage = "releaseFirst";
			  }// end of downloadSign
			  
			// start of action downloadThumb
			  if(actionName.equalsIgnoreCase("downloadThumb")){
				    String filePath=dto.getThumbPath()+"/"+dto.getThunmbName();
	    			logger.debug("retrieval path of thumb-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			byte[] docContents=dmsUtil.getDocumentBytes(filePath);
	    			dmsUtil.downloadDocument(response, filePath, docContents);
	    			actionName="";
	    			formName="";
	    			forwardPage = "releaseFirst";
			  }// end of downloadThumb		
			// start of action stateload
				if(actionName.equalsIgnoreCase("stateload")){
					
					String countryId = dto.getRcountryId();
					if (!countryId.equals(null) || countryId != null|| countryId != "") 
					{
						dto.setRstateList(lockBD.getState(countryId,language));
					} 
					
					actionName="";
	    			formName="";
					forwardPage="releaseFirst";
	                 }// end of action state load
				
				
				// start of action districtLoad
	              if(actionName.equalsIgnoreCase("districtLoad")){
					
					String stateid = dto.getRstateId();
					if (!stateid.equals(null) || stateid != null|| stateid != "") 
					{
						dto.setRdistrictList(lockBD.getDistrict(stateid,language));
					} 
					
					actionName="";
	    			formName="";
					forwardPage="releaseFirst";
	                 }// end of action districtLoad
	              // start of action resetRel1
	              if(actionName.equalsIgnoreCase("resetRel1")){
	            	     dto.setPersonType("");
	            	     dto.setScriptRelType("");
	            	     dto.setReleaserType("");
						 dto.setReleaserName("");
						 dto.setRelation("");
						 dto.setRelationId("");
						 dto.setRcountryId("");
						 dto.setRcountryName("");
						 dto.setRstate("");
						 dto.setRstateId("");
						 dto.setRstateName("");
						 dto.setRdistrictId("");
						 dto.setRdistrictName("");
						 dto.setRelFatherName("");
						 dto.setRelMotherName("");
						 dto.setRelphone("");
						 dto.setRelMobNo("");
						 dto.setRelEmail("");
						 dto.setRelAddress("");
						 dto.setRelAttachDoc(null);
						 dto.setRelPhotoName("");
						 dto.setRelPhotoPath("");
						 dto.setRelAttachthumb(null);
						 dto.setRelThumbName("");
						 dto.setRelThumbPath("");
						 dto.setRelAttaSig(null);
						 dto.setRelSignName("");
						 dto.setRelSignPath("");
						 dto.setRelDeathCerAttach(null);
						 dto.setRelDeathCerName("");
						 dto.setRelDeathCrtPath("");
						 dto.setReasonForRelease("");
						 dto.setRemarksForRelease("");
						 actionName="";
			    		 formName="";
						 forwardPage="releaseFirst";
	              }// end of action resetRel1
	              
	              // start of action radioChk
	             if(actionName.equalsIgnoreCase("radioChk")){
						String relType= dto.getPersonType();
						if(relType.equalsIgnoreCase("Applicant")||relType.equalsIgnoreCase("आवेदक")){
							 dto.setReleaserType("1");
							 dto.setScriptRelType("1");
							 dto.setReleaserName("");
							 dto.setRelation("");
							 dto.setRelationId("");
							 dto.setRcountryId("");
							 dto.setRcountryName("");
							 dto.setRstate("");
							 dto.setRstateId("");
							 dto.setRstateName("");
							 dto.setRdistrictId("");
							 dto.setRdistrictName("");
							 dto.setRelFatherName("");
							 dto.setRelMotherName("");
							 dto.setRelphone("");
							 dto.setRelMobNo("");
							 dto.setRelEmail("");
							 dto.setRelAddress("");
							 dto.setRelAttachDoc(null);
							 dto.setRelPhotoName("");
							 dto.setRelPhotoPath("");
							 dto.setRelAttachthumb(null);
							 dto.setRelThumbName("");
							 dto.setRelThumbPath("");
							 dto.setRelAttaSig(null);
							 dto.setRelSignName("");
							 dto.setRelSignPath("");
							 dto.setRelDeathCerAttach(null);
							 dto.setRelDeathCerName("");
							 dto.setRelDeathCrtPath("");
							}
						
                         if(relType.equalsIgnoreCase("Relative")||relType.equalsIgnoreCase("संबंधी")){
                        	 dto.setReleaserType("2");
                        	 dto.setScriptRelType("2");
                        	 dto.setReleaserName("");
            				 dto.setRelation("");
            				 dto.setRelationId("");
            				 dto.setRcountryId("");
            				 dto.setRcountryName("");
            				 dto.setRstate("");
            				 dto.setRstateId("");
            				 dto.setRstateName("");
            				 dto.setRdistrictId("");
            				 dto.setRdistrictName("");
            				 dto.setRelFatherName("");
            				 dto.setRelMotherName("");
            				 dto.setRelphone("");
            				 dto.setRelMobNo("");
            				 dto.setRelEmail("");
            				 dto.setRelAddress("");
            				 dto.setRelAttachDoc(null);
            				 dto.setRelPhotoName("");
            				 dto.setRelPhotoPath("");
            				 dto.setRelAttachthumb(null);
            				 dto.setRelThumbName("");
            				 dto.setRelThumbPath("");
            				 dto.setRelAttaSig(null);
            				 dto.setRelSignName("");
            				 dto.setRelSignPath("");
            				 dto.setRelDeathCerAttach(null);
            				 dto.setRelDeathCerName("");
            				 dto.setRelDeathCrtPath("");
						}
						
						actionName="";
		    			formName="";
						forwardPage="releaseFirst";
		                 }
	              
	              //end of action radioChk
	              
	             //added by shruti--12th nov 2013
	              if(actionName.equalsIgnoreCase("retrieveUploadPhoto")){
	            	  //commented by shruti--as discussed with Anuj
	            	//added by shruti--12 th nov 2013
	  				/*dto.setGuidUpload(GUIDGenerator.getGUID());
	  				dto.setParentPathFP("D:/Upload/11/Release/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"ThumbPrint");
	  				dto.setFileNameFP("FingerPrint.BMP");
	  				dto.setParentPathScan("D:/Upload/11/Release/Scan");
	  				dto.setFileNameScan("CapturedDocument.PDF");
	  				dto.setParentPathSign("D:/Upload/11/Release/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"Sign");
	  				dto.setFileNameSign("CapturedScan.GIF");
	  				dto.setForwardPath("/lockAction.do?uploadSign=true");
	  				dto.setForwardName("releaseFirst");
	  				dto.setPhotoName("Photo.GIF");
	  				dto.setWebcameraPath("D:/Upload/11/Release/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"Photo");
	  				//end
*/	          	  	String completePathFP = dto.getWebcameraPath() + "/"+ dto.getGuidUpload();
	          	  		dto.setRelPhotoPath(completePathFP);
						dto.setRelPhotoName(dto.getPhotoName());
						logger.debug("PhotoUploadRead completePathFP : " + completePathFP);
						logger.debug("PhotoUploadRead dto.getFileNameFP() : " + dto.getPhotoName());
						formName = "";
						actionName = "";
						eForm.setActionName("");
						forwardPage = "releaseFirst";
	            }
	              //end
	              
	              
	              // start of action setUploadPhoto
	              if(actionName.equalsIgnoreCase("setUploadPhoto")){
	              
	            	  ArrayList	errorList = new ArrayList();
					  FormFile uploadedFile = dto.getRelAttachDoc();
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
							request.setAttribute("errorsList",errorList);
						} else {
							if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
								errorList.add("File size is Greater than 10 MB. Please select another file.");
								request.setAttribute("errorsList",errorList);
							} else {
								  Date date = new Date();
								  Format yearformat  = new SimpleDateFormat("yy");
								  Format monthformat = new SimpleDateFormat("MM");
								  Format dateformat  = new SimpleDateFormat("dd");
								  Format hourformat  = new SimpleDateFormat("hh");
								  Format minformat  = new SimpleDateFormat("mm");
								  Format secformat  = new SimpleDateFormat("ss");
								  Format ampmformat  = new SimpleDateFormat("a");
								  String dfmt = dateformat.format(date);
								  String yfmt = yearformat.format(date);
								  String mfmt = monthformat.format(date);
								  String hfmt = hourformat.format(date);
								  String mifmt = minformat.format(date);
								  String sfmt = secformat.format(date);
								  String ampmfmt = ampmformat.format(date);
								  String fldrnm = dfmt+mfmt+yfmt+hfmt+mifmt+sfmt+ampmfmt;
								  logger.debug("the new date in concatenated format in Photo upload is----------------------- >"+fldrnm);
								String docName = uploadedFile.getFileName();
								String docPath = pr.getValue("igrs_upload_path")+"/11/Release/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/Photo/"+fldrnm;
								boolean up=uploadFile(dto.getRelAttachDoc(),docName,docPath);
								dto.setRelPhotoName(uploadedFile.getFileName());
								dto.setRelPhotoPath(docPath);
								dto.setRelPhotoContents(uploadedFile.getFileData());
								dto.setRelPhotoSize(photoSize);
								dto.setRelPhotoCheck("phloded");
								
								forwardPage="releaseFirst";
								} 
							}
						
						formName="";
						actionName="";
						forwardPage="releaseFirst";
						}// end of action setUploadPhoto
	              
	              
	            //added by shruti--12th nov 2013
	              if(actionName.equalsIgnoreCase("retrieveUploadThumb")){
	            	  //commented by shruti--as discussed with Anuj
	            	//added by shruti--12 th nov 2013
	  				/*dto.setGuidUpload(GUIDGenerator.getGUID());
	  				dto.setParentPathFP("D:/Upload/11/Release/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"ThumbPrint");
	  				dto.setFileNameFP("FingerPrint.BMP");
	  				dto.setParentPathScan("D:/Upload/11/Release/Scan");
	  				dto.setFileNameScan("CapturedDocument.PDF");
	  				dto.setParentPathSign("D:/Upload/11/Release/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"Sign");
	  				dto.setFileNameSign("CapturedScan.GIF");
	  				dto.setForwardPath("/lockAction.do?uploadSign=true");
	  				dto.setForwardName("releaseFirst");
	  				dto.setPhotoName("Photo.GIF");
	  				dto.setWebcameraPath("D:/Upload/11/Release/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"Photo");
	  				//end
*/	          	  	String completePathFP = dto.getWebcameraPath() + "/"+ dto.getGuidUpload();
	          	  		dto.setRelThumbPath(completePathFP);
						dto.setRelThumbName(dto.getFileNameFP());
						logger.debug("PhotoUploadRead completePathFP : " + completePathFP);
						logger.debug("PhotoUploadRead dto.getFileNameFP() : " + dto.getPhotoName());
						formName = "";
						actionName = "";
						eForm.setActionName("");
						forwardPage = "releaseFirst";
	            }
	              //end
	              
	           // start of action setUploadThumb
	              if(actionName.equalsIgnoreCase("setUploadThumb")){
	              
	            	  ArrayList	errorList = new ArrayList();
					  FormFile uploadedFile = dto.getRelAttachthumb();
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
						String thumbSize = "(" + fileSize + "MB)";
						if (rule.isError()) {
							errorList.add("Invalid file type. Please select another file.");
							request.setAttribute("errorsList",errorList);
						} else {
							if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
								errorList.add("File size is Greater than 10 MB. Please select another file.");
								request.setAttribute("errorsList",errorList);
							} else {
								 Date date = new Date();
								  Format yearformat  = new SimpleDateFormat("yy");
								  Format monthformat = new SimpleDateFormat("MM");
								  Format dateformat  = new SimpleDateFormat("dd");
								  Format hourformat  = new SimpleDateFormat("hh");
								  Format minformat  = new SimpleDateFormat("mm");
								  Format secformat  = new SimpleDateFormat("ss");
								  Format ampmformat  = new SimpleDateFormat("a");
								  String dfmt = dateformat.format(date);
								  String yfmt = yearformat.format(date);
								  String mfmt = monthformat.format(date);
								  String hfmt = hourformat.format(date);
								  String mifmt = minformat.format(date);
								  String sfmt = secformat.format(date);
								  String ampmfmt = ampmformat.format(date);
								  String fldrnm = dfmt+mfmt+yfmt+hfmt+mifmt+sfmt+ampmfmt;
								logger.debug("the new date in upload thumb is----------------------- >"+fldrnm);
								String thumbName = uploadedFile.getFileName();
								String thumbPath = pr.getValue("igrs_upload_path")+"/11/Release/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/Thumb/"+fldrnm;
								boolean up=uploadFile(dto.getRelAttachthumb(),thumbName,thumbPath);
								
								dto.setRelThumbName(uploadedFile.getFileName());
								dto.setRelThumbPath(thumbPath);
								dto.setRelThumbContents(uploadedFile.getFileData());							
								dto.setRelThumbSize(thumbSize);
								dto.setRelThumbCheck("thloded");
								
								forwardPage="releaseFirst";
								} 
							}
						
						formName="";
						actionName="";
						forwardPage="releaseFirst";
						}// end of action setUploadThumb
	              
	              
	              
	              
	              
	           // start of action setUploadSign
	              if(actionName.equalsIgnoreCase("setUploadSign")){
	                  ArrayList	errorList = new ArrayList();
					  FormFile uploadedFile = dto.getRelAttaSig();
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
						String signSize = "(" + fileSize + "MB)";
						if (rule.isError()) {
							errorList.add("Invalid file type. Please select another file.");
							request.setAttribute("errorsList",errorList);
						} else {
							if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
								errorList.add("File size is Greater than 10 MB. Please select another file.");
								request.setAttribute("errorsList",errorList);
							} else {
								Date date = new Date();
								  Format yearformat  = new SimpleDateFormat("yy");
								  Format monthformat = new SimpleDateFormat("MM");
								  Format dateformat  = new SimpleDateFormat("dd");
								  Format hourformat  = new SimpleDateFormat("hh");
								  Format minformat  = new SimpleDateFormat("mm");
								  Format secformat  = new SimpleDateFormat("ss");
								  Format ampmformat  = new SimpleDateFormat("a");
								  String dfmt = dateformat.format(date);
								  String yfmt = yearformat.format(date);
								  String mfmt = monthformat.format(date);
								  String hfmt = hourformat.format(date);
								  String mifmt = minformat.format(date);
								  String sfmt = secformat.format(date);
								  String ampmfmt = ampmformat.format(date);
								  String fldrnm = dfmt+mfmt+yfmt+hfmt+mifmt+sfmt+ampmfmt;
								logger.debug("the new date in signature upload is----------------------- >"+fldrnm);
								String signName = uploadedFile.getFileName();
								String signPath = pr.getValue("igrs_upload_path")+"/11/Release/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/Signature/"+fldrnm;
								boolean up=uploadFile(dto.getRelAttaSig(),signName,signPath);
								
								
								dto.setRelSignName(uploadedFile.getFileName());
								dto.setRelSignPath(signPath);
								dto.setRelSignContents(uploadedFile.getFileData());
								dto.setRelSigSize(signSize);
								dto.setRelSignCheck("signloded");
								forwardPage="releaseFirst";
								} 
							}
						
						formName="";
						actionName="";
						forwardPage="releaseFirst";
	            	 }// end of action setUploadSign
	              
	              // start of action setUploadDeathCrt
	              if(actionName.equalsIgnoreCase("setUploadDeathCrt")){
	                  ArrayList	errorList = new ArrayList();
					  FormFile uploadedFile = dto.getRelDeathCerAttach();
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
						String deathCertSize = "(" + fileSize + "MB)";
						if (rule.isError()) {
							errorList.add("Invalid file type. Please select another file.");
							request.setAttribute("errorsList",errorList);
						} else {
							if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
								errorList.add("File size is Greater than 10 MB. Please select another file.");
								request.setAttribute("errorsList",errorList);
							} else {
								Date date = new Date();
								  Format yearformat  = new SimpleDateFormat("yy");
								  Format monthformat = new SimpleDateFormat("MM");
								  Format dateformat  = new SimpleDateFormat("dd");
								  Format hourformat  = new SimpleDateFormat("hh");
								  Format minformat  = new SimpleDateFormat("mm");
								  Format secformat  = new SimpleDateFormat("ss");
								  Format ampmformat  = new SimpleDateFormat("a");
								  String dfmt = dateformat.format(date);
								  String yfmt = yearformat.format(date);
								  String mfmt = monthformat.format(date);
								  String hfmt = hourformat.format(date);
								  String mifmt = minformat.format(date);
								  String sfmt = secformat.format(date);
								  String ampmfmt = ampmformat.format(date);
								  String fldrnm = dfmt+mfmt+yfmt+hfmt+mifmt+sfmt+ampmfmt;
								logger.debug("the new date in signature upload is----------------------- >"+fldrnm);
								String deathcerName = uploadedFile.getFileName();
								String deathCrPath = pr.getValue("igrs_upload_path")+"/11/Release/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/DeathCert/"+fldrnm;
								boolean up=uploadFile(dto.getRelDeathCerAttach(),deathcerName,deathCrPath);
								
															
								dto.setRelDeathCerName(uploadedFile.getFileName());
								dto.setRelDeathCrtPath(deathCrPath);
								dto.setRelDeathCerContents(uploadedFile.getFileData());
								dto.setRelDeathCerSize(deathCertSize);
								dto.setRelDeathCerCheck("dcerloded");
								forwardPage="releaseFirst";
								} 
							}
						
						formName="";
						actionName="";
						forwardPage="releaseFirst";
	            	 }// end of action setUploadDeathCrt
			
	              //added by shruti--12th nov 2013
	              if(actionName.equalsIgnoreCase("retrieveUploadDeathCrt")){
	            	  //commenetd by shruti--as discussed with anuj
	            	/*//added by shruti--12 th nov 2013
	  				dto.setGuidUpload(GUIDGenerator.getGUID());
	  				dto.setParentPathFP("D:/Upload/11/Release/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"ThumbPrint");
	  				dto.setFileNameFP("FingerPrint.BMP");
	  				dto.setParentPathScan("D:/Upload/11/Release/Scan");
	  				dto.setFileNameScan("CapturedDocument.PDF");
	  				dto.setParentPathSign("D:/Upload/11/Release/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"Sign");
	  				dto.setFileNameSign("CapturedScan.GIF");
	  				dto.setForwardPath("/lockAction.do?uploadSign=true");
	  				dto.setForwardName("releaseFirst");
	  				dto.setPhotoName("Photo.GIF");
	  				dto.setWebcameraPath("D:/Upload/11/Release/"+dto.getRegistrationId()+"/"+dto.getPropId()+"/"+"Photo");
	  				//end
*/	          	  	String completePathFP = dto.getWebcameraPath() + "/"+ dto.getGuidUpload();
	          	  		dto.setRelDeathCrtPath(completePathFP);
						dto.setRelDeathCerName(dto.getFileNameScan());
						logger.debug("PhotoUploadRead completePathFP : " + completePathFP);
						logger.debug("PhotoUploadRead dto.getFileNameFP() : " + dto.getRelDeathCerName());
						formName = "";
						actionName = "";
						eForm.setActionName("");
						forwardPage = "releaseFirst";
	            }
	              //end
	              
			//start of action PropRelease
	              if(actionName.equalsIgnoreCase("PropRelease")){
 
	             	 dto.setPropertyLockDisplayAction("");
	              	 dto.setPropertyLockDisplayAction("");
	              	 dto.setPaymentSuccessAction("");
	              	 
	              	 ArrayList lockinfo=new ArrayList();
	              	 ArrayList comp7 = new ArrayList();
	              	 String st="";
	              	 String refId="";
	              	 lockinfo = lockBD.getLockStatus(dto.getRegistrationId(), dto.getPropId()); 
	              	 
	              	 if(lockinfo.size()>0){
	              		 
	              		 for(int i=0; i<lockinfo.size(); i++){
	              			comp7.add((ArrayList)lockinfo.get(i));
	 						  if(comp7 != null && comp7.size()>0){ 
	 						  for (int k=0; k< comp7.size(); k++){
	 							  ArrayList compSub = (ArrayList)comp7.get(k);
	 							  st = (String) compSub.get(0);
	 							  refId = (String) compSub.get(1);
	 							 }
	 						  } 
	              		 }
	              		 
	              		 
	              	 if("2".equalsIgnoreCase(st))
	                   {
	              			 
	              		     String releaserType = dto.getPersonType();
	              		     String retUnLockId ="";  
	              		     if(releaserType.equalsIgnoreCase("Applicant")||releaserType.equalsIgnoreCase("आवेदक")){
	     	                 retUnLockId = lockBD.insertReleaseDetls(dto,userId,officeId );  
	              		     }
	              		   if(releaserType.equalsIgnoreCase("Relative")||releaserType.equalsIgnoreCase("संबंधी")){
		     	                 retUnLockId = lockBD.insertReleaseDetlsR(dto,userId,officeId );  
		              		     }
	     	            	 if(!retUnLockId.equalsIgnoreCase(""))
	     			         {
	     			        	dto.setPropertyReleaseId(retUnLockId);
	     			        	// transaction details---
	     			        	ArrayList txnDetls = new ArrayList();
	     						ArrayList comp = new ArrayList();
	     						txnDetls=lockBD.getTxnDetlsR(dto.getRegistrationId(), dto.getPropId(),retUnLockId,language);
	     						 if (txnDetls.size()>0){
	     								for(int i =0; i<txnDetls.size(); i++){
	     									comp.add((ArrayList)txnDetls.get(i));
	     									  if(comp != null && comp.size()>0){ 
	     									  for (int k=0; k< comp.size(); k++){
	     										  ArrayList compSub = (ArrayList)comp.get(k);
	     										  dto.setPropertyReleaseId((String) compSub.get(0));
	     										  dto.setRegistrationId((String) compSub.get(1));
	     										  dto.setRegistrationDate((String) compSub.get(2));
	     										  String prId = (String) compSub.get(3);
	 											  if (prId.length()==15){
	 												  prId = "0"+prId;
	 											  }
	 											  dto.setPropId(prId);
	     										  dto.setPoaRegNo((String) compSub.get(4));
	     										  dto.setPoaRegDate((String) compSub.get(5));
	     										  dto.setLockStatus((String) compSub.get(6));
	     										  dto.setReasonForRelease((String) compSub.get(7));
	     										  dto.setReleaseDate((String) compSub.get(8));
	     										  dto.setRemarksForRelease((String) compSub.get(9));
	     										  dto.setReleaserType((String) compSub.get(10));
	     										 }
	     									  }
	     									  }
	     								}
	     						 //--party details
	     						if(dto.getReleaserType().equalsIgnoreCase("1")){
	     						ArrayList partyDetls = new ArrayList();
	      						ArrayList comp2 = new ArrayList();
	      						partyDetls=lockBD.getPartyDetls(dto.getRegistrationId(),retUnLockId,language);
	      						if (partyDetls.size()>0){
	      								for(int i =0; i<partyDetls.size(); i++){
	      									comp2.add((ArrayList)partyDetls.get(i));
	      									  if(comp2 != null && comp2.size()>0){ 
	      									  for (int k=0; k< comp2.size(); k++){
	      										  ArrayList compSub = (ArrayList)comp2.get(k);
	      										  String apptype=(String) compSub.get(0);
	      										  dto.setAppType((String) compSub.get(0));
	      										  dto.setAppTypeName((String) compSub.get(1));
	      										 if(apptype.equalsIgnoreCase("2")){
	      											  dto.setFirstName((String) compSub.get(2));
	      											  dto.setMidName((String) compSub.get(3));
	      											  dto.setLastName((String) compSub.get(4));
	      											  dto.setGender((String) compSub.get(5));
	      											/*if("M".equalsIgnoreCase(dto.getGender()))
	     											  {
	     												  if("en".equalsIgnoreCase(language))
	     												  {
	     													 dto.setGender("male");
	     												  }
	     												  else
	     												  {
	     													 dto.setGender("पुस्र्ष");
	     												  }
	     												 
	     												 
	     											  }
	     											  else
	     											  {
	     												 if("en".equalsIgnoreCase(language))
	    												  {
	    													 dto.setGender("female");
	    												  }
	    												  else
	    												  {
	    													 dto.setGender("महिला");
	    												  }  
	     											  }*/
	     											  
	      											  dto.setAge((String) compSub.get(6));
	      											  dto.setAddress((String) compSub.get(10));
	      										      dto.setPhone((String) compSub.get(12));
	      										      dto.setMphone((String) compSub.get(13));
	      											  dto.setPin((String) compSub.get(11));
	      											  dto.setEmail((String) compSub.get(14));
	      											  /*dto.setIdProofName((String) compSub.get(15));
	      											  dto.setIdProofNo((String) compSub.get(16));*/
	      											  dto.setBankName((String) compSub.get(15));
	      											  dto.setBankAddress((String) compSub.get(16));
	      											  dto.setGuardianName((String) compSub.get(19));
	      											  dto.setMotherName((String) compSub.get(20));
	      											 }else
	      												 if(apptype.equalsIgnoreCase("1")){
	      													 dto.setOrgName((String) compSub.get(17));
	      													 dto.setAuthName((String) compSub.get(18));
	      													 dto.setOrgAddress((String) compSub.get(10));
	      													 dto.setOrgPhno((String) compSub.get(12));
	      													 dto.setOrgMobno((String) compSub.get(13));
	      												 }
	      										  dto.setOrgCountryName((String) compSub.get(7));
	      										  dto.setOrgStateName((String) compSub.get(8));
	      										  dto.setOrgDistrictName((String) compSub.get(9));
	      										  dto.setDocumentName((String) compSub.get(21));
	      										  dto.setThunmbName((String) compSub.get(22));
	      										  dto.setSignatureName((String) compSub.get(23));
	      										  dto.setPhotoPath((String) compSub.get(24));
	      										  dto.setThumbPath((String) compSub.get(25));
	      										  dto.setSignPath((String) compSub.get(26));
	      										
	      										 }
	      									  }
	      									  }
	      								}
	     			         
	      						        ArrayList photoDetls = new ArrayList();
	      		 						ArrayList comp6 = new ArrayList();
	      		 						photoDetls=lockBD.getPhotoDetl(retUnLockId,language);
	      		 						if (photoDetls.size()>0){
	      		 							for(int i =0; i<photoDetls.size(); i++){
	      		 								comp6.add((ArrayList)photoDetls.get(i));
	      		 								  if(comp6 != null && comp6.size()>0){ 
	      		 								  for (int k=0; k< comp6.size(); k++){
	      		 									  ArrayList compSub = (ArrayList)comp6.get(k);
	      		 									  dto.setIdProofName((String) compSub.get(1));
	      		 									  dto.setIdProofNo((String) compSub.get(0));
	      		 								  }
	      		 								  }
	      		 								  }
	      		 							}
	      		 						if("en".equalsIgnoreCase(language))
	      		 						{
	      		 						dto.setPersonType("Applicant");}
	      		 						else
	      		 						{
	      		 							dto.setPersonType("आवेदक");
	      		 						}
	      		 						}// end of releaser type=1
	     						
	     						if(dto.getReleaserType().equalsIgnoreCase("2")){
	     							ArrayList relativeDetls = new ArrayList();
		      						ArrayList comp2 = new ArrayList();
		      						relativeDetls=lockBD.getRelativeDetls(dto.getRegistrationId(),retUnLockId,language);
		      						if (relativeDetls.size()>0){
		      								for(int i =0; i<relativeDetls.size(); i++){
		      									comp2.add((ArrayList)relativeDetls.get(i));
		      									  if(comp2 != null && comp2.size()>0){ 
		      									  for (int k=0; k< comp2.size(); k++){
		      										  ArrayList compSub = (ArrayList)comp2.get(k);
		      										  
		      										 dto.setReleaserName((String) compSub.get(0));
		      										 dto.setRelation((String) compSub.get(1));
		      										 dto.setRelFatherName((String) compSub.get(2));
		      										 dto.setRelMotherName((String) compSub.get(3));
		      										 dto.setRcountryName((String) compSub.get(4));
		      										 dto.setRstateName((String) compSub.get(5));
		      										 dto.setRdistrictName((String) compSub.get(6));
		      										 dto.setRelAddress((String) compSub.get(7));
		      										 dto.setRelMobNo((String) compSub.get(8));
		      										 dto.setRelphone((String) compSub.get(9));
		      										 dto.setRelEmail((String) compSub.get(10));
		      										 dto.setRelDeathCerName((String) compSub.get(11));
		      										 dto.setRelPhotoName((String) compSub.get(12));
		      										 dto.setRelThumbName((String) compSub.get(13));
		      										 dto.setRelSignName((String) compSub.get(14));
		      										 dto.setRelDeathCrtPath((String) compSub.get(15));
		      										 dto.setRelPhotoPath((String) compSub.get(16));
		      										 dto.setRelThumbPath((String) compSub.get(17));
		      										 dto.setRelSignPath((String) compSub.get(18));
		      										if("en".equalsIgnoreCase(language))
		   										 {
		   										 dto.setPersonType("Relative");
		   										 }
		   										 else
		   										 {
		   											 dto.setPersonType("संबंधी");
		   										 }
		      										
		      										 }
		      									  }
		      									  }
		      								}
		      						}// end of releaser type=2
	     						
	     						formName="";
	     						actionName="";
	     						dto.setIsRelSuccess(1);
	      						forwardPage = "releaseFirstView";  
	     			           }//end insertion
	     		            	 else //failed to insert into DB
	     				         {
	     				        	    dto.setNotInsertedRel(1);
	     				            	forwardPage = "releaseFirst";  
	     				         }
	              		 
	              		 
	                  	
	                   }
	                   else if("1".equalsIgnoreCase(st)||"3".equalsIgnoreCase(st)||"4".equalsIgnoreCase(st))
	                   {
	                 	  dto.setIsAlrdyLocked(1); 
	                 	  dto.setAlrdyLockId(refId);
	                 	  forwardPage = "releaseFirst";                    
	                   }
	                              
	              	 }
	              	 
	              }// end of action PropRelease
			
			      // start of action releaseSaveP
	              if(actionName.equalsIgnoreCase("releaseSaveP")){
	            	  
	            	  
	            	     dto.setPropertyLockDisplayAction("");
		              	 dto.setPropertyLockDisplayAction("");
		              	 dto.setPaymentSuccessAction("");
		              	 
		              	 ArrayList lockinfo=new ArrayList();
		              	 ArrayList comp7 = new ArrayList();
		              	 String st="";
		              	 String refId="";
		              	 lockinfo = lockBD.getLockStatus(dto.getRegistrationId(), dto.getPropId()); 
		              	 
		              	 if(lockinfo.size()>0){
		              		 
		              		 for(int i=0; i<lockinfo.size(); i++){
		              			comp7.add((ArrayList)lockinfo.get(i));
		 						  if(comp7 != null && comp7.size()>0){ 
		 						  for (int k=0; k< comp7.size(); k++){
		 							  ArrayList compSub = (ArrayList)comp7.get(k);
		 							  st = (String) compSub.get(0);
		 							  refId = (String) compSub.get(1);
		 							 }
		 						  } 
		              		 }
		              		 
		              		 
		              	 if("2".equalsIgnoreCase(st))
		                   {
		              			 
		              		     String releaserType = dto.getPersonType();
		              		     String retUnLockId ="";  
		              		     if(releaserType.equalsIgnoreCase("Applicant")||releaserType.equalsIgnoreCase("आवेदक")){
		     	                 retUnLockId = lockBD.insertReleaseDetlsP(dto,userId,officeId );  
		              		     }
		              		   if(releaserType.equalsIgnoreCase("Relative")||releaserType.equalsIgnoreCase("संबंधी")){
			     	                 retUnLockId = lockBD.insertReleaseDetlsRP(dto,userId,officeId );  
			              		     }
		     	            	 if(!retUnLockId.equalsIgnoreCase(""))
		     			         {
		     			        	dto.setPropertyReleaseId(retUnLockId);
		     			        	// transaction details---
		     			        	ArrayList txnDetls = new ArrayList();
		     						ArrayList comp = new ArrayList();
		     						txnDetls=lockBD.getTxnDetlsRP(dto.getRegistrationId(), dto.getPropId(),retUnLockId,language);
		     						 if (txnDetls.size()>0){
		     								for(int i =0; i<txnDetls.size(); i++){
		     									comp.add((ArrayList)txnDetls.get(i));
		     									  if(comp != null && comp.size()>0){ 
		     									  for (int k=0; k< comp.size(); k++){
		     										  ArrayList compSub = (ArrayList)comp.get(k);
		     										  dto.setPropertyReleaseId((String) compSub.get(0));
		     										  dto.setRegistrationId((String) compSub.get(1));
		     										  dto.setRegistrationDate((String) compSub.get(2));
		     										  String prId = (String) compSub.get(3);
		 											  if (prId.length()==15){
		 												  prId = "0"+prId;
		 											  }
		 											  dto.setPropId(prId);
		     										  dto.setPoaRegNo((String) compSub.get(4));
		     										  dto.setPoaRegDate((String) compSub.get(5));
		     										  dto.setLockStatus((String) compSub.get(6));
		     										  dto.setReasonForRelease((String) compSub.get(7));
		     										  dto.setReleaseDate((String) compSub.get(8));
		     										  dto.setRemarksForRelease((String) compSub.get(9));
		     										  dto.setReleaserType((String) compSub.get(10));
		     										 }
		     									  }
		     									  }
		     								}
		     						 //--party details
		     						if(dto.getReleaserType().equalsIgnoreCase("1")){
		     						ArrayList partyDetls = new ArrayList();
		      						ArrayList comp2 = new ArrayList();
		      						partyDetls=lockBD.getPartyDetls(dto.getRegistrationId(),retUnLockId,language);
		      						if (partyDetls.size()>0){
		      								for(int i =0; i<partyDetls.size(); i++){
		      									comp2.add((ArrayList)partyDetls.get(i));
		      									  if(comp2 != null && comp2.size()>0){ 
		      									  for (int k=0; k< comp2.size(); k++){
		      										  ArrayList compSub = (ArrayList)comp2.get(k);
		      										  String apptype=(String) compSub.get(0);
		      										  dto.setAppType((String) compSub.get(0));
		      										  dto.setAppTypeName((String) compSub.get(1));
		      										 if(apptype.equalsIgnoreCase("2")){
		      											  dto.setFirstName((String) compSub.get(2));
		      											  dto.setMidName((String) compSub.get(3));
		      											  dto.setLastName((String) compSub.get(4));
		      											  dto.setGender((String) compSub.get(5));
		      											/*if("M".equalsIgnoreCase(dto.getGender()))
		     											  {
		     												  if("en".equalsIgnoreCase(language))
		     												  {
		     													 dto.setGender("male");
		     												  }
		     												  else
		     												  {
		     													 dto.setGender("पुस्र्ष");
		     												  }
		     												 
		     												 
		     											  }
		     											  else
		     											  {
		     												 if("en".equalsIgnoreCase(language))
		    												  {
		    													 dto.setGender("female");
		    												  }
		    												  else
		    												  {
		    													 dto.setGender("महिला");
		    												  }  
		     											  }
		     											  */
		      											  dto.setAge((String) compSub.get(6));
		      											  dto.setAddress((String) compSub.get(10));
		      										      dto.setPhone((String) compSub.get(12));
		      										      dto.setMphone((String) compSub.get(13));
		      											  dto.setPin((String) compSub.get(11));
		      											  dto.setEmail((String) compSub.get(14));
		      											  /*dto.setIdProofName((String) compSub.get(15));
		      											  dto.setIdProofNo((String) compSub.get(16));*/
		      											  dto.setBankName((String) compSub.get(15));
		      											  dto.setBankAddress((String) compSub.get(16));
		      											  dto.setGuardianName((String) compSub.get(19));
		      											  dto.setMotherName((String) compSub.get(20));
		      											 }else
		      												 if(apptype.equalsIgnoreCase("1")){
		      													 dto.setOrgName((String) compSub.get(17));
		      													 dto.setAuthName((String) compSub.get(18));
		      													 dto.setOrgAddress((String) compSub.get(10));
		      													 dto.setOrgPhno((String) compSub.get(12));
		      													 dto.setOrgMobno((String) compSub.get(13));
		      												 }
		      										  dto.setOrgCountryName((String) compSub.get(7));
		      										  dto.setOrgStateName((String) compSub.get(8));
		      										  dto.setOrgDistrictName((String) compSub.get(9));
		      										  dto.setDocumentName((String) compSub.get(21));
		      										  dto.setThunmbName((String) compSub.get(22));
		      										  dto.setSignatureName((String) compSub.get(23));
		      										  dto.setPhotoPath((String) compSub.get(24));
		      										  dto.setThumbPath((String) compSub.get(25));
		      										  dto.setSignPath((String) compSub.get(26));
		      										
		      										 }
		      									  }
		      									  }
		      								}
		     			         
		      						        ArrayList photoDetls = new ArrayList();
		      		 						ArrayList comp6 = new ArrayList();
		      		 						photoDetls=lockBD.getPhotoDetl(retUnLockId,language);
		      		 						if (photoDetls.size()>0){
		      		 							for(int i =0; i<photoDetls.size(); i++){
		      		 								comp6.add((ArrayList)photoDetls.get(i));
		      		 								  if(comp6 != null && comp6.size()>0){ 
		      		 								  for (int k=0; k< comp6.size(); k++){
		      		 									  ArrayList compSub = (ArrayList)comp6.get(k);
		      		 									  dto.setIdProofName((String) compSub.get(1));
		      		 									  dto.setIdProofNo((String) compSub.get(0));
		      		 								  }
		      		 								  }
		      		 								  }
		      		 							}
		      		 						if("en".equalsIgnoreCase(language))
		      		 						{
		      		 						dto.setPersonType("Applicant");
		      		 						}
		      		 						else
		      		 						{
		      		 							dto.setPersonType("आवेदक");
		      		 						}
		      		 						}// end of releaser type=1
		     						
		     						if(dto.getReleaserType().equalsIgnoreCase("2")){
		     							ArrayList relativeDetls = new ArrayList();
			      						ArrayList comp2 = new ArrayList();
			      						relativeDetls=lockBD.getRelativeDetls(dto.getRegistrationId(),retUnLockId,language);
			      						if (relativeDetls.size()>0){
			      								for(int i =0; i<relativeDetls.size(); i++){
			      									comp2.add((ArrayList)relativeDetls.get(i));
			      									  if(comp2 != null && comp2.size()>0){ 
			      									  for (int k=0; k< comp2.size(); k++){
			      										  ArrayList compSub = (ArrayList)comp2.get(k);
			      										  
			      										 dto.setReleaserName((String) compSub.get(0));
			      										 dto.setRelation((String) compSub.get(1));
			      										 dto.setRelFatherName((String) compSub.get(2));
			      										 dto.setRelMotherName((String) compSub.get(3));
			      										 dto.setRcountryName((String) compSub.get(4));
			      										 dto.setRstateName((String) compSub.get(5));
			      										 dto.setRdistrictName((String) compSub.get(6));
			      										 dto.setRelAddress((String) compSub.get(7));
			      										 dto.setRelMobNo((String) compSub.get(8));
			      										 dto.setRelphone((String) compSub.get(9));
			      										 dto.setRelEmail((String) compSub.get(10));
			      										 dto.setRelDeathCerName((String) compSub.get(11));
			      										 dto.setRelPhotoName((String) compSub.get(12));
			      										 dto.setRelThumbName((String) compSub.get(13));
			      										 dto.setRelSignName((String) compSub.get(14));
			      										 dto.setRelDeathCrtPath((String) compSub.get(15));
			      										 dto.setRelPhotoPath((String) compSub.get(16));
			      										 dto.setRelThumbPath((String) compSub.get(17));
			      										 dto.setRelSignPath((String) compSub.get(18));
			      										if("en".equalsIgnoreCase(language))
			   										 {
			   										 dto.setPersonType("Relative");
			   										 }
			   										 else
			   										 {
			   											 dto.setPersonType("संबंधी");
			   										 }
			      										
			      										 }
			      									  }
			      									  }
			      								}
			      						}// end of releaser type=2
		     						
		     						formName="";
		     						actionName="";
		     						dto.setIsReleaseSave(1);
		      						forwardPage = "releasePayView";  
		     			           }//end insertion
		     		            	 else //failed to insert into DB
		     				         {
		     				        	    dto.setNotInsertedRel(1);
		     				            	forwardPage = "releaseFirst";  
		     				         }
		              		 
		              		 
		                  	
		                   }
		                   else if("1".equalsIgnoreCase(st)||"3".equalsIgnoreCase(st)||"4".equalsIgnoreCase(st))
		                   {
		                 	  dto.setIsAlrdyLocked(1); 
		                 	  dto.setAlrdyLockId(refId);
		                 	  forwardPage = "releaseFirst";                    
		                   }
		                              
		              	 }
	            	  
	            	  
	              }// end of action releaseSaveP
		}//end of lockRelease1 form
		
		 // start of ReleaseMainView page
		if (formName!=null && formName.equalsIgnoreCase("ReleaseMainView")){
			
			// start of action downloadPhoto
			  if(actionName.equalsIgnoreCase("downloadPhoto")){
				        String filePath=dto.getRelPhotoPath()+"/"+dto.getRelPhotoName();
		    			logger.debug("retrieval path of photo-->"+filePath);
		    			DMSUtility dmsUtil=new DMSUtility();
		    			dmsUtil.readImage(filePath);
		    			actionName="";
		    			formName="";
		    			forwardPage="releaseFirstView";
					}// end of downloadPhoto
			
			// start of action downloadSign
			  if(actionName.equalsIgnoreCase("downloadSign")){
				    String filePath=dto.getRelSignPath()+"/"+dto.getRelSignName();
	    			logger.debug("retrieval path of sign-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			dmsUtil.readImage(filePath);
	    			actionName="";
	    			formName="";
	    			forwardPage="releaseFirstView";
			  }// end of downloadSign
			  
			// start of action downloadThumb
			  if(actionName.equalsIgnoreCase("downloadThumb")){
				    String filePath=dto.getRelThumbPath()+"/"+dto.getRelThumbName();
	    			logger.debug("retrieval path of thumb-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			dmsUtil.readImage(filePath);
	    			actionName="";
	    			formName="";
	    			forwardPage="releaseFirstView"; 
			  }// end of downloadThumb
			
			// start of action downloadDeathCert
			  if(actionName.equalsIgnoreCase("downloadDeathCert")){
				    String filePath=dto.getRelDeathCrtPath()+"/"+dto.getRelDeathCerName();
	    			logger.debug("retrieval path of thumb-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			dmsUtil.readImage(filePath);
	    			actionName="";
	    			formName="";
	    			forwardPage="releaseFirstView"; 
			  }// end of downloadDeathCert
			  
			// start of action downloadPhotoA
			  if(actionName.equalsIgnoreCase("downloadPhotoA")){
				        String filePath=dto.getPhotoPath()+"/"+dto.getDocumentName();
		    			logger.debug("retrieval path of photo-->"+filePath);
		    			DMSUtility dmsUtil=new DMSUtility();
		    			byte[] docContents=dmsUtil.getDocumentBytes(filePath);
		    			dmsUtil.downloadDocument(response, filePath, docContents);
		    			actionName="";
		    			formName="";
		    			forwardPage="releaseFirstView";
					}// end of downloadPhotoA
			
			// start of action downloadSignA
			  if(actionName.equalsIgnoreCase("downloadSignA")){
				    String filePath=dto.getSignPath()+"/"+dto.getSignatureName();
	    			logger.debug("retrieval path of sign-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			byte[] docContents=dmsUtil.getDocumentBytes(filePath);
	    			dmsUtil.downloadDocument(response, filePath, docContents);
	    			actionName="";
	    			formName="";
	    			forwardPage="releaseFirstView";
			  }// end of downloadSignA
			  
			// start of action downloadThumbA
			  if(actionName.equalsIgnoreCase("downloadThumbA")){
				    String filePath=dto.getThumbPath()+"/"+dto.getThunmbName();
	    			logger.debug("retrieval path of thumb-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			byte[] docContents=dmsUtil.getDocumentBytes(filePath);
	    			dmsUtil.downloadDocument(response, filePath, docContents);
	    			actionName="";
	    			formName="";
	    			forwardPage="releaseFirstView"; 
			  }// end of downloadThumbA
			
			
		}// end of form ReleaseMainView
	
	//start of ReleaseMainViewP form
          if (formName!=null && formName.equalsIgnoreCase("ReleaseMainViewP")){
			
			// start of action downloadPhoto
			  if(actionName.equalsIgnoreCase("downloadPhoto")){
				        String filePath=dto.getRelPhotoPath()+"/"+dto.getRelPhotoName();
		    			logger.debug("retrieval path of photo-->"+filePath);
		    			DMSUtility dmsUtil=new DMSUtility();
		    			dmsUtil.readImage(filePath);
		    			actionName="";
		    			formName="";
		    			forwardPage="releasePayView";
					}// end of downloadPhoto
			
			// start of action downloadSign
			  if(actionName.equalsIgnoreCase("downloadSign")){
				    String filePath=dto.getRelSignPath()+"/"+dto.getRelSignName();
	    			logger.debug("retrieval path of sign-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			dmsUtil.readImage(filePath);
	    			actionName="";
	    			formName="";
	    			forwardPage="releasePayView";
			  }// end of downloadSign
			  
			// start of action downloadThumb
			  if(actionName.equalsIgnoreCase("downloadThumb")){
				    String filePath=dto.getRelThumbPath()+"/"+dto.getRelThumbName();
	    			logger.debug("retrieval path of thumb-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			dmsUtil.readImage(filePath);
	    			actionName="";
	    			formName="";
	    			forwardPage="releasePayView"; 
			  }// end of downloadThumb
			
			// start of action downloadDeathCert
			  if(actionName.equalsIgnoreCase("downloadDeathCert")){
				    String filePath=dto.getRelDeathCrtPath()+"/"+dto.getRelDeathCerName();
	    			logger.debug("retrieval path of thumb-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			dmsUtil.readImage(filePath);
	    			actionName="";
	    			formName="";
	    			forwardPage="releasePayView"; 
			  }// end of downloadDeathCert
			  
			// start of action downloadPhotoA
			  if(actionName.equalsIgnoreCase("downloadPhotoA")){
				        String filePath=dto.getPhotoPath()+"/"+dto.getDocumentName();
		    			logger.debug("retrieval path of photo-->"+filePath);
		    			DMSUtility dmsUtil=new DMSUtility();
		    			dmsUtil.readImage(filePath);
		    			actionName="";
		    			formName="";
		    			forwardPage="releasePayView";
					}// end of downloadPhotoA
			
			// start of action downloadSignA
			  if(actionName.equalsIgnoreCase("downloadSignA")){
				    String filePath=dto.getSignPath()+"/"+dto.getSignatureName();
	    			logger.debug("retrieval path of sign-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			dmsUtil.readImage(filePath);
	    			actionName="";
	    			formName="";
	    			forwardPage="releasePayView";
			  }// end of downloadSignA
			  
			// start of action downloadThumbA
			  if(actionName.equalsIgnoreCase("downloadThumbA")){
				    String filePath=dto.getThumbPath()+"/"+dto.getThunmbName();
	    			logger.debug("retrieval path of thumb-->"+filePath);
	    			DMSUtility dmsUtil=new DMSUtility();
	    			dmsUtil.readImage(filePath);
	    			actionName="";
	    			formName="";
	    			forwardPage="releasePayView"; 
			  }// end of downloadThumbA
			  
			// start of action NEXT_TO_PAYMENT_ACTION
			  if(actionName.equalsIgnoreCase("NEXT_TO_PAYMENT_ACTION")){
				  
					DecimalFormat myformatter=new DecimalFormat("############");    
					String releaseId=dto.getPropertyReleaseId();
					String payableAmt = dto.getPayableFee();
					userId = (String)session.getAttribute("UserId");
					dto.setNotInsertedP(0);
					dto.setIsPartial(0);
					
					String officeName="";
					String districtId="";
					String districtName="";
					officeId = (String)session.getAttribute("loggedToOffice");
					ArrayList alist = lockBD.getrequestDetails(officeId);
					if(alist.size()>0){
					ArrayList rowList = (ArrayList)alist.get(0);
					officeName=(String)rowList.get(0);
					districtId=(String)rowList.get(1);
					districtName=(String)rowList.get(2);
					}
					logger.debug("Inside payment method----the release id is-->>"+releaseId);
					
					ArrayList first = new ArrayList();
					first = lockBD.getPayDtls(releaseId);
					if (first.size()==0){
						logger.debug("inside if");
						boolean insrt = false;
						try{
							String id = lockBD.getId();
							insrt = lockBD.insertPay(releaseId,payableAmt,userId,id, "FUN_201");
						
						     if(insrt){
						    logger.debug("inside if insrt");
							
							request.setAttribute("parentModName", "Property Locking");
							request.setAttribute("parentFunName", "Property Release");
							request.setAttribute("parentFunId", "FUN_201");
							request.setAttribute("parentAmount", payableAmt);
							request.setAttribute("parentTable", "IGRS_PROP_LOCK_PAYMENT_DETLS");
							request.setAttribute("parentKey", id);
							request.setAttribute("forwardPath", "./lockAction.do?PropRelease=release&TRFS=NGI");
							request.setAttribute("parentColumnName", "PAYMENT_DETL_ID");
							request.setAttribute("parentOfficeId", officeId);
							request.setAttribute("parentOfficeName", officeName);
							request.setAttribute("parentDistrictId", districtId);
							request.setAttribute("parentDistrictName", districtName);
							request.setAttribute("parentReferenceId", releaseId);
							request.setAttribute("formName", "lockForm");
							logger.debug("just before redirection");
							formName="";
							actionName="";
							forwardPage = "nextToPay";
							
							}
						     else{
						    	 dto.setNotInsertedP(1);
						    	 forwardPage = "releasePayView";
						          }
						}catch(Exception e){
							dto.setNotInsertedP(1);
							forwardPage = "releasePayView";
						}
						}
					// if pay details are not empty
					else{
							logger.debug("inside else---means record is found");
							ArrayList comp = new ArrayList();
							String uniqId="";
						    String amtToBePaid="";
						    String paidAmt="";
						    String pymtFlg="";
							
							for(int i =0; i<first.size(); i++){
								comp.add((ArrayList)first.get(i));
								  if(comp != null && comp.size()>0){ 
								  for (int k=0; k< comp.size(); k++){
									  ArrayList compSub = (ArrayList)comp.get(k);
									  uniqId =      (String) compSub.get(0);
									  amtToBePaid = (String) compSub.get(1);
									  paidAmt =     (String) compSub.get(2);
									  pymtFlg =     (String) compSub.get(3);
									    logger.debug("unique id is ............................"+uniqId);
										logger.debug("unpayable amt is ............................"+amtToBePaid);
										logger.debug("paid amt is..................................."+paidAmt);
										logger.debug("pymtFlg is....................................."+pymtFlg);
									 }
								  }
								  }
							double damtToBePaid = Double.parseDouble(amtToBePaid);
							
							//------- if payment flag is I ----
							if(pymtFlg.equalsIgnoreCase("I")){
								
								request.setAttribute("parentModName", "Property Locking");
								request.setAttribute("parentFunName", "Property Release");
								request.setAttribute("parentFunId", "FUN_201");
								request.setAttribute("parentAmount", amtToBePaid);
								request.setAttribute("parentTable", "IGRS_PROP_LOCK_PAYMENT_DETLS");
								request.setAttribute("parentKey", uniqId);
								request.setAttribute("forwardPath", "./lockAction.do?PropRelease=release&TRFS=NGI");
								request.setAttribute("parentColumnName", "PAYMENT_DETL_ID");
								request.setAttribute("parentOfficeId", officeId);
								request.setAttribute("parentOfficeName", officeName);
								request.setAttribute("parentDistrictId", districtId);
								request.setAttribute("parentDistrictName", districtName);
								request.setAttribute("parentReferenceId", releaseId);
								request.setAttribute("formName", "lockForm");
								formName="";
								actionName="";						
								forwardPage = "nextToPay";
							}
							//----------- if payment flag is P -----
							
							if(pymtFlg.equalsIgnoreCase("P")){
								
								double dpaidAmt = Double.parseDouble(paidAmt);
								//condition 1-------------------pending amount-------------
								if (damtToBePaid>dpaidAmt){
									double bal= damtToBePaid-dpaidAmt;
									String balfrmt = myformatter.format(bal);
									dto.setPayableFee(balfrmt);
									boolean insrt = false;
									
									try{
										String id = lockBD.getId();
										insrt = lockBD.insertPay(releaseId,balfrmt,userId,id,"FUN_201");
									
									 if(insrt){
								     logger.debug("inside if insrt-----of else condition---means record found");
								        request.setAttribute("parentModName", "Property Locking");
										request.setAttribute("parentFunName", "Property Release");
										request.setAttribute("parentFunId", "FUN_201");
										request.setAttribute("parentAmount", balfrmt);
										request.setAttribute("parentTable", "IGRS_PROP_LOCK_PAYMENT_DETLS");
										request.setAttribute("parentKey", id);
										request.setAttribute("forwardPath", "./lockAction.do?PropRelease=release&TRFS=NGI");
										request.setAttribute("parentColumnName", "PAYMENT_DETL_ID");
										request.setAttribute("parentOfficeId", officeId);
										request.setAttribute("parentOfficeName", officeName);
										request.setAttribute("parentDistrictId", districtId);
										request.setAttribute("parentDistrictName", districtName);
										request.setAttribute("parentReferenceId", releaseId);
										request.setAttribute("formName", "lockForm");
										formName="";
										actionName="";						
										forwardPage = "nextToPay";
								     
									}
									  else{
									    	 dto.setNotInsertedP(1);
									    	 forwardPage = "releasePayView";
									          }
									}catch(Exception e){
										dto.setNotInsertedP(1);
										forwardPage = "releasePayView";
									}
									
								}
								}
					}// end of pay details not empty
					
			  }// end of NEXT_TO_PAYMENT_ACTION
			  }
   // end of ReleaseMainViewP form
		
          
    //start of form lockView1
      if (formName!=null && formName.equalsIgnoreCase("lockView1")){
    	  
    	  //start of action viewSearch
    	  if(actionName.equalsIgnoreCase("viewSearch")){
    		
    		  String referId = "";               
              String regno ="";
              String fromDate="";
              String toDate="";
              String aStatus="" ;
              referId  = dto.getPropertyTxnId();
              regno    = dto.getRegistrationId();                
              fromDate = dto.getFromRequestDate();                
              toDate   = dto.getToRequestDate();
              aStatus  = dto.getAppStatus();
              
              dto.setIsViewEmpty(0);
              dto.setViewResultList(new ArrayList());
  		      ArrayList viewResultList = new ArrayList();
  			try{
  				    viewResultList= lockBD.getViewDetls(referId,regno,fromDate,toDate,aStatus,language);
  					if(viewResultList.size()>0){
  						dto.setViewResultList(viewResultList);
  						request.setAttribute("viewResultList", viewResultList);
  						formName="";
  				        actionName="";
  						forwardPage = "plockView1";
  						
  					}else{
  						 dto.setIsViewEmpty(1);
  						 formName="";
  				         actionName="";
  						forwardPage = "plockView1";
  					   }
  				
  		    
  			}catch(Exception e){
  			logger.debug("inside popup page1, reg detl is empty");
  			 dto.setIsViewEmpty(1);
  			formName="";
  	        actionName="";
  			forwardPage = "plockView1";
  		}
  			formName="";
  			actionName="";		
           }
    	  //end of action viewSearch
    	  
    	  // start of action resetView1
    	  if(actionName.equalsIgnoreCase("resetView1")){
    		  
    		  dto.setViewComb("");
    		  dto.setViewResultList(new ArrayList());
    		  dto.setIsViewEmpty(0);
    		  dto.setIsFromView(0);
    		  dto.setPropertyTxnId("");
    		  dto.setRegistrationId("");
    		  dto.setFromRequestDate("");
    		  dto.setToRequestDate("");
    		  dto.setAppStatus("");
    		  dto.setCreatedDt("");
    		  formName="";
    	      actionName="";
    		  forwardPage = "plockView1";
    		  
    	  }//end of action resetView1
    	  
    	 
    	  if(("downloadPhoto1").equalsIgnoreCase(actionName)){
		        String filePath=dto.getPhotoPath()+"/"+dto.getDocumentName();
  			logger.debug("retrieval path of photo-->"+filePath);
  			DMSUtility dmsUtil=new DMSUtility();
  			byte contents[] = dmsUtil.getDocumentBytes(filePath);
			downloadDocument(response, contents, filePath);
  			actionName="";
  			formName="";
  			forwardPage="lockFirstView";
			}// end of downloadPhoto
		  if(("downloadSign1").equalsIgnoreCase(actionName)){
				String filePath=dto.getSignPath()+"/"+dto.getSignatureName();
	  			logger.debug("retrieval path of sign-->"+filePath);
	  			DMSUtility dmsUtil=new DMSUtility();
	  			byte contents[] = dmsUtil.getDocumentBytes(filePath);
			    downloadDocument(response, contents, filePath);
	  			actionName="";
	  			formName="";
	  			forwardPage="lockFirstView";
			}// end of downloadPhoto
		  if(("downloadThumb1").equalsIgnoreCase(actionName)){
			  String filePath=dto.getThumbPath()+"/"+dto.getThunmbName();
	  			logger.debug("retrieval path of thumb-->"+filePath);
	  			DMSUtility dmsUtil=new DMSUtility();
	  			byte contents[] = dmsUtil.getDocumentBytes(filePath);
			    downloadDocument(response, contents, filePath);
	  			actionName="";
	  			formName="";
	  			forwardPage="lockFirstView"; 
			}// end of downloadPhoto
        	  //end 		  
          } // end of form lockView1
          
          
	}// end of (form!=null)
	logger.debug("FORWARD_JSP---------------> "+forwardPage);	 
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

private boolean uploadFile(FormFile filetobeuploaded,String fileName,String filePath) {

	
	
	try {
		File folder = new File(filePath);
	      if (!folder.exists()) {
		folder.mkdirs();
	       }
	      
			File newFile = new File(filePath, fileName);
			logger.info("NEW FILE NAME:-" + newFile);
			FileOutputStream fos = new FileOutputStream(newFile);
			fos.write(filetobeuploaded.getFileData());
			fos.close();
		

	} catch (Exception ex) {
		ex.printStackTrace();
	}
	return true;
}

//added by shruti---8 april 2014
public static void downloadDocument(HttpServletResponse res, byte[] docContents, String fileName) {
	try {
		OutputStream os = res.getOutputStream();
		res.setContentType("application/download");
		res.setHeader("Content-Disposition", "attachment; filename="
				+ URLEncoder.encode(fileName,"UTF-8"));
		os.write(docContents);
		os.flush();
		os.close();
	} catch (Exception e) {
	}
}


}
