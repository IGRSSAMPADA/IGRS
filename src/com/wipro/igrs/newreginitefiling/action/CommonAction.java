package com.wipro.igrs.newreginitefiling.action;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.RegCompMaker.dto.RegCompleteMakerDTO;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.commonEfiling.PropertiesFileReader;
import com.wipro.igrs.commonEfiling.mime.MIMECheck;
import com.wipro.igrs.newPropvaluationefiling.dto.PropertyValuationDTO;
import com.wipro.igrs.newdutycalculationefiling.bo.DutyCalculationBO;
import com.wipro.igrs.newdutycalculationefiling.constant.CommonConstant;
import com.wipro.igrs.newdutycalculationefiling.form.DutyCalculationForm;
import com.wipro.igrs.newreginitefiling.bo.RegCommonBO;
import com.wipro.igrs.newreginitefiling.constant.RegInitConstant;
import com.wipro.igrs.newreginitefiling.dto.CommonDTO;
import com.wipro.igrs.newreginitefiling.dto.RegCommonDTO;
import com.wipro.igrs.newreginitefiling.dto.RegCompletDTO;
import com.wipro.igrs.newreginitefiling.form.RegCommonForm;

public class CommonAction extends BaseAction {
	private Logger logger = (Logger) Logger.getLogger(CommonAction.class);

	@SuppressWarnings({"unchecked", "null"})
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		HashMap map = null;
		HashMap map2 = null;
		String fwdPage = request.getParameter("fwdName");
		String pathfile = request.getParameter("pathfile");
		String forward = "";
		RegCommonBO commonBo = new RegCommonBO();
		DutyCalculationForm dutyForm = new DutyCalculationForm();
		RegCommonForm regForm;
		String TempIdPay = "";
		String DutyIdCheck = "";
		String EfileId = "";
		CommonActionUtilities utilities = new CommonActionUtilities();
		if (request.getAttribute("regFormDashboard") != null) {
			regForm = (RegCommonForm) request.getAttribute("regFormDashboard");
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			request.setAttribute("roleId", regForm.getPartyType());
		} else
			regForm = (RegCommonForm) form;

		if (request.getParameter("confirmation") != null) {
			if (request.getParameter("confirmation").equalsIgnoreCase("Y")) {
				regForm.setActionName(RegInitConstant.NO_ACTION);
			}

		}
		if (request.getParameter("multipleProps") != null) {
			if (request.getParameter("multipleProps").equalsIgnoreCase("Y")) {

				regForm.setActionName(RegInitConstant.NO_ACTION);

			}

		}

		if (request.getAttribute("propDTO") != null) {
			PropertyValuationDTO propDTO = (PropertyValuationDTO) request.getAttribute("propDTO");

			regForm.setIsMultiplePropsFlag(propDTO.getIsMultipleProperty());

		} else {
			regForm.setIsMultiplePropsFlag(0);

		}

		RegCommonDTO commonDto;

		if (regForm.getCommonDto() != null) {
			commonDto = regForm.getCommonDto();
			commonDto.setTehsilName("");
			commonDto.setTehsilName1("");
		} else {
			commonDto = new RegCommonDTO();
			commonDto.setState(new ArrayList());
			commonDto.setDistrict(new ArrayList());
			commonDto.setIndstate(new ArrayList());
			commonDto.setInddistrict(new ArrayList());
			commonDto.setInddistrictTrns(new ArrayList());
			commonDto.setTehsilName("");
			commonDto.setTehsilName1("");
		}

		String languageLocale = "hi";
		if (session.getAttribute("languageLocale") != null) {
			languageLocale = (String) session.getAttribute("languageLocale");
		}

		if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
			session.setAttribute("modName", RegInitConstant.MODNAME_ENGLISH);
		} else {
			session.setAttribute("modName", RegInitConstant.MODNAME_HINDI);
		}
		commonDto.setLanguage(languageLocale);

		if (fwdPage != null && "viewPropertyUpload".equalsIgnoreCase(fwdPage)) {

			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			String downloadPath = pr.getValue("igrs_upload_path");
			String deedpath = (String) request.getParameter("fileName");
			String EfilingPath = deedpath + File.separator + "AdditionalUpload.Pdf";
			logger.debug("EfilingPath additional uploa----------->" + EfilingPath);
			byte bytes[] = DMSUtility.getDocumentBytes(EfilingPath);
			if (bytes != null) {
				logger.debug("byte not null addtional upload---------->" + EfilingPath);
				DMSUtility.downloadDocument(response, EfilingPath, bytes);

				forward = "podisplaydutybank";
				logger.debug("forward additional upload---------->" + forward);
				return mapping.findForward(forward);
			}
		}
		if (fwdPage != null && "viewPropertyUpload1".equalsIgnoreCase(fwdPage)) {

			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			String downloadPath = pr.getValue("igrs_upload_path");
			String deedpath = (String) request.getParameter("fileName");
			String EfilingPath = deedpath + File.separator + "AdditionalUpload.Pdf";

			byte bytes[] = DMSUtility.getDocumentBytes(EfilingPath);
			if (bytes != null) {
				DMSUtility.downloadDocument(response, EfilingPath, bytes);

				forward = "srdispalyduty";
				return mapping.findForward(forward);
			}
			// Added by gulrej 0n 13/6/17
			if (languageLocale.equalsIgnoreCase("en"))
				request.setAttribute(RegInitConstant.FAIL_MSG, "No Record Found");
			else
				request.setAttribute(RegInitConstant.FAIL_MSG, "कोई रिकॉर्ड नहीं मिला");
			forward = "srdispalyduty";
			return mapping.findForward(forward);
		}

		if (fwdPage != null && "downloadDeed".equalsIgnoreCase(fwdPage)) {

			logger.debug("CommonAction :: downloadDeed :: Start");

			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			String downloadPath = pr.getValue("igrs_efiling_forms_path");
			String deedpath = (String) request.getParameter("downloadPath");
			String EfilingPath = downloadPath.trim() + pathfile.trim(); // Updated
																		// By
																		// Gulrej
																		// on
																		// 21st
																		// Aug,
																		// 2017

			logger.debug("CommonAction :: downloadDeed :: " + EfilingPath.trim());
			// String EfilingPath = downloadPath + pathfile;

			byte bytes[] = DMSUtility.getDocumentBytes(EfilingPath);
			if (bytes != null) {
				DMSUtility.downloadDocument(response, pathfile, bytes);
				forward = "successefiling";
				logger.debug("CommonAction :: downloadDeed :: " + forward);
				return mapping.findForward(forward);
			}
			logger.debug("CommonAction :: downloadDeed :: Not able to downlaod");
			forward = "successefiling";
			return mapping.findForward(forward);
		}

		logger.debug("CommonAction :: downloadDeed :: End  ");

		if (fwdPage != null && "downloadUploadedForm".equalsIgnoreCase(fwdPage)) {
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

			String efileTxnId = (String) session.getAttribute("regTxnId");
			String downloadPath = pr.getValue("igrs_upload_path");
			String deedpath = (String) request.getParameter("downloadPath");
			String EfilingUploadPath = downloadPath + RegInitConstant.FILE_UPLOAD_PATHFILE + efileTxnId + pathfile;

			byte bytes[] = DMSUtility.getDocumentBytes(EfilingUploadPath);
			if (bytes != null) {
				DMSUtility.downloadDocument(response, pathfile, bytes);

				forward = "successefiling";
				return mapping.findForward(forward);
			}
		}
		if (fwdPage != null && "UploadSRView".equalsIgnoreCase(fwdPage)) {
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

			String efileTxnId = (String) session.getAttribute("TempId");
			String downloadPath = pr.getValue("igrs_upload_path");
			String deedpath = (String) request.getParameter("downloadPath");
			String EfilingUploadPathSR = downloadPath + RegInitConstant.FILE_UPLOAD_PATH_SR + efileTxnId + pathfile;

			byte bytes[] = DMSUtility.getDocumentBytes(EfilingUploadPathSR);
			if (bytes != null) {
				DMSUtility.downloadDocument(response, pathfile, bytes);
				forward = "srUploadViewSuccess";
				return mapping.findForward(forward);
			}
		}
		if (fwdPage != null && "UploadSRReject".equalsIgnoreCase(fwdPage)) {
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

			String efileTxnId = (String) session.getAttribute("TempId");
			String downloadPath = pr.getValue("igrs_upload_path");
			String deedpath = (String) request.getParameter("downloadPath");
			String EfilingUploadPathSR = downloadPath + RegInitConstant.FILE_UPLOAD_PATH_SR + efileTxnId + pathfile;

			byte bytes[] = DMSUtility.getDocumentBytes(EfilingUploadPathSR);
			if (bytes != null) {
				DMSUtility.downloadDocument(response, pathfile, bytes);
				forward = "srdisplayReject1";
				return mapping.findForward(forward);
			}
		}
		if (fwdPage != null && "viewPOUploadedForm".equalsIgnoreCase(fwdPage)) {
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

			String downloadPath = (String) request.getParameter("fileName");
			String uploadFileName = (String) request.getParameter("pathfile");

			String EfilingUploadPathSR = downloadPath + File.separator + "UploadForm.Pdf";
			logger.debug("EfilingUploadPathSR upload form----------->" + EfilingUploadPathSR);
			byte bytes[] = DMSUtility.getDocumentBytes(EfilingUploadPathSR);
			if (bytes != null) {
				logger.debug("byte not null upload form----------->" + EfilingUploadPathSR);
				DMSUtility.downloadDocument(response, EfilingUploadPathSR, bytes);
				forward = "podisplaydutybank";
				logger.debug("forward upload form----------->" + forward);
				return mapping.findForward(forward);
			}
		}
		if (fwdPage != null && "viewPOUploadedForm1".equalsIgnoreCase(fwdPage)) {
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

			String downloadPath = (String) request.getParameter("fileName");
			String uploadFileName = (String) request.getParameter("pathfile");

			String EfilingUploadPathSR = downloadPath + File.separator + "UploadForm.Pdf";

			byte bytes[] = DMSUtility.getDocumentBytes(EfilingUploadPathSR);
			if (bytes != null) {
				DMSUtility.downloadDocument(response, EfilingUploadPathSR, bytes);
				forward = "srdispalyduty";
				return mapping.findForward(forward);
			}

			// Added by gulrej 0n 12/5/17
			if (languageLocale.equalsIgnoreCase("en"))
				request.setAttribute(RegInitConstant.FAIL_MSG, "No Record Found");
			else
				request.setAttribute(RegInitConstant.FAIL_MSG, "कोई रिकॉर्ड नहीं मिला");
			forward = "srdispalyduty";
			return mapping.findForward(forward);
		}
		if (request.getParameter("modName") != null) {
			if (request.getParameter("modName").equalsIgnoreCase("PODashboard Process")) {
				ArrayList pendingApplicationListPo = commonBo.getPendingEfileAppsPo(session.getAttribute("UserId").toString(), languageLocale);

				if (pendingApplicationListPo.size() == 0)
					regForm.setPendingApplicationListPo(new ArrayList());
				else
					regForm.setPendingApplicationListPo(pendingApplicationListPo);

				request.setAttribute("pendingApplicationListPo", regForm.getPendingApplicationListPo());
				regForm.setEfileNumber("");
				regForm.setDurationTo("");
				regForm.setDurationFrom("");
				forward = "DashboardPO";
				return mapping.findForward(forward);
			}

		}
		if (request.getParameter("modName") != null) {

			if (request.getParameter("modName").equalsIgnoreCase("RegistrationProperty") || request.getParameter("modName").equalsIgnoreCase("Registration Process")) {

				if (regForm != null) {

					commonBo.setRegForm(regForm);
					commonDto.setTehsilId1("");
					regForm.setConsiAmountTrns("");
					regForm.setExtraFieldLabel(RegInitConstant.EXTRA_FIELD_NOT_APPLICABLE);

					regForm.setContriProp("");

					regForm.setDissolutionDate("");
					regForm.setRetirmentDate("");

					regForm.setAgreeMemoInstFlag(0);
					regForm.setClaimantFlag(0);
					regForm.setPoaHolderFlag(0);
					regForm.setCommonDeed(0);
					regForm.setAddPartyNewRole(0); // variable for radio. value
					regForm.setRoleSameAsPrevious(0); // flag for above radio. 1
					regForm.setCommonDeedRoleName("");
					regForm.setAddMoreTransParty("N");
					regForm.setAddMoreParticularCounter(0);
					regForm.setMapParticulars(new HashMap());
					regForm.setParticularName("");
					regForm.setParticularDesc("");
					regForm.setDeleteParticularID("");
					regForm.setHdnDeleteParticularID("");

					regForm.setIsPoaAddedFlag(0);
					regForm.setApplicantRoleId(0);

					regForm.setInitiateAdjuApp(0);
					regForm.setStampManually("N");
					regForm.setStampduty1("");
					regForm.setNagarPalikaDuty("");
					regForm.setPanchayatDuty("");
					regForm.setUpkarDuty("");
					regForm.setTotalduty("");
					regForm.setRegistrationFee("");
					regForm.setStampduty1Adju("");
					regForm.setNagarPalikaDutyAdju("");
					regForm.setPanchayatDutyAdju("");
					regForm.setUpkarDutyAdju("");
					regForm.setTotaldutyAdju("");
					regForm.setRegistrationFeeAdju("");

					regForm.setIsFirstPartyAddedFlag(0);

					regForm.setExecutantClaimant(0);
					regForm.setExecutantClaimantTrns(0);

					regForm.setAuthPerAddress("");
					regForm.setAuthPerCountry("1");
					regForm.setAuthPerDistrict("");
					regForm.setAuthPerStatename("1");
					regForm.setAuthPerFatherName("");
					regForm.setAuthPerRelationship(0);
					regForm.setAuthPerGendar("M");

					regForm.setAuthPerAddressTrns("");
					regForm.setAuthPerCountryTrns("1");
					regForm.setAuthPerDistrictTrns("");
					commonDto.setTehsilId1("");
					regForm.setAuthPerStatenameTrns("1");
					regForm.setAuthPerFatherNameTrns("");
					regForm.setAuthPerRelationshipTrns(0);
					regForm.setAuthPerGendarTrns("M");

					regForm.setAdjuRemarks("");

					regForm.setExecutionDate("");
					regForm.setRegistrationDate("");
					regForm.setRegistrationNo("");
					regForm.setReceiptAmount(0);
					regForm.setReceiptAmountDisp("");
					regForm.setBankName("");

					regForm.setCancellationLabel("");
					regForm.setAddPropertyOptional("");
					regForm.setHdnAddPropertyOptional("off");
					commonDto.setIndtehsil(new ArrayList());
					if (regForm.getIsMultiplePropsFlag() == 0) {

						commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendar(), languageLocale));
						commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendar(), languageLocale));

						regForm.setFromAdjudication(0);
						if (request.getParameter("regStatus") != null) {
							if (request.getParameter("regStatus").equalsIgnoreCase(RegInitConstant.STATUS_TRNS_BANK1)) {
								String hindReg = regForm.getHidnRegTxnId();
								regForm.setHidnRegTxnId(hindReg);

							}
						}
						/*
						 * else{ regForm.setHidnRegTxnId(""); }
						 *//* Commented by Gulrej on 5th may */
						regForm.setParty1OwnerCount(0);
						regForm.setParty1PoaHolderCount(0);
						regForm.setParty2OwnerCount(0);
						regForm.setParty2PoaHolderCount(0);
						regForm.setDoneeCount(0);
						regForm.setDonorCount(0);
						regForm.setBuyerCount(0);
						regForm.setSellerPoaCount(0);
						regForm.setSellerSelfCount(0);
						regForm.setOwnerCount(0);
						regForm.setPoaAccepterCount(0);
						regForm.setPoaHolderCount(0);
					}
					if (regForm.getIsMultiplePropsFlag() == 1) {

						commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
						commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));

						regForm.setCountryTrns("1");
						regForm.setCountryNameTrns("INDIA");
						regForm.setStatenameTrns("1");
						regForm.setStatenameNameTrns("MADHYA PRADESH");
						regForm.setIndcountryTrns("1");
						regForm.setIndcountryNameTrns("INDIA");
						regForm.setIndstatenameTrns("1");
						regForm.setIndstatenameNameTrns("MADHYA PRADESH");

						regForm.setAuthPerCountryTrns("1");
						regForm.setAuthPerCountryNameTrns("INDIA");
						regForm.setAuthPerStatenameTrns("1");
						regForm.setAuthPerStatenameNameTrns("MADHYA PRADESH");

						if (regForm.getHidnRegTxnId() != null) {
							String shareApp = commonBo.getApplicantShare(regForm.getHidnRegTxnId());
							if (shareApp != null) {
								if (shareApp.equalsIgnoreCase("") || shareApp.equalsIgnoreCase("null")) {
									regForm.setHdnDeclareShareCheck("N");
								} else {
									regForm.setHdnDeclareShareCheck("Y");
								}
							}
						}

					}

				}

				session.removeAttribute("commonDto");
				session.removeAttribute("roleId");
				session.removeAttribute("functionId");

				session.removeAttribute("status");
				session.removeAttribute("view");
				session.removeAttribute("regFormProp");

			}

		}
		// end of code for clearing form beans
		String userId = (String) session.getAttribute("UserId");
		regForm.setHidnUserId(userId);
		if (request.getParameter("regStatus") != null) {

			DutyCalculationForm eForm = (DutyCalculationForm) request.getAttribute("eForm");
			if (eForm.getFromAdjudication() == 1) {

				regForm.setFromAdjudication(1);

			} else {
				regForm.setFromAdjudication(0);

			}

			if (eForm.getDutyCalculationDTO().getMainDutyTxnId() != null)
				regForm.setDuty_txn_id(Integer.parseInt(eForm.getDutyCalculationDTO().getMainDutyTxnId()));
			else
				regForm.setDuty_txn_id(0);
			logger.debug("duty txn id----------->" + regForm.getDuty_txn_id());

			if (eForm.getDutyCalculationDTO().getCancellationFlag() != null) {

				if (eForm.getDutyCalculationDTO().getCancellationFlag().equalsIgnoreCase("Y")) {

					commonBo.getCancellationLabel(Integer.toString(regForm.getDuty_txn_id()), regForm, languageLocale);

				} else {
					regForm.setCancellationLabel("");
				}

			} else {
				regForm.setCancellationLabel("");
			}
			regForm.setDeedID(regForm.getDeedID());
			regForm.setDeedtype1(commonBo.getDeedName(Integer.toString(regForm.getDeedID()), languageLocale));

			regForm.setInstID(regForm.getInstID());
			regForm.setInstType(commonBo.getInstrumentName(Integer.toString(regForm.getInstID()), languageLocale));
			logger.debug("inst id----------->" + regForm.getInstID());
			regForm.setExmpID(commonBo.getExempId(regForm.getDuty_txn_id()));
			regForm.setSelectedExemptionList(commonBo.getExemptionList(Integer.toString(regForm.getDuty_txn_id()), languageLocale));
			regForm.setFamilyFlag(commonBo.getFamilyFlag(Integer.toString(regForm.getDuty_txn_id())));

			String flags[] = commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));

			if (flags != null && flags[0] != null && flags[1] != null && flags[2] != null) {

				if (flags[2].trim().equalsIgnoreCase("Y")) {
					regForm.setCommonDeed(1);
				} else {
					regForm.setCommonDeed(0);
				}

				regForm.setPvReqFlag(flags[1].trim());
				regForm.setPropReqFlag(flags[0].trim());

			} else {
				regForm.setCommonDeed(0);
				// regForm.setPvReqFlag("");
				// regForm.setPropReqFlag("");

				// Added by Gulrej on 8th may
				regForm.setPvReqFlag(flags[1].trim());
				regForm.setPropReqFlag(flags[0].trim());
			}

			commonBo.getAllPropertyList(regForm, 0, null, languageLocale);
			regForm.setFromAdjudication(eForm.getFromAdjudication());
			if (regForm.getFromAdjudication() == 1) {
				if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_ENGLISH);
				} else {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_HINDI);
				}

			} else {

				if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_ENGLISH);
				} else {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_HINDI);
				}
			}

			int deed = 0;
			deed = regForm.getDeedID();
			request.setAttribute("deedId", deed);
			commonDto.setDeedId(deed);
			if (regForm.getIsDashboardFlag() == 0)
				commonDto.setPartyType(commonBo.getPartyType(deed, regForm.getInstID(), languageLocale));

			commonDto.setCountry(commonBo.getCountry(languageLocale));
			commonDto.setIndcountry(commonBo.getCountry(languageLocale));
			commonDto.setIdProf(commonBo.getIdProf(languageLocale));
			commonDto.setDeedType(commonBo.getDeedType(languageLocale));
			commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
			commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));

			regForm.setCountry("1");
			regForm.setCountryName("INDIA");
			regForm.setStatename("1");
			regForm.setStatenameName("MADHYA PRADESH");
			regForm.setAuthPerCountry("1");
			regForm.setAuthPerCountryName("INDIA");
			regForm.setAuthPerStatename("1");
			regForm.setAuthPerStatenameName("MADHYA PRADESH");
			regForm.setIndcountry("1");
			regForm.setIndcountryName("INDIA");
			regForm.setIndstatename("1");
			regForm.setIndstatenameName("MADHYA PRADESH");
			regForm.setIndcountryArb("1");
			regForm.setIndstatenameArb("1");

		} else {
			if ((request.getAttribute("eForm") != null)) {
				DutyCalculationForm eForm = (DutyCalculationForm) request.getAttribute("eForm");
				if (eForm.getFromAdjudication() == 1) {

					regForm.setFromAdjudication(1);

				} else {
					regForm.setFromAdjudication(0);

				}

				if (eForm.getDutyCalculationDTO().getMainDutyTxnId() != null)
					regForm.setDuty_txn_id(Integer.parseInt(eForm.getDutyCalculationDTO().getMainDutyTxnId()));
				else
					regForm.setDuty_txn_id(0);
				logger.debug("duty txn id----------->" + regForm.getDuty_txn_id());

				if (eForm.getDutyCalculationDTO().getCancellationFlag() != null) {

					if (eForm.getDutyCalculationDTO().getCancellationFlag().equalsIgnoreCase("Y")) {

						commonBo.getCancellationLabel(Integer.toString(regForm.getDuty_txn_id()), regForm, languageLocale);

					} else {
						regForm.setCancellationLabel("");
					}

				} else {
					regForm.setCancellationLabel("");
				}
				regForm.setDeedID(eForm.getDutyCalculationDTO().getDeedId());
				regForm.setDeedtype1(commonBo.getDeedName(Integer.toString(regForm.getDeedID()), languageLocale));

				regForm.setInstID(eForm.getInstDTO().getInstId());
				regForm.setInstType(commonBo.getInstrumentName(Integer.toString(regForm.getInstID()), languageLocale));
				logger.debug("inst id----------->" + regForm.getInstID());
				regForm.setExmpID(commonBo.getExempId(regForm.getDuty_txn_id()));
				regForm.setSelectedExemptionList(commonBo.getExemptionList(Integer.toString(regForm.getDuty_txn_id()), languageLocale));
				regForm.setFamilyFlag(commonBo.getFamilyFlag(Integer.toString(regForm.getDuty_txn_id())));

				String flags[] = commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));

				if (flags != null && flags[0] != null && flags[1] != null && flags[2] != null) {

					if (flags[2].trim().equalsIgnoreCase("Y")) {
						regForm.setCommonDeed(1);
					} else {
						regForm.setCommonDeed(0);
					}

					regForm.setPvReqFlag(flags[1].trim());
					regForm.setPropReqFlag(flags[0].trim());

				} else {
					regForm.setCommonDeed(0);
					// regForm.setPvReqFlag("");
					// regForm.setPropReqFlag("");

					// Added by gulrej on 8th may
					regForm.setPvReqFlag(flags[1].trim());
					regForm.setPropReqFlag(flags[0].trim());
				}

				commonBo.getAllPropertyList(regForm, 0, null, languageLocale);
				regForm.setFromAdjudication(eForm.getFromAdjudication());
				if (regForm.getFromAdjudication() == 1) {
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_ENGLISH);
					} else {
						session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_HINDI);
					}

				} else {

					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_ENGLISH);
					} else {
						session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_HINDI);
					}
				}

				int deed = 0;
				deed = regForm.getDeedID();
				request.setAttribute("deedId", deed);
				commonDto.setDeedId(deed);
				if (regForm.getIsDashboardFlag() == 0)
					commonDto.setPartyType(commonBo.getPartyType(deed, regForm.getInstID(), languageLocale));

				commonDto.setCountry(commonBo.getCountry(languageLocale));
				commonDto.setIndcountry(commonBo.getCountry(languageLocale));
				commonDto.setIdProf(commonBo.getIdProf(languageLocale));
				commonDto.setDeedType(commonBo.getDeedType(languageLocale));
				commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
				commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));

				regForm.setCountry("1");
				regForm.setCountryName("INDIA");
				regForm.setStatename("1");
				regForm.setStatenameName("MADHYA PRADESH");
				regForm.setAuthPerCountry("1");
				regForm.setAuthPerCountryName("INDIA");
				regForm.setAuthPerStatename("1");
				regForm.setAuthPerStatenameName("MADHYA PRADESH");
				regForm.setIndcountry("1");
				regForm.setIndcountryName("INDIA");
				regForm.setIndstatename("1");
				regForm.setIndstatenameName("MADHYA PRADESH");
				regForm.setIndcountryArb("1");
				regForm.setIndstatenameArb("1");
			}
		}

		if (regForm.getFromAdjudication() == 0) {

			if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_ENGLISH);
			} else {
				session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_HINDI);
			}

		} else {

			if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_ENGLISH);
			} else {
				session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_HINDI);
			}
		}

		forward = regForm.getPage();

		if (request.getAttribute("regFormDashboard") == null) {
			if (regForm.getCountry() != null && !regForm.getCountry().equalsIgnoreCase("") && !regForm.getCountry().equalsIgnoreCase("null")) {
				commonDto.setState(commonBo.getState(regForm.getCountry(), languageLocale));
				forward = "success";
			} else {
				commonDto.setState(new ArrayList());
			}
			// for getting organization district list
			if (regForm.getStatename() != null && !regForm.getStatename().equalsIgnoreCase("") && !regForm.getStatename().equalsIgnoreCase("null")) {
				commonDto.setDistrict(commonBo.getDistrict(regForm.getStatename(), languageLocale));
				forward = "success";
			} else {
				commonDto.setDistrict(new ArrayList());
			}
			if (regForm.getIndcountry() != null && !regForm.getIndcountry().equalsIgnoreCase("") && !regForm.getIndcountry().equalsIgnoreCase("null")) {
				commonDto.setIndstate(commonBo.getState(regForm.getIndcountry(), languageLocale));
				forward = "success";
			} else {
				commonDto.setIndstate(new ArrayList());
			}
			if (regForm.getIndstatename() != null && !regForm.getIndstatename().equalsIgnoreCase("") && !regForm.getIndstatename().equalsIgnoreCase("null")) {
				commonDto.setInddistrict(commonBo.getDistrict(regForm.getIndstatename(), languageLocale));
				forward = "success";
			} else {
				commonDto.setInddistrict(new ArrayList());
			}
			if (request.getParameter("dms") != null) {
				if (request.getParameter("dms").equalsIgnoreCase("retrieval")) {

					if (request.getParameter("path") != null) {

						String partyType = "";
						String filePath = request.getParameter("path").toString();
						logger.debug("retrieval path-->" + filePath);
						int indexOfDot = filePath.lastIndexOf(".");
						String key = request.getParameter("key");
						String fileName = "";
						if (filePath != null && !filePath.equalsIgnoreCase("null")) {
							fileName = filePath.substring(indexOfDot - 2, indexOfDot) + ".";
						}

						if (request.getParameter("roleType") != null) {
							partyType = request.getParameter("roleType").toString();
							regForm.setPartyType(partyType);
							logger.debug("role type---------->" + partyType);
							RegCommonDTO dtoObj = (RegCommonDTO) regForm.getMapTransactingPartiesDisp().get(key);

							if (request.getParameter("addUpload") != null && request.getParameter("addUpload").toString().equalsIgnoreCase("1")) {

								String id = request.getParameter("uKey");
								ArrayList<CommonDTO> listDto = dtoObj.getListDto();
								if (id != null && !id.equalsIgnoreCase("")) {
									for (int i = 0; i < listDto.size(); i++) {
										CommonDTO dto = listDto.get(i);
										if (dto.getUniqueIdUpload().equalsIgnoreCase(id)) {
											DMSUtility.downloadDocument(response, dto.getDocumentPath(), dto.getDocContents());
											break;
										}

									}

								}
							}

							else {
								if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_CERTIFICATE)) {
									DMSUtility.downloadDocument(response, filePath, dtoObj.getDocContentsTrns());

								}
								if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PHOTO_PROOF)) {
									DMSUtility.downloadDocument(response, filePath, dtoObj.getU2DocContentsTrns());

								}

								int roleId = Integer.parseInt(partyType);
								String[] claimantArr = commonBo.getClaimantFlag(Integer.toString(roleId));
								int claimantFlag = Integer.parseInt(claimantArr[0].trim());

								// BELOW CODE FOR EXECUTANT
								if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {

									if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_NOTARIZED_AFFIDAVIT_EXECUTANT)) {
										DMSUtility.downloadDocument(response, filePath, dtoObj.getU3DocContentsTrns());

									}

									if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_EXECUTANT_PHOTO)) {
										DMSUtility.downloadDocument(response, filePath, dtoObj.getU4DocContentsTrns());

									}

									if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PAN_FORM_60)) {
										DMSUtility.downloadDocument(response, filePath, dtoObj.getU10DocContentsTrns());

									}

								}
								// BELOW CODE FOR EXECUTANT POA HOLDER
								if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2) {

									if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_NOTARIZED_AFFIDAVIT_ATTORNEY)) {
										DMSUtility.downloadDocument(response, filePath, dtoObj.getU5DocContentsTrns());

									}

									if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_ATTORNEY_PHOTO)) {
										DMSUtility.downloadDocument(response, filePath, dtoObj.getU6DocContentsTrns());

									}
									if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_EXECUTANT_PHOTO)) {
										DMSUtility.downloadDocument(response, filePath, dtoObj.getU7DocContentsTrns());

									}

								}

								// BELOW CODE FOR CLAIMANT
								if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {

									if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_CLAIMANT_PHOTO)) {
										DMSUtility.downloadDocument(response, filePath, dtoObj.getU8DocContentsTrns());

									}

									if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PAN_FORM_60)) {
										DMSUtility.downloadDocument(response, filePath, dtoObj.getU11DocContentsTrns());

									}

								}

								// BELOW CODE FOR CLAIMANT POA HOLDER
								if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4) {

									if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_ATTORNEY_PHOTO)) {
										DMSUtility.downloadDocument(response, filePath, dtoObj.getU9DocContentsTrns());

									}

								}

							}
							if (regForm.getPvReqFlag().equalsIgnoreCase("Y")) {

								forward = "displayRegDetls";

							} else {

								forward = "displayRegDetlsNPV";

							}
							request.setAttribute("deedId", regForm.getDeedID());
							request.setAttribute("instId", regForm.getInstID());
							request.setAttribute("roleId", partyType);
						} else {

							if (request.getParameter("addUpload") != null && request.getParameter("addUpload").toString().equalsIgnoreCase("3")) {

								String id = request.getParameter("uKey");
								ArrayList<CommonDTO> listDto = regForm.getListDtoAdju();
								if (id != null && !id.equalsIgnoreCase("")) {
									for (int i = 0; i < listDto.size(); i++) {
										CommonDTO dto = listDto.get(i);
										if (dto.getUniqueIdUpload().equalsIgnoreCase(id)) {
											DMSUtility.downloadDocument(response, dto.getDocumentPath(), dto.getDocContents());
											break;
										}

									}

								}
								if (regForm.getPvReqFlag().equalsIgnoreCase("Y")) {
									forward = "reginitConfirm";
								} else {
									forward = "reginitConfirmNonPV";
								}

							} else if (request.getParameter("addUpload") != null && request.getParameter("addUpload").toString().equalsIgnoreCase("4")) {

								String id = request.getParameter("uKey");

								if (("Y").equalsIgnoreCase(regForm.getPvReqFlag())) {
									forward = "displayConsenterDetls";
								} else {
									forward = "displayConsenterDetlsNPV";
								}

							} else {

								RegCompletDTO dtoObj = (RegCompletDTO) regForm.getMapPropertyTransPartyDisp().get(key);

								if (request.getParameter("addUpload") != null && request.getParameter("addUpload").toString().equalsIgnoreCase("2")) {

									String id = request.getParameter("uKey");
									ArrayList<CommonDTO> listDto = dtoObj.getListDtoP();
									if (id != null && !id.equalsIgnoreCase("")) {
										for (int i = 0; i < listDto.size(); i++) {
											CommonDTO dto = listDto.get(i);
											if (dto.getUniqueIdUpload().equalsIgnoreCase(id)) {
												DMSUtility.downloadDocument(response, dto.getDocumentPath(), dto.getDocContents());
												break;
											}

										}

									}

								}

								if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_ANGLE_1)) {
									DMSUtility.downloadDocument(response, filePath, dtoObj.getPropImage1DocContents());

								}

								if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_ANGLE_2)) {
									DMSUtility.downloadDocument(response, filePath, dtoObj.getPropImage2DocContents());

								}

								if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_ANGLE_3)) {
									DMSUtility.downloadDocument(response, filePath, dtoObj.getPropImage3DocContents());

								}

								if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_MAP)) {
									DMSUtility.downloadDocument(response, filePath, dtoObj.getPropMapDocContents());

								}

								if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_RIN)) {
									DMSUtility.downloadDocument(response, filePath, dtoObj.getPropRinDocContents());

								}

								if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_KHASRA)) {
									DMSUtility.downloadDocument(response, filePath, dtoObj.getPropKhasraDocContents());

								}
								if (regForm.getPvReqFlag().equalsIgnoreCase("Y")) {
									forward = "propertyView";
								} else {
									forward = "propertyViewNPV";
								}
							}
						}
					}
				}
				if (request.getParameter("dms").equalsIgnoreCase("retrievalLive")) {

					if (request.getParameter("retrievalType") != null && request.getParameter("retrievalType").toString().equalsIgnoreCase("1")) {

						// below for applicant screen
						if (request.getParameter("appFlag") != null && request.getParameter("appFlag").toString().equalsIgnoreCase("Y")) {
							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("1")) {
								DMSUtility.downloadDocument(response, regForm.getFilePath(), regForm.getDocContents());

							}
							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("2")) {
								DMSUtility.downloadDocument(response, regForm.getU2FilePath(), regForm.getU2DocContents());

							}

							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("3")) {
								DMSUtility.downloadDocument(response, regForm.getU3FilePath(), regForm.getU3DocContents());

							}

							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("4")) {
								DMSUtility.downloadDocument(response, regForm.getU4FilePath(), regForm.getU4DocContents());

							}

							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("10")) {
								DMSUtility.downloadDocument(response, regForm.getU10FilePath(), regForm.getU10DocContents());

							}

							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("5")) {
								DMSUtility.downloadDocument(response, regForm.getU5FilePath(), regForm.getU5DocContents());

							}

							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("6")) {
								DMSUtility.downloadDocument(response, regForm.getU6FilePath(), regForm.getU6DocContents());

							}
							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("7")) {
								DMSUtility.downloadDocument(response, regForm.getU7FilePath(), regForm.getU7DocContents());

							}

							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("8")) {
								DMSUtility.downloadDocument(response, regForm.getU8FilePath(), regForm.getU8DocContents());

							}

							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("11")) {
								DMSUtility.downloadDocument(response, regForm.getU11FilePath(), regForm.getU11DocContents());

							}

							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("9")) {
								DMSUtility.downloadDocument(response, regForm.getU9FilePath(), regForm.getU9DocContents());

							}

							// }

							forward = "success";

						}
						// below for transacting party screen
						if (request.getParameter("appFlag") != null && (request.getParameter("appFlag").toString().equalsIgnoreCase("N") || request.getParameter("appFlag").toString().equalsIgnoreCase("E"))) {
							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("1")) {
								DMSUtility.downloadDocument(response, regForm.getFilePathTrns(), regForm.getDocContentsTrns());

							}
							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("2")) {
								DMSUtility.downloadDocument(response, regForm.getU2FilePathTrns(), regForm.getU2DocContentsTrns());

							}

							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("3")) {
								DMSUtility.downloadDocument(response, regForm.getU3FilePathTrns(), regForm.getU3DocContentsTrns());

							}

							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("4")) {
								DMSUtility.downloadDocument(response, regForm.getU4FilePathTrns(), regForm.getU4DocContentsTrns());

							}

							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("10")) {
								DMSUtility.downloadDocument(response, regForm.getU10FilePathTrns(), regForm.getU10DocContentsTrns());

							}

							// }
							// BELOW CODE FOR EXECUTANT POA HOLDER
							// if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_2)
							// {

							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("5")) {
								DMSUtility.downloadDocument(response, regForm.getU5FilePathTrns(), regForm.getU5DocContentsTrns());

							}

							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("6")) {
								DMSUtility.downloadDocument(response, regForm.getU6FilePathTrns(), regForm.getU6DocContentsTrns());

							}
							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("7")) {
								DMSUtility.downloadDocument(response, regForm.getU7FilePathTrns(), regForm.getU7DocContentsTrns());

							}
							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("8")) {
								DMSUtility.downloadDocument(response, regForm.getU8FilePathTrns(), regForm.getU8DocContentsTrns());

							}

							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("11")) {
								DMSUtility.downloadDocument(response, regForm.getU11FilePathTrns(), regForm.getU11DocContentsTrns());

							}
							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("9")) {
								DMSUtility.downloadDocument(response, regForm.getU9FilePathTrns(), regForm.getU9DocContentsTrns());

							}
							if (request.getParameter("appFlag").toString().equalsIgnoreCase("N")) {
								forward = "transactingParty";
								return mapping.findForward(forward);
							} else {
								forward = "displayRegDetls";
								return mapping.findForward(forward);
							}
						}
					}
				}
			}
			String districtId = null;
			String languageLocale1 = (String) session.getAttribute("languageLocale");
			if (request.getParameter("districtIdModify") != null) {

				districtId = request.getParameter("districtIdModify").toString();
				ArrayList tehsilListModify = commonBo.getTehsilListModify(districtId, languageLocale1);
				commonDto.setTehsilTrns(tehsilListModify);
				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());
				request.setAttribute("roleId", regForm.getPartyType());
				forward = "displayRegDetls";

				return mapping.findForward(forward);
			}
			if (request.getParameter("districtIdModify2") != null) {

				districtId = request.getParameter("districtIdModify2").toString();

				ArrayList tehsilListModify = commonBo.getTehsilListModify(districtId, languageLocale1);

				commonDto.setTehsilTrns(tehsilListModify);
				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());
				request.setAttribute("roleId", regForm.getPartyType());
				forward = "displayRegDetls";

				return mapping.findForward(forward);

			}
			if (request.getParameter("districtIdModify1") != null) {

				districtId = request.getParameter("districtIdModify1").toString();

				ArrayList tehsilListModify = commonBo.getTehsilListModify1(districtId, languageLocale1);

				commonDto.setTehsilTrns1(tehsilListModify);
				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());
				request.setAttribute("roleId", regForm.getPartyType());
				forward = "displayRegDetls";

				return mapping.findForward(forward);

			}
			if (request.getParameter("tehsilID") != null) {

				districtId = request.getParameter("tehsilID").toString();
				ArrayList tehsilList = commonBo.getTehsilList(districtId, languageLocale1);
				regForm.getCommonDto().setTehsilName1("");
				regForm.getCommonDto().setIndtehsil(tehsilList);
				regForm.getCommonDto().setTehsilId("-1"); // added by siddhartha
				System.out.println("display tehsil" + tehsilList);

				forward = "success";

			}

			if (request.getParameter("tehsilID1") != null) {

				districtId = request.getParameter("tehsilID1").toString();
				System.out.println("district ID" + districtId);
				ArrayList tehsilList = commonBo.getTehsilList1(districtId, languageLocale1);
				System.out.println("tehsil list value" + tehsilList);
				regForm.getCommonDto().setTehsilName1("");
				regForm.getCommonDto().setTehsilId("-1"); // added by siddhartha
				regForm.getCommonDto().setIndtehsil(tehsilList);
				System.out.println("display tehsil" + tehsilList);

				forward = "success";

			}

			if (request.getParameter("tehsilIDOrg") != null) {

				districtId = request.getParameter("tehsilIDOrg").toString();
				System.out.println("district ID" + districtId);
				session.setAttribute("districtId", districtId);
				ArrayList tehsilList1 = commonBo.getTehsilList5(districtId, languageLocale1);
				System.out.println("tehsil list value" + tehsilList1);
				regForm.getCommonDto().setTehsilName("");
				regForm.getCommonDto().setTehsilId("-1"); // added by siddhartha
				regForm.getCommonDto().setIndtehsilpoaOrgPro(tehsilList1);
				System.out.println("display tehsil" + tehsilList1);

				forward = "success";

			}

			if (request.getParameter("tehsilID7") != null) {

				districtId = request.getParameter("tehsilID7").toString();
				System.out.println("district ID" + districtId);
				session.setAttribute("districtId", districtId);
				ArrayList tehsilList1 = commonBo.getTehsilList(districtId, languageLocale1);
				System.out.println("tehsil list value" + tehsilList1);
				regForm.getCommonDto().setTehsilName1("");
				regForm.getCommonDto().setTehsilId("-1"); // added by siddhartha
				regForm.getCommonDto().setIndtehsilpoaOrgPro7(tehsilList1);
				System.out.println("display tehsil" + tehsilList1);

				forward = "success";

			}

			if (request.getParameter("tehsilIDPoa2") != null) {

				districtId = request.getParameter("tehsilIDPoa2").toString();
				System.out.println("district ID" + districtId);
				session.setAttribute("districtId", districtId);
				ArrayList tehsilList = commonBo.getTehsilList1(districtId, languageLocale1);
				System.out.println("tehsil list value" + tehsilList);
				regForm.getCommonDto().setTehsilName1("");
				regForm.getCommonDto().setTehsilId("-1"); // added by siddhartha
				regForm.getCommonDto().setIndtehsilpoa2(tehsilList);
				System.out.println("display tehsil" + tehsilList);

				forward = "success";

			}

			if (request.getParameter("tehsilID9") != null) {

				districtId = request.getParameter("tehsilID9").toString();
				System.out.println("district ID" + districtId);
				session.setAttribute("districtId", districtId);
				ArrayList tehsilList = commonBo.getTehsilList1(districtId, languageLocale1);
				System.out.println("tehsil list value" + tehsilList);
				regForm.getCommonDto().setTehsilName1("");
				regForm.getCommonDto().setTehsilId("-1"); // added by siddhartha
				regForm.getCommonDto().setIndtehsilpoa9(tehsilList);
				System.out.println("display tehsil" + tehsilList);

				forward = "success";

			}

			if (request.getParameter("tehsilID10") != null) {

				districtId = request.getParameter("tehsilID10").toString();
				System.out.println("district ID" + districtId);
				session.setAttribute("districtId", districtId);
				ArrayList tehsilList = commonBo.getTehsilList3(districtId, languageLocale1);
				System.out.println("tehsil list value" + tehsilList);
				regForm.getCommonDto().setTehsilName1("");
				regForm.getCommonDto().setTehsilId("-1"); // added by siddhartha
				regForm.getCommonDto().setIndtehsilpoa10(tehsilList);
				System.out.println("display tehsil" + tehsilList);

				forward = "success";

			}

			if (request.getParameter("tehsilIDPOA") != null) {

				districtId = request.getParameter("tehsilIDPOA").toString();
				System.out.println("district ID" + districtId);
				session.setAttribute("districtId", districtId);
				ArrayList tehsilList = commonBo.getTehsilList(districtId, languageLocale1);
				System.out.println("tehsil list value" + tehsilList);
				regForm.getCommonDto().setTehsilName("");
				regForm.getCommonDto().setTehsilId("-1"); // added by siddhartha
				regForm.getCommonDto().setIndtehsilpoapro(tehsilList);
				System.out.println("display tehsil" + tehsilList);

				forward = "success";

			}

			if (regForm.getDeedID() == RegInitConstant.DEED_AWARD_PV) {
				// for getting Arbitrator state list
				if (regForm.getIndcountryArb() != null && !regForm.getIndcountryArb().equalsIgnoreCase("") && !regForm.getIndcountryArb().equalsIgnoreCase("null")) {
					commonDto.setIndstate(commonBo.getState(regForm.getIndcountryArb(), languageLocale));
					forward = "success";
				} else {
					commonDto.setIndstate(new ArrayList());
				}
				// for getting Arbitrator district list
				if (regForm.getIndstatenameArb() != null && !regForm.getIndstatenameArb().equalsIgnoreCase("") && !regForm.getIndstatenameArb().equalsIgnoreCase("null")) {
					commonDto.setInddistrict(commonBo.getDistrict(regForm.getIndstatenameArb(), languageLocale));
					forward = "success";
				} else {
					commonDto.setInddistrict(new ArrayList());
				}
			}
		}
		if (regForm.getIsMultiplePropsFlag() == 1) {

			String[] deedInstArray = commonBo.getDeedInstId(regForm.getHidnRegTxnId());

			regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
			regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
			if (deedInstArray[2].trim().equals("-")) {
				regForm.setExmpID("");
				regForm.setHdnExAmount("");
			} else {
				regForm.setExmpID(deedInstArray[2].trim());
				regForm.setHdnExAmount(deedInstArray[2].trim());
			}

		}
		String hidnRegTxnId = "";
		if ((String) session.getAttribute("regTxnId") != null) {
			hidnRegTxnId = (String) session.getAttribute("regTxnId");
			regForm.setHidnRegTxnId(hidnRegTxnId);
		} else {
			hidnRegTxnId = regForm.getHidnRegTxnId();
			regForm.setHidnRegTxnId(hidnRegTxnId);

		}
		forward = "success";

		String formName = regForm.getFormName();
		String actionName = regForm.getActionName();
		logger.debug("formName:-" + formName);
		logger.debug("actionName:-" + actionName);
		if (request.getParameter("label") != null) {
			if (request.getParameter("label").equalsIgnoreCase("displayParty1")) {

				regForm.setPartyModifyFlag(0);
				regForm.setOwnerModifyFlag(0);
				commonBo.openPartyView(request, regForm, languageLocale);

				forward = "displayRegDetls1";
			}
		}
		if (RegInitConstant.OWNER_PAGE_FORM.equals(formName)) {

			if (RegInitConstant.MODIFY_OWNER_ACTION.equals(actionName)) {
			}

			if (RegInitConstant.SAVE_OWNER_ACTION.equals(actionName)) {

				boolean boo = commonBo.updateOwnerDetails(regForm);
				if (boo) {

					regForm.setOwnerModifyFlag(0);
					commonBo.getOwnerDetails(regForm.getOwnerId(), regForm, languageLocale);
					regForm.setActionName("");
					forward = "displayOwner";
				} else {
					forward = "failure";
				}
				// SAVE OWNER DETAILS

			}

		}

		if (RegInitConstant.CONSENTER_PAGE_FORM.equals(formName)) {

			if (RegInitConstant.BACK_TO_CONSENTER_DISPLAY_ACTION.equals(actionName)) {

				regForm.setActionName("");
				regForm.setPartyModifyFlag(0);

				commonBo.openConsenterView(request, regForm, languageLocale);
				forward = "displayConsenterDetls";
				return mapping.findForward(forward);

			}

			if (RegInitConstant.MODIFY_CONSENTER_ACTION.equals(actionName)) {

				regForm.setActionName("");
				regForm.setPartyModifyFlag(1);
				commonDto.setDistrict(commonBo.getDistrict(regForm.getRegDTO().getConsenterState(), languageLocale));
				commonDto.setIdProf(commonBo.getIdProf(languageLocale));
				commonDto.setState(commonBo.getState("1", languageLocale));
				commonDto.setRelationshipList(commonBo.getRelationshipList(null, languageLocale));
				if (regForm.getRegDTO().getConsenterPhotoIdNumber().equalsIgnoreCase("-")) {
					regForm.getRegDTO().setConsenterPhotoIdNumber("");
				}
				forward = "displayConsenterDetls";
				return mapping.findForward(forward);
			}
		}

		if (RegInitConstant.MAPPING_FORM.equals(formName)) {

			if (RegInitConstant.PARTY_POPUP_CLOSE_MAPPING_ACTION.equals(actionName)) {

				forward = commonBo.landMappingScreen(regForm, languageLocale);
				regForm.setActionName("");
				return mapping.findForward(forward);

			}

			if (RegInitConstant.RESET_SHARES_ACTION.equals(actionName)) {

				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "alreadyMapped";
				return mapping.findForward(forward);

			}

			if (RegInitConstant.VALIDATE_SHARES_ACTION.equals(actionName)) {

				regForm.setActionName("");

				HashMap activeMap = new HashMap();
				int saveMappingFlag = 0;

				if (regForm.getPropWithMapping() != null && regForm.getPropWithMapping().size() > 0) {
					activeMap = regForm.getPropWithMapping();
				} else {
					activeMap = regForm.getPropWithoutMapping();
					saveMappingFlag = 1;
				}

				commonBo.refreshHashMap(activeMap, request, regForm, saveMappingFlag);

				HashMap shareMap = activeMap;
				double shareParty1 = 0;
				double shareParty2 = 0;
				int party1Added = 0;
				int party2Added = 0;

				if (shareMap != null && shareMap.size() > 0) {

					Set set = shareMap.keySet();
					Iterator itr = set.iterator();
					ArrayList partyList;
					String key;
					CommonDTO dto;
					int roleId;
					double share;
					int partyType1Or2;
					while (itr.hasNext()) {
						party1Added = 0;
						party2Added = 0;
						//if(regForm.getDeedID()!=RegInitConstant.DEED_EXCHANGE)
						// {
						shareParty1 = 0;
						shareParty2 = 0;
						// }
						key = (String) itr.next();// key is property id
						partyList = (ArrayList) shareMap.get(key);
						if (partyList != null && partyList.size() > 0) {

							for (int i = 0; i < partyList.size(); i++) {

								dto = (CommonDTO) partyList.get(i);

								roleId = Integer.parseInt(dto.getRoleId());

								partyType1Or2 = commonBo.getPartyType1Or2(regForm.getDeedID(), regForm.getInstID(), roleId);

								if (saveMappingFlag == 1) {

									if (partyType1Or2 == 1) {
										if (dto.getPartyCheck().equalsIgnoreCase("Y"))
											party1Added = 1;
									} else {
										if (dto.getPartyCheck().equalsIgnoreCase("Y"))
											party2Added = 1;
									}

								}

								if (dto.getShareOfProp() != null && !dto.getShareOfProp().equalsIgnoreCase("")) {
									share = Double.parseDouble(dto.getShareOfProp());
								} else {
									share = 0;
								}
								if (partyType1Or2 == 1) {
									shareParty1 += share;
								} else {
									shareParty2 += share;
								}

							}

							if (saveMappingFlag == 1) {

								if (regForm.getDeedID() == RegInitConstant.DEED_TRUST || regForm.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV || (regForm.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_NPV && !(regForm.getInstID() == RegInitConstant.INST_DISSOLUTION_NPV || regForm.getInstID() == RegInitConstant.INST_DISSOLUTION_2))) {

									if (party1Added == 0) {
										if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
											request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_1_EN);
										} else {
											request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_1_HI);
										}
										forward = "alreadyMapped";
										return mapping.findForward(forward);
									}

								} else if (party1Added == 0 || party2Added == 0) {
									if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_1_EN);
									} else {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_1_HI);
									}
									forward = "alreadyMapped";
									return mapping.findForward(forward);
								}

							}

							if (regForm.getDeedID() == RegInitConstant.DEED_EXCHANGE) {
								if ((shareParty2 != 0 && shareParty2 != 100) || (shareParty1 != 0 && shareParty1 != 100)) {
									if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_EN);
									} else {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_HI);
									}
									forward = "alreadyMapped";
									return mapping.findForward(forward);
								}
							} else if (regForm.getDeedID() == RegInitConstant.DEED_PARTITION_PV || regForm.getDeedID() == RegInitConstant.DEED_TRUST || regForm.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV || (regForm.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_NPV && !(regForm.getInstID() == RegInitConstant.INST_DISSOLUTION_NPV || regForm.getInstID() == RegInitConstant.INST_DISSOLUTION_2))) {
								if (shareParty1 != 0 && shareParty1 != 100) {
									if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_EN);
									} else {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_HI);
									}
									forward = "alreadyMapped";
									return mapping.findForward(forward);
								}
							} else if (regForm.getInstID() == RegInitConstant.INST_BUILDER_2) {

								// get total shares from prop_duty table
								ArrayList shares = commonBo.getBuilderOwnerShares(key, regForm.getDuty_txn_id());
								// ArrayList shares=new ArrayList();

								if (shares != null) {
									int builderShare = shares.get(0) != null ? Integer.parseInt(shares.get(0).toString()) : 100;
									int ownerShare = shares.get(1) != null ? Integer.parseInt(shares.get(1).toString()) : 100;
									// int builderShare=40;
									// int ownerShare=60;
									if ((shareParty1 != 0 && shareParty2 != 0) && (shareParty1 != ownerShare || shareParty2 != builderShare)) {
										if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
											request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_EN);
										} else {
											request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_HI);
										}
										forward = "alreadyMapped";
										return mapping.findForward(forward);
									}
								}

							} else {
								if ((shareParty1 != 0 && shareParty2 != 0) && (shareParty1 != 100 || shareParty2 != 100)) {
									if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_EN);
									} else {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_HI);
									}
									forward = "alreadyMapped";
									return mapping.findForward(forward);
								}
							}
						}
					}

					boolean shareUpdate = commonBo.updatePartyPropShares(shareMap, regForm, saveMappingFlag);
					if (request.getParameter("regStatus") != null) {

						regForm.setConsenterFlag("N");

					}
					if (shareUpdate) {

						regForm.setActionName("");
						if (regForm.getConsenterFlag() == null) {
							regForm.setConsenterFlag("NA");
						}
						if (regForm.getPropReqFlag().equalsIgnoreCase("Y") && regForm.getConsenterFlag().equalsIgnoreCase("Y")) {
							boolean updateStatus = commonBo.updateConsenterFlag(RegInitConstant.CONSENTER_IN_PROGRESS, regForm.getHidnRegTxnId(), "Y", regForm.getConsenterWithConsidFlag());
							if (updateStatus) {
								forward = "reginitConsenter";
								regForm.setListDto(new ArrayList());
								regForm.setRegDTO(new RegCompleteMakerDTO());
								regForm.setConsenterAddedCount(0);
								commonDto.setIdProf(commonBo.getIdProf(languageLocale));
								commonDto.setState(commonBo.getState("1", languageLocale));
								commonDto.setDistrict(new ArrayList());
								commonDto.setRelationshipList(commonBo.getRelationshipList(null, languageLocale));
								// return mapping.findForward(forward);
							} else {
								logger.debug("unable to update reg status for consenter.");
								forward = "failure";

							}
						} else {

							dutyForm.setPropImage1DocumentName("");
							dutyForm.setEmptyFile("");

							if (regForm.getConsenterFlag().equalsIgnoreCase("NA")) {
								String regTxnId = regForm.getHidnRegTxnId();
								dutyForm.setHidnRegTxnId(regTxnId);
								session.setAttribute("regTxnId", regTxnId);
								request.setAttribute("regTxnId", regTxnId);
								// regForm.setHidnRegTxnId(regTxnId);
								forward = "reginitConfirm2";
							}

							else {
								forward = "reginitConfirm2";
								request.setAttribute("deedId", regForm.getDeedID());
								request.setAttribute("instId", regForm.getInstID());
								DutyCalculationForm eForm = new DutyCalculationForm();
								String regTxnId = (String) session.getAttribute("regTxnId");
								String getdutyId = commonBo.getdutyId(regTxnId);
								String exeDate = commonBo.getexeDate(getdutyId);
								eForm.setSlotdate(exeDate);
								request.setAttribute("regTxnId", regTxnId);

								eForm.setHidnRegTxnId(regTxnId);
								return mapping.findForward(forward);
							}
						}
					} else {
						forward = "failure";
					}
					return mapping.findForward(forward);
				}
			}
		}

		if (RegInitConstant.TRANS_PARTY_PAGE_FORM.equals(formName)) {

			if (RegInitConstant.ADD_MORE_TRNS_OWNER_ACTION.equals(actionName)) {

				RegCommonDTO mapDto = new RegCommonDTO();

				mapDto.setOwnerNameTrns(regForm.getOwnerNameTrns());
				if (regForm.getOwnerOgrNameTrns().equalsIgnoreCase("") || regForm.getOwnerOgrNameTrns().equalsIgnoreCase("null"))
					mapDto.setOwnerOgrNameTrns("-");
				else
					mapDto.setOwnerOgrNameTrns(regForm.getOwnerOgrNameTrns());

				mapDto.setOwnerGendarValTrns(regForm.getOwnerGendarTrns());
				if (regForm.getOwnerGendarTrns().equalsIgnoreCase("f")) {
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_ENGLISH);

					} else {
						mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_HINDI);

					}
				} else {
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setOwnerGendarTrns(RegInitConstant.MALE_ENGLISH);

					} else {
						mapDto.setOwnerGendarTrns(RegInitConstant.MALE_HINDI);

					}
				}
				mapDto.setOwnerNationalityTrns(regForm.getOwnerNationalityTrns());
				mapDto.setOwnerAddressTrns(regForm.getOwnerAddressTrns());
				mapDto.setOwnerPhnoTrns(regForm.getOwnerPhnoTrns());
				if (regForm.getOwnerEmailIDTrns().equalsIgnoreCase("") || regForm.getOwnerEmailIDTrns().equalsIgnoreCase("null"))
					mapDto.setOwnerEmailIDTrns("-");
				else
					mapDto.setOwnerEmailIDTrns(regForm.getOwnerEmailIDTrns());

				mapDto.setOwnerAgeTrns(regForm.getOwnerAgeTrns());

				mapDto.setOwnerIdnoTrns(regForm.getOwnerIdnoTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerIdnoTrns());
				mapDto.setOwnerListIDTrns(regForm.getOwnerListIDTrns());
				mapDto.setOwnerProofNameTrns(regForm.getOwnerListIDTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerProofNameTrns());

				if (regForm.getAddressOwnerOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressOwnerOutMpTrns())) {
					mapDto.setAddressOwnerOutMpTrns(regForm.getAddressOwnerOutMpTrns());
				} else {
					mapDto.setAddressOwnerOutMpTrns("-");
				}

				mapDto.setOwnerTxnId(commonBo.getTransactingPartyIdSeq());

				regForm.getTrnsOwnerList().put(mapDto.getOwnerTxnId(), mapDto);

				regForm.setOwnerNameTrns("");
				regForm.setOwnerOgrNameTrns("");
				regForm.setOwnerGendarTrns("M");
				regForm.setOwnerNationalityTrns("");
				regForm.setOwnerAddressTrns("");
				regForm.setOwnerPhnoTrns("");
				regForm.setOwnerEmailIDTrns("");
				regForm.setOwnerAgeTrns("");
				regForm.setOwnerIdnoTrns("");
				regForm.setOwnerListIDTrns("");
				regForm.setOwnerProofNameTrns("");
				regForm.setAddressOwnerOutMpTrns("");

				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "transactingParty";

			}

			if (RegInitConstant.DELETE_MORE_TRNS_OWNER_ACTION.equals(actionName)) {

				String[] trnsPrtyID = regForm.getHdnDeleteOwnerID().split(",");

				HashMap ownerMap = regForm.getTrnsOwnerList();

				for (int i = 0; i < trnsPrtyID.length; i++) {

					ownerMap.remove(trnsPrtyID[i]);

				}
				regForm.setTrnsOwnerList(ownerMap);

				forward = "transactingParty";

				regForm.setActionName(RegInitConstant.NO_ACTION);
				actionName = RegInitConstant.NO_ACTION;

			}

			if (RegInitConstant.GENDER_TRNS_ACTION.equals(actionName)) {
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "transactingParty";
				// return mapping.findForward(forward);
			}
			if (RegInitConstant.AUTH_PER_GENDER_TRNS_ACTION.equals(actionName)) {
				commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "transactingParty";

			}

			if (RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName)) {
				String partyTypeTrns = regForm.getListAdoptedPartyTrns();
				logger.debug("partyTypeTrns:-" + partyTypeTrns);

				forward = "transactingParty";
				// saveToken(request);
			}

			if (!RegInitConstant.GENDER_MODIFY_ACTION.equals(actionName) && !RegInitConstant.AUTH_PER_GENDER_MODIFY_ACTION.equals(actionName)) {
				if (regForm.getCountryTrns() != null && !regForm.getCountryTrns().equalsIgnoreCase("") && !regForm.getCountryTrns().equalsIgnoreCase("null")) {
					commonDto.setStateTrns(commonBo.getState(regForm.getCountryTrns(), languageLocale));
					forward = "transactingParty";
				} else {
					commonDto.setStateTrns(new ArrayList());
				}
				// for getting organization district list
				if (regForm.getStatenameTrns() != null && !regForm.getStatenameTrns().equalsIgnoreCase("") && !regForm.getStatenameTrns().equalsIgnoreCase("null")) {
					commonDto.setDistrictTrns(commonBo.getDistrict(regForm.getStatenameTrns(), languageLocale));

					forward = "transactingParty";
				} else {
					commonDto.setDistrictTrns(new ArrayList());
				}
				// for getting individual state list
				if (regForm.getIndcountryTrns() != null && !regForm.getIndcountryTrns().equalsIgnoreCase("") && !regForm.getIndcountryTrns().equalsIgnoreCase("null")) {
					commonDto.setIndstateTrns(commonBo.getState(regForm.getIndcountryTrns(), languageLocale));
					forward = "transactingParty";
				} else {
					commonDto.setIndstateTrns(new ArrayList());
				}
				// for getting individual district list
				if (regForm.getIndstatenameTrns() != null && !regForm.getIndstatenameTrns().equalsIgnoreCase("") && !regForm.getIndstatenameTrns().equalsIgnoreCase("null")) {
					commonDto.setInddistrictTrns(commonBo.getDistrict(regForm.getIndstatenameTrns(), languageLocale));

					forward = "transactingParty";
				} else {
					commonDto.setInddistrictTrns(new ArrayList());
				}
				// end of code for populating drop downs
			}

			// NEXT TO PROPERTY DETAILS PAGE THROUGH TRANSACTING PARTIES PAGE
			if (RegInitConstant.NEXT_TO_PROPERTY_ACTION.equals(actionName)) {
				RegCommonDTO mapDto = new RegCommonDTO();

				// following code for insertion of owner details into map

				int roleId = Integer.parseInt(regForm.getPartyTypeTrns());

				String[] claimantArr = commonBo.getClaimantFlag(Integer.toString(roleId));
				int claimantFlag = Integer.parseInt(claimantArr[0].trim());

				mapDto.setClaimantFlag(claimantFlag);
				if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
					mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);

					RegCommonDTO mapDto1 = new RegCommonDTO();

					mapDto1.setOwnerNameTrns(regForm.getOwnerNameTrns());
					if (regForm.getOwnerOgrNameTrns().equalsIgnoreCase("") || regForm.getOwnerOgrNameTrns().equalsIgnoreCase("null"))
						mapDto1.setOwnerOgrNameTrns("-");
					else
						mapDto1.setOwnerOgrNameTrns(regForm.getOwnerOgrNameTrns());

					mapDto1.setOwnerGendarValTrns(regForm.getOwnerGendarTrns());
					if (regForm.getOwnerGendarTrns().equalsIgnoreCase("f")) {
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto1.setOwnerGendarTrns(RegInitConstant.FEMALE_ENGLISH);

						} else {
							mapDto1.setOwnerGendarTrns(RegInitConstant.FEMALE_HINDI);

						}
					} else {
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto1.setOwnerGendarTrns(RegInitConstant.MALE_ENGLISH);

						} else {
							mapDto1.setOwnerGendarTrns(RegInitConstant.MALE_HINDI);

						}
					}
					mapDto1.setOwnerNationalityTrns(regForm.getOwnerNationalityTrns());
					mapDto1.setOwnerAddressTrns(regForm.getOwnerAddressTrns());
					mapDto1.setOwnerPhnoTrns(regForm.getOwnerPhnoTrns());
					if (regForm.getOwnerEmailIDTrns().equalsIgnoreCase("") || regForm.getOwnerEmailIDTrns().equalsIgnoreCase("null"))
						mapDto1.setOwnerEmailIDTrns("-");
					else
						mapDto1.setOwnerEmailIDTrns(regForm.getOwnerEmailIDTrns());

					mapDto1.setOwnerAgeTrns(regForm.getOwnerAgeTrns());

					mapDto1.setOwnerIdnoTrns(regForm.getOwnerIdnoTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerIdnoTrns());
					mapDto1.setOwnerListIDTrns(regForm.getOwnerListIDTrns());
					mapDto1.setOwnerProofNameTrns(regForm.getOwnerListIDTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerProofNameTrns());

					if (regForm.getAddressOwnerOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressOwnerOutMpTrns())) {
						mapDto1.setAddressOwnerOutMpTrns(regForm.getAddressOwnerOutMpTrns());
					} else {
						mapDto1.setAddressOwnerOutMpTrns("-");
					}

					mapDto1.setOwnerTxnId(commonBo.getTransactingPartyIdSeq());

					regForm.getTrnsOwnerList().put(mapDto1.getOwnerTxnId(), mapDto1);

					mapDto.setTrnsOwnerList(regForm.getTrnsOwnerList());
				}

				mapDto.setListAdoptedPartyTrns(regForm.getListAdoptedPartyTrns());
				mapDto.setListAdoptedPartyNameTrns(commonBo.getAppleteName(regForm.getListAdoptedPartyTrns().trim(), languageLocale));
				mapDto.setPurposeTrns("");
				mapDto.setBname(""); // might be changed
				mapDto.setBaddress(""); // might be changed
				mapDto.setApplicantOrTransParty("Transacting");
				mapDto.setPartyTypeTrns(regForm.getPartyTypeTrns());
				regForm.setPartyRoleTypeId(commonBo.getTransactingPartyIdSeq());

				mapDto.setPartyRoleTypeId(regForm.getPartyRoleTypeId());
				mapDto.setUserID(regForm.getHidnUserId());

				if (regForm.getRelationWithOwnerTrns().equalsIgnoreCase("") || regForm.getRelationWithOwnerTrns().equalsIgnoreCase("null")) {
					mapDto.setRelationWithOwnerTrns("-");
				} else {
					mapDto.setRelationWithOwnerTrns(regForm.getRelationWithOwnerTrns());
				}

				// FOLLOWING CODE FOR SETTING PARTY TYPE FLAG
				// int
				// selectedRoleId=Integer.parseInt(regForm.getPartyTypeTrns());

				mapDto.setPartyTypeFlag("N");

				mapDto.setRoleName(commonBo.getRoleName(regForm.getPartyTypeTrns(), languageLocale, regForm.getDeedID(), regForm.getInstID()));

				if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {

					// mapDto.setGovtOfcCheck(regForm.getGovtOfcCheck());
					if (regForm.getNameOfOfficialTrns() != null && !("").equalsIgnoreCase(regForm.getNameOfOfficialTrns())) {
						mapDto.setNameOfOfficial(regForm.getNameOfOfficialTrns());
					} else {
						mapDto.setNameOfOfficial("-");
					}
					mapDto.setDesgOfOfficial(regForm.getDesgOfOfficialTrns());
					mapDto.setAddressOfOfficial(regForm.getAddressOfOfficialTrns());
					mapDto.setSelectedDistrict(regForm.getInddistrictTrns());
					mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getInddistrictTrns(), languageLocale));
					mapDto.setOgrNameTrns("-");
					//mapDto.setAuthPerNameTrns(regForm.getAuthPerName().trim())
					// ;
					mapDto.setIndAuthPersn(mapDto.getDesgOfOfficial());
					mapDto.setIndividualOrOrganization(RegInitConstant.GOVT_OFFCL_ID);
					regForm.setAbc("2");

					if (regForm.getAddressGovtOffclOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressGovtOffclOutMpTrns())) {
						mapDto.setAddressGovtOffclOutMpTrns(regForm.getAddressGovtOffclOutMpTrns());
					} else {
						mapDto.setAddressGovtOffclOutMpTrns("-");
					}

				} else if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)) {
					// organization
					mapDto.setOgrNameTrns(regForm.getOgrNameTrns());
					mapDto.setAuthPerNameTrns(regForm.getAuthPerNameTrns());
					mapDto.setIndAuthPersn(regForm.getAuthPerNameTrns());
					mapDto.setOrgaddressTrns(regForm.getOrgaddressTrns());
					mapDto.setSelectedCountry(regForm.getCountryTrns());
					mapDto.setSelectedCountryName(regForm.getCountryNameTrns());
					mapDto.setSelectedState(regForm.getStatenameTrns());
					mapDto.setSelectedStateName(regForm.getStatenameNameTrns());
					mapDto.setSelectedDistrict(regForm.getDistrictTrns());
					mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getDistrictTrns(), languageLocale));
					if (regForm.getMobnoTrns().equalsIgnoreCase("") || regForm.getMobnoTrns().equalsIgnoreCase("null"))
						mapDto.setMobnoTrns("-");
					else
						mapDto.setMobnoTrns(regForm.getMobnoTrns());
					if (regForm.getPhnoTrns().equalsIgnoreCase("") || regForm.getPhnoTrns().equalsIgnoreCase("null"))
						mapDto.setPhnoTrns("-");
					else
						mapDto.setPhnoTrns(regForm.getPhnoTrns());
					mapDto.setConsiAmountTrns(regForm.getConsiAmountTrns());
					mapDto.setMarketValueTrns(regForm.getMarketValueTrns());

					mapDto.setFnameTrns("");
					mapDto.setMnameTrns("");
					mapDto.setLnameTrns("");
					// mapDto.setGendarTrns("-");
					// mapDto.setSelectedGender("");
					mapDto.setAgeTrns("");
					// mapDto.setFatherNameTrns("-");
					mapDto.setMotherNameTrns("");
					mapDto.setSpouseNameTrns("");
					mapDto.setNationalityTrns("");
					mapDto.setPostalCodeTrns("");
					mapDto.setEmailIDTrns("");
					mapDto.setSelectedPhotoId("");
					mapDto.setSelectedPhotoIdName("");
					mapDto.setIdnoTrns("-");
					mapDto.setIndReligionTrns("");
					mapDto.setIndCasteTrns("");
					mapDto.setIndDisabilityTrns("");

					mapDto.setIndividualOrOrganization(RegInitConstant.ORGANISATION_ID);

					mapDto.setAuthPerGendarTrns(regForm.getAuthPerGendarTrns());
					if (regForm.getAuthPerGendarTrns().equalsIgnoreCase("m"))
						mapDto.setSelectedGender("Male");
					else
						mapDto.setSelectedGender("Female");
					mapDto.setAuthPerFatherNameTrns(regForm.getAuthPerFatherNameTrns());

					mapDto.setRelationshipTrns(regForm.getAuthPerRelationshipTrns());
					mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getAuthPerRelationshipTrns()), languageLocale));

					mapDto.setAuthPerAddressTrns(regForm.getAuthPerAddressTrns().trim());
					mapDto.setAuthPerCountryTrns(regForm.getAuthPerCountryTrns());
					mapDto.setAuthPerCountryNameTrns(commonBo.getCountryName(regForm.getAuthPerCountryTrns(), languageLocale));
					mapDto.setAuthPerStatenameTrns(regForm.getAuthPerStatenameTrns());
					mapDto.setAuthPerStatenameNameTrns(commonBo.getStateName(regForm.getAuthPerStatenameTrns(), languageLocale));
					mapDto.setAuthPerDistrictTrns(regForm.getAuthPerDistrictTrns());
					mapDto.setAuthPerDistrictNameTrns(commonBo.getDistrictName(regForm.getAuthPerDistrictTrns().trim(), languageLocale));

					if (regForm.getAddressOrgOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressOrgOutMpTrns())) {
						mapDto.setAddressOrgOutMpTrns(regForm.getAddressOrgOutMpTrns());
					} else {
						mapDto.setAddressOrgOutMpTrns("-");
					}

					if (regForm.getAddressAuthPerOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressAuthPerOutMpTrns())) {
						mapDto.setAddressAuthPerOutMpTrns(regForm.getAddressAuthPerOutMpTrns());
					} else {
						mapDto.setAddressAuthPerOutMpTrns("-");
					}
					regForm.setAbc("2");
				} else if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
					// individual
					mapDto.setFnameTrns(regForm.getFnameTrns());
					if (regForm.getMnameTrns().equalsIgnoreCase("") || regForm.getMnameTrns().equalsIgnoreCase("null"))
						mapDto.setMnameTrns("-");
					else
						mapDto.setMnameTrns(regForm.getMnameTrns());
					mapDto.setLnameTrns(regForm.getLnameTrns());
					mapDto.setGendarTrns(regForm.getGendarTrns());
					if (regForm.getGendarTrns().equalsIgnoreCase("m"))
						mapDto.setSelectedGender("Male");
					else
						mapDto.setSelectedGender("Female");
					if (regForm.getAgeTrns().equalsIgnoreCase("") || regForm.getAgeTrns().equalsIgnoreCase("null"))
						mapDto.setAgeTrns("-");
					else
						mapDto.setAgeTrns(regForm.getAgeTrns());
					mapDto.setFatherNameTrns(regForm.getFatherNameTrns());
					if (regForm.getMotherNameTrns().equalsIgnoreCase("") || regForm.getMotherNameTrns().equalsIgnoreCase("null"))
						mapDto.setMotherNameTrns("-");
					else
						mapDto.setMotherNameTrns(regForm.getMotherNameTrns());
					if (regForm.getSpouseNameTrns().equalsIgnoreCase("") || regForm.getSpouseNameTrns().equalsIgnoreCase("null"))
						mapDto.setSpouseNameTrns("-");
					else
						mapDto.setSpouseNameTrns(regForm.getSpouseNameTrns());
					mapDto.setNationalityTrns(regForm.getNationalityTrns());

					if (regForm.getPostalCodeTrns().equalsIgnoreCase("") || regForm.getPostalCodeTrns().equalsIgnoreCase("null"))
						mapDto.setPostalCodeTrns("-");
					else
						mapDto.setPostalCodeTrns(regForm.getPostalCodeTrns());
					if (regForm.getIndphnoTrns().equalsIgnoreCase("") || regForm.getIndphnoTrns().equalsIgnoreCase("null"))
						mapDto.setPhnoTrns("-");
					else
						mapDto.setPhnoTrns(regForm.getIndphnoTrns());
					if (regForm.getIndmobnoTrns().equalsIgnoreCase("") || regForm.getIndmobnoTrns().equalsIgnoreCase("null"))
						mapDto.setMobnoTrns("-");
					else
						mapDto.setMobnoTrns(regForm.getIndmobnoTrns());
					if (regForm.getEmailIDTrns().equalsIgnoreCase("") || regForm.getEmailIDTrns().equalsIgnoreCase("null"))
						mapDto.setEmailIDTrns("-");
					else
						mapDto.setEmailIDTrns(regForm.getEmailIDTrns());
					mapDto.setSelectedPhotoId(regForm.getListIDTrns());
					mapDto.setSelectedPhotoIdName(regForm.getListIDNameTrns());
					mapDto.setIdnoTrns(regForm.getIdnoTrns());

					mapDto.setOgrNameTrns("-");
					mapDto.setAuthPerNameTrns("-");
					mapDto.setIndAuthPersn(regForm.getFnameTrns() + " " + regForm.getLnameTrns());
					mapDto.setOrgaddressTrns(regForm.getIndaddressTrns());
					mapDto.setSelectedCountry(regForm.getIndcountryTrns());
					mapDto.setSelectedCountryName(regForm.getIndcountryNameTrns());
					mapDto.setSelectedState(regForm.getIndstatenameTrns());
					mapDto.setSelectedStateName(regForm.getIndstatenameNameTrns());
					mapDto.setSelectedDistrict(regForm.getInddistrictTrns());
					mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getInddistrictTrns(), languageLocale));

					mapDto.setIndCategoryTrns(regForm.getIndCategoryTrns());
					mapDto.setSelectedOccupationName(commonBo.getOccupationName(regForm.getOccupationIdTrns(), languageLocale));
					mapDto.setOccupationIdTrns(regForm.getOccupationIdTrns());

					mapDto.setIndScheduleAreaTrns(regForm.getIndScheduleAreaTrns());

					if (regForm.getIndScheduleAreaTrns() != null) {
						if (regForm.getIndScheduleAreaTrns().equalsIgnoreCase("Y")) {
							mapDto.setIndScheduleAreaTrnsDisplay("Yes");
						} else {
							mapDto.setIndScheduleAreaTrnsDisplay("No");
							mapDto.setPermissionNoTrns(regForm.getPermissionNoTrns());
							mapDto.setDocumentNameTrns(regForm.getDocumentNameTrns());
							mapDto.setDocumentSizeTrns(regForm.getDocumentSizeTrns());
							mapDto.setDocContentsTrns(regForm.getDocContentsTrns());
							mapDto.setFilePathTrns(regForm.getFilePathTrns());
							mapDto.setPartyOrPropTrns(regForm.getPartyOrPropTrns());
							mapDto.setUploadTypeTrns(regForm.getUploadTypeTrns());
						}
					}

					if (regForm.getIndDisabilityTrns().equalsIgnoreCase("") || regForm.getIndDisabilityTrns().equalsIgnoreCase("null")) {
						mapDto.setIndDisabilityTrns("-");
					} else if (regForm.getIndDisabilityTrns().equalsIgnoreCase("Y")) {
						mapDto.setIndDisabilityTrns("Yes");
						if (regForm.getIndDisabilityDescTrns().equalsIgnoreCase("") || regForm.getIndDisabilityDescTrns().equalsIgnoreCase("null")) {
							mapDto.setIndDisabilityDescTrns("-");
						} else {
							mapDto.setIndDisabilityDescTrns(regForm.getIndDisabilityDescTrns());
						}
					} else if (regForm.getIndDisabilityTrns().equalsIgnoreCase("N")) {
						mapDto.setIndDisabilityTrns("No");

					}

					mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);

					// mapDto.setUserDOBTrns(UserRegistrationBD.getOracleDate(
					// regForm.getUserDayTrns(),
					// regForm.getUserMonthTrns(), regForm.getUserYearTrns()));

					if (regForm.getIndMinorityTrns().equalsIgnoreCase("") || regForm.getIndMinorityTrns().equalsIgnoreCase("null")) {
						mapDto.setIndMinorityTrns("-");
					} else if (regForm.getIndMinorityTrns().equalsIgnoreCase("Y")) {
						mapDto.setIndMinorityTrns("Yes");

					} else if (regForm.getIndMinorityTrns().equalsIgnoreCase("N")) {
						mapDto.setIndMinorityTrns("No");

					}

					// mapDto.setIndMinorityTrns("");
					if (regForm.getIndPovertyLineTrns().equalsIgnoreCase("") || regForm.getIndPovertyLineTrns().equalsIgnoreCase("null")) {
						mapDto.setIndPovertyLineTrns("-");
					} else if (regForm.getIndPovertyLineTrns().equalsIgnoreCase("Y")) {
						mapDto.setIndPovertyLineTrns("Yes");

					} else if (regForm.getIndPovertyLineTrns().equalsIgnoreCase("N")) {
						mapDto.setIndPovertyLineTrns("No");

					}

					mapDto.setRelationshipTrns(regForm.getRelationshipTrns());
					mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getRelationshipTrns()), languageLocale));

					if (regForm.getAddressIndOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressIndOutMpTrns())) {
						mapDto.setAddressIndOutMpTrns(regForm.getAddressIndOutMpTrns());
					} else {
						mapDto.setAddressIndOutMpTrns("-");
					}
				}

				// anuj set in dto from form

				// below code for uploads other than collector's permission no.
				if (!regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {

					mapDto.setU2DocumentNameTrns(regForm.getU2DocumentNameTrns());
					mapDto.setU2DocumentSizeTrns(regForm.getU2DocumentSizeTrns());
					mapDto.setU2FilePathTrns(regForm.getU2FilePathTrns());
					mapDto.setU2DocContentsTrns(regForm.getU2DocContentsTrns());
					mapDto.setU2PartyOrPropTrns(regForm.getU2PartyOrPropTrns());
					mapDto.setU2UploadTypeTrns(regForm.getU2UploadTypeTrns());

					// BELOW CODE FOR EXECUTANT
					if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {
						mapDto.setU3DocumentNameTrns(regForm.getU3DocumentNameTrns());
						mapDto.setU3DocumentSizeTrns(regForm.getU3DocumentSizeTrns());
						mapDto.setU3FilePathTrns(regForm.getU3FilePathTrns());
						mapDto.setU3DocContentsTrns(regForm.getU3DocContentsTrns());
						mapDto.setU3PartyOrPropTrns(regForm.getU3PartyOrPropTrns());
						mapDto.setU3UploadTypeTrns(regForm.getU3UploadTypeTrns());

						mapDto.setU10DocumentNameTrns(regForm.getU10DocumentNameTrns());
						mapDto.setU10DocumentSizeTrns(regForm.getU10DocumentSizeTrns());
						mapDto.setU10FilePathTrns(regForm.getU10FilePathTrns());
						mapDto.setU10DocContentsTrns(regForm.getU10DocContentsTrns());
						mapDto.setU10PartyOrPropTrns(regForm.getU10PartyOrPropTrns());
						mapDto.setU10UploadTypeTrns(regForm.getU10UploadTypeTrns());
					}
					// BELOW CODE FOR EXECUTANT POA HOLDER
					if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2) {

						mapDto.setU6DocumentNameTrns(regForm.getU6DocumentNameTrns());
						mapDto.setU6DocumentSizeTrns(regForm.getU6DocumentSizeTrns());
						mapDto.setU6FilePathTrns(regForm.getU6FilePathTrns());
						mapDto.setU6DocContentsTrns(regForm.getU6DocContentsTrns());
						mapDto.setU6PartyOrPropTrns(regForm.getU6PartyOrPropTrns());
						mapDto.setU6UploadTypeTrns(regForm.getU6UploadTypeTrns());
						mapDto.setSrOfficeNameTrns(regForm.getSrOfficeNameTrns());
						if (regForm.getPoaRegNoTrns() != null && !regForm.getPoaRegNoTrns().equalsIgnoreCase("")) {
							mapDto.setPoaRegNoTrns(regForm.getPoaRegNoTrns());
						} else {
							mapDto.setPoaRegNoTrns("-");
						}

						if (regForm.getDatePoaRegTrns() != null && !regForm.getDatePoaRegTrns().equalsIgnoreCase("")) {
							mapDto.setDatePoaRegTrns(commonBo.convertCalenderDate(regForm.getDatePoaRegTrns()));
						} else {
							mapDto.setDatePoaRegTrns("-");
						}
					}

					// BELOW CODE FOR CLAIMANT
					if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {

						mapDto.setU11DocumentNameTrns(regForm.getU11DocumentNameTrns());
						mapDto.setU11DocumentSizeTrns(regForm.getU11DocumentSizeTrns());
						mapDto.setU11FilePathTrns(regForm.getU11FilePathTrns());
						mapDto.setU11DocContentsTrns(regForm.getU11DocContentsTrns());
						mapDto.setU11PartyOrPropTrns(regForm.getU11PartyOrPropTrns());
						mapDto.setU11UploadTypeTrns(regForm.getU11UploadTypeTrns());
					}

					// BELOW CODE FOR CLAIMANT POA HOLDER
					if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4) {

						mapDto.setU9DocumentNameTrns(regForm.getU9DocumentNameTrns());
						mapDto.setU9DocumentSizeTrns(regForm.getU9DocumentSizeTrns());
						mapDto.setU9FilePathTrns(regForm.getU9FilePathTrns());
						mapDto.setU9DocContentsTrns(regForm.getU9DocContentsTrns());
						mapDto.setU9PartyOrPropTrns(regForm.getU9PartyOrPropTrns());
						mapDto.setU9UploadTypeTrns(regForm.getU9UploadTypeTrns());

					}
				}

				mapDto.setListDto(regForm.getListDto());

				map = regForm.getMapTransactingParties();

				int count = regForm.getMapKeyCount();
				if (count == 0)
					count = 1;

				if (map.containsKey(Integer.toString(count))) {

					count++;
					map.put(Integer.toString(count), mapDto);

				} else
					map.put(Integer.toString(count), mapDto);

				regForm.setMapKeyCount(count);

				regForm.setAddMoreCounter(count);
				// key="key";

				regForm.setMapTransactingParties(map);

				Collection mapCol = regForm.getMapTransactingParties().values();
				Object[] l2 = mapCol.toArray();
				RegCommonDTO mapDto2 = new RegCommonDTO();
				int roleId1 = 0;
				int partyType1Or2 = 0;
				int party1Added = 0;
				int party2Added = 0;
				int party3Added = 0;

				for (int i = 0; i < l2.length; i++) {

					mapDto2 = (RegCommonDTO) l2[i];

					if (mapDto2.getPartyTypeTrns() != null && !mapDto2.getPartyTypeTrns().equalsIgnoreCase("")) {
						roleId1 = Integer.parseInt(mapDto2.getPartyTypeTrns());
					}

					partyType1Or2 = commonBo.getPartyType1Or2(regForm.getDeedID(), regForm.getInstID(), roleId1);
					if (partyType1Or2 == 1) {
						party1Added = 1;
					} else if (partyType1Or2 == 2) {
						party2Added = 1;
					} else {
						party3Added = 1;
					}
				}

				if (regForm.getDeedID() == RegInitConstant.DEED_TRUST || regForm.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV || regForm.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV || (regForm.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_NPV && !(regForm.getInstID() == RegInitConstant.INST_DISSOLUTION_NPV || regForm.getInstID() == RegInitConstant.INST_DISSOLUTION_2)) || regForm.getDeedID() == RegInitConstant.DEED_PARTITION_NPV) {
					if (party1Added == 0) {
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_3_EN);
						} else {
							request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_3_HI);
						}
						map.remove(Integer.toString(count));
						regForm.setMapKeyCount(--count);
						regForm.setAddMoreCounter(count);
						regForm.setMapTransactingParties(map);
						actionName = RegInitConstant.NO_ACTION;
						regForm.setActionName(RegInitConstant.NO_ACTION);
						request.setAttribute("deedId", regForm.getDeedID());
						request.setAttribute("instId", regForm.getInstID());
						request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
						forward = "transactingParty";
						return mapping.findForward(forward);
					}
				} else if (party1Added == 0 || party2Added == 0) {
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_3_EN);
					} else {
						request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_3_HI);
					}

					map.remove(Integer.toString(count));
					regForm.setMapKeyCount(--count);
					regForm.setAddMoreCounter(count);
					regForm.setMapTransactingParties(map);
					actionName = RegInitConstant.NO_ACTION;
					regForm.setActionName(RegInitConstant.NO_ACTION);
					request.setAttribute("deedId", regForm.getDeedID());
					request.setAttribute("instId", regForm.getInstID());
					request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
					forward = "transactingParty";
					return mapping.findForward(forward);
				}

				// end of insertion of last transacting party in map
				regForm.getMapTransPartyDbInsertion().put(Integer.toString(count), mapDto);

				// below code is for insertion of transacting parties in
				// database.
				Collection mapCollection = regForm.getMapTransPartyDbInsertion().values();
				Object[] l1 = mapCollection.toArray();
				RegCommonDTO mapDto1 = new RegCommonDTO();
				boolean allUploadSuccessful = false;
				boolean transPartyDetailsInserted = false;
				// below loop for creating folder structure of uploads
				String filePath;
				String filePathPhotoProof;
				String filePathNotAffExec;
				String filePathExecPhoto;
				String filePathNotAffAttrn;
				String filePathAttrnPhoto;
				String filePathClaimPhoto;
				String filePathPanForm60;
				// above declarations added because of audit report

				for (int i = 0; i < l1.length; i++) {
					allUploadSuccessful = false;
					mapDto1 = (RegCommonDTO) l1[i];

					mapDto1.setU2FilePathTrns("");
					mapDto1.setU3FilePathTrns("");
					mapDto1.setU10FilePathTrns("");
					mapDto1.setU6FilePathTrns("");
					mapDto1.setU11FilePathTrns("");
					mapDto1.setU9FilePathTrns("");
					mapDto1.setFilePathTrns("");

					filePath = "";
					filePathPhotoProof = "";
					filePathNotAffExec = "";
					filePathExecPhoto = "";
					filePathNotAffAttrn = "";
					filePathAttrnPhoto = "";
					filePathClaimPhoto = "";
					filePathPanForm60 = "";
					// above commented for audit report

					int dtoRoleId = Integer.parseInt(mapDto1.getPartyTypeTrns());

					String[] claimantArr1 = commonBo.getClaimantFlag(Integer.toString(dtoRoleId));
					int claimantFlag1 = Integer.parseInt(claimantArr1[0].trim());

					boolean additionalUpload = false;

					ArrayList<CommonDTO> dto = mapDto1.getListDto();

					if (dto != null && dto.size() > 0) {
						for (int j = 0; j < dto.size(); j++) {
							CommonDTO dtos = dto.get(j);
							dtos.setDocumentName(commonBo.getNewFileName(dtos.getDocumentName(), Integer.toString(j)));
							String checkUpload = uploadFile(regForm.getHidnRegTxnId(), dtos.getDocContents(), mapDto1.getPartyRoleTypeId(), "01", dtos.getDocumentName());
							if (checkUpload == null) {
								additionalUpload = false;
								break;
							} else {
								dtos.setDocumentPath(checkUpload);
								additionalUpload = true;
							}

						}

					} else {
						additionalUpload = true;
					}
					// mohit additional upload

					if (!mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
						if (additionalUpload) {
							if (mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID) || mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)) {

								if ((mapDto1.getU2DocumentNameTrns() != null && !mapDto1.getU2DocumentNameTrns().equalsIgnoreCase(""))) {
									filePathPhotoProof = uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU2DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU2PartyOrPropTrns(), mapDto1.getU2UploadTypeTrns());
								} else {
									filePathPhotoProof = "";
								}

								if (filePathPhotoProof != null) {
									mapDto1.setU2FilePathTrns(filePathPhotoProof);
									// BELOW CODE FOR EXECUTANT
									if (claimantFlag1 == RegInitConstant.CLAIMANT_FLAG_1) {

										if (mapDto1.getU3DocumentNameTrns() != null && !mapDto1.getU3DocumentNameTrns().equalsIgnoreCase("")) {
											filePathNotAffExec = uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU3DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU3PartyOrPropTrns(), mapDto1.getU3UploadTypeTrns());
										} else {
											filePathNotAffExec = "";
										}

										if (filePathNotAffExec != null) {
											mapDto1.setU3FilePathTrns(filePathNotAffExec);

											if (mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
												if (!mapDto1.getSelectedPhotoId().equalsIgnoreCase("4") && (regForm.getTransCheck().equalsIgnoreCase("Y") || (mapDto1.getU10DocumentNameTrns() != null && !mapDto1.getU10DocumentNameTrns().equalsIgnoreCase("")))) {

													filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU10DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU10PartyOrPropTrns(), mapDto1.getU10UploadTypeTrns());

													if (filePathPanForm60 != null && !filePathPanForm60.equalsIgnoreCase("")) {
														mapDto1.setU10FilePathTrns(filePathPanForm60);
														allUploadSuccessful = true;
													} else {
														allUploadSuccessful = false;
														break;
													}

												} else {
													allUploadSuccessful = true;
												}

											} else {

												if ((regForm.getTransCheck().equalsIgnoreCase("Y") || (mapDto1.getU10DocumentNameTrns() != null && !mapDto1.getU10DocumentNameTrns().equalsIgnoreCase("")))) {

													filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU10DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU10PartyOrPropTrns(), mapDto1.getU10UploadTypeTrns());

													if (filePathPanForm60 != null && !filePathPanForm60.equalsIgnoreCase("")) {
														mapDto1.setU10FilePathTrns(filePathPanForm60);
														allUploadSuccessful = true;
													} else {
														allUploadSuccessful = false;
														break;
													}

												} else {
													allUploadSuccessful = true;
												}

											}

										} else {
											allUploadSuccessful = false;
											break;
										}
									}
									// BELOW CODE FOR EXECUTANT POA HOLDER
									if (claimantFlag1 == RegInitConstant.CLAIMANT_FLAG_2) {

										filePathAttrnPhoto = uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU6DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU6PartyOrPropTrns(), mapDto1.getU6UploadTypeTrns());

										if (filePathAttrnPhoto != null && !filePathAttrnPhoto.equalsIgnoreCase("")) {
											mapDto1.setU6FilePathTrns(filePathAttrnPhoto);

											allUploadSuccessful = true;
										} else {
											allUploadSuccessful = false;
											break;
										}

									}
									// BELOW CODE FOR CLAIMANT
									if (claimantFlag1 == RegInitConstant.CLAIMANT_FLAG_3) {

										if (mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
											if (!mapDto1.getSelectedPhotoId().equalsIgnoreCase("4") && (regForm.getTransCheck().equalsIgnoreCase("Y") || (mapDto1.getU11DocumentNameTrns() != null && !mapDto1.getU11DocumentNameTrns().equalsIgnoreCase("")))) {

												filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU11DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU11PartyOrPropTrns(), mapDto1.getU11UploadTypeTrns());

												if (filePathPanForm60 != null && !filePathPanForm60.equalsIgnoreCase("")) {
													mapDto1.setU11FilePathTrns(filePathPanForm60);
													allUploadSuccessful = true;
												} else {
													allUploadSuccessful = false;
													break;
												}

											} else {
												allUploadSuccessful = true;
											}

										} else {

											if ((regForm.getTransCheck().equalsIgnoreCase("Y") || (mapDto1.getU11DocumentNameTrns() != null && !mapDto1.getU11DocumentNameTrns().equalsIgnoreCase("")))) {

												filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU11DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU11PartyOrPropTrns(), mapDto1.getU11UploadTypeTrns());

												if (filePathPanForm60 != null && !filePathPanForm60.equalsIgnoreCase("")) {
													mapDto1.setU11FilePathTrns(filePathPanForm60);
													allUploadSuccessful = true;
												} else {
													allUploadSuccessful = false;
													break;
												}

											} else {
												allUploadSuccessful = true;
											}

										}
									}

									// BELOW CODE FOR CLAIMANT POA HOLDER
									if (claimantFlag1 == RegInitConstant.CLAIMANT_FLAG_4) {

										filePathAttrnPhoto = uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU9DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU9PartyOrPropTrns(), mapDto1.getU9UploadTypeTrns());

										if (filePathAttrnPhoto != null && !filePathAttrnPhoto.equalsIgnoreCase("")) {
											mapDto1.setU9FilePathTrns(filePathAttrnPhoto);
											allUploadSuccessful = true;

										} else {
											allUploadSuccessful = false;
											break;
										}
									}

									if (mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
										if (mapDto1.getIndCategoryTrns().equalsIgnoreCase("1") && mapDto1.getIndScheduleAreaTrns().equalsIgnoreCase("N")) {
											filePath = uploadFile(regForm.getHidnRegTxnId(), mapDto1.getDocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getPartyOrPropTrns(), mapDto1.getUploadTypeTrns());
											if (filePath != null && !filePath.equalsIgnoreCase("")) {
												mapDto1.setFilePathTrns(filePath);
												allUploadSuccessful = true;
											} else {
												allUploadSuccessful = false;
												break;
											}
										}
									}
								}

							} else {
								allUploadSuccessful = true;
							}

						}
					} else {
						allUploadSuccessful = true;
					}

				}

				// below loop for inserting data into database
				if (allUploadSuccessful) {
					for (int i = 0; i < l1.length; i++) {
						mapDto1 = (RegCommonDTO) l1[i];

						boolean insertAdditionalUploads = false;
						// mohit database insertion

						if (mapDto1.getListDto() != null && mapDto1.getListDto().size() > 0) {
							insertAdditionalUploads = commonBo.insertAdditionalUploads(mapDto1, regForm);
						} else {
							insertAdditionalUploads = true;
						}

						if (insertAdditionalUploads) {
							mapDto1.setPropertyId(regForm.getPropertyId());
							transPartyDetailsInserted = commonBo.insertTransPartyDetails(mapDto1, regForm, regForm.getExchangePropertyList(), regForm.getIsMultiplePropsFlag(), regForm.getAgriPropertyList());

							// insert in db-anuj

							if (!transPartyDetailsInserted) {
								forward = "transactingParty";
								regForm.setHidnRegTxnId(null);
								actionName = RegInitConstant.NO_ACTION;
								regForm.setActionName(RegInitConstant.NO_ACTION);
								break;
							}
						}

					}
					if (transPartyDetailsInserted) {

						forward = "reginitProperty";

					} else {

						forward = "transactingParty";
						regForm.setHidnRegTxnId(null);
						actionName = RegInitConstant.NO_ACTION;
						regForm.setActionName(RegInitConstant.NO_ACTION);

					}

				} else {
					forward = "transactingParty";
					regForm.setHidnRegTxnId(null);
					actionName = RegInitConstant.NO_ACTION;
					regForm.setActionName(RegInitConstant.NO_ACTION);
				}

				regForm.setMapTransPartyDbInsertion(new HashMap());
				request.setAttribute("propAction", "propAction");
				request.setAttribute("regInitForm", regForm);

				regForm.setActionName(RegInitConstant.NO_ACTION);
			}

			if (regForm.getMapKeyCount() == 0)
				regForm.setDeleteTrnsPrtyID("");

			if (RegInitConstant.ADD_MORE_ACTION.equals(actionName)) {

				regForm.setActionName("");
				RegCommonDTO mapDto = new RegCommonDTO();

				// following code for insertion of owner details into map

				int roleId = Integer.parseInt(regForm.getPartyTypeTrns());
				String[] claimantArr = commonBo.getClaimantFlag(Integer.toString(roleId));
				int claimantFlag = Integer.parseInt(claimantArr[0].trim());
				if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {

					mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);

					RegCommonDTO mapDto1 = new RegCommonDTO();

					mapDto1.setOwnerNameTrns(regForm.getOwnerNameTrns());
					if (regForm.getOwnerOgrNameTrns().equalsIgnoreCase("") || regForm.getOwnerOgrNameTrns().equalsIgnoreCase("null"))
						mapDto1.setOwnerOgrNameTrns("-");
					else
						mapDto1.setOwnerOgrNameTrns(regForm.getOwnerOgrNameTrns());

					mapDto1.setOwnerGendarValTrns(regForm.getOwnerGendarTrns());
					if (regForm.getOwnerGendarTrns().equalsIgnoreCase("f")) {
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto1.setOwnerGendarTrns(RegInitConstant.FEMALE_ENGLISH);

						} else {
							mapDto1.setOwnerGendarTrns(RegInitConstant.FEMALE_HINDI);

						}
					} else {
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto1.setOwnerGendarTrns(RegInitConstant.MALE_ENGLISH);

						} else {
							mapDto1.setOwnerGendarTrns(RegInitConstant.MALE_HINDI);

						}
					}
					mapDto1.setOwnerNationalityTrns(regForm.getOwnerNationalityTrns());
					mapDto1.setOwnerAddressTrns(regForm.getOwnerAddressTrns());
					mapDto1.setOwnerPhnoTrns(regForm.getOwnerPhnoTrns());
					if (regForm.getOwnerEmailIDTrns().equalsIgnoreCase("") || regForm.getOwnerEmailIDTrns().equalsIgnoreCase("null"))
						mapDto1.setOwnerEmailIDTrns("-");
					else
						mapDto1.setOwnerEmailIDTrns(regForm.getOwnerEmailIDTrns());

					mapDto1.setOwnerAgeTrns(regForm.getOwnerAgeTrns());

					mapDto1.setOwnerIdnoTrns(regForm.getOwnerIdnoTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerIdnoTrns());
					mapDto1.setOwnerListIDTrns(regForm.getOwnerListIDTrns());
					mapDto1.setOwnerProofNameTrns(regForm.getOwnerListIDTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerProofNameTrns());

					if (regForm.getAddressOwnerOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressOwnerOutMpTrns())) {
						mapDto1.setAddressOwnerOutMpTrns(regForm.getAddressOwnerOutMpTrns());
					} else {
						mapDto1.setAddressOwnerOutMpTrns("-");
					}

					mapDto1.setOwnerTxnId(commonBo.getTransactingPartyIdSeq());

					regForm.getTrnsOwnerList().put(mapDto1.getOwnerTxnId(), mapDto1);

					mapDto.setTrnsOwnerList(regForm.getTrnsOwnerList());
				}

				mapDto.setListAdoptedPartyTrns(regForm.getListAdoptedPartyTrns());
				mapDto.setListAdoptedPartyNameTrns(commonBo.getAppleteName(regForm.getListAdoptedPartyTrns().trim(), languageLocale));
				mapDto.setPurposeTrns("");
				mapDto.setBname(""); // might be changed
				mapDto.setBaddress(""); // might be changed
				mapDto.setApplicantOrTransParty("Transacting");
				mapDto.setPartyTypeTrns(regForm.getPartyTypeTrns());
				regForm.setPartyRoleTypeId(commonBo.getTransactingPartyIdSeq());

				mapDto.setPartyRoleTypeId(regForm.getPartyRoleTypeId());
				mapDto.setUserID(regForm.getHidnUserId());

				if (regForm.getRelationWithOwnerTrns() != null && (regForm.getRelationWithOwnerTrns().equalsIgnoreCase("") || regForm.getRelationWithOwnerTrns().equalsIgnoreCase("null"))) {
					mapDto.setRelationWithOwnerTrns("-");
				} else {
					mapDto.setRelationWithOwnerTrns(regForm.getRelationWithOwnerTrns());
				}
				if (regForm.getShareOfPropTrns().equalsIgnoreCase("") || regForm.getShareOfPropTrns().equalsIgnoreCase("null")) {
					mapDto.setShareOfPropTrns("-");
					mapDto.setHdnDeclareShareCheck("N");
				} else {
					mapDto.setShareOfPropTrns(regForm.getShareOfPropTrns());
					mapDto.setHdnDeclareShareCheck("Y");
				}

				String applicantRoleId1 = commonBo.getApplicantRoleId(regForm.getHidnRegTxnId());

				// following if condition for disabling applicant role once
				// total sum of share is 100%.

				if (regForm.getTotalShareOfProp() == 100) {
					regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId1));
				}

				mapDto.setRoleName(commonBo.getRoleName(regForm.getPartyTypeTrns(), languageLocale, regForm.getDeedID(), regForm.getInstID()));

				if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {

					// mapDto.setGovtOfcCheck(regForm.getGovtOfcCheck());
					if (regForm.getNameOfOfficialTrns() != null && !("").equalsIgnoreCase(regForm.getNameOfOfficialTrns())) {
						mapDto.setNameOfOfficial(regForm.getNameOfOfficialTrns());
					} else {
						mapDto.setNameOfOfficial("-");
					}
					mapDto.setDesgOfOfficial(regForm.getDesgOfOfficialTrns());
					mapDto.setAddressOfOfficial(regForm.getAddressOfOfficialTrns());
					mapDto.setSelectedDistrict(regForm.getInddistrictTrns());
					mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getInddistrictTrns(), languageLocale));
					mapDto.setOgrNameTrns("-");
					//mapDto.setAuthPerNameTrns(regForm.getAuthPerName().trim())
					// ;
					mapDto.setIndAuthPersn(mapDto.getDesgOfOfficial());
					mapDto.setIndividualOrOrganization(RegInitConstant.GOVT_OFFCL_ID);

					if (regForm.getAddressGovtOffclOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressGovtOffclOutMpTrns())) {
						mapDto.setAddressGovtOffclOutMpTrns(regForm.getAddressGovtOffclOutMpTrns());
					} else {
						mapDto.setAddressGovtOffclOutMpTrns("-");
					}

				} else if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)) {
					// organization
					mapDto.setOgrNameTrns(regForm.getOgrNameTrns());
					mapDto.setAuthPerNameTrns(regForm.getAuthPerNameTrns());
					mapDto.setIndAuthPersn(regForm.getAuthPerNameTrns());
					mapDto.setOrgaddressTrns(regForm.getOrgaddressTrns());
					mapDto.setSelectedCountry(regForm.getCountryTrns());
					mapDto.setSelectedCountryName(commonBo.getCountryName("1", languageLocale));
					mapDto.setSelectedState(regForm.getStatenameTrns());
					mapDto.setSelectedStateName(commonBo.getStateName("1", languageLocale));
					mapDto.setSelectedDistrict(regForm.getDistrictTrns());
					mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getDistrictTrns(), languageLocale));
					if (regForm.getMobnoTrns().equalsIgnoreCase("") || regForm.getMobnoTrns().equalsIgnoreCase("null"))
						mapDto.setMobnoTrns("-");
					else
						mapDto.setMobnoTrns(regForm.getMobnoTrns());
					if (regForm.getPhnoTrns().equalsIgnoreCase("") || regForm.getPhnoTrns().equalsIgnoreCase("null"))
						mapDto.setPhnoTrns("-");
					else
						mapDto.setPhnoTrns(regForm.getPhnoTrns());
					mapDto.setConsiAmountTrns(regForm.getConsiAmountTrns());
					mapDto.setMarketValueTrns(regForm.getMarketValueTrns());

					mapDto.setFnameTrns("");
					mapDto.setMnameTrns("");
					mapDto.setLnameTrns("");
					// mapDto.setGendarTrns("-");
					// mapDto.setSelectedGender("");
					mapDto.setAgeTrns("");
					// mapDto.setFatherNameTrns("-");
					mapDto.setMotherNameTrns("");
					mapDto.setSpouseNameTrns("");
					mapDto.setNationalityTrns("");
					mapDto.setPostalCodeTrns("");
					mapDto.setEmailIDTrns("");
					mapDto.setSelectedPhotoId("");
					mapDto.setSelectedPhotoIdName("");
					mapDto.setIdnoTrns("-");

					// mapDto.setIndReligionTrns("");
					mapDto.setIndCasteTrns("");
					mapDto.setIndDisabilityTrns("");

					mapDto.setIndividualOrOrganization(RegInitConstant.ORGANISATION_ID);

					mapDto.setAuthPerGendarTrns(regForm.getAuthPerGendarTrns());
					if (regForm.getAuthPerGendarTrns().equalsIgnoreCase("m")) {
						// mapDto.setSelectedGender("Male");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setSelectedGender(RegInitConstant.MALE_ENGLISH);

						} else {
							mapDto.setSelectedGender(RegInitConstant.MALE_HINDI);

						}
					} else {
						// mapDto.setSelectedGender("Female");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setSelectedGender(RegInitConstant.FEMALE_ENGLISH);

						} else {
							mapDto.setSelectedGender(RegInitConstant.FEMALE_HINDI);

						}
					}
					mapDto.setAuthPerFatherNameTrns(regForm.getAuthPerFatherNameTrns());

					mapDto.setRelationshipTrns(regForm.getAuthPerRelationshipTrns());
					mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getAuthPerRelationshipTrns()), languageLocale));

					mapDto.setAuthPerAddressTrns(regForm.getAuthPerAddressTrns().trim());
					mapDto.setAuthPerCountryTrns(regForm.getAuthPerCountryTrns());
					mapDto.setAuthPerCountryNameTrns(commonBo.getCountryName(regForm.getAuthPerCountryTrns(), languageLocale));
					mapDto.setAuthPerStatenameTrns(regForm.getAuthPerStatenameTrns());
					mapDto.setAuthPerStatenameNameTrns(commonBo.getStateName(regForm.getAuthPerStatenameTrns(), languageLocale));
					mapDto.setAuthPerDistrictTrns(regForm.getAuthPerDistrictTrns());
					mapDto.setAuthPerDistrictNameTrns(commonBo.getDistrictName(regForm.getAuthPerDistrictTrns().trim(), languageLocale));

					if (regForm.getAddressOrgOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressOrgOutMpTrns())) {
						mapDto.setAddressOrgOutMpTrns(regForm.getAddressOrgOutMpTrns());
					} else {
						mapDto.setAddressOrgOutMpTrns("-");
					}

					if (regForm.getAddressAuthPerOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressAuthPerOutMpTrns())) {
						mapDto.setAddressAuthPerOutMpTrns(regForm.getAddressAuthPerOutMpTrns());
					} else {
						mapDto.setAddressAuthPerOutMpTrns("-");
					}
				} else if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
					// individual
					mapDto.setFnameTrns(regForm.getFnameTrns());
					if (regForm.getMnameTrns().equalsIgnoreCase("") || regForm.getMnameTrns().equalsIgnoreCase("null"))
						mapDto.setMnameTrns("-");
					else
						mapDto.setMnameTrns(regForm.getMnameTrns());
					mapDto.setLnameTrns(regForm.getLnameTrns());
					mapDto.setGendarTrns(regForm.getGendarTrns());
					if (regForm.getGendarTrns().equalsIgnoreCase("m")) {
						// mapDto.setSelectedGender("Male");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setSelectedGender(RegInitConstant.MALE_ENGLISH);

						} else {
							mapDto.setSelectedGender(RegInitConstant.MALE_HINDI);

						}
					} else {
						// mapDto.setSelectedGender("Female");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setSelectedGender(RegInitConstant.FEMALE_ENGLISH);

						} else {
							mapDto.setSelectedGender(RegInitConstant.FEMALE_HINDI);

						}
					}
					if (regForm.getAgeTrns().equalsIgnoreCase(""))
						mapDto.setAgeTrns("-");
					else
						mapDto.setAgeTrns(regForm.getAgeTrns());
					mapDto.setFatherNameTrns(regForm.getFatherNameTrns());
					if (regForm.getMotherNameTrns().equalsIgnoreCase(""))
						mapDto.setMotherNameTrns("-");
					else
						mapDto.setMotherNameTrns(regForm.getMotherNameTrns());
					if (regForm.getSpouseNameTrns().equalsIgnoreCase(""))
						mapDto.setSpouseNameTrns("-");
					else
						mapDto.setSpouseNameTrns(regForm.getSpouseNameTrns());
					mapDto.setNationalityTrns(regForm.getNationalityTrns());
					if (regForm.getPostalCodeTrns().equalsIgnoreCase(""))
						mapDto.setPostalCodeTrns("-");
					else
						mapDto.setPostalCodeTrns(regForm.getPostalCodeTrns());

					if (regForm.getIndphnoTrns().equalsIgnoreCase(""))
						mapDto.setPhnoTrns("-");
					else
						mapDto.setPhnoTrns(regForm.getIndphnoTrns());

					if (regForm.getIndmobnoTrns().equalsIgnoreCase(""))
						mapDto.setMobnoTrns("-");
					else
						mapDto.setMobnoTrns(regForm.getIndmobnoTrns());

					if (regForm.getEmailIDTrns().equalsIgnoreCase(""))
						mapDto.setEmailIDTrns("-");
					else
						mapDto.setEmailIDTrns(regForm.getEmailIDTrns());
					mapDto.setSelectedPhotoId(regForm.getListIDTrns());
					mapDto.setSelectedPhotoIdName(regForm.getListIDNameTrns());
					mapDto.setIdnoTrns(regForm.getIdnoTrns());

					mapDto.setOgrNameTrns("-");
					mapDto.setAuthPerNameTrns("-");
					mapDto.setIndAuthPersn(regForm.getFnameTrns() + " " + regForm.getLnameTrns());
					mapDto.setOrgaddressTrns(regForm.getIndaddressTrns());
					mapDto.setSelectedCountry(regForm.getIndcountryTrns());
					mapDto.setSelectedCountryName(commonBo.getCountryName("1", languageLocale));
					mapDto.setSelectedState(regForm.getIndstatenameTrns());
					mapDto.setSelectedStateName(commonBo.getStateName("1", languageLocale));
					mapDto.setSelectedDistrict(regForm.getInddistrictTrns());
					mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getInddistrictTrns(), languageLocale));
					mapDto.setSelectedCategoryName(regForm.getSelectedCategoryNameTrns());
					mapDto.setIndCategoryTrns(regForm.getIndCategoryTrns());
					mapDto.setSelectedOccupationName(commonBo.getOccupationName(regForm.getOccupationIdTrns(), languageLocale));
					mapDto.setOccupationIdTrns(regForm.getOccupationIdTrns());

					mapDto.setIndScheduleAreaTrns(regForm.getIndScheduleAreaTrns());

					regForm.setAbc("2");

					if (regForm.getIndCategoryTrns().equalsIgnoreCase(RegInitConstant.ST_CONSTANT)) {
						if (regForm.getIndScheduleAreaTrns() != null) {
							if (regForm.getIndScheduleAreaTrns().equalsIgnoreCase("Y")) {
								// mapDto.setIndScheduleAreaTrnsDisplay("Yes");
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_ENGLISH);
								} else {
									mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_HINDI);
								}
							} else {
								// mapDto.setIndScheduleAreaTrnsDisplay("No");
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_ENGLISH);
								} else {
									mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_HINDI);
								}
								mapDto.setPermissionNoTrns(regForm.getPermissionNoTrns());
								mapDto.setDocumentNameTrns(regForm.getDocumentNameTrns());
								mapDto.setDocumentSizeTrns(regForm.getDocumentSizeTrns());
								mapDto.setFilePathTrns(regForm.getFilePathTrns());
								mapDto.setDocContentsTrns(regForm.getDocContentsTrns());
								mapDto.setPartyOrPropTrns(regForm.getPartyOrPropTrns());
								mapDto.setUploadTypeTrns(regForm.getUploadTypeTrns());

							}
						}
					}

					if (regForm.getIndDisabilityTrns().equalsIgnoreCase("")) {
						mapDto.setIndDisabilityTrns("-");
					} else if (regForm.getIndDisabilityTrns().equalsIgnoreCase("Y")) {
						mapDto.setIndDisabilityIdTrns(regForm.getIndDisabilityTrns());
						// mapDto.setIndDisabilityTrns("Yes");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setIndDisabilityTrns(RegInitConstant.YES_ENGLISH);
						} else {
							mapDto.setIndDisabilityTrns(RegInitConstant.YES_HINDI);
						}
						if (regForm.getIndDisabilityDescTrns().equalsIgnoreCase("")) {
							mapDto.setIndDisabilityDescTrns("-");
						} else {
							mapDto.setIndDisabilityDescTrns(regForm.getIndDisabilityDescTrns());
						}
					} else if (regForm.getIndDisabilityTrns().equalsIgnoreCase("N")) {
						mapDto.setIndDisabilityIdTrns(regForm.getIndDisabilityTrns());
						// mapDto.setIndDisabilityTrns("No");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setIndDisabilityTrns(RegInitConstant.NO_ENGLISH);
						} else {
							mapDto.setIndDisabilityTrns(RegInitConstant.NO_HINDI);
						}

					}

					mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);

					// mapDto.setUserDOBTrns(UserRegistrationBD.getOracleDate(
					// regForm.getUserDayTrns(),
					// regForm.getUserMonthTrns(), regForm.getUserYearTrns()));

					if (regForm.getIndMinorityTrns().equalsIgnoreCase("")) {
						mapDto.setIndMinorityTrns("-");
					} else if (regForm.getIndMinorityTrns().equalsIgnoreCase("Y")) {
						// mapDto.setIndMinorityTrns("Yes");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setIndMinorityTrns(RegInitConstant.YES_ENGLISH);
						} else {
							mapDto.setIndMinorityTrns(RegInitConstant.YES_HINDI);
						}

					} else if (regForm.getIndMinorityTrns().equalsIgnoreCase("N")) {
						// mapDto.setIndMinorityTrns("No");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setIndMinorityTrns(RegInitConstant.NO_ENGLISH);
						} else {
							mapDto.setIndMinorityTrns(RegInitConstant.NO_HINDI);
						}

					}

					// mapDto.setIndMinorityTrns("");
					if (regForm.getIndPovertyLineTrns().equalsIgnoreCase("")) {
						mapDto.setIndPovertyLineTrns("-");
					} else if (regForm.getIndPovertyLineTrns().equalsIgnoreCase("Y")) {
						// mapDto.setIndPovertyLineTrns("Yes");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setIndPovertyLineTrns(RegInitConstant.YES_ENGLISH);
						} else {
							mapDto.setIndPovertyLineTrns(RegInitConstant.YES_HINDI);
						}

					} else if (regForm.getIndPovertyLineTrns().equalsIgnoreCase("N")) {
						// mapDto.setIndPovertyLineTrns("No");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setIndPovertyLineTrns(RegInitConstant.NO_ENGLISH);
						} else {
							mapDto.setIndPovertyLineTrns(RegInitConstant.NO_HINDI);
						}

					}
					// mapDto.setIndPovertyLineTrns("");

					mapDto.setRelationshipTrns(regForm.getRelationshipTrns());
					mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getRelationshipTrns()), languageLocale));

					if (regForm.getAddressIndOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressIndOutMpTrns())) {
						mapDto.setAddressIndOutMpTrns(regForm.getAddressIndOutMpTrns());
					} else {
						mapDto.setAddressIndOutMpTrns("-");
					}
				}
				// below code for uploads other than collector's permission no.
				if (!regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
					mapDto.setU2DocumentNameTrns(regForm.getU2DocumentNameTrns());
					mapDto.setU2DocumentSizeTrns(regForm.getU2DocumentSizeTrns());
					mapDto.setU2FilePathTrns(regForm.getU2FilePathTrns());
					mapDto.setU2DocContentsTrns(regForm.getU2DocContentsTrns());
					mapDto.setU2PartyOrPropTrns(regForm.getU2PartyOrPropTrns());
					mapDto.setU2UploadTypeTrns(regForm.getU2UploadTypeTrns());

					mapDto.setClaimantFlag(claimantFlag);
					// BELOW CODE FOR EXECUTANT
					if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {
						mapDto.setU3DocumentNameTrns(regForm.getU3DocumentNameTrns());
						mapDto.setU3DocumentSizeTrns(regForm.getU3DocumentSizeTrns());
						mapDto.setU3FilePathTrns(regForm.getU3FilePathTrns());
						mapDto.setU3DocContentsTrns(regForm.getU3DocContentsTrns());
						mapDto.setU3PartyOrPropTrns(regForm.getU3PartyOrPropTrns());
						mapDto.setU3UploadTypeTrns(regForm.getU3UploadTypeTrns());

						mapDto.setU10DocumentNameTrns(regForm.getU10DocumentNameTrns());
						mapDto.setU10DocumentSizeTrns(regForm.getU10DocumentSizeTrns());
						mapDto.setU10FilePathTrns(regForm.getU10FilePathTrns());
						mapDto.setU10DocContentsTrns(regForm.getU10DocContentsTrns());
						mapDto.setU10PartyOrPropTrns(regForm.getU10PartyOrPropTrns());
						mapDto.setU10UploadTypeTrns(regForm.getU10UploadTypeTrns());
					}
					// BELOW CODE FOR EXECUTANT POA HOLDER
					if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2) {

						mapDto.setU6DocumentNameTrns(regForm.getU6DocumentNameTrns());
						mapDto.setU6DocumentSizeTrns(regForm.getU6DocumentSizeTrns());
						mapDto.setU6FilePathTrns(regForm.getU6FilePathTrns());
						mapDto.setU6DocContentsTrns(regForm.getU6DocContentsTrns());
						mapDto.setU6PartyOrPropTrns(regForm.getU6PartyOrPropTrns());
						mapDto.setU6UploadTypeTrns(regForm.getU6UploadTypeTrns());

						mapDto.setSrOfficeNameTrns(regForm.getSrOfficeNameTrns());
						if (regForm.getPoaRegNoTrns() != null && !regForm.getPoaRegNoTrns().equalsIgnoreCase("")) {
							mapDto.setPoaRegNoTrns(regForm.getPoaRegNoTrns());
						} else {
							mapDto.setPoaRegNoTrns("-");
						}

						if (regForm.getDatePoaRegTrns() != null && !regForm.getDatePoaRegTrns().equalsIgnoreCase("")) {
							mapDto.setDatePoaRegTrns(commonBo.convertCalenderDate(regForm.getDatePoaRegTrns()));
						} else {
							mapDto.setDatePoaRegTrns("-");
						}
					}

					// BELOW CODE FOR CLAIMANT
					if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {
						mapDto.setU11DocumentNameTrns(regForm.getU11DocumentNameTrns());
						mapDto.setU11DocumentSizeTrns(regForm.getU11DocumentSizeTrns());
						mapDto.setU11FilePathTrns(regForm.getU11FilePathTrns());
						mapDto.setU11DocContentsTrns(regForm.getU11DocContentsTrns());
						mapDto.setU11PartyOrPropTrns(regForm.getU11PartyOrPropTrns());
						mapDto.setU11UploadTypeTrns(regForm.getU11UploadTypeTrns());
					}

					// BELOW CODE FOR CLAIMANT POA HOLDER
					if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4) {

						mapDto.setU9DocumentNameTrns(regForm.getU9DocumentNameTrns());
						mapDto.setU9DocumentSizeTrns(regForm.getU9DocumentSizeTrns());
						mapDto.setU9FilePathTrns(regForm.getU9FilePathTrns());
						mapDto.setU9DocContentsTrns(regForm.getU9DocContentsTrns());
						mapDto.setU9PartyOrPropTrns(regForm.getU9PartyOrPropTrns());
						mapDto.setU9UploadTypeTrns(regForm.getU9UploadTypeTrns());

					}

				}

				mapDto.setListDto(regForm.getListDto());
				regForm.setListDto(new ArrayList<CommonDTO>());

				map = regForm.getMapTransactingParties();

				int count = regForm.getMapKeyCount();
				if (count == 0)
					count = 1;

				if (map.containsKey(Integer.toString(count))) {

					count++;
					map.put(Integer.toString(count), mapDto);

				} else
					map.put(Integer.toString(count), mapDto);

				regForm.getMapTransPartyDbInsertion().put(Integer.toString(count), mapDto);

				regForm.setMapKeyCount(count);

				regForm.setAddMoreCounter(count);
				// key="key";

				regForm.setMapTransactingParties(map);

				if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
					regForm.setGovtOfcCheckTrns("");
					regForm.setNameOfOfficialTrns("");
					regForm.setDesgOfOfficialTrns("");
					regForm.setAddressOfOfficialTrns("");
					regForm.setAddressGovtOffclOutMpTrns("");
					regForm.setInddistrictTrns("");
					commonDto.setTehsilId1("");

				}
				if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)) {
					regForm.setPartyTypeTrns(null);
					regForm.setOgrNameTrns("");
					regForm.setAuthPerNameTrns("");
					regForm.setOrgaddressTrns("");
					regForm.setPhnoTrns("");
					regForm.setMobnoTrns("");
					regForm.setConsiAmountTrns("");
					regForm.setMarketValueTrns("");
					regForm.setCountryTrns("");
					regForm.setDistrictTrns("");
					commonDto.setTehsilId1("");
					regForm.setStatenameTrns("");
					// commonDto.setCountryTrns(commonBo.getCountry());
					regForm.setActionName("");
					regForm.setOwnershipShareTrns("");
					regForm.setRelationWithOwnerTrns("");
					regForm.setShareOfPropTrns("");
					regForm.setCountryTrns("1");
					regForm.setCountryNameTrns(commonBo.getCountryName("1", languageLocale));
					regForm.setStatenameTrns("1");
					regForm.setStatenameNameTrns(commonBo.getStateName("1", languageLocale));

					regForm.setAuthPerGendarTrns("M");
					regForm.setAuthPerFatherNameTrns("");
					regForm.setAuthPerRelationshipTrns(0);
					regForm.setAuthPerAddressTrns("");
					regForm.setAuthPerDistrictTrns("");
					commonDto.setTehsilId1("");
					regForm.setAddressOrgOutMpTrns("");
					regForm.setAddressAuthPerOutMpTrns("");

					commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));
				}
				if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
					regForm.setPartyTypeTrns(null);
					regForm.setFnameTrns("");
					regForm.setMnameTrns("");
					regForm.setLnameTrns("");
					regForm.setGendarTrns("M");
					regForm.setAgeTrns("");
					regForm.setFatherNameTrns("");
					regForm.setMotherNameTrns("");
					regForm.setSpouseNameTrns("");
					regForm.setNationalityTrns("");
					regForm.setPostalCodeTrns("");
					regForm.setIndphnoTrns("");
					regForm.setIndmobnoTrns("");
					regForm.setEmailIDTrns("");
					regForm.setListIDTrns("");
					regForm.setIdnoTrns("");
					regForm.setIndaddressTrns("");
					regForm.setIndcountryTrns("");
					regForm.setInddistrictTrns("");
					commonDto.setTehsilId1("");
					regForm.setIndstatenameTrns("");
					// commonDto.setIndcountryTrns(commonBo.getCountry());
					// commonDto.setIdProfTrns(commonBo.getIdProf());
					regForm.setActionName("");
					regForm.setIndReligionTrns("");
					regForm.setIndCasteTrns("");
					regForm.setIndDisabilityTrns("N");
					regForm.setIndDisabilityDescTrns("");
					regForm.setIndMinorityTrns("N");
					regForm.setOwnershipShareTrns("");
					regForm.setRelationWithOwnerTrns("");
					regForm.setShareOfPropTrns("");
					regForm.setIndPovertyLineTrns("N");
					// regForm.setIndMinorityTrns("N");
					regForm.setIndCategoryTrns("");
					regForm.setOccupationIdTrns("");
					/*
					 * regForm.setCountryTrns("INDIA");
					 * regForm.setCountryNameTrns("INDIA");
					 */
					regForm.setIndstatenameTrns("1");
					regForm.setIndstatenameNameTrns(commonBo.getStateName("1", languageLocale));
					regForm.setIndcountryTrns("1");
					regForm.setIndcountryNameTrns(commonBo.getCountryName("1", languageLocale));

					regForm.setUserDayTrns("");
					regForm.setUserMonthTrns("");
					regForm.setUserYearTrns("");
					regForm.setUserDOBTrns("");
					regForm.setIndScheduleAreaTrns("");
					regForm.setPermissionNoTrns("");
					regForm.setCertificateTrns(null);
					regForm.setDocumentNameTrns("");
					regForm.setDocumentSizeTrns("");
					regForm.setFilePathTrns("");
					regForm.setDocContentsTrns(new byte[0]);
					regForm.setPartyOrPropTrns("");
					regForm.setUploadTypeTrns("");

					regForm.setAddressIndOutMpTrns("");

					// regForm.setDocContentsTrns(new byte());
					// regForm.setIndstatenameTrns("MP");
					// regForm.setIndstatenameNameTrns("MADHYA PRADESH");

					regForm.setRelationshipTrns(0);
					commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));

				}

				if (!regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
					regForm.setU2Trns(null);
					regForm.setU2DocumentNameTrns("");
					regForm.setU2DocumentSizeTrns("");
					regForm.setU2FilePathTrns("");
					regForm.setU2DocContentsTrns(new byte[0]);
					regForm.setU2PartyOrPropTrns("");
					regForm.setU2UploadTypeTrns("");

					regForm.setU3Trns(null);
					regForm.setU3DocumentNameTrns("");
					regForm.setU3DocumentSizeTrns("");
					regForm.setU3FilePathTrns("");
					regForm.setU3DocContentsTrns(new byte[0]);
					regForm.setU3PartyOrPropTrns("");
					regForm.setU3UploadTypeTrns("");

					regForm.setU4Trns(null);
					regForm.setU4DocumentNameTrns("");
					regForm.setU4DocumentSizeTrns("");
					regForm.setU4FilePathTrns("");
					regForm.setU4DocContentsTrns(new byte[0]);
					regForm.setU4PartyOrPropTrns("");
					regForm.setU4UploadTypeTrns("");

					regForm.setU5Trns(null);
					regForm.setU5DocumentNameTrns("");
					regForm.setU5DocumentSizeTrns("");
					regForm.setU5FilePathTrns("");
					regForm.setU5DocContentsTrns(new byte[0]);
					regForm.setU5PartyOrPropTrns("");
					regForm.setU5UploadTypeTrns("");

					regForm.setU6Trns(null);
					regForm.setU6DocumentNameTrns("");
					regForm.setU6DocumentSizeTrns("");
					regForm.setU6FilePathTrns("");
					regForm.setU6DocContentsTrns(new byte[0]);
					regForm.setU6PartyOrPropTrns("");
					regForm.setU6UploadTypeTrns("");

					regForm.setU7Trns(null);
					regForm.setU7DocumentNameTrns("");
					regForm.setU7DocumentSizeTrns("");
					regForm.setU7FilePathTrns("");
					regForm.setU7DocContentsTrns(new byte[0]);
					regForm.setU7PartyOrPropTrns("");
					regForm.setU7UploadTypeTrns("");

					regForm.setU8Trns(null);
					regForm.setU8DocumentNameTrns("");
					regForm.setU8DocumentSizeTrns("");
					regForm.setU8FilePathTrns("");
					regForm.setU8DocContentsTrns(new byte[0]);
					regForm.setU8PartyOrPropTrns("");
					regForm.setU8UploadTypeTrns("");

					regForm.setU9Trns(null);
					regForm.setU9DocumentNameTrns("");
					regForm.setU9DocumentSizeTrns("");
					regForm.setU9FilePathTrns("");
					regForm.setU9DocContentsTrns(new byte[0]);
					regForm.setU9PartyOrPropTrns("");
					regForm.setU9UploadTypeTrns("");

					regForm.setU10Trns(null);
					regForm.setU10DocumentNameTrns("");
					regForm.setU10DocumentSizeTrns("");
					regForm.setU10FilePathTrns("");
					regForm.setU10DocContentsTrns(new byte[0]);
					regForm.setU10PartyOrPropTrns("");
					regForm.setU10UploadTypeTrns("");

					regForm.setU11Trns(null);
					regForm.setU11DocumentNameTrns("");
					regForm.setU11DocumentSizeTrns("");
					regForm.setU11FilePathTrns("");
					regForm.setU11DocContentsTrns(new byte[0]);
					regForm.setU11PartyOrPropTrns("");
					regForm.setU11UploadTypeTrns("");
				}

				regForm.setSrOfficeNameTrns("");
				regForm.setPoaRegNoTrns("");
				regForm.setDatePoaRegTrns("");
				if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
					regForm.setTrnsOwnerList(new HashMap());
					regForm.setOwnerNameTrns("");
					regForm.setOwnerOgrNameTrns("");
					regForm.setOwnerGendarTrns("M");
					regForm.setOwnerNationalityTrns("");
					regForm.setOwnerAddressTrns("");
					regForm.setOwnerPhnoTrns("");
					regForm.setOwnerEmailIDTrns("");
					regForm.setOwnerIdnoTrns("");
					regForm.setOwnerAgeTrns("");
					regForm.setOwnerListIDTrns("");
					regForm.setOwnerProofNameTrns("");
					commonDto.setIdProf(commonBo.getIdProf(languageLocale));
					regForm.setOwnerDayTrns("");
					regForm.setOwnerMonthTrns("");
					regForm.setOwnerYearTrns("");
					regForm.setAddressOwnerOutMpTrns("");
				}
				regForm.setTermsCondPartyTrns("");
				regForm.setListAdoptedPartyTrns(null);

				forward = "transactingParty";
			}
			if (RegInitConstant.DELETE_MORE_ACTION.equals(actionName)) {

				String[] trnsPrtyID = regForm.getHdnDeleteTrnsPrtyId().split(",");

				int count = regForm.getMapKeyCount() - 1;
				map = regForm.getMapTransactingParties();

				for (int i = 0; i < trnsPrtyID.length; i++) {

					map.remove(trnsPrtyID[i]);

				}
				regForm.setMapTransactingParties(map);

				// above for display map
				// below for insertion map
				map = new HashMap();
				map = regForm.getMapTransPartyDbInsertion();

				for (int j = 0; j < trnsPrtyID.length; j++) {
					logger.debug(trnsPrtyID[j] + " is removed...");
					map.remove(trnsPrtyID[j]);

				}
				regForm.setAddMoreCounter(count);
				regForm.setMapKeyCount(count);

				if (map.size() == 0) {
					regForm.setAddMoreCounter(0);
				}

				regForm.setMapTransPartyDbInsertion(map);

				forward = "transactingParty";
				//request.setAttribute("roleIdTrns",regForm.getPartyTypeTrns());
				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());
				regForm.setActionName(RegInitConstant.NO_ACTION);
				actionName = RegInitConstant.NO_ACTION;
				// return mapping.findForward(forward);
			}

			if (RegInitConstant.RESET_TRANSACTING_ACTION.equals(actionName)) {

				if (regForm.getPartyTypeTrns() != null && !regForm.getPartyTypeTrns().equalsIgnoreCase("") && !regForm.getPartyTypeTrns().equalsIgnoreCase("null")) {
					request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
				} else {
					request.setAttribute("roleIdTrns", 0);
				}

				utilities.abc(regForm, commonBo, languageLocale, commonDto);

				// session.setAttribute("regForm", regForm);

				forward = "transactingParty";

			}
		}

		// back from upload action code here

		if (RegInitConstant.PARTY_PAGE_FORM.equals(formName)) {

			if (RegInitConstant.OWNER_POPUP_MODIFY_CLOSE_ACTION.equals(actionName)) {
				// regForm.setPartyModifyFlag(1);
				String partyId = request.getParameter("key");
				String roleId = request.getParameter("role");

				if (partyId != null && roleId != null) {
					regForm.setPartyTxnId(partyId);
					regForm.setPartyType(roleId);
				}

				HashMap partyDispMap = regForm.getMapTransactingPartiesDisp();
				RegCommonDTO mapDto = (RegCommonDTO) partyDispMap.get(regForm.getPartyTxnId());

				mapDto.setTrnsOwnerList(commonBo.getOwnersHashMap(regForm.getPartyTxnId(), regForm.getHidnRegTxnId()));

				// commonBo.openPartyView(request, regForm, languageLocale);
				if (regForm.getPartyModifyFlag() == 1) {
					regForm.setOwnerModifyFlag(1);
					// commonBo.getPartyDetsForViewConfirmModify(regForm);
					regForm.setTrnsOwnerList(mapDto.getTrnsOwnerList());

				} else {
					regForm.setOwnerModifyFlag(0);
				}
				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());
				request.setAttribute("roleId", regForm.getPartyType());
				forward = "displayRegDetls";
				actionName = "";
				regForm.setActionName("");
				return mapping.findForward(forward);
			}

			if (RegInitConstant.ADD_MORE_APP_OWNER_ACTION.equals(actionName)) {

				RegCommonDTO mapDto = new RegCommonDTO();

				mapDto.setOwnerNameTrns(regForm.getOwnerName());
				if (regForm.getOwnerOgrName().equalsIgnoreCase("") || regForm.getOwnerOgrName().equalsIgnoreCase("null"))
					mapDto.setOwnerOgrNameTrns("-");
				else
					mapDto.setOwnerOgrNameTrns(regForm.getOwnerOgrName());

				mapDto.setOwnerGendarValTrns(regForm.getOwnerGendar());
				if (regForm.getOwnerGendar().equalsIgnoreCase("f")) {
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_ENGLISH);

					} else {
						mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_HINDI);

					}
				} else {
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setOwnerGendarTrns(RegInitConstant.MALE_ENGLISH);

					} else {
						mapDto.setOwnerGendarTrns(RegInitConstant.MALE_HINDI);

					}
				}
				mapDto.setOwnerNationalityTrns(regForm.getOwnerNationality());
				mapDto.setOwnerAddressTrns(regForm.getOwnerAddress());
				mapDto.setOwnerPhnoTrns(regForm.getOwnerPhno());
				if (regForm.getOwnerEmailID().equalsIgnoreCase("") || regForm.getOwnerEmailID().equalsIgnoreCase("null"))
					mapDto.setOwnerEmailIDTrns("-");
				else
					mapDto.setOwnerEmailIDTrns(regForm.getOwnerEmailID());

				mapDto.setOwnerAgeTrns(regForm.getOwnerAge());

				mapDto.setOwnerIdnoTrns(regForm.getOwnerIdno().equalsIgnoreCase("") ? "-" : regForm.getOwnerIdno());
				mapDto.setOwnerListIDTrns(regForm.getOwnerListID());
				mapDto.setOwnerProofNameTrns(regForm.getOwnerListID().equalsIgnoreCase("") ? "-" : regForm.getOwnerProofName());

				if (regForm.getAddressOwnerOutMp() != null && !("").equalsIgnoreCase(regForm.getAddressOwnerOutMp())) {
					mapDto.setAddressOwnerOutMpTrns(regForm.getAddressOwnerOutMp());
				} else {
					mapDto.setAddressOwnerOutMpTrns("-");
				}

				mapDto.setOwnerTxnId(commonBo.getTransactingPartyIdSeq());

				regForm.getAppOwnerList().put(mapDto.getOwnerTxnId(), mapDto);

				regForm.setOwnerName("");
				regForm.setOwnerOgrName("");
				regForm.setOwnerGendar("M");
				regForm.setOwnerNationality("");
				regForm.setOwnerAddress("");
				regForm.setOwnerPhno("");
				regForm.setOwnerEmailID("");
				regForm.setOwnerAge("");
				regForm.setOwnerIdno("");
				regForm.setOwnerListID("");
				regForm.setOwnerProofName("");
				regForm.setAddressOwnerOutMp("");

				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "success";

			}

			if (RegInitConstant.DELETE_MORE_APP_OWNER_ACTION.equals(actionName)) {

				String[] trnsPrtyID = regForm.getHdnDeleteOwnerID().split(",");

				HashMap ownerMap = regForm.getAppOwnerList();

				for (int i = 0; i < trnsPrtyID.length; i++) {

					ownerMap.remove(trnsPrtyID[i]);

				}
				regForm.setAppOwnerList(ownerMap);

				forward = "success";

				regForm.setActionName(RegInitConstant.NO_ACTION);
				actionName = RegInitConstant.NO_ACTION;

			}

			if (RegInitConstant.GENDER_ACTION.equals(actionName)) {
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendar(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "success";

			}
			if (RegInitConstant.AUTH_PER_GENDER_ACTION.equals(actionName)) {
				commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendar(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "success";

			}

			if (RegInitConstant.GENDER_MODIFY_ACTION.equals(actionName)) {
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "displayRegDetls";
				// return mapping.findForward(forward);
			}
			if (RegInitConstant.AUTH_PER_GENDER_MODIFY_ACTION.equals(actionName)) {
				commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "displayRegDetls";
				// return mapping.findForward(forward);
			}

			if (RegInitConstant.CHANGE_PARTY_ACTION.equals(actionName)) {
				String partyType = regForm.getListAdoptedParty();
				logger.debug("partyType:-" + partyType);

				// saveToken(request);
			}

			if (RegInitConstant.NEXT_ACTION.equals(actionName)) {
				// if(isTokenValid(request)) {

				// following code for populating drop downs
				//commonDto.setAppTypeTrns(commonBo.getAppType(languageLocale));
				commonDto.setCountryTrns(commonBo.getCountry(languageLocale));
				commonDto.setIndcountryTrns(commonBo.getCountry(languageLocale));
				commonDto.setIdProfTrns(commonBo.getIdProf(languageLocale));
				commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
				commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
				commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));
				// BELOW CODE FOR INSERTING APPLICANT DETAILS IN DATABASE.

				regForm.setHidnUserId(userId);
				if (regForm.getIsMultiplePropsFlag() == 0) {

				}
				int roleId = Integer.parseInt(regForm.getPartyType());

				String[] claimantArr = commonBo.getClaimantFlag(Integer.toString(roleId));
				int claimantFlag = Integer.parseInt(claimantArr[0].trim());

				regForm.setPartyRoleTypeId(commonBo.getTransactingPartyIdSeq());
				boolean applicantDetailsInserted = false;
				boolean allUploadSuccessful = false;
				String filePath;
				String filePathPhotoProof;
				String filePathNotAffExec;
				// String filePathExecPhoto;
				// String filePathNotAffAttrn;
				String filePathAttrnPhoto;
				// String filePathClaimPhoto;
				String filePathPanForm60;
				regForm.setU2FilePath("");
				regForm.setU3FilePath("");
				regForm.setU10FilePath("");
				regForm.setU6FilePath("");
				regForm.setU11FilePath("");
				regForm.setU9FilePath("");
				regForm.setFilePath("");

				if (!regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {

					if ((regForm.getU2DocumentName() != null && !regForm.getU2DocumentName().equalsIgnoreCase(""))) {
						filePathPhotoProof = uploadFile(regForm.getHidnRegTxnId(), regForm.getU2DocContents(), regForm.getPartyRoleTypeId(), regForm.getU2PartyOrProp(), regForm.getU2UploadType()); // for
																																																		// pan
					} else {
						filePathPhotoProof = "";
					}

					if (filePathPhotoProof != null) {
						regForm.setU2FilePath(filePathPhotoProof);
						// BELOW CODE FOR EXECUTANT
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {
							if (regForm.getU3DocumentName() != null && !regForm.getU3DocumentName().equalsIgnoreCase("")) {
								filePathNotAffExec = uploadFile(regForm.getHidnRegTxnId(), regForm.getU3DocContents(), regForm.getPartyRoleTypeId(), regForm.getU3PartyOrProp(), regForm.getU3UploadType());
							} else {
								filePathNotAffExec = "";
							}
							if (filePathNotAffExec != null) {
								regForm.setU3FilePath(filePathNotAffExec);

								if (regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
									if (!regForm.getListID().equalsIgnoreCase("4") && (regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU10DocumentName() != null && !regForm.getU10DocumentName().equalsIgnoreCase("")))) {

										filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), regForm.getU10DocContents(), regForm.getPartyRoleTypeId(), regForm.getU10PartyOrProp(), regForm.getU10UploadType());

										if (filePathPanForm60 != null) {
											regForm.setU10FilePath(filePathPanForm60);
											allUploadSuccessful = true;
										} else {
											allUploadSuccessful = false;
										}

									} else {
										allUploadSuccessful = true;
									}

								} else {

									if ((regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU10DocumentName() != null && !regForm.getU10DocumentName().equalsIgnoreCase("")))) {

										filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), regForm.getU10DocContents(), regForm.getPartyRoleTypeId(), regForm.getU10PartyOrProp(), regForm.getU10UploadType());

										if (filePathPanForm60 != null) {
											regForm.setU10FilePath(filePathPanForm60);
											allUploadSuccessful = true;
										} else {
											allUploadSuccessful = false;
										}

									} else {
										allUploadSuccessful = true;
									}

								}

							} else {
								allUploadSuccessful = false;
							}
						}
						// BELOW CODE FOR EXECUTANT POA HOLDER
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2) {

							filePathAttrnPhoto = uploadFile(regForm.getHidnRegTxnId(), regForm.getU6DocContents(), regForm.getPartyRoleTypeId(), regForm.getU6PartyOrProp(), regForm.getU6UploadType());

							if (filePathAttrnPhoto != null) {
								regForm.setU6FilePath(filePathAttrnPhoto);

								allUploadSuccessful = true;
							} else {
								allUploadSuccessful = false;
							}
							// }

							// }

						}
						// BELOW CODE FOR CLAIMANT
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {

							if (regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
								if (!regForm.getListID().equalsIgnoreCase("4") && (regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU11DocumentName() != null && !regForm.getU11DocumentName().equalsIgnoreCase("")))) {

									filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), regForm.getU11DocContents(), regForm.getPartyRoleTypeId(), regForm.getU11PartyOrProp(), regForm.getU11UploadType());

									if (filePathPanForm60 != null) {
										regForm.setU11FilePath(filePathPanForm60);
										allUploadSuccessful = true;
									} else {
										allUploadSuccessful = false;
									}

								} else {
									allUploadSuccessful = true;
								}
							} else {

								if ((regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU11DocumentName() != null && !regForm.getU11DocumentName().equalsIgnoreCase("")))) {

									filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), regForm.getU11DocContents(), regForm.getPartyRoleTypeId(), regForm.getU11PartyOrProp(), regForm.getU11UploadType());

									if (filePathPanForm60 != null) {
										regForm.setU11FilePath(filePathPanForm60);
										allUploadSuccessful = true;
									} else {
										allUploadSuccessful = false;
									}

								} else {
									allUploadSuccessful = true;
								}

							}

						}

						// BELOW CODE FOR CLAIMANT POA HOLDER
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4) {

							filePathAttrnPhoto = uploadFile(regForm.getHidnRegTxnId(), regForm.getU9DocContents(), regForm.getPartyRoleTypeId(), regForm.getU9PartyOrProp(), regForm.getU9UploadType());

							if (filePathAttrnPhoto != null) {
								regForm.setU9FilePath(filePathAttrnPhoto);
								allUploadSuccessful = true;

							} else {
								allUploadSuccessful = false;
							}
						}

						if (regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID) && regForm.getIndCategory().equalsIgnoreCase("1") && regForm.getIndScheduleArea().equalsIgnoreCase("N")) {

							filePath = uploadFile(regForm.getHidnRegTxnId(), regForm.getDocContents(), regForm.getPartyRoleTypeId(), regForm.getPartyOrProp(), regForm.getUploadType());
							if (filePath != null) {
								regForm.setFilePath(filePath);
								allUploadSuccessful = true;
							} else {
								allUploadSuccessful = false;
							}
						}
					}

				} else {
					allUploadSuccessful = true;
				}

				if (allUploadSuccessful) {

					boolean check = true;
					ArrayList<CommonDTO> dto = regForm.getListDto();
					if (dto != null && dto.size() > 0) {
						for (int i = 0; i < dto.size(); i++) {
							CommonDTO dtos = dto.get(i);
							dtos.setDocumentName(commonBo.getNewFileName(dtos.getDocumentName(), Integer.toString(i)));
							String checkUpload = uploadFile(regForm.getHidnRegTxnId(), dtos.getDocContents(), regForm.getPartyRoleTypeId(), "01", dtos.getDocumentName());
							if (checkUpload == null) {
								check = false;
								break;
							} else {
								dtos.setDocumentPath(checkUpload);
								check = true;
							}

						}

					} else {
						check = true;
					}

					if (check) {

						if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {

							RegCommonDTO mapDto = new RegCommonDTO();

							mapDto.setOwnerNameTrns(regForm.getOwnerName());
							if (regForm.getOwnerOgrName().equalsIgnoreCase("") || regForm.getOwnerOgrName().equalsIgnoreCase("null"))
								mapDto.setOwnerOgrNameTrns("-");
							else
								mapDto.setOwnerOgrNameTrns(regForm.getOwnerOgrName());

							mapDto.setOwnerGendarValTrns(regForm.getOwnerGendar());
							if (regForm.getOwnerGendar().equalsIgnoreCase("f")) {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_ENGLISH);

								} else {
									mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_HINDI);

								}
							} else {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									mapDto.setOwnerGendarTrns(RegInitConstant.MALE_ENGLISH);

								} else {
									mapDto.setOwnerGendarTrns(RegInitConstant.MALE_HINDI);

								}
							}
							mapDto.setOwnerNationalityTrns(regForm.getOwnerNationality());
							mapDto.setOwnerAddressTrns(regForm.getOwnerAddress());
							mapDto.setOwnerPhnoTrns(regForm.getOwnerPhno());
							if (regForm.getOwnerEmailID().equalsIgnoreCase("") || regForm.getOwnerEmailID().equalsIgnoreCase("null"))
								mapDto.setOwnerEmailIDTrns("-");
							else
								mapDto.setOwnerEmailIDTrns(regForm.getOwnerEmailID());

							mapDto.setOwnerAgeTrns(regForm.getOwnerAge());

							mapDto.setOwnerIdnoTrns(regForm.getOwnerIdno().equalsIgnoreCase("") ? "-" : regForm.getOwnerIdno());
							mapDto.setOwnerListIDTrns(regForm.getOwnerListID());
							mapDto.setOwnerProofNameTrns(regForm.getOwnerListID().equalsIgnoreCase("") ? "-" : regForm.getOwnerProofName());

							if (regForm.getAddressOwnerOutMp() != null && !("").equalsIgnoreCase(regForm.getAddressOwnerOutMp())) {
								mapDto.setAddressOwnerOutMpTrns(regForm.getAddressOwnerOutMp());
							} else {
								mapDto.setAddressOwnerOutMpTrns("-");
							}

							mapDto.setOwnerTxnId(commonBo.getTransactingPartyIdSeq());

							regForm.getAppOwnerList().put(mapDto.getOwnerTxnId(), mapDto);

						}

						applicantDetailsInserted = commonBo.insertApplicantAndPropertyDetails(regForm);
						if (applicantDetailsInserted) {// this if condition

							if (check) {
								check = commonBo.insertAdditionalUploads(regForm);
							} else {
								applicantDetailsInserted = false;
							}

						}

					}
				}

				if (applicantDetailsInserted) {

					RegCommonDTO mapDto = new RegCommonDTO();

					mapDto.setClaimantFlag(claimantFlag);

					mapDto.setListAdoptedPartyTrns(regForm.getListAdoptedParty().trim());
					// mapDto.setListAdoptedPartyNameTrns(regForm.
					// getListAdoptedPartyName().trim());
					mapDto.setListAdoptedPartyNameTrns(commonBo.getAppleteName(regForm.getListAdoptedParty().trim(), languageLocale));
					mapDto.setPurposeTrns(regForm.getPurpose().trim());
					mapDto.setBname("");
					mapDto.setBaddress("");
					mapDto.setApplicantOrTransParty("Applicant");
					mapDto.setPartyRoleTypeId(regForm.getPartyRoleTypeId().trim());
					mapDto.setUserID(regForm.getHidnUserId().trim());

					if (regForm.getRelationWithOwner() != null) {
						if (regForm.getRelationWithOwner().equalsIgnoreCase("") || regForm.getRelationWithOwner().equalsIgnoreCase("null")) {
							mapDto.setRelationWithOwnerTrns("-");
						} else {
							mapDto.setRelationWithOwnerTrns(regForm.getRelationWithOwner().trim());
						}
					}

					mapDto.setRoleName(commonBo.getRoleName(regForm.getPartyType().trim(), languageLocale, regForm.getDeedID(), regForm.getInstID()));

					mapDto.setPartyTypeFlag("A");
					if (!regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
						mapDto.setU2DocumentNameTrns(regForm.getU2DocumentName());
						mapDto.setU2DocumentSizeTrns(regForm.getU2DocumentSize());
						mapDto.setU2FilePathTrns(regForm.getU2FilePath());
						mapDto.setU2DocContentsTrns(regForm.getU2DocContents());

						// BELOW CODE FOR EXECUTANT
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {
							mapDto.setU3DocumentNameTrns(regForm.getU3DocumentName());
							mapDto.setU3DocumentSizeTrns(regForm.getU3DocumentSize());
							mapDto.setU3FilePathTrns(regForm.getU3FilePath());
							mapDto.setU3DocContentsTrns(regForm.getU3DocContents());

							mapDto.setU10DocumentNameTrns(regForm.getU10DocumentName());
							mapDto.setU10DocumentSizeTrns(regForm.getU10DocumentSize());
							mapDto.setU10FilePathTrns(regForm.getU10FilePath());
							mapDto.setU10DocContentsTrns(regForm.getU10DocContents());
						}
						// BELOW CODE FOR EXECUTANT POA HOLDER
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2) {

							mapDto.setU6DocumentNameTrns(regForm.getU6DocumentName());
							mapDto.setU6DocumentSizeTrns(regForm.getU6DocumentSize());
							mapDto.setU6FilePathTrns(regForm.getU6FilePath());
							mapDto.setU6DocContentsTrns(regForm.getU6DocContents());
							mapDto.setSrOfficeNameTrns(regForm.getSrOfficeName());
							if (regForm.getPoaRegNo() != null && !regForm.getPoaRegNo().equalsIgnoreCase("")) {
								mapDto.setPoaRegNoTrns(regForm.getPoaRegNo());
							} else {
								mapDto.setPoaRegNoTrns("-");
							}
							if (regForm.getDatePoaReg() != null && !regForm.getDatePoaReg().equalsIgnoreCase("")) {
								mapDto.setDatePoaRegTrns(commonBo.convertCalenderDate(regForm.getDatePoaReg()));
							} else {
								mapDto.setDatePoaRegTrns("-");
							}
						}

						// BELOW CODE FOR CLAIMANT
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {

							mapDto.setU11DocumentNameTrns(regForm.getU11DocumentName());
							mapDto.setU11DocumentSizeTrns(regForm.getU11DocumentSize());
							mapDto.setU11FilePathTrns(regForm.getU11FilePath());
							mapDto.setU11DocContentsTrns(regForm.getU11DocContents());
						}

						// BELOW CODE FOR CLAIMANT POA HOLDER
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4) {

							mapDto.setU9DocumentNameTrns(regForm.getU9DocumentName());
							mapDto.setU9DocumentSizeTrns(regForm.getU9DocumentSize());
							mapDto.setU9FilePathTrns(regForm.getU9FilePath());
							mapDto.setU9DocContentsTrns(regForm.getU9DocContents());

						}
					}

					mapDto.setListDto(regForm.getListDto());

					regForm.setListDto(new ArrayList<CommonDTO>());

					if (regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {

						// mapDto.setGovtOfcCheck(regForm.getGovtOfcCheck());
						if (regForm.getNameOfOfficial() != null && !("").equalsIgnoreCase(regForm.getNameOfOfficial())) {
							mapDto.setNameOfOfficial(regForm.getNameOfOfficial());
						} else {
							mapDto.setNameOfOfficial("-");
						}
						mapDto.setDesgOfOfficial(regForm.getDesgOfOfficial());
						mapDto.setAddressOfOfficial(regForm.getAddressOfOfficial());
						mapDto.setSelectedDistrict(regForm.getInddistrict());
						mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getInddistrict(), languageLocale));
						mapDto.setOgrNameTrns("-");
						mapDto.setIndAuthPersn(mapDto.getDesgOfOfficial());
						mapDto.setIndividualOrOrganization(RegInitConstant.GOVT_OFFCL_ID);
						regForm.setAbc("2");
						if (regForm.getPartyType() != null) {
							mapDto.setPartyTypeTrns(regForm.getPartyType().trim());
						} else {
							mapDto.setPartyTypeTrns("");
						}

						if (regForm.getAddressGovtOffclOutMp() != null && !("").equalsIgnoreCase(regForm.getAddressGovtOffclOutMp())) {
							mapDto.setAddressGovtOffclOutMpTrns(regForm.getAddressGovtOffclOutMp());
						} else {
							mapDto.setAddressGovtOffclOutMpTrns("-");
						}

					} else if (regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)) {
						// organization
						mapDto.setOgrNameTrns(regForm.getOgrName().trim());
						mapDto.setAuthPerNameTrns(regForm.getAuthPerName().trim());
						mapDto.setIndAuthPersn(regForm.getAuthPerName().trim());
						mapDto.setOrgaddressTrns(regForm.getOrgaddress().trim());
						mapDto.setSelectedCountry(regForm.getCountry().trim());
						mapDto.setSelectedCountryName(commonBo.getCountryName("1", languageLocale));
						mapDto.setSelectedState(regForm.getStatename().trim());
						mapDto.setSelectedStateName(commonBo.getStateName("1", languageLocale));
						mapDto.setSelectedDistrict(regForm.getDistrict().trim());
						mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getDistrict().trim(), languageLocale));
						if (regForm.getMobno().trim().equalsIgnoreCase("") || regForm.getMobno().trim().equalsIgnoreCase("null"))
							mapDto.setMobnoTrns("-");
						else
							mapDto.setMobnoTrns(regForm.getMobno().trim());
						if (regForm.getPhno().trim().equalsIgnoreCase("") || regForm.getPhno().trim().equalsIgnoreCase("null"))
							mapDto.setPhnoTrns("-");
						else
							mapDto.setPhnoTrns(regForm.getPhno().trim());

						mapDto.setFnameTrns("");
						mapDto.setMnameTrns("");
						mapDto.setLnameTrns("");
						mapDto.setGendarTrns("-");
						mapDto.setSelectedGender("");
						mapDto.setAgeTrns("-");
						mapDto.setFatherNameTrns("-");
						mapDto.setMotherNameTrns("");
						mapDto.setSpouseNameTrns("");
						mapDto.setNationalityTrns("");
						mapDto.setPostalCodeTrns("");
						mapDto.setEmailIDTrns("");
						mapDto.setSelectedPhotoId("");
						mapDto.setSelectedPhotoIdName("-");
						mapDto.setIdnoTrns("-");

						mapDto.setPartyTypeTrns(regForm.getPartyType().trim());
						// mapDto.setIndReligionTrns("");
						mapDto.setIndCasteTrns("");
						mapDto.setIndDisabilityTrns("");

						mapDto.setIndividualOrOrganization(RegInitConstant.ORGANISATION_ID);
						mapDto.setAuthPerGendarTrns(regForm.getAuthPerGendar());

						String gendr = "";

						if (regForm.getAuthPerGendar().equalsIgnoreCase("m")) {
							// mapDto.setSelectedGender("Male");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								gendr = RegInitConstant.MALE_ENGLISH;
							} else {
								gendr = RegInitConstant.MALE_HINDI;
							}
						} else {

							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								gendr = RegInitConstant.FEMALE_ENGLISH;
							} else {
								gendr = RegInitConstant.FEMALE_HINDI;
							}
							// mapDto.setSelectedGender("Female");
						}
						mapDto.setSelectedGender(gendr);

						mapDto.setAuthPerFatherNameTrns(regForm.getAuthPerFatherName());

						mapDto.setRelationshipTrns(regForm.getAuthPerRelationship());
						mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getAuthPerRelationship()), languageLocale));

						mapDto.setAuthPerAddressTrns(regForm.getAuthPerAddress().trim());
						mapDto.setAuthPerCountryTrns(regForm.getAuthPerCountry());
						mapDto.setAuthPerCountryNameTrns(commonBo.getCountryName(regForm.getAuthPerCountry(), languageLocale));
						mapDto.setAuthPerStatenameTrns(regForm.getAuthPerStatename());
						mapDto.setAuthPerStatenameNameTrns(commonBo.getStateName(regForm.getAuthPerStatename(), languageLocale));
						mapDto.setAuthPerDistrictTrns(regForm.getAuthPerDistrict());
						mapDto.setAuthPerDistrictNameTrns(commonBo.getDistrictName(regForm.getAuthPerDistrict().trim(), languageLocale));

						if (regForm.getAddressOrgOutMp() != null && !("").equalsIgnoreCase(regForm.getAddressOrgOutMp())) {
							mapDto.setAddressOrgOutMpTrns(regForm.getAddressOrgOutMp());
						} else {
							mapDto.setAddressOrgOutMpTrns("-");
						}

						if (regForm.getAddressAuthPerOutMp() != null && !("").equalsIgnoreCase(regForm.getAddressAuthPerOutMp())) {
							mapDto.setAddressAuthPerOutMpTrns(regForm.getAddressAuthPerOutMp());
						} else {
							mapDto.setAddressAuthPerOutMpTrns("-");
						}

						// mapDto.setUserDOBTrns("-");

					} else if (regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
						// individual
						mapDto.setFnameTrns(regForm.getFname());
						if (regForm.getMname().equalsIgnoreCase("") || regForm.getMname().equalsIgnoreCase("null"))
							mapDto.setMnameTrns("-");
						else
							mapDto.setMnameTrns(regForm.getMname());
						mapDto.setLnameTrns(regForm.getLname());
						mapDto.setGendarTrns(regForm.getGendar());
						String gendr = "";

						if (regForm.getGendar().equalsIgnoreCase("m")) {

							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								gendr = RegInitConstant.MALE_ENGLISH;
							} else {
								gendr = RegInitConstant.MALE_HINDI;
							}
						} else {

							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								gendr = RegInitConstant.FEMALE_ENGLISH;
							} else {
								gendr = RegInitConstant.FEMALE_HINDI;
							}

						}
						mapDto.setSelectedGender(gendr);
						if (regForm.getAge().equalsIgnoreCase("") || regForm.getAge().equalsIgnoreCase("null"))
							mapDto.setAgeTrns("-");
						else
							mapDto.setAgeTrns(regForm.getAge());
						mapDto.setFatherNameTrns(regForm.getFatherName());
						if (regForm.getMotherName().equalsIgnoreCase("") || regForm.getMotherName().equalsIgnoreCase("null"))
							mapDto.setMotherNameTrns("-");
						else
							mapDto.setMotherNameTrns(regForm.getMotherName());
						if (regForm.getSpouseName().equalsIgnoreCase("") || regForm.getSpouseName().equalsIgnoreCase("null"))
							mapDto.setSpouseNameTrns("-");
						else
							mapDto.setSpouseNameTrns(regForm.getSpouseName());
						mapDto.setNationalityTrns(regForm.getNationality());

						if (regForm.getPostalCode().equalsIgnoreCase("") || regForm.getPostalCode().equalsIgnoreCase("null"))
							mapDto.setPostalCodeTrns("-");
						else
							mapDto.setPostalCodeTrns(regForm.getPostalCode());

						if (regForm.getIndphno().equalsIgnoreCase("") || regForm.getIndphno().equalsIgnoreCase("null"))
							mapDto.setPhnoTrns("-");
						else
							mapDto.setPhnoTrns(regForm.getIndphno());

						if (regForm.getIndmobno().equalsIgnoreCase("") || regForm.getIndmobno().equalsIgnoreCase("null"))
							mapDto.setMobnoTrns("-");
						else
							mapDto.setMobnoTrns(regForm.getIndmobno());

						if (regForm.getEmailID().equalsIgnoreCase("") || regForm.getEmailID().equalsIgnoreCase("null"))
							mapDto.setEmailIDTrns("-");
						else
							mapDto.setEmailIDTrns(regForm.getEmailID());
						mapDto.setSelectedPhotoId(regForm.getListID());

						mapDto.setSelectedPhotoIdName(regForm.getListIDName());
						if (regForm.getIdno().equalsIgnoreCase("") || regForm.getIdno().equalsIgnoreCase("null"))
							mapDto.setIdnoTrns("-");
						else
							mapDto.setIdnoTrns(regForm.getIdno());

						mapDto.setOgrNameTrns("-");
						mapDto.setAuthPerNameTrns("-");
						mapDto.setIndAuthPersn(regForm.getFname() + " " + regForm.getLname());
						mapDto.setOrgaddressTrns(regForm.getIndaddress());
						mapDto.setSelectedCountry(regForm.getIndcountry());
						mapDto.setSelectedCountryName(commonBo.getCountryName("1", languageLocale));
						mapDto.setSelectedState(regForm.getIndstatename());
						mapDto.setSelectedStateName(commonBo.getStateName("1", languageLocale));
						mapDto.setSelectedDistrict(regForm.getInddistrict());
						mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getInddistrict(), languageLocale));

						mapDto.setSelectedCategoryName(regForm.getSelectedCategoryName());
						mapDto.setSelectedOccupationName(commonBo.getOccupationName(regForm.getOccupationId(), languageLocale));
						mapDto.setIndCategoryTrns(regForm.getIndCategory());
						mapDto.setPartyTypeTrns(regForm.getPartyType());
						mapDto.setIndScheduleAreaTrns(regForm.getIndScheduleArea());

						if (regForm.getIndScheduleArea() != null) {
							if (regForm.getIndScheduleArea().equalsIgnoreCase("Y")) {
								// mapDto.setIndScheduleAreaTrnsDisplay("Yes");
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_ENGLISH);
								} else {
									mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_HINDI);
								}
							} else {
								// mapDto.setIndScheduleAreaTrnsDisplay("No");
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_ENGLISH);
								} else {
									mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_HINDI);
								}
								mapDto.setPermissionNoTrns(regForm.getPermissionNo());
								mapDto.setDocumentNameTrns(regForm.getDocumentName());
								mapDto.setDocumentSizeTrns(regForm.getDocumentSize());
								mapDto.setFilePathTrns(regForm.getFilePath());
								mapDto.setDocContentsTrns(regForm.getDocContents());

							}
						}

						if (regForm.getIndDisability().equalsIgnoreCase("") || regForm.getIndDisability().equalsIgnoreCase("null")) {
							mapDto.setIndDisabilityTrns("-");
						} else if (regForm.getIndDisability().equalsIgnoreCase("Y")) {
							mapDto.setIndDisabilityIdTrns(regForm.getIndDisability());
							// mapDto.setIndDisabilityTrns("Yes");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setIndDisabilityTrns(RegInitConstant.YES_ENGLISH);
							} else {
								mapDto.setIndDisabilityTrns(RegInitConstant.YES_HINDI);
							}
							if (regForm.getIndDisabilityDesc().equalsIgnoreCase("") || regForm.getIndDisabilityDesc().equalsIgnoreCase("null")) {
								mapDto.setIndDisabilityDescTrns("-");
							} else {
								mapDto.setIndDisabilityDescTrns(regForm.getIndDisabilityDesc());
							}
						} else if (regForm.getIndDisability().equalsIgnoreCase("N")) {
							mapDto.setIndDisabilityIdTrns(regForm.getIndDisability());
							// mapDto.setIndDisabilityTrns("No");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setIndDisabilityTrns(RegInitConstant.NO_ENGLISH);
							} else {
								mapDto.setIndDisabilityTrns(RegInitConstant.NO_HINDI);
							}

						}

						mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);

						if (regForm.getIndMinority().equalsIgnoreCase("") || regForm.getIndMinority().equalsIgnoreCase("null")) {
							mapDto.setIndMinorityTrns("-");
						} else if (regForm.getIndMinority().equalsIgnoreCase("Y")) {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setIndMinorityTrns(RegInitConstant.YES_ENGLISH);
							} else {
								mapDto.setIndMinorityTrns(RegInitConstant.YES_HINDI);
							}

						} else if (regForm.getIndMinority().equalsIgnoreCase("N")) {

							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setIndMinorityTrns(RegInitConstant.NO_ENGLISH);
							} else {
								mapDto.setIndMinorityTrns(RegInitConstant.NO_HINDI);
							}
							// mapDto.setIndMinorityTrns("No");

						}

						if (regForm.getIndPovertyLine().equalsIgnoreCase("") || regForm.getIndPovertyLine().equalsIgnoreCase("null")) {
							mapDto.setIndPovertyLineTrns("-");
						} else if (regForm.getIndPovertyLine().equalsIgnoreCase("Y")) {
							// mapDto.setIndPovertyLineTrns("Yes");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setIndPovertyLineTrns(RegInitConstant.YES_ENGLISH);
							} else {
								mapDto.setIndPovertyLineTrns(RegInitConstant.YES_HINDI);
							}

						} else if (regForm.getIndPovertyLine().equalsIgnoreCase("N")) {
							// mapDto.setIndPovertyLineTrns("No");

							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setIndPovertyLineTrns(RegInitConstant.NO_ENGLISH);
							} else {
								mapDto.setIndPovertyLineTrns(RegInitConstant.NO_HINDI);
							}

						}

						mapDto.setRelationshipTrns(regForm.getRelationship());
						mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getRelationship()), languageLocale));

						if (regForm.getAddressIndOutMp() != null && !("").equalsIgnoreCase(regForm.getAddressIndOutMp())) {
							mapDto.setAddressIndOutMpTrns(regForm.getAddressIndOutMp());
						} else {
							mapDto.setAddressIndOutMpTrns("-");
						}

					}

					if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
						mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						mapDto.setTrnsOwnerList(regForm.getAppOwnerList());

						regForm.setDeleteOwnerID("");
						regForm.setHdnDeleteOwnerID("");

					}

					map = regForm.getMapTransactingParties();
					int count = regForm.getMapKeyCount();
					if (count == 0)
						count = 1;

					if (map.containsKey(Integer.toString(count))) {

						count++;
						map.put(Integer.toString(count), mapDto);

					} else
						map.put(Integer.toString(count), mapDto);

					regForm.setMapKeyCount(count);

					regForm.setAddMoreCounter(count);
					regForm.setMapBuildingDetails(map);

					if (regForm.getInstID() == RegInitConstant.INST_TRUST_DISSOLUTION) {

						// CHECK FOR REDIRECTION ON THE BASIS OF RADIO BUTTON
						// SELECTED.
						if (regForm.getAddMoreTransParty().equalsIgnoreCase("N")) {
							actionName = RegInitConstant.NO_ACTION;
							regForm.setActionName(RegInitConstant.NO_ACTION);
							request.setAttribute("propAction", "propAction");
							request.setAttribute("regInitForm", regForm);
							forward = "reginitProperty";
							return mapping.findForward(forward);
						} else {
							forward = "transactingParty";
						}

					} else {

						forward = "transactingParty";
					}

				} else {
					forward = "failure";
					regForm.setHidnRegTxnId(null);
					request.setAttribute("deedId", regForm.getDeedID());
					request.setAttribute("instId", regForm.getInstID());
					request.setAttribute("roleId", regForm.getPartyType());
					return mapping.findForward(forward);
				}
				actionName = RegInitConstant.NO_ACTION;
				regForm.setActionName(RegInitConstant.NO_ACTION);
				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());
				request.setAttribute("roleId", regForm.getPartyType());
				request.setAttribute("roleIdTrns", 0);
				regForm.setDeedId(request.getAttribute("deedId").toString());
				regForm.setCountryTrns("1");
				regForm.setCountryNameTrns(commonBo.getCountryName("1", languageLocale));
				regForm.setStatenameTrns("1");
				regForm.setStatenameNameTrns(commonBo.getStateName("1", languageLocale));
				regForm.setIndcountryTrns("1");
				regForm.setIndcountryNameTrns(commonBo.getCountryName("1", languageLocale));
				regForm.setIndstatenameTrns("1");
				regForm.setIndstatenameNameTrns(commonBo.getStateName("1", languageLocale));

				regForm.setClaimantFlag(0);
				regForm.setPoaHolderFlag(0);
			}

			if (RegInitConstant.RESET_APPLICANT_ACTION.equals(actionName)) {

				String partyType = regForm.getListAdoptedParty();
				commonDto.setInstrument(new ArrayList());
				commonDto.setExemption(new ArrayList());
				regForm.getCommonDto().setIndtehsilpoaOrgPro7(new ArrayList());
				regForm.getCommonDto().setIndtehsilpoaOrgPro(new ArrayList());
				regForm.getCommonDto().setIndtehsilpoapro(new ArrayList());
				regForm.getCommonDto().setIndtehsil(new ArrayList());
				regForm.getCommonDto().setIndtehsilpoa9(new ArrayList());
				regForm.getCommonDto().setIndtehsilpoa10(new ArrayList());
				regForm.getCommonDto().setIndtehsilpoa2(new ArrayList());
				regForm.setDeleteOwnerID("");
				regForm.setHdnDeleteOwnerID("");
				regForm.setAppOwnerList(new HashMap());
				regForm.setRent(0);
				regForm.setAdvance(0);
				regForm.setDevlpmtCharge(0);
				regForm.setOtherRecCharge(0);
				regForm.setPremium(0);
				regForm.setTermLease(0);
				regForm.setTrustName("");
				regForm.setTrustDate("");
				regForm.setWithPos("");
				regForm.setSecLoanAmt(0);
				regForm.setFnameArb("");
				regForm.setMnameArb("");
				regForm.setLnameArb("");
				regForm.setGendarArb("M");
				regForm.setAgeArb("");
				regForm.setFatherNameArb("");
				regForm.setMotherNameArb("");
				regForm.setSpouseNameArb("");
				regForm.setNationalityArb("");
				regForm.setIndaddressArb("");
				regForm.setInddistrictArb("");
				regForm.setEmailIDArb("");
				regForm.setIndCategoryArb("");
				regForm.setIndDisabilityArb("N");
				regForm.setIndDisabilityDescArb("");
				regForm.setListIDArb("");
				regForm.setIdnoArb("");
				regForm.setAdvancePaymntDetails("");
				regForm.setPossGiven("N");
				regForm.setDissolutionDate("");
				regForm.setRetirmentDate("");
				regForm.setPoaPeriod(0);

				regForm.setNameOfOfficial("");
				regForm.setDesgOfOfficial("");
				regForm.setAddressOfOfficial("");
				regForm.setAddressGovtOffclOutMp("");
				regForm.setAddressAuthPerOutMp("");
				regForm.setAddressIndOutMp("");
				regForm.setAddressOrgOutMp("");
				regForm.setAddressOwnerOutMp("");

				regForm.setFname("");
				regForm.setMname("");
				regForm.setLname("");
				regForm.setGendar("M");
				// regForm.setAuthPerGendar("M");
				regForm.setAge("");
				regForm.setFatherName("");
				regForm.setMotherName("");
				regForm.setSpouseName("");
				regForm.setNationality("");
				regForm.setIndaddress("");
				regForm.setIndcountry("");
				regForm.setIndstatename("");
				regForm.setInddistrict("");
				regForm.setPostalCode("");
				regForm.setIndphno("");
				regForm.setIndmobno("");
				regForm.setEmailID("");
				regForm.setListID("");
				regForm.setIdno("");
				regForm.setDeedType("");
				regForm.setIndCaste("");
				regForm.setIndReligion("");
				regForm.setIndDisability("N");
				// regForm.setShareOfProp("");
				regForm.setOwnershipShare("");
				regForm.setRelationWithOwner("");
				regForm.setIndDisabilityDesc("");
				regForm.setIndMinority("N");
				regForm.setIndPovertyLine("N");
				regForm.setIndCategory("");

				regForm.setIndScheduleArea("");
				regForm.setPermissionNo("");
				regForm.setCertificate(null);
				regForm.setDocumentName("");
				regForm.setDocumentSize("");
				regForm.setFilePath("");

				regForm.setOccupationId("");

				// regForm.setIndPovertyLineTrns("");

				regForm.setInstrument("");
				commonDto.setState(commonBo.getState("1", languageLocale));
				commonDto.setDistrict(commonBo.getDistrict("1", languageLocale));
				commonDto.setIndstate(commonBo.getState("1", languageLocale));
				commonDto.setInddistrict(commonBo.getDistrict("1", languageLocale));
				// commonDto.setAppType(commonBo.getAppType(languageLocale));
				commonDto.setCountry(commonBo.getCountry(languageLocale));
				commonDto.setIndcountry(commonBo.getCountry(languageLocale));
				commonDto.setIdProf(commonBo.getIdProf(languageLocale));
				commonDto.setDeedType(commonBo.getDeedType(languageLocale));
				commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
				commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendar(), languageLocale));
				commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendar(), languageLocale));
				regForm.setRelationship(0);
				// commonDto.setDistrict(commonBo.getDistrict("1"));
				regForm.setOgrName("");
				regForm.setAuthPerName("");
				regForm.setOrgaddress("");
				regForm.setCountry("");
				regForm.setStatename("");
				regForm.setDistrict("");
				regForm.setPhno("");
				regForm.setMobno("");
				regForm.setApplicantUserIdCheck(null);
				regForm.setActionName(RegInitConstant.NO_ACTION);
				// following reseting owner details
				regForm.setOwnerName("");
				regForm.setOwnerOgrName("");
				regForm.setOwnerAddress("");
				regForm.setOwnerAge("");
				regForm.setOwnerEmailID("");
				regForm.setOwnerGendar("M");
				regForm.setOwnerIdno("");
				regForm.setOwnerListID("");
				regForm.setOwnerNationality("");
				regForm.setOwnerPhno("");
				regForm.setOwnerProofName("");
				regForm.setCommonDto(commonDto);
				regForm.setCountry("1");
				regForm.setCountryName("INDIA");
				regForm.setStatename("1");
				regForm.setStatenameName("MADHYA PRADESH");
				regForm.setIndcountry("1");
				regForm.setIndcountryName("INDIA");
				regForm.setIndstatename("1");
				regForm.setIndstatenameName("MADHYA PRADESH");
				regForm.setIndcountryArb("1");
				regForm.setIndstatenameArb("1");

				regForm.setUserDay("");
				regForm.setUserMonth("");
				regForm.setUserYear("");
				regForm.setUserDOB("");

				// regForm.setDeclareShare("true");
				regForm.setHdnDeclareShareCheck("Y");

				regForm.setRent(0);
				regForm.setAdvance(0);
				regForm.setDevlpmtCharge(0);
				regForm.setOtherRecCharge(0);
				regForm.setPremium(0);
				regForm.setTermLease(0);

				regForm.setTrustName("");
				regForm.setTrustDate("");

				regForm.setWithPos("");
				regForm.setSecLoanAmt(0);
				regForm.setIndcountryArb("1");
				regForm.setIndstatenameArb("1");
				regForm.setInddistrictArb("");
				regForm.setFnameArb("");
				regForm.setMnameArb("");
				regForm.setLnameArb("");
				regForm.setGendarArb("M");
				regForm.setAgeArb("");
				regForm.setFatherNameArb("");
				regForm.setMotherNameArb("");
				regForm.setSpouseNameArb("");
				regForm.setNationalityArb("");
				regForm.setIndaddressArb("");
				regForm.setIndcountryArb("");
				regForm.setIndstatenameArb("");
				regForm.setInddistrictArb("");
				regForm.setIndphnoArb("");
				regForm.setIndmobnoArb("");
				regForm.setEmailIDArb("");
				regForm.setIndCategoryArb("");
				regForm.setIndDisabilityArb("N");
				regForm.setIndDisabilityDescArb("");
				regForm.setListIDArb("");
				regForm.setIdnoArb("");
				regForm.setAdvance(0);
				regForm.setAdvancePaymntDate("");
				regForm.setPossGiven("N");
				regForm.setPossGivenName("");

				regForm.setPoaWithConsid("");
				regForm.setPoaPeriod(0);

				regForm.setDissolutionDate("");
				regForm.setRetirmentDate("");

				regForm.setCertificate(null);
				regForm.setDocumentName("");
				regForm.setDocumentSize("");
				regForm.setFilePath("");
				regForm.setDocContents(new byte[0]);
				regForm.setPartyOrProp("");
				regForm.setUploadType("");

				regForm.setU2(null);
				regForm.setU2DocumentName("");
				regForm.setU2DocumentSize("");
				regForm.setU2FilePath("");
				regForm.setU2DocContents(new byte[0]);
				regForm.setU2PartyOrProp("");
				regForm.setU2UploadType("");

				regForm.setU3(null);
				regForm.setU3DocumentName("");
				regForm.setU3DocumentSize("");
				regForm.setU3FilePath("");
				regForm.setU3DocContents(new byte[0]);
				regForm.setU3PartyOrProp("");
				regForm.setU3UploadType("");

				regForm.setU4(null);
				regForm.setU4DocumentName("");
				regForm.setU4DocumentSize("");
				regForm.setU4FilePath("");
				regForm.setU4DocContents(new byte[0]);
				regForm.setU4PartyOrProp("");
				regForm.setU4UploadType("");

				regForm.setU5(null);
				regForm.setU5DocumentName("");
				regForm.setU5DocumentSize("");
				regForm.setU5FilePath("");
				regForm.setU5DocContents(new byte[0]);
				regForm.setU5PartyOrProp("");
				regForm.setU5UploadType("");

				regForm.setU6(null);
				regForm.setU6DocumentName("");
				regForm.setU6DocumentSize("");
				regForm.setU6FilePath("");
				regForm.setU6DocContents(new byte[0]);
				regForm.setU6PartyOrProp("");
				regForm.setU6UploadType("");

				regForm.setU7(null);
				regForm.setU7DocumentName("");
				regForm.setU7DocumentSize("");
				regForm.setU7FilePath("");
				regForm.setU7DocContents(new byte[0]);
				regForm.setU7PartyOrProp("");
				regForm.setU7UploadType("");

				regForm.setU8(null);
				regForm.setU8DocumentName("");
				regForm.setU8DocumentSize("");
				regForm.setU8FilePath("");
				regForm.setU8DocContents(new byte[0]);
				regForm.setU8PartyOrProp("");
				regForm.setU8UploadType("");

				regForm.setU9(null);
				regForm.setU9DocumentName("");
				regForm.setU9DocumentSize("");
				regForm.setU9FilePath("");
				regForm.setU9DocContents(new byte[0]);
				regForm.setU9PartyOrProp("");
				regForm.setU9UploadType("");

				regForm.setU10(null);
				regForm.setU10DocumentName("");
				regForm.setU10DocumentSize("");
				regForm.setU10FilePath("");
				regForm.setU10DocContents(new byte[0]);
				regForm.setU10PartyOrProp("");
				regForm.setU10UploadType("");

				regForm.setU11(null);
				regForm.setU11DocumentName("");
				regForm.setU11DocumentSize("");
				regForm.setU11FilePath("");
				regForm.setU11DocContents(new byte[0]);
				regForm.setU11PartyOrProp("");
				regForm.setU11UploadType("");

				regForm.setSrOfficeName("");
				regForm.setPoaRegNo("");
				regForm.setDatePoaReg("");

				regForm.setAuthPerAddress("");
				regForm.setAuthPerCountry("1");
				regForm.setAuthPerDistrict("");
				regForm.setAuthPerStatename("1");
				regForm.setAuthPerFatherName("");
				regForm.setAuthPerRelationship(0);
				regForm.setAuthPerGendar("M");

				forward = "success";

			}

			if (CommonConstant.CANCEL_ACTION.equals(actionName)) {
				regForm.setActionName("");
				forward = "welcome";
			}

			if (RegInitConstant.APPLICANT_USERID_CHECK_ACTION.equalsIgnoreCase(actionName)) {
				regForm.setActionName(RegInitConstant.NO_ACTION);
				if (regForm.getHdnapplicantUserIdCheck().equalsIgnoreCase("checked")) {

					commonBo.getApplicantRegistrationDetls(session.getAttribute("UserId").toString(), regForm, languageLocale);
					regForm.getCommonDto().setIndtehsilpoapro(new ArrayList());

					if (regForm.getInddistrict() != null) {

						ArrayList tehsilList = commonBo.getTehsilList(regForm.getInddistrict(), languageLocale);
						regForm.getCommonDto().setTehsilName("");
						regForm.getCommonDto().setIndtehsilpoapro(tehsilList);
						regForm.getCommonDto().setTehsilId("-1");

					}

					commonDto.setIndcountry(commonBo.getCountry(languageLocale));
					commonDto.setIndstate(commonBo.getState("1", languageLocale));
					commonDto.setInddistrict(commonBo.getDistrict("1", languageLocale));
					regForm.setCommonDto(commonDto);

				} else {
					regForm.setApplicantUserIdCheck(null);
					regForm.getCommonDto().setIndtehsilpoapro(new ArrayList());
					regForm.setFname("");
					regForm.setMname("");
					regForm.setLname("");
					regForm.setGendar("M");
					commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendar(), languageLocale));
					regForm.setNationality("");

					regForm.setInddistrict("");
					regForm.setIndaddress("");
					regForm.setPostalCode("");
					regForm.setIndphno("");
					regForm.setIndmobno("");
					regForm.setEmailID("");
					regForm.setListID("");
					regForm.setIdno("");
					regForm.setFatherName("");
					regForm.setMotherName("");
					regForm.setSpouseName("");
					regForm.setAge("");

					commonDto.setIdProf(commonBo.getIdProf(languageLocale));
					regForm.setCommonDto(commonDto);

					regForm.setIndstatename("1");
					regForm.setIndstatenameName(commonBo.getStateName("1", languageLocale));
					regForm.setIndcountry("1");
					regForm.setIndcountryName(commonBo.getCountryName("1", languageLocale));
					regForm.setUserDay("");
					regForm.setUserMonth("");
					regForm.setUserYear("");
					regForm.setIndCategory("");
					regForm.setOccupationId("");

				}

				forward = "success";

			}

		}

		if (request.getParameter("mapping") != null && request.getParameter("mapping").equalsIgnoreCase("Y")) {

			String mapCheck;
			if (request.getAttribute("mapCheck") != null) {
				mapCheck = (String) request.getAttribute("mapCheck");
			} else {
				mapCheck = "";
			}

			int count = commonBo.getPropertyCount(regForm.getHidnRegTxnId());
			int partyCount = commonBo.getPartyCount(regForm.getHidnRegTxnId());
			logger.debug("party count after property page  : " + partyCount);
			boolean mapInsert = false;

			if ((mapCheck != null && mapCheck.equalsIgnoreCase("ON")) || (partyCount == 1 && count > 1)) {

				mapInsert = commonBo.getAllPartiesProperties(regForm);

				logger.debug("mapping insertion status : " + mapInsert);

			}

			// CHANGE HERE FOR DISSOLUTION OF TRUST
			if (count == 1 || partyCount == 1 || regForm.getDeedID() == RegInitConstant.DEED_EXCHANGE || regForm.getDeedID() == RegInitConstant.DEED_PARTITION_NPV || (count > 1 && mapCheck.equalsIgnoreCase("ON") && mapInsert)) {

				regForm.setPropWithMapping(commonBo.getAllPartiesPropertiesAlreadyMap(regForm, languageLocale));
				regForm.setPropWithoutMapping(null);
				regForm.setAppStatus(RegInitConstant.STATUS_BACKED_MAPPING_SAVED);

				regForm.setDeclareShareSize(regForm.getPropWithMapping().size());

				forward = "alreadyMapped";
				regForm.setCheckParty("true");

				return mapping.findForward(forward);

			} else {

				regForm.setPropWithMapping(null);
				regForm.setPropWithoutMapping(commonBo.getAllPartiesPropertiesMap(regForm, languageLocale));
				regForm.setAppStatus(RegInitConstant.STATUS_PROP_SAVED);
				regForm.setDeclareShareSize(regForm.getPropWithoutMapping().size());

				forward = "getMapping";
				return mapping.findForward(forward);

			}

		}

		if (RegInitConstant.PARTY_POPUP_CLOSE_CONFIRM_ACTION.equals(actionName)) {
			commonBo.landConfirmationScreen(regForm, languageLocale, request);
			regForm.setActionName("");
			forward = "reginitConfirm";
		}
		if (RegInitConstant.REG_INIT_CONFIRM_PAGE.equals(formName)) {

			if (RegInitConstant.MODIFY_ACTION.equals(actionName)) {

				forward = "success";
			}
			if (RegInitConstant.BACK_TO_EXTRA_DISPLAY_ACTION.equals(actionName)) {

				commonBo.getExtraDetls(regForm, languageLocale);
				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());

				logger.debug("request deed ---->" + request.getAttribute("deedId"));
				regForm.setCallingAction("regInit.do?TRFS=NGI");
				regForm.setExtraDetlsModifyFlag(0);
				forward = "displayExtraDetls";

			}
			// added by Shreeraj
			if (RegInitConstant.SUBMIT_ADJU_ACTION.equals(actionName)) {

				String currDate = commonBo.getCurrDateTime();

				System.out.println("curr date 2 : " + currDate);

				regForm.setCurrDateTime(currDate);

				boolean updateTxnStatus = commonBo.insertTxnDetailsFinalAction(RegInitConstant.STATUS_COMPLETE, regForm.getHidnRegTxnId());

				logger.debug("txn status updated as 10 after complete adju payment: " + updateTxnStatus);
				if (!updateTxnStatus) {
					forward = "failure";
					return mapping.findForward(forward);
				}

				forward = "success1";
			}

			if (RegInitConstant.ADJU_COMPLETED.equals(actionName)) {
				forward = "welcome";
			}
			if (RegInitConstant.POSTAL_COUNTRY_CHANGE.equals(actionName)) {

				commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
				regForm.setPostalState("");
				regForm.setPostalDistrict("");

				forward = "success1";
			}
			if (RegInitConstant.POSTAL_STATE_CHANGE.equals(actionName)) {

				commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
				commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(), languageLocale));

				forward = "success1";
			}

			if (RegInitConstant.NEXT_TO_FEE_PAYMENT_ACTION.equals(actionName)) {

				regForm.setActionName("");

				regForm.setHidnUserId(session.getAttribute("UserId").toString());

				if (Double.parseDouble(regForm.getTotalBalanceAmountFee()) > 0) {
					request.setAttribute("parentModName", "Registration Process");
					request.setAttribute("parentFunName", "Registration Initiation");
					request.setAttribute("formName", "reginit");
					request.setAttribute("formName", "reginitefiling");
					request.setAttribute("parentFunId", "FUN_206");
					request.setAttribute("parentAmount", regForm.getTotalBalanceAmountFee());// CHANGE
					request.setAttribute("parentTable", "IGRS_REG_PAYMENT_DETLS");

					request.setAttribute("forwardPath", "./regInit.do?TRFS=NGI");
					request.setAttribute("parentColumnName", "TXN_ID");

					String userTypeId = commonBo.getUserTypeId(userId);
					String parentOfficeId = "NA";
					String parentOfficeName = "NA";
					String parentDistrictId = "NA";
					String parentDistrictName = "NA";
					String parentReferenceId = regForm.getHidnRegTxnId();

					if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_RU)) {

						parentDistrictId = commonBo.getDistIdEstamp(regForm.getHidnRegTxnId());
						parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);

					} else if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SP) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SPB) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_FI) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_PO)) {

						String[] arr = commonBo.getDistIdNameSP(userId);

						if (arr != null && arr.length == 2) {
							parentDistrictId = arr[0].trim();
							parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
						} else {
							parentDistrictId = "NA";
							parentDistrictName = "NA";
						}

					} else if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_DRS)) {

						String[] arr = commonBo.getDistIdNameOfficeNameDRS(session.getAttribute("loggedToOffice").toString());
						parentOfficeId = session.getAttribute("loggedToOffice").toString();

						if (arr != null && arr.length == 3) {

							parentDistrictId = arr[0].trim();
							parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
							parentOfficeName = commonBo.getOfficeName(parentOfficeId, languageLocale);
						} else {

							parentDistrictId = "NA";
							parentDistrictName = "NA";
							parentOfficeName = "NA";
						}

					}

					request.setAttribute("parentOfficeId", parentOfficeId);
					request.setAttribute("parentOfficeName", parentOfficeName);
					request.setAttribute("parentDistrictId", parentDistrictId);
					request.setAttribute("parentDistrictName", parentDistrictName);
					request.setAttribute("parentReferenceId", parentReferenceId);

					// end of new payment requirements added on 4th sept 2013

					String paymentType = "2";

					regForm.setPaymentType(paymentType);

					// CHECK FOR PAYMENT_STATUS BEFORE REDIRECTING.

					String[] paymentArr = commonBo.getPaymentFlag(regForm.getHidnRegTxnId(), paymentType);
					//logger.debug("----------------->payment flag:-"+paymentArr
					// [0]);
					if (paymentArr != null) {
						if (!paymentArr[0].equalsIgnoreCase("p") && !paymentArr[0].equalsIgnoreCase("c")) {

							if (paymentArr[0].equalsIgnoreCase("I")) {

								regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
								request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());

								boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
								logger.debug("----------------->payment insertion status:-" + insertStatus);
								if (insertStatus)
									forward = "reginitPayment";
								else
									forward = "failure";

							}

						} else if (paymentArr[0].equalsIgnoreCase("p")) {

							regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
							request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
							boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
							logger.debug("----------------->payment insertion status:-" + insertStatus);
							if (insertStatus)
								forward = "reginitPayment";
							else
								forward = "failure";
						} else if (paymentArr[0].equalsIgnoreCase("c")) {
							forward = "success1";
						} else {
							forward = "failure";
						}
					} else {

						regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
						request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());

						boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
						logger.debug("----------------->payment insertion status:-" + insertStatus);
						if (insertStatus)
							forward = "reginitPayment";
						else
							forward = "failure";
					}
				} else {

					actionName = RegInitConstant.FINAL_ACTION;

				}
			}
		}
		// FOLLOWING ADDED BY ROOPAM
		if (RegInitConstant.CANCEL_TRANSACTING_PART_ACTION.equals(actionName) || RegInitConstant.CANCEL_ACTION.equals(actionName) && (request.getParameter("modName") == null)) {

			utilities.cancelAction(session, regForm);
			if (map != null) {
				if (!map.isEmpty())
					map.clear();
			}

			forward = "welcome";
			return mapping.findForward(forward);
		}
		if (RegInitConstant.PARTY_PAGE_FORM.equals(formName) || RegInitConstant.TRANS_PARTY_PAGE_FORM.equals(formName)) {
			String roleType = null;
			String roleTypeTrns = null;
			if (request.getParameter("partyType") == null || request.getParameter("partyType").toString().equalsIgnoreCase("")) {
				roleType = "0";
			} else {
				roleType = (String) request.getParameter("partyType");
				regForm.setPartyType(roleType); // setting role id.
				String[] claimantArr = commonBo.getClaimantFlag(roleType);
				// int claimantFlag=Integer.parseInt(claimantArr[0]);
				regForm.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
				regForm.setPoaHolderFlag(Integer.parseInt(claimantArr[1].trim()));
				commonDto.setAppType(commonBo.getAppType(languageLocale, regForm.getPoaHolderFlag(), 0));
			}
			if (request.getParameter("partyTypeTrns") == null || request.getParameter("partyTypeTrns").toString().equalsIgnoreCase("")) {
				roleTypeTrns = "0";
			} else {
				roleTypeTrns = (String) request.getParameter("partyTypeTrns");
				regForm.setPartyTypeTrns(roleTypeTrns); // setting role id.
				String[] claimantArr = commonBo.getClaimantFlag(roleTypeTrns);
				// int claimantFlag=Integer.parseInt(claimantArr[0]);
				regForm.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
				regForm.setPoaHolderFlag(Integer.parseInt(claimantArr[1].trim()));
				commonDto.setAppTypeTrns(commonBo.getAppType(languageLocale, regForm.getPoaHolderFlag(), 0));
			}

			if ((roleType != null || RegInitConstant.NEXT_ACTION.equals(actionName)) || RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName)) {

				if ((roleType != null || RegInitConstant.NEXT_ACTION.equals(actionName)) && !(RegInitConstant.TRNS_ROLE_TYPE_ACTION.equals(actionName))) {
					request.setAttribute("roleId", Integer.parseInt(roleType));
					request.setAttribute("roleIdTrns", "0");
				}
				if (roleTypeTrns != null && (RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName) || RegInitConstant.TRNS_ROLE_TYPE_ACTION.equals(actionName)))
					request.setAttribute("roleIdTrns", Integer.parseInt(roleTypeTrns));

			}
		}

		if (RegInitConstant.NO_ACTION.equals(actionName)) {
			if (regForm.getPartyRoleId() != null && !regForm.getPartyRoleId().equalsIgnoreCase("") && !regForm.getPartyRoleId().equalsIgnoreCase("null")) {
				request.setAttribute("roleIdTrns", Integer.parseInt(regForm.getPartyRoleId()));
			} else
				request.setAttribute("roleIdTrns", 0);
		}

		if (RegInitConstant.NO_ACTION.equals(actionName)) { // forward
			// ="reginitMapping";

		} else if (RegInitConstant.NEXT_ACTION.equals(actionName) || RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName) || RegInitConstant.TRNS_ROLE_TYPE_ACTION.equals(actionName) || RegInitConstant.ADD_MORE_ACTION.equals(actionName) || RegInitConstant.DELETE_MORE_ACTION.equals(actionName))
			forward = "transactingParty";
		else if (RegInitConstant.NEXT_TO_MAPPING_ACTION.equals(actionName))
			forward = "reginitMapping";
		else if (RegInitConstant.NEXT_TO_PROPERTY_ACTION.equals(actionName) || RegInitConstant.NEXT_TO_PROPERTY_ACTION_THROUGH_MAP.equals(actionName))
			forward = "reginitProperty";
		else if (RegInitConstant.NEXT_TO_EFILE_PAYMENT_ACTION.equals(actionName)) {// forward
																					// =
																					// "reginitPayment"
																					// ;
			regForm.setActionName(RegInitConstant.NO_ACTION);
		}
		// else
		// forward ="success";

		if (RegInitConstant.RESET_MAPPING_ACTION.equals(actionName)) {
			regForm.setActionName(RegInitConstant.NO_ACTION);
			regForm.setOwnerId("");
			// session.setAttribute("regForm", regForm);
			forward = "reginitMapping";

		}
		if (RegInitConstant.RESET_PROPERTY_ACTION.equals(actionName)) {
			regForm.setActionName(RegInitConstant.NO_ACTION);
			// PropertyValuationDTO dto= new PropertyValuationDTO();
			RegCompletDTO comDto = new RegCompletDTO();
			regForm.setRegcompletDto(comDto);
			// regForm.setPropertyDTO(dto);

			// session.setAttribute("regForm", regForm);
			forward = "reginitProperty";

		}

		String label = null;
		label = (String) request.getParameter("label");
		if (label != null && label != "") {
			if (label.equalsIgnoreCase("display")) {

				RegCommonDTO mapDtoDisp = new RegCommonDTO();
				String key = request.getParameter("key");
				request.setAttribute("displayOnwerPartyKey", key);
				map2 = regForm.getMapTransactingPartiesDisp();

				if (!map2.isEmpty())
					map2.clear();

				mapDtoDisp = (RegCommonDTO) regForm.getMapTransactingParties().get(key);
				map2.put(key, mapDtoDisp);

				regForm.setMapTransactingPartiesDisp(map2);
				String confirmationFlag = null;
				confirmationFlag = (String) request.getParameter("confirmationFlag");
				if (confirmationFlag != null && confirmationFlag != "") {
					if (confirmationFlag.equalsIgnoreCase("true")) {
						regForm.setConfirmationFlag("01");
					}
				} else {
					regForm.setConfirmationFlag("00");
				}
				request.setAttribute("deedId", regForm.getDeedId());
				request.setAttribute("instId", regForm.getInstID());

				if (mapDtoDisp.getPartyTypeTrns() != null && !mapDtoDisp.getPartyTypeTrns().equalsIgnoreCase("") && !mapDtoDisp.getPartyTypeTrns().equalsIgnoreCase("null")) {
					request.setAttribute("roleId", mapDtoDisp.getPartyTypeTrns());
				} else {
					request.setAttribute("roleId", 0);
				}
				forward = "displayRegDetls";
			}

			if (label.equalsIgnoreCase("displayApp")) {
				forward = "success";
			}
			if (label.equalsIgnoreCase("displayTrns")) {
				forward = "transactingParty";
			}
			if (label.equalsIgnoreCase("displayPartyEdit")) {
				forward = "displayRegDetls";
			}
			if (label.equalsIgnoreCase("displayOwner")) {
				// open owner view here
				String key = request.getParameter("key");
				String reqAtt = request.getParameter("displayOnwerPartyKey");
				if (reqAtt != null) {
					commonBo.getOwnerDetailsFromHashmap(key, regForm, languageLocale, null, reqAtt);
				} else {
					commonBo.getOwnerDetails(key, regForm, languageLocale);
				}
				regForm.setActionName("");
				if (regForm.getPartyModifyFlag() == 1) {
					regForm.setOwnerModifyFlag(1);
				} else {
					regForm.setOwnerModifyFlag(0);
				}

				if (regForm.getOwnerModifyFlag() == 1) {

					if (regForm.getOwnerOgrName().equals("-")) {
						regForm.setOwnerOgrName("");
					}
					if (regForm.getOwnerEmailID().equals("-")) {
						regForm.setOwnerEmailID("");
					}
					if (regForm.getOwnerIdno().equals("-")) {
						regForm.setOwnerIdno("");
					}
					if (regForm.getAddressOwnerOutMp().equals("-")) {
						regForm.setAddressOwnerOutMp("");
					}

					commonDto.setIdProf(commonBo.getIdProf(languageLocale));

				}

				forward = "displayOwner";
			}
			if (label.equalsIgnoreCase("displayOwnerAppLive")) {
				// open owner view here
				String key = request.getParameter("key");

				commonBo.getOwnerDetailsFromHashmap(key, regForm, languageLocale, "app", null);
				regForm.setActionName("");

				forward = "displayOwner";
			}
			if (label.equalsIgnoreCase("displayOwnerTrnsLive")) {
				// open owner view here
				String key = request.getParameter("key");

				commonBo.getOwnerDetailsFromHashmap(key, regForm, languageLocale, "Trns", null);
				regForm.setActionName("");

				forward = "displayOwner";
			}
		}

		// ok action
		if (RegInitConstant.OK_ACTION.equals(actionName)) {

			request.setAttribute("roleId", regForm.getPartyType());
			if (!regForm.getPartyTypeTrns().equals(null) && !regForm.getPartyTypeTrns().equalsIgnoreCase(""))
				request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
			else
				request.setAttribute("roleIdTrns", "0");
			regForm.setActionName(RegInitConstant.NO_ACTION);
			forward = "transactingParty";

		}
		// confirmation ok action
		if (RegInitConstant.CONFIRMATION_OK_ACTION.equals(actionName)) {

			regForm.setPartyModifyFlag(0);
			regForm.setExtraDetlsModifyFlag(0);

			String[] deedInstArray = commonBo.getDeedInstId(regForm.getHidnRegTxnId());
			if (deedInstArray != null && deedInstArray.length > 0) {

				request.setAttribute("deedId", deedInstArray[0].trim());
				request.setAttribute("instId", deedInstArray[1].trim());
				regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
				regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));

			} else {
				request.setAttribute("deedId", 0);
				request.setAttribute("instId", 0);
				regForm.setDeedID(0);
				regForm.setInstID(0);
			}

			regForm.setActionName(RegInitConstant.NO_ACTION);
			forward = "reginitConfirm";

		}
		if (RegInitConstant.BACK_TO_REG_DISPLAY_ACTION.equals(actionName)) {

			commonBo.openPartyView(request, regForm, languageLocale);
			regForm.setPartyModifyFlag(0);
			regForm.setOwnerModifyFlag(0);
			commonDto.setIdProfTrns(commonBo.getIdProf(languageLocale));
			commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
			commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
			commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));
			regForm.setActionName(RegInitConstant.NO_ACTION);
			commonDto.setDistrictTrns(commonBo.getDistrict("1", languageLocale));
			commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));

			forward = "displayRegDetls";
		}
		if (request.getParameter("paymentStatus") != null && request.getAttribute("eCode") == null) {

			if (regForm.getFromAdjudication() == 1) {
				// session.setAttribute("fnName","Adjudication");
				if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_ENGLISH);
				} else {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_HINDI);
				}

			} else {
				// session.setAttribute("fnName","Initiation");
				if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_ENGLISH);
				} else {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_HINDI);
				}
			}

			// changes by preeti to update payment by challan
			if (request.getParameter("paymentStatus").equalsIgnoreCase("P")) {
				// set p to c
				String TempIdPo1 = (String) session.getAttribute("TempIdPo");
				String parentKey = (String) session.getAttribute("parentKey");
				String param1[] = {RegInitConstant.PAYMENT_FLAG_COMPLETED, TempIdPo1};
				boolean updatePaymentStatus = commonBo.updatePaymentStatusEfile(param1);

				boolean updateEfileTable = commonBo.updateEfileTable(TempIdPo1);

				logger.debug("payment status updated as c: " + updatePaymentStatus);
				if (updatePaymentStatus) {
					forward = "conformpayment";
					return mapping.findForward(forward);
				} else {
					forward = "conformpayment";
					return mapping.findForward(forward);
				}
			}

			// end

			NumberFormat obj = new DecimalFormat("#0.00");
			double paidAmount = 0;
		}

		if (RegInitConstant.UPLOAD_CONSENTER.equalsIgnoreCase(actionName)) {

			regForm.setIsDashboardFlag(3);
			regForm.setConsUploadFlag("1");// uploads are present
			regForm.setConsenterUploadPage(1);
			regForm.setConsBrowseFlag("Y");
			regForm.setActionName("");
			regForm.setConsenterUploadList(new ArrayList());
			forward = "reginitConfirm";
		}

		if (RegInitConstant.UPLOAD_CONSENTER_DOC.equalsIgnoreCase(actionName)) {
			FormFile uploadFile = regForm.getConsenterUploadPath();
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			boolean ret = false;
			String path = pr.getValue("upload.location");
			System.out.println(regForm.getIndexCons());
			String filePath = path + RegInitConstant.FILE_UPLOAD_PATH + regForm.getHidnRegTxnId() + RegInitConstant.FILE_UPLOAD_PATH_DEED_DRAFT + "/" + RegInitConstant.CONSENTER_PAGE + regForm.getConsenterUploadPage() + ".pdf";

			String ext = utilities.getFileExtension((String) uploadFile.getFileName());
			if (uploadFile != null) {
				if ("jpg".equalsIgnoreCase(ext) || "jpeg".equalsIgnoreCase(ext) || "tiff".equalsIgnoreCase(ext) || "bmp".equalsIgnoreCase(ext)) {
					ArrayList imageList = new ArrayList();
					imageList.add(uploadFile.getFileData());
					ret = commonBo.generatePDF(filePath, imageList);
					if (ret) {

						regForm.setUpOrNot("1");
					} else {
						regForm.setUpOrNot("0");
					}
				} else {
					regForm.setFinalPage("N");
					if (languageLocale.equalsIgnoreCase("en"))
						request.setAttribute(RegInitConstant.FAILURE_MSG, ext + " file cannot be uploaded. Kindly upload JPEG file");
					else
						request.setAttribute(RegInitConstant.FAILURE_MSG, "फ़ाइल अपलोड नहीं की जा सकती कृपया JPEG फ़ाइल अपलोड करें");

				}

			}
			if (ret) {
				logger.debug("Uploaded successfully");

				regForm.setConsUploadSucc("Y");
				if (regForm.getConsDeleteFlag() != null && regForm.getConsDeleteFlag().equalsIgnoreCase("Y")) {

					ArrayList list = regForm.getConsenterUploadList();
					RegCommonForm regF = new RegCommonForm();
					regF.setConsenterPages(regForm.getConsenterUploadPage());
					list.add(regF);
					regForm.setConsenterUploadList(list);

					if (regForm.getConsenterNoPages() == list.size()) {
						regForm.setConsBrowseFlag("N");
						regForm.setMergeSuccess("Y");
					}
					if (list.size() == 0) {
						regForm.setConsenterUploadPage(1);
					} else {
						// regForm.setConsenterUploadPage(regForm.
						// getConsenterUploadPage()+1);
						for (int i = 1; i <= regForm.getConsenterNoPages(); i++) {
							boolean flag = false;
							for (int j = 0; j < list.size(); j++) {
								RegCommonForm rForm = (RegCommonForm) list.get(j);
								int pageNo = rForm.getConsenterPages();
								if (i == pageNo)
									flag = true;
							}
							if (flag == false) {
								regForm.setConsenterUploadPage(i);
								break;
							}
						}
					}
				} else {
					regForm.setConsenterUploadPage(regForm.getConsenterUploadPage() + 1);
					ArrayList uploadList = new ArrayList();
					for (int i = 1; i < regForm.getConsenterUploadPage(); i++) {
						RegCommonForm regF = new RegCommonForm();
						regF.setConsenterPages(i);
						uploadList.add(regF);

						if (regForm.getConsenterNoPages() + 1 == regForm.getConsenterUploadPage()) {
							regForm.setConsBrowseFlag("N");
							regForm.setMergeSuccess("Y");
						}
					}
					regForm.setConsenterUploadList(uploadList);
				}

			} else {

				if (languageLocale.equalsIgnoreCase("en"))
					request.setAttribute(RegInitConstant.FAILURE_MSG, "The document could not be uploaded.Kindly upload JPEG file");
				else
					request.setAttribute(RegInitConstant.FAILURE_MSG, "दस्तावेज अपलोड नहीं किया जा सका। कृपया JPEG फ़ाइल अपलोड करें");
				regForm.setUpOrNot("0");
				logger.debug("Upload Failure PAGE NUMBER:::::" + regForm.getConsenterUploadPage());
			}
			regForm.setActionName("");
			forward = "reginitConfirm";
		}

		if (RegInitConstant.DELETE_CONSENTER_DOC.equalsIgnoreCase(actionName)) {
			String deletePageNo = regForm.getIndexCons();
			ArrayList arr = regForm.getConsenterUploadList();
			for (int i = 0; i < arr.size(); i++) {
				RegCommonForm rForm = (RegCommonForm) arr.get(i);
				if (rForm.getConsenterPages() == Integer.parseInt(deletePageNo))
					arr.remove(i);
			}
			regForm.setConsenterUploadList(arr);
			regForm.setConsBrowseFlag("Y");
			regForm.setConsDeleteFlag("Y");
			regForm.setMergeSuccess("");
			ArrayList list = regForm.getConsenterUploadList();
			for (int i = 1; i <= regForm.getConsenterNoPages(); i++) {
				boolean flag = false;

				for (int j = 0; j < list.size(); j++) {
					RegCommonForm rForm = (RegCommonForm) list.get(j);
					int pageNo = rForm.getConsenterPages();
					if (i == pageNo)
						flag = true;
				}
				if (flag == false) {
					regForm.setConsenterUploadPage(i);
					break;
				}
			}
			regForm.setActionName("");
			if (arr.size() == 0) {
				regForm.setConsenterUploadPage(1);
			}
			regForm.setActionName("");
			forward = "reginitConfirm";
		}

		if (RegInitConstant.ADD_UPLOADS_AGAIN.equalsIgnoreCase(actionName)) {
			regForm.setConsenterUploadList(new ArrayList());
			int noOfPages = regForm.getConsenterNoPages();
			regForm.setConsUploadFlag("1");// uploads are present
			regForm.setConsBrowseFlag("Y");
			regForm.setMergeSuccess("N");
			regForm.setFinalPage("N");
			regForm.setConsenterUploadPage(1);
			regForm.setActionName("");
			forward = "reginitConfirm";
		}

		String viewUpload = (String) request.getParameter("fwdName");
		if (viewUpload != null && "viewUpload".equalsIgnoreCase(viewUpload)) {
			try {
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

				String path = pr.getValue("upload.location");
				String deedName = pr.getValue("reg.deedDraftFileName");
				String filename = path + RegInitConstant.FILE_UPLOAD_PATH + "/" + regForm.getHidnRegTxnId() + "/" + RegInitConstant.FILE_UPLOAD_PATH_DEED_DRAFT + "/" + deedName;

				byte[] arr = DMSUtility.getDocumentBytes(filename);
				DMSUtility.downloadDocument(response, filename, arr);

			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
			forward = "reginitConfirm";
		}

		if (viewUpload != null && "viewUploadCons".equalsIgnoreCase(viewUpload)) {
			try {
				String pageConsNo = (String) request.getParameter("PageConsNo");

				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

				String path = pr.getValue("upload.location");
				String deedName = pr.getValue("reg.deedDraftFileName");
				String filepath = path + RegInitConstant.FILE_UPLOAD_PATH + regForm.getHidnRegTxnId() + RegInitConstant.FILE_UPLOAD_PATH_DEED_DRAFT + "/" + RegInitConstant.CONSENTER_PAGE + pageConsNo + ".pdf";

				byte[] arr = DMSUtility.getDocumentBytes(filepath);
				DMSUtility.downloadDocument(response, RegInitConstant.CONSENTER_PAGE + pageConsNo + ".pdf", arr);

			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
			forward = "reginitConfirm";
		}

		// final action after payment.
		if (RegInitConstant.FINAL_ACTION.equals(actionName)) {

			try {
				commonDto.setIndcountry(commonBo.getCountry(languageLocale));
				commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
				commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(), languageLocale));
				regForm.setPostalCountry("1");
				regForm.setPostalState("");
				regForm.setPostalDistrict("");

				regForm.setHdnPostalAddress1(regForm.getPostalAddress1());

				// change payment flag to c here.

				if (regForm.getPurpose() != null && !regForm.getPurpose().equalsIgnoreCase("")) {
					boolean updatePurpose = commonBo.updateEStampPurpose(regForm.getHidnRegTxnId(), regForm.getPurpose());
					if (updatePurpose) {
						logger.debug("purpose updated");
						// forward="reginitPayment";
					} else {
						logger.debug("purpose not updated");
						// forward="failure";
					}
				}

				String currDate = commonBo.getCurrDateTime();

				logger.debug("curr date 2 : " + currDate);

				regForm.setCurrDateTime(currDate);

				String msg = "";
				// added by Anuj for ref_gee_pay_check checkbox

				String regFeeCheck = (String) regForm.getCheckRegFeeBox();
				if ("on".equalsIgnoreCase(regFeeCheck)) {
					regFeeCheck = "Y";
				} else {
					regFeeCheck = "N";
				}
				if (Double.parseDouble(regForm.getTotalduty()) > 0) {

					String param1[] = {RegInitConstant.PAYMENT_FLAG_COMPLETED, regForm.getHidnRegTxnId()};
					boolean updatePaymentStatus = commonBo.insertTxnDetails(param1, regFeeCheck);

					logger.debug("payment status updated as c: " + updatePaymentStatus);

					if (updatePaymentStatus)
						msg = regForm.getHidnRegTxnId().toString();

					else
						msg = RegInitConstant.ERROR_MSG;
				} else {

					boolean updatePaymentStatus = commonBo.insertTxnDetailsFinalActionWithRegFee(RegInitConstant.STATUS_FINAL_SCREEN, regForm.getHidnRegTxnId(), regFeeCheck);

					logger.debug("payment status updated as c: " + updatePaymentStatus);

					if (updatePaymentStatus)
						msg = regForm.getHidnRegTxnId().toString();

					else
						msg = RegInitConstant.ERROR_MSG;

					// msg=regForm.getHidnRegTxnId().toString();
				}

				regForm.setActionName(RegInitConstant.NO_ACTION);
				request.setAttribute("regInitTxnId", regForm.getHidnRegTxnId());

				if ("Y".equalsIgnoreCase(regFeeCheck)) {
					RegCommonBO bo = new RegCommonBO();
					bo.getPaymentAmounts(regForm);
					forward = "regFeePage";
				} else {
					forward = "success1";
				}

			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
				forward = "failure";

			}

		}
		// final action after payment.
		if (RegInitConstant.FINAL_ACTION_FEE.equals(actionName)) {

			commonDto.setIndcountry(commonBo.getCountry(languageLocale));
			commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
			commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(), languageLocale));
			regForm.setPostalCountry("1");
			regForm.setPostalState("");
			regForm.setPostalDistrict("");
			regForm.setHdnPostalAddress1(regForm.getPostalAddress1());

			// change payment flag to c here.

			String currDate = commonBo.getCurrDateTime();

			System.out.println("curr date 2 : " + currDate);

			regForm.setCurrDateTime(currDate);

			String msg = "";

			String param1[] = {RegInitConstant.PAYMENT_FLAG_COMPLETED, regForm.getHidnRegTxnId()};
			boolean updatePaymentStatus = commonBo.insertTxnDetailsFee(param1);

			logger.debug("fee payment status updated as c: " + updatePaymentStatus);

			if (updatePaymentStatus)
				msg = regForm.getHidnRegTxnId().toString();

			else
				msg = RegInitConstant.ERROR_MSG;

			regForm.setActionName(RegInitConstant.NO_ACTION);
			request.setAttribute("regInitTxnId", regForm.getHidnRegTxnId());
			forward = "success1";

		}
		// COMPLETE TRANSACTION
		if (RegInitConstant.COMPLETE_APPLICATION_ACTION.equals(actionName)) {

			try {
				ArrayList list = commonBo.getAllUploadsCountAndSize(regForm.getHidnRegTxnId());

				boolean completeTransaction = commonBo.updateTransactionStatus(regForm, ((Integer) list.get(0)).toString(), ((Double) list.get(1)).toString());
				logger.debug("complete transactiooooon status: " + completeTransaction);
				if (completeTransaction) {

					utilities.cancelAction(session, regForm);
					if (map != null) {
						if (!map.isEmpty())
							map.clear();
					}

					forward = "welcome";

				} else
					forward = "failure";

			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
				forward = "failure";
			}

		}

		if (fwdPage != null && "PropertySR".equalsIgnoreCase(fwdPage)) {
			regForm.setPropImage2DocumentName("");
			regForm.setPropImage5DocumentName("");

			String TempPropId = (String) session.getAttribute("EfileTempId");
			String propId = request.getParameter("key");

			regForm.setMapPropertyTransPartyDisp(commonBo.getPropertyDetsForViewConfirm(TempPropId, propId, languageLocale));
			String status = commonBo.getstautsSR(TempPropId);

			if (status.equalsIgnoreCase("Approved")) {
				regForm.setStatusFlagSR("Y");
			}

			else if (status.equalsIgnoreCase("Reject")) {
				regForm.setStatusFlagSR("Y");
			}

			else if (status.equalsIgnoreCase("Refer To DR")) {
				regForm.setStatusFlagSR("Y");
			}

			else {
				regForm.setStatusFlagSR("");
			}
			regForm.setModifyFlagSR("Y");
			forward = "propertyView";
			return mapping.findForward(forward);

		}

		if (fwdPage != null && "PropertyPO".equalsIgnoreCase(fwdPage)) {
			String TempPropId = (String) session.getAttribute("TempIdPo");
			// String EfileTempId = commonBo.getEfileTempId1(TempPropId);
			String EfileTempId = request.getParameter("role");
			String propId = request.getParameter("key");
			regForm.setMapPropertyTransPartyDisp(commonBo.getPropertyDetsForViewConfirm(EfileTempId, propId, languageLocale));

			regForm.setModifyFlagSR("Y");
			forward = "propertyView";
			return mapping.findForward(forward);
		}

		// end

		// for creating pending search Dashboard SR

		if (fwdPage != null && "Pending".equalsIgnoreCase(fwdPage)) {
			String officeId = (String) session.getAttribute("loggedToOffice");
			ArrayList pendingSearchList = commonBo.getPendingEfile(session.getAttribute("UserId").toString(), officeId);

			if (pendingSearchList.size() == 0)
				regForm.setPendingSearchList(null);
			else
				regForm.setPendingSearchList(pendingSearchList);

			request.setAttribute("pendingSearchList", regForm.getPendingSearchList());
			forward = "pendingNewSearchDashboard";
			return mapping.findForward(forward);

		}
		// end of pending search

		// for creating pending search Dashboard PO

		if (fwdPage != null && "PendingPO".equalsIgnoreCase(fwdPage)) {

			ArrayList pendingSearchListPo = commonBo.getPendingEfilePO(session.getAttribute("UserId").toString());

			if (pendingSearchListPo.size() == 0)
				regForm.setPendingSearchListPo(null);
			else
				regForm.setPendingSearchListPo(pendingSearchListPo);

			request.setAttribute("pendingSearchListPo", regForm.getPendingSearchListPo());
			forward = "pendingNewSearchDashboardPO";
			return mapping.findForward(forward);

		}
		// end of pending search

		// for creating submit search Dashboard SR

		if (fwdPage != null && "Submit".equalsIgnoreCase(fwdPage)) {
			String officeId = (String) session.getAttribute("loggedToOffice");
			ArrayList submitSearchList = commonBo.getSubmitEfile(session.getAttribute("UserId").toString(), officeId);

			if (submitSearchList.size() == 0)
				regForm.setSubmitSearchList(null);
			else
				regForm.setSubmitSearchList(submitSearchList);

			request.setAttribute("submitSearchList", regForm.getSubmitSearchList());
			forward = "submitNewSearchDashboard";
			return mapping.findForward(forward);

		}
		// end of submit search

		// for creating reject search Dashboard SR

		if (fwdPage != null && "Reject".equalsIgnoreCase(fwdPage)) {
			String officeId = (String) session.getAttribute("loggedToOffice");
			ArrayList rejectSearchList = commonBo.getRejectEfile(session.getAttribute("UserId").toString(), officeId);

			if (rejectSearchList.size() == 0)
				regForm.setRejectSearchList(null);
			else
				regForm.setRejectSearchList(rejectSearchList);

			request.setAttribute("rejectSearchList", regForm.getRejectSearchList());
			forward = "rejectNewSearchDashboard";

		}
		// end of reject search

		// for creating reject search Dashboard PO

		if (fwdPage != null && "RejectPO".equalsIgnoreCase(fwdPage)) {

			ArrayList rejectSearchListPo = commonBo.getRejectEfilePO(session.getAttribute("UserId").toString());

			if (rejectSearchListPo.size() == 0)
				regForm.setRejectSearchListPo(null);
			else
				regForm.setRejectSearchListPo(rejectSearchListPo);

			request.setAttribute("rejectSearchListPo", regForm.getRejectSearchListPo());
			forward = "rejectNewSearchDashboardPo";
			return mapping.findForward(forward);

		}
		// end of reject search

		// for duration sr search
		if (RegInitConstant.DURATION_SR_PAGE.equals(actionName)) {
			String durationFrom = regForm.getDurationFrom();
			String durationTo = regForm.getDurationTo();
			String officeId = (String) session.getAttribute("loggedToOffice");
			ArrayList durationSearchList = commonBo.getDurationEfile(durationFrom, durationTo, session.getAttribute("UserId").toString(), officeId);
			if (durationSearchList.size() == 0)
				regForm.setDurationSearchList(null);
			else
				regForm.setDurationSearchList(durationSearchList);

			request.setAttribute("durationSearchList", regForm.getDurationSearchList());
			forward = "durationNewSearchDashboard";
		}

		// end

		// for duration po search
		if (RegInitConstant.DURATION_PO_PAGE.equals(actionName)) {
			String durationFrom = regForm.getDurationFrom();
			String durationTo = regForm.getDurationTo();

			ArrayList durationSearchListPo = commonBo.getDurationEfilePo(durationFrom, durationTo, session.getAttribute("UserId").toString());
			if (durationSearchListPo.size() == 0)
				regForm.setDurationSearchListPo(null);
			else
				regForm.setDurationSearchListPo(durationSearchListPo);

			request.setAttribute("durationSearchListPo", regForm.getDurationSearchListPo());
			forward = "durationNewSearchDashboardPo";
		}

		// end

		// after click on any pending application id from dashboard
		if (request.getParameter("hdnApplicationId") != null) {
			ArrayList exemptionEfile = new ArrayList();
			boolean checkMode = false;
			boolean checkMode1 = false;
			regForm.setExtraflag("");
			regForm.setExtraflag1("");
			regForm.setExempflag("");
			regForm.setExempflag4("");
			regForm.setDisplaypropoutMPFlag1("");
			regForm.setPhysicalStampFlag("");
			regForm.setUploadflag("");
			try {

				String TempId = request.getParameter("hdnApplicationId");
				session.setAttribute("TempId", TempId);

				// below code to get duty txn id from table

				String EfileTempId = commonBo.getEfileTempId(TempId);
				session.setAttribute("EfileTempId", EfileTempId);
				String TempDutyId = commonBo.getTempDutyId(EfileTempId);
				String instId = commonBo.getinstId(TempDutyId);
				regForm.setPartysr(instId);
				session.setAttribute("TempDutyId", TempDutyId);

				if (instId.equalsIgnoreCase("2012") || instId.equalsIgnoreCase("2016")) {
					ArrayList getInstExtraList = commonBo.getInstExtraList(EfileTempId);
					if (getInstExtraList.size() > 0) {
						ArrayList rowList;
						for (int i = 0; i < getInstExtraList.size(); i++) {
							rowList = (ArrayList) getInstExtraList.get(i);
							regForm.setBankdepositedeed(rowList.get(0).toString());
							regForm.setBranchdepositdeed(rowList.get(1).toString());
							regForm.setBankaddressdeed(rowList.get(2).toString());
							regForm.setBankauthdeed(rowList.get(3).toString());
							regForm.setLoanamt(rowList.get(4).toString());
							regForm.setSanamt(rowList.get(5).toString());
							regForm.setExtraflag("Y");
						}
					}
				}
				if (instId.equalsIgnoreCase("2104") || instId.equalsIgnoreCase("2054") || instId.equalsIgnoreCase("2055")) {
					ArrayList getInstExtraList1 = commonBo.getInstExtraList1(EfileTempId);
					if (getInstExtraList1.size() > 0) {
						ArrayList rowList;
						for (int i = 0; i < getInstExtraList1.size(); i++) {
							rowList = (ArrayList) getInstExtraList1.get(i);
							regForm.setWithpossession(rowList.get(0).toString());
							regForm.setSecuredamt(rowList.get(1).toString());
							regForm.setExtraflag1("Y");
						}
					}
				}

				checkMode = commonBo.getCheckModeDetails(EfileTempId);
				checkMode1 = commonBo.getCheckModeDetails1(EfileTempId);
				// below code used if there is any exemption selected while
				// initaing application at PO End
				boolean exemptionModeEfile = false;
				exemptionModeEfile = commonBo.getCheckExemptionDetails(TempDutyId);

				if (exemptionModeEfile) {

					ArrayList exemption = commonBo.getExemptionListEfile(TempDutyId);
					if (exemption.size() > 0) {
						regForm.setExemptionListEfileSelected(exemption);

					}
					if (exemption.size() > 0) {
						regForm.setExempflag("Y");
					} else {
						regForm.setExempflag("");
					}

					// below for efiling exemption
					// for efiling exemption
					exemptionEfile = commonBo.getExemptionListEfile4(TempDutyId);

					if (exemptionEfile.size() > 0) {
						regForm.setExemptionListEfileSelected4(exemptionEfile);

					}

					if (exemptionEfile.size() > 0) {
						regForm.setExempflag4("Y");
					} else {
						regForm.setExempflag4("");
					}

				}

				if (!checkMode && !checkMode1) {
					ArrayList getDuty = commonBo.getDutyEfileNonPayment(TempDutyId, EfileTempId);

					if (getDuty.size() > 0) {
						ArrayList rowList;
						for (int i = 0; i < getDuty.size(); i++) {
							rowList = (ArrayList) getDuty.get(i);
							if (languageLocale.equalsIgnoreCase("en")) {
								regForm.setDeedNameEfile(rowList.get(0).toString());
							} else {
								regForm.setDeedNameEfile(rowList.get(12).toString());
							}
							if (languageLocale.equalsIgnoreCase("en")) {
								regForm.setInstNameEfile(rowList.get(1).toString());
							} else {
								regForm.setInstNameEfile(rowList.get(13).toString());
							}
							//regForm.setDeedNameEfile(rowList.get(0).toString()
							// );
							//regForm.setInstNameEfile(rowList.get(1).toString()
							// );

							regForm.setStampDutyEfile(rowList.get(2).toString());
							regForm.setMunDUTYEfile(rowList.get(3).toString());
							regForm.setBlockDutyEfile(rowList.get(4).toString());
							regForm.setUpkarDutyEfile(rowList.get(5).toString());
							if (exemptionEfile.size() > 0) {
								regForm.setRegfeeEfile("0");
							} else {
								regForm.setRegfeeEfile(rowList.get(6).toString());
							}
							//regForm.setRegfeeEfile(rowList.get(6).toString());
							regForm.setDutypaidEfile(rowList.get(7).toString());
							regForm.setTotalpayableduty(rowList.get(8).toString());
							regForm.setTotalAfterexmpEfile(rowList.get(9).toString());
							if (rowList.get(10) != null) {
								regForm.setDisplaypurploan(rowList.get(10).toString());
							} else {
								regForm.setDisplaypurploan("-");
							}

							if (rowList.get(11) != null) {
								regForm.setDisplaypropoutMP1(rowList.get(11).toString());
								regForm.setDisplaypropoutMPFlag1("Y");
							}
							forward = "srdispalyduty";

						}
					}
				}

				else if (checkMode) {

					if (TempDutyId != null) {
						ArrayList getDuty = commonBo.getDutyEfile(TempDutyId, EfileTempId);

						if (getDuty.size() > 0) {
							System.out.println("inside getduty of action");
							ArrayList rowList;
							for (int i = 0; i < getDuty.size(); i++) {
								rowList = (ArrayList) getDuty.get(i);
								if (languageLocale.equalsIgnoreCase("en")) {
									regForm.setDeedNameEfile(rowList.get(0).toString());
								} else {
									regForm.setDeedNameEfile(rowList.get(14).toString());
								}
								if (languageLocale.equalsIgnoreCase("en")) {
									regForm.setInstNameEfile(rowList.get(1).toString());
								} else {
									regForm.setInstNameEfile(rowList.get(15).toString());
								}
								// regForm.setDeedNameEfile(rowList.get(0)
								// .toString());
								// regForm.setInstNameEfile(rowList.get(1)
								// .toString());
								regForm.setStampDutyEfile(rowList.get(2).toString());
								regForm.setMunDUTYEfile(rowList.get(3).toString());
								regForm.setBlockDutyEfile(rowList.get(4).toString());
								regForm.setUpkarDutyEfile(rowList.get(5).toString());
								if (exemptionEfile.size() > 0) {
									regForm.setRegfeeEfile("0");
								} else {
									regForm.setRegfeeEfile(rowList.get(6).toString());
								}
								// regForm.setRegfeeEfile(rowList.get(6)
								// .toString());
								regForm.setDutypaidEfile(rowList.get(7).toString());
								regForm.setTotalpayableduty(rowList.get(8).toString());
								regForm.setTotalAfterexmpEfile(rowList.get(9).toString());
								regForm.setEstampCodeDisplay(rowList.get(10).toString());
								regForm.setEstampCodeStatus(rowList.get(11).toString());
								regForm.setDisplaypurploan(rowList.get(12).toString());
								regForm.setPhysicalStampFlag1("Y");

								if (rowList.get(13) != null) {
									regForm.setDisplaypropoutMP1(rowList.get(13).toString());
									regForm.setDisplaypropoutMPFlag1("Y");
								}

							}

						}
						forward = "srdispalyduty";

					}
				}

				else {

					if (TempDutyId != null) {
						ArrayList getDuty = commonBo.getDutyPhyEfile(TempDutyId, EfileTempId);

						if (getDuty.size() > 0) {
							System.out.println("inside getduty of action");
							ArrayList rowList;
							for (int i = 0; i < getDuty.size(); i++) {
								rowList = (ArrayList) getDuty.get(i);
								if (languageLocale.equalsIgnoreCase("en")) {
									regForm.setDeedNameEfile(rowList.get(0).toString());
								} else {
									regForm.setDeedNameEfile(rowList.get(16).toString());
								}
								if (languageLocale.equalsIgnoreCase("en")) {
									regForm.setInstNameEfile(rowList.get(1).toString());
								} else {
									regForm.setInstNameEfile(rowList.get(17).toString());
								}
								// regForm.setDeedNameEfile(rowList.get(0).
								// toString());
								// regForm.setInstNameEfile(rowList.get(1).
								// toString());
								regForm.setStampDutyEfile(rowList.get(2).toString());
								regForm.setMunDUTYEfile(rowList.get(3).toString());
								regForm.setBlockDutyEfile(rowList.get(4).toString());
								regForm.setUpkarDutyEfile(rowList.get(5).toString());
								if (exemptionEfile.size() > 0) {
									regForm.setRegfeeEfile("0");
								} else {
									regForm.setRegfeeEfile(rowList.get(6).toString());
								}
								// regForm.setRegfeeEfile("500");
								regForm.setDutypaidEfile(rowList.get(7).toString());
								regForm.setTotalpayableduty(rowList.get(8).toString());
								regForm.setTotalAfterexmpEfile(rowList.get(9).toString());
								regForm.setDisplaypurploan(rowList.get(10).toString());
								if (rowList.get(11) != null) {
									regForm.setDisplaypropoutMP1(rowList.get(11).toString());
									regForm.setDisplaypropoutMPFlag1("Y");
								}
								ArrayList physicalList = commonBo.getphysicalList(EfileTempId, regForm);
								if (physicalList.size() > 0) {
									regForm.setPhyListEfileSelected(physicalList);

								}
								regForm.setPhysicalStampFlag("Y");

							}

						}
						forward = "srdispalyduty";

					}
				}

			} catch (Exception e) {

				logger.debug(e);
				logger.debug(e.getMessage(), e);
				forward = "failure";
				return mapping.findForward(forward);

			}

			// to check for additional upload
			if (checkMode) {
				String dutyid = (String) session.getAttribute("TempDutyId");
				String uploadpath = commonBo.getuploadpath(dutyid);
				if (!uploadpath.isEmpty()) {
					regForm.setUploadpath(uploadpath);
					regForm.setUploadflag("Y");
					String view = "View";
					regForm.setCommonView(view);
				}
			} else {
				String dutyid = (String) session.getAttribute("TempDutyId");
				String uploadpath1 = commonBo.getuploadpath1(dutyid);
				if (!uploadpath1.isEmpty()) {
					regForm.setUploadpath(uploadpath1);
					regForm.setUploadflag("Y");
					String view = "View";
					regForm.setCommonView(view);
				}
			}

			// below code for form upload file
			String TempIdPo = (String) session.getAttribute("EfileTempId");
			String EfileTempId = commonBo.getEfileTempId(TempIdPo);
			String uploadPathSR = commonBo.getuploadPathSR(TempIdPo);
			regForm.setUploadPathSR(uploadPathSR);
			// Added By gulrej on 12th may 2017 // Start
			String uploadName = null;
			if (languageLocale.equalsIgnoreCase("en")) {
				uploadName = "View";
			} else {
				uploadName = "व्यू";
			}
			// Added By gulrej on 12th may 2017 // end
			regForm.setUploadView(uploadName);
			return mapping.findForward(forward);

		}

		// end

		// efile number search new

		if (RegInitConstant.EFILE_SR_PAGE.equals(actionName)) {
			String efileNumber = regForm.getEfileNumber();
			String officeId = (String) session.getAttribute("loggedToOffice");
			boolean Status = commonBo.getStatusSR(efileNumber, session.getAttribute("UserId").toString(), officeId);
			System.out.println("efile search " + efileNumber);
			System.out.println("efile search Status" + Status);
			System.out.println("efile search " + efileNumber);
			ArrayList efileSearchList = commonBo.getEfileNumber(efileNumber, session.getAttribute("UserId").toString(), officeId);
			if (Status) {
				if (efileSearchList.size() == 0)
					regForm.setEfileSearchList(null);
				else
					regForm.setEfileSearchList(efileSearchList);

				request.setAttribute("efileSearchList", regForm.getEfileSearchList());
				forward = "efileNewSearchDashboard";
				return mapping.findForward(forward);
			} else {
				forward = "efileNewSearchDashboardFail";
				return mapping.findForward(forward);
			}

		}

		// after click on any pending application id from PO dashboard
		if (request.getParameter("hdnApplicationIdPo") != null) {
			boolean checkMode = false;
			boolean checkMode1 = false;
			regForm.setExtraflag("");
			regForm.setExtraflag1("");
			regForm.setExempflag("");
			regForm.setExempflag4("");
			regForm.setDisplaypropoutMPFlag("");
			regForm.setPhysicalStampFlagPo1("");
			regForm.setPhysicalStampFlagPo("");
			regForm.setUploadflag("");
			ArrayList exemption = new ArrayList();
			ArrayList exemptionEfile = new ArrayList();
			try {

				String TempIdPo = request.getParameter("hdnApplicationIdPo");
				session.setAttribute("TempIdPo", TempIdPo);

				// below code to get duty txn id from table
				String EfileTempId = commonBo.getEfileTempId(TempIdPo);
				String TempDutyIdPo = commonBo.getTempDutyId(EfileTempId);
				String instId = commonBo.getinstId(TempDutyIdPo);
				regForm.setPartypo(instId);
				session.setAttribute("TempDutyIdPo", TempDutyIdPo);

				if (instId.equalsIgnoreCase("2012") || instId.equalsIgnoreCase("2016")) {
					ArrayList getInstExtraList = commonBo.getInstExtraList(EfileTempId);
					if (getInstExtraList.size() > 0) {
						ArrayList rowList;
						for (int i = 0; i < getInstExtraList.size(); i++) {
							rowList = (ArrayList) getInstExtraList.get(i);
							regForm.setBankdepositedeed(rowList.get(0).toString());
							regForm.setBranchdepositdeed(rowList.get(1).toString());
							regForm.setBankaddressdeed(rowList.get(2).toString());
							regForm.setBankauthdeed(rowList.get(3).toString());
							regForm.setLoanamt(rowList.get(4).toString());
							regForm.setSanamt(rowList.get(5).toString());
							regForm.setExtraflag("Y");
						}

					}
				}

				if (instId.equalsIgnoreCase("2104") || instId.equalsIgnoreCase("2054") || instId.equalsIgnoreCase("2055")) {
					ArrayList getInstExtraList1 = commonBo.getInstExtraList1(EfileTempId);
					if (getInstExtraList1.size() > 0) {
						ArrayList rowList;
						for (int i = 0; i < getInstExtraList1.size(); i++) {
							rowList = (ArrayList) getInstExtraList1.get(i);
							regForm.setWithpossession(rowList.get(0).toString());
							regForm.setSecuredamt(rowList.get(1).toString());
							regForm.setExtraflag1("Y");
						}

					}
				}

				checkMode = commonBo.getCheckModePO(EfileTempId);
				checkMode1 = commonBo.getCheckModePO1(EfileTempId);

				boolean exemptionModeEfile = false;
				exemptionModeEfile = commonBo.getCheckExemptionDetails1(TempDutyIdPo);

				if (exemptionModeEfile) {
					// first exemption on stamp duty
					exemption = commonBo.getExemptionListEfile1(TempDutyIdPo);
					if (exemption.size() > 0) {
						regForm.setExemptionListEfileSelected(exemption);

					}

					if (exemption.size() > 0) {
						regForm.setExempflag("Y");
					} else {
						regForm.setExempflag("");
					}

					// for efiling exemption
					exemptionEfile = commonBo.getExemptionListEfile4(TempDutyIdPo);

					if (exemptionEfile.size() > 0) {
						regForm.setExemptionListEfileSelected4(exemptionEfile);

					}

					if (exemptionEfile.size() > 0) {
						regForm.setExempflag4("Y");
					} else {
						regForm.setExempflag4("");
					}
				}

				if (!checkMode && !checkMode1) {
					ArrayList getDuty = commonBo.getDutyEfileNonPayment1(TempDutyIdPo, EfileTempId);
					if (getDuty.size() > 0) {
						ArrayList rowList;
						for (int i = 0; i < getDuty.size(); i++) {
							rowList = (ArrayList) getDuty.get(i);
							if (languageLocale.equalsIgnoreCase("en")) {
								regForm.setDeedNameEfile(rowList.get(0).toString());
							} else {
								regForm.setDeedNameEfile(rowList.get(12).toString());
							}

							if (languageLocale.equalsIgnoreCase("en")) {
								regForm.setInstNameEfile(rowList.get(1).toString());
							} else {
								regForm.setInstNameEfile(rowList.get(13).toString());
							}
							//regForm.setDeedNameEfile(rowList.get(0).toString()
							// );
							//regForm.setInstNameEfile(rowList.get(1).toString()
							// );

							regForm.setStampDutyEfile(rowList.get(2).toString());
							regForm.setMunDUTYEfile(rowList.get(3).toString());
							regForm.setBlockDutyEfile(rowList.get(4).toString());
							regForm.setUpkarDutyEfile(rowList.get(5).toString());
							if (exemptionEfile.size() > 0) {
								regForm.setRegfeeEfile("0");
							} else {
								regForm.setRegfeeEfile(rowList.get(6).toString());
							}
							regForm.setDutypaidEfile(rowList.get(7).toString());
							regForm.setTotalpayableduty(rowList.get(8).toString());
							if (exemption.size() > 0) {
								regForm.setTotalAfterexmpEfile("0");
							} else {
								regForm.setTotalAfterexmpEfile(rowList.get(9).toString());
							}
							if (rowList.get(10) != null) {
								regForm.setDisplaypurploan(rowList.get(10).toString());
							} else {
								regForm.setDisplaypurploan("");
							}

							if (rowList.get(11) != null) {
								regForm.setDisplaypropoutMP(rowList.get(11).toString());
								regForm.setDisplaypropoutMPFlag("Y");
							}

							forward = "podisplaydutybank";
						}
					}
				} else if (checkMode) {
					if (TempDutyIdPo != null) {
						ArrayList getDuty = commonBo.getDutyEfilePo(TempDutyIdPo, EfileTempId);

						if (getDuty.size() > 0) {
							System.out.println("inside getduty of action");
							ArrayList rowList;
							for (int i = 0; i < getDuty.size(); i++) {
								rowList = (ArrayList) getDuty.get(i);
								if (languageLocale.equalsIgnoreCase("en")) {
									regForm.setDeedNameEfile(rowList.get(0).toString());
								} else {
									regForm.setDeedNameEfile(rowList.get(14).toString());
								}
								if (languageLocale.equalsIgnoreCase("en")) {
									regForm.setInstNameEfile(rowList.get(1).toString());
								} else {
									regForm.setInstNameEfile(rowList.get(15).toString());
								}
								// regForm.setDeedNameEfile(rowList.get(0)
								// .toString());
								// regForm.setInstNameEfile(rowList.get(1)
								// .toString());
								regForm.setStampDutyEfile(rowList.get(2).toString());
								regForm.setMunDUTYEfile(rowList.get(3).toString());
								regForm.setBlockDutyEfile(rowList.get(4).toString());
								regForm.setUpkarDutyEfile(rowList.get(5).toString());
								if (exemptionEfile.size() > 0) {
									regForm.setRegfeeEfile("0");
								} else {
									regForm.setRegfeeEfile(rowList.get(6).toString());
								}
								regForm.setDutypaidEfile(rowList.get(7).toString());
								regForm.setTotalpayableduty(rowList.get(8).toString());
								if (exemption.size() > 0) {
									regForm.setTotalAfterexmpEfile("0");
								} else {
									regForm.setTotalAfterexmpEfile(rowList.get(9).toString());
								}
								regForm.setEstampCodeDisplay(rowList.get(10).toString());
								regForm.setEstampCodeStatus(rowList.get(11).toString());
								regForm.setDisplaypurploan(rowList.get(12).toString());
								regForm.setPhysicalStampFlagPo1("Y");

								if (rowList.get(13) != null) {
									regForm.setDisplaypropoutMP(rowList.get(13).toString());
									regForm.setDisplaypropoutMPFlag("Y");
								}
							}

						}

						forward = "podisplaydutybank";
					}
				} else {
					ArrayList getDuty = commonBo.getDutyPhyEfilePo(TempDutyIdPo, EfileTempId);

					if (getDuty.size() > 0) {
						System.out.println("inside getduty of action");
						ArrayList rowList;
						for (int i = 0; i < getDuty.size(); i++) {
							rowList = (ArrayList) getDuty.get(i);
							if (languageLocale.equalsIgnoreCase("en")) {
								regForm.setDeedNameEfile(rowList.get(0).toString());
							} else {
								regForm.setDeedNameEfile(rowList.get(16).toString());
							}

							if (languageLocale.equalsIgnoreCase("en")) {
								regForm.setInstNameEfile(rowList.get(1).toString());
							} else {
								regForm.setInstNameEfile(rowList.get(17).toString());
							}

							//regForm.setDeedNameEfile(rowList.get(0).toString()
							// );
							//regForm.setInstNameEfile(rowList.get(1).toString()
							// );
							regForm.setStampDutyEfile(rowList.get(2).toString());
							regForm.setMunDUTYEfile(rowList.get(3).toString());
							regForm.setBlockDutyEfile(rowList.get(4).toString());
							regForm.setUpkarDutyEfile(rowList.get(5).toString());
							if (exemptionEfile.size() > 0) {
								regForm.setRegfeeEfile("0");
							} else {
								regForm.setRegfeeEfile(rowList.get(6).toString());
							}
							regForm.setDutypaidEfile(rowList.get(7).toString());
							regForm.setTotalpayableduty(rowList.get(8).toString());
							if (exemption.size() > 0) {
								regForm.setTotalAfterexmpEfile("0");
							} else {
								regForm.setTotalAfterexmpEfile(rowList.get(9).toString());
							}
							regForm.setDisplaypurploan(rowList.get(10).toString());
							if (rowList.get(11) != null) {
								regForm.setDisplaypropoutMP(rowList.get(11).toString());
								regForm.setDisplaypropoutMPFlag("Y");
							}
							ArrayList physicalList = commonBo.getphysicalList(EfileTempId, regForm);
							if (physicalList.size() > 0) {
								regForm.setPhyListEfileSelected(physicalList);

							}
							regForm.setPhysicalStampFlagPo("Y");
						}
					}
					forward = "podisplaydutybank";
				}
			}

			catch (Exception e) {

				logger.debug(e);
				logger.debug(e.getMessage(), e);
				forward = "failure";
				return mapping.findForward(forward);

			}

			// to check for additional upload
			if (checkMode) {
				String dutyid = (String) session.getAttribute("TempDutyIdPo");
				String uploadpath = commonBo.getuploadpath(dutyid);
				if (!uploadpath.isEmpty()) {
					regForm.setUploadpath(uploadpath);
					regForm.setUploadflag("Y");
					String view = "View";
					regForm.setCommonView(view);
				}
			} else {
				String dutyid = (String) session.getAttribute("TempDutyIdPo");
				String uploadpath1 = commonBo.getuploadpath1(dutyid);
				if (!uploadpath1.isEmpty()) {
					regForm.setUploadpath(uploadpath1);
					regForm.setUploadflag("Y");
					String view = "View";
					regForm.setCommonView(view);
				}
			}

			// below code for form upload file
			String TempIdPo = (String) session.getAttribute("TempIdPo");
			String EfileTempId = commonBo.getEfileTempId(TempIdPo);
			String uploadPathSR = commonBo.getuploadPathSR(EfileTempId);
			regForm.setUploadPathSR(uploadPathSR);
			// String uploadName = "View";
			// Added By gulrej on 12th may 2017 // Start
			String uploadName = null;
			if (languageLocale.equalsIgnoreCase("en")) {
				uploadName = "View";
			} else {
				uploadName = "व्यू";
			}
			// Added By gulrej on 12th may 2017 // end
			regForm.setUploadView(uploadName);

			return mapping.findForward(forward);

		}

		// end

		// efile number search new PO

		if (RegInitConstant.EFILE_PO_PAGE.equals(actionName)) {
			String efileNumber = regForm.getEfileNumber();

			boolean Status = commonBo.getStatus(efileNumber, session.getAttribute("UserId").toString());
			System.out.println("efile search " + efileNumber);
			System.out.println("efile search Status" + Status);

			if (Status) {
				ArrayList efileSearchListPo = commonBo.getEfileNumberPo(efileNumber);

				if (efileSearchListPo.size() == 0)
					regForm.setEfileSearchListPo(null);
				else
					regForm.setEfileSearchListPo(efileSearchListPo);

				request.setAttribute("efileSearchListPo", regForm.getEfileSearchListPo());
				regForm.setActionName(" ");
				forward = "efileNewSearchDashboardPO";
				return mapping.findForward(forward);

			}

			else {
				regForm.setActionName(" ");
				forward = "efileNewSearchDashboardPOFail";
				return mapping.findForward(forward);
			}
		}

		// for creating refer to DR search Dashboard SR

		if (fwdPage != null && "Refer".equalsIgnoreCase(fwdPage)) {
			String officeId = (String) session.getAttribute("loggedToOffice");
			ArrayList referSearchList = commonBo.getReferEfile(session.getAttribute("UserId").toString(), officeId);

			if (referSearchList.size() == 0)
				regForm.setReferSearchList(null);
			else
				regForm.setReferSearchList(referSearchList);

			request.setAttribute("referSearchList", regForm.getReferSearchList());
			forward = "referNewSearchDashboard";

		}
		// end of reject search

		// for creating refer to DR search Dashboard PO

		if (fwdPage != null && "ReferPO".equalsIgnoreCase(fwdPage)) {

			ArrayList referSearchListPo = commonBo.getReferEfilePO(session.getAttribute("UserId").toString());

			if (referSearchListPo.size() == 0)
				regForm.setReferSearchListPo(null);
			else
				regForm.setReferSearchListPo(referSearchListPo);

			request.setAttribute("referSearchListPo", regForm.getReferSearchListPo());
			forward = "referNewSearchDashboardPO";
			return mapping.findForward(forward);

		}
		// end of reject search

		// for creating new search Dashboard SR

		if (fwdPage != null && "Dashboard".equalsIgnoreCase(fwdPage)) {

			forward = "dashboardNewSearch";
			return mapping.findForward(forward);

		}
		// end of new search

		// For Print efile certificate
		if (fwdPage != null && "Print".equalsIgnoreCase(fwdPage)) {
			ArrayList efilePrintListPo = commonBo.getPrintEfile(session.getAttribute("UserId").toString());

			if (efilePrintListPo.size() == 0)
				regForm.setReferSearchListPo(null);
			else
				regForm.setEfilePrintListPo(efilePrintListPo);

			request.setAttribute("efilePrintListPo", regForm.getEfilePrintListPo());
			forward = "printDashboardPO";
			return mapping.findForward(forward);
		}

		// end of Print efile certificate

		if (request.getParameter("hdnApplicationIdPrint") != null) {
			// efile refernce no
			String TempIdPrint = request.getParameter("hdnApplicationIdPrint");
			session.setAttribute("TempIdPrint", TempIdPrint);

			// below code to get duty txn id from table

			// TempDutyIdPrint duty id
			String TempDutyIdPrint = commonBo.getTempDutyId1(TempIdPrint);
			session.setAttribute("TempDutyIdPrint", TempDutyIdPrint);

			// check whether efile doc no is generated or not
			String docEfileNo = "";
			boolean checkdocnoStatus = commonBo.checkdocnoStatus(TempIdPrint);
			if (checkdocnoStatus) {
				String docEfileNumber = commonBo.getdocEfileNo(TempIdPrint);
				docEfileNo = docEfileNumber;
			}

			else {
				// generate Efiling Number
				String district = "";
				String districtSeq;
				String stateId = "11";
				String firstSeq = commonBo.firstSeqPO();
				ArrayList districtId = commonBo.getDistrictEfile(TempDutyIdPrint);

				for (int i = 0; i < districtId.size(); i++) {
					ArrayList abc = (ArrayList) districtId.get(0);
					district = (String) abc.get(0);
				}

				if (district.length() != 2 && !(district.length() >= 2)) {
					districtSeq = "0" + district;
				} else {
					districtSeq = district;
				}

				String srOffId = commonBo.getsrOffId(TempDutyIdPrint);
				String offId = srOffId.substring(3, 5);

				Calendar cal = new GregorianCalendar();
				String yr = "";
				int curr_year = cal.get(Calendar.YEAR);
				yr = String.valueOf(curr_year);

				String efileTxnIdSeqPo = commonBo.efileTxnIdSeqPo();
				String bookType = "11";

				docEfileNo = stateId + firstSeq + districtSeq + offId + yr + bookType + efileTxnIdSeqPo;
			}

			boolean status = commonBo.insertDocEfileNo(docEfileNo, TempDutyIdPrint);

			// end of generate Efiling Number

			// check whether estamp or physical stamp payment mode
			boolean checkMode = commonBo.getCheckMode(TempDutyIdPrint);
			regForm.setEFILETXNID(docEfileNo); // Added By Gulrej on 4th Aug,
												// 2017
			boolean checkModePhysical = commonBo.getcheckModePhysical(TempDutyIdPrint);

			ArrayList getgovDetails = new ArrayList();
			ArrayList getindDetails = new ArrayList();
			ArrayList getorgDetails = new ArrayList();
			ArrayList getindpoaDetails = new ArrayList();
			ArrayList getindpoaDetails1 = new ArrayList();
			ArrayList getorgpoaDetails = new ArrayList();
			ArrayList getorgpoaDetails1 = new ArrayList();
			ArrayList list = new ArrayList();
			ArrayList list1 = new ArrayList();
			ArrayList list2 = new ArrayList();
			ArrayList list3 = new ArrayList();
			ArrayList list4 = new ArrayList();
			if (!checkMode && !checkModePhysical) {
				System.out.println("this an case of where payment mode is null i.e through estamp or physical stamp");
				ArrayList getprintDetails = commonBo.getprintDetailsNonPayment(TempIdPrint);
				ArrayList getpartyPrintDetails = commonBo.getpartyPrintDetails(TempIdPrint);
				ArrayList getpropertyPrintDetails = commonBo.getpropertyPrintDetails(TempDutyIdPrint);

				if (getprintDetails.size() > 0) {
					ArrayList rowList;
					for (int i = 0; i < getprintDetails.size(); i++) {
						rowList = (ArrayList) getprintDetails.get(i);

						if (rowList.get(0) != null) {
							regForm.setDEEDNAME(rowList.get(0).toString());
						} else {
							regForm.setDEEDNAME("-");
						}

						if (rowList.get(1) != null) {
							regForm.setEFILETXNID(docEfileNo);
						} else {
							regForm.setEFILETXNID(docEfileNo);
						}

						if (rowList.get(2) != null) {
							regForm.setEFILEDATE(rowList.get(2).toString());
						} else {
							regForm.setEFILEDATE("-");
						}

						if (rowList.get(3) != null) {
							regForm.setEXCDATE(rowList.get(3).toString());
						} else {
							regForm.setEXCDATE("-");
						}

						if (rowList.get(4) != null) {
							regForm.setSRNAME(rowList.get(4).toString());
						} else {
							regForm.setSRNAME("-");
						}

						if (rowList.get(5) != null) {
							regForm.setSUBREGISTRAROFFICE(rowList.get(5).toString());
						} else {
							regForm.setSUBREGISTRAROFFICE("-");
						}

						if (rowList.get(6) != null) {
							regForm.setDATEOFSUBMISSIONPO(rowList.get(6).toString());
						} else {
							regForm.setDATEOFSUBMISSIONPO("-");
						}
						if (rowList.get(7) != null) {
							regForm.setSTAMPDUTY(rowList.get(7).toString());
						} else {
							regForm.setSTAMPDUTY("-");
						}
						if (rowList.get(8) != null) {
							regForm.setBANKNAME(rowList.get(8).toString());
						} else {
							regForm.setBANKNAME("-");
						}
						if (rowList.get(9) != null) {
							regForm.setADDRESS(rowList.get(9).toString());
						} else {
							regForm.setADDRESS("-");
						}
						if (rowList.get(10) != null) {
							regForm.setBANKAUTHORITYNAME(rowList.get(10).toString());
						} else {
							regForm.setBANKAUTHORITYNAME("-");
						}
						if (rowList.get(11) != null) {
							regForm.setBANKAUTHORITYDESIGNATION(rowList.get(11).toString());
						} else {
							regForm.setBANKAUTHORITYDESIGNATION("-");
						}

						if (rowList.get(12) != null) {
							regForm.setPURLOAN(rowList.get(12).toString());
						} else {
							regForm.setPURLOAN("-");
						}

						if (rowList.get(13) != null) {
							regForm.setFiledValue(rowList.get(13).toString());
						} else {
							regForm.setFiledValue("-");
						}

					}

					if (getpartyPrintDetails.size() > 0) {

						ArrayList rowList1;

						for (int i = 0; i < getpartyPrintDetails.size(); i++) {
							rowList1 = (ArrayList) getpartyPrintDetails.get(i);

							if (rowList1.get(1).toString().equalsIgnoreCase("Government Official")) {
								getgovDetails = commonBo.getgovDetails(rowList1.get(0).toString());

								if (getgovDetails.size() > 0) {
									for (int j = 0; j < getgovDetails.size(); j++) {
										RegCommonDTO a = (RegCommonDTO) getgovDetails.get(0);

										regForm.setGovdesg(a.getGovdesg());
										regForm.setGovfather(a.getGovfather());
										regForm.setGovaddress(a.getGovaddress());
										regForm.setGovPartyType(a.getGovpartyType());

									}
								}

								list.addAll(getgovDetails);
							}
							if (rowList1.get(1).toString().equalsIgnoreCase("Individual")) {
								boolean isPoA = false;
								boolean isPoACount = false;
								isPoA = commonBo.getispoa(rowList1.get(0).toString());
								if (isPoA) {
									isPoACount = commonBo.getisPoACount(rowList1.get(0).toString());

									if (isPoACount) {
										// when count is 1
										getindpoaDetails = commonBo.getindpoaDetails(rowList1.get(0).toString());

										if (getindpoaDetails.size() > 0) {
											for (int j = 0; j < getindpoaDetails.size(); j++) {
												RegCommonDTO a = (RegCommonDTO) getindpoaDetails.get(0);

												regForm.setPoaauthname(a.getPoaauthname());
												regForm.setPoafather(a.getPoafather());
												regForm.setPoaaddress(a.getPoaaddress());
												regForm.setPoapartytype(a.getPoapartytype());
											}
										}
										list3.addAll(getindpoaDetails);
									}

									else {
										getindpoaDetails1 = commonBo.getindpoaDetails1(rowList1.get(0).toString());
										if (getindpoaDetails1.size() > 0) {
											for (int j = 0; j < getindpoaDetails1.size(); j++) {
												RegCommonDTO a = (RegCommonDTO) getindpoaDetails1.get(0);

												regForm.setPoaauthname(a.getPoaauthname());
												regForm.setPoafather(a.getPoafather());
												regForm.setPoaaddress(a.getPoaaddress());
												regForm.setPoapartytype(a.getPoapartytype());
											}

										}
										list3.addAll(getindpoaDetails1);
									}

								}

								else {
									getindDetails = commonBo.getindDetails(rowList1.get(0).toString());
									if (getindDetails.size() > 0) {
										for (int j = 0; j < getindDetails.size(); j++) {
											RegCommonDTO a = (RegCommonDTO) getindDetails.get(0);

											regForm.setIndname(a.getIndname());
											regForm.setIndfather(a.getIndfather());
											regForm.setIndiaddress(a.getIndiaddress());
											regForm.setIndpartyType(a.getIndpartyType());
										}
									}

									list1.addAll(getindDetails);
								}
							}

							if (rowList1.get(1).toString().equalsIgnoreCase("Organization")) {
								boolean isPoA = false;
								boolean isPoACount = false;
								isPoA = commonBo.getispoa(rowList1.get(0).toString());
								if (isPoA) {
									isPoACount = commonBo.getisPoACount(rowList1.get(0).toString());
									if (isPoACount) {
										getorgpoaDetails = commonBo.getorgpoaDetails(rowList1.get(0).toString());
										if (getorgpoaDetails.size() > 0) {
											for (int j = 0; j < getorgpoaDetails.size(); j++) {
												RegCommonDTO a = (RegCommonDTO) getorgpoaDetails.get(0);

												regForm.setPoaorgauthname(a.getPoaorgauthname());
												regForm.setPoaorgfather(a.getPoaorgfather());
												regForm.setPoaorgaddress(a.getPoaorgaddress());
												regForm.setPoaorgpartytype(a.getPoaorgpartytype());
											}
										}
										list4.addAll(getorgpoaDetails);
									} else {
										getorgpoaDetails1 = commonBo.getorgpoaDetails1(rowList1.get(0).toString());
										if (getorgpoaDetails.size() > 0) {
											for (int j = 0; j < getorgpoaDetails1.size(); j++) {
												RegCommonDTO a = (RegCommonDTO) getorgpoaDetails1.get(0);

												regForm.setPoaorgauthname(a.getPoaorgauthname());
												regForm.setPoaorgfather(a.getPoaorgfather());
												regForm.setPoaorgaddress(a.getPoaorgaddress());
												regForm.setPoaorgpartytype(a.getPoaorgpartytype());
											}
										}
										list4.addAll(getorgpoaDetails1);
									}
								} else {
									getorgDetails = commonBo.getorgDetails(rowList1.get(0).toString());
									if (getorgDetails.size() > 0) {
										for (int z = 0; z < getorgDetails.size(); z++) {
											RegCommonDTO a = (RegCommonDTO) getorgDetails.get(0);
											regForm.setOrgname(a.getOrgname());
											regForm.setOrgfather(a.getOrgfather());
											regForm.setOrgiaddress(a.getOrgiaddress());
											regForm.setOrgpartyType(a.getOrgpartyType());
										}
									}
									list2.addAll(getorgDetails);
								}
							}
						}
					}
					if (getpropertyPrintDetails.size() > 0) {
						for (int i = 0; i < getpropertyPrintDetails.size(); i++) {
							RegCommonDTO a = (RegCommonDTO) getpropertyPrintDetails.get(0);
							regForm.setProtype(a.getProtype());
							regForm.setPropaddress(a.getPropaddress());
							regForm.setProparear(a.getProparear());

						}
					}
					regForm.setGetpropPrintDetails(getpropertyPrintDetails);
					if (list != null) {
						regForm.setGetpartyPrintDetailsGov(list);
					}

					if (list1 != null) {
						regForm.setGetpartyPrintDetailsInd(list1);
					}
					if (list2 != null) {
						regForm.setGetpartyPrintDetailsOrg(list2);
					}
					if (list3 != null) {
						regForm.setGetpartyPrintDetailsPOA(list3);
					}
					if (list4 != null) {
						regForm.setGetpartyPrintDetailsOrgPOA(list4);
					}

					regForm.setEfileFeePrint("500");
					regForm.setPROPERTYVALID("NA");
					regForm.setAREAEFILE("NA");
					regForm.setPROPERTYADDRESS("NA");
					forward = "printDetailsPage";

				}
			}

			else if (checkMode) {
				ArrayList getprintDetails = commonBo.getprintDetails(TempIdPrint);
				ArrayList getpartyPrintDetails = commonBo.getpartyPrintDetails(TempIdPrint);
				ArrayList getpropertyPrintDetails = commonBo.getpropertyPrintDetails(TempDutyIdPrint);
				if (getprintDetails.size() > 0) {
					System.out.println("inside getduty of action");
					ArrayList rowList;
					for (int i = 0; i < getprintDetails.size(); i++) {
						rowList = (ArrayList) getprintDetails.get(i);
						if (rowList.get(0) != null) {
							regForm.setDEEDNAME(rowList.get(0).toString());
						} else {
							regForm.setDEEDNAME("-");
						}

						if (rowList.get(1) != null) {
							regForm.setEFILETXNID(docEfileNo);
						} else {
							regForm.setEFILETXNID(docEfileNo);
						}

						if (rowList.get(2) != null) {
							regForm.setEFILEDATE(rowList.get(2).toString());
						} else {
							regForm.setEFILEDATE("-");
						}

						if (rowList.get(3) != null) {
							regForm.setEXCDATE(rowList.get(3).toString());
						} else {
							regForm.setEXCDATE("-");
						}

						if (rowList.get(4) != null) {
							regForm.setSRNAME(rowList.get(4).toString());
						} else {
							regForm.setSRNAME("-");
						}

						if (rowList.get(5) != null) {
							regForm.setSUBREGISTRAROFFICE(rowList.get(5).toString());
						} else {
							regForm.setSUBREGISTRAROFFICE("-");
						}

						if (rowList.get(6) != null) {
							regForm.setDATEOFSUBMISSIONPO(rowList.get(6).toString());
						} else {
							regForm.setDATEOFSUBMISSIONPO("-");
						}
						if (rowList.get(7) != null) {
							regForm.setSTAMPDUTY(rowList.get(7).toString());
						} else {
							regForm.setSTAMPDUTY("-");
						}
						if (rowList.get(8) != null) {
							regForm.setBANKNAME(rowList.get(8).toString());
						} else {
							regForm.setBANKNAME("-");
						}
						if (rowList.get(9) != null) {
							regForm.setADDRESS(rowList.get(9).toString());
						} else {
							regForm.setADDRESS("-");
						}
						if (rowList.get(10) != null) {
							regForm.setBANKAUTHORITYNAME(rowList.get(10).toString());
						} else {
							regForm.setBANKAUTHORITYNAME("-");
						}
						if (rowList.get(11) != null) {
							regForm.setBANKAUTHORITYDESIGNATION(rowList.get(11).toString());
						} else {
							regForm.setBANKAUTHORITYDESIGNATION("-");
						}
						if (rowList.get(12) != null) {
							regForm.setPROPERTYID(rowList.get(12).toString());
						} else {
							regForm.setPROPERTYID("-");
						}
						if (rowList.get(18) != null) {
							regForm.setPROPERTYVALID(rowList.get(18).toString());
						} else {
							regForm.setPROPERTYVALID("-");
						}
						if (rowList.get(14) != null) {
							regForm.setPROPERTYADDRESS(rowList.get(14).toString());
						} else {
							regForm.setPROPERTYADDRESS("-");
						}
						if (rowList.get(15) != null) {
							regForm.setAreaType(rowList.get(15).toString());
						} else {
							regForm.setAreaType("-");
						}
						if (rowList.get(16) != null) {
							regForm.setECODE(rowList.get(16).toString());
						} else {
							regForm.setECODE("-");
						}
						if (rowList.get(17) != null) {
							regForm.setPURLOAN(rowList.get(17).toString());
						} else {
							regForm.setPURLOAN("-");
						}
						if (rowList.get(18) != null) {
							regForm.setTypePropertyEFile(rowList.get(18).toString());
						} else {
							regForm.setTypePropertyEFile("-");
						}
						if (rowList.get(19) != null) {
							regForm.setAREAEFILE(rowList.get(19).toString());
						} else {
							regForm.setAREAEFILE("-");
						}

						if (rowList.get(20) != null) {
							regForm.setFiledValue(rowList.get(20).toString());
						} else {
							regForm.setFiledValue("-");
						}
					}

				}
				if (getpartyPrintDetails.size() > 0) {

					ArrayList rowList;

					for (int i = 0; i < getpartyPrintDetails.size(); i++) {
						rowList = (ArrayList) getpartyPrintDetails.get(i);

						if (rowList.get(1).toString().equalsIgnoreCase("Government Official")) {
							getgovDetails = commonBo.getgovDetails(rowList.get(0).toString());

							if (getgovDetails.size() > 0) {
								for (int j = 0; j < getgovDetails.size(); j++) {
									RegCommonDTO a = (RegCommonDTO) getgovDetails.get(0);

									regForm.setGovdesg(a.getGovdesg());
									regForm.setGovfather(a.getGovfather());
									regForm.setGovaddress(a.getGovaddress());
									regForm.setGovPartyType(a.getGovpartyType());

								}
							}

							list.addAll(getgovDetails);
						}
						if (rowList.get(1).toString().equalsIgnoreCase("Individual")) {
							boolean isPoA = false;
							boolean isPoACount = false;
							isPoA = commonBo.getispoa(rowList.get(0).toString());
							if (isPoA) {
								isPoACount = commonBo.getisPoACount(rowList.get(0).toString());

								if (isPoACount) {
									// when count is 1
									getindpoaDetails = commonBo.getindpoaDetails(rowList.get(0).toString());

									if (getindpoaDetails.size() > 0) {
										for (int j = 0; j < getindpoaDetails.size(); j++) {
											RegCommonDTO a = (RegCommonDTO) getindpoaDetails.get(0);

											regForm.setPoaauthname(a.getPoaauthname());
											regForm.setPoafather(a.getPoafather());
											regForm.setPoaaddress(a.getPoaaddress());
											regForm.setPoapartytype(a.getPoapartytype());
										}
									}
									list3.addAll(getindpoaDetails);
								}

								else {
									getindpoaDetails1 = commonBo.getindpoaDetails1(rowList.get(0).toString());
									if (getindpoaDetails1.size() > 0) {
										for (int j = 0; j < getindpoaDetails1.size(); j++) {
											RegCommonDTO a = (RegCommonDTO) getindpoaDetails1.get(0);

											regForm.setPoaauthname(a.getPoaauthname());
											regForm.setPoafather(a.getPoafather());
											regForm.setPoaaddress(a.getPoaaddress());
											regForm.setPoapartytype(a.getPoapartytype());
										}

									}
									list3.addAll(getindpoaDetails1);
								}

							}

							else {
								getindDetails = commonBo.getindDetails(rowList.get(0).toString());
								if (getindDetails.size() > 0) {
									for (int j = 0; j < getindDetails.size(); j++) {
										RegCommonDTO a = (RegCommonDTO) getindDetails.get(0);

										regForm.setIndname(a.getIndname());
										regForm.setIndfather(a.getIndfather());
										regForm.setIndiaddress(a.getIndiaddress());
										regForm.setIndpartyType(a.getIndpartyType());
									}
								}

								list1.addAll(getindDetails);
							}
						}

						if (rowList.get(1).toString().equalsIgnoreCase("Organization")) {
							boolean isPoA = false;
							boolean isPoACount = false;
							isPoA = commonBo.getispoa(rowList.get(0).toString());
							if (isPoA) {
								isPoACount = commonBo.getisPoACount(rowList.get(0).toString());
								if (isPoACount) {
									getorgpoaDetails = commonBo.getorgpoaDetails(rowList.get(0).toString());
									if (getorgpoaDetails.size() > 0) {
										for (int j = 0; j < getorgpoaDetails.size(); j++) {
											RegCommonDTO a = (RegCommonDTO) getorgpoaDetails.get(0);

											regForm.setPoaorgauthname(a.getPoaorgauthname());
											regForm.setPoaorgfather(a.getPoaorgfather());
											regForm.setPoaorgaddress(a.getPoaorgaddress());
											regForm.setPoaorgpartytype(a.getPoaorgpartytype());
										}
									}
									list4.addAll(getorgpoaDetails);
								} else {
									getorgpoaDetails1 = commonBo.getorgpoaDetails1(rowList.get(0).toString());
									if (getorgpoaDetails1.size() > 0) {
										for (int j = 0; j < getorgpoaDetails1.size(); j++) {
											RegCommonDTO a = (RegCommonDTO) getorgpoaDetails1.get(0);

											regForm.setPoaorgauthname(a.getPoaorgauthname());
											regForm.setPoaorgfather(a.getPoaorgfather());
											regForm.setPoaorgaddress(a.getPoaorgaddress());
											regForm.setPoaorgpartytype(a.getPoaorgpartytype());
										}
									}
									list4.addAll(getorgpoaDetails1);
								}
							} else {
								getorgDetails = commonBo.getorgDetails(rowList.get(0).toString());
								if (getorgDetails.size() > 0) {
									for (int z = 0; z < getorgDetails.size(); z++) {
										RegCommonDTO a = (RegCommonDTO) getorgDetails.get(0);
										regForm.setOrgname(a.getOrgname());
										regForm.setOrgfather(a.getOrgfather());
										regForm.setOrgiaddress(a.getOrgiaddress());
										regForm.setOrgpartyType(a.getOrgpartyType());
									}
								}
								list2.addAll(getorgDetails);
							}
						}
					}
				}
				if (getpropertyPrintDetails.size() > 0) {
					for (int i = 0; i < getpropertyPrintDetails.size(); i++) {
						RegCommonDTO a = (RegCommonDTO) getpropertyPrintDetails.get(0);
						regForm.setProtype(a.getProtype());
						regForm.setPropaddress(a.getPropaddress());
						regForm.setProparear(a.getProparear());
					}
				}
				regForm.setGetpropPrintDetails(getpropertyPrintDetails);
				if (list != null) {
					regForm.setGetpartyPrintDetailsGov(list);
				}

				if (list1 != null) {
					regForm.setGetpartyPrintDetailsInd(list1);
				}
				if (list2 != null) {
					regForm.setGetpartyPrintDetailsOrg(list2);
				}
				if (list3 != null) {
					regForm.setGetpartyPrintDetailsPOA(list3);
				}
				if (list4 != null) {
					regForm.setGetpartyPrintDetailsOrgPOA(list4);
				}
				regForm.setEfileFeePrint("500");
				forward = "printDetailsPage";

			}
			// below else will work when payment mode is physical stamp
			else {
				ArrayList getprintDetails = commonBo.getprintPhysicalDetails(TempIdPrint);
				ArrayList getpartyPrintDetails = commonBo.getpartyPrintDetails(TempIdPrint);
				ArrayList getpropertyPrintDetails = commonBo.getpropertyPrintDetails(TempDutyIdPrint);
				String physicalSeries = commonBo.getphysicalSeries(TempIdPrint);

				if (getprintDetails.size() > 0) {
					System.out.println("inside getduty of action");
					ArrayList rowList;
					for (int i = 0; i < getprintDetails.size(); i++) {
						rowList = (ArrayList) getprintDetails.get(i);
						if (rowList.get(0) != null) {
							regForm.setDEEDNAME(rowList.get(0).toString());
						} else {
							regForm.setDEEDNAME("-");
						}

						if (rowList.get(1) != null) {
							regForm.setEFILETXNID(docEfileNo);
						} else {
							regForm.setEFILETXNID(docEfileNo);
						}

						if (rowList.get(2) != null) {
							regForm.setEFILEDATE(rowList.get(2).toString());
						} else {
							regForm.setEFILEDATE("-");
						}

						if (rowList.get(3) != null) {
							regForm.setEXCDATE(rowList.get(3).toString());
						} else {
							regForm.setEXCDATE("-");
						}

						if (rowList.get(4) != null) {
							regForm.setSRNAME(rowList.get(4).toString());
						} else {
							regForm.setSRNAME("-");
						}

						if (rowList.get(5) != null) {
							regForm.setSUBREGISTRAROFFICE(rowList.get(5).toString());
						} else {
							regForm.setSUBREGISTRAROFFICE("-");
						}

						if (rowList.get(6) != null) {
							regForm.setDATEOFSUBMISSIONPO(rowList.get(6).toString());
						} else {
							regForm.setDATEOFSUBMISSIONPO("-");
						}
						if (rowList.get(7) != null) {
							regForm.setSTAMPDUTY(rowList.get(7).toString());
						} else {
							regForm.setSTAMPDUTY("-");
						}
						if (rowList.get(8) != null) {
							regForm.setBANKNAME(rowList.get(8).toString());
						} else {
							regForm.setBANKNAME("-");
						}
						if (rowList.get(9) != null) {
							regForm.setADDRESS(rowList.get(9).toString());
						} else {
							regForm.setADDRESS("-");
						}
						if (rowList.get(10) != null) {
							regForm.setBANKAUTHORITYNAME(rowList.get(10).toString());
						} else {
							regForm.setBANKAUTHORITYNAME("-");
						}
						if (rowList.get(11) != null) {
							regForm.setBANKAUTHORITYDESIGNATION(rowList.get(11).toString());
						} else {
							regForm.setBANKAUTHORITYDESIGNATION("-");
						}
						if (rowList.get(12) != null) {
							regForm.setPROPERTYID(rowList.get(12).toString());
						} else {
							regForm.setPROPERTYID("-");
						}
						if (rowList.get(18) != null) {
							regForm.setPROPERTYVALID(rowList.get(18).toString());
						} else {
							regForm.setPROPERTYVALID("-");
						}
						if (rowList.get(14) != null) {
							regForm.setPROPERTYADDRESS(rowList.get(14).toString());
						} else {
							regForm.setPROPERTYADDRESS("-");
						}
						if (rowList.get(15) != null) {
							regForm.setAreaType(rowList.get(15).toString());
						} else {
							regForm.setAreaType("-");
						}
						if (rowList.get(16) != null) {
							regForm.setECODE(physicalSeries);
						} else {
							regForm.setECODE("-");
						}
						if (rowList.get(17) != null) {
							regForm.setPURLOAN(rowList.get(17).toString());
						} else {
							regForm.setPURLOAN("-");
						}
						if (rowList.get(18) != null) {
							regForm.setTypePropertyEFile(rowList.get(18).toString());
						} else {
							regForm.setTypePropertyEFile("-");
						}
						if (rowList.get(19) != null) {
							regForm.setAREAEFILE(rowList.get(19).toString());
						} else {
							regForm.setAREAEFILE("-");
						}

						if (rowList.get(20) != null) {
							regForm.setFiledValue(rowList.get(20).toString());
						} else {
							regForm.setFiledValue("-");
						}
					}

				}

				if (getpartyPrintDetails.size() > 0) {

					ArrayList rowList;

					for (int i = 0; i < getpartyPrintDetails.size(); i++) {
						rowList = (ArrayList) getpartyPrintDetails.get(i);

						if (rowList.get(1).toString().equalsIgnoreCase("Government Official")) {
							getgovDetails = commonBo.getgovDetails(rowList.get(0).toString());

							if (getgovDetails.size() > 0) {
								for (int j = 0; j < getgovDetails.size(); j++) {
									RegCommonDTO a = (RegCommonDTO) getgovDetails.get(0);

									regForm.setGovdesg(a.getGovdesg());
									regForm.setGovfather(a.getGovfather());
									regForm.setGovaddress(a.getGovaddress());
									regForm.setGovPartyType(a.getGovpartyType());

								}
							}

							list.addAll(getgovDetails);
						}
						if (rowList.get(1).toString().equalsIgnoreCase("Individual")) {
							boolean isPoA = false;
							boolean isPoACount = false;
							isPoA = commonBo.getispoa(rowList.get(0).toString());
							if (isPoA) {
								isPoACount = commonBo.getisPoACount(rowList.get(0).toString());

								if (isPoACount) {
									// when count is 1
									getindpoaDetails = commonBo.getindpoaDetails(rowList.get(0).toString());

									if (getindpoaDetails.size() > 0) {
										for (int j = 0; j < getindpoaDetails.size(); j++) {
											RegCommonDTO a = (RegCommonDTO) getindpoaDetails.get(0);

											regForm.setPoaauthname(a.getPoaauthname());
											regForm.setPoafather(a.getPoafather());
											regForm.setPoaaddress(a.getPoaaddress());
											regForm.setPoapartytype(a.getPoapartytype());
										}
									}
									list3.addAll(getindpoaDetails);
								}

								else {
									getindpoaDetails1 = commonBo.getindpoaDetails1(rowList.get(0).toString());
									if (getindpoaDetails1.size() > 0) {
										for (int j = 0; j < getindpoaDetails1.size(); j++) {
											RegCommonDTO a = (RegCommonDTO) getindpoaDetails1.get(0);

											regForm.setPoaauthname(a.getPoaauthname());
											regForm.setPoafather(a.getPoafather());
											regForm.setPoaaddress(a.getPoaaddress());
											regForm.setPoapartytype(a.getPoapartytype());
										}

									}
									list3.addAll(getindpoaDetails1);
								}

							}

							else {
								getindDetails = commonBo.getindDetails(rowList.get(0).toString());
								if (getindDetails.size() > 0) {
									for (int j = 0; j < getindDetails.size(); j++) {
										RegCommonDTO a = (RegCommonDTO) getindDetails.get(0);

										regForm.setIndname(a.getIndname());
										regForm.setIndfather(a.getIndfather());
										regForm.setIndiaddress(a.getIndiaddress());
										regForm.setIndpartyType(a.getIndpartyType());
									}
								}

								list1.addAll(getindDetails);
							}
						}

						if (rowList.get(1).toString().equalsIgnoreCase("Organization")) {
							boolean isPoA = false;
							boolean isPoACount = false;
							isPoA = commonBo.getispoa(rowList.get(0).toString());
							if (isPoA) {
								isPoACount = commonBo.getisPoACount(rowList.get(0).toString());
								if (isPoACount) {
									getorgpoaDetails = commonBo.getorgpoaDetails(rowList.get(0).toString());
									if (getorgpoaDetails.size() > 0) {
										for (int j = 0; j < getorgpoaDetails.size(); j++) {
											RegCommonDTO a = (RegCommonDTO) getorgpoaDetails.get(0);

											regForm.setPoaorgauthname(a.getPoaorgauthname());
											regForm.setPoaorgfather(a.getPoaorgfather());
											regForm.setPoaorgaddress(a.getPoaorgaddress());
											regForm.setPoaorgpartytype(a.getPoaorgpartytype());
										}
									}
									list4.addAll(getorgpoaDetails);
								} else {
									getorgpoaDetails1 = commonBo.getorgpoaDetails1(rowList.get(0).toString());
									if (getorgpoaDetails1.size() > 0) {
										for (int j = 0; j < getorgpoaDetails1.size(); j++) {
											RegCommonDTO a = (RegCommonDTO) getorgpoaDetails1.get(0);

											regForm.setPoaorgauthname(a.getPoaorgauthname());
											regForm.setPoaorgfather(a.getPoaorgfather());
											regForm.setPoaorgaddress(a.getPoaorgaddress());
											regForm.setPoaorgpartytype(a.getPoaorgpartytype());
										}
									}
									list4.addAll(getorgpoaDetails1);
								}
							} else {
								getorgDetails = commonBo.getorgDetails(rowList.get(0).toString());
								if (getorgDetails.size() > 0) {
									for (int z = 0; z < getorgDetails.size(); z++) {
										RegCommonDTO a = (RegCommonDTO) getorgDetails.get(0);
										regForm.setOrgname(a.getOrgname());
										regForm.setOrgfather(a.getOrgfather());
										regForm.setOrgiaddress(a.getOrgiaddress());
										regForm.setOrgpartyType(a.getOrgpartyType());
									}
								}
								list2.addAll(getorgDetails);
							}
						}
					}
				}
				if (getpropertyPrintDetails.size() > 0) {
					for (int i = 0; i < getpropertyPrintDetails.size(); i++) {
						RegCommonDTO a = (RegCommonDTO) getpropertyPrintDetails.get(0);
						regForm.setProtype(a.getProtype());
						regForm.setPropaddress(a.getPropaddress());
						regForm.setProparear(a.getProparear());

					}
				}
				regForm.setGetpropPrintDetails(getpropertyPrintDetails);

				if (list != null) {
					regForm.setGetpartyPrintDetailsGov(list);
				}

				if (list1 != null) {
					regForm.setGetpartyPrintDetailsInd(list1);
				}
				if (list2 != null) {
					regForm.setGetpartyPrintDetailsOrg(list2);
				}
				if (list3 != null) {
					regForm.setGetpartyPrintDetailsPOA(list3);
				}
				if (list4 != null) {
					regForm.setGetpartyPrintDetailsOrgPOA(list4);
				}
				regForm.setEfileFeePrint("500");
				forward = "printDetailsPage";
			}
		}

		// for print page details

		// end

		if (RegInitConstant.NEXT_TO_EFILE_PAYMENT_ACTION.equals(actionName)) {
			DutyIdCheck = (String) session.getAttribute("TempDutyIdPo");
			EfileId = (String) session.getAttribute("TempIdPo");
			boolean efileexemption = false;
			boolean flagExemption = commonBo.checkExempFlag(DutyIdCheck);
			if (flagExemption) {

				String checkEfiledutyDetails = null;

				checkEfiledutyDetails = commonBo.checkEfiledutyDetails(DutyIdCheck);// F
				if (!checkEfiledutyDetails.isEmpty() && checkEfiledutyDetails != null) {
					efileexemption = true;
				}

			}

			// if(Double.parseDouble(regForm.getTotalBalanceAmount())>0)
			if (!efileexemption)

			{
				String efileFee = commonBo.getefileFee(DutyIdCheck);
				request.setAttribute("parentModName", "PODashboard Process");
				request.setAttribute("parentFunName", "E-Filing");
				request.setAttribute("formName", "reginitefiling");

				request.setAttribute("parentFunId", "FUN_501");
				// request.setAttribute("parentAmount", "500");
				request.setAttribute("parentAmount", efileFee);

				request.setAttribute("parentTable", "IGRS_EFILE_PAYMENT_DETLS");

				request.setAttribute("forwardPath", "./regInitEfiling.do?TRFS=NGI");
				request.setAttribute("parentColumnName", "TXN_ID");
				String TempIdPo = (String) session.getAttribute("TempIdPo");
				String userTypeId = commonBo.getUserTypeId(userId);
				String parentOfficeId = "NA";
				String parentOfficeName = "NA";
				String parentDistrictId = "NA";
				String parentDistrictName = "NA";
				String parentReferenceId = TempIdPo;

				if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_RU)) {

					parentDistrictId = commonBo.getDistIdEstamp(regForm.getHidnRegTxnId());
					parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);

				} else if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SP) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SPB) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_FI) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_PO)) {

					String[] arr = commonBo.getDistIdNameSP(userId);

					if (arr != null && arr.length == 2) {
						parentDistrictId = arr[0].trim();
						parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
					} else {
						parentDistrictId = "NA";
						parentDistrictName = "NA";
					}

				}

				// changes by preeti for PO district
				else if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_PUBOFC)) {
					String TempIdPo1 = (String) session.getAttribute("TempIdPo");

					String tempefile = commonBo.getTempEfileNoPayment(TempIdPo1);
					parentDistrictId = commonBo.getDistIdEfile(tempefile);
					parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
				}

				// end

				else if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_DRS)) {

					String[] arr = commonBo.getDistIdNameOfficeNameDRS(session.getAttribute("loggedToOffice").toString());
					parentOfficeId = session.getAttribute("loggedToOffice").toString();

					if (arr != null && arr.length == 3) {

						parentDistrictId = arr[0].trim();
						parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
						parentOfficeName = commonBo.getOfficeName(parentOfficeId, languageLocale);
					} else {

						parentDistrictId = "NA";
						parentDistrictName = "NA";
						parentOfficeName = "NA";
					}

				}

				request.setAttribute("parentOfficeId", parentOfficeId);
				request.setAttribute("parentOfficeName", parentOfficeName);
				request.setAttribute("parentDistrictId", parentDistrictId);
				request.setAttribute("parentDistrictName", parentDistrictName);
				request.setAttribute("parentReferenceId", parentReferenceId);

				String paymentType = "1";

				regForm.setPaymentType(paymentType);
				// CHECK FOR PAYMENT_STATUS BEFORE REDIRECTING.

				String[] paymentArr = commonBo.getPaymentFlag(regForm.getHidnRegTxnId(), paymentType);
				//logger.debug("----------------->payment flag:-"+paymentArr[0])
				// ;
				if (paymentArr != null) {
					if (!paymentArr[0].equalsIgnoreCase("p") && !paymentArr[0].equalsIgnoreCase("c")) {

						if (paymentArr[0].equalsIgnoreCase("I")) {

							// following code for updating purpose
							if (regForm.getPurpose() != null && !regForm.getPurpose().equalsIgnoreCase("")) {
								boolean updatePurpose = commonBo.updateEStampPurpose(regForm.getHidnRegTxnId(), regForm.getPurpose());
								if (updatePurpose) {
									logger.debug("purpose updated");
									forward = "reginitEfilingPayment";
								} else {
									logger.debug("purpose not updated");
									forward = "failure";
								}
							}

							boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
							logger.debug("----------------->payment insertion status new record:-" + insertStatus);
							if (insertStatus)
								forward = "reginitEfilingPayment";
							else
								forward = "failure";

						}

					} else if (paymentArr[0].equalsIgnoreCase("p")) {

						if (regForm.getPurpose() != null && !regForm.getPurpose().equalsIgnoreCase("")) {
							boolean updatePurpose = commonBo.updateEStampPurpose(regForm.getHidnRegTxnId(), regForm.getPurpose());
							if (updatePurpose) {
								logger.debug("purpose updated");

							} else {
								logger.debug("purpose not updated");

							}
						}

						regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
						request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());

						boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
						logger.debug("----------------->payment insertion status:-" + insertStatus);
						if (insertStatus)
							forward = "reginitEfilingPayment";
						else
							forward = "failure";
					} else if (paymentArr[0].equalsIgnoreCase("c")) {
						forward = "success1";
					} else {
						forward = "failure";
					}
				} else {

					if (regForm.getPurpose() != null && !regForm.getPurpose().equalsIgnoreCase("")) {
						boolean updatePurpose = commonBo.updateEStampPurpose(regForm.getHidnRegTxnId(), regForm.getPurpose());
						if (updatePurpose) {
							logger.debug("purpose updated");

						} else {
							logger.debug("purpose not updated");

						}
					}
					TempIdPay = (String) session.getAttribute("TempIdPo");
					boolean checkflag = commonBo.checkpayflag(TempIdPay);
					if (!checkflag == true) {
						regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
						request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());

						String parentKey = regForm.getPaymentTxnSeqId();
						session.setAttribute("parentKey", parentKey);
						boolean insertStatus = commonBo.insertPaymentDetailsEfile(regForm, paymentType, TempIdPo);
						logger.debug("----------------->payment insertion status:-" + insertStatus);
						if (insertStatus)
							forward = "reginitEfilingPayment";
					} else {

						String parentKey = commonBo.getparentKey(TempIdPay);
						request.setAttribute("parentKey", parentKey);
						forward = "challanDetails";
						// forward="reginitEfilingPayment";
					}
				}
			} else {
				boolean updatePaymentStatus = commonBo.updatePaymentStatus(EfileId);
				forward = "exemptionDetails";

			}

		}
		if (RegInitConstant.NEXT_TO_EFILE_CASHLESH_PAYMENT_ACTION.equals(actionName)) {
			DutyIdCheck = (String) session.getAttribute("TempDutyIdPo");
			EfileId = (String) session.getAttribute("TempIdPo");
			boolean efileexemption = false;
			boolean flagExemption = commonBo.checkExempFlag(DutyIdCheck);
			if (flagExemption) {

				String checkEfiledutyDetails = null;

				checkEfiledutyDetails = commonBo.checkEfiledutyDetails(DutyIdCheck);// F
				if (!checkEfiledutyDetails.isEmpty() && checkEfiledutyDetails != null) {
					efileexemption = true;
				}
			}
			if (!efileexemption){
				String efileFee = commonBo.getefileFee(DutyIdCheck);
				session.setAttribute("referenceID", EfileId);
				session.setAttribute("payableAmt", efileFee);
				session.setAttribute("payFuncID", "FUN_501");
				forward="efileCashlessPayment";
			}else{
				boolean updatePaymentStatus = commonBo.updatePaymentStatus(EfileId);
				forward = "exemptionDetails";
			}
		}
		// for creating new search Dashboard PO

		if (fwdPage != null && "DashboardPO".equalsIgnoreCase(fwdPage)) {

			forward = "dashboardNewSearchPO";
			return mapping.findForward(forward);

		}
		// end of new search

		// for creating submit search Dashboard PO

		if (fwdPage != null && "SubmitPO".equalsIgnoreCase(fwdPage)) {

			ArrayList submitSearchListPo = commonBo.getSubmitEfilePo();

			if (submitSearchListPo.size() == 0)
				regForm.setSubmitSearchListPo(null);
			else
				regForm.setSubmitSearchListPo(submitSearchListPo);

			request.setAttribute("submitSearchListPo", regForm.getSubmitSearchListPo());
			forward = "submitNewSearchDashboardPO";

		}
		// end of submit search

		// for creating sr dashboard
		if (request.getParameter("modName") != null) {
			if (request.getParameter("modName").equalsIgnoreCase("Registration Process")) {
				regForm.setDurationFrom("");
				regForm.setDurationTo("");
				regForm.setEfileNumber("");
				regForm.setExtraflag("");
				regForm.setExtraflag1("");
				String officeId = (String) session.getAttribute("loggedToOffice");
				ArrayList pendingApplicationList = commonBo.getPendingEfileApps1(session.getAttribute("UserId").toString(), officeId, languageLocale);

				if (pendingApplicationList.size() == 0) {
					regForm.setPendingRegApplicationList(new ArrayList());
				} else {
					regForm.setPendingEfileApplicationList(pendingApplicationList);
				}

				request.setAttribute("pendingApplicationList", regForm.getPendingEfileApplicationList());
				forward = "reginitDashboard";
				return mapping.findForward(forward);
			}
		}

		// for bank SR page
		if (RegInitConstant.BANK_SR_PAGE.equals(actionName)) {

			String TempId = (String) session.getAttribute("TempDutyId");
			ArrayList getBankDetails = commonBo.getBankDtls(TempId);

			if (getBankDetails.size() > 0) {

				ArrayList rowList;
				for (int i = 0; i < getBankDetails.size(); i++) {
					rowList = (ArrayList) getBankDetails.get(i);

					if (rowList.get(0) != null) {

						regForm.setBank(rowList.get(0).toString());
					} else {
						regForm.setBank("-");
					}

					if (rowList.get(1) != null) {
						regForm.setAddress(rowList.get(1).toString());
					} else {
						regForm.setAddress("-");
					}

					if (rowList.get(2) != null) {
						regForm.setCotry(rowList.get(2).toString());
					} else {
						regForm.setCotry("India");
					}

					if (rowList.get(3) != null) {
						regForm.setState(rowList.get(3).toString());
					} else {
						regForm.setState("Madhya Pradesh");
					}

					if (rowList.get(4) != null) {
						regForm.setDis(rowList.get(4).toString());
					} else {
						regForm.setDis("-");
					}

					if (rowList.get(5) != null) {
						regForm.setPincode(rowList.get(5).toString());
					} else {
						regForm.setPincode("-");
					}

					if (rowList.get(6) != null) {
						regForm.setPhnumber(rowList.get(6).toString());
					} else {
						regForm.setPhnumber("-");
					}

					if (rowList.get(7) != null) {
						regForm.setBankauthoname(rowList.get(7).toString());
					} else {
						regForm.setBankauthoname("-");
					}

					if (rowList.get(8) != null) {
						regForm.setBankauthodeg(rowList.get(8).toString());
					} else {
						regForm.setBankauthodeg("-");
					}

					if (rowList.get(9) != null) {
						regForm.setBankauthophno(rowList.get(9).toString());
					} else {
						regForm.setBankauthophno("-");
					}

					if (rowList.get(10) != null) {
						regForm.setBankauthoemail(rowList.get(10).toString());
					} else {
						regForm.setBankauthoemail("-");
					}

					if (rowList.get(11) != null) {
						regForm.setCreateddate(rowList.get(11).toString());
					} else {
						regForm.setCreateddate("-");
					}

				}

			}
			forward = "srdispalybank";
			return mapping.findForward(forward);
		}

		// for bank PO page
		if (RegInitConstant.BANK_PO_PAGE.equals(actionName)) {

			String TempId = (String) session.getAttribute("TempDutyIdPo");
			ArrayList getBankDetails = commonBo.getBankDtls(TempId);

			if (getBankDetails.size() > 0) {

				ArrayList rowList;
				for (int i = 0; i < getBankDetails.size(); i++) {
					rowList = (ArrayList) getBankDetails.get(i);

					if (rowList.get(0) != null) {

						regForm.setBank(rowList.get(0).toString());
					} else {
						regForm.setBank("-");
					}

					if (rowList.get(1) != null) {
						regForm.setAddress(rowList.get(1).toString());
					} else {
						regForm.setAddress("-");
					}

					if (rowList.get(2) != null) {
						regForm.setCotry(rowList.get(2).toString());
					} else {
						regForm.setCotry("-");
					}

					if (rowList.get(3) != null) {
						regForm.setState(rowList.get(3).toString());
					} else {
						regForm.setState("-");
					}

					if (rowList.get(4) != null) {
						regForm.setDis(rowList.get(4).toString());
					} else {
						regForm.setDis("-");
					}

					if (rowList.get(5) != null) {
						regForm.setPincode(rowList.get(5).toString());
					} else {
						regForm.setPincode("-");
					}

					if (rowList.get(6) != null) {
						regForm.setPhnumber(rowList.get(6).toString());
					} else {
						regForm.setPhnumber("-");
					}

					if (rowList.get(7) != null) {
						regForm.setBankauthoname(rowList.get(7).toString());
					} else {
						regForm.setBankauthoname("-");
					}

					if (rowList.get(8) != null) {
						regForm.setBankauthodeg(rowList.get(8).toString());
					} else {
						regForm.setBankauthodeg("-");
					}

					if (rowList.get(9) != null) {
						regForm.setBankauthophno(rowList.get(9).toString());
					} else {
						regForm.setBankauthophno("-");
					}

					if (rowList.get(10) != null) {
						regForm.setBankauthoemail(rowList.get(10).toString());
					} else {
						regForm.setBankauthoemail("-");
					}

					if (rowList.get(11) != null) {
						regForm.setCreateddate(rowList.get(11).toString());
					} else {
						regForm.setCreateddate("-");
					}

				}

			}
			forward = "podispalybank";
			return mapping.findForward(forward);
		}

		// for party SR page

		if (RegInitConstant.PARTY_SR_PAGE.equals(actionName)) {
			ArrayList dataList = new ArrayList();
			ArrayList FinalList = new ArrayList();
			System.out.println("inside party page ");

			String TempId = (String) session.getAttribute("EfileTempId");
			String EfileTempId = commonBo.getEfileTempId1(TempId);
			regForm.setHidnRegTxnId(TempId);
			// to get deed id
			String dutyId = commonBo.getDutyId1(TempId);
			String deedId = commonBo.getDeedId(dutyId);
			int instId = commonBo.getInstId(dutyId);
			DutyCalculationBO bo = new DutyCalculationBO();
			String propertyFlag = bo.getpropertyFlag(TempId);
			String propValRequried = bo.getpropValRequried(propertyFlag);

			regForm.setDeedId(deedId);
			regForm.setInstID(instId);
			regForm.setPropReqFlag(propValRequried);

			// to get Applicant ID from table

			ArrayList appId = commonBo.getappId(TempId, languageLocale);

			if (appId.size() > 0) {

				for (int i = 0; i < appId.size(); i++) {
					RegCommonDTO a = (RegCommonDTO) appId.get(0);
					regForm.setApplicantTypeEfile(a.getApplicantTypeEfile());
					regForm.setPartyTypeEfile(a.getPartyTypeEfile());
					regForm.setPartyIdEfile(a.getPartyIdEfile());

				}
			}

			regForm.setPartyListSelected(appId);
			regForm.setStatusFlagPO("");
			regForm.setActionName(null);
			forward = "srdisplayparty";
			return mapping.findForward(forward);
		}

		// end of party SR page

		// for party PO page

		if (RegInitConstant.PARTY_PO_PAGE.equals(actionName)) {
			ArrayList dataList = new ArrayList();
			ArrayList FinalList = new ArrayList();
			System.out.println("inside party page ");

			String TempId = (String) session.getAttribute("TempIdPo");
			String EfileTempId = commonBo.getEfileTempId1(TempId);
			regForm.setHidnRegTxnId(EfileTempId);
			// to get deed id
			String dutyId = commonBo.getDutyId(EfileTempId);
			String deedId = commonBo.getDeedId(dutyId);
			int instId = commonBo.getInstId(dutyId);
			DutyCalculationBO bo = new DutyCalculationBO();
			String propertyFlag = bo.getpropertyFlag(EfileTempId);
			String propValRequried = bo.getpropValRequried(propertyFlag);

			regForm.setDeedId(deedId);
			regForm.setInstID(instId);
			regForm.setPropReqFlag(propValRequried);
			// to get Applicant ID from table

			ArrayList appId = commonBo.getappId(EfileTempId, languageLocale);

			if (appId.size() > 0) {

				for (int i = 0; i < appId.size(); i++) {
					// MISReportDTO a = (MISReportDTO) tehsilList.get(0);
					RegCommonDTO a = (RegCommonDTO) appId.get(0);
					regForm.setApplicantTypeEfile(a.getApplicantTypeEfile());
					regForm.setPartyTypeEfile(a.getPartyTypeEfile());
					regForm.setPartyIdEfile(a.getPartyIdEfile());
					regForm.setIdparty(a.getIdparty());

				}
			}

			regForm.setPartyListSelected(appId);
			regForm.setStatusFlagSR("");
			regForm.setStatusFlagPO("");
			regForm.setPopropflag("");
			regForm.setActionName(null);
			forward = "podisplayparty";
			return mapping.findForward(forward);
		}

		// SR Property Page
		if (RegInitConstant.PROPERTY_SR_PAGE.equals(actionName)) {

			ArrayList dataList = new ArrayList();
			ArrayList FinalList = new ArrayList();
			System.out.println("inside party page ");

			String TempId = (String) session.getAttribute("EfileTempId");
			String EfileTempId = commonBo.getEfileTempId1(TempId);

			// to get Applicant ID from table

			ArrayList appId = commonBo.getappId1(TempId, languageLocale);

			if (appId.size() > 0) {

				for (int i = 0; i < appId.size(); i++) {
					// MISReportDTO a = (MISReportDTO) tehsilList.get(0);
					RegCommonDTO a = (RegCommonDTO) appId.get(0);
					regForm.setApplicantTypeEfile1(a.getApplicantTypeEfile1());
					regForm.setPartyTypeEfile1(a.getPartyTypeEfile1());
					regForm.setPartyIdEfile1(a.getPartyIdEfile1());

				}
			}

			if (appId == null || appId.isEmpty()) {
				regForm.setPopropflag1("Y");
			}

			regForm.setPartyListSelected1(appId);
			String status = commonBo.getstautsSR(TempId);

			if (status.equalsIgnoreCase("Approved")) {
				regForm.setStatusFlagSR("Y");
			}

			else if (status.equalsIgnoreCase("Reject")) {
				regForm.setStatusFlagSR("Y");
			}

			else if (status.equalsIgnoreCase("Refer To DR")) {
				regForm.setStatusFlagSR("Y");
			}

			else {
				regForm.setStatusFlagSR("");
			}

			forward = "propertySR";
			return mapping.findForward(forward);

		}
		// end of sr property page

		// PO Property Page
		if (RegInitConstant.PROPERTY_PO_PAGE.equals(actionName)) {
			ArrayList dataList = new ArrayList();
			ArrayList FinalList = new ArrayList();
			System.out.println("inside party page ");

			String TempId = (String) session.getAttribute("TempIdPo");
			String EfileTempId = commonBo.getEfileTempId1(TempId);

			// to get Applicant ID from table

			ArrayList appId = commonBo.getappId3(EfileTempId);

			if (appId.size() > 0) {

				for (int i = 0; i < appId.size(); i++) {
					// MISReportDTO a = (MISReportDTO) tehsilList.get(0);
					RegCommonDTO a = (RegCommonDTO) appId.get(0);
					regForm.setApplicantTypeEfile1(a.getApplicantTypeEfile1());
					regForm.setPartyTypeEfile1(a.getPartyTypeEfile1());
					regForm.setPartyIdEfile1(a.getPartyIdEfile1());

				}
			}

			regForm.setPartyListSelected1(appId);
			regForm.setEfileNumber(TempId);
			if (appId == null || appId.isEmpty()) {
				regForm.setPopropflag("Y");
			}

			String StatusPO = commonBo.getstautsPO(EfileTempId);

			if (StatusPO.equalsIgnoreCase("Reject")) {
				regForm.setStatusFlagPO("Y");
			}

			else if (StatusPO.equalsIgnoreCase("Refer To DR")) {
				regForm.setStatusFlagPO("Y");
			} else if (StatusPO.equalsIgnoreCase("Pending")) {
				regForm.setStatusFlagPO("Y");
			}

			else {
				regForm.setStatusFlagPO("");
			}

			String view = "View";
			regForm.setEfileUploadOrgView(view);

			forward = "propertyPO";
			return mapping.findForward(forward);

		}
		// end of po property page

		// SR Dashboard Submit Reject PAGE

		if (RegInitConstant.SR_SUBMIT_REJECT1.equals(actionName)) {
			regForm.setPropImage5DocumentName("");
			forward = "srdisplayReject1";
			return mapping.findForward(forward);
		}

		if (RegInitConstant.SR_SUBMIT_REJECT.equals(actionName)) {
			String TempRejId = (String) session.getAttribute("TempId");
			String TempId = commonBo.getefileId(TempRejId);
			boolean status = false;

			status = commonBo.changeStatus(TempRejId);
			String saveFilePath = (String) session.getAttribute("pathfile");
			String remarks = regForm.getRemarks();
			String srID = (String) session.getAttribute("UserId");

			boolean finalSubmit = false;
			finalSubmit = commonBo.finalSubmitReject(TempId, TempRejId, saveFilePath, remarks, srID);

			if (status == true) {

				// to resolve bug 8 july 2016

				ArrayList pendingApplicationList = commonBo.getPendingEfileApps(session.getAttribute("UserId").toString(), languageLocale);

				regForm.setPendingEfileApplicationList(pendingApplicationList);

				// end
				if (languageLocale.equalsIgnoreCase("en"))
					request.setAttribute(RegInitConstant.SUCCESS_MSG, "Successfully Rejected.");
				else
					request.setAttribute(RegInitConstant.SUCCESS_MSG, "सफलतापूर्वक अस्वीकृत किया गया है");
				// eForm.getDutyCalculationDTO().setEstampFlag("Y");
				forward = "srdisplayReject";
				return mapping.findForward(forward);
			}

			else {
				// session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
				if (languageLocale.equalsIgnoreCase("en"))
					request.setAttribute(RegInitConstant.FAIL_MSG, "Application Not Rejected.Please Try Again");
				else
					request.setAttribute(RegInitConstant.FAIL_MSG, "आवेदन अस्वीकृत नहीं है। कृपया पुनः प्रयास करें");
				forward = "srdisplayReject";
				return mapping.findForward(forward);
			}

		}
		if (RegInitConstant.SR_REFER_DR.equals(actionName)) {
			regForm.setPropImage2DocumentName("");
			forward = "srdisplayReferToDRRemarks";
			return mapping.findForward(forward);
		}
		// SR Dashboard Submit Refer to DR PAGE
		if (RegInitConstant.SR_SUBMIT_REFER_DR.equals(actionName)) {
			String efileId = (String) session.getAttribute("TempId");
			String remarks = regForm.getRemarks();
			String srID = session.getAttribute("UserId").toString();
			boolean status = false;
			status = commonBo.finalReferDR(efileId, remarks, srID);

			ArrayList pendingApplicationList = commonBo.getPendingEfileApps(session.getAttribute("UserId").toString(), languageLocale);

			regForm.setPendingEfileApplicationList(pendingApplicationList);

			if (languageLocale.equalsIgnoreCase("en")) {
				request.setAttribute(RegInitConstant.SUCCESS_DR_MSG, "Application is Under Case Monitoring");
			} else {

				request.setAttribute(RegInitConstant.SUCCESS_DR_MSG, "आवेदन मामला निगरानी के अधीन है");
			}
			forward = "srdisplayReferToDR";
			return mapping.findForward(forward);

		}

		// end of

		// SR Dashboard Approve
		if (RegInitConstant.SR_SUBMIT_APPROVED.equals(actionName)) {
			regForm.setPropImage2DocumentName("");
			forward = "srdisplayApprove";
			regForm.setRemarks("");
			return mapping.findForward(forward);
		}

		// end

		// upload pdf in san

		if (RegInitConstant.UPLOAD_FILE_ANGLE_SR.equals(actionName)) {
			System.out.println("inside upload method of action ");

			// String efileTxnId= (String) session.getAttribute("regTxnId");
			String efileTxnId = (String) session.getAttribute("TempId");
			String filePath;
			String path = "";

			FormFile uploadedFile = regForm.getPropImage1();
			// to check file size
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			if (fileSizeInKB > CommonConstant.MAX_FILE_SIZE) {
				request.setAttribute(RegInitConstant.lbl_reg_init_upload_msg3, "File size is Greater than 500 KB. Please select another file. / फ़ाइल का माप 500 KB से अधिक है। अन्य फाइल चुनें।");
				regForm.setFileSizeUploadError("Y");
				forward = "srUploadSuccess";
				return mapping.findForward(forward);
			}
			// check for file extension
			MIMECheck mimeCheck = new MIMECheck();
			String fileExt = mimeCheck.getFileExtension(uploadedFile.getFileName());
			Boolean mime = mimeCheck.validateFileTypeEfile(fileExt);

			// end of file extension

			if (mime) {
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				path = pr.getValue("upload.location");
				filePath = path + RegInitConstant.FILE_UPLOAD_PATH_SR + efileTxnId;

				session.setAttribute("filePath", filePath);

				File folder = new File(filePath);
				String fileName = RegInitConstant.FILE_UPLOAD_NAME_SR;
				regForm.setPropImage2DocumentName(fileName);

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
						if (languageLocale.equalsIgnoreCase("en"))
							request.setAttribute(RegInitConstant.SUCCESS_MSG_UPLOAD, "Successfully Uploaded.");
						else
							request.setAttribute(RegInitConstant.SUCCESS_MSG_UPLOAD, "सफलतापूर्वक अपलोड की गई");
						forward = "srUploadSuccess";
					}

					else {
						// session.setAttribute("checkStatus",
						// Constants.PROBLEM_IN_AS);
						if (languageLocale.equalsIgnoreCase("en"))
							request.setAttribute(RegInitConstant.FAILURE_MSG_UPLOAD, "Not Uploaded Successfully");
						else
							request.setAttribute(RegInitConstant.FAILURE_MSG_UPLOAD, "सफलतापूर्वक अपलोड नहीं किया गया");
						forward = "srUploadSuccess";
					}

					return mapping.findForward(forward);

				} catch (Exception ex) {

					ex.printStackTrace();
				}
			} else {
				regForm.setFileError("Y");
				if (languageLocale.equalsIgnoreCase("en"))
					request.setAttribute(RegInitConstant.ERRORFILETYPE_MSG_UPLOAD, "Please Upload a Valid File.File should be in PDF Format.");
				else
					request.setAttribute(RegInitConstant.ERRORFILETYPE_MSG_UPLOAD, "कृपया एक मान्य फाइल अपलोड करें। फाइल पीडीएफ प्रारूप में होनी चाहिए।");
				forward = "srUploadSuccess";
			}
		}

		// Added By Gulrej on 27th july, 2017

		if (RegInitConstant.UPLOAD_FILE_ANGLE_DR.equals(actionName)) {
			System.out.println("inside upload method of action ");

			// String efileTxnId= (String) session.getAttribute("regTxnId");
			String efileTxnId = (String) session.getAttribute("TempId");
			String filePath;
			String path = "";

			FormFile uploadedFile = regForm.getPropImage1();
			// to check file size
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			if (fileSizeInKB > CommonConstant.MAX_FILE_SIZE) {
				request.setAttribute(RegInitConstant.lbl_reg_init_upload_msg3, "File size is Greater than 500 KB. Please select another file. / फ़ाइल का माप 500 KB से अधिक है। अन्य फाइल चुनें।");
				regForm.setFileSizeUploadError("Y");
				forward = "srdisplayReferToDRRemarks";
				return mapping.findForward(forward);
			}
			// check for file extension
			MIMECheck mimeCheck = new MIMECheck();
			String fileExt = mimeCheck.getFileExtension(uploadedFile.getFileName());
			Boolean mime = mimeCheck.validateFileTypeEfile(fileExt);

			// end of file extension

			if (mime) {
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				path = pr.getValue("upload.location");
				filePath = path + RegInitConstant.FILE_UPLOAD_PATH_SR + efileTxnId;

				session.setAttribute("filePath", filePath);

				File folder = new File(filePath);
				String fileName = RegInitConstant.FILE_UPLOAD_NAME_SR;
				regForm.setPropImage2DocumentName(fileName);

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
						if (languageLocale.equalsIgnoreCase("en"))
							request.setAttribute(RegInitConstant.SUCCESS_MSG_UPLOAD, "Successfully Uploaded.");
						else
							request.setAttribute(RegInitConstant.SUCCESS_MSG_UPLOAD, "सफलतापूर्वक अपलोड की गई");
						forward = "srdisplayReferToDRRemarks";
					}

					else {
						// session.setAttribute("checkStatus",
						// Constants.PROBLEM_IN_AS);
						if (languageLocale.equalsIgnoreCase("en"))
							request.setAttribute(RegInitConstant.FAILURE_MSG_UPLOAD, "Not Uploaded Successfully");
						else
							request.setAttribute(RegInitConstant.FAILURE_MSG_UPLOAD, "सफलतापूर्वक अपलोड नहीं किया गया");
						forward = "srdisplayReferToDRRemarks";
					}

					return mapping.findForward(forward);

				} catch (Exception ex) {

					ex.printStackTrace();
				}
			} else {
				regForm.setFileError("Y");
				if (languageLocale.equalsIgnoreCase("en"))
					request.setAttribute(RegInitConstant.ERRORFILETYPE_MSG_UPLOAD, "Please Upload a Valid File.File should be in PDF Format.");
				else
					request.setAttribute(RegInitConstant.ERRORFILETYPE_MSG_UPLOAD, "कृपया एक मान्य फाइल अपलोड करें। फाइल पीडीएफ प्रारूप में होनी चाहिए।");
				forward = "srdisplayReferToDRRemarks";
			}
		}

		// sr upload reject
		if (RegInitConstant.UPLOAD_FILE_ANGLE_REJECT.equals(actionName)) {
			String efileTxnId = (String) session.getAttribute("TempId");
			String filePath;
			String path = "";

			FormFile uploadedFile = regForm.getPropImage1();
			// to check file size
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			if (fileSizeInKB > CommonConstant.MAX_FILE_SIZE) {
				request.setAttribute(RegInitConstant.lbl_reg_init_upload_msg3, "File size is Greater than 500 KB. Please select another file. / फ़ाइल का माप 500 KB से अधिक है। अन्य फाइल चुनें।");
				regForm.setFileSizeUploadError("Y");
				forward = "srdisplayReject1";
				return mapping.findForward(forward);
			}
			// check for file extension
			MIMECheck mimeCheck = new MIMECheck();
			String fileExt = mimeCheck.getFileExtension(uploadedFile.getFileName());
			Boolean mime = mimeCheck.validateFileTypeEfile(fileExt);

			// end of file extension

			if (mime) {
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				path = pr.getValue("upload.location");
				filePath = path + RegInitConstant.FILE_UPLOAD_PATH_SR + efileTxnId;

				session.setAttribute("filePath", filePath);

				File folder = new File(filePath);
				String fileName = RegInitConstant.FILE_UPLOAD_NAME_SR_REJECT;
				regForm.setPropImage5DocumentName(fileName);

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
						if (languageLocale.equalsIgnoreCase("en"))
							request.setAttribute(RegInitConstant.SUCCESS_MSG_UPLOAD, "Successfully Uploaded.");
						else
							request.setAttribute(RegInitConstant.SUCCESS_MSG_UPLOAD, "कोई रिकॉर्ड  मिला");
						forward = "srdisplayReject1";
					}

					else {
						// session.setAttribute("checkStatus",
						// Constants.PROBLEM_IN_AS);
						if (languageLocale.equalsIgnoreCase("en"))
							request.setAttribute(RegInitConstant.FAILURE_MSG_UPLOAD, "Not Uploaded Successfully");
						else
							request.setAttribute(RegInitConstant.FAILURE_MSG_UPLOAD, "सफलतापूर्वक अपलोड नहीं किया गया");
						forward = "srdisplayReject1";
					}

					return mapping.findForward(forward);

				} catch (Exception ex) {

					ex.printStackTrace();
				}
			} else {
				regForm.setFileError("Y");
				if (languageLocale.equalsIgnoreCase("en"))
					request.setAttribute(RegInitConstant.ERRORFILETYPE_MSG_UPLOAD, "Please Upload a Valid File.File should be in PDF Format.");
				else
					request.setAttribute(RegInitConstant.ERRORFILETYPE_MSG_UPLOAD, "कोई रिकॉर्ड  मिला");
				forward = "srdisplayReject1";
			}
		}

		// final submit by SR to approve

		if (RegInitConstant.SR_FINAL_SUBMIT.equals(actionName)) {

			String efileId = (String) session.getAttribute("TempId");
			String saveFilePath = (String) session.getAttribute("pathfile");
			String remarks = regForm.getRemarks();
			String srID = (String) session.getAttribute("UserId");

			boolean finalSubmit = false;
			finalSubmit = commonBo.finalSubmit(efileId, saveFilePath, remarks, srID);

			if (finalSubmit == true) {

				ArrayList pendingApplicationList = commonBo.getPendingEfileApps(session.getAttribute("UserId").toString(), languageLocale);

				regForm.setPendingEfileApplicationList(pendingApplicationList);

				if (languageLocale.equalsIgnoreCase("en"))
					request.setAttribute(RegInitConstant.SUCCESS_MSG_FINAL_SR, "Successfully Approved.");
				else
					request.setAttribute(RegInitConstant.SUCCESS_MSG_FINAL_SR, "सफलतापूर्वक स्वीकृत किया गया है");
				// eForm.getDutyCalculationDTO().setEstampFlag("Y");
				forward = "dashboardSuccess";
			}

			else {
				// session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
				if (languageLocale.equalsIgnoreCase("en"))
					request.setAttribute(RegInitConstant.FAILURE_MSG_FINAL_SR, "Not Approved.Please Try Again ");
				else
					request.setAttribute(RegInitConstant.FAILURE_MSG_FINAL_SR, "स्वीकृत नहीं किया गया है। कृपया पुनः प्रयास करें।");
				forward = "dashboardSuccess";
			}
			return mapping.findForward(forward);

		}
		// END
		// NEXT TO CONFIRMATION SCREEN
		if (request.getParameter("confirmation") != null) {
			if (request.getParameter("confirmation").equalsIgnoreCase("Y")) {

				regForm.setActionName("");
				String[] param = commonBo.getConsenterFlag(regForm.getHidnRegTxnId());
				regForm.setConsenterFlag("Y");
				regForm.setConsenterWithConsidFlag(param[0]);
				// regForm.setConsenterCount(Integer.parseInt(param[1]));

				if (regForm.getPropReqFlag().equalsIgnoreCase("Y") && regForm.getConsenterFlag().equalsIgnoreCase("Y")) {
					boolean updateStatus = commonBo.updateConsenterFlag(RegInitConstant.CONSENTER_IN_PROGRESS, regForm.getHidnRegTxnId(), "Y", regForm.getConsenterWithConsidFlag());
					if (updateStatus) {
						forward = "reginitConsenter";
						regForm.setListDto(new ArrayList());
						regForm.setRegDTO(new RegCompleteMakerDTO());
						regForm.setConsenterAddedCount(0);
						commonDto.setIdProf(commonBo.getIdProf(languageLocale));
						commonDto.setState(commonBo.getState("1", languageLocale));
						commonDto.setDistrict(new ArrayList());
						commonDto.setRelationshipList(commonBo.getRelationshipList(null, languageLocale));
						return mapping.findForward(forward);
					} else {
						logger.debug("unable to update reg status for consenter.");
						forward = "failure";

					}
				} else {
					forward = "reginitConfirm";
					request.setAttribute("deedId", regForm.getDeedID());
					request.setAttribute("instId", regForm.getInstID());
					commonBo.landConfirmationScreen(regForm, languageLocale, request);
					return mapping.findForward(forward);
				}
			}
		}

		if (request.getParameter("label") != null) {

			if (request.getParameter("label").equalsIgnoreCase("displayConsenter")) {

				if (request.getParameter("fromPage") != null && request.getParameter("fromPage").toString().equalsIgnoreCase("consenterDetls")) {
					regForm.setConfirmationFlag("00");
				} else {
					regForm.setConfirmationFlag("01");
				}

				regForm.setPartyModifyFlag(0);
				commonBo.openConsenterView(request, regForm, languageLocale);

				forward = "displayConsenterDetls";
			}

			if (request.getParameter("label").equalsIgnoreCase("displayDuties")) {

				commonBo.getDutiesView(regForm, languageLocale);

				forward = "dutyView";
			}
			if (request.getParameter("label").equalsIgnoreCase("displayConsenterDuties")) {

				commonBo.getConsenterDutiesView(regForm, languageLocale);

				forward = "displayConsenterDuties";
			}
			if (request.getParameter("label").equalsIgnoreCase("displayParty")) {

				regForm.setPartyModifyFlag(0);
				regForm.setOwnerModifyFlag(0);
				commonBo.openPartyView(request, regForm, languageLocale);

				forward = "displayRegDetls";
			}
			if (request.getParameter("label").equalsIgnoreCase("displayProperty")) {

				String confirmationFlag = null;
				confirmationFlag = (String) request.getParameter("confirmationFlag");
				if (confirmationFlag != null && confirmationFlag != "") {
					if (confirmationFlag.equalsIgnoreCase("false")) {
						regForm.setConfirmationFlag("00");
					} else {
						regForm.setConfirmationFlag("01");
					}
				} else {
					regForm.setConfirmationFlag("01");
				}
				String propertyId = "";
				if (request.getParameter("key") != null)
					propertyId = request.getParameter("key");
				else if (request.getAttribute("key") != null)
					propertyId = (String) request.getAttribute("key");

				regForm.setPropertyId(propertyId);
				regForm.setMapPropertyTransPartyDisp(commonBo.getPropertyDetsForViewConfirm(regForm.getHidnRegTxnId(), propertyId, languageLocale));
				forward = "propertyView";
			}
			if (request.getParameter("label").equalsIgnoreCase("displayExtra")) {

				commonBo.getExtraDetls(regForm, languageLocale);
				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());
				logger.debug("request deed ---->" + request.getAttribute("deedId"));
				regForm.setCallingAction("regInit.do?TRFS=NGI");
				regForm.setExtraDetlsModifyFlag(0);
				forward = "displayExtraDetls";
			}
			if (request.getParameter("label").equalsIgnoreCase("fromAdjudication")) {
				forward = "searchAdju";
			}
		}

		if (RegInitConstant.PRINT_ACTION.equalsIgnoreCase(actionName) || RegInitConstant.PRINT_ACTION_FINAL.equalsIgnoreCase(actionName))

		{
			regForm.setActionName("");
			if (RegInitConstant.PRINT_ACTION.equalsIgnoreCase(actionName)) {
				commonBo.printToPdfJasper(regForm, request, response, languageLocale, "N");
				regForm.setRegInitEstampCode(null);
			} else {
				commonBo.printToPdfJasper(regForm, request, response, languageLocale, "Y");
			}

			commonDto.setIndcountry(commonBo.getCountry(languageLocale));
			commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
			commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(), languageLocale));

			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			forward = regForm.getJspName();

		}
		if (RegInitConstant.MODIFY_PARTY_ACTION.equalsIgnoreCase(actionName))

		{
			actionName = "";
			regForm.setActionName(RegInitConstant.NO_ACTION);
			commonBo.getPartyDetsForViewConfirmModify(regForm, languageLocale);

			commonDto.setIdProfTrns(commonBo.getIdProf(languageLocale));
			// commonDto.setCategoryList(commonBo.getCategoryList());
			commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
			commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
			commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));
			// regForm.setFnameTrns("Roopam");
			// regForm.setActionName(RegInitConstant.NO_ACTION);
			commonDto.setDistrictTrns(commonBo.getDistrict("1", languageLocale));
			if (regForm.getInddistrictTrns() != null && !regForm.getInddistrictTrns().isEmpty()) {
				commonDto.setTehsilTrns(commonBo.getTehsildrop(regForm.getInddistrictTrns(), languageLocale));
			} else {
				commonDto.setTehsilTrns(commonBo.getTehsildrop(regForm.getDistrictTrns(), languageLocale));
			}

			if (regForm.getAuthPerDistrictTrns() != null && !regForm.getAuthPerDistrictTrns().isEmpty()) {
				commonDto.setTehsilTrns1(commonBo.getTehsildrop1(regForm.getAuthPerDistrictTrns()));
			}

			commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
			regForm.setPartyModifyFlag(1);
			regForm.setOwnerModifyFlag(1);
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			request.setAttribute("roleId", regForm.getPartyType());

			String[] claimantArr = commonBo.getClaimantFlag(regForm.getPartyType());
			regForm.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
			regForm.setPoaHolderFlag(Integer.parseInt(claimantArr[1].trim()));

			forward = "displayRegDetls";

		}
		if (RegInitConstant.SAVE_PARTY_ACTION.equalsIgnoreCase(actionName))

		{
			String path = "";

			try {
				PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");

				path = prop.getValue("upload.location");
			} catch (Exception e) {
				logger.debug("exception in uploadFile : " + e);
				logger.debug(e.getMessage(), e);
			}

			regForm.setActionName(RegInitConstant.NO_ACTION);
			regForm.setPartyModifyFlag(0);
			regForm.setOwnerModifyFlag(0);
			boolean checkAdditionalUploads = true;
			path = path + RegInitConstant.FILE_UPLOAD_PATH + regForm.getHidnRegTxnId() + RegInitConstant.FILE_UPLOAD_PATH_PARTY + regForm.getPartyTxnId() + "/";
			try {

				// By Mohit
				File f = new File(path);
				FileUtils.cleanDirectory(f);
				// commonBo.deleteAllRemovedUploads(regForm.getHidnRegTxnId(),
				// regForm.getPartyTxnId());
			} catch (Exception e) {
				request.setAttribute("error", "Unable to clean directory.");
				logger.debug(e.getMessage(), e);
				// checkAdditionalUploads = true;
			} finally {
				try {
					checkAdditionalUploads = true;
					commonBo.deleteAllRemovedUploads(regForm.getHidnRegTxnId(), regForm.getPartyTxnId());
				} catch (Exception e) {
					request.setAttribute("error", "Unable to delete records.");
					logger.debug(e.getMessage(), e);
					checkAdditionalUploads = false;
				}
			}
			// below write code for saving modified party details
			if (checkAdditionalUploads) {
				ArrayList<CommonDTO> listDto = regForm.getListDto();
				if (listDto != null && listDto.size() > 0) {

					if (checkAdditionalUploads) {
						for (int i = 0; i < listDto.size(); i++) {
							CommonDTO dto = listDto.get(i);
							dto.setDocumentName(commonBo.getNewFileName(dto.getDocumentName(), Integer.toString(i)));
							String filepath = uploadFile(regForm.getHidnRegTxnId(), dto.getDocContents(), regForm.getPartyTxnId(), "01", dto.getDocumentName());
							if (filepath != null) {
								checkAdditionalUploads = true;
								dto.setDocumentPath(filepath);
							} else {
								checkAdditionalUploads = false;
								break;
							}
						}

					}
					if (checkAdditionalUploads) {
						regForm.setPartyRoleTypeId(regForm.getPartyTxnId());
						checkAdditionalUploads = commonBo.insertAdditionalUploads(regForm);

					}

				} else {
					checkAdditionalUploads = true;
				}
			}
			// mohit
			if (checkAdditionalUploads) {
				boolean allUploadsUpdated = false;
				boolean allDetailsUpdated = false;

				String filePath;
				String filePathPhotoProof;
				String filePathNotAffExec;
				String filePathExecPhoto;
				String filePathNotAffAttrn;
				String filePathAttrnPhoto;
				String filePathClaimPhoto;
				String filePathPanForm60;

				regForm.setU2FilePathTrns("");
				regForm.setU3FilePathTrns("");
				regForm.setU10FilePathTrns("");
				regForm.setU6FilePathTrns("");
				regForm.setU11FilePathTrns("");
				regForm.setU9FilePathTrns("");
				regForm.setFilePathTrns("");

				int roleId = Integer.parseInt(regForm.getPartyTypeTrns());

				String[] claimantArr = commonBo.getClaimantFlag(Integer.toString(roleId));
				int claimantFlag = Integer.parseInt(claimantArr[0].trim());

				if (!regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID))

				{

					if (!regForm.getU2DocumentNameTrns().equalsIgnoreCase("")) {
						filePathPhotoProof = uploadFile(regForm.getHidnRegTxnId(), regForm.getU2DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU2PartyOrPropTrns(), regForm.getU2UploadTypeTrns());
					} else {
						filePathPhotoProof = "";

					}

					if (filePathPhotoProof != null) {
						regForm.setU2FilePathTrns(filePathPhotoProof);
						// BELOW CODE FOR EXECUTANT
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {
							if (regForm.getU3DocumentNameTrns() != null && !regForm.getU3DocumentNameTrns().equalsIgnoreCase("")) {
								filePathNotAffExec = uploadFile(regForm.getHidnRegTxnId(), regForm.getU3DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU3PartyOrPropTrns(), regForm.getU3UploadTypeTrns());
							} else {
								filePathNotAffExec = "";
							}
							if (filePathNotAffExec != null) {
								regForm.setU3FilePathTrns(filePathNotAffExec);

								if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) // for
								// individual
								{
									if (!regForm.getListIDTrns().equalsIgnoreCase("4") && (regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU10DocumentNameTrns() != null && !regForm.getU10DocumentNameTrns().equalsIgnoreCase("")))) {

										filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), regForm.getU10DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU10PartyOrPropTrns(), regForm.getU10UploadTypeTrns());

										if (filePathPanForm60 != null) {
											regForm.setU10FilePathTrns(filePathPanForm60);
											allUploadsUpdated = true;
										} else {
											allUploadsUpdated = false;
										}

									} else {
										allUploadsUpdated = true;
									}

								} else { // for organization

									if ((regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU10DocumentNameTrns() != null && !regForm.getU10DocumentNameTrns().equalsIgnoreCase("")))) {

										filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), regForm.getU10DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU10PartyOrPropTrns(), regForm.getU10UploadTypeTrns());

										if (filePathPanForm60 != null) {
											regForm.setU10FilePathTrns(filePathPanForm60);
											allUploadsUpdated = true;
										} else {
											allUploadsUpdated = false;
										}

									} else {
										allUploadsUpdated = true;
									}

								}
							} else {
								allUploadsUpdated = false;
							}
						}
						// BELOW CODE FOR EXECUTANT POA HOLDER
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2) {

							filePathAttrnPhoto = uploadFile(regForm.getHidnRegTxnId(), regForm.getU6DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU6PartyOrPropTrns(), regForm.getU6UploadTypeTrns());

							if (filePathAttrnPhoto != null) {
								regForm.setU6FilePathTrns(filePathAttrnPhoto);

								allUploadsUpdated = true;
							} else {
								allUploadsUpdated = false;
							}
							// }

							// }

						}
						// BELOW CODE FOR CLAIMANT
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {

							if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) // for
							// individual
							{
								if (!regForm.getListID().equalsIgnoreCase("4") && (regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU11DocumentNameTrns() != null && !regForm.getU11DocumentNameTrns().equalsIgnoreCase("")))) {

									filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), regForm.getU11DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU11PartyOrPropTrns(), regForm.getU11UploadTypeTrns());

									if (filePathPanForm60 != null) {
										regForm.setU11FilePathTrns(filePathPanForm60);
										allUploadsUpdated = true;
									} else {
										allUploadsUpdated = false;
									}

								} else {
									allUploadsUpdated = true;
								}

							} else {

								if ((regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU11DocumentNameTrns() != null && !regForm.getU11DocumentNameTrns().equalsIgnoreCase("")))) {

									filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), regForm.getU11DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU11PartyOrPropTrns(), regForm.getU11UploadTypeTrns());

									if (filePathPanForm60 != null) {
										regForm.setU11FilePathTrns(filePathPanForm60);
										allUploadsUpdated = true;
									} else {
										allUploadsUpdated = false;
									}

								} else {
									allUploadsUpdated = true;
								}

							}
						}
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4) {

							filePathAttrnPhoto = uploadFile(regForm.getHidnRegTxnId(), regForm.getU9DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU9PartyOrPropTrns(), regForm.getU9UploadTypeTrns());

							if (filePathAttrnPhoto != null) {
								regForm.setU9FilePathTrns(filePathAttrnPhoto);
								allUploadsUpdated = true;

							} else {
								allUploadsUpdated = false;
							}
						}

						if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
							if (regForm.getIndCategoryTrns().equalsIgnoreCase("1") && regForm.getIndScheduleAreaTrns().equalsIgnoreCase("N")) {
								filePath = uploadFile(regForm.getHidnRegTxnId(), regForm.getDocContentsTrns(), regForm.getPartyTxnId(), regForm.getPartyOrPropTrns(), regForm.getUploadTypeTrns());
								if (filePath != null) {
									regForm.setFilePathTrns(filePath);
									allUploadsUpdated = true;
								} else {
									allUploadsUpdated = false;
								}
							}
						}
					}

				} else {
					allUploadsUpdated = true;
				}

				if (allUploadsUpdated) {

					allDetailsUpdated = commonBo.updateTransPartyDetails(regForm); // update
																					// in
																					// db
					// anuj
					logger.debug("transacting party details updation status---->" + allDetailsUpdated);
				}

				commonBo.openPartyView(request, regForm, languageLocale);
				regForm.setPartyModifyFlag(0);
				regForm.setOwnerModifyFlag(0);
				commonDto.setIdProfTrns(commonBo.getIdProf(languageLocale));
				commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
				commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				commonDto.setDistrictTrns(commonBo.getDistrict("1", languageLocale));// state
																						// id
																						// passed
																						// is
																						// for
																						// MP
				commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
			} else {
				request.setAttribute("error", "Unable To Save the Document");
			}
			forward = "displayRegDetls";

		}
		if (RegInitConstant.SUBMIT_FORM_ACTION.equalsIgnoreCase(actionName))

		{
			logger.debug("category value----------->" + regForm.getIndCategoryTrns());
			regForm.setActionName(RegInitConstant.NO_ACTION);
			commonDto.setDistrictTrns(commonBo.getDistrict("1", languageLocale));
			commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
			regForm.setPartyModifyFlag(1);
			regForm.setOwnerModifyFlag(1);
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			request.setAttribute("roleId", regForm.getPartyType());
			forward = regForm.getJspName();

		}

		if (RegInitConstant.MODIFY_PROPERTY_ACTION.equalsIgnoreCase(actionName))

		{

			regForm.setPropertyModifyFlag(1);

			forward = "modifyPropDetls";
			request.setAttribute("regInitForm", regForm);

		}
		if (RegInitConstant.MODIFY_EXTRA_DETLS_ACTION.equalsIgnoreCase(actionName))

		{
			commonDto.setIndcountry(commonBo.getCountry(languageLocale));
			commonDto.setIdProf(commonBo.getIdProf(languageLocale));
			commonDto.setDeedType(commonBo.getDeedType(languageLocale));
			commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
			commonDto.setIndstate(commonBo.getState("1", languageLocale));
			commonDto.setInddistrict(commonBo.getDistrict("1", languageLocale));

			if (regForm.getIndDisabilityDescArb().equalsIgnoreCase("-")) {
				regForm.setIndDisabilityDescArb("");
			}
			if (regForm.getMnameArb().equalsIgnoreCase("-")) {
				regForm.setMnameArb("");
			}
			if (regForm.getSpouseNameArb().equalsIgnoreCase("-")) {
				regForm.setSpouseNameArb("");
			}
			if (regForm.getMotherNameArb().equalsIgnoreCase("-")) {
				regForm.setMotherNameArb("");
			}
			if (regForm.getIndphnoArb().equalsIgnoreCase("-")) {
				regForm.setIndphnoArb("");
			}
			if (regForm.getEmailIDArb().equalsIgnoreCase("-")) {
				regForm.setEmailIDArb("");
			}

			regForm.setActionName(RegInitConstant.NO_ACTION);

			regForm.setExtraDetlsModifyFlag(1);
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			regForm.setCallingAction("regInit.do?TRFS=NGI");
			forward = "displayExtraDetls";

		}
		if (RegInitConstant.SAVE_EXTRA_DETLS_ACTION.equalsIgnoreCase(actionName)) {
			regForm.setActionName(RegInitConstant.NO_ACTION);
			regForm.setExtraDetlsModifyFlag(0);
			// below write code for saving modified extra details
			boolean allDetailsUpdated = false;

			allDetailsUpdated = commonBo.updateBankDetails(regForm);
			logger.debug("extra details updation status---->" + allDetailsUpdated);

			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());

			regForm.setCallingAction("regInit.do?TRFS=NGI");
			commonBo.getExtraDetls(regForm, languageLocale);
			forward = "displayExtraDetls";

		}
		if (RegInitConstant.EXTRA_DETLS_NO_ACTION.equalsIgnoreCase(actionName)) {
			regForm.setActionName(RegInitConstant.NO_ACTION);
			regForm.setCallingAction("regInit.do?TRFS=NGI");

			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			forward = "displayExtraDetls";

		}

		if (RegInitConstant.POSTAL_CHECK_ACTION.equalsIgnoreCase(actionName))

		{

			forward = "success1";

		}
		if (RegInitConstant.COPY_APPLICANT_ADDRESS_ACTION.equalsIgnoreCase(actionName))

		{

			commonBo.copyPostalAddress(regForm);
			commonDto.setIndcountry(commonBo.getCountry(languageLocale));
			commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
			commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(), languageLocale));

			forward = "success1";

		}

		// if(request.getParameter("label")==null){
		if (regForm.getDeedID() != 0) {
			request.setAttribute("deedId", regForm.getDeedID());
		} else {
			request.setAttribute("deedId", 0);
		}
		if (regForm.getInstID() != 0) {
			request.setAttribute("instId", regForm.getInstID());
		} else {
			request.setAttribute("instId", 0);
		}

		if (regForm.getPartyType() != null && !regForm.getPartyType().equalsIgnoreCase("")) {
			request.setAttribute("roleId", regForm.getPartyType());
		} else {
			request.setAttribute("roleId", 0);
		}
		if (regForm.getPartyTypeTrns() != null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")) {
			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
		} else {
			request.setAttribute("roleIdTrns", 0);
		}
		// }

		regForm.setCommonDto(commonDto);

		request.setAttribute("reginit", regForm);

		logger.debug("deed id request attribute-->" + request.getAttribute("deedId"));
		// for creating dashboard of PO where pending initate application can be
		// viewed
		if (request.getParameter("modName") != null) {
			if (request.getParameter("modName").equalsIgnoreCase("Registration ProcessEfile")) {
				System.out.println("inside creating resume application function ");
				regForm.setDurationFrom("");
				regForm.setDurationTo("");
				regForm.setEfileNumber("");
				regForm.setExtraflag("");
				regForm.setExtraflag1("");
				ArrayList pendingInitateApplicationListPo = commonBo.getPendingEfileInitate(session.getAttribute("UserId").toString(), languageLocale);
				if (pendingInitateApplicationListPo.size() == 0)
					regForm.setPendingInitateApplicationListPo(new ArrayList());
				else
					regForm.setPendingInitateApplicationListPo(pendingInitateApplicationListPo);

				request.setAttribute("pendingInitateApplicationListPo", regForm.getPendingInitateApplicationListPo());
				forward = "InitateDashboardPO";
				return mapping.findForward(forward);

			}
		}
		// after click on any pending application id from dashboard
		if (request.getParameter("regStatus") != null) {
			if (request.getParameter("regStatus").equalsIgnoreCase(RegInitConstant.STATUS_TRNS_BANK1)) {
				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());
				forward = "success";
				return mapping.findForward(forward);

			}
		}
		if (request.getParameter("hdnApplicationIdInitate") != null) {
			try {
				utilities.cancelAction(session, regForm);

				regForm.setHidnRegTxnId(request.getParameter("hdnApplicationIdInitate"));
				regForm.setHidnUserId(userId);
				// String appStatus[] = new String[5];

				// FOR GETTING REGISTRATION STATUS FROM DB
				regForm.setPhysicalStampFlagPo("");
				regForm.setEstamppayflag("");
				String appStatus = "";
				String appFlag = "";
				String[] appStatus1 = commonBo.getPendingAppStatus(regForm.getHidnRegTxnId());

				appStatus = appStatus1[0].trim();

				logger.debug("pending app status from db------------>" + appStatus);
				regForm.setAppStatusEfile(appStatus);
				regForm.setAppStatus(appStatus);

				if (appStatus != null && !appStatus.equalsIgnoreCase("") && !appStatus.equalsIgnoreCase("null")) {

					System.out.println(regForm.getHidnRegTxnId());

					regForm.setFromAdjudication(0);
					// session.setAttribute("fnName","Initiation");
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_ENGLISH);
					} else {
						session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_HINDI);
					}

					String[] deedInstArray = commonBo.getDeedInstId(regForm.getHidnRegTxnId());// get
																								// pan
																								// flag
																								// from
																								// db
					regForm.setTransCheck(deedInstArray[7].trim());
					regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));

					regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
					if (deedInstArray[2].trim().equals("-") || deedInstArray[2].trim().equals("")) {
						regForm.setExmpID("");
						regForm.setHdnExAmount("");
					} else {
						regForm.setExmpID(deedInstArray[2].trim());
						regForm.setHdnExAmount(deedInstArray[2].trim());
					}

					if (deedInstArray[5] != null) {
						regForm.setDuty_txn_id(Integer.parseInt(deedInstArray[5].trim()));
					} else {
						regForm.setDuty_txn_id(0);
					}
					String flags[] = commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));
					if (flags != null && flags[0] != null && flags[1] != null && flags[2] != null) {

						if (flags[2].trim().equalsIgnoreCase("Y")) {
							regForm.setCommonDeed(1);
						} else {
							regForm.setCommonDeed(0);
						}

						regForm.setPvReqFlag(flags[1].trim());
						regForm.setPropReqFlag(flags[0].trim());

					} else {
						regForm.setCommonDeed(0);
						regForm.setPvReqFlag(flags[1].trim());
						regForm.setPropReqFlag(flags[0].trim());
					}

					regForm.setDeedtype1(commonBo.getDeedName(Integer.toString(regForm.getDeedID()), languageLocale));
					regForm.setInstType(commonBo.getInstrumentName(Integer.toString(regForm.getInstID()), languageLocale));

					// below code for exemptions
					regForm.setSelectedExemptionList(commonBo.getExemptionList(Integer.toString(regForm.getDuty_txn_id()), languageLocale));
					regForm.setFamilyFlag(commonBo.getFamilyFlag(Integer.toString(regForm.getDuty_txn_id())));

					if (appStatus.equalsIgnoreCase(RegInitConstant.STATUS_APP_SAVED)) {
						commonBo.landTransactingPartyScreen(regForm, appStatus, languageLocale);

						if (regForm.getPvReqFlag().equalsIgnoreCase("y")) {
							forward = "transactingParty";
						} else { // for deeds following code set 2 (pv not
							// related)
							forward = "dashboardLandTrns";
						}
						request.setAttribute("deedId", regForm.getDeedID());
						request.setAttribute("instId", regForm.getInstID());
						request.setAttribute("roleIdTrns", "0");
					}

					else if (appStatus.equalsIgnoreCase(RegInitConstant.STATUS_SHARES_SAVED)) {

						String regTxnId = request.getParameter("hdnApplicationIdInitate");
						String getdutyId = commonBo.getdutyId(regTxnId);
						String exeDate = commonBo.getexeDate(getdutyId);
						DutyCalculationForm dutyForm1 = new DutyCalculationForm();

						dutyForm1.setHidnRegTxnId(regTxnId);

						dutyForm1.setSlotdate(exeDate);
						dutyForm1.setRadioResiComm("");
						dutyForm1.setPropImage1DocumentName("");
						session.setAttribute(regTxnId, "regTxnId");
						forward = "reginitConfirm2";
						request.setAttribute("instId", String.valueOf(regForm.getInstID()));

						request.setAttribute("dutyForm1", dutyForm1);

						return mapping.findForward(forward);
					}
					// Added by gulrej on 9th June, 2017 -- Start
					else if (appStatus.equalsIgnoreCase(RegInitConstant.STATUS_TRNS_MAP)) {

						String regTxnId = request.getParameter("hdnApplicationIdInitate");
						String getdutyId = commonBo.getdutyId(regTxnId);
						String exeDate = commonBo.getexeDate(getdutyId);
						DutyCalculationForm dutyForm1 = new DutyCalculationForm();

						dutyForm1.setHidnRegTxnId(regTxnId);

						dutyForm1.setSlotdate(exeDate);
						dutyForm1.setRadioResiComm("");
						dutyForm1.setPropImage1DocumentName("");
						session.setAttribute(regTxnId, "regTxnId");
						forward = "MAP";
						request.setAttribute("instId", String.valueOf(regForm.getInstID()));

						request.setAttribute("dutyForm1", dutyForm1);

						return mapping.findForward(forward);
					} // Added by gulrej on 9th June, 2017 -- End

					// below code for estamp page
					else if (appStatus.equalsIgnoreCase(RegInitConstant.STATUS_TRNS_ESTAMP)) {
						session.removeAttribute("dutyId1");
						session.removeAttribute("regTxnId");
						System.out.println(">>>>  " + session.getAttribute(CommonConstant.SUCCESS_MSG_AMT1));
						DutyCalculationForm eform = new DutyCalculationForm();
						eform.getDutyCalculationDTO().setPurposeLoan("");
						String tempefileid = regForm.getHidnRegTxnId();
						String getdutyTxnId = commonBo.getdutyTxnIdDashboard(tempefileid);
						regForm.setHidnRegTxnId(tempefileid);
						request.setAttribute("tempefileid", tempefileid);
						eform.getDutyCalculationDTO().setHidnRegTxnId(tempefileid);
						session.setAttribute("tempefileid", tempefileid);
						session.setAttribute("getdutyTxnId", getdutyTxnId);
						// below method is for additional upload on payment mode
						// page
						regForm.setAdduploadflag("");
						regForm.setAdduploadflag1("");
						PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
						String downloadPath = pr.getValue("igrs_upload_path");
						String deedpath = downloadPath + File.separator + "EFILING" + File.separator + tempefileid;
						String EfilingPath = deedpath + File.separator + "AdditionalUpload.Pdf";
						regForm.setPropImage2DocumentName1(deedpath);// added by
																		// saurav
						regForm.setPropImage2DocumentName("AdditionalUpload.Pdf");// added
																					// by
																					// saurav
						byte bytes[] = DMSUtility.getDocumentBytes(EfilingPath);
						if (bytes != null) {

							regForm.setAdduploadflag("Y");
							// String uploadName = "View";
							// Added By gulrej on 12th may 2017 // Start
							String uploadName = null;
							if (languageLocale.equalsIgnoreCase("en")) {
								uploadName = "View";
							} else {
								uploadName = "व्यू";
							}
							// Added By gulrej on 12th may 2017 // end
							regForm.setUploadView(uploadName);

						} else {
							regForm.setAdduploadflag1("Y");
						}

						int instID = regForm.getInstID();
						if (instID == 2269 || instID == 2268 || instID == 2267 || instID == 2266) {
							regForm.setAdduploadflag1("");
							regForm.setAdduploadflag("");
						}

						// below check is for duty is paid by estamp or physical
						String payflagestamp = commonBo.getpayFlag(tempefileid);
						String payflagphysical = commonBo.getpayflagphysical(tempefileid);
						if (payflagestamp.equalsIgnoreCase("Y")) {

							ArrayList getdutyDetails = commonBo.getdutyDetails(getdutyTxnId);

							if (getdutyDetails.size() > 0) {
								System.out.println("inside getduty of action");
								ArrayList rowList;
								for (int i = 0; i < getdutyDetails.size(); i++) {
									rowList = (ArrayList) getdutyDetails.get(i);

									regForm.setStampDutyDashboard(rowList.get(0).toString());
									regForm.setMunDutyDashboard(rowList.get(1).toString());
									regForm.setJanpadDutyDashboard(rowList.get(2).toString());
									regForm.setUpkarDutyDashboard(rowList.get(3).toString());
									regForm.setDutyAlreadyPaid(rowList.get(4).toString());
									regForm.setTotalDutyDashboard(rowList.get(5).toString());
									regForm.setTotalafterExemDashboard(rowList.get(6).toString());
									if (rowList.get(6).toString().equalsIgnoreCase("0")) {
										regForm.setAdduploadflag1("");
										regForm.setAdduploadflag("");
									}
									regForm.setEfileFee("500");
									regForm.setEstampCodeDashboard(rowList.get(7).toString());
									regForm.setEstampStatusDashboard(rowList.get(8).toString());
									regForm.setPURLOAN(rowList.get(9).toString());// added
																					// by
																					// saurav
									if (null == regForm.getPURLOAN()) {
										regForm.setPURLOAN("NA");
									}
								}
							}
							boolean exemptionCheck = commonBo.chekExemption(getdutyTxnId); // added
																							// by
																							// siddhartha
							if (exemptionCheck) {
								String checkStampdutyDetails = null;
								String checkEfiledutyDetails = null;
								checkStampdutyDetails = commonBo.checkStampdutyDetails(getdutyTxnId); // E
																										// multiple
																										// count
								checkEfiledutyDetails = commonBo.checkEfiledutyDetails(getdutyTxnId);// F
								if (checkStampdutyDetails.equalsIgnoreCase("E") && checkEfiledutyDetails.equalsIgnoreCase("")) {
									regForm.setTotalafterExemDashboard("0");
									regForm.setEfileFee("500");

								} else if (checkEfiledutyDetails.equalsIgnoreCase("F") && checkStampdutyDetails.equalsIgnoreCase("")) {
									regForm.setEfileFee("0");

								} else if (checkStampdutyDetails.equalsIgnoreCase("E") && checkEfiledutyDetails.equalsIgnoreCase("F")) {
									regForm.setTotalafterExemDashboard("0");
									regForm.setEfileFee("0");

								}

							}

							regForm.setEstamppayflag("Y");
							forward = "dashboardDutyEstampCodePg";
						}

						if (payflagphysical.equalsIgnoreCase("Y")) {
							ArrayList getdutyDetails = commonBo.getdutyDetails1(getdutyTxnId);

							if (getdutyDetails.size() > 0) {
								System.out.println("inside getduty of action");
								ArrayList rowList;
								for (int i = 0; i < getdutyDetails.size(); i++) {
									rowList = (ArrayList) getdutyDetails.get(i);

									regForm.setStampDutyDashboard(rowList.get(0).toString());
									regForm.setMunDutyDashboard(rowList.get(1).toString());
									regForm.setJanpadDutyDashboard(rowList.get(2).toString());
									regForm.setUpkarDutyDashboard(rowList.get(3).toString());
									regForm.setDutyAlreadyPaid(rowList.get(4).toString());
									regForm.setTotalDutyDashboard(rowList.get(5).toString());

									regForm.setTotalafterExemDashboard(rowList.get(6).toString());
									if (rowList.get(6).toString().equalsIgnoreCase("0")) {
										regForm.setAdduploadflag1("");
										regForm.setAdduploadflag("");
									}
									regForm.setEfileFee("500");
								}
							}

							// // Added By Gulrej on 16th May, 2017
							// if(regForm.getDutyAlreadyPaid()!=null &&
							// regForm.getDutyAlreadyPaid().trim()!=""){
							//								
							// if(regForm.getDutyAlreadyPaid()!=
							// regForm.getTotalDutyDashboard()){
							// boolean deleteflag=false;
							// deleteflag=commonBo.deleteStamp(tempefileid);
							// if(deleteflag){
							// forward =
							// CommonConstant.FORWARD_PAGE_DISPLAY_efiling;
							// return mapping.findForward(forward);
							// }
							// }
							// }
							boolean exemptionCheck = commonBo.chekExemption(getdutyTxnId); // added
																							// by
																							// siddhartha
							if (exemptionCheck) {
								String checkStampdutyDetails = null;
								String checkEfiledutyDetails = null;
								checkStampdutyDetails = commonBo.checkStampdutyDetails(getdutyTxnId); // E
																										// mukltiple
																										// s
								checkEfiledutyDetails = commonBo.checkEfiledutyDetails(getdutyTxnId);// F
								if (checkStampdutyDetails.equalsIgnoreCase("E") && checkEfiledutyDetails.equalsIgnoreCase("")) {
									regForm.setTotalafterExemDashboard("0");
									regForm.setEfileFee("500");

								} else if (checkEfiledutyDetails.equalsIgnoreCase("F") && checkStampdutyDetails.equalsIgnoreCase("")) {
									regForm.setEfileFee("0");

								} else if (checkStampdutyDetails.equalsIgnoreCase("E") && checkEfiledutyDetails.equalsIgnoreCase("F")) {
									regForm.setTotalafterExemDashboard("0");
									regForm.setEfileFee("0");
								}

							}
							ArrayList physicalList = commonBo.getphysicalList(tempefileid, regForm);
							if (physicalList.size() > 0) {
								regForm.setPhyListEfileSelected(physicalList);

							}
							// added by saurav to remove upload option and
							// estamp add flag while coming from dashboard
							int physicalStampValue = 0;
							for (int i = 0; i < physicalList.size(); i++) {
								RegCommonDTO regdtophystamp = (RegCommonDTO) physicalList.get(i);
								physicalStampValue = Integer.parseInt(regdtophystamp.getPhysicalStampCode()) + physicalStampValue;

							}
							if (String.valueOf(physicalStampValue).equals(regForm.getTotalDutyDashboard())) {
								regForm.setAdduploadflag1("");
							}
							regForm.setPhysicalStampFlagPo("Y");
							forward = "dashboardDutyEstampCodePg";
						}

						if (!payflagphysical.equalsIgnoreCase("Y") && !payflagestamp.equalsIgnoreCase("Y")) {
							ArrayList getdutyDetails1 = commonBo.getdutyDetails1(getdutyTxnId);

							if (getdutyDetails1.size() > 0) {
								System.out.println("inside getduty of action");
								ArrayList rowList;
								for (int i = 0; i < getdutyDetails1.size(); i++) {
									rowList = (ArrayList) getdutyDetails1.get(i);

									regForm.setStampDutyDashboard(rowList.get(0).toString());
									regForm.setMunDutyDashboard(rowList.get(1).toString());
									regForm.setJanpadDutyDashboard(rowList.get(2).toString());
									regForm.setUpkarDutyDashboard(rowList.get(3).toString());
									regForm.setDutyAlreadyPaid(rowList.get(4).toString());
									regForm.setTotalDutyDashboard(rowList.get(5).toString());
									regForm.setTotalafterExemDashboard(rowList.get(6).toString());
									if (rowList.get(6).toString().equalsIgnoreCase("0")) {
										regForm.setAdduploadflag1("");
										regForm.setAdduploadflag("");
									}
									regForm.setEfileFee("500");
									regForm.setPhysicalStampFlagPo("");
									regForm.setEstamppayflag("");

								}
							}

							/*
							 * ArrayList physicalStampList =
							 * commonBo.getPhysicalStampDetails(getdutyTxnId);
							 * ArrayList<DutyCalculationDTO> dto1 = new
							 * ArrayList(); DutyCalculationDTO dutydto=null;
							 * dutydto=new DutyCalculationDTO();
							 * 
							 * if (physicalStampList.size() > 0) { ArrayList
							 * rowList; for (int i = 0; i <
							 * physicalStampList.size(); i++) { rowList =
							 * (ArrayList) physicalStampList.get(i);
							 * 
							 * //DutyCalculationDTO dutydto=null; //dutydto=new
							 * DutyCalculationDTO();
							 * dutydto.setDisplayStampVendorName
							 * (rowList.get(1).toString());
							 * dutydto.setDisplayStampVendorSeries
							 * (rowList.get(3).toString());
							 * dutydto.setDisplayCodeStamp
							 * (rowList.get(2).toString());
							 * dutydto.setDisplayDateStamp
							 * (rowList.get(4).toString()); dto1.add(dutydto);
							 * regForm.setPhysicalStampList(dto1);
							 * 
							 * } }
							 */

							boolean exemptionCheck = commonBo.chekExemption(getdutyTxnId);
							if (exemptionCheck) {
								String checkStampdutyDetails = null;
								String checkEfiledutyDetails = null;
								checkStampdutyDetails = commonBo.checkStampdutyDetails(getdutyTxnId); // E
																										// mukltiple
																										// s
								checkEfiledutyDetails = commonBo.checkEfiledutyDetails(getdutyTxnId);// F
								if (checkStampdutyDetails.equalsIgnoreCase("E") && checkEfiledutyDetails.equalsIgnoreCase("")) {
									regForm.setTotalafterExemDashboard("0");
									regForm.setAdduploadflag1("");
									regForm.setAdduploadflag("");
									regForm.setEfileFee("500");

								} else if (checkEfiledutyDetails.equalsIgnoreCase("F") && checkStampdutyDetails.equalsIgnoreCase("")) {
									regForm.setEfileFee("0");

								} else if (checkStampdutyDetails.equalsIgnoreCase("E") && checkEfiledutyDetails.equalsIgnoreCase("F")) {
									regForm.setTotalafterExemDashboard("0");
									regForm.setAdduploadflag1("");
									regForm.setAdduploadflag("");
									regForm.setEfileFee("0");

								}

							}

							forward = "dashboardDutyEstampCodePg";
							return mapping.findForward(forward);
						}

					}
					// below code for after bank page i.e party page
					else if (appStatus.equalsIgnoreCase(RegInitConstant.STATUS_TRNS_BANK)) {
						String tempefileidbankpage = regForm.getHidnRegTxnId();
						session.setAttribute("tempefileidbankpage", tempefileidbankpage);
						String getdutyTxnIdbankpage = commonBo.getdutyTxnIdDashboard(tempefileidbankpage);
						session.setAttribute("tempefileidbankpage", tempefileidbankpage);
						session.setAttribute("getdutyTxnIdbankpage", getdutyTxnIdbankpage);

						int instId = regForm.getInstID();
						if (instId == 2089 || instId == 2265) {
							DutyCalculationForm eForm = new DutyCalculationForm();
							eForm.getDutyCalculationDTO().setCancellationFlag("N");
							eForm.getDutyCalculationDTO().setMainDutyTxnId(getdutyTxnIdbankpage);

							request.setAttribute("eForm", eForm);
							forward = "dashboardLandPartyFirstPgReg";
						} else {
							request.setAttribute("fromAdju", 0);
							DutyCalculationForm eForm = new DutyCalculationForm();
							// RegCommonForm regForm1=new RegCommonForm ();
							eForm.getDutyCalculationDTO().setMainDutyTxnId(getdutyTxnIdbankpage);
							eForm.getDutyCalculationDTO().setCancellationFlag("");
							eForm.getDutyCalculationDTO().setDeedId(regForm.getDeedID());
							eForm.getInstDTO().setInstType(regForm.getInstType());
							eForm.getInstDTO().setInstId(instId);
							int duty = Integer.parseInt(getdutyTxnIdbankpage);
							regForm.setDuty_txn_id(duty);
							regForm.setIsDashboardFlag(0);
							forward = "dashboardLandPartyFirstPg";

						}

						return mapping.findForward(forward);
					}

					else if (appStatus.equalsIgnoreCase(RegInitConstant.STATUS_TRNS_SAVED) || appStatus.equalsIgnoreCase(RegInitConstant.STATUS_MULTI_PROP_PV_RECVD)) {

						if (appStatus.equalsIgnoreCase(RegInitConstant.STATUS_TRNS_SAVED)) {
							if (regForm.getPvReqFlag().equalsIgnoreCase("y")) {
								commonBo.landPropertyScreen(regForm, appStatus, languageLocale);
								forward = "reginitProperty";
							} else if (regForm.getPropReqFlag().equalsIgnoreCase("y")) {
								forward = "dashboardLandProp";
							} else if (regForm.getCommonDeed() == 1) {
								forward = "reginitParticular";
							}
						} else {

							forward = "reginitPropertyNPV";

						}

						request.setAttribute("propAction", "propAction");
						request.setAttribute("regInitForm", regForm);
						request.setAttribute("dutyForm", dutyForm);

					} else if (appStatus.equalsIgnoreCase(RegInitConstant.STATUS_MULTI_PROP_IN_PROGRESS)) {

						if (regForm.getPvReqFlag().equalsIgnoreCase("y")) {
							commonBo.landPropertyScreen(regForm, appStatus, languageLocale);
							forward = "reginitProperty";
						} else {
							forward = "dashboardLandMultiProp";
						}

						request.setAttribute("propAction", "propAction");
						request.setAttribute("regInitForm", regForm);
						request.setAttribute("dutyForm", dutyForm);

					} else if (appStatus.equalsIgnoreCase(RegInitConstant.STATUS_PROP_SAVED) || appStatus.equalsIgnoreCase(RegInitConstant.STATUS_BACKED_MAPPING_SAVED)) {

						forward = commonBo.landMappingScreen(regForm, languageLocale);
						return mapping.findForward(forward);

					} else if (appStatus.equalsIgnoreCase(RegInitConstant.CONSENTER_IN_PROGRESS)) {

						String[] param = commonBo.getConsenterFlag(regForm.getHidnRegTxnId());
						regForm.setConsenterFlag("Y");
						regForm.setConsenterWithConsidFlag(param[0]);
						//regForm.setConsenterCount(Integer.parseInt(param[1]));
						regForm.setDtlsMapConsntr(commonBo.getConsenters(regForm.getHidnRegTxnId()));
						regForm.setConsenterAddedCount(regForm.getDtlsMapConsntr() != null ? regForm.getDtlsMapConsntr().size() : 0);
						// get already added consenters in map here
						if (regForm.getPvReqFlag().equalsIgnoreCase("Y")) {
							forward = "reginitConsenter";
						} else {
							forward = "reginitConsenterNonPV";
						}
						commonDto.setIdProf(commonBo.getIdProf(languageLocale));
						commonDto.setState(commonBo.getState("1", languageLocale));
						commonDto.setDistrict(new ArrayList());
						commonDto.setRelationshipList(commonBo.getRelationshipList(null, languageLocale));
						return mapping.findForward(forward);

					} else if (appStatus.equalsIgnoreCase(RegInitConstant.STATUS_SHARES_SAVED)) {

						commonBo.landConfirmationScreen(regForm, languageLocale, request);

						// if(regForm.getDeedID()!=0 && regForm.getInstID()!=0){
						request.setAttribute("deedId", regForm.getDeedID());
						request.setAttribute("instId", regForm.getInstID());

						double totPay = Double.parseDouble(regForm.getTotalPayableAmount());
						double totBal = Double.parseDouble(regForm.getTotalBalanceAmount());

						if (regForm.getPvReqFlag().equalsIgnoreCase("N")) {
							if ((regForm.getFromAdjudication() == 0 && totPay > 0 && totBal <= 0 && regForm.getRegInitEstampCode() == null)) {

								request.setAttribute("eStampRegId", regForm.getHidnRegTxnId());
								request.setAttribute("eStampRegAmnt", regForm.getTotalPayableAmount());
								request.setAttribute("eStampRegDistId", commonBo.getDistIdEstamp(regForm.getHidnRegTxnId()));
								request.setAttribute("eStampRegFuncId", "023");
								request.setAttribute("eStampRegPurpose", regForm.getPurpose());
								request.setAttribute("sourceModFlag", "PVN");
								forward = "reginitEstamp";
								return mapping.findForward(forward);

							} else {
								forward = "reginitConfirmNonPV";
							}
						} else {
							if ((regForm.getFromAdjudication() == 0 && totPay > 0 && totBal <= 0 && regForm.getRegInitEstampCode() == null)) {

								request.setAttribute("eStampRegId", regForm.getHidnRegTxnId());
								request.setAttribute("eStampRegAmnt", regForm.getTotalPayableAmount());
								request.setAttribute("eStampRegDistId", commonBo.getDistIdEstamp(regForm.getHidnRegTxnId()));
								request.setAttribute("eStampRegFuncId", "023");
								request.setAttribute("eStampRegPurpose", regForm.getPurpose());
								request.setAttribute("sourceModFlag", "PV");
								forward = "reginitEstamp";
								return mapping.findForward(forward);

							} else {

								forward = "reginitConfirm";
							}
						}
					} else if (appStatus.equalsIgnoreCase(RegInitConstant.STATUS_FINAL_SCREEN)) {

						RegCommonBO bo = new RegCommonBO();
						bo.getPaymentAmounts(regForm);
						double regFee = 0;
						double regFeePaid = 0;
						String payComplt = "Y";
						if ("Y".equalsIgnoreCase(regForm.getCheckRegFeeBox())) {
							regFee = Double.parseDouble((String) regForm.getTotalBalanceAmountFee());
							regFeePaid = Double.parseDouble((String) regForm.getTotalPaidAmountFee());
							payComplt = bo.checkFeeCompletFlag(regForm.getHidnRegTxnId());

						}

						if ("Y".equalsIgnoreCase(regForm.getCheckRegFeeBox()) && (regFeePaid == 0)) {

							commonBo.landConfirmationScreen(regForm, languageLocale, request);
							request.setAttribute("deedId", regForm.getDeedID());
							request.setAttribute("instId", regForm.getInstID());
							if (regForm.getPvReqFlag().equalsIgnoreCase("N")) {
								forward = "reginitConfirmNonPV";
							} else {
								forward = "reginitConfirm";
							}
						} else if ("Y".equalsIgnoreCase(regForm.getCheckRegFeeBox()) && (regFee > 0.0 || "N".equalsIgnoreCase(payComplt))) {

							if (regForm.getPvReqFlag().equalsIgnoreCase("N")) {
								forward = "regFeePageNPV";
							} else {
								forward = "regFeePage";
							}

						} else {
							commonDto.setIndcountry(commonBo.getCountry(languageLocale));
							commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
							commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(), languageLocale));
							regForm.setPostalCountry("1");
							regForm.setPostalState("");
							regForm.setPostalDistrict("");

							regForm.setHdnPostalAddress1(regForm.getPostalAddress1());

							String dutyListArr[] = new String[9];
							String purpose = commonBo.getEStampPurpose(regForm.getHidnRegTxnId());

							if (purpose != null && !purpose.equalsIgnoreCase("")) {
								regForm.setPurpose(purpose.replace(RegInitConstant.SPLIT_CONSTANT, ","));
							} else {
								regForm.setPurpose("");
							}

							request.setAttribute("regInitTxnId", regForm.getHidnRegTxnId());

							if (regForm.getPvReqFlag().equalsIgnoreCase("Y")) {
								forward = "success1";
								// forward="reginitConfirm1";
							} else {
								forward = "dashboardLandSuccess";
							}
						}
					} else if (appStatus.equalsIgnoreCase(RegInitConstant.STATUS_COMPLETE)) {

						String purpose = commonBo.getEStampPurpose(regForm.getHidnRegTxnId());

						if (purpose != null && !purpose.equalsIgnoreCase("")) {
							regForm.setPurpose(purpose.replace(RegInitConstant.SPLIT_CONSTANT, ","));
						} else {
							regForm.setPurpose("");
						}

						String dutyListArr[] = new String[10];
						if (regForm.getFromAdjudication() == 0) {

							dutyListArr = commonBo.getDutyDetlsForConfirmation(regForm.getHidnRegTxnId());
						} else {

							dutyListArr = commonBo.getDutyDetlsAdjuForConfirmation(regForm.getHidnRegTxnId());
						}

						commonBo.setDutyInForm(regForm, dutyListArr, languageLocale);

						commonBo.printToPdfJasper(regForm, request, response, languageLocale, "Y");

						request.setAttribute("deedId", regForm.getDeedID());
						request.setAttribute("instId", regForm.getInstID());

						request.setAttribute("pendingApplicationList", regForm.getPendingRegApplicationList());
						forward = "reginitDashboard";
					}

				}
			} catch (Exception e) {

				logger.debug(e);
				logger.debug(e.getMessage(), e);
				forward = "failure";
				return mapping.findForward(forward);

			}
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());

			return mapping.findForward(forward);
		}
		return mapping.findForward(forward);
	}

	// method for creating folder on server
	public String uploadFile(String regTxnId, byte[] content, String txnPartyId, String partyOrProp, String uploadType) {

		String filePath;
		String path = "";

		try {
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

			path = pr.getValue("upload.location");

			if (partyOrProp != null) {
				if (partyOrProp.equalsIgnoreCase("01")) {
					filePath = path + RegInitConstant.FILE_UPLOAD_PATH + regTxnId + RegInitConstant.FILE_UPLOAD_PATH_PARTY + txnPartyId + "/";
				} else if (partyOrProp.equalsIgnoreCase("02")) {
					filePath = path + RegInitConstant.FILE_UPLOAD_PATH + regTxnId + RegInitConstant.FILE_UPLOAD_PATH_PROP + txnPartyId + "/";
				} else if (partyOrProp.equalsIgnoreCase("03")) {
					filePath = path + RegInitConstant.FILE_UPLOAD_PATH + regTxnId + RegInitConstant.FILE_UPLOAD_PATH_DEED_DRAFT + txnPartyId + "/";
				} else if (partyOrProp.equalsIgnoreCase("04")) {
					filePath = path + RegInitConstant.FILE_UPLOAD_PATH + regTxnId + RegInitConstant.FILE_UPLOAD_PATH_CONSENTER + txnPartyId + "/";
				} else {
					return null;
				}
			} else {

				filePath = path + RegInitConstant.FILE_UPLOAD_PATH + regTxnId + "/";

			}

			logger.debug("Upload path created : " + filePath);

			String fileName = uploadType;

			File folder = new File(filePath);
			if (!folder.exists()) {
				folder.mkdirs();
			}

			File newFile = new File(filePath, fileName);
			if (!newFile.exists()) {
				logger.info("NEW FILE NAME:-" + newFile);
				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(content);
				fos.close();
				logger.debug("file written:" + (filePath + fileName));
			} else {

				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(content);
				fos.close();
				logger.debug("file written:" + (filePath + fileName));
			}
			return filePath + fileName;

		} catch (Exception ex) {

			logger.debug(ex.getMessage(), ex);
			return null;
		}

	}

}
