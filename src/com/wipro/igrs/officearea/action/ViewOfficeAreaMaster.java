package com.wipro.igrs.officearea.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.officearea.bd.IOfficeAreaMappingBD;
import com.wipro.igrs.officearea.bd.OfficeAreaMappingBD;
import com.wipro.igrs.officearea.form.ViewOfficeMasterForm;

public class ViewOfficeAreaMaster extends BaseAction {
    

	private IOfficeAreaMappingBD areaMappingBD = new OfficeAreaMappingBD();
    
    public ViewOfficeAreaMaster() {
    }
  

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		ViewOfficeMasterForm bean = (ViewOfficeMasterForm)form;
    	
    	List officeMappings = areaMappingBD.getOfficeMappings();
    	
    	bean.setOfficeMappingList(officeMappings);
    	
    	request.setAttribute("officeMappingList", officeMappings);
    	
    	return mapping.findForward("officeAreaMasterPage");
	}

}