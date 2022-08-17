/**
 * Copyright (c) 2009-10 WIPRO INFOTECH. All Rights Reserved.
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
 *
 * Customer specific copyright notice - NA
 */
package com.wipro.igrs.caveats.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
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

import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.ODServerDetails;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveats.bo.CaveatsBO;
import com.wipro.igrs.caveats.dao.CaveatsDAO;
import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.caveats.form.CaveatsForm;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.common.mime.MIMECheck;
import com.wipro.igrs.estamping.constant.CommonConstant;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.util.GUIDGenerator;
import com.wipro.igrs.util.PropertiesFileReader;
/**
* 
* InitAction.java <br>
* InitAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CaveatCreateAction extends BaseAction {
	
	private Logger logger = (Logger) Logger
			.getLogger(CaveatActionConfirm.class);
	

	/**
	 * This is the main action called from the Struts framework.
	 * 
	 * @param 
	 *            The ActionMapping used to select this instance.
	 * @param form
	 *            The optional ActionForm bean for this request.
	 * @param request
	 *            The HTTP Request we are processing.
	 * @param response
	 *            The HTTP Response we are processing.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws IOException, ServletException, Exception {
		try {
			String language=(String)session.getAttribute("languageLocale");
			String activityid = request.getParameter("ACTID");
			String funId = (String) session.getAttribute("functionId");
			String userId = (String) session.getAttribute("UserId");
			String officeId= (String) session.getAttribute("loggedToOffice");
			CaveatsForm fm = (CaveatsForm) form; 
			CaveatsForm caveatFormBean  =  (CaveatsForm)form;
			fm.setLanguage(language);
			//CaveatsDTO cDto = new CaveatsDTO();
			CaveatsDTO cDto = fm.getCaveatsDTO(); 
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			cDto.setUserId(userId);
			fm.setFunID(funId);
			/*if(activityid!=null)
			{
			IGRSCommon save=null;
			try {
				save = new IGRSCommon();
				save.saveactivitylog(userId, activityid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}*/

			CaveatsBO objCaveatBO = new CaveatsBO();
			
			String FORWAD_SUCCESS = "";
			String actionName = cDto.getActionName();
			CaveatsDAO refDAO = new CaveatsDAO();
			ArrayList errorList = new ArrayList();
			String isMenuClick = request.getParameter("isMenuClick");
			String page=null;
			String transid=null;
			
			
			cDto.setLanguage(language);
			page=request.getParameter("pageName");
			transid=request.getParameter("transactionID");
			
			
			
			 if(null!=page && !"".equalsIgnoreCase(page)){
				actionName = null;
			}
			if(null!=fm && !"".equals(fm) 
					&& null!=fm.getPaymentSeqId() && !"".equalsIgnoreCase(fm.getPaymentSeqId())
					&& "process".equalsIgnoreCase(fm.getActionName())){
				actionName = fm.getActionName();
			}
			if("dashboard".equalsIgnoreCase(page)){
				
				reset(fm);
				
				return mapping.findForward("DashboardScreen");
				
			}
			
			if("sampadadoc".equalsIgnoreCase(actionName)){
				
				if("No".equalsIgnoreCase(cDto.getSampadaDocFlag())){
					
					cDto.setRegistrationNumber("");
					fm.setSearchResultList(null);
					
					String districtDetls1 = objCaveatBO.getDistrictName(officeId, language);
					String districtDetails1 [] = districtDetls1.split(";");
					cDto.setDistrictName(districtDetails1[0]);
					cDto.setDistrictId(districtDetails1[1]);
					cDto.setProtestLogDistrict(districtDetails1[0]);
					//cDto.setProtestLogDistId(districtDetails1[1]);
					
					cDto.setTehsilList(objCaveatBO.getTehsilList(cDto));
	
				}
				
				return mapping.findForward("DashboardScreen");
			}
			 String fwdPage = (String) request.getParameter("fwdName");
			  if (fwdPage != null && "drillDownCount".equalsIgnoreCase(fwdPage)) {
						
						String docNo = (String) request.getParameter("downloadDoc");
						String forwardPage = (String) request.getParameter("searchPage");
						
				if (docNo != null) {	
					
					DocumentOperations docOperations = new DocumentOperations();
					ODServerDetails connDetails = new ODServerDetails();
					logger.debug("value start:here");
					logger.debug(pr.getValue("AppServerIp"));
					logger.debug(pr.getValue("CabinetName"));
					logger.debug(pr.getValue("AppServerUserId"));
					logger.debug(pr.getValue("AppServerUserPass"));
					logger.debug(pr.getValue("ImageServerIP"));
					logger.debug(pr.getValue("ImageServerPort"));
					Dataclass metaDataInfo = new Dataclass();
					connDetails.setAppServerIp(pr.getValue("AppServerIp"));
					connDetails.setCabinetName(pr.getValue("CabinetName"));
					connDetails.setAppServerUserId(pr
							.getValue("AppServerUserId"));
					connDetails.setAppServerUserPass(pr
							.getValue("AppServerUserPass"));
					connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
					connDetails.setImageServerPort(pr
							.getValue("ImageServerPort"));
					connDetails
							.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
					metaDataInfo.setUniqueNo(objCaveatBO.fetchRegTxnId(docNo));
					metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
					metaDataInfo.setType("F");
			        metaDataInfo.setLatestFlag("L");
					//metaDataInfo.setType("R");
					String guid = GUIDGenerator.getGUID();
					String downloadPath = pr.getValue("igrs_upload_path");
					// String EstampPath =
					// CommonConstant.ESTAMP_DOWNLOAD_PATH+estampFormBean.getObjEstampDet().getEcode().toString();
					// String EstampPath =
					// CommonConstant.ESTAMP_DOWNLOAD_PATH_LINUX+guid;
					String EstampPath = downloadPath + File.separator + guid;

					File folder = new File(EstampPath.toString());
					if (!folder.exists()) {
						System.out.println(folder.toString());

						folder.mkdirs();

					}
					String tempPath = "";

					// int result =
					// docOperations.downloadDocument(connDetails,metaDataInfo,CommonConstant.ESTAMP_DOWNLOAD_PATH+estampFormBean.getObjEstampDet().getEcode(),CommonConstant.MODULE_NAME);
					if (null==metaDataInfo.getUniqueNo()) {
						session.setAttribute("checkStatus", "DMSError");
						return new ActionForward("/jsp/login/UserRoleValidationPage.jsp");
					}
					if (metaDataInfo.getUniqueNo().equals("")) {
						session.setAttribute("checkStatus", "DMSError");
						return new ActionForward("/jsp/login/UserRoleValidationPage.jsp");
					}
					int result = docOperations.downloadDocument(connDetails,metaDataInfo, EstampPath.toString(),CommonConstant.MODULE_NAME);
					File[] listOfFiles = folder.listFiles();
					for (int z = 0; z < listOfFiles.length; z++) {
						tempPath = listOfFiles[z].getPath();

					}
					logger.debug("download result---------->" + result);
					 //String filePath=bd.burnRequestIdForPDF( dform.getDsearchdto().getRefId(), tempPath,EstampPath);
					byte bytes[] = DMSUtility.getDocumentBytes(tempPath);
					if (bytes != null) {
						DMSUtility.downloadDocument(response, tempPath, bytes);
					} else {
						//if (language.equalsIgnoreCase("en")){
							ActionMessages message = new ActionMessages();
							message.add("message", new ActionMessage("reg.doc.download.failedMsg"));
							saveMessages(request,message);
						//}	
					}
				}
				if(null!=forwardPage && !"".equalsIgnoreCase(forwardPage) && "protestDashboardDetls".equalsIgnoreCase(forwardPage)){
					return mapping.findForward("protestdashboard");
				}else if(null!=forwardPage && !"".equalsIgnoreCase(forwardPage) && "protestReleaseDetls".equalsIgnoreCase(forwardPage)){
					return mapping.findForward("releasePage");
				}else{
					return mapping.findForward("DashboardScreen");
				}
				
			  }	
			if("regIdSearchResult".equalsIgnoreCase(actionName)){
				
				String data = objCaveatBO.validateRegNo(cDto);
				String cancelFlag = null;
				String nullandVoidFlag = null;
				String data1 [] = new String [2];
				if(null!=data && !"".equalsIgnoreCase(data)){
					data1 = data.split(";");
					
					if(null!=data1[0] && !"".equalsIgnoreCase(data1[0])){
						cancelFlag = data1[0];
					}
					if(null!=data1[1] && !"".equalsIgnoreCase(data1[1])){
						nullandVoidFlag = data1[1];
					}
				}
				
				String districtDetls1 = objCaveatBO.getDistrictName(officeId, language);
				String districtDetails1 [] = districtDetls1.split(";");
				cDto.setDistrictName(districtDetails1[0]);
				cDto.setDistrictId(districtDetails1[1]);
				cDto.setProtestLogDistrict(districtDetails1[0]);
				//cDto.setProtestLogDistId(districtDetails1[1]);
				/*else if(null!=nullandVoidFlag && !"".equalsIgnoreCase(nullandVoidFlag) && "T".equalsIgnoreCase(nullandVoidFlag)){
				ActionMessages message = new ActionMessages();
				message.add("message", new ActionMessage("caveats.reg.nullvoid.validation.message"));
				saveMessages(request,message);
				return mapping.findForward("DashboardScreen");
			   }*/
				if(null!=cancelFlag && !"".equalsIgnoreCase(cancelFlag) && "Y".equalsIgnoreCase(cancelFlag)){
					ActionMessages message = new ActionMessages();
					message.add("message", new ActionMessage("caveats.reg.cancel.validation.message"));
					saveMessages(request,message);
					return mapping.findForward("DashboardScreen");
				}else {
					fm.setSearchResultList(null);
					ArrayList result = null;
					if(null!=cancelFlag && !"".equalsIgnoreCase(cancelFlag) && null!=nullandVoidFlag && !"".equalsIgnoreCase(nullandVoidFlag)){
						 result = objCaveatBO.fetchPropertyTxnIDList(cDto.getRegistrationNumber(),language);
						 
						 if(result.size()>0){
								for(int i =0; i<result.size(); i++){
									CaveatsDTO caveatsDTO = (CaveatsDTO) result.get(i);
									if(null!=caveatsDTO.getPropDistrictId() && !"".equalsIgnoreCase(caveatsDTO.getPropDistrictId())
											&& !(cDto.getDistrictId().equalsIgnoreCase(caveatsDTO.getPropDistrictId()))){
										fm.getCaveatsDTO().setRegistrationNumber("");
										ActionMessages message = new ActionMessages();
										message.add("message", new ActionMessage("caveats.property.district.validation.msg"));
										saveMessages(request,message);
										return mapping.findForward("DashboardScreen");
									}
									
								}
							}
					}
					
					
				if (result != null && result.size() > 0) {
						fm.setSearchResultList(result);
					
				} else {
					    
						 fm.getCaveatsDTO().setRegistrationNumber("");
						ActionMessages message = new ActionMessages();
						
						message.add("message", new ActionMessage("caveats.reg.action.message"));
						saveMessages(request,message);
						
					}
				}
				
					
				return mapping.findForward("DashboardScreen");
			}
	 
			if("selectPropId".equalsIgnoreCase(actionName)){
				cDto.setDistrictId("");
				fm.setSelectedList(null);
				
				Object attr = request.getParameterValues("chkProperty");
				String[] arr = (String[]) attr;
				try {
					if (arr != null) {
						ArrayList selectList = new ArrayList();
						ArrayList resultList = fm.getSearchResultList();
						for (String idx : arr) {
							int index = Integer.parseInt(idx);
							selectList.add(resultList.get(index));
							
						}
						
						if(selectList.size()>1){
							cDto.setMultiRegNumFlag("Yes");
						}else{
							cDto.setMultiRegNumFlag("No");
						}
						fm.setSelectedList(selectList);
						fm.setSearchResultList(null);
					}
				} catch (Exception e) {
				}
				session.setAttribute("caveatfrm", fm);
				cDto.setDistrictMasterList(refDAO.districtStack(cDto
						.getStateId(),language));
				int len = fm.getSelectedList().size();
				if(len>0){
					String serviceFee = objCaveatBO.fetchServiceFeeDetails(funId);
					int serviceFee1 = 0;
					long serviceFee2 = 0;
					if(null!=serviceFee && !"".equalsIgnoreCase(serviceFee)){
						serviceFee1 = Integer.parseInt(serviceFee);
						serviceFee2 = (long) serviceFee1 * len;
						
						String serviceFeeVal = String.valueOf(serviceFee2);
						
						cDto.setPblAmount(serviceFeeVal);
						cDto.setBalanceAmount(serviceFeeVal);
					}
				}
				return mapping.findForward("CreateScreen");
			}
			
			if("fetchAreaList".equalsIgnoreCase(actionName)){
				cDto.setAreaList(objCaveatBO.getAreaList(cDto));
				
				ArrayList<CaveatsDTO> list = cDto.getTehsilList();
				for(int i=0; i<list.size(); i++){
					String tehsilId = list.get(i).getTehsilId();
					String tehsilName = list.get(i).getTehsilName();
					
					if(tehsilId.equalsIgnoreCase(cDto.getTehsilName())){
						cDto.setTehsilNameVal(tehsilName);
					}
				}
				
				return mapping.findForward("DashboardScreen");
			}
			if("fetchSubAreaList".equalsIgnoreCase(actionName)){
				
				    cDto.setAreaId(cDto.getAreaName());
					cDto.setSubAreaList(objCaveatBO.getSubAreaList(cDto));
					
					ArrayList<CaveatsDTO> list = cDto.getAreaList();
					for(int i=0; i<list.size(); i++){
						String areaid = list.get(i).getAreaId();
						String areaName = list.get(i).getAreaName();
						
						if(areaid.equalsIgnoreCase(cDto.getAreaName())){
							cDto.setAreaNameVal(areaName);
						}
					}

				return mapping.findForward("DashboardScreen");
			}
			if("fetchWardList".equalsIgnoreCase(actionName)){
				cDto.setSubAreaId(cDto.getSubAreaName());
				cDto.setTehsilId(cDto.getTehsilName());
				cDto.setWardPatwariList(objCaveatBO.getWardList(cDto));
				
				ArrayList<CaveatsDTO> list = cDto.getSubAreaList();
				for(int i=0; i<list.size(); i++){
					String subareaid = list.get(i).getSubAreaId();
					String subareaName = list.get(i).getSubAreaName();
					
					if(subareaid.equalsIgnoreCase(cDto.getSubAreaName())){
						cDto.setSubAreaNameVal(subareaName);
					}
				}

			
			return mapping.findForward("DashboardScreen");
		  }
			if("fetchColonyList".equalsIgnoreCase(actionName)){
				//cDto.setWardPatwariId(cDto.getWardPatwariName());
				ArrayList<CaveatsDTO> caveatsDto = cDto.getWardPatwariList();
				if(caveatsDto.size()>0){
					for(int i =0; i<caveatsDto.size(); i++){
						String wardId = caveatsDto.get(i).getWardPatwariId();
						if(wardId.equalsIgnoreCase(cDto.getWardPatwariName())){
							cDto.setSubAreaWardMappingId(caveatsDto.get(i).getSubAreaWardMappingId());
						}
					}
					
				}
				cDto.setColonyList(objCaveatBO.getColonyList(cDto));
				
				ArrayList<CaveatsDTO> list = cDto.getWardPatwariList();
				for(int i=0; i<list.size(); i++){
					String wardid = list.get(i).getWardPatwariId();
					String wardName = list.get(i).getWardPatwariName();
					
					if(wardid.equalsIgnoreCase(cDto.getWardPatwariName())){
						cDto.setWardPatwariNameVal(wardName);
					}
				}

			return mapping.findForward("DashboardScreen");
		  }
			
		if("caveatsNextScreen".equalsIgnoreCase(actionName)){
			
			cDto.setDistrictId("");
			cDto.setDistrictName("");
			ArrayList<CaveatsDTO> list = cDto.getColonyList();
			for(int i=0; i<list.size(); i++){
				String colonyId = list.get(i).getColonyId();
				String colonyName = list.get(i).getColonyName();
				
				if(colonyId.equalsIgnoreCase(cDto.getColonyName())){
					cDto.setColonyNameVal(colonyName);
				}
			}
			
			cDto.setDistrictMasterList(refDAO.districtStack(cDto
					.getStateId(),language));
			String serviceFee = objCaveatBO.fetchServiceFeeDetails(funId);
			int serviceFee1 = 0;
			long serviceFee2 = 0;
			if(null!=serviceFee && !"".equalsIgnoreCase(serviceFee)){
				serviceFee1 = Integer.parseInt(serviceFee);
				serviceFee2 = (long) serviceFee1 ;
				
				String serviceFeeVal = String.valueOf(serviceFee2);
				
				cDto.setPblAmount(serviceFeeVal);
				cDto.setBalanceAmount(serviceFeeVal);
			}
			return mapping.findForward("CreateScreen");
		}
		if("caveatDetlsScreen".equalsIgnoreCase(actionName)){
			
			return mapping.findForward("CreateScreen");
		}
		if("submitCaveatsData".equalsIgnoreCase(actionName)){
			
			boolean protesInsertFlag = false;
			
			String districtDetls = objCaveatBO.getDistrictDetls(cDto.getDistrictId(), language);
			if(null!=districtDetls && !"".equalsIgnoreCase(districtDetls)){
				cDto.setProtestCourtDistrict(districtDetls);
			}else{
				cDto.setProtestCourtDistrict("NA");
			}
			
			
			protesInsertFlag = objCaveatBO.insertProtestDetls(fm);
			if(!protesInsertFlag){
				ActionMessages message = new ActionMessages();
				message.add("message", new ActionMessage("caveats_error_msg"));
				saveMessages(request,message);
				return mapping.findForward("CreateScreen");
			}
			
			return mapping.findForward("protestSuccess");
		}
		if("process".equalsIgnoreCase(actionName)){
			String paymentTransId = null;
			if(null!=fm.getPaymentSeqId() && !"".equalsIgnoreCase(fm.getPaymentSeqId())){
				paymentTransId = objCaveatBO.fetchChallanPaySeqId(fm.getPaymentSeqId());
			}
			if(null!=paymentTransId && !"".equalsIgnoreCase(paymentTransId))
			 request.setAttribute("PayTransId", paymentTransId);
			
			return mapping.findForward("challanPaymentSuccess");
		}
		if("payNow".equalsIgnoreCase(actionName)){
			
			RegCommonBO commonBo = new RegCommonBO();
			
			request.setAttribute("parentModName","CAVEATS AND CHARGES");
			request.setAttribute("parentFunName","Protests Charges");
			request.setAttribute("formName", "caveatfrm");
			request.setAttribute("parentFunId", "FUN_016");
			request.setAttribute("parentAmount", cDto.getPblAmount());// CHANGE
			request.setAttribute("parentTable","IGRS_CAVEAT_PAYMENT_DETAILS");
		
			request.setAttribute("forwardPath","./CaveatsCreate.do?TRFS=NGI");
			request.setAttribute("parentColumnName", "TRANSACTION_ID");
			
		
			String userTypeId = objCaveatBO.getUserTypeId(userId);
			String parentOfficeId = "NA";
			String parentOfficeName = "NA";
			String parentDistrictId = "NA";
			String parentDistrictName = "NA";
			//String parentReferenceId = regForm.getHidnRegTxnId();
			
			String parentReferenceId = (String) fm.getCaveatsDTO().getTransactionID();
			
			if (userTypeId
					.equalsIgnoreCase("2")) {

		
				/*parentDistrictId = commonBo.getDistIdEstamp(regForm
						.getHidnRegTxnId());
				parentDistrictName = commonBo.getDistrictName(
						parentDistrictId, languageLocale);*/
				
				String districtDetls = objCaveatBO.getDistrictName(officeId, language);
				String districtDetails [] = districtDetls.split(";");
				parentDistrictName = districtDetails[0];
				parentDistrictId = districtDetails[1];
				// }
				// else{
				// parentDistrictId="NA";
				// parentDistrictName="NA";
				// }

			} else if (userTypeId
					.equalsIgnoreCase("3")
					|| userTypeId
							.equalsIgnoreCase("4")
					|| userTypeId
							.equalsIgnoreCase("7")
					|| userTypeId
							.equalsIgnoreCase("5")) {

				String[] arr = commonBo.getDistIdNameSP(userId);

				if (arr != null && arr.length == 2) {
					parentDistrictId = arr[0].trim();
					parentDistrictName = commonBo.getDistrictName(
							parentDistrictId, language);
				} else {
					parentDistrictId = "NA";
					parentDistrictName = "NA";
				}

			} else if (userTypeId
					.equalsIgnoreCase("I")) {

				String[] arr = commonBo
						.getDistIdNameOfficeNameDRS(session
								.getAttribute("loggedToOffice")
								.toString());
				parentOfficeId = session.getAttribute("loggedToOffice")
						.toString();

				if (arr != null && arr.length == 3) {

					parentDistrictId = arr[0].trim();
					parentDistrictName = commonBo.getDistrictName(parentDistrictId, language);
					parentOfficeName = commonBo.getOfficeName(parentOfficeId, language);
				} else {

					parentDistrictId = "NA";
					parentDistrictName = "NA";
					parentOfficeName = "NA";
				}

			}

			request.setAttribute("parentOfficeId", parentOfficeId);
			request.setAttribute("parentOfficeName", parentOfficeName);
			request.setAttribute("parentDistrictId", parentDistrictId);
			request.setAttribute("parentDistrictName",parentDistrictName);
			request.setAttribute("parentReferenceId",parentReferenceId);

			// end of new payment requirements added on 4th sept 2013

			String paymentType = "3";

			fm.setPaymentType(paymentType);
			
			
				fm.setPaymentSeqId(objCaveatBO.getPaymentTxnIdSeq());
				request.setAttribute("parentKey", fm.getPaymentSeqId());
				// insertion code
				// String
				// param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
				boolean insertStatus = objCaveatBO.insertPaymentDetls(fm, paymentType);
				logger
						.debug("----------------->payment insertion status:-"
								+ insertStatus);
				if (insertStatus){
					return mapping.findForward("reginitPayment");
				}else{
					return mapping.findForward("protestCreationFailed");
					
				}
		
		}
		
		//if ("create".equalsIgnoreCase(request.getParameter("pageName"))) {
			
			 if ("downloadMainDoc".equalsIgnoreCase(actionName)) {
				try {
					
					byte[] content = cDto.getDocContents();
					String filename = cDto.getDocumentName();
					if(content != null) {
						CaveatsViewSearchAction.downloadDocument(response, content, filename);
					}
					return mapping.findForward("CreateScreen");
				} catch (Exception e) {
				}			
			}
	
		 if ("setUploadFile".equalsIgnoreCase(actionName)) {
			 
				try {
					cDto.setDistrictMasterList(refDAO.districtStack(cDto
							.getStateId(),language));
					
					FormFile uploadedFile = cDto.getAttachedDoc();
					
					byte contents[] = uploadedFile.getFileData();
					cDto.setPhoto(contents);
					if ("".equals(uploadedFile.getFileName())) {
//						clearDoc(cDto);
						
						ActionMessages message = new ActionMessages();
						message.add("message", new ActionMessage("caveats.invalid.fileName"));
						saveMessages(request,message);
					}

					MIMECheck mimeCheck = new MIMECheck();
					String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
			        //Boolean mime = mimeCheck.validateMIMEAndJPGFileType(uploadedFile);  // common code to check mime type and validation.
					Boolean mime = validateFileTypePDFAndJPG(fileExt);
					
					long fileSize = 512000;
					 // new code finish.
					int size = uploadedFile.getFileSize();
					if (!mime) {

						ActionMessages message = new ActionMessages();
						message.add("message", new ActionMessage("caveats.invalid.fileType"));
						saveMessages(request,message);
	
					} else {
						//if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
						if (size > fileSize) {
//							clearDoc(cDto);
							ActionMessages message = new ActionMessages();
							message.add("message", new ActionMessage("caveats.invalid.fileSize"));
							saveMessages(request,message);
						} else {
							String uploadPath = pr.getValue("igrs_caveats_upload_path");
							String dateTime = new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
							uploadPath = uploadPath+dateTime+"//";
							String fileName = uploadedFile.getFileName();
							cDto.setUploaded_doc_path(uploadPath);
							cDto.setDocumentName(fileName);
							cDto.setDocContents(uploadedFile.getFileData());
							cDto.setDocFileSize(getFileSize(uploadedFile.getFileData().length));
							File file1 = new File(uploadPath);
							if (!file1.exists()) {
								file1.mkdirs();
					           }
							
							File file2 = new File(uploadPath+fileName);
							OutputStream os  = null;
							try {
								 os = new FileOutputStream(file2);
						         for(int x = 0; x < contents.length ; x++) {
						            os.write( contents[x] ); 
						         }
							} catch (IOException e) {
								
							}finally{
								if (os != null) {
						            try {
						            	os.close();
									} catch (IOException e) {
										e.printStackTrace();
									}
						         }
							}
						}
					}
					return mapping.findForward("CreateScreen");
				} catch (Exception e) {
					ActionMessages message = new ActionMessages();
					message.add("message", new ActionMessage("caveats.invalid.fileUploadErr"));
					saveMessages(request,message);
				}
			} 
		  
		

			if ("viewProperty".equalsIgnoreCase(request.getParameter("pageName"))) {
				
				RegCommonBO commonBo = new RegCommonBO();
				String propertyId=request.getParameter("propertyTxnID");
				String regId=request.getParameter("registrationNo");
				if(propertyId.length()==15){
					propertyId="0"+propertyId;
				}
				String reginitId="";
				if(regId!=null)
				reginitId=objCaveatBO.getReginitId(regId);
				String propval = new CaveatsDAO().getPropVal(regId);
				RegCommonBO bo = new  RegCommonBO();
				RegCommonForm regForm = new RegCommonForm();
				cDto.setAddressOfInsti(propval);
			if(propval.equalsIgnoreCase("Y"))
			{
				regForm.setMapPropertyTransPartyDisp(bo.getPropertyDetsForViewConfirm(reginitId, propertyId,language));
			request.setAttribute("reginit", regForm);	
			FORWAD_SUCCESS = "propertyView";
			}
			else
			{
				regForm.setMapPropertyTransPartyDisp(bo.getPropertyDetsForViewConfirm(reginitId, propertyId, language));	
			request.setAttribute("reginit", regForm);	
			FORWAD_SUCCESS = "propertyView1";
			}
	
			}
			
			
			return mapping.findForward(FORWAD_SUCCESS);
		} catch (Exception e) {
			
			return mapping.findForward("protestCreationFailed");
		}
	}
	/**
	 * 
	 * @param form
	 * @param errorList
	 * @param request
	 * @param currSysDate
	 * @return
	 */


	/**
	 * 
	 * @param dtoRelease
	 */
	@SuppressWarnings("unused")
	private void clearDoc(CaveatsDTO dtoRelease) {
		dtoRelease.setDocumentName(null);
		dtoRelease.setDocContents(null);
	}
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	
	public void reset(CaveatsForm fm){
		//CaveatsForm fm = (CaveatsForm) form; 
		CaveatsDTO cDto = fm.getCaveatsDTO(); 
		cDto.setSampadaDocFlag("");
		cDto.setTehsilName("");
		cDto.setTehsilList(null);
		cDto.setAreaName("");
		cDto.setAreaList(null);
		cDto.setSubAreaName("");
		cDto.setSubAreaList(null);
		cDto.setWardPatwariName("");
		cDto.setWardPatwariList(null);
		cDto.setActionName("");
		cDto.setColonyName("");
		cDto.setColonyList(null);
		fm.setSearchResultList(null);
		fm.setSelectedList(null);
		cDto.setPropertyTypeId("");
		cDto.setPropertyTypeList(null);
		cDto.setPropertyId("");
		cDto.setPropertyTypeName("");
		cDto.setPropertyTxnId("");
		cDto.setBankCourtName("");
		cDto.setBankCourtRepName("");
		cDto.setBankCourtAddress("");
		cDto.setDistrictName("");
		cDto.setBankCourtPostalCode("");
		cDto.setBankCourtPhoneNumber("");
		cDto.setStayOrderNo("");
		cDto.setStayOrderDetails("");
		cDto.setCaveatDetails("");
		cDto.setRemarks("");
		cDto.setDocUrl("");
		cDto.setDocumentName(null);
		cDto.setDocContents(null);
		cDto.setAttachedDoc(null);
		cDto.setRegistrationNumber("");
		cDto.setCaseNum("");
		cDto.setCourtOrderDate("");
		cDto.setBankCourtName("");
		cDto.setBankCourtAddress("");
		cDto.setBankCourtPostalCode("");
		cDto.setBankCourtPhoneNumber("");
		cDto.setStayOrderStartDate("");
		cDto.setStayOrderUptoDate("");
		cDto.setCaveatDetails("");
		cDto.setCaveatApplicantName("");
		cDto.setPropertyTxnLock("");
		cDto.setProtestIdList(null);
		cDto.setPdAmount("");
		cDto.setPblAmount("");
		cDto.setPaidByUser("");
		cDto.setPaidAmount("");
		cDto.setPaymentDate("");
		cDto.setPropertyDetails("");
		
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
	
	public static boolean validateFileTypePDFAndJPG(String fileExt) {
		String[] arrFileExt = {"jpg","jpeg","pdf","Pdf"};
		try {
			for (int i = 0; i < arrFileExt.length; i++) {
				if (fileExt.equalsIgnoreCase(arrFileExt[i])) {
					return true;
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	
	/**
	 * 
	 * @param length
	 * @return
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
	

	
}
