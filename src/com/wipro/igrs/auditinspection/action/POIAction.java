/*
/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             SHRUTI          xxxxx              
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.auditinspection.action;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.auditinspection.bd.AGMPReportDetailsBD;
import com.wipro.igrs.auditinspection.bd.PublicInspectionBD;
import com.wipro.igrs.auditinspection.bd.SROReportDetailsBD;
import com.wipro.igrs.auditinspection.constant.CommonConstant;
import com.wipro.igrs.auditinspection.dto.AGMPReportDetailsDTO;
import com.wipro.igrs.auditinspection.dto.POIAddObjectionDTO;
import com.wipro.igrs.auditinspection.dto.PartyDetailsDTO;
import com.wipro.igrs.auditinspection.dto.PropertyDetailsDTO;
import com.wipro.igrs.auditinspection.dto.PublicDTO;
import com.wipro.igrs.auditinspection.dto.RegIdOtherSearch;
import com.wipro.igrs.auditinspection.dto.UploadFileDTO;
import com.wipro.igrs.auditinspection.form.PublicForm;
import com.wipro.igrs.auditinspection.rule.PublicOfficeInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.IGRSCommon;

public class POIAction extends BaseAction {
	Logger logger = (Logger) Logger.getLogger(POIAction.class);
	PublicDTO publicDTO = null;
	private PublicInspectionBD publicInspectionBD = new PublicInspectionBD();

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PublicForm publicForm = (PublicForm) form;
         publicDTO = publicForm.getPublicInspectionDTO();
		String FORWARD_PAGE = null;
		PublicOfficeInspectionRule rule = null;
		ArrayList errorList = null;
		String para_txn_id = request.getParameter("paraID");
		String obj_txn_id = request.getParameter("objTxnId");
		String action = request.getParameter("action");
		String officeId = (String) session.getAttribute("loggedToOffice");
		//added by shruti
		String language=(String)session.getAttribute("languageLocale");
		publicForm.setLanguage(language);
		String activityid = request.getParameter("ACTID");
		CommonConstant auditConstant=new CommonConstant();
		String userId = (String) session.getAttribute("UserId");
		SROReportDetailsBD sroReportBd = new SROReportDetailsBD();
		//end

		//ACTIVITY LOG
		if(activityid!=null)
		{
		IGRSCommon save=null;
		try {
			save = new IGRSCommon();
			save.saveactivitylog(userId, activityid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//role check
		if(auditConstant.DIG_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
		{
			if("ACT_14".equalsIgnoreCase(activityid)||"ACT_16".equalsIgnoreCase(activityid)||"ACT_17".equalsIgnoreCase(activityid))
			{FORWARD_PAGE = "authchk";
			session.setAttribute("role", "DIG");
			return mapping.findForward(FORWARD_PAGE);
			}
		}
		else if(auditConstant.IG_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
		{
			if("ACT_14".equalsIgnoreCase(activityid)||"ACT_16".equalsIgnoreCase(activityid)||"ACT_17".equalsIgnoreCase(activityid))
			{FORWARD_PAGE = "authchk";
			session.setAttribute("role", "IG");
			return mapping.findForward(FORWARD_PAGE);
			}
		}
		else if(auditConstant.SR_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
		{
			if("ACT_14".equalsIgnoreCase(activityid)||"ACT_16".equalsIgnoreCase(activityid)||"ACT_17".equalsIgnoreCase(activityid))
			{FORWARD_PAGE = "authchk";
			session.setAttribute("role", "SR");
			return mapping.findForward(FORWARD_PAGE);
			}	
		}
		else
		{
			session.setAttribute("role", "DR");
		}
		//END
		}
		
		
		if (publicForm.getActionType() == null&& request.getParameter("regid") != null) 
		{
			String searchId = request.getParameter("regid");
			String userid = (String) session.getAttribute("UserId");
			RegIdOtherSearch regIdOthers = publicInspectionBD.getRegIdDetails(searchId, userid);
			request.setAttribute("regidOthers", regIdOthers);
			FORWARD_PAGE = "regdetails";
		} 
		else if ("updateParaIdStatus".equalsIgnoreCase(action))
		{
			PublicDTO pdto = (PublicDTO) session.getAttribute("publicDto");
			//MODIFIED BY SHRUTI--24 FEB 2014
			if (publicInspectionBD.updateParaStatus(pdto)) 
			{
				//modified by shruti---25 feb 2014
				//String poiId = (String) session.getAttribute("POIID");
				String poiId=publicDTO.getReportId();
				//end
				PublicDTO myPublicDTO = publicInspectionBD.getExistingDetails(poiId);
				myPublicDTO.setReportType("existing");
				// vinay
				session.setAttribute("InspectionDetails", myPublicDTO);
				ArrayList paradetail = publicInspectionBD.getParaDetails(poiId,language);
				if (paradetail.size() > 0) 
				{
					request.setAttribute("paraDetail", paradetail);
				}
				try 
				{
					ArrayList paralist = publicInspectionBD.getPublicParaDetails(language);
					session.setAttribute("Paralist", paralist);
				} 
				catch (Exception e)
				{
					// TODO: handle exception
				}
				FORWARD_PAGE = "existingPOIDetails";
			}

		}
		 else if ("updateobjectionStatus".equalsIgnoreCase(action)) 
		 {
			 //-------------- modified by shruti---25 feb 2014
			PublicDTO pdto = (PublicDTO) session.getAttribute("publicDTO");
			// added by shruti
			pdto.setObjStatus(publicDTO.getObjStatus());
			pdto.setParaTxnid(publicDTO.getParaTxnid());
			pdto.setParaId(publicDTO.getParaId());
			pdto.setObjStatus(publicDTO.getObjStatus());
			// end
			ArrayList objList = null;
			if (publicInspectionBD.updateObjStatus(pdto)) 
			{
				publicForm.setActionType("");
				PublicDTO pdto1=new PublicDTO();
				pdto1 = (PublicDTO) session.getAttribute("publicDto");
				try
				{
					pdto1 = publicInspectionBD.getParaDtls(pdto1.getParaTxnid());
					objList = publicInspectionBD.getObjectionDtls(pdto1.getParaId());
					AGMPReportDetailsBD agmpbd = new AGMPReportDetailsBD();
					ArrayList paraStatusList = agmpbd.getParaStatusList(pdto1.getParaStatus());
					pdto1.setParaStatusList(paraStatusList);//added by shruti
					pdto.setParaStatusList(paraStatusList);

				} 
				catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				session.setAttribute("publicDto", pdto1);
				request.setAttribute("objList", objList);
				publicForm.setPublicInspectionDTO(pdto);//uncommented by shruti--21 oct 2014
				return mapping.findForward("editObj");
			}
		}
		 else if (obj_txn_id != null) 
		{
			PublicDTO pdto2;
			try 
			{
				pdto2= publicInspectionBD.getObjectionParaDtls(obj_txn_id);
				pdto2.setObjTxnId(obj_txn_id);
				pdto2.setObjStatus("");
				// COMMENTED BY SHRUTI  
				session.setAttribute("publicDTO", pdto2);//uncommented as there was error in objection details
				return mapping.findForward("editObjDetails");
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return mapping.findForward("editObjDetails");
		} 
		 else if (para_txn_id != null)
		 {
			 //----------modified by shruti---25 feb 2014
			PublicDTO pdto3;
			ArrayList objList;
			ArrayList paraStatusList;
			try {
				pdto3 = publicInspectionBD.getParaDtls(para_txn_id);
				objList = publicInspectionBD.getObjectionDtls(para_txn_id);
				pdto3.setTxnId(publicForm.getPublicInspectionDTO().getTxnId());
				pdto3.setParaTxnid(para_txn_id);
				AGMPReportDetailsBD agmpbd = new AGMPReportDetailsBD();
				paraStatusList = agmpbd.getParaStatusList(pdto3.getParaStatus());
				//publicForm.setPublicInspectionDTO(pdto3);
				pdto3.setParaStatusList(paraStatusList);
				session.setAttribute("publicDto", pdto3);
				request.setAttribute("objList", objList);
				return mapping.findForward("editObj");
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}

		}
		 else if (publicForm.getActionType() == null&& request.getParameter("filling_id") != null)
		 {
			String docid = request.getParameter("filling_id");
			RegIdOtherSearch regIdOthers = publicInspectionBD.getRegIdOtherDetails(docid);
			request.setAttribute("regidOthers", regIdOthers);
			return mapping.findForward("regothers");
		}
		// modified by shruti---as request scope removed
		// else if (publicForm.getActionType() == null &&
		// request.getParameter("poiId") != null)
		else if (request.getParameter("poiId") != null) 
		{
			String poiId = (String) request.getParameter("poiId");
			session.setAttribute("POIID", poiId);
			//ADDDED BY SHRUTI FOR PERSISTING REPORT ID---24 FEB 2014
			publicDTO.setReportId(poiId);
			//END
			PublicDTO myPublicDTO = publicInspectionBD.getExistingDetails(poiId);
			myPublicDTO.setReportType("existing");
			session.setAttribute("InspectionDetails", myPublicDTO);
			ArrayList paradetail = publicInspectionBD.getParaDetails(poiId,language);
			if (paradetail.size() > 0) 
			{
				request.setAttribute("paraDetail", paradetail);
			}
			try 
			{
				ArrayList paralist = publicInspectionBD.getPublicParaDetails(language);
				session.setAttribute("Paralist", paralist);
			}
			catch (Exception e) 
			{
				// TODO: handle exception
			}
			FORWARD_PAGE = "existingPOIDetails";

		}

		else if (publicForm.getActionType() == null && request.getParameter("txnid") != null)
		{
			String poiTxnId = request.getParameter("txnid");
			ArrayList list = publicInspectionBD.getPrintDetails(poiTxnId);
			PublicDTO myPublicDTO = (PublicDTO) list.get(0);
			ArrayList listDoc = (ArrayList) list.get(1);
			ArrayList listPara = (ArrayList) list.get(2);
			request.setAttribute("Details", myPublicDTO);
			request.setAttribute("DocDetails", listDoc);
			request.setAttribute("ParaDetails", listPara);
			FORWARD_PAGE = "printDetails";

		} 
		else if (publicForm.getActionType() == null && request.getParameter("cancel") != null
				&& request.getParameter("cancel").equalsIgnoreCase("true")) 
		{
			if (session.getAttribute("InspectionDetails") != null)
			{
				session.removeAttribute("InspectionDetails");
			}
			if (session.getAttribute("districtBean") != null) 
			{
				session.removeAttribute("districtBean");
			}
			FORWARD_PAGE = "newPOIInspection";
		}
		//ADDED BY SHRUTI---24 FEB 2014
		else if (publicForm.getActionType() != null && request.getParameter("cancel") != null
				&& request.getParameter("cancel").equalsIgnoreCase("true")) 
		{
			if (session.getAttribute("InspectionDetails") != null)
			{
				session.removeAttribute("InspectionDetails");
			}
			if (session.getAttribute("districtBean") != null) 
			{
				session.removeAttribute("districtBean");
			}
			FORWARD_PAGE = "newPOIInspection";
		}
		//END

		else if (publicForm.getActionType() != null && request.getParameter("actionType") != null
				&& request.getParameter("actionType").equalsIgnoreCase("display")) 
		{
			//String userId = (String) session.getAttribute("UserId");
			//added by shruti--24 feb 2014
			publicDTO = new PublicDTO();
			//end
			ArrayList officeList = publicInspectionBD.getOffice(userId);
			publicDTO.setOfficeList(officeList);
			session.setAttribute("officeBean", publicDTO);
			session.removeAttribute("attachment1");
			//ADDED
			publicForm.setPublicInspectionDTO(publicDTO);
			//END
			FORWARD_PAGE = "newPOIInspection";
		}
		else if (publicForm.getActionType() != null && publicForm.getActionType().equals("new")) 
		{
			rule = new PublicOfficeInspectionRule();
			errorList = rule.validateExistingReport(publicForm);
			if (rule.isError()) 
			{
				request.setAttribute("errorsList", errorList);
				FORWARD_PAGE = "newPOIInspection";
			} 
			else 
			{
				if (publicForm.getPublicInspectionDTO().getReportType().equalsIgnoreCase("new")) 
				{
					String office = publicForm.getPublicInspectionDTO().getOfficeId();
					PublicDTO newPublicDTO = (PublicDTO) session.getAttribute("officeBean");
					ArrayList list = (ArrayList) newPublicDTO.getOfficeList();
					for (int i = 0; i < list.size(); i++) 
					{
						PublicDTO myPublicDTO = (PublicDTO) list.get(i);
						if (myPublicDTO.getOfficeId().equals(office)) 
						{
							publicDTO.setOfficeName(myPublicDTO.getOfficeName());
							break;
						}

					}
					ArrayList districtList = publicInspectionBD.getDistrict(office,language);
					publicDTO.setDistrictList(districtList);
					session.setAttribute("districtBean", publicDTO);
					FORWARD_PAGE = "newReport";
				} 
				else 
				{
					ArrayList listReport = publicInspectionBD.getExistingReports(publicForm.getPublicInspectionDTO(),language);
					request.setAttribute("ExistingDetails", listReport);
					FORWARD_PAGE = "existingDetails";
				}
			}
		}

		else if (publicForm.getActionType() != null && publicForm.getActionType().equalsIgnoreCase("newReport")) 
		{
			rule = new PublicOfficeInspectionRule();
			errorList = rule.validateNewReport(publicForm);
			if (rule.isError())
			{
				request.setAttribute("errorsList", errorList);
				FORWARD_PAGE = "newReport";

			}
			else 
			{
				session.removeAttribute("paralist");
				String districtId = publicForm.getPublicInspectionDTO().getDistrictId();
				PublicDTO myPublicDTO = (PublicDTO) session.getAttribute("districtBean");
				ArrayList list = (ArrayList) myPublicDTO.getDistrictList();
				for (int i = 0; i < list.size(); i++) 
				{
					PublicDTO newPublicDTO = (PublicDTO) list.get(i);
					if (newPublicDTO.getDistrictId().equals(districtId)) 
					{
						myPublicDTO.setDistrictName(newPublicDTO.getDistrictName());
						break;
					}
				}
				myPublicDTO.setDistrictId(publicForm.getPublicInspectionDTO().getDistrictId());
				myPublicDTO.setAddress(publicForm.getPublicInspectionDTO().getAddress());
				myPublicDTO.setInsDate(publicForm.getPublicInspectionDTO().getInsDate());
				myPublicDTO.setDispatchDate(publicForm.getPublicInspectionDTO().getDispatchDate());
				myPublicDTO.setDispatchNumber(publicForm.getPublicInspectionDTO().getDispatchNumber());
				myPublicDTO.setInsEntryDate(publicForm.getPublicInspectionDTO().getInsEntryDate());
				myPublicDTO.setFromDate(publicForm.getPublicInspectionDTO().getFromDate());
				myPublicDTO.setToDate(publicForm.getPublicInspectionDTO().getToDate());
				myPublicDTO.setComments(publicForm.getPublicInspectionDTO().getComments());
				session.setAttribute("InspectionDetails", myPublicDTO);
				publicForm.setActionType(null);
				try {
					ArrayList paralist = publicInspectionBD.getPublicParaDetails(language);
					session.setAttribute("Paralist", paralist);
				} 
				catch (Exception e) 
				{
					// TODO: handle exception
				}
				FORWARD_PAGE = "report3";
			}
		}

		// ADDED by ShreeraJ
		else if (request.getParameter("documentName") != null)
		{
			String docName = request.getParameter("documentName");
			// String inspId=publicDTO.getInspID();
			DMSUtility dms = new DMSUtility();
			// session.setAttribute("FilePath", filePath);
			// session.setAttribute("attachment1", arrForSession);
			ArrayList arrSession = (ArrayList) session.getAttribute("attachment1");
			String filePath = (String) session.getAttribute("FilePath");
			for (int i = 0; i < arrSession.size(); i++) {

				UploadFileDTO uDTO = (UploadFileDTO) arrSession.get(i);
				if (uDTO.getFileName().equals(docName)) {
					String fileFullPath = filePath.concat(uDTO.getFileName());
					byte b[] = dms.getDocumentBytes(fileFullPath);
					dms.downloadDocument(response, fileFullPath, b);
				}
			}
			// String inspId=(String)request.getParameter("poiId");
			// ArrayList<PublicDTO>
			// docPath=publicInspectionBD.getDocPath(inspId);
			/*
			 * DMSUtility dms=new DMSUtility(); for(int
			 * i=0;i<docPath.size();i++){ PublicDTO
			 * pDTO=(PublicDTO)docPath.get(i);
			 * if(pDTO.getDocName().equals(docName)){ byte
			 * b[]=dms.getDocumentBytes(pDTO.getDocPath());
			 * dms.downloadDocument(response, pDTO.getDocPath(), b); }
			 */
			// session.removeAttribute("poiId");

			// }
		}

		else if (publicForm.getActionType() != null && publicForm.getActionType().equalsIgnoreCase("AddPara"))
		{
			rule = new PublicOfficeInspectionRule();
			
			errorList = rule.validatePara(publicForm);
			if (rule.isError()) 
			{
				if (session.getAttribute("attachment1") == null) 
				{
					errorList.add("<li><font color=" + "red >"+ "Please Upload a Document First</font></li>");
				}
				//added by shruti----13 feb 2014
				if(publicForm.getPublicInspectionDTO().getParaType() == null)
				{
				request.setAttribute("errorsList", errorList);
				}
				//end
				FORWARD_PAGE = "report3";
			}
			else if (session.getAttribute("attachment1") == null) 
			{
				ArrayList errorList1 = new ArrayList();
				errorList1.add("<li><font color=" + "red >"+ "Please Select a Document</font></li>");
				request.setAttribute("errorsList", errorList1);
				FORWARD_PAGE = "report3";
			} 
			else 
			{
				PublicDTO myPublicDTO = (PublicDTO) session.getAttribute("InspectionDetails");
				myPublicDTO.setParaType(publicForm.getPublicInspectionDTO().getParaType());
				session.removeAttribute("InspectionDetails");
				session.setAttribute("InspectionDetails", myPublicDTO);
				FORWARD_PAGE = "addParaDetail";
			}
		}

		else if (publicForm.getActionType() != null && publicForm.getActionType().equalsIgnoreCase("AddDocument")) {
			rule = new PublicOfficeInspectionRule();
			errorList = rule.validateParaDetails(publicForm);
			if (rule.isError()) 
			{
				request.setAttribute("errorsList", errorList);
				FORWARD_PAGE = "addParaDetail";
			} 
			else 
			{
				PublicDTO myPublicDTO = (PublicDTO) session.getAttribute("InspectionDetails");
				myPublicDTO.setParaName(publicForm.getPublicInspectionDTO().getParaName());
				myPublicDTO.setParaComments(publicForm.getPublicInspectionDTO().getParaComments());
				session.removeAttribute("InspectionDetails");
				session.setAttribute("InspectionDetails", myPublicDTO);
				FORWARD_PAGE = "addDocument";
			}
		}

		/* SAVING AND ADDING MORE DOCUMENTS */

		else if (publicForm.getActionType() != null&& publicForm.getActionType().equalsIgnoreCase("save")) 
		{
			rule = new PublicOfficeInspectionRule();
			errorList = rule.validateObjectedDocuments(publicForm);
			// modified by shruti-14th oct 2013
			// publicForm.getPublicInspectionDTO().setObjectionStatus("O");
			publicForm.getPublicInspectionDTO().setObjectionStatus("OPEN");
			if (rule.isError()) 
			{
				request.setAttribute("errorsList", errorList);
				FORWARD_PAGE = "addDocument";
			} else if (publicForm.getAddMore().equalsIgnoreCase("addMore"))// Checking
			// For
			// ADD
			// MORE
			{
				if (session.getAttribute("ObjectedItems") == null) 
				{
					ArrayList objectionList = getObjectedItems(publicForm.getPublicInspectionDTO());
					session.setAttribute("ObjectedItems", objectionList);
				} 
				else 
				{
					ArrayList objectionList = (ArrayList) session.getAttribute("ObjectedItems");
					ArrayList tempList = getObjectedItems(publicForm.getPublicInspectionDTO());
					POIAddObjectionDTO tempDTO = (POIAddObjectionDTO) tempList.get(0);
					objectionList.add(tempDTO);
					session.removeAttribute("ObjectedItems");
					session.setAttribute("ObjectedItems", objectionList);
				}
				FORWARD_PAGE = "addDocument";
			} 
			else 
			{ // Saving The Inspection Report
				ArrayList objectionList = null;
				if (session.getAttribute("ObjectedItems") == null)
				{
					objectionList = getObjectedItems(publicForm.getPublicInspectionDTO());
				} 
				else 
				{
					objectionList = (ArrayList) session.getAttribute("ObjectedItems");
					ArrayList tempList = getObjectedItems(publicForm.getPublicInspectionDTO());
					POIAddObjectionDTO tempDTO = (POIAddObjectionDTO) tempList.get(0);
					objectionList.add(tempDTO);
				}
				PublicDTO myPublicDTO = (PublicDTO) session.getAttribute("InspectionDetails");
				ArrayList listFileNames = (ArrayList) session.getAttribute("attachment1");
				String strFilePath = (String) session.getAttribute("FilePath");
				String result = null;
				String strUserId = (String) session.getAttribute("UserId");
				myPublicDTO.setOfficeId(officeId);
				if (myPublicDTO.getReportType().equalsIgnoreCase("new")) 
				{
					result = publicInspectionBD.submitInspectionReport(myPublicDTO, listFileNames, strFilePath,objectionList, strUserId);
					for (int i = 0; i < listFileNames.size(); i++) 
					{
						UploadFileDTO uploadFormDTO = (UploadFileDTO) listFileNames.get(i);
						FormFile strFileName = uploadFormDTO.getTheFile();
						String fileStore = strFilePath.concat(result).concat("/").concat(uploadFormDTO.getFileName());
						File folder = new File(strFilePath.concat(result));
						if (!folder.exists())
						{
							folder.mkdir();
						}
						try 
						{
							File newFile = new File(fileStore);
							// FormFile strFileName = newFile.getAbsoluteFile()
							FileOutputStream fos = new FileOutputStream(newFile);
							fos.write(strFileName.getFileData());
							fos.close();
						} 
						catch (Exception ex) 
						{
							ex.printStackTrace();
						}
						// newFile.delete();
					}
					if (!result.equalsIgnoreCase("false")) 
					{
						request.setAttribute("TxnId", result);
						session.removeAttribute("attachment1");
						session.removeAttribute("InspectionDetails");
						session.removeAttribute("ObjectedItems");
						errorList = new ArrayList();
						errorList.add("<font color=red>POI Report has been Created SuccessFully</font>");
						request.setAttribute("errorsList", "errorList");
						FORWARD_PAGE = "confirmation";
					} 
					else 
					{
						session.removeAttribute("attachment1");
						session.removeAttribute("InspectionDetails");
						session.removeAttribute("ObjectedItems");
						errorList = new ArrayList();
						errorList.add("<font color=red>POI Report has not been Created SuccessFully</font>");
						request.setAttribute("errorsList", "errorList");
						//String userId = (String) session.getAttribute("UserId");
						ArrayList officeList = publicInspectionBD.getOffice(userId);
						publicDTO.setOfficeList(officeList);
						session.setAttribute("officeBean", publicDTO);
						FORWARD_PAGE = "newPOIInspection";
					}
				}
			}
		}

		// REGISTRATION ID SEARCH

		else if (publicForm.getActionType() != null && publicForm.getActionType().equalsIgnoreCase("RegIdSearch")) {
			String docType = publicForm.getPublicInspectionDTO().getDocType();
			if (docType == null) 
			{
				docType = publicForm.getDocType();
				publicForm.getPublicInspectionDTO().setDocType(docType);
			} 
			else 
			{
				docType = publicForm.getPublicInspectionDTO().getDocType();
			}
			if (docType.equalsIgnoreCase("Regid")) 
			{
				FORWARD_PAGE = regIdSearchAction(request, publicForm.getPublicInspectionDTO(), publicForm,publicInspectionBD, session);
			} /*else {
				FORWARD_PAGE = searchOtherDetails(request, publicForm.getPublicInspectionDTO(), publicInspectionBD);
			}
			*/
			if (publicDTO.getOthersDocTypePoi()!="" && "sendToAction".equalsIgnoreCase(publicDTO.getOthersDocTypePoi())) 
			{//OTHERS
				
				
				
				FORWARD_PAGE= "addDocument";
				publicDTO.setOthersDocTypePoi("");
				publicDTO.setOthersFlagPoi("1");
				return mapping.findForward(FORWARD_PAGE);
			}
			if (docType.equalsIgnoreCase("others")) 
			{
				publicDTO.setOthersRegIdPoi("");
				publicDTO.setOthersRegDatePoi("");
				publicDTO.setOthersRegFeePoi("");
				publicDTO.setOthersConsiderationAmtPoi("");
				publicDTO.setOthersSrNamePoi("");
				publicDTO.setOthersSroNamePoi("");
				publicDTO.setOthersStampDutyPoi("");
				publicDTO.setOthersValAtRegTimePoi("");
				
				//OTHERS
				//strReturnVal = searchOtherDetails(request,agmpreportdetailsdto, agmpbd);
				//publicDTO.setDocTypeFlag("O");
				//return mapping.findForward(strReturnVal);
				
				FORWARD_PAGE= "idotherspoi";
			//	agmpreportdetailsdto.setOthersDocType("");
			//	agmpreportdetailsdto.setOthersFlag("1");
				return mapping.findForward(FORWARD_PAGE);
			}
		}

		/*
		 * if(request.getParameter("id")!=null &&
		 * request.getParameter("id").equalsIgnoreCase
		 * (request.getParameter("id"))){ ArrayList
		 * list=(ArrayList)session.getAttribute("paralist");
		 * session.setAttribute("paralist",list); FORWARD_PAGE="popup"; }
		 */

		/**
		 * *********************************** EXISTING PUBLIC OFFICE INSPECTION
		 * REPORT **********************************************
		 */
		else if (publicForm.getActionType() != null && publicForm.getActionType().equalsIgnoreCase("existingAddPara")) 
		{
			rule = new PublicOfficeInspectionRule();
			errorList = rule.validatePara(publicForm);
			if (rule.isError()) 
			{
				request.setAttribute("errorsList", errorList);
				FORWARD_PAGE = "existingPOIDetails";
			} 
			else 
			{
				PublicDTO myPublicDTO = (PublicDTO) session.getAttribute("InspectionDetails");
				myPublicDTO.setParaType(publicForm.getPublicInspectionDTO().getParaType());
				session.removeAttribute("InspectionDetails");
				session.setAttribute("InspectionDetails", myPublicDTO);
				FORWARD_PAGE = "existingAddParaDetail";
			}

		} 
		else if (publicForm.getActionType() != null && publicForm.getActionType().equalsIgnoreCase("existingAddDocument"))
		{
			rule = new PublicOfficeInspectionRule();
			errorList = rule.validateParaDetails(publicForm);
			if (rule.isError()) 
			{
				request.setAttribute("errorsList", errorList);
				FORWARD_PAGE = "existingAddParaDetail";
			} 
			else 
			{
				PublicDTO myPublicDTO = (PublicDTO) session.getAttribute("InspectionDetails");
				myPublicDTO.setParaName(publicForm.getPublicInspectionDTO().getParaName());
				myPublicDTO.setParaComments(publicForm.getPublicInspectionDTO().getParaComments());
				session.removeAttribute("InspectionDetails");
				session.setAttribute("InspectionDetails", myPublicDTO);
				FORWARD_PAGE = "existingAddDocument";
			}
		} 
		else if (publicForm.getActionType() != null && publicForm.getActionType().equalsIgnoreCase("existingSave")) {
			rule = new PublicOfficeInspectionRule();
			errorList = rule.validateObjectedDocuments(publicForm);
			publicForm.getPublicInspectionDTO().setObjectionStatus("O");
			if (rule.isError()) {
				request.setAttribute("errorsList", errorList);
				FORWARD_PAGE = "existingAddDocument";
			} else if (publicForm.getAddMore().equalsIgnoreCase("addMore")) // Checking
			// For
			// ADD
			// MORE
			{
				if (session.getAttribute("ObjectedItems") == null) 
				{
					ArrayList objectionList = getObjectedItems(publicForm.getPublicInspectionDTO());
					session.setAttribute("ObjectedItems", objectionList);
				} 
				else 
				{
					ArrayList objectionList = (ArrayList) session.getAttribute("ObjectedItems");
					ArrayList tempList = getObjectedItems(publicForm.getPublicInspectionDTO());
					POIAddObjectionDTO tempDTO = (POIAddObjectionDTO) tempList.get(0);
					objectionList.add(tempDTO);
					session.removeAttribute("ObjectedItems");
					session.setAttribute("ObjectedItems", objectionList);
				}
				FORWARD_PAGE = "existingAddDocument";
			}
			else // Saving The Inspection Report
			{
				ArrayList objectionList = null;
				if (session.getAttribute("ObjectedItems") == null) 
				{
					objectionList = getObjectedItems(publicForm.getPublicInspectionDTO());
				}
				else 
				{
					objectionList = (ArrayList) session.getAttribute("ObjectedItems");
					ArrayList tempList = getObjectedItems(publicForm.getPublicInspectionDTO());
					POIAddObjectionDTO tempDTO = (POIAddObjectionDTO) tempList.get(0);
					objectionList.add(tempDTO);
				}
				PublicDTO myPublicDTO = (PublicDTO) session.getAttribute("InspectionDetails");
				boolean result = false;
				String strUserId = (String) session.getAttribute("UserId");
				if (myPublicDTO.getReportType().equalsIgnoreCase("existing")) 
				{
					result = publicInspectionBD.updateInspectionReport(myPublicDTO, objectionList, strUserId);
					if (result) 
					{
						request.setAttribute("TxnId", myPublicDTO.getTxnId());
						session.removeAttribute("InspectionDetails");
						session.removeAttribute("ObjectedItems");
						FORWARD_PAGE = "confirmation";
					}
				}
			}
		}

		logger.info("FORWARD_PAGE :" + FORWARD_PAGE);
		return mapping.findForward(FORWARD_PAGE);
	}

	/**
	 * ************************************************************* END OF
	 * METHOD EXCEUTE *********************************************************
	 */

	private ArrayList getObjectedItems(PublicDTO publicDTO) {

		ArrayList objectionList = new ArrayList();
		POIAddObjectionDTO objectionDTO = new POIAddObjectionDTO();
		objectionDTO.setTxtDocID(publicDTO.getTxtDocID());
		objectionDTO.setTxtSRname(publicDTO.getTxtSRname());
		objectionDTO.setTxtStampDuty1(publicDTO.getTxtStampDuty1());
		objectionDTO.setTxtRegFee1(publicDTO.getTxtRegFee1());
		objectionDTO.setTempValAgmp(publicDTO.getTempValAgmp());
		objectionDTO.setTxtStampDuty(publicDTO.getTxtStampDuty());
		objectionDTO.setTxtRegFee(publicDTO.getTxtRegFee());
		objectionDTO.setTxtObjDetails(publicDTO.getTxtObjDetails());
		objectionDTO.setTxtAgmpComm(publicDTO.getTxtAgmpComm());
		if(publicDTO.getObjectionStatus()!=null&&publicDTO.getObjectionStatus()!=""&&"O".equalsIgnoreCase(publicDTO.getObjectionStatus()))
		{
			publicDTO.setObjectionStatus("OPEN");
		}
		objectionDTO.setObjectionStatus(publicDTO.getObjectionStatus());

		objectionList.add(objectionDTO);
		return objectionList;
	}

	private String regIdSearchAction(HttpServletRequest request,
			PublicDTO publicDTO, PublicForm publicForm, PublicInspectionBD bd,
			HttpSession session) 
	{
		ArrayList searchlist = null;
		String strForward = null;
		String searchRegId = publicDTO.getRegId();

		if (publicDTO.getDocType().equalsIgnoreCase("Regid")) 
		{
			// Perform Like Search In his Case.
			if (publicDTO.getRegId().length() < 15) 
			{
				searchlist = bd.getRegDetails(searchRegId);
				strForward = "regId";
			} 
			else 
			{
				String userid = (String) session.getAttribute("UserId");
				RegIdOtherSearch regIdOthers = bd.getRegIdDetails(searchRegId,userid);
				request.setAttribute("regidOthers", regIdOthers);
				strForward = "regdetails";
			}
			request.setAttribute("searchlist", searchlist);

			// added by shruti--- 19th nov 2013
			// added by shruti
			String partydtls = (String) request.getParameter("partyDetails");
			if (partydtls != null) 
			{
				if (partydtls.equalsIgnoreCase("true")) 
				{
					
					// publicDTO.setDocType("");
					PartyDetailsDTO partydto1 = new PartyDetailsDTO();
					PartyDetailsDTO partyDto = new PartyDetailsDTO();
					try 
					{
						String language=(String)session.getAttribute("languageLocale");
						partydto1 = publicInspectionBD.getPartyDtls(publicDTO.getRegId(),language);
						partyDto.setPartyList(partydto1.getPartyList());
						publicForm.setPartyDto(partyDto);
						strForward = "partydtls";

					} 
					catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			// end
			String propertydtls = (String) request.getParameter("propertyDetails");
			if (propertydtls != null) 
			{
				if (propertydtls.equalsIgnoreCase("true")) 
				{
					String language=(String)session.getAttribute("languageLocale");
					// publicDTO.setDocType("");
					PropertyDetailsDTO propertydto1 = new PropertyDetailsDTO();
					PropertyDetailsDTO propertyDto = new PropertyDetailsDTO();
					try 
					{
						propertydto1 = publicInspectionBD.getPropertyDtls(publicDTO.getRegId(),language);
						propertyDto.setPropertyList(propertydto1.getPropertyList());
						publicForm.setPropertyDto(propertyDto);
						strForward = "propertydtls";
					} 
					catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			// end

		}
		return strForward;
	}// END OF METHOD

	private String searchOtherDetails(HttpServletRequest request,
			PublicDTO publicDTO, PublicInspectionBD bd) 
	{
		if (publicDTO.getRegId().trim().length() == 6) 
		{
			RegIdOtherSearch regIdOthers = bd.getRegIdOtherDetails(publicDTO.getRegId().trim());
			request.setAttribute("regidOthers", regIdOthers);
			return "regothers";
		} 
		else 
		{
			ArrayList docidlist = new ArrayList();
			docidlist = bd.getDocIdDetails(publicDTO.getRegId().trim());
			request.setAttribute("docidlist", docidlist);
			return "documentids";
		}
	}// END OF METHOD

}// END OF ACTION CLASS

