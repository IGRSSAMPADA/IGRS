package com.wipro.igrs.noEncumbrance.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;
import org.apache.struts.upload.FormFile;


import com.newgen.burning.BurningImageAndText;
import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentDetails;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.Encumbrance;
import com.newgen.burning.ODServerDetails;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveats.action.CaveatsViewSearchAction;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.copyissuance.bd.CertifiedBD;
import com.wipro.igrs.copyissuance.constant.CommonConstant;
import com.wipro.igrs.newPropvaluation.bo.PropertyValuationBO;
import com.wipro.igrs.noEncumbrance.bd.NoEncumBD;
import com.wipro.igrs.noEncumbrance.bo.NoEncumBO;
import com.wipro.igrs.noEncumbrance.dto.FloorDetailsDTO;
import com.wipro.igrs.noEncumbrance.dto.FloorTypeDTO;
import com.wipro.igrs.noEncumbrance.dto.KhasraDTO;
import com.wipro.igrs.noEncumbrance.dto.NoEncumDTO;
import com.wipro.igrs.noEncumbrance.form.NoEncumForm;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.util.GUIDGenerator;
import com.wipro.igrs.util.PropertiesFileReader;

public class NoEncumAction extends BaseAction {
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
	private Logger logger = (Logger) Logger.getLogger(NoEncumAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException, ServletException,
			Exception {
		String FORWAD_SUCCESS = "";
		PropertyValuationBO newPropBO=new PropertyValuationBO();
		NoEncumForm ncForm = (NoEncumForm) form;
		logger.debug("WE ARE IN SuppleDocAction Debug");
		logger.info("WE ARE IN  SuppleDocAction INFO");
		// HttpSession session = request.getSession(true);
		String roleId = (String) session.getAttribute("loggedInRole");
		String funId = (String) session.getAttribute("functionId");
		String userId = (String) session.getAttribute("UserId");
		String officeId=(String)session.getAttribute("loggedToOffice");	
		String language=(String)session.getAttribute("languageLocale");
		 PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		NoEncumBO objEncumBO = new NoEncumBO();
		String feeVal = null;
		String otherFeeVal = null;
		String userType = null;
		String serviceId = null;
		float total = 0;
		String page = null;
		String actionType = request.getParameter("actionType");
		page = request.getParameter("pageName");
		 String acname=null;
	     acname=(String)request.getParameter("actioname");
	     String frdName=null;
	     frdName=request.getParameter("fwdName");
	     ArrayList errorList1 = new ArrayList();
		String trancId=null;
		trancId=request.getParameter("txnId");
		String languageLocale="hi";
		if(session.getAttribute("languageLocale")!=null){
			languageLocale=(String)session.getAttribute("languageLocale");
		}
		if (page != null) {
			if (page.equalsIgnoreCase("dashboard")) {
			//	NoEncumForm ncForm = (NoEncumForm) form;
				ArrayList pendingApplicationList = objEncumBO.getPendingNoEncumApps(session.getAttribute("UserId")
								.toString());
				if (pendingApplicationList.size() == 0)
					ncForm.getObjDashBoardDTO()
							.setPendingNoEncumApplicationList(null);
				else
					ncForm.getObjDashBoardDTO()
							.setPendingNoEncumApplicationList(
									pendingApplicationList);
				request.setAttribute("pendingApplicationList", ncForm
						.getObjDashBoardDTO()
						.getPendingNoEncumApplicationList());

				FORWAD_SUCCESS = "DashScreen";
				return mapping.findForward(FORWAD_SUCCESS);
			}
		}
		if(trancId!=null)
		{
			//NoEncumForm ncForm = (NoEncumForm) form;
			NoEncumDTO dto=new NoEncumBD().getDetailsOnId(trancId,languageLocale);
			ncForm.setNoEncumDTO(dto);
			session.setAttribute("userDetailsDTO",dto);
			FORWAD_SUCCESS = "idClick";
			return mapping.findForward(FORWAD_SUCCESS);		
		}
		if(request.getParameter("propertyTxnID")!=null){
    		
    		String propertyId=request.getParameter("propertyTxnID");
    		
    		//Start added By ankita 
    		/*
    		 * This code is added for redirecting from linking History page*/
    	
    		//End added By ankita 
    		
    		// added by simran for checklist
    		
    	
    		RegCommonForm regForm =new RegCommonForm();
    		RegCommonBO commonBo = new RegCommonBO();
    		regForm.setHidnRegTxnId(objEncumBO.getRegTxn(ncForm.getNoEncumDTO().getRegNo()));
    		String[] deedInstArray=commonBo.getDeedInstId(regForm.getHidnRegTxnId());
    		
    		regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
    		//regForm.setCommonDeed(commonBo.getCommonDeedFlag(deedInstArray[0].trim()));   // setting common deed flag
    		regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
    		if(deedInstArray[2].trim().equals("-") || deedInstArray[2].trim().equals("")){
    			regForm.setExmpID("");
    			regForm.setHdnExAmount("");
    			}else{
    				regForm.setExmpID(deedInstArray[2].trim());
    				regForm.setHdnExAmount(deedInstArray[2].trim());
    			}
    		
    		if(deedInstArray[5]!=null){
    		regForm.setDuty_txn_id(Integer.parseInt(deedInstArray[5].trim()));
    		}else{
    			regForm.setDuty_txn_id(0);
    		}
    		
    		String flags[]=commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));
			
			if(flags!=null && flags[0]!=null && flags[1]!=null && flags[2]!=null){
				
				if(flags[2].trim().equalsIgnoreCase("Y"))
				{regForm.setCommonDeed(1);}
				else
				{regForm.setCommonDeed(0);}
				
				regForm.setPvReqFlag(flags[1].trim());
				regForm.setPropReqFlag(flags[0].trim());
				
				
			}else{
				regForm.setCommonDeed(0);
				regForm.setPvReqFlag("");
				regForm.setPropReqFlag("");
			}
    		
			if(regForm.getPvReqFlag().equalsIgnoreCase("Y")){
    		regForm.setMapPropertyTransPartyDisp
    		(commonBo.getPropertyDetsForViewConfirm(regForm.getHidnRegTxnId(), propertyId,language));
    		FORWAD_SUCCESS="propertyViewPV";
			}else{
				regForm.setMapPropertyTransPartyDisp
	    		(commonBo.getPropertyDetsForViewConfirmNonProp(regForm.getHidnRegTxnId(), propertyId,language));
				FORWAD_SUCCESS="propertyView";
			}
    		
    		//regForm.setConfirmationFlag("01");
    		request.setAttribute("reginit", regForm);
    		
    		return mapping.findForward(FORWAD_SUCCESS);
    	}
		
		if (form != null) {
			//NoEncumForm ncForm = (NoEncumForm) form;
			NoEncumBD sdBD = new NoEncumBD();
			String frwdName = request.getParameter("fwdName");
			
			NoEncumDTO sdDTO = ncForm.getNoEncumDTO();
			sdDTO.setFunId(funId);
			sdDTO.setRoleId(roleId);
			sdDTO.setUserId(userId);
			// Fee calculation
			if (funId == null) {
				funId = "FUN_204";
			}
			// userType = (String)session.getAttribute("role");//Ramesh
			// commented on 12 dec 12
			CertifiedBD bd = new CertifiedBD();
			userType = bd.getUserType(userId);
			if (userType == null) {
				userType = "I";
			}
			
			feeVal = bd.getFee(funId, userType);
			if (feeVal != null)
				if (!feeVal.equalsIgnoreCase(""))
					ncForm.getNoEncumDTO().setFee(feeVal);

			otherFeeVal ="0.0"; 
				//bd.getOthersFeeDuty(funId, serviceId, userType) == null ? "0.0": bd.getOthersFeeDuty(funId, serviceId, userType);
			logger.debug("IssuanceCopyAction -- otherFeeVal =" + otherFeeVal);
			if (otherFeeVal != null) {
				ncForm.getNoEncumDTO().setPostalFee(otherFeeVal);
				total = Float
						.parseFloat(ncForm.getNoEncumDTO().getFee() == null||ncForm.getNoEncumDTO().getFee().equalsIgnoreCase("") ? "0.0"
								: ncForm.getNoEncumDTO().getFee())
						+ Float.parseFloat(ncForm.getNoEncumDTO()
								.getPostalFee() == null ? "0.0" : ncForm
								.getNoEncumDTO().getPostalFee());
				ncForm.getNoEncumDTO().setTotalFee(String.valueOf(total));
				logger.debug("IssuanceCopyAction -- total =" + total);
			}
			if(ncForm.getNoEncumDTO().getSelectedItems()!=null && ncForm.getNoEncumDTO().getSelectedItems().size()>0)
			{
				double feeval1=0.0;
				double otherfeeval1=0.0;
			if(feeVal!=null&&!feeVal.equalsIgnoreCase(""))
			{
				 feeval1=ncForm.getNoEncumDTO().getSelectedItems().size()*Double.parseDouble(feeVal);
			}
			if(otherFeeVal!=null&&!otherFeeVal.equalsIgnoreCase(""))
			{

			 otherfeeval1=ncForm.getNoEncumDTO().getSelectedItems().size()*Double.parseDouble(otherFeeVal);
			}
			

			double totalfeeval1=ncForm.getNoEncumDTO().getSelectedItems().size()*total;
					
			request.setAttribute("fee", String.valueOf(feeval1));
			request.setAttribute("otherFee", String.valueOf(otherfeeval1));
			request.setAttribute("totalFee", String.valueOf(totalfeeval1));
			sdDTO.setTotalFee(String.valueOf(totalfeeval1));
			}
			else
			{
				request.setAttribute("fee", feeVal);
				request.setAttribute("otherFee", otherFeeVal);
				request.setAttribute("totalFee", String.valueOf(total));
			}
			// End fee calculation
			String pymntParamStatus = (String) request.getParameter("paymentStatus");
			try {

				if (!((String) request.getParameter("frm") == null || (String) request
						.getParameter("axn") == null)) {
					sdDTO.setActionName(request.getParameter("axn"));
					sdDTO.setFormName(request.getParameter("frm"));
				}
				// String crntUser=(String)session.getAttribute("user_id");
				if (userId == null || "".equalsIgnoreCase(userId))
					userId = "igrs";
				sdDTO.setCurrentUserId(userId);
				if (frwdName != null
						&& (frwdName.equalsIgnoreCase("Create") || frwdName
								.equalsIgnoreCase(""))) {
					frwdName = "";
					sdDTO.setShowpCountyName("");
					sdDTO.setShowpStateName("");
					sdDTO.setShowpDistName("");
					sdDTO.setShowpTehesilName("");
					sdDTO.setShowpPatwariName("");
					sdDTO.setShowpMuncpaltyName("");
					sdDTO.setShowpUrbanName("");
					sdDTO.setShowpGramName("");
					sdDTO.setMunicipalty("");
					sdDTO.setAreaType("");
					sdDTO.setPurposeReq("");
					sdDTO.setRegNo("");
					sdDTO.setRegDate("");
					sdDTO.setAgViKhand("");
					sdDTO.setAgRicircle("");
					sdDTO.setAgpatwari("");
					sdDTO.setAgGram("");
					sdDTO.setAgKhasraNo("");
					sdDTO.setAgLayoutDtls("");
					sdDTO.setAgPustikaNo("");
					sdDTO.setAgNorthboundryDtls("");
					sdDTO.setAgSouthboundryDtls("");
					sdDTO.setAgEastboundryDtls("");
					sdDTO.setAgWestboundryDtls("");
					sdDTO.setAgSize("");
					sdDTO.setAgArea("");
					sdDTO.setAgIsIrigated("");
					sdDTO.setPltViKhand("");
					sdDTO.setPltRicircle("");
					sdDTO.setPltPatwari("");
					sdDTO.setPltGram("");
					sdDTO.setPltMohala("");
					sdDTO.setPltKhasraNo("");
					sdDTO.setPltLayout("");
					sdDTO.setPltNazSheetNo("");
					sdDTO.setPltPlotNo("");
					sdDTO.setPltNorthboundryDtls("");
					sdDTO.setPltSouthboundryDtls("");
					sdDTO.setPltEastboundryDtls("");
					sdDTO.setPltWestboundryDtls("");
					sdDTO.setPltSize("");
					sdDTO.setPltArea("");
					sdDTO.setPltCorner("");
					sdDTO.setPltResiCom("");
					sdDTO.setBuildViKhand("");
					sdDTO.setBuildRicircle("");
					sdDTO.setBuildPatwari("");
					sdDTO.setBuildGram("");
					sdDTO.setBuildMohalla("");
					sdDTO.setBuildKhasraNo("");
					sdDTO.setBuildLayout("");
					sdDTO.setBuildNazSheetNo("");
					sdDTO.setBuildPlotNo("");
					sdDTO.setBuilsPlotArea("");
					sdDTO.setBuildResiCom("");
					sdDTO.setBuildNoOfFloors("");
					sdDTO.setBuildFloorType("");
					sdDTO.setBuildSize("");
					sdDTO.setBuildArea("");
					sdDTO.setBuildingNoAndShopOffice("");
					sdDTO.setBuildCeilingType("");
					sdDTO.setBuildNorthboundryDtls("");
					sdDTO.setBuildSouthboundryDtls("");
					sdDTO.setBuildEastboundryDtls("");
					sdDTO.setBuildWestboundryDtls("");
					sdDTO.setUserMode("");
					sdDTO.setFname("");
					sdDTO.setTxnId("");
					sdDTO.setMname("");
					sdDTO.setLname("");
					sdDTO.setFatherName("");
					sdDTO.setMotherName("");
					sdDTO.setSpouseName("");
					sdDTO.setGender("");
					sdDTO.setAge("");
					sdDTO.setCasteName("");
					sdDTO.setReligionName("");
					sdDTO.setNationality("");
					sdDTO.setPhyChallanged("");
					sdDTO.setPhotoProofTypeName("");
					sdDTO.setPhotoId("");
					sdDTO.setPhotoBankName("");
					sdDTO.setPhotoBankAddress("");
					sdDTO.setOccupation("");
					sdDTO.setAddress("");
					sdDTO.setCountryName("");
					sdDTO.setStateName("");
					sdDTO.setDistrictName("");
					sdDTO.setPostalCode("");
					sdDTO.setPhoneNo("");
					sdDTO.setMobNo("");
					sdDTO.setMailId("");
					sdDTO.setPropCountryName("");
					sdDTO.setStatePropName("");
					sdDTO.setPropDistrictName("");
					sdDTO.setPropTehesilName("");
					sdDTO.setPropPatwariName("");
					sdDTO.setPropGramName("");
					sdDTO.setPropertyName("");
					// sdDTO.setKhasraList(null);
					sdDTO.setStateList(null);
					sdDTO.setDistrictList(null);
					sdDTO.setStatePropList(null);
					sdDTO.setPropDistrictList(null);
					sdDTO.setPropTehesilList(null);
					sdDTO.setPropPatwariList(null);
					sdDTO.setPropGramList(null);
					sdDTO.setSelectedItems(null);
					// sdDTO.setRegNo("");
					logger.info("Inside COUTNRY,PhotoIdProof,Caste,Religion master retrieve Action");
					sdDTO.setCountryList(sdBD.countryStackBD(languageLocale));
					//sdDTO.setPropCountryList(sdBD.countryPropBD());
					sdDTO.setPropDistrictList(sdBD.districtPropBD("1",languageLocale));
					sdDTO.setPhotoIdList(sdBD.photoIdStackBD(languageLocale));
					sdDTO.setCasteList(sdBD.casteStackBD(languageLocale));//HINDI PENDING IN QUERY
					sdDTO.setReligionList(sdBD.religionStackBD(languageLocale));
					sdDTO.setPropertyList(sdBD.propertyBD(languageLocale));
					//sdDTO.setMunicipaltyList(sdBD.getMunicipalList());
					sdDTO.setMunicipaltyList(new ArrayList());
					sdDTO.setAreaTypeList(sdBD.getAreaTypeList(languageLocale));
					sdDTO.setUserMode("New");

					ncForm.setFloorMasterList(sdBD.getFloorMasterList(languageLocale));
					// ncForm.setNoEncumDTO(new NoEncumDTO());
					sdDTO.setFloordetails(new ArrayList<FloorDetailsDTO>());
					sdDTO.setKhasraList(new ArrayList<KhasraDTO>());
					FORWAD_SUCCESS = "CreateScreen";
				}
				
				else if (sdDTO.getUpdateRequestAction()!=null && sdDTO.getUpdateRequestAction().equalsIgnoreCase("true"))
				{
					boolean b=	new NoEncumBD().updateIssuance(ncForm.getNoEncumDTO(), roleId, funId, userId, "");
					if(b)
					{
						acname=null;
						FORWAD_SUCCESS = "updatecon";
						sdDTO.setUpdateRequestAction("");
						ncForm.getNoEncumDTO().setDisDate("");
						//return mapping.findForward(FORWAD_SUCCESS);
						
					}
				//	FORWAD_SUCCESS = "ConfirmScreen";
					//sdDTO.setUpdateRequestAction("");
					//return mapping.findForward(FORWAD_SUCCESS);
				}
				else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("addkhasraRow"))) {
					try {
						NoEncumDTO floorDTO = ncForm.getNoEncumDTO();
						ArrayList<KhasraDTO> familyMembers = floorDTO
								.getKhasraList();
						refreshKhasraData(familyMembers, request);
						familyMembers.add(new KhasraDTO());
						request.setAttribute("noencumfrm", ncForm);
					} catch (Exception exception) {
						logger.error(exception.getMessage(), exception);
					}
					FORWAD_SUCCESS = "CreateScreen";
				} else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("delkhasraRow"))) {
					try {
						int index = Integer.parseInt(request
								.getParameter("selectedIndex"));
						NoEncumDTO floorDTO = ncForm.getNoEncumDTO();
						ArrayList<KhasraDTO> familyMembers = floorDTO
								.getKhasraList();
						refreshKhasraData(familyMembers, request);
						familyMembers.remove(index);
						request.setAttribute("noencumfrm", ncForm);
					} catch (Exception exception) {
						logger.error(exception.getMessage(), exception);
					}
					FORWAD_SUCCESS = "CreateScreen";
				} else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("addfloorRow"))) {
					try {
						NoEncumDTO floorDTO = ncForm.getNoEncumDTO();
						ArrayList<FloorDetailsDTO> familyMembers = floorDTO
								.getFloordetails();
						refreshFamilyData(familyMembers, request,
								ncForm.getFloorMasterList());
						familyMembers.add(new FloorDetailsDTO());
						request.setAttribute("noencumfrm", ncForm);
						ArrayList<KhasraDTO> familyMembers1 = floorDTO
								.getKhasraList();
						refreshKhasraData(familyMembers1, request);
					} catch (Exception exception) {
						logger.error(exception.getMessage(), exception);
					}
					
					FORWAD_SUCCESS = "CreateScreen";
				}
				else if ("createselecteditem".equalsIgnoreCase(request.getParameter("pageName1"))) 
				{
					// if ("setSelectedPropIDs".equalsIgnoreCase(ncForm.getNoEncumDTO().getActionName())) {
							if("yes".equals(session.getAttribute("cvtSearchUpdate"))) {
								try {
									
									ncForm.getNoEncumDTO().setSelectedItems(
											(ArrayList) ncForm.getNoEncumDTO()
													.getCloneSelectedItems().clone());
									ncForm.getNoEncumDTO().getCloneSelectedItems().clear();
									session.removeAttribute("cvtSearchUpdate");
									double feeval1=ncForm.getNoEncumDTO().getSelectedItems().size()*Double.parseDouble(feeVal);
									double otherfeeval1=ncForm.getNoEncumDTO().getSelectedItems().size()*Double.parseDouble(otherFeeVal);
									double totalfeeval1=ncForm.getNoEncumDTO().getSelectedItems().size()*total;
									
									request.setAttribute("fee", String.valueOf(feeval1));
									request.setAttribute("otherFee", String.valueOf(otherfeeval1));
									request.setAttribute("totalFee", String.valueOf(totalfeeval1));
									//int size=ncForm.getNoEncumDTO().getSelectedItems().size();
									//String fee1=String.valueOf(size*Integer.parseInt(ncForm.getNoEncumDTO().getFee()));
									//	String other1=String.valueOf(size*Integer.parseInt(ncForm.getNoEncumDTO().getOtherFee()));
									//	String total1=String.valueOf(size*Integer.parseInt(ncForm.getNoEncumDTO().getTotalFee()));
									//ncForm.getNoEncumDTO().setTotalFee();
									//ncForm.getNoEncumDTO().setFee(fee1);
									//ncForm.getNoEncumDTO().setOtherFee(other1);
									//ncForm.getNoEncumDTO().setTotalFee(total1);
									FORWAD_SUCCESS = "CreateScreen";
								
								
								} catch (Exception e) {
								}
							}
						//}
				}
					else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("delFamilyRow"))) {
					try {
						int index = Integer.parseInt(request
								.getParameter("selectedIndex"));
						NoEncumDTO floorDTO = ncForm.getNoEncumDTO();
						ArrayList<FloorDetailsDTO> familyMembers = floorDTO
								.getFloordetails();
						refreshFamilyData(familyMembers, request,
								ncForm.getFloorMasterList());
						familyMembers.remove(index);
						request.setAttribute("noencumfrm", ncForm);
						ArrayList<KhasraDTO> familyMembers1 = floorDTO
								.getKhasraList();
						refreshKhasraData(familyMembers1, request);
					} catch (Exception exception) {
						logger.error(exception.getMessage(), exception);
					}
					FORWAD_SUCCESS = "CreateScreen";
				}

					else if (frwdName != null
							&& (frwdName.equalsIgnoreCase("issuanceRequestUpdate"))) {
						
						String txnid1=request.getParameter("certifiedId");
						NoEncumDTO dto=new NoEncumBD().getDetailsOnId(txnid1,languageLocale);
						dto.setDisDate("");
						//dto.setDispatchType("M");
						ncForm.setNoEncumDTO(dto);
						session.setAttribute("userDetailsDTO",dto);
						FORWAD_SUCCESS = "idClickRequest";
						//return mapping.findForward(FORWAD_SUCCESS);
					}
					else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("StateProp"))) {
					logger.info("Inside State Action");
					sdDTO.setStatePropList(sdBD.statePropBD(sdDTO
							.getPropCountryId()));
					// sdDTO.setDistrictList(null);
					FORWAD_SUCCESS = "CreateScreen";
				} else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("State"))) {
					logger.info("Inside State Action");
					sdDTO.setCountryList(sdBD.countryStackBD(languageLocale));
					sdDTO.setPropCountryList(sdBD.countryPropBD(languageLocale));
					sdDTO.setPhotoIdList(sdBD.photoIdStackBD(languageLocale));
					sdDTO.setCasteList(sdBD.casteStackBD(languageLocale));
					sdDTO.setReligionList(sdBD.religionStackBD(languageLocale));
					sdDTO.setPropertyList(sdBD.propertyBD(languageLocale));
					//sdDTO.setMunicipaltyList(sdBD.getMunicipalList());
					//sdDTO.setMunicipaltyList(new ArrayList());
					sdDTO.setAreaTypeList(sdBD.getAreaTypeList(languageLocale));
					sdDTO.setStateList(sdBD.stateStackBD(sdDTO.getCountryId(),languageLocale));
					// sdDTO.setDistrictList(null);
					FORWAD_SUCCESS = "CreateScreen";
				} else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("District"))) {
					logger.info("Inside District Action");
					sdDTO.setDistrictList(sdBD.districtStackBD(sdDTO
							.getStateId(),languageLocale));
					FORWAD_SUCCESS = "CreateScreen";
				} else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("DistrictProp"))) {
					logger.info("Inside District Action");
					/*sdDTO.setPropDistrictList(sdBD.districtPropBD(sdDTO
							.getStatePropId()));*/
					sdDTO.setPropDistrictList(sdBD.districtPropBD("1",languageLocale));
					FORWAD_SUCCESS = "CreateScreen";
				} else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("TehesilProp"))) {
					logger.info("Inside District Action");
					sdDTO.setPropTehesilList(sdBD.tehesilPropBD(sdDTO
							.getPropDistrictId(),languageLocale));
					FORWAD_SUCCESS = "CreateScreen";
				} else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("PatwariProp"))) {
					
					
					logger.info("Inside District Action");
					/*sdDTO.setPropPatwariList(sdBD.patwariPropBD(sdDTO
							.getPropTehesilId()));*/
					
					ArrayList wardList=newPropBO.getWardPatwari(languageLocale,sdDTO.getMunicipaltyId(),sdDTO.getPropTehesilName());
					if(wardList!=null){
						sdDTO.setPropPatwariList(wardList);
					}else{
						sdDTO.setPropPatwariList(new ArrayList());
					}
					
					
					FORWAD_SUCCESS = "CreateScreen";
					
					
				} else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("GramProp"))) {
					
					
					logger.info("Inside Gram Action");
					/*sdDTO.setPropGramList(sdBD.gramPropBD(sdDTO
							.getPropPatwariId()));*/
					
					ArrayList mohallaList=newPropBO.getColonyName(languageLocale,sdDTO.getPropPatwariId());
					if(mohallaList!=null){
						sdDTO.setPropGramList(mohallaList);
					}else{
						sdDTO.setPropGramList(new ArrayList());
					}
					
					
					FORWAD_SUCCESS = "CreateScreen";
					
					
				} else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("MunicipalBodyProp"))) {
					logger.info("Inside MunicipalBodyProp Action");
					
					
					String urbanFlag="N";
					if("2".equalsIgnoreCase(sdDTO.getAreaTypeId())){
						urbanFlag="Y";
					}
					//eForm.setMunicipalList(newPropBO.getSubArea(languageLocale,eForm.getRegcompletDto().getAreaTypeId()));
					
					ArrayList municipalList=newPropBO.getSubArea(languageLocale,sdDTO.getAreaTypeId(),
							sdDTO.getPropTehesilName(),urbanFlag);
					if(municipalList!=null){
						sdDTO.setMunicipaltyList(municipalList);
					}else{
						sdDTO.setMunicipaltyList(new ArrayList());
					}
					
					
					
					
					
					FORWAD_SUCCESS = "CreateScreen";
				}
				else if("setSelectedPropIDs".equals(actionType)) {
	            	
	            	ArrayList searchResultList = ncForm.getSearchResultList();
	            	String[] selectedIndexes = request.getParameterValues("chkProperty");
	            	ArrayList selectedItems = null;
	            	if(selectedIndexes != null && selectedIndexes.length>0) {
	            		selectedItems = new ArrayList();
						for (String selectedIndex : selectedIndexes) {
							int index = Integer.parseInt(selectedIndex);
							selectedItems.add(searchResultList.get(index-1));
						}
						ncForm.setSearchResultList(null);
						sdDTO.setCloneSelectedItems(selectedItems);
	            		//caveatDto.setSelectedItems(selectedItems);
	            		session.setAttribute("cvtSearchUpdate","yes");
	            	} else {
	            		request.setAttribute("error", "Please select at least one Property");
	            	}
	            	
	            	sdDTO.setErrorMsg("FLAG");
					FORWAD_SUCCESS = "PopupScreen";
	            }
				else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("regIdSearchNew"))) {
					sdDTO.setRegNoSearch("");
					ncForm.setSearchResultList(null);
					sdDTO.setCloneSelectedItems(null);
					request.removeAttribute("Enc_Reg_View_Details");
					FORWAD_SUCCESS = "PopupScreen";

				} else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("regIdSearch"))) {

					request.removeAttribute("Enc_Reg_View_Details");
					sdDTO = ncForm.getNoEncumDTO();
					ArrayList regis_view_details = sdBD.regIdCheckInfo(sdDTO
							.getRegNoSearch(),language);
					ncForm.setSearchResultList(regis_view_details );
					request.setAttribute("Enc_Reg_View_Details",
							regis_view_details);
					FORWAD_SUCCESS = "PopupScreen";

				} else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("SearchUser"))) {
					logger.info("Inside RegIDSearch Action");
					sdDTO.setUseIdFlag("Look");
					NoEncumDTO ndto = ncForm.getNoEncumDTO();
					ndto = sdBD.userIdCheckBD(sdDTO.getRegUserId());
					ndto.setRegNo(sdDTO.getRegNoSearch());
					ndto.setRegDate(sdDTO.getCreatedDate());
					if (ndto != null) {
						ncForm.setNoEncumDTO(ndto);
						session.setAttribute("userDetailsDTO", ndto);// Session
																		// for
																		// Existing
																		// user
																		// details
																		// to be
																		// inserted
					}
					FORWAD_SUCCESS = "CreateScreen";
				} else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("confirmDetails1"))) {
					logger.info("Inside confirmDetails Action");
					sdDTO = (NoEncumDTO) session.getAttribute("userDetailsDTO");
					sdDTO.setRegNo(ncForm.getNoEncumDTO().getRegNo());
					
					// Starting of Service Fee matrix
					/*
					 * ArrayList list=sdBD.getOthersFeeDuty(funId, null,
					 * userId); if(!list.isEmpty()) {
					 * sdDTO.setServiceFee((String)list.get(0));
					 * sdDTO.setOtherFee((String)list.get(1));
					 * sdDTO.setTotalFee((String)list.get(2));
					 * session.setAttribute("Fee", list.get(0));
					 * session.setAttribute("Other_Fee", list.get(1));
					 * session.setAttribute("Total_Fee", list.get(2));
					 * session.setAttribute("amount",list.get(2)); }
					 */

					// End of Service Fee Matrix

					ncForm.setNoEncumDTO(sdDTO);
					FORWAD_SUCCESS = "ConfirmScreen";
				} 
				else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("issuanceUpdate"))) {
					String txnid1=request.getParameter("certifiedId");
					NoEncumDTO dto=new NoEncumBD().getDetailsOnId(txnid1,languageLocale);
					ncForm.setNoEncumDTO(dto);
					session.setAttribute("userDetailsDTO",dto);
					FORWAD_SUCCESS = "idClickCopy";
					return mapping.findForward(FORWAD_SUCCESS);	
					
				}
				
				else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("confirmDetails2"))) {
					// Starting of Service Fee matrix
					session.removeAttribute("userDetailsDTO");
					if ("1".equals(sdDTO.getPropertyName()))// plot condition
															// added on 19APR13
					{
						sdDTO.setAgViKhand("");
						sdDTO.setBuildViKhand("");
						// sdDTO.setFloordetails(null);
					}
					if ("2".equals(sdDTO.getPropertyName())) // Building
																// condition
																// added on
																// 19APR13
					{
						sdDTO.setPltViKhand("");
						sdDTO.setAgViKhand("");
					}
					if ("3".equals(sdDTO.getPropertyName()))// Agriculture
															// condition added
															// on 19APR13
					{
						sdDTO.setBuildViKhand("");
						// sdDTO.setFloordetails(null);
						sdDTO.setPltViKhand("");
					}
					
					sdDTO.setShowpCountyName(sdBD.getPropCountryName(sdDTO.getPropCountryId(),languageLocale));
					sdDTO.setShowpStateName(sdBD.getPropStateName(sdDTO.getStatePropId(),languageLocale));
					sdDTO.setShowpDistName(sdBD.getPropDistrictName(sdDTO.getPropDistrictId(),languageLocale));
					sdDTO.setShowpTehesilName(sdBD.getPropTehsilName(sdDTO.getPropTehesilName(),languageLocale));
					sdDTO.setShowpPatwariName(sdBD.getPropPatwariName(sdDTO.getPropPatwariId().split("~")[0],languageLocale));
					sdDTO.setShowpGramName(sdBD.getPropGramName(sdDTO.getPropGramName().split("~")[0],languageLocale));
					sdDTO.setShowpMuncpaltyName(sdBD.getPropMCDName(sdDTO.getMunicipaltyId(),languageLocale));
					
					if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					if(sdDTO.getAreaType().equalsIgnoreCase("1"))
					{
						sdDTO.setShowpUrbanName("RURAL AREA");
					}
					else
					{
						sdDTO.setShowpUrbanName("URBAN AREA");
					}
					}else{

						if(sdDTO.getAreaType().equalsIgnoreCase("1"))
						{
							sdDTO.setShowpUrbanName("ग्राम क्षेत्र");
						}
						else
						{
							sdDTO.setShowpUrbanName("नगरीय क्षेत्र");
						}
						
					}
					NoEncumDTO floorDTO = ncForm.getNoEncumDTO();
					ArrayList<FloorDetailsDTO> familyMembers = floorDTO
							.getFloordetails();
					refreshFamilyData(familyMembers, request,
							ncForm.getFloorMasterList());
					ArrayList<KhasraDTO> familyMembers1 = floorDTO
							.getKhasraList();
					refreshKhasraData(familyMembers1, request);
					
					ArrayList list = sdBD.getOthersFeeDuty(funId, null, userId);
					/*
					 * if(!list.isEmpty()) {
					 * sdDTO.setServiceFee((String)list.get(0));
					 * sdDTO.setOtherFee((String)list.get(1));
					 * sdDTO.setTotalFee((String)list.get(2));
					 * session.setAttribute("Fee", list.get(0));
					 * session.setAttribute("Other_Fee", list.get(1));
					 * session.setAttribute("Total_Fee", list.get(2));
					 * session.setAttribute("amount",list.get(2)); }
					 */

					// End of Service Fee Matrix
					String gender = sdDTO.getGender();
					if ("M".equalsIgnoreCase(gender)) {
						gender = "Male";
					} else {
						gender = "Female";
					}
					sdDTO.setViewGender(gender);
					if(sdDTO.getTxnId()==null||sdDTO.getTxnId().equalsIgnoreCase(""))
					{
						String txnid = sdBD.insertNoEncumDetailsBD(sdDTO,languageLocale);
						sdDTO.setTxnId(txnid);
					}
					else
					{
						sdBD.deleteDetails(sdDTO.getTxnId());
						String txnid = sdBD.insertNoEncumDetailsBD(sdDTO,languageLocale);
						sdDTO.setTxnId(txnid);
					}
					ncForm.setNoEncumDTO(sdDTO);
					session.setAttribute("userDetailsDTO", sdDTO);// Session for
																	// New User
																	// details
					//String txnid = sdBD.insertNoEncumDetailsBD(sdDTO);												// to be
					
					FORWAD_SUCCESS = "ConfirmScreen";
				} else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("modifyDetails"))) {
					logger.info("Inside modifyDetails Action");
					// ----------Setting all Master
					ncForm.setNoEncumDTO(sdDTO);
					sdDTO.setCountryList(sdBD.countryStackBD(languageLocale));
					sdDTO.setStateList(sdBD.stateStackBD(sdDTO.getCountryId(),languageLocale));
					sdDTO.setDistrictList(sdBD.districtStackBD(sdDTO
							.getStateId(),languageLocale));
					sdDTO.setPhotoIdList(sdBD.photoIdStackBD(languageLocale));
					sdDTO.setCasteList(sdBD.casteStackBD(languageLocale));
					sdDTO.setReligionList(sdBD.religionStackBD(languageLocale));
					//sdDTO.setPropCountryList(sdBD.countryPropBD());
					
					//sdDTO.setStatePropList(sdBD.statePropBD(sdDTO
					//		.getPropCountryId()));
					/*sdDTO.setPropDistrictList(sdBD.districtPropBD(sdDTO
							.getStatePropId()));*/
					//sdDTO.setPropDistrictList(sdBD.districtPropBD("1"));
					//sdDTO.setPropTehesilList(sdBD.tehesilPropBD(sdDTO
					//		.getPropDistrictId()));
					//sdDTO.setPropPatwariList(sdBD.patwariPropBD(sdDTO
					//		.getPropTehesilId()));
					//sdDTO.setPropGramList(sdBD.gramPropBD(sdDTO
					//		.getPropPatwariId()));
					//sdDTO.setPropertyList(sdBD.propertyBD());
					// -----------End of All
					FORWAD_SUCCESS = "CreateScreen";
					
				}
				/*
				 * else
				 * if(frwdName!=null&&(frwdName.equalsIgnoreCase("insertDetails"
				 * ))) { logger.info("Inside insertDetails Action");
				 * sdDTO=(NoEncumDTO)session.getAttribute("userDetailsDTO");
				 * sdDTO.setPayTxnId((String)session.getAttribute("status"));
				 * String txnid=sdBD.insertNoEncumDetailsBD(sdDTO);
				 * if(!(txnid.equalsIgnoreCase("")||txnid==null)) {
				 * sdDTO.setCerCopyTxnId(txnid); fm.setNoEncumDTO(sdDTO);
				 * FORWAD_SUCCESS="SubmitSuccess"; } else
				 * FORWAD_SUCCESS="failure"; }
				 */
				if ("P".equalsIgnoreCase(pymntParamStatus)) {
					// CertifiedActionForm coForm = (CertifiedActionForm)
					// session.getAttribute("copyForm");
					String succMsg = "";
					if(sdBD.isCompletePayment(sdDTO.getCerCopyTxnId())<=0)
					{
						succMsg = sdBD.updateICopyInfo(sdDTO, pymntParamStatus);
						if ("succ".equalsIgnoreCase(succMsg)) 
						{
							request.setAttribute("refId", sdDTO.getCerCopyTxnId());
							// request.setAttribute("copyForm",coForm);
							FORWAD_SUCCESS = "SubmitSuccess";
						}
						else 
						{
							// String msg="Payment status could not updated.";
							// sdDTO.setCopyIssuanceSuccessAction("");
							// request.setAttribute("CopyStatus", msg);
							FORWAD_SUCCESS = "failure";
						}
					}
					else
					{
						request.setAttribute("refId", sdDTO.getCerCopyTxnId());
						// request.setAttribute("copyForm",coForm);
						FORWAD_SUCCESS = "SubmitSuccess";
					}
						
					/*if ("succ".equalsIgnoreCase(succMsg)) {
						request.setAttribute("refId", sdDTO.getCerCopyTxnId());
						// request.setAttribute("copyForm",coForm);
						FORWAD_SUCCESS = "SubmitSuccess";
					} else {
						// String msg="Payment status could not updated.";
						// sdDTO.setCopyIssuanceSuccessAction("");
						// request.setAttribute("CopyStatus", msg);
						FORWAD_SUCCESS = "failure";
					}*/
				} 
				 else if (frwdName != null
							&& (frwdName.equalsIgnoreCase("initiatePartialPayment"))) {
					 
					 sdDTO = (NoEncumDTO) session.getAttribute("userDetailsDTO");
					 if(Double.parseDouble(sdDTO.getBalanceAmount())>0)
					 {
						 	NoEncumDTO ncerDTO = ncForm.getNoEncumDTO();
							ncerDTO = sdBD.getFunctionName(funId,languageLocale);
							String parentkey=sdBD.setPaymentDetails(sdDTO,funId,userId);
							// Ramesh added attributes for payment integration
							// on 15 Feb 13
							String office_id1="";
				            String office_name1="";
				            String district_id1="";
				            String district_name1="";
							if(sdDTO.getRegNo()!=null&&!sdDTO.getRegNo().equalsIgnoreCase(""))
							{
									
								ArrayList parameter=sdBD.getPaymentParameter(sdDTO.getRegNo(),languageLocale);
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
							}
							else
							{
								if(sdBD.isDRO(officeId))
								{
									office_id1=officeId;
									office_name1=sdBD.getofficeName(officeId,languageLocale);
									district_name1=sdDTO.getShowpDistName();
									district_id1=sdDTO.getPropDistrictId();
								}
								else
								{
									district_name1=sdDTO.getShowpDistName();
									district_id1=sdDTO.getPropDistrictId();
									office_id1="NA";
									office_name1="NA";
								}
							}
							request.setAttribute("forwardPath",
									"./NoEncumAction.do?TRFS=NGI");
							request.setAttribute("parentTable","IGRS_CERT_COPY_PAYMENT_DTLS");
							request.setAttribute("parentAmount",
									String.valueOf(sdDTO.getBalanceAmount()));
							request.setAttribute("parentFunId", funId);
							request.setAttribute("parentKey", parentkey);
							request.setAttribute("parentModName",
									ncerDTO.getParentModName());
							request.setAttribute("parentFunName",
									ncerDTO.getParentFunName());
							request.setAttribute("parentColumnName","CERT_PAYMENT_ID");
							sdDTO.setCerCopyTxnId(sdDTO.getTxnId());
							ncForm.setNoEncumDTO(sdDTO);
							request.setAttribute("parentOfficeId", office_id1);

			 	           	request.setAttribute("parentOfficeName", office_name1);

			 	           	request.setAttribute("parentDistrictId", district_id1);

			               	request.setAttribute("parentDistrictName", district_name1);
			               	request.setAttribute("parentReferenceId",sdDTO.getTxnId());
							request.setAttribute("refId",sdDTO.getTxnId());
				 	          request.setAttribute("copyForm",ncForm);
				 	          request.setAttribute("formName","noencumfrm");

				 	          
				 	          FORWAD_SUCCESS = "proceedPayment";
					}
				 
					 else {
							String succMsg = "";
							succMsg = sdBD.updatePaymentInfo(sdDTO);
							if ("succ".equalsIgnoreCase(succMsg)) {
								sdDTO.setCerCopyTxnId(sdDTO.getTxnId());
								ncForm.setNoEncumDTO(sdDTO);
								FORWAD_SUCCESS = "SubmitSuccess";
							} else {
								// String
								// msg="Payment status could not updated.";
								// sdDTO.setCopyIssuanceSuccessAction("");
								// request.setAttribute("CopyStatus", msg);
								FORWAD_SUCCESS = "failure";
							}
					 }
				 
				 }
				else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("initiatePayment"))) {
					logger.info("Inside insertDetails Action");
					sdDTO = (NoEncumDTO) session.getAttribute("userDetailsDTO");
					//sdDTO.setPayTxnId((String) session.getAttribute("status"));
					String txnid = sdDTO.getTxnId();
					double totalFee = Double.parseDouble(sdDTO.getTotalFee());
					sdDTO.setBalanceAmount(sdDTO.getTotalFee());
					// double totalFee=0.0;
					if (!(txnid.equalsIgnoreCase("") || txnid == null)) {
						if (totalFee > 0) {
							NoEncumDTO ncerDTO = ncForm.getNoEncumDTO();
							ncerDTO = sdBD.getFunctionName(funId,languageLocale);
							String parentkey=sdBD.setPaymentDetails(sdDTO,funId,userId);
							// Ramesh added attributes for payment integration
							// on 15 Feb 13
							String office_id1="";
				            String office_name1="";
				            String district_id1="";
				            String district_name1="";
							if(sdDTO.getRegNo()!=null&&!sdDTO.getRegNo().equalsIgnoreCase(""))
							{
									
								ArrayList parameter=sdBD.getPaymentParameter(sdDTO.getRegNo(),languageLocale);
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
							}
							else
							{
								if(sdBD.isDRO(officeId))
								{
									office_id1=officeId;
									office_name1=sdBD.getofficeName(officeId,languageLocale);
									district_name1=sdDTO.getShowpDistName();
									district_id1=sdDTO.getPropDistrictId();
								}
								else
								{
									district_name1=sdDTO.getShowpDistName();
									district_id1=sdDTO.getPropDistrictId();
									office_id1="NA";
									office_name1="NA";
								}
							}
							request.setAttribute("forwardPath",
									"./NoEncumAction.do?TRFS=NGI");
							request.setAttribute("parentTable","IGRS_CERT_COPY_PAYMENT_DTLS");
							request.setAttribute("parentAmount",
									String.valueOf(sdDTO.getTotalFee()));
							request.setAttribute("parentFunId", funId);
							request.setAttribute("parentKey", parentkey);
							request.setAttribute("parentModName",
									ncerDTO.getParentModName());
							request.setAttribute("parentFunName",
									ncerDTO.getParentFunName());
							request.setAttribute("parentColumnName","CERT_PAYMENT_ID");
							request.setAttribute("parentOfficeId", office_id1);

			 	           	request.setAttribute("parentOfficeName", office_name1);

			 	           	request.setAttribute("parentDistrictId", district_id1);

			               	request.setAttribute("parentDistrictName", district_name1);
			               	request.setAttribute("parentReferenceId",sdDTO.getTxnId());
							request.setAttribute("refId",sdDTO.getTxnId());
							
							sdDTO.setCerCopyTxnId(txnid);
							ncForm.setNoEncumDTO(sdDTO);
							 request.setAttribute("refId",txnid);
				 	          request.setAttribute("copyForm",ncForm);
				 	          request.setAttribute("formName","noencumfrm");

							FORWAD_SUCCESS = "proceedPayment";
						}

						else {
							String succMsg = "";
							succMsg = sdBD.updatePaymentInfo(sdDTO);
							if ("succ".equalsIgnoreCase(succMsg)) {
								sdDTO.setCerCopyTxnId(txnid);
								ncForm.setNoEncumDTO(sdDTO);
								FORWAD_SUCCESS = "SubmitSuccess";
							} else {
								// String
								// msg="Payment status could not updated.";
								// sdDTO.setCopyIssuanceSuccessAction("");
								// request.setAttribute("CopyStatus", msg);
								FORWAD_SUCCESS = "failure";
							}

						}

					} else {
						FORWAD_SUCCESS = "failure"; // /commented on 04April 13
					}
				}

				else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("clearSelectedItems")))
				{
					
					request.setAttribute("fee", feeVal);
					request.setAttribute("otherFee", otherFeeVal);
					request.setAttribute("totalFee", String.valueOf(total));
					ncForm.getNoEncumDTO().setSelectedItems(null);
					FORWAD_SUCCESS = "CreateScreen";
				}
				else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("downloadCopy")))
				{
					if(true)//sdBD.UpdateDownloadStatus(ncForm.getNoEncumDTO()))
					{	//code for pdf generation to be written
						//PropertiesFileReader pr;
						try {
							//pr = PropertiesFileReader.getInstance("resources.igrs");
						
						DocumentOperations docOperations = new DocumentOperations();
				        ODServerDetails connDetails = new ODServerDetails();
				        Dataclass metaDataInfo = new Dataclass();
				      
				        
				        connDetails.setAppServerIp(pr.getValue("AppServerIp"));
				        connDetails.setCabinetName(pr.getValue("CabinetName"));
				        connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
				        connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
				        connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
				        connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
				        metaDataInfo.setUniqueNo(ncForm.getNoEncumDTO().getTxnId());
				        metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
				        ////modified by shruti---20 march 2014--to maintain different folders for new/remnewed license
				     /*   if(objServiceProviderForm.getObjServiceProviderDTO().getNewOrRenewalFlag().toString().equalsIgnoreCase("New")) 
				        {
				        metaDataInfo.setType("Granted");
				        }
				        else
				        {
				        metaDataInfo.setType("Renewed");
				        }*/
				        PropertiesFileReader pr1 = PropertiesFileReader.getInstance("resources.igrs");
				        String hindiFont=pr1.getValue("dms_hindi_font_path");
						String englishFont=pr1.getValue("dms_english_font_path");
				        BurningImageAndText burnObj = new BurningImageAndText(hindiFont,englishFont);
				        Encumbrance encumbObjc = new Encumbrance();				        
				        /** For English **/
				        //spObj.setSpPhotoLocation("C:\\Users\\SHRUTI\\Desktop\\wallpapers\\photo.jpg");
				        String parentOfficeId="NA";
						String parentOfficeName="NA";
						String parentDistrictId="NA";
						String parentDistrictName="NA";
						String parentTehsilName="NA";
						RegCommonBO commonBo = new RegCommonBO();
				        String[] arr=commonBo.getDistIdNameOfficeNameDRS(session.getAttribute("loggedToOffice").toString());
						parentOfficeId=session.getAttribute("loggedToOffice").toString();
						
						if(arr!=null && arr.length==3){
							
							parentDistrictId=arr[0].trim();
							parentDistrictName=commonBo.getDistrictName(parentDistrictId,languageLocale);
							parentOfficeName=commonBo.getOfficeName(parentOfficeId,languageLocale);
							parentTehsilName=sdBD.getTehsilName(parentOfficeId,languageLocale);
						}
						
				        
				        
				        encumbObjc.setSubRegistrarOffName(parentOfficeName);
				        encumbObjc.setSubDistrictName(parentTehsilName);
				        encumbObjc.setDistrictName(parentDistrictName);
				        //encumbObjc.setCertNo("NEC120420146576879");  sdDTO
				        encumbObjc.setCertNo(sdDTO.getTxnId());
				        encumbObjc.setAddresseName(sdDTO.getFname()+" "+sdDTO.getLname()+", \n "+sdDTO.getAddress());
				        if("en".equalsIgnoreCase(language))
				        {	
				        encumbObjc.setSubject("Regarding Issuance of No-Encumbrance Certificate.");
				        }
				        else
				        {
					     encumbObjc.setSubject("भार मुक्त प्रमाण पत्र जारी करने संबंध में");
				        }
				        encumbObjc.setReferenceDate(sdBD.getNEDate(sdDTO.getTxnId()));//date of ne cert geneartion.
				        if(sdDTO.getRegNo()!=null && !sdDTO.getRegNo().equalsIgnoreCase("")){
				        	String propId="";
				        	ArrayList list=sdDTO.getSelectedItems();
				        	for(int i=0;i<list.size();i++)
				        	{
				        		NoEncumDTO dto= (NoEncumDTO) list.get(0);
				        		propId=dto.getPropertyTxnId();
				        	}
				        	
				        	encumbObjc.setRegistrationNo(sdDTO.getRegNo());
				        	encumbObjc.setRegistrationDate(sdBD.getRegDate(sdDTO.getRegNo()));
				        	sdBD.PdfDetails(propId, languageLocale, encumbObjc,ncForm);
				        	
				        	/*encumbObjc.setPropertyLocation("Bhopal");
					        encumbObjc.setPropertyDetails("not clear");
				
					        encumbObjc.setPropertyAreaEast("road");
					        encumbObjc.setPropertyAreaWest("park");
					        encumbObjc.setPropertyAreaNorth("highway");
					        encumbObjc.setPropertyAreaSouth("-");*/
					        
				        }else{
				        	encumbObjc.setRegistrationNo("NA");
				        	encumbObjc.setRegistrationDate("NA");
				        	encumbObjc.setPropertyLocation(sdDTO.getShowpDistName());
					        String propDetails=sdDTO.getShowPropName();
					        if(sdDTO.getPropertyName().equalsIgnoreCase("1")){
					        encumbObjc.setPropertyAreaEast(sdDTO.getPltEastboundryDtls());
					        encumbObjc.setPropertyAreaWest(sdDTO.getPltWestboundryDtls());
					        encumbObjc.setPropertyAreaNorth(sdDTO.getPltNorthboundryDtls());
					        encumbObjc.setPropertyAreaSouth(sdDTO.getPltSouthboundryDtls());
					       
					        if("en".equalsIgnoreCase(language))
					        {
					        	String propertyDetails="PLOT"+" "+sdDTO.getPltPlotNo()+" "+sdDTO.getPltLayout()+" "+sdDTO.getShowpGramName()+
					        	" "+sdDTO.getShowpMuncpaltyName()+" " + sdDTO.getShowpPatwariName()+" "+sdDTO.getShowpUrbanName()+" "+sdDTO.getShowpTehesilName()
					        	+",Area-"+sdDTO.getPltArea()+" sqm";
					        	encumbObjc.setPropertyDetails(propertyDetails);
					        }
					        else
					        {
					        	String propertyDetails="भूखंड"+" "+sdDTO.getPltPlotNo()+" "+sdDTO.getPltLayout()+" "+sdDTO.getShowpGramName()+
					        	" "+sdDTO.getShowpMuncpaltyName()+" " + sdDTO.getShowpPatwariName()+" "+sdDTO.getShowpUrbanName()+" "+sdDTO.getShowpTehesilName()
					        	+",क्षेत्रफल-"+sdDTO.getPltArea()+" वर्गमीटर";
					        	 encumbObjc.setPropertyDetails(propertyDetails);
					        } 
					        
					        
					        
					        
					       
					        }else if(sdDTO.getPropertyName().equalsIgnoreCase("2")){
					        	encumbObjc.setPropertyAreaEast(sdDTO.getBuildEastboundryDtls());
						        encumbObjc.setPropertyAreaWest(sdDTO.getBuildWestboundryDtls());
						        encumbObjc.setPropertyAreaNorth(sdDTO.getBuildNorthboundryDtls());
						        encumbObjc.setPropertyAreaSouth(sdDTO.getBuildSouthboundryDtls());
						        encumbObjc.setPropertyDetails("not clear");
						        if("en".equalsIgnoreCase(language))
						        {
						        	String propertyDetails="Building"+" "+sdDTO.getBuildPlotNo()+" "+sdDTO.getBuildLayout()+" "+sdDTO.getShowpGramName()+
						        	" "+sdDTO.getShowpMuncpaltyName()+" " + sdDTO.getShowpPatwariName()+" "+sdDTO.getShowpUrbanName()+" "+sdDTO.getShowpTehesilName()
						        	+",Area-"+sdDTO.getPltArea()+" sqm";
						        	encumbObjc.setPropertyDetails(propertyDetails);
						        }
						        else
						        {
						        	String propertyDetails="भवन"+" "+sdDTO.getBuildPlotNo()+" "+sdDTO.getBuildLayout()+" "+sdDTO.getShowpGramName()+
						        	" "+sdDTO.getShowpMuncpaltyName()+" " + sdDTO.getShowpPatwariName()+" "+sdDTO.getShowpUrbanName()+" "+sdDTO.getShowpTehesilName()
						        	+",क्षेत्रफल-"+sdDTO.getPltArea()+" वर्गमीटर";
						        	 encumbObjc.setPropertyDetails(propertyDetails);
						        } 
					        }else if(sdDTO.getPropertyName().equalsIgnoreCase("3")){
					        	encumbObjc.setPropertyAreaEast(sdDTO.getAgEastboundryDtls());
						        encumbObjc.setPropertyAreaWest(sdDTO.getAgWestboundryDtls());
						        encumbObjc.setPropertyAreaNorth(sdDTO.getAgNorthboundryDtls());
						        encumbObjc.setPropertyAreaSouth(sdDTO.getAgSouthboundryDtls());
						        encumbObjc.setPropertyDetails("not clear");
						        if("en".equalsIgnoreCase(language))
						        {
						        	String propertyDetails="Aricultural land "+sdDTO.getAgLayoutDtls()+" "+sdDTO.getShowpGramName()+
						        	" "+sdDTO.getShowpMuncpaltyName()+" " + sdDTO.getShowpPatwariName()+" "+sdDTO.getShowpUrbanName()+" "+sdDTO.getShowpTehesilName()
						        	+",Area-"+sdDTO.getAgArea()+" hectare";
						        	encumbObjc.setPropertyDetails(propertyDetails);
						        }
						        else
						        {
						        	String propertyDetails="कृषि भूमि "+sdDTO.getAgLayoutDtls()+" "+sdDTO.getShowpGramName()+
						        	" "+sdDTO.getShowpMuncpaltyName()+" " + sdDTO.getShowpPatwariName()+" "+sdDTO.getShowpUrbanName()+" "+sdDTO.getShowpTehesilName()
						        	+",क्षेत्रफल-"+sdDTO.getAgArea()+" हेक्टेयर";
						        	 encumbObjc.setPropertyDetails(propertyDetails);
						        } 
					        }
				        }
				      
				        encumbObjc.setPropertyStartDate(sdDTO.getFromDate()); // from date
				        encumbObjc.setPropertyEndDate(sdDTO.getToDate());   //to date
				        encumbObjc.setOldRegistrationNo("MC10334FG4454");
				        encumbObjc.setBookNo("BK1234");
				        encumbObjc.setBookStartPageNo("32");
				        encumbObjc.setBookEndPageNo("45");
				        encumbObjc.setTextNo("4");
				        encumbObjc.setTextDate("31/12/2013");
				        
				        if(ncForm.getNoEncumDTO().getRegNo()!=null && !ncForm.getNoEncumDTO().getRegNo().equalsIgnoreCase("")){
				        String regTxnId=objEncumBO.getRegTxn(ncForm.getNoEncumDTO().getRegNo());
			    		String[] deedInstArray=commonBo.getDeedInstId(regTxnId);
				        
				        
				        encumbObjc.setTextKind(commonBo.getDeedName(deedInstArray[0], languageLocale)+" : "+commonBo.getInstrumentName(deedInstArray[1], languageLocale));  //deed and instrument name
				        }else{
				        	encumbObjc.setTextKind("-");  //deed and instrument name
				        }
				        //----added by satbir kumar-----------
				        
				        encumbObjc.setSubRegistrarSignLoc(objEncumBO.getSignPath(userId));
				        
				        //-----end of addition----------
				        
				        int result = -1;
				        //ADDED BY SHRUTI to create folder for storing sp pdf generated after burning
				        File dir;
				        
				        String folderpath=pr1.getValue("igrs_upload_path");
				        
				        String preFixFolder = folderpath+File.separator+"IGRS"+File.separator+"NoEncum"+File.separator;
						dir = new File(preFixFolder+ncForm.getNoEncumDTO().getTxnId().toString());
						if (dir.exists()) {
							logger.info("file already exists deleting....");
							dir.delete();
						}
						else
							dir.mkdirs();
						//END
						String headerimg=getServlet().getServletContext().getRealPath("")+ "/images/header img.jpg";
						
						String createdFileName = File.separator+"No_Encumbrance.pdf";
				        String outputPath =preFixFolder+ncForm.getNoEncumDTO().getTxnId().toString();
				        String dmsFolderName = "IGRS";
				        if (null==metaDataInfo.getUniqueNo()) {
							throw new Exception();
						}
						if (metaDataInfo.getUniqueNo().equals("")) {
							throw new Exception();
						}
				        if("en".equalsIgnoreCase(language))
				        {	
				        result = burnObj.generateNonEcumCert(connDetails, metaDataInfo, outputPath, headerimg, "English", encumbObjc , "No_Encumbrance.pdf", dmsFolderName);
				        }
				        else
				        {
				        	result = burnObj.generateNonEcumCert(connDetails, metaDataInfo, outputPath, headerimg, "Hindi", encumbObjc , "No_Encumbrance.pdf", dmsFolderName); 	
				        }
				            /** Search  & Download Document on basis of metadata & Name */
				           // ArrayList<DocumentDetails> docList = docOperations.searchDocs(connDetails, metaDataInfo, null);
				           // DocumentDetails docDetails = new DocumentDetails();
				          //  int result1=-1;
				            // DOWNLAOD
				         //   result1 = docOperations.downloadDocument(connDetails, metaDataInfo,preFixFolder+ncForm.getNoEncumDTO().getTxnId().toString()+createdFileName,"General");
				           
				            //added by shruti---20  march 2014
				            if(result==0){
				            String fileName=preFixFolder+ncForm.getNoEncumDTO().getTxnId().toString()+createdFileName;
				            byte[] contents =null;
				            contents=DMSUtility.getDocumentBytes(fileName);
				            DMSUtility.downloadDocument(response, fileName,contents);
				            }else{
				            	FORWAD_SUCCESS = "failure";
								return mapping.findForward(FORWAD_SUCCESS);		
				            }
						} 
				        catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							FORWAD_SUCCESS = "failure";
							return mapping.findForward(FORWAD_SUCCESS);	
						}
				       
						//ncForm.getNoEncumDTO().setPurposeDownload("");
						 if(objEncumBO.UpdateDownloadStatus(sdDTO)){
						FORWAD_SUCCESS = "downsuccess";
						 }else{
							 FORWAD_SUCCESS = "failure"; 
						 }
						return mapping.findForward(FORWAD_SUCCESS);	
					}
				}
				else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("downloadCopy1")))
				{
					 if(objEncumBO.UpdateDownloadStatus(sdDTO)){
							FORWAD_SUCCESS = "downsuccess";
							 }else{
								 FORWAD_SUCCESS = "failure"; 
							 }
							return mapping.findForward(FORWAD_SUCCESS);	
				}
				else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("ViewENC"))) {

					sdDTO.setCerCopyTxnIdSearch("");
					sdDTO.setSearchFromDate("");
					sdDTO.setSearchToDate("");
					sdDTO.setErrorMsg(null);
					request.removeAttribute("docSearchDetails");
					FORWAD_SUCCESS = "SearENCPage";
				} else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("viewDetails"))) {
					logger.info("Inside viewDetails Action");
					ArrayList searchList = new ArrayList();
					searchList = sdBD.searchListViewBD(
							sdDTO.getCerCopyTxnIdSearch(),
							sdDTO.getSearchFromDate(), sdDTO.getSearchToDate());
					if (searchList.isEmpty()) {
						sdDTO.setErrorMsg("No record found .Please try again.");
					} else {
						sdDTO.setErrorMsg(null);
						request.setAttribute("docSearchDetails", searchList);
					}
					FORWAD_SUCCESS = "searchResultScreen";
				} else if (frwdName != null
						&& (frwdName.equalsIgnoreCase("completView"))) {
					logger.info("Inside completView Action");
					String txnId = request.getParameter("cerCopyTxnId");
					sdDTO = sdBD.getDetailsOnId(txnId,languageLocale);
					if (sdDTO != null) {
						ncForm.setNoEncumDTO(sdDTO);
						FORWAD_SUCCESS = "viewScreen";
					} else {
						request.setAttribute("errorMsg",
								"No details available for the request No. ("
										+ txnId + ")");
						FORWAD_SUCCESS = "searchResultScreen";
					}
				}

				
				else if ("viewNEC".equalsIgnoreCase(sdDTO.getViewAction())) {
					FORWAD_SUCCESS = "searchResultScreen";

				}

			} catch (Exception e) {
				logger.error(e);
				return mapping.findForward("failure");
			}
		}
		
		
		 if ("setUploadFile".equalsIgnoreCase(acname)) {
				try {
					FormFile uploadedFile = ncForm.getNoEncumDTO().getAttachedDoc(); //copyform.getIssuanceDTO()
					byte contents[] = uploadedFile.getFileData();
					
					ncForm.getNoEncumDTO().setPhoto(contents); //copyform.getIssuanceDTO()
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
					if (rule.isError()) {
//						clearDoc(cDto);
						errorList1
								.add("Invalid file type. Please select another file./अमान्य फ़ाइल प्रकार. एक और फ़ाइल का चयन करें.");
						request.setAttribute("errorsList", errorList1);
					} else {
						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
//							clearDoc(cDto);
							errorList1
									.add("File size is Greater than 10 MB. Please select another file./फ़ाइल का आकार 10 एमबी से अधिक है. एक और फाइल चुनें");
							request.setAttribute("errorsList", errorList1);
						} else {
							 String docName = "NoEncum_Document."+fileExt;
							 String str = GUIDGenerator.getGUID();
							 String docPath = pr.getValue("igrs_upload_path")+File.separator+"UPLOAD/26/NoEncum"+str;
							 
							  //copyform.getIssuanceDTO()
							 ncForm.getNoEncumDTO().setDocumentName(uploadedFile.getFileName()); //copyform.getIssuanceDTO()
							 ncForm.getNoEncumDTO().setDocContents(uploadedFile.getFileData()); //copyform.getIssuanceDTO()
							 ncForm.getNoEncumDTO().setDocFileSize(getFileSize(uploadedFile.getFileData().length));
							 boolean up=uploadFile(uploadedFile,docName,docPath);
							 docPath=docPath+"/"+docName;
							 ncForm.getNoEncumDTO().setUploaded_doc_path(docPath);
						}
					}
				} catch (Exception e) {
					errorList1.add("Unable to upload file. Please try again./फ़ाइल अपलोड करने में असमर्थ. पुन: प्रयास करें.");
					request.setAttribute("errorsList", errorList1);
				}
				
				FORWAD_SUCCESS = "idClickRequest";  
				
			} 
	     
	     
	     
	     
	      if ("download".equalsIgnoreCase(acname)) {
				try {
					byte[] content = ncForm.getNoEncumDTO().getPhoto();
					String filename =  ncForm.getNoEncumDTO().getDocumentName();
					if(content != null) {
						CaveatsViewSearchAction.downloadDocument(response, content, filename);
					}
					acname=null;
					FORWAD_SUCCESS = "idClickRequest";
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
					FORWAD_SUCCESS = CommonConstant.VIEW_DETAILS_SUBMIT;
					
			 
			 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 
			 
			}
		
		logger.info("Country name"+ncForm.getNoEncumDTO().getPropCountryName());
		logger.info("Country Id"+ncForm.getNoEncumDTO().getPropCountryId());
		logger.info("FORWARD SUCCESS -->" + FORWAD_SUCCESS);
		
		return mapping.findForward(FORWAD_SUCCESS);
	}

	private void refreshFamilyData(ArrayList<FloorDetailsDTO> familyMembers,
			HttpServletRequest request, ArrayList<FloorTypeDTO> floorMasterList) {
		try {
			SimpleDateFormat inputFormat = new SimpleDateFormat("ddMMMyyyy");
			SimpleDateFormat displayFormat = new SimpleDateFormat("dd/MM/yyyy");
			inputFormat.setLenient(false);
			displayFormat.setLenient(false);
			String dateString;
			HashMap<String, String> relativeMaster = new HashMap<String, String>(
					floorMasterList.size());
			String[] floorTypeId = request.getParameterValues("floorTypeId");
			String[] buildingSize = request.getParameterValues("buildingSize");
			String[] buildingArea = request.getParameterValues("buildingArea");
			String[] breadth=request.getParameterValues("buildingSizeBreadth");
			if (floorTypeId == null) {
				floorTypeId = new String[familyMembers.size()];
			}
			if (buildingSize == null) {
				buildingSize = new String[familyMembers.size()];
			}
			if (buildingArea == null) {
				buildingArea = new String[familyMembers.size()];
			}
			if (breadth == null) {
				breadth = new String[familyMembers.size()];
			}
			for (FloorTypeDTO relativeTypeDTO : floorMasterList) {
				relativeMaster.put(relativeTypeDTO.getFloorID(),
						relativeTypeDTO.getFloorType());
			}
			for (int iLoop = 0; iLoop < familyMembers.size(); iLoop++) {
				FloorDetailsDTO member = familyMembers.get(iLoop);
				member.setFloorTypeId(floorTypeId[iLoop] != null ? floorTypeId[iLoop]
						.trim() : "");
				member.setFloorLabel(relativeMaster.get(member.getFloorTypeId()));
				member.setBuildingSize(buildingSize[iLoop] != null ? buildingSize[iLoop]
						.trim() : "");
				member.setBuildingArea(buildingArea[iLoop] != null ? buildingArea[iLoop]
						.trim() : "");
				member.setBuildingSizeBreadth(breadth[iLoop] != null ? breadth[iLoop]
						.trim() : "");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	private void refreshKhasraData(ArrayList<KhasraDTO> familyMembers,
			HttpServletRequest request) {
		try {
			/*
			 * SimpleDateFormat inputFormat = new SimpleDateFormat("ddMMMyyyy");
			 * SimpleDateFormat displayFormat = new
			 * SimpleDateFormat("dd/MM/yyyy"); inputFormat.setLenient(false);
			 * displayFormat.setLenient(false); String dateString;
			 */
			String[] buildingSize = request.getParameterValues("khasraNumber");
			String[] buildingArea = request.getParameterValues("rinPustika");
			String[] khasraArea=request.getParameterValues("khasraArea");
			String[] lagaan=request.getParameterValues("lagaan");

			if (buildingSize == null) {
				buildingSize = new String[familyMembers.size()];
			}
			if (khasraArea == null) {
				khasraArea = new String[familyMembers.size()];
			}
			if (lagaan == null) {
				lagaan = new String[familyMembers.size()];
			}
			if (buildingArea == null) {
				buildingArea = new String[familyMembers.size()];
			}

			for (int iLoop = 0; iLoop < familyMembers.size(); iLoop++) {
				KhasraDTO member = familyMembers.get(iLoop);
				member.setKhasraNumber(buildingSize[iLoop] != null ? buildingSize[iLoop]
						.trim() : "");
				member.setRinPustika(buildingArea[iLoop] != null ? buildingArea[iLoop]
						.trim() : "");
				member.setKhasraArea(khasraArea[iLoop] != null ? khasraArea[iLoop]
						.trim() : "");
				member.setLagaan(lagaan[iLoop] != null ? lagaan[iLoop]
						.trim() : "");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
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
