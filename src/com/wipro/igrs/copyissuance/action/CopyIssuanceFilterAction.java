package com.wipro.igrs.copyissuance.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.copyissuance.bd.CertifiedBD;
import com.wipro.igrs.copyissuance.dto.CertifiedCopyDetailsDTO;
import com.wipro.igrs.copyissuance.form.CertifiedActionForm;
import com.wipro.igrs.login.action.LoginAction;
import com.wipro.igrs.noEncumbrance.bd.NoEncumBD;

public class CopyIssuanceFilterAction extends BaseAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wipro.igrs.baseaction.action.BaseAction#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse,
	 *      javax.servlet.http.HttpSession)
	 */
	private Logger logger = Logger.getLogger(LoginAction.class);


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		String languageLocale="hi";
		if(session.getAttribute("languageLocale")!=null){
			languageLocale=(String)session.getAttribute("languageLocale");
		}
		
		CertifiedActionForm copyForm = (CertifiedActionForm)form;
		CertifiedCopyDetailsDTO dto = new CertifiedCopyDetailsDTO();
		String FORWARD="";
		 // START Ramesh Added for dynamic population of dropdown
        String frwdName=request.getParameter("fwdName");
        NoEncumBD sdBD=new NoEncumBD();
        if(frwdName!=null&&(frwdName.equalsIgnoreCase("State")))
    	{
    		
    		dto.setStateList(sdBD.stateStackBD(dto.getCountryId(),languageLocale));
    		dto.setDistrictList(null);
    		FORWARD = new String("copyissuance");
    		// return mapping.findForward(FORWARD_JSP);
    	}
        
        //END Ramesh Added for dynamic population of dropdown

		return mapping.findForward(FORWARD);
	}

}
