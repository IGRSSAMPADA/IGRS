package com.wipro.igrs.bankingestamp.action;
/**
 * ===========================================================================
 * File           :   EStampAction.java
 * Description    :   Represents the Action Class

 * Author         :   Pavani Param
 * Created Date   :   Dec 05, 2007

 * ===========================================================================
 */
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.newgen.burning.BurningImageAndText;
import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentDetails;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.EStamp;
import com.newgen.burning.EStampPartyDetails;
import com.newgen.burning.ODServerDetails;
import com.newgen.burning.ReadPropertiesFile;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.Seals.bd.SealsBD;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.baseaction.constant.Constants;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.db.OTPUtility;
import com.wipro.igrs.deedDraft.constant.DeedDraftConstant;
import com.wipro.igrs.digitalSign.DigtalSignThread;
import com.wipro.igrs.digitalSign.DocumentService;
import com.wipro.igrs.estamping.bd.EstampingBD;
import com.wipro.igrs.estamping.bo.DutyCalculationBO;
import com.wipro.igrs.estamping.bo.EstampBO;
import com.wipro.igrs.estamping.constant.CommonConstant;
import com.wipro.igrs.estamping.dao.EstampDAO;
import com.wipro.igrs.estamping.dto.EstampDetailsDTO;
import com.wipro.igrs.estamping.form.EstampFormBean;
import com.wipro.igrs.util.GUIDGenerator;
public class EStampAction extends BaseAction {
	
	//String FORWARD_JSP = "success";
	private String forwardJsp = CommonConstant.FORWARD_PAGE_HOME;
    
	private Logger logger = 
		(Logger) Logger.getLogger(EStampAction.class);
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws Exception {
    	logger.info("EStampAction -- In action for getting form object--> " );
    	logger.info("EStampAction -- In action for getting form object--> " + form);
    	//DashBoardDTO objDashBoardDTO1=new DashBoardDTO();
    	
    	if (form != null){
    	String language = (String)session.getAttribute("languageLocale");
      String PAY_FORWARD_JSP = null;
      String paymentTxnId=null;
      String estampTxnId;
      String estampCode;
      String deactTxnID;
      ArrayList mstampIndList = null;
      ArrayList retRegInitList = null;
      ArrayList estampTxnList = null;
      ArrayList estampSearchList = null;
      ArrayList mStampResList = null;
      
      
      EstampFormBean estampFormBean  =  (EstampFormBean)form;
      EstampBO objEstampBO = new EstampBO();
      EstampDetailsDTO objEstampDto = estampFormBean.getObjEstampDet();
      String formName = objEstampDto.getFormName();
      
      String userId = (String) session.getAttribute("UserId");
      estampFormBean.getObjEstampDet().setUid(userId);
      estampFormBean.getObjEstampDet().setOwmFlag("");
	  estampFormBean.getObjEstampDet().setOwmFile("");
	  estampFormBean.getObjEstampDet().setAllowPrint("N");
		estampFormBean.getObjEstampDet().setPrintFlag("N");
		estampFormBean.setPrintCheck("");
      IGRSCommon common = new IGRSCommon();
      System.out.println("Before action");
      String actionName = objEstampDto.getActionName();
      String page = (String) request.getParameter("page");
 
      IGRSCommon objCommon = new IGRSCommon();
      PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");
		
      String downloadPath=proper.getValue("igrs_upload_path");
      downloadPath=downloadPath+File.separator+"IGRS";
      String roleId1 = (String)session.getAttribute("role");
	  String funId = (String)session.getAttribute("functionId");
	  //String userId = (String)session.getAttribute("UserId");
        
	  String modName = (String) request.getParameter("modName");
		System.out.println("modName:-" + modName);
		String fnName = (String) request.getParameter("fnName");
		
		System.out.println("fnName:-" + fnName);

		if (modName != null && fnName != null) {
			session.setAttribute("modName", modName);
			session.setAttribute("fnName", fnName);
		}
	  try
        {
        EstampDAO objEstampDAO = new EstampDAO();
        EstampingBD objEstBd = new EstampingBD();
       // EstampDetailsDTO objEstampDto = new EstampDetailsDTO();
        
       // ArrayList instrList = new ArrayList();
        //instrList = objEstBd.getInstrumentDet();
  	  
      //*********** START ****************************JUDICIAL****************************
        
      //Added by Lavi
        
      //for creating dashboard
        
        if (request.getParameter("link") == null) {
        
        if(request.getParameter("modName")!=null){
        	
        	if(request.getParameter("modName").equalsIgnoreCase("E-Stamps")){
        		
        		ArrayList pendingApplicationList = objEstampBO.getPendingApps(session.getAttribute("UserId").toString(),language);
        		logger.info("--------------->"+pendingApplicationList.size());
				if(pendingApplicationList.size()==0)
					estampFormBean.getObjEstampDet().setPendingApps(null);
				else
					estampFormBean.getObjEstampDet().setPendingApps(pendingApplicationList);
				
				request.setAttribute("pendingApplicationList", estampFormBean.getObjEstampDet().getPendingApps());
				forwardJsp  =  "dashboardJudicial";
				estampFormBean.getObjEstampDet().setActionName("");
				objEstampDto.setActionName("");
				actionName = "";
				
			}
        }
       }
        
      //after click on any pending application id from dashboard
        
        if (request.getParameter("hdnApplicationId") != null) {
			
			String[] txnBalBoth = request.getParameter("hdnApplicationId").toString().split("~");
			
			

			estampFormBean.getObjEstampDet().setHidnEstampTxnId(txnBalBoth[0]);
			estampFormBean.getObjEstampDet().setBalanceAmount(txnBalBoth[1]);
			estampFormBean.getObjEstampDet().setHidnUserId(userId);
			estampFormBean.getObjEstampDet().setPrintFlag("N");
			estampFormBean.getObjEstampDet().setAllowPrint("N");//ok
			estampFormBean.getObjEstampDet().setOtp("");
			//objDashBoardDTO1.setFlag("Y");
			// String appStatus[] = new String[5];

			try {
				String TxnId = estampFormBean.getObjEstampDet().getHidnEstampTxnId().toString();
				
				//objEstampDto.setHidnEstampTxnId(TxnId);
				
				//estampFormBean.getObjEstampDet().setMainTxnId(objEstampDto.getHidnEstampTxnId());
				
				estampFormBean.getObjEstampDet().setMainTxnId(TxnId);
				
				String balAmnt = (String)estampFormBean.getObjEstampDet().getBalanceAmount();
				
				logger.debug("======= > "+balAmnt);
				
				
				if (Double.parseDouble(balAmnt)>0){
					
				ArrayList judicialDetails = objEstampBO.getjudDetails(TxnId,language);
				logger.info("--------------->" + judicialDetails.size());
				
				if (judicialDetails.size() == 0)
					estampFormBean.getObjEstampDet().setDetailsTxnID(null);
				else
					estampFormBean.getObjEstampDet().setDetailsTxnID(judicialDetails);

				request.setAttribute("judicialDetails", estampFormBean.getObjEstampDet().getDetailsTxnID());

				ArrayList applicantDetails = objEstampBO.getDetailsOfApplicantJud(TxnId,language);
				logger.info("--------------->"
						+ applicantDetails.size());

				if (applicantDetails.size() == 0)
					estampFormBean.getObjEstampDet().setPartyDetails(null);
				else
					estampFormBean.getObjEstampDet().setPartyDetails(applicantDetails);

				request.setAttribute("applicantDetails", estampFormBean.getObjEstampDet().getPartyDetails());

				for (int i = 0; i < applicantDetails.size(); i++) {
					//DashBoardDTO ddto = (DashBoardDTO) applicantDetails.get(i);

					logger.info("--------------->"+ objEstampDto.getAppStatus());
					logger.info("--------------->" + objEstampDto.getAppType());
					logger.info("--------------->"+ objEstampDto.getPartyType());
					logger.info("--------------->"+ objEstampDto.getAppAuthFirstName());
					logger.info("--------------->"+ objEstampDto.getPartyAuthFirstName());
				}

				ArrayList documentDetails = objEstampBO.getDetailsOfDocumentJud(TxnId);
				logger.info("--------------->"+ documentDetails.size());

				if (documentDetails.size() == 0)
					estampFormBean.getObjEstampDet().setDocDetails(new ArrayList());
				else
					estampFormBean.getObjEstampDet().setDocDetails(documentDetails);

				request.setAttribute("documentDetails", estampFormBean.getObjEstampDet().getDocDetails());
				forwardJsp = CommonConstant.FORWARD_PAGE_SUCCESS;
				}
				else
				{
					/*if(estampFormBean.getObjEstampDet().getMainTxnId() != null)
					{
						logger.info("------>*****"+estampFormBean.getObjEstampDet().getMainTxnId());
					}
					else
					{
						logger.info("------>*****"+objEstampDto.getHidnEstampTxnId());
						String TxnRequestId = objEstampDto.getHidnEstampTxnId();
						estampFormBean.getObjEstampDet().setMainTxnId(TxnRequestId);
					}*/

					DecimalFormat myformatter = new DecimalFormat(
					"############");
					// duty//
					//String duty = bo.getDuty(eForm.getMainTxnId());
					//estampFormBean.getObjEstampDet().setTotal(
							//Double.parseDouble(estampFormBean.getObjEstampDet().getAmount()));
					// date and time//
					String currDate = objEstampBO.getCurrentDate();
					estampFormBean.getObjEstampDet().setCurrentDate(currDate);
					// deeds and instruments and purpose//
					/*ArrayList second = new ArrayList();
					ArrayList comp1 = new ArrayList();
					second = bo.getDeedDtls(estampFormBean.getObjEstampDet().getMainTxnId());
					if (second.size() > 0) {
						for (int i = 0; i < second.size(); i++) {
							comp1.add((ArrayList) second.get(i));
							if (comp1 != null && comp1.size() > 0) {
								for (int k = 0; k < comp1.size(); k++) {
									ArrayList compSub = (ArrayList) comp1
									.get(k);
									String deed = (String) compSub.get(0);
									String inst = (String) compSub.get(1);
									String purpose = (String) compSub
									.get(2);
									logger
									.debug("the deed is.........................."
											+ deed);
									logger
									.debug("the inst is.........................."
											+ inst);
									logger
									.debug("the purpose is.........................."
											+ purpose);
									eForm.getDutyCalculationDTO()
									.setDeedType(deed);
									eForm.getInstDTO().setInstType(inst);
									eForm.setEstampPurpose(purpose);
								}
							}
						}
					}*/
					// names and office details//
					
					String type = objEstampBO.gettype(userId);      //--added by satbir kumar-- 
					
					logger.debug(type);
					
					/*String roleId = objEstampBO.getRole(userId);
					String srrole = "GR1356609170484";
					String drrole = "GR1356671177515";
					String sprole = "GR1358488496205";
					String rurole = "GR1357368369828";
					String spbankrole = "GR1359453019113";*/
					
					//estampFormBean.getObjEstampDet().setRole(roleId);

					if ("2".equalsIgnoreCase(type)) {
						ArrayList userdet = new ArrayList();
						ArrayList comp2 = new ArrayList();
						userdet = objEstampBO.getruName(userId,language);
						if (userdet.size() > 0) {
							for (int i = 0; i < userdet.size(); i++) {
								comp2.add((ArrayList) userdet.get(i));
								if (comp2 != null && comp2.size() > 0) {
									for (int k = 0; k < comp2.size(); k++) {
										ArrayList compSub = (ArrayList) comp2
										.get(k);

										String name = (String) compSub
										.get(0);
										String cid = (String) compSub
										.get(1);
										String sid = (String) compSub
										.get(2);
										String did = (String) compSub
										.get(3);
										String dname = (String) compSub
										.get(4);

										estampFormBean.getObjEstampDet().setUserName(name);
										if (cid.equalsIgnoreCase("1")) {
											if (sid.equalsIgnoreCase("1")) {
												estampFormBean.getObjEstampDet().setIssuedPlace(dname);

											} else {
												if("en".equalsIgnoreCase(language))
												{	
													estampFormBean.getObjEstampDet()
													.setIssuedPlace("Others");
												}
												else
												{
													estampFormBean.getObjEstampDet()
													.setIssuedPlace("अन्य");	
												}
											}

										} else {
											if("en".equalsIgnoreCase(language))
											{	
												estampFormBean.getObjEstampDet()
												.setIssuedPlace("Others");
											}
											else
											{
												estampFormBean.getObjEstampDet()
												.setIssuedPlace("अन्य");	
											}										}

									}
								}
							}
						}

						estampFormBean.getObjEstampDet().setOfficeName("-");
					}

					else if ("3".equalsIgnoreCase(type)) {
						
						String spname = objEstampBO.getspName(userId);
						String licNo= new DutyCalculationBO().getspLicenseNo(userId);
						ArrayList spdtls = new ArrayList();
						ArrayList comp3 = new ArrayList();
						spdtls = objEstampBO.getspDtls(userId,language);
						if (spdtls.size() > 0) {
							for (int i = 0; i < spdtls.size(); i++) {
								comp3.add((ArrayList) spdtls.get(i));
								if (comp3 != null && comp3.size() > 0) {
									for (int k = 0; k < comp3.size(); k++) {
										ArrayList compSub = (ArrayList) comp3
										.get(k);
										
										String ofc = (String) compSub
										.get(0);
										System.out.println(ofc);
										String plc = (String) compSub
										.get(1);
										String tehsil = (String) compSub
										.get(2);
										estampFormBean.getObjEstampDet().setOfficeName(ofc+" "+tehsil+" "+plc);
										estampFormBean.getObjEstampDet().setIssuedPlace(plc);
										estampFormBean.getObjEstampDet().setUserName(spname+"/"+licNo);
									}
								}
							}

						}
					}

					/*else if (roleId.equalsIgnoreCase(spbankrole)) {

						ArrayList spbankdtls = new ArrayList();
						ArrayList comp4 = new ArrayList();
						spbankdtls = objEstampBO.getspbnkDtls(userId);
						if (spbankdtls.size() > 0) {
							for (int i = 0; i < spbankdtls.size(); i++) {
								comp4.add((ArrayList) spbankdtls.get(i));
								if (comp4 != null && comp4.size() > 0) {
									for (int k = 0; k < comp4.size(); k++) {
										ArrayList compSub = (ArrayList) comp4
										.get(k);

										String uname = (String) compSub
										.get(0);
										String ofc = (String) compSub
										.get(1);
										String plc = (String) compSub
										.get(2);
										estampFormBean.getObjEstampDet().setOfficeName(ofc);
										estampFormBean.getObjEstampDet().setIssuedPlace(plc);
										estampFormBean.getObjEstampDet().setUserName(uname);
									}
								}
							}

						}
					}*/
					
					else if("4".equalsIgnoreCase(type)||"7".equalsIgnoreCase(type)||"5".equalsIgnoreCase(type))
					{
						ArrayList spbankdtls = new ArrayList();
						ArrayList comp4 = new ArrayList();
						
						spbankdtls = objEstampBO.getspbnkDtls(userId,language);
						if (spbankdtls.size() > 0) {
							for (int i = 0; i < spbankdtls.size(); i++) {
								comp4.add((ArrayList) spbankdtls.get(i));
								if (comp4 != null && comp4.size() > 0) {
									for (int k = 0; k < comp4.size(); k++) {
										ArrayList compSub = (ArrayList) comp4
										.get(k);

										String uname = (String) compSub
										.get(0);
										String ofc = (String) compSub
										.get(1);
										String plc = (String) compSub
										.get(2);
										String tehsil = (String) compSub
										.get(3);
										estampFormBean.getObjEstampDet().setOfficeName(ofc+" "+tehsil+" "+plc);
										estampFormBean.getObjEstampDet().setIssuedPlace(plc);
										estampFormBean.getObjEstampDet().setUserName(uname);
									}
								}
							}

						}
					}
					
					else if("I".equalsIgnoreCase(type))
					{
						ArrayList iudtls = new ArrayList();
						ArrayList comp4 = new ArrayList();
						String officeId = (String)session.getAttribute("loggedToOffice");
						iudtls = objEstampBO.getiuDtls(userId,officeId,language);
						if (iudtls.size() > 0) {
							for (int i = 0; i < iudtls.size(); i++) {
								comp4.add((ArrayList) iudtls.get(i));
								if (comp4 != null && comp4.size() > 0) {
									for (int k = 0; k < comp4.size(); k++) {
										ArrayList compSub = (ArrayList) comp4
										.get(k);

										String ofc = (String) compSub
										.get(0);
										String plc = (String) compSub
										.get(1);
										String uname = (String) compSub
										.get(2);
										estampFormBean.getObjEstampDet().setOfficeName(ofc);
										estampFormBean.getObjEstampDet().setIssuedPlace(plc);
										estampFormBean.getObjEstampDet().setUserName(uname);
									}
								}
							}

						}
					}

					// party details--applicant//
					ArrayList appdtls = new ArrayList();
					ArrayList comp5 = new ArrayList();
					appdtls = objEstampBO.getAppDtls(estampFormBean.getObjEstampDet().getMainTxnId().toString(),language);
					if (appdtls.size() > 0) {
						for (int i = 0; i < appdtls.size(); i++) {
							comp5.add((ArrayList) appdtls.get(i));
							if (comp5 != null && comp5.size() > 0) {
								for (int k = 0; k < comp5.size(); k++) {
									ArrayList compSub = (ArrayList) comp5
									.get(k);

									String name = (String) compSub.get(0);
									String cntry = (String) compSub.get(1);
									String state = (String) compSub.get(2);
									String district = (String) compSub
									.get(3);
									String addrs = (String) compSub.get(4);
									String fathrNme = (String) compSub
									.get(5);
									String noOfPrsns = (String) compSub
									.get(6);
									String orgName = (String) compSub
									.get(7);
									String disId = (String) compSub.get(8);
									String deedDuratn = (String)compSub.get(9);

									if (orgName != null) {

										estampFormBean.getObjEstampDet().setAppNamedsply(orgName);
										estampFormBean.getObjEstampDet().setAppCountryName(cntry);
										estampFormBean.getObjEstampDet().setAppStateName(state);
										estampFormBean.getObjEstampDet().setAppDistrictName(district);
										estampFormBean.getObjEstampDet().setAppAddress(addrs);
										estampFormBean.getObjEstampDet().setAppAuthPersonName(name);
										estampFormBean.getObjEstampDet().setAppPersons(noOfPrsns);
										estampFormBean.getObjEstampDet().setAppDistrict(disId);
										estampFormBean.getObjEstampDet().setIsAuthapp(1);
										if (deedDuratn!=null)
											estampFormBean.getObjEstampDet().setDeedDuration(deedDuratn);
											else
												estampFormBean.getObjEstampDet().setDeedDuration("-");
									} else {
										estampFormBean.getObjEstampDet().setAppNamedsply(name);
										estampFormBean.getObjEstampDet().setAppCountryName(cntry);
										estampFormBean.getObjEstampDet().setAppStateName(state);
										estampFormBean.getObjEstampDet().setAppDistrictName(district);
										estampFormBean.getObjEstampDet().setAppAddress(addrs);
										estampFormBean.getObjEstampDet().setAppFatherName(fathrNme);
										estampFormBean.getObjEstampDet().setAppPersons(noOfPrsns);
										estampFormBean.getObjEstampDet().setAppDistrict(disId);
										if (deedDuratn!=null)
											estampFormBean.getObjEstampDet().setDeedDuration(deedDuratn);
										else
											estampFormBean.getObjEstampDet().setDeedDuration("-");

									}

								}
							}
						}

					}

					// party details--party//
					ArrayList partydtls = new ArrayList();
					ArrayList comp6 = new ArrayList();
					partydtls = objEstampBO.getPartyDtls(estampFormBean.getObjEstampDet().getMainTxnId().toString(),language);
					if (partydtls.size() > 0) {
						for (int i = 0; i < partydtls.size(); i++) {
							comp6.add((ArrayList) partydtls.get(i));
							if (comp6 != null && comp6.size() > 0) {
								for (int k = 0; k < comp6.size(); k++) {
									ArrayList compSub = (ArrayList) comp6
									.get(k);

									String name = (String) compSub.get(0);
									String cntry = (String) compSub.get(1);
									String state = (String) compSub.get(2);
									String district = (String) compSub
									.get(3);
									String addrs = (String) compSub.get(4);
									String fathrNme = (String) compSub
									.get(5);
									String noOfPrsns = (String) compSub
									.get(6);
									String orgName = (String) compSub
									.get(7);

									if (orgName != null) {
										estampFormBean.getObjEstampDet().setPartyNamedsply(orgName);
										estampFormBean.getObjEstampDet().setPartyCountryName(cntry);
										estampFormBean.getObjEstampDet().setPartyStateName(state);
										estampFormBean.getObjEstampDet()
										.setPartyDistrictName(district);
										estampFormBean.getObjEstampDet().setPartyAddress(addrs);
										estampFormBean.getObjEstampDet().setPartyAuthPersonName(name);
                                                                                estampFormBean.getObjEstampDet().setPartyFatherName(null);
										estampFormBean.getObjEstampDet().setPartyPersons(noOfPrsns);
										estampFormBean.getObjEstampDet().setIsAuthparty(1);
									} else {
										estampFormBean.getObjEstampDet().setPartyNamedsply(name);
										estampFormBean.getObjEstampDet().setPartyCountryName(cntry);
										estampFormBean.getObjEstampDet().setPartyStateName(state);
										estampFormBean.getObjEstampDet()
										.setPartyDistrictName(district);
										estampFormBean.getObjEstampDet().setPartyAddress(addrs);
										estampFormBean.getObjEstampDet().setPartyFatherName(fathrNme);
										estampFormBean.getObjEstampDet().setPartyPersons(noOfPrsns);
									}

									/*
									 * if (orgName.equalsIgnoreCase(null)){
									 * eForm.setPartyNamedsply(name);
									 * eForm.setPartyCountryName(cntry);
									 * eForm.setPartyStateName(state);
									 * eForm.setPartyDistrictName(district);
									 * eForm.setPartyAddress(addrs);
									 * eForm.setPartyFatherName(fathrNme);
									 * eForm.setPartyPersons(noOfPrsns);
									 * }else{
									 * eForm.setPartyNamedsply(orgName);
									 * eForm.setPartyCountryName(cntry);
									 * eForm.setPartyStateName(state);
									 * eForm.setPartyDistrictName(district);
									 * eForm.setPartyAddress(addrs);
									 * eForm.setPartyAuthPersonName(name);
									 * eForm.setPartyPersons(noOfPrsns);
									 * eForm.setIsAuthparty(1);
									 * 
									 * }
									 */

								}
							}
						}

					} else {
						estampFormBean.getObjEstampDet().setPartyNamedsply("-");
						estampFormBean.getObjEstampDet().setPartyCountryName("-");
						estampFormBean.getObjEstampDet().setPartyStateName("-");
						estampFormBean.getObjEstampDet().setPartyDistrictName("-");
						estampFormBean.getObjEstampDet().setPartyAddress("-");
						estampFormBean.getObjEstampDet().setPartyFatherName("-");
						estampFormBean.getObjEstampDet().setPartyPersons("-");

					}
					////////////////////////BY LAVI////////////////////////
					ArrayList judicialDetails = objEstampBO.getjudDetails(TxnId,language);
					
					
					if (judicialDetails.size()>0){
						
						for (int i = 0; i < judicialDetails.size(); i++) {
							
							EstampDetailsDTO objEstampDetailsDTO = (EstampDetailsDTO)judicialDetails.get(i);
									estampFormBean.getObjEstampDet().setCourtDocTypeName(objEstampDetailsDTO.getCourtDocTypeName());
									estampFormBean.getObjEstampDet().setEstampPurps(objEstampDetailsDTO.getEstampPurps());
									estampFormBean.getObjEstampDet().setAmount(objEstampDetailsDTO.getAmount());
									estampFormBean.getObjEstampDet().setCourtType(objEstampDetailsDTO.getCourtType());
									estampFormBean.getObjEstampDet().setCourtName(objEstampDetailsDTO.getCourtName());
									estampFormBean.getObjEstampDet().setDisttName(objEstampDetailsDTO.getDisttName());
									
								
								
						}
						}
					
					// if ecode already exists then only e-stamp certificate generation
					
					ArrayList ecodeExists = new ArrayList();
					ArrayList comp8 = new ArrayList();
					ecodeExists = objEstampBO.getEcodeDtls(estampFormBean.getObjEstampDet().getMainTxnId().toString());
					
					if (ecodeExists.size()>0){
						
					for (int i = 0; i < ecodeExists.size(); i++) {
						comp8.add((ArrayList) ecodeExists.get(i));
						if (comp8 != null && comp8.size() > 0) {
							for (int k = 0; k < comp8.size(); k++) {
								ArrayList compSub1 = (ArrayList) comp8
								.get(k);

								String ecode = (String) compSub1
								.get(0);
								String amt = (String) compSub1
								.get(1);
								String estampType = "";
								if(language.equalsIgnoreCase("en"))
								{
									estampType = (String) compSub1
									.get(2);
								}
								else
								{
									//estampType = (String) compSub1
									//.get(8);
									String Name = "न्यायिक";
									estampType = Name;
								}
								
								String estampDate = (String) compSub1
								.get(3);
								String issuedBy = (String) compSub1
								.get(4);
								String offc = (String) compSub1
								.get(5);
								String place = (String) compSub1
								.get(6);
								String validityDt = (String) compSub1
								.get(7);
								String printCheck = (String) compSub1
								.get(8);
								if(printCheck!=null)
									estampFormBean.setPrintCheck("N");
								estampFormBean.getObjEstampDet().setEcode(ecode);
								estampFormBean.getObjEstampDet().setAmount(amt);
								estampFormBean.getObjEstampDet().setEstampType(estampType);
								/*
								 * SimpleDateFormat sdf1 = new
								 * SimpleDateFormat
								 * ("yyyy-MM-dd- KK:mm:ss");
								 * SimpleDateFormat sdf2 = new
								 * SimpleDateFormat
								 * ("dd/MM/yyyy hh:mm:ss a");
								 * logger
								 * .debug("the date is-----"
								 * +estampDate); Date d1 =
								 * sdf1.parse(estampDate);
								 * String transDate =
								 * sdf2.format(d1);
								 */
								estampFormBean.getObjEstampDet()
								.setCurrentDate(estampDate);
								estampFormBean.getObjEstampDet().setUserName(issuedBy);
								estampFormBean.getObjEstampDet().setOfficeName(offc);
								estampFormBean.getObjEstampDet().setIssuedPlace(place);
								estampFormBean.getObjEstampDet()
								.setEstampValidity(validityDt);
								logger
								.debug("Just before Final page redirection if ecode is already present");
								forwardJsp = "ecodePageForZeroBal";

							}
						}
					}
					}

				
					else {
					// generation of e-code and insertion into the tables.
					try {
						boolean boo = false;
						boo = objEstampBO.inserteCode(estampFormBean, objEstampDto);

						if (boo) {

							ArrayList ecodedtls = new ArrayList();
							ArrayList comp7 = new ArrayList();
							ecodedtls = objEstampBO.getEcodeDtls(estampFormBean.getObjEstampDet().getMainTxnId().toString());
							if (ecodedtls.size() > 0) {
								for (int i = 0; i < ecodedtls.size(); i++) {
									comp7.add((ArrayList) ecodedtls.get(i));
									if (comp7 != null && comp7.size() > 0) {
										for (int k = 0; k < comp7.size(); k++) {
											ArrayList compSub = (ArrayList) comp7
											.get(k);

											String ecode = (String) compSub
											.get(0);
											String amt = (String) compSub
											.get(1);
											String estampType = "";
											if(language.equalsIgnoreCase("en"))
											{
												estampType = (String) compSub
												.get(2);
											}
											else
											{
												String Name = "न्यायिक";
												estampType = Name;
											}
											
											String estampDate = (String) compSub
											.get(3);
											String issuedBy = (String) compSub
											.get(4);
											String offc = (String) compSub
											.get(5);
											String place = (String) compSub
											.get(6);
											String validityDt = (String) compSub
											.get(7);
											String printCheck = (String) compSub
											.get(8);
											if(printCheck!=null)
												estampFormBean.setPrintCheck("N");
											estampFormBean.getObjEstampDet().setEcode(ecode);
											estampFormBean.getObjEstampDet().setAmount(amt);
											estampFormBean.getObjEstampDet().setEstampType(estampType);
											/*
											 * SimpleDateFormat sdf1 = new
											 * SimpleDateFormat
											 * ("yyyy-MM-dd- KK:mm:ss");
											 * SimpleDateFormat sdf2 = new
											 * SimpleDateFormat
											 * ("dd/MM/yyyy hh:mm:ss a");
											 * logger
											 * .debug("the date is-----"
											 * +estampDate); Date d1 =
											 * sdf1.parse(estampDate);
											 * String transDate =
											 * sdf2.format(d1);
											 */
											estampFormBean.getObjEstampDet()
											.setCurrentDate(estampDate);
											estampFormBean.getObjEstampDet().setUserName(issuedBy);
											estampFormBean.getObjEstampDet().setOfficeName(offc);
											estampFormBean.getObjEstampDet().setIssuedPlace(place);
											estampFormBean.getObjEstampDet()
											.setEstampValidity(validityDt);
											logger
											.debug("Just before Final page redirection");
											forwardJsp = "ecodePageForZeroBal";

										}
									}
								}

							}
						} else {
							forwardJsp = "failure";
						}

					} catch (Exception e) {

					}
					}

				
				}
				
				}catch (Exception e) {

				logger.debug(e);
				forwardJsp = CommonConstant.FORWARD_PAGE_FAILURE;
				return mapping.findForward(forwardJsp);

			}

			//forwardJsp = CommonConstant.FORWARD_PAGE_SUCCESS;
			//return mapping.findForward(forwardJsp);
		}
        if ("judicialCourtTypeAction".equalsIgnoreCase(actionName)) {
        	String DistrictCourtId=estampFormBean.getObjEstampDet().getDistt();
    		estampFormBean.getObjEstampDet().setCourtList(objEstampBO.getCourtList(estampFormBean.getObjEstampDet().getCourtType(),DistrictCourtId,language));
    		//manish
    		return mapping.findForward("dutyDisplay");
    	}
        if (CommonConstant.DUTY_PAGE.equals(page)) {			

        	estampFormBean.getObjEstampDet().setAppType("Select");
        	
        	estampFormBean.getObjEstampDet().setPartyType("Select");			

        	estampFormBean.getObjEstampDet().setEstampPurpose("");
        	estampFormBean.getObjEstampDet().setEstampPurps("");
        	estampFormBean.getObjEstampDet().setCourtDocType("Select");
        	estampFormBean.getObjEstampDet().setCourtName("");
        	estampFormBean.getObjEstampDet().setCourtPlace("");
        	estampFormBean.getObjEstampDet().setCourtType("");
        	estampFormBean.getObjEstampDet().setCntry("Select");
        	estampFormBean.getObjEstampDet().setStateCourt("Select");
        	estampFormBean.getObjEstampDet().setDistt("Select");
        	estampFormBean.getObjEstampDet().setCourt("Select");
        	
        	//estampFormBean.getObjEstampDet().setAppDistrict("Select");
        	estampFormBean.getObjEstampDet().setCourtList(new ArrayList());
        	estampFormBean.getObjEstampDet().setAmount("");
        	estampFormBean.getObjEstampDet().setFilePhoto2(null);
        	estampFormBean.getObjEstampDet().setDoc("");
        	estampFormBean.getObjEstampDet().setDocname("");
        	estampFormBean.getObjEstampDet().setIsModify(0);
        	estampFormBean.getObjEstampDet().setErrMsg(0);
        	estampFormBean.getObjEstampDet().setIsInternalUser(0);
        	estampFormBean.getObjEstampDet().setPrintFlag("N");
			estampFormBean.getObjEstampDet().setAllowPrint("N");//ok By Mohit
			estampFormBean.getObjEstampDet().setOtp("");
        	// added by Lavi on 11th October 2013 for scanner integration
			estampFormBean.getObjEstampDet().setDistrictListCourt(objEstampBO.getDistrictCourt(language));
        	String type = objEstampBO.gettype(userId);
        	
        	if("I".equalsIgnoreCase(type))
			{
        		logger.info("Inside if action of Judicial page for internal users: "+type);
        		estampFormBean.getObjEstampDet().setIsInternalUser(1);
        		//downloadPath=downloadPath+File.separator+"IGRS";
        		estampFormBean.getObjEstampDet().setParentPathScan(downloadPath+File.separator+"04"+File.separator);
        		estampFormBean.getObjEstampDet().setFileNameScan("Estamp_Document.pdf");
        		estampFormBean.getObjEstampDet().setGuidUpload(GUIDGenerator.getGUID());
        		
			}
        	
        	// end of code added by Lavi on 11th October 2013 for scanner integration

        	
        	estampFormBean.getObjEstampDet().setCountryList(objEstampBO.getCountry(language));
        	logger.debug("------> get the countrylist"+objEstampDto.getCountryList());
        	
        	estampFormBean.getObjEstampDet().setPhotoIdList(objEstampBO.getPhotoId(language));
        	estampFormBean.getObjEstampDet().setAppellate(objEstampBO.getAppellate(language));
        	estampFormBean.getObjEstampDet().setDocTypeList(objEstampBO.getDocType(language));
        	estampFormBean.getObjEstampDet().setDate(objEstampBO.getDate());
        
        	forwardJsp = "dutyDisplay";
        	
			//forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
			estampFormBean.getObjEstampDet().setActionName("");
			objEstampDto.setActionName("");
			actionName = "";
			// forwardJsp = CommonConstant.FORWARD_PAGE_HOME;
			// forwardJsp = "dutyNext";

		}
        if (CommonConstant.DUTY_VIEW_PAGE.equals(formName)) {
			logger.debug(" hello inside next page action");
			System.out.println("the actionanme is" + actionName);

			if ("appType1".equalsIgnoreCase(actionName)) {

	        	estampFormBean.getObjEstampDet().setAppFirsName("");
	        	estampFormBean.getObjEstampDet().setAppMiddleName("");
	        	estampFormBean.getObjEstampDet().setAppLastName("");
				estampFormBean.getObjEstampDet().setAppAge("");
	        	estampFormBean.getObjEstampDet().setAppDay("");
	        	estampFormBean.getObjEstampDet().setAppMonth("");
	        	estampFormBean.getObjEstampDet().setAppYear("");
	        	estampFormBean.getObjEstampDet().setAppFatherName("");
	        	estampFormBean.getObjEstampDet().setAppMotherName("");
	        	estampFormBean.getObjEstampDet().setAppCountry("");
	        	estampFormBean.getObjEstampDet().setAppState("");
	        	estampFormBean.getObjEstampDet().setAppDistrict("");
	        	estampFormBean.getObjEstampDet().setAppAddress("");
	        	estampFormBean.getObjEstampDet().setAppPostalCode("");
	        	estampFormBean.getObjEstampDet().setAppPhno("");
	        	estampFormBean.getObjEstampDet().setAppMobno("");
	        	estampFormBean.getObjEstampDet().setAppEmailID("");
	        	estampFormBean.getObjEstampDet().setAppPhotoId("");
	        	estampFormBean.getObjEstampDet().setAppPhotoIdno("");
	        	estampFormBean.getObjEstampDet().setAppPersons("");
	        	estampFormBean.getObjEstampDet().setAppOrgName("");
	        	estampFormBean.getObjEstampDet().setAppCountry("Select");
	        	estampFormBean.getObjEstampDet().setAppState("Select");
	        	estampFormBean.getObjEstampDet().setAppDistrict("Select");
	        	estampFormBean.getObjEstampDet().setAppAuthFirstName("");
	        	estampFormBean.getObjEstampDet().setAppAuthMiddleName("");
	        	estampFormBean.getObjEstampDet().setAppAuthLastName("");
	        
	        	estampFormBean.getObjEstampDet().setAppAuthPersonName("");
	        	if("en".equalsIgnoreCase(language)){
	        	estampFormBean.getObjEstampDet().setAppCountryName("INDIA");
	        	estampFormBean.getObjEstampDet().setAppStateName("M.P.");
	        	}
	        	else{
	        		estampFormBean.getObjEstampDet().setAppCountryName("भारत");
		        	estampFormBean.getObjEstampDet().setAppStateName("मध्य प्रदेश");	
	        		
	        	}
	        	estampFormBean.getObjEstampDet().setDistrictList(objEstampBO.getDistrict("1",language));
	        	//estampFormBean.getObjEstampDet().setDistrictListCourt(objEstampBO.getDistrictCourt(language));

				String appname = estampFormBean.getObjEstampDet().getAppTypeName().toString();
				estampFormBean.getObjEstampDet().setAppGender("M");
				String currYear = objEstampBO.getCurrentYear();
				estampFormBean.getObjEstampDet().setCurrentYear(currYear);
				forwardJsp = "dutyDisplay";
				estampFormBean.getObjEstampDet().setActionName("");
				objEstampDto.setActionName("");
				actionName = "";
				//forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
			}
			if ("partyType1".equalsIgnoreCase(actionName)) {
				
	        	estampFormBean.getObjEstampDet().setPartyFirsName("");
	        	estampFormBean.getObjEstampDet().setPartyMiddleName("");
	        	estampFormBean.getObjEstampDet().setPartyLastName("");
				//dto.setPartyAge("");
	        	estampFormBean.getObjEstampDet().setPartyDay("");
	        	estampFormBean.getObjEstampDet().setPartyMonth("");
	        	estampFormBean.getObjEstampDet().setPartyYear("");
	        	estampFormBean.getObjEstampDet().setPartyFatherName("");
	        	estampFormBean.getObjEstampDet().setPartyMotherName("");
	        	estampFormBean.getObjEstampDet().setPartyCountry("");
	        	estampFormBean.getObjEstampDet().setPartyState("");
	        	estampFormBean.getObjEstampDet().setPartyDistrict("");
	        	estampFormBean.getObjEstampDet().setPartyAddress("");
	        	estampFormBean.getObjEstampDet().setPartyPostalCode("");
	        	estampFormBean.getObjEstampDet().setPartyPhno("");
	        	estampFormBean.getObjEstampDet().setPartyMobno("");
	        	estampFormBean.getObjEstampDet().setPartyEmailID("");
	        	estampFormBean.getObjEstampDet().setPartyPhotoId("");
	        	estampFormBean.getObjEstampDet().setPartyPhotoIdno("");
	        	estampFormBean.getObjEstampDet().setPartyPersons("");
	        	estampFormBean.getObjEstampDet().setPartyOrgName("");
	        	estampFormBean.getObjEstampDet().setPartyAuthFirstName("");
	        	estampFormBean.getObjEstampDet().setPartyAuthMiddleName("");
	        	estampFormBean.getObjEstampDet().setPartyAuthLastName("");
	        	estampFormBean.getObjEstampDet().setPartyAuthPersonName("");
				

	        	estampFormBean.getObjEstampDet().setEstampPurpose("");
	        	estampFormBean.getObjEstampDet().setFilePhoto2(null);
	        	estampFormBean.getObjEstampDet().setDoc("");
	        	estampFormBean.getObjEstampDet().setDocname("");
	        	
	        	estampFormBean.getObjEstampDet().setErrMsg(0);
	        	estampFormBean.getObjEstampDet().setPartyAge("");
	        	estampFormBean.getObjEstampDet().setPartyCountry("Select");
	        	estampFormBean.getObjEstampDet().setPartyState("Select");
	        	estampFormBean.getObjEstampDet().setPartyDistrict("Select");
	        	estampFormBean.getObjEstampDet().setPartyCountry("1");
	        	estampFormBean.getObjEstampDet().setPartyState("1");
	        	estampFormBean.getObjEstampDet().setDistrictList(objEstampBO.getDistrict("1",language));
	        	
	         	if("en".equalsIgnoreCase(language)){
	        	
	        	estampFormBean.getObjEstampDet().setPartyCountryName("INDIA");
	        	estampFormBean.getObjEstampDet().setPartyStateName("M.P.");
	         	}
	         	else{
	         		estampFormBean.getObjEstampDet().setPartyCountryName("भारत");
		        	estampFormBean.getObjEstampDet().setPartyStateName("मध्य प्रदेश");
	         		
	         	}
				String partyname = estampFormBean.getObjEstampDet().getPartyTypeName().toString();
				estampFormBean.getObjEstampDet().setPartyGender("M");
				
				String currYear = objEstampBO.getCurrentYear();
				estampFormBean.getObjEstampDet().setCurrentYear(currYear);
				forwardJsp = "dutyDisplay";
				estampFormBean.getObjEstampDet().setActionName("");
				objEstampDto.setActionName("");
				actionName = "";
				//forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
			}

			if (CommonConstant.STATE_LOAD.equals(actionName)) {
				String countryId = estampFormBean.getObjEstampDet().getAppCountry();
				String countryId2 = estampFormBean.getObjEstampDet().getPartyCountry();
				String countryId3 = estampFormBean.getObjEstampDet().getCntry();
				
				if (countryId != null||!countryId.equals(null) || countryId != "")
				{
					estampFormBean.getObjEstampDet().setStateList(objEstampBO.getState(countryId,language));
				} 
				else if (countryId2 != null|| !countryId2.equals(null) || countryId2 != "")
				{
					estampFormBean.getObjEstampDet().setStateList(objEstampBO.getState(countryId2,language));
				}
				else 
				{
					estampFormBean.getObjEstampDet().setStateList(objEstampBO.getState(countryId3,language));
				}
				forwardJsp = "dutyDisplay";
				estampFormBean.getObjEstampDet().setActionName("");
				objEstampDto.setActionName("");
				actionName = "";
				//forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
			}

			if ("districtLoad".equalsIgnoreCase(actionName)) {
				String stateId = estampFormBean.getObjEstampDet().getAppState();
				String stateId2 = estampFormBean.getObjEstampDet().getPartyState();
				String stateId3 = estampFormBean.getObjEstampDet().getStateCourt();
				
				if (stateId != null || !stateId.equals(null)  || stateId != "")
				{
				  estampFormBean.getObjEstampDet().setDistrictList(objEstampBO.getDistrict(stateId,language));
				} 
				else if (stateId2 != null || !stateId2.equals(null) || stateId2 != "")
				{
				  estampFormBean.getObjEstampDet().setDistrictList(objEstampBO.getDistrict(stateId2,language));
				}
				else 
				{
				  estampFormBean.getObjEstampDet().setDistrictList(objEstampBO.getDistrict(stateId3,language));
				}
				forwardJsp = "dutyDisplay";
				estampFormBean.getObjEstampDet().setActionName("");
				objEstampDto.setActionName("");
				actionName = "";
				//forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
			}
			
			logger.debug("ACTION NAME IS - "+actionName);
			if ("documentType".equalsIgnoreCase(actionName))
			{
					logger.debug("inside documentType action try");
					
					ArrayList docTypeValue = new ArrayList();
					
					String documentName = estampFormBean.getObjEstampDet().getCourtDocTypeName().toString();
					
					logger.debug("########" +documentName);
					
					docTypeValue = objEstampBO.getdocTypeValue(documentName,language);
					
					logger.debug("inside documentType action-after getting documentType value");
					//
					if(docTypeValue!=null && docTypeValue.size()>0){
						ArrayList rowList = null;
					
					for (int i=0; i<docTypeValue.size();i++)
					{
						rowList = (ArrayList)docTypeValue.get(i);
						
						if (rowList.get(0)==null || rowList.get(0)== "")
						{
							estampFormBean.getObjEstampDet().setAmount(null);
						}
						else
						{
						estampFormBean.getObjEstampDet().setAmount(rowList.get(0).toString());	
						}
					}
					forwardJsp = "dutyDisplay";
					}
					objEstampDto.setActionName(null);
					actionName = "";
					estampFormBean.getObjEstampDet().setActionName(null);
					estampFormBean.getObjEstampDet().setActionName("");
			}//end of getting values of type of document
			logger.debug("ACTION NAME IS AFTER METHOD DOC TYPE - "+actionName);
			
			// added by Lavi on 11th October 2013 for scanner integration
			if("afterScannerUpload".equalsIgnoreCase(actionName))
			{
				estampFormBean.getObjEstampDet().setDoc(estampFormBean.getObjEstampDet().getFileNameScan());
				estampFormBean.getObjEstampDet().setDocPath(estampFormBean.getObjEstampDet().getParentPathScan()+"/"+estampFormBean.getObjEstampDet().getGuidUpload());
				estampFormBean.getObjEstampDet().setDocPathComplete(estampFormBean.getObjEstampDet().getDocPath()+"/"+estampFormBean.getObjEstampDet().getDoc());
				forwardJsp="dutyDisplay";
				estampFormBean.getObjEstampDet().setActionName("");
				objEstampDto.setActionName("");
				actionName = "";
			}
			// end of code added by Lavi on 11th October 2013 for scanner integration
			/*if("deedIdValidate".equalsIgnoreCase(actionName))
			{
				
				estampFormBean.getObjEstampDet().setActionName("");
				estampFormBean.getObjEstampDet().setDeedDraftErrorFlag("N");
				estampFormBean.getObjEstampDet().setDeedDraftStatus("A");
				estampFormBean.getObjEstampDet().setDeedDraftError("");	
				objEstampBO.validateDeedId(estampFormBean.getObjEstampDet(),language);
				actionName = "";
			}*/
			/*if("deedConsume".equalsIgnoreCase(actionName))
			{
				
				estampFormBean.getObjEstampDet().setActionName("");
				boolean flag=objEstampBO.updateDeedStatus(estampFormBean.getObjEstampDet().getDeedDraftId());
				if(flag==true)
				{
					estampFormBean.getObjEstampDet().setDeedConsumeFlag("Y");
				}
				else
				{
				estampFormBean.getObjEstampDet().setDeedConsumeFlag("N");

				}
				actionName = "";
			}
			if("deedDraft".equalsIgnoreCase(actionName))
			{
				
				estampFormBean.getObjEstampDet().setActionName("");

				DeedDraftBD bd = new DeedDraftBD();
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				//boolean validID=bd.checkID(regForm.getDeedDraftId());
				//if(validID)
				//{
					String path=pr.getValue("pdf.location");
					bd.generateDeedPDF(path,estampFormBean.getObjEstampDet().getDeedDraftId(), response);
				
			
				//}else{
					//formDTO.setValidDeed("Invalid");
				//}
			  		
				actionName = "";
			}*/
			
			// Start : upload coding
			if ("setUploadFile".equalsIgnoreCase(actionName)) {

				try {
				
					ArrayList	errorList = new ArrayList();
					FormFile uploadedFile = estampFormBean.getObjEstampDet().getFilePhoto2();
					
					//added by satbir kumar
					estampFormBean.getObjEstampDet().setImage1(uploadedFile.getFileData());
					
					estampFormBean.getObjEstampDet().setImage(uploadedFile);
					
					//end of addition--
					if ("".equals(uploadedFile.getFileName())) {
						errorList.add("Invalid file selection. Please try again.");
					}
					String fileExt = getFileExtension(uploadedFile.getFileName());
					AuditInspectionRule rule = new AuditInspectionRule();
					rule.validateFileTypeEstamp(fileExt);
					int size = uploadedFile.getFileSize();
					double fileSizeInBytes = size;
					double fileSizeInKB = fileSizeInBytes / 1024.0;
					double fileSizeInMB = fileSizeInKB / 1024.0;
					DecimalFormat decim = new DecimalFormat("#.##");
					Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
					String photoSize = "(" + fileSize + "MB)";
					if (rule.isError()) {
						if("en".equalsIgnoreCase(language))
						{	
						errorList.add("Invalid file type. Please select another file.");
						}
						else
						{
						errorList.add("अमान्य फ़ाइल प्रकार |अन्य फाइल चुनें |");	
						}
						request.setAttribute("errorsList",errorList);
					} else {
						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
							if("en".equalsIgnoreCase(language))
							{	
							errorList.add("File size is Greater than 10 MB. Please select another file.");
							}
							else
							{
							errorList.add("फ़ाइल का आकार 10 MB से अधिक है| अन्य फाइल चुनें|");

							}
							request.setAttribute("errorsList",errorList);
						} else {
							
							String docName = "Estamp_Document."+ fileExt;
							
							estampFormBean.getObjEstampDet().setDoc(docName);
							//estampFormBean.getObjEstampDet().setDocPath(docPath);
							/*dto.setFilePhoto2(uploadedFile);
							eForm.getDoddto().setFilePhoto2(uploadedFile);*/
							//FormFile photo =estampFormBean.getObjEstampDet().getFilePhoto2(); 
							//boolean up=uploadFile(estampFormBean.getObjEstampDet().getFilePhoto2(),docName,docPath);
							forwardJsp="dutyDisplay";
							estampFormBean.getObjEstampDet().setActionName(null);
							} 
						}
					} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// ----------------------remove file coding----------
			if ("removeUploadFile".equalsIgnoreCase(actionName)) {
				try {
					
					//Added By Lavi For Scanner Integration
					if(Integer.toString(estampFormBean.getObjEstampDet().getIsInternalUser()).equalsIgnoreCase("1"))
					{
						String fname = (String)estampFormBean.getObjEstampDet().getDoc();
						String docPath = estampFormBean.getObjEstampDet().getDocPath();
						removeFile(fname, docPath);
						estampFormBean.getObjEstampDet().setDocname("");
						estampFormBean.getObjEstampDet().setDoc("");
						
					}
					//end of code Added By Lavi For Scanner Integration
					
					else
					{

					FormFile fname = (FormFile) estampFormBean.getObjEstampDet().getFilePhoto2();
				//	downloadPath=downloadPath+File.separator+"IGRS";
					String docPath = downloadPath+File.separator+"04"+File.separator+estampFormBean.getObjEstampDet().getMainTxnId()+"_"+"jud"+"_"+fname.getFileName();

					removeFile(fname.getFileName(), docPath);
					estampFormBean.getObjEstampDet().setDocname("");
					estampFormBean.getObjEstampDet().setDoc("");
					
					}

					forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
					objEstampDto.setActionName(null);
					actionName = "";
					estampFormBean.getObjEstampDet().setActionName(null);
					estampFormBean.getObjEstampDet().setActionName("");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// end :upload coding
			

			if ("insertAction".equalsIgnoreCase(actionName)) {
				/*if(estampFormBean.getObjEstampDet().getDoc()==null||estampFormBean.getObjEstampDet().getDoc().toString().equalsIgnoreCase(""))
				{
					ArrayList errorList= new ArrayList();
					if("en".equalsIgnoreCase(language))
					{	
					errorList.add("Please upload the document.");
					}
					else
					{
					errorList.add("कृपया दस्तावेज़ अपलोड करें");

					}
					request.setAttribute("errorsList",errorList);
				}
				else*/
				{
				
				
				logger.debug("inside insert action");
				try {
					estampFormBean.getObjEstampDet().setErrMsg(0);
					logger.debug("amount is----"+estampFormBean.getObjEstampDet().getAmount());
					estampFormBean.getObjEstampDet().setAmount(estampFormBean.getObjEstampDet().getAmount());
					logger.debug("inside insert action try");
					boolean txn = false;
					txn = objEstampBO.insertTxn(estampFormBean,language);
					logger.debug("inside insert action-after insert");
					if (txn) {
						String maintxnid = estampFormBean.getObjEstampDet().getMainTxnId().toString();
						logger
						.debug("the main transaction id after insertion is........"
								+ maintxnid);
						logger.debug("Document name is "+estampFormBean.getObjEstampDet().getDoc());
						estampFormBean.getObjEstampDet().setTotal((Double.parseDouble((String) estampFormBean.getObjEstampDet().getAmount())));
						if(estampFormBean.getObjEstampDet().getCourtType().trim().equals("D")){
							if("en".equalsIgnoreCase(language)){
								estampFormBean.getObjEstampDet().setCourtType("District");
							}else{
								estampFormBean.getObjEstampDet().setCourtType("जिला");
							}
							
						}else{
							if("en".equalsIgnoreCase(language)){
								estampFormBean.getObjEstampDet().setCourtType("Tehsil");
							}else{
								estampFormBean.getObjEstampDet().setCourtType("तहसील");
							}
						}
						
						boolean boo = objEstampBO.insertPay(estampFormBean, objEstampDto);
						String doc = (String)estampFormBean.getObjEstampDet().getDoc();
						String txnTableArray=estampFormBean.getObjEstampDet().getCourt();
						 String array[] = txnTableArray.split("::");
						 estampFormBean.getObjEstampDet().setCourtName(array[1]) ;
						//added by Lavi for scanner integration of internal user
						if(estampFormBean.getObjEstampDet().getDoc().toString().equalsIgnoreCase("")==false)
						{
						if(Integer.toString(estampFormBean.getObjEstampDet().getIsInternalUser()).equalsIgnoreCase("1"))
						{
							//TODO ---							
							boolean inserDocDetls = objEstampBO.insertDocDetls(estampFormBean);
						}
						//end of code added by Lavi for scanner integration of internal user
						else
						{
							//added by satbir kumar for insert upload data
						
						if((estampFormBean.getObjEstampDet().getDoc()!=null)||(!((String) estampFormBean.getObjEstampDet().getDoc()).equalsIgnoreCase("")))
						{
						FormFile photo = estampFormBean.getObjEstampDet().getImage();
						String docname = estampFormBean.getObjEstampDet().getDoc().toString();
						//downloadPath=downloadPath+File.separator+"IGRS";
						String docPath = downloadPath+File.separator+"04"+File.separator+estampFormBean.getObjEstampDet().getMainTxnId().toString();
						estampFormBean.getObjEstampDet().setDocPath(docPath);
						boolean up=uploadFile(photo,docname,docPath);
						boolean inserDocDetls = objEstampBO.insertDocDetls(estampFormBean);
						
						}
							//---end of addition -----
						}
						}
						
						
						
						
					//Added By Lavi for validation
					if (estampFormBean.getObjEstampDet().getAmount().toString().equalsIgnoreCase("0")){
						estampFormBean.getObjEstampDet().setPay(0);
					logger.debug("----------->"+estampFormBean.getObjEstampDet().getPay());
					}
						else {
							estampFormBean.getObjEstampDet().setPay(1);
					logger.debug("----------->"+estampFormBean.getObjEstampDet().getPay());
						}
					//End of validation
					forwardJsp = "viewPage";
						//forwardJsp = CommonConstant.FORWARD_PAGE_VIEW;
					} else {

						estampFormBean.getObjEstampDet().setErrMsg(1);
						forwardJsp = "dutyDisplay";
						//forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
					}

				} catch (Exception e) {
					e.printStackTrace();
					estampFormBean.getObjEstampDet().setErrMsg(1);
					forwardJsp = "dutyDisplay";
					///////////// BY LAVI ///////
					objEstampDto.setActionName(null);
					actionName = "";
					estampFormBean.getObjEstampDet().setActionName(null);
					estampFormBean.getObjEstampDet().setActionName("");
					////////////////////////////
					//forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;

				}// end catch
			}
			}// end insertAction
        
        
        }
        

        if ("detailsViewPage".equalsIgnoreCase(formName)) {
        	
        	if ("modify".equals(actionName)) {
				estampFormBean.getObjEstampDet().setIsModify(1);
				if(estampFormBean.getObjEstampDet().getCourtType().trim().equals("District") || estampFormBean.getObjEstampDet().getCourtType().trim().equals("जिला")){
					estampFormBean.getObjEstampDet().setCourtType("D");
				}else{
					estampFormBean.getObjEstampDet().setCourtType("T");
				}
				forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
				objEstampDto.setActionName(null);
				actionName = "";
				estampFormBean.getObjEstampDet().setActionName(null);
				estampFormBean.getObjEstampDet().setActionName("");

			}
			
			if ("PAY_LATER".equalsIgnoreCase(actionName)){
				
				boolean boo = false;
				
				estampFormBean.getObjEstampDet().setTotal((Double.parseDouble((String) estampFormBean.getObjEstampDet().getAmount())));
				
			//	boo = objEstampBO.insertPay(estampFormBean, objEstampDto);
				
				forwardJsp = CommonConstant.FORWARD_PAGE_AFTR_PAY_LATER;
				objEstampDto.setActionName(null);
				actionName = "";
				estampFormBean.getObjEstampDet().setActionName(null);
				estampFormBean.getObjEstampDet().setActionName("");
			}
			
			
			if ("ESTAMP_CERTIFICATE".equalsIgnoreCase(actionName))
			{
				boolean boo = false;
				estampFormBean.getObjEstampDet().setTotal(Double.parseDouble(estampFormBean.getObjEstampDet().getAmount().toString()));
				boo = objEstampBO.insertPay(estampFormBean, objEstampDto);
				

				DecimalFormat myformatter = new DecimalFormat(
				"############");
				
				// duty//
				/*String duty = bo.getDuty(estampFormBean.getObjEstampDet().getMainTxnId());
				eForm.getDutyCalculationDTO().setTotal(
						Double.parseDouble(duty));
				eForm.getDutyCalculationDTO().setTotalDisplay(
						myformatter.format(Double
								.parseDouble(duty))); */
				// date and time//
				String currDate = objEstampBO.getCurrentDate();
				estampFormBean.getObjEstampDet().setCurrentDate(currDate);
				// deeds and instruments and purpose//
				/*ArrayList second = new ArrayList();
				ArrayList comp1 = new ArrayList();
				second = bo.getDeedDtls(estampFormBean.getObjEstampDet().getMainTxnId());
				if (second.size() > 0) {
					for (int i = 0; i < second.size(); i++) {
						comp1.add((ArrayList) second.get(i));
						if (comp1 != null && comp1.size() > 0) {
							for (int k = 0; k < comp1.size(); k++) {
								ArrayList compSub = (ArrayList) comp1
								.get(k);
								String deed = (String) compSub
								.get(0);
								String inst = (String) compSub
								.get(1);
								String purpose = (String) compSub
								.get(2);
								logger
								.debug("the deed is.........................."
										+ deed);
								logger
								.debug("the inst is.........................."
										+ inst);
								logger
								.debug("the purpose is.........................."
										+ purpose);
								eForm.getDutyCalculationDTO()
								.setDeedType(deed);
								eForm.getInstDTO().setInstType(
										inst);
								eForm.setEstampPurpose(purpose);
							}
						}
					}
				} */
				
				
				// names and office details//
				
				String type = objEstampBO.gettype(userId);      //--added by satbir kumar-- 
				
				logger.debug(type);
				/*String roleId = objEstampBO.getRole(userId);
				String srrole = "GR1356609170484";
				String drrole = "GR1356671177515";
				String sprole = "GR1358488496205";
				String rurole = "GR1357368369828";
				String spbankrole = "GR1359453019113";
				
				estampFormBean.getObjEstampDet().setRole(roleId);*/
				
				if ("2".equalsIgnoreCase(type)) {
					ArrayList userdet = new ArrayList();
					ArrayList comp2 = new ArrayList();
					userdet = objEstampBO.getruName(userId,language);
					if (userdet.size() > 0) {
						for (int i = 0; i < userdet.size(); i++) {
							comp2.add((ArrayList) userdet
									.get(i));
							if (comp2 != null
									&& comp2.size() > 0) {
								for (int k = 0; k < comp2
								.size(); k++) {
									ArrayList compSub = (ArrayList) comp2
									.get(k);

									String name = (String) compSub
									.get(0);
									String cid = (String) compSub
									.get(1);
									String sid = (String) compSub
									.get(2);
									String did = (String) compSub
									.get(3);
									String dname = (String) compSub
									.get(4);

									estampFormBean.getObjEstampDet().setUserName(name);
									if (cid
											.equalsIgnoreCase("1")) {
										if (sid
												.equalsIgnoreCase("1")) {
											estampFormBean.getObjEstampDet()
											.setIssuedPlace(dname);

										} else {
											if("en".equalsIgnoreCase(language))
											{	
												estampFormBean.getObjEstampDet()
												.setIssuedPlace("Others");
											}
											else
											{
												estampFormBean.getObjEstampDet()
												.setIssuedPlace("अन्य");	
											}
										}

									} else {
										if("en".equalsIgnoreCase(language))
										{	
											estampFormBean.getObjEstampDet()
											.setIssuedPlace("Others");
										}
										else
										{
											estampFormBean.getObjEstampDet()
											.setIssuedPlace("अन्य");	
										}
									}

								}
							}
						}
					}

					estampFormBean.getObjEstampDet().setOfficeName("-");
				}

				else if ("3".equalsIgnoreCase(type)) {
					String spname = objEstampBO.getspName(userId);
					String licNo= new DutyCalculationBO().getspLicenseNo(userId);
					ArrayList spdtls = new ArrayList();
					ArrayList comp3 = new ArrayList();
					spdtls = objEstampBO.getspDtls(userId,language);
					if (spdtls.size() > 0) {
						for (int i = 0; i < spdtls.size(); i++) {
							comp3
							.add((ArrayList) spdtls
									.get(i));
							if (comp3 != null
									&& comp3.size() > 0) {
								for (int k = 0; k < comp3
								.size(); k++) {
									ArrayList compSub = (ArrayList) comp3
									.get(k);

									String ofc = (String) compSub
									.get(0);
									String plc = (String) compSub
									.get(1);
									String tehsil = (String) compSub
									.get(2);
									estampFormBean.getObjEstampDet().setOfficeName(ofc+" "+tehsil+" "+plc);
									estampFormBean.getObjEstampDet().setIssuedPlace(plc);
									estampFormBean.getObjEstampDet().setUserName(spname+"/"+licNo);
								}
							}
						}

					}
				}

				else if("4".equalsIgnoreCase(type)||"7".equalsIgnoreCase(type)||"5".equalsIgnoreCase(type))
				{

					ArrayList spbankdtls = new ArrayList();
					ArrayList comp4 = new ArrayList();
					spbankdtls = objEstampBO.getspbnkDtls(userId,language);
					if (spbankdtls.size() > 0) {
						for (int i = 0; i < spbankdtls.size(); i++) {
							comp4.add((ArrayList) spbankdtls
									.get(i));
							if (comp4 != null
									&& comp4.size() > 0) {
								for (int k = 0; k < comp4
								.size(); k++) {
									ArrayList compSub = (ArrayList) comp4
									.get(k);

									String uname = (String) compSub
									.get(0);
									String ofc = (String) compSub
									.get(1);
									String plc = (String) compSub
									.get(2);
									String tehsil = (String) compSub
									.get(3);
									estampFormBean.getObjEstampDet().setOfficeName(ofc+" "+tehsil+" "+plc);
									estampFormBean.getObjEstampDet().setIssuedPlace(plc);
									estampFormBean.getObjEstampDet().setUserName(uname);
								}
							}
						}

					}
				}
				
				
				else if("I".equalsIgnoreCase(type))
				{
					ArrayList iudtls = new ArrayList();
					ArrayList comp4 = new ArrayList();
					String officeId = (String)session.getAttribute("loggedToOffice");
					iudtls = objEstampBO.getiuDtls(userId,officeId,language);
					if (iudtls.size() > 0) {
						for (int i = 0; i < iudtls.size(); i++) {
							comp4.add((ArrayList) iudtls.get(i));
							if (comp4 != null && comp4.size() > 0) {
								for (int k = 0; k < comp4.size(); k++) {
									ArrayList compSub = (ArrayList) comp4
									.get(k);

									String ofc = (String) compSub
									.get(0);
									String plc = (String) compSub
									.get(1);
									String uname = (String) compSub
									.get(2);
									estampFormBean.getObjEstampDet().setOfficeName(ofc);
									estampFormBean.getObjEstampDet().setIssuedPlace(plc);
									estampFormBean.getObjEstampDet().setUserName(uname);
								}
							}
						}

					}
				}

				// party details--applicant//
				ArrayList appdtls = new ArrayList();
				ArrayList comp5 = new ArrayList();
				appdtls = objEstampBO.getAppDtls(estampFormBean.getObjEstampDet().getMainTxnId().toString(),language);
				if (appdtls.size() > 0) {
					for (int i = 0; i < appdtls.size(); i++) {
						comp5.add((ArrayList) appdtls.get(i));
						if (comp5 != null && comp5.size() > 0) {
							for (int k = 0; k < comp5.size(); k++) {
								ArrayList compSub = (ArrayList) comp5
								.get(k);

								String name = (String) compSub
								.get(0);
								String cntry = (String) compSub
								.get(1);
								String state = (String) compSub
								.get(2);
								String district = (String) compSub
								.get(3);
								String addrs = (String) compSub
								.get(4);
								String fathrNme = (String) compSub
								.get(5);
								String noOfPrsns = (String) compSub
								.get(6);
								String orgName = (String) compSub
								.get(7);
								String disId = (String) compSub
								.get(8);

								//if (orgName.equalsIgnoreCase(null)|| orgName.equalsIgnoreCase("")) {
									if (orgName == null|| orgName == "") {
									estampFormBean.getObjEstampDet().setAppNamedsply(name);
									estampFormBean.getObjEstampDet()
									.setAppCountryName(cntry);
									estampFormBean.getObjEstampDet()
									.setAppStateName(state);
									estampFormBean.getObjEstampDet()
									.setAppDistrictName(district);
									estampFormBean.getObjEstampDet().setAppAddress(addrs);
									estampFormBean.getObjEstampDet()
									.setAppFatherName(fathrNme);
									estampFormBean.getObjEstampDet()
									.setAppPersons(noOfPrsns);
									estampFormBean.getObjEstampDet().setAppDistrict(disId);
								} else {
									estampFormBean.getObjEstampDet()
									.setAppNamedsply(orgName);
									estampFormBean.getObjEstampDet()
									.setAppCountryName(cntry);
									estampFormBean.getObjEstampDet()
									.setAppStateName(state);
									estampFormBean.getObjEstampDet()
									.setAppDistrictName(district);
									estampFormBean.getObjEstampDet().setAppAddress(addrs);
									estampFormBean.getObjEstampDet()
									.setAppAuthPersonName(name);
									estampFormBean.getObjEstampDet()
									.setAppPersons(noOfPrsns);
									estampFormBean.getObjEstampDet().setAppDistrict(disId);
									estampFormBean.getObjEstampDet().setIsAuthapp(1);

								}

							}
						}
					}

				}

				// party details--party//
				ArrayList partydtls = new ArrayList();
				ArrayList comp6 = new ArrayList();
				partydtls = objEstampBO.getPartyDtls(estampFormBean.getObjEstampDet()
						.getMainTxnId().toString(),language);
				if (partydtls.size() > 0) {
					for (int i = 0; i < partydtls.size(); i++) {
						comp6.add((ArrayList) partydtls.get(i));
						if (comp6 != null && comp6.size() > 0) {
							for (int k = 0; k < comp6.size(); k++) {
								ArrayList compSub = (ArrayList) comp6
								.get(k);

								String name = (String) compSub
								.get(0);
								String cntry = (String) compSub
								.get(1);
								String state = (String) compSub
								.get(2);
								String district = (String) compSub
								.get(3);
								String addrs = (String) compSub
								.get(4);
								String fathrNme = (String) compSub
								.get(5);
								String noOfPrsns = (String) compSub
								.get(6);
								String orgName = (String) compSub
								.get(7);

								if (orgName == null|| orgName.equalsIgnoreCase("")) {
									estampFormBean.getObjEstampDet()
									.setPartyNamedsply(name);
									estampFormBean.getObjEstampDet()
									.setPartyCountryName(cntry);
									estampFormBean.getObjEstampDet()
									.setPartyStateName(state);
									estampFormBean.getObjEstampDet()
									.setPartyDistrictName(district);
									estampFormBean.getObjEstampDet()
									.setPartyAddress(addrs);
									estampFormBean.getObjEstampDet()
									.setPartyFatherName(fathrNme);
									estampFormBean.getObjEstampDet()
									.setPartyPersons(noOfPrsns);
								} else {
									estampFormBean.getObjEstampDet()
									.setPartyNamedsply(orgName);
									estampFormBean.getObjEstampDet()
									.setPartyCountryName(cntry);
									estampFormBean.getObjEstampDet()
									.setPartyStateName(state);
									estampFormBean.getObjEstampDet()
									.setPartyDistrictName(district);
									estampFormBean.getObjEstampDet()
									.setPartyAddress(addrs);
									estampFormBean.getObjEstampDet()
									.setPartyAuthPersonName(name);
									estampFormBean.getObjEstampDet()
									.setPartyPersons(noOfPrsns);
									estampFormBean.getObjEstampDet().setIsAuthparty(1);

								}

							}
						}
					}

				} else {
					estampFormBean.getObjEstampDet().setPartyNamedsply("-");
					estampFormBean.getObjEstampDet().setPartyCountryName("-");
					estampFormBean.getObjEstampDet().setPartyStateName("-");
					estampFormBean.getObjEstampDet().setPartyDistrictName("-");
					estampFormBean.getObjEstampDet().setPartyAddress("-");
					estampFormBean.getObjEstampDet().setPartyFatherName("-");
					estampFormBean.getObjEstampDet().setPartyPersons("-");

				}
				// generation of e-code and insertion into the
				// tables.
				try {
					boolean boo1 = false;
					boo1 = objEstampBO.inserteCode(estampFormBean, objEstampDto);

					if (boo1) {

						ArrayList ecodedtls = new ArrayList();
						ArrayList comp7 = new ArrayList();
						ecodedtls = objEstampBO.getEcodeDtls(estampFormBean.getObjEstampDet()
								.getMainTxnId().toString());
						if (ecodedtls.size() > 0) {
							for (int i = 0; i < ecodedtls
							.size(); i++) {
								comp7.add((ArrayList) ecodedtls
										.get(i));
								if (comp7 != null
										&& comp7.size() > 0) {
									for (int k = 0; k < comp7
									.size(); k++) {
										ArrayList compSub = (ArrayList) comp7
										.get(k);

										String ecode = (String) compSub
										.get(0);
										String amt = (String) compSub
										.get(1);
										String estampType = "";
										if(language.equalsIgnoreCase("en"))
										{
											estampType = (String) compSub
											.get(2);
										}
										else
										{
											//estampType = (String) compSub
											//.get(8);
											String Name = "न्यायिक";
											estampType = Name;
										}
										
										String estampDate = (String) compSub
										.get(3);
										String issuedBy = (String) compSub
										.get(4);
										String offc = (String) compSub
										.get(5);
										String place = (String) compSub
										.get(6);
										String validityDt = (String) compSub
										.get(7);
										estampFormBean.getObjEstampDet().setEcode(ecode);
										estampFormBean.getObjEstampDet().setAmount(amt);
										estampFormBean.getObjEstampDet()
										.setEstampType(estampType);
										estampFormBean.getObjEstampDet()
										.setCurrentDate(estampDate);
										estampFormBean.getObjEstampDet()
										.setUserName(issuedBy);
										estampFormBean.getObjEstampDet()
										.setOfficeName(offc);
										estampFormBean.getObjEstampDet()
										.setIssuedPlace(place);
										estampFormBean.getObjEstampDet()
										.setEstampValidity(validityDt);

										forwardJsp = "ecodePage";

									}
								}
							}

						}
					} else {
						forwardJsp = "failure";
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				objEstampDto.setActionName(null);
				actionName = "";
				estampFormBean.getObjEstampDet().setActionName(null);
				estampFormBean.getObjEstampDet().setActionName("");
			}
        	
        if ("NEXT_TO_PAYMENT_ACTION".equals(actionName)) {

			ArrayList first = new ArrayList();
			first = objEstampBO.getPayDtls(estampFormBean.getObjEstampDet().getMainTxnId().toString());
			
			first= first==null?new ArrayList():first;
			
			if (first.size() == 0) {
				logger.debug("inside if");
				//String duty = objEstampBO.getDuty(estampFormBean.getObjEstampDet().getMainTxnId());
				estampFormBean.getObjEstampDet().setTotal(
						Double.parseDouble(estampFormBean.getObjEstampDet().getAmount().toString())); 
				boolean insrt = false;
				try {
					logger.debug("NEXT_TO_PAYMENT_ACTION :: if block ");
					insrt = objEstampBO.insertPay(estampFormBean, objEstampDto);

					if (insrt) {
						
						String type = objEstampBO.gettype(userId);      //--added by satbir kumar-- 
						
						logger.debug(type);
						
						if("3".equalsIgnoreCase(type)||"4".equalsIgnoreCase(type)||"7".equalsIgnoreCase(type)||"5".equalsIgnoreCase(type))
						{
							ArrayList details = objEstampBO.getDetails(userId,objEstampDto,language);
							
							if(details!=null)
							{
								logger.debug(estampFormBean.getObjEstampDet()
										.getDistrictid());
								logger.debug(estampFormBean.getObjEstampDet()
										.getDistrictname());
								
								request.setAttribute("parentDistrictId", estampFormBean.getObjEstampDet()
										.getDistrictid());
								
								request.setAttribute("parentDistrictName", estampFormBean.getObjEstampDet()
										.getDistrictname());
								request.setAttribute("parentOfficeId", "NA");
								request.setAttribute("parentOfficeName", "NA");
								request.setAttribute("parentReferenceId", estampFormBean.getObjEstampDet().getMainTxnId().toString());
							}
						}
						
						if("2".equalsIgnoreCase(type))
						{
							String txnId=estampFormBean.getObjEstampDet().getMainTxnId().toString();
							ArrayList details = objEstampBO.getruDetails(userId,objEstampDto,txnId,language);
							
							if(details!=null)
							{
								request.setAttribute("parentDistrictId", estampFormBean.getObjEstampDet()
										.getDistrictid());
								
								request.setAttribute("parentDistrictName", estampFormBean.getObjEstampDet()
										.getDistrictname());
								request.setAttribute("parentOfficeId", "NA");
								request.setAttribute("parentOfficeName", "NA");
								request.setAttribute("parentReferenceId", estampFormBean.getObjEstampDet().getMainTxnId().toString());
							}
						}
							
						
							if("I".equalsIgnoreCase(type))
							{
								String offcId = (String) session.getAttribute("loggedToOffice");
								ArrayList iudetails = objEstampBO.getiuDetails(objEstampDto,offcId,language);
								
								if(iudetails!=null)
								{
									request.setAttribute("parentDistrictId", estampFormBean.getObjEstampDet()
											.getDistrictid());
									
									request.setAttribute("parentDistrictName", estampFormBean.getObjEstampDet()
											.getDistrictname());
									request.setAttribute("parentOfficeId", estampFormBean.getObjEstampDet().getIuofficeid() );
									request.setAttribute("parentOfficeName", estampFormBean.getObjEstampDet().getIuofficename());
									request.setAttribute("parentReferenceId", estampFormBean.getObjEstampDet().getMainTxnId().toString());
								}
						
						
						}
						logger.debug("inside if insrt");
						DecimalFormat myformatter = new DecimalFormat(
						"############");
						request.setAttribute("parentModName",
						"E-Stamping");
						request.setAttribute("parentFunName",
						"E-Stamp Judicial");
						request.setAttribute("parentFunId", "FUN_022");
						request.setAttribute("parentAmount",
								myformatter.format(estampFormBean.getObjEstampDet().getTotal()));
						request.setAttribute("parentTable",
						"IGRS_ESTAMP_PAYMENT_DETLS");
						request.setAttribute("parentKey", estampFormBean.getObjEstampDet()
								.getUniqKey());
						request.setAttribute("forwardPath",
						"./estampJudicialNew.do?TRFS=NGI");
						request.setAttribute("parentColumnName",
						"ESTAMP_PAYMENT_ID");
						request.setAttribute("formName","estampFormBean");	
						
						logger.debug("just before redirection");
						forwardJsp = "nextToPay";

					} else {
						forwardJsp = "failure";
					}
				}
				catch (Exception e) {
					e.printStackTrace();
					forwardJsp = "failure";
				}

			} else {

				logger.debug("inside else---means record is found");
				ArrayList comp = new ArrayList();
				String uniqId = "";
				String amtToBePaid = "";
				String paidAmt = "";
				String pymtFlg = "";

				for (int i = 0; i < first.size(); i++) {
					comp.add((ArrayList) first.get(i));
					if (comp != null && comp.size() > 0) {
						for (int k = 0; k < comp.size(); k++) {
							ArrayList compSub = (ArrayList) comp.get(k);
							uniqId = (String) compSub.get(0);
							amtToBePaid = (String) compSub.get(1);
							paidAmt = (String) compSub.get(2);
							pymtFlg = (String) compSub.get(3);
							logger
							.debug("unique id is ............................"
									+ uniqId);
							logger
							.debug("unpayable amt is ............................"
									+ amtToBePaid);
							logger
							.debug("paid amt is..................................."
									+ paidAmt);
							logger
							.debug("pymtFlg is....................................."
									+ pymtFlg);
						}
					}
				}
				double damtToBePaid = Double.parseDouble(amtToBePaid);

				if (pymtFlg.equalsIgnoreCase("I")) {
					
					String type = objEstampBO.gettype(userId);      //--added by satbir kumar-- 
					
					logger.debug(type);
					
					if("3".equalsIgnoreCase(type)||"4".equalsIgnoreCase(type)||"7".equalsIgnoreCase(type)||"5".equalsIgnoreCase(type))
					{
						ArrayList details = objEstampBO.getDetails(userId,objEstampDto,language);
						
						if(details!=null)
						{
							logger.debug(estampFormBean.getObjEstampDet()
									.getDistrictid());
							logger.debug(estampFormBean.getObjEstampDet()
									.getDistrictname());
							
							request.setAttribute("parentDistrictId", estampFormBean.getObjEstampDet()
									.getDistrictid());
							
							request.setAttribute("parentDistrictName", estampFormBean.getObjEstampDet()
									.getDistrictname());
							request.setAttribute("parentOfficeId", "NA");
							request.setAttribute("parentOfficeName", "NA");
							request.setAttribute("parentReferenceId", estampFormBean.getObjEstampDet().getMainTxnId().toString());
						}
					}
					
					if("2".equalsIgnoreCase(type))
					{
						String txnId=estampFormBean.getObjEstampDet().getMainTxnId().toString();
						ArrayList details = objEstampBO.getruDetails(userId,objEstampDto,txnId,language);
						
						if(details!=null)
						{
							request.setAttribute("parentDistrictId", estampFormBean.getObjEstampDet()
									.getDistrictid());
							
							request.setAttribute("parentDistrictName", estampFormBean.getObjEstampDet()
									.getDistrictname());
							request.setAttribute("parentOfficeId", "NA");
							request.setAttribute("parentOfficeName", "NA");
							request.setAttribute("parentReferenceId", estampFormBean.getObjEstampDet().getMainTxnId().toString());
						}
					}
						
					
						if("I".equalsIgnoreCase(type))
						{
							String offcId = (String) session.getAttribute("loggedToOffice");
							ArrayList iudetails = objEstampBO.getiuDetails(objEstampDto,offcId,language);
							
							if(iudetails!=null)
							{
								request.setAttribute("parentDistrictId", estampFormBean.getObjEstampDet()
										.getDistrictid());
								
								request.setAttribute("parentDistrictName", estampFormBean.getObjEstampDet()
										.getDistrictname());
								request.setAttribute("parentOfficeId", estampFormBean.getObjEstampDet().getIuofficeid() );
								request.setAttribute("parentOfficeName", estampFormBean.getObjEstampDet().getIuofficename());
								request.setAttribute("parentReferenceId", estampFormBean.getObjEstampDet().getMainTxnId().toString());
							}
					
					
					}
					
					

					DecimalFormat myformatter = new DecimalFormat(
					"############");
					request.setAttribute("parentModName", "E-Stamping");
					request.setAttribute("parentFunName",
					"E-Stamp Judicial");
					request.setAttribute("parentFunId", "FUN_022");
					request.setAttribute("parentAmount", myformatter
							.format(damtToBePaid));
					request.setAttribute("parentTable",
					"IGRS_ESTAMP_PAYMENT_DETLS");
					request.setAttribute("parentKey", uniqId);
					request.setAttribute("forwardPath",
					"./estampJudicialNew.do?TRFS=NGI");
					request.setAttribute("parentColumnName",
					"ESTAMP_PAYMENT_ID");
					request.setAttribute("formName","estampFormBean");
					forwardJsp = "nextToPay";
				}

				if (pymtFlg.equalsIgnoreCase("P")) {

					double dpaidAmt = Double.parseDouble(paidAmt);
					// condition 1-------------------pending
					// amount-------------
					if (damtToBePaid > dpaidAmt) {
						double bal = damtToBePaid - dpaidAmt;
						boolean insrt = false;
						estampFormBean.getObjEstampDet().setTotal(bal);
						try {
							
							logger.debug("NEXT_TO_PAYMENT_ACTION :: else block :: pymtFlg= P ");
							insrt = objEstampBO.insertPay(estampFormBean, objEstampDto);

							if (insrt) {
								logger
								.debug("inside if insrt-----of else condition---means record found");
								
								String type = objEstampBO.gettype(userId);      //--added by satbir kumar-- 
								
								logger.debug(type);
								
								if("3".equalsIgnoreCase(type)||"4".equalsIgnoreCase(type)||"7".equalsIgnoreCase(type)||"5".equalsIgnoreCase(type))
								{
									ArrayList details = objEstampBO.getDetails(userId,objEstampDto,language);
									
									if(details!=null)
									{
										logger.debug(estampFormBean.getObjEstampDet()
												.getDistrictid());
										logger.debug(estampFormBean.getObjEstampDet()
												.getDistrictname());
										
										request.setAttribute("parentDistrictId", estampFormBean.getObjEstampDet()
												.getDistrictid());
										
										request.setAttribute("parentDistrictName", estampFormBean.getObjEstampDet()
												.getDistrictname());
										request.setAttribute("parentOfficeId", "NA");
										request.setAttribute("parentOfficeName", "NA");
										request.setAttribute("parentReferenceId", estampFormBean.getObjEstampDet().getMainTxnId().toString());
									}
								}
								
								if("2".equalsIgnoreCase(type))
								{
									String txnId=estampFormBean.getObjEstampDet().getMainTxnId().toString();
									ArrayList details = objEstampBO.getruDetails(userId,objEstampDto,txnId,language);
									
									if(details!=null)
									{
										request.setAttribute("parentDistrictId", estampFormBean.getObjEstampDet()
												.getDistrictid());
										
										request.setAttribute("parentDistrictName", estampFormBean.getObjEstampDet()
												.getDistrictname());
										request.setAttribute("parentOfficeId", "NA");
										request.setAttribute("parentOfficeName", "NA");
										request.setAttribute("parentReferenceId", estampFormBean.getObjEstampDet().getMainTxnId().toString());
									}
								}
									
								
									if("I".equalsIgnoreCase(type))
									{
										String offcId = (String) session.getAttribute("loggedToOffice");
										ArrayList iudetails = objEstampBO.getiuDetails(objEstampDto,offcId,language);
										
										if(iudetails!=null)
										{
											request.setAttribute("parentDistrictId", estampFormBean.getObjEstampDet()
													.getDistrictid());
											
											request.setAttribute("parentDistrictName", estampFormBean.getObjEstampDet()
													.getDistrictname());
											request.setAttribute("parentOfficeId", estampFormBean.getObjEstampDet().getIuofficeid() );
											request.setAttribute("parentOfficeName", estampFormBean.getObjEstampDet().getIuofficename());
											request.setAttribute("parentReferenceId", estampFormBean.getObjEstampDet().getMainTxnId().toString());
										}
								
								
								}
								
								DecimalFormat myformatter = new DecimalFormat(
								"############");
								request.setAttribute("parentModName",
								"E-Stamping");
								request.setAttribute("parentFunName",
								"E-Stamp Judicial");
								request.setAttribute("parentFunId",
								"FUN_022");
								request
								.setAttribute(
										"parentAmount",
										myformatter
										.format(estampFormBean.getObjEstampDet().getTotal()));
								request.setAttribute("parentTable",
								"IGRS_ESTAMP_PAYMENT_DETLS");
								request.setAttribute("parentKey", estampFormBean.getObjEstampDet()
										.getUniqKey());
								request.setAttribute("forwardPath",
								"./estampJudicialNew.do?TRFS=NGI");
								request.setAttribute(
										"parentColumnName",
								"ESTAMP_PAYMENT_ID");
								request.setAttribute("formName","estampFormBean");
								logger.debug("just before redirection");
								forwardJsp = "nextToPay";

							} else {
								forwardJsp = "failure";
							}
						} catch (Exception e) {
							forwardJsp = "failure";
						}

					}

					// condition 2-------------------full payment, eCode
					// generation------
					if (damtToBePaid == dpaidAmt) {
						DecimalFormat myformatter = new DecimalFormat(
						"############");
						double bal = damtToBePaid - dpaidAmt;
						// duty//
						
						estampFormBean.getObjEstampDet().setTotal(
								Double.parseDouble(estampFormBean.getObjEstampDet().getAmount().toString())); 
						
						/*String duty = bo.getDuty(estampFormBean.getObjEstampDet().getMainTxnId());
						
						eForm.getDutyCalculationDTO().setTotal(
								Double.parseDouble(duty));
						eForm.getDutyCalculationDTO().setTotalDisplay(
								myformatter.format(Double
										.parseDouble(duty))); */
						// date and time//
						String currDate = objEstampBO.getCurrentDate();
						estampFormBean.getObjEstampDet().setCurrentDate(currDate);
						// deeds and instruments and purpose//
						/*ArrayList second = new ArrayList();
						ArrayList comp1 = new ArrayList();
						second = bo.getDeedDtls(estampFormBean.getObjEstampDet().getMainTxnId());
						if (second.size() > 0) {
							for (int i = 0; i < second.size(); i++) {
								comp1.add((ArrayList) second.get(i));
								if (comp1 != null && comp1.size() > 0) {
									for (int k = 0; k < comp1.size(); k++) {
										ArrayList compSub = (ArrayList) comp1
										.get(k);
										String deed = (String) compSub
										.get(0);
										String inst = (String) compSub
										.get(1);
										String purpose = (String) compSub
										.get(2);
										logger
										.debug("the deed is.........................."
												+ deed);
										logger
										.debug("the inst is.........................."
												+ inst);
										logger
										.debug("the purpose is.........................."
												+ purpose);
										eForm.getDutyCalculationDTO()
										.setDeedType(deed);
										eForm.getInstDTO().setInstType(
												inst);
										eForm.setEstampPurpose(purpose);
									}
								}
							}
						} */
						
						
						// names and office details//
						
						String type = objEstampBO.gettype(userId);      //--added by satbir kumar-- 
						
						logger.debug(type);
						/*String roleId = objEstampBO.getRole(userId);
						String srrole = "GR1356609170484";
						String drrole = "GR1356671177515";
						String sprole = "GR1358488496205";
						String rurole = "GR1357368369828";
						String spbankrole = "GR1359453019113";
						
						estampFormBean.getObjEstampDet().setRole(roleId);*/
						
						if ("2".equalsIgnoreCase(type)) {
							ArrayList userdet = new ArrayList();
							ArrayList comp2 = new ArrayList();
							userdet = objEstampBO.getruName(userId,language);
							if (userdet.size() > 0) {
								for (int i = 0; i < userdet.size(); i++) {
									comp2.add((ArrayList) userdet
											.get(i));
									if (comp2 != null
											&& comp2.size() > 0) {
										for (int k = 0; k < comp2
										.size(); k++) {
											ArrayList compSub = (ArrayList) comp2
											.get(k);

											String name = (String) compSub
											.get(0);
											String cid = (String) compSub
											.get(1);
											String sid = (String) compSub
											.get(2);
											String did = (String) compSub
											.get(3);
											String dname = (String) compSub
											.get(4);

											estampFormBean.getObjEstampDet().setUserName(name);
											if (cid
													.equalsIgnoreCase("1")) {
												if (sid
														.equalsIgnoreCase("1")) {
													estampFormBean.getObjEstampDet()
													.setIssuedPlace(dname);

												} else {
													if("en".equalsIgnoreCase(language))
													{	
														estampFormBean.getObjEstampDet()
														.setIssuedPlace("Others");
													}
													else
													{
														estampFormBean.getObjEstampDet()
														.setIssuedPlace("अन्य");	
													}
												}

											} else {
												if("en".equalsIgnoreCase(language))
												{	
													estampFormBean.getObjEstampDet()
													.setIssuedPlace("Others");
												}
												else
												{
													estampFormBean.getObjEstampDet()
													.setIssuedPlace("अन्य");	
												}
											}

										}
									}
								}
							}

							estampFormBean.getObjEstampDet().setOfficeName("-");
						}

						else if ("3".equalsIgnoreCase(type)) {
							String spname = objEstampBO.getspName(userId);
							String licNo= new DutyCalculationBO().getspLicenseNo(userId);
							ArrayList spdtls = new ArrayList();
							ArrayList comp3 = new ArrayList();
							spdtls = objEstampBO.getspDtls(userId,language);
							if (spdtls.size() > 0) {
								for (int i = 0; i < spdtls.size(); i++) {
									comp3
									.add((ArrayList) spdtls
											.get(i));
									if (comp3 != null
											&& comp3.size() > 0) {
										for (int k = 0; k < comp3
										.size(); k++) {
											ArrayList compSub = (ArrayList) comp3
											.get(k);

											String ofc = (String) compSub
											.get(0);
											String plc = (String) compSub
											.get(1);
											String tehsil = (String) compSub
											.get(2);
											estampFormBean.getObjEstampDet().setOfficeName(ofc+" "+tehsil+" "+plc);
											estampFormBean.getObjEstampDet().setIssuedPlace(plc);
											estampFormBean.getObjEstampDet().setUserName(spname+"/"+licNo);
										}
									}
								}

							}
						}

						else if("4".equalsIgnoreCase(type)||"7".equalsIgnoreCase(type)||"5".equalsIgnoreCase(type))
						{

							ArrayList spbankdtls = new ArrayList();
							ArrayList comp4 = new ArrayList();
							spbankdtls = objEstampBO.getspbnkDtls(userId,language);
							if (spbankdtls.size() > 0) {
								for (int i = 0; i < spbankdtls.size(); i++) {
									comp4.add((ArrayList) spbankdtls
											.get(i));
									if (comp4 != null
											&& comp4.size() > 0) {
										for (int k = 0; k < comp4
										.size(); k++) {
											ArrayList compSub = (ArrayList) comp4
											.get(k);

											String uname = (String) compSub
											.get(0);
											String ofc = (String) compSub
											.get(1);
											String plc = (String) compSub
											.get(2);
											String tehsil = (String) compSub
											.get(3);
											estampFormBean.getObjEstampDet().setOfficeName(ofc+" "+tehsil+" "+plc);
											estampFormBean.getObjEstampDet().setIssuedPlace(plc);
											estampFormBean.getObjEstampDet().setUserName(uname);
										}
									}
								}

							}
						}

						else if("I".equalsIgnoreCase(type))
						{
							ArrayList iudtls = new ArrayList();
							ArrayList comp4 = new ArrayList();
							String officeId = (String)session.getAttribute("loggedToOffice");
							iudtls = objEstampBO.getiuDtls(userId,officeId,language);
							if (iudtls.size() > 0) {
								for (int i = 0; i < iudtls.size(); i++) {
									comp4.add((ArrayList) iudtls.get(i));
									if (comp4 != null && comp4.size() > 0) {
										for (int k = 0; k < comp4.size(); k++) {
											ArrayList compSub = (ArrayList) comp4
											.get(k);

											String ofc = (String) compSub
											.get(0);
											String plc = (String) compSub
											.get(1);
											String uname = (String) compSub
											.get(2);
											estampFormBean.getObjEstampDet().setOfficeName(ofc);
											estampFormBean.getObjEstampDet().setIssuedPlace(plc);
											estampFormBean.getObjEstampDet().setUserName(uname);
										}
									}
								}

							}
						}
						// party details--applicant//
						ArrayList appdtls = new ArrayList();
						ArrayList comp5 = new ArrayList();
						appdtls = objEstampBO.getAppDtls(estampFormBean.getObjEstampDet().getMainTxnId().toString(),language);
						if (appdtls.size() > 0) {
							for (int i = 0; i < appdtls.size(); i++) {
								comp5.add((ArrayList) appdtls.get(i));
								if (comp5 != null && comp5.size() > 0) {
									for (int k = 0; k < comp5.size(); k++) {
										ArrayList compSub = (ArrayList) comp5
										.get(k);

										String name = (String) compSub
										.get(0);
										String cntry = (String) compSub
										.get(1);
										String state = (String) compSub
										.get(2);
										String district = (String) compSub
										.get(3);
										String addrs = (String) compSub
										.get(4);
										String fathrNme = (String) compSub
										.get(5);
										String noOfPrsns = (String) compSub
										.get(6);
										String orgName = (String) compSub
										.get(7);
										String disId = (String) compSub
										.get(8);

										if (orgName == null || orgName.equalsIgnoreCase("")) {
											estampFormBean.getObjEstampDet().setAppNamedsply(name);
											estampFormBean.getObjEstampDet()
											.setAppCountryName(cntry);
											estampFormBean.getObjEstampDet()
											.setAppStateName(state);
											estampFormBean.getObjEstampDet()
											.setAppDistrictName(district);
											estampFormBean.getObjEstampDet().setAppAddress(addrs);
											estampFormBean.getObjEstampDet()
											.setAppFatherName(fathrNme);
											estampFormBean.getObjEstampDet()
											.setAppPersons(noOfPrsns);
											estampFormBean.getObjEstampDet().setAppDistrict(disId);
										} else {
											estampFormBean.getObjEstampDet()
											.setAppNamedsply(orgName);
											estampFormBean.getObjEstampDet()
											.setAppCountryName(cntry);
											estampFormBean.getObjEstampDet()
											.setAppStateName(state);
											estampFormBean.getObjEstampDet()
											.setAppDistrictName(district);
											estampFormBean.getObjEstampDet().setAppAddress(addrs);
											estampFormBean.getObjEstampDet()
											.setAppAuthPersonName(name);
											estampFormBean.getObjEstampDet()
											.setAppPersons(noOfPrsns);
											estampFormBean.getObjEstampDet().setAppDistrict(disId);
											estampFormBean.getObjEstampDet().setIsAuthapp(1);

										}

									}
								}
							}

						}

						// party details--party//
						ArrayList partydtls = new ArrayList();
						ArrayList comp6 = new ArrayList();
						partydtls = objEstampBO.getPartyDtls(estampFormBean.getObjEstampDet()
								.getMainTxnId().toString(),language);
						if (partydtls.size() > 0) {
							for (int i = 0; i < partydtls.size(); i++) {
								comp6.add((ArrayList) partydtls.get(i));
								if (comp6 != null && comp6.size() > 0) {
									for (int k = 0; k < comp6.size(); k++) {
										ArrayList compSub = (ArrayList) comp6
										.get(k);

										String name = (String) compSub
										.get(0);
										String cntry = (String) compSub
										.get(1);
										String state = (String) compSub
										.get(2);
										String district = (String) compSub
										.get(3);
										String addrs = (String) compSub
										.get(4);
										String fathrNme = (String) compSub
										.get(5);
										String noOfPrsns = (String) compSub
										.get(6);
										String orgName = (String) compSub
										.get(7);

										if (orgName == null || orgName.equalsIgnoreCase("")) {
											estampFormBean.getObjEstampDet()
											.setPartyNamedsply(name);
											estampFormBean.getObjEstampDet()
											.setPartyCountryName(cntry);
											estampFormBean.getObjEstampDet()
											.setPartyStateName(state);
											estampFormBean.getObjEstampDet()
											.setPartyDistrictName(district);
											estampFormBean.getObjEstampDet()
											.setPartyAddress(addrs);
											estampFormBean.getObjEstampDet()
											.setPartyFatherName(fathrNme);
											estampFormBean.getObjEstampDet()
											.setPartyPersons(noOfPrsns);
										} else {
											estampFormBean.getObjEstampDet()
											.setPartyNamedsply(orgName);
											estampFormBean.getObjEstampDet()
											.setPartyCountryName(cntry);
											estampFormBean.getObjEstampDet()
											.setPartyStateName(state);
											estampFormBean.getObjEstampDet()
											.setPartyDistrictName(district);
											estampFormBean.getObjEstampDet()
											.setPartyAddress(addrs);
											estampFormBean.getObjEstampDet()
											.setPartyAuthPersonName(name);
											estampFormBean.getObjEstampDet()
											.setPartyPersons(noOfPrsns);
											estampFormBean.getObjEstampDet().setIsAuthparty(1);

										}

									}
								}
							}

						} else {
							estampFormBean.getObjEstampDet().setPartyNamedsply("-");
							estampFormBean.getObjEstampDet().setPartyCountryName("-");
							estampFormBean.getObjEstampDet().setPartyStateName("-");
							estampFormBean.getObjEstampDet().setPartyDistrictName("-");
							estampFormBean.getObjEstampDet().setPartyAddress("-");
							estampFormBean.getObjEstampDet().setPartyFatherName("-");
							estampFormBean.getObjEstampDet().setPartyPersons("-");

						}
						// generation of e-code and insertion into the
						// tables.
						try {
							boolean boo = false;
							boo = objEstampBO.inserteCode(estampFormBean, objEstampDto);

							if (boo) {

								ArrayList ecodedtls = new ArrayList();
								ArrayList comp7 = new ArrayList();
								ecodedtls = objEstampBO.getEcodeDtls(estampFormBean.getObjEstampDet()
										.getMainTxnId().toString());
								if (ecodedtls.size() > 0) {
									for (int i = 0; i < ecodedtls
									.size(); i++) {
										comp7.add((ArrayList) ecodedtls
												.get(i));
										if (comp7 != null
												&& comp7.size() > 0) {
											for (int k = 0; k < comp7
											.size(); k++) {
												ArrayList compSub = (ArrayList) comp7
												.get(k);

												String ecode = (String) compSub
												.get(0);
												String amt = (String) compSub
												.get(1);
												String estampType = "";
												if(language.equalsIgnoreCase("en"))
												{
													estampType = (String) compSub
													.get(2);
												}
												else
												{
													//estampType = (String) compSub
													//.get(8);
													String Name = "न्यायिक";
													estampType = Name;
												}
												
												String estampDate = (String) compSub
												.get(3);
												String issuedBy = (String) compSub
												.get(4);
												String offc = (String) compSub
												.get(5);
												String place = (String) compSub
												.get(6);
												String validityDt = (String) compSub
												.get(7);
												estampFormBean.getObjEstampDet().setEcode(ecode);
												estampFormBean.getObjEstampDet().setAmount(amt);
												estampFormBean.getObjEstampDet()
												.setEstampType(estampType);
												estampFormBean.getObjEstampDet()
												.setCurrentDate(estampDate);
												estampFormBean.getObjEstampDet()
												.setUserName(issuedBy);
												estampFormBean.getObjEstampDet()
												.setOfficeName(offc);
												estampFormBean.getObjEstampDet()
												.setIssuedPlace(place);
												estampFormBean.getObjEstampDet()
												.setEstampValidity(validityDt);

												forwardJsp = "ecodePage";

											}
										}
									}

								}
							} else {
								forwardJsp = "failure";
							}

						} catch (Exception e) {

						}

					}// --------end second condition

				}// -------end paymnent flag=P

				if (pymtFlg.equalsIgnoreCase("C")) {

					DecimalFormat myformatter = new DecimalFormat(
					"############");
					// double bal= damtToBePaid-dpaidAmt;
					// duty//
					/*String duty = bo.getDuty(estampFormBean.getObjEstampDet().getMainTxnId());
					eForm.getDutyCalculationDTO().setTotal(
							Double.parseDouble(duty));
					eForm.getDutyCalculationDTO().setTotalDisplay(
							myformatter
							.format(Double.parseDouble(duty)));*/
					// date and time//
					String currDate = objEstampBO.getCurrentDate();
					estampFormBean.getObjEstampDet().setCurrentDate(currDate);
					// deeds and instruments and purpose//
					/*ArrayList second = new ArrayList();
					ArrayList comp1 = new ArrayList();
					second = bo.getDeedDtls(estampFormBean.getObjEstampDet().getMainTxnId());
					if (second.size() > 0) {
						for (int i = 0; i < second.size(); i++) {
							comp1.add((ArrayList) second.get(i));
							if (comp1 != null && comp1.size() > 0) {
								for (int k = 0; k < comp1.size(); k++) {
									ArrayList compSub = (ArrayList) comp1
									.get(k);
									String deed = (String) compSub
									.get(0);
									String inst = (String) compSub
									.get(1);
									String purpose = (String) compSub
									.get(2);
									logger
									.debug("the deed is.........................."
											+ deed);
									logger
									.debug("the inst is.........................."
											+ inst);
									logger
									.debug("the purpose is.........................."
											+ purpose);
									eForm.getDutyCalculationDTO()
									.setDeedType(deed);
									eForm.getInstDTO()
									.setInstType(inst);
									estampFormBean.getObjEstampDet().setEstampPurps(purpose);
								}
							}
						}
					}*/
					// names and office details//
					
	String type = objEstampBO.gettype(userId);      //--added by satbir kumar-- 
					
					logger.debug(type);
					/*String roleId = objEstampBO.getRole(userId);
					String srrole = "GR1356609170484";
					String drrole = "GR1356671177515";
					String sprole = "GR1358488496205";
					String rurole = "GR1357368369828";
					String spbankrole = "GR1359453019113";
					
					estampFormBean.getObjEstampDet().setRole(roleId);*/

					if ("2".equalsIgnoreCase(type)) {
						ArrayList userdet = new ArrayList();
						ArrayList comp2 = new ArrayList();
						userdet = objEstampBO.getruName(userId,language);
						if (userdet.size() > 0) {
							for (int i = 0; i < userdet.size(); i++) {
								comp2.add((ArrayList) userdet.get(i));
								if (comp2 != null && comp2.size() > 0) {
									for (int k = 0; k < comp2.size(); k++) {
										ArrayList compSub = (ArrayList) comp2
										.get(k);

										String name = (String) compSub
										.get(0);
										String cid = (String) compSub
										.get(1);
										String sid = (String) compSub
										.get(2);
										String did = (String) compSub
										.get(3);
										String dname = (String) compSub
										.get(4);

										estampFormBean.getObjEstampDet().setUserName(name);
										if (cid.equalsIgnoreCase("1")) {
											if (sid
													.equalsIgnoreCase("1")) {
												estampFormBean.getObjEstampDet()
												.setIssuedPlace(dname);

											} else {
												if("en".equalsIgnoreCase(language))
												{	
													estampFormBean.getObjEstampDet()
													.setIssuedPlace("Others");
												}
												else
												{
													estampFormBean.getObjEstampDet()
													.setIssuedPlace("अन्य");	
												}
											}

										} else {
											if("en".equalsIgnoreCase(language))
											{	
												estampFormBean.getObjEstampDet()
												.setIssuedPlace("Others");
											}
											else
											{
												estampFormBean.getObjEstampDet()
												.setIssuedPlace("अन्य");	
											}
										}

									}
								}
							}
						}

						estampFormBean.getObjEstampDet().setOfficeName("-");
					}

					else if ("3".equalsIgnoreCase(type)) {
						String spname = objEstampBO.getspName(userId);
						String licNo= new DutyCalculationBO().getspLicenseNo(userId);
						ArrayList spdtls = new ArrayList();
						ArrayList comp3 = new ArrayList();
						spdtls = objEstampBO.getspDtls(userId,language);
						if (spdtls.size() > 0) {
							for (int i = 0; i < spdtls.size(); i++) {
								comp3.add((ArrayList) spdtls.get(i));
								if (comp3 != null && comp3.size() > 0) {
									for (int k = 0; k < comp3.size(); k++) {
										ArrayList compSub = (ArrayList) comp3
										.get(k);

										String ofc = (String) compSub
										.get(0);
										String plc = (String) compSub
										.get(1);
										String tehsil = (String) compSub
										.get(2);
										estampFormBean.getObjEstampDet().setOfficeName(ofc+" "+tehsil+" "+plc);
										estampFormBean.getObjEstampDet().setIssuedPlace(plc);
										estampFormBean.getObjEstampDet().setUserName(spname+"/"+licNo);
									}
								}
							}

						}
					}

					else if("4".equalsIgnoreCase(type)||"7".equalsIgnoreCase(type)||"5".equalsIgnoreCase(type))
					{

						ArrayList spbankdtls = new ArrayList();
						ArrayList comp4 = new ArrayList();
						spbankdtls = objEstampBO.getspbnkDtls(userId,language);
						if (spbankdtls.size() > 0) {
							for (int i = 0; i < spbankdtls.size(); i++) {
								comp4
								.add((ArrayList) spbankdtls
										.get(i));
								if (comp4 != null && comp4.size() > 0) {
									for (int k = 0; k < comp4.size(); k++) {
										ArrayList compSub = (ArrayList) comp4
										.get(k);

										String uname = (String) compSub
										.get(0);
										String ofc = (String) compSub
										.get(1);
										String plc = (String) compSub
										.get(2);
										String tehsil = (String) compSub
										.get(3);
										estampFormBean.getObjEstampDet().setOfficeName(ofc+" "+tehsil+" "+plc);
										estampFormBean.getObjEstampDet().setIssuedPlace(plc);
										estampFormBean.getObjEstampDet().setUserName(uname);
									}
								}
							}

						}
					}

					else if("I".equalsIgnoreCase(type))
					{
						ArrayList iudtls = new ArrayList();
						ArrayList comp4 = new ArrayList();
						String officeId = (String)session.getAttribute("loggedToOffice");
						iudtls = objEstampBO.getiuDtls(userId,officeId,language);
						if (iudtls.size() > 0) {
							for (int i = 0; i < iudtls.size(); i++) {
								comp4.add((ArrayList) iudtls.get(i));
								if (comp4 != null && comp4.size() > 0) {
									for (int k = 0; k < comp4.size(); k++) {
										ArrayList compSub = (ArrayList) comp4
										.get(k);

										String ofc = (String) compSub
										.get(0);
										String plc = (String) compSub
										.get(1);
										String uname = (String) compSub
										.get(2);
										estampFormBean.getObjEstampDet().setOfficeName(ofc);
										estampFormBean.getObjEstampDet().setIssuedPlace(plc);
										estampFormBean.getObjEstampDet().setUserName(uname);
									}
								}
							}

						}
					}

					// party details--applicant//
					ArrayList appdtls = new ArrayList();
					ArrayList comp5 = new ArrayList();
					appdtls = objEstampBO.getAppDtls(estampFormBean.getObjEstampDet().getMainTxnId().toString(),language);
					if (appdtls.size() > 0) {
						for (int i = 0; i < appdtls.size(); i++) {
							comp5.add((ArrayList) appdtls.get(i));
							if (comp5 != null && comp5.size() > 0) {
								for (int k = 0; k < comp5.size(); k++) {
									ArrayList compSub = (ArrayList) comp5
									.get(k);

									String name = (String) compSub
									.get(0);
									String cntry = (String) compSub
									.get(1);
									String state = (String) compSub
									.get(2);
									String district = (String) compSub
									.get(3);
									String addrs = (String) compSub
									.get(4);
									String fathrNme = (String) compSub
									.get(5);
									String noOfPrsns = (String) compSub
									.get(6);
									String orgName = (String) compSub
									.get(7);
									String disId = (String) compSub
									.get(8);

									if (orgName==null || orgName.equalsIgnoreCase("")) {
										estampFormBean.getObjEstampDet().setAppNamedsply(name);
										estampFormBean.getObjEstampDet().setAppCountryName(cntry);
										estampFormBean.getObjEstampDet().setAppStateName(state);
										estampFormBean.getObjEstampDet()
										.setAppDistrictName(district);
										estampFormBean.getObjEstampDet().setAppAddress(addrs);
										estampFormBean.getObjEstampDet()
										.setAppFatherName(fathrNme);
										estampFormBean.getObjEstampDet().setAppPersons(noOfPrsns);
										estampFormBean.getObjEstampDet().setAppDistrict(disId);
									} else {
										estampFormBean.getObjEstampDet().setAppNamedsply(orgName);
										estampFormBean.getObjEstampDet().setAppCountryName(cntry);
										estampFormBean.getObjEstampDet().setAppStateName(state);
										estampFormBean.getObjEstampDet()
										.setAppDistrictName(district);
										estampFormBean.getObjEstampDet().setAppAddress(addrs);
										estampFormBean.getObjEstampDet()
										.setAppAuthPersonName(name);
										estampFormBean.getObjEstampDet().setAppPersons(noOfPrsns);
										estampFormBean.getObjEstampDet().setAppDistrict(disId);
										estampFormBean.getObjEstampDet().setIsAuthapp(1);

									}

								}
							}
						}

					}

					// party details--party//
					ArrayList partydtls = new ArrayList();
					ArrayList comp6 = new ArrayList();
					partydtls = objEstampBO.getPartyDtls(estampFormBean.getObjEstampDet().getMainTxnId().toString(),language);
					if (partydtls.size() > 0) {
						for (int i = 0; i < partydtls.size(); i++) {
							comp6.add((ArrayList) partydtls.get(i));
							if (comp6 != null && comp6.size() > 0) {
								for (int k = 0; k < comp6.size(); k++) {
									ArrayList compSub = (ArrayList) comp6
									.get(k);

									String name = (String) compSub
									.get(0);
									String cntry = (String) compSub
									.get(1);
									String state = (String) compSub
									.get(2);
									String district = (String) compSub
									.get(3);
									String addrs = (String) compSub
									.get(4);
									String fathrNme = (String) compSub
									.get(5);
									String noOfPrsns = (String) compSub
									.get(6);
									String orgName = (String) compSub
									.get(7);

									if (orgName==null|| orgName.equalsIgnoreCase("")) {
										estampFormBean.getObjEstampDet().setPartyNamedsply(name);
										estampFormBean.getObjEstampDet()
										.setPartyCountryName(cntry);
										estampFormBean.getObjEstampDet().setPartyStateName(state);
										estampFormBean.getObjEstampDet()
										.setPartyDistrictName(district);
										estampFormBean.getObjEstampDet().setPartyAddress(addrs);
										estampFormBean.getObjEstampDet()
										.setPartyFatherName(fathrNme);
										estampFormBean.getObjEstampDet()
										.setPartyPersons(noOfPrsns);
									} else {
										estampFormBean.getObjEstampDet()
										.setPartyNamedsply(orgName);
										estampFormBean.getObjEstampDet()
										.setPartyCountryName(cntry);
										estampFormBean.getObjEstampDet().setPartyStateName(state);
										estampFormBean.getObjEstampDet()
										.setPartyDistrictName(district);
										estampFormBean.getObjEstampDet().setPartyAddress(addrs);
										estampFormBean.getObjEstampDet()
										.setPartyAuthPersonName(name);
										estampFormBean.getObjEstampDet()
										.setPartyPersons(noOfPrsns);
										estampFormBean.getObjEstampDet().setIsAuthparty(1);

									}

								}
							}
						}

					} else {
						estampFormBean.getObjEstampDet().setPartyNamedsply("-");
						estampFormBean.getObjEstampDet().setPartyCountryName("-");
						estampFormBean.getObjEstampDet().setPartyStateName("-");
						estampFormBean.getObjEstampDet().setPartyDistrictName("-");
						estampFormBean.getObjEstampDet().setPartyAddress("-");
						estampFormBean.getObjEstampDet().setPartyFatherName("-");
						estampFormBean.getObjEstampDet().setPartyPersons("-");

					}
					// e-code details.

					ArrayList ecodedtls = new ArrayList();
					ArrayList comp7 = new ArrayList();
					ecodedtls = objEstampBO.getEcodeDtls(estampFormBean.getObjEstampDet().getMainTxnId().toString());
					if (ecodedtls.size() > 0) {
						for (int i = 0; i < ecodedtls.size(); i++) {
							comp7.add((ArrayList) ecodedtls.get(i));
							if (comp7 != null && comp7.size() > 0) {
								for (int k = 0; k < comp7.size(); k++) {
									ArrayList compSub = (ArrayList) comp7
									.get(k);

									String ecode = (String) compSub
									.get(0);
									String amt = (String) compSub
									.get(1);
									String estampType = "";
									if(language.equalsIgnoreCase("en"))
									{
										estampType = (String) compSub
										.get(2);
									}
									else
									{
										//estampType = (String) compSub
										//.get(8);
										String Name = "न्यायिक";
										estampType = Name;
									}
									
									String estampDate = (String) compSub
									.get(3);
									String issuedBy = (String) compSub
									.get(4);
									String offc = (String) compSub
									.get(5);
									String place = (String) compSub
									.get(6);
									String validityDt = (String) compSub
									.get(7);
									estampFormBean.getObjEstampDet().setEcode(ecode);
									estampFormBean.getObjEstampDet().setAmount(amt);
									estampFormBean.getObjEstampDet().setEstampType(estampType);
									estampFormBean.getObjEstampDet().setCurrentDate(estampDate);
									estampFormBean.getObjEstampDet().setUserName(issuedBy);
									estampFormBean.getObjEstampDet().setOfficeName(offc);
									estampFormBean.getObjEstampDet().setIssuedPlace(place);
									estampFormBean.getObjEstampDet().setEstampValidity(validityDt);

									forwardJsp = "ecodePage";

								}
							}
						}

					}

				}// end payment flag=C

			}// -----------------end else condition--means record found.

		}// ---------------payment action ends---------------
        estampFormBean.getObjEstampDet().setFormName("");
        estampFormBean.getObjEstampDet().setActionName("");
        }
        if (request.getParameter("paymentStatus") != null && request.getAttribute("eStampRegDistId") == null) {
			logger.info("------>*****BACK TO ACTION");
			if(estampFormBean.getObjEstampDet().getMainTxnId() != null)
			{
				logger.info("------>*****"+estampFormBean.getObjEstampDet().getMainTxnId());
			}
			else
			{
				logger.info("------>*****"+objEstampDto.getMainTxnId());
				String TxnRequestId = objEstampDto.getMainTxnId().toString();
				estampFormBean.getObjEstampDet().setMainTxnId(TxnRequestId);
			}
				//objDashBoardDTO1.getMainTxnId1().toString();
			
			//logger.info("------>*****"+eForm.getMainTxnId());

			if (request.getParameter("paymentStatus").equalsIgnoreCase("P")) {
				String uniId = (String) request.getParameter("parentKey");
				if (uniId != null || uniId != "") {
					estampFormBean.getObjEstampDet().setUniqKey(uniId);
				}
				/*
				 * String mainId = bo.getMainId(uniId);
				 * eForm.setMainTxnId(mainId);
				 */
				ArrayList first = new ArrayList();
				ArrayList comp = new ArrayList();
				String uniqId = "";
				String amtToBePaid = "";
				String paidAmt = "";
				String pymtFlg = "";
				first = objEstampBO.getPayDtls(estampFormBean.getObjEstampDet().getMainTxnId().toString());

				if (first.size() > 0) {
					for (int i = 0; i < first.size(); i++) {
						comp.add((ArrayList) first.get(i));
						if (comp != null && comp.size() > 0) {
							for (int k = 0; k < comp.size(); k++) {
								ArrayList compSub = (ArrayList) comp.get(k);
								uniqId = (String) compSub.get(0);
								amtToBePaid = (String) compSub.get(1);
								paidAmt = (String) compSub.get(2);
								pymtFlg = (String) compSub.get(3);
							}
						}
					}
				}
				//Commented By Lavi for partial payment
				//double damtToBePaid = Double.parseDouble(paidAmt);   
				
				double damtToBePaid = Double.parseDouble(amtToBePaid);		// Added By Lavi for Partial Payment.
				double dpaidAmt = Double.parseDouble(paidAmt);
				// condition 1-------------------pending amount-------------
				if (damtToBePaid > dpaidAmt) {
					double bal = damtToBePaid - dpaidAmt;
					estampFormBean.getObjEstampDet().setIsPartial(1);
					
					//Added By Lavi for forwarding from payment screen to Dashboard.
					
					ArrayList pendingApplicationList = objEstampBO.getPendingApps(session.getAttribute("UserId").toString(),language);
	        		logger.info("--------------->"+pendingApplicationList.size());
					if(pendingApplicationList.size()==0)
						estampFormBean.getObjEstampDet().setPendingApps(null);
					else
						estampFormBean.getObjEstampDet().setPendingApps(pendingApplicationList);
					request.setAttribute("pendingApplicationList", estampFormBean.getObjEstampDet().getPendingApps());
					if(language.equalsIgnoreCase("en"))
					{
						request.setAttribute("success_msg", "The Estamp has still amount left to process the transaction. Estamp Transaction Id is: "+estampFormBean.getObjEstampDet().getMainTxnId());
					}
					else
					{
						request.setAttribute("success_msg", "ई स्टाम्प अभी भी राशि लेन - देन की प्रक्रिया के लिए छोड़ दिया गया है। ई स्टाम्प आइडी : "+estampFormBean.getObjEstampDet().getMainTxnId());
					}
					
					forwardJsp  =  "confirmation";
				}

				// condition 2-------------------full payment, eCode
				// generation------
				//MODIFIED BY SIMRAN
				if (damtToBePaid <= dpaidAmt) {
					String eCodeNumber = "";
					DecimalFormat myformatter = new DecimalFormat(
					"############");
					double bal = damtToBePaid - dpaidAmt;
					// duty//
					//String duty = objEstampBO.getDuty(estampFormBean.getObjEstampDet().getMainTxnId());
					//estampFormBean.getObjEstampDet().setTotal(
							//Double.parseDouble(estampFormBean.getObjEstampDet().getAmount().toString()));
					//eForm.getDutyCalculationDTO().setTotalDisplay(
							//myformatter.format(Double.parseDouble(duty)));
					// date and time//
					String currDate = objEstampBO.getCurrentDate();
					estampFormBean.getObjEstampDet().setCurrentDate(currDate);
					// deeds and instruments and purpose//
					/*ArrayList second = new ArrayList();
					ArrayList comp1 = new ArrayList();
					second = objEstampBO.getDeedDtls(estampFormBean.getObjEstampDet().getMainTxnId());
					if (second.size() > 0) {
						for (int i = 0; i < second.size(); i++) {
							comp1.add((ArrayList) second.get(i));
							if (comp1 != null && comp1.size() > 0) {
								for (int k = 0; k < comp1.size(); k++) {
									ArrayList compSub = (ArrayList) comp1
									.get(k);
									String deed = (String) compSub.get(0);
									String inst = (String) compSub.get(1);
									String purpose = (String) compSub
									.get(2);
									logger
									.debug("the deed is.........................."
											+ deed);
									logger
									.debug("the inst is.........................."
											+ inst);
									logger
									.debug("the purpose is.........................."
											+ purpose);
									eForm.getDutyCalculationDTO()
									.setDeedType(deed);
									eForm.getInstDTO().setInstType(inst);
									eForm.setEstampPurpose(purpose);
								}
							}
						}
					}*/
					// names and office details//
					
	String type = objEstampBO.gettype(userId);      //--added by satbir kumar-- 
					
					logger.debug(type);
					/*String roleId = objEstampBO.getRole(userId);
					String srrole = "GR1356609170484";
					String drrole = "GR1356671177515";
					String sprole = "GR1358488496205";
					String rurole = "GR1357368369828";
					String spbankrole = "GR1359453019113";
					
					estampFormBean.getObjEstampDet().setRole(roleId);*/

					if ("2".equalsIgnoreCase(type)) {
						ArrayList userdet = new ArrayList();
						ArrayList comp2 = new ArrayList();
						userdet = objEstampBO.getruName(userId,language);
						if (userdet.size() > 0) {
							for (int i = 0; i < userdet.size(); i++) {
								comp2.add((ArrayList) userdet.get(i));
								if (comp2 != null && comp2.size() > 0) {
									for (int k = 0; k < comp2.size(); k++) {
										ArrayList compSub = (ArrayList) comp2
										.get(k);

										String name = (String) compSub
										.get(0);
										String cid = (String) compSub
										.get(1);
										String sid = (String) compSub
										.get(2);
										String did = (String) compSub
										.get(3);
										String dname = (String) compSub
										.get(4);

										estampFormBean.getObjEstampDet().setUserName(name);
										if (cid.equalsIgnoreCase("1")) {
											if (sid.equalsIgnoreCase("1")) {
												estampFormBean.getObjEstampDet().setIssuedPlace(dname);

											} else {
												if("en".equalsIgnoreCase(language))
												{	
													estampFormBean.getObjEstampDet()
													.setIssuedPlace("Others");
												}
												else
												{
													estampFormBean.getObjEstampDet()
													.setIssuedPlace("अन्य");	
												}
											}

										} else {
											if("en".equalsIgnoreCase(language))
											{	
												estampFormBean.getObjEstampDet()
												.setIssuedPlace("Others");
											}
											else
											{
												estampFormBean.getObjEstampDet()
												.setIssuedPlace("अन्य");	
											}
										}

									}
								}
							}
						}

						estampFormBean.getObjEstampDet().setOfficeName("-");
					}

					else if ("3".equalsIgnoreCase(type)) {
						String spname = objEstampBO.getspName(userId);
						String licNo= new DutyCalculationBO().getspLicenseNo(userId);
						ArrayList spdtls = new ArrayList();
						ArrayList comp3 = new ArrayList();
						spdtls = objEstampBO.getspDtls(userId,language);
						if (spdtls.size() > 0) {
							for (int i = 0; i < spdtls.size(); i++) {
								comp3.add((ArrayList) spdtls.get(i));
								if (comp3 != null && comp3.size() > 0) {
									for (int k = 0; k < comp3.size(); k++) {
										ArrayList compSub = (ArrayList) comp3
										.get(k);

										String ofc = (String) compSub
										.get(0);
										String plc = (String) compSub
										.get(1);
										String tehsil = (String) compSub
										.get(2);
										estampFormBean.getObjEstampDet().setOfficeName(ofc+" "+tehsil+" "+plc);
										estampFormBean.getObjEstampDet().setIssuedPlace(plc);
										estampFormBean.getObjEstampDet().setUserName(spname+"/"+licNo);
									}
								}
							}

						}
					}

					else if("4".equalsIgnoreCase(type)||"7".equalsIgnoreCase(type)||"5".equalsIgnoreCase(type))
					{

						ArrayList spbankdtls = new ArrayList();
						ArrayList comp4 = new ArrayList();
						spbankdtls = objEstampBO.getspbnkDtls(userId,language);
						if (spbankdtls.size() > 0) {
							for (int i = 0; i < spbankdtls.size(); i++) {
								comp4.add((ArrayList) spbankdtls.get(i));
								if (comp4 != null && comp4.size() > 0) {
									for (int k = 0; k < comp4.size(); k++) {
										ArrayList compSub = (ArrayList) comp4
										.get(k);

										String uname = (String) compSub
										.get(0);
										String ofc = (String) compSub
										.get(1);
										String plc = (String) compSub
										.get(2);
										String tehsil = (String) compSub
										.get(3);
										estampFormBean.getObjEstampDet().setOfficeName(ofc+" "+tehsil+" "+plc);
										estampFormBean.getObjEstampDet().setIssuedPlace(plc);
										estampFormBean.getObjEstampDet().setUserName(uname);
									}
								}
							}

						}
					}

					else if("I".equalsIgnoreCase(type))
					{
						ArrayList iudtls = new ArrayList();
						ArrayList comp4 = new ArrayList();
						String officeId = (String)session.getAttribute("loggedToOffice");
						iudtls = objEstampBO.getiuDtls(userId,officeId,language);
						if (iudtls.size() > 0) {
							for (int i = 0; i < iudtls.size(); i++) {
								comp4.add((ArrayList) iudtls.get(i));
								if (comp4 != null && comp4.size() > 0) {
									for (int k = 0; k < comp4.size(); k++) {
										ArrayList compSub = (ArrayList) comp4
										.get(k);

										String ofc = (String) compSub
										.get(0);
										String plc = (String) compSub
										.get(1);
										String uname = (String) compSub
										.get(2);
										estampFormBean.getObjEstampDet().setOfficeName(ofc);
										estampFormBean.getObjEstampDet().setIssuedPlace(plc);
										estampFormBean.getObjEstampDet().setUserName(uname);
									}
								}
							}

						}
					}
					// party details--applicant//
					ArrayList appdtls = new ArrayList();
					ArrayList comp5 = new ArrayList();
					appdtls = objEstampBO.getAppDtls(estampFormBean.getObjEstampDet().getMainTxnId().toString(),language);
					if (appdtls.size() > 0) {
						for (int i = 0; i < appdtls.size(); i++) {
							comp5.add((ArrayList) appdtls.get(i));
							if (comp5 != null && comp5.size() > 0) {
								for (int k = 0; k < comp5.size(); k++) {
									ArrayList compSub = (ArrayList) comp5
									.get(k);

									String name = (String) compSub.get(0);
									String cntry = (String) compSub.get(1);
									String state = (String) compSub.get(2);
									String district = (String) compSub
									.get(3);
									String addrs = (String) compSub.get(4);
									String fathrNme = (String) compSub
									.get(5);
									String noOfPrsns = (String) compSub
									.get(6);
									String orgName = (String) compSub
									.get(7);
									String disId = (String) compSub.get(8);
									String deedDuratn = (String)compSub.get(9);

									if (orgName != null) {

										estampFormBean.getObjEstampDet().setAppNamedsply(orgName);
										estampFormBean.getObjEstampDet().setAppCountryName(cntry);
										estampFormBean.getObjEstampDet().setAppStateName(state);
										estampFormBean.getObjEstampDet().setAppDistrictName(district);
										estampFormBean.getObjEstampDet().setAppAddress(addrs);
										estampFormBean.getObjEstampDet().setAppAuthPersonName(name);
										estampFormBean.getObjEstampDet().setAppPersons(noOfPrsns);
										estampFormBean.getObjEstampDet().setAppDistrict(disId);
										estampFormBean.getObjEstampDet().setIsAuthapp(1);
										if (deedDuratn!=null)
											estampFormBean.getObjEstampDet().setDeedDuration(deedDuratn);
											else
												estampFormBean.getObjEstampDet().setDeedDuration("-");
									}
									else {
										estampFormBean.getObjEstampDet().setAppNamedsply(name);
										estampFormBean.getObjEstampDet().setAppCountryName(cntry);
										estampFormBean.getObjEstampDet().setAppStateName(state);
										estampFormBean.getObjEstampDet().setAppDistrictName(district);
										estampFormBean.getObjEstampDet().setAppAddress(addrs);
										estampFormBean.getObjEstampDet().setAppFatherName(fathrNme);
										estampFormBean.getObjEstampDet().setAppPersons(noOfPrsns);
										estampFormBean.getObjEstampDet().setAppDistrict(disId);
										

									}

								}
							}
						}

					}

					// party details--party//
					ArrayList partydtls = new ArrayList();
					ArrayList comp6 = new ArrayList();
					partydtls = objEstampBO.getPartyDtls(estampFormBean.getObjEstampDet().getMainTxnId().toString(),language);
					if (partydtls.size() > 0) {
						for (int i = 0; i < partydtls.size(); i++) {
							comp6.add((ArrayList) partydtls.get(i));
							if (comp6 != null && comp6.size() > 0) {
								for (int k = 0; k < comp6.size(); k++) {
									ArrayList compSub = (ArrayList) comp6
									.get(k);

									String name = (String) compSub.get(0);
									String cntry = (String) compSub.get(1);
									String state = (String) compSub.get(2);
									String district = (String) compSub
									.get(3);
									String addrs = (String) compSub.get(4);
									String fathrNme = (String) compSub
									.get(5);
									String noOfPrsns = (String) compSub
									.get(6);
									String orgName = (String) compSub
									.get(7);

									if (orgName != null) {
										estampFormBean.getObjEstampDet().setPartyNamedsply(orgName);
										estampFormBean.getObjEstampDet().setPartyCountryName(cntry);
										estampFormBean.getObjEstampDet().setPartyStateName(state);
										estampFormBean.getObjEstampDet()
										.setPartyDistrictName(district);
										estampFormBean.getObjEstampDet().setPartyAddress(addrs);
										estampFormBean.getObjEstampDet().setPartyAuthPersonName(name);
										estampFormBean.getObjEstampDet().setPartyPersons(noOfPrsns);
										estampFormBean.getObjEstampDet().setIsAuthparty(1);
									} else {
										estampFormBean.getObjEstampDet().setPartyNamedsply(name);
										estampFormBean.getObjEstampDet().setPartyCountryName(cntry);
										estampFormBean.getObjEstampDet().setPartyStateName(state);
										estampFormBean.getObjEstampDet()
										.setPartyDistrictName(district);
										estampFormBean.getObjEstampDet().setPartyAddress(addrs);
										estampFormBean.getObjEstampDet().setPartyFatherName(fathrNme);
										estampFormBean.getObjEstampDet().setPartyPersons(noOfPrsns);
									}

									/*
									 * if (orgName.equalsIgnoreCase(null)){
									 * eForm.setPartyNamedsply(name);
									 * eForm.setPartyCountryName(cntry);
									 * eForm.setPartyStateName(state);
									 * eForm.setPartyDistrictName(district);
									 * eForm.setPartyAddress(addrs);
									 * eForm.setPartyFatherName(fathrNme);
									 * eForm.setPartyPersons(noOfPrsns);
									 * }else{
									 * eForm.setPartyNamedsply(orgName);
									 * eForm.setPartyCountryName(cntry);
									 * eForm.setPartyStateName(state);
									 * eForm.setPartyDistrictName(district);
									 * eForm.setPartyAddress(addrs);
									 * eForm.setPartyAuthPersonName(name);
									 * eForm.setPartyPersons(noOfPrsns);
									 * eForm.setIsAuthparty(1);
									 * 
									 * }
									 */

								}
							}
						}

					} else {
						estampFormBean.getObjEstampDet().setPartyNamedsply("-");
						estampFormBean.getObjEstampDet().setPartyCountryName("-");
						estampFormBean.getObjEstampDet().setPartyStateName("-");
						estampFormBean.getObjEstampDet().setPartyDistrictName("-");
						estampFormBean.getObjEstampDet().setPartyAddress("-");
						estampFormBean.getObjEstampDet().setPartyFatherName("-");
						estampFormBean.getObjEstampDet().setPartyPersons("-");

					}
					
					ArrayList judicialDetails = objEstampBO.getjudDetails(estampFormBean.getObjEstampDet().getMainTxnId().toString(),language);
					
					
					if (judicialDetails.size()>0){
						
						for (int i = 0; i < judicialDetails.size(); i++) {
							
							EstampDetailsDTO objEstampDetailsDTO = (EstampDetailsDTO)judicialDetails.get(i);
									estampFormBean.getObjEstampDet().setCourtDocTypeName(objEstampDetailsDTO.getCourtDocTypeName());
									estampFormBean.getObjEstampDet().setEstampPurps(objEstampDetailsDTO.getEstampPurps());
									estampFormBean.getObjEstampDet().setAmount(objEstampDetailsDTO.getAmount());
								
						}
						}
					
					// generation of e-code and insertion into the tables.
					try {
						boolean boo = false;
						boolean checkEstamp = false;
						checkEstamp=objEstampBO.checkEstamp(estampFormBean.getObjEstampDet().getMainTxnId().toString(),"E");
						if(checkEstamp){
						boo = objEstampBO.inserteCode(estampFormBean, objEstampDto);
						}
						else{
							eCodeNumber=objEstampBO.getEcode(estampFormBean.getObjEstampDet().getMainTxnId().toString(),"E");	
						}
						if (boo) {
							
							ArrayList ecodedtls = new ArrayList();
							ArrayList comp7 = new ArrayList();
							ecodedtls = objEstampBO.getEcodeDtls(estampFormBean.getObjEstampDet().getMainTxnId().toString());
							if (ecodedtls.size() > 0) {
								for (int i = 0; i < ecodedtls.size(); i++) {
									comp7.add((ArrayList) ecodedtls.get(i));
									if (comp7 != null && comp7.size() > 0) {
										for (int k = 0; k < comp7.size(); k++) {
											ArrayList compSub = (ArrayList) comp7
											.get(k);
											eCodeNumber = (String) compSub
											.get(0);
											String ecode = (String) compSub
											.get(0);
											String amt = (String) compSub
											.get(1);
											String estampType = "";
											if(language.equalsIgnoreCase("en"))
											{
												estampType = (String) compSub
												.get(2);
											}
											else
											{
												//estampType = (String) compSub
												//.get(8);
												String Name = "न्यायिक";
												estampType = Name;
											}
											
											String estampDate = (String) compSub
											.get(3);
											String issuedBy = (String) compSub
											.get(4);
											String offc = (String) compSub
											.get(5);
											String place = (String) compSub
											.get(6);
											String validityDt = (String) compSub
											.get(7);
											estampFormBean.getObjEstampDet().setEcode(ecode);
											estampFormBean.getObjEstampDet()
											.setAmount(amt);
											estampFormBean.getObjEstampDet().setEstampType(estampType);
											/*
											 * SimpleDateFormat sdf1 = new
											 * SimpleDateFormat
											 * ("yyyy-MM-dd- KK:mm:ss");
											 * SimpleDateFormat sdf2 = new
											 * SimpleDateFormat
											 * ("dd/MM/yyyy hh:mm:ss a");
											 * logger
											 * .debug("the date is-----"
											 * +estampDate); Date d1 =
											 * sdf1.parse(estampDate);
											 * String transDate =
											 * sdf2.format(d1);
											 */
											estampFormBean.getObjEstampDet()
											.setCurrentDate(estampDate);
											estampFormBean.getObjEstampDet().setUserName(issuedBy);
											estampFormBean.getObjEstampDet().setOfficeName(offc);
											estampFormBean.getObjEstampDet().setIssuedPlace(place);
											estampFormBean.getObjEstampDet()
											.setEstampValidity(validityDt);
											logger
											.debug("Just before Final page redirection");
											forwardJsp = "ecodePage";

										}
									}
								}

							}
						} else {
							forwardJsp = "failure";
						}

					} catch (Exception e) {

					}
					if(language.equalsIgnoreCase("en"))
					{
						request.setAttribute("success_msg", "The Estamp has been created successfully. Estamp code is: "+eCodeNumber);
					}
					else
					{
						request.setAttribute("success_msg", "ई स्टाम्प सफलतापूर्वक बना दिया गया है। ई स्टाम्प कोड : "+eCodeNumber);
					}
					
					forwardJsp  =  "confirmation";
				}// end of second condition
			}
		}
        
        if ("NEXT_TO_PAYMENT_ACTION_DASHBOARD".equals(actionName)) {

			ArrayList first = new ArrayList();
			logger.info("----->" +objEstampDto.getMainTxnId());
			//String TxnRequestId = objEstampDto.getMainTxnId().toString();
			String TxnRequestId = estampFormBean.getObjEstampDet().getMainTxnId().toString();
			// objDashBoardDTO1.getMainTxnId1();
			logger.info("---------------->" + actionName);
			//TxnRequestId = eForm.getObjDashBoardDTO().getMainTxnId1().toString();
			logger.info("---------------->" + TxnRequestId);
			// first = bo.getPayDtls(eForm.getMainTxnId());
			first = objEstampBO.getPayDtls(TxnRequestId);
			estampFormBean.getObjEstampDet().setMainTxnId(TxnRequestId);
			logger.info("---->***"+estampFormBean.getObjEstampDet().getMainTxnId());
			first= first==null?new ArrayList():first;
			if(first.size()==0)
			{	
			estampFormBean.getObjEstampDet().setTotal(
					Double.parseDouble(estampFormBean.getObjEstampDet().getBalanceAmount().toString()));
			}
			else
			{ArrayList comp = new ArrayList();
			String uniqId = "";
			String amtToBePaid = "";
			String paidAmt = "";
			String pymtFlg = "";
				for (int i = 0; i < first.size(); i++) {
					comp.add((ArrayList) first.get(i));
					if (comp != null && comp.size() > 0) {
						for (int k = 0; k < comp.size(); k++) {
							ArrayList compSub = (ArrayList) comp.get(k);
							uniqId = (String) compSub.get(0);
							amtToBePaid = (String) compSub.get(1);
							paidAmt = (String) compSub.get(2);
							pymtFlg = (String) compSub.get(3);
							logger.debug("unique id is ............................"+ uniqId);
							logger.debug("unpayable amt is ............................"+ amtToBePaid);
							logger.debug("paid amt is..................................."+ paidAmt);
							logger
							.debug("pymtFlg is....................................."+ pymtFlg);
						}
					}
				}
				double damtToBePaid = Double.parseDouble(amtToBePaid);
				estampFormBean.getObjEstampDet().setTotal(damtToBePaid);
			}
			//boolean insrt1 = objEstampBO.insertPay(estampFormBean, objEstampDto);
			logger.info("---->***"+estampFormBean.getObjEstampDet().getMainTxnId());
			
			
			
			

			if (first.size() == 0) {
				logger.debug("inside if");
				
				estampFormBean.getObjEstampDet().setTotal(
						Double.parseDouble(estampFormBean.getObjEstampDet().getBalanceAmount().toString())); 
				// String duty = bo.getDuty(eForm.getMainTxnId());
				//String duty = bo.getDuty((String) TxnRequestId);

				//eForm.getDutyCalculationDTO().(
						//Double.parseDouble(duty));
				boolean insrt = false;
				try {
					
					logger.debug("NEXT_TO_PAYMENT_ACTION_DASHBOARD :: if block ");
					insrt = objEstampBO.insertPay(estampFormBean, objEstampDto);

					if (insrt) {
						logger.debug("inside if insrt");
						String type = objEstampBO.gettype(userId);      //--added by satbir kumar-- 
						
						logger.debug(type);
						
						if("3".equalsIgnoreCase(type)||"4".equalsIgnoreCase(type)||"7".equalsIgnoreCase(type)||"5".equalsIgnoreCase(type))
						{
							ArrayList details = objEstampBO.getDetails(userId,objEstampDto,language);
							
							if(details!=null)
							{
								logger.debug(estampFormBean.getObjEstampDet()
										.getDistrictid());
								logger.debug(estampFormBean.getObjEstampDet()
										.getDistrictname());
								
								request.setAttribute("parentDistrictId", estampFormBean.getObjEstampDet()
										.getDistrictid());
								
								request.setAttribute("parentDistrictName", estampFormBean.getObjEstampDet()
										.getDistrictname());
								request.setAttribute("parentOfficeId", "NA");
								request.setAttribute("parentOfficeName", "NA");
								request.setAttribute("parentReferenceId", estampFormBean.getObjEstampDet().getMainTxnId().toString());
							}
						}
						
						if("2".equalsIgnoreCase(type))
						{
							String txnId=estampFormBean.getObjEstampDet().getMainTxnId().toString();
							ArrayList details = objEstampBO.getruDetails(userId,objEstampDto,txnId,language);
							
							if(details!=null)
							{
								request.setAttribute("parentDistrictId", estampFormBean.getObjEstampDet()
										.getDistrictid());
								
								request.setAttribute("parentDistrictName", estampFormBean.getObjEstampDet()
										.getDistrictname());
								request.setAttribute("parentOfficeId", "NA");
								request.setAttribute("parentOfficeName", "NA");
								request.setAttribute("parentReferenceId", estampFormBean.getObjEstampDet().getMainTxnId().toString());
							}
						}
							
						
							if("I".equalsIgnoreCase(type))
							{
								String offcId = (String) session.getAttribute("loggedToOffice");
								ArrayList iudetails = objEstampBO.getiuDetails(objEstampDto,offcId,language);
								
								if(iudetails!=null)
								{
									request.setAttribute("parentDistrictId", estampFormBean.getObjEstampDet()
											.getDistrictid());
									
									request.setAttribute("parentDistrictName", estampFormBean.getObjEstampDet()
											.getDistrictname());
									request.setAttribute("parentOfficeId", estampFormBean.getObjEstampDet().getIuofficeid() );
									request.setAttribute("parentOfficeName", estampFormBean.getObjEstampDet().getIuofficename());
									request.setAttribute("parentReferenceId", estampFormBean.getObjEstampDet().getMainTxnId().toString());
								}
						
						
						}
						DecimalFormat myformatter = new DecimalFormat(
						"############");
						request.setAttribute("parentModName", "E-Stamping");
						request.setAttribute("parentFunName","E-Stamp Judicial");
						request.setAttribute("parentFunId", "FUN_022");
						double amt=0.0;
						
						request.setAttribute("parentAmount", myformatter.format(
								Double.parseDouble(estampFormBean.getObjEstampDet().getBalanceAmount().toString())));
						request.setAttribute("parentTable",
						"IGRS_ESTAMP_PAYMENT_DETLS");
						request.setAttribute("parentKey", estampFormBean.getObjEstampDet().getUniqKey());
						request.setAttribute("forwardPath","./estampJudicialNew.do?TRFS=NGI");
						request.setAttribute("parentColumnName","ESTAMP_PAYMENT_ID");
						request.setAttribute("formName","estampFormBean");
						logger.debug("just before redirection");
						forwardJsp = "nextToPay";

					} else {
						forwardJsp = "failure";
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.debug(e.getMessage(),e);
					forwardJsp = "failure";
				}

			} else {

				logger.debug("inside else---means record is found");
				ArrayList comp = new ArrayList();
				String uniqId = "";
				String amtToBePaid = "";
				String paidAmt = "";
				String pymtFlg = "";

				for (int i = 0; i < first.size(); i++) {
					comp.add((ArrayList) first.get(i));
					if (comp != null && comp.size() > 0) {
						for (int k = 0; k < comp.size(); k++) {
							ArrayList compSub = (ArrayList) comp.get(k);
							uniqId = (String) compSub.get(0);
							amtToBePaid = (String) compSub.get(1);
							paidAmt = (String) compSub.get(2);
							pymtFlg = (String) compSub.get(3);
							logger.debug("unique id is ............................"+ uniqId);
							logger.debug("unpayable amt is ............................"+ amtToBePaid);
							logger.debug("paid amt is..................................."+ paidAmt);
							logger
							.debug("pymtFlg is....................................."+ pymtFlg);
						}
					}
				}
				double damtToBePaid = Double.parseDouble(amtToBePaid);

				if (pymtFlg.equalsIgnoreCase("I")) {

					DecimalFormat myformatter = new DecimalFormat(
					"############");
					String type = objEstampBO.gettype(userId);      //--added by satbir kumar-- 
					
					logger.debug(type);
					
					if("3".equalsIgnoreCase(type)||"4".equalsIgnoreCase(type)||"7".equalsIgnoreCase(type)||"5".equalsIgnoreCase(type))
					{
						ArrayList details = objEstampBO.getDetails(userId,objEstampDto,language);
						
						if(details!=null)
						{
							logger.debug(estampFormBean.getObjEstampDet()
									.getDistrictid());
							logger.debug(estampFormBean.getObjEstampDet()
									.getDistrictname());
							
							request.setAttribute("parentDistrictId", estampFormBean.getObjEstampDet()
									.getDistrictid());
							
							request.setAttribute("parentDistrictName", estampFormBean.getObjEstampDet()
									.getDistrictname());
							request.setAttribute("parentOfficeId", "NA");
							request.setAttribute("parentOfficeName", "NA");
							request.setAttribute("parentReferenceId", estampFormBean.getObjEstampDet().getMainTxnId().toString());
						}
					}
					
					if("2".equalsIgnoreCase(type))
					{
						String txnId=estampFormBean.getObjEstampDet().getMainTxnId().toString();
						ArrayList details = objEstampBO.getruDetails(userId,objEstampDto,txnId,language);
						
						if(details!=null)
						{
							request.setAttribute("parentDistrictId", estampFormBean.getObjEstampDet()
									.getDistrictid());
							
							request.setAttribute("parentDistrictName", estampFormBean.getObjEstampDet()
									.getDistrictname());
							request.setAttribute("parentOfficeId", "NA");
							request.setAttribute("parentOfficeName", "NA");
							request.setAttribute("parentReferenceId", estampFormBean.getObjEstampDet().getMainTxnId().toString());
						}
					}
						
					
						if("I".equalsIgnoreCase(type))
						{
							String offcId = (String) session.getAttribute("loggedToOffice");
							ArrayList iudetails = objEstampBO.getiuDetails(objEstampDto,offcId,language);
							
							if(iudetails!=null)
							{
								request.setAttribute("parentDistrictId", estampFormBean.getObjEstampDet()
										.getDistrictid());
								
								request.setAttribute("parentDistrictName", estampFormBean.getObjEstampDet()
										.getDistrictname());
								request.setAttribute("parentOfficeId", estampFormBean.getObjEstampDet().getIuofficeid() );
								request.setAttribute("parentOfficeName", estampFormBean.getObjEstampDet().getIuofficename());
								request.setAttribute("parentReferenceId", estampFormBean.getObjEstampDet().getMainTxnId().toString());
							}
							
					
					}
						
					request.setAttribute("parentModName", "E-Stamping");
					request.setAttribute("parentFunName","E-Stamp Judicial");
					request.setAttribute("parentFunId", "FUN_022");
					request.setAttribute("parentAmount", myformatter.format(damtToBePaid));
					request.setAttribute("parentTable","IGRS_ESTAMP_PAYMENT_DETLS");
					request.setAttribute("parentKey", uniqId);
					request.setAttribute("forwardPath","./estampJudicialNew.do?TRFS=NGI");
					request.setAttribute("parentColumnName","ESTAMP_PAYMENT_ID");
					request.setAttribute("formName","estampFormBean");
					forwardJsp = "nextToPay";
				}

				if (pymtFlg.equalsIgnoreCase("P")) {

					double dpaidAmt = Double.parseDouble(paidAmt);
					// condition 1-------------------pending
					// amount-------------
					if (damtToBePaid > dpaidAmt) {
						double bal = damtToBePaid - dpaidAmt;
						boolean insrt = false;
						estampFormBean.getObjEstampDet().setTotal(bal);
						try {
							logger.debug("NEXT_TO_PAYMENT_ACTION_DASHBOARD :: else block :: pymtFlg =  P ");
							insrt = objEstampBO.insertPay(estampFormBean, objEstampDto);

							if (insrt) {
								
								logger
								.debug("inside if insrt-----of else condition---means record found");
								String type = objEstampBO.gettype(userId);      //--added by satbir kumar-- 
								
								logger.debug(type);
								
								if("3".equalsIgnoreCase(type)||"4".equalsIgnoreCase(type)||"7".equalsIgnoreCase(type)||"5".equalsIgnoreCase(type))
								{
									ArrayList details = objEstampBO.getDetails(userId,objEstampDto,language);
									
									if(details!=null)
									{
										logger.debug(estampFormBean.getObjEstampDet()
												.getDistrictid());
										logger.debug(estampFormBean.getObjEstampDet()
												.getDistrictname());
										
										request.setAttribute("parentDistrictId", estampFormBean.getObjEstampDet()
												.getDistrictid());
										
										request.setAttribute("parentDistrictName", estampFormBean.getObjEstampDet()
												.getDistrictname());
										request.setAttribute("parentOfficeId", "NA");
										request.setAttribute("parentOfficeName", "NA");
										request.setAttribute("parentReferenceId", estampFormBean.getObjEstampDet().getMainTxnId().toString());
									}
								}
								
								if("2".equalsIgnoreCase(type))
								{
									String txnId=estampFormBean.getObjEstampDet().getMainTxnId().toString();
									ArrayList details = objEstampBO.getruDetails(userId,objEstampDto,txnId,language);
									
									if(details!=null)
									{
										request.setAttribute("parentDistrictId", estampFormBean.getObjEstampDet()
												.getDistrictid());
										
										request.setAttribute("parentDistrictName", estampFormBean.getObjEstampDet()
												.getDistrictname());
										request.setAttribute("parentOfficeId", "NA");
										request.setAttribute("parentOfficeName", "NA");
										request.setAttribute("parentReferenceId", estampFormBean.getObjEstampDet().getMainTxnId().toString());
									}
								}
									
								
									if("I".equalsIgnoreCase(type))
									{
										String offcId = (String) session.getAttribute("loggedToOffice");
										ArrayList iudetails = objEstampBO.getiuDetails(objEstampDto,offcId,language);
										
										if(iudetails!=null)
										{
											request.setAttribute("parentDistrictId", estampFormBean.getObjEstampDet()
													.getDistrictid());
											
											request.setAttribute("parentDistrictName", estampFormBean.getObjEstampDet()
													.getDistrictname());
											request.setAttribute("parentOfficeId", estampFormBean.getObjEstampDet().getIuofficeid() );
											request.setAttribute("parentOfficeName", estampFormBean.getObjEstampDet().getIuofficename());
											request.setAttribute("parentReferenceId", estampFormBean.getObjEstampDet().getMainTxnId().toString());
										}
								
								
								}
								DecimalFormat myformatter = new DecimalFormat(
								"############");
								request.setAttribute("parentModName","E-Stamping");
								request.setAttribute("parentFunName","E-Stamp Judicial");
								request.setAttribute("parentFunId","FUN_022");
								request.setAttribute("parentAmount",myformatter.format(estampFormBean.getObjEstampDet().getTotal()));
								request.setAttribute("parentTable","IGRS_ESTAMP_PAYMENT_DETLS");
								request.setAttribute("parentKey", estampFormBean.getObjEstampDet().getUniqKey());
								request.setAttribute("forwardPath","./estampJudicialNew.do?TRFS=NGI");
								request.setAttribute("parentColumnName","ESTAMP_PAYMENT_ID");
								request.setAttribute("formName","estampFormBean");
								logger.debug("just before redirection");
								forwardJsp = "nextToPay";

							} else {
								forwardJsp = "failure";
							}
						} catch (Exception e) {
							forwardJsp = "failure";
						}

					}

					// condition 2-------------------full payment, eCode
					// generation------
					if (damtToBePaid == dpaidAmt) {
						DecimalFormat myformatter = new DecimalFormat(
						"############");
						double bal = damtToBePaid - dpaidAmt;
						// duty//
						//String duty = bo.getDuty(TxnRequestId);
						//eForm.getDutyCalculationDTO().setTotal(
								//Double.parseDouble(duty));
						//eForm.getDutyCalculationDTO().setTotalDisplay(
								//myformatter
								//.format(Double.parseDouble(duty)));
						// date and time//
						String currDate = objEstampBO.getCurrentDate();
						estampFormBean.getObjEstampDet().setCurrentDate(currDate);
						// deeds and instruments and purpose//
						/*ArrayList second = new ArrayList();
						ArrayList comp1 = new ArrayList();
						second = objEstampBO.getDeedDtls(TxnRequestId);
						if (second.size() > 0) {
							for (int i = 0; i < second.size(); i++) {
								comp1.add((ArrayList) second.get(i));
								if (comp1 != null && comp1.size() > 0) {
									for (int k = 0; k < comp1.size(); k++) {
										ArrayList compSub = (ArrayList) comp1
										.get(k);
										String deed = (String) compSub
										.get(0);
										String inst = (String) compSub
										.get(1);
										String purpose = (String) compSub
										.get(2);
										logger
										.debug("the deed is.........................."
												+ deed);
										logger
										.debug("the inst is.........................."
												+ inst);
										logger
										.debug("the purpose is.........................."
												+ purpose);
										eForm.getDutyCalculationDTO()
										.setDeedType(deed);
										eForm.getInstDTO()
										.setInstType(inst);
										eForm.setEstampPurpose(purpose);
									}
								}
							}
						}*/
						// names and office details//
						
						String type = objEstampBO.gettype(userId);      //--added by satbir kumar-- 
						
						logger.debug(type);
						
						/*String roleId = objEstampBO.getRole(userId);
						String srrole = "GR1356609170484";
						String drrole = "GR1356671177515";
						String sprole = "GR1358488496205";
						String rurole = "GR1357368369828";
						String spbankrole = "GR1359453019113";
						
						estampFormBean.getObjEstampDet().setRole(roleId);*/

						if ("2".equalsIgnoreCase(type)) {
							ArrayList userdet = new ArrayList();
							ArrayList comp2 = new ArrayList();
							userdet = objEstampBO.getruName(userId,language);
							if (userdet.size() > 0) {
								for (int i = 0; i < userdet.size(); i++) {
									comp2.add((ArrayList) userdet.get(i));
									if (comp2 != null && comp2.size() > 0) {
										for (int k = 0; k < comp2.size(); k++) {
											ArrayList compSub = (ArrayList) comp2
											.get(k);

											String name = (String) compSub
											.get(0);
											String cid = (String) compSub
											.get(1);
											String sid = (String) compSub
											.get(2);
											String did = (String) compSub
											.get(3);
											String dname = (String) compSub
											.get(4);

											estampFormBean.getObjEstampDet().setUserName(name);
											if (cid.equalsIgnoreCase("1")) {
												if (sid
														.equalsIgnoreCase("1")) {
													estampFormBean.getObjEstampDet()
													.setIssuedPlace(dname);

												} else {
													
													if("en".equalsIgnoreCase(language))
													{	
														estampFormBean.getObjEstampDet()
														.setIssuedPlace("Others");
													}
													else
													{
														estampFormBean.getObjEstampDet()
														.setIssuedPlace("अन्य");	
													}
												}

											} else {
												if("en".equalsIgnoreCase(language))
												{	
													estampFormBean.getObjEstampDet()
													.setIssuedPlace("Others");
												}
												else
												{
													estampFormBean.getObjEstampDet()
													.setIssuedPlace("अन्य");	
												}
											}

										}
									}
								}
							}

							estampFormBean.getObjEstampDet().setOfficeName("-");
						}

						else if ("3".equalsIgnoreCase(type)) {
							String spname = objEstampBO.getspName(userId);
							String licNo= new DutyCalculationBO().getspLicenseNo(userId);
							ArrayList spdtls = new ArrayList();
							ArrayList comp3 = new ArrayList();
							spdtls = objEstampBO.getspDtls(userId,language);
							if (spdtls.size() > 0) {
								for (int i = 0; i < spdtls.size(); i++) {
									comp3.add((ArrayList) spdtls.get(i));
									if (comp3 != null && comp3.size() > 0) {
										for (int k = 0; k < comp3.size(); k++) {
											ArrayList compSub = (ArrayList) comp3
											.get(k);

											String ofc = (String) compSub
											.get(0);
											String plc = (String) compSub
											.get(1);
											String tehsil = (String) compSub
											.get(2);
											estampFormBean.getObjEstampDet().setOfficeName(ofc+" "+tehsil+" "+plc);
											estampFormBean.getObjEstampDet().setIssuedPlace(plc);
											estampFormBean.getObjEstampDet().setUserName(spname+"/"+licNo);
										}
									}
								}

							}
						}

						else if("4".equalsIgnoreCase(type)||"7".equalsIgnoreCase(type)||"5".equalsIgnoreCase(type))
						{

							ArrayList spbankdtls = new ArrayList();
							ArrayList comp4 = new ArrayList();
							spbankdtls = objEstampBO.getspbnkDtls(userId,language);
							if (spbankdtls.size() > 0) {
								for (int i = 0; i < spbankdtls.size(); i++) {
									comp4
									.add((ArrayList) spbankdtls
											.get(i));
									if (comp4 != null && comp4.size() > 0) {
										for (int k = 0; k < comp4.size(); k++) {
											ArrayList compSub = (ArrayList) comp4
											.get(k);

											String uname = (String) compSub
											.get(0);
											String ofc = (String) compSub
											.get(1);
											String plc = (String) compSub
											.get(2);
											String tehsil = (String) compSub
											.get(3);
											estampFormBean.getObjEstampDet().setOfficeName(ofc+" "+tehsil+" "+plc);
											estampFormBean.getObjEstampDet().setIssuedPlace(plc);
											estampFormBean.getObjEstampDet().setUserName(uname);
										}
									}
								}

							}
						}

						else if("I".equalsIgnoreCase(type))
						{
							ArrayList iudtls = new ArrayList();
							ArrayList comp4 = new ArrayList();
							String officeId = (String)session.getAttribute("loggedToOffice");
							iudtls = objEstampBO.getiuDtls(userId,officeId,language);
							if (iudtls.size() > 0) {
								for (int i = 0; i < iudtls.size(); i++) {
									comp4.add((ArrayList) iudtls.get(i));
									if (comp4 != null && comp4.size() > 0) {
										for (int k = 0; k < comp4.size(); k++) {
											ArrayList compSub = (ArrayList) comp4
											.get(k);

											String ofc = (String) compSub
											.get(0);
											String plc = (String) compSub
											.get(1);
											String uname = (String) compSub
											.get(2);
											estampFormBean.getObjEstampDet().setOfficeName(ofc);
											estampFormBean.getObjEstampDet().setIssuedPlace(plc);
											estampFormBean.getObjEstampDet().setUserName(uname);
										}
									}
								}

							}
						}

						// party details--applicant//
						ArrayList appdtls = new ArrayList();
						ArrayList comp5 = new ArrayList();
						appdtls = objEstampBO.getAppDtls(TxnRequestId,language);
						if (appdtls.size() > 0) {
							for (int i = 0; i < appdtls.size(); i++) {
								comp5.add((ArrayList) appdtls.get(i));
								if (comp5 != null && comp5.size() > 0) {
									for (int k = 0; k < comp5.size(); k++) {
										ArrayList compSub = (ArrayList) comp5
										.get(k);

										String name = (String) compSub
										.get(0);
										String cntry = (String) compSub
										.get(1);
										String state = (String) compSub
										.get(2);
										String district = (String) compSub
										.get(3);
										String addrs = (String) compSub
										.get(4);
										String fathrNme = (String) compSub
										.get(5);
										String noOfPrsns = (String) compSub
										.get(6);
										String orgName = (String) compSub
										.get(7);
										String disId = (String) compSub
										.get(8);

										if (orgName == null || orgName.equalsIgnoreCase("")) {
											estampFormBean.getObjEstampDet().setAppNamedsply(name);
											estampFormBean.getObjEstampDet().setAppCountryName(cntry);
											estampFormBean.getObjEstampDet().setAppStateName(state);
											estampFormBean.getObjEstampDet()
											.setAppDistrictName(district);
											estampFormBean.getObjEstampDet().setAppAddress(addrs);
											estampFormBean.getObjEstampDet()
											.setAppFatherName(fathrNme);
											estampFormBean.getObjEstampDet().setAppPersons(noOfPrsns);
											estampFormBean.getObjEstampDet().setAppDistrict(disId);
										} else {
											estampFormBean.getObjEstampDet().setAppNamedsply(orgName);
											estampFormBean.getObjEstampDet().setAppCountryName(cntry);
											estampFormBean.getObjEstampDet().setAppStateName(state);
											estampFormBean.getObjEstampDet()
											.setAppDistrictName(district);
											estampFormBean.getObjEstampDet().setAppAddress(addrs);
											estampFormBean.getObjEstampDet()
											.setAppAuthPersonName(name);
											estampFormBean.getObjEstampDet().setAppPersons(noOfPrsns);
											estampFormBean.getObjEstampDet().setAppDistrict(disId);
											estampFormBean.getObjEstampDet().setIsAuthapp(1);

										}

									}
								}
							}

						}

						// party details--party//
						ArrayList partydtls = new ArrayList();
						ArrayList comp6 = new ArrayList();
						partydtls = objEstampBO.getPartyDtls(TxnRequestId,language);
						if (partydtls.size() > 0) {
							for (int i = 0; i < partydtls.size(); i++) {
								comp6.add((ArrayList) partydtls.get(i));
								if (comp6 != null && comp6.size() > 0) {
									for (int k = 0; k < comp6.size(); k++) {
										ArrayList compSub = (ArrayList) comp6
										.get(k);

										String name = (String) compSub
										.get(0);
										String cntry = (String) compSub
										.get(1);
										String state = (String) compSub
										.get(2);
										String district = (String) compSub
										.get(3);
										String addrs = (String) compSub
										.get(4);
										String fathrNme = (String) compSub
										.get(5);
										String noOfPrsns = (String) compSub
										.get(6);
										String orgName = (String) compSub
										.get(7);

										if (orgName == null || orgName.equalsIgnoreCase("")) {
											estampFormBean.getObjEstampDet().setPartyNamedsply(name);
											estampFormBean.getObjEstampDet()
											.setPartyCountryName(cntry);
											estampFormBean.getObjEstampDet().setPartyStateName(state);
											estampFormBean.getObjEstampDet()
											.setPartyDistrictName(district);
											estampFormBean.getObjEstampDet().setPartyAddress(addrs);
											estampFormBean.getObjEstampDet()
											.setPartyFatherName(fathrNme);
											estampFormBean.getObjEstampDet()
											.setPartyPersons(noOfPrsns);
										} else {
											estampFormBean.getObjEstampDet()
											.setPartyNamedsply(orgName);
											estampFormBean.getObjEstampDet()
											.setPartyCountryName(cntry);
											estampFormBean.getObjEstampDet().setPartyStateName(state);
											estampFormBean.getObjEstampDet()
											.setPartyDistrictName(district);
											estampFormBean.getObjEstampDet().setPartyAddress(addrs);
											estampFormBean.getObjEstampDet()
											.setPartyAuthPersonName(name);
											estampFormBean.getObjEstampDet()
											.setPartyPersons(noOfPrsns);
											estampFormBean.getObjEstampDet().setIsAuthparty(1);

										}

									}
								}
							}

						} else {
							estampFormBean.getObjEstampDet().setPartyNamedsply("-");
							estampFormBean.getObjEstampDet().setPartyCountryName("-");
							estampFormBean.getObjEstampDet().setPartyStateName("-");
							estampFormBean.getObjEstampDet().setPartyDistrictName("-");
							estampFormBean.getObjEstampDet().setPartyAddress("-");
							estampFormBean.getObjEstampDet().setPartyFatherName("-");
							estampFormBean.getObjEstampDet().setPartyPersons("-");

						}
						// generation of e-code and insertion into the
						// tables.
						try {
							boolean boo = false;
							boo = objEstampBO.inserteCode(estampFormBean, objEstampDto);

							if (boo) {

								ArrayList ecodedtls = new ArrayList();
								ArrayList comp7 = new ArrayList();
								ecodedtls = objEstampBO.getEcodeDtls(TxnRequestId);
								if (ecodedtls.size() > 0) {
									for (int i = 0; i < ecodedtls.size(); i++) {
										comp7.add((ArrayList) ecodedtls
												.get(i));
										if (comp7 != null
												&& comp7.size() > 0) {
											for (int k = 0; k < comp7
											.size(); k++) {
												ArrayList compSub = (ArrayList) comp7
												.get(k);

												String ecode = (String) compSub
												.get(0);
												String amt = (String) compSub
												.get(1);
												String estampType = "";
												if(language.equalsIgnoreCase("en"))
												{
													estampType = (String) compSub
													.get(2);
												}
												else
												{
												//	estampType = (String) compSub
													//.get(8);
													String Name = "न्यायिक";
													estampType = Name;
												}
												
												String estampDate = (String) compSub
												.get(3);
												String issuedBy = (String) compSub
												.get(4);
												String offc = (String) compSub
												.get(5);
												String place = (String) compSub
												.get(6);
												String validityDt = (String) compSub
												.get(7);
												estampFormBean.getObjEstampDet().setEcode(ecode);
												estampFormBean.getObjEstampDet().setAmount(amt);
												estampFormBean.getObjEstampDet()
												.setEstampType(estampType);
												estampFormBean.getObjEstampDet()
												.setCurrentDate(estampDate);
												estampFormBean.getObjEstampDet().setUserName(issuedBy);
												estampFormBean.getObjEstampDet().setOfficeName(offc);
												estampFormBean.getObjEstampDet().setIssuedPlace(place);
												estampFormBean.getObjEstampDet()
												.setEstampValidity(validityDt);

												forwardJsp = "ecodePage";

											}
										}
									}

								}
							} else {
								forwardJsp = "failure";
							}

						} catch (Exception e) {

						}

					}// --------end second condition

				}// -------end paymnent flag=P

				if (pymtFlg.equalsIgnoreCase("C")) {

					DecimalFormat myformatter = new DecimalFormat(
					"############");
					// double bal= damtToBePaid-dpaidAmt;
					// duty//
					//String duty = bo.getDuty(TxnRequestId);
					//eForm.getDutyCalculationDTO().setTotal(
							//Double.parseDouble(duty));
					//eForm.getDutyCalculationDTO().setTotalDisplay(
							//myformatter.format(Double.parseDouble(duty)));
					// date and time//
					String currDate = objEstampBO.getCurrentDate();
					estampFormBean.getObjEstampDet().setCurrentDate(currDate);
					// deeds and instruments and purpose//
					/*ArrayList second = new ArrayList();
					ArrayList comp1 = new ArrayList();
					second = bo.getDeedDtls(TxnRequestId);
					if (second.size() > 0) {
						for (int i = 0; i < second.size(); i++) {
							comp1.add((ArrayList) second.get(i));
							if (comp1 != null && comp1.size() > 0) {
								for (int k = 0; k < comp1.size(); k++) {
									ArrayList compSub = (ArrayList) comp1
									.get(k);
									String deed = (String) compSub.get(0);
									String inst = (String) compSub.get(1);
									String purpose = (String) compSub
									.get(2);
									logger
									.debug("the deed is.........................."
											+ deed);
									logger
									.debug("the inst is.........................."
											+ inst);
									logger
									.debug("the purpose is.........................."
											+ purpose);
									eForm.getDutyCalculationDTO()
									.setDeedType(deed);
									eForm.getInstDTO().setInstType(inst);
									eForm.setEstampPurpose(purpose);
								}
							}
						}
					}*/
					// names and office details//
	String type = objEstampBO.gettype(userId);      //--added by satbir kumar-- 
					
					logger.debug(type);
					/*String roleId = objEstampBO.getRole(userId);
					String srrole = "GR1356609170484";
					String drrole = "GR1356671177515";
					String sprole = "GR1358488496205";
					String rurole = "GR1357368369828";
					String spbankrole = "GR1359453019113";
					
					estampFormBean.getObjEstampDet().setRole(roleId);
*/
					if ("2".equalsIgnoreCase(type)) {
						ArrayList userdet = new ArrayList();
						ArrayList comp2 = new ArrayList();
						userdet = objEstampBO.getruName(userId,language);
						if (userdet.size() > 0) {
							for (int i = 0; i < userdet.size(); i++) {
								comp2.add((ArrayList) userdet.get(i));
								if (comp2 != null && comp2.size() > 0) {
									for (int k = 0; k < comp2.size(); k++) {
										ArrayList compSub = (ArrayList) comp2
										.get(k);

										String name = (String) compSub
										.get(0);
										String cid = (String) compSub
										.get(1);
										String sid = (String) compSub
										.get(2);
										String did = (String) compSub
										.get(3);
										String dname = (String) compSub
										.get(4);

										estampFormBean.getObjEstampDet().setUserName(name);
										if (cid.equalsIgnoreCase("1")) {
											if (sid.equalsIgnoreCase("1")) {
												estampFormBean.getObjEstampDet().setIssuedPlace(dname);

											} else {
												if("en".equalsIgnoreCase(language))
												{	
													estampFormBean.getObjEstampDet()
													.setIssuedPlace("Others");
												}
												else
												{
													estampFormBean.getObjEstampDet()
													.setIssuedPlace("अन्य");	
												}
											}

										} else {
											if("en".equalsIgnoreCase(language))
											{	
												estampFormBean.getObjEstampDet()
												.setIssuedPlace("Others");
											}
											else
											{
												estampFormBean.getObjEstampDet()
												.setIssuedPlace("अन्य");	
											}										}

									}
								}
							}
						}

						estampFormBean.getObjEstampDet().setOfficeName("-");
					}

					else if ("3".equalsIgnoreCase(type)) {
						String spname = objEstampBO.getspName(userId);
						String licNo= new DutyCalculationBO().getspLicenseNo(userId);
						ArrayList spdtls = new ArrayList();
						ArrayList comp3 = new ArrayList();
						spdtls = objEstampBO.getspDtls(userId,language);
						if (spdtls.size() > 0) {
							for (int i = 0; i < spdtls.size(); i++) {
								comp3.add((ArrayList) spdtls.get(i));
								if (comp3 != null && comp3.size() > 0) {
									for (int k = 0; k < comp3.size(); k++) {
										ArrayList compSub = (ArrayList) comp3
										.get(k);

										String ofc = (String) compSub
										.get(0);
										String plc = (String) compSub
										.get(1);
										String tehsil = (String) compSub
										.get(2);
										estampFormBean.getObjEstampDet().setOfficeName(ofc+" "+tehsil+" "+plc);
										estampFormBean.getObjEstampDet().setIssuedPlace(plc);
										estampFormBean.getObjEstampDet().setUserName(spname+"/"+licNo);
									}
								}
							}

						}
					}

					else if("4".equalsIgnoreCase(type)||"7".equalsIgnoreCase(type)||"5".equalsIgnoreCase(type))
					{

						ArrayList spbankdtls = new ArrayList();
						ArrayList comp4 = new ArrayList();
						spbankdtls = objEstampBO.getspbnkDtls(userId,language);
						if (spbankdtls.size() > 0) {
							for (int i = 0; i < spbankdtls.size(); i++) {
								comp4.add((ArrayList) spbankdtls.get(i));
								if (comp4 != null && comp4.size() > 0) {
									for (int k = 0; k < comp4.size(); k++) {
										ArrayList compSub = (ArrayList) comp4
										.get(k);

										String uname = (String) compSub
										.get(0);
										String ofc = (String) compSub
										.get(1);
										String plc = (String) compSub
										.get(2);
										String tehsil = (String) compSub
										.get(3);
										estampFormBean.getObjEstampDet().setOfficeName(ofc+" "+tehsil+" "+plc);
										estampFormBean.getObjEstampDet().setIssuedPlace(plc);
										estampFormBean.getObjEstampDet().setUserName(uname);
									}
								}
							}

						}
					}

					else if("I".equalsIgnoreCase(type))
					{
						ArrayList iudtls = new ArrayList();
						ArrayList comp4 = new ArrayList();
						String officeId = (String)session.getAttribute("loggedToOffice");
						iudtls = objEstampBO.getiuDtls(userId,officeId,language);
						if (iudtls.size() > 0) {
							for (int i = 0; i < iudtls.size(); i++) {
								comp4.add((ArrayList) iudtls.get(i));
								if (comp4 != null && comp4.size() > 0) {
									for (int k = 0; k < comp4.size(); k++) {
										ArrayList compSub = (ArrayList) comp4
										.get(k);

										String ofc = (String) compSub
										.get(0);
										String plc = (String) compSub
										.get(1);
										String uname = (String) compSub
										.get(2);
										estampFormBean.getObjEstampDet().setOfficeName(ofc);
										estampFormBean.getObjEstampDet().setIssuedPlace(plc);
										estampFormBean.getObjEstampDet().setUserName(uname);
									}
								}
							}

						}
					}

					// party details--applicant//
					ArrayList appdtls = new ArrayList();
					ArrayList comp5 = new ArrayList();
					appdtls = objEstampBO.getAppDtls(TxnRequestId,language);
					if (appdtls.size() > 0) {
						for (int i = 0; i < appdtls.size(); i++) {
							comp5.add((ArrayList) appdtls.get(i));
							if (comp5 != null && comp5.size() > 0) {
								for (int k = 0; k < comp5.size(); k++) {
									ArrayList compSub = (ArrayList) comp5
									.get(k);

									String name = (String) compSub.get(0);
									String cntry = (String) compSub.get(1);
									String state = (String) compSub.get(2);
									String district = (String) compSub
									.get(3);
									String addrs = (String) compSub.get(4);
									String fathrNme = (String) compSub
									.get(5);
									String noOfPrsns = (String) compSub
									.get(6);
									String orgName = (String) compSub
									.get(7);
									String disId = (String) compSub.get(8);

									if (orgName == null || orgName.equalsIgnoreCase("")) {
										estampFormBean.getObjEstampDet().setAppNamedsply(name);
										estampFormBean.getObjEstampDet().setAppCountryName(cntry);
										estampFormBean.getObjEstampDet().setAppStateName(state);
										estampFormBean.getObjEstampDet().setAppDistrictName(district);
										estampFormBean.getObjEstampDet().setAppAddress(addrs);
										estampFormBean.getObjEstampDet().setAppFatherName(fathrNme);
										estampFormBean.getObjEstampDet().setAppPersons(noOfPrsns);
										estampFormBean.getObjEstampDet().setAppDistrict(disId);
									} else {
										estampFormBean.getObjEstampDet().setAppNamedsply(orgName);
										estampFormBean.getObjEstampDet().setAppCountryName(cntry);
										estampFormBean.getObjEstampDet().setAppStateName(state);
										estampFormBean.getObjEstampDet().setAppDistrictName(district);
										estampFormBean.getObjEstampDet().setAppAddress(addrs);
										estampFormBean.getObjEstampDet().setAppAuthPersonName(name);
										estampFormBean.getObjEstampDet().setAppPersons(noOfPrsns);
										estampFormBean.getObjEstampDet().setAppDistrict(disId);
										estampFormBean.getObjEstampDet().setIsAuthapp(1);

									}

								}
							}
						}

					}

					// party details--party//
					ArrayList partydtls = new ArrayList();
					ArrayList comp6 = new ArrayList();
					partydtls = objEstampBO.getPartyDtls(TxnRequestId,language);
					if (partydtls.size() > 0) {
						for (int i = 0; i < partydtls.size(); i++) {
							comp6.add((ArrayList) partydtls.get(i));
							if (comp6 != null && comp6.size() > 0) {
								for (int k = 0; k < comp6.size(); k++) {
									ArrayList compSub = (ArrayList) comp6
									.get(k);

									String name = (String) compSub.get(0);
									String cntry = (String) compSub.get(1);
									String state = (String) compSub.get(2);
									String district = (String) compSub
									.get(3);
									String addrs = (String) compSub.get(4);
									String fathrNme = (String) compSub
									.get(5);
									String noOfPrsns = (String) compSub
									.get(6);
									String orgName = (String) compSub
									.get(7);

									if (orgName == null || orgName.equalsIgnoreCase("")) {
										estampFormBean.getObjEstampDet().setPartyNamedsply(name);
										estampFormBean.getObjEstampDet().setPartyCountryName(cntry);
										estampFormBean.getObjEstampDet().setPartyStateName(state);
										estampFormBean.getObjEstampDet()
										.setPartyDistrictName(district);
										estampFormBean.getObjEstampDet().setPartyAddress(addrs);
										estampFormBean.getObjEstampDet().setPartyFatherName(fathrNme);
										estampFormBean.getObjEstampDet().setPartyPersons(noOfPrsns);
									} else {
										estampFormBean.getObjEstampDet().setPartyNamedsply(orgName);
										estampFormBean.getObjEstampDet().setPartyCountryName(cntry);
										estampFormBean.getObjEstampDet().setPartyStateName(state);
										estampFormBean.getObjEstampDet()
										.setPartyDistrictName(district);
										estampFormBean.getObjEstampDet().setPartyAddress(addrs);
										estampFormBean.getObjEstampDet().setPartyAuthPersonName(name);
										estampFormBean.getObjEstampDet().setPartyPersons(noOfPrsns);
										estampFormBean.getObjEstampDet().setIsAuthparty(1);

									}

								}
							}
						}

					} else {
						estampFormBean.getObjEstampDet().setPartyNamedsply("-");
						estampFormBean.getObjEstampDet().setPartyCountryName("-");
						estampFormBean.getObjEstampDet().setPartyStateName("-");
						estampFormBean.getObjEstampDet().setPartyDistrictName("-");
						estampFormBean.getObjEstampDet().setPartyAddress("-");
						estampFormBean.getObjEstampDet().setPartyFatherName("-");
						estampFormBean.getObjEstampDet().setPartyPersons("-");

					}
					// e-code details.

					ArrayList ecodedtls = new ArrayList();
					ArrayList comp7 = new ArrayList();
					ecodedtls = objEstampBO.getEcodeDtls(TxnRequestId);
					if (ecodedtls.size() > 0) {
						for (int i = 0; i < ecodedtls.size(); i++) {
							comp7.add((ArrayList) ecodedtls.get(i));
							if (comp7 != null && comp7.size() > 0) {
								for (int k = 0; k < comp7.size(); k++) {
									ArrayList compSub = (ArrayList) comp7
									.get(k);

									String ecode = (String) compSub.get(0);
									String amt = (String) compSub.get(1);
									String estampType = "";
									if(language.equalsIgnoreCase("en"))
									{
										estampType = (String) compSub
										.get(2);
									}
									else
									{
										//estampType = (String) compSub
										//.get(8);
										String Name = "न्यायिक";
										estampType = Name;
									}
									
									String estampDate = (String) compSub
									.get(3);
									String issuedBy = (String) compSub
									.get(4);
									String offc = (String) compSub.get(5);
									String place = (String) compSub.get(6);
									String validityDt = (String) compSub
									.get(7);
									estampFormBean.getObjEstampDet().setEcode(ecode);
									estampFormBean.getObjEstampDet().setAmount(amt);
									estampFormBean.getObjEstampDet().setEstampType(estampType);
									estampFormBean.getObjEstampDet().setCurrentDate(estampDate);
									estampFormBean.getObjEstampDet().setUserName(issuedBy);
									estampFormBean.getObjEstampDet().setOfficeName(offc);
									estampFormBean.getObjEstampDet().setIssuedPlace(place);
									estampFormBean.getObjEstampDet().setEstampValidity(validityDt);

									forwardJsp = "ecodePage";

								}
							}
						}

					}

				}// end payment flag=C

			}// -----------------end else condition--means record found.

		}
			if ("updateAction".equalsIgnoreCase(actionName)) {

				try {
					estampFormBean.getObjEstampDet().setErrMsg(0);
					String updatedCourtValue = estampFormBean.getObjEstampDet().getCourt();
					String courtArr[] = updatedCourtValue.split("::");
					estampFormBean.getObjEstampDet().setCourtName(courtArr[1]);
					if(estampFormBean.getObjEstampDet().getCourtType().trim().equals("D")){
						if("en".equalsIgnoreCase(language)){
							estampFormBean.getObjEstampDet().setCourtType("District");
						}else{
							estampFormBean.getObjEstampDet().setCourtType("जिला");
						}
						
					}else{
						if("en".equalsIgnoreCase(language)){
							estampFormBean.getObjEstampDet().setCourtType("Tehsil");
						}else{
							estampFormBean.getObjEstampDet().setCourtType("तहसील");
						}
					}
					logger.debug("inside insert action try");
					boolean updatetxn = false;
					
					//added by Lavi for scanner integration of internal user
					if(estampFormBean.getObjEstampDet().getDoc().toString().equalsIgnoreCase("")==false)
					{
					if(Integer.toString(estampFormBean.getObjEstampDet().getIsInternalUser()).equalsIgnoreCase("1"))
					{
						logger.debug("Document Name" +estampFormBean.getObjEstampDet().getDoc());
						estampFormBean.getObjEstampDet().setDoc(estampFormBean.getObjEstampDet().getFileNameScan());
						estampFormBean.getObjEstampDet().setDocPath(estampFormBean.getObjEstampDet().getParentPathScan());
					}
					//end of code added by Lavi for scanner integration of internal user
					else
					{
					//addition by satbir kumar
					if((estampFormBean.getObjEstampDet().getDoc()!=null)||(!((String) estampFormBean.getObjEstampDet().getDoc()).equalsIgnoreCase("")))
					{
					FormFile photo = estampFormBean.getObjEstampDet().getImage();
					String docname = estampFormBean.getObjEstampDet().getDoc().toString();
				//	downloadPath=downloadPath+File.separator+"IGRS";
					String docPath = downloadPath+File.separator+"04"+File.separator+estampFormBean.getObjEstampDet().getMainTxnId().toString();
					estampFormBean.getObjEstampDet().setDocPath(docPath);
					boolean up=uploadFile(photo,docname,docPath);
					}
					//end of addition by satbir kumar
					}
					}
					
					updatetxn = objEstampBO.updateTxn(estampFormBean,language);
					
					
					logger.debug("inside insert action-after insert");
					if (updatetxn) {
						String maintxnid = estampFormBean.getObjEstampDet().getMainTxnId().toString();
						logger
						.debug("the main transaction id after insertion is........"
								+ maintxnid);
						if (estampFormBean.getObjEstampDet().getAmount().toString().equalsIgnoreCase("0")){
							estampFormBean.getObjEstampDet().setPay(0);
						logger.debug("----------->"+estampFormBean.getObjEstampDet().getPay());
						}
							else {
								estampFormBean.getObjEstampDet().setPay(1);
						logger.debug("----------->"+estampFormBean.getObjEstampDet().getPay());
							}
						
                         estampFormBean.getObjEstampDet().setTotal((Double.parseDouble((String) estampFormBean.getObjEstampDet().getAmount())));
						
                         boolean flag = objEstampBO.updatePay(estampFormBean, objEstampDto);
                         if(flag){
						boolean boo = objEstampBO.insertPay(estampFormBean, objEstampDto);
                         }
						forwardJsp = CommonConstant.FORWARD_PAGE_VIEW;
						objEstampDto.setActionName(null);
						actionName = "";
						estampFormBean.getObjEstampDet().setActionName(null);
						estampFormBean.getObjEstampDet().setActionName("");
					} else {

						estampFormBean.getObjEstampDet().setErrMsg(1);
						forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
					}

				} catch (Exception e) {
					e.printStackTrace();
					estampFormBean.getObjEstampDet().setErrMsg(1);
					forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;

				}
			}
			
			if (request.getParameter("actionName")!=null)
			  {
				  if (request.getParameter("actionName").equalsIgnoreCase("downLoad1"))
				  {
					  try
					  {
					  byte contents[] = estampFormBean.getObjEstampDet().getImage1();
					  String fileName = estampFormBean.getObjEstampDet().getDoc().toString();
					  downloadDocument(response, contents, fileName);
					  }
					  catch (Exception e)
					  {
						  e.printStackTrace();
					  }
				  }
			  }
			
			//Added by Lavi on 14th October 2013 for downloading the uploaded scanned document.
			 
			 if (request.getParameter("dms")!=null)
			 {
				 if(request.getParameter("dms").equalsIgnoreCase("downloadFromPath"))
			 {
				 String filename = request.getParameter("path").toString();

				   // set the http content type to "APPLICATION/OCTET-STREAM
				   response.setContentType("application/download");

				   // initialize the http content-disposition header to
				   // indicate a file attachment with the default filename
				   // "myFile.txt"
				   String fileName = (String)estampFormBean.getObjEstampDet().getDoc();
				   //Filename=\"myFile.txt\"";
				   response.setHeader("Content-Disposition", "attachment; filename="
						+ URLEncoder.encode(fileName,"UTF-8"));

				   File fileToDownload = new File(filename);
				   FileInputStream fileInputStream = new FileInputStream(fileToDownload);
				   OutputStream out = response.getOutputStream();
				   byte[] buf = new byte[2048];

				   int readNum;
				   while ((readNum=fileInputStream.read(buf))!=-1)
				   {
				      out.write(buf,0,readNum);
				   }
				   fileInputStream.close();
				   out.close();
			 }
			 }
			 
			//end of code Added by Lavi on 14th October 2013 for downloading the uploaded scanned document.
			
			if ("ecodeFinalPage".equalsIgnoreCase(formName)) {
				
				logger.debug("ESTAMP ID BEFORE PDF IN ECODE FINAL PAGE------>"+estampFormBean.getObjEstampDet().getMainTxnId());
				
				if("generateOTPAction".equalsIgnoreCase(actionName))
				{
					OTPUtility otp=	new OTPUtility();
					boolean flag=otp.generateOTP(userId, estampFormBean.getObjEstampDet().getMainTxnId().toString(), "ACT_204");
					if(flag)
					{
						forwardJsp="ecodePageForZeroBal";
					}
				}
				else if("validateOTPAction".equalsIgnoreCase(actionName))
				{
						OTPUtility otp=	new OTPUtility();
					boolean flag =	otp.validateOTP(estampFormBean.getObjEstampDet().getMainTxnId().toString(), "ACT_204", userId, estampFormBean.getObjEstampDet().getOtp());
					if(flag)
					{
						estampFormBean.getObjEstampDet().setAllowPrint("Y");
						estampFormBean.getObjEstampDet().setPrintFlag("N");
						forwardJsp="ecodePageForZeroBal";
					}
					else
					{
						estampFormBean.getObjEstampDet().setPrintFlag("Y");
					}
					
				}
				else if("generateOTPActionE".equalsIgnoreCase(actionName))
				{
					OTPUtility otp=	new OTPUtility();
					boolean flag=otp.generateOTP(userId, estampFormBean.getObjEstampDet().getMainTxnId().toString(), "ACT_204");
					if(flag)
					{
						forwardJsp="ecodePage";
					}
				}
				else if("validateOTPActionE".equalsIgnoreCase(actionName))
				{
						OTPUtility otp=	new OTPUtility();
					boolean flag =	otp.validateOTP(estampFormBean.getObjEstampDet().getMainTxnId().toString(), "ACT_204", userId, estampFormBean.getObjEstampDet().getOtp());
					if(flag)
					{
						estampFormBean.getObjEstampDet().setAllowPrint("Y");
						estampFormBean.getObjEstampDet().setPrintFlag("N");
						forwardJsp="ecodePage";
					}
					else
					{
						estampFormBean.getObjEstampDet().setPrintFlag("Y");
						forwardJsp="ecodePage";
					}
					
				}
			
				
				
else if ("COMPLETE_TXN".equalsIgnoreCase(actionName)) {
					
					logger.debug("ESTAMP ID BEFORE PDF------>"+estampFormBean.getObjEstampDet().getMainTxnId());
					// For the pdf creation of E-stamp certificate.
					String estampGenCert = objEstampBO.getCertChkDetails(estampFormBean.getObjEstampDet().getMainTxnId().toString());
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
			        metaDataInfo.setUniqueNo(estampFormBean.getObjEstampDet().getEcode().toString());
			        metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			        
					if(estampGenCert.equalsIgnoreCase("Y"))   // Certificate already generated .....code to download that
					{
						if(language.equalsIgnoreCase("en"))
							request.setAttribute(CommonConstant.FAILURE_MSG,"The Estamp has already been printed");
						else
							request.setAttribute(CommonConstant.FAILURE_MSG,"ई-स्टाम्प पहले से ही उत्पन्न किया जा चुका है।");
						  estampFormBean.getObjEstampDet().setOwmFlag(null);
						  estampFormBean.getObjEstampDet().setOwmFile(null);
						forwardJsp="ecodePageForZeroBal";
						
					}
					else
					{

						String EstampPath = downloadPath+File.separator+estampFormBean.getObjEstampDet().getEcode().toString()+File.separator+"EStamp.PDF";
						File output;
						output = new File(EstampPath.toString());
						if (output.exists()) {
							logger.info("file already exists deleting....");
							output.delete();
						}
						if (output.getParentFile().exists() == false) {
							logger.info("Parent not found creating directory....");
							output.getParentFile().mkdirs();
						}
						EStamp estampObj = new EStamp();
				        ArrayList<EStampPartyDetails> eStampPartyList = new ArrayList();
estampObj.seteStampCode(estampFormBean.getObjEstampDet().getEcode().toString());

				        
				        estampObj.seteStampType(estampFormBean.getObjEstampDet().getEstampType().toString());
				        estampObj.setIssuedDate(estampFormBean.getObjEstampDet().getCurrentDate().toString());
				        estampObj.setUserId(estampFormBean.getObjEstampDet().getUserName());
				        estampObj.setOffice(estampFormBean.getObjEstampDet().getOfficeName());
				        estampObj.setPlace(estampFormBean.getObjEstampDet().getIssuedPlace());
				        estampObj.setValidity(estampFormBean.getObjEstampDet().getEstampValidity().toString());
				        estampObj.setDeedPurpose(estampFormBean.getObjEstampDet().getEstampPurps().toString());
				        estampObj.seteStampAmount(estampFormBean.getObjEstampDet().getAmount().toString());
				        estampObj.setDocumentType(estampFormBean.getObjEstampDet().getCourtDocTypeName().toString());
				        estampObj.setMainDeedDocPath(null);
				        EStampPartyDetails party1 = new EStampPartyDetails();
				        party1.setName(estampFormBean.getObjEstampDet().getAppNamedsply().toString());
				        party1.setCountry(estampFormBean.getObjEstampDet().getAppCountryName());
				        party1.setState(estampFormBean.getObjEstampDet().getAppStateName());
				        party1.setDistrict(estampFormBean.getObjEstampDet().getAppDistrictName());   
				        party1.setAddress(estampFormBean.getObjEstampDet().getAppAddress().toString()+" "+estampFormBean.getObjEstampDet().getAppDistrictName()+" "+estampFormBean.getObjEstampDet().getAppStateName()+" "+estampFormBean.getObjEstampDet().getAppCountryName());
				        if(estampFormBean.getObjEstampDet().getAppFatherName()==null || estampFormBean.getObjEstampDet().getAppFatherName().toString().equalsIgnoreCase(""))
				      	{
					        party1.setAuthorizerName(estampFormBean.getObjEstampDet().getAppAuthPersonName().toString());
					        party1.setPartyType("1");


				      	}
				        else
				        {
                                       party1.setAuthorizerName(estampFormBean.getObjEstampDet().getAppFatherName().toString());
				        	if("en".equalsIgnoreCase(language))
				        	{
				        		 party1.setName(estampFormBean.getObjEstampDet().getAppNamedsply().toString()+" S/O D/O W/O C/O "+estampFormBean.getObjEstampDet().getAppFatherName().toString() );	
				        	}
				        	else
				        	{
				        		 party1.setName(estampFormBean.getObjEstampDet().getAppNamedsply().toString()+" पुत्र/पुत्री/पत्नी/द्वारा  "+estampFormBean.getObjEstampDet().getAppFatherName().toString() );	
				        	}
					     
					        party1.setPartyType("2");
		
				        }
				        party1.setNoOfPerson(estampFormBean.getObjEstampDet().getAppPersons().toString());
				        eStampPartyList.add(party1);
				       
				        EStampPartyDetails party2 = new EStampPartyDetails();
				        party2.setName(estampFormBean.getObjEstampDet().getPartyNamedsply().toString());
				        party2.setCountry(estampFormBean.getObjEstampDet().getPartyCountryName());
				        party2.setState(estampFormBean.getObjEstampDet().getPartyStateName());
				        party2.setDistrict(estampFormBean.getObjEstampDet().getPartyDistrictName());   
				        party2.setAddress(estampFormBean.getObjEstampDet().getPartyAddress().toString()+" "+estampFormBean.getObjEstampDet().getPartyDistrictName()+" "+estampFormBean.getObjEstampDet().getPartyStateName()+" "+estampFormBean.getObjEstampDet().getPartyCountryName());
				        if(estampFormBean.getObjEstampDet().getPartyFatherName()==null || estampFormBean.getObjEstampDet().getPartyFatherName().toString().equalsIgnoreCase(""))
				      	{
					        party2.setAuthorizerName(estampFormBean.getObjEstampDet().getPartyAuthPersonName().toString());
					        party2.setPartyType("1");


				      	}
				        else
				        {
				        	if("en".equalsIgnoreCase(language))
				        	{
				        		 party2.setName(estampFormBean.getObjEstampDet().getPartyNamedsply().toString()+" S/O D/O W/O C/O "+estampFormBean.getObjEstampDet().getPartyFatherName().toString() );	
				        	}
				        	else
				        	{
				        		 party2.setName(estampFormBean.getObjEstampDet().getPartyNamedsply().toString()+" पुत्र/पुत्री/पत्नी/द्वारा  "+estampFormBean.getObjEstampDet().getPartyNamedsply().toString() );	
				        	}
					        party2.setAuthorizerName(estampFormBean.getObjEstampDet().getPartyFatherName().toString());
					        party2.setPartyType("2");

				       
				        }
				        party2.setNoOfPerson(estampFormBean.getObjEstampDet().getPartyPersons().toString());
				        eStampPartyList.add(party2);
				        
				        estampObj.setPartyList(eStampPartyList);
				        int resultStamp = -1;
				      PropertiesFileReader pr1 = PropertiesFileReader.getInstance("resources.igrs");
				   // PropertiesFileReader pr1 = PropertiesFileReader.getInstance("E:\\IGRS_WORKSPACE\\IGRS\\src\\resources\\igrs.properties");
						
						String hindiFont=pr1.getValue("dms_hindi_font_path");
						String englishFont=pr1.getValue("dms_english_font_path");
						SealsBD sealBD=new SealsBD();
						boolean val=sealBD.validateFont(hindiFont,englishFont);
						if(!val){
							throw new Exception();
						}
				        BurningImageAndText burnObj = new BurningImageAndText(hindiFont,englishFont);
				        String     outputPath = downloadPath+File.separator+estampFormBean.getObjEstampDet().getEcode().toString();
				      	String var=getServlet().getServletContext().getRealPath("")+File.separator+ "images"+File.separator+"header img.jpg";
			      		String dmsFolderName = "IGRS";
			      		
			      		String docPath = objEstampBO.getEstampDocDetails(estampFormBean.getObjEstampDet().getMainTxnId().toString());
						
							if("en".equalsIgnoreCase(language))
						       {  
						        resultStamp = burnObj.createEStamps(outputPath, var, "English", estampObj, "EStamp1.PDF",1,1);
						       }
						       else
						       {
								     estampObj.seteStampType("न्यायिक");

					    	   resultStamp = burnObj.createEStamps(outputPath,var, "Hindi", estampObj, "EStamp1.PDF",1,1);
		   
					       }
				
					String signFlag=pr.getValue("digital_sign_flag");
					if("I".equalsIgnoreCase(new EstampingBD().getUserType(userId)))
					{
					if(signFlag.equalsIgnoreCase("Y"))
			        {
					new DocumentService().sign(outputPath+File.separator+ "EStamp1.PDF", outputPath+File.separator+ "EStamp.PDF", objEstampBO.getSubjectName(userId).toString(), 100, 100, 200, 200);
			        }
					else
					{

			              ExecutorService executor = Executors.newFixedThreadPool(1);
			              List<Callable<Object>> todo = new ArrayList<Callable<Object>>();
			              logger.debug("signed pathelse:"+outputPath+File.separator+ "EStamp.PDF");
			              logger.debug("input pathelse:"+outputPath+File.separator+ "EStamp1.PDF");
			              
			                  todo.add(Executors.callable(new DigtalSignThread(outputPath+File.separator+ "EStamp1.PDF", outputPath+File.separator+ "EStamp.PDF", "100", "100", "200", "200",objEstampBO.getSubjectName(userId).toString()))); 
			               //   logger.debug("input path:"+documentPath);

			              executor.invokeAll(todo,1, TimeUnit.MINUTES); // Timeout 
			              
			             
			              executor.shutdown();
			        
			              //executor.invokeAll(Arrays.asList(new DigtalSignThread(outputPath+File.separator+ "EStamp1.PDF",outputPath+File.separator+ "EStamp.PDF",objEstampBO.getSubjectName(userId).toString(),"100","100","200","200")), 1, TimeUnit.MINUTES); // Timeout of 10 minutes.
			              
			        
					
					}
					try
				      {
						ReadPropertiesFile prop = new ReadPropertiesFile();
							metaDataInfo.setUniqueNo(estampFormBean.getObjEstampDet().getEcode().toString());
							metaDataInfo.setLatestFlag("L");
							metaDataInfo.setType("E");	
							String	fileName = "EStamp.PDF";
									String path = outputPath+File.separator+fileName;
									logger.debug("path^^^^^^^^^^^^^^^^^^^^^^^^^^^"+path);
									File file = new File(path);
									if(!file.exists())
									{
										throw new Exception();
									}
									metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
									String docId = docOperations.uploadDocument(connDetails, file, "IGRS", true, metaDataInfo);
									logger.debug("doc Id "+docId);
									if(docId != "-1")
	  								{
	  									// update generate cert status in table
	  									boolean flag = objEstampBO.updateCertificateGenerationChk(estampFormBean.getObjEstampDet().getMainTxnId().toString());
	  									
	  									
	  								}
									String guid=GUIDGenerator.getGUID();
							        //String EstampPath = CommonConstant.ESTAMP_DOWNLOAD_PATH+estampFormBean.getObjEstampDet().getEcode().toString();
							      //  String EstampPath1 = CommonConstant.ESTAMP_DOWNLOAD_PATH_LINUX+guid;
									String EstampPath1 = downloadPath+File.separator+guid;
							        File output1;
									output1 = new File(EstampPath1.toString());
									if (output.exists()) {
										logger.info("file already exists deleting....");
										output1.delete();
									}
									if (output1.getParentFile().exists() == false) {
										logger.info("Parent not found creating directory....");
										output1.getParentFile().mkdirs();
									}
									metaDataInfo.setType("E");
							        int result = docOperations.downloadDocument(connDetails,metaDataInfo,EstampPath1,CommonConstant.MODULE_NAME);
								
							        estampFormBean.getObjEstampDet().setOwmFlag("Y");
							        estampFormBean.getObjEstampDet().setOwmFile(EstampPath1+File.separator+CommonConstant.SIGNED_ESTAMP_NAME);
							        logger.debug("download result---------->"+result);
							    	forwardJsp="ecodePageForZeroBal";
									
									
									
									
									
									
									// below code to view the doc
	  						        // set the http content type to "APPLICATION/OCTET-STREAM
	  		          			   /*response.setContentType("application/download");

	  		          			  
	  		          			   response.setHeader("Content-Disposition", "attachment; filename="
	  		          						+ URLEncoder.encode(path,"UTF-8"));
	  		          			   
	  		          			   File fileToDownload = new File(path);
	  		          			   FileInputStream fileInputStream = new FileInputStream(fileToDownload);
	  		          			   OutputStream out = response.getOutputStream();
	  		          			   byte[] buf = new byte[2048];

	  		          			   int readNum;
	  		          			   while ((readNum=fileInputStream.read(buf))!=-1)
	  		          			   {
	  		          			      out.write(buf,0,readNum);
	  		          			   }
	  		          			   fileInputStream.close();
	  		          			   out.close();*/
									
					}
					catch(Exception e)
					{
						logger.debug(e.getStackTrace());
					}
					forwardJsp="ecodePageForZeroBal";
				}else{
					byte[] eStampByte=DMSUtility.getDocumentBytes(outputPath+File.separator+ "EStamp1.PDF");
					request.setAttribute("eStampByte", eStampByte);
					request.setAttribute("eStampfilePath", outputPath.replaceAll("\\\\", "/")+"//"+ CommonConstant.SIGNED_ESTAMP_NAME);
					forwardJsp="eStampSignPDF";
					logger.debug("BYTE ARRAY IS:::::::"+eStampByte+"FINAL PATH IS::::::::"+outputPath.replaceAll("\\\\", "/")+"//"+ CommonConstant.SIGNED_ESTAMP_NAME);
					logger.debug("CODE TRANSFERRED TO E-MUDHRA");
					
				}
					
					
					}
					
					
					
					
					
					}
					
			
			
				else if ("DOC_SIGNED_DSC".equalsIgnoreCase(actionName)) {
					logger.debug("ESTAMP ID AFTER PDF------>"+estampFormBean.getObjEstampDet().getMainTxnId());
					// For the pdf creation of E-stamp certificate.
					System.out.println("DOC_SIGNED_DSC"+estampFormBean.getSignedPath());
					String outputPath=estampFormBean.getSignedPath();
					if(outputPath!=null && !outputPath.isEmpty())
					{
					
					String estampGenCert = objEstampBO.getCertChkDetails(estampFormBean.getObjEstampDet().getMainTxnId().toString());
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
			        metaDataInfo.setUniqueNo(estampFormBean.getObjEstampDet().getEcode().toString());
			        metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			        if(estampGenCert!=null && estampGenCert.equalsIgnoreCase("Y"))
			        {
			        	session.setAttribute("estampID", null);
			        	estampFormBean.setPrintCheck("N");
						forwardJsp = "ecodePageForZeroBal";
						return mapping.findForward(forwardJsp);
			        }
					
					try
				      {
						ReadPropertiesFile prop = new ReadPropertiesFile();
							metaDataInfo.setUniqueNo(estampFormBean.getObjEstampDet().getEcode().toString());
								//String	fileName = "EStamp.PDF";
									String path = outputPath;//+File.separator+fileName;
									logger.debug("path^^^^^^^^^^^^^^^^^^^^^^^^^^^"+path);
									File file = new File(path);
									metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
									metaDataInfo.setType("E");
									metaDataInfo.setLatestFlag("L");
									String docId = docOperations.uploadDocument(connDetails, file, "IGRS", true, metaDataInfo);
									logger.debug("doc Id "+docId);
									boolean flag = false;
									if(docId != "-1")
	  								{
	  									// update generate cert status in table
	  									 flag = objEstampBO.updateCertificateGenerationChk(estampFormBean.getObjEstampDet().getMainTxnId().toString());
	  									session.setAttribute("estampID", "estampreprint");
	  									
	  								}
									if(flag){
										estampFormBean.setPrintCheck("N");
									}
									 String guid=GUIDGenerator.getGUID();
								        //String EstampPath = CommonConstant.ESTAMP_DOWNLOAD_PATH+estampFormBean.getObjEstampDet().getEcode().toString();
								      //  String EstampPath1 = CommonConstant.ESTAMP_DOWNLOAD_PATH_LINUX+guid;
								        String EstampPath1 = downloadPath+File.separator+guid;

								        File output1;
										output1 = new File(EstampPath1.toString());
										if (output1.exists()) {
											logger.info("file already exists deleting....");
											output1.delete();
										}
										if (output1.getParentFile().exists() == false) {
											logger.info("Parent not found creating directory....");
											output1.getParentFile().mkdirs();
										}
										metaDataInfo.setType("E");
										metaDataInfo.setLatestFlag("L");
								        int result = docOperations.downloadDocument(connDetails,metaDataInfo,EstampPath1,CommonConstant.MODULE_NAME);
									
									 estampFormBean.getObjEstampDet().setOwmFlag("Y");
									  estampFormBean.getObjEstampDet().setOwmFile(EstampPath1+File.separator+CommonConstant.SIGNED_ESTAMP_NAME);
									  logger.debug("OWN FILE PATH::::::"+ estampFormBean.getObjEstampDet().getOwmFile());
									  logger.debug("download result---------->"+result);
	  								 // below code to view the doc
	  						        // set the http content type to "APPLICATION/OCTET-STREAM
									forwardJsp="ecodePageForZeroBal";
									/*response.setContentType("application/download");

	  		          			  
	  		          			   response.setHeader("Content-Disposition", "attachment; filename="
	  		          						+ URLEncoder.encode(path,"UTF-8"));
	  		          			   
	  		          			   File fileToDownload = new File(path);
	  		          			   FileInputStream fileInputStream = new FileInputStream(fileToDownload);
	  		          			   OutputStream out = response.getOutputStream();
	  		          			   byte[] buf = new byte[2048];

	  		          			   int readNum;
	  		          			   while ((readNum=fileInputStream.read(buf))!=-1)
	  		          			   {
	  		          			      out.write(buf,0,readNum);
	  		          			   }
	  		          			   fileInputStream.close();
	  		          			   out.close();*/
									
					}
					catch(Exception e)
					{
						logger.debug(e.getStackTrace());
						forwardJsp = "failure";
					}
					forwardJsp="ecodePageForZeroBal";
					}else{
						if(language.equalsIgnoreCase("en"))
							request.setAttribute(CommonConstant.FAILURE_MSG,"Could not Apply the Digital Signature");
						else
							request.setAttribute(CommonConstant.FAILURE_MSG,"Could not Apply the Digital Signature");
						
						forwardJsp = "ecodePageForZeroBal";
					}
					
				}
			}// end of print page

			
						
		
			
			
     // COMMENTED BY LAVI FOR E-Stamp Judicial   
        
        /* if(estampFormBean.getPageTitle().equalsIgnoreCase("NonJudicial"))
        {
        	FORWARD_JSP  =  "view";
        	PAY_FORWARD_JSP ="./estampNonJudConf.do";
        	
        }
       System.out.println("code is e");
        if(estampFormBean.getPageTitle().equalsIgnoreCase("NonJudicialConfirm")) {
        	logger.info("EStampAction --  before printing the values  Estamp Details in action  ");
        	estampFormBean  =  objEstBd.printEstampTxnDet(estampFormBean);
        	FORWARD_JSP  =  "print";
        
        }
   
        
        //*********** END ****************************NoN-JUDICIAL*******************************
        
        
        
        //*********** START ****************************JUDICIAL*******************************
        
       if(estampFormBean.getPageTitle().equalsIgnoreCase("Judicial")){ 
    	   logger.debug("page name"+ estampFormBean.getPageTitle());
    	
    	   
    	   FormFile strDocName = estampFormBean.getUpLoadDoc();
    	  logger.debug("Document Name is "+strDocName);   
    	  String fileN = strDocName.getFileName();
    	  logger.debug("file name is:---"+fileN);
    	  objEstampDto.setUpLoadDocName(fileN);
    	  logger.debug("value set in the dto is:-----"+objEstampDto.getUpLoadDocName());
    	 // objEstampDto.setUpLoadDocName(strDocName.getFileName());
   //added
    	  
    	  System.out.println("LENGTH"+fileN.length());
    	 if (fileN.length()!=0){
    		 File folder=new File(filePath);

             if(!folder.exists()){

                     folder.mkdir();
               }

   //end of code
       	   
       	   objEstampDto.setFilePath(filePath);
       	   
       	  /* File newFile1 = new File(filePath
   					+ strDocName.getFileName());*/
       	   /*File newFile1 = new File(filePath
   					, fileN);
   			FileOutputStream fos1 = new FileOutputStream(
   					newFile1);
   			fos1.write(strDocName.getFileData());
   			fos1.close(); 
   			FORWARD_JSP  =  "judView";
     	   PAY_FORWARD_JSP ="./estampJudConf.do";
    	 }
    	 else{
    	   

    	   FORWARD_JSP  =  "judView";
    	   PAY_FORWARD_JSP ="./estampJudConf.do";
    	 } 
        }
       if(estampFormBean.getPageTitle().equalsIgnoreCase("JudicialView")){
        	FORWARD_JSP  =  "success";
        }
       
      if(session.getAttribute("status")!=null)
	paymentTxnId=session.getAttribute("status").toString();
      //logger.debug("EstampAction  paymentTxnId ="+ paymentTxnId);
       logger.info("paymentTxnId="+paymentTxnId);
       ArrayList listForm = estampFormBean.getFormList();
   	HashMap mapForm = new HashMap();
   	logger.info("EstampBD-- listForm--------->"+listForm);
   	HashMap values = estampFormBean.getValues();
	if(values != null){
	    Iterator I = values.entrySet().iterator();
       	while(I.hasNext()) {
           	    Map.Entry me = (Map.Entry) I.next();
           	    logger.debug("me.getKey:-"+me.getKey());
           	    logger.debug("me.getValue:-"+me.getValue());
           	    mapForm.put(me.getKey(),
           		    me.getValue());
       		}
	estampFormBean.getObjEstampDet().setMapForm(mapForm);
	}
		if (paymentTxnId !=null ) {
			if(PAY_FORWARD_JSP != null)
			if(PAY_FORWARD_JSP.equalsIgnoreCase("./estampJudConf.do"))
			{
			estampFormBean.setPmtTxnId(paymentTxnId);
        	insertValue=false;
        	if(session.getAttribute("insertJud")==null){
        		estampFormBean.setObjEstampDet(objEstampDto);
        		logger.debug("mesage is-------------------"+estampFormBean.getObjEstampDet().getFilePath());
        		logger.debug("mesage is-------------------"+estampFormBean.getObjEstampDet().getUpLoadDocName());
        	 insertValue = objEstBd.submitJudEstampInfo(estampFormBean,roleId,funId,userId);
        	// logger.debug("EstampAction  insertValue ="+ insertValue);
        	 session.setAttribute("insertJud","inserted");
        	 session.setAttribute("status",null);
        	}
			 FORWARD_JSP = "judConf";
			 if(insertValue)
        		 return mapping.findForward(FORWARD_JSP);
			}else if(PAY_FORWARD_JSP.equalsIgnoreCase("./estampNonJudConf.do"))
			{
				insertValue=false;
				estampFormBean.setPmtTxnId(paymentTxnId);
	        	if(session.getAttribute("insertNonJud")==null){
	        	 insertValue = objEstBd.submitEstampInfo(estampFormBean,roleId,funId,userId);
	        	 session.setAttribute("insertNonJud","inserted");
	        	}
				 FORWARD_JSP = "nonJudConf";
				 if(insertValue)
	        		 return mapping.findForward(FORWARD_JSP);
			}
		}
        
        if(estampFormBean.getPageTitle().equalsIgnoreCase("JudicialConfirm"))
        {
        	logger.info("EStampAction -- before printing the values  Estamp Details in action  ");
        	estampFormBean  =  objEstBd.printJudEstampTxnDet(estampFormBean);
        	FORWARD_JSP  =  "judPrint";
        
        }
        
        
        //*********** END ****************************JUDICIAL*******************************
        
        
      //*********** START ****************************ESTAMP_SEARCH*******************************
        if(estampFormBean.getPageTitle().equalsIgnoreCase("EstampSearch"))
        {
        	 
        	estampSearchList = objEstBd.getEstampSearchResult((EstampDetailsDTO)estampFormBean.getObjEstampDet());
        	logger.info(" EStampAction -- after searching  Estamp Details in action  estampSearchList =  "+estampSearchList);
            
        	estampFormBean.getObjEstampDet().setEstampSResult(estampSearchList);
        	estampFormBean.setEstampSResult(estampSearchList);
        	
        	
        	FORWARD_JSP  =  "searchConfirm";
        }
        if(estampCode != null)
        {
           
            logger.info(" EStampAction -- getType=========>  "+estampFormBean.getObjEstampDet().getType());
            estampFormBean.setEstampCode(estampCode);
            estampFormBean  =  objEstBd.getEstampSearchDet(estampFormBean);
            FORWARD_JSP = "view";
            
        }
        //*********** END ****************************ESTAMP_SEARCH*******************************
        if(estampFormBean.getPageTitle().equalsIgnoreCase("DeactEstamp")){
        	
        	FORWARD_JSP  =  "deactView";
        }
        
        if(estampFormBean.getPageTitle().equalsIgnoreCase("DeactPopupDet")){
          	logger.info("EStampAction --   Before  getting Registation Initiated Application  Details");
          	estampFormBean = objEstBd.getRegIntDet(estampFormBean);
          	logger.info("after getting Registation Initiated Application  Details");
          	FORWARD_JSP  =  "deactStart";
           }
        
        if(estampFormBean.getPageTitle().equalsIgnoreCase("DeactPopRes")){
       	logger.info("EStampAction -- before getting Registation Initiated Application  Details");
       	estampFormBean = objEstBd.getRegIntDet(estampFormBean);
       	logger.info("EStampAction --  after getting Registation Initiated Application  Details");
       	//estampFormBean.setStampDuty(1203.00);
       	
       	FORWARD_JSP  =  "deactPopDet";
        }
        if(estampFormBean.getPageTitle().equalsIgnoreCase("DeactPopup")){
        	
        	retRegInitList=objEstBd.getInitAppDet(estampFormBean);
        	logger.debug("EStampAction --  retRegInitList=="+retRegInitList);
        	estampFormBean.setRetRegInitList(retRegInitList);
        	estampFormBean.setPageTitle("DeactPopRes");
        	FORWARD_JSP  =  "deactPopRes";
        }
        
        
        if(estampFormBean.getPageTitle().equalsIgnoreCase("DeactEstampView"))
        {
        	logger.info("EStampAction --  before inserting Estamp De-activation details. ");
        	estampFormBean.setDeactTxnId(objCommon.getSequenceNumber("IGRS_ESTAMP_SEQ", "DEstamp"));
        	blnDeactIns=objEstBd.subDeactEstampInfo(estampFormBean,roleId,funId,userId);
        	logger.info("EStampAction -- after inserting Estamp De-activation details. ");
        	
        		logger.info("EStampAction --  getting  De-activation Txn Id ");
        		deactTxnID=objEstBd.getDeactTxnId();
        		logger.info("deactTxnID==="+deactTxnID);
        		estampFormBean.setDeactTxnId(objEstBd.getDeactTxnId());
        		if(deactTxnID != null)
        		{
        	    FORWARD_JSP  =  "deactConf";
                }
        }	
        
        if(estampFormBean.getPageTitle().equalsIgnoreCase("DeactEstampConf")){
        	logger.info("EStampAction --  before printing Estamp De-activation details. ");
        	estampFormBean=objEstBd.PrintDeactDet(estampFormBean);
        	logger.info("EStampAction --  after printing Estamp De-activation details. ");
        	FORWARD_JSP  =  "deactPrint";
        }
        
      
        if(estampFormBean.getPageTitle().equalsIgnoreCase("mstampEntry"))
        {
        	String temp = null;
        	String temp_[] = null;
        	if(estampFormBean.getStampType().equalsIgnoreCase("1")) 
        	{
        	   temp = estampFormBean.getTemp();
				if (temp!=null & (!temp.equalsIgnoreCase("")))
				{
			    	 temp_ = temp.split(",");
			    	 mstampIndList = new ArrayList();
			    	 for(int i = 0; i <temp_.length;i++)
			    	  {					    					    	
					    	EstampDetailsDTO chdto=new EstampDetailsDTO();
					    	chdto.setMstampCode(temp_[i]);
					    	logger.info("stampcode="+chdto.getMstampCode());
					    	chdto.setDenomination(temp_[++i]);
					    	logger.info("denomination="+chdto.getDenomination());
					    	mstampIndList.add(chdto);
			    	  }
				}
        	}
        	else
        	{
        		temp = estampFormBean.getTemp();
				if (temp!=null & (!temp.equalsIgnoreCase("")))
				{
			    	 temp_ = temp.split(",");
			    	 mstampIndList = new ArrayList();
			    	 for(int i = 0; i <temp_.length;i++)
			    	  {					    					    	
					    	EstampDetailsDTO chdto=new EstampDetailsDTO();
					    	chdto.setStampCodeFrom(temp_[i]);
					    	//logger.info("stampcode="+chdto.getMstampCode());
					    	chdto.setStampCodeTo(temp_[++i]);
					    	//logger.info("denomination="+chdto.getDenomination());
					    	chdto.setSrDenom(temp_[++i]);
					    	mstampIndList.add(chdto);
			    	  }
				}
        	}
			objEstampDto.setPstList(mstampIndList);
		    estampFormBean.setObjEstampDet(objEstampDto); 
		    estampFormBean.setUserId(userId);
        	insertValue = objEstBd.savePstampDet(estampFormBean);
        	FORWARD_JSP = "mstampConf";
        }
        if(estampFormBean.getPageTitle().equalsIgnoreCase("mstampView"))
			 
        {
        	
        	
        	mStampResList = objEstBd.getMStampViewRes(estampFormBean);
        	//logger.info("Action Class........mStampResList="+mStampResList);
        	estampFormBean.setMstResList(mStampResList);
        	FORWARD_JSP = "mstampViewRes";
        } 
	    //*********** START ****************************ESTAMP_SEARCH*******************************
	  
	    //*********** START ****************************ESTAMP_SEARCH*******************************

       // session.setAttribute("user","user");
       session.setAttribute("forwardPath",PAY_FORWARD_JSP);
       session.setAttribute("amount",estampFormBean.getObjEstampDet().getTotal());
       logger.info("EStampAction -- ..........FORWARD_JSP=="+FORWARD_JSP);
       logger.info("Action Class.......PageTitle=="+estampFormBean.getPageTitle());
	    session.setAttribute("EstmpSList", estampFormBean.getObjEstampDet().getEstampSResult());
	    session.setAttribute("RegInitList", estampFormBean.getRetRegInitList());
     session.setAttribute("mstampResList", estampFormBean.getMstResList());
	    session.setAttribute("EstampFormBean",estampFormBean);
    request.setAttribute("list",estampTxnList); */
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    	}
        logger.debug("FORWARD_JSP===="+forwardJsp);
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


	private String removeFile(String fileName, String filePath) {
		//String filePath = "D:/upload/";
		//String filePath = getServlet().getServletContext().getRealPath("")
		//+ "/temp/";
		File newFile = new File(filePath + fileName);
		newFile.delete();

		return "";
	}

	private boolean uploadFile(FormFile filetobeuploaded,String fileName,String filePath) {

		
		
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

	

	public static void downloadDocument(HttpServletResponse res, byte[] docContents, String fileName) {
		try {
			OutputStream os = res.getOutputStream();
			res.setContentType("application/download");
			res.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(fileName,"UTF-8"));
			os.write(docContents);
			os.flush();
			os.close();
		} catch (Exception e) {
		}

	}

}   

