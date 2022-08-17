package com.wipro.igrs.newreginit.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.baseaction.constant.Constants;
import com.wipro.igrs.newPropvaluation.bo.PropertyValuationBO;
import com.wipro.igrs.newdutycalculation.bo.CalculateDuty;
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
import com.wipro.igrs.newreginit.util.PropertiesFileReader;
import com.wipro.igrs.propertyvaluation.constant.CommonConstant;
import com.wipro.igrs.regcompletion.bd.CommonDropDownBD;
import java.io.ByteArrayOutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import com.wipro.igrs.newreginit.rule.RegInitRule;

/**
 * @author Madan Mohan
 * 
 */
public class RegInitNonPropertyAction extends BaseAction {
	private int count = 0;
	private HashMap map = null;

	private Logger logger = (Logger) Logger.getLogger(RegInitNonPropertyAction.class);

	private int floorCount = 0;
	boolean bol = true;
	/**
	 * @see forwardJsp is used for redirecting
	 */
	private String forwardJsp;
	private static Proxy proxy;

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse respons, HttpSession session) throws IOException, ServletException, Exception {

		// HttpSession session=request.getSession();
		try {
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			// session = request.getSession();
			PropertyValuationBO newPropBO = new PropertyValuationBO();
			RegCommonBO commonBo = new RegCommonBO();

			String languageLocale = "hi";
			if (session.getAttribute("languageLocale") != null) {
				languageLocale = (String) session.getAttribute("languageLocale");
			}

			System.out.println("request ij propert action:" + request);
			if (logger.isDebugEnabled()) {
				logger.debug("execute(ActionMapping, ActionForm, " + "HttpServletRequest, HttpServletResponse) -" + " start");
			}
			// PropertiesFileReader pr = PropertiesFileReader
			// .getInstance("resources.propertyvaluation");
			// PropertyValuationBO valueBo = new PropertyValuationBO();

			if (form != null) {
				RegCompletionForm eForm = (RegCompletionForm) form;

				RegCommonForm regInitForm;
				if (request.getAttribute("regInitForm") != null) {

					regInitForm = (RegCommonForm) request.getAttribute("regInitForm");
					// session.setAttribute("regInitForm", regInitForm);
					eForm.setRegInitForm((RegCommonForm) request.getAttribute("regInitForm"));
					eForm.setHidnRegTxnId(regInitForm.getHidnRegTxnId());
					eForm.setRegcompletDto(new RegCompletDTO());
					eForm.setListDtoP(new ArrayList());
					eForm.setMpcstAvailFlag("");
				} else {
					// regInitForm=(RegCommonForm)session.getAttribute("regInitForm");
					regInitForm = eForm.getRegInitForm();

				}
				// RegCommonForm regForm = new RegCommonForm();
				String userID = (String) session.getAttribute("UserId");
				eForm.setUserID(userID);
				if (request.getParameter("pageName") != null) {
					if (request.getParameter("pageName").equalsIgnoreCase("propertyvalutioan")) {

						// set multiprop map here

						/*
						 * if(request.getAttribute("multiProp")!=null){
						 * 
						 * if(request.getAttribute("multiProp").toString().
						 * equalsIgnoreCase("Y")){
						 */

						HashMap dispProp = commonBo.getAllPropertyListNPV(eForm, languageLocale);

						if (dispProp != null && dispProp.size() > 0) {

							eForm.setMapProperty(dispProp);

						} else {
							eForm.setMapProperty(new HashMap());
							eForm.setAddMoreCounter(0);

						}
						/*
						 * }else{ eForm.setMapProperty(new HashMap());
						 * eForm.setAddMoreCounter(0); }
						 * 
						 * }else{ eForm.setMapProperty(new HashMap());
						 * eForm.setAddMoreCounter(0); }
						 */

						// eForm.setDistList(commonBo.getDistrict("1",languageLocale));//1
						// is for state mp
						// eForm.setAreaTypeList(commonBo.getArea(languageLocale));
						// eForm.setMunicipalList(valueBo.getMunicipalBody(languageLocale));
						// eForm.setPropertyType(commonBo.getPropertyType(languageLocale));
						// eForm.setLandTypeList(valueBo.getLandType());
						// eForm.setNumfloors(0);
						// eForm.setMapBuildingDetails1(new HashMap());
						// eForm.setMapProperty(new HashMap());
						// eForm.setAddMoreCounter(0);
						eForm.setActionName("");

					}
				}

				CommonDropDownBD bd = new CommonDropDownBD();

				RegCompletDTO proDetailsdto = null;
				RegCompletDTO dto = new RegCompletDTO();
				ArrayList resultSplitPartList = bd.getLocsOfSplitPart();

				eForm.getRegcompletDto().setSplitPartList(resultSplitPartList);

				RegCompletDTO refdto = eForm.getRegcompletDto();
				// RegCommonBO commonBo = new RegCommonBO();

				eForm.setHidnRegTxnId(regInitForm.getHidnRegTxnId());
				eForm.setInstID(regInitForm.getInstID());
				eForm.setDeedID(regInitForm.getDeedID());

				// added by ankit for plant and machinery -- Start -- >
				// eForm.setInstID(eForm.getRegInitForm().getInstID());
				logger.info("Instrument id in property action is " + eForm.getInstID());

				eForm.setMovPropFlag(commonBo.getMovPropFlag(String.valueOf(eForm.getDeedID()), String.valueOf(eForm.getInstID())));
				logger.info("setMovPropFlag in property action is " + eForm.getMovPropFlag());
				// added by ankit for plant and machinery - end -->

				logger.debug("I am RegInitPropertyAction 1------------");
				if (request.getAttribute("propAction") != null) {
					if (request.getAttribute("propAction").toString().equalsIgnoreCase("propAction")) {
						eForm.setRegcompletDto(new RegCompletDTO());
						eForm.setListDtoP(new ArrayList());
						eForm.setMpcstAvailFlag("");
					}
				}
				String formName = eForm.getFormName();
				String actionName = eForm.getActionName();

				logger.debug("I am RegInitPropertyAction 2------------");

				logger.debug("formName:-" + formName);
				logger.debug("actionName:-" + actionName);
				String pageName = request.getParameter("pageName");

				if (request.getAttribute("fromAdju") != null) {
					// by shreeraj
					int fromAdju = Integer.parseInt(request.getAttribute("fromAdju").toString());
					System.out.println("from Adju flag is: " + fromAdju);
					// session.setAttribute("fromAdju", fromAdju);
					eForm.setFromAdjudication(fromAdju);
					// end
				}

				if (pageName != null && "propertyvalutioan".equalsIgnoreCase(pageName)) {

					dto.getKhasraDetlsDisplay().add(new CommonDTO());

					// Added By Aruna
					if (session.getAttribute(RegInitConstant.PROPERTY_DTO_SESSION) != null) {

						forwardJsp = "propdetails";
					} else {

						// ArrayList distlist = bd.getDistrict();
						// dto.setDistList(distlist);

						String valId = "";
						boolean propertyInserted = false;

						if (request.getAttribute("valTxnId") != null) {

							valId = (String) request.getAttribute("valTxnId");

							commonBo.getPropertyListNPVDeed(eForm, languageLocale, valId, dto);

							propertyInserted = commonBo.insrtPropDetlsNonPVDeeds(eForm);

						} else {

							String latest[] = commonBo.getLatestPropertyId(eForm.getRegInitForm().getHidnRegTxnId());

							dto.setSelectedPropValId(latest[1]);
							dto.setSelectedPropId(latest[0]);

							regInitForm.setTermsCondPartyAppKhasra("");

							if (dto.getSelectedPropId().length() == 15) {
								dto.setSelectedPropId("0" + dto.getSelectedPropId());

							}

							String clrFlag = "N";

							clrFlag = commonBo.getClrFlag(dto.getSelectedPropId());

							String clrF = commonBo.getClrByClrTable(dto.getSelectedPropValId());

							if (clrF != null && !clrF.isEmpty()) {

								if (clrF.equalsIgnoreCase("Y")) {

									eForm.setClrFlag(clrF);
								} else {
									eForm.setClrFlag("");
								}
							} else {
								eForm.setClrFlag("");
							}

						}

						// insert prop details in reg init tables.
						// eForm.setExchangePropertyList(new ArrayList());

						// commonBo.getPropertyListNPVDeed(eForm,
						// languageLocale, valId,dto);

						// boolean propertyInserted =
						// commonBo.insrtPropDetlsNonPVDeeds(eForm);

						// below is retrieval of data for display on screen

						if (propertyInserted || (request.getAttribute("valTxnId") == null)) {
							ArrayList pform = new ArrayList();
							pform = commonBo.getPropDetlsForDashboard(dto.getSelectedPropValId(), dto.getSelectedPropId());

							ArrayList rowList = new ArrayList();
							if (pform != null && pform.size() == 1)
								rowList = (ArrayList) pform.get(0);

							eForm.setPropertyTypeId(rowList.get(0).toString());
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								eForm.setPropertyType1(rowList.get(1).toString());
							} else {
								eForm.setPropertyType1(rowList.get(2).toString());
							}
							eForm.setPropertyId(dto.getSelectedPropId());

							eForm.getRegcompletDto().setGovmunciplId(rowList.get(3) != null ? rowList.get(3).toString() : "");
							eForm.getRegcompletDto().setAreaTypeId(rowList.get(4) != null ? rowList.get(4).toString() : "");
							eForm.setInstID(regInitForm.getInstID());
							eForm.setDeedID(regInitForm.getDeedID());
							if (eForm.getPropertyTypeId().equalsIgnoreCase("1")) { // plot
								eForm.setClrFlag("");
								// eForm.getValuationId();
								dto.setTotalSqMeter(commonBo.getPlotTotArea(dto.getSelectedPropValId()));

							} else if (eForm.getPropertyTypeId().equalsIgnoreCase("2")) { // building
								eForm.setClrFlag("");

							} else if (eForm.getPropertyTypeId().equalsIgnoreCase("3")) { // agri

								// eForm.getValuationId();
								dto.setTotalSqMeter(commonBo.getAgriTotArea(dto.getSelectedPropValId()));

								// added for clr integration by akansha

								String propertyId = eForm.getPropertyId();
								String valTxnId = commonBo.getValTxnID(eForm.getPropertyId());

								eForm.setValTxnID(valTxnId);
								String getPropType = commonBo.getPropertyTypeID(propertyId);
								// added by akansha for clr
								String checkClrFlagByValuationID = commonBo.getClrByClrTable(valTxnId);
								if (checkClrFlagByValuationID != null && !checkClrFlagByValuationID.isEmpty()) {

									if (getPropType.equalsIgnoreCase("3") && checkClrFlagByValuationID.equalsIgnoreCase("Y")) {
										boolean flag = commonBo.updatePropertyKhasraDetls(eForm, userID);

										if (flag) {

											ArrayList khasraDetails = commonBo.getkhasraDetails(propertyId);

											ArrayList subList;

											RegCompletDTO paramDto1;
											ArrayList<RegCompletDTO> paramList1 = new ArrayList<RegCompletDTO>();
											for (int i = 0; i < khasraDetails.size(); i++) {
												subList = (ArrayList) khasraDetails.get(i);
												paramDto1 = new RegCompletDTO();
												/*
												 * eForm.getRegcompletDto().setClrkhasraNo
												 * (subList.get(0).toString());
												 * eForm.getRegcompletDto().
												 * setKhasaraNum1
												 * (subList.get(0).toString());
												 * eForm
												 * .getRegcompletDto().setKhasraId
												 * (subList.get(1).toString());
												 * 
												 * eForm.getRegcompletDto().
												 * setClrKhasraArea
												 * (subList.get(2).toString());
												 * eForm.getRegcompletDto().
												 * setKhasraSellableArea
												 * (subList.get(3).toString());
												 * eForm.getRegcompletDto().
												 * setKhasraArea1
												 * (subList.get(3).toString());
												 */
												dto.setRinpustikaNum(subList.get(4).toString());
												eForm.getRegcompletDto().setRinpustikaNum(subList.get(4).toString());
												/*
												 * dto.setClrkhasraNo(subList.get
												 * (0).toString());
												 * dto.setKhasaraNum1
												 * (subList.get(0).toString());
												 * dto
												 * .setKhasraId(subList.get(1)
												 * .toString());
												 * 
												 * 
												 * dto.setClrKhasraArea(subList.get
												 * (2).toString());
												 * dto.setKhasraSellableArea
												 * (subList.get(3).toString());
												 * dto
												 * .setKhasraArea1(subList.get
												 * (3).toString());
												 */

												paramDto1.setClrkhasraNo(subList.get(0).toString());
												paramDto1.setKhasaraNum1(subList.get(0).toString());
												paramDto1.setKhasraId(subList.get(1).toString());

												paramDto1.setClrKhasraArea(subList.get(2).toString());
												paramDto1.setKhasraSellableArea(subList.get(3).toString());
												paramDto1.setKhasraArea1(subList.get(3).toString());
												paramDto1.setRinpustikaNum(subList.get(4).toString());
												paramDto1.setRicircle(subList.get(5).toString());
												paramDto1.setNorth("");
												paramDto1.setSouth("");
												paramDto1.setEast("");
												paramDto1.setWest("");
												paramDto1.setLagaan("");
												paramList1.add(paramDto1);

											}

											eForm.setKhasraArrayList(paramList1);

										}

										// eForm.setClrFlag(commonBo.getClrFlag(eForm.getPropertyId()));

										// regInitForm.setClrFlg(eForm.getClrFlag());

										String clrF = commonBo.getClrByClrTable(valTxnId);

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

										// CLR Web Service calling: Start

										for (int k = 0; k < eForm.getKhasraArrayList().size(); k++) {
											// Added by Rakesh for CLR
											// Integration: Start
											if (regInitForm.getClrFlg() != null && eForm.getKhasraArrayList().get(k).getKhasraId() != null) {

												if (regInitForm.getClrFlg().equalsIgnoreCase("Y")) {

													eForm.setClrErrorFlag("0");
													eForm.setPropertySelected(1);
													if (regInitForm.getClrFlg().equalsIgnoreCase("Y")) {

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
															// paramDto1.setRinpustikaNum(subList.get(4).toString());
															paramDto1.setRinpustikaNum(subList.get(4).toString());
															paramDto1.setRicircle(subList.get(5).toString());
															paramDto1.setNorth("");
															paramDto1.setSouth("");
															paramDto1.setEast("");
															paramDto1.setWest("");
															paramDto1.setLagaan("");
															paramList1.add(paramDto1);
														}

														eForm.setKhasraArrayList(paramList1);

													}
													// return
													// mapping.findForward(forwardJsp);

												}
											}
										}
									}
								}

								// CLR Web Service calling: Start

							}

							// get parameterd for mpcst
							commonBo.getMPCSTParams(dto.getSelectedPropValId(), eForm, dto);

							// MPCST AND dashboard
							/*
							 * if(rowList.get(5)!=null && rowList.get(6)!=null
							 * && rowList.get(7)!=null &&
							 * !rowList.get(5).toString().equalsIgnoreCase("")
							 * &&
							 * !rowList.get(6).toString().equalsIgnoreCase("")
							 * &&
							 * rowList.get(7).toString().equalsIgnoreCase("Y"))
							 * { dto.setMpcstSuccessFlag("Y");
							 * dto.setFile1Path(rowList.get(5).toString());
							 * dto.setFile2Path(rowList.get(6).toString());
							 * dto.setFile1PathContents
							 * (DMSUtility.getDocumentBytes
							 * (rowList.get(5).toString()));
							 * dto.setFile2PathContents
							 * (DMSUtility.getDocumentBytes
							 * (rowList.get(6).toString())); } else
							 * if(rowList.get(7)!=null &&
							 * rowList.get(7).toString().equalsIgnoreCase("N")){
							 * dto.setMpcstSuccessFlag(null);
							 * eForm.setIGRS_DATA_AVLBL("N"); } else{
							 * dto.setMpcstSuccessFlag("");
							 * eForm.setIGRS_DATA_AVLBL(null); }
							 */

						} else {
							session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
							forwardJsp = "failure";
							mapping.findForward(forwardJsp);
						}

						eForm.setFormName("");
						eForm.setActionName("");
						eForm.setRegcompletDto(dto);
						if (eForm.getClrFlag() != null && !eForm.getClrFlag().isEmpty()) {
							if (eForm.getClrFlag().equalsIgnoreCase("y")) {

								forwardJsp = "propdetailsOldCLR";
							} else {
								forwardJsp = "propdetailsOld";
							}
						} else {

							forwardJsp = "propdetailsOld";
						}
					}
				}

				if (pageName != null && "propertyView".equalsIgnoreCase(pageName)) {

					if (RegInitConstant.MODIFY_PROPERTY_ACTION.equals(actionName)) {
						/*
						 * //by shreeraj int
						 * fromAdju=Integer.parseInt(request.getAttribute
						 * ("fromAdju").toString());
						 * System.out.println("from Adju flag is: "+fromAdju);
						 * eForm.setFromAdjudication(fromAdju); //end
						 */// session.getAttribute("fromAdju");
						/*
						 * System.out.println("AAAAAAAAAAAAAAA");
						 * System.out.println
						 * ("VALUE>>>>>>>>>"+(String)session.getAttribute
						 * ("fromAdju")); int
						 * fromAdju=Integer.parseInt((String)session
						 * .getAttribute("fromAdju"));
						 */
						// System.out.println("from aju issss>>>>>>>>>>>>>>>>>>>>:"+fromAdju);
						// eForm.setFromAdjudication(fromAdju);

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

						forwardJsp = "editPropDetails";

					}
				}

				else if (pageName != null && "propertyViewClr".equalsIgnoreCase(pageName)) {

					if (RegInitConstant.MODIFY_PROPERTY_ACTION.equals(actionName)) {
						String valTxnId = commonBo.getValTxnID(eForm.getPropertyId());
						String clrF = commonBo.getClrByClrTable(valTxnId);

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
						// request.setAttribute("deedId",
						// regInitForm.getDeedID());
						// forwardJsp="editPropDetailsCLR";
						forwardJsp = "editPropDetailsCLR";

					}
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

					formName = "";
					actionName = "";
					eForm.setActionName("");

					forwardJsp = "mpcstUploads";
					request.setAttribute("deedId", eForm.getRegInitForm().getDeedID());

				}
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

						if (RegInitConstant.ADD_KHASRA_ACTION.equals(actionName)) {
							forwardJsp = "propdetailsOld";
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
						if (RegInitConstant.DEL_KHASRA_ACTION.equals(actionName)) {
							forwardJsp = "propdetailsOld";
						} else {
							forwardJsp = "editPropDetails";
						}
					}

					if (RegInitConstant.REGCOMP_DISTRICT_ACTION.equals(actionName)) {

						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);

						eForm.setTehsilList(new ArrayList());
						if (eForm.getRegcompletDto().getDistId() != null) {
							logger.debug("dto.getDistId():-" + eForm.getRegcompletDto());
							String[] param = eForm.getRegcompletDto().getDistId().split("~");
							if (param != null && param.length > 0) {
								// eForm.setTehsilList(newPropBO.getTehsil(languageLocale,param[0]));//hindi
								// missing

								ArrayList tehsilList = commonBo.getTehsil(languageLocale, param[0]);
								if (tehsilList != null) {
									eForm.setTehsilList(tehsilList);
								} else {
									eForm.setTehsilList(new ArrayList());
								}

								logger.debug("inside");
								forwardJsp = "propdetailsOld";

							}
						}
					}
					// added by shruti
					if (RegInitConstant.REGCOMP_PROPERTYTYPE_ACTION.equals(actionName)) // NEEDED
																						// CORRECT

					{
						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);

						commonBo = new RegCommonBO();
						String propId = eForm.getRegcompletDto().getPropertyTypeId();
						String thesilId = eForm.getRegcompletDto().getTehsilId();
						String[] paramProperty = new String[0];
						paramProperty = propId.split("~");
						logger.debug("propertyId:-" + propId + ":" + paramProperty[0]);
						String paramProp = "0";
						if (paramProperty != null) {
							paramProp = paramProperty[0];

							if (paramProp.equalsIgnoreCase("select")) {
								paramProp = "0";
							}

						}
						eForm.setPropTypeL1List(commonBo.getPropTypeL1List(paramProp, languageLocale));
						eForm.setPropTypeL2List(new ArrayList());
						eForm.setUnitList(new ArrayList());
						eForm.setFloorTypeList(new ArrayList());
						if (!paramProp.equalsIgnoreCase("2")) { // 2 is for
																// building
							eForm.getRegcompletDto().setMapBuilding(new HashMap());
							eForm.getRegcompletDto().setAddMoreFloorCounter(0);
							eForm.getRegcompletDto().setUnit("");

						} else {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								eForm.getRegcompletDto().setUnit(RegInitConstant.SQM_ENGLISH);
							} else {
								eForm.getRegcompletDto().setUnit(RegInitConstant.SQM_HINDI);
							}
						}
						eForm.getRegcompletDto().setArea(0);
						eForm.getRegcompletDto().setPropTypeL2Flag(0);

						forwardJsp = "propdetailsOld";
					}
					if (RegInitConstant.REGCOMP_PROPL1TYPE_ACTION.equals(actionName)) // NEEDED

					{
						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);

						commonBo = new RegCommonBO();
						String propL1Id = eForm.getRegcompletDto().getPropTypeL1Id();
						String[] paramProperty;
						paramProperty = propL1Id.split("~");
						logger.debug("propertyId:-" + propL1Id + ":" + paramProperty[0]);
						String paramProp = "";
						if (paramProperty != null && paramProperty.length == 2) {
							paramProp = paramProperty[0];
						}
						eForm.setPropTypeL2List(commonBo.getPropTypeL2List(paramProp, languageLocale));

						if (eForm.getPropTypeL2List() != null && eForm.getPropTypeL2List().size() > 0) {
							eForm.getRegcompletDto().setPropTypeL2Flag(1);
						} else {
							eForm.getRegcompletDto().setPropTypeL2Flag(0);
						}

						if (eForm.getRegcompletDto().getPropertyTypeId().equalsIgnoreCase("2")) {
							eForm.setFloorTypeList(commonBo.getFloorType(paramProp, languageLocale)); // get
																										// floor
																										// list
																										// in
																										// case
																										// of
																										// property
																										// type
																										// building
						}
						eForm.setUnitList(commonBo.getUnitList(paramProp, languageLocale));

						forwardJsp = "propdetailsOld";
					}
					if (RegInitConstant.REGCOMP_PROPL2TYPE_ACTION.equals(actionName)) // NEEDED

					{
						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);

						commonBo = new RegCommonBO();
						// String
						// propL1Id=eForm.getRegcompletDto().getPropTypeL1Id();
						// String[] paramProperty;
						// paramProperty = propL1Id.split("~");
						// logger.debug("propertyId:-" + propL1Id + ":"+
						// paramProperty[0]);
						// String paramProp = "";
						// if (paramProperty != null && paramProperty.length ==
						// 2) {
						// paramProp = paramProperty[0];
						// }
						// eForm.setPropTypeL2List(commonBo.getPropTypeL2List(paramProp,languageLocale));

						// if(eForm.getPropTypeL2List()!=null &&
						// eForm.getPropTypeL2List().size()>0){
						// eForm.getRegcompletDto().setPropTypeL2Flag(1);
						// }else{
						// eForm.getRegcompletDto().setPropTypeL2Flag(0);
						// }

						if (eForm.getRegcompletDto().getPropTypeL2Id().equalsIgnoreCase("8")) {// L2
																								// RESIDENTIAL
							eForm.setFloorTypeList(commonBo.getFloorType("18", languageLocale)); // LI
																									// RESIDENTIAL
																									// ID
																									// //get
																									// floor
																									// list
																									// in
																									// case
																									// of
																									// property
																									// type
																									// building
						} else if (eForm.getRegcompletDto().getPropTypeL2Id().equalsIgnoreCase("9")) {// L2
																										// COMMERCIAL
							eForm.setFloorTypeList(commonBo.getFloorType("19", languageLocale)); // L1
																									// COMMERCIAL
																									// ID
																									// //get
																									// floor
																									// list
																									// in
																									// case
																									// of
																									// property
																									// type
																									// building
						}
						// eForm.setUnitList(commonBo.getUnitList(paramProp,languageLocale));

						forwardJsp = "propdetailsOld";
					}

					// end of code added by shruti

					if (RegInitConstant.CANCEL_ACTION.equals(actionName)) {
						resetValue(eForm.getPropertyDTO(), eForm.getRegcompletDto());
						session.removeAttribute(RegInitConstant.PROPERTY_DTO_SESSION);
						session.removeAttribute(RegInitConstant.PROPERTY_TYPE_SESSION);
						session.removeAttribute("considerAmt");
						session.removeAttribute("systemValue");
						session.removeAttribute(CommonConstant.HASHMAP_DTO_SESSION);
						session.removeAttribute("sessionPropertyTxnId");

						CommonAction.cancelAction(session, regInitForm);
						forwardJsp = "welcome";
					}

					if (RegInitConstant.REGCOMP_SUBAREA_ACTION.equals(actionName)) {

						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);

						String urbanFlag = "N";
						if ("2".equalsIgnoreCase(eForm.getRegcompletDto().getAreaTypeId())) {
							urbanFlag = "Y";
						}
						// eForm.setMunicipalList(newPropBO.getSubArea(languageLocale,eForm.getRegcompletDto().getAreaTypeId()));

						ArrayList municipalList = newPropBO.getSubArea(languageLocale, eForm.getRegcompletDto().getAreaTypeId(), eForm.getRegcompletDto().getTehsilId(), urbanFlag);
						if (municipalList != null) {
							eForm.setMunicipalList(municipalList);
						} else {
							eForm.setMunicipalList(new ArrayList());
						}

						forwardJsp = "propdetailsOld";

					}

					if (RegInitConstant.REGCOMP_WARDPATWARI_ACTION.equals(actionName)) {

						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);

						ArrayList wardList = newPropBO.getWardPatwari(languageLocale, eForm.getRegcompletDto().getGovmunciplId(), eForm.getRegcompletDto().getTehsilId());
						if (wardList != null) {
							eForm.setWardList(wardList);
						} else {
							eForm.setWardList(new ArrayList());
						}
						// eForm.setWardList(newPropBO.getWardPatwari(languageLocale,eForm.getRegcompletDto().getGovmunciplId(),eForm.getRegcompletDto().getTehsilId()));
						forwardJsp = "propdetailsOld";

					}
					if (RegInitConstant.REGCOMP_MOHALLA_ACTION.equals(actionName)) {

						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);

						ArrayList mohallaList = newPropBO.getColonyName(languageLocale, eForm.getRegcompletDto().getWardId());
						if (mohallaList != null) {
							eForm.setMahallaList(mohallaList);
						} else {
							eForm.setMahallaList(new ArrayList());
						}
						// eForm.setMahallaList(newPropBO.getColonyName(languageLocale,eForm.getRegcompletDto().getWardId()));
						forwardJsp = "propdetailsOld";

					}

					if (RegInitConstant.ADD_FLOOR_ACTION.equals(actionName))

					{

						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);

						logger.debug("action name floor 1--->" + eForm.getActionName());

						eForm.setActionName(RegInitConstant.NO_ACTION);

						logger.debug("action name floor 2--->" + eForm.getActionName());
						HashMap mapFloor = eForm.getRegcompletDto().getMapBuilding();

						RegCompletDTO mapDto = new RegCompletDTO();

						mapDto.setPropTypeL1Id(eForm.getRegcompletDto().getPropTypeL1Id());
						String[] l1 = eForm.getRegcompletDto().getPropTypeL1Id().split("~");
						mapDto.setPropTypeL1Name(l1[1]); // prop type l1 name
						mapDto.setPropTypeL2Id(eForm.getRegcompletDto().getPropTypeL2Id());
						mapDto.setTypeFloorID(eForm.getRegcompletDto().getFloorId());
						String[] floor = eForm.getRegcompletDto().getFloorId().split("~");
						mapDto.setTypeFloorName(floor[1]); // floor name
						mapDto.setAreaConstructed(eForm.getRegcompletDto().getAreaConstructed());
						mapDto.setArea(eForm.getRegcompletDto().getArea());
						mapDto.setUnitTypeId(eForm.getRegcompletDto().getUnitId());
						String[] unit = eForm.getRegcompletDto().getUnitId().split("~");
						mapDto.setUnitType(unit[1]); // unit name
						// mapDto.setFloorTxnId(floorTxnId);

						int count = eForm.getRegcompletDto().getAddMoreFloorCounter();
						if (count == 0)
							count = 1;

						if (mapFloor.containsKey(Integer.toString(count))) {

							count++;
							mapFloor.put(Integer.toString(count), mapDto);

						} else
							mapFloor.put(Integer.toString(count), mapDto);
						// mapFloor.put("", mapDto);

						eForm.getRegcompletDto().setMapBuilding(mapFloor);
						eForm.getRegcompletDto().setAddMoreFloorCounter(count);
						eForm.setPropTypeL1List(commonBo.getPropTypeL1List(eForm.getRegcompletDto().getPropertyTypeId(), languageLocale));
						eForm.setPropTypeL2List(new ArrayList());
						eForm.setUnitList(new ArrayList());
						eForm.setFloorTypeList(new ArrayList());
						eForm.getRegcompletDto().setArea(0);
						eForm.getRegcompletDto().setAreaConstructed(0);
						eForm.getRegcompletDto().setPropTypeL1Id("");

						forwardJsp = "propdetailsOld";
						eForm.setForwardJsp(forwardJsp);
					}
					if (RegInitConstant.DELETE_FLOOR_ACTION.equals(actionName)) // NEEDED

					{

						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);

						String[] floorID = eForm.getRegcompletDto().getHdnDeleteFloorID().split(",");
						HashMap floorMap = eForm.getRegcompletDto().getMapBuilding();

						for (int j = 0; j < floorID.length; j++) {
							logger.debug(floorID[j] + " is removed...");
							floorMap.remove(floorID[j]);

						}
						eForm.getRegcompletDto().setMapBuilding(floorMap);

						forwardJsp = "propdetailsOld";
					}

					if (RegInitConstant.UNIT_ACTION.equals(actionName)) // NEEDED
																		// CORRECT
					{

						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);

						if (eForm.getRegcompletDto().getUnitId() != null) {
							logger.debug("dto.getUnitId():-" + eForm.getRegcompletDto().getUnitId());
							String[] param = eForm.getRegcompletDto().getUnitId().split("~");
							if (param != null && param.length == 2) {
								eForm.getRegcompletDto().setUnit(param[1].trim());
								logger.debug("inside UNIT_ACTION");

							} else {
								eForm.getRegcompletDto().setUnit("");
							}
							forwardJsp = "propdetailsOld";
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
							forwardJsp = "propdetailsOld";
						}
						if (label.equalsIgnoreCase("displayPropEdit")) {
							request.setAttribute("deedId", regInitForm.getDeedID());
							eForm.setActionName("");
							actionName = "";
							forwardJsp = "editPropDetails";
						}
					}
				}

				// below code by roopam
				if (RegInitConstant.REGCOM_PROPERTY_SEARCH.equals(formName)) {
					if (RegInitConstant.NEXT_TO_CONFIRM_ACTION // NEEDED
					.equals(actionName)) {

						// ews re-calculated duty.
						String[] newDuties = new CalculateDuty().calculateDutiesEWS_NPV(eForm, eForm.getHidnRegTxnId());

						ArrayList<CommonDTO> khasraDets = eForm.getRegcompletDto().getKhasraDetlsDisplay();
						if (khasraDets != null && khasraDets.size() > 0) {
							commonBo.refreshKhasraData(khasraDets, request);
						}
						eForm.getRegcompletDto().setPropertyTypeName(commonBo.getPropertyTypeName(eForm.getRegcompletDto().getPropertyTypeId(), languageLocale));
						HashMap mapProperty = eForm.getMapProperty();
						// HashMap lastMapProperty=new HashMap();
						int countProp = eForm.getAddMoreCounter();
						eForm.getRegcompletDto().setPropertyId(eForm.getRegcompletDto().getSelectedPropId());
						if (countProp == 0)
							countProp = 1;

						if (mapProperty.containsKey(Integer.toString(countProp))) {

							countProp++;
							mapProperty.put(Integer.toString(countProp), eForm.getRegcompletDto());
							// lastMapProperty.put(Integer.toString(countProp),
							// eForm.getRegcompletDto());
						} else {
							mapProperty.put(Integer.toString(countProp), eForm.getRegcompletDto());
							// lastMapProperty.put(Integer.toString(countProp),
							// eForm.getRegcompletDto());
						}

						eForm.setMapProperty(mapProperty);
						eForm.setAddMoreCounter(countProp);

						// Collection mapCollection=lastMapProperty.values();
						// Object[] l1=mapCollection.toArray();
						RegCompletDTO mapDto1 = eForm.getRegcompletDto();

						boolean propertyDetailsInserted = false;

						// for(int i=0;i<l1.length;i++)
						// {

						// mapDto1=(RegCompletDTO)l1[i];

						CommonAction action = new CommonAction();

						String returnJsp = commonBo.validateMPCST(mapDto1, eForm, request, mapDto1.getPropertyId());
						if (returnJsp.equalsIgnoreCase("error")) {
							forwardJsp = "propdetailsOld";
							return mapping.findForward(forwardJsp);
						}

						boolean check = true;
						ArrayList<CommonDTO> dto1 = eForm.getListDtoP();
						if (dto1 != null && dto1.size() > 0) {
							for (int K = 0; K < dto1.size(); K++) {
								CommonDTO dtos = dto1.get(K);
								dtos.setDocumentName(commonBo.getNewFileName(dtos.getDocumentName(), Integer.toString(K)));
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

						if (check) {
							String filePathAuditReport = ""; // added by ankit
																// for plant and
																// machinery
							String filePathAngle1 = "";
							String filePathAngle2 = "";
							String filePathAngle3 = "";
							String filePathMap = "";
							String filePathRin = "";
							String filePathKhasra = "";
							// boolean propertyDetailsInserted=false;

							String propTypeId = eForm.getPropertyTypeId();
							String municipalId = mapDto1.getGovmunciplId();
							String areaTypeId = mapDto1.getAreaTypeId();

							String getPropTypeCheck = commonBo.getPropertyTypeID(eForm.getPropertyId());

							String valuationID = commonBo.getValTxnID(eForm.getPropertyId());
							String clrF = commonBo.getClrByClrTable(valuationID);

							if (eForm.getInstID() != 2012 && eForm.getInstID() != 2016 && (propTypeId.equals("1") || propTypeId.equals("2") || (propTypeId.equals("3") && (areaTypeId.equals("2") || municipalId.equals("5"))))) {
								// added by ankit for plant and machinery
								if (mapDto1.getAuditReportDocumentName() != null && !mapDto1.getAuditReportDocumentName().equalsIgnoreCase("")) {

									filePathAuditReport = action.uploadFile(eForm.getHidnRegTxnId(), mapDto1.getAuditReportDocContents(), eForm.getPropertyId(), mapDto1.getAuditReportPartyOrProp(), mapDto1.getAuditReportUploadType());
								}
								mapDto1.setAuditReportFilePath(filePathAuditReport);
								// added by ankit for plant and machinery--end
								// -->>

								if (mapDto1.getPropImage1DocumentName() != null && !mapDto1.getPropImage1DocumentName().equalsIgnoreCase("")) {
									filePathAngle1 = action.uploadFile(eForm.getHidnRegTxnId(), mapDto1.getPropImage1DocContents(), mapDto1.getPropertyId(), mapDto1.getPropImage1PartyOrProp(), mapDto1.getPropImage1UploadType());
								} else {
									filePathAngle1 = "";
								}

								if (filePathAngle1 != null) {
									mapDto1.setPropImage1FilePath(filePathAngle1);
									/*
									 * filePathAngle2=
									 * action.uploadFile(eForm.getHidnRegTxnId
									 * (),mapDto1.getPropImage2DocContents(),
									 * mapDto1.getPropertyId(),mapDto1.
									 * getPropImage2PartyOrProp
									 * (),mapDto1.getPropImage2UploadType());
									 * if(filePathAngle2!=null &&
									 * !filePathAngle2.equalsIgnoreCase("")){
									 * mapDto1
									 * .setPropImage2FilePath(filePathAngle2);
									 * filePathAngle3=
									 * action.uploadFile(eForm.getHidnRegTxnId
									 * (),mapDto1.getPropImage3DocContents(),
									 * mapDto1.getPropertyId(),mapDto1.
									 * getPropImage3PartyOrProp
									 * (),mapDto1.getPropImage3UploadType());
									 * if(filePathAngle3!=null &&
									 * !filePathAngle3.equalsIgnoreCase("")){
									 * mapDto1
									 * .setPropImage3FilePath(filePathAngle3);
									 */
									if (mapDto1.getPropMapDocumentName() != null && !mapDto1.getPropMapDocumentName().equalsIgnoreCase("")) {
										filePathMap = action.uploadFile(eForm.getHidnRegTxnId(), mapDto1.getPropMapDocContents(), mapDto1.getPropertyId(), mapDto1.getPropMapPartyOrProp(), mapDto1.getPropMapUploadType());
									} else {
										filePathMap = "";
									}
									if (filePathMap != null && eForm.getPropertyTypeId().equalsIgnoreCase("3")) {
										mapDto1.setPropMapFilePath(filePathMap);
										filePathRin = action.uploadFile(eForm.getHidnRegTxnId(), mapDto1.getPropRinDocContents(), mapDto1.getPropertyId(), mapDto1.getPropRinPartyOrProp(), mapDto1.getPropRinUploadType());
										if (filePathRin != null && !filePathRin.equalsIgnoreCase("")) {
											mapDto1.setPropRinFilePath(filePathRin);
											filePathKhasra = action.uploadFile(eForm.getHidnRegTxnId(), mapDto1.getPropKhasraDocContents(), mapDto1.getPropertyId(), mapDto1.getPropKhasraPartyOrProp(), mapDto1.getPropKhasraUploadType());
											if (filePathKhasra != null && !filePathKhasra.equalsIgnoreCase("")) {
												mapDto1.setPropKhasraFilePath(filePathKhasra);

												// clrOwnerShare

												if (clrF != null && !clrF.isEmpty()) {
													if (getPropTypeCheck.equalsIgnoreCase("3") && clrF.equalsIgnoreCase("y")) {

														propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeedsCLR(regInitForm, mapDto1, RegInitConstant.STATUS_PROP_SAVED, eForm, newDuties);

													} else {

														propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeeds(regInitForm, mapDto1, RegInitConstant.STATUS_PROP_SAVED, eForm, newDuties);

													}
												} else {

													propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeeds(regInitForm, mapDto1, RegInitConstant.STATUS_PROP_SAVED, eForm, newDuties);
												}

											} else {
												// break;
											}

										} else {
											// break;
										}

									} else if (filePathMap != null) {

										mapDto1.setPropMapFilePath(filePathMap);

										if (clrF != null && !clrF.isEmpty()) {
											if (getPropTypeCheck.equalsIgnoreCase("3") && clrF.equalsIgnoreCase("y")) {

												propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeedsCLR(regInitForm, mapDto1, RegInitConstant.STATUS_PROP_SAVED, eForm, newDuties);

											} else {

												propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeeds(regInitForm, mapDto1, RegInitConstant.STATUS_PROP_SAVED, eForm, newDuties);

											}
										} else {

											propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeeds(regInitForm, mapDto1, RegInitConstant.STATUS_PROP_SAVED, eForm, newDuties);
										}
									} else {
										// break;
									}

									/*
									 * }else{ //break; }
									 * 
									 * 
									 * }else{ //break; }
									 */

								} else {
									// break;
								}

							} else {
								// added by ankit for plant and machinery
								if (mapDto1.getAuditReportDocumentName() != null && !mapDto1.getAuditReportDocumentName().equalsIgnoreCase("")) {

									filePathAuditReport = action.uploadFile(eForm.getHidnRegTxnId(), mapDto1.getAuditReportDocContents(), eForm.getPropertyId(), mapDto1.getAuditReportPartyOrProp(), mapDto1.getAuditReportUploadType());
								}
								mapDto1.setAuditReportFilePath(filePathAuditReport);
								// added by ankit for plant and machinery--end
								// -->>

								if (mapDto1.getPropImage1DocumentName() != null && !mapDto1.getPropImage1DocumentName().equalsIgnoreCase("")) {
									filePathAngle1 = action.uploadFile(eForm.getHidnRegTxnId(), mapDto1.getPropImage1DocContents(), mapDto1.getPropertyId(), mapDto1.getPropImage1PartyOrProp(), mapDto1.getPropImage1UploadType());

									mapDto1.setPropImage1FilePath(filePathAngle1);
								} else {
									mapDto1.setPropImage1FilePath("");
								}

								/*
								 * if(mapDto1.getPropImage2DocContents()!=null){
								 * filePathAngle2=
								 * action.uploadFile(eForm.getHidnRegTxnId
								 * (),mapDto1.getPropImage2DocContents(),
								 * mapDto1
								 * .getPropertyId(),mapDto1.getPropImage2PartyOrProp
								 * (),mapDto1.getPropImage2UploadType());
								 * 
								 * 
								 * mapDto1.setPropImage2FilePath(filePathAngle2);
								 * }else{ mapDto1.setPropImage2FilePath(""); }
								 * 
								 * 
								 * if(mapDto1.getPropImage3DocContents()!=null){
								 * filePathAngle3=
								 * action.uploadFile(eForm.getHidnRegTxnId
								 * (),mapDto1.getPropImage3DocContents(),
								 * mapDto1
								 * .getPropertyId(),mapDto1.getPropImage3PartyOrProp
								 * (),mapDto1.getPropImage3UploadType());
								 * 
								 * 
								 * mapDto1.setPropImage3FilePath(filePathAngle3);
								 * }else{ mapDto1.setPropImage3FilePath(""); }
								 */

								if (mapDto1.getPropMapDocumentName() != null && !mapDto1.getPropMapDocumentName().equalsIgnoreCase("")) {
									filePathMap = action.uploadFile(eForm.getHidnRegTxnId(), mapDto1.getPropMapDocContents(), mapDto1.getPropertyId(), mapDto1.getPropMapPartyOrProp(), mapDto1.getPropMapUploadType());

									mapDto1.setPropMapFilePath(filePathMap);
								} else {
									mapDto1.setPropMapFilePath("");
								}

								if (eForm.getPropertyTypeId().equalsIgnoreCase("3")) {

									filePathRin = action.uploadFile(eForm.getHidnRegTxnId(), mapDto1.getPropRinDocContents(), mapDto1.getPropertyId(), mapDto1.getPropRinPartyOrProp(), mapDto1.getPropRinUploadType());
									if (filePathRin != null && !filePathRin.equalsIgnoreCase("")) {
										mapDto1.setPropRinFilePath(filePathRin);
										filePathKhasra = action.uploadFile(eForm.getHidnRegTxnId(), mapDto1.getPropKhasraDocContents(), mapDto1.getPropertyId(), mapDto1.getPropKhasraPartyOrProp(), mapDto1.getPropKhasraUploadType());
										if (filePathKhasra != null && !filePathKhasra.equalsIgnoreCase("")) {
											mapDto1.setPropKhasraFilePath(filePathKhasra);
											// propertyDetailsInserted =
											// commonBo.insertPropertyDetailsNonPropDeeds(regInitForm,mapDto1,RegInitConstant.STATUS_PROP_SAVED,eForm,newDuties);

											if (clrF != null && !clrF.isEmpty()) {
												if (getPropTypeCheck.equalsIgnoreCase("3") && clrF.equalsIgnoreCase("y")) {

													propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeedsCLR(regInitForm, mapDto1, RegInitConstant.STATUS_PROP_SAVED, eForm, newDuties);

												} else {

													propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeeds(regInitForm, mapDto1, RegInitConstant.STATUS_PROP_SAVED, eForm, newDuties);

												}
											} else {

												propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeeds(regInitForm, mapDto1, RegInitConstant.STATUS_PROP_SAVED, eForm, newDuties);
											}
										} else {
											// break;
										}
									} else {
										// break;
									}
								} else {

									mapDto1.setPropRinFilePath("");
									mapDto1.setPropKhasraFilePath("");
									if (clrF != null && !clrF.isEmpty()) {
										if (getPropTypeCheck.equalsIgnoreCase("3") && clrF.equalsIgnoreCase("y")) {

											propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeedsCLR(regInitForm, mapDto1, RegInitConstant.STATUS_PROP_SAVED, eForm, newDuties);

										} else {

											propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeeds(regInitForm, mapDto1, RegInitConstant.STATUS_PROP_SAVED, eForm, newDuties);

										}
									} else {

										propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeeds(regInitForm, mapDto1, RegInitConstant.STATUS_PROP_SAVED, eForm, newDuties);
									}
								}

							}

						} else {
							propertyDetailsInserted = false;
						}

						logger.debug("property details inserted :- " + propertyDetailsInserted);

						/*
						 * propertyDetailsInserted =
						 * commonBo.insertPropertyDetailsNonPropDeeds
						 * (regInitForm
						 * ,mapDto1,RegInitConstant.STATUS_PROP_SAVED);
						 * logger.debug
						 * ("property details inserted :- "+propertyDetailsInserted
						 * );
						 */

						// }
						// boolean propertyDetailsInserted;

						if (propertyDetailsInserted) {

							// forwardJsp = "reginitConfirm";
							eForm.setActionName("");
							eForm.getRegcompletDto().setPropTypeL2Flag(0);
							actionName = RegInitConstant.NEXT_TO_MAPPING_ACTION;
						} else {

							eForm.setHidnRegTxnId(null);
							eForm.setActionName("");
							eForm.getRegcompletDto().setPropTypeL2Flag(0);
							eForm.setMapProperty(new HashMap());
							forwardJsp = "propdetailsOld";

						}

					}
					// ADDED BY SHREERAJ
					if (RegInitConstant.NEXT_TO_MAPPING_ACTION.equals(actionName)) {

						String mapCheck = eForm.getRegcompletDto().getCheckboxProperty();

						// in case of single property, set mapCheck as ON
						// explicitly

						int propCount = 0;

						propCount = commonBo.getPropertyCount(eForm.getHidnRegTxnId());

						if (propCount == 1) {

							mapCheck = "ON";
						}

						// Added by Rakesh to update CLR ADJ Details and Owner
						// details: Start

						String getPropTypeCheck = commonBo.getPropertyTypeID(eForm.getPropertyId());

						if (getPropTypeCheck.equalsIgnoreCase("3")) {

							/*
							 * boolean flag =
							 * commonBo.updatePropertyKhasraDetls1(eForm);
							 * if(flag){ boolean
							 * flag2=commonBo.insertClrPropOwnerDetails(eForm);
							 * }
							 */
						}

						// Added by Rakesh to update CLR ADJ Details and Owner
						// details: End

						request.setAttribute("mapCheck", mapCheck);
						request.setAttribute("deedId", eForm.getDeedID());
						forwardJsp = "reginitnonPropMapping";
						/*
						 * boolean mapInsert=false;
						 * 
						 * if(mapCheck!=null &&
						 * mapCheck.equalsIgnoreCase("ON")){
						 * 
						 * mapInsert=commonBo.getAllPartiesProperties(eForm);
						 * 
						 * 
						 * logger.debug("mapping insertion status : "+mapInsert);
						 * 
						 * 
						 * }
						 * 
						 * int
						 * count=commonBo.getPropertyCount(eForm.getHidnRegTxnId
						 * ()); int
						 * partyCount=commonBo.getPartyCount(eForm.getHidnRegTxnId
						 * ()); if ((count==1 && partyCount==1) ||(count==1 &&
						 * partyCount>1) || (partyCount==1 && count>1) ||
						 * mapCheck.equalsIgnoreCase("ON") && mapCheck!=null &&
						 * mapInsert){
						 * 
						 * //hashmap already containing mappings
						 * 
						 * eForm.setPropWithMapping(commonBo.
						 * getAllPartiesPropertiesAlreadyMap
						 * (eForm,languageLocale));
						 * eForm.setPropWithoutMapping(null);
						 * eForm.setCheckParty("check");
						 * forwardJsp="reginitPropMapping"; return
						 * mapping.findForward(forwardJsp);
						 * 
						 * }else{
						 * 
						 * eForm.setPropWithMapping(null);
						 * eForm.setPropWithoutMapping
						 * (commonBo.getAllPartiesPropertiesMap
						 * (eForm,languageLocale));
						 * forwardJsp="reginitPropMapping"; return
						 * mapping.findForward(forwardJsp);
						 * 
						 * }
						 */
					}

					if (RegInitConstant.MAPPING_FORM.equals(formName)) {

						if (RegInitConstant.VALIDATE_SHARES_ACTION.equals(actionName)) {

							commonBo.refreshHashMap(eForm.getPropWithMapping(), request, eForm);

							HashMap shareMap = eForm.getPropWithMapping();
							double shareParty1 = 0;
							double shareParty2 = 0;

							if (shareMap != null && shareMap.size() > 0) {

								Set set = shareMap.keySet();
								Iterator itr = set.iterator();
								ArrayList partyList;
								String key;
								CommonDTO dto1;
								int roleId;
								double share;
								int partyType1Or2;
								while (itr.hasNext()) {
									shareParty1 = 0;
									shareParty2 = 0;
									key = (String) itr.next();
									partyList = (ArrayList) shareMap.get(key);
									if (partyList != null && partyList.size() > 0) {

										for (int i = 0; i < partyList.size(); i++) {

											dto1 = (CommonDTO) partyList.get(i);

											roleId = Integer.parseInt(dto1.getRoleId());

											partyType1Or2 = commonBo.getPartyType1Or2(eForm.getDeedID(), eForm.getInstID(), roleId);

											if (dto1.getShareOfProp() != null && !dto1.getShareOfProp().equalsIgnoreCase("")) {
												share = Double.parseDouble(dto1.getShareOfProp());
											} else {
												share = 0;
											}

											if (partyType1Or2 == 1) {

												shareParty1 += share;

											} else {

												shareParty2 += share;

											}

										}

										if ((shareParty1 != 0 && shareParty2 != 0) && (shareParty1 != 100 || shareParty2 != 100)) {

											request.setAttribute("shareErrorMsg", "shares not properly defined.");
											forwardJsp = "alreadyMapped";
											return mapping.findForward(forwardJsp);

										}/*
										 * else{ forward="reginitConfirm";
										 * mapping.findForward(forward); }
										 */

									}

								}

								boolean shareUpdate = commonBo.updatePartyPropShares(shareMap, eForm);

								if (shareUpdate) {
									// commonBo.landConfirmationScreen(eForm,
									// languageLocale);
									request.setAttribute("deedId", eForm.getDeedID());
									request.setAttribute("instId", eForm.getInstID());
									forwardJsp = "reginitConfirm";
								} else {
									session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
									forwardJsp = "failure";
								}
								return mapping.findForward(forwardJsp);

								// insert shares here

							}

						}

					}

					/*
					 * if(request.getParameter("label")!=null){ String
					 * label=null; label=(String)request.getParameter("label");
					 * if(label!=null && label!="") {
					 * if(label.equalsIgnoreCase("displayProp")){
					 * request.setAttribute("deedId", regInitForm.getDeedID());
					 * eForm.setActionName(""); forwardJsp= "propdetails"; } } }
					 */

					if (RegInitConstant.ADD_MORE_PROP_ACTION.equals(actionName)) { // NEEDED
						request.setAttribute("instsId",regInitForm.getInstID());
						regInitForm.setTermsCondProp("");

						logger.debug("action name prop 1--->" + eForm.getActionName());

						eForm.setActionName(RegInitConstant.NO_ACTION);

						logger.debug("action name prop 2--->" + eForm.getActionName());

						ArrayList<CommonDTO> khasraDets = eForm.getRegcompletDto().getKhasraDetlsDisplay();
						if (khasraDets != null && khasraDets.size() > 0) {
							commonBo.refreshKhasraData(khasraDets, request);
						}
						eForm.getRegcompletDto().setListDtoP(eForm.getListDtoP());
						eForm.setListDtoP(new ArrayList<CommonDTO>());
						eForm.getRegcompletDto().setPropertyTypeName(commonBo.getPropertyTypeName(eForm.getRegcompletDto().getPropertyTypeId(), languageLocale));
						HashMap mapPropertyDisplay = eForm.getMapProperty();
						HashMap mapProperty = new HashMap();
						int hashSize = mapPropertyDisplay.size();
						int countProp = eForm.getAddMoreCounter();
						if (countProp == 0)
							countProp = 1;

						eForm.getRegcompletDto().setPropertyId(eForm.getRegcompletDto().getSelectedPropId());

						String getPropTypeCheck = commonBo.getPropertyTypeID(eForm.getRegcompletDto().getSelectedPropId());

						String valuationID = commonBo.getValTxnID(eForm.getRegcompletDto().getSelectedPropId());
						String clrF = commonBo.getClrByClrTable(valuationID);

						/*if (clrF != null && !clrF.isEmpty()) {

							if (getPropTypeCheck.equalsIgnoreCase("3") && clrF.equalsIgnoreCase("Y")) {

								boolean flag = commonBo.updatePropertyKhasraDetls1(eForm);
								if (flag) {
									//boolean flag2 = commonBo.insertClrPropOwnerDetails(eForm);
								}

							}
						}
*/
						if (mapProperty.containsKey(Integer.toString(countProp))) {

							countProp++;
							mapProperty.put(Integer.toString(countProp), eForm.getRegcompletDto());
							mapPropertyDisplay.put(Integer.toString(hashSize), eForm.getRegcompletDto());
						} else {
							mapProperty.put(Integer.toString(countProp), eForm.getRegcompletDto());
							mapPropertyDisplay.put(Integer.toString(hashSize), eForm.getRegcompletDto());
						}

						eForm.setMapProperty(mapPropertyDisplay);
						eForm.setAddMoreCounter(countProp);

						{

							Collection mapCollection = mapProperty.values();
							Object[] l1 = mapCollection.toArray();
							RegCompletDTO mapDto1 = new RegCompletDTO();

							boolean propertyDetailsInserted = false;

							for (int i = 0; i < l1.length; i++) {

								mapDto1 = (RegCompletDTO) l1[i];
								CommonAction action = new CommonAction();

								String returnJsp = commonBo.validateMPCST(mapDto1, eForm, request, mapDto1.getPropertyId());
								if (returnJsp.equalsIgnoreCase("error")) {
									forwardJsp = "propdetailsOld";
									return mapping.findForward(forwardJsp);
								}

								boolean check = true;
								ArrayList<CommonDTO> dto1 = mapDto1.getListDtoP();
								if (dto1 != null && dto1.size() > 0) {
									for (int j = 0; j < dto1.size(); j++) {
										CommonDTO dtos = dto1.get(j);
										dtos.setDocumentName(commonBo.getNewFileName(dtos.getDocumentName(), Integer.toString(j)));
										String checkUpload = action.uploadFile(eForm.getHidnRegTxnId(), dtos.getDocContents(), mapDto1.getPropertyId(), "02", dtos.getDocumentName());
										if (checkUpload == null) {
											check = false;
											break;
										} else {
											dtos.setDocumentPath(checkUpload);
											check = true;
										}

									}
									// Mohit
									if (check) {
										check = commonBo.insertAdditionalUploads(mapDto1, eForm);
									}
								} else {
									check = true;
								}

								if (check) {

									/*
									 * if(!eForm.getIGRS_DATA_AVLBL().equalsIgnoreCase
									 * ("Y")) {
									 */
									String filePathAuditReport = ""; // added by
																		// ankit
																		// for
																		// plant
																		// and
																		// machinery
									String filePathAngle1 = "";
									String filePathAngle2 = "";
									String filePathAngle3 = "";
									String filePathMap = "";
									String filePathRin = "";
									String filePathKhasra = "";
									// boolean propertyDetailsInserted=false;
									String propTypeId = eForm.getPropertyTypeId();
									String municipalId = mapDto1.getGovmunciplId();
									String areaTypeId = mapDto1.getAreaTypeId();

									if (eForm.getInstID() != 2012 && eForm.getInstID() != 2016 && (propTypeId.equals("1") || propTypeId.equals("2") || (propTypeId.equals("3") && (areaTypeId.equals("2") || municipalId.equals("5"))))) {
										// added by ankit for plant and
										// machinery
										if (mapDto1.getAuditReportDocumentName() != null && !mapDto1.getAuditReportDocumentName().equalsIgnoreCase("")) {

											filePathAuditReport = action.uploadFile(eForm.getHidnRegTxnId(), mapDto1.getAuditReportDocContents(), eForm.getPropertyId(), mapDto1.getAuditReportPartyOrProp(), mapDto1.getAuditReportUploadType());
										}
										eForm.getRegcompletDto().setAuditReportFilePath(filePathAuditReport);
										// added by ankit for plant and
										// machinery--end -->>

										if (mapDto1.getPropImage1DocumentName() != null && !mapDto1.getPropImage1DocumentName().equalsIgnoreCase("")) {
											filePathAngle1 = action.uploadFile(eForm.getHidnRegTxnId(), mapDto1.getPropImage1DocContents(), mapDto1.getPropertyId(), mapDto1.getPropImage1PartyOrProp(), mapDto1.getPropImage1UploadType());
										} else {
											filePathAngle1 = "";
										}
										if (filePathAngle1 != null) {
											mapDto1.setPropImage1FilePath(filePathAngle1);
											/*
											 * filePathAngle2=
											 * action.uploadFile(
											 * eForm.getHidnRegTxnId
											 * (),mapDto1.getPropImage2DocContents
											 * (),
											 * mapDto1.getPropertyId(),mapDto1
											 * .getPropImage2PartyOrProp
											 * (),mapDto1
											 * .getPropImage2UploadType());
											 * if(filePathAngle2!=null &&
											 * !filePathAngle2
											 * .equalsIgnoreCase("")){
											 * mapDto1.setPropImage2FilePath
											 * (filePathAngle2); filePathAngle3=
											 * action
											 * .uploadFile(eForm.getHidnRegTxnId
											 * (
											 * ),mapDto1.getPropImage3DocContents
											 * (),
											 * mapDto1.getPropertyId(),mapDto1
											 * .getPropImage3PartyOrProp
											 * (),mapDto1
											 * .getPropImage3UploadType());
											 * if(filePathAngle3!=null &&
											 * !filePathAngle3
											 * .equalsIgnoreCase("")){
											 * mapDto1.setPropImage3FilePath
											 * (filePathAngle3);
											 */

											if (mapDto1.getPropMapDocumentName() != null && !mapDto1.getPropMapDocumentName().equalsIgnoreCase("")) {
												filePathMap = action.uploadFile(eForm.getHidnRegTxnId(), mapDto1.getPropMapDocContents(), mapDto1.getPropertyId(), mapDto1.getPropMapPartyOrProp(), mapDto1.getPropMapUploadType());
											} else {
												filePathMap = "";
											}

											if (eForm.getPropertyTypeId().equalsIgnoreCase("3")) {
												mapDto1.setPropMapFilePath(filePathMap);
												filePathRin = action.uploadFile(eForm.getHidnRegTxnId(), mapDto1.getPropRinDocContents(), mapDto1.getPropertyId(), mapDto1.getPropRinPartyOrProp(), mapDto1.getPropRinUploadType());
												if (filePathRin != null && !filePathRin.equalsIgnoreCase("")) {
													mapDto1.setPropRinFilePath(filePathRin);
													filePathKhasra = action.uploadFile(eForm.getHidnRegTxnId(), mapDto1.getPropKhasraDocContents(), mapDto1.getPropertyId(), mapDto1.getPropKhasraPartyOrProp(), mapDto1.getPropKhasraUploadType());
													if (filePathKhasra != null && !filePathKhasra.equalsIgnoreCase("")) {
														mapDto1.setPropKhasraFilePath(filePathKhasra);
														// propertyDetailsInserted
														// =
														// commonBo.insertPropertyDetailsNonPropDeeds(regInitForm,mapDto1,RegInitConstant.STATUS_MULTI_PROP_IN_PROGRESS);
														// commonBo.insertPropertyDetailsNonPropDeeds(regInitForm,mapDto1,RegInitConstant.STATUS_MULTI_PROP_PV_RECVD,eForm,null);

														if (clrF != null && !clrF.isEmpty()) {
															if (getPropTypeCheck.equalsIgnoreCase("3") && clrF.equalsIgnoreCase("y")) {

																propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeedsCLR(regInitForm, mapDto1, RegInitConstant.STATUS_MULTI_PROP_PV_RECVD, eForm, null);

															} else {

																propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeeds(regInitForm, mapDto1, RegInitConstant.STATUS_MULTI_PROP_PV_RECVD, eForm, null);

															}
														} else {

															propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeeds(regInitForm, mapDto1, RegInitConstant.STATUS_MULTI_PROP_PV_RECVD, eForm, null);
														}

													} else {
														break;
													}

												} else {
													break;
												}

											} else if (filePathMap != null) {

												mapDto1.setPropMapFilePath(filePathMap);
												// propertyDetailsInserted =
												// commonBo.insertPropertyDetailsNonPropDeeds(regInitForm,mapDto1,RegInitConstant.STATUS_MULTI_PROP_IN_PROGRESS);
												// commonBo.insertPropertyDetailsNonPropDeeds(regInitForm,mapDto1,RegInitConstant.STATUS_MULTI_PROP_PV_RECVD,eForm,null);

												if (clrF != null && !clrF.isEmpty()) {
													if (getPropTypeCheck.equalsIgnoreCase("3") && clrF.equalsIgnoreCase("y")) {

														propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeedsCLR(regInitForm, mapDto1, RegInitConstant.STATUS_MULTI_PROP_PV_RECVD, eForm, null);

													} else {

														propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeeds(regInitForm, mapDto1, RegInitConstant.STATUS_MULTI_PROP_PV_RECVD, eForm, null);

													}
												} else {

													propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeeds(regInitForm, mapDto1, RegInitConstant.STATUS_MULTI_PROP_PV_RECVD, eForm, null);
												}

											} else {
												break;
											}

											/*
											 * }else{ break; }
											 * 
											 * 
											 * }else{ break; }
											 */

										} else {
											break;
										}

									} else {

										// added by ankit for plant and
										// machinery
										if (mapDto1.getAuditReportDocumentName() != null && !mapDto1.getAuditReportDocumentName().equalsIgnoreCase("")) {

											filePathAuditReport = action.uploadFile(eForm.getHidnRegTxnId(), mapDto1.getAuditReportDocContents(), eForm.getPropertyId(), mapDto1.getAuditReportPartyOrProp(), mapDto1.getAuditReportUploadType());
										}
										mapDto1.setAuditReportFilePath(filePathAuditReport);
										// added by ankit for plant and
										// machinery--end -->>

										if (mapDto1.getPropImage1DocumentName() != null && !mapDto1.getPropImage1DocumentName().equalsIgnoreCase("")) {
											filePathAngle1 = action.uploadFile(eForm.getHidnRegTxnId(), mapDto1.getPropImage1DocContents(), mapDto1.getPropertyId(), mapDto1.getPropImage1PartyOrProp(), mapDto1.getPropImage1UploadType());

											mapDto1.setPropImage1FilePath(filePathAngle1);
										} else {
											mapDto1.setPropImage1FilePath("");
										}

										if (mapDto1.getPropMapDocumentName() != null && !mapDto1.getPropMapDocumentName().equalsIgnoreCase("")) {
											filePathMap = action.uploadFile(eForm.getHidnRegTxnId(), mapDto1.getPropMapDocContents(), mapDto1.getPropertyId(), mapDto1.getPropMapPartyOrProp(), mapDto1.getPropMapUploadType());

											mapDto1.setPropMapFilePath(filePathMap);
										} else {
											mapDto1.setPropMapFilePath("");
										}

										if (eForm.getPropertyTypeId().equalsIgnoreCase("3")) {

											filePathRin = action.uploadFile(eForm.getHidnRegTxnId(), mapDto1.getPropRinDocContents(), mapDto1.getPropertyId(), mapDto1.getPropRinPartyOrProp(), mapDto1.getPropRinUploadType());
											if (filePathRin != null && !filePathRin.equalsIgnoreCase("")) {
												mapDto1.setPropRinFilePath(filePathRin);
												filePathKhasra = action.uploadFile(eForm.getHidnRegTxnId(), mapDto1.getPropKhasraDocContents(), mapDto1.getPropertyId(), mapDto1.getPropKhasraPartyOrProp(), mapDto1.getPropKhasraUploadType());
												if (filePathKhasra != null && !filePathKhasra.equalsIgnoreCase("")) {
													mapDto1.setPropKhasraFilePath(filePathKhasra);
													// propertyDetailsInserted =
													// commonBo.insertPropertyDetailsNonPropDeeds(regInitForm,mapDto1,RegInitConstant.STATUS_MULTI_PROP_PV_RECVD,eForm,null);
													if (clrF != null && !clrF.isEmpty()) {
														if (getPropTypeCheck.equalsIgnoreCase("3") && clrF.equalsIgnoreCase("y")) {

															propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeedsCLR(regInitForm, mapDto1, RegInitConstant.STATUS_MULTI_PROP_PV_RECVD, eForm, null);

														} else {

															propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeeds(regInitForm, mapDto1, RegInitConstant.STATUS_MULTI_PROP_PV_RECVD, eForm, null);

														}
													} else {

														propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeeds(regInitForm, mapDto1, RegInitConstant.STATUS_MULTI_PROP_PV_RECVD, eForm, null);
													}

												} else {
													break;
												}

											} else {
												break;
											}

										} else {

											mapDto1.setPropRinFilePath("");
											mapDto1.setPropKhasraFilePath("");
											// propertyDetailsInserted =
											// commonBo.insertPropertyDetailsNonPropDeeds(regInitForm,mapDto1,RegInitConstant.STATUS_MULTI_PROP_PV_RECVD,eForm,null);

											if (clrF != null && !clrF.isEmpty()) {
												if (getPropTypeCheck.equalsIgnoreCase("3") && clrF.equalsIgnoreCase("y")) {

													propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeedsCLR(regInitForm, mapDto1, RegInitConstant.STATUS_MULTI_PROP_PV_RECVD, eForm, null);

												} else {

													propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeeds(regInitForm, mapDto1, RegInitConstant.STATUS_MULTI_PROP_PV_RECVD, eForm, null);

												}
											} else {

												propertyDetailsInserted = commonBo.insertPropertyDetailsNonPropDeeds(regInitForm, mapDto1, RegInitConstant.STATUS_MULTI_PROP_PV_RECVD, eForm, null);
											}

										}

									}

									/*
									 * }else{
									 * 
									 * //mpcst insertion
									 * 
									 * }
									 */

								}
								logger.debug("property details inserted :- " + propertyDetailsInserted);

								/*
								 * propertyDetailsInserted =
								 * commonBo.insertPropertyDetailsNonPropDeeds
								 * (regInitForm,mapDto1,RegInitConstant.
								 * STATUS_MULTI_PROP_IN_PROGRESS);
								 * logger.debug("property details inserted :- "
								 * +propertyDetailsInserted);
								 */

							}

							if (propertyDetailsInserted) {
								eForm.setActionName("");
								eForm.getRegcompletDto().setPropTypeL2Flag(0);
								eForm.getRegcompletDto().setPropTypeL2Flag(0);
								eForm.setRegcompletDto(new RegCompletDTO());
								eForm.setListDtoP(new ArrayList());
								eForm.getRegcompletDto().getKhasraDetlsDisplay().add(new CommonDTO());
								// eForm.setMapProperty(new HashMap());
								request.setAttribute("multiProp", "Y");
								request.setAttribute("instId", regInitForm.getInstID());
								request.setAttribute("instsId",regInitForm.getInstID());
								forwardJsp = "propdetails";
							} else {
								eForm.setHidnRegTxnId(null);
								eForm.setActionName("");
								eForm.getRegcompletDto().setPropTypeL2Flag(0);
								eForm.setMapProperty(new HashMap());
								session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
								forwardJsp = "failure";

							}

						}/*
						 * else{
						 * 
						 * eForm.setRegcompletDto(new RegCompletDTO());
						 * eForm.getRegcompletDto
						 * ().getKhasraDetlsDisplay().add(new CommonDTO());
						 * eForm
						 * .setDistList(commonBo.getDistrict("1",languageLocale
						 * ));
						 * eForm.setAreaTypeList(newPropBO.getArea(languageLocale
						 * ));
						 * //eForm.setMunicipalList(newPropBO.getMunicipalBody
						 * (languageLocale));
						 * eForm.setPropertyType(newPropBO.getPropertyType
						 * (languageLocale));
						 * eForm.getRegcompletDto().setPropTypeL2Flag(0);
						 * 
						 * forwardJsp = "propdetails";
						 * 
						 * }
						 */

						/*
						 * if(regInitForm.getDeedID()==RegInitConstant.DEED_LEASE_NPV
						 * ){ forwardJsp = "multipleProps"; }else{
						 * 
						 * }
						 */

						eForm.setForwardJsp(forwardJsp);

					}

					if (RegInitConstant.RESET_PROPERTY_ACTION // NEEDED
					.equals(actionName)) {

						PropertyValuationDTO dto1 = new PropertyValuationDTO();
						RegCompletDTO comDto = new RegCompletDTO();
						comDto.getKhasraDetlsDisplay().add(new CommonDTO());
						eForm.setRegcompletDto(comDto);
						eForm.setPropertyDTO(dto1);

						forwardJsp = "propdetailsOld";
					}
					if (RegInitConstant.CONFIRMATION_OK_ACTION.equals(actionName)) {

						eForm.setActionName(RegInitConstant.NO_ACTION);
						regInitForm.setActionName(RegInitConstant.NO_ACTION);
						request.setAttribute("deedId", regInitForm.getDeedID());
						request.setAttribute("instId", regInitForm.getInstID());
						request.setAttribute("key", eForm.getPropertyId());
						forwardJsp = "reginitConfirmModify";

					}
					if (RegInitConstant.SAVE_PROP_ACTION.equals(actionName)) {

						String getPropTypeCheck = commonBo.getPropertyTypeID(eForm.getPropertyId());

						String valuationID = commonBo.getValTxnID(eForm.getPropertyId());
						String clrF = "N";

						clrF = commonBo.getClrByClrTable(valuationID);

						if (clrF.equalsIgnoreCase("Y") && getPropTypeCheck.equalsIgnoreCase("3")) {
							eForm.setClrFlag(clrF);
						}

						/*
						 * if(eForm.getKhasraNoArray() != null &&
						 * eForm.getKhasraAreaArray()!=null &&
						 * eForm.getLagaanArray()!=null &&
						 * eForm.getRinPustikaArray()!=null &&
						 * !eForm.getKhasraNoArray().equalsIgnoreCase("null") &&
						 * !eForm.getKhasraAreaArray().equalsIgnoreCase("null")
						 * && !eForm.getLagaanArray().equalsIgnoreCase("null")
						 * &&
						 * !eForm.getRinPustikaArray().equalsIgnoreCase("null")
						 * && !eForm.getKhasraNoArray().equalsIgnoreCase("") &&
						 * !eForm.getKhasraAreaArray().equalsIgnoreCase("") &&
						 * !eForm.getLagaanArray().equalsIgnoreCase("") &&
						 * !eForm.getRinPustikaArray().equalsIgnoreCase("")) {
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
						 * 
						 * 
						 * }
						 */

						ArrayList<CommonDTO> khasraDets = eForm.getRegcompletDto().getKhasraDetlsDisplay();
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
						if (khasraDets != null && khasraDets.size() > 0) {
							//commonBo.refreshKhasraData(khasraDets, request);
						}
						CommonAction action = new CommonAction();
						String path = "";

						try {
							// PropertiesFileReader pr =
							// PropertiesFileReader.getInstance("resources.igrs");

							path = pr.getValue("upload.location");
						} catch (Exception e) {
							logger.debug("exception in uploadFile : " + e);
							logger.debug(e.getMessage(), e);
						}
						boolean checkAdditionalUploads = true;
						path = path + RegInitConstant.FILE_UPLOAD_PATH + eForm.getHidnRegTxnId() + RegInitConstant.FILE_UPLOAD_PATH_PROP + eForm.getPropertyId() + "/";
						// Added by rakesh1: Start
						String getPath = pr.getValue("upload.location");
						getPath = getPath + RegInitConstant.FILE_UPLOAD_PATH + eForm.getHidnRegTxnId() + RegInitConstant.FILE_UPLOAD_PATH_PROP + eForm.getPropertyId() + "/";
						String getKhasraPath = getPath + pr.getValue("clr_khasra_pdf");
						String getMapPath = getPath + pr.getValue("clr_map_pdf");
						byte[] khasraPathContent = DMSUtility.getDocumentBytes(getKhasraPath);
						byte[] mapPathContent = DMSUtility.getDocumentBytes(getMapPath);
						eForm.getRegcompletDto().setKhasraPdfContents(khasraPathContent);
						eForm.getRegcompletDto().setMapPdfContents(mapPathContent);
						// Added by rakesh1: End

						try {

							// By Mohit
							// File f = new File(path);
							// FileUtils.cleanDirectory(f);
							// commonBo.deleteAllRemovedUploadsP(eForm.getHidnRegTxnId(),eForm.getPropertyId());

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
						 * eForm.getRegcompletDto(
						 * ).setMapPathClr(filePathMapClr);} String
						 * filePathKhasraClr=
						 * action.uploadFile(eForm.getHidnRegTxnId
						 * (),eForm.getRegcompletDto().getKhasraPdfContents(),
						 * eForm
						 * .getPropertyId(),"KhasraCLR",pr.getValue("clr_khasra_pdf"
						 * )); if(filePathKhasraClr !=null){
						 * eForm.getRegcompletDto
						 * ().setKhasraPathClr(filePathKhasraClr);}
						 */
						// rakesh2: End

						// below write code for saving modified party details
						if (checkAdditionalUploads) {
							ArrayList<CommonDTO> listDto = eForm.getListDtoP();
							if (listDto != null && listDto.size() > 0) {

								// checkAdditionalUploads =
								// commonBo.deleteAllRemovedUploadsP(eForm.getHidnRegTxnId(),eForm.getPropertyId());
								if (checkAdditionalUploads) {
									for (int i = 0; i < listDto.size(); i++) {
										CommonDTO dtop = listDto.get(i);
										dtop.setDocumentName(commonBo.getNewFileName(dtop.getDocumentName(), Integer.toString(i)));
										String filepath = action.uploadFile(eForm.getHidnRegTxnId(), dtop.getDocContents(), eForm.getPropertyId(), "02", dtop.getDocumentName());
										if (filepath != null) {
											checkAdditionalUploads = true;
											dtop.setDocumentPath(filepath);
										} else {
											checkAdditionalUploads = false;
											break;
										}
									}

								}
								if (checkAdditionalUploads) {

									checkAdditionalUploads = commonBo.insertAdditionalUploads(eForm);

								}

							} else {
								checkAdditionalUploads = true;
							}
						}

						boolean propertyDetailsInserted = false;
						if (checkAdditionalUploads) {
							/*
							 * if(!eForm.getIGRS_DATA_AVLBL().equalsIgnoreCase("Y"
							 * )) {
							 */

							String filePathAuditReport = ""; // added by ankit
																// for plant and
																// machinery
							String filePathAngle1 = "";
							String filePathAngle2 = "";
							String filePathAngle3 = "";
							String filePathMap = "";
							String filePathRin = "";
							String filePathKhasra = "";

							String propTypeId = eForm.getPropertyTypeId();
							String municipalId = eForm.getRegcompletDto().getGovmunciplId();
							String areaTypeId = eForm.getRegcompletDto().getAreaTypeId();

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
								// added by ankit for plant and machinery--end
								// -->>

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
									 * (),eForm.getRegcompletDto().
									 * getPropImage2DocContents(),
									 * eForm.getPropertyId
									 * (),eForm.getRegcompletDto
									 * ().getPropImage2PartyOrProp
									 * (),eForm.getRegcompletDto
									 * ().getPropImage2UploadType());
									 * if(filePathAngle2!=null &&
									 * !filePathAngle2.equalsIgnoreCase("")){
									 * eForm
									 * .getRegcompletDto().setPropImage2FilePath
									 * (filePathAngle2); filePathAngle3=
									 * action.uploadFile
									 * (eForm.getHidnRegTxnId(),
									 * eForm.getRegcompletDto
									 * ().getPropImage3DocContents(),
									 * eForm.getPropertyId
									 * (),eForm.getRegcompletDto
									 * ().getPropImage3PartyOrProp
									 * (),eForm.getRegcompletDto
									 * ().getPropImage3UploadType());
									 * if(filePathAngle3!=null &&
									 * !filePathAngle3.equalsIgnoreCase("")){
									 * eForm
									 * .getRegcompletDto().setPropImage3FilePath
									 * (filePathAngle3);
									 */
									if (eForm.getRegcompletDto().getPropMapDocumentName() != null && !eForm.getRegcompletDto().getPropMapDocumentName().equalsIgnoreCase("")) {
										filePathMap = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getPropMapDocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getPropMapPartyOrProp(), eForm.getRegcompletDto().getPropMapUploadType());
									} else {
										filePathMap = "";
									}
									if (eForm.getPropertyTypeId().equalsIgnoreCase(RegInitConstant.PROPERTY_TYPE)) {
										eForm.getRegcompletDto().setPropMapFilePath(filePathMap);
										filePathRin = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getPropRinDocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getPropRinPartyOrProp(), eForm.getRegcompletDto().getPropRinUploadType());
										if (filePathRin != null && !filePathRin.equalsIgnoreCase("")) {
											eForm.getRegcompletDto().setPropRinFilePath(filePathRin);
											filePathKhasra = action.uploadFile(eForm.getHidnRegTxnId(), eForm.getRegcompletDto().getPropKhasraDocContents(), eForm.getPropertyId(), eForm.getRegcompletDto().getPropKhasraPartyOrProp(), eForm.getRegcompletDto().getPropKhasraUploadType());
											if (filePathKhasra != null && !filePathKhasra.equalsIgnoreCase("")) {
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

										// propertyDetailsInserted =
										// commonBo.insertPropertyDetails(regInitForm,eForm);
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
								// added by ankit for plant and machinery--end
								// -->>

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
								 * .getHidnRegTxnId(),eForm
								 * .getRegcompletDto().getPropImage2DocContents
								 * (),
								 * eForm.getPropertyId(),eForm.getRegcompletDto
								 * ().getPropImage2PartyOrProp(),eForm.
								 * getRegcompletDto
								 * ().getPropImage2UploadType());
								 * 
								 * 
								 * eForm.getRegcompletDto().setPropImage2FilePath
								 * (filePathAngle2); }else{
								 * eForm.getRegcompletDto
								 * ().setPropImage2FilePath(""); }
								 * 
								 * 
								 * if(eForm.getRegcompletDto().
								 * getPropImage3DocContents()!=null){
								 * filePathAngle3=
								 * action.uploadFile(eForm.getHidnRegTxnId
								 * (),eForm
								 * .getRegcompletDto().getPropImage3DocContents
								 * (),
								 * eForm.getPropertyId(),eForm.getRegcompletDto
								 * ().getPropImage3PartyOrProp(),eForm.
								 * getRegcompletDto
								 * ().getPropImage3UploadType());
								 * 
								 * 
								 * eForm.getRegcompletDto().setPropImage3FilePath
								 * (filePathAngle3); }else{
								 * eForm.getRegcompletDto
								 * ().setPropImage3FilePath(""); }
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
											// propertyDetailsInserted =
											// commonBo.insertPropertyDetails(regInitForm,eForm);

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

								} else {

									eForm.getRegcompletDto().setPropRinFilePath("");
									eForm.getRegcompletDto().setPropKhasraFilePath("");
									// propertyDetailsInserted =
									// commonBo.insertPropertyDetails(regInitForm,eForm);
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
							/*
							 * } else{
							 * 
							 * 
							 * //mpcst insertion
							 * 
							 * 
							 * }
							 */

						}

						logger.debug("property details inserted :- " + propertyDetailsInserted);

						/*
						 * if(regInitForm.getDeedID()==RegInitConstant.DEED_EXCHANGE
						 * &&
						 * !RegInitConstant.SAVE_PROP_ACTION.equals(actionName
						 * )){ regInitForm.setPropertyId("");
						 * eForm.setPropertyId(""); }
						 */

						if (propertyDetailsInserted) {

							eForm.setActionName(RegInitConstant.NO_ACTION);
							regInitForm.setActionName(RegInitConstant.NO_ACTION);
							request.setAttribute("deedId", regInitForm.getDeedID());
							request.setAttribute("instId", regInitForm.getInstID());
							request.setAttribute("key", eForm.getPropertyId());
							forwardJsp = "reginitConfirmModify";
							// forwardJsp ="reginitConfirmModify";

							// forwardJsp ="reginitConfirm";
						}

						else {
							eForm.setHidnRegTxnId(null);

							eForm.setActionName(RegInitConstant.NO_ACTION);
							regInitForm.setActionName(RegInitConstant.NO_ACTION);
							if (regInitForm.getClrFlg() != null) {
								if (regInitForm.getClrFlg().equalsIgnoreCase("Y")) {

									forwardJsp = "editPropDetailsCLR";
								} else {
									forwardJsp = "editPropDetails";

								}
								// forwardJsp= "editPropDetails";

							}

						}

						eForm.setActionName("");

					}

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
								forwardJsp = "propdetailsOld";
							} else if (request.getParameter("retrievalType").toString().equalsIgnoreCase("2")) {
								forwardJsp = "editPropDetails";
							}
						}
					}
				}

				if (RegInitConstant.NO_ACTION // NEEDED
				.equals(actionName)) {
					forwardJsp = eForm.getForwardJsp();
				}

			}

		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
			forwardJsp = "failure";
		}
		return mapping.findForward(forwardJsp);
	}

	private void resetValue(PropertyValuationDTO dto, RegCompletDTO rdto) {
		rdto.setAddress("");
		rdto.setAdjustAppNo("");
		rdto.setAreaList(new ArrayList());
		rdto.setAreaTypeId("");

		rdto.setCeilinghidType("");
		rdto.setCeilingTypeId("");
		rdto.setConsiderAmt(0);
		rdto.setConstructedArea("");
		rdto.setDistId("");
		rdto.setEast("");
		rdto.setNorth("");
		rdto.setWardStatus("");
		rdto.setWardId("");
		rdto.setMohallaId("");
		rdto.setWardList(new ArrayList());
		rdto.setMohallList(new ArrayList());
		rdto.setRicircle("");
		rdto.setLayoutdet("");
		rdto.setRinpustikaNum("");
		rdto.setPropertyTypeId("");
		rdto.setGovmunciplId("");
		rdto.setHdnExtSubClause("");
		rdto.setKhasaraNum("");
		rdto.setMapBuilding(new HashMap());
		rdto.setWest("");
		rdto.setWardpatwarName("");
		rdto.setWardList(new ArrayList());
		rdto.setWardpatwariList(new ArrayList());
		rdto.setVikasId("");
		rdto.setUsePlotId("");
		rdto.setUsageBuilding("");
		rdto.setMarketValue("");
		rdto.setNazoolstNum("");
		rdto.setTehsilId("");
		rdto.setMohallaId("");
		rdto.setPoaNo("");
		rdto.setSouth("");
		rdto.setTehsilList(new ArrayList());
		rdto.setMohallList(new ArrayList());

		dto.setConsiderAmt(0);
		dto.setConstructedArea(0);
		dto.setTotalSqMeter(0);
		dto.setCeilingTypeId("");
		dto.setHdnExtSubClause("");
		dto.setTehsilID("");
		dto.setSubClause("");

	}

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
