package com.wipro.igrs.newPropvaluationefiling.action;

/**
 * ===========================================================================
 * File           :   PropertyValuationAction.java
 * Description    :   Represents the Action Class for property Valuation Module

 * Author         :   Vinay Sharma,Satbir Kumar,ShreeRaj Khare
 * Created Date   :   Mar 12, 2014
 * ===========================================================================
 */
import java.io.IOException;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.common.form.CommonForm;
import com.wipro.igrs.newPropvaluationefiling.bo.PropertyValuationBO;
import com.wipro.igrs.newPropvaluationefiling.constant.PropertyValuationConstant;
import com.wipro.igrs.newPropvaluationefiling.dto.KhasraClrDTO;
import com.wipro.igrs.newPropvaluationefiling.dto.PropertyValuationDTO;
import com.wipro.igrs.newPropvaluationefiling.dto.SampadaKhasraClrDTO;
import com.wipro.igrs.newPropvaluationefiling.dto.TreeDTO;
import com.wipro.igrs.newPropvaluationefiling.form.PropertyValuationForm;
import com.wipro.igrs.newreginitefiling.form.RegCommonForm;

public class PropertyValuationAction extends BaseAction {
	private static Logger logger = org.apache.log4j.Logger.getLogger(PropertyValuationAction.class);
	private static Proxy proxy;
	String FORWARD_JSP = "";

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		/*
		 * logger.debug("pageName="+request.getParameter("pageName"));
		 * logger.debug("modName="+request.getParameter("modName"));
		 * logger.debug("fnName="+request.getParameter("fnName"));
		 * logger.debug("funId="+request.getParameter("funId"));
		 * logger.debug("fromModule="+request.getParameter("fromModule"));
		 */

		PropertyValuationForm propForm = (PropertyValuationForm) form;
		PropertyValuationBO propBO = new PropertyValuationBO();
		PropertyValuationDTO propDTO = propForm.getPropDTO();
		
		KhasraClrDTO clrDto = new KhasraClrDTO();
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		CommonForm commonForm = new CommonForm();
		String language = (String) session.getAttribute("languageLocale");
		String regTxnId = (String) session.getAttribute("regTxnId");
		String frwdName = request.getParameter("fwdName");
		// added by vinay Sharma
		String districtID = request.getParameter("districtID");
		String instId = request.getParameter("instId");
		if (instId != null && instId.trim() != "") {
			propDTO.setInstId(instId);
		}

		String userId = (String) session.getAttribute("UserId");

		String[] plotSId = (String[]) request.getParameterValues("plotSubId");
		String id = (String) request.getParameter("id");

		String idA = (String) request.getAttribute("id");

		String pageName = request.getParameter("pageName");
		String fromModule = request.getParameter("fromModule");
		String deedId = (String) request.getAttribute("deedId1");
		int instrumentId = 0;
		if (request.getAttribute("instId") != null) {
			instrumentId = Integer.parseInt(String.valueOf(request.getAttribute("instId")));

		}
		String treeCategoryId = null;
		treeCategoryId = propDTO.getAgriTreeTypeId();
		propDTO.setUserId(userId);
		/*
		 * if(pageName.equals(null) && fromModule!=null &&
		 * fromModule.equalsIgnoreCase("dutycalc")){
		 * propDTO.setFromModule(fromModule); propDTO.setAgriMainValTxnId("");
		 * FORWARD_JSP="MAP"; }else
		 */
		if (fromModule != null) {
			if (fromModule.equalsIgnoreCase("dutycalc") || fromModule.equalsIgnoreCase("regInit") || fromModule.equalsIgnoreCase("regInitNonPV") || fromModule.equalsIgnoreCase("dutyCalcefiling")) {
				propDTO.setClrFlag("");
				propDTO.setDeedId(deedId);
				propDTO.setInstId(String.valueOf(instrumentId));
				if ("Y".equalsIgnoreCase((String) request.getAttribute("rinPustika"))) {
					propDTO.setFromAgri("YES");
					propDTO.setPropertyId("3");
					propDTO.setPropertyName("3");

				} else {
					propDTO.setFromAgri("");
					propDTO.setPropertyId("");
				}
				if ("Y".equalsIgnoreCase((String) request.getAttribute("singleBuyer"))) {
					propDTO.setFreezeBuyer("Y");
					propDTO.setAreMoreBuyers("nobuyers");
				} else {
					propDTO.setFreezeBuyer("N");
					propDTO.setAreMoreBuyers("");
				}
				propForm.setActionName("");

				propDTO.setAgriLandTypeId("");
				propDTO.setBuildingTypeId("");
				propDTO.setMultiStoreyTypeId("");
				propDTO.setFromModule(fromModule);
				propDTO.setAgriMainValTxnId("");
				propDTO.setIsMultipleProperty(1);
				propDTO.setAllAreaZero(""); // added to enable checkbox to add
				// second property.
				if (fromModule.equalsIgnoreCase("regInit") || fromModule.equalsIgnoreCase("regInitNonPV")) {
					propDTO.setIsMultipleProperty(1);
				}
				propDTO.setAgriNoOfBuyersCheck(1);
				FORWARD_JSP = "MAP";

				if (fromModule.equalsIgnoreCase("regInitNonPV")) {
					RegCommonForm regInitForm;
					if (request.getAttribute("regInitForm") != null) {

						regInitForm = (RegCommonForm) request.getAttribute("regInitForm");

						propForm.setRegInitForm(regInitForm);
					} else {
						// regInitForm=(RegCommonForm)session.getAttribute(
						// "regInitForm");
						regInitForm = propForm.getRegInitForm();
					}

					if (request.getAttribute("multiProp") != null) {

						if (request.getAttribute("multiProp").toString().equalsIgnoreCase("Y")) {

							propForm.setRegMultiProp("Y");

						} else {

							propForm.setRegMultiProp("N");
						}

					}

				}

			}
		} else {
			if (propDTO.getFromModule() != null) {

			} else {
				propDTO.setFromModule("");
			}
		}
		if (pageName != null) {
			if (pageName.equalsIgnoreCase("valuationHome")) {
				propDTO.setAgriMainValTxnId("");
				propDTO.setFromAgri("");
				propDTO.setPropertyId("");
				propDTO.setIsMultipleProperty(0);
				propDTO.setFreezeBuyer("");
				propDTO.setFromModule("");
				propDTO.setDeedId("");
				propDTO.setInstId("");
				propDTO.setAgriNoOfBuyersCheck(1);
				session.setAttribute("rinPustikaArrayClr", null);
				session.setAttribute("khasraMapClr", null);
				FORWARD_JSP = "MAP";
			}
			if (pageName.equalsIgnoreCase("valuationHome1")) {
				propDTO.setPropertyId("");
				propDTO.setAgriNoOfBuyersCheck(1);
				FORWARD_JSP = "MAP";
			}
		} else if (districtID != null && !districtID.equalsIgnoreCase("")) {

			propDTO.setTehsilId("");
			propDTO.setIsConstruction("");
			propDTO.setMunicipalCheckDisplay("");
			propDTO.setMunicipalFlag("");
			propDTO.setMunicipalCheckFlag("");
			propDTO.setMunicipalCheck("");
			propDTO.setTehsilName("");
			propDTO.setFloorName("");
			propDTO.setIsLift("");
			propDTO.setIsOpenTerraceFlag("");
			propDTO.setIsOpenTerrace("");
			propDTO.setIsOlder("");
			propDTO.setOlder("");
			propDTO.setIsOlderFlag("");
			propDTO.setColonyList(null);
			propDTO.setDistrictId(districtID);
			propDTO.setAreaList(null);
			propDTO.setSubAreaList(null);
			propDTO.setWardPatwariList(null);
			if (!"YES".equalsIgnoreCase(propDTO.getFromAgri())) {
				propDTO.setPropertyTypeList(null);
				propDTO.setPropertyName("");
			}

			propDTO.setAgriAddPropButtonCheck(0);
			propDTO.setAgriSelectedSubclauseId("");

			propDTO.setPlotValueFinal("");
			propDTO.setConstructionCostFinal("0.0");
			propDTO.setDistrictName(propBO.getDistrictName(language, districtID));
			propDTO.setPropertyTypeList(propBO.getPropertyType(language, propDTO.getInstId()));
			propDTO.setTehsilList(propBO.getTehsil(language, districtID));
			propDTO.setClrFlag("");
			propDTO.setClrFlag(propBO.getDistrictClrFlag(language, districtID));
			FORWARD_JSP = "AREASELECT";

		} else if (frwdName != null && (frwdName.equalsIgnoreCase("area"))) {

			propDTO.setAreaList(propBO.getArea2(language, propDTO.getInstId()));// getArea2
			// used
			// by
			// roopam
			// for
			// partition
			// of
			// agri
			// land
			// -
			// 14
			// april
			// 15

			FORWARD_JSP = "AREASELECT";
		} else if (frwdName != null && (frwdName.equalsIgnoreCase("subArea"))) {
			if ("2".equalsIgnoreCase(propDTO.getAreaId())) {
				propDTO.setSubAreaList(propBO.getSubArea(language, propDTO.getAreaId(), propDTO.getTehsilId(), "Y"));
			} else {
				propDTO.setSubAreaList(propBO.getSubArea(language, propDTO.getAreaId(), propDTO.getTehsilId(), "N"));

			}
			FORWARD_JSP = "AREASELECT";
		} else if (frwdName != null && (frwdName.equalsIgnoreCase("wardPatwari"))) {
			String municipalFlag = propBO.getMuncipalFlag(propDTO.getSubAreaId());
			if ("RF".equalsIgnoreCase(municipalFlag)) {
				propDTO.setMunicipalCheckDisplay("Y");
				propDTO.setMunicipalFlag("");
			} else if ("NF".equalsIgnoreCase(municipalFlag)) {
				propDTO.setMunicipalCheckDisplay("N");
				propDTO.setMunicipalFlag("Y");
			} else {
				propDTO.setMunicipalCheckDisplay("N");
				propDTO.setMunicipalFlag("N");
			}
			propDTO.setWardPatwariList(propBO.getWardPatwari(language, propDTO.getSubAreaId(), propDTO.getTehsilId()));
			FORWARD_JSP = "AREASELECT";
		} else if (frwdName != null && (frwdName.equalsIgnoreCase("colony"))) {

			propDTO.setColonyList(propBO.getColonyName2(language, propDTO.getWardPatwariId(), propDTO.getInstId()));// getColonyName2
			// used by
			// roopam for
			// partition of
			// agri land- 14
			// april 15

			FORWARD_JSP = "AREASELECT";
		} else if (propForm.getActionName().equalsIgnoreCase("municipalChecked")) {
			if ("Y".equalsIgnoreCase(propDTO.getMunicipalCheckFlag())) {
				propDTO.setMunicipalCheck("Y");
			} else {
				propDTO.setMunicipalCheck("N");

			}
			FORWARD_JSP = "AREASELECT";
		}
		// addition end
		else if (propForm.getActionName().equalsIgnoreCase("submitPropAreaDetails") && propDTO.getPropertyId().equalsIgnoreCase("1")) {

			// added by ShreeraJ
			ArrayList plotSubclause = null;
			propForm.getPropDTO().setPlotCommArea("");
			propForm.getPropDTO().setPlotCost("");
			// propForm.getPropDTO().setPlotEduTcp("");
			propForm.getPropDTO().setPlotHealthArea("");
			// propForm.getPropDTO().setPlotHealthTcp("");
			propForm.getPropDTO().setPlotIndusArea("");
			propForm.setPlotEduTcp(null);
			propForm.setPlotHealthTcp(null);
			propForm.getPropDTO().setPlotMarketValue("");
			propForm.getPropDTO().setPlotOtherArea("");
			propForm.getPropDTO().setPlotResiArea("");
			propForm.getPropDTO().setPlotSchoolArea("");
			propForm.getPropDTO().setPlotSubId("");
			propForm.getPropDTO().setPlotTotArea("");
			propForm.getPropDTO().setPlotValuationId("");
			propForm.getPropDTO().setPlotResicumComm("");
			propForm.getPropDTO().setRadioResiComm("");
			plotSubclause = propBO.getSubclauseData(propForm.getPropDTO(), language);
			propForm.getPropDTO().setPlotSubClauseList(plotSubclause);
			propForm.propDTO.setIsConstructionKhasraNo("");

			FORWARD_JSP = "plot";
		} else if (propForm.getActionName().equalsIgnoreCase("plotPrevious")) {
			// added by ShreeraJ

			FORWARD_JSP = "AREASELECT";
		} else if (propForm.getActionName().equalsIgnoreCase("propMap")) {
			// added by ShreeraJ
			propForm.getPropDTO().setPropertyId(null);
			propForm.getPropDTO().setTehsilId("");

			FORWARD_JSP = "MAP";
		} else if (propForm.getActionName().equalsIgnoreCase("submitPlotMarket")) {
			float area = Float.parseFloat(propForm.getPropDTO().getPlotTotArea());
			session.setAttribute("area", area);
			if (area > 40) {
				String subId = propForm.getPropDTO().getPlotSubId();

				if (subId.equalsIgnoreCase("~6")) {
					propForm.getPropDTO().setPlotSubId("");
				} else {
					if (subId.contains("~6")) {
						String sId[] = subId.split("~6");
						if (subId.charAt(subId.length() - 1) == '6') {
							propForm.getPropDTO().setPlotSubId(sId[0]);

						} else {
							propForm.getPropDTO().setPlotSubId(sId[0].concat(sId[1]));
						}
					}
				}
			}
			String valId = propBO.insertPlot(propForm.getPropDTO(), propForm.getPlotEduTcp(), propForm.getPlotHealthTcp());
			String guideLineId = propBO.getGuideLineId(propForm.getPropDTO());
			String marketValue[] = propBO.getPlotMarketValue(propForm.getPropDTO(), propForm.getPlotEduTcp(), propForm.getPlotHealthTcp());
			propForm.getPropDTO().setGuideLineId(guideLineId);

			propForm.getPropDTO().setPlotMarketValue(marketValue[1]);
			propForm.getPropDTO().setValuationId(valId);
			propBO.insertSubclauseValues(propForm.getPropDTO());
			propForm.getPropDTO().setGuideLineValue(marketValue[0]);
			propBO.insertFinalPlot(propForm.getPropDTO());
			FORWARD_JSP = "finalMarketValue";
		}
		// end
		else if (propForm.getActionName().equalsIgnoreCase("submitPropAreaDetails") && propDTO.getPropertyId().equalsIgnoreCase("2")) {
			propDTO.setFloorName("");
			propDTO.setIsLift("");
			propDTO.setIsOpenTerraceFlag("");
			propDTO.setIsOpenTerrace("");
			propDTO.setIsOlder("");
			propDTO.setOlder("");
			propDTO.setIsOlderFlag("");
			propDTO.setBuildingTypeId("");
			propDTO.setPlotValueFinal("");
			propDTO.setConstructionCostFinal("0.0");
			propDTO.setBuildingTypeList(propBO.getBuildingType(language));
			propDTO.setIsConstructionKhasraNo("");
			FORWARD_JSP = "BUILDINGTYPEPAGE";

		}

		// Added by Rakesh Soni for Previous Button functionality for
		// SearchKhasra Page
		else if (propForm.getActionName().equalsIgnoreCase("SEARCHKHASRAJSP")) {
			// Undiverted data clear
			propForm.propDTO.setAgriTotalUnDivertedArea("");
			propForm.propDTO.setAgriUndivSingleCropArea("");
			propForm.propDTO.setAgriUndivDoubleCropArea("");
			propForm.propDTO.setAgriUndivIrrigatedArea("");
			propForm.propDTO.setKhasraNoClr("");
			// Diverted data clear
			propForm.propDTO.setAgriTotalDivertedResidentialArea("");
			propForm.propDTO.setAgriTotalDivertedCommercialArea("");
			propForm.propDTO.setAgriTotalDivertedEducationalArea("");
			propForm.propDTO.setAgriTotalDivertedHealthArea("");
			propForm.propDTO.setAgriTotalDivertedIndustrialArea("");
			propForm.propDTO.setAgriTotalDivertedOthersArea("");
			propForm.propDTO.setAgriTotalDivertedArea("");
			propForm.setSearchDisplay("1");
			propForm.setClrData("0");
			propDTO.setIsConstructionKhasraNo("");
			FORWARD_JSP = "SEARCHKHASRAJSP";
		}

		// ---added by satbir kumar for Agri-land
		else if (propForm.getActionName().equalsIgnoreCase("submitPropAreaDetails") && propDTO.getPropertyId().equalsIgnoreCase("3")) {

			// Added by Rakesh
			// CLR data clear
			propForm.propDTO.setClrTotalKhasraArea(0.0);
			propForm.propDTO.setClrTotAgriIrrigated(0.0);
			propForm.propDTO.setClrTotAgriUnirrigated(0.0);
			propForm.propDTO.setClrTotAgriSingleCropArea(0.0);
			propForm.propDTO.setClrTotAgriDoubleCropArea(0.0);

			// Undiverted data clear
			propForm.propDTO.setAgriTotalUnDivertedArea("");
			propForm.propDTO.setAgriUndivSingleCropArea("");
			propForm.propDTO.setAgriUndivDoubleCropArea("");
			propForm.propDTO.setAgriUndivIrrigatedArea("");
			propForm.propDTO.setKhasraNoClr("");

			propDTO.setTotAgriTotalUnDivertedArea(0.0);
			propDTO.setTotAgriUndivIrrigatedArea(0.0);
			propDTO.setTotAgriUndivDoubleCropArea(0.0);
			propDTO.setTotAgriUndivSingleCropArea(0.0);
			// Diverted data clear
			propForm.propDTO.setAgriTotalDivertedResidentialArea("");
			propForm.propDTO.setAgriTotalDivertedCommercialArea("");
			propForm.propDTO.setAgriTotalDivertedEducationalArea("");
			propForm.propDTO.setAgriTotalDivertedHealthArea("");
			propForm.propDTO.setAgriTotalDivertedIndustrialArea("");
			propForm.propDTO.setAgriTotalDivertedOthersArea("");
			propForm.propDTO.setAgriTotalDivertedArea("");

			propDTO.setTotDivertedCommercialArea(0.0);
			propDTO.setTotDivertedEducationalArea(0.0);
			propDTO.setTotDivertedHealthArea(0.0);
			propDTO.setTotDivertedIndustrialArea(0.0);
			propDTO.setTotDivertedOthersArea(0.0);
			propDTO.setTotDivertedResidentialArea(0.0);
			propDTO.setTotDivertedArea(0.0);

			propDTO.setRinPustikaNoClr("");
			propForm.getClrKhasraList().clear();
			propForm.getUniKhasraArray().clear();
			propForm.getSampadaClrKhasraList().clear();
			// Added by Rakesh
			propDTO.setAgriLandTypeList(propBO.getAgriLandType(language, propDTO.getDeedId(), propDTO.getInstId()));
			propDTO.setAgriWardPatwariId(propDTO.getWardPatwariId().split("~")[0]);
			propDTO.setAgriSubAreaWardMapId(propDTO.getWardPatwariId().split("~")[1]);
			propDTO.setAgriColonyid(propDTO.getColonyId().split("~")[0]);
			propDTO.setAgriColonyWardMapId(propDTO.getColonyId().split("~")[1]);
			propDTO.setAgriApplicableSubclauseId(propDTO.getColonyId().split("~")[2]);

			propDTO.setClrFlag(propBO.getTehsilClrFlag(language, propDTO.getTehsilId()));
			if (propDTO.getClrFlag() != null && !propDTO.getClrFlag().isEmpty()) {
				if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {

					String censusCode = (propBO.censusId(propDTO.getAgriColonyid()));
					if (censusCode != null && !censusCode.isEmpty()) {
						//propDTO.setCensusCode(Double.parseDouble(censusCode));
						propDTO.setCensusCode(censusCode);
					} else {
						propDTO.setClrFlag("");
					}
				}
			}
			propDTO.setNoOfBuyers("");
			propDTO.setFamily("");
			propDTO.setAreMoreBuyers("");
			propDTO.setAgriShowBuyerCheck("Show");
			propDTO.setAgriAddPropButtonCheck(0);
			propForm.setActionName("");
			propDTO.setAgriNoConstruction("");
			propDTO.setIsConstruction("");
			propDTO.setBuildingTxnIdAgri("");
			propDTO.setConstructedValueBuilding("0.0");
			propDTO.setConstructedAreaBuilding("0.0");
			propDTO.setAgriTotalUnDivertedArea("");
			propDTO.setAgriUndivSingleCropArea("");
			propDTO.setAgriUndivDoubleCropArea("");
			propDTO.setAgriUndivIrrigatedArea("");
			propDTO.setAgriTotalDivertedArea("");
			propDTO.setAgriTotalDivertedResidentialArea("");
			propDTO.setAgriTotalDivertedCommercialArea("");
			propDTO.setAgriTotalDivertedIndustrialArea("");
			propDTO.setAgriTotalDivertedEducationalArea("");
			propDTO.setAgriTotalDivertedHealthArea("");
			propDTO.setAgriTotalDivertedOthersArea("");
			propDTO.setIsPropDiffPustika("");
			propDTO.setAgriDiscloseShare("");
			propDTO.setIsAgriTncpEducational("");
			propDTO.setIsAgriTncpHealth("");
			propForm.propDTO.setIsConstructionKhasraNo("");

			FORWARD_JSP = "AGRILAND";
		}

		else if (propForm.getActionName().equalsIgnoreCase("noConstructionUndiv") && propDTO.getAgriLandTypeId().equalsIgnoreCase("5")) {

			propDTO.setBuildingTxnIdAgri("");
			propDTO.setConstructedValueBuilding("0.0");
			propDTO.setConstructedAreaBuilding("0.0");
			propDTO.setIsConstruction("noconstruct");
			propDTO.setConstructionCostFinal("0");
			propDTO.setPlotValueFinal("0");
			propDTO.setAgriBuildingIndivConstValue("0");
			propDTO.setAgriBuildingPlotValue("0");
			propForm.setActionName("");
			if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {

				FORWARD_JSP = "AGRILANDUNDIVERTEDCLR";
			} else {
				FORWARD_JSP = "AGRILANDUNDIVERTED";
			}
		}

		else if (propForm.getActionName().equalsIgnoreCase("noConstructionDiv") && propDTO.getAgriLandTypeId().equalsIgnoreCase("6")) {

			propDTO.setBuildingTxnIdAgri("");
			propDTO.setConstructedValueBuilding("0.0");
			propDTO.setConstructedAreaBuilding("0.0");
			propDTO.setIsConstruction("noconstruct");
			propDTO.setConstructionCostFinal("0");
			propDTO.setPlotValueFinal("0");
			propForm.setActionName("");
			if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {

				FORWARD_JSP = "AGRILANDDIVERTEDCLR";
			} else {
				FORWARD_JSP = "AGRILANDDIVERTED";

			}
		}

		else if (propForm.getActionName().equalsIgnoreCase("noConstructionBoth") && propDTO.getAgriLandTypeId().equalsIgnoreCase("7")) {

			propDTO.setBuildingTxnIdAgri("");
			propDTO.setConstructedValueBuilding("0.0");
			propDTO.setConstructedAreaBuilding("0.0");
			propDTO.setIsConstruction("noconstruct");
			propDTO.setConstructionCostFinal("0");
			propDTO.setPlotValueFinal("0");
			propForm.setActionName("");

			if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {

				FORWARD_JSP = "AGRIBOTHCLR";
			} else {
				FORWARD_JSP = "AGRIBOTH";

			}
		}

		else if (propForm.getActionName().equalsIgnoreCase("agriLandTypeSubmit") && propDTO.getAgriLandTypeId().equalsIgnoreCase("5")) {

			propForm.setSearchDisplay("1");
			propForm.setClrData("0");
			propDTO.setKhasraNoClr("");
			propForm.setClrDto(new KhasraClrDTO());

			if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {

				if (propDTO.getAreMoreBuyers().equalsIgnoreCase("morebuyers") && (!propDTO.getFamily().equalsIgnoreCase("samefamily"))) {

					propDTO.setAgriDiscloseShare("unopenshare");
				}

			}

			if (propDTO.getAreMoreBuyers().equalsIgnoreCase("nobuyers")) {

				propDTO.setAgriSubTreeTypeGrt45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setAgriSubTreeTypeLes45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setIsPropDiffPustika("");
				propDTO.setIsConstruction("");
				propDTO.setAreThereTrees("");
				propDTO.setAgriTreeTypeList(propBO.getAgriTreeType(language));
				propDTO.setAgriSubClauseList((propBO.getAgriSubClauseList(language, propDTO)));
				propDTO.setAgriAddPropButtonCheck(1);
				propForm.setActionName("");
				/*
				 * if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {
				 * 
				 * FORWARD_JSP = "AGRILANDUNDIVERTEDCLR"; } else { FORWARD_JSP =
				 * "AGRILANDUNDIVERTED"; }
				 */

				if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {

					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else {
					FORWARD_JSP = "AGRILANDUNDIVERTED";
				}
			} else if (propDTO.getAreMoreBuyers().equalsIgnoreCase("morebuyers") && (propDTO.getFamily().equalsIgnoreCase("samefamily"))) {

				propDTO.setAgriSubTreeTypeGrt45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setAgriSubTreeTypeLes45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setIsPropDiffPustika("");
				propDTO.setAreThereTrees("");
				propDTO.setIsConstruction("");
				propDTO.setAgriTreeTypeList(propBO.getAgriTreeType(language));
				propDTO.setAgriSubClauseList((propBO.getAgriSubClauseList(language, propDTO)));
				propDTO.setAgriAddPropButtonCheck(1);
				propForm.setActionName("");

				if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {

					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else {
					FORWARD_JSP = "AGRILANDUNDIVERTED";
				}

			} else if (propDTO.getAreMoreBuyers().equalsIgnoreCase("morebuyers") && propDTO.getAgriDiscloseShare().equalsIgnoreCase("openshare")) {
				propDTO.setAgriSubTreeTypeGrt45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setAgriSubTreeTypeLes45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setIsPropDiffPustika("");
				propDTO.setAreThereTrees("");
				propDTO.setIsConstruction("");
				// propDTO.setAgriAddPropButtonCheck(0);
				propDTO.setAgriTreeTypeList(propBO.getAgriTreeType(language));
				propDTO.setAgriSubClauseList((propBO.getAgriSubClauseList(language, propDTO)));
				propForm.setActionName("");
				if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {

					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else {
					FORWARD_JSP = "AGRILANDUNDIVERTED";
				}
			} else {
				propDTO.setAgriSubTreeTypeGrt45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setAgriSubTreeTypeLes45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setIsPropDiffPustika("");
				propDTO.setAreThereTrees("");
				propDTO.setIsConstruction("");
				propDTO.setAgriAddPropButtonCheck(1);
				propDTO.setAgriTreeTypeList(propBO.getAgriTreeType(language));
				propDTO.setAgriSubClauseList((propBO.getAgriSubClauseList(language, propDTO)));
				propForm.setActionName("");
				if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {

					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else {
					FORWARD_JSP = "AGRILANDUNDIVERTED";
				}
			}

			// Added by Rakesh Soni for clr rinPustika uniqueNess: Start
			ArrayList rinPustikaArrayClr = null;

			if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {
				if (!"regInitNonPV".equalsIgnoreCase(propDTO.getFromModule())) {

					rinPustikaArrayClr = (ArrayList) session.getAttribute("rinPustikaArrayClr");
					if (rinPustikaArrayClr != null && !rinPustikaArrayClr.isEmpty()) {
						if (rinPustikaArrayClr.contains(propDTO.getRinPustikaNoClr())) {

							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERROR", "RIN pustika number is already part of another property in the ongoing transaction. Please input new RIN pustika number.");
							} else {
								request.setAttribute("ERROR", "à¤‹à¤£  à¤ªà¥�à¤¸à¥�à¤¤à¤¿à¤•à¤¾ à¤¸à¤‚à¤–à¥�à¤¯à¤¾ à¤ªà¤¹à¤²à¥‡ à¤¸à¥‡ à¤šà¤² à¤°à¤¹à¥€ à¤²à¥‡à¤¨à¤¦à¥‡à¤¨ à¤®à¥‡à¤‚ à¤¦à¥‚à¤¸à¤°à¥€ à¤¸à¤‚à¤ªà¤¤à¥�à¤¤à¤¿ à¤•à¤¾ à¤¹à¤¿à¤¸à¥�à¤¸à¤¾ à¤¹à¥ˆà¥¤ à¤•à¥ƒà¤ªà¤¯à¤¾ à¤¨à¤�  à¤‹à¤£ à¤ªà¥�à¤¸à¥�à¤¤à¤¿à¤•à¤¾ à¤¸à¤‚à¤–à¥�à¤¯à¤¾ à¤•à¥‹ à¤‡à¤¨à¤ªà¥�à¤Ÿ à¤•à¤°à¥‡à¤‚à¥¤");

							}
							FORWARD_JSP = "AGRILAND";
							propForm.getActionName().equalsIgnoreCase("");

						}

						/*
						 * else{ propDTO.getRinPustikaArrayClr().clear(); }
						 */

						return mapping.findForward(FORWARD_JSP);
					}

				} else if (propForm.getRegInitForm().getPvReqFlag().equalsIgnoreCase("N") && propForm.getRegInitForm().getPropReqFlag().equalsIgnoreCase("y")) {
					if (propForm.getRegInitForm().getHidnRegTxnId() != null && !propForm.getRegInitForm().getHidnRegTxnId().isEmpty()) {
						ArrayList rinPustika = new ArrayList();
						rinPustika = (ArrayList) propBO.getRinPustikaNo(propForm.getRegInitForm().getHidnRegTxnId());
						ArrayList rinPustikaList = new ArrayList();
						for (int i = 0; i < rinPustika.size(); i++) {
							ArrayList temp = (ArrayList) rinPustika.get(i);
							rinPustikaList.add(temp.get(0));
						}
						if (rinPustikaList.contains(propDTO.getRinPustikaNoClr())) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERROR", "RIN pustika number is already part of another property in the ongoing transaction. Please input new RIN pustika number.");
							} else {
								request.setAttribute("ERROR", "à¤‹à¤£ à¤ªà¥�à¤¸à¥�à¤¤à¤¿à¤•à¤¾ à¤¸à¤‚à¤–à¥�à¤¯à¤¾ à¤ªà¤¹à¤²à¥‡ à¤¸à¥‡ à¤šà¤² à¤°à¤¹à¥€ à¤²à¥‡à¤¨à¤¦à¥‡à¤¨ à¤®à¥‡à¤‚ à¤¦à¥‚à¤¸à¤°à¥€ à¤¸à¤‚à¤ªà¤¤à¥�à¤¤à¤¿ à¤•à¤¾ à¤¹à¤¿à¤¸à¥�à¤¸à¤¾ à¤¹à¥ˆà¥¤ à¤•à¥ƒà¤ªà¤¯à¤¾ à¤¨à¤� à¤‹à¤£  à¤ªà¥�à¤¸à¥�à¤¤à¤¿à¤•à¤¾ à¤¸à¤‚à¤–à¥�à¤¯à¤¾ à¤•à¥‹ à¤‡à¤¨à¤ªà¥�à¤Ÿ à¤•à¤°à¥‡à¤‚à¥¤");

							}
							FORWARD_JSP = "AGRILAND";
							propForm.getActionName().equalsIgnoreCase("");

							return mapping.findForward(FORWARD_JSP);
						}

					}

				}
			}
			// Added by Rakesh Soni for clr rinPustika uniqueNess: End

		} else if (propForm.getActionName().equalsIgnoreCase("agriLandTypeSubmit") && propDTO.getAgriLandTypeId().equalsIgnoreCase("6")) {

			propForm.setSearchDisplay("1");
			propForm.setClrData("0");
			propDTO.setKhasraNoClr("");
			propForm.setClrDto(new KhasraClrDTO());

			if (propDTO.getAreMoreBuyers().equalsIgnoreCase("nobuyers")) {
				propDTO.setAgriSubTreeTypeGrt45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setAgriSubTreeTypeLes45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setIsPropDiffPustika("");
				propDTO.setAreThereTrees("");
				propDTO.setIsConstruction("");
				propDTO.setAgriAddPropButtonCheck(1);
				propDTO.setAgriTreeTypeList(propBO.getAgriTreeType(language));
				propDTO.setAgriSubClauseList((propBO.getAgriSubClauseList(language, propDTO)));
				propForm.setActionName("");

				if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {

					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else {
					FORWARD_JSP = "AGRILANDDIVERTED";
				}
			} else if (propDTO.getAreMoreBuyers().equalsIgnoreCase("morebuyers") && (propDTO.getFamily().equalsIgnoreCase("samefamily"))) {
				propDTO.setAgriSubTreeTypeGrt45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setAgriSubTreeTypeLes45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setIsPropDiffPustika("");
				propDTO.setAreThereTrees("");
				propDTO.setIsConstruction("");
				propDTO.setAgriAddPropButtonCheck(1);
				propDTO.setAgriTreeTypeList(propBO.getAgriTreeType(language));
				propDTO.setAgriSubClauseList((propBO.getAgriSubClauseList(language, propDTO)));
				propForm.setActionName("");
				if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {

					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else {
					FORWARD_JSP = "AGRILANDDIVERTED";
				}
			}

			else if (propDTO.getAreMoreBuyers().equalsIgnoreCase("morebuyers") && propDTO.getAgriDiscloseShare().equalsIgnoreCase("openshare")) {
				propDTO.setAgriSubTreeTypeGrt45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setAgriSubTreeTypeLes45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setIsPropDiffPustika("");
				propDTO.setAreThereTrees("");
				propDTO.setIsConstruction("");
				// propDTO.setAgriAddPropButtonCheck(0);
				propDTO.setAgriTreeTypeList(propBO.getAgriTreeType(language));
				propDTO.setAgriSubClauseList((propBO.getAgriSubClauseList(language, propDTO)));
				propForm.setActionName("");
				if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {

					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else {
					FORWARD_JSP = "AGRILANDDIVERTED";
				}
			} else {
				propDTO.setAgriSubTreeTypeGrt45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setAgriSubTreeTypeLes45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setIsPropDiffPustika("");
				propDTO.setAreThereTrees("");
				propDTO.setIsConstruction("");
				propDTO.setAgriAddPropButtonCheck(1);
				propDTO.setAgriTreeTypeList(propBO.getAgriTreeType(language));
				propDTO.setAgriSubClauseList((propBO.getAgriSubClauseList(language, propDTO)));
				propForm.setActionName("");
				if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {

					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else {
					FORWARD_JSP = "AGRILANDDIVERTED";
				}
			}
			// Added by Rakesh Soni for clr rinPustika uniqueNess: Start
			ArrayList rinPustikaArrayClr = null;

			if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {
				if (!"regInitNonPV".equalsIgnoreCase(propDTO.getFromModule())) {

					rinPustikaArrayClr = (ArrayList) session.getAttribute("rinPustikaArrayClr");
					if (rinPustikaArrayClr != null && !rinPustikaArrayClr.isEmpty()) {
						if (rinPustikaArrayClr.contains(propDTO.getRinPustikaNoClr())) {

							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERROR", "RIN pustika number is already part of another property in the ongoing transaction. Please input new RIN pustika number.");
							} else {
								request.setAttribute("ERROR", "à¤‹à¤£  à¤ªà¥�à¤¸à¥�à¤¤à¤¿à¤•à¤¾ à¤¸à¤‚à¤–à¥�à¤¯à¤¾ à¤ªà¤¹à¤²à¥‡ à¤¸à¥‡ à¤šà¤² à¤°à¤¹à¥€ à¤²à¥‡à¤¨à¤¦à¥‡à¤¨ à¤®à¥‡à¤‚ à¤¦à¥‚à¤¸à¤°à¥€ à¤¸à¤‚à¤ªà¤¤à¥�à¤¤à¤¿ à¤•à¤¾ à¤¹à¤¿à¤¸à¥�à¤¸à¤¾ à¤¹à¥ˆà¥¤ à¤•à¥ƒà¤ªà¤¯à¤¾ à¤¨à¤�  à¤‹à¤£ à¤ªà¥�à¤¸à¥�à¤¤à¤¿à¤•à¤¾ à¤¸à¤‚à¤–à¥�à¤¯à¤¾ à¤•à¥‹ à¤‡à¤¨à¤ªà¥�à¤Ÿ à¤•à¤°à¥‡à¤‚à¥¤");

							}
							FORWARD_JSP = "AGRILAND";
							propForm.getActionName().equalsIgnoreCase("");

						}

						/*
						 * else{ propDTO.getRinPustikaArrayClr().clear(); }
						 */

						return mapping.findForward(FORWARD_JSP);
					}

				} else if (propForm.getRegInitForm().getPvReqFlag().equalsIgnoreCase("N") && propForm.getRegInitForm().getPropReqFlag().equalsIgnoreCase("y")) {
					if (propForm.getRegInitForm().getHidnRegTxnId() != null && !propForm.getRegInitForm().getHidnRegTxnId().isEmpty()) {
						ArrayList rinPustika = new ArrayList();
						rinPustika = (ArrayList) propBO.getRinPustikaNo(propForm.getRegInitForm().getHidnRegTxnId());
						ArrayList rinPustikaList = new ArrayList();
						for (int i = 0; i < rinPustika.size(); i++) {
							ArrayList temp = (ArrayList) rinPustika.get(i);
							rinPustikaList.add(temp.get(0));
						}
						if (rinPustikaList.contains(propDTO.getRinPustikaNoClr())) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERROR", "RIN pustika number is already part of another property in the ongoing transaction. Please input new RIN pustika number.");
							} else {
								request.setAttribute("ERROR", "à¤‹à¤£ à¤ªà¥�à¤¸à¥�à¤¤à¤¿à¤•à¤¾ à¤¸à¤‚à¤–à¥�à¤¯à¤¾ à¤ªà¤¹à¤²à¥‡ à¤¸à¥‡ à¤šà¤² à¤°à¤¹à¥€ à¤²à¥‡à¤¨à¤¦à¥‡à¤¨ à¤®à¥‡à¤‚ à¤¦à¥‚à¤¸à¤°à¥€ à¤¸à¤‚à¤ªà¤¤à¥�à¤¤à¤¿ à¤•à¤¾ à¤¹à¤¿à¤¸à¥�à¤¸à¤¾ à¤¹à¥ˆà¥¤ à¤•à¥ƒà¤ªà¤¯à¤¾ à¤¨à¤� à¤‹à¤£  à¤ªà¥�à¤¸à¥�à¤¤à¤¿à¤•à¤¾ à¤¸à¤‚à¤–à¥�à¤¯à¤¾ à¤•à¥‹ à¤‡à¤¨à¤ªà¥�à¤Ÿ à¤•à¤°à¥‡à¤‚à¥¤");

							}
							FORWARD_JSP = "AGRILAND";
							propForm.getActionName().equalsIgnoreCase("");

							return mapping.findForward(FORWARD_JSP);
						}

					}

				}
			}
			// Added by Rakesh Soni for clr rinPustika uniqueNess: End

		} else if (propForm.getActionName().equalsIgnoreCase("agriLandTypeSubmit") && propDTO.getAgriLandTypeId().equalsIgnoreCase("7")) {
			propForm.setSearchDisplay("1");
			propForm.setClrData("0");
			propDTO.setKhasraNoClr("");
			propForm.setClrDto(new KhasraClrDTO());

			if (propDTO.getAreMoreBuyers().equalsIgnoreCase("nobuyers")) {
				propDTO.setAgriSubTreeTypeGrt45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setAgriSubTreeTypeLes45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setIsPropDiffPustika("");
				propDTO.setAreThereTrees("");
				propDTO.setIsConstruction("");
				propDTO.setAgriAddPropButtonCheck(1);
				propDTO.setAgriTreeTypeList(propBO.getAgriTreeType(language));
				propDTO.setAgriSubClauseList((propBO.getAgriSubClauseList(language, propDTO)));
				propForm.setActionName("");

				if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {

					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else {
					FORWARD_JSP = "AGRIBOTH";
				}

			} else if (propDTO.getAreMoreBuyers().equalsIgnoreCase("morebuyers") && (propDTO.getFamily().equalsIgnoreCase("samefamily"))) {
				propDTO.setAgriSubTreeTypeGrt45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setAgriSubTreeTypeLes45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setIsPropDiffPustika("");
				propDTO.setAreThereTrees("");
				propDTO.setIsConstruction("");
				propDTO.setAgriAddPropButtonCheck(1);
				propDTO.setAgriTreeTypeList(propBO.getAgriTreeType(language));
				propDTO.setAgriSubClauseList((propBO.getAgriSubClauseList(language, propDTO)));
				propForm.setActionName("");
				if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {

					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else {
					FORWARD_JSP = "AGRIBOTH";
				}
			} else if (propDTO.getAreMoreBuyers().equalsIgnoreCase("morebuyers") && propDTO.getAgriDiscloseShare().equalsIgnoreCase("openshare")) {
				propDTO.setAgriSubTreeTypeGrt45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setAgriSubTreeTypeLes45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setIsPropDiffPustika("");
				propDTO.setAreThereTrees("");
				propDTO.setIsConstruction("");
				// propDTO.setAgriAddPropButtonCheck(0);
				propDTO.setAgriTreeTypeList(propBO.getAgriTreeType(language));
				propDTO.setAgriSubClauseList((propBO.getAgriSubClauseList(language, propDTO)));
				propForm.setActionName("");
				if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {

					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else {
					FORWARD_JSP = "AGRIBOTH";
				}
			} else {
				propDTO.setAgriSubTreeTypeGrt45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setAgriSubTreeTypeLes45List(new ArrayList<PropertyValuationDTO>());
				propDTO.setIsPropDiffPustika("");
				propDTO.setAreThereTrees("");
				propDTO.setIsConstruction("");
				propDTO.setAgriAddPropButtonCheck(1);
				propDTO.setAgriTreeTypeList(propBO.getAgriTreeType(language));
				propDTO.setAgriSubClauseList((propBO.getAgriSubClauseList(language, propDTO)));
				propForm.setActionName("");
				if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {

					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else {
					FORWARD_JSP = "AGRIBOTH";
				}
			}

			// Added by Rakesh Soni for clr rinPustika uniqueNess: Start
			ArrayList rinPustikaArrayClr = null;

			if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {
				if (!"regInitNonPV".equalsIgnoreCase(propDTO.getFromModule())) {

					rinPustikaArrayClr = (ArrayList) session.getAttribute("rinPustikaArrayClr");
					if (rinPustikaArrayClr != null && !rinPustikaArrayClr.isEmpty()) {
						if (rinPustikaArrayClr.contains(propDTO.getRinPustikaNoClr())) {

							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERROR", "RIN pustika number is already part of another property in the ongoing transaction. Please input new RIN pustika number.");
							} else {
								request.setAttribute("ERROR", "à¤‹à¤£  à¤ªà¥�à¤¸à¥�à¤¤à¤¿à¤•à¤¾ à¤¸à¤‚à¤–à¥�à¤¯à¤¾ à¤ªà¤¹à¤²à¥‡ à¤¸à¥‡ à¤šà¤² à¤°à¤¹à¥€ à¤²à¥‡à¤¨à¤¦à¥‡à¤¨ à¤®à¥‡à¤‚ à¤¦à¥‚à¤¸à¤°à¥€ à¤¸à¤‚à¤ªà¤¤à¥�à¤¤à¤¿ à¤•à¤¾ à¤¹à¤¿à¤¸à¥�à¤¸à¤¾ à¤¹à¥ˆà¥¤ à¤•à¥ƒà¤ªà¤¯à¤¾ à¤¨à¤�  à¤‹à¤£ à¤ªà¥�à¤¸à¥�à¤¤à¤¿à¤•à¤¾ à¤¸à¤‚à¤–à¥�à¤¯à¤¾ à¤•à¥‹ à¤‡à¤¨à¤ªà¥�à¤Ÿ à¤•à¤°à¥‡à¤‚à¥¤");

							}
							FORWARD_JSP = "AGRILAND";
							propForm.getActionName().equalsIgnoreCase("");

						}

						/*
						 * else{ propDTO.getRinPustikaArrayClr().clear(); }
						 */

						return mapping.findForward(FORWARD_JSP);
					}

				} else if (propForm.getRegInitForm().getPvReqFlag().equalsIgnoreCase("N") && propForm.getRegInitForm().getPropReqFlag().equalsIgnoreCase("y")) {
					if (propForm.getRegInitForm().getHidnRegTxnId() != null && !propForm.getRegInitForm().getHidnRegTxnId().isEmpty()) {
						ArrayList rinPustika = new ArrayList();
						rinPustika = (ArrayList) propBO.getRinPustikaNo(propForm.getRegInitForm().getHidnRegTxnId());
						ArrayList rinPustikaList = new ArrayList();
						for (int i = 0; i < rinPustika.size(); i++) {
							ArrayList temp = (ArrayList) rinPustika.get(i);
							rinPustikaList.add(temp.get(0));
						}
						if (rinPustikaList.contains(propDTO.getRinPustikaNoClr())) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERROR", "RIN pustika number is already part of another property in the ongoing transaction. Please input new RIN pustika number.");
							} else {
								request.setAttribute("ERROR", "à¤‹à¤£ à¤ªà¥�à¤¸à¥�à¤¤à¤¿à¤•à¤¾ à¤¸à¤‚à¤–à¥�à¤¯à¤¾ à¤ªà¤¹à¤²à¥‡ à¤¸à¥‡ à¤šà¤² à¤°à¤¹à¥€ à¤²à¥‡à¤¨à¤¦à¥‡à¤¨ à¤®à¥‡à¤‚ à¤¦à¥‚à¤¸à¤°à¥€ à¤¸à¤‚à¤ªà¤¤à¥�à¤¤à¤¿ à¤•à¤¾ à¤¹à¤¿à¤¸à¥�à¤¸à¤¾ à¤¹à¥ˆà¥¤ à¤•à¥ƒà¤ªà¤¯à¤¾ à¤¨à¤� à¤‹à¤£  à¤ªà¥�à¤¸à¥�à¤¤à¤¿à¤•à¤¾ à¤¸à¤‚à¤–à¥�à¤¯à¤¾ à¤•à¥‹ à¤‡à¤¨à¤ªà¥�à¤Ÿ à¤•à¤°à¥‡à¤‚à¥¤");

							}
							FORWARD_JSP = "AGRILAND";
							propForm.getActionName().equalsIgnoreCase("");

							return mapping.findForward(FORWARD_JSP);
						}

					}

				}
			}
			// Added by Rakesh Soni for clr rinPustika uniqueNess: End

		}
		// Rakesh CLR phase2: Start
		else if (propForm.getActionName().equalsIgnoreCase("addMoreKhasraClr") && propDTO.getAgriLandTypeId().equalsIgnoreCase("5")) {

			// for duplicate khasra

			propForm.getUniKhasraArray().add(propDTO.getKhasraNoClr().trim());

			// HashMap<String, Double> saleableAreaPerKhasra=new HashMap<String,
			// Double>();
			// saleableAreaPerKhasra.put(propForm.getClrDto().getKhasraNoClr(),
			// Double.valueOf(propDTO.getAgriTotalUnDivertedArea()));

			SampadaKhasraClrDTO sampadaDTO = new SampadaKhasraClrDTO();
			// CLR DATA
			sampadaDTO.setCensusCode(propDTO.getCensusCode());
			sampadaDTO.setKhasraNoClr(propForm.getClrDto().getKhasraNoClr());
			sampadaDTO.setClrKhasraId(propForm.getClrDto().getClrKhasraId());
			sampadaDTO.setKhasraTotalArea(propForm.getClrDto().getKhasraTotalArea());
			sampadaDTO.setDoubleCropArea(propForm.getClrDto().getDoubleCropArea());
			sampadaDTO.setSingleCropArea(propForm.getClrDto().getSingleCropArea());
			sampadaDTO.setIrrigatedArea(propForm.getClrDto().getIrrigatedArea());
			sampadaDTO.setNonIrrigatedArea(propForm.getClrDto().getNonIrrigatedArea());
			sampadaDTO.setPadtiArea(propForm.getClrDto().getPadtiArea());
			sampadaDTO.setLandIrriOrNot(propForm.getClrDto().getLandIrriOrNot());
			sampadaDTO.setLandUnIrriOrNot(propForm.getClrDto().getLandUnIrriOrNot());
			sampadaDTO.setPadtiFlag(propForm.getClrDto().getPadtiFlag());
			// sampadaDTO.setLandUnirriDouble(landUnirriDouble);
			sampadaDTO.setCurrentStatus(propForm.getClrDto().getCurrentStatus());
			sampadaDTO.setMortgage(propForm.getClrDto().getMortgage());
			sampadaDTO.setNohiyat(propForm.getClrDto().getNohiyat());
			sampadaDTO.setKehfiyat(propForm.getClrDto().getKehfiyat());
			sampadaDTO.setOwnership(propForm.getClrDto().getOwnership());
			sampadaDTO.setClrFlag(propForm.getClrDto().getClrFlag());

			// Sampada Data
			sampadaDTO.setSampadaDoubleCrop(Double.valueOf(!propDTO.getAgriUndivDoubleCropArea().isEmpty() ? propDTO.getAgriUndivDoubleCropArea() : "0.0"));
			sampadaDTO.setSampadaIrrigated(Double.valueOf(!propDTO.getAgriUndivIrrigatedArea().isEmpty() ? propDTO.getAgriUndivIrrigatedArea() : "0.0"));
			sampadaDTO.setSampadaSingleCrop(Double.valueOf(!propDTO.getAgriUndivSingleCropArea().isEmpty() ? propDTO.getAgriUndivSingleCropArea() : "0.0"));
			sampadaDTO.setTotalSaleableArea(Double.valueOf(!propDTO.getAgriTotalUnDivertedArea().isEmpty() ? propDTO.getAgriTotalUnDivertedArea() : "0.0"));

			propForm.getSampadaClrKhasraList().add(sampadaDTO);

			// Add up Sampada undiverted Data
			propDTO.setTotAgriTotalUnDivertedArea(Double.valueOf(!propDTO.getAgriTotalUnDivertedArea().isEmpty() ? propDTO.getAgriTotalUnDivertedArea() : "0.0") + propDTO.getTotAgriTotalUnDivertedArea());
			propDTO.setTotAgriUndivIrrigatedArea(Double.valueOf(!propDTO.getAgriUndivIrrigatedArea().isEmpty() ? propDTO.getAgriUndivIrrigatedArea() : "0.0") + propDTO.getTotAgriUndivIrrigatedArea());
			propDTO.setTotAgriUndivDoubleCropArea(Double.valueOf(!propDTO.getAgriUndivDoubleCropArea().isEmpty() ? propDTO.getAgriUndivDoubleCropArea() : "0.0") + propDTO.getTotAgriUndivDoubleCropArea());
			propDTO.setTotAgriUndivSingleCropArea(Double.valueOf(!propDTO.getAgriUndivSingleCropArea().isEmpty() ? propDTO.getAgriUndivSingleCropArea() : "0.0") + propDTO.getTotAgriUndivSingleCropArea());

			propForm.propDTO.setAgriTotalUnDivertedArea("");
			propForm.propDTO.setAgriUndivSingleCropArea("");
			propForm.propDTO.setAgriUndivDoubleCropArea("");
			propForm.propDTO.setAgriUndivIrrigatedArea("");
			propForm.propDTO.setKhasraNoClr("");
			propForm.setSearchDisplay("1");
			// propForm.setClrDto("");
			propForm.setClrData("0");
			// for duplicate khasra

			//propForm.getUniKhasraArray().add(propDTO.getKhasraNoClr().trim());
			FORWARD_JSP = "SEARCHKHASRAJSP";

		} else if (propForm.getActionName().equalsIgnoreCase("addMoreKhasraClr") && propDTO.getAgriLandTypeId().equalsIgnoreCase("6")) {

			// for duplicate khasra

			propForm.getUniKhasraArray().add(propDTO.getKhasraNoClr().trim());

			SampadaKhasraClrDTO sampadaDTO = new SampadaKhasraClrDTO();
			// CLR DATA
			//sampadaDTO.setCensusCode(BigDecimal.valueOf(propDTO.getCensusCode(
			// )).toPlainString());
			sampadaDTO.setCensusCode(propDTO.getCensusCode());
			sampadaDTO.setKhasraNoClr(propForm.getClrDto().getKhasraNoClr());
			sampadaDTO.setClrKhasraId(propForm.getClrDto().getClrKhasraId());
			sampadaDTO.setKhasraTotalArea(propForm.getClrDto().getKhasraTotalArea());
			sampadaDTO.setDoubleCropArea(propForm.getClrDto().getDoubleCropArea());
			sampadaDTO.setSingleCropArea(propForm.getClrDto().getSingleCropArea());
			sampadaDTO.setIrrigatedArea(propForm.getClrDto().getIrrigatedArea());
			sampadaDTO.setNonIrrigatedArea(propForm.getClrDto().getNonIrrigatedArea());
			sampadaDTO.setPadtiArea(propForm.getClrDto().getPadtiArea());
			sampadaDTO.setLandIrriOrNot(propForm.getClrDto().getLandIrriOrNot());
			sampadaDTO.setLandUnIrriOrNot(propForm.getClrDto().getLandUnIrriOrNot());
			sampadaDTO.setPadtiFlag(propForm.getClrDto().getPadtiFlag());
			// sampadaDTO.setLandUnirriDouble(landUnirriDouble);
			sampadaDTO.setCurrentStatus(propForm.getClrDto().getCurrentStatus());
			sampadaDTO.setMortgage(propForm.getClrDto().getMortgage());
			sampadaDTO.setNohiyat(propForm.getClrDto().getNohiyat());
			sampadaDTO.setKehfiyat(propForm.getClrDto().getKehfiyat());
			sampadaDTO.setOwnership(propForm.getClrDto().getOwnership());
			sampadaDTO.setClrFlag(propForm.getClrDto().getClrFlag());

			sampadaDTO.setSampadaTotalDivertedArea(Double.valueOf(!propDTO.getAgriTotalDivertedArea().isEmpty() ? propDTO.getAgriTotalDivertedArea() : "0.0"));
			sampadaDTO.setSampadaTotalDivertedCommercialArea(Double.valueOf(!propDTO.getAgriTotalDivertedCommercialArea().isEmpty() ? propDTO.getAgriTotalDivertedCommercialArea() : "0.0"));
			sampadaDTO.setSampadaTotalDivertedEducationalArea(Double.valueOf(!propDTO.getAgriTotalDivertedEducationalArea().isEmpty() ? propDTO.getAgriTotalDivertedEducationalArea() : "0.0"));
			sampadaDTO.setSampadaTotalDivertedHealthArea(Double.valueOf(!propDTO.getAgriTotalDivertedHealthArea().isEmpty() ? propDTO.getAgriTotalDivertedHealthArea() : "0.0"));
			sampadaDTO.setSampadaTotalDivertedIndustrialArea(Double.valueOf(!propDTO.getAgriTotalDivertedIndustrialArea().isEmpty() ? propDTO.getAgriTotalDivertedIndustrialArea() : "0.0"));
			sampadaDTO.setSampadaTotalDivertedOthersArea(Double.valueOf(!propDTO.getAgriTotalDivertedOthersArea().isEmpty() ? propDTO.getAgriTotalDivertedOthersArea() : "0.0"));
			sampadaDTO.setSampadaTotalDivertedResidentialArea(Double.valueOf(!propDTO.getAgriTotalDivertedResidentialArea().isEmpty() ? propDTO.getAgriTotalDivertedResidentialArea() : "0.0"));

			propForm.getSampadaClrKhasraList().add(sampadaDTO);

			propDTO.setTotDivertedCommercialArea(Double.valueOf(!propDTO.getAgriTotalDivertedCommercialArea().isEmpty() ? propDTO.getAgriTotalDivertedCommercialArea() : "0.0") + propDTO.getTotDivertedCommercialArea());
			propDTO.setTotDivertedEducationalArea(Double.valueOf(!propDTO.getAgriTotalDivertedEducationalArea().isEmpty() ? propDTO.getAgriTotalDivertedEducationalArea() : "0.0") + propDTO.getTotDivertedEducationalArea());
			propDTO.setTotDivertedHealthArea(Double.valueOf(!propDTO.getAgriTotalDivertedHealthArea().isEmpty() ? propDTO.getAgriTotalDivertedHealthArea() : "0.0") + propDTO.getTotDivertedHealthArea());
			propDTO.setTotDivertedIndustrialArea(Double.valueOf(!propDTO.getAgriTotalDivertedIndustrialArea().isEmpty() ? propDTO.getAgriTotalDivertedIndustrialArea() : "0.0") + propDTO.getTotDivertedIndustrialArea());
			propDTO.setTotDivertedOthersArea(Double.valueOf(!propDTO.getAgriTotalDivertedOthersArea().isEmpty() ? propDTO.getAgriTotalDivertedOthersArea() : "0.0") + propDTO.getTotDivertedOthersArea());
			propDTO.setTotDivertedResidentialArea(Double.valueOf(!propDTO.getAgriTotalDivertedResidentialArea().isEmpty() ? propDTO.getAgriTotalDivertedResidentialArea() : "0.0") + propDTO.getTotDivertedResidentialArea());
			propDTO.setTotDivertedArea(Double.valueOf(!propDTO.getAgriTotalDivertedArea().isEmpty() ? propDTO.getAgriTotalDivertedArea() : "0.0") + propDTO.getTotDivertedArea());

			propForm.propDTO.setAgriTotalDivertedResidentialArea("");
			propForm.propDTO.setAgriTotalDivertedCommercialArea("");
			propForm.propDTO.setAgriTotalDivertedEducationalArea("");
			propForm.propDTO.setAgriTotalDivertedHealthArea("");
			propForm.propDTO.setAgriTotalDivertedIndustrialArea("");
			propForm.propDTO.setAgriTotalDivertedOthersArea("");
			propForm.propDTO.setAgriTotalDivertedArea("");
			propForm.propDTO.setKhasraNoClr("");

			propForm.setSearchDisplay("1");
			// propForm.setClrDto("");
			propForm.setClrData("0");

			FORWARD_JSP = "SEARCHKHASRAJSP";

		} else if (propForm.getActionName().equalsIgnoreCase("addMoreKhasraClr") && propDTO.getAgriLandTypeId().equalsIgnoreCase("7")) {

			// for duplicate khasra

			propForm.getUniKhasraArray().add(propDTO.getKhasraNoClr().trim());

			SampadaKhasraClrDTO sampadaDTO = new SampadaKhasraClrDTO();
			// CLR DATA
			sampadaDTO.setCensusCode(propDTO.getCensusCode());
			sampadaDTO.setKhasraNoClr(propForm.getClrDto().getKhasraNoClr());
			sampadaDTO.setClrKhasraId(propForm.getClrDto().getClrKhasraId());
			sampadaDTO.setKhasraTotalArea(propForm.getClrDto().getKhasraTotalArea());
			sampadaDTO.setDoubleCropArea(propForm.getClrDto().getDoubleCropArea());
			sampadaDTO.setSingleCropArea(propForm.getClrDto().getSingleCropArea());
			sampadaDTO.setIrrigatedArea(propForm.getClrDto().getIrrigatedArea());
			sampadaDTO.setNonIrrigatedArea(propForm.getClrDto().getNonIrrigatedArea());
			sampadaDTO.setPadtiArea(propForm.getClrDto().getPadtiArea());
			sampadaDTO.setLandIrriOrNot(propForm.getClrDto().getLandIrriOrNot());
			sampadaDTO.setLandUnIrriOrNot(propForm.getClrDto().getLandUnIrriOrNot());
			sampadaDTO.setPadtiFlag(propForm.getClrDto().getPadtiFlag());
			// sampadaDTO.setLandUnirriDouble(landUnirriDouble);
			sampadaDTO.setCurrentStatus(propForm.getClrDto().getCurrentStatus());
			sampadaDTO.setMortgage(propForm.getClrDto().getMortgage());
			sampadaDTO.setNohiyat(propForm.getClrDto().getNohiyat());
			sampadaDTO.setKehfiyat(propForm.getClrDto().getKehfiyat());
			sampadaDTO.setOwnership(propForm.getClrDto().getOwnership());
			sampadaDTO.setClrFlag(propForm.getClrDto().getClrFlag());

			// Sampada Data
			sampadaDTO.setSampadaDoubleCrop(Double.valueOf(!propDTO.getAgriUndivDoubleCropArea().isEmpty() ? propDTO.getAgriUndivDoubleCropArea() : "0.0"));
			sampadaDTO.setSampadaIrrigated(Double.valueOf(!propDTO.getAgriUndivIrrigatedArea().isEmpty() ? propDTO.getAgriUndivIrrigatedArea() : "0.0"));
			sampadaDTO.setSampadaSingleCrop(Double.valueOf(!propDTO.getAgriUndivSingleCropArea().isEmpty() ? propDTO.getAgriUndivSingleCropArea() : "0.0"));
			sampadaDTO.setTotalSaleableArea(Double.valueOf(!propDTO.getAgriTotalUnDivertedArea().isEmpty() ? propDTO.getAgriTotalUnDivertedArea() : "0.0"));

			sampadaDTO.setSampadaTotalDivertedArea(Double.valueOf(!propDTO.getAgriTotalDivertedArea().isEmpty() ? propDTO.getAgriTotalDivertedArea() : "0.0"));
			sampadaDTO.setSampadaTotalDivertedCommercialArea(Double.valueOf(!propDTO.getAgriTotalDivertedCommercialArea().isEmpty() ? propDTO.getAgriTotalDivertedCommercialArea() : "0.0"));
			sampadaDTO.setSampadaTotalDivertedEducationalArea(Double.valueOf(!propDTO.getAgriTotalDivertedEducationalArea().isEmpty() ? propDTO.getAgriTotalDivertedEducationalArea() : "0.0"));
			sampadaDTO.setSampadaTotalDivertedHealthArea(Double.valueOf(!propDTO.getAgriTotalDivertedHealthArea().isEmpty() ? propDTO.getAgriTotalDivertedHealthArea() : "0.0"));
			sampadaDTO.setSampadaTotalDivertedIndustrialArea(Double.valueOf(!propDTO.getAgriTotalDivertedIndustrialArea().isEmpty() ? propDTO.getAgriTotalDivertedIndustrialArea() : "0.0"));
			sampadaDTO.setSampadaTotalDivertedOthersArea(Double.valueOf(!propDTO.getAgriTotalDivertedOthersArea().isEmpty() ? propDTO.getAgriTotalDivertedOthersArea() : "0.0"));
			sampadaDTO.setSampadaTotalDivertedResidentialArea(Double.valueOf(!propDTO.getAgriTotalDivertedResidentialArea().isEmpty() ? propDTO.getAgriTotalDivertedResidentialArea() : "0.0"));

			propForm.getSampadaClrKhasraList().add(sampadaDTO);

			// Add up Sampada undiverted Data
			propDTO.setTotAgriTotalUnDivertedArea(Double.valueOf(!propDTO.getAgriTotalUnDivertedArea().isEmpty() ? propDTO.getAgriTotalUnDivertedArea() : "0.0") + propDTO.getTotAgriTotalUnDivertedArea());
			propDTO.setTotAgriUndivIrrigatedArea(Double.valueOf(!propDTO.getAgriUndivIrrigatedArea().isEmpty() ? propDTO.getAgriUndivIrrigatedArea() : "0.0") + propDTO.getTotAgriUndivIrrigatedArea());
			propDTO.setTotAgriUndivDoubleCropArea(Double.valueOf(!propDTO.getAgriUndivDoubleCropArea().isEmpty() ? propDTO.getAgriUndivDoubleCropArea() : "0.0") + propDTO.getTotAgriUndivDoubleCropArea());
			propDTO.setTotAgriUndivSingleCropArea(Double.valueOf(!propDTO.getAgriUndivSingleCropArea().isEmpty() ? propDTO.getAgriUndivSingleCropArea() : "0.0") + propDTO.getTotAgriUndivSingleCropArea());

			propForm.propDTO.setAgriTotalUnDivertedArea("");
			propForm.propDTO.setAgriUndivSingleCropArea("");
			propForm.propDTO.setAgriUndivDoubleCropArea("");
			propForm.propDTO.setAgriUndivIrrigatedArea("");
			// propForm.propDTO.setKhasraNoClr("");

			// Add up Sampada diverted Data
			propDTO.setTotDivertedCommercialArea(Double.valueOf(!propDTO.getAgriTotalDivertedCommercialArea().isEmpty() ? propDTO.getAgriTotalDivertedCommercialArea() : "0.0") + propDTO.getTotDivertedCommercialArea());
			propDTO.setTotDivertedEducationalArea(Double.valueOf(!propDTO.getAgriTotalDivertedEducationalArea().isEmpty() ? propDTO.getAgriTotalDivertedEducationalArea() : "0.0") + propDTO.getTotDivertedEducationalArea());
			propDTO.setTotDivertedHealthArea(Double.valueOf(!propDTO.getAgriTotalDivertedHealthArea().isEmpty() ? propDTO.getAgriTotalDivertedHealthArea() : "0.0") + propDTO.getTotDivertedHealthArea());
			propDTO.setTotDivertedIndustrialArea(Double.valueOf(!propDTO.getAgriTotalDivertedIndustrialArea().isEmpty() ? propDTO.getAgriTotalDivertedIndustrialArea() : "0.0") + propDTO.getTotDivertedIndustrialArea());
			propDTO.setTotDivertedOthersArea(Double.valueOf(!propDTO.getAgriTotalDivertedOthersArea().isEmpty() ? propDTO.getAgriTotalDivertedOthersArea() : "0.0") + propDTO.getTotDivertedOthersArea());
			propDTO.setTotDivertedResidentialArea(Double.valueOf(!propDTO.getAgriTotalDivertedResidentialArea().isEmpty() ? propDTO.getAgriTotalDivertedResidentialArea() : "0.0") + propDTO.getTotDivertedResidentialArea());
			// propDTO.setTotDivertedArea(Double.valueOf(!propDTO.
			// getAgriTotalDivertedArea().isEmpty()?
			// propDTO.getAgriTotalDivertedArea() :"0.0")+
			// propDTO.getTotDivertedOthersArea());
			propDTO.setTotDivertedArea(Double.valueOf(!propDTO.getAgriTotalDivertedArea().isEmpty() ? propDTO.getAgriTotalDivertedArea() : "0.0") + propDTO.getTotDivertedArea());

			propForm.propDTO.setAgriTotalDivertedResidentialArea("");
			propForm.propDTO.setAgriTotalDivertedCommercialArea("");
			propForm.propDTO.setAgriTotalDivertedEducationalArea("");
			propForm.propDTO.setAgriTotalDivertedHealthArea("");
			propForm.propDTO.setAgriTotalDivertedIndustrialArea("");
			propForm.propDTO.setAgriTotalDivertedOthersArea("");
			propForm.propDTO.setAgriTotalDivertedArea("");
			propForm.propDTO.setKhasraNoClr("");

			propForm.setSearchDisplay("1");
			// propForm.setClrDto("");
			propForm.setClrData("0");

			FORWARD_JSP = "SEARCHKHASRAJSP";

		}
		// Rakesh CLR phase2: End

		else if (propForm.getActionName().equalsIgnoreCase("agriLandundivNoTreeSubmit")) {

			if (treeCategoryId.equalsIgnoreCase("1")) {
				propDTO.setAgriSubTreeTypeGrt45List(new ArrayList<PropertyValuationDTO>());
				for (int i = 0; i < propDTO.getAgriTreeTypeList().size(); i++) {
					if (propDTO.getAgriTreeTypeList().get(i).getAgriTreeTypeId().equalsIgnoreCase("1")) {
						propDTO.getAgriTreeTypeList().get(i).setAreThereTrees("");
						String[] TreeLesser45 = request.getParameterValues("noOfSubTreeTypeLes45");
						if (TreeLesser45 != null && TreeLesser45.length > 0) {
							propDTO.getAgriSubTreeTypeLes45List().get(0).setNoOfSubTreeTypeLes45(TreeLesser45[0]);
						}
					}
				}
			}
			if (treeCategoryId.equalsIgnoreCase("2")) {
				propDTO.setAgriSubTreeTypeLes45List(new ArrayList<PropertyValuationDTO>());
				for (int i = 0; i < propDTO.getAgriTreeTypeList().size(); i++) {
					if (propDTO.getAgriTreeTypeList().get(i).getAgriTreeTypeId().equalsIgnoreCase("2")) {
						propDTO.getAgriTreeTypeList().get(i).setAreThereTrees("");
						String[] TreeGreater45 = request.getParameterValues("noOfSubTreeTypeGrt45");
						if (TreeGreater45 != null && TreeGreater45.length > 0) {
							propDTO.getAgriSubTreeTypeGrt45List().get(0).setNoOfSubTreeTypeGrt45(TreeGreater45[0]);
							propDTO.getAgriSubTreeTypeGrt45List().get(1).setNoOfSubTreeTypeGrt45(TreeGreater45[1]);
							propDTO.getAgriSubTreeTypeGrt45List().get(2).setNoOfSubTreeTypeGrt45(TreeGreater45[2]);
						}
					}
				}
			}
			propDTO.setAgriTreeTypeId(null);
			propForm.setActionName("");
			if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {

				FORWARD_JSP = "AGRILANDUNDIVERTEDCLR";
			} else {
				FORWARD_JSP = "AGRILANDUNDIVERTED";
			}
		}

		else if (propForm.getActionName().equalsIgnoreCase("agriLandundivTreeSubmit")) {

			if (treeCategoryId.equalsIgnoreCase("1")) {

				propDTO.setAgriSubTreeTypeGrt45List(propBO.getAgriSubTreeGrt45Type(language, treeCategoryId));
				for (int i = 0; i < propDTO.getAgriTreeTypeList().size(); i++) {
					if (propDTO.getAgriTreeTypeList().get(i).getAgriTreeTypeId().equalsIgnoreCase("1")) {
						propDTO.getAgriTreeTypeList().get(i).setAreThereTrees("1");

						String[] TreeLesser45 = request.getParameterValues("noOfSubTreeTypeLes45");
						if (TreeLesser45 != null && TreeLesser45.length > 0) {
							propDTO.getAgriSubTreeTypeLes45List().get(0).setNoOfSubTreeTypeLes45(TreeLesser45[0]);

						}
					}
				}
			}
			if (treeCategoryId.equalsIgnoreCase("2")) {
				propDTO.setAgriSubTreeTypeLes45List(propBO.getAgriSubTreeles45Type(language, treeCategoryId));
				for (int i = 0; i < propDTO.getAgriTreeTypeList().size(); i++) {
					if (propDTO.getAgriTreeTypeList().get(i).getAgriTreeTypeId().equalsIgnoreCase("2")) {
						propDTO.getAgriTreeTypeList().get(i).setAreThereTrees("2");
						String[] TreeGreater45 = request.getParameterValues("noOfSubTreeTypeGrt45");
						if (TreeGreater45 != null && TreeGreater45.length > 0) {
							propDTO.getAgriSubTreeTypeGrt45List().get(0).setNoOfSubTreeTypeGrt45(TreeGreater45[0]);
							propDTO.getAgriSubTreeTypeGrt45List().get(1).setNoOfSubTreeTypeGrt45(TreeGreater45[1]);
							propDTO.getAgriSubTreeTypeGrt45List().get(2).setNoOfSubTreeTypeGrt45(TreeGreater45[2]);
						}

					}
				}
			}
			propForm.setActionName("");

			if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {
				FORWARD_JSP = "AGRILANDUNDIVERTEDTREESCLR";

			} else {
				FORWARD_JSP = "AGRILANDUNDIVERTEDTREES";
			}
		}

		else if (propForm.getActionName().equalsIgnoreCase("agriLanddivNoTreeSubmit")) {
			if (treeCategoryId.equalsIgnoreCase("1")) {
				propDTO.setAgriSubTreeTypeGrt45List(new ArrayList<PropertyValuationDTO>());
				for (int i = 0; i < propDTO.getAgriTreeTypeList().size(); i++) {
					if (propDTO.getAgriTreeTypeList().get(i).getAgriTreeTypeId().equalsIgnoreCase("1")) {
						propDTO.getAgriTreeTypeList().get(i).setAreThereTrees("");
						String[] TreeLesser45 = request.getParameterValues("noOfSubTreeTypeLes45");
						if (TreeLesser45 != null && TreeLesser45.length > 0) {
							propDTO.getAgriSubTreeTypeLes45List().get(0).setNoOfSubTreeTypeLes45(TreeLesser45[0]);
						}

					}
				}
			}
			if (treeCategoryId.equalsIgnoreCase("2")) {
				propDTO.setAgriSubTreeTypeLes45List(new ArrayList<PropertyValuationDTO>());
				for (int i = 0; i < propDTO.getAgriTreeTypeList().size(); i++) {
					if (propDTO.getAgriTreeTypeList().get(i).getAgriTreeTypeId().equalsIgnoreCase("2")) {
						propDTO.getAgriTreeTypeList().get(i).setAreThereTrees("");
						String[] TreeGreater45 = request.getParameterValues("noOfSubTreeTypeGrt45");
						if (TreeGreater45 != null && TreeGreater45.length > 0) {
							propDTO.getAgriSubTreeTypeGrt45List().get(0).setNoOfSubTreeTypeGrt45(TreeGreater45[0]);
							propDTO.getAgriSubTreeTypeGrt45List().get(1).setNoOfSubTreeTypeGrt45(TreeGreater45[1]);
							propDTO.getAgriSubTreeTypeGrt45List().get(2).setNoOfSubTreeTypeGrt45(TreeGreater45[2]);
						}
					}
				}
			}
			propDTO.setAgriTreeTypeId(null);
			propForm.setActionName("");

			if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {

				FORWARD_JSP = "AGRILANDDIVERTEDTREESCLR";
			} else {
				FORWARD_JSP = "AGRILANDDIVERTEDTREES";
			}

		}

		else if (propForm.getActionName().equalsIgnoreCase("agriLanddivTreeSubmit")) {

			if (treeCategoryId.equalsIgnoreCase("1")) {
				propDTO.setAgriSubTreeTypeGrt45List(propBO.getAgriSubTreeGrt45Type(language, treeCategoryId));
				for (int i = 0; i < propDTO.getAgriTreeTypeList().size(); i++) {
					if (propDTO.getAgriTreeTypeList().get(i).getAgriTreeTypeId().equalsIgnoreCase("1")) {
						propDTO.getAgriTreeTypeList().get(i).setAreThereTrees("1");
						String[] TreeLesser45 = request.getParameterValues("noOfSubTreeTypeLes45");
						if (TreeLesser45 != null && TreeLesser45.length > 0) {
							propDTO.getAgriSubTreeTypeLes45List().get(0).setNoOfSubTreeTypeLes45(TreeLesser45[0]);

						}
					}
				}
			}
			if (treeCategoryId.equalsIgnoreCase("2")) {
				propDTO.setAgriSubTreeTypeLes45List(propBO.getAgriSubTreeles45Type(language, treeCategoryId));
				for (int i = 0; i < propDTO.getAgriTreeTypeList().size(); i++) {
					if (propDTO.getAgriTreeTypeList().get(i).getAgriTreeTypeId().equalsIgnoreCase("2")) {
						propDTO.getAgriTreeTypeList().get(i).setAreThereTrees("2");
						String[] TreeGreater45 = request.getParameterValues("noOfSubTreeTypeGrt45");
						if (TreeGreater45 != null && TreeGreater45.length > 0) {
							propDTO.getAgriSubTreeTypeGrt45List().get(0).setNoOfSubTreeTypeGrt45(TreeGreater45[0]);
							propDTO.getAgriSubTreeTypeGrt45List().get(1).setNoOfSubTreeTypeGrt45(TreeGreater45[1]);
							propDTO.getAgriSubTreeTypeGrt45List().get(2).setNoOfSubTreeTypeGrt45(TreeGreater45[2]);
						}
					}
				}

			}
			propForm.setActionName("");
			if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {

				FORWARD_JSP = "AGRILANDDIVERTEDCLR";
			} else {
				FORWARD_JSP = "AGRILANDDIVERTED";

			}
		}

		else if (propForm.getActionName().equalsIgnoreCase("agriLandbothNoTreeSubmit")) {
			if (treeCategoryId.equalsIgnoreCase("1")) {
				propDTO.setAgriSubTreeTypeGrt45List(new ArrayList<PropertyValuationDTO>());
				for (int i = 0; i < propDTO.getAgriTreeTypeList().size(); i++) {
					if (propDTO.getAgriTreeTypeList().get(i).getAgriTreeTypeId().equalsIgnoreCase("1")) {
						propDTO.getAgriTreeTypeList().get(i).setAreThereTrees("");
						String[] TreeLesser45 = request.getParameterValues("noOfSubTreeTypeLes45");
						if (TreeLesser45 != null && TreeLesser45.length > 0) {
							propDTO.getAgriSubTreeTypeLes45List().get(0).setNoOfSubTreeTypeLes45(TreeLesser45[0]);
						}
					}
				}
			}
			if (treeCategoryId.equalsIgnoreCase("2")) {
				propDTO.setAgriSubTreeTypeLes45List(new ArrayList<PropertyValuationDTO>());
				for (int i = 0; i < propDTO.getAgriTreeTypeList().size(); i++) {
					if (propDTO.getAgriTreeTypeList().get(i).getAgriTreeTypeId().equalsIgnoreCase("2")) {
						propDTO.getAgriTreeTypeList().get(i).setAreThereTrees("");
						String[] TreeGreater45 = request.getParameterValues("noOfSubTreeTypeGrt45");
						if (TreeGreater45 != null && TreeGreater45.length > 0) {
							propDTO.getAgriSubTreeTypeGrt45List().get(0).setNoOfSubTreeTypeGrt45(TreeGreater45[0]);
							propDTO.getAgriSubTreeTypeGrt45List().get(1).setNoOfSubTreeTypeGrt45(TreeGreater45[1]);
							propDTO.getAgriSubTreeTypeGrt45List().get(2).setNoOfSubTreeTypeGrt45(TreeGreater45[2]);
						}
					}
				}
			}
			propDTO.setAgriTreeTypeId(null);
			propForm.setActionName("");
			if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {

				FORWARD_JSP = "AGRIBOTHCLR";
			} else {
				FORWARD_JSP = "AGRIBOTH";

			}
		}

		else if (propForm.getActionName().equalsIgnoreCase("agriLandbothTreeSubmit")) {

			if (treeCategoryId.equalsIgnoreCase("1")) {
				propDTO.setAgriSubTreeTypeGrt45List(propBO.getAgriSubTreeGrt45Type(language, treeCategoryId));
				for (int i = 0; i < propDTO.getAgriTreeTypeList().size(); i++) {
					if (propDTO.getAgriTreeTypeList().get(i).getAgriTreeTypeId().equalsIgnoreCase("1")) {
						propDTO.getAgriTreeTypeList().get(i).setAreThereTrees("1");
						String[] TreeLesser45 = request.getParameterValues("noOfSubTreeTypeLes45");
						if (TreeLesser45 != null && TreeLesser45.length > 0) {
							propDTO.getAgriSubTreeTypeLes45List().get(0).setNoOfSubTreeTypeLes45(TreeLesser45[0]);

						}
					}
				}
			}
			if (treeCategoryId.equalsIgnoreCase("2")) {
				propDTO.setAgriSubTreeTypeLes45List(propBO.getAgriSubTreeles45Type(language, treeCategoryId));
				for (int i = 0; i < propDTO.getAgriTreeTypeList().size(); i++) {
					if (propDTO.getAgriTreeTypeList().get(i).getAgriTreeTypeId().equalsIgnoreCase("2")) {
						propDTO.getAgriTreeTypeList().get(i).setAreThereTrees("2");
						String[] TreeGreater45 = request.getParameterValues("noOfSubTreeTypeGrt45");
						if (TreeGreater45 != null && TreeGreater45.length > 0) {
							propDTO.getAgriSubTreeTypeGrt45List().get(0).setNoOfSubTreeTypeGrt45(TreeGreater45[0]);
							propDTO.getAgriSubTreeTypeGrt45List().get(1).setNoOfSubTreeTypeGrt45(TreeGreater45[1]);
							propDTO.getAgriSubTreeTypeGrt45List().get(2).setNoOfSubTreeTypeGrt45(TreeGreater45[2]);
						}
					}
				}
			}
			propForm.setActionName("");

			if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {
				FORWARD_JSP = "AGRILANDBOTHTREESCLR";

			} else {
				FORWARD_JSP = "AGRILANDBOTHTREES";
			}
		}

		else if (propForm.getActionName().equalsIgnoreCase("addMoreProperty")) {

			if (propDTO.getAgriLandTypeId().equalsIgnoreCase("5")) {
				PropertyValuationDTO undivpropDTO = new PropertyValuationDTO();
				undivpropDTO.setAgriTotalUnDivertedArea(propDTO.getAgriTotalUnDivertedArea());
				propForm.setSampadaArea(propDTO.getAgriTotalUnDivertedArea());
				undivpropDTO.setAgriUndivSingleCropArea(propDTO.getAgriUndivSingleCropArea());
				undivpropDTO.setAgriUndivDoubleCropArea(propDTO.getAgriUndivDoubleCropArea());
				undivpropDTO.setAgriUndivIrrigatedArea(propDTO.getAgriUndivIrrigatedArea());
				undivpropDTO.setIsConstruction(propDTO.getIsConstruction());
				undivpropDTO.setAgriBuildingIndivConstValue(propDTO.getConstructionCostFinal());
				if ("construct".equalsIgnoreCase(undivpropDTO.getIsConstruction()))

				{
					undivpropDTO.setAgriBuildingConstructTxnId(propDTO.getBuildingTxnIdAgri());
					undivpropDTO.setAgriBuildingConstructCost(propDTO.getConstructedValueBuilding());
				}
				undivpropDTO.setAgriBuildingPlotValue(propDTO.getPlotValueFinal());
				undivpropDTO.setAgriLandTypeId(propDTO.getAgriLandTypeId());
				// ---getting undiverted values

				String[] undivValue = propBO.getundivertedValue(undivpropDTO, propDTO);
				
				propDTO.setGuideLineId(undivValue[0]);
				propDTO.setAgriUndivertedValue(undivValue[1]);
				// ---end of undiverted values
				undivpropDTO.setAgriUndivertedValue(propDTO.getAgriUndivertedValue());
				undivpropDTO.setAgriBuildingConstructArea(propDTO.getConstructedAreaBuilding());
				propForm.getAgriAddBuyerList().add(undivpropDTO);
			}
			if (propDTO.getAgriLandTypeId().equalsIgnoreCase("6")) {
				PropertyValuationDTO divpropDTO = new PropertyValuationDTO();
				divpropDTO.setAgriLandTypeId(propDTO.getAgriLandTypeId());
				divpropDTO.setAgriTotalDivertedArea(propDTO.getAgriTotalDivertedArea());
				propForm.setSampadaArea(propDTO.getAgriTotalDivertedArea());
				divpropDTO.setAgriTotalDivertedResidentialArea(propDTO.getAgriTotalDivertedResidentialArea()); // --
																												// --
																												// --
																												// --
																												// --
																												// -
				// diverted
				// value
				// from
				// calculation
				// with
				// areas
				// left
				divpropDTO.setAgriTotalDivertedCommercialArea(propDTO.getAgriTotalDivertedCommercialArea());
				divpropDTO.setAgriTotalDivertedIndustrialArea(propDTO.getAgriTotalDivertedIndustrialArea());
				divpropDTO.setAgriTotalDivertedEducationalArea(propDTO.getAgriTotalDivertedEducationalArea());
				divpropDTO.setAgriTotalDivertedHealthArea(propDTO.getAgriTotalDivertedHealthArea());
				divpropDTO.setAgriTotalDivertedOthersArea(propDTO.getAgriTotalDivertedOthersArea());
				divpropDTO.setIsAgriTncpEducational(propDTO.getIsAgriTncpEducational());
				divpropDTO.setIsAgriTncpHealth(propDTO.getIsAgriTncpHealth());
				divpropDTO.setAgriBuildingPlotValue(propDTO.getPlotValueFinal());
				divpropDTO.setAgriBuildingIndivConstValue(propDTO.getConstructionCostFinal());
				// ---getting diverted values
				String[] divValue = propBO.getdivertedValue(divpropDTO, propDTO);
				
				propDTO.setGuideLineId(divValue[0]);
				propDTO.setAgriDivertedValue(divValue[1]);
				divpropDTO.setAgriDivertedValue(propDTO.getAgriDivertedValue());
				// ---end of undiverted values

				divpropDTO.setAgriDivertedValue(propDTO.getAgriDivertedValue());
				divpropDTO.setIsConstruction(propDTO.getIsConstruction());
				if ("construct".equalsIgnoreCase(divpropDTO.getIsConstruction()))

				{
					divpropDTO.setAgriBuildingConstructTxnId(propDTO.getBuildingTxnIdAgri());
					divpropDTO.setAgriBuildingConstructCost(propDTO.getConstructedValueBuilding());
				}
				divpropDTO.setAgriBuildingConstructArea(propDTO.getConstructedAreaBuilding());
				propForm.getAgriAddBuyerList().add(divpropDTO);
			}

			if (propDTO.getAgriLandTypeId().equalsIgnoreCase("7")) {
				PropertyValuationDTO bothpropDTO = new PropertyValuationDTO();
				bothpropDTO.setAgriLandTypeId(propDTO.getAgriLandTypeId());
				bothpropDTO.setAgriTotalUnDivertedArea(propDTO.getAgriTotalUnDivertedArea());
				bothpropDTO.setAgriUndivSingleCropArea(propDTO.getAgriUndivSingleCropArea());
				bothpropDTO.setAgriUndivDoubleCropArea(propDTO.getAgriUndivDoubleCropArea());
				bothpropDTO.setAgriUndivIrrigatedArea(propDTO.getAgriUndivIrrigatedArea());
				bothpropDTO.setIsConstruction(propDTO.getIsConstruction());
				bothpropDTO.setAgriBuildingPlotValue(propDTO.getPlotValueFinal());
				bothpropDTO.setAgriBuildingIndivConstValue(propDTO.getConstructionCostFinal());
				if ("construct".equalsIgnoreCase(bothpropDTO.getIsConstruction()))

				{
					bothpropDTO.setAgriBuildingConstructTxnId(propDTO.getBuildingTxnIdAgri());
					bothpropDTO.setAgriBuildingConstructCost(propDTO.getConstructedValueBuilding());
				}

				bothpropDTO.setAgriTotalDivertedArea(propDTO.getAgriTotalDivertedArea());

				if (propDTO.getClrFlag() != null && !propDTO.getClrFlag().isEmpty()) {
					if (propDTO.getClrFlag().equalsIgnoreCase("y")) {
						double totUndiv = Double.parseDouble(propDTO.getAgriTotalUnDivertedArea());
						double totdiv = Double.parseDouble(propDTO.getAgriTotalDivertedArea());
						double area = totUndiv + totdiv;
						DecimalFormat formate1 = new DecimalFormat("0.000");
						propForm.setSampadaArea(String.valueOf(formate1.format(area)));
					}
				}

				bothpropDTO.setAgriTotalDivertedResidentialArea(propDTO.getAgriTotalDivertedResidentialArea()); // --
																												// --
																												// --
																												// --
																												// --
																												// -
				// diverted
				// value
				// from
				// calculation
				// with
				// areas
				// left
				bothpropDTO.setAgriTotalDivertedCommercialArea(propDTO.getAgriTotalDivertedCommercialArea());
				bothpropDTO.setAgriTotalDivertedIndustrialArea(propDTO.getAgriTotalDivertedIndustrialArea());
				bothpropDTO.setAgriTotalDivertedEducationalArea(propDTO.getAgriTotalDivertedEducationalArea());
				bothpropDTO.setAgriTotalDivertedHealthArea(propDTO.getAgriTotalDivertedHealthArea());
				bothpropDTO.setAgriTotalDivertedOthersArea(propDTO.getAgriTotalDivertedOthersArea());
				bothpropDTO.setIsAgriTncpEducational(propDTO.getIsAgriTncpEducational());
				bothpropDTO.setIsAgriTncpHealth(propDTO.getIsAgriTncpHealth());
				bothpropDTO.setAgriBuildingConstructArea(propDTO.getConstructedAreaBuilding());
				String[] bothValue = propBO.getbothValue(bothpropDTO, propDTO);
				

				propDTO.setGuideLineId(bothValue[0]);
				bothpropDTO.setAgriDivertedValue(bothValue[1]);
				bothpropDTO.setAgriUndivertedValue(bothValue[2]);

				logger.info("bothValue 1 : " + bothValue[1]);
				logger.info("bothValue 2 : " + bothValue[2]);

				propForm.getAgriAddBuyerList().add(bothpropDTO);
			}

			int count = propDTO.getAgriNoOfBuyersCheck();

			count++;
			if (count == Integer.valueOf(propDTO.getNoOfBuyers())) {

				propDTO.setAgriAddPropButtonCheck(1);
				propDTO.setAgriShowBuyerCheck("Hide");

			} else {
				propDTO.setAgriAddPropButtonCheck(0);
				propDTO.setAgriShowBuyerCheck("Hide");
			}

			propDTO.setAgriNoOfBuyersCheck(count);
			// ---reset the all properties to be done
			propDTO.setAgriUndivIrrigatedArea("");
			propDTO.setAgriUndivDoubleCropArea("");
			propDTO.setAgriUndivSingleCropArea("");
			propDTO.setAgriTotalUnDivertedArea("");
			propDTO.setIsConstruction("");
			propDTO.setAgriTotalDivertedArea("");
			propDTO.setAgriTotalDivertedResidentialArea("");
			propDTO.setAgriTotalDivertedCommercialArea("");
			propDTO.setAgriTotalDivertedIndustrialArea("");
			propDTO.setAgriTotalDivertedEducationalArea("");
			propDTO.setConstructedValueBuilding("0.0");
			propDTO.setAgriTotalDivertedHealthArea("");
			propDTO.setAgriTotalDivertedOthersArea("");
			propDTO.setIsAgriTncpEducational("");
			propDTO.setIsAgriTncpHealth("");
			propDTO.setAgriDivertedValue("");
			propDTO.setAgriUndivertedValue("");

			FORWARD_JSP = "AGRILAND";

			/*
			 * if (propDTO.getIsPropDiffPustika().equalsIgnoreCase("YES")) {
			 * FORWARD_JSP="MAP"; }
			 * 
			 * else
			 * 
			 * { FORWARD_JSP="AGRILAND"; }
			 */
		}

		else if (propForm.getActionName().equalsIgnoreCase("addMorePropertyDiffRinPustika")) {

			propDTO.setPropertyId("3");
			propDTO.setPropertyName("3");
			propDTO.setFromAgri("YES");
			if (propDTO.getAgriLandTypeId().equalsIgnoreCase("5")) {
				PropertyValuationDTO undivpropDTO = new PropertyValuationDTO();
				undivpropDTO.setAgriTotalUnDivertedArea(propDTO.getAgriTotalUnDivertedArea());
				propForm.setSampadaArea(propDTO.getAgriTotalUnDivertedArea());
				undivpropDTO.setAgriUndivSingleCropArea(propDTO.getAgriUndivSingleCropArea());
				undivpropDTO.setAgriUndivDoubleCropArea(propDTO.getAgriUndivDoubleCropArea());
				undivpropDTO.setAgriUndivIrrigatedArea(propDTO.getAgriUndivIrrigatedArea());
				undivpropDTO.setIsConstruction(propDTO.getIsConstruction());
				undivpropDTO.setAgriBuildingPlotValue(propDTO.getPlotValueFinal());
				undivpropDTO.setAgriBuildingIndivConstValue(propDTO.getConstructionCostFinal());
				if ("construct".equalsIgnoreCase(undivpropDTO.getIsConstruction()))

				{
					undivpropDTO.setAgriBuildingConstructTxnId(propDTO.getBuildingTxnIdAgri());
					undivpropDTO.setAgriBuildingConstructCost(propDTO.getConstructedValueBuilding());
				}

				undivpropDTO.setAgriLandTypeId(propDTO.getAgriLandTypeId());
				// ---getting undiverted values

				String[] undivValue = propBO.getundivertedValue(undivpropDTO, propDTO);
				

				propDTO.setGuideLineId(undivValue[0]);
				propDTO.setAgriUndivertedValue(undivValue[1]);
				undivpropDTO.setAgriUndivertedValue(propDTO.getAgriUndivertedValue());

				// ---end of undiverted values
				undivpropDTO.setAgriBuildingConstructArea(propDTO.getConstructedAreaBuilding());
				propForm.getAgriAddBuyerList().add(undivpropDTO);
			}
			if (propDTO.getAgriLandTypeId().equalsIgnoreCase("6")) {
				PropertyValuationDTO divpropDTO = new PropertyValuationDTO();
				divpropDTO.setAgriLandTypeId(propDTO.getAgriLandTypeId());
				divpropDTO.setAgriTotalDivertedArea(propDTO.getAgriTotalDivertedArea());
				propForm.setSampadaArea(propDTO.getAgriTotalDivertedArea());
				divpropDTO.setAgriTotalDivertedResidentialArea(propDTO.getAgriTotalDivertedResidentialArea()); // --
																												// --
																												// --
																												// --
																												// --
																												// -
				// diverted
				// value
				// from
				// calculation
				// with
				// areas
				// left
				divpropDTO.setAgriTotalDivertedCommercialArea(propDTO.getAgriTotalDivertedCommercialArea());
				divpropDTO.setAgriTotalDivertedIndustrialArea(propDTO.getAgriTotalDivertedIndustrialArea());
				divpropDTO.setAgriTotalDivertedEducationalArea(propDTO.getAgriTotalDivertedEducationalArea());
				divpropDTO.setAgriTotalDivertedHealthArea(propDTO.getAgriTotalDivertedHealthArea());
				divpropDTO.setAgriTotalDivertedOthersArea(propDTO.getAgriTotalDivertedOthersArea());
				divpropDTO.setIsAgriTncpEducational(propDTO.getIsAgriTncpEducational());
				divpropDTO.setIsAgriTncpHealth(propDTO.getIsAgriTncpHealth());
				divpropDTO.setAgriBuildingPlotValue(propDTO.getPlotValueFinal());
				divpropDTO.setAgriBuildingIndivConstValue(propDTO.getConstructionCostFinal());
				// ---getting diverted values
				String[] divValue = propBO.getdivertedValue(divpropDTO, propDTO);
				
				propDTO.setGuideLineId(divValue[0]);
				propDTO.setAgriDivertedValue(divValue[1]);
				divpropDTO.setAgriDivertedValue(propDTO.getAgriDivertedValue());
				// ---end of undiverted values

				divpropDTO.setIsConstruction(propDTO.getIsConstruction());
				if ("construct".equalsIgnoreCase(divpropDTO.getIsConstruction()))

				{
					divpropDTO.setAgriBuildingConstructTxnId(propDTO.getBuildingTxnIdAgri());
					divpropDTO.setAgriBuildingConstructCost(propDTO.getConstructedValueBuilding());
				}
				divpropDTO.setAgriBuildingConstructArea(propDTO.getConstructedAreaBuilding());
				propForm.getAgriAddBuyerList().add(divpropDTO);
			}

			if (propDTO.getAgriLandTypeId().equalsIgnoreCase("7")) {
				PropertyValuationDTO bothpropDTO = new PropertyValuationDTO();
				bothpropDTO.setAgriLandTypeId(propDTO.getAgriLandTypeId());
				bothpropDTO.setAgriTotalUnDivertedArea(propDTO.getAgriTotalUnDivertedArea());
				bothpropDTO.setAgriUndivSingleCropArea(propDTO.getAgriUndivSingleCropArea());
				bothpropDTO.setAgriUndivDoubleCropArea(propDTO.getAgriUndivDoubleCropArea());
				bothpropDTO.setAgriUndivIrrigatedArea(propDTO.getAgriUndivIrrigatedArea());
				bothpropDTO.setIsConstruction(propDTO.getIsConstruction());
				bothpropDTO.setAgriBuildingPlotValue(propDTO.getPlotValueFinal());
				bothpropDTO.setAgriBuildingIndivConstValue(propDTO.getConstructionCostFinal());
				if ("construct".equalsIgnoreCase(bothpropDTO.getIsConstruction()))

				{
					bothpropDTO.setAgriBuildingConstructTxnId(propDTO.getBuildingTxnIdAgri());
					bothpropDTO.setAgriBuildingConstructCost(propDTO.getConstructedValueBuilding());
				}
				bothpropDTO.setAgriTotalDivertedArea(propDTO.getAgriTotalDivertedArea());

				if (propDTO.getClrFlag() != null && !propDTO.getClrFlag().isEmpty()) {
					if (propDTO.getClrFlag().equalsIgnoreCase("y")) {
						double totUndiv = Double.parseDouble(propDTO.getAgriTotalUnDivertedArea());
						double totdiv = Double.parseDouble(propDTO.getAgriTotalDivertedArea());
						double area = totUndiv + totdiv;
						DecimalFormat formate1 = new DecimalFormat("0.000");
						propForm.setSampadaArea(String.valueOf(formate1.format(area)));
					}
				}
				// Updated by Rakesh : End

				bothpropDTO.setAgriTotalDivertedResidentialArea(propDTO.getAgriTotalDivertedResidentialArea()); // --
																												// --
																												// --
																												// --
																												// --
																												// -
				// diverted
				// value
				// from
				// calculation
				// with
				// areas
				// left
				bothpropDTO.setAgriTotalDivertedCommercialArea(propDTO.getAgriTotalDivertedCommercialArea());
				bothpropDTO.setAgriTotalDivertedIndustrialArea(propDTO.getAgriTotalDivertedIndustrialArea());
				bothpropDTO.setAgriTotalDivertedEducationalArea(propDTO.getAgriTotalDivertedEducationalArea());
				bothpropDTO.setAgriTotalDivertedHealthArea(propDTO.getAgriTotalDivertedHealthArea());
				bothpropDTO.setAgriTotalDivertedOthersArea(propDTO.getAgriTotalDivertedOthersArea());
				bothpropDTO.setIsAgriTncpEducational(propDTO.getIsAgriTncpEducational());
				bothpropDTO.setIsAgriTncpHealth(propDTO.getIsAgriTncpHealth());
				bothpropDTO.setAgriBuildingConstructArea(propDTO.getConstructedAreaBuilding());

				/*
				 * doubledivertedArea=Double.parseDouble(bothpropDTO.
				 * getAgriTotalDivertedArea()); double
				 * constructedArea=Double.parseDouble
				 * (propDTO.getConstructedAreaBuilding())/10000;
				 */

				String[] bothValue = propBO.getbothValue(bothpropDTO, propDTO);
				logger.info("bothValue 1 2: " + bothValue[1]);
				logger.info("bothValue 2 2: " + bothValue[2]);
				
				propDTO.setGuideLineId(bothValue[0]);
				bothpropDTO.setAgriDivertedValue(bothValue[1]);
				bothpropDTO.setAgriUndivertedValue(bothValue[2]);

				propForm.getAgriAddBuyerList().add(bothpropDTO);
			}
			// propForm.getAgriAddBuyerList().add(propDTO);
			String[] TreeGreater45 = request.getParameterValues("noOfSubTreeTypeGrt45");
			String[] TreeLesser45 = request.getParameterValues("noOfSubTreeTypeLes45");
			TreeDTO dto = new TreeDTO();
			if (TreeGreater45 != null && TreeGreater45.length > 0) {
				dto.setSagunTree(TreeGreater45[0]);
				dto.setSaalTree(TreeGreater45[1]);
				dto.setFruitTree(TreeGreater45[2]);

			}
			if (TreeLesser45 != null && TreeLesser45.length > 0) {
				dto.setLess45Tree(TreeLesser45[0]);

			}
			propDTO.setAgriAddPropButtonCheck(0);

			String[] totalTreeValue = propBO.getTotalTreeValue(dto, propDTO);
			propDTO.setAgriTotalTreeValue(totalTreeValue[0]);

			// ----inserting first time mainTxn ID with null values--

			if (propDTO.getAgriMainValTxnId() == null || propDTO.getAgriMainValTxnId().equalsIgnoreCase("")) {

				String InsertMainTxnId = propBO.insertMainTxnId(language, userId);
				propForm.getPropDTO().setAgriMainValTxnId(InsertMainTxnId);
				propForm.getPropDTO().setValuationId(InsertMainTxnId);

			}

			// ----inserting user data in database---

			boolean Insert = propBO.insertAgriBuyerTreeDetails(dto, propForm, language, userId);

			// ------end of total insertion---

			if (Insert = true) {

				logger.debug("inserted");

			}

			else {
				logger.debug("total Fail");

			}

			boolean update = propBO.updateGuidelineMV(dto, propForm, language, userId);
			propBO.insertSubclauseValues(propForm.getPropDTO());
			if (update == true) {
				logger.debug("updated GUIDELINE_MV");
			}

			else {
				logger.debug("cannot updated GUIDELINE_MV");
			}

			propForm.setAgriAddBuyerList(new ArrayList<PropertyValuationDTO>());
			propForm.setAgriTreeList(new ArrayList<TreeDTO>());
			propDTO.setAgriShowBuyerCheck("Show");
			propDTO.setAgriNoOfBuyersCheck(1);
			propDTO.setAgriUndivIrrigatedArea("");
			propDTO.setAgriUndivDoubleCropArea("");
			propDTO.setAgriUndivSingleCropArea("");
			propDTO.setAgriTotalUnDivertedArea("");
			propDTO.setIsConstruction("");
			propDTO.setAgriTotalDivertedArea("");
			propDTO.setAgriTotalDivertedResidentialArea("");
			propDTO.setAgriTotalDivertedCommercialArea("");
			propDTO.setAgriTotalDivertedIndustrialArea("");
			propDTO.setAgriTotalDivertedEducationalArea("");
			propDTO.setAgriTotalDivertedHealthArea("");
			propDTO.setAgriTotalDivertedOthersArea("");
			propDTO.setIsAgriTncpEducational("");
			propDTO.setIsAgriTncpHealth("");
			propDTO.setAgriDivertedValue("");
			propDTO.setAgriUndivertedValue("");
			FORWARD_JSP = "MAP";

		}

		else if (propForm.getActionName().equalsIgnoreCase("getFinalMarketValue")) {
			logger.info(" ***** propDTO.getAgriLandTypeId() : " + propDTO.getAgriLandTypeId());
			if (propDTO.getAgriLandTypeId().equalsIgnoreCase("5")) {
				PropertyValuationDTO undivpropDTO = new PropertyValuationDTO();
				undivpropDTO.setAgriTotalUnDivertedArea(propDTO.getAgriTotalUnDivertedArea());
				propForm.setSampadaArea(undivpropDTO.getAgriTotalUnDivertedArea());
				undivpropDTO.setAgriUndivSingleCropArea(propDTO.getAgriUndivSingleCropArea());
				undivpropDTO.setAgriUndivDoubleCropArea(propDTO.getAgriUndivDoubleCropArea());
				undivpropDTO.setAgriUndivIrrigatedArea(propDTO.getAgriUndivIrrigatedArea());
				undivpropDTO.setIsConstruction(propDTO.getIsConstruction());
				undivpropDTO.setAgriBuildingPlotValue(propDTO.getPlotValueFinal());
				undivpropDTO.setAgriBuildingIndivConstValue(propDTO.getConstructionCostFinal());
				if ("construct".equalsIgnoreCase(undivpropDTO.getIsConstruction()))

				{
					undivpropDTO.setAgriBuildingConstructTxnId(propDTO.getBuildingTxnIdAgri());
					undivpropDTO.setAgriBuildingConstructCost(propDTO.getConstructedValueBuilding());

					undivpropDTO.setIsConstructionKhasraNo(propDTO.getIsConstructionKhasraNo() != null ? propDTO.getIsConstructionKhasraNo() : "");
				}

				undivpropDTO.setAgriLandTypeId(propDTO.getAgriLandTypeId());
				// ---getting undiverted values

				String[] undivValue = propBO.getundivertedValue(undivpropDTO, propDTO);
				
				propDTO.setGuideLineId(undivValue[0]);
				propDTO.setAgriUndivertedValue(undivValue[1]);
				// ---end of undiverted values

				undivpropDTO.setAgriUndivertedValue(propDTO.getAgriUndivertedValue());
				undivpropDTO.setAgriBuildingConstructArea(propDTO.getConstructedAreaBuilding());
				propForm.getAgriAddBuyerList().add(undivpropDTO);
			}
			if (propDTO.getAgriLandTypeId().equalsIgnoreCase("6")) {
				PropertyValuationDTO divpropDTO = new PropertyValuationDTO();
				divpropDTO.setAgriLandTypeId(propDTO.getAgriLandTypeId());
				divpropDTO.setAgriTotalDivertedArea(propDTO.getAgriTotalDivertedArea());
				propForm.setSampadaArea(propDTO.getAgriTotalDivertedArea());
				divpropDTO.setAgriTotalDivertedResidentialArea(propDTO.getAgriTotalDivertedResidentialArea()); // --
																												// --
																												// --
																												// --
																												// --
																												// -
				// diverted
				// value
				// from
				// calculation
				// with
				// areas
				// left
				divpropDTO.setAgriTotalDivertedCommercialArea(propDTO.getAgriTotalDivertedCommercialArea());
				divpropDTO.setAgriTotalDivertedIndustrialArea(propDTO.getAgriTotalDivertedIndustrialArea());
				divpropDTO.setAgriTotalDivertedEducationalArea(propDTO.getAgriTotalDivertedEducationalArea());
				divpropDTO.setAgriTotalDivertedHealthArea(propDTO.getAgriTotalDivertedHealthArea());
				divpropDTO.setAgriTotalDivertedOthersArea(propDTO.getAgriTotalDivertedOthersArea());
				divpropDTO.setIsAgriTncpEducational(propDTO.getIsAgriTncpEducational());
				divpropDTO.setIsAgriTncpHealth(propDTO.getIsAgriTncpHealth());
				divpropDTO.setAgriBuildingPlotValue(propDTO.getPlotValueFinal());
				divpropDTO.setAgriBuildingIndivConstValue(propDTO.getConstructionCostFinal());
				// ---getting diverted values
				String[] divValue = propBO.getdivertedValue(divpropDTO, propDTO);
				propDTO.setGuideLineId(divValue[0]);
				propDTO.setAgriDivertedValue(divValue[1]);
				divpropDTO.setAgriDivertedValue(propDTO.getAgriDivertedValue());
				// ---end of undiverted values

				divpropDTO.setAgriDivertedValue(propDTO.getAgriDivertedValue());
				divpropDTO.setIsConstruction(propDTO.getIsConstruction());
				if ("construct".equalsIgnoreCase(divpropDTO.getIsConstruction()))

				{
					divpropDTO.setAgriBuildingConstructTxnId(propDTO.getBuildingTxnIdAgri());
					divpropDTO.setAgriBuildingConstructCost(propDTO.getConstructedValueBuilding());

					divpropDTO.setIsConstructionKhasraNo(propDTO.getIsConstructionKhasraNo() != null ? propDTO.getIsConstructionKhasraNo() : "");
				}
				divpropDTO.setAgriBuildingConstructArea(propDTO.getConstructedAreaBuilding());
				propForm.getAgriAddBuyerList().add(divpropDTO);
			}

			if (propDTO.getAgriLandTypeId().equalsIgnoreCase("7")) {
				PropertyValuationDTO bothpropDTO = new PropertyValuationDTO();
				bothpropDTO.setAgriLandTypeId(propDTO.getAgriLandTypeId());
				bothpropDTO.setAgriTotalUnDivertedArea(propDTO.getAgriTotalUnDivertedArea());
				bothpropDTO.setAgriUndivSingleCropArea(propDTO.getAgriUndivSingleCropArea());
				bothpropDTO.setAgriUndivDoubleCropArea(propDTO.getAgriUndivDoubleCropArea());
				bothpropDTO.setAgriUndivIrrigatedArea(propDTO.getAgriUndivIrrigatedArea());
				bothpropDTO.setIsConstruction(propDTO.getIsConstruction());
				bothpropDTO.setAgriBuildingPlotValue(propDTO.getPlotValueFinal());
				bothpropDTO.setAgriBuildingIndivConstValue(propDTO.getConstructionCostFinal());
				if ("construct".equalsIgnoreCase(bothpropDTO.getIsConstruction()))

				{
					bothpropDTO.setAgriBuildingConstructTxnId(propDTO.getBuildingTxnIdAgri());
					bothpropDTO.setAgriBuildingConstructCost(propDTO.getConstructedValueBuilding());
					bothpropDTO.setIsConstructionKhasraNo(propDTO.getIsConstructionKhasraNo() != null ? propDTO.getIsConstructionKhasraNo() : "");
				}
				bothpropDTO.setAgriTotalDivertedArea(propDTO.getAgriTotalDivertedArea());

				// Updated by Rakesh: Start
				if (propDTO.getClrFlag() != null && !propDTO.getClrFlag().isEmpty()) {
					if (propDTO.getClrFlag().equalsIgnoreCase("y")) {
						double totUndiv = Double.parseDouble(propDTO.getAgriTotalUnDivertedArea());
						double totdiv = Double.parseDouble(propDTO.getAgriTotalDivertedArea());
						double area = totUndiv + totdiv;
						DecimalFormat formate1 = new DecimalFormat("0.000");
						propForm.setSampadaArea(String.valueOf(formate1.format(area)));
					}
				}
				// Updated by Rakesh : End

				bothpropDTO.setAgriTotalDivertedResidentialArea(propDTO.getAgriTotalDivertedResidentialArea()); // --
																												// --
																												// --
																												// --
																												// --
																												// -
				// diverted
				// value
				// from
				// calculation
				// with
				// areas
				// left
				bothpropDTO.setAgriTotalDivertedCommercialArea(propDTO.getAgriTotalDivertedCommercialArea());
				bothpropDTO.setAgriTotalDivertedIndustrialArea(propDTO.getAgriTotalDivertedIndustrialArea());
				bothpropDTO.setAgriTotalDivertedEducationalArea(propDTO.getAgriTotalDivertedEducationalArea());
				bothpropDTO.setAgriTotalDivertedHealthArea(propDTO.getAgriTotalDivertedHealthArea());
				bothpropDTO.setAgriTotalDivertedOthersArea(propDTO.getAgriTotalDivertedOthersArea());
				bothpropDTO.setIsAgriTncpEducational(propDTO.getIsAgriTncpEducational());
				bothpropDTO.setIsAgriTncpHealth(propDTO.getIsAgriTncpHealth());
				bothpropDTO.setAgriBuildingConstructArea(propDTO.getConstructedAreaBuilding());

				/*
				 * doubledivertedArea=Double.parseDouble(bothpropDTO.
				 * getAgriTotalDivertedArea()); double
				 * constructedArea=Double.parseDouble
				 * (propDTO.getConstructedAreaBuilding())/10000;
				 */

				String[] bothValue = propBO.getbothValue(bothpropDTO, propDTO);
				propDTO.setGuideLineId(bothValue[0]);
				bothpropDTO.setAgriDivertedValue(bothValue[1]);
				bothpropDTO.setAgriUndivertedValue(bothValue[2]);

				/*
				 * if(divertedArea>=constructedArea) { String []
				 * divValue=propBO.getdivertedValue(bothpropDTO,propDTO);
				 * propDTO.setGuideLineId(divValue[0]);
				 * propDTO.setAgriDivertedValue(divValue[1]);
				 * bothpropDTO.setAgriDivertedValue
				 * (propDTO.getAgriDivertedValue());
				 * propDTO.setConstructedAreaBuilding("0.0"); } else {
				 * constructedArea=(constructedArea-divertedArea)10000;
				 * propDTO.setConstructedAreaBuilding
				 * (String.valueOf(constructedArea));
				 * propDTO.setAgriDivertedValue("0.0");
				 * bothpropDTO.setAgriDivertedValue
				 * (propDTO.getAgriDivertedValue()); }
				 * 
				 * //---end of diverted values
				 */

				/*
				 * //---getting undiverted values String []
				 * undivValue=propBO.getundivertedValue(bothpropDTO,propDTO);
				 * 
				 * propDTO.setAgriUndivertedValue(undivValue[1]); //---end of
				 * undiverted values
				 */

				propForm.getAgriAddBuyerList().add(bothpropDTO);

			}

			String[] TreeGreater45 = request.getParameterValues("noOfSubTreeTypeGrt45");
			String[] TreeLesser45 = request.getParameterValues("noOfSubTreeTypeLes45");
			TreeDTO dto = new TreeDTO();
			if (TreeGreater45 != null && TreeGreater45.length > 0) {
				dto.setSagunTree(TreeGreater45[0]);
				dto.setSaalTree(TreeGreater45[1]);
				dto.setFruitTree(TreeGreater45[2]);

			}
			if (TreeLesser45 != null && TreeLesser45.length > 0) {
				dto.setLess45Tree(TreeLesser45[0]);

			}

			String[] totalTreeValue = propBO.getTotalTreeValue(dto, propDTO);
			propDTO.setAgriTotalTreeValue(totalTreeValue[0]);

			propDTO.setAgriAddPropButtonCheck(0);

			// ----inserting first time mainTxn ID with null values--

			if (propDTO.getAgriMainValTxnId() == null || propDTO.getAgriMainValTxnId().equalsIgnoreCase("")) {

				String InsertMainTxnId = propBO.insertMainTxnId(language, userId);
				propForm.getPropDTO().setAgriMainValTxnId(InsertMainTxnId);
				// ----setting property for displaying Main Valuation Id on
				// final page----
				propForm.getPropDTO().setValuationId(InsertMainTxnId);
				// -----end of setup

			}

			// ----inserting user data in database---

			boolean Insert = propBO.insertAgriBuyerTreeDetails(dto, propForm, language, userId);

			// ------end of total insertion---

			if (Insert = true) {

				logger.debug("inserted");

			}

			else {
				logger.debug("total Fail");

			}

			boolean update = propBO.updateGuidelineMV(dto, propForm, language, userId);

			if (update = true) {

				logger.debug("updated GuidelineMV");

			}

			else {
				logger.debug("cannot updated GuidelineMV");

			}

			String FinalMarketValue = propBO.updateFinalMarketValue(dto, propForm, language, userId);
			double finalMV = Double.parseDouble(FinalMarketValue);
			finalMV = Math.ceil(finalMV);
			FinalMarketValue = BigDecimal.valueOf(finalMV).toPlainString();
			// FinalMarketValue=String.valueOf(finalMV);

			// ----insertion of selected subclauses-----
			// propBO.insertSubclauseValues(propForm.getPropDTO());
			// ----insertion of selected subclauses-----
			propDTO.setAgriTotalPropertyList(propBO.getAgriTotalPropertyList(propDTO.getAgriMainValTxnId()));

			// ---addition for property view on the final screen

			propDTO.setAgriRefrncIdFinalMVList(propBO.getAgriRefrncIdFinalMVList(propDTO.getAgriMainValTxnId()));

			// ---end of addition

			if (FinalMarketValue != null && !FinalMarketValue.equalsIgnoreCase("")) {
				logger.debug("TOTAL SUCCESS");
			}

			propForm.getPropDTO().setPlotMarketValue(FinalMarketValue);
			logger.debug(propForm.getPropDTO().getValuationId());
			propForm.setAgriAddBuyerList(new ArrayList<PropertyValuationDTO>());
			propForm.setAgriTreeList(new ArrayList<TreeDTO>());
			propDTO.setAgriShowBuyerCheck("Show");
			propDTO.setAgriNoOfBuyersCheck(1);
			propDTO.setAgriUndivIrrigatedArea("");
			propDTO.setAgriUndivDoubleCropArea("");
			propDTO.setAgriUndivSingleCropArea("");
			propDTO.setAgriTotalUnDivertedArea("");
			propDTO.setIsConstruction("");
			propDTO.setAgriTotalDivertedArea("");
			propDTO.setAgriTotalDivertedResidentialArea("");
			propDTO.setAgriTotalDivertedCommercialArea("");
			propDTO.setAgriTotalDivertedIndustrialArea("");
			propDTO.setAgriTotalDivertedEducationalArea("");
			propDTO.setAgriTotalDivertedHealthArea("");
			propDTO.setAgriTotalDivertedOthersArea("");
			propDTO.setIsAgriTncpEducational("");
			propDTO.setIsAgriTncpHealth("");
			propDTO.setAgriDivertedValue("");
			propDTO.setAgriUndivertedValue("");
			propDTO.setAgriMainValTxnId("");
			logger.debug(propForm.getPropDTO().getValuationId());
			FORWARD_JSP = "finalMarketValue";
		}

		else if (propForm.getActionName().equalsIgnoreCase("getFinalMarketValueToDisplayForSampada")) {

			logger.info(" ***** propDTO.getAgriLandTypeId() : " + propDTO.getAgriLandTypeId());

			if (propDTO.getAgriLandTypeId().equalsIgnoreCase("5")) {
				/* if(propForm.getActionNameSetValue().equalsIgnoreCase("1")){ */
				DecimalFormat df = new DecimalFormat("#.#####");

				SampadaKhasraClrDTO sampadaDTO = new SampadaKhasraClrDTO();
				// CLR DATA
				sampadaDTO.setCensusCode(propDTO.getCensusCode());
				sampadaDTO.setKhasraNoClr(propForm.getClrDto().getKhasraNoClr());
				sampadaDTO.setClrKhasraId(propForm.getClrDto().getClrKhasraId());
				sampadaDTO.setKhasraTotalArea(propForm.getClrDto().getKhasraTotalArea());
				sampadaDTO.setDoubleCropArea(propForm.getClrDto().getDoubleCropArea());
				sampadaDTO.setSingleCropArea(propForm.getClrDto().getSingleCropArea());
				sampadaDTO.setIrrigatedArea(propForm.getClrDto().getIrrigatedArea());
				sampadaDTO.setNonIrrigatedArea(propForm.getClrDto().getNonIrrigatedArea());
				sampadaDTO.setPadtiArea(propForm.getClrDto().getPadtiArea());
				sampadaDTO.setLandIrriOrNot(propForm.getClrDto().getLandIrriOrNot());
				sampadaDTO.setLandUnIrriOrNot(propForm.getClrDto().getLandUnIrriOrNot());
				sampadaDTO.setPadtiFlag(propForm.getClrDto().getPadtiFlag());
				// sampadaDTO.setLandUnirriDouble(landUnirriDouble);
				sampadaDTO.setCurrentStatus(propForm.getClrDto().getCurrentStatus());
				sampadaDTO.setMortgage(propForm.getClrDto().getMortgage());
				sampadaDTO.setNohiyat(propForm.getClrDto().getNohiyat());
				sampadaDTO.setKehfiyat(propForm.getClrDto().getKehfiyat());
				sampadaDTO.setOwnership(propForm.getClrDto().getOwnership());
				sampadaDTO.setClrFlag(propForm.getClrDto().getClrFlag());

				// Sampada Data
				sampadaDTO.setSampadaDoubleCrop(Double.valueOf(!propDTO.getAgriUndivDoubleCropArea().isEmpty() ? propDTO.getAgriUndivDoubleCropArea() : "0.0"));
				sampadaDTO.setSampadaIrrigated(Double.valueOf(!propDTO.getAgriUndivIrrigatedArea().isEmpty() ? propDTO.getAgriUndivIrrigatedArea() : "0.0"));
				sampadaDTO.setSampadaSingleCrop(Double.valueOf(!propDTO.getAgriUndivSingleCropArea().isEmpty() ? propDTO.getAgriUndivSingleCropArea() : "0.0"));
				sampadaDTO.setTotalSaleableArea(Double.valueOf(!propDTO.getAgriTotalUnDivertedArea().isEmpty() ? propDTO.getAgriTotalUnDivertedArea() : "0.0"));

				propForm.getSampadaClrKhasraList().add(sampadaDTO);

				propDTO.setTotAgriTotalUnDivertedArea(Double.valueOf(!propDTO.getAgriTotalUnDivertedArea().isEmpty() ? propDTO.getAgriTotalUnDivertedArea() : "0.0") + propDTO.getTotAgriTotalUnDivertedArea());
				propDTO.setTotAgriUndivIrrigatedArea(Double.valueOf(!propDTO.getAgriUndivIrrigatedArea().isEmpty() ? propDTO.getAgriUndivIrrigatedArea() : "0.0") + propDTO.getTotAgriUndivIrrigatedArea());
				propDTO.setTotAgriUndivDoubleCropArea(Double.valueOf(!propDTO.getAgriUndivDoubleCropArea().isEmpty() ? propDTO.getAgriUndivDoubleCropArea() : "0.0") + propDTO.getTotAgriUndivDoubleCropArea());
				propDTO.setTotAgriUndivSingleCropArea(Double.valueOf(!propDTO.getAgriUndivSingleCropArea().isEmpty() ? propDTO.getAgriUndivSingleCropArea() : "0.0") + propDTO.getTotAgriUndivSingleCropArea());

				propDTO.setAgriTotalUnDivertedArea(String.valueOf(Math.round(propDTO.getTotAgriTotalUnDivertedArea() * 100000d) / 100000d));
				propDTO.setAgriUndivIrrigatedArea(String.valueOf(Math.round(propDTO.getTotAgriUndivIrrigatedArea() * 100000d) / 100000d));
				propDTO.setAgriUndivDoubleCropArea(String.valueOf(Math.round(propDTO.getTotAgriUndivDoubleCropArea() * 100000d) / 100000d));
				propDTO.setAgriUndivSingleCropArea(String.valueOf(Math.round(propDTO.getTotAgriUndivSingleCropArea() * 100000d) / 100000d));

				for (int i = 0; i < propForm.getSampadaClrKhasraList().size(); i++) {

					propDTO.setClrTotalKhasraArea(Math.round((Double.valueOf(propForm.getSampadaClrKhasraList().get(i).getKhasraTotalArea()) + propDTO.getClrTotalKhasraArea()) * 100000d) / 100000d);
					propDTO.setClrTotAgriIrrigated(Math.round((Double.valueOf(propForm.getSampadaClrKhasraList().get(i).getIrrigatedArea()) + propDTO.getClrTotAgriIrrigated()) * 100000d) / 100000d);
					propDTO.setClrTotAgriUnirrigated(Math.round((Double.valueOf(propForm.getSampadaClrKhasraList().get(i).getNonIrrigatedArea()) + propDTO.getClrTotAgriUnirrigated()) * 100000d) / 100000d);
					propDTO.setClrTotAgriSingleCropArea(Math.round((Double.valueOf(propForm.getSampadaClrKhasraList().get(i).getSingleCropArea()) + propDTO.getClrTotAgriSingleCropArea()) * 100000d) / 100000d);
					propDTO.setClrTotAgriDoubleCropArea(Math.round((Double.valueOf(propForm.getSampadaClrKhasraList().get(i).getDoubleCropArea()) + propDTO.getClrTotAgriDoubleCropArea()) * 100000d) / 100000d);

				}

				/*
				 * propDTO.setAgriTotalUnDivertedArea("0");
				 * propDTO.setAgriUndivIrrigatedArea("0");
				 * propDTO.setAgriUndivDoubleCropArea("0");
				 * propDTO.setAgriUndivSingleCropArea("0");
				 * propForm.setActionNameSetValue("2");
				 */

				FORWARD_JSP = "AGRILANDUNDIVERTEDCLR";

				/*
				 * else{
				 * 
				 * 
				 * 
				 * 
				 * propDTO.setTotAgriTotalUnDivertedArea(Double.valueOf(!propDTO.
				 * getAgriTotalUnDivertedArea().isEmpty()?
				 * propDTO.getAgriTotalUnDivertedArea() :"0.0")+
				 * propDTO.getTotAgriTotalUnDivertedArea());
				 * propDTO.setTotAgriUndivIrrigatedArea
				 * (Double.valueOf(!propDTO.getAgriUndivIrrigatedArea
				 * ().isEmpty() ? propDTO.getAgriUndivIrrigatedArea() : "0.0") +
				 * propDTO.getTotAgriUndivIrrigatedArea());
				 * propDTO.setTotAgriUndivDoubleCropArea
				 * (Double.valueOf(!propDTO.
				 * getAgriUndivDoubleCropArea().isEmpty()?
				 * propDTO.getAgriUndivDoubleCropArea() : "0.0") +
				 * propDTO.getTotAgriUndivDoubleCropArea() );
				 * propDTO.setTotAgriUndivSingleCropArea
				 * (Double.valueOf(!propDTO.
				 * getAgriUndivSingleCropArea().isEmpty() ?
				 * propDTO.getAgriUndivSingleCropArea() : "0.0" ) +
				 * propDTO.getTotAgriUndivSingleCropArea() );
				 * 
				 * propDTO.setAgriTotalUnDivertedArea(
				 * String.valueOf(propDTO.getTotAgriTotalUnDivertedArea()));
				 * propDTO.setAgriUndivIrrigatedArea(String.valueOf(propDTO.
				 * getTotAgriUndivIrrigatedArea()));
				 * propDTO.setAgriUndivDoubleCropArea
				 * (String.valueOf(propDTO.getTotAgriUndivDoubleCropArea() ));
				 * propDTO.setAgriUndivSingleCropArea(String.valueOf(propDTO.
				 * getTotAgriUndivSingleCropArea()) ); }
				 */

			}

			if (propDTO.getAgriLandTypeId().equalsIgnoreCase("6")) {

				SampadaKhasraClrDTO sampadaDTO = new SampadaKhasraClrDTO();
				// CLR DATA
				sampadaDTO.setCensusCode(propDTO.getCensusCode());
				sampadaDTO.setKhasraNoClr(propForm.getClrDto().getKhasraNoClr());
				sampadaDTO.setClrKhasraId(propForm.getClrDto().getClrKhasraId());
				sampadaDTO.setKhasraTotalArea(propForm.getClrDto().getKhasraTotalArea());
				sampadaDTO.setDoubleCropArea(propForm.getClrDto().getDoubleCropArea());
				sampadaDTO.setSingleCropArea(propForm.getClrDto().getSingleCropArea());
				sampadaDTO.setIrrigatedArea(propForm.getClrDto().getIrrigatedArea());
				sampadaDTO.setNonIrrigatedArea(propForm.getClrDto().getNonIrrigatedArea());
				sampadaDTO.setPadtiArea(propForm.getClrDto().getPadtiArea());
				sampadaDTO.setLandIrriOrNot(propForm.getClrDto().getLandIrriOrNot());
				sampadaDTO.setLandUnIrriOrNot(propForm.getClrDto().getLandUnIrriOrNot());
				sampadaDTO.setPadtiFlag(propForm.getClrDto().getPadtiFlag());
				// sampadaDTO.setLandUnirriDouble(landUnirriDouble);
				sampadaDTO.setCurrentStatus(propForm.getClrDto().getCurrentStatus());
				sampadaDTO.setMortgage(propForm.getClrDto().getMortgage());
				sampadaDTO.setNohiyat(propForm.getClrDto().getNohiyat());
				sampadaDTO.setKehfiyat(propForm.getClrDto().getKehfiyat());
				sampadaDTO.setOwnership(propForm.getClrDto().getOwnership());
				sampadaDTO.setClrFlag(propForm.getClrDto().getClrFlag());

				// Sampada Data
				sampadaDTO.setSampadaTotalDivertedArea(Double.valueOf(!propDTO.getAgriTotalDivertedArea().isEmpty() ? propDTO.getAgriTotalDivertedArea() : "0.0"));
				sampadaDTO.setSampadaTotalDivertedCommercialArea(Double.valueOf(!propDTO.getAgriTotalDivertedCommercialArea().isEmpty() ? propDTO.getAgriTotalDivertedCommercialArea() : "0.0"));
				sampadaDTO.setSampadaTotalDivertedEducationalArea(Double.valueOf(!propDTO.getAgriTotalDivertedEducationalArea().isEmpty() ? propDTO.getAgriTotalDivertedEducationalArea() : "0.0"));
				sampadaDTO.setSampadaTotalDivertedHealthArea(Double.valueOf(!propDTO.getAgriTotalDivertedHealthArea().isEmpty() ? propDTO.getAgriTotalDivertedHealthArea() : "0.0"));
				sampadaDTO.setSampadaTotalDivertedIndustrialArea(Double.valueOf(!propDTO.getAgriTotalDivertedIndustrialArea().isEmpty() ? propDTO.getAgriTotalDivertedIndustrialArea() : "0.0"));
				sampadaDTO.setSampadaTotalDivertedOthersArea(Double.valueOf(!propDTO.getAgriTotalDivertedOthersArea().isEmpty() ? propDTO.getAgriTotalDivertedOthersArea() : "0.0"));
				sampadaDTO.setSampadaTotalDivertedResidentialArea(Double.valueOf(!propDTO.getAgriTotalDivertedResidentialArea().isEmpty() ? propDTO.getAgriTotalDivertedResidentialArea() : "0.0"));

				propForm.getSampadaClrKhasraList().add(sampadaDTO);

				propDTO.setTotDivertedCommercialArea(Double.valueOf(!propDTO.getAgriTotalDivertedCommercialArea().isEmpty() ? propDTO.getAgriTotalDivertedCommercialArea() : "0.0") + propDTO.getTotDivertedCommercialArea());
				propDTO.setTotDivertedEducationalArea(Double.valueOf(!propDTO.getAgriTotalDivertedEducationalArea().isEmpty() ? propDTO.getAgriTotalDivertedEducationalArea() : "0.0") + propDTO.getTotDivertedEducationalArea());
				propDTO.setTotDivertedHealthArea(Double.valueOf(!propDTO.getAgriTotalDivertedHealthArea().isEmpty() ? propDTO.getAgriTotalDivertedHealthArea() : "0.0") + propDTO.getTotDivertedHealthArea());
				propDTO.setTotDivertedIndustrialArea(Double.valueOf(!propDTO.getAgriTotalDivertedIndustrialArea().isEmpty() ? propDTO.getAgriTotalDivertedIndustrialArea() : "0.0") + propDTO.getTotDivertedIndustrialArea());
				propDTO.setTotDivertedOthersArea(Double.valueOf(!propDTO.getAgriTotalDivertedOthersArea().isEmpty() ? propDTO.getAgriTotalDivertedOthersArea() : "0.0") + propDTO.getTotDivertedOthersArea());
				propDTO.setTotDivertedResidentialArea(Double.valueOf(!propDTO.getAgriTotalDivertedResidentialArea().isEmpty() ? propDTO.getAgriTotalDivertedResidentialArea() : "0.0") + propDTO.getTotDivertedResidentialArea());
				propDTO.setTotDivertedArea(Double.valueOf(!propDTO.getAgriTotalDivertedArea().isEmpty() ? propDTO.getAgriTotalDivertedArea() : "0.0") + propDTO.getTotDivertedArea());

				// display on final page un editable
				propDTO.setAgriTotalDivertedCommercialArea(String.valueOf(Math.round(propDTO.getTotDivertedCommercialArea() * 100000d) / 100000d));
				propDTO.setAgriTotalDivertedEducationalArea(String.valueOf(Math.round(propDTO.getTotDivertedEducationalArea() * 100000d) / 100000d));
				propDTO.setAgriTotalDivertedHealthArea(String.valueOf(Math.round(propDTO.getTotDivertedHealthArea() * 100000d) / 100000d));
				propDTO.setAgriTotalDivertedIndustrialArea(String.valueOf(Math.round(propDTO.getTotDivertedIndustrialArea() * 100000d) / 100000d));
				propDTO.setAgriTotalDivertedOthersArea(String.valueOf(Math.round(propDTO.getTotDivertedOthersArea() * 100000d) / 100000d));
				propDTO.setAgriTotalDivertedResidentialArea(String.valueOf(Math.round(propDTO.getTotDivertedResidentialArea() * 100000d) / 100000d));
				propDTO.setAgriTotalDivertedArea(String.valueOf(Math.round(propDTO.getTotDivertedArea() * 100000d) / 100000d));

				for (int i = 0; i < propForm.getSampadaClrKhasraList().size(); i++) {

					propDTO.setClrTotalKhasraArea(Math.round((Double.valueOf(propForm.getSampadaClrKhasraList().get(i).getKhasraTotalArea()) + propDTO.getClrTotalKhasraArea()) * 100000d) / 100000d);
					propDTO.setClrTotAgriIrrigated(Math.round((Double.valueOf(propForm.getSampadaClrKhasraList().get(i).getIrrigatedArea()) + propDTO.getClrTotAgriIrrigated()) * 100000d) / 100000d);
					propDTO.setClrTotAgriUnirrigated(Math.round((Double.valueOf(propForm.getSampadaClrKhasraList().get(i).getNonIrrigatedArea()) + propDTO.getClrTotAgriUnirrigated()) * 100000d) / 100000d);
					propDTO.setClrTotAgriSingleCropArea(Math.round((Double.valueOf(propForm.getSampadaClrKhasraList().get(i).getSingleCropArea()) + propDTO.getClrTotAgriSingleCropArea()) * 100000d) / 100000d);
					propDTO.setClrTotAgriDoubleCropArea(Math.round((Double.valueOf(propForm.getSampadaClrKhasraList().get(i).getDoubleCropArea()) + propDTO.getClrTotAgriDoubleCropArea()) * 100000d) / 100000d);

				}

				FORWARD_JSP = "AGRILANDDIVERTEDCLR";

			}

			if (propDTO.getAgriLandTypeId().equalsIgnoreCase("7")) {

				SampadaKhasraClrDTO sampadaDTO = new SampadaKhasraClrDTO();
				// CLR DATA
				sampadaDTO.setCensusCode(propDTO.getCensusCode());
				sampadaDTO.setKhasraNoClr(propForm.getClrDto().getKhasraNoClr());
				sampadaDTO.setClrKhasraId(propForm.getClrDto().getClrKhasraId());
				sampadaDTO.setKhasraTotalArea(propForm.getClrDto().getKhasraTotalArea());
				sampadaDTO.setDoubleCropArea(propForm.getClrDto().getDoubleCropArea());
				sampadaDTO.setSingleCropArea(propForm.getClrDto().getSingleCropArea());
				sampadaDTO.setIrrigatedArea(propForm.getClrDto().getIrrigatedArea());
				sampadaDTO.setNonIrrigatedArea(propForm.getClrDto().getNonIrrigatedArea());
				sampadaDTO.setPadtiArea(propForm.getClrDto().getPadtiArea());
				sampadaDTO.setLandIrriOrNot(propForm.getClrDto().getLandIrriOrNot());
				sampadaDTO.setLandUnIrriOrNot(propForm.getClrDto().getLandUnIrriOrNot());
				sampadaDTO.setPadtiFlag(propForm.getClrDto().getPadtiFlag());
				// sampadaDTO.setLandUnirriDouble(landUnirriDouble);
				sampadaDTO.setCurrentStatus(propForm.getClrDto().getCurrentStatus());
				sampadaDTO.setMortgage(propForm.getClrDto().getMortgage());
				sampadaDTO.setNohiyat(propForm.getClrDto().getNohiyat());
				sampadaDTO.setKehfiyat(propForm.getClrDto().getKehfiyat());
				sampadaDTO.setOwnership(propForm.getClrDto().getOwnership());
				sampadaDTO.setClrFlag(propForm.getClrDto().getClrFlag());

				// Sampada Data
				sampadaDTO.setSampadaDoubleCrop(Double.valueOf(!propDTO.getAgriUndivDoubleCropArea().isEmpty() ? propDTO.getAgriUndivDoubleCropArea() : "0.0"));
				sampadaDTO.setSampadaIrrigated(Double.valueOf(!propDTO.getAgriUndivIrrigatedArea().isEmpty() ? propDTO.getAgriUndivIrrigatedArea() : "0.0"));
				sampadaDTO.setSampadaSingleCrop(Double.valueOf(!propDTO.getAgriUndivSingleCropArea().isEmpty() ? propDTO.getAgriUndivSingleCropArea() : "0.0"));
				sampadaDTO.setTotalSaleableArea(Double.valueOf(!propDTO.getAgriTotalUnDivertedArea().isEmpty() ? propDTO.getAgriTotalUnDivertedArea() : "0.0"));

				sampadaDTO.setSampadaTotalDivertedArea(Double.valueOf(!propDTO.getAgriTotalDivertedArea().isEmpty() ? propDTO.getAgriTotalDivertedArea() : "0.0"));
				sampadaDTO.setSampadaTotalDivertedCommercialArea(Double.valueOf(!propDTO.getAgriTotalDivertedCommercialArea().isEmpty() ? propDTO.getAgriTotalDivertedCommercialArea() : "0.0"));
				sampadaDTO.setSampadaTotalDivertedEducationalArea(Double.valueOf(!propDTO.getAgriTotalDivertedEducationalArea().isEmpty() ? propDTO.getAgriTotalDivertedEducationalArea() : "0.0"));
				sampadaDTO.setSampadaTotalDivertedHealthArea(Double.valueOf(!propDTO.getAgriTotalDivertedHealthArea().isEmpty() ? propDTO.getAgriTotalDivertedHealthArea() : "0.0"));
				sampadaDTO.setSampadaTotalDivertedIndustrialArea(Double.valueOf(!propDTO.getAgriTotalDivertedIndustrialArea().isEmpty() ? propDTO.getAgriTotalDivertedIndustrialArea() : "0.0"));
				sampadaDTO.setSampadaTotalDivertedOthersArea(Double.valueOf(!propDTO.getAgriTotalDivertedOthersArea().isEmpty() ? propDTO.getAgriTotalDivertedOthersArea() : "0.0"));
				sampadaDTO.setSampadaTotalDivertedResidentialArea(Double.valueOf(!propDTO.getAgriTotalDivertedResidentialArea().isEmpty() ? propDTO.getAgriTotalDivertedResidentialArea() : "0.0"));

				propForm.getSampadaClrKhasraList().add(sampadaDTO);

				propDTO.setTotAgriTotalUnDivertedArea(Double.valueOf(!propDTO.getAgriTotalUnDivertedArea().isEmpty() ? propDTO.getAgriTotalUnDivertedArea() : "0.0") + propDTO.getTotAgriTotalUnDivertedArea());
				propDTO.setTotAgriUndivIrrigatedArea(Double.valueOf(!propDTO.getAgriUndivIrrigatedArea().isEmpty() ? propDTO.getAgriUndivIrrigatedArea() : "0.0") + propDTO.getTotAgriUndivIrrigatedArea());
				propDTO.setTotAgriUndivDoubleCropArea(Double.valueOf(!propDTO.getAgriUndivDoubleCropArea().isEmpty() ? propDTO.getAgriUndivDoubleCropArea() : "0.0") + propDTO.getTotAgriUndivDoubleCropArea());
				propDTO.setTotAgriUndivSingleCropArea(Double.valueOf(!propDTO.getAgriUndivSingleCropArea().isEmpty() ? propDTO.getAgriUndivSingleCropArea() : "0.0") + propDTO.getTotAgriUndivSingleCropArea());

				propDTO.setAgriTotalUnDivertedArea(String.valueOf(Math.round(propDTO.getTotAgriTotalUnDivertedArea() * 100000d) / 100000d));
				propDTO.setAgriUndivIrrigatedArea(String.valueOf(Math.round(propDTO.getTotAgriUndivIrrigatedArea() * 100000d) / 100000d));
				propDTO.setAgriUndivDoubleCropArea(String.valueOf(Math.round(propDTO.getTotAgriUndivDoubleCropArea() * 100000d) / 100000d));
				propDTO.setAgriUndivSingleCropArea(String.valueOf(Math.round(propDTO.getTotAgriUndivSingleCropArea() * 100000d) / 100000d));

				propDTO.setTotDivertedCommercialArea(Double.valueOf(!propDTO.getAgriTotalDivertedCommercialArea().isEmpty() ? propDTO.getAgriTotalDivertedCommercialArea() : "0.0") + propDTO.getTotDivertedCommercialArea());
				propDTO.setTotDivertedEducationalArea(Double.valueOf(!propDTO.getAgriTotalDivertedEducationalArea().isEmpty() ? propDTO.getAgriTotalDivertedEducationalArea() : "0.0") + propDTO.getTotDivertedEducationalArea());
				propDTO.setTotDivertedHealthArea(Double.valueOf(!propDTO.getAgriTotalDivertedHealthArea().isEmpty() ? propDTO.getAgriTotalDivertedHealthArea() : "0.0") + propDTO.getTotDivertedHealthArea());
				propDTO.setTotDivertedIndustrialArea(Double.valueOf(!propDTO.getAgriTotalDivertedIndustrialArea().isEmpty() ? propDTO.getAgriTotalDivertedIndustrialArea() : "0.0") + propDTO.getTotDivertedIndustrialArea());
				propDTO.setTotDivertedOthersArea(Double.valueOf(!propDTO.getAgriTotalDivertedOthersArea().isEmpty() ? propDTO.getAgriTotalDivertedOthersArea() : "0.0") + propDTO.getTotDivertedOthersArea());
				propDTO.setTotDivertedResidentialArea(Double.valueOf(!propDTO.getAgriTotalDivertedResidentialArea().isEmpty() ? propDTO.getAgriTotalDivertedResidentialArea() : "0.0") + propDTO.getTotDivertedResidentialArea());
				propDTO.setTotDivertedArea(Double.valueOf(!propDTO.getAgriTotalDivertedArea().isEmpty() ? propDTO.getAgriTotalDivertedArea() : "0.0") + propDTO.getTotDivertedArea());

				// display on final page un editable

				propDTO.setAgriTotalDivertedCommercialArea(String.valueOf(Math.round(propDTO.getTotDivertedCommercialArea() * 100000d) / 100000d));
				propDTO.setAgriTotalDivertedEducationalArea(String.valueOf(Math.round(propDTO.getTotDivertedEducationalArea() * 100000d) / 100000d));
				propDTO.setAgriTotalDivertedHealthArea(String.valueOf(Math.round(propDTO.getTotDivertedHealthArea() * 100000d) / 100000d));
				propDTO.setAgriTotalDivertedIndustrialArea(String.valueOf(Math.round(propDTO.getTotDivertedIndustrialArea() * 100000d) / 100000d));
				propDTO.setAgriTotalDivertedOthersArea(String.valueOf(Math.round(propDTO.getTotDivertedOthersArea() * 100000d) / 100000d));
				propDTO.setAgriTotalDivertedResidentialArea(String.valueOf(Math.round(propDTO.getTotDivertedResidentialArea() * 100000d) / 100000d));
				propDTO.setAgriTotalDivertedArea(String.valueOf(Math.round(propDTO.getTotDivertedArea() * 100000d) / 100000d));

				for (int i = 0; i < propForm.getSampadaClrKhasraList().size(); i++) {

					propDTO.setClrTotalKhasraArea(Math.round((Double.valueOf(propForm.getSampadaClrKhasraList().get(i).getKhasraTotalArea()) + propDTO.getClrTotalKhasraArea()) * 100000d) / 100000d);
					propDTO.setClrTotAgriIrrigated(Math.round((Double.valueOf(propForm.getSampadaClrKhasraList().get(i).getIrrigatedArea()) + propDTO.getClrTotAgriIrrigated()) * 100000d) / 100000d);
					propDTO.setClrTotAgriUnirrigated(Math.round((Double.valueOf(propForm.getSampadaClrKhasraList().get(i).getNonIrrigatedArea()) + propDTO.getClrTotAgriUnirrigated()) * 100000d) / 100000d);
					propDTO.setClrTotAgriSingleCropArea(Math.round((Double.valueOf(propForm.getSampadaClrKhasraList().get(i).getSingleCropArea()) + propDTO.getClrTotAgriSingleCropArea()) * 100000d) / 100000d);
					propDTO.setClrTotAgriDoubleCropArea(Math.round((Double.valueOf(propForm.getSampadaClrKhasraList().get(i).getDoubleCropArea()) + propDTO.getClrTotAgriDoubleCropArea()) * 100000d) / 100000d);

				}

				FORWARD_JSP = "AGRIBOTHCLR";

			}

		}

		// ----end of addition
		else if (propForm.getActionName().equalsIgnoreCase("buildingTypeSubmit") && propDTO.getBuildingTypeId().equalsIgnoreCase("1")) {
			propDTO.setIndpenBuildPropList(propBO.getIndepenBuildPropType(language, propDTO));
			propDTO.setResiFlag("N");
			propDTO.setCommFlag("N");
			propDTO.setIndFlag("N");
			propDTO.setSchoolFlag("N");
			propDTO.setHealthFlag("N");
			propDTO.setOtherFlag("N");
			propDTO.setResiCommFlag("N");
			propDTO.setOnlyIndustrial("");
			propDTO.setResiCommArea("");
			propDTO.setHousingFlag("");
			propDTO.setHousingCheckFlag("");
			propDTO.setHousingCheck("");
			propDTO.setOlderId(0);
			propDTO.setResiFloorAreaList(new ArrayList<PropertyValuationDTO>());
			propDTO.setCommFloorAreaList(new ArrayList<PropertyValuationDTO>());
			propDTO.setIndFloorAreaList(new ArrayList<PropertyValuationDTO>());
			propDTO.setSchoolFloorAreaList(new ArrayList<PropertyValuationDTO>());
			propDTO.setHealthFloorAreaList(new ArrayList<PropertyValuationDTO>());
			propDTO.setOtherFloorAreaList(new ArrayList<PropertyValuationDTO>());
			propDTO.setResiCommFloorAreaList(new ArrayList<PropertyValuationDTO>());

			propDTO.setResiFloorAreaList(new ArrayList<PropertyValuationDTO>());
			propDTO.setCommFloorAreaList(new ArrayList<PropertyValuationDTO>());
			propDTO.setIndFloorAreaList(new ArrayList<PropertyValuationDTO>());
			propDTO.setSchoolFloorAreaList(new ArrayList<PropertyValuationDTO>());
			propDTO.setHealthFloorAreaList(new ArrayList<PropertyValuationDTO>());
			propDTO.setOtherFloorAreaList(new ArrayList<PropertyValuationDTO>());
			propDTO.setResiCommFloorAreaList(new ArrayList<PropertyValuationDTO>());

			FORWARD_JSP = "INDEPENDENTBUILDFIRST";
		} else if (propForm.getActionName().equalsIgnoreCase("buildingTypeSubmit") && propDTO.getBuildingTypeId().equalsIgnoreCase("2")) {
			propDTO.setFloorTypeList(propBO.getFloorName(language, "RESI"));
			propDTO.setConstructionSlabList(new ArrayList());
			propDTO.setOlderId(0);
			propDTO.setConstructionSlabList(propBO.getConstructionSlabList(language));

			propDTO.setFloorArea("");
			propDTO.setFloorName("");
			propDTO.setOpenTerraceArea("");
			propDTO.setOpenTerraceUsageName("");
			propDTO.setIsLiftCheck("N");
			propDTO.setIsLift("N");
			propDTO.setFloorId("");
			propDTO.setOpenTerraceUsageId("");
			FORWARD_JSP = "INDEPENDENTFLOOR";
		}

		// Added by Rakesh to call clr first web method: Start
		else if (propForm.getActionName().equalsIgnoreCase("searchKhasraClr")) {
			propDTO.setIsConstructionKhasraNo("");
			logger.debug("Inside CLR First Web method call");
			// Added by Rakesh Soni to check kharsa uniqueness wrt colonyId:
			// Start

			ConcurrentHashMap khasraMapClr = null;

			if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {
				if (!"regInitNonPV".equalsIgnoreCase(propDTO.getFromModule())) {

					khasraMapClr = (ConcurrentHashMap) session.getAttribute("khasraMapClr");
					ArrayList tempKhasraList = null;
					if (khasraMapClr != null && !khasraMapClr.isEmpty()) {
						for (Object key : khasraMapClr.keySet()) {
							if (key.equals(propDTO.getAgriColonyid().trim())) {
								tempKhasraList = (ArrayList) khasraMapClr.get(propDTO.getAgriColonyid().trim());

							}
						}
					}
					// ArrayList
					// khasraListClr=(ArrayList)khasraMapClr.get(propDTO
					// .getAgriColonyid().trim());
					if (tempKhasraList != null && !tempKhasraList.isEmpty()) {
						if (tempKhasraList.contains(propDTO.getKhasraNoClr())) {

							logger.info("Duplicate Khasra check wrt rin Pustika");

							propForm.getClrDto().setClrFlag("N");
							propForm.getClrDto().setError("duplicate2");
							propForm.setSearchDisplay("1");
							propForm.setClrData("2");

							if (propDTO.getAgriLandTypeId().equalsIgnoreCase("5")) {
								FORWARD_JSP = "SEARCHKHASRAJSP";
							} else if (propDTO.getAgriLandTypeId().equalsIgnoreCase("6")) {
								FORWARD_JSP = "SEARCHKHASRAJSP";
							} else {
								FORWARD_JSP = "SEARCHKHASRAJSP";
							}

							return mapping.findForward(FORWARD_JSP);

						}
					}
				}
				// NonProp
				else if (propForm.getRegInitForm().getPvReqFlag().equalsIgnoreCase("N") && propForm.getRegInitForm().getPropReqFlag().equalsIgnoreCase("y")) {

					if (propForm.getRegInitForm().getHidnRegTxnId() != null && !propForm.getRegInitForm().getHidnRegTxnId().isEmpty()) {
						ArrayList khasraList = new ArrayList();
						ArrayList valTxnId = null;
						valTxnId = (ArrayList) propBO.getValTxnId(propForm.getRegInitForm().getHidnRegTxnId());
						ArrayList valTxnIdList = new ArrayList();
						for (int i = 0; i < valTxnId.size(); i++) {
							ArrayList temp = (ArrayList) valTxnId.get(i);
							valTxnIdList.add(temp.get(0));
							String colonyId = "";
							colonyId = (String) propBO.getColonyId((String) temp.get(0));

							if (colonyId.equalsIgnoreCase(propDTO.getAgriColonyid().trim())) {
								ArrayList khastListWRTValTxnID = null;
								khastListWRTValTxnID = (ArrayList) propBO.getKhasraListClr((String) temp.get(0));
								if (khastListWRTValTxnID != null && !khastListWRTValTxnID.isEmpty()) {
									if (khastListWRTValTxnID.contains(propDTO.getKhasraNoClr())) {

										logger.info("Duplicate Khasra check wrt rin Pustika");

										propForm.getClrDto().setClrFlag("N");
										propForm.getClrDto().setError("duplicate2");
										propForm.setSearchDisplay("1");
										propForm.setClrData("2");

										if (propDTO.getAgriLandTypeId().equalsIgnoreCase("5")) {
											FORWARD_JSP = "SEARCHKHASRAJSP";
										} else if (propDTO.getAgriLandTypeId().equalsIgnoreCase("6")) {
											FORWARD_JSP = "SEARCHKHASRAJSP";
										} else {
											FORWARD_JSP = "SEARCHKHASRAJSP";
										}

										return mapping.findForward(FORWARD_JSP);

									}
								}

							}

						}

					}

				}

				// NonProp
			}
			// Added By Rakesh SOni to check kharsa uniqueness wrt colonyId: End

			// for check duplicate khasraNo
			if (propForm.getUniKhasraArray().contains(propDTO.getKhasraNoClr().trim())) {

				logger.info("Duplicate Khasra check wrt rin Pustika");

				propForm.getClrDto().setClrFlag("N");
				propForm.getClrDto().setError("duplicate");
				propForm.setSearchDisplay("1");
				propForm.setClrData("2");

				if (propDTO.getAgriLandTypeId().equalsIgnoreCase("5")) {
					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else if (propDTO.getAgriLandTypeId().equalsIgnoreCase("6")) {
					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else {
					FORWARD_JSP = "SEARCHKHASRAJSP";
				}

				return mapping.findForward(FORWARD_JSP);

			}
			/*
			 * else{
			 * propForm.getUniKhasraArray().add(propDTO.getKhasraNoClr().trim
			 * ()); }
			 */

			propForm.setClrData("0");
			propForm.setSearchDisplay("0");
			propForm.setActionNameSetValue("1");
			try {

				String bhucode = propDTO.getCensusCode() != null ? propDTO.getCensusCode().trim() : "";
				String khasraNo = propDTO.getKhasraNoClr() != null ? propDTO.getKhasraNoClr().trim() : "";
				// String khasraNo = propDTO.getKhasraNoClr().trim();
				logger.debug("KhasraNo:-" + khasraNo + "bhucode:-" + bhucode);
				// String khasraUrl1 = pr.getValue("khasraUrl1") + bhucode +
				// "&khasraNo=" + khasraNo;

				String query = (URLEncoder.encode(bhucode)).concat("&khasraNo=").concat(URLEncoder.encode(khasraNo));

				logger.debug("query:-" + query);
				String khasraUrl1 = pr.getValue("khasraUrl1").concat(query);

				logger.debug("khasraUrl1:-" + khasraUrl1);

				URL url = new URL(khasraUrl1);
				logger.debug("url:-" + url);
				HttpURLConnection conn;

				String proxyFlag = pr.getValue("proxyFlag");
				logger.debug("proxyFlag:-" + proxyFlag);
				if (proxyFlag.equalsIgnoreCase("Y")) {
					try {
						String proxyIp = pr.getValue("http.proxyHost");
						String proxyPort = pr.getValue("http.proxyPort");
						if (proxyIp != null && !proxyIp.isEmpty() && proxyPort != null && !proxyPort.isEmpty()) {
							Proxy proxy = new Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress(proxyIp, Integer.parseInt(proxyPort)));
						}
					} catch (Exception e) {
						logger.debug("Error message : " + e.getMessage(), e);
					}

					conn = (HttpURLConnection) url.openConnection(proxy);
				} else {
					conn = (HttpURLConnection) url.openConnection();
				}
				logger.debug("conn:-" + conn);
				// HttpURLConnection conn = (HttpURLConnection)
				// url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "text/xml");
				int timeout = Integer.valueOf(pr.getValue("igrs_khasra_timeout"));
				logger.debug("timeout:-" + timeout);
				try {
					conn.setConnectTimeout(timeout * 1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				logger.debug("Response Code: " + conn.getResponseCode());
				if (conn.getResponseCode() != 200) {
					propForm.setClrData("2");
					propForm.getClrDto().setClrFlag("N");
					commonForm.setClrFlag("N");

					propForm.getClrDto().setError("unknownUrl");

					propForm.setSearchDisplay("1");
					logger.debug("JSP Form Data" + propForm.getSearchDisplay() + ":" + propForm.getClrData() + ":" + propForm.getClrDto().getError());

					if (propDTO.getAgriLandTypeId().equalsIgnoreCase("5")) {
						FORWARD_JSP = "SEARCHKHASRAJSP";
					} else if (propDTO.getAgriLandTypeId().equalsIgnoreCase("6")) {
						FORWARD_JSP = "SEARCHKHASRAJSP";
					} else {
						FORWARD_JSP = "SEARCHKHASRAJSP";
					}
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}
				logger.debug("Before unmarshalling");

				JAXBContext jc = JAXBContext.newInstance(KhasraClrDTO.class);
				Unmarshaller ums = jc.createUnmarshaller();
				KhasraClrDTO kdto = (KhasraClrDTO) ums.unmarshal(url);

				logger.debug("After unmarshalling");
				logger.debug("Service Flag: " + kdto.getServiceFlag().trim() + ":For KhasraNo: " + khasraNo);
				if (kdto.getServiceFlag().trim().equalsIgnoreCase("0")) {
					propForm.setClrData("2");
					propForm.getClrDto().setClrFlag("N");
					propForm.getClrDto().setError("nodatafound");
					propForm.setSearchDisplay("1");

					if (propDTO.getAgriLandTypeId().equalsIgnoreCase("5")) {
						FORWARD_JSP = "SEARCHKHASRAJSP";
					} else if (propDTO.getAgriLandTypeId().equalsIgnoreCase("6")) {
						FORWARD_JSP = "SEARCHKHASRAJSP";
					} else {
						FORWARD_JSP = "SEARCHKHASRAJSP";
					}

					return mapping.findForward(FORWARD_JSP);
				} else {
					propForm.setClrData("1");
				}

				kdto.setSingleCropArea("0.0");
				if (kdto.getLandIrriOrNot().trim().equalsIgnoreCase("1")) {
					kdto.setLandIrriOrNot("Yes");
				} else if (kdto.getLandIrriOrNot().trim().equalsIgnoreCase("0")) {
					kdto.setLandIrriOrNot("No");

				} else {
					kdto.setLandIrriOrNot(pr.getValue("clr_no_data_found"));
				}

				if (kdto.getLandUnirriDouble().trim().equalsIgnoreCase("1")) {
					kdto.setLandUnirriDouble("Yes");
				} else if (kdto.getLandUnirriDouble().trim().equalsIgnoreCase("0")) {
					kdto.setLandUnirriDouble("No");

				} else {
					kdto.setLandUnirriDouble(pr.getValue("clr_no_data_found"));
				}

				if (kdto.getLandUnIrriOrNot().trim().equalsIgnoreCase("1")) {
					kdto.setLandUnIrriOrNot("Yes");
				} else if (kdto.getLandUnIrriOrNot().trim().equalsIgnoreCase("0")) {
					kdto.setLandUnIrriOrNot("No");

				} else {
					kdto.setLandUnIrriOrNot(pr.getValue("clr_no_data_found"));
				}
				if (kdto.getPadtiFlag().trim().equalsIgnoreCase("1")) {
					kdto.setPadtiFlag("Yes");
				} else if (kdto.getPadtiFlag().trim().equalsIgnoreCase("0")) {
					kdto.setPadtiFlag("No");

				} else {
					kdto.setPadtiFlag(pr.getValue("clr_no_data_found"));
				}
				if (kdto.getNohiyat().trim() == null || kdto.getNohiyat().isEmpty()) {
					kdto.setNohiyat(pr.getValue("clr_no_data_found"));
				}
				if (kdto.getCurrentStatus() == null || kdto.getCurrentStatus().isEmpty()) {
					kdto.setCurrentStatus(pr.getValue("clr_no_data_found"));
				}
				if (kdto.getMortgage().trim() == null || kdto.getMortgage().trim().isEmpty()) {
					kdto.setMortgage(pr.getValue("clr_no_data_found"));
				}

				if (kdto.getIrrigatedArea().trim() == null || kdto.getIrrigatedArea().trim().isEmpty()) {
					kdto.setIrrigatedArea("0.0");
				}

				if (kdto.getNonIrrigatedArea().trim() == null || kdto.getNonIrrigatedArea().trim().isEmpty()) {
					kdto.setNonIrrigatedArea("0.0");
				}

				if (kdto.getPadtiArea().trim() == null || kdto.getPadtiArea().trim().isEmpty()) {
					kdto.setPadtiArea("0.0");
				}

				if (kdto.getDoubleCropArea().trim() == null || kdto.getDoubleCropArea().trim().isEmpty()) {
					kdto.setDoubleCropArea("0.0");
				}
				if (kdto.getNonIrrigatedArea().trim() == null || kdto.getNonIrrigatedArea().trim().isEmpty()) {
					kdto.setNonIrrigatedArea("0.0");
				}

				// Added by Rakesh ph2
				propForm.getClrKhasraList().add(kdto);
				/*
				 * ArrayList<KhasraClrDTO> khasraList=new
				 * ArrayList<KhasraClrDTO>(); khasraList.add(kdto);
				 */

				propForm.setClrDto(kdto);

				RegCommonForm regInitForm = new RegCommonForm();
				regInitForm.setClrDto(kdto);
				propForm.setClrData("1");
				propForm.getClrDto().setClrFlag("Y");
				commonForm.setClrFlag("Y");

				conn.disconnect();
			}

			catch (UnknownHostException e) {

				logger.debug("Unknown Url Error :-" + e.getMessage(), e);
				propForm.setClrData("2");
				propForm.getClrDto().setClrFlag("N");
				commonForm.setClrFlag("N");
				propForm.getClrDto().setError("unknownUrl");

				propForm.setSearchDisplay("1");
				logger.debug("JSP Form Data" + propForm.getSearchDisplay() + ":" + propForm.getClrData() + ":" + propForm.getClrDto().getError());

				if (propDTO.getAgriLandTypeId().equalsIgnoreCase("5")) {
					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else if (propDTO.getAgriLandTypeId().equalsIgnoreCase("6")) {
					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else {
					FORWARD_JSP = "SEARCHKHASRAJSP";
				}

				return mapping.findForward(FORWARD_JSP);

			} catch (ConnectException e) {

				logger.debug("Connection Time Out Error :-" + e.getMessage(), e);
				propForm.getClrDto().setClrFlag("N");
				commonForm.setClrFlag("N");
				propForm.getClrDto().setError("unknownUrl");
				propForm.setSearchDisplay("1");
				propForm.setClrData("2");
				logger.debug(" Debug as ConnectException " + propForm.getSearchDisplay() + ":" + propForm.getClrData() + ":" + propForm.getClrDto().getError());
				if (propDTO.getAgriLandTypeId().equalsIgnoreCase("5")) {
					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else if (propDTO.getAgriLandTypeId().equalsIgnoreCase("6")) {
					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else {
					FORWARD_JSP = "SEARCHKHASRAJSP";
				}
				return mapping.findForward(FORWARD_JSP);
			} catch (SocketTimeoutException e) {

				logger.debug("SocketTimeoutException Time Out Error :-" + e.getMessage(), e);

				propForm.getClrDto().setClrFlag("N");
				commonForm.setClrFlag("N");
				propForm.getClrDto().setError("unknownUrl");
				propForm.setSearchDisplay("1");
				propForm.setClrData("2");
				logger.debug(" Debug as ConnectException " + propForm.getSearchDisplay() + ":" + propForm.getClrData() + ":" + propForm.getClrDto().getError());
				if (propDTO.getAgriLandTypeId().equalsIgnoreCase("5")) {
					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else if (propDTO.getAgriLandTypeId().equalsIgnoreCase("6")) {
					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else {
					FORWARD_JSP = "SEARCHKHASRAJSP";
				}
				// FORWARD_JSP = "AGRILANDUNDIVERTEDCLR";
				return mapping.findForward(FORWARD_JSP);
			} catch (IOException e) {

				logger.debug("IO Error :-" + e.getMessage(), e);

				propForm.getClrDto().setClrFlag("N");
				commonForm.setClrFlag("N");
				propForm.getClrDto().setError("unknownUrl");
				propForm.setSearchDisplay("1");
				propForm.setClrData("2");
				logger.debug("Debug as IO Exception" + propForm.getSearchDisplay() + ":" + propForm.getClrData() + ":" + propForm.getClrDto().getError());

				if (propDTO.getAgriLandTypeId().equalsIgnoreCase("5")) {
					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else if (propDTO.getAgriLandTypeId().equalsIgnoreCase("6")) {
					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else {
					FORWARD_JSP = "SEARCHKHASRAJSP";
				}
				// FORWARD_JSP = "AGRILANDUNDIVERTEDCLR";
				return mapping.findForward(FORWARD_JSP);

			} catch (JAXBException e) {
				logger.debug("Debug as JAXB Exception :-" + e.getMessage(), e);

				propForm.getClrDto().setClrFlag("N");
				propForm.getClrDto().setError("unknownUrl");
				propForm.setSearchDisplay("1");
				propForm.setClrData("2");
				logger.debug("Debug as JAXB Exception" + propForm.getSearchDisplay() + ":" + propForm.getClrData() + ":" + propForm.getClrDto().getError());

				if (propDTO.getAgriLandTypeId().equalsIgnoreCase("5")) {
					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else if (propDTO.getAgriLandTypeId().equalsIgnoreCase("6")) {
					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else {
					FORWARD_JSP = "SEARCHKHASRAJSP";
				}

				// FORWARD_JSP = "AGRILANDUNDIVERTEDCLR";
				return mapping.findForward(FORWARD_JSP);
			} catch (Exception e) {
				logger.debug("Debug as JAXB Exception :-" + e.getMessage(), e);

				propForm.getClrDto().setClrFlag("N");
				propForm.getClrDto().setError("unknownUrl");

				propForm.setSearchDisplay("1");
				propForm.setClrData("2");
				logger.debug("Exception" + propForm.getSearchDisplay() + ":" + propForm.getClrData() + ":" + propForm.getClrDto().getError());

				if (propDTO.getAgriLandTypeId().equalsIgnoreCase("5")) {
					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else if (propDTO.getAgriLandTypeId().equalsIgnoreCase("6")) {
					FORWARD_JSP = "SEARCHKHASRAJSP";
				} else {
					FORWARD_JSP = "SEARCHKHASRAJSP";
				}

				// FORWARD_JSP = "AGRILANDUNDIVERTEDCLR";
				return mapping.findForward(FORWARD_JSP);
			}

			if (propDTO.getAgriLandTypeId().equalsIgnoreCase("5")) {
				FORWARD_JSP = "SEARCHKHASRAJSP";
			} else if (propDTO.getAgriLandTypeId().equalsIgnoreCase("6")) {
				FORWARD_JSP = "SEARCHKHASRAJSP";
			} else {
				FORWARD_JSP = "SEARCHKHASRAJSP";
			}
			// FORWARD_JSP = "AGRILANDUNDIVERTEDCLR";
		}
		// Added by Rakesh to call clr first web method: End

		/*
		 * else if (propForm.getActionName().equalsIgnoreCase("addMoreKhasra"))
		 * { FORWARD_JSP = "addMoreKhasra";
		 * 
		 * }
		 */
		else if (propForm.getActionName().equalsIgnoreCase("buildingTypeSubmit") && propDTO.getBuildingTypeId().equalsIgnoreCase("3")) {
			propDTO.setMultiStoreyTypeList(propBO.getMultiStoreyType(language, propDTO));
			propDTO.setBuiltUpArea("");
			propDTO.setCommonArea("");
			propDTO.setMultiStoreyTypeId("");
			FORWARD_JSP = "MULTISTOREYTYPE";
		} else if (propForm.getActionName().equalsIgnoreCase("buildingTypeSubmit") && propDTO.getBuildingTypeId().equalsIgnoreCase("4")) {

			propDTO.setOpenTerraceUsageList(propBO.getL1Name(language, propDTO));
			propDTO.setOpenTerraceArea("");
			propDTO.setOpenTerraceUsageName("");
			propDTO.setOpenTerraceUsageId("");
			FORWARD_JSP = "OPENTERRACEPAGE";
		} else if (propForm.getActionName().equalsIgnoreCase("independentOlderYes")) {
			propDTO.setIsOlderFlag("YES");
			propDTO.setIsOlder("YES");
			if ("Y".equalsIgnoreCase(propDTO.getIsLift())) {
				propDTO.setIsLiftCheck("Y");
			} else {
				propDTO.setIsLiftCheck("N");
			}
			FORWARD_JSP = "INDEPENDENTFLOOR";
		} else if (propForm.getActionName().equalsIgnoreCase("independentOlderNo")) {
			propDTO.setIsOlderFlag("NO");
			propDTO.setIsOlder("NO");
			if ("Y".equalsIgnoreCase(propDTO.getIsLift())) {
				propDTO.setIsLiftCheck("Y");
			} else {
				propDTO.setIsLiftCheck("N");
			}
			FORWARD_JSP = "INDEPENDENTFLOOR";
		} else if (propForm.getActionName().equalsIgnoreCase("independentOpenTerraceYes")) {
			propDTO.setIsOpenTerrace("YES");
			propDTO.setIsOpenTerraceFlag("YES");
			PropertyValuationDTO propDTO1 = new PropertyValuationDTO();
			propDTO1.setBuildingTypeId("4");
			propDTO1.setPropertyId("2");
			if ("Y".equalsIgnoreCase(propDTO.getIsLift())) {
				propDTO.setIsLiftCheck("Y");
			} else {
				propDTO.setIsLiftCheck("N");
			}
			propDTO.setOpenTerraceUsageList(propBO.getL1Name(language, propDTO1));
			FORWARD_JSP = "INDEPENDENTFLOOR";
		} else if (propForm.getActionName().equalsIgnoreCase("independentOpenTerraceNo")) {
			propDTO.setIsOpenTerrace("NO");
			propDTO.setIsOpenTerraceFlag("NO");
			if ("Y".equalsIgnoreCase(propDTO.getIsLift())) {
				propDTO.setIsLiftCheck("Y");
			} else {
				propDTO.setIsLiftCheck("N");
			}
			FORWARD_JSP = "INDEPENDENTFLOOR";
		} else if (propForm.getActionName().equalsIgnoreCase("mutiStoreyTypeSubmit") && propDTO.getMultiStoreyTypeId().equalsIgnoreCase("18")) {
			propDTO.setFloorTypeList(propBO.getFloorName(language, "RESI"));
			propDTO.setOlderId(0);
			propDTO.setConstructionSlabList(new ArrayList());
			propDTO.setConstructionSlabList(propBO.getConstructionSlabList(language));

			// ADDED BY SIMRAN TO GET SUBCLAUSE LIST FOR RESIDENTIAL MULTISTORY
			propDTO.setBuildingSubId("");
			propDTO.setFloorArea("");
			propDTO.setFloorName("");
			propDTO.setOpenTerraceArea("");
			propDTO.setOpenTerraceUsageName("");
			propDTO.setSubclauseList(propBO.getSubclauseDataBuilding(propDTO, language, "NO"));
			logger.debug("^^^^^^^^^^^^" + propDTO.getSubclauseList().size());
			propDTO.setIsLiftCheck("N");
			propDTO.setIsLift("N");
			propDTO.setFloorId("");
			propDTO.setOpenTerraceUsageId("");

			FORWARD_JSP = "MULTISTOREYRESIDENTIAL";
		} else if (propForm.getActionName().equalsIgnoreCase("mutiStoreyTypeSubmit") && propDTO.getMultiStoreyTypeId().equalsIgnoreCase("19")) {
			propDTO.setNearRoad("");
			propDTO.setOlderId(0);
			propDTO.setConstructionSlabList(new ArrayList());
			propDTO.setFloorTypeList(propBO.getFloorName(language, "COMM"));
			propDTO.setFloorArea("");
			propDTO.setFloorName("");
			propDTO.setIsLift("");
			propDTO.setOpenTerraceArea("");
			propDTO.setOpenTerraceUsageName("");
			if (propBO.multiStroreyCommercialType(propDTO)) {
				propDTO.setMultiCommPropList(propBO.getMultiCommPropertyName(language, "comm"));
			} else {
				propDTO.setMultiCommPropList(propBO.getMultiCommPropertyName(language, "other"));
			}
			propDTO.setIsLiftCheck("N");
			propDTO.setIsLift("N");
			propDTO.setNearRoad("N");
			propDTO.setNearRoadCheck("N");
			propDTO.setFloorId("");
			propDTO.setMultiCommPropId("");
			propDTO.setOpenTerraceUsageId("");
			FORWARD_JSP = "MULTISTOREYCOMMERCIAL";
		} else if (propForm.getActionName().equalsIgnoreCase("multiResiOlderYes")) {
			propDTO.setIsOlderFlag("YES");
			propDTO.setIsOlder("YES");
			if ("Y".equalsIgnoreCase(propDTO.getIsLift())) {
				propDTO.setIsLiftCheck("Y");
			} else {
				propDTO.setIsLiftCheck("N");
			}
			FORWARD_JSP = "MULTISTOREYRESIDENTIAL";
		} else if (propForm.getActionName().equalsIgnoreCase("multiResiOlderNo")) {
			propDTO.setIsOlderFlag("NO");
			propDTO.setIsOlder("NO");
			if ("Y".equalsIgnoreCase(propDTO.getIsLift())) {
				propDTO.setIsLiftCheck("Y");
			} else {
				propDTO.setIsLiftCheck("N");
			}
			FORWARD_JSP = "MULTISTOREYRESIDENTIAL";
		} else if (propForm.getActionName().equalsIgnoreCase("multiResiOpenTerraceYes")) {
			propDTO.setIsOpenTerrace("YES");
			propDTO.setIsOpenTerraceFlag("YES");
			PropertyValuationDTO propDTO1 = new PropertyValuationDTO();
			propDTO1.setBuildingTypeId("4");
			propDTO1.setPropertyId("2");
			propDTO.setOpenTerraceUsageList(propBO.getL1Name(language, propDTO1));
			if ("Y".equalsIgnoreCase(propDTO.getIsLift())) {
				propDTO.setIsLiftCheck("Y");
			} else {
				propDTO.setIsLiftCheck("N");
			}
			FORWARD_JSP = "MULTISTOREYRESIDENTIAL";
		} else if (propForm.getActionName().equalsIgnoreCase("multiResiOpenTerraceNo")) {
			propDTO.setIsOpenTerrace("NO");
			propDTO.setIsOpenTerraceFlag("NO");
			if ("Y".equalsIgnoreCase(propDTO.getIsLift())) {
				propDTO.setIsLiftCheck("Y");
			} else {
				propDTO.setIsLiftCheck("N");
			}
			FORWARD_JSP = "MULTISTOREYRESIDENTIAL";
		} else if (propForm.getActionName().equalsIgnoreCase("multiCommOlderYes")) {
			propDTO.setIsOlderFlag("YES");
			propDTO.setIsOlder("YES");
			if ("Y".equalsIgnoreCase(propDTO.getIsLift())) {
				propDTO.setIsLiftCheck("Y");
			} else {
				propDTO.setIsLiftCheck("N");
			}
			if ("Y".equalsIgnoreCase(propDTO.getNearRoad())) {
				propDTO.setNearRoadCheck("Y");
			} else {
				propDTO.setNearRoadCheck("N");
			}
			FORWARD_JSP = "MULTISTOREYCOMMERCIAL";
		} else if (propForm.getActionName().equalsIgnoreCase("multiCommOlderNo")) {
			propDTO.setIsOlderFlag("NO");
			propDTO.setIsOlder("NO");
			if ("Y".equalsIgnoreCase(propDTO.getIsLift())) {
				propDTO.setIsLiftCheck("Y");
			} else {
				propDTO.setIsLiftCheck("N");
			}
			if ("Y".equalsIgnoreCase(propDTO.getNearRoad())) {
				propDTO.setNearRoadCheck("Y");
			} else {
				propDTO.setNearRoadCheck("N");
			}
			FORWARD_JSP = "MULTISTOREYCOMMERCIAL";
		} else if (propForm.getActionName().equalsIgnoreCase("multiCommOpenTerraceYes")) {
			propDTO.setIsOpenTerrace("YES");
			propDTO.setIsOpenTerraceFlag("YES");
			PropertyValuationDTO propDTO1 = new PropertyValuationDTO();
			propDTO1.setBuildingTypeId("4");
			propDTO1.setPropertyId("2");
			propDTO.setOpenTerraceUsageList(propBO.getL1Name(language, propDTO1));
			if ("Y".equalsIgnoreCase(propDTO.getIsLift())) {
				propDTO.setIsLiftCheck("Y");
			} else {
				propDTO.setIsLiftCheck("N");
			}
			if ("Y".equalsIgnoreCase(propDTO.getNearRoad())) {
				propDTO.setNearRoadCheck("Y");
			} else {
				propDTO.setNearRoadCheck("N");
			}
			FORWARD_JSP = "MULTISTOREYCOMMERCIAL";
		} else if (propForm.getActionName().equalsIgnoreCase("multiCommOpenTerraceNo")) {
			propDTO.setIsOpenTerrace("NO");
			propDTO.setIsOpenTerraceFlag("NO");
			if ("Y".equalsIgnoreCase(propDTO.getIsLift())) {
				propDTO.setIsLiftCheck("Y");
			} else {
				propDTO.setIsLiftCheck("N");
			}
			if ("Y".equalsIgnoreCase(propDTO.getNearRoad())) {
				propDTO.setNearRoadCheck("Y");
			} else {
				propDTO.setNearRoadCheck("N");
			}
			FORWARD_JSP = "MULTISTOREYCOMMERCIAL";
		} else if (propForm.getActionName().equalsIgnoreCase("independentBuildPropSubmit")) {

			propDTO.setIsOnlyResidential("N");
			propDTO.setFloorTypeListBuilding(propBO.getFloorName(language, "RESI"));
			propDTO.setConstructionSlabList(new ArrayList());
			propDTO.setConstructionSlabList(propBO.getConstructionSlabList(language));
			propDTO.setOlderId(0);
			if ("Y".equalsIgnoreCase(propDTO.getResiFlag())) {
				propDTO.setResiL2List(propBO.getResiL2Name(language));
			}
			if ("Y".equalsIgnoreCase(propDTO.getCommFlag())) {
				propDTO.setFloorTypeListBuilding(propBO.getFloorName(language, "COMM"));
				propDTO.setCommL2List(propBO.getCommL2Name(language));
			}
			if ("Y".equalsIgnoreCase(propDTO.getIndFlag())) {
				propDTO.setIndL2List(propBO.getIndL2Name(language));
			}
			if ("Y".equalsIgnoreCase(propDTO.getSchoolFlag())) {
				propDTO.setSchoolL2List(propBO.getSchoolL2Name(language));
			}
			if ("Y".equalsIgnoreCase(propDTO.getHealthFlag())) {
				propDTO.setHealthL2List(propBO.getHealthL2Name(language));
			}
			if ("Y".equalsIgnoreCase(propDTO.getOtherFlag())) {
				propDTO.setOtherL2List(propBO.getOtherL2Name(language));
			}
			if ("Y".equalsIgnoreCase(propDTO.getResiCommFlag())) {
				propDTO.setFloorTypeListBuilding(propBO.getFloorName(language, "COMM"));
				propDTO.setResiCommL2List(propBO.getResiCommL2Name(language));
			}
			propDTO.setPlotTotArea("");
			propDTO.setPlotResiArea("");
			propDTO.setPlotCommArea("");
			propDTO.setPlotIndusArea("");
			propDTO.setPlotSchoolArea("");
			propDTO.setPlotHealthArea("");
			propDTO.setPlotOtherArea("");

			if (propDTO.getResiFlag().equalsIgnoreCase("Y") && propDTO.getCommFlag().equalsIgnoreCase("N") && propDTO.getIndFlag().equalsIgnoreCase("N") && propDTO.getHealthFlag().equalsIgnoreCase("N") && propDTO.getSchoolFlag().equalsIgnoreCase("N") && propDTO.getOtherFlag().equalsIgnoreCase("N") && propDTO.getResiCommFlag().equalsIgnoreCase("N")) {

				propDTO.setIsOnlyResidential("Y");
			}

			else {

				propDTO.setIsOnlyResidential("");
			}
			if ("construct".equalsIgnoreCase(propDTO.getIsConstruction())) {
				propDTO.setSubclauseList(propBO.getSubclauseDataBuilding(propDTO, language, "YES"));
			} else {
				propDTO.setSubclauseList(propBO.getSubclauseDataBuilding(propDTO, language, "NO"));
			}
			propDTO.setFloorTypeList(new ArrayList<PropertyValuationDTO>());
			for (int i = 0; i < propDTO.getFloorTypeListBuilding().size(); i++) {
				PropertyValuationDTO newDTO = propDTO.getFloorTypeListBuilding().get(i);
				String floorID = newDTO.getFloorId();
				if ("1058".equalsIgnoreCase(propDTO.getDeedId())) {
					propDTO.getFloorTypeList().add(newDTO);
				} else {
					if (floorID.equalsIgnoreCase("3")) {
						propDTO.getFloorTypeList().add(newDTO);
					}
				}
			}
			propDTO.setConstCost(0.0);
			propDTO.setConstructionCost("0.0");
			propDTO.setBuildingSubId("");
			propDTO.setFloorMarketValueList(new ArrayList());
			propDTO.setDisableEntry("false");
			propDTO.setIsTncpSchoolCheck("N");
			propDTO.setIsTncpHealthCheck("N");
			propDTO.setIndAreaZero("");
			propDTO.setIsAkvn("");
			propDTO.setIsAkvnFlag("");
			propDTO.setIsSuperConstruction("");
			propDTO.setIsSuperConstructionFlag("");

			propDTO.setOlderId(0);// added by akansha
			// propDTO.setConstructionSlabList(new ArrayList());//added by
			// akansha
			FORWARD_JSP = "INDEPENDENTBUILDINGSECOND";
		} else if (propForm.getActionName().equalsIgnoreCase("addMoreFloor")) {
			String floorNo = String.valueOf(propDTO.getAddfloorcounter() + 1);
			propDTO.setAddfloorcounter(propDTO.getAddfloorcounter() + 1);
			PropertyValuationDTO resiAreaDTO = null;
			PropertyValuationDTO commAreaDTO = null;
			PropertyValuationDTO indAreaDTO = null;
			PropertyValuationDTO schoolAreaDTO = null;
			PropertyValuationDTO healthAreaDTO = null;
			PropertyValuationDTO otherAreaDTO = null;
			PropertyValuationDTO resiCommAreaDTO = null;
			if ("Y".equalsIgnoreCase(propDTO.getResiFlag())) {
				propDTO.setResiL2List(propBO.getResiL2Name(language));
				resiAreaDTO = new PropertyValuationDTO();
				String[] resiArea = request.getParameterValues("resiL2Area");
				String rccArea = "";
				String rbcArea = "";
				String tinArea = "";
				String kacchaArea = "";
				if (resiArea != null) {
					rccArea = resiArea[0];
					rbcArea = resiArea[1];
					tinArea = resiArea[2];
					kacchaArea = resiArea[3];
				}
				resiAreaDTO.setFloorNo(floorNo);
				resiAreaDTO.setFloorId(propDTO.getFloorId());
				resiAreaDTO.setKacchaArea(kacchaArea);
				resiAreaDTO.setTinArea(tinArea);
				resiAreaDTO.setRccArea(rccArea);
				resiAreaDTO.setRbcArea(rbcArea);
				propDTO.getResiFloorAreaList().add(resiAreaDTO);
			}
			if ("Y".equalsIgnoreCase(propDTO.getCommFlag())) {
				propDTO.setCommL2List(propBO.getCommL2Name(language));
				commAreaDTO = new PropertyValuationDTO();
				String[] commArea = request.getParameterValues("commL2Area");
				String shopArea = commArea[0];
				String officeArea = commArea[1];
				String godownArea = commArea[2];
				commAreaDTO.setFloorNo(floorNo);
				commAreaDTO.setFloorId(propDTO.getFloorId());
				commAreaDTO.setShopArea(shopArea);
				commAreaDTO.setOfficeArea(officeArea);
				commAreaDTO.setGodownArea(godownArea);
				propDTO.getCommFloorAreaList().add(commAreaDTO);

			}
			if ("Y".equalsIgnoreCase(propDTO.getIndFlag())) {
				propDTO.setIndL2List(propBO.getIndL2Name(language));
				indAreaDTO = new PropertyValuationDTO();
				String[] indArea = request.getParameterValues("indL2Area");
				String rccArea = "";
				String rbcArea = "";
				String tinArea = "";
				String kacchaArea = "";
				if (indArea != null) {
					rccArea = indArea[0];
					rbcArea = indArea[1];
					tinArea = indArea[2];
					kacchaArea = indArea[3];
				}
				indAreaDTO.setFloorNo(floorNo);
				indAreaDTO.setFloorId(propDTO.getFloorId());
				indAreaDTO.setKacchaArea(kacchaArea);
				indAreaDTO.setTinArea(tinArea);
				indAreaDTO.setRccArea(rccArea);
				indAreaDTO.setRbcArea(rbcArea);
				propDTO.getIndFloorAreaList().add(indAreaDTO);
			}
			if ("Y".equalsIgnoreCase(propDTO.getSchoolFlag())) {
				propDTO.setSchoolL2List(propBO.getSchoolL2Name(language));
				schoolAreaDTO = new PropertyValuationDTO();
				String[] schoolArea = request.getParameterValues("schoolL2Area");
				String rccArea = "";
				String rbcArea = "";
				String tinArea = "";
				String kacchaArea = "";
				if (schoolArea != null) {
					rccArea = schoolArea[0];
					rbcArea = schoolArea[1];
					tinArea = schoolArea[2];
					kacchaArea = schoolArea[3];
				}
				schoolAreaDTO.setFloorNo(floorNo);
				schoolAreaDTO.setFloorId(propDTO.getFloorId());
				schoolAreaDTO.setKacchaArea(kacchaArea);
				schoolAreaDTO.setTinArea(tinArea);
				schoolAreaDTO.setRccArea(rccArea);
				schoolAreaDTO.setRbcArea(rbcArea);
				propDTO.getSchoolFloorAreaList().add(schoolAreaDTO);
			}
			if ("Y".equalsIgnoreCase(propDTO.getHealthFlag())) {
				propDTO.setHealthL2List(propBO.getHealthL2Name(language));
				healthAreaDTO = new PropertyValuationDTO();
				String[] healthArea = request.getParameterValues("healthL2Area");
				String rccArea = "";
				String rbcArea = "";
				String tinArea = "";
				String kacchaArea = "";
				if (healthArea != null) {
					rccArea = healthArea[0];
					rbcArea = healthArea[1];
					tinArea = healthArea[2];
					kacchaArea = healthArea[3];
				}
				healthAreaDTO.setFloorNo(floorNo);
				healthAreaDTO.setFloorId(propDTO.getFloorId());
				healthAreaDTO.setKacchaArea(kacchaArea);
				healthAreaDTO.setTinArea(tinArea);
				healthAreaDTO.setRccArea(rccArea);
				healthAreaDTO.setRbcArea(rbcArea);
				propDTO.getHealthFloorAreaList().add(healthAreaDTO);
			}
			if ("Y".equalsIgnoreCase(propDTO.getOtherFlag())) {
				propDTO.setOtherL2List(propBO.getOtherL2Name(language));
				otherAreaDTO = new PropertyValuationDTO();
				String[] otherArea = request.getParameterValues("otherL2Area");
				String rccArea = "";
				String rbcArea = "";
				String tinArea = "";
				String kacchaArea = "";
				if (otherArea != null) {
					rccArea = otherArea[0];
					rbcArea = otherArea[1];
					tinArea = otherArea[2];
					kacchaArea = otherArea[3];
				}
				otherAreaDTO.setFloorNo(floorNo);
				otherAreaDTO.setFloorId(propDTO.getFloorId());
				otherAreaDTO.setKacchaArea(kacchaArea);
				otherAreaDTO.setTinArea(tinArea);
				otherAreaDTO.setRccArea(rccArea);
				otherAreaDTO.setRbcArea(rbcArea);
				propDTO.getOtherFloorAreaList().add(otherAreaDTO);
			}
			if ("Y".equalsIgnoreCase(propDTO.getResiCommFlag())) {
				propDTO.setResiCommL2List(propBO.getResiCommL2Name(language));
				resiCommAreaDTO = new PropertyValuationDTO();
				String[] resiCommArea = request.getParameterValues("resiCommL2Area");
				String rccArea = "";
				String rbcArea = "";
				String tinArea = "";
				String kacchaArea = "";
				String shopArea = "";
				String officeArea = "";
				String godownArea = "";
				if (resiCommArea != null) {
					if ("2".equalsIgnoreCase(propDTO.getFloorId()) || "10".equalsIgnoreCase(propDTO.getFloorId())) {

						shopArea = resiCommArea[0];
						officeArea = resiCommArea[1];
						godownArea = resiCommArea[2];
					} else {
						rccArea = resiCommArea[0];
						rbcArea = resiCommArea[1];
						tinArea = resiCommArea[2];
						kacchaArea = resiCommArea[3];
						shopArea = resiCommArea[4];
						officeArea = resiCommArea[5];
						godownArea = resiCommArea[6];
					}
				}
				resiCommAreaDTO.setFloorNo(floorNo);
				resiCommAreaDTO.setFloorId(propDTO.getFloorId());
				resiCommAreaDTO.setKacchaArea(kacchaArea);
				resiCommAreaDTO.setTinArea(tinArea);
				resiCommAreaDTO.setRccArea(rccArea);
				resiCommAreaDTO.setRbcArea(rbcArea);
				resiCommAreaDTO.setShopArea(shopArea);
				resiCommAreaDTO.setOfficeArea(officeArea);
				resiCommAreaDTO.setGodownArea(godownArea);
				propDTO.getResiCommFloorAreaList().add(resiCommAreaDTO);
			}
			PropertyValuationDTO floorDTO = new PropertyValuationDTO();
			floorDTO.setFloorNo(floorNo);
			floorDTO.setFloorName(propDTO.getFloorName());
			floorDTO.setFloorId(propDTO.getFloorId());

			PropertyValuationDTO[] dto = new PropertyValuationDTO[9];
			dto[0] = floorDTO;
			dto[1] = resiAreaDTO;
			dto[2] = commAreaDTO;
			dto[3] = indAreaDTO;
			dto[4] = schoolAreaDTO;
			dto[5] = healthAreaDTO;
			dto[6] = otherAreaDTO;
			dto[7] = propDTO;
			dto[8] = resiCommAreaDTO;
			floorDTO.setFloorValue(propBO.getFloorValue(dto));

			// below set total construction cost. Added by Roopam on 11 feb 2014
			double constCost = propDTO.getConstCost();
			constCost = constCost + floorDTO.getFloorValue();
			propDTO.setConstCost(constCost);
			propDTO.setFloorTypeList(new ArrayList<PropertyValuationDTO>());
			propDTO.getFloorMarketValueList().add(floorDTO);
			for (int i = 0; i < propDTO.getFloorTypeListBuilding().size(); i++) {
				PropertyValuationDTO newDTO = propDTO.getFloorTypeListBuilding().get(i);
				boolean flag = true;
				String floorID = newDTO.getFloorId();
				for (int j = 0; j < propDTO.getFloorMarketValueList().size(); j++) {
					PropertyValuationDTO newDTO1 = propDTO.getFloorMarketValueList().get(j);
					String floorId1 = newDTO1.getFloorId();
					if (floorID.equalsIgnoreCase(floorId1)) {
						flag = false;
					} else {

					}
				}
				if (flag == false) {
					if (floorID.equalsIgnoreCase("7") || floorID.equalsIgnoreCase("8")) {
						propDTO.getFloorTypeList().add(newDTO);
					}
				} else if (flag == true) {
					propDTO.getFloorTypeList().add(newDTO);
				}
			}

			if (propDTO.getFloorMarketValueList() != null && propDTO.getFloorMarketValueList().size() > 0) {
				propDTO.setDisableEntry("true");
			} else {
				propDTO.setDisableEntry("false");
			}
			if ("construct".equalsIgnoreCase(propDTO.getIsConstruction())) {
				propDTO.setSubclauseList(propBO.getSubclauseDataBuilding(propDTO, language, "YES"));
			} else {
				propDTO.setSubclauseList(propBO.getSubclauseDataBuilding(propDTO, language, "NO"));
			}
			if ("Y".equalsIgnoreCase(propDTO.getIsTncpHealth())) {
				propDTO.setIsTncpHealthCheck("Y");
			} else {
				propDTO.setIsTncpHealthCheck("N");
			}
			if ("Y".equalsIgnoreCase(propDTO.getIsTncpSchool())) {
				propDTO.setIsTncpSchoolCheck("Y");
			} else {
				propDTO.setIsTncpSchoolCheck("N");
			}
			FORWARD_JSP = "INDEPENDENTBUILDINGSECOND";

		} else if (propForm.getActionName().equalsIgnoreCase("deleteFloor")) {
			String deleteFloorNo = propDTO.getDeleteFloorNo();
			ArrayList<PropertyValuationDTO> newList = new ArrayList<PropertyValuationDTO>();
			ArrayList<PropertyValuationDTO> newResiList = new ArrayList<PropertyValuationDTO>();
			ArrayList<PropertyValuationDTO> newCommList = new ArrayList<PropertyValuationDTO>();
			ArrayList<PropertyValuationDTO> newIndList = new ArrayList<PropertyValuationDTO>();
			ArrayList<PropertyValuationDTO> newSchoolList = new ArrayList<PropertyValuationDTO>();
			ArrayList<PropertyValuationDTO> newHealthList = new ArrayList<PropertyValuationDTO>();
			ArrayList<PropertyValuationDTO> newOtherList = new ArrayList<PropertyValuationDTO>();
			ArrayList<PropertyValuationDTO> newResiCommList = new ArrayList<PropertyValuationDTO>();
			for (int i = 0; i < propDTO.getFloorMarketValueList().size(); i++) {
				PropertyValuationDTO dto = propDTO.getFloorMarketValueList().get(i);
				if (dto.getFloorNo().equalsIgnoreCase(deleteFloorNo)) {

					// below set total construction cost. Added by Roopam on 11
					// feb 2014
					double constCost = propDTO.getConstCost();
					constCost = constCost - dto.getFloorValue();
					propDTO.setConstCost(constCost);

				} else {
					newList.add(dto);
				}
				if ("Y".equalsIgnoreCase(propDTO.getResiFlag())) {
					PropertyValuationDTO resiDTO = propDTO.getResiFloorAreaList().get(i);
					if (resiDTO.getFloorNo().equalsIgnoreCase(deleteFloorNo)) {

					} else {
						newResiList.add(resiDTO);
					}

				}
				if ("Y".equalsIgnoreCase(propDTO.getCommFlag())) {
					PropertyValuationDTO commDTO = propDTO.getCommFloorAreaList().get(i);
					if (commDTO.getFloorNo().equalsIgnoreCase(deleteFloorNo)) {

					} else {
						newCommList.add(commDTO);
					}

				}
				if ("Y".equalsIgnoreCase(propDTO.getIndFlag())) {
					PropertyValuationDTO indDTO = propDTO.getIndFloorAreaList().get(i);
					if (indDTO.getFloorNo().equalsIgnoreCase(deleteFloorNo)) {

					} else {
						newIndList.add(indDTO);
					}

				}
				if ("Y".equalsIgnoreCase(propDTO.getSchoolFlag())) {
					PropertyValuationDTO schoolDTO = propDTO.getSchoolFloorAreaList().get(i);
					if (schoolDTO.getFloorNo().equalsIgnoreCase(deleteFloorNo)) {

					} else {
						newSchoolList.add(schoolDTO);
					}
				}

				if ("Y".equalsIgnoreCase(propDTO.getHealthFlag())) {
					PropertyValuationDTO healthDTO = propDTO.getHealthFloorAreaList().get(i);
					if (healthDTO.getFloorNo().equalsIgnoreCase(deleteFloorNo)) {

					} else {
						newHealthList.add(healthDTO);
					}
				}
				if ("Y".equalsIgnoreCase(propDTO.getOtherFlag())) {
					PropertyValuationDTO otherDTO = propDTO.getOtherFloorAreaList().get(i);
					if (otherDTO.getFloorNo().equalsIgnoreCase(deleteFloorNo)) {

					} else {
						newOtherList.add(otherDTO);
					}
				}
				if ("Y".equalsIgnoreCase(propDTO.getResiCommFlag())) {
					PropertyValuationDTO resiCommDTO = propDTO.getResiCommFloorAreaList().get(i);
					if (resiCommDTO.getFloorNo().equalsIgnoreCase(deleteFloorNo)) {

					} else {
						newResiCommList.add(resiCommDTO);
					}
				}
			}
			propDTO.setFloorMarketValueList(newList);
			propDTO.setResiFloorAreaList(newResiList);
			propDTO.setCommFloorAreaList(newCommList);
			propDTO.setIndFloorAreaList(newIndList);
			propDTO.setSchoolFloorAreaList(newSchoolList);
			propDTO.setHealthFloorAreaList(newHealthList);
			propDTO.setOtherFloorAreaList(newOtherList);
			propDTO.setResiCommFloorAreaList(newResiCommList);
			if (propDTO.getFloorMarketValueList() != null && propDTO.getFloorMarketValueList().size() > 0) {
				propDTO.setDisableEntry("true");
			} else {
				propDTO.setDisableEntry("false");
			}
			if ("construct".equalsIgnoreCase(propDTO.getIsConstruction())) {
				propDTO.setSubclauseList(propBO.getSubclauseDataBuilding(propDTO, language, "YES"));
			} else {
				propDTO.setSubclauseList(propBO.getSubclauseDataBuilding(propDTO, language, "NO"));
			}
			if ("Y".equalsIgnoreCase(propDTO.getIsTncpHealth())) {
				propDTO.setIsTncpHealthCheck("Y");
			} else {
				propDTO.setIsTncpHealthCheck("N");
			}
			if ("Y".equalsIgnoreCase(propDTO.getIsTncpSchool())) {
				propDTO.setIsTncpSchoolCheck("Y");
			} else {
				propDTO.setIsTncpSchoolCheck("N");
			}
			propDTO.setFloorTypeList(new ArrayList<PropertyValuationDTO>());
			if (propDTO.getFloorMarketValueList().size() == 0) {
				for (int i = 0; i < propDTO.getFloorTypeListBuilding().size(); i++) {
					PropertyValuationDTO newDTO = propDTO.getFloorTypeListBuilding().get(i);
					String floorID = newDTO.getFloorId();
					if (floorID.equalsIgnoreCase("3")) {
						propDTO.getFloorTypeList().add(newDTO);
					}
				}
			} else {
				for (int i = 0; i < propDTO.getFloorTypeListBuilding().size(); i++) {
					PropertyValuationDTO newDTO = propDTO.getFloorTypeListBuilding().get(i);
					boolean flag = true;
					String floorID = newDTO.getFloorId();
					for (int j = 0; j < propDTO.getFloorMarketValueList().size(); j++) {
						PropertyValuationDTO newDTO1 = propDTO.getFloorMarketValueList().get(j);
						String floorId1 = newDTO1.getFloorId();
						if (floorID.equalsIgnoreCase(floorId1)) {
							flag = false;
						} else {

						}
					}
					if (flag == false) {
						if (floorID.equalsIgnoreCase("7") || floorID.equalsIgnoreCase("8")) {
							propDTO.getFloorTypeList().add(newDTO);
						}
					} else if (flag == true) {
						propDTO.getFloorTypeList().add(newDTO);
					}
				}
			}
			FORWARD_JSP = "INDEPENDENTBUILDINGSECOND";
		}

		else if ("submitOnlyOpenTerrace".equalsIgnoreCase(propForm.getActionName())) {

			String[] guidelineValue = propBO.getGuidelineValuesBuilding(propDTO);
			if (guidelineValue != null)
				;
			{
				propDTO.setGuideLineId(guidelineValue[0]);
				propDTO.setGuideLineValue(guidelineValue[1]);
				propDTO.setFinalMarketValue(guidelineValue[1]);
				propForm.getPropDTO().setPlotMarketValue(guidelineValue[1]);
				propDTO.setPlotValueFinal(propDTO.getFinalMarketValue());
			}

			String val_id = propBO.submitOpenTerraceDetails(propDTO);
			if ("construct".equalsIgnoreCase(propDTO.getIsConstruction())) {
				propDTO.setConstructedValueBuilding(propDTO.getFinalMarketValue());
				propDTO.setConstructedAreaBuilding(propDTO.getOpenTerraceArea());
				propDTO.setConstructedBuildingType(propBO.getBulidingName(propDTO.getBuildingTypeId(), language));
				propDTO.setBuildingTxnIdAgri(val_id);

				propDTO.setPropertyId("3");
				FORWARD_JSP = propDTO.getAgriPageName();
			} else {
				propForm.getPropDTO().setValuationId(val_id);
				FORWARD_JSP = "finalMarketValue";
			}
		} else if ("submitIndpendentFloor".equalsIgnoreCase(propForm.getActionName())) {
			String[] guidelineValue = propBO.getGuidelineValuesBuilding(propDTO);
			if (guidelineValue != null)
				;
			{
				propDTO.setGuideLineId(guidelineValue[0]);
				propDTO.setGuideLineValue(guidelineValue[1]);
				propDTO.setFinalMarketValue(propBO.getValueWithInternalSubClause(guidelineValue[1], propDTO));
				propForm.getPropDTO().setPlotMarketValue(propDTO.getFinalMarketValue());
				propDTO.setPlotValueFinal(propDTO.getFinalMarketValue());
			}
			String val_id = propBO.submitIndependentFloorDetails(propDTO);
			if ("construct".equalsIgnoreCase(propDTO.getIsConstruction())) {
				propDTO.setConstructedValueBuilding(propDTO.getFinalMarketValue());
				propDTO.setConstructedAreaBuilding(propDTO.getFloorArea());
				propDTO.setConstructedBuildingType(propBO.getBulidingName(propDTO.getBuildingTypeId(), language));
				propDTO.setBuildingTxnIdAgri(val_id);
				propDTO.setPropertyId("3");
				FORWARD_JSP = propDTO.getAgriPageName();
			} else {
				propForm.getPropDTO().setValuationId(val_id);
				FORWARD_JSP = "finalMarketValue";
			}
		} else if ("submitMultiStorey".equalsIgnoreCase(propForm.getActionName())) {
			String[] guidelineValue = propBO.getGuidelineValuesBuilding(propDTO);
			if (guidelineValue != null) {
				propDTO.setGuideLineId(guidelineValue[0]);
				propDTO.setGuideLineValue(guidelineValue[1]);
				propDTO.setFinalMarketValue(propBO.getValueWithInternalSubClause(guidelineValue[1], propDTO));
				propDTO.setPlotValueFinal(propDTO.getFinalMarketValue());
				if (propDTO.getMultiStoreyTypeId().equalsIgnoreCase("18"))// ADDED
				// BY
				// SIMRAN
				// FOR
				// SUBCLAUSE
				// IN
				// CASE
				// OF
				// RESIDENTIAL
				{
					String subIds = propDTO.getBuildingSubId() == null ? "" : propDTO.getBuildingSubId();
					if (subIds != "") {
						logger.debug("not null");
						propDTO.setFinalMarketValue(propBO.getValueWithSubClause(propDTO.getFinalMarketValue(), propDTO));
						propDTO.setPlotValueFinal(propDTO.getFinalMarketValue());
					}

				}
				propForm.getPropDTO().setPlotMarketValue(propDTO.getFinalMarketValue());
			}
			String val_id = propBO.submitMultiStoreyDetails(propDTO, userId);
			if ("construct".equalsIgnoreCase(propDTO.getIsConstruction())) {
				propDTO.setConstructedValueBuilding(propDTO.getFinalMarketValue());
				propDTO.setConstructedAreaBuilding(propDTO.getFloorArea());
				propDTO.setConstructedBuildingType(propBO.getBulidingName(propDTO.getBuildingTypeId(), language));
				propDTO.setPropertyId("3");
				propDTO.setBuildingTxnIdAgri(val_id);

				FORWARD_JSP = propDTO.getAgriPageName();
			} else {

				propForm.getPropDTO().setValuationId(val_id);
				FORWARD_JSP = "finalMarketValue";
			}
		} else if ("submitIndependentBuliding".equalsIgnoreCase(propForm.getActionName())) // for
																							// submitting
																							// independent
																							// building
		// details and calculation
		{
			String floorNo = String.valueOf(propDTO.getAddfloorcounter() + 1);
			propDTO.setAddfloorcounter(propDTO.getAddfloorcounter() + 1);

			PropertyValuationDTO resiAreaDTO = null;
			PropertyValuationDTO commAreaDTO = null;
			PropertyValuationDTO indAreaDTO = null;
			PropertyValuationDTO schoolAreaDTO = null;
			PropertyValuationDTO healthAreaDTO = null;
			PropertyValuationDTO otherAreaDTO = null;
			PropertyValuationDTO resiCommAreaDTO = null;

			if ("Y".equalsIgnoreCase(propDTO.getResiFlag())) {
				propDTO.setResiL2List(propBO.getResiL2Name(language));
				resiAreaDTO = new PropertyValuationDTO();
				String[] resiArea = request.getParameterValues("resiL2Area");
				String rccArea = "";
				String rbcArea = "";
				String tinArea = "";
				String kacchaArea = "";
				if (resiArea != null) {
					rccArea = resiArea[0];
					rbcArea = resiArea[1];
					tinArea = resiArea[2];
					kacchaArea = resiArea[3];
				}
				resiAreaDTO.setFloorNo(floorNo);
				resiAreaDTO.setFloorId(propDTO.getFloorId());
				resiAreaDTO.setKacchaArea(kacchaArea);
				resiAreaDTO.setTinArea(tinArea);
				resiAreaDTO.setRccArea(rccArea);
				resiAreaDTO.setRbcArea(rbcArea);
				propDTO.getResiFloorAreaList().add(resiAreaDTO);
			}
			if ("Y".equalsIgnoreCase(propDTO.getCommFlag())) {
				propDTO.setCommL2List(propBO.getCommL2Name(language));
				commAreaDTO = new PropertyValuationDTO();
				String[] commArea = request.getParameterValues("commL2Area");
				String shopArea = commArea[0];
				String officeArea = commArea[1];
				String godownArea = commArea[2];
				commAreaDTO.setFloorNo(floorNo);
				commAreaDTO.setFloorId(propDTO.getFloorId());
				commAreaDTO.setShopArea(shopArea);
				commAreaDTO.setOfficeArea(officeArea);
				commAreaDTO.setGodownArea(godownArea);
				propDTO.getCommFloorAreaList().add(commAreaDTO);

			}
			if ("Y".equalsIgnoreCase(propDTO.getIndFlag())) {
				propDTO.setIndL2List(propBO.getIndL2Name(language));
				indAreaDTO = new PropertyValuationDTO();
				String[] indArea = request.getParameterValues("indL2Area");
				String rccArea = "";
				String rbcArea = "";
				String tinArea = "";
				String kacchaArea = "";
				if (indArea != null) {
					rccArea = indArea[0];
					rbcArea = indArea[1];
					tinArea = indArea[2];
					kacchaArea = indArea[3];
				}
				indAreaDTO.setFloorNo(floorNo);
				indAreaDTO.setFloorId(propDTO.getFloorId());
				indAreaDTO.setKacchaArea(kacchaArea);
				indAreaDTO.setTinArea(tinArea);
				indAreaDTO.setRccArea(rccArea);
				indAreaDTO.setRbcArea(rbcArea);
				propDTO.getIndFloorAreaList().add(indAreaDTO);
			}
			if ("Y".equalsIgnoreCase(propDTO.getSchoolFlag())) {
				propDTO.setSchoolL2List(propBO.getSchoolL2Name(language));
				schoolAreaDTO = new PropertyValuationDTO();
				String[] schoolArea = request.getParameterValues("schoolL2Area");
				String rccArea = "";
				String rbcArea = "";
				String tinArea = "";
				String kacchaArea = "";
				if (schoolArea != null) {
					rccArea = schoolArea[0];
					rbcArea = schoolArea[1];
					tinArea = schoolArea[2];
					kacchaArea = schoolArea[3];
				}
				schoolAreaDTO.setFloorNo(floorNo);
				schoolAreaDTO.setFloorId(propDTO.getFloorId());
				schoolAreaDTO.setKacchaArea(kacchaArea);
				schoolAreaDTO.setTinArea(tinArea);
				schoolAreaDTO.setRccArea(rccArea);
				schoolAreaDTO.setRbcArea(rbcArea);
				propDTO.getSchoolFloorAreaList().add(schoolAreaDTO);
			}
			if ("Y".equalsIgnoreCase(propDTO.getHealthFlag())) {
				propDTO.setHealthL2List(propBO.getHealthL2Name(language));
				healthAreaDTO = new PropertyValuationDTO();
				String[] healthArea = request.getParameterValues("healthL2Area");
				String rccArea = "";
				String rbcArea = "";
				String tinArea = "";
				String kacchaArea = "";
				if (healthArea != null) {
					rccArea = healthArea[0];
					rbcArea = healthArea[1];
					tinArea = healthArea[2];
					kacchaArea = healthArea[3];
				}
				healthAreaDTO.setFloorNo(floorNo);
				healthAreaDTO.setFloorId(propDTO.getFloorId());
				healthAreaDTO.setKacchaArea(kacchaArea);
				healthAreaDTO.setTinArea(tinArea);
				healthAreaDTO.setRccArea(rccArea);
				healthAreaDTO.setRbcArea(rbcArea);
				propDTO.getHealthFloorAreaList().add(healthAreaDTO);
			}
			if ("Y".equalsIgnoreCase(propDTO.getOtherFlag())) {
				propDTO.setOtherL2List(propBO.getOtherL2Name(language));
				otherAreaDTO = new PropertyValuationDTO();
				String[] otherArea = request.getParameterValues("otherL2Area");
				String rccArea = "";
				String rbcArea = "";
				String tinArea = "";
				String kacchaArea = "";
				if (otherArea != null) {
					rccArea = otherArea[0];
					rbcArea = otherArea[1];
					tinArea = otherArea[2];
					kacchaArea = otherArea[3];
				}
				otherAreaDTO.setFloorNo(floorNo);
				otherAreaDTO.setFloorId(propDTO.getFloorId());
				otherAreaDTO.setKacchaArea(kacchaArea);
				otherAreaDTO.setTinArea(tinArea);
				otherAreaDTO.setRccArea(rccArea);
				otherAreaDTO.setRbcArea(rbcArea);
				propDTO.getOtherFloorAreaList().add(otherAreaDTO);
			}
			if ("Y".equalsIgnoreCase(propDTO.getResiCommFlag())) {
				propDTO.setResiCommL2List(propBO.getResiCommL2Name(language));
				resiCommAreaDTO = new PropertyValuationDTO();
				String[] resiCommArea = request.getParameterValues("resiCommL2Area");
				String rccArea = "";
				String rbcArea = "";
				String tinArea = "";
				String kacchaArea = "";
				String shopArea = "";
				String officeArea = "";
				String godownArea = "";
				if (resiCommArea != null) {
					if ("2".equalsIgnoreCase(propDTO.getFloorId()) || "10".equalsIgnoreCase(propDTO.getFloorId())) {// if
						// MEZZANINE
						// FLOOR
						// or
						// UPPER
						// GROUND
						// FLOOR
						// -
						// id
						// added
						// by
						// Roopam
						// -
						// 27
						// June2015
						shopArea = resiCommArea[0];
						officeArea = resiCommArea[1];
						godownArea = resiCommArea[2];
					} else {
						rccArea = resiCommArea[0];
						rbcArea = resiCommArea[1];
						tinArea = resiCommArea[2];
						kacchaArea = resiCommArea[3];
						shopArea = resiCommArea[4];
						officeArea = resiCommArea[5];
						godownArea = resiCommArea[6];
					}

				}
				resiCommAreaDTO.setFloorNo(floorNo);
				resiCommAreaDTO.setKacchaArea(kacchaArea);
				resiCommAreaDTO.setTinArea(tinArea);
				resiCommAreaDTO.setRccArea(rccArea);
				resiCommAreaDTO.setRbcArea(rbcArea);
				resiCommAreaDTO.setShopArea(shopArea);
				resiCommAreaDTO.setOfficeArea(officeArea);
				resiCommAreaDTO.setGodownArea(godownArea);
				resiCommAreaDTO.setFloorId(propDTO.getFloorId());
				propDTO.getResiCommFloorAreaList().add(resiCommAreaDTO);
			}
			PropertyValuationDTO floorDTO = new PropertyValuationDTO();
			floorDTO.setFloorNo(floorNo);
			floorDTO.setFloorName(propDTO.getFloorName());
			floorDTO.setFloorId(propDTO.getFloorId());

			PropertyValuationDTO[] dto = new PropertyValuationDTO[9];
			dto[0] = floorDTO;
			dto[1] = resiAreaDTO;
			dto[2] = commAreaDTO;
			dto[3] = indAreaDTO;
			dto[4] = schoolAreaDTO;
			dto[5] = healthAreaDTO;
			dto[6] = otherAreaDTO;
			dto[7] = propDTO;
			dto[8] = resiCommAreaDTO;
			floorDTO.setFloorValue(propBO.getFloorValue(dto)); // getting last
			// floor value
			propDTO.getFloorMarketValueList().add(floorDTO);

			// below set total construction cost. Added by Roopam on 11 feb 2014
			double constCost = propDTO.getConstCost();
			constCost = constCost + floorDTO.getFloorValue();
			propDTO.setConstCost(constCost);
			propDTO.setConstructionCost(BigDecimal.valueOf(constCost).toPlainString());

			// below get guidline value for independent building. Added by
			// Roopam on 11 feb 2014
			String[] guidelineValue = propBO.getGuidelineValuesBuilding(propDTO); // getting
																					// plot
			// guideline value
			// for the
			// independent
			// building
			if (guidelineValue != null)
				;
			{

				propDTO.setGuideLineId(guidelineValue[0]);
				propDTO.setGuideLineValue(guidelineValue[1]);

				// below for getting subclause ids
				String subIds = "";
				if (propDTO.getBuildingSubId() != null) {
					subIds = propDTO.getBuildingSubId();
				}

				String[] ids = subIds.split(PropertyValuationConstant.SPLIT_CONSTANT);
				String[] values = propBO.getBuildingValueWithSubClause(guidelineValue[1], ids, propDTO);
				propDTO.setFinalMarketValue(values[0]);
				propDTO.setPlotValueFinal(values[1]);
				propDTO.setConstructionCostFinal(values[2]);// FOR GETTING FINAL
				// VALUATION
				propForm.getPropDTO().setPlotMarketValue(propDTO.getFinalMarketValue());
			}

			String val_id = propBO.submitIndependentBuildingDetails(propDTO);
			if ("construct".equalsIgnoreCase(propDTO.getIsConstruction())) {
				propDTO.setConstructedValueBuilding(propDTO.getFinalMarketValue());
				propDTO.setConstructedAreaBuilding(propDTO.getPlotTotArea());
				propDTO.setConstructedBuildingType(propBO.getBulidingName(propDTO.getBuildingTypeId(), language));
				propDTO.setPropertyId("3");
				propDTO.setBuildingTxnIdAgri(val_id);
				FORWARD_JSP = propDTO.getAgriPageName();
			} else {
				propForm.getPropDTO().setValuationId(val_id);

				FORWARD_JSP = "finalMarketValue";
			}
		} else if ("AgriPrevious".equalsIgnoreCase(propForm.getActionName())) {
			propDTO.setIsConstruction("noconstruct");
			propDTO.setBuildingTxnIdAgri("");
			propDTO.setConstructedValueBuilding("0.0");
			propDTO.setConstructedAreaBuilding("0.0");
			propDTO.setPropertyId("3");
			FORWARD_JSP = propDTO.getAgriPageName();

			if (propDTO.getClrFlag().equalsIgnoreCase("Y")) {
				if (propDTO.getAgriLandTypeId().equalsIgnoreCase("5")) {
					FORWARD_JSP = "AGRILANDUNDIVERTEDCLR";
				}

				else if (propDTO.getAgriLandTypeId().equalsIgnoreCase("6")) {

				} else if (propDTO.getAgriLandTypeId().equalsIgnoreCase("7")) {

				}
			} else {
				FORWARD_JSP = propDTO.getAgriPageName();
			}
		} else if ("floorFreeze".equalsIgnoreCase(propForm.getActionName())) {
			if ("construct".equalsIgnoreCase(propDTO.getIsConstruction())) {
				propDTO.setSubclauseList(propBO.getSubclauseDataBuilding(propDTO, language, "YES"));
			} else {
				propDTO.setSubclauseList(propBO.getSubclauseDataBuilding(propDTO, language, "NO"));
			}
			if ("Y".equalsIgnoreCase(propDTO.getIsTncpHealth())) {
				propDTO.setIsTncpHealthCheck("Y");
			} else {
				propDTO.setIsTncpHealthCheck("N");
			}
			if ("Y".equalsIgnoreCase(propDTO.getIsTncpSchool())) {
				propDTO.setIsTncpSchoolCheck("Y");
			} else {
				propDTO.setIsTncpSchoolCheck("N");
			}
			String floorId = propDTO.getFloorFreezeId();
			if (floorId.equalsIgnoreCase("2") || floorId.equalsIgnoreCase("10"))// for
			// mezzanine
			// and
			// upper
			// ground
			// floor
			{
				propDTO.setFloorFreezeFlag("true");
			} else {
				propDTO.setFloorFreezeFlag("false");
			}
			FORWARD_JSP = "INDEPENDENTBUILDINGSECOND";
		} else if ("secondIndependent".equalsIgnoreCase(propForm.getActionName())) {
			if ("Y".equalsIgnoreCase(propDTO.getIsAkvnFlag())) {
				propDTO.setIsAkvn("Y");
			} else {
				propDTO.setIsAkvn("N");
			}
			if ("Y".equalsIgnoreCase(propDTO.getIsSuperConstructionFlag())) {
				propDTO.setIsSuperConstruction("Y");
			} else {
				propDTO.setIsSuperConstruction("N");
			}
			if ("Y".equalsIgnoreCase(propDTO.getIsSuperConstruction()) && "Y".equalsIgnoreCase(propDTO.getIsAkvn())) {
				propDTO.setIndAreaZero("TRUE");
				propDTO.setAllAreaZero("TRUE");
				propDTO.setPlotIndusArea("0");
				propDTO.setPlotTotArea("0");

			} else {
				propDTO.setIndAreaZero("FALSE");
				propDTO.setPlotIndusArea("");
				propDTO.setPlotTotArea("");
			}
			if ("Y".equalsIgnoreCase(propDTO.getHousingCheckFlag())) {
				propDTO.setHousingCheck("Y");

				propDTO.setPlotTotArea("0");
				propDTO.setAllAreaZero("TRUE");
				if ("Y".equalsIgnoreCase(propDTO.getResiFlag())) {
					propDTO.setPlotResiArea("0");
				}
				if ("Y".equalsIgnoreCase(propDTO.getCommFlag())) {
					propDTO.setPlotCommArea("0");
				}
				if ("Y".equalsIgnoreCase(propDTO.getSchoolFlag())) {
					propDTO.setPlotSchoolArea("0");
				}
				if ("Y".equalsIgnoreCase(propDTO.getHealthFlag())) {
					propDTO.setPlotHealthArea("0");
				}
				if ("Y".equalsIgnoreCase(propDTO.getOtherFlag())) {
					propDTO.setPlotOtherArea("0");
				}
				if ("Y".equalsIgnoreCase(propDTO.getResiCommFlag())) {
					propDTO.setResiCommArea("0");
				}

			} else {
				if (!propDTO.getIndAreaZero().equalsIgnoreCase("TRUE")) {
					propDTO.setAllAreaZero("FALSE");
					propDTO.setHousingCheck("N");
					propDTO.setPlotTotArea("");
					propDTO.setPlotResiArea("");
					propDTO.setPlotCommArea("");
					propDTO.setPlotSchoolArea("");
					propDTO.setPlotHealthArea("");
					propDTO.setPlotOtherArea("");
					propDTO.setResiCommArea("");

				}

			}

			FORWARD_JSP = "INDEPENDENTBUILDINGSECOND";
		}
		if (FORWARD_JSP.equalsIgnoreCase("finalMarketValue")) {

			request.setAttribute("propDTO", propDTO);
			if (propDTO.getFromModule().equalsIgnoreCase("dutyCalc")) {

				// request.setAttribute("valuationId",
				// propForm.getPropDTO().getValuationId());
				// request.setAttribute("propDTO", propDTO);

				FORWARD_JSP = "dutyCalcEfilling";
			}

			else if (propDTO.getFromModule().equalsIgnoreCase("dutyCalcefiling")) {
				FORWARD_JSP = "dutyCalcEfilling";
			}

			else if (propDTO.getFromModule().equalsIgnoreCase("regInit")) {

				// request.setAttribute("propDTO", propDTO);

				FORWARD_JSP = "regInitEfilling";
			} else if (propDTO.getFromModule().equalsIgnoreCase("regInitNonPV")) {

				request.setAttribute("valTxnId", propDTO.getValuationId());
				request.setAttribute("regInitForm", propForm.getRegInitForm());
				request.setAttribute("multiProp", propForm.getRegMultiProp());
				FORWARD_JSP = "regInitNonPVEfilling";
			}

		}

		return mapping.findForward(FORWARD_JSP);
	}

}
