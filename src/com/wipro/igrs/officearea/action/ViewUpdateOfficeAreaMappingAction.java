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
import com.wipro.igrs.officearea.dto.OfficeAreaMappingDTO;
import com.wipro.igrs.officearea.form.OfficeMappingForm;

public class ViewUpdateOfficeAreaMappingAction extends BaseAction {
    

	private IOfficeAreaMappingBD areaMappingBD = new OfficeAreaMappingBD();
    
    public ViewUpdateOfficeAreaMappingAction() {
    }


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		OfficeMappingForm bean = (OfficeMappingForm)form;
	   	
    	OfficeAreaMappingDTO officeMapping = areaMappingBD.getByOfficeId(bean.getOfficeId());
    	
    	
    	bean.setTehsilList( areaMappingBD.getTehsils(officeMapping.getDistrictId()));
		bean.setWardPatwariList( areaMappingBD.getWardPatwaris(officeMapping.getTehsilId()));
		bean.setMohallaVillageList(areaMappingBD.getMohalaVilliges(officeMapping.getWardPatwariId()));
		
		bean.setOfficeId(officeMapping.getOfficeId());
		bean.setOfficeName(officeMapping.getOfficeName());
		bean.setDistrictId(officeMapping.getDistrictId());
		bean.setTehsilId(officeMapping.getTehsilId());
		bean.setWardPatwariId(officeMapping.getWardPatwariId());
		bean.setMohallaVillageId(officeMapping.getMohallaVillageId());
    	
        bean.setDistrictList(areaMappingBD.getDistricts());
        
        return mapping.findForward("editOfficeAreaMappingPage");
	}

}