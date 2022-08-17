package com.wipro.igrs.newreginit.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.newPropvaluation.dto.SampadaKhasraClrDTO;

import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.dto.CommonDTO;
import com.wipro.igrs.newreginit.dto.KhasraAdjDetails;
import com.wipro.igrs.newreginit.dto.KhasraOwnerDetails;
import com.wipro.igrs.newreginit.dto.Owner;
import com.wipro.igrs.newreginit.dto.PropertyValuationDTO;
import com.wipro.igrs.newreginit.dto.RegCompletDTO;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.newreginit.form.RegCompletionForm;
import com.wipro.igrs.newreginit.rule.RegInitRule;

/**
 * @author Madan Mohan
 * 
 */
public class RegInitPropertyAction extends BaseAction {
	// private int count = 0;
	// private HashMap map = null;

	private Logger logger = (Logger) Logger.getLogger(RegInitPropertyAction.class);
	private static Proxy proxy;
	// private int floorCount = 0;
	// boolean bol = true;
	/**
	 * @see forwardJsp is used for redirecting
	 */
	// private String forwardJsp ;
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse respons, HttpSession session) throws IOException, ServletException, Exception {
		String forwardJsp = "";
		try {

			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			// HttpSession session = request.getSession();
			// System.out.println("request ij propert action:"+request);

			String languageLocale = "hi";
			if (session.getAttribute("languageLocale") != null) {
				languageLocale = (String) session.getAttribute("languageLocale");
			}

			NumberFormat obj = new DecimalFormat("#0.00");
			if (logger.isDebugEnabled()) {
				logger.debug("execute(ActionMapping, ActionForm, " + "HttpServletRequest, HttpServletResponse) -" + " start");
			}
			/*
			 * PropertiesFileReader pr = PropertiesFileReader.
			 * getInstance("resouces.propertyvaluation");
			 */
			String userId = "";

			userId = (String) session.getAttribute("UserId");
			if (form != null) {
				RegCompletionForm eForm = (RegCompletionForm) form;

				if (request.getParameter("pageName") != null) {
					if (request.getParameter("pageName").equalsIgnoreCase("propertyvalutioan")) {
						eForm.setNumfloors(0);
						eForm.setMapBuildingDetails1(new HashMap());
						eForm.setExchangePropertyList(new ArrayList());
						eForm.setExchangeAgriPropertyListDisp(new ArrayList());
						eForm.setPropertySelected(0);
						eForm.setIsPropertyRemaining(0);
						eForm.setAllPropertiesAdded(0);
						eForm.setSelectedSubClauseList(new ArrayList());
						eForm.setKhasraNoArray("");
						eForm.setKhasraAreaArray("");
						eForm.setLagaanArray("");
						eForm.setRinPustikaArray("");
						eForm.setForwardJsp("");
						eForm.setHdnMapAllPropsPartiesCheck("");
						eForm.setMapAllPropsParties("");
						eForm.setPropListInitialSize(0);
						eForm.setListDtoP(new ArrayList());
						eForm.setMpcstAvailFlag("");
					}

				}

				/*
				 * String distId = null; String tehsilId = null; String
				 * areaTypeId = null; String govMunicipalId = null; String
				 * wardOrPatwariId = null; String mohallaOrVillageId = null;
				 * String mohallaOrVillageName = ""; String wardId = null;
				 * String patwariId = null; String mohallaId = null; String
				 * villageId = null;
				 * 
				 * String propertyTypeId = null; String wardOrPatwariName = "";
				 */

				// IGRSCommon common = new IGRSCommon();
				// PropertyValuationBO valueBo = new PropertyValuationBO();
				// CommonDropDownBD bd = new CommonDropDownBD();
				// RegCompletDTO proDetailsdto = null;
				// RegCompletDTO dto = new RegCompletDTO();

				/*
				 * ArrayList resultSplitPartList = bd.getLocsOfSplitPart();
				 * eForm
				 * .getRegcompletDto().setSplitPartList(resultSplitPartList);
				 */

				// RegCompBO regcompbo = new RegCompBO();
				// RegCompletDTO refdto = eForm.getRegcompletDto();
				RegCommonBO commonBo = new RegCommonBO();
				RegCommonForm regInitForm;
				if (request.getAttribute("regInitForm") != null) {

					regInitForm = (RegCommonForm) request.getAttribute("regInitForm");
					eForm.setRegInitForm(regInitForm);
					eForm.setHidnRegTxnId(regInitForm.getHidnRegTxnId());
					if (regInitForm.getExchangePropertyList() != null && regInitForm.getExchangeAgriPropertyListDisp() != null) {
						eForm.setPropListInitialSize(regInitForm.getExchangePropertyList().size() + regInitForm.getExchangeAgriPropertyListDisp().size());
					} else {
						eForm.setPropListInitialSize(0);
					}

				} else {

					regInitForm = eForm.getRegInitForm();
				}

				// Start:====== Added by Ankita
				/* put a condition here if comming from rishabs module * */

				if (request.getAttribute("propAction") != null) {
					if (request.getAttribute("propAction").toString().equalsIgnoreCase("propAction")) {
						eForm.setRegcompletDto(new RegCompletDTO());
						eForm.getRegcompletDto().setFromAction("regInitProperty.do");

					}
				}
				String formName = eForm.getFormName();
				String actionName = eForm.getActionName();

				/*
				 * if(session.getAttribute("eform")!=null){
				 * PropertyValuationForm pform =
				 * (PropertyValuationForm)session.getAttribute("eform");
				 * 
				 * 
				 * 
				 * eForm.setAreaType(pform.getAreaDTO().getAreaType());
				 * eForm.setDistrict(pform.getDistDTO().getDistrict());
				 * eForm.setTehsil(pform.getTehsilDTO().getTehsil());
				 * eForm.setPropertyType1(pform.getPropDTO().getPropertyType());
				 * 
				 * //this code should actually get data from db table of rishab*
				 * //start here
				 * if(eForm.getPropertyType1().equalsIgnoreCase("plot") ||
				 * eForm.getPropertyType1().equalsIgnoreCase("building")) {
				 * 
				 * eForm.setUnit("sq mtr"); } else {
				 * 
				 * String unit=pform.getPropertyDTO().getLandMeterId();
				 * if(unit.equalsIgnoreCase("2")) { eForm.setUnit("SQM"); } else
				 * if(unit.equalsIgnoreCase("3")) { eForm.setUnit("SQHECTRE"); }
				 * 
				 * } //end here
				 * eForm.setMunicipalBody(pform.getMunicipalDTO().getMunicipalBody
				 * ()); eForm.setMahalla(pform.getMahallaDTO().getMahalla());
				 * eForm.setWard(pform.getWardDTO().getWard());
				 * //eForm.setWardId(pform.getWardDTO().getWardId());
				 * eForm.setNumfloors(pform.getMapBuildingDetails().size());
				 * eForm
				 * .setTotalSqMeter(pform.getPropertyDTO().getTotalSqMeter());
				 * String wardstatus=pform.getPropertyDTO().getPatwariStatus();
				 * 
				 * if (wardstatus.equalsIgnoreCase("W")) { wardstatus="Ward"; }
				 * else { wardstatus="Patwari/Halka"; }
				 * eForm.setPatwariStatus(wardstatus);
				 * 
				 * System.out.println(eForm.getNumfloors());
				 * eForm.setMapBuildingDetails1(pform.getMapBuildingDetails());
				 * eForm.setSubCls(pform.getSubClauseList());
				 * System.out.println(pform.getSubClauseList().size());
				 * System.out.println(eForm.getMapBuildingDetails1().size());
				 * HashMap mapBuild = pform.getMapBuildingDetails();
				 * 
				 * }
				 */
				// End:=======Added by Ankita
				// following added by roopam to remove session and fetch
				// property details from database.
				String pageName = request.getParameter("pageName");
				if (RegInitConstant.NEXT_TO_PROPERTY_ACTION.equals(actionName) || RegInitConstant.CHANGE_PROPERTY_ACTION.equals(actionName) || (pageName != null && "propertyvalutioan".equalsIgnoreCase(pageName))) {

					eForm.setHidnRegTxnId(eForm.getRegInitForm().getHidnRegTxnId());
					eForm.setDeedID(eForm.getRegInitForm().getDeedID());
					// eForm.setValuationId(eForm.getRegInitForm().getValuationId());

					logger.debug("reg id in property action is----->" + eForm.getHidnRegTxnId());
					logger.debug("deed id in property action is----->" + eForm.getDeedID());

					// added by ankit for plant and machinery -- Start -- >
					eForm.setInstID(eForm.getRegInitForm().getInstID());
					logger.info("Instrument id in property action is " + eForm.getInstID());

					eForm.setMovPropFlag(commonBo.getMovPropFlag(String.valueOf(eForm.getDeedID()), String.valueOf(eForm.getInstID())));
					logger.info("setMovPropFlag in property action is " + eForm.getMovPropFlag());
					// added by ankit for plant and machinery - end -->

					// if((regInitForm.getExchangePropertyList().size()>0 ||
					// regInitForm.getAgriPropertyList().size()>0) &&
					// !(RegInitConstant.CHANGE_PROPERTY_ACTION.equals(actionName))){
					if ((regInitForm.getExchangePropertyList().size() > 0) && !(RegInitConstant.CHANGE_PROPERTY_ACTION.equals(actionName))) {

						if (regInitForm.getExchangePropertyList().size() > 0) {
							eForm.getRegcompletDto().setMultiPropsFromAgri("N");
							eForm.setExchangePropertyList(regInitForm.getExchangePropertyList());
						}/*
						 * else if(regInitForm.getAgriPropertyList().size()>0){
						 * eForm.getRegcompletDto().setMultiPropsFromAgri("Y");
						 * eForm
						 * .setExchangePropertyList(regInitForm.getAgriPropertyList
						 * ()); }
						 */
						eForm.setExchangeAgriPropertyListDisp(regInitForm.getExchangeAgriPropertyListDisp());
						logger.debug("exchange property list--->" + eForm.getExchangePropertyList());
						eForm.setPropertySelected(0);
						eForm.setClrErrorFlag("0");
						// eForm.setIsPropertyRemaining(1);
						if (eForm.getExchangePropertyList().size() == 1) {
							eForm.setAllPropertiesAdded(1);
							eForm.setIsPropertyRemaining(0);
						} else {
							eForm.setIsPropertyRemaining(1);
						}
					}

					if (RegInitConstant.CHANGE_PROPERTY_ACTION.equals(actionName)) {
						eForm.setActionName("");
						regInitForm.setTermsCondPartyAppKhasra("");
						eForm.setNumfloors(0);
						eForm.setMapBuildingDetails1(new HashMap());
						eForm.setSelectedSubClauseList(new ArrayList());
						eForm.setPropertyId(eForm.getRegcompletDto().getSelectedPropId());
						eForm.setValuationId(eForm.getRegcompletDto().getSelectedPropValId());

						if (eForm.getRegcompletDto().getSelectedPropValId().equalsIgnoreCase("select")) {
							eForm.setPropertySelected(0);
							eForm.setClrErrorFlag("0");
							eForm.setPropertyId(eForm.getRegInitForm().getPropertyId());
						} else {
							eForm.setPropertyId(eForm.getRegcompletDto().getSelectedPropId());

							if (eForm.getPropertyId() != null) {
								// Added by Rakesh to flush form value
								eForm.getRegcompletDto().setRicircle("");
								eForm.getRegcompletDto().setKhasaraNum1("");
								eForm.getRegcompletDto().setKhasraSellableArea("");
								eForm.getRegcompletDto().setClrKhasraArea("");
								eForm.getRegcompletDto().setKhasraArea1("");
								eForm.getRegcompletDto().setRinpustikaNum("");
								eForm.getRegcompletDto().setPropAddress("");
								eForm.getRegcompletDto().setPropLandmark("");
								eForm.getRegcompletDto().setPlotnum("");
								eForm.getRegcompletDto().setSheetnum("");
								eForm.getRegcompletDto().setLayoutdet("");
								eForm.getRegcompletDto().setVikasId("");
								eForm.getRegcompletDto().setNorth("");
								eForm.getRegcompletDto().setSouth("");
								eForm.getRegcompletDto().setEast("");
								eForm.getRegcompletDto().setWest("");
								// Added by Rakesh to flush form value

								String propertyId = eForm.getPropertyId();
								String valTxnId = commonBo.getValTxnID(eForm.getPropertyId());

								eForm.setValTxnID(valTxnId);

								String getPropType = commonBo.getPropertyTypeID(propertyId);
								// added by akansha for clr
								String checkClrFlagByValuationID = commonBo.getClrByClrTable(valTxnId);

								if (checkClrFlagByValuationID != null && !checkClrFlagByValuationID.isEmpty()) {

									if (getPropType.equalsIgnoreCase("3") && checkClrFlagByValuationID.equalsIgnoreCase("Y")) {
										boolean flag = commonBo.updatePropertyKhasraDetls(eForm, userId);

										if (flag) {

											ArrayList khasraDetails = commonBo.getkhasraDetails(propertyId);
											// khasraArrayList
											ArrayList subList;
											RegCompletDTO paramDto1;
											ArrayList<RegCompletDTO> paramList1 = new ArrayList<RegCompletDTO>();
											for (int i = 0; i < khasraDetails.size(); i++) {
												subList = (ArrayList) khasraDetails.get(i);
												paramDto1 = new RegCompletDTO();

												eForm.getRegcompletDto().setRinpustikaNum(subList.get(4).toString());

												paramDto1.setClrkhasraNo(subList.get(0).toString());
												paramDto1.setKhasaraNum1(subList.get(0).toString());
												paramDto1.setKhasraId(subList.get(1).toString());

												paramDto1.setClrKhasraArea(subList.get(2).toString());
												paramDto1.setKhasraSellableArea(subList.get(3).toString());
												paramDto1.setKhasraArea1(subList.get(3).toString());
												paramDto1.setRicircle(subList.get(5).toString());
												paramDto1.setRinpustikaNum(subList.get(4).toString());
												paramDto1.setNorth("");
												paramDto1.setSouth("");
												paramDto1.setEast("");
												paramDto1.setWest("");
												paramDto1.setLagaan("");
												paramList1.add(paramDto1);
											}

											eForm.setKhasraArrayList(paramList1);

										}

										eForm.setClrFlag(commonBo.getClrFlag(eForm.getPropertyId()));
										regInitForm.setClrFlg(eForm.getClrFlag());
										eForm.setClrErrorFlag("0");
										eForm.setPropertySelected(1);

										/*
										 * for(int k = 0
										 * ;k<eForm.getKhasraArrayList().size();
										 * k++) { //Added by Rakesh for CLR
										 * Integration: Start
										 * if(regInitForm.getClrFlg()!=null &&
										 * eForm
										 * .getKhasraArrayList().get(k).getKhasraId
										 * ()!=null){
										 * 
										 * 
										 * eForm.setClrErrorFlag("0");
										 * eForm.setPropertySelected(1);
										 * if(regInitForm
										 * .getClrFlg().equalsIgnoreCase("Y")){
										 * 
										 * }
										 * 
										 * 
										 * 
										 * }
										 * 
										 * }
										 */
									}
								}
							}

							// Added by Rakesh for CLR Integration: end

						}
					}

					logger.debug("prop id in property action is----->" + eForm.getPropertyId());
					if ((eForm.getExchangePropertyList().size() == 0 && (regInitForm.getValuationId() != null && regInitForm.getValuationId() != "")) || (eForm.getExchangePropertyList().size() > 0 && (eForm.getRegcompletDto().getSelectedPropValId() != null && !eForm.getRegcompletDto().getSelectedPropValId().equalsIgnoreCase("select")))) {

						// String pform[]=new String[16];
						ArrayList pform = new ArrayList();

						/*
						 * if(eForm.getExchangePropertyList().size()==0) { pform
						 * =commonBo.getPropDetlsForDashboard(regInitForm.
						 * getValuationId());
						 * 
						 * } else {
						 */
						pform = commonBo.getPropDetlsForDashboard(eForm.getRegcompletDto().getSelectedPropValId(), eForm.getRegcompletDto().getSelectedPropId());

						ArrayList rowList = new ArrayList();
						if (pform != null && pform.size() == 1)
							rowList = (ArrayList) pform.get(0);
						// }

						// if(pform[5].trim()!=null)
						/*
						 * eForm.setAreaType(pform[5].trim()); //else //
						 * eForm.setAreaType("-");
						 * eForm.setDistrict(pform[1].trim());
						 * eForm.setTehsil(pform[3].trim());
						 * eForm.setPropertyType1(pform[14].trim());
						 */

						/*
						 * if(eForm.getPropertyType1().equalsIgnoreCase("plot")
						 * ||
						 * eForm.getPropertyType1().equalsIgnoreCase("building"
						 * ))
						 */

						// if(rowList.get(13)!=null){
						eForm.setPropertyTypeId(rowList.get(0).toString());
						eForm.getRegcompletDto().setGovmunciplId(rowList.get(3) != null ? rowList.get(3).toString() : "");
						eForm.getRegcompletDto().setAreaTypeId(rowList.get(4) != null ? rowList.get(4).toString() : "");
						eForm.setInstID(regInitForm.getInstID());
						eForm.setDeedID(regInitForm.getDeedID());

						// added by ankit for GIS work
						String valuationID = commonBo.getValTxnID(eForm.getPropertyId());
						String munGISFlag = commonBo.getMunGISFlag(eForm.getRegcompletDto().getGovmunciplId(), valuationID);
						eForm.getRegcompletDto().setMunGISFlag(munGISFlag);
						logger.info("municipal GIS flag from DB  - " + munGISFlag);
						eForm.getRegcompletDto().setGisFlag(munGISFlag);

						// }else{
						// eForm.setPropertyTypeId("0");
						// }
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {

							eForm.setPropertyType1(rowList.get(1).toString());

						} else {

							eForm.setPropertyType1(rowList.get(2).toString());

						}

						if (eForm.getPropertyTypeId().equalsIgnoreCase("1")) { // plot

							// eForm.getValuationId();
							eForm.getRegcompletDto().setTotalSqMeter(commonBo.getPlotTotArea(eForm.getValuationId()));
							eForm.setClrFlag("");

						} else if (eForm.getPropertyTypeId().equalsIgnoreCase("2")) { // building

							eForm.setClrFlag("");

						} else if (eForm.getPropertyTypeId().equalsIgnoreCase("3")) { // agri
							System.out.println("Hi");
							System.out.println();
							// eForm.getValuationId();

							// eForm.setClrFlag(commonBo.getClrFlag(eForm.getPropertyId()));
							// String valuationID =
							// commonBo.getValTxnID(eForm.getPropertyId());
							String clrF = commonBo.getClrByClrTable(valuationID);

							if (clrF != null && !clrF.isEmpty()) {

								if (clrF.equalsIgnoreCase("Y")) {

									eForm.setClrFlag(clrF);
								} else {
									eForm.setClrFlag("");
								}
							} else {
								eForm.setClrFlag("");
							}
							regInitForm.setClrFlg(eForm.getClrFlag());
							eForm.getRegcompletDto().setTotalSqMeter(commonBo.getAgriTotArea(eForm.getValuationId()));

						}

						commonBo.getMPCSTParams(eForm.getValuationId(), eForm, eForm.getRegcompletDto());

						if (eForm.getPropertySelected() == 2) {

						} else {
							eForm.setPropertySelected(1);
						}

					}

					request.setAttribute("deedId", regInitForm.getDeedID());
					eForm.setActionName("");

					if (eForm.getClrFlag() != null && !eForm.getClrFlag().isEmpty()) {
						if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

							forwardJsp = "propdetailsClr";
						}

						else {
							forwardJsp = "propdetails";

						}

					} else {

						forwardJsp = "propdetails";
					}
				}
				// end of addition by roopam

				if (pageName != null && "propertyvalutioan".equalsIgnoreCase(pageName)) {

					eForm.getRegcompletDto().getKhasraDetlsDisplay().add(new CommonDTO());
					request.setAttribute("deedId", regInitForm.getDeedID());
					if (eForm.getClrFlag() != null) {
						if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

							forwardJsp = "propdetailsClr";
						}

						else {
							forwardJsp = "propdetails";

						}

					} else {

						forwardJsp = "propdetails";
					}

					if (regInitForm.getFromAdjudication() == 1) {
						// PropertyValuationForm pform = null;
						// regInitForm.setFromAdjudication(eForm.getFromAdjudication());
						// eForm.setFromAdjudication(1);
						// session.setAttribute("fnName","Adjudication");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_ENGLISH);
						} else {
							session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_HINDI);
						}

					}// else{
					// eForm.setFromAdjudication(0);
					// }

				}

				if (pageName != null && ("propertyView".equalsIgnoreCase(pageName) || ("propertyViewClr".equalsIgnoreCase(pageName)))) {

					if (RegInitConstant.MODIFY_PROPERTY_ACTION.equals(actionName)) {
						String valuationID = commonBo.getValTxnID(regInitForm.getPropertyId());
						eForm.setActionName(RegInitConstant.NO_ACTION);
						regInitForm.setActionName(RegInitConstant.NO_ACTION);
						eForm.setHidnRegTxnId(regInitForm.getHidnRegTxnId());
						eForm.setPropertyId(regInitForm.getPropertyId());
						eForm.setDeedID(regInitForm.getDeedID());
						HashMap propMap = regInitForm.getMapPropertyTransPartyDisp();
						
						commonBo.getPropertyDetsForViewConfirmModify(eForm, propMap, regInitForm.getDeedID());
						ArrayList list = commonBo.getMPCSTdata(regInitForm.getPropertyId());
						if (list != null && list.size() == 1) {
							ArrayList rowList = (ArrayList) list.get(0);

							String availFlag = rowList.get(2) != null ? rowList.get(2).toString() : "N";

							eForm.setMpcstAvailFlag(availFlag);
						}
						String clrF = commonBo.getClrByClrTable(valuationID);

						if (clrF != null && !clrF.isEmpty()) {

							if (clrF.equalsIgnoreCase("Y")) {

								eForm.setClrFlag(clrF);
							} else {
								eForm.setClrFlag("");
							}
						} else {
							eForm.setClrFlag("");
						}
						regInitForm.setClrFlg(eForm.getClrFlag());
						// request.setAttribute("deedId",
						// regInitForm.getDeedID());
						if ("propertyView".equalsIgnoreCase(pageName)) {
							forwardJsp = "editPropDetails";
						}
						if ("propertyViewClr".equalsIgnoreCase(pageName)) {
							forwardJsp = "editPropDetailsCLR";
						}

					}

				}

				if (request.getParameter("label") != null) {
					String label = null;
					label = (String) request.getParameter("label");
					if (label != null && label != "") {
						if (label.equalsIgnoreCase("displayProp")) {
							request.setAttribute("deedId", regInitForm.getDeedID());
							eForm.setActionName("");
							actionName = "";
							if (eForm.getClrFlag() != null) {
								if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

									forwardJsp = "propdetailsClr";
								}

								else {
									forwardJsp = "propdetails";

								}

							} else {

								forwardJsp = "propdetails";
							}
						}
						if (label.equalsIgnoreCase("displayPropEdit")) {
							request.setAttribute("deedId", regInitForm.getDeedID());
							eForm.setActionName("");
							actionName = "";

							if (eForm.getClrFlag() != null || regInitForm.getClrFlg() != null) {
								if (eForm.getClrFlag().equalsIgnoreCase("Y") || regInitForm.getClrFlg().equalsIgnoreCase("Y")) {

									forwardJsp = "editPropDetailsCLR";
								} else {
									forwardJsp = "editPropDetails";

								}
								// forwardJsp= "editPropDetails";

							} else {
								// test
								logger.debug("testPropId" + eForm.getPropertyId());
								forwardJsp = "editPropDetails";
							}

						}
					}
				}
				// below code by roopam
				if (RegInitConstant.REGCOM_PROPERTY_SEARCH.equals(formName)) {

					if (RegInitConstant.ZIP_MPCST_ACTION.equals(actionName)) {
						ArrayList errorList = new ArrayList();
						try {

							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							ZipOutputStream zos = new ZipOutputStream(baos);
							ZipEntry entry = new ZipEntry(eForm.getRegcompletDto().getMpcst1DocumentName());
							entry.setSize(eForm.getRegcompletDto().getMpcst1DocContents().length);
							zos.putNextEntry(entry);
							zos.write(eForm.getRegcompletDto().getMpcst1DocContents());

							ZipEntry entry2 = new ZipEntry(eForm.getRegcompletDto().getMpcst2DocumentName());
							entry2.setSize(eForm.getRegcompletDto().getMpcst2DocContents().length);
							zos.putNextEntry(entry2);
							zos.write(eForm.getRegcompletDto().getMpcst2DocContents());

							zos.closeEntry();
							zos.close();
							byte[] zipFile = baos.toByteArray();

							logger.debug("zip byte[]:" + zipFile);

							commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);

							String fileExt = "zip";
							RegInitRule rule = new RegInitRule();

							if (rule.isError()) {
								errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);

								request.setAttribute("errorsList", errorList);
							} else {

								eForm.getRegcompletDto().setPropMapDocumentName("MPCST.zip");
								eForm.getRegcompletDto().setPropMapDocContents(zipFile);
								// eForm.getRegcompletDto().setPropMapDocumentSize(photoSize);
								eForm.getRegcompletDto().setPropMapFilePath(RegInitConstant.FILE_NAME_PROP_MAP + fileExt);
								eForm.getRegcompletDto().setPropMapPartyOrProp(RegInitConstant.FILE_UPLOAD_PROP);
								eForm.getRegcompletDto().setPropMapUploadType(RegInitConstant.FILE_NAME_PROP_MAP + fileExt);

							}
						} catch (Exception e) {
							logger.debug(e.getMessage(), e);
							errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
							request.setAttribute("errorsList", errorList);
						}

						// Added by Rakesh

						if (eForm.getClrFlag() != null || regInitForm.getClrFlg() != null) {
							if (eForm.getClrFlag().equalsIgnoreCase("Y") || regInitForm.getClrFlg().equalsIgnoreCase("Y")) {
								forwardJsp = "propdetailsZipClr";
							} else {
								forwardJsp = "propdetailsZip";

							}

						} else {

							forwardJsp = "propdetailsZip";
						}

						// forwardJsp="propdetailsZip";

						// eForm.getRegcompletDto().setPropAddress("here");
						request.setAttribute("deedId", eForm.getRegInitForm().getDeedID());
						// request.setAttribute("roleIdTrns",
						// regForm.getPartyTypeTrns());
					}

					if (request.getParameter("mpcstUpload") != null && request.getParameter("mpcstUpload").toString().equalsIgnoreCase("yes")) {

						eForm.getRegcompletDto().setMpcst1(null);
						eForm.getRegcompletDto().setMpcst1DocContents(null);
						eForm.getRegcompletDto().setMpcst1DocumentName("");
						eForm.getRegcompletDto().setMpcst1FilePath("");

						eForm.getRegcompletDto().setMpcst2(null);
						eForm.getRegcompletDto().setMpcst2DocContents(null);
						eForm.getRegcompletDto().setMpcst2DocumentName("");
						eForm.getRegcompletDto().setMpcst2FilePath("");

						forwardJsp = "mpcstUploads";
						request.setAttribute("deedId", eForm.getRegInitForm().getDeedID());

					}

					if (RegInitConstant.ADD_KHASRA_ACTION.equals(actionName) || RegInitConstant.ADD_KHASRA_EDIT_ACTION.equals(actionName)) {

						try {
							// NoEncumDTO floorDTO = ncForm.getNoEncumDTO();
							ArrayList<CommonDTO> khasraDets = eForm.getRegcompletDto().getKhasraDetlsDisplay();
							commonBo.refreshKhasraData(khasraDets, request);
							khasraDets.add(new CommonDTO());

							eForm.getRegcompletDto().setKhasraDetlsDisplay(khasraDets);
							// request.setAttribute("noencumfrm", ncForm);
						} catch (Exception exception) {
							logger.error(exception.getMessage(), exception);
						}

						request.setAttribute("deedId", regInitForm.getDeedID());
						if (RegInitConstant.ADD_KHASRA_ACTION.equals(actionName)) {
							forwardJsp = "propdetails";
						} else {
							forwardJsp = "editPropDetails";
						}

					}

					if (RegInitConstant.DEL_KHASRA_ACTION.equals(actionName) || RegInitConstant.DEL_KHASRA_EDIT_ACTION.equals(actionName)) {
						try {
							// String[] index =
							// eForm.getRegcompletDto().getSelectedIndex();

							String[] index = request.getParameterValues("selectDeleteNot");

							ArrayList<CommonDTO> khasraDets = eForm.getRegcompletDto().getKhasraDetlsDisplay();
							commonBo.refreshKhasraData(khasraDets, request);
							// khasraDets.add(new CommonDTO());

							if (index != null && index.length > 0) {
								for (int i = 0; i < index.length; i++) {
									khasraDets.remove(Integer.parseInt(index[i].trim()) - i);
									// commonBo.refreshKhasraData(khasraDets,
									// request);
								}

							}

							// commonBo.refreshKhasraData(khasraDets, request);

							eForm.getRegcompletDto().setKhasraDetlsDisplay(khasraDets);

							// request.setAttribute("noencumfrm", ncForm);
						} catch (Exception exception) {
							logger.error(exception.getMessage(), exception);
						}
						request.setAttribute("deedId", regInitForm.getDeedID());
						if (RegInitConstant.DEL_KHASRA_ACTION.equals(actionName)) {
							forwardJsp = "propdetails";
						} else {
							forwardJsp = "editPropDetails";
						}
					}

					if (RegInitConstant.NEXT_TO_MAPPING_ACTION.equals(actionName) || RegInitConstant.SAVE_PROP_ACTION.equals(actionName)) {

						String getPropTypeCheck = commonBo.getPropertyTypeID(eForm.getPropertyId());

						String valuationID = commonBo.getValTxnID(eForm.getPropertyId());
						String clrF = "N";

						clrF = commonBo.getClrByClrTable(valuationID);

						if (clrF.equalsIgnoreCase("Y") && getPropTypeCheck.equalsIgnoreCase("3")) {
							eForm.setClrFlag(clrF);
						}

						ArrayList<CommonDTO> khasraDets = eForm.getRegcompletDto().getKhasraDetlsDisplay();
						if (khasraDets != null && khasraDets.size() > 0) {
							// commonBo.refreshKhasraData(khasraDets, request);

							/*
							 * if(eForm.getClrFlag()!=null){
							 * if(eForm.getClrFlag().equalsIgnoreCase("Y")){
							 * 
							 * commonBo.refreshKhasraDataClr(eForm, request); }
							 * 
							 * else{ commonBo.refreshKhasraData(khasraDets,
							 * request);
							 * 
							 * }
							 * 
							 * 
							 * } else{
							 * 
							 * commonBo.refreshKhasraData(khasraDets, request);
							 * }
							 */

						}

						// if((regInitForm.getDeedID()==RegInitConstant.DEED_EXCHANGE
						// ||
						// regInitForm.getDeedID()==RegInitConstant.DEED_PARTITION_PV)
						// &&
						// !RegInitConstant.SAVE_PROP_ACTION.equals(actionName))
						if ((eForm.getExchangePropertyList().size() > 0) && !RegInitConstant.SAVE_PROP_ACTION.equals(actionName))

						{
							CommonDTO dto1 = new CommonDTO();
							for (int i = 0; i < eForm.getExchangePropertyList().size(); i++) {

								dto1 = (CommonDTO) eForm.getExchangePropertyList().get(i);
								if (dto1.getValuationId().equalsIgnoreCase(eForm.getRegcompletDto().getSelectedPropValId())) {

									regInitForm.setPropertyId(dto1.getPropertyId());
									eForm.setPropertyId(dto1.getPropertyId());
									break;
								}

							}
						}

						// added by ankit for GIS work
						if ("Y".equalsIgnoreCase(eForm.getRegcompletDto().getMunGISFlag()) || "Y".equalsIgnoreCase(eForm.getRegcompletDto().getGisFlag())) {
							logger.info("regInitForm.getGisIdPrevious() - " + regInitForm.getGisIdPrevious());
							boolean isGISIDExist = false;
							isGISIDExist = commonBo.checkGISDuplicate(eForm.getRegcompletDto().getGisReferenceKey(), eForm.getPropertyId());
							/*
							 * if(RegInitConstant.SAVE_PROP_ACTION.equals(actionName
							 * )){
							 * if(eForm.getRegcompletDto().getGisReferenceKey
							 * ().equalsIgnoreCase
							 * (regInitForm.getGisIdPrevious())){ isGISIDExist =
							 * false; }else{ isGISIDExist =
							 * commonBo.checkGISDuplicate
							 * (eForm.getRegcompletDto().getGisReferenceKey());
							 * } }else{ isGISIDExist =
							 * commonBo.checkGISDuplicate
							 * (eForm.getRegcompletDto().getGisReferenceKey());
							 * }
							 */
							if (isGISIDExist) {
								ArrayList errorList = new ArrayList();
								request.setAttribute("deedId", regInitForm.getDeedID());
								errorList.add(RegInitConstant.gis_duplicate_error);
								request.setAttribute("errorsList", errorList);
								// request.setAttribute("error",
								// " GIS ID already exist \n\n जीआईएस आईडी पहले से मौजूद है");
								if (RegInitConstant.SAVE_PROP_ACTION.equals(actionName)) {
									if (eForm.getClrFlag() != null) {
										if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

											forwardJsp = "editPropDetailsCLR";
										}

										else {
											forwardJsp = "editPropDetails";

										}

									} else {

										forwardJsp = "editPropDetails";
									}
								} else {
									if (eForm.getClrFlag() != null) {
										if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

											forwardJsp = "propdetailsClr";
										}

										else {
											forwardJsp = "propdetails";

										}

									} else {

										forwardJsp = "propdetails";
									}
								}

								return mapping.findForward(forwardJsp);
							}
						}

						CommonAction action = new CommonAction();
						if (!RegInitConstant.SAVE_PROP_ACTION.equals(actionName)) {
							String returnJsp = commonBo.validateMPCST(eForm.getRegcompletDto(), eForm, request, eForm.getPropertyId());
							if (returnJsp.equalsIgnoreCase("error")) {
								request.setAttribute("deedId", regInitForm.getDeedID());
								if (eForm.getClrFlag() != null) {
									if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

										forwardJsp = "propdetailsClr";
									}

									else {
										forwardJsp = "propdetails";

									}

								} else {

									forwardJsp = "propdetails";
								}
								return mapping.findForward(forwardJsp);
							}
						}
						if (RegInitConstant.NEXT_TO_MAPPING_ACTION.equals(actionName)) {

							if (eForm.getClrFlag() != null) {
								if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

									commonBo.refreshKhasraDataClr(eForm, request);
								}

								else {
									commonBo.refreshKhasraData(khasraDets, request);

								}

							} else {

								commonBo.refreshKhasraData(khasraDets, request);
							}

							boolean check = true;
							ArrayList<CommonDTO> dto = eForm.getListDtoP();
							if (dto != null && dto.size() > 0) {
								for (int i = 0; i < dto.size(); i++) {
									CommonDTO dtos = dto.get(i);
									dtos.setDocumentName(commonBo.getNewFileName(dtos.getDocumentName(), Integer.toString(i)));
									String checkUpload = action.uploadFile(eForm.getHidnRegTxnId(), dtos.getDocContents(), eForm.getPropertyId(), "02", dtos.getDocumentName());
									if (checkUpload == null) {
										check = false;
										break;
									} else {
										dtos.setDocumentPath(checkUpload);
										check = true;
									}

								}

								if (check == true) {
									check = commonBo.insertAdditionalUploads(eForm);
								}

							} else {
								check = true;
							}

							// CLR_insertion:Start

							/*
							 * String getPropTypeCheck =
							 * commonBo.getPropertyTypeID
							 * (eForm.getPropertyId());
							 * 
							 * String valuationID =
							 * commonBo.getValTxnID(eForm.getPropertyId());
							 * String clrF =
							 * commonBo.getClrByClrTable(valuationID);
							 */

							/*
							 * if(clrF!=null && !clrF.isEmpty()){
							 * if(getPropTypeCheck.equalsIgnoreCase("3") &&
							 * clrF.equalsIgnoreCase("y")){
							 * 
							 * 
							 * 
							 * boolean flag =
							 * commonBo.updatePropertyKhasraDetls1(eForm);
							 * if(flag){ boolean
							 * flag2=commonBo.insertClrPropOwnerDetails(eForm);
							 * }
							 * 
							 * }}
							 */
							// CLR_insertion:End
						} else {

							if (eForm.getClrFlag() != null) {
								if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

									commonBo.refreshKhasraDataClrEdit(eForm, request);
								}

								else {
									commonBo.refreshKhasraData(khasraDets, request);

								}

							} else {

								commonBo.refreshKhasraData(khasraDets, request);
							}

							String path = "";

							try {
								// PropertiesFileReader pr =
								// PropertiesFileReader.getInstance("resources.igrs");

								path = pr.getValue("upload.location");
							} catch (Exception e) {
								logger.debug("exception in uploadFile : " + e);
								logger.debug(e.getMessage(), e);
							}
							// String getPropTypeCheck =
							// commonBo.getPropertyTypeID(eForm.getPropertyId());

							// String valuationID =
							// commonBo.getValTxnID(eForm.getPropertyId());
							// String clrF =
							// commonBo.getClrByClrTable(valuationID);

							/*
							 * if(clrF!=null && !clrF.isEmpty()){
							 * if(getPropTypeCheck.equalsIgnoreCase("3") &&
							 * clrF.equalsIgnoreCase("y")){
							 * 
							 * 
							 * 
							 * boolean flag =
							 * commonBo.updatePropertyKhasraDetls1(eForm);
							 * if(flag){ boolean
							 * flag2=commonBo.insertClrPropOwnerDetails(eForm);
							 * }
							 * 
							 * }}
							 */
							boolean checkAdditionalUploads = true;
							path = path + RegInitConstant.FILE_UPLOAD_PATH + eForm.getHidnRegTxnId() + RegInitConstant.FILE_UPLOAD_PATH_PROP + eForm.getPropertyId() + "/";
							// Added by rakesh1: Start
							/*
							 * String getPath=pr.getValue("upload.location");
							 * getPath=getPath+
							 * RegInitConstant.FILE_UPLOAD_PATH+
							 * eForm.getHidnRegTxnId
							 * ()+RegInitConstant.FILE_UPLOAD_PATH_PROP
							 * +eForm.getPropertyId()+"/"; String
							 * getKhasraPath=getPath
							 * +pr.getValue("clr_khasra_pdf"); String
							 * getMapPath=getPath+pr.getValue("clr_map_pdf");
							 * byte[]
							 * khasraPathContent=DMSUtility.getDocumentBytes
							 * (getKhasraPath); byte[]
							 * mapPathContent=DMSUtility.
							 * getDocumentBytes(getMapPath);
							 * eForm.getRegcompletDto
							 * ().setKhasraPathClr(getKhasraPath);
							 * eForm.getRegcompletDto
							 * ().setMapPathClr(getMapPath);
							 * eForm.getRegcompletDto
							 * ().setKhasraPdfContents(khasraPathContent);
							 * eForm.
							 * getRegcompletDto().setMapPdfContents(mapPathContent
							 * );
							 */
							// Added by rakesh1: End

							try {

								// By Mohit
								// File f = new File(path);
								// FileUtils.cleanDirectory(f);
								// commonBo.deleteAllRemovedUploadsP(eForm.getHidnRegTxnId(),eForm.getPropertyId());
								// Added by Rakesh to delete all files without
								// removing subfolder from directory

								File dir = new File(path);
								File[] directoryListing = dir.listFiles();
								if (directoryListing != null) {
									for (File child : directoryListing) {

										if (child.isDirectory()) {
											logger.debug("SubFolders path which are not deleting from Directory: " + child.getAbsolutePath());

										}
										if (child.isFile()) {
											logger.debug("Deleting File path from Directory: " + child.getAbsolutePath());
											child.delete();
										}
										System.out.println(child.getAbsolutePath());
									}
								} else {

								}

							} catch (Exception e) {
								request.setAttribute("error", "Unable to clean directory.");
								// checkAdditionalUploads = true;
								logger.debug(e.getMessage(), e);
							} finally {
								try {

									// By Mohit
									// File f = new File(path);
									// FileUtils.cleanDirectory(f);
									checkAdditionalUploads = true;
									commonBo.deleteAllRemovedUploadsP(eForm.getHidnRegTxnId(), eForm.getPropertyId());
								} catch (Exception e) {
									request.setAttribute("error", "Unable to delete rows.");
									checkAdditionalUploads = false;
									logger.debug(e.getMessage(), e);
								}
							}

							// rakesh2: Start
							/*
							 * logger.debug("To Add CLR DOCS AGAIN"); String
							 * filePathMapClr=
							 * action.uploadFile(eForm.getHidnRegTxnId
							 * (),eForm.getRegcompletDto().getMapPdfContents(),
							 * eForm
							 * .getPropertyId(),"MapCLR",pr.getValue("clr_map_pdf"
							 * )); if(filePathMapClr !=null){
							 * eForm.getRegcompletDto
							 * ().setMapPathClr(filePathMapClr);} String
							 * filePathKhasraClr=
							 * action.uploadFile(eForm.getHidnRegTxnId
							 * (),eForm.getRegcompletDto
							 * ().getKhasraPdfContents(),
							 * eForm.getPropertyId(),"KhasraCLR"
							 * ,pr.getValue("clr_khasra_pdf"));
							 * if(filePathKhasraClr !=null){
							 * eForm.getRegcompletDto
							 * ().setKhasraPathClr(filePathKhasraClr);}
							 */
							// rakesh2: End

							// below write code for saving modified party
							// details
							if (checkAdditionalUploads) {
								ArrayList<CommonDTO> listDto = eForm.getListDtoP();
								if (listDto != null && listDto.size() > 0) {

									// checkAdditionalUploads =
									// commonBo.deleteAllRemovedUploadsP(eForm.getHidnRegTxnId(),eForm.getPropertyId());
									if (checkAdditionalUploads) {
										for (int i = 0; i < listDto.size(); i++) {
											CommonDTO dto = listDto.get(i);
											dto.setDocumentName(commonBo.getNewFileName(dto.getDocumentName(), Integer.toString(i)));
											String filepath = action.uploadFile(eForm.getHidnRegTxnId(), dto.getDocContents(), eForm.getPropertyId(), "02", dto.getDocumentName());
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

										checkAdditionalUploads = commonBo.insertAdditionalUploads(eForm);

									}

								}
							}

						}
						String filePathAuditReport = ""; // added by ankit for
															// plant and
															// machinery
						String filePathAngle1;
						String filePathAngle2;
						String filePathAngle3;
						String filePathMap;
						String filePathRin;
						String filePathKhasra;
						boolean propertyDetailsInserted = false;

						String propTypeId = eForm.getPropertyTypeId();
						String municipalId = eForm.getRegcompletDto().getGovmunciplId();
						String areaTypeId = eForm.getRegcompletDto().getAreaTypeId();

						/*
						 * String getPropTypeCheck =
						 * commonBo.getPropertyTypeID(eForm.getPropertyId());
						 * 
						 * String valuationID =
						 * commonBo.getValTxnID(eForm.getPropertyId()); String
						 * clrF = "N";
						 */
						// clrF = commonBo.getClrByClrTable(valuationID);
						if (getPropTypeCheck.equalsIgnoreCase("3")) {
							if (clrF.equalsIgnoreCase("Y"))
								eForm.setClrFlag(clrF);
						}

						if (eForm.getInstID() != 2012 && eForm.getInstID() != 2016 && (propTypeId.equals("1") || propTypeId.equals("2") || (propTypeId.equals("3") && (areaTypeId.equals("2") || municipalId.equals("5"))))) {
							// added by ankit for plant and machinery
							if (eForm.getRegcompletDto().getAuditReportDocumentName() != null && !eForm.getRegcompletDto().getAuditReportDocumentName().equalsIgnoreCase("")) {

								filePathAuditReport = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getAuditReportDocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getAuditReportPartyOrProp(), eForm.getRegcompletDto().getAuditReportUploadType());
							}
							eForm.getRegcompletDto().setAuditReportFilePath(filePathAuditReport);
							// added by ankit for plant and machinery--end -->>

							if (eForm.getRegcompletDto().getPropImage1DocumentName() != null && !eForm.getRegcompletDto().getPropImage1DocumentName().equalsIgnoreCase("")) {
								filePathAngle1 = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getPropImage1DocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getPropImage1PartyOrProp(), eForm.getRegcompletDto().getPropImage1UploadType());
							} else {
								filePathAngle1 = "";
							}
							if (filePathAngle1 != null) {
								eForm.getRegcompletDto().setPropImage1FilePath(filePathAngle1);
								/*
								 * filePathAngle2=
								 * action.uploadFile(eForm.getHidnRegTxnId
								 * (),eForm
								 * .getRegcompletDto().getPropImage2DocContents
								 * (),
								 * eForm.getPropertyId(),eForm.getRegcompletDto
								 * ().getPropImage2PartyOrProp(),eForm.
								 * getRegcompletDto
								 * ().getPropImage2UploadType());
								 * if(filePathAngle2!=null){
								 * eForm.getRegcompletDto
								 * ().setPropImage2FilePath(filePathAngle2);
								 * filePathAngle3=
								 * action.uploadFile(eForm.getHidnRegTxnId
								 * (),eForm
								 * .getRegcompletDto().getPropImage3DocContents
								 * (),
								 * eForm.getPropertyId(),eForm.getRegcompletDto
								 * ().getPropImage3PartyOrProp(),eForm.
								 * getRegcompletDto
								 * ().getPropImage3UploadType());
								 * if(filePathAngle3!=null){
								 * eForm.getRegcompletDto
								 * ().setPropImage3FilePath(filePathAngle3);
								 */
								if (eForm.getRegcompletDto().getPropMapDocumentName() != null && !eForm.getRegcompletDto().getPropMapDocumentName().equalsIgnoreCase("")) {
									filePathMap = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getPropMapDocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getPropMapPartyOrProp(), eForm.getRegcompletDto().getPropMapUploadType());
								} else {
									filePathMap = "";
								}
								if (filePathMap != null && eForm.getPropertyTypeId().equalsIgnoreCase(RegInitConstant.PROPERTY_TYPE)) {
									eForm.getRegcompletDto().setPropMapFilePath(filePathMap);
									filePathRin = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getPropRinDocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getPropRinPartyOrProp(), eForm.getRegcompletDto().getPropRinUploadType());
									if (filePathRin != null) {
										eForm.getRegcompletDto().setPropRinFilePath(filePathRin);
										filePathKhasra = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getPropKhasraDocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getPropKhasraPartyOrProp(), eForm.getRegcompletDto().getPropKhasraUploadType());
										if (filePathKhasra != null) {
											eForm.getRegcompletDto().setPropKhasraFilePath(filePathKhasra);

											if (clrF != null && !clrF.isEmpty()) {
												if (getPropTypeCheck.equalsIgnoreCase("3") && clrF.equalsIgnoreCase("y")) {

													propertyDetailsInserted = commonBo.insertPropertyDetailsCLR(regInitForm, eForm);

												} else {

													propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm, eForm);
												}
											} else {

												propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm, eForm);

											}
										}

									}

								} else if (filePathMap != null) {

									eForm.getRegcompletDto().setPropMapFilePath(filePathMap);

									if (clrF != null && !clrF.isEmpty()) {
										if (getPropTypeCheck.equalsIgnoreCase("3") && clrF.equalsIgnoreCase("y")) {

											propertyDetailsInserted = commonBo.insertPropertyDetailsCLR(regInitForm, eForm);

										} else {

											propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm, eForm);
										}
									} else {
										propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm, eForm);
									}
									/*
									 * if(regInitForm.getFromAdjudication()==1){
									 * PropertyValuationForm pform = null;
									 * regInitForm.setFromAdjudication(pform.
									 * getFromAdjudication());
									 * session.setAttribute
									 * ("fnName","Adjudication");
									 * 
									 * }
									 */
								}

								/*
								 * }
								 * 
								 * 
								 * }
								 */

							}

						} else {

							// added by ankit for plant and machinery
							if (eForm.getRegcompletDto().getAuditReportDocumentName() != null && !eForm.getRegcompletDto().getAuditReportDocumentName().equalsIgnoreCase("")) {

								filePathAuditReport = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getAuditReportDocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getAuditReportPartyOrProp(), eForm.getRegcompletDto().getAuditReportUploadType());
							}
							eForm.getRegcompletDto().setAuditReportFilePath(filePathAuditReport);
							// added by ankit for plant and machinery--end -->>

							if (eForm.getRegcompletDto().getPropImage1DocumentName() != null && !eForm.getRegcompletDto().getPropImage1DocumentName().equalsIgnoreCase("")) {
								filePathAngle1 = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getPropImage1DocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getPropImage1PartyOrProp(), eForm.getRegcompletDto().getPropImage1UploadType());
								eForm.getRegcompletDto().setPropImage1FilePath(filePathAngle1);
							} else {
								eForm.getRegcompletDto().setPropImage1FilePath("");
							}

							if (eForm.getRegcompletDto().getPropMapDocumentName() != null && !eForm.getRegcompletDto().getPropMapDocumentName().equalsIgnoreCase("")) {
								filePathMap = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getPropMapDocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getPropMapPartyOrProp(), eForm.getRegcompletDto().getPropMapUploadType());
								eForm.getRegcompletDto().setPropMapFilePath(filePathMap);
							} else {
								eForm.getRegcompletDto().setPropMapFilePath("");
							}

							if (eForm.getPropertyTypeId().equalsIgnoreCase(RegInitConstant.PROPERTY_TYPE)) {

								filePathRin = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getPropRinDocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getPropRinPartyOrProp(), eForm.getRegcompletDto().getPropRinUploadType());
								if (filePathRin != null) {
									eForm.getRegcompletDto().setPropRinFilePath(filePathRin);
									filePathKhasra = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getPropKhasraDocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getPropKhasraPartyOrProp(), eForm.getRegcompletDto().getPropKhasraUploadType());
									if (filePathKhasra != null) {
										eForm.getRegcompletDto().setPropKhasraFilePath(filePathKhasra);

										if (clrF != null && !clrF.isEmpty()) {
											if (getPropTypeCheck.equalsIgnoreCase("3") && clrF.equalsIgnoreCase("y")) {

												propertyDetailsInserted = commonBo.insertPropertyDetailsCLR(regInitForm, eForm);

											} else {

												propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm, eForm);
											}
										} else {
											propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm, eForm);
										}

									}
									// rakesh1: Start
									logger.debug("To Add CLR DOCS AGAIN");
									/*
									 * String filePathMapClr=
									 * action.uploadFile(eForm
									 * .getHidnRegTxnId(),
									 * eForm.getRegcompletDto(
									 * ).getMapPdfContents(),
									 * eForm.getPropertyId
									 * (),"MapCLR",pr.getValue("clr_map_pdf"));
									 * eForm.getRegcompletDto().setMapPathClr(
									 * filePathMapClr); String
									 * filePathKhasraClr=
									 * action.uploadFile(eForm
									 * .getHidnRegTxnId(),
									 * eForm.getRegcompletDto(
									 * ).getKhasraPdfContents(),
									 * eForm.getPropertyId
									 * (),"KhasraCLR",pr.getValue
									 * ("clr_khasra_pdf"));
									 * eForm.getRegcompletDto
									 * ().setKhasraPathClr(filePathKhasraClr);
									 */
									// rakesh1: End
								}

							} else {

								/*
								 * eForm.getRegcompletDto().setPropRinFilePath(""
								 * );
								 * eForm.getRegcompletDto().setPropKhasraFilePath
								 * ("");
								 */
								if (clrF != null && !clrF.isEmpty()) {
									if (getPropTypeCheck.equalsIgnoreCase("3") && clrF.equalsIgnoreCase("y")) {

										propertyDetailsInserted = commonBo.insertPropertyDetailsCLR(regInitForm, eForm);

									} else {

										propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm, eForm);
									}
								} else {
									propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm, eForm);
								}
							}

						}

						logger.debug("property details inserted :- " + propertyDetailsInserted);

						if ((regInitForm.getDeedID() == RegInitConstant.DEED_EXCHANGE || regInitForm.getDeedID() == RegInitConstant.DEED_PARTITION_PV) && !RegInitConstant.SAVE_PROP_ACTION.equals(actionName)) {
							regInitForm.setPropertyId("");
							eForm.setPropertyId("");
						}

						if (propertyDetailsInserted) {

							if (RegInitConstant.NEXT_TO_MAPPING_ACTION.equals(actionName)) {
								forwardJsp = "reginitMapping";
								request.setAttribute("mapCheck", eForm.getMapAllPropsParties());
							} else {
								eForm.setActionName(RegInitConstant.NO_ACTION);
								regInitForm.setActionName(RegInitConstant.NO_ACTION);
								request.setAttribute("deedId", regInitForm.getDeedID());
								request.setAttribute("instId", regInitForm.getInstID());
								request.setAttribute("key", eForm.getPropertyId());
								forwardJsp = "reginitConfirmModify";
							}

							// forwardJsp ="reginitConfirm";
						} else {
							eForm.setHidnRegTxnId(null);
							if (RegInitConstant.NEXT_TO_MAPPING_ACTION.equals(actionName)) {
								request.setAttribute("deedId", regInitForm.getDeedID());
								if (eForm.getClrFlag() != null) {
									if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

										eForm.setPropertySelected(2);
										// eForm.setKhasraArrayList(eForm.getKhasraArrayList());
										eForm.getRegcompletDto().setSelectedPropValId("");
										forwardJsp = "propdetailsClr";
									}

									else {
										forwardJsp = "propdetails";

									}

								} else {

									forwardJsp = "propdetails";
								}
							} else {
								eForm.setActionName(RegInitConstant.NO_ACTION);
								regInitForm.setActionName(RegInitConstant.NO_ACTION);

								if (regInitForm.getClrFlg() != null) {
									if (regInitForm.getClrFlg().equalsIgnoreCase("Y")) {

										forwardJsp = "editPropDetailsCLR";
									} else {
										forwardJsp = "editPropDetails";

									}
									// forwardJsp= "editPropDetails";

								} else {
									forwardJsp = "editPropDetails";
									// test
									System.out.println("testPropId" + eForm.getPropertyId());
								}

							}
						}
						eForm.setActionName("");

					}
					if (RegInitConstant.ADD_MORE_PROP_ACTION.equals(actionName)) {
					}

					if (RegInitConstant.RESET_PROPERTY_ACTION.equals(actionName)) {

						PropertyValuationDTO dto1 = new PropertyValuationDTO();
						RegCompletDTO comDto = new RegCompletDTO();
						eForm.setRegcompletDto(comDto);
						eForm.setPropertyDTO(dto1);
						eForm.getRegcompletDto().getKhasraDetlsDisplay().add(new CommonDTO());
						request.setAttribute("deedId", regInitForm.getDeedID());
						if (eForm.getClrFlag() != null) {
							if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

								forwardJsp = "propdetailsClr";
								eForm.setPropertySelected(2);
							}

							else {
								forwardJsp = "propdetails";

							}

						} else {

							forwardJsp = "propdetails";
						}
						eForm.setActionName("");
					}
					if (RegInitConstant.CONFIRMATION_OK_ACTION.equals(actionName)) {

						eForm.setActionName(RegInitConstant.NO_ACTION);
						regInitForm.setActionName(RegInitConstant.NO_ACTION);
						request.setAttribute("deedId", regInitForm.getDeedID());
						request.setAttribute("instId", regInitForm.getInstID());
						request.setAttribute("key", eForm.getPropertyId());
						forwardJsp = "reginitConfirmModify";

					}
					if (RegInitConstant.ADD_NEXT_PROP_DETLS_ACTION.equals(actionName)) {

						regInitForm.setTermsCondProp("");

						CommonDTO dto1 = new CommonDTO();
						int index = 0;

						/*
						 * if(eForm.getKhasraNoArray() != null &&
						 * eForm.getKhasraAreaArray()!=null &&
						 * eForm.getLagaanArray()!=null &&
						 * eForm.getRinPustikaArray()!=null &&
						 * !eForm.getKhasraNoArray().equalsIgnoreCase("null") &&
						 * !eForm.getKhasraAreaArray().equalsIgnoreCase("null")
						 * && !eForm.getLagaanArray().equalsIgnoreCase("null")
						 * &&
						 * !eForm.getRinPustikaArray().equalsIgnoreCase("null"))
						 * {
						 * 
						 * 
						 * 
						 * logger.debug("khasra nos. :-"+eForm.getKhasraNoArray()
						 * );
						 * logger.debug("khasra areas. :-"+eForm.getKhasraAreaArray
						 * ());
						 * logger.debug("lagaans :-"+eForm.getLagaanArray());
						 * logger
						 * .debug("rin pustikas :-"+eForm.getRinPustikaArray());
						 * 
						 * String str=""; str=eForm.getKhasraNoArray();
						 * str=str.replace
						 * (",",RegInitConstant.SPLIT_CONSTANT_KHASRA);
						 * eForm.setKhasraNoArray(str); //
						 * str=eForm.getKhasraAreaArray();
						 * str=str.replace(",",RegInitConstant
						 * .SPLIT_CONSTANT_KHASRA);
						 * eForm.setKhasraAreaArray(str); //
						 * str=eForm.getLagaanArray();
						 * str=str.replace(",",RegInitConstant
						 * .SPLIT_CONSTANT_KHASRA); eForm.setLagaanArray(str);
						 * // str=eForm.getRinPustikaArray();
						 * str=str.replace(","
						 * ,RegInitConstant.SPLIT_CONSTANT_KHASRA);
						 * eForm.setRinPustikaArray(str);
						 * 
						 * }
						 */
						ArrayList<CommonDTO> khasraDets = eForm.getRegcompletDto().getKhasraDetlsDisplay();
						if (khasraDets != null && khasraDets.size() > 0) {
							commonBo.refreshKhasraData(khasraDets, request);
						}
						for (int i = 0; i < eForm.getExchangePropertyList().size(); i++) {

							dto1 = (CommonDTO) eForm.getExchangePropertyList().get(i);
							if (dto1.getValuationId().equalsIgnoreCase(eForm.getRegcompletDto().getSelectedPropValId())) {
								index = i;
								regInitForm.setPropertyId(dto1.getPropertyId());
								eForm.setPropertyId(dto1.getPropertyId());
								break;
							}

						}

						// added by ankit for GIS work
						if ("Y".equalsIgnoreCase(eForm.getRegcompletDto().getMunGISFlag()) || "Y".equalsIgnoreCase(eForm.getRegcompletDto().getGisFlag())) {
							boolean isGISIDExist = commonBo.checkGISDuplicate(eForm.getRegcompletDto().getGisReferenceKey(), eForm.getPropertyId());
							if (isGISIDExist) {
								ArrayList errorList = new ArrayList();
								request.setAttribute("deedId", regInitForm.getDeedID());
								errorList.add(RegInitConstant.gis_duplicate_error);
								request.setAttribute("errorsList", errorList);
								// request.setAttribute("error",
								// " GIS ID already exist \n\n जीआईएस आईडी पहले से मौजूद है");
								if (RegInitConstant.SAVE_PROP_ACTION.equals(actionName)) {
									forwardJsp = "editPropDetails";
								} else {
									forwardJsp = "propdetails";
								}

								return mapping.findForward(forwardJsp);
							}
						}

						CommonAction action = new CommonAction();

						String returnJsp = commonBo.validateMPCST(eForm.getRegcompletDto(), eForm, request, eForm.getPropertyId());
						if (returnJsp.equalsIgnoreCase("error")) {
							request.setAttribute("deedId", regInitForm.getDeedID());
							if (eForm.getClrFlag() != null) {
								if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

									forwardJsp = "propdetailsClr";
								}

								else {
									forwardJsp = "propdetails";

								}

							} else {

								forwardJsp = "propdetails";
							}
							return mapping.findForward(forwardJsp);
						}

						boolean check = true;
						ArrayList<CommonDTO> dto = eForm.getListDtoP();
						if (dto.size() > 0) {
							for (int i = 0; i < dto.size(); i++) {
								CommonDTO dtos = dto.get(i);
								dtos.setDocumentName(commonBo.getNewFileName(dtos.getDocumentName(), Integer.toString(i)));
								String checkUpload = action.uploadFile(eForm.getHidnRegTxnId(), dtos.getDocContents(), eForm.getPropertyId(), "02", dtos.getDocumentName());
								if (checkUpload == null) {
									check = false;
									break;
								} else {
									dtos.setDocumentPath(checkUpload);
									check = true;
								}

							}

							if (check == true) {
								check = commonBo.insertAdditionalUploads(eForm);
							}

						} else {
							check = true;
						}

						String filePathAuditReport = "";// Added by ankit for
														// plant and machinery
						String filePathAngle1;
						String filePathAngle2;
						String filePathAngle3;
						String filePathMap;
						String filePathRin;
						String filePathKhasra;
						boolean propertyDetailsInserted = false;

						String propTypeId = eForm.getPropertyTypeId();
						String municipalId = eForm.getRegcompletDto().getGovmunciplId();
						String areaTypeId = eForm.getRegcompletDto().getAreaTypeId();

						// CLR_insertion:Start

						String valuationID = commonBo.getValTxnID(eForm.getPropertyId());
						String clrF = commonBo.getClrByClrTable(valuationID);

						/*if (clrF != null && !clrF.isEmpty()) {
							if (propTypeId.equalsIgnoreCase("3") && clrF.equalsIgnoreCase("y")) {

								boolean flag = commonBo.updatePropertyKhasraDetls1(eForm);
								if (flag) {
								//	boolean flag2 = commonBo.insertClrPropOwnerDetails(eForm);
								}

							}
						}*/

						// CLR_insertion:End

						// prop=='1' || prop=='2' || (prop=='3' && (areaId=='2'
						// || govtId=='5'))

						if (eForm.getInstID() != 2012 && eForm.getInstID() != 2016 && (propTypeId.equals("1") || propTypeId.equals("2") || (propTypeId.equals("3") && (areaTypeId.equals("2") || municipalId.equals("5"))))) {
							// added by ankit for plant and machinery
							if (eForm.getRegcompletDto().getAuditReportDocumentName() != null && !eForm.getRegcompletDto().getAuditReportDocumentName().equalsIgnoreCase("")) {

								filePathAuditReport = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getAuditReportDocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getAuditReportPartyOrProp(), eForm.getRegcompletDto().getAuditReportUploadType());
							}
							eForm.getRegcompletDto().setAuditReportFilePath(filePathAuditReport);
							// added by ankit for plant and machinery--end -->>

							if (eForm.getRegcompletDto().getPropImage1DocumentName() != null && !eForm.getRegcompletDto().getPropImage1DocumentName().equalsIgnoreCase("")) {
								filePathAngle1 = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getPropImage1DocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getPropImage1PartyOrProp(), eForm.getRegcompletDto().getPropImage1UploadType());
							} else {
								filePathAngle1 = "";
							}

							if (filePathAngle1 != null) {
								eForm.getRegcompletDto().setPropImage1FilePath(filePathAngle1);
								/*
								 * filePathAngle2=
								 * action.uploadFile(eForm.getHidnRegTxnId
								 * (),eForm
								 * .getRegcompletDto().getPropImage2DocContents
								 * (),
								 * eForm.getPropertyId(),eForm.getRegcompletDto
								 * ().getPropImage2PartyOrProp(),eForm.
								 * getRegcompletDto
								 * ().getPropImage2UploadType());
								 * if(filePathAngle2!=null){
								 * eForm.getRegcompletDto
								 * ().setPropImage2FilePath(filePathAngle2);
								 * filePathAngle3=
								 * action.uploadFile(eForm.getHidnRegTxnId
								 * (),eForm
								 * .getRegcompletDto().getPropImage3DocContents
								 * (),
								 * eForm.getPropertyId(),eForm.getRegcompletDto
								 * ().getPropImage3PartyOrProp(),eForm.
								 * getRegcompletDto
								 * ().getPropImage3UploadType());
								 * if(filePathAngle3!=null){
								 * eForm.getRegcompletDto
								 * ().setPropImage3FilePath(filePathAngle3);
								 */
								if (eForm.getRegcompletDto().getPropMapDocumentName() != null && !eForm.getRegcompletDto().getPropMapDocumentName().equalsIgnoreCase("")) {
									filePathMap = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getPropMapDocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getPropMapPartyOrProp(), eForm.getRegcompletDto().getPropMapUploadType());
								} else {
									filePathMap = "";
								}
								if (filePathMap != null && eForm.getPropertyTypeId().equalsIgnoreCase(RegInitConstant.PROPERTY_TYPE)) {
									eForm.getRegcompletDto().setPropMapFilePath(filePathMap);
									filePathRin = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getPropRinDocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getPropRinPartyOrProp(), eForm.getRegcompletDto().getPropRinUploadType());
									if (filePathRin != null) {
										eForm.getRegcompletDto().setPropRinFilePath(filePathRin);
										filePathKhasra = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getPropKhasraDocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getPropKhasraPartyOrProp(), eForm.getRegcompletDto().getPropKhasraUploadType());
										if (filePathKhasra != null) {
											eForm.getRegcompletDto().setPropKhasraFilePath(filePathKhasra);

											if (clrF != null && !clrF.isEmpty()) {
												if (propTypeId.equalsIgnoreCase("3") && clrF.equalsIgnoreCase("y")) {

													propertyDetailsInserted = commonBo.insertPropertyDetailsCLR(regInitForm, eForm);

												} else {

													propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm, eForm);
												}
											}

											else {

												propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm, eForm);

											}

										}

									}

								} else if (filePathMap != null) {

									eForm.getRegcompletDto().setPropMapFilePath(filePathMap);
									if (clrF != null && !clrF.isEmpty()) {
										if (propTypeId.equalsIgnoreCase("3") && clrF.equalsIgnoreCase("y")) {

											propertyDetailsInserted = commonBo.insertPropertyDetailsCLR(regInitForm, eForm);

										} else {

											propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm, eForm);
										}
									}

									else {

										propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm, eForm);

									}
								}

								/*
								 * }
								 * 
								 * 
								 * }
								 */

							}
						} else {

							// added by ankit for plant and machinery
							if (eForm.getRegcompletDto().getAuditReportDocumentName() != null && !eForm.getRegcompletDto().getAuditReportDocumentName().equalsIgnoreCase("")) {

								filePathAuditReport = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getAuditReportDocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getAuditReportPartyOrProp(), eForm.getRegcompletDto().getAuditReportUploadType());
							}
							eForm.getRegcompletDto().setAuditReportFilePath(filePathAuditReport);
							// added by ankit for plant and machinery--end -->>

							if (eForm.getRegcompletDto().getPropImage1DocumentName() != null && !eForm.getRegcompletDto().getPropImage1DocumentName().equalsIgnoreCase("")) {
								filePathAngle1 = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getPropImage1DocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getPropImage1PartyOrProp(), eForm.getRegcompletDto().getPropImage1UploadType());
								eForm.getRegcompletDto().setPropImage1FilePath(filePathAngle1);
							} else {
								eForm.getRegcompletDto().setPropImage1FilePath("");
							}

							/*
							 * if(eForm.getRegcompletDto().getPropImage2DocContents
							 * ()!=null){ filePathAngle2=
							 * action.uploadFile(eForm
							 * .getHidnRegTxnId(),eForm.getRegcompletDto
							 * ().getPropImage2DocContents(),
							 * eForm.getPropertyId
							 * (),eForm.getRegcompletDto().getPropImage2PartyOrProp
							 * (
							 * ),eForm.getRegcompletDto().getPropImage2UploadType
							 * ());
							 * 
							 * eForm.getRegcompletDto().setPropImage2FilePath(
							 * filePathAngle2); }else{
							 * eForm.getRegcompletDto().setPropImage2FilePath
							 * (""); }
							 * 
							 * 
							 * 
							 * if(eForm.getRegcompletDto().getPropImage3DocContents
							 * ()!=null){ filePathAngle3=
							 * action.uploadFile(eForm
							 * .getHidnRegTxnId(),eForm.getRegcompletDto
							 * ().getPropImage3DocContents(),
							 * eForm.getPropertyId
							 * (),eForm.getRegcompletDto().getPropImage3PartyOrProp
							 * (
							 * ),eForm.getRegcompletDto().getPropImage3UploadType
							 * ());
							 * 
							 * eForm.getRegcompletDto().setPropImage3FilePath(
							 * filePathAngle3); }else{
							 * eForm.getRegcompletDto().setPropImage3FilePath
							 * (""); }
							 */

							if (eForm.getRegcompletDto().getPropMapDocumentName() != null && !eForm.getRegcompletDto().getPropMapDocumentName().equalsIgnoreCase("")) {
								filePathMap = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getPropMapDocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getPropMapPartyOrProp(), eForm.getRegcompletDto().getPropMapUploadType());
								eForm.getRegcompletDto().setPropMapFilePath(filePathMap);
							} else {
								eForm.getRegcompletDto().setPropMapFilePath("");
							}

							if (eForm.getPropertyTypeId().equalsIgnoreCase(RegInitConstant.PROPERTY_TYPE)) {

								filePathRin = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getPropRinDocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getPropRinPartyOrProp(), eForm.getRegcompletDto().getPropRinUploadType());
								if (filePathRin != null) {
									eForm.getRegcompletDto().setPropRinFilePath(filePathRin);
									filePathKhasra = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getPropKhasraDocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getPropKhasraPartyOrProp(), eForm.getRegcompletDto().getPropKhasraUploadType());
									if (filePathKhasra != null) {
										eForm.getRegcompletDto().setPropKhasraFilePath(filePathKhasra);
										if (clrF != null && !clrF.isEmpty()) {
											if (propTypeId.equalsIgnoreCase("3") && clrF.equalsIgnoreCase("y")) {

												propertyDetailsInserted = commonBo.insertPropertyDetailsCLR(regInitForm, eForm);

											} else {

												propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm, eForm);
											}
										}

										else {

											propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm, eForm);

										}

									}

								}

							} else {

								/*
								 * eForm.getRegcompletDto().setPropRinFilePath(""
								 * );
								 * eForm.getRegcompletDto().setPropKhasraFilePath
								 * ("");
								 */
								if (clrF != null && !clrF.isEmpty()) {
									if (propTypeId.equalsIgnoreCase("3") && clrF.equalsIgnoreCase("y")) {

										propertyDetailsInserted = commonBo.insertPropertyDetailsCLR(regInitForm, eForm);

									} else {

										propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm, eForm);
									}
								}

								else {

									propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm, eForm);

								}
							}

						}

						if (propertyDetailsInserted) {

							regInitForm.setPropertyId("");
							eForm.setPropertyId("");

							eForm.getExchangeAgriPropertyListDisp().add(eForm.getRegcompletDto().getSelectedPropId());

							logger.debug("index----->" + index);

							eForm.getExchangePropertyList().remove(index);

							if (eForm.getExchangePropertyList().size() == 1) {
								eForm.setAllPropertiesAdded(1);
								eForm.setIsPropertyRemaining(0);
							} else {
								eForm.setIsPropertyRemaining(1);
							}

							RegCompletDTO comDto = new RegCompletDTO();
							eForm.setRegcompletDto(comDto);
							// Mohit
							eForm.setListDtoP(new ArrayList<CommonDTO>());

							eForm.setActionName("");
							eForm.setPropertySelected(0);
							if (eForm.getClrFlag() != null) {
								if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

									forwardJsp = "propdetailsClr";
								}

								else {
									forwardJsp = "propdetails";

								}

							} else {

								forwardJsp = "propdetails";
							}
							eForm.setActionName("");
							eForm.getRegcompletDto().getKhasraDetlsDisplay().add(new CommonDTO());
						} else {
							eForm.setHidnRegTxnId(null);
							if (eForm.getClrFlag() != null) {
								if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

									forwardJsp = "propdetailsClr";
								}

								else {
									forwardJsp = "propdetails";

								}

							} else {

								forwardJsp = "propdetails";
							}
						}

						request.setAttribute("deedId", regInitForm.getDeedID());

					}
					if (RegInitConstant.CANCEL_ACTION.equals(actionName)) {

						PropertyValuationDTO dto1 = new PropertyValuationDTO();
						RegCompletDTO comDto = new RegCompletDTO();
						eForm.setRegcompletDto(comDto);
						eForm.setPropertyDTO(dto1);
						// CommonAction.cancelAction(session,regInitForm);
						forwardJsp = "welcome";
					}

					eForm.setActionName("");

				}
				if (request.getParameter("dms") != null) {
					if (request.getParameter("dms").equalsIgnoreCase("retrievalLive")) {
						if (request.getParameter("retrievalType") != null) {

							// RegCompletDTO
							// dtoObj=(RegCompletDTO)regForm.getMapPropertyTransPartyDisp().get(key);

							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("angleTemplate")) {
								String templatePath = pr.getValue("angle.template.location");;
								byte[] templateContents = DMSUtility.getDocumentBytes(templatePath);
								DMSUtility.downloadDocument(respons, templatePath, templateContents);

							}
							// added by rakesh to download clr docs: start
							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("MapCLR")) {
								// String
								// templatePath1=pr.getValue("clr.map.location");

								String getMapPath = pr.getValue("upload.location");
								getMapPath = getMapPath + RegInitConstant.FILE_UPLOAD_PATH + eForm.getHidnRegTxnId() + RegInitConstant.FILE_UPLOAD_PATH_PROP + eForm.getPropertyId() + "/";
								String kId = request.getParameter("key");
								getMapPath = getMapPath + kId + "/";
								getMapPath = getMapPath + pr.getValue("clr_map_pdf");
								System.out.println("MPATH: " + getMapPath);
								byte[] templateContents = DMSUtility.getDocumentBytes(getMapPath);
								// DMSUtility.downloadDocument(respons,
								// eForm.getRegcompletDto().getMapPdf(),
								// eForm.getRegcompletDto().getMapPdfContents());
								DMSUtility.downloadDocument(respons, getMapPath, templateContents);

							}
							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("KhasraCLR")) {

								String getKhasraPath = pr.getValue("upload.location");
								getKhasraPath = getKhasraPath + RegInitConstant.FILE_UPLOAD_PATH + eForm.getHidnRegTxnId() + RegInitConstant.FILE_UPLOAD_PATH_PROP + eForm.getPropertyId() + "/";
								String kId = request.getParameter("key");
								getKhasraPath = getKhasraPath + kId + "/";
								getKhasraPath = getKhasraPath + pr.getValue("clr_khasra_pdf");
								System.out.println("KPATH: " + getKhasraPath);
								byte[] templateContents = DMSUtility.getDocumentBytes(getKhasraPath);
								// DMSUtility.downloadDocument(respons,
								// eForm.getRegcompletDto().getKhasraPdf(),
								// eForm.getRegcompletDto().getKhasraPdfContents());
								DMSUtility.downloadDocument(respons, getKhasraPath, templateContents);

							}
							// added by rakesh to download clr docs: end

							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("image1")) {
								DMSUtility.downloadDocument(respons, eForm.getRegcompletDto().getPropImage1FilePath(), eForm.getRegcompletDto().getPropImage1DocContents());

							}

							/*
							 * if(request.getParameter("retrievalId")!=null &&
							 * request.getParameter("retrievalId").toString().
							 * equalsIgnoreCase("image2")){
							 * DMSUtility.downloadDocument(respons,
							 * eForm.getRegcompletDto().getPropImage2FilePath()
							 * ,
							 * eForm.getRegcompletDto().getPropImage2DocContents
							 * ());
							 * 
							 * }
							 * 
							 * if(request.getParameter("retrievalId")!=null &&
							 * request.getParameter("retrievalId").toString().
							 * equalsIgnoreCase("image3")){
							 * DMSUtility.downloadDocument(respons,
							 * eForm.getRegcompletDto().getPropImage3FilePath()
							 * ,
							 * eForm.getRegcompletDto().getPropImage3DocContents
							 * ());
							 * 
							 * }
							 */

							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("map")) {
								DMSUtility.downloadDocument(respons, eForm.getRegcompletDto().getPropMapFilePath(), eForm.getRegcompletDto().getPropMapDocContents());

							}

							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("rin")) {
								DMSUtility.downloadDocument(respons, eForm.getRegcompletDto().getPropRinFilePath(), eForm.getRegcompletDto().getPropRinDocContents());

							}

							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("khasra")) {
								DMSUtility.downloadDocument(respons, eForm.getRegcompletDto().getPropKhasraFilePath(), eForm.getRegcompletDto().getPropKhasraDocContents());

							}
							// Added by ankit for plant and machinery
							if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("auditReport")) {
								DMSUtility.downloadDocument(respons, eForm.getRegcompletDto().getAuditReportFilePath(), eForm.getRegcompletDto().getAuditReportDocContents());

							}

							if (request.getParameter("retrievalType").toString().equalsIgnoreCase("1")) {
								if (eForm.getClrFlag() != null) {
									if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

										forwardJsp = "propdetailsClr";
									}

									else {
										forwardJsp = "propdetails";

									}

								} else {

									forwardJsp = "propdetails";
								}
							} else if (request.getParameter("retrievalType").toString().equalsIgnoreCase("2")) {
								forwardJsp = "editPropDetails";
							}
						}
					}
				}

				request.setAttribute("reginitproperty", eForm);

				logger.debug("file name:" + eForm.getRegcompletDto().getPropMapDocumentName());
			}

		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			forwardJsp = "Failure";
		}

		return mapping.findForward(forwardJsp);
	}

	/*
	 * private void resetValue(PropertyValuationDTO dto,RegCompletDTO rdto) {
	 * rdto.setAddress(""); rdto.setAdjustAppNo(""); rdto.setAreaList(new
	 * ArrayList()); rdto.setAreaTypeId("");
	 * 
	 * rdto.setCeilinghidType(""); rdto.setCeilingTypeId("");
	 * rdto.setConsiderAmt(0); rdto.setConstructedArea(""); rdto.setDistId("");
	 * rdto.setEast(""); rdto.setNorth(""); rdto.setWardStatus("");
	 * rdto.setWardId(""); rdto.setMohallaId(""); rdto.setWardList(new
	 * ArrayList()); rdto.setMohallList(new ArrayList()); rdto.setRicircle("");
	 * rdto.setLayoutdet(""); rdto.setRinpustikaNum("");
	 * rdto.setPropertyTypeId(""); rdto.setGovmunciplId("");
	 * rdto.setHdnExtSubClause(""); rdto.setKhasaraNum("");
	 * rdto.setMapBuilding(new HashMap()); rdto.setWest("");
	 * rdto.setWardpatwarName(""); rdto.setWardList(new ArrayList());
	 * rdto.setWardpatwariList(new ArrayList()); rdto.setVikasId("");
	 * rdto.setUsePlotId(""); rdto.setUsageBuilding("");
	 * rdto.setMarketValue(""); rdto.setNazoolstNum(""); rdto.setTehsilId("");
	 * rdto.setMohallaId(""); rdto.setPoaNo(""); rdto.setSouth("");
	 * rdto.setTehsilList(new ArrayList()); rdto.setMohallList(new ArrayList());
	 * 
	 * dto.setConsiderAmt(0); dto.setConstructedArea(0); dto.setTotalSqMeter(0);
	 * dto.setCeilingTypeId(""); dto.setHdnExtSubClause("");
	 * dto.setTehsilID(""); dto.setSubClause("");
	 * 
	 * }
	 */
	/*
	 * private String[] getSubClauses(String _subClause) { String[] retString =
	 * new String[100]; logger.debug("_subClause--<>" + _subClause);
	 * StringTokenizer rsTokenOne = new StringTokenizer(_subClause, ","); int i
	 * = 0; while (rsTokenOne.hasMoreTokens()) { logger.debug("in while");
	 * String subone = rsTokenOne.nextToken(); String[] rsTokenTwo =
	 * subone.split("~"); logger.debug("--rsTokenTwo--<>" + rsTokenTwo);
	 * logger.debug("in else sub"); retString[i] = rsTokenTwo[1];
	 * 
	 * logger.debug("retString---<><>" + retString); i++; }
	 * logger.debug("---sub clause out put" + retString); return retString; }
	 */

}
