/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :  ServiceProviderAction.java 
 * Author      :   Lavi Tripathi
 * Description :   Created on 22 July 2013
*/

package com.wipro.igrs.serviceProvider.action;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import com.newgen.burning.BurningImageAndText;
import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentDetails;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.ODServerDetails;
import com.newgen.burning.ServiceProvider;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.db.OTPUtility;
import com.wipro.igrs.digitalSign.DigtalSignThread;
import com.wipro.igrs.digitalSign.DocumentService;
import com.wipro.igrs.serviceProvider.bo.ServiceProviderBO;
import com.wipro.igrs.serviceProvider.constant.ServiceProviderConstant;
import com.wipro.igrs.serviceProvider.dto.ServiceProviderDTO;
import com.wipro.igrs.serviceProvider.form.ServiceProviderForm;
import com.wipro.igrs.util.GUIDGenerator;
import com.wipro.igrs.util.PropertiesFileReader;

//Added new common code for MIME Type check of Uploaded file
import com.wipro.igrs.common.mime.MIMECheck;


public class ServiceProviderAction extends BaseAction {
	
	//String FORWARD_JSP = "success";
	private String forwardJsp = ServiceProviderConstant.SUCCESS_PAGE;
	
	private Logger logger = 
		(Logger) Logger.getLogger(ServiceProviderAction.class);
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws Exception {
    	logger.info("ServiceProviderAction -- In action for getting form object--> " );
    	logger.info("ServiceProviderAction -- In action for getting form object--> " + form);

    	
    	if (form != null){
      ServiceProviderForm objServiceProviderForm  =  (ServiceProviderForm)form;
      ServiceProviderBO objServiceProviderBO = new ServiceProviderBO();
      ServiceProviderDTO objServiceProviderDTO = objServiceProviderForm.getObjServiceProviderDTO();
      String formName = objServiceProviderDTO.getFormName();
      String userId = (String) session.getAttribute("UserId");
      objServiceProviderForm.getObjServiceProviderDTO().setUid(userId);
      System.out.println("Before action");
      String actionName = objServiceProviderDTO.getActionName();
      String page = (String) request.getParameter("page");
      String roleId = (String)session.getAttribute("role");
	  String funId = (String)session.getAttribute("functionId");
	  String loggedToOffice = (String)session.getAttribute("loggedToOffice");
	  objServiceProviderForm.getObjServiceProviderDTO().setOfficeId(loggedToOffice);
	  String lang=(String)session.getAttribute("languageLocale");
	  objServiceProviderForm.setLang(lang);
	//added by shruti---30 july 2014
	  PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		
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
		  /// for Cancellation of License Of ServiceProvider
		  	objServiceProviderForm = cancelLicenseComplete(request,
				objServiceProviderForm, objServiceProviderBO,
				objServiceProviderDTO,session);
		  
		  // for adding criterias and checking the type of SP
		  if (request.getParameter("modName") != null) 
		  {
				if (request.getParameter("modName").equalsIgnoreCase("ServiceProvider") && request.getParameter("fnName").equalsIgnoreCase("AddCriteria"))
				{
					objServiceProviderForm.getObjServiceProviderDTO().setIschecked(0);
					objServiceProviderForm.getObjServiceProviderDTO().setRadioSelect("");
					logger.debug("------------------->inside add criteria action");
					forwardJsp = ServiceProviderConstant.ADD_CRITERIAS;
					logger.info("--------------->showing add criteria screen");
					objServiceProviderDTO.setActionName("");
				}
			}// end of checking the type of SP
		  
		  //modified by shruti---10 sep 2014
		  //reprint case
		  if("generateOTPAction".equalsIgnoreCase(actionName))
			{
			  logger.info("inside generate OTP Action");
			  String TxnId =objServiceProviderForm.getObjServiceProviderDTO().getRequestNo().toString() ;
			  logger.info("Transaction is"+TxnId);
			  objServiceProviderForm.getObjServiceProviderDTO().setMainTxnId(TxnId);
				logger.info("Main Txn Id"+ objServiceProviderForm.getObjServiceProviderDTO().getMainTxnId());
				OTPUtility otp=	new OTPUtility();
				boolean flag=false;
				try{
				flag=otp.generateOTP(userId, objServiceProviderForm.getObjServiceProviderDTO().getMainTxnId().toString(), "ACT_340");
				}
				catch(Exception e)
				{
				logger.debug(e.getMessage(),e)	;
				}
				logger.info("flag from generate OTP>>>>>>>>>>>>>>>>>>>>>>>"+flag);
				if(flag)
				{
					objServiceProviderForm.setErrorMessageOTP("");
					forwardJsp="viewAfterFeePaid";
				}
				else
				{
					objServiceProviderForm.setErrorMessageOTP("The Server is Not available at the moment . Please Generate OTP later./ सर्वर  उपलब्ध नहीं है। OTP बाद में उत्पन्न करें।");
					forwardJsp="viewAfterFeePaid";
				}
			}
			else if("validateOTPAction".equalsIgnoreCase(actionName))
			{
				logger.info("inside validate OTP mehtod");
				
				OTPUtility otp=	new OTPUtility();
				boolean flag =	otp.validateOTP(objServiceProviderForm.getObjServiceProviderDTO().getMainTxnId().toString(), "ACT_340", userId, objServiceProviderForm.getObjServiceProviderDTO().getOtp());
				logger.info("from validate OTP flag>>>>>>>>>>>>>>>>>>>>>"+flag);
				if(flag)
				{
					objServiceProviderForm.getObjServiceProviderDTO().setEnablePrintFlag("Y");
					objServiceProviderForm.getObjServiceProviderDTO().setOtpChk("Y");
					forwardJsp="viewAfterFeePaid";
				}
				else
				{
					objServiceProviderForm.getObjServiceProviderDTO().setOtpChk("N");
					forwardJsp="viewAfterFeePaid";
				}
				
			}
		  //for handling print for the first time
		  if("generateOTPAction1".equalsIgnoreCase(actionName))
			{
			  logger.info("inside generate OTP Action1");
			  String TxnId =objServiceProviderForm.getObjServiceProviderDTO().getRequestNo().toString() ;
			  logger.info("Transaction is"+TxnId);
			  objServiceProviderForm.getObjServiceProviderDTO().setMainTxnId(TxnId);
				logger.info("Main Txn Id"+ objServiceProviderForm.getObjServiceProviderDTO().getMainTxnId());
				OTPUtility otp=	new OTPUtility();
				boolean flag=otp.generateOTP(userId, objServiceProviderForm.getObjServiceProviderDTO().getMainTxnId().toString(), "ACT_340");
				logger.info("flag from generate OTP1>>>>>>>>>>>>>>>>>>"+flag);
				if(flag)
				{
					forwardJsp="viewAfterLicenseGenratn";
				}
			}
			else if("validateOTPAction1".equalsIgnoreCase(actionName))
			{
				OTPUtility otp=	new OTPUtility();
				boolean flag =	otp.validateOTP(objServiceProviderForm.getObjServiceProviderDTO().getMainTxnId().toString(), "ACT_340", userId, objServiceProviderForm.getObjServiceProviderDTO().getOtp());
				if(flag)
				{
					objServiceProviderForm.getObjServiceProviderDTO().setEnablePrintFlag("Y");
					objServiceProviderForm.getObjServiceProviderDTO().setOtpChk("Y");
					logger.info("flag from validate OTP1>>>>>>>>>>>>>>>>>>"+flag);
					forwardJsp="viewAfterLicenseGenratn";
				}
				else
				{
					objServiceProviderForm.getObjServiceProviderDTO().setOtpChk("N");
					forwardJsp="viewAfterLicenseGenratn";
				}
				
			}
		  //end of changes for OTP
		  
		  
		// for adding criterias after checking the type of SP
		  
		  if("spChecked".equalsIgnoreCase(actionName))
		  {
			  logger.debug("----------------> inside SP Type action");
			  objServiceProviderForm.getObjServiceProviderDTO().setIschecked(1);
			  objServiceProviderForm.getObjServiceProviderDTO().setCriteria("");
			  String value= (String)objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect();
			  logger.debug("------->"+value);
			  forwardJsp = ServiceProviderConstant.ADD_CRITERIAS;
			  //added by shruti---28 april 2014
			  if("4".equals(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus()))
			  {
				  forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			  }
			  if("8".equals(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus()))
			  {
				  forwardJsp=ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			  }
			  //end
			  objServiceProviderDTO.setActionName("");
		  }
      
		  // for submitting the entered criteria
		  
		  if ("ADD_CRITERIA".equalsIgnoreCase(actionName))
		  {
			  logger.debug("----------------> inside add criteria action");
			  boolean addCriteria;
			  addCriteria = objServiceProviderBO.insertCriteria(objServiceProviderForm);
			  logger.debug("----------------> after successful insertion");
			  forwardJsp = ServiceProviderConstant.ADD_CRITERIAS_SUCCESS_PAGE;
			  objServiceProviderDTO.setActionName("");
			  
			  
		  }// end of adding criterias after checking the type of SP
		  
		  // reset method for criteria addition
		  if ("RESET_ACTION".equalsIgnoreCase(actionName))
		  {
			  logger.debug("----------------> inside reset action");
			  objServiceProviderForm.getObjServiceProviderDTO().setCriteria("");
			  forwardJsp = ServiceProviderConstant.ADD_CRITERIAS;;
			  objServiceProviderDTO.setActionName("");
			  
		  }
		// end of reset method for criteria addition
		  
		  // for edit/delete the criteria for corresponding SP type
		  if (request.getParameter("modName") != null) 
		  {
		  
		  if (request.getParameter("modName").equalsIgnoreCase("ServiceProvider") && request.getParameter("fnName").equalsIgnoreCase("Edit/DeleteCriteria"))
		  {
			  objServiceProviderForm.getObjServiceProviderDTO().setIschecked(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsempty(0);
				objServiceProviderForm.getObjServiceProviderDTO().setRadioSelect("");
				logger.debug("------------------->inside edit/delete criteria action");
				forwardJsp = ServiceProviderConstant.EDIT_DELETE_CRITERIAS;
				logger.info("--------------->showing edit/delete criteria screen");
				objServiceProviderDTO.setActionName("");
			  
		  }
		  }
		  // for edit/delete criterias and checking the type of SP
		  
		  if("spCheckedType".equalsIgnoreCase(actionName))
		  {
			  logger.debug("----------------> inside SP Type action");
			  objServiceProviderForm.getObjServiceProviderDTO().setIschecked(1);
			  String value= (String)objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect();
			  logger.debug("------->"+value);
			  ArrayList criteriaList = objServiceProviderBO.getCriterias(value);
		      logger.info("--------------->"+criteriaList.size());
		      if(criteriaList.size()==0)
		      {
		      		  objServiceProviderForm.getObjServiceProviderDTO().setEditDeleteCriteria(null);
		      		objServiceProviderForm.getObjServiceProviderDTO().setIsempty(1);
		      }
		      else
		      {
		    	  objServiceProviderForm.getObjServiceProviderDTO().setIsempty(0);
		      		objServiceProviderForm.getObjServiceProviderDTO().setEditDeleteCriteria(criteriaList);
		      }
		      	  
		      request.setAttribute("criteriaList", objServiceProviderForm.getObjServiceProviderDTO().getEditDeleteCriteria());
		      forwardJsp  =  ServiceProviderConstant.EDIT_DELETE_CRITERIAS;
			  objServiceProviderDTO.setActionName("");
		  }
		// end of code for edit criterias and checking the type of SP
		  
		  // for entering the edited values in textbox
		  if ("EDIT_CRITERIA".equalsIgnoreCase(actionName))
		  {
			 String[] idName = objServiceProviderForm.getObjServiceProviderDTO().getRadioSelectCriteria().toString().split("~");
			 objServiceProviderForm.getObjServiceProviderDTO().setCriteriaId(idName[0]);
			 String id = idName[0];
			 logger.debug("------->"+id);
			 objServiceProviderForm.getObjServiceProviderDTO().setCriteriaName(idName[1]);
			 String name = (String) objServiceProviderForm.getObjServiceProviderDTO().getCriteriaName();
			 logger.debug("------->"+name);
			 objServiceProviderForm.getObjServiceProviderDTO().setCriteria(name);
			 forwardJsp  =  ServiceProviderConstant.EDIT_CRITERIA;
			 objServiceProviderDTO.setActionName("");
		  }
		  // end of code for entering the edited values in textbox
		  
		  // for deleting the criteria
		  if ("DELETE_CRITERIA".equalsIgnoreCase(actionName))
		  {
			  String[] idName = objServiceProviderForm.getObjServiceProviderDTO().getRadioSelectCriteria().toString().split("~");
			  objServiceProviderForm.getObjServiceProviderDTO().setCriteriaId(idName[0]);
			  String id = idName[0];
			  logger.debug("------->"+id);
			  objServiceProviderForm.getObjServiceProviderDTO().setCriteriaName(idName[1]);
			 boolean delCriteria;
			 delCriteria = objServiceProviderBO.deleteCriteria(id);
			 forwardJsp  =  ServiceProviderConstant.DELETE_CRITERIA_SUCCESS;
			 objServiceProviderDTO.setActionName("");
		  }
		  // end of code for deleting the criteria
		  
		  // for submitting the edited values in textbox
		  if("SUBMIT_EDIT_CRITERIA".equalsIgnoreCase(actionName))
		  {
			 String id = (String) objServiceProviderForm.getObjServiceProviderDTO().getCriteriaId();
			 logger.debug("------->"+id);
			 boolean edtCriteria;
			 edtCriteria = objServiceProviderBO.editCriteria(objServiceProviderForm);
			 forwardJsp  =  ServiceProviderConstant.EDIT_CRITERIA_SUCCESS;
			 objServiceProviderDTO.setActionName("");
		  }  
		// end of code for submitting the edited values in textbox
		  
		  // end of edit/delete the criteria for corresponding SP type
		  
		  if ("RESET_ACTION_EDIT".equalsIgnoreCase(actionName))
		  {
			  logger.debug("----------------> inside reset action");
			  objServiceProviderForm.getObjServiceProviderDTO().setCriteria("");
			  forwardJsp = ServiceProviderConstant.EDIT_CRITERIA;
			  objServiceProviderDTO.setActionName("");
		  }
		  
		  //added by shruti---1 st april 2014
		  if ("setPanCardFlag".equalsIgnoreCase(actionName))
		  {
			  //added by shruti---28 april 2014
			  objServiceProviderForm.getObjServiceProviderDTO().setForm60(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setForm60Doc(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setForm60Upload(null);
			  //end
			  objServiceProviderDTO.setPancardFlag(objServiceProviderForm.getObjServiceProviderDTO().getPancardFlag());
			  objServiceProviderForm.getObjServiceProviderDTO().setPancardFlag(objServiceProviderDTO.getPancardFlag());
			  objServiceProviderForm.setPanCardFlag(objServiceProviderDTO.getPancardFlag());
			  forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
			  objServiceProviderDTO.setActionName("");
		  }
		  if ("setPanCardFlagEditable".equalsIgnoreCase(actionName))
		  {
			  //added by shruti---28 april 2014
			  objServiceProviderForm.getObjServiceProviderDTO().setForm60(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setForm60Doc(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setForm60Upload(null);
			  //end
			  objServiceProviderDTO.setPancardFlag(objServiceProviderForm.getObjServiceProviderDTO().getPancardFlag());
			  objServiceProviderForm.getObjServiceProviderDTO().setPancardFlag(objServiceProviderDTO.getPancardFlag());
			  objServiceProviderForm.setPanCardFlag(objServiceProviderDTO.getPancardFlag());
			  forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			  objServiceProviderDTO.setActionName("");
		  }
		  if ("setPanCardFlagRenew".equalsIgnoreCase(actionName))
		  {
			  //added by shruti---28 april 2014
			  objServiceProviderForm.getObjServiceProviderDTO().setForm60(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setForm60Doc(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setForm60Upload(null);
			  //end
			  objServiceProviderDTO.setPancardFlag(objServiceProviderForm.getObjServiceProviderDTO().getPancardFlag());
			  objServiceProviderForm.getObjServiceProviderDTO().setPancardFlag(objServiceProviderDTO.getPancardFlag());
			  objServiceProviderForm.setPanCardFlag(objServiceProviderDTO.getPancardFlag());
			  forwardJsp=ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			  objServiceProviderDTO.setActionName("");
		  }
		  if ("setForm60Flag".equalsIgnoreCase(actionName))
		  {
			  //added by shruti---28 april 2014
			  objServiceProviderForm.getObjServiceProviderDTO().setPanCard(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setPancardDoc(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setPancardUpload(null);
			  //end
			  objServiceProviderDTO.setForm60Flag(objServiceProviderForm.getObjServiceProviderDTO().getForm60Flag());
			  objServiceProviderForm.getObjServiceProviderDTO().setForm60Flag(objServiceProviderDTO.getForm60Flag());
			  objServiceProviderForm.setForm60Flag(objServiceProviderDTO.getForm60Flag());
			  forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
			  objServiceProviderDTO.setActionName("");
		  }
		  if ("setForm60FlagEditable".equalsIgnoreCase(actionName))
		  {
			  //added by shruti---28 april 2014
			  objServiceProviderForm.getObjServiceProviderDTO().setPanCard(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setPancardDoc(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setPancardUpload(null);
			  //end
			  objServiceProviderDTO.setForm60Flag(objServiceProviderForm.getObjServiceProviderDTO().getForm60Flag());
			  objServiceProviderForm.getObjServiceProviderDTO().setForm60Flag(objServiceProviderDTO.getForm60Flag());
			  objServiceProviderForm.setForm60Flag(objServiceProviderDTO.getForm60Flag());
			  forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			  objServiceProviderDTO.setActionName("");
		  }
		  if ("setForm60FlagRenew".equalsIgnoreCase(actionName))
		  {
			  //added by shruti---28 april 2014
			  objServiceProviderForm.getObjServiceProviderDTO().setPanCard(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setPancardDoc(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setPancardUpload(null);
			  //end
			  objServiceProviderDTO.setForm60Flag(objServiceProviderForm.getObjServiceProviderDTO().getForm60Flag());
			  objServiceProviderForm.getObjServiceProviderDTO().setForm60Flag(objServiceProviderDTO.getForm60Flag());
			  objServiceProviderForm.setForm60Flag(objServiceProviderDTO.getForm60Flag());
			  forwardJsp=ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			  objServiceProviderDTO.setActionName("");
		  }
		  //end
		  
		// for viewing the criteria for corresponding SP type
		  if (request.getParameter("modName") != null) 
		  {
		  
		  if (request.getParameter("modName").equalsIgnoreCase("ServiceProvider") && request.getParameter("fnName").equalsIgnoreCase("ViewCriteria"))
		  {
			  objServiceProviderForm.getObjServiceProviderDTO().setIschecked(1);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsempty(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setRadioSelect("1");
			  logger.debug("------------------->inside view criteria action");
			  String value= (String)objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect();
			  logger.debug("------->"+value);
			  ArrayList criteriaList = objServiceProviderBO.getCriterias(value);
		      logger.info("--------------->"+criteriaList.size());
		      if(criteriaList.size()==0)
		      {
		      		objServiceProviderForm.getObjServiceProviderDTO().setEditDeleteCriteria(null);
		      		objServiceProviderForm.getObjServiceProviderDTO().setIsempty(1);
		      }
		      else
		      {
		    	  objServiceProviderForm.getObjServiceProviderDTO().setIsempty(0);
		      	  objServiceProviderForm.getObjServiceProviderDTO().setEditDeleteCriteria(criteriaList);
		      }
			  forwardJsp = ServiceProviderConstant.VIEW_CRITERIA;
			  logger.info("--------------->showing view criteria screen");
			  objServiceProviderDTO.setActionName("");
		  }
		  }
		  
		  if ("VIEW_ACTION".equalsIgnoreCase(actionName))
		  {
			  logger.debug("----------------> inside SP Type action");
			  objServiceProviderForm.getObjServiceProviderDTO().setIschecked(1);
			  String value= (String)objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect();
			  logger.debug("------->"+value);
			  ArrayList criteriaList = objServiceProviderBO.getCriterias(value);
		      logger.info("--------------->"+criteriaList.size());
		      if(criteriaList.size()==0)
		      {
		      		objServiceProviderForm.getObjServiceProviderDTO().setEditDeleteCriteria(null);
		      		objServiceProviderForm.getObjServiceProviderDTO().setIsempty(1);
		      }
		      else
		      {
		    	  objServiceProviderForm.getObjServiceProviderDTO().setIsempty(0);
		      	  objServiceProviderForm.getObjServiceProviderDTO().setEditDeleteCriteria(criteriaList);
		      }
		      request.setAttribute("criteriaList", objServiceProviderForm.getObjServiceProviderDTO().getEditDeleteCriteria());
		      forwardJsp  =  ServiceProviderConstant.VIEW_CRITERIA;
			  objServiceProviderDTO.setActionName("");
		  }
		  // end of viewing the criteria for corresponding SP type
		 
		  //for adding terms and conditions and checking the type of SP
		  if (request.getParameter("modName") != null) 
		  {
				if (request.getParameter("modName").equalsIgnoreCase("ServiceProvider") && request.getParameter("fnName").equalsIgnoreCase("AddTermsConditions"))
				{
					objServiceProviderForm.getObjServiceProviderDTO().setIschecked(0);
					objServiceProviderForm.getObjServiceProviderDTO().setRadioSelect("");
					logger.debug("------------------->inside add terms and conditions action");
					forwardJsp = ServiceProviderConstant.ADD_TERMS_CONDITIONS;
					logger.info("--------------->showing add criteria screen");
					objServiceProviderDTO.setActionName("");
				}
			}// end of checking the type of SP
		  
		  
		// for adding terms and conditions after checking the type of SP
		  
		  if("spType".equalsIgnoreCase(actionName))
		  {
			  logger.debug("----------------> inside SP Type action");
			  objServiceProviderForm.getObjServiceProviderDTO().setIschecked(1);
			  objServiceProviderForm.getObjServiceProviderDTO().setCriteria("");
			  String value= (String)objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect();
			  logger.debug("------->"+value);
			  forwardJsp = ServiceProviderConstant.ADD_TERMS_CONDITIONS;
			  objServiceProviderDTO.setActionName("");
		  }
      
		  if ("ADD_TERMS_CONDITIONS".equalsIgnoreCase(actionName))
		  {
			  logger.debug("----------------> inside add criteria action");
			  boolean addCriteria;
			  addCriteria = objServiceProviderBO.insertTandC(objServiceProviderForm);
			  logger.debug("----------------> after successful insertion");
			  forwardJsp = ServiceProviderConstant.ADD_TERMS_CONDITIONS_SUCCESS;
			  objServiceProviderDTO.setActionName("");
		  }// end of adding terms and conditions after checking the type of SP
		  
		  if ("RESET_ACTION_TC".equalsIgnoreCase(actionName))
		  {
			  logger.debug("----------------> inside reset action");
			  objServiceProviderForm.getObjServiceProviderDTO().setCriteria("");
			  forwardJsp = ServiceProviderConstant.ADD_TERMS_CONDITIONS;
			  objServiceProviderDTO.setActionName("");
		  }
		  
		// for edit/delete the terms and conditions for corresponding SP type
		  if (request.getParameter("modName") != null) 
		  {
			  if (request.getParameter("modName").equalsIgnoreCase("ServiceProvider") && request.getParameter("fnName").equalsIgnoreCase("Edit/DeleteTermsConditions"))
			  {
				  logger.debug("----->inside edit/deleteTandC screen");
				  objServiceProviderForm.getObjServiceProviderDTO().setRadioSelect("");
				  objServiceProviderForm.getObjServiceProviderDTO().setIschecked(0);
				  forwardJsp = ServiceProviderConstant.EDIT_DELETE_TERMS_CONDITIONS;
				  logger.debug("----->showing page of edit/deleteTandC screen");
			  }
		  }
		  
		  if("spTypeEdit".equalsIgnoreCase(actionName))
		  {
			  logger.debug("----------------> inside SP Type action of edit/delete terms and conditions");
			  objServiceProviderForm.getObjServiceProviderDTO().setIschecked(1);
			  String value= (String)objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect();
			  logger.debug("------->"+value);
			  ArrayList tcList = objServiceProviderBO.getTC(value);
		      logger.info("--------------->"+tcList.size());
		      if(tcList.size()==0)
		      {
		      		  objServiceProviderForm.getObjServiceProviderDTO().setEditDeleteTC(null);
		      		objServiceProviderForm.getObjServiceProviderDTO().setIsempty(1);
		      }
		      else
		      {
		    	  objServiceProviderForm.getObjServiceProviderDTO().setIsempty(0);
		      		objServiceProviderForm.getObjServiceProviderDTO().setEditDeleteTC(tcList);
		      }
		      	  
		      request.setAttribute("tcList", objServiceProviderForm.getObjServiceProviderDTO().getEditDeleteTC());
		      forwardJsp  =  ServiceProviderConstant.EDIT_DELETE_TERMS_CONDITIONS;
			  objServiceProviderDTO.setActionName("");
		  }
		  
		  if ("EDIT_CRITERIA_TC".equalsIgnoreCase(actionName))
		  {
			  String[] idName = objServiceProviderForm.getObjServiceProviderDTO().getRadioSelectCriteria().toString().split("~");
			  objServiceProviderForm.getObjServiceProviderDTO().setTcId(idName[0]);
			  String id = idName[0];
			  logger.debug("------->"+id);
			  objServiceProviderForm.getObjServiceProviderDTO().setTcName(idName[1]);
			  String name = (String) objServiceProviderForm.getObjServiceProviderDTO().getTcName();
			  logger.debug("------->"+name);
			  objServiceProviderForm.getObjServiceProviderDTO().setCriteria(name);
			  forwardJsp  =  ServiceProviderConstant.EDIT_TERMS_CONDITIONS;
			  objServiceProviderDTO.setActionName("");
		  }
		  if ("DELETE_CRITERIA_TC".equalsIgnoreCase(actionName))
		  {
			  String[] idName = objServiceProviderForm.getObjServiceProviderDTO().getRadioSelectCriteria().toString().split("~");
			  objServiceProviderForm.getObjServiceProviderDTO().setTcId(idName[0]);
			  String id = idName[0];
			  logger.debug("------->"+id);
			  objServiceProviderForm.getObjServiceProviderDTO().setTcName(idName[1]);
			 boolean delTC;
			 delTC = objServiceProviderBO.deleteTC(id);
			 forwardJsp  =  ServiceProviderConstant.DELETE_TERMS_CONDITIONS_SUCCESS;
			 objServiceProviderDTO.setActionName("");
		  }
		  
		  if("SUBMIT_EDIT_CRITERIA_TC".equalsIgnoreCase(actionName))
		  {
			 String id = (String) objServiceProviderForm.getObjServiceProviderDTO().getTcId();
			 logger.debug("------->"+id);
			 boolean edtTC;
			 edtTC = objServiceProviderBO.editTC(objServiceProviderForm);
			 forwardJsp  =  ServiceProviderConstant.EDIT_TERMS_CONDITIONS_SUCCESS;
			 objServiceProviderDTO.setActionName("");
		  }  
		  // end of edit/delete the terms and conditions for corresponding SP type
		  
		  if ("RESET_ACTION_EDIT_TC".equalsIgnoreCase(actionName))
		  {
			  logger.debug("----------------> inside reset action");
			  objServiceProviderForm.getObjServiceProviderDTO().setCriteria("");
			  forwardJsp = ServiceProviderConstant.EDIT_TERMS_CONDITIONS;
			  objServiceProviderDTO.setActionName("");
		  }
		  
		// for viewing the criteria for corresponding SP type
		  if (request.getParameter("modName") != null) 
		  {
		  
		  if (request.getParameter("modName").equalsIgnoreCase("ServiceProvider") && request.getParameter("fnName").equalsIgnoreCase("ViewTermsConditions"))
		  {
			  objServiceProviderForm.getObjServiceProviderDTO().setIschecked(1);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsempty(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setRadioSelect("1");
			  logger.debug("------------------->inside view terms and conditions action");
			  String value= (String)objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect();
			  logger.debug("------->"+value);
			  ArrayList tCList = objServiceProviderBO.getTC(value);
		      logger.info("--------------->"+tCList.size());
		      if(tCList.size()==0)
		      {
		      		objServiceProviderForm.getObjServiceProviderDTO().setEditDeleteTC(null);
		      		objServiceProviderForm.getObjServiceProviderDTO().setIsempty(1);
		      }
		      
		      else
		      {
		    	  objServiceProviderForm.getObjServiceProviderDTO().setIsempty(0);
		      	  objServiceProviderForm.getObjServiceProviderDTO().setEditDeleteTC(tCList);
		      }
			  forwardJsp = ServiceProviderConstant.VIEW_TERMS_CONDITIONS;
			  logger.info("--------------->showing view terms and conditions screen");
			  objServiceProviderDTO.setActionName("");
			  
		  }
		  }
		  
		  if ("VIEW_ACTION_TC".equalsIgnoreCase(actionName))
		  {
			  logger.debug("----------------> inside SP Type action");
			  objServiceProviderForm.getObjServiceProviderDTO().setIschecked(1);
			  String value= (String)objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect();
			  logger.debug("------->"+value);
			  ArrayList tCList = objServiceProviderBO.getTC(value);
		      logger.info("--------------->"+tCList.size());
		      if(tCList.size()==0)
		      {
		      	  objServiceProviderForm.getObjServiceProviderDTO().setEditDeleteTC(null);
		      	  objServiceProviderForm.getObjServiceProviderDTO().setIsempty(1);
		      }
		      else
		      {
		    	  objServiceProviderForm.getObjServiceProviderDTO().setIsempty(0);
		      	  objServiceProviderForm.getObjServiceProviderDTO().setEditDeleteTC(tCList);
		      }
		      request.setAttribute("tCList", objServiceProviderForm.getObjServiceProviderDTO().getEditDeleteTC());
		      forwardJsp  =  ServiceProviderConstant.VIEW_TERMS_CONDITIONS;
			  objServiceProviderDTO.setActionName("");
		  }
		  // end of viewing the terms and conditions for corresponding SP type
		  
		  if (request.getParameter("modName")!=null)
		  {
			  if (request.getParameter("modName").equalsIgnoreCase("ServiceProvider") && request.getParameter("fnName").equalsIgnoreCase("SpApplication"))
			  {
				  logger.debug("------> inside online application action");
				  String userID = (String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
				  //String lang=(String)session.getAttribute("languageLocale");
				  ArrayList pendingApps = objServiceProviderBO.getDroDetailsIfAlreadyFiled(objServiceProviderForm,lang);
				  logger.debug("*******"+pendingApps.size());
				  if (pendingApps.size()>0)
				  { 
				  objServiceProviderForm.getObjServiceProviderDTO().setIsAlreadyFiled(1);	
				  objServiceProviderForm.getObjServiceProviderDTO().setDroDetails(pendingApps);
				  forwardJsp = ServiceProviderConstant.SP_APP_FORM_SUCCESS_OR_ALREADY_FILED;  
				  }
				  else
				  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsAlreadyFiled(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(0);
			 	  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setSpType("Select");
				  objServiceProviderForm.getObjServiceProviderDTO().setSpTypeList(objServiceProviderBO.getSpType(lang));
				  objServiceProviderForm.getObjServiceProviderDTO().setCriteriaPopUp("");
				  forwardJsp = ServiceProviderConstant.VIEW_SP_DETLS_FROM_USER_REG;
				  }
				  objServiceProviderDTO.setActionName("");
			  }
		  }
			  
		 if ("VIEW_DETAILS".equalsIgnoreCase(actionName))
		 {
			 	  objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(0);
			 	  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(0);
				  String id= objServiceProviderForm.getObjServiceProviderDTO().getSpType();
				  logger.debug("value is - "+id);
				  String userID = (String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
				  logger.debug("user id is --" +userID);
				  if (id.equalsIgnoreCase("1"))
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(1);
					  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(0);
					  ArrayList spDetls = new ArrayList();
					  spDetls = objServiceProviderBO.getSPDetailsInd(userID,lang);
					  logger.debug("------->"+spDetls.size());
					  if (spDetls.size()==0)
					  {
						  objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(null);
					  }
					  else
					  {
						  objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(spDetls);
					  }
					  logger.debug("***** get the sp type list"+objServiceProviderBO.getSpType(lang)); 
				  }
				  if (id.equalsIgnoreCase("2") || id.equalsIgnoreCase("3") || id.equalsIgnoreCase("4"))
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(1);
					  objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(0);
					  ArrayList spDetls = new ArrayList();
					  spDetls = objServiceProviderBO.getSPDetails(userID,lang);
					  logger.debug("------->"+spDetls.size());
					  if (spDetls.size()==0)
					  {
						  objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(null);
					  }
					  else
					  {
						  objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(spDetls);
					  }
					  logger.debug("***** get the sp type list"+objServiceProviderBO.getSpType(lang)); 
				  }
				  forwardJsp = ServiceProviderConstant.VIEW_SP_DETLS_FROM_USER_REG;
				  objServiceProviderDTO.setActionName("");
			  }
		  
		  if ("NEXT_PAGE_ACTION".equalsIgnoreCase(actionName))
		  {
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpBank(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setBankName("");
			  objServiceProviderForm.getObjServiceProviderDTO().setAuthPersName("");
			  objServiceProviderForm.getObjServiceProviderDTO().setDesignation("");
			  objServiceProviderForm.getObjServiceProviderDTO().setAddressSp("");
			  objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc("");
			  objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiGazDoc("");
			  objServiceProviderForm.getObjServiceProviderDTO().setDobCertiDoc("");
			  objServiceProviderForm.getObjServiceProviderDTO().setRentDetailDoc("");
			  objServiceProviderForm.getObjServiceProviderDTO().setHigherSecCertiDoc("");
			  objServiceProviderForm.getObjServiceProviderDTO().setPhotoImgDoc("");
			  objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiDoc("");
			  objServiceProviderForm.getObjServiceProviderDTO().setAgeProofDoc("");
			  objServiceProviderForm.getObjServiceProviderDTO().setSolvencyCertiDoc("");
			  objServiceProviderForm.getObjServiceProviderDTO().setPoliceCaseCertiDoc("");
			  objServiceProviderForm.getObjServiceProviderDTO().setPossessionBusinessDocDoc("");
			  objServiceProviderForm.getObjServiceProviderDTO().setHigherEduCertiDoc("");
			  objServiceProviderForm.getObjServiceProviderDTO().setAffidavitHardwareDoc("");
			  //added by shruti--2nd april 2014
			  objServiceProviderForm.getObjServiceProviderDTO().setPancardDoc("");
			  objServiceProviderForm.getObjServiceProviderDTO().setForm60Doc("");
			  objServiceProviderForm.setPanCardFlag("");
			  objServiceProviderForm.setForm60Flag("");
			  objServiceProviderForm.getObjServiceProviderDTO().setPancardFlag("");
			  objServiceProviderForm.getObjServiceProviderDTO().setForm60Flag("");
			  objServiceProviderForm.getObjServiceProviderDTO().setPanCard("");
			  objServiceProviderForm.getObjServiceProviderDTO().setForm60("");
			  //end
			  objServiceProviderForm.getObjServiceProviderDTO().setAmount("");
			  objServiceProviderForm.getObjServiceProviderDTO().setEducation("");
			  objServiceProviderForm.getObjServiceProviderDTO().setRadioSelect("");
			  objServiceProviderForm.getObjServiceProviderDTO().setRadioSelect1("");
			  objServiceProviderForm.getObjServiceProviderDTO().setTermsCond("");
			  //ADDED BY SHRUTI---20 MAY 2014
			  objServiceProviderForm.getObjServiceProviderDTO().setOpticalWatermarkFlag("");
			  
			  String id= objServiceProviderForm.getObjServiceProviderDTO().getSpType();
			  logger.debug("value is - "+id);
			  if(id.equalsIgnoreCase("2") || id.equalsIgnoreCase("3") || id.equalsIgnoreCase("4"))
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpBank(1);
			  }
			  
			  if(id.equalsIgnoreCase("1"))
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(1);
				  objServiceProviderForm.getObjServiceProviderDTO().setRadioSelect("");
			  }
			  
			  if(id.equalsIgnoreCase("2") || id.equalsIgnoreCase("3") || id.equalsIgnoreCase("4") || id.equalsIgnoreCase("1"))
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(1);
				  objServiceProviderForm.getObjServiceProviderDTO().setRadioSelectExp("N");
				  objServiceProviderForm.getObjServiceProviderDTO().setRadioSelectCh("N");
				  objServiceProviderForm.getObjServiceProviderDTO().setDistrict("Select");
				  objServiceProviderForm.getObjServiceProviderDTO().setTehsil("Select");
				  objServiceProviderForm.getObjServiceProviderDTO().setWard("Select");
				  objServiceProviderForm.getObjServiceProviderDTO().setMohalla("Select");
				  objServiceProviderForm.getObjServiceProviderDTO().setDistrictList(objServiceProviderBO.getDistrict(lang));
				  //end
			  }
			  forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
			  objServiceProviderDTO.setActionName("");
		  }
		  
		  if("LOAD_TEHSIL".equalsIgnoreCase(actionName))
		  {			  
			  String disttId =  objServiceProviderForm.getObjServiceProviderDTO().getDistrict();
			  logger.debug("District id is - "+disttId);
			  objServiceProviderForm.getObjServiceProviderDTO().setTehsilList(objServiceProviderBO.getTehsil(disttId,lang));
			  forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
			  objServiceProviderDTO.setActionName("");
		  }
		  //added by shruti---21 july 2014
		  if("LOAD_AREA".equalsIgnoreCase(actionName))
		  {		
			  String tehsilId =  objServiceProviderForm.getObjServiceProviderDTO().getTehsil();
			  logger.debug("tehsilId id is - "+tehsilId);
			  objServiceProviderForm.getObjServiceProviderDTO().setAreaList(objServiceProviderBO.getArea(lang));
			  forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
			  objServiceProviderDTO.setActionName("");
		  }
		  if("LOAD_SUB_AREA".equalsIgnoreCase(actionName))
		  {			  
			  String areaId =  objServiceProviderForm.getObjServiceProviderDTO().getAreaId();
			  logger.debug("area id is - "+areaId);
			  if("2".equalsIgnoreCase(objServiceProviderDTO.getAreaId()))
				{	
				  objServiceProviderDTO.setSubAreaList(objServiceProviderBO.getSubArea(lang,objServiceProviderDTO.getAreaId(),objServiceProviderDTO.getTehsil(),"Y"));
				}
				else
				{
					objServiceProviderDTO.setSubAreaList(objServiceProviderBO.getSubArea(lang,objServiceProviderDTO.getAreaId(),objServiceProviderDTO.getTehsil(),"N"));

				}
			  forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
			  objServiceProviderDTO.setActionName("");		 
		  }
		  if("LOAD_WARD".equalsIgnoreCase(actionName))
		  {			  
			  String subAreaId =  objServiceProviderForm.getObjServiceProviderDTO().getSubAreaId();
			  logger.debug("sub area id is - "+subAreaId);
			  objServiceProviderForm.getObjServiceProviderDTO().setWardPatwariList(objServiceProviderBO.getWardPatwari(lang,objServiceProviderDTO.getSubAreaId(),objServiceProviderDTO.getTehsil()));
			  forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
			  objServiceProviderDTO.setActionName("");
		  }
		  if("LOAD_COLONY".equalsIgnoreCase(actionName))
		  {			  
			  String wardPatwariId =  objServiceProviderForm.getObjServiceProviderDTO().getWardPatwariId();
			  logger.debug("wardPatwariId id is - "+wardPatwariId);
			  objServiceProviderDTO.setColonyList(objServiceProviderBO.getColonyName(lang,objServiceProviderDTO.getWardPatwariId()));
			  forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
			  objServiceProviderDTO.setActionName("");
		  }
		  //end
		  
		  if ("PREVIOUS_ACTION".equalsIgnoreCase(actionName))
		  {
			  forwardJsp=ServiceProviderConstant.VIEW_SP_DETLS_FROM_USER_REG;
			  objServiceProviderDTO.setActionName("");
		  }
		  	  
		// for submission of SP details for the first time while filing the application.
		  
		  if ("SUBMIT_ACTION".equalsIgnoreCase(actionName))
		  {
			  boolean insertTxn = false;
			if( objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect()!="" && objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect1()!="")
			  {
				  String language = objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect()+"&"+objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect1();
				  objServiceProviderForm.getObjServiceProviderDTO().setLangKnown(language);
			  }
			  
			  if(("").equals(objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect()) && objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect1()!="")
			  {
				  String language = (String) objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect1();
				  logger.debug("------>"+language);
				  objServiceProviderForm.getObjServiceProviderDTO().setLangKnown(language);
			  }
			  
			  if(objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect()!="" && ("").equals(objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect1()))
			  {
				  String language = (String) objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect();
				  objServiceProviderForm.getObjServiceProviderDTO().setLangKnown(language);
			  }
			  
			  objServiceProviderForm.getObjServiceProviderDTO().setNewOrRenewalFlag("New");
			  
			  insertTxn = objServiceProviderBO.insertSpDetls(objServiceProviderForm);
			  
			  logger.debug("Success--->"+insertTxn);
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Criminal_Activities_Affidavit."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					//String docPath = "D:/Upload/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					  String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGaz()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGaz();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Character_Ceertificate_Gazette."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
				  
				    
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getDobCerti()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getDobCerti();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="DOB_Certificate."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
				   
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getRentDetail()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getRentDetail();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Rent_Details."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCerti()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCerti();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Higher_Secondary_Certificate."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
				   
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getPhotoImg()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPhotoImg();
				  String fileExt = getFileExtension(photo.getFileName());
				  //String docName ="Photo."+fileExt;
				  
				  String docName ="Photo."+pr.getValue("sp_photo_extension_param");
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
				    
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getCharacterCerti()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getCharacterCerti();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Character_Certificate."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
				  
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getAgeProof()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAgeProof();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Age_Proof."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm);
				  
			  }
		/*	  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCerti()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCerti();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Solvency_Certificate."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm);
				  
			  }
			  */
			  if (objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCerti()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCerti();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Affidavit_Police_Case."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm);
				    
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDoc()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDoc();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Business_Premises."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm);
				   
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCerti()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCerti();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Education_Qualification."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm);
				  
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Hardware_Available."+fileExt;;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm);
				  
			  }
			  //added by shruti---1 april 2014
			  if(objServiceProviderForm.getPanCardFlag()!=""||objServiceProviderForm.getPanCardFlag()!=null)
			  {
				  if("Y".equalsIgnoreCase(objServiceProviderForm.getPanCardFlag()))
				  {
					  if (objServiceProviderForm.getObjServiceProviderDTO().getPancardUpload()!=null)
						  //if (objServiceProviderForm.getObjServiceProviderDTO().getPancardDoc()!=null)
						  {
							  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPancardUpload();
							  String fileExt = getFileExtension(photo.getFileName());
							  String docName ="PanCard."+fileExt;;
							  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
							  
							  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
								{
								String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
								objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
								}
								else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
								{
								String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
								//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
								objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
								}
								else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
								{
								String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
								objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
								}
								else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
								{
								String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
								objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
								}
							  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
							  boolean up=uploadFile(photo,docName,docPath);
							  boolean insertTxn1 = false;
							  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm);
							  
						  }
				  }
			  }
			  if(objServiceProviderForm.getForm60Flag()!=""||objServiceProviderForm.getForm60Flag()!=null)
			  {
				  if("Y".equalsIgnoreCase(objServiceProviderForm.getForm60Flag()))
				  {
					  if (objServiceProviderForm.getObjServiceProviderDTO().getForm60Upload()!=null)
					  {
						  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getForm60Upload();
						  String fileExt = getFileExtension(photo.getFileName());
						  String docName ="Form60_61."+fileExt;;
						  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
						  
						  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
							{
							String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
							objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
							}
							else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
							{
							String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
							objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
							}
							else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
							{
							String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
							objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
							}
							else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
							{
							String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
							objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
							}
						  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
						  boolean up=uploadFile(photo,docName,docPath);
						  boolean insertTxn1 = false;
						  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm);
						  
					  }
				  }
			  }
		//changes to insert the old documents of SP to renew license by santosh.
	    if("Y".equalsIgnoreCase(objServiceProviderForm.getPanCardFlag()) 
	    		|| "Y".equalsIgnoreCase(objServiceProviderForm.getForm60Flag())){
		  try {
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpOldDocDetls(objServiceProviderForm);
				  logger.debug("Old document inserted successfully :: "+insertTxn1);  
			  
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("Exception occurred in insertSpOldDocDetls fun "+e.getMessage());
			} 
	     }
			  //end
			  
			  String distt= objServiceProviderForm.getObjServiceProviderDTO().getDistrict();
			  logger.debug(distt);
			  ArrayList drOfficeDetails = new ArrayList();
			  
			  drOfficeDetails= objServiceProviderBO.getDroDetails(distt,lang);
			  logger.debug("size of arraylist: "+drOfficeDetails.size());
			  
			  if (drOfficeDetails.size()==0)
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setDroDetails(null);
			  }
			  else
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setDroDetails(drOfficeDetails);
			  }
			  
			  forwardJsp=ServiceProviderConstant.SP_APP_FORM_SUCCESS_OR_ALREADY_FILED;
			  
			  objServiceProviderDTO.setActionName("");
		  }
		  
		  // end of code for submission of SP details for the first time while filing the application.
		  
		  // for pop up of terms and conditions
		  if (request.getParameter("actionName")!=null)
		  {
		  if (request.getParameter("actionName").equalsIgnoreCase("termsConditions"))
		  {
			  String value = objServiceProviderForm.getObjServiceProviderDTO().getSpType();
			  
			  objServiceProviderForm.getObjServiceProviderDTO().setIschecked(1);
			  
			  
			  ArrayList tCList = objServiceProviderBO.getTC(value);
		      logger.info("--------------->"+tCList.size());
		      if(tCList.size()==0)
		      {
		      	  objServiceProviderForm.getObjServiceProviderDTO().setEditDeleteTC(null);
		      	  objServiceProviderForm.getObjServiceProviderDTO().setIsempty(1);
		      }
		      
		      else
		      {
		    	  objServiceProviderForm.getObjServiceProviderDTO().setIsempty(0);
		      	  objServiceProviderForm.getObjServiceProviderDTO().setEditDeleteTC(tCList);
		      }
		      	  
		      request.setAttribute("tCList", objServiceProviderForm.getObjServiceProviderDTO().getEditDeleteTC());
			  
			  
			  forwardJsp=ServiceProviderConstant.POP_UP_TERMS_CONDITIONS;
			  objServiceProviderDTO.setActionName("");
			  //added by shruti---23 april 2014 for resolving terms and condtions pop up issue
			  actionName="";
			  
		  }
		  }
		  
		// end of code for pop up of terms and conditions
		  
		//------upload file coding------
		  
		  if ("setUploadFile".equalsIgnoreCase(actionName)) {
			  setUploadFile(request, objServiceProviderForm,
						objServiceProviderDTO);
		  }
		  
		  if ("setUploadFile1".equalsIgnoreCase(actionName)) {
			  setUploadFile1(request, objServiceProviderForm,
						objServiceProviderDTO);
		
		  }
		  
		  if ("setUploadFile2".equalsIgnoreCase(actionName)) {
			  setUploadFile2(request, objServiceProviderForm,
						objServiceProviderDTO);
	
		  }
		  
		  if ("setUploadFile3".equalsIgnoreCase(actionName)) {
			  setUploadFile3(request, objServiceProviderForm,
						objServiceProviderDTO);
				
		  }
		  
		  if ("setUploadFile4".equalsIgnoreCase(actionName)) {
			  setUploadFile4(request, objServiceProviderForm,
						objServiceProviderDTO);
				
		  }
		  
		  if ("setUploadFile5".equalsIgnoreCase(actionName)) {
			  setUploadFile5(request, objServiceProviderForm,
						objServiceProviderDTO);
				
		  }
		  
		  if ("setUploadFile6".equalsIgnoreCase(actionName)) {
			  setUploadFile6(request, objServiceProviderForm,
						objServiceProviderDTO);
				
		  }
		  
		  if ("setUploadFile7".equalsIgnoreCase(actionName)) {
			  setUploadFile7(request, objServiceProviderForm,
						objServiceProviderDTO);
				
		  }
		  
		  /*if ("setUploadFile8".equalsIgnoreCase(actionName)) {
			  setUploadFile8(request, objServiceProviderForm,
						objServiceProviderDTO);
				
		  }*/
		  
		  if ("setUploadFile9".equalsIgnoreCase(actionName)) {
			  setUploadFile9(request, objServiceProviderForm,
						objServiceProviderDTO);
			
		  }
		  
		  if ("setUploadFile10".equalsIgnoreCase(actionName)) {

			  setUploadFile10(request, objServiceProviderForm,
						objServiceProviderDTO);
		  }
		  
		  if ("setUploadFile11".equalsIgnoreCase(actionName)) {

				setUploadFile11(request, objServiceProviderForm,
						objServiceProviderDTO);
		  }
		  
		  if ("setUploadFile12".equalsIgnoreCase(actionName)) {
			  setUploadFile12(request, objServiceProviderForm,
						objServiceProviderDTO);
		
		  }
		  //added by shruti----31 march 2014
		  if ("setUploadPanCardFile".equalsIgnoreCase(actionName)) {
			  setPanCardUpload(request,
						objServiceProviderForm, objServiceProviderBO,
						objServiceProviderDTO,session);
				
		  }
		  if ("setUploadForm60File".equalsIgnoreCase(actionName)) {

			  setForm60Upload(request,
						objServiceProviderForm, objServiceProviderBO,
						objServiceProviderDTO,session);
		  }
		  //end
		  
		  //------end of upload file coding------

		  // Changes in equalsIgnoreCase cond for term condition page
			// ----------------------upload remove file coding----------
			if ("removeUploadFile".equalsIgnoreCase(actionName)) {
				try {

					FormFile fname = (FormFile) objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal();
					//String docPath = "D:/Upload/05/"+eForm.getMainTxnId()+"_"+"nonJud"+"_"+fname.getFileName();
					if (fname!=null)
					{
					if(("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
					removeFile(fname.getFileName(), docPath);
					objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc("");
					}
					else if (("2").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
					{
					String docPath = pr.getValue("igrs_upload_path")+"=/12/02"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
					removeFile(fname.getFileName(), docPath);
					objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc("");
					}
					else if (("3").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
					removeFile(fname.getFileName(), docPath);
					objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc("");
					}
					else if (("4").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
					removeFile(fname.getFileName(), docPath);
					objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc("");
					}
					
					if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getFirstFileSP()))
					{
						forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
					}
					
					else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getFirstFileDR()))
					{
						forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
					}
					
					else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getFirstFileRenewal()))
					{
						forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
					}
					
					else
					{
					
					forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
					}
					
					}
					
					else
					{
						String name = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminalDoc();
						String path = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminalPath();
						removeFile(name, path);
						objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc("");
						
						if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getFirstFileSP()))
						{
							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
						}
						
						else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getFirstFileDR()))
						{
							forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
						}
						
						else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getFirstFileRenewal()))
						{
							forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
						}
					}

					objServiceProviderDTO.setActionName("");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if ("removeUploadFile1".equalsIgnoreCase(actionName)) {
				try {

					FormFile fname = (FormFile) objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGaz();
					//String docPath = "D:/Upload/05/"+eForm.getMainTxnId()+"_"+"nonJud"+"_"+fname.getFileName();
					if (fname!=null)
					{
					if(("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
					removeFile(fname.getFileName(), docPath);
					objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiGazDoc("");
					}
					else if (("2").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
					removeFile(fname.getFileName(), docPath);
					objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiGazDoc("");
					}
					else if (("3").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
					removeFile(fname.getFileName(), docPath);
					objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiGazDoc("");
					}
					else if (("4").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
					removeFile(fname.getFileName(), docPath);
					objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiGazDoc("");
					}
					
					if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSecondFileSP()))
					{
						forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
					}
					
					else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSecondFileDR()))
					{
						forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
					}
					
					else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSecondFileRenewal()))
					{
						forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
					}
					else
					{
					
					forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
					}
					}
					
					else
					{
						String name = objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGazDoc();
						String path = objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGazPath();
						removeFile(name, path);
						objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiGazDoc("");
						
						if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSecondFileSP()))
						{
							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
						}
						
						else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSecondFileDR()))
						{
							forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
						}
						
						else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSecondFileRenewal()))
						{
							forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
						}
					}

					objServiceProviderDTO.setActionName("");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if ("removeUploadFile2".equalsIgnoreCase(actionName)) {
				
				removeUploadFile2(objServiceProviderForm, objServiceProviderDTO);
			}
			
			if ("removeUploadFile3".equalsIgnoreCase(actionName)) {
				
				removeUploadFile3(objServiceProviderForm, objServiceProviderDTO);
			}
			
			if ("removeUploadFile4".equalsIgnoreCase(actionName)) {
				removeUploadFile4(objServiceProviderForm, objServiceProviderDTO);
			
			}
			
			if ("removeUploadFile5".equalsIgnoreCase(actionName)) {
				removeUploadFile5(objServiceProviderForm, objServiceProviderDTO);
		
			}
			
			if ("removeUploadFile6".equalsIgnoreCase(actionName)) {
				removeUploadFile6(objServiceProviderForm, objServiceProviderDTO);
				
			}
			
			if ("removeUploadFile7".equalsIgnoreCase(actionName)) {
				removeUploadFile7(objServiceProviderForm, objServiceProviderDTO);
				
			}
			
			/*if ("removeUploadFile8".equalsIgnoreCase(actionName)) {
				removeUploadFile8(objServiceProviderForm, objServiceProviderDTO);
				
			}*/
			
			if ("removeUploadFile9".equalsIgnoreCase(actionName)) {
				removeUploadFile9(objServiceProviderForm, objServiceProviderDTO);
				
			}
			
			if ("removeUploadFile10".equalsIgnoreCase(actionName)) {
				removeUploadFile10(objServiceProviderForm, objServiceProviderDTO);
				
			}
			
			if ("removeUploadFile11".equalsIgnoreCase(actionName)) {
				removeUploadFile11(objServiceProviderForm, objServiceProviderDTO);
				
			}
			
			if ("removeUploadFile12".equalsIgnoreCase(actionName)) {
				removeUploadFile12(objServiceProviderForm, objServiceProviderDTO);
				
			}
			//added by shruti----1 april 2014
			if ("removeUploadPanCard".equalsIgnoreCase(actionName)) {
				removeUploadedPanCard(request,
						objServiceProviderForm, objServiceProviderBO,
						objServiceProviderDTO,session);
			}
			if ("removeUploadForm60".equalsIgnoreCase(actionName)) {
				removeUploadedForm60(request,
						objServiceProviderForm, objServiceProviderBO,
						objServiceProviderDTO,session);
			}
			//end
			
			// end :upload removal coding
		  
		  if (request.getParameter("actionName")!=null)
		  {
			  if (request.getParameter("actionName").equalsIgnoreCase("downLoad1"))
			  {
				  try
				  {
				  byte contents[] = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal1();
				  String fileName = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminalDoc();
				  downloadDocument(response, contents, fileName);
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
		  }
		  
		  if (request.getParameter("actionName")!=null)
		  {
			  if (request.getParameter("actionName").equalsIgnoreCase("downLoad2"))
			  {
				  try
				  {
				  byte contents[] = objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGaz1();
				  String fileName = objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGazDoc();
				  downloadDocument(response, contents, fileName);
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
		  }
		  
		  if (request.getParameter("actionName")!=null)
		  {
			  if (request.getParameter("actionName").equalsIgnoreCase("downLoad3"))
			  {
				  try
				  {
				  //FormFile doc = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal();
				  byte contents[] = objServiceProviderForm.getObjServiceProviderDTO().getDobCerti1();
				  String fileName = objServiceProviderForm.getObjServiceProviderDTO().getDobCertiDoc();
				  downloadDocument(response, contents, fileName);
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
		  }
		  
		  if (request.getParameter("actionName")!=null)
		  {
			  if (request.getParameter("actionName").equalsIgnoreCase("downLoad4"))
			  {
				  try
				  {
				  //FormFile doc = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal();
				  byte contents[] = objServiceProviderForm.getObjServiceProviderDTO().getRentDetail1();
				  String fileName = objServiceProviderForm.getObjServiceProviderDTO().getRentDetailDoc();
				  downloadDocument(response, contents, fileName);
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
		  }
		  
		  if (request.getParameter("actionName")!=null)
		  {
			  if (request.getParameter("actionName").equalsIgnoreCase("downLoad5"))
			  {
				  try
				  {
				  //FormFile doc = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal();
				  byte contents[] = objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCerti1();
				  String fileName = objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCertiDoc();
				  downloadDocument(response, contents, fileName);
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
		  }
		  
		  if (request.getParameter("actionName")!=null)
		  {
			  if (request.getParameter("actionName").equalsIgnoreCase("downLoad6"))
			  {
				  try
				  {
				  //FormFile doc = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal();
				  byte contents[] = objServiceProviderForm.getObjServiceProviderDTO().getPhotoImg1();
				  String fileName = objServiceProviderForm.getObjServiceProviderDTO().getPhotoImgDoc();
				  downloadDocument(response, contents, fileName);
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
		  }
		  
		  if (request.getParameter("actionName")!=null)
		  {
			  if (request.getParameter("actionName").equalsIgnoreCase("downLoad7"))
			  {
				  try
				  {
				  //FormFile doc = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal();
				  byte contents[] = objServiceProviderForm.getObjServiceProviderDTO().getCharacterCerti1();
				  String fileName = objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiDoc();
				  downloadDocument(response, contents, fileName);
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
		  }
		  
		  if (request.getParameter("actionName")!=null)
		  {
			  if (request.getParameter("actionName").equalsIgnoreCase("downLoad8"))
			  {
				  try
				  {
				  //FormFile doc = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal();
				  byte contents[] = objServiceProviderForm.getObjServiceProviderDTO().getAgeProof1();
				  String fileName = objServiceProviderForm.getObjServiceProviderDTO().getAgeProofDoc();
				  downloadDocument(response, contents, fileName);
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
		  }
		  
		  /*if (request.getParameter("actionName")!=null)
		  {
			  if (request.getParameter("actionName").equalsIgnoreCase("downLoad"))
			  {
				  try
				  {
				  //FormFile doc = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal();
				  byte contents[] = objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCerti1();
				  String fileName = objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCertiDoc();
				  downloadDocument(response, contents, fileName);
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
		  }*/
		  
		  if (request.getParameter("actionName")!=null)
		  {
			  if (request.getParameter("actionName").equalsIgnoreCase("downLoad10"))
			  {
				  try
				  {
				  //FormFile doc = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal();
				  byte contents[] = objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCerti1();
				  String fileName = objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCertiDoc();
				  downloadDocument(response, contents, fileName);
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
		  }
		  
		  if (request.getParameter("actionName")!=null)
		  {
			  if (request.getParameter("actionName").equalsIgnoreCase("downLoad11"))
			  {
				  try
				  {
				  //FormFile doc = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal();
				  byte contents[] = objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDoc1();
				  String fileName = objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDocDoc();
				  downloadDocument(response, contents, fileName);
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
		  }
		  
		  if (request.getParameter("actionName")!=null)
		  {
			  if (request.getParameter("actionName").equalsIgnoreCase("downLoad12"))
			  {
				  try
				  {
				  //FormFile doc = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal();
				  byte contents[] = objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCerti1();
				  String fileName = objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCertiDoc();
				  downloadDocument(response, contents, fileName);
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
		  }
		  
		  if (request.getParameter("actionName")!=null)
		  {
			  if (request.getParameter("actionName").equalsIgnoreCase("downLoad13"))
			  {
				  try
				  {
				  //FormFile doc = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal();
				  byte contents[] = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware1();
				  String fileName = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardwareDoc();
				  downloadDocument(response, contents, fileName);
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
		  }
		//added by shruti---1 april 2014
		  if (request.getParameter("actionName")!=null)
		  {
			  if (request.getParameter("actionName").equalsIgnoreCase("downLoadPanCard"))
			  {
				  try
				  {
				  byte contents[] = objServiceProviderForm.getObjServiceProviderDTO().getPancardUpload1();
				  String fileName = objServiceProviderForm.getObjServiceProviderDTO().getPancardDoc();
				  downloadDocument(response, contents, fileName);
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
			  
		  }
		  if (request.getParameter("actionName")!=null)
		  {
			  if (request.getParameter("actionName").equalsIgnoreCase("downLoadForm60"))
			  {
				  try
				  {
				  byte contents[] = objServiceProviderForm.getObjServiceProviderDTO().getForm60Upload1();
				  String fileName = objServiceProviderForm.getObjServiceProviderDTO().getForm60Doc();
				  downloadDocument(response, contents, fileName);
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
			  
		  }
		  //end
		
		  // commented by Lavi as this case is directly catered in jsp.
		  
		  /*if (request.getParameter("actionName")!=null)
		  {
			  if (request.getParameter("actionName").equalsIgnoreCase("docDownload1"))
			  {
				  try {
					   String filename = request.getRealPath("/")+"filecontent/format_affidavit.pdf";

					   // set the http content type to "APPLICATION/OCTET-STREAM
					   response.setContentType("application/download");

					   // initialize the http content-disposition header to
					   // indicate a file attachment with the default filename
					   // "myFile.txt"
					   String fileName = "format_affidavit.pdf";
					   //Filename=\"myFile.txt\"";
					   response.setHeader("Content-Disposition", "attachment; filename="
							+ URLEncoder.encode(fileName,"UTF-8"));

					   // transfer the file byte-by-byte to the response object
					   File fileToDownload = new File(filename);
					   FileInputStream fileInputStream = new FileInputStream(fileToDownload);
					   OutputStream out = response.getOutputStream();
					   int i;
					   while ((i=fileInputStream.read())!=-1)
					   {
					      out.write(i);
					   }
					   fileInputStream.close();
					   out.close();
					   }catch(Exception e) // file IO errors
					   {
					   e.printStackTrace();
					}

			  }
		  }*/
		  
		  /*if (request.getParameter("actionName")!=null)
		  {
			  if (request.getParameter("actionName").equalsIgnoreCase("docDownload2"))
			  {
				  try {
					   String filename = request.getRealPath("/")+"filecontent/format_character_certificate.pdf";

					   // set the http content type to "APPLICATION/OCTET-STREAM
					   response.setContentType("application/download");

					   // initialize the http content-disposition header to
					   // indicate a file attachment with the default filename
					   // "myFile.txt"
					   String fileName = "format_character_certificate.pdf";
					   //Filename=\"myFile.txt\"";
					   response.setHeader("Content-Disposition", "attachment; filename="
								+ URLEncoder.encode(fileName,"UTF-8"));

					   // transfer the file byte-by-byte to the response object
					   File fileToDownload = new File(filename);
					   FileInputStream fileInputStream = new FileInputStream(fileToDownload);
					   OutputStream out = response.getOutputStream();
					   int i;
					   while ((i=fileInputStream.read())!=-1)
					   {
					      out.write(i);
					   }
					   fileInputStream.close();
					   out.close();
					   }catch(Exception e) // file IO errors
					   {
					   e.printStackTrace();
					}

			  }
		  }*/
		// end of code commented by Lavi as this case is directly catered in jsp.
		  
		  if(request.getParameter("modName")!=null)
		  {
		  
			  if (request.getParameter("modName").equalsIgnoreCase("ServiceProvider") && request.getParameter("fnName").equalsIgnoreCase("licenseRequest"))
			  {
				  try {
					  objServiceProviderForm.getObjServiceProviderDTO().setFromCreatedDate("");
					  objServiceProviderForm.getObjServiceProviderDTO().setToCreatedDate("");
					  objServiceProviderForm.getObjServiceProviderDTO().setRequestNo("");
					  
					  //ADDED BY SHRUTI---20 MAY 2014
					  objServiceProviderForm.getObjServiceProviderDTO().setOpticalWatermarkFlag("");
					  //END
					  //modified by shruti--hindi conversion
					  objServiceProviderForm.getObjServiceProviderDTO().setStatusList(objServiceProviderBO.getApplictnStatus(lang));
					  //END modified by shruti--hindi conversion
					  String ofcId = (String)objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
					
					  String distt = objServiceProviderBO.getdisttId(ofcId);
					  logger.debug("district id of logged in user is: "+distt);
					  
					  //modified by shruti--hindi conversion
					  //modified by shruti for resolving pagination issue
					  ArrayList pendingApplicationList=null;
					  if(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus()!=null&& !"0".equalsIgnoreCase((String)objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus()))
					  {
						  pendingApplicationList = objServiceProviderBO.getPendingAppsSearch3(distt, (String)objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus(), lang);
					  }
					  else
					  {
						  pendingApplicationList = objServiceProviderBO.getPendingApps(distt,lang);
						  objServiceProviderForm.getObjServiceProviderDTO().setApplicantStatus("0");
					  }
					  
					  
					  //end modified by shruti--hindi conversion
					  
						logger.info("--------------->"+ pendingApplicationList.size());
						if (pendingApplicationList.size() == 0)
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(null);
						else
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(pendingApplicationList);
						request.setAttribute("pendingApplicationList", objServiceProviderForm.getObjServiceProviderDTO().getAppList());
						logger.info("--------------->succesfully received values");
						
						forwardJsp = ServiceProviderConstant.DASHBOARD_DR;
						
						logger.info("--------------->showing dashboard");
						objServiceProviderDTO.setActionName("");
					  
				 
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
		  }
		  
		  if (("SEARCH_ACTION").equalsIgnoreCase(actionName))
		  {
			  if(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().equals("Select"))
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setApplicantStatus("0");
				  logger.debug("---->"+objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus());
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getFromCreatedDate().equals("")==false && objServiceProviderForm.getObjServiceProviderDTO().getRequestNo().equals("") && objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().equals("0"))
			  {
				  try {
					  String ofcId = (String)objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
					  
					  String distt = objServiceProviderBO.getdisttId(ofcId);
					  logger.debug("district id of logged in user is: "+distt);
					  
					  String fromDate = (String)objServiceProviderForm.getObjServiceProviderDTO().getFromCreatedDate();
					  logger.debug("******* initial from date : "+fromDate);
			    	  
			    	  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			    	  Date dateFrom = sdf.parse(fromDate);
			    	  logger.debug("--------->1st step: "+fromDate);
			    	  SimpleDateFormat sdfNew = new SimpleDateFormat("dd/MMM/yyyy");
			    	  fromDate = sdfNew.format(dateFrom);
			    	  
			    	  logger.debug("--------->final from date: "+fromDate);
			    	  
			    	  
					  
					  String toDate = (String)objServiceProviderForm.getObjServiceProviderDTO().getToCreatedDate();
					  logger.debug("******* initial to date : "+toDate);
					  
					  Date dateTo = sdf.parse(toDate);
			    	  toDate = sdfNew.format(dateTo);
			    	  
			    	  logger.debug("------->final to date : "+toDate);
					  
					  ArrayList pendingApplicationList = objServiceProviderBO.getPendingAppsSearch1(distt, fromDate,toDate,lang);

						logger.info("--------------->"+ pendingApplicationList.size());
						if (pendingApplicationList.size() == 0)
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(null);
						else
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(pendingApplicationList);
						request.setAttribute("pendingApplicationList", objServiceProviderForm.getObjServiceProviderDTO().getAppList());
						logger.info("--------------->succesfully received values");
						
						forwardJsp = ServiceProviderConstant.DASHBOARD_DR;
						
						logger.info("--------------->showing dashboard");
						objServiceProviderDTO.setActionName("");
					  
				 
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
			  if (objServiceProviderForm.getObjServiceProviderDTO().getFromCreatedDate().equals("") && objServiceProviderForm.getObjServiceProviderDTO().getRequestNo().equals("")==false && objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().equals("0"))
			  {
				  try {
			    	  
					  String ofcId = (String)objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
					  
					  String distt = objServiceProviderBO.getdisttId(ofcId);
					  logger.debug("district id of logged in user is: "+distt);
					  
					  String applctnNumber = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					  
					  logger.debug("------------------------> initially : "+applctnNumber);
					
			    	  
					  
					  ArrayList pendingApplicationList = objServiceProviderBO.getPendingAppsSearch2(distt,applctnNumber,lang);

						logger.info("--------------->"+ pendingApplicationList.size());
						if (pendingApplicationList.size() == 0)
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(null);
						else
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(pendingApplicationList);
						request.setAttribute("pendingApplicationList", objServiceProviderForm.getObjServiceProviderDTO().getAppList());
						logger.info("--------------->succesfully received values");
						
						forwardJsp = ServiceProviderConstant.DASHBOARD_DR;
						
						logger.info("--------------->showing dashboard");
						objServiceProviderDTO.setActionName("");
					  
				 
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
			  //if (objServiceProviderForm.getObjServiceProviderDTO().getFromCreatedDate().equals("") && objServiceProviderForm.getObjServiceProviderDTO().getRequestNo().equals("") && objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus()!="0")
			  if (objServiceProviderForm.getObjServiceProviderDTO().getFromCreatedDate().equals("") && objServiceProviderForm.getObjServiceProviderDTO().getRequestNo().equals("") && objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().equals("0")==false)  
			  {
				  try {
			    	  
					  String ofcId = (String)objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
					  
					  String distt = objServiceProviderBO.getdisttId(ofcId);
					  logger.debug("district id of logged in user is: "+distt);
					  
					  String status = (String)objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus();
					  
					  logger.debug("------------------------> initially : "+status);
					
			    	  
					  //MODIFIED BY SHRUTI
					  ArrayList pendingApplicationList = objServiceProviderBO.getPendingAppsSearch3(distt, status,lang);
					  //END
						logger.info("--------------->"+ pendingApplicationList.size());
						if (pendingApplicationList.size() == 0)
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(null);
						else
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(pendingApplicationList);
						request.setAttribute("pendingApplicationList", objServiceProviderForm.getObjServiceProviderDTO().getAppList());
						logger.info("--------------->succesfully received values");
						
						forwardJsp = ServiceProviderConstant.DASHBOARD_DR;
						
						logger.info("--------------->showing dashboard");
						objServiceProviderDTO.setActionName("");
					  
				 
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
				  
			  }
			//  if (objServiceProviderForm.getObjServiceProviderDTO().getFromCreatedDate()!="" && objServiceProviderForm.getObjServiceProviderDTO().getRequestNo()!="" && objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().equals("0"))
			  if (objServiceProviderForm.getObjServiceProviderDTO().getFromCreatedDate().equals("")==false && objServiceProviderForm.getObjServiceProviderDTO().getRequestNo().equals("")==false && objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().equals("0"))
			  {
				  try {
			    	  
					  String ofcId = (String)objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
					  
					  String distt = objServiceProviderBO.getdisttId(ofcId);
					  logger.debug("district id of logged in user is: "+distt);
					  
					  String fromDate = (String)objServiceProviderForm.getObjServiceProviderDTO().getFromCreatedDate();
					  logger.debug("******* initial from date : "+fromDate);
			    	  
			    	  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			    	  Date dateFrom = sdf.parse(fromDate);
			    	  logger.debug("--------->1st step: "+fromDate);
			    	  SimpleDateFormat sdfNew = new SimpleDateFormat("dd/MMM/yyyy");
			    	  fromDate = sdfNew.format(dateFrom);
			    	  
			    	  logger.debug("--------->final from date: "+fromDate);
			    	  
			    	  
					  
					  String toDate = (String)objServiceProviderForm.getObjServiceProviderDTO().getToCreatedDate();
					  logger.debug("******* initial to date : "+toDate);
					  
					  Date dateTo = sdf.parse(toDate);
			    	  toDate = sdfNew.format(dateTo);
			    	  
			    	  logger.debug("------->final to date : "+toDate);
					  
					  String applctnNumber = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					  
					  logger.debug("------------------------> initially : "+applctnNumber);
					
			    	  
					 //modfied by shruti 
					  ArrayList pendingApplicationList = objServiceProviderBO.getPendingAppsSearch4(distt, fromDate, toDate, applctnNumber,lang);
					  //end
						logger.info("--------------->"+ pendingApplicationList.size());
						if (pendingApplicationList.size() == 0)
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(null);
						else
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(pendingApplicationList);
						request.setAttribute("pendingApplicationList", objServiceProviderForm.getObjServiceProviderDTO().getAppList());
						logger.info("--------------->succesfully received values");
						
						forwardJsp = ServiceProviderConstant.DASHBOARD_DR;
						
						logger.info("--------------->showing dashboard");
						objServiceProviderDTO.setActionName("");
					  
				 
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }  
				  
			  }
			  if (objServiceProviderForm.getObjServiceProviderDTO().getFromCreatedDate().equals("")==false && objServiceProviderForm.getObjServiceProviderDTO().getRequestNo().equals("") && objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().equals("0")==false)
			  {
				  try {
			    	  
					  String ofcId = (String)objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
					  
					  String distt = objServiceProviderBO.getdisttId(ofcId);
					  logger.debug("district id of logged in user is: "+distt);
					  
					  String fromDate = (String)objServiceProviderForm.getObjServiceProviderDTO().getFromCreatedDate();
					  logger.debug("******* initial from date : "+fromDate);
			    	  
			    	  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			    	  Date dateFrom = sdf.parse(fromDate);
			    	  logger.debug("--------->1st step: "+fromDate);
			    	  SimpleDateFormat sdfNew = new SimpleDateFormat("dd/MMM/yyyy");
			    	  fromDate = sdfNew.format(dateFrom);
			    	  
			    	  logger.debug("--------->final from date: "+fromDate);
			    	  
			    	  
					  
					  String toDate = (String)objServiceProviderForm.getObjServiceProviderDTO().getToCreatedDate();
					  logger.debug("******* initial to date : "+toDate);
					  
					  Date dateTo = sdf.parse(toDate);
			    	  toDate = sdfNew.format(dateTo);
			    	  
			    	  logger.debug("------->final to date : "+toDate);
			    	  
			    	  String status = (String)objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus();
					  
					  logger.debug("------------------------> initially : "+status);
					
			    	  
					  //modified by shruti
					  ArrayList pendingApplicationList = objServiceProviderBO.getPendingAppsSearch5(distt,fromDate, toDate, status,lang);
					  //end
						logger.info("--------------->"+ pendingApplicationList.size());
						if (pendingApplicationList.size() == 0)
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(null);
						else
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(pendingApplicationList);
						request.setAttribute("pendingApplicationList", objServiceProviderForm.getObjServiceProviderDTO().getAppList());
						logger.info("--------------->succesfully received values");
						
						forwardJsp = ServiceProviderConstant.DASHBOARD_DR;
						
						logger.info("--------------->showing dashboard");
						objServiceProviderDTO.setActionName("");
					  
				 
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
			  if (objServiceProviderForm.getObjServiceProviderDTO().getFromCreatedDate().equals("") && objServiceProviderForm.getObjServiceProviderDTO().getRequestNo().equals("")==false && objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().equals("0")==false)
			  {
				  try {
			    	  
					  String ofcId = (String)objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
					  
					  String distt = objServiceProviderBO.getdisttId(ofcId);
					  logger.debug("district id of logged in user is: "+distt);
					  
					  
					  
					  String applctnNumber = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					  
					  logger.debug("------------------------> initially : "+applctnNumber);
					  
					  String status = (String)objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus();
					  
					  logger.debug("------------------------> initially : "+status);
					
			    	  
					  //modified by shruti
					  ArrayList pendingApplicationList = objServiceProviderBO.getPendingAppsSearch6(distt, applctnNumber, status,lang);
					  //end
						logger.info("--------------->"+ pendingApplicationList.size());
						if (pendingApplicationList.size() == 0)
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(null);
						else
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(pendingApplicationList);
						request.setAttribute("pendingApplicationList", objServiceProviderForm.getObjServiceProviderDTO().getAppList());
						logger.info("--------------->succesfully received values");
						
						forwardJsp = ServiceProviderConstant.DASHBOARD_DR;
						
						logger.info("--------------->showing dashboard");
						objServiceProviderDTO.setActionName("");
					  
				 
				  }
				  
				  
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getFromCreatedDate().equals("")==false && objServiceProviderForm.getObjServiceProviderDTO().getRequestNo().equals("")==false && objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().equals("0")==false)
			  {
				  try {
			    	  
					  String ofcId = (String)objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
					  
					  String distt = objServiceProviderBO.getdisttId(ofcId);
					  logger.debug("district id of logged in user is: "+distt);
					  
					  String fromDate = (String)objServiceProviderForm.getObjServiceProviderDTO().getFromCreatedDate();
					  logger.debug("******* initial from date : "+fromDate);
			    	  
			    	  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			    	  Date dateFrom = sdf.parse(fromDate);
			    	  logger.debug("--------->1st step: "+fromDate);
			    	  SimpleDateFormat sdfNew = new SimpleDateFormat("dd/MMM/yyyy");
			    	  fromDate = sdfNew.format(dateFrom);
			    	  
			    	  logger.debug("--------->final from date: "+fromDate);
			    	  
			    	  
					  
					  String toDate = (String)objServiceProviderForm.getObjServiceProviderDTO().getToCreatedDate();
					  logger.debug("******* initial to date : "+toDate);
					  
					  Date dateTo = sdf.parse(toDate);
			    	  toDate = sdfNew.format(dateTo);
			    	  
			    	  logger.debug("------->final to date : "+toDate);
					  
					  String applctnNumber = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					  
					  logger.debug("------------------------> initially : "+applctnNumber);
					  
					  String status = (String)objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus();
					  
					  logger.debug("------------------------> initially : "+status);
					
			    	  
					  
					  ArrayList pendingApplicationList = objServiceProviderBO.getPendingAppsSearch7(distt, fromDate, toDate, applctnNumber, status);

						logger.info("--------------->"+ pendingApplicationList.size());
						if (pendingApplicationList.size() == 0)
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(null);
						else
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(pendingApplicationList);
						request.setAttribute("pendingApplicationList", objServiceProviderForm.getObjServiceProviderDTO().getAppList());
						logger.info("--------------->succesfully received values");
						
						forwardJsp = ServiceProviderConstant.DASHBOARD_DR;
						
						logger.info("--------------->showing dashboard");
						objServiceProviderDTO.setActionName("");
					  
				 
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
		  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getFromCreatedDate().equals("") && objServiceProviderForm.getObjServiceProviderDTO().getRequestNo().equals("") && objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().equals("0"))
			  {
				  try {
				  String ofcId = (String)objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
				  
				  String distt = objServiceProviderBO.getdisttId(ofcId);
				  logger.debug("district id of logged in user is: "+distt);
				  
				  ArrayList pendingApplicationList = objServiceProviderBO.getPendingApps(distt,lang);

					logger.info("--------------->"+ pendingApplicationList.size());
					if (pendingApplicationList.size() == 0)
						objServiceProviderForm.getObjServiceProviderDTO().setAppList(null);
					else
						objServiceProviderForm.getObjServiceProviderDTO().setAppList(pendingApplicationList);
					request.setAttribute("pendingApplicationList", objServiceProviderForm.getObjServiceProviderDTO().getAppList());
					logger.info("--------------->succesfully received values");
					
					forwardJsp = ServiceProviderConstant.DASHBOARD_DR;
					
					logger.info("--------------->showing dashboard");
					objServiceProviderDTO.setActionName("");
					
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
		  }
			  
		  }
		  // for Regiseterd user/SP to view the license status
		  if(request.getParameter("modName")!=null)
		  {
			  if (request.getParameter("modName").equalsIgnoreCase("ServiceProvider") && request.getParameter("fnName").equalsIgnoreCase("licenseStatus"))
			  {
				  try {
					  //START | added by santosh to fix issue - go to sp apply for renewal page and then click on license app status, it show sp renewal page
					  objServiceProviderForm.getObjServiceProviderDTO().setActionName(null);
					  actionName = null;
					  //END | added by santosh to fix issue - go to sp apply for renewal page and then click on license app status, it show sp renewal page
					  String uId = (String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
					  //modified by shruti
					  ArrayList pendingApplicationList = objServiceProviderBO.getPendingAppsSP(uId,lang);
					  //end
					for (int i = 0; i<pendingApplicationList.size(); i++)
					{
						ServiceProviderDTO dto = (ServiceProviderDTO)pendingApplicationList.get(i);
						objServiceProviderForm.getObjServiceProviderDTO().setBalAmount(dto.getBalAmount());
						logger.debug("The balance amount is:"+objServiceProviderForm.getObjServiceProviderDTO().getBalAmount());
						//added by shruti---28 nov 2014
						objServiceProviderForm.getObjServiceProviderDTO().setUid(uId);
						objServiceProviderForm.getObjServiceProviderDTO().setRequestNo(dto.getRequestNo());
						objServiceProviderForm.getObjServiceProviderDTO().setApplctnStatus(dto.getApplctnStatus());
						//end
					}
					
					//added by shruti---28 Nov 2014
					if((("0.0".equals(objServiceProviderForm.getObjServiceProviderDTO().getBalAmount())) || ("0".equals(objServiceProviderForm.getObjServiceProviderDTO().getBalAmount())))
							&&("अनुरोध स्वीकार कर लिया गया है".equals(objServiceProviderForm.getObjServiceProviderDTO().getApplctnStatus())||"Request Approved".equals(objServiceProviderForm.getObjServiceProviderDTO().getApplctnStatus())))
					{
						boolean insert = false;
			  			insert = objServiceProviderBO.updateStatus(objServiceProviderForm);
			  			objServiceProviderDTO.setActionName("");
					}
					pendingApplicationList = objServiceProviderBO.getPendingAppsSP(uId,lang);
					for (int i = 0; i<pendingApplicationList.size(); i++)
					{
						ServiceProviderDTO dto = (ServiceProviderDTO)pendingApplicationList.get(i);
						objServiceProviderForm.getObjServiceProviderDTO().setBalAmount(dto.getBalAmount());
						logger.debug("The balance amount is:"+objServiceProviderForm.getObjServiceProviderDTO().getBalAmount());
					}
					//end
					
						logger.info("--------------->"+ pendingApplicationList.size());
						if (pendingApplicationList.size() == 0)
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(null);
						else
						objServiceProviderForm.getObjServiceProviderDTO().setAppList(pendingApplicationList);
						request.setAttribute("pendingApplicationList", objServiceProviderForm.getObjServiceProviderDTO().getAppList());
						logger.info("--------------->succesfully received values");
						forwardJsp = ServiceProviderConstant.DASHBOARD_SP;
						logger.info("--------------->showing dashboard for SP");
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
		  }
		  
		// end of code for Regiseterd user/SP to view the license status
		  
		  // For viewing and approving the application  of an SP by DR.
		  
		  if (request.getParameter("hdnApplicationId") != null)
		  {
			  objServiceProviderForm = afterSPIdClickByDR(request,
					objServiceProviderForm, objServiceProviderBO,
					objServiceProviderDTO,session);
			  
		  }
		  
		  // for viewing the application and its status by SP and DIG.
		  
		  if (request.getParameter("hdnApplicationIdSP") != null)
		  {
			  objServiceProviderForm = viewAfterSPClick(request,
					objServiceProviderForm, objServiceProviderBO,
					objServiceProviderDTO,session);
			  
		  }
		  
		  if("LOAD_TEHSIL_ADDITIONAL".equalsIgnoreCase(actionName))
		  {			  
			  String disttId =  objServiceProviderForm.getObjServiceProviderDTO().getDistrict();
			  logger.debug("District id is - "+disttId);
			  objServiceProviderForm.getObjServiceProviderDTO().setTehsilList(objServiceProviderBO.getTehsil(disttId,lang));
			  forwardJsp=ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			  objServiceProviderDTO.setActionName("");
		  }
		  if("LOAD_AREA_ADDITIONAL".equalsIgnoreCase(actionName))
		  {		
			  String tehsilId =  objServiceProviderForm.getObjServiceProviderDTO().getTehsil();
			  logger.debug("tehsilId id is - "+tehsilId);
			  objServiceProviderForm.getObjServiceProviderDTO().setAreaList(objServiceProviderBO.getArea(lang));
			  forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			  objServiceProviderDTO.setActionName("");
		  }
		  if("LOAD_SUB_AREA_ADDITIONAL".equalsIgnoreCase(actionName))
		  {			  
			  String areaId =  objServiceProviderForm.getObjServiceProviderDTO().getAreaId();
			  logger.debug("area id is - "+areaId);
			  if("2".equalsIgnoreCase(objServiceProviderDTO.getAreaId()))
				{	
				  objServiceProviderDTO.setSubAreaList(objServiceProviderBO.getSubArea(lang,objServiceProviderDTO.getAreaId(),objServiceProviderDTO.getTehsil(),"Y"));
				}
				else
				{
					objServiceProviderDTO.setSubAreaList(objServiceProviderBO.getSubArea(lang,objServiceProviderDTO.getAreaId(),objServiceProviderDTO.getTehsil(),"N"));

				}
			  forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			  objServiceProviderDTO.setActionName("");		 
		  }
		  if("LOAD_WARD_ADDITIONAL".equalsIgnoreCase(actionName))
		  {			  
			  String subAreaId =  objServiceProviderForm.getObjServiceProviderDTO().getSubAreaId();
			  logger.debug("sub area id is - "+subAreaId);
			  objServiceProviderForm.getObjServiceProviderDTO().setWardPatwariList(objServiceProviderBO.getWardPatwari(lang,objServiceProviderDTO.getSubAreaId(),objServiceProviderDTO.getTehsil()));
			  forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			  objServiceProviderDTO.setActionName("");
		  }
		  if("LOAD_COLONY_ADDITIONAL".equalsIgnoreCase(actionName))
		  {			  
			  String wardPatwariId =  objServiceProviderForm.getObjServiceProviderDTO().getWardPatwariId();
			  logger.debug("wardPatwariId id is - "+wardPatwariId);
			  objServiceProviderDTO.setColonyList(objServiceProviderBO.getColonyName(lang,objServiceProviderDTO.getWardPatwariId()));
			  forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			  objServiceProviderDTO.setActionName("");
		  }
		  
		  if (request.getParameter("hdnApplicationIdDIG") != null)
		  {
			  try {
				  String requestNumber = request.getParameter("hdnApplicationIdDIG");
				  objServiceProviderForm.getObjServiceProviderDTO().setRequestNo(requestNumber);
				  logger.debug("The request number obtained on the click of dashboard link is: "+requestNumber);
				  
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpBank(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovd(0);
				  
				  objServiceProviderForm = (ServiceProviderForm) objServiceProviderBO.getspDocDetlsDR(objServiceProviderForm);
				  
				
				  String spType = objServiceProviderBO.getspTypeId(requestNumber);
				  logger.debug("The sp type id obtained from database is : "+spType);
				  
				  if (spType.equalsIgnoreCase("1"))
				  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(1);
				  ArrayList details = objServiceProviderBO.getspDetls(objServiceProviderForm,lang);
				  logger.info("--------------->"+ details.size()); 
				  
				  if (details.size() == 0)
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetails(null);
				  }
				  else
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetails(details);
				  }

					  request.setAttribute("details", objServiceProviderForm.getObjServiceProviderDTO().getSpAppDetails());
					  
					  	ArrayList applicantDetails = objServiceProviderBO.getSPDetailsInd(objServiceProviderForm,lang);
						logger.info("--------------->"+ applicantDetails.size()); 
						
						if (applicantDetails.size() == 0)
						{
							objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(null);
						}
						else
						{
							objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(applicantDetails);
						}

						request.setAttribute("applicantDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDetails());
				  }
				  
				  if (spType.equalsIgnoreCase("2") || spType.equalsIgnoreCase("3") || spType.equalsIgnoreCase("4"))
				  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpBank(1);
				  ArrayList details = objServiceProviderBO.getspDetlsBank(objServiceProviderForm,lang);
				  logger.info("--------------->"+ details.size()); 
				  
				  if (details.size() == 0)
					  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsBank(null);
				  else
					  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsBank(details);

					  request.setAttribute("details", objServiceProviderForm.getObjServiceProviderDTO().getSpAppDetailsBank());
				  }
				  
				  if (spType.equalsIgnoreCase("1")||spType.equalsIgnoreCase("2")||spType.equalsIgnoreCase("3")||spType.equalsIgnoreCase("4"))
				  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(1);
				  ArrayList details = objServiceProviderBO.getspDetlsAll(objServiceProviderForm,lang);
				  logger.info("--------------->"+ details.size()); 
				  
				  if (details.size() == 0)
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsAll(null);
				  }
				  else
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsAll(details);
				  }

					  request.setAttribute("details", objServiceProviderForm.getObjServiceProviderDTO().getSpAppDetailsAll());
					  
					  ArrayList applicntDetails = objServiceProviderBO.getSPDetails(objServiceProviderForm,lang);
						logger.info("--------------->"+ applicntDetails.size());
						  
						if (applicntDetails.size() == 0)
						{
							objServiceProviderForm.getObjServiceProviderDTO().setSpDetailsAll(null);
						}
						else
						{
							objServiceProviderForm.getObjServiceProviderDTO().setSpDetailsAll(applicntDetails);
						}

						request.setAttribute("applicntDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDetailsAll());
				  }
				
				  
				  	forwardJsp = ServiceProviderConstant.VIEW_PAGE_DIG_AFTER_DASHBOARD_ID_CLICK;
					objServiceProviderDTO.setActionName("");
				  }
			  	
				  
			  catch (Exception e)
			  {
				  e.printStackTrace();
			  }
			  
		  }
		  
		  if(request.getParameter("dms")!=null){
			  if(request.getParameter("dms").equalsIgnoreCase("retrieval1")){
              retrieval1(request, response, objServiceProviderForm,
					objServiceProviderDTO);
			  }
            }
		  
		  if(request.getParameter("dms")!=null){
			  if(request.getParameter("dms").equalsIgnoreCase("retrieval2")){
              retrieval2(request, response, objServiceProviderForm,
					objServiceProviderDTO);
			  }
            }
		  
		  if(request.getParameter("dms")!=null){
              if(request.getParameter("dms").equalsIgnoreCase("retrieval3")){  
            	  retrieval3(request, response, objServiceProviderForm,
      					objServiceProviderDTO);                   
              }
            }
		  
		  if(request.getParameter("dms")!=null){
			  if(request.getParameter("dms").equalsIgnoreCase("retrieval4")){
              retrieval4(request, response, objServiceProviderForm,
					objServiceProviderDTO);
			  }
            }
		  
		  if(request.getParameter("dms")!=null){
              if(request.getParameter("dms").equalsIgnoreCase("retrieval5")){
            	  retrieval5(request, response, objServiceProviderForm,
      					objServiceProviderDTO);
              }
            }
		  
		  if(request.getParameter("dms")!=null){
              if(request.getParameter("dms").equalsIgnoreCase("retrieval6")){   
            	  retrieval6(request, response, objServiceProviderForm,
        					objServiceProviderDTO);
              }
            }
		  
		  if(request.getParameter("dms")!=null){
              if(request.getParameter("dms").equalsIgnoreCase("retrieval7")){
            	  retrieval7(request, response, objServiceProviderForm,
      					objServiceProviderDTO);      
              }
            }
		  
		  if(request.getParameter("dms")!=null){
              if(request.getParameter("dms").equalsIgnoreCase("retrieval8")){ 
            	  retrieval8(request, response, objServiceProviderForm,
        					objServiceProviderDTO);
              }
            }
		  
		 /* if(request.getParameter("dms")!=null){
              if(request.getParameter("dms").equalsIgnoreCase("retrieval9")){
            	  retrieval9(request, response, objServiceProviderForm,
      					objServiceProviderDTO);
              }
            }*/
		  
		  if(request.getParameter("dms")!=null){
              if(request.getParameter("dms").equalsIgnoreCase("retrieval10")){
            	  retrieval10(request, response, objServiceProviderForm,
        					objServiceProviderDTO);
        
              }
            }
		  
		  if(request.getParameter("dms")!=null){
              if(request.getParameter("dms").equalsIgnoreCase("retrieval11")){
            	  retrieval11(request, response, objServiceProviderForm,
      					objServiceProviderDTO);
            	  
              }
            }
		  
		  if(request.getParameter("dms")!=null){
              if(request.getParameter("dms").equalsIgnoreCase("retrieval12")){
            	  retrieval12(request, response, objServiceProviderForm,
        					objServiceProviderDTO);
              }
            }
		  
		  if(request.getParameter("dms")!=null){
              if(request.getParameter("dms").equalsIgnoreCase("retrieval13")){
            	  retrieval13(request, response, objServiceProviderForm,
        					objServiceProviderDTO);
              }
            }
		  //added by shruti---2nd april 2014
		  if(request.getParameter("dms")!=null){
              if(request.getParameter("dms").equalsIgnoreCase("retrievalpancard")){
            	  retrievalpancard(request, response, objServiceProviderForm,
      					objServiceProviderDTO);
              }
            }
		  if(request.getParameter("dms")!=null){
              if(request.getParameter("dms").equalsIgnoreCase("retrievalform60")){
            	  retrievalform60(request, response, objServiceProviderForm,
      					objServiceProviderDTO);
              }
            }
		  //end
		  if (request.getParameter("actionName")!=null)
		  {
		  if (request.getParameter("actionName").equalsIgnoreCase("popUpCriteria"))
		  {
			  String value = objServiceProviderForm.getObjServiceProviderDTO().getSpType();
			  
			  objServiceProviderForm.getObjServiceProviderDTO().setIschecked(1);
			  
			  
			  ArrayList criteriaList = objServiceProviderBO.getCriterias(value);
		      logger.info("--------------->"+criteriaList.size());
		      if(criteriaList.size()==0)
		      {
		      	  objServiceProviderForm.getObjServiceProviderDTO().setEditDeleteCriteria(null);
		      	  objServiceProviderForm.getObjServiceProviderDTO().setIsempty(1);
		      }
		      
		      else
		      {
		    	  objServiceProviderForm.getObjServiceProviderDTO().setIsempty(0);
		      	  objServiceProviderForm.getObjServiceProviderDTO().setEditDeleteCriteria(criteriaList);
		      }
		      	  
		      request.setAttribute("criteriaList", objServiceProviderForm.getObjServiceProviderDTO().getEditDeleteCriteria());
			  
			  
			  forwardJsp=ServiceProviderConstant.POP_UP_CRITERIA;
			  objServiceProviderDTO.setActionName("");
		  }
		  }
		  
		  if (("APPROVE_ACTION").equalsIgnoreCase(actionName))
		  {
			 try {
				 objServiceProviderForm.getObjServiceProviderDTO().setLicenseFromDate("");
				 objServiceProviderForm.getObjServiceProviderDTO().setLicenseToDate("");
				 objServiceProviderForm.getObjServiceProviderDTO().setFees(objServiceProviderBO.getFees());
				 objServiceProviderForm.getObjServiceProviderDTO().setComments("");
				 
				 forwardJsp=ServiceProviderConstant.PAGE_FOR_ENTERING_DETLS_FOR_APPROVAL;
				 objServiceProviderDTO.setActionName("");
				 
			 }
			 catch (Exception e)
			 {
				 e.printStackTrace();
			 }
		  }
		  
		  if (("RESET_ACTION_DR_APPROVE").equalsIgnoreCase(actionName))
		  {
			 try {
				 objServiceProviderForm.getObjServiceProviderDTO().setLicenseFromDate("");
				 objServiceProviderForm.getObjServiceProviderDTO().setLicenseToDate("");
				 objServiceProviderForm.getObjServiceProviderDTO().setFees("");
				 objServiceProviderForm.getObjServiceProviderDTO().setComments("");
				 
				 forwardJsp=ServiceProviderConstant.PAGE_FOR_ENTERING_DETLS_FOR_APPROVAL;
				 objServiceProviderDTO.setActionName("");
				 
			 }
			 catch (Exception e)
			 {
				 e.printStackTrace();
			 }
		  }
		  
		  if (("SUBMIT_APPROVAL").equalsIgnoreCase(actionName))
		  {
			 try {
		    	 boolean insertTxn = false;
		    	 if (objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Criminal_Activities_Affidavit."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGaz()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGaz();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Character_Ceertificate_Gazette."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
					  
					    
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getDobCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getDobCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="DOB_Certificate."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
					   
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getRentDetail()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getRentDetail();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Rent_Details."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Higher_Secondary_Certificate."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
					   
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getPhotoImg()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPhotoImg();
					  String fileExt = getFileExtension(photo.getFileName());
					  //String docName ="Photo."+fileExt;
					  String docName ="Photo."+pr.getValue("sp_photo_extension_param");
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
					    
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getCharacterCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getCharacterCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Character_Certificate."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
					  
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getAgeProof()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAgeProof();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Age_Proof."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  }
				/*  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Solvency_Certificate."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  }*/
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Affidavit_Police_Case."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					    
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDoc()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDoc();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Business_Premises."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					   
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Education_Qualification."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Hardware_Available."+fileExt;;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  } 
				  //ADDED BY SHRUTI---28 APRIL 2014
				  if (objServiceProviderForm.getObjServiceProviderDTO().getPancardUpload()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPancardUpload();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="PanCard."+fileExt;;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  } 
				  if (objServiceProviderForm.getObjServiceProviderDTO().getForm60Upload()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getForm60Upload();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Form60_61."+fileExt;;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  } 
				  //END
		    	 insertTxn = objServiceProviderBO.insertDRApprovalComments(objServiceProviderForm);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsDRApproved(1);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsCallForAddInfo(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsReject(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPV(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsDIG(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsReactivateByDR(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsDRCancelled(0);
				 
				 forwardJsp=ServiceProviderConstant.APP_STATUS_CHANGE_BY_DR_SUCCESS;
				 objServiceProviderDTO.setActionName("");
				 
			 }
			 catch (Exception e)
			 {
				 e.printStackTrace();
			 }
		  }
		  
		  if (("CALL_FOR_PV").equalsIgnoreCase(actionName))
		  {
			 try {
		    	 
				 objServiceProviderForm.getObjServiceProviderDTO().setRmrksCallForPV("");
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsCallForAddInfo(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsReject(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPV(1);
				 forwardJsp=ServiceProviderConstant.APP_CALL_FOR_PV_BY_DR;
				 objServiceProviderDTO.setActionName("");
				 
			 }
			 catch (Exception e)
			 {
				 e.printStackTrace();
			 }
		  }
		  
		  if (("RESET_CALL_FOR_PV").equalsIgnoreCase(actionName))
		  {
			 try {
		    	 
				 objServiceProviderForm.getObjServiceProviderDTO().setRmrksCallForPV("");
				 objServiceProviderForm.getObjServiceProviderDTO().setRmrksCallForAddInfo("");
				 objServiceProviderForm.getObjServiceProviderDTO().setRmrksForRejection("");
				 forwardJsp=ServiceProviderConstant.APP_CALL_FOR_PV_BY_DR;
				 objServiceProviderDTO.setActionName("");
				 
			 }
			 catch (Exception e)
			 {
				 e.printStackTrace();
			 }
		  }
		  
		  if (("CALL_FOR_ADD_INFO").equalsIgnoreCase(actionName))
		  {
			 try {
				 objServiceProviderForm.getObjServiceProviderDTO().setRmrksCallForAddInfo("");
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsCallForAddInfo(1);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsReject(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPV(0);
				 
				 forwardJsp=ServiceProviderConstant.APP_CALL_FOR_PV_BY_DR;
				 objServiceProviderDTO.setActionName("");
				 
			 }
			 catch (Exception e)
			 {
				 e.printStackTrace();
			 }
		  }
		  
		  if (("REJECTION").equalsIgnoreCase(actionName))
		  {
			 try {
				 objServiceProviderForm.getObjServiceProviderDTO().setRmrksForRejection("");
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsCallForAddInfo(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsReject(1);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPV(0);
				 
				 forwardJsp=ServiceProviderConstant.APP_CALL_FOR_PV_BY_DR;
				 objServiceProviderDTO.setActionName("");
				 
			 }
			 catch (Exception e)
			 {
				 e.printStackTrace();
			 }
		  }
		  
		  if (("CALL_FOR_PV_SUBMIT").equalsIgnoreCase(actionName))
		  {
			 try {
		    	 boolean insertTxn = false;
		    	 
		    	 if (objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Criminal_Activities_Affidavit."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGaz()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGaz();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Character_Ceertificate_Gazette."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
					  
					    
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getDobCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getDobCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="DOB_Certificate."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
					   
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getRentDetail()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getRentDetail();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Rent_Details."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Higher_Secondary_Certificate."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
					   
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getPhotoImg()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPhotoImg();
					  String fileExt = getFileExtension(photo.getFileName());
					  //String docName ="Photo."+fileExt;
					  String docName ="Photo."+pr.getValue("sp_photo_extension_param");
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
					    
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getCharacterCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getCharacterCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Character_Certificate."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
					  
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getAgeProof()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAgeProof();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Age_Proof."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  }
				  
				  /*if (objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Solvency_Certificate."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  }*/
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Affidavit_Police_Case."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					    
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDoc()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDoc();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Business_Premises."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					   
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Education_Qualification."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Hardware_Available."+fileExt;;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  }
				  //added by shruti--30 july 2014
				  if (objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Hardware_Available."+fileExt;;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  }
				  if (objServiceProviderForm.getObjServiceProviderDTO().getPancardUpload()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPancardUpload();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="PanCard."+fileExt;;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  }
				  if (objServiceProviderForm.getObjServiceProviderDTO().getForm60Upload()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getForm60Upload();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Form60_61."+fileExt;;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  }
				  //end
				  
		    	 insertTxn = objServiceProviderBO.insertDRCallForPVComments(objServiceProviderForm);
		    	 
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsDRApproved(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsCallForAddInfo(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsReject(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPV(1);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsDIG(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsReactivateByDR(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsDRCancelled(0);
		    	 
				 
				 forwardJsp=ServiceProviderConstant.APP_STATUS_CHANGE_BY_DR_SUCCESS;
				 objServiceProviderDTO.setActionName("");
				 
			 }
			 catch (Exception e)
			 {
				 e.printStackTrace();
			 }
		  }
		  
		  if (("CALL_FOR_ADD_INFO_SUBMIT").equalsIgnoreCase(actionName))
		  {
			 try {
		    	 boolean insertTxn = false;
		    	 if (objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Criminal_Activities_Affidavit."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGaz()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGaz();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Character_Ceertificate_Gazette."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
					  
					    
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getDobCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getDobCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="DOB_Certificate."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
					   
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getRentDetail()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getRentDetail();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Rent_Details."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Higher_Secondary_Certificate."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
					   
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getPhotoImg()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPhotoImg();
					  String fileExt = getFileExtension(photo.getFileName());
					  //String docName ="Photo."+fileExt;
					  String docName ="Photo."+pr.getValue("sp_photo_extension_param");
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = "/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
					    
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getCharacterCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getCharacterCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Character_Certificate."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
					  
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getAgeProof()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAgeProof();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Age_Proof."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  }
				  
				  /*if (objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Solvency_Certificate."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  }
				  */
				  if (objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Affidavit_Police_Case."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					    
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDoc()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDoc();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Business_Premises."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = "/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					   
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Education_Qualification."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Hardware_Available."+fileExt;;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  }
				//ADDED BY SHRUTI---6 May 2014
				  if (objServiceProviderForm.getObjServiceProviderDTO().getPancardUpload()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPancardUpload();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="PanCard."+fileExt;;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  } 
				  if (objServiceProviderForm.getObjServiceProviderDTO().getForm60Upload()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getForm60Upload();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Form60_61."+fileExt;;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  } 
				  //END
				  
		    	 insertTxn = objServiceProviderBO.insertDRCallForAddInfoComments(objServiceProviderForm);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsDRApproved(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsCallForAddInfo(1);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsReject(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPV(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsDIG(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsReactivateByDR(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsDRCancelled(0);
				 
				 forwardJsp=ServiceProviderConstant.APP_STATUS_CHANGE_BY_DR_SUCCESS;
				 objServiceProviderDTO.setActionName("");
				 
			 }
			 catch (Exception e)
			 {
				 e.printStackTrace();
			 }
		  }
		  
		  if (("REJECTION_SUBMIT").equalsIgnoreCase(actionName))
		  {
			 try {
		    	 boolean insertTxn = false;
		    	 if (objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Criminal_Activities_Affidavit."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGaz()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGaz();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Character_Ceertificate_Gazette."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
					  
					    
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getDobCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getDobCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="DOB_Certificate."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
					   
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getRentDetail()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getRentDetail();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Rent_Details."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Higher_Secondary_Certificate."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
					   
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getPhotoImg()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPhotoImg();
					  String fileExt = getFileExtension(photo.getFileName());
					  //String docName ="Photo."+fileExt;
					  String docName ="Photo."+pr.getValue("sp_photo_extension_param");
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
					    
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getCharacterCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getCharacterCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Character_Certificate."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
					  
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getAgeProof()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAgeProof();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Age_Proof."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  }
				  
				  /*if (objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Solvency_Certificate."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  }
				  */
				  if (objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Affidavit_Police_Case."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					    
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDoc()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDoc();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Business_Premises."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					   
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCerti()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCerti();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Education_Qualification."+fileExt;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Hardware_Available."+fileExt;;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  }
				//ADDED BY SHRUTI---6 May 2014
				  if (objServiceProviderForm.getObjServiceProviderDTO().getPancardUpload()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPancardUpload();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="PanCard."+fileExt;;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  } 
				  if (objServiceProviderForm.getObjServiceProviderDTO().getForm60Upload()!=null)
				  {
					  
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getForm60Upload();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Form60_61."+fileExt;;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
						//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
					  
				  } 
				  //END
				  
		    	 insertTxn = objServiceProviderBO.insertDrRejectionComments(objServiceProviderForm);
		    	 
		    	 // if during renewal the license gets rejected and the previous license is in the category to get expired.
		    	 ArrayList getDetlsWhetherExprd = objServiceProviderBO.getDetlsWhetherPreviousLicenseExprd(objServiceProviderForm);
		    	 
		    	 if (getDetlsWhetherExprd.size()>0)
		    	 {
		    		 boolean updateTxn= false;
		    		 updateTxn = objServiceProviderBO.updateExprdStatus(objServiceProviderForm);
		    	 }
		    	 //end of this case
		    	 
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsDRApproved(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsCallForAddInfo(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsReject(1);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPV(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsDIG(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsReactivateByDR(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsDRCancelled(0);
		    	 
				 forwardJsp=ServiceProviderConstant.APP_STATUS_CHANGE_BY_DR_SUCCESS;
				 objServiceProviderDTO.setActionName("");
				 objServiceProviderDTO.setActionName("");
				 
			 }
			 catch (Exception e)
			 {
				 e.printStackTrace();
			 }
		  }
		  
		  // for activitiy of DIG when he/she has to reactivate an application for DR.
		  
		  if(request.getParameter("modName")!=null)
		  {
		  
			  if (request.getParameter("modName").equalsIgnoreCase("ServiceProvider") && request.getParameter("fnName").equalsIgnoreCase("licenseReactivateDIG"))
			  {
				  try {
					  objServiceProviderForm.getObjServiceProviderDTO().setFromCreatedDate("");
					  objServiceProviderForm.getObjServiceProviderDTO().setToCreatedDate("");
					  objServiceProviderForm.getObjServiceProviderDTO().setRequestNo("");
					  objServiceProviderForm.getObjServiceProviderDTO().setApplicantStatus("0");
					  objServiceProviderForm.getObjServiceProviderDTO().setStatusList(objServiceProviderBO.getApplictnStatus(lang));
					  
					  String ofcIdDIG = (String)objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
					  
					  logger.debug("office id of logged in user is: "+ofcIdDIG);
					  
					  ArrayList pendingApplicationList = objServiceProviderBO.getPendingAppsDIG(ofcIdDIG,lang);

						logger.info("--------------->"+ pendingApplicationList.size());
						if (pendingApplicationList.size() == 0)
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(null);
						else
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(pendingApplicationList);
						request.setAttribute("pendingApplicationList", objServiceProviderForm.getObjServiceProviderDTO().getAppList());
						logger.info("--------------->succesfully received values");
						
						forwardJsp = ServiceProviderConstant.DASHBOARD_DIG;
						
						logger.info("--------------->showing dashboard");
						objServiceProviderDTO.setActionName("");
					  
				 
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
		  }
		  
		  if (("SEARCH_ACTION_DIG").equalsIgnoreCase(actionName))
		  {
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getFromCreatedDate()!="" && objServiceProviderForm.getObjServiceProviderDTO().getRequestNo().equals(""))
			  {
				  try {
					  String ofcId = (String)objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
					
					  logger.debug("ofc id of logged in user is: "+ofcId);
					  
					  String fromDate = (String)objServiceProviderForm.getObjServiceProviderDTO().getFromCreatedDate();
					  logger.debug("******* initial from date : "+fromDate);
			    	  
			    	  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			    	  Date dateFrom = sdf.parse(fromDate);
			    	  logger.debug("--------->1st step: "+fromDate);
			    	  SimpleDateFormat sdfNew = new SimpleDateFormat("dd/MMM/yyyy");
			    	  fromDate = sdfNew.format(dateFrom);
			    	  
			    	  logger.debug("--------->final from date: "+fromDate);
			    	  
			    	  
					  
					  String toDate = (String)objServiceProviderForm.getObjServiceProviderDTO().getToCreatedDate();
					  logger.debug("******* initial to date : "+toDate);
					  
					  Date dateTo = sdf.parse(toDate);
			    	  toDate = sdfNew.format(dateTo);
			    	  
			    	  logger.debug("------->final to date : "+toDate);
					  
					  ArrayList pendingApplicationList = objServiceProviderBO.getPendingAppsSearchDIG1(ofcId, fromDate,toDate);

						logger.info("--------------->"+ pendingApplicationList.size());
						if (pendingApplicationList.size() == 0)
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(null);
						else
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(pendingApplicationList);
						request.setAttribute("pendingApplicationList", objServiceProviderForm.getObjServiceProviderDTO().getAppList());
						logger.info("--------------->succesfully received values");
						
						forwardJsp = ServiceProviderConstant.DASHBOARD_DIG;
						
						logger.info("--------------->showing dashboard");
						objServiceProviderDTO.setActionName("");
					  
				 
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
			  if (objServiceProviderForm.getObjServiceProviderDTO().getFromCreatedDate().equals("") && objServiceProviderForm.getObjServiceProviderDTO().getRequestNo()!="")
			  {
				  try {
			    	  
					  String ofcId = (String)objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
					  
					  logger.debug("ofc id of logged in user is: "+ofcId);
					  
					  String applctnNumber = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					  
					  logger.debug("------------------------> initially : "+applctnNumber);
					
			    	  
					  
					  ArrayList pendingApplicationList = objServiceProviderBO.getPendingAppsSearchDIG2(ofcId,applctnNumber);

						logger.info("--------------->"+ pendingApplicationList.size());
						if (pendingApplicationList.size() == 0)
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(null);
						else
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(pendingApplicationList);
						request.setAttribute("pendingApplicationList", objServiceProviderForm.getObjServiceProviderDTO().getAppList());
						logger.info("--------------->succesfully received values");
						
						forwardJsp = ServiceProviderConstant.DASHBOARD_DIG;
						
						logger.info("--------------->showing dashboard");
						objServiceProviderDTO.setActionName("");
					  
				 
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
			  if (objServiceProviderForm.getObjServiceProviderDTO().getFromCreatedDate()!="" && objServiceProviderForm.getObjServiceProviderDTO().getRequestNo()!="")
			  {
				  try {
			    	  
					  String ofcId = (String)objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
					  
					  logger.debug("ofc id of logged in user is: "+ofcId);
					  
					  String fromDate = (String)objServiceProviderForm.getObjServiceProviderDTO().getFromCreatedDate();
					  logger.debug("******* initial from date : "+fromDate);
			    	  
			    	  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			    	  Date dateFrom = sdf.parse(fromDate);
			    	  logger.debug("--------->1st step: "+fromDate);
			    	  SimpleDateFormat sdfNew = new SimpleDateFormat("dd/MMM/yyyy");
			    	  fromDate = sdfNew.format(dateFrom);
			    	  
			    	  logger.debug("--------->final from date: "+fromDate);
			    	  
			    	  
					  
					  String toDate = (String)objServiceProviderForm.getObjServiceProviderDTO().getToCreatedDate();
					  logger.debug("******* initial to date : "+toDate);
					  
					  Date dateTo = sdf.parse(toDate);
			    	  toDate = sdfNew.format(dateTo);
			    	  
			    	  logger.debug("------->final to date : "+toDate);
					  
					  String applctnNumber = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					  
					  logger.debug("------------------------> initially : "+applctnNumber);
					
			    	  
					  
					  ArrayList pendingApplicationList = objServiceProviderBO.getPendingAppsSearchDIG3(ofcId, fromDate, toDate, applctnNumber);

						logger.info("--------------->"+ pendingApplicationList.size());
						if (pendingApplicationList.size() == 0)
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(null);
						else
							objServiceProviderForm.getObjServiceProviderDTO().setAppList(pendingApplicationList);
						request.setAttribute("pendingApplicationList", objServiceProviderForm.getObjServiceProviderDTO().getAppList());
						logger.info("--------------->succesfully received values");
						
						forwardJsp = ServiceProviderConstant.DASHBOARD_DIG;
						
						logger.info("--------------->showing dashboard");
						objServiceProviderDTO.setActionName("");
					  
				 
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }  
				  
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getFromCreatedDate().equals("") && objServiceProviderForm.getObjServiceProviderDTO().getRequestNo().equals(""))
			  {
				  try {
				  String ofcId = (String)objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
				  
				  logger.debug("ofc id of logged in user is: "+ofcId);
				  
				  ArrayList pendingApplicationList = objServiceProviderBO.getPendingAppsDIG(ofcId,lang);

					logger.info("--------------->"+ pendingApplicationList.size());
					if (pendingApplicationList.size() == 0)
						objServiceProviderForm.getObjServiceProviderDTO().setAppList(null);
					else
						objServiceProviderForm.getObjServiceProviderDTO().setAppList(pendingApplicationList);
					request.setAttribute("pendingApplicationList", objServiceProviderForm.getObjServiceProviderDTO().getAppList());
					logger.info("--------------->succesfully received values");
					
					forwardJsp = ServiceProviderConstant.DASHBOARD_DIG;
					
					logger.info("--------------->showing dashboard");
					objServiceProviderDTO.setActionName("");
					
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
		  }
			  
		  }
		  
		  if (("ACTIVATE_ACTION_DIG").equalsIgnoreCase(actionName))
		  {
			  	 objServiceProviderForm.getObjServiceProviderDTO().setIsDIG(1);
			  	 objServiceProviderForm.getObjServiceProviderDTO().setIsDRApproved(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsCallForAddInfo(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsReject(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPV(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsReactivateByDR(0);
		    	 objServiceProviderForm.getObjServiceProviderDTO().setIsDRCancelled(0);
		    	 
		    	 boolean updateStatus = false;
		    	 updateStatus = objServiceProviderBO.updateStatusOfApplicatnDIG(objServiceProviderForm);
			  
			  forwardJsp=ServiceProviderConstant.APP_STATUS_CHANGE_BY_DR_SUCCESS;
			  objServiceProviderDTO.setActionName("");
		  }
		  
		  if (("REACTIVATE_ACTION_DR").equalsIgnoreCase(actionName))
		  {
			  boolean updateStatus = false;
		      updateStatus = objServiceProviderBO.updateStatusOfApplicatnDR(objServiceProviderForm);
		      objServiceProviderForm.getObjServiceProviderDTO().setIsReactivateByDR(1);
		      objServiceProviderForm.getObjServiceProviderDTO().setIsDIG(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsDRApproved(0);
		      objServiceProviderForm.getObjServiceProviderDTO().setIsCallForAddInfo(0);
		      objServiceProviderForm.getObjServiceProviderDTO().setIsReject(0);
		      objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPV(0);
		      objServiceProviderForm.getObjServiceProviderDTO().setIsDRCancelled(0);
			  
			  forwardJsp=ServiceProviderConstant.APP_STATUS_CHANGE_BY_DR_SUCCESS;
			  objServiceProviderDTO.setActionName("");
		  }
		  
		  if (("UPDATE_ACTION_SP").equalsIgnoreCase(actionName))
		  {

			  boolean updateTxn = false;
			  
			  objServiceProviderForm.getObjServiceProviderDTO().setIsAlreadyFiled(0);
			  
			  logger.debug("*********"+objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect());
			  logger.debug("*********"+objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect1());
			  
			  if( objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect()!=null && objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect1()!=null)
			  {
				  String language = objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect()+"&"+objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect1();
				  objServiceProviderForm.getObjServiceProviderDTO().setLangKnown(language);
			  }
			  
			  else if(objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect()==null && objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect1()!=null)
			  {
				  String language = (String) objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect1();
				  logger.debug("------>"+language);
				  objServiceProviderForm.getObjServiceProviderDTO().setLangKnown(language);
			  }
			  
			  else if(objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect()!=null && objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect1()==null)
			  {
				  String language = (String) objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect();
				  objServiceProviderForm.getObjServiceProviderDTO().setLangKnown(language);
			  }
			  
			  		updateTxn = objServiceProviderBO.updateSpDetls(objServiceProviderForm);
			  
			  		logger.debug("Success--->"+updateTxn);
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Criminal_Activities_Affidavit."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGaz()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGaz();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Character_Ceertificate_Gazette."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
				  
				    
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getDobCerti()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getDobCerti();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="DOB_Certificate."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
				   
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getRentDetail()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getRentDetail();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Rent_Details."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCerti()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCerti();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Higher_Secondary_Certificate."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
				   
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getPhotoImg()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPhotoImg();
				  String fileExt = getFileExtension(photo.getFileName());
				  //String docName ="Photo."+fileExt;
				  String docName ="Photo."+pr.getValue("sp_photo_extension_param");
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
				    
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getCharacterCerti()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getCharacterCerti();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Character_Certificate."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm); 
				  
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getAgeProof()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAgeProof();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Age_Proof."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
				  
			  }
			  
			  /*if (objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCerti()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCerti();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Solvency_Certificate."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
				  
			  }*/
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCerti()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCerti();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Affidavit_Police_Case."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
				    
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDoc()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDoc();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Business_Premises."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
				   
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCerti()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCerti();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Education_Qualification."+fileExt;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
				  
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware()!=null)
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Hardware_Available."+fileExt;;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
				  
			  }
			//ADDED BY SHRUTI---6 May 2014
			  if (objServiceProviderForm.getObjServiceProviderDTO().getPancardUpload()!=null&& "Y".equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getPancardFlag()))
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPancardUpload();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="PanCard."+fileExt;;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
				  
			  } 
			  if (objServiceProviderForm.getObjServiceProviderDTO().getForm60Upload()!=null && "Y".equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getForm60Flag()))
			  {
				  
				  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getForm60Upload();
				  String fileExt = getFileExtension(photo.getFileName());
				  String docName ="Form60_61."+fileExt;;
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFile(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.updateSpDocDetls(objServiceProviderForm);
				  
			  } 
			  //END
			  String distt= objServiceProviderForm.getObjServiceProviderDTO().getDistrict();
			  logger.debug(distt);
			  ArrayList drOfficeDetails = new ArrayList();
			  
			  String reqNo = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
			  objServiceProviderForm.getObjServiceProviderDTO().setReqNo(reqNo);
			  
			  drOfficeDetails= objServiceProviderBO.getDroDetails(distt,lang);
			  logger.debug("size of arraylist: "+drOfficeDetails.size());
			  
			  if (drOfficeDetails.size()==0)
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setDroDetails(null);
			  }
			  else
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setDroDetails(drOfficeDetails);
			  }
			  
			  forwardJsp=ServiceProviderConstant.SP_APP_FORM_SUCCESS_OR_ALREADY_FILED;
			  
			  objServiceProviderDTO.setActionName("");
		  
		  }
		  
		  if (("PAY_NOW_ACTION").equalsIgnoreCase(actionName))
		  {
			  try
			  {
				  //CHECK INTRODUCED ON 21 MARCH 2014 REGARDING LANGUAGE
				  	objServiceProviderForm = objServiceProviderBO.getPaymentDetls(objServiceProviderForm,lang);
				  	// for first time payment---- Payable amount will be equal to the amount which is getting reflected on the payment screen.
				  	double amtToBePaid = Double.parseDouble((String) objServiceProviderForm.getObjServiceProviderDTO().getPayableAmount());
				  	if (objServiceProviderForm.getObjServiceProviderDTO().getPaymentFlag().equals("I"))
				  	{
					DecimalFormat myformatter = new DecimalFormat("############");
					request.setAttribute("parentModName","Service Provider");
					request.setAttribute("parentFunName","ONLINE APPLICATION SP");
					request.setAttribute("parentFunId", "FUN_225");
					// for total amount setting for payment
					request.setAttribute("parentAmount",myformatter.format(amtToBePaid));
					request.setAttribute("parentTable","IGRS_SP_PAYMENT_DETLS");
					//for setting serial no. for payment
					request.setAttribute("parentKey", objServiceProviderForm.getObjServiceProviderDTO().getSpPaymentId());
					request.setAttribute("forwardPath","./serviceProviderAction.do?TRFS=NGI");
					request.setAttribute("parentColumnName","SP_PAYMENT_ID");
					
					request.setAttribute("parentOfficeId",objServiceProviderForm.getObjServiceProviderDTO().getDrOfcId());
					request.setAttribute("parentOfficeName",objServiceProviderForm.getObjServiceProviderDTO().getDrOfficeName());
					request.setAttribute("parentDistrictId",objServiceProviderForm.getObjServiceProviderDTO().getDrDistrict());
					request.setAttribute("parentDistrictName",objServiceProviderForm.getObjServiceProviderDTO().getDrDistrictName());
					request.setAttribute("parentReferenceId",objServiceProviderForm.getObjServiceProviderDTO().getRequestNo());
					
					//modified by shruti---19 nov 2014-payment changes
					request.setAttribute("formName","serviceProvider");  
					//end
					
					//added by shruti--27 feb 2014 for breadcum issue;
					if("en".equalsIgnoreCase(lang)){
						session.setAttribute("modName",ServiceProviderConstant.MODNAME_ENGLISH);	
					}else{
						session.setAttribute("modName",ServiceProviderConstant.MODNAME_HINDI);
					}
					if("en".equalsIgnoreCase(lang)){
						session.setAttribute("fnName",ServiceProviderConstant.FUNCTION_PAY_ENGLISH);
					}else{
						session.setAttribute("fnName",ServiceProviderConstant.FUNCTION_PAY_HINDI);
					}
					//end
					logger.debug("just before redirection");
					forwardJsp = ServiceProviderConstant.REDIRECT_TO_PAYMENT_PAGE; 
				  	}
				  	
				  	if (objServiceProviderForm.getObjServiceProviderDTO().getPaymentFlag().equals("P"))
				  	{
				  	double paidAmount = Double.parseDouble((String)objServiceProviderForm.getObjServiceProviderDTO().getPaidAmount());
				  		if(paidAmount < amtToBePaid)
				  		{
				  			double bal = amtToBePaid - paidAmount;
				  			boolean insert = false;
				  			objServiceProviderForm.getObjServiceProviderDTO().setAmountRemaining(bal);
				  			
				  			insert = objServiceProviderBO.insertPaymentDetls(objServiceProviderForm);
				  			
				  			objServiceProviderForm = objServiceProviderBO.getPaymentDetls(objServiceProviderForm,lang);
				  			
				  			DecimalFormat myformatter = new DecimalFormat("############");
				  			request.setAttribute("parentModName","Service Provider");
				  			request.setAttribute("parentFunName","ONLINE APPLICATION SP");
				  			request.setAttribute("parentFunId", "FUN_225");
				  			// for total amount setting for payment
				  			request.setAttribute("parentAmount",myformatter.format(objServiceProviderForm.getObjServiceProviderDTO().getAmountRemaining()));
				  			request.setAttribute("parentTable","IGRS_SP_PAYMENT_DETLS");
				  			//for setting serial no. for payment
				  			request.setAttribute("parentKey", objServiceProviderForm.getObjServiceProviderDTO().getSpPaymentId());
				  			request.setAttribute("forwardPath","./serviceProviderAction.do?TRFS=NGI");
				  			request.setAttribute("parentColumnName","SP_PAYMENT_ID");
				  			//new parameters
				  			request.setAttribute("parentOfficeId",objServiceProviderForm.getObjServiceProviderDTO().getDrOfcId());
				  			request.setAttribute("parentOfficeName",objServiceProviderForm.getObjServiceProviderDTO().getDrOfficeName());
				  			request.setAttribute("parentDistrictId",objServiceProviderForm.getObjServiceProviderDTO().getDrDistrict());
				  			request.setAttribute("parentDistrictName",objServiceProviderForm.getObjServiceProviderDTO().getDrDistrictName());
				  			request.setAttribute("parentReferenceId",objServiceProviderForm.getObjServiceProviderDTO().getRequestNo());
					
				  			//modified by shruti---19 nov 2014-payment changes
							request.setAttribute("formName","serviceProvider");  
							//end
				  			logger.debug("just before redirection");
				  			forwardJsp = ServiceProviderConstant.REDIRECT_TO_PAYMENT_PAGE; 
				  		}
				  		//*********************************added by shruti----12 march 2014
				  		if (amtToBePaid <= paidAmount)
						{
							String requestNumber= (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
							
							  objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(0);
							  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(0);
							  objServiceProviderForm.getObjServiceProviderDTO().setIsSpBank(0);
							  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovd(0);
							  objServiceProviderForm.getObjServiceProviderDTO().setIsPaymentComplete(0);
							
							String spType = objServiceProviderBO.getspTypeId(requestNumber);
							
							logger.debug("The sp type id obtained from database is : "+spType);
							  
							  if (spType.equalsIgnoreCase("1"))
							  {
							  objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(1);
							  ArrayList details = objServiceProviderBO.getspDetls(objServiceProviderForm,lang);
							  logger.info("--------------->"+ details.size());   
							  if (details.size() == 0)
							  {
								  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetails(null);
							  }
							  else
							  {
								  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetails(details);
							  }

								  request.setAttribute("details", objServiceProviderForm.getObjServiceProviderDTO().getSpAppDetails());
								  
								  	ArrayList applicantDetails = objServiceProviderBO.getSPDetailsInd(objServiceProviderForm,lang);
									logger.info("--------------->"+ applicantDetails.size()); 
									
									if (applicantDetails.size() == 0)
									{
										objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(null);
									}
									else
									{
										objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(applicantDetails);
									}

									request.setAttribute("applicantDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDetails());
							  }
							  
							  if (spType.equalsIgnoreCase("2") || spType.equalsIgnoreCase("3") || spType.equalsIgnoreCase("4"))
							  {
							  objServiceProviderForm.getObjServiceProviderDTO().setIsSpBank(1);
							  ArrayList details = objServiceProviderBO.getspDetlsBank(objServiceProviderForm,lang);
							  logger.info("--------------->"+ details.size()); 
							  
							  if (details.size() == 0)
								  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsBank(null);
							  else
								  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsBank(details);

								  request.setAttribute("details", objServiceProviderForm.getObjServiceProviderDTO().getSpAppDetailsBank());
							  }
							  
							  if (spType.equalsIgnoreCase("1")||spType.equalsIgnoreCase("2")||spType.equalsIgnoreCase("3")||spType.equalsIgnoreCase("4"))
							  {
							  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(1);
							  ArrayList details = objServiceProviderBO.getspDetlsAll(objServiceProviderForm,lang);
							  logger.info("--------------->"+ details.size()); 
							  
							  if (details.size() == 0)
							  {
								  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsAll(null);
							  }
							  else
							  {
								  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsAll(details);
							  }

								  request.setAttribute("details", objServiceProviderForm.getObjServiceProviderDTO().getSpAppDetailsAll());
								  
								  ArrayList applicntDetails = objServiceProviderBO.getSPDetails(objServiceProviderForm,lang);
									logger.info("--------------->"+ applicntDetails.size());
									  
									if (applicntDetails.size() == 0)
									{
										objServiceProviderForm.getObjServiceProviderDTO().setSpDetailsAll(null);
									}
									else
									{
										objServiceProviderForm.getObjServiceProviderDTO().setSpDetailsAll(applicntDetails);
									}

									request.setAttribute("applicntDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDetailsAll());
							  }
							  
							  if (objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("5"))
							  {
								  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovd(0); 
								  objServiceProviderForm.getObjServiceProviderDTO().setIsPaymentComplete(1);
							  }
								  ArrayList docDetails = objServiceProviderBO.getspDocDetls(requestNumber);
								  logger.info("--------------->"+ docDetails.size());   
								  if (docDetails.size() == 0)
								  {
									  objServiceProviderForm.getObjServiceProviderDTO().setSpDocDetails(null);
								  }
								  else
								  {
									  objServiceProviderForm.getObjServiceProviderDTO().setSpDocDetails(docDetails);
								  }
									  request.setAttribute("docDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDocDetails());
								  forwardJsp = ServiceProviderConstant.VIEW_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;  
						
						
						}
				  		////////***************************** end
				  		
				  	}
				  	
				  	objServiceProviderDTO.setActionName("");
			  }
			  
			  catch (Exception e)
			  {
				  e.printStackTrace();
			  }
		  }
		  
		  if (request.getParameter("paymentStatus") != null)
		  {
			  //modfied by shruti-session added
				fromPaymentModule(request, objServiceProviderForm,
						objServiceProviderBO,session);
				//end
				objServiceProviderDTO.setActionName("");
		  }
		  
		  if (("STATUS_CHANGE_AFTR_PAYMENT").equalsIgnoreCase(actionName))
		  {
			  	boolean insert = false;
	  			insert = objServiceProviderBO.updateStatus(objServiceProviderForm);
	  			objServiceProviderForm.getObjServiceProviderDTO().setIsAlreadyFiled(0);	
	  			forwardJsp = ServiceProviderConstant.SP_APP_FORM_SUCCESS_OR_ALREADY_FILED; 
	  			objServiceProviderDTO.setActionName("");
		  }
		  
		  /*if (("GENERATE_LICENSE_NUMBER").equalsIgnoreCase(actionName))
		  {
			  try
			  {
				  ArrayList previousLicenseDetlsEstamp = objServiceProviderBO.getPreviousLicenseDetailsEstamp(objServiceProviderForm);
				  ArrayList previousLicenseDetlsOthers = objServiceProviderBO.getPreviousLicenseDetailsOthers(objServiceProviderForm);
				  logger.debug("Size of previous license details arrayList is: "+previousLicenseDetlsEstamp.size());
				  
				  if((previousLicenseDetlsEstamp.size()>0)||(previousLicenseDetlsOthers.size()>0))
				  {
					  
				  }
				  
				  else
				  {
					  String ofcId = (String)objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
					  logger.debug("The office id of the logged in DR is "+ofcId);
					  String distt = objServiceProviderBO.getdisttId(ofcId);
					  logger.debug("The district id of the logged in DR is "+distt);
					  objServiceProviderForm.getObjServiceProviderDTO().setDrDistrict(distt);
					  ArrayList licenseDetails = objServiceProviderBO.getLicenseDetails(objServiceProviderForm);
					  logger.debug("Size of ArrayList licenseDetails:- "+licenseDetails.size());
					  
					  if (licenseDetails.size()>0)
					  {
						  boolean renewLicenseNumber = false;
						  renewLicenseNumber = objServiceProviderBO.renewLicenseNumber(objServiceProviderForm);
						  logger.debug("license number updated:- "+renewLicenseNumber);
					  }
					  else
					  {
					  // for inserting license number in the database.
					  boolean insrtLicenseNumber = false;
					  insrtLicenseNumber = objServiceProviderBO.insertLicenseNumber(objServiceProviderForm);
					  }
					  // for view of license number.
					  objServiceProviderForm = objServiceProviderBO.getFirstTimeLicenseDetails(objServiceProviderForm);
					  
					  forwardJsp=ServiceProviderConstant.PAGE_AFTER_LICENSE_NUMBR_GENERATION;
					 
				  }
				  objServiceProviderDTO.setActionName("");
			  }
			  catch(Exception e)
			  {
				  e.printStackTrace();
			  }
		  }*/
		  
		  if (("GENERATE_LICENSE_NUMBER").equalsIgnoreCase(actionName))
		  {
			  try{
				  	objServiceProviderForm = generateLicenseNumber(
					objServiceProviderForm, objServiceProviderBO,
					objServiceProviderDTO);
			  }
				catch(Exception e)
			  {
					if("PDFVIEWEXCEPTION".equalsIgnoreCase(e.getMessage())){
						
						
						//return new ActionForward("/jsp/areaManagement/FailureViewPDF.jsp");
						//forwardJsp = ServiceProviderConstant
						forwardJsp = ServiceProviderConstant.Failure_jsp;
						objServiceProviderDTO.setActionName("");
					
			  }
			  }
		  }
		  
		  if (("PRINT_LICENSE").equalsIgnoreCase(actionName))
		  {
			  /// language chk commented until format of sp pdf hindi is not shared.
			 /* if("en".equalsIgnoreCase(lang))
			  {*/
			  //modified by shruti---13 dec 2013
			  
			  //modified by shruti----27 may 2014
//			  	String fileName="D:/upload/IGRS/SP010500905201400004"+"/Service_Provider_license.pdf";
//	            byte[] contents =null;
//	            contents=DMSUtility.getDocumentBytes(fileName);
//	            objServiceProviderForm.getObjServiceProviderDTO().setOpticalWatermarkFlag("Y");
//	            objServiceProviderForm.getObjServiceProviderDTO().setLNo(objServiceProviderForm.getObjServiceProviderDTO().getLicenseNumber());
//	            objServiceProviderForm.getObjServiceProviderDTO().setPdfContent(contents);
//	            String guid=GUIDGenerator.getGUID();
//	            String folderpath="uo1/app/oracle/PrintDoc/"+guid+"/";
//	            File folder = new File(folderpath);
//				 if (!folder.exists()) 
//				 {
//		              folder.mkdirs();
//			     }
//				 	File newFile = new File(folderpath);
//					FileOutputStream fos = new FileOutputStream(folderpath);
//					fos.write(contents);
//					fos.close();
//	            forwardJsp=ServiceProviderConstant.VIEW_AFTER_LICENSE_FEE_PAID;
	            //end
	            
	            
	            
			 pdfOfSpLicense(response, objServiceProviderForm,objServiceProviderBO,objServiceProviderDTO,session); 
			
			  /* }*/
			 /* else
			  {
		  		CommonUtil cmUtil=new CommonUtil();
				  //path of jrxml report for the functionality
				  PropertiesFileReader property = PropertiesFileReader.getInstance("resources.igrs");
				  String reportPath = property.getValue("pdf.location")+"report.jrxml";   
				  System.out.println(">>>>"+reportPath);
				// This hashmap is used for passing parameters to your jrxml template.
	            //Here the first parameter denotes the name of the variable in your
				// jrxml template and the second paramater passes the value
				HashMap hm = new HashMap();
				hm.put("SP_REQUEST_NUMBER", objServiceProviderDTO.getRequestNo());
				cmUtil.convertPdfHindi(hm,reportPath,response);
			 }*/
		  }
		  
		  // for renewal of license online application
		  
		  if (request.getParameter("modName")!=null)
		  {
			  if (request.getParameter("modName").equalsIgnoreCase("ServiceProvider") && request.getParameter("fnName").equalsIgnoreCase("renewLicense"))
			  {
				  logger.debug("------> inside renewal online application action");
				  
				  objServiceProviderForm.getObjServiceProviderDTO().setCriteriaPopUp("");
				  
				  String userID = (String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
				  
				  ArrayList details = objServiceProviderBO.getSpTypeRenewal(objServiceProviderForm,lang);
				  
				  if (details.size()>0)
				  {
				  
				  ArrayList pendingApps = objServiceProviderBO.getDroDetailsIfAlreadyFiledRenewal(objServiceProviderForm,lang);
				  
				  logger.debug("*******"+pendingApps.size());
				  
				  	if (pendingApps.size()>0)
				  	{
				  		objServiceProviderForm.getObjServiceProviderDTO().setIsAlreadyFiled(1);	
				  		objServiceProviderForm.getObjServiceProviderDTO().setDroDetails(pendingApps);
				   
				  		forwardJsp = ServiceProviderConstant.SP_APP_FORM_SUCCESS_OR_ALREADY_FILED;
					  
				  	}
				  	else
				  	{
					  
				  		objServiceProviderForm.getObjServiceProviderDTO().setIsAlreadyFiled(0);
				  		objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(0);
				  		objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(0);
				  		objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(0);
				  		objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(0);
				 	  
				  		String id= objServiceProviderForm.getObjServiceProviderDTO().getSpType();
				  		logger.debug("value is - "+id);
					  
				  		String uID = (String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
				  		logger.debug("user id is --" +uID);
					  
				  		if (id.equalsIgnoreCase("1"))
				  		{
				  			objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(1);
				  			objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(0);
						  	ArrayList spDetls = new ArrayList();
						  	spDetls = objServiceProviderBO.getSPDetailsInd(uID,lang);
						  	logger.debug("------->"+spDetls.size());
						  
						  	if (spDetls.size()==0)
						  	{
						  		objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(null);
						  	}
						  	else
						  	{
						  		objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(spDetls);
						  	}
						  
						  	logger.debug("***** get the sp type list"+objServiceProviderBO.getSpType(lang)); 
				  		}
				  		if (id.equalsIgnoreCase("2") || id.equalsIgnoreCase("3") || id.equalsIgnoreCase("4"))
				  		{
						  
				  			objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(1);
				  			objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(0);
				  			ArrayList spDetls = new ArrayList();
				  			spDetls = objServiceProviderBO.getSPDetails(userID,lang);
				  			logger.debug("------->"+spDetls.size());
						  
				  			if (spDetls.size()==0)
				  			{
				  				objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(null);
				  			}
				  			else
				  			{
				  				objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(spDetls);
				  			}
						  
				  			logger.debug("***** get the sp type list"+objServiceProviderBO.getSpType(lang)); 
				  		}
					  
					  
				  		forwardJsp = ServiceProviderConstant.RENEWAL_APP_PAGE_WITH_USER_DETLS_FROM_REG;
				  	}	
				 }
				  
				 else
				 {
					 objServiceProviderForm.getObjServiceProviderDTO().setIsLicenseNotIssued(1);
					 forwardJsp = ServiceProviderConstant.RENEWAL_SUCCESS_COMMENTS_PAGE; 
				 }
				  objServiceProviderDTO.setActionName("");
				  
			  }
			  
			  
		  }
		  
		  if (request.getParameter("actionName")!=null && request.getParameter("actionName").equalsIgnoreCase("CRITERIA_POP_UP"))
		  {
			  String value = objServiceProviderForm.getObjServiceProviderDTO().getSpType();
			  
			  objServiceProviderForm.getObjServiceProviderDTO().setIschecked(1);
			  
			  
			  ArrayList criteriaList = objServiceProviderBO.getCriterias(value);
		      logger.info("--------------->"+criteriaList.size());
		      if(criteriaList.size()==0)
		      {
		      	  objServiceProviderForm.getObjServiceProviderDTO().setEditDeleteCriteria(null);
		      	  objServiceProviderForm.getObjServiceProviderDTO().setIsempty(1);
		      }
		      
		      else
		      {
		    	  objServiceProviderForm.getObjServiceProviderDTO().setIsempty(0);
		      	  objServiceProviderForm.getObjServiceProviderDTO().setEditDeleteCriteria(criteriaList);
		      }
		      	  
		      request.setAttribute("criteriaList", objServiceProviderForm.getObjServiceProviderDTO().getEditDeleteCriteria());
			  
			  
			  forwardJsp=ServiceProviderConstant.POP_UP_TERMS_CRITERIA;
			  objServiceProviderDTO.setActionName("");
		  }
		  
		  if (("NEXT_PAGE_ACTION_RENEWAL").equalsIgnoreCase(actionName))
		  {
			  nextPageOfAppFormForRenewal(request, objServiceProviderForm,objServiceProviderBO,session);
		  }
		  
		  if("LOAD_TEHSIL_RENEWAL".equalsIgnoreCase(actionName))
		  {			  
			  String disttId =  objServiceProviderForm.getObjServiceProviderDTO().getDistrict();
			  logger.debug("District id is - "+disttId);
			  objServiceProviderForm.getObjServiceProviderDTO().setTehsilList(objServiceProviderBO.getTehsil(disttId,lang));
			  forwardJsp=ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			  objServiceProviderDTO.setActionName("");
		  }
		  //added by shruti---21 july 2014
		  if("LOAD_AREA_RENEWAL".equalsIgnoreCase(actionName))
		  {		
			  String tehsilId =  objServiceProviderForm.getObjServiceProviderDTO().getTehsil();
			  logger.debug("tehsilId id is - "+tehsilId);
			  objServiceProviderForm.getObjServiceProviderDTO().setAreaList(objServiceProviderBO.getArea(lang));
			  forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			  objServiceProviderDTO.setActionName("");
		  }
		  if("LOAD_SUB_AREA_RENEWAL".equalsIgnoreCase(actionName))
		  {			  
			  String areaId =  objServiceProviderForm.getObjServiceProviderDTO().getAreaId();
			  logger.debug("area id is - "+areaId);
			  if("2".equalsIgnoreCase(objServiceProviderDTO.getAreaId()))
				{	
				  objServiceProviderDTO.setSubAreaList(objServiceProviderBO.getSubArea(lang,objServiceProviderDTO.getAreaId(),objServiceProviderDTO.getTehsil(),"Y"));
				}
				else
				{
					objServiceProviderDTO.setSubAreaList(objServiceProviderBO.getSubArea(lang,objServiceProviderDTO.getAreaId(),objServiceProviderDTO.getTehsil(),"N"));

				}
			  forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			  objServiceProviderDTO.setActionName("");		 
		  }
		  if("LOAD_WARD_RENEWAL".equalsIgnoreCase(actionName))
		  {			  
			  String subAreaId =  objServiceProviderForm.getObjServiceProviderDTO().getSubAreaId();
			  logger.debug("sub area id is - "+subAreaId);
			  objServiceProviderForm.getObjServiceProviderDTO().setWardPatwariList(objServiceProviderBO.getWardPatwari(lang,objServiceProviderDTO.getSubAreaId(),objServiceProviderDTO.getTehsil()));
			  forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			  objServiceProviderDTO.setActionName("");
		  }
		  if("LOAD_COLONY_RENEWAL".equalsIgnoreCase(actionName))
		  {			  
			  String wardPatwariId =  objServiceProviderForm.getObjServiceProviderDTO().getWardPatwariId();
			  logger.debug("wardPatwariId id is - "+wardPatwariId);
			  objServiceProviderDTO.setColonyList(objServiceProviderBO.getColonyName(lang,objServiceProviderDTO.getWardPatwariId()));
			  forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			  objServiceProviderDTO.setActionName("");
		  }
		  //end
		  /*if("LOAD_WARD_RENEWAL".equalsIgnoreCase(actionName))
		  {
			  String tehsilId =  objServiceProviderForm.getObjServiceProviderDTO().getTehsil();
			  logger.debug("Tehsil id is - "+tehsilId);
			  objServiceProviderForm.getObjServiceProviderDTO().setWardList(objServiceProviderBO.getWardPatwari(tehsilId,lang));
			  forwardJsp=ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			  objServiceProviderDTO.setActionName("");
		  }
		  
		  if("LOAD_MOHALLA_RENEWAL".equalsIgnoreCase(actionName))
		  {			  
			  String wardId =  objServiceProviderForm.getObjServiceProviderDTO().getWard();
			  logger.debug("Ward id is - "+wardId);
			  objServiceProviderForm.getObjServiceProviderDTO().setMohallaList(objServiceProviderBO.getMohallaVillage(wardId,lang));
			  forwardJsp=ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			  objServiceProviderDTO.setActionName("");
		  }*/
		  
		  if ("SUBMIT_ACTION_RENEWAL".equalsIgnoreCase(actionName))
		  {
			  submitActionRenewal(objServiceProviderForm, objServiceProviderBO,
					objServiceProviderDTO, response,session);
		  }
		
		  //If DR forwards the balance to the new license number
		  if("CARRY_FORWARD_BALANCE".equalsIgnoreCase(actionName))
		  {			  
			
			  String bal[] = objServiceProviderForm.getObjServiceProviderDTO().getSelectedValuesOfCreditLimit().split(",");
			  logger.debug(bal[0]);
			  logger.debug(bal[1]);
			  logger.debug(bal[2]); // added by siddhartha
			  boolean insertForwardedAmount = objServiceProviderBO.insertAmount(objServiceProviderForm);
			  
			// for view of license number
			  viewLicenseNumber(objServiceProviderForm, objServiceProviderBO,
						objServiceProviderDTO);
			  objServiceProviderDTO.setActionName("");
		  }
		  
		  // If DR doesn't forward the balance then this action will take him to the next page.
		  if("NEXT_ACTION_FOR_PRINT".equalsIgnoreCase(actionName))
		  {			  
			viewLicenseNumber(objServiceProviderForm, objServiceProviderBO,
					objServiceProviderDTO);
		  }
		  
		  //for getting the value from E-writing pen completion
		  if(request.getParameter("signatureOfDR") != null)
			{
			  	obtainValueFromEwritingIntegration(objServiceProviderForm,
						objServiceProviderDTO);
			  
			}
		  
		  //ADDED BY SHRUTI FOR VIEW OF UPLOADED SIGNATURE
		  if (request.getParameter("actionName")!=null)
		  {
			  if (request.getParameter("actionName").equalsIgnoreCase("viewSign"))
			  {
				  try
				  {
				  //byte contents[] = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal1();
				  String fileName = objServiceProviderForm.getObjServiceProviderDTO().getParentPathSign()+"/"+objServiceProviderForm.getObjServiceProviderDTO().getFileNameSign();
				  byte contents[]=DMSUtility.getDocumentBytes(fileName);
				  downloadDocument(response, contents, fileName);
				  }
				  catch (Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
		  }

		  
		  
		 
		  
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    	}
        logger.debug("FORWARD_JSP===="+forwardJsp);
         return mapping.findForward(forwardJsp);
         

    }
    
    //added by shruti

	/**
	 * @param request
	 * @param objServiceProviderForm
	 * @param objServiceProviderBO
	 * @param objServiceProviderDTO
	 * @return
	 */
	private ServiceProviderForm viewAfterSPClick(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderBO objServiceProviderBO,
			ServiceProviderDTO objServiceProviderDTO,HttpSession session) {
		 String lang=(String)session.getAttribute("languageLocale");
		try {
			  
			  String requestNumber = request.getParameter("hdnApplicationIdSP");
			  objServiceProviderForm.getObjServiceProviderDTO().setRequestNo(requestNumber);
			  objServiceProviderForm.getObjServiceProviderDTO().setReqNo(requestNumber);
			  logger.debug("The request number obtained on the click of dashboard link is: "+requestNumber);
			  
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpBank(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovd(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsPaymentComplete(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsRjectRmrks(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovdRmrks(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPVRmrks(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsFeeNotPaid(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsLicenseNumberPresent(0);
			  
			  
			  objServiceProviderForm = objServiceProviderBO.getPaymentDetls(objServiceProviderForm,lang);
			  logger.debug("The balance amount obtained is: "+Double.toString(objServiceProviderForm.getObjServiceProviderDTO().getAmountRemaining()));
			  
			  String spType = objServiceProviderBO.getspTypeId(requestNumber);
			  
			  logger.debug("The sp type id obtained from database is : "+spType);
			  
			  objServiceProviderForm.getObjServiceProviderDTO().setSpType(spType);
			  
			  
			  if (spType.equalsIgnoreCase("1"))
			  {
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(1);
			  //modified by shruti
			  ArrayList details = objServiceProviderBO.getspDetls(objServiceProviderForm,lang);
			  //end
			  logger.info("--------------->"+ details.size()); 
			  
			  if (details.size() == 0)
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetails(null);
			  }
			  else
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetails(details);
				  
			  }

				  request.setAttribute("details", objServiceProviderForm.getObjServiceProviderDTO().getSpAppDetails());
				  //modified by shruti
				  	ArrayList applicantDetails = objServiceProviderBO.getSPDetailsInd(objServiceProviderForm,lang);
					//end
				  	logger.info("--------------->"+ applicantDetails.size()); 
					
					if (applicantDetails.size() == 0)
					{
						objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(null);
					}
					else
					{
						objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(applicantDetails);
					}

					request.setAttribute("applicantDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDetails());
			  }
			  
			  if (spType.equalsIgnoreCase("2") || spType.equalsIgnoreCase("3") || spType.equalsIgnoreCase("4"))
			  {
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpBank(1);
			  ArrayList details = objServiceProviderBO.getspDetlsBank(objServiceProviderForm,lang);
			  logger.info("--------------->"+ details.size()); 
			  
			  if (details.size() == 0)
				  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsBank(null);
			  else
				  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsBank(details);

				  request.setAttribute("details", objServiceProviderForm.getObjServiceProviderDTO().getSpAppDetailsBank());
			  }
			  
			  if (spType.equalsIgnoreCase("1")||spType.equalsIgnoreCase("2")||spType.equalsIgnoreCase("3")||spType.equalsIgnoreCase("4"))
			  {
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(1);
			  ArrayList details = objServiceProviderBO.getspDetlsAll(objServiceProviderForm,lang);
			  logger.info("--------------->"+ details.size()); 
			  
			  if (details.size() == 0)
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsAll(null);
			  }
			  else
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsAll(details);
			  }

				  request.setAttribute("details", objServiceProviderForm.getObjServiceProviderDTO().getSpAppDetailsAll());
				  
				  ArrayList applicntDetails = objServiceProviderBO.getSPDetails(objServiceProviderForm,lang);
					logger.info("--------------->"+ applicntDetails.size());
					  
					if (applicntDetails.size() == 0)
					{
						objServiceProviderForm.getObjServiceProviderDTO().setSpDetailsAll(null);
					}
					else
					{
						objServiceProviderForm.getObjServiceProviderDTO().setSpDetailsAll(applicntDetails);
					}

					request.setAttribute("applicntDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDetailsAll());
			  }
			  
			  if (("5").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString()))
			  {
				  if (Double.toString(objServiceProviderForm.getObjServiceProviderDTO().getAmountRemaining()).equals("0.0"))
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovd(0); 
					  objServiceProviderForm.getObjServiceProviderDTO().setIsPaymentComplete(1);
					  objServiceProviderForm.getObjServiceProviderDTO().setIsFeeNotPaid(0);
				  }
				  //added by shruti---20 feb 2014
				  if (objServiceProviderForm.getObjServiceProviderDTO().getAmountRemaining()<0.0)
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovd(0); 
					  objServiceProviderForm.getObjServiceProviderDTO().setIsPaymentComplete(1);
					  objServiceProviderForm.getObjServiceProviderDTO().setIsFeeNotPaid(0);
				  }
				  //end
				  else
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovd(1); 
					  objServiceProviderForm.getObjServiceProviderDTO().setIsPaymentComplete(0);
					  objServiceProviderForm.getObjServiceProviderDTO().setIsFeeNotPaid(0);
				  }
				  
				  
				  
				  objServiceProviderForm.getObjServiceProviderDTO().setIsRjectRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovdRmrks(1);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPVRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsLicenseNumberPresent(0);
				  //for getting latest comments of DR
				  objServiceProviderForm = objServiceProviderBO.getDRComments(objServiceProviderForm);
			  }
			  
			  if ("3".equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString()))
			  {
				  
				  objServiceProviderForm.getObjServiceProviderDTO().setIsRjectRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovdRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPVRmrks(1);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsLicenseNumberPresent(0);
				//for getting latest comments of DR
				  objServiceProviderForm = objServiceProviderBO.getDRComments(objServiceProviderForm);
			  }
			  
			  if ("10".equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString()))
			  {
				  
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovd(0); 
				  objServiceProviderForm.getObjServiceProviderDTO().setIsPaymentComplete(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsFeeNotPaid(1);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsRjectRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovdRmrks(1);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPVRmrks(0);
				  //for getting latest comments of DR
				  objServiceProviderForm = objServiceProviderBO.getDRComments(objServiceProviderForm);
			  }
			  
			  if ("6".equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString()))
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsRjectRmrks(1);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovdRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPVRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsLicenseNumberPresent(0);
				  //for getting latest comments of DR
				  objServiceProviderForm = objServiceProviderBO.getDRComments(objServiceProviderForm);
			  }
			  
			  if ("8".equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString()))
			  {
				  
				  
				  ArrayList licenseDetails = objServiceProviderBO.getLicenseDetails(objServiceProviderForm);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsRjectRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovdRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPVRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsLicenseNumberPresent(1);
			  }
			  
			  if ("4".equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString()))
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminal(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiGaz(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setDobCerti(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setRentDetail(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setHigherSecCerti(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setPhotoImg(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setCharacterCerti(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setAgeProof(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setSolvencyCerti(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setPoliceCaseCerti(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setPossessionBusinessDoc(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setHigherEduCerti(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setAffidavitHardware(null);
				  //added by shruti---2nd april 2014
				  objServiceProviderForm.getObjServiceProviderDTO().setPancardUpload(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setForm60Upload(null);
				  //end
				  objServiceProviderForm.getObjServiceProviderDTO().setDistrictList(objServiceProviderBO.getDistrict(lang));
				  //for getting latest comments of DR
				  objServiceProviderForm = objServiceProviderBO.getDRComments(objServiceProviderForm);
				  
				  if (spType.equalsIgnoreCase("1"))
				  {
					  	objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(1);
					  
					  	objServiceProviderForm =(ServiceProviderForm) objServiceProviderBO.getspDetlsForUpdation(objServiceProviderForm,lang);
					  
					  	ArrayList applicantDetails = objServiceProviderBO.getSPDetailsInd(objServiceProviderForm,lang);
						logger.info("--------------->"+ applicantDetails.size()); 
						
						if (applicantDetails.size() == 0)
						{
							objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(null);
						}
						else
						{
							objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(applicantDetails);
						}

						request.setAttribute("applicantDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDetails());
				  }
				  
				  if (spType.equalsIgnoreCase("2") || spType.equalsIgnoreCase("3") || spType.equalsIgnoreCase("4"))
				  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpBank(1);
				  objServiceProviderForm = (ServiceProviderForm)objServiceProviderBO.getspDetlsBankForUpdation(objServiceProviderForm,lang);
				  }
				  
				  if (spType.equalsIgnoreCase("1")||spType.equalsIgnoreCase("2")||spType.equalsIgnoreCase("3")||spType.equalsIgnoreCase("4"))
				  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(1);
				  objServiceProviderForm = (ServiceProviderForm)objServiceProviderBO.getspDetlsAllForUpdation(objServiceProviderForm,lang);
				  
				  String disttName = (String)objServiceProviderForm.getObjServiceProviderDTO().getDistrict();
				  String disttId =(String)objServiceProviderBO.getdisttId(objServiceProviderForm);
				  //added by shruti--24 march 2014
				  String tehsilId=(String)objServiceProviderBO.getTehsilId(objServiceProviderForm);
				  String areaId=(String)objServiceProviderBO.getAreaId(objServiceProviderForm);
				  String subareaId=(String)objServiceProviderBO.getSubAreaId(objServiceProviderForm);
				  String wardId=(String)objServiceProviderBO.getWardId(objServiceProviderForm,subareaId,tehsilId);
				  String mohallaId="";
				  if(wardId!=null && !wardId.equals(""))
				  {
				  mohallaId=(String)objServiceProviderBO.getMohallaId(objServiceProviderForm,wardId.split("~")[1]);
				  }
				  //String mohallaId=wardId.split("~")[1];
				  //end
				  objServiceProviderForm.getObjServiceProviderDTO().setDistrict(disttId);
				  //added by shruti--24 march 2014
				  objServiceProviderForm.getObjServiceProviderDTO().setTehsil(tehsilId);
				  /*objServiceProviderForm.getObjServiceProviderDTO().setWard(wardId);
				  objServiceProviderForm.getObjServiceProviderDTO().setMohalla(mohallaId);*/
				  
				  objServiceProviderForm.getObjServiceProviderDTO().setWardPatwariId(wardId);
				  objServiceProviderForm.getObjServiceProviderDTO().setColonyId(mohallaId);
				  
				  objServiceProviderForm.getObjServiceProviderDTO().setAreaId(areaId);
				  objServiceProviderForm.getObjServiceProviderDTO().setSubAreaId(subareaId);
				  //end
				  objServiceProviderForm.getObjServiceProviderDTO().setTehsilList(objServiceProviderBO.getTehsil(disttId,lang));
					  //added by shruti---24 march 2014
				  /*objServiceProviderForm.getObjServiceProviderDTO().setWardList(objServiceProviderBO.getWardPatwari(tehsilId, lang));
				  objServiceProviderForm.getObjServiceProviderDTO().setMohallaList(objServiceProviderBO.getMohallaVillage(wardId, lang));*/
				  objServiceProviderForm.getObjServiceProviderDTO().setWardPatwariList(objServiceProviderBO.getWardPatwari(lang,subareaId,tehsilId));
				  objServiceProviderForm.getObjServiceProviderDTO().setColonyList(objServiceProviderBO.getColonyName(lang,wardId));
				  
				  objServiceProviderForm.getObjServiceProviderDTO().setAreaList(objServiceProviderBO.getArea(lang));
				  
				  if("2".equalsIgnoreCase(objServiceProviderDTO.getAreaId()))
					{	
					  objServiceProviderForm.getObjServiceProviderDTO().setSubAreaList(objServiceProviderBO.getSubArea(lang,objServiceProviderDTO.getAreaId(),objServiceProviderDTO.getTehsil(),"Y"));
					}
					else
					{
						 objServiceProviderForm.getObjServiceProviderDTO().setSubAreaList(objServiceProviderBO.getSubArea(lang,objServiceProviderDTO.getAreaId(),objServiceProviderDTO.getTehsil(),"N"));

					}
				  
				  //end
					  ArrayList applicntDetails = objServiceProviderBO.getSPDetails(objServiceProviderForm,lang);
						logger.info("--------------->"+ applicntDetails.size());
						  
						if (applicntDetails.size() == 0)
						{
							objServiceProviderForm.getObjServiceProviderDTO().setSpDetailsAll(null);
						}
						else
						{
							objServiceProviderForm.getObjServiceProviderDTO().setSpDetailsAll(applicntDetails);
						}

						request.setAttribute("applicntDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDetailsAll());
				  }
				  
				  objServiceProviderForm = (ServiceProviderForm) objServiceProviderBO.getspDocDetlsDR(objServiceProviderForm);
				  forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			  }
			  else
			  {
				  objServiceProviderForm = (ServiceProviderForm) objServiceProviderBO.getspDocDetlsDR(objServiceProviderForm);
				  forwardJsp = ServiceProviderConstant.VIEW_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;  
			  }
				objServiceProviderDTO.setActionName("");
			  }
		  	
			  
		  catch (Exception e)
		  {
			  e.printStackTrace();
		  }
		return objServiceProviderForm;
	}

	/**
	 * @param objServiceProviderForm
	 * @param objServiceProviderDTO
	 */
	private void obtainValueFromEwritingIntegration(
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try
		{
		  objServiceProviderForm.getObjServiceProviderDTO().setIsSigned(1);
		  objServiceProviderForm.getObjServiceProviderDTO().setDocName(objServiceProviderForm.getObjServiceProviderDTO().getFileNameSign());
		  objServiceProviderForm.getObjServiceProviderDTO().setDocPath(objServiceProviderForm.getObjServiceProviderDTO().getParentPathSign());
		  objServiceProviderForm.getObjServiceProviderDTO().setSignatureName(objServiceProviderForm.getObjServiceProviderDTO().getFileNameSign());
		  
		  forwardJsp=ServiceProviderConstant.VIEW_AFTER_LICENSE_FEE_PAID;
		  objServiceProviderDTO.setActionName("");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @param objServiceProviderForm
	 * @param objServiceProviderBO
	 * @param objServiceProviderDTO
	 * @return
	 */
	private ServiceProviderForm generateLicenseNumber(
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderBO objServiceProviderBO,
			ServiceProviderDTO objServiceProviderDTO) throws Exception {
		try
		  {
			 logger.debug("Inside first time DR process ");
			  String ofcId = (String)objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
			  logger.debug("The office id of the logged in DR is "+ofcId);
			  String distt = objServiceProviderBO.getdisttId(ofcId);
			  logger.debug("The district id of the logged in DR is "+distt);
			  objServiceProviderForm.getObjServiceProviderDTO().setDrDistrict(distt);
			  ArrayList licenseDetails = objServiceProviderBO.getLicenseDetails(objServiceProviderForm);
			  logger.debug("Size of ArrayList licenseDetails:- "+licenseDetails.size());
			  
			  if (licenseDetails.size()>0)
			  {
				  boolean renewLicenseNumber = false;
				  renewLicenseNumber = objServiceProviderBO.renewLicenseNumber(objServiceProviderForm);
				  logger.debug("license number updated:- "+renewLicenseNumber);
				  
				  //for inserting signature in case of renewal
				  if ((Integer.toString(objServiceProviderForm.getObjServiceProviderDTO().getIsSigned())).equalsIgnoreCase("1"))
				  {
					  
				  boolean insrtSignDetls = false;
				  insrtSignDetls = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSigned(0);
				  
				  }
				  
				  // for view of license number.
				  objServiceProviderForm = objServiceProviderBO.getFirstTimeLicenseDetails(objServiceProviderForm);
				  
				  //added by shruti---10 sep 2014--digital signature
				  
				  objServiceProviderForm.getObjServiceProviderDTO().setEnablePrintFlag("N");
				  //end
				  forwardJsp=ServiceProviderConstant.PAGE_AFTER_LICENSE_NUMBR_GENERATION;
			  }
			  else
			  {
			  // for inserting license number in the database.
			  boolean insrtLicenseNumber = false;
			  insrtLicenseNumber = objServiceProviderBO.insertLicenseNumber(objServiceProviderForm);
			  
			  if ((Integer.toString(objServiceProviderForm.getObjServiceProviderDTO().getIsSigned())).equalsIgnoreCase("1"))
			  {
				  
			  boolean insrtSignDetls = false;
			  insrtSignDetls = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSigned(0);
			  
			  }
			 //TODO --- Changes to display credit limit page first time after generating license by santosh on 13/04/2017.
			  ArrayList licenseIssuedFeePaid = objServiceProviderBO.getLicenseNumber(objServiceProviderForm);
			  // for account updation
			  ArrayList previousLicenseDetlsEstamp = objServiceProviderBO.getPreviousLicenseDetailsEstamp1(objServiceProviderForm);
			  ArrayList previousLicenseDetlsOthers = objServiceProviderBO.getPreviousLicenseDetailsOthers1(objServiceProviderForm);
	          // added by siddhartha
			  ArrayList previousLicenseDetlsJudicialEstamp = objServiceProviderBO.getPreviousLicenseDetailsJudicialEstamp1(objServiceProviderForm);
			  logger.debug("Size of previous license details arrayList is: "+previousLicenseDetlsEstamp.size());
			  logger.debug("Size of previous license details arrayList is: "+previousLicenseDetlsOthers.size());
			  logger.debug("Size of previous license details arrayList is: "+previousLicenseDetlsJudicialEstamp.size());
			  
			  if((previousLicenseDetlsEstamp.size()>0)||(previousLicenseDetlsOthers.size()>0)||previousLicenseDetlsJudicialEstamp.size()>0)
			  {
				//changes to make balance amount carry forward default option yes by santosh
				  //objServiceProviderForm.getObjServiceProviderDTO().setRadioBal("N");
				  objServiceProviderForm.getObjServiceProviderDTO().setRadioBal("Y");
				  objServiceProviderForm.getObjServiceProviderDTO().setChkBoxCreditLimit("");
				  if ((previousLicenseDetlsEstamp.size()>0))
				  {
				  objServiceProviderForm.getObjServiceProviderDTO().setEstampBalanceCreditLimit(objServiceProviderForm.getObjServiceProviderDTO().getBalAmountCreditEstamp());
				  logger.debug("got estamp amount for non judicial");
				  }
				  else 
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setEstampBalanceCreditLimit("0"); 
					  objServiceProviderForm.getObjServiceProviderDTO().setHdnBalAmountCreditEstamp("0"); 
					  logger.debug(" non judicial - amount not retrieved");
				  }
				  if ((previousLicenseDetlsOthers.size()>0))
				  {
				  objServiceProviderForm.getObjServiceProviderDTO().setOthersBalanceCreditLimit(objServiceProviderForm.getObjServiceProviderDTO().getBalAmountCreditOthers());
				  logger.debug("got estamp amount for Other services");
				  }
				  else
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setOthersBalanceCreditLimit("0");
					  objServiceProviderForm.getObjServiceProviderDTO().setHdnBalAmountCreditOthers("0");
					  logger.debug(" Other services - amount not retrieved");
				  }
				  // added by siddhartha
				  if ((previousLicenseDetlsJudicialEstamp.size()>0))
				  {
				  objServiceProviderForm.getObjServiceProviderDTO().setJudicialEstampBalanceCreditLimit(objServiceProviderForm.getObjServiceProviderDTO().getBalAmountCreditEstampJudicial());
				  logger.debug("got estamp amount for judicial");
				  }
				  else 
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setJudicialEstampBalanceCreditLimit("0"); 
					  objServiceProviderForm.getObjServiceProviderDTO().setHdnBalAmountCreditEstampJudicial("0"); 
					  logger.debug(" Judicial - amount not retrieved");
				  }
				  
				  // for view of license number.
				  objServiceProviderForm = objServiceProviderBO.getFirstTimeLicenseDetails(objServiceProviderForm);
				  forwardJsp=ServiceProviderConstant.PAGE_SHOWING_CARRY_FORWARD_BALANCE;
				  logger.debug("FORWARD_JSP===="+forwardJsp);
			  }
			  else
			  {
				  viewLicenseNumber(objServiceProviderForm, objServiceProviderBO,
							objServiceProviderDTO);
			  }
			  }
			  
			  objServiceProviderDTO.setActionName("");
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
			  if("PDFVIEWEXCEPTION".equalsIgnoreCase(e.getMessage())){
					throw e;
					
					//return new ActionForward("/jsp/areaManagement/FailureViewPDF.jsp");
					//forwardJsp = ServiceProviderConstant
					
				
				}	
		  }
		return objServiceProviderForm;
	}

	/**
	 * @param objServiceProviderForm
	 * @param objServiceProviderBO
	 * @param objServiceProviderDTO
	 * @throws Exception
	 */
	private void viewLicenseNumber(ServiceProviderForm objServiceProviderForm,
			ServiceProviderBO objServiceProviderBO,
			ServiceProviderDTO objServiceProviderDTO) throws Exception {
		// for view of license number.
		  objServiceProviderForm = objServiceProviderBO.getFirstTimeLicenseDetails(objServiceProviderForm);
		  forwardJsp=ServiceProviderConstant.PAGE_AFTER_LICENSE_NUMBR_GENERATION;
		  objServiceProviderDTO.setActionName("");
	}

	/**
	 * @param objServiceProviderForm
	 * @param objServiceProviderBO
	 * @param objServiceProviderDTO
	 * @throws Exception
	 */
	private void submitActionRenewal(
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderBO objServiceProviderBO,
			ServiceProviderDTO objServiceProviderDTO, HttpServletResponse response,HttpSession session) throws Exception {
		boolean insertTxn = false;
		  String lang=(String)session.getAttribute("languageLocale");
		  PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		if( objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect()!=null && objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect1()!=null)
		  {
			  String language = objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect()+"&"+objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect1();
			  objServiceProviderForm.getObjServiceProviderDTO().setLangKnown(language);
		  }
		  
		  else if(objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect()==null && objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect1()!=null)
		  {
			  String language = (String) objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect1();
			  logger.debug("------>"+language);
			  objServiceProviderForm.getObjServiceProviderDTO().setLangKnown(language);
		  }
		  
		  else if(objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect()!=null && objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect1()==null)
		  {
			  String language = (String) objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect();
			  objServiceProviderForm.getObjServiceProviderDTO().setLangKnown(language);
		  }
		  
		  objServiceProviderForm.getObjServiceProviderDTO().setNewOrRenewalFlag("Renewed");
		  insertTxn = objServiceProviderBO.insertSpDetls(objServiceProviderForm);
		  
		  logger.debug("Success--->"+insertTxn);
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal()!=null)
		  {
			  
			  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal();
			  String fileExt = getFileExtension(photo.getFileName());
			  String docName ="Criminal_Activities_Affidavit."+fileExt;
			  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
			  
			  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
			  
			  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
			  boolean up=uploadFile(photo,docName,docPath);
			  boolean insertTxn1 = false;
			  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
		  }
		  
		  else if (objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal()==null)
		  {  
			  
			  String filePath = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminalPath();
			  
			  	  File fileToDownload = new File(filePath);
			  	  // call function to get byte array and store bytes
			  	  byte[] photo = getByteArray(fileToDownload);
			  	
				  String docName =objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminalDoc();
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFileFromBytes(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
			  }
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGaz()!=null)
		  {
			  
			  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGaz();
			  String fileExt = getFileExtension(photo.getFileName());
			  String docName ="Character_Ceertificate_Gazette."+fileExt;
			  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
			  
			  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
			  
			  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
			  boolean up=uploadFile(photo,docName,docPath);
			  boolean insertTxn1 = false;
			  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
			  
			    
		  }
		  
		  else if (objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGaz()==null)
		  {  
			  
			  String filePath = objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGazPath();
			  
			  	  File fileToDownload = new File(filePath);
			  	  // call function to get byte array and store bytes
			  	  byte[] photo = getByteArray(fileToDownload);
			  	
				  String docName =objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGazDoc();
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFileFromBytes(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
			  }
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getDobCerti()!=null)
		  {
			  
			  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getDobCerti();
			  String fileExt = getFileExtension(photo.getFileName());
			  String docName ="DOB_Certificate."+fileExt;
			  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
			  
			  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
			  
			  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
			  boolean up=uploadFile(photo,docName,docPath);
			  boolean insertTxn1 = false;
			  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
			   
		  }
		  
		  else if (objServiceProviderForm.getObjServiceProviderDTO().getDobCerti()==null)
		  {  
			  
			  String filePath = objServiceProviderForm.getObjServiceProviderDTO().getDobCertiPath();
			  
			  	  File fileToDownload = new File(filePath);
			  	  // call function to get byte array and store bytes
			  	  byte[] photo = getByteArray(fileToDownload);
			  	
				  String docName =objServiceProviderForm.getObjServiceProviderDTO().getDobCertiDoc();
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFileFromBytes(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
			  }
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getRentDetail()!=null)
		  {
			  
			  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getRentDetail();
			  String fileExt = getFileExtension(photo.getFileName());
			  String docName ="Rent_Details."+fileExt;
			  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
			  
			  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
			  
			  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
			  boolean up=uploadFile(photo,docName,docPath);
			  boolean insertTxn1 = false;
			  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
		  }
		  
		  else if (objServiceProviderForm.getObjServiceProviderDTO().getRentDetail()==null)
		  {  
			  
			  String filePath = objServiceProviderForm.getObjServiceProviderDTO().getRentDetailPath();
			  
			  	  File fileToDownload = new File(filePath);
			  	  // call function to get byte array and store bytes
			  	  byte[] photo = getByteArray(fileToDownload);
			  	
				  String docName =objServiceProviderForm.getObjServiceProviderDTO().getRentDetailDoc();
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFileFromBytes(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
			  }
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCerti()!=null)
		  {
			  
			  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCerti();
			  String fileExt = getFileExtension(photo.getFileName());
			  String docName ="Higher_Secondary_Certificate."+fileExt;
			  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
			  
			  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
			  
			  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
			  boolean up=uploadFile(photo,docName,docPath);
			  boolean insertTxn1 = false;
			  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
			   
		  }
		  
		  else if (objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCerti()==null)
		  {  
			  
			  String filePath = objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCertiPath();
			  
			  	  File fileToDownload = new File(filePath);
			  	  // call function to get byte array and store bytes
			  	  byte[] photo = getByteArray(fileToDownload);
			  	
				  String docName =objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCertiDoc();
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFileFromBytes(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
			  }
		  
		  
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getPhotoImg()!=null)
		  {
			  
			  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPhotoImg();
			  String fileExt = getFileExtension(photo.getFileName());
			  //String docName ="Photo."+fileExt;
			  String docName ="Photo."+pr.getValue("sp_photo_extension_param");
			  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
			  
			  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
			  
			  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
			  boolean up=uploadFile(photo,docName,docPath);
			  boolean insertTxn1 = false;
			  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
			    
		  }
		  
		  else if (objServiceProviderForm.getObjServiceProviderDTO().getPhotoImg()==null)
		  {  
			  
			  String filePath = objServiceProviderForm.getObjServiceProviderDTO().getPhotoImgPath();
			  
			  	  File fileToDownload = new File(filePath);
			  	  // call function to get byte array and store bytes
			  	  byte[] photo = getByteArray(fileToDownload);
			  	
				  String docName =objServiceProviderForm.getObjServiceProviderDTO().getPhotoImgDoc();
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFileFromBytes(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
			  }
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getCharacterCerti()!=null)
		  {
			  
			  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getCharacterCerti();
			  String fileExt = getFileExtension(photo.getFileName());
			  String docName ="Character_Certificate."+fileExt;
			  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
			  
			  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
			  
			  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
			  boolean up=uploadFile(photo,docName,docPath);
			  boolean insertTxn1 = false;
			  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
			  
		  }
		  
		  else if (objServiceProviderForm.getObjServiceProviderDTO().getCharacterCerti()==null)
		  {  
			  
			  String filePath = objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiPath();
			  
			  	  File fileToDownload = new File(filePath);
			  	  // call function to get byte array and store bytes
			  	  byte[] photo = getByteArray(fileToDownload);
			  	
				  String docName =objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiDoc();
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFileFromBytes(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
			  }
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getAgeProof()!=null)
		  {
			  
			  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAgeProof();
			  String fileExt = getFileExtension(photo.getFileName());
			  String docName ="Age_Proof."+fileExt;
			  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
			  
			  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
			  
			  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
			  boolean up=uploadFile(photo,docName,docPath);
			  boolean insertTxn1 = false;
			  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm);
			  
		  }
		  
		  else if (objServiceProviderForm.getObjServiceProviderDTO().getAgeProof()==null)
		  {  
			  
			  String filePath = objServiceProviderForm.getObjServiceProviderDTO().getAgeProofPath();
			  
			  	  File fileToDownload = new File(filePath);
			  	  // call function to get byte array and store bytes
			  	  byte[] photo = getByteArray(fileToDownload);
			  	
				  String docName =objServiceProviderForm.getObjServiceProviderDTO().getAgeProofDoc();
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFileFromBytes(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
			  }
		  
		  /*if (objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCerti()!=null)
		  {
			  
			  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCerti();
			  String fileExt = getFileExtension(photo.getFileName());
			  String docName ="Solvency_Certificate."+fileExt;
			  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
			  
			  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
			  
			  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
			  boolean up=uploadFile(photo,docName,docPath);
			  boolean insertTxn1 = false;
			  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm);
			  
		  }
		  
		  else if (objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCerti()==null)
		  {  
			  
			  String filePath = objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCertiPath();
			  
			  	  File fileToDownload = new File(filePath);
			  	  // call function to get byte array and store bytes
			  	  byte[] photo = getByteArray(fileToDownload);
			  	
				  String docName =objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCertiDoc();
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFileFromBytes(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
			  }*/
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCerti()!=null)
		  {
			  
			  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCerti();
			  String fileExt = getFileExtension(photo.getFileName());
			  String docName ="Affidavit_Police_Case."+fileExt;
			  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
			  
			  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
			  
			  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
			  boolean up=uploadFile(photo,docName,docPath);
			  boolean insertTxn1 = false;
			  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm);
			    
		  }
		  
		  else if (objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCerti()==null)
		  {  
			  
			  String filePath = objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCertiPath();
			  
			  	  File fileToDownload = new File(filePath);
			  	  // call function to get byte array and store bytes
			  	  byte[] photo = getByteArray(fileToDownload);
			  	
				  String docName =objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCertiDoc();
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFileFromBytes(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
			  }
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDoc()!=null)
		  {
			  
			  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDoc();
			  String fileExt = getFileExtension(photo.getFileName());
			  String docName ="Business_Premises."+fileExt;
			  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
			  
			  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
			  
			  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
			  boolean up=uploadFile(photo,docName,docPath);
			  boolean insertTxn1 = false;
			  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm);
			   
		  }
		  
		  else if (objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDoc()==null)
		  {  
			  
			  String filePath = objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDocPath();
			  
			  	  File fileToDownload = new File(filePath);
			  	  // call function to get byte array and store bytes
			  	  byte[] photo = getByteArray(fileToDownload);
			  	
				  String docName =objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDocDoc();
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFileFromBytes(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
			  }
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCerti()!=null)
		  {
			  
			  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCerti();
			  String fileExt = getFileExtension(photo.getFileName());
			  String docName ="Education_Qualification."+fileExt;
			  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
			  
			  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
			  
			  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
			  boolean up=uploadFile(photo,docName,docPath);
			  boolean insertTxn1 = false;
			  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm);
			  
		  }
		  
		  else if (objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCerti()==null)
		  {  
			  
			  String filePath = objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCertiPath();
			  
			  	  File fileToDownload = new File(filePath);
			  	  // call function to get byte array and store bytes
			  	  byte[] photo = getByteArray(fileToDownload);
			  	
				  String docName =objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCertiDoc();
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFileFromBytes(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
			  }
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware()!=null)
		  {
			  
			  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware();
			  String fileExt = getFileExtension(photo.getFileName());
			  String docName ="Hardware_Available."+fileExt;;
			  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
			  
			  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
				else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
				{
				String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
				//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
				objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
				}
			  
			  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
			  boolean up=uploadFile(photo,docName,docPath);
			  boolean insertTxn1 = false;
			  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm);
			  
		  }
		  
		  else if (objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware()==null)
		  {  
			  
			  String filePath = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardwarePath();
			  
			  	  File fileToDownload = new File(filePath);
			  	  // call function to get byte array and store bytes
			  	  byte[] photo = getByteArray(fileToDownload);
			  	
				  String docName =objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardwareDoc();
				  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
				  
				  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
					else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
					{
					String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
					//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
					}
				  
				  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
				  boolean up=uploadFileFromBytes(photo,docName,docPath);
				  boolean insertTxn1 = false;
				  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
			  }
		//ADDED BY SHRUTI---6 May 2014
		  if(objServiceProviderForm.getPanCardFlag()!=""||objServiceProviderForm.getPanCardFlag()!=null)
		  {
			  if("Y".equalsIgnoreCase(objServiceProviderForm.getPanCardFlag()))
			  {
				  if (objServiceProviderForm.getObjServiceProviderDTO().getPancardUpload()!=null)
					  //if (objServiceProviderForm.getObjServiceProviderDTO().getPancardDoc()!=null)
					  {
						  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getPancardUpload();
						  String fileExt = getFileExtension(photo.getFileName());
						  String docName ="PanCard."+fileExt;;
						  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
						  
						  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
							{
							String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
							objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
							}
							else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
							{
							String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
							//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
							objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
							}
							else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
							{
							String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
							objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
							}
							else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
							{
							String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
							objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
							}
						  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
						  boolean up=uploadFile(photo,docName,docPath);
						  boolean insertTxn1 = false;
						  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm);
						  
					  }
				  else if (objServiceProviderForm.getObjServiceProviderDTO().getPancardUpload()==null)
				  {  
					  String filePath = objServiceProviderForm.getObjServiceProviderDTO().getPancardPath();
					  
					  	  File fileToDownload = new File(filePath);
					  	  // call function to get byte array and store bytes
					  	  byte[] photo = getByteArray(fileToDownload);
					  	
						  String docName =objServiceProviderForm.getObjServiceProviderDTO().getPancardDoc();
						  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
						  
						  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
							{
							String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
							//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
							objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
							}
							else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
							{
							String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
							//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
							objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
							}
							else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
							{
							String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
							//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
							objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
							}
							else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
							{
							String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
							//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
							objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
							}
						  
						  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
						  boolean up=uploadFileFromBytes(photo,docName,docPath);
						  boolean insertTxn1 = false;
						  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
					  }
			  }
		  }
		  if(objServiceProviderForm.getForm60Flag()!=""||objServiceProviderForm.getForm60Flag()!=null)
		  {
			  if("Y".equalsIgnoreCase(objServiceProviderForm.getForm60Flag()))
			  {
				  if (objServiceProviderForm.getObjServiceProviderDTO().getForm60Upload()!=null)
				  {
					  FormFile photo =objServiceProviderForm.getObjServiceProviderDTO().getForm60Upload();
					  String fileExt = getFileExtension(photo.getFileName());
					  String docName ="Form60_61."+fileExt;;
					  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
					  
					  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
						else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
						{
						String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
						objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
						}
					  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
					  boolean up=uploadFile(photo,docName,docPath);
					  boolean insertTxn1 = false;
					  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm);
					  
				  }
				  else if (objServiceProviderForm.getObjServiceProviderDTO().getForm60Upload()==null)
				  {  
					  String filePath = objServiceProviderForm.getObjServiceProviderDTO().getForm60Path();
					  
					  	  File fileToDownload = new File(filePath);
					  	  // call function to get byte array and store bytes
					  	  byte[] photo = getByteArray(fileToDownload);
					  	
						  String docName =objServiceProviderForm.getObjServiceProviderDTO().getForm60Doc();
						  objServiceProviderForm.getObjServiceProviderDTO().setDocName(docName);
						  
						  if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
							{
							String docPath = pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
							//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
							objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);
							}
							else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
							{
							String docPath = pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
							//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
							objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
							}
							else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
							{
							String docPath = pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
							//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
							objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
							}
							else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
							{
							String docPath = pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
							//objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
							objServiceProviderForm.getObjServiceProviderDTO().setDocPath(docPath);	
							}
						  
						  String docPath =objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
						  boolean up=uploadFileFromBytes(photo,docName,docPath);
						  boolean insertTxn1 = false;
						  insertTxn1 = objServiceProviderBO.insertSpDocDetls1(objServiceProviderForm); 
					  }
			  }
		  }
		 
		  //END
		  
		  String distt= objServiceProviderForm.getObjServiceProviderDTO().getDistrict();
		  logger.debug(distt);
		  ArrayList drOfficeDetails = new ArrayList();
		
		  drOfficeDetails= objServiceProviderBO.getDroDetails(distt,lang);
		  logger.debug("size of arraylist: "+drOfficeDetails.size());
		  
		  if (drOfficeDetails.size()==0)
		  {
			  objServiceProviderForm.getObjServiceProviderDTO().setDroDetails(null);
		  }
		  else
		  {
			  objServiceProviderForm.getObjServiceProviderDTO().setDroDetails(drOfficeDetails);
		  }
		  
		  forwardJsp=ServiceProviderConstant.SP_APP_FORM_SUCCESS_OR_ALREADY_FILED;
		  
		  objServiceProviderDTO.setActionName("");
	}

	/**
	 * @param request
	 * @param response
	 * @param objServiceProviderForm
	 * @param objServiceProviderDTO
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	private void retrieval2(HttpServletRequest request,
			HttpServletResponse response,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO)
			throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
		        
		        if(request.getParameter("path")!=null){

		        		if (objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGaz()!=null)
		          	  {
		          		  FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGaz();
		          		  byte contents[] = uploadedFile.getFileData();
							  objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiGaz1(contents);
							  String fileExt = getFileExtension(uploadedFile.getFileName());
						      String fileName ="Character_Ceertificate_Gazette."+fileExt;
							  downloadDocument(response, contents, fileName);
							  if(request.getParameter("isSpEdit")!=null)
		     					{
		     						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
		     						{
		     							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
		     						}
		     						
		     					}
							  
							  else if(request.getParameter("isRenewal")!=null)
		     					{
		     						if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
		     						{
		     							forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
		     						}
		     						
		     					}
							  
							  else
							  {
								  forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
							  }
		          	  }
		          	  else
		          	  {
		          		  String filename = request.getParameter("path").toString();

						   // set the http content type to "APPLICATION/OCTET-STREAM
						   response.setContentType("application/download");

						   // initialize the http content-disposition header to
						   // indicate a file attachment with the default filename
						   // "myFile.txt"
						   String fileName = objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGazDoc();
						   //Filename=\"myFile.txt\"";
						   response.setHeader("Content-Disposition", "attachment; filename="
								+ URLEncoder.encode(fileName,"UTF-8"));

						   // transfer the file byte-by-byte to the response object
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
						   
						  if(request.getParameter("isSpEdit")!=null)
		 					{
		 						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
		 						{
		 							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
		 						}
		 						
		 					}
						  
						 else if(request.getParameter("isRenewal")!=null)
							{
								if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
								{
									forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
								}
								
							}
						  
						  else
						  {
						   
							 forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
						  }
		               
		          }
		        		 objServiceProviderDTO.setActionName("");
		        }
		        
	}

	/**
	 * @param request
	 * @param response
	 * @param objServiceProviderForm
	 * @param objServiceProviderDTO
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	private void retrieval1(HttpServletRequest request,
			HttpServletResponse response,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO)
			throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
		        
		        if(request.getParameter("path")!=null){

		        	  if (objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal()!=null)
		        	  {
		        		  FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal();
		        		  byte contents[] = uploadedFile.getFileData();
						  objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminal1(contents);
						  String fileExt = getFileExtension(uploadedFile.getFileName());
					      String fileName ="Criminal_Activities_Affidavit."+fileExt;
						  downloadDocument(response, contents, fileName);
						if(request.getParameter("isSpEdit")!=null)
						{
							if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
							{
								forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
							}
							
						}
						
						else if(request.getParameter("isRenewal")!=null)
						{
							if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
							{
								forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
							}
							
						}
						
						else
						{
							forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
						}
		        	  }
		        	  else
		        	  {
		        		  String filename = request.getParameter("path").toString();

					   // set the http content type to "APPLICATION/OCTET-STREAM
					   response.setContentType("application/download");

					   // initialize the http content-disposition header to
					   // indicate a file attachment with the default filename
					   // "myFile.txt"
					   String fileName = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminalDoc();
					   //Filename=\"myFile.txt\"";
					   response.setHeader("Content-Disposition", "attachment; filename="
							+ URLEncoder.encode(fileName,"UTF-8"));

					   // transfer the file byte-by-byte to the response object
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
					   
					if(request.getParameter("isSpEdit")!=null)
					{
						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
						{
							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
						}
						
					}
					
					else if(request.getParameter("isRenewal")!=null)
					{
						if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
						{
							forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
						}
						
					}
					
					else
					{
					   
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
					}
					
		              
		        }
		        	  objServiceProviderDTO.setActionName("");
		      }
		        
	}

	/**
	 * @param request
	 * @param response
	 * @param objServiceProviderForm
	 * @param objServiceProviderDTO
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	private void retrieval4(HttpServletRequest request,
			HttpServletResponse response,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO)
			throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
		        
		        if(request.getParameter("path")!=null){

		        	if (objServiceProviderForm.getObjServiceProviderDTO().getRentDetail()!=null)
		      	  {
		      		  FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getRentDetail();
		      		  byte contents[] = uploadedFile.getFileData();
						  objServiceProviderForm.getObjServiceProviderDTO().setRentDetail1(contents);
						  String fileExt = getFileExtension(uploadedFile.getFileName());
					      String fileName ="Rent_Details."+fileExt;
						  downloadDocument(response, contents, fileName);
						  if(request.getParameter("isSpEdit")!=null)
		 					{
		 						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
		 						{
		 							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
		 						}
		 						
		 					}
						  
						  else if(request.getParameter("isRenewal")!=null)
		 					{
		 						if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
		 						{
		 							forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
		 						}
		 						
		 					}
						  
						  else
						  {
							  forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
						  }
		      	  }
		      	  else
		      	  {
		      		  String filename = request.getParameter("path").toString();

					   // set the http content type to "APPLICATION/OCTET-STREAM
					   response.setContentType("application/download");

					   // initialize the http content-disposition header to
					   // indicate a file attachment with the default filename
					   // "myFile.txt"
					   String fileName = objServiceProviderForm.getObjServiceProviderDTO().getRentDetailDoc();
					   //Filename=\"myFile.txt\"";
					   response.setHeader("Content-Disposition", "attachment; filename="
							+ URLEncoder.encode(fileName,"UTF-8"));

					   // transfer the file byte-by-byte to the response object
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
					   
					  if(request.getParameter("isSpEdit")!=null)
						{
							if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
							{
								forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
							}
							
						}
					  
					 else if(request.getParameter("isRenewal")!=null)
						{
							if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
							{
								forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
							}
							
						}
					  
					  else
					  {
					   
						 forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
					  }
		           
		      }
		    		 objServiceProviderDTO.setActionName("");
		        }
		        
	}

	//added by shruti----6th may 2014
	private void retrieval5(HttpServletRequest request,
			HttpServletResponse response,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO)
			throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
		        

        if(request.getParameter("path")!=null){

        	if (objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCerti()!=null)
      	  {
      		  FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCerti();
      		  byte contents[] = uploadedFile.getFileData();
				  objServiceProviderForm.getObjServiceProviderDTO().setHigherSecCerti1(contents);
				  String fileExt = getFileExtension(uploadedFile.getFileName());
			      String fileName ="Higher_Secondary_Certificate."+fileExt;
				  downloadDocument(response, contents, fileName);
				  if(request.getParameter("isSpEdit")!=null)
 					{
 						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
 						{
 							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
 						}
 						
 					}
				  
				  else if(request.getParameter("isRenewal")!=null)
 					{
 						if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
 						{
 							forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
 						}
 						
 					}
				  
				  else
				  {
					  forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				  }
      	  }
      	  else
      	  {
      		  String filename = request.getParameter("path").toString();

				   // set the http content type to "APPLICATION/OCTET-STREAM
				   response.setContentType("application/download");

				   // initialize the http content-disposition header to
				   // indicate a file attachment with the default filename
				   // "myFile.txt"
				   String fileName = objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCertiDoc();
				   //Filename=\"myFile.txt\"";
				   response.setHeader("Content-Disposition", "attachment; filename="
						+ URLEncoder.encode(fileName,"UTF-8"));

				   // transfer the file byte-by-byte to the response object
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
				   
				  if(request.getParameter("isSpEdit")!=null)
					{
						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
						{
							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
						}
						
					}
				  
				 else if(request.getParameter("isRenewal")!=null)
				{
					if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
					{
						forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
					}
					
				}
				  
				  else
				  {
				   
					 forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				  }
           
      }
    		 objServiceProviderDTO.setActionName("");
        }
	}

	private void retrieval6(HttpServletRequest request,
			HttpServletResponse response,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO)
			throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
		        

	 	  
        if(request.getParameter("path")!=null){
        	 
        	if (objServiceProviderForm.getObjServiceProviderDTO().getPhotoImg()!=null)
        	  {
        		  FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getPhotoImg();
        		  byte contents[] = uploadedFile.getFileData();
					  objServiceProviderForm.getObjServiceProviderDTO().setPhotoImg1(contents);
					  String fileExt = getFileExtension(uploadedFile.getFileName());
				      //String fileName ="Photo."+fileExt;
					 
					  String fileName=null;
					try {
						PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
						fileName = "Photo."+pr.getValue("sp_photo_extension_param");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  downloadDocument(response, contents, fileName);
					  
					if(request.getParameter("isSpEdit")!=null)
					{
						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
						{
							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
						}
						
					}
					
					else if(request.getParameter("isRenewal")!=null)
					{
						if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
						{
							forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
						}
						
					}
					
					else
					{
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
					}
        	  }
        	  else
        	  {
        		  String filename = request.getParameter("path").toString();

				   // set the http content type to "APPLICATION/OCTET-STREAM
				   response.setContentType("application/download");

				   // initialize the http content-disposition header to
				   // indicate a file attachment with the default filename
				   // "myFile.txt"
				   String fileName = objServiceProviderForm.getObjServiceProviderDTO().getPhotoImgDoc();
				   //Filename=\"myFile.txt\"";
				   response.setHeader("Content-Disposition", "attachment; filename="
						+ URLEncoder.encode(fileName,"UTF-8"));

				   // transfer the file byte-by-byte to the response object
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
				   
				if(request.getParameter("isSpEdit")!=null)
				{
					if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
					{
						forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
					}
					
				}
				
				else if(request.getParameter("isRenewal")!=null)
				{
					if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
					{
						forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
					}
					
				}
				
				else
				{
				   
					forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				}
             
        }
      		 objServiceProviderDTO.setActionName("");
        }
        
	}
	private void retrieval7(HttpServletRequest request,
			HttpServletResponse response,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO)
			throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
		        

	 	  

        if(request.getParameter("path")!=null){

        	if (objServiceProviderForm.getObjServiceProviderDTO().getCharacterCerti()!=null)
      	  {
      		  FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getCharacterCerti();
      		  byte contents[] = uploadedFile.getFileData();
				  objServiceProviderForm.getObjServiceProviderDTO().setCharacterCerti1(contents);
				  String fileExt = getFileExtension(uploadedFile.getFileName());
			      String fileName ="Character_Certificate."+fileExt;
				  downloadDocument(response, contents, fileName);
				  if(request.getParameter("isSpEdit")!=null)
 					{
 						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
 						{
 							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
 						}
 						
 					}
				  
				  else if(request.getParameter("isRenewal")!=null)
 					{
 						if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
 						{
 							forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
 						}
 						
 					}
				  
				  else
				  {
					  forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				  }
      	  }
      	  else
      	  {
      		  String filename = request.getParameter("path").toString();

				   // set the http content type to "APPLICATION/OCTET-STREAM
				   response.setContentType("application/download");

				   // initialize the http content-disposition header to
				   // indicate a file attachment with the default filename
				   // "myFile.txt"
				   String fileName = objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiDoc();
				   //Filename=\"myFile.txt\"";
				   response.setHeader("Content-Disposition", "attachment; filename="
						+ URLEncoder.encode(fileName,"UTF-8"));

				   // transfer the file byte-by-byte to the response object
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
				   
				  if(request.getParameter("isSpEdit")!=null)
					{
						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
						{
							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
						}
						
					}
				  
				 else if(request.getParameter("isRenewal")!=null)
				{
					if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
					{
						forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
					}
					
				}
				  
				  else
				  {
				   
					 forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				  }
           
      }
    		 objServiceProviderDTO.setActionName("");
        }
	}
	private void retrieval8(HttpServletRequest request,
			HttpServletResponse response,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO)
			throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
		        

	 	  

		  if(request.getParameter("path")!=null){

          	if (objServiceProviderForm.getObjServiceProviderDTO().getAgeProof()!=null)
          	  {
          		  FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getAgeProof();
          		  byte contents[] = uploadedFile.getFileData();
					  objServiceProviderForm.getObjServiceProviderDTO().setAgeProof1(contents);
					  String fileExt = getFileExtension(uploadedFile.getFileName());
				      String fileName ="Age_Proof."+fileExt;
					  downloadDocument(response, contents, fileName);
					if(request.getParameter("isSpEdit")!=null)
 					{
 						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
 						{
 							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
 						}
 						
 					}
					
					else if(request.getParameter("isRenewal")!=null)
 					{
 						if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
 						{
 							forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
 						}
 						
 					}
					
					else
					{
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
					}
          	  }
          	  else
          	  {
          		  String filename = request.getParameter("path").toString();

					   // set the http content type to "APPLICATION/OCTET-STREAM
					   response.setContentType("application/download");

					   // initialize the http content-disposition header to
					   // indicate a file attachment with the default filename
					   // "myFile.txt"
					   String fileName = objServiceProviderForm.getObjServiceProviderDTO().getAgeProofDoc();
					   //Filename=\"myFile.txt\"";
					   response.setHeader("Content-Disposition", "attachment; filename="
							+ URLEncoder.encode(fileName,"UTF-8"));

					   // transfer the file byte-by-byte to the response object
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
					   
					if(request.getParameter("isSpEdit")!=null)
					{
						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
						{
							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
						}
						
					}
					
					else if(request.getParameter("isRenewal")!=null)
					{
						if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
						{
							forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
						}
						
					}
					
					else
					{
					   
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
					}
               
          }
        		 objServiceProviderDTO.setActionName("");
          }
	}

	/*private void retrieval9(HttpServletRequest request,
			HttpServletResponse response,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO)
			throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
		        
	 	  
        if(request.getParameter("path")!=null){

        	if (objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCerti()!=null)
      	  {
      		  FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCerti();
      		  byte contents[] = uploadedFile.getFileData();
				  objServiceProviderForm.getObjServiceProviderDTO().setSolvencyCerti1(contents);
				  String fileExt = getFileExtension(uploadedFile.getFileName());
			      String fileName ="Solvency_Certificate."+fileExt;
				  downloadDocument(response, contents, fileName);
				  
				  if(request.getParameter("isSpEdit")!=null)
 					{
 						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
 						{
 							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
 						}
 						
 					}
				  
				  else if(request.getParameter("isRenewal")!=null)
 					{
 						if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
 						{
 							forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
 						}
 						
 					}
				  
				  else
				  {
					  
					  forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				  }
      	  }
      	  else
      	  {
      		  String filename = request.getParameter("path").toString();

				   // set the http content type to "APPLICATION/OCTET-STREAM
				   response.setContentType("application/download");

				   // initialize the http content-disposition header to
				   // indicate a file attachment with the default filename
				   // "myFile.txt"
				   String fileName = objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCertiDoc();
				   //Filename=\"myFile.txt\"";
				   response.setHeader("Content-Disposition", "attachment; filename="
						+ URLEncoder.encode(fileName,"UTF-8"));

				   // transfer the file byte-by-byte to the response object
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
				   
				  if(request.getParameter("isSpEdit")!=null)
					{
						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
						{
							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
						}
						
					}
				  
				 else if(request.getParameter("isRenewal")!=null)
				{
					if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
					{
						forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
					}
					
				}
				  
				  else
				  {
				   
					 forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				  }
           
      }
    		 objServiceProviderDTO.setActionName("");
        }
	}

	*/
	private void retrieval10(HttpServletRequest request,
			HttpServletResponse response,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO)
			throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
		        
	 	  
        
        if(request.getParameter("path")!=null){

        	if (objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCerti()!=null)
        	  {
        		  FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCerti();
        		  byte contents[] = uploadedFile.getFileData();
					  objServiceProviderForm.getObjServiceProviderDTO().setPoliceCaseCerti1(contents);
					  String fileExt = getFileExtension(uploadedFile.getFileName());
				      String fileName ="Affidavit_Police_Case."+fileExt;
					  downloadDocument(response, contents, fileName);
					if(request.getParameter("isSpEdit")!=null)
					{
						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
						{
							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
						}
						
					}
					
					else if(request.getParameter("isRenewal")!=null)
					{
						if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
						{
							forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
						}
						
					}
					
					else
					{
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
					}
        	  }
        	  else
        	  {
        		  String filename = request.getParameter("path").toString();

				   // set the http content type to "APPLICATION/OCTET-STREAM
				   response.setContentType("application/download");

				   // initialize the http content-disposition header to
				   // indicate a file attachment with the default filename
				   // "myFile.txt"
				   String fileName = objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCertiDoc();
				   //Filename=\"myFile.txt\"";
				   response.setHeader("Content-Disposition", "attachment; filename="
						+ URLEncoder.encode(fileName,"UTF-8"));

				   // transfer the file byte-by-byte to the response object
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
				   
				if(request.getParameter("isSpEdit")!=null)
				{
					if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
					{
						forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
					}
					
				}
				
				else if(request.getParameter("isRenewal")!=null)
				{
					if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
					{
						forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
					}
					
				}
				
				else
				{
				   
					forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				}
             
        }
      		 objServiceProviderDTO.setActionName("");
        }
        
	}
	private void retrieval11(HttpServletRequest request,
			HttpServletResponse response,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO)
			throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
	       if(request.getParameter("path")!=null){

           	if (objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDoc()!=null)
         	  {
         		  FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDoc();
         		  byte contents[] = uploadedFile.getFileData();
					  objServiceProviderForm.getObjServiceProviderDTO().setPossessionBusinessDoc1(contents);
					  String fileExt = getFileExtension(uploadedFile.getFileName());
				      String fileName ="Business_Premises."+fileExt;
					  downloadDocument(response, contents, fileName);
					  if(request.getParameter("isSpEdit")!=null)
    					{
    						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
    						{
    							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
    						}
    						
    					}
					  
					  else if(request.getParameter("isRenewal")!=null)
    					{
    						if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
    						{
    							forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
    						}
    						
    					}
					  
					  else
					  {
						  forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
					  }
         	  }
         	  else
         	  {
         		  String filename = request.getParameter("path").toString();

				   // set the http content type to "APPLICATION/OCTET-STREAM
				   response.setContentType("application/download");

				   // initialize the http content-disposition header to
				   // indicate a file attachment with the default filename
				   // "myFile.txt"
				   String fileName = objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDocDoc();
				   //Filename=\"myFile.txt\"";
				   response.setHeader("Content-Disposition", "attachment; filename="
						+ URLEncoder.encode(fileName,"UTF-8"));

				   // transfer the file byte-by-byte to the response object
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
				  if(request.getParameter("isSpEdit")!=null)
					{
						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
						{
							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
						}
						
					}
				  
				 else if(request.getParameter("isRenewal")!=null)
					{
						if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
						{
							forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
						}
						
					}
				  
				  else
				  {
				   
					 forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				  }
              
         }
       		 objServiceProviderDTO.setActionName("");
           }
	}

	private void retrieval12(HttpServletRequest request,
			HttpServletResponse response,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO)
			throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
	   	  
  	  
        if(request.getParameter("path")!=null){

        	if (objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCerti()!=null)
        	  {
        		  FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCerti();
        		  byte contents[] = uploadedFile.getFileData();
					  objServiceProviderForm.getObjServiceProviderDTO().setHigherEduCerti1(contents);
					  String fileExt = getFileExtension(uploadedFile.getFileName());
				      String fileName ="Education_Qualification."+fileExt;
					  downloadDocument(response, contents, fileName);
					if(request.getParameter("isSpEdit")!=null)
					{
						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
						{
							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
						}
						
					}
					
					else if(request.getParameter("isRenewal")!=null)
					{
						if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
						{
							forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
						}
						
					}
					
					else
					{
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
					}
        	  }
        	  else
        	  {
        		  String filename = request.getParameter("path").toString();

				   // set the http content type to "APPLICATION/OCTET-STREAM
				   response.setContentType("application/download");

				   // initialize the http content-disposition header to
				   // indicate a file attachment with the default filename
				   // "myFile.txt"
				   String fileName = objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCertiDoc();
				   //Filename=\"myFile.txt\"";
				   response.setHeader("Content-Disposition", "attachment; filename="
						+ URLEncoder.encode(fileName,"UTF-8"));

				   // transfer the file byte-by-byte to the response object
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
				   
				if(request.getParameter("isSpEdit")!=null)
				{
					if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
					{
						forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
					}
					
				}
				
				else if(request.getParameter("isRenewal")!=null)
				{
					if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
					{
						forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
					}
					
				}
				
				else
				{
				   
					forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				}
             
        }
      		 objServiceProviderDTO.setActionName("");
        }
	       }
	
	
	private void retrieval13(HttpServletRequest request,
			HttpServletResponse response,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO)
			throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
        
        if(request.getParameter("path")!=null){

        	if (objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware()!=null)
      	  {
      		  FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware();
      		  byte contents[] = uploadedFile.getFileData();
				  objServiceProviderForm.getObjServiceProviderDTO().setAffidavitHardware1(contents);
				  String fileExt = getFileExtension(uploadedFile.getFileName());
			      String fileName ="Hardware_Available."+fileExt;
				  downloadDocument(response, contents, fileName);
				  if(request.getParameter("isSpEdit")!=null)
 					{
 						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
 						{
 							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
 						}
 						
 					}
				  
				  else if(request.getParameter("isRenewal")!=null)
 					{
 						if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
 						{
 							forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
 						}
 						
 					}
				  
				  else
				  {
					  forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				  }
      	  }
      	  else
      	  {
      		  String filename = request.getParameter("path").toString();

				   // set the http content type to "APPLICATION/OCTET-STREAM
				   response.setContentType("application/download");

				   // initialize the http content-disposition header to
				   // indicate a file attachment with the default filename
				   // "myFile.txt"
				   String fileName = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardwareDoc();
				   //Filename=\"myFile.txt\"";
				   response.setHeader("Content-Disposition", "attachment; filename="
						+ URLEncoder.encode(fileName,"UTF-8"));

				   // transfer the file byte-by-byte to the response object
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
				  if(request.getParameter("isSpEdit")!=null)
					{
						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
						{
							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
						}
						
					}
				  
				 else if(request.getParameter("isRenewal")!=null)
				{
					if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
					{
						forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
					}
					
				}
				  
				  else
				  {
				   
					 forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				  }
           
      }
    		 objServiceProviderDTO.setActionName("");
        }
        
		
	}
	//end
	/**
	 * @param request
	 * @param objServiceProviderForm
	 * @param objServiceProviderDTO
	 */
	private void setUploadFile11(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
		
			ArrayList	errorList = new ArrayList();
			//FormFile uploadedFile = eForm.getFilePhoto2();
			FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCerti();
			byte contents[] = uploadedFile.getFileData();
			objServiceProviderForm.getObjServiceProviderDTO().setHigherEduCerti1(contents);
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add("Invalid file selection. Please try again.\n\n अवैध फ़ाइल चयन |पुन: प्रयास करें |");
			}
			/*String fileExt = getFileExtension(uploadedFile.getFileName());
			AuditInspectionRule rule = new AuditInspectionRule();
			rule.validateFileTypeSP(fileExt);*/
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			
			String photoSize = "(" + fileSize + "MB)";
			// new code 
			MIMECheck mimeCheck = new MIMECheck();
			String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
	        Boolean mime = mimeCheck.validateMIMEAndFileType(uploadedFile);  // common code to check mime type and validation.
	       
			if (!mime) {
				errorList.add("Invalid file type. Please select another file.\n\n अमान्य फ़ाइल प्रकार |अन्य फाइल चुनें |");
				request.setAttribute("errorsList11",errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE_SP) {
					errorList.add("File size is Greater than 500 KB. Please select another file.\n\n फ़ाइल का आकार 500 KB से अधिक है| अन्य फाइल चुनें|");
					request.setAttribute("errorsList11",errorList);
				} else {
					
					String docName = "Education_Qualification."+fileExt;
					objServiceProviderForm.getObjServiceProviderDTO().setHigherEduCertiDoc(uploadedFile.getFileName());
					
					if (((String)objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCertiPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getTwelfthFileSP().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCertiPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getTwelfthFileDR().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCertiPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getTwelfthFileRenewal().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;	
					}
					else
					{
					forwardJsp=ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
					}
					
					} 
				}
			objServiceProviderDTO.setActionName("");
}
		catch (Exception e) {
		e.printStackTrace();
}
	}
	
	

	//added by shruti---28 april 2014
	private void setUploadFile10(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			
			ArrayList	errorList = new ArrayList();
			//FormFile uploadedFile = eForm.getFilePhoto2();
			FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDoc();
			byte contents[] = uploadedFile.getFileData();
			objServiceProviderForm.getObjServiceProviderDTO().setPossessionBusinessDoc1(contents);
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add("Invalid file selection. Please try again.\n\n अवैध फ़ाइल चयन |पुन: प्रयास करें |");
			}
			/*String fileExt = getFileExtension(uploadedFile.getFileName());
			AuditInspectionRule rule = new AuditInspectionRule();
			rule.validateFileTypeSP(fileExt);*/
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String photoSize = "(" + fileSize + "MB)";
			// new code 
			MIMECheck mimeCheck = new MIMECheck();
			String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
	        Boolean mime = mimeCheck.validateMIMEAndFileType(uploadedFile);  // common code to check mime type and validation.
	       
			if (!mime) {
				errorList.add("Invalid file type. Please select another file.\n\n अमान्य फ़ाइल प्रकार |अन्य फाइल चुनें |");
				request.setAttribute("errorsList10",errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE_SP) {
					errorList.add("File size is Greater than 500 KB. Please select another file.\n\n फ़ाइल का आकार 500 KB से अधिक है| अन्य फाइल चुनें|");
					request.setAttribute("errorsList10",errorList);
				} else {
					
					String docName = "Business_Premises."+fileExt;
					objServiceProviderForm.getObjServiceProviderDTO().setPossessionBusinessDocDoc(uploadedFile.getFileName());
					
					if (((String)objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDocPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getEleventhFileSP().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDocPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getEleventhFileDR().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDocPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getEleventhFileRenewal().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;	
					}
					else
					{
					forwardJsp=ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
					}
					
					} 
				}
			objServiceProviderDTO.setActionName("");
	}
		catch (Exception e) {
		e.printStackTrace();
	}
	}

	//end
	/**
	 * @param objServiceProviderForm
	 * @param objServiceProviderDTO
	 */
	private void removeUploadFile3(ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			 PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			FormFile fname = (FormFile) objServiceProviderForm.getObjServiceProviderDTO().getRentDetail();
			//String docPath = pr.getValue("igrs_upload_path")+"/05/"+eForm.getMainTxnId()+"_"+"nonJud"+"_"+fname.getFileName();
			if (fname!=null)
			{
			if(("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/01"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setRentDetailDoc("");
			}
			else if (("2").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/02"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setRentDetailDoc("");
			}
			else if (("3").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/03"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setRentDetailDoc("");
			}
			else if (("4").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/04"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setRentDetailDoc("");
			}
			
			if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getFourthFileSP()))
			{
				forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getFourthFileDR()))
			{
				forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getFourthFileRenewal()))
			{
				forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			}
			else
			{
			
			forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
			}
			}
			
			else
			{
				String name = objServiceProviderForm.getObjServiceProviderDTO().getRentDetailDoc();
				String path = objServiceProviderForm.getObjServiceProviderDTO().getRentDetailPath();
				removeFile(name, path);
				objServiceProviderForm.getObjServiceProviderDTO().setRentDetailDoc("");
				
				if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getFourthFileSP()))
				{
					forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getFourthFileDR()))
				{
					forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getFourthFileRenewal()))
				{
					forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
				}
			}

			objServiceProviderDTO.setActionName("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void removeUploadFile4(ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			 PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			FormFile fname = (FormFile) objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCerti();
			//String docPath = "D:/Upload/05/"+eForm.getMainTxnId()+"_"+"nonJud"+"_"+fname.getFileName();
			if (fname!=null)
			{
			if(("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/01"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setHigherSecCertiDoc("");
			}
			else if (("2").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/02"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setHigherSecCertiDoc("");
			}
			else if (("3").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/03"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setHigherSecCertiDoc("");
			}
			else if (("4").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/04"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setHigherSecCertiDoc("");
			}
			
			if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getFifthFileSP()))
			{
				forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getFifthFileDR()))
			{
				forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getFifthFileRenewal()))
			{
				forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			}
			
			else
			{
			
			forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
			}
			}
			
			else
			{
				String name = objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCertiDoc();
				String path = objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCertiPath();
				removeFile(name, path);
				objServiceProviderForm.getObjServiceProviderDTO().setHigherSecCertiDoc("");
				
				if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getFifthFileSP()))
				{
					forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getFifthFileDR()))
				{
					forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getFifthFileRenewal()))
				{
					forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
				}
			}

			objServiceProviderDTO.setActionName("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void removeUploadFile5(ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			 PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

			FormFile fname = (FormFile) objServiceProviderForm.getObjServiceProviderDTO().getPhotoImg();
			//String docPath = "D:/Upload/05/"+eForm.getMainTxnId()+"_"+"nonJud"+"_"+fname.getFileName();
			if (fname!=null)
			{
			if(("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/01"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setPhotoImgDoc("");
			}
			else if (("2").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/02"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setPhotoImgDoc("");
			}
			else if (("3").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/03"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setPhotoImgDoc("");
			}
			else if (("4").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/04"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setPhotoImgDoc("");
			}
			
			if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSixthFileSP()))
			{
				forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSixthFileDR()))
			{
				forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSixthFileRenewal()))
			{
				forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			}
			
			else
			{
			
			forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
			}
			}
			
			else
			{
				String name = objServiceProviderForm.getObjServiceProviderDTO().getPhotoImgDoc();
				String path = objServiceProviderForm.getObjServiceProviderDTO().getPhotoImgPath();
				removeFile(name, path);
				objServiceProviderForm.getObjServiceProviderDTO().setPhotoImgDoc("");
				
				if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSixthFileSP()))
				{
					forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSixthFileDR()))
				{
					forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSixthFileRenewal()))
				{
					forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
				}
			}

			objServiceProviderDTO.setActionName("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void removeUploadFile6(ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			 PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

			FormFile fname = (FormFile) objServiceProviderForm.getObjServiceProviderDTO().getCharacterCerti();
			//String docPath = "D:/Upload/05/"+eForm.getMainTxnId()+"_"+"nonJud"+"_"+fname.getFileName();
			if (fname!=null)
			{
			if(("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/01"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiDoc("");
			}
			else if (("2").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/02"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiDoc("");
			}
			else if (("3").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/03"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiDoc("");
			}
			else if (("4").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/04"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiDoc("");
			}
			
			if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSeventhFileSP()))
			{
				forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSeventhFileDR()))
			{
				forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSeventhFileRenewal()))
			{
				forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			}
			else
			{
			
			forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
			}
			}
			
			else
			{
				String name = objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiDoc();
				String path = objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiPath();
				removeFile(name, path);
				objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiDoc("");
				
				if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSeventhFileSP()))
				{
					forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSeventhFileDR()))
				{
					forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSeventhFileRenewal()))
				{
					forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
				}
			}

			objServiceProviderDTO.setActionName("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void removeUploadFile7(ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			 PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

			FormFile fname = (FormFile) objServiceProviderForm.getObjServiceProviderDTO().getAgeProof();
			//String docPath = "D:/Upload/05/"+eForm.getMainTxnId()+"_"+"nonJud"+"_"+fname.getFileName();
			if (fname!=null)
			{
			if(("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/01"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setAgeProofDoc("");
			}
			else if (("2").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/02"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setAgeProofDoc("");
			}
			else if (("3").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/03"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setAgeProofDoc("");
			}
			else if (("4").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/04"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setAgeProofDoc("");
			}
			
			if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getEighthFileSP()))
			{
				forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getEighthFileDR()))
			{
				forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getEighthFileRenewal()))
			{
				forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			}
			else
			{
			
			forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
			}
			}
			
			else
			{
				String name = objServiceProviderForm.getObjServiceProviderDTO().getAgeProofDoc();
				String path = objServiceProviderForm.getObjServiceProviderDTO().getAgeProofPath();
				removeFile(name, path);
				objServiceProviderForm.getObjServiceProviderDTO().setAgeProofDoc("");
				
				if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getEighthFileSP()))
				{
					forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getEighthFileDR()))
				{
					forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getEighthFileRenewal()))
				{
					forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
				}
			}

			objServiceProviderDTO.setActionName("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*private void removeUploadFile8(ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			 PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

			FormFile fname = (FormFile) objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCerti();
			//String docPath = "D:/Upload/05/"+eForm.getMainTxnId()+"_"+"nonJud"+"_"+fname.getFileName();
			if (fname!=null)
			{
			if(objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("1"))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/01"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setSolvencyCertiDoc("");
			}
			else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("2"))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/02"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setSolvencyCertiDoc("");
			}
			else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("3"))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/03"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setSolvencyCertiDoc("");
			}
			else if (objServiceProviderForm.getObjServiceProviderDTO().getSpType().equalsIgnoreCase("4"))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/04"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setSolvencyCertiDoc("");
			}
			
			if (objServiceProviderForm.getObjServiceProviderDTO().getNinthFileSP().equalsIgnoreCase("1"))
			{
				forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (objServiceProviderForm.getObjServiceProviderDTO().getNinthFileDR().equalsIgnoreCase("1"))
			{
				forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (objServiceProviderForm.getObjServiceProviderDTO().getNinthFileRenewal().equalsIgnoreCase("1"))
			{
				forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			}
			
			else
			{
			
			forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
			}
			}
			
			else
			{
				String name = objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCertiDoc();
				String path = objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCertiPath();
				removeFile(name, path);
				objServiceProviderForm.getObjServiceProviderDTO().setSolvencyCertiDoc("");
				
				if (objServiceProviderForm.getObjServiceProviderDTO().getNinthFileSP().equalsIgnoreCase("1"))
				{
					forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (objServiceProviderForm.getObjServiceProviderDTO().getNinthFileDR().equalsIgnoreCase("1"))
				{
					forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (objServiceProviderForm.getObjServiceProviderDTO().getNinthFileRenewal().equalsIgnoreCase("1"))
				{
					forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
				}
			}

			objServiceProviderDTO.setActionName("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	private void removeUploadFile9(ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			 PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

			FormFile fname = (FormFile) objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCerti();
			//String docPath = "D:/Upload/05/"+eForm.getMainTxnId()+"_"+"nonJud"+"_"+fname.getFileName();
			if (fname!=null)
			{
			if(("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/01"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setPoliceCaseCertiDoc("");
			}
			else if (("2").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/02"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setPoliceCaseCertiDoc("");
			}
			else if (("3").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/03"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setPoliceCaseCertiDoc("");
			}
			else if (("4").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/04"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setPoliceCaseCertiDoc("");
			}
			
			if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getTenthFileSP()))
			{
				forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getTenthFileDR()))
			{
				forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getTenthFileRenewal()))
			{
				forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			}
			
			else
			{
			
			forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
			}
			}
			
			else
			{
				String name = objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCertiDoc();
				String path = objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCertiPath();
				removeFile(name, path);
				objServiceProviderForm.getObjServiceProviderDTO().setPoliceCaseCertiDoc("");
				
				if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getTenthFileSP()))
				{
					forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getTenthFileDR()))
				{
					forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getTenthFileRenewal()))
				{
					forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
				}
			}

			objServiceProviderDTO.setActionName("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void removeUploadFile10(ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			 PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

			FormFile fname = (FormFile) objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDoc();
			//String docPath = "D:/Upload/05/"+eForm.getMainTxnId()+"_"+"nonJud"+"_"+fname.getFileName();
			if (fname!=null)
			{
			if(("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/01"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setPossessionBusinessDocDoc("");
			}
			else if (("2").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/02"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setPossessionBusinessDocDoc("");
			}
			else if (("3").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/03"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setPossessionBusinessDocDoc("");
			}
			else if (("4").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/04"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setPossessionBusinessDocDoc("");
			}
			
			if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getEleventhFileSP()))
			{
				forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getEleventhFileDR()))
			{
				forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getEleventhFileRenewal()))
			{
				forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			}
			else
			{
			
			forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
			}
			}
			
			else
			{
				String name = objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDocDoc();
				String path = objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDocPath();
				removeFile(name, path);
				objServiceProviderForm.getObjServiceProviderDTO().setPossessionBusinessDocDoc("");
				
				if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getEleventhFileSP()))
				{
					forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getEleventhFileDR()))
				{
					forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getEleventhFileRenewal()))
				{
					forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
				}
			}

			objServiceProviderDTO.setActionName("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void removeUploadFile11(ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			 PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

			FormFile fname = (FormFile) objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCerti();
			//String docPath = "D:/Upload/05/"+eForm.getMainTxnId()+"_"+"nonJud"+"_"+fname.getFileName();
			if (fname!=null)
			{
			if(("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/01"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setHigherEduCertiDoc("");
			}
			else if (("2").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/02"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setHigherEduCertiDoc("");
			}
			else if (("3").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/03"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setHigherEduCertiDoc("");
			}
			else if (("4").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/04"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setHigherEduCertiDoc("");
			}
			
			if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getTwelfthFileSP()))
			{
				forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getTwelfthFileDR()))
			{
				forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getTwelfthFileRenewal()))
			{
				forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			}
			else
			{
			
			forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
			}
			}
			
			else
			{
				String name = objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCertiDoc();
				String path = objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCertiPath();
				removeFile(name, path);
				objServiceProviderForm.getObjServiceProviderDTO().setHigherEduCertiDoc("");
				
				if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getTwelfthFileSP()))
				{
					forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getTwelfthFileDR()))
				{
					forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getTwelfthFileRenewal()))
				{
					forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
				}
			}

			objServiceProviderDTO.setActionName("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void removeUploadFile12(ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			 PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

			FormFile fname = (FormFile) objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware();
			//String docPath = "D:/Upload/05/"+eForm.getMainTxnId()+"_"+"nonJud"+"_"+fname.getFileName();
			if (fname!=null)
			{
			
			if(("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/01"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setAffidavitHardwareDoc("");
			}
			else if (("2").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/02"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setAffidavitHardwareDoc("");
			}
			else if (("3").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/03"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setAffidavitHardwareDoc("");
			}
			else if (("4").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/04"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setAffidavitHardwareDoc("");
			}
			
			if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getThirteenthFileSP()))
			{
				forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getThirteenthFileDR()))
			{
				forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getThirteenthFileRenewal()))
			{
				forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			}
			else
			{
			
			forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
			}
			
			}
			else
			{
				String name = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardwareDoc();
				String path = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardwarePath();
				removeFile(name, path);
				objServiceProviderForm.getObjServiceProviderDTO().setAffidavitHardwareDoc("");
				
				if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getThirteenthFileSP()))
				{
					forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getThirteenthFileDR()))
				{
					forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getThirteenthFileRenewal()))
				{
					forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
				}
			}

			objServiceProviderDTO.setActionName("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param objServiceProviderForm
	 * @param objServiceProviderDTO
	 */
	private void removeUploadFile2(ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			 PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			FormFile fname = (FormFile) objServiceProviderForm.getObjServiceProviderDTO().getDobCerti();
			if (fname!=null)
			{
			if(("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/01"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setDobCertiDoc("");
			}
			else if (("2").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/02"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setDobCertiDoc("");
			}
			else if (("3").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/03"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setDobCertiDoc("");
			}
			else if (("4").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/04"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setDobCertiDoc("");
			}
			
			if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getThirdFileSP()))
			{
				forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getThirdFileDR()))
			{
				forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getThirdFileRenewal()))
			{
				forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			}
			else
			{
			
			forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
			}
			}
			
			else
			{
				String name = objServiceProviderForm.getObjServiceProviderDTO().getDobCertiDoc();
				String path = objServiceProviderForm.getObjServiceProviderDTO().getDobCertiPath();
				removeFile(name, path);
				objServiceProviderForm.getObjServiceProviderDTO().setDobCertiDoc("");
				
				if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getThirdFileSP()))
				{
					forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getThirdFileDR()))
				{
					forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getThirdFileRenewal()))
				{
					forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
				}
			}

			objServiceProviderDTO.setActionName("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param objServiceProviderForm
	 * @param objServiceProviderBO
	 */
	//modified by shruti---session added
	private void nextPageOfAppFormForRenewal(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderBO objServiceProviderBO,HttpSession session) {
		 String lang=(String)session.getAttribute("languageLocale");
		try
		  {
			  objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminal(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiGaz(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setDobCerti(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setRentDetail(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setHigherSecCerti(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setPhotoImg(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setCharacterCerti(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setAgeProof(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setSolvencyCerti(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setPoliceCaseCerti(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setPossessionBusinessDoc(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setHigherEduCerti(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setAffidavitHardware(null);
			  //added by shruti 2nd april 2014
			  objServiceProviderForm.getObjServiceProviderDTO().setPancardUpload(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setForm60Upload(null);
			   //end
			  
			  objServiceProviderForm.getObjServiceProviderDTO().setDistrictList(objServiceProviderBO.getDistrict(lang));
			  //for getting latest comments of DR
			  
			  objServiceProviderForm = objServiceProviderBO.getDRComments(objServiceProviderForm);
			  String spType = objServiceProviderForm.getObjServiceProviderDTO().getSpType();
			  
			  if (spType.equalsIgnoreCase("1"))
			  {
				  	objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(1);
				  
				  	objServiceProviderForm =(ServiceProviderForm) objServiceProviderBO.getspDetlsForRenewal(objServiceProviderForm);
			  }
			  
			  if (spType.equalsIgnoreCase("2") || spType.equalsIgnoreCase("3") || spType.equalsIgnoreCase("4"))
			  {
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpBank(1);
			  objServiceProviderForm = (ServiceProviderForm)objServiceProviderBO.getspDetlsBankForRenewal(objServiceProviderForm);
			  }
			  
			  if (spType.equalsIgnoreCase("1")||spType.equalsIgnoreCase("2")||spType.equalsIgnoreCase("3")||spType.equalsIgnoreCase("4"))
			  {
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(1);
			  objServiceProviderForm = (ServiceProviderForm)objServiceProviderBO.getspDetlsAllForRenewal(objServiceProviderForm);
			  
			  String disttName = (String)objServiceProviderForm.getObjServiceProviderDTO().getDistrict();
			  String disttId =(String)objServiceProviderBO.getdisttId(objServiceProviderForm);
			 
			  ArrayList detlsTehsil = objServiceProviderBO.getPlaceDetls(objServiceProviderForm);
			  String tehsilId = (String)objServiceProviderForm.getObjServiceProviderDTO().getTehsil();
			  //String wardId = (String)objServiceProviderForm.getObjServiceProviderDTO().getWard();
			  String areaId=(String)objServiceProviderBO.getAreaId(objServiceProviderForm);
			  String subareaId=(String)objServiceProviderBO.getSubAreaId(objServiceProviderForm);
			  String wardId=(String)objServiceProviderBO.getWardId(objServiceProviderForm,subareaId,tehsilId);
			  String mohallaId="";
			  if(wardId!=null && !wardId.equals(""))
			  {
			  mohallaId=(String)objServiceProviderBO.getMohallaId(objServiceProviderForm,wardId.split("~")[1]);
			  }
			  
			  objServiceProviderForm.getObjServiceProviderDTO().setDistrict(disttId);
			  
			  objServiceProviderForm.getObjServiceProviderDTO().setTehsil(tehsilId);
			  objServiceProviderForm.getObjServiceProviderDTO().setAreaId(areaId);
			  objServiceProviderForm.getObjServiceProviderDTO().setSubAreaId(subareaId);
			  objServiceProviderForm.getObjServiceProviderDTO().setWardPatwariId(wardId);
			  objServiceProviderForm.getObjServiceProviderDTO().setColonyId(mohallaId);
			  
			  //objServiceProviderForm.getObjServiceProviderDTO().setTehsilList(objServiceProviderBO.getTehsil(disttId,lang));
			 // objServiceProviderForm.getObjServiceProviderDTO().setWardList(objServiceProviderBO.getWardPatwari(tehsilId,lang));
			 // objServiceProviderForm.getObjServiceProviderDTO().setMohallaList(objServiceProviderBO.getMohallaVillage(wardId,lang));
			  
			  objServiceProviderForm.getObjServiceProviderDTO().setAreaList(objServiceProviderBO.getArea(lang));
			  
			  if("2".equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getAreaId()))
				{	
				  objServiceProviderForm.getObjServiceProviderDTO().setSubAreaList(objServiceProviderBO.getSubArea(lang,objServiceProviderForm.getObjServiceProviderDTO().getAreaId(),objServiceProviderForm.getObjServiceProviderDTO().getTehsil(),"Y"));
				}
				else
				{
					 objServiceProviderForm.getObjServiceProviderDTO().setSubAreaList(objServiceProviderBO.getSubArea(lang,objServiceProviderForm.getObjServiceProviderDTO().getAreaId(),objServiceProviderForm.getObjServiceProviderDTO().getTehsil(),"N"));

				}
			  objServiceProviderForm.getObjServiceProviderDTO().setTehsilList(objServiceProviderBO.getTehsil(disttId,lang));
			  objServiceProviderForm.getObjServiceProviderDTO().setWardPatwariList(objServiceProviderBO.getWardPatwari(lang,subareaId,tehsilId));
			  objServiceProviderForm.getObjServiceProviderDTO().setColonyList(objServiceProviderBO.getColonyName(lang,wardId));
			  
			  }
			  
			  objServiceProviderForm = (ServiceProviderForm) objServiceProviderBO.getspDocDetlsDR(objServiceProviderForm);
			  
			  //start | added by santosh to clear form on license renewal
			  if((null==objServiceProviderForm.getObjServiceProviderDTO().getPanCard() 
					  || ""==objServiceProviderForm.getObjServiceProviderDTO().getPanCard())
					  && (null==objServiceProviderForm.getObjServiceProviderDTO().getForm60() 
							  || ""==objServiceProviderForm.getObjServiceProviderDTO().getForm60())){
				  objServiceProviderForm.getObjServiceProviderDTO().setPanCard("");
				  objServiceProviderForm.getObjServiceProviderDTO().setPancardDoc("");
				  objServiceProviderForm.getObjServiceProviderDTO().setPancardFlag("");
				  objServiceProviderForm.setPanCardFlag("");
				  
				  objServiceProviderForm.getObjServiceProviderDTO().setForm60("");
				  objServiceProviderForm.getObjServiceProviderDTO().setForm60Doc("");
				  objServiceProviderForm.getObjServiceProviderDTO().setForm60Flag("");
				  objServiceProviderForm.setForm60Flag("");
		        }
			  
			  //end | added by santosh to clear form on license renewal
			  
			  forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
		  
		  }
		  catch (Exception e)
		  {
			  e.printStackTrace();
		  }
	}
//modified by shruti---http session added to declaration
	private ServiceProviderForm cancelLicenseComplete(
			HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderBO objServiceProviderBO,
			ServiceProviderDTO objServiceProviderDTO,HttpSession session) throws Exception {
		if (request.getParameter("modName") != null) 
		  {
				if (request.getParameter("modName").equalsIgnoreCase("ServiceProvider") && request.getParameter("fnName").equalsIgnoreCase("cancelLicense"))
				{
					cancelLicensePage(objServiceProviderForm,objServiceProviderDTO);
				}
			}
		  if (request.getParameter("modName") != null) 
		  {
				if (request.getParameter("modName").equalsIgnoreCase("ServiceProvider") && request.getParameter("fnName").equalsIgnoreCase("cancelLicenseDashboard"))
				{
			  
			  ServiceProviderDTO spDTO = objServiceProviderForm.getObjServiceProviderDTO();
			  //modified by shruti--
			  String lang=(String)session.getAttribute("languageLocale");
			  ArrayList <ServiceProviderDTO> spList=objServiceProviderBO.cancelLicenseDashboard(spDTO,lang); 
			  //end
			  request.setAttribute("cancelList", spList);
			  forwardJsp = ServiceProviderConstant.CANCEL_LICENSE_DASHBOARD;
			  objServiceProviderDTO.setActionName("");
			}
		  }
		
		  if (request.getParameter("modName") != null) 
		  {
				if (request.getParameter("modName").equalsIgnoreCase("ServiceProvider") && request.getParameter("fnName").equalsIgnoreCase("cancelLicenseUpdate"))
				{
					insertCommentCancel(objServiceProviderForm,
							objServiceProviderBO, objServiceProviderDTO);
					
					
				}
		  }
		
		  if (request.getParameter("licenseId") != null)
		  {
			  objServiceProviderForm = cancelLicenseView(request,
					objServiceProviderForm, objServiceProviderBO,
					objServiceProviderDTO,session);
			  
		  }
		return objServiceProviderForm;
	}

	private void cancelLicensePage(ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		
		objServiceProviderForm.getObjServiceProviderDTO().setIschecked(0);
		objServiceProviderForm.getObjServiceProviderDTO().setRadioSelect("");
		
		logger.debug("------------------->inside cancelLicensePage action");
		
		objServiceProviderForm.getObjServiceProviderDTO().setSearchType("");
		objServiceProviderForm.getObjServiceProviderDTO().setLicenseId("");
		objServiceProviderForm.getObjServiceProviderDTO().setSearchName("");
		objServiceProviderForm.getObjServiceProviderDTO().setToCreatedDate("");
		objServiceProviderForm.getObjServiceProviderDTO().setFromCreatedDate("");
		
		forwardJsp = ServiceProviderConstant.CANCEL_LICENSE_VIEW;
		
		logger.info("--------------->showing cancel License screen");
		objServiceProviderDTO.setActionName("");
	}

	/**
	 * @param response
	 * @param objServiceProviderForm
	 * @param objServiceProviderBO
	 * @throws DocumentException
	 * @throws BadElementException
	 * @throws IOException
	 * @throws Exception
	 */
	private void pdfOfSpLicense(HttpServletResponse response,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderBO objServiceProviderBO,
			ServiceProviderDTO objServiceProviderDTO,HttpSession session) throws DocumentException,
			BadElementException, IOException, Exception {
		
		try 
		{
			 String lang=(String)session.getAttribute("languageLocale");
			 //added by shruti---29 apri 2014
			 String userId=(String)session.getAttribute("UserId");
			 objServiceProviderForm.setUserId(userId);
			 
		boolean updateDate = false;
		updateDate = objServiceProviderBO.updateLicenseDate(objServiceProviderForm);
		
		ArrayList pdfDetls= objServiceProviderBO.getPdfDetlsForLicense(objServiceProviderForm,lang);
		String todaysDate = objServiceProviderBO.getTodaysDate();
		
		if (pdfDetls.size()>0)
		{
			//method for pdf generation
			pdfGenerationLogic(response, objServiceProviderForm, todaysDate,session);
			//end of method for pdf generation
			ArrayList licenseDetails = objServiceProviderBO.getLicenseDetails(objServiceProviderForm);
			logger.debug("Size of ArrayList licenseDetails:- "+licenseDetails.size());
		  
			if (licenseDetails.size()>0)
			{
				boolean updatePreviousStatus = false;
				updatePreviousStatus = objServiceProviderBO.updatePreviousStatusOfLicenseIssued(objServiceProviderForm);
				logger.debug("deactivated previous status:- "+updatePreviousStatus);
			}
		  
			boolean update = false;
			update = objServiceProviderBO.updateStatusToLicenseIssued(objServiceProviderForm);
			logger.debug("License copy is generated in pdf");
		
		}
		
		else
		{
			ArrayList pdfDetlsForReprint= objServiceProviderBO.getPdfDetlsForLicenseReprint(objServiceProviderForm,lang);
			//method for pdf generation
			pdfGenerationLogic(response, objServiceProviderForm, todaysDate,session);
			//end of method for pdf generation
			
		}
		
		objServiceProviderDTO.setActionName("");
		
		}
		
		catch (Exception e)
		{
			forwardJsp=ServiceProviderConstant.HOME_PAGE;
			e.printStackTrace();
		}
		
		finally {
			 try{	    
				 //modified by shruti---4 aug 2014
				 //forwardJsp=ServiceProviderConstant.HOME_PAGE;
			 }
			 catch (Exception e) {
			 logger.error("Exception at serviceProviderAction in action  " + e);
			 }		
	       }
		
	}

	/**
	 * @param response
	 * @param objServiceProviderForm
	 * @param todaysDate
	 * @throws DocumentException
	 * @throws BadElementException
	 * @throws IOException
	 */
	
	private void pdfGenerationLogic(HttpServletResponse response,
			ServiceProviderForm objServiceProviderForm, String todaysDate,HttpSession session)
			throws DocumentException, BadElementException, IOException {
		
		System.out.println("inside generate pdf method");
		ServiceProviderBO objServiceProviderBO = new ServiceProviderBO();
		
		//added by shruti----14 march 2014---------DMS
		PropertiesFileReader pr;
		try {
		pr = PropertiesFileReader.getInstance("resources.igrs");
		String lang=(String)session.getAttribute("languageLocale");
		DocumentOperations docOperations = new DocumentOperations();
        ODServerDetails connDetails = new ODServerDetails();
        Dataclass metaDataInfo = new Dataclass();
        connDetails.setAppServerIp(pr.getValue("AppServerIp"));
        connDetails.setCabinetName(pr.getValue("CabinetName"));
        connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
        connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
        connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
        connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
        metaDataInfo.setUniqueNo(objServiceProviderForm.getObjServiceProviderDTO().getLicenseNumber().toString());
        metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
        ////modified by shruti---20 march 2014--to maintain different folders for new/renewed license
        if(objServiceProviderForm.getObjServiceProviderDTO().getNewOrRenewalFlag().toString().equalsIgnoreCase("New")) 
        {
        metaDataInfo.setType("Granted");
        }
        else
        {
        metaDataInfo.setType("Renewed");
        }
        BurningImageAndText burnObj = new BurningImageAndText(pr.getValue("dms_hindi_font_path"),pr.getValue("dms_english_font_path"));
        ServiceProvider spObj = new ServiceProvider();
        
        /** For English **/
        
        if("".equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getPhotoLoc()))
        {
        	objServiceProviderForm.getObjServiceProviderDTO().setPhotoLoc(null);
        }
        if("".equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSignLoc()))
        {
        	objServiceProviderForm.getObjServiceProviderDTO().setSignLoc(null);
        }
        spObj.setSpPhotoLocation(objServiceProviderForm.getObjServiceProviderDTO().getPhotoLoc());
        spObj.setSpLicenseNo(objServiceProviderForm.getObjServiceProviderDTO().getLicenseNumber().toString());
        spObj.setStampVenderName(objServiceProviderForm.getObjServiceProviderDTO().getSpName());
        spObj.setStampVenderFatherName(objServiceProviderForm.getObjServiceProviderDTO().getFatherName().toString());
       /* spObj.setStampVenderAddress(objServiceProviderForm.getObjServiceProviderDTO().getAddressSp().toString()+" , "+objServiceProviderForm.getObjServiceProviderDTO().getDistrict().toString()+" , "+objServiceProviderForm.getObjServiceProviderDTO().getTehsil()+" , "+objServiceProviderForm.getObjServiceProviderDTO().getWard().toString()+" , "+objServiceProviderForm.getObjServiceProviderDTO().getMohalla().toString());*/
        spObj.setStampVenderAddress(objServiceProviderForm.getObjServiceProviderDTO().getAddress().toString());
        spObj.setStampVenderBusinessPlace(objServiceProviderForm.getObjServiceProviderDTO().getAddressSp().toString());
        spObj.setStampVenderBusinessTown(objServiceProviderForm.getObjServiceProviderDTO().getMohalla().toString());
        spObj.setStampVenderBusinessTehsil(objServiceProviderForm.getObjServiceProviderDTO().getTehsil().toString());
        spObj.setStampVenderBusinessDistrict(objServiceProviderForm.getObjServiceProviderDTO().getDistrict().toString());
        spObj.setSpLicenseDuration(objServiceProviderForm.getObjServiceProviderDTO().getYear().toString()+" "+objServiceProviderForm.getObjServiceProviderDTO().getMonth().toString()+" "+objServiceProviderForm.getObjServiceProviderDTO().getDays().toString());
        spObj.setSpLicensePeriodSartDt(objServiceProviderForm.getObjServiceProviderDTO().getDurationFrom().toString());
        spObj.setSpLicensePeriodEndDt(objServiceProviderForm.getObjServiceProviderDTO().getDurationTo().toString());
        spObj.setDisRegSigLocation(objServiceProviderForm.getObjServiceProviderDTO().getSignLoc());
        spObj.setDisRegPlace(objServiceProviderForm.getObjServiceProviderDTO().getDistrict().toString());
        spObj.setSpIssueDate(todaysDate);
        spObj.setDisRegSealContent(null);
        spObj.setDisRegName(objServiceProviderForm.getObjServiceProviderDTO().getDrName());
        //spObj.setDisRegDesignation(objServiceProviderForm.getObjServiceProviderDTO().getDrDesignation());
        if("en".equalsIgnoreCase(lang))
        {
        spObj.setDisRegDesignation("Collector of Stamps");
        }
        else
        {
        spObj.setDisRegDesignation("कलेक्टर आफ स्टेम्पस");
        }
        System.out.println("inside sp action after setting all dms parameters");
        logger.debug("inside sp action after setting all dms parameters");
        logger.info("inside sp action after setting all dms parameters");
        int result = -1;
        //ADDED BY SHRUTI to create folder for storing sp pdf generated after burning
        File dir;
		dir = new File(pr.getValue("igrs_upload_path")+"/IGRS/"+objServiceProviderForm.getObjServiceProviderDTO().getLicenseNumber().toString());
		if (dir.exists()) {
			logger.info("file already exists deleting....");
			dir.delete();
		}
		else
			dir.mkdirs();
		//END
		 
        String outputPath =pr.getValue("igrs_upload_path")+"/IGRS/"+objServiceProviderForm.getObjServiceProviderDTO().getLicenseNumber().toString();
        System.out.println("output path in sp module>>>>>>"+outputPath);
        logger.debug("output path in sp module>>>>>>"+outputPath);
        logger.info("output path in sp module>>>>>>"+outputPath);
        String dmsFolderName = "IGRS";
  
        String headerimg=getServlet().getServletContext().getRealPath("")+ "/images/header img.jpg";
		//String lang=(String)session.getAttribute("languageLocale");
        if (null==metaDataInfo.getUniqueNo()) {
			throw new Exception();
		}
		if (metaDataInfo.getUniqueNo().equals("")) {
			throw new Exception();
		}
        if("en".equalsIgnoreCase(lang))
        {
        result = burnObj.generateSPCert(connDetails, metaDataInfo, outputPath,headerimg, "English", spObj, "Granted/Renewed", "Service_Provider_license1.pdf", dmsFolderName);
        }
        else
        {
        result = burnObj.generateSPCert(connDetails, metaDataInfo, outputPath,headerimg, "Hindi", spObj, "दी /नए सिरे से दी", "Service_Provider_license1.pdf", dmsFolderName);	
        }
        System.out.println("result code returned by sp generate certificate method>>"+result);
        
            /** Search  & Download Document on basis of metadata & Name */
            ArrayList<DocumentDetails> docList = docOperations.searchDocs(connDetails, metaDataInfo, null);
            DocumentDetails docDetails = new DocumentDetails();
            int result1=-1;
            String guid=GUIDGenerator.getGUID();
            logger.info("GUID from sp module>>>>>>>>>>>>>>>>"+guid);
            logger.debug("GUID from sp module>>>>>>>>>>>>>>>>"+guid);
	        String SPLicPath = ServiceProviderConstant.SP_DOWNLOAD_PATH_LINUX+guid+File.separator+"IGRS";
			System.out.println("SP license path from SP action"+SPLicPath);
			logger.info("SP license path from SP action"+SPLicPath);
			logger.debug("SP license path from SP action"+SPLicPath);
	        File output;
			output = new File(SPLicPath.toString());
			if (output.exists()) {
				logger.info("file already exists deleting....");
				output.delete();
			}
			if (output.getParentFile().exists() == false) {
				logger.info("Parent not found creating directory....");
				output.getParentFile().mkdirs();
			}
			
			//download uploaded doc in dms for applying digital signature
			 result1 = docOperations.downloadDocument(connDetails, metaDataInfo,SPLicPath,"Service_Provider_license1.pdf");
			 System.out.println(" result returned from download method of dms"+result1);
			 String userId = (String) session.getAttribute("UserId");
			 
			 //changes for digital signature integration in SP Module
		      try
		      {
		    	  String signFlag=pr.getValue("digital_sign_flag");
		    	  logger.debug("sign flag from SP>>>>>>>>>>>"+signFlag);
		         if(signFlag.equalsIgnoreCase("Y"))
		         {
		        	   logger.info("inside sign flag yes>>>>>>>>");
		        	 new DocumentService().sign(SPLicPath+File.separator+ "Service_Provider_license1.pdf", SPLicPath+File.separator+ "Service_Provider_license.pdf", objServiceProviderBO.getSubjectName(userId).toString(),200,100,300,200);  	       
		           logger.debug("after call for document servcie in SP>>>>>>>>>>>>"); 
		         }
		        else
		        {
		        	logger.debug("inside else code of digital signature>>>>>>>");
		              ExecutorService executor = Executors.newFixedThreadPool(1);
		              List<Callable<Object>> todo = new ArrayList<Callable<Object>>();
		              todo.add(Executors.callable(new DigtalSignThread(SPLicPath+File.separator+ "Service_Provider_license1.pdf", SPLicPath+File.separator+ "Service_Provider_license.pdf", "100", "100", "200", "200",objServiceProviderBO.getSubjectName(userId).toString()))); 
		              logger.debug("code for executor service in SP executed properly,might b error in path passed");
		              executor.invokeAll(todo,1, TimeUnit.MINUTES); // Timeout      
		              executor.shutdown();       
		        }
		         //end
		         
		         //preovious code commented
		         
		    	  //new DocumentService().sign(SPLicPath+File.separator+ "Service_Provider_license1.pdf", SPLicPath+File.separator+ "Service_Provider_license.pdf", objServiceProviderBO.getSubjectName(userId).toString(),200,100,300,200);  	
		      }
		      catch (Exception e) {
			logger.debug(e.getMessage());
			}
		      System.out.println("after digital signature in sp module>>>>>>>>>>");
			logger.info("after digital signature in sp module>>>>>>>>>>");
			logger.debug("after digital signature in sp module>>>>>>>>>>");
			 metaDataInfo.setUniqueNo(objServiceProviderForm.getObjServiceProviderDTO().getLicenseNumber().toString());
			 System.out.println("Unique number from SP Module>>>>"+objServiceProviderForm.getObjServiceProviderDTO().getLicenseNumber().toString());
			 logger.info("Unique number from SP Module>>>>"+objServiceProviderForm.getObjServiceProviderDTO().getLicenseNumber().toString());
			 logger.debug("Unique number from SP Module>>>>"+objServiceProviderForm.getObjServiceProviderDTO().getLicenseNumber().toString());
			 metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			 metaDataInfo.setType("Granted");
			 String	fileName = "Service_Provider_license.pdf";
			 String path = SPLicPath+File.separator+fileName;
			 logger.debug("final path from SP action^^^^^^^^^^^^^^^^^^^^^^^^^^^"+path);
			 logger.info("final path from SP action^^^^^^^^^^^^^^^^^^^^^^^^^^^"+path);
			 System.out.println("finlal path from SP action^^^^^^^^^^^^^^^^^^^^^^^^^^^"+path);
			 File file = new File(path);
			 if (null==metaDataInfo.getUniqueNo()) {
					throw new Exception();
				}
				if (metaDataInfo.getUniqueNo().equals("")) {
					throw new Exception();
				}				
			 //upload doc in dms with digital signature-always use IGRS/.. path for uploading otherwise an exception is thrown
			String docId = docOperations.uploadDocument(connDetails, file, "IGRS", true, metaDataInfo);
			logger.debug("document has been uploaded successfully after digital signature");
			logger.debug("doc Id---------result code for upload of doc after digital sign "+docId);
			 //end
			 
			//download doc for optical watermark\
			if (null==metaDataInfo.getUniqueNo()) {
				throw new Exception();
			}
			if (metaDataInfo.getUniqueNo().equals("")) {
				throw new Exception();
			}
			int result2=-1;
			result2 = docOperations.downloadDocument(connDetails, metaDataInfo,SPLicPath,"Service_Provider_license.pdf");
			  
			System.out.println("result code of download method from dms for downlaoding digitally signed doc>>>>>"+result2);
			 objServiceProviderForm.getObjServiceProviderDTO().setOwmFlag("Y");
			 System.out.println("owm flag from SP action>>>>>"+"SET");
			 logger.info("owm flag from SP action>>>>>"+"SET");
			 logger.debug("owm flag from SP action>>>>>"+"SET");
			 objServiceProviderForm.getObjServiceProviderDTO().setOwmFile(SPLicPath+File.separator+"Service_Provider_license.pdf");
			 System.out.println("setting owm file path>>>>>>>>>"+ objServiceProviderForm.getObjServiceProviderDTO().getOwmFile());
			 logger.info("setting owm file path>>>>>>>>>"+ objServiceProviderForm.getObjServiceProviderDTO().getOwmFile());
			 logger.debug("setting owm file path>>>>>>>>>"+ objServiceProviderForm.getObjServiceProviderDTO().getOwmFile());
			 objServiceProviderForm.getObjServiceProviderDTO().setLicNo((String)objServiceProviderForm.getObjServiceProviderDTO().getLicenseNumber());
		
            forwardJsp=ServiceProviderConstant.VIEW_AFTER_LICENSE_FEE_PAID;
          
            
             
		} 
        catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /////end of dms
	}

	/**
	 * @param objServiceProviderForm
	 * @param objServiceProviderBO
	 * @param objServiceProviderDTO
	 * @throws Exception
	 */
	private void insertCommentCancel(
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderBO objServiceProviderBO,
			ServiceProviderDTO objServiceProviderDTO) throws Exception {
		String licenseId=objServiceProviderForm.getObjServiceProviderDTO().getLicenseId();
		objServiceProviderForm.getObjServiceProviderDTO().setRequestNo(objServiceProviderBO.getRequestNumber(licenseId));
		boolean flag= objServiceProviderBO.insertCommentsCancel(objServiceProviderForm);
		if(flag)
		{
			 objServiceProviderForm.getObjServiceProviderDTO().setIsDRApproved(0);
			 objServiceProviderForm.getObjServiceProviderDTO().setIsCallForAddInfo(0);
			 objServiceProviderForm.getObjServiceProviderDTO().setIsReject(0);
			 objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPV(0);
			 objServiceProviderForm.getObjServiceProviderDTO().setIsDIG(0);
			 objServiceProviderForm.getObjServiceProviderDTO().setIsReactivateByDR(0);
			 objServiceProviderForm.getObjServiceProviderDTO().setIsDRCancelled(1);
			 
			 forwardJsp=ServiceProviderConstant.APP_STATUS_CHANGE_BY_DR_SUCCESS;
			 objServiceProviderDTO.setActionName("");
		}
	}

	/**
	 * @param request
	 * @param objServiceProviderForm
	 * @param objServiceProviderBO

	 * @param objServiceProviderDTO
	 * @return
	 */
	//modified by shruti-session added
	private ServiceProviderForm afterSPIdClickByDR(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderBO objServiceProviderBO,
			ServiceProviderDTO objServiceProviderDTO,HttpSession session) {
		
		String lang=(String)session.getAttribute("languageLocale");
		try {
			 PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			  String requestNumber = request.getParameter("hdnApplicationId");
			  logger.debug("The request number obtained on the click of dashboard link is: "+requestNumber);
			  objServiceProviderForm.getObjServiceProviderDTO().setRequestNo(requestNumber);
			  objServiceProviderForm.getObjServiceProviderDTO().setReqNo(requestNumber);
			  
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpBank(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovd(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsReactivateByDR(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsRjectRmrks(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovdRmrks(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPVRmrks(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsCallForAddInfoRmrks(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminal(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiGaz(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setDobCerti(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setRentDetail(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setHigherSecCerti(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setPhotoImg(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setCharacterCerti(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setAgeProof(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setSolvencyCerti(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setPoliceCaseCerti(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setPossessionBusinessDoc(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setHigherEduCerti(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setAffidavitHardware(null);
			  //added by shruti--2nd april 2014
			  objServiceProviderForm.getObjServiceProviderDTO().setPancardUpload(null);
			  objServiceProviderForm.getObjServiceProviderDTO().setForm60Upload(null);
			  //end
			  //ADDED BY SHRUTI---22 APRIL 2014
			  objServiceProviderForm.getObjServiceProviderDTO().setSignatureName(null);
			  //ADDED BY SHRUTI---28 july 2014
			  objServiceProviderForm.getObjServiceProviderDTO().setOwmFile("");
			  objServiceProviderForm.getObjServiceProviderDTO().setOwmFlag("");
			  objServiceProviderForm.getObjServiceProviderDTO().setEnablePrintFlag("N");
			  objServiceProviderForm.getObjServiceProviderDTO().setOtp("");
			  //END
			  objServiceProviderForm = (ServiceProviderForm) objServiceProviderBO.getspDocDetlsDR(objServiceProviderForm);
			  
			  String spType = objServiceProviderBO.getspTypeId(requestNumber);
			  objServiceProviderForm.getObjServiceProviderDTO().setSpType(spType);
			  logger.debug("The sp type id obtained from database is : "+spType);
			  
			  if (spType.equalsIgnoreCase("1"))
			  {
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(1);
			  ArrayList details = objServiceProviderBO.getspDetls(objServiceProviderForm,lang);
			  logger.info("--------------->"+ details.size()); 
			  
			  if (details.size() == 0)
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetails(null);
			  }
			  else
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetails(details);
			  }

				  request.setAttribute("details", objServiceProviderForm.getObjServiceProviderDTO().getSpAppDetails());
				  
				  	ArrayList applicantDetails = objServiceProviderBO.getSPDetailsInd(objServiceProviderForm,lang);
					logger.info("--------------->"+ applicantDetails.size()); 
					
					if (applicantDetails.size() == 0)
					{
						objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(null);
					}
					else
					{
						objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(applicantDetails);
					}

					request.setAttribute("applicantDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDetails());
					
			  }
			  
			  if (spType.equalsIgnoreCase("2") || spType.equalsIgnoreCase("3") || spType.equalsIgnoreCase("4"))
			  {
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpBank(1);
			  ArrayList details = objServiceProviderBO.getspDetlsBank(objServiceProviderForm,lang);
			  logger.info("--------------->"+ details.size()); 
			  
			  if (details.size() == 0)
				  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsBank(null);
			  else
				  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsBank(details);

				  request.setAttribute("details", objServiceProviderForm.getObjServiceProviderDTO().getSpAppDetailsBank());
			  }
			  
			  if (spType.equalsIgnoreCase("1")||spType.equalsIgnoreCase("2")||spType.equalsIgnoreCase("3")||spType.equalsIgnoreCase("4"))
			  {
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(1);
			  ArrayList details = objServiceProviderBO.getspDetlsAll(objServiceProviderForm,lang);
			  logger.info("--------------->"+ details.size()); 
			  
			  if (details.size() == 0)
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsAll(null);
			  }
			  else
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsAll(details);
			  }

				  request.setAttribute("details", objServiceProviderForm.getObjServiceProviderDTO().getSpAppDetailsAll());
				  
				  ArrayList applicntDetails = objServiceProviderBO.getSPDetails(objServiceProviderForm,lang);
					logger.info("--------------->"+ applicntDetails.size());
					  
					if (applicntDetails.size() == 0)
					{
						objServiceProviderForm.getObjServiceProviderDTO().setSpDetailsAll(null);
					}
					else
					{
						objServiceProviderForm.getObjServiceProviderDTO().setSpDetailsAll(applicntDetails);
					}

					request.setAttribute("applicntDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDetailsAll());
					
			  }
			  
			  //modified by shruti-----15 april 2014
			  if ((objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("5")) //Request Approved
					  ||(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("6")) //Request Rejected
					  ||(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("9")) //Pending with DIG
					  ||(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("10")) //License Fee not Paid
			/*		  ||(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("11"))*/ //Cancelled
					  ||(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("4")) //Call for Additional Information by DR
					  ||(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("8")) //License Issued
					/*  ||(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("12"))*/) //Expired
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovd(1); 
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReactivateByDR(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsLicenseFeePaid(0);
			  }
		
			  if (objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("2")) //Pending with DR
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovd(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsLicenseFeePaid(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReactivateByDR(1);
				  
			  }
			  if (objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("3")) //Call for Physical Verification by DR
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsRjectRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovdRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPVRmrks(1);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsCallForAddInfoRmrks(0);
				  //for getting latest comments of DR
				  objServiceProviderForm = objServiceProviderBO.getDRComments(objServiceProviderForm);
				  
			  }
			  if (objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("4")) //Call for Additional Information by DR
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsRjectRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovdRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPVRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsCallForAddInfoRmrks(1);
				  //for getting latest comments of DR
				  objServiceProviderForm = objServiceProviderBO.getDRComments(objServiceProviderForm);
				  
			  }
			  if (objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("5")) //Request Approved
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsRjectRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovdRmrks(1);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPVRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsCallForAddInfoRmrks(0);
				  //for getting latest comments of DR
				  objServiceProviderForm = objServiceProviderBO.getDRComments(objServiceProviderForm);
				  
			  }
			  if (objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("6")) //Request Rejected
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsRjectRmrks(1);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovdRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPVRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsCallForAddInfoRmrks(0);
				  //for getting latest comments of DR
				  objServiceProviderForm = objServiceProviderBO.getDRComments(objServiceProviderForm);
				  
			  }
			  
			  if (objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("7")) //License fee paid
			  {
				  
				  //TODO --- needs to be done for credit limit page view.
				  ArrayList licenseIssuedFeePaid = objServiceProviderBO.getLicenseNumber(objServiceProviderForm);
				  
				  if(licenseIssuedFeePaid.size()>0)
				  {
				  ArrayList previousLicenseDetlsEstamp = objServiceProviderBO.getPreviousLicenseDetailsEstamp1(objServiceProviderForm);
				  ArrayList previousLicenseDetlsOthers = objServiceProviderBO.getPreviousLicenseDetailsOthers1(objServiceProviderForm);
				//added by siddhartha  
				  ArrayList previousLicenseDetlsJudicialEstamp = objServiceProviderBO.getPreviousLicenseDetailsJudicialEstamp1(objServiceProviderForm);
				  
				  
				  logger.debug("Size of previous license details arrayList is: "+previousLicenseDetlsEstamp.size());
				  logger.debug("Size of previous license details arrayList is: "+previousLicenseDetlsOthers.size());
				  logger.debug("Size of previous license details arrayList is: "+previousLicenseDetlsJudicialEstamp.size());
				  
				  if((previousLicenseDetlsEstamp.size()>0)||(previousLicenseDetlsOthers.size()>0)||previousLicenseDetlsJudicialEstamp.size()>0)
				  {
					  //changes to make balance amount carry forward default option yes by santosh
					  //objServiceProviderForm.getObjServiceProviderDTO().setRadioBal("N");
					  objServiceProviderForm.getObjServiceProviderDTO().setRadioBal("Y");
					  objServiceProviderForm.getObjServiceProviderDTO().setChkBoxCreditLimit("");
					  if ((previousLicenseDetlsEstamp.size()>0))
					  {
					  objServiceProviderForm.getObjServiceProviderDTO().setEstampBalanceCreditLimit(objServiceProviderForm.getObjServiceProviderDTO().getBalAmountCreditEstamp());
					  }
					  else 
					  {
						  objServiceProviderForm.getObjServiceProviderDTO().setEstampBalanceCreditLimit("0"); 
						  objServiceProviderForm.getObjServiceProviderDTO().setHdnBalAmountCreditEstamp("0"); 
					  }
					  if ((previousLicenseDetlsOthers.size()>0))
					  {
					  objServiceProviderForm.getObjServiceProviderDTO().setOthersBalanceCreditLimit(objServiceProviderForm.getObjServiceProviderDTO().getBalAmountCreditOthers());
					  }
					  
					  else
					  {
						  objServiceProviderForm.getObjServiceProviderDTO().setOthersBalanceCreditLimit("0");
						  objServiceProviderForm.getObjServiceProviderDTO().setHdnBalAmountCreditOthers("0");
					  }
					  if ((previousLicenseDetlsJudicialEstamp.size()>0))
					  {
					  objServiceProviderForm.getObjServiceProviderDTO().setJudicialEstampBalanceCreditLimit(objServiceProviderForm.getObjServiceProviderDTO().getBalAmountCreditEstampJudicial());
					  }
					  else 
					  {
						  objServiceProviderForm.getObjServiceProviderDTO().setJudicialEstampBalanceCreditLimit("0"); 
						  objServiceProviderForm.getObjServiceProviderDTO().setHdnBalAmountCreditEstampJudicial("0"); 
					  }
					  
					  // for view of license number.
					  objServiceProviderForm = objServiceProviderBO.getFirstTimeLicenseDetails(objServiceProviderForm);
					  forwardJsp=ServiceProviderConstant.PAGE_SHOWING_CARRY_FORWARD_BALANCE;
				  }
				  
				  else
				  {
					  //TODO --
					  //ADDED BY SHRUTI--20 FEB 2014---LICENSE DURATION DATA
					  ArrayList licenseDetails = objServiceProviderBO.getLicenseDetails(objServiceProviderForm);
					  objServiceProviderForm.getObjServiceProviderDTO().setIsLicenseFeePaid(0);
					  objServiceProviderForm.getObjServiceProviderDTO().setIsLicenseNumberPresent(1);
					  
					  forwardJsp=ServiceProviderConstant.VIEW_AFTER_LICENSE_FEE_PAID;
				  }
				  
				  }
				  
				  else
				  {
				  ArrayList licenseIssued = objServiceProviderBO.getLicenseNumber(objServiceProviderForm);
				  logger.debug("Size : "+licenseIssued.size());
				  objServiceProviderForm.getObjServiceProviderDTO().setGuid("");
				  
				  if (spType.equalsIgnoreCase("1"))
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setParentPathSign(pr.getValue("igrs_upload_path")+"/12/01/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_DR");
				  }
				  else if (spType.equalsIgnoreCase("2"))
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setParentPathSign(pr.getValue("igrs_upload_path")+"/12/02/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_DR");  
				  }
				  else if (spType.equalsIgnoreCase("3"))
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setParentPathSign(pr.getValue("igrs_upload_path")+"/12/03/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_DR"); 
				  }
				  else if (spType.equalsIgnoreCase("4"))
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setParentPathSign(pr.getValue("igrs_upload_path")+"/12/04/"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_DR");  
				  }
				  objServiceProviderForm.getObjServiceProviderDTO().setFileNameSign("Signature_Licensing_Authority.GIF");
				  objServiceProviderForm.getObjServiceProviderDTO().setForwardName("serviceProviderAction");
				  objServiceProviderForm.getObjServiceProviderDTO().setForwardPath("/serviceProviderAction.do?signatureOfDR=fromSP");
				  
				  if (licenseIssued.size()>0)
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setIsLicenseFeePaid(0);
					  objServiceProviderForm.getObjServiceProviderDTO().setIsLicenseNumberPresent(1);
					  
				  }
				  else
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setIsLicenseFeePaid(1); 
					  objServiceProviderForm.getObjServiceProviderDTO().setIsLicenseNumberPresent(0);
					  
				  }
				  forwardJsp=ServiceProviderConstant.VIEW_AFTER_LICENSE_FEE_PAID;
				  }
				  
			  }
			  
			  else if (objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("8")) //License Issued
			  {
			  	  ArrayList licenseDetails = objServiceProviderBO.getLicenseDetails(objServiceProviderForm);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsLicenseFeePaid(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsLicenseNumberPresent(1);
				  
			  
				  forwardJsp=ServiceProviderConstant.VIEW_AFTER_LICENSE_FEE_PAID;
			  
			  }
			//added---15 april 2014
			  else if ((objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("11")) //Cancelled //License Issued
					  ||(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("12"))//EXPIRED
					  ||(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("9"))//PENDING WITH DIG
					  ||(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("6"))) //REJECTED
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovd(1); 
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReactivateByDR(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsLicenseFeePaid(0);
				  forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK_OTHER;
			  }
			  //added by shruti---28 april 2014
			  else if ((objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("4"))) //call for add info
					  
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsRjectRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovdRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsCallForPVRmrks(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsCallForAddInfoRmrks(1);
				  //for getting latest comments of DR
				  objServiceProviderForm = objServiceProviderBO.getDRComments(objServiceProviderForm);
				  forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK_OTHER;
			  }
			  //modified by shruti---29 aug 2014
			  else if ((objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("5"))) //request approved
				  
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovd(1); 
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReactivateByDR(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsLicenseFeePaid(0);
				  forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK_OTHER;
			  }
			  //end
			  
			  else
			  {
			  
			  forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
			  
			  }
			  objServiceProviderDTO.setActionName("");
			  }
			  
		  catch (Exception e)
		  {
			  e.printStackTrace();
		  }
		return objServiceProviderForm;
	}

	/**
	 * @param request
	 * @param objServiceProviderForm
	 * @param objServiceProviderBO
	 * @param objServiceProviderDTO
	 * @return
	 */
	private ServiceProviderForm cancelLicenseView(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderBO objServiceProviderBO,
			ServiceProviderDTO objServiceProviderDTO,HttpSession session) {
		String lang=(String)session.getAttribute("languageLocale");
		try {
			  
			objServiceProviderForm.getObjServiceProviderDTO().setGistOrder("");
			String licenseId = request.getParameter("licenseId");
			  String requestNumber=objServiceProviderBO.getRequestNumber(licenseId);
			  objServiceProviderForm.getObjServiceProviderDTO().setLicenseId(licenseId);
			  objServiceProviderForm.getObjServiceProviderDTO().setRequestNo(requestNumber);
			  objServiceProviderForm.getObjServiceProviderDTO().setReqNo(requestNumber);
			  logger.debug("The request number obtained on the click of dashboard link is: "+requestNumber);
			  
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpBank(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovd(0);
			  objServiceProviderForm.getObjServiceProviderDTO().setIsPaymentComplete(0);
			  
			  objServiceProviderForm = objServiceProviderBO.getPaymentDetls(objServiceProviderForm,lang);
			  logger.debug("The balance amount obtained is: "+Double.toString(objServiceProviderForm.getObjServiceProviderDTO().getAmountRemaining()));
			  
			  String spType = objServiceProviderBO.getspTypeId(requestNumber);
			  
			  logger.debug("The sp type id obtained from database is : "+spType);
			  
			  objServiceProviderForm.getObjServiceProviderDTO().setSpType(spType);
			  
			  
			  if (spType.equalsIgnoreCase("1"))
			  {
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(1);
			  ArrayList details = objServiceProviderBO.getspDetls(objServiceProviderForm,lang);
			  logger.info("--------------->"+ details.size()); 
			  
			  if (details.size() == 0)
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetails(null);
			  }
			  else
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetails(details);
				  
			  }

				  request.setAttribute("details", objServiceProviderForm.getObjServiceProviderDTO().getSpAppDetails());
				  
				  	ArrayList applicantDetails = objServiceProviderBO.getSPDetailsInd(objServiceProviderForm,lang);
					logger.info("--------------->"+ applicantDetails.size()); 
					
					if (applicantDetails.size() == 0)
					{
						objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(null);
					}
					else
					{
						objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(applicantDetails);
					}

					request.setAttribute("applicantDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDetails());
			  }
			  
			  if (spType.equalsIgnoreCase("2") || spType.equalsIgnoreCase("3") || spType.equalsIgnoreCase("4"))
			  {
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpBank(1);
			  ArrayList details = objServiceProviderBO.getspDetlsBank(objServiceProviderForm,lang);
			  logger.info("--------------->"+ details.size()); 
			  
			  if (details.size() == 0)
				  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsBank(null);
			  else
				  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsBank(details);

				  request.setAttribute("details", objServiceProviderForm.getObjServiceProviderDTO().getSpAppDetailsBank());
			  }
			  
			  if (spType.equalsIgnoreCase("1")||spType.equalsIgnoreCase("2")||spType.equalsIgnoreCase("3")||spType.equalsIgnoreCase("4"))
			  {
			  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(1);
			  ArrayList details = objServiceProviderBO.getspDetlsAll(objServiceProviderForm,lang);
			  logger.info("--------------->"+ details.size()); 
			  
			  if (details.size() == 0)
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsAll(null);
			  }
			  else
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsAll(details);
			  }

				  request.setAttribute("details", objServiceProviderForm.getObjServiceProviderDTO().getSpAppDetailsAll());
				  
				  ArrayList applicntDetails = objServiceProviderBO.getSPDetails(objServiceProviderForm,lang);
					logger.info("--------------->"+ applicntDetails.size());
					  
					if (applicntDetails.size() == 0)
					{
						objServiceProviderForm.getObjServiceProviderDTO().setSpDetailsAll(null);
					}
					else
					{
						objServiceProviderForm.getObjServiceProviderDTO().setSpDetailsAll(applicntDetails);
					}

					request.setAttribute("applicntDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDetailsAll());
			  }
			  
			  if ("5".equals(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus()))
			  {
				  if (Double.toString(objServiceProviderForm.getObjServiceProviderDTO().getAmountRemaining()).equals("0.0"))
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovd(0); 
					  objServiceProviderForm.getObjServiceProviderDTO().setIsPaymentComplete(1);
				  }
				  else
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovd(1); 
					  objServiceProviderForm.getObjServiceProviderDTO().setIsPaymentComplete(0);
				  }
			  }
			  
			  if ("4".equals(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus()))
			  {
				  objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminal(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiGaz(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setDobCerti(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setRentDetail(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setHigherSecCerti(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setPhotoImg(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setCharacterCerti(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setAgeProof(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setSolvencyCerti(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setPoliceCaseCerti(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setPossessionBusinessDoc(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setHigherEduCerti(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setAffidavitHardware(null);
				  //added by shruti--2nd april 2014
				  objServiceProviderForm.getObjServiceProviderDTO().setPancardUpload(null);
				  objServiceProviderForm.getObjServiceProviderDTO().setForm60Upload(null);
				  //end
				  objServiceProviderForm.getObjServiceProviderDTO().setDistrictList(objServiceProviderBO.getDistrict(lang));
				  
				  if (spType.equalsIgnoreCase("1"))
				  {
					  	objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(1);
					  
					  	objServiceProviderForm =(ServiceProviderForm) objServiceProviderBO.getspDetlsForUpdation(objServiceProviderForm,lang);
					  
					  	ArrayList applicantDetails = objServiceProviderBO.getSPDetailsInd(objServiceProviderForm,lang);
						logger.info("--------------->"+ applicantDetails.size()); 
						
						if (applicantDetails.size() == 0)
						{
							objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(null);
						}
						else
						{
							objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(applicantDetails);
						}

						request.setAttribute("applicantDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDetails());
				  }
				  
				  if (spType.equalsIgnoreCase("2") || spType.equalsIgnoreCase("3") || spType.equalsIgnoreCase("4"))
				  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpBank(1);
				  objServiceProviderForm = (ServiceProviderForm)objServiceProviderBO.getspDetlsBankForUpdation(objServiceProviderForm,lang);
				  }
				  
				  if (spType.equalsIgnoreCase("1")||spType.equalsIgnoreCase("2")||spType.equalsIgnoreCase("3")||spType.equalsIgnoreCase("4"))
				  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(1);
				  objServiceProviderForm = (ServiceProviderForm)objServiceProviderBO.getspDetlsAllForUpdation(objServiceProviderForm,lang);
				  
				  String disttName = (String)objServiceProviderForm.getObjServiceProviderDTO().getDistrict();
				  String disttId =(String)objServiceProviderBO.getdisttId(objServiceProviderForm);
				  objServiceProviderForm.getObjServiceProviderDTO().setDistrict(disttId);
				  objServiceProviderForm.getObjServiceProviderDTO().setTehsilList(objServiceProviderBO.getTehsil(disttId,lang));
					  
					  ArrayList applicntDetails = objServiceProviderBO.getSPDetails(objServiceProviderForm,lang);
						logger.info("--------------->"+ applicntDetails.size());
						  
						if (applicntDetails.size() == 0)
						{
							objServiceProviderForm.getObjServiceProviderDTO().setSpDetailsAll(null);
						}
						else
						{
							objServiceProviderForm.getObjServiceProviderDTO().setSpDetailsAll(applicntDetails);
						}

						request.setAttribute("applicntDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDetailsAll());
				  }
				  
				  objServiceProviderForm = (ServiceProviderForm) objServiceProviderBO.getspDocDetlsDR(objServiceProviderForm);
				  
				  forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			  }
			  else
			  {
				  //MODIFIED BY SHRUTI---2ND APRIL 2014
				  /*ArrayList docDetails = objServiceProviderBO.getspDocDetls(requestNumber);
				  logger.info("--------------->"+ docDetails.size()); 
				  
				  if (docDetails.size() == 0)
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setSpDocDetails(null);
				  }
				  else
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setSpDocDetails(docDetails);
				  }*/
				  objServiceProviderForm=objServiceProviderBO.getspDocDetlsDR(objServiceProviderForm);

					  request.setAttribute("docDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDocDetails());
					  
				  forwardJsp =ServiceProviderConstant.CANCEL_LICENSE_SUBMIT;  
			  }
			  
			  	
				objServiceProviderDTO.setActionName("");
			  }
		  	
			  
		  catch (Exception e)
		  {
			  e.printStackTrace();
		  }
		return objServiceProviderForm;
	}

	/**
	 * @param request
	 * @param objServiceProviderForm
	 * @param objServiceProviderBO
	 * @throws Exception
	 */
	//declaration modified to store session--shruti
	private void fromPaymentModule(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderBO objServiceProviderBO,HttpSession session) throws Exception {
		
		logger.info("------>*****BACK TO ACTION");
		String lang=(String)session.getAttribute("languageLocale");
		if (request.getParameter("paymentStatus").equalsIgnoreCase("P"))
		{
			String uniId = (String) request.getParameter("parentKey");
			
			if (uniId != null || uniId != "") 
			{
				objServiceProviderForm.getObjServiceProviderDTO().setSpPaymentId(uniId);
			}
			
			objServiceProviderForm = objServiceProviderBO.getPaymentDetls(objServiceProviderForm,lang);
			double amtToBePaid = Double.parseDouble((String) objServiceProviderForm.getObjServiceProviderDTO().getPayableAmount());
			double paidAmount = Double.parseDouble((String)objServiceProviderForm.getObjServiceProviderDTO().getPaidAmount());
			
			if (amtToBePaid > paidAmount)
			{
				String uId = (String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
				// String lang=(String)session.getAttribute("languageLocale");
				  ArrayList pendingApplicationList = objServiceProviderBO.getPendingAppsSP(uId,lang);

					logger.info("--------------->"+ pendingApplicationList.size());
					if (pendingApplicationList.size() == 0)
						objServiceProviderForm.getObjServiceProviderDTO().setAppList(null);
					else
						objServiceProviderForm.getObjServiceProviderDTO().setAppList(pendingApplicationList);
					request.setAttribute("pendingApplicationList", objServiceProviderForm.getObjServiceProviderDTO().getAppList());
					logger.info("--------------->succesfully received values");
					
					forwardJsp = ServiceProviderConstant.DASHBOARD_SP;
					
					logger.info("--------------->showing dashboard for SP");
			}
			if (amtToBePaid == paidAmount)
			{
				String requestNumber= (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
				
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpBank(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovd(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsPaymentComplete(0);
				
				String spType = objServiceProviderBO.getspTypeId(requestNumber);
				
				logger.debug("The sp type id obtained from database is : "+spType);
				  
				  if (spType.equalsIgnoreCase("1"))
				  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(1);
				  ArrayList details = objServiceProviderBO.getspDetls(objServiceProviderForm,lang);
				  logger.info("--------------->"+ details.size()); 
				  
				  if (details.size() == 0)
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetails(null);
				  }
				  else
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetails(details);
					  
				  }

					  request.setAttribute("details", objServiceProviderForm.getObjServiceProviderDTO().getSpAppDetails());
					  
					  	ArrayList applicantDetails = objServiceProviderBO.getSPDetailsInd(objServiceProviderForm,lang);
						logger.info("--------------->"+ applicantDetails.size()); 
						
						if (applicantDetails.size() == 0)
						{
							objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(null);
						}
						else
						{
							objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(applicantDetails);
						}

						request.setAttribute("applicantDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDetails());
				  }
				  
				  if (spType.equalsIgnoreCase("2") || spType.equalsIgnoreCase("3") || spType.equalsIgnoreCase("4"))
				  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpBank(1);
				  ArrayList details = objServiceProviderBO.getspDetlsBank(objServiceProviderForm,lang);
				  logger.info("--------------->"+ details.size()); 
				  
				  if (details.size() == 0)
					  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsBank(null);
				  else
					  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsBank(details);

					  request.setAttribute("details", objServiceProviderForm.getObjServiceProviderDTO().getSpAppDetailsBank());
				  }
				  
				  if (spType.equalsIgnoreCase("1")||spType.equalsIgnoreCase("2")||spType.equalsIgnoreCase("3")||spType.equalsIgnoreCase("4"))
				  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(1);
				  ArrayList details = objServiceProviderBO.getspDetlsAll(objServiceProviderForm,lang);
				  logger.info("--------------->"+ details.size()); 
				  
				  if (details.size() == 0)
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsAll(null);
				  }
				  else
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsAll(details);
				  }

					  request.setAttribute("details", objServiceProviderForm.getObjServiceProviderDTO().getSpAppDetailsAll());
					  
					  ArrayList applicntDetails = objServiceProviderBO.getSPDetails(objServiceProviderForm,lang);
						logger.info("--------------->"+ applicntDetails.size());
						  
						if (applicntDetails.size() == 0)
						{
							objServiceProviderForm.getObjServiceProviderDTO().setSpDetailsAll(null);
						}
						else
						{
							objServiceProviderForm.getObjServiceProviderDTO().setSpDetailsAll(applicntDetails);
						}

						request.setAttribute("applicntDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDetailsAll());
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("5"))
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovd(0); 
					  objServiceProviderForm.getObjServiceProviderDTO().setIsPaymentComplete(1);
				  }
				  
				  
				  
					  ArrayList docDetails = objServiceProviderBO.getspDocDetls(requestNumber);
					  logger.info("--------------->"+ docDetails.size()); 
					  
					  if (docDetails.size() == 0)
					  {
						  objServiceProviderForm.getObjServiceProviderDTO().setSpDocDetails(null);
					  }
					  else
					  {
						  objServiceProviderForm.getObjServiceProviderDTO().setSpDocDetails(docDetails);
					  }

						  request.setAttribute("docDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDocDetails());
						  
					  forwardJsp = ServiceProviderConstant.VIEW_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;  
			
			
			}
			//modified by shruti to accomodate the more amount paid than the actual amount payable case--20 feb 2014
			else if (paidAmount>amtToBePaid)
			{
				String requestNumber= (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
				
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpBank(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovd(0);
				  objServiceProviderForm.getObjServiceProviderDTO().setIsPaymentComplete(0);
				
				String spType = objServiceProviderBO.getspTypeId(requestNumber);
				
				logger.debug("The sp type id obtained from database is : "+spType);
				  
				  if (spType.equalsIgnoreCase("1"))
				  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpInd(1);
				  ArrayList details = objServiceProviderBO.getspDetls(objServiceProviderForm,lang);
				  logger.info("--------------->"+ details.size()); 
				  
				  if (details.size() == 0)
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetails(null);
				  }
				  else
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetails(details);
					  
				  }

					  request.setAttribute("details", objServiceProviderForm.getObjServiceProviderDTO().getSpAppDetails());
					  
					  	ArrayList applicantDetails = objServiceProviderBO.getSPDetailsInd(objServiceProviderForm,lang);
						logger.info("--------------->"+ applicantDetails.size()); 
						
						if (applicantDetails.size() == 0)
						{
							objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(null);
						}
						else
						{
							objServiceProviderForm.getObjServiceProviderDTO().setSpDetails(applicantDetails);
						}

						request.setAttribute("applicantDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDetails());
				  }
				  
				  if (spType.equalsIgnoreCase("2") || spType.equalsIgnoreCase("3") || spType.equalsIgnoreCase("4"))
				  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpBank(1);
				  ArrayList details = objServiceProviderBO.getspDetlsBank(objServiceProviderForm,lang);
				  logger.info("--------------->"+ details.size()); 
				  
				  if (details.size() == 0)
					  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsBank(null);
				  else
					  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsBank(details);

					  request.setAttribute("details", objServiceProviderForm.getObjServiceProviderDTO().getSpAppDetailsBank());
				  }
				  
				  if (spType.equalsIgnoreCase("1")||spType.equalsIgnoreCase("2")||spType.equalsIgnoreCase("3")||spType.equalsIgnoreCase("4"))
				  {
				  objServiceProviderForm.getObjServiceProviderDTO().setIsSpAllType(1);
				  ArrayList details = objServiceProviderBO.getspDetlsAll(objServiceProviderForm,lang);
				  logger.info("--------------->"+ details.size()); 
				  
				  if (details.size() == 0)
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsAll(null);
				  }
				  else
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setSpAppDetailsAll(details);
				  }

					  request.setAttribute("details", objServiceProviderForm.getObjServiceProviderDTO().getSpAppDetailsAll());
					  
					  ArrayList applicntDetails = objServiceProviderBO.getSPDetails(objServiceProviderForm,lang);
						logger.info("--------------->"+ applicntDetails.size());
						  
						if (applicntDetails.size() == 0)
						{
							objServiceProviderForm.getObjServiceProviderDTO().setSpDetailsAll(null);
						}
						else
						{
							objServiceProviderForm.getObjServiceProviderDTO().setSpDetailsAll(applicntDetails);
						}

						request.setAttribute("applicntDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDetailsAll());
				  }
				  
				  if (objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().toString().equalsIgnoreCase("5"))
				  {
					  objServiceProviderForm.getObjServiceProviderDTO().setIsReqApprovd(0); 
					  objServiceProviderForm.getObjServiceProviderDTO().setIsPaymentComplete(1);
				  }
				  
				  
				  
					  ArrayList docDetails = objServiceProviderBO.getspDocDetls(requestNumber);
					  logger.info("--------------->"+ docDetails.size()); 
					  
					  if (docDetails.size() == 0)
					  {
						  objServiceProviderForm.getObjServiceProviderDTO().setSpDocDetails(null);
					  }
					  else
					  {
						  objServiceProviderForm.getObjServiceProviderDTO().setSpDocDetails(docDetails);
					  }

						  request.setAttribute("docDetails", objServiceProviderForm.getObjServiceProviderDTO().getSpDocDetails());
						  
					  forwardJsp = ServiceProviderConstant.VIEW_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;  
			
			
			}
			//end
		}
	}
    
    private String getFileExtension(String fileName) {
		try {
			int pos = fileName.lastIndexOf(".");
			String strExt = fileName.substring(pos + 1, fileName.length());
			if(strExt!=null && !" ".equalsIgnoreCase(strExt))
			{
			strExt=strExt.toLowerCase();
			}
			return strExt;
		} catch (Exception e) {
		}
		return "";
	}
    
    private String removeFile(String fileName, String filePath) {
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
	
	private byte[] getByteArray(File filetobeuploaded) throws FileNotFoundException {

				
				FileInputStream fis = new FileInputStream(filetobeuploaded);
				
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
		        byte[] buf = new byte[1024];
		        try {
		            for (int readNum; (readNum = fis.read(buf)) != -1;) {
		                //Writes to this byte array output stream
		                bos.write(buf, 0, readNum); 
		            }
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		 
		        byte[] bytes = bos.toByteArray();
		return bytes;
	}	
	
	private boolean uploadFileFromBytes(byte[] filetobeuploaded,String fileName,String filePath) {
		
		try {
			File folder = new File(filePath);
		      if (!folder.exists()) {
			folder.mkdirs();
		       }
		      
				File newFile = new File(filePath, fileName);
				logger.info("NEW FILE NAME:-" + newFile);
				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(filetobeuploaded);
				fos.close();
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

  //added by shruti
	public void setPanCardUpload(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderBO objServiceProviderBO,
			ServiceProviderDTO objServiceProviderDTO,HttpSession session) {
		try {
			
			objServiceProviderForm.getObjServiceProviderDTO().setPancardFlag(objServiceProviderForm.getObjServiceProviderDTO().getPancardFlag());
			ArrayList	errorList = new ArrayList();
			//FormFile uploadedFile = eForm.getFilePhoto2();
			FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getPancardUpload();
			byte contents[] = uploadedFile.getFileData();
			objServiceProviderForm.getObjServiceProviderDTO().setPancardUpload1(contents);
			
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add("Invalid file selection. Please try again.\n\n अवैध फ़ाइल चयन |पुन: प्रयास करें|");
			}
			String fileExt = getFileExtension(uploadedFile.getFileName());
			AuditInspectionRule rule = new AuditInspectionRule();
			rule.validateFileTypeSP(fileExt);
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String photoSize = "(" + fileSize + "MB)";
			if (rule.isError()) {
				errorList.add("Invalid file type. Please select another file.\n\n अमान्य फ़ाइल प्रकार |अन्य फाइल चुनें |");
				request.setAttribute("errorsListp",errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE_SP) {
					errorList.add("File size is Greater than 500 KB. Please select another file.\n\n फ़ाइल का आकार 500 KB से अधिक है| अन्य फाइल चुनें|");
					request.setAttribute("errorsListp",errorList);
				} else {
					
					logger.debug("The type of user is "+objServiceProviderForm.getObjServiceProviderDTO().getPancardFileDR());
					
					String docName = "PanCard."+fileExt;
					objServiceProviderForm.getObjServiceProviderDTO().setPancardDoc(uploadedFile.getFileName());
					if (((String)objServiceProviderForm.getObjServiceProviderDTO().getPancardPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getPancardFileSP().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getPancardPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getPancardFileDR().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getPancardPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getPancardFileRenewal().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;	
					}
					else
					{
					forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
					}
					
					} 
				}
			objServiceProviderDTO.setActionName("");
	}
		catch (Exception e) {
		e.printStackTrace();
	}
	}

	
	public void setForm60Upload(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderBO objServiceProviderBO,
			ServiceProviderDTO objServiceProviderDTO,HttpSession session) {
		try {
			
			ArrayList	errorList = new ArrayList();
			FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getForm60Upload();
			byte contents[] = uploadedFile.getFileData();
			objServiceProviderForm.getObjServiceProviderDTO().setForm60Upload1(contents);
			
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add("Invalid file selection. Please try again.\n\n अवैध फ़ाइल चयन |पुन: प्रयास करें |");
			}
			String fileExt = getFileExtension(uploadedFile.getFileName());
			AuditInspectionRule rule = new AuditInspectionRule();
			rule.validateFileTypeSP(fileExt);
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String photoSize = "(" + fileSize + "MB)";
			if (rule.isError()) {
				errorList.add("Invalid file type. Please select another file.\n\n अमान्य फ़ाइल प्रकार |अन्य फाइल चुनें |");
				request.setAttribute("errorsListf",errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE_SP) {
					errorList.add("File size is Greater than 500 KB. Please select another file.\n\n फ़ाइल का आकार 500 KB से अधिक है| अन्य फाइल चुनें|");
					request.setAttribute("errorsListf",errorList);
				} else {
					
					logger.debug("The type of user is "+objServiceProviderForm.getObjServiceProviderDTO().getForm60FileDR());
					
					String docName = "Form60_61."+fileExt;
					objServiceProviderForm.getObjServiceProviderDTO().setForm60Doc(uploadedFile.getFileName());
					if (((String)objServiceProviderForm.getObjServiceProviderDTO().getForm60Path() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getForm60FileSP().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getForm60Path() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getForm60FileDR().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getForm60Path() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getForm60FileRenewal().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;	
					}
					else
					{
					forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
					}
					
					} 
				}
			objServiceProviderDTO.setActionName("");
	}
		catch (Exception e) {
		e.printStackTrace();
	}
	}	
	
	public void removeUploadedPanCard(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderBO objServiceProviderBO,
			ServiceProviderDTO objServiceProviderDTO,HttpSession session) {
		try {
			 PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			FormFile fname = (FormFile) objServiceProviderForm.getObjServiceProviderDTO().getPancardUpload();
			if (fname!=null)
			{
			if(("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/01"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setPancardDoc("");
			}
			else if (("2").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/02"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setPancardDoc("");
			}
			else if (("3").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/03"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setPancardDoc("");
			}
			else if (("4").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/04"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setPancardDoc("");
			}
			
			if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getPancardFileSP()))
			{
				forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getPancardFileDR()))
			{
				forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getPancardFileRenewal()))
			{
				forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			}
			
			else
			{
			
			forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
			}
			
			}
			
			else
			{
				String name = objServiceProviderForm.getObjServiceProviderDTO().getPancardDoc();
				String path = objServiceProviderForm.getObjServiceProviderDTO().getPancardPath();
				removeFile(name, path);
				objServiceProviderForm.getObjServiceProviderDTO().setPancardDoc("");
				
				if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getPancardFileSP()))
				{
					forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getPancardFileDR()))
				{
					forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getPancardFileRenewal()))
				{
					forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
				}
			}
			
			objServiceProviderDTO.setActionName("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void removeUploadedForm60(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderBO objServiceProviderBO,
			ServiceProviderDTO objServiceProviderDTO,HttpSession session) {
		try {
			 PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			FormFile fname = (FormFile) objServiceProviderForm.getObjServiceProviderDTO().getForm60Upload();
			if (fname!=null)
			{
			if(("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/01"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setForm60Doc("");
			}
			else if (("2").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/02"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setForm60Doc("");
			}
			else if (("3").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/03"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setForm60Doc("");
			}
			else if (("4").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getSpType()))
			{
			String docPath = pr.getValue("igrs_upload_path")+"/12/04"+objServiceProviderForm.getObjServiceProviderDTO().getReqNo()+"_"+fname.getFileName();
			removeFile(fname.getFileName(), docPath);
			objServiceProviderForm.getObjServiceProviderDTO().setForm60Doc("");
			}
			
			if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getForm60FileSP()))
			{
				forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getForm60FileDR()))
			{
				forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
			}
			
			else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getForm60FileRenewal()))
			{
				forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
			}
			
			else
			{
			
			forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
			}
			
			}
			
			else
			{
				String name = objServiceProviderForm.getObjServiceProviderDTO().getForm60Doc();
				String path = objServiceProviderForm.getObjServiceProviderDTO().getForm60Path();
				removeFile(name, path);
				objServiceProviderForm.getObjServiceProviderDTO().setForm60Doc("");
				
				if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getForm60FileSP()))
				{
					forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getForm60FileDR()))
				{
					forwardJsp = ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				}
				
				else if (("1").equalsIgnoreCase(objServiceProviderForm.getObjServiceProviderDTO().getForm60FileRenewal()))
				{
					forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
				}
			}
			
			objServiceProviderDTO.setActionName("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//added by shruti---2nd april 2014
	private void retrievalpancard(HttpServletRequest request,
			HttpServletResponse response,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO)
			throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
		if(request.getParameter("dms").equalsIgnoreCase("retrievalpancard")){
		        
		        if(request.getParameter("path")!=null){

		        	if (objServiceProviderForm.getObjServiceProviderDTO().getPancardUpload()!=null)
		      	  {
		      		  FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getPancardUpload();
		      		  byte contents[] = uploadedFile.getFileData();
						  objServiceProviderForm.getObjServiceProviderDTO().setPancardUpload1(contents);
						  String fileExt = getFileExtension(uploadedFile.getFileName());
					      String fileName ="PanCard."+fileExt;
						  downloadDocument(response, contents, fileName);
						  if(request.getParameter("isSpEdit")!=null)
		 					{
		 						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
		 						{
		 							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
		 						}
		 						
		 					}
						  
						  else if(request.getParameter("isRenewal")!=null)
		 					{
		 						if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
		 						{
		 							forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
		 						}
		 						
		 					}
						  
						  else
						  {
							  forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
						  }
		      	  }
		      	  else
		      	  {
		      		  String filename = request.getParameter("path").toString();
					   response.setContentType("application/download");
					   String fileName = objServiceProviderForm.getObjServiceProviderDTO().getPancardDoc();
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
					   
					  if(request.getParameter("isSpEdit")!=null)
						{
							if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
							{
								forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
							}
							
						}
					  
					 else if(request.getParameter("isRenewal")!=null)
						{
							if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
							{
								forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
							}
						}
					  
					  else
					  {
					   
						 forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
					  }
		           
		      }
		    		 objServiceProviderDTO.setActionName("");
		        }
		        
		  }
	}
	private void retrievalform60(HttpServletRequest request,
			HttpServletResponse response,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO)
			throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
		if(request.getParameter("dms").equalsIgnoreCase("retrievalform60")){
		        
		        if(request.getParameter("path")!=null){

		        	if (objServiceProviderForm.getObjServiceProviderDTO().getForm60Upload()!=null)
		      	  {
		      		  FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getForm60Upload();
		      		  byte contents[] = uploadedFile.getFileData();
						  objServiceProviderForm.getObjServiceProviderDTO().setForm60Upload1(contents);
						  String fileExt = getFileExtension(uploadedFile.getFileName());
					      String fileName ="PanCard."+fileExt;
						  downloadDocument(response, contents, fileName);
						  if(request.getParameter("isSpEdit")!=null)
		 					{
		 						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
		 						{
		 							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
		 						}
		 						
		 					}
						  
						  else if(request.getParameter("isRenewal")!=null)
		 					{
		 						if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
		 						{
		 							forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
		 						}
		 						
		 					}
						  
						  else
						  {
							  forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
						  }
		      	  }
		      	  else
		      	  {
		      		  String filename = request.getParameter("path").toString();
					   response.setContentType("application/download");
					   String fileName = objServiceProviderForm.getObjServiceProviderDTO().getForm60Doc();
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
					   
					  if(request.getParameter("isSpEdit")!=null)
						{
							if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
							{
								forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
							}
							
						}
					  
					 else if(request.getParameter("isRenewal")!=null)
						{
							if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
							{
								forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
							}
							
						}
					  
					  else
					  {
					   
						 forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
					  }
		           
		      }
		    		 objServiceProviderDTO.setActionName("");
		        }
		        
		  }
	}
//end
	private void retrieval3(HttpServletRequest request,
			HttpServletResponse response,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO)
			throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
		if(request.getParameter("path")!=null){

        	if (objServiceProviderForm.getObjServiceProviderDTO().getDobCerti()!=null)
        	  {
        		  FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getDobCerti();
        		  byte contents[] = uploadedFile.getFileData();
					  objServiceProviderForm.getObjServiceProviderDTO().setDobCerti1(contents);
					  String fileExt = getFileExtension(uploadedFile.getFileName());
				      String fileName ="DOB_Certificate."+fileExt;
					  downloadDocument(response, contents, fileName);
					if(request.getParameter("isSpEdit")!=null)
					{
						if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
						{
							forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
						}
						
					}
					
					else if(request.getParameter("isRenewal")!=null)
					{
						if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
						{
							forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
						}
						
					}
					
					else
					{
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
					}
        	  }
        	  else
        	  {
        		  String filename = request.getParameter("path").toString();

				   // set the http content type to "APPLICATION/OCTET-STREAM
				   response.setContentType("application/download");

				   // initialize the http content-disposition header to
				   // indicate a file attachment with the default filename
				   // "myFile.txt"
				   String fileName = objServiceProviderForm.getObjServiceProviderDTO().getDobCertiDoc();
				   //Filename=\"myFile.txt\"";
				   response.setHeader("Content-Disposition", "attachment; filename="
						+ URLEncoder.encode(fileName,"UTF-8"));

				   // transfer the file byte-by-byte to the response object
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
				   
				if(request.getParameter("isSpEdit")!=null)
				{
					if(request.getParameter("isSpEdit").equalsIgnoreCase("yes"))
					{
						forwardJsp = ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;
					}
					
				}
				
				else if(request.getParameter("isRenewal")!=null)
				{
					if(request.getParameter("isRenewal").equalsIgnoreCase("yes"))
					{
						forwardJsp = ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;
					}
					
				}
				
				else
				{
				   
					forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;
				}
             
        }
      		 objServiceProviderDTO.setActionName("");
        }
	}
//end
	
	// Modified code for common MIME type and validation --Rahul
	private void setUploadFile(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			
			ArrayList	errorList = new ArrayList();
			//FormFile uploadedFile = eForm.getFilePhoto2();
			FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal();
			byte contents[] = uploadedFile.getFileData();
			objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminal1(contents);
			
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add("Invalid file selection. Please try again.\n\n अवैध फ़ाइल चयन |पुन: प्रयास करें |");
			}	
			// Added new code for MIME type common - Rahul
			MIMECheck mimeCheck = new MIMECheck();
			//rule.validateFileType(fileExt);
			String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
	       Boolean mime = mimeCheck.validateMIMEAndFileType(uploadedFile);  // common code to check mime type and validation.
			//String fileExt = getFileExtension(uploadedFile.getFileName());
			//AuditInspectionRule rule = new AuditInspectionRule();
			//rule.validateFileTypeSP(fileExt);
			
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String photoSize = "(" + fileSize + "MB)";
			//if (rule.isError())
			if (!mime)
			{
				errorList.add("Invalid file type. Please select another file.\n\n अमान्य फ़ाइल प्रकार |अन्य फाइल चुनें |");
				request.setAttribute("errorsList",errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE_SP) {
					errorList.add("File size is Greater than 500 KB. Please select another file.\n\n फ़ाइल का आकार 500 KB से अधिक है| अन्य फाइल चुनें|");
					request.setAttribute("errorsList",errorList);
				} else {
					
					logger.debug("The type of user is "+objServiceProviderForm.getObjServiceProviderDTO().getFirstFileDR());
					
					String docName = "Criminal_Activities_Affidavit."+fileExt;
					objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc(uploadedFile.getFileName());
					if (((String)objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminalPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getFirstFileSP().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminalPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getFirstFileDR().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminalPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getFirstFileRenewal().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;	
					}
					else
					{
					forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
					}
					
					} 
				}
			objServiceProviderDTO.setActionName("");
	}
		catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	// Modified code
	private void setUploadFile1(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		
		try {
			
			ArrayList	errorList = new ArrayList();
			//FormFile uploadedFile = eForm.getFilePhoto2();
			FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGaz();
			byte contents[] = uploadedFile.getFileData();
			objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiGaz1(contents);
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add("Invalid file selection. Please try again.\n\n अवैध फ़ाइल चयन |पुन: प्रयास करें |");
			}
			/*String fileExt = getFileExtension(uploadedFile.getFileName());
			AuditInspectionRule rule = new AuditInspectionRule();
			rule.validateFileTypeSP(fileExt);*/
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String photoSize = "(" + fileSize + "MB)";
			// new code MIME type - Rahul
			MIMECheck mimeCheck = new MIMECheck();
			String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
	       Boolean mime = mimeCheck.validateMIMEAndFileType(uploadedFile);  // common code to check mime type and validation.
			if (!mime) {
				errorList.add("Invalid file type. Please select another file.\n\n अमान्य फ़ाइल प्रकार |अन्य फाइल चुनें |");
				request.setAttribute("errorsList1",errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE_SP) {
					errorList.add("File size is Greater than 500 KB. Please select another file.\n\n फ़ाइल का आकार 500 KB से अधिक है| अन्य फाइल चुनें|");
					request.setAttribute("errorsList1",errorList);
				} else {
					
					String docName = "Character_Ceertificate_Gazette."+fileExt;
					objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiGazDoc(uploadedFile.getFileName());
					
					if (((String)objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGazPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getSecondFileSP().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGazPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getSecondFileDR().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGazPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getSecondFileRenewal().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;	
					}
					else
					{
					forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
					}
					
					} 
				}
			objServiceProviderDTO.setActionName("");
	}
		catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	// new code for MIME type 
	private void setUploadFile2(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			
			ArrayList	errorList = new ArrayList();
			//FormFile uploadedFile = eForm.getFilePhoto2();
			FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getDobCerti();
			byte contents[] = uploadedFile.getFileData();
			objServiceProviderForm.getObjServiceProviderDTO().setDobCerti1(contents);
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add("Invalid file selection. Please try again.\n\n अवैध फ़ाइल चयन |पुन: प्रयास करें |");
			}
			/*String fileExt = getFileExtension(uploadedFile.getFileName());
			AuditInspectionRule rule = new AuditInspectionRule();
			rule.validateFileTypeSP(fileExt);*/
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String photoSize = "(" + fileSize + "MB)";
			// new code started
			MIMECheck mimeCheck = new MIMECheck();
			String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
	       Boolean mime = mimeCheck.validateMIMEAndFileType(uploadedFile);  // common code to check mime type and validation.
			if (!mime) {
				errorList.add("Invalid file type. Please select another file.\n\n अमान्य फ़ाइल प्रकार |अन्य फाइल चुनें |");
				request.setAttribute("errorsList2",errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE_SP) {
					errorList.add("File size is Greater than 500 KB. Please select another file.\n\n फ़ाइल का आकार 500 KB से अधिक है| अन्य फाइल चुनें|");
					request.setAttribute("errorsList2",errorList);
				} else {
					
					String docName = "DOB_Certificate."+fileExt;
					objServiceProviderForm.getObjServiceProviderDTO().setDobCertiDoc(uploadedFile.getFileName());
					
					if (((String)objServiceProviderForm.getObjServiceProviderDTO().getDobCertiPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getThirdFileSP().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getDobCertiPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getThirdFileDR().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getDobCertiPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getThirdFileRenewal().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;	
					}
					else
					{
					forwardJsp = ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
					}
					
					} 
				}
			objServiceProviderDTO.setActionName("");
	}
		catch (Exception e) {
		e.printStackTrace();
	}
		
	}
	private void setUploadFile3(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			
			ArrayList	errorList = new ArrayList();
			//FormFile uploadedFile = eForm.getFilePhoto2();
			FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getRentDetail();
			byte contents[] = uploadedFile.getFileData();
			objServiceProviderForm.getObjServiceProviderDTO().setRentDetail1(contents);
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add("Invalid file selection. Please try again.\n\n अवैध फ़ाइल चयन |पुन: प्रयास करें |");
			}
			/*String fileExt = getFileExtension(uploadedFile.getFileName());
			AuditInspectionRule rule = new AuditInspectionRule();
			rule.validateFileTypeSP(fileExt);*/
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String photoSize = "(" + fileSize + "MB)";
			// new code 
			MIMECheck mimeCheck = new MIMECheck();
			String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
	       Boolean mime = mimeCheck.validateMIMEAndFileType(uploadedFile);  // common code to check mime type and validation.
			if (!mime) {
				errorList.add("Invalid file type. Please select another file.\n\n अमान्य फ़ाइल प्रकार |अन्य फाइल चुनें |");
				request.setAttribute("errorsList3",errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE_SP) {
					errorList.add("File size is Greater than 500 KB. Please select another file.\n\n फ़ाइल का आकार 500 KB से अधिक है| अन्य फाइल चुनें|");
					request.setAttribute("errorsList3",errorList);
				} else {
					
					String docName = "Rent_Details."+fileExt;
					objServiceProviderForm.getObjServiceProviderDTO().setRentDetailDoc(uploadedFile.getFileName());
					
					
					if (((String)objServiceProviderForm.getObjServiceProviderDTO().getRentDetailPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getFourthFileSP().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getRentDetailPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getFourthFileDR().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getRentDetailPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getFourthFileRenewal().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;	
					}
					else
					{
					forwardJsp=ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
					}
					
					} 
				}
			objServiceProviderDTO.setActionName("");
	}
		catch (Exception e) {
		e.printStackTrace();
	}
		
	}
	private void setUploadFile4(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			
			ArrayList	errorList = new ArrayList();
			//FormFile uploadedFile = eForm.getFilePhoto2();
			FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCerti();
			byte contents[] = uploadedFile.getFileData();
			objServiceProviderForm.getObjServiceProviderDTO().setHigherSecCerti1(contents);
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add("Invalid file selection. Please try again.\n\n अवैध फ़ाइल चयन |पुन: प्रयास करें |");
			}
			/*String fileExt = getFileExtension(uploadedFile.getFileName());
			AuditInspectionRule rule = new AuditInspectionRule();
			rule.validateFileTypeSP(fileExt);*/
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String photoSize = "(" + fileSize + "MB)";
			
			// new code 
			MIMECheck mimeCheck = new MIMECheck();
			String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
	       Boolean mime = mimeCheck.validateMIMEAndFileType(uploadedFile);  // common code to check mime type and validation.
	       
			if (!mime) {
				errorList.add("Invalid file type. Please select another file.\n\n अमान्य फ़ाइल प्रकार |अन्य फाइल चुनें |");
				request.setAttribute("errorsList4",errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE_SP) {
					errorList.add("File size is Greater than 500 KB. Please select another file.\n\n फ़ाइल का आकार 500 KB से अधिक है| अन्य फाइल चुनें|");
					request.setAttribute("errorsList4",errorList);
				} else {
					
					String docName = "Higher_Secondary_Certificate."+fileExt;
					objServiceProviderForm.getObjServiceProviderDTO().setHigherSecCertiDoc(uploadedFile.getFileName());
					
					if (((String)objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCertiPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getFifthFileSP().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCertiPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getFifthFileDR().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCertiPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getFifthFileDR().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;	
					}
					else
					{
					forwardJsp=ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
					}
					
					} 
				}
			objServiceProviderDTO.setActionName("");
	}
		catch (Exception e) {
		e.printStackTrace();
	}
		
	}
	private void setUploadFile12(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			
			ArrayList	errorList = new ArrayList();
			//FormFile uploadedFile = eForm.getFilePhoto2();
			FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware();
			byte contents[] = uploadedFile.getFileData();
			objServiceProviderForm.getObjServiceProviderDTO().setAffidavitHardware1(contents);
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add("Invalid file selection. Please try again.\n\n अवैध फ़ाइल चयन |पुन: प्रयास करें |");
			}
			/*String fileExt = getFileExtension(uploadedFile.getFileName());
			AuditInspectionRule rule = new AuditInspectionRule();
			rule.validateFileTypeSP(fileExt);*/
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String photoSize = "(" + fileSize + "MB)";
			// new code 
			MIMECheck mimeCheck = new MIMECheck();
			String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
	        Boolean mime = mimeCheck.validateMIMEAndFileType(uploadedFile);  // common code to check mime type and validation.
	       
			if (!mime) {
				errorList.add("Invalid file type. Please select another file.\n\n अमान्य फ़ाइल प्रकार |अन्य फाइल चुनें |");
				request.setAttribute("errorsList12",errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE_SP) {
					errorList.add("File size is Greater than 500 KB. Please select another file.\n\n फ़ाइल का आकार 500 KB से अधिक है| अन्य फाइल चुनें|");
					request.setAttribute("errorsList12",errorList);
				} else {
					
					String docName = "Hardware_Available."+fileExt;
					objServiceProviderForm.getObjServiceProviderDTO().setAffidavitHardwareDoc(uploadedFile.getFileName());
					if (((String)objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardwarePath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getThirteenthFileSP().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardwarePath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getThirteenthFileDR().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardwarePath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getThirteenthFileRenewal().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;	
					}
					else
					{
					forwardJsp=ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
					}
					
					} 
				}
			objServiceProviderDTO.setActionName("");
	}
		catch (Exception e) {
		e.printStackTrace();
	}
	}
	private void setUploadFile5(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			
			ArrayList	errorList = new ArrayList();
			//FormFile uploadedFile = eForm.getFilePhoto2();
			FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getPhotoImg();
			byte contents[] = uploadedFile.getFileData();
			objServiceProviderForm.getObjServiceProviderDTO().setPhotoImg1(contents);
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add("Invalid file selection. Please try again.\n\n अवैध फ़ाइल चयन |पुन: प्रयास करें |");
			}
			/*String fileExt = getFileExtension(uploadedFile.getFileName());
			AuditInspectionRule rule = new AuditInspectionRule();
			rule.validateFileTypeSPPhoto(fileExt);*/
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String photoSize = "(" + fileSize + "MB)";
			
			// new code 
			MIMECheck mimeCheck = new MIMECheck();
			String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
	       Boolean mime = mimeCheck.validateMIMEAndFileType(uploadedFile);  // common code to check mime type and validation.
	       
			
			if (!mime) {
				errorList.add("Invalid file type. Please select another file.\n\n अमान्य फ़ाइल प्रकार |अन्य फाइल चुनें |");
				request.setAttribute("errorsList5",errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE_SP) {
					errorList.add("File size is Greater than 500 KB. Please select another file.\n\n फ़ाइल का आकार 500 KB से अधिक है| अन्य फाइल चुनें|");
					request.setAttribute("errorsList5",errorList);
				} else {
					
					//String docName = "Photo."+fileExt;
					PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					String docName = "Photo."+pr.getValue("sp_photo_extension_param");
					objServiceProviderForm.getObjServiceProviderDTO().setPhotoImgDoc(uploadedFile.getFileName());
					
					if (((String)objServiceProviderForm.getObjServiceProviderDTO().getPhotoImgPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getSixthFileSP().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getPhotoImgPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getSixthFileDR().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getPhotoImgPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getSixthFileRenewal().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;	
					}
					else
					{
					forwardJsp=ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
					}
					
					} 
				}
			objServiceProviderDTO.setActionName("");
	}
		catch (Exception e) {
		e.printStackTrace();
	}
	}
	private void setUploadFile6(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			
			ArrayList	errorList = new ArrayList();
			//FormFile uploadedFile = eForm.getFilePhoto2();
			FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getCharacterCerti();
			byte contents[] = uploadedFile.getFileData();
			objServiceProviderForm.getObjServiceProviderDTO().setCharacterCerti1(contents);
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add("Invalid file selection. Please try again.\n\n अवैध फ़ाइल चयन |पुन: प्रयास करें |");
			}
			/*String fileExt = getFileExtension(uploadedFile.getFileName());
			AuditInspectionRule rule = new AuditInspectionRule();
			rule.validateFileTypeSP(fileExt);*/
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String photoSize = "(" + fileSize + "MB)";
			
			// new code 
			MIMECheck mimeCheck = new MIMECheck();
			String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
	       Boolean mime = mimeCheck.validateMIMEAndFileType(uploadedFile);  // common code to check mime type and validation.
	       
			if (!mime) {
				errorList.add("Invalid file type. Please select another file.\n\n अमान्य फ़ाइल प्रकार |अन्य फाइल चुनें |");
				request.setAttribute("errorsList6",errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE_SP) {
					errorList.add("File size is Greater than 500 KB. Please select another file.\n\n फ़ाइल का आकार 500 KB से अधिक है| अन्य फाइल चुनें|");
					request.setAttribute("errorsList6",errorList);
				} else {
					
					String docName = "Character_Certificate."+fileExt;
					objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiDoc(uploadedFile.getFileName());
					
					if (((String)objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getSeventhFileSP().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getSeventhFileDR().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getSeventhFileRenewal().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;	
					}
					else
					{
					forwardJsp=ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
					}
					
					} 
				}
			objServiceProviderDTO.setActionName("");
	}
		catch (Exception e) {
		e.printStackTrace();
	}
	}
	private void setUploadFile7(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			
			ArrayList	errorList = new ArrayList();
			//FormFile uploadedFile = eForm.getFilePhoto2();
			FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getAgeProof();
			byte contents[] = uploadedFile.getFileData();
			objServiceProviderForm.getObjServiceProviderDTO().setAgeProof1(contents);
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add("Invalid file selection. Please try again.\n\n अवैध फ़ाइल चयन |पुन: प्रयास करें |");
			}
			/*String fileExt = getFileExtension(uploadedFile.getFileName());
			AuditInspectionRule rule = new AuditInspectionRule();
			rule.validateFileTypeSP(fileExt);*/
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String photoSize = "(" + fileSize + "MB)";
			
			// new code 
			MIMECheck mimeCheck = new MIMECheck();
			String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
	        Boolean mime = mimeCheck.validateMIMEAndFileType(uploadedFile);  // common code to check mime type and validation.
	       
			if (!mime) {
				errorList.add("Invalid file type. Please select another file.\n\n अमान्य फ़ाइल प्रकार |अन्य फाइल चुनें |");
				request.setAttribute("errorsList7",errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE_SP) {
					errorList.add("File size is Greater than 500 KB. Please select another file.\n\n फ़ाइल का आकार 500 KB से अधिक है| अन्य फाइल चुनें|");
					request.setAttribute("errorsList7",errorList);
				} else {
					
					String docName = "Age_Proof."+fileExt;
					objServiceProviderForm.getObjServiceProviderDTO().setAgeProofDoc(uploadedFile.getFileName());
					
					if (((String)objServiceProviderForm.getObjServiceProviderDTO().getAgeProofPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getEighthFileSP().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getAgeProofPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getEighthFileDR().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getAgeProofPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getEighthFileRenewal().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;	
					}
					else
					{
					forwardJsp=ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
					}
					
					} 
				}
			objServiceProviderDTO.setActionName("");
	}
		catch (Exception e) {
		e.printStackTrace();
	}
	}
	/*private void setUploadFile8(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			
			ArrayList	errorList = new ArrayList();
			//FormFile uploadedFile = eForm.getFilePhoto2();
			FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCerti();
			byte contents[] = uploadedFile.getFileData();
			objServiceProviderForm.getObjServiceProviderDTO().setSolvencyCerti1(contents);
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add("Invalid file selection. Please try again.\n\n अवैध फ़ाइल चयन |पुन: प्रयास करें |");
			}
			String fileExt = getFileExtension(uploadedFile.getFileName());
			AuditInspectionRule rule = new AuditInspectionRule();
			rule.validateFileTypeSP(fileExt);
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String photoSize = "(" + fileSize + "MB)";
			if (rule.isError()) {
				errorList.add("Invalid file type. Please select another file.\n\n अमान्य फ़ाइल प्रकार |अन्य फाइल चुनें |");
				request.setAttribute("errorsList8",errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE_SP) {
					errorList.add("File size is Greater than 500 KB. Please select another file.\n\n फ़ाइल का आकार 500 KB से अधिक है| अन्य फाइल चुनें|");
					request.setAttribute("errorsList8",errorList);
				} else {
					
					String docName = "Solvency_Certificate."+fileExt;
					objServiceProviderForm.getObjServiceProviderDTO().setSolvencyCertiDoc(uploadedFile.getFileName());
					
					if (((String)objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCertiPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getNinthFileSP().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCertiPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getNinthFileDR().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCertiPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getNinthFileRenewal().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;	
					}
					else
					{
					forwardJsp=ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
					}
					
					} 
				}
			objServiceProviderDTO.setActionName("");
	}
		catch (Exception e) {
		e.printStackTrace();
	}
	}*/
	private void setUploadFile9(HttpServletRequest request,
			ServiceProviderForm objServiceProviderForm,
			ServiceProviderDTO objServiceProviderDTO) {
		try {
			
			ArrayList	errorList = new ArrayList();
			//FormFile uploadedFile = eForm.getFilePhoto2();
			FormFile uploadedFile = objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCerti();
			byte contents[] = uploadedFile.getFileData();
			objServiceProviderForm.getObjServiceProviderDTO().setPoliceCaseCerti1(contents);
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add("Invalid file selection. Please try again.\n\n अवैध फ़ाइल चयन |पुन: प्रयास करें |");
			}
			/*String fileExt = getFileExtension(uploadedFile.getFileName());
			AuditInspectionRule rule = new AuditInspectionRule();
			rule.validateFileTypeSP(fileExt);*/
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String photoSize = "(" + fileSize + "MB)";
			
			// new code 
			MIMECheck mimeCheck = new MIMECheck();
			String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
	        Boolean mime = mimeCheck.validateMIMEAndFileType(uploadedFile);  // common code to check mime type and validation.
	       
			if (!mime) {
				errorList.add("Invalid file type. Please select another file.\n\n अमान्य फ़ाइल प्रकार |अन्य फाइल चुनें |");
				request.setAttribute("errorsList9",errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE_SP) {
					errorList.add("File size is Greater than 500 KB. Please select another file.\n\n फ़ाइल का आकार 500 KB से अधिक है| अन्य फाइल चुनें|");
					request.setAttribute("errorsList9",errorList);
				} else {
					
					String docName = "Affidavit_Police_Case."+fileExt;
					objServiceProviderForm.getObjServiceProviderDTO().setPoliceCaseCertiDoc(uploadedFile.getFileName());
					
					if (((String)objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCertiPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getTenthFileSP().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.EDITABLE_PAGE_SP_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCertiPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getTenthFileDR().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.VIEW_PAGE_DR_AFTER_DASHBOARD_ID_CLICK;	
					}
					else if (((String)objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCertiPath() !=null) && (objServiceProviderForm.getObjServiceProviderDTO().getTenthFileRenewal().equalsIgnoreCase("1")))
					{
						forwardJsp=ServiceProviderConstant.RENEWAL_APP_FORM_SECOND_PAGE;	
					}
					else
					{
					forwardJsp=ServiceProviderConstant.FILL_SP_APPLICATION_DETLS;
					}
					
					} 
				}
			objServiceProviderDTO.setActionName("");
	}
		catch (Exception e) {
		e.printStackTrace();
	}
		
	}
		
	}