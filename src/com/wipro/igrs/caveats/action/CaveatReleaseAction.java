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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
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
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveats.bd.CaveatsDelegate;
import com.wipro.igrs.caveats.bo.CaveatsBO;
import com.wipro.igrs.caveats.dao.CaveatsDAO;
import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.caveats.form.CaveatsForm;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.util.JrxmlExportUtility;
import com.wipro.igrs.util.PropertiesFileReader;
/**
* 
* CaveatReleaseAction.java <br>
* CaveatReleaseAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CaveatReleaseAction extends BaseAction {
   
	private Logger logger = 
		(Logger) Logger.getLogger(CaveatActionConfirm.class);

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
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws IOException, 
                                                                      ServletException,Exception {
    	logger.debug("WE ARE IN CaveatReleaseAction Debug");
		logger.info("WE ARE IN  CaveatReleaseAction INFO");
        try{
            CaveatsForm fm = (CaveatsForm) form;
            CaveatsDTO dtoRelease = fm.getCaveatsDTO();
            CaveatsDelegate cavBD = new CaveatsDelegate();
            ArrayList releaseSearchList=new ArrayList();
            String FORWAD_SUCCESS="failure";
            String refID=request.getParameter("referenceID");
            String actionName = dtoRelease.getActionName();
            String isMenuClick=request.getParameter("isMenuClick");
            //String downloadMainDoc=request.getParameter("downloadMainDoc");
            String language=(String)session.getAttribute("languageLocale");
            
        	String activityid = request.getParameter("ACTID");
			String userId = (String) session.getAttribute("UserId");
			String funId = (String) session.getAttribute("functionId");
			String officeId= (String) session.getAttribute("loggedToOffice");
            
            dtoRelease.setLanguage(language);
            dtoRelease.setUserId(userId);
            String pageName = request.getParameter("pageName");
            CaveatsBO caveatsBO = new CaveatsBO();
            String protestid = request.getParameter("protestID");
            String funName = request.getParameter("funName");
            
            
            PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
            CaveatsDAO caveatsDAO = new CaveatsDAO();

            if(null!=protestid && !"".equalsIgnoreCase(protestid)){
            	dtoRelease.setProtestID(protestid);
            }
            
            
            if(null!=pageName){
            	actionName = null;
            }else{
            	
            }
           if("protestIdFlag".equalsIgnoreCase(actionName)){
        	   dtoRelease.setDurationFlag("");
        	   return mapping.findForward("releaseInit");
           }
           if("durationFlag".equalsIgnoreCase(actionName)){
        	   dtoRelease.setProtestIDFlag("");
        	   fm.setFromDate("");
        	   fm.setToDate("");
        	   
        	   return mapping.findForward("releaseInit");
           }
           
           if("yes".equalsIgnoreCase(isMenuClick) && "ProtestRelease".equalsIgnoreCase(pageName)){
        	   reset(fm);
        	   return mapping.findForward("releaseInit");
           }
           if("yes".equalsIgnoreCase(isMenuClick) && "ProtestDashboard".equalsIgnoreCase(pageName)){
        	   reset(fm);
        	   return mapping.findForward("searchPage");
           }
           if("yes".equalsIgnoreCase(isMenuClick) && "ProtestReport".equalsIgnoreCase(pageName)){
        	   //reset(fm);
        	   //dtoRelease.setDistrictMasterList(caveatsDAO.districtStack(dtoRelease
   				//	.getStateId(),language));
        	   dtoRelease.setRegistrationNumber("");
        	   dtoRelease.setDistrictId("");
        	   dtoRelease.setDistrictName("");
        	   dtoRelease.setReportUserTypeIG("No");
        	   fm.setFromDate("");
        	   fm.setToDate("");
        	   String officeType = caveatsBO.getOfficeType(officeId);
				ArrayList<CaveatsDTO> districtList= new ArrayList<CaveatsDTO>();

				if(officeType.equalsIgnoreCase("4")){
					String zoneId = caveatsBO.getDIGZoneBo(officeId);
					logger.debug("The Zone of DIG : " + zoneId);
					districtList = caveatsBO.getDistDIGListBO(zoneId,
							language);
					dtoRelease.setReportUserTypeIG("Yes");
				}
				else{
					districtList= caveatsBO.getDistrictDetailsMIS(language,
							officeId);
				}
				
				dtoRelease.setDistrictMasterList(districtList);
        	   
        	   return mapping.findForward("protestReport");
           }
           //Logic to fetch the log protest data on the basis of todate and fromDate on Protest Release page.
           if("showProtestDetails".equalsIgnoreCase(actionName) ){
        	   if(null!=fm.getFromDate() && !"".equalsIgnoreCase(fm.getFromDate())){
        		   dtoRelease.setFromDate(fm.getFromDate());
        		   String fromDate [] = dtoRelease.getFromDate().split("/");
            	   String fromDateYr = fromDate[2].substring(2);
            	   String fromDate1 = fromDate[0]+"/"+fromDate[1]+"/"+fromDateYr;
            	   dtoRelease.setFromDate(fromDate1);
        	   }
        	   
        	   if(null!=fm.getToDate() && !"".equalsIgnoreCase(fm.getToDate())){
        		   dtoRelease.setToDate(fm.getToDate());
        		   String toDate [] = dtoRelease.getToDate().split("/");
            	   String toDateYr = toDate[2].substring(2);
            	   String toDate1 = toDate[0]+"/"+toDate[1]+"/"+toDateYr;
            	   dtoRelease.setToDate(toDate1);
        	   }
        	   
        	    String districtID = null;
        	    //String districtName = null;
        	    String districtDetls2 = caveatsBO.getDistrictName(officeId, language);
				String districtDetails2 [] = districtDetls2.split(";");
				//districtName = districtDetails2[0];
				districtID = districtDetails2[1];
				
				 
				   String distName = null;
	        	   String distHinName = null;
	        	   String districtDetls = caveatsBO.getDistrictValues(districtID, language);
	        	   String districtDetails1 [] = districtDetls.split(";");
	        	   distName = districtDetails1[0];
	        	   distHinName = districtDetails1[1];
	        	   
	       	 
	   	    	  
	       	  ArrayList<CaveatsDTO> protestAppList = caveatsBO.getProtestId(dtoRelease,distName,distHinName);
	       	ArrayList<CaveatsDTO> protestAppList1 = new ArrayList<CaveatsDTO>();
     	   if(null!=protestAppList && protestAppList.size()>0){
     		   for(int i=0; i<protestAppList.size(); i++){
     			   
     			   CaveatsDTO caveatsDTO = protestAppList.get(i);
     			   caveatsDTO.setLanguage(language);
     			   
     			   String data = caveatsBO.validateProtestRelStatus(caveatsDTO);
     			   
     			   if(null!=data && !"".equalsIgnoreCase(data)){
     				   
     			   }else{
     				   protestAppList1.add(caveatsDTO);
     			   }
     			   
     		   }
     	   }

        	  
        	   if(null!=protestAppList1 && protestAppList1.size()>0){
        		   Collections.sort(protestAppList1, new ProtestDateComparator());
   					
        		   request.setAttribute("protestAppList", protestAppList1);
        	   }else{
					ActionMessages message = new ActionMessages();
					message.add("message", new ActionMessage("caveats.records.notfound.msg"));
					saveMessages(request,message);
				}  
        	   return mapping.findForward("releaseInit");
           }
           // Logic to fetch the protest logged details after click on protest id as hyperlink 
           //or directly searching with protest id on protest release page.
           if("fetchProtestId".equalsIgnoreCase(pageName)){
        	   
        	   
        	   
        	   ArrayList list = null;
        	   
       	       String districtID = null;
       	       String districtName = null;
       	       String districtDetls2 = caveatsBO.getDistrictName(officeId, language);
				String districtDetails2 [] = districtDetls2.split(";");
				districtName = districtDetails2[0];
				districtID = districtDetails2[1];
				
				 String distName = null;
				 String protestStatus = null;
	        	String districtDetls = caveatsBO.validateRelDistrict(dtoRelease.getProtestID());
	        	/*String districtDetls1 [] =districtDetls.split(";");
	        	distName = districtDetls1[0];
	        	protestStatus = districtDetls1[1];*/
	        	
	        	
	       	   String districtIdVal = null;	
	       	   districtIdVal = caveatsBO.getDistrictIdVal(districtDetls, language);

	       	  String data = caveatsBO.validateProtestRelStatus(dtoRelease);
	       	   
		       if(null!=data && !"".equalsIgnoreCase(data)){
	      		   ActionMessages message = new ActionMessages();
	      		   message.add("message", new ActionMessage("caveats.protest.released.validation.msg"));
				   saveMessages(request,message);
				   return mapping.findForward("releaseInit");
	      	   }else if(null!=districtIdVal && !"".equalsIgnoreCase(districtIdVal) && !(districtIdVal.equalsIgnoreCase(districtID))){
	   		        ActionMessages message = new ActionMessages();
	   		        message.add("message", new ActionMessage("caveats.release.district.validation.msg"));
					saveMessages(request,message);
					return mapping.findForward("releaseInit");
	   	      }else{
	   	    	list = caveatsBO.getProtestDetls(dtoRelease);
	   	      }

        	   ArrayList list1 = null;
        	   if(null!=list && list.size()>0){
					for(int i=0; i<list.size(); i++){
						list1 = (ArrayList) list.get(i);
						dtoRelease.setSampadaDocFlag((String)list1.get(0));
						dtoRelease.setProtestID((String)list1.get(1));
						if("Yes".equalsIgnoreCase((String)list1.get(0))){
							dtoRelease.setPropertyId((String)list1.get(2));
							dtoRelease.setRegistrationNumber((String)list1.get(3));
						}
						if("No".equalsIgnoreCase((String)list1.get(0))){
							dtoRelease.setPropertyDetails((String)list1.get(4));
							dtoRelease.setAreaNameVal((String)list1.get(5));
							dtoRelease.setSubAreaNameVal((String)list1.get(6));
							dtoRelease.setTehsilNameVal((String)list1.get(7));
							dtoRelease.setWardPatwariNameVal((String)list1.get(8));
							dtoRelease.setColonyNameVal((String)list1.get(9));
						}
						dtoRelease.setDocumentName((String)list1.get(10));
						dtoRelease.setCaseNum((String)list1.get(11));
						dtoRelease.setCourtOrderDate((String)list1.get(12));
						dtoRelease.setBankCourtName((String)list1.get(13));
						dtoRelease.setBankCourtAddress((String)list1.get(14));
						if(null!=(String)list1.get(15) && !"".equalsIgnoreCase((String)list1.get(15))){
							dtoRelease.setBankCourtPostalCode((String)list1.get(15));
						}else{
							dtoRelease.setBankCourtPostalCode("NA");
						}
						if(null!=(String)list1.get(16) && !"".equalsIgnoreCase((String)list1.get(16))){
							dtoRelease.setBankCourtPhoneNumber((String)list1.get(16));
						}else{
							dtoRelease.setBankCourtPhoneNumber("NA");
						}
						dtoRelease.setCaveatApplicantName((String)list1.get(17));
						dtoRelease.setPropertyTxnLock((String)list1.get(18));
						dtoRelease.setCreatedDate((String)list1.get(19));
						dtoRelease.setPblAmount((String)list1.get(20));
						dtoRelease.setCaveatDetails((String)list1.get(21));
						dtoRelease.setStayOrderStartDate((String)list1.get(22));
						dtoRelease.setStayOrderUptoDate((String)list1.get(23));
						dtoRelease.setProtestCourtDistrict((String)list1.get(24));
						dtoRelease.setUploaded_doc_path((String)list1.get(25));
						if(null!=(String)list1.get(26) && !"".equalsIgnoreCase((String)list1.get(26))){
							dtoRelease.setRemarksForRelease((String)list1.get(26));
							dtoRelease.setReleaseDocName((String)list1.get(27));
							dtoRelease.setReleaseDocPath((String)list1.get(28));
						}
						
						dtoRelease.setPdAmount((String)list1.get(29));
						 dtoRelease.setPaidByUser((String)list1.get(30));
						 dtoRelease.setPaymentDate((String)list1.get(31));
						 dtoRelease.setProtestLogDistrict((String)list1.get(32));
							/*String districtDetls = caveatsBO.getDistrictName(officeId, language);
							String districtDetails [] = districtDetls.split(";");
							dtoRelease.setDistrictName(districtDetails[0]);
							dtoRelease.setDistrictId(districtDetails[1]);*/
						    dtoRelease.setProtestRelDistrict((String)list1.get(32));
							
						
					}
				}else{
					ActionMessages message = new ActionMessages();
					message.add("message", new ActionMessage("caveats.invalid.protestId"));
					saveMessages(request,message);
					return mapping.findForward("releaseInit");
				}

        	   dtoRelease.setBankCourtStateName("Madhya Pradesh");
        	   

        	   return mapping.findForward("refId");
           }
           // Logic to fetch the protest details when user click on the protest id as hyper link from protest dashboard page
           if("fetchDashboardProtestId".equalsIgnoreCase(pageName)){
        	   
        	   ArrayList list = caveatsBO.getProtestDashboardDetls(dtoRelease);
        	   
        	   ArrayList list1 = null;
        	   if(null!=list && list.size()>0){
					for(int i=0; i<list.size(); i++){
						list1 = (ArrayList) list.get(i);
						dtoRelease.setSampadaDocFlag((String)list1.get(0));
						dtoRelease.setProtestID((String)list1.get(1));
						if("Yes".equalsIgnoreCase((String)list1.get(0))){
							dtoRelease.setPropertyId((String)list1.get(2));
							dtoRelease.setRegistrationNumber((String)list1.get(3));
						}
						if("No".equalsIgnoreCase((String)list1.get(0))){
							dtoRelease.setPropertyDetails((String)list1.get(4));
							dtoRelease.setAreaNameVal((String)list1.get(5));
							dtoRelease.setSubAreaNameVal((String)list1.get(6));
							dtoRelease.setTehsilNameVal((String)list1.get(7));
							dtoRelease.setWardPatwariNameVal((String)list1.get(8));
							dtoRelease.setColonyNameVal((String)list1.get(9));
						}
						dtoRelease.setDocumentName((String)list1.get(10));
						dtoRelease.setCaseNum((String)list1.get(11));
						dtoRelease.setCourtOrderDate((String)list1.get(12));
						dtoRelease.setBankCourtName((String)list1.get(13));
						dtoRelease.setBankCourtAddress((String)list1.get(14));
						
						if(null!=(String)list1.get(15) && !"".equalsIgnoreCase((String)list1.get(15))){
							dtoRelease.setBankCourtPostalCode((String)list1.get(15));
						}else{
							dtoRelease.setBankCourtPostalCode("NA");
						}
						if(null!=(String)list1.get(16) && !"".equalsIgnoreCase((String)list1.get(16))){
							dtoRelease.setBankCourtPhoneNumber((String)list1.get(16));
						}else{
							dtoRelease.setBankCourtPhoneNumber("NA");
						}
						//dtoRelease.setBankCourtPostalCode((String)list1.get(15));
						//dtoRelease.setBankCourtPhoneNumber((String)list1.get(16));
						dtoRelease.setCaveatApplicantName((String)list1.get(17));
						dtoRelease.setPropertyTxnLock((String)list1.get(18));
						dtoRelease.setCreatedDate((String)list1.get(19));
						dtoRelease.setPblAmount((String)list1.get(20));
						dtoRelease.setCaveatDetails((String)list1.get(21));
						dtoRelease.setStayOrderStartDate((String)list1.get(22));
						dtoRelease.setStayOrderUptoDate((String)list1.get(23));
						dtoRelease.setProtestCourtDistrict((String)list1.get(24));
						dtoRelease.setUploaded_doc_path((String)list1.get(25));
						if(null!=(String)list1.get(26) && !"".equalsIgnoreCase((String)list1.get(26))){
							dtoRelease.setRemarksForRelease((String)list1.get(26));
							dtoRelease.setReleaseDocName((String)list1.get(27));
							dtoRelease.setReleaseDocPath((String)list1.get(28));
						}
						if("protestDashboardSearch".equalsIgnoreCase(funName)){
							
							dtoRelease.setProtestStatus((String)list1.get(29));
						}
						 dtoRelease.setPdAmount((String)list1.get(30));
						 dtoRelease.setPaidByUser((String)list1.get(31));
						 dtoRelease.setPaymentDate((String)list1.get(32));
						 dtoRelease.setProtestLogDistrict((String)list1.get(33));
							/*String districtDetls = caveatsBO.getDistrictName(officeId, language);
							String districtDetails [] = districtDetls.split(";");
							dtoRelease.setDistrictName(districtDetails[0]);
							dtoRelease.setDistrictId(districtDetails[1]);*/
						
					}
				}else if("protestDashboardSearch".equalsIgnoreCase(funName) && list.size()==0){
					
					 return mapping.findForward("searchPage");
				}
        	   else{
					ActionMessages message = new ActionMessages();
					message.add("message", new ActionMessage("caveats.invalid.protestId"));
					saveMessages(request,message);
					return mapping.findForward("searchPage");
				}

        	   dtoRelease.setBankCourtStateName("Madhya Pradesh");
        	   

        	   return mapping.findForward("protestViewDetails");
           }

           if("pageSubmit".equalsIgnoreCase(actionName)){
        	   boolean protestRelFlag = caveatsBO.InsertProtestRelDetls(fm);
        	  
        	   if(protestRelFlag && "Yes".equalsIgnoreCase(dtoRelease.getSampadaDocFlag())){
        		   caveatsBO.updatePropertyProtestDetls(dtoRelease,fm.getProtestRelId());
        	   }
        	   
        	   if("Yes".equalsIgnoreCase(dtoRelease.getSampadaDocFlag())){
         		  caveatsBO.updateRegProtestDetls(dtoRelease);
 			  }
        	   if(protestRelFlag){
        		   return mapping.findForward("protestRelSuccess"); 
        	   }else {
        		    ActionMessages message = new ActionMessages();
					message.add("message", new ActionMessage("protest.release.failed.msg"));
					saveMessages(request,message);
        	   }
        	   return mapping.findForward("refId");
           }
           if("protestLoggedDownldRecpt".equalsIgnoreCase(actionName)){
        	   boolean Flag = false;
        	   Flag = caveatsBO.updatePropertyProtestDetls(dtoRelease,fm.getProtestRelId());
        	   
        	  if("Yes".equalsIgnoreCase(dtoRelease.getSampadaDocFlag())){
        		  Flag = caveatsBO.updateRegProtestDetls(dtoRelease);
			  }
        	   if(Flag){
        		   return mapping.findForward("protestLogSuccess");
        	   }else{
        		   ActionMessages message = new ActionMessages();
					message.add("message", new ActionMessage("protest.release.failed.msg"));
					saveMessages(request,message);
        	   } 
        	   return mapping.findForward("protestLogSuccess");
           }
           // Logic to download the protest release receipt
           if("ProtestReceiptDownload".equalsIgnoreCase(pageName)){
        	   
        	   try {
      				logger.debug("GENERATE_Protest_Release_RECEIPT_REPORT entered");
      				JrxmlExportUtility export = new JrxmlExportUtility();
      				PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");

      				String path = prop.getValue("pdf.location");
      				String reportPath = path
      						.concat("Protest_Release_Receipt.jrxml");
      				String reportName = fm.getProtestRelId();
      				HashMap jasperMap = new HashMap();
      				jasperMap.put("funcId", funId);
      				jasperMap.put("protestTxnId", fm.getProtestRelId());
      				jasperMap.put("lang", language);
      				jasperMap.put("UserID", userId);
      				jasperMap.put("path", path);
      				jasperMap.put("OfficeId", officeId);
      				jasperMap.put("protestRemarks", dtoRelease.getRemarksForRelease());
      				//jasperMap.put("distName", dtoRelease.getDistrictName());
      			    jasperMap.put("distName", dtoRelease.getProtestLogDistrict());
      				
      				//boolean flag = export.genProtestRelPDF(jasperMap, reportPath, reportName, response);
      				
      				export.getJasperPdfviewerFromImg(jasperMap, reportPath,
      						reportName, response, path, "P");
      				logger.debug("GENERATE_Protest_Release_RECEIPT_REPORT exit" );
      				//return mapping.findForward("protestRelSuccess"); 
      				
      				} catch (Exception e) {
      					
      					e.printStackTrace();
      					return mapping.findForward("protestRelSuccess"); 
      				}
           }
           // Logic to download the protest log receipt
           if("ProtestLogReceiptDownload".equalsIgnoreCase(pageName)){
        	   
        	   try {
      				logger.debug("GENERATE_Protest_Log_RECEIPT_REPORT entered");
      				JrxmlExportUtility export = new JrxmlExportUtility();
      				PropertiesFileReader prop = PropertiesFileReader
      						.getInstance("resources.igrs");

      				String path = prop.getValue("pdf.location");
      				String reportPath = path
      						.concat("Protest_Log_Receipt.jrxml");
      				String reportName = dtoRelease.getProtestID();
      				HashMap jasperMap = new HashMap();
      				jasperMap.put("funcId", funId);
      				jasperMap.put("protestTxnId", dtoRelease.getProtestID());
      				jasperMap.put("lang", language);
      				jasperMap.put("UserID", userId);
      				jasperMap.put("path", path);
      				jasperMap.put("OfficeId", officeId);
      				jasperMap.put("protestRemarks", dtoRelease.getCaveatDetails());
      				jasperMap.put("distName", dtoRelease.getProtestLogDistrict());
      				
      				//boolean flag = export.genProtestRelPDF(jasperMap, reportPath, reportName, response);
      				//caveatsBO.updateProtestLogStatus(dtoRelease);
      				
      				export.getJasperPdfviewerFromImg(jasperMap, reportPath,
      						reportName, response, path, "P");

      				logger.debug("GENERATE_Protest_Log_RECEIPT_REPORT exit" );
      				} catch (Exception e) {
      					e.printStackTrace();
      					return mapping.findForward("protestLogSuccess");
      				}
           }
           //Logic to fetch all the protest details as report based on district or duration search
           if("protestReportData".equalsIgnoreCase(actionName)){
        	   
        	   String protestReportDis = null;
        	   dtoRelease.setDistrictName("");
        	   ArrayList<CaveatsDTO> districtList = dtoRelease.getDistrictMasterList();
        	   String distList [] = null;
        	  
        	   if(null!=districtList && districtList.size()>0){
        		   for(int i=0; i<districtList.size(); i++){
   					String districtId = districtList.get(i).getDistrictId();
   					String districtName = districtList.get(i).getDistrictName();
   					
   					if(districtId.equalsIgnoreCase(dtoRelease.getDistrictId())){
   						//dtoRelease.setDistrictName(districtName);
   						protestReportDis = districtName;
   					}
   				}
        	   }
        	   String distName = null;
        	   String distHinName = null;
        	   String districtDetls = caveatsBO.getDistrictValues(dtoRelease.getDistrictId(), language);
        	   
        	   String districtDetails1 [] = districtDetls.split(";");
        	   distName = districtDetails1[0];
        	   distHinName = districtDetails1[1];
		   			
        	   
        	   /*if(("".equalsIgnoreCase(dtoRelease.getDistrictName()) || null==dtoRelease.getDistrictName()) 
        			   && "No".equalsIgnoreCase(dtoRelease.getReportUserTypeIG())){
        		   
        		    String districtDetls1 = caveatsBO.getDistrictName(officeId, language);
					String districtDetails1 [] = districtDetls1.split(";");
					protestReportDis = districtDetails1[0];
					
        	   }*/
			  if(null!=fm.getFromDate() && !"".equalsIgnoreCase(fm.getFromDate())){
        	   //if(null!=dtoRelease.getFromDate() && !"".equalsIgnoreCase(dtoRelease.getFromDate())){
				  dtoRelease.setFromDate(fm.getFromDate());
        		   String fromDate [] = dtoRelease.getFromDate().split("/");
            	   String fromDateYr = fromDate[2].substring(2);
            	   String fromDate1 = fromDate[0]+"/"+fromDate[1]+"/"+fromDateYr;
            	   dtoRelease.setFromDate(fromDate1);
        	   }
			  if(null!=fm.getToDate() && !"".equalsIgnoreCase(fm.getToDate())){
        	   //if(null!=dtoRelease.getToDate() && !"".equalsIgnoreCase(dtoRelease.getToDate())){
				   dtoRelease.setToDate(fm.getToDate());
        		   String toDate [] = dtoRelease.getToDate().split("/");
            	   String toDateYr = toDate[2].substring(2);
            	   String toDate1 = toDate[0]+"/"+toDate[1]+"/"+toDateYr;
            	   dtoRelease.setToDate(toDate1);
        	   }
        	   
        	   ArrayList<CaveatsDTO> list2 = new ArrayList<CaveatsDTO>();
        	   ArrayList<CaveatsDTO> list3 = new ArrayList<CaveatsDTO>();
        	   ArrayList<CaveatsDTO> protestSearchList = caveatsBO.getProtestReportData(dtoRelease,distName,distHinName);
        		   if("en".equalsIgnoreCase(language)){
        			   if(protestSearchList.size()>0){
      	        			for(int j=0; j<protestSearchList.size(); j++){
      	 	        		   
      		        			CaveatsDTO caveatsDTO = new CaveatsDTO();
      		        			caveatsDTO = protestSearchList.get(j);
      		        			if(null!=distName && !"".equalsIgnoreCase(distName)){
      		        				caveatsDTO.setDistrictName(distName);
      		        			}else{
      		        				caveatsDTO.setDistrictName("NA");
      		        			}
      		        			
      		        			if(null!=caveatsDTO.getRegistrationNumber() && !"".equalsIgnoreCase(caveatsDTO.getRegistrationNumber())){
      		        				
      		        			}else{
      		        				caveatsDTO.setRegistrationNumber("NA");
      		        			}
      		        			if("आपत्ति लॉग करने हेतु भुगतान लंबित".equalsIgnoreCase(caveatsDTO.getProtestStatus())){
      		        				caveatsDTO.setProtestStatus("LOGGED PAYMENT PENDING");
      		        			}else if("आपत्ति हेतु भुगतान पूर्ण".equalsIgnoreCase(caveatsDTO.getProtestStatus())){
      		        				caveatsDTO.setProtestStatus("LOGGED PAYMENT COMPLETED");
      		        			}
      		        			else if("आपत्ति लॉग".equalsIgnoreCase(caveatsDTO.getProtestStatus())){
      		        				caveatsDTO.setProtestStatus("LOGGED");
      		        			}else if("आपत्ति जारी".equalsIgnoreCase(caveatsDTO.getProtestStatus())){
      		        				caveatsDTO.setProtestStatus("RELEASED");
      		        			}
      		        			list2.add(caveatsDTO);
      	        			}
      	        		}
        			   
        		   }else if("hi".equalsIgnoreCase(language)){
        			   if(protestSearchList.size()>0){
   	        			for(int j=0; j<protestSearchList.size(); j++){
   	 	        		   
   		        			CaveatsDTO caveatsDTO = new CaveatsDTO();
   		        			caveatsDTO = protestSearchList.get(j);
   		        			if(null!=distName && !"".equalsIgnoreCase(distName)){
  		        				caveatsDTO.setDistrictName(distHinName);
  		        			}else{
  		        				caveatsDTO.setDistrictName("NA");
  		        			}
   		        			if(null!=caveatsDTO.getRegistrationNumber() && !"".equalsIgnoreCase(caveatsDTO.getRegistrationNumber())){
  		        				
  		        			}else{
  		        				caveatsDTO.setRegistrationNumber("NA");
  		        			}
   		        			if("LOGGED PAYMENT PENDING".equalsIgnoreCase(caveatsDTO.getProtestStatus())){
   		        				caveatsDTO.setProtestStatus("आपत्ति लॉग करने हेतु भुगतान लंबित");
   		        			}else if("LOGGED PAYMENT COMPLETED".equalsIgnoreCase(caveatsDTO.getProtestStatus())){
   		        				caveatsDTO.setProtestStatus("आपत्ति हेतु भुगतान पूर्ण");
   		        			}
   		        			else if("LOGGED".equalsIgnoreCase(caveatsDTO.getProtestStatus())){
   		        				caveatsDTO.setProtestStatus("आपत्ति लॉग");
   		        			}else if("RELEASED".equalsIgnoreCase(caveatsDTO.getProtestStatus())){
   		        				caveatsDTO.setProtestStatus("आपत्ति जारी");
   		        			}
   		        			list3.add(caveatsDTO);
   	        			}
   	        		}
        		   }

		       if(null!=protestSearchList && protestSearchList.size()>0 && "en".equalsIgnoreCase(language)){
		    	   Collections.sort(list2, new ProtestDateComparator());
        		   request.setAttribute("protestSearchList", list2);
        	   }else if(null!=protestSearchList && protestSearchList.size()>0 && "hi".equalsIgnoreCase(language)){
        		   Collections.sort(list3, new ProtestDateComparator());
        		   request.setAttribute("protestSearchList", list3);
        	   }else{
					ActionMessages message = new ActionMessages();
					message.add("message", new ActionMessage("caveats.records.notfound.msg"));
					saveMessages(request,message);
				} 
		       
        	   return mapping.findForward("protestReport");
           }
           //Logic to fetch the data search by user on basis of protest id, protest status or duration from protest dashboard page
           if("protestDashboardSearch".equalsIgnoreCase(actionName)){

        	  
        		 if(null!=fm.getFromDate() && !"".equalsIgnoreCase(fm.getFromDate())){
        		   dtoRelease.setFromDate(fm.getFromDate());
        		   String fromDate [] = dtoRelease.getFromDate().split("/");
            	   String fromDateYr = fromDate[2].substring(2);
            	   String fromDate1 = fromDate[0]+"/"+fromDate[1]+"/"+fromDateYr;
            	   dtoRelease.setFromDate(fromDate1);
            	   //session.setAttribute("fromDate", fromDate1);
        	   }
        	   
        	
        		   if(null!=fm.getToDate() && !"".equalsIgnoreCase(fm.getToDate())){
        		   dtoRelease.setToDate(fm.getToDate());
        		   String toDate [] = dtoRelease.getToDate().split("/");
            	   String toDateYr = toDate[2].substring(2);
            	   String toDate1 = toDate[0]+"/"+toDate[1]+"/"+toDateYr;
            	   dtoRelease.setToDate(toDate1);
            	   //session.setAttribute("toDate", toDate1);
        	   }
        	   // START | Logic to fetch all the protest data with payment pending status and check in payment table for payment details
        	   //And update the status to payment completed if payment is done.
           try {
        	 if("LOGGED PAYMENT PENDING".equalsIgnoreCase(dtoRelease.getProtestStatus()) || "LOGGED PAYMENT COMPLETED".equalsIgnoreCase(dtoRelease.getProtestStatus())){
		
        	   //ArrayList<CaveatsDTO> protestSearchList2 = caveatsBO.getProtestSearchData(dtoRelease);
        		 ArrayList<CaveatsDTO> protestSearchList2 = new ArrayList<CaveatsDTO>();
        		 try {
        		      protestSearchList2 = caveatsBO.fetchPaymentDetails(dtoRelease);
        		 } catch (Exception e) {
        			 //logger.debug(" Exception occured while fetching payment pending records "+e.getMessage());
         			   e.printStackTrace();
 				}
        	   
        	   if(protestSearchList2.size()>0){
        		   for(int i=0; i<protestSearchList2.size(); i++){
	        		   
	        			CaveatsDTO caveatsDTO = new CaveatsDTO();
	        			caveatsDTO = protestSearchList2.get(i);
	        			
	        			
	        			ArrayList list2 = null;	
	        			try {
	        			    list2 = caveatsBO.getPaymentDetails(caveatsDTO);
	        			} catch (Exception e) {
	           			 //logger.debug(" Exception occured while fetching payment details for pending records "+e.getMessage());
	            		  e.printStackTrace();
	    				}
	        			
	 	            	   ArrayList list3 = null;  
	 	            	  try {
	 	            	   if(null!=list2 && list2.size()>0){
	 	    					for(int j=0; j<list2.size(); j++){
	 	    						list3 = (ArrayList) list2.get(j);
	 	    						
	 	    						caveatsDTO.setPaymentFlag((String)list3.get(0));
	 	    						caveatsDTO.setPdAmount((String)list3.get(1));
	 	    						caveatsDTO.setPaidByUser((String)list3.get(2));
	 	    						caveatsDTO.setPaymentDate((String)list3.get(3));
	 	    					}	
	 	            	   }else{
	 	            		   caveatsDTO.setPaymentFlag("");
	 	            		   caveatsDTO.setPdAmount("");
	 	            		   caveatsDTO.setPaidByUser("");
	 	            		   caveatsDTO.setPaymentDate("");
	 	            	   }
	 	            	 } catch (Exception e) {
								e.printStackTrace();
							}
	 	            	  if(("P".equalsIgnoreCase(caveatsDTO.getPaymentFlag()) || "C".equalsIgnoreCase(caveatsDTO.getPaymentFlag()))
	 	            			  && null!=caveatsDTO.getPdAmount() && !"".equalsIgnoreCase(caveatsDTO.getPdAmount())){
		            		try {
						
	 	            		 	caveatsDTO.setLanguage(language);
		            		   caveatsBO.updateProtestStatus(caveatsDTO);
		            		   //caveatsBO.updatePaymentDetls(dtoRelease);
		            		} catch (Exception e) {
								e.printStackTrace();
							}
		            	   }
	        				
	        				
	        			//}		
        		   }
        	   }
        	   }
           } catch (Exception e) {
        	   //logger.debug(" Exception occured fetching payment pending and completed data outside loop "+e.getMessage());
   			   e.printStackTrace();
   			  return mapping.findForward("failure");
   		   }
        	// END | Logic to fetch all the protest data with payment pending status and check in payment table for payment details
        	   //And update the status to payment completed if payment is done.
        	   
           String districtID = null;
   	       //String districtName = null;
   	       String districtDetls2 = caveatsBO.getDistrictName(officeId, language);
			String districtDetails2 [] = districtDetls2.split(";");
			//districtName = districtDetails2[0];
			districtID = districtDetails2[1];
			
			 
		   String distName = null;
       	   String distHinName = null;
       	   String districtDetls = caveatsBO.getDistrictValues(districtID, language);
       	   String districtDetails1 [] = districtDetls.split(";");
       	   distName = districtDetails1[0];
       	   distHinName = districtDetails1[1];
       	   
        	   ArrayList<CaveatsDTO> list2 = new ArrayList<CaveatsDTO>();
        	   ArrayList<CaveatsDTO> list3 = new ArrayList<CaveatsDTO>();
        	   ArrayList<CaveatsDTO> protestSearchList = new ArrayList<CaveatsDTO>();
        	  try {
        		protestSearchList = caveatsBO.getProtestSearchData(dtoRelease,distName,distHinName);
              } catch (Exception e) {
  			     logger.debug(" Exception occured while fetching all the records after payment details updated "+e.getMessage());
   			      e.printStackTrace();
			   }
        		   if("en".equalsIgnoreCase(language)){
        			   if(protestSearchList.size()>0){
      	        			for(int j=0; j<protestSearchList.size(); j++){
      	 	        		   
      		        			CaveatsDTO caveatsDTO = new CaveatsDTO();
      		        			caveatsDTO = protestSearchList.get(j);
      		        			if("आपत्ति लॉग करने हेतु भुगतान लंबित".equalsIgnoreCase(caveatsDTO.getProtestStatus())){
      		        				caveatsDTO.setProtestStatus("LOGGED PAYMENT PENDING");
      		        			}else if("आपत्ति हेतु भुगतान पूर्ण".equalsIgnoreCase(caveatsDTO.getProtestStatus())){
      		        				caveatsDTO.setProtestStatus("LOGGED PAYMENT COMPLETED");
      		        			}
      		        			else if("आपत्ति लॉग".equalsIgnoreCase(caveatsDTO.getProtestStatus())){
      		        				caveatsDTO.setProtestStatus("LOGGED");
      		        			}else if("आपत्ति जारी".equalsIgnoreCase(caveatsDTO.getProtestStatus())){
      		        				caveatsDTO.setProtestStatus("RELEASED");
      		        			}
      		        			list2.add(caveatsDTO);
      	        			}
      	        		}
        			   
        		   }else if("hi".equalsIgnoreCase(language)){
        			   if(protestSearchList.size()>0){
   	        			for(int j=0; j<protestSearchList.size(); j++){
   	 	        		   
   		        			CaveatsDTO caveatsDTO = new CaveatsDTO();
   		        			caveatsDTO = protestSearchList.get(j);
   		        			if("LOGGED PAYMENT PENDING".equalsIgnoreCase(caveatsDTO.getProtestStatus())){
   		        				caveatsDTO.setProtestStatus("आपत्ति लॉग करने हेतु भुगतान लंबित");
   		        			}else if("LOGGED PAYMENT COMPLETED".equalsIgnoreCase(caveatsDTO.getProtestStatus())){
   		        				caveatsDTO.setProtestStatus("आपत्ति हेतु भुगतान पूर्ण");
   		        			}
   		        			else if("LOGGED".equalsIgnoreCase(caveatsDTO.getProtestStatus())){
   		        				caveatsDTO.setProtestStatus("आपत्ति लॉग");
   		        			}else if("RELEASED".equalsIgnoreCase(caveatsDTO.getProtestStatus())){
   		        				caveatsDTO.setProtestStatus("आपत्ति जारी");
   		        			}
   		        			list3.add(caveatsDTO);
   	        			}
   	        		}
        		   }

		      if(null!=protestSearchList && protestSearchList.size()>0 && "en".equalsIgnoreCase(language)){
		    	  Collections.sort(list2, new ProtestDateComparator());
        		   request.setAttribute("protestSearchList", list2);
        	   }else if(null!=protestSearchList && protestSearchList.size()>0 && "hi".equalsIgnoreCase(language)){
        		   
        		   Collections.sort(list3, new ProtestDateComparator());
        		   request.setAttribute("protestSearchList", list3);
        	   }else{
					ActionMessages message = new ActionMessages();
					message.add("message", new ActionMessage("caveats.records.notfound.msg"));
					saveMessages(request,message);
				}  
        	   
        	   return mapping.findForward("searchPage");
        	   
           }
           if ("upload".equalsIgnoreCase(actionName)) {
				ArrayList errorList = new ArrayList();
				try {
					FormFile uploadedFile = dtoRelease.getAttachedDoc();
					if ("".equals(uploadedFile.getFileName())) {
						
						ActionMessages message = new ActionMessages();
						message.add("message", new ActionMessage("caveats.invalid.fileName"));
						saveMessages(request,message);
					}
					String fileExt = getFileExtension(uploadedFile
							.getFileName());
					//AuditInspectionRule rule = new AuditInspectionRule();
					//rule.validateFileType(fileExt);
					Boolean validFileType = validateFileTypePDFAndJPG(fileExt);
					int size = uploadedFile.getFileSize();
					long fileSize = 512000;
					if (!validFileType) {
//						clearDoc(dtoRelease);
						ActionMessages message = new ActionMessages();
						message.add("message", new ActionMessage("caveats.invalid.fileType"));
						saveMessages(request,message);
					} else {
						//if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
						if (size > fileSize) {
//							clearDoc(dtoRelease);
							ActionMessages message = new ActionMessages();
							message.add("message", new ActionMessage("caveats.invalid.fileSize"));
							saveMessages(request,message);
						} else {
							String uploadPath = pr.getValue("igrs_caveats_upload_path");
							String dateTime = new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
							uploadPath = uploadPath+dateTime+"//";
							//dtoRelease.setUploaded_doc_path(uploadPath);
							dtoRelease.setReleaseDocPath(uploadPath);
							dtoRelease.setReleaseDocName(uploadedFile.getFileName());
							dtoRelease.setReleaseContents(uploadedFile.getFileData());
							dtoRelease.setReleaseFileSize(getFileSize(uploadedFile.getFileData().length));
							
							File file1 = new File(uploadPath);
							if (!file1.exists()) {
								file1.mkdirs();
					           }
							
							byte contents[] = uploadedFile.getFileData();
							File file2 = new File(uploadPath+uploadedFile.getFileName());
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
				} catch (Exception e) {
					ActionMessages message = new ActionMessages();
					message.add("message", new ActionMessage("caveats.invalid.fileUploadErr"));
					saveMessages(request,message);
				}
				return mapping.findForward("refId");
			}
		 if ("downloadRelease".equalsIgnoreCase(actionName)) {
				byte[] content = dtoRelease.getReleaseContents();
				String filename = dtoRelease.getReleaseDocName();
				try {
					if (content != null) {
						CaveatsViewSearchAction.downloadDocument(response,
								content, filename);
					}
				} catch (Exception e) {
					e.hashCode();
				}
				return mapping.findForward("refId");
			}
            
               if("downloadMainDoc".equalsIgnoreCase(actionName)) {

	        	try
	        	{
	        	response.setContentType("application/download");
	            
				String filePath=dtoRelease.getUploaded_doc_path();
				String fileName=dtoRelease.getDocumentName();
				File fileToDownload = new File(filePath+fileName);

				response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(dtoRelease.getDocumentName(),"UTF-8"));
				
	            FileInputStream fileInputStream = new FileInputStream(fileToDownload);

	            OutputStream out = response.getOutputStream();

	            byte[] buf = new byte[2048];

	            int readNum;

	            while ((readNum=fileInputStream.read(buf))!=-1)
	            {
	               out.write(buf,0,readNum);
	            }
	            fileInputStream.close();
	            if(null!=out){
	            	out.close();
	            }
	            return mapping.findForward("refId");
	        
	        }
            catch (Exception e) {
            	// logger.error(e);
			}
	        }
               if("downloadDoc".equalsIgnoreCase(actionName)) {

   	        	try
   	        	{
   	        	response.setContentType("application/download");
   	            
   				String filePath=dtoRelease.getUploaded_doc_path();
   				String fileName=dtoRelease.getDocumentName();
   				File fileToDownload = new File(filePath+fileName);

   				response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(dtoRelease.getDocumentName(),"UTF-8"));
   				
   	            FileInputStream fileInputStream = new FileInputStream(fileToDownload);

   	            OutputStream out = response.getOutputStream();

   	            byte[] buf = new byte[2048];

   	            int readNum;

   	            while ((readNum=fileInputStream.read(buf))!=-1)
   	            {
   	               out.write(buf,0,readNum);
   	            }
   	            fileInputStream.close();
   	            if(null!=out){
   	            	out.close();
   	            }
   	         return mapping.findForward("protestViewDetails");
   	        
   	        }
               catch (Exception e) {
               	// logger.error(e);
   			}
   	        }
            if("downloadReleaseDoc".equalsIgnoreCase(actionName)) {

   	        	try
   	        	{
   	        	response.setContentType("application/download");
   	            
   				//String filePath=dtoRelease.getUploaded_doc_path();
   	        	String filePath=dtoRelease.getReleaseDocPath();
   				String fileName=dtoRelease.getReleaseDocName();
   				File fileToDownload = new File(filePath+fileName);

   				response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(dtoRelease.getReleaseDocName(),"UTF-8"));
   				
   	            FileInputStream fileInputStream = new FileInputStream(fileToDownload);

   	            OutputStream out = response.getOutputStream();

   	            byte[] buf = new byte[2048];

   	            int readNum;

   	            while ((readNum=fileInputStream.read(buf))!=-1)
   	            {
   	               out.write(buf,0,readNum);
   	            }
   	            fileInputStream.close();
   	            if(null!=out){
   	            	out.close();
   	            }
   	            return mapping.findForward("protestViewDetails");
   	        
   	        }
               catch (Exception e) {
               	// logger.error(e);
   			}
   	        }
          
               return mapping.findForward("refId");
        }catch(Exception e)
        {
            //logger.error(e);
            return mapping.findForward("failure");
        }
    }
    
    public void reset(CaveatsForm caveatsForm){
    	CaveatsDTO CaveatsDTO = caveatsForm.getCaveatsDTO();
    	CaveatsDTO.setProtestIDFlag("");
    	CaveatsDTO.setDurationFlag("");
    	CaveatsDTO.setProtestID("");
    	CaveatsDTO.setPropertyId("");
    	CaveatsDTO.setRegistrationNumber("");
    	CaveatsDTO.setFromDate("");
    	CaveatsDTO.setToDate("");
    	CaveatsDTO.setProtestStatus("");
    	CaveatsDTO.setRemarksForRelease("");
    	CaveatsDTO.setReleaseFileSize("");
    	CaveatsDTO.setReleaseDocName("");
    	caveatsForm.setFromDate("");
    	caveatsForm.setToDate("");
    	CaveatsDTO.setSampadaDocFlag("");
    	//caveatsForm.setMultiRegNumFlag("No");
    	
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

}

