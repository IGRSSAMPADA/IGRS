package com.wipro.igrs.spotInsp.action;
/**
 * ===========================================================================
 * File           :   SpotInspAction.java
 * Description    :   Represents the Action Class

 * Author         :   Pavani Param
 * Created Date   :   Apr 08,2008.

 * ===========================================================================
 */
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.ODServerDetails;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveats.bo.CaveatsBO;
import com.wipro.igrs.caveats.dao.CaveatsDAO;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.deedDraft.constant.DeedDraftConstant;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.spotInsp.bd.SpotInspBD;
import com.wipro.igrs.spotInsp.bo.SpotInspBO;
import com.wipro.igrs.spotInsp.constant.CommonConstant;
import com.wipro.igrs.spotInsp.dto.SpotInspDTO;
import com.wipro.igrs.spotInsp.form.SpotInspForm;
import com.wipro.igrs.util.GUIDGenerator;
import com.wipro.igrs.util.PropertiesFileReader;

public class SpotInspAction extends BaseAction {
	private final Logger logger = Logger.getLogger(SpotInspAction.class);
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws SQLException, Exception {
		logger.info("  In SpotInspAction -- for getting form object--> " + form);
		String FORWARD_JSP = "success";
		ArrayList propIdList = null;

		SpotInspDTO spotDto = new SpotInspDTO();
		SpotInspForm spForm = (SpotInspForm) form;
		SpotInspBD objSpBD = new SpotInspBD();
		String language = (String) session.getAttribute("languageLocale");
		spotDto.setLanguage(language);
		spForm.setLanguage(language);
		boolean blnInsVal = false;

		String regTxnId = null;
		// HttpSession session = request.getSession();
		// String roleId = (String)session.getAttribute("role"); //commented by
		// ramesh on 06 Dec 12
		String roleId = (String) session.getAttribute("loggedInRole");
		String funId = (String) session.getAttribute("functionId");
		String userId = (String) session.getAttribute("UserId");
		String office = (String) session.getAttribute("loggedToOffice");
		String officer = objSpBD.getType(office);
		// changes done by akansha for spot inspection date configuration
		
		boolean validateDate = objSpBD.validateDate(spForm.getDurFrom(), spForm.getDurTo());
		String durationSQL = objSpBD.duration();
		
		
		String dateCheck = "Y";
		if(null!=spForm.getDurFrom() & null!=spForm.getDurTo() & !("").equalsIgnoreCase(spForm.getDurFrom()) & !("").equalsIgnoreCase(spForm.getDurTo())){
		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(spForm.getDurFrom()); 
		Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(spForm.getDurTo()); 
		
		if(date1.compareTo(date2)==0){
			dateCheck="Y";
		}else if(date1.compareTo(date2)>0){
			dateCheck="N";
		}else if(date1.compareTo(date2)<0){
			dateCheck="Y";
		}
		}
		// System.out.println(spForm.getClauseIds().getClass().getName());
		if (officer.equalsIgnoreCase("IGO")) {
			spForm.setUser("I");
		} else if (officer.equalsIgnoreCase("DRO")) {
			spForm.setUser("DR");
			ArrayList distList = objSpBD.getDistList(office);
			SpotInspDTO a = (SpotInspDTO) distList.get(0);
			spForm.setDistrictId(a.getDistrictId());
			spForm.setZoneId(a.getZoneId());
		} else if (officer.equalsIgnoreCase("DIGO")) {
			String zoneId = objSpBD.getDIGZone(office);
			spForm.setZoneId(zoneId);
			logger.debug("The Zone of DIG : " + zoneId);

			spForm.setUser("DI");
		}

		spForm.setLoggedInUserId(userId);
		session.removeAttribute("spotVRList");
		session.removeAttribute("visitForm");
		spForm.setDrResp("");
		String pageTitle = request.getParameter("pageTitleView");
		String fwdpage = request.getParameter("fwdPage");
		String actionName = request.getParameter("actionName");
		logger.info("actionName --  " + actionName);
		
		// added by saurav
		if (office != null && !office.equalsIgnoreCase("")) {
			spForm.setLoggedInOffice(office);
		}
		spForm.setLoggedInOfficeType(officer);
		if (actionName != null && !actionName.equalsIgnoreCase("")) {
			spForm.setActionName(actionName);
			spForm.setPageTitle("");
		}

		if ("viewProperty".equalsIgnoreCase(fwdpage)) {
			RegCommonBO commonBo = new RegCommonBO();
			String propertyId = request.getParameter("propertyTxnID");
			String regId = request.getParameter("registrationNo");
			if (propertyId.length() == 15) {
				propertyId = "0" + propertyId;
			}
			String reginitId = "";
			if (regId != null) {
				CaveatsBO objCaveatBO = new CaveatsBO();
				reginitId = objCaveatBO.getReginitId(regId);
			}

			String propval = new CaveatsDAO().getPropVal(regId);
			RegCommonBO bo = new RegCommonBO();
			RegCommonForm regForm = new RegCommonForm();
			// cDto.setAddressOfInsti(propval);
			if (propval.equalsIgnoreCase("Y")) {
				regForm.setMapPropertyTransPartyDisp(bo.getPropertyDetsForViewConfirm(reginitId, propertyId, language));
				request.setAttribute("reginit", regForm);
				FORWARD_JSP = "propertyView";
			} else {
				regForm.setMapPropertyTransPartyDisp(bo.getPropertyDetsForViewConfirmNonProp(reginitId, propertyId, language));
				request.setAttribute("reginit", regForm);
				FORWARD_JSP = "propertyView1";

			}
		}
		if ("searchByDR".equalsIgnoreCase(pageTitle)) {
			spForm.setReferenceID("");
			spForm.setDurFrom("");
			spForm.setDurTo("");
			spForm.setStatus("");
			spForm.setDrReason("");
			spForm.setDrRemarks("");
			FORWARD_JSP = "searchView";
		} else if ("requestViewByDR".equalsIgnoreCase(pageTitle)) {
			spForm.setReferenceID("");
			spForm.setDurFrom("");
			spForm.setDurTo("");
			spForm.setStatus("");
			FORWARD_JSP = "requestView";
		} else if ("spotInsRequest".equalsIgnoreCase(pageTitle))// Spot
																// inspection
																// request by SR
		{
			spForm.setReferenceID("");
			spForm.setDurFrom("");
			spForm.setDurTo("");
			spForm.setStatus("");
			spForm.setRemarks("");
			spForm.setSiReason("");
			spForm.setViewType("");
			spForm.setRangeId("");
			spForm.setPropertyId("");
			spForm.setPropertyL2Id("");
			spForm.setDeedId("");
			spForm.setClauseId("");
			String selectArray[] = new String[1];
			selectArray[0] = "-1";
			spForm.setSelectedClauses(selectArray);
			spForm.setPropertyL2List(new ArrayList());
			ArrayList rangeList = new ArrayList();
			ArrayList clauseList = new ArrayList();
			ArrayList deedList = new ArrayList();
			ArrayList propertyList = new ArrayList();

			try {
				rangeList = objSpBD.getCurrentRangeList();
				clauseList = objSpBD.getCurrentSubClauseList1(spForm.getLanguage());
				deedList = objSpBD.getcurrentdeedInstrumentList(spForm.getLanguage());
				propertyList = objSpBD.getCurrentpropertyList(spForm.getLanguage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			spForm.setRangeList(rangeList);
			spForm.setClauseList(clauseList);
			spForm.setDeedList(deedList);
			spForm.setPropertyList(propertyList);
			FORWARD_JSP = "spotinsRrequest";
		} else if ("srSchedule".equalsIgnoreCase(pageTitle)) {
			spForm.setRemarks("");
			spForm.setSiReason("");
			spForm.setInsPlanDate("");
			spForm.setDurTo("");
			spForm.setDurFrom("");
			spForm.setReferenceID("");
			spForm.setStatus("");
			spForm.setActionName("");
			FORWARD_JSP = "spotschedule";

		} else if ("srCompletion".equalsIgnoreCase(actionName)) {
			spForm.setRemarks("");
			spForm.setSiReason("");
			spForm.setChangeVal("");
			spForm.setNameofParty("");
			spForm.setInspRemarks("");
			spForm.setInsActDate("");
			spForm.setReferenceID("");
			spForm.setDurFrom("");
			spForm.setDurTo("");
			spForm.setStatus("");
			spForm.setCaseId("");
			spForm.setSaveInspStatus("Y");
			spForm.getObjSIDto().setAreaType("");
			FORWARD_JSP = "spotCompletion";
		} else if ("reInspectionCompletion".equalsIgnoreCase(actionName)) {
			spForm.setRemarks("");
			spForm.setSiReason("");
			spForm.setChangeVal("");
			spForm.setNameofParty("");
			spForm.setInspRemarks("");
			spForm.setInsActDate("");
			spForm.setReferenceID("");
			spForm.setDurFrom("");
			spForm.setDurTo("");
			spForm.setStatus("");
			spForm.setInspStatus("N");
			spForm.setPhotoCopyName(null);
			spForm.setCaseId(null);
			FORWARD_JSP = "spotCompletion";
		} else if ("srViewpage".equalsIgnoreCase(actionName)) {
			spForm.setApplicationNumber("");
			spForm.setDurFrom("");
			spForm.setDurTo("");
			spForm.setStatus("");
			spForm.setRemarks("");
			spForm.setSiReason("");

			FORWARD_JSP = "spotView";
		} else if ("reViewpage".equalsIgnoreCase(actionName)) {
			spForm.setApplicationNumber("");
			spForm.setDurFrom("");
			spForm.setDurTo("");
			spForm.setStatus("");
			spForm.setRemarks("");
			spForm.setSiReason("");
			FORWARD_JSP = "spotView";
		} else if ("reReport".equalsIgnoreCase(actionName)) {
			spForm.setRemarks("");
			spForm.setSiReason("");
			spForm.setInsPlanDate("");
			spForm.setDurTo("");
			spForm.setDurFrom("");
			spForm.setReferenceID("");
			spForm.setStatus("R");
			FORWARD_JSP = "spotschedule";
		}

		else {
			if (userId == null) {
				spForm.setUserId("102");
			} else {
				spForm.setUserId(userId);
			}
			regTxnId = (String) session.getAttribute("sessionRegTxnId");
			if (regTxnId == null) {
				// reg id getting from spot inspection
				regTxnId = spForm.getDrReqApplNo();
			}
			spForm.setRegTxnId(regTxnId);
			try {

				// Ramesh Added on 05 DEC 12
				if (spForm.getPageTitle().equalsIgnoreCase("SpotInspRequest")) {
					// spForm = objSpBD.getSpotSechRequest(spForm);
					// FORWARD_JSP="spotinsRrequest";

				}

				else if (spForm.getPageTitle().equalsIgnoreCase("SpotInspSchedule")) {
					// changes done by akansha for spot inspection date
					// configuration
					if (validateDate) {
						ArrayList temp = objSpBD.getSpotViewResFinal(spForm);
						request.setAttribute("spotVRList", spForm.getSpotViewList());
						request.setAttribute("visitForm", temp);

						if (spForm.getStatus().equalsIgnoreCase("R")) {
							FORWARD_JSP = "success1";
						} else {
							FORWARD_JSP = "success";
						}
					}

					else {

						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of " + durationSQL + " days or less than that");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी " + durationSQL + " दिन या उस से कम की रखें।");
						if(dateCheck.equalsIgnoreCase("N")){
							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Duration has not selected properly");
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी " + durationSQL + " दिन या उस से कम की रखें।");
						}
						FORWARD_JSP = "spotschedule";

					}

				} else if (spForm.getPageTitle().equalsIgnoreCase("SpotInspScheduleDet")) {
					blnInsVal = objSpBD.SpotInspSechUpd(spForm.getSchApplNo(), spForm.getInsPlanDate(), roleId, funId, userId, spForm.getInspRemarks());
				} else if (spForm.getPageTitle().equalsIgnoreCase("SpotInspCompletionDet1")) {
					if (spForm.getInspStatus().equalsIgnoreCase("N")) {
						if (spForm.getStatus() != null && spForm.getStatus().equalsIgnoreCase("R")) {
							setReValueNew(spForm, request);
						} else {
							setValueNew(spForm, request);
						}

						spForm.setPhotoCopyName("");
						spForm.setChangeVal("");
					} else if (spForm.getInspStatus().equalsIgnoreCase("Y")) {
						if (spForm.getStatus() != null && spForm.getStatus().equalsIgnoreCase("R")) {
							setReValueNew(spForm, request);
						} else {
							setValueNew(spForm, request);
						}
						spForm.setPhotoCopyName("");
						// spForm.setChangeVal("");
					}

					spForm.setActionName("");
					spForm.setPageTitle("");
					if (spForm.getStatus() != null && spForm.getStatus().equalsIgnoreCase("R")) {
						FORWARD_JSP = "successpage1";
					} else {
						FORWARD_JSP = "successpage";
					}
				} else if (spForm.getPageTitle().equalsIgnoreCase("SpotInspCompletionDet"))// Search
				{
					spForm.setActionName("");
					// added by akansha
					setValueNew(spForm, request);
					spForm.setSaveInspStatus("N");
					blnInsVal = objSpBD.SpotInspCompUpd(spForm, roleId, funId, userId, request);
					if (blnInsVal) {// added by akansha

						objSpBD.updateStatus(spForm);
					}
				} else if (spForm.getPageTitle().equalsIgnoreCase("SpotInspCompletionDetUpload")) {
					boolean up = false;
					spForm.setActionName("");
					PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					ArrayList errorList = new ArrayList();
					FormFile uploadedFile = spForm.getPhoto();
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
						request.setAttribute("errorsList", errorList);

						if (language.equalsIgnoreCase("en")) {
							setValueNew(spForm, request);
							request.setAttribute(CommonConstant.FAILURE_MSG, "Invalid file type. Please select jpg file");
						} else {
							setValueNew(spForm, request);
							request.setAttribute(CommonConstant.FAILURE_MSG, "अमान्य फ़ाइल प्रकार। कृपया jpg फ़ाइल का चयन करें।");
						}
						// FORWARD_JSP ="successpage";
					} else {
						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
							errorList.add("File size is Greater than 10 MB. Please select another file.");

							request.setAttribute("errorsList", errorList);
							if (language.equalsIgnoreCase("en")) {
								if (spForm.getStatus() != null && spForm.getStatus().equalsIgnoreCase("R")) {
									setReValueNew(spForm, request);

								} else {

									setValueNew(spForm, request);

								}
								request.setAttribute(CommonConstant.FAILURE_MSG, "Invalid file type. Please select another file");
							} else {
								if (spForm.getStatus() != null && spForm.getStatus().equalsIgnoreCase("R")) {
									setReValueNew(spForm, request);

								} else {

									setValueNew(spForm, request);

								}
								request.setAttribute(CommonConstant.FAILURE_MSG, "अमान्य फ़ाइल प्रकार। कृपया अन्य फ़ाइल का चयन करें।");
							}
							// FORWARD_JSP ="successpage";
						} else {
							Date date = new Date();
							Format yearformat = new SimpleDateFormat("yy");
							Format monthformat = new SimpleDateFormat("MM");
							Format dateformat = new SimpleDateFormat("dd");
							Format hourformat = new SimpleDateFormat("hh");
							Format minformat = new SimpleDateFormat("mm");
							Format secformat = new SimpleDateFormat("ss");
							Format ampmformat = new SimpleDateFormat("a");
							String dfmt = dateformat.format(date);
							String yfmt = yearformat.format(date);
							String mfmt = monthformat.format(date);
							String hfmt = hourformat.format(date);
							String mifmt = minformat.format(date);
							String sfmt = secformat.format(date);
							String ampmfmt = ampmformat.format(date);
							String fldrnm = dfmt + mfmt + yfmt + hfmt + mifmt + sfmt + ampmfmt;
							// logger.debug("the new date in upload thumb is----------------------- >"+fldrnm);
							String thumbName = uploadedFile.getFileName();
							String thumbPath = "";
							String downloadPath = pr.getValue("igrs_upload_path");
							thumbPath = downloadPath + CommonConstant.IGRS_FILE_PATH + File.separator;
							if (spForm.getStatus() != null && spForm.getStatus().equalsIgnoreCase("R")) {
								thumbPath = thumbPath + CommonConstant.SPOT_REINSPECTION_FILE_PATH + File.separator + spForm.getSpotInspTxnId();
							} else {
								thumbPath = thumbPath + CommonConstant.SPOT_INSPECTION_FILE_PATH + File.separator + spForm.getSpotInspTxnId();

							}
							up = uploadFile(spForm.getPhoto(), thumbName, thumbPath);
							if (up) {
								spForm.setPhotoCopyName(uploadedFile.getFileName());
								spForm.setPhotCopyPath(thumbPath);
								if (spForm.getStatus() != null && spForm.getStatus().equalsIgnoreCase("R")) {
									setReValueNew(spForm, request);
									FORWARD_JSP = "successpage1";
								} else {
									setValueNew(spForm, request);
									FORWARD_JSP = "successpage";
								}

								// sdDTO.setThumbContents(uploadedFile.getFileData());
								// sdDTO.setThumbSize(thumbSize);
								// sdDTO.setThumbCheck("thloded");

							} else {
								if (language.equalsIgnoreCase("en")) {

									if (spForm.getStatus() != null && spForm.getStatus().equalsIgnoreCase("R")) {
										setReValueNew(spForm, request);
										// request.setAttribute(CommonConstant.FAILURE_MSG,"File could not be uploaded. Please try again.");
									} else {

										setValueNew(spForm, request);

									}
									request.setAttribute(CommonConstant.FAILURE_MSG, "File could not be uploaded. Please try again.");
								} else {

									if (spForm.getStatus() != null && spForm.getStatus().equalsIgnoreCase("R")) {
										setReValueNew(spForm, request);
										// request.setAttribute(CommonConstant.FAILURE_MSG,"फाइल अपलोड नहीं हो पाई है। कृपया पुनः प्रयास करें।");

									} else {

										setValueNew(spForm, request);

									}
									request.setAttribute(CommonConstant.FAILURE_MSG, "फाइल अपलोड नहीं हो पाई है। कृपया पुनः प्रयास करें।");
								}
							}
						}
					}
					if (spForm.getStatus() != null && spForm.getStatus().equalsIgnoreCase("R")) {
						FORWARD_JSP = "successpage1";
					} else {
						FORWARD_JSP = "successpage";
					}
					spForm.setPageTitle("");// added by akansha
				}

				else if (spForm.getPageTitle().equalsIgnoreCase("DRReqUpdate")) {
					// blnInsVal = objSpBD.SpotInspCompUpd(spForm);
					spForm = objSpBD.getDrReqRes(spForm);
					request.setAttribute("spotVRList", spForm.getSpotViewList());
					request.setAttribute("visitForm", spForm);
					logger.info("SpotInspAction ..........FORWARD_JSP==" + FORWARD_JSP);
					logger.info("SpotInspAction ..........pageTitlel==" + spForm.getPageTitle());
					FORWARD_JSP = "reinspectionDetail";

				} else if (spForm.getPageTitle().equalsIgnoreCase("DRReqUpdateDet")) {
					// blnInsVal = objSpBD.SpotInspCompUpd(spForm);

					blnInsVal = objSpBD.updateDrReqDet(spForm, roleId, funId, userId);
					if (blnInsVal) {
						if (spForm.getInspStatus().equalsIgnoreCase("A"))
							spForm.setDrResp(CommonConstant.IGRS_SPOT_DR_RES_A);
						else
							spForm.setDrResp(CommonConstant.IGRS_SPOT_DR_RES_D);
					}
					FORWARD_JSP = "success";
				}

				else if (spForm.getPageTitle().equalsIgnoreCase("SpotInsReq")) {
					blnInsVal = objSpBD.updateSpReqDet(spForm, roleId, funId, userId);
					if (blnInsVal) {
						request.setAttribute("ISINSERT", "success");
					}

				}

				else if (spForm.getPageTitle().equalsIgnoreCase("DRReqView")) {
					// blnInsVal = objSpBD.SpotInspCompUpd(spForm);
					logger.info("Before ");
					spForm = objSpBD.getDrViewRes(spForm);

					request.setAttribute("spotVRList", spForm.getSpotViewList());
					request.setAttribute("visitForm", spForm);
					logger.info("SpotInspAction ..........FORWARD_JSP==" + FORWARD_JSP);
					logger.info("SpotInspAction ..........pageTitlel==" + spForm.getPageTitle());
				} else if (spForm.getPageTitle().equalsIgnoreCase("SpotInspCompletion")) // added
																							// by
																							// roopam
																							// 30
																							// oct
																							// 2013
				{
					// add code for sr worklist creation here.
				}

				if (spForm.getActionName() != null) {
					if (spForm.getActionName().equals("districtAction")) {
						spForm.setDurFrom("");
						spForm.setDurTo("");
						String select[] = new String[1];
						select[0] = "-1";
						spForm.setSelectedClauses(select);
						spForm.setDeedId("");
						spForm.setRangeId("");
						spForm.setPropertyId("");
						spForm.setPropertyL2Id("");
						spForm.setPropertyL2List(new ArrayList());
						spForm.setQuestion("N");

						if (spForm.getUser().equalsIgnoreCase("DI")) {
							spForm.setDistrictId("-1");
							ArrayList list = objSpBD.getDistDIGList(spForm.getZoneId(), spForm.getLanguage());
							spForm.setZoneList(objSpBD.getZone("1", spForm.getLanguage()));
							if (list.size() > 0)
								spForm.setDistrictList(list);
							else
								spForm.setDistrictList(new ArrayList());

						} else if (spForm.getUser().equalsIgnoreCase("I")) {
							spForm.setAreaTypeList(objSpBD.getAreTypea(spForm.getLanguage()));
							spForm.setZoneId("");
							spForm.setZoneList(objSpBD.getZone("1", spForm.getLanguage()));
							spForm.setDistrictList(new ArrayList());
						} else if (spForm.getUser().equalsIgnoreCase("DR")) {
							// logger.debug("Entered DR");
							ArrayList distList = objSpBD.getDistList(office);

							if (distList.size() > 0) {
								SpotInspDTO a = (SpotInspDTO) distList.get(0);
								spForm.setDistrictId(a.getDistrictId());
								spForm.setZoneId(a.getZoneId());

							}

							spForm.setTehsilList(objSpBD.getTehsil(spForm.getDistrictId(), spForm.getLanguage()));
							spForm.setAreaTypeList(objSpBD.getAreTypea(spForm.getLanguage()));
							spForm.setZoneList(objSpBD.getZone("1", spForm.getLanguage()));
							ArrayList list = objSpBD.getDistDIGList(spForm.getZoneId(), spForm.getLanguage());
							if (list.size() > 0)
								spForm.setDistrictList(list);
							else
								spForm.setDistrictList(new ArrayList());
						}
						spForm.setActionName("");

						FORWARD_JSP = "spotinsRrequest";

					}
					if (spForm.getActionName().equals("zoneAction")) {
						spForm.setDurFrom("");
						spForm.setDurTo("");
						String select[] = new String[1];
						select[0] = "-1";
						spForm.setSelectedClauses(select);
						spForm.setDeedId("");
						spForm.setRangeId("");
						spForm.setPropertyId("");
						spForm.setPropertyL2Id("");
						spForm.setPropertyL2List(new ArrayList());
						spForm.setDistrictId("-1");
						if (spForm.getUser().equalsIgnoreCase("I")) {
							spForm.setZoneId("-1");
							spForm.setDistrictId("-1");

						} else if (spForm.getUser().equalsIgnoreCase("DI")) {
						}

						spForm.setQuestion("N");
						spForm.setZoneList(objSpBD.getZone("1", spForm.getLanguage()));
						spForm.setDistrictList(objSpBD.getDistDIGList(spForm.getZoneId(), spForm.getLanguage()));
						FORWARD_JSP = "spotinsRrequest";

					}

					if (spForm.getActionName().equals("getWardPatwari")) {

						String status = spForm.getWardPatwariId();

						if (status.equalsIgnoreCase("W")) {
							status = "1";
						} else if (status.equalsIgnoreCase("P")) {
							status = "2";
						}

						String paramAr = spForm.getAreaTypeId();

						spForm.setWardList(objSpBD.getWardList(spForm.getTehsilId(), paramAr, status, spForm.getLanguage()));

						FORWARD_JSP = "spotinsRrequest";

					}

					if (spForm.getActionName().equals("getWard")) {

						String statusSubId = spForm.getSubAreaId();
						SpotInspBO bo = new SpotInspBO();
						String municipalFlag = bo.getMuncipalFlag(statusSubId);

						if ("RF".equalsIgnoreCase(municipalFlag)) {
							spForm.setMunicipalCheckDisplay("Y");
							spForm.setMunicipalFlag("");
						} else if ("NF".equalsIgnoreCase(municipalFlag)) {
							spForm.setMunicipalCheckDisplay("N");
							spForm.setMunicipalFlag("Y");
						} else {
							spForm.setMunicipalCheckDisplay("N");
							spForm.setMunicipalFlag("N");
						}
						spForm.setWardPatwariList(bo.getWardPatwari(language, statusSubId, spForm.getTehsilId()));
						FORWARD_JSP = "spotinsRrequest";

					}

					if (spForm.getActionName().equals("getDistrictZone")) {
						spForm.setDistrictList(objSpBD.getDistrictZone(spForm.getZoneId(), spForm.getLanguage()));
						FORWARD_JSP = "spotinsRrequest";

					}

					if (spForm.getActionName().equals("getMohallaAction")) {
						SpotInspBO bo = new SpotInspBO();
						logger.info("Inside Get Colony Action");
						spForm.setMohallaList(bo.getColonyName(language, spForm.getWardIds()));
						FORWARD_JSP = "spotinsRrequest";

					}
					if (spForm.getActionName().equals("getPropertyDistrict")) {
						spForm.setTehsilList(objSpBD.getTehsil(spForm.getDistrictId(), spForm.getLanguage()));

						FORWARD_JSP = "spotinsRrequest";

					}
					if (spForm.getActionName().equals("setTehsil")) {
						System.out.println(spForm.getTehsilId());
						spForm.setTehsilId(spForm.getTehsilId());
						FORWARD_JSP = "spotinsRrequest";

					}
					if (spForm.getActionName().equals("setArea")) {
						logger.info("Inside GetSubArea Action");

						String areaId = spForm.getAreaTypeId();
						SpotInspBO bo = new SpotInspBO();

						if ("2".equalsIgnoreCase(areaId)) {
							spForm.setSubAreaList(bo.getSubArea(language, areaId, spForm.getTehsilId(), "Y"));
						} else {
							spForm.setSubAreaList(bo.getSubArea(language, areaId, spForm.getTehsilId(), "N"));

						}
						FORWARD_JSP = "spotinsRrequest";

					}

					if (spForm.getActionName().equals("setMohalla")) {

						FORWARD_JSP = "spotinsRrequest";

					}
					if (spForm.getActionName().equals("setWard")) {

						FORWARD_JSP = "spotinsRrequest";

					}

					if (spForm.getActionName().equals("answerRadio")) {
						if (spForm.getQuestion().equalsIgnoreCase("Y")) {
							if (!spForm.getViewType().equalsIgnoreCase("District")) {
								spForm.setTehsilList(new ArrayList());
							}
							spForm.setMohallaList(new ArrayList());
							spForm.setWardList(new ArrayList());
							spForm.setTehsilId("-1");
							spForm.setAreaTypeId("-1");
							spForm.setMohallaId("-1");
							spForm.setWardId("-1");
							spForm.setWardPatwariId("-1");
							spForm.setSubAreaId("-1");
							spForm.setColonyId("-1");
							spForm.setWardIds("-1");
							// added by akansha
							if (!spForm.getViewType().equalsIgnoreCase("District")) {
								spForm.setDistrictId("-1");
							}
						}

						spForm.setAreaTypeList(objSpBD.getAreTypea(spForm.getLanguage()));

						FORWARD_JSP = "spotinsRrequest";

					}

					if (spForm.getActionName().equals("stateAction")) {
						spForm.setDurFrom("");
						spForm.setDurTo("");
						String select[] = new String[1];
						select[0] = "-1";
						spForm.setSelectedClauses(select);
						spForm.setDeedId("");
						spForm.setRangeId("");
						spForm.setPropertyId("");
						spForm.setPropertyL2Id("");
						spForm.setPropertyL2List(new ArrayList());
						if (spForm.getUser().equalsIgnoreCase("I")) {
							spForm.setDistrictList(new ArrayList());

						}

						spForm.setZoneList(objSpBD.getZone("1", spForm.getLanguage()));

						spForm.setQuestion("N");
						spForm.setZoneId("");
						spForm.setDistrictId("");
						FORWARD_JSP = "spotinsRrequest";

					}

					if (spForm.getActionName().equals("getFilteredList")) {

						ArrayList temp = objSpBD.clonedData(spForm.getMainCountList());

						request.setAttribute("spotCriteriaList", objSpBD.applyPercentage(temp, Integer.parseInt(spForm.getPercentage())));

						FORWARD_JSP = "spotcriteria";

					}

					if (spForm.getActionName().equals("viewReinspectionDet")) {

						ArrayList list = objSpBD.getReInspectionDocument(spForm);
						spForm.setReinspectionList(list);
						FORWARD_JSP = "reassigningDrillDownReport";

					}

					if (spForm.getActionName().equals("allotreinspection")) {
						String a[] = spForm.getCheckBoxData().split(",");
						ArrayList list = spForm.getReinspectionList();
						ArrayList finalList = objSpBD.getReFilterList(list, a, office);
						spForm.setReinspectionList(finalList);
						FORWARD_JSP = "reassigningDrillDownReport1";

					}

					if (spForm.getActionName().equals("assignreInspection")) {

						ArrayList list = spForm.getReinspectionList();

						HashMap<String, ArrayList<SpotInspDTO>> data = objSpBD.reassignData(list, userId);

						spForm.setReinspMap(data);
						ArrayList<SpotInspDTO> finalPageList = new ArrayList<SpotInspDTO>();
						if (data != null) {

							Iterator<String> srKeySet = data.keySet().iterator();

							while (srKeySet.hasNext()) {
								String key = srKeySet.next();
								ArrayList<SpotInspDTO> temp = data.get(key);
								int size = temp.size();
								if (size > 0) {

									SpotInspDTO t = temp.get(0);
									SpotInspDTO u = new SpotInspDTO();
									u.setZoneName(t.getZoneName());
									u.setSpotId(t.getSpotId());
									u.setSrRoleId(t.getSrRoleId());
									u.setReassignPersonName(t.getReassignPersonName());
									u.setNoOfDocuments(String.valueOf(size));
									if (spForm.getLanguage().equalsIgnoreCase("en"))
										u.setStatus("Assigned For Spot Re-Inpection");
									else
										u.setStatus("स्थल पुनः निरीक्षण के लिए सौंपा गया");
									finalPageList.add(u);
								}

							}

						}

						request.setAttribute("ReisnpTwoLevelData", finalPageList);
						spForm.setReassignFinalList(finalPageList);
						FORWARD_JSP = "reassignTwoLevelReport";

					}
					if (spForm.getActionName().equals("reassignPopDown")) {
						HashMap<String, ArrayList<SpotInspDTO>> data = spForm.getReinspMap();
						String id = request.getParameter(("srId"));
						String[] userIds = id.split("~");

						ArrayList<SpotInspDTO> dto = data.get(userIds[0]);
						request.setAttribute("ReisnpTwoLevelData", spForm.getReassignFinalList());
						request.setAttribute("ReisnpTwoLevelDrillData", dto);

						FORWARD_JSP = "reassignTwoLevelReport";
					}

					if (spForm.getActionName().equals("getSro")) {
						logger.debug("values" + spForm.getDto().getOffice_id());
						String values[] = spForm.getDto().getOffice_id().split("~");
						String officeId = values[0];

						ArrayList list = spForm.getReinspectionList();
						for (int i = 0; i < list.size(); i++) {
							SpotInspDTO ab = (SpotInspDTO) list.get(i);
							String reg = ab.getRegIdcompletion();
							if (reg.equals(values[1])) {
								logger.debug("match");
								ArrayList li = new ArrayList();
								if (!officeId.equalsIgnoreCase("-1")) {
									li = objSpBD.getSRORoleList(spForm.getDto().getOffice_id(), reg);
									ab.setSrRoleList(li);

									ab.setOffice_id(spForm.getDto().getOffice_id());
								} else {
									ab.setOffice_id("-1");
								}
								break;
							}
						}

						// ArrayList list = spForm.getReinspectionList();
						// ArrayList finalList =
						// objSpBD.getReFilterList(list,a);
						// spForm.setReinspectionList(finalList);
						FORWARD_JSP = "reassigningDrillDownReport1";

					}

					if (spForm.getActionName().equals("getSrofficers")) {
						logger.debug("values" + spForm.getDto().getSrRoleId());
						String values[] = spForm.getDto().getSrRoleId().split("~");
						String srRoleId = values[0];
						ArrayList list = spForm.getReinspectionList();
						for (int i = 0; i < list.size(); i++) {
							SpotInspDTO ab = (SpotInspDTO) list.get(i);
							String reg = ab.getRegIdcompletion();
							if (reg.equals(values[1])) {
								logger.debug("match");
								ab.setSrRoleId(spForm.getDto().getSrRoleId());
								ab.setSrRoleName(spForm.getDto().getSrRoleName());
								break;
							}
						}
						// ArrayList list = spForm.getReinspectionList();
						// ArrayList finalList =
						// objSpBD.getReFilterList(list,a);
						// spForm.setReinspectionList(finalList);
						FORWARD_JSP = "reassigningDrillDownReport1";

					}

					if (spForm.getActionName().equals("seeReport")) {

						ArrayList allDto = new ArrayList();
						HashMap assignList = new HashMap();
						if ("District".equalsIgnoreCase(spForm.getViewType())) {
							logger.debug("Entered district see Report");
							HashMap data = spForm.getFinalValues();

							Iterator<String> a = data.keySet().iterator();
							String districtId = spForm.getDistrictId();
							ArrayList<String> ids = new ArrayList<String>();
							ArrayList finals = new ArrayList();
							System.out.println("District Id is " + districtId);
							while (a.hasNext()) {
								String key = a.next();
								String d[] = key.split("~");
								System.out.println("Key " + key + " " + d[0]);
								if (d[0].equalsIgnoreCase(districtId)) {
									System.out.println("Adding Key" + key);
									ids.add(key);

								}

							}

							for (int i = 0; i < ids.size(); i++) {

								finals.add(data.get(ids.get(i)));

							}

							System.out.println(finals.size());
							for (int j = 0; j < finals.size(); j++) {
								ArrayList temp = (ArrayList) finals.get(j); // temp
																			// contains
																			// multiple
																			// dtos
								System.out.println(temp.size());
								double size = temp.size();
								int percentage = Integer.parseInt(spForm.getPercentage());
								size = (size * percentage) / 100;
								int sizefinal = (int) Math.round(size);

								for (int k = temp.size() - 1; k >= sizefinal; k--) {

									temp.remove(k);

								}
								for (int l = 0; l < temp.size(); l++) {
									allDto.add(temp.get(l));
								}
								ArrayList temp1 = (ArrayList) finals.get(j);
								System.out.println(temp1.size());
							}

						}

						else if ("State".equalsIgnoreCase(spForm.getViewType())) {
							HashMap data = spForm.getFinalValues();
							Iterator<String> a = data.keySet().iterator();
							ArrayList finals = new ArrayList();
							while (a.hasNext()) {
								finals.add(data.get(a.next()));
							}

							System.out.println(finals.size());
							for (int j = 0; j < finals.size(); j++) {
								ArrayList temp = (ArrayList) finals.get(j);
								System.out.println(temp.size());
								double size = temp.size();
								int percentage = Integer.parseInt(spForm.getPercentage());
								size = (size * percentage) / 100;
								int sizefinal = (int) Math.round(size);

								for (int k = temp.size() - 1; k >= sizefinal; k--) {

									temp.remove(k);

								}

								for (int l = 0; l < temp.size(); l++) {
									allDto.add(temp.get(l));
								}
								ArrayList temp1 = (ArrayList) finals.get(j);
								System.out.println(temp1.size());
							}
							System.out.println("All dto : " + allDto.size());
						}

						else {
							HashMap data = spForm.getFinalValues();
							Iterator<String> a = data.keySet().iterator();
							String zoneId = spForm.getZoneId();
							ArrayList<String> ids = new ArrayList<String>();
							ArrayList finals = new ArrayList();

							while (a.hasNext()) {
								String key = a.next();
								String d[] = key.split("~");
								if (d[1].equalsIgnoreCase(zoneId)) {
									ids.add(key);

								}
							}

							for (int i = 0; i < ids.size(); i++) {

								finals.add(data.get(ids.get(i)));

							}

							for (int j = 0; j < finals.size(); j++) {
								ArrayList temp = (ArrayList) finals.get(j);
								System.out.println(temp.size());
								double size = temp.size();
								int percentage = Integer.parseInt(spForm.getPercentage());
								size = (size * percentage) / 100;

								int sizefinal = (int) Math.round(size);

								for (int k = temp.size() - 1; k >= sizefinal; k--) {

									temp.remove(k);

								}

								for (int l = 0; l < temp.size(); l++) {
									allDto.add(temp.get(l));
								}

								ArrayList temp1 = (ArrayList) finals.get(j);
								System.out.println(temp1.size());
							}

						}
						if (!allDto.isEmpty() || allDto.size() > 0) {
							request.setAttribute("FinalList", allDto);
							spForm.setAssignSpotList(allDto);
							spForm.setMaintain("1");
						} else {
							spForm.setMaintain("0");
						}

						if ("Y".equalsIgnoreCase(spForm.getQuestion())) {
							FORWARD_JSP = "spotcriteriasucces";
						} else if ("N".equalsIgnoreCase(spForm.getQuestion())) {

							FORWARD_JSP = "spotcriteriasucces1";

						}

					}
					if (spForm.getActionName().equals("spotCriteria")) {

						// changes done by akansha for spot inspection date
						// configuration
						if (validateDate) {

							if (request.getParameterValues("clauseId") != null) {
								String values[] = request.getParameterValues("clauseId");
								String dummy = "";
								for (int i = 0; i < values.length; i++) {
									if (!values[i].equalsIgnoreCase("-1")) {
										if (i != values.length - 1)
											dummy = dummy + values[i] + ",";
										else
											dummy = dummy + values[i];
									}
								}

								spForm.setSubclauselist(dummy);
							}
							String propType = objSpBD.getPropIdSearch(spForm.getPropertyId());
							spForm.setLoggedInOffice(office);
							if ("1".equalsIgnoreCase(propType)) {
								spForm.setSpotCriteriaList(objSpBD.getCriteriaDocumentPlot(spForm));
							} else if ("3".equalsIgnoreCase(propType)) {
								spForm.setSpotCriteriaList(objSpBD.getCriteriaDocumentAgri(spForm));
							} else if ("2".equalsIgnoreCase(propType)) {
								spForm.setSpotCriteriaList(objSpBD.getCriteriaDocumentBuild(spForm));
							}
							if (spForm.getSpotCriteriaList() != null) {
								ArrayList temp = objSpBD.clonedData(spForm.getMainCountList());

								ArrayList newList = objSpBD.applyPercentage(temp, Integer.parseInt(spForm.getPercentage()));
								logger.info("getSpotCriteriaList --  " + spForm.getSpotCriteriaList().size());
								request.setAttribute("spotCriteriaList", newList);
								spForm.setCheckError("1");
							} else {
								spForm.setCheckError("0");

							}
							FORWARD_JSP = "spotcriteria";
						} else {

							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of " + durationSQL + " days or less than that");
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी " + durationSQL + " दिन या उस से कम की रखें।");
							if(dateCheck.equalsIgnoreCase("N")){
								if (language.equalsIgnoreCase("en"))
									request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Duration has not selected properly");
								else
									request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी " + durationSQL + " दिन या उस से कम की रखें।");
							}
							FORWARD_JSP = "spotinsRrequest";
						}

					}
					if (spForm.getActionName().equals("popupRegDetails"))// WHEN
																			// ANY
																			// SPOT
																			// ORDER
																			// ID
																			// IS
																			// CLICKED
																			// FROM
																			// SR
																			// WORKLIST
					{
						String spot = request.getParameter("key");
						HashMap<String, ArrayList<SpotInspForm>> temp = spForm.getViewList();

						ArrayList<SpotInspForm> t = temp.get(spot);
						String regId = spForm.getObjSIDto().getRegId();

						// added by Shreeraj
						for (SpotInspForm sif : t) {
							spForm.setOrderDate(sif.getApplicationDate());
							spForm.setSpotInspTxnId(sif.getSpotId());
						}
						spForm.setPhotoCopyName("");
						spForm.setPhotCopyPath("");
						spForm.setInspStatus("");
						spForm.setChangeVal("");
						objSpBD.getBifercatedDuties(regId, spForm);

						request.setAttribute("RegList", t);

						System.out.println(t.size());

						spForm.setActionName("");
					}
					if (spForm.getActionName().equals("popUpDetails"))// GGG
					{

						String id = request.getParameter(("srId"));
						spForm.setCurrentSr(id);
						HashMap<String, ArrayList<SpotInspDTO>> value = spForm.getSrFinalList();
						if (id != null && !("".equalsIgnoreCase(id))) {
							ArrayList<SpotInspDTO> temp = value.get(id);

							request.setAttribute("PopupList", temp);

						}

						request.setAttribute("AssginedList", spForm.getDisplayList());
						FORWARD_JSP = "spotcriteriaAlloted";

					}

					if (spForm.getActionName().equals("viewSpotDashboard")) {

						String id = request.getParameter(("spotId"));
						ArrayList dataList = objSpBD.getDrillDownDashboardValues(id);
						ArrayList data = spForm.getAssignList();

						// Added by Akansha - STQC
						String status = objSpBD.getInspectionStatus(id);
						logger.debug("Inspection Status - " + status);
						if (status != null && !status.isEmpty()) {
							if (status.equalsIgnoreCase("Y"))
								spForm.setStatus("Y");
							if (status.equalsIgnoreCase("C"))
								spForm.setStatus("C");
						}
						request.setAttribute("dashboardForm", data);
						request.setAttribute("drillDashboard", dataList);
						request.setAttribute("spotIDupdated", id);
						FORWARD_JSP = "success";

					}
					if (spForm.getActionName().equals("viewReSpotDashboard")) {

						String id = request.getParameter(("spotId"));
						ArrayList dataList = objSpBD.getReDrillDownDashboardValues(id);

						ArrayList data = spForm.getReassignFinalList();
						spForm.setAssignList(dataList);
						request.setAttribute("dashboardForm", data);
						request.setAttribute("drillReDashboard", dataList);
						FORWARD_JSP = "successReInspect";

					}

					if (spForm.getActionName().equals("getPropertyL2")) {
						spForm.getClauseIds();
						String propertyId = spForm.getPropertyId();
						ArrayList propertyL2 = new ArrayList();
						try {
							String prop[] = propertyId.split("~");

							propertyL2 = objSpBD.getPropertyL2(prop[0], spForm.getLanguage());
						} catch (Exception k) {
							logger.debug(k.getMessage());
						}
						// logger.info("getSpotCriteriaList --  "+spForm.getSpotCriteriaList().size());
						// request.setAttribute("spotCriteriaList",spForm.getSpotCriteriaList());
						spForm.setPropertyL2List(propertyL2);
						FORWARD_JSP = "spotinsRrequest";

					}
					if (spForm.getActionName().equals("AssignSpot")) {
						ArrayList m = spForm.getAssignSpotList();
						HashMap<String, ArrayList> data = new HashMap<String, ArrayList>();
						TreeSet<String> officeId = new TreeSet<String>();
						for (int i = 0; i < m.size(); i++) {
							SpotInspDTO a = (SpotInspDTO) m.get(i);

							officeId.add(a.getRegistrationOfficeId()); // adding
																		// distinct
																		// office
																		// ids
																		// in
																		// treeset.

						}
						Iterator<String> ids = officeId.iterator();

						System.out.println(officeId.size() + " Office Ids");
						while (ids.hasNext()) {
							String id = ids.next();
							ArrayList d = new ArrayList();
							for (int i = 0; i < m.size(); i++) {
								SpotInspDTO a = (SpotInspDTO) m.get(i);
								if (a.getRegistrationOfficeId().equalsIgnoreCase(id)) {
									d.add(m.get(i));

								}

							}
							System.out.println(d.size());
							data.put(id, d); // creating office id- rEgistration
												// numbers hashmap
						}

						System.out.println(data.size() + " Final hashmap size"); // size
																					// should
																					// be
																					// equal
																					// to
																					// number
																					// of
																					// distinct
																					// office
																					// ids

						/*
						 * HashMap<String, ArrayList> srList = new
						 * HashMap<String, ArrayList>(); // hashmap of office id
						 * and sr lists ArrayList<SpotInspDTO> dto = new
						 * ArrayList<SpotInspDTO>();
						 * 
						 * SpotInspDTO a= new SpotInspDTO();
						 * a.setSrName("Atul"); a.setSrId("7777777");
						 * dto.add(a);
						 * 
						 * SpotInspDTO b= new SpotInspDTO();
						 * b.setSrName("Vatul"); b.setSrId("666666");
						 * dto.add(b);
						 * 
						 * ArrayList<SpotInspDTO> dto1 = new
						 * ArrayList<SpotInspDTO>();
						 * 
						 * SpotInspDTO c= new SpotInspDTO();
						 * c.setSrName("Mandala"); c.setSrId("77127777127");
						 * dto1.add(c);
						 * 
						 * srList.put("OFC85",dto ); srList.put("OFC115",dto1);
						 */

						Iterator<String> ids1 = officeId.iterator();
						HashMap<String, ArrayList<SpotInspDTO>> srList = objSpBD.getSrLists(ids1);
						Iterator<String> ite = data.keySet().iterator();

						while (ite.hasNext()) {
							String office_id = ite.next();
							ArrayList<SpotInspDTO> edata = data.get(office_id.trim());;
							ArrayList<SpotInspDTO> srdata = srList.get(office_id.trim());;
							int srSize = srdata.size();
							int j = 0;
							for (int y = 0; y < edata.size(); y++) {

								SpotInspDTO currentDto = edata.get(y);
								SpotInspDTO currentSrData = srdata.get(j);
								// added by saurav
								String docCompleteBy = currentDto.getDocCompletionSR().trim();
								if (currentSrData.getSrId().equalsIgnoreCase(docCompleteBy)) {
									j++;
									if (j == srSize) {
										j = 0;
									}
									currentSrData = srdata.get(j);
									currentDto.setAssginPersonUserId(currentSrData.getSrId());
									currentDto.setAssignPersonName(currentSrData.getSrName());
								} else {
									currentDto.setAssginPersonUserId(currentSrData.getSrId());
									currentDto.setAssignPersonName(currentSrData.getSrName());
								}
								/*
								 * currentDto.setAssginPersonUserId(currentSrData
								 * .getSrId());
								 * currentDto.setAssignPersonName(currentSrData
								 * .getSrName());
								 */

								j++;
								if (j == srSize) {
									j = 0;

								}
							}

						}

						Iterator<String> of = data.keySet().iterator();
						ArrayList<SpotInspDTO> allData = new ArrayList<SpotInspDTO>();
						while (of.hasNext()) {
							String idOfdata = of.next();
							ArrayList<SpotInspDTO> temp = data.get(idOfdata);

							for (int g = 0; g < temp.size(); g++) {
								allData.add(temp.get(g));

							}

						}

						TreeSet<String> srIds = new TreeSet<String>();
						for (int i = 0; i < allData.size(); i++) {
							SpotInspDTO as = allData.get(i);

							if (as.getAssginPersonUserId() == null) {
								srIds.add("");
							} else {
								srIds.add(as.getAssginPersonUserId()); // adding
																		// sr
																		// office
																		// ids
																		// in
																		// treeset.
							}

						}
						Iterator<String> srIterator = srIds.iterator();
						HashMap<String, ArrayList<SpotInspDTO>> srMap = new HashMap<String, ArrayList<SpotInspDTO>>();

						while (srIterator.hasNext()) {
							String id = srIterator.next();
							ArrayList d = new ArrayList();
							for (int i = 0; i < allData.size(); i++) {
								SpotInspDTO al = allData.get(i);
								if (al.getAssginPersonUserId().equalsIgnoreCase(id)) {
									d.add(allData.get(i));

								}

							}

							srMap.put(id, d); // creating office id-
												// rEgistration numbers hashmap
						}
						// We have got the distimct sr ids

						boolean checkInsert = objSpBD.insertAssignSpotData(srMap, roleId, funId, userId);

						spForm.setSrFinalList(srMap);
						ArrayList<SpotInspDTO> finalPageList = new ArrayList<SpotInspDTO>();
						if (checkInsert) {
							Iterator<String> srKeySet = srMap.keySet().iterator();

							while (srKeySet.hasNext()) {
								String key = srKeySet.next();
								ArrayList<SpotInspDTO> temp = srMap.get(key);
								int size = temp.size();
								if (size > 0) {

									SpotInspDTO t = temp.get(0);
									SpotInspDTO u = new SpotInspDTO();
									u.setZoneName(t.getZoneName());
									u.setSpotId(t.getSpotId());
									u.setAssignPersonName(t.getAssignPersonName());
									u.setAssginPersonUserId(t.getAssginPersonUserId());
									u.setNoOfDocuments(String.valueOf(size));
									// objSpBD.initiateEmailData(t.getAssginPersonUserId(),userId);
									if (spForm.getLanguage().equalsIgnoreCase("en"))
										u.setStatus("Assigned For Spot Inpection");
									else
										u.setStatus("स्थल निरीक्षण के लिए सौंपा गया ");
									finalPageList.add(u);
								}

							}

						} else {

						}
						spForm.setDisplayList(finalPageList);

						request.setAttribute("AssginedList", finalPageList);
						FORWARD_JSP = "spotcriteriaAlloted";

					}
					if (spForm.getActionName().equals("spotReInspectionCriteria"))// Re-inspection
																					// Start;
					{
						// changes done by akansha for spot inspection date
						// configuration
						if (validateDate) {
							spForm.setSpotCriteriaList(objSpBD.getDocumentReInspection(spForm));
							logger.info("getSpotCriteriaList --  " + spForm.getSpotCriteriaList().size());
							request.setAttribute("spotCriteriaList", spForm.getSpotCriteriaList());
						}

						else {

							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of " + durationSQL + " days or less than that");
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी " + durationSQL + " दिन या उस से कम की रखें।");
							if(dateCheck.equalsIgnoreCase("N")){
								if (language.equalsIgnoreCase("en"))
									request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Duration has not selected properly");
								else
									request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी " + durationSQL + " दिन या उस से कम की रखें।");
							}
							FORWARD_JSP = "spotinsRrequest";

						}
					}

					if (spForm.getActionName().equals("spotReInspectionSelection")) {

						spForm = objSpBD.getDrReqRes(spForm);
						request.setAttribute("spotVRList", spForm.getSpotViewList());
						request.setAttribute("visitForm", spForm);

						FORWARD_JSP = "reinspectionDetail";

						logger.info("SpotInspAction ..........FORWARD_JSP==" + FORWARD_JSP);
						request.setAttribute("spotCriteriaList", spForm.getSpotViewList());

					}
					if (spForm.getActionName().equals("assignDistrictCriteria1")) {
						String id = request.getParameter("districtId");

						spForm.setDistrictId(id);

						spForm.setViewType("District");
						spForm.setMainCountList((objSpBD.getCriteriaDocumentHyperLink(spForm)));

						ArrayList temp = objSpBD.clonedData(spForm.getMainCountList());
						request.setAttribute("spotCriteriaList", objSpBD.applyPercentage(temp, Integer.parseInt(spForm.getPercentage())));

						FORWARD_JSP = "spotcriteria";

					}

					if (spForm.getActionName().equals("assignDistrictCriteria")) {
						logger.info("dist size--  " + spForm.getDistrictId());
						logger.info("dist size--  " + spForm.getSpotCriteriaList().size());
						spForm.setSpotCriteriaList(objSpBD.getAssignCriteriaDocument(spForm));
						logger.info("dist size--  " + spForm.getSpotCriteriaList().size());
						spForm.setSrList(objSpBD.getDistSrList(spForm.getDistrictId()));
						spForm.setDrList(objSpBD.getDistDrList(spForm.getDistrictId()));
						int assignPerValue = Integer.parseInt(objSpBD.getAssignPercantage());
						logger.info("assignPerValue " + assignPerValue);
						int percent = (assignPerValue) * (spForm.getSpotCriteriaList().size());
						logger.info("percent " + percent);
						int assignPerCentData = (percent) / 100;
						logger.info("assignPerCentData " + assignPerCentData);
						for (int m = 0; m < spForm.getDrList().size(); m++) {
							spForm.getSpotCriteriaList().size();

						}
						String drName = null;
						String drId = null;
						for (int m = 0; m < spForm.getDrList().size(); m++) {
							SpotInspDTO drdto = (SpotInspDTO) spForm.getDrList().get(m);
							drName = drdto.getUserName();
							drId = drdto.getUserId();
							logger.info("drName " + drName);
							logger.info("drId " + drId);
						}

						ArrayList assignCriteriaList = new ArrayList();
						int criteriaListSize = spForm.getSpotCriteriaList().size();
						logger.info("criteriaListSize--  " + criteriaListSize);
						int srListSize = spForm.getSrList().size();
						logger.info("rListSize--  " + srListSize);
						int count = 1;
						logger.info("criteriaListSize/srListSize1 --  " + criteriaListSize / srListSize);
						if (criteriaListSize == srListSize) {
							logger.info("criteriaListSize/srListSize 2--  " + criteriaListSize / srListSize);
							for (int j = 0; j < srListSize; j++)
								for (int i = 0; i <= 10; i++) {
									logger.info("criteriaListSize/srListSize3 --  " + criteriaListSize / srListSize);
									SpotInspDTO assignSrDTO = new SpotInspDTO();
									SpotInspDTO dto = (SpotInspDTO) spForm.getSpotCriteriaList().get(i);
									SpotInspDTO srdto = (SpotInspDTO) spForm.getSrList().get(j);
									assignSrDTO.setDistrictName(dto.getDistrictName());
									assignSrDTO.setDistrictId(dto.getDistrictId());
									assignSrDTO.setZoneName(dto.getZoneName());
									assignSrDTO.setZoneId(dto.getZoneId());
									assignSrDTO.setCount(Integer.toString(count));
									assignSrDTO.setRegId(dto.getRegId());
									assignSrDTO.setTehsilId(dto.getTehsilId());
									assignSrDTO.setTehsilName(dto.getTehsilName());
									assignSrDTO.setMarketValue(dto.getMarketValue());
									assignSrDTO.setRegFee(dto.getRegFee());
									assignSrDTO.setStampDuty(dto.getStampDuty());
									logger.info("dto.getRegId() --  " + assignSrDTO.getRegId());
									assignSrDTO.setUserName(srdto.getUserName());
									assignSrDTO.setUserId(srdto.getUserId());
									logger.info("dto.getUserName() --  " + assignSrDTO.getUserName());
									assignSrDTO.setUserId(srdto.getUserId());
									logger.info("dto.getRegId() --  " + assignSrDTO.getUserId());
									assignSrDTO.setDrName(drName);
									assignSrDTO.setDrId(drId);
									logger.info("assignSrDTO.drId() --  " + drId);
									assignCriteriaList.add(assignSrDTO);
									logger.info("dto.setCount() --  " + count);
									count++;
								}

						}

						else if (criteriaListSize / srListSize > 1) {

							for (int j = 0; j < spForm.getSrList().size(); j++) {
								logger.info("loop count " + j);
								logger.info("spForm.getSpotCriteriaList().size()/srListSize " + spForm.getSpotCriteriaList().size() / srListSize);
								for (int i = 0; i < criteriaListSize / srListSize; i++) {
									logger.info("criteriaListSize/srListSize3 --  " + criteriaListSize / srListSize);
									SpotInspDTO assignSrDTO = new SpotInspDTO();
									SpotInspDTO dto = (SpotInspDTO) spForm.getSpotCriteriaList().get(i);
									if (spForm.getSrList().size() - 1 > j) {
										spForm.getSpotCriteriaList().remove(i);
									}
									SpotInspDTO srdto = (SpotInspDTO) spForm.getSrList().get(j);
									assignSrDTO.setDistrictName(dto.getDistrictName());
									logger.info("dto.setCount() --  " + count);
									assignSrDTO.setDistrictId(dto.getDistrictId());
									assignSrDTO.setZoneName(dto.getZoneName());
									assignSrDTO.setZoneId(dto.getZoneId());
									assignSrDTO.setCount(Integer.toString(count));
									assignSrDTO.setRegId(dto.getRegId());
									assignSrDTO.setTehsilId(dto.getTehsilId());
									assignSrDTO.setTehsilName(dto.getTehsilName());
									assignSrDTO.setMarketValue(dto.getMarketValue());
									assignSrDTO.setRegFee(dto.getRegFee());
									assignSrDTO.setStampDuty(dto.getStampDuty());
									logger.info("dto.getRegId() --  " + dto.getRegId());
									dto.setUserName(srdto.getUserName());
									assignSrDTO.setUserName(dto.getUserName());
									logger.info("dto.getRegId() --  " + assignSrDTO.getUserName());
									dto.setUserId(srdto.getUserId());
									assignSrDTO.setUserId(srdto.getUserId());
									assignSrDTO.setDrName(drName);
									assignSrDTO.setDrId(drId);
									logger.info("assignSrDTO.drId() --  " + drId);
									assignCriteriaList.add(assignSrDTO);
									logger.info("criteriaListSize size --  " + spForm.getSpotCriteriaList().size());
									if (spForm.getSpotCriteriaList().size() < 2) {
										break;
									}
									count++;
								}
							}
						} else {
							for (int i = 0; i < criteriaListSize; i++) {
								logger.info("criteriaListSize/srListSize3 --  " + criteriaListSize / srListSize);
								SpotInspDTO assignSrDTO = new SpotInspDTO();
								SpotInspDTO srdto = new SpotInspDTO();
								SpotInspDTO dto = (SpotInspDTO) spForm.getSpotCriteriaList().get(i);
								if (spForm.getSpotCriteriaList().size() > spForm.getSrList().size()) {
									spForm.getSpotCriteriaList().remove(i);
									criteriaListSize = spForm.getSpotCriteriaList().size();
								}
								if (i < srListSize)
									srdto = (SpotInspDTO) spForm.getSrList().get(i);
								else {
									srdto = (SpotInspDTO) spForm.getSrList().get(i);
								}
								assignSrDTO.setDistrictName(dto.getDistrictName());
								logger.info("dto.setCount() --  " + count);
								assignSrDTO.setDistrictId(dto.getDistrictId());
								assignSrDTO.setZoneName(dto.getZoneName());
								assignSrDTO.setZoneId(dto.getZoneId());
								assignSrDTO.setCount(Integer.toString(count));
								assignSrDTO.setRegId(dto.getRegId());
								assignSrDTO.setTehsilId(dto.getTehsilId());
								assignSrDTO.setTehsilName(dto.getTehsilName());
								assignSrDTO.setMarketValue(dto.getMarketValue());
								assignSrDTO.setRegFee(dto.getRegFee());
								assignSrDTO.setStampDuty(dto.getStampDuty());
								logger.info("dto.getRegId() --  " + dto.getRegId());
								dto.setUserName(srdto.getUserName());
								assignSrDTO.setUserName(dto.getUserName());
								logger.info("dto.getRegId() --  " + assignSrDTO.getUserName());
								dto.setUserId(srdto.getUserId());
								assignSrDTO.setUserId(srdto.getUserId());
								assignSrDTO.setDrName(drName);
								assignSrDTO.setDruserId(drId);
								logger.info("assignSrDTO.drId() --  " + drId);
								assignCriteriaList.add(assignSrDTO);
								logger.info("criteriaListSize size --  " + spForm.getSpotCriteriaList().size());
								if (spForm.getSpotCriteriaList().size() < 2) {
									break;
								}
								count++;
							}
						}

						if (assignPerCentData != assignCriteriaList.size() && assignPerCentData < assignCriteriaList.size()) {
							logger.info("assignPerCentData  --  " + assignPerCentData);
							logger.info("assignCriteriaList.size()  --  " + assignCriteriaList.size());
							for (int i = assignCriteriaList.size() - 1; i > assignPerCentData - 1; i--)
								assignCriteriaList.remove(i);
						}

						spForm.setAssignedCriteriaList(assignCriteriaList);
						logger.info("getAssignedCriteriaList --  " + spForm.getAssignedCriteriaList().size());
						request.setAttribute("assignCriteriaList", spForm.getAssignedCriteriaList());
						FORWARD_JSP = "spotcriteriaassign";

					}

					if (spForm.getActionName().equals("assignDistrictCriteriaTest")) {
						logger.info("dist size--  " + spForm.getDistrictId());
						logger.info("dist size--  " + spForm.getSpotCriteriaList().size());
						spForm.setSpotCriteriaList(objSpBD.getAssignCriteriaDocumentTest(spForm));
						logger.info("dist size--  " + spForm.getSpotCriteriaList().size());
						spForm.setSrList(objSpBD.getDistSrList(spForm.getDistrictId()));
						spForm.setDrList(objSpBD.getDistDrList(spForm.getDistrictId()));
						int assignPerValue = Integer.parseInt(objSpBD.getAssignPercantage());
						logger.info("assignPerValue " + assignPerValue);
						int percent = (assignPerValue) * (spForm.getSpotCriteriaList().size());
						logger.info("percent " + percent);
						int assignPerCentData = (percent) / 100;
						logger.info("assignPerCentData " + assignPerCentData);
						for (int m = 0; m < spForm.getDrList().size(); m++)// incomplete
						{
							spForm.getSpotCriteriaList().size();

						}
						String drName = null;
						String drId = null;
						for (int m = 0; m < spForm.getDrList().size(); m++)// incomplete
						{
							SpotInspDTO drdto = (SpotInspDTO) spForm.getDrList().get(m);
							drName = drdto.getUserName();
							drId = drdto.getUserId();
							logger.info("drName " + drName);
							logger.info("drId " + drId);
						}

						ArrayList assignCriteriaList = new ArrayList();
						int criteriaListSize = spForm.getSpotCriteriaList().size();
						logger.info("criteriaListSize--  " + criteriaListSize);
						int srListSize = spForm.getSrList().size();
						logger.info("rListSize--  " + srListSize);
						int count = 1;
						logger.info("criteriaListSize/srListSize1 --  " + criteriaListSize / srListSize);
						if (criteriaListSize == srListSize) {
							logger.info("criteriaListSize/srListSize 2--  " + criteriaListSize / srListSize);
							for (int j = 0; j < srListSize; j++)
								for (int i = 0; i <= 10; i++) {
									logger.info("criteriaListSize/srListSize3 --  " + criteriaListSize / srListSize);
									SpotInspDTO assignSrDTO = new SpotInspDTO();
									SpotInspDTO dto = (SpotInspDTO) spForm.getSpotCriteriaList().get(i);
									SpotInspDTO srdto = (SpotInspDTO) spForm.getSrList().get(j);
									assignSrDTO.setDistrictName(dto.getDistrictName());
									assignSrDTO.setDistrictId(dto.getDistrictId());
									assignSrDTO.setZoneName(dto.getZoneName());
									assignSrDTO.setZoneId(dto.getZoneId());
									assignSrDTO.setCount(Integer.toString(count));
									assignSrDTO.setRegId(dto.getRegId());
									assignSrDTO.setTehsilId(dto.getTehsilId());
									assignSrDTO.setTehsilName(dto.getTehsilName());
									assignSrDTO.setMarketValue(dto.getMarketValue());
									assignSrDTO.setRegFee(dto.getRegFee());
									assignSrDTO.setStampDuty(dto.getStampDuty());
									logger.info("dto.getRegId() --  " + assignSrDTO.getRegId());
									assignSrDTO.setUserName(srdto.getUserName());
									assignSrDTO.setUserId(srdto.getUserId());
									logger.info("dto.getUserName() --  " + assignSrDTO.getUserName());
									assignSrDTO.setUserId(srdto.getUserId());
									logger.info("dto.getRegId() --  " + assignSrDTO.getUserId());
									assignSrDTO.setDrName(drName);
									assignSrDTO.setDrId(drId);
									logger.info("assignSrDTO.drId() --  " + drId);
									assignCriteriaList.add(assignSrDTO);
									logger.info("dto.setCount() --  " + count);
									count++;
								}

						}

						else if (criteriaListSize / srListSize > 1) {

							for (int j = 0; j < spForm.getSrList().size(); j++) {
								logger.info("loop count " + j);
								logger.info("spForm.getSpotCriteriaList().size()/srListSize " + spForm.getSpotCriteriaList().size() / srListSize);
								for (int i = 0; i < criteriaListSize / srListSize; i++) {
									logger.info("criteriaListSize/srListSize3 --  " + criteriaListSize / srListSize);
									SpotInspDTO assignSrDTO = new SpotInspDTO();
									SpotInspDTO dto = (SpotInspDTO) spForm.getSpotCriteriaList().get(i);
									if (spForm.getSrList().size() - 1 > j) {
										spForm.getSpotCriteriaList().remove(i);
									}
									SpotInspDTO srdto = (SpotInspDTO) spForm.getSrList().get(j);
									assignSrDTO.setDistrictName(dto.getDistrictName());
									logger.info("dto.setCount() --  " + count);
									assignSrDTO.setDistrictId(dto.getDistrictId());
									assignSrDTO.setZoneName(dto.getZoneName());
									assignSrDTO.setZoneId(dto.getZoneId());
									assignSrDTO.setCount(Integer.toString(count));
									assignSrDTO.setRegId(dto.getRegId());
									assignSrDTO.setTehsilId(dto.getTehsilId());
									assignSrDTO.setTehsilName(dto.getTehsilName());
									assignSrDTO.setMarketValue(dto.getMarketValue());
									assignSrDTO.setRegFee(dto.getRegFee());
									assignSrDTO.setStampDuty(dto.getStampDuty());
									logger.info("dto.getRegId() --  " + dto.getRegId());
									dto.setUserName(srdto.getUserName());
									assignSrDTO.setUserName(dto.getUserName());
									logger.info("dto.getRegId() --  " + assignSrDTO.getUserName());
									dto.setUserId(srdto.getUserId());
									assignSrDTO.setUserId(srdto.getUserId());
									assignSrDTO.setDrName(drName);
									assignSrDTO.setDrId(drId);
									logger.info("assignSrDTO.drId() --  " + drId);
									assignCriteriaList.add(assignSrDTO);
									logger.info("criteriaListSize size --  " + spForm.getSpotCriteriaList().size());
									if (spForm.getSpotCriteriaList().size() < 2) {
										break;
									}
									count++;
								}
							}
						} else {
							for (int i = 0; i < criteriaListSize; i++) {
								logger.info("criteriaListSize/srListSize3 --  " + criteriaListSize / srListSize);
								SpotInspDTO assignSrDTO = new SpotInspDTO();
								SpotInspDTO srdto = new SpotInspDTO();
								SpotInspDTO dto = (SpotInspDTO) spForm.getSpotCriteriaList().get(i);
								if (spForm.getSpotCriteriaList().size() > spForm.getSrList().size()) {
									spForm.getSpotCriteriaList().remove(i);
									criteriaListSize = spForm.getSpotCriteriaList().size();
								}
								if (i < srListSize)
									srdto = (SpotInspDTO) spForm.getSrList().get(i);
								else {
									srdto = (SpotInspDTO) spForm.getSrList().get(i);
								}
								assignSrDTO.setDistrictName(dto.getDistrictName());
								logger.info("dto.setCount() --  " + count);
								assignSrDTO.setDistrictId(dto.getDistrictId());
								assignSrDTO.setZoneName(dto.getZoneName());
								assignSrDTO.setZoneId(dto.getZoneId());
								assignSrDTO.setCount(Integer.toString(count));
								assignSrDTO.setRegId(dto.getRegId());
								assignSrDTO.setTehsilId(dto.getTehsilId());
								assignSrDTO.setTehsilName(dto.getTehsilName());
								assignSrDTO.setMarketValue(dto.getMarketValue());
								assignSrDTO.setRegFee(dto.getRegFee());
								assignSrDTO.setStampDuty(dto.getStampDuty());
								logger.info("dto.getRegId() --  " + dto.getRegId());
								dto.setUserName(srdto.getUserName());
								assignSrDTO.setUserName(dto.getUserName());
								logger.info("dto.getRegId() --  " + assignSrDTO.getUserName());
								dto.setUserId(srdto.getUserId());
								assignSrDTO.setUserId(srdto.getUserId());
								assignSrDTO.setDrName(drName);
								assignSrDTO.setDruserId(drId);
								logger.info("assignSrDTO.drId() --  " + drId);
								assignCriteriaList.add(assignSrDTO);
								logger.info("criteriaListSize size --  " + spForm.getSpotCriteriaList().size());
								if (spForm.getSpotCriteriaList().size() < 2) {
									break;
								}
								count++;
							}
						}

						if (assignPerCentData != assignCriteriaList.size() && assignPerCentData < assignCriteriaList.size()) {
							logger.info("assignPerCentData  --  " + assignPerCentData);
							logger.info("assignCriteriaList.size()  --  " + assignCriteriaList.size());
							for (int i = assignCriteriaList.size() - 1; i > assignPerCentData - 1; i--)
								assignCriteriaList.remove(i);
						}

						spForm.setAssignedCriteriaList(assignCriteriaList);
						logger.info("getAssignedCriteriaList --  " + spForm.getAssignedCriteriaList().size());
						request.setAttribute("assignCriteriaList", spForm.getAssignedCriteriaList());
						FORWARD_JSP = "spotcriteriaassign";

					}

					if (spForm.getActionName().equals("assignZoneCriteria")) {

						String zoneId = request.getParameter("zoneId");

						spForm.setZoneId(zoneId);
						spForm.setViewType("Zone");
						spForm.setMainCountList((objSpBD.getCriteriaDocumentHyperLink(spForm)));

						ArrayList temp = objSpBD.clonedData(spForm.getMainCountList());
						request.setAttribute("spotCriteriaList", objSpBD.applyPercentage(temp, Integer.parseInt(spForm.getPercentage())));

						FORWARD_JSP = "spotcriteria";

					}

					if (spForm.getActionName().equals("reassignZoneCriteria")) {

						String zoneId = request.getParameter("zoneId");

						spForm.setZoneId(zoneId);
						spForm.setViewType("Zone");

						spForm.setSpotCriteriaList(objSpBD.getDocumentReInspection(spForm));
						logger.info("getSpotCriteriaList --  " + spForm.getSpotCriteriaList().size());
						request.setAttribute("spotCriteriaList", spForm.getSpotCriteriaList());

						FORWARD_JSP = "assigning";

					}

					if (spForm.getActionName().equals("reassignDistrictCriteria1")) {
						String id = request.getParameter("districtId");

						spForm.setDistrictId(id);

						spForm.setViewType("District");

						spForm.setSpotCriteriaList(objSpBD.getDocumentReInspection(spForm));
						logger.info("getSpotCriteriaList --  " + spForm.getSpotCriteriaList().size());
						request.setAttribute("spotCriteriaList", spForm.getSpotCriteriaList());

						FORWARD_JSP = "assigning";

					}

					if (spForm.getActionName().equals("SpotInspSchedule")) {

						spForm = objSpBD.getSpotSechRequest(spForm);
						FORWARD_JSP = "spotinsRrequest";

					}
					if (spForm.getActionName().equalsIgnoreCase("srViewpage1")) // View
																				// Page
																				// Details
					{
						spForm = objSpBD.getSpotViewRes(spForm);

						request.setAttribute("spotVRList", spForm.getSpotViewList());
						request.setAttribute("visitForm", spForm);
						FORWARD_JSP = "success";
					}
					if (spForm.getActionName().equalsIgnoreCase("srViewpage")) // View
																				// Page
																				// Details
					{
						if (validateDate) {// changes done by akansha for spot
											// inspection date configuration
							ArrayList data = objSpBD.getDashBoardDetails(spForm, userId);
							spForm.setAssignList(data);
							request.setAttribute("dashboardForm", data);
							FORWARD_JSP = "success";
						}

						else {

							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of " + durationSQL + " days or less than that");
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी " + durationSQL + " दिन या उस से कम की रखें।");
							if(dateCheck.equalsIgnoreCase("N")){
								if (language.equalsIgnoreCase("en"))
									request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Duration has not selected properly");
								else
									request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी " + durationSQL + " दिन या उस से कम की रखें।");
							}
							FORWARD_JSP = "spotView";

						}
						/*
						 * ArrayList temp = objSpBD.getSpotViewResFinal(spForm);
						 * request.setAttribute("spotVRList",
						 * spForm.getSpotViewList());
						 * request.setAttribute("visitForm",temp);
						 * FORWARD_JSP="success";
						 */
					}
					if (spForm.getActionName().equalsIgnoreCase("reViewpage")) {
						if (validateDate) {// changes done by akansha for spot
											// inspection date configuration
							spForm.setStatus("R");
							ArrayList data = objSpBD.getDashBoardDetails(spForm, userId);
							spForm.setReassignFinalList(data);
							request.setAttribute("dashboardForm", data);
							FORWARD_JSP = "successReInspect";
						} else {

							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of " + durationSQL + " days or less than that");
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी " + durationSQL + " दिन या उस से कम की रखें।");
							if(dateCheck.equalsIgnoreCase("N")){
								if (language.equalsIgnoreCase("en"))
									request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Duration has not selected properly");
								else
									request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी " + durationSQL + " दिन या उस से कम की रखें।");
							}
							FORWARD_JSP = "spotView";

						}
					}

					if (spForm.getActionName().equalsIgnoreCase("srCompletion"))// for
																				// creating
																				// work
																				// list
																				// of
																				// SR
					{

						if (validateDate) {// changes done by akansha for spot
											// inspection date configuration
							String currentDistrict = "";
							try {
								currentDistrict = objSpBD.getCurrentDistrict(office);
								logger.info("currentDistrict" + currentDistrict);
								spForm.setDistrictId(currentDistrict);
								session.setAttribute("currentLoggedInDistrict", spForm.getDistrictId());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							spForm = objSpBD.getSpotCompRes(spForm, spForm.getLanguage());

							logger.info("SpotInspAction-- SpotInspCompletion -- after getting the values " + spForm.getSpotViewList());
							request.setAttribute("spotVRList", spForm.getSpotViewList());
						}

						else {

							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of " + durationSQL + " days or less than that");
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी " + durationSQL + " दिन या उस से कम की रखें।");
							if(dateCheck.equalsIgnoreCase("N")){
								if (language.equalsIgnoreCase("en"))
									request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Duration has not selected properly");
								else
									request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी " + durationSQL + " दिन या उस से कम की रखें।");
							}
							FORWARD_JSP = "spotCompletion";

						}
					}

					if (spForm.getActionName().equalsIgnoreCase("reInspectionCompletion")) {

						if (validateDate) {// changes done by akansha for spot
											// inspection date configuration
							String currentDistrict = "";
							try {
								currentDistrict = objSpBD.getCurrentDistrict(office);
								logger.info("currentDistrict" + currentDistrict);
								spForm.setDistrictId(currentDistrict);
								session.setAttribute("currentLoggedInDistrict", spForm.getDistrictId());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							spForm.setStatus("R");
							spForm = objSpBD.getSpotCompRes(spForm, spForm.getLanguage());
							logger.info("SpotInspAction-- SpotInspCompletion -- after getting the values " + spForm.getSpotViewList());
							request.setAttribute("spotVRList", spForm.getSpotViewList());

						} else {

							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of " + durationSQL + " days or less than that");
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी " + durationSQL + " दिन या उस से कम की रखें।");
							if(dateCheck.equalsIgnoreCase("N")){
								if (language.equalsIgnoreCase("en"))
									request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Duration has not selected properly");
								else
									request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी " + durationSQL + " दिन या उस से कम की रखें।");
							}
							FORWARD_JSP = "spotCompletion";

						}

					}

					if (spForm.getActionName().equals("assignSpot")) {

						objSpBD.insSpSpotAssignDet(spForm, roleId, funId, userId);
						logger.info("getSpotCriteriaList --  " + spForm.getSpotCriteriaList().size());
						request.setAttribute("spotAssignedList", spForm.getAssignedCriteriaList());
						FORWARD_JSP = "spotcriteriasucces";

					}

					// Added by Akansha - STQC 26, 27 point
					if (spForm.getActionName().equals("viewSRDetails")) {
						SpotInspDTO temp = null;
						SpotInspDTO tempNew = null;
						String regNo = request.getParameter("registrationNo");
						System.out.println("regNo" + regNo);
						if (regNo != null && !regNo.isEmpty()) {
							spForm.setCompApplNo(regNo);
							// String regTxnIds =
							// objSpBD.getRegCompletionNumber(regNo);

							propIdList = objSpBD.getPropIdListLR(regNo);
							temp = objSpBD.getObservationInfo(regNo);
							spotDto.setPropList(propIdList);
							spotDto.setRegFee(temp.getRegFee());
							spotDto.setNewRegfee(temp.getNewRegfee());
							spotDto.setNewStampDuty(temp.getNewStampDuty());
							spotDto.setStampDuty(temp.getStampDuty());
							spotDto.setJandpadDuty(temp.getJandpadDuty());
							spotDto.setMunicipalDuty(temp.getMunicipalDuty());
							spotDto.setUpkarDuty(temp.getUpkarDuty());
							spotDto.setTotalDuty(temp.getTotalDuty());
							spotDto.setNewJandpadDuty(temp.getNewJandpadDuty());
							spotDto.setNewTotalDuty(temp.getNewTotalDuty());
							spotDto.setNewUpkarDuty(temp.getNewUpkarDuty());
							spotDto.setNewMunicipalDuty(temp.getNewMunicipalDuty());
							spotDto.setRemark(temp.getRemark());
							spotDto.setNewRemark(temp.getNewRemark());

							// Inspection Date, Found Correct Details

							tempNew = objSpBD.getOtherSpotInsDtls(regNo);
							spotDto.setDateinsp(tempNew.getDateinsp());
							if (tempNew.getFoundStatus().equalsIgnoreCase("Y")) {
								spForm.setInspStatus("Y");
								spotDto.setFoundStatus("Yes");

								/*
								 * spotDto.setChangeInVal(tempNew.getChangeInVal(
								 * ));
								 * spotDto.setUploadPath(tempNew.getUploadPath
								 * ());
								 * spForm.setPhotCopyPath(spotDto.getUploadPath
								 * ());
								 * spForm.setPhotoCopyName(tempNew.getPhotoFilename
								 * ());
								 */
							} else {
								spForm.setInspStatus("N");
								spotDto.setFoundStatus("No");
								spotDto.setChangeInVal(tempNew.getChangeInVal());
								spotDto.setUploadPath(tempNew.getUploadPath());
								spForm.setPhotCopyPath(spotDto.getUploadPath());
								spForm.setPhotoCopyName(tempNew.getPhotoFilename());
							}
						}

						spForm.setObjSIDto(spotDto);
						spForm.setActionName("");
						FORWARD_JSP = "spotInspDetailView";
					}

					// added by SHreeraj
					String fwdPage = request.getParameter("fwdpage");
					if (fwdPage != null && "downloadDoc".equalsIgnoreCase(fwdPage)) {

						String docNo = request.getParameter("docNo");

						if (docNo != null) {

							PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
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
							connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
							connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
							connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
							connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
							connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
							metaDataInfo.setUniqueNo(objSpBD.getRegTxnID(docNo));
							metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
							metaDataInfo.setType("F");
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
							if (null==metaDataInfo.getUniqueNo()) {
								session.setAttribute("checkStatus", "DMSError");
								return new ActionForward("/jsp/login/UserRoleValidationPage.jsp");
							}
							if (metaDataInfo.getUniqueNo().equals("")) {
								session.setAttribute("checkStatus", "DMSError");
								return new ActionForward("/jsp/login/UserRoleValidationPage.jsp");
							}
							// int result =
							// docOperations.downloadDocument(connDetails,metaDataInfo,CommonConstant.ESTAMP_DOWNLOAD_PATH+estampFormBean.getObjEstampDet().getEcode(),CommonConstant.MODULE_NAME);
							int result = docOperations.downloadDocument(connDetails, metaDataInfo, EstampPath.toString(), CommonConstant.MODULE_NAME);
							File[] listOfFiles = folder.listFiles();
							for (int z = 0; z < listOfFiles.length; z++) {
								tempPath = listOfFiles[z].getPath();

							}
							logger.debug("download result---------->" + result);
							byte bytes[] = DMSUtility.getDocumentBytes(tempPath);
							if (bytes != null) {
								DMSUtility.downloadDocument(response, tempPath, bytes);
							} else {
								if (language.equalsIgnoreCase("en"))
									request.setAttribute(CommonConstant.FAILURE_MSG, "Document was not downloaded");
								else
									request.setAttribute(CommonConstant.FAILURE_MSG, "दस्तावेज डाउनलोड नहीं हो पाया है");
							}
						}
						if (spForm.getStatus() != null && spForm.getStatus().equalsIgnoreCase("R")) {
							FORWARD_JSP = "successpage1";
						} else {
							FORWARD_JSP = "successpage";
						}
					}
					if (fwdPage != null && "downloadFile".equalsIgnoreCase(fwdPage)) {
						String docPath = request.getParameter("docPath");
						String docName = request.getParameter("docName");
						DMSUtility.downloadDocument(response, docName, DMSUtility.getDocumentBytes(docPath + File.separator + docName));
					}
					if (fwdPage != null && "removeFile".equalsIgnoreCase(fwdPage)) {
						String docPath = request.getParameter("docPath");
						String docName = request.getParameter("docName");
						spForm.setPhotoCopyName("");
						request.setAttribute(CommonConstant.FAILURE_MSG, "");
					}

					/*
					 * if("viewProperty".equalsIgnoreCase(fwdpage)) {
					 * RegCommonBO commonBo = new RegCommonBO(); String
					 * propertyId=request.getParameter("propertyTxnID"); String
					 * regId=request.getParameter("registrationNo");
					 * if(propertyId.length()==15){ propertyId="0"+propertyId; }
					 * String reginitId=""; if(regId!=null){ CaveatsBO
					 * objCaveatBO = new CaveatsBO();
					 * reginitId=objCaveatBO.getReginitId(regId); }
					 * 
					 * String propval = new CaveatsDAO().getPropVal(regId);
					 * RegCommonBO bo = new RegCommonBO(); RegCommonForm regForm
					 * = new RegCommonForm(); //cDto.setAddressOfInsti(propval);
					 * if(propval.equalsIgnoreCase("Y")) {
					 * regForm.setMapPropertyTransPartyDisp
					 * (bo.getPropertyDetsForViewConfirm(reginitId,
					 * propertyId,language)); request.setAttribute("reginit",
					 * regForm); FORWARD_JSP = "propertyView"; } else {
					 * regForm.setMapPropertyTransPartyDisp
					 * (bo.getPropertyDetsForViewConfirmNonProp(reginitId,
					 * propertyId, language)); request.setAttribute("reginit",
					 * regForm); FORWARD_JSP = "propertyView1";
					 * 
					 * 
					 * 
					 * } }
					 */

				}

				/*
				 * if(spForm.getRegTxnId() != null) propIdList =
				 * objSpBD.getPropIdList(spForm.getRegTxnId());
				 * spotDto.setPropList(propIdList); spForm.setObjSIDto(spotDto)
				 * ;
				 * logger.info("SpotInspAction ..........FORWARD_JSP=="+FORWARD_JSP
				 * );
				 * logger.info("SpotInspAction ..........pageTitlel=="+spForm.
				 * getPageTitle()); request.setAttribute("spotVRList",
				 * spForm.getSpotViewList());
				 * request.setAttribute("visitForm",spForm);
				 */
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.info("FORWARD_JSP --  " + FORWARD_JSP);
		return mapping.findForward(FORWARD_JSP);
	}

	private void setValueNew(SpotInspForm _refForm, HttpServletRequest request) {
		String prefix = "spotprop1[";
		String suffix = "].";
		StringBuilder paramBldr = new StringBuilder();
		String value = "";
		String s[] = null;
		SpotInspDTO dto = new SpotInspDTO();
		for (int i = 0; i < _refForm.getObjSIDto().getPropList().size(); i++) {
			logger.info("criteriaListSize/srListSize3 --  " + _refForm.getObjSIDto().getPropList().size());
			dto = (SpotInspDTO) _refForm.getObjSIDto().getPropList().get(i);
			paramBldr.delete(0, paramBldr.length());
			paramBldr.append(prefix);
			paramBldr.append(i);
			paramBldr.append(suffix);
			paramBldr.append("postInspectionComment");
			s = request.getParameterValues("postInspectionComment");
			if (s != null)
				dto.setPostInspectionComment(s[i]);
		}

		System.out.println(dto + "to check dto");
	}

	private void setReValueNew(SpotInspForm _refForm, HttpServletRequest request) {
		String prefix = "spotprop1[";
		String suffix = "].";
		StringBuilder paramBldr = new StringBuilder();
		String value = "";
		String s[] = null;
		for (int i = 0; i < _refForm.getObjSIDto().getPropList().size(); i++) {
			logger.info("criteriaListSize/srListSize3 --  " + _refForm.getObjSIDto().getPropList().size());
			SpotInspDTO dto = (SpotInspDTO) _refForm.getObjSIDto().getPropList().get(i);
			paramBldr.delete(0, paramBldr.length());
			paramBldr.append(prefix);
			paramBldr.append(i);
			paramBldr.append(suffix);
			paramBldr.append("reInspectionComments");
			s = request.getParameterValues("reInspectionComments");
			if (s != null)
				dto.setReInspectionComments(s[i]);
		}

	}

	private void setValue(SpotInspForm _refForm, HttpServletRequest request) {
		String prefix = "spotprop1[";
		String suffix = "].";

		String prefix1 = "spotprop2[";
		String suffix1 = "].";

		String newArea = "newArea";

		String newConstructedArea = "newConstructedArea";
		String newTypeOfConstruction = "newTypeOfConstruction";
		String newPropertyUse = "newPropertyUse";
		String newMarketValue = "newMarketValue";
		String newGuideLineValue = "newguidelineValue";

		StringBuilder paramBldr = new StringBuilder();
		String value = "";
		String s[] = null;
		StringBuilder paramBldrs = new StringBuilder();
		String values = "";
		for (int i = 0; i < _refForm.getObjSIDto().getPropList().size(); i++) {
			logger.info("criteriaListSize/srListSize3 --  " + _refForm.getObjSIDto().getPropList().size());
			SpotInspDTO dto = (SpotInspDTO) _refForm.getObjSIDto().getPropList().get(i);

			ArrayList<SpotInspDTO> list = dto.getFloorList();
			if (dto.getPropertyId().equalsIgnoreCase("2")) {
				for (int j = 0; j < list.size(); j++) {
					SpotInspDTO dto1 = list.get(j);
					paramBldrs.delete(0, paramBldrs.length());
					paramBldrs.append(prefix1);
					paramBldrs.append(j);
					paramBldrs.append(suffix1);
					paramBldrs.append(newArea);

					s = request.getParameterValues(paramBldrs.toString());
					values = s[i];
					if (values != null && ("".equals(values)) == false) {
						dto1.setNewArea(values);
					}

					paramBldrs.delete(0, paramBldrs.length());
					paramBldrs.append(prefix1);
					paramBldrs.append(j);
					paramBldrs.append(suffix1);
					paramBldrs.append(newConstructedArea);
					s = request.getParameterValues(paramBldrs.toString());
					values = s[i];
					if (values != null && ("".equals(values)) == false) {
						dto1.setNewConstructedArea(values);
					}

					paramBldrs.delete(0, paramBldrs.length());
					paramBldrs.append(prefix1);
					paramBldrs.append(j);
					paramBldrs.append(suffix1);
					paramBldrs.append(newTypeOfConstruction);
					s = request.getParameterValues(paramBldrs.toString());
					values = s[i];
					if (values != null && ("".equals(values)) == false) {
						dto1.setNewTypeOfConstruction(values);
					}

					paramBldrs.delete(0, paramBldrs.length());
					paramBldrs.append(prefix1);
					paramBldrs.append(j);
					paramBldrs.append(suffix1);
					paramBldrs.append(newPropertyUse);
					s = request.getParameterValues(paramBldrs.toString());
					values = s[i];
					if (values != null && ("".equals(values)) == false) {
						dto1.setNewPropertyUse(values);
					}
				}
			} else {
				if (list != null && list.size() > 0) {
					SpotInspDTO dto1 = list.get(0);
					paramBldrs.delete(0, paramBldrs.length());
					paramBldrs.append(prefix1);
					paramBldrs.append(0);
					paramBldrs.append(suffix1);
					paramBldrs.append(newTypeOfConstruction);
					s = request.getParameterValues(paramBldrs.toString());
					values = s[i];
					if (values != null && ("".equals(values)) == false) {
						dto1.setNewTypeOfConstruction(values);
					}

					paramBldrs.delete(0, paramBldrs.length());
					paramBldrs.append(prefix1);
					paramBldrs.append(0);
					paramBldrs.append(suffix1);
					paramBldrs.append(newPropertyUse);
					s = request.getParameterValues(paramBldrs.toString());
					values = s[i];
					if (values != null && ("".equals(values)) == false) {
						dto1.setNewPropertyUse(values);
					}

					paramBldrs.delete(0, paramBldrs.length());
					paramBldrs.append(prefix1);
					paramBldrs.append(0);
					paramBldrs.append(suffix1);
					paramBldrs.append(newArea);
					s = request.getParameterValues(paramBldrs.toString());
					values = s[i];
					if (values != null && ("".equals(values)) == false) {
						dto1.setNewArea(values);
					}

				}
			}

			paramBldr.delete(0, paramBldr.length());
			paramBldr.append(prefix);
			paramBldr.append(i);
			paramBldr.append(suffix);
			paramBldr.append(newMarketValue);
			value = request.getParameter(paramBldr.toString());

			if (value != null && ("".equals(value)) == false) {
				dto.setNewMarketValue(value);
			}

			paramBldr.delete(0, paramBldr.length());
			paramBldr.append(prefix);
			paramBldr.append(i);
			paramBldr.append(suffix);
			paramBldr.append(newGuideLineValue);
			value = request.getParameter(paramBldr.toString());
			if (value != null && ("".equals(value)) == false) {
				dto.setNewguidelineValue(value);
			}

		}
	}

	private void setReValue(SpotInspForm _refForm, HttpServletRequest request) {
		String prefix = "spotprop1[";
		String suffix = "].";

		String prefix1 = "spotprop2[";
		String suffix1 = "].";

		String newArea = "crossArea";

		String newConstructedArea = "crossConstructedArea";
		String newTypeOfConstruction = "crossTypeOfConstruction";
		String newPropertyUse = "crossPropertyUse";
		String newMarketValue = "crossMarketValue";
		String newGuideLineValue = "crossGuideLineValue";

		StringBuilder paramBldr = new StringBuilder();
		String value = "";
		String s[] = null;
		StringBuilder paramBldrs = new StringBuilder();
		String values = "";
		for (int i = 0; i < _refForm.getObjSIDto().getPropList().size(); i++) {
			logger.info("criteriaListSize/srListSize3 --  " + _refForm.getObjSIDto().getPropList().size());
			SpotInspDTO dto = (SpotInspDTO) _refForm.getObjSIDto().getPropList().get(i);

			ArrayList<SpotInspDTO> list = dto.getFloorList();
			if (dto.getPropertyId().equalsIgnoreCase("2")) {
				for (int j = 0; j < list.size(); j++) {
					SpotInspDTO dto1 = list.get(j);
					paramBldrs.delete(0, paramBldrs.length());
					paramBldrs.append(prefix1);
					paramBldrs.append(j);
					paramBldrs.append(suffix1);
					paramBldrs.append(newArea);

					s = request.getParameterValues(paramBldrs.toString());
					values = s[i];
					if (values != null && ("".equals(values)) == false) {
						dto1.setCrossArea(values);
					}

					paramBldrs.delete(0, paramBldrs.length());
					paramBldrs.append(prefix1);
					paramBldrs.append(j);
					paramBldrs.append(suffix1);
					paramBldrs.append(newConstructedArea);
					s = request.getParameterValues(paramBldrs.toString());
					values = s[i];
					if (values != null && ("".equals(values)) == false) {
						dto1.setCrossConstructedArea(values);
					}

					paramBldrs.delete(0, paramBldrs.length());
					paramBldrs.append(prefix1);
					paramBldrs.append(j);
					paramBldrs.append(suffix1);
					paramBldrs.append(newTypeOfConstruction);
					s = request.getParameterValues(paramBldrs.toString());
					values = s[i];
					if (values != null && ("".equals(values)) == false) {
						dto1.setCrossTypeOfConstruction(values);
					}

					paramBldrs.delete(0, paramBldrs.length());
					paramBldrs.append(prefix1);
					paramBldrs.append(j);
					paramBldrs.append(suffix1);
					paramBldrs.append(newPropertyUse);
					s = request.getParameterValues(paramBldrs.toString());
					values = s[i];
					if (values != null && ("".equals(values)) == false) {
						dto1.setCrossPropertyUse(values);
					}
				}
			} else {
				if (list != null && list.size() > 0) {
					SpotInspDTO dto1 = list.get(0);
					paramBldrs.delete(0, paramBldrs.length());
					paramBldrs.append(prefix1);
					paramBldrs.append(0);
					paramBldrs.append(suffix1);
					paramBldrs.append(newTypeOfConstruction);
					s = request.getParameterValues(paramBldrs.toString());
					values = s[i];
					if (values != null && ("".equals(values)) == false) {
						dto1.setCrossTypeOfConstruction(values);
					}

					paramBldrs.delete(0, paramBldrs.length());
					paramBldrs.append(prefix1);
					paramBldrs.append(0);
					paramBldrs.append(suffix1);
					paramBldrs.append(newPropertyUse);
					s = request.getParameterValues(paramBldrs.toString());
					values = s[i];
					if (values != null && ("".equals(values)) == false) {
						dto1.setCrossPropertyUse(values);
					}

					paramBldrs.delete(0, paramBldrs.length());
					paramBldrs.append(prefix1);
					paramBldrs.append(0);
					paramBldrs.append(suffix1);
					paramBldrs.append(newArea);
					s = request.getParameterValues(paramBldrs.toString());
					values = s[i];
					if (values != null && ("".equals(values)) == false) {
						dto1.setCrossArea(values);
					}

				}
			}

			paramBldr.delete(0, paramBldr.length());
			paramBldr.append(prefix);
			paramBldr.append(i);
			paramBldr.append(suffix);
			paramBldr.append(newMarketValue);
			value = request.getParameter(paramBldr.toString());

			if (value != null && ("".equals(value)) == false) {
				dto.setCrossMarketValue(value);
			}

			paramBldr.delete(0, paramBldr.length());
			paramBldr.append(prefix);
			paramBldr.append(i);
			paramBldr.append(suffix);
			paramBldr.append(newGuideLineValue);
			value = request.getParameter(paramBldr.toString());
			if (value != null && ("".equals(value)) == false) {
				dto.setCrossGuideLineValue(value);
			}

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
