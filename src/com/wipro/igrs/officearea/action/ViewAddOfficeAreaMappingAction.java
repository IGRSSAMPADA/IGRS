package com.wipro.igrs.officearea.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.officearea.bd.IOfficeAreaMappingBD;
import com.wipro.igrs.officearea.bd.OfficeAreaMappingBD;
import com.wipro.igrs.officearea.dto.OfficeAreaMappingDTO;
import com.wipro.igrs.officearea.form.OfficeMappingForm;
import com.wipro.igrs.officearea.form.ViewOfficeMappingForm;

public class ViewAddOfficeAreaMappingAction extends BaseAction {
    

	IOfficeAreaMappingBD areaMappingBD = new OfficeAreaMappingBD();

    
    public ViewAddOfficeAreaMappingAction() {
    }
    

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		OfficeMappingForm bean = (OfficeMappingForm)form;
    	
    	
       List officeList = areaMappingBD.getOffices();
       
       bean.setOfficeList(officeList);
       bean.setDistrictList(areaMappingBD.getDistricts());
       bean.setDistrictId("");
       
       return mapping.findForward("createOfficeAreaMappingPage");
	}

}