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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveats.bd.CaveatsDelegate;
import com.wipro.igrs.caveats.bo.CaveatsBO;
import com.wipro.igrs.caveats.constant.CaveatsConstant;
import com.wipro.igrs.caveats.dao.CaveatsDAO;
import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.caveats.dto.OTTDetailDTO;
import com.wipro.igrs.caveats.form.CaveatsForm;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.estamping.bo.EstampBO;
import com.wipro.igrs.estamping.form.EstampFormBean;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.regCompChecker.bd.RegCompCheckerBD;
import com.wipro.igrs.regCompChecker.constant.RegCompCheckerConstant;
import com.wipro.igrs.regCompChecker.form.RegCompCheckerForm;

import com.wipro.igrs.reginit.constant.RegInitConstant;
// Added new common code for MIME Type check of Uploaded file
import com.wipro.igrs.common.mime.MIMECheck;

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
public class InitAction extends BaseAction {
	
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
			String userId = (String) session.getAttribute("UserId");
			CaveatsForm fm = (CaveatsForm) form; 
			CaveatsForm caveatFormBean  =  (CaveatsForm)form;
			fm.setLanguage(language);
			CaveatsDTO cDto = new CaveatsDTO();
			cDto = fm.getCaveatsDTO(); 
			
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
			
			}
			
			if(fm.getLanguage().equalsIgnoreCase("hi"))
			{
				ArrayList<String> list = new ArrayList<String>();
				list.add("हां");
				list.add("नहीं");
				cDto.setYesNoList(list);
				
			}
			else
			{
				ArrayList<String> list = new ArrayList<String>();
				list.add("Yes");
				list.add("No");
				cDto.setYesNoList(list);
			}
			
			
			CaveatsBO objCaveatBO = new CaveatsBO();
			if(cDto.getStayOrderStartDate() != null) {
				fm.setStayOrderStartDate(cDto.getStayOrderStartDate());
			}
			if(cDto.getStayOrderUptoDate() != null) {
				fm.setStayOrderUptoDate(cDto.getStayOrderUptoDate());
			}
			String FORWAD_SUCCESS = "";
			String actionName = cDto.getActionName();
			CaveatsDAO refDAO = new CaveatsDAO();
			ArrayList errorList = new ArrayList();
			String isMenuClick = request.getParameter("isMenuClick");
			String page=null;
			String transid=null;
			
			///
			
		/*	String language="hi";
			language=(String)session.getAttribute("languageLocale");
			
			
			if(language.equalsIgnoreCase(CaveatsConstant.LANGUAGE_ENGLISH)){
				session.setAttribute("modName",CaveatsConstant.MODNAME_ENGLISH);	
				session.setAttribute("fnName",CaveatsConstant.FUNCTION_REGINIT_ENGLISH);
				
			}
			else{
				session.setAttribute("modName",CaveatsConstant.MODNAME_HINDI);
				session.setAttribute("fnName",CaveatsConstant.FUNCTION_REGINIT_HINDI);
			}
			
			cDto.setLanguage(language);

			*/
			
			//
			
			
		
			cDto.setLanguage(language);
			page=request.getParameter("pageName");
			transid=request.getParameter("transactionID");
			if(page!=null )
				
			{
				
		//ashima---		
				if(page.equals("dashboard"))
			{
					
					
		        		ArrayList pendingApplicationList = objCaveatBO.getPendingApps(session.getAttribute("UserId").toString(),language);
		        		//logger.info("--------------->"+pendingApplicationList.size());
						if(pendingApplicationList.size()==0)
						
							caveatFormBean.getCaveatsDTO().setPendingApps(null);
						else
							
							caveatFormBean.getCaveatsDTO().setPendingApps(pendingApplicationList);
						    request.setAttribute("pendingApplicationList", pendingApplicationList);
					        return mapping.findForward("DashboardScreen");	
					}
		        
			  }
		        
			
			
			
                 		if(transid!=null )
        				
        			{
        				try
        				{
        					
        				
        				
                        	  CaveatsForm fm1 = (CaveatsForm) form;
                        	//  CaveatsDelegate objTrans= new CaveatsDelegate();
                        	  CaveatsBO objTrans= new CaveatsBO();
                  			 CaveatsDTO dto=objTrans.getTransactionidDetails(transid,language);

                         fm1.setCaveatsDTO(dto);
                         request.setAttribute("fm1",fm1);
                               fm1.setFunID((String)session.getAttribute("functionId"));                
                          request.setAttribute("refId",transid);
                          
                          return mapping.findForward("TransidScreen");
  							
//                  request.setAttribute("PartialPayment","true");
                         

        				}
		 
                catch(Exception e) 	
                {
                	e.printStackTrace();
                	
        			}	
        				/*finally
        				{
        				 return mapping.findForward("TransidScreen");	
        				}*/
                 		
        			}
                 		
                 		
          //ashima--
                  
			if("yes".equals(isMenuClick)) {
				fm.setCaveatsDTO(new CaveatsDTO());

				session.setAttribute("MAX_FILE_SIZE", AuditInspectionConstant.MAX_FILE_SIZE);
				cDto = fm.getCaveatsDTO();
				actionName = cDto.getActionName();
				cDto.setDocumentName(null);
				cDto.setCaveatTypeList(refDAO.createCaveatsList(language));
		//		cDto.setCaveatTypeList(refDAO.createDashboard());
				
				cDto.setCountryMasterList(refDAO.countryList(language));
				fm.setSelectedList(new ArrayList());
				fm.setSearchResultList(new ArrayList());
				fm.setOttSearchResultList(new ArrayList<OTTDetailDTO>());
//				return mapping.findForward("CreateScreen");
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
			
			
			//
			if ("create".equalsIgnoreCase(request.getParameter("pageName"))) {
				if (actionName == null || "".equalsIgnoreCase(actionName)) {
					cDto.setDocumentName(null);
					cDto.setCaveatTypeList(refDAO.createCaveatsList(language));
					cDto.setCountryMasterList(refDAO.countryList(language));
				} else if ("State".equalsIgnoreCase(actionName)) {
					cDto.setStateMasterList(refDAO.stateList(cDto
							.getCountryId(),language));
					cDto.setDistrictMasterList(null);
				} else if ("District".equalsIgnoreCase(actionName)) {
					cDto.setDistrictMasterList(refDAO.districtStack(cDto
							.getStateId(),language));
				} else if ("setSelectedPropIDs".equalsIgnoreCase(actionName)) {
					if("yes".equals(session.getAttribute("cvtSearchUpdate"))) {
						try {
							fm = (CaveatsForm) session
									.getAttribute("caveatfrm");
							fm.getCaveatsDTO().setSelectedItems(
									(ArrayList) fm.getCaveatsDTO()
											.getCloneSelectedItems().clone());
							fm.getCaveatsDTO().getCloneSelectedItems().clear();
							session.removeAttribute("cvtSearchUpdate");
						} catch (Exception e) {
						}
					}
				} else if ("cancelClick".equalsIgnoreCase(actionName)) {
					
					try {
						fm = (CaveatsForm) session.getAttribute("caveatfrm");
						fm.setSearchResultList(new ArrayList());
						fm.getCaveatsDTO().setCloneSelectedItems(new ArrayList());
						session.removeAttribute("cvtSearchUpdate");
					} catch (Exception e) {
					}
				} 
				else if ("downloadMainDoc".equalsIgnoreCase(actionName)) {
					try {
						byte[] content = cDto.getDocContents();
						String filename = cDto.getDocumentName();
						if(content != null) {
							CaveatsViewSearchAction.downloadDocument(response, content, filename);
						}
					} catch (Exception e) {
					}			
				}
				
				
				
				
			/*	else if("download".equalsIgnoreCase(actionName)) {
					
					try
		        	{
		        	response.setContentType("application/download");
		            
					String filename=cDto.getUploaded_doc_path();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition", "attachment; filename="

		                                                + URLEncoder.encode(cDto.getDocName().toString(),"UTF-8"));
					
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
		            cDto.setActionName("");
		            FORWAD_SUCCESS = "confirm";
		        
		        }
	            catch (Exception e) {
				}	
		       
				}
				
				*/
			
				
				
				else if ("setUploadFile".equalsIgnoreCase(actionName)) {
					try {
						FormFile uploadedFile = cDto.getAttachedDoc();
						byte contents[] = uploadedFile.getFileData();
						cDto.setPhoto(contents);
						if ("".equals(uploadedFile.getFileName())) {
//							clearDoc(cDto);
							errorList
							.add("Invalid file selection. Please try again./अवैध फ़ाइल चयन. पुन: प्रयास करें.");
						}
						
					//	String fileExt = getFileExtension(uploadedFile.getFileName());
						//AuditInspectionRule rule = new AuditInspectionRule();
						
						// Added new code for MIME type common - Rahul
						MIMECheck mimeCheck = new MIMECheck();
						//rule.validateFileType(fileExt);
						String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
				        Boolean mime = mimeCheck.validateMIMEAndJPGFileType(uploadedFile);  // common code to check mime type and validation.
						 // new code finish.
						int size = uploadedFile.getFileSize();
						if (!mime) {
//							clearDoc(cDto);
							errorList
									.add("Invalid file type. Please select another file./अमान्य फ़ाइल प्रकार. एक और फ़ाइल का चयन करें.");
							request.setAttribute("errorsList", errorList);
						} else {
							if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
//								clearDoc(cDto);
								errorList
										.add("File size is Greater than 10 MB. Please select another file./फ़ाइल का आकार 10 एमबी से अधिक है. एक और फाइल चुनें");
								request.setAttribute("errorsList", errorList);
							} else {
								 String docName = "Caveat_Document."+fileExt;
								 fm.getCaveatsDTO().setUploaded_doc_path(docName);
								cDto.setDocumentName(uploadedFile.getFileName());
								cDto.setDocContents(uploadedFile.getFileData());
								cDto.setDocFileSize(getFileSize(uploadedFile.getFileData().length));
								
							
							}
						}
					} catch (Exception e) {
						errorList.add("Unable to upload file. Please try again./फ़ाइल अपलोड करने में असमर्थ. पुन: प्रयास करें.");
						request.setAttribute("errorsList", errorList);
					}
				} 
				
				else if("setValueType".equalsIgnoreCase(actionName))
				{
					System.out.println(cDto.getCaveatType());
					
					
				}
				else if("OK_ACTION".equalsIgnoreCase(actionName))
				{
					cDto.setActionName("");
					
					
					FORWAD_SUCCESS = "CreateScreen";
				}
				
				else if("getProperty".equalsIgnoreCase(actionName))
				{
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
					regForm.setMapPropertyTransPartyDisp(bo.getPropertyDetsForViewConfirmNonProp(reginitId, propertyId, language));	
				request.setAttribute("reginit", regForm);	
				FORWAD_SUCCESS = "propertyView1";
				}
				
				}
				
				else if("setValueTypeLock".equalsIgnoreCase(actionName))
				{
					System.out.println(cDto.getPropertyTxnLock());
					
					
				}
				else if("doneView".equalsIgnoreCase(actionName))
				{
					
					FORWAD_SUCCESS = "CreateScreen";
					
				}
				
				else if ("setSubmitData".equalsIgnoreCase(actionName)) {
					
					ArrayList caveatTypeList = cDto.getCaveatTypeList();
					for (Object item : caveatTypeList) {
						CaveatsDTO caveatType = (CaveatsDTO) item;
//						logger.debug("caveatType.getCaveatType() " + caveatType.getCaveatType());
//						logger.debug("cDto.getCaveatType() " + cDto.getCaveatType());
//						logger.debug("caveatType.getCaveatId() " + caveatType.getCaveatId());
//						logger.debug("cDto.getCaveatId() " + cDto.getCaveatId());
						if(caveatType.getCaveatId().equalsIgnoreCase(cDto.getCaveatType())) {
							cDto.setCaveatId(caveatType.getCaveatId());
							cDto.setCaveatLabel(caveatType.getCaveatType());
						//	cDto.setTransactionID(caveatType.getCaveatType());

							break;
						}
						//
					
				
					}
					
					//
                	boolean isError = validateForm(fm, errorList, request, (Date)session.getAttribute("currSysDate"));
					if(isError==false) {
						session.setAttribute("caveatfrm", fm);
						FORWAD_SUCCESS = "ConfirmScreen";
						return mapping.findForward(FORWAD_SUCCESS);
					} else {
						request.setAttribute("errorsList", errorList);
					}
				//
				}
				session.setAttribute("caveatfrm", fm);
				if(actionName!=null)
				{
				if(actionName.equalsIgnoreCase("getProperty"))
				{
					if(cDto.getAddressOfInsti().equalsIgnoreCase("Y"))
					FORWAD_SUCCESS="propertyView";
					else
						FORWAD_SUCCESS="propertyView1";	
				cDto.setActionName(null);
					}
				else
					FORWAD_SUCCESS = "CreateScreen";
				}
					else
				FORWAD_SUCCESS = "CreateScreen";
			} else if ("modify".equalsIgnoreCase(request
					.getParameter("pageName"))) {
				fm.setCaveatsDTO((CaveatsDTO) request.getSession()
						.getAttribute("suppleDetails"));
				//fm.setCaveatsDTO((CaveatsDTO) session.getSession()
						//.getAttribute("suppleDetails"));
				cDto.setCaveatTypeList(refDAO.createCaveatsList(language));
				cDto.setCountryMasterList(refDAO.countryList(language));
				cDto.setStateMasterList(refDAO.stateList(cDto.getCountryId(),language));
				cDto.setDistrictMasterList(refDAO.districtStack(cDto
						.getStateId(),language));
				fm.setCaveatsDTO(cDto);
				session.setAttribute("caveatfrm", fm);
				request.getSession().setAttribute("frwdedByEdit", "yes");
				FORWAD_SUCCESS = "CreateScreen";
			}
			//added on 28 jan
			//To display property details
			else if("displayPropDtls".equalsIgnoreCase(request
					.getParameter("pageName")))
			{
				RegCompCheckerBD bd = new RegCompCheckerBD();
				String propertyId=request.getParameter("key");
				String id= request.getParameter("id");
//				logger.debug("<____---id"+id);
				String regInit = null;
				if(id.equals("id"))
				{
					regInit = cDto.getRegistrationNumber();
					//regInit = formDTO.getRegInitNumber();
				}
				else
				{
					
					regInit = bd.getInitiationNumber(id);
					
				}
				if(request.getParameter("page")!= null)
				{
					request.setAttribute("page","propHistory");
				}
        		logger.debug("VIEW PROP DETAILS");
 //       		logger.debug("PROPERTY ID"+propertyId);
        		//RegCompCheckerForm eForm = (RegCompCheckerForm)form;
        		fm.setMapPropertyTransPartyDisp
        		(bd.getPropertyDetsForViewConfirm(regInit, propertyId,language));
        		
        		FORWAD_SUCCESS = "propertyView";
				return mapping.findForward(FORWAD_SUCCESS);
        		
			}
			
			else if ("reset".equalsIgnoreCase(request.getParameter("pageName")))
			{
				cDto.setCaveatType("");
				cDto.setBankCourtName("");
				cDto.setBankCourtRepName("");
				cDto.setBankCourtAddress("");
				cDto.setCountryName("");
				cDto.setStateName("");
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
				FORWAD_SUCCESS = "CreateScreen";
			}
			return mapping.findForward(FORWAD_SUCCESS);
		} catch (Exception e) {
			//logger.error(e);
			return mapping.findForward("Failure");
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
	

	
	
	
	
	
	private boolean validateForm(CaveatsForm form, ArrayList errorList, HttpServletRequest request, Date currSysDate) {
		boolean retVal = false;
		errorList.clear();
		try {
			String tmpVal;
			CaveatsDTO dto = form.getCaveatsDTO();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar sysCal = Calendar.getInstance();
			sysCal.setTime(currSysDate);
	        sysCal.set(Calendar.HOUR_OF_DAY, 0);
	        sysCal.set(Calendar.MINUTE, 0);
	        sysCal.set(Calendar.SECOND, 0);
	        sysCal.set(Calendar.MILLISECOND, 0);
	        currSysDate = sysCal.getTime();
			sdf.setLenient(false);
			//Registration No
			if(dto.getRegistrationNumber() == null || "".equals(dto.getRegistrationNumber())) {
				errorList.add("Please specify a Registration Number/एक पंजीकरण संख्या निर्दिष्ट करें");
				//Selected Properties
				if(dto.getSelectedItems() == null || dto.getSelectedItems().isEmpty()) {
					errorList.add("Please select at least one Property ID from the Registration Search pop-up window/पंजीकरण खोज पॉप अप विंडो से कम से कम एक संपत्ति आईडी का चयन करें");
				}
			}
			if(dto.getDocContents()==null || dto.getDocContents().length==0) {
				errorList.add("Please upload and attach a supporting document/एक समर्थन दस्तावेज़ अपलोड करें और संलग्न करें");
			}
			if(dto.getRemarks() != null) {
				tmpVal = dto.getRemarks().trim();
				dto.setRemarks(tmpVal);
				if(("".equals(tmpVal)) == false) {
					if(tmpVal.length() > 200) {
						errorList.add("Remarks can contain upto 200 characters/रिमार्क्स 200 अक्षर तक शामिल कर सकते हैं");
					}
				}
			}
			if(dto.getCaveatDetails() == null || "".equals(dto.getCaveatDetails().trim())) {
				errorList.add("Please specify Protest Details/आपत्ति विवरण निर्दिष्ट करें");
			} else {
				tmpVal = dto.getCaveatDetails().trim();
				dto.setCaveatDetails(tmpVal);
				if(tmpVal.length() > 200) {
					errorList.add("Protest Details can contain upto 200 characters/चेतावनी विवरण 200 अक्षरों तक शामिल कर सकते हैं");
				}
			}
			if(dto.getCaveatType()==null||dto.getCaveatType().trim().equalsIgnoreCase("CAVEAT_TYPE_01"))
			{
				if(dto.getBankCourtRepName() == null || "".equals(dto.getBankCourtRepName().trim())) {
					errorList.add("Please specify Representative Name /प्रतिनिधि का नाम निर्दिष्ट करें");
				} 
				if(dto.getStayOrderDetails() == null ||"".equals(dto.getStayOrderDetails().trim()))
				{
					errorList.add("Please specify Stay Order Details. /रोक के विवरण  का नाम निर्दिष्ट करें");
				}
				else {
					tmpVal = dto.getCaveatDetails().trim();
					dto.setCaveatDetails(tmpVal);
					if(tmpVal.length() > 200) {
						errorList.add("Stay Order Details can contain upto 200 characters/आदेश विवरण 200 अक्षरों तक  शामिल कर सकते हैं");
					}
				}
				System.out.println(dto.getPropertyTxnLock());
				if(dto.getPropertyTxnLock().equalsIgnoreCase("-1"))
				{
					System.out.println("in");
					errorList.add("Please select Lock Transcation or not  /लॉक लेनदेन का चयन करें ");
				}
				
			}
			boolean flagStartDate = false;
			boolean flagEndDate = false;
			Date startDate = null;
			Date endDate = null;
			
					
			
			
			
			if(dto.getStayOrderStartDate()==null || "".equals(dto.getStayOrderStartDate().trim())) {
				errorList.add("Please specify Start Date/प्रारंभ तिथि निर्दिष्ट करें");

			}
			
				if(dto.getStayOrderStartDate() != null) {
					tmpVal = dto.getStayOrderStartDate().trim();
					dto.setStayOrderStartDate(tmpVal);
					if ("".equals(tmpVal) != true) {
						try {
							startDate = sdf.parse(tmpVal);
							flagStartDate = true;
						} catch (ParseException e) {
							errorList
									.add("Please enter proper Stay Order Start Date/उचित स्टे ऑर्डर प्रारंभ दिनांक दर्ज करें");
						}
					}
					}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			if(dto.getStayOrderUptoDate() != null) {
				tmpVal = dto.getStayOrderUptoDate().trim();
				dto.setStayOrderUptoDate(tmpVal);
				if ("".equals(tmpVal)!= true) {
					try {
						endDate = sdf.parse(tmpVal);
						flagEndDate = true;
					} catch (ParseException e) {
						errorList
								.add("Please enter proper Stay Order Upto Date/ उचित स्टे ऑर्डर तिथि तक दर्ज करें");
					}
				}
			}
			
			
			if(flagEndDate) {
				if (endDate.before(currSysDate)) {
					errorList.add("Please enter proper Stay Order Upto Date (Past dates are not allowed)/उचित स्टे ऑर्डर तिथि तक दर्ज करेंविगत दिनांक अनुमति नहीं है)");
				}
			}
			if(flagStartDate != true && flagEndDate) {
				errorList
				.add("Please enter Stay Order Start Date/स्टे ऑर्डर प्रारंभ दिनांक दर्ज करें");
			}
			
			if(flagStartDate && flagEndDate) {

				if (endDate.before(startDate)) {
					errorList.add("Please enter proper Stay Order Start Date and Stay Order Upto Date/उचित स्टे ऑर्डर प्रारंभ दिनांक और स्टे ऑर्डर तिथि तक दर्ज करें");
				}
				
			}
			
			if(dto.getStayOrderDetails() != null) {
				tmpVal = dto.getStayOrderDetails().trim();
				dto.setStayOrderDetails(tmpVal);
				
				if(("".equals(tmpVal)) == false) {
					if(tmpVal.length() > 200) {
						errorList.add("Stay Order Details can contain upto 200 characters/स्टे ऑर्डर विवरण 200 अक्षरों तक शामिल कर सकते हैं");
					}
				}
			}
			if(dto.getBankCourtRepName() != null) {
				tmpVal = dto.getBankCourtRepName().trim();
				dto.setBankCourtRepName(tmpVal);
				
				String value=dto.getLanguage();
				if(value.equalsIgnoreCase("en"))
				{
				if(("".equals(tmpVal)) == false) {
					if(tmpVal.trim().length() > 30) {
						errorList.add("Representative Name can contain upto 30 characters/प्रतिनिधि का नाम 30 अक्षरों तक शामिल कर सकते हैं");
					} else if(tmpVal.matches("[a-zA-Z\\s]*") != true) {
						errorList.add("Please enter proper Representative Name/उचित प्रतिनिधि का नाम दर्ज करें");
					}
					
				}
			}
			
			else
			{
				if(("".equals(tmpVal)) == false) {
					if(tmpVal.trim().length() > 30) {
						errorList.add("Representative Name can contain upto 30 characters/प्रतिनिधि का नाम 30 अक्षरों तक शामिल कर सकते हैं");
					} else if(tmpVal.matches("[0-9]*") == true) {
						errorList.add("Please enter proper Representative Name/उचित प्रतिनिधि का नाम दर्ज करें");
					}
					
					
				}
			}
			}
			if(dto.getStayOrderNo() != null) {
				tmpVal = dto.getStayOrderNo().trim();
				dto.setStayOrderNo(tmpVal);
				if(("".equals(tmpVal)) == false) {
					if(tmpVal.length() > 30) {
						errorList.add("Stay Order Number can contain upto 30 characters/स्टे आदेश संख्या 30 अक्षरों तक शामिल कर सकते हैं");
					}
				}
			}
			if(dto.getBankCourtPostalCode() != null) {
				tmpVal = dto.getBankCourtPostalCode().trim();
				dto.setBankCourtPostalCode(tmpVal);
				if(("".equals(tmpVal)) == false) {
					if(tmpVal.trim().length() != 6) {
						errorList.add("Postal Code can contain exactly 6 characters/कोड बिल्कुल 6 वर्ण हो सकते हैं");
					} else if(tmpVal.matches("[0-9]*") != true) {
						errorList.add("Please enter proper Postal Code/उचित कोड दर्ज करें");
					}
				}
			}
			//modified by ashima
			if(dto.getBankCourtPhoneNumber() != null) {
				tmpVal = dto.getBankCourtPhoneNumber().trim();
				dto.setBankCourtPhoneNumber(tmpVal);
				if(("".equals(tmpVal)) == false) {
					if( tmpVal.trim().length() < 10  ) {
						errorList.add("Phone/Mobile Number should contain min 10 characters /फोन / मोबाइल नंबर  कम से कम 10 वर्ण तक हो सकते हैं");
					}
					if(tmpVal.trim().length() > 13 ) {
						errorList.add("Phone/Mobile Number can contain max 13 characters/फोन / मोबाइल नंबर  13 वर्ण तक हो सकते हैं");
					}
					 else if(tmpVal.matches("[0-9\\+\\-\\(\\)]*") != true) {
						errorList.add("Please enter proper Phone/Mobile Number/उचित फोन / मोबाइल नंबर दर्ज करें");
					}
				}
			}
			//ADDED BY ASHIMA
			if(dto.getCaseNum() == null || "".equals(dto.getCaseNum())) {
				errorList.add("Please specify a Case Number/एक केस संख्या निर्दिष्ट करें");
			}
			if(dto.getCaseNum() != null) {
				tmpVal = dto.getCaseNum().trim();
				dto.setCaseNum(tmpVal);
				if(("".equals(tmpVal)) == false) {
					if(tmpVal.trim().length() > 20) {
						errorList.add("Case Number can contain upto 20 characters/ केस  संख्या  20 वर्ण तक हो सकते हैं");
					} /*else if(tmpVal.matches("[0-9\\+\\-\\(\\)]*") != true) {
						errorList.add("Please enter proper Case number/उचित केस संख्या दर्ज करें");
					}*/
				}
			}	
			
			if(dto.getCourtOrderDate() == null || "".equals(dto.getCourtOrderDate())) {
				errorList.add("Please specify a Court Order Date/एक न्यायालय आदेश तिथि निर्दिष्ट करें");
			}
			
			if(dto.getDistrictId() == null || "".equals(dto.getDistrictId())) {
				errorList.add("Please specify District/जिला निर्दिष्ट करें");
			}
			if(dto.getStateId() == null || "".equals(dto.getStateId())) {
				errorList.add("Please specify State/राज्य निर्दिष्ट करें");
			}
			if(dto.getCountryId() == null || "".equals(dto.getCountryId())) {
				errorList.add("Please specify Country/देश निर्दिष्ट करें");
			}
			if(dto.getBankCourtAddress() == null || "".equals(dto.getBankCourtAddress().trim())) {
				errorList.add("Please specify Address of Bank/Court/Others/बैंक / न्यायालय / अन्य का पता निर्दिष्ट करें");
			} else {
				tmpVal = dto.getBankCourtAddress().trim();
				dto.setBankCourtAddress(tmpVal);
				if (tmpVal.length() > 200) {
					errorList.add("Address of Bank/Court/Others can contain upto 200 characters/बैंक / न्यायालय / अन्य का पता 200 वर्ण  तक हो सकते हैं");
				}
			}
			
			
			if(dto.getBankCourtName() == null || "".equals(dto.getBankCourtName().trim())) {
				errorList.add("Please specify Name of Bank/Court/Others/बैंक / न्यायालय / अन्य के नाम निर्दिष्ट करें");
			} else {
				tmpVal = dto.getBankCourtName().trim();
				dto.setBankCourtName(tmpVal);
				if(tmpVal.length() > 30) {
					errorList.add("Name of Bank/Court/Others can contain upto 30 characters/बैंक / न्यायालय / अन्य के नाम 30 अक्षरों तक शामिल कर सकते हैं");
				}
			}
			
			if(dto.getCaveatType() == null || "".equals(dto.getCaveatType())) {
				errorList.add("Please specify Type Of Protest/आपत्ति के प्रकार निर्दिष्ट करें");
			}
			
			//logger.debug("Number of error messages : " + errorList.size());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			Collections.reverse(errorList);
			retVal = errorList.isEmpty() != true;
		}
		return retVal;
		
	}

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
	/*private String getFileExtension(String fileName) {
		try {
			int pos = fileName.lastIndexOf(".");
			String strExt = fileName.substring(pos + 1, fileName.length());
			return strExt;
		} catch (Exception e) {
		}
		return "";
	}
	*/
	
	
	
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
