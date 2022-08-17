package com.wipro.igrs.guideline.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.guideline.bd.GuidelinePreparationBD;
import com.wipro.igrs.guideline.constant.CommonConstant;
import com.wipro.igrs.guideline.dao.GuidelineApprovalDAO;
import com.wipro.igrs.guideline.dto.GuidelineDTO;
import com.wipro.igrs.guideline.form.GuidelinePreparationForm;
import com.wipro.igrs.guideline.rule.GuideLineRule;

public class GuidelineDeriveAction extends BaseAction {

	String forwardJsp = new String(CommonConstant.MAKER_DERIVE);
	private Logger logger = Logger.getLogger(GuidelineDeriveAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		String page = request.getParameter("page");
		String districtName;
		long guidelineID;
		boolean flag = false;
		String propDetails = null;
		String actionName = null;

		// HttpSession session = request.getSession();
		logger.debug("Inside Action.");
		ActionMessages messages = new ActionMessages();
		String roleId = (String) session.getAttribute("role");
		String funId = (String) session.getAttribute("functionId");
		String userId = (String) session.getAttribute("UserId");
		String officeId = (String) session.getAttribute("loggedToOffice");
		String language = (String) session.getAttribute("languageLocale");
		ArrayList officeList = new ArrayList();
		/*
		 * if(session.getAttribute("officeIdList")!= null) {
		 * logger.debug("<------notnull"); officeList =
		 * (ArrayList)session.getAttribute("officeIdList"); }
		 */
		String actId = (String) request.getParameter("actId");
		HashMap officeDetails = new HashMap();
		String roleID = "";

		// if(session.getAttribute("officeIdList")!= null)
		// {
		// logger.debug("<------notnull");
		// officeList = (ArrayList)session.getAttribute("officeIdList");
		// }

		if (session.getAttribute("officeactivitydata") != null) {
			officeDetails = (HashMap) session.getAttribute("officeactivitydata");

		}

		if (form != null) {
			GuidelinePreparationForm eForm = (GuidelinePreparationForm) form;
			GuidelinePreparationBD bd = new GuidelinePreparationBD();

			ArrayList financialYearList = bd.getFinancialYearList();
			eForm.setFinancialYearList(financialYearList);

			GuidelineDTO formDTO = eForm.getGuideDTO();
			formDTO.setLanguage(language);
			formDTO.setListType("");
			if (page != null) {
				if ("derive".equals(page)) {
					// ArrayList districtList =
					// bd.getDistrictList(officeDetails,actId);
					ArrayList districtList = bd.getDistrict(officeId, formDTO);
					eForm.setDistrictList(districtList);

					eForm.setTehsilList(new ArrayList());
					eForm.setWardList(new ArrayList());
					eForm.setMohallaList(new ArrayList());
					eForm.setPatwariList(new ArrayList());
					eForm.setVillageList(new ArrayList());
					eForm.setVersionList(new ArrayList());

					// eForm.setFinancialYearList(new ArrayList());
					formDTO.setListType("");
					formDTO.setFinancialYear("");
					formDTO.setFromDepositeDate("");
					formDTO.setToDepositeDate("");

					formDTO.setBasePeriodFrom("");
					formDTO.setBasePeriodTo("");
					formDTO.setAreaTypeID("");
					formDTO.setWardPatwari("");
					formDTO.setMohalla("");
					formDTO.setWard("");
					formDTO.setPatwari("");
					formDTO.setActionName("");
					eForm.setGuideDTO(new GuidelineDTO());
					session.removeAttribute(CommonConstant.MOHALLA_LIST);
					session.removeAttribute(CommonConstant.PATWARI_LIST);
					session.removeAttribute(CommonConstant.DTO);
					// ************for log*****************//
					IGRSCommon save = null;

					try {

						save = new IGRSCommon();

						save.saveactivitylog(userId, actId);

					} catch (Exception e) {

						e.printStackTrace();

					}

					// ****************************//
					forwardJsp = CommonConstant.MAKER_DERIVE;
					logger.debug("Page is forwarded to :-    " + forwardJsp);
				}
			}

			if (request.getParameter("actionName") != null) {
				actionName = request.getParameter("actionName");
				formDTO.setActionName("");
			} else {
				actionName = formDTO.getActionName();
			}
			// logger.debug(eForm.getGuideDTO().getGuidelinePreparationForm());

			if (CommonConstant.GUIDELINE_DERIVE.equalsIgnoreCase(eForm.getGuideDTO().getGuidelinePreparationForm())) {
				if ("deriveGuideline".equalsIgnoreCase(actionName)) {
					String distId = formDTO.getDistrictID();
					boolean flag1 = bd.insertTemplateData(distId, formDTO);

					if (flag1) {
						logger.debug("template data updated successfully");
					}

					else {
						logger.debug("template data update failed");
					}

					GuideLineRule guideRule = new GuideLineRule();
					// ArrayList tehsilList = new ArrayList();

					// System.out.println(formDTO.getFinancialYear());
					String fYear = formDTO.getFinancialYear();
					String Financial[] = formDTO.getFinancialYear().split("-");
					String finanDistt = Financial[0].concat(Financial[1]).concat(distId);

					String fromDate = formDTO.getFromDepositeDate();
					String toDate = formDTO.getToDepositeDate();

					// String[] fYear1 = new String[] { fYear};

					ArrayList start_end = bd.getStartEndDate(fYear);
					logger.debug("<-----After Calling---------->");
					String start = null;
					String end = null;
					if (start_end != null && start_end.size() == 2) {
						start = (String) start_end.get(0);
						// logger.debug("<----START DATE--->"+start);

						end = (String) start_end.get(1);
						// logger.debug("<-----"+start);

					}
					eForm = guideRule.checkDurationDates(fromDate, toDate, fYear, start, end, eForm);

					if (guideRule.isError()) {
						logger.debug("is error");
						eForm.setGuideDTO(formDTO);

						request.setAttribute(CommonConstant.DURATION_DATE_INVALID, "true");
						// request.setAttribute(CommonConstant.DURATION_DATES_ERRORS,
						// errorList);
						logger.debug("Action: Inside Error");
					}

					else {

						guidelineID = bd.getGuidelineID(finanDistt, distId);
						if (guidelineID == 0) {
							guidelineID = Long.parseLong(finanDistt.concat("100"));
						} else {
							guidelineID = guidelineID + 1;
						}
						long versionNo = guidelineID;
						// logger.debug("guideID"+guidelineID);
						boolean valueInserted = bd.insertAllCombinations(String.valueOf(guidelineID), distId);
						// String guideID = Long.toString(guidelineID);
						// String ver = Long.toString(versionNo);
						// TODO delete previous and insert new combinations
						formDTO.setGuideID(guidelineID);
						formDTO.setDistrictID(distId);
						formDTO.setFromDepositeDate(fromDate);
						formDTO.setToDepositeDate(toDate);
						formDTO.setFinancialYear(fYear);
						boolean dataCopied = bd.insertParentData(formDTO, userId);

						logger.debug("IN ACTION LAST" + dataCopied);
						// request.setAttribute("guideID", guideID);
						// request.setAttribute("ver", ver);

						// tehsilList = bd.getTehsilListMaker(distId,
						// guidelineID);
						// eForm.setTehsilList(tehsilList);

						// ArrayList areaTypes = bd.getAreasTypeList();
						// eForm.setAreaTypeList(areaTypes);
						if (dataCopied) {
							boolean valueDeleted = bd.deleteDeacCombinations(String.valueOf(guidelineID), distId);
							boolean newDataInserted = bd.insertNewCombinations(String.valueOf(guidelineID), distId);

							if (valueDeleted) {
								logger.debug("deactivated combinations deleted successfully...");
							}
							if (newDataInserted) {
								logger.debug("new combinations inserted successfully...");
							}

							formDTO.setGuideLineID(String.valueOf(guidelineID));
							messages.add("SUCCESS_MSG", new ActionMessage("conf_msg_guideline_derive_final"));
							saveMessages(request, messages);
							formDTO.setConfirmationChk("3");
							formDTO.setDownlaodChkId("FinalDerived");
							request.setAttribute(CommonConstant.SUCCESS_MSG, "Guideline successfully derived from previous Year's Guideline");
							logger.debug("data copied successfully...");
							forwardJsp = CommonConstant.PUBLISH_MAIN;
						} else {
							messages.add("FAILURE_MSG", new ActionMessage("conf_msg_guideline_derive_failed"));
							saveMessages(request, messages);
							formDTO.setConfirmationChk("3");
							request.setAttribute(CommonConstant.FAILURE_MSG, "Guideline derivation failed");
							logger.debug("data not copied");
							forwardJsp = CommonConstant.PUBLISH_MAIN;
						}
					}
				}

				if ("submitSRAllModification".equalsIgnoreCase(actionName)) {

					GuidelineApprovalDAO appDAO = new GuidelineApprovalDAO();

					ArrayList mohallaDetails = eForm.getMohallaAllDetails();

					int size = mohallaDetails != null ? mohallaDetails.size() : 0;
					ArrayList gda = new ArrayList();

					GuidelineDTO gDTO = eForm.getGuideDTO();
					String dFrom = null;
					String dTo = null;

					dFrom = formDTO.getFromDepositeDate();
					dTo = formDTO.getToDepositeDate();
					// logger.debug(dFrom);
					request.setAttribute("gDTO", gDTO);
					for (int i = 0; i < size; i++) {
						GuidelineDTO gd = (GuidelineDTO) mohallaDetails.get(i);
						gda.add(gd);
					}
					// ArrayList lst = bd.checkTempTableData();

					request.getAttribute("gDTO");
					eForm.setNewMohallas(new ArrayList());
					eForm.setNewMohallas(bd.getNewMohallaName(language, formDTO));
					boolean check = false;
					String id[] = formDTO.getMohallaID().split("~");
					String mohid = id[0];
					if (eForm.getNewMohallas() != null && eForm.getNewMohallas().size() >= 1) {
						for (int h = 0; h < eForm.getNewMohallas().size(); h++) {
							GuidelineDTO formDTO1 = (GuidelineDTO) eForm.getNewMohallas().get(h);

							if (formDTO1.getNewMohallaID().equalsIgnoreCase(mohid))

							{
								check = true;
							} else {
								check = false;
								break;
							}
						}

						if (check) {

							// logger.debug("!!!!!!!!!!!!!in save Action"+formDTO.getGuideLineID());
							// logger.debug("!!!!!!!!!!!!!in save Action"+formDTO.getTehsilID());
							// logger.debug("!!!!!!!!!!!!!in save Action"+formDTO.getWardID());
							// logger.debug("!!!!!!!!!!!!!in save Action"+formDTO.getPatwariID());
							// logger.debug("!!!!!!!!!!!!!in save Action"+formDTO.getMohallaID());

							if (formDTO.isNewMohalla()) {
								boolean insertMohallaDetails = bd.insertPendingGuidelineValues(gda, gDTO, dFrom, dTo, roleId, funId, userId, "D");
								if (insertMohallaDetails) { // boolean
															// insertSubClause =
															// bd.subClauseInsertionNewMohalla(propSub,
															// formDTO);
									// if(insertSubClause)
									// {
									messages.add("SUCCESS_MSG_1", new ActionMessage("conf_msg_guideline_derive_modify"));
									saveMessages(request, messages);
									formDTO.setConfirmationChk("4");
									request.setAttribute(CommonConstant.SUCCESS_MSG_1, "Mohalla/Village successfully modified");
									logger.debug("data copied successfully...");

									forwardJsp = CommonConstant.PUBLISH_MAIN;
									// }
									// else
									// {
									// messages.add("SUCCESS_MSG_1", new
									// ActionMessage(
									// "conf_msg_guideline_derive_modify_fail"));
									// saveMessages(request, messages);
									// formDTO.setConfirmationChk("5");
									// request.setAttribute(CommonConstant.SUCCESS_MSG_1,"Guideline modification failed");
									// logger.debug("Sub CLAUSE INSERTION FAILED");
									// forwardJsp = CommonConstant.PUBLISH_MAIN;
									// }
								} else {
									messages.add("SUCCESS_MSG_1", new ActionMessage("conf_msg_guideline_derive_modify_fail"));
									saveMessages(request, messages);
									formDTO.setConfirmationChk("5");
									request.setAttribute(CommonConstant.SUCCESS_MSG_1, "Mohalla/Village modification failed");
									logger.debug("data copied successfully...");
									forwardJsp = CommonConstant.PUBLISH_MAIN;
								}
							}

							// boolean insertMohallaDetails =
							// bd.insertIndividualMohallaDetails(gda, gDTO,
							// dFrom, dTo,roleId,funId,userId);
							else {
								boolean insertMohallaDetails = bd.updatePendingGuidelineValues(gda, formDTO, dFrom, dTo, roleId, funId, userId);

								if (insertMohallaDetails) {
									bd.getUpdateSubmitSRAll(formDTO);
									formDTO.setDownlaodChkId("X");
									forwardJsp = CommonConstant.PUBLISH_MAIN;
								} else {
									messages.add("SUCCESS_MSG_1", new ActionMessage("conf_msg_guideline_derive_modify_fail"));
									saveMessages(request, messages);
									formDTO.setDownlaodChkId("E1");
									formDTO.setConfirmationChk("5");
									request.setAttribute(CommonConstant.SUCCESS_MSG_1, "Guideline modification failed");
									logger.debug("data copied successfully...");
									forwardJsp = CommonConstant.PUBLISH_MAIN;
								}
							}

						}

						else {
							boolean insertMohallaDetails = bd.updatePendingGuidelineValues(gda, formDTO, dFrom, dTo, roleId, funId, userId);
							formDTO.setDownlaodChkId("updateleft");
							forwardJsp = CommonConstant.PUBLISH_MAIN;
						}

					}
					/*
					 * else if(eForm.getNewMohallas()!=null &&
					 * eForm.getNewMohallas().size()>1) { for (int h = 0; h <
					 * eForm.getNewMohallas().size(); h++) { GuidelineDTO
					 * formDTO1 = (GuidelineDTO) eForm.getNewMohallas().get(h);
					 * 
					 * 
					 * if(formDTO1.getNewMohallaID().equalsIgnoreCase(mohid))
					 * 
					 * { check=true; } } }
					 */
					else {
						boolean insertMohallaDetails = bd.updatePendingGuidelineValues(gda, formDTO, dFrom, dTo, roleId, funId, userId);
						bd.getUpdateSubmitSRAll(formDTO);
						formDTO.setDownlaodChkId("X");
						forwardJsp = CommonConstant.PUBLISH_MAIN;
					}

				}

				if ("resetPageAction".equalsIgnoreCase(actionName)) {

					formDTO.setFinancialYear("");
					formDTO.setDistrict("");
					eForm.setErrorMsg("");
					formDTO.setDistrictID("");
					formDTO.setFromDepositeDate("");
					formDTO.setToDepositeDate("");
					eForm.setVersionList(new ArrayList());
					forwardJsp = CommonConstant.MAKER_DERIVE;

				}

				if ("showVersion".equalsIgnoreCase(actionName)) {
					eForm.setErrorMsg("");
					String finan = formDTO.getFinancialYear();
					String district = formDTO.getDistrictID();
					int derivedFrom = 3;
					formDTO.setLoggedOfficeId(officeId);

					ArrayList versionList = bd.showVersionFinalMaker(finan, district, derivedFrom, language, formDTO);
					formDTO.setPreviousPage("final");
					if (versionList.size() > 0) {
						eForm.setVersionList(versionList);
						request.setAttribute("versionList", versionList);

					} else {
						formDTO.setCheck("Y");
						messages.add("MSG", new ActionMessage("no_data_found"));
						saveMessages(request, messages);
					}

					forwardJsp = CommonConstant.MAKER_DERIVE;

				}

				if ("tehsilAction".equalsIgnoreCase(actionName)) {
					logger.debug("Inside Tehsil");
					// logger.debug(formDTO.getTehsil());
					String tehID = formDTO.getTehsilID();
					// logger.debug(formDTO.getTehsilID());
					// System.out.println(formDTO.getGuideLineID());
					long guideID = Long.parseLong(formDTO.getGuideLineID());

					ArrayList wardList = bd.getWardListMaker(tehID, guideID, formDTO);
					ArrayList patwariList = bd.getPatwariListMaker(tehID, guideID, formDTO);
					eForm.setWardList(wardList);
					eForm.setPatwariList(patwariList);

					session.removeAttribute(CommonConstant.MOHALLA_LIST);
					session.removeAttribute(CommonConstant.PATWARI_LIST);
					forwardJsp = CommonConstant.MAKER_DERIVE_2;
				}

				if ("dropDownWardPatwariAction".equalsIgnoreCase(actionName)) {

					eForm.getGuideDTO().setWardPatwari(eForm.getGuideDTO().getWardPatwari());

					if (eForm.getGuideDTO().getWardPatwari().equalsIgnoreCase("P")) {
						session.removeAttribute(CommonConstant.MOHALLA_LIST);
						eForm.getGuideDTO().setWard("");
					}
					if (eForm.getGuideDTO().getWardPatwari().equalsIgnoreCase("W")) {
						session.removeAttribute(CommonConstant.PATWARI_LIST);
						eForm.getGuideDTO().setPatwari("");
					}
					forwardJsp = CommonConstant.MAKER_DERIVE_2;
				}

				if ("wardAction".equalsIgnoreCase(actionName)) {
					String[] wardAry = null;
					String type = formDTO.getWardPatwari();
					if (type != null && type.length() > 0) {
						if (type.equalsIgnoreCase("W")) {
							wardAry = formDTO.getWard().split("~");
						} else if (type.equalsIgnoreCase("P")) {
							wardAry = formDTO.getPatwari().split("~");
						}
					}
					String fromDate = formDTO.getFromDepositeDate();
					String toDate = formDTO.getToDepositeDate();

					String durationFrom = formDTO.getDurationFrom();

					formDTO.setGuideLineID(formDTO.getGuideLineID());
					session.setAttribute(CommonConstant.DTO, formDTO);

					long guideID = Long.parseLong(formDTO.getGuideLineID());

					if (wardAry.length == 2) {
						ArrayList mohallalist = bd.getMohListMaker(wardAry[0], guideID, formDTO);
						formDTO.setMohList(mohallalist);
						eForm.setGuideDTO(formDTO);
						session.setAttribute(CommonConstant.MOHALLA_LIST, eForm);
						session.removeAttribute("patwarilist");
					}
					forwardJsp = CommonConstant.MAKER_DERIVE_2;
				}

				if ("previousAction".equalsIgnoreCase(actionName)) {
					/*
					 * GuidelineDTO dtoForm =
					 * (GuidelineDTO)session.getAttribute(CommonConstant.DTO);
					 * GuidelineDTO dtoFrom = new GuidelineDTO(); String
					 * fromDate = dtoForm.getFromDepositeDate(); String toDate =
					 * dtoForm.getToDepositeDate(); String baseFrom =
					 * dtoForm.getBasePeriodFrom(); String baseTo =
					 * dtoForm.getBasePeriodTo(); String areaType =
					 * dtoForm.getAreaName();
					 * 
					 * dtoFrom.setDistrict(dtoForm.getDistrict());
					 * //dtoFrom.setTehsil(dtoForm.getTehsil());
					 * dtoFrom.setWard("");
					 * dtoFrom.setPatwari(dtoForm.getPatwari());
					 * dtoFrom.setWardPatwari("");
					 * dtoForm.setMohalla(dtoForm.getMohalla());
					 * dtoFrom.setFinancialYear(dtoForm.getFinancialYear());
					 * dtoFrom.setFromDepositeDate(fromDate);
					 * dtoFrom.setToDepositeDate(toDate);
					 * dtoFrom.setBasePeriodFrom(baseFrom);
					 * dtoFrom.setBasePeriodTo(baseTo);
					 * dtoFrom.setAreaName(areaType);
					 * dtoFrom.setGuideLineID(formDTO.getGuideLineID());
					 * dtoForm.setGuideID(dtoForm.getGuideID());
					 * dtoForm.setActionName("");
					 * 
					 * eForm.setGuideDTO(dtoFrom);
					 */
					forwardJsp = CommonConstant.MAKER_DERIVE_2;
				}

				// 26 -march

				if ("getSubAreaList".equalsIgnoreCase(formDTO.getActionName())) {

					String urbanFlag = "N";
					if (formDTO.getAreaTypeID().equals("2"))
						urbanFlag = "Y";

					eForm.setSubAreaList(bd.getSubArea(language, formDTO.getAreaTypeID(), formDTO.getTehsilID(), urbanFlag, formDTO.getGuideLineID()));

					eForm.setWardList(new ArrayList());
					formDTO.setWardID("");
					eForm.setMohallaList(new ArrayList());
					formDTO.setMohallaID("");

					forwardJsp = CommonConstant.MAKER_DERIVE_2;
				} else if ("getWardPatwariList".equalsIgnoreCase(formDTO.getActionName())) {

					eForm.setWardList(bd.getWardPatwari(language, formDTO.getSubAreaId(), formDTO.getTehsilID(), formDTO.getGuideLineID()));

					eForm.setMohallaList(new ArrayList());
					formDTO.setMohallaID("");
					forwardJsp = CommonConstant.MAKER_DERIVE_2;
				} else if ("getMohallaColony".equalsIgnoreCase(formDTO.getActionName())) {

					eForm.setMohallaList(bd.getColonyName(language, formDTO.getWardID(), formDTO.getGuideLineID()));

					// logger.debug("size of colony "+eForm.getMohallaList().size());
					forwardJsp = CommonConstant.MAKER_DERIVE_2;
				}

				if ("mohallaAction".equalsIgnoreCase(actionName)) {

					/*
					 * GuideLineRule guideRule = new GuideLineRule();
					 * GuidelineDTO dtoForm = eForm.getGuideDTO(); String fYear
					 * = dtoForm.getFinancialYear(); String fromDate =
					 * dtoForm.getFromDepositeDate(); String toDate =
					 * dtoForm.getToDepositeDate(); String baseFrom =
					 * dtoForm.getBasePeriodFrom(); String baseTo =
					 * dtoForm.getBasePeriodTo(); String tehsil =
					 * dtoForm.getTehsil(); String[] wardAry =
					 * dtoForm.getWard().split("~"); String districtAry =
					 * dtoForm.getDistrict(); String mohallaId =
					 * dtoForm.getMohallaID(); String areaType =
					 * dtoForm.getAreaTypeID() ; String[] patAry =
					 * dtoForm.getPatwari().split("~");
					 * 
					 * //logger.debug(dtoForm.getPatwari()); GuidelineDTO
					 * dtoFrom = new GuidelineDTO();
					 * dtoFrom.setDistrict(dtoForm.getDistrict());
					 * dtoFrom.setTehsil(dtoForm.getTehsil());
					 * dtoFrom.setWard(dtoForm.getWard());
					 * dtoFrom.setPatwari(dtoForm.getPatwari());
					 * dtoFrom.setWardPatwari(dtoForm.getWardPatwari());
					 * dtoFrom.setFinancialYear(dtoForm.getFinancialYear());
					 * dtoFrom.setFromDepositeDate(fromDate);
					 * dtoFrom.setToDepositeDate(toDate);
					 * dtoFrom.setBasePeriodFrom(baseFrom);
					 * dtoFrom.setBasePeriodTo(baseTo);
					 * dtoFrom.setAreaTypeID(areaType);
					 * dtoForm.setGuideLineID(dtoForm.getGuideLineID());
					 * dtoForm.setGuideID(dtoForm.getGuideID());
					 */
					// logger.debug(dtoForm.getGuideLineID());
					// logger.debug(dtoForm.getGuideID());
					// logger.debug(formDTO.getTehsilID());
					// logger.debug(formDTO.getWardID());
					// logger.debug(formDTO.getPatwariID());
					// logger.debug(formDTO.getMohallaID());

					/*
					 * if(wardAry!=null & wardAry.length==2) {
					 * dtoForm.setWardID(wardAry[0]);
					 * dtoForm.setWard(wardAry[1]); } //Added by simran
					 * if(patAry!=null & patAry.length==2) {
					 * dtoForm.setPatwariID(patAry[0]);
					 * dtoForm.setPatwari(patAry[1]); } // end
					 * 
					 * String[] areaArray = dtoForm.getAreaTypeID().split("~");
					 * 
					 * if(areaArray!=null && areaArray.length == 2) {
					 * dtoForm.setAreaTypeID(areaArray[0]);
					 * dtoForm.setAreaName(areaArray[1]); } String[] mohArray =
					 * mohallaId.split("="); if(mohArray!=null &&
					 * mohArray.length ==2) { dtoForm.setMohallaID(mohArray[0]);
					 * dtoForm.setMohalla(mohArray[1]); }
					 * 
					 * //Added by simran
					 * 
					 * 
					 * String[] fYear1 = new String[] { fYear};
					 * 
					 * ArrayList start_end = bd.getStartEndDate( fYear1[0]);
					 * logger.debug("<-----After Calling---------->"); String
					 * start= null; String end= null; if(start_end != null &&
					 * start_end.size()==2) { start =(String)start_end.get(0);
					 * // logger.debug("<----START DATE--->"+start);
					 * 
					 * end =(String)start_end.get(1);
					 * //logger.debug("<-----"+start);
					 * 
					 * }
					 */

					// ArrayList errorList =
					// guideRule.checkDurationDates(fromDate, toDate, fYear,
					// start, end, eForm);
					// if (guideRule.isError()) {
					// logger.debug("is error");
					// eForm.setGuideDTO(dtoFrom);
					// request.setAttribute(CommonConstant.DURATION_DATE_INVALID,
					// "true");
					// request.setAttribute(CommonConstant.DURATION_DATES_ERRORS,
					// errorList);
					// logger.debug("Action: Inside Error");
					// }
					// else{
					String plotval = "";

					ArrayList individualMohallaAttributes = bd.getIndividualMohallaDetailsNew(formDTO);
					if (individualMohallaAttributes.size() != 0) {
						for (int i = 0; i < individualMohallaAttributes.size(); i++) {
							GuidelineDTO dto = (GuidelineDTO) individualMohallaAttributes.get(i);
							if (i == 0) {
								GuidelineDTO dto1 = (GuidelineDTO) individualMohallaAttributes.get(0);

								plotval = dto1.getGuideLineValue();
							}
							String status = dto.getStatus();
							request.setAttribute("status", status);

						}
						formDTO.setListType("");
						String id = formDTO.getMohallaID();
						String colid[] = id.split("~");
						id = colid[0];
						formDTO.setMohallaChkId(id);
						String appliclause = bd.getMohallaAppliclause(formDTO);
						ArrayList constructRateList = bd.getconstructRateList(appliclause);
						ArrayList list1 = new ArrayList();
						if (constructRateList.size() != 0) {

							for (int i = 0; i < constructRateList.size(); i++) {
								list1 = (ArrayList) constructRateList.get(i);

								formDTO.setRccVal(Double.parseDouble((String) list1.get(0)));
								request.setAttribute("RCCCOST", String.valueOf(formDTO.getRccVal() + Double.parseDouble(plotval)));
								formDTO.setRbcVal(Double.parseDouble((String) list1.get(1)));
								request.setAttribute("RBCCOST", String.valueOf(formDTO.getRbcVal() + Double.parseDouble(plotval)));
								formDTO.setTinVal(Double.parseDouble((String) list1.get(2)));
								request.setAttribute("TINCOST", String.valueOf(formDTO.getTinVal() + Double.parseDouble(plotval)));
								formDTO.setKabeluVal(Double.parseDouble((String) list1.get(3)));
								request.setAttribute("KABELUCOST", String.valueOf(formDTO.getKabeluVal() + Double.parseDouble(plotval)));

							}

						} else {
							formDTO.setRccVal(0.0);
							request.setAttribute("RCCCOST", String.valueOf(formDTO.getRccVal() + Double.parseDouble(plotval)));
							formDTO.setRbcVal(0.0);
							request.setAttribute("RBCCOST", String.valueOf(formDTO.getRbcVal() + Double.parseDouble(plotval)));
							formDTO.setTinVal(0.0);
							request.setAttribute("TINCOST", String.valueOf(formDTO.getTinVal() + Double.parseDouble(plotval)));
							formDTO.setKabeluVal(0.0);
							request.setAttribute("KABELUCOST", String.valueOf(formDTO.getKabeluVal() + Double.parseDouble(plotval)));
						}
						eForm.setMohallaAllDetails(individualMohallaAttributes);
					} else {
						ArrayList individualMohallaAttributes2 = bd.getIndividualMohallaDetails2(formDTO);

						formDTO.setNewMohalla(true);
						formDTO.setListType("");
						String id = formDTO.getMohallaID();
						String colid[] = id.split("~");
						id = colid[0];
						formDTO.setMohallaChkId(id);
						String appliclause = bd.getMohallaAppliclause(formDTO);
						ArrayList constructRateList = bd.getconstructRateList(appliclause);
						ArrayList list1 = new ArrayList();
						if (constructRateList.size() != 0) {

							for (int i = 0; i < constructRateList.size(); i++) {
								list1 = (ArrayList) constructRateList.get(i);

								formDTO.setRccVal(Double.parseDouble((String) list1.get(0)));
								request.setAttribute("RCCCOST", String.valueOf(formDTO.getRccVal() + Double.parseDouble(plotval)));
								formDTO.setRbcVal(Double.parseDouble((String) list1.get(1)));
								request.setAttribute("RBCCOST", String.valueOf(formDTO.getRbcVal() + Double.parseDouble(plotval)));
								formDTO.setTinVal(Double.parseDouble((String) list1.get(2)));
								request.setAttribute("TINCOST", String.valueOf(formDTO.getTinVal() + Double.parseDouble(plotval)));
								formDTO.setKabeluVal(Double.parseDouble((String) list1.get(3)));
								request.setAttribute("KABELUCOST", String.valueOf(formDTO.getKabeluVal() + Double.parseDouble(plotval)));

							}

						} else {
							formDTO.setRccVal(0.0);
							request.setAttribute("RCCCOST", String.valueOf(formDTO.getRccVal() + Double.parseDouble(plotval)));
							formDTO.setRbcVal(0.0);
							request.setAttribute("RBCCOST", String.valueOf(formDTO.getRbcVal() + Double.parseDouble(plotval)));
							formDTO.setTinVal(0.0);
							request.setAttribute("TINCOST", String.valueOf(formDTO.getTinVal() + Double.parseDouble(plotval)));
							formDTO.setKabeluVal(0.0);
							request.setAttribute("KABELUCOST", String.valueOf(formDTO.getKabeluVal() + Double.parseDouble(plotval)));
						}

						eForm.setMohallaAllDetails(individualMohallaAttributes2);
					}

					forwardJsp = CommonConstant.MAKER_DERIVE_3;
					// }
				}

				if ("resetPageMaker2".equalsIgnoreCase(actionName)) {
					logger.debug("reset action");
					formDTO.setTehsilID("");
					formDTO.setTehsil("");
					formDTO.setAreaTypeID("");
					formDTO.setAreaName("");
					formDTO.setWardID("");
					formDTO.setWard("");
					formDTO.setPatwari("");
					formDTO.setMohalla("");
					formDTO.setMohallaID("");
					formDTO.setSubAreaId("");
					formDTO.setSubAreaName("");
					forwardJsp = CommonConstant.MAKER_DERIVE_2;
				}
				if ("previousPageMaker".equalsIgnoreCase(formDTO.getActionName())) {
					logger.debug("previous page");
					formDTO.setTehsilID("");
					formDTO.setTehsil("");
					formDTO.setAreaTypeID("");
					formDTO.setAreaName("");
					formDTO.setWardID("");
					formDTO.setWard("");
					formDTO.setPatwari("");
					formDTO.setMohalla("");
					formDTO.setMohallaID("");
					formDTO.setSubAreaId("");
					formDTO.setSubAreaName("");
					if (eForm.getVersionList().size() > 0) {
						request.setAttribute("versionList", eForm.getVersionList());
					}
					if (formDTO.getPreviousPage().equalsIgnoreCase("final")) {
						forwardJsp = CommonConstant.MAKER_DERIVE;
					} else {
						forwardJsp = CommonConstant.MAKER_DERIVE_DRAFT;
					}

				}
				if (CommonConstant.NEXT_PAGE_ACTION.equalsIgnoreCase(actionName)) {

					/*
					 * HashMap hMap2 = formDTO.getHMap(); int size = (hMap2 ==
					 * null?0:hMap2.size());
					 * //logger.debug("SIXE OF HASHMAP "+size);
					 * 
					 * 
					 * if(size == 0) { Enumeration parameterList =
					 * request.getParameterNames(); HashMap hMap = new
					 * HashMap(); ArrayList nullKeys = new ArrayList();
					 * logger.debug("GuidelineDeriveAction--ActionForward");
					 * while( parameterList.hasMoreElements() ) { String sName =
					 * parameterList.nextElement().toString();
					 * if(sName.startsWith("subID_")){ String sVal =
					 * (request.getParameter
					 * (sName)==null?"0":request.getParameter(sName)) ;
					 * hMap.put(sName, sVal); //logger.debug("Added--->" + sName
					 * +"="+sVal); } // String[] sMultiple =
					 * request.getParameterValues( sName );
					 * 
					 * //for( int i=0; i<sMultiple.length; i++ ) // if a
					 * paramater contains multiple values, print all of them
					 * //System.out.println(sName + "[" + i + "] = " +
					 * sMultiple[i] + "" ); //logger.debug(sName); // } }
					 * 
					 * Set mapSet = (Set)hMap.entrySet();
					 * 
					 * Iterator mapIterator = mapSet.iterator();
					 * 
					 * while(mapIterator.hasNext()) { Map.Entry mapEntry =
					 * (Map.Entry)mapIterator.next(); String value =
					 * (String)mapEntry.getValue();
					 * //logger.debug((String)mapEntry.getKey()+" val "+value);
					 * value = value == null?"":value.trim();
					 * if("".equals(value)) { String keyValue =
					 * (String)mapEntry.getKey(); nullKeys.add(keyValue); } }
					 * logger.debug(nullKeys.size());
					 */
					// logger.debug("GUIDELINE"+formDTO.getGuideLineID());
					// logger.debug("TEHSIL"+formDTO.getTehsilID());
					// logger.debug("WARD"+formDTO.getWardID());
					// logger.debug("mohalla"+formDTO.getMohallaID());
					/*
					 * 
					 * getting SubClause Ids for Keys having null values in
					 * HashMap1
					 */
					/*
					 * HashMap hMap1 = new HashMap(); if(formDTO.isNewMohalla())
					 * { hMap1 = bd.subClauseNotClickedNewMohalla(nullKeys,
					 * formDTO); } else { hMap1 =
					 * bd.subClauseNotClicked(nullKeys,formDTO); }
					 */

					// logger.debug("size of map before complete insertion of values"+hMap1.size());
					/*
					 * setting values in Previous HashMap i.e hMap
					 */
					/*
					 * Set mapSet1 = (Set)hMap1.entrySet();
					 * 
					 * Iterator mapIterator1 = mapSet1.iterator();
					 * 
					 * while(mapIterator1.hasNext()) { Map.Entry mapEntry1 =
					 * (Map.Entry)mapIterator1.next(); String key =
					 * (String)mapEntry1.getKey();
					 * //logger.debug("&&&&&&&&key"+key); String value =
					 * (String)mapEntry1.getValue();
					 * //logger.debug("&&&&&&&&key"+value); hMap.put(key,value);
					 * 
					 * }//logger.debug(
					 * "size of map after complete insertion of values"
					 * +hMap.size()); formDTO.setHMap(hMap); }
					 * 
					 * else {
					 * 
					 * Enumeration parameterList = request.getParameterNames();
					 * 
					 * ArrayList nullKeys = new ArrayList();
					 * logger.debug("GuidelineDeriveAction--ActionForward");
					 * while( parameterList.hasMoreElements() ) { String sName =
					 * parameterList.nextElement().toString();
					 * if(sName.startsWith("subID_")){ String sVal =
					 * request.getParameter(sName) ;
					 * //logger.debug("Value"+sVal);
					 * if("".equalsIgnoreCase(sVal)!= true) {
					 * logger.debug("Inside IF --val not null");
					 * hMap2.put(sName, sVal); logger.debug("Added--->" + sName
					 * +"="+sVal); } } // String[] sMultiple =
					 * request.getParameterValues( sName );
					 * 
					 * //for( int i=0; i<sMultiple.length; i++ ) // if a
					 * paramater contains multiple values, print all of them
					 * //System.out.println(sName + "[" + i + "] = " +
					 * sMultiple[i] + "" ); // logger.debug(sName); // } } Set
					 * mapSet = (Set)hMap2.entrySet();
					 * 
					 * Iterator mapIterator = mapSet.iterator();
					 * 
					 * while(mapIterator.hasNext()) { Map.Entry mapEntry =
					 * (Map.Entry)mapIterator.next(); String value =
					 * (String)mapEntry.getValue();
					 * logger.debug((String)mapEntry.getKey()+" val "+value);
					 * value = value == null?"":value.trim();
					 * 
					 * if("".equals(value)) { String keyValue =
					 * (String)mapEntry.getKey(); nullKeys.add(keyValue); } }
					 * logger.debug(nullKeys.size());
					 */
					// /logger.debug("GUIDELINE"+formDTO.getGuideLineID());
					// logger.debug("TEHSIL"+formDTO.getTehsilID());
					// logger.debug("WARD"+formDTO.getWardID());
					// logger.debug("mohalla"+formDTO.getMohallaID());
					/*
					 * 
					 * getting SubClause Ids for Keys having null values in
					 * HashMap1
					 * 
					 * HashMap hMap1 = new HashMap(); if(formDTO.isNewMohalla())
					 * { hMap1 = bd.subClauseNotClickedNewMohalla(nullKeys,
					 * formDTO); } else { hMap1 =
					 * bd.subClauseNotClicked(nullKeys,formDTO); }
					 */

					// logger.debug("size of map before complete insertion of values"+hMap1.size());
					/*
					 * setting values in Previous HashMap i.e hMap
					 */
					/*
					 * Set mapSet1 = (Set)hMap1.entrySet();
					 * 
					 * Iterator mapIterator1 = mapSet1.iterator();
					 * 
					 * while(mapIterator1.hasNext()) { Map.Entry mapEntry1 =
					 * (Map.Entry)mapIterator1.next(); String key =
					 * (String)mapEntry1.getKey();
					 * //logger.debug("&&&&&&&&key"+key); String value =
					 * (String)mapEntry1.getValue();
					 * //logger.debug("&&&&&&&&value"+value);
					 * hMap2.put(key,value);
					 * 
					 * }//logger.debug(
					 * "size of map after complete insertion of values"
					 * +hMap2.size()); formDTO.setHMap(hMap2);
					 * 
					 * }
					 */
					logger.debug("GOING TO READ ONLY PAGE");
					forwardJsp = CommonConstant.MAKER_DERIVE_4;
				}

				if (CommonConstant.MODIFY_MOHALLA_DETAILS.equalsIgnoreCase(actionName)) {
					formDTO.setListType("");
					String id = formDTO.getMohallaID();
					String colid[] = id.split("~");
					id = colid[0];
					formDTO.setMohallaChkId(id);
					String appliclause = bd.getMohallaAppliclause(formDTO);
					ArrayList constructRateList = bd.getconstructRateList(appliclause);
					ArrayList list1 = new ArrayList();
					if (constructRateList.size() != 0) {

						for (int i = 0; i < constructRateList.size(); i++) {
							list1 = (ArrayList) constructRateList.get(i);

							formDTO.setRccVal(Double.parseDouble((String) list1.get(0)));
							request.setAttribute("RCCCOST", String.valueOf(formDTO.getRccVal()));
							formDTO.setRbcVal(Double.parseDouble((String) list1.get(1)));
							request.setAttribute("RBCCOST", String.valueOf(formDTO.getRbcVal()));
							formDTO.setTinVal(Double.parseDouble((String) list1.get(2)));
							request.setAttribute("TINCOST", String.valueOf(formDTO.getTinVal()));
							formDTO.setKabeluVal(Double.parseDouble((String) list1.get(3)));
							request.setAttribute("KABELUCOST", String.valueOf(formDTO.getKabeluVal()));

						}

					} else {
						formDTO.setRccVal(0.0);
						request.setAttribute("RCCCOST", String.valueOf(formDTO.getRccVal()));
						formDTO.setRbcVal(0.0);
						request.setAttribute("RBCCOST", String.valueOf(formDTO.getRbcVal()));
						formDTO.setTinVal(0.0);
						request.setAttribute("TINCOST", String.valueOf(formDTO.getTinVal()));
						formDTO.setKabeluVal(0.0);
						request.setAttribute("KABELUCOST", String.valueOf(formDTO.getKabeluVal()));
					}
					formDTO.setModify(true);
					formDTO.setListType("Y");
					forwardJsp = CommonConstant.MAKER_DERIVE_3;
					// logger.debug("Forwarded to :   "+forwardJsp);
				}

				if ("mohallaSaveAction".equalsIgnoreCase(actionName)) {

					GuidelineApprovalDAO appDAO = new GuidelineApprovalDAO();
					/*
					 * HashMap hMap = new HashMap(); hMap = formDTO.getHMap();
					 * Set mapSet1 = (Set)hMap.entrySet();
					 * 
					 * Iterator mapIterator1 = mapSet1.iterator();
					 * 
					 * while(mapIterator1.hasNext()) { Map.Entry mapEntry1 =
					 * (Map.Entry)mapIterator1.next(); String key =
					 * (String)mapEntry1.getKey(); String value =
					 * (String)mapEntry1.getValue();
					 * //logger.debug("FINAL VALUES IN HASHMAP^^^^^^^^^^^^"
					 * +key+" val "+value); }
					 * 
					 * //logger.debug(hMap.size()); ArrayList propSub = new
					 * ArrayList(); Set mapSet2 = (Set)hMap.entrySet();
					 * 
					 * Iterator mapIterator2 = mapSet2.iterator();
					 * 
					 * while(mapIterator2.hasNext()) { GuidelineDTO gdObj = new
					 * GuidelineDTO(); Map.Entry mapEntry1 =
					 * (Map.Entry)mapIterator2.next();
					 * 
					 * String keys[] = ((String)mapEntry1.getKey()).split("_");
					 * logger.debug("sizeof array---->"+keys.length);
					 * gdObj.setPropId(keys[1]); gdObj.setPropId1(keys[2]);
					 * gdObj.setPropId2(keys[3]); logger.debug("before value");
					 * String val = (String)mapEntry1.getValue();
					 * //logger.debug("val"+val);
					 * 
					 * if(val !=null) {
					 * if(!((String)mapEntry1.getValue()).equals("")){
					 * logger.debug("after value"); String values[] =
					 * ((String)mapEntry1.getValue()).split("_");
					 * gdObj.setSubClauseIds(values); propSub.add(gdObj); }
					 * 
					 * } else {
					 * 
					 * propSub.add(gdObj); } } logger.debug(propSub.size());
					 */

					ArrayList mohallaDetails = eForm.getMohallaAllDetails();

					int size = mohallaDetails != null ? mohallaDetails.size() : 0;
					ArrayList gda = new ArrayList();

					GuidelineDTO gDTO = eForm.getGuideDTO();
					String dFrom = null;
					String dTo = null;

					/*
					 * String []dFromArr =
					 * formDTO.getFromDepositeDate().toString().split("-");
					 * if(dFromArr.length ==3) { dFrom =
					 * dFromArr[2]+"/"+dFromArr[1]+"/"+dFromArr[0]; }
					 * 
					 * String [] dToArr =
					 * formDTO.getToDepositeDate().toString().split("-");
					 * if(dToArr.length ==3) { dTo =
					 * dToArr[2]+"/"+dToArr[1]+"/"+dToArr[0]; }
					 */
					dFrom = formDTO.getFromDepositeDate();
					dTo = formDTO.getToDepositeDate();
					// logger.debug(dFrom);
					request.setAttribute("gDTO", gDTO);
					for (int i = 0; i < size; i++) {
						GuidelineDTO gd = (GuidelineDTO) mohallaDetails.get(i);
						gda.add(gd);
					}
					// ArrayList lst = bd.checkTempTableData();

					request.getAttribute("gDTO");

					// TODO check for already update
					// logger.debug("!!!!!!!!!!!!!in save Action"+formDTO.getGuideLineID());
					// logger.debug("!!!!!!!!!!!!!in save Action"+formDTO.getTehsilID());
					// logger.debug("!!!!!!!!!!!!!in save Action"+formDTO.getWardID());
					// logger.debug("!!!!!!!!!!!!!in save Action"+formDTO.getPatwariID());
					// logger.debug("!!!!!!!!!!!!!in save Action"+formDTO.getMohallaID());

					if (formDTO.isNewMohalla()) {
						boolean insertMohallaDetails = bd.insertPendingGuidelineValues(gda, gDTO, dFrom, dTo, roleId, funId, userId, "D");
						if (insertMohallaDetails) { // boolean insertSubClause =
													// bd.subClauseInsertionNewMohalla(propSub,
													// formDTO);
							// if(insertSubClause)
							// {
							messages.add("SUCCESS_MSG_1", new ActionMessage("conf_msg_guideline_derive_modify"));
							saveMessages(request, messages);
							formDTO.setConfirmationChk("4");
							request.setAttribute(CommonConstant.SUCCESS_MSG_1, "Mohalla/Village successfully modified");
							logger.debug("data copied successfully...");

							forwardJsp = CommonConstant.PUBLISH_MAIN;
							// }
							// else
							// {
							// messages.add("SUCCESS_MSG_1", new ActionMessage(
							// "conf_msg_guideline_derive_modify_fail"));
							// saveMessages(request, messages);
							// formDTO.setConfirmationChk("5");
							// request.setAttribute(CommonConstant.SUCCESS_MSG_1,"Guideline modification failed");
							// logger.debug("Sub CLAUSE INSERTION FAILED");
							// forwardJsp = CommonConstant.PUBLISH_MAIN;
							// }
						} else {
							messages.add("SUCCESS_MSG_1", new ActionMessage("conf_msg_guideline_derive_modify_fail"));
							saveMessages(request, messages);
							formDTO.setConfirmationChk("5");
							request.setAttribute(CommonConstant.SUCCESS_MSG_1, "Mohalla/Village modification failed");
							logger.debug("data copied successfully...");
							forwardJsp = CommonConstant.PUBLISH_MAIN;
						}
					}

					// boolean insertMohallaDetails =
					// bd.insertIndividualMohallaDetails(gda, gDTO, dFrom,
					// dTo,roleId,funId,userId);
					else {
						boolean insertMohallaDetails = bd.updatePendingGuidelineValues(gda, formDTO, dFrom, dTo, roleId, funId, userId);

						if (insertMohallaDetails) {
							eForm.setNewMohallas(new ArrayList());
							eForm.setNewMohallas(bd.getNewMohallaName(language, formDTO));
							int i = 0;
							formDTO.setUrbanFlag("Y");
							eForm.setMohallaList(bd.getColonyName(language, formDTO.getWardID(), formDTO.getGuideLineID()));
							if (eForm.getMohallaList() == null || eForm.getMohallaList().size() == 0) {
								eForm.setWardList(bd.getWardPatwari(language, formDTO.getSubAreaId(), formDTO.getTehsilID(), formDTO.getGuideLineID()));
							}

							if (eForm.getWardList() == null || eForm.getWardList().size() == 0) {
								eForm.setSubAreaList(bd.getSubArea(language, formDTO.getAreaTypeID(), formDTO.getTehsilID(), formDTO.getUrbanFlag(), formDTO.getGuideLineID()));
							}

							if (eForm.getSubAreaList() == null || eForm.getSubAreaList().size() == 0) {
								formDTO.setAreaFlag("2");

								ArrayList areaTypes = bd.getAreasTypeList(formDTO);
								eForm.setAreaTypeList(areaTypes);

							}
							if (eForm.getAreaTypeList() == null || eForm.getAreaTypeList().size() == 0) {
								if (("Y").equalsIgnoreCase(formDTO.getGuidFlag())) {
									i = 1;
									eForm.setAreaTypeList(new ArrayList());

									bd.getUpdateSubmitSRAll(formDTO);
									formDTO.setDownlaodChkId("X");
									forwardJsp = CommonConstant.PUBLISH_MAIN;
								} else {
									formDTO.setAreaFlag("1");
									formDTO.setAreaTypeID("");
									ArrayList areaTypes = bd.getAreasTypeList(formDTO);
									eForm.setAreaTypeList(areaTypes);
								}
								Long guideID = Long.parseLong(formDTO.getGuideLineID());
								eForm.setTehsilList(bd.getTehsilListMaker(formDTO.getDistrictID(), guideID, formDTO));
								forwardJsp = CommonConstant.PUBLISH_MAIN;
							}

							if (i == 0) {
								messages.add("SUCCESS_MSG_1", new ActionMessage("conf_msg_guideline_derive_modify"));
								saveMessages(request, messages);
								formDTO.setConfirmationChk("4");
								request.setAttribute(CommonConstant.SUCCESS_MSG_1, "Guideline successfully modified");
								logger.debug("data copied successfully...");
								forwardJsp = CommonConstant.MAKER_DERIVE_2;
								// forwardJsp = CommonConstant.PUBLISH_MAIN;
							}

						} else {
							messages.add("SUCCESS_MSG_1", new ActionMessage("conf_msg_guideline_derive_modify_fail"));
							saveMessages(request, messages);
							formDTO.setConfirmationChk("5");
							request.setAttribute(CommonConstant.SUCCESS_MSG_1, "Guideline modification failed");
							logger.debug("data copied successfully...");
							forwardJsp = CommonConstant.PUBLISH_MAIN;
						}
					}
				}

				if (request.getParameter("actionName") != null) {
					if ("subClauseClick".equalsIgnoreCase(request.getParameter("actionName"))) {

						String propID = null;
						String propL1 = null;
						String propL2 = null;
						String index = null;
						String value = null;
						String sub = request.getParameter("selectedSubClause");
						// logger.debug(sub);
						propDetails = request.getParameter("prop");
						// logger.debug("*********************"+propDetails);
						formDTO.setPropDetails(propDetails);
						String val[] = formDTO.getPropDetails().split("_");
						request.setAttribute("propDetails", (String) formDTO.getPropDetails());
						int k = 0;
						ArrayList subClauseList = new ArrayList();

						if (val != null && val.length == 4) {
							propID = val[1];
							propL1 = val[2];
							propL2 = val[3];

						}

						formDTO.setPropertyID(propID);
						formDTO.setL1_ID(propL1);
						formDTO.setL2_ID(propL2);

						// logger.debug("GUIDELINE"+formDTO.getGuideLineID());
						// logger.debug("TEHSIL"+formDTO.getTehsilID());
						// /logger.debug("WARD"+formDTO.getWardID());
						// logger.debug("mohalla"+formDTO.getMohallaID());

						if (formDTO.isNewMohalla()) {
							subClauseList = bd.subclausesNotDerived(formDTO);
						} else {
							subClauseList = bd.subclauses(formDTO);
						}

						if (!sub.equals("")) {
							String selectedSubClause[] = (request.getParameter("selectedSubClause")).split("_");
							Iterator itr = subClauseList.iterator();
							while (itr.hasNext()) {
								logger.debug("While LooP");
								GuidelineDTO gDTO = (GuidelineDTO) itr.next();
								gDTO.getSubClauseId();
								for (int i = 0; i < selectedSubClause.length; i++) {
									logger.debug("FOR LOOP");
									if (selectedSubClause[i] != null && selectedSubClause[i].equals(gDTO.getSubClauseId())) {
										gDTO.setStatus("Y");
										logger.debug("STATUS EQUALS to Y");
										break;
									} else {
										gDTO.setStatus("N");
									}
								}
							}
							// logger.debug("SUB CLAUSES"+subClauseList.size());

						}
						if (formDTO.isModify()) {
							HashMap hMap = formDTO.getHMap();
							// logger.debug("SIZE OF HASH MAP IN READ ONLY CLICK"+hMap.size());
							String subIdArr[] = {};
							Set mapSet = (Set) hMap.entrySet();

							Iterator mapIterator = mapSet.iterator();
							Iterator itr = subClauseList.iterator();

							while (mapIterator.hasNext()) {
								Map.Entry mapEntry = (Map.Entry) mapIterator.next();
								if (mapEntry.getKey().equals(propDetails)) {
									logger.debug("Inside if condition");
									// logger.debug("String Value is"+mapEntry.getValue());
									Object obj = null;
									if (mapEntry.getValue() != null) {
										subIdArr = ((String) mapEntry.getValue()).split("_");

									}
									break;
								}
							}

							while (itr.hasNext()) {
								logger.debug("While LooP");
								GuidelineDTO gDTO = (GuidelineDTO) itr.next();
								gDTO.getSubClauseId();
								for (int i = 0; i < subIdArr.length; i++) {
									logger.debug("FOR LOOP");
									if (subIdArr[i] != null && subIdArr[i].equals(gDTO.getSubClauseId())) {
										gDTO.setStatus("Y");
										logger.debug("STATUS EQUALS to Y");
										break;
									} else {
										gDTO.setStatus("N");
									}
								}
							}
						}

						// logger.debug("SUB CLAUSES"+subClauseList.size());
						eForm.setSubClauseList(subClauseList);
						request.setAttribute("subClauseList", subClauseList);
						forwardJsp = CommonConstant.SUB_CLAUSE_DERIVE;
					}
				}

				if ("subClauseClickReadOnly".equalsIgnoreCase(request.getParameter("actionName"))) {

					String propID = null;
					String propL1 = null;
					String propL2 = null;
					String index = null;
					String value = null;
					ArrayList subClauseList = null;
					propDetails = request.getParameter("prop");
					// logger.debug("*********************"+propDetails);

					HashMap hMap = formDTO.getHMap();
					// logger.debug("SIZE OF HASH MAP IN READ ONLY CLICK"+hMap.size());

					Set mapSet = (Set) hMap.entrySet();

					Iterator mapIterator = mapSet.iterator();

					while (mapIterator.hasNext()) {
						Map.Entry mapEntry = (Map.Entry) mapIterator.next();
						if (mapEntry.getKey().equals(propDetails)) {
							logger.debug("Inside if condition");
							// logger.debug("String Value is"+mapEntry.getValue());
							Object obj = null;
							if (mapEntry.getValue() != null) {
								String subIds[] = ((String) mapEntry.getValue()).split("_");
								subClauseList = bd.subClause(subIds);
								logger.debug("size of arrayList in if condition");
							}
							break;
						}
					}

					eForm.setSubClauseList(subClauseList);
					request.setAttribute("subClauseList", subClauseList);
					forwardJsp = CommonConstant.SUB_CLAUSE_READ_ONLY;

				}
			}

		}
		logger.debug(forwardJsp);
		return mapping.findForward(forwardJsp);
	}
}
