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
 * 1.0             SHRUTI           xxxxx              
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.documentsearch.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.octo.captcha.service.CaptchaServiceException;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.common.CommonConstant;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.deedDraft.constant.DeedDraftConstant;
import com.wipro.igrs.documentsearch.bd.DocumentSearchBD;
import com.wipro.igrs.documentsearch.bd.OldDocumentSearchBD;
import com.wipro.igrs.documentsearch.bo.DocumentSearchBO;
import com.wipro.igrs.documentsearch.dto.DocumentSearchDTO;
import com.wipro.igrs.documentsearch.dto.OldDocSearchDashboardDTO;
import com.wipro.igrs.documentsearch.dto.PartyDetailsDTO;
import com.wipro.igrs.documentsearch.form.DocumentSearchForm;
import com.wipro.igrs.newPropvaluation.bo.PropertyValuationBO;

public class DocumentSearchAction extends BaseAction {
	
	private Logger logger = (Logger) Logger.getLogger(DocumentSearchAction.class);
	
	String forwardPage;
	String userid;
	String userId = "";
	String noofyears = "";
	Locale currentLocale;
	String country;
	ResourceBundle messages;
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException, Exception {
		logger.debug("WE ARE IN DocumentSearchAction ACTION");
		String language = (String) session.getAttribute("languageLocale");
		PartyDetailsDTO partydto = new PartyDetailsDTO();
		String pageName = (String) request.getParameter("pageName");
		String spotReg = (String) request.getParameter("searchSpotRegNumber");
		String formNameNU = mapping.getName();
		PropertyValuationBO newPropBO = new PropertyValuationBO();
		if (pageName == null) {
			pageName = "";
		}
		logger.debug("Page Name-->" + request.getParameter("pageName"));
		try {
			DocumentSearchForm dform = (DocumentSearchForm) form;
			dform.setLanguage(language);
			logger.debug("dform.getDsearchdto().getActionName()--<>" + dform.getDsearchdto().getActionName());
			DocumentSearchDTO feeDsdto = new DocumentSearchDTO();
			DocumentSearchDTO dsdto = dform.getDsearchdto();
			DocumentSearchBD bd = new DocumentSearchBD();
			DocumentSearchBO bo = new DocumentSearchBO();
			userId = (null == session.getAttribute("UserId")) ? "" : (String) (session.getAttribute("UserId").toString());
			dsdto.setUserTypeId(bo.chkUser(userId));
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			String dms = pr.getValue("ImageServerIP");
			String port = pr.getValue("port");
			dform.getDsearchdto().setDms(dms);
			dform.getDsearchdto().setPort(port);
			if ("documentsearchtypeAonenu".equalsIgnoreCase(formNameNU)) {
				dsdto.setUserTypeId("");
			}
			String officeId = (String) session.getAttribute("loggedToOffice");
			if (dsdto.getUserTypeId().equalsIgnoreCase("I")) {
				ArrayList dtlList = (ArrayList) bo.getInternalUserDtls(officeId);
				dsdto.setParentOfficeId(officeId);
				for (int i = 0; i < dtlList.size(); i++) {
					ArrayList list = (ArrayList) dtlList.get(0);
					dsdto.setParentOfficeName((String) list.get(0));
					dsdto.setParentDistrictId((String) list.get(1));
					dsdto.setParentDistrictName((String) list.get(2));
				}
			} else if (dsdto.getUserTypeId().equalsIgnoreCase("2")) {
				dsdto.setParentDistrictName("NA");
				dsdto.setParentDistrictId("NA");
				dsdto.setParentOfficeId("NA");
				dsdto.setParentOfficeName("NA");
			} else {
				ArrayList dtlList = bo.getExternalUserDtls(userId);
				for (int i = 0; i < dtlList.size(); i++) {
					ArrayList list = (ArrayList) dtlList.get(0);
					dsdto.setParentDistrictName((String) list.get(0));
					dsdto.setParentDistrictId((String) list.get(1));
				}
				dsdto.setParentOfficeId("NA");
				dsdto.setParentOfficeName("NA");
			}
			// code in use for spot inspection module
			if (pageName != null && spotReg != null && pageName.equalsIgnoreCase("fromSpot")) {
				String regNo = spotReg;
				String userId = (String) session.getAttribute("UserId");
				if (regNo != null && !regNo.equals("")) {
					DocumentSearchDTO resultdto = bd.checkRegistrationId(regNo, language);
					String checkProtestDetls = bd.getProtestFlag(dsdto.getRegistNumber());
					logger.info(resultdto.getKhasraList().size());
					request.setAttribute("abc", resultdto.getKhasraList());
					logger.debug(" in action --<><>" + resultdto);
					session.setAttribute("resultdto", resultdto);
					if (resultdto != null) {
						logger.debug("--in action result --<>" + resultdto.getTypeBresult());
						ArrayList refprtList = (ArrayList) resultdto.getPartyDTO().getPartyList();
						logger.debug("party list value size is " + refprtList.size());
						partydto.setPartyList(refprtList);
						dform.setPartyDto(partydto);
						dsdto.setTypeBresult(resultdto.getTypeBresult());
						dsdto.setPropertyList(resultdto.getPropertyList());
						dsdto.setKhasraList(resultdto.getKhasraList());
						if (checkProtestDetls != null && !checkProtestDetls.equals("")) {
							dsdto.setProtestList(resultdto.getProtestList());
						}
						dsdto.setCaveatslist(resultdto.getCaveatslist());
						dsdto.setComplianceList(resultdto.getComplianceList());
						dsdto.setApplicationId(resultdto.getApplicationId());
						logger.debug("Party list size in form " + dform.getPartyDto().getPartyList().size());
						forwardPage = "docSearch";
					}
				}
			}
			// end
			
			if (request.getParameter("pageName") != null && request.getParameter("pageName").equalsIgnoreCase("officeTypeA")) {
				logger.debug("in if search---->" + request.getParameter("pageName"));
				
				dsdto = new DocumentSearchDTO();
				dsdto.setIsCompletePay(1);
				dform.getDsearchdto().setSearchType("OfficeType");
				dsdto.setSearchType("OfficeType");
				ArrayList dist = bd.getDistrict(language);
				dsdto.setDistList(dist);
				ArrayList areaList = bd.getAreaType(language);
				dsdto.setAreaList(areaList);
				ArrayList fiscalYear = bd.getFinancialYear(language);
				dsdto.setFinancialYearList(fiscalYear);
				forwardPage = "officedocsearcha";
			}
			
			if (pageName != null && pageName.equalsIgnoreCase("sroName")) {
				ArrayList dist = bd.getDistrict(language);
				ArrayList areaList = bd.getAreaType(language);
				ArrayList fiscalYear = bd.getFinancialYear(language);
				ArrayList sroList = new ArrayList();
				if (dform.getDsearchdto().getDistId() != null) {
					sroList = bd.getSroList(dform.getDsearchdto().getDistId(), language);
				}
				dsdto.setDistList(dist);
				dsdto.setSroList(sroList);
				dsdto.setAreaList(areaList);
				dform.setDsearchdto(dsdto);
				dsdto.setFinancialYearList(fiscalYear);
				forwardPage = "usertypeasearchinput";
			}
			// added by Shreeraj
			if (pageName != null && pageName.equalsIgnoreCase("sroNameC")) {
				ArrayList dist = bd.getDistrict(language);
				ArrayList areaList = bd.getAreaType(language);
				ArrayList fiscalYear = bd.getFinancialYear(language);
				ArrayList sroList = new ArrayList();
				if (dform.getDsearchdto().getDistId() != null) {
					sroList = bd.getSroList(dform.getDsearchdto().getDistId(), language);
				}
				dsdto.setDistList(dist);
				dsdto.setSroList(sroList);
				dsdto.setAreaList(areaList);
				dform.setDsearchdto(dsdto);
				dsdto.setFinancialYearList(fiscalYear);
				forwardPage = "usertypeacsearchinput";
			}
			
			// on click of admin search menu item
			if (request.getParameter("pageName") != null && request.getParameter("pageName").equalsIgnoreCase("logSearch")) {
				forwardPage = "adminSearch";
			}
			// end
			
			// added by shruti---18 august 2014---optional upload
			if (request.getParameter("pageName") != null && request.getParameter("pageName").equalsIgnoreCase("optionalUpload")) {
				try {
					ArrayList errorList = new ArrayList();
					FormFile uploadedFile = dform.getDsearchdto().getOffUpload();
					byte contents[] = uploadedFile.getFileData();
					dform.getDsearchdto().setOffUploadContents(contents);
					if ("".equals(uploadedFile.getFileName())) {
						errorList.add("Invalid file selection. Please try again.\n\n अवैध फ़ाइल चयन |पुन: प्रयास करें |");
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
						errorList.add("Invalid file type. Please select another file.\n\n अमान्य फ़ाइल प्रकार |अन्य फाइल चुनें |");
						request.setAttribute("errorsList", errorList);
					} else {
						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
							errorList.add("File size is Greater than 10 MB. Please select another file.\n\n फ़ाइल का आकार 10 MB से अधिक है| अन्य फाइल चुनें|");
							request.setAttribute("errorsList", errorList);
						} else {
							String docName = "optionalDoc." + fileExt;
							dform.getDsearchdto().setOffUploadDoc(uploadedFile.getFileName());
						}
					}
					dform.getDsearchdto().setRegFlag(dform.getDsearchdto().getRegFlag());
					forwardPage = "officedocsearcha";
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			// added by shruti---18 august 2014
			if (request.getParameter("pageName") != null && request.getParameter("pageName").equalsIgnoreCase("optionalUpload1")) {
				try {
					ArrayList errorList = new ArrayList();
					FormFile uploadedFile = dform.getDsearchdto().getOffUpload();
					byte contents[] = uploadedFile.getFileData();
					dform.getDsearchdto().setOffUploadContents(contents);
					if ("".equals(uploadedFile.getFileName())) {
						errorList.add("Invalid file selection. Please try again.\n\n अवैध फ़ाइल चयन |पुन: प्रयास करें |");
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
						errorList.add("Invalid file type. Please select another file.\n\n अमान्य फ़ाइल प्रकार |अन्य फाइल चुनें |");
						request.setAttribute("errorsList", errorList);
					} else {
						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
							errorList.add("File size is Greater than 10 MB. Please select another file.\n\n फ़ाइल का आकार 10 MB से अधिक है| अन्य फाइल चुनें|");
							request.setAttribute("errorsList", errorList);
						} else {
							String docName = "optionalDoc." + fileExt;
							dform.getDsearchdto().setOffUploadDoc(uploadedFile.getFileName());
						}
					}
					forwardPage = "officedocsearchc";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// end
			
			// added by shruti----19 august 2014
			if (request.getParameter("actionName") != null && "downLoadOffDoc".equalsIgnoreCase((String) request.getParameter("actionName"))) {
				try {
					byte contents[] = dform.getDsearchdto().getOffUploadContents();
					String fileName = dform.getDsearchdto().getOffUploadDoc();
					downloadDocument(response, contents, fileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (request.getParameter("pageName") != null && "removeUploadFile".equalsIgnoreCase((String) request.getParameter("pageName"))) {
				try {
					FormFile fname = (FormFile) dform.getDsearchdto().getOffUpload();
					if (fname != null) {
						forwardPage = "officedocsearcha";
					} else {
						String name = dform.getDsearchdto().getOffUploadDoc();
						String path = "";
						removeFile(name, path);
						dform.getDsearchdto().setOffUploadDoc("");
						forwardPage = "officedocsearcha";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// end
			// for displaying 1st screen for log details
			if (request.getParameter("pageName") != null && request.getParameter("pageName").equalsIgnoreCase("logDetails")) {
				String criteria = dform.getDsearchdto().getLogCriteria();
				String toDate = dform.getToDate();
				if (criteria.equalsIgnoreCase("1")) {
					ArrayList srchLogList = bd.getSrchLogList(toDate);
					if (srchLogList != null || srchLogList.size() > 0) {
						request.setAttribute("srchLogList", srchLogList);
						forwardPage = "adminDashboard";
					} else {
						forwardPage = "adminDashboard";
					}
				} else if (criteria.equalsIgnoreCase("2")) {
					ArrayList srchLogList = bd.getDownloadLogList(toDate);
					if (srchLogList != null || srchLogList.size() > 0) {
						request.setAttribute("srchLogList", srchLogList);
						forwardPage = "adminDashboard";
					} else {
						forwardPage = "adminDashboard";
					}
				}
				dform.setToDate(null);
			}
			// end
			
			if (request.getParameter("pageName") != null && request.getParameter("pageName").equalsIgnoreCase("userIdRecordView")) {
				// ==null condition added because !=null condition failing
				if (dsdto.getSrchdUserId() == null) {
					userid = request.getParameter("srchdUserId");
					dsdto.setSrchdUserId(userid);
				} else {
					userid = dsdto.getSrchdUserId();
				}
				ArrayList detailsList = bd.getUserDetailsList(userid);
				if (detailsList != null || detailsList.size() > 0) {
					request.setAttribute("detailsList", detailsList);
					forwardPage = "detailsDashboard";
				} else {
					forwardPage = "detailsDashboard";
				}
			}
			
			/*
			 * //on click of search type b in menu item if
			 * (request.getParameter("pageName") != null &&
			 * request.getParameter("pageName").equalsIgnoreCase("officeTypeB"))
			 * { String userId = session.getAttribute("UserId").toString();
			 * dsdto.setServiceType("FUN_063"); ArrayList
			 * srchBList=bd.getSrchBResultList(userId);
			 * feeDsdto=bd.getOthersFeeDuty("FUN_063", "18",
			 * dsdto.getUserTypeId()); session.setAttribute("Fee",
			 * feeDsdto.getServiceFee()); session.setAttribute("Other_Fee",
			 * feeDsdto.getOtherFee()); //added by shruti--------8 july 2014
			 * if(feeDsdto.getTotalFee()!=null) { if(dform.getFromDate()!=null
			 * && dform.getToDate()!=null) {
			 * logger.debug("Date value is "+dform.getFromDate());
			 * logger.debug("Date value is "+dform.getToDate());
			 * noofyears=bd.getNoOfDocYears(dform.getFromDate(),
			 * dform.getToDate());
			 * logger.info("NO of doc years>>>>>>>>>>>>>>>>>>>"+noofyears); int
			 * totalamt
			 * =Integer.parseInt(noofyears)*Integer.parseInt(feeDsdto.getTotalFee
			 * ()); String totalfee=String.valueOf(totalamt);
			 * feeDsdto.setTotalFee(totalfee); } }
			 * session.setAttribute("Total_Fee", feeDsdto.getTotalFee());
			 * if(srchBList.isEmpty()) { dform.setFromDate("");
			 * dform.setToDate(""); dsdto=new DocumentSearchDTO();
			 * logger.debug("m here in officeTypeB ---------------------------- "
			 * ); ArrayList dist = bd.getDistrict(language);
			 * dsdto.setDistList(dist); ArrayList regDistList =
			 * bd.getRegsitrationDistrict(language);
			 * dsdto.setRegDistList(regDistList); ArrayList areaList =
			 * bd.getAreaType(language); dsdto.setAreaList(areaList);
			 * dsdto.setServiceType("FUN_063"); forwardPage = "usertypeb"; }
			 * else if(srchBList!=null || srchBList.size()>0) {
			 * request.setAttribute("srchBList", srchBList);
			 * forwardPage="searchBDashboard"; } else { dform.setFromDate("");
			 * dform.setToDate(""); dsdto=new DocumentSearchDTO();
			 * logger.debug("m here in officeTypeB ---------------------------- "
			 * ); ArrayList dist = bd.getDistrict(language); ArrayList
			 * regDistList = bd.getRegsitrationDistrict(language); ArrayList
			 * areaList = bd.getAreaType(language); dsdto.setAreaList(areaList);
			 * dsdto.setDistList(dist); dsdto.setRegDistList(regDistList);
			 * dsdto.setServiceType("FUN_063"); forwardPage = "usertypeb"; } }
			 */

			// added by shruti
			/*
			 * String newsrchB=request.getParameter("newUserSearchB");
			 * if(newsrchB!=null && newsrchB.equals("true")) {
			 * dform.setFromDate(""); dform.setToDate(""); dsdto=new
			 * DocumentSearchDTO();
			 * logger.debug("m here in officeTypeB ---------------------------- "
			 * ); ArrayList regDistList = bd.getRegsitrationDistrict(language);
			 * ArrayList areaList = bd.getAreaType(language);
			 * dsdto.setAreaList(areaList); dsdto.setRegDistList(regDistList);
			 * //dsdto.setDistList(dist); //dsdto.setDistList1(dist);
			 * dsdto.setServiceType("FUN_063"); forwardPage = "usertypeb"; }
			 * //end
			 */
			if (request.getParameter("pageName") != null && request.getParameter("pageName").equalsIgnoreCase("officeTypeC")) {
				logger.debug("m here in officeTypeC ---------------------------- ");
				dform.setFromDate("");
				dform.setToDate("");
				
				dsdto = new DocumentSearchDTO();
				logger.debug("m here in officeTypeB ---------------------------- ");
				ArrayList dist = bd.getDistrict(language);
				dsdto.setDistList(dist);
				ArrayList regDistList = bd.getRegsitrationDistrict(language);
				dsdto.setRegDistList(regDistList);
				ArrayList areaList = bd.getAreaType(language);
				dsdto.setAreaList(areaList);
				ArrayList fiscalYear = bd.getFinancialYear(language);
				dsdto.setFinancialYearList(fiscalYear);
				dsdto.setServiceType("FUN_064");
				dsdto.setSearchType("FUN_064");
				dsdto.setUserType("I");
				dsdto.setTotalFee("0");
				forwardPage = "usertypeb";
			}
			if (request.getParameter("pageName") != null && request.getParameter("pageName").equalsIgnoreCase("officedocsearchc")) {
				if (request.getParameter("getAction") != null) {
					String getAction = request.getParameter("getAction").toString();
					if (getAction.equalsIgnoreCase("tehsil")) {
						ArrayList tehsilList = bd.getTehisil(dform.getDsearchdto().getDistId(), language);
						if (tehsilList != null) {
							dsdto.setTehsiList(tehsilList);
						} else {
							dsdto.setTehsiList(new ArrayList());
						}
						dsdto.setTehisilId("");
						dsdto.setTehsiList(tehsilList);
						dsdto.setAreaTypeId("");
						dsdto.setSubAreaTypeId("");
						dsdto.setHdnSubAreaWardMappingId("");
						dsdto.setMohallaId("");
						dsdto.setAreaList(bd.getAreaType(language));
						dsdto.setSubAreaTypeList(new ArrayList());
						dsdto.setWardList(new ArrayList());
						dsdto.setMohallaList(new ArrayList());
					}
					if (getAction.equalsIgnoreCase("tehsilChange")) {
						dsdto.setSubAreaTypeId("");
						dsdto.setHdnSubAreaWardMappingId("");
						dsdto.setMohallaId("");
						String urbanFlag = "N";
						if ("2".equalsIgnoreCase(dform.getDsearchdto().getAreaTypeId())) {
							urbanFlag = "Y";
						}
						if (dform.getDsearchdto().getTehisilId() != null && !dform.getDsearchdto().getTehisilId().equalsIgnoreCase("")) {
							ArrayList subAreaList = newPropBO.getSubArea(language, dform.getDsearchdto().getAreaTypeId(), dform.getDsearchdto().getTehisilId(), urbanFlag);
							if (subAreaList != null) {
								dsdto.setSubAreaTypeList(subAreaList);
							} else {
								dsdto.setSubAreaTypeList(new ArrayList());
							}
						} else {
							dsdto.setAreaTypeId("");
							dsdto.setSubAreaTypeList(new ArrayList());
						}
						
						dsdto.setWardList(new ArrayList());
						dsdto.setMohallaList(new ArrayList());
					}
					if (getAction.equalsIgnoreCase("subArea")) {
						String urbanFlag = "N";
						if ("2".equalsIgnoreCase(dform.getDsearchdto().getAreaTypeId())) {
							urbanFlag = "Y";
						}
						ArrayList subAreaList = newPropBO.getSubArea(language, dform.getDsearchdto().getAreaTypeId(), dform.getDsearchdto().getTehisilId(), urbanFlag);
						if (subAreaList != null) {
							dsdto.setSubAreaTypeList(subAreaList);
						} else {
							dsdto.setSubAreaTypeList(new ArrayList());
						}
						dsdto.setSubAreaTypeId("");
						dsdto.setHdnSubAreaWardMappingId("");
						dsdto.setMohallaId("");
						dsdto.setWardList(new ArrayList());
						dsdto.setMohallaList(new ArrayList());
					}
					if (getAction.equalsIgnoreCase("wardPatwari")) {
						dsdto.setHdnSubAreaWardMappingId("");
						dsdto.setMohallaId("");
						ArrayList wardlist = newPropBO.getWardPatwari(language, dform.getDsearchdto().getSubAreaTypeId(), dform.getDsearchdto().getTehisilId());
						if (wardlist != null) {
							dsdto.setWardList(wardlist);
						} else {
							dsdto.setWardList(new ArrayList());
						}
						dsdto.setMohallaList(new ArrayList());
					}
					
					if (getAction.equalsIgnoreCase("colony")) {
						dsdto.setMohallaId("");
						ArrayList mohallaList = newPropBO.getColonyName(language, dform.getDsearchdto().getHdnSubAreaWardMappingId());
						if (mohallaList != null) {
							dsdto.setMohallaList(mohallaList);
						} else {
							dsdto.setMohallaList(new ArrayList());
						}
					}
				} else {
					
					ArrayList dist = bd.getDistrict(language);
					dsdto.setDistList(dist);
				}
				ArrayList tehsilList = new ArrayList();
				ArrayList patwariList = new ArrayList();
				ArrayList mohallaList = new ArrayList();
				ArrayList villageList = new ArrayList();
				String flag = dform.getDsearchdto().getRegFlag();
				logger.info("Value of flag is:::::: " + flag);
				dsdto.setFlag(flag);
				ArrayList dist = bd.getDistrict(language);
				ArrayList areaList = bd.getAreaType(language);
				if (dform.getDsearchdto().getAreaTypeId() != "") {
					ArrayList subAreaList = bd.getSubAreaTypeId(dform.getDsearchdto().getAreaTypeId(), language);
				}
				
				logger.debug("Date value is " + dform.getFromDate());
				logger.debug("Date value is " + dform.getToDate());
				noofyears = bd.getNoOfDocYears(dform.getFromDate(), dform.getToDate());
				logger.info("NO of doc years>>>>>>>>>>>>>>>>>>>" + noofyears);
				logger.info(dform.getDsearchdto().getDistId());
				if (dform.getDsearchdto().getDistId() != null) {
					logger.info("The value of flag is::::::::" + flag);
					if (dform.getDsearchdto().getRegFlag() != "") {
						if (flag.equals("new")) {
							dsdto.setRadiobutton("e");
							logger.info(dsdto.getRadiobutton());
							request.setAttribute("radio", dsdto.getRadiobutton());
							tehsilList = bd.getTehisil(dform.getDsearchdto().getDistId(), language);
							ArrayList wardlist = new ArrayList();
							ArrayList mohallalist = new ArrayList();
							logger.debug("--dform.getDsearchdto().getAreaType()--<><>" + dform.getDsearchdto().getAreaTypeId());
							logger.debug("--dform.getDsearchdto().getWardpatwarId()--<><>" + dform.getDsearchdto().getWardpatwarId());
							if (dform.getDsearchdto().getSubAreaTypeId() != null) {
								wardlist = bd.getWardOrPatwari((DocumentSearchDTO) dform.getDsearchdto(), language);
							}
							if (dform.getDsearchdto().getHdnSubAreaWardMappingId() != null) {
								String[] splitmapid = dform.getDsearchdto().getHdnSubAreaWardMappingId().split("~");
								// dform.getDsearchdto().setSubAreaWardMappingId(splitmapid[0]);
								mohallalist = bd.getMohallaOrVillage((DocumentSearchDTO) dform.getDsearchdto(), language);
								// dsdto.setMohallaList(mohallalist);
							}
							if (tehsilList.size() > 0) {
								dform.getDsearchdto().setDistId(dform.getDsearchdto().getDistId());
							} else {
								dform.getDsearchdto().setTehisilId("");
								dform.getDsearchdto().setTehisilName("");
								tehsilList.add(dform.getDsearchdto());
							}
							if (wardlist.size() > 0) {// dsdto.setWardList(wardlist);
							
							} else {
								dform.getDsearchdto().setWardNo("");
								dform.getDsearchdto().setWardORpathivasHalka("");
								// wardlist.add(dform.getDsearchdto());
							}
							dsdto.setDistId(dform.getDsearchdto().getDistId());
							dsdto.setTehisilId(dform.getDsearchdto().getTehisilId());
							dsdto.setAreaTypeId(dform.getDsearchdto().getAreaTypeId());
							dsdto.setWardId(dform.getDsearchdto().getWardId());
							dsdto.setMohallaId(dform.getDsearchdto().getMohallaId());
							dsdto.setPatwariId(dform.getDsearchdto().getPatwariId());
							dsdto.setVillageId(dform.getDsearchdto().getVillageId());
							// dsdto.setTehsiList(tehsilList);
							// dsdto.setAreaList(areaList);
						}

						else {
							tehsilList = bd.getTehisil(dform.getDsearchdto().getDistId1(), language);
							if (tehsilList.size() > 0) {
								logger.info("old ID" + dform.getDsearchdto().getDistId1());
								dform.getDsearchdto().setDistId1(dform.getDsearchdto().getDistId1());
							} else {
								dform.getDsearchdto().setTehisilId("");
								dform.getDsearchdto().setTehisilName("");
								tehsilList.add(dform.getDsearchdto());
							}
							dsdto.setDistId1(dform.getDsearchdto().getDistId1());
							dsdto.setTehisilId(dform.getDsearchdto().getTehisilId());
						}
					}
				}
				// dsdto.setDistList(dist);
				logger.debug("--in action dist list type b--<>" + dsdto.getDistList().size());
				logger.debug("in if usertypeadisp---->" + request.getParameter("pageName"));
				dsdto.setToDate(dsdto.getToDate());
				dsdto.setFromDate(dsdto.getFromDate());
				forwardPage = "officedocsearchc";
			}
			// dashboard integration for search type a
			if (pageName != null && pageName.equals("usertypeastart")) {
				String userId = session.getAttribute("UserId").toString();
				dsdto.setServiceType("FUN_062");
				ArrayList srchAList = bd.getSrchAResultList(userId);
				OldDocumentSearchBD oldDocumentSearchBD = new OldDocumentSearchBD();
				List<OldDocSearchDashboardDTO> oldSrchAList = oldDocumentSearchBD.getOldSrchAResult(userId);
				session.setAttribute("oldSrchAList", oldSrchAList);
				request.setAttribute("srchAList", srchAList);
				forwardPage = "searchADashboard";
				/*
				 * if(srchAList.isEmpty()) { dsdto=new DocumentSearchDTO();
				 * dsdto.setIsCompletePay(1); dsdto.setServiceType("FUN_062");
				 * ArrayList dist = bd.getDistrict(language);
				 * dsdto.setDistList(dist); ArrayList areaList =
				 * bd.getAreaType(language); dsdto.setAreaList(areaList);
				 * forwardPage = "usertypeasearchinput"; } else
				 * if(srchAList!=null || srchAList.size()>0) {
				 * request.setAttribute("srchAList", srchAList);
				 * forwardPage="searchADashboard"; } else { dsdto=new
				 * DocumentSearchDTO(); dsdto.setServiceType("FUN_062");
				 * ArrayList dist = bd.getDistrict(language);
				 * dsdto.setDistList(dist); ArrayList areaList =
				 * bd.getAreaType(language); dsdto.setAreaList(areaList);
				 * forwardPage = "usertypeasearchinput"; }
				 */
			}
			
			// on click of new search hyperlink for search a
			String newsrchA = request.getParameter("newUserSearchA");
			if (newsrchA != null && newsrchA.equals("true")) {
				dsdto = new DocumentSearchDTO();
				dsdto.setIsCompletePay(1);
				dsdto.setServiceType("FUN_062");
				ArrayList dist = bd.getDistrict(language);
				dsdto.setDistList(dist);
				ArrayList areaList = bd.getAreaType(language);
				dsdto.setAreaList(areaList);
				ArrayList fiscalYear = bd.getFinancialYear(language);
				dsdto.setFinancialYearList(fiscalYear);
				forwardPage = "usertypeasearchinput";
			}
			// view for search a/b on dashboard
			if (request.getParameter("pageName") != null && ((request.getParameter("pageName").equalsIgnoreCase("userSrchAView")) || (request.getParameter("pageName").equalsIgnoreCase("userSrchBView")))) {
				// for checking the state at which point the transaction will be
				// resumed
				String hdnRefId = (String) request.getParameter("hdnDocId");
				String[] txnIds = hdnRefId.split("~");
				String regNo = txnIds[1].toString();
				String flag = txnIds[2].toString();
				String docNo = txnIds[0].toString();
				String currentStatus = bd.checkResumeState(docNo);
				dsdto.setParentReferenceId(docNo);
				dsdto.setRefId(docNo);
				dsdto.setRegistNumber(regNo);
				String docId = "";
				boolean chkCountDownloaded = bd.chkPreviousDownloadedExist(dsdto, userId, docNo);
				if (chkCountDownloaded)
					if (regNo != null && !regNo.equals("")) {
						String functionId = dsdto.getServiceType();
						docId = bd.storeSearchATxnDetails(dsdto, userId, functionId);
						dsdto.setRefId(docId);
					}
				// resuming after search point 1
				if (currentStatus.equalsIgnoreCase("FUN_062")) {
					ArrayList payeeDetl = bd.getPaymentList(docNo, "FUN_062");
					if (payeeDetl != null && payeeDetl.size() > 0) {
						float paidAmount = 0;
						float totalAmount = 0;
						float amtToBePaid = 0;
						for (int i = 0; i < payeeDetl.size(); i++) {
							DocumentSearchDTO ldto = (DocumentSearchDTO) payeeDetl.get(i);
							float paidAmt = ldto.getPaidAmount();
							paidAmount = paidAmount + paidAmt;
							totalAmount = Float.parseFloat(ldto.getTotalFee());
							
						}
						amtToBePaid = totalAmount - paidAmount;
						if (amtToBePaid < 0)
							amtToBePaid = 0;
						dform.getDsearchdto().setPaidAmount(paidAmount);
						dform.getDsearchdto().setAmounttobePaid(amtToBePaid);
						if (paidAmount < totalAmount) {
							dform.getDsearchdto().setIsCompletePay(1);
							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Kindly pay the remaining amount INR: " + amtToBePaid);
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "कृपया शेष राशि का भुगतान  करें (रुपए) :" + amtToBePaid);
							
							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.SUCCESS_MSG, "Your Certified Copy Reference ID is: " + docNo);
							else
								request.setAttribute(DeedDraftConstant.SUCCESS_MSG, "आपका प्रमाणित प्रतिलिपि संदर्भ आईडी है :" + docNo);
							
							forwardPage = "usertypeasearchinput";
						} else {
							
							dform.getDsearchdto().setIsCompletePay(0);
							if (regNo != null && !regNo.equals("")) {
								ArrayList payeeDownloadDetl = bd.getPaymentList(docNo, "FUN_230");
								if (payeeDownloadDetl != null && payeeDownloadDetl.size() > 0) {
									
									for (int i = 0; i < payeeDownloadDetl.size(); i++) {
										DocumentSearchDTO ldto = (DocumentSearchDTO) payeeDownloadDetl.get(i);
										float paidAmt = ldto.getPaidAmount();
										paidAmount = paidAmount + paidAmt;
										totalAmount = Float.parseFloat(ldto.getTotalFee());
										
									}
									amtToBePaid = totalAmount - paidAmount;
									dform.getDsearchdto().setPaidAmount(paidAmount);
									dform.getDsearchdto().setAmounttobePaid(amtToBePaid);
									if (paidAmount < totalAmount) {
										dform.getDsearchdto().setIsCompletePay(1);
										if (language.equalsIgnoreCase("en"))
											request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Kindly pay the remaining amount INR: " + amtToBePaid);
										else
											request.setAttribute(DeedDraftConstant.FAILURE_MSG, "कृपया शेष राशि का भुगतान  करें (रुपए) :" + amtToBePaid);
									} else {
										dform.getDsearchdto().setIsCompletePay(0);
										
									}
								} else {
									dform.getDsearchdto().setIsCompletePay(1);
									dform.getDsearchdto().setPaidAmount(0);
									dform.getDsearchdto().setAmounttobePaid(0);
								}
								DocumentSearchDTO resultdto = bd.checkRegistrationIdNew(regNo, flag, language);
								// dsdto.setRefId(txnIds[0].toString());
								logger.debug(" in action --<><>" + resultdto);
								session.setAttribute("resultdto", resultdto);
								if (resultdto == null) {
									forwardPage = "usertypeadisp";
								} else if (resultdto != null) {
									String checkProtestDetls = bd.getProtestFlag(dsdto.getRegistNumber());
									logger.debug("--in action result --<>" + resultdto.getTypeBresult());
									ArrayList refprtList = (ArrayList) resultdto.getPartyDTO().getPartyList();
									logger.debug("party list value size is " + refprtList.size());
									partydto.setPartyList(refprtList);
									dform.setPartyDto(partydto);
									dsdto.setTypeBresult(resultdto.getTypeBresult());
									dsdto.setPropertyList(resultdto.getPropertyList());
									dsdto.setCaveatslist(resultdto.getCaveatslist());
									if (checkProtestDetls != null && !checkProtestDetls.equals("")) {
										dsdto.setProtestList(resultdto.getProtestList());
									}
									dsdto.setComplianceList(resultdto.getComplianceList());
									dsdto.setApplicationId(resultdto.getApplicationId());
									logger.debug("Party list size in form " + dform.getPartyDto().getPartyList().size());
									try {
										/*
										 * feeDsdto=bd.getOthersFeeDuty("FUN_230"
										 * , "19", "SRO");
										 */
										feeDsdto = bd.getOthersFeeDuty("FUN_230", "19", dsdto.getUserTypeId());
										session.setAttribute("Fee", feeDsdto.getServiceFee());
										session.setAttribute("Other_Fee", feeDsdto.getOtherFee());
										session.setAttribute("Total_Fee", feeDsdto.getTotalFee());
										dform.getDsearchdto().setTotalFee(feeDsdto.getTotalFee());
									} catch (Exception e) {
										logger.info(e);
									}
									forwardPage = "usertypeadisp";
								} else {
									forwardPage = "usertypeadisp";
								}
							} else {
								forwardPage = "usertypeadisp";
							}
						}
					}
				} else if (currentStatus.equalsIgnoreCase("FUN_230")) {
					ArrayList payeeDownloadDetl = bd.getPaymentList(dsdto.getRefId(), "FUN_230");
					// String
					// downloadStatus=bd.getDownloadStatus(dsdto.getParentReferenceId());
					
					/*
					 * if("Y".equalsIgnoreCase(downloadStatus)){
					 * 
					 * dform.getDsearchdto().setIsCompletePay(1);
					 * dform.getDsearchdto().setPaidAmount(0);
					 * dform.getDsearchdto().setAmounttobePaid(0); }else
					 */
					if (payeeDownloadDetl != null && payeeDownloadDetl.size() > 0) {
						float paidAmount = 0;
						float totalAmount = 0;
						float amtToBePaid = 0;
						for (int i = 0; i < payeeDownloadDetl.size(); i++) {
							DocumentSearchDTO ldto = (DocumentSearchDTO) payeeDownloadDetl.get(i);
							float paidAmt = ldto.getPaidAmount();
							paidAmount = paidAmount + paidAmt;
							totalAmount = Float.parseFloat(ldto.getTotalFee());
							
						}
						amtToBePaid = totalAmount - paidAmount;
						dform.getDsearchdto().setPaidAmount(paidAmount);
						dform.getDsearchdto().setAmounttobePaid(amtToBePaid);
						
						if (amtToBePaid < 0)
							dform.getDsearchdto().setAmounttobePaid(0);
						
						if (paidAmount > totalAmount)
							dform.getDsearchdto().setPaidAmount(totalAmount);
						
						if (paidAmount < totalAmount) {
							dform.getDsearchdto().setIsCompletePay(1);
							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Kindly pay the remaining amount INR: " + amtToBePaid);
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "कृपया शेष राशि का भुगतान  करें (रुपए) :" + amtToBePaid);
							forwardPage = "usertypeadisp";
						} else {
							dform.getDsearchdto().setIsCompletePay(0);
						}
						
					} else {
						dform.getDsearchdto().setIsCompletePay(1);
						dform.getDsearchdto().setPaidAmount(0);
						dform.getDsearchdto().setAmounttobePaid(0);
					}
					
					String checkProtestDetls = bd.getProtestFlag(dsdto.getRegistNumber());
					DocumentSearchDTO resultdto = null;
					if (checkProtestDetls != null && !checkProtestDetls.equals("")) {
						
						resultdto = bd.checkRegistrationIdNewWithProtest(regNo, flag, language);
					} else {
						resultdto = bd.checkRegistrationIdNew(regNo, flag, language);
					}
					// dsdto.setRefId(txnIds[0].toString());
					logger.debug(" in action --<><>" + resultdto);
					session.setAttribute("resultdto", resultdto);
					if (resultdto == null) {
						forwardPage = "usertypeadisp";
					} else if (resultdto != null) {
						ArrayList nullVoidDetls = new ArrayList();
						String checkNullVoid = bd.getNullvoidFlag(dsdto.getRegistNumber());
						if (checkNullVoid.equalsIgnoreCase("T")) {
							nullVoidDetls = bd.getNullVoidDetls(dsdto.getRegistNumber());
							dsdto.setNullVoidFlag("T");
						} else if (checkNullVoid.equalsIgnoreCase("F")) {
							nullVoidDetls = bd.getNullVoidDetls(dsdto.getRegistNumber());
							dsdto.setNullVoidFlag("F");
						}
						if (nullVoidDetls != null && nullVoidDetls.size() > 0) {
							for (int i = 0; i < nullVoidDetls.size(); i++) {
								DocumentSearchDTO dto = (DocumentSearchDTO) nullVoidDetls.get(i);
								String courtName = dto.getCourtName();
								String courtOrderNo = dto.getCourtOrderNo();
								String courtOrderDate = dto.getCourtOrderDate();
								dsdto.setCourtName(courtName);
								dsdto.setCourtOrderNo(courtOrderNo);
								dsdto.setCourtOrderDate(courtOrderDate);
								
							}
						}
						logger.debug("--in action result --<>" + resultdto.getTypeBresult());
						ArrayList refprtList = (ArrayList) resultdto.getPartyDTO().getPartyList();
						logger.debug("party list value size is " + refprtList.size());
						partydto.setPartyList(refprtList);
						dform.setPartyDto(partydto);
						dsdto.setTypeBresult(resultdto.getTypeBresult());
						dsdto.setPropertyList(resultdto.getPropertyList());
						dsdto.setCaveatslist(resultdto.getCaveatslist());
						dsdto.setKhasraList(resultdto.getKhasraList());
						if (checkProtestDetls != null && !checkProtestDetls.equals("")) {
							dsdto.setProtestList(resultdto.getProtestList());
						}
						dsdto.setComplianceList(resultdto.getComplianceList());
						dsdto.setApplicationId(resultdto.getApplicationId());
						logger.debug("Party list size in form " + dform.getPartyDto().getPartyList().size());
						try {
							/*
							 * feeDsdto=bd.getOthersFeeDuty("FUN_230", "19",
							 * "SRO");
							 */
							feeDsdto = bd.getOthersFeeDuty("FUN_230", "19", dsdto.getUserTypeId());
							session.setAttribute("Fee", feeDsdto.getServiceFee());
							session.setAttribute("Other_Fee", feeDsdto.getOtherFee());
							session.setAttribute("Total_Fee", feeDsdto.getTotalFee());
							dform.getDsearchdto().setTotalFee(feeDsdto.getTotalFee());
						} catch (Exception e) {
							logger.info(e);
						}
						forwardPage = "usertypeadisp";
					}
					
				} else if (currentStatus.equalsIgnoreCase("FUN_063")) {
					DocumentSearchDTO sessionDTOObj = new DocumentSearchDTO();
					logger.debug("in if usertypeadisp---->" + request.getParameter("pageName"));
					ArrayList dtls = new ArrayList();
					dtls = (ArrayList) bd.getTypeBSearchRecordDetails(docNo);
					for (int i = 0; i < dtls.size(); i++) {
						sessionDTOObj = (DocumentSearchDTO) dtls.get(i);
					}
					// dsdto.setRefId(sessionDTOObj.getRefId());
					logger.debug("Date To  is" + sessionDTOObj.getToDate());
					logger.debug("Date from  is" + sessionDTOObj.getFromDate());
					logger.debug("in if usertypeadisp----> (DocumentSearchDTO)sessionFormObj.getDsearchdto()" + sessionDTOObj.getDistId());
					ArrayList typeBSearchResult = bd.getTypeBSearchDetails(sessionDTOObj);
					session.setAttribute("typeBSearchResult", typeBSearchResult);
					logger.debug("=in action typeBSearchResult==" + session.getAttribute("typeBSearchResult"));
					dsdto.setTypeBresultList(typeBSearchResult);
					logger.debug(" jsp total fee-->" + sessionDTOObj.getTotalFee());
					/* feeDsdto=bd.getOthersFeeDuty("FUN_231", "19", "SRO"); */
					feeDsdto = bd.getOthersFeeDuty("FUN_231", "19", dsdto.getUserTypeId());
					dform.getDsearchdto().setServiceFee(feeDsdto.getServiceFee());
					dform.getDsearchdto().setOtherFee(feeDsdto.getOtherFee());
					dform.getDsearchdto().setTotalFee(feeDsdto.getTotalFee());
					session.setAttribute("Fee", feeDsdto.getServiceFee());
					session.setAttribute("Other_Fee", feeDsdto.getOtherFee());
					session.setAttribute("Total_Fee", feeDsdto.getTotalFee());
					dsdto.setTotalFee(sessionDTOObj.getTotalFee());
					forwardPage = "usertypebdisp";
				} else if (currentStatus.equalsIgnoreCase("FUN_231")) {
					if (regNo != null && !regNo.equals("")) {
						DocumentSearchDTO resultdto = bd.checkRegistrationIdNew(regNo, flag, language);
						// dsdto.setRefId(txnIds[0].toString());
						logger.debug(" in action --<><>" + resultdto);
						session.setAttribute("resultdto", resultdto);
						if (resultdto == null) {
							forwardPage = "usertypeadisp";
						}
						if (resultdto != null) {
							logger.debug("--in action result --<>" + resultdto.getTypeBresult());
							ArrayList refprtList = (ArrayList) resultdto.getPartyDTO().getPartyList();
							logger.debug("party list value size is " + refprtList.size());
							partydto.setPartyList(refprtList);
							dform.setPartyDto(partydto);
							dsdto.setTypeBresult(resultdto.getTypeBresult());
							dsdto.setPropertyList(resultdto.getPropertyList());
							dsdto.setCaveatslist(resultdto.getCaveatslist());
							dsdto.setComplianceList(resultdto.getComplianceList());
							dsdto.setApplicationId(resultdto.getApplicationId());
							logger.debug("Party list size in form " + dform.getPartyDto().getPartyList().size());
							logger.info("fees is....." + feeDsdto.getTotalFee());
							
							try {
								/*
								 * feeDsdto=bd.getOthersFeeDuty("FUN_232", "19",
								 * "SRO");
								 */
								feeDsdto = bd.getOthersFeeDuty("FUN_232", "19", dsdto.getUserTypeId());
								logger.debug("feeDsdto.getOtherFee() " + feeDsdto.getOtherFee());
								logger.debug("feeDsdto.getServiceFee() " + feeDsdto.getServiceFee());
								logger.info("fees is....." + feeDsdto.getTotalFee());
								session.setAttribute("Fee", feeDsdto.getServiceFee());
								session.setAttribute("Other_Fee", feeDsdto.getOtherFee());
								session.setAttribute("Total_Fee", feeDsdto.getTotalFee());
								dform.getDsearchdto().setTotalFee(feeDsdto.getTotalFee());
							} catch (Exception e) {
								logger.info(e);
							}
							forwardPage = "usertypeadisp";
						} else {
							forwardPage = "usertypeadisp";
						}
					}
				} else if (currentStatus.equalsIgnoreCase("FUN_232")) {
					
					forwardPage = "success";
				}
			}
			
			// on click of menu item search of type a
			if ((pageName != null && pageName.equals("usertypea")) || (pageName != null && pageName.equals("SearchB"))) {
				logger.info("In Action before setting the fee in to session .......");
				/* feeDsdto=bd.getOthersFeeDuty("FUN_062", "19", "SRO"); */
				feeDsdto = bd.getOthersFeeDuty("FUN_062", "19", dsdto.getUserTypeId());
				dsdto.setSearchType("FUN_062");
				logger.info("fees is....." + feeDsdto.getTotalFee());
				session.setAttribute("Fee", feeDsdto.getServiceFee());
				session.setAttribute("Other_Fee", feeDsdto.getOtherFee());
				session.setAttribute("Total_Fee", feeDsdto.getTotalFee());
				dsdto.setTotalFee(feeDsdto.getTotalFee());
				if (pageName.equalsIgnoreCase("usertypea")) {
					forwardPage = "usertypea";
				} else {
					forwardPage = "usertypeb";
				}
			}
			// new changes
			
			if (pageName != null && pageName.equals("usertypeb")) {
				if (request.getParameter("getAction") != null) {
					String getAction = request.getParameter("getAction").toString();
					if (getAction.equalsIgnoreCase("tehsil")) {
						ArrayList regTehsilList = bd.getRegistrationTehsil(dform.getDsearchdto().getHdnRegDistId().split("~")[0], language);
						if (regTehsilList != null) {
							dsdto.setRegTehsilList(regTehsilList);
						} else {
							dsdto.setRegTehsilList(new ArrayList());
						}
						dsdto.setTehisilId("");
						dsdto.setAreaTypeId("");
						dsdto.setSubAreaTypeId("");
						dsdto.setHdnSubAreaWardMappingId("");
						dsdto.setMohallaId("");
						dsdto.setAreaList(bd.getAreaType(language));
						dsdto.setSubAreaTypeList(new ArrayList());
						dsdto.setWardList(new ArrayList());
						dsdto.setMohallaList(new ArrayList());
					}
					if (getAction.equalsIgnoreCase("regTehsil")) {
						ArrayList regTehsilList = bd.getRegistrationTehsil(dform.getDsearchdto().getHdnRegDistId().split("~")[0], language);
						if (regTehsilList != null) {
							dsdto.setRegTehsilList(regTehsilList);
						} else {
							dsdto.setRegTehsilList(new ArrayList());
						}
						dsdto.setTehisilId("");
						dsdto.setAreaTypeId("");
						dsdto.setSubAreaTypeId("");
						dsdto.setHdnSubAreaWardMappingId("");
						dsdto.setMohallaId("");
						dsdto.setAreaList(bd.getAreaType(language));
						dsdto.setSubAreaTypeList(new ArrayList());
						dsdto.setWardList(new ArrayList());
						dsdto.setMohallaList(new ArrayList());
					}
					if (getAction.equalsIgnoreCase("tehsilChange")) {
						dsdto.setSubAreaTypeId("");
						dsdto.setHdnSubAreaWardMappingId("");
						dsdto.setMohallaId("");
						String urbanFlag = "N";
						if ("2".equalsIgnoreCase(dform.getDsearchdto().getAreaTypeId())) {
							urbanFlag = "Y";
						}
						if (dform.getDsearchdto().getTehisilId() != null && !dform.getDsearchdto().getTehisilId().equalsIgnoreCase("")) {
							ArrayList subAreaList = newPropBO.getSubArea(language, dform.getDsearchdto().getAreaTypeId(), dform.getDsearchdto().getTehisilId(), urbanFlag);
							if (subAreaList != null) {
								dsdto.setSubAreaTypeList(subAreaList);
							} else {
								dsdto.setSubAreaTypeList(new ArrayList());
							}
						} else {
							dsdto.setAreaTypeId("");
							dsdto.setSubAreaTypeList(new ArrayList());
						}
						dsdto.setWardList(new ArrayList());
						dsdto.setMohallaList(new ArrayList());
					}
					if (getAction.equalsIgnoreCase("regTehsilChange")) {
						dsdto.setSubAreaTypeId("");
						dsdto.setHdnSubAreaWardMappingId("");
						dsdto.setMohallaId("");
						String urbanFlag = "N";
						if ("2".equalsIgnoreCase(dform.getDsearchdto().getAreaTypeId())) {
							urbanFlag = "Y";
						}
						if (dform.getDsearchdto().getTehisilId() != null && !dform.getDsearchdto().getTehisilId().equalsIgnoreCase("")) {
							ArrayList subAreaList = newPropBO.getSubArea(language, dform.getDsearchdto().getAreaTypeId(), dform.getDsearchdto().getTehisilId(), urbanFlag);
							if (subAreaList != null) {
								dsdto.setSubAreaTypeList(subAreaList);
							} else {
								dsdto.setSubAreaTypeList(new ArrayList());
							}
						} else {
							dsdto.setAreaTypeId("");
							dsdto.setSubAreaTypeList(new ArrayList());
						}
						dsdto.setWardList(new ArrayList());
						dsdto.setMohallaList(new ArrayList());
					}
					if (getAction.equalsIgnoreCase("subArea")) {
						String urbanFlag = "N";
						if ("2".equalsIgnoreCase(dform.getDsearchdto().getAreaTypeId())) {
							urbanFlag = "Y";
						}
						ArrayList subAreaList = newPropBO.getSubArea(language, dform.getDsearchdto().getAreaTypeId(), dform.getDsearchdto().getTehisilId(), urbanFlag);
						if (subAreaList != null) {
							dsdto.setSubAreaTypeList(subAreaList);
						} else {
							dsdto.setSubAreaTypeList(new ArrayList());
						}
						dsdto.setSubAreaTypeId("");
						dsdto.setHdnSubAreaWardMappingId("");
						dsdto.setMohallaId("");
						dsdto.setWardList(new ArrayList());
						dsdto.setMohallaList(new ArrayList());
					}
					if (getAction.equalsIgnoreCase("wardPatwari")) {
						dsdto.setHdnSubAreaWardMappingId("");
						dsdto.setMohallaId("");
						ArrayList wardlist = newPropBO.getWardPatwari(language, dform.getDsearchdto().getSubAreaTypeId(), dform.getDsearchdto().getTehisilId());
						if (wardlist != null) {
							dsdto.setWardList(wardlist);
						} else {
							dsdto.setWardList(new ArrayList());
						}
						dsdto.setMohallaList(new ArrayList());
					}
					if (getAction.equalsIgnoreCase("colony")) {
						dsdto.setMohallaId("");
						ArrayList mohallaList = newPropBO.getColonyName(language, dform.getDsearchdto().getHdnSubAreaWardMappingId());
						if (mohallaList != null) {
							dsdto.setMohallaList(mohallaList);
						} else {
							dsdto.setMohallaList(new ArrayList());
						}
					}
				} else {
					ArrayList dist = bd.getDistrict(language);
					dsdto.setDistList(dist);
				}
				dsdto.setSearchType("FUN_063");
				String flag = dform.getDsearchdto().getRegFlag();
				logger.info("Value of flag is:::::: " + flag);
				dsdto.setFlag(flag);
				ArrayList tehsilList = null;
				dsdto.setMohallaName(dform.getDsearchdto().getMohName());// c
				dsdto.setRegFlag(flag);
				logger.info("districtID" + dform.getDsearchdto().getDistId());
				logger.info("newdidid" + dsdto.getDistId1());
				ArrayList patwariList = null;// c
				ArrayList mohallaList = null;// c
				ArrayList villageList = null;
				if (dform.getDsearchdto().getDistId() != null) {
					logger.info("The value of flag is::::::::" + flag);
					if (flag.equals("new")) {
						dsdto.setRadiobutton("e");
						logger.info(dsdto.getRadiobutton());
						request.setAttribute("radio", dsdto.getRadiobutton());
						tehsilList = bd.getTehisil(dform.getDsearchdto().getDistId(), language);
						ArrayList wardlist = new ArrayList();// c
						ArrayList mohallalist = new ArrayList();// c
						logger.debug("--dform.getDsearchdto().getAreaType()--<><>" + dform.getDsearchdto().getAreaTypeId());
						logger.debug("--dform.getDsearchdto().getWardpatwarId()--<><>" + dform.getDsearchdto().getWardpatwarId());
						
						if (dform.getDsearchdto().getSubAreaTypeId() != "") {
							wardlist = newPropBO.getWardPatwari(language, dform.getDsearchdto().getSubAreaTypeId(), dform.getDsearchdto().getTehisilId());
						}
						if (dform.getDsearchdto().getHdnSubAreaWardMappingId() != "") {
							mohallaList = newPropBO.getColonyName(language, dform.getDsearchdto().getHdnSubAreaWardMappingId());
						}
						if (tehsilList.size() > 0) {
							dform.getDsearchdto().setDistId(dform.getDsearchdto().getDistId());
						} else {
							dform.getDsearchdto().setTehisilId("");
							dform.getDsearchdto().setTehisilName("");
						}
						if (wardlist.size() > 0) {
							dsdto.setWardList(wardlist);
						} else {
							dform.getDsearchdto().setWardNo("");
							dform.getDsearchdto().setWardORpathivasHalka("");
						}
						
						logger.info("tehsilname " + dform.getDsearchdto().getTehsilName() + "wardname" + dform.getDsearchdto().getWarddName());
						dsdto.setDistId(dform.getDsearchdto().getDistId());
						dsdto.setTehisilId(dform.getDsearchdto().getTehisilId());
						dsdto.setAreaTypeId(dform.getDsearchdto().getAreaTypeId());
						dsdto.setWardId(dform.getDsearchdto().getWardId());
						dsdto.setMohallaId(dform.getDsearchdto().getMohallaId());
						dsdto.setPatwariId(dform.getDsearchdto().getPatwariId());
						dsdto.setVillageId(dform.getDsearchdto().getVillageId());
					}

					else {
						tehsilList = bd.getTehisil(dform.getDsearchdto().getDistId1(), language);
						if (tehsilList.size() > 0) {
							logger.info("old ID" + dform.getDsearchdto().getDistId1());
							dform.getDsearchdto().setDistId1(dform.getDsearchdto().getDistId1());
						} else {
							dform.getDsearchdto().setTehisilId("");
							dform.getDsearchdto().setTehisilName("");
							tehsilList.add(dform.getDsearchdto());
						}
					}
					logger.info("tehsilname " + dform.getDsearchdto().getTehsilName() + "wardname" + dform.getDsearchdto().getWarddName());
					dsdto.setDistId1(dform.getDsearchdto().getDistId1());
					dsdto.setTehisilId(dform.getDsearchdto().getTehisilId());
				}
				// dsdto.setDistList(dist);
				logger.info(dsdto.getDistId());
				logger.info(dsdto.getDistId1());
				logger.debug("--in action dist list type b--<>" + dsdto.getDistList().size());
				logger.debug("in if usertypeadisp---->" + request.getParameter("pageName"));
				feeDsdto = bd.getOthersFeeDuty("FUN_063", "18", dsdto.getUserTypeId());
				// modified by shruti---8 july 2014
				if (feeDsdto.getTotalFee() != null) {
					if (dform.getFromDate() != null && dform.getToDate() != null) {
						logger.debug("Date value is " + dform.getFromDate());
						logger.debug("Date value is " + dform.getToDate());
						noofyears = bd.getNoOfDocYears(dform.getFromDate(), dform.getToDate());
						logger.info("NO of doc years>>>>>>>>>>>>>>>>>>>" + noofyears);
						int totalamt = Integer.parseInt(noofyears) * Integer.parseInt(feeDsdto.getTotalFee());
						String totalfee = String.valueOf(totalamt);
						feeDsdto.setTotalFee(totalfee);
					}
				}
				// end
				dform.getDsearchdto().setTotalFee(feeDsdto.getTotalFee());
				session.setAttribute("Fee", feeDsdto.getServiceFee());
				session.setAttribute("Other_Fee", feeDsdto.getOtherFee());
				session.setAttribute("Total_Fee", feeDsdto.getTotalFee());
				forwardPage = "usertypeb";
			}
			// on click of payment process for the first time
			if (dform.getDsearchdto().getActionName() != null && dform.getDsearchdto().getActionName().equals("paymentModule")) {
				logger.debug("in action payment call--- dform.getDsearchdto().getFormName()<>" + dform.getDsearchdto().getFormName());
				dform.getDsearchdto().setActionName(null);
				if (dform.getDsearchdto().getFormName() != null && dform.getDsearchdto().getFormName().equals("docSearchTypeA")) {
					logger.debug("in action payment usertypeadisp call---<>");
					dsdto.setDateOfReg(dform.getDateOfReg());
					logger.info("DISTRICT>>>>>>>>>>>>>>>>>>" + dsdto.getDistId());
					logger.info("SRO NAME>>>>>>>>>>>>>>>>>>" + dsdto.getSroName());
					logger.info("SR NAME>>>>>>>>>>>>>>>>>>" + dsdto.getSrName());
					logger.info("BOOK NO >>>>>>>>>>>>>>>>>>" + dsdto.getBookNo());
					logger.info("VOLUME NO>>>>>>>>>>>>>>>>>>" + dsdto.getVolNo());
					logger.info("SERIAL NUMBER>>>>>>>>>>>>>>>>>>" + dsdto.getSerialNo());
					logger.info("REGISTRATION DATE>>>>>>>>>>>>>>>>>>" + dsdto.getDateOfReg());
					logger.info("AREA TYPE >>>>>>>>>>>>>>>>>>" + dsdto.getAreaTypeId());
					String userId = (String) session.getAttribute("UserId");
					String functionId = dsdto.getServiceType();
					logger.info("UserId is ===============" + userId);
					logger.info("functionId is ===========" + functionId);
					String refID = dform.getDsearchdto().getParentReferenceId();
					String txnid = bd.storePaymetTxnDetails(CommonConstant.PAYMENT_FLAG, dsdto, userId, functionId, refID);
					dform.getDsearchdto().setRefId(dsdto.getParentReferenceId());
					String amountTobePaid = "";
					ArrayList payeeDetl = bd.getPaymentList(dsdto.getParentReferenceId(), functionId);
					if (payeeDetl != null && payeeDetl.size() > 0) {
						float paidAmount = 0;
						float totalAmount = 0;
						float amtToBePaid = 0;
						for (int i = 0; i < payeeDetl.size(); i++) {
							DocumentSearchDTO ldto = (DocumentSearchDTO) payeeDetl.get(i);
							float paidAmt = ldto.getPaidAmount();
							paidAmount = paidAmount + paidAmt;
							totalAmount = Float.parseFloat(ldto.getTotalFee());
							
						}
						amtToBePaid = totalAmount - paidAmount;
						if (amtToBePaid < 0)
							amtToBePaid = 0;
						dform.getDsearchdto().setPaidAmount(paidAmount);
						dform.getDsearchdto().setAmounttobePaid(amtToBePaid);
						amountTobePaid = String.valueOf(amtToBePaid);
					} else
						amountTobePaid = String.valueOf(dform.getDsearchdto().getTotalFee());
					request.setAttribute("forwardPath", "./docsearchtypea.do?pageName=usertypeadisp&TRFS=NGI");
					logger.debug("in IF--- request path<>" + request.getAttribute("forwardPath"));
					request.setAttribute("parentTable", "IGRS_SEARCH_PAYMENT_DETAILS");
					request.setAttribute("parentAmount", amountTobePaid);
					logger.info("PENDING AMOUNT SEND by DOC SEARCh MODULE>>>>>>>>>>>>>" + amountTobePaid);
					
					logger.info("PARENT AMOUNT SEND by DOC SEARCh MODULE>>>>>>>>>>>>>" + dform.getDsearchdto().getTotalFee());
					request.setAttribute("parentFunId", functionId);
					
					request.setAttribute("parentKey", txnid);
					request.setAttribute("parentModName", session.getAttribute("modName"));
					request.setAttribute("parentFunName", dsdto.getServiceType());
					
					request.setAttribute("parentDistrictId", dsdto.getParentDistrictId());
					request.setAttribute("parentDistrictName", dsdto.getParentDistrictName());
					request.setAttribute("parentOfficeId", dsdto.getParentOfficeId());
					request.setAttribute("parentOfficeName", dsdto.getParentOfficeName());
					request.setAttribute("parentReferenceId", dsdto.getParentReferenceId());
					
					// modified by shruti---19 nov 2014-payment changes
					request.setAttribute("formName", "documentsearchtypeAone");
					// end
					
					// added by shruti---15 sep 2014
					if ("en".equalsIgnoreCase(language)) {
						session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
					} else {
						session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
					}
					if ("en".equalsIgnoreCase(language)) {
						session.setAttribute("fnName", CommonConstant.DOC_SRCH_A_FUNCTION_PAY_ENGLISH);
					} else {
						session.setAttribute("fnName", CommonConstant.DOC_SRCH_A_FUNCTION_PAY_HINDI);
					}
					// end
					
					request.setAttribute("parentColumnName", "TRANSACTION_ID");
					logger.info("getting parentAmount is ===============" + request.getAttribute("parentAmount"));
					logger.info("getting functionId is ===========" + request.getAttribute("parentFunId"));
					logger.info("parentKey is ===============" + request.getAttribute("parentKey"));
					
					feeDsdto = bd.getOthersFeeDuty("FUN_231", "19", dsdto.getUserTypeId());
					dform.getDsearchdto().setTotalFee(feeDsdto.getTotalFee());
					session.setAttribute("Fee", feeDsdto.getServiceFee());
					session.setAttribute("Other_Fee", feeDsdto.getOtherFee());
					session.setAttribute("Total_Fee", feeDsdto.getTotalFee());
					dform.getDsearchdto().setTotalFee(feeDsdto.getTotalFee());
					dform.getDsearchdto().setOtherFee(feeDsdto.getOtherFee());
					dform.getDsearchdto().setServiceFee(feeDsdto.getServiceFee());
					forwardPage = "forwardPath";
				}
				// payment for doc search type b
				if (dform.getDsearchdto().getFormName() != null && dform.getDsearchdto().getFormName().equals("docSearchTypeB")) {
					logger.debug("in action payment docSearchTypeB call---<>");
					dform.getDsearchdto().setToDate(dform.getToDate());
					dform.getDsearchdto().setFromDate(dform.getFromDate());
					dform.getDsearchdto().setToDate1(dform.getToDate1());
					dform.getDsearchdto().setFromDate1(dform.getFromDate1());
					dform.getDsearchdto().setRegDate(dform.getDateOfReg());
					dform.getDsearchdto().setRegFlag(dform.getDsearchdto().getRegFlag());
					session.setAttribute("sessionTypeBDTOObj", dform.getDsearchdto());
					logger.debug("sessionTypeBDTOObj" + ((DocumentSearchDTO) session.getAttribute("sessionTypeBDTOObj")).getDistId());
					session.setAttribute("user", "user");
					session.setAttribute("forwardPath", "./docsearchtypea.do?pageName=usertypebresult");
					String userId = (String) session.getAttribute("UserId");
					
					String functionId = dsdto.getServiceType();
					logger.info("UserId is ===============" + userId);
					logger.info("functionId is ===========" + functionId);
					String txnid = bd.storeBTypeSearch(CommonConstant.PAYMENT_FLAG, dform.getDsearchdto(), dsdto, userId, functionId);
					String[] temp = new String[2];
					temp = txnid.split("~");
					String pkey = temp[0];
					String refid = temp[1];
					dsdto.setParentReferenceId(refid);
					dform.getDsearchdto().setRefId(refid);
					String amountTobePaid = "";
					ArrayList payeeDetl = bd.getPaymentList(dsdto.getParentReferenceId(), functionId);
					if (payeeDetl != null && payeeDetl.size() > 0) {
						float paidAmount = 0;
						float totalAmount = 0;
						float amtToBePaid = 0;
						for (int i = 0; i < payeeDetl.size(); i++) {
							DocumentSearchDTO ldto = (DocumentSearchDTO) payeeDetl.get(i);
							float paidAmt = ldto.getPaidAmount();
							paidAmount = paidAmount + paidAmt;
							totalAmount = Float.parseFloat(ldto.getTotalFee());
							
						}
						amtToBePaid = totalAmount - paidAmount;
						if (amtToBePaid < 0)
							amtToBePaid = 0;
						dform.getDsearchdto().setPaidAmount(paidAmount);
						dform.getDsearchdto().setAmounttobePaid(amtToBePaid);
						amountTobePaid = String.valueOf(amtToBePaid);
					} else
						amountTobePaid = String.valueOf(dform.getDsearchdto().getTotalFee());
					request.setAttribute("forwardPath", "./docsearchtypea.do?pageName=usertypebdisp&TRFS=NGI");
					request.setAttribute("parentTable", "IGRS_SEARCH_PAYMENT_DETAILS");
					request.setAttribute("parentAmount", amountTobePaid);
					logger.info("PENDING AMOUNT FOR DOC SRCH B>>>>>" + amountTobePaid);
					
					logger.info("PARENT AMOUNT FOR DOC SRCH B>>>>>" + dform.getDsearchdto().getTotalFee());
					request.setAttribute("parentFunId", functionId);
					request.setAttribute("parentKey", pkey);
					request.setAttribute("parentModName", session.getAttribute("modName"));
					request.setAttribute("parentFunName", session.getAttribute("fnName"));
					request.setAttribute("parentColumnName", "TRANSACTION_ID");
					
					// modified by shruti---19 nov 2014-payment changes
					request.setAttribute("formName", "documentsearchtypeAone");
					// end
					
					// added by shruti---15 sep 2014
					if ("en".equalsIgnoreCase(language)) {
						session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
					} else {
						session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
					}
					if ("en".equalsIgnoreCase(language)) {
						session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_ENGLISH);
					} else {
						session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_HINDI);
					}
					// end
					request.setAttribute("parentDistrictId", dsdto.getParentDistrictId());
					request.setAttribute("parentDistrictName", dsdto.getParentDistrictName());
					request.setAttribute("parentOfficeId", dsdto.getParentOfficeId());
					request.setAttribute("parentOfficeName", dsdto.getParentOfficeName());
					request.setAttribute("parentReferenceId", dsdto.getParentReferenceId());
					
					logger.info("VVVVVVV>>>>>>" + dsdto.getParentDistrictId());
					logger.info("VVVVVVVVVVVVVVVV" + dsdto.getParentDistrictName());
					logger.info("VVVVVVVVVVVVVV" + dsdto.getParentOfficeId());
					logger.info("VVVVVVVV" + dsdto.getParentOfficeName());
					
					logger.info("getting parentAmount is ===============" + request.getAttribute("parentAmount"));
					logger.info("getting functionId is ===========" + request.getAttribute("parentFunId"));
					forwardPage = "forwardPath";
				}
				// payment on download click
				if (dform.getDsearchdto().getFormName() != null && dform.getDsearchdto().getFormName().equals("downloadDocumentFees")) {
					boolean signatureCheck = bd.getSignatureCheck(dform.getDsearchdto().getRegistNumber());
					if (signatureCheck) {
						logger.debug("in action payment download document call---<>");
						String userId = (String) session.getAttribute("UserId");
						
						String functionId = dsdto.getServiceType();
						if (functionId.equalsIgnoreCase("FUN_062")) {
							functionId = "FUN_230";
							feeDsdto = bd.getOthersFeeDuty("FUN_230", "19", dsdto.getUserTypeId());
							session.setAttribute("Fee", feeDsdto.getServiceFee());
							session.setAttribute("Other_Fee", feeDsdto.getOtherFee());
							session.setAttribute("Total_Fee", feeDsdto.getTotalFee());
							if ("en".equalsIgnoreCase(language)) {
								session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
							} else {
								session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
							}
							if ("en".equalsIgnoreCase(language)) {
								session.setAttribute("fnName", CommonConstant.DOC_SRCH_A_FUNCTION_PAY_ENGLISH);
							} else {
								session.setAttribute("fnName", CommonConstant.DOC_SRCH_A_FUNCTION_PAY_HINDI);
							}
						} else if (functionId.equalsIgnoreCase("FUN_063")) {
							functionId = "FUN_232";
							feeDsdto = bd.getOthersFeeDuty("FUN_232", "19", dsdto.getUserTypeId());
							session.setAttribute("Fee", feeDsdto.getServiceFee());
							session.setAttribute("Other_Fee", feeDsdto.getOtherFee());
							session.setAttribute("Total_Fee", feeDsdto.getTotalFee());
							if ("en".equalsIgnoreCase(language)) {
								session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
							} else {
								session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
							}
							if ("en".equalsIgnoreCase(language)) {
								session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_ENGLISH);
							} else {
								session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_HINDI);
							}
							// END
						}
						// for download of search a condition applied is if func
						// id FUN_062 change it to fun_230
						logger.info("UserId is ===============" + userId);
						logger.info("functionId is ===========" + functionId);
						String doctxnid = dform.getDsearchdto().getRefId();
						String txnid = bd.downloadPaymetTxnDetails(CommonConstant.PAYMENT_FLAG, doctxnid, userId, functionId, feeDsdto.getTotalFee());
						// ADDED BY SHRUTI--11 OCT 2013
						dsdto.setParentReferenceId(txnid);
						// END
						// ADDED BY SHREERAJ--28 MAY 2015
						String amountTobePaid = "";
						// String downloadStatus=bd.getDownloadStatus(doctxnid);
						ArrayList payeeDetl = bd.getPaymentList(doctxnid, functionId);
						/*
						 * if("Y".equalsIgnoreCase(downloadStatus)){
						 * amountTobePaid
						 * =(String)session.getAttribute("Total_Fee"); }else
						 */if (payeeDetl != null && payeeDetl.size() > 0) {
							float paidAmount = 0;
							float totalAmount = 0;
							float amtToBePaid = 0;
							for (int i = 0; i < payeeDetl.size(); i++) {
								DocumentSearchDTO ldto = (DocumentSearchDTO) payeeDetl.get(i);
								float paidAmt = ldto.getPaidAmount();
								paidAmount = paidAmount + paidAmt;
								totalAmount = Float.parseFloat(ldto.getTotalFee());
								
							}
							amtToBePaid = totalAmount - paidAmount;
							if (amtToBePaid < 0)
								amtToBePaid = 0;
							dform.getDsearchdto().setPaidAmount(paidAmount);
							dform.getDsearchdto().setAmounttobePaid(amtToBePaid);
							amountTobePaid = String.valueOf(amtToBePaid);
						} else
							amountTobePaid = String.valueOf((String) session.getAttribute("Total_Fee"));
						request.setAttribute("forwardPath", "./docsearchtypea.do?pageName=downloadDocumentRedirect&TRFS=NGI");
						request.setAttribute("parentTable", "IGRS_SEARCH_PAYMENT_DETAILS");
						// request.setAttribute("parentAmount",dform.getDsearchdto().getTotalFee());
						request.setAttribute("parentAmount", amountTobePaid);
						request.setAttribute("parentFunId", functionId);
						request.setAttribute("parentKey", txnid);
						request.setAttribute("parentModName", session.getAttribute("modName"));
						request.setAttribute("parentFunName", session.getAttribute("fnName"));
						request.setAttribute("parentColumnName", "TRANSACTION_ID");
						request.setAttribute("parentDistrictId", dsdto.getParentDistrictId());
						request.setAttribute("parentDistrictName", dsdto.getParentDistrictName());
						request.setAttribute("parentOfficeId", dsdto.getParentOfficeId());
						request.setAttribute("parentOfficeName", dsdto.getParentOfficeName());
						request.setAttribute("parentReferenceId", dsdto.getParentReferenceId());
						
						// modified by shruti---19 nov 2014-payment changes
						request.setAttribute("formName", "documentsearchtypeAone");
						// end
						
						logger.info("VVVVVVV>>>>>>" + dsdto.getParentDistrictId());
						logger.info("VVVVVVVVVVVVVVVV" + dsdto.getParentDistrictName());
						logger.info("VVVVVVVVVVVVVV" + dsdto.getParentOfficeId());
						logger.info("VVVVVVVV" + dsdto.getParentOfficeName());
						logger.info("getting parentAmount is ===============" + request.getAttribute("parentAmount"));
						logger.info("getting functionId is ===========" + request.getAttribute("parentFunId"));
						forwardPage = "forwardPath";
					} else {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Cannot download the document as it is not signed by the Officer");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "यह दस्तावेज़ अधिकारी द्वारा हस्ताक्षरित नहीं है इसीलिए आप इसे डाउनलोड नहीं कर सक्ते।");
						dsdto.setTypeBresult(null);
						forwardPage = "usertypeadisp";
					}
				}
				// redirect to payment after 2nd time in case of search b
				if (dform.getDsearchdto().getFormName() != null && dform.getDsearchdto().getFormName().equals("docSearchTypeBSearch")) {
					logger.debug("in action payment docSearchTypeBSearch call---<>");
					String userId = (String) session.getAttribute("UserId");
					String functionId = dsdto.getServiceType();
					if (functionId.equalsIgnoreCase("FUN_063")) {
						dform.getDsearchdto().setTotalFee(dform.getDsearchdto().getServiceFee());
						functionId = "FUN_231";
						if ("en".equalsIgnoreCase(language)) {
							session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
						} else {
							session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
						}
						if ("en".equalsIgnoreCase(language)) {
							session.setAttribute("fnName", CommonConstant.DOC_SRCH_A_FUNCTION_PAY_ENGLISH);
						} else {
							session.setAttribute("fnName", CommonConstant.DOC_SRCH_A_FUNCTION_PAY_HINDI);
						}
					}
					String doctxnid = dform.getDsearchdto().getRefId();
					
					dsdto.setParentReferenceId(doctxnid);
					String txnid = bd.updatePaymetTxnDetails(CommonConstant.PAYMENT_FLAG, doctxnid, dform.getDsearchdto().getRegistNumber(), userId, functionId);
					logger.info("functionId is ===========" + functionId);
					String amountTobePaid = "";
					ArrayList payeeDetl = bd.getPaymentList(doctxnid, functionId);
					if (payeeDetl != null && payeeDetl.size() > 0) {
						float paidAmount = 0;
						float totalAmount = 0;
						float amtToBePaid = 0;
						for (int i = 0; i < payeeDetl.size(); i++) {
							DocumentSearchDTO ldto = (DocumentSearchDTO) payeeDetl.get(i);
							float paidAmt = ldto.getPaidAmount();
							paidAmount = paidAmount + paidAmt;
							totalAmount = Float.parseFloat(ldto.getTotalFee());
							
						}
						amtToBePaid = totalAmount - paidAmount;
						if (amtToBePaid < 0)
							amtToBePaid = 0;
						dform.getDsearchdto().setPaidAmount(paidAmount);
						dform.getDsearchdto().setAmounttobePaid(amtToBePaid);
						amountTobePaid = String.valueOf(amtToBePaid);
					} else
						amountTobePaid = String.valueOf(dform.getDsearchdto().getTotalFee());
					request.setAttribute("forwardPath", "./docsearchtypea.do?pageName=usertypeadisp&TRFS=NGI");
					request.setAttribute("parentTable", "IGRS_SEARCH_PAYMENT_DETAILS");
					request.setAttribute("parentAmount", amountTobePaid);
					logger.info("pending AMOUNT in case of search b @@@@@@ >>>>>>>>>>>>>>>" + amountTobePaid);
					
					logger.info("PARENT AMOUNT in case of search b @@@@@@ >>>>>>>>>>>>>>>" + dform.getDsearchdto().getTotalFee());
					request.setAttribute("parentFunId", functionId);
					request.setAttribute("parentKey", txnid);
					request.setAttribute("parentModName", session.getAttribute("modName"));
					request.setAttribute("parentFunName", session.getAttribute("fnName"));
					request.setAttribute("parentColumnName", "TRANSACTION_ID");
					request.setAttribute("parentDistrictId", dsdto.getParentDistrictId());
					request.setAttribute("parentDistrictName", dsdto.getParentDistrictName());
					request.setAttribute("parentOfficeId", dsdto.getParentOfficeId());
					request.setAttribute("parentOfficeName", dsdto.getParentOfficeName());
					request.setAttribute("parentReferenceId", dsdto.getParentReferenceId());
					
					// modified by shruti---19 nov 2014-payment changes
					request.setAttribute("formName", "documentsearchtypeAone");
					// end
					
					logger.info("VVVVVVV>>>>>>" + dsdto.getParentDistrictId());
					logger.info("VVVVVVVVVVVVVVVV" + dsdto.getParentDistrictName());
					logger.info("VVVVVVVVVVVVVV" + dsdto.getParentOfficeId());
					logger.info("VVVVVVVV" + dsdto.getParentOfficeName());
					logger.info("getting parentAmount is ===============" + request.getAttribute("parentAmount"));
					logger.info("getting functionId is ===========" + request.getAttribute("parentFunId"));
					forwardPage = "forwardPath";
				}
			}
			
			if (request.getParameter("pageName") != null && request.getParameter("pageName").equalsIgnoreCase("usertypeaofficedisp")) {
				String registerNum = dform.getDsearchdto().getRegistNumber();
				String userId = (String) session.getAttribute("UserId");
				logger.debug(" userId is --<><>" + userId);
				String functionId = (String) session.getAttribute("functionId");
				logger.debug(" functionId is --<><>" + functionId);
				logger.debug(" Reason is --<><>" + dform.getDsearchdto().getReason());
				
				String txnid = bd.storeOfficeTxnDetails(dform.getDsearchdto().getReason(), dform.getDsearchdto().getRegistNumber(), userId, functionId);
				dform.getDsearchdto().setRefId(txnid);
				
				DocumentSearchDTO sesstionDTO = dform.getDsearchdto();
				String regNo = sesstionDTO.getRegistNumber();
				if (regNo != null && !regNo.equals("")) {
					DocumentSearchDTO resultdto = null;
					String checkProtestDetls = bd.getProtestFlag(dsdto.getRegistNumber());
					if (checkProtestDetls != null && !checkProtestDetls.equals("")) {
						resultdto = bd.checkRegistrationIdWithProtest(regNo, language);
					} else {
						resultdto = bd.checkRegistrationId(regNo, language);
						
					}
					logger.debug(" in action --<><>" + resultdto);
					session.setAttribute("resultdto", resultdto);
					// dsdto.setRefId(docId);
					if (resultdto == null) {
						forwardPage = "usertypeadisp";
					}
					if (resultdto != null) {
						ArrayList nullVoidDetls = new ArrayList();
						String checkNullVoid = bd.getNullvoidFlag(dsdto.getRegistNumber());
						if (checkNullVoid.equalsIgnoreCase("T")) {
							nullVoidDetls = bd.getNullVoidDetls(dsdto.getRegistNumber());
							dsdto.setNullVoidFlag("T");
						} else if (checkNullVoid.equalsIgnoreCase("F")) {
							nullVoidDetls = bd.getNullVoidDetls(dsdto.getRegistNumber());
							dsdto.setNullVoidFlag("F");
						}
						if (nullVoidDetls != null && nullVoidDetls.size() > 0) {
							for (int i = 0; i < nullVoidDetls.size(); i++) {
								DocumentSearchDTO dto = (DocumentSearchDTO) nullVoidDetls.get(i);
								String courtName = dto.getCourtName();
								String courtOrderNo = dto.getCourtOrderNo();
								String courtOrderDate = dto.getCourtOrderDate();
								dsdto.setCourtName(courtName);
								dsdto.setCourtOrderNo(courtOrderNo);
								dsdto.setCourtOrderDate(courtOrderDate);
								
							}
						}
						logger.debug("--in action result --<>" + resultdto.getTypeBresult());
						ArrayList refprtList = (ArrayList) resultdto.getPartyDTO().getPartyList();
						logger.debug("party list value size is " + refprtList.size());
						partydto.setPartyList(refprtList);
						dform.setPartyDto(partydto);
						dsdto.setTypeBresult(resultdto.getTypeBresult());
						dsdto.setPropertyList(resultdto.getPropertyList());
						dsdto.setKhasraList(resultdto.getKhasraList());
						if (checkProtestDetls != null && !checkProtestDetls.equals("")) {
							dsdto.setProtestList(resultdto.getProtestList());
						}
						dsdto.setCaveatslist(resultdto.getCaveatslist());
						dsdto.setComplianceList(resultdto.getComplianceList());
						dsdto.setApplicationId(resultdto.getApplicationId());
						logger.debug("Party list size in form " + dform.getPartyDto().getPartyList().size());
						forwardPage = "usertypeadisp";
					}
				}
				
			}
			// for search type a
			// on return after payment success for search a
			if (request.getParameter("pageName") != null && request.getParameter("pageName").equalsIgnoreCase("usertypeadisp")) {
				DocumentSearchDTO sesstionDTO = dform.getDsearchdto();
				// logger.debug("--sesstionForm--<><>"+sesstionDTO.getRegistNumber());
				String regNo = sesstionDTO.getRegistNumber();
				// PARTIAL PAYMENT IMPLEMENTAION by------->ShreeraJ<-------
				ArrayList payeeDetl = bd.getPaymentList(sesstionDTO.getParentReferenceId(), "FUN_062");
				if (payeeDetl != null && payeeDetl.size() > 0) {
					float paidAmount = 0;
					float totalAmount = 0;
					float amtToBePaid = 0;
					for (int i = 0; i < payeeDetl.size(); i++) {
						DocumentSearchDTO ldto = (DocumentSearchDTO) payeeDetl.get(i);
						float paidAmt = ldto.getPaidAmount();
						paidAmount = paidAmount + paidAmt;
						totalAmount = Float.parseFloat(ldto.getTotalFee());
						
					}
					amtToBePaid = totalAmount - paidAmount;
					if (amtToBePaid < 0)
						amtToBePaid = 0;
					dform.getDsearchdto().setPaidAmount(paidAmount);
					dform.getDsearchdto().setAmounttobePaid(amtToBePaid);
					if (paidAmount < totalAmount) {
						dform.getDsearchdto().setIsCompletePay(1);
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Kindly pay the remaining amount INR: " + amtToBePaid);
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "कृपया शेष राशि का भुगतान  करें (रुपए) :" + amtToBePaid);
						
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.SUCCESS_MSG, "Your Certified Copy Reference ID is: " + sesstionDTO.getParentReferenceId());
						else
							request.setAttribute(DeedDraftConstant.SUCCESS_MSG, "आपका प्रमाणित प्रतिलिपि संदर्भ आईडी है :" + sesstionDTO.getParentReferenceId());
						
						forwardPage = "usertypeasearchinput";
					} else {
						dform.getDsearchdto().setIsCompletePay(1);
						if (regNo != null && !regNo.equals("")) {
							dform.getDsearchdto().setPaidAmount(0);
							dform.getDsearchdto().setAmounttobePaid(0);
							logger.info("SEARCH TYPE>>>>>>>>>>>>>>>>>>>" + sesstionDTO.getSearchType());
							DocumentSearchDTO resultdto = bd.checkRegistrationId(regNo, language);
							String checkProtestDetls = bd.getProtestFlag(dsdto.getRegistNumber());
							if (resultdto == null) {
								dform.getDsearchdto().setErrorName("No such record found");
								forwardPage = "usertypeadisp";
							}
							// end
							else if (resultdto != null) {
								request.setAttribute("abc", resultdto.getKhasraList());
								session.setAttribute("resultdto", resultdto);
								ArrayList refprtList = (ArrayList) resultdto.getPartyDTO().getPartyList();
								logger.debug("party list value size is " + refprtList.size());
								partydto.setPartyList(refprtList);
								dform.setPartyDto(partydto);
								dsdto.setTypeBresult(resultdto.getTypeBresult());
								dsdto.setPropertyList(resultdto.getPropertyList());
								dsdto.setKhasraList(resultdto.getKhasraList());
								if (checkProtestDetls != null && !checkProtestDetls.equals("")) {
									dsdto.setProtestList(resultdto.getProtestList());
								}
								dsdto.setCaveatslist(resultdto.getCaveatslist());
								dsdto.setComplianceList(resultdto.getComplianceList());
								dsdto.setApplicationId(resultdto.getApplicationId());
								logger.debug("Party list size in form " + dform.getPartyDto().getPartyList().size());
								try {
									if (dsdto.getSearchType() != null) {
										if (dsdto.getSearchType().equalsIgnoreCase("FUN_062")) {
											// bd.updateSearchStatus(dsdto.getParentReferenceId(),"FUN_062",CommonConstant.SECOND_PAGE);
											// feeDsdto=bd.getOthersFeeDuty("FUN_230",
											// "19", "SRO");
											feeDsdto = bd.getOthersFeeDuty("FUN_230", "19", dsdto.getUserTypeId());
											if ("en".equalsIgnoreCase(language)) {
												session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
											} else {
												session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
											}
											if ("en".equalsIgnoreCase(language)) {
												session.setAttribute("fnName", CommonConstant.DOC_SRCH_A_FUNCTION_PAY_ENGLISH);
											} else {
												session.setAttribute("fnName", CommonConstant.DOC_SRCH_A_FUNCTION_PAY_HINDI);
											}
										} else if (dsdto.getSearchType().equalsIgnoreCase("FUN_063")) {
											// bd.updateSearchStatus(dsdto.getParentReferenceId(),"FUN_063",CommonConstant.SECOND_PAGE);
											
											/*
											 * feeDsdto=bd.getOthersFeeDuty("FUN_232"
											 * , "19", "SRO");
											 */
											// feeDsdto=bd.getOthersFeeDuty("FUN_232",
											// "19", dsdto.getUserTypeId());
											if ("en".equalsIgnoreCase(language)) {
												session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
											} else {
												session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
											}
											if ("en".equalsIgnoreCase(language)) {
												session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_ENGLISH);
											} else {
												session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_HINDI);
											}
										}
									}
									// end
									logger.debug("feeDsdto.getOtherFee() " + feeDsdto.getOtherFee());
									logger.debug("feeDsdto.getServiceFee() " + feeDsdto.getServiceFee());
									logger.info("fees is....." + feeDsdto.getTotalFee());
									session.setAttribute("Fee", feeDsdto.getServiceFee());
									session.setAttribute("Other_Fee", feeDsdto.getOtherFee());
									session.setAttribute("Total_Fee", feeDsdto.getTotalFee());
									dform.getDsearchdto().setTotalFee(feeDsdto.getTotalFee());
								} catch (Exception e) {
									logger.info(e);
								}
								forwardPage = "usertypeadisp";
							} else {
								forwardPage = "usertypeadisp";
							}
						} else {
							forwardPage = "usertypeadisp";
						}
					}
					
				}
				
			}
			// after payment success for download service
			if (request.getParameter("pageName") != null && request.getParameter("pageName").equalsIgnoreCase("downloadDocumentRedirect")) {
				ArrayList payeeDownloadDetl = bd.getPaymentList(dsdto.getRefId(), "FUN_230");
				if (payeeDownloadDetl != null && payeeDownloadDetl.size() > 0) {
					float paidAmount = 0;
					float totalAmount = 0;
					float amtToBePaid = 0;
					for (int i = 0; i < payeeDownloadDetl.size(); i++) {
						DocumentSearchDTO ldto = (DocumentSearchDTO) payeeDownloadDetl.get(i);
						float paidAmt = ldto.getPaidAmount();
						paidAmount = paidAmount + paidAmt;
						totalAmount = Float.parseFloat(ldto.getTotalFee());
						
					}
					amtToBePaid = totalAmount - paidAmount;
					dform.getDsearchdto().setPaidAmount(paidAmount);
					dform.getDsearchdto().setAmounttobePaid(amtToBePaid);
					if (paidAmount < totalAmount) {
						dform.getDsearchdto().setIsCompletePay(1);
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Kindly pay the remaining amount INR: " + amtToBePaid);
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "कृपया शेष राशि का भुगतान  करें (रुपए) :" + amtToBePaid);
						forwardPage = "usertypeadisp";
					} else {
						dform.getDsearchdto().setIsCompletePay(0);
						forwardPage = "success";
					}
					
				} else {
					forwardPage = "usertypeadisp";
				}
				
			}
			if (request.getParameter("pageName") != null && request.getParameter("pageName").equalsIgnoreCase("downloadDocumentSuccess")) {
				// dform.getDsearchdto().setSuccess("Completed");
				forwardPage = "success";
			}
			
			/*
			 * //on th click of download here
			 * if(request.getParameter("pageName") != null &&
			 * request.getParameter
			 * ("pageName").equalsIgnoreCase("downloadDocument")) { String
			 * fileFolderPath="D:/download/"; //added by shruti String
			 * fileName="registration.pdf"; File fileToDownload = new
			 * File(fileFolderPath+fileName); FileInputStream fileInputStream =
			 * new FileInputStream(fileToDownload); byte[] buf = new byte[(int)
			 * fileToDownload.length()]; fileInputStream.read(buf);
			 * downloadDocument(response, buf, fileName);
			 * fileInputStream.close(); forwardPage="success"; }
			 */

			if (request.getParameter("pageName") != null && request.getParameter("pageName").equalsIgnoreCase("usertypecdisp")) {
				logger.debug("in if usertypeadisp---->" + request.getParameter("pageName"));
				dform.getDsearchdto().setToDate(dform.getToDate());
				dform.getDsearchdto().setFromDate(dform.getFromDate());
				dform.getDsearchdto().setToDate1(dform.getToDate1());
				dform.getDsearchdto().setFromDate1(dform.getFromDate1());
				dform.getDsearchdto().setRegDate(dform.getDateOfReg());
				dform.getDsearchdto().setRegFlag(dform.getDsearchdto().getRegFlag());
				dform.getDsearchdto().setTransPartyFirstName(dform.getDsearchdto().getTransPartyFirstName());
				dform.getDsearchdto().setTransPartyMiddName(dform.getDsearchdto().getTransPartyMiddName());
				dform.getDsearchdto().setTransPartyLastName(dform.getDsearchdto().getTransPartyLastName());
				dform.getDsearchdto().setTransPartyAdd(dform.getDsearchdto().getTransPartyAdd());
				dform.getDsearchdto().setPropertyAddr(dform.getDsearchdto().getPropertyAddr());
				dform.getDsearchdto().setOrgName(dform.getDsearchdto().getOrgName());
				
				DocumentSearchDTO sessionDTOObj = dform.getDsearchdto();
				logger.debug("Date value is " + dform.getFromDate());
				logger.debug("Date value is " + dform.getToDate());
				String fName = dform.getDsearchdto().getTransPartyFirstName();
				String mName = dform.getDsearchdto().getTransPartyMiddName();
				String lName = dform.getDsearchdto().getTransPartyLastName();
				
				if (!((mName.equals(null)) || (mName.equals("")))) {
					String fullName = fName.concat(" ").concat(mName).concat(" ").concat(lName);
					logger.info("full name is" + fullName);
					sessionDTOObj.setFullName(fullName);
				} else {
					String fullName = fName.concat(" ").concat(lName);
					logger.info("full name is" + fullName);
					sessionDTOObj.setFullName(fullName);
				}
				
				sessionDTOObj.setFromDate(dform.getFromDate());
				sessionDTOObj.setToDate(dform.getToDate());
				sessionDTOObj.setRegFlag(dform.getDsearchdto().getRegFlag());
				logger.debug("Date To  is" + sessionDTOObj.getToDate());
				logger.debug("Date from  is" + sessionDTOObj.getFromDate());
				logger.debug("in if usertypeadisp----> (DocumentSearchDTO)sessionFormObj.getDsearchdto()" + sessionDTOObj.getDistId());
				String userId = (String) session.getAttribute("UserId");
				logger.debug(" userId is --<><>" + userId);
				String functionId = (String) session.getAttribute("functionId");
				logger.debug(" functionId is --<><>" + functionId);
				String txnid = bd.storeCTypeSearch(CommonConstant.PAYMENT_FLAG, dform.getDsearchdto(), userId, functionId); // for
																															// C
																															// type
																															// B
				
				if (dform.getDsearchdto().getOffUpload() != null) {
					FormFile photo = dform.getDsearchdto().getOffUpload();
					String fileExt = getFileExtension(photo.getFileName());
					String docName = "Optional_Upload." + fileExt;
					dform.getDsearchdto().setDocName(docName);
					String docPath = pr.getValue("igrs_upload_path") + "/21/" + txnid;
					dform.getDsearchdto().setDocPath(docPath);
					boolean up = uploadFile(photo, docName, docPath);
					boolean updateTxn = false;
					updateTxn = bd.updateOfficeTxnDetails(docName, docPath, txnid);
				}
				ArrayList typeBSearchResult = bd.getTypeBSearchDetails(sessionDTOObj);
				dform.getDsearchdto().setRefId(txnid);
				dsdto.setTypeBresultList(typeBSearchResult);
				forwardPage = "usertypecdisp";
			}
			// after success for payment in search b for first time and second
			// time also
			if (request.getParameter("pageName") != null && request.getParameter("pageName").equalsIgnoreCase("usertypebdisp")) {
				logger.debug("in if usertypeadisp---->" + request.getParameter("pageName"));
				DocumentSearchDTO sessionDTOObj = dform.getDsearchdto();
				logger.debug("sessionDTOObj  " + sessionDTOObj.getRefId());
				logger.debug("Date value is " + dform.getToDate());
				sessionDTOObj.setFromDate(dform.getFromDate());
				sessionDTOObj.setToDate(dform.getToDate());
				logger.info("tehsilname " + dform.getDsearchdto().getTehsilName() + "wardname" + dform.getDsearchdto().getWarddName());
				dsdto.setTehsilName(dform.getDsearchdto().getTehsilName());
				dsdto.setAreaTypeName(dform.getDsearchdto().getAreaName());
				dsdto.setWardPatName(dform.getDsearchdto().getWardPatName());
				dsdto.setWarddName(dform.getDsearchdto().getWarddName());
				dsdto.setMohallaName(dform.getDsearchdto().getMohName());
				dsdto.setOrgName(dform.getDsearchdto().getOrgName());
				dsdto.setTransPartyFirstName(dform.getDsearchdto().getTransPartyFirstName());
				dsdto.setTransPartyMiddName(dform.getDsearchdto().getTransPartyMiddName());
				dsdto.setTransPartyLastName(dform.getDsearchdto().getTransPartyLastName());
				dsdto.setTransPartyGender(dform.getDsearchdto().getTransPartyGender());
				dsdto.setTransPartyMotName(dform.getDsearchdto().getTransPartyMotName());
				dsdto.setTransPartyFatName(dform.getDsearchdto().getTransPartyFatName());
				dsdto.setTransPartyAdd(dform.getDsearchdto().getTransPartyAdd());
				dsdto.setPropertyAddr(dform.getDsearchdto().getPropertyAddr());
				dsdto.setDateOfReg(dform.getDateOfReg());
				logger.debug("Date To  is" + sessionDTOObj.getToDate());
				logger.debug("Date To  is" + sessionDTOObj.getToDate());
				logger.debug("Date from  is" + sessionDTOObj.getFromDate());
				logger.debug("in if usertypeadisp----> (DocumentSearchDTO)sessionFormObj.getDsearchdto()" + sessionDTOObj.getDistId());
				ArrayList typeBSearchResult = bd.getTypeBSearchDetails(sessionDTOObj);
				session.setAttribute("typeBSearchResult", typeBSearchResult);
				logger.debug("=in action typeBSearchResult==" + session.getAttribute("typeBSearchResult"));
				dsdto.setTypeBresultList(typeBSearchResult);
				logger.debug(" jsp total fee-->" + sessionDTOObj.getTotalFee());
				feeDsdto = bd.getOthersFeeDuty("FUN_231", "19", dsdto.getUserTypeId());
				dform.getDsearchdto().setServiceFee(feeDsdto.getServiceFee());
				dform.getDsearchdto().setOtherFee(feeDsdto.getOtherFee());
				dform.getDsearchdto().setTotalFee(feeDsdto.getTotalFee());
				session.setAttribute("Fee", feeDsdto.getServiceFee());
				session.setAttribute("Other_Fee", feeDsdto.getOtherFee());
				session.setAttribute("Total_Fee", feeDsdto.getTotalFee());
				dsdto.setTotalFee(sessionDTOObj.getTotalFee());
				forwardPage = "usertypebdisp";
			}
			if (request.getParameter("pageName") != null && request.getParameter("pageName").equalsIgnoreCase("usertypebresult")) {
				logger.debug("in if search---->" + request.getParameter("pageName"));
				DocumentSearchDTO sessionDTO = dform.getDsearchdto();
				String userId = (String) session.getAttribute("UserId");
				String functionId = dsdto.getServiceType();
				String verifyRegisterNum = dform.getDsearchdto().getRegistNumber();
				logger.info("UserId is ===============" + userId);
				logger.info("functionId is ===========" + functionId);
				String doctxnid = dform.getDsearchdto().getRefId();
				String txnid = bd.updatePaymetTxnDetails(CommonConstant.PAYMENT_FLAG, doctxnid, dform.getDsearchdto().getRegistNumber(), userId, functionId);
				logger.info("functionId is ===========" + functionId);
				String amountTobePaid = "";
				ArrayList payeeDetl = bd.getPaymentList(doctxnid, functionId);
				if (payeeDetl != null && payeeDetl.size() > 0) {
					float paidAmount = 0;
					float totalAmount = 0;
					float amtToBePaid = 0;
					for (int i = 0; i < payeeDetl.size(); i++) {
						DocumentSearchDTO ldto = (DocumentSearchDTO) payeeDetl.get(i);
						float paidAmt = ldto.getPaidAmount();
						paidAmount = paidAmount + paidAmt;
						totalAmount = Float.parseFloat(ldto.getTotalFee());
						
					}
					amtToBePaid = totalAmount - paidAmount;
					if (amtToBePaid < 0)
						amtToBePaid = 0;
					dform.getDsearchdto().setPaidAmount(paidAmount);
					dform.getDsearchdto().setAmounttobePaid(amtToBePaid);
					amountTobePaid = String.valueOf(amtToBePaid);
				} else
					amountTobePaid = String.valueOf(dform.getDsearchdto().getTotalFee());
				request.setAttribute("forwardPath", "./docsearchtypea.do?pageName=usertypeadisp&TRFS=NGI");
				request.setAttribute("parentTable", "IGRS_SEARCH_PAYMENT_DETAILS");
				request.setAttribute("parentAmount", amountTobePaid);
				logger.info("PENDING AMOUNT IN DOC SEARCH>>>>>>>>>>>>>>" + amountTobePaid);
				
				logger.info("PARENT AMOUNT IN DOC SEARCH>>>>>>>>>>>>>>" + dform.getDsearchdto().getTotalFee());
				request.setAttribute("parentFunId", functionId);
				request.setAttribute("parentKey", txnid);
				request.setAttribute("parentModName", session.getAttribute("modName"));
				request.setAttribute("parentFunName", session.getAttribute("fnName"));
				request.setAttribute("parentColumnName", "DOC_SEARCH_TXN_ID");
				
				// modified by shruti---19 nov 2014-payment changes
				request.setAttribute("formName", "documentsearchtypeAone");
				// end
				
				if ("en".equalsIgnoreCase(language)) {
					session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
				} else {
					session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
				}
				if ("en".equalsIgnoreCase(language)) {
					session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_ENGLISH);
				} else {
					session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_HINDI);
				}
				logger.info("getting parentAmount is ===============" + request.getAttribute("parentAmount"));
				logger.info("getting functionId is ===========" + request.getAttribute("parentFunId"));
				logger.info("verifyRegisterNum is ===============" + request.getAttribute("parentKey"));
				forwardPage = "usertypebresult";
			}
			
			// added by shruti 23rd sep 2013---------search in case of type A
			if (request.getParameter("pageName") != null && request.getParameter("pageName").equalsIgnoreCase("typeadisp")) {
				DocumentSearchDTO sesstionDTO = dform.getDsearchdto();
				String regNo = sesstionDTO.getRegistNumber();
				String userId = (String) session.getAttribute("UserId");
				String functionId = dsdto.getServiceType();
				
				if (regNo != null && !regNo.equals("")) {
					DocumentSearchDTO resultdto = null;
					String docId = bd.storeSearchATxnDetails(dsdto, userId, functionId);
					logger.info("SEARCH TYPE>>>>>>>>>>>>>>>>>>>" + sesstionDTO.getSearchType());
					String checkProtestDetls = bd.getProtestFlag(dsdto.getRegistNumber());
					if (checkProtestDetls != null && !checkProtestDetls.equals("")) {
						
						resultdto = bd.checkRegistrationIdWithProtest(regNo, language);
					} else {
						resultdto = bd.checkRegistrationId(regNo, language);
					}
					
					dsdto.setRefId(docId);
					if (resultdto == null) {
						dform.getDsearchdto().setErrorName("No such record found");
						forwardPage = "usertypeadisp";
					}
					if (resultdto != null) {
						ArrayList nullVoidDetls = new ArrayList();
						String checkNullVoid = bd.getNullvoidFlag(dsdto.getRegistNumber());
						ArrayList payeeDetl = bd.getPaymentList(dsdto.getRegistNumber(), "FUN_232");
						
						if (checkNullVoid.equalsIgnoreCase("T")) {
							nullVoidDetls = bd.getNullVoidDetls(dsdto.getRegistNumber());
							dsdto.setNullVoidFlag("T");
						} else if (checkNullVoid.equalsIgnoreCase("F")) {
							nullVoidDetls = bd.getNullVoidDetls(dsdto.getRegistNumber());
							dsdto.setNullVoidFlag("F");
						}
						
						if (nullVoidDetls != null && nullVoidDetls.size() > 0) {
							for (int i = 0; i < nullVoidDetls.size(); i++) {
								DocumentSearchDTO dto = (DocumentSearchDTO) nullVoidDetls.get(i);
								String courtName = dto.getCourtName();
								String courtOrderNo = dto.getCourtOrderNo();
								String courtOrderDate = dto.getCourtOrderDate();
								dsdto.setCourtName(courtName);
								dsdto.setCourtOrderNo(courtOrderNo);
								dsdto.setCourtOrderDate(courtOrderDate);
								
							}
						}
						request.setAttribute("abc", resultdto.getKhasraList());
						logger.debug(" in action --<><>" + resultdto);
						session.setAttribute("resultdto", resultdto);
						
						logger.debug("--in action result --<>" + resultdto.getTypeBresult());
						ArrayList refprtList = (ArrayList) resultdto.getPartyDTO().getPartyList();
						logger.debug("party list value size is " + refprtList.size());
						partydto.setPartyList(refprtList);
						dform.setPartyDto(partydto);
						dsdto.setTypeBresult(resultdto.getTypeBresult());
						dsdto.setPropertyList(resultdto.getPropertyList());
						dsdto.setKhasraList(resultdto.getKhasraList());
						dsdto.setCaveatslist(resultdto.getCaveatslist());
						if (checkProtestDetls != null && !checkProtestDetls.equals("")) {
							dsdto.setProtestList(resultdto.getProtestList());
						}
						dsdto.setComplianceList(resultdto.getComplianceList());
						dsdto.setApplicationId(resultdto.getApplicationId());
						
						/*
						 * if(protestDetls!=null && protestDetls.size()>0){
						 * for(int i=0;i<protestDetls.size();i++){
						 * DocumentSearchDTO
						 * dto=(DocumentSearchDTO)protestDetls.get(i); String
						 * protestId=dto.getProtestId(); String
						 * docPath=dto.getProtestDocPath(); String
						 * docName=dto.getProtestDocName(); String
						 * path=docPath+"/"+docName; if(protestId!=null)
						 * dsdto.setProtestId(protestId);
						 * dsdto.setProtestDocName(docName);
						 * dsdto.setProtestDocPath(docPath);
						 * dsdto.setPath(path);
						 * 
						 * } }
						 */

						logger.debug("Party list size in form " + dform.getPartyDto().getPartyList().size());
						
						try {
							if (dsdto.getSearchType() != null) {
								if (dsdto.getSearchType().equalsIgnoreCase("FUN_062")) {
									/*
									 * feeDsdto=bd.getOthersFeeDuty("FUN_230",
									 * "19", "SRO");
									 */
									feeDsdto = bd.getOthersFeeDuty("FUN_230", "19", dsdto.getUserTypeId());
									
								} else if (dsdto.getSearchType().equalsIgnoreCase("FUN_063")) {
									/*
									 * feeDsdto=bd.getOthersFeeDuty("FUN_232",
									 * "19", "SRO");
									 */
									feeDsdto = bd.getOthersFeeDuty("FUN_232", "19", dsdto.getUserTypeId());
								}
							}
							logger.debug("feeDsdto.getOtherFee() " + feeDsdto.getOtherFee());
							logger.debug("feeDsdto.getServiceFee() " + feeDsdto.getServiceFee());
							logger.info("fees is....." + feeDsdto.getTotalFee());
							session.setAttribute("Fee", feeDsdto.getServiceFee());
							session.setAttribute("Other_Fee", feeDsdto.getOtherFee());
							session.setAttribute("Total_Fee", feeDsdto.getTotalFee());
							dform.getDsearchdto().setTotalFee(feeDsdto.getTotalFee());
						} catch (Exception e) {
							logger.info(e);
						}
						forwardPage = "usertypeadisp";
					} else {
						forwardPage = "usertypeadisp";
					}
				} else {
					// MODIFIED BY SHRUTI--14 FEB 2014
					dsdto.setDateOfReg(dform.getDateOfReg());
					String docId = bd.storeSearchATxnDetails(dsdto, userId, functionId);
					logger.info("SEARCH TYPE>>>>>>>>>>>>>>>>>>>" + sesstionDTO.getSearchType());
					DocumentSearchDTO resultdto = bd.checkOldRegistrationId(dsdto, language);
					String checkProtestDetls = bd.getProtestFlag(dsdto.getRegistNumber());
					if (resultdto == null) {
						dform.getDsearchdto().setErrorName("No such record found");
						
						forwardPage = "usertypeadisp";
						
					}
					// logger.info(resultdto.getKhasraList().size());
					dsdto.setRefId(docId);
					
					if (resultdto != null) {
						request.setAttribute("abc", resultdto.getKhasraList());
						logger.debug(" in action --<><>" + resultdto);
						session.setAttribute("resultdto", resultdto);
						
						logger.debug("--in action result --<>" + resultdto.getTypeBresult());
						ArrayList refprtList = (ArrayList) resultdto.getPartyDTO().getPartyList();
						logger.debug("party list value size is " + refprtList.size());
						partydto.setPartyList(refprtList);
						dform.setPartyDto(partydto);
						dsdto.setTypeBresult(resultdto.getTypeBresult());
						dsdto.setPropertyList(resultdto.getPropertyList());
						dsdto.setKhasraList(resultdto.getKhasraList());
						if (checkProtestDetls != null && !checkProtestDetls.equals("")) {
							dsdto.setProtestList(resultdto.getProtestList());
						}
						dsdto.setCaveatslist(resultdto.getCaveatslist());
						dsdto.setComplianceList(resultdto.getComplianceList());
						dsdto.setApplicationId(resultdto.getApplicationId());
						logger.debug("Party list size in form " + dform.getPartyDto().getPartyList().size());
						try {
							if (dsdto.getSearchType() != null) {
								if (dsdto.getSearchType().equalsIgnoreCase("FUN_062")) {
									/*
									 * feeDsdto=bd.getOthersFeeDuty("FUN_230",
									 * "19", "SRO");
									 */
									feeDsdto = bd.getOthersFeeDuty("FUN_230", "19", dsdto.getUserTypeId());
									if ("en".equalsIgnoreCase(language)) {
										session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
									} else {
										session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
									}
									if ("en".equalsIgnoreCase(language)) {
										session.setAttribute("fnName", CommonConstant.DOC_SRCH_A_FUNCTION_PAY_ENGLISH);
									} else {
										session.setAttribute("fnName", CommonConstant.DOC_SRCH_A_FUNCTION_PAY_HINDI);
									}
								} else if (dsdto.getSearchType().equalsIgnoreCase("FUN_063")) {
									/*
									 * feeDsdto=bd.getOthersFeeDuty("FUN_232",
									 * "19", "SRO");
									 */
									feeDsdto = bd.getOthersFeeDuty("FUN_232", "19", dsdto.getUserTypeId());
									if ("en".equalsIgnoreCase(language)) {
										session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
									} else {
										session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
									}
									if ("en".equalsIgnoreCase(language)) {
										session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_ENGLISH);
									} else {
										session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_HINDI);
									}
								}
							}
							logger.debug("feeDsdto.getOtherFee() " + feeDsdto.getOtherFee());
							logger.debug("feeDsdto.getServiceFee() " + feeDsdto.getServiceFee());
							logger.info("fees is....." + feeDsdto.getTotalFee());
							session.setAttribute("Fee", feeDsdto.getServiceFee());
							session.setAttribute("Other_Fee", feeDsdto.getOtherFee());
							session.setAttribute("Total_Fee", feeDsdto.getTotalFee());
							dsdto.setTotalFee(feeDsdto.getTotalFee());
						} catch (Exception e) {
							logger.info(e);
						}
						forwardPage = "usertypeadisp";
					}
				}
			}
			
			// end
			if (request.getParameter("pageName") != null && request.getParameter("pageName").equalsIgnoreCase("typebdisp")) {
				logger.debug("in if usertypeadisp---->" + request.getParameter("pageName"));
				DocumentSearchDTO sessionDTOObj = dform.getDsearchdto();
				logger.debug("Date value is " + dform.getToDate());
				sessionDTOObj.setFromDate(dform.getFromDate());
				sessionDTOObj.setToDate(dform.getToDate());
				dform.getDsearchdto().setToDate1(dform.getToDate1());
				dform.getDsearchdto().setFromDate1(dform.getFromDate1());
				dform.getDsearchdto().setRegDate(dform.getDateOfReg());
				dform.getDsearchdto().setRegFlag(dform.getDsearchdto().getRegFlag());
				session.setAttribute("sessionTypeBDTOObj", dform.getDsearchdto());
				logger.info("tehsilname " + dform.getDsearchdto().getTehsilName() + "wardname" + dform.getDsearchdto().getWarddName());
				
				String userId = (String) session.getAttribute("UserId");
				String functionId = dsdto.getServiceType();
				String txnid = bd.storeCTypeSearch(CommonConstant.PAYMENT_FLAG, dsdto, userId, functionId);
				dform.getDsearchdto().setRefId(txnid);
				
				dsdto.setTehsilName(dform.getDsearchdto().getTehsilName());
				dsdto.setAreaTypeName(dform.getDsearchdto().getAreaName());
				dsdto.setWardPatName(dform.getDsearchdto().getWardPatName());
				dsdto.setWarddName(dform.getDsearchdto().getWarddName());
				dsdto.setMohallaName(dform.getDsearchdto().getMohName());
				dsdto.setOrgName(dform.getDsearchdto().getOrgName());
				dsdto.setTransPartyFirstName(dform.getDsearchdto().getTransPartyFirstName());
				dsdto.setTransPartyMiddName(dform.getDsearchdto().getTransPartyMiddName());
				dsdto.setTransPartyLastName(dform.getDsearchdto().getTransPartyLastName());
				dsdto.setTransPartyGender(dform.getDsearchdto().getTransPartyGender());
				dsdto.setTransPartyMotName(dform.getDsearchdto().getTransPartyMotName());
				dsdto.setTransPartyFatName(dform.getDsearchdto().getTransPartyFatName());
				dsdto.setTransPartyAdd(dform.getDsearchdto().getTransPartyAdd());
				dsdto.setPropertyAddr(dform.getDsearchdto().getPropertyAddr());
				dsdto.setDateOfReg(dform.getDateOfReg());
				
				logger.debug("Date To  is" + sessionDTOObj.getToDate());
				logger.debug("Date To  is" + sessionDTOObj.getToDate());
				logger.debug("Date from  is" + sessionDTOObj.getFromDate());
				logger.debug("in if usertypeadisp----> (DocumentSearchDTO)sessionFormObj.getDsearchdto()" + sessionDTOObj.getDistId());
				ArrayList typeBSearchResult = bd.getTypeBSearchDetails(sessionDTOObj);
				session.setAttribute("typeBSearchResult", typeBSearchResult);
				logger.debug("=in action typeBSearchResult==" + session.getAttribute("typeBSearchResult"));
				dsdto.setTypeBresultList(typeBSearchResult);
				logger.debug(" jsp total fee-->" + sessionDTOObj.getTotalFee());
				/* feeDsdto=bd.getOthersFeeDuty("FUN_231", "19", "SRO"); */
				if ("en".equalsIgnoreCase(language)) {
					session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
				} else {
					session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
				}
				if ("en".equalsIgnoreCase(language)) {
					session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_ENGLISH);
				} else {
					session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_HINDI);
				}
				feeDsdto = bd.getOthersFeeDuty("FUN_231", "19", dsdto.getUserTypeId());
				dform.getDsearchdto().setServiceFee(feeDsdto.getServiceFee());
				dform.getDsearchdto().setOtherFee(feeDsdto.getOtherFee());
				dform.getDsearchdto().setTotalFee(feeDsdto.getTotalFee());
				session.setAttribute("Fee", feeDsdto.getServiceFee());
				session.setAttribute("Other_Fee", feeDsdto.getOtherFee());
				session.setAttribute("Total_Fee", feeDsdto.getTotalFee());
				dsdto.setTotalFee(sessionDTOObj.getTotalFee());
				forwardPage = "usertypebdisp";
			}
			// added by saurav for document search without login service

			if (("documentsearchtypeAonenu").equalsIgnoreCase(formNameNU)) {
				if (null == request.getParameter("pageName")) {
					if (userId.isEmpty()) {
						dform.getDsearchdto().setErrorMessage("");
						dform.getDsearchdto().setRegistNumber("");
						dform.getDsearchdto().setLangSelect("");
						String lang = "hi";
						if (null != request.getParameter("lang")) {
							lang = (String) request.getParameter("lang");
						} else {
							if (null != session.getAttribute("languageLocale")) {
								lang = (String) session.getAttribute("languageLocale");
							}
						}
						if (!("en".equalsIgnoreCase(lang) || "hi".equalsIgnoreCase(lang))) {
							lang = "hi";
						}
						country = new String("IN");
						currentLocale = new Locale(lang, country);
						session.setAttribute("languageLocale", lang);
						session.setAttribute("org.apache.struts.action.LOCALE", currentLocale);
						messages = ResourceBundle.getBundle("resources.MessagesBundle", currentLocale);
						dsdto.setServiceType("FUN_062");
						forwardPage = "usertypeanu";
					} else {
						forwardPage = "usertypeadisplog";
					}
				} else {
					dform.getDsearchdto().setErrorMessage("");
					Boolean isResponseCorrect = Boolean.FALSE;
					String captchaId = (String) request.getSession().getAttribute("QARFAD");
					String responses = request.getParameter("j_captcha_response");
					try {
						isResponseCorrect = IGRSCommon.validateCaptcha(captchaId, responses);
					} catch (CaptchaServiceException e) {
						logger.debug("Failed to get Captcha", e);
					}
					if (!isResponseCorrect) {
						dform.getDsearchdto().setErrorMessage("CF");
						forwardPage = "usertypeanu";
						logger.debug("----------------> Captcha failed..");
					} else {
						if ("typeadispnu".equalsIgnoreCase(request.getParameter("pageName"))) {
							DocumentSearchDTO sesstionDTO = dform.getDsearchdto();
							String regNo = sesstionDTO.getRegistNumber();
							String userId = (String) session.getAttribute("UserId");
							String functionId = dsdto.getServiceType();
							String lang = "hi";
							if (null != dform.getDsearchdto().getLangSelect() & !("").equals(dform.getDsearchdto().getLangSelect())) {
								lang = dform.getDsearchdto().getLangSelect();
							}
							country = new String("IN");
							currentLocale = new Locale(lang, country);
							dform.getDsearchdto().setLangSelect(lang);
							language = lang;
							session.setAttribute("languageLocale", lang);
							session.setAttribute("org.apache.struts.action.LOCALE", currentLocale);
							messages = ResourceBundle.getBundle("resources.MessagesBundle", currentLocale);
							boolean validRegno = bd.getValidregNo(sesstionDTO.getRegistNumber());
							if (regNo != null && !regNo.equals("")) {
								DocumentSearchDTO resultdto = null;
								logger.info("SEARCH TYPE>>>>>>>>>>>>>>>>>>>" + sesstionDTO.getSearchType());
								String checkProtestDetls = bd.getProtestFlag(dsdto.getRegistNumber());
								if (checkProtestDetls != null && !checkProtestDetls.equals("")) {
									resultdto = bd.checkRegistrationIdWithProtest(regNo, language);
								} else {
									resultdto = bd.checkRegistrationId(regNo, language);
								}
								if (resultdto == null) {
									dform.getDsearchdto().setErrorName("No such record found");
									request.removeAttribute("abc");
									session.removeAttribute("resultdto");
									dform.setDsearchdto(new DocumentSearchDTO());
									dsdto = new DocumentSearchDTO();
									forwardPage = "usertypeadisp";
								}
								if (resultdto != null) {
									String docId = bd.storeSearchATxnDetails(dsdto, "FREE-DOCSEARCHA", functionId);
									ArrayList nullVoidDetls = new ArrayList();
									String checkNullVoid = bd.getNullvoidFlag(dsdto.getRegistNumber());
									if (checkNullVoid.equalsIgnoreCase("T")) {
										nullVoidDetls = bd.getNullVoidDetls(dsdto.getRegistNumber());
										dsdto.setNullVoidFlag("T");
									} else if (checkNullVoid.equalsIgnoreCase("F")) {
										nullVoidDetls = bd.getNullVoidDetls(dsdto.getRegistNumber());
										dsdto.setNullVoidFlag("F");
									}

									if (nullVoidDetls != null && nullVoidDetls.size() > 0) {
										for (int i = 0; i < nullVoidDetls.size(); i++) {
											DocumentSearchDTO dto = (DocumentSearchDTO) nullVoidDetls.get(i);
											String courtName = dto.getCourtName();
											String courtOrderNo = dto.getCourtOrderNo();
											String courtOrderDate = dto.getCourtOrderDate();
											dsdto.setCourtName(courtName);
											dsdto.setCourtOrderNo(courtOrderNo);
											dsdto.setCourtOrderDate(courtOrderDate);

										}
									}
									request.setAttribute("abc", resultdto.getKhasraList());
									logger.debug(" in action --<><>" + resultdto);
									session.setAttribute("resultdto", resultdto);

									logger.debug("--in action result --<>" + resultdto.getTypeBresult());
									ArrayList refprtList = (ArrayList) resultdto.getPartyDTO().getPartyList();
									logger.debug("party list value size is " + refprtList.size());
									partydto.setPartyList(refprtList);
									dform.setPartyDto(partydto);
									dsdto.setTypeBresult(resultdto.getTypeBresult());
									dsdto.setPropertyList(resultdto.getPropertyList());
									dsdto.setKhasraList(resultdto.getKhasraList());
									dsdto.setCaveatslist(resultdto.getCaveatslist());
									if (checkProtestDetls != null && !checkProtestDetls.equals("")) {
										dsdto.setProtestList(resultdto.getProtestList());
									}
									dsdto.setComplianceList(resultdto.getComplianceList());
									dsdto.setApplicationId(resultdto.getApplicationId());
									logger.debug("Party list size in form " + dform.getPartyDto().getPartyList().size());
									forwardPage = "usertypeadispnu";
								} else {
									forwardPage = "usertypeadispnu";
								}
							} else {
								// MODIFIED BY SHRUTI--14 FEB 2014
								dsdto.setDateOfReg(dform.getDateOfReg());
								logger.info("SEARCH TYPE>>>>>>>>>>>>>>>>>>>" + sesstionDTO.getSearchType());
								if (validRegno) {

									dform.getDsearchdto().setErrorName("No such record found");
								} else {
									request.setAttribute("commonerror", "lbl_doc_search_error");
								}

								forwardPage = "usertypeadispnu";
							}

						}
					}
				}
			}
			// added by shruti---11 june 2014
			if (request.getParameter("actionName") != null) {
				String actionName = request.getParameter("actionName");
				if ("downLoadPropertyMap".equalsIgnoreCase(actionName)) {
					String filePath = request.getParameter("path");
					logger.info(">>>>>" + filePath);
					try {
						DMSUtility dmsUtil = new DMSUtility();
						byte contents[] = dmsUtil.getDocumentBytes(filePath);
						downloadDocument(response, contents, filePath);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				/*
				 * else
				 * if(("downLoadPropertestDoc".equalsIgnoreCase(actionName))){
				 * String filePath= request.getParameter("path");
				 * logger.info(">>>>>"+filePath); try { DMSUtility dmsUtil=new
				 * DMSUtility(); byte contents[]
				 * =dmsUtil.getDocumentBytes(filePath);
				 * downloadDocument(response, contents, filePath); } catch
				 * (Exception e) { e.printStackTrace(); } }
				 */
			}
			// end
			logger.debug("Party list size in form  new value" + dform.getPartyDto().getPartyList().size());
			logger.debug("-- in action before setting into session--<>" + dsdto.getDistList().size());
			dform.setDsearchdto(dsdto);
			logger.info("error value---------------------" + dform.getDsearchdto().getErrorName());
			session.setAttribute("dform", dform);
			logger.debug("before forward--<><>" + dform.getDsearchdto().getTypeBresultList());
		} catch (Exception c) {
			logger.error(c);
			mapping.findForward("failure");
		}
		logger.info(" in action before forward 1234--<>" + forwardPage);
		logger.debug("before payment >>> Page Name-->" + request.getParameter("pageName"));
		logger.debug("in IF--- request path<>" + request.getAttribute("forwardPath"));
		logger.debug("Forward page is: " + forwardPage);
		logger.debug("Returned Jsp is : @@@@ " + mapping.findForward(forwardPage));
		return mapping.findForward(forwardPage);
	}
	
	public static void downloadDocument(HttpServletResponse res, byte[] docContents, String fileName) {
		try {
			OutputStream os = res.getOutputStream();
			res.setContentType("application/download");
			res.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			os.write(docContents);
			os.flush();
			os.close();
		} catch (Exception e) {
		}
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
	private String removeFile(String fileName, String filePath) {
		File newFile = new File(filePath + fileName);
		newFile.delete();
		
		return "";
	}
	
	private boolean uploadFile(FormFile filetobeuploaded, String fileName, String filePath) {
		
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
	
}
