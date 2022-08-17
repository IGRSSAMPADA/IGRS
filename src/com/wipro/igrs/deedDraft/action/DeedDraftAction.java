package com.wipro.igrs.deedDraft.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.deedDraft.bd.DeedDraftBD;
import com.wipro.igrs.deedDraft.constant.DeedDraftConstant;
import com.wipro.igrs.deedDraft.dto.DeedDraftAppDTO;
import com.wipro.igrs.deedDraft.dto.DeedDraftDTO;
import com.wipro.igrs.deedDraft.form.DeedDraftForm;

public class DeedDraftAction extends BaseAction {
	String forwardJsp = "";
	private Logger logger = Logger.getLogger(DeedDraftAction.class);

	@Override public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		String label = (String) request.getParameter("label");
		String roleId = (String) session.getAttribute("role");
		String funId = (String) session.getAttribute("functionId");
		String userId = (String) session.getAttribute("UserId");
		String officeId = (String) session.getAttribute("loggedToOffice");
		String language = (String) session.getAttribute("languageLocale");
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		if (form != null) {
			DeedDraftForm eForm = (DeedDraftForm) form;
			DeedDraftDTO formDTO = eForm.getDeedDTO();
			DeedDraftAppDTO deedAppDTO = eForm.getDeedAppDTO();
			DeedDraftBD bd = new DeedDraftBD();
			String actionName = null;
			if (label != null) {
				if (label.equalsIgnoreCase("create")) {
					ArrayList deedCategory;
					formDTO.setActionName("");
					formDTO.setDeedUploadPath(null);
					formDTO.setUpOrNot("");
					eForm.setErrorMessage("");
					eForm.setIsValidate("0");
					eForm.setIsProp("");
					eForm.setIsReg("");
					eForm.setIsEstampExemp("");
					eForm.setIsRegExemp("");
					eForm.setPropOptionalCheck("N");
					eForm.setIsSelected("N");
					formDTO.setRegTxnId("");
					formDTO.setDeedName("");
					formDTO.setDeedContent("");
					formDTO.setAppTypeId("");
					formDTO.setDeedId("");
					formDTO.setPageId("1");
					eForm.setAppTypeSelect("");
					eForm.setIsVal("N");
					eForm.setTotMv(new BigDecimal("0.0"));
					formDTO.setFirstPageChk("Y");
					formDTO.setFirstPageLimit(pr.getValue("deed_draft_first_page_limit"));
					formDTO.setRestPagesLimit(pr.getValue("deed_draft_rest_pages_limit"));
					// saurav for deed draft changes
					eForm.setConsenterList(new ArrayList());
					deedAppDTO.setPropType("");
					deedAppDTO.setDutyTxnId("");
					eForm.setRegExemption(new ArrayList<String>());
					eForm.setStampExemption(new ArrayList<String>());
					ArrayList i = new ArrayList();
					for (int j = 0; j < 2; j++) {
						deedAppDTO = new DeedDraftAppDTO();
						if (j == 0) {
							deedAppDTO.setAppTypeID("1");
							deedAppDTO.setAppType(language.equals("en") ? "E-stamp" : "ई-स्टाम्प");
						}
						if (j == 1) {
							deedAppDTO.setAppTypeID("2");
							deedAppDTO.setAppType(language.equals("en") ? "Registration Process" : "पंजीयन प्रक्रिया");
						}
						i.add(deedAppDTO);
					}
					eForm.setAppTypeList(i);
					forwardJsp = "deedDraftForm1";
				} else if (label.equalsIgnoreCase("viewpdf")) {
					formDTO.setActionName("");
					formDTO.setDeedId("");
					forwardJsp = "deedDraftPdfSerach";
				}
			}
			if (request.getParameter("actionName") != null)
				actionName = request.getParameter("actionName");
			else
				actionName = formDTO.getActionName();
			logger.debug("ACTION NAME ----------->" + actionName);
			if (actionName.equalsIgnoreCase(DeedDraftConstant.SAVE_ACTION)) {
				String appType = "E";
				if ("1".equals(eForm.getAppTypeSelect())) {
					appType = "E";
				} else {
					appType = "R";
				}
				String applicationId = "";
				if ("R".equals(appType)) {
					applicationId = formDTO.getRegTxnId();
					String adjuFlag = bd.getAdjudicationFlag(formDTO.getRegTxnId());
					if (adjuFlag != null && !("".equals(adjuFlag))) {
						appType = adjuFlag;
					}
				}
				boolean validDeed = bd.getDeedVerified(applicationId, appType, formDTO.getDeedId());
				boolean flag = bd.saveDeedDraftDetails(formDTO, userId, appType, applicationId);
				logger.debug("deedId after first page insertion--->" + formDTO.getDeedId());
				if (flag) {
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.SUCCESS_MSG, "Data has been saved successfully. Deed Id is :- " + formDTO.getDeedId());
					else
						request.setAttribute(DeedDraftConstant.SUCCESS_MSG, "डेटा को सफलतापूर्वक सुरक्षित किया गया है | डीड आईडी :- " + formDTO.getDeedId());
					forwardJsp = "deedDraftForm1";
				} else {
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Data has not been saved properly. Please try again");
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "डेटा ठीक से दर्ज नहीं किया गया । पुनः प्रयास करें");
					forwardJsp = "deedDraftForm1";
				}
			} else if (actionName.equalsIgnoreCase("appTypeCheck")) {
				String propCheck = "N";
				String propOptionalCheck = "N";
				String isVal = "N";
				eForm.setRegExemption(new ArrayList<String>());
				eForm.setStampExemption(new ArrayList<String>());
				eForm.setIsSelected("Y");
				String regTxnId = formDTO.getRegTxnId();
				String s = regTxnId.substring(0, 1);
				int totMv = 0;
				BigDecimal totMarketValue = new BigDecimal("0.0");
				if ("0".equals(s)) {
					regTxnId = regTxnId.substring(1);
				}
				eForm.getDeedAppDTO().setRegTxnId(regTxnId);
				ArrayList list = bd.checkValidRegID(regTxnId);
				//System.out.println("Reg Txn id from table " + list.get(0));
				if (list != null && !list.isEmpty()) {
					if (list.get(0).equals(regTxnId)) {
						if (list.get(2).equals(userId)) {
							String deedId = (String) list.get(3);
							String instId = (String) list.get(4);
							propCheck = bd.getPropRelated(deedId, instId);
							propOptionalCheck = bd.getPropOptionalCheck(deedId, instId);
							isVal = bd.getPropValCheck(deedId, instId);
							eForm.setIsVal(isVal);
							if (!(list.get(1) == null)) {
								eForm.setIsVal("N");
								eForm.setIsValidate("0");
								if ("en".equals(language)) {
									eForm.setErrorMessage("Deed Draft Id has already been consumed for  " + regTxnId + " which is " + list.get(1));
								} else {
									eForm.setErrorMessage(regTxnId + " के लिए डीड पहले से ही उपयोग किया जा चूका है जो  " + list.get(1) + " है|");
								}
							} else if (!(("5").equals(list.get(5)) || ("3").equals(list.get(5)) || ("N").equals(propCheck) || ("6").equals(list.get(5)))) {
								eForm.setIsVal("N");
								eForm.setIsReg("");
								eForm.setIsValidate("0");
								if ("en".equals(language)) {
									eForm.setErrorMessage("Please add property details first");
								} else {
									eForm.setErrorMessage("कृपया पहले संपत्ति विवरण जोड़ें |");
								}
							} else {
								eForm.setTotMv(totMarketValue);
								eForm.setIsValidate("1");
								eForm.setIsReg("Y");
								eForm.setPropCount(1);
								formDTO.setAppTypeId("");
								ArrayList propDetails = new ArrayList();
								ArrayList dutyDetails = new ArrayList();
								ArrayList propDutyDetailsTemp = new ArrayList();//for property wise Duty details
								propDetails = bd.getPropdetails(regTxnId);
								dutyDetails = bd.getDutyDetails(regTxnId);
								String dutyTxnId = (String) ((ArrayList) dutyDetails.get(0)).get(0);
								eForm.setConsenterList(new ArrayList());
								int propCount = propDetails.size();
								eForm.setPropCount(propCount);
								//Consenter details
								ArrayList consenterDetailsTemp = bd.getConsenterDetails(regTxnId, userId);
								ArrayList consenterList = new ArrayList();
								DeedDraftAppDTO deedConsenterDetails = null;
								if (consenterDetailsTemp != null && !consenterDetailsTemp.isEmpty()) {
									for (int consenterCount = 0; consenterCount < consenterDetailsTemp.size(); consenterCount++) {
										ArrayList consenterDetails = (ArrayList) (consenterDetailsTemp).get(consenterCount);
										deedConsenterDetails = new DeedDraftAppDTO();
										deedConsenterDetails.setConsenterName((String) consenterDetails.get(0));
										deedConsenterDetails.setConsenterStampDuty((String) consenterDetails.get(1));
										deedConsenterDetails.setConsenterMunicipalDuty((String) consenterDetails.get(2));
										deedConsenterDetails.setConsenterJanpadDuty((String) consenterDetails.get(3));
										deedConsenterDetails.setConsenterUpkarDuty((String) consenterDetails.get(4));
										deedConsenterDetails.setConsenterTotal((String) consenterDetails.get(5));
										deedConsenterDetails.setConsenterRegFee((String) consenterDetails.get(6));
										deedConsenterDetails.setConsenterTotalAfterExemp((String) consenterDetails.get(7));
										deedConsenterDetails.setConsenterRegFeeAfterExemp((String) consenterDetails.get(8));
										deedConsenterDetails.setConsenterExempDuty((String) consenterDetails.get(9));
										deedConsenterDetails.setConsenterExempRegFee((String) consenterDetails.get(10));
										eForm.getConsenterList().add(deedConsenterDetails);
									}
								}
								/*if (!propDetails.isEmpty()){
									for (int k = 0; k < propDetails.size(); k++) {
										ArrayList lst = (ArrayList) propDetails
										.get(k);
										valIds.add((String) lst.get(10));
									}
								} else if (propDetails.isEmpty()){
									String deedId = (String) list.get(3);
									String instId = (String) list.get(4);
									propCheck = bd.getPropRelated(deedId,instId);
								}
								*/
								//for prop type check
								//String propType="";
								/*ArrayList plotList = new ArrayList();
								ArrayList agriList = new ArrayList();
								ArrayList buildingList = new ArrayList();*/
								/*for(int valLen = 0; valLen<valIds.size();valLen++){
									String vlId = (String)valIds.get(valLen);
									propType = bd.getPropType(vlId);
									ArrayList build=null;
									ArrayList plot = null;
									ArrayList agri = null;
										if("2".equals(propType)){
											build = new ArrayList();
											build = bd.getbuildingView(vlId, language);
										}
										if(build!=null){
											buildingList.add(build);
										}
									
									if(plot!=null){
										plotList.add(plot);
									}
								}*/
								//valDetails = bd.getValuationDetails(valIds, language);
								ArrayList appDetails = bd.getAppDetails(regTxnId);
								deedAppDTO.setDeedType((String) ((language.equalsIgnoreCase("en")) ? appDetails.get(0) : appDetails.get(1)));
								deedAppDTO.setInstrumentType((String) ((language.equalsIgnoreCase("en")) ? appDetails.get(2) : appDetails.get(3)));
								/*deedAppDTO.setSubInstCheck(appDetails.get(4).equals("--")?"N":"Y");
								deedAppDTO.setSubInstrumentType(appDetails.get(4).equals("--")?"":(String)((language.equalsIgnoreCase("en"))? appDetails.get(4):appDetails.get(5)));
								*/
								//property details
								if (!("N").equals(propCheck)) {
									eForm.setIsProp("Y");
									DeedDraftAppDTO d = null;
									ArrayList userEnterableValue = new ArrayList();
									ArrayList a = new ArrayList();
									for (int k = 0; k < propDetails.size(); k++) {
										d = new DeedDraftAppDTO();
										d.setPropDutyUserPropWiseEnterableList(new ArrayList());
										ArrayList lst = (ArrayList) propDetails.get(k);
										String propId = (String) lst.get(1);
										d.setProprtyId((String) lst.get(1));
										d.setPropType("en".equals(language) ? (String) lst.get(2) : (String) lst.get(12));
										d.setVikasKhand((String) lst.get(3));
										d.setRiCircle((String) lst.get(4));
										d.setLayoutDetails((String) lst.get(5));
										d.setNazoolOrSheetNumber((String) lst.get(6));
										d.setPlotNumber((String) lst.get(7));
										d.setPropertyAddress((String) lst.get(8));
										d.setPropertyLandmark((String) lst.get(9));
										d.setIsUrban((String) lst.get(13));
										String valId = ((String) lst.get(10) == "" || (String) lst.get(10) == null) ? "--" : (String) lst.get(10);
										d.setValId(valId);
										ArrayList khasraDetails = bd.getKhasraDetails(propId);
										//for prop duty details
										propDutyDetailsTemp = bd.getPropDutyDetails(dutyTxnId, regTxnId, propId);
										d.setIsPropDuty("");
										if (propDutyDetailsTemp != null && !propDutyDetailsTemp.isEmpty()) {
											d.setIsPropDuty("Y");
											ArrayList propDutyDetails = (ArrayList) propDutyDetailsTemp.get(0);
											d.setPropDutyStampDuty((String) propDutyDetails.get(3));
											d.setPropDutyMunicipalDuty((String) propDutyDetails.get(4));
											d.setPropDutyBlockDuty((String) propDutyDetails.get(5));
											d.setPropDutyUpkarDuty((String) propDutyDetails.get(6));
											d.setPropDutyTotal((String) propDutyDetails.get(7));
											d.setPropDutyRegFee((String) propDutyDetails.get(8));
											d.setPropDutyPayableDuty((String) propDutyDetails.get(11));
											d.setPropDutyPayableRegFee((String) propDutyDetails.get(12));
											d.setPropDutySysMv((String) propDutyDetails.get(13));
											d.setPropExempDuty((String) propDutyDetails.get(7));
											d.setPropExempRegFee((String) propDutyDetails.get(7));
											userEnterableValue = bd.getUserEnterableValue(dutyTxnId, (String) propDutyDetails.get(2), language);
											if (userEnterableValue != null && !userEnterableValue.isEmpty()) {
												for (int uevLen = 0; uevLen < userEnterableValue.size(); uevLen++) {
													ArrayList list1 = (ArrayList) userEnterableValue.get(uevLen);
													DeedDraftAppDTO userEnterbleValDTO = new DeedDraftAppDTO();
													userEnterbleValDTO.setPropDutyLabelName((String) list1.get(0));
													userEnterbleValDTO.setPropDutyLabelValue((String) list1.get(1));
													d.getPropDutyUserPropWiseEnterableList().add(userEnterbleValDTO);
												}
											}
											totMarketValue = totMarketValue.add(new BigDecimal((String) propDutyDetails.get(13)));
										}/*else {
																																																			userEnterableValue = bd.getUserEnterableValue(dutyTxnId,"", language);
																																																			if(userEnterableValue!=null && !userEnterableValue.isEmpty()){
																																																				userEnterableValue = (ArrayList) userEnterableValue.get(0);
																																																				d.setPropDutyLabelName((String)userEnterableValue.get(0));
																																																				d.setPropDutyLabelValue((String)userEnterableValue.get(1));
																																																				}
																																																			
																																																			
																																																		}*/
										d.setKhashraList(new ArrayList());
										if (khasraDetails != null) {
											ArrayList lst1 = new ArrayList();
											DeedDraftAppDTO dAppDTO = null;
											for (int i = 0; i < khasraDetails.size(); i++) {
												lst1 = (ArrayList) khasraDetails.get(i);
												dAppDTO = new DeedDraftAppDTO();
												dAppDTO.setKhashraArea((String) lst1.get(0));
												dAppDTO.setKhashraNumber((String) lst1.get(1));
												dAppDTO.setLagan((String) lst1.get(2));
												dAppDTO.setRinPushtikaNumber((String) lst1.get(3));
												dAppDTO.setNorthBoundary((String) lst1.get(4));
												dAppDTO.setSouthBoundary((String) lst1.get(5));
												dAppDTO.setWestBoundary((String) lst1.get(6));
												dAppDTO.setEastBoundary((String) lst1.get(7));
												d.getKhashraList().add(dAppDTO);
											}
										}
										if (khasraDetails.isEmpty()) {
											ArrayList lst1 = new ArrayList();
											DeedDraftAppDTO dAppDTO = null;
											dAppDTO = new DeedDraftAppDTO();
											dAppDTO.setKhashraArea("-");
											dAppDTO.setKhashraNumber("-");
											dAppDTO.setLagan("-");
											dAppDTO.setRinPushtikaNumber("-");
											dAppDTO.setNorthBoundary("-");
											dAppDTO.setSouthBoundary("-");
											dAppDTO.setWestBoundary("-");
											dAppDTO.setEastBoundary("-");
											d.getKhashraList().add(dAppDTO);
										}
										/*d.setKhashraArea((String) lst.get(11));
										d.setKhashraNumber((String) lst.get(12));
										d.setLagan((String) lst.get(13));
										d.setRinPushtikaNumber((String) lst.get(14));
										d.setNorthBoundary((String) lst.get(15));
										d.setSouthBoundary((String) lst.get(16));
										d.setWestBoundary((String) lst.get(17));
										d.setEastBoundary((String) lst.get(18));*/
										String propertyType = (String) lst.get(11);
										if ("1".equals(propertyType)) {
											ArrayList plot = new ArrayList();
											d.setPropTypeId("P");
											plot = bd.getValuationDetails(valId, language);
											plot = (ArrayList) plot.get(0);
											d.setDistrictName((String) plot.get(0));
											d.setTehsilName((String) plot.get(1));
											d.setAreaTypeName((String) plot.get(2));
											d.setWardName((String) plot.get(3));
											d.setGoverningMunipalBodyName((String) plot.get(4));
											d.setColonyName((String) plot.get(5));
											d.setTotalArea((String) plot.get(6));
											d.setResidentialArea(("0").equalsIgnoreCase((String) plot.get(7)) ? "--" : ((String) plot.get(7)));
											d.setCommercialArea(("0").equalsIgnoreCase((String) plot.get(8)) ? "--" : ((String) plot.get(8)));
											d.setIndustrialArea(("0").equalsIgnoreCase((String) plot.get(9)) ? "--" : ((String) plot.get(9)));
											d.setEducationalArea(("0").equalsIgnoreCase((String) plot.get(10)) ? "--" : ((String) plot.get(10)));
											d.setHealthServiceArea(("0").equalsIgnoreCase((String) plot.get(11)) ? "--" : ((String) plot.get(11)));
											d.setOtherArea(("0").equalsIgnoreCase((String) plot.get(12)) ? "--" : ((String) plot.get(12)));
											d.setResCumCommPurpose((String) plot.get(15));
											d.setResiCumCommPurposeArea(("0").equalsIgnoreCase((String) plot.get(16)) ? "--" : ((String) plot.get(16)));
										} else if ("2".equals(propertyType)) {
											ArrayList build = new ArrayList();
											d.setPropTypeId("B");
											build = bd.getbuildingView(valId, language);
											build = (ArrayList) build.get(0);
											d.setDistrictName((String) build.get(0));
											d.setTehsilName((String) build.get(1));
											d.setAreaTypeName((String) build.get(2));
											d.setSubAreaTypeName((String) build.get(3));
											d.setWardName((String) build.get(4));
											d.setColonyName((String) build.get(5));
											String buildingTxnId = (String) build.get(6);
											String buildingId = (String) build.get(7);
											d.setBuildingTypeId(buildingId);
											d.setResCumCommPurpose((String) build.get(35));
											String areaId = ((String) build.get(36));
											d.setAreaId(areaId);
											// added for construction slab
											// changes by saurav
											String isRes = ((String) build.get(38));
											String consSlabId = "";
											String consSlabDesc = "";
											String isResidential = "N";
											d.setIsConstSlabApplied("N");
											d.setAppliedConstSlabDesc("");
											if ("1".equals(buildingId)) {
												if (((String) build.get(23) == null) || ((String) build.get(23)).equalsIgnoreCase("")) {
													isResidential = "N";
													d.setIsConstSlabApplied("NR");
												} else {
													if (((String) build.get(17)).equals((String) build.get(23))) {
														isResidential = "Y";
													} else {
														isResidential = "N";
													}
												}
											}
											if (isRes.equalsIgnoreCase("R")) {
												isResidential = "Y";
											} else {
												isResidential = "N";
											}
											if ("3".equals(buildingId) && "18".equals(build.get(34).toString())) {
												isResidential = "Y";
											}
											if (("1".equals(buildingId) && "Y".equals(isResidential)) || "2".equals(buildingId) || ("3".equals(buildingId) && "Y".equals(isResidential))) {
												consSlabId = ((String) build.get(37));
												consSlabDesc = consSlabId.equals("0") ? "NA" : bd.getConstructionSlabDesc(consSlabId, language);
												if (!(consSlabDesc.equalsIgnoreCase("NA"))) {
													d.setIsConstSlabApplied("Y");
													d.setAppliedConstSlabDesc(consSlabDesc);
												}
												if (isRes.equalsIgnoreCase("NR")) {
													d.setIsConstSlabApplied("NR");
													isResidential = "N";
												}
											}
											// end of construction slab changes
											if ("4".equals(buildingId)) {
												d.setOpenTerraceArea((String) build.get(15));
												d.setOpenTerraceUsage((String) build.get(14));
											} else if ("2".equals(buildingId)) {
												d.setTotalArea((String) build.get(8));
												d.setFloorName((String) build.get(9));
												d.setOlder("Y");
												String older20 = build.get(10) == null ? "" : (String) build.get(10);
												String older50 = build.get(11) == null ? "" : (String) build.get(11);
												if ("Y".equals(older50)) {
													d.setOlder("50");
												}
												if ("Y".equals(older20)) {
													d.setOlder("20");
												}
												if ("N".equals(older50) && "N".equals(older20)) {
													d.setOlder("N");
												}// construction slab changes by
												// saurav
												if (isResidential.equals("Y")) {
													d.setOlder("N");
												}
												// end of construction slab
												// changes
												String lift = (String) build.get(12) == null ? "" : (String) build.get(12);
												if ("Y".equalsIgnoreCase(lift)) {
													d.setIsLiftMall("Y");
												} else {
													d.setIsLiftMall("N");
												}
												String openTerrace = (String) build.get(13) == null ? "" : (String) build.get(13);
												if ("Y".equalsIgnoreCase(openTerrace)) {
													d.setOpenTerraceFlag("Y");
													d.setOpenTerraceArea((String) build.get(15));
													d.setOpenTerraceUsage((String) build.get(14));
												} else {
													d.setOpenTerraceFlag("N");
												}
											} else if ("3".equals(buildingId)) {
												String multiStoreyId = (String) build.get(34).toString();
												if ("18".equalsIgnoreCase(multiStoreyId)) {
													d.setMultiStoreyTypeId("18");
													d.setMultiStoreyUsageName((String) build.get(16));
													d.setTotalArea((String) build.get(8));
													d.setCommonArea((String) build.get(19));
													d.setBuiltUpArea((String) build.get(18));
													d.setFloorName((String) build.get(9));
													d.setOlder("N");
													String older20 = (String) build.get(10) == null ? "" : (String) build.get(10);
													String older50 = (String) build.get(11) == null ? "" : (String) build.get(11);
													if ("Y".equalsIgnoreCase(older50)) {
														d.setOlder("50");
													}
													if ("Y".equalsIgnoreCase(older20)) {
														d.setOlder("20");
													}// construction slab
													// changes by saurav
													if (isResidential.equals("Y")) {
														d.setOlder("N");
													}
													String lift = (String) build.get(12) == null ? "" : (String) build.get(12);
													if ("Y".equalsIgnoreCase(lift)) {
														d.setIsLiftMall("Y");
													} else {
														d.setIsLiftMall("N");
													}
													String openTerrace = (String) build.get(13) == null ? "" : (String) build.get(13);
													if ("Y".equalsIgnoreCase(openTerrace)) {
														d.setOpenTerraceFlag("Y");
														d.setOpenTerraceArea((String) build.get(15));
														d.setOpenTerraceUsage((String) build.get(14));
													} else {
														d.setOpenTerraceFlag("N");
													}
												} else {
													d.setMultiStoreyTypeId("19");
													d.setMultiStoreyUsageName((String) build.get(16));
													d.setTotalArea((String) build.get(8));
													d.setCommonArea((String) build.get(19));
													d.setBuiltUpArea((String) build.get(18));
													d.setFloorName((String) build.get(9));
													d.setMultiStoreyUsageName((String) build.get(20));
													d.setOlder("N");
													String older20 = (String) build.get(10) == null ? "" : (String) build.get(10);
													String older50 = (String) build.get(11) == null ? "" : (String) build.get(11);
													if ("Y".equalsIgnoreCase(older50)) {
														d.setOlder("50");
													}
													if ("Y".equalsIgnoreCase(older20)) {
														d.setOlder("20");
													}
													String nearRoad = (String) build.get(22) == null ? "" : (String) build.get(22);
													if ("Y".equalsIgnoreCase(nearRoad)) {
														d.setNearRoad("Y");
													} else {
														d.setNearRoad("N");
													}
													String mall = (String) build.get(21) == null ? "" : (String) build.get(21);;
													if ("Y".equalsIgnoreCase(mall)) {
														d.setIsLiftMall("Y");
													} else {
														d.setIsLiftMall("N");
													}
													String openTerrace = (String) build.get(13) == null ? "" : (String) build.get(13);
													if ("Y".equalsIgnoreCase(openTerrace)) {
														d.setOpenTerraceFlag("Y");
														d.setOpenTerraceArea((String) build.get(15));
														d.setOpenTerraceUsage((String) build.get(14));
													} else {
														d.setOpenTerraceFlag("N");
													}
												}
											} else {
												d.setTotalArea((String) build.get(17));
												if (((String) build.get(23) == null) || ((String) build.get(23)).equalsIgnoreCase("")) {
													d.setResiArea("");
												} else {
													d.setResiArea((String) build.get(23));
												}
												if (((String) build.get(24) == null) || ((String) build.get(24)).equalsIgnoreCase("")) {
													d.setCommArea("");
												} else {
													d.setCommArea((String) build.get(24));
												}
												if (((String) build.get(25) == null) || ((String) build.get(25)).equalsIgnoreCase("")) {
													d.setIndArea("");
												} else {
													d.setIndArea((String) build.get(25));
												}
												if ((String) build.get(26) == null || ((String) build.get(26)).equalsIgnoreCase("")) {
													d.setSchoolArea("");
												} else {
													d.setSchoolArea((String) build.get(26));
												}
												if ((String) build.get(27) == null || ((String) build.get(27)).equalsIgnoreCase("")) {
													d.setHealthArea("");
												} else {
													d.setHealthArea((String) build.get(27));
												}
												if ((String) build.get(28) == null || ((String) build.get(28)).equalsIgnoreCase("")) {
													d.setOtherArea("");
												} else {
													d.setOtherArea((String) build.get(28));
												}
												if ((String) build.get(35) == null || ((String) build.get(35)).equalsIgnoreCase("")) {
													d.setResiCommArea("");
												} else {
													d.setResiCommArea((String) build.get(35));
												}
												String housingBoard = (String) build.get(33) == null ? "" : (String) build.get(33);
												if ("Y".equalsIgnoreCase(housingBoard)) {
													d.setIsHosingBoard("Y");
												} else {
													d.setIsHosingBoard("N");
												}
												String isAkvn = (String) build.get(32) == null ? "" : (String) build.get(32);
												if ("Y".equalsIgnoreCase(isAkvn)) {
													d.setIsAkvn("Y");
												} else {
													d.setIsAkvn("N");
												}
												String superConstruction = (String) build.get(31) == null ? "" : (String) build.get(31);
												if ("Y".equalsIgnoreCase(superConstruction)) {
													d.setIsSuperConstruction("Y");
												} else {
													d.setIsSuperConstruction("N");
												}
												String eduTcp = (String) build.get(29) == null ? "" : (String) build.get(29);;
												String healthTCP = (String) build.get(30) == null ? "" : (String) build.get(30);;
												if ("Y".equalsIgnoreCase(healthTCP)) {
													d.setHealthTCP("Y");
												} else {
													d.setHealthTCP("N");
												}
												if ("Y".equalsIgnoreCase(eduTcp)) {
													d.setEduTCP("Y");
												} else {
													d.setEduTCP("N");
												}
												ArrayList floorAreaList = bd.getFloorList(buildingTxnId, language);
												if (floorAreaList != null) {
													d.setFloorAreaList(new ArrayList());
													for (int i = 0; i < floorAreaList.size(); i++) {
														ArrayList floorSubList = (ArrayList) floorAreaList.get(i);
														DeedDraftAppDTO ddto = new DeedDraftAppDTO();
														ddto.setFloorPropId((String) floorSubList.get(0));
														ddto.setFloorName((String) floorSubList.get(1));
														if ((String) floorSubList.get(2) == null || ((String) floorSubList.get(2)).equalsIgnoreCase("")) {
															ddto.setRccArea("0");
														} else {
															ddto.setRccArea((String) floorSubList.get(2));
														}
														if (floorSubList.get(5) == null || ((String) floorSubList.get(5)).equalsIgnoreCase("")) {
															ddto.setKacchaArea("0");
														} else {
															ddto.setKacchaArea((String) floorSubList.get(5));
														}
														if (floorSubList.get(4) == null || ((String) floorSubList.get(4)).equalsIgnoreCase("")) {
															ddto.setTinArea("0");
														} else {
															ddto.setTinArea((String) floorSubList.get(4));
														}
														if (floorSubList.get(3) == null || ((String) floorSubList.get(3)).equalsIgnoreCase("")) {
															ddto.setRbcArea("0");
														} else {
															ddto.setRbcArea((String) floorSubList.get(3));
														}
														if (floorSubList.get(6) == null || ((String) floorSubList.get(6)).equalsIgnoreCase("")) {
															ddto.setShopArea("0");
														} else {
															ddto.setShopArea((String) floorSubList.get(6));
														}
														if (floorSubList.get(7) == null || ((String) floorSubList.get(7)).equalsIgnoreCase("")) {
															ddto.setOfficeArea("0");
														} else {
															ddto.setOfficeArea((String) floorSubList.get(7));
														}
														if (floorSubList.get(8) == null || ((String) floorSubList.get(8)).equalsIgnoreCase("")) {
															ddto.setGodownArea("0");
														} else {
															ddto.setGodownArea((String) floorSubList.get(8));
														}
														d.getFloorAreaList().add(ddto);
													}
												}
											}
										} else if ("3".equals(propertyType)) {
											d.setPropTypeId("A");
											ArrayList agriDetails = bd.getAgriDetails(valId, language);
											for (int i = 0; i < agriDetails.size(); i++) {
												ArrayList subList = (ArrayList) agriDetails.get(i);
												d.setDistrictName((String) subList.get(0));
												d.setTehsilName((String) subList.get(1));
												d.setAreaTypeName((String) subList.get(2));
												d.setSubAreaTypeName((String) subList.get(3));
												d.setWardName((String) subList.get(4));
												d.setColonyName((String) subList.get(5));
												d.setCommonAgriSingleBuyer((String) subList.get(6));
												d.setCommonAgriSameFamily((String) subList.get(7) == null ? "-" : (String) subList.get(7).toString());
												d.setCommonAgriBuyerCount((String) subList.get(8) == null ? "-" : (String) subList.get(8).toString());
												d.setCommonAgriTreePresent((String) subList.get(9) == null ? "-" : (String) subList.get(9).toString());
												d.setCommonAgriDiscloseShare((String) subList.get(10) == null ? "-" : (String) subList.get(10).toString());
												d.setAreaId((String) subList.get(11));
											}
											ArrayList agriPropDetails = bd.getAgriPropDetails(valId, language);
											String agriTxnId = "";
											ArrayList agriProp = new ArrayList();
											for (int i = 0; i < agriPropDetails.size(); i++) {
												ArrayList subList = (ArrayList) agriPropDetails.get(i);
												DeedDraftAppDTO d1 = new DeedDraftAppDTO();
												d1.setCommonAgriTxnid((String) subList.get(0) == null ? "-" : (String) subList.get(0));
												d1.setCommonAgriSubTypeId((String) subList.get(1) == null ? "-" : (String) subList.get(1));
												d1.setCommonAgriPropSubTypeName((String) subList.get(2) == null ? "-" : (String) subList.get(2));
												d1.setCommonTotalUndivArea((String) subList.get(3) == null ? "-" : (String) subList.get(3));
												d1.setCommonTotalUnirriOneCrop((String) subList.get(4) == null ? "-" : (String) subList.get(4));
												d1.setCommonTotalUnirriTwoCrop((String) subList.get(5) == null ? "-" : (String) subList.get(5));
												d1.setCommonTotalIrrigatedArea((String) subList.get(6) == null ? "-" : (String) subList.get(6));
												d1.setCommonAgriConstruction((String) subList.get(7) == null ? "-" : (String) subList.get(7));
												d1.setCommonTotalDivArea((String) subList.get(8) == null ? "-" : (String) subList.get(8));
												d1.setCommonTotalResiArea((String) subList.get(9) == null ? "-" : (String) subList.get(9));
												d1.setCommonTotalCommArea((String) subList.get(10) == null ? "-" : (String) subList.get(10));
												d1.setCommonTotalIndArea((String) subList.get(11) == null ? "-" : (String) subList.get(11));
												d1.setCommonTotalEduArea((String) subList.get(12) == null ? "-" : (String) subList.get(12));
												d1.setCommonTotalHealthArea((String) subList.get(13) == null ? "-" : (String) subList.get(13));
												d1.setCommonTotalOtherArea((String) subList.get(14) == null ? "-" : (String) subList.get(14));
												d1.setCommonAgriEducationTcp((String) subList.get(15) == null ? "-" : (String) subList.get(15));
												d1.setCommonAgriHealthTcp((String) subList.get(16) == null ? "-" : (String) subList.get(16));
												agriTxnId = (String) subList.get(0);
												if ("Y".equals(d1.getCommonAgriConstruction())) {
													ArrayList constructionDetails = bd.getbuildingView(valId, language);;
													DeedDraftAppDTO deedAgriConstructionDetails = new DeedDraftAppDTO();
													d1.setAgriConstructionDetailsList(new ArrayList());
													ArrayList build = new ArrayList();
													deedAgriConstructionDetails.setPropTypeId("B");
													build = bd.getBuildingDetailsAgri(agriTxnId, language);
													build = (ArrayList) build.get(0);
													//clause list for construction done on agri land
													ArrayList clauseListTempAgriConst = bd.getClauseListAgriConst(language, agriTxnId);
													ArrayList clauseListAgriConst = new ArrayList();
													for (int clauseLength = 0; clauseLength < clauseListTempAgriConst.size(); clauseLength++) {
														ArrayList clauseDetailAgriConst = (ArrayList) clauseListTempAgriConst.get(clauseLength);
														DeedDraftAppDTO deedClauseAgriConst = new DeedDraftAppDTO();
														deedClauseAgriConst.setClauseId((String) clauseDetailAgriConst.get(0));
														if ("en".equals(language)) {
															deedClauseAgriConst.setClasueName((String) clauseDetailAgriConst.get(0));
														} else {
															deedClauseAgriConst.setClasueName((String) clauseDetailAgriConst.get(0));
														}
														clauseListAgriConst.add(deedClauseAgriConst);
													}
													deedAgriConstructionDetails.setAgriConstClauseList(clauseListAgriConst);
													deedAgriConstructionDetails.setDistrictName((String) build.get(0));
													deedAgriConstructionDetails.setTehsilName((String) build.get(1));
													deedAgriConstructionDetails.setAreaTypeName((String) build.get(2));
													deedAgriConstructionDetails.setSubAreaTypeName((String) build.get(3));
													deedAgriConstructionDetails.setWardName((String) build.get(4));
													deedAgriConstructionDetails.setColonyName((String) build.get(5));
													String buildingTxnId = (String) build.get(6);
													String buildingId = (String) build.get(7);
													deedAgriConstructionDetails.setBuildingTypeId(buildingId);
													d.setResCumCommPurpose((String) build.get(35));
													// construction slab rate
													// changes by saurav
													ArrayList constSlab = bd.getconstSlabDetail(agriTxnId, language);
													constSlab = (ArrayList) constSlab.get(0);
													String consSlabId = "";
													String consSlabDesc = "";
													String isResidential = "N";
													String isRes = ((String) constSlab.get(1));
													if (isRes.equalsIgnoreCase("R")) {
														isResidential = "Y";
													} else {
														isResidential = "N";
													}
													if ("1".equals(buildingId)) {
														if (((String) build.get(23) == null) || ((String) build.get(23)).equalsIgnoreCase("")) {
															isResidential = "N";
															deedAgriConstructionDetails.setIsConstSlabApplied("NR");
														} else {
															if (((String) build.get(17)).equals((String) build.get(23))) {
																isResidential = "Y";
															} else {
																isResidential = "N";
															}
														}
													}
													if ("3".equals(buildingId) && "18".equals(build.get(34).toString())) {
														isResidential = "Y";
													}
													if (("1".equals(buildingId) && "Y".equals(isResidential)) || "2".equals(buildingId) || ("3".equals(buildingId) && "Y".equals(isResidential))) {
														consSlabId = ((String) constSlab.get(0));
														consSlabDesc = consSlabId.equals("0") ? "NA" : bd.getConstructionSlabDesc(consSlabId, language);
														if (!(consSlabDesc.equalsIgnoreCase("NA"))) {
															deedAgriConstructionDetails.setIsConstSlabApplied("Y");
															deedAgriConstructionDetails.setAppliedConstSlabDesc(consSlabDesc);
														}

														if (isRes.equalsIgnoreCase("NR")) {
															deedAgriConstructionDetails.setIsConstSlabApplied("NR");
															isResidential = "N";
														}
													}
													// end of construction slab
													// rate changes
													if ("4".equals(buildingId)) {
														deedAgriConstructionDetails.setOpenTerraceArea((String) build.get(15));
														deedAgriConstructionDetails.setOpenTerraceUsage((String) build.get(14));
													} else if ("2".equals(buildingId)) {
														deedAgriConstructionDetails.setTotalArea((String) build.get(8));
														deedAgriConstructionDetails.setFloorName((String) build.get(9));
														deedAgriConstructionDetails.setOlder("Y");
														String older20 = build.get(10) == null ? "" : (String) build.get(10);
														String older50 = build.get(11) == null ? "" : (String) build.get(11);
														if ("Y".equals(older50)) {
															deedAgriConstructionDetails.setOlder("50");
														}
														if ("Y".equals(older20)) {
															deedAgriConstructionDetails.setOlder("20");
														}
														if (("N".equals(older50) && "N".equals(older20))) {
															deedAgriConstructionDetails.setOlder("N");
														}
														// construction slab
														// rate changes by
														// saurav
														if (isResidential.equals("Y")) {
															deedAgriConstructionDetails.setOlder("N");
														}
														// end of construction
														// slab rate changes
														String lift = (String) build.get(12) == null ? "" : (String) build.get(12);
														if ("Y".equalsIgnoreCase(lift)) {
															deedAgriConstructionDetails.setIsLiftMall("Y");
														} else {
															deedAgriConstructionDetails.setIsLiftMall("N");
														}
														String openTerrace = (String) build.get(13) == null ? "" : (String) build.get(13);
														if ("Y".equalsIgnoreCase(openTerrace)) {
															deedAgriConstructionDetails.setOpenTerraceFlag("Y");
															deedAgriConstructionDetails.setOpenTerraceArea((String) build.get(15));
															deedAgriConstructionDetails.setOpenTerraceUsage((String) build.get(14));
														} else {
															deedAgriConstructionDetails.setOpenTerraceFlag("N");
														}
													} else if ("3".equals(buildingId)) {
														String multiStoreyId = (String) build.get(34).toString();
														if ("18".equalsIgnoreCase(multiStoreyId)) {
															deedAgriConstructionDetails.setMultiStoreyTypeId("18");
															deedAgriConstructionDetails.setMultiStoreyUsageName((String) build.get(16));
															deedAgriConstructionDetails.setTotalArea((String) build.get(8));
															deedAgriConstructionDetails.setCommonArea((String) build.get(19));
															deedAgriConstructionDetails.setBuiltUpArea((String) build.get(18));
															deedAgriConstructionDetails.setFloorName((String) build.get(9));
															deedAgriConstructionDetails.setOlder("N");
															String older20 = (String) build.get(10) == null ? "" : (String) build.get(10);
															String older50 = (String) build.get(11) == null ? "" : (String) build.get(11);
															if ("Y".equalsIgnoreCase(older50)) {
																deedAgriConstructionDetails.setOlder("50");
															}
															if ("Y".equalsIgnoreCase(older20)) {
																deedAgriConstructionDetails.setOlder("20");
															}
															if (isResidential.equals("Y")) {
																deedAgriConstructionDetails.setOlder("N");
															}
															String lift = (String) build.get(12) == null ? "" : (String) build.get(12);
															if ("Y".equalsIgnoreCase(lift)) {
																deedAgriConstructionDetails.setIsLiftMall("Y");
															} else {
																deedAgriConstructionDetails.setIsLiftMall("N");
															}
															String openTerrace = (String) build.get(13) == null ? "" : (String) build.get(13);
															if ("Y".equalsIgnoreCase(openTerrace)) {
																deedAgriConstructionDetails.setOpenTerraceFlag("Y");
																deedAgriConstructionDetails.setOpenTerraceArea((String) build.get(15));
																deedAgriConstructionDetails.setOpenTerraceUsage((String) build.get(14));
															} else {
																deedAgriConstructionDetails.setOpenTerraceFlag("N");
															}
														} else {
															deedAgriConstructionDetails.setMultiStoreyTypeId("19");
															deedAgriConstructionDetails.setMultiStoreyUsageName((String) build.get(16));
															deedAgriConstructionDetails.setTotalArea((String) build.get(8));
															deedAgriConstructionDetails.setCommonArea((String) build.get(19));
															deedAgriConstructionDetails.setBuiltUpArea((String) build.get(18));
															deedAgriConstructionDetails.setFloorName((String) build.get(9));
															deedAgriConstructionDetails.setMultiStoreyUsageName((String) build.get(20));
															deedAgriConstructionDetails.setOlder("N");
															String older20 = (String) build.get(10) == null ? "" : (String) build.get(10);
															String older50 = (String) build.get(11) == null ? "" : (String) build.get(11);
															if ("Y".equalsIgnoreCase(older50)) {
																deedAgriConstructionDetails.setOlder("50");
															}
															if ("Y".equalsIgnoreCase(older20)) {
																deedAgriConstructionDetails.setOlder("20");
															}
															String nearRoad = (String) build.get(22) == null ? "" : (String) build.get(22);
															if ("Y".equalsIgnoreCase(nearRoad)) {
																deedAgriConstructionDetails.setNearRoad("Y");
															} else {
																deedAgriConstructionDetails.setNearRoad("N");
															}
															String mall = (String) build.get(21) == null ? "" : (String) build.get(21);;
															if ("Y".equalsIgnoreCase(mall)) {
																deedAgriConstructionDetails.setIsLiftMall("Y");
															} else {
																deedAgriConstructionDetails.setIsLiftMall("N");
															}
															String openTerrace = (String) build.get(13) == null ? "" : (String) build.get(13);
															if ("Y".equalsIgnoreCase(openTerrace)) {
																deedAgriConstructionDetails.setOpenTerraceFlag("Y");
																deedAgriConstructionDetails.setOpenTerraceArea((String) build.get(15));
																deedAgriConstructionDetails.setOpenTerraceUsage((String) build.get(14));
															} else {
																deedAgriConstructionDetails.setOpenTerraceFlag("N");
															}
														}
													} else {
														deedAgriConstructionDetails.setTotalArea((String) build.get(17));
														if (((String) build.get(23) == null) || ((String) build.get(23)).equalsIgnoreCase("")) {
															deedAgriConstructionDetails.setResiArea("");
														} else {
															deedAgriConstructionDetails.setResiArea((String) build.get(23));
														}
														if (((String) build.get(24) == null) || ((String) build.get(24)).equalsIgnoreCase("")) {
															deedAgriConstructionDetails.setCommArea("");
														} else {
															deedAgriConstructionDetails.setCommArea((String) build.get(24));
														}
														if (((String) build.get(25) == null) || ((String) build.get(25)).equalsIgnoreCase("")) {
															deedAgriConstructionDetails.setIndArea("");
														} else {
															deedAgriConstructionDetails.setIndArea((String) build.get(25));
														}
														if ((String) build.get(26) == null || ((String) build.get(26)).equalsIgnoreCase("")) {
															deedAgriConstructionDetails.setSchoolArea("");
														} else {
															deedAgriConstructionDetails.setSchoolArea((String) build.get(26));
														}
														if ((String) build.get(27) == null || ((String) build.get(27)).equalsIgnoreCase("")) {
															deedAgriConstructionDetails.setHealthArea("");
														} else {
															deedAgriConstructionDetails.setHealthArea((String) build.get(27));
														}
														if ((String) build.get(28) == null || ((String) build.get(28)).equalsIgnoreCase("")) {
															deedAgriConstructionDetails.setOtherArea("");
														} else {
															deedAgriConstructionDetails.setOtherArea((String) build.get(28));
														}
														if ((String) build.get(35) == null || ((String) build.get(35)).equalsIgnoreCase("")) {
															deedAgriConstructionDetails.setResiCommArea("");
														} else {
															deedAgriConstructionDetails.setResiCommArea((String) build.get(35));
														}
														String housingBoard = (String) build.get(33) == null ? "" : (String) build.get(33);
														if ("Y".equalsIgnoreCase(housingBoard)) {
															deedAgriConstructionDetails.setIsHosingBoard("Y");
														} else {
															deedAgriConstructionDetails.setIsHosingBoard("N");
														}
														String isAkvn = (String) build.get(32) == null ? "" : (String) build.get(32);
														if ("Y".equalsIgnoreCase(isAkvn)) {
															deedAgriConstructionDetails.setIsAkvn("Y");
														} else {
															deedAgriConstructionDetails.setIsAkvn("N");
														}
														String superConstruction = (String) build.get(31) == null ? "" : (String) build.get(31);
														if ("Y".equalsIgnoreCase(superConstruction)) {
															deedAgriConstructionDetails.setIsSuperConstruction("Y");
														} else {
															deedAgriConstructionDetails.setIsSuperConstruction("N");
														}
														String eduTcp = (String) build.get(29) == null ? "" : (String) build.get(29);;
														String healthTCP = (String) build.get(30) == null ? "" : (String) build.get(30);;
														if ("Y".equalsIgnoreCase(healthTCP)) {
															deedAgriConstructionDetails.setHealthTCP("Y");
														} else {
															deedAgriConstructionDetails.setHealthTCP("N");
														}
														if ("Y".equalsIgnoreCase(eduTcp)) {
															deedAgriConstructionDetails.setEduTCP("Y");
														} else {
															deedAgriConstructionDetails.setEduTCP("N");
														}
														ArrayList floorAreaList = bd.getFloorList(buildingTxnId, language);
														if (floorAreaList != null) {
															deedAgriConstructionDetails.setFloorAreaList(new ArrayList());
															for (int j = 0; j < floorAreaList.size(); j++) {
																ArrayList floorSubList = (ArrayList) floorAreaList.get(j);
																DeedDraftAppDTO ddto = new DeedDraftAppDTO();
																ddto.setFloorPropId((String) floorSubList.get(0));
																ddto.setFloorName((String) floorSubList.get(1));
																if ((String) floorSubList.get(2) == null || ((String) floorSubList.get(2)).equalsIgnoreCase("")) {
																	ddto.setRccArea("0");
																} else {
																	ddto.setRccArea((String) floorSubList.get(2));
																}
																if (floorSubList.get(5) == null || ((String) floorSubList.get(5)).equalsIgnoreCase("")) {
																	ddto.setKacchaArea("0");
																} else {
																	ddto.setKacchaArea((String) floorSubList.get(5));
																}
																if (floorSubList.get(4) == null || ((String) floorSubList.get(4)).equalsIgnoreCase("")) {
																	ddto.setTinArea("0");
																} else {
																	ddto.setTinArea((String) floorSubList.get(4));
																}
																if (floorSubList.get(3) == null || ((String) floorSubList.get(3)).equalsIgnoreCase("")) {
																	ddto.setRbcArea("0");
																} else {
																	ddto.setRbcArea((String) floorSubList.get(3));
																}
																if (floorSubList.get(6) == null || ((String) floorSubList.get(6)).equalsIgnoreCase("")) {
																	ddto.setShopArea("0");
																} else {
																	ddto.setShopArea((String) floorSubList.get(6));
																}
																if (floorSubList.get(7) == null || ((String) floorSubList.get(7)).equalsIgnoreCase("")) {
																	ddto.setOfficeArea("0");
																} else {
																	ddto.setOfficeArea((String) floorSubList.get(7));
																}
																if (floorSubList.get(8) == null || ((String) floorSubList.get(8)).equalsIgnoreCase("")) {
																	ddto.setGodownArea("0");
																} else {
																	ddto.setGodownArea((String) floorSubList.get(8));
																}
																deedAgriConstructionDetails.getFloorAreaList().add(ddto);
															}
														}
													}
													d1.getAgriConstructionDetailsList().add(deedAgriConstructionDetails);
												}
												agriProp.add(d1);
											}
											d.setAgriPropStore(agriProp);
											ArrayList treeListPresent = new ArrayList();
											ArrayList agriTreeDetails = bd.getAgriTreeDetails(valId, language);
											if (bd.getAgriTreeDetails(valId, language).size() != 0 && bd.getAgriTreeDetails(valId, language) != null) {
												d.setAgriTreeChkd("Y");
												for (int i = 0; i < agriTreeDetails.size(); i++) {
													ArrayList subList = (ArrayList) agriTreeDetails.get(i);
													DeedDraftAppDTO ddto = new DeedDraftAppDTO();
													ddto.setCommonAgriTreeCount((String) subList.get(1));
													if (language.equalsIgnoreCase("en")) {
														ddto.setCommonAgriTreeName((String) subList.get(0));
													} else {
														ddto.setCommonAgriTreeName((String) subList.get(0));
													}
													treeListPresent.add(ddto);
												}
												d.setAgriTreeStore(treeListPresent);
											} else {
												d.setAgriTreeChkd("N");
											}
											eForm.getDeedAppDTO().setAgriAreaDetailsList(agriDetails);
											eForm.getDeedAppDTO().setAgriPropDetailsList(agriPropDetails);
											eForm.getDeedAppDTO().setAgriTreeDetailsList(agriTreeDetails);
										}
										ArrayList clauseListTemp = bd.getClauseList(language, valId);
										ArrayList clauseList = new ArrayList();
										for (int clauseLength = 0; clauseLength < clauseListTemp.size(); clauseLength++) {
											ArrayList clauseDetail = (ArrayList) clauseListTemp.get(clauseLength);
											DeedDraftAppDTO deedClause = new DeedDraftAppDTO();
											deedClause.setClauseId((String) clauseDetail.get(0));
											if ("en".equals(language)) {
												deedClause.setClasueName((String) clauseDetail.get(0));
											} else {
												deedClause.setClasueName((String) clauseDetail.get(0));
											}
											clauseList.add(deedClause);
										}
										d.setClauseList(clauseList);
										a.add(d);
									}
									if (!propDetails.isEmpty()) {
										eForm.setTotMv(totMarketValue);
									}
									eForm.setPropList(a);
								} else if ("N".equals(propCheck)) {
									eForm.setIsProp("N");
								} else {
									eForm.setIsReg("");
									eForm.setIsValidate("0");
									if ("en".equals(language)) {
										eForm.setErrorMessage("Please add property details first");
									} else {
										eForm.setErrorMessage("कृपया पहले संपत्ति विवरण जोड़ें |");
									}
								}
								//Duty Details
								if (!propDetails.isEmpty() || ("N".equalsIgnoreCase(propCheck)) || ("Y".equalsIgnoreCase(propOptionalCheck))) {
									dutyDetails = (ArrayList) dutyDetails.get(0);
									deedAppDTO.setDutyTxnId((String) dutyDetails.get(0));
									deedAppDTO.setStampDuty((String) dutyDetails.get(1));
									deedAppDTO.setMunicipalDuty((String) dutyDetails.get(2));
									deedAppDTO.setJanpadDuty((String) dutyDetails.get(3));
									deedAppDTO.setUpkarDuty((String) dutyDetails.get(4));
									deedAppDTO.setRegistrationFee((String) dutyDetails.get(5));
									deedAppDTO.setTotalDuty((String) dutyDetails.get(6));
									deedAppDTO.setPayableRegFee((String) dutyDetails.get(7));
									deedAppDTO.setPayableDuty((String) dutyDetails.get(8));
									deedAppDTO.setExempDuty((String) dutyDetails.get(9));
									deedAppDTO.setExempRegFee((String) dutyDetails.get(10));
								}
								//Exemption details
								ArrayList rExemption = new ArrayList();
								ArrayList eExemption = new ArrayList();
								rExemption = bd.getExemption(regTxnId, "R");
								eExemption = bd.getExemption(regTxnId, "S");
								if (!rExemption.isEmpty()) {
									eForm.setIsRegExemp("R");
									int exempLen = rExemption.size();
									int i = 0;
									while (i < exempLen) {
										ArrayList ls = (ArrayList) rExemption.get(i);
										if ("en".equals(language)) {
											eForm.getRegExemption().add((String) ls.get(0));
										} else {
											eForm.getRegExemption().add((String) ls.get(1));
										}
										i++;
									}
								}
								if (!eExemption.isEmpty()) {
									eForm.setIsEstampExemp("S");
									int exempLen = eExemption.size();
									int i = 0;
									while (i < exempLen) {
										ArrayList ls = (ArrayList) eExemption.get(i);
										if ("en".equals(language)) {
											eForm.getStampExemption().add((String) ls.get(0));
										} else {
											eForm.getStampExemption().add((String) ls.get(1));
										}
										i++;
									}
								}
							}
						} else {
							eForm.setIsValidate("0");
							eForm.setIsProp("");
							eForm.setIsReg("");
							eForm.setIsSelected("Y");
							eForm.setIsEstampExemp("");
							eForm.setIsRegExemp("");
							eForm.getDeedDTO().setRegTxnId("");
							eForm.getDeedAppDTO().setProprtyId("");
							eForm.getDeedAppDTO().setPropType("");
							eForm.getDeedAppDTO().setDutyTxnId("");
							if ("en".equals(language)) {
								eForm.setErrorMessage("You can only create deed for the application that you have created");
							} else {
								eForm.setErrorMessage("आप केवल आपके द्वारा बनाए गए एप्लिकेशन के लिए डीड बना सकते हैं |");
							}
						}
					} else {
						eForm.setIsValidate("0");
						eForm.setIsProp("");
						eForm.setIsReg("");
						eForm.setIsSelected("Y");
						eForm.setIsEstampExemp("");
						eForm.setIsRegExemp("");
						eForm.getDeedDTO().setRegTxnId("");
						eForm.getDeedAppDTO().setProprtyId("");
						eForm.getDeedAppDTO().setPropType("");
						eForm.getDeedAppDTO().setDutyTxnId("");
						if ("en".equals(language)) {
							eForm.setErrorMessage("Please enter correct application number");
						} else {
							eForm.setErrorMessage("कृपया सही एप्लीकेशन संख्या लिखें ");
						}
					}
				} else {
					eForm.setIsValidate("0");
					eForm.setIsProp("");
					eForm.setIsReg("");
					eForm.setIsSelected("Y");
					eForm.setIsEstampExemp("");
					eForm.setIsRegExemp("");
					eForm.getDeedDTO().setRegTxnId("");
					eForm.getDeedAppDTO().setProprtyId("");
					eForm.getDeedAppDTO().setPropType("");
					eForm.getDeedAppDTO().setDutyTxnId("");
					if ("en".equals(language)) {
						eForm.setErrorMessage("Please enter correct application number");
					} else {
						eForm.setErrorMessage("कृपया सही एप्लीकेशन संख्या लिखें ");
					}
				}
			} else if (actionName.equalsIgnoreCase("appCheck")) {
				if (eForm.getAppTypeSelect().equals("1")) {
					eForm.setIsValidate("1");
					eForm.setIsProp("");
					eForm.setIsReg("N");
					eForm.setIsSelected("Y");
					eForm.setIsEstampExemp("");
					eForm.setIsRegExemp("");
					eForm.getDeedDTO().setRegTxnId("");
					eForm.getDeedAppDTO().setProprtyId("");
					eForm.getDeedAppDTO().setPropType("");
					eForm.getDeedAppDTO().setDutyTxnId("");
				} else if (eForm.getAppTypeSelect().equals("2")) {
					eForm.setIsValidate("0");
					eForm.setIsProp("");
					eForm.setIsReg("Y");
					eForm.setIsEstampExemp("");
					eForm.setIsRegExemp("");
					eForm.setIsSelected("Y");
					eForm.getDeedAppDTO().setDutyTxnId("");
				}
			} else if (actionName.equalsIgnoreCase(DeedDraftConstant.SAVE_NEXT_ACTION)) {
				String appType = "E";
				if ("1".equals(eForm.getAppTypeSelect())) {
					appType = "E";
				} else {
					appType = "R";
				}
				String applicationId = "";
				if ("R".equals(appType)) {
					applicationId = formDTO.getRegTxnId();
					String adjuFlag = bd.getAdjudicationFlag(formDTO.getRegTxnId());
					if (adjuFlag != null && !("".equals(adjuFlag))) {
						appType = adjuFlag;
					}
				}
				boolean flag = bd.saveDeedDraftDetails(formDTO, userId, appType, applicationId);
				logger.debug("deedId after first page insertion--->" + formDTO.getDeedId());
				if (flag) {
					int pageId = Integer.parseInt(formDTO.getPageId());
					formDTO.setPageId(String.valueOf((pageId + 1)));
					if (bd.checkDeedRecordOnNext(formDTO.getDeedId(), formDTO.getPageId()))
						formDTO.setDeedContent(bd.getDeedContent(formDTO.getDeedId(), formDTO.getPageId()));
					else
						formDTO.setDeedContent("");
					forwardJsp = "deedDraftForm1";
				} else {
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Data has not been saved properly. Please try again");
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "डाटा समुचित रूप से नहीं डाला गया है। पुनः प्रयास करें।");
					forwardJsp = "deedDraftForm1";
				}
				if (formDTO.getPageId().equals("1"))
					formDTO.setFirstPageChk("Y");
				else
					formDTO.setFirstPageChk("N");
			}
			//addded by Anuj for print and upload deed
			else if (actionName.equalsIgnoreCase(DeedDraftConstant.FINISH_ACTION)) {
				/*boolean flag = false;
				if(formDTO.getDeedName()== null || formDTO.getDeedName().equals(""))
				{
					flag= true;
				}
				else
				{
					
					//formDTO.setFirstPageChk("N");
					flag = bd.saveDeedDraftDetails(formDTO, userId);
					
					forwardJsp = "printDeed1";
					
				}
				
				logger.debug("deedId after first page insertion--->"+formDTO.getDeedId());*/
				boolean flag = false;
				if (formDTO.getDeedName() == null || formDTO.getDeedName().equals("")) {
					flag = true;
				} else {
					String appType = "E";
					if ("1".equals(eForm.getAppTypeSelect())) {
						appType = "E";
					} else {
						appType = "R";
					}
					String applicationId = "";
					if ("R".equals(appType)) {
						applicationId = formDTO.getRegTxnId();
						String adjuFlag = bd.getAdjudicationFlag(formDTO.getRegTxnId());
						if (adjuFlag != null && !("".equals(adjuFlag))) {
							appType = adjuFlag;
						}
					}
					flag = bd.saveDeedDraftDetails(formDTO, userId, appType, applicationId);
				}
				logger.debug("deedId after first page insertion--->" + formDTO.getDeedId());
				if (!"Y".equalsIgnoreCase(formDTO.getCheckSign())) {
					if (flag) {
						flag = bd.updateDeedDraftStatus(formDTO.getDeedId());
						if (flag) {
							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.SUCCESS_MSG, "Deed has been drafted successfully");
							else
								request.setAttribute(DeedDraftConstant.SUCCESS_MSG, "विलेख सफलतापूर्वक प्रारूपित हो गया है।");
							forwardJsp = "confirmation";
						} else {
							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Data has not been saved properly. Please try again");
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "डाटा समुचित रूप से नहीं डाला गया है। पुनः प्रयास करें।");
							forwardJsp = "deedDraftForm1";
						}
					} else {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Data has not been saved properly. Please try again");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "डाटा समुचित रूप से नहीं डाला गया है। पुनः प्रयास करें।");
						forwardJsp = "deedDraftForm1";
					}
				} else {
					forwardJsp = "signOut";
				}
			}
			//commented by SIMRAN
			/*else if(actionName.equalsIgnoreCase("Print")){
				FormFile abc = formDTO.getDeedUploadPath();
				//PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				
				String path=pr.getValue("deed_draft_upload_location");
				
				String filePath = path+formDTO.getDeedId()+"/"+DeedDraftConstant.UPLOAD_NAME_SIGN_DOC;
				String ext = getFileExtension((String)abc.getFileName());
				if(abc!=null){
				if("pdf".equalsIgnoreCase(ext)){
					
					boolean ret = generatePDF2(filePath, abc.getFileData());
					if(ret){
						formDTO.setUpOrNot("1");	
					}
					else{
						formDTO.setUpOrNot("0");
					}
					}
				else if("jpg".equalsIgnoreCase(ext) || "jpeg".equalsIgnoreCase(ext) || "tiff".equalsIgnoreCase(ext)
						|| "bmp".equalsIgnoreCase(ext)){
					ArrayList aaaa = new ArrayList();
					aaaa.add(abc.getFileData());
					boolean ret=generatePDF(filePath, aaaa);
					if(ret){
						formDTO.setUpOrNot("1");
					}
					else{
						formDTO.setUpOrNot("0");
					}
				}
				else{
					formDTO.setUpOrNot("0");
				}
				}
				logger.debug("Uploaded successfu6lly");
				
				formDTO.setUploadCheck("0");
				forwardJsp = "printDeed1";
			}
			
			
			else if(actionName.equalsIgnoreCase("Final_upload"))
			{
				boolean flag = false;
				if(formDTO.getDeedName()== null || formDTO.getDeedName().equals(""))
				{
					flag= true;
				}
				else
				{
					flag = bd.saveDeedDraftDetails(formDTO, userId);
					bd.saveDeedDraftPath(formDTO, userId);
					
				}
				
				logger.debug("deedId after first page insertion--->"+formDTO.getDeedId());
				if(!"Y".equalsIgnoreCase(formDTO.getCheckSign())){
				if(flag)
				{
					if(language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.SUCCESS_MSG,"Deed has been drafted successfully");
					else
						request.setAttribute(DeedDraftConstant.SUCCESS_MSG,"डीड सफलतापूर्वक बना दी गयी है");
					forwardJsp = "confirmation";
				}
				else
				{
					if(language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG,"Data has not been saved properly. Please try again");
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG,"डेटा ठीक से दर्ज नहीं किया गया । पुनः प्रयास करें");
					forwardJsp = "deedDraftForm1";
				}
				}
				else{
					forwardJsp = "signOut";
				}
			}*/
			else if (actionName.equalsIgnoreCase(DeedDraftConstant.BACK_ACTION)) {
				if (formDTO.getSaveCheck().equalsIgnoreCase("Y")) {
					String appType = "E";
					if ("1".equals(eForm.getAppTypeSelect())) {
						appType = "E";
					} else {
						appType = "R";
					}
					String applicationId = "";
					if ("R".equals(appType)) {
						applicationId = formDTO.getRegTxnId();
						String adjuFlag = bd.getAdjudicationFlag(formDTO.getRegTxnId());
						if (adjuFlag != null && !("".equals(adjuFlag))) {
							appType = adjuFlag;
						}
					}
					boolean flag = bd.saveDeedDraftDetails(formDTO, userId, appType, applicationId);
				}
				int pageID = Integer.parseInt(formDTO.getPageId());
				formDTO.setPageId(String.valueOf(pageID - 1));
				if (formDTO.getPageId().equals("1"))
					formDTO.setFirstPageChk("Y");
				formDTO.setDeedContent(bd.getDeedContent(formDTO.getDeedId(), formDTO.getPageId()));
				forwardJsp = "deedDraftForm1";
			} else if (actionName.equalsIgnoreCase(DeedDraftConstant.VIEW_PDF)) {
				String validID = bd.checkID(formDTO.getDeedId(), userId);
				if (validID.equalsIgnoreCase("valid")) {
					String path = pr.getValue("pdf.location");
					byte[] propDetlsBytes = null; //added by ankit for prop val pdf 
					bd.generateDeedPDF(path, formDTO.getDeedId(), response, 0, propDetlsBytes); //change by ankit for prop val pdf 
					/*DeedDraftBD deedBD = new DeedDraftBD();
					
					byte arr[];
					String flags[] = commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));
					String propReq = flags[0];
					String propValReq = flags[1];
					String commonDeed = flags[2];
					  if (propReq.equalsIgnoreCase("Y")
								&& (propValReq.equalsIgnoreCase("Y") || propValReq.equalsIgnoreCase("N"))
								&& commonDeed.equalsIgnoreCase("N")) {
						  arr = deedBD.newDeedPDF(regForm,path, formDTO.getDeedId(), response, 0,language);
					  }else{
						  arr= deedBD.generateDeedPDFOLD(path, formDTO.getDeedId(), response, 0);  //changed by ankit for prop val pdf 
					  }*/
					//added by ankit for prop val pdf - end
				} else {
					if (validID.equalsIgnoreCase("invalid")) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "The Deed ID you entered is invaid. Please enter again");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "आपके द्वारा दर्ज की गई डीड आईडी अमान्य है । पुनः प्रयास करें");
					} else if (validID.equalsIgnoreCase("consumed")) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "The Deed ID you entered is already consumed hence you cannot view it");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "आपके द्वारा दर्ज की गई डीड आईडी का सेवन किया जा चुका है इसलिए आप उसे नहीं देख सकेंगे");
					} else if (validID.equalsIgnoreCase("incomplete")) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "The Deed ID you entered is not yet completed.");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "आपके द्वारा दर्ज की गई डीड आईडी अभी तक पूरी नहीं की गई है इसलिए आप उसे नहीं देख सकेंगे");
					}
					//formDTO.setValidDeed("Invalid");
				}
				formDTO.setActionName("");
				forwardJsp = "deedDraftPdfSerach";
			}
			String viewUpload = (String) request.getParameter("fwdName");
			if ("viewUpload".equalsIgnoreCase(viewUpload)) {
				try {
					String path = pr.getValue("deed_draft_upload_location");
					String filename = path + formDTO.getDeedId() + "/" + DeedDraftConstant.UPLOAD_NAME_SIGN_DOC;
					// set the http content type to "APPLICATION/OCTET-STREAM
					response.setContentType("application/download");
					String file = filename.substring(filename.lastIndexOf("/") + 1, filename.length());
					// initialize the http content-disposition header to
					// indicate a file attachment with the default filename
					// "myFile.txt"
					// String fileName = (String)formDTO.getCompThumbPath();
					//Filename=\"myFile.txt\"";
					response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(file, "UTF-8"));
					File fileToDownload = new File(filename);
					FileInputStream fileInputStream = new FileInputStream(fileToDownload);
					OutputStream out = response.getOutputStream();
					byte[] buf = new byte[2048];
					int readNum;
					while ((readNum = fileInputStream.read(buf)) != -1) {
						out.write(buf, 0, readNum);
					}
					fileInputStream.close();
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return mapping.findForward(forwardJsp);
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

	private boolean generatePDF2(String fileName, byte[] array) throws Exception {
		boolean ret = false;
		try {
			File output = new File(fileName);
			if (output.exists()) {
				logger.info("file already exists deleting....");
				output.delete();
			}
			if (output.getParentFile().exists() == false) {
				logger.info("Parent not found creating directory....");
				output.getParentFile().mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(output);
			fos.write(array);
			fos.close();
			ret = true;
		} catch (Exception e) {
		}
		return ret;
	}

	private boolean generatePDF(String fileName, ArrayList<byte[]> arrays) throws Exception {
		boolean ret = false;
		logger.info("Entering method generatePDF");
		try {
			File output = new File(fileName);
			if (output.exists()) {
				logger.info("file already exists deleting....");
				output.delete();
			}
			if (output.getParentFile().exists() == false) {
				logger.info("Parent not found creating directory....");
				output.getParentFile().mkdirs();
			}
			Image pdfImage;
			logger.info("No. of arrays : " + arrays.size());
			Document document = new Document(PageSize.A4, 20, 20, 20, 20);
			OutputStream fos = new FileOutputStream(fileName, false);
			PdfWriter.getInstance(document, fos);
			document.open();
			for (byte[] bytes : arrays) {
				pdfImage = Image.getInstance(bytes);
				pdfImage.scalePercent(25.0f);
				document.newPage();
				document.add(pdfImage);
			}
			document.close();
			ret = true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("Leaving method generatePDF");
		return ret;
	}
}
