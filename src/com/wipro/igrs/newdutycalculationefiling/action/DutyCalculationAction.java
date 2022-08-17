/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :  DutyCalculationAction.java 
 * Author      :  Madan Mohan 
 * Description :   
 */

/**
 * File Name: DutyCalculationAction.java
 * @author Madan Mohan
 * @version 1.0
 * Created on 14 jan 2008
 *
 */
package com.wipro.igrs.newdutycalculationefiling.action;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.commonEfiling.PropertiesFileReader;
import com.wipro.igrs.commonEfiling.mime.MIMECheck;
import com.wipro.igrs.newPropvaluationefiling.dto.PropertyValuationDTO;
import com.wipro.igrs.newdutycalculationefiling.bo.CalculateDuty;
import com.wipro.igrs.newdutycalculationefiling.bo.CalculateRegFee;
import com.wipro.igrs.newdutycalculationefiling.bo.DutyCalculationBO;
import com.wipro.igrs.newdutycalculationefiling.constant.CommonConstant;
import com.wipro.igrs.newdutycalculationefiling.dto.UserEnterableDTO;
import com.wipro.igrs.newdutycalculationefiling.form.DutyCalculationForm;
import com.wipro.igrs.newreginitefiling.bo.RegCommonBO;
import com.wipro.igrs.newreginitefiling.form.RegCommonForm;
import com.wipro.igrs.propertyvaluationefiling.bo.PropertyValuationBO;
import com.wipro.igrs.propertyvaluationefiling.dto.DutyCalculationDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.ExemptionDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.InstrumentsDTO;

/**
 * @author PR836429 Preeti Kuralkar 20 May 2016
 * 
 */
public class DutyCalculationAction extends BaseAction {

	/**
	 * @see forwardJsp is used for redirecting
	 */

	private String forwardJsp = CommonConstant.FORWARD_PAGE_HOME;
	private String estampCode;
	private String datephyestamp;
	int duty = 0;

	/**
	 * @author Vinay Sharma
	 */
	private Logger logger = (Logger) Logger
			.getLogger(DutyCalculationAction.class);

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @exception Exception
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		if (form != null) {

			DutyCalculationForm eForm = (DutyCalculationForm) form;

			eForm.setPropImage1DocumentName("");
			eForm.setPropImage1DocumentName(null);
			eForm.setEmptyFile("");

			DutyCalculationBO bo = new DutyCalculationBO();
			PropertyValuationBO pbo = new PropertyValuationBO();

			eForm.getDutyCalculationDTO().setCurrentYear(bo.getCurrentYear());
			String page = (String) request.getParameter("page");
			String fwdPage = request.getParameter("fwdName");
			if (request.getParameter("hdnApplicationIdInitate") != null) {
				String regTxnId5 = request
						.getParameter("hdnApplicationIdInitate");
				session.setAttribute(regTxnId5, "regTxnId5");
			}
			String pathfile = request.getParameter("pathfile");
			String userId = (String) session.getAttribute("UserId");
			String language = (String) session.getAttribute("languageLocale");
			int deedIdJSP = eForm.getDutyCalculationDTO().getDeedId();
			request.setAttribute("deedId", eForm.getDutyCalculationDTO()
					.getDeedId());
			eForm.getDutyCalculationDTO().setUserId(userId);
			eForm.getDutyCalculationDTO().setOpenPopUpPremium("");
			eForm.getDutyCalculationDTO().setOpenPopUpRent("");
			eForm.setPaymentFlag("N");
			String locale = CommonConstant.LANGUAGE_HINDI;
			Locale currentLocale;
			if (session.getAttribute("org.apache.struts.action.LOCALE") != null) {
				currentLocale = (Locale) session
						.getAttribute("org.apache.struts.action.LOCALE");
				locale = currentLocale.toString();

			}
			logger.debug("Page  " + page);
			DutyCalculationDTO dto = eForm.getDutyCalculationDTO();
			logger.debug("Before action");
			String formName = dto.getFormName();
			String actionName = dto.getActionName();

			String modName = (String) request.getParameter("modName");
			logger.debug("modName:-" + modName);
			String fnName = (String) request.getParameter("fnName");
			logger.debug("modName:-" + modName);
			boolean regFlag = true;
			String fromModule = (String) request.getParameter("fromModule");
			String rentRequest = (String) request.getParameter("pageAction");
			if (fromModule != null) {

				if (fromModule.equalsIgnoreCase("propVal")) {
					page = "";
					formName = "";
					actionName = CommonConstant.RETURN_FROM_PROP_VAL;

				}

			}

			if (modName != null && fnName != null) {
				session.setAttribute("modName", modName);
				session.setAttribute("fnName", fnName);
			}

			// to view upload form
			if (fwdPage != null
					&& "downloadUploadedForm".equalsIgnoreCase(fwdPage)) {
				PropertiesFileReader pr = PropertiesFileReader
						.getInstance("resources.igrs");
				String efileTxnId = null;
				efileTxnId = (String) session.getAttribute("regTxnId");
				if (efileTxnId == null) {

					efileTxnId = (String) session.getAttribute("tempefileid");
				}
				String downloadPath = pr.getValue("igrs_upload_path");
				String deedpath = (String) request.getParameter("downloadPath");
				String EfilingUploadPath = downloadPath
						+ CommonConstant.FILE_UPLOAD_PATH + efileTxnId
						+ pathfile;

				byte bytes[] = DMSUtility.getDocumentBytes(EfilingUploadPath);
				if (bytes != null) {
					DMSUtility.downloadDocument(response, pathfile, bytes);
					forwardJsp = "uploadSuccessEFile";

					return mapping.findForward(forwardJsp);
				}
			}
			if (fwdPage != null
					&& "downloadUploadedForm9".equalsIgnoreCase(fwdPage)) {
				PropertiesFileReader pr = PropertiesFileReader
						.getInstance("resources.igrs");
				String efileTxnId = null;
				efileTxnId = (String) session.getAttribute("regTxnId");
				if (efileTxnId == null) {

					efileTxnId = (String) session.getAttribute("tempefileid");
				}
				String downloadPath = pr.getValue("igrs_upload_path");
				String deedpath = (String) request.getParameter("downloadPath");
				String EfilingUploadPath = downloadPath
						+ CommonConstant.FILE_UPLOAD_PATH + efileTxnId
						+ pathfile;

				byte bytes[] = DMSUtility.getDocumentBytes(EfilingUploadPath);
				if (bytes != null) {
					DMSUtility.downloadDocument(response, pathfile, bytes);
					forwardJsp = "dashboardDutyEstampCodePg";

					return mapping.findForward(forwardJsp);
				}
			}

			if (CommonConstant.REGISTRATION_DUTY_PAGE.equals(page)) {

				eForm.setRadioPhFlag("");
				eForm.setExemptionDTOList(new ArrayList());
				eForm.setInstrumentDTOList(new ArrayList());
				eForm.setDutyCalculationDTO(new DutyCalculationDTO());
				// dto.setFirstName("");
				// dto.setMidName("");
				// dto.setLastName("");
				// dto.setDob("");
				// dto.setDobDay("");
				// dto.setDobMonth("");
				// dto.setDobYear("");
				// dto.setSex("");
				eForm.setExempDTO(new ExemptionDTO());
				eForm.getExempDTO().setHdnExempAmount("");
				eForm.getExempDTO().setHdnExAmount("");
				dto.setDutyPaid(0.0);
				dto.setDeedId(0);
				dto.setMunicipalCheck("");
				dto.setAvailExemption("");
				dto.setBaseValue("0.0");// double to string
				eForm.setExemptionDTOList(new ArrayList());
				eForm.setInstrumentDTOList(new ArrayList());
				eForm.setDutyCalculationDTO(new DutyCalculationDTO());
				dto.setFirstName("");
				dto.setMidName("");
				dto.setLastName("");
				dto.setDob("");
				dto.setMinRegDisclaimerFlag("");
				dto.setMinStampDisclaimerFlag("");
				dto.setDobDay("");
				dto.setDobMonth("");
				dto.setCancellationFlag("");
				dto.setDobYear("");
				dto.setSex("");
				dto.setTypeOfTransaction("N");
				dto.setFromModule("");
				dto.setDeedCategoryName("");
				dto.setDeedCategoryId("");
				eForm.setExempDTO(new ExemptionDTO());
				eForm.getExempDTO().setHdnExempAmount("");
				eForm.getExempDTO().setHdnExAmount("");
				dto.setDutyPaid(0.0);
				dto.setDeedId(0);
				dto.setBaseValue("0.0");// double to string
				eForm.setModuleName("");
				eForm.setDutyCalculationDTO(dto);
				eForm.setInstDTO(new InstrumentsDTO());
				dto.setActionName("");
				dto.setPropertyValuationRequired("");
				dto.setPropertyName("");
				dto.setMarketValue("");
				dto.setValuationIdValidate("");
				dto.setValuationId("");
				dto.setHvValId("");
				dto.setHvValIdFlag("");
				dto.setAddPropertyFlag("");
				dto.setFamilyCheck("");
				dto.setFamilyVisible("");
				dto.setFamilyCheckFlag("");
				dto.setPropertyDetailsList(new ArrayList());
				dto.setUserValueList(new ArrayList());
				dto.setExemptionDescpList(new ArrayList());
				dto.setExemptionList(new ArrayList());
				dto.setRegFeeExemptionList(new ArrayList());
				dto.setRegFeeExemptionDiscriptionList(new ArrayList());
				dto.setInstDescription("");
				dto.setDeedDescription("");
				dto.setMunicipalVisible("N");
				dto.setAvailExemptionFlag("");
				eForm.setAvailExemptionFlagEfile("");
				eForm.setExemptionFlagEfile("");
				// eForm.setModuleName("");
				eForm.setDutyCalculationDTO(dto);
				eForm.setInstDTO(new InstrumentsDTO());

				dto.setActionName("");
				forwardJsp = CommonConstant.FORWARD_PAGE_HOME;

				eForm.setModuleName(CommonConstant.REGISTRATION_DUTY_PAGE);
				formName = "dutyHomePage";
				actionName = "nextPage";
			}

			if (CommonConstant.DUTY_PAGE.equals(page)) {
				eForm.setExemptionDTOList(new ArrayList());
				eForm.setInstrumentDTOList(new ArrayList());
				eForm.setDutyCalculationDTO(new DutyCalculationDTO());
				eForm.setPropImage1DocumentName("");
				eForm.setPropImage1DocumentName(null);
				eForm.setEmptyFile("");
				dto.setFirstName("");
				dto.setMidName("");
				dto.setLastName("");
				dto.setTypeOfTransaction("N");
				dto.setDob("");
				dto.setDobDay("");
				dto.setDobMonth("");
				dto.setAvailExemption("");
				dto.setDobYear("");
				dto.setSex("");
				eForm.setExempDTO(new ExemptionDTO());
				eForm.getExempDTO().setHdnExempAmount("");
				eForm.getExempDTO().setHdnExAmount("");
				dto.setDutyPaid(0.0);
				dto.setDeedId(0);
				dto.setBaseValue("0.0");// double to string
				eForm.setModuleName("");
				eForm.setDutyCalculationDTO(dto);
				eForm.setInstDTO(new InstrumentsDTO());
				eForm.setRadioEFlag("");
				eForm.setRadioPFlag("");
				dto.setActionName("");
				dto.setPropertyValuationRequired("");
				dto.setPropertyName("");
				dto.setMarketValue("");
				dto.setValuationIdValidate("");
				dto.setValuationId("");
				dto.setHvValId("");
				dto.setMinRegDisclaimerFlag("");
				dto.setMinStampDisclaimerFlag("");
				dto.setHvValIdFlag("");
				dto.setAddPropertyFlag("");
				dto.setPropertyDetailsList(new ArrayList());
				dto.setUserValueList(new ArrayList());
				dto.setExemptionDescpList(new ArrayList());
				dto.setExemptionList(new ArrayList());
				dto.setRegFeeExemptionList(new ArrayList());
				dto.setRegFeeExemptionDiscriptionList(new ArrayList());
				dto.setInstDescription("");

				dto.setFromModule("");

				dto.setCancellationFlag("");
				dto.setDeedCategoryId("");
				dto.setDeedCategoryName("");
				dto.setFamilyCheck("");
				dto.setFamilyVisible("");
				dto.setFamilyCheckFlag("");
				dto.setDeedDescription("");
				dto.setMunicipalCheck("");
				dto.setMunicipalCheckFlag("");
				dto.setMunicipalVisible("N");
				dto.setActionName("");
				eForm.setRadioPhFlag("");
				actionName = "";
				forwardJsp = CommonConstant.FORWARD_PAGE_HOME;

			}

			if (request.getParameter("pageName") != null) {
				if (request.getParameter("pageName").equalsIgnoreCase(
						"efiledashboard")) {
					String regTxnId;
					regTxnId = request.getParameter("hdnApplicationIdInitate");
					if (regTxnId == null) {
						// RegCommonForm regform= new RegCommonForm();

						regTxnId = request.getAttribute("regTxnId").toString();
						// eForm.setHidnRegTxnId((String)
						// request.getAttribute("regTxnId"));
					}
					eForm.setHidnRegTxnId(regTxnId);
					RegCommonBO commonBo = new RegCommonBO();
					String getdutyId = commonBo.getdutyId(regTxnId);
					String exeDate = commonBo.getexeDate(getdutyId);
					session.setAttribute("exeDate", exeDate);
					DutyCalculationForm dutyForm1 = new DutyCalculationForm();

					dutyForm1.setHidnRegTxnId(regTxnId);

					dutyForm1.setSlotdate(exeDate);
					dutyForm1.setRadioResiComm("");
					dutyForm1.setPropImage1DocumentName("");

					dutyForm1.getDutyCalculationDTO().setSlotdate(exeDate);
					dutyForm1.getDutyCalculationDTO().setHidnRegTxnId(regTxnId);

					request.setAttribute("slotdate", dutyForm1.getSlotdate());
					request
							.setAttribute("efileId", dutyForm1
									.getHidnRegTxnId());
					String efileId1 = dutyForm1.getHidnRegTxnId();

					forwardJsp = CommonConstant.FORWARD_PAGE_HOME1;

					return mapping.findForward(forwardJsp);

				}
			}

			// dashboard upload in SAN
			if (CommonConstant.UPLOAD_FILE_ANGLE_1_DASHBOARD.equals(actionName)) {
				System.out.println("inside upload method of action ");
				String efileTxnId = "";

				efileTxnId = eForm.getHidnRegTxnId();

				// below code is for jsp does not get null value on request
				// parameter
				RegCommonBO commonBo = new RegCommonBO();
				String getdutyId = commonBo.getdutyId(efileTxnId);
				String exeDate = commonBo.getexeDate(getdutyId);
				request.setAttribute("slotdate", exeDate);
				request.setAttribute("efileId", efileTxnId);
				// end

				String filePath;
				String path = "";

				FormFile uploadedFile = eForm.getPropImage1();
				// to check file size
				int size = uploadedFile.getFileSize();
				double fileSizeInBytes = size;
				// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
				double fileSizeInKB = fileSizeInBytes / 1024.0;
				if (fileSizeInKB > CommonConstant.MAX_FILE_SIZE) {
					request
							.setAttribute(
									CommonConstant.lbl_reg_init_upload_msg3,
									"File size is Greater than 500 KB. Please select another file. / फ़ाइल का माप 500 KB से अधिक है। अन्य फाइल चुनें।");
					eForm.setFileSizeUploadError("Y");
					forwardJsp = "uploadSuccess1";
					return mapping.findForward(forwardJsp);
				}
				// check for file extension
				MIMECheck mimeCheck = new MIMECheck();
				String fileExt = mimeCheck.getFileExtension(uploadedFile
						.getFileName());
				Boolean mime = mimeCheck.validateFileTypeEfile(fileExt);

				// end of file extension

				if (mime) {
					PropertiesFileReader pr = PropertiesFileReader
							.getInstance("resources.igrs");
					path = pr.getValue("upload.location");
					filePath = path + CommonConstant.FILE_UPLOAD_PATH
							+ efileTxnId;
					File folder = new File(filePath);
					// String fileName=uploadedFile.getFileName();
					String fileName = CommonConstant.FILE_UPLOAD_INITATE;
					eForm.setPropImage1DocumentName(fileName);
					boolean pathStatus = false;
					pathStatus = bo.getuploadformpath(efileTxnId, filePath);
					byte[] content = uploadedFile.getFileData();
					if (!folder.exists()) {
						folder.mkdirs();
					}

					try {
						File newFile = new File(filePath, fileName);
						FileOutputStream fos = new FileOutputStream(newFile);
						fos.write(content);
						fos.close();

						boolean uploadStatus = true;

						if (uploadStatus == true) {
							if (language.equalsIgnoreCase("en"))
								request.setAttribute(
										CommonConstant.SUCCESS_MSG_UPLOAD,
										"Successfully Uploaded.");
							else
								request.setAttribute(
										CommonConstant.SUCCESS_MSG_UPLOAD,
										"सफलतापूर्वक अपलोड की गई");
							forwardJsp = "uploadSuccess1";
						}

						else {
							// session.setAttribute("checkStatus",
							// Constants.PROBLEM_IN_AS);
							if (language.equalsIgnoreCase("en"))
								request.setAttribute(
										CommonConstant.FAILURE_MSG_UPLOAD,
										"Not Uploaded Successfully");
							else
								request.setAttribute(
										CommonConstant.FAILURE_MSG_UPLOAD,
										"सफलतापूर्वक अपलोड नहीं किया गया");
							forwardJsp = "uploadSuccess1";
						}

						return mapping.findForward(forwardJsp);

					} catch (Exception ex) {

						ex.printStackTrace();
					}
				} else {
					eForm.setUploadFileError("Y");
					if (language.equalsIgnoreCase("en"))
						request
								.setAttribute(
										CommonConstant.ERRORFILETYPE_MSG_UPLOAD,
										"Please Upload a Valid File.File should be in PDF Format.");
					else
						request
								.setAttribute(
										CommonConstant.ERRORFILETYPE_MSG_UPLOAD,
										"कृपया एक मान्य फाइल अपलोड करें। फाइल पीडीएफ प्रारूप में होनी चाहिए।");
					forwardJsp = "uploadSuccess1";
					return mapping.findForward(forwardJsp);
				}
			}

			// method to generate efile number

			if (request.getParameter("tehsilID") != null) {
				System.out.println("inside efile number method");
				String tempEfileId = "";
				tempEfileId = eForm.getHidnRegTxnId();

				String district = "";
				String districtSec = "";
				String officeSRName = "";
				String officeSRId = "";

				// generating last seq number
				Calendar currentDate = Calendar.getInstance();
				SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
				String dateNow = formatter.format(currentDate.getTime());
				String regTxnIdSeq = bo.getFinalSeqEfile();

				// for First 3 digit seq

				String fSeq = bo.getfSeq1();

				// for district
				String districtSeq;
				ArrayList districtId = bo.getDistrictOfProperty(tempEfileId); // Modified
																				// by
																				// Gulrej
																				// on
																				// 5
																				// th
																				// Sept
																				// ,
																				// 2017

				for (int i = 0; i < districtId.size(); i++) {
					ArrayList abc = (ArrayList) districtId.get(0);
					district = (String) abc.get(0);

				}

				// String district= (String) session.getAttribute("districtId");
				System.out.println("district abccsgavdjhsdvfhdv" + district);

				if (district.length() != 2 && !(district.length() >= 2)) {
					districtSeq = "0" + district;
				} else {

					// districtSeq= (String) session.getAttribute("districtId");
					districtSeq = district;
				}

				String regTxnId1 = null;
				regTxnId1 = fSeq + districtSeq + dateNow + regTxnIdSeq;
				System.out.println("number" + regTxnId1);

				String officeName = request.getParameter("tehsilID");

				String officeId = bo.getOffIdEfile(officeName, language);

				// to get how many office are mapped to office id

				// ArrayList officeMap=bo.getOfficeMapEfile(officeId);
				ArrayList officeMap = bo.getOfficeMapEfile(officeName);

				// below code to distrubte efilining application

				int no = officeMap.size();
				Random randomGenerator = new Random();
				int index = randomGenerator.nextInt(no);
				int count = 0;
				String roleID = null;
				for (int i = 0; i < officeMap.size(); i++) {
					ArrayList abc = (ArrayList) officeMap.get(index);
					officeSRName = (String) abc.get(0);
					officeSRId = (String) abc.get(1);
					roleID = (String) abc.get(2);
				}

				// end of code
				if (eForm.getSlotdate().equalsIgnoreCase("")) {
					RegCommonBO commonBo = new RegCommonBO();
					String getdutyId = commonBo.getdutyId(tempEfileId);
					String exeDate = commonBo.getexeDate(getdutyId);

					eForm.setSlotdate(exeDate);
				}
				bo.submitDetails(eForm, tempEfileId, session.getAttribute(
						"UserId").toString());
				System.out.println("abc final efiling number display to user"
						+ regTxnId1);

				bo.submitEfileNoDetails(tempEfileId, regTxnId1, officeName,
						officeId, officeSRName, officeSRId);

				eForm.getDutyCalculationDTO().setHidEfileTxnId(regTxnId1);
				// eForm.getDutyCalculationDTO().setOfficeNameSR(officeName);
				eForm.getDutyCalculationDTO().setOfficeNameSR(officeId);

				String Finalpage03 = "53";
				boolean updateStausLast = bo.updateStausLast(tempEfileId,
						Finalpage03, roleID, officeSRId);

				// boolean updateCount=bo.updateCount();
				eForm.setSRFlag("");
				eForm.setEfileGenerateFlag("Y");
				eForm.getDutyCalculationDTO().setOfficeId("");
				forwardJsp = "efilenumber";
				return mapping.findForward(forwardJsp);
			}

			// method for select SR

			if (CommonConstant.DUTY_CALCULATE_ACTION_SROfficeDisplay
					.equals(actionName)) {
				String tempEfileId = "";
				tempEfileId = eForm.getHidnRegTxnId();

				/* Commented By Gulrej on 15th May, 2017 */
				// below check is used to distrubute application at
				// PropertyValuation if deed involve property valuation
				/*
				 * String propertyFlag=bo.getpropertyFlag(tempEfileId); String
				 * propValRequried=bo.getpropValRequried(propertyFlag);
				 * 
				 * if(propValRequried.equalsIgnoreCase("Y")){
				 */
				/* Commented By Gulrej on 15th May, 2017 */

				String valId = bo.getValId(tempEfileId);
				String tehsilId = bo.getValtehsilId(valId);
				ArrayList officeName = bo.getoffNameEfile(tehsilId, language);
				eForm.getDutyCalculationDTO().setOfficeList(officeName);
				eForm.setSRFlag("Y");
				eForm.setEfileGenerateFlag("");
				eForm.setRadioResiComm("");

				forwardJsp = "efilenumber";
				return mapping.findForward(forwardJsp);
				// }
				// boolean propValEfileId=false;
				// propValEfileId=bo.getpropValId(tempEfileId);

				/*
				 * if(propValEfileId){ String valId=bo.getValId(tempEfileId);
				 * String tehsilId=bo.getValtehsilId(valId); ArrayList
				 * officeName =bo.getoffNameEfile(tehsilId);
				 * eForm.getDutyCalculationDTO().setOfficeList(officeName);
				 * eForm.setSRFlag("Y"); eForm.setEfileGenerateFlag("");
				 * 
				 * forwardJsp="efilenumber"; return
				 * mapping.findForward(forwardJsp); }
				 */

				/* else{ */

				/* Commented By Gulrej on 15th May, 2017 */
				/*
				 * else{ String district=""; ArrayList
				 * districtId=bo.getDistrictEfile(tempEfileId); for (int i = 0;
				 * i < districtId.size(); i++){ ArrayList abc = (ArrayList)
				 * districtId.get(0); district= (String) abc.get(0);
				 * eForm.getDutyCalculationDTO().setOfficeList(new ArrayList());
				 * ArrayList officeName =bo.getoffNameEfile(district,language);
				 * eForm.getDutyCalculationDTO().setOfficeList(officeName);
				 * 
				 * eForm.setSRFlag("Y"); eForm.setEfileGenerateFlag("");
				 * eForm.getDutyCalculationDTO().setOfficeName("-1");
				 * 
				 * forwardJsp="efilenumber"; return
				 * mapping.findForward(forwardJsp); }
				 * 
				 * }
				 *//* Commented By Gulrej on 15th May, 2017 */
			}

			if (CommonConstant.ESTAMP_MODULE.equalsIgnoreCase(fromModule)) {

				eForm.setExemptionDTOList(new ArrayList());
				eForm.setInstrumentDTOList(new ArrayList());
				eForm.setDutyCalculationDTO(new DutyCalculationDTO());
				// dto.setFirstName("");
				// dto.setMidName("");
				// dto.setLastName("");
				// dto.setDob("");
				// dto.setDobDay("");
				// dto.setDobMonth("");
				// dto.setDobYear("");
				// dto.setSex("");
				eForm.setExempDTO(new ExemptionDTO());
				eForm.getExempDTO().setHdnExempAmount("");
				eForm.getExempDTO().setHdnExAmount("");
				dto.setDutyPaid(0.0);
				dto.setDeedId(0);
				dto.setBaseValue("0.0");// double to string
				eForm.setExemptionDTOList(new ArrayList());
				eForm.setInstrumentDTOList(new ArrayList());
				eForm.setDutyCalculationDTO(new DutyCalculationDTO());
				dto.setFirstName("");
				dto.setMidName("");
				dto.setLastName("");
				dto.setFromModule(CommonConstant.ESTAMP_MODULE);
				dto.setDob("");
				dto.setDobDay("");
				dto.setDobMonth("");
				dto.setDobYear("");
				dto.setSex("");
				dto.setDeedCategoryName("");
				dto.setDeedCategoryId("");
				eForm.setExempDTO(new ExemptionDTO());
				eForm.getExempDTO().setHdnExempAmount("");
				eForm.getExempDTO().setHdnExAmount("");
				dto.setDutyPaid(0.0);
				dto.setDeedId(0);
				dto.setBaseValue("0.0");// double to string
				eForm.setModuleName("");
				eForm.setDutyCalculationDTO(dto);
				eForm.setInstDTO(new InstrumentsDTO());
				dto.setActionName("");
				dto.setPropertyValuationRequired("");
				dto.setPropertyName("");
				dto.setMarketValue("");
				dto.setValuationIdValidate("");
				dto.setValuationId("");
				dto.setHvValId("");
				dto.setHvValIdFlag("");
				dto.setAddPropertyFlag("");
				dto.setPropertyDetailsList(new ArrayList());
				dto.setUserValueList(new ArrayList());
				dto.setExemptionDescpList(new ArrayList());
				dto.setExemptionList(new ArrayList());
				dto.setRegFeeExemptionList(new ArrayList());
				dto.setRegFeeExemptionDiscriptionList(new ArrayList());
				dto.setInstDescription("");
				dto.setDeedDescription("");
				dto.setMunicipalVisible("N");
				// eForm.setModuleName("");
				eForm.setDutyCalculationDTO(dto);
				eForm.setInstDTO(new InstrumentsDTO());
				dto.setActionName("");
				forwardJsp = CommonConstant.FORWARD_PAGE_HOME;

				eForm.setModuleName(CommonConstant.REGISTRATION_DUTY_PAGE);
				formName = "dutyHomePage";
				actionName = "nextPage";
			}

			logger.debug("actionName:-" + actionName + ":" + formName);
			logger.debug("forwardJsp:-" + forwardJsp);

			if (CommonConstant.DUTY_HOME_PAGE.equals(formName)) {
				if (CommonConstant.CANCEL_ACTION.equals(actionName)) {
					forwardJsp = "welcome";
				}
				if (CommonConstant.RESET_PAGE_ACTION.equals(actionName)) {
					eForm.setExemptionDTOList(new ArrayList());
					eForm.setInstrumentDTOList(new ArrayList());

					dto.setFirstName("");
					dto.setMidName("");
					dto.setLastName("");
					dto.setDob("");
					dto.setDobDay("");
					dto.setDobMonth("");
					dto.setDobYear("");
					dto.setSex("");
					eForm.setHdnAmount("");

					dto.setActionName(null);
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_HOME;

				}

				if (CommonConstant.DUTY_NEXT_ACTION.equals(actionName)) {
					eForm.setPhysicalStampFlag("NO"); // Added By Gulrej on 26th
														// July, 2017
					eForm.getDutyCalculationDTO().setPropertyoutMP("");
					eForm.setPropertyoutMPFlag("");
					eForm.setFlagdisplay("");
					eForm.setSlotdate("");
					// dto.setDeedId(dto.getDeedId());
					if (eForm.getModuleName() != null
							&& eForm.getModuleName().equalsIgnoreCase(
									CommonConstant.REGISTRATION_DUTY_PAGE)) {
						eForm.setDeedCategoryDTOList(bo
								.getDeedCategory(language));
						// eForm.setDutycalculationDTOList(bo.getDeed(
						// CommonConstant.NON_DUTY_REGISTRATION_DEED ));
						// added by lavi
						dto.setFromReg(1);
					} else {
						/* Added by Vinay */
						eForm.setDeedCategoryDTOList(bo
								.getDeedCategory(language));

						// eForm.setDutycalculationDTOList(bo.getDeed(
						// CommonConstant.NON_DUTY_DEED)); commented by vinay
					}

					if (request.getParameter("fromAdju") != null) {
						// RegCommonForm rForm= new RegCommonForm();
						if (request.getParameter("fromAdju").equalsIgnoreCase(
								"Y")) {

							eForm.setFromAdjudication(1);
							// session.setAttribute("fnName","Adjudication");
							//rForm.setFromAdjudication(eForm.getFromAdjudication
							// ());
						} else {
							eForm.setFromAdjudication(0);
							//rForm.setFromAdjudication(eForm.getFromAdjudication
							// ());
							// session.setAttribute("fnName","Initiation");
						}

					} else {
						eForm.setFromAdjudication(0);
					}

					if (locale.equalsIgnoreCase("hi_IN")) {
						dto.setSex("M".equals(dto.getSex()) ? "h.Male" : "F"
								.equals(dto.getSex()) ? "h.Female" : "");
					} else {
						dto.setSex("M".equals(dto.getSex()) ? "Male" : "F"
								.equals(dto.getSex()) ? "Female" : "");
					}

					dto.setActionName(null);
					actionName = "";
					eForm.setDutyCalculationDTO(dto);
					eForm.getDutyCalculationDTO().setRadioResiComm3("");
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;

				}

			}
			if (CommonConstant.DUTY_NEXT_PAGE.equals(formName)) {

				logger.debug("actionName:-" + actionName);
				if (CommonConstant.DUTY_PREV_ACTION.equals(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					dto.setFirstName(dto1.getFirstName());
					dto.setMidName(dto1.getMidName());
					dto.setLastName(dto1.getLastName());
					dto.setAge(dto1.getAge());
					if (locale.equalsIgnoreCase("hi_IN")) {
						dto.setSex("M".equals(dto.getSex()) ? "h.MAlE" : "F"
								.equals(dto.getSex()) ? "h.Female" : "");
					} else {
						dto.setSex("Male".equals(dto1.getSex()) ? "M"
								: "Female".equals(dto1.getSex()) ? "F" : "");
					}

					logger.debug("Name:-" + dto1.getFirstName());
					eForm.setDutyCalculationDTO(dto);
					dto.setActionName(null);
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_HOME;

				}
				logger.debug(" before change action actionName:-" + actionName);

				if (CommonConstant.DEED_CATEGORY_CHANGE_ACTION
						.equals(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					eForm.setDutycalculationDTOList(bo.getDeed(dto1
							.getDeedCategoryId(), dto1.getCancellationFlag(),
							language, dto1.getFromModule()));
					eForm.getDutyCalculationDTO().setBaseValue("0.0");// double
																		// to
																		// string
					eForm.getDutyCalculationDTO().setAnnualRent(0.0);
					eForm.getDutyCalculationDTO().setDutyPaid(0.0);
					eForm.getDutyCalculationDTO().setDeedType("");
					eForm.setInstDTO(new InstrumentsDTO());
					eForm.setExempDTO(new ExemptionDTO());
					eForm.setExemptionDTOList(new ArrayList());
					eForm.setInstrumentDTOList(new ArrayList());
					dto.setUserValueList(new ArrayList());
					dto.setDeedDescription("");
					dto.setInstDescription("");
					dto.setPropertyDetailsList(new ArrayList());
					dto.setMunicipalVisible("");
					if (!"Y".equalsIgnoreCase(dto.getCancellationFlag())) {
						dto.setExemptionList(new ArrayList());
						dto.setExemptionDescpList(new ArrayList());
						dto.setRegFeeExemptionList(new ArrayList());
						dto.setRegFeeExemptionDiscriptionList(new ArrayList());
					}
					dto.setPropertyValuationRequired("");
					dto.setPropertyName("");
					dto.setMarketValue("");
					dto.setValuationIdValidate("");
					dto.setValuationId("");
					dto.setHvValId("");
					dto.setHvValIdFlag("");
					eForm.getDutyCalculationDTO().setRadioResiComm3("");
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
					dto.setActionName(null);
					actionName = "";
				}

				if (CommonConstant.EXCHANGE_ADD_PROPERTY
						.equalsIgnoreCase(actionName)) {
					eForm.getDutyCalculationDTO().setRadioResiComm3("");
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
					dto.setRinPustikaCheck("");
					dto.setRinPustikaCheckFlag("");
					String exP = dto.getExchangeParty();
					boolean rinFlag = false;
					ArrayList propertyList = dto.getExchangeList1();
					for (int i = 0; i < propertyList.size(); i++) {
						DutyCalculationDTO dddto = (DutyCalculationDTO) propertyList
								.get(i);
						if ("3".equalsIgnoreCase(dddto.getPropertyId())) {
							rinFlag = true;
						}
					}
					if (rinFlag) {
						dto.setRinPustikaVisible1("Y");
					} else {
						dto.setRinPustikaVisible1("N");
					}
					boolean rinFlag1 = false;
					ArrayList propertyList1 = dto.getExchangeList2();
					for (int i = 0; i < propertyList1.size(); i++) {
						DutyCalculationDTO dddto = (DutyCalculationDTO) propertyList1
								.get(i);
						if ("3".equalsIgnoreCase(dddto.getPropertyId())) {
							rinFlag1 = true;
						}
					}
					if (rinFlag1) {
						dto.setRinPustikaVisible2("Y");
					} else {
						dto.setRinPustikaVisible2("N");
					}
					dto.setAddPropertyFlag("Y");
					dto.setActionName("");
				}
				if (CommonConstant.EXCHANGE_DELETE_PROPERTY1
						.equalsIgnoreCase(actionName)) {

					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList propertyDetails = dto1.getExchangeList1();
					ArrayList propertyDetailsList = new ArrayList();
					for (int i = 0; i < propertyDetails.size(); i++) {
						DutyCalculationDTO ddto = (DutyCalculationDTO) propertyDetails
								.get(i);
						if (dto1.getDeleteValuationId().equalsIgnoreCase(
								ddto.getValuationId())) {
							dto1
									.setExchangeMV1(dto1.getExchangeMV1()
											- Double.parseDouble(ddto
													.getMarketValue()));

						} else {
							propertyDetailsList.add(ddto);
						}
					}
					dto1.setExchangeList1(propertyDetailsList);
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					eForm.getDutyCalculationDTO().setRadioResiComm3("");
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;

				}
				if (CommonConstant.EXCHANGE_DELETE_PROPERTY2
						.equalsIgnoreCase(actionName)) {

					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList propertyDetails = dto1.getExchangeList2();
					ArrayList propertyDetailsList = new ArrayList();
					for (int i = 0; i < propertyDetails.size(); i++) {
						DutyCalculationDTO ddto = (DutyCalculationDTO) propertyDetails
								.get(i);
						if (dto1.getDeleteValuationId().equalsIgnoreCase(
								ddto.getValuationId())) {
							dto1
									.setExchangeMV2(dto1.getExchangeMV2()
											- Double.parseDouble(ddto
													.getMarketValue()));

						} else {
							propertyDetailsList.add(ddto);
						}
					}
					dto1.setExchangeList2(propertyDetailsList);
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					eForm.getDutyCalculationDTO().setRadioResiComm3("");
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;

				}

				if ("ShareClickAction".equalsIgnoreCase(actionName)) {

					/*
					 * String
					 * param[]=request.getParameterValues("userEnterableFieldValue"
					 * ); ArrayList
					 * list=eForm.getInstDTO().getUserEnterableList();
					 * if(list!=null) { int j=0; for(int i=0;i<list.size();i++)
					 * { InstrumentsDTO indto= (InstrumentsDTO) list.get(i);
					 * String id=indto.getUserEnterableId(); String value="";
					 * if(
					 * "Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag())&&
					 * "Y"
					 * .equalsIgnoreCase(indto.getAsConsiderationAmountFlag()))
					 * { value=eForm.getInstDTO().getCommonUserField(); } else
					 * if
					 * ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag())&&
					 * "N"
					 * .equalsIgnoreCase(indto.getAsConsiderationAmountFlag()))
					 * { value=eForm.getInstDTO().getInStampDutyAmount(); } else
					 * if
					 * ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag())&&
					 * "Y"
					 * .equalsIgnoreCase(indto.getAsConsiderationAmountFlag()))
					 * { value=eForm.getInstDTO().getAsConsiderationAmount(); }
					 * else { value=param[j]; j++; }
					 * indto.setUserEnterableFieldValue(value); } }
					 */
					if ("N".equalsIgnoreCase(dto.getHvValIdFlag())) {
						dto.setValuationIdValidate("false");
						dto.setValuationId("");
						dto.setValuationIdList(new ArrayList());
						dto.setMarketValue("");
						dto.setPropertyName("");
					} else {
						dto.setValuationIdValidate("");
						dto.setValuationId("");
					}
					eForm.getInstDTO().setAsConsiderationAmount("");
					eForm.getInstDTO().setInStampDutyAmount("");
					if ("Y".equalsIgnoreCase(dto.getHvShareFlag())) {
						eForm.getInstDTO().setUserEnterableFieldReq("Y");
						ArrayList enetrableList = bo.getUserEnterableField(
								String.valueOf(dto.getDeedId()),
								String.valueOf(eForm.getInstDTO().getInstId()),
								language);
						eForm.getInstDTO().setUserEnterableList(enetrableList);
					} else {
						eForm.getInstDTO().setUserEnterableFieldReq("N");
						eForm.getInstDTO().setUserEnterableList(null);
					}
					eForm.getDutyCalculationDTO().setRadioResiComm3("");
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;

				}
				if (CommonConstant.NO_ID_VALIDATE.equalsIgnoreCase(actionName)) {
					eForm.getDutyCalculationDTO().setPropertyoutMP("");
					eForm.setPropertyoutMPFlag("");
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
					return mapping.findForward(forwardJsp);
				}

				if (CommonConstant.ID_VALIDATE.equalsIgnoreCase(actionName)) {
					String param[] = request
							.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "N".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getAsConsiderationAmount();
							} else {
								value = param[j];
								j++;
							}
							indto.setUserEnterableFieldValue(value);
						}
					}
					if ("N".equalsIgnoreCase(dto.getHvValIdFlag())) {
						dto.setValuationIdValidate("false");
						dto.setValuationId("");
						dto.setValuationIdList(new ArrayList());
						dto.setMarketValue("");
						dto.setPropertyName("");
					} else {
						dto.setValuationIdValidate("");
						dto.setValuationId("");
					}
					// eForm.getDutyCalculationDTO().setRadioResiComm3("");
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if (CommonConstant.VALUATION_ID_VALIDATE
						.equalsIgnoreCase(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					// HashMap<String,String> userValueMap=new
					// HashMap<String,String>();
					ArrayList userList = new ArrayList();
					String param[] = request
							.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "N".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getAsConsiderationAmount();
							} else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidRegFeeFlag())) {
								dto1.setAlreadyPaidRegFee(value);
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidStampDutyFlag())) {
								dto1.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue("");
							userList.add(userDto);
						}
						// dto1.setUserValueMap(userValueMap);
						dto1.setUserValueList(userList);
					}
					String validate = bo.validateValuationId(dto1, eForm
							.getInstDTO(), language);// method alterd by roopam
														// 14april15
					if ("A".equalsIgnoreCase(validate)) {
						dto1.setValuationIdValidate("true");
						// eForm.getInstDTO().setCommonUserField("");
						// eForm.getInstDTO().setAsConsiderationAmount("");
						// eForm.getInstDTO().setInStampDutyAmount("");
						dto1.setAddPropertyFlag("N");
						dto1.setHvValIdFlag("N");
						dto1.setHvValId("");
						if (list != null) {
							int j = 0;
							for (int i = 0; i < list.size(); i++) {
								InstrumentsDTO indto = (InstrumentsDTO) list
										.get(i);
								String id = indto.getUserEnterableId();
								String value = "";
								if ("Y".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "Y"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getCommonUserField();
								} else if ("Y".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "N"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getInStampDutyAmount();
								} else if ("N".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "Y"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getAsConsiderationAmount();
								} else {
									value = param[j];
									j++;
								}
								indto.setUserEnterableFieldValue(value);
							}
						}

					} else if ("NA".equalsIgnoreCase(validate)) {
						dto1.setValuationIdValidate("false");
						dto1.setAddPropertyFlag("Y");
						if (list != null) {
							int j = 0;
							for (int i = 0; i < list.size(); i++) {
								InstrumentsDTO indto = (InstrumentsDTO) list
										.get(i);
								String id = indto.getUserEnterableId();
								String value = "";
								if ("Y".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "Y"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getCommonUserField();
								} else if ("Y".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "N"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getInStampDutyAmount();
								} else if ("N".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "Y"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getAsConsiderationAmount();
								} else {
									value = param[j];
									j++;
								}
								indto.setUserEnterableFieldValue(value);
							}
						}

					} else if ("Agri".equalsIgnoreCase(validate)) {

						dto1.setValuationIdValidate("AGRI");
						dto1.setAddPropertyFlag("Y");

						if (list != null) {
							int j = 0;
							for (int i = 0; i < list.size(); i++) {
								InstrumentsDTO indto = (InstrumentsDTO) list
										.get(i);
								String id = indto.getUserEnterableId();
								String value = "";
								if ("Y".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "Y"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getCommonUserField();
								} else if ("Y".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "N"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getInStampDutyAmount();
								} else if ("N".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "Y"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getAsConsiderationAmount();
								} else {
									value = param[j];
									j++;
								}
								indto.setUserEnterableFieldValue(value);
							}
						}

					} else if ("SUB".equalsIgnoreCase(validate)) {

						dto1.setValuationIdValidate("SUB");
						dto1.setAddPropertyFlag("Y");

						if (list != null) {
							int j = 0;
							for (int i = 0; i < list.size(); i++) {
								InstrumentsDTO indto = (InstrumentsDTO) list
										.get(i);
								String id = indto.getUserEnterableId();
								String value = "";
								if ("Y".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "Y"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getCommonUserField();
								} else if ("Y".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "N"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getInStampDutyAmount();
								} else if ("N".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "Y"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getAsConsiderationAmount();
								} else {
									value = param[j];
									j++;
								}
								indto.setUserEnterableFieldValue(value);
							}
						}

					} else if ("buyer".equalsIgnoreCase(validate)) {

						dto1.setValuationIdValidate("BUYER");
						dto1.setAddPropertyFlag("Y");

						if (list != null) {
							int j = 0;
							for (int i = 0; i < list.size(); i++) {
								InstrumentsDTO indto = (InstrumentsDTO) list
										.get(i);
								String id = indto.getUserEnterableId();
								String value = "";
								if ("Y".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "Y"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getCommonUserField();
								} else if ("Y".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "N"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getInStampDutyAmount();
								} else if ("N".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "Y"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getAsConsiderationAmount();
								} else {
									value = param[j];
									j++;
								}
								indto.setUserEnterableFieldValue(value);
							}
						}

					} else if ("leaseun".equalsIgnoreCase(validate)) {

						dto1.setValuationIdValidate("lease");
						dto1.setAddPropertyFlag("Y");

						if (list != null) {
							int j = 0;
							for (int i = 0; i < list.size(); i++) {
								InstrumentsDTO indto = (InstrumentsDTO) list
										.get(i);
								String id = indto.getUserEnterableId();
								String value = "";
								if ("Y".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "Y"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getCommonUserField();
								} else if ("Y".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "N"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getInStampDutyAmount();
								} else if ("N".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "Y"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getAsConsiderationAmount();
								} else {
									value = param[j];
									j++;
								}
								indto.setUserEnterableFieldValue(value);
							}
						}

					} else {
						dto1.setValuationIdValidate("DUP");
						dto1.setAddPropertyFlag("Y");

						if (list != null) {
							int j = 0;
							for (int i = 0; i < list.size(); i++) {
								InstrumentsDTO indto = (InstrumentsDTO) list
										.get(i);
								String id = indto.getUserEnterableId();
								String value = "";
								if ("Y".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "Y"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getCommonUserField();
								} else if ("Y".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "N"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getInStampDutyAmount();
								} else if ("N".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "Y"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getAsConsiderationAmount();
								} else {
									value = param[j];
									j++;
								}
								indto.setUserEnterableFieldValue(value);
							}
						}
					}
					dto1.setActionName(null);
					if (dto1.getPropertyDetailsList().size() > 0) {
						InstrumentsDTO idto = eForm.getInstDTO();
						idto.setLeaseFreeze("Y");
					}
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					eForm.getDutyCalculationDTO().setRadioResiComm3("");
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;

				}
				if (CommonConstant.ADD_PROPERTY.equalsIgnoreCase(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					dto1.setRinPustikaCheck("");
					dto1.setRinPustikaCheckFlag("");
					dto1.setAddPropertyFlag("Y");
					// changes by preeti on 23 september 2016
					eForm.getDutyCalculationDTO().setRadioResiComm3(
							dto1.getRadioResiComm3());
					eForm.getDutyCalculationDTO().setPropertyoutMP(
							dto1.getPropertyoutMP());
					if (dto1.getPropertyoutMP() != null
							&& !dto1.getPropertyoutMP().isEmpty()) {
						eForm.setPropertyoutMPFlag("Y");
					} else {
						eForm.setPropertyoutMPFlag("");
					}
					boolean rinFlag = false;
					ArrayList propertyList = dto1.getPropertyDetailsList();

					for (int i = 0; i < propertyList.size(); i++) {
						DutyCalculationDTO dddto = (DutyCalculationDTO) propertyList
								.get(i);
						if ("3".equalsIgnoreCase(dddto.getPropertyId())) {
							rinFlag = true;
						}
					}
					if (rinFlag) {
						dto1.setRinPustikaVisible("Y");
					} else {
						dto1.setRinPustikaVisible("N");
					}
					eForm.getInstDTO().setCommonUserField("");
					eForm.getInstDTO().setAsConsiderationAmount("");
					eForm.getInstDTO().setInStampDutyAmount("");
					eForm.getDutyCalculationDTO().setGovValue("");
					ArrayList userList = new ArrayList();
					String param[] = request
							.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "N".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getAsConsiderationAmount();
							} else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidRegFeeFlag())) {
								dto1.setAlreadyPaidRegFee(value);
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidStampDutyFlag())) {
								dto1.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue("");
							userList.add(userDto);
						}
						// dto1.setUserValueMap(userValueMap);
						dto1.setUserValueList(userList);
					}
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					// eForm.getDutyCalculationDTO().setRadioResiComm3("");
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;

				}
				if (CommonConstant.ADD_LAND_REVENUE
						.equalsIgnoreCase(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					if (dto1.getAvgLandRevenue() == null
							|| dto1.getAvgLandRevenue().equalsIgnoreCase("")) {
						if ("en".equalsIgnoreCase(language)) {
							request.setAttribute("ERROR",
									"Enter average land revenue");
						} else {
							request.setAttribute("ERROR",
									"Enter average land revenue");

						}
						actionName = "";
						eForm.getDutyCalculationDTO().setRadioResiComm3("");
						forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
					} else {
						int id = dto1.getLandRevenueId() + 1;
						dto1.setLandRevenueId(id);
						DutyCalculationDTO dto2 = new DutyCalculationDTO();
						dto2.setAvgLandRevenue(dto1.getAvgLandRevenue());
						dto2.setLandRevenueIdString(String.valueOf(id));
						dto1.getLandRevenueList().add(dto2);
						dto1.setAvgLandRevenue("");
						dto1.setTotalLandRevenue(dto1.getTotalLandRevenue()
								+ Double.parseDouble(dto2.getAvgLandRevenue()));
						if (Double.parseDouble(dto2.getAvgLandRevenue()) >= dto1
								.getMaxLandRevenue()) {
							dto1.setMaxLandRevenue(Double.parseDouble(dto2
									.getAvgLandRevenue()));
						}
						eForm.setDutyCalculationDTO(dto1);
						actionName = "";
						eForm.getDutyCalculationDTO().setRadioResiComm3("");
						forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
					}
				}
				if (CommonConstant.DELETE_LAND_REVENUE
						.equalsIgnoreCase(actionName)) {

					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList propertyDetails = dto1.getLandRevenueList();
					ArrayList propertyDetailsList = new ArrayList();
					dto1.setMaxLandRevenue(0);
					for (int i = 0; i < propertyDetails.size(); i++) {
						DutyCalculationDTO ddto = (DutyCalculationDTO) propertyDetails
								.get(i);
						if (dto1.getDeleteValuationId().equalsIgnoreCase(
								String.valueOf(ddto.getLandRevenueIdString()))) {
							dto1.setTotalLandRevenue(dto1.getTotalLandRevenue()
									- Double.parseDouble(ddto
											.getAvgLandRevenue()));
						} else {
							if (Double.parseDouble(ddto.getAvgLandRevenue()) >= dto1
									.getMaxLandRevenue()) {
								dto1.setMaxLandRevenue(Double.parseDouble(ddto
										.getAvgLandRevenue()));
							}
							propertyDetailsList.add(ddto);

						}
					}
					if (propertyDetailsList.size() == 0) {
						dto1.setAddPropertyFlag("Y");
					}
					dto1.setLandRevenueList(propertyDetailsList);
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					eForm.getDutyCalculationDTO().setRadioResiComm3("");
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;

				}
				if (CommonConstant.DELETE_PROPERTY.equalsIgnoreCase(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList propertyDetails = dto1.getPropertyDetailsList();
					ArrayList propertyDetailsList = new ArrayList();
					for (int i = 0; i < propertyDetails.size(); i++) {
						DutyCalculationDTO ddto = (DutyCalculationDTO) propertyDetails
								.get(i);
						if (dto1.getDeleteValuationId().equalsIgnoreCase(
								ddto.getValuationId())) {
							if (dto1.getDeedId() != 1058) {
								dto1.setTotalStampDuty(dto1.getTotalStampDuty()
										- ddto.getStampDuty());
								dto1.setTotalregFee(dto1.getTotalregFee()
										- ddto.getRegFee());
								dto1.setTotalUpkar(dto1.getTotalUpkar()
										- ddto.getUpkarDuty());
								dto1.setTotalNagarPalika(dto1
										.getTotalNagarPalika()
										- ddto.getNagarPalikaDuty());
								dto1.setTotalPanchyat(dto1.getTotalPanchyat()
										- ddto.getPanchayatDuty());
								dto1.setTotalPaidStamp(dto1.getTotalPaidStamp()
										- ddto.getPaidStamp());
								dto1.setTotalPaidReg(dto1.getTotalPaidReg()
										- ddto.getPaidReg());
								dto1.setEntireTotal(dto1.getEntireTotal()
										- ddto.getTotal());
								dto1.setTotalPayableReg(dto1
										.getTotalPayableReg()
										- ddto.getPayableRegFee());
								dto1.setTotalPayableStamp(dto1
										.getTotalPayableStamp()
										- ddto.getPayableStampDuty());
								double govtValue = 0;
								if (ddto.getGovValue() != null
										&& !ddto.getGovValue()
												.equalsIgnoreCase("")) {
									govtValue = Double.parseDouble(ddto
											.getGovValue());
								}
								dto1.setTotalGovValue(dto1.getTotalGovValue()
										- govtValue);
							}
						} else {
							propertyDetailsList.add(ddto);
						}
					}
					if (propertyDetailsList.size() == 0) {
						dto1.setAddPropertyFlag("Y");
						InstrumentsDTO idto = eForm.getInstDTO();
						idto.setLeaseFreeze("N");
					}
					dto1.setPropertyDetailsList(propertyDetailsList);
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					eForm.getDutyCalculationDTO().setRadioResiComm3("");
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;

				}
				if (CommonConstant.DIFF_RIN_PUSTIKA.equals(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					if ("Y".equalsIgnoreCase(dto1.getRinPustikaCheckFlag())) {
						dto1.setRinPustikaCheck("Y");

					} else {
						dto1.setRinPustikaCheck("N");

					}
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					eForm.getDutyCalculationDTO().setRadioResiComm3("");
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if (CommonConstant.MUNICIPAL_CHECK.equals(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList userList = new ArrayList();
					String param[] = request
							.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "N".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getAsConsiderationAmount();
							} else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidRegFeeFlag())) {
								dto1.setAlreadyPaidRegFee(value);
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidStampDutyFlag())) {
								dto1.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue(value);
							userList.add(userDto);
						}
						// dto1.setUserValueMap(userValueMap);
						dto1.setUserValueList(userList);
					}
					if ("Y".equalsIgnoreCase(dto1.getMunicipalCheckFlag())) {
						dto1.setMunicipalCheck("Y");

					} else {
						dto1.setMunicipalCheck("N");
					}
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					eForm.getDutyCalculationDTO().setRadioResiComm3("");
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if ("familyAction".equals(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList userList = new ArrayList();
					String param[] = request
							.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "N".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getAsConsiderationAmount();
							} else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidRegFeeFlag())) {
								dto1.setAlreadyPaidRegFee(value);
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidStampDutyFlag())) {
								dto1.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue(value);
							userList.add(userDto);
						}
						// dto1.setUserValueMap(userValueMap);
						dto1.setUserValueList(userList);
					}
					if ("Y".equalsIgnoreCase(dto1.getFamilyCheckFlag())) {
						dto1.setFamilyCheck("Y");

					} else {
						dto1.setFamilyCheck("N");
					}
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					eForm.getDutyCalculationDTO().setRadioResiComm3("");
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if ("govAction".equals(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList userList = new ArrayList();
					String param[] = request
							.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "N".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getAsConsiderationAmount();
							} else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidRegFeeFlag())) {
								dto1.setAlreadyPaidRegFee(value);
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidStampDutyFlag())) {
								dto1.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue(value);
							userList.add(userDto);
						}
						// dto1.setUserValueMap(userValueMap);
						dto1.setUserValueList(userList);
					}
					if ("Y".equalsIgnoreCase(dto1.getGovCheckFlag())) {
						dto1.setGovCheck("Y");

					} else {
						dto1.setGovCheck("N");
					}
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					eForm.getDutyCalculationDTO().setRadioResiComm3("");
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}

				if ("AddExemption".equalsIgnoreCase(actionName)) {
					String availExemptionFlag = "Y";
					eForm.setAvailExemptionFlagEfile(availExemptionFlag);
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList userList = new ArrayList();
					String param[] = request
							.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "N".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getAsConsiderationAmount();
							} else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidRegFeeFlag())) {
								dto1.setAlreadyPaidRegFee(value);
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidStampDutyFlag())) {
								dto1.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue(value);
							userList.add(userDto);
						}
						// dto1.setUserValueMap(userValueMap);
						dto1.setUserValueList(userList);
					}
					eForm.setDutyCalculationDTO(dto1);
					ArrayList exempList = dto1.getExemptionList();
					for (int i = 0; i < exempList.size(); i++) {
						DutyCalculationDTO dto2 = (DutyCalculationDTO) exempList
								.get(i);
						if (dto1.getSelectedExempId().equalsIgnoreCase(
								dto2.getExempId())) {
							dto2.setExempCheckBox(dto2.getExempId());
							dto1.getExemptionDescpList().add(dto2);
						}
					}
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					eForm.getDutyCalculationDTO().setRadioResiComm3("");
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;

				}
				if ("deleteExempetion".equalsIgnoreCase(actionName)) {
					String availExemptionFlag = "";
					eForm.setAvailExemptionFlagEfile(availExemptionFlag);
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList userList = new ArrayList();
					String param[] = request
							.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "N".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getAsConsiderationAmount();
							} else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidRegFeeFlag())) {
								dto1.setAlreadyPaidRegFee(value);
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidStampDutyFlag())) {
								dto1.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue(value);
							userList.add(userDto);
						}
						// dto1.setUserValueMap(userValueMap);
						dto1.setUserValueList(userList);
					}
					ArrayList exempList = dto1.getExemptionList();
					ArrayList newList = new ArrayList();
					for (int i = 0; i < exempList.size(); i++) {
						DutyCalculationDTO dto2 = (DutyCalculationDTO) exempList
								.get(i);
						if (dto1.getSelectedExempId().equalsIgnoreCase(
								dto2.getExempId())) {
							dto2.setExempCheckBox("");
						}
					}
					ArrayList exempDispList = dto1.getExemptionDescpList();
					for (int i = 0; i < exempDispList.size(); i++) {
						DutyCalculationDTO dto2 = (DutyCalculationDTO) exempDispList
								.get(i);
						if (dto1.getSelectedExempId().equalsIgnoreCase(
								dto2.getExempId())) {

						} else {
							newList.add(dto2);
						}
					}
					dto1.setExemptionDescpList(newList);
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					eForm.getDutyCalculationDTO().setRadioResiComm3("");
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if ("AddRegExemption".equalsIgnoreCase(actionName)) {
					String exemptionFlag = "Y";
					eForm.setExemptionFlagEfile(exemptionFlag);
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList userList = new ArrayList();
					String param[] = request
							.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "N".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getAsConsiderationAmount();
							} else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidRegFeeFlag())) {
								dto1.setAlreadyPaidRegFee(value);
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidStampDutyFlag())) {
								dto1.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue(value);
							userList.add(userDto);
						}
						// dto1.setUserValueMap(userValueMap);
						dto1.setUserValueList(userList);
					}
					eForm.setDutyCalculationDTO(dto1);
					ArrayList exempList = dto1.getRegFeeExemptionList();
					for (int i = 0; i < exempList.size(); i++) {
						DutyCalculationDTO dto2 = (DutyCalculationDTO) exempList
								.get(i);
						if (dto1.getSelectedExempId().equalsIgnoreCase(
								dto2.getExempId())) {
							dto2.setExempCheckBox(dto2.getExempId());
							dto1.getRegFeeExemptionDiscriptionList().add(dto2);
						}
					}
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					eForm.getDutyCalculationDTO().setRadioResiComm3("");
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;

				}
				if ("deleteRegExempetion".equalsIgnoreCase(actionName)) {
					String exemptionFlag = "";
					eForm.setExemptionFlagEfile(exemptionFlag);
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList userList = new ArrayList();
					String param[] = request
							.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "N".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getAsConsiderationAmount();
							} else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidRegFeeFlag())) {
								dto1.setAlreadyPaidRegFee(value);
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidStampDutyFlag())) {
								dto1.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue(value);
							userList.add(userDto);
						}
						// dto1.setUserValueMap(userValueMap);
						dto1.setUserValueList(userList);
					}
					ArrayList exempList = dto1.getRegFeeExemptionList();
					ArrayList newList = new ArrayList();
					for (int i = 0; i < exempList.size(); i++) {
						DutyCalculationDTO dto2 = (DutyCalculationDTO) exempList
								.get(i);
						if (dto1.getSelectedExempId().equalsIgnoreCase(
								dto2.getExempId())) {
							dto2.setExempCheckBox("");
						}
					}
					ArrayList exempDispList = dto1
							.getRegFeeExemptionDiscriptionList();
					for (int i = 0; i < exempDispList.size(); i++) {
						DutyCalculationDTO dto2 = (DutyCalculationDTO) exempDispList
								.get(i);
						if (dto1.getSelectedExempId().equalsIgnoreCase(
								dto2.getExempId())) {

						} else {
							newList.add(dto2);
						}
					}
					dto1.setRegFeeExemptionDiscriptionList(newList);
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					eForm.getDutyCalculationDTO().setRadioResiComm3("");
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if (CommonConstant.DEED_CHANGE_ACTION.equals(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					int deedId = dto1.getDeedId();
					eForm.setHdnAmount("");
					eForm.getDutyCalculationDTO().setBaseValue("0.0");// double
																		// to
																		// string
					eForm.getDutyCalculationDTO().setAnnualRent(0.0);
					eForm.getDutyCalculationDTO().setGovCheck("");
					eForm.getDutyCalculationDTO().setGovValue("");
					eForm.getDutyCalculationDTO().setGovCheckFlag("");
					eForm.getDutyCalculationDTO().setDutyPaid(0.0);
					eForm.setInstDTO(new InstrumentsDTO());
					eForm.setExempDTO(new ExemptionDTO());
					if (!"Y".equalsIgnoreCase(dto1.getCancellationFlag())) {
						dto.setExemptionList(new ArrayList());
						dto.setExemptionDescpList(new ArrayList());
						dto.setRegFeeExemptionList(new ArrayList());
						dto.setRegFeeExemptionDiscriptionList(new ArrayList());
					}
					ArrayList instList = bo.getInstrument(deedId, locale, dto1
							.getCancellationFlag(), dto1.getFromModule());
					dto.setValuationIdValidate("");
					/*
					 * ArrayList<ExemptionDTO> exemDTOList
					 * =bo.getExemptions(dto.getDeedId());
					 */eForm.setExemptionDTOList(new ArrayList());
					dto.setDeedDescription(bo.getDeedDiscription(deedId,
							language));
					dto.setExemptionDescpList(new ArrayList());
					dto.setInstDescription("");

					if (deedId == 1054) {
						dto.setValuationIdValidate("true");
						dto.setExchangeMV1(0);
						dto.setExchangeMV2(0);
						dto.setExchangeList1(new ArrayList());
						dto.setExchangeList2(new ArrayList());

					}

					eForm.setInstrumentDTOList(instList);
					eForm.getDutyCalculationDTO().setRadioResiComm3("");
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
					dto.setActionName(null);
					actionName = "";
				}

				// upload pdf in san

				if (CommonConstant.UPLOAD_FILE_ANGLE_1.equals(actionName)) {
					System.out.println("inside upload method of action ");

					String efileTxnId = (String) session
							.getAttribute("regTxnId");
					String filePath;
					String path = "";

					FormFile uploadedFile = eForm.getPropImage1();
					// to check file size
					int size = uploadedFile.getFileSize();
					double fileSizeInBytes = size;
					// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
					double fileSizeInKB = fileSizeInBytes / 1024.0;
					if (fileSizeInKB > CommonConstant.MAX_FILE_SIZE) {
						request
								.setAttribute(
										CommonConstant.lbl_reg_init_upload_msg3,
										"File size is Greater than 500 KB. Please select another file. / फ़ाइल का माप 500 KB से अधिक है। अन्य फाइल चुनें।");
						eForm.setFileSizeUploadError("Y");
						forwardJsp = "uploadSuccess";
						return mapping.findForward(forwardJsp);
					}
					// check for file extension
					MIMECheck mimeCheck = new MIMECheck();
					String fileExt = mimeCheck.getFileExtension(uploadedFile
							.getFileName());
					Boolean mime = mimeCheck.validateFileTypeEfile(fileExt);

					// end of file extension

					if (mime) {
						PropertiesFileReader pr = PropertiesFileReader
								.getInstance("resources.igrs");
						path = pr.getValue("upload.location");
						filePath = path + CommonConstant.FILE_UPLOAD_PATH
								+ efileTxnId;
						File folder = new File(filePath);
						// String fileName=uploadedFile.getFileName();
						String fileName = CommonConstant.FILE_UPLOAD_INITATE;
						eForm.setPropImage1DocumentName(fileName);
						eForm.setEmptyFile("Y");
						boolean pathStatus = false;
						pathStatus = bo.getuploadformpath(efileTxnId, filePath);
						byte[] content = uploadedFile.getFileData();
						if (!folder.exists()) {
							folder.mkdirs();
						}

						try {
							File newFile = new File(filePath, fileName);
							FileOutputStream fos = new FileOutputStream(newFile);
							fos.write(content);
							fos.close();

							boolean uploadStatus = true;

							if (uploadStatus == true) {
								if (language.equalsIgnoreCase("en"))
									request.setAttribute(
											CommonConstant.SUCCESS_MSG_UPLOAD,
											"Successfully Uploaded.");
								else
									request.setAttribute(
											CommonConstant.SUCCESS_MSG_UPLOAD,
											"सफलतापूर्वक अपलोड की गई");
								forwardJsp = "uploadSuccess";
							}

							else {
								// session.setAttribute("checkStatus",
								// Constants.PROBLEM_IN_AS);
								if (language.equalsIgnoreCase("en"))
									request.setAttribute(
											CommonConstant.FAILURE_MSG_UPLOAD,
											"Not Uploaded Successfully");
								else
									request.setAttribute(
											CommonConstant.FAILURE_MSG_UPLOAD,
											"सफलतापूर्वक अपलोड नहीं किया गया");
								forwardJsp = "uploadSuccess";
							}

							return mapping.findForward(forwardJsp);

						} catch (Exception ex) {

							ex.printStackTrace();
						}
					} else {
						eForm.setUploadFileError("Y");
						if (language.equalsIgnoreCase("en"))
							request
									.setAttribute(
											CommonConstant.ERRORFILETYPE_MSG_UPLOAD,
											"Please Upload a Valid File.File should be in PDF Format.");
						else
							request
									.setAttribute(
											CommonConstant.ERRORFILETYPE_MSG_UPLOAD,
											"कृपया एक मान्य फाइल अपलोड करें। फाइल पीडीएफ प्रारूप में होनी चाहिए।");
						forwardJsp = "uploadSuccess";
						return mapping.findForward(forwardJsp);
					}
				}

				if (CommonConstant.UPLOAD_FILE_ANGLE_2.equals(actionName)) {

					/* Added by Gulrej // For addition of physical estamps */

					// eForm.setActionName(CommonConstant.NO_ACTION);
					// eForm.setActionName("");
					// actionName="";
					String maindutyId = (String) session
							.getAttribute("dutyId1");
					String userid = (String) session.getAttribute("UserId");
					String deedId = bo.getdeedId(maindutyId);
					String instId = bo.getInstId(maindutyId);
					//added by saurav
					String payableDuty = bo.getPayableDuty(maindutyId);
					String regTxnIdCheck = bo.getRegTxnId(maindutyId);
					String physicalStampArrAdded[] = request
							.getParameterValues("physicalStampArr");
					if (physicalStampArrAdded != null
							&& physicalStampArrAdded[0].trim() != "") {
						logger.debug("Addition of physical estamps started ::"
								+ physicalStampArrAdded.length);

						boolean statusupdate = false;

						boolean status = false;

						// below code for temporary efiling
						Calendar currentDate = Calendar.getInstance();
						SimpleDateFormat formatter = new SimpleDateFormat(
								"ddMMyyyy");
						String dateNow = formatter
								.format(currentDate.getTime());
						String regTxnIdSeq="";
						String regTxnId = null;
						if(null!=regTxnIdCheck && "".equals(regTxnIdCheck)){
							regTxnIdSeq = bo.getRegInitTxnIdSeqEfile();
						System.out.println("temp rehtxnid" + regTxnIdSeq);
						regTxnId = dateNow + regTxnIdSeq;
						System.out.println("number" + regTxnId);
						}else{
							regTxnId=regTxnIdCheck;
						}

						eForm.setHidnRegTxnId(regTxnId);
						session.setAttribute("regTxnId", regTxnId);

						// end of temporary efiling

						// for( int i = 0; i < physicalStampArr.length; i++)
						// {
						//System.out.print("physicalStamp   :: "+physicalStamp);
						// physicalStamp = physicalStampArr[i];
						String[] physicalStampArr = physicalStampArrAdded[0]
								.split(",");
						ArrayList<DutyCalculationDTO> dto1 = new ArrayList();
						String physicalSTampCodeValue="0";
						for (int j = 0; j < physicalStampArr.length; j++) {
							String physicalStamp = physicalStampArr[j];
							String[] physicalStamp1 = physicalStamp.split("!");
							String seriesNumber = physicalStamp1[1];
							String stampvendor = physicalStamp1[0];
							String codeOfStamps = physicalStamp1[2];
							String stampdate = physicalStamp1[3];
							status = bo.getphysicalstamp(seriesNumber,
									stampvendor, codeOfStamps, stampdate,
									deedId, instId, maindutyId, regTxnId);
							physicalSTampCodeValue=String.valueOf(Integer.parseInt(physicalSTampCodeValue) + Integer.parseInt(codeOfStamps));
							// below code is used to display details in grid
							// form on jsp

							DutyCalculationDTO dutydto = new DutyCalculationDTO();
							dutydto.setDisplayStampVendorName(stampvendor);
							dutydto.setDisplayStampVendorSeries(seriesNumber);
							dutydto.setDisplayCodeStamp(codeOfStamps);
							dutydto.setDisplayDateStamp(stampdate);
							dto1.add(dutydto);
							eForm.setPhysicalStampList(dto1);

						}
						if(physicalSTampCodeValue.equals(payableDuty)){
							eForm.setPaymentFlag("Y");
						}
						// }

						String statuspage01 = "51";

						statusupdate = bo.updateStatus(regTxnId, statuspage01,
								maindutyId, deedId, instId, userid);
						eForm.setPhysicalStampFlag("YES");
						eForm.getDutyCalculationDTO().setRadioResiComm("no");
					}
					System.out.println("inside upload method 2 of action ");

					String efileTxnId = (String) session
							.getAttribute("regTxnId");
					String filePath;
					String path = "";

					FormFile uploadedFile = eForm.getPropImage2();
					// to check file size
					int size = uploadedFile.getFileSize();
					double fileSizeInBytes = size;
					// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
					double fileSizeInKB = fileSizeInBytes / 1024.0;
					if (fileSizeInKB > CommonConstant.MAX_FILE_SIZE) {
						request
								.setAttribute(
										CommonConstant.lbl_reg_init_upload_msg3,
										"File size is Greater than 500 KB. Please select another file. / फ़ाइल का माप 500 KB से अधिक है। अन्य फाइल चुनें।");
						eForm.setFileSizeError("Y");
						eForm.setFlagdisplay("Y");
						forwardJsp = "uploadSuccessEFile";
						return mapping.findForward(forwardJsp);
					}

					// check for file extension
					MIMECheck mimeCheck = new MIMECheck();
					String fileExt = mimeCheck.getFileExtension(uploadedFile
							.getFileName());
					Boolean mime = mimeCheck.validateFileTypeEfile(fileExt);

					// end of file extension

					if (mime) {
						eForm.setPropImage2DocumentName(uploadedFile
								.getFileName());
						PropertiesFileReader pr = PropertiesFileReader
								.getInstance("resources.igrs");
						path = pr.getValue("upload.location");
						filePath = path + CommonConstant.FILE_UPLOAD_PATH
								+ efileTxnId;

						File folder = new File(filePath);
						// String fileName=uploadedFile.getFileName();
						String fileName = CommonConstant.FILE_UPLOAD_PAYMENT_PAGE;
						// below code to save upload path
						// String maindutyId=(String)
						// session.getAttribute("dutyId1");
						boolean pathStatus = false;
						boolean checkduty = bo.getcheckduty(maindutyId);
						if (checkduty) {
							pathStatus = bo.getuploadpath(maindutyId, filePath);
						} else {
							pathStatus = bo
									.getuploadpath1(maindutyId, filePath);
						}
						// end of code
						byte[] content = uploadedFile.getFileData();
						if (!folder.exists()) {
							folder.mkdirs();
						}

						try {
							File newFile = new File(filePath, fileName);
							FileOutputStream fos = new FileOutputStream(newFile);
							fos.write(content);
							fos.close();

							boolean uploadStatus = true;

							if (uploadStatus == true) {
								if (language.equalsIgnoreCase("en"))
									request.setAttribute(
											CommonConstant.SUCCESS_MSG_UPLOAD,
											"Successfully Uploaded.");
								else
									request.setAttribute(
											CommonConstant.SUCCESS_MSG_UPLOAD,
											"सफलतापूर्वक अपलोड की गई");
								eForm.setFlagdisplay("Y");
								forwardJsp = "uploadSuccessEFile";
							}

							else {
								// session.setAttribute("checkStatus",
								// Constants.PROBLEM_IN_AS);
								if (language.equalsIgnoreCase("en"))
									request.setAttribute(
											CommonConstant.FAILURE_MSG_UPLOAD,
											"Not Uploaded Successfully");
								else
									request.setAttribute(
											CommonConstant.FAILURE_MSG_UPLOAD,
											"सफलतापूर्वक अपलोड नहीं किया गया");
								forwardJsp = "uploadSuccessEFile";
							}

							eForm.setFlagdisplay("Y");
							return mapping.findForward(forwardJsp);

						} catch (Exception ex) {

							ex.printStackTrace();
						}
					} else {
						eForm.setFileError("Y");
						if (language.equalsIgnoreCase("en"))
							request
									.setAttribute(
											CommonConstant.ERRORFILETYPE_MSG_UPLOAD,
											"Please Upload a Valid File.File should be in PDF Format.");
						else
							request
									.setAttribute(
											CommonConstant.ERRORFILETYPE_MSG_UPLOAD,
											"कृपया एक मान्य फाइल अपलोड करें। फाइल पीडीएफ प्रारूप में होनी चाहिए।");
						forwardJsp = "uploadSuccessEFile";
						eForm.setFlagdisplay("Y");
						return mapping.findForward(forwardJsp);

					}
				}

				if (CommonConstant.UPLOAD_FILE_ANGLE_3.equals(actionName)) {
					System.out.println("inside upload method 2 of action ");
					String efileTxnId = "";
					if ((String) session.getAttribute("regTxnId") != null) {
						efileTxnId = (String) session.getAttribute("regTxnId");
					} else {
						efileTxnId = eForm.getDutyCalculationDTO()
								.getHidnRegTxnId();
						efileTxnId = eForm.getHidnRegTxnId();
					}
					String filePath;
					String path = "";

					FormFile uploadedFile = eForm.getPropImage2();
					// to check file size
					int size = uploadedFile.getFileSize();
					double fileSizeInBytes = size;
					// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
					double fileSizeInKB = fileSizeInBytes / 1024.0;
					if (fileSizeInKB > CommonConstant.MAX_FILE_SIZE) {
						request
								.setAttribute(
										CommonConstant.lbl_reg_init_upload_msg3,
										"File size is Greater than 500 KB. Please select another file. / फ़ाइल का माप 500 KB से अधिक है। अन्य फाइल चुनें।");
						eForm.setFileSizeError("Y");
						forwardJsp = "uploadSuccessEFile1";
						return mapping.findForward(forwardJsp);
					}

					// check for file extension
					MIMECheck mimeCheck = new MIMECheck();
					String fileExt = mimeCheck.getFileExtension(uploadedFile
							.getFileName());
					Boolean mime = mimeCheck.validateFileTypeEfile(fileExt);

					// end of file extension

					if (mime) {
						eForm.setPropImage2DocumentName(uploadedFile
								.getFileName());
						PropertiesFileReader pr = PropertiesFileReader
								.getInstance("resources.igrs");
						path = pr.getValue("upload.location");
						filePath = path + CommonConstant.FILE_UPLOAD_PATH
								+ efileTxnId;

						File folder = new File(filePath);
						// String fileName=uploadedFile.getFileName();
						String fileName = CommonConstant.FILE_UPLOAD_PAYMENT_PAGE;
						// below code to save upload path
						String maindutyId = (String) session
								.getAttribute("dutyId1");
						boolean pathStatus = false;
						boolean checkduty = bo.getcheckduty(maindutyId);
						if (checkduty) {
							pathStatus = bo.getuploadpath(maindutyId, filePath);
						} else {
							pathStatus = bo
									.getuploadpath1(maindutyId, filePath);
						}
						// end of code
						byte[] content = uploadedFile.getFileData();
						if (!folder.exists()) {
							folder.mkdirs();
						}

						try {
							//if()
							File newFile = new File(filePath, fileName);
							FileOutputStream fos = new FileOutputStream(newFile);
							fos.write(content);
							fos.close();

							boolean uploadStatus = true;

							if (uploadStatus == true) {
								if (language.equalsIgnoreCase("en"))
									request.setAttribute(
											CommonConstant.SUCCESS_MSG_UPLOAD,
											"Successfully Uploaded.");
								else
									request.setAttribute(
											CommonConstant.SUCCESS_MSG_UPLOAD,
											"सफलतापूर्वक अपलोड की गई");
								forwardJsp = "uploadSuccessEFile1";
							}

							else {
								// session.setAttribute("checkStatus",
								// Constants.PROBLEM_IN_AS);
								if (language.equalsIgnoreCase("en"))
									request.setAttribute(
											CommonConstant.FAILURE_MSG_UPLOAD,
											"Not Uploaded Successfully");
								else
									request.setAttribute(
											CommonConstant.FAILURE_MSG_UPLOAD,
											"सफलतापूर्वक अपलोड नहीं किया गया");
								forwardJsp = "uploadSuccessEFile1";
							}

							return mapping.findForward(forwardJsp);

						} catch (Exception ex) {

							ex.printStackTrace();
						}
					} else {
						eForm.setFileError("Y");
						if (language.equalsIgnoreCase("en"))
							request
									.setAttribute(
											CommonConstant.ERRORFILETYPE_MSG_UPLOAD,
											"Please Upload a Valid File.File should be in PDF Format.");
						else
							request
									.setAttribute(
											CommonConstant.ERRORFILETYPE_MSG_UPLOAD,
											"कृपया एक मान्य फाइल अपलोड करें। फाइल पीडीएफ प्रारूप में होनी चाहिए।");
						forwardJsp = "uploadSuccessEFile1";
						return mapping.findForward(forwardJsp);

					}
				}

				/* } */

				/*
				 * if(CommonConstant.DUTY_CALCULATE_ACTION_efilenumber.equals(actionName
				 * )){ System.out.println("inside efile number method"); String
				 * tempEfileId= (String) session.getAttribute("regTxnId");
				 * bo.submitDetails(eForm,tempEfileId); String district="";
				 * String districtSec=""; //generating last seq numbe Calendar
				 * currentDate = Calendar.getInstance(); SimpleDateFormat
				 * formatter= new SimpleDateFormat("ddMMyyyy"); String dateNow =
				 * formatter.format(currentDate.getTime()); String regTxnIdSeq=
				 * bo.getFinalSeqEfile();
				 * 
				 * 
				 * //for First 3 digit seq
				 * 
				 * String fSeq=bo.getfSeq1();
				 * 
				 * //for district String districtSeq; ArrayList
				 * districtId=bo.getDistrictEfile(tempEfileId);
				 * 
				 * 
				 * 
				 * for (int i = 0; i < districtId.size(); i++){ ArrayList abc =
				 * (ArrayList) districtId.get(0); district= (String) abc.get(0);
				 * 
				 * 
				 * }
				 * 
				 * // String district= (String)
				 * session.getAttribute("districtId");
				 * System.out.println("district abccsgavdjhsdvfhdv"+district);
				 * 
				 * if(district.length()!=2 && !(district.length()>=2) ){
				 * districtSeq="0"+district; } else{
				 * 
				 * // districtSeq= (String) session.getAttribute("districtId");
				 * districtSeq= district; }
				 * 
				 * 
				 * 
				 * 
				 * String regTxnId1=null;
				 * regTxnId1=fSeq+districtSeq+dateNow+regTxnIdSeq;
				 * System.out.println("number"+regTxnId1);
				 * 
				 * //to get office name and office Id
				 * 
				 * // ArrayList districtList=bo.getbankDistrict(stateDisId); //
				 * eForm.getDutyCalculationDTO().setDistList(districtList);
				 * 
				 * 
				 * ArrayList List=new ArrayList(); ArrayList officeName
				 * =bo.getoffNameEfile(district);
				 * eForm.getDutyCalculationDTO().setOfficeList(officeName);
				 * 
				 * for(int i = 0; i < officeName.size(); i++){ ArrayList abc =
				 * (ArrayList) officeName.get(i);
				 * 
				 * List.add(abc);
				 * 
				 * } System.out.println("office list "+List);
				 * 
				 * 
				 * //int i = (int) (Math.random() rank.length); // String
				 * officeId=bo.getOffIdEfile(district);
				 * 
				 * //to get how many office are mapped to office id
				 * 
				 * // ArrayList officeMap=bo.getOfficeMapEfile(officeId);
				 * 
				 * 
				 * 
				 * 
				 * System.out.println("abc final efiling number display to user"+
				 * regTxnId1);
				 * 
				 * 
				 * bo.submitEfileNoDetails(tempEfileId,regTxnId1);
				 * 
				 * 
				 * 
				 * 
				 * 
				 * eForm.getDutyCalculationDTO().setHidEfileTxnId(regTxnId1);
				 * 
				 * 
				 * 
				 * 
				 * String Finalpage03="53"; boolean
				 * updateStausLast=bo.updateStausLast(tempEfileId,Finalpage03);
				 * 
				 * forwardJsp="efilenumber"; return
				 * mapping.findForward(forwardJsp);
				 * 
				 * }
				 */

				// for new deed
				if (CommonConstant.DUTY_CALCULATE_ACTION_NONBANK
						.equals(actionName)) {
					eForm.getDutyCalculationDTO().setNameOfOfficial("");
					eForm.getDutyCalculationDTO().setAddressGovtOffclOutMp("");
					eForm.getDutyCalculationDTO().setDistrictId("");
					eForm.getDutyCalculationDTO().setNameOfOfficial3("");
					eForm.getDutyCalculationDTO().setNameOfOfficial4("");
					eForm.getDutyCalculationDTO().setNameOfOfficial5("");
					eForm.getDutyCalculationDTO().setNameOfOfficial6("");
					eForm.getDutyCalculationDTO().setNameOfOfficial7("");
					eForm.getDutyCalculationDTO().setNameOfOfficial8("");
					boolean statusupdate = false;

					String purposeLoan;

					String maindutyId = (String) session
							.getAttribute("dutyId1");
					String tempefileId = (String) session
							.getAttribute("regTxnId");

					String deedId = bo.getdeedId(maindutyId);
					String instId = bo.getInstId(maindutyId);
					String userid = (String) session.getAttribute("UserId");
					String maindutyId1 = (String) session
							.getAttribute("getdutyTxnId");
					String tempefileId1 = (String) session
							.getAttribute("tempefileid");

					purposeLoan = eForm.getDutyCalculationDTO()
							.getPurposeLoan();
					boolean consumedStatus = false;
					consumedStatus = bo.setpurposeLoanNonPayment(purposeLoan,
							maindutyId);

					String statuspage01 = "51";
					statusupdate = bo.updateStatus(tempefileId, statuspage01,
							maindutyId, deedId, instId, userid);

					String stateDisId = "1";
					// ArrayList districtList=bo.getDistrictId(stateDisId);

					ArrayList districtList = null;
					// ArrayList districtList=bo.getbankDistrict(stateDisId);
					// eForm.getDutyCalculationDTO().setDistList(districtList);

					// Added by Gulrej on 11/5/2017 // populates districts in
					// hindi language
					if (language.equalsIgnoreCase("en")) {
						districtList = bo.getbankDistrict(stateDisId);
						eForm.getDutyCalculationDTO().setDistList(districtList);
					} else {
						districtList = bo.getbankDistrictHindi(stateDisId);
						eForm.getDutyCalculationDTO().setDistList(districtList);
					}

					System.out.println("display district in bank page"
							+ districtList);
					forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY_BANK;
					return mapping.findForward(forwardJsp);
				}

				// below code is used when payable duty is zero

				if (CommonConstant.DUTY_CALCULATE_ACTION_ZERODUTYBANK
						.equals(actionName)) {
					eForm.getDutyCalculationDTO().setNameOfOfficial("");
					eForm.getDutyCalculationDTO().setAddressGovtOffclOutMp("");
					eForm.getDutyCalculationDTO().setDistrictId("");
					eForm.getDutyCalculationDTO().setNameOfOfficial3("");
					eForm.getDutyCalculationDTO().setNameOfOfficial4("");
					eForm.getDutyCalculationDTO().setNameOfOfficial5("");
					eForm.getDutyCalculationDTO().setNameOfOfficial6("");
					eForm.getDutyCalculationDTO().setNameOfOfficial7("");
					eForm.getDutyCalculationDTO().setNameOfOfficial8("");
					boolean statusupdate = false;

					String purposeLoan;

					String maindutyId = (String) session
							.getAttribute("dutyId1");
					String tempefileId = (String) session
							.getAttribute("regTxnId");

					String deedId = bo.getdeedId(maindutyId);
					String instId = bo.getInstId(maindutyId);

					String maindutyId1 = (String) session
							.getAttribute("getdutyTxnId");
					String tempefileId1 = (String) session
							.getAttribute("tempefileid");

					purposeLoan = eForm.getDutyCalculationDTO()
							.getPurposeLoan();
					boolean consumedStatus = false;
					consumedStatus = bo.setpurposeLoanNonPayment(purposeLoan,
							maindutyId);
					String userid = (String) session.getAttribute("UserId");
					String statuspage01 = "51";
					statusupdate = bo.updateStatus(tempefileId, statuspage01,
							maindutyId, deedId, instId, userid);

					String stateDisId = "1";
					// ArrayList districtList=bo.getDistrictId(stateDisId);

					// ArrayList districtList=bo.getbankDistrict(stateDisId);
					// eForm.getDutyCalculationDTO().setDistList(districtList);

					// Added by Gulrej 0n 11/5/2017 // populates districts in
					// hindi language
					ArrayList districtList = null;

					if (language.equalsIgnoreCase("en")) {
						districtList = bo.getbankDistrict(stateDisId);
						eForm.getDutyCalculationDTO().setDistList(districtList);
					} else {
						districtList = bo.getbankDistrictHindi(stateDisId);
						eForm.getDutyCalculationDTO().setDistList(districtList);
					}

					System.out.println("display district in bank page"
							+ districtList);
					forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY_BANK;
					return mapping.findForward(forwardJsp);
				}

				// end

				// bank page
				if (request.getParameter("fwdName") != null) {
					String Forward = request.getParameter("fwdName");

					if (Forward.equalsIgnoreCase("deleteStamp")) {
						String efiledeleteId = request
								.getParameter("efileTempId");
						String deleteseriesNo = request
								.getParameter("seriesNumber");
						boolean deleteflag = false;
						deleteflag = bo.deleteStamp(efiledeleteId,
								deleteseriesNo);

						if (deleteflag) {
							ArrayList remainDetails = new ArrayList();

							remainDetails = bo.getremainDetails(efiledeleteId);
							if (remainDetails.size() > 0) {
								ArrayList<DutyCalculationDTO> dto1 = eForm
										.getListDto();
								if (dto1 != null) {

								} else {

									dto1 = new ArrayList();

								}
								ArrayList rowList;
								dto1 = new ArrayList();
								for (int i = 0; i < remainDetails.size(); i++) {

									rowList = (ArrayList) remainDetails.get(i);
									DutyCalculationDTO dutydto = null;
									dutydto = new DutyCalculationDTO();
									dutydto.setDisplayStampVendorName(rowList
											.get(0).toString());
									dutydto.setDisplayStampVendorSeries(rowList
											.get(2).toString());
									dutydto.setDisplayCodeStamp(rowList.get(1)
											.toString());
									dutydto.setDisplayDateStamp(rowList.get(3)
											.toString());
									dto1.add(dutydto);
									// eForm.setListDto(dto1);
									eForm.setPhysicalStampList(dto1); // Added
																		// by
																		// Gulrej
																		// on
																		// 9th
																		// may,
																		// 2017

								}
							} else {
								ArrayList<DutyCalculationDTO> dto1 = eForm
										.getListDto();
								DutyCalculationDTO dutydto = null;
								dutydto = new DutyCalculationDTO();
								dto1.add(dutydto);
								eForm.setListDto(new ArrayList());
							}
							Forward = "";
							forwardJsp = "estampCodeSuccess";
						} else {
							Forward = "";
							forwardJsp = "estampCodeSuccess";
						}
						return mapping.findForward(forwardJsp);
					}
				}

				// added for bank details page
				if (CommonConstant.DUTY_CALCULATE_ACTION_BANK
						.equals(actionName)) {

					/* Added by Gulrej // For addition of physical estamps */

					// eForm.setActionName(CommonConstant.NO_ACTION);
					// eForm.setActionName("");
					// actionName="";
					String maindutyId = (String) session
							.getAttribute("dutyId1");
					String userid = (String) session.getAttribute("UserId");
					String deedId = bo.getdeedId(maindutyId);
					String instId = bo.getInstId(maindutyId);
					String physicalStampArrAdded[] = request
							.getParameterValues("physicalStampArr");
					if (physicalStampArrAdded != null
							&& physicalStampArrAdded[0] != "") {
						// String physicalStamp = null;

						boolean statusupdate = false;

						boolean status = false;

						// below code for temporary efiling
						Calendar currentDate = Calendar.getInstance();
						SimpleDateFormat formatter = new SimpleDateFormat(
								"ddMMyyyy");
						String dateNow = formatter
								.format(currentDate.getTime());

						String regTxnIdSeq = bo.getRegInitTxnIdSeqEfile();
						System.out.println("temp rehtxnid" + regTxnIdSeq);
						String regTxnId = null;
						regTxnId = dateNow + regTxnIdSeq;
						System.out.println("number" + regTxnId);

						eForm.setHidnRegTxnId(regTxnId);
						session.setAttribute("regTxnId", regTxnId);

						// end of temporary efiling

						// for( int i = 0; i < physicalStampArr.length; i++)
						// {
						//System.out.print("physicalStamp   :: "+physicalStamp);
						// physicalStamp = physicalStampArr[i];
						String[] physicalStampArr = physicalStampArrAdded[0]
								.split(",");
						for (int j = 0; j < physicalStampArr.length; j++) {
							String physicalStamp = physicalStampArr[j];
							String[] physicalStamp1 = physicalStamp.split("!");
							String seriesNumber = physicalStamp1[1];
							String stampvendor = physicalStamp1[0];
							String codeOfStamps = physicalStamp1[2];
							String stampdate = physicalStamp1[3];
							status = bo.getphysicalstamp(seriesNumber,
									stampvendor, codeOfStamps, stampdate,
									deedId, instId, maindutyId, regTxnId);
						}

						// }

						String statuspage01 = "51";

						statusupdate = bo.updateStatus(regTxnId, statuspage01,
								maindutyId, deedId, instId, userid);
					}
					// if(status==true||statusupdate==true){
					// eForm.setActionName(CommonConstant.NO_ACTION);
					// eForm.setActionName("");
					// actionName="";
					// eForm.setAddFlag("Y");
					// eForm.setHiddenFlagFirst("Y");

					// below code is used to display details in grid form on jsp
					// ArrayList<DutyCalculationDTO> dto1 = eForm.getListDto();
					// if(dto1!=null){

					// }else{

					// dto1=new ArrayList();

					// }
					// DutyCalculationDTO dutydto=null;
					// dutydto=new DutyCalculationDTO();
					// dutydto.setDisplayStampVendorName(eForm.
					// getDutyCalculationDTO().getStampvendor());
					// dutydto.setDisplayStampVendorSeries(eForm.
					// getDutyCalculationDTO().getSeriesNumber());
					//dutydto.setDisplayCodeStamp(eForm.getDutyCalculationDTO().
					// getCodeOfStamps());
					// dutydto.setDisplayDateStamp(datephyestamp);
					// dto1.add(dutydto);
					// eForm.setListDto(dto1);
					// eForm.setPhysicalStampList(dto1); // Added by Gulrej on
					// 9th may, 2017
					eForm.setButtonflag("Y");
					//

					// if(language.equalsIgnoreCase("en"))
					//request.setAttribute(CommonConstant.SUCCESS_MSG_PHYSICAL1,
					// "Successfully Submitted Physical EstampCode.");
					// else
					//request.setAttribute(CommonConstant.SUCCESS_MSG_PHYSICAL1,
					// "शारीरिक एस्टाम्पकोड सफलतापूर्वक प्रस्तुत किया गया है");

					// eForm.setRadioEFlag("disable");
					// eForm.setRadioPhFlag("disable");
					// forwardJsp="estampCodeSuccess";
					// }
					// eForm.setActionName(CommonConstant.NO_ACTION);
					// eForm.setActionName("");
					// actionName="";
					// eForm.setFlagdisplay("Y");
					// return mapping.findForward(forwardJsp);

					/* */

					// String maindutyId=(String)
					// session.getAttribute("dutyId1");
					String tempefileId = (String) session
							.getAttribute("regTxnId");

					String maindutyId1 = (String) session
							.getAttribute("getdutyTxnId");
					String tempefileId1 = (String) session
							.getAttribute("tempefileid");

					if (maindutyId != null && tempefileId != null) {
						eForm.setHidnRegTxnId(tempefileId);
					} else {
						eForm.setHidnRegTxnId(tempefileId1);
					}

					// below code is used to check physical stamp amount should
					// be equal to total duty
					// String
					// radiotype=eForm.getDutyCalculationDTO().getRadioResiComm
					// ();
					// if(maindutyId!=null && tempefileId!=null){
					// if(radiotype!=null && !radiotype.isEmpty() ){
					// if(radiotype.equalsIgnoreCase("no")){
					// if(maindutyId!=null && tempefileId!=null){
					// double
					// dutyAmt=Double.parseDouble(bo.getestampAmt(maindutyId));
					// double physicalAmt=0.0d;
					// String flag=bo.physicalAmt(tempefileId);
					// if(!flag.isEmpty()){
					//physicalAmt=Double.parseDouble(bo.physicalAmt(tempefileId)
					// );
					// }
					// else{
					// if(language.equalsIgnoreCase("en")){
					// request.setAttribute(CommonConstant.SUCCESS_MSG_AMT4,
					// "Please Add Physical Stamp Code to proceed. ");

					// }
					// else{
					// request.setAttribute(CommonConstant.SUCCESS_MSG_AMT4,
					// "कृपया शारीरिक स्टाम्प संहिता आगे बढ़ने के लिए");
					// }
					// return mapping.findForward(forwardJsp);
					// }

					// int retval = Double.compare(dutyAmt, physicalAmt);

					// if(retval == 1 ||retval == -1){
					// if(language.equalsIgnoreCase("en")){
					// request.setAttribute(CommonConstant.SUCCESS_MSG_AMT1,
					// "StampCode amount should be equal to TotalPayable Duty."
					// );
					// //eForm.setPhysicalEstampErrorFlag(
					// "StampCode amount should be equal to TotalPayable Duty."
					// );
					// }
					// else{
					// //eForm.setPhysicalEstampErrorFlag(
					// "स्टाम्प संहिता राशि कुल देय शुल्क के बराबर होना चाहिए");
					// request.setAttribute(CommonConstant.SUCCESS_MSG_AMT1,
					// "स्टाम्प संहिता राशि कुल देय शुल्क के बराबर होना चाहिए");
					// }
					// // eForm.getDutyCalculationDTO().setEstampFlag("Y");
					// forwardJsp="estampCodeSuccess";
					//
					// return mapping.findForward(forwardJsp);
					// }
					// }

					// }
					// }
					// }
					//
					// // Added by Gulrej 0n 10th May
					//			
					// if(maindutyId1!=null && tempefileId1!=null){
					// double
					// dutyAmt=Double.parseDouble(bo.getestampAmt(maindutyId1));
					// double physicalAmt=0.0d;
					// String flag=bo.physicalAmt(tempefileId1);
					// if(!flag.isEmpty()){
					//physicalAmt=Double.parseDouble(bo.physicalAmt(tempefileId1
					// ));
					// }
					//			
					//				
					// int retval = Double.compare(dutyAmt, physicalAmt);
					//				
					// if(retval == 1 ||retval == -1){
					// if(language.equalsIgnoreCase("en")){
					// request.setAttribute(CommonConstant.SUCCESS_MSG_AMT1,
					// "StampCode amount should be equal to TotalPayable Duty."
					// );
					// //eForm.setPhysicalEstampErrorFlag(
					// "StampCode amount should be equal to TotalPayable Duty."
					// );
					// }
					// else{
					// //eForm.setPhysicalEstampErrorFlag(
					// "स्टाम्प संहिता राशि कुल देय शुल्क के बराबर होना चाहिए");
					// request.setAttribute(CommonConstant.SUCCESS_MSG_AMT1,
					// "स्टाम्प संहिता राशि कुल देय शुल्क के बराबर होना चाहिए");
					// }
					// forwardJsp = "dashboardDutyEstampCodePg";
					// return mapping.findForward(forwardJsp);
					// }
					// }

					// Added by Gulrej 0n 10th May -- End

					eForm.getDutyCalculationDTO().setNameOfOfficial("");
					eForm.getDutyCalculationDTO().setAddressGovtOffclOutMp("");
					eForm.getDutyCalculationDTO().setDistrictId("");
					eForm.getDutyCalculationDTO().setNameOfOfficial3("");
					eForm.getDutyCalculationDTO().setNameOfOfficial4("");
					eForm.getDutyCalculationDTO().setNameOfOfficial5("");
					eForm.getDutyCalculationDTO().setNameOfOfficial6("");
					eForm.getDutyCalculationDTO().setNameOfOfficial7("");
					eForm.getDutyCalculationDTO().setNameOfOfficial8("");
					String purposeLoan;

					purposeLoan = eForm.getDutyCalculationDTO()
							.getPurposeLoan();

					boolean consumedStatus = false;
					boolean consumedStatusPhysical = false;
					boolean amtCheck = false;
					String checkdutyInsert = null;

					if (maindutyId != null && tempefileId != null) {
						consumedStatusPhysical = bo.getPhysicalFlag(
								tempefileId, maindutyId);
						checkdutyInsert = bo.getdutyCheck(tempefileId,
								maindutyId);
					}

					else {
						consumedStatusPhysical = bo.getPhysicalFlag(
								tempefileId1, maindutyId1);
						checkdutyInsert = bo.getdutyCheck(tempefileId1,
								maindutyId1);
					}

					if (maindutyId != null || tempefileId != null) {
						if (!consumedStatusPhysical) {
							consumedStatus = bo.setpurposeLoan(purposeLoan,
									maindutyId, tempefileId);

						} else {
							consumedStatus = bo.setpurposeLoanPhysical(
									purposeLoan, maindutyId, tempefileId);
						}
					}

					/*
					 * else if(maindutyId!=null || tempefileId!=null){
					 * if(consumedStatusPhysical){
					 * consumedStatus=bo.setpurposeLoanPhysical
					 * (purposeLoan,maindutyId,tempefileId); } }
					 */

					else {

						if (maindutyId1 != null || tempefileId1 != null) {

							if (!consumedStatusPhysical) {
								consumedStatus = bo.setpurposeLoan(purposeLoan,
										maindutyId1, tempefileId1);
							}

							else {

								consumedStatus = bo.setpurposeLoanPhysical(
										purposeLoan, maindutyId1, tempefileId1);

							}
							if (!consumedStatus) {
								consumedStatus = bo.setpurposeLoanDuty(
										purposeLoan, maindutyId1, tempefileId1);
							}
						}

					}
					if (consumedStatus == true) {

						String stateDisId = "1";
						ArrayList districtList = new ArrayList();
						// ArrayList districtList=bo.getDistrictId(stateDisId);
						if (language.equalsIgnoreCase("en")) {
							districtList = bo.getbankDistrict(stateDisId);
							eForm.getDutyCalculationDTO().setDistList(
									districtList);
						} else {
							districtList = bo.getbankDistrictHindi(stateDisId);
							eForm.getDutyCalculationDTO().setDistList(
									districtList);
						}

						System.out.println("display district in bank page"
								+ districtList);
						forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY_BANK;
						return mapping.findForward(forwardJsp);
					}
				}

				// changes by preeti for estamp

				if (CommonConstant.DUTY_CALCULATE_ACTION_estamp
						.equals(actionName)) {
					System.out.println("inside esatmp code verification");
					// estampCode=eForm.getRegcompletDto().getEstampCode();
					String maindutyId = (String) session
							.getAttribute("dutyId1");
					String deedId = bo.getdeedId(maindutyId);
					String instId = bo.getInstId(maindutyId);
					estampCode = eForm.getDutyCalculationDTO().getEstampCode();
					System.out.println("in estamp code verification"
							+ estampCode);
					boolean status = false;
					boolean statusDeactivated = false;
					boolean statusEstampType = false;
					boolean statusSourceType = false;
					boolean consumedStatus = false;
					boolean statusupdate = false;

					// below check is for type i.e estamp type id=1
					statusEstampType = bo.getstatusEstampType(estampCode);
					if (statusEstampType) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(
									CommonConstant.SUCCESS_MSG_TYPE,
									"Judicial E-Stamp cannot be Consumed.");
						else
							request
									.setAttribute(
											CommonConstant.SUCCESS_MSG_TYPE,
											"न्यायिक ई- स्टाम्प का सेवन नहीं किया जा सकता");
						// eForm.getDutyCalculationDTO().setEstampFlag("Y");
						forwardJsp = "estampCodeSuccess";

						return mapping.findForward(forwardJsp);
					}

					// below check is for estamp source mode i.e E
					statusSourceType = bo.getstatusSourceType(estampCode);
					if (!statusSourceType) {
						if (language.equalsIgnoreCase("en"))
							request
									.setAttribute(
											CommonConstant.SUCCESS_MSG_SOURCE_TYPE,
											"This E-stamp is Created in Registration Process,hence cannot be Consumed.");
						else
							request
									.setAttribute(
											CommonConstant.SUCCESS_MSG_SOURCE_TYPE,
											"इस ई- स्टांप पंजीकरण प्रक्रिया में बनाया जाता है, इसलिए सेवन नहीं किया जा सकता");
						// eForm.getDutyCalculationDTO().setEstampFlag("Y");
						forwardJsp = "estampCodeSuccess";

						return mapping.findForward(forwardJsp);
					}

					statusDeactivated = bo.getstatusEstamp(estampCode);
					if (!statusDeactivated) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(
									CommonConstant.SUCCESS_MSG_DEACT,
									"E-Stamp is Deactivated.");
						else
							request.setAttribute(
									CommonConstant.SUCCESS_MSG_DEACT,
									"ई- स्टाम्प निष्क्रिय है");
						// eForm.getDutyCalculationDTO().setEstampFlag("Y");
						eForm.setRadioPFlag("disable");

						forwardJsp = "estampCodeSuccess";

						return mapping.findForward(forwardJsp);
					}

					// check estamp code is of proper amount or not

					double dutyAmt = Double.parseDouble(bo
							.getestampAmt(maindutyId));
					double estampAmt = Double
							.parseDouble(bo.getAmt(estampCode));
					int retval = Double.compare(dutyAmt, estampAmt);

					if (retval == -1 || retval == 1) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(
									CommonConstant.SUCCESS_MSG_AMT,
									"E-Stamp amount is not correct.");
						else
							request.setAttribute(
									CommonConstant.SUCCESS_MSG_AMT,
									"ई- स्टाम्प राशि सही नहीं है");
						// eForm.getDutyCalculationDTO().setEstampFlag("Y");
						forwardJsp = "estampCodeSuccess";

						return mapping.findForward(forwardJsp);
					}

					// end

					status = bo.getestamp(estampCode);
					if (status == true) {
						// below code for temporary efiling
						Calendar currentDate = Calendar.getInstance();
						SimpleDateFormat formatter = new SimpleDateFormat(
								"ddMMyyyy");
						String dateNow = formatter
								.format(currentDate.getTime());
						String userid = (String) session.getAttribute("UserId");

						String regTxnIdSeq = bo.getRegInitTxnIdSeqEfile();
						System.out.println("temp rehtxnid" + regTxnIdSeq);
						String regTxnId = null;
						regTxnId = dateNow + regTxnIdSeq;
						System.out.println("number" + regTxnId);

						eForm.setHidnRegTxnId(regTxnId);
						session.setAttribute("regTxnId", regTxnId);
						// end of temporary efiling

						consumedStatus = bo.setestamp(estampCode, maindutyId,
								regTxnId);
						String statuspage01 = "51";
						statusupdate = bo.updateStatus(regTxnId, statuspage01,
								maindutyId, deedId, instId, userid);

						if (consumedStatus == true || statusupdate == true) {

							eForm.setRadioPFlag("disable");
							eForm.setEstampValidated("YES");
							if (language.equalsIgnoreCase("en"))
								request.setAttribute(
										CommonConstant.SUCCESS_MSG,
										"Successfully Consumed.");
							else
								request.setAttribute(
										CommonConstant.SUCCESS_MSG,
										"सफलतापूर्वक उपभोग किया गया");
							// eForm.getDutyCalculationDTO().setEstampFlag("Y");
							forwardJsp = "estampCodeSuccess";
						}
					} else {
						// session.setAttribute("checkStatus",
						// Constants.PROBLEM_IN_AS);
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(CommonConstant.FAILURE_MSG,
									"Estamp Already Consumed.");
						else
							request.setAttribute(CommonConstant.FAILURE_MSG,
									"एस्टाम पहले से ही खपत है");
						forwardJsp = "estampCodeSuccess";
					}
					eForm.setFlagdisplay("Y");
					eForm.getDutyCalculationDTO().setRadioResiComm("yes");
					return mapping.findForward(forwardJsp);
				}

				if (CommonConstant.DUTY_CALCULATE_ACTION_ADD_MORE_BUTTON
						.equals(actionName)) {
					eForm.getDutyCalculationDTO().setStampvendor("");
					eForm.getDutyCalculationDTO().setCodeOfStamps("");
					eForm.getDutyCalculationDTO().setSeriesNumber("");
					eForm.setStampdate("");
					eForm.setActionName("");
					eForm.setHiddenFlag("Y");
					eForm.setAddFlag("");
					eForm.setRadioPhFlag("");
					actionName = "";

					forwardJsp = "estampCodeSuccess";
					return mapping.findForward(forwardJsp);

				}

				if (CommonConstant.DUTY_CALCULATE_ACTION_PHYSICAL_STAMPS_ADD_MORE
						.equals(actionName)) {
					boolean status = false;
					String maindutyId = (String) session
							.getAttribute("dutyId1");
					String deedId = bo.getdeedId(maindutyId);
					String instId = bo.getInstId(maindutyId);
					String regTxnId = (String) session.getAttribute("regTxnId");
					datephyestamp = eForm.getStampdate();
					String checkdutyInsert = null;

					checkdutyInsert = bo.getdutyCheck(regTxnId, maindutyId);
					status = bo.addMorephysicalstamp(eForm, deedId, instId,
							regTxnId, datephyestamp);

					if (status) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(
									CommonConstant.SUCCESS_MSG_PHYSICAL,
									"Successfully Added Physical EstampCode.");
						else
							request
									.setAttribute(
											CommonConstant.SUCCESS_MSG_PHYSICAL,
											"शारीरिक एस्टैम्पकोड सफलतापूर्वक जोड़ा गया");
						// eForm.getDutyCalculationDTO().setEstampFlag("Y");
						eForm.setHiddenFlag("");
						eForm.setAddFlag("Y");
						// below code is used to display details in grid form on
						// jsp
						ArrayList<DutyCalculationDTO> dto1 = eForm.getListDto();
						if (dto1 != null) {

						} else {

							dto1 = new ArrayList();

						}
						DutyCalculationDTO dutydto = null;
						dutydto = new DutyCalculationDTO();
						dutydto.setDisplayStampVendorName(eForm
								.getDutyCalculationDTO().getStampvendor());
						dutydto.setDisplayStampVendorSeries(eForm
								.getDutyCalculationDTO().getSeriesNumber());
						dutydto.setDisplayCodeStamp(eForm
								.getDutyCalculationDTO().getCodeOfStamps());
						dutydto.setDisplayDateStamp(datephyestamp);
						dto1.add(dutydto);
						// eForm.setListDto(dto1);
						eForm.setPhysicalStampList(dto1); // Added by Gulrej on
															// 9th may, 2017
						//
						eForm.setRadioPhFlag("disable");
						forwardJsp = "estampCodeSuccess";

					}

					else {
						// session.setAttribute("checkStatus",
						// Constants.PROBLEM_IN_AS);
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(
									CommonConstant.FAILURE_MSG_PHYSICAL,
									"Record Not Added Successfully ");
						else
							request.setAttribute(
									CommonConstant.FAILURE_MSG_PHYSICAL,
									"रिकॉर्ड सफलतापूर्वक जोड़ा नहीं गया");
						forwardJsp = "estampCodeSuccess";
					}
					return mapping.findForward(forwardJsp);
				}

				// changes by preeti for physical stamps
				if (CommonConstant.DUTY_CALCULATE_ACTION_PHYSICAL_STAMPS
						.equals(actionName)) {
					eForm.setActionName(CommonConstant.NO_ACTION);
					eForm.setActionName("");
					actionName = "";
					boolean statusupdate = false;
					String maindutyId = (String) session
							.getAttribute("dutyId1");
					String userid = (String) session.getAttribute("UserId");
					String deedId = bo.getdeedId(maindutyId);
					String instId = bo.getInstId(maindutyId);
					datephyestamp = eForm.getStampdate();
					boolean status = false;

					// below code for temporary efiling
					Calendar currentDate = Calendar.getInstance();
					SimpleDateFormat formatter = new SimpleDateFormat(
							"ddMMyyyy");
					String dateNow = formatter.format(currentDate.getTime());

					String regTxnIdSeq = bo.getRegInitTxnIdSeqEfile();
					System.out.println("temp rehtxnid" + regTxnIdSeq);
					String regTxnId = null;
					regTxnId = dateNow + regTxnIdSeq;
					System.out.println("number" + regTxnId);

					eForm.setHidnRegTxnId(regTxnId);
					session.setAttribute("regTxnId", regTxnId);

					// end of temporary efiling
					// status=bo.getphysicalstamp(eForm.getDutyCalculationDTO(),
					// datephyestamp,deedId,instId,maindutyId,regTxnId);
					String statuspage01 = "51";

					statusupdate = bo.updateStatus(regTxnId, statuspage01,
							maindutyId, deedId, instId, userid);
					if (status == true || statusupdate == true) {
						eForm.setActionName(CommonConstant.NO_ACTION);
						eForm.setActionName("");
						actionName = "";
						eForm.setAddFlag("Y");
						eForm.setHiddenFlagFirst("Y");

						// below code is used to display details in grid form on
						// jsp
						ArrayList<DutyCalculationDTO> dto1 = eForm.getListDto();
						if (dto1 != null) {

						} else {

							dto1 = new ArrayList();

						}
						DutyCalculationDTO dutydto = null;
						dutydto = new DutyCalculationDTO();
						dutydto.setDisplayStampVendorName(eForm
								.getDutyCalculationDTO().getStampvendor());
						dutydto.setDisplayStampVendorSeries(eForm
								.getDutyCalculationDTO().getSeriesNumber());
						dutydto.setDisplayCodeStamp(eForm
								.getDutyCalculationDTO().getCodeOfStamps());
						dutydto.setDisplayDateStamp(datephyestamp);
						dto1.add(dutydto);
						// eForm.setListDto(dto1);
						eForm.setPhysicalStampList(dto1); // Added by Gulrej on
															// 9th may, 2017
						eForm.setButtonflag("Y");
						//

						if (language.equalsIgnoreCase("en"))
							request
									.setAttribute(
											CommonConstant.SUCCESS_MSG_PHYSICAL1,
											"Successfully Submitted Physical EstampCode.");
						else
							request
									.setAttribute(
											CommonConstant.SUCCESS_MSG_PHYSICAL1,
											"शारीरिक एस्टाम्पकोड सफलतापूर्वक प्रस्तुत किया गया है");

						eForm.setRadioEFlag("disable");
						eForm.setRadioPhFlag("disable");
						forwardJsp = "estampCodeSuccess";
					}
					eForm.setActionName(CommonConstant.NO_ACTION);
					eForm.setActionName("");
					actionName = "";
					eForm.setFlagdisplay("Y");
					return mapping.findForward(forwardJsp);
				}

				// end

				// changes by preeti on 13 march 2015
				if (CommonConstant.DUTY_CALCULATE_ACTION_efiling
						.equals(actionName)) {

					eForm.setHidnRegTxnId("");
					eForm.getDutyCalculationDTO().setRadioResiComm("yes");
					eForm.getDutyCalculationDTO().setEstampCode("");
					eForm.getDutyCalculationDTO().setStampvendor("");
					eForm.getDutyCalculationDTO().setSeriesNumber("");
					eForm.getDutyCalculationDTO().setCodeOfStamps("");
					eForm.setStampdate("");
					eForm.getDutyCalculationDTO().setPurposeLoan("");
					eForm.setRadioEFlag("");
					eForm.setRadioPFlag("");
					InstrumentsDTO instdto = eForm.getInstDTO();
					ExemptionDTO exedto = eForm.getExempDTO();
					DutyCalculationDTO dtoForm = eForm.getDutyCalculationDTO();
					double userStampDuty = 0;

					// System.out.println("The exemption value is :"+exedto.
					// getHdnExAmount());
					// System.out.println("The exemption value is for :"+exedto.
					// getHdnExempAmount());
					logger.debug("The exemption NAME is :"
							+ exedto.getHdnExemptions());
					// System.out.println("The exemption value is :"+exedto.
					// getExemType());

					String param[] = request
							.getParameterValues("userEnterableFieldValue");
					ArrayList userList = new ArrayList();
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";

							if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "N".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getInStampDutyAmount();

							} else if ("N".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getAsConsiderationAmount();
							} else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidRegFeeFlag())) {
								dtoForm.setAlreadyPaidRegFee(value);
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidStampDutyFlag())) {
								dtoForm.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue("");
							userList.add(userDto);
						}
						// dto1.setUserValueMap(userValueMap);
						dtoForm.setUserValueList(userList);
					}

					String[] exemptions = exedto.getHdnExemptions() == null ? null
							: exedto.getHdnExemptions().split("~");

					// added by akansha

					if (instdto.getInstId() == 2253) {
						userStampDuty = Double.parseDouble(eForm.getInstDTO()
								.getInStampDutyAmount());
						dtoForm.setStampDuty(userStampDuty);

						double stampDuty = userStampDuty;//bo.getDutyCalculation
															// (dtoForm,instdto,
															// exedto);

						dtoForm.setStampDuty(stampDuty);

						double dutyArray[] = new double[3];
						double nagarPalikaDuty = new CalculateDuty()
								.calculateMuncipalDuty(instdto, dtoForm,
										dtoForm);
						double panchayatDuty = new CalculateDuty()
								.calculateJanPadDuty(instdto, dtoForm, dtoForm);

						double upkarDuty = 0.0;

						dtoForm.setNagarPalikaDuty((nagarPalikaDuty));
						dtoForm.setPanchayatDuty(panchayatDuty);
						upkarDuty = new CalculateDuty().calculateUpkar(eForm
								.getInstDTO().getInstId(), stampDuty);
						dtoForm.setUpkarDuty(upkarDuty);
						double total = nagarPalikaDuty + panchayatDuty
								+ upkarDuty + stampDuty;
						dtoForm.setTotal(total);

						double regFee = 0;//bo.getRegistrationFee(dtoForm,instdto
											// ,exedto);
						regFee = Double.parseDouble(new CalculateRegFee()
								.calculateRegFee(instdto, dtoForm, dtoForm));

						dtoForm.setRegFee(regFee);
						// session.setAttribute("regFee",regFee);

						logger.debug("Reg Fee:-" + regFee);
						double paidRegFee = 0;
						double paidStampduty = 0;
						if (dtoForm.getAlreadyPaidRegFee() != null
								&& !dtoForm.getAlreadyPaidRegFee()
										.equalsIgnoreCase("")) {
							paidRegFee = Double.parseDouble(dtoForm
									.getAlreadyPaidRegFee());
						}
						if (dtoForm.getAlreadyPaidStampDuty() != null
								&& !dtoForm.getAlreadyPaidStampDuty()
										.equalsIgnoreCase("")) {
							paidStampduty = Double.parseDouble(dtoForm
									.getAlreadyPaidStampDuty());
						}
						if (((regFee) - paidRegFee) < 0) {
							dtoForm.setPayableRegFee(0);
						} else {
							dtoForm.setPayableRegFee((regFee) - paidRegFee);
						}
						if (((total) - paidStampduty) < 0) {
							dtoForm.setPayableStampDuty(0);
						} else {
							dtoForm
									.setPayableStampDuty((total)
											- paidStampduty);
						}
						double minRegFee = bo.minRegFee(String.valueOf(dtoForm
								.getDeedId()), String.valueOf(instdto
								.getInstId()));
						double minStamp = bo.minStampDuty(String
								.valueOf(dtoForm.getDeedId()), String
								.valueOf(instdto.getInstId()));
						if (minRegFee > 0) {
							dtoForm.setMinRegFee(String.valueOf(minRegFee));
							dtoForm.setMinRegDisclaimerFlag("Y");
						} else {
							dtoForm.setMinRegFee(String.valueOf(minRegFee));
							dtoForm.setMinRegDisclaimerFlag("N");
						}
						if (minStamp > 0) {
							dtoForm.setMinStamp(String.valueOf(minStamp));
							dtoForm.setMinStampDisclaimerFlag("Y");
						} else {
							dtoForm.setMinStamp(String.valueOf(minStamp));
							dtoForm.setMinStampDisclaimerFlag("N");
						}
						if (dtoForm.getPayableStampDuty() < minStamp) {
							dtoForm.setPayableStampDuty(minStamp);
						}
						if (dtoForm.getPayableRegFee() < minRegFee) {
							dtoForm.setPayableRegFee(minRegFee);
						}

						// property wise exemption code comes here-Roopam- 4
						// April 2015

						int flag = new CalculateDuty()
								.calculatePropWiseExemptedDutiesEWS(instdto,
										dtoForm);

						if (flag == 1) {

							if ("en".equalsIgnoreCase(language)) {
								request
										.setAttribute(
												"ERROR",
												"If you want to avail EWS / LIG / MIG exemption, please select checkbox on Property valuation page – ‘Is your property is lease hold property of Housing Board’");
							} else {
								request
										.setAttribute(
												"ERROR",
												"If you want to avail EWS / LIG / MIG exemption, please select checkbox on Property valuation page – ‘Is your property is lease hold property of Housing Board’");

							}
							regFlag = false;
							eForm.getDutyCalculationDTO().setRadioResiComm3("");
							forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
							dto.setActionName(null);
							actionName = "";

						} else if (flag == 2) {

							if ("en".equalsIgnoreCase(language)) {
								request
										.setAttribute("ERROR",
												"Please select exemption belonging to either of EWS or MIG or LIG category");
							} else {
								request
										.setAttribute("ERROR",
												"Please select exemption belonging to either of EWS or MIG or LIG category");

							}
							regFlag = false;
							eForm.getDutyCalculationDTO().setRadioResiComm3("");
							forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
							dto.setActionName(null);
							actionName = "";

						} else {
							double d = bo.getExemptionPecentage(dtoForm);
							double fd = dtoForm.getPayableStampDuty()
									- ((dtoForm.getPayableStampDuty() * d) / 100);
							dtoForm.setMinusExempStamp(((dtoForm
									.getPayableStampDuty() * d) / 100));
							double fd1 = dtoForm.getTotalPayableStamp()
									- ((dtoForm.getTotalPayableStamp() * d) / 100);
							// dtoForm.setMinusExempStampTotal(((dtoForm.
							// getTotalPayableStamp()*d)/100));
							dtoForm
									.setMinusExempStampTotal((dtoForm
											.getMinusExempStampTotal())
											+ ((dtoForm.getTotalPayableStamp() * d) / 100));

							if (dtoForm.getDeedId() == 1092) {// lease

								boolean leaseRegFee1000Exemp = false;
								ArrayList list1 = dtoForm
										.getRegFeeExemptionDiscriptionList();

								if (list1 != null && list1.size() > 0) {
									for (int i = 0; i < list1.size(); i++) {
										DutyCalculationDTO dto1 = (DutyCalculationDTO) list1
												.get(i);

										if (!dto1.getExempId()
												.equalsIgnoreCase("130")) {
											leaseRegFee1000Exemp = true;
											break;

										}
									}
								} else {
									leaseRegFee1000Exemp = true;
								}

								if (!leaseRegFee1000Exemp) {

									if (dtoForm.getTotalPayableReg() > 1000) {

										dtoForm.setTotalPayableReg(1000);
										// regFee=1000;
									}

								}

							}
							String exceptionlist = exedto.getExemId();
							System.out.println("exceptionlist cons  "
									+ exceptionlist);
							System.out
									.println("exemptions cons  " + exemptions);

							double r = bo.getExemptionPecentageRegFee(dtoForm);
							double rd = dtoForm.getPayableRegFee()
									- ((dtoForm.getPayableRegFee() * r) / 100);
							dtoForm.setMinusExempReg(((dtoForm
									.getPayableRegFee() * r) / 100));
							double rd1 = dtoForm.getTotalPayableReg()
									- ((dtoForm.getTotalPayableReg() * r) / 100);
							dtoForm.setMinusExempRegTotal(((dtoForm
									.getTotalPayableReg() * r) / 100));

							dtoForm.setDutyafterExemption(fd);
							dtoForm.setDutyafterExemptionTotal(fd1);
							dtoForm.setRegFeeafterExemp(rd);
							dtoForm.setRegFeeafterExempTotal(rd1);

							// System.out.println("hdghjdhgsadhasd");

							String propValReqFlag = bo
									.getPropValRequiredFlag(instdto.getInstId());
							if (propValReqFlag.equalsIgnoreCase("Y")
									&& !"true".equalsIgnoreCase(dtoForm
											.getValuationIdValidate())) {

							} else {
								if (dtoForm.getTotalPayableStamp() < minStamp) {
									dtoForm.setTotalPayableStamp(minStamp);
								}
								if (dtoForm.getTotalPayableReg() < minRegFee) {
									dtoForm.setTotalPayableReg(minRegFee);
								}
							}
							if (dtoForm.getDutyafterExemption() < minStamp) {
								dtoForm.setDutyafterExemption(minStamp);
							}
							/*
							 * if(dtoForm.getRegFeeafterExemp()<minRegFee) {
							 * dtoForm.setRegFeeafterExemp(minRegFee); }
							 */

							if (dtoForm.getDutyafterExemptionTotal() < minStamp) {
								dtoForm.setDutyafterExemptionTotal(minStamp);
							}
							/*
							 * if(dtoForm.getRegFeeafterExempTotal()<minRegFee)
							 * { dtoForm.setRegFeeafterExempTotal(minRegFee); }
							 */

						}

					}

					else {
						double stampDuty = 0;// bo.getDutyCalculation(dtoForm,
												// instdto,exedto);

						stampDuty = Double.parseDouble(new CalculateDuty()
								.calculateStampDuty(instdto, dtoForm, dtoForm));
						dtoForm.setStampDuty(stampDuty);

						if (stampDuty < 0) {
							stampDuty = 0.0;
							dtoForm.setStampDuty(stampDuty);
						}

						double dutyArray[] = new double[3];
						double nagarPalikaDuty = new CalculateDuty()
								.calculateMuncipalDuty(instdto, dtoForm,
										dtoForm);
						double panchayatDuty = new CalculateDuty()
								.calculateJanPadDuty(instdto, dtoForm, dtoForm);
						if (instdto.getInstId() == 2012
								|| instdto.getInstId() == 2016
								|| instdto.getInstId() == 2041) {
							if (panchayatDuty > stampDuty) {
								panchayatDuty = stampDuty;
							}

							if (nagarPalikaDuty > stampDuty) {
								nagarPalikaDuty = stampDuty;
							}

						}

						double upkarDuty = 0.0;

						/*
						 * dutyArray=bo.getMunicipalDutyCalculation(dtoForm,instdto
						 * ,exedto);
						 * 
						 * 
						 * //System.out.println("NAGAR PALIKA DUTY"+dutyArray[0]+
						 * "PANCHAYAT DUTY"
						 * //+dutyArray[1]+"UPKAR DUTY"+dutyArray[2]);
						 * 
						 * 
						 * 
						 * if( dutyArray.length>=1) {
						 * nagarPalikaDuty=(dutyArray[0]);
						 * dtoForm.setNagarPalikaDuty((nagarPalikaDuty));
						 * panchayatDuty=(dutyArray[1]);
						 * dtoForm.setPanchayatDuty(dutyArray[1]);
						 * upkarDuty=(dutyArray[2]);
						 * dtoForm.setUpkarDuty(upkarDuty); }
						 */

						//System.out.println("NAGAR PALIKA DUTY"+nagarPalikaDuty
						// +"PANCHAYAT DUTY"
						// +panchayatDuty+"UPKAR DUTY"+upkarDuty);
						dtoForm.setNagarPalikaDuty((nagarPalikaDuty));
						dtoForm.setPanchayatDuty(panchayatDuty);
						upkarDuty = new CalculateDuty().calculateUpkar(eForm
								.getInstDTO().getInstId(), stampDuty);
						dtoForm.setUpkarDuty(upkarDuty);
						double total = nagarPalikaDuty + panchayatDuty
								+ upkarDuty + stampDuty;
						dtoForm.setTotal(total);

						// dtoForm.setTotal(common.getCurrencyFormat(total));

						//session.setAttribute("nagarPalikaDuty",nagarPalikaDuty
						// );
						// session.setAttribute("panchayatDuty",panchayatDuty);
						// session.setAttribute("upkarDuty",upkarDuty);
						// session.setAttribute("total",total);

						// session.setAttribute("DutyDTO", dtoForm);
						// session.setAttribute("InstrumentDTO", instdto);
						// session.setAttribute("ExemptionDTO", exedto);
						// session.setAttribute("duty", duty);

						double regFee = 0;//bo.getRegistrationFee(dtoForm,instdto
											// ,exedto);
						regFee = Double.parseDouble(new CalculateRegFee()
								.calculateRegFee(instdto, dtoForm, dtoForm));

						/*
						 * if(dtoForm.getDeedId()==1092){//lease
						 * 
						 * boolean leaseRegFee1000Exemp=false; ArrayList
						 * list1=dtoForm.getExemptionDescpList(); for(int
						 * i=0;i<list1.size();i++) { DutyCalculationDTO
						 * dto1=(DutyCalculationDTO) list1.get(i);
						 * 
						 * 
						 * if(!dto1.getExempId().equalsIgnoreCase("130")) {
						 * leaseRegFee1000Exemp=true; break;
						 * 
						 * } }
						 * 
						 * if(!leaseRegFee1000Exemp){
						 * 
						 * if(regFee>1000) { regFee=1000; }
						 * 
						 * 
						 * }
						 * 
						 * 
						 * }
						 */

						dtoForm.setRegFee(500);
						// session.setAttribute("regFee",regFee);
						if (regFee < 0) {
							regFee = 0.0;
							dtoForm.setRegFee(500);
						}
						logger.debug("Reg Fee:-" + regFee);
						double paidRegFee = 0;
						double paidStampduty = 0;
						if (dtoForm.getAlreadyPaidRegFee() != null
								&& !dtoForm.getAlreadyPaidRegFee()
										.equalsIgnoreCase("")) {
							paidRegFee = Double.parseDouble(dtoForm
									.getAlreadyPaidRegFee());
						}
						if (dtoForm.getAlreadyPaidStampDuty() != null
								&& !dtoForm.getAlreadyPaidStampDuty()
										.equalsIgnoreCase("")) {
							paidStampduty = Double.parseDouble(dtoForm
									.getAlreadyPaidStampDuty());
						}
						if (((regFee) - paidRegFee) < 0) {
							dtoForm.setPayableRegFee(0);
						} else {
							dtoForm.setPayableRegFee((regFee) - paidRegFee);
						}
						if (((total) - paidStampduty) < 0) {
							dtoForm.setPayableStampDuty(0);
						} else {
							dtoForm
									.setPayableStampDuty((total)
											- paidStampduty);
						}
						double minRegFee = bo.minRegFee(String.valueOf(dtoForm
								.getDeedId()), String.valueOf(instdto
								.getInstId()));
						double minStamp = bo.minStampDuty(String
								.valueOf(dtoForm.getDeedId()), String
								.valueOf(instdto.getInstId()));
						if (minRegFee > 0) {
							dtoForm.setMinRegFee(String.valueOf(minRegFee));
							dtoForm.setMinRegDisclaimerFlag("Y");
						} else {
							dtoForm.setMinRegFee(String.valueOf(minRegFee));
							dtoForm.setMinRegDisclaimerFlag("N");
						}
						if (minStamp > 0) {
							dtoForm.setMinStamp(String.valueOf(minStamp));
							dtoForm.setMinStampDisclaimerFlag("Y");
						} else {
							dtoForm.setMinStamp(String.valueOf(minStamp));
							dtoForm.setMinStampDisclaimerFlag("N");
						}
						if (dtoForm.getPayableStampDuty() < minStamp) {
							dtoForm.setPayableStampDuty(minStamp);
						}
						if (dtoForm.getPayableRegFee() < minRegFee) {
							dtoForm.setPayableRegFee(minRegFee);
						}

						// property wise exemption code comes here-Roopam- 4
						// April 2015

						int flag = new CalculateDuty()
								.calculatePropWiseExemptedDutiesEWS(instdto,
										dtoForm);

						if (flag == 1) {

							if ("en".equalsIgnoreCase(language)) {
								request
										.setAttribute(
												"ERROR",
												"If you want to avail EWS / LIG / MIG exemption, please select checkbox on Property valuation page – ‘Is your property is lease hold property of Housing Board’");
							} else {
								request
										.setAttribute(
												"ERROR",
												"If you want to avail EWS / LIG / MIG exemption, please select checkbox on Property valuation page – ‘Is your property is lease hold property of Housing Board’");

							}
							regFlag = false;
							eForm.getDutyCalculationDTO().setRadioResiComm3("");
							forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
							dto.setActionName(null);
							actionName = "";

						} else if (flag == 2) {

							if ("en".equalsIgnoreCase(language)) {
								request
										.setAttribute("ERROR",
												"Please select exemption belonging to either of EWS or MIG or LIG category");
							} else {
								request
										.setAttribute("ERROR",
												"Please select exemption belonging to either of EWS or MIG or LIG category");

							}
							regFlag = false;
							eForm.getDutyCalculationDTO().setRadioResiComm3("");
							forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
							dto.setActionName(null);
							actionName = "";

						} else {
							/*
							 * String exceptionlist = exedto.getExemId();
							 * System.
							 * out.println("exceptionlist  sing "+exceptionlist
							 * );
							 * System.out.println("exemptions  sing "+exemptions
							 * );
							 */
							double d = bo.getExemptionPecentage(dtoForm);
							double fd = dtoForm.getPayableStampDuty()
									- ((dtoForm.getPayableStampDuty() * d) / 100);
							dtoForm.setMinusExempStamp(((dtoForm
									.getPayableStampDuty() * d) / 100));
							double fd1 = dtoForm.getTotalPayableStamp()
									- ((dtoForm.getTotalPayableStamp() * d) / 100);
							// dtoForm.setMinusExempStampTotal(((dtoForm.
							// getTotalPayableStamp()*d)/100));
							dtoForm
									.setMinusExempStampTotal((dtoForm
											.getMinusExempStampTotal())
											+ ((dtoForm.getTotalPayableStamp() * d) / 100));

							// below for new exemption-by roopam

							/*
							 * if(dtoForm.getDeedId()==1053) {
							 * 
							 * 
							 * boolean conveyance1PercentExemption=false;
							 * ArrayList list1=dtoForm.getExemptionDescpList();
							 * for(int i=0;i<list1.size();i++) {
							 * DutyCalculationDTO dto1=(DutyCalculationDTO)
							 * list1.get(i);
							 * 
							 * 
							 * if(!dto1.getExempId().equalsIgnoreCase("129")) {
							 * conveyance1PercentExemption=true; break;
							 * 
							 * } }
							 * 
							 * if(!conveyance1PercentExemption){
							 * 
							 * stampDuty=Double.parseDouble(new
							 * CalculateDuty().calculateStampDutyCoveyance1Percent
							 * (instdto, dtoForm,dtoForm));
							 * dtoForm.setStampDuty(stampDuty);
							 * 
							 * if (stampDuty<0) { stampDuty=0.0;
							 * dtoForm.setStampDuty(stampDuty); }
							 * 
							 * 
							 * 
							 * 
							 * upkarDuty=new
							 * CalculateDuty().calculateUpkar(eForm
							 * .getInstDTO().getInstId(),stampDuty);
							 * dtoForm.setUpkarDuty(upkarDuty); double totalNew
							 * =nagarPalikaDuty+panchayatDuty+upkarDuty+
							 * stampDuty; dtoForm.setTotal(totalNew);
							 * 
							 * if(((totalNew)-paidStampduty)<0) {
							 * dtoForm.setPayableStampDuty(0); } else {
							 * dtoForm.setPayableStampDuty
							 * ((totalNew)-paidStampduty); }
							 * 
							 * fd=dtoForm.getPayableStampDuty();
							 * fd1=dtoForm.getPayableStampDuty(); }
							 * 
							 * }
							 */

							if (dtoForm.getDeedId() == 1092) {// lease

								boolean leaseRegFee1000Exemp = false;
								ArrayList list1 = dtoForm
										.getRegFeeExemptionDiscriptionList();

								if (list1 != null && list1.size() > 0) {
									for (int i = 0; i < list1.size(); i++) {
										DutyCalculationDTO dto1 = (DutyCalculationDTO) list1
												.get(i);

										if (!dto1.getExempId()
												.equalsIgnoreCase("130")) {
											leaseRegFee1000Exemp = true;
											break;

										}
									}
								} else {
									leaseRegFee1000Exemp = true;
								}

								if (!leaseRegFee1000Exemp) {

									if (dtoForm.getTotalPayableReg() > 1000) {

										dtoForm.setTotalPayableReg(1000);
										// regFee=1000;
									}

								}

							}

							double r = bo.getExemptionPecentageRegFee(dtoForm);
							double rd = dtoForm.getPayableRegFee()
									- ((dtoForm.getPayableRegFee() * r) / 100);
							dtoForm.setMinusExempReg(((dtoForm
									.getPayableRegFee() * r) / 100));
							double rd1 = dtoForm.getTotalPayableReg()
									- ((dtoForm.getTotalPayableReg() * r) / 100);
							dtoForm.setMinusExempRegTotal(((dtoForm
									.getTotalPayableReg() * r) / 100));

							dtoForm.setDutyafterExemption(fd);
							dtoForm.setDutyafterExemptionTotal(fd1);
							dtoForm.setRegFeeafterExemp(rd);
							dtoForm.setRegFeeafterExempTotal(rd1);

							String propValReqFlag = bo
									.getPropValRequiredFlag(instdto.getInstId());
							if (propValReqFlag.equalsIgnoreCase("Y")
									&& !"true".equalsIgnoreCase(dtoForm
											.getValuationIdValidate())) {

							} else {
								if (dtoForm.getTotalPayableStamp() < minStamp) {
									dtoForm.setTotalPayableStamp(minStamp);
								}
								if (dtoForm.getTotalPayableReg() < minRegFee) {
									dtoForm.setTotalPayableReg(minRegFee);
								}
							}
							if (dtoForm.getDutyafterExemption() < minStamp) {
								dtoForm.setDutyafterExemption(minStamp);
							}
							if (2058 != instdto.getInstId()) {
								if (dtoForm.getRegFeeafterExemp() < minRegFee) {
									dtoForm.setRegFeeafterExemp(minRegFee);
								}
							}
							if (dtoForm.getDutyafterExemptionTotal() < minStamp) {
								dtoForm.setDutyafterExemptionTotal(minStamp);
							}
							if (2058 != instdto.getInstId()) {
								if (dtoForm.getRegFeeafterExempTotal() < minRegFee) {
									dtoForm.setRegFeeafterExempTotal(minRegFee);
								}
							}

						}
					}
				}
				// end of changes by preeti on 13 march 2015

				// preeti change 2 on 13 march 2016

				if (CommonConstant.DUTY_CALCULATE_ACTION_efiling
						.equals(actionName)) {

					DutyCalculationBO boexemption = new DutyCalculationBO();
					DutyCalculationDTO dutyDTO = eForm.getDutyCalculationDTO();
					InstrumentsDTO instrumentDTO = eForm.getInstDTO();
					ExemptionDTO exemptionDTO = eForm.getExempDTO();
					// RegCommonForm rForm= new RegCommonForm();
					// rForm.setFromAdjudication(eForm.getFromAdjudication());

					//request.setAttribute("fromAdju",eForm.getFromAdjudication(
					// ));
					dutyDTO.setEfileFeeBeforeExemp("");
					dutyDTO.setEfileFeeAfterExemp("");
					eForm.setFlag("");
					eForm.getDutyCalculationDTO().setRadioResiComm("yes");
					logger.debug("from ADJU type"
							+ request.getAttribute("fromAdju"));
					/*
					 * if(rForm.getFromAdjudication()==1){
					 * session.setAttribute("fnName","Adjudication"); }
					 */

					logger.debug(exemptionDTO);
					int inst = instrumentDTO.getInstId();
					int deed = dutyDTO.getDeedId();
					System.out.println(exemptionDTO.getHdnExemptions());
					/*
					 * String[] exemptions = exemptionDTO.getHdnExemptions() ==
					 * null ? null :
					 * exemptionDTO.getHdnExemptions().split(", ");
					 */

					String[] exemptions;

					if (exemptionDTO.getHdnExemptions() != null
							&& !exemptionDTO.getHdnExemptions()
									.equalsIgnoreCase("")) {
						exemptions = exemptionDTO.getHdnExemptions()
								.split(", ");
					} else {
						exemptions = null;
					}

					// String[] exem = exemptionDTO.getHdnExempAmount().split("
					// ~");

					eForm.setSelectedExemptionList(boexemption
							.getExemptionList(exemptions));

					if (eForm.getSelectedExemptionList().size() <= 0) {
						eForm.setSelectedExemptionList(null);
					}

					DecimalFormat myformatter = new DecimalFormat("###.##");

					//bo.captureNonPropStampDetails(eForm.getDutyCalculationDTO(
					// ), eForm.getInstDTO(), eForm.getExempDTO());

					// dutyDTO.setBaseValueDisplay(String.valueOf(dutyDTO.
					// getBaseValue()));
					// dutyDTO.setBaseValueDisplay(String.valueOf(dutyDTO.
					// getBaseValue()));
					// dutyDTO.setBaseValueDisplay(common.
					// getCurrencyFormatBaseValue
					// (dutyDTO.getBaseValue()));//double to string
					dutyDTO.setBaseValueDisplay(dutyDTO.getBaseValue());// double
																		// to
																		// string
					// BigDecimal bigD=new
					// BigDecimal(dutyDTO.getBaseValue()).setScale
					// (20,RoundingMode.HALF_EVEN);

					// new BigDecimal(dutyDTO.getBaseValue()).setScale(7, 7);
					//dutyDTO.setBaseValueDisplay(String.valueOf(bigD.doubleValue
					// ()));
					// bigD.setScale(7, 7);

					// added by akansha by convence exemption
					if ((deed == 1053)
							&& (dto.getSelectedExempId().equals("96"))
							&& (inst == 2090)) {
						double d = bo.getExemptionPecentage(dutyDTO);
						dutyDTO.setAnnualRentDisplay(myformatter.format(dutyDTO
								.getAnnualRent()));
						//dutyDTO.setBaseValueDisplay(myformatter.format(dutyDTO
						// .getBaseValue()));
						dutyDTO.setDutyAlreadyPaidDisplay(myformatter
								.format(dutyDTO.getDutyAlreadyPaid()));
						dutyDTO.setDutyPaidDisplay(myformatter.format(dutyDTO
								.getDutyPaid()));
						dutyDTO.setDateCalculation(bo.getCurrentDate());
						dutyDTO.setTotalDisplay(myformatter.format(dutyDTO
								.getTotal()));
						dutyDTO.setNagarPalikaDutyDisplay(myformatter
								.format(dutyDTO.getNagarPalikaDuty()));
						dutyDTO.setPanchayatDutyDisplay(myformatter
								.format(dutyDTO.getPanchayatDuty()));
						dutyDTO.setUpkarDutyDisplay(myformatter.format(dutyDTO
								.getUpkarDuty()));
						dutyDTO.setStampDutyDisplay(myformatter.format(dutyDTO
								.getStampDuty()));
						dutyDTO.setRegFeeDisplay(myformatter.format(dutyDTO
								.getRegFee()));
						dutyDTO.setTotalregFeeDisplay(myformatter
								.format(dutyDTO.getTotalregFee()));
						dutyDTO.setTotalStampDutyDisplay(myformatter
								.format(dutyDTO.getTotalStampDuty() * d / 100));
						// dutyDTO.setTotalStampDutyDisplay(myformatter.format(
						// 9999));

						dutyDTO.setTotalNagarPalikaDisplay(myformatter
								.format(dutyDTO.getTotalNagarPalika()));
						// double Nagarpalika = dutyDTO.getTotalNagarPalika();
						//dutyDTO.setTotalNagarPalikaDisplay(myformatter.format(
						// 111));
						dutyDTO.setTotalPanchyatDisplay(myformatter
								.format(dutyDTO.getTotalPanchyat()));
						//dutyDTO.setTotalPanchyatDisplay(myformatter.format(3434
						// ));
						//dutyDTO.setTotalUpkarDisplay(myformatter.format(dutyDTO
						// .getTotalUpkar()));

						double stampNew = Math.ceil(Double.parseDouble((dutyDTO
								.getTotalStampDutyDisplay())));

						dutyDTO.setTotalUpkarDisplay(myformatter
								.format(stampNew * 2.5 / 100));

						double upkar = Math.ceil(Double.parseDouble(dutyDTO
								.getTotalUpkarDisplay()));

						//dutyDTO.setTotalUpkarDisplay(myformatter.format(5645))
						// ;
						dutyDTO.setEntireTotalDisplay(myformatter
								.format(dutyDTO.getEntireTotal()));
						//dutyDTO.setEntireTotalDisplay(myformatter.format(7547)
						// );
						dutyDTO.setTotalPayableStampDisplay(myformatter
								.format(dutyDTO.getTotalPayableStamp()));
						//dutyDTO.setTotalPayableStampDisplay(myformatter.format
						// (654));
						dutyDTO.setTotalPayableRegDisplay(myformatter
								.format(dutyDTO.getTotalPayableReg()));
						dutyDTO.setDutyafterExemptionDisplay(myformatter
								.format(dutyDTO.getDutyafterExemption()));
						//dutyDTO.setDutyafterExemptionDisplay(myformatter.format
						// (947));
						//dutyDTO.setDutyafterExemptionDisplayTotal(myformatter.
						// format(dutyDTO.getDutyafterExemptionTotal()));

						dutyDTO.setDutyafterExemptionDisplayTotal(myformatter
								.format(stampNew
										+ dutyDTO.getTotalNagarPalika()
										+ dutyDTO.getTotalPanchyat() + upkar));
						dutyDTO.setDutyafterExemptionTotal(Double
								.parseDouble((dutyDTO
										.getDutyafterExemptionDisplayTotal())));
						//dutyDTO.setDutyafterExemptionDisplayTotal(myformatter.
						// format(543));
						dutyDTO.setRegFeeafterExempDisplay(myformatter
								.format(dutyDTO.getRegFeeafterExemp()));
						dutyDTO.setRegFeeafterExempTotalDisplay(myformatter
								.format(dutyDTO.getRegFeeafterExempTotal()));
						dutyDTO.setPayableRegFeeDisplay(myformatter
								.format(dutyDTO.getPayableRegFee()));
						dutyDTO.setPayableDutyDisplay(myformatter
								.format(dutyDTO.getPayableStampDuty()));

					}

					else if (eForm.getExemptionFlagEfile() != null
							&& !eForm.getExemptionFlagEfile().isEmpty()
							&& !eForm.getExemptionFlagEfile().equalsIgnoreCase(
									"")) {
						dutyDTO.setEileFeeDisplay("0");
						eForm.setEfileflag("Y");
						dutyDTO.setAnnualRentDisplay(myformatter.format(dutyDTO
								.getAnnualRent()));
						//dutyDTO.setBaseValueDisplay(myformatter.format(dutyDTO
						// .getBaseValue()));
						dutyDTO.setDutyAlreadyPaidDisplay(myformatter
								.format(dutyDTO.getDutyAlreadyPaid()));
						dutyDTO.setDutyPaidDisplay(myformatter.format(dutyDTO
								.getDutyPaid()));
						dutyDTO.setDateCalculation(bo.getCurrentDate());
						dutyDTO.setTotalDisplay(myformatter.format(dutyDTO
								.getTotal()));
						dutyDTO.setNagarPalikaDutyDisplay(myformatter
								.format(dutyDTO.getNagarPalikaDuty()));
						dutyDTO.setPanchayatDutyDisplay(myformatter
								.format(dutyDTO.getPanchayatDuty()));
						dutyDTO.setUpkarDutyDisplay(myformatter.format(dutyDTO
								.getUpkarDuty()));
						dutyDTO.setStampDutyDisplay(myformatter.format(dutyDTO
								.getStampDuty()));
						dutyDTO.setRegFeeDisplay(myformatter.format(dutyDTO
								.getRegFee()));
						dutyDTO.setTotalregFeeDisplay(myformatter
								.format(dutyDTO.getTotalregFee()));
						dutyDTO.setTotalStampDutyDisplay(myformatter
								.format(dutyDTO.getTotalStampDuty()));
						dutyDTO.setTotalNagarPalikaDisplay(myformatter
								.format(dutyDTO.getTotalNagarPalika()));
						dutyDTO.setTotalPanchyatDisplay(myformatter
								.format(dutyDTO.getTotalPanchyat()));
						dutyDTO.setTotalUpkarDisplay(myformatter.format(dutyDTO
								.getTotalUpkar()));
						dutyDTO.setEntireTotalDisplay(myformatter
								.format(dutyDTO.getEntireTotal()));
						dutyDTO.setTotalPayableStampDisplay(myformatter
								.format(dutyDTO.getTotalPayableStamp()));
						dutyDTO.setTotalPayableRegDisplay(myformatter
								.format(dutyDTO.getTotalPayableReg()));
						dutyDTO.setDutyafterExemptionDisplay(myformatter
								.format(dutyDTO.getDutyafterExemption()));
						dutyDTO.setDutyafterExemptionDisplayTotal(myformatter
								.format(dutyDTO.getDutyafterExemptionTotal()));
						dutyDTO.setRegFeeafterExempDisplay(myformatter
								.format(dutyDTO.getRegFeeafterExemp()));
						dutyDTO.setRegFeeafterExempTotalDisplay(myformatter
								.format(dutyDTO.getRegFeeafterExempTotal()));
						dutyDTO.setPayableRegFeeDisplay(myformatter
								.format(dutyDTO.getPayableRegFee()));
						dutyDTO.setPayableDutyDisplay(myformatter
								.format(dutyDTO.getPayableStampDuty()));
					}

					else {
						if (eForm.getAvailExemptionFlagEfile() != null
								&& !eForm.getAvailExemptionFlagEfile()
										.isEmpty()
								&& !eForm.getAvailExemptionFlagEfile()
										.equalsIgnoreCase("")) {
							if (eForm.getAvailExemptionFlagEfile()
									.equalsIgnoreCase("Y")) {
								// dutyDTO.setEfileFeeBeforeExemp("500");
								dutyDTO.setEfileFeeAfterExempDisplay("0");
								// dutyDTO.setEfileFeeAfterExemp("0");
								// dutyDTO.setEileFeeDisplay("0");
								dutyDTO.setEileFeeDisplay("500");
								eForm.setFlag("Y");
							}
						} else {
							dutyDTO.setEileFeeDisplay("500");
							eForm.setFlag("");
						}
						dutyDTO.setAnnualRentDisplay(myformatter.format(dutyDTO
								.getAnnualRent()));
						//dutyDTO.setBaseValueDisplay(myformatter.format(dutyDTO
						// .getBaseValue()));
						dutyDTO.setDutyAlreadyPaidDisplay(myformatter
								.format(dutyDTO.getDutyAlreadyPaid()));
						dutyDTO.setDutyPaidDisplay(myformatter.format(dutyDTO
								.getDutyPaid()));
						dutyDTO.setDateCalculation(bo.getCurrentDate());
						dutyDTO.setTotalDisplay(myformatter.format(dutyDTO
								.getTotal()));
						dutyDTO.setNagarPalikaDutyDisplay(myformatter
								.format(dutyDTO.getNagarPalikaDuty()));
						dutyDTO.setPanchayatDutyDisplay(myformatter
								.format(dutyDTO.getPanchayatDuty()));
						dutyDTO.setUpkarDutyDisplay(myformatter.format(dutyDTO
								.getUpkarDuty()));
						dutyDTO.setStampDutyDisplay(myformatter.format(dutyDTO
								.getStampDuty()));
						dutyDTO.setRegFeeDisplay(myformatter.format(dutyDTO
								.getRegFee()));
						dutyDTO.setTotalregFeeDisplay(myformatter
								.format(dutyDTO.getTotalregFee()));
						dutyDTO.setTotalStampDutyDisplay(myformatter
								.format(dutyDTO.getTotalStampDuty()));
						dutyDTO.setTotalNagarPalikaDisplay(myformatter
								.format(dutyDTO.getTotalNagarPalika()));
						dutyDTO.setTotalPanchyatDisplay(myformatter
								.format(dutyDTO.getTotalPanchyat()));
						dutyDTO.setTotalUpkarDisplay(myformatter.format(dutyDTO
								.getTotalUpkar()));
						dutyDTO.setEntireTotalDisplay(myformatter
								.format(dutyDTO.getEntireTotal()));
						dutyDTO.setTotalPayableStampDisplay(myformatter
								.format(dutyDTO.getTotalPayableStamp()));
						dutyDTO.setTotalPayableRegDisplay(myformatter
								.format(dutyDTO.getTotalPayableReg()));
						dutyDTO.setDutyafterExemptionDisplay(myformatter
								.format(dutyDTO.getDutyafterExemption()));
						dutyDTO.setDutyafterExemptionDisplayTotal(myformatter
								.format(dutyDTO.getDutyafterExemptionTotal()));
						dutyDTO.setRegFeeafterExempDisplay(myformatter
								.format(dutyDTO.getRegFeeafterExemp()));
						dutyDTO.setRegFeeafterExempTotalDisplay(myformatter
								.format(dutyDTO.getRegFeeafterExempTotal()));
						dutyDTO.setPayableRegFeeDisplay(myformatter
								.format(dutyDTO.getPayableRegFee()));
						dutyDTO.setPayableDutyDisplay(myformatter
								.format(dutyDTO.getPayableStampDuty()));

					}
					logger.debug("Stamp Id" + dutyDTO.getStampId());
					request.setAttribute("stampId", dutyDTO.getStampId());
					logger.debug("Stamp Id" + request.getAttribute("stampId"));
					eForm.setDutyCalculationDTO(dutyDTO);
					eForm.setExempDTO(exemptionDTO);
					eForm.setInstDTO(instrumentDTO);
					String exedate = eForm.getSlotdate();
					String exemption = "";
					if (eForm.getAvailExemptionFlagEfile() != null
							&& !eForm.getAvailExemptionFlagEfile().isEmpty()
							&& !eForm.getAvailExemptionFlagEfile()
									.equalsIgnoreCase("")) {
						exemption = "Y";
					}
					/*
					 * else{ exemption="N"; }
					 */

					// below code for efile fee exemption
					else if (eForm.getExemptionFlagEfile() != null
							&& !eForm.getExemptionFlagEfile().isEmpty()
							&& !eForm.getExemptionFlagEfile().equalsIgnoreCase(
									"")) {
						exemption = "Y";
					} else {
						exemption = "N";
					}

					// end of code

					logger.debug("base Value:-" + dutyDTO.getBaseValue());
					logger.debug("Deed Name:-" + dutyDTO.getDeedType());
					logger.debug("Instrument Name:-"
							+ instrumentDTO.getInstType());
					logger.debug("Exemption Name:-"
							+ exemptionDTO.getHdnExemptions());

					// ADD BELOW STATEMENTS IN DEED-INSTRUMENT CONDITION
					// GET PROP_VAL_REQ_FLAG BASED IN INSTRUMENT ID HERE

					String propValReqFlag = bo.getPropValRequiredFlag(inst);

					if (propValReqFlag.equalsIgnoreCase("Y")
							&& !"true".equalsIgnoreCase(dutyDTO
									.getValuationIdValidate())) {
						request.setAttribute("deedId1", String.valueOf(eForm
								.getDutyCalculationDTO().getDeedId()));
						forwardJsp = CommonConstant.FORWARD_PROP_VAL;
						if ("Y".equalsIgnoreCase(eForm.getDutyCalculationDTO()
								.getRinPustikaCheckFlag())) {
							request.setAttribute("rinPustika", "Y");
						} else {
							request.setAttribute("rinPustika", "N");
						}
						if (eForm.getInstDTO().getInstId() == 2105
								|| eForm.getInstDTO().getInstId() == 2106
								|| eForm.getInstDTO().getInstId() == 2108
								|| eForm.getInstDTO().getInstId() == 2184) {
							request.setAttribute("singleBuyer", "Y");
						} else {
							request.setAttribute("singleBuyer", "N");
						}

						// HashMap<String,String> userValueMap=new
						// HashMap<String,String>();
						ArrayList userList = new ArrayList();
						String param[] = request
								.getParameterValues("userEnterableFieldValue");
						ArrayList list = eForm.getInstDTO()
								.getUserEnterableList();
						if (list != null) {
							int j = 0;
							for (int i = 0; i < list.size(); i++) {
								InstrumentsDTO indto = (InstrumentsDTO) list
										.get(i);
								UserEnterableDTO userDto = new UserEnterableDTO();
								String id = indto.getUserEnterableId();
								String value = "";
								if ("Y".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "Y"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getCommonUserField();
								} else if ("Y".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "N"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getInStampDutyAmount();
								} else if ("N".equalsIgnoreCase(indto
										.getInStampDutyAmountFlag())
										&& "Y"
												.equalsIgnoreCase(indto
														.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO()
											.getAsConsiderationAmount();
								} else {
									value = param[j];
									j++;
								}
								if ("Y".equalsIgnoreCase(indto
										.getAlreadyPaidRegFeeFlag())) {
									dto.setAlreadyPaidRegFee(value);
								}
								if ("Y".equalsIgnoreCase(indto
										.getAlreadyPaidStampDutyFlag())) {
									dto.setAlreadyPaidStampDuty(value);
								}
								userDto.setId(id);
								userDto.setValue(value);
								userDto.setName(indto.getUserEnterableName());
								indto.setUserEnterableFieldValue(value);
								// userValueMap.put(id,value);
								userList.add(userDto);
							}
							// dto.setUserValueMap(userValueMap);
							dto.setUserValueList(userList);
						}
						dto.setActionName(null);
						actionName = "";

					} else {
						/*
						 * if
						 * (eForm.getModuleName()!=null&&eForm.getModuleName()
						 * .equalsIgnoreCase
						 * (CommonConstant.REGISTRATION_DUTY_PAGE)) {
						 * 
						 * if(propValReqFlag.equalsIgnoreCase("Y")){
						 * 
						 * forwardJsp = CommonConstant.FORWARD_REGINIT_DISPLAY;
						 * 
						 * }else{ forwardJsp =
						 * CommonConstant.FORWARD_NONREGINIT_DISPLAY; }
						 * request.setAttribute("stampId",
						 * dutyDTO.getStampId());
						 * request.setAttribute("fromAdju"
						 * ,eForm.getFromAdjudication());
						 * dto.setActionName(null); actionName=""; } else
						 */
						// {
						if (dto.getDeedId() == 1054) {
							if (dto.getExchangeList1().size() == 0
									&& dto.getExchangeList2().size() == 0) {
								if ("en".equalsIgnoreCase(language)) {
									request
											.setAttribute("ERROR",
													"Please add property for both transactiong parties");
								} else {
									request
											.setAttribute("ERROR",
													"कृपया दोनों पार्टी के लिए संपत्ति जोड़ें|");

								}
								regFlag = false;
								eForm.getDutyCalculationDTO()
										.setRadioResiComm3("");
								forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
								dto.setActionName(null);
								actionName = "";
							} else if (dto.getExchangeList1().size() == 0) {
								if ("en".equalsIgnoreCase(language)) {
									request
											.setAttribute("ERROR",
													"Please add property for first transacting party");
								} else {
									request
											.setAttribute("ERROR",
													"कृपया प्रथम पार्टी के लिए संपत्ति जोड़ें|");

								}
								regFlag = false;
								eForm.getDutyCalculationDTO()
										.setRadioResiComm3("");
								forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
								dto.setActionName(null);
								actionName = "";
							} else if (dto.getExchangeList2().size() == 0) {
								if ("en".equalsIgnoreCase(language)) {
									request
											.setAttribute("ERROR",
													"Please add property for second transactiong party");
								} else {
									request
											.setAttribute("ERROR",
													"कृपया दूसरी  पार्टी के लिए संपत्ति जोड़ें| ");

								}
								regFlag = false;
								eForm.getDutyCalculationDTO()
										.setRadioResiComm3("");
								forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
								dto.setActionName(null);
								actionName = "";
							} else {
								bo.submitDutyDetails(dutyDTO, eForm
										.getInstDTO(), exedate, exemption);

								forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
								dto.setActionName(null);
								actionName = "";
							}
						} else if (eForm.getInstDTO().getInstId() == 2105
								|| eForm.getInstDTO().getInstId() == 2106
								|| eForm.getInstDTO().getInstId() == 2108
								|| eForm.getInstDTO().getInstId() == 2184) {
							double separtedPart = Double.parseDouble(dto
									.getNoOfSperatedPart());
							if (separtedPart < 2) {
								if ("en".equalsIgnoreCase(language)) {
									request
											.setAttribute("ERROR",
													"No of Separated Parts must be greater than equal to 2");
								} else {
									request
											.setAttribute("ERROR",
													"हिस्सों की संख्या 2 के बराबर या उससे अधिक होनी चाहिए");

								}
								regFlag = false;

							} else if (separtedPart != dto
									.getPropertyDetailsList().size()) {
								if ("en".equalsIgnoreCase(language)) {
									request
											.setAttribute("ERROR",
													"No of Separated Parts must be equal to no of Properties");
								} else {
									request
											.setAttribute("ERROR",
													"हिस्सों की संख्या संपत्तियों की संख्या के बराबर होनी चाहिए।");

								}
								regFlag = false;

							} else if (eForm.getInstDTO().getInstId() == 2105
									|| eForm.getInstDTO().getInstId() == 2106
									|| eForm.getInstDTO().getInstId() == 2108
									|| eForm.getInstDTO().getInstId() == 2184) {
								if (bo.partitionvalidation(dto
										.getPropertyDetailsList()))// change
																	// partition
																	// validation
																	// here
																	// ...Roopam
								{
									bo.submitDutyDetails(dutyDTO, eForm
											.getInstDTO(), exedate, exemption);
									String dutyId = dutyDTO.getMainDutyTxnId();
									session.setAttribute("dutyId", dutyId);
									forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
									dto.setActionName(null);
									actionName = "";
								} else {
									if ("en".equalsIgnoreCase(language)) {
										request
												.setAttribute(
														"ERROR",
														"If you intend to do the partition of only undiverted agricultural land please refer instrument 'Partition of Agricultural land' ");
									} else {
										request
												.setAttribute(
														"ERROR",
														"केवल अव्यपवर्तित कृषि भूमि के विभाजन की अनुमति नहीं है.।एक अलग  प्रकार की संपत्ति जोडे|");

									}
									regFlag = false;

								}
							} else {

								bo.submitDutyDetails(dutyDTO, eForm
										.getInstDTO(), exedate, exemption);
								forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
								dto.setActionName(null);
								actionName = "";
							}
						} else if (eForm.getInstDTO().getInstId() == 2107) {
							if (dto.getLandRevenueList().size() < 2) {
								if ("en".equalsIgnoreCase(language)) {
									request
											.setAttribute("ERROR",
													"No of Separated Parts must be greater than equal to 2");
								} else {
									request
											.setAttribute("ERROR",
													"हिस्सों की संख्या 2 के बराबर या उससे अधिक होनी चाहिए");

								}
								regFlag = false;

							} else {

								bo.submitDutyDetails(dutyDTO, eForm
										.getInstDTO(), exedate, exemption);
								forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
								dto.setActionName(null);
								actionName = "";

							}
						} else {
							bo.submitDutyDetails(dutyDTO, eForm.getInstDTO(),
									exedate, exemption);
							String dutyId1 = dutyDTO.getMainDutyTxnId();
							session.setAttribute("dutyId1", dutyId1);
							session.setAttribute("dutyId1", dutyId1);
							forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
							dto.setActionName(null);
							actionName = "";
						}
						// }

						if (eForm.getModuleName() != null
								&& eForm.getModuleName().equalsIgnoreCase(
										CommonConstant.REGISTRATION_DUTY_PAGE)
								&& regFlag == true) {
							if (propValReqFlag.equalsIgnoreCase("Y")) {
								eForm.setPropImage2(null);
								eForm.setPropImage2DocumentName(null);
								eForm.getDutyCalculationDTO().setRadioResiComm(
										"yes");
								eForm.setRadioResiComm("");
								eForm.setHiddenFlagFirst("");
								eForm.setHiddenFlag("");
								eForm.setAddFlag("");
								eForm.setFileSizeError("");
								eForm.setListDto(new ArrayList());

								if (eForm.getAvailExemptionFlagEfile() != null
										&& !eForm.getAvailExemptionFlagEfile()
												.isEmpty()
										&& !eForm.getAvailExemptionFlagEfile()
												.equalsIgnoreCase("")) {
									dutyDTO.setEfileFeeAfterExempDisplay("0");
									// dutyDTO.setEfileFeeAfterExemp("0");
									// dutyDTO.setEileFeeDisplay("0");
									dutyDTO.setEileFeeDisplay("500");
									eForm.setFlag("Y");

									// below code for temporary efiling
									Calendar currentDate = Calendar
											.getInstance();
									SimpleDateFormat formatter = new SimpleDateFormat(
											"ddMMyyyy");
									String dateNow = formatter
											.format(currentDate.getTime());

									String regTxnIdSeq = bo
											.getRegInitTxnIdSeqEfile();
									System.out.println("temp rehtxnid"
											+ regTxnIdSeq);
									String regTxnId = null;
									regTxnId = dateNow + regTxnIdSeq;
									System.out.println("number" + regTxnId);

									eForm.setHidnRegTxnId(regTxnId);
									session.setAttribute("regTxnId", regTxnId);
									// end of temporary efiling
									request.setAttribute("deedId", dutyDTO
											.getDeedId());
									eForm.setFileSizeError("");
									forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY_efiling_ZERO_DUTY;
								} else {
									forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY_efiling;
									// forwardJsp =
									// CommonConstant.FORWARD_REGINIT_DISPLAY;
									request.setAttribute("fromAdju", eForm
											.getFromAdjudication());
								}

							} else if (dutyDTO.getDeedId() == 1100
									|| dutyDTO.getDeedId() == 1101
									|| dutyDTO.getDeedId() == 1102
									|| dutyDTO.getDeedId() == 1103) {

								// below code for temporary efiling
								Calendar currentDate = Calendar.getInstance();
								SimpleDateFormat formatter = new SimpleDateFormat(
										"ddMMyyyy");
								String dateNow = formatter.format(currentDate
										.getTime());

								String regTxnIdSeq = bo
										.getRegInitTxnIdSeqEfile();
								System.out.println("temp rehtxnid"
										+ regTxnIdSeq);
								String regTxnId = null;
								regTxnId = dateNow + regTxnIdSeq;
								System.out.println("number" + regTxnId);

								eForm.setHidnRegTxnId(regTxnId);
								session.setAttribute("regTxnId", regTxnId);
								// end of temporary efiling
								request.setAttribute("deedId", dutyDTO
										.getDeedId());
								eForm.setFileSizeError("");
								forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY_efiling_ZERO_PAYMENT;

								// request.setAttribute("stampId",
								// dutyDTO.getStampId());
								request.setAttribute("fromAdju", eForm
										.getFromAdjudication());
							}

							// below code if stamp and efiling both exemption is
							// selected is by user

							else if ((eForm.getAvailExemptionFlagEfile() != null
									&& !eForm.getAvailExemptionFlagEfile()
											.isEmpty() && !eForm
									.getAvailExemptionFlagEfile()
									.equalsIgnoreCase(""))
									&& (eForm.getExemptionFlagEfile() != null
											&& !eForm.getExemptionFlagEfile()
													.isEmpty() && !eForm
											.getExemptionFlagEfile()
											.equalsIgnoreCase(""))) {
								dutyDTO.setEfileFeeAfterExempDisplay("0");
								// dutyDTO.setEfileFeeAfterExemp("0");
								// dutyDTO.setEileFeeDisplay("0");
								dutyDTO.setEileFeeDisplay("0");
								eForm.setFlag("Y");
								// below code for temporary efiling
								Calendar currentDate = Calendar.getInstance();
								SimpleDateFormat formatter = new SimpleDateFormat(
										"ddMMyyyy");
								String dateNow = formatter.format(currentDate
										.getTime());

								String regTxnIdSeq = bo
										.getRegInitTxnIdSeqEfile();
								System.out.println("temp rehtxnid"
										+ regTxnIdSeq);
								String regTxnId = null;
								regTxnId = dateNow + regTxnIdSeq;
								System.out.println("number" + regTxnId);

								eForm.setHidnRegTxnId(regTxnId);
								session.setAttribute("regTxnId", regTxnId);
								// end of temporary efiling
								request.setAttribute("deedId", dutyDTO
										.getDeedId());
								eForm.setFileSizeError("");
								forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY_efile_both_exemption;
							}
							// end of code

							// below else if if exemption is selected and
							// payable duty set as 0

							else if (eForm.getAvailExemptionFlagEfile() != null
									&& !eForm.getAvailExemptionFlagEfile()
											.isEmpty()
									&& !eForm.getAvailExemptionFlagEfile()
											.equalsIgnoreCase("")) {
								// below code for temporary efiling
								Calendar currentDate = Calendar.getInstance();
								SimpleDateFormat formatter = new SimpleDateFormat(
										"ddMMyyyy");
								String dateNow = formatter.format(currentDate
										.getTime());

								String regTxnIdSeq = bo
										.getRegInitTxnIdSeqEfile();
								System.out.println("temp rehtxnid"
										+ regTxnIdSeq);
								String regTxnId = null;
								regTxnId = dateNow + regTxnIdSeq;
								System.out.println("number" + regTxnId);

								eForm.setHidnRegTxnId(regTxnId);
								session.setAttribute("regTxnId", regTxnId);
								// end of temporary efiling
								request.setAttribute("deedId", dutyDTO
										.getDeedId());
								eForm.setFileSizeError("");
								forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY_efiling_ZERO_DUTY;
							}

							// below else if for if Payable duty is zero then no
							// need of estampCode and Physical code
							else if (dutyDTO.getDutyafterExemption() == 0) {
								// below code for temporary efiling
								Calendar currentDate = Calendar.getInstance();
								SimpleDateFormat formatter = new SimpleDateFormat(
										"ddMMyyyy");
								String dateNow = formatter.format(currentDate
										.getTime());

								String regTxnIdSeq = bo
										.getRegInitTxnIdSeqEfile();
								System.out.println("temp rehtxnid"
										+ regTxnIdSeq);
								String regTxnId = null;
								regTxnId = dateNow + regTxnIdSeq;
								System.out.println("number" + regTxnId);

								eForm.setHidnRegTxnId(regTxnId);
								session.setAttribute("regTxnId", regTxnId);
								// end of temporary efiling
								request.setAttribute("deedId", dutyDTO
										.getDeedId());
								eForm.setFileSizeError("");
								forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY_efiling_ZERO_DUTY;
							}

							// end of code
							else {
								eForm.setPropImage2(null);
								eForm.setPropImage2DocumentName(null);
								eForm.getDutyCalculationDTO().setRadioResiComm(
										"yes");
								eForm.setRadioResiComm("");
								eForm.setHiddenFlagFirst("");
								eForm.setHiddenFlag("");
								eForm.setAddFlag("");
								eForm.setFileSizeError("");
								eForm.setListDto(new ArrayList());
								forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY_efiling;
							}
							request.setAttribute("eForm", eForm);

							dto.setActionName(null);
							actionName = "";
						}
						if (eForm.getDutyCalculationDTO().getFromModule()
								.equalsIgnoreCase("eStamping")
								&& regFlag == true) {
							request.setAttribute("dutyDTO", eForm
									.getDutyCalculationDTO());
							request.setAttribute("instDTO", eForm.getInstDTO());
							forwardJsp = CommonConstant.TO_ESTAMP;
							dto.setActionName(null);
							actionName = "";
						}

					}
					if (eForm.getDutyCalculationDTO().getPropertyoutMP() != null) {
						String dutyId1 = dutyDTO.getMainDutyTxnId();
						String propertyOutMP = bo.getPropValRequiredFlag(eForm
								.getDutyCalculationDTO().getPropertyoutMP(),
								dutyId1);
					}
				}

				// end of changes on 13 march 2016

				if (CommonConstant.DUTY_CALCULATE_ACTION.equals(actionName)) {/*
																			 * 
																			 * InstrumentsDTO
																			 * instdto
																			 * =
																			 * eForm
																			 * .
																			 * getInstDTO
																			 * (
																			 * )
																			 * ;
																			 * ExemptionDTO
																			 * exedto
																			 * =
																			 * eForm
																			 * .
																			 * getExempDTO
																			 * (
																			 * )
																			 * ;
																			 * DutyCalculationDTO
																			 * dtoForm
																			 * =
																			 * eForm
																			 * .
																			 * getDutyCalculationDTO
																			 * (
																			 * )
																			 * ;
																			 * double
																			 * userStampDuty
																			 * =
																			 * 0
																			 * ;
																			 * 
																			 * 
																			 * 
																			 * //
																			 * System
																			 * .
																			 * out
																			 * .
																			 * println
																			 * (
																			 * "The exemption value is :"
																			 * +
																			 * exedto
																			 * .
																			 * getHdnExAmount
																			 * (
																			 * )
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * System
																			 * .
																			 * out
																			 * .
																			 * println
																			 * (
																			 * "The exemption value is for :"
																			 * +
																			 * exedto
																			 * .
																			 * getHdnExempAmount
																			 * (
																			 * )
																			 * )
																			 * ;
																			 * logger
																			 * .
																			 * debug
																			 * (
																			 * "The exemption NAME is :"
																			 * +
																			 * exedto
																			 * .
																			 * getHdnExemptions
																			 * (
																			 * )
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * System
																			 * .
																			 * out
																			 * .
																			 * println
																			 * (
																			 * "The exemption value is :"
																			 * +
																			 * exedto
																			 * .
																			 * getExemType
																			 * (
																			 * )
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * String
																			 * param
																			 * [
																			 * ]
																			 * =
																			 * request
																			 * .
																			 * getParameterValues
																			 * (
																			 * "userEnterableFieldValue"
																			 * )
																			 * ;
																			 * ArrayList
																			 * userList
																			 * =
																			 * new
																			 * ArrayList
																			 * (
																			 * )
																			 * ;
																			 * ArrayList
																			 * list
																			 * =
																			 * eForm
																			 * .
																			 * getInstDTO
																			 * (
																			 * )
																			 * .
																			 * getUserEnterableList
																			 * (
																			 * )
																			 * ;
																			 * if
																			 * (
																			 * list
																			 * !=
																			 * null
																			 * )
																			 * {
																			 * int
																			 * j
																			 * =
																			 * 0
																			 * ;
																			 * for
																			 * (
																			 * int
																			 * i
																			 * =
																			 * 0
																			 * ;
																			 * i
																			 * <
																			 * list
																			 * .
																			 * size
																			 * (
																			 * )
																			 * ;
																			 * i
																			 * ++
																			 * )
																			 * {
																			 * InstrumentsDTO
																			 * indto
																			 * =
																			 * (
																			 * InstrumentsDTO
																			 * )
																			 * list
																			 * .
																			 * get
																			 * (
																			 * i
																			 * )
																			 * ;
																			 * UserEnterableDTO
																			 * userDto
																			 * =
																			 * new
																			 * UserEnterableDTO
																			 * (
																			 * )
																			 * ;
																			 * String
																			 * id
																			 * =
																			 * indto
																			 * .
																			 * getUserEnterableId
																			 * (
																			 * )
																			 * ;
																			 * String
																			 * value
																			 * =
																			 * ""
																			 * ;
																			 * 
																			 * 
																			 * if(
																			 * "Y"
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * indto
																			 * .
																			 * getInStampDutyAmountFlag
																			 * (
																			 * )
																			 * )
																			 * &&
																			 * "Y"
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * indto
																			 * .
																			 * getAsConsiderationAmountFlag
																			 * (
																			 * )
																			 * )
																			 * )
																			 * {
																			 * value
																			 * =
																			 * eForm
																			 * .
																			 * getInstDTO
																			 * (
																			 * )
																			 * .
																			 * getCommonUserField
																			 * (
																			 * )
																			 * ;
																			 * }
																			 * else
																			 * if
																			 * (
																			 * "Y"
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * indto
																			 * .
																			 * getInStampDutyAmountFlag
																			 * (
																			 * )
																			 * )
																			 * &&
																			 * "N"
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * indto
																			 * .
																			 * getAsConsiderationAmountFlag
																			 * (
																			 * )
																			 * )
																			 * )
																			 * {
																			 * value
																			 * =
																			 * eForm
																			 * .
																			 * getInstDTO
																			 * (
																			 * )
																			 * .
																			 * getInStampDutyAmount
																			 * (
																			 * )
																			 * ;
																			 * 
																			 * }
																			 * else
																			 * if
																			 * (
																			 * "N"
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * indto
																			 * .
																			 * getInStampDutyAmountFlag
																			 * (
																			 * )
																			 * )
																			 * &&
																			 * "Y"
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * indto
																			 * .
																			 * getAsConsiderationAmountFlag
																			 * (
																			 * )
																			 * )
																			 * )
																			 * {
																			 * value
																			 * =
																			 * eForm
																			 * .
																			 * getInstDTO
																			 * (
																			 * )
																			 * .
																			 * getAsConsiderationAmount
																			 * (
																			 * )
																			 * ;
																			 * }
																			 * else
																			 * {
																			 * value
																			 * =
																			 * param
																			 * [
																			 * j
																			 * ]
																			 * ;
																			 * j
																			 * ++
																			 * ;
																			 * }
																			 * if
																			 * (
																			 * "Y"
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * indto
																			 * .
																			 * getAlreadyPaidRegFeeFlag
																			 * (
																			 * )
																			 * )
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setAlreadyPaidRegFee
																			 * (
																			 * value
																			 * )
																			 * ;
																			 * }
																			 * if
																			 * (
																			 * "Y"
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * indto
																			 * .
																			 * getAlreadyPaidStampDutyFlag
																			 * (
																			 * )
																			 * )
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setAlreadyPaidStampDuty
																			 * (
																			 * value
																			 * )
																			 * ;
																			 * }
																			 * userDto
																			 * .
																			 * setId
																			 * (
																			 * id
																			 * )
																			 * ;
																			 * userDto
																			 * .
																			 * setValue
																			 * (
																			 * value
																			 * )
																			 * ;
																			 * userDto
																			 * .
																			 * setName
																			 * (
																			 * indto
																			 * .
																			 * getUserEnterableName
																			 * (
																			 * )
																			 * )
																			 * ;
																			 * indto
																			 * .
																			 * setUserEnterableFieldValue
																			 * (
																			 * ""
																			 * )
																			 * ;
																			 * userList
																			 * .
																			 * add
																			 * (
																			 * userDto
																			 * )
																			 * ;
																			 * }
																			 * /
																			 * /
																			 * dto1
																			 * .
																			 * setUserValueMap
																			 * (
																			 * userValueMap
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setUserValueList
																			 * (
																			 * userList
																			 * )
																			 * ;
																			 * }
																			 * 
																			 * String
																			 * [
																			 * ]
																			 * exemptions
																			 * =
																			 * exedto
																			 * .
																			 * getHdnExemptions
																			 * (
																			 * )
																			 * ==
																			 * null
																			 * ?
																			 * null
																			 * :
																			 * exedto
																			 * .
																			 * getHdnExemptions
																			 * (
																			 * )
																			 * .
																			 * split
																			 * (
																			 * "~"
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * 
																			 * 
																			 * 
																			 * //
																			 * added
																			 * by
																			 * akansha
																			 * 
																			 * 
																			 * if(
																			 * instdto
																			 * .
																			 * getInstId
																			 * (
																			 * )
																			 * ==
																			 * 2253
																			 * )
																			 * {
																			 * userStampDuty
																			 * =
																			 * Double
																			 * .
																			 * parseDouble
																			 * (
																			 * eForm
																			 * .
																			 * getInstDTO
																			 * (
																			 * )
																			 * .
																			 * getInStampDutyAmount
																			 * (
																			 * )
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setStampDuty
																			 * (
																			 * userStampDuty
																			 * )
																			 * ;
																			 * 
																			 * double
																			 * stampDuty
																			 * =
																			 * userStampDuty
																			 * ;
																			 * /
																			 * /
																			 * bo
																			 * .
																			 * getDutyCalculation
																			 * (
																			 * dtoForm
																			 * ,
																			 * instdto
																			 * ,
																			 * exedto
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * dtoForm
																			 * .
																			 * setStampDuty
																			 * (
																			 * stampDuty
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * 
																			 * double
																			 * dutyArray
																			 * [
																			 * ]
																			 * =
																			 * new
																			 * double
																			 * [
																			 * 3
																			 * ]
																			 * ;
																			 * double
																			 * nagarPalikaDuty
																			 * =
																			 * new
																			 * CalculateDuty
																			 * (
																			 * )
																			 * .
																			 * calculateMuncipalDuty
																			 * (
																			 * instdto
																			 * ,
																			 * dtoForm
																			 * ,
																			 * dtoForm
																			 * )
																			 * ;
																			 * double
																			 * panchayatDuty
																			 * =
																			 * new
																			 * CalculateDuty
																			 * (
																			 * )
																			 * .
																			 * calculateJanPadDuty
																			 * (
																			 * instdto
																			 * ,
																			 * dtoForm
																			 * ,
																			 * dtoForm
																			 * )
																			 * ;
																			 * 
																			 * double
																			 * upkarDuty
																			 * =
																			 * 0.0
																			 * ;
																			 * 
																			 * 
																			 * dtoForm
																			 * .
																			 * setNagarPalikaDuty
																			 * (
																			 * (
																			 * nagarPalikaDuty
																			 * )
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setPanchayatDuty
																			 * (
																			 * panchayatDuty
																			 * )
																			 * ;
																			 * upkarDuty
																			 * =
																			 * new
																			 * CalculateDuty
																			 * (
																			 * )
																			 * .
																			 * calculateUpkar
																			 * (
																			 * eForm
																			 * .
																			 * getInstDTO
																			 * (
																			 * )
																			 * .
																			 * getInstId
																			 * (
																			 * )
																			 * ,
																			 * stampDuty
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setUpkarDuty
																			 * (
																			 * upkarDuty
																			 * )
																			 * ;
																			 * double
																			 * total
																			 * =
																			 * nagarPalikaDuty
																			 * +
																			 * panchayatDuty
																			 * +
																			 * upkarDuty
																			 * +
																			 * stampDuty
																			 * ;
																			 * dtoForm
																			 * .
																			 * setTotal
																			 * (
																			 * total
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * 
																			 * 
																			 * 
																			 * double
																			 * regFee
																			 * =
																			 * 0
																			 * ;
																			 * /
																			 * /
																			 * bo
																			 * .
																			 * getRegistrationFee
																			 * (
																			 * dtoForm
																			 * ,
																			 * instdto
																			 * ,
																			 * exedto
																			 * )
																			 * ;
																			 * regFee
																			 * =
																			 * Double
																			 * .
																			 * parseDouble
																			 * (
																			 * new
																			 * CalculateRegFee
																			 * (
																			 * )
																			 * .
																			 * calculateRegFee
																			 * (
																			 * instdto
																			 * ,
																			 * dtoForm
																			 * ,
																			 * dtoForm
																			 * )
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * dtoForm
																			 * .
																			 * setRegFee
																			 * (
																			 * regFee
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * session
																			 * .
																			 * setAttribute
																			 * (
																			 * "regFee"
																			 * ,
																			 * regFee
																			 * )
																			 * ;
																			 * 
																			 * logger
																			 * .
																			 * debug
																			 * (
																			 * "Reg Fee:-"
																			 * +
																			 * regFee
																			 * )
																			 * ;
																			 * double
																			 * paidRegFee
																			 * =
																			 * 0
																			 * ;
																			 * double
																			 * paidStampduty
																			 * =
																			 * 0
																			 * ;
																			 * if
																			 * (
																			 * dtoForm
																			 * .
																			 * getAlreadyPaidRegFee
																			 * (
																			 * )
																			 * !=
																			 * null
																			 * &&
																			 * !
																			 * dtoForm
																			 * .
																			 * getAlreadyPaidRegFee
																			 * (
																			 * )
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * ""
																			 * )
																			 * )
																			 * {
																			 * paidRegFee
																			 * =
																			 * Double
																			 * .
																			 * parseDouble
																			 * (
																			 * dtoForm
																			 * .
																			 * getAlreadyPaidRegFee
																			 * (
																			 * )
																			 * )
																			 * ;
																			 * }
																			 * if
																			 * (
																			 * dtoForm
																			 * .
																			 * getAlreadyPaidStampDuty
																			 * (
																			 * )
																			 * !=
																			 * null
																			 * &&
																			 * !
																			 * dtoForm
																			 * .
																			 * getAlreadyPaidStampDuty
																			 * (
																			 * )
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * ""
																			 * )
																			 * )
																			 * {
																			 * paidStampduty
																			 * =
																			 * Double
																			 * .
																			 * parseDouble
																			 * (
																			 * dtoForm
																			 * .
																			 * getAlreadyPaidStampDuty
																			 * (
																			 * )
																			 * )
																			 * ;
																			 * }
																			 * if
																			 * (
																			 * (
																			 * (
																			 * regFee
																			 * )
																			 * -
																			 * paidRegFee
																			 * )
																			 * <
																			 * 0
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setPayableRegFee
																			 * (
																			 * 0
																			 * )
																			 * ;
																			 * }
																			 * else
																			 * {
																			 * dtoForm
																			 * .
																			 * setPayableRegFee
																			 * (
																			 * (
																			 * regFee
																			 * )
																			 * -
																			 * paidRegFee
																			 * )
																			 * ;
																			 * }
																			 * if
																			 * (
																			 * (
																			 * (
																			 * total
																			 * )
																			 * -
																			 * paidStampduty
																			 * )
																			 * <
																			 * 0
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setPayableStampDuty
																			 * (
																			 * 0
																			 * )
																			 * ;
																			 * }
																			 * else
																			 * {
																			 * dtoForm
																			 * .
																			 * setPayableStampDuty
																			 * (
																			 * (
																			 * total
																			 * )
																			 * -
																			 * paidStampduty
																			 * )
																			 * ;
																			 * }
																			 * double
																			 * minRegFee
																			 * =
																			 * bo
																			 * .
																			 * minRegFee
																			 * (
																			 * String
																			 * .
																			 * valueOf
																			 * (
																			 * dtoForm
																			 * .
																			 * getDeedId
																			 * (
																			 * )
																			 * )
																			 * ,
																			 * String
																			 * .
																			 * valueOf
																			 * (
																			 * instdto
																			 * .
																			 * getInstId
																			 * (
																			 * )
																			 * )
																			 * )
																			 * ;
																			 * double
																			 * minStamp
																			 * =
																			 * bo
																			 * .
																			 * minStampDuty
																			 * (
																			 * String
																			 * .
																			 * valueOf
																			 * (
																			 * dtoForm
																			 * .
																			 * getDeedId
																			 * (
																			 * )
																			 * )
																			 * ,
																			 * String
																			 * .
																			 * valueOf
																			 * (
																			 * instdto
																			 * .
																			 * getInstId
																			 * (
																			 * )
																			 * )
																			 * )
																			 * ;
																			 * if
																			 * (
																			 * minRegFee
																			 * >
																			 * 0
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setMinRegFee
																			 * (
																			 * String
																			 * .
																			 * valueOf
																			 * (
																			 * minRegFee
																			 * )
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setMinRegDisclaimerFlag
																			 * (
																			 * "Y"
																			 * )
																			 * ;
																			 * }
																			 * else
																			 * {
																			 * dtoForm
																			 * .
																			 * setMinRegFee
																			 * (
																			 * String
																			 * .
																			 * valueOf
																			 * (
																			 * minRegFee
																			 * )
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setMinRegDisclaimerFlag
																			 * (
																			 * "N"
																			 * )
																			 * ;
																			 * }
																			 * if
																			 * (
																			 * minStamp
																			 * >
																			 * 0
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setMinStamp
																			 * (
																			 * String
																			 * .
																			 * valueOf
																			 * (
																			 * minStamp
																			 * )
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setMinStampDisclaimerFlag
																			 * (
																			 * "Y"
																			 * )
																			 * ;
																			 * }
																			 * else
																			 * {
																			 * dtoForm
																			 * .
																			 * setMinStamp
																			 * (
																			 * String
																			 * .
																			 * valueOf
																			 * (
																			 * minStamp
																			 * )
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setMinStampDisclaimerFlag
																			 * (
																			 * "N"
																			 * )
																			 * ;
																			 * }
																			 * if
																			 * (
																			 * dtoForm
																			 * .
																			 * getPayableStampDuty
																			 * (
																			 * )
																			 * <
																			 * minStamp
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setPayableStampDuty
																			 * (
																			 * minStamp
																			 * )
																			 * ;
																			 * }
																			 * if
																			 * (
																			 * dtoForm
																			 * .
																			 * getPayableRegFee
																			 * (
																			 * )
																			 * <
																			 * minRegFee
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setPayableRegFee
																			 * (
																			 * minRegFee
																			 * )
																			 * ;
																			 * }
																			 * 
																			 * 
																			 * 
																			 * //
																			 * property
																			 * wise
																			 * exemption
																			 * code
																			 * comes
																			 * here
																			 * -
																			 * Roopam
																			 * -
																			 * 4
																			 * April
																			 * 2015
																			 * 
																			 * 
																			 * 
																			 * int
																			 * flag
																			 * =
																			 * new
																			 * CalculateDuty
																			 * (
																			 * )
																			 * .
																			 * calculatePropWiseExemptedDutiesEWS
																			 * (
																			 * instdto
																			 * ,
																			 * dtoForm
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * if(
																			 * flag
																			 * ==
																			 * 1
																			 * )
																			 * {
																			 * 
																			 * 
																			 * if(
																			 * "en"
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * language
																			 * )
																			 * )
																			 * {
																			 * request
																			 * .
																			 * setAttribute
																			 * (
																			 * "ERROR"
																			 * ,
																			 * "If you want to avail EWS / LIG / MIG exemption, please select checkbox on Property valuation page – ‘Is your property is lease hold property of Housing Board’"
																			 * )
																			 * ;
																			 * }
																			 * else
																			 * {
																			 * request
																			 * .
																			 * setAttribute
																			 * (
																			 * "ERROR"
																			 * ,
																			 * "If you want to avail EWS / LIG / MIG exemption, please select checkbox on Property valuation page – ‘Is your property is lease hold property of Housing Board’"
																			 * )
																			 * ;
																			 * 
																			 * }
																			 * regFlag
																			 * =
																			 * false
																			 * ;
																			 * forwardJsp
																			 * =
																			 * CommonConstant
																			 * .
																			 * FORWARD_PAGE_NEXT
																			 * ;
																			 * dto
																			 * .
																			 * setActionName
																			 * (
																			 * null
																			 * )
																			 * ;
																			 * actionName
																			 * =
																			 * ""
																			 * ;
																			 * 
																			 * 
																			 * }else
																			 * if
																			 * (
																			 * flag
																			 * ==
																			 * 2
																			 * )
																			 * {
																			 * 
																			 * 
																			 * 
																			 * if(
																			 * "en"
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * language
																			 * )
																			 * )
																			 * {
																			 * request
																			 * .
																			 * setAttribute
																			 * (
																			 * "ERROR"
																			 * ,
																			 * "Please select exemption belonging to either of EWS or MIG or LIG category"
																			 * )
																			 * ;
																			 * }
																			 * else
																			 * {
																			 * request
																			 * .
																			 * setAttribute
																			 * (
																			 * "ERROR"
																			 * ,
																			 * "Please select exemption belonging to either of EWS or MIG or LIG category"
																			 * )
																			 * ;
																			 * 
																			 * }
																			 * regFlag
																			 * =
																			 * false
																			 * ;
																			 * forwardJsp
																			 * =
																			 * CommonConstant
																			 * .
																			 * FORWARD_PAGE_NEXT
																			 * ;
																			 * dto
																			 * .
																			 * setActionName
																			 * (
																			 * null
																			 * )
																			 * ;
																			 * actionName
																			 * =
																			 * ""
																			 * ;
																			 * 
																			 * 
																			 * }
																			 * else
																			 * {
																			 * double
																			 * d
																			 * =
																			 * bo
																			 * .
																			 * getExemptionPecentage
																			 * (
																			 * dtoForm
																			 * )
																			 * ;
																			 * double
																			 * fd
																			 * =
																			 * dtoForm
																			 * .
																			 * getPayableStampDuty
																			 * (
																			 * )
																			 * -
																			 * (
																			 * (
																			 * dtoForm
																			 * .
																			 * getPayableStampDuty
																			 * (
																			 * )
																			 * d
																			 * )
																			 * /
																			 * 100
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setMinusExempStamp
																			 * (
																			 * (
																			 * (
																			 * dtoForm
																			 * .
																			 * getPayableStampDuty
																			 * (
																			 * )
																			 * d
																			 * )
																			 * /
																			 * 100
																			 * )
																			 * )
																			 * ;
																			 * double
																			 * fd1
																			 * =
																			 * dtoForm
																			 * .
																			 * getTotalPayableStamp
																			 * (
																			 * )
																			 * -
																			 * (
																			 * (
																			 * dtoForm
																			 * .
																			 * getTotalPayableStamp
																			 * (
																			 * )
																			 * d
																			 * )
																			 * /
																			 * 100
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * dtoForm
																			 * .
																			 * setMinusExempStampTotal
																			 * (
																			 * (
																			 * (
																			 * dtoForm
																			 * .
																			 * getTotalPayableStamp
																			 * (
																			 * )
																			 * d
																			 * )
																			 * /
																			 * 100
																			 * )
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setMinusExempStampTotal
																			 * (
																			 * (
																			 * dtoForm
																			 * .
																			 * getMinusExempStampTotal
																			 * (
																			 * )
																			 * )
																			 * +
																			 * (
																			 * (
																			 * dtoForm
																			 * .
																			 * getTotalPayableStamp
																			 * (
																			 * )
																			 * d
																			 * )
																			 * /
																			 * 100
																			 * )
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * 
																			 * 
																			 * 
																			 * 
																			 * if(
																			 * dtoForm
																			 * .
																			 * getDeedId
																			 * (
																			 * )
																			 * ==
																			 * 1092
																			 * )
																			 * {
																			 * /
																			 * /
																			 * lease
																			 * 
																			 * boolean
																			 * leaseRegFee1000Exemp
																			 * =
																			 * false
																			 * ;
																			 * ArrayList
																			 * list1
																			 * =
																			 * dtoForm
																			 * .
																			 * getRegFeeExemptionDiscriptionList
																			 * (
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * if(
																			 * list1
																			 * !=
																			 * null
																			 * &&
																			 * list1
																			 * .
																			 * size
																			 * (
																			 * )
																			 * >
																			 * 0
																			 * )
																			 * {
																			 * for
																			 * (
																			 * int
																			 * i
																			 * =
																			 * 0
																			 * ;
																			 * i
																			 * <
																			 * list1
																			 * .
																			 * size
																			 * (
																			 * )
																			 * ;
																			 * i
																			 * ++
																			 * )
																			 * {
																			 * DutyCalculationDTO
																			 * dto1
																			 * =
																			 * (
																			 * DutyCalculationDTO
																			 * )
																			 * list1
																			 * .
																			 * get
																			 * (
																			 * i
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * 
																			 * if(
																			 * !
																			 * dto1
																			 * .
																			 * getExempId
																			 * (
																			 * )
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * "130"
																			 * )
																			 * )
																			 * {
																			 * leaseRegFee1000Exemp
																			 * =
																			 * true
																			 * ;
																			 * break
																			 * ;
																			 * 
																			 * }
																			 * }
																			 * }
																			 * else
																			 * {
																			 * leaseRegFee1000Exemp
																			 * =
																			 * true
																			 * ;
																			 * }
																			 * 
																			 * 
																			 * if(
																			 * !
																			 * leaseRegFee1000Exemp
																			 * )
																			 * {
																			 * 
																			 * 
																			 * if(
																			 * dtoForm
																			 * .
																			 * getTotalPayableReg
																			 * (
																			 * )
																			 * >
																			 * 1000
																			 * )
																			 * {
																			 * 
																			 * dtoForm
																			 * .
																			 * setTotalPayableReg
																			 * (
																			 * 1000
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * regFee
																			 * =
																			 * 1000
																			 * ;
																			 * }
																			 * 
																			 * 
																			 * }
																			 * 
																			 * 
																			 * }
																			 * String
																			 * exceptionlist
																			 * =
																			 * exedto
																			 * .
																			 * getExemId
																			 * (
																			 * )
																			 * ;
																			 * System
																			 * .
																			 * out
																			 * .
																			 * println
																			 * (
																			 * "exceptionlist cons  "
																			 * +
																			 * exceptionlist
																			 * )
																			 * ;
																			 * System
																			 * .
																			 * out
																			 * .
																			 * println
																			 * (
																			 * "exemptions cons  "
																			 * +
																			 * exemptions
																			 * )
																			 * ;
																			 * 
																			 * double
																			 * r
																			 * =
																			 * bo
																			 * .
																			 * getExemptionPecentageRegFee
																			 * (
																			 * dtoForm
																			 * )
																			 * ;
																			 * double
																			 * rd
																			 * =
																			 * dtoForm
																			 * .
																			 * getPayableRegFee
																			 * (
																			 * )
																			 * -
																			 * (
																			 * (
																			 * dtoForm
																			 * .
																			 * getPayableRegFee
																			 * (
																			 * )
																			 * r
																			 * )
																			 * /
																			 * 100
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setMinusExempReg
																			 * (
																			 * (
																			 * (
																			 * dtoForm
																			 * .
																			 * getPayableRegFee
																			 * (
																			 * )
																			 * r
																			 * )
																			 * /
																			 * 100
																			 * )
																			 * )
																			 * ;
																			 * double
																			 * rd1
																			 * =
																			 * dtoForm
																			 * .
																			 * getTotalPayableReg
																			 * (
																			 * )
																			 * -
																			 * (
																			 * (
																			 * dtoForm
																			 * .
																			 * getTotalPayableReg
																			 * (
																			 * )
																			 * r
																			 * )
																			 * /
																			 * 100
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setMinusExempRegTotal
																			 * (
																			 * (
																			 * (
																			 * dtoForm
																			 * .
																			 * getTotalPayableReg
																			 * (
																			 * )
																			 * r
																			 * )
																			 * /
																			 * 100
																			 * )
																			 * )
																			 * ;
																			 * 
																			 * dtoForm
																			 * .
																			 * setDutyafterExemption
																			 * (
																			 * fd
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setDutyafterExemptionTotal
																			 * (
																			 * fd1
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setRegFeeafterExemp
																			 * (
																			 * rd
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setRegFeeafterExempTotal
																			 * (
																			 * rd1
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * //
																			 * System
																			 * .
																			 * out
																			 * .
																			 * println
																			 * (
																			 * "hdghjdhgsadhasd"
																			 * )
																			 * ;
																			 * 
																			 * String
																			 * propValReqFlag
																			 * =
																			 * bo
																			 * .
																			 * getPropValRequiredFlag
																			 * (
																			 * instdto
																			 * .
																			 * getInstId
																			 * (
																			 * )
																			 * )
																			 * ;
																			 * if
																			 * (
																			 * propValReqFlag
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * "Y"
																			 * )
																			 * &&
																			 * !
																			 * "true"
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * dtoForm
																			 * .
																			 * getValuationIdValidate
																			 * (
																			 * )
																			 * )
																			 * )
																			 * {
																			 * 
																			 * }
																			 * else
																			 * {
																			 * if
																			 * (
																			 * dtoForm
																			 * .
																			 * getTotalPayableStamp
																			 * (
																			 * )
																			 * <
																			 * minStamp
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setTotalPayableStamp
																			 * (
																			 * minStamp
																			 * )
																			 * ;
																			 * }
																			 * if
																			 * (
																			 * dtoForm
																			 * .
																			 * getTotalPayableReg
																			 * (
																			 * )
																			 * <
																			 * minRegFee
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setTotalPayableReg
																			 * (
																			 * minRegFee
																			 * )
																			 * ;
																			 * }
																			 * }
																			 * if
																			 * (
																			 * dtoForm
																			 * .
																			 * getDutyafterExemption
																			 * (
																			 * )
																			 * <
																			 * minStamp
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setDutyafterExemption
																			 * (
																			 * minStamp
																			 * )
																			 * ;
																			 * }
																			 * if
																			 * (
																			 * dtoForm
																			 * .
																			 * getRegFeeafterExemp
																			 * (
																			 * )
																			 * <
																			 * minRegFee
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setRegFeeafterExemp
																			 * (
																			 * minRegFee
																			 * )
																			 * ;
																			 * }
																			 * 
																			 * 
																			 * if(
																			 * dtoForm
																			 * .
																			 * getDutyafterExemptionTotal
																			 * (
																			 * )
																			 * <
																			 * minStamp
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setDutyafterExemptionTotal
																			 * (
																			 * minStamp
																			 * )
																			 * ;
																			 * }
																			 * if
																			 * (
																			 * dtoForm
																			 * .
																			 * getRegFeeafterExempTotal
																			 * (
																			 * )
																			 * <
																			 * minRegFee
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setRegFeeafterExempTotal
																			 * (
																			 * minRegFee
																			 * )
																			 * ;
																			 * }
																			 * 
																			 * 
																			 * }
																			 * 
																			 * }
																			 * 
																			 * 
																			 * else{
																			 * double
																			 * stampDuty
																			 * =
																			 * 0
																			 * ;
																			 * /
																			 * /
																			 * bo
																			 * .
																			 * getDutyCalculation
																			 * (
																			 * dtoForm
																			 * ,
																			 * instdto
																			 * ,
																			 * exedto
																			 * )
																			 * ;
																			 * 
																			 * stampDuty
																			 * =
																			 * Double
																			 * .
																			 * parseDouble
																			 * (
																			 * new
																			 * CalculateDuty
																			 * (
																			 * )
																			 * .
																			 * calculateStampDuty
																			 * (
																			 * instdto
																			 * ,
																			 * dtoForm
																			 * ,
																			 * dtoForm
																			 * )
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setStampDuty
																			 * (
																			 * stampDuty
																			 * )
																			 * ;
																			 * 
																			 * if
																			 * (
																			 * stampDuty
																			 * <
																			 * 0
																			 * )
																			 * {
																			 * stampDuty
																			 * =
																			 * 0.0
																			 * ;
																			 * dtoForm
																			 * .
																			 * setStampDuty
																			 * (
																			 * stampDuty
																			 * )
																			 * ;
																			 * }
																			 * 
																			 * double
																			 * dutyArray
																			 * [
																			 * ]
																			 * =
																			 * new
																			 * double
																			 * [
																			 * 3
																			 * ]
																			 * ;
																			 * double
																			 * nagarPalikaDuty
																			 * =
																			 * new
																			 * CalculateDuty
																			 * (
																			 * )
																			 * .
																			 * calculateMuncipalDuty
																			 * (
																			 * instdto
																			 * ,
																			 * dtoForm
																			 * ,
																			 * dtoForm
																			 * )
																			 * ;
																			 * double
																			 * panchayatDuty
																			 * =
																			 * new
																			 * CalculateDuty
																			 * (
																			 * )
																			 * .
																			 * calculateJanPadDuty
																			 * (
																			 * instdto
																			 * ,
																			 * dtoForm
																			 * ,
																			 * dtoForm
																			 * )
																			 * ;
																			 * if
																			 * (
																			 * instdto
																			 * .
																			 * getInstId
																			 * (
																			 * )
																			 * ==
																			 * 2012
																			 * ||
																			 * instdto
																			 * .
																			 * getInstId
																			 * (
																			 * )
																			 * ==
																			 * 2016
																			 * ||
																			 * instdto
																			 * .
																			 * getInstId
																			 * (
																			 * )
																			 * ==
																			 * 2041
																			 * )
																			 * {
																			 * if
																			 * (
																			 * panchayatDuty
																			 * >
																			 * stampDuty
																			 * )
																			 * {
																			 * panchayatDuty
																			 * =
																			 * stampDuty
																			 * ;
																			 * }
																			 * 
																			 * 
																			 * 
																			 * if(
																			 * nagarPalikaDuty
																			 * >
																			 * stampDuty
																			 * )
																			 * {
																			 * nagarPalikaDuty
																			 * =
																			 * stampDuty
																			 * ;
																			 * }
																			 * 
																			 * }
																			 * 
																			 * double
																			 * upkarDuty
																			 * =
																			 * 0.0
																			 * ;
																			 * 
																			 * dutyArray
																			 * =
																			 * bo
																			 * .
																			 * getMunicipalDutyCalculation
																			 * (
																			 * dtoForm
																			 * ,
																			 * instdto
																			 * ,
																			 * exedto
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * //
																			 * System
																			 * .
																			 * out
																			 * .
																			 * println
																			 * (
																			 * "NAGAR PALIKA DUTY"
																			 * +
																			 * dutyArray
																			 * [
																			 * 0
																			 * ]
																			 * +
																			 * "PANCHAYAT DUTY"
																			 * /
																			 * /
																			 * +
																			 * dutyArray
																			 * [
																			 * 1
																			 * ]
																			 * +
																			 * "UPKAR DUTY"
																			 * +
																			 * dutyArray
																			 * [
																			 * 2
																			 * ]
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * 
																			 * 
																			 * if(
																			 * dutyArray
																			 * .
																			 * length
																			 * >=
																			 * 1
																			 * )
																			 * {
																			 * nagarPalikaDuty
																			 * =
																			 * (
																			 * dutyArray
																			 * [
																			 * 0
																			 * ]
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setNagarPalikaDuty
																			 * (
																			 * (
																			 * nagarPalikaDuty
																			 * )
																			 * )
																			 * ;
																			 * panchayatDuty
																			 * =
																			 * (
																			 * dutyArray
																			 * [
																			 * 1
																			 * ]
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setPanchayatDuty
																			 * (
																			 * dutyArray
																			 * [
																			 * 1
																			 * ]
																			 * )
																			 * ;
																			 * upkarDuty
																			 * =
																			 * (
																			 * dutyArray
																			 * [
																			 * 2
																			 * ]
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setUpkarDuty
																			 * (
																			 * upkarDuty
																			 * )
																			 * ;
																			 * }
																			 * 
																			 * 
																			 * 
																			 * 
																			 * 
																			 * //
																			 * System
																			 * .
																			 * out
																			 * .
																			 * println
																			 * (
																			 * "NAGAR PALIKA DUTY"
																			 * +
																			 * nagarPalikaDuty
																			 * +
																			 * "PANCHAYAT DUTY"
																			 * /
																			 * /
																			 * +
																			 * panchayatDuty
																			 * +
																			 * "UPKAR DUTY"
																			 * +
																			 * upkarDuty
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setNagarPalikaDuty
																			 * (
																			 * (
																			 * nagarPalikaDuty
																			 * )
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setPanchayatDuty
																			 * (
																			 * panchayatDuty
																			 * )
																			 * ;
																			 * upkarDuty
																			 * =
																			 * new
																			 * CalculateDuty
																			 * (
																			 * )
																			 * .
																			 * calculateUpkar
																			 * (
																			 * eForm
																			 * .
																			 * getInstDTO
																			 * (
																			 * )
																			 * .
																			 * getInstId
																			 * (
																			 * )
																			 * ,
																			 * stampDuty
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setUpkarDuty
																			 * (
																			 * upkarDuty
																			 * )
																			 * ;
																			 * double
																			 * total
																			 * =
																			 * nagarPalikaDuty
																			 * +
																			 * panchayatDuty
																			 * +
																			 * upkarDuty
																			 * +
																			 * stampDuty
																			 * ;
																			 * dtoForm
																			 * .
																			 * setTotal
																			 * (
																			 * total
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * 
																			 * 
																			 * //
																			 * dtoForm
																			 * .
																			 * setTotal
																			 * (
																			 * common
																			 * .
																			 * getCurrencyFormat
																			 * (
																			 * total
																			 * )
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * 
																			 * //
																			 * session
																			 * .
																			 * setAttribute
																			 * (
																			 * "nagarPalikaDuty"
																			 * ,
																			 * nagarPalikaDuty
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * session
																			 * .
																			 * setAttribute
																			 * (
																			 * "panchayatDuty"
																			 * ,
																			 * panchayatDuty
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * session
																			 * .
																			 * setAttribute
																			 * (
																			 * "upkarDuty"
																			 * ,
																			 * upkarDuty
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * session
																			 * .
																			 * setAttribute
																			 * (
																			 * "total"
																			 * ,
																			 * total
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * //
																			 * session
																			 * .
																			 * setAttribute
																			 * (
																			 * "DutyDTO"
																			 * ,
																			 * dtoForm
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * session
																			 * .
																			 * setAttribute
																			 * (
																			 * "InstrumentDTO"
																			 * ,
																			 * instdto
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * session
																			 * .
																			 * setAttribute
																			 * (
																			 * "ExemptionDTO"
																			 * ,
																			 * exedto
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * session
																			 * .
																			 * setAttribute
																			 * (
																			 * "duty"
																			 * ,
																			 * duty
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * double
																			 * regFee
																			 * =
																			 * 0
																			 * ;
																			 * /
																			 * /
																			 * bo
																			 * .
																			 * getRegistrationFee
																			 * (
																			 * dtoForm
																			 * ,
																			 * instdto
																			 * ,
																			 * exedto
																			 * )
																			 * ;
																			 * regFee
																			 * =
																			 * Double
																			 * .
																			 * parseDouble
																			 * (
																			 * new
																			 * CalculateRegFee
																			 * (
																			 * )
																			 * .
																			 * calculateRegFee
																			 * (
																			 * instdto
																			 * ,
																			 * dtoForm
																			 * ,
																			 * dtoForm
																			 * )
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * if(
																			 * dtoForm
																			 * .
																			 * getDeedId
																			 * (
																			 * )
																			 * ==
																			 * 1092
																			 * )
																			 * {
																			 * /
																			 * /
																			 * lease
																			 * 
																			 * boolean
																			 * leaseRegFee1000Exemp
																			 * =
																			 * false
																			 * ;
																			 * ArrayList
																			 * list1
																			 * =
																			 * dtoForm
																			 * .
																			 * getExemptionDescpList
																			 * (
																			 * )
																			 * ;
																			 * for
																			 * (
																			 * int
																			 * i
																			 * =
																			 * 0
																			 * ;
																			 * i
																			 * <
																			 * list1
																			 * .
																			 * size
																			 * (
																			 * )
																			 * ;
																			 * i
																			 * ++
																			 * )
																			 * {
																			 * DutyCalculationDTO
																			 * dto1
																			 * =
																			 * (
																			 * DutyCalculationDTO
																			 * )
																			 * list1
																			 * .
																			 * get
																			 * (
																			 * i
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * 
																			 * if(
																			 * !
																			 * dto1
																			 * .
																			 * getExempId
																			 * (
																			 * )
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * "130"
																			 * )
																			 * )
																			 * {
																			 * leaseRegFee1000Exemp
																			 * =
																			 * true
																			 * ;
																			 * break
																			 * ;
																			 * 
																			 * }
																			 * }
																			 * 
																			 * 
																			 * if(
																			 * !
																			 * leaseRegFee1000Exemp
																			 * )
																			 * {
																			 * 
																			 * 
																			 * if(
																			 * regFee
																			 * >
																			 * 1000
																			 * )
																			 * {
																			 * regFee
																			 * =
																			 * 1000
																			 * ;
																			 * }
																			 * 
																			 * 
																			 * }
																			 * 
																			 * 
																			 * }
																			 * 
																			 * 
																			 * dtoForm
																			 * .
																			 * setRegFee
																			 * (
																			 * regFee
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * session
																			 * .
																			 * setAttribute
																			 * (
																			 * "regFee"
																			 * ,
																			 * regFee
																			 * )
																			 * ;
																			 * if
																			 * (
																			 * regFee
																			 * <
																			 * 0
																			 * )
																			 * {
																			 * regFee
																			 * =
																			 * 0.0
																			 * ;
																			 * dtoForm
																			 * .
																			 * setRegFee
																			 * (
																			 * regFee
																			 * )
																			 * ;
																			 * }
																			 * logger
																			 * .
																			 * debug
																			 * (
																			 * "Reg Fee:-"
																			 * +
																			 * regFee
																			 * )
																			 * ;
																			 * double
																			 * paidRegFee
																			 * =
																			 * 0
																			 * ;
																			 * double
																			 * paidStampduty
																			 * =
																			 * 0
																			 * ;
																			 * if
																			 * (
																			 * dtoForm
																			 * .
																			 * getAlreadyPaidRegFee
																			 * (
																			 * )
																			 * !=
																			 * null
																			 * &&
																			 * !
																			 * dtoForm
																			 * .
																			 * getAlreadyPaidRegFee
																			 * (
																			 * )
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * ""
																			 * )
																			 * )
																			 * {
																			 * paidRegFee
																			 * =
																			 * Double
																			 * .
																			 * parseDouble
																			 * (
																			 * dtoForm
																			 * .
																			 * getAlreadyPaidRegFee
																			 * (
																			 * )
																			 * )
																			 * ;
																			 * }
																			 * if
																			 * (
																			 * dtoForm
																			 * .
																			 * getAlreadyPaidStampDuty
																			 * (
																			 * )
																			 * !=
																			 * null
																			 * &&
																			 * !
																			 * dtoForm
																			 * .
																			 * getAlreadyPaidStampDuty
																			 * (
																			 * )
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * ""
																			 * )
																			 * )
																			 * {
																			 * paidStampduty
																			 * =
																			 * Double
																			 * .
																			 * parseDouble
																			 * (
																			 * dtoForm
																			 * .
																			 * getAlreadyPaidStampDuty
																			 * (
																			 * )
																			 * )
																			 * ;
																			 * }
																			 * if
																			 * (
																			 * (
																			 * (
																			 * regFee
																			 * )
																			 * -
																			 * paidRegFee
																			 * )
																			 * <
																			 * 0
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setPayableRegFee
																			 * (
																			 * 0
																			 * )
																			 * ;
																			 * }
																			 * else
																			 * {
																			 * dtoForm
																			 * .
																			 * setPayableRegFee
																			 * (
																			 * (
																			 * regFee
																			 * )
																			 * -
																			 * paidRegFee
																			 * )
																			 * ;
																			 * }
																			 * if
																			 * (
																			 * (
																			 * (
																			 * total
																			 * )
																			 * -
																			 * paidStampduty
																			 * )
																			 * <
																			 * 0
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setPayableStampDuty
																			 * (
																			 * 0
																			 * )
																			 * ;
																			 * }
																			 * else
																			 * {
																			 * dtoForm
																			 * .
																			 * setPayableStampDuty
																			 * (
																			 * (
																			 * total
																			 * )
																			 * -
																			 * paidStampduty
																			 * )
																			 * ;
																			 * }
																			 * double
																			 * minRegFee
																			 * =
																			 * bo
																			 * .
																			 * minRegFee
																			 * (
																			 * String
																			 * .
																			 * valueOf
																			 * (
																			 * dtoForm
																			 * .
																			 * getDeedId
																			 * (
																			 * )
																			 * )
																			 * ,
																			 * String
																			 * .
																			 * valueOf
																			 * (
																			 * instdto
																			 * .
																			 * getInstId
																			 * (
																			 * )
																			 * )
																			 * )
																			 * ;
																			 * double
																			 * minStamp
																			 * =
																			 * bo
																			 * .
																			 * minStampDuty
																			 * (
																			 * String
																			 * .
																			 * valueOf
																			 * (
																			 * dtoForm
																			 * .
																			 * getDeedId
																			 * (
																			 * )
																			 * )
																			 * ,
																			 * String
																			 * .
																			 * valueOf
																			 * (
																			 * instdto
																			 * .
																			 * getInstId
																			 * (
																			 * )
																			 * )
																			 * )
																			 * ;
																			 * if
																			 * (
																			 * minRegFee
																			 * >
																			 * 0
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setMinRegFee
																			 * (
																			 * String
																			 * .
																			 * valueOf
																			 * (
																			 * minRegFee
																			 * )
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setMinRegDisclaimerFlag
																			 * (
																			 * "Y"
																			 * )
																			 * ;
																			 * }
																			 * else
																			 * {
																			 * dtoForm
																			 * .
																			 * setMinRegFee
																			 * (
																			 * String
																			 * .
																			 * valueOf
																			 * (
																			 * minRegFee
																			 * )
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setMinRegDisclaimerFlag
																			 * (
																			 * "N"
																			 * )
																			 * ;
																			 * }
																			 * if
																			 * (
																			 * minStamp
																			 * >
																			 * 0
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setMinStamp
																			 * (
																			 * String
																			 * .
																			 * valueOf
																			 * (
																			 * minStamp
																			 * )
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setMinStampDisclaimerFlag
																			 * (
																			 * "Y"
																			 * )
																			 * ;
																			 * }
																			 * else
																			 * {
																			 * dtoForm
																			 * .
																			 * setMinStamp
																			 * (
																			 * String
																			 * .
																			 * valueOf
																			 * (
																			 * minStamp
																			 * )
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setMinStampDisclaimerFlag
																			 * (
																			 * "N"
																			 * )
																			 * ;
																			 * }
																			 * if
																			 * (
																			 * dtoForm
																			 * .
																			 * getPayableStampDuty
																			 * (
																			 * )
																			 * <
																			 * minStamp
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setPayableStampDuty
																			 * (
																			 * minStamp
																			 * )
																			 * ;
																			 * }
																			 * if
																			 * (
																			 * dtoForm
																			 * .
																			 * getPayableRegFee
																			 * (
																			 * )
																			 * <
																			 * minRegFee
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setPayableRegFee
																			 * (
																			 * minRegFee
																			 * )
																			 * ;
																			 * }
																			 * 
																			 * 
																			 * 
																			 * //
																			 * property
																			 * wise
																			 * exemption
																			 * code
																			 * comes
																			 * here
																			 * -
																			 * Roopam
																			 * -
																			 * 4
																			 * April
																			 * 2015
																			 * 
																			 * 
																			 * 
																			 * int
																			 * flag
																			 * =
																			 * new
																			 * CalculateDuty
																			 * (
																			 * )
																			 * .
																			 * calculatePropWiseExemptedDutiesEWS
																			 * (
																			 * instdto
																			 * ,
																			 * dtoForm
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * if(
																			 * flag
																			 * ==
																			 * 1
																			 * )
																			 * {
																			 * 
																			 * 
																			 * if(
																			 * "en"
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * language
																			 * )
																			 * )
																			 * {
																			 * request
																			 * .
																			 * setAttribute
																			 * (
																			 * "ERROR"
																			 * ,
																			 * "If you want to avail EWS / LIG / MIG exemption, please select checkbox on Property valuation page – ‘Is your property is lease hold property of Housing Board’"
																			 * )
																			 * ;
																			 * }
																			 * else
																			 * {
																			 * request
																			 * .
																			 * setAttribute
																			 * (
																			 * "ERROR"
																			 * ,
																			 * "If you want to avail EWS / LIG / MIG exemption, please select checkbox on Property valuation page – ‘Is your property is lease hold property of Housing Board’"
																			 * )
																			 * ;
																			 * 
																			 * }
																			 * regFlag
																			 * =
																			 * false
																			 * ;
																			 * forwardJsp
																			 * =
																			 * CommonConstant
																			 * .
																			 * FORWARD_PAGE_NEXT
																			 * ;
																			 * dto
																			 * .
																			 * setActionName
																			 * (
																			 * null
																			 * )
																			 * ;
																			 * actionName
																			 * =
																			 * ""
																			 * ;
																			 * 
																			 * 
																			 * }else
																			 * if
																			 * (
																			 * flag
																			 * ==
																			 * 2
																			 * )
																			 * {
																			 * 
																			 * 
																			 * 
																			 * if(
																			 * "en"
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * language
																			 * )
																			 * )
																			 * {
																			 * request
																			 * .
																			 * setAttribute
																			 * (
																			 * "ERROR"
																			 * ,
																			 * "Please select exemption belonging to either of EWS or MIG or LIG category"
																			 * )
																			 * ;
																			 * }
																			 * else
																			 * {
																			 * request
																			 * .
																			 * setAttribute
																			 * (
																			 * "ERROR"
																			 * ,
																			 * "Please select exemption belonging to either of EWS or MIG or LIG category"
																			 * )
																			 * ;
																			 * 
																			 * }
																			 * regFlag
																			 * =
																			 * false
																			 * ;
																			 * forwardJsp
																			 * =
																			 * CommonConstant
																			 * .
																			 * FORWARD_PAGE_NEXT
																			 * ;
																			 * dto
																			 * .
																			 * setActionName
																			 * (
																			 * null
																			 * )
																			 * ;
																			 * actionName
																			 * =
																			 * ""
																			 * ;
																			 * 
																			 * 
																			 * }
																			 * else
																			 * {
																			 * String
																			 * exceptionlist
																			 * =
																			 * exedto
																			 * .
																			 * getExemId
																			 * (
																			 * )
																			 * ;
																			 * System
																			 * .
																			 * out
																			 * .
																			 * println
																			 * (
																			 * "exceptionlist  sing "
																			 * +
																			 * exceptionlist
																			 * )
																			 * ;
																			 * System
																			 * .
																			 * out
																			 * .
																			 * println
																			 * (
																			 * "exemptions  sing "
																			 * +
																			 * exemptions
																			 * )
																			 * ;
																			 * double
																			 * d
																			 * =
																			 * bo
																			 * .
																			 * getExemptionPecentage
																			 * (
																			 * dtoForm
																			 * )
																			 * ;
																			 * double
																			 * fd
																			 * =
																			 * dtoForm
																			 * .
																			 * getPayableStampDuty
																			 * (
																			 * )
																			 * -
																			 * (
																			 * (
																			 * dtoForm
																			 * .
																			 * getPayableStampDuty
																			 * (
																			 * )
																			 * d
																			 * )
																			 * /
																			 * 100
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setMinusExempStamp
																			 * (
																			 * (
																			 * (
																			 * dtoForm
																			 * .
																			 * getPayableStampDuty
																			 * (
																			 * )
																			 * d
																			 * )
																			 * /
																			 * 100
																			 * )
																			 * )
																			 * ;
																			 * double
																			 * fd1
																			 * =
																			 * dtoForm
																			 * .
																			 * getTotalPayableStamp
																			 * (
																			 * )
																			 * -
																			 * (
																			 * (
																			 * dtoForm
																			 * .
																			 * getTotalPayableStamp
																			 * (
																			 * )
																			 * d
																			 * )
																			 * /
																			 * 100
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * dtoForm
																			 * .
																			 * setMinusExempStampTotal
																			 * (
																			 * (
																			 * (
																			 * dtoForm
																			 * .
																			 * getTotalPayableStamp
																			 * (
																			 * )
																			 * d
																			 * )
																			 * /
																			 * 100
																			 * )
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setMinusExempStampTotal
																			 * (
																			 * (
																			 * dtoForm
																			 * .
																			 * getMinusExempStampTotal
																			 * (
																			 * )
																			 * )
																			 * +
																			 * (
																			 * (
																			 * dtoForm
																			 * .
																			 * getTotalPayableStamp
																			 * (
																			 * )
																			 * d
																			 * )
																			 * /
																			 * 100
																			 * )
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * 
																			 * //
																			 * below
																			 * for
																			 * new
																			 * exemption
																			 * -
																			 * by
																			 * roopam
																			 * 
																			 * 
																			 * if(
																			 * dtoForm
																			 * .
																			 * getDeedId
																			 * (
																			 * )
																			 * ==
																			 * 1053
																			 * )
																			 * {
																			 * 
																			 * 
																			 * boolean
																			 * conveyance1PercentExemption
																			 * =
																			 * false
																			 * ;
																			 * ArrayList
																			 * list1
																			 * =
																			 * dtoForm
																			 * .
																			 * getExemptionDescpList
																			 * (
																			 * )
																			 * ;
																			 * for
																			 * (
																			 * int
																			 * i
																			 * =
																			 * 0
																			 * ;
																			 * i
																			 * <
																			 * list1
																			 * .
																			 * size
																			 * (
																			 * )
																			 * ;
																			 * i
																			 * ++
																			 * )
																			 * {
																			 * DutyCalculationDTO
																			 * dto1
																			 * =
																			 * (
																			 * DutyCalculationDTO
																			 * )
																			 * list1
																			 * .
																			 * get
																			 * (
																			 * i
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * 
																			 * if(
																			 * !
																			 * dto1
																			 * .
																			 * getExempId
																			 * (
																			 * )
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * "129"
																			 * )
																			 * )
																			 * {
																			 * conveyance1PercentExemption
																			 * =
																			 * true
																			 * ;
																			 * break
																			 * ;
																			 * 
																			 * }
																			 * }
																			 * 
																			 * 
																			 * if(
																			 * !
																			 * conveyance1PercentExemption
																			 * )
																			 * {
																			 * 
																			 * stampDuty
																			 * =
																			 * Double
																			 * .
																			 * parseDouble
																			 * (
																			 * new
																			 * CalculateDuty
																			 * (
																			 * )
																			 * .
																			 * calculateStampDutyCoveyance1Percent
																			 * (
																			 * instdto
																			 * ,
																			 * dtoForm
																			 * ,
																			 * dtoForm
																			 * )
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setStampDuty
																			 * (
																			 * stampDuty
																			 * )
																			 * ;
																			 * 
																			 * if
																			 * (
																			 * stampDuty
																			 * <
																			 * 0
																			 * )
																			 * {
																			 * stampDuty
																			 * =
																			 * 0.0
																			 * ;
																			 * dtoForm
																			 * .
																			 * setStampDuty
																			 * (
																			 * stampDuty
																			 * )
																			 * ;
																			 * }
																			 * 
																			 * 
																			 * 
																			 * 
																			 * upkarDuty
																			 * =
																			 * new
																			 * CalculateDuty
																			 * (
																			 * )
																			 * .
																			 * calculateUpkar
																			 * (
																			 * eForm
																			 * .
																			 * getInstDTO
																			 * (
																			 * )
																			 * .
																			 * getInstId
																			 * (
																			 * )
																			 * ,
																			 * stampDuty
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setUpkarDuty
																			 * (
																			 * upkarDuty
																			 * )
																			 * ;
																			 * double
																			 * totalNew
																			 * =
																			 * nagarPalikaDuty
																			 * +
																			 * panchayatDuty
																			 * +
																			 * upkarDuty
																			 * +
																			 * stampDuty
																			 * ;
																			 * dtoForm
																			 * .
																			 * setTotal
																			 * (
																			 * totalNew
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * if(
																			 * (
																			 * (
																			 * totalNew
																			 * )
																			 * -
																			 * paidStampduty
																			 * )
																			 * <
																			 * 0
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setPayableStampDuty
																			 * (
																			 * 0
																			 * )
																			 * ;
																			 * }
																			 * else
																			 * {
																			 * dtoForm
																			 * .
																			 * setPayableStampDuty
																			 * (
																			 * (
																			 * totalNew
																			 * )
																			 * -
																			 * paidStampduty
																			 * )
																			 * ;
																			 * }
																			 * 
																			 * 
																			 * fd=
																			 * dtoForm
																			 * .
																			 * getPayableStampDuty
																			 * (
																			 * )
																			 * ;
																			 * fd1
																			 * =
																			 * dtoForm
																			 * .
																			 * getPayableStampDuty
																			 * (
																			 * )
																			 * ;
																			 * }
																			 * 
																			 * }
																			 * 
																			 * 
																			 * 
																			 * if(
																			 * dtoForm
																			 * .
																			 * getDeedId
																			 * (
																			 * )
																			 * ==
																			 * 1092
																			 * )
																			 * {
																			 * /
																			 * /
																			 * lease
																			 * 
																			 * boolean
																			 * leaseRegFee1000Exemp
																			 * =
																			 * false
																			 * ;
																			 * ArrayList
																			 * list1
																			 * =
																			 * dtoForm
																			 * .
																			 * getRegFeeExemptionDiscriptionList
																			 * (
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * if(
																			 * list1
																			 * !=
																			 * null
																			 * &&
																			 * list1
																			 * .
																			 * size
																			 * (
																			 * )
																			 * >
																			 * 0
																			 * )
																			 * {
																			 * for
																			 * (
																			 * int
																			 * i
																			 * =
																			 * 0
																			 * ;
																			 * i
																			 * <
																			 * list1
																			 * .
																			 * size
																			 * (
																			 * )
																			 * ;
																			 * i
																			 * ++
																			 * )
																			 * {
																			 * DutyCalculationDTO
																			 * dto1
																			 * =
																			 * (
																			 * DutyCalculationDTO
																			 * )
																			 * list1
																			 * .
																			 * get
																			 * (
																			 * i
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * 
																			 * if(
																			 * !
																			 * dto1
																			 * .
																			 * getExempId
																			 * (
																			 * )
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * "130"
																			 * )
																			 * )
																			 * {
																			 * leaseRegFee1000Exemp
																			 * =
																			 * true
																			 * ;
																			 * break
																			 * ;
																			 * 
																			 * }
																			 * }
																			 * }
																			 * else
																			 * {
																			 * leaseRegFee1000Exemp
																			 * =
																			 * true
																			 * ;
																			 * }
																			 * 
																			 * 
																			 * if(
																			 * !
																			 * leaseRegFee1000Exemp
																			 * )
																			 * {
																			 * 
																			 * 
																			 * if(
																			 * dtoForm
																			 * .
																			 * getTotalPayableReg
																			 * (
																			 * )
																			 * >
																			 * 1000
																			 * )
																			 * {
																			 * 
																			 * dtoForm
																			 * .
																			 * setTotalPayableReg
																			 * (
																			 * 1000
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * regFee
																			 * =
																			 * 1000
																			 * ;
																			 * }
																			 * 
																			 * 
																			 * }
																			 * 
																			 * 
																			 * }
																			 * 
																			 * double
																			 * r
																			 * =
																			 * bo
																			 * .
																			 * getExemptionPecentageRegFee
																			 * (
																			 * dtoForm
																			 * )
																			 * ;
																			 * double
																			 * rd
																			 * =
																			 * dtoForm
																			 * .
																			 * getPayableRegFee
																			 * (
																			 * )
																			 * -
																			 * (
																			 * (
																			 * dtoForm
																			 * .
																			 * getPayableRegFee
																			 * (
																			 * )
																			 * r
																			 * )
																			 * /
																			 * 100
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setMinusExempReg
																			 * (
																			 * (
																			 * (
																			 * dtoForm
																			 * .
																			 * getPayableRegFee
																			 * (
																			 * )
																			 * r
																			 * )
																			 * /
																			 * 100
																			 * )
																			 * )
																			 * ;
																			 * double
																			 * rd1
																			 * =
																			 * dtoForm
																			 * .
																			 * getTotalPayableReg
																			 * (
																			 * )
																			 * -
																			 * (
																			 * (
																			 * dtoForm
																			 * .
																			 * getTotalPayableReg
																			 * (
																			 * )
																			 * r
																			 * )
																			 * /
																			 * 100
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setMinusExempRegTotal
																			 * (
																			 * (
																			 * (
																			 * dtoForm
																			 * .
																			 * getTotalPayableReg
																			 * (
																			 * )
																			 * r
																			 * )
																			 * /
																			 * 100
																			 * )
																			 * )
																			 * ;
																			 * 
																			 * dtoForm
																			 * .
																			 * setDutyafterExemption
																			 * (
																			 * fd
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setDutyafterExemptionTotal
																			 * (
																			 * fd1
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setRegFeeafterExemp
																			 * (
																			 * rd
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setRegFeeafterExempTotal
																			 * (
																			 * rd1
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * 
																			 * String
																			 * propValReqFlag
																			 * =
																			 * bo
																			 * .
																			 * getPropValRequiredFlag
																			 * (
																			 * instdto
																			 * .
																			 * getInstId
																			 * (
																			 * )
																			 * )
																			 * ;
																			 * if
																			 * (
																			 * propValReqFlag
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * "Y"
																			 * )
																			 * &&
																			 * !
																			 * "true"
																			 * .
																			 * equalsIgnoreCase
																			 * (
																			 * dtoForm
																			 * .
																			 * getValuationIdValidate
																			 * (
																			 * )
																			 * )
																			 * )
																			 * {
																			 * 
																			 * }
																			 * else
																			 * {
																			 * if
																			 * (
																			 * dtoForm
																			 * .
																			 * getTotalPayableStamp
																			 * (
																			 * )
																			 * <
																			 * minStamp
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setTotalPayableStamp
																			 * (
																			 * minStamp
																			 * )
																			 * ;
																			 * }
																			 * if
																			 * (
																			 * dtoForm
																			 * .
																			 * getTotalPayableReg
																			 * (
																			 * )
																			 * <
																			 * minRegFee
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setTotalPayableReg
																			 * (
																			 * minRegFee
																			 * )
																			 * ;
																			 * }
																			 * }
																			 * if
																			 * (
																			 * dtoForm
																			 * .
																			 * getDutyafterExemption
																			 * (
																			 * )
																			 * <
																			 * minStamp
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setDutyafterExemption
																			 * (
																			 * minStamp
																			 * )
																			 * ;
																			 * }
																			 * if
																			 * (
																			 * 2058
																			 * !=
																			 * instdto
																			 * .
																			 * getInstId
																			 * (
																			 * )
																			 * )
																			 * {
																			 * if
																			 * (
																			 * dtoForm
																			 * .
																			 * getRegFeeafterExemp
																			 * (
																			 * )
																			 * <
																			 * minRegFee
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setRegFeeafterExemp
																			 * (
																			 * minRegFee
																			 * )
																			 * ;
																			 * }
																			 * }
																			 * if
																			 * (
																			 * dtoForm
																			 * .
																			 * getDutyafterExemptionTotal
																			 * (
																			 * )
																			 * <
																			 * minStamp
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setDutyafterExemptionTotal
																			 * (
																			 * minStamp
																			 * )
																			 * ;
																			 * }
																			 * if
																			 * (
																			 * 2058
																			 * !=
																			 * instdto
																			 * .
																			 * getInstId
																			 * (
																			 * )
																			 * )
																			 * {
																			 * if
																			 * (
																			 * dtoForm
																			 * .
																			 * getRegFeeafterExempTotal
																			 * (
																			 * )
																			 * <
																			 * minRegFee
																			 * )
																			 * {
																			 * dtoForm
																			 * .
																			 * setRegFeeafterExempTotal
																			 * (
																			 * minRegFee
																			 * )
																			 * ;
																			 * }
																			 * }
																			 * 
																			 * 
																			 * }}
																			 */
				}

			}

			if ("calcRent".equalsIgnoreCase(actionName)) {
				ArrayList userList = new ArrayList();
				String param[] = request
						.getParameterValues("userEnterableFieldValue");
				DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
				ArrayList list = eForm.getInstDTO().getUserEnterableList();
				if (list != null) {
					int j = 0;
					for (int i = 0; i < list.size(); i++) {
						InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
						UserEnterableDTO userDto = new UserEnterableDTO();
						String id = indto.getUserEnterableId();
						String value = "";
						if ("Y".equalsIgnoreCase(indto
								.getInStampDutyAmountFlag())
								&& "Y".equalsIgnoreCase(indto
										.getAsConsiderationAmountFlag())) {
							value = eForm.getInstDTO().getCommonUserField();
						} else if ("Y".equalsIgnoreCase(indto
								.getInStampDutyAmountFlag())
								&& "N".equalsIgnoreCase(indto
										.getAsConsiderationAmountFlag())) {
							value = eForm.getInstDTO().getInStampDutyAmount();
						} else if ("N".equalsIgnoreCase(indto
								.getInStampDutyAmountFlag())
								&& "Y".equalsIgnoreCase(indto
										.getAsConsiderationAmountFlag())) {
							value = eForm.getInstDTO()
									.getAsConsiderationAmount();
						} else {
							value = param[j];
							j++;
						}
						if ("Y".equalsIgnoreCase(indto
								.getAlreadyPaidRegFeeFlag())) {
							dto1.setAlreadyPaidRegFee(value);
						}
						if ("Y".equalsIgnoreCase(indto
								.getAlreadyPaidStampDutyFlag())) {
							dto1.setAlreadyPaidStampDuty(value);
						}
						userDto.setId(id);
						userDto.setValue(value);
						userDto.setName(indto.getUserEnterableName());
						indto.setUserEnterableFieldValue(value);
						userList.add(userDto);
					}
					// dto1.setUserValueMap(userValueMap);
					dto1.setOpenPopUpRent("Y");
					dto1.setUserValueList(userList);
					dto1.setActionName("");
				}
				eForm.setDutyCalculationDTO(dto1);
				eForm.getDutyCalculationDTO().setRadioResiComm3("");
				forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;

			}
			if ("calcPremium".equalsIgnoreCase(actionName)) {
				ArrayList userList = new ArrayList();
				String param[] = request
						.getParameterValues("userEnterableFieldValue");
				DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
				ArrayList list = eForm.getInstDTO().getUserEnterableList();
				if (list != null) {
					int j = 0;
					for (int i = 0; i < list.size(); i++) {
						InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
						UserEnterableDTO userDto = new UserEnterableDTO();
						String id = indto.getUserEnterableId();
						String value = "";
						if ("Y".equalsIgnoreCase(indto
								.getInStampDutyAmountFlag())
								&& "Y".equalsIgnoreCase(indto
										.getAsConsiderationAmountFlag())) {
							value = eForm.getInstDTO().getCommonUserField();
						} else if ("Y".equalsIgnoreCase(indto
								.getInStampDutyAmountFlag())
								&& "N".equalsIgnoreCase(indto
										.getAsConsiderationAmountFlag())) {
							value = eForm.getInstDTO().getInStampDutyAmount();
						} else if ("N".equalsIgnoreCase(indto
								.getInStampDutyAmountFlag())
								&& "Y".equalsIgnoreCase(indto
										.getAsConsiderationAmountFlag())) {
							value = eForm.getInstDTO()
									.getAsConsiderationAmount();
						} else {
							value = param[j];
							j++;
						}
						if ("Y".equalsIgnoreCase(indto
								.getAlreadyPaidRegFeeFlag())) {
							dto1.setAlreadyPaidRegFee(value);
						}
						if ("Y".equalsIgnoreCase(indto
								.getAlreadyPaidStampDutyFlag())) {
							dto1.setAlreadyPaidStampDuty(value);
						}
						userDto.setId(id);
						userDto.setValue(value);
						userDto.setName(indto.getUserEnterableName());
						indto.setUserEnterableFieldValue(value);
						userList.add(userDto);
					}
					// dto1.setUserValueMap(userValueMap);
					dto1.setOpenPopUpPremium("Y");
					dto1.setUserValueList(userList);
					dto1.setActionName(null);
				}
				eForm.setDutyCalculationDTO(dto1);
				eForm.getDutyCalculationDTO().setRadioResiComm3("");
				forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;

			}

			if (CommonConstant.FORWARD_PREMIUM_PAGE_ACTION
					.equalsIgnoreCase(rentRequest)) {
				dto.setPremium("");
				dto.setDevelopmentCharges("");
				dto.setAdditionalPremium("");
				dto.setOtherCharges("");

				forwardJsp = CommonConstant.FORWARD_PREMIUM_PAGE;

			}
			if (CommonConstant.FORWARD_RENT_PAGE_ACTION
					.equalsIgnoreCase(rentRequest)) {
				dto.setRentArrayList(new ArrayList());
				dto.setTotalYear(0);
				dto.setTotalMonth(0);
				dto.setMothlyRent("");
				dto.setPeriodMonth("");
				dto.setPeriodYear("");
				dto.setMaintence("");
				forwardJsp = CommonConstant.FORWARD_RENT_PAGE;

			}
			if (CommonConstant.CALCULATE_PREMIUM.equalsIgnoreCase((actionName))) {
				if (dto.getPremium() == null
						|| dto.getPremium().equalsIgnoreCase("")) {
					dto.setPremium("0");
				}
				if (dto.getAdditionalPremium() == null
						|| dto.getAdditionalPremium().equalsIgnoreCase("")) {
					dto.setAdditionalPremium("0");
				}
				if (dto.getDevelopmentCharges() == null
						|| dto.getDevelopmentCharges().equalsIgnoreCase("")) {
					dto.setDevelopmentCharges("0");
				}
				if (dto.getOtherCharges() == null
						|| dto.getOtherCharges().equalsIgnoreCase("")) {
					dto.setOtherCharges("0");
				}

				double premium = Double.parseDouble(dto.getPremium());
				double addtionalPremium = Double.parseDouble(dto
						.getAdditionalPremium());
				double devcharges = Double.parseDouble(dto
						.getDevelopmentCharges());
				double other = Double.parseDouble(dto.getOtherCharges());
				eForm.getInstDTO().setAsConsiderationAmount(
						String.valueOf(premium + addtionalPremium + devcharges
								+ other));
				eForm.getDutyCalculationDTO().setOpenPopUpPremium("");
				eForm.getDutyCalculationDTO().setOpenPopUpRent("");
				dto.setActionName(null);
				eForm.getDutyCalculationDTO().setRadioResiComm3("");
				forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;

			}
			if (CommonConstant.ADD_SLAB.equalsIgnoreCase((actionName))) {
				DutyCalculationDTO ddto = new DutyCalculationDTO();
				InstrumentsDTO idto = eForm.getInstDTO();
				int instId = idto.getInstId();
				String leaseId = idto.getLeaseId();
				int lid = 0;
				if (leaseId != null && !leaseId.equalsIgnoreCase("")) {
					lid = Integer.parseInt(leaseId);
				}
				ddto.setMothlyRent(dto.getMothlyRent());
				ddto.setPeriodMonth(dto.getPeriodMonth());
				ddto.setPeriodYear(dto.getPeriodYear());
				ddto.setMaintence(dto.getMaintence());
				double year = dto.getTotalYear();
				double month = dto.getTotalMonth();
				year = year + Double.parseDouble(dto.getPeriodYear());
				month = month + Double.parseDouble(dto.getPeriodMonth());
				if (month >= 12) {
					year = year + 1;
					month = month - 12;
				}
				if (instId == 2215 || lid == 2215) {
					if (year >= 5) {
						if ("en".equalsIgnoreCase(language)) {
							request
									.setAttribute("ERROR",
											"Period cannot be greater than  or equal to 5 years");

						} else {
							request
									.setAttribute("ERROR",
											"अवधि 5 साल के बराबर  या उससे अधिक नहीं हो सकती|");

						}
					} else {
						dto.getRentArrayList().add(ddto);
						dto.setTotalYear(year);
						dto.setTotalMonth(month);
						dto.setMothlyRent("");
						dto.setPeriodMonth("");
						dto.setPeriodYear("");
						dto.setMaintence("");
					}
				} else if (instId == 2216 || lid == 2216) {
					if (year >= 10) {
						if ("en".equalsIgnoreCase(language)) {
							request
									.setAttribute("ERROR",
											"Period cannot be greater than or equal to 10 years");

						} else {
							request
									.setAttribute("ERROR",
											"अवधि 10 साल के बराबर  या उससे अधिक नहीं हो सकती|");

						}
					} else {
						dto.getRentArrayList().add(ddto);
						dto.setTotalYear(year);
						dto.setTotalMonth(month);
						dto.setMothlyRent("");
						dto.setPeriodMonth("");
						dto.setPeriodYear("");
						dto.setMaintence("");
					}
				} else if (instId == 2223 || lid == 2223) {
					if (year >= 20) {
						if ("en".equalsIgnoreCase(language)) {
							request
									.setAttribute("ERROR",
											"Period cannot be greater than or equal to 20 years");

						} else {
							request
									.setAttribute("ERROR",
											"अवधि 20 साल के बराबर  या उससे अधिक नहीं हो सकती|");

						}
					} else {
						dto.getRentArrayList().add(ddto);
						dto.setTotalYear(year);
						dto.setTotalMonth(month);
						dto.setMothlyRent("");
						dto.setPeriodMonth("");
						dto.setPeriodYear("");
						dto.setMaintence("");
					}
				} else if (instId == 2217 || instId == 2221 || instId == 2210
						|| lid == 2217) {
					if (year >= 30) {
						if ("en".equalsIgnoreCase(language)) {
							request
									.setAttribute("ERROR",
											"Period can't be greater than  or equal to 30 years");

						} else {
							request
									.setAttribute("ERROR",
											"अवधि 30 साल के बराबर  या उससे अधिक नहीं हो सकती|");

						}
					} else {
						dto.getRentArrayList().add(ddto);
						dto.setMothlyRent("");
						dto.setTotalYear(year);
						dto.setTotalMonth(month);
						dto.setPeriodMonth("");
						dto.setPeriodYear("");
						dto.setMaintence("");
					}
				} else {
					dto.getRentArrayList().add(ddto);
					dto.setMothlyRent("");
					dto.setTotalYear(year);
					dto.setTotalMonth(month);
					dto.setPeriodMonth("");
					dto.setPeriodYear("");
					dto.setMaintence("");
				}

				dto.setActionName(null);
				forwardJsp = CommonConstant.FORWARD_RENT_PAGE;

			}
			if (CommonConstant.CALCULATE_RENT.equalsIgnoreCase((actionName))) {
				double rent = 0;
				if (dto.getMothlyRent() == null
						|| dto.getMothlyRent().equalsIgnoreCase("")) {
					dto.setMothlyRent("0");
				}
				if (dto.getMaintence() == null
						|| dto.getMaintence().equalsIgnoreCase("")) {
					dto.setMaintence("0");
				}
				if (dto.getPeriodYear() == null
						|| dto.getPeriodYear().equalsIgnoreCase("")) {
					dto.setPeriodYear("0");
				}
				if (dto.getPeriodMonth() == null
						|| dto.getPeriodMonth().equalsIgnoreCase("")) {
					dto.setPeriodMonth("0");
				}
				double monthlyRent = Double.parseDouble(dto.getMothlyRent());
				double maintenece = Double.parseDouble(dto.getMaintence());
				double year = Double.parseDouble(dto.getPeriodYear());
				double month = Double.parseDouble(dto.getPeriodMonth());
				double period = year + (month / 12);
				double totalPeriod = period;
				rent = (period * (monthlyRent + maintenece));
				ArrayList list = dto.getRentArrayList();
				for (int i = 0; i < list.size(); i++) {
					DutyCalculationDTO ddto = (DutyCalculationDTO) list.get(i);
					monthlyRent = Double.parseDouble(ddto.getMothlyRent());
					maintenece = Double.parseDouble(ddto.getMaintence());
					year = Double.parseDouble(ddto.getPeriodYear());
					month = Double.parseDouble(ddto.getPeriodMonth());
					period = year + (month / 12);
					totalPeriod = period + totalPeriod;
					rent = rent + (period * (monthlyRent + maintenece));

				}
				DutyCalculationDTO ddto = new DutyCalculationDTO();
				InstrumentsDTO idto = eForm.getInstDTO();
				int instId = idto.getInstId();
				ddto.setMothlyRent(dto.getMothlyRent());
				ddto.setPeriodMonth(dto.getPeriodMonth());
				ddto.setPeriodYear(dto.getPeriodYear());
				ddto.setMaintence(dto.getMaintence());
				dto.getRentArrayList().add(ddto);
				rent = Math.ceil((rent / totalPeriod) * 12);
				eForm.getInstDTO().setInStampDutyAmount(String.valueOf(rent));
				eForm.getDutyCalculationDTO().setOpenPopUpPremium("");
				eForm.getDutyCalculationDTO().setOpenPopUpRent("");
				dto.setActionName(null);
				eForm.getDutyCalculationDTO().setRadioResiComm3("");
				forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;

			}

			if ("cancellationCategoryChange".equalsIgnoreCase(actionName)) {
				DutyCalculationDTO dtoId = eForm.getDutyCalculationDTO();
				dtoId.setCancellationFlag("N");
				dtoId.setDeedCategoryName(dtoId.getCancellationCategoryName());
				dtoId.setDeedCategoryId(dtoId.getCancellationCategoryId());
				dtoId.setDeedId(0);
				dtoId.setDeedType("");
				InstrumentsDTO instdto = eForm.getInstDTO();
				instdto.setUserEnterableList(new ArrayList());
				dtoId.setExemptionList(new ArrayList());
				dtoId.setExemptionDescpList(new ArrayList());
				dtoId.setRegFeeExemptionList(new ArrayList());
				dtoId.setRegFeeExemptionDiscriptionList(new ArrayList());

				eForm.setDutycalculationDTOList(bo
						.getDeed(dtoId.getCancellationCategoryId(), dtoId
								.getCancellationFlag(), language, dtoId
								.getFromModule()));
				eForm.setInstrumentDTOList(new ArrayList());
				eForm.setDutyCalculationDTO(dtoId);

			}
			if ("cancellationDeedchange".equalsIgnoreCase(actionName)) {
				DutyCalculationDTO dtoId = eForm.getDutyCalculationDTO();
				InstrumentsDTO instdto = eForm.getInstDTO();
				instdto.setUserEnterableList(new ArrayList());
				dtoId.setCancellationFlag("N");
				eForm.setDutycalculationDTOList(bo
						.getDeed(dtoId.getCancellationCategoryId(), dtoId
								.getCancellationFlag(), language, dtoId
								.getFromModule()));
				dtoId.setDeedType(dtoId.getCancellationDeedName());
				dtoId.setDeedCategoryName(dtoId.getCancellationCategoryName());
				dtoId.setDeedCategoryId(dtoId.getCancellationCategoryId());
				dtoId
						.setDeedId(Integer.parseInt(dtoId
								.getCancellationDeedId()));
				dtoId.setExemptionList(new ArrayList());
				dtoId.setExemptionDescpList(new ArrayList());
				dtoId.setRegFeeExemptionList(new ArrayList());
				dtoId.setRegFeeExemptionDiscriptionList(new ArrayList());
				ArrayList instList = bo.getInstrument(dtoId.getDeedId(), "N",
						locale, dtoId.getFromModule());
				eForm.setInstrumentDTOList(instList);
				eForm.setDutyCalculationDTO(dtoId);
			}
			if ("availexemptionAction".equalsIgnoreCase(actionName)) {
				DutyCalculationDTO dtoId = eForm.getDutyCalculationDTO();
				ArrayList userList = new ArrayList();
				String param[] = request
						.getParameterValues("userEnterableFieldValue");
				ArrayList list = eForm.getInstDTO().getUserEnterableList();
				if (list != null) {
					int j = 0;
					for (int i = 0; i < list.size(); i++) {
						InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
						UserEnterableDTO userDto = new UserEnterableDTO();
						String id = indto.getUserEnterableId();
						String value = "";
						if ("Y".equalsIgnoreCase(indto
								.getInStampDutyAmountFlag())
								&& "Y".equalsIgnoreCase(indto
										.getAsConsiderationAmountFlag())) {
							value = eForm.getInstDTO().getCommonUserField();
						} else if ("Y".equalsIgnoreCase(indto
								.getInStampDutyAmountFlag())
								&& "N".equalsIgnoreCase(indto
										.getAsConsiderationAmountFlag())) {
							value = eForm.getInstDTO().getInStampDutyAmount();
						} else if ("N".equalsIgnoreCase(indto
								.getInStampDutyAmountFlag())
								&& "Y".equalsIgnoreCase(indto
										.getAsConsiderationAmountFlag())) {
							value = eForm.getInstDTO()
									.getAsConsiderationAmount();
						} else {
							value = param != null ? param[j] : "";// null
																	// handeling
																	// done by
																	// roopam
							j++;
						}
						if ("Y".equalsIgnoreCase(indto
								.getAlreadyPaidRegFeeFlag())) {
							dtoId.setAlreadyPaidRegFee(value);
						}
						if ("Y".equalsIgnoreCase(indto
								.getAlreadyPaidStampDutyFlag())) {
							dtoId.setAlreadyPaidStampDuty(value);
						}
						userDto.setId(id);
						userDto.setValue(value);
						userDto.setName(indto.getUserEnterableName());
						indto.setUserEnterableFieldValue(value);
						userList.add(userDto);
					}
					// dto1.setUserValueMap(userValueMap);
					dtoId.setUserValueList(userList);
				}
				if ("Y".equalsIgnoreCase(dtoId.getAvailExemptionFlag())) {
					dtoId.setAvailExemption("Y");
					dtoId.setExemptionVisible("Y");
				} else {
					dtoId.setAvailExemption("N");
					dtoId.setExemptionVisible("N");
					dtoId.setExemptionDescpList(new ArrayList());
					dtoId.setRegFeeExemptionDiscriptionList(new ArrayList());
					ArrayList regList = dtoId.getRegFeeExemptionList();
					ArrayList stList = dtoId.getExemptionList();
					for (int i = 0; i < regList.size(); i++) {
						DutyCalculationDTO dto2 = (DutyCalculationDTO) regList
								.get(i);
						dto2.setExempCheckBox("");

					}
					for (int i = 0; i < stList.size(); i++) {
						DutyCalculationDTO dto2 = (DutyCalculationDTO) stList
								.get(i);
						dto2.setExempCheckBox("");

					}

				}
				eForm.setDutyCalculationDTO(dtoId);
				eForm.getDutyCalculationDTO().setRadioResiComm3("");
				forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
			}
			if ("leaseIdChange".equalsIgnoreCase(actionName)) {
				InstrumentsDTO instdto = eForm.getInstDTO();
				eForm.getDutyCalculationDTO().setOpenPopUpPremium("");
				eForm.getDutyCalculationDTO().setOpenPopUpRent("");
				DutyCalculationDTO dtoId = eForm.getDutyCalculationDTO();
				logger.debug("Inside radio butto");

				int deedId = dtoId.getDeedId();
				int instId = instdto.getInstId();
				ArrayList enetrableList1 = bo.getUserEnterableField(String
						.valueOf(deedId), String.valueOf(instId), language);
				ArrayList enetrableList = bo.getUserEnterableField(String
						.valueOf(deedId), String.valueOf(instdto.getLeaseId()),
						language);
				instdto.setUserEnterableList(new ArrayList());
				instdto.setUserEnterableList(enetrableList1);
				instdto.getUserEnterableList().addAll(enetrableList);
				dtoId.setActionName(null);
				eForm.setDutyCalculationDTO(dtoId);
				eForm.getDutyCalculationDTO().setRadioResiComm3("");
				forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;

			}
			if (CommonConstant.DUTY_RADIO_ACTION.equals(actionName)) {
				InstrumentsDTO instdto = eForm.getInstDTO();
				eForm.getDutyCalculationDTO().setOpenPopUpPremium("");
				eForm.getDutyCalculationDTO().setOpenPopUpRent("");
				DutyCalculationDTO dtoId = eForm.getDutyCalculationDTO();
				logger.debug("Inside radio butto");

				int deedId = dtoId.getDeedId();
				int instId = instdto.getInstId();
				instdto.setLeaseFreeze("N");
				ArrayList enetrableList = bo.getUserEnterableField(String
						.valueOf(deedId), String.valueOf(instId), language);
				if (enetrableList == null || enetrableList.size() == 0) {
					instdto.setUserEnterableFieldReq("N");
				} else {
					if (instId == 2179) {
						dtoId.setHvShare("N");
						instdto.setUserEnterableFieldReq("N");
						instdto.setUserEnterableList(null);
					} else {
						instdto.setUserEnterableFieldReq("Y");
						instdto.setUserEnterableList(enetrableList);
					}

				}
				String propValRequired = bo.getPropValRequiredFlag(instId);
				String municipal = bo.getmuniciplaFlag(instId);
				if ("Y".equalsIgnoreCase(municipal)) {
					dtoId.setMunicipalVisible("Y");
				} else {
					dtoId.setMunicipalVisible("N");

				}
				dtoId.setRinPustikaVisible("N");
				dtoId.setRinPustikaVisible1("N");
				dtoId.setRinPustikaVisible2("N");
				dtoId.setEntireTotal(0);
				dtoId.setTotalStampDuty(0);
				dtoId.setTotalregFee(0);
				dtoId.setGovCheck("");
				dtoId.setGovValue("");
				dtoId.setGovCheckFlag("");
				dtoId.setTotalUpkar(0);
				dtoId.setTotalNagarPalika(0);
				dtoId.setTotalPanchyat(0);
				dtoId.setTotalPaidStamp(0);
				dtoId.setTotalPaidReg(0);
				dtoId.setEntireTotal(0);
				dtoId.setTotalGovValue(0);
				dtoId.setTotalPayableReg(0);
				instdto.setLeaseId("");
				dtoId.setTotalPayableStamp(0);
				dtoId.setAlreadyPaidRegFee("0");
				dtoId.setAlreadyPaidStampDuty("0");
				dtoId.setPropertyValuationRequired(propValRequired);
				if (!"Y".equalsIgnoreCase(dtoId.getCancellationFlag())) {
					ArrayList exemDTOList = bo.getExemptions(deedId, instId,
							language);
					dtoId.setExemptionList(exemDTOList);
					ArrayList regFeeExempList = bo.getRegFeeExemptions(deedId,
							instId, language);
					dtoId.setRegFeeExemptionList(regFeeExempList);
					dtoId.setExemptionVisible("N");
					dtoId.setAvailExemption("N");
					String family = bo.getFamilyFlag(instId);
					if ("Y".equalsIgnoreCase(family)) {
						dtoId.setFamilyVisible("Y");
					} else {
						dtoId.setFamilyVisible("N");

					}
				}
				dtoId.setInstDescription(bo
						.getInstDiscription(instId, language));
				dtoId.setExemptionDescpList(new ArrayList());
				dtoId.setRegFeeExemptionDiscriptionList(new ArrayList());

				logger.debug("After exemption list");
				dtoId.setPropertyDetailsList(new ArrayList());
				// dtoId.setBaseValue(dto.getBaseValue());
				if (instId == 2105 || instId == 2106 || instId == 2108
						|| instId == 2184) {
					dtoId.setMaxPartitionMv(0);
					dtoId.setPartitionMV(0);
					dtoId.setNoOfSperatedPart("");
					dtoId.setAlreadyPaidStampDuty("");
				}
				if (instId == 2220 || instId == 2219) {
					if ("en".equalsIgnoreCase(language)) {
						InstrumentsDTO idto = new InstrumentsDTO();
						idto.setLeaseId("2214");
						idto.setLeaseName("0 to less than 1 Yr");
						InstrumentsDTO idto1 = new InstrumentsDTO();
						idto1.setLeaseId("2215");
						idto1.setLeaseName("1 to less than 5 Yr");
						InstrumentsDTO idto2 = new InstrumentsDTO();
						idto2.setLeaseId("2216");
						idto2.setLeaseName("5 to less than 10 Yr");
						InstrumentsDTO idto3 = new InstrumentsDTO();
						idto3.setLeaseId("2223");
						idto3.setLeaseName("10 to less than 20 Yr");
						InstrumentsDTO idto4 = new InstrumentsDTO();
						idto4.setLeaseId("2217");
						idto4.setLeaseName("20 to less than 30 Yr");
						InstrumentsDTO idto5 = new InstrumentsDTO();
						idto5.setLeaseId("2218");
						idto5.setLeaseName("greater than or equal to 30 yr");
						instdto.setLeasePeriod(new ArrayList());
						instdto.getLeasePeriod().add(idto);
						instdto.getLeasePeriod().add(idto1);
						instdto.getLeasePeriod().add(idto2);
						instdto.getLeasePeriod().add(idto3);
						instdto.getLeasePeriod().add(idto4);
						instdto.getLeasePeriod().add(idto5);

					} else {
						InstrumentsDTO idto = new InstrumentsDTO();
						idto.setLeaseId("2214");
						idto.setLeaseName("0 से 1 वर्ष से कम के लिए");
						InstrumentsDTO idto1 = new InstrumentsDTO();
						idto1.setLeaseId("2215");
						idto1.setLeaseName("1 से 5 वर्ष से कम के लिए");
						InstrumentsDTO idto2 = new InstrumentsDTO();
						idto2.setLeaseId("2216");
						idto2.setLeaseName("5 से 10 वर्ष से कम के लिए");
						InstrumentsDTO idto3 = new InstrumentsDTO();
						idto3.setLeaseId("2223");
						idto3.setLeaseName("10 से 20 वर्ष से कम के लिए");
						InstrumentsDTO idto4 = new InstrumentsDTO();
						idto4.setLeaseId("2217");
						idto4.setLeaseName("20 से 30 वर्ष से कम के लिए");
						InstrumentsDTO idto5 = new InstrumentsDTO();
						idto5.setLeaseId("2218");
						idto5.setLeaseName("30 वर्ष  से अधिक या बराबर");
						instdto.setLeasePeriod(new ArrayList());
						instdto.getLeasePeriod().add(idto);
						instdto.getLeasePeriod().add(idto1);
						instdto.getLeasePeriod().add(idto2);
						instdto.getLeasePeriod().add(idto3);
						instdto.getLeasePeriod().add(idto4);
						instdto.getLeasePeriod().add(idto5);
					}
				}
				if (instId == 2025
						&& !"Y".equalsIgnoreCase(dtoId.getCancellationFlag())) {
					dtoId.setCancellationCategoryList(bo
							.cancellationCategoryList(language));
					dtoId.setCancellationCategoryId("5");
					dtoId.setCancellationCategoryName("Miscellaneous");
					dtoId.setCancellationDeedList(bo
							.cancellationDeedList(language));
					dtoId.setCancellationDeedId("1014");
					dtoId.setCancellationDeedName("Cancellation deed");
					dtoId.setCancellationFlag("Y");
					dtoId.setDeedId(0);
					dtoId.setDeedType("");
					instdto.setInstId(0);
					dtoId.setCancellationInstrument(instdto.getInstType());
					instdto.setInstType("");
					dtoId.setDeedCategoryId("");
					dtoId.setDeedCategoryName("");
					dtoId.setCancellationDeedDiscprtion(dtoId
							.getDeedDescription());
					dtoId.setCancellationInstDiscrition(dtoId
							.getInstDescription());
					dtoId.setCancellationRadio("1");
					dtoId.setDeedDescription("");
					dtoId.setInstDescription("");
					eForm.setDutycalculationDTOList(bo.getDeed(dtoId
							.getCancellationCategoryId(), "Y", language, dtoId
							.getFromModule()));
					eForm.setInstrumentDTOList(new ArrayList());
				}
				dtoId.setLandRevenueList(new ArrayList());
				dtoId.setTotalLandRevenue(0);
				dtoId.setMaxLandRevenue(0);
				dtoId.setLandRevenueId(0);
				dtoId.setActionName(null);
				eForm.setDutyCalculationDTO(dtoId);
				eForm.getDutyCalculationDTO().setRadioResiComm3("");
				forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
			}
			if (CommonConstant.DUTY_CALCULATE_ACTION.equals(actionName)) {
				DutyCalculationBO boexemption = new DutyCalculationBO();
				DutyCalculationDTO dutyDTO = eForm.getDutyCalculationDTO();
				InstrumentsDTO instrumentDTO = eForm.getInstDTO();
				ExemptionDTO exemptionDTO = eForm.getExempDTO();
				// RegCommonForm rForm= new RegCommonForm();
				// rForm.setFromAdjudication(eForm.getFromAdjudication());

				// request.setAttribute("fromAdju",eForm.getFromAdjudication());

				logger.debug("from ADJU type"
						+ request.getAttribute("fromAdju"));
				/*
				 * if(rForm.getFromAdjudication()==1){
				 * session.setAttribute("fnName","Adjudication"); }
				 */

				// insert bank details in table
				String tempEfileId = "";
				String dutyId = "";
				dutyId = (String) session.getAttribute("dutyId1");
				tempEfileId = (String) session.getAttribute("regTxnId");

				if (dutyId == null && tempEfileId == null) {
					tempEfileId = eForm.getHidnRegTxnId();
					RegCommonBO commonBo = new RegCommonBO();
					dutyId = commonBo.getdashboarddutyid(tempEfileId);

					duty = Integer.parseInt(dutyId);
				}
				boolean bankdetailinsert = false;

				String disName = bo.getoffNameEfileBank(
						dutyDTO.getDistrictId(), language);
				dutyDTO.setDistrictName(disName);

				bankdetailinsert = bo.insertBankDtls(dutyDTO, dutyId,
						tempEfileId);
				if (bankdetailinsert) {
					String statuspage02 = "52";

					boolean statusUpdate = false;

					statusUpdate = bo.updateStatusBank(tempEfileId,
							statuspage02);
				}

				// end of bank details insert

				logger.debug(exemptionDTO);
				int inst = instrumentDTO.getInstId();
				int deed = dutyDTO.getDeedId();

				if (inst == 0 && deed == 0) {
					String regTxnId = tempEfileId;
					session.setAttribute("regTxnId", regTxnId);
					inst = bo.getinstdashboardid(tempEfileId);
					deed = bo.getdeeddashboardid(tempEfileId);
					String getdeedType = bo.getdeedType(deed);
					String getInstType = bo.getInstType(inst);
					eForm.setModuleName("RegistrationNonProperty");

					if (inst == 2089 || inst == 2265) {
						dutyDTO.setValuationIdValidate("true");
						regFlag = true;
					}
					RegCommonForm regform = new RegCommonForm();
					eForm.getDutyCalculationDTO().setMainDutyTxnId(dutyId);
					eForm.getDutyCalculationDTO().setDeedType(getdeedType);
					eForm.getDutyCalculationDTO().setDeedId(deed);
					eForm.getInstDTO().setInstType(getInstType);
					eForm.getInstDTO().setInstId(inst);
					regform.setDuty_txn_id(duty);
					regform.setInstID(inst);
				}

				System.out.println(exemptionDTO.getHdnExemptions());
				/*
				 * String[] exemptions = exemptionDTO.getHdnExemptions() == null
				 * ? null : exemptionDTO.getHdnExemptions().split(", ");
				 */

				String[] exemptions;

				if (exemptionDTO.getHdnExemptions() != null
						&& !exemptionDTO.getHdnExemptions()
								.equalsIgnoreCase("")) {
					exemptions = exemptionDTO.getHdnExemptions().split(", ");
				} else {
					exemptions = null;
				}

				// String[] exem = exemptionDTO.getHdnExempAmount().split("
				// ~");

				eForm.setSelectedExemptionList(boexemption
						.getExemptionList(exemptions));

				if (eForm.getSelectedExemptionList().size() <= 0) {
					eForm.setSelectedExemptionList(null);
				}

				DecimalFormat myformatter = new DecimalFormat("###.##");

				// bo.captureNonPropStampDetails(eForm.getDutyCalculationDTO(),
				// eForm.getInstDTO(), eForm.getExempDTO());

				//dutyDTO.setBaseValueDisplay(String.valueOf(dutyDTO.getBaseValue
				// ()));
				//dutyDTO.setBaseValueDisplay(String.valueOf(dutyDTO.getBaseValue
				// ()));
				//dutyDTO.setBaseValueDisplay(common.getCurrencyFormatBaseValue(
				// dutyDTO.getBaseValue()));//double to string
				dutyDTO.setBaseValueDisplay(dutyDTO.getBaseValue());// double to
																	// string
				// BigDecimal bigD=new
				// BigDecimal(dutyDTO.getBaseValue()).setScale
				// (20,RoundingMode.HALF_EVEN);

				// new BigDecimal(dutyDTO.getBaseValue()).setScale(7, 7);
				//dutyDTO.setBaseValueDisplay(String.valueOf(bigD.doubleValue())
				// );
				// bigD.setScale(7, 7);

				// added by akansha by convence exemption
				if ((deed == 1053) && (dto.getSelectedExempId().equals("96"))
						&& (inst == 2090)) {
					double d = bo.getExemptionPecentage(dutyDTO);
					dutyDTO.setAnnualRentDisplay(myformatter.format(dutyDTO
							.getAnnualRent()));
					// dutyDTO.setBaseValueDisplay(myformatter.format(dutyDTO.
					// getBaseValue()));
					dutyDTO.setDutyAlreadyPaidDisplay(myformatter
							.format(dutyDTO.getDutyAlreadyPaid()));
					dutyDTO.setDutyPaidDisplay(myformatter.format(dutyDTO
							.getDutyPaid()));
					dutyDTO.setDateCalculation(bo.getCurrentDate());
					dutyDTO.setTotalDisplay(myformatter.format(dutyDTO
							.getTotal()));
					dutyDTO.setNagarPalikaDutyDisplay(myformatter
							.format(dutyDTO.getNagarPalikaDuty()));
					dutyDTO.setPanchayatDutyDisplay(myformatter.format(dutyDTO
							.getPanchayatDuty()));
					dutyDTO.setUpkarDutyDisplay(myformatter.format(dutyDTO
							.getUpkarDuty()));
					dutyDTO.setStampDutyDisplay(myformatter.format(dutyDTO
							.getStampDuty()));
					dutyDTO.setRegFeeDisplay(myformatter.format(dutyDTO
							.getRegFee()));
					dutyDTO.setTotalregFeeDisplay(myformatter.format(dutyDTO
							.getTotalregFee()));
					dutyDTO.setTotalStampDutyDisplay(myformatter.format(dutyDTO
							.getTotalStampDuty()
							* d / 100));
					//dutyDTO.setTotalStampDutyDisplay(myformatter.format(9999))
					// ;

					dutyDTO.setTotalNagarPalikaDisplay(myformatter
							.format(dutyDTO.getTotalNagarPalika()));
					// double Nagarpalika = dutyDTO.getTotalNagarPalika();
					//dutyDTO.setTotalNagarPalikaDisplay(myformatter.format(111)
					// );
					dutyDTO.setTotalPanchyatDisplay(myformatter.format(dutyDTO
							.getTotalPanchyat()));
					//dutyDTO.setTotalPanchyatDisplay(myformatter.format(3434));
					// dutyDTO.setTotalUpkarDisplay(myformatter.format(dutyDTO.
					// getTotalUpkar()));

					double stampNew = Math.ceil(Double.parseDouble((dutyDTO
							.getTotalStampDutyDisplay())));

					dutyDTO.setTotalUpkarDisplay(myformatter
							.format(stampNew * 2.5 / 100));

					double upkar = Math.ceil(Double.parseDouble(dutyDTO
							.getTotalUpkarDisplay()));

					// dutyDTO.setTotalUpkarDisplay(myformatter.format(5645));
					dutyDTO.setEntireTotalDisplay(myformatter.format(dutyDTO
							.getEntireTotal()));
					// dutyDTO.setEntireTotalDisplay(myformatter.format(7547));
					dutyDTO.setTotalPayableStampDisplay(myformatter
							.format(dutyDTO.getTotalPayableStamp()));
					//dutyDTO.setTotalPayableStampDisplay(myformatter.format(654
					// ));
					dutyDTO.setTotalPayableRegDisplay(myformatter
							.format(dutyDTO.getTotalPayableReg()));
					dutyDTO.setDutyafterExemptionDisplay(myformatter
							.format(dutyDTO.getDutyafterExemption()));
					//dutyDTO.setDutyafterExemptionDisplay(myformatter.format(947
					// ));
					// dutyDTO.setDutyafterExemptionDisplayTotal(myformatter.
					// format(dutyDTO.getDutyafterExemptionTotal()));

					dutyDTO.setDutyafterExemptionDisplayTotal(myformatter
							.format(stampNew + dutyDTO.getTotalNagarPalika()
									+ dutyDTO.getTotalPanchyat() + upkar));
					dutyDTO.setDutyafterExemptionTotal(Double
							.parseDouble((dutyDTO
									.getDutyafterExemptionDisplayTotal())));
					// dutyDTO.setDutyafterExemptionDisplayTotal(myformatter.
					// format(543));
					dutyDTO.setRegFeeafterExempDisplay(myformatter
							.format(dutyDTO.getRegFeeafterExemp()));
					dutyDTO.setRegFeeafterExempTotalDisplay(myformatter
							.format(dutyDTO.getRegFeeafterExempTotal()));
					dutyDTO.setPayableRegFeeDisplay(myformatter.format(dutyDTO
							.getPayableRegFee()));
					dutyDTO.setPayableDutyDisplay(myformatter.format(dutyDTO
							.getPayableStampDuty()));

				} else {
					dutyDTO.setAnnualRentDisplay(myformatter.format(dutyDTO
							.getAnnualRent()));
					// dutyDTO.setBaseValueDisplay(myformatter.format(dutyDTO.
					// getBaseValue()));
					dutyDTO.setDutyAlreadyPaidDisplay(myformatter
							.format(dutyDTO.getDutyAlreadyPaid()));
					dutyDTO.setDutyPaidDisplay(myformatter.format(dutyDTO
							.getDutyPaid()));
					dutyDTO.setDateCalculation(bo.getCurrentDate());

					if (dutyDTO.getTotal() == null) {
						String total = myformatter.format(0);
						dutyDTO.setTotalDisplay(total);
					} else {
						String total = myformatter.format(dutyDTO.getTotal());
						dutyDTO.setTotalDisplay(total);
					}

					dutyDTO.setNagarPalikaDutyDisplay(myformatter
							.format(dutyDTO.getNagarPalikaDuty()));
					dutyDTO.setPanchayatDutyDisplay(myformatter.format(dutyDTO
							.getPanchayatDuty()));
					dutyDTO.setUpkarDutyDisplay(myformatter.format(dutyDTO
							.getUpkarDuty()));
					dutyDTO.setStampDutyDisplay(myformatter.format(dutyDTO
							.getStampDuty()));
					dutyDTO.setRegFeeDisplay(myformatter.format(dutyDTO
							.getRegFee()));
					dutyDTO.setTotalregFeeDisplay(myformatter.format(dutyDTO
							.getTotalregFee()));
					dutyDTO.setTotalStampDutyDisplay(myformatter.format(dutyDTO
							.getTotalStampDuty()));
					dutyDTO.setTotalNagarPalikaDisplay(myformatter
							.format(dutyDTO.getTotalNagarPalika()));
					dutyDTO.setTotalPanchyatDisplay(myformatter.format(dutyDTO
							.getTotalPanchyat()));
					dutyDTO.setTotalUpkarDisplay(myformatter.format(dutyDTO
							.getTotalUpkar()));
					dutyDTO.setEntireTotalDisplay(myformatter.format(dutyDTO
							.getEntireTotal()));
					dutyDTO.setTotalPayableStampDisplay(myformatter
							.format(dutyDTO.getTotalPayableStamp()));
					dutyDTO.setTotalPayableRegDisplay(myformatter
							.format(dutyDTO.getTotalPayableReg()));
					dutyDTO.setDutyafterExemptionDisplay(myformatter
							.format(dutyDTO.getDutyafterExemption()));
					dutyDTO.setDutyafterExemptionDisplayTotal(myformatter
							.format(dutyDTO.getDutyafterExemptionTotal()));
					dutyDTO.setRegFeeafterExempDisplay(myformatter
							.format(dutyDTO.getRegFeeafterExemp()));
					dutyDTO.setRegFeeafterExempTotalDisplay(myformatter
							.format(dutyDTO.getRegFeeafterExempTotal()));
					dutyDTO.setPayableRegFeeDisplay(myformatter.format(dutyDTO
							.getPayableRegFee()));
					dutyDTO.setPayableDutyDisplay(myformatter.format(dutyDTO
							.getPayableStampDuty()));
				}
				logger.debug("Stamp Id" + dutyDTO.getStampId());
				request.setAttribute("stampId", dutyDTO.getStampId());
				logger.debug("Stamp Id" + request.getAttribute("stampId"));
				eForm.setDutyCalculationDTO(dutyDTO);
				eForm.setExempDTO(exemptionDTO);
				eForm.setInstDTO(instrumentDTO);

				logger.debug("base Value:-" + dutyDTO.getBaseValue());
				logger.debug("Deed Name:-" + dutyDTO.getDeedType());
				logger.debug("Instrument Name:-" + instrumentDTO.getInstType());
				logger.debug("Exemption Name:-"
						+ exemptionDTO.getHdnExemptions());

				// ADD BELOW STATEMENTS IN DEED-INSTRUMENT CONDITION
				// GET PROP_VAL_REQ_FLAG BASED IN INSTRUMENT ID HERE

				String propValReqFlag = bo.getPropValRequiredFlag(inst);

				if (propValReqFlag.equalsIgnoreCase("Y")
						&& !"true".equalsIgnoreCase(dutyDTO
								.getValuationIdValidate())) {
					request.setAttribute("deedId1", String.valueOf(eForm
							.getDutyCalculationDTO().getDeedId()));
					forwardJsp = CommonConstant.FORWARD_PROP_VAL;
					if ("Y".equalsIgnoreCase(eForm.getDutyCalculationDTO()
							.getRinPustikaCheckFlag())) {
						request.setAttribute("rinPustika", "Y");
					} else {
						request.setAttribute("rinPustika", "N");
					}
					if (eForm.getInstDTO().getInstId() == 2105
							|| eForm.getInstDTO().getInstId() == 2106
							|| eForm.getInstDTO().getInstId() == 2108
							|| eForm.getInstDTO().getInstId() == 2184) {
						request.setAttribute("singleBuyer", "Y");
					} else {
						request.setAttribute("singleBuyer", "N");
					}

					// HashMap<String,String> userValueMap=new
					// HashMap<String,String>();
					ArrayList userList = new ArrayList();
					String param[] = request
							.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "N".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto
									.getInStampDutyAmountFlag())
									&& "Y".equalsIgnoreCase(indto
											.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO()
										.getAsConsiderationAmount();
							} else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidRegFeeFlag())) {
								dto.setAlreadyPaidRegFee(value);
							}
							if ("Y".equalsIgnoreCase(indto
									.getAlreadyPaidStampDutyFlag())) {
								dto.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue(value);
							// userValueMap.put(id,value);
							userList.add(userDto);
						}
						// dto.setUserValueMap(userValueMap);
						dto.setUserValueList(userList);
					}
					dto.setActionName(null);
					actionName = "";

				} else {
					/*
					 * if(eForm.getModuleName()!=null&&eForm.getModuleName().
					 * equalsIgnoreCase(CommonConstant.REGISTRATION_DUTY_PAGE))
					 * {
					 * 
					 * if(propValReqFlag.equalsIgnoreCase("Y")){
					 * 
					 * forwardJsp = CommonConstant.FORWARD_REGINIT_DISPLAY;
					 * 
					 * }else{ forwardJsp =
					 * CommonConstant.FORWARD_NONREGINIT_DISPLAY; }
					 * request.setAttribute("stampId", dutyDTO.getStampId());
					 * request
					 * .setAttribute("fromAdju",eForm.getFromAdjudication());
					 * dto.setActionName(null); actionName=""; } else
					 */
					// {
					if (dto.getDeedId() == 1054) {
						if (dto.getExchangeList1().size() == 0
								&& dto.getExchangeList2().size() == 0) {
							if ("en".equalsIgnoreCase(language)) {
								request
										.setAttribute("ERROR",
												"Please add property for both transactiong parties");
							} else {
								request
										.setAttribute("ERROR",
												"कृपया दोनों पार्टी के लिए संपत्ति जोड़ें|");

							}
							regFlag = false;
							eForm.getDutyCalculationDTO().setRadioResiComm3("");
							forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
							dto.setActionName(null);
							actionName = "";
						} else if (dto.getExchangeList1().size() == 0) {
							if ("en".equalsIgnoreCase(language)) {
								request
										.setAttribute("ERROR",
												"Please add property for first transacting party");
							} else {
								request
										.setAttribute("ERROR",
												"कृपया प्रथम पार्टी के लिए संपत्ति जोड़ें|");

							}
							regFlag = false;
							eForm.getDutyCalculationDTO().setRadioResiComm3("");
							forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
							dto.setActionName(null);
							actionName = "";
						} else if (dto.getExchangeList2().size() == 0) {
							if ("en".equalsIgnoreCase(language)) {
								request
										.setAttribute("ERROR",
												"Please add property for second transactiong party");
							} else {
								request
										.setAttribute("ERROR",
												"कृपया दूसरी  पार्टी के लिए संपत्ति जोड़ें| ");

							}
							regFlag = false;
							eForm.getDutyCalculationDTO().setRadioResiComm3("");
							forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
							dto.setActionName(null);
							actionName = "";
						} else {
							// String exedate= eForm.getSlotdate();
							// bo.submitDutyDetails(dutyDTO,
							// eForm.getInstDTO(),exedate);
							forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
							dto.setActionName(null);
							actionName = "";
						}
					} else if (eForm.getInstDTO().getInstId() == 2105
							|| eForm.getInstDTO().getInstId() == 2106
							|| eForm.getInstDTO().getInstId() == 2108
							|| eForm.getInstDTO().getInstId() == 2184) {
						double separtedPart = Double.parseDouble(dto
								.getNoOfSperatedPart());
						if (separtedPart < 2) {
							if ("en".equalsIgnoreCase(language)) {
								request
										.setAttribute("ERROR",
												"No of Separated Parts must be greater than equal to 2");
							} else {
								request
										.setAttribute("ERROR",
												"हिस्सों की संख्या 2 के बराबर या उससे अधिक होनी चाहिए");

							}
							regFlag = false;

						} else if (separtedPart != dto.getPropertyDetailsList()
								.size()) {
							if ("en".equalsIgnoreCase(language)) {
								request
										.setAttribute("ERROR",
												"No of Separated Parts must be equal to no of Properties");
							} else {
								request
										.setAttribute("ERROR",
												"हिस्सों की संख्या संपत्तियों की संख्या के बराबर होनी चाहिए।");

							}
							regFlag = false;

						} else if (eForm.getInstDTO().getInstId() == 2105
								|| eForm.getInstDTO().getInstId() == 2106
								|| eForm.getInstDTO().getInstId() == 2108
								|| eForm.getInstDTO().getInstId() == 2184) {
							if (bo.partitionvalidation(dto
									.getPropertyDetailsList()))// change
																// partition
																// validation
																// here...Roopam
							{
								// String exedate= eForm.getSlotdate();
								// bo.submitDutyDetails(dutyDTO,
								// eForm.getInstDTO(),exedate);
								forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
								dto.setActionName(null);
								actionName = "";
							} else {
								if ("en".equalsIgnoreCase(language)) {
									request
											.setAttribute(
													"ERROR",
													"If you intend to do the partition of only undiverted agricultural land please refer instrument 'Partition of Agricultural land' ");
								} else {
									request
											.setAttribute(
													"ERROR",
													"केवल अव्यपवर्तित कृषि भूमि के विभाजन की अनुमति नहीं है.।एक अलग  प्रकार की संपत्ति जोडे|");

								}
								regFlag = false;

							}
						} else {
							// String exedate= eForm.getSlotdate();
							// bo.submitDutyDetails(dutyDTO,
							// eForm.getInstDTO(),exedate);
							forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
							dto.setActionName(null);
							actionName = "";
						}
					} else if (eForm.getInstDTO().getInstId() == 2107) {
						if (dto.getLandRevenueList().size() < 2) {
							if ("en".equalsIgnoreCase(language)) {
								request
										.setAttribute("ERROR",
												"No of Separated Parts must be greater than equal to 2");
							} else {
								request
										.setAttribute("ERROR",
												"हिस्सों की संख्या 2 के बराबर या उससे अधिक होनी चाहिए");

							}
							regFlag = false;

						} else {
							// String exedate= eForm.getSlotdate();
							// bo.submitDutyDetails(dutyDTO,
							// eForm.getInstDTO(),exedate);
							forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
							dto.setActionName(null);
							actionName = "";

						}
					} else {
						// String exedate= eForm.getSlotdate();
						// bo.submitDutyDetails(dutyDTO,
						// eForm.getInstDTO(),exedate);
						// bo.submitBankDetails(dutyDTO);
						forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
						dto.setActionName(null);
						actionName = "";
					}
					// }

					if (eForm.getModuleName() != null
							&& eForm.getModuleName().equalsIgnoreCase(
									CommonConstant.REGISTRATION_DUTY_PAGE)
							&& regFlag == true) {
						if (propValReqFlag.equalsIgnoreCase("Y")) {
							forwardJsp = CommonConstant.FORWARD_REGINIT_DISPLAY;
							request.setAttribute("fromAdju", eForm
									.getFromAdjudication());

						} else {
							forwardJsp = CommonConstant.FORWARD_NONREGINIT_DISPLAY;
							// request.setAttribute("stampId",
							// dutyDTO.getStampId());
							request.setAttribute("fromAdju", eForm
									.getFromAdjudication());
						}
						request.setAttribute("eForm", eForm);

						dto.setActionName(null);
						actionName = "";
					}
					if (eForm.getDutyCalculationDTO().getFromModule() != null) {
						if (eForm.getDutyCalculationDTO().getFromModule()
								.equalsIgnoreCase("eStamping")
								&& regFlag == true) {
							request.setAttribute("dutyDTO", eForm
									.getDutyCalculationDTO());
							request.setAttribute("instDTO", eForm.getInstDTO());
							forwardJsp = CommonConstant.TO_ESTAMP;
							dto.setActionName(null);
							actionName = "";
						}
					}

				}

			}
			if (CommonConstant.CANCEL_ACTION.equals(actionName)) {
				dto.setActionName("");
				forwardJsp = "welcome";
			}
			if (CommonConstant.RESET_PAGE_ACTION.equals(actionName)) {
				eForm.setExemptionDTOList(new ArrayList());
				eForm.setInstrumentDTOList(new ArrayList());
				DutyCalculationDTO dto1 = new DutyCalculationDTO();

				/*
				 * dto.setFirstName(dto1.getFirstName());
				 * dto.setMidName(dto1.getMidName());
				 * dto.setLastName(dto1.getLastName());
				 * dto.setAge(dto1.getAge());
				 * dto.setSex("Male".equals(dto1.getSex()) ? "M" : "Female"
				 * .equals(dto1.getSex()) ? "F" : "");
				 */
				dto.setDeedId(dto1.getDeedId());
				dto.setBaseValue("0.0");// double to string

				eForm.setInstDTO(new InstrumentsDTO());
				eForm.setDutyCalculationDTO(dto);
				eForm.getDutyCalculationDTO().setRadioResiComm3("");
				forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				dto.setActionName(null);
				actionName = "";
			}
			if (CommonConstant.RETURN_FROM_PROP_VAL.equals(actionName)) {

				PropertyValuationDTO propDTO = new PropertyValuationDTO();
				if (request.getAttribute("propDTO") != null) {

					propDTO = (PropertyValuationDTO) request
							.getAttribute("propDTO");
					// use propDTO for final screen designing of duty
					// calculation involving property details or forwarding to
					// reg init
				}

				DutyCalculationDTO dutyDTO = eForm.getDutyCalculationDTO();
				//dutyDTO.setValTxnId((String)request.getAttribute("valuationId"
				// ));
				// above line commented because valuation id is available in
				// propDTO.
				// changes by preeti on 23 september 2016
				eForm.getDutyCalculationDTO().setRadioResiComm3(
						dutyDTO.getRadioResiComm3());
				eForm.getDutyCalculationDTO().setPropertyoutMP(
						dutyDTO.getPropertyoutMP());
				if (dutyDTO.getPropertyoutMP() != null
						&& !dutyDTO.getPropertyoutMP().isEmpty()) {
					eForm.setPropertyoutMPFlag("Y");
				} else {
					eForm.setPropertyoutMPFlag("");
				}
				dutyDTO.setPropDTO(propDTO);
				if (propDTO.getFinalMarketValue() == null
						|| propDTO.getFinalMarketValue().equalsIgnoreCase("")
						|| "3".equalsIgnoreCase(propDTO.getPropertyName())
						|| "1".equalsIgnoreCase(propDTO.getPropertyName())) {
					propDTO.setFinalMarketValue(propDTO.getPlotMarketValue());
				}
				/*
				 * if(eForm.getModuleName()!=null&&eForm.getModuleName().
				 * equalsIgnoreCase(CommonConstant.REGISTRATION_DUTY_PAGE)) {
				 * forwardJsp = CommonConstant.FORWARD_REGINIT_DISPLAY; // this
				 * section code will only be executed if selected instrument
				 * involved property valuation.
				 * 
				 * 
				 * request.setAttribute("eForm", eForm);
				 * //request.setAttribute("stampId", dutyDTO.getStampId());
				 * //request
				 * .setAttribute("fromAdju",eForm.getFromAdjudication());
				 * 
				 * dto.setActionName(null); actionName=""; }
				 */
				// else
				{
					if (dutyDTO.getDeedId() == 1054) {
						DutyCalculationDTO ddto = new DutyCalculationDTO();
						ddto.setPropertyName(bo.getPropertyName(propDTO
								.getPropertyName(), language));
						ddto.setPropertyId(propDTO.getPropertyName());
						ddto.setMarketValue(propDTO.getFinalMarketValue());
						DecimalFormat myformatter1 = new DecimalFormat("###.##");
						ddto.setMarketValueDisplay(myformatter1.format(Double
								.parseDouble(ddto.getMarketValue())));
						ddto.setLandId(propDTO.getAgriLandTypeId());
						if (propDTO.getAgriTotalUnDivertedArea() != null
								&& !propDTO.getAgriTotalUnDivertedArea()
										.equalsIgnoreCase("")) {
							ddto.setPropArea(Double.parseDouble(propDTO
									.getAgriTotalUnDivertedArea()));
						}
						if ("3".equals(propDTO.getPropertyName())) {
							if ("firstParty".equalsIgnoreCase(dutyDTO
									.getExchangeParty())) {
								dutyDTO.setRinPustikaVisible1("Y");
							} else {
								dutyDTO.setRinPustikaVisible2("Y");
							}
							ddto.setValuationId(bo.getValuationIdAGri(propDTO
									.getValuationId()));

						} else {
							// dutyDTO.setRinPustikaVisible("N");

							ddto.setValuationId(propDTO.getValuationId());

						}
						if ("firstParty".equalsIgnoreCase(dutyDTO
								.getExchangeParty())) {
							dutyDTO.getExchangeList1().add(ddto);
							dutyDTO.setPartyAdded("firstParty");
							dutyDTO
									.setExchangeMV1(dutyDTO.getExchangeMV1()
											+ Double.parseDouble(ddto
													.getMarketValue()));
						} else {
							dutyDTO.getExchangeList2().add(ddto);
							dutyDTO.setPartyAdded("secondParty");
							dutyDTO
									.setExchangeMV2(dutyDTO.getExchangeMV2()
											+ Double.parseDouble(ddto
													.getMarketValue()));

						}
						dutyDTO.setValuationIdValidate("true");
						dutyDTO.setAddPropertyFlag("N");
						dutyDTO.setHvValIdFlag("N");
						dutyDTO.setHvValId("");
						dutyDTO.setExchangeParty("");

						eForm.setDutyCalculationDTO(dutyDTO);
						// eForm.getDutyCalculationDTO().setRadioResiComm3("");
						forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
						dto.setActionName(null);
						actionName = "";
					} else if (eForm.getInstDTO().getInstId() == 2105
							|| eForm.getInstDTO().getInstId() == 2106
							|| eForm.getInstDTO().getInstId() == 2108
							|| eForm.getInstDTO().getInstId() == 2184) {

						DutyCalculationDTO ddto = new DutyCalculationDTO();
						ddto.setPropertyName(bo.getPropertyName(propDTO
								.getPropertyName(), language));
						ddto.setPropertyId(propDTO.getPropertyName());
						ddto.setLandId(propDTO.getAgriLandTypeId());
						if ("3".equals(propDTO.getPropertyName())) {
							dutyDTO.setRinPustikaVisible("Y");
							ddto.setValuationId(bo.getValuationIdAGri(propDTO
									.getValuationId()));

						} else {
							// dutyDTO.setRinPustikaVisible("N");

							ddto.setValuationId(propDTO.getValuationId());

						}
						ddto.setMarketValue(propDTO.getFinalMarketValue());
						DecimalFormat myformatter1 = new DecimalFormat("###.##");
						ddto.setMarketValueDisplay(myformatter1.format(Double
								.parseDouble(ddto.getMarketValue())));
						dutyDTO.setPartitionMV(dutyDTO.getPartitionMV()
								+ Double.parseDouble(ddto.getMarketValue()));
						if (dutyDTO.getMaxPartitionMv() <= Double
								.parseDouble(ddto.getMarketValue())) {
							dutyDTO.setMaxPartitionMv(Double.parseDouble(ddto
									.getMarketValue()));
						}
						ddto.setAgriApplicableSubclauseId(propDTO
								.getAgriApplicableSubclauseId());// adde by
																	// roopam
																	// -14april15
						dutyDTO.getPropertyDetailsList().add(ddto);
						eForm.getInstDTO().setLeaseFreeze("Y");
						dutyDTO.setValuationIdValidate("true");
						dutyDTO.setAddPropertyFlag("N");
						dutyDTO.setHvValIdFlag("N");
						dutyDTO.setHvValId("");
						dutyDTO.setExchangeParty("");

						eForm.setDutyCalculationDTO(dutyDTO);
						eForm.getDutyCalculationDTO().setRadioResiComm3("");
						forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
						dto.setActionName(null);
						actionName = "";

					} else {
						// use dutyDTO.getPropDTO() for displaying property
						// details on dc final screen.
						DutyCalculationDTO ddto = new DutyCalculationDTO();
						ddto.setPropertyName(bo.getPropertyName(propDTO
								.getPropertyName(), language));
						ddto.setPropertyId(propDTO.getPropertyName());

						if ("3".equals(propDTO.getPropertyName())) {
							dutyDTO.setRinPustikaVisible("Y");
							ddto.setValuationId(bo.getValuationIdAGri(propDTO
									.getValuationId()));

						} else {
							// dutyDTO.setRinPustikaVisible("N");

							ddto.setValuationId(propDTO.getValuationId());

						}
						ddto.setMarketValue(propDTO.getFinalMarketValue());
						DecimalFormat myformatter1 = new DecimalFormat("###.##");
						ddto.setMarketValueDisplay(myformatter1.format(Double
								.parseDouble(ddto.getMarketValue())));
						// ddto.setUserValueMap(dto.getUserValueMap());
						ddto.setUserValueList(dto.getUserValueList());
						String stampDuty = new CalculateDuty()
								.calculateStampDuty(eForm.getInstDTO(),
										dutyDTO, ddto);
						double stamp = Double.parseDouble(stampDuty);
						ddto.setStampDuty(Double.parseDouble(stampDuty));
						String regFee = new CalculateRegFee().calculateRegFee(
								eForm.getInstDTO(), dutyDTO, ddto);
						double upkarDuty = new CalculateDuty().calculateUpkar(
								eForm.getInstDTO().getInstId(), Double
										.parseDouble(stampDuty));
						ddto.setUpkarDuty(upkarDuty);
						double nagarpalika = new CalculateDuty()
								.calculateMuncipalDuty(eForm.getInstDTO(),
										dutyDTO, ddto);
						// if(nagarpalika<=stamp)
						{
							ddto.setNagarPalikaDuty(nagarpalika);
						}
						/*
						 * else { ddto.setNagarPalikaDuty(stamp); }
						 */
						double panchayat = new CalculateDuty()
								.calculateJanPadDuty(eForm.getInstDTO(),
										dutyDTO, ddto);
						// if(panchayat<=stamp)
						{
							ddto.setPanchayatDuty(panchayat);
						}
						/*
						 * else { ddto.setPanchayatDuty(stamp); }
						 */
						double total = Double.parseDouble(stampDuty)
								+ upkarDuty + nagarpalika + panchayat;
						double paidRegFee = 0;
						double paidStampduty = 0;
						if (dutyDTO.getAlreadyPaidRegFee() != null
								&& !dutyDTO.getAlreadyPaidRegFee()
										.equalsIgnoreCase("")) {
							paidRegFee = Double.parseDouble(dutyDTO
									.getAlreadyPaidRegFee());
						}
						if (dutyDTO.getAlreadyPaidStampDuty() != null
								&& !dutyDTO.getAlreadyPaidStampDuty()
										.equalsIgnoreCase("")) {
							paidStampduty = Double.parseDouble(dutyDTO
									.getAlreadyPaidStampDuty());
						}
						if (((Double.parseDouble(regFee)) - paidRegFee) < 0) {
							ddto.setPayableRegFee(0);
						} else {
							ddto.setPayableRegFee((Double.parseDouble(regFee))
									- paidRegFee);
						}
						if ((total - paidStampduty) < 0) {
							ddto.setPayableStampDuty(0);
						} else {
							ddto.setPayableStampDuty(total - paidStampduty);
						}
						DecimalFormat myformatter = new DecimalFormat("###.##");
						ddto.setPremium(dutyDTO.getPremium());
						ddto.setAdditionalPremium(dutyDTO
								.getAdditionalPremium());
						ddto.setOtherCharges(dutyDTO.getOtherCharges());
						ddto.setDevelopmentCharges(dutyDTO
								.getDevelopmentCharges());
						ddto.setRentArrayList(dutyDTO.getRentArrayList());
						ddto.setAlreadyPaidRegFee(dutyDTO
								.getAlreadyPaidRegFee());
						ddto.setAlreadyPaidStampDuty(dutyDTO
								.getAlreadyPaidStampDuty());
						ddto.setTotal(total);

						dto.setPaidStamp(paidStampduty);
						ddto.setPaidReg(paidRegFee);
						ddto.setRegFee(Double.parseDouble(regFee));

						ddto.setDashReg((myformatter.format(ddto.getRegFee())));
						ddto.setDashSatmp(myformatter.format(ddto
								.getStampDuty()));
						ddto.setDashMunicipal(myformatter.format(ddto
								.getNagarPalikaDuty()));
						ddto.setDashJanpad(myformatter.format(ddto
								.getPanchayatDuty()));
						ddto.setDashUpkar(myformatter.format(ddto
								.getUpkarDuty()));
						ddto.setDashTotal(myformatter.format(ddto.getTotal()));
						ddto.setDashPayTotal(myformatter.format(ddto
								.getPayableStampDuty()));
						ddto.setDashPayReg(myformatter.format(ddto
								.getPayableRegFee()));
						ddto.setGovValue(dutyDTO.getGovValue());
						double govtValue = 0;
						if (dutyDTO.getGovValue() != null
								&& !dutyDTO.getGovValue().equalsIgnoreCase("")) {
							govtValue = Double.parseDouble(dutyDTO
									.getGovValue());
						}
						dutyDTO.setTotalGovValue(dutyDTO.getTotalGovValue()
								+ govtValue);
						dutyDTO.setTotalStampDuty(dutyDTO.getTotalStampDuty()
								+ Double.parseDouble(stampDuty));
						dutyDTO.setTotalregFee(dutyDTO.getTotalregFee()
								+ Double.parseDouble(regFee));
						dutyDTO.setTotalUpkar(dutyDTO.getTotalUpkar()
								+ upkarDuty);
						dutyDTO.setTotalNagarPalika(dutyDTO
								.getTotalNagarPalika()
								+ nagarpalika);
						dutyDTO.setTotalPanchyat(dutyDTO.getTotalPanchyat()
								+ panchayat);
						dutyDTO
								.setEntireTotal(dutyDTO.getEntireTotal()
										+ total);
						dutyDTO.setTotalPaidStamp(dutyDTO.getTotalPaidStamp()
								+ paidStampduty);
						dutyDTO.setTotalPaidReg(dutyDTO.getTotalPaidReg()
								+ paidRegFee);
						dutyDTO.setTotalPayableReg(dutyDTO.getTotalPayableReg()
								+ ddto.getPayableRegFee());
						dutyDTO.setTotalPayableStamp(dutyDTO
								.getTotalPayableStamp()
								+ ddto.getPayableStampDuty());
						dutyDTO.getPropertyDetailsList().add(ddto);
						dutyDTO.setValuationIdValidate("true");
						dutyDTO.setAddPropertyFlag("N");
						dutyDTO.setHvValIdFlag("N");
						dutyDTO.setHvValId("");
						dutyDTO.setActionName(null);
						eForm.setDutyCalculationDTO(dutyDTO);
						// eForm.getDutyCalculationDTO().setRadioResiComm3("");
						forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
						dto.setActionName(null);
						actionName = "";
					}
				}
			}
		}

		return mapping.findForward(forwardJsp);
	}
}
