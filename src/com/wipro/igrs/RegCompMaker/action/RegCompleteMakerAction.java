package com.wipro.igrs.RegCompMaker.action;

/**
 * ===========================================================================
 * File           :   RegCompleteMakerAction.java
 * Description    :   Represents the Common Action Class
 * @author        :   Ankita
 * Created Date   :   January 15, 2013
 * Updated Date		Version			UpdatedBy
 *  
 * ===========================================================================
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.ODServerDetails;
import com.newgen.burning.ReadPropertiesFile;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.RegCompMaker.bd.RegCompMkrBD;
import com.wipro.igrs.RegCompMaker.bo.RegCompMkrBO;
import com.wipro.igrs.RegCompMaker.constants.RegCompConstant;
import com.wipro.igrs.RegCompMaker.dto.CommonMkrDTO;
import com.wipro.igrs.RegCompMaker.dto.OthrDeedDtlsDTO;
import com.wipro.igrs.RegCompMaker.dto.RegCompleteDutiesDTO;
import com.wipro.igrs.RegCompMaker.dto.RegCompleteMakerDTO;
import com.wipro.igrs.RegCompMaker.form.RegCompleteMakerForm;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.baseaction.constant.Constants;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.device.applet.EtokenChange;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.poa.bo.POAAuthenticationFormBO;
import com.wipro.igrs.poa.form.POAAuthenticationForm;
import com.wipro.igrs.regCompChecker.bd.RegCompCheckerBD;
import com.wipro.igrs.regCompChecker.constant.RegCompCheckerConstant;
import com.wipro.igrs.regCompChecker.dto.RegCompCheckerDTO;

public class RegCompleteMakerAction extends BaseAction {
	private Logger logger = Logger.getLogger(RegCompleteMakerAction.class);

	/**
	 * This is the main action called from the Struts framework.
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance.
	 * @param form
	 *            The optional ActionForm bean for this request.
	 * @param request
	 *            The HTTP Request we are processing.
	 * @param response
	 *            The HTTP Response we are processing.
	 */
	String forwardJsp = "";

	ArrayList retainList = new ArrayList();
	static private String key = "key";

	private void checkValidFile() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wipro.igrs.baseaction.action.BaseAction#execute(org.apache.struts
	 * .action.ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wipro.igrs.baseaction.action.BaseAction#execute(org.apache.struts
	 * .action.ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession)
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		Map mymap = new HashMap();
		Map dtlsMap = new HashMap();
		Map poaList = new HashMap();
		Map deathCertList = new HashMap();
		RegCompMkrBO regmkrBO = new RegCompMkrBO();
		RegCompMkrBD regmkrBD = new RegCompMkrBD();
		RegCommonBO regCommonBO = new RegCommonBO();
		PropertiesFileReader proper;
		String FILE_UPLOAD_PATH = "";
		String userId = (String) session.getAttribute("UserId");
		try {
			proper = PropertiesFileReader.getInstance("resources.igrs");
			FILE_UPLOAD_PATH = proper.getValue("igrs_upload_path") + File.separator + "02" + File.separator;
		} catch (Exception e1) {
			logger.debug(e1.getStackTrace());
		}

		// added by simran
		String language = (String) session.getAttribute("languageLocale");

		logger.debug("Language:  " + language);
		RegCompCheckerBD bd = new RegCompCheckerBD();
		//
		ActionMessages messages = new ActionMessages();
		RegCompleteMakerForm eForm = (RegCompleteMakerForm) form;
		if (eForm != null) {
			mymap = eForm.getMyMap();
			dtlsMap = eForm.getDtlsMap();

		}
		
		try {
			String UserId = (String) session.getAttribute("UserId");
			String officeId = (String) session.getAttribute("loggedToOffice");
			// Date createdDate = regmkrBD.getsysDate();
			Date createdDate = new Date();
			SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
			String cdate = sdfrmt.format(createdDate);

			ArrayList regCompDeedList;
			ArrayList regPropList;
			Map witnessPhotoUploadMap = new HashMap();
			HashMap propertyRelatedComplianceMap = new HashMap();
			HashMap partyRelatedComplianceMap = new HashMap();
			String param = request.getParameter("param");

			logger.debug("param:-" + param);

			// $1 Start:---- landing on first page

			if ("regSearch".equals(param)) {

				// Start :===Code for checking for hold

				// End :===Code for checking for hold
				RegCompleteMakerDTO dto = new RegCompleteMakerDTO();
				eForm.setFormName("");
				eForm.setActionName("");
				eForm.setRegDTO(dto);
				eForm.setCheckRegNo("");
				eForm.setMyMap(new HashMap());
				eForm.setDtlsMap(new HashMap());
				eForm.setCheck("");
				eForm.setHldBtnFlg("");
				eForm.setChkHoldClick("");
				eForm.setCountry("");
				eForm.setState("");
				eForm.setDistrict("");
				// for blanking out checklist
				eForm.setExeDate("");
				eForm.setExeOutIndDate("");
				eForm.setLstAppealDate("");
				eForm.setOrdrDate("");
				eForm.setDcreeOrderDate("");
				eForm.setOutIndCheck("");
				eForm.setCrtDcreeCheck("");
				eForm.setCrtDcreeAppealChk("");
				eForm.setDocAftrDeathChk("");
				eForm.setRegByPoa("");
				eForm.setTypeOfPoa("");
				eForm.setAddress("");
				// for blanking out checklist
				eForm.setCaveatBankDetailList(new ArrayList());
				eForm.setCaveatDetailList(new ArrayList());
				eForm.setBnkCaveatList(new ArrayList());
				eForm.setCaveatList(new ArrayList());
				eForm.setPoaList(new HashMap());
				eForm.setUploadDthList(new HashMap());
				eForm.setOldPropIdList(new ArrayList());
				eForm.setListSize(0);
				eForm.setRelationshipList(new ArrayList());
				eForm.setAddMoreCounter(0);
				eForm.setCommonDTO(new CommonMkrDTO());
				eForm.setDtlsMap(new HashMap());
				eForm.setDtlsMapConsntr(new HashMap());
				eForm.setAddress("");
				eForm.setFirstName("");
				eForm.setMiddleName("");
				eForm.setLastName("");
				eForm.setAge("");
				eForm.setFatherName("");
				eForm.setMotherName("");
				eForm.setSpouseName("");
				eForm.setNationality("");
				eForm.setGender("");
				eForm.setFatherName("");
				dto.setRelationshipWit("");
				eForm.setPhoneNumber("");
				eForm.setDeathCertChk("");
				eForm.setPoaChk("");
				//eForm.setRegTxnId("");
				forwardJsp = "page1";

			}
			if ("linkPOA".equalsIgnoreCase(request.getParameter("actionName5"))) {
				logger.debug("link POA");
				RegCompleteMakerDTO dto = eForm.getRegDTO();
				dto.setPoaAuthNum(request.getParameter("txt"));
				boolean isPOA = regmkrBD.checkPOA(dto.getPoaAuthNum(), dto.getRegNumber());
				if (isPOA) {

					POAAuthenticationFormBO poaBO = new POAAuthenticationFormBO();
					POAAuthenticationForm poaForm = new POAAuthenticationForm();
					String poaId = dto.getPoaAuthNum();

					ArrayList poaApplicantDetls = poaBO.getPOAApplicantDetlsForView(poaId);
					poaForm.setPoaApplicntDetlsList(poaApplicantDetls);
					ArrayList poaAwardeeDetls = poaBO.getPOAAwardeeDetlsForView(poaId);
					poaForm.setPoaAwardeeDetlsList(poaAwardeeDetls);
					ArrayList poaAcceptorDetls = poaBO.getPOAAcceptorDetlsForView(poaId);
					poaForm.setPoaAcceptorList(poaAcceptorDetls);
					ArrayList poaCommonDetls = poaBO.getPOACommonDetlsForView(poaId);
					poaForm.setPoaCommonDetlsList(poaCommonDetls);
					poaForm.setActionType("");
					request.setAttribute("poaForm", poaForm);
					forwardJsp = "poaListView2";
					return mapping.findForward(forwardJsp);
				} else {

					POAAuthenticationForm poaForm = new POAAuthenticationForm();
					poaForm.setActionType("error");
					request.setAttribute("poaForm", poaForm);
					forwardJsp = "poaListView2";
					dto.setPoaAuthNum("");
					return mapping.findForward(forwardJsp);
					/*
					 * logger.debug("in else----link POA"); messages.add("MSG",
					 * new ActionMessage( "link_poa_error"));
					 * saveMessages(request, messages); eForm.setErrLnkFlg("E");
					 * forwardJsp = "checklist";
					 */
				}

			}
			if ("printToPdf".equals(param)) {
				RegCommonForm regcommonForm = new RegCommonForm();

				regCommonBO.setRegCommonFormForPrint(regcommonForm, eForm.getRegDTO().getRegNumber(), language);
				regCommonBO.printToPdfJasper(regcommonForm, request, response, language, "Y");

			}

			// $1 End:---- landing on first page

			/*
			 * if ("success".equals(param)) { RegCompleteMakerDTO dto1 = new
			 * RegCompleteMakerDTO(); eForm.setFormName("");
			 * eForm.setActionName(""); eForm.setRegDTO(dto1); ArrayList
			 * complList = regmkrBD.getComplList();
			 * eForm.setComplList(complList); forwardJsp = "page10"; }
			 */
			if (eForm != null) {
				String formName = eForm.getFormName();
				// start get formname from property request
				/*
				 * this section of code for redirecting from
				 * DisplayPropertyDetls to linkingHistory.
				 */
				if (request.getParameter("formName") != null) {
					formName = request.getParameter("formName");
				}
				// /String propFormName = request.getParameter("formName");
				// if (!propFormName.equals("")) {
				// formName = propFormName;
				// }

				// end get formnamefrom property request
				String actionName = eForm.getActionName();
				logger.debug("formName:-" + formName);
				logger.debug("actionName:-" + actionName);
				RegCompleteMakerDTO dto = eForm.getRegDTO();
				CommonMkrDTO cdto = eForm.getCommonDTO();
				if ("cancel".equals(param)) {
					actionName = "CancelAction";
				}
				if("Y".equalsIgnoreCase(eForm.getDupTab())){

					if ("en".equalsIgnoreCase(language)) {
						dto.setRegNumber("");
						request.setAttribute("ERR", "Some error occured, please search again");
					} else {
						dto.setRegNumber("");
						request.setAttribute("ERR", "कुछ त्रुटि हुई है, कृपया फिर से खोजें");
					}
					eForm.setCheck("Y");
					eForm.setDupTab("");
					if(null!=session.getAttribute("dupTabRegId")){
						session.removeAttribute("dupTabRegId");
					}
					request.setAttribute("checkRegID", eForm);
					forwardJsp = "page1";
					return mapping.findForward(forwardJsp);
				
				}
				/*
				 * cdto.setCountryList(regmkrBD.getCountry());
				 * eForm.setCommonDTO(cdto); if (eForm.getCountry() != null &&
				 * !eForm.getCountry().equalsIgnoreCase("")) {
				 * 
				 * cdto.setStateList(regmkrBD.getState(eForm.getCountry()));
				 * eForm.setCommonDTO(cdto);
				 * 
				 * } else { cdto.setStateList(new ArrayList()); }
				 * 
				 * if (eForm.getState() != null &&
				 * !eForm.getState().equalsIgnoreCase("")) { cdto
				 * .setDistrictList(regmkrBD.getDistrict(eForm .getState()));
				 * 
				 * } else { cdto.setDistrictList(new ArrayList());
				 * 
				 * }
				 */

				String getTypeOfArea = regmkrBD.getTypeOfArea(dto.getRegNumber());
				boolean getisPOA = regmkrBD.getIsPoa(dto.getRegNumber());
				// String getDeedId= regmkrBD.getDeedId(dto.getRegNumber());

				if (getTypeOfArea.equalsIgnoreCase("1")) {
					eForm.getRegDTO().setTypeOfArea("1");
				} else if (getTypeOfArea.equalsIgnoreCase("2")) {
					eForm.getRegDTO().setTypeOfArea("2");
				}
				if (getisPOA) {
					eForm.getRegDTO().setIsPoa("Y");
				}
				ArrayList getPartydet = regmkrBD.getPartyDet(dto.getRegNumber());
				eForm.setCompPartyList(getPartydet);

				// /////added by simran

				if (request.getAttribute("regCompChecker") != null) {
					logger.debug("<-----IN MAKER ACTION REDIRECTED FROM CHECKER ACTION--->");
					RegCompCheckerDTO rDTO = (RegCompCheckerDTO) request.getAttribute("regCompChecker");
					actionName = rDTO.getActionName();

					if ("LINK_PAYMENT_CHECKER".equals(actionName)) {
						eForm.setMyMap(new HashMap());
						String str = rDTO.getRegInitNumber();
						dto.setRegNumber(str);
						ArrayList propIdList = regmkrBD.getpropIdList(str);
						if (propIdList.size() != 0) {
							eForm.setPropIdList(propIdList);
							for (int i = 0; i < propIdList.size(); i++) {
								System.out.println(propIdList.get(i).toString());
							}
							dto.setChecker("checker");
							forwardJsp = "linkingPayment";
						}
						dto.setChkChecker("Y");
						forwardJsp = "linkingPayment";
					}
				}
				if (request.getAttribute("LinkHistoryFromChecker") != null) {
					logger.debug("<-----IN MAKER ACTION REDIRECTED FROM CHECKER ACTION for link History Modification--->");
					RegCompCheckerDTO rDTO = (RegCompCheckerDTO) request.getAttribute("LinkHistoryFromChecker");
					actionName = rDTO.getActionName();
					if (RegCompConstant.MODIFY_LINK_HISTORY.equals(actionName)) {
						String regNum = rDTO.getRegInitNumber();
						dto.setRegNumber(regNum);

						String deedId = regmkrBD.getDeedId(dto.getRegNumber());
						String instId = regmkrBD.getInstId(dto.getRegNumber());
						int deedID = Integer.parseInt(deedId);
						int instID = Integer.parseInt(instId);
						dto.setCommonDeedFlag(regCommonBO.getCommonDeedFlag(deedId));

						/*
						 * if(deedID==RegInitConstant.DEED_ADOPTION ||
						 * deedID==RegInitConstant.DEED_TRUST ||
						 * deedID==RegInitConstant.DEED_DEPOSIT_OF_TITLE ||
						 * deedID==RegInitConstant.DEED_CANCELLATION_OF_WILL_POA
						 * || deedID==RegInitConstant.DEED_LEASE_NPV ||
						 * deedID==RegInitConstant.DEED_MORTGAGE_NPV ||
						 * deedID==RegInitConstant.DEED_POA ||
						 * deedID==RegInitConstant.DEED_SETTLEMENT_NPV ||
						 * deedID==RegInitConstant.DEED_WILL_NPV ||
						 * deedID==RegInitConstant.DEED_TRANSFER_LEASE_NPV ||
						 * deedID==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
						 * deedID==RegInitConstant.DEED_SURRENDER_LEASE_NPV ||
						 * deedID==RegInitConstant.DEED_PARTNERSHIP_NPV ||
						 * deedID==RegInitConstant.DEED_PARTITION_NPV ||
						 * deedID==RegInitConstant.DEED_AGREEMENT_MEMO_NPV ||
						 * deedID==RegInitConstant.DEED_COMPOSITION_NPV ||
						 * deedID==RegInitConstant.DEED_LETTER_OF_LICENCE_NPV ||
						 * deedID==RegInitConstant.DEED_SECURITY_BOND_NPV ||
						 * deedID==RegInitConstant.DEED_TRANSFER_NPV ||
						 * deedID==RegInitConstant.DEED_FURTHER_CHARGE_NPV ||
						 * dto.getCommonDeedFlag()==1){ //for deeds following
						 * code set2
						 * 
						 * 
						 * if(deedID==RegInitConstant.DEED_AGREEMENT_MEMO_NPV ||
						 * deedID==RegInitConstant.DEED_TRANSFER_NPV ||
						 * deedID==RegInitConstant.DEED_FURTHER_CHARGE_NPV){
						 * 
						 * if( instID==RegInitConstant.INST_AGREEMENT_MEMO_NPV_1
						 * || instID==RegInitConstant.INST_AGREEMENT_MEMO_NPV_2
						 * || instID==RegInitConstant.INST_AGREEMENT_MEMO_NPV_3
						 * || instID==RegInitConstant.INST_AGREEMENT_MEMO_NPV_4
						 * || instID==RegInitConstant.INST_AGREEMENT_MEMO_NPV_6
						 * || instID==RegInitConstant.INST_AGREEMENT_MEMO_NPV_7
						 * || instID==RegInitConstant.INST_AGREEMENT_MEMO_NPV_9
						 * || instID==RegInitConstant.INST_TRANSFER_NPV_1 ||
						 * instID==RegInitConstant.INST_TRANSFER_NPV_2 ||
						 * instID==RegInitConstant.INST_FURTHER_CHARGE_NPV_1){
						 * 
						 * dto.setAgreeMemoInstFlag(RegInitConstant.
						 * NPV_PROP_NOT_REQ_CONSTANT_2);
						 * 
						 * }else
						 * if(instID==RegInitConstant.INST_AGREEMENT_MEMO_NPV_10
						 * || instID==RegInitConstant.INST_AGREEMENT_MEMO_NPV_5
						 * || instID==RegInitConstant.INST_AGREEMENT_MEMO_NPV_8
						 * || instID==RegInitConstant.INST_TRANSFER_NPV_3 ||
						 * instID==RegInitConstant.INST_TRANSFER_NPV_4 ||
						 * instID==RegInitConstant.INST_FURTHER_CHARGE_NPV_2){
						 * 
						 * dto.setAgreeMemoInstFlag(RegInitConstant.
						 * NPV_PROP_REQ_CONSTANT_1); }
						 * 
						 * 
						 * }
						 * 
						 * dto.setDeedTypeFlag(1);
						 * //regForm.setHdnDeclareShareCheck("N");
						 * 
						 * 
						 * } if(deedID==RegInitConstant.DEED_DEPOSIT_OF_TITLE
						 * ||deedID==RegInitConstant.DEED_LEASE_NPV ||
						 * deedID==RegInitConstant.DEED_MORTGAGE_NPV
						 * ||deedID==RegInitConstant.DEED_POA ||
						 * deedID==RegInitConstant.DEED_SETTLEMENT_NPV
						 * ||deedID==RegInitConstant.DEED_WILL_NPV ||
						 * deedID==RegInitConstant.DEED_TRANSFER_LEASE_NPV
						 * ||deedID==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
						 * deedID==RegInitConstant.DEED_SURRENDER_LEASE_NPV
						 * ||deedID==RegInitConstant.DEED_COMPOSITION_NPV ||
						 * deedID==RegInitConstant.DEED_SECURITY_BOND_NPV
						 * ||deedID==RegInitConstant.DEED_LETTER_OF_LICENCE_NPV
						 * || (deedID==RegInitConstant.DEED_TRUST &&
						 * instID==RegInitConstant.INST_TRUST_NPV_P ) ||
						 * (deedID==RegInitConstant.DEED_PARTNERSHIP_NPV &&
						 * (instID==RegInitConstant.INST_PARTNERSHIP_NPV_1 ||
						 * instID==RegInitConstant.INST_PARTNERSHIP_NPV_2 ))||
						 * deedID==RegInitConstant.DEED_PARTITION_NPV ||
						 * (deedID==RegInitConstant.DEED_AGREEMENT_MEMO_NPV &&
						 * dto.getAgreeMemoInstFlag()==RegInitConstant.
						 * NPV_PROP_REQ_CONSTANT_1) ||
						 * (deedID==RegInitConstant.DEED_TRANSFER_NPV &&
						 * dto.getAgreeMemoInstFlag
						 * ()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
						 * (deedID==RegInitConstant.DEED_FURTHER_CHARGE_NPV &&
						 * dto.getAgreeMemoInstFlag()==RegInitConstant.
						 * NPV_PROP_REQ_CONSTANT_1) || dto.getDeedTypeFlag()==1)
						 * { dto.setPropPage("N"); } else {
						 * dto.setPropPage("P"); }
						 */
						dto.setDeedTypeFlag(1);
						dto.setPropPage("P");
						ArrayList propList = regmkrBD.getpropIdList(regNum);
						eForm.getCommonDTO().setPropertyList(propList);
						dto.setChecker("checker");
						forwardJsp = "linkingHistory";
					}
				}

				if (request.getParameter("actionName") != null) {
					logger.debug("--------------------------");
					if (request.getParameter("actionName").equalsIgnoreCase(RegCompConstant.CAVEAT_VIEW)) {
						String transactionId = request.getParameter("transactionId");

						if (request.getParameter("type").equalsIgnoreCase("court")) {
							eForm.setCaveatDetailList(regmkrBD.getCompleteCaveatDetails(transactionId));
							eForm.setCaveatBankDetailList(new ArrayList());
						} else if (request.getParameter("type").equalsIgnoreCase("bank")) {
							eForm.setCaveatBankDetailList(regmkrBD.getBankCaveatDetails(transactionId, language));
							eForm.setCaveatDetailList(new ArrayList());
						} else {
							logger.debug("error in Action Class");
						}
						forwardJsp = RegCompConstant.CAVEAT_DISPLAY;

					}

					mapping.findForward(forwardJsp);
				}
				// //////end

				if (request.getParameter("dms") != null) {
					String filePath = "";
					if (request.getParameter("dms").equalsIgnoreCase("retrieval")) {

						if (request.getParameter("path") != null) {
							/*
							 * String partyType="";
							 * filePath=request.getParameter("path").toString();
							 * DMSUtility dmsUtil=new DMSUtility();
							 * dmsUtil.readImage(filePath); String
							 * deedid=regmkrBD.getDeedId(dto.getRegNumber());
							 * request.setAttribute("deedId", deedid);
							 */
							String partyType = "";
							// filePath=request.getParameter("path").toString();
							// logger.debug("retrieval path-->"+filePath);
							// DMSUtility dmsUtil=new DMSUtility();
							// dmsUtil.readImage(filePath);
							String filename = request.getParameter("path").toString();
							// set the http content type to
							// "APPLICATION/OCTET-STREAM

							// initialize the http content-disposition header to
							// indicate a file attachment with the default
							// filename
							// "myFile.txt"
							// String fileName =
							// (String)formDTO.getCompThumbPath();
							// Filename=\"myFile.txt\"";

							// CODE COMMENTED BY SHRUTI----2 JUNE 2014
							// response.setContentType("application/download");
							// response.setContentType("application/download");

							/*
							 * response.setHeader("Content-Disposition",
							 * "attachment; filename=" +
							 * URLEncoder.encode(filename,"UTF-8"));
							 */
							// File fileToDownload = new File(filename);
							byte[] contents = null;
							contents = DMSUtility.getDocumentBytes(filename);
							downloadDocument(response, contents, filename);
							/*
							 * FileInputStream fileInputStream = new
							 * FileInputStream(fileToDownload); OutputStream out
							 * = response.getOutputStream(); byte[] buf = new
							 * byte[2048];
							 * 
							 * int readNum; while
							 * ((readNum=fileInputStream.read(buf))!=-1) {
							 * out.write(buf,0,readNum); }
							 * fileInputStream.close(); out.close();
							 */

							// end of code commented by shruti
							String[] deedInstArray = bd.getDeedInstId(dto.getRegNumber());
							if (deedInstArray != null && deedInstArray.length > 0) {

								request.setAttribute("deedId", deedInstArray[0].trim());
								request.setAttribute("instId", deedInstArray[1].trim());
							}
							// forwardJsp = "complianceListNew";
						}

					}
				}

				// $2 Start: code for Page1.jsp
				// Start:Search Form
				if (RegCompConstant.REGCOMP_SEARCH_FORM.equals(formName)) {

					if (RegCompConstant.SEARCH_ACTION.equals(actionName)) {

						dto.setStoreAction(actionName);
						// /////////////////HOLD RESUME////////////////////
						String regNumber1 = dto.getRegNumber();

						if (regNumber1.startsWith("0")) {
							regNumber1 = regNumber1.substring(1);
						}
						logger.debug("***********" + regNumber1);
						dto.setRegNumber(regNumber1);
						boolean checkEtokenFlag = regmkrBD.checkEtokenFlag(officeId);
						String checkEtokenUserId = "";
						if (checkEtokenFlag) {
							checkEtokenUserId = regmkrBD.checkEtokenUserId(regNumber1, userId);
						}
						ArrayList estampDetls = bd.getEstampStatus(dto.getRegNumber());
						ArrayList estampRefundDetls = bd.getEstampRefundStatus(dto.getRegNumber());
						ArrayList cavList = bd.cavetsCheck(dto.getRegNumber());
						logger.debug("<----Checking estamp Details");
						if ((checkEtokenUserId == null || checkEtokenUserId == "") && checkEtokenFlag) {
							if ("en".equalsIgnoreCase(language)) {
								dto.setRegNumber("");
								request.setAttribute("ERR", "For the logged in office Application number can be processed through EToken");
							} else {
								dto.setRegNumber("");
								request.setAttribute("ERR", "लॉग इन ऑफिस के लिए एप्लीकेशन नंबर ईटोकेन के जरिए प्रोसेस किया जा सकता है");
							}
							eForm.setCheck("Y");
							request.setAttribute("checkRegID", eForm);
							forwardJsp = "page1";
							return mapping.findForward(forwardJsp);
						} else if (!checkEtokenUserId.equalsIgnoreCase(userId) && checkEtokenFlag) {
							if ("en".equalsIgnoreCase(language)) {
								dto.setRegNumber("");
								request.setAttribute("ERR", "Different counter has been assigned to this initiation number.");
							} else {
								dto.setRegNumber("");
								request.setAttribute("ERR", "इस  पंजीकरण संख्या को सौंपा गया  काउंटर अलग है |");
							}
							eForm.setCheck("Y");
							request.setAttribute("checkRegID", eForm);
							forwardJsp = "page1";
							return mapping.findForward(forwardJsp);
						} else if (regmkrBD.resumeLinkingProp(dto.getRegNumber())) {
							dto.setIsLinking("Y");
							eForm.setRegDTO(dto);
							forwardJsp = "confirmScreen";
						} else if (cavList != null && cavList.size() > 0) {
							eForm.setCaveatDetailsList(cavList);
							dto.setIsCavets("Y");
							eForm.setRegDTO(dto);
							forwardJsp = "confirmScreen";
						} else if (estampDetls.size() > 0 || estampRefundDetls.size() > 0) {
							if (estampDetls.size() > 0) {
								logger.debug("estamp not valid");
								for (int i = 0; i < estampDetls.size(); i++) {

									RegCompCheckerDTO rdto = (RegCompCheckerDTO) estampDetls.get(i);
									// logger.debug("Estamp Status---->"+rdto.
									// getEstampStatus());
									// logger.debug("Estamp Id---->"+rdto.
									// getEstampTxnId());
									dto.setEstampStatus(rdto.getEstampStatus());
									dto.setEstampTxnId(rdto.getEstampTxnId());
									if (rdto.getEstampStatus().equals("D")) {
										logger.debug("Status --Deactivated");
										dto.setRegNumber("");
										messages.add("ERRORMSG", new ActionMessage("reg.Estamp.Deactivate"));
									} else if (rdto.getEstampStatus().equals("E")) {
										dto.setRegNumber("");
										messages.add("ERRORMSG", new ActionMessage("reg.Estamp.Expire"));
									} else {
										dto.setRegNumber("");
										messages.add("ERRORMSG", new ActionMessage("reg.Estamp.consumed"));

									}
								}

							} else {
								for (int i = 0; i < estampRefundDetls.size(); i++) {

									RegCompCheckerDTO rdto = (RegCompCheckerDTO) estampRefundDetls.get(i);
									// logger.debug("Estamp Status---->"+rdto.
									// getEstampStatus());
									// logger.debug("Estamp Id---->"+rdto.
									// getEstampTxnId());
									dto.setEstampStatus(rdto.getEstampStatus());
									dto.setEstampTxnId(rdto.getEstampTxnId());
									dto.setRegNumber("");
									messages.add("ERRORMSG", new ActionMessage("reg.Estamp.Refunded"));
								}
							}
							saveMessages(request, messages);
							eForm.setRegDTO(dto);
							forwardJsp = "confirmScreen";
						} else {
							String regNumber = dto.getRegNumber();
							if("".equals(regNumber)){
								if ("en".equalsIgnoreCase(language)) {
									dto.setRegNumber("");
									request.setAttribute("ERR", "Some error occured, Please search again");
								} else {
									dto.setRegNumber("");
									request.setAttribute("ERR", "कुछ त्रुटि हुई है, कृपया फिर से खोजें");
								}
								eForm.setCheck("Y");
								request.setAttribute("checkRegID", eForm);
								forwardJsp = "page1";
								return mapping.findForward(forwardJsp);
							}
							/**
							 * RegCompletionRule rule = new RegCompletionRule();
							 * ArrayList errorList = rule.checkRegID(regNumber);
							 * System.out.println(errorList.size());
							 * if(rule.isError()) {
							 * request.setAttribute(RegCompConstant.ERROR_FLAG,
							 * "true");
							 * request.setAttribute(RegCompConstant.ERROR_LIST,
							 * errorList); eForm.setCheckRegNo("");
							 */

							// Start :check status =m
							boolean checkStatusM = regmkrBD.checkstatusM(regNumber);

							if (checkStatusM) {
								forwardJsp = "success1";
								return mapping.findForward(forwardJsp);
							}

							else {
								if (!bd.checkRegNumber(regNumber)) {
									if ("en".equalsIgnoreCase(language)) {
										dto.setRegNumber("");
										request.setAttribute("ERR", "no records found");
										// messages.add("MSG", new
										// ActionMessage(
										// "no.slot.booked"));
									} else {
										dto.setRegNumber("");
										request.setAttribute("ERR", "कोई रिकॉर्ड नहीं पाया गया|");
									}
									eForm.setCheck("Y");
									request.setAttribute("checkRegID", eForm);
									forwardJsp = "page1";
								}

								else {
									if (bd.checkerMakerOffice(regNumber, officeId).equalsIgnoreCase("BOOKEDPROPER")) {
										// Start:===code for checking hold
										String checkHld = regmkrBD.checkHold(regNumber);
										if (!checkHld.equalsIgnoreCase("")) {

											regmkrBD.insertTimeMakerStart(eForm, UserId, officeId);

											eForm.setCheckHold("true");
											actionName = "CONTINUE_HOLD";
											eForm.setFormName(checkHld);
											formName = eForm.getFormName();
											logger.debug(eForm.getFormName());
										} else {

											// *****************FOR PARTIAL
											// SAVE-- ADDED BY
											// SIMRAN************
											// *****************///
											String regStatus = regmkrBD.getCancelledDetails(dto.getRegNumber());
											if (regStatus != "") {

												if (!"10".equalsIgnoreCase(regStatus)) {
													regmkrBD.insertTimeMakerStart(eForm, UserId, officeId);
												}

												if (regStatus.equalsIgnoreCase(RegCompConstant.LINKING_PAYMENT_STATUS)) {
													String regNum = dto.getRegNumber();
													RegCompleteDutiesDTO dutydto = new RegCompleteDutiesDTO();
													// regmkrBD.getTotalDuties(
													// regNum);
													eForm.setDutyDTO(dutydto);
													eForm.setFormName("");
													eForm.setActionName("");
													String str = dto.getRegNumber();
													// boolean
													// checkalrdyPaidDuty =
													// regmkrBD
													// .checkalrdyPaidDuty
													// (dto.getRegNumber());
													// if (checkalrdyPaidDuty) {
													// actionName =
													// "NEXT_ACTION";
													// if (RegCompConstant.
													// NEXT_ACTION
													// .equals(actionName)) {
													ArrayList propIdList = regmkrBD.getpropIdList(str);
													if (propIdList.size() != 0) {
														eForm.setPropIdList(propIdList);
														// /for (int i = 0; i <
														// propIdList.size();
														// i++) {
														//logger.debug(propIdList
														// .get(i).toString());
														// }
														forwardJsp = "linkingPayment";
													}
													// }

													// }
												} else if (regStatus.equalsIgnoreCase(RegCompConstant.TIME_BARRED_STATUS)) {
													String peanlityId = bd.getPenalityId(dto.getRegNumber());
													if (peanlityId != null && !peanlityId.equalsIgnoreCase("")) {
														dto.setIsCase("Y");
														dto.setCaseNumber(peanlityId);
														forwardJsp = "confirmScreen";
													} else {
														peanlityId = bd.getPenalityIdOnly(dto.getRegNumber());
														dto.setUploadFlag("N");
														dto.setExecDate(bd.getExecDate(dto.getRegNumber()));
														dto.setTimePhotoName(RegCompConstant.TIME_BARRED_DOCUMENT);
														dto.setTimePhotoPath(FILE_UPLOAD_PATH + dto.getRegNumber() + File.separator + RegCompConstant.TIME_BARRED_FOLDER + File.separator);
														dto.setCaseNumber(peanlityId);
														dto.setTimePhotoPathUploaded("");
														forwardJsp = "timebarredResume";
													}
												} else if (regStatus.equalsIgnoreCase(RegCompConstant.WITNESS_DETAILS_STATUS)) {

													eForm.setRelationshipList(regmkrBD.getRelationshipList("M", language));
													forwardJsp = "witnessDet2";
												} else if (regStatus.equalsIgnoreCase(RegCompConstant.OTHER_DEED_DETLS_STATUS)) {
													String deedId = regmkrBD.getDeedId(dto.getRegNumber());

													dto.setDeedID(deedId);
													forwardJsp = "otherDeedDetailsNew";
												} else if (regStatus.equalsIgnoreCase(RegCompConstant.LINKING_HISTORY_STATUS)) {
													String deedId = regmkrBD.getDeedId(dto.getRegNumber());
													String instId = regmkrBD.getInstId(dto.getRegNumber());
													int deedID = Integer.parseInt(deedId);
													int instID = Integer.parseInt(instId);
													dto.setCommonDeedFlag(regCommonBO.getCommonDeedFlag(deedId));

													/*
													 * if(deedID==RegInitConstant
													 * .DEED_ADOPTION ||
													 * deedID==
													 * RegInitConstant.DEED_TRUST
													 * ||
													 * deedID==RegInitConstant
													 * .DEED_DEPOSIT_OF_TITLE ||
													 * deedID==RegInitConstant.
													 * DEED_CANCELLATION_OF_WILL_POA
													 * ||
													 * deedID==RegInitConstant
													 * .DEED_LEASE_NPV ||
													 * deedID==RegInitConstant.
													 * DEED_MORTGAGE_NPV ||
													 * deedID
													 * ==RegInitConstant.DEED_POA
													 * ||
													 * deedID==RegInitConstant
													 * .DEED_SETTLEMENT_NPV ||
													 * deedID==RegInitConstant.
													 * DEED_WILL_NPV ||
													 * deedID==RegInitConstant
													 * .DEED_TRANSFER_LEASE_NPV
													 * ||
													 * deedID==RegInitConstant
													 * .DEED_RECONV_MORTGAGE_NPV
													 * ||
													 * deedID==RegInitConstant
													 * .DEED_SURRENDER_LEASE_NPV
													 * ||
													 * deedID==RegInitConstant
													 * .DEED_PARTNERSHIP_NPV ||
													 * deedID==RegInitConstant.
													 * DEED_PARTITION_NPV ||
													 * deedID==RegInitConstant.
													 * DEED_AGREEMENT_MEMO_NPV
													 * ||
													 * deedID==RegInitConstant
													 * .DEED_COMPOSITION_NPV ||
													 * deedID==RegInitConstant.
													 * DEED_LETTER_OF_LICENCE_NPV
													 * ||
													 * deedID==RegInitConstant
													 * .DEED_SECURITY_BOND_NPV
													 * ||
													 * deedID==RegInitConstant
													 * .DEED_TRANSFER_NPV ||
													 * deedID==RegInitConstant.
													 * DEED_FURTHER_CHARGE_NPV
													 * ||
													 * dto.getCommonDeedFlag()
													 * ==1){ //for deeds
													 * following code set2
													 * 
													 * 
													 * 
													 * 
													 * if(deedID==RegInitConstant
													 * .DEED_AGREEMENT_MEMO_NPV
													 * ||
													 * deedID==RegInitConstant
													 * .DEED_TRANSFER_NPV ||
													 * deedID==RegInitConstant.
													 * DEED_FURTHER_CHARGE_NPV){
													 * 
													 * if(
													 * instID==RegInitConstant
													 * .INST_AGREEMENT_MEMO_NPV_1
													 * ||
													 * instID==RegInitConstant
													 * .INST_AGREEMENT_MEMO_NPV_2
													 * ||
													 * instID==RegInitConstant
													 * .INST_AGREEMENT_MEMO_NPV_3
													 * ||
													 * instID==RegInitConstant
													 * .INST_AGREEMENT_MEMO_NPV_4
													 * ||
													 * instID==RegInitConstant
													 * .INST_AGREEMENT_MEMO_NPV_6
													 * ||
													 * instID==RegInitConstant
													 * .INST_AGREEMENT_MEMO_NPV_7
													 * ||
													 * instID==RegInitConstant
													 * .INST_AGREEMENT_MEMO_NPV_9
													 * ||
													 * instID==RegInitConstant
													 * .INST_TRANSFER_NPV_1 ||
													 * instID==RegInitConstant.
													 * INST_TRANSFER_NPV_2 ||
													 * instID==RegInitConstant.
													 * INST_FURTHER_CHARGE_NPV_1
													 * ){
													 * 
													 * dto.setAgreeMemoInstFlag(
													 * RegInitConstant
													 * .NPV_PROP_NOT_REQ_CONSTANT_2
													 * );
													 * 
													 * }else
													 * if(instID==RegInitConstant
													 * .
													 * INST_AGREEMENT_MEMO_NPV_10
													 * ||
													 * instID==RegInitConstant
													 * .INST_AGREEMENT_MEMO_NPV_5
													 * ||
													 * instID==RegInitConstant
													 * .INST_AGREEMENT_MEMO_NPV_8
													 * ||
													 * instID==RegInitConstant
													 * .INST_TRANSFER_NPV_3 ||
													 * instID==RegInitConstant.
													 * INST_TRANSFER_NPV_4 ||
													 * instID==RegInitConstant.
													 * INST_FURTHER_CHARGE_NPV_2
													 * ){
													 * 
													 * dto.setAgreeMemoInstFlag(
													 * RegInitConstant
													 * .NPV_PROP_REQ_CONSTANT_1
													 * ); }
													 * 
													 * 
													 * }
													 * 
													 * dto.setDeedTypeFlag(1);
													 * //regForm.
													 * setHdnDeclareShareCheck
													 * ("N");
													 * 
													 * 
													 * }
													 * if(deedID==RegInitConstant
													 * .DEED_DEPOSIT_OF_TITLE
													 * ||deedID
													 * ==RegInitConstant.
													 * DEED_LEASE_NPV ||
													 * deedID==RegInitConstant.
													 * DEED_MORTGAGE_NPV
													 * ||deedID
													 * ==RegInitConstant.
													 * DEED_POA ||
													 * deedID==RegInitConstant
													 * .DEED_SETTLEMENT_NPV
													 * ||deedID
													 * ==RegInitConstant.
													 * DEED_WILL_NPV ||
													 * deedID==RegInitConstant
													 * .DEED_TRANSFER_LEASE_NPV
													 * ||
													 * deedID==RegInitConstant.
													 * DEED_RECONV_MORTGAGE_NPV
													 * ||
													 * deedID==RegInitConstant
													 * .DEED_SURRENDER_LEASE_NPV
													 * ||
													 * deedID==RegInitConstant.
													 * DEED_COMPOSITION_NPV ||
													 * deedID==RegInitConstant.
													 * DEED_SECURITY_BOND_NPV
													 * ||deedID
													 * ==RegInitConstant.
													 * DEED_LETTER_OF_LICENCE_NPV
													 * ||
													 * (deedID==RegInitConstant
													 * .DEED_TRUST &&
													 * instID==RegInitConstant
													 * .INST_TRUST_NPV_P ) ||
													 * (deedID==RegInitConstant.
													 * DEED_PARTNERSHIP_NPV //&&
													 * /
													 * /(instID==RegInitConstant
													 * .INST_PARTNERSHIP_NPV_1
													 * ||
													 * instID==RegInitConstant
													 * .INST_PARTNERSHIP_NPV_2 )
													 * )||
													 * deedID==RegInitConstant
													 * .DEED_PARTITION_NPV ||
													 * (deedID==RegInitConstant.
													 * DEED_AGREEMENT_MEMO_NPV
													 * &&
													 * dto.getAgreeMemoInstFlag
													 * ()==RegInitConstant.
													 * NPV_PROP_REQ_CONSTANT_1)
													 * ||
													 * (deedID==RegInitConstant
													 * .DEED_TRANSFER_NPV &&
													 * dto.
													 * getAgreeMemoInstFlag()
													 * ==RegInitConstant
													 * .NPV_PROP_REQ_CONSTANT_1)
													 * ||
													 * (deedID==RegInitConstant
													 * .DEED_FURTHER_CHARGE_NPV
													 * &&
													 * dto.getAgreeMemoInstFlag
													 * ()==RegInitConstant.
													 * NPV_PROP_REQ_CONSTANT_1)
													 * ||
													 * dto.getDeedTypeFlag()==1)
													 * { dto.setPropPage("N"); }
													 * else {
													 * dto.setPropPage("P"); }
													 */
													dto.setDeedTypeFlag(1);
													dto.setPropPage("P");
													ArrayList propList = regmkrBD.getpropIdList(dto.getRegNumber());
													eForm.getCommonDTO().setPropertyList(propList);
													eForm.setPropIdList(propList);
													forwardJsp = "linkingHistory";
												} else if (regStatus.equalsIgnoreCase(RegCompConstant.PRESENTATION_STATUS)) {
													String regNum = dto.getRegNumber();
													ArrayList transPartyList = regmkrBD.getTransactingParties(regNum, language);
													// ArrayList witnessList =
													// regmkrBD
													// .getWitnessList(regNum
													// ,cdate);
													eForm.setTransactPartyList(transPartyList);
													// eForm.setWitnessList(
													// witnessList);
													// ArrayList getOfficialList
													// =
													// regmkrBD.getofficialList
													// ();
													// eForm.getRegDTO().
													// setGovOfficialList
													// (getOfficialList);
													forwardJsp = "presentation";
												} else if (regStatus.equalsIgnoreCase(RegCompConstant.COMPLIANCE_LIST_STATUS)) {
													propertyRelatedComplianceMap = regmkrBD.getPropertyRelatedComplianeList(dto.getRegNumber());
													eForm.setCompliancePropertyRelatedMap(propertyRelatedComplianceMap);

													partyRelatedComplianceMap = regmkrBD.getPartyRelatedComplianeList(dto.getRegNumber(), language);
													eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);

													witnessPhotoUploadMap = regmkrBD.getWitnessDetailsForCompliance(dto.getRegNumber());
													eForm.setUploadWitnessPhotograph(witnessPhotoUploadMap);
													String deedid = regmkrBD.getDeedId(dto.getRegNumber());
													request.setAttribute("deedId", deedid);
													forwardJsp = "complianceListNew";
												} else if (regStatus.equalsIgnoreCase(RegCompConstant.CHECKLIST_STATUS)) {
													dto.setStoreAction("CAVEAT_CHECKLIST");
													ArrayList caveatdet = regmkrBD.getCaveatDet(dto.getRegNumber());
													eForm.setCaveatListSize(caveatdet.size());
													eForm.setCaveatList(caveatdet);
													ArrayList bnkCaveatdet = regmkrBD.getBnkCaveatDet(dto.getRegNumber());
													eForm.setBnkCaveatList(bnkCaveatdet);
													eForm.setBnkCaveatListSize(bnkCaveatdet.size());
													ArrayList partyDetls = regmkrBD.getTransPartyDets(dto.getRegNumber());
													eForm.setPartyList(partyDetls);
													// ArrayList witnessList =
													// regmkrBD
													// .getWitnessList(dto
													// .getRegNumber(),cdate);
													// eForm.setWitnessList(
													// witnessList);
													eForm.setActionName("");
													String filePath = FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_DEATH_CERT;
													dto.setDeathCert(RegCompCheckerConstant.FILE_NAME_DEATH_CERT);
													dto.setFilePath(filePath);
													String filePathRelation = FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_RELN_PROOF;
													dto.setRelationProofName(RegCompConstant.FILE_NAME_RELTN_PROOF);
													dto.setFilePathRltn(filePathRelation);
													dto.setFilePathPOA(FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_POA_DOC);
													dto.setPoaUploadFilename(RegCompCheckerConstant.FILE_NAME_POA_DOC);
													// dto.setExeDate(bd.
													// getExecDate
													// (dto.getRegNumber()));
													eForm.setExeDate(bd.getExecDate(dto.getRegNumber()));
													forwardJsp = "checklist";
												} else if (regStatus.equalsIgnoreCase("42")) {

													regNumber = dto.getRegNumber();
													ArrayList transPartyList = regmkrBD.getTransactingParties(regNumber, language);
													ArrayList witnessList = regmkrBD.getWitnessList(regNumber, cdate, language);
													ArrayList consenterList = regmkrBD.getConsenterList(regNumber, language);
													if (consenterList.size() > 0) {
														dto.setConsenterChk("Y");
													} else {
														dto.setConsenterChk("N");
													}
													eForm.setTransactPartyList(transPartyList);
													eForm.setWitnessList(witnessList);
													eForm.setConsenterList(consenterList);
													String pathFP = FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_PHOTO;
													dto.setParentPathPhoto(pathFP);
													dto.setFileNamePhoto("Photograph.JPG");
													// regmkrBD.
													// UpdateRegistrationCompletionStatus
													// (dto.getRegNumber(),
													// RegCompConstant
													// .PHOTOGRAPH_STATUS,
													// cdate, UserId);
													forwardJsp = "PhotographCapture";
												} else if (regStatus.equalsIgnoreCase("43")) {
													eForm.setRelationshipList(regmkrBD.getRelationshipList("M", language));
													forwardJsp = "consenterDetails";
												} else if (regStatus.equalsIgnoreCase(RegCompConstant.VERIFICATION_STATUS)) {
													forwardJsp = "verification";
												}
											} else {

												Boolean checkRegID = regmkrBD.checkID(regNumber);
												// checkEtokenFlag=regmkrBD.
												// checkEtokenFlag(officeId);
												// checkEtokenUserId="";
												// if(checkEtokenFlag){
												// checkEtokenUserId=regmkrBD.
												// checkEtokenUserId
												// (regNumber,userId);
												// }
												if (!checkRegID) {
													// messages.add("MSG", new
													// ActionMessage(
													// "no.records.found"));
													// saveMessages(request,
													// messages);
													if ("en".equalsIgnoreCase(language)) {
														dto.setRegNumber("");
														request.setAttribute("ERR", "no records found");
														// messages.add("MSG",
														// new ActionMessage(
														// "no.slot.booked"));
													} else {
														dto.setRegNumber("");
														request.setAttribute("ERR", "कोई रिकॉर्ड नहीं पाया गया|");
													}
													eForm.setCheck("Y");
													request.setAttribute("checkRegID", eForm);
													forwardJsp = "page1";

												} else if ((checkEtokenUserId == null || checkEtokenUserId == "") && checkEtokenFlag) {
													if ("en".equalsIgnoreCase(language)) {
														dto.setRegNumber("");
														request.setAttribute("ERR", "For the logged in office Application number can be processed through EToken");
													} else {
														dto.setRegNumber("");
														request.setAttribute("ERR", "लॉग इन ऑफिस के लिए एप्लीकेशन नंबर ईटोकेन के जरिए प्रोसेस किया जा सकता है");
													}
													eForm.setCheck("Y");
													request.setAttribute("checkRegID", eForm);
													forwardJsp = "page1";
												} else if (!checkEtokenUserId.equalsIgnoreCase(userId) && checkEtokenFlag) {
													if ("en".equalsIgnoreCase(language)) {
														dto.setRegNumber("");
														request.setAttribute("ERR", "Different counter has been assigned to this initiation number.");
													} else {
														dto.setRegNumber("");
														request.setAttribute("ERR", "इस  पंजीकरण संख्या को सौंपा गया  काउंटर अलग है |");
													}
													eForm.setCheck("Y");
													request.setAttribute("checkRegID", eForm);
													forwardJsp = "page1";
												}
												// End:==== Code for checking
												// Hold

												else {
													//added by saurav for slot booking changes
													ArrayList<String> slotStatusList = regmkrBD.getSlotActiveCheck(regNumber, officeId);
													if(!slotStatusList.isEmpty()){
													if (!("No Slot Id".equalsIgnoreCase(slotStatusList.get(6))) & !(("NA").equalsIgnoreCase(slotStatusList.get(6)))) {
														String uppperTimeStatus = "";
														String lowerTimeStatus = "";
														boolean slotStatus = true;
														boolean slotEarly = false;
														// slotStatusList =
														// slotStatusList
														// .get(0);

														uppperTimeStatus = slotStatusList.get(1);
														lowerTimeStatus = slotStatusList.get(2);
														String upperTimeDuration = slotStatusList.get(0);
														Timestamp tstmp = new Timestamp(System.currentTimeMillis());
														long currentTimeInMS = tstmp.getTime();
														String slotdate = slotStatusList.get(5);
														Timestamp tstmp1 = Timestamp.valueOf(slotdate + " " + slotStatusList.get(3));
														long timedifference = tstmp.getTime() - tstmp1.getTime();
														if ("A".equalsIgnoreCase(uppperTimeStatus)) {

															if (timedifference > Integer.parseInt(upperTimeDuration) * 60 * 1000) {
																slotStatus = false;
															}
														}
														if ("A".equalsIgnoreCase(lowerTimeStatus)) {
															timedifference = tstmp.getTime() - tstmp1.getTime();
															if (timedifference < 0) {
																slotEarly = true;
															}
														}
														if (!slotStatus) {
															if ("en".equalsIgnoreCase(language)) {
																dto.setRegNumber("");
																request.setAttribute("ERR", "Slot has been blocked due to late arrival of user");
																
															} else {
																dto.setRegNumber("");
																request.setAttribute("ERR", "उपयोगकर्ता के देर से आने के कारण स्लॉट को ब्लॉक कर दिया गया है");
															}
															eForm.setCheck("Y");
															request.setAttribute("checkRegID", eForm);
															forwardJsp = "page1";
															return mapping.findForward(forwardJsp);
														}
														if (slotEarly) {
															if ("en".equalsIgnoreCase(language)) {
																dto.setRegNumber("");
																request.setAttribute("ERR", "Please come at the alloted slot time, which is " + tstmp1);
																// ;
															} else {
																dto.setRegNumber("");
																request.setAttribute("ERR", "कृपया आवंटित स्लॉट समय पर आएं, जो " + tstmp1 + " है |");
															}
															eForm.setCheck("Y");
															request.setAttribute("checkRegID", eForm);
															forwardJsp = "page1";
															return mapping.findForward(forwardJsp);
														}
													}
													}
													//end saurav
													if (bd.checkBookedSlot(regNumber, officeId)) // for
													// slot
													// booking
													{

														logger.debug("********deed*******" + dto.getParentPathScan());
														regmkrBD.insertTimeMakerStart(eForm, UserId, officeId);
														String deedId = regmkrBD.getDeedId(regNumber);
														String instId = regmkrBD.getInstId(regNumber);
														int deed = Integer.parseInt(deedId);
														int inst = Integer.parseInt(instId);
														dto.setDeedID(deedId);
														dto.setInstrumentId(instId);
														int commonDeedFlag = regCommonBO.getCommonDeedFlag(deedId);
														dto.setCommonDeedFlag(commonDeedFlag);
														//eForm.setRegTxnId(regNumber);
														session.setAttribute("dupTabRegId", regNumber);
														if (deed == RegInitConstant.DEED_ADOPTION || (deed == RegInitConstant.DEED_TRUST && inst == RegInitConstant.INST_TRUST_NPV_P) || deed == RegInitConstant.DEED_CANCELLATION_OF_WILL_POA || deed == RegInitConstant.DEED_AGREEMENT_MEMO_NPV || deed == RegInitConstant.DEED_AGREEMENT_MEMO_NPV_LOAN || deed == RegInitConstant.DEED_DOC_AMEND // For
														// Miscellenous
														// ,
														// amend
														// /
														// correction
														// deed
														|| dto.getCommonDeedFlag() == 1) // deeds
														// not
														// requiring
														// property
														// valuation
														{
															logger.debug("----------->third category");
															eForm.setRegDTO(dto);
															System.out.println(dto.getAge());
															eForm.setCheckRegNo("W");
															RegCommonForm regcommonForm = new RegCommonForm();
															regcommonForm.setActionName("CONFIRMATION_OK_ACTION_NO_VALUATION");
															regcommonForm.setFromModule("RegCompMaker");
															regcommonForm.setHidnRegTxnId(eForm.getRegDTO().getRegNumber());
															regcommonForm.getRegDTO().setParentPathScan(FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_DEED_DOC);
															regcommonForm.getRegDTO().setFileNameScan(RegCompConstant.FILE_NAME_DEED_DOC);
															regcommonForm.getRegDTO().setGuidUpload("");
															ArrayList deedDocList = regmkrBD.getDeedDocDetails(dto.getRegNumber());
															for (int i = 0; i < deedDocList.size(); i++) {
																ArrayList subList = (ArrayList) deedDocList.get(i);
																String deedDocPath = (String) subList.get(1);
																if (deedDocPath == null || deedDocPath.equals(""))
																	regcommonForm.setDeedDoc("N");
																else
																	regcommonForm.setDeedDoc("Y");
															}
															if (deedDocList.size() == 0)
																regcommonForm.setDeedDoc("N");
															// regcommonForm.
															// setDeedDoc("N");
															regcommonForm.setCheckRegNo("W");
															request.setAttribute("regForm", regcommonForm);
															forwardJsp = "redirect";
														}

														else if (deed == RegInitConstant.DEED_DEPOSIT_OF_TITLE || deed == RegInitConstant.DEED_SURRENDER_LEASE_NPV || deed == RegInitConstant.DEED_RECONV_MORTGAGE_NPV || deed == RegInitConstant.DEED_MORTGAGE_NPV || deed == RegInitConstant.DEED_POA || deed == RegInitConstant.DEED_TRANSFER_LEASE_NPV || deed == RegInitConstant.DEED_PARTITION_NPV || deed == RegInitConstant.DEED_PARTNERSHIP_NPV || deed == RegInitConstant.DEED_LEASE_NPV || deed == RegInitConstant.DEED_WILL_NPV || deed == RegInitConstant.DEED_SETTLEMENT_NPV || (deed == RegInitConstant.DEED_TRUST && inst == RegInitConstant.INST_TRUST_NPV_NP)) {
															logger.debug("----------->third category");
															eForm.setRegDTO(dto);
															System.out.println(dto.getAge());
															eForm.setCheckRegNo("W");
															RegCommonForm regcommonForm = new RegCommonForm();
															regcommonForm.setActionName("CONFIRMATION_OK_ACTION_NO_VALUATION");
															regcommonForm.setFromModule("RegCompMaker");
															regcommonForm.setHidnRegTxnId(eForm.getRegDTO().getRegNumber());
															regcommonForm.getRegDTO().setParentPathScan(FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_DEED_DOC);
															regcommonForm.getRegDTO().setFileNameScan(RegCompConstant.FILE_NAME_DEED_DOC);
															regcommonForm.getRegDTO().setGuidUpload("");
															ArrayList deedDocList = regmkrBD.getDeedDocDetails(dto.getRegNumber());
															for (int i = 0; i < deedDocList.size(); i++) {
																ArrayList subList = (ArrayList) deedDocList.get(i);
																String deedDocPath = (String) subList.get(1);
																if (deedDocPath == null || deedDocPath.equals(""))
																	regcommonForm.setDeedDoc("N");
																else
																	regcommonForm.setDeedDoc("Y");
															}
															if (deedDocList.size() == 0)
																regcommonForm.setDeedDoc("N");
															// regcommonForm.
															// setDeedDoc("N");
															regcommonForm.setCheckRegNo("W");
															request.setAttribute("regForm", regcommonForm);
															forwardJsp = "redirect";
														}

														else // property
														// valuation
														{
															eForm.setRegDTO(dto);
															System.out.println(dto.getAge());
															eForm.setCheckRegNo("W");
															RegCommonForm regcommonForm = new RegCommonForm();
															regcommonForm.setActionName("CONFIRMATION_OK_ACTION");
															regcommonForm.setFromModule("RegCompMaker");
															regcommonForm.setHidnRegTxnId(eForm.getRegDTO().getRegNumber());
															regcommonForm.getRegDTO().setParentPathScan(FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_DEED_DOC);
															regcommonForm.getRegDTO().setFileNameScan(RegCompConstant.FILE_NAME_DEED_DOC);
															regcommonForm.getRegDTO().setGuidUpload("");
															ArrayList deedDocList = regmkrBD.getDeedDocDetails(dto.getRegNumber());
															for (int i = 0; i < deedDocList.size(); i++) {
																ArrayList subList = (ArrayList) deedDocList.get(i);
																String deedDocPath = (String) subList.get(1);
																if (deedDocPath == null || deedDocPath.equals(""))
																	regcommonForm.setDeedDoc("N");
																else
																	regcommonForm.setDeedDoc("Y");
															}
															if (deedDocList.size() == 0)
																regcommonForm.setDeedDoc("N");
															// regcommonForm.
															// setDeedDoc("N");
															regcommonForm.setCheckRegNo("W");
															request.setAttribute("regForm", regcommonForm);
															forwardJsp = "redirect";

														}
													} else {
														// messages.add("MSG",
														// new ActionMessage(
														// "no.slot.found"));
														// saveMessages(request,
														// messages);
														if ("en".equalsIgnoreCase(language)) {
															
															request.setAttribute("ERR", "Slot Not booked for Today.");
															//messages.add("MSG"
															// , new
															// ActionMessage(
															//"no.slot.booked"))
															// ;
														} else {
															request.setAttribute("ERR", "स्लॉट आज के लिए बुक नहीं किया गया है|");
														}
														eForm.setCheck("Y");
														String chkRegNum ="";
														if(null!=session.getAttribute("dupTabRegId")){
															chkRegNum=(String) session.getAttribute("dupTabRegId");
														}
														if(!regNumber.equals(chkRegNum)){
															if(!"".equalsIgnoreCase(chkRegNum)){
																eForm.setDupTab("Y");
															}
														}
														dto.setRegNumber("");
														request.setAttribute("checkRegID", eForm);
														forwardJsp = "page1";
													}
												}
											}

										}
									} else if (bd.checkerMakerOffice(regNumber, officeId).equalsIgnoreCase("NOTPROPER")) {
										// messages.add("MSG", new
										// ActionMessage(
										// "no.proper.slot.booked"));
										// saveMessages(request, messages);
										if ("en".equalsIgnoreCase(language)) {
											dto.setRegNumber("");
											request.setAttribute("ERR", "Slot booked for another office.Please Visit the Office for Which slot is booked.");
											// messages.add("MSG", new
											// ActionMessage(
											// "no.slot.booked"));
										} else {
											dto.setRegNumber("");
											request.setAttribute("ERR", "स्लॉट अन्य कार्यालय के लिए बुक किया गया। कृपया जिस कार्यालय के लिए स्लॉट बुक किया गया है वहां जाएं।");
										}
										eForm.setCheck("Y");
										request.setAttribute("checkRegID", eForm);
										forwardJsp = "page1";
									} else {
										if ("en".equalsIgnoreCase(language)) {
											dto.setRegNumber("");
											request.setAttribute("ERR", "No Slot is booked.Kindly book a slot and Come back again.");
											// messages.add("MSG", new
											// ActionMessage(
											// "no.slot.booked"));
										} else {
											dto.setRegNumber("");
											request.setAttribute("ERR", "कोई स्लॉट बुक नहीं  किया गया है| कृपया एक स्लॉट बुक करे और फिर वापस आऍ।");
										}
										saveMessages(request, messages);
										eForm.setCheck("Y");
										request.setAttribute("checkRegID", eForm);
										forwardJsp = "page1";
									}
								}
							}
						}
					}
					if (RegCompConstant.CANCEL_ACTION.equals(actionName)) {
						eForm.setCheckRegNo("");
						resetFields(dto);

						forwardJsp = "welcome";
					}
					if (RegCompConstant.RESET_ACTION.equals(actionName)) {
						eForm.setCheckRegNo("");
						resetFields(dto);
						forwardJsp = "page1";
					}

					// ///Commented by Simran 16 may
					/*
					 * if (RegCompConstant.NEXT_ACTION.equals(actionName)) { //
					 * eForm.setCheckRegNo(""); String regNumber =
					 * dto.getRegNumber();
					 * 
					 * RegCompletionRule rule = new RegCompletionRule();
					 * 
					 * ArrayList errorList = rule
					 * .checkRegistrationNumber(regNumber); if (rule.isError())
					 * { request.setAttribute(RegCompConstant.ERROR_FLAG,
					 * "true"); request.setAttribute(RegCompConstant.ERROR_LIST,
					 * errorList); eForm.setCheckRegNo(""); forwardJsp =
					 * "page1"; } else {
					 * 
					 * } try { regCompDeedList = regmkrBO
					 * .getCompDeedList(regNumber); regPropList = regmkrBD
					 * .getPropertyListByRegId(regNumber); if (regCompDeedList
					 * != null && regCompDeedList.size() > 0) {
					 * eForm.setDeedTxnList(regCompDeedList);
					 * eForm.setPropList(regPropList); forwardJsp =
					 * "deedViewList"; } else { forwardJsp = "page2"; }
					 * request.setAttribute("sessionRegTxnId", regNumber);
					 * eForm.setDeedList(regmkrBO.getDeedType());
					 * eForm.setInstList(new ArrayList()); eForm.setFormList(new
					 * ArrayList()); eForm.setExemList(new ArrayList());
					 * 
					 * dto.setDeedId(""); } catch (Exception e) {
					 * logger.debug(e); }
					 * 
					 * }
					 */
					// end :==commented by simran
				}

				// Commented by simran 16 may
				/*
				 * if (request.getParameter("paymentStatus") != null &&
				 * request.getParameter("parentKey") != null) { String pkey =
				 * (String) request.getParameter("parentKey"); if
				 * (request.getParameter("paymentStatus").equalsIgnoreCase(
				 * "P")) {
				 * 
				 * boolean flg = regmkrBD.updtStatus(pkey, UserId, cdate);
				 * forwardJsp = "success1"; mapping.findForward(forwardJsp); } }
				 */
				// end :==commented by simran
				// Commented by SIMRAN--NO PAYMENT(16 may)
				/*
				 * if (("proceedtopayment").equalsIgnoreCase(formName)) { if
				 * ("NEXT_TO_PAYMENT_ACTION".equalsIgnoreCase(actionName)) {
				 * String a = Double.toString(eForm.getRegDTO()
				 * .getTotalamount()); request.setAttribute("parentModName",
				 * "Registration Process");
				 * request.setAttribute("parentFunName",
				 * "Registration Initiation");
				 * request.setAttribute("parentFunId", "FUN_023");
				 * request.setAttribute("parentAmount", a);
				 * request.setAttribute("parentTable",
				 * "IGRS_REG_COMPLETE_TXN_DETLS");
				 * request.setAttribute("parentKey", eForm.getRegDTO()
				 * .getRegNumber()); request.setAttribute("forwardPath",
				 * "./regcompletemaker.do");
				 * request.setAttribute("parentColumnName", "TRANSACTION_ID");
				 * // end of addition on 13feb for new payment modality.
				 * 
				 * // CHECK FOR PAYMENT_STATUS BEFORE REDIRECTING.
				 * 
				 * String paymentFlag = regmkrBD.getPaymentFlag(dto
				 * .getRegNumber());
				 * logger.debug("----------------->payment flag:-" +
				 * paymentFlag); if (!paymentFlag.equalsIgnoreCase("p") &&
				 * !paymentFlag.equalsIgnoreCase("c")) {
				 * 
				 * if (paymentFlag.equalsIgnoreCase("I")) { forwardJsp =
				 * "regcomppayment"; } else if (paymentFlag.equals(null) ||
				 * paymentFlag.equalsIgnoreCase("")) {
				 * 
				 * // insertion code
				 * 
				 * boolean insertStatus = regmkrBD .insertPaymentDetails(dto
				 * .getRegNumber(), Double.toString(dto .getTotalamount()),
				 * UserId, cdate); logger
				 * .debug("----------------->payment insertion status:-" +
				 * insertStatus); if (insertStatus) { forwardJsp =
				 * "regcomppayment"; } else { forwardJsp = "failure"; }
				 * 
				 * }
				 * 
				 * } else if (paymentFlag.equalsIgnoreCase("p")) { forwardJsp =
				 * "regcomppayment"; } else if
				 * (paymentFlag.equalsIgnoreCase("c")) { forwardJsp =
				 * "success1"; } else { forwardJsp = "failure"; }
				 * 
				 * String ifHold = eForm.getHldBtnFlg();
				 * 
				 * if (!ifHold.equalsIgnoreCase("")) { if
				 * (ifHold.equalsIgnoreCase("hold")) { ; } String hldFlg = "N";
				 * boolean updtHoldTbl = regmkrBD.updtHoldTbl(dto
				 * .getRegNumber(), UserId, cdate, hldFlg);
				 * eForm.setHldBtnFlg(""); }
				 * 
				 * }
				 * 
				 * if ("PAY_LATER".equalsIgnoreCase(actionName)) { // Start===
				 * this code for saving the data in the hold // table String
				 * hldFlag = "Y"; String fwdPage = eForm.formName; String
				 * holdId=""; String holdRemarks="payment Hold"; String str =
				 * regmkrBD.saveHoldData(dto.getRegNumber(), hldFlag, fwdPage,
				 * cdate, UserId, holdId, holdRemarks); // End=== this code for
				 * saving the data in the hold // table if
				 * (str.equalsIgnoreCase("1")) { forwardJsp = "welcome"; } } if
				 * ("CANCEL_ACTION".equalsIgnoreCase(actionName)) { // Start===
				 * this code for saving the data in the hold // table
				 * 
				 * String hldFlag = "Y"; String fwdPage = eForm.formName; String
				 * str = regmkrBD.saveHoldData(dto.getRegNumber(), hldFlag,
				 * fwdPage, cdate, UserId);
				 * 
				 * // End=== this code for saving the data in the hold // table
				 * if (str.equalsIgnoreCase("1")) { forwardJsp = "welcome"; // }
				 * }
				 * 
				 * if ("CONTINUE_HOLD".equalsIgnoreCase(actionName)) {
				 * 
				 * eForm.setHldBtnFlg("hold"); eForm.setCheckHold(""); String
				 * regNum = dto.getRegNumber(); ArrayList getlnkdamt =
				 * regmkrBD.getlnkdamt(dto .getRegNumber()); if
				 * (getlnkdamt.size() != 0) { String str =
				 * getlnkdamt.toString(); str = str.substring(2, (str.length() -
				 * 2)); String[] str1 = str.split(","); String sd = (str1[0]);
				 * String rf = (str1[1]); if (sd == null ||
				 * sd.equalsIgnoreCase("")) { sd = "0"; } if (rf == null ||
				 * rf.equalsIgnoreCase("")) { rf = "0"; }
				 * 
				 * // if(sd.equalsIgnoreCase("0") && //
				 * rf.equalsIgnoreCase("0")) if (sd.equalsIgnoreCase("0")) {
				 * forwardJsp = "success1"; } else { double regduty =
				 * Double.parseDouble(sd); double regfee =
				 * Double.parseDouble(rf); // commented on 4/1/2013 as it was
				 * communicated // only duty will be paid in maker // double
				 * total = regduty +regfee; double total = regduty;
				 * logger.debug("data" + regduty + regfee + total);
				 * 
				 * dto.setRegfee(regfee); dto.setStampduty(regduty);
				 * dto.setTotalamount(total); eForm.setRegDTO(dto);
				 * 
				 * forwardJsp = "proceedtopayment"; } }
				 * eForm.setCommonDTO(cdto);
				 * 
				 * }
				 * 
				 * }
				 */
				// end :== commented by Simran
				// $2 End: code for Page1.jsp
				// below code to redirect to Linking page in case there is an
				// already paid duty or
				// else to witness page
				if ("displayExtra".equals(request.getParameter("okClick"))) {
					logger.debug("*************OK CLICK***********");
					eForm.setCheckRegNo("W");
					RegCommonForm regcommonForm = new RegCommonForm();
					regcommonForm.setActionName("CONFIRMATION_OK_ACTION");
					regcommonForm.setFromModule("RegCompMaker");
					regcommonForm.setHidnRegTxnId(eForm.getRegDTO().getRegNumber());
					regcommonForm.setCheckRegNo("W");
					ArrayList deedDocList = regmkrBD.getDeedDocDetails(dto.getRegNumber());
					for (int i = 0; i < deedDocList.size(); i++) {
						ArrayList subList = (ArrayList) deedDocList.get(i);
						String deedDocPath = (String) subList.get(1);
						if (deedDocPath == null || deedDocPath.equals(""))
							regcommonForm.setDeedDoc("N");
						else
							regcommonForm.setDeedDoc("Y");
					}
					if (deedDocList.size() == 0)
						regcommonForm.setDeedDoc("N");
					request.setAttribute("regForm", regcommonForm);
					forwardJsp = "redirect";
				}

				if ("CONFIRMATION_NO_VALUATION".equals(request.getParameter("okClick"))) {
					logger.debug("*************OK CLICK--CONFIRMATION_OK_ACTION_NO_VALUATION***********");
					eForm.setCheckRegNo("W");
					RegCommonForm regcommonForm = new RegCommonForm();
					regcommonForm.setActionName("CONFIRMATION_OK_ACTION_NO_VALUATION");
					regcommonForm.setFromModule("RegCompMaker");
					regcommonForm.setHidnRegTxnId(eForm.getRegDTO().getRegNumber());
					regcommonForm.setCheckRegNo("W");
					ArrayList deedDocList = regmkrBD.getDeedDocDetails(dto.getRegNumber());
					for (int i = 0; i < deedDocList.size(); i++) {
						ArrayList subList = (ArrayList) deedDocList.get(i);
						String deedDocPath = (String) subList.get(1);
						if (deedDocPath == null || deedDocPath.equals(""))
							regcommonForm.setDeedDoc("N");
						else
							regcommonForm.setDeedDoc("Y");
					}
					if (deedDocList.size() == 0)
						regcommonForm.setDeedDoc("N");
					request.setAttribute("regForm", regcommonForm);
					forwardJsp = "redirect";
				}
				if ("CONFIRMATION_OK_ACTION".equals(request.getParameter("okClick"))) {
					logger.debug("*************OK CLICK***********");
					eForm.setCheckRegNo("W");
					RegCommonForm regcommonForm = new RegCommonForm();
					regcommonForm.setActionName("CONFIRMATION_OK_ACTION");
					regcommonForm.setFromModule("RegCompMaker");
					regcommonForm.setHidnRegTxnId(eForm.getRegDTO().getRegNumber());
					regcommonForm.setCheckRegNo("W");
					ArrayList deedDocList = regmkrBD.getDeedDocDetails(dto.getRegNumber());
					for (int i = 0; i < deedDocList.size(); i++) {
						ArrayList subList = (ArrayList) deedDocList.get(i);
						String deedDocPath = (String) subList.get(1);
						if (deedDocPath == null || deedDocPath.equals(""))
							regcommonForm.setDeedDoc("N");
						else
							regcommonForm.setDeedDoc("Y");
					}
					if (deedDocList.size() == 0)
						regcommonForm.setDeedDoc("N");
					request.setAttribute("regForm", regcommonForm);
					forwardJsp = "redirect";
				}
				if ("LINK_HISTORY".equalsIgnoreCase(request.getParameter("okClick"))) {
					forwardJsp = "linkingHistory";
				}
				if ("CHECKLIST".equalsIgnoreCase(request.getParameter("okClick"))) {
					forwardJsp = "checklist";
				}
				if ("back".equals(param)) {
					String regNum = dto.getRegNumber();

					// *****to insert deed doc fiename and
					// Path*******************//
					logger.debug("********deed*******" + eForm.getRegDTO().getParentPathScan());
					// boolean flag = regmkrBD.insertDeedDocDetails(dto,UserId);
					// if(flag)
					// {
					// RegCompleteMakerDTO dto1 = new RegCompleteMakerDTO();
					RegCompleteDutiesDTO dutydto = new RegCompleteDutiesDTO();
					// regmkrBD.getTotalDuties(regNum);
					eForm.setDutyDTO(dutydto);
					eForm.setFormName("");
					eForm.setActionName("");
					// eForm.setRegDTO(dto1);
					String str = dto.getRegNumber();
					// redirect to linking payment only if there
					// are any balance stamp duties else redirect to next page
					boolean checkalrdyPaidDuty = regmkrBD.checkalrdyPaidDuty(dto.getRegNumber());
					if (checkalrdyPaidDuty) {

						actionName = "NEXT_ACTION";
						if (RegCompConstant.NEXT_ACTION.equals(actionName)) {
							ArrayList propIdList = regmkrBD.getpropIdList(str);
							if (propIdList.size() != 0) {
								eForm.setPropIdList(propIdList);
								for (int i = 0; i < propIdList.size(); i++) {
									System.out.println(propIdList.get(i).toString());
								}
								boolean flag = regmkrBD.UpdateRegistrationCompletionStatus(dto.getRegNumber(), RegCompConstant.LINKING_PAYMENT_STATUS, cdate, UserId);
								if (flag) {
									forwardJsp = "linkingPayment";
								} else {
									session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
									forwardJsp = "failuremaker";
								}
							}
						}

					} else {
						boolean flag = regmkrBD.UpdateRegistrationCompletionStatus(dto.getRegNumber(), RegCompConstant.WITNESS_DETAILS_STATUS, cdate, UserId);
						if (flag) {
							eForm.setRelationshipList(regmkrBD.getRelationshipList("M", language));
							forwardJsp = "witnessDet2";
						} else {
							session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
							forwardJsp = "failuremaker";
						}
					}

					/*
					 * } else { messages.add("MSG", new ActionMessage(
					 * "msg_deed_doc_error")); saveMessages(request, messages);
					 * eForm.setCheck("DEEDDOC");
					 * 
					 * }
					 */

					return mapping.findForward(forwardJsp);
				}
				if ("CHANGE_DEED_DOC".equalsIgnoreCase(param)) {
					logger.debug("**********Scan deed doc***********");
					String deedId = dto.getDeedID();
					String instId = dto.getInstrumentId();
					dto.setDeedDoc("N");
					RegCommonForm regcommonForm = new RegCommonForm();
					regcommonForm.setDeedDoc("N");
					int deed = Integer.parseInt(deedId);
					int inst = Integer.parseInt(instId);
					request.setAttribute("deedId", deedId);
					request.setAttribute("instId", instId);
					dto.setDeedDocName(RegCompConstant.FILE_NAME_DEED_DOC);
					dto.setDeedDocPath(FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_DEED_DOC + RegCompConstant.FILE_NAME_DEED_DOC);
					// UPLOAD DEED DOC IN DMS - 27 feb
					/*
					 * try { logger.debug("try uploading deed doc");
					 * ReadPropertiesFile prop = new ReadPropertiesFile();
					 * 
					 * PropertiesFileReader pr =
					 * PropertiesFileReader.getInstance("resources.igrs");
					 * 
					 * DocumentOperations dos = new DocumentOperations();
					 * ODServerDetails ods = new ODServerDetails(); Dataclass dc
					 * = new Dataclass();
					 * ods.setAppServerIp(pr.getValue("AppServerIp"));
					 * ods.setCabinetName(pr.getValue("CabinetName"));
					 * ods.setAppServerUserId(pr.getValue("AppServerUserId"));
					 * ods
					 * .setAppServerUserPass(pr.getValue("AppServerUserPass"));
					 * ods.setImageServerIp(pr.getValue("ImageServerIP"));
					 * ods.setImageServerPort(pr.getValue("ImageServerPort"));
					 * dc.setUniqueNo(dto.getRegNumber());
					 * logger.debug("try uploading deed doc"); File file = new
					 * File(dto.getDeedDocPath());
					 * dc.setDataclassName(pr.getValue("IGRSDataclass"));
					 * logger.debug("try uploading deed doc"); String docId =
					 * dos.uploadDocument(ods, file, "IGRS", true, dc);
					 * logger.debug("^^^^^^^^^^^^^^^DOC ID"+docId);
					 * 
					 * } catch(Exception e) {
					 * 
					 * }
					 */
					if (deed == RegInitConstant.DEED_ADOPTION || (deed == RegInitConstant.DEED_TRUST && inst == RegInitConstant.INST_TRUST_NPV_P) || deed == RegInitConstant.DEED_CANCELLATION_OF_WILL_POA || deed == RegInitConstant.DEED_AGREEMENT_MEMO_NPV || deed == RegInitConstant.DEED_AGREEMENT_MEMO_NPV_LOAN || dto.getCommonDeedFlag() == 1) // deeds
					// not
					// requiring
					// property
					// valuation
					{
						logger.debug("----------->third category");
						eForm.setRegDTO(dto);
						System.out.println(dto.getAge());
						eForm.setCheckRegNo("W");
						regcommonForm = new RegCommonForm();
						regcommonForm.setDeedDocName(RegCompConstant.FILE_NAME_DEED_DOC);
						regcommonForm.setDeedDocPath(FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_DEED_DOC + RegCompConstant.FILE_NAME_DEED_DOC);
						regcommonForm.setActionName("CONFIRMATION_OK_ACTION_NO_VALUATION");
						regcommonForm.setFromModule("RegCompMaker");
						regcommonForm.setHidnRegTxnId(eForm.getRegDTO().getRegNumber());
						regcommonForm.setCheckRegNo("W");
						regcommonForm.getRegDTO().setDeedDoc("N");
						regcommonForm.setDeedDoc("N");
						regcommonForm.setDeedStat("4");
						request.setAttribute("regForm", regcommonForm);
						forwardJsp = "redirect";
					}

					else if (deedId.equals(RegInitConstant.DEED_DEPOSIT_OF_TITLE) || deedId.equals(RegInitConstant.DEED_SURRENDER_LEASE_NPV) || deedId.equals(RegInitConstant.DEED_RECONV_MORTGAGE_NPV) || deedId.equals(RegInitConstant.DEED_MORTGAGE_NPV) || deedId.equals(RegInitConstant.DEED_POA) || deedId.equals(RegInitConstant.DEED_TRANSFER_LEASE_NPV) || deedId.equals(RegInitConstant.DEED_PARTITION_NPV) || deedId.equals(RegInitConstant.DEED_PARTNERSHIP_NPV) || deedId.equals(RegInitConstant.DEED_LEASE_NPV) || deedId.equals(RegInitConstant.DEED_WILL_NPV) || deedId.equals(RegInitConstant.DEED_SETTLEMENT_NPV) || (deedId.equals(RegInitConstant.DEED_TRUST) && instId.equals(RegInitConstant.INST_TRUST_NPV_NP))) {
						logger.debug("----------->third category");
						eForm.setRegDTO(dto);
						System.out.println(dto.getAge());
						eForm.setCheckRegNo("W");
						regcommonForm = new RegCommonForm();
						regcommonForm.setDeedDocName(RegCompConstant.FILE_NAME_DEED_DOC);
						regcommonForm.setDeedDocPath(FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_DEED_DOC + RegCompConstant.FILE_NAME_DEED_DOC);
						regcommonForm.setActionName("CONFIRMATION_OK_ACTION_NO_VALUATION");
						regcommonForm.setFromModule("RegCompMaker");
						regcommonForm.setHidnRegTxnId(eForm.getRegDTO().getRegNumber());
						regcommonForm.getRegDTO().setDeedDoc("N");
						regcommonForm.setDeedDoc("N");
						regcommonForm.setCheckRegNo("W");
						regcommonForm.setDeedStat("4");
						request.setAttribute("regForm", regcommonForm);
						forwardJsp = "redirect";
					}

					else // property valuation
					{
						eForm.setRegDTO(dto);
						System.out.println(dto.getAge());
						eForm.setCheckRegNo("W");
						regcommonForm = new RegCommonForm();
						regcommonForm.getRegDTO().setDeedDoc("N");
						regcommonForm.setDeedDoc("Y");
						regcommonForm.setDeedDocName(RegCompConstant.FILE_NAME_DEED_DOC);
						regcommonForm.setDeedDocPath(FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_DEED_DOC + RegCompConstant.FILE_NAME_DEED_DOC);
						regcommonForm.setActionName("CONFIRMATION_OK_ACTION");
						regcommonForm.setFromModule("RegCompMaker");
						regcommonForm.setHidnRegTxnId(eForm.getRegDTO().getRegNumber());
						regcommonForm.setCheckRegNo("W");
						regcommonForm.setDeedDoc("N");
						regcommonForm.setDeedStat("4");
						request.setAttribute("regForm", regcommonForm);
						forwardJsp = "redirect";
					}

				}

				if ("popup".equals(param)) {
					// RegCompleteMakerDTO dto1 = eForm.getRegDTO();
					eForm.setFormName("");
					eForm.setActionName("");
					cdto.setRadioVal("");
					cdto.setLinkedPhysclStmpNum("");
					cdto.setOldRegistrationDate("");
					cdto.setOldRegistrationNumber("");
					eForm.getRegDTO().setLinkCheck("");
					eForm.getRegDTO().setLinkingFlag("");
					eForm.getRegDTO().setDenotingCheck("");
					eForm.getRegDTO().setDenotingFlag("");
					eForm.setCommonDTO(cdto);
					// eForm.setRegDTO(dto1);
					//System.out.println("hidden id in action"+dto1.getHidnPropId
					// ());
					forwardJsp = "linkingPaymentPopUp";
					return mapping.findForward(forwardJsp);
				}

				if ("SCAN_DEED_DOC".equalsIgnoreCase(actionName)) {

					logger.debug("**********Scan deed doc***********");
					String deedId = dto.getDeedID();
					String instId = dto.getInstrumentId();
					int deed = Integer.parseInt(deedId);
					int inst = Integer.parseInt(instId);
					dto.setDeedDoc("Y");
					RegCommonForm regcommonForm = new RegCommonForm();
					regcommonForm.setDeedDoc("Y");
					request.setAttribute("deedId", deedId);
					request.setAttribute("instId", instId);
					dto.setDeedDocName(RegCompConstant.FILE_NAME_DEED_DOC);
					dto.setDeedDocPath(FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_DEED_DOC + RegCompConstant.FILE_NAME_DEED_DOC);
					// UPLOAD DEED DOC IN DMS - 27 feb
					/*
					 * try {
					 * 
					 * ReadPropertiesFile prop = new ReadPropertiesFile();
					 * 
					 * PropertiesFileReader pr =
					 * PropertiesFileReader.getInstance("resources.igrs");
					 * 
					 * DocumentOperations dos = new DocumentOperations();
					 * ODServerDetails ods = new ODServerDetails(); Dataclass dc
					 * = new Dataclass();
					 * ods.setAppServerIp(pr.getValue("AppServerIp"));
					 * ods.setCabinetName(pr.getValue("CabinetName"));
					 * ods.setAppServerUserId(pr.getValue("AppServerUserId"));
					 * ods
					 * .setAppServerUserPass(pr.getValue("AppServerUserPass"));
					 * ods.setImageServerIp(pr.getValue("ImageServerIP"));
					 * ods.setImageServerPort(pr.getValue("ImageServerPort"));
					 * dc.setUniqueNo(dto.getRegNumber()); File file = new
					 * File(dto.getDeedDocPath());
					 * dc.setDataclassName(pr.getValue("IGRSDataclass")); String
					 * docId = dos.uploadDocument(ods, file, "IGRS", true, dc);
					 * logger.debug("^^^^^^^^^^^^^^^DOC ID"+docId);
					 * 
					 * } catch(Exception e) {
					 * 
					 * }
					 */
					if (deed == RegInitConstant.DEED_ADOPTION || (deed == RegInitConstant.DEED_TRUST && inst == RegInitConstant.INST_TRUST_NPV_P) || deed == RegInitConstant.DEED_CANCELLATION_OF_WILL_POA || deed == RegInitConstant.DEED_AGREEMENT_MEMO_NPV || deed == RegInitConstant.DEED_AGREEMENT_MEMO_NPV_LOAN || dto.getCommonDeedFlag() == 1) // deeds
					// not
					// requiring
					// property
					// valuation
					{
						logger.debug("----------->third category");
						eForm.setRegDTO(dto);
						System.out.println(dto.getAge());
						eForm.setCheckRegNo("W");
						regcommonForm = new RegCommonForm();
						regcommonForm.setActionName("CONFIRMATION_OK_ACTION_NO_VALUATION");
						regcommonForm.setFromModule("RegCompMaker");
						regcommonForm.setHidnRegTxnId(eForm.getRegDTO().getRegNumber());
						regcommonForm.setCheckRegNo("W");
						regcommonForm.setDeedDoc("Y");
						request.setAttribute("regForm", regcommonForm);

						forwardJsp = "redirect";
					}

					else if (deedId.equals(RegInitConstant.DEED_DEPOSIT_OF_TITLE) || deedId.equals(RegInitConstant.DEED_SURRENDER_LEASE_NPV) || deedId.equals(RegInitConstant.DEED_RECONV_MORTGAGE_NPV) || deedId.equals(RegInitConstant.DEED_MORTGAGE_NPV) || deedId.equals(RegInitConstant.DEED_POA) || deedId.equals(RegInitConstant.DEED_TRANSFER_LEASE_NPV) || deedId.equals(RegInitConstant.DEED_PARTITION_NPV) || deedId.equals(RegInitConstant.DEED_PARTNERSHIP_NPV) || deedId.equals(RegInitConstant.DEED_LEASE_NPV) || deedId.equals(RegInitConstant.DEED_WILL_NPV) || deedId.equals(RegInitConstant.DEED_SETTLEMENT_NPV) || (deedId.equals(RegInitConstant.DEED_TRUST) && instId.equals(RegInitConstant.INST_TRUST_NPV_NP))) {
						logger.debug("----------->third category");
						eForm.setRegDTO(dto);
						System.out.println(dto.getAge());
						eForm.setCheckRegNo("W");
						regcommonForm = new RegCommonForm();
						regcommonForm.setActionName("CONFIRMATION_OK_ACTION_NO_VALUATION");
						regcommonForm.setFromModule("RegCompMaker");
						regcommonForm.setHidnRegTxnId(eForm.getRegDTO().getRegNumber());
						regcommonForm.setCheckRegNo("W");
						regcommonForm.setDeedDoc("Y");
						request.setAttribute("regForm", regcommonForm);
						forwardJsp = "redirect";
					}

					else // property valuation
					{
						eForm.setRegDTO(dto);
						System.out.println(dto.getAge());
						eForm.setCheckRegNo("W");
						regcommonForm = new RegCommonForm();
						regcommonForm.setActionName("CONFIRMATION_OK_ACTION");
						regcommonForm.setFromModule("RegCompMaker");
						regcommonForm.setHidnRegTxnId(eForm.getRegDTO().getRegNumber());
						regcommonForm.setCheckRegNo("W");
						regcommonForm.setDeedDoc("Y");
						request.setAttribute("regForm", regcommonForm);
						forwardJsp = "redirect";
					}
				}

				// for linking the payment in case of only sale deed/lease deed

				if (RegCompConstant.STAMP_DUTY_PAGE.equals(formName)) {
					String str = dto.getRegNumber();

					if (RegCompConstant.NEXT_ACTION.equals(actionName)) {
						ArrayList propIdList = regmkrBD.getpropIdList(str);
						if (propIdList.size() != 0) {
							eForm.setPropIdList(propIdList);
							for (int i = 0; i < propIdList.size(); i++) {
								System.out.println(propIdList.get(i).toString());
							}
							forwardJsp = "linkingPayment";
						}
					}
				}

				if (RegCompConstant.LINKING_PAYMENT_POPUP_PAGE.equals(formName)) {

					if (RegCompConstant.RESET_ACTION.equals(actionName)) {
						eForm.setCheckVal("");
						eForm.setErrLnkFlg("");
						forwardJsp = "linkingPaymentPopUp";
					} else if (RegCompConstant.BACK_ACTION.equals(actionName)) {
						forwardJsp = "linkingPayment";
					} else {

						String num = "";
						String searchBy = "";
						String regNum = dto.getRegNumber();
						String hidnPropId = dto.getHidnPropId();
						String compNumber = cdto.getLinkedRegNum();
						System.out.println("property id" + hidnPropId);

						// Following code added by Simran
						ArrayList alrdyPaidDuty = regmkrBD.getAlrdyPaidDuty(dto.getRegNumber());
						for (int i = 0; i < alrdyPaidDuty.size(); i++) {
							RegCompleteMakerDTO rDTO = (RegCompleteMakerDTO) alrdyPaidDuty.get(i);
							dto.setDutyAlreadyPaid(rDTO.getLinkedTotalDuty());
							dto.setRegFeeAlreadyPaid(rDTO.getLinkedTotalRegFee());
						}

						if (RegCompConstant.SEARCH_BY_REGNUM.equals(actionName)) {
							// cdto=eForm.getCommonDTO();

							System.out.println(num);
							eForm.setErrLnkFlg("");

							/****
							 * Start added on 11.4.2013 for changing search on
							 * registration initiation number to registration
							 * completion number
							 * ****/
							// this number is completion number
							compNumber = cdto.getLinkedRegNum();
							// Code for respective initiation number

							// code for getting registration initiation number
							num = regmkrBD.getreginitNumber(compNumber);
							/** end **/
							boolean tempFlag = true;

							// Start :commented on 11.4.2013
							/*
							 * if (regNum.equalsIgnoreCase(num) ) { tempFlag =
							 * false; // write message here messages.add("MSG1",
							 * new ActionMessage( "linking_error"));
							 * saveMessages(request, messages);
							 * eForm.setErrLnkFlg("E"); forwardJsp =
							 * "linkingPaymentPopUp"; return
							 * mapping.findForward(forwardJsp); }
							 */

							// end commented on 11.4.2013
							// Start : added if no regnumber is found
							if (num.equalsIgnoreCase("") || num.equalsIgnoreCase(null)) {
								tempFlag = false;
								// write message here
								if ("en".equalsIgnoreCase(language)) {
									request.setAttribute("MSG", "Please enter a valid registration number.");
								} else {
									request.setAttribute("MSG", "कृपया एक वैध पंजीकरण संख्या दर्ज करें|");
								}
								messages.add("MSG", new ActionMessage("no_link_record"));
								saveMessages(request, messages);
								eForm.setErrLnkFlg("E");

								forwardJsp = "linkingPaymentPopUp";
								return mapping.findForward(forwardJsp);
							}
							// end : added if no regnumber is found

							System.out.println("reg number is" + num);
							searchBy = RegCompConstant.SEARCH_BY_REGNUM;

							System.out.println(num);
							String key = hidnPropId;
							System.out.println("key is" + key);

							// Code for checking if the linked Document has
							// already
							// been consumed.
							boolean checkConsumedDoc = false;
							// ADDED BY SIMRAN
							if (dto.getChkChecker() == "Y") {
								checkConsumedDoc = regmkrBD.getConsumedDocChecker(compNumber, searchBy);
							} else {
								// checkConsumedDoc =
								// regmkrBD.getConsumedDoc(num,searchBy);
								checkConsumedDoc = regmkrBD.getConsumedDoc(compNumber, searchBy);
							}
							// END ADDED BY SIMRAN
							System.out.println("flag" + checkConsumedDoc);
							if (checkConsumedDoc) {
								messages.add("MSG", new ActionMessage("document.already.linked"));
								saveMessages(request, messages);
								eForm.setCheckVal("Y");
								forwardJsp = "linkingPaymentPopUp";
							}

							else {
								// this list gets amounts corresponding to the
								// present number
								// passed to it. This needs to be appended to
								// the
								// old list

								// BELOW CODE COMMENTED BY SIMRAN
								// ArrayList alrdyPaidDuty = regmkrBD
								// .getAlrdyPaidDuty(dto.getRegNumber());

								// ArrayList alrdyPaidDuty = new Array
								/*
								 * CommonMkrDTO cdto1 = new CommonMkrDTO();
								 * cdto1.setLinkedTotalDuty(400.50);
								 * cdto1.setLinkedTotalRegFee(700.60);
								 * alrdyPaidDuty.add(cdto1);
								 */

								// //////////////////////////////BELOW CODE
								// COMMENTED BY
								// SIMRAN//////////////////////////////////
								/*
								 * if (mymap.containsKey(key)) { ArrayList
								 * linkedAmtList = regmkrBD .getLinkedAmt(num,
								 * searchBy);
								 * 
								 * retainList = new ArrayList(); retainList =
								 * (ArrayList) mymap.get(key); //
								 * retainList.add(linkedAmtList.get(0)); //
								 * Collection c =linkedAmtList;
								 * retainList.addAll((linkedAmtList));
								 * eForm.setLinkedAmtList(retainList);
								 * System.out.println(retainList); Collection c
								 * = new ArrayList(); // mymap.put(key,
								 * eForm.getLinkedAmtList()); mymap.put(key,
								 * retainList); System.out.println("1"); //
								 * eForm.setLinkedAmtList(new ArrayList()); }
								 * else { retainList = new ArrayList(); //
								 * retainList.add(linkedAmtList.get(0)); // will
								 * contain already paid duty
								 * retainList.addAll(alrdyPaidDuty);
								 * 
								 * ArrayList linkedAmtList = regmkrBD
								 * .getLinkedAmt(num, searchBy);
								 * retainList.addAll((linkedAmtList)); //
								 * eForm.setLinkedAmtList( retainList); //
								 * mymap.put(key, eForm.getLinkedAmtList());
								 * mymap.put(key, retainList); //
								 * eForm.setLinkedAmtList(new ArrayList());
								 * System.out.println("2"); }
								 */
								///////////////////////END//////////////////////
								// ///////////////////
								ArrayList linkedAmtList = regmkrBD.getLinkedAmt(num, searchBy);
								mymap.put(key, linkedAmtList);

								eForm.setMyMap(mymap);
								eForm.setCheck("X");
								/*
								 * Map testMap= new HashMap();
								 * testMap.put("101201300000085", 200);
								 * testMap.put("101201300000084", 400);
								 * request.setAttribute("testmap", testMap);
								 * request.setAttribute("mymap", mymap);
								 * eForm.setAmtMap(testMap);
								 */
								forwardJsp = "linkingPayment";

							}

						} else if (RegCompConstant.SEARCH_BY_ESTAMP.equals(actionName)) {
							String estampCode = cdto.getLinkedEstampNum();
							String estampTxnId = regmkrBD.getEstampTxnId(estampCode);
							if (estampTxnId.equals("") || estampTxnId.equals(null)) {
								if ("en".equalsIgnoreCase(language)) {
									request.setAttribute("MSG", "Please enter a valid estamp number.");
								} else {
									request.setAttribute("MSG", "कृपया एक वैध ई-मुद्रांक संख्या दर्ज करें|");
								}
								messages.add("MSG", new ActionMessage("no_link_record"));
								saveMessages(request, messages);
								eForm.setErrLnkFlg("E");

								forwardJsp = "linkingPaymentPopUp";
								return mapping.findForward(forwardJsp);
							}

							// TODO :- chk if reg is done....
							String regNumber = regmkrBD.getRegInitNumber(estampCode);
							if (regNumber.equals("") || regNumber.equals(null)) {
								searchBy = RegCompConstant.SEARCH_BY_ESTAMP;
								String key = hidnPropId;
								System.out.println("key is" + key);

								// Code for checking if the linked Document has
								// already
								// been consumed.

								boolean checkForConsumedEstamp = false;

								checkForConsumedEstamp = regmkrBD.getConsumedEstamp(estampCode);

								if (checkForConsumedEstamp) {

									if ("en".equalsIgnoreCase(language)) {
										request.setAttribute("MSG", "This Estamp cannot be linked");
									} else {
										request.setAttribute("MSG", "यह ई-मुद्रांक लिंक नहीं किया जा सकता है");
									}
									messages.add("MSG", new ActionMessage("no_link_record"));
									saveMessages(request, messages);
									eForm.setErrLnkFlg("E");

									forwardJsp = "linkingPaymentPopUp";
									return mapping.findForward(forwardJsp);

								}

								boolean checkConsumedDoc = false;
								// ADDED BY SIMRAN
								if (dto.getChkChecker() == "Y") {
									checkConsumedDoc = regmkrBD.getConsumedDocChecker(estampCode, searchBy);
								} else {
									// checkConsumedDoc =
									// regmkrBD.getConsumedDoc(num,searchBy);
									checkConsumedDoc = regmkrBD.getConsumedDoc(estampCode, searchBy);
								}
								// END ADDED BY SIMRAN
								System.out.println("flag" + checkConsumedDoc);
								if (checkConsumedDoc) {
									messages.add("MSG", new ActionMessage("document.already.linked"));
									saveMessages(request, messages);
									eForm.setCheckVal("Y");
									forwardJsp = "linkingPaymentPopUp";
								}

								else {
									// this list gets amounts corresponding to
									// the

									ArrayList linkedAmtList = regmkrBD.getLinkedAmt(estampCode, searchBy);
									mymap.put(key, linkedAmtList);

									eForm.setMyMap(mymap);
									eForm.setCheck("X");

									forwardJsp = "linkingPayment";

								}
							} else {
								// linking by reg Number
								if ("en".equalsIgnoreCase(language)) {
									request.setAttribute("MSG", "This Estamp cannot be linked");
								} else {
									request.setAttribute("MSG", "यह ई-मुद्रांक लिंक नहीं किया जा सकता है");
								}
								messages.add("MSG", new ActionMessage("no_link_record"));
								saveMessages(request, messages);
								eForm.setErrLnkFlg("E");

								forwardJsp = "linkingPaymentPopUp";
								return mapping.findForward(forwardJsp);

							}

						} else if (RegCompConstant.SEARCH_BY_PHYSTAMP.equals(actionName)) {
							String linkedPhysclStmpNum = cdto.getLinkedPhysclStmpNum();
							searchBy = RegCompConstant.SEARCH_BY_PHYSTAMP;
							ArrayList linkedAmtList = regmkrBD.getLinkedAmt(linkedPhysclStmpNum, searchBy);
							String key = hidnPropId;
							mymap.put(key, linkedAmtList);

							eForm.setMyMap(mymap);
							eForm.setCheck("X");

							forwardJsp = "linkingPayment";
							// forwardJsp = "linkingPayment";
							// num = cdto.getLinkedPhysclStmpNum();
							// searchBy = RegCompConstant.SEARCH_BY_PHYSTAMP;
						} else if (RegCompConstant.SEARCH_BY_OLD_REG.equals(actionName)) {
							String oldRegNumber = cdto.getOldRegistrationNumber();
							String date = cdto.getOldRegistrationDate();
							eForm.getRegDTO().setOldRegistrationDate(date);
							searchBy = RegCompConstant.SEARCH_BY_OLD_REG;
							ArrayList linkedAmtList = regmkrBD.getLinkedAmt(oldRegNumber, searchBy);
							String key = hidnPropId;
							mymap.put(key, linkedAmtList);

							eForm.setMyMap(mymap);
							eForm.setCheck("X");

							forwardJsp = "linkingPayment";
							// forwardJsp = "linkingPayment";
							// num = cdto.getLinkedPhysclStmpNum();
							// searchBy = RegCompConstant.SEARCH_BY_PHYSTAMP;
						}

					}
				}
				// code for saving the map

				if ("linkingpayment".equalsIgnoreCase(formName)) {
					// String checkHold = eForm.getCheckHold();
					if (RegCompConstant.SAVE_LINKED_AMOUNTS.equalsIgnoreCase(actionName)) {
						String regNum = dto.getRegNumber();

						String compRegNumber = cdto.getLinkedRegNum();
						ArrayList linkedDuties = new ArrayList();
						String arr[] = dto.getRegStampArr().split(",");

						for (int i = 0; i < arr.length; i++) {
							String[] reg_duty = arr[i].split("~");
							RegCompleteMakerDTO rDTO = new RegCompleteMakerDTO();
							// logger.debug("PROPERTY------>"+reg_duty[0]);
							// logger.debug("regFee---->"+reg_duty[1]);
							// logger.debug("stampDuty---->"+reg_duty[2]);
							linkedDuties = (ArrayList) mymap.get(reg_duty[0]);
							for (int k = 0; k < linkedDuties.size(); k++) {
								CommonMkrDTO cdto1 = (CommonMkrDTO) linkedDuties.get(k);
								// logger.debug("<----"+cdto1.getLinkedFlag());
								// logger.debug("<---"+cdto1.getLinkedNum());
								//logger.debug("<---"+cdto1.getLinkedTotalDuty()
								// );
								//logger.debug("<---"+cdto1.getLinkedTotalRegFee
								// ());
								cdto1.setLinkedTotalDuty(Double.parseDouble(reg_duty[2]));
								cdto1.setLinkedTotalRegFee(Double.parseDouble(reg_duty[1]));

							}
							// rDTO.setRegfee(Double.parseDouble(reg_duty[1]));
							//rDTO.setStampduty(Double.parseDouble(reg_duty[2]))
							// ;
							// linkedDuties.add(rDTO);
							// mymap.put(reg_duty[0],linkedDuties);
						}

						double balDuty = cdto.getTempDuty();
						double balfee = cdto.getTempFee();
						boolean tmpflg = regmkrBD.checkAlrdyInsertdRegID(regNum, UserId, cdate);
						// ////ADDED BY SIMRAN////////////////
						// logger.debug("<------flag "+tmpflg);
						if (tmpflg) {
							boolean flag = regmkrBD.saveLinkedAmt(regNum, mymap, balDuty, balfee, compRegNumber, UserId, cdate, eForm.getRegDTO());
							if (flag) {
								messages.add("MSG", new ActionMessage("save.success"));
								saveMessages(request, messages);
								eForm.setCheck("X");
								eForm.setCheck("X");
								request.setAttribute("checkRegID", eForm);
								eForm.setRelationshipList(regmkrBD.getRelationshipList("M", language));
								regmkrBD.UpdateRegistrationCompletionStatus(dto.getRegNumber(), RegCompConstant.WITNESS_DETAILS_STATUS, cdate, UserId);
								forwardJsp = "witnessDet2";
								// forwardJsp = "linkingPayment";
							} else {
								session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
								forwardJsp = "failuremaker";
							}

						} else {
							session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
							forwardJsp = "failuremaker";
						}

					}
					if ("CONTINUE_HOLD".equalsIgnoreCase(actionName)) {

						eForm.setHldBtnFlg("hold");
						eForm.setCheckHold("");
						String regNum = dto.getRegNumber();
						cdto = (CommonMkrDTO) regmkrBD.getLnkdPmnt(regNum, cdto);
						eForm.setCommonDTO(cdto);
						forwardJsp = "linkingPayment";
					}
					if (RegCompConstant.NEXT_ACTION.equals(actionName)) {
						String regNum = dto.getRegNumber();
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String updtDate = sdf.format(new Date());
						String hldFlg = "N";
						boolean updtHoldTbl = regmkrBD.updtHoldTbl(regNum, UserId, updtDate, hldFlg);
						eForm.setHldBtnFlg("");// to show that hold has been
						// resumed after linking payment
						// page
						eForm.setRelationshipList(regmkrBD.getRelationshipList("M", language));
						regmkrBD.UpdateRegistrationCompletionStatus(dto.getRegNumber(), RegCompConstant.WITNESS_DETAILS_STATUS, cdate, UserId);
						forwardJsp = "witnessDet2";
					}

					if (RegCompConstant.HOLD_ACTION.equalsIgnoreCase(actionName)) {
						// ArrayList getHoldList = regmkrBD.getHoldData(); //
						// commented by Simran
						eForm.setChkHoldClick("C"); // c stands for hold buttons
						// clicked
						// eForm.setHoldListDisp(getHoldList);
						System.out.println(dto.getHoldName());
						forwardJsp = "linkingPayment";
					}

					if (RegCompConstant.SAVE_HOLD_ACTION.equalsIgnoreCase(actionName)) {
						// Start:== this code for saving the data in the Map
						String regNum = dto.getRegNumber();
						double balDuty = cdto.getTempDuty();
						double balfee = cdto.getTempFee();

						ArrayList linkedDuties = new ArrayList();
						String arr[] = dto.getRegStampArr().split(",");

						for (int i = 0; i < arr.length; i++) {
							String[] reg_duty = arr[i].split("~");
							RegCompleteMakerDTO rDTO = new RegCompleteMakerDTO();
							// logger.debug("PROPERTY------>"+reg_duty[0]);
							// logger.debug("regFee---->"+reg_duty[1]);
							// logger.debug("stampDuty---->"+reg_duty[2]);
							linkedDuties = (ArrayList) mymap.get(reg_duty[0]);
							for (int k = 0; k < linkedDuties.size(); k++) {
								CommonMkrDTO cdto1 = (CommonMkrDTO) linkedDuties.get(k);
								// logger.debug("<----"+cdto1.getLinkedFlag());
								// logger.debug("<---"+cdto1.getLinkedNum());
								//logger.debug("<---"+cdto1.getLinkedTotalDuty()
								// );
								//logger.debug("<---"+cdto1.getLinkedTotalRegFee
								// ());
								cdto1.setLinkedTotalDuty(Double.parseDouble(reg_duty[2]));
								cdto1.setLinkedTotalRegFee(Double.parseDouble(reg_duty[1]));

							}
						}
						// String holdId= dto.getHoldId(); // commented by
						// Simran
						String holdRemarks = dto.getTxtArea();
						String compRegNumber = cdto.getLinkedRegNum();
						boolean tmpflg = regmkrBD.checkAlrdyInsertdRegID(regNum, UserId, cdate);
						// ////ADDED BY SIMRAN////////////////
						logger.debug("<------flag " + tmpflg);
						if (tmpflg) {
							boolean flag = regmkrBD.saveLinkedAmt(regNum, eForm.getMyMap(), balDuty, balfee, compRegNumber, UserId, cdate, eForm.getRegDTO());
							// End :== this code for saving the data in the Map
						}
						// Start=== this code for saving the data in the hold
						// table
						String hldFlag = "Y";
						String fwdPage = eForm.formName;
						String holdId = "1";
						String createdBy = UserId;
						Date date = new Date();
						System.out.println(date);
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						System.out.println(sdf.format(date));
						String Date = sdf.format(date);
						String str = regmkrBD.saveHoldData(regNum, hldFlag, fwdPage, Date, createdBy, holdId, holdRemarks, officeId);

						String status = regmkrBD.emailAlertHold(dto.getRegNumber(), officeId);
						logger.debug("email alert----->" + status);
						// End=== this code for saving the data in the hold
						// table

						// Commented By Simran
						/*
						 * if (str.equalsIgnoreCase("1")) {
						 * if(!holdId.equalsIgnoreCase("") &&
						 * !holdId.equalsIgnoreCase(null)) { String holdName =
						 * regmkrBD.getHoldName(holdId);
						 * eForm.setHoldReason(holdName); }
						 * 
						 * forwardJsp = "commonHoldPage"; }
						 */
						forwardJsp = "commonHoldPage";
					}
					if (RegCompConstant.CANCEL_ACTION.equals(actionName)) {
						eForm.setCheckRegNo("");
						resetFields(dto);
						forwardJsp = "welcome";
					}
				}

				if ("witnessDetails".equalsIgnoreCase(formName)) {

					// eForm =
					//(RegCompleteMakerForm)request.getAttribute("regcompmaker")
					// ;
					if (RegCompConstant.CANCEL_ACTION.equals(actionName)) {
						eForm.setCheckRegNo("");
						resetFields(dto);

						forwardJsp = "welcome";
					}
					if (RegCompConstant.NEXT_ACTION.equalsIgnoreCase(actionName)) {
						OthrDeedDtlsDTO mapdto = new OthrDeedDtlsDTO();
						mapdto.setFirstName(eForm.getFirstName());
						mapdto.setMiddleName(eForm.getMiddleName());
						mapdto.setLastName(eForm.getLastName());
						// mapdto.setGender(eForm.getGender());
						mapdto.setAge(eForm.getAge());
						mapdto.setFatherName(eForm.getFatherName());
						mapdto.setMotherName(eForm.getMotherName());
						mapdto.setSpouseName(eForm.getSpouseName());
						mapdto.setNationality(eForm.getNationality());
						mapdto.setGender(eForm.getSlctGenderRad());
						mapdto.setFatherName(eForm.getFatherName());
						mapdto.setAddress(eForm.getAddress());
						dtlsMap = eForm.getDtlsMap();
						int count = eForm.getKeyCount();
						if (count == 0) {
							count = 1;
							// else
							// count++;
						}

						if (dtlsMap.containsKey(Integer.toString(count))) {
							// keyCount=keyCount+1;
							// key=key+keyCount;
							count++;
							dtlsMap.put(Integer.toString(count), mapdto);

						} else {
							dtlsMap.put(Integer.toString(count), mapdto);
						}

						eForm.setKeyCount(count);

						eForm.setAddMoreCounter(count);

						key = "key";

						eForm.setDtlsMap(dtlsMap);
						eForm.setAddress("");
						forwardJsp = "witnessDet2";

					}

				}

				if ("witnessDetails2".equalsIgnoreCase(formName)) {

					if (RegCompConstant.CANCEL_ACTION.equals(actionName)) {
						eForm.setCheckRegNo("");
						resetFields(dto);

						forwardJsp = "welcome";
					}

					if (RegCompConstant.DELETE_WITNESS_ACTION.equalsIgnoreCase(actionName)) {
						OthrDeedDtlsDTO mapdto = new OthrDeedDtlsDTO();
						String[] dltwitness = eForm.getHdnDeleteWitness().split(",");
						int count = eForm.getKeyCount() - 1;
						dtlsMap = eForm.getDtlsMap();
						for (int i = 0; i < dltwitness.length; i++) {
							mapdto = (OthrDeedDtlsDTO) dtlsMap.get(dltwitness[i]);
							dtlsMap.remove(dltwitness[i]);
						}
						eForm.setAddMoreCounter(count);
						eForm.setKeyCount(count);
						eForm.setDtlsMap(dtlsMap);
						forwardJsp = "witnessDet2";
					}

					if (RegCompConstant.ADD_ACTION.equalsIgnoreCase(actionName)) {
						if (eForm.getWitAdd().equalsIgnoreCase("Y")) {
							OthrDeedDtlsDTO mapdto = new OthrDeedDtlsDTO();
							logger.debug("first name************" + eForm.getFirstName());
							mapdto.setFirstName(eForm.getFirstName());
							mapdto.setMiddleName(eForm.getMiddleName());
							mapdto.setLastName(eForm.getLastName());
							// mapdto.setGender(eForm.getGender());
							mapdto.setAge(eForm.getAge());
							mapdto.setFatherName(eForm.getFatherName());
							mapdto.setMotherName(eForm.getMotherName());
							mapdto.setSpouseName(eForm.getSpouseName());
							mapdto.setNationality(eForm.getNationality());
							mapdto.setGender(eForm.getSlctGenderRad());
							if (language.equalsIgnoreCase("hi")) {
								if (eForm.getSlctGenderRad().equalsIgnoreCase("Male")) {
									mapdto.setDisplayGender("पुस्र्ष");
								} else if (eForm.getSlctGenderRad().equalsIgnoreCase("Female")) {
									mapdto.setDisplayGender("महिला");
								} else {

									mapdto.setDisplayGender("अन्य");
								}
							} else {
								mapdto.setDisplayGender(mapdto.getGender());
							}
							mapdto.setFatherName(eForm.getFatherName());
							mapdto.setAddress(eForm.getAddress());
							mapdto.setRelationshipWit(dto.getRelationshipWit());
							mapdto.setPhoneNumber(eForm.getPhoneNumber());
							dtlsMap = eForm.getDtlsMap();
							int count = eForm.getKeyCount();
							if (count == 0) {
								count = 1;
								// else
								// count++;
							}

							if (dtlsMap.containsKey(Integer.toString(count))) {
								// keyCount=keyCount+1;
								// key=key+keyCount;
								count++;
								dtlsMap.put(Integer.toString(count), mapdto);

							} else {
								dtlsMap.put(Integer.toString(count), mapdto);
							}
							eForm.setKeyCount(count);

							eForm.setAddMoreCounter(count);
							key = "key";

							eForm.setDtlsMap(dtlsMap);
							eForm.setAddress("");
							eForm.setFirstName("");
							eForm.setMiddleName("");
							eForm.setLastName("");
							eForm.setAge("");
							eForm.setFatherName("");
							eForm.setMotherName("");
							eForm.setSpouseName("");
							eForm.setNationality("");
							eForm.setGender("");
							eForm.setFatherName("");
							dto.setRelationshipWit("");
							eForm.setPhoneNumber("");
							eForm.setSlctGenderRad("Male");
							eForm.setRelationshipList(regmkrBD.getRelationshipList("M", language));
							forwardJsp = "witnessDet2";
							eForm.setWitAdd("N");
						}

					}

					if (RegCompConstant.NEXT_ACTION.equalsIgnoreCase(actionName)) {
						if (eForm.getWitnessAdd().equalsIgnoreCase("Y")) {
							OthrDeedDtlsDTO mapdto = new OthrDeedDtlsDTO();
							mapdto.setFirstName(eForm.getFirstName());
							mapdto.setMiddleName(eForm.getMiddleName());
							mapdto.setLastName(eForm.getLastName());
							// mapdto.setGender(eForm.getGender());
							mapdto.setAge(eForm.getAge());
							mapdto.setFatherName(eForm.getFatherName());
							mapdto.setMotherName(eForm.getMotherName());
							mapdto.setSpouseName(eForm.getSpouseName());
							mapdto.setNationality(eForm.getNationality());
							mapdto.setGender(eForm.getSlctGenderRad());
							mapdto.setFatherName(eForm.getFatherName());
							mapdto.setAddress(eForm.getAddress());
							eForm.setSlctGenderRad("Male");
							mapdto.setRelationshipWit(dto.getRelationshipWit());
							mapdto.setPhoneNumber(eForm.getPhoneNumber());
							dtlsMap = eForm.getDtlsMap();
							int count = eForm.getKeyCount();
							if (count == 0) {
								count = 1;
								// else
								// count++;
							}

							if (dtlsMap.containsKey(Integer.toString(count))) {
								// keyCount=keyCount+1;
								// key=key+keyCount;
								count++;
								dtlsMap.put(Integer.toString(count), mapdto);

							} else {
								dtlsMap.put(Integer.toString(count), mapdto);
							}

							eForm.setKeyCount(count);

							eForm.setAddMoreCounter(count);

							key = "key";

							eForm.setDtlsMap(dtlsMap);
						}

						if (eForm.getDtlsMap().size() < 2) {
							messages.add("MSG", new ActionMessage("witness_required"));
							if (language.equalsIgnoreCase("en"))
								request.setAttribute("MSG", "Enter atleast one more witness details");
							else
								request.setAttribute("MSG", "कम से कम एक और गवाह विवरण दर्ज करें");
							saveMessages(request, messages);
							eForm.setCheck("Y");
							request.setAttribute("checkRegID", eForm);
							eForm.setActionName("");
							eForm.setAddress("");
							eForm.setFirstName("");
							eForm.setMiddleName("");
							eForm.setLastName("");
							eForm.setAge("");
							eForm.setFatherName("");
							eForm.setMotherName("");
							eForm.setSpouseName("");
							eForm.setNationality("");
							eForm.setGender("");
							eForm.setPhoneNumber("");
							eForm.setFatherName("");
							dto.setRelationshipWit("");
							eForm.setSlctGenderRad("Male");
							eForm.setRelationshipList(regmkrBD.getRelationshipList("M", language));
							forwardJsp = "witnessDet2";
						} else {// code for inserting data into DB

							String regNum = dto.getRegNumber();
							boolean flg1 = regmkrBD.checkalrdyPrsntWtness(regNum);

							boolean flg = regmkrBD.insertDtlsMap(regNum, eForm.getDtlsMap(), UserId);
							// ArrayList propList =
							// regmkrBD.getpropIdList(regNum);
							// eForm.getCommonDTO().setPropertyList(propList);
							// //commented by simran (new page is to be added)
							// eForm.setPropIdList(propList);
							// forwardJsp = "linkingHistory";

							// ***********added on 28sep***************consenter
							// detls***//
							if (flg) {
								if (dto.getConsenterVal().equals("Y")) {
									// eForm.setDtlsMap(new HashMap());
									eForm.setAddress("");
									eForm.setFirstName("");
									eForm.setMiddleName("");
									eForm.setLastName("");
									eForm.setAge("");
									eForm.setFatherName("");
									eForm.setMotherName("");
									eForm.setSpouseName("");
									eForm.setNationality("");
									eForm.setGender("");
									eForm.setFatherName("");
									eForm.setPhoneNumber("");
									dto.setRelationshipWit("");
									eForm.setSlctGenderRad("Male");

									eForm.setAddMoreCounter(0);
									eForm.setRelationshipList(regmkrBD.getRelationshipList("M", language));
									forwardJsp = "consenterDetails";
									// TODO
									regmkrBD.UpdateRegistrationCompletionStatus(dto.getRegNumber(), RegCompConstant.CONSENTER_DETAILS_STATUS, cdate, UserId);
								} else {
									String deedId = regmkrBD.getDeedId(dto.getRegNumber());
									// logger.debug("<-----DEED ID"+deedId);
									dto.setDeedID(deedId);
									boolean flag = regmkrBD.UpdateRegistrationCompletionStatus(dto.getRegNumber(), RegCompConstant.OTHER_DEED_DETLS_STATUS, cdate, UserId);
									if (flag) {
										forwardJsp = "otherDeedDetailsNew";
									} else {
										session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
										forwardJsp = "failuremaker";
									}
								}
							} else {
								if (language.equalsIgnoreCase("en"))
									request.setAttribute("MSG", "Could not save the details. Try Again");
								else
									request.setAttribute("MSG", "डेटा सुरक्षित नहीं हुआ");
								forwardJsp = "witnessDet2";
							}

						}
					}

					if ("GENDER_TRNS_ACTION".equalsIgnoreCase(actionName)) {
						String gender = "";
						if (eForm.getSlctGenderRad().equalsIgnoreCase("Male"))
							gender = "M";
						else if (eForm.getSlctGenderRad().equalsIgnoreCase("Female"))
							gender = "F";
						else {
							gender = "O";
						}
						eForm.setRelationshipList(regmkrBD.getRelationshipList(gender, language));

						forwardJsp = "witnessDet2";
					}

				}

				if ("consenterDetails".equalsIgnoreCase(formName)) {
					if (RegCompConstant.CANCEL_ACTION.equals(actionName)) {
						eForm.setCheckRegNo("");
						resetFields(dto);

						forwardJsp = "welcome";
					}

					if ("GENDER_TRNS_ACTION".equalsIgnoreCase(actionName)) {
						String gender = "";
						if (eForm.getSlctGenderRad().equalsIgnoreCase("Male"))
							gender = "M";
						else
							gender = "F";
						eForm.setRelationshipList(regmkrBD.getRelationshipList(gender, language));
						forwardJsp = "consenterDetails";
					}

					if (RegCompConstant.DELETE_ACTION.equalsIgnoreCase(actionName)) {
						logger.debug("********del consenter****");
						OthrDeedDtlsDTO mapdto = new OthrDeedDtlsDTO();
						String[] dltwitness = eForm.getHdnDeleteWitness().split(",");
						int count = eForm.getKeyCount() - 1;
						dtlsMap = eForm.getDtlsMapConsntr();
						for (int i = 0; i < dltwitness.length; i++) {
							mapdto = (OthrDeedDtlsDTO) dtlsMap.get(dltwitness[i]);
							dtlsMap.remove(dltwitness[i]);
						}
						eForm.setAddMoreCounter(count);
						eForm.setKeyCount(count);
						eForm.setDtlsMap(dtlsMap);
						forwardJsp = "consenterDetails";
					}

					if (RegCompConstant.ADD_CONSENTER_ACTION.equalsIgnoreCase(actionName)) {
						logger.debug("********add consenter****");
						OthrDeedDtlsDTO mapdto = new OthrDeedDtlsDTO();
						mapdto.setFirstName(eForm.getFirstName());
						mapdto.setMiddleName(eForm.getMiddleName());
						mapdto.setLastName(eForm.getLastName());
						// mapdto.setGender(eForm.getGender());
						mapdto.setAge(eForm.getAge());
						mapdto.setFatherName(eForm.getFatherName());
						mapdto.setMotherName(eForm.getMotherName());
						mapdto.setSpouseName(eForm.getSpouseName());
						mapdto.setNationality(eForm.getNationality());
						mapdto.setGender(eForm.getSlctGenderRad());
						mapdto.setPhoneNumber(eForm.getPhoneNumber());
						if (language.equalsIgnoreCase("hi")) {
							if (eForm.getSlctGenderRad().equalsIgnoreCase("Male")) {
								mapdto.setDisplayGender("पुस्र्ष");
							} else {
								mapdto.setDisplayGender("महिला");
							}
						} else {
							mapdto.setDisplayGender(mapdto.getGender());
						}
						mapdto.setFatherName(eForm.getFatherName());
						mapdto.setAddress(eForm.getAddress());
						mapdto.setRelationshipWit(dto.getRelationshipWit());
						dtlsMap = eForm.getDtlsMapConsntr();
						int count = eForm.getKeyCount();
						if (count == 0) {
							count = 1;
							// else
							// count++;
						}

						if (dtlsMap.containsKey(Integer.toString(count))) {
							// keyCount=keyCount+1;
							// key=key+keyCount;
							count++;
							dtlsMap.put(Integer.toString(count), mapdto);

						} else {
							dtlsMap.put(Integer.toString(count), mapdto);
						}

						eForm.setKeyCount(count);

						eForm.setAddMoreCounter(count);

						key = "key";

						eForm.setDtlsMapConsntr(dtlsMap);
						eForm.setAddress("");
						eForm.setFirstName("");
						eForm.setMiddleName("");
						eForm.setLastName("");
						eForm.setAge("");
						eForm.setFatherName("");
						eForm.setMotherName("");
						eForm.setSpouseName("");
						eForm.setNationality("");
						eForm.setGender("");
						eForm.setFatherName("");
						dto.setRelationshipWit("");
						eForm.setPhoneNumber("");
						eForm.setSlctGenderRad("Male");
						eForm.setRelationshipList(regmkrBD.getRelationshipList("M", language));
						forwardJsp = "consenterDetails";
					}

					if (RegCompConstant.NEXT_TO_OTHER_DEED_DETAILS.equalsIgnoreCase(actionName)) {
						logger.debug("********saving consenter details****");
						if (eForm.getFirstName() != null && !eForm.getFirstName().equals("")) {
							OthrDeedDtlsDTO mapdto = new OthrDeedDtlsDTO();
							mapdto.setFirstName(eForm.getFirstName());
							mapdto.setMiddleName(eForm.getMiddleName());
							mapdto.setLastName(eForm.getLastName());
							// mapdto.setGender(eForm.getGender());
							mapdto.setAge(eForm.getAge());
							mapdto.setFatherName(eForm.getFatherName());
							mapdto.setMotherName(eForm.getMotherName());
							mapdto.setSpouseName(eForm.getSpouseName());
							mapdto.setNationality(eForm.getNationality());
							mapdto.setGender(eForm.getSlctGenderRad());
							mapdto.setFatherName(eForm.getFatherName());
							mapdto.setAddress(eForm.getAddress());
							mapdto.setRelationshipWit(dto.getRelationshipWit());
							mapdto.setPhoneNumber(eForm.getPhoneNumber());
							dtlsMap = eForm.getDtlsMapConsntr();
							int count = eForm.getKeyCount();
							if (count == 0) {
								count = 1;
								// else
								// count++;
							}

							if (dtlsMap.containsKey(Integer.toString(count))) {
								// keyCount=keyCount+1;
								// key=key+keyCount;
								count++;
								dtlsMap.put(Integer.toString(count), mapdto);

							} else {
								dtlsMap.put(Integer.toString(count), mapdto);
							}

							eForm.setKeyCount(count);

							eForm.setAddMoreCounter(count);

							key = "key";

							eForm.setDtlsMapConsntr(dtlsMap);
						}

						{// code for inserting data into DB
							boolean flg = false;
							if (eForm.getDtlsMapConsntr().size() > 0) {
								String regNum = dto.getRegNumber();
								boolean flg1 = regmkrBD.checkalrdyPrsntConsenter(regNum);

								flg = regmkrBD.insertConsenterDtlsMap(regNum, eForm.getDtlsMapConsntr(), UserId);
							} else {
								flg = true;
							}

							// ArrayList propList =
							// regmkrBD.getpropIdList(regNum);
							// eForm.getCommonDTO().setPropertyList(propList);
							// //commented by simran (new page is to be added)
							// eForm.setPropIdList(propList);
							// forwardJsp = "linkingHistory";

							// ***********added on 28sep***************consenter
							// detls***//

							if (flg) {
								String deedId = regmkrBD.getDeedId(dto.getRegNumber());
								// logger.debug("<-----DEED ID"+deedId);
								dto.setDeedID(deedId);
								regmkrBD.UpdateRegistrationCompletionStatus(dto.getRegNumber(), RegCompConstant.OTHER_DEED_DETLS_STATUS, cdate, UserId);
								forwardJsp = "otherDeedDetailsNew";
							} else {
								if (language.equalsIgnoreCase("en"))
									request.setAttribute("MSG", "Could not save the details. Try Again");
								else
									request.setAttribute("MSG", "डेटा सुरक्षित नहीं हुआ");
								forwardJsp = "consenterDetails";
							}

						}
					}
				}
				// ////////////////ADDED BY
				// SIMRAN///////////////////////////////////////
				if ("otherDeedDetls".equalsIgnoreCase(formName)) {
					if (RegCompConstant.NEXT_ACTION.equalsIgnoreCase(actionName)) {
						boolean insert = regmkrBD.insertOtherDeedDetls(dto, UserId, cdate);
						if (insert) {
							logger.debug("Inserted");
							String regNum = dto.getRegNumber();
							String deedId = regmkrBD.getDeedId(dto.getRegNumber());
							String instId = regmkrBD.getInstId(dto.getRegNumber());
							int deed = Integer.parseInt(deedId);
							int inst = Integer.parseInt(instId);
							boolean linkingFlag = regmkrBD.isLinkingPage(dto.getRegNumber());
							// if(deed == RegInitConstant.DEED_ADOPTION||(deed==
							// RegInitConstant.DEED_TRUST &&
							// inst==RegInitConstant
							// .INST_TRUST_NPV_P)||deed==RegInitConstant
							// .DEED_CANCELLATION_OF_WILL_POA
							// ||deed==RegInitConstant.DEED_AGREEMENT_MEMO_NPV||
							// dto.getCommonDeedFlag() == 1) //deeds not
							// requiring property valuation
							if (!linkingFlag) {

								ArrayList transPartyList = regmkrBD.getTransactingParties(regNum, language);
								ArrayList witnessList = regmkrBD.getWitnessList(regNum, cdate, language);
								eForm.setTransactPartyList(transPartyList);
								eForm.setWitnessList(witnessList);
								ArrayList getOfficialList = regmkrBD.getofficialList();
								eForm.getRegDTO().setGovOfficialList(getOfficialList);
								regmkrBD.UpdateRegistrationCompletionStatus(dto.getRegNumber(), RegCompConstant.PRESENTATION_STATUS, cdate, UserId);
								forwardJsp = "presentation";
							} else {
								int deedID = Integer.parseInt(deedId);
								int instID = Integer.parseInt(instId);
								dto.setCommonDeedFlag(regCommonBO.getCommonDeedFlag(deedId));

								/*
								 * if(deedID==RegInitConstant.DEED_ADOPTION ||
								 * deedID==RegInitConstant.DEED_TRUST ||
								 * deedID==RegInitConstant.DEED_DEPOSIT_OF_TITLE
								 * ||deedID==RegInitConstant.
								 * DEED_CANCELLATION_OF_WILL_POA ||
								 * deedID==RegInitConstant.DEED_LEASE_NPV ||
								 * deedID==RegInitConstant.DEED_MORTGAGE_NPV ||
								 * deedID==RegInitConstant.DEED_POA ||
								 * deedID==RegInitConstant.DEED_SETTLEMENT_NPV
								 * || deedID==RegInitConstant.DEED_WILL_NPV ||
								 * deedID
								 * ==RegInitConstant.DEED_TRANSFER_LEASE_NPV ||
								 * deedID
								 * ==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
								 * deedID
								 * ==RegInitConstant.DEED_SURRENDER_LEASE_NPV ||
								 * deedID==RegInitConstant.DEED_PARTNERSHIP_NPV
								 * || deedID==RegInitConstant.DEED_PARTITION_NPV
								 * ||
								 * deedID==RegInitConstant.DEED_AGREEMENT_MEMO_NPV
								 * ||
								 * deedID==RegInitConstant.DEED_COMPOSITION_NPV
								 * ||deedID==RegInitConstant.
								 * DEED_LETTER_OF_LICENCE_NPV ||
								 * deedID==RegInitConstant
								 * .DEED_SECURITY_BOND_NPV ||
								 * deedID==RegInitConstant.DEED_TRANSFER_NPV ||
								 * deedID
								 * ==RegInitConstant.DEED_FURTHER_CHARGE_NPV ||
								 * dto.getCommonDeedFlag()==1){ //for deeds
								 * following code set2
								 * 
								 * 
								 * if(deedID==RegInitConstant.
								 * DEED_AGREEMENT_MEMO_NPV ||
								 * deedID==RegInitConstant.DEED_TRANSFER_NPV ||
								 * deedID
								 * ==RegInitConstant.DEED_FURTHER_CHARGE_NPV){
								 * 
								 * if(
								 * instID==RegInitConstant.INST_AGREEMENT_MEMO_NPV_1
								 * ||
								 * instID==RegInitConstant.INST_AGREEMENT_MEMO_NPV_2
								 * ||
								 * instID==RegInitConstant.INST_AGREEMENT_MEMO_NPV_3
								 * ||
								 * instID==RegInitConstant.INST_AGREEMENT_MEMO_NPV_4
								 * ||
								 * instID==RegInitConstant.INST_AGREEMENT_MEMO_NPV_6
								 * ||
								 * instID==RegInitConstant.INST_AGREEMENT_MEMO_NPV_7
								 * ||
								 * instID==RegInitConstant.INST_AGREEMENT_MEMO_NPV_9
								 * ||
								 * instID==RegInitConstant.INST_TRANSFER_NPV_1
								 * ||
								 * instID==RegInitConstant.INST_TRANSFER_NPV_2
								 * ||
								 * instID==RegInitConstant.INST_FURTHER_CHARGE_NPV_1
								 * ){
								 * 
								 * dto.setAgreeMemoInstFlag(RegInitConstant.
								 * NPV_PROP_NOT_REQ_CONSTANT_2);
								 * 
								 * }elseif(instID==RegInitConstant.
								 * INST_AGREEMENT_MEMO_NPV_10 ||
								 * instID==RegInitConstant
								 * .INST_AGREEMENT_MEMO_NPV_5 ||
								 * instID==RegInitConstant
								 * .INST_AGREEMENT_MEMO_NPV_8 ||
								 * instID==RegInitConstant.INST_TRANSFER_NPV_3
								 * ||
								 * instID==RegInitConstant.INST_TRANSFER_NPV_4
								 * ||
								 * instID==RegInitConstant.INST_FURTHER_CHARGE_NPV_2
								 * ){
								 * 
								 * dto.setAgreeMemoInstFlag(RegInitConstant.
								 * NPV_PROP_REQ_CONSTANT_1); }
								 * 
								 * 
								 * }
								 * 
								 * dto.setDeedTypeFlag(1);
								 * //regForm.setHdnDeclareShareCheck("N");
								 * 
								 * 
								 * }
								 * if(deedID==RegInitConstant.DEED_DEPOSIT_OF_TITLE
								 * ||deedID==RegInitConstant.DEED_LEASE_NPV ||
								 * deedID==RegInitConstant.DEED_MORTGAGE_NPV
								 * ||deedID==RegInitConstant.DEED_POA ||
								 * deedID==RegInitConstant.DEED_SETTLEMENT_NPV
								 * ||deedID==RegInitConstant.DEED_WILL_NPV ||
								 * deedID
								 * ==RegInitConstant.DEED_TRANSFER_LEASE_NPV
								 * ||deedID
								 * ==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
								 * deedID
								 * ==RegInitConstant.DEED_SURRENDER_LEASE_NPV
								 * ||deedID
								 * ==RegInitConstant.DEED_COMPOSITION_NPV ||
								 * deedID
								 * ==RegInitConstant.DEED_SECURITY_BOND_NPV
								 * ||deedID
								 * ==RegInitConstant.DEED_LETTER_OF_LICENCE_NPV
								 * || (deedID==RegInitConstant.DEED_TRUST &&
								 * instID==RegInitConstant.INST_TRUST_NPV_P ) ||
								 * (deedID==RegInitConstant.DEED_PARTNERSHIP_NPV
								 * &&
								 * (instID==RegInitConstant.INST_PARTNERSHIP_NPV_1
								 * ||
								 * instID==RegInitConstant.INST_PARTNERSHIP_NPV_2
								 * ))||
								 * deedID==RegInitConstant.DEED_PARTITION_NPV ||
								 * (
								 * deedID==RegInitConstant.DEED_AGREEMENT_MEMO_NPV
								 * &&
								 * dto.getAgreeMemoInstFlag()==RegInitConstant
								 * .NPV_PROP_REQ_CONSTANT_1) ||
								 * (deedID==RegInitConstant.DEED_TRANSFER_NPV &&
								 * dto.getAgreeMemoInstFlag()==RegInitConstant.
								 * NPV_PROP_REQ_CONSTANT_1) ||
								 * (deedID==RegInitConstant
								 * .DEED_FURTHER_CHARGE_NPV &&
								 * dto.getAgreeMemoInstFlag
								 * ()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1)
								 * || dto.getDeedTypeFlag()==1) {
								 * dto.setPropPage("N"); } else {
								 * dto.setPropPage("P"); }
								 */
								dto.setDeedTypeFlag(1);
								dto.setPropPage("P");
								ArrayList propList = regmkrBD.getpropIdList(regNum);
								eForm.getCommonDTO().setPropertyList(propList);
								eForm.setPropIdList(propList);
								regmkrBD.UpdateRegistrationCompletionStatus(dto.getRegNumber(), RegCompConstant.LINKING_HISTORY_STATUS, cdate, UserId);
								forwardJsp = "linkingHistory";
							}
						} else {
							session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
							forwardJsp = "failuremaker";
						}

					}

					if (RegCompConstant.RESET_ACTION.equalsIgnoreCase(actionName)) {
						dto.setPendingTaxDuties("");
						dto.setLoanInfo("");
						dto.setChargeOrCase("");
						dto.setRightToSale("");
						forwardJsp = "otherDeedDetailsNew";
					}

				}
				///////////////////////////END//////////////////////////////////
				// //
				if ("linkingHistory".equalsIgnoreCase(formName)) {

					if (RegCompConstant.CANCEL_ACTION.equals(actionName)) {
						eForm.setCheckRegNo("");
						// if cancelled then deactivate the inserted witness
						// details
						boolean flg1 = regmkrBD.checkalrdyPrsntWtness(dto.getRegNumber());
						resetFields(dto);
						// end
						forwardJsp = "welcome";
					}

					if ("SEARCH_PROP_HIST".equalsIgnoreCase(actionName)) {
						String regNum = dto.getRegNumber();
						/*
						 * if(regmkrBD.checkPinRequired(eForm.getCommonDTO().getOldRegNum
						 * ())) { dto.setPinRequired("Y"); } else
						 */
						{
							dto.setPinRequired("N");
						}

						// this oldregnum is completion number of
						// the old property
						/****
						 * Start added on 11.4.2013 for changing search on
						 * registration initiation number to registration
						 * completion number
						 * ****/
						String oldcompregNum = eForm.getCommonDTO().getOldRegNum();
						logger.debug("oldcompregNum" + oldcompregNum);
						String oldregNum = regmkrBD.getreginitNumber(oldcompregNum);
						/** end **/
						eForm.setErrLnkFlg("");
						if (oldregNum.equalsIgnoreCase("")) {
							// messages.add("MSG1", new ActionMessage(
							// "linking_error"));
							// saveMessages(request, messages);
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("msg", "Please Enter a valid Registration Number");
							} else {
								request.setAttribute("msg", "कृपया एक वैध पंजीकरण संख्या दर्ज करें|");
							}
							eForm.setErrLnkFlg("E");
							forwardJsp = "linkingHistory";
						}

						// code to check oldregNum != regNum
						boolean tempFlag = true;
						if (regNum.equalsIgnoreCase(oldregNum)) {
							tempFlag = false;
							// write message here
							// messages.add("MSG1", new ActionMessage(
							// "linking_error"));
							// saveMessages(request, messages);
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("msg", "Cannot Link with same Registration Number.");
							} else {
								request.setAttribute("msg", "एक ही रजिस्ट्रेशन नंबर के साथ लिंक नहीं कर सकते|");
							}
							eForm.setErrLnkFlg("E");
							forwardJsp = "linkingHistory";

						}
						if (tempFlag) {
							String deedId = regmkrBD.getDeedId(oldregNum);
							int deedID = Integer.parseInt(deedId);
							int instID = Integer.parseInt(regmkrBD.getInstId(oldregNum));

							dto.setCommonDeedFlag(regCommonBO.getCommonDeedFlag(deedId));

							if (deedID == RegInitConstant.DEED_ADOPTION || deedID == RegInitConstant.DEED_TRUST || deedID == RegInitConstant.DEED_DEPOSIT_OF_TITLE || deedID == RegInitConstant.DEED_CANCELLATION_OF_WILL_POA || deedID == RegInitConstant.DEED_LEASE_NPV || deedID == RegInitConstant.DEED_MORTGAGE_NPV || deedID == RegInitConstant.DEED_POA || deedID == RegInitConstant.DEED_SETTLEMENT_NPV || deedID == RegInitConstant.DEED_WILL_NPV || deedID == RegInitConstant.DEED_TRANSFER_LEASE_NPV || deedID == RegInitConstant.DEED_RECONV_MORTGAGE_NPV || deedID == RegInitConstant.DEED_SURRENDER_LEASE_NPV || deedID == RegInitConstant.DEED_PARTNERSHIP_NPV || deedID == RegInitConstant.DEED_PARTITION_NPV || deedID == RegInitConstant.DEED_AGREEMENT_MEMO_NPV || deedID == RegInitConstant.DEED_AGREEMENT_MEMO_NPV_LOAN || deedID == RegInitConstant.DEED_COMPOSITION_NPV || deedID == RegInitConstant.DEED_LETTER_OF_LICENCE_NPV || deedID == RegInitConstant.DEED_SECURITY_BOND_NPV || deedID == RegInitConstant.DEED_TRANSFER_NPV || deedID == RegInitConstant.DEED_FURTHER_CHARGE_NPV || dto.getCommonDeedFlag() == 1) { // for
								// deeds
								// following
								// code
								// set2

								if (deedID == RegInitConstant.DEED_AGREEMENT_MEMO_NPV || deedID == RegInitConstant.DEED_AGREEMENT_MEMO_NPV_LOAN || deedID == RegInitConstant.DEED_TRANSFER_NPV || deedID == RegInitConstant.DEED_FURTHER_CHARGE_NPV) {

									if (instID == RegInitConstant.INST_AGREEMENT_MEMO_NPV_1 || instID == RegInitConstant.INST_AGREEMENT_MEMO_NPV_2 || instID == RegInitConstant.INST_AGREEMENT_MEMO_NPV_3 || instID == RegInitConstant.INST_AGREEMENT_MEMO_NPV_4 || instID == RegInitConstant.INST_AGREEMENT_MEMO_NPV_6 || instID == RegInitConstant.INST_AGREEMENT_MEMO_NPV_6_LOAN || instID == RegInitConstant.INST_AGREEMENT_MEMO_NPV_7 || instID == RegInitConstant.INST_AGREEMENT_MEMO_NPV_9 || instID == RegInitConstant.INST_TRANSFER_NPV_1 || instID == RegInitConstant.INST_TRANSFER_NPV_2 || instID == RegInitConstant.INST_FURTHER_CHARGE_NPV_1) {

										dto.setAgreeMemoInstFlag(RegInitConstant.NPV_PROP_NOT_REQ_CONSTANT_2);

									} else if (instID == RegInitConstant.INST_AGREEMENT_MEMO_NPV_10 || instID == RegInitConstant.INST_AGREEMENT_MEMO_NPV_5 || instID == RegInitConstant.INST_AGREEMENT_MEMO_NPV_8 || instID == RegInitConstant.INST_TRANSFER_NPV_3 || instID == RegInitConstant.INST_TRANSFER_NPV_4 || instID == RegInitConstant.INST_FURTHER_CHARGE_NPV_2) {

										dto.setAgreeMemoInstFlag(RegInitConstant.NPV_PROP_REQ_CONSTANT_1);
									}

								}

								dto.setDeedTypeFlag(1);
								// regForm.setHdnDeclareShareCheck("N");

							}

							if (deedID == RegInitConstant.DEED_DEPOSIT_OF_TITLE || deedID == RegInitConstant.DEED_LEASE_NPV || deedID == RegInitConstant.DEED_MORTGAGE_NPV || deedID == RegInitConstant.DEED_POA || deedID == RegInitConstant.DEED_SETTLEMENT_NPV || deedID == RegInitConstant.DEED_WILL_NPV || deedID == RegInitConstant.DEED_TRANSFER_LEASE_NPV || deedID == RegInitConstant.DEED_RECONV_MORTGAGE_NPV || deedID == RegInitConstant.DEED_SURRENDER_LEASE_NPV || deedID == RegInitConstant.DEED_COMPOSITION_NPV || deedID == RegInitConstant.DEED_SECURITY_BOND_NPV || deedID == RegInitConstant.DEED_LETTER_OF_LICENCE_NPV || (deedID == RegInitConstant.DEED_TRUST && instID == RegInitConstant.INST_TRUST_NPV_P) || (deedID == RegInitConstant.DEED_PARTNERSHIP_NPV && (instID == RegInitConstant.INST_PARTNERSHIP_NPV_1 || instID == RegInitConstant.INST_PARTNERSHIP_NPV_2)) || deedID == RegInitConstant.DEED_PARTITION_NPV || (deedID == RegInitConstant.DEED_AGREEMENT_MEMO_NPV && dto.getAgreeMemoInstFlag() == RegInitConstant.NPV_PROP_REQ_CONSTANT_1) || (deedID == RegInitConstant.DEED_AGREEMENT_MEMO_NPV_LOAN && dto.getAgreeMemoInstFlag() == RegInitConstant.NPV_PROP_REQ_CONSTANT_1) || (deedID == RegInitConstant.DEED_TRANSFER_NPV && dto.getAgreeMemoInstFlag() == RegInitConstant.NPV_PROP_REQ_CONSTANT_1) || (deedID == RegInitConstant.DEED_FURTHER_CHARGE_NPV && dto.getAgreeMemoInstFlag() == RegInitConstant.NPV_PROP_REQ_CONSTANT_1) || dto.getDeedTypeFlag() == 1) {
								dto.setPropPageOld("N");
							} else {
								dto.setPropPageOld("P");
							}
							ArrayList propIdList = regmkrBD.getpropIdList(oldregNum);
							if (propIdList == null || propIdList.size() == 0) {
								if ("en".equalsIgnoreCase(language)) {
									request.setAttribute("msg", "No property is associated with this Registration Number");
								} else {
									request.setAttribute("msg", "इस पंजीकरण संख्या के साथ कोई संपत्ति नहीं जुड़ी है|");
								}
								eForm.setErrLnkFlg("E");
								forwardJsp = "linkingHistory";
							} else {
								eForm.setOldPropIdList(propIdList);
								eForm.getCommonDTO().setProDeedList(propIdList);
								eForm.setListSize(propIdList.size());
								forwardJsp = "linkingHistory";
							}
						}
					}

					if ("LINK_HISTORY".equalsIgnoreCase(actionName)) {
						request.removeAttribute("msg");
						String propId = eForm.getPropId();
						String propIdInit = eForm.getPropIdInit();
						String regNum = dto.getRegNumber();
						String oldregNum = eForm.getCommonDTO().getOldRegNum();

						logger.debug("^^^^^^^^" + dto.getPinNumber());
						logger.debug("^^^^^^^" + dto.getTransactionType());
						/*
						 * Status I = linking Initiated Status L = linking
						 * Complete In maker part linking will only be initiated
						 * and will be complete only after completion of
						 * registration
						 */
						String status = "I";
						boolean flag = regmkrBD.getPropLockDetails(oldregNum, propId, dto.getRegNumber(), propIdInit);
						// ArrayList cavList=
						// bd.cavetsCheck(dto.getRegNumber());
						if (bd.checkPropertyCaveats(propId)) {
							eForm.setListSize(0);
							// messages.add("MSG", new ActionMessage(
							// "linking_cavets_error"));
							// saveMessages(request, messages);
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("msg", "Protest is logged on the selected Property.");
							} else {
								request.setAttribute("msg", "चयनित संपत्ति पर आपत्ति लॉग है|");
							}
							eForm.setLnkFlg("L");
						} else if (flag) {
							// eForm.setListSize(0);
							// messages.add("MSG", new ActionMessage(
							// "linking_lock_error"));
							// saveMessages(request, messages);
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("msg", "Selected Property is already Locked.");
							} else {
								request.setAttribute("msg", "चयनित संपत्ति पहले से ही लॉक है|");
							}
							eForm.setLnkFlg("L");
						}

						else {

							// TODO:- chk for pin no inserted
							String pinChk = regmkrBD.getPinDetails(propId, dto.getPinNumber());
							if (dto.getPinRequired().equalsIgnoreCase("Y")) {
								if (pinChk.equalsIgnoreCase(RegCompCheckerConstant.WRONG_PIN_ENTETRED)) {
									// eForm.setListSize(0);
									// messages.add("MSG", new ActionMessage(
									// "link.wrong.pin"));
									// saveMessages(request, messages);
									if ("en".equalsIgnoreCase(language)) {
										request.setAttribute("msg", "You have entered a wrong Pin..");
									} else {
										request.setAttribute("msg", "आपने गलत पिन दर्ज किया है।|");
									}
									eForm.setLnkFlg("L");
								} else if (pinChk.equalsIgnoreCase(RegCompCheckerConstant.PIN_EXPIRED)) {
									// eForm.setListSize(0);
									// messages.add("MSG", new ActionMessage(
									// "link.pin.expired"));
									// saveMessages(request, messages);
									if ("en".equalsIgnoreCase(language)) {
										request.setAttribute("msg", "This property has already been sold");
									} else {
										request.setAttribute("msg", "यह संपत्ति पहले ही बेची जा चुकी है।");
									}
									eForm.setLnkFlg("L");
								}

								else if (!regmkrBD.checkArea(propIdInit, propId, dto.getTransactionType(), request, language)) {
									/*
									 * if("en".equalsIgnoreCase(language)) {
									 * request.setAttribute("msg",
									 * "This property has already been sold"); }
									 * else {request.setAttribute("msg",
									 * "यह संपत्ति पहले ही बेची जा चुकी है।"); }
									 */eForm.setLnkFlg("L");
								}

								else {

									boolean chkAlrdyLnkd = regmkrBD.chkAlrdyLinked(propId, regNum, propIdInit, oldregNum, status);
									if (chkAlrdyLnkd) {
										boolean updtTble = regmkrBD.updateLinkngTble(propId, regNum, propIdInit, oldregNum, status, UserId, cdate, dto.getTransactionType());
										if (updtTble) {
											eForm.getCommonDTO().setOldRegNum("");
											eForm.setOldPropIdList(new ArrayList());
											eForm.setListSize(0);
											// messages.add("MSG", new
											// ActionMessage(
											// "linking_initiated"));
											// saveMessages(request, messages);
											dto.setLinkingDashboardList(regmkrBD.linkingDashBoardList(regNum, language));

											if ("en".equalsIgnoreCase(language)) {
												request.setAttribute("msg", "Document linking has been initiated successfully.");
											} else {
												request.setAttribute("msg", "दस्तावेज़ों को लिंक करने की प्रक्रिया सफलतापूर्वक शुरू की गई है।");
											}
											eForm.setLnkFlg("I");
											dto.setPinNumber("");
											cdto.setOldRegNo("");
											eForm.setPropId("");
											dto.setTransactionType("");
										}
									} else {
										boolean flg = regmkrBD.linkhistory(propId, regNum, propIdInit, oldregNum, status, UserId, cdate, dto.getTransactionType());
										if (flg) {
											eForm.getCommonDTO().setOldRegNum("");
											eForm.setOldPropIdList(new ArrayList());
											eForm.setListSize(0);
											// messages.add("MSG", new
											// ActionMessage(
											// "linking_initiated"));
											// saveMessages(request, messages);
											dto.setLinkingDashboardList(regmkrBD.linkingDashBoardList(regNum, language));
											if ("en".equalsIgnoreCase(language)) {
												request.setAttribute("msg", "Document linking has been initiated successfully.");
											} else {
												request.setAttribute("msg", "दस्तावेज़ों को लिंक करने की प्रक्रिया सफलतापूर्वक शुरू की गई है।");
											}
											eForm.setLnkFlg("I");
											dto.setPinNumber("");
											cdto.setOldRegNo("");
											eForm.setPropId("");
											dto.setTransactionType("");
										}
									}

								}

							} else {
								if (!regmkrBD.checkArea(propIdInit, propId, dto.getTransactionType(), request, language)) {
									/*
									 * if("en".equalsIgnoreCase(language)) {
									 * request.setAttribute("msg",
									 * "This property has already been sold"); }
									 * else {request.setAttribute("msg",
									 * "यह संपत्ति पहले ही बेची जा चुकी है।"); }
									 */
									eForm.setLnkFlg("L");
								} else {

									boolean chkAlrdyLnkd = regmkrBD.chkAlrdyLinked(propId, regNum, propIdInit, oldregNum, status);
									if (chkAlrdyLnkd) {
										boolean updtTble = regmkrBD.updateLinkngTble(propId, regNum, propIdInit, oldregNum, status, UserId, cdate, dto.getTransactionType());
										if (updtTble) {
											eForm.getCommonDTO().setOldRegNum("");
											eForm.setOldPropIdList(new ArrayList());
											eForm.setListSize(0);
											// messages.add("MSG", new
											// ActionMessage(
											// "linking_initiated"));
											// saveMessages(request, messages);
											dto.setLinkingDashboardList(regmkrBD.linkingDashBoardList(regNum, language));

											if ("en".equalsIgnoreCase(language)) {
												request.setAttribute("msg", "Document linking has been initiated successfully.");
											} else {
												request.setAttribute("msg", "दस्तावेज़ों को लिंक करने की प्रक्रिया सफलतापूर्वक शुरू की गई है।");
											}
											eForm.setLnkFlg("I");
											dto.setPinNumber("");
											cdto.setOldRegNo("");
											eForm.setPropId("");
											dto.setTransactionType("");
										}
									} else {
										boolean flg = regmkrBD.linkhistory(propId, regNum, propIdInit, oldregNum, status, UserId, cdate, dto.getTransactionType());
										if (flg) {
											eForm.getCommonDTO().setOldRegNum("");
											eForm.setOldPropIdList(new ArrayList());
											eForm.setListSize(0);
											// messages.add("MSG", new
											// ActionMessage(
											// "linking_initiated"));
											// saveMessages(request, messages);
											dto.setLinkingDashboardList(regmkrBD.linkingDashBoardList(regNum, language));

											if ("en".equalsIgnoreCase(language)) {
												request.setAttribute("msg", "Document linking has been initiated successfully.");
											} else {
												request.setAttribute("msg", "दस्तावेज़ों को लिंक करने की प्रक्रिया सफलतापूर्वक शुरू की गई है।");
											}
											eForm.setLnkFlg("I");
											dto.setPinNumber("");
											cdto.setOldRegNo("");
											eForm.setPropId("");
											dto.setTransactionType("");
										}
									}

								}
							}
						}

						forwardJsp = "linkingHistory";
					}

					if ("RESET_ACTION".equalsIgnoreCase(actionName)) {
						eForm.getCommonDTO().setOldRegNum("");
						eForm.setOldPropIdList(new ArrayList());
						eForm.setListSize(0);
						eForm.setLnkFlg("");
						forwardJsp = "linkingHistory";
					}

					if (RegCompConstant.NEXT_ACTION.equalsIgnoreCase(actionName)) {
						//

						// String compRegNum =

						String regNum = dto.getRegNumber();
						/** End **/
						// GET LIST OF TRANSACTION PARTIES
						ArrayList transPartyList = regmkrBD.getTransactingParties(regNum, language);
						ArrayList witnessList = regmkrBD.getWitnessList(regNum, cdate, language);
						eForm.setTransactPartyList(transPartyList);
						eForm.setWitnessList(witnessList);
						ArrayList getOfficialList = regmkrBD.getofficialList();
						eForm.getRegDTO().setGovOfficialList(getOfficialList);
						boolean flag = regmkrBD.UpdateRegistrationCompletionStatus(dto.getRegNumber(), RegCompConstant.PRESENTATION_STATUS, cdate, UserId);
						if (flag) {
							forwardJsp = "presentation";
						} else {
							session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
							forwardJsp = "failuremaker";
						}
					}
				}

				if ("presentation".equalsIgnoreCase(formName)) {

					if (RegCompConstant.CANCEL_ACTION.equals(actionName)) {
						eForm.setCheckRegNo("");
						resetFields(dto);

						forwardJsp = "welcome";
					}

					if (RegCompConstant.NEXT_ACTION.equals(actionName)) {

						// ****************photographs***********************//
						String regNum = dto.getRegNumber();
						String hdnPresentParty = eForm.getHdnPresentParty();
						String hdnPresentWitness = eForm.getHdnPresentWitness();
						System.out.println(hdnPresentParty);
						System.out.println(hdnPresentWitness);
						boolean checkAlrdyPrsntData = regmkrBD.dltAlrdyPresentData(dto.getRegNumber(), UserId, cdate);
						boolean insrtPrtyPrsntDet = regmkrBD.insrtPrtyPrsntDet(hdnPresentParty, cdate, UserId, regNum);

						String regNumber = dto.getRegNumber();
						ArrayList transPartyList = regmkrBD.getTransactingParties(regNumber, language);
						ArrayList witnessList = regmkrBD.getWitnessList(regNumber, cdate, language);
						ArrayList consenterList = regmkrBD.getConsenterList(regNumber, language);
						if (consenterList.size() > 0) {
							dto.setConsenterChk("Y");
						} else {
							dto.setConsenterChk("N");
						}
						eForm.setTransactPartyList(transPartyList);
						eForm.setWitnessList(witnessList);
						eForm.setConsenterList(consenterList);
						String pathFP = FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_PHOTO;
						dto.setParentPathPhoto(pathFP);
						dto.setFileNamePhoto("Photograph.JPG");
						boolean flag = false;
						if (transPartyList != null && transPartyList.size() > 0 && witnessList != null && witnessList.size() > 0) {
							flag = regmkrBD.updatePhotographDetails(eForm, UserId, cdate, dto.getRegNumber());
						}
						if (flag && insrtPrtyPrsntDet) {
							flag = regmkrBD.UpdateRegistrationCompletionStatus(dto.getRegNumber(), RegCompConstant.PHOTOGRAPH_STATUS, cdate, UserId);
							if (flag) {
								forwardJsp = "PhotographCapture";
							} else {
								session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
								forwardJsp = "failuremaker";
							}
						} else {
							session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
							forwardJsp = "failuremaker";

						}
						// **************************************************//

					}

					/*
					 * if("UPLOAD_ACTION".equalsIgnoreCase(actionName)) {
					 * forwardJsp="upload"; }
					 */
				}

				if ("photographDetails".equalsIgnoreCase(formName)) {
					if (RegCompConstant.NEXT_ACTION.equalsIgnoreCase(actionName)) {
						// *****photograph Details Insertion*******//
						// update party
						// vinay photo
						// boolean flag =
						// regmkrBD.updatePhotographDetails(eForm, UserId,
						// cdate, dto.getRegNumber());
						// logger.debug("******photograph Details"+flag);

						// *****************************************//
						// commented on 3/4/2013 to remove witness from
						// presentation page
						/*
						 * boolean updtOthrDeedDtlTbl = regmkrBD
						 * .updtOthrDeedDtlTbl(hdnPresentWitness, cdate, UserId,
						 * regNum);
						 */

						// this code is for prerequisite checking of compliance
						// page
						propertyRelatedComplianceMap = regmkrBD.getPropertyRelatedComplianeList(dto.getRegNumber());
						eForm.setCompliancePropertyRelatedMap(propertyRelatedComplianceMap);

						partyRelatedComplianceMap = regmkrBD.getPartyRelatedComplianeList(dto.getRegNumber(), language);
						eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
						// commented by SIMRAN: - 6-Jan
						// witnessPhotoUploadMap =
						// regmkrBD.getWitnessDetailsForCompliance
						// (dto.getRegNumber());
						//eForm.setUploadWitnessPhotograph(witnessPhotoUploadMap
						// );
						// ArrayList witnessList =
						// regmkrBD.getWitnessList(dto.getRegNumber(),cdate);
						// eForm.setWitnessList(witnessList);
						// forwardJsp = "successtest";
						String deedid = regmkrBD.getDeedId(dto.getRegNumber());
						request.setAttribute("deedId", deedid);

						boolean flag = regmkrBD.UpdateRegistrationCompletionStatus(dto.getRegNumber(), RegCompConstant.COMPLIANCE_LIST_STATUS, cdate, UserId);
						if (flag) {
							forwardJsp = "complianceListNew";
						} else {
							session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
							forwardJsp = "failuremaker";
						}
					}

					if ("PHOTOGRAPH_CAPTURE".equalsIgnoreCase(actionName)) {
						boolean flag = false;
						if (dto.getPartyIdUpload() != "" && dto.getPartyIdUpload() != null) {
							//logger.debug("partyId----"+formDTO.getPartyIdUpload
							// ());

							ArrayList partyList = eForm.getTransactPartyList();

							Iterator itr = partyList.iterator();
							while (itr.hasNext()) {
								RegCompleteMakerDTO rrdto = (RegCompleteMakerDTO) itr.next();
								String partyId = rrdto.getPartyTypeID();
								if (dto.getPartyIdUpload().equalsIgnoreCase(partyId)) {
									logger.debug("************MATCH");
									rrdto.setPartyPhotoName("Photograph.JPG");
									rrdto.setPartyPhotoPath(FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_PHOTO + partyId + "/" + rrdto.getPartyPhotoName());
									rrdto.setPhotoChkParty("Y");
									flag = bd.imageCheck(rrdto.getPartyPhotoPath());
									if (flag) {
										flag = regmkrBD.updatePhotoDetails(partyId, rrdto.getPartyPhotoName(), rrdto.getPartyPhotoPath());
									}
									if (flag) {

									} else {
										rrdto.setPartyPhotoName("");
										rrdto.setPartyPhotoPath("");
										rrdto.setPhotoChkParty("N");
										ArrayList error = new ArrayList();
										error.add("Unable to upload file. Please try again.");
										request.setAttribute("ERROR_LIST", error);
										request.setAttribute("ERROR_FLAG", "true");
									}

								}
							}
							dto.setPartyIdUpload("");
						}
						if (dto.getWitIdUpload() != null && dto.getWitIdUpload() != "") {
							logger.debug("wit Id" + dto.getWitIdUpload());
							ArrayList partyList2 = eForm.getWitnessList();

							Iterator itr2 = partyList2.iterator();
							while (itr2.hasNext()) {
								RegCompleteMakerDTO rrdto = (RegCompleteMakerDTO) itr2.next();
								String partyId = rrdto.getWitnessSno();
								if (dto.getWitIdUpload().equalsIgnoreCase(partyId)) {
									logger.debug("************MATCH");
									rrdto.setWitnessPhotoName("Photograph.JPG");
									rrdto.setWitnessPhotoPath(FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_PHOTO + partyId + "/" + rrdto.getWitnessPhotoName());
									rrdto.setPhotoChkWitness("Y");
									flag = bd.imageCheck(rrdto.getWitnessPhotoPath());
									if (flag) {
										flag = regmkrBD.updatePhotoDetailsWitness(partyId, rrdto.getWitnessPhotoName(), rrdto.getWitnessPhotoPath());
									}
									if (flag) {
										rrdto.setWitnessPhotoName("Photograph.JPG");
										rrdto.setWitnessPhotoPath(FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_PHOTO + partyId + "/" + rrdto.getWitnessPhotoName());
										rrdto.setPhotoChkWitness("Y");
									} else {
										rrdto.setWitnessPhotoName("");
										rrdto.setWitnessPhotoPath("");
										rrdto.setPhotoChkWitness("N");
										ArrayList error = new ArrayList();
										error.add("Unable to upload file. Please try again.");
										request.setAttribute("ERROR_LIST", error);
										request.setAttribute("ERROR_FLAG", "true");
									}
								}
							}
							dto.setWitIdUpload("");
						}
						if (dto.getConsenterIdUpload() != null && dto.getConsenterIdUpload() != "") {
							logger.debug("consenter Id" + dto.getConsenterIdUpload());
							ArrayList partyList2 = eForm.getConsenterList();

							Iterator itr2 = partyList2.iterator();
							while (itr2.hasNext()) {
								RegCompleteMakerDTO rrdto = (RegCompleteMakerDTO) itr2.next();
								String partyId = rrdto.getConsenterSno();
								if (dto.getConsenterIdUpload().equalsIgnoreCase(partyId)) {
									logger.debug("************MATCH");
									rrdto.setConsenterPhotoName("Photograph.JPG");
									rrdto.setConsenterPhotoPath(FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_PHOTO + partyId + "/" + rrdto.getConsenterPhotoName());
									rrdto.setPhotoChkConsenter("Y");
									flag = bd.imageCheck(rrdto.getConsenterPhotoPath());
									if (flag) {
										flag = regmkrBD.updatePhotoDetailsConsenter(partyId, rrdto.getConsenterPhotoName(), rrdto.getConsenterPhotoPath());
									}
									if (flag) {
										rrdto.setConsenterPhotoName("Photograph.JPG");
										rrdto.setConsenterPhotoPath(FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompCheckerConstant.UPLOAD_PATH_PARTY_PHOTO + partyId + "/" + rrdto.getConsenterPhotoName());
										rrdto.setPhotoChkConsenter("Y");
									} else {
										rrdto.setConsenterPhotoName("");
										rrdto.setConsenterPhotoPath("");
										rrdto.setPhotoChkConsenter("N");
										ArrayList error = new ArrayList();
										error.add("Unable to upload file. Please try again.");
										request.setAttribute("ERROR_LIST", error);
										request.setAttribute("ERROR_FLAG", "true");
									}

								}
							}
							dto.setConsenterIdUpload("");
						}
						forwardJsp = "PhotographCapture";
					}

				}

				if ("checklist".equalsIgnoreCase(formName)) {

					if ("CONTINUE_HOLD".equalsIgnoreCase(actionName)) {

						eForm.setHldBtnFlg("hold");
						eForm.setCheckHold("");
						dto.setContinueHold("Y");
						dto.setRemarks(regmkrBD.getHoldRemarks(dto.getRegNumber(), "2"));
						ArrayList caveatdet = regmkrBD.getCaveatDet(dto.getRegNumber());
						eForm.setCaveatList(caveatdet);
						eForm.setCaveatListSize(caveatdet.size());
						eForm.setCommonDTO(cdto);
						ArrayList bnkCaveatdet = regmkrBD.getBnkCaveatDet(dto.getRegNumber());
						eForm.setBnkCaveatList(bnkCaveatdet);
						eForm.setBnkCaveatListSize(bnkCaveatdet.size());
						eForm.setExeDate(bd.getExecDate(dto.getRegNumber()));
						ArrayList partyDetls = regmkrBD.getTransPartyDets(dto.getRegNumber());
						eForm.setPartyList(partyDetls);
						eForm.setActionName("");
						String filePath = FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_DEATH_CERT;
						dto.setDeathCert(RegCompCheckerConstant.FILE_NAME_DEATH_CERT);
						dto.setFilePath(filePath);
						String filePathRelation = FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_RELN_PROOF;
						dto.setRelationProofName(RegCompConstant.FILE_NAME_RELTN_PROOF);
						dto.setFilePathRltn(filePathRelation);
						dto.setFilePathPOA(FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_POA_DOC);
						dto.setPoaUploadFilename(RegCompCheckerConstant.FILE_NAME_POA_DOC);
						dto.setSuppDocFileName("Unhold_Checklist.jpg");
						dto.setSuppDocFilePath(FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_SUPP_DOC);
						// regmkrBD.UpdateRegistrationCompletionStatus(dto.
						// getRegNumber(), RegCompConstant.CHECKLIST_STATUS,
						// cdate, UserId);
						forwardJsp = "checklist";
					}
					if (RegCompConstant.HOLD_ACTION.equalsIgnoreCase(actionName)) {
						ArrayList getHoldList = regmkrBD.getHoldData();
						eForm.setChkHoldClick("C"); // c stands for hold buttons
						// clicked
						eForm.setHoldListDisp(getHoldList);
						// logger.debug("shdhsdhs----->"+dto.getHoldName());
						eForm.setActionName("");
						forwardJsp = "checklist";
					}

					if (RegCompConstant.SAVE_HOLD_ACTION.equalsIgnoreCase(actionName)) {
						// Start :====code for storing Date when hold
						// *******************ADDED BY
						// SIMRAN*******************//
						String partyID = dto.getPartyTypeID();
						if (!(partyID.equals(null) || (partyID.equals("")))) {

							ArrayList docs = new ArrayList();
							RegCompleteMakerDTO gDTO = new RegCompleteMakerDTO();
							gDTO.setDeatCertContents(dto.getDeatCertContents());
							gDTO.setUpldDeathCert(dto.getUpldDeathCert());
							gDTO.setReltnProofContents(dto.getReltnProofContents());
							gDTO.setUplaReltnProof(dto.getUplaReltnProof());
							gDTO.setTxtArea(eForm.getComments());
							gDTO.setFilePath(dto.getFilePath() + "/" + dto.getPartyTypeID() + "/" + dto.getDeathCert());
							gDTO.setFilePathRltn(dto.getFilePathRltn() + "/" + dto.getPartyTypeID() + "/" + dto.getRelationProof());
							docs.add(gDTO);
							deathCertList = eForm.getUploadDthList();
							deathCertList.put(partyID, docs);
						}

						RegCompleteMakerDTO rdto = new RegCompleteMakerDTO();
						if (!eForm.getPoaAuthNum().equals("")) {
							if (eForm.getPoaFlg().equals("1")) {
								rdto.setPoaFlg(RegCompConstant.POA_OUTSIDE_MP);
							} else {
								rdto.setPoaFlg(RegCompConstant.POA_FROM_MP);
							}
							rdto.setPoaAuthNum(eForm.getPoaAuthNum());
							rdto.setFilePathPOA(dto.getFilePathPOA() + "/" + eForm.getPoaAuthNum() + "/" + dto.getUploadPoaDoc());
							rdto.setPoaUploadContents(dto.getPoaUploadContents());
							rdto.setPoaUploadSize(dto.getPoaUploadSize());
							rdto.setUploadPoaDoc(dto.getUploadPoaDoc());
							ArrayList poa = new ArrayList();
							poa.add(rdto);
							poaList = eForm.getPoaList();
							int count = eForm.getPoaCount();
							if (count == 0) {
								count = 1;

							}
							// logger.debug("count"+count);
							if (poaList.containsKey(Integer.toString(count))) {

								logger.debug("if");
								count++;
								poaList.put(Integer.toString(count), poa);

							} else {
								logger.debug("else");
								poaList.put(Integer.toString(count), poa);
							}

							eForm.setPoaCount(count);
							//logger.debug("<-----size of Poa List"+poaList.size
							// ());
						}
						// *******************END----ADDED BY
						// SIMRAN*******************//
						dto.setExeDate((String) eForm.getExeDate());

						if ((String) eForm.getExeOutIndDate() != null && !((String) eForm.getExeOutIndDate()).equalsIgnoreCase("")) {
							dto.setExeOutIndFlg("Y");// executed outside India
							dto.setExeOutIndDate((String) eForm.getExeDate());
						} else {
							dto.setExeOutIndFlg("");// executed outside India
							dto.setExeOutIndDate("");
						}

						if ((String) eForm.getOrdrDate() != null && !((String) eForm.getOrdrDate()).equalsIgnoreCase("")) {
							dto.setOrdrDate((String) eForm.getOrdrDate());
						} else {
							dto.setOrdrDate("");
						}

						if ((String) eForm.getLstAppealDate() != null && (!((String) eForm.getLstAppealDate()).equalsIgnoreCase("")) && (String) eForm.getDcreeOrderDate() != null && (!((String) eForm.getDcreeOrderDate()).equalsIgnoreCase(""))

						) {
							dto.setDcreeOrderDate((String) eForm.getDcreeOrderDate());
							dto.setLstAppealDate((String) eForm.getLstAppealDate());
							dto.setCrtDcrWithAplFlg("Y"); // court decree with
							// appeal

						} else {
							dto.setDcreeOrderDate("");
							dto.setLstAppealDate("");
							dto.setCrtDcrWithAplFlg(""); // court decree with
							// appeal

						}

						if ((String) eForm.getComments() != null && ((String) eForm.getComments()).equalsIgnoreCase("")) {

							dto.setComments(eForm.getComments());
						} else {
							dto.setComments("");
						}

						if (eForm.getPoaAuthNum() != null && eForm.getPoaAuthNum().equalsIgnoreCase("")) {
							dto.setPoaAuthNum(eForm.getPoaAuthNum());
							dto.setPoaFlg("Y");
						} else {
							dto.setPoaAuthNum("");
						}
						// *******************ADDED BY
						// SIMRAN*******************//
						/*
						 * deathCertList = new HashMap(); deathCertList =
						 * eForm.getUploadDthList(); if(deathCertList.size()!= 0
						 * ) { String fileName = "";
						 * dto.setDocAfterDeathFlg("Y"); Set mapSet =
						 * (Set)deathCertList.entrySet(); Iterator mapIterator =
						 * mapSet.iterator(); while(mapIterator.hasNext()) {
						 * Map.Entry mapEntry = (Map.Entry)mapIterator.next();
						 * ArrayList valueList = (ArrayList)mapEntry.getValue();
						 * RegCompleteMakerDTO rDTO =
						 * (RegCompleteMakerDTO)valueList.get(0); String partyId
						 * = (String)mapEntry.getKey();
						 * if(rDTO.getDeatCertContents() != null) { fileName =
						 * uploadFile(dto.getRegNumber(),
						 * rDTO.getDeatCertContents
						 * (),partyId,RegCompConstant.UPLOAD_PATH_DEATH_CERT,
						 * RegCompConstant.FILE_NAME_DTH_CERTIFICATE);
						 * 
						 * if(fileName != null && rDTO.getReltnProofContents()
						 * != null) { rDTO.setFilePath(fileName); fileName =
						 * uploadFile(dto.getRegNumber(),
						 * rDTO.getReltnProofContents
						 * (),partyId,RegCompConstant.UPLOAD_PATH_RELN_PROOF,
						 * RegCompConstant.FILE_NAME_RELTN_PROOF); if(fileName!=
						 * null) { rDTO.setFilePathRltn(fileName); } } }
						 * if(fileName == null) { messages.add("MSG1", new
						 * ActionMessage( "upload_error"));
						 * saveMessages(request, messages);
						 * eForm.setErrLnkFlg("U"); eForm.setActionName("");
						 * forwardJsp = "checklist"; } } } poaList = new
						 * HashMap(); poaList = eForm.getPoaList();
						 * //logger.debug("size of POA MAP"+poaList.size());
						 * if(poaList.size()!= 0 ) { String fileName = "";
						 * 
						 * Set mapSet = (Set)poaList.entrySet(); Iterator
						 * mapIterator = mapSet.iterator();
						 * while(mapIterator.hasNext()) { Map.Entry mapEntry =
						 * (Map.Entry)mapIterator.next(); ArrayList valueList =
						 * (ArrayList)mapEntry.getValue(); RegCompleteMakerDTO
						 * rDTO = (RegCompleteMakerDTO)valueList.get(0); String
						 * poaAuthNo = rDTO.getPoaAuthNum();
						 * if(rDTO.getPoaUploadContents() != null) { fileName =
						 * uploadFile(dto.getRegNumber(),
						 * rDTO.getPoaUploadContents
						 * (),poaAuthNo,RegCompConstant.UPLOAD_PATH_POA_DOC,
						 * RegCompConstant.FILE_NAME_POA_DOC);
						 * 
						 * if(fileName != null) { rDTO.setFilePathPOA(fileName);
						 * } } if(fileName == null) { messages.add("MSG1", new
						 * ActionMessage( "upload_error"));
						 * saveMessages(request, messages);
						 * eForm.setErrLnkFlg("U"); eForm.setActionName("");
						 * forwardJsp = "checklist"; } } }
						 */
						/*
						 * if(regmkrBD.checkListData(dto.getRegNumber(), UserId,
						 * cdate)) { boolean flg = regmkrBD .saveChecklist(dto,
						 * cdate, UserId,deathCertList,poaList); } else {
						 * 
						 * }
						 */
						// *******************END---ADDED BY
						// SIMRAN*******************//
						// end :====code for storing Date when hold
						// Start=== this code for saving the data in the hold
						// table
						String regNum = dto.getRegNumber();
						String hldFlag = "Y";
						String fwdPage = eForm.formName;
						String createdBy = UserId;
						String Date = cdate;
						String holdId = "2";
						String holdRemarks = dto.getTxtArea();
						String str = regmkrBD.saveHoldData(regNum, hldFlag, fwdPage, Date, createdBy, holdId, holdRemarks, officeId);
						request.setAttribute("HOLD_MESSAGE", regmkrBD.getEmailHoldContent(regNum, holdRemarks, officeId, language, officeId));

						// End=== this code for saving the data in the hold
						// table

						// code commented by simran--- 3 june
						/*
						 * if (str.equalsIgnoreCase("1")) { {
						 * if(!holdId.equalsIgnoreCase("") &&
						 * !holdId.equalsIgnoreCase(null)) { String holdName =
						 * regmkrBD.getHoldName(holdId);
						 * eForm.setHoldReason(holdName); }
						 * 
						 * forwardJsp = "holdchecklist"; }
						 * 
						 * }
						 */
						// EtokenChange eChange = new
						// EtokenChange(dto.getRegNumber(),"11",null);
						// / Thread t = new Thread(eChange);
						// t.start();
						forwardJsp = "holdchecklist";
					}

					if (RegCompConstant.NEXT_ACTION.equals(actionName)) {
						// *******************ADDED BY
						// SIMRAN*******************//
						String partyID = dto.getPartyTypeID();
						deathCertList = eForm.getUploadDthList();
						poaList = eForm.getPoaList();
						if (!(partyID.equals(null) || (partyID.equals("")))) {
							ArrayList docs = new ArrayList();
							RegCompleteMakerDTO gDTO = new RegCompleteMakerDTO();
							gDTO.setDeatCertContents(dto.getDeatCertContents());
							gDTO.setUpldDeathCert(dto.getUpldDeathCert());
							gDTO.setReltnProofContents(dto.getReltnProofContents());
							gDTO.setUplaReltnProof(dto.getUplaReltnProof());
							gDTO.setTxtArea(eForm.getComments());
							gDTO.setFilePath(dto.getFilePath() + "/" + dto.getPartyTypeID() + "/" + dto.getDeathCert());
							gDTO.setFilePathRltn(dto.getFilePathRltn() + "/" + dto.getPartyTypeID() + "/" + dto.getRelationProof());
							docs.add(gDTO);

							deathCertList.put(partyID, docs);
						}
						RegCompleteMakerDTO rdto = new RegCompleteMakerDTO();

						if (!eForm.getPoaAuthNum().equals("")) {
							if (eForm.getPoaFlg().equals("1")) {
								rdto.setPoaFlg(RegCompConstant.POA_OUTSIDE_MP);
							} else {
								rdto.setPoaFlg(RegCompConstant.POA_FROM_MP);
							}
							rdto.setPoaAuthNum(eForm.getPoaAuthNum());
							rdto.setFilePathPOA(dto.getFilePathPOA() + "/" + eForm.getPoaAuthNum() + "/" + dto.getUploadPoaDoc());
							rdto.setPoaUploadContents(dto.getPoaUploadContents());
							rdto.setPoaUploadSize(dto.getPoaUploadSize());
							rdto.setUploadPoaDoc(dto.getUploadPoaDoc());
							ArrayList poa = new ArrayList();
							poa.add(rdto);

							int count = eForm.getPoaCount();
							if (count == 0) {
								count = 1;

							}
							// logger.debug("count"+count);
							if (poaList.containsKey(Integer.toString(count))) {

								logger.debug("if");
								count++;
								poaList.put(Integer.toString(count), poa);

							} else {
								logger.debug("else");
								poaList.put(Integer.toString(count), poa);
							}

							eForm.setPoaCount(count);
							//logger.debug("<-----size of Poa List"+poaList.size
							// ());
						}
						// *******************END---ADDED BY
						// SIMRAN*******************//
						dto.setExeDate((String) eForm.getExeDate());

						if ((String) eForm.getExeOutIndDate() != null && !((String) eForm.getExeOutIndDate()).equalsIgnoreCase("")) {
							dto.setExeOutIndFlg("Y");// executed outside India
							dto.setExeOutIndDate((String) eForm.getExeOutIndDate());
						} else {
							dto.setExeOutIndFlg("");// executed outside India
							dto.setExeOutIndDate("");
						}

						if ((String) eForm.getOrdrDate() != null && !((String) eForm.getOrdrDate()).equalsIgnoreCase("")) {
							dto.setOrdrDate((String) eForm.getOrdrDate());
							dto.setCourtDecreeFlg("Y");
						} else {
							dto.setOrdrDate("");
						}

						if ((String) eForm.getLstAppealDate() != null && (!((String) eForm.getLstAppealDate()).equalsIgnoreCase("")) && (String) eForm.getDcreeOrderDate() != null && (!((String) eForm.getDcreeOrderDate()).equalsIgnoreCase(""))

						) {
							dto.setDcreeOrderDate((String) eForm.getDcreeOrderDate());
							dto.setLstAppealDate((String) eForm.getLstAppealDate());
							dto.setCrtDcrWithAplFlg("Y"); // court decree with
							// appeal

						} else {
							dto.setDcreeOrderDate("");
							dto.setLstAppealDate("");
							dto.setCrtDcrWithAplFlg(""); // court decree with
							// appeal

						}

						if ((String) eForm.getComments() != null && !((String) eForm.getComments()).equalsIgnoreCase("")) {

							dto.setComments(eForm.getComments());
						} else {
							dto.setComments("");
						}

						if (eForm.getPoaAuthNum() != null && !(eForm.getPoaAuthNum().equalsIgnoreCase(""))) {
							dto.setPoaAuthNum(eForm.getPoaAuthNum());
							dto.setPoaFlg("Y");
						} else {
							dto.setPoaAuthNum("");
						}
						if (deathCertList.size() > 0) {
							dto.setDocAfterDeathFlg("Y");
						}
						if (poaList.size() > 0) {
							dto.setPoaFlg("Y");
						}
						// *******************ADDED BY
						// SIMRAN*******************//
						/*
						 * deathCertList = new HashMap();
						 * 
						 * deathCertList = eForm.getUploadDthList();
						 * if(deathCertList.size()!= 0 ) { String fileName = "";
						 * dto.setDocAfterDeathFlg("Y"); Set mapSet =
						 * (Set)deathCertList.entrySet(); Iterator mapIterator =
						 * mapSet.iterator(); while(mapIterator.hasNext()) {
						 * Map.Entry mapEntry = (Map.Entry)mapIterator.next();
						 * ArrayList valueList = (ArrayList)mapEntry.getValue();
						 * RegCompleteMakerDTO rDTO =
						 * (RegCompleteMakerDTO)valueList.get(0); String partyId
						 * = (String)mapEntry.getKey();
						 * if(rDTO.getDeatCertContents() != null) { fileName =
						 * uploadFile(dto.getRegNumber(),
						 * rDTO.getDeatCertContents
						 * (),partyId,RegCompConstant.UPLOAD_PATH_DEATH_CERT,
						 * RegCompConstant.FILE_NAME_DTH_CERTIFICATE);
						 * 
						 * if(fileName != null && rDTO.getReltnProofContents()
						 * != null) { rDTO.setFilePath(fileName); fileName =
						 * uploadFile(dto.getRegNumber(),
						 * rDTO.getReltnProofContents
						 * (),partyId,RegCompConstant.UPLOAD_PATH_RELN_PROOF,
						 * RegCompConstant.FILE_NAME_RELTN_PROOF); if(fileName!=
						 * null) { rDTO.setFilePathRltn(fileName); } } }
						 * if(fileName == null) { messages.add("MSG1", new
						 * ActionMessage( "upload_error"));
						 * saveMessages(request, messages);
						 * eForm.setErrLnkFlg("U"); eForm.setActionName("");
						 * forwardJsp = "checklist"; } } }
						 * 
						 * poaList = new HashMap(); poaList =
						 * eForm.getPoaList(); if(poaList.size()!= 0 ) { String
						 * fileName = "";
						 * 
						 * Set mapSet = (Set)poaList.entrySet(); Iterator
						 * mapIterator = mapSet.iterator();
						 * while(mapIterator.hasNext()) { Map.Entry mapEntry =
						 * (Map.Entry)mapIterator.next(); ArrayList valueList =
						 * (ArrayList)mapEntry.getValue(); RegCompleteMakerDTO
						 * rDTO = (RegCompleteMakerDTO)valueList.get(0); String
						 * poaAuthNo = rDTO.getPoaAuthNum();
						 * if(rDTO.getPoaUploadContents() != null) { fileName =
						 * uploadFile(dto.getRegNumber(),
						 * rDTO.getPoaUploadContents
						 * (),poaAuthNo,RegCompConstant.UPLOAD_PATH_POA_DOC,
						 * RegCompConstant.FILE_NAME_POA_DOC);
						 * 
						 * if(fileName != null) { rDTO.setFilePathPOA(fileName);
						 * } } if(fileName == null) { messages.add("MSG1", new
						 * ActionMessage( "upload_error"));
						 * saveMessages(request, messages);
						 * eForm.setErrLnkFlg("U"); eForm.setActionName("");
						 * forwardJsp = "checklist"; } } }
						 */

						// if(regmkrBD.checkListData(dto.getRegNumber(), UserId,
						// cdate))
						// {
						boolean flag1 = false;
						boolean flg = regmkrBD.saveChecklist(dto, cdate, UserId, deathCertList, poaList);
						if (!flg) {
							session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
							forwardJsp = "failuremaker";
							return mapping.findForward("failureMaker");
						}
						logger.debug("continue hold" + dto.getContinueHold());
						if (dto.getContinueHold() != null) {
							if (dto.getContinueHold().equalsIgnoreCase("Y")) {

								flag1 = regmkrBD.saveHoldResumeDetails(dto.getTxtArea(), dto.getSuppDocFileName(), dto.getSuppDocFilePath(), dto.getRegNumber(), "9");
								if (flag1) {
									boolean updtHoldTbl = regmkrBD.updtHoldTbl(dto.getRegNumber(), UserId, cdate, "N");
								}
							}
						}

						// }
						// else
						// {

						// }

						// ******code added on 12-sep for email
						// alert******************//
						String status = regmkrBD.emailAlertPresentation(dto.getExeDate(), dto.getRegNumber());
						// logger.debug("***************status"+status);
						// *******************END---ADDED BY
						// SIMRAN*******************//

						/*
						 * if(flg) {
						 */
						// start:==update hold flag in hold table if the page
						// was put on hold
						// String ifHold = eForm.getHldBtnFlg();
						// if (!ifHold.equalsIgnoreCase("")) {
						// if (ifHold.equalsIgnoreCase("hold")) {
						;
						// }
						// String hldFlg = "N";
						// boolean updtHoldTbl = regmkrBD.updtHoldTbl(dto
						// .getRegNumber(), UserId, cdate, hldFlg);
						// eForm.setHldBtnFlg("");
						// }

						// end:==update hold flag in hold table if the page was
						// put on hold

						// //////////BELOW CODE COMMENTED BY SIMRAN
						// ////////////////////////

						/*
						 * ArrayList getlnkdamt = regmkrBD.getlnkdamt(dto
						 * .getRegNumber()); if (getlnkdamt.size() != 0) {
						 * String str = getlnkdamt.toString(); str =
						 * str.substring(2, (str.length() - 2)); String[] str1 =
						 * str.split(","); String sd = (str1[0]); String rf =
						 * (str1[1]); if (sd == null || sd.equalsIgnoreCase(""))
						 * { sd = "0"; } if (rf == null ||
						 * rf.equalsIgnoreCase("")) { rf = "0"; } // commented
						 * because fee is not to be paid here. //
						 * if(sd.equalsIgnoreCase("0") && //
						 * rf.equalsIgnoreCase("0")) if
						 * (sd.equalsIgnoreCase("0")) { forwardJsp = "success1";
						 * } else { double regduty = Double.parseDouble(sd);
						 * double regfee = Double.parseDouble(rf); // double
						 * total = regduty +regfee; double total = regduty;
						 * logger.debug("data" + regduty + regfee + total);
						 * 
						 * dto.setRegfee(regfee); dto.setStampduty(regduty);
						 * dto.setTotalamount(total); eForm.setRegDTO(dto);
						 * 
						 * // End:==== code for getting caveat details
						 * 
						 * // } forwardJsp = "proceedtopayment"; } } else {
						 * 
						 * boolean flg1 = regmkrBD.updtStatus(dto
						 * .getRegNumber(), UserId, cdate); forwardJsp =
						 * "success1"; }
						 */
						/*
						 * boolean flg1 = regmkrBD.updtStatus(dto
						 * .getRegNumber(), UserId, cdate);
						 */

						// boolean updtHoldTbl =
						// regmkrBD.updtHoldTbl(dto.getRegNumber(),
						// UserId, cdate, "N");
						/*
						 * EtokenChange eChange = new
						 * EtokenChange(dto.getRegNumber(),"13",null); Thread t
						 * = new Thread(eChange); t.start();
						 */

						String execDate = bd.getExecDate(dto.getRegNumber());
						boolean check = bd.dateComparison(execDate);
						if (!check) {
							String caseDetails = bd.updateTimeBarrdCaseDetails(dto.getRegNumber(), cdate, userId, "48");
							String status1 = bd.emailAlertHold(dto.getRegNumber(), officeId);
							dto.setCaseNumber(caseDetails);
							dto.setCaseNumber(caseDetails);
							dto.setIsCase("Y");
							forwardJsp = "confirmScreen";
						} else {
							boolean flag = regmkrBD.UpdateRegistrationCompletionStatus(dto.getRegNumber(), RegCompConstant.VERIFICATION_STATUS, cdate, UserId);
							if (flag) {
								forwardJsp = "verification";
							} else {
								session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
								forwardJsp = "failuremaker";
							}
						}
						// ///////////////////////////////////////////////
					}

					ArrayList errorList = new ArrayList();
					String frwdName = request.getParameter("fwdName");
					String index = request.getParameter("index");

					if ("deathcert".equalsIgnoreCase(frwdName)) {

						if ("setUploadFile".equalsIgnoreCase(actionName)) {
							/*
							 * try { FormFile uploadedFile = dto
							 * .getDeathCertificate(); if
							 * ("".equals(uploadedFile.getFileName())) {
							 * errorList
							 * .add("Invalid file selection. Please try again."
							 * ); } // formDTO.setDocumentName(null); //
							 * formDTO.setDocContents(null); String fileExt =
							 * getFileExtension(uploadedFile.getFileName());
							 * AuditInspectionRule rule = new
							 * AuditInspectionRule();
							 * rule.validateFileType(fileExt); int size =
							 * uploadedFile.getFileSize(); double
							 * fileSizeInBytes = size; // Convert the bytes to
							 * Kilobytes (1 KB = 1024 // Bytes) double
							 * fileSizeInKB = fileSizeInBytes / 1024.0; //
							 * Convert the KB to MegaBytes (1 MB = 1024 //
							 * KBytes) double fileSizeInMB = fileSizeInKB /
							 * 1024.0; DecimalFormat decim = new
							 * DecimalFormat("#.##"); Double fileSize =
							 * Double.parseDouble(decim .format(fileSizeInMB));
							 * String photoSize = "(" + fileSize + "MB)"; if
							 * (rule.isError()) { errorList
							 * .add("Invalid file type. Please select another file."
							 * );
							 * 
							 * request.setAttribute("errorsList", errorList); }
							 * else { if (size >
							 * AuditInspectionConstant.MAX_FILE_SIZE) {
							 * errorList.add(
							 * "File size is Greater than 10 MB. Please select another file."
							 * ); request.setAttribute("errorsList", errorList);
							 * } else { //commented by Simran
							 * //uploadFile(uploadedFile, uploadedFile //
							 * .getFileName(), dto //.getRegNumber());
							 * dto.setUpldDeathCert(uploadedFile
							 * .getFileName());
							 * dto.setDeatCertContents(uploadedFile
							 * .getFileData()); dto.setDeathCertSize(photoSize);
							 * eForm.setRegDTO(dto); } } } catch (Exception e) {
							 * errorList
							 * .add("Unable to upload file. Please try again.");
							 * request.setAttribute("errorsList", errorList); }
							 */
							logger.debug("^^^^^^" + dto.getUploadDoc());
							if (dto.getUploadDoc().equalsIgnoreCase("deathCert")) {
								dto.setUpldDeathCert(RegCompConstant.FILE_NAME_DTH_CERTIFICATE);
								dto.setUpldDeathCertPath(dto.getFilePath() + "/" + dto.getPartyTypeID() + "/" + RegCompConstant.FILE_NAME_DTH_CERTIFICATE);
							} else if (dto.getUploadDoc().equalsIgnoreCase("RelationProof")) {
								dto.setUplaReltnProof(RegCompConstant.FILE_NAME_RELTN_PROOF);
								dto.setUplaReltnProofPath(dto.getFilePathRltn() + "/" + dto.getPartyTypeID() + "/" + RegCompConstant.FILE_NAME_RELTN_PROOF);
							} else if (dto.getUploadDoc().equalsIgnoreCase("supportingDoc")) {
								dto.setUploadSuppDoc("Unhold_checklist.jpg");
								dto.setUploadSuppDocPath(dto.getSuppDocFilePath() + "/Unhold_checklist.jpg");
							} else {
								dto.setUploadPoaDoc(RegCompConstant.FILE_NAME_POA_DOC);
								dto.setUploadPoaDocPath(dto.getFilePathPOA() + "/" + eForm.getPoaAuthNum() + "/" + RegCompConstant.FILE_NAME_POA_DOC);
							}
						}

						eForm.setActionName("");
						forwardJsp = "checklist";

					}

					if ("reltnshpproof".equalsIgnoreCase(frwdName)) {

						if ("setUploadFile".equalsIgnoreCase(actionName)) {
							try {
								FormFile uploadedFile = dto.getRelationProof();
								if ("".equals(uploadedFile.getFileName())) {
									errorList.add("Invalid file selection. Please try again.");
								}
								// formDTO.setDocumentName(null);
								// formDTO.setDocContents(null);
								String fileExt = getFileExtension(uploadedFile.getFileName());
								AuditInspectionRule rule = new AuditInspectionRule();
								rule.validateFileType(fileExt);
								int size = uploadedFile.getFileSize();
								double fileSizeInBytes = size;
								// Convert the bytes to Kilobytes (1 KB = 1024
								// Bytes)
								double fileSizeInKB = fileSizeInBytes / 1024.0;
								// Convert the KB to MegaBytes (1 MB = 1024
								// KBytes)
								double fileSizeInMB = fileSizeInKB / 1024.0;
								DecimalFormat decim = new DecimalFormat("#.##");
								Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
								String photoSize = "(" + fileSize + "MB)";

								if (rule.isError()) {
									errorList.add("Invalid file type. Please select another file.");

									request.setAttribute("errorsList", errorList);
								} else {
									if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
										errorList.add("File size is Greater than 10 MB. Please select another file.");
										request.setAttribute("errorsList", errorList);
									} else {
										// commented by Simran
										// uploadFile(uploadedFile, uploadedFile
										// .getFileName(), dto
										// .getRegNumber());
										dto.setUplaReltnProof(uploadedFile.getFileName());
										dto.setReltnProofContents(uploadedFile.getFileData());
										dto.setReltnProofSize(photoSize);
										eForm.setRegDTO(dto);
									}
								}
							} catch (Exception e) {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
						}

						eForm.setActionName("");
						forwardJsp = "checklist";

					}

					if ("poaDoc".equalsIgnoreCase(frwdName)) {

						if ("setUploadFile".equalsIgnoreCase(actionName)) {
							try {
								FormFile uploadedFile = dto.getPoaDocument();
								if ("".equals(uploadedFile.getFileName())) {
									errorList.add("Invalid file selection. Please try again.");
								}
								// formDTO.setDocumentName(null);
								// formDTO.setDocContents(null);
								String fileExt = getFileExtension(uploadedFile.getFileName());
								AuditInspectionRule rule = new AuditInspectionRule();
								rule.validateFileType(fileExt);
								int size = uploadedFile.getFileSize();
								double fileSizeInBytes = size;
								// Convert the bytes to Kilobytes (1 KB = 1024
								// Bytes)
								double fileSizeInKB = fileSizeInBytes / 1024.0;
								// Convert the KB to MegaBytes (1 MB = 1024
								// KBytes)
								double fileSizeInMB = fileSizeInKB / 1024.0;
								DecimalFormat decim = new DecimalFormat("#.##");
								Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
								String photoSize = "(" + fileSize + "MB)";

								if (rule.isError()) {
									errorList.add("Invalid file type. Please select another file.");

									request.setAttribute("errorsList", errorList);
								} else {
									if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
										errorList.add("File size is Greater than 10 MB. Please select another file.");
										request.setAttribute("errorsList", errorList);
									} else {
										// commented by Simran
										// uploadFile(uploadedFile, uploadedFile
										// .getFileName(), dto
										// .getRegNumber());
										dto.setUploadPoaDoc(uploadedFile.getFileName());
										dto.setPoaUploadContents(uploadedFile.getFileData());
										dto.setPoaUploadSize(photoSize);
										eForm.setRegDTO(dto);
									}
								}
							} catch (Exception e) {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
						}
						eForm.setActionName("");
						forwardJsp = "checklist";

					}

					// added by simran for Maker

					if (("addMorePOA").equalsIgnoreCase(actionName)) {
						RegCompleteMakerDTO rdto = new RegCompleteMakerDTO();
						logger.debug("<-------add more POA");

						if (eForm.getPoaFlg().equals("1")) {
							rdto.setPoaFlg(RegCompConstant.POA_OUTSIDE_MP);
						} else {
							rdto.setPoaFlg(RegCompConstant.POA_FROM_MP);
						}
						rdto.setPoaAuthNum(eForm.getPoaAuthNum());
						rdto.setFilePathPOA(dto.getFilePath() + "/" + eForm.getPoaAuthNum() + "/" + RegCompConstant.FILE_NAME_POA_DOC);
						//rdto.setPoaUploadContents(dto.getPoaUploadContents());
						// rdto.setPoaUploadSize(dto.getPoaUploadSize());
						rdto.setUploadPoaDoc(dto.getUploadPoaDoc());
						rdto.setUploadPoaDocPath(dto.getUploadPoaDocPath());
						ArrayList poa = new ArrayList();
						poa.add(rdto);
						poaList = eForm.getPoaList();
						int count = eForm.getPoaCount();
						if (count == 0) {
							count = 1;

						}
						// logger.debug("count"+count);
						if (poaList.containsKey(Integer.toString(count))) {

							logger.debug("if");
							count++;
							poaList.put(Integer.toString(count), poa);

						} else {
							logger.debug("else");
							poaList.put(Integer.toString(count), poa);
						}

						eForm.setPoaCount(count);
						//logger.debug("<-----size of Poa List"+poaList.size());
						eForm.setPoaList(poaList);
						eForm.setPoaAuthNum("");
						eForm.setTypeOfPoa("");
						dto.setUploadPoaDoc("");
						eForm.setActionName("");
						eForm.setPoaChk("Y");
						forwardJsp = "checklist";

					}

					if ("deletePOA".equalsIgnoreCase(actionName)) {
						String poaKeys[] = eForm.getPoaKeys().split(",");
						poaList = eForm.getPoaList();
						for (int i = 0; i < poaKeys.length; i++) {
							if (poaList.containsKey(poaKeys[i])) {
								poaList.remove(poaKeys[i]);
							}
						}
						//logger.debug("Size of map after deletion"+poaList.size
						// ());
						if (poaList.size() == 0)
							eForm.setPoaChk("N");
						eForm.setPoaList(poaList);
					}

					if ("linkPOA".equalsIgnoreCase(actionName) || "linkPOA".equalsIgnoreCase(request.getParameter("actionName5"))) {
						logger.debug("link POA");
						dto.setPoaAuthNum(request.getParameter("txt"));
						boolean isPOA = regmkrBD.checkPOA(dto.getPoaAuthNum(), dto.getRegNumber());
						if (isPOA) {

							POAAuthenticationFormBO poaBO = new POAAuthenticationFormBO();
							POAAuthenticationForm poaForm = new POAAuthenticationForm();
							String poaId = dto.getPoaAuthNum();
							// logger.debug("POAID******************"+poaId);
							ArrayList poaApplicantDetls = poaBO.getPOAApplicantDetlsForView(poaId);
							poaForm.setPoaApplicntDetlsList(poaApplicantDetls);
							ArrayList poaAwardeeDetls = poaBO.getPOAAwardeeDetlsForView(poaId);
							poaForm.setPoaAwardeeDetlsList(poaAwardeeDetls);
							ArrayList poaAcceptorDetls = poaBO.getPOAAcceptorDetlsForView(poaId);
							poaForm.setPoaAcceptorList(poaAcceptorDetls);
							ArrayList poaCommonDetls = poaBO.getPOACommonDetlsForView(poaId);
							poaForm.setPoaCommonDetlsList(poaCommonDetls);
							poaForm.setActionType("");
							poaForm.setPoaAuthNo(dto.getPoaAuthNum());
							request.setAttribute("poaForm", poaForm);
							forwardJsp = "poaListView2";
						} else {

							POAAuthenticationForm poaForm = new POAAuthenticationForm();
							poaForm.setActionType("error");
							request.setAttribute("poaForm", poaForm);
							poaForm.setPoaAuthNo("");
							forwardJsp = "poaListView2";
							/*
							 * logger.debug("in else----link POA");
							 * messages.add("MSG", new ActionMessage(
							 * "link_poa_error")); saveMessages(request,
							 * messages); eForm.setErrLnkFlg("E"); forwardJsp =
							 * "checklist";
							 */
						}

					}

					if ("addMoreDeathCert".equalsIgnoreCase(actionName)) {
						logger.debug("addMoreDeathCert");
						String partyId = dto.getPartyTypeID();
						// byte[] deathCertContnt = dto.getDeatCertContents();
						// byte[] reltnProofContnt =
						// dto.getReltnProofContents();
						// String deathCertName = dto.getUpldDeathCert();
						// String relnProofName = dto.getUplaReltnProof();
						ArrayList docs = new ArrayList();
						RegCompleteMakerDTO gDTO = new RegCompleteMakerDTO();
						// gDTO.setDeatCertContents(dto.getDeatCertContents());
						gDTO.setUpldDeathCert(dto.getUpldDeathCert());
						gDTO.setUpldDeathCertPath(dto.getUpldDeathCertPath());
						//gDTO.setReltnProofContents(dto.getReltnProofContents()
						// );
						gDTO.setUplaReltnProof(dto.getUplaReltnProof());
						gDTO.setUplaReltnProofPath(dto.getUplaReltnProofPath());
						gDTO.setTxtArea(eForm.getComments());
						gDTO.setFilePath(dto.getFilePath() + "/" + dto.getPartyTypeID() + "/" + dto.getDeathCert());
						gDTO.setFilePathRltn(dto.getFilePathRltn() + "/" + dto.getPartyTypeID() + "/" + dto.getRelationProofName());
						docs.add(gDTO);

						deathCertList = eForm.getUploadDthList();
						// int count = eForm.getDeathCertCount();
						// if (count == 0) {
						// count = 1;

						// }
						// logger.debug("count"+count);
						// if
						// (deathCertList.containsKey(Integer.toString(count)))
						// {

						// logger.debug("if");
						// count++;
						// List.put(Integer.toString(count), poa);

						// } else {
						// logger.debug("else");
						deathCertList.put(partyId, docs);
						// }

						// eForm.setDeathCertCount(count);
						// logger.debug("<-----size of deathCertList List"+
						// deathCertList.size());
						eForm.setUploadDthList(deathCertList);
						dto.setUplaReltnProof("");
						dto.setUpldDeathCert("");
						dto.setPartyFirstName("");
						dto.setPartyTypeID("");
						eForm.setComments("");
						eForm.setActionName("");
						eForm.setDeathCertChk("Y");
						forwardJsp = "checklist";

					}
					if ("partyId".equalsIgnoreCase(actionName)) {
						logger.debug("getting Party  type Ids");
						String partyName = dto.getPartyFirstName();
						ArrayList partyIdsList = regmkrBD.getTransPartyIds(dto.getRegNumber(), partyName);
						eForm.setPartyIDList(partyIdsList);
						eForm.setActionName("");
						forwardJsp = "checklist";
					}

					if ("delDthCert".equalsIgnoreCase(actionName)) {
						String dthCertKeys[] = eForm.getDthCertKeys().split(",");
						deathCertList = eForm.getUploadDthList();
						for (int i = 0; i < dthCertKeys.length; i++) {
							if (deathCertList.containsKey(dthCertKeys[i])) {
								deathCertList.remove(dthCertKeys[i]);
							}
						}
						//logger.debug("Size of map after deletion"+deathCertList
						// .size());
						if (deathCertList.size() == 0)
							eForm.setDeathCertChk("N");
						eForm.setUploadDthList(deathCertList);
					}

				}
				if ("timebarred".equalsIgnoreCase(formName)) {
					if ("setUploadFile".equalsIgnoreCase(actionName)) {
						dto.setTimePhotoName(RegCompConstant.TIME_BARRED_DOCUMENT);
						dto.setTimePhotoPathUploaded(FILE_UPLOAD_PATH + dto.getRegNumber() + File.separator + RegCompConstant.TIME_BARRED_FOLDER + File.separator + RegCompConstant.TIME_BARRED_DOCUMENT);
						dto.setUploadFlag("Y");
						forwardJsp = "timebarredResume";
					}
					if ("NEXT_ACTION".equalsIgnoreCase(actionName)) {
						boolean flag = bd.upadteTimeBarredDetials(dto);
						if (flag) {
							regmkrBD.UpdateRegistrationCompletionStatus(dto.getRegNumber(), RegCompConstant.VERIFICATION_STATUS, cdate, UserId);
							forwardJsp = "verification";
						} else {
							session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
							forwardJsp = "failuremaker";
							logger.debug("error in submiiting time barred resume");
						}

					}

				}
				if ("verificationPending".equalsIgnoreCase(formName)) {
					if ("verificationDone".equalsIgnoreCase(actionName)) {
						boolean flg1 = regmkrBD.updtStatus(dto.getRegNumber(), UserId, cdate);
						// EtokenChange eChange = new
						// EtokenChange(dto.getRegNumber(),"13",null);
						// Thread t = new Thread(eChange);
						// t.start();
						if (flg1) {
							forwardJsp = "success1";
							eForm.setFormName("");
							eForm.setActionName("");
						} else {
							session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
							forwardJsp = "failuremaker";
						}
					}
				}
				if ("compliance".equalsIgnoreCase(formName)) {

					// start :=== code for getting caveat Details
					dto.setStoreAction(actionName);
					ArrayList caveatdet = regmkrBD.getCaveatDet(dto.getRegNumber());
					eForm.setCaveatListSize(caveatdet.size());

					eForm.setCaveatList(caveatdet);
					ArrayList bnkCaveatdet = regmkrBD.getBnkCaveatDet(dto.getRegNumber());
					eForm.setBnkCaveatList(bnkCaveatdet);
					eForm.setBnkCaveatListSize(bnkCaveatdet.size());
					ArrayList partyDetls = regmkrBD.getTransPartyDets(dto.getRegNumber());
					eForm.setPartyList(partyDetls);
					/*
					 * String compRegNum = dto.getRegNumber();
					 * 
					 * String ReginitNumber=
					 * regmkrBD.getreginitNumber(compRegNum);
					 *//** End **/
					ArrayList witnessList = regmkrBD.getWitnessList(dto.getRegNumber(), cdate, language);
					eForm.setWitnessList(witnessList);

					// *********commented by SIMRAN 10 july*******************//
					/*
					 * if ("insertcomplianceid".equalsIgnoreCase(actionName)) {
					 * logger.debug("In insertcomplianceid"); String regNum =
					 * dto.getRegNumber(); String complId =
					 * eForm.getRegDTO().getHdnCompArray(); boolean save =
					 * regmkrBD.insertComplianceID(complId, regNum, cdate,
					 * UserId); String ifHold = eForm.getHldBtnFlg();
					 * 
					 * if (!ifHold.equalsIgnoreCase("")) { if
					 * (ifHold.equalsIgnoreCase("hold")) { ; } String hldFlg =
					 * "N"; boolean updtHoldTbl = regmkrBD.updtHoldTbl(dto
					 * .getRegNumber(), UserId, cdate, hldFlg);
					 * eForm.setHldBtnFlg(""); } forwardJsp = "checklist"; }
					 */

					if ("NEXT_TO_CHECK_LIST".equalsIgnoreCase(actionName)) {
						// /////******************saving compliance
						// data**************************//
						boolean flag = regmkrBD.modifyComplianceListData(eForm, dto.getRegNumber(), UserId, cdate);
						if (flag) {
							dto.setStoreAction(actionName);
							// logger.debug(
							// "**********Complaince list modified****"+flag);
							// boolean flag1 =
							// regmkrBD.addWitnessPhotographDetails
							// (eForm.getUploadWitnessPhotograph(),
							// dto.getRegNumber(), UserId, cdate);
							// logger.debug("**********WitnessList****"+flag1);
							// if(flag1)
							// {
							if (dto.getContinueHold() != null) {
								if (dto.getContinueHold().equalsIgnoreCase("Y")) {

									boolean flag1 = regmkrBD.saveHoldResumeDetails(dto.getTxtArea(), dto.getSuppDocFileName(), dto.getSuppDocFilePath(), dto.getRegNumber(), "9");
									if (flag1) {
										boolean updtHoldTbl = regmkrBD.updtHoldTbl(dto.getRegNumber(), UserId, cdate, "N");
									}
								}
							}

							// }
							//*****************************END******************
							// ********************//
							eForm.setActionName("");
							String filePath = FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_DEATH_CERT;
							dto.setDeathCert(RegCompCheckerConstant.FILE_NAME_DEATH_CERT);
							dto.setFilePath(filePath);
							String filePathRelation = FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_RELN_PROOF;
							dto.setRelationProofName(RegCompConstant.FILE_NAME_RELTN_PROOF);
							dto.setFilePathRltn(filePathRelation);
							dto.setFilePathPOA(FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_POA_DOC);
							dto.setPoaUploadFilename(RegCompCheckerConstant.FILE_NAME_POA_DOC);
							boolean flag2 = regmkrBD.UpdateRegistrationCompletionStatus(dto.getRegNumber(), RegCompConstant.CHECKLIST_STATUS, cdate, UserId);
							dto.setContinueHold("");
							dto.setTxtArea("");
							dto.setRemarks("");
							eForm.setExeDate(bd.getExecDate(dto.getRegNumber()));
							if (flag2) {
								forwardJsp = "checklist";
							} else {
								session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
								forwardJsp = "failuremaker";
							}
						} else {
							session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
							forwardJsp = "failuremaker";
						}
					}

					if ("CAVEAT_CHECKLIST".equalsIgnoreCase(actionName)) {
						// ArrayList caveatdet =
						// regmkrBD.getCaveatDet(dto.getRegNumber());
						// eForm.setCaveatListSize(caveatdet.size());
						// eForm.setCaveatList(caveatdet);
						// ArrayList bnkCaveatdet =
						// regmkrBD.getBnkCaveatDet(dto.getRegNumber());
						// eForm.setBnkCaveatList(bnkCaveatdet);
						// eForm.setBnkCaveatListSize(bnkCaveatdet.size());
						// ArrayList partyDetls =
						// regmkrBD.getTransPartyDets(dto.getRegNumber());
						// eForm.setPartyList(partyDetls);
						// ArrayList witnessList =
						// regmkrBD.getWitnessList(dto.getRegNumber(),cdate);
						// eForm.setWitnessList(witnessList);
						eForm.setActionName("");
						String filePath = FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_DEATH_CERT;
						dto.setDeathCert(RegCompCheckerConstant.FILE_NAME_DEATH_CERT);
						dto.setFilePath(filePath);
						String filePathRelation = FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_RELN_PROOF;
						dto.setRelationProofName(RegCompConstant.FILE_NAME_RELTN_PROOF);
						dto.setFilePathRltn(filePathRelation);
						dto.setFilePathPOA(FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_POA_DOC);
						dto.setPoaUploadFilename(RegCompCheckerConstant.FILE_NAME_POA_DOC);
						forwardJsp = "checklist";
					}

					if (RegCompConstant.HOLD_ACTION.equalsIgnoreCase(actionName)) {
						// ArrayList getHoldList = regmkrBD.getHoldData();
						eForm.setChkHoldClick("C"); // c stands for hold buttons
						// clicked
						// eForm.setHoldListDisp(getHoldList);
						String deedid = regmkrBD.getDeedId(dto.getRegNumber());
						request.setAttribute("deedId", deedid);
						forwardJsp = "complianceListNew";
					}

					if (RegCompConstant.SAVE_HOLD_ACTION.equalsIgnoreCase(actionName)) {

						String regNum = dto.getRegNumber();
						// String complId = eForm.getRegDTO().getHdnCompArray();
						// boolean save = regmkrBD.insertComplianceID(complId,
						// regNum, cdate, UserId);
						boolean flag = regmkrBD.modifyComplianceListData(eForm, dto.getRegNumber(), UserId, cdate);
						//logger.debug("**********Complaince list modified****"+
						// flag);
						// boolean flag1 =
						// regmkrBD.addWitnessPhotographDetails(eForm
						// .getUploadWitnessPhotograph(), dto.getRegNumber(),
						// UserId, cdate);
						// logger.debug("**********WitnessList****"+flag1);
						//*****************************END**********************
						// ****************//
						eForm.setActionName("");
						// if (flag1) {
						String hldFlag = "Y";
						String fwdPage = eForm.formName;
						String holdId = "9";
						String holdRemarks = dto.getTxtArea();

						String str = regmkrBD.saveHoldData(regNum, hldFlag, fwdPage, cdate, UserId, holdId, holdRemarks, officeId);
						// End=== this code for saving the data in the hold
						// table
						if (str.equalsIgnoreCase("1")) {
							// forwardJsp = "holdCompPage";
							if (!holdId.equalsIgnoreCase("") && !holdId.equalsIgnoreCase(null)) {
								String holdName = regmkrBD.getHoldName(holdId);
								eForm.setHoldReason(holdName);
							}
							String status = regmkrBD.emailAlertHold(dto.getRegNumber(), officeId);
							logger.debug("<-----email alert" + status);
							// EtokenChange eChange = new
							// EtokenChange(dto.getRegNumber(),"11",null);
							// Thread t = new Thread(eChange);
							// t.start();
							request.setAttribute("HOLD_MESSAGE", regmkrBD.getEmailHoldContent(regNum, holdRemarks, officeId, language, officeId));
							forwardJsp = "commonHoldPage";
						}
						// }
					}
					if ("CONTINUE_HOLD".equalsIgnoreCase(actionName)) {
						dto.setStoreAction(actionName);
						eForm.setHldBtnFlg("hold");
						dto.setContinueHold("Y");
						dto.setRemarks(regmkrBD.getHoldRemarks(dto.getRegNumber(), "9"));
						propertyRelatedComplianceMap = regmkrBD.getPropertyRelatedComplianeList(dto.getRegNumber());
						eForm.setCompliancePropertyRelatedMap(propertyRelatedComplianceMap);

						partyRelatedComplianceMap = regmkrBD.getPartyRelatedComplianeList(dto.getRegNumber(), language);
						eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);

						witnessPhotoUploadMap = regmkrBD.getWitnessDetailsForCompliance(dto.getRegNumber());
						eForm.setUploadWitnessPhotograph(witnessPhotoUploadMap);
						String deedid = regmkrBD.getDeedId(dto.getRegNumber());
						dto.setSuppDocFileName("Unhold_Compliance.jpg");
						dto.setSuppDocFilePath(FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_SUPP_DOC);
						dto.setGuidUpload("");
						request.setAttribute("deedId", deedid);
						forwardJsp = "complianceListNew";
					}

					if ("UPLOAD_RETURN".equalsIgnoreCase(actionName)) {
						String deedid = regmkrBD.getDeedId(dto.getRegNumber());
						request.setAttribute("deedId", deedid);
						dto.setUploadSuppDoc("Unhold_compliance.jpg");
						dto.setUploadSuppDocPath(FILE_UPLOAD_PATH + dto.getRegNumber() + RegCompConstant.UPLOAD_PATH_SUPP_DOC + "/Unhold_compliance.jpg");
						forwardJsp = "complianceListNew";
					}

					ArrayList errorList = new ArrayList();
					String frwdName = request.getParameter("fwdName");
					String frwdName1 = dto.getUploadAction();
					String index = request.getParameter("index");
					String filePath = "";

					// *************************************COMPLIANCE LIST
					// UPLOADS
					// *****************************************************///
					// *******************1. WITNESS
					// PHOTOGRAPH*******************************//
					if ("witPhotograph".equalsIgnoreCase(frwdName1)) {
						if ("setUploadFile".equalsIgnoreCase(actionName)) {

							String key = dto.getSelectedWitnessTxnNummber();
							try {

								Integer indx = Integer.parseInt(dto.getIndx());
								// FormFile uploadFile[] =
								// dto.getWitnessPhotograph();
								// FormFile uploadedFile = uploadFile[indx];
								//logger.debug("######"+uploadedFile.getFileName
								// ());
								// if ("".equals(uploadedFile.getFileName())) {
								// errorList.add(
								// "Invalid file selection. Please try again.");
								// }
								// String fileExt =
								// getFileExtension(uploadedFile.getFileName());
								// AuditInspectionRule rule = new
								// AuditInspectionRule();
								// rule.validateFileType(fileExt);
								// int size = uploadedFile.getFileSize();
								// double fileSizeInBytes = size;
								// double fileSizeInKB = fileSizeInBytes /
								// 1024.0;
								// double fileSizeInMB = fileSizeInKB / 1024.0;
								// DecimalFormat decim = new
								// DecimalFormat("#.##");
								// Double fileSize =
								// Double.parseDouble(decim.format
								// (fileSizeInMB));
								// String photoSize = "(" + fileSize + "MB)";
								// if (rule.isError()) {
								// errorList.add(
								// "Invalid file type. Please select another file."
								// );
								// request.setAttribute("errorsList",errorList);
								// } else
								{
									// if (size >
									// AuditInspectionConstant.MAX_FILE_SIZE) {
									// errorList.add(
									// "File size is Greater than 10 MB. Please select another file."
									// );
									//request.setAttribute("errorsList",errorList
									// );
									// } else
									{
										// if(uploadedFile.getFileData() !=
										// null)
										// {
										// filePath =
										// uploadFile(dto.getRegNumber
										// (),uploadedFile.getFileData(),key,
										// RegCompCheckerConstant
										// .UPLOAD_PATH_PARTY_RELATED_COMPLAINCE
										// , RegCompCheckerConstant.
										// FILE_NAME_WIT_PHOTO);
										// }
										// logger.debug(
										// "**************************"
										// +filePath);
										dto.setWitnessDocName1(dto.getScanName(), indx);
										eForm.setRegDTO(dto);
										witnessPhotoUploadMap = eForm.getUploadWitnessPhotograph();
										Set mapSet = (Set) witnessPhotoUploadMap.entrySet();
										Iterator mapIterator = mapSet.iterator();
										while (mapIterator.hasNext()) {
											Map.Entry mapEntry = (Map.Entry) mapIterator.next();
											ArrayList valueList = (ArrayList) mapEntry.getValue();
											RegCompleteMakerDTO rrdto = (RegCompleteMakerDTO) valueList.get(0);
											String witnessId = (String) mapEntry.getKey();
											if (witnessId.equals(key)) {
												logger.debug("MATCH IN WITNESS");
												rrdto.setWitnessDocName(dto.getScanName());
												// rrdto.setWitnessDocContents(
												// uploadedFile.getFileData());
												// rrdto.setWitnessDocSize(
												// photoSize);
												// logger.debug(
												// "**************************"
												// +filePath);
												rrdto.setWitnessDocPath(dto.getScanPath());
											}
										}

									}
								}
							} catch (Exception e) {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}

							// logger.debug("Size of hashmap upload"+
							// witnessPhotoUploadMap.size());
							eForm.setUploadWitnessPhotograph(witnessPhotoUploadMap);
							String deedid = regmkrBD.getDeedId(dto.getRegNumber());
							request.setAttribute("deedId", deedid);
							forwardJsp = "complianceListNew";
						}

					}
					// ///******************************end
					// 1*********************************************//
					// /******************2. Prop Photo from Angle
					// 1****************************************//
					if ("angle1Upload".equalsIgnoreCase(frwdName1)) {
						if ("setUploadFile".equalsIgnoreCase(actionName)) {
							RegCompleteMakerDTO rrdto = new RegCompleteMakerDTO();
							try {
								// Integer indx =
								// Integer.parseInt(dto.getIndx());
								String propId = dto.getPropId();
								boolean boo = regmkrBD.updatePropertyUpload(propId, dto.getRegNumber(), RegCompCheckerConstant.ANGLE_1_COLUMN, dto.getScanPath());
								if (boo) {
									propertyRelatedComplianceMap = regmkrBD.getPropertyRelatedComplianeList(dto.getRegNumber());
									eForm.setCompliancePropertyRelatedMap(propertyRelatedComplianceMap);
								} else {
									errorList.add("Unable to upload file. Please try again.");
									request.setAttribute("errorsList", errorList);
								}
								// FormFile uploadFile[] = dto.getPropAngle1();
								// FormFile uploadedFile = uploadFile[indx];
								//logger.debug("######"+uploadedFile.getFileName
								// ());
								// if ("".equals(uploadedFile.getFileName())) {
								// errorList.add(
								// "Invalid file selection. Please try again.");
								// }

								// String fileExt =
								// getFileExtension(uploadedFile.getFileName());
								// AuditInspectionRule rule = new
								// AuditInspectionRule();
								// rule.validateFileType(fileExt);
								// int size = uploadedFile.getFileSize();
								// double fileSizeInBytes = size;
								// double fileSizeInKB = fileSizeInBytes /
								// 1024.0;
								// double fileSizeInMB = fileSizeInKB / 1024.0;
								// DecimalFormat decim = new
								// DecimalFormat("#.##");
								// Double fileSize =
								// Double.parseDouble(decim.format
								// (fileSizeInMB));
								// String photoSize = "(" + fileSize + "MB)";
								// if (rule.isError()) {
								// errorList.add(
								// "Invalid file type. Please select another file."
								// );
								// req

								/*
								 * { // if (size >
								 * AuditInspectionConstant.MAX_FILE_SIZE) { //
								 * errorList.add(
								 * "File size is Greater than 10 MB. Please select another file."
								 * ); //
								 * request.setAttribute("errorsList",errorList);
								 * // } else { // if(uploadedFile.getFileData()
								 * != null) // { // filePath =
								 * uploadFile(dto.getRegNumber
								 * (),uploadedFile.getFileData
								 * (),propId,RegCompCheckerConstant
								 * .UPLOAD_PATH_PROP_RELATED_COMPLAINCE,
								 * RegCompCheckerConstant.FILE_NAME_ANGLE_1); //
								 * }
								 * //logger.debug("**************************"+
								 * filePath); //if(filePath != null) {
								 * propertyRelatedComplianceMap =
								 * eForm.getCompliancePropertyRelatedMap(); Set
								 * mapSet =
								 * (Set)propertyRelatedComplianceMap.entrySet();
								 * Iterator mapIterator = mapSet.iterator();
								 * while(mapIterator.hasNext()) { Map.Entry
								 * mapEntry = (Map.Entry)mapIterator.next();
								 * String propertyId =
								 * (String)mapEntry.getKey();
								 * if(propertyId.equals(propId)) { ArrayList
								 * valueList = (ArrayList)mapEntry.getValue();
								 * RegCompleteMakerDTO gdto =
								 * (RegCompleteMakerDTO)valueList.get(0);
								 * gdto.setPropImage1FilePath
								 * (dto.getScanPath());
								 * gdto.setPropImage1DocumentName
								 * (dto.getScanName()); gdto.setIsUpload("Y"); }
								 * } }eForm.setCompliancePropertyRelatedMap(
								 * propertyRelatedComplianceMap);
								 * 
								 * } }
								 */
							} catch (Exception e) {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							String deedid = regmkrBD.getDeedId(dto.getRegNumber());
							request.setAttribute("deedId", deedid);
							forwardJsp = "complianceListNew";
						}

					}
					// ///******************************end
					// 2*********************************************//
					// ///************************3. Prop Photo from angle
					// 2******************************************//
					if ("angle2Upload".equalsIgnoreCase(frwdName1)) {
						if ("setUploadFile".equalsIgnoreCase(actionName)) {
							RegCompleteMakerDTO rrdto = new RegCompleteMakerDTO();
							try {
								// Integer indx =
								// Integer.parseInt(dto.getIndx());
								String propId = dto.getPropId();
								boolean boo = regmkrBD.updatePropertyUpload(propId, dto.getRegNumber(), RegCompCheckerConstant.ANGLE_2_COLUMN, dto.getScanPath());
								if (boo) {
									propertyRelatedComplianceMap = regmkrBD.getPropertyRelatedComplianeList(dto.getRegNumber());
									eForm.setCompliancePropertyRelatedMap(propertyRelatedComplianceMap);
								} else {
									errorList.add("Unable to upload file. Please try again.");
									request.setAttribute("errorsList", errorList);
								}
								// FormFile uploadFile[] = dto.getPropAngle2();
								// FormFile uploadedFile = uploadFile[indx];
								//logger.debug("######"+uploadedFile.getFileName
								// ());
								// if ("".equals(uploadedFile.getFileName())) {
								// errorList.add(
								// "Invalid file selection. Please try again.");
								// }

								// String fileExt =
								// getFileExtension(uploadedFile.getFileName());
								// AuditInspectionRule rule = new
								// AuditInspectionRule();
								// rule.validateFileType(fileExt);
								// int size = uploadedFile.getFileSize();
								// double fileSizeInBytes = size;
								// / double fileSizeInKB = fileSizeInBytes /
								// 1024.0;
								// double fileSizeInMB = fileSizeInKB / 1024.0;
								// DecimalFormat decim = new
								// DecimalFormat("#.##");
								// Double fileSize =
								// Double.parseDouble(decim.format
								// (fileSizeInMB));
								// String photoSize = "(" + fileSize + "MB)";
								// if (rule.isError()) {
								// errorList.add(
								// "Invalid file type. Please select another file."
								// );
								// request.setAttribute("errorsList",errorList);
								// } else
								/*
								 * // if (size >
								 * AuditInspectionConstant.MAX_FILE_SIZE) { //
								 * errorList.add(
								 * "File size is Greater than 10 MB. Please select another file."
								 * ); //
								 * request.setAttribute("errorsList",errorList);
								 * // } else { // if(uploadedFile.getFileData()
								 * != null) // { // filePath =
								 * uploadFile(dto.getRegNumber
								 * (),uploadedFile.getFileData
								 * (),propId,RegCompCheckerConstant
								 * .UPLOAD_PATH_PROP_RELATED_COMPLAINCE,
								 * RegCompCheckerConstant.FILE_NAME_ANGLE_2); //
								 * }
								 * //logger.debug("**************************"+
								 * filePath); // if(filePath != null) {
								 * propertyRelatedComplianceMap =
								 * eForm.getCompliancePropertyRelatedMap(); Set
								 * mapSet =
								 * (Set)propertyRelatedComplianceMap.entrySet();
								 * Iterator mapIterator = mapSet.iterator();
								 * while(mapIterator.hasNext()) { Map.Entry
								 * mapEntry = (Map.Entry)mapIterator.next();
								 * String propertyId =
								 * (String)mapEntry.getKey();
								 * if(propertyId.equals(propId)) { ArrayList
								 * valueList = (ArrayList)mapEntry.getValue();
								 * RegCompleteMakerDTO gdto =
								 * (RegCompleteMakerDTO)valueList.get(0);
								 * gdto.setPropImage2FilePath
								 * (dto.getScanPath());
								 * gdto.setPropImage2DocumentName
								 * (dto.getScanName()); gdto.setIsUpload("Y"); }
								 * } }eForm.setCompliancePropertyRelatedMap(
								 * propertyRelatedComplianceMap);
								 * 
								 * }
								 */
							} catch (Exception e) {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							String deedid = regmkrBD.getDeedId(dto.getRegNumber());
							request.setAttribute("deedId", deedid);
							forwardJsp = "complianceListNew";
						}

					}
					// ///******************************end
					// 1*********************************************//
					// ///*************************Prop photo from angle
					// 3*********************************************//
					if ("angle3Upload".equalsIgnoreCase(frwdName1)) {
						if ("setUploadFile".equalsIgnoreCase(actionName)) {
							RegCompleteMakerDTO rrdto = new RegCompleteMakerDTO();
							try {
								// Integer indx =
								// Integer.parseInt(dto.getIndx());
								String propId = dto.getPropId();
								boolean boo = regmkrBD.updatePropertyUpload(propId, dto.getRegNumber(), RegCompCheckerConstant.ANGLE_3_COLUMN, dto.getScanPath());
								if (boo) {
									propertyRelatedComplianceMap = regmkrBD.getPropertyRelatedComplianeList(dto.getRegNumber());
									eForm.setCompliancePropertyRelatedMap(propertyRelatedComplianceMap);
								} else {
									errorList.add("Unable to upload file. Please try again.");
									request.setAttribute("errorsList", errorList);
								}
								// FormFile uploadFile[] = dto.getPropAngle3();
								// FormFile uploadedFile = uploadFile[indx];
								//logger.debug("######"+uploadedFile.getFileName
								// ());
								// if ("".equals(uploadedFile.getFileName())) {
								// errorList.add(
								// "Invalid file selection. Please try again.");
								// }
								//			
								// String fileExt =
								// getFileExtension(uploadedFile.getFileName());
								// AuditInspectionRule rule = new
								// AuditInspectionRule();
								// rule.validateFileType(fileExt);
								// int size = uploadedFile.getFileSize();
								// double fileSizeInBytes = size;
								// double fileSizeInKB = fileSizeInBytes /
								// 1024.0;
								// double fileSizeInMB = fileSizeInKB / 1024.0;
								// DecimalFormat decim = new
								// DecimalFormat("#.##");
								// Double fileSize =
								// Double.parseDouble(decim.format
								// (fileSizeInMB));
								// String photoSize = "(" + fileSize + "MB)";
								// if (rule.isError()) {
								// errorList.add(
								// "Invalid file type. Please select another file."
								// );
								// request.setAttribute("errorsList",errorList);
								// } else
								/*
								 * // if (size >
								 * AuditInspectionConstant.MAX_FILE_SIZE) { //
								 * errorList.add(
								 * "File size is Greater than 10 MB. Please select another file."
								 * ); //
								 * request.setAttribute("errorsList",errorList);
								 * // } else { // if(uploadedFile.getFileData()
								 * != null) // { // filePath =
								 * uploadFile(dto.getRegNumber
								 * (),uploadedFile.getFileData
								 * (),propId,RegCompCheckerConstant
								 * .UPLOAD_PATH_PROP_RELATED_COMPLAINCE,
								 * RegCompCheckerConstant.FILE_NAME_ANGLE_3); //
								 * }
								 * //logger.debug("**************************"+
								 * filePath); // if(filePath != null) {
								 * propertyRelatedComplianceMap =
								 * eForm.getCompliancePropertyRelatedMap(); Set
								 * mapSet =
								 * (Set)propertyRelatedComplianceMap.entrySet();
								 * Iterator mapIterator = mapSet.iterator();
								 * while(mapIterator.hasNext()) { Map.Entry
								 * mapEntry = (Map.Entry)mapIterator.next();
								 * String propertyId =
								 * (String)mapEntry.getKey();
								 * if(propertyId.equals(propId)) { ArrayList
								 * valueList = (ArrayList)mapEntry.getValue();
								 * RegCompleteMakerDTO gdto =
								 * (RegCompleteMakerDTO)valueList.get(0);
								 * gdto.setPropImage3FilePath
								 * (dto.getScanPath());
								 * gdto.setPropImage3DocumentName
								 * (dto.getScanName()); gdto.setIsUpload("Y"); }
								 * } }eForm.setCompliancePropertyRelatedMap(
								 * propertyRelatedComplianceMap);
								 * 
								 * }
								 */// }
							} catch (Exception e) {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							String deedid = regmkrBD.getDeedId(dto.getRegNumber());
							request.setAttribute("deedId", deedid);
							forwardJsp = "complianceListNew";
						}

					}
					// ///******************************end
					// 1*********************************************//
					// ///**********************4 prop
					// Map****************************************//
					if ("propMapUpload".equalsIgnoreCase(frwdName1)) {
						if ("setUploadFile".equalsIgnoreCase(actionName)) {
							RegCompleteMakerDTO rrdto = new RegCompleteMakerDTO();
							try {
								// Integer indx =
								// Integer.parseInt(dto.getIndx());
								String propId = dto.getPropId();
								boolean boo = regmkrBD.updatePropertyUpload(propId, dto.getRegNumber(), RegCompCheckerConstant.MAP_COLUMN, dto.getScanPath());
								if (boo) {
									propertyRelatedComplianceMap = regmkrBD.getPropertyRelatedComplianeList(dto.getRegNumber());
									eForm.setCompliancePropertyRelatedMap(propertyRelatedComplianceMap);
								} else {
									errorList.add("Unable to upload file. Please try again.");
									request.setAttribute("errorsList", errorList);
								}
								// FormFile uploadFile[] = dto.getPropMap();
								// FormFile uploadedFile = uploadFile[indx];
								//logger.debug("######"+uploadedFile.getFileName
								// ());
								// if ("".equals(uploadedFile.getFileName())) {
								// errorList.add(
								// "Invalid file selection. Please try again.");
								// }

								// String fileExt =
								// getFileExtension(uploadedFile.getFileName());
								// AuditInspectionRule rule = new
								// AuditInspectionRule();
								// rule.validateFileType(fileExt);
								// / int size = uploadedFile.getFileSize();
								// double fileSizeInBytes = size;
								// double fileSizeInKB = fileSizeInBytes /
								// 1024.0;
								// double fileSizeInMB = fileSizeInKB / 1024.0;
								// DecimalFormat decim = new
								// DecimalFormat("#.##");
								// Double fileSize =
								// Double.parseDouble(decim.format
								// (fileSizeInMB));
								// String photoSize = "(" + fileSize + "MB)";
								// if (rule.isError()) {
								// errorList.add(
								// "Invalid file type. Please select another file."
								// );
								// request.setAttribute("errorsList",errorList);
								// // } else
								/*
								 * // if (size >
								 * AuditInspectionConstant.MAX_FILE_SIZE) { //
								 * errorList.add(
								 * "File size is Greater than 10 MB. Please select another file."
								 * ); //
								 * request.setAttribute("errorsList",errorList);
								 * // } else { // if(uploadedFile.getFileData()
								 * != null) // { // filePath =
								 * uploadFile(dto.getRegNumber
								 * (),uploadedFile.getFileData
								 * (),propId,RegCompCheckerConstant
								 * .UPLOAD_PATH_PROP_RELATED_COMPLAINCE,
								 * RegCompCheckerConstant.FILE_NAME_PROP_MAP);
								 * // }
								 * //logger.debug("**************************"
								 * +filePath); // if(filePath != null) {
								 * propertyRelatedComplianceMap =
								 * eForm.getCompliancePropertyRelatedMap(); Set
								 * mapSet =
								 * (Set)propertyRelatedComplianceMap.entrySet();
								 * Iterator mapIterator = mapSet.iterator();
								 * while(mapIterator.hasNext()) { Map.Entry
								 * mapEntry = (Map.Entry)mapIterator.next();
								 * String propertyId =
								 * (String)mapEntry.getKey();
								 * if(propertyId.equals(propId)) { ArrayList
								 * valueList = (ArrayList)mapEntry.getValue();
								 * RegCompleteMakerDTO gdto =
								 * (RegCompleteMakerDTO)valueList.get(0);
								 * gdto.setPropMapFilePath(dto.getScanPath());
								 * gdto
								 * .setPropMapDocumentName(dto.getScanName());
								 * gdto.setIsUpload("Y"); } } }
								 * eForm.setCompliancePropertyRelatedMap
								 * (propertyRelatedComplianceMap);
								 * 
								 * }
								 */// }
							} catch (Exception e) {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							String deedid = regmkrBD.getDeedId(dto.getRegNumber());
							request.setAttribute("deedId", deedid);
							forwardJsp = "complianceListNew";
						}

					}
					// **************************end
					// 4***************************
					// *****************************//
					// ///**********************5 prop
					// RIN****************************************//
					if ("propRinUpload".equalsIgnoreCase(frwdName1)) {
						if ("setUploadFile".equalsIgnoreCase(actionName)) {
							RegCompleteMakerDTO rrdto = new RegCompleteMakerDTO();
							try {
								// Integer indx =
								// Integer.parseInt(dto.getIndx());
								String propId = dto.getPropId();
								boolean boo = regmkrBD.updatePropertyUpload(propId, dto.getRegNumber(), RegCompCheckerConstant.RIN_COLUMN, dto.getScanPath());
								if (boo) {
									propertyRelatedComplianceMap = regmkrBD.getPropertyRelatedComplianeList(dto.getRegNumber());
									eForm.setCompliancePropertyRelatedMap(propertyRelatedComplianceMap);
								} else {
									errorList.add("Unable to upload file. Please try again.");
									request.setAttribute("errorsList", errorList);
								}
								// FormFile uploadFile[] = dto.getPropRin();
								// FormFile uploadedFile = uploadFile[indx];
								//logger.debug("######"+uploadedFile.getFileName
								// ());
								// if ("".equals(uploadedFile.getFileName())) {
								// errorList.add(
								// "Invalid file selection. Please try again.");
								// }

								// String fileExt =
								// getFileExtension(uploadedFile.getFileName());
								// AuditInspectionRule rule = new
								// AuditInspectionRule();
								// rule.validateFileType(fileExt);
								// int size = uploadedFile.getFileSize();
								// / double fileSizeInBytes = size;
								// double fileSizeInKB = fileSizeInBytes /
								// 1024.0;
								// double fileSizeInMB = fileSizeInKB / 1024.0;
								// DecimalFormat decim = new
								// DecimalFormat("#.##");
								// Double fileSize =
								// Double.parseDouble(decim.format
								// (fileSizeInMB));
								// String photoSize = "(" + fileSize + "MB)";
								// if (rule.isError()) {
								// / errorList.add(
								// "Invalid file type. Please select another file."
								// );
								// request.setAttribute("errorsList",errorList);
								// } else
								/*
								 * // if (size >
								 * AuditInspectionConstant.MAX_FILE_SIZE) { //
								 * errorList.add(
								 * "File size is Greater than 10 MB. Please select another file."
								 * ); //
								 * request.setAttribute("errorsList",errorList);
								 * // } else { // if(uploadedFile.getFileData()
								 * != null) // { // filePath =
								 * uploadFile(dto.getRegNumber
								 * (),uploadedFile.getFileData
								 * (),propId,RegCompCheckerConstant
								 * .UPLOAD_PATH_PROP_RELATED_COMPLAINCE,
								 * RegCompCheckerConstant.FILE_NAME_RIN); // }
								 * //logger.debug("**************************"+
								 * filePath); // if(filePath != null) {
								 * propertyRelatedComplianceMap =
								 * eForm.getCompliancePropertyRelatedMap(); Set
								 * mapSet =
								 * (Set)propertyRelatedComplianceMap.entrySet();
								 * Iterator mapIterator = mapSet.iterator();
								 * while(mapIterator.hasNext()) { Map.Entry
								 * mapEntry = (Map.Entry)mapIterator.next();
								 * String propertyId =
								 * (String)mapEntry.getKey();
								 * if(propertyId.equals(propId)) { ArrayList
								 * valueList = (ArrayList)mapEntry.getValue();
								 * RegCompleteMakerDTO gdto =
								 * (RegCompleteMakerDTO)valueList.get(0);
								 * gdto.setPropRinFilePath(dto.getScanPath());
								 * gdto
								 * .setPropRinDocumentName(dto.getScanName());
								 * gdto.setIsUpload("Y"); } } }
								 * eForm.setCompliancePropertyRelatedMap
								 * (propertyRelatedComplianceMap);
								 * 
								 * }
								 */// }
							} catch (Exception e) {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							String deedid = regmkrBD.getDeedId(dto.getRegNumber());
							request.setAttribute("deedId", deedid);
							forwardJsp = "complianceListNew";
						}

					}
					// **************************end
					// 5***************************
					// *****************************//
					// ///**********************6 prop
					// Khasra****************************************//
					if ("propKhasraUpload".equalsIgnoreCase(frwdName1)) {
						if ("setUploadFile".equalsIgnoreCase(actionName)) {
							RegCompleteMakerDTO rrdto = new RegCompleteMakerDTO();
							try {
								// Integer indx =
								// Integer.parseInt(dto.getIndx());
								String propId = dto.getPropId();
								boolean boo = regmkrBD.updatePropertyUpload(propId, dto.getRegNumber(), RegCompCheckerConstant.KHASRA_COLUMN, dto.getScanPath());
								if (boo) {
									propertyRelatedComplianceMap = regmkrBD.getPropertyRelatedComplianeList(dto.getRegNumber());
									eForm.setCompliancePropertyRelatedMap(propertyRelatedComplianceMap);
								} else {
									errorList.add("Unable to upload file. Please try again.");
									request.setAttribute("errorsList", errorList);
								}
								// FormFile uploadFile[] = dto.getPropKhasra();
								// FormFile uploadedFile = uploadFile[indx];
								//logger.debug("######"+uploadedFile.getFileName
								// ());
								// if ("".equals(uploadedFile.getFileName())) {
								// errorList.add(
								// "Invalid file selection. Please try again.");
								// }

								// String fileExt =
								// getFileExtension(uploadedFile.getFileName());
								// AuditInspectionRule rule = new
								// AuditInspectionRule();
								// rule.validateFileType(fileExt);
								// int size = uploadedFile.getFileSize();
								// double fileSizeInBytes = size;
								// double fileSizeInKB = fileSizeInBytes /
								// 1024.0;
								// double fileSizeInMB = fileSizeInKB / 1024.0;
								// DecimalFormat decim = new
								// DecimalFormat("#.##");
								// Double fileSize =
								// Double.parseDouble(decim.format
								// (fileSizeInMB));
								// String photoSize = "(" + fileSize + "MB)";
								// if (rule.isError()) {
								// errorList.add(
								// "Invalid file type. Please select another file."
								// );
								// request.setAttribute("errorsList",errorList);
								// } else
								/*
								 * // if (size >
								 * AuditInspectionConstant.MAX_FILE_SIZE) { //
								 * errorList.add(
								 * "File size is Greater than 10 MB. Please select another file."
								 * ); //
								 * request.setAttribute("errorsList",errorList);
								 * // } else { // if(uploadedFile.getFileData()
								 * != null) // { // filePath =
								 * uploadFile(dto.getRegNumber
								 * (),uploadedFile.getFileData
								 * (),propId,RegCompCheckerConstant
								 * .UPLOAD_PATH_PROP_RELATED_COMPLAINCE,
								 * RegCompCheckerConstant.FILE_NAME_KHASRA); //
								 * }
								 * //logger.debug("**************************"+
								 * filePath); // if(filePath != null) {
								 * propertyRelatedComplianceMap =
								 * eForm.getCompliancePropertyRelatedMap(); Set
								 * mapSet =
								 * (Set)propertyRelatedComplianceMap.entrySet();
								 * Iterator mapIterator = mapSet.iterator();
								 * while(mapIterator.hasNext()) { Map.Entry
								 * mapEntry = (Map.Entry)mapIterator.next();
								 * String propertyId =
								 * (String)mapEntry.getKey();
								 * if(propertyId.equals(propId)) { ArrayList
								 * valueList = (ArrayList)mapEntry.getValue();
								 * RegCompleteMakerDTO gdto =
								 * (RegCompleteMakerDTO)valueList.get(0);
								 * gdto.setPropKhasraFilePath
								 * (dto.getScanPath());
								 * gdto.setPropKhasraDocumentName
								 * (dto.getScanName()); gdto.setIsUpload("Y"); }
								 * } }eForm.setCompliancePropertyRelatedMap(
								 * propertyRelatedComplianceMap);
								 * 
								 * }
								 */// }
							} catch (Exception e) {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							String deedid = regmkrBD.getDeedId(dto.getRegNumber());
							request.setAttribute("deedId", deedid);
							forwardJsp = "complianceListNew";
						}

					}
					// **************************end
					// 6***************************
					// *****************************//
					// *******************************PARTY RELATED
					// UPLOADS******************************//
					// //////////////////1. Collector
					// cert************************************************//
					if ("partyCollectorCert".equalsIgnoreCase(frwdName1)) {
						if ("setUploadFile".equalsIgnoreCase(actionName)) {
							RegCompleteMakerDTO rrdto = new RegCompleteMakerDTO();
							try {
								// Integer indx =
								// Integer.parseInt(dto.getIndx());
								String partyId = dto.getPartyId();
								boolean boo = regmkrBD.updatePartyUpload(partyId, dto.getRegNumber(), RegCompCheckerConstant.COLLECTER_CERTIFICATE_COLUMN, dto.getScanPath());
								if (boo) {
									partyRelatedComplianceMap = regmkrBD.getPartyRelatedComplianeList(dto.getRegNumber(), language);
									eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
								} else {
									errorList.add("Unable to upload file. Please try again.");
									request.setAttribute("errorsList", errorList);
								}
								// FormFile uploadFile[] =
								// dto.getPartyCollectorCertDoc();
								// FormFile uploadedFile = uploadFile[indx];
								//logger.debug("######"+uploadedFile.getFileName
								// ());
								// if ("".equals(uploadedFile.getFileName())) {
								// errorList.add(
								// "Invalid file selection. Please try again.");
								// }

								// String fileExt =
								// getFileExtension(uploadedFile.getFileName());
								// AuditInspectionRule rule = new
								// AuditInspectionRule();
								// rule.validateFileType(fileExt);
								// int size = uploadedFile.getFileSize();
								// double fileSizeInBytes = size;
								// double fileSizeInKB = fileSizeInBytes /
								// 1024.0;
								// double fileSizeInMB = fileSizeInKB / 1024.0;
								// DecimalFormat decim = new
								// DecimalFormat("#.##");
								// Double fileSize =
								// Double.parseDouble(decim.format
								// (fileSizeInMB));
								// String photoSize = "(" + fileSize + "MB)";
								// if (rule.isError()) {
								// errorList.add(
								// "Invalid file type. Please select another file."
								// );
								// request.setAttribute("errorsList",errorList);
								// } else
								/*
								 * // if (size >
								 * AuditInspectionConstant.MAX_FILE_SIZE) { //
								 * errorList.add(
								 * "File size is Greater than 10 MB. Please select another file."
								 * ); //
								 * request.setAttribute("errorsList",errorList);
								 * // } else { // if(uploadedFile.getFileData()
								 * != null) // { // filePath =
								 * uploadFile(dto.getRegNumber
								 * (),uploadedFile.getFileData
								 * (),partyId,RegCompCheckerConstant
								 * .UPLOAD_PATH_PARTY_RELATED_COMPLAINCE,
								 * RegCompCheckerConstant
								 * .FILE_NAME_COLLECTOR_CERT); // }
								 * //logger.debug
								 * ("**************************"+filePath); //
								 * if(filePath != null) {
								 * partyRelatedComplianceMap =
								 * eForm.getCompliancePartyRelatedMap(); Set
								 * mapSet =
								 * (Set)partyRelatedComplianceMap.entrySet();
								 * Iterator mapIterator = mapSet.iterator();
								 * while(mapIterator.hasNext()) { Map.Entry
								 * mapEntry = (Map.Entry)mapIterator.next();
								 * String partyID = (String)mapEntry.getKey();
								 * if(partyId.equals(partyID)) { ArrayList
								 * valueList = (ArrayList)mapEntry.getValue();
								 * RegCompleteMakerDTO gdto =
								 * (RegCompleteMakerDTO)valueList.get(0);
								 * gdto.setCollectorCertDocPath
								 * (dto.getScanPath());
								 * gdto.setCollectorCertDocName
								 * (dto.getScanName()); gdto.setIsUpload("Y"); }
								 * } }eForm.setCompliancePartyRelatedMap(
								 * partyRelatedComplianceMap);
								 * 
								 * }
								 */// }
							} catch (Exception e) {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							String deedid = regmkrBD.getDeedId(dto.getRegNumber());
							request.setAttribute("deedId", deedid);
							forwardJsp = "complianceListNew";
						}

					}
					// ***********************************end party
					// 1***********************************//
					// //********************************2. photo
					// proof***********************************//
					if ("partyPhotoProof".equalsIgnoreCase(frwdName1)) {
						if ("setUploadFile".equalsIgnoreCase(actionName)) {
							RegCompleteMakerDTO rrdto = new RegCompleteMakerDTO();
							try {
								// Integer indx =
								// Integer.parseInt(dto.getIndx());
								String partyId = dto.getPartyId();
								boolean boo = regmkrBD.updatePartyUpload(partyId, dto.getRegNumber(), RegCompCheckerConstant.PHOTO_PROOF_COLUMN, dto.getScanPath());
								if (boo) {
									partyRelatedComplianceMap = regmkrBD.getPartyRelatedComplianeList(dto.getRegNumber(), language);
									eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
								} else {
									errorList.add("Unable to upload file. Please try again.");
									request.setAttribute("errorsList", errorList);
								}
								// FormFile uploadFile[] =
								// dto.getPartyPhotoProofDoc();
								// FormFile uploadedFile = uploadFile[indx];
								//logger.debug("######"+uploadedFile.getFileName
								// ());
								// if ("".equals(uploadedFile.getFileName())) {
								// errorList.add(
								// "Invalid file selection. Please try again.");
								// }

								// String fileExt =
								// getFileExtension(uploadedFile.getFileName());
								// AuditInspectionRule rule = new
								// AuditInspectionRule();
								// rule.validateFileType(fileExt);
								// int size = uploadedFile.getFileSize();
								// double fileSizeInBytes = size;
								// double fileSizeInKB = fileSizeInBytes /
								// 1024.0;
								// double fileSizeInMB = fileSizeInKB / 1024.0;
								// DecimalFormat decim = new
								// DecimalFormat("#.##");
								// Double fileSize =
								// Double.parseDouble(decim.format
								// (fileSizeInMB));
								// String photoSize = "(" + fileSize + "MB)";
								// if (rule.isError()) {
								// errorList.add(
								// "Invalid file type. Please select another file."
								// );
								// request.setAttribute("errorsList",errorList);
								// } else
								/*
								 * // if (size >
								 * AuditInspectionConstant.MAX_FILE_SIZE) { //
								 * errorList.add(
								 * "File size is Greater than 10 MB. Please select another file."
								 * ); //
								 * request.setAttribute("errorsList",errorList);
								 * // } else { // if(uploadedFile.getFileData()
								 * != null) // { // filePath =
								 * uploadFile(dto.getRegNumber
								 * (),uploadedFile.getFileData
								 * (),partyId,RegCompCheckerConstant
								 * .UPLOAD_PATH_PARTY_RELATED_COMPLAINCE,
								 * RegCompCheckerConstant
								 * .FILE_NAME_PHOTO_PROOF); // }
								 * //logger.debug("**************************"
								 * +filePath); // if(filePath != null) {
								 * partyRelatedComplianceMap =
								 * eForm.getCompliancePartyRelatedMap(); Set
								 * mapSet =
								 * (Set)partyRelatedComplianceMap.entrySet();
								 * Iterator mapIterator = mapSet.iterator();
								 * while(mapIterator.hasNext()) { Map.Entry
								 * mapEntry = (Map.Entry)mapIterator.next();
								 * String partyID = (String)mapEntry.getKey();
								 * if(partyId.equals(partyID)) { ArrayList
								 * valueList = (ArrayList)mapEntry.getValue();
								 * RegCompleteMakerDTO gdto =
								 * (RegCompleteMakerDTO)valueList.get(0);
								 * gdto.setPhotoProofDocPath(dto.getScanPath());
								 * gdto.setPhotoProofDocName(dto.getScanName());
								 * gdto.setIsUpload("Y"); } } }
								 * eForm.setCompliancePartyRelatedMap
								 * (partyRelatedComplianceMap);
								 * 
								 * }
								 */// }
							} catch (Exception e) {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							String deedid = regmkrBD.getDeedId(dto.getRegNumber());
							request.setAttribute("deedId", deedid);
							forwardJsp = "complianceListNew";
						}

					}
					// *****************************END
					// 2************************
					// ******************************************//
					// *******************3. Notarized Affidavit by the
					// Executant****************************//
					if ("partyNotAffdExec".equalsIgnoreCase(frwdName1)) {
						if ("setUploadFile".equalsIgnoreCase(actionName)) {
							RegCompleteMakerDTO rrdto = new RegCompleteMakerDTO();
							try {
								// Integer indx =
								// Integer.parseInt(dto.getIndx());
								String partyId = dto.getPartyId();
								boolean boo = regmkrBD.updatePartyUpload(partyId, dto.getRegNumber(), RegCompCheckerConstant.NOT_AFFD_EXEC_COLUMN, dto.getScanPath());
								if (boo) {
									partyRelatedComplianceMap = regmkrBD.getPartyRelatedComplianeList(dto.getRegNumber(), language);
									eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
								} else {
									errorList.add("Unable to upload file. Please try again.");
									request.setAttribute("errorsList", errorList);
								}
								// FormFile uploadFile[] =
								// dto.getPartyNotAffdExecDoc();
								// FormFile uploadedFile = uploadFile[indx];
								//logger.debug("######"+uploadedFile.getFileName
								// ());
								// if ("".equals(uploadedFile.getFileName())) {
								// errorList.add(
								// "Invalid file selection. Please try again.");
								// }

								// String fileExt =
								// getFileExtension(uploadedFile.getFileName());
								// AuditInspectionRule rule = new
								// AuditInspectionRule();
								// rule.validateFileType(fileExt);
								// int size = uploadedFile.getFileSize();
								// double fileSizeInBytes = size;
								// double fileSizeInKB = fileSizeInBytes /
								// 1024.0;
								// double fileSizeInMB = fileSizeInKB / 1024.0;
								// DecimalFormat decim = new
								// DecimalFormat("#.##");
								// Double fileSize =
								// Double.parseDouble(decim.format
								// (fileSizeInMB));
								// String photoSize = "(" + fileSize + "MB)";
								// if (rule.isError()) {
								// errorList.add(
								// "Invalid file type. Please select another file."
								// );
								// request.setAttribute("errorsList",errorList);
								// } else
								/*
								 * // if (size >
								 * AuditInspectionConstant.MAX_FILE_SIZE) { //
								 * errorList.add(
								 * "File size is Greater than 10 MB. Please select another file."
								 * ); //
								 * request.setAttribute("errorsList",errorList);
								 * // } else { // if(uploadedFile.getFileData()
								 * != null) // { // filePath =
								 * uploadFile(dto.getRegNumber
								 * (),uploadedFile.getFileData
								 * (),partyId,RegCompCheckerConstant
								 * .UPLOAD_PATH_PARTY_RELATED_COMPLAINCE,
								 * RegCompCheckerConstant
								 * .FILE_NAME_NOTARIZED_AFFIDAVIT_EXECUTANT); //
								 * }
								 * //logger.debug("**************************"+
								 * filePath); // if(filePath != null) {
								 * partyRelatedComplianceMap =
								 * eForm.getCompliancePartyRelatedMap(); Set
								 * mapSet =
								 * (Set)partyRelatedComplianceMap.entrySet();
								 * Iterator mapIterator = mapSet.iterator();
								 * while(mapIterator.hasNext()) { Map.Entry
								 * mapEntry = (Map.Entry)mapIterator.next();
								 * String partyID = (String)mapEntry.getKey();
								 * if(partyId.equals(partyID)) { ArrayList
								 * valueList = (ArrayList)mapEntry.getValue();
								 * RegCompleteMakerDTO gdto =
								 * (RegCompleteMakerDTO)valueList.get(0);
								 * gdto.setNotAffdExecDocPath
								 * (dto.getScanPath());
								 * gdto.setNotAffdExecDocName
								 * (dto.getScanName()); gdto.setIsUpload("Y"); }
								 * } }eForm.setCompliancePartyRelatedMap(
								 * partyRelatedComplianceMap);
								 * 
								 * }
								 */// }
							} catch (Exception e) {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							String deedid = regmkrBD.getDeedId(dto.getRegNumber());
							request.setAttribute("deedId", deedid);
							forwardJsp = "complianceListNew";
						}

					}
					// *****************************END
					// 3************************
					// *******************************//
					// ***********************4. Executant's
					// Photograph**********
					// **************************************//
					if ("partyExecPhoto".equalsIgnoreCase(frwdName1)) {
						if ("setUploadFile".equalsIgnoreCase(actionName)) {
							RegCompleteMakerDTO rrdto = new RegCompleteMakerDTO();
							try {
								// Integer indx =
								// Integer.parseInt(dto.getIndx());
								String partyId = dto.getPartyId();
								boolean boo = regmkrBD.updatePartyUpload(partyId, dto.getRegNumber(), RegCompCheckerConstant.EXEC_PHOTO_COLUMN, dto.getScanPath());
								if (boo) {
									partyRelatedComplianceMap = regmkrBD.getPartyRelatedComplianeList(dto.getRegNumber(), language);
									eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
								} else {
									errorList.add("Unable to upload file. Please try again.");
									request.setAttribute("errorsList", errorList);
								}
								// FormFile uploadFile[] =
								// dto.getPartyExecPhoto();
								// FormFile uploadedFile = uploadFile[indx];
								//logger.debug("######"+uploadedFile.getFileName
								// ());
								// if ("".equals(uploadedFile.getFileName())) {
								// errorList.add(
								// "Invalid file selection. Please try again.");
								// }

								// String fileExt =
								// getFileExtension(uploadedFile.getFileName());
								// AuditInspectionRule rule = new
								// AuditInspectionRule();
								// / rule.validateFileType(fileExt);
								// int size = uploadedFile.getFileSize();
								// double fileSizeInBytes = size;
								// double fileSizeInKB = fileSizeInBytes /
								// 1024.0;
								// double fileSizeInMB = fileSizeInKB / 1024.0;
								// DecimalFormat decim = new
								// DecimalFormat("#.##");
								// Double fileSize =
								// Double.parseDouble(decim.format
								// (fileSizeInMB));
								// String photoSize = "(" + fileSize + "MB)";
								// if (rule.isError()) {
								// errorList.add(
								// "Invalid file type. Please select another file."
								// );
								// request.setAttribute("errorsList",errorList);
								// } else
								/*
								 * // if (size >
								 * AuditInspectionConstant.MAX_FILE_SIZE) { //
								 * errorList.add(
								 * "File size is Greater than 10 MB. Please select another file."
								 * ); //
								 * request.setAttribute("errorsList",errorList);
								 * // } else { // if(uploadedFile.getFileData()
								 * != null) // { // filePath =
								 * uploadFile(dto.getRegNumber
								 * (),uploadedFile.getFileData
								 * (),partyId,RegCompCheckerConstant
								 * .UPLOAD_PATH_PARTY_RELATED_COMPLAINCE,
								 * RegCompCheckerConstant
								 * .FILE_NAME_EXECUTANT_PHOTO); // }
								 * //logger.debug
								 * ("**************************"+filePath); //
								 * if(filePath != null) {
								 * partyRelatedComplianceMap =
								 * eForm.getCompliancePartyRelatedMap(); Set
								 * mapSet =
								 * (Set)partyRelatedComplianceMap.entrySet();
								 * Iterator mapIterator = mapSet.iterator();
								 * while(mapIterator.hasNext()) { Map.Entry
								 * mapEntry = (Map.Entry)mapIterator.next();
								 * String partyID = (String)mapEntry.getKey();
								 * if(partyId.equals(partyID)) { ArrayList
								 * valueList = (ArrayList)mapEntry.getValue();
								 * RegCompleteMakerDTO gdto =
								 * (RegCompleteMakerDTO)valueList.get(0);
								 * gdto.setExecPhotoPath(dto.getScanPath());
								 * gdto.setExecPhotoName(dto.getScanName());
								 * gdto.setIsUpload("Y"); gdto.setIsUpload("Y");
								 * } } }eForm.setCompliancePartyRelatedMap(
								 * partyRelatedComplianceMap);
								 * 
								 * }
								 */// }
							} catch (Exception e) {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							String deedid = regmkrBD.getDeedId(dto.getRegNumber());
							request.setAttribute("deedId", deedid);
							forwardJsp = "complianceListNew";
						}

					}
					// ************************************end
					// 4***************************************************//
					// **********************************5. Notarized Affidavit
					// of Attorney******************//
					if ("partyNotAffdAttr".equalsIgnoreCase(frwdName1)) {
						if ("setUploadFile".equalsIgnoreCase(actionName)) {
							RegCompleteMakerDTO rrdto = new RegCompleteMakerDTO();
							try {
								// Integer indx =
								// Integer.parseInt(dto.getIndx());
								String partyId = dto.getPartyId();
								boolean boo = regmkrBD.updatePartyUpload(partyId, dto.getRegNumber(), RegCompCheckerConstant.NOT_AFFD_ATTR_PATH, dto.getScanPath());
								if (boo) {
									partyRelatedComplianceMap = regmkrBD.getPartyRelatedComplianeList(dto.getRegNumber(), language);
									eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
								} else {
									errorList.add("Unable to upload file. Please try again.");
									request.setAttribute("errorsList", errorList);
								}
								// FormFile uploadFile[] =
								// dto.getPartyNotAffdAttr();
								// FormFile uploadedFile = uploadFile[indx];
								//logger.debug("######"+uploadedFile.getFileName
								// ());
								// if ("".equals(uploadedFile.getFileName())) {
								// errorList.add(
								// "Invalid file selection. Please try again.");
								// }

								// String fileExt =
								// getFileExtension(uploadedFile.getFileName());
								// AuditInspectionRule rule = new
								// AuditInspectionRule();
								// rule.validateFileType(fileExt);
								// int size = uploadedFile.getFileSize();
								// double fileSizeInBytes = size;
								// double fileSizeInKB = fileSizeInBytes /
								// 1024.0;
								// double fileSizeInMB = fileSizeInKB / 1024.0;
								// DecimalFormat decim = new
								// DecimalFormat("#.##");
								// Double fileSize =
								// Double.parseDouble(decim.format
								// (fileSizeInMB));
								// String photoSize = "(" + fileSize + "MB)";
								// if (rule.isError()) {
								// errorList.add(
								// "Invalid file type. Please select another file."
								// );
								// request.setAttribute("errorsList",errorList);
								// } else
								/*
								 * // if (size >
								 * AuditInspectionConstant.MAX_FILE_SIZE) { //
								 * errorList.add(
								 * "File size is Greater than 10 MB. Please select another file."
								 * ); //
								 * request.setAttribute("errorsList",errorList);
								 * // } else { /// if(uploadedFile.getFileData()
								 * != null) // { // filePath =
								 * uploadFile(dto.getRegNumber
								 * (),uploadedFile.getFileData
								 * (),partyId,RegCompCheckerConstant
								 * .UPLOAD_PATH_PARTY_RELATED_COMPLAINCE,
								 * RegCompCheckerConstant
								 * .FILE_NAME_NOTARIZED_AFFIDAVIT_ATTORNEY); //
								 * }
								 * //logger.debug("**************************"+
								 * filePath); // if(filePath != null) {
								 * partyRelatedComplianceMap =
								 * eForm.getCompliancePartyRelatedMap(); Set
								 * mapSet =
								 * (Set)partyRelatedComplianceMap.entrySet();
								 * Iterator mapIterator = mapSet.iterator();
								 * while(mapIterator.hasNext()) { Map.Entry
								 * mapEntry = (Map.Entry)mapIterator.next();
								 * String partyID = (String)mapEntry.getKey();
								 * if(partyId.equals(partyID)) { ArrayList
								 * valueList = (ArrayList)mapEntry.getValue();
								 * RegCompleteMakerDTO gdto =
								 * (RegCompleteMakerDTO)valueList.get(0);
								 * gdto.setNotAffdAttrPath(dto.getScanPath());
								 * gdto.setNotAffdAttrName(dto.getScanName());
								 * gdto.setIsUpload("Y"); } } }
								 * eForm.setCompliancePartyRelatedMap
								 * (partyRelatedComplianceMap);
								 * 
								 * }
								 */// }
							} catch (Exception e) {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							String deedid = regmkrBD.getDeedId(dto.getRegNumber());
							request.setAttribute("deedId", deedid);
							forwardJsp = "complianceListNew";
						}

					}

					// ***************************************end
					// 5****************************************************//
					// *************************************6. Attorney
					// Photograph************************************//
					if ("partyAttrPhoto".equalsIgnoreCase(frwdName1)) {
						if ("setUploadFile".equalsIgnoreCase(actionName)) {
							RegCompleteMakerDTO rrdto = new RegCompleteMakerDTO();
							try {
								// Integer indx =
								// Integer.parseInt(dto.getIndx());
								String partyId = dto.getPartyId();
								boolean boo = regmkrBD.updatePartyUpload(partyId, dto.getRegNumber(), RegCompCheckerConstant.ATTR_PHOTO_PATH, dto.getScanPath());
								if (boo) {
									partyRelatedComplianceMap = regmkrBD.getPartyRelatedComplianeList(dto.getRegNumber(), language);
									eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
								} else {
									errorList.add("Unable to upload file. Please try again.");
									request.setAttribute("errorsList", errorList);
								}
								// FormFile uploadFile[] =
								// dto.getPartyAttrPhoto();
								// FormFile uploadedFile = uploadFile[indx];
								//logger.debug("######"+uploadedFile.getFileName
								// ());
								// if ("".equals(uploadedFile.getFileName())) {
								// errorList.add(
								// "Invalid file selection. Please try again.");
								// }

								// String fileExt =
								// getFileExtension(uploadedFile.getFileName());
								// AuditInspectionRule rule = new
								// AuditInspectionRule();
								// rule.validateFileType(fileExt);
								// int size = uploadedFile.getFileSize();
								// double fileSizeInBytes = size;
								// double fileSizeInKB = fileSizeInBytes /
								// 1024.0;
								// double fileSizeInMB = fileSizeInKB / 1024.0;
								// DecimalFormat decim = new
								// DecimalFormat("#.##");
								// Double fileSize =
								// Double.parseDouble(decim.format
								// (fileSizeInMB));
								// String photoSize = "(" + fileSize + "MB)";
								// if (rule.isError()) {
								// // errorList.add(
								// "Invalid file type. Please select another file."
								// );
								// request.setAttribute("errorsList",errorList);
								// } else
								/*
								 * // if (size >
								 * AuditInspectionConstant.MAX_FILE_SIZE) { //
								 * errorList.add(
								 * "File size is Greater than 10 MB. Please select another file."
								 * ); //
								 * request.setAttribute("errorsList",errorList);
								 * // } else { // if(uploadedFile.getFileData()
								 * != null) // { // filePath =
								 * uploadFile(dto.getRegNumber
								 * (),uploadedFile.getFileData
								 * (),partyId,RegCompCheckerConstant
								 * .UPLOAD_PATH_PARTY_RELATED_COMPLAINCE,
								 * RegCompCheckerConstant
								 * .FILE_NAME_ATTORNEY_PHOTO); // }
								 * //logger.debug
								 * ("**************************"+filePath); //
								 * if(filePath != null) {
								 * partyRelatedComplianceMap =
								 * eForm.getCompliancePartyRelatedMap(); Set
								 * mapSet =
								 * (Set)partyRelatedComplianceMap.entrySet();
								 * Iterator mapIterator = mapSet.iterator();
								 * while(mapIterator.hasNext()) { Map.Entry
								 * mapEntry = (Map.Entry)mapIterator.next();
								 * String partyID = (String)mapEntry.getKey();
								 * if(partyId.equals(partyID)) { ArrayList
								 * valueList = (ArrayList)mapEntry.getValue();
								 * RegCompleteMakerDTO gdto =
								 * (RegCompleteMakerDTO)valueList.get(0);
								 * gdto.setAttrPhotoPath(dto.getScanPath());
								 * gdto.setAttrPhotoName(dto.getScanName());
								 * gdto.setIsUpload("Y"); } } }
								 * eForm.setCompliancePartyRelatedMap
								 * (partyRelatedComplianceMap);
								 * 
								 * }
								 */// }
							} catch (Exception e) {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							String deedid = regmkrBD.getDeedId(dto.getRegNumber());
							request.setAttribute("deedId", deedid);
							forwardJsp = "complianceListNew";
						}

					}

					// ***********************************************end
					// 6*****************************************//
					// ******************************7.Claimants's Photograph
					// *********************************************//
					if ("partyClaimntPhoto".equalsIgnoreCase(frwdName1)) {
						if ("setUploadFile".equalsIgnoreCase(actionName)) {
							RegCompleteMakerDTO rrdto = new RegCompleteMakerDTO();
							try {
								// Integer indx =
								// Integer.parseInt(dto.getIndx());
								String partyId = dto.getPartyId();
								boolean boo = regmkrBD.updatePartyUpload(partyId, dto.getRegNumber(), RegCompCheckerConstant.CLAIMAINT_PHOTO_COLUMN, dto.getScanPath());
								if (boo) {
									partyRelatedComplianceMap = regmkrBD.getPartyRelatedComplianeList(dto.getRegNumber(), language);
									eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
								} else {
									errorList.add("Unable to upload file. Please try again.");
									request.setAttribute("errorsList", errorList);
								}
								// FormFile uploadFile[] =
								// dto.getPartyClaimntPhoto();
								// FormFile uploadedFile = uploadFile[indx];
								//logger.debug("######"+uploadedFile.getFileName
								// ());
								// if ("".equals(uploadedFile.getFileName())) {
								// errorList.add(
								// "Invalid file selection. Please try again.");
								// }

								// String fileExt =
								// getFileExtension(uploadedFile.getFileName());
								// AuditInspectionRule rule = new
								// AuditInspectionRule();
								// rule.validateFileType(fileExt);
								// int size = uploadedFile.getFileSize();
								// double fileSizeInBytes = size;
								// double fileSizeInKB = fileSizeInBytes /
								// 1024.0;
								// double fileSizeInMB = fileSizeInKB / 1024.0;
								// DecimalFormat decim = new
								// DecimalFormat("#.##");
								// Double fileSize =
								// Double.parseDouble(decim.format
								// (fileSizeInMB));
								// String photoSize = "(" + fileSize + "MB)";
								// if (rule.isError()) {
								// errorList.add(
								// "Invalid file type. Please select another file."
								// );
								// request.setAttribute("errorsList",errorList);
								// } else
								/*
								 * // if (size >
								 * AuditInspectionConstant.MAX_FILE_SIZE) { //
								 * errorList.add(
								 * "File size is Greater than 10 MB. Please select another file."
								 * );
								 * request.setAttribute("errorsList",errorList);
								 * // } else { // if(uploadedFile.getFileData()
								 * != null) // { // filePath =
								 * uploadFile(dto.getRegNumber
								 * (),uploadedFile.getFileData
								 * (),partyId,RegCompCheckerConstant
								 * .UPLOAD_PATH_PARTY_RELATED_COMPLAINCE,
								 * RegCompCheckerConstant
								 * .FILE_NAME_CLAIMANT_PHOTO); // }
								 * //logger.debug
								 * ("**************************"+filePath); //
								 * if(filePath != null) {
								 * partyRelatedComplianceMap =
								 * eForm.getCompliancePartyRelatedMap(); Set
								 * mapSet =
								 * (Set)partyRelatedComplianceMap.entrySet();
								 * Iterator mapIterator = mapSet.iterator();
								 * while(mapIterator.hasNext()) { Map.Entry
								 * mapEntry = (Map.Entry)mapIterator.next();
								 * String partyID = (String)mapEntry.getKey();
								 * if(partyId.equals(partyID)) { ArrayList
								 * valueList = (ArrayList)mapEntry.getValue();
								 * RegCompleteMakerDTO gdto =
								 * (RegCompleteMakerDTO)valueList.get(0);
								 * gdto.setClaimntPhotoPath(dto.getScanPath());
								 * gdto.setClaimntPhotoName(dto.getScanName());
								 * gdto.setIsUpload("Y"); } } }
								 * eForm.setCompliancePartyRelatedMap
								 * (partyRelatedComplianceMap);
								 * 
								 * }
								 */// }
							} catch (Exception e) {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							String deedid = regmkrBD.getDeedId(dto.getRegNumber());
							request.setAttribute("deedId", deedid);
							forwardJsp = "complianceListNew";
						}

					}
					//**********************************************************
					// *END 7****************//
					// **********************************8.PAN or form
					// 60/61*************************************//
					if ("partyPanForm".equalsIgnoreCase(frwdName1)) {
						if ("setUploadFile".equalsIgnoreCase(actionName)) {
							RegCompleteMakerDTO rrdto = new RegCompleteMakerDTO();
							try {
								// Integer indx =
								// Integer.parseInt(dto.getIndx());
								String partyId = dto.getPartyId();
								boolean boo = regmkrBD.updatePartyUpload(partyId, dto.getRegNumber(), RegCompCheckerConstant.PAN_FORM_COLUMN, dto.getScanPath());
								if (boo) {
									partyRelatedComplianceMap = regmkrBD.getPartyRelatedComplianeList(dto.getRegNumber(), language);
									eForm.setCompliancePartyRelatedMap(partyRelatedComplianceMap);
								} else {
									errorList.add("Unable to upload file. Please try again.");
									request.setAttribute("errorsList", errorList);
								}
								// FormFile uploadFile[] =
								// dto.getPartyPanForm60();
								// FormFile uploadedFile = uploadFile[indx];
								//logger.debug("######"+uploadedFile.getFileName
								// ());
								// if ("".equals(uploadedFile.getFileName())) {
								// errorList.add(
								// "Invalid file selection. Please try again.");
								// }

								// String fileExt =
								// getFileExtension(uploadedFile.getFileName());
								// AuditInspectionRule rule = new
								// AuditInspectionRule();
								// rule.validateFileType(fileExt);
								// int size = uploadedFile.getFileSize();
								// double fileSizeInBytes = size;
								// double fileSizeInKB = fileSizeInBytes /
								// 1024.0;
								// double fileSizeInMB = fileSizeInKB / 1024.0;
								// DecimalFormat decim = new
								// DecimalFormat("#.##");
								// Double fileSize =
								// Double.parseDouble(decim.format
								// (fileSizeInMB));
								// String photoSize = "(" + fileSize + "MB)";
								// if (rule.isError()) {
								// errorList.add(
								// "Invalid file type. Please select another file."
								// );
								// request.setAttribute("errorsList",errorList);
								// } else
								/*
								 * // if (size >
								 * AuditInspectionConstant.MAX_FILE_SIZE) { //
								 * errorList.add(
								 * "File size is Greater than 10 MB. Please select another file."
								 * ); //
								 * request.setAttribute("errorsList",errorList);
								 * // } else { // if(uploadedFile.getFileData()
								 * != null) // { // filePath =
								 * uploadFile(dto.getRegNumber
								 * (),uploadedFile.getFileData
								 * (),partyId,RegCompCheckerConstant
								 * .UPLOAD_PATH_PARTY_RELATED_COMPLAINCE,
								 * RegCompCheckerConstant
								 * .FILE_NAME_PAN_FORM_60); // } //
								 * //logger.debug
								 * ("**************************"+filePath); //
								 * if(filePath != null) {
								 * partyRelatedComplianceMap =
								 * eForm.getCompliancePartyRelatedMap(); Set
								 * mapSet =
								 * (Set)partyRelatedComplianceMap.entrySet();
								 * Iterator mapIterator = mapSet.iterator();
								 * while(mapIterator.hasNext()) { Map.Entry
								 * mapEntry = (Map.Entry)mapIterator.next();
								 * String partyID = (String)mapEntry.getKey();
								 * if(partyId.equals(partyID)) { ArrayList
								 * valueList = (ArrayList)mapEntry.getValue();
								 * RegCompleteMakerDTO gdto =
								 * (RegCompleteMakerDTO)valueList.get(0);
								 * gdto.setPanForm60Path(dto.getScanPath());
								 * gdto.setPanForm60Name(dto.getScanName());
								 * gdto.setIsUpload("Y"); } } }
								 * eForm.setCompliancePartyRelatedMap
								 * (partyRelatedComplianceMap);
								 * 
								 * }
								 */// }
							} catch (Exception e) {
								errorList.add("Unable to upload file. Please try again.");
								request.setAttribute("errorsList", errorList);
							}
							String deedid = regmkrBD.getDeedId(dto.getRegNumber());
							request.setAttribute("deedId", deedid);
							forwardJsp = "complianceListNew";
						}

					}

					// ********************************END
					// 8**************************************//

					/*
					 * if ("photo".equalsIgnoreCase(frwdName)) { if
					 * ("setUploadFile".equalsIgnoreCase(actionName)) { try {
					 * errorList = new ArrayList(); FormFile uploadedFile =
					 * dto.getFilePhoto(); if
					 * ("".equals(uploadedFile.getFileName())) { errorList
					 * .add("Invalid file selection. Please try again."); }
					 * String fileExt = getFileExtension(uploadedFile
					 * .getFileName()); AuditInspectionRule rule = new
					 * AuditInspectionRule(); rule.validateFileType(fileExt);
					 * int size = uploadedFile.getFileSize(); double
					 * fileSizeInBytes = size; // Convert the bytes to Kilobytes
					 * (1 KB = 1024 // Bytes) double fileSizeInKB =
					 * fileSizeInBytes / 1024.0; // Convert the KB to MegaBytes
					 * (1 MB = 1024 // KBytes) double fileSizeInMB =
					 * fileSizeInKB / 1024.0; DecimalFormat decim = new
					 * DecimalFormat("#.##"); Double fileSize =
					 * Double.parseDouble(decim .format(fileSizeInMB)); String
					 * photoSize = "(" + fileSize + "MB)";
					 * 
					 * if (rule.isError()) { errorList
					 * .add("Invalid file type. Please select another file.");
					 * 
					 * request.setAttribute("errorsList", errorList); } else {
					 * if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					 * errorList.add(
					 * "File size is Greater than 10 MB. Please select another file."
					 * ); request.setAttribute("errorsList", errorList); } else
					 * {
					 * 
					 * //ArrayList list = new ArrayList();
					 * 
					 * //ArrayList templist; //if
					 * (eForm.getRegDTO().getPhotoLst() //.size() == 0) {
					 * //list.add(uploadedFile);
					 * //eForm.getRegDTO().setPhotoLst(list); //} else { //
					 * templist = eForm.getRegDTO() // .getPhotoLst();
					 * //templist.add(uploadedFile); // ArrayList sublst=
					 * (ArrayList) // templist.get(0); //for (int i = 0; i <
					 * templist.size(); i++) {
					 * 
					 * // RegCompleteMakerDTO rdto = // new
					 * RegCompleteMakerDTO(); // rdto //
					 * .setTname((String)templist // .get(i)); list.add(rdto);
					 * 
					 * //System.out.println(templist //.get(i)); //}
					 * 
					 * // list.addAll(templist);
					 * //eForm.getRegDTO().setPhotoLst( //templist);
					 * 
					 * //} //String upld = uploadFile(uploadedFile,
					 * //uploadedFile.getFileName(), dto //.getRegNumber());
					 * //eForm.setRegDTO(dto);
					 * 
					 * 
					 * 
					 * //code here for storing data in database
					 * 
					 * String partyTxnId = dto.getSlctRad();// get value of
					 * selected String partyName =
					 * regmkrBD.getPartyName(partyTxnId); String partyName1
					 * =null; String photoDocName=uploadedFile.getFileName();
					 * byte[] content = uploadedFile.getFileData();
					 * 
					 * boolean
					 * chkAlrdyInsrtdData=regmkrBD.checkCompliancedata(partyTxnId
					 * );
					 * 
					 * if(chkAlrdyInsrtdData) { //code for updating } else {
					 * boolean InsertData =
					 * regmkrBD.insertCompliancedata(partyTxnId,partyName,
					 * photoDocName,content,cdate,UserId); }
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 * } } forwardJsp = "successtest"; } catch (Exception e) {
					 * System.out.println(e.getStackTrace()); } }
					 * 
					 * if ("removeUploadFile".equalsIgnoreCase(actionName)) {
					 * try { ArrayList templist = eForm.getRegDTO()
					 * .getPhotoLst(); int i = Integer.parseInt(index); FormFile
					 * fname = (FormFile) templist.get(i); fname.getFileName();
					 * removeFile(fname.getFileName()); templist.remove(i);
					 * System.out.println(templist);
					 * eForm.getRegDTO().setPhotoLst(templist); forwardJsp =
					 * "successtest"; } catch (Exception e) {
					 * e.printStackTrace(); } }
					 * 
					 * }
					 * 
					 * // $.1 Start:===upload of old registered document if
					 * ("oldregdoc".equalsIgnoreCase(frwdName)) { if
					 * ("setUploadFile".equalsIgnoreCase(actionName)) { try {
					 * errorList = new ArrayList(); FormFile uploadedFile =
					 * dto.getOldRedDoc(); if
					 * ("".equals(uploadedFile.getFileName())) { errorList
					 * .add("Invalid file selection. Please try again."); }
					 * String fileExt = getFileExtension(uploadedFile
					 * .getFileName()); AuditInspectionRule rule = new
					 * AuditInspectionRule(); rule.validateFileType(fileExt);
					 * int size = uploadedFile.getFileSize(); double
					 * fileSizeInBytes = size; // Convert the bytes to Kilobytes
					 * (1 KB = 1024 // Bytes) double fileSizeInKB =
					 * fileSizeInBytes / 1024.0; // Convert the KB to MegaBytes
					 * (1 MB = 1024 // KBytes) double fileSizeInMB =
					 * fileSizeInKB / 1024.0; DecimalFormat decim = new
					 * DecimalFormat("#.##"); Double fileSize =
					 * Double.parseDouble(decim .format(fileSizeInMB)); String
					 * photoSize = "(" + fileSize + "MB)";
					 * 
					 * if (rule.isError()) { errorList
					 * .add("Invalid file type. Please select another file.");
					 * 
					 * request.setAttribute("errorsList", errorList); } else {
					 * if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					 * errorList.add(
					 * "File size is Greater than 10 MB. Please select another file."
					 * ); request.setAttribute("errorsList", errorList); } else
					 * {
					 * 
					 * ArrayList list = new ArrayList(); ; ArrayList templist;
					 * if (eForm.getRegDTO().getOldRegDocLst() .size() == 0) {
					 * list.add(uploadedFile);
					 * eForm.getRegDTO().setOldRegDocLst( list); } else {
					 * templist = eForm.getRegDTO() .getOldRegDocLst();
					 * templist.add(uploadedFile); // ArrayList sublst=
					 * (ArrayList) // templist.get(0); for (int i = 0; i <
					 * templist.size(); i++) {
					 * 
					 * RegCompleteMakerDTO rdto = new RegCompleteMakerDTO();
					 * rdto .setTname((String)templist .get(i)); list.add(rdto);
					 * // System.out.println(templist .get(i)); }
					 * 
					 * // list.addAll(templist);
					 * eForm.getRegDTO().setOldRegDocLst( templist);
					 * 
					 * } String upld = uploadFile(uploadedFile,
					 * uploadedFile.getFileName(), dto .getRegNumber());
					 * eForm.setRegDTO(dto);
					 * 
					 * } } forwardJsp = "successtest"; } catch (Exception e) {
					 * System.out.println(e.getStackTrace()); } }
					 * 
					 * if ("removeUploadFile".equalsIgnoreCase(actionName)) {
					 * try { ArrayList templist = eForm.getRegDTO()
					 * .getOldRegDocLst(); int i = Integer.parseInt(index);
					 * FormFile fname = (FormFile) templist.get(i);
					 * fname.getFileName(); removeFile(fname.getFileName());
					 * templist.remove(i); System.out.println(templist);
					 * eForm.getRegDTO().setOldRegDocLst(templist); forwardJsp =
					 * "successtest"; } catch (Exception e) {
					 * e.printStackTrace(); } }
					 * 
					 * } // End:==== upload of old registered document
					 * 
					 * // $2. Start=== propert image from three different angle
					 * if ("propphoto".equalsIgnoreCase(frwdName)) { if
					 * ("setUploadFile".equalsIgnoreCase(actionName)) { try {
					 * errorList = new ArrayList(); FormFile uploadedFile =
					 * dto.getPropImg(); if
					 * ("".equals(uploadedFile.getFileName())) { errorList
					 * .add("Invalid file selection. Please try again."); }
					 * String fileExt = getFileExtension(uploadedFile
					 * .getFileName()); AuditInspectionRule rule = new
					 * AuditInspectionRule(); rule.validateFileType(fileExt);
					 * int size = uploadedFile.getFileSize(); double
					 * fileSizeInBytes = size; // Convert the bytes to Kilobytes
					 * (1 KB = 1024 // Bytes) double fileSizeInKB =
					 * fileSizeInBytes / 1024.0; // Convert the KB to MegaBytes
					 * (1 MB = 1024 // KBytes) double fileSizeInMB =
					 * fileSizeInKB / 1024.0; DecimalFormat decim = new
					 * DecimalFormat("#.##"); Double fileSize =
					 * Double.parseDouble(decim .format(fileSizeInMB)); String
					 * photoSize = "(" + fileSize + "MB)";
					 * 
					 * if (rule.isError()) { errorList
					 * .add("Invalid file type. Please select another file.");
					 * 
					 * request.setAttribute("errorsList", errorList); } else {
					 * if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					 * errorList.add(
					 * "File size is Greater than 10 MB. Please select another file."
					 * ); request.setAttribute("errorsList", errorList); } else
					 * {
					 * 
					 * ArrayList list = new ArrayList(); ; ArrayList templist;
					 * if (eForm.getRegDTO().getPropImgLst() .size() == 0) {
					 * list.add(uploadedFile); eForm.getRegDTO().setPropImgLst(
					 * list); } else { templist = eForm.getRegDTO()
					 * .getPropImgLst(); templist.add(uploadedFile); //
					 * ArrayList sublst= (ArrayList) // templist.get(0); for
					 * (int i = 0; i < templist.size(); i++) { /// //
					 * RegCompleteMakerDTO rdto = // new RegCompleteMakerDTO();
					 * /// rdto // .setTname((String)templist // .get(i));
					 * list.add(rdto); // System.out.println(templist .get(i));
					 * }
					 * 
					 * // list.addAll(templist);
					 * eForm.getRegDTO().setPropImgLst( templist);
					 * 
					 * } String upld = uploadFile(uploadedFile,
					 * uploadedFile.getFileName(), dto .getRegNumber());
					 * eForm.setRegDTO(dto);
					 * 
					 * } } forwardJsp = "successtest"; } catch (Exception e) {
					 * System.out.println(e.getStackTrace()); } }
					 * 
					 * if ("removeUploadFile".equalsIgnoreCase(actionName)) {
					 * try { ArrayList templist = eForm.getRegDTO()
					 * .getPropImgLst(); int i = Integer.parseInt(index);
					 * FormFile fname = (FormFile) templist.get(i);
					 * fname.getFileName(); removeFile(fname.getFileName());
					 * templist.remove(i); System.out.println(templist);
					 * eForm.getRegDTO().setPropImgLst(templist); forwardJsp =
					 * "successtest"; } catch (Exception e) {
					 * e.printStackTrace(); } }
					 * 
					 * } // $2. end=== propert image from three different angle
					 * 
					 * // $3.Start :=== notarized affidavit by seller
					 * 
					 * if ("notaffi".equalsIgnoreCase(frwdName)) { if
					 * ("setUploadFile".equalsIgnoreCase(actionName)) { try {
					 * errorList = new ArrayList(); FormFile uploadedFile = dto
					 * .getNotarizedAffidavit(); if
					 * ("".equals(uploadedFile.getFileName())) { errorList
					 * .add("Invalid file selection. Please try again."); }
					 * String fileExt = getFileExtension(uploadedFile
					 * .getFileName()); AuditInspectionRule rule = new
					 * AuditInspectionRule(); rule.validateFileType(fileExt);
					 * int size = uploadedFile.getFileSize(); double
					 * fileSizeInBytes = size; // Convert the bytes to Kilobytes
					 * (1 KB = 1024 // Bytes) double fileSizeInKB =
					 * fileSizeInBytes / 1024.0; // Convert the KB to MegaBytes
					 * (1 MB = 1024 // KBytes) double fileSizeInMB =
					 * fileSizeInKB / 1024.0; DecimalFormat decim = new
					 * DecimalFormat("#.##"); Double fileSize =
					 * Double.parseDouble(decim .format(fileSizeInMB)); String
					 * photoSize = "(" + fileSize + "MB)";
					 * 
					 * if (rule.isError()) { errorList
					 * .add("Invalid file type. Please select another file.");
					 * 
					 * request.setAttribute("errorsList", errorList); } else {
					 * if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					 * errorList.add(
					 * "File size is Greater than 10 MB. Please select another file."
					 * ); request.setAttribute("errorsList", errorList); } else
					 * {
					 * 
					 * ArrayList list = new ArrayList(); ; ArrayList templist;
					 * if (eForm.getRegDTO().getNotAffiList() .size() == 0) {
					 * list.add(uploadedFile); eForm.getRegDTO().setNotAffiList(
					 * list); } else { templist = eForm.getRegDTO()
					 * .getNotAffiList(); templist.add(uploadedFile); //
					 * ArrayList sublst= (ArrayList) // templist.get(0); for
					 * (int i = 0; i < templist.size(); i++) { /// ///
					 * RegCompleteMakerDTO rdto = // new RegCompleteMakerDTO();
					 * // rdto // .setTname((String)templist // .get(i));
					 * list.add(rdto); // System.out.println(templist .get(i));
					 * }
					 * 
					 * // list.addAll(templist);
					 * eForm.getRegDTO().setNotAffiList( templist);
					 * 
					 * } String upld = uploadFile(uploadedFile,
					 * uploadedFile.getFileName(), dto .getRegNumber());
					 * eForm.setRegDTO(dto);
					 * 
					 * } } forwardJsp = "successtest"; } catch (Exception e) {
					 * System.out.println(e.getStackTrace()); } }
					 * 
					 * if ("removeUploadFile".equalsIgnoreCase(actionName)) {
					 * try { ArrayList templist = eForm.getRegDTO()
					 * .getNotAffiList(); int i = Integer.parseInt(index);
					 * FormFile fname = (FormFile) templist.get(i);
					 * fname.getFileName(); removeFile(fname.getFileName());
					 * templist.remove(i); System.out.println(templist);
					 * eForm.getRegDTO().setNotAffiList(templist); forwardJsp =
					 * "successtest"; } catch (Exception e) {
					 * e.printStackTrace(); } }
					 * 
					 * }
					 * 
					 * // $3.end :=== notarized affidavit by seller
					 * 
					 * // $4. Start:==== photo and affidavit if the seller is
					 * not // present if
					 * ("photoaffi".equalsIgnoreCase(frwdName)) {
					 * 
					 * if ("setUploadFile".equalsIgnoreCase(actionName)) { try {
					 * errorList = new ArrayList(); FormFile uploadedFile =
					 * dto.getPhotoAffi(); if
					 * ("".equals(uploadedFile.getFileName())) { errorList
					 * .add("Invalid file selection. Please try again."); }
					 * String fileExt = getFileExtension(uploadedFile
					 * .getFileName()); AuditInspectionRule rule = new
					 * AuditInspectionRule(); rule.validateFileType(fileExt);
					 * int size = uploadedFile.getFileSize(); double
					 * fileSizeInBytes = size; // Convert the bytes to Kilobytes
					 * (1 KB = 1024 // Bytes) double fileSizeInKB =
					 * fileSizeInBytes / 1024.0; // Convert the KB to MegaBytes
					 * (1 MB = 1024 // KBytes) double fileSizeInMB =
					 * fileSizeInKB / 1024.0; DecimalFormat decim = new
					 * DecimalFormat("#.##"); Double fileSize =
					 * Double.parseDouble(decim .format(fileSizeInMB)); String
					 * photoSize = "(" + fileSize + "MB)";
					 * 
					 * if (rule.isError()) { errorList
					 * .add("Invalid file type. Please select another file.");
					 * 
					 * request.setAttribute("errorsList", errorList); } else {
					 * if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					 * errorList.add(
					 * "File size is Greater than 10 MB. Please select another file."
					 * ); request.setAttribute("errorsList", errorList); } else
					 * {
					 * 
					 * ArrayList list = new ArrayList(); ; ArrayList templist;
					 * if (eForm.getRegDTO().getPhotoAffiLst() .size() == 0) {
					 * list.add(uploadedFile);
					 * eForm.getRegDTO().setPhotoAffiLst( list); } else {
					 * templist = eForm.getRegDTO() .getPhotoAffiLst();
					 * templist.add(uploadedFile); // ArrayList sublst=
					 * (ArrayList) // templist.get(0); for (int i = 0; i <
					 * templist.size(); i++) { /// // RegCompleteMakerDTO rdto =
					 * // new RegCompleteMakerDTO(); // rdto //
					 * .setTname((String)templist // .get(i)); list.add(rdto);
					 * // System.out.println(templist .get(i)); }
					 * 
					 * // list.addAll(templist);
					 * eForm.getRegDTO().setPhotoAffiLst( templist);
					 * 
					 * } String upld = uploadFile(uploadedFile,
					 * uploadedFile.getFileName(), dto .getRegNumber());
					 * eForm.setRegDTO(dto);
					 * 
					 * } } forwardJsp = "successtest"; } catch (Exception e) {
					 * System.out.println(e.getStackTrace()); } }
					 * 
					 * if ("removeUploadFile".equalsIgnoreCase(actionName)) {
					 * try { ArrayList templist = eForm.getRegDTO()
					 * .getPhotoAffiLst(); int i = Integer.parseInt(index);
					 * FormFile fname = (FormFile) templist.get(i);
					 * fname.getFileName(); removeFile(fname.getFileName());
					 * templist.remove(i); System.out.println(templist);
					 * eForm.getRegDTO().setPhotoAffiLst(templist); forwardJsp =
					 * "successtest"; } catch (Exception e) {
					 * e.printStackTrace(); } }
					 * 
					 * }
					 * 
					 * // $4. End:==== photo and affidavit if the seller is not
					 * // present
					 * 
					 * // $5. start :==== upload property map if
					 * ("propmap".equalsIgnoreCase(frwdName)) {
					 * 
					 * if ("setUploadFile".equalsIgnoreCase(actionName)) { try {
					 * errorList = new ArrayList(); FormFile uploadedFile =
					 * dto.getPropmap(); if
					 * ("".equals(uploadedFile.getFileName())) { errorList
					 * .add("Invalid file selection. Please try again."); }
					 * String fileExt = getFileExtension(uploadedFile
					 * .getFileName()); AuditInspectionRule rule = new
					 * AuditInspectionRule(); rule.validateFileType(fileExt);
					 * int size = uploadedFile.getFileSize(); double
					 * fileSizeInBytes = size; // Convert the bytes to Kilobytes
					 * (1 KB = 1024 // Bytes) double fileSizeInKB =
					 * fileSizeInBytes / 1024.0; // Convert the KB to MegaBytes
					 * (1 MB = 1024 // KBytes) double fileSizeInMB =
					 * fileSizeInKB / 1024.0; DecimalFormat decim = new
					 * DecimalFormat("#.##"); Double fileSize =
					 * Double.parseDouble(decim .format(fileSizeInMB)); String
					 * photoSize = "(" + fileSize + "MB)";
					 * 
					 * if (rule.isError()) { errorList
					 * .add("Invalid file type. Please select another file.");
					 * 
					 * request.setAttribute("errorsList", errorList); } else {
					 * if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					 * errorList.add(
					 * "File size is Greater than 10 MB. Please select another file."
					 * ); request.setAttribute("errorsList", errorList); } else
					 * {
					 * 
					 * ArrayList list = new ArrayList(); ; ArrayList templist;
					 * if (eForm.getRegDTO().getPropMapLst() .size() == 0) {
					 * list.add(uploadedFile); eForm.getRegDTO().setPropMapLst(
					 * list); } else { templist = eForm.getRegDTO()
					 * .getPropMapLst(); templist.add(uploadedFile); //
					 * ArrayList sublst= (ArrayList) // templist.get(0); for
					 * (int i = 0; i < templist.size(); i++) { /// //
					 * RegCompleteMakerDTO rdto = // new RegCompleteMakerDTO();
					 * // rdto // .setTname((String)templist // .get(i));
					 * list.add(rdto); // System.out.println(templist .get(i));
					 * }
					 * 
					 * // list.addAll(templist);
					 * eForm.getRegDTO().setPropMapLst( templist);
					 * 
					 * } String upld = uploadFile(uploadedFile,
					 * uploadedFile.getFileName(), dto .getRegNumber());
					 * eForm.setRegDTO(dto);
					 * 
					 * } } forwardJsp = "successtest"; } catch (Exception e) {
					 * System.out.println(e.getStackTrace()); } }
					 * 
					 * if ("removeUploadFile".equalsIgnoreCase(actionName)) {
					 * try { ArrayList templist = eForm.getRegDTO()
					 * .getPropMapLst(); int i = Integer.parseInt(index);
					 * FormFile fname = (FormFile) templist.get(i);
					 * fname.getFileName(); removeFile(fname.getFileName());
					 * templist.remove(i); System.out.println(templist);
					 * eForm.getRegDTO().setPropMapLst(templist); forwardJsp =
					 * "successtest"; } catch (Exception e) {
					 * e.printStackTrace(); } }
					 * 
					 * }
					 * 
					 * // $5. end :==== upload property map
					 * 
					 * // $6.start:===upload details of poa if
					 * ("poadet".equalsIgnoreCase(frwdName)) {
					 * 
					 * if ("setUploadFile".equalsIgnoreCase(actionName)) { try {
					 * errorList = new ArrayList(); FormFile uploadedFile =
					 * dto.getPoaDet(); if
					 * ("".equals(uploadedFile.getFileName())) { errorList
					 * .add("Invalid file selection. Please try again."); }
					 * String fileExt = getFileExtension(uploadedFile
					 * .getFileName()); AuditInspectionRule rule = new
					 * AuditInspectionRule(); rule.validateFileType(fileExt);
					 * int size = uploadedFile.getFileSize(); double
					 * fileSizeInBytes = size; // Convert the bytes to Kilobytes
					 * (1 KB = 1024 // Bytes) double fileSizeInKB =
					 * fileSizeInBytes / 1024.0; // Convert the KB to MegaBytes
					 * (1 MB = 1024 // KBytes) double fileSizeInMB =
					 * fileSizeInKB / 1024.0; DecimalFormat decim = new
					 * DecimalFormat("#.##"); Double fileSize =
					 * Double.parseDouble(decim .format(fileSizeInMB)); String
					 * photoSize = "(" + fileSize + "MB)";
					 * 
					 * if (rule.isError()) { errorList
					 * .add("Invalid file type. Please select another file.");
					 * 
					 * request.setAttribute("errorsList", errorList); } else {
					 * if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					 * errorList.add(
					 * "File size is Greater than 10 MB. Please select another file."
					 * ); request.setAttribute("errorsList", errorList); } else
					 * {
					 * 
					 * ArrayList list = new ArrayList(); ; ArrayList templist;
					 * if (eForm.getRegDTO().getPoaDetLst() .size() == 0) {
					 * list.add(uploadedFile); eForm.getRegDTO()
					 * .setPoaDetLst(list); } else { templist =
					 * eForm.getRegDTO() .getPoaDetLst();
					 * templist.add(uploadedFile); // ArrayList sublst=
					 * (ArrayList) // templist.get(0); for (int i = 0; i <
					 * templist.size(); i++) {
					 * 
					 * RegCompleteMakerDTO rdto = new RegCompleteMakerDTO();
					 * rdto .setTname((String)templist .get(i)); list.add(rdto);
					 * 
					 * System.out.println(templist .get(i)); }
					 * 
					 * // list.addAll(templist); eForm.getRegDTO().setPoaDetLst(
					 * templist);
					 * 
					 * } String upld = uploadFile(uploadedFile,
					 * uploadedFile.getFileName(), dto .getRegNumber());
					 * eForm.setRegDTO(dto);
					 * 
					 * } } forwardJsp = "successtest"; } catch (Exception e) {
					 * System.out.println(e.getStackTrace()); } }
					 * 
					 * if ("removeUploadFile".equalsIgnoreCase(actionName)) {
					 * try { ArrayList templist = eForm.getRegDTO()
					 * .getPoaDetLst(); int i = Integer.parseInt(index);
					 * FormFile fname = (FormFile) templist.get(i);
					 * fname.getFileName(); removeFile(fname.getFileName());
					 * templist.remove(i); System.out.println(templist);
					 * eForm.getRegDTO().setPoaDetLst(templist); forwardJsp =
					 * "successtest"; } catch (Exception e) {
					 * e.printStackTrace(); } }
					 * 
					 * }
					 * 
					 * // $.6 End :======upload details of poa
					 * 
					 * // $7. start :===== upload rin pustika if
					 * ("rinpustika".equalsIgnoreCase(frwdName)) {
					 * 
					 * if ("setUploadFile".equalsIgnoreCase(actionName)) { try {
					 * errorList = new ArrayList(); FormFile uploadedFile =
					 * dto.getRinPustika(); if
					 * ("".equals(uploadedFile.getFileName())) { errorList
					 * .add("Invalid file selection. Please try again."); }
					 * String fileExt = getFileExtension(uploadedFile
					 * .getFileName()); AuditInspectionRule rule = new
					 * AuditInspectionRule(); rule.validateFileType(fileExt);
					 * int size = uploadedFile.getFileSize(); double
					 * fileSizeInBytes = size; // Convert the bytes to Kilobytes
					 * (1 KB = 1024 // Bytes) double fileSizeInKB =
					 * fileSizeInBytes / 1024.0; // Convert the KB to MegaBytes
					 * (1 MB = 1024 // KBytes) double fileSizeInMB =
					 * fileSizeInKB / 1024.0; DecimalFormat decim = new
					 * DecimalFormat("#.##"); Double fileSize =
					 * Double.parseDouble(decim .format(fileSizeInMB)); String
					 * photoSize = "(" + fileSize + "MB)";
					 * 
					 * if (rule.isError()) { errorList
					 * .add("Invalid file type. Please select another file.");
					 * 
					 * request.setAttribute("errorsList", errorList); } else {
					 * if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					 * errorList.add(
					 * "File size is Greater than 10 MB. Please select another file."
					 * ); request.setAttribute("errorsList", errorList); } else
					 * {
					 * 
					 * ArrayList list = new ArrayList(); ; ArrayList templist;
					 * if (eForm.getRegDTO() .getRinPustikaLst().size() == 0) {
					 * list.add(uploadedFile);
					 * eForm.getRegDTO().setRinPustikaLst( list); } else {
					 * templist = eForm.getRegDTO() .getRinPustikaLst();
					 * templist.add(uploadedFile); // ArrayList sublst=
					 * (ArrayList) // templist.get(0); for (int i = 0; i <
					 * templist.size(); i++) {
					 * 
					 * RegCompleteMakerDTO rdto = new RegCompleteMakerDTO();
					 * rdto .setTname((String)templist .get(i)); list.add(rdto);
					 * 
					 * System.out.println(templist .get(i)); }
					 * 
					 * // list.addAll(templist);
					 * eForm.getRegDTO().setRinPustikaLst( templist);
					 * 
					 * } String upld = uploadFile(uploadedFile,
					 * uploadedFile.getFileName(), dto .getRegNumber());
					 * eForm.setRegDTO(dto);
					 * 
					 * } } forwardJsp = "successtest"; } catch (Exception e) {
					 * System.out.println(e.getStackTrace()); } }
					 * 
					 * if ("removeUploadFile".equalsIgnoreCase(actionName)) {
					 * try { ArrayList templist = eForm.getRegDTO()
					 * .getPhotoAffiLst(); int i = Integer.parseInt(index);
					 * FormFile fname = (FormFile) templist.get(i);
					 * fname.getFileName(); removeFile(fname.getFileName());
					 * templist.remove(i); System.out.println(templist);
					 * eForm.getRegDTO().setRinPustikaLst(templist); forwardJsp
					 * = "successtest"; } catch (Exception e) {
					 * e.printStackTrace(); } }
					 * 
					 * }
					 * 
					 * // 7. end:===== upload rin pustika
					 * 
					 * // $8. start :===== upload khasra if
					 * ("khasra".equalsIgnoreCase(frwdName)) {
					 * 
					 * if ("setUploadFile".equalsIgnoreCase(actionName)) { try {
					 * errorList = new ArrayList(); FormFile uploadedFile =
					 * dto.getKhasraNum(); if
					 * ("".equals(uploadedFile.getFileName())) { errorList
					 * .add("Invalid file selection. Please try again."); }
					 * String fileExt = getFileExtension(uploadedFile
					 * .getFileName()); AuditInspectionRule rule = new
					 * AuditInspectionRule(); rule.validateFileType(fileExt);
					 * int size = uploadedFile.getFileSize(); double
					 * fileSizeInBytes = size; // Convert the bytes to Kilobytes
					 * (1 KB = 1024 // Bytes) double fileSizeInKB =
					 * fileSizeInBytes / 1024.0; // Convert the KB to MegaBytes
					 * (1 MB = 1024 // KBytes) double fileSizeInMB =
					 * fileSizeInKB / 1024.0; DecimalFormat decim = new
					 * DecimalFormat("#.##"); Double fileSize =
					 * Double.parseDouble(decim .format(fileSizeInMB)); String
					 * photoSize = "(" + fileSize + "MB)";
					 * 
					 * if (rule.isError()) { errorList
					 * .add("Invalid file type. Please select another file.");
					 * 
					 * request.setAttribute("errorsList", errorList); } else {
					 * if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					 * errorList.add(
					 * "File size is Greater than 10 MB. Please select another file."
					 * ); request.setAttribute("errorsList", errorList); } else
					 * {
					 * 
					 * ArrayList list = new ArrayList(); ; ArrayList templist;
					 * if (eForm.getRegDTO().getKhasraNumLst() .size() == 0) {
					 * list.add(uploadedFile);
					 * eForm.getRegDTO().setKhasraNumLst( list); } else {
					 * templist = eForm.getRegDTO() .getKhasraNumLst();
					 * templist.add(uploadedFile); // ArrayList sublst=
					 * (ArrayList) // templist.get(0); for (int i = 0; i <
					 * templist.size(); i++) { / RegCompleteMakerDTO rdto = new
					 * RegCompleteMakerDTO(); rdto .setTname((String)templist
					 * .get(i)); list.add(rdto);
					 * 
					 * System.out.println(templist .get(i)); }
					 * 
					 * // list.addAll(templist);
					 * eForm.getRegDTO().setKhasraNumLst( templist);
					 * 
					 * } String upld = uploadFile(uploadedFile,
					 * uploadedFile.getFileName(), dto .getRegNumber());
					 * eForm.setRegDTO(dto);
					 * 
					 * } } forwardJsp = "successtest"; } catch (Exception e) {
					 * System.out.println(e.getStackTrace()); } }
					 * 
					 * if ("removeUploadFile".equalsIgnoreCase(actionName)) {
					 * try { ArrayList templist = eForm.getRegDTO()
					 * .getPhotoAffiLst(); int i = Integer.parseInt(index);
					 * FormFile fname = (FormFile) templist.get(i);
					 * fname.getFileName(); removeFile(fname.getFileName());
					 * templist.remove(i); System.out.println(templist);
					 * eForm.getRegDTO().setKhasraNumLst(templist); forwardJsp =
					 * "successtest"; } catch (Exception e) {
					 * e.printStackTrace(); } }
					 * 
					 * }
					 * 
					 * // 8. end:===== upload khasra
					 * 
					 * if ("thumb".equalsIgnoreCase(frwdName)) {
					 * 
					 * if ("setUploadFile".equalsIgnoreCase(actionName)) { try {
					 * errorList = new ArrayList(); FormFile uploadedFile =
					 * dto.getFileThumb(); if
					 * ("".equals(uploadedFile.getFileName())) { errorList
					 * .add("Invalid file selection. Please try again."); }
					 * String fileExt = getFileExtension(uploadedFile
					 * .getFileName()); AuditInspectionRule rule = new
					 * AuditInspectionRule(); rule.validateFileType(fileExt);
					 * int size = uploadedFile.getFileSize(); double
					 * fileSizeInBytes = size; // Convert the bytes to Kilobytes
					 * (1 KB = 1024 // Bytes) double fileSizeInKB =
					 * fileSizeInBytes / 1024.0; // Convert the KB to MegaBytes
					 * (1 MB = 1024 // KBytes) double fileSizeInMB =
					 * fileSizeInKB / 1024.0; DecimalFormat decim = new
					 * DecimalFormat("#.##"); Double fileSize =
					 * Double.parseDouble(decim .format(fileSizeInMB)); String
					 * photoSize = "(" + fileSize + "MB)";
					 * 
					 * if (rule.isError()) { errorList
					 * .add("Invalid file type. Please select another file.");
					 * 
					 * request.setAttribute("errorsList", errorList); } else {
					 * if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					 * errorList.add(
					 * "File size is Greater than 10 MB. Please select another file."
					 * ); request.setAttribute("errorsList", errorList); } else
					 * {
					 * 
					 * ArrayList list = new ArrayList(); ; ArrayList templist;
					 * if (eForm.getRegDTO().getThunmbName() .size() == 0) {
					 * list.add(uploadedFile); eForm.getRegDTO().setThunmbName(
					 * list); } else { templist = eForm.getRegDTO()
					 * .getThunmbName(); templist.add(uploadedFile); //
					 * ArrayList sublst= (ArrayList) // templist.get(0); for
					 * (int i = 0; i < templist.size(); i++) {
					 * 
					 * RegCompleteMakerDTO rdto = new RegCompleteMakerDTO();
					 * rdto .setTname((String)templist .get(i)); list.add(rdto);
					 * 
					 * System.out.println(templist .get(i)); }
					 * 
					 * // list.addAll(templist);
					 * eForm.getRegDTO().setThunmbName( templist);
					 * 
					 * } String upld = uploadFile(uploadedFile,
					 * uploadedFile.getFileName(), dto .getRegNumber());
					 * eForm.setRegDTO(dto); errorList = new ArrayList(); } }
					 * 
					 * forwardJsp = "successtest"; } catch (Exception e) {
					 * System.out.println(e.getStackTrace()); } }
					 * 
					 * if ("removeUploadFile".equalsIgnoreCase(actionName)) {
					 * try { ArrayList templist = eForm.getRegDTO()
					 * .getThunmbName(); int i = Integer.parseInt(index);
					 * FormFile fname = (FormFile) templist.get(i);
					 * fname.getFileName(); removeFile(fname.getFileName());
					 * templist.remove(i); System.out.println(templist);
					 * eForm.getRegDTO().setThunmbName(templist); forwardJsp =
					 * "successtest"; } catch (Exception e) {
					 * e.printStackTrace(); } }
					 * 
					 * }
					 */

				}
				// ADDED BY SIMRAN
				// logger.debug("<----------------"+actionName);
				if ("linkingPayment".equalsIgnoreCase(formName)) {
					logger.debug("inside checker back action maketAction");
					// String checkHold = eForm.getCheckHold();
					if (RegCompConstant.BACK_TO_CHECKER.equalsIgnoreCase(actionName)) {
						String regNum = dto.getRegNumber();
						double balDuty = cdto.getTempDuty();
						double balfee = cdto.getTempFee();
						String compRegNumber = cdto.getLinkedRegNum();
						ArrayList linkedDuties = new ArrayList();
						String arr[] = dto.getRegStampArr().split(",");

						for (int i = 0; i < arr.length; i++) {
							String[] reg_duty = arr[i].split("~");
							RegCompleteMakerDTO rDTO = new RegCompleteMakerDTO();
							// logger.debug("PROPERTY------>"+reg_duty[0]);
							// logger.debug("regFee---->"+reg_duty[1]);
							// logger.debug("stampDuty---->"+reg_duty[2]);
							linkedDuties = (ArrayList) mymap.get(reg_duty[0]);
							for (int k = 0; k < linkedDuties.size(); k++) {
								CommonMkrDTO cdto1 = (CommonMkrDTO) linkedDuties.get(k);
								// logger.debug("<----"+cdto1.getLinkedFlag());
								// logger.debug("<---"+cdto1.getLinkedNum());
								//logger.debug("<---"+cdto1.getLinkedTotalDuty()
								// );
								//logger.debug("<---"+cdto1.getLinkedTotalRegFee
								// ());
								cdto1.setLinkedTotalDuty(Double.parseDouble(reg_duty[2]));
								cdto1.setLinkedTotalRegFee(Double.parseDouble(reg_duty[1]));

							}
							// rDTO.setRegfee(Double.parseDouble(reg_duty[1]));
							//rDTO.setStampduty(Double.parseDouble(reg_duty[2]))
							// ;
							// linkedDuties.add(rDTO);
							// mymap.put(reg_duty[0],linkedDuties);
						}

						// logger.debug("BAL DUTY"+balDuty);
						// logger.debug("BAL fee"+balfee);
						boolean tmpflg = regmkrBD.checkAlrdyInsertdCheckerLinkin(regNum);// duty
						// linking
						// -
						// roopam
						logger.debug("tempFLAG----->" + tmpflg);
						boolean flag = regmkrBD.saveLinkedAmtChecker(regNum, eForm.getMyMap(), balDuty, balfee, tmpflg, compRegNumber, UserId, cdate, eForm.getRegDTO());
						if (flag) {
							messages.add("MSG", new ActionMessage("save.success"));
							saveMessages(request, messages);
							eForm.setCheck("X");
							request.setAttribute("checkRegID", eForm);
							// request.setAttribute(arg0, arg1)
							// forwardJsp = "linkingPayment";

						}
						forwardJsp = "redirectToChecker";
					}

					if (RegCompConstant.BACK_TO_CHECKER_WITHOUT_CHANGE.equalsIgnoreCase(actionName)) {
						forwardJsp = "redirectToChecker";
					}

					if (RegCompConstant.HOLD_CHECKER.equalsIgnoreCase(actionName)) {
						// Start:== this code for saving the data in the Map
						String regNum = dto.getRegNumber();
						double balDuty = cdto.getTempDuty();
						double balfee = cdto.getTempFee();
						String compRegNumber = cdto.getLinkedRegNum();
						ArrayList linkedDuties = new ArrayList();
						String arr[] = dto.getRegStampArr().split(",");

						for (int i = 0; i < arr.length; i++) {
							String[] reg_duty = arr[i].split("~");
							RegCompleteMakerDTO rDTO = new RegCompleteMakerDTO();
							// logger.debug("PROPERTY------>"+reg_duty[0]);
							// logger.debug("regFee---->"+reg_duty[1]);
							// logger.debug("stampDuty---->"+reg_duty[2]);
							// linkedDuties = (ArrayList)mymap.get(reg_duty[0]);
							for (int k = 0; k < linkedDuties.size(); k++) {
								CommonMkrDTO cdto1 = (CommonMkrDTO) linkedDuties.get(k);
								// logger.debug("<----"+cdto1.getLinkedFlag());
								// logger.debug("<---"+cdto1.getLinkedNum());
								//logger.debug("<---"+cdto1.getLinkedTotalDuty()
								// );
								//logger.debug("<---"+cdto1.getLinkedTotalRegFee
								// ());
								cdto1.setLinkedTotalDuty(Double.parseDouble(reg_duty[2]));
								cdto1.setLinkedTotalRegFee(Double.parseDouble(reg_duty[1]));

							}
							// rDTO.setRegfee(Double.parseDouble(reg_duty[1]));
							//rDTO.setStampduty(Double.parseDouble(reg_duty[2]))
							// ;
							// linkedDuties.add(rDTO);
							// mymap.put(reg_duty[0],linkedDuties);
						}
						boolean tmpflg = regmkrBD.checkAlrdyInsertdCheckerLinkin(regNum);
						boolean flag = regmkrBD.saveLinkedAmtChecker(regNum, eForm.getMyMap(), balDuty, balfee, tmpflg, compRegNumber, UserId, cdate, eForm.getRegDTO());
						// End :== this code for saving the data in the Map

						// Start=== this code for saving the data in the hold
						// table
						/*
						 * String hldFlag = "Y"; String fwdPage =
						 * "linkingPayment"; String createdBy = UserId; Date
						 * date = new Date(); System.out.println(date);
						 * SimpleDateFormat sdf = new
						 * SimpleDateFormat("dd/MM/yyyy");
						 * System.out.println(sdf.format(date)); String Date =
						 * sdf.format(date); boolean flagChecker =
						 * bd.isHoldData(regNum,fwdPage);
						 */
						/*
						 * boolean holdData =
						 * regmkrBD.saveHoldDataChecker(regNum, hldFlag,
						 * fwdPage, Date, createdBy,flagChecker);
						 */
						// boolean holdData = regmkrBD.saveHoldDataChecker(dto,
						// hldFlag,
						// fwdPage, Date, createdBy,flagChecker);
						String holdRemarks = dto.getTxtArea();
						String hldFlag = "Y";
						String fwdPage = "linkingpayment";
						String holdId = "1";
						String createdBy = UserId;
						Date date = new Date();
						System.out.println(date);
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						System.out.println(sdf.format(date));
						String Date = sdf.format(date);
						String str = regmkrBD.saveHoldData(regNum, hldFlag, fwdPage, Date, createdBy, holdId, holdRemarks, officeId);
						// End=== this code for saving the data in the hold
						// table
						// if (holdData) {
						forwardJsp = "redirectToCheckerHold";
						// }
					}

					if (RegCompConstant.PREVIOUS_CHECKER.equalsIgnoreCase(actionName)) {

						forwardJsp = "redirectToCheckerLink";

					}
					if (RegCompConstant.PREVIOUS_CHECKER.equalsIgnoreCase(actionName)) {
						request.removeAttribute("regCompChecker");
						dto.setChecker("");
						dto.setChkChecker("");
						forwardJsp = "redirectToCheckerLink";

					}
				}
				if ("linkingHistory".equalsIgnoreCase(formName)) {

					if ("PIN_REQ_ACTION".equalsIgnoreCase(actionName)) {
						if (regmkrBD.checkPinRequired(eForm.getCommonDTO().getOldRegNum())) {
							dto.setPinRequired("Y");
							/*
							 * if(regmkrBD.checkPinRequiredBuilding(dto.getPropIdSelected
							 * ())) { dto.setPinRequired("N"); } else {
							 * dto.setPinRequired("Y"); }
							 */
						} else {
							dto.setPinRequired("N");
						}
					}

					if (RegCompConstant.LINK_HISTORY_CHECKER.equalsIgnoreCase(actionName)) {
						request.removeAttribute("msg");
						String propId = eForm.getPropId();
						String propIdInit = eForm.getPropIdInit();
						String regNum = dto.getRegNumber();
						String oldregNum = eForm.getCommonDTO().getOldRegNum();
						/*
						 * Status I = linking Initiated Status L = linking
						 * Complete In maker part linking will only be initiated
						 * and will be complete only after completion of
						 * registration
						 */
						String status = "I";
						boolean flag = regmkrBD.getPropLockDetails(oldregNum, propId, dto.getRegNumber(), propIdInit);
						// ArrayList cavList=
						// bd.cavetsCheck(dto.getRegNumber());
						if (flag) {
							eForm.setListSize(0);
							// messages.add("MSG", new ActionMessage(
							// "linking_lock_error"));
							// saveMessages(request, messages);
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("msg", "Selected Property is already Locked.");
							} else {
								request.setAttribute("msg", "चयनित संपत्ति पहले से ही लॉक है|");
							}
							eForm.setLnkFlg("L");
						} else if (bd.checkPropertyCaveats(propId)) {
							eForm.setListSize(0);
							// messages.add("MSG", new ActionMessage(
							// "linking_cavets_error"));
							// saveMessages(request, messages);
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("msg", "Protest is logged on the selected Property.");
							} else {
								request.setAttribute("msg", "चयनित संपत्ति पर आपत्ति लॉग है|");
							}
							eForm.setLnkFlg("L");
						} else {

							// TODO:- chk for pin no inserted
							if (dto.getPinRequired().equalsIgnoreCase("Y")) {
								String pinChk = regmkrBD.getPinDetails(propId, dto.getPinNumber());

								if (pinChk.equalsIgnoreCase(RegCompCheckerConstant.WRONG_PIN_ENTETRED)) {
									// eForm.setListSize(0);
									// messages.add("MSG", new ActionMessage(
									// "link.wrong.pin"));
									// saveMessages(request, messages);
									if ("en".equalsIgnoreCase(language)) {
										request.setAttribute("msg", "You have entered a wrong Pin..");
									} else {
										request.setAttribute("msg", "आपने गलत पिन दर्ज किया है।|");
									}
									eForm.setLnkFlg("L");
								} else if (pinChk.equalsIgnoreCase(RegCompCheckerConstant.PIN_EXPIRED)) {
									// eForm.setListSize(0);
									// messages.add("MSG", new ActionMessage(
									// "link.pin.expired"));
									// saveMessages(request, messages);
									if ("en".equalsIgnoreCase(language)) {
										request.setAttribute("msg", "This property has already been sold");
									} else {
										request.setAttribute("msg", "यह संपत्ति पहले ही बेची जा चुकी है।");
									}
									eForm.setLnkFlg("L");
								} else if (!regmkrBD.checkArea(propIdInit, propId, dto.getTransactionType(), request, language)) {
									/*
									 * if("en".equalsIgnoreCase(language)) {
									 * request.setAttribute("msg",
									 * "This property has already been sold"); }
									 * else {request.setAttribute("msg",
									 * "यह संपत्ति पहले ही बेची जा चुकी है।"); }
									 */
									eForm.setLnkFlg("L");
								} else {
									boolean chkAlrdyLnkd = regmkrBD.chkAlrdyLinked(propId, regNum, propIdInit, oldregNum, status);
									if (chkAlrdyLnkd) {
										boolean updtTble = regmkrBD.updateLinkngTble(propId, regNum, propIdInit, oldregNum, status, UserId, cdate, dto.getTransactionType());
										if (updtTble) {
											eForm.getCommonDTO().setOldRegNum("");
											eForm.setOldPropIdList(new ArrayList());
											eForm.setListSize(0);
											// messages.add("MSG", new
											// ActionMessage
											// ("linking_initiated"));
											// saveMessages(request, messages);
											dto.setLinkingDashboardList(regmkrBD.linkingDashBoardList(regNum, language));

											if ("en".equalsIgnoreCase(language)) {
												request.setAttribute("msg", "Document linking has been initiated successfully.");
											} else {
												request.setAttribute("msg", "दस्तावेज़ों को लिंक करने की प्रक्रिया सफलतापूर्वक शुरू की गई है।");
											}
											eForm.setLnkFlg("I");
											dto.setPinNumber("");
											cdto.setOldRegNo("");
											eForm.setPropId("");
											dto.setTransactionType("");
										}
									} else {
										boolean flg = regmkrBD.linkhistory(propId, regNum, propIdInit, oldregNum, status, UserId, cdate, dto.getTransactionType());
										if (flg) {
											eForm.getCommonDTO().setOldRegNum("");
											eForm.setOldPropIdList(new ArrayList());
											eForm.setListSize(0);
											// messages.add("MSG", new
											// ActionMessage(
											// "linking_initiated"));
											// saveMessages(request, messages);
											dto.setLinkingDashboardList(regmkrBD.linkingDashBoardList(regNum, language));

											if ("en".equalsIgnoreCase(language)) {
												request.setAttribute("msg", "Document linking has been initiated successfully.");
											} else {
												request.setAttribute("msg", "दस्तावेज़ों को लिंक करने की प्रक्रिया सफलतापूर्वक शुरू की गई है।");
											}
											eForm.setLnkFlg("I");
											dto.setPinNumber("");
											cdto.setOldRegNo("");
											eForm.setPropId("");
											dto.setTransactionType("");
										}
									}
								}
							} else {
								if (!regmkrBD.checkArea(propIdInit, propId, dto.getTransactionType(), request, language)) {
									/*
									 * if("en".equalsIgnoreCase(language)) {
									 * request.setAttribute("msg",
									 * "This property has already been sold"); }
									 * else {request.setAttribute("msg",
									 * "यह संपत्ति पहले ही बेची जा चुकी है।"); }
									 */eForm.setLnkFlg("L");
								}

								else {
									boolean chkAlrdyLnkd = regmkrBD.chkAlrdyLinked(propId, regNum, propIdInit, oldregNum, status);
									if (chkAlrdyLnkd) {
										boolean updtTble = regmkrBD.updateLinkngTble(propId, regNum, propIdInit, oldregNum, status, UserId, cdate, dto.getTransactionType());
										if (updtTble) {
											eForm.getCommonDTO().setOldRegNum("");
											eForm.setOldPropIdList(new ArrayList());
											eForm.setListSize(0);
											// messages.add("MSG", new
											// ActionMessage
											// ("linking_initiated"));
											// saveMessages(request, messages);
											dto.setLinkingDashboardList(regmkrBD.linkingDashBoardList(regNum, language));

											if ("en".equalsIgnoreCase(language)) {
												request.setAttribute("msg", "Document linking has been initiated successfully.");
											} else {
												request.setAttribute("msg", "दस्तावेज़ों को लिंक करने की प्रक्रिया सफलतापूर्वक शुरू की गई है।");
											}
											eForm.setLnkFlg("I");
											dto.setPinNumber("");
											cdto.setOldRegNo("");
											eForm.setPropId("");
											dto.setTransactionType("");
										}
									} else {
										boolean flg = regmkrBD.linkhistory(propId, regNum, propIdInit, oldregNum, status, UserId, cdate, dto.getTransactionType());
										if (flg) {
											eForm.getCommonDTO().setOldRegNum("");
											eForm.setOldPropIdList(new ArrayList());
											eForm.setListSize(0);
											// messages.add("MSG", new
											// ActionMessage(
											// "linking_initiated"));
											// saveMessages(request, messages);
											dto.setLinkingDashboardList(regmkrBD.linkingDashBoardList(regNum, language));

											if ("en".equalsIgnoreCase(language)) {
												request.setAttribute("msg", "Document linking has been initiated successfully.");
											} else {
												request.setAttribute("msg", "दस्तावेज़ों को लिंक करने की प्रक्रिया सफलतापूर्वक शुरू की गई है।");
											}
											eForm.setLnkFlg("I");
											dto.setPinNumber("");
											cdto.setOldRegNo("");
											eForm.setPropId("");
											dto.setTransactionType("");
										}
									}

								}
							}
						}
						forwardJsp = "linkingHistory";
					}

					if (RegCompConstant.NEXT_TO_CHECKER.equalsIgnoreCase(actionName)) {
						request.removeAttribute("regCompChecker");
						dto.setChecker("");
						dto.setChkChecker("");
						forwardJsp = "redirectToCheckerPresentation";
					}
				}

				// ******************FOR PARTIAL
				// SAVE*******************************************//
				if (RegCompCheckerConstant.CANCEL_ACTION.equalsIgnoreCase(actionName)) {
					String pageName = dto.getCancelledPage();
					String status = "";
					if (pageName.equalsIgnoreCase(RegCompConstant.REG_INIT_CONFIRMATION)) {
						status = RegCompConstant.REG_INIT_CONFIRMATION_STATUS;
					} else if (pageName.equalsIgnoreCase(RegCompConstant.LINKING_PAYMENT)) {
						status = RegCompConstant.LINKING_PAYMENT_STATUS;
					} else if (pageName.equalsIgnoreCase(RegCompConstant.WITNESS_DETAILS)) {
						status = RegCompConstant.WITNESS_DETAILS_STATUS;
					} else if (pageName.equalsIgnoreCase(RegCompConstant.LINKING_HISTORY)) {
						status = RegCompConstant.LINKING_HISTORY_STATUS;
					} else if (pageName.equalsIgnoreCase(RegCompConstant.OTHER_DEED_DETLS)) {
						status = RegCompConstant.OTHER_DEED_DETLS_STATUS;
					} else if (pageName.equalsIgnoreCase(RegCompCheckerConstant.PRESENTATION)) {
						status = RegCompConstant.PRESENTATION_STATUS;
					} else if (pageName.equalsIgnoreCase(RegCompConstant.COMPLIANCE_LIST)) {
						status = RegCompConstant.COMPLIANCE_LIST_STATUS;
					} else if (pageName.equalsIgnoreCase(RegCompConstant.CHECKLIST)) {
						status = RegCompConstant.CHECKLIST_STATUS;
					} else if (pageName.equalsIgnoreCase(RegCompConstant.LINK_HISTORY_CHK)) {
						status = RegCompConstant.LINK_HISTORY_CHK_STATUS;
					}

					boolean flag = bd.UpdateRegistrationCompletionStatus(dto.getRegNumber(), status, cdate, UserId);
					if (flag) {
						logger.debug("STATUS UPDATED SUCCESSFULLY");
					}
					forwardJsp = RegCompCheckerConstant.USER_LOGIN_CONFIRMATION;

				}

				// ********************************END-PARTIAL
				// SAVE*****************************//

			}
			// END ADDED BY SIMRAN

		} catch (Exception e) {
			// System.out.println(e.getStackTrace());
			logger.error(e.getStackTrace());
			logger.error(e.getStackTrace());

		}
		request.setAttribute("regcompmaker", eForm);
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

	private String removeFile(String fileName) {
		String filePath = "D:/upload/";
		File newFile = new File(filePath + fileName);
		newFile.delete();

		return "";
	}

	// Start:=code for writing common functions
	private void resetFields(RegCompleteMakerDTO dto) {
		dto.setRegNumber("");

	}

	private String uploadFile(FormFile filetobeuploaded, String fileName, String regNum) {

		String filePath = "D:/upload/";
		File folder = new File(filePath);
		if (!folder.exists()) {
			folder.mkdir();
		}
		try {

			File newFile = new File(filePath, fileName);
			if (!newFile.exists()) {
				// logger.info("NEW FILE NAME:-" + newFile);
				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(filetobeuploaded.getFileData());
				fos.close();
			} else {

				String str = fileName.substring(0, fileName.indexOf("."));
				fileName = str + "01" + ".jpg";
				newFile = new File(filePath, fileName);
				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(filetobeuploaded.getFileData());
				fos.close();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	// added by shruti----2 june 2014
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
	// end

	// ///ADDED BY SIMRAN FOR UPLAOD/////////////////////////////////
	private String uploadFile(String regTxnId, byte[] content, String PartyId, String fileUplaodPath, String fileName) {

		// FormFile uploadedFile = regForm.getCertificate();
		PropertiesFileReader proper;
		String FILE_UPLOAD_PATH = "";

		try {
			proper = PropertiesFileReader.getInstance("resources.igrs");
			FILE_UPLOAD_PATH = proper.getValue("igrs_upload_path") + File.separator + "02" + File.separator;
		} catch (Exception e1) {
			logger.debug(e1.getStackTrace());
		}
		String filePath = FILE_UPLOAD_PATH + regTxnId + fileUplaodPath + PartyId + "/";

		// String fileName=RegInitConstant.FILE_NAME_CERTIFICATE;

		/*
		 * String filePath = getServlet().getServletContext().getRealPath("") +
		 * "/temp/";
		 */

		File folder = new File(filePath);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		try {

			File newFile = new File(filePath, fileName);
			if (!newFile.exists()) {
				// logger.info("NEW FILE NAME:-" + newFile);
				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(content);
				fos.close();
			} else {

				// String str = fileName.substring(0, fileName.indexOf("."));
				// fileName = str + "_01" + ".jpg";
				newFile = new File(filePath, fileName);
				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(content);
				fos.close();
			}

		} catch (Exception ex) {

			ex.printStackTrace();
			return null;
		}
		return filePath + fileName;
	}
	// ////////////////////end added by SIMRAN//////////////
}
