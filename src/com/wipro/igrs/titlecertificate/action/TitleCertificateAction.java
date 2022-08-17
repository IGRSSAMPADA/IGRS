package com.wipro.igrs.titlecertificate.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.titlecertificate.bo.TitleCertificateBO;
import com.wipro.igrs.titlecertificate.form.TitleCertificateForm;


public class TitleCertificateAction extends BaseAction {

	private Logger logger = 
		(Logger) Logger.getLogger(TitleCertificateAction.class);
	String formName ="";
	String actionName="";
	HashMap map = null;
	
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
			HttpServletRequest request, 
			HttpServletResponse response,HttpSession session) 
		throws Exception { 
		
		String forwardJsp = new String("popupTitle");
		logger.debug("Popup window start");
		 
		TitleCertificateBO bo = new TitleCertificateBO();
		String registrationID = (String)session.getAttribute("regCompId");
		
		TitleCertificateForm eForm = (TitleCertificateForm) form;
		eForm.setTitleDTO(
				bo.getTitleCertificatePropertyDetails(registrationID));
		logger.debug("registrationID:-"+registrationID);
		eForm.setProprietorList(
				bo.getTitleCertificateOwnerDetails(registrationID));
		
		if(form!=null) {
			if(registrationID!=null) {
				
				forwardJsp =  "popupTitle";
			}
		}
		return mapping.findForward(forwardJsp);
	}
	
}
