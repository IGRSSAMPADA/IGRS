package com.wipro.igrs.pot.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.pot.bd.PotBD;
import com.wipro.igrs.pot.form.PotFORM;
import com.wipro.igrs.reginit.constant.RegInitConstant;

public class PotUpdateCheck extends BaseAction
{
	 /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws Exception 
     */
	public ActionForward execute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response,HttpSession session)
	{
		
			
		PotFORM data1 = (PotFORM) form;
		String language=(String)session.getAttribute("languageLocale");
		data1.setLanguage(language);
		data1.setTransactionId("");
		data1.setDurationFrom("");
         data1.setDurationTo("");
		//String language=(String)session.getAttribute("languageLocale");
		//PotFORM formData = (PotFORM) form;
		//PotBD bd = new PotBD();
		//String languageLocale="hi";
		//if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			//bd.getDistrictId(request,language);
		//}else{
		//	bd.getDistrictId(request,language);
		//}
		
		//formData.setLanguage(language);
		
		
		return mapping.findForward("success");
	
}
}