package com.wipro.igrs.pindelivery.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.pindelivery.bo.PinDeliveryBO;
import com.wipro.igrs.pindelivery.constant.CommonConstant;
import com.wipro.igrs.pindelivery.dto.PinDeliveryDTO;
import com.wipro.igrs.pindelivery.form.PinDeliveryForm;
import com.wipro.igrs.pindelivery.rule.PinDeliveryRule;
 
 
public class PinDeliveryAction extends BaseAction {

	
	private Logger logger = 
		(Logger) Logger.getLogger(PinDeliveryAction.class);
	String formName ="";
	String actionName="";
	HashMap map = null;
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
			HttpServletRequest request, 
			HttpServletResponse response,HttpSession session) 
		throws Exception { 
		
		
		String forwardJsp = new String("deliveryPage");
		logger.debug("Popup window start");
	//	HttpSession session = request.getSession();
		PinDeliveryBO bo = new PinDeliveryBO();
		if(form!=null) {
			PinDeliveryForm eForm = (PinDeliveryForm)form;
			String formName = eForm.getFormName();
			String actionName = eForm.getActionName();
			eForm.setPinDTOList(bo.getPINDeliveryDetails());
			
			String page = request.getParameter("param");
			if("create".equals(page)){
				forwardJsp = CommonConstant.PIN_DELIVERY_FORM;
			}
			//insert into Pin Delivery Details
			if(CommonConstant.PIN_DELIVERY_FORM.equals(formName)) {
				if(CommonConstant.PIN_DELIVERY_SAVE_ACTION.equals(actionName)) {
					String userID = "dr123";//(String) session.getAttribute("UserID");
					PinDeliveryDTO dto = eForm.getPinDTO();
					dto.setUserID(userID);
					boolean bol = 
						 bo.insertPINStatusDetails(dto);
					if(bol) {
						forwardJsp = CommonConstant.PIN_DELIVERY_CONFIRMATION;
					}else {
						PinDeliveryRule rule = new PinDeliveryRule();
						ArrayList errorList = rule.getInsertedPIN(bol,
											dto.getRegistrationID());
						if(rule.isError()) {
						    request.setAttribute("isError", "true");
							request.setAttribute("errorList", errorList);
						}
						forwardJsp = CommonConstant.PIN_DELIVERY_FORM;
					}
					
				}
				if(CommonConstant.PIN_DELIVERY_RESET_ACTION.equals(actionName)) {
					PinDeliveryDTO dto = eForm.getPinDTO();
					dto.setRegistrationID("");
					dto.setDeliveryComment("");
					eForm.setPinDTO(dto);
				}
			}
			
			
		}
		
		
		return mapping.findForward(forwardJsp);
	}
}
