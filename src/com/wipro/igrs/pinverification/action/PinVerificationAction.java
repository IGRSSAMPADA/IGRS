package com.wipro.igrs.pinverification.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.UserRegistration.action.CryptoLibrary;
import com.wipro.igrs.pinverification.bo.PinVerificationBO;
import com.wipro.igrs.pinverification.constant.CommonConstant;
import com.wipro.igrs.pinverification.dto.PinVerificationDTO;
import com.wipro.igrs.pinverification.form.PinVerificationForm;
import com.wipro.igrs.pinverification.rule.PinVerificationRule;

 
public class PinVerificationAction extends Action {

	
	private Logger logger = 
		(Logger) Logger.getLogger(PinVerificationAction.class);
	String formName ="";
	String actionName="";
	HashMap map = null;
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
			HttpServletRequest request, 
			HttpServletResponse response ) 
		throws Exception { 
		
		
		String forwardJsp = new String("pinVerification");
		logger.debug("Popup window start");
	 	HttpSession session = request.getSession();
		
		if(form!=null) {
			PinVerificationForm eForm = (PinVerificationForm) form;
			PinVerificationDTO dto = eForm.getPinVerificationDTO();
			PinVerificationBO bo = new PinVerificationBO();
			String propertyID = "PROP12345";//(String)session.getAttribute("PropertyID");
			String nextPage = (String) session.getAttribute("nextPage");
			String prevPage = (String) session.getAttribute("prevPage");
			//PropertyID should be 
			//<propertyid-1>~<propertyid-2>~<propertyid-3>~etc. 
			//in this format
			eForm.setListPropertyID(bo.getPropertyList(propertyID));
			
			/*if(propertyID !=null) {
				
				forwardJsp = "pinVerification";
			}*/
			String formName = eForm.getFormName();
			String actionName = eForm.getActionName();
			if(CommonConstant.PIN_VERIFICATION_PAGE.equals(formName)){
				if(CommonConstant.PIN_COMBO_ACTION.equals(actionName)) {
					 
						String[] property = dto.getPropertyID() != null && 
									!"".equals(dto.getPropertyID()) ? 
								dto.getPropertyID().split("~") : null;
					   
						logger.debug("dto.getPropertyID():-"+dto.getPropertyID());
					   
						PinVerificationDTO dtoReturn = 
							bo.getPropertyAddressDetails(property !=null ? property[1] :"");
					
						dtoReturn.setPropertyID(dto.getPropertyID());
						dtoReturn.setPropertyName(property[0]);
						logger.debug("inside combo action"+dto.getPropertyID()); 
						eForm.setPinVerificationDTO(dtoReturn);
						eForm.setListUser(bo.getUserDetails(property[1]));
						forwardJsp = CommonConstant.PIN_VERIFICATION_PAGE;
					 
				}
				if(CommonConstant.PIN_VERIFY_ACTION.equals(actionName)) {
					 String hdnPinNumber = eForm.getHdnPinNumber().trim();
					 ArrayList list = new ArrayList();
					 CryptoLibrary crypt = new CryptoLibrary();
					 if(hdnPinNumber !=null) {
						 String[] hdnParamPin = hdnPinNumber.split(",");
						 logger.debug("hdnPinNumber:-"+hdnPinNumber+":"+hdnParamPin.length);
						 if(hdnParamPin !=null) {
							 for(int i = 0; i<hdnParamPin.length;i++) {
								 if(!hdnParamPin[i].trim().equals("")) {
									 String[] hdnPinUserID = hdnParamPin[i].split("~");
									 PinVerificationDTO dtoList = new PinVerificationDTO();
									// logger.debug("PinNumber:-"+hdnPinUserID[0]+":"+hdnPinUserID[1]);
									 if(hdnPinUserID!=null){
										 String[] userDetail = hdnPinUserID!=null ?
												 			hdnPinUserID[0].split("-") : null;
										 if(userDetail!=null) {
											 dtoList.setUserID(userDetail[0]);
											 dtoList.setUserName(userDetail[1]);
										 }
										 
										 dtoList.setPinNumber(crypt.encrypt(hdnPinUserID[1]));
									 }
									 String[] property = dto.getPropertyID() != null ? 
												dto.getPropertyID().split("~") : null;
									 if(property !=null){			
										 dtoList.setPropertyID(property[1]);
										 dtoList.setPropertyName(property[0]);
									 }
									 list.add(dtoList);
								 }
							 }
							 
							 
							 PinVerificationRule rule = new PinVerificationRule();
							 ArrayList errorList = rule.getVerifiedPIN(list);
							 logger.debug("isError:-"+rule.isError());
							 //rule.setError(false);
							 if(rule.isError()) {
								 request.setAttribute("isError", "true");
								 request.setAttribute("errorList", errorList);
							 }else {
								request.setAttribute("isError", "false");
								map = eForm.getMapProperty();
								 String[] property = dto.getPropertyID() != null ? 
										dto.getPropertyID().split("~") : null;
								PinVerificationDTO dtoReturn = new PinVerificationDTO();
								dtoReturn.setPinVerified("Verified");
								if(property!=null) {
									dtoReturn.setPropertyID(property[0]);
									map.put(property[1],dtoReturn);
								}
								eForm.setMapProperty(map);
							 }
						 }
					 }
					 
					 
					 forwardJsp = CommonConstant.PIN_VERIFICATION_PAGE;
				}
				if(CommonConstant.PIN_VERIFY_NEXT.equals(actionName)) {
					HashMap verifyMap = eForm.getMapProperty();
					ArrayList listProperty = eForm.getListPropertyID();
					PinVerificationRule rule = new PinVerificationRule();
					ArrayList errorList = 
						rule.checkedVerifiedPIN(listProperty, verifyMap);
					logger.debug("isError:-"+rule.isError());
					if(rule.isError()) {
						 request.setAttribute("isError", "true");
						 request.setAttribute("errorList", errorList);
						 forwardJsp = CommonConstant.PIN_VERIFICATION_PAGE;
					 }else {
						  forwardJsp = nextPage;//CommonConstant.PIN_VERIFICATION_PAGE; forward to payment page
					 }
				}
				if(CommonConstant.PIN_VERIFY_PREVIOUS.equals(actionName)) {
					 
					forwardJsp = prevPage;//CommonConstant.PIN_VERIFICATION_PAGE; forward to payment page
					 
				}
				if(CommonConstant.PIN_VERIFY_RESET.equals(actionName)){
					eForm.setMapProperty(new HashMap());
					eForm.setPinVerificationDTO(new PinVerificationDTO());
					eForm.setListUser(new ArrayList());
					forwardJsp = CommonConstant.PIN_VERIFICATION_PAGE;
				}
				/*if(CommonConstant.PIN_VERIFY_CANCEL.equals(actionName)){
					eForm.setMapProperty(new HashMap());
					eForm.setPinVerificationDTO(new PinVerificationDTO());
					eForm.setListUser(new ArrayList());
					forwardJsp = CommonConstant.PIN_VERIFICATION_PAGE;
				}*/
			}
			
		}
		
		
		return mapping.findForward(forwardJsp);
	}
}
