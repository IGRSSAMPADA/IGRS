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

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.auditinspection.bd.PublicInspectionBD;
import com.wipro.igrs.auditinspection.bd.SROReportDetailsBD;
import com.wipro.igrs.auditinspection.constant.CommonConstant;
import com.wipro.igrs.auditinspection.dto.PublicDTO;
import com.wipro.igrs.auditinspection.form.PublicForm;
import com.wipro.igrs.baseaction.action.BaseAction;

/**
 * @author root
 * 
 */
public class PublicInspectionViewAction extends BaseAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wipro.igrs.baseaction.action.BaseAction#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse,
	 *      javax.servlet.http.HttpSession)
	 */
	String forward_Jsp;
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
			ArrayList errorList = null;
			PublicForm publicForm= (PublicForm)form;
			
			//added by shruti
			String language=(String)session.getAttribute("languageLocale");
			publicForm.setLanguage(language);
			//end
			
			PublicDTO publicDTO = publicForm.getPublicInspectionDTO();
			 PublicInspectionBD publicInspectionBD = new PublicInspectionBD();
			 
			String officeId = (String) session.getAttribute("loggedToOffice");
			CommonConstant auditConstant=new CommonConstant();
			String userId = (String) session.getAttribute("UserId");
			SROReportDetailsBD sroReportBd = new SROReportDetailsBD();
			String activityid = request.getParameter("ACTID");
			//role check
			if(auditConstant.DIG_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
			{		
				session.setAttribute("role", "DIG");
			}
			else if(auditConstant.IG_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
			{
				session.setAttribute("role", "IG");
			}
			else if(auditConstant.SR_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
			{	
				session.setAttribute("role", "SR");
			}
			else
			{
				session.setAttribute("role", "DR");
			}
			//END
			
			
			
			if("View POI Report".equalsIgnoreCase(request.getParameter("fnName"))&&"Audit And  Inspection".equalsIgnoreCase(request.getParameter("modName")))
			{
				//ADDED BY SHRUTI--24 FEB 2014
				publicDTO=new PublicDTO(); 
				//END
				ArrayList districtList=new ArrayList();
				districtList = publicInspectionBD.getDistrict("",language);
				publicDTO.setDistrictList(districtList);
				session.setAttribute("districtBean", publicDTO);
				publicDTO.setReportId("");
				publicDTO.setFromDate("");
				publicDTO.setToDate("");
				session.removeAttribute("inspectionidlist");
				//ADDED BY SHRUTI
				publicForm.setPublicInspectionDTO(publicDTO);
				forward_Jsp="SearchPage";
			}
			else if(publicDTO.getActionName().equalsIgnoreCase("searchReports"))
			{
				
				ArrayList listInspIds=publicInspectionBD.getInspectionPoiId(publicDTO,language);
				if(listInspIds.size() > 0){
					   session.setAttribute("inspectionidlist", listInspIds);
					   
					}
					if(listInspIds.size() == 0){
						session.removeAttribute("inspectionidlist");
						errorList = new ArrayList();
						errorList.add("<li><font color=" + "red"
								+ ">No Records Present.</font></li>");
						request.setAttribute("errorsList", errorList);
					}
					publicDTO.setActionName("");
					publicDTO.setReportId("");
					publicDTO.setFromDate("");
					publicDTO.setToDate("");
					forward_Jsp="SearchPage";
			}
			else if(request.getParameter("inspid")!=null)
			{
				String inspId=request.getParameter("inspid");
				PublicDTO poiDTO= publicInspectionBD.getPOIDetails(inspId,language);
				ArrayList<PublicDTO> docname=publicInspectionBD.getDocName(inspId);
				ArrayList <PublicDTO> paradetail=publicInspectionBD.getParaDetails(inspId,language);
				ArrayList <PublicDTO> objDetails=publicInspectionBD.getObjDetails(inspId,language);
			
				
				publicDTO.setInspID(inspId);
			
				session.setAttribute("poiId", inspId);
				//request.setAttribute("poiId", inspId);
				request.setAttribute("poiDetail", poiDTO);
				request.setAttribute("docName", docname);
				request.setAttribute("paraDetail", paradetail);
				request.setAttribute("objDetail", objDetails);
				forward_Jsp="SearchFinalPage";
				
			}
			else if(request.getParameter("documentName")!=null)
			{
				String docName=request.getParameter("documentName");
				String inspId=publicDTO.getInspID();
				//String inspId=(String)request.getParameter("poiId");
				ArrayList<PublicDTO> docPath=publicInspectionBD.getDocPath(inspId);
				DMSUtility dms=new DMSUtility();
				 for(int i=0;i<docPath.size();i++){		
					 PublicDTO pDTO=(PublicDTO)docPath.get(i);
					 if(pDTO.getDocName().equals(docName)){
					 	byte b[]=dms.getDocumentBytes(pDTO.getDocPath());
					 	dms.downloadDocument(response, pDTO.getDocPath(), b);
					 }
					// session.removeAttribute("poiId");
				 }
				
				// }
			}
			else if(publicDTO.getActionName().equalsIgnoreCase("searchReportsBack"))
			{
				forward_Jsp="SearchPage";
			}	
				return mapping.findForward(forward_Jsp);
	}

}
