package com.wipro.igrs.officearea.action;

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
import com.wipro.igrs.officearea.exception.AreaMappingAlreadyExistException;
import com.wipro.igrs.officearea.exception.OfficeAlreadyMappedException;
import com.wipro.igrs.officearea.form.OfficeMappingForm;

public class AddOfficeMappingAction extends BaseAction {
    

	private IOfficeAreaMappingBD areaMappingBD = new OfficeAreaMappingBD();
    
    public AddOfficeMappingAction() {
    }


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		if(isCancelled(request))
    		return mapping.findForward("viewOfficeAreaMaster");
    	
    	OfficeMappingForm bean = (OfficeMappingForm)form;
    //	session = request.getSession();
    	String roleId = (String)session.getAttribute("role");
    	String funId = (String)session.getAttribute("functionId");
    	String userId = (String)session.getAttribute("UserId");
        
        OfficeAreaMappingDTO areaMappingDTO = new OfficeAreaMappingDTO();
        areaMappingDTO.setOfficeId(bean.getOfficeId());
        areaMappingDTO.setDistrictId(bean.getDistrictId());
        areaMappingDTO.setTehsilId(bean.getTehsilId());
        areaMappingDTO.setWardPatwariId(bean.getWardPatwariId());
        areaMappingDTO.setMohallaVillageId(bean.getMohallaVillageId());
        
        
        
        try {
			areaMappingBD.addOfficeAreaMapping(areaMappingDTO,roleId,funId,userId);
		} catch (OfficeAlreadyMappedException ex) {
			ActionErrors errors = new ActionErrors();
			errors.add("officeError", new ActionError("error.office.alreadyExist") );
			saveErrors(request, errors);
			
			return mapping.findForward("viewAddOfficeAreaMapping");
			
		} catch (AreaMappingAlreadyExistException ex) {
			ActionErrors errors = new ActionErrors();
			errors.add("areaMappingError", new ActionError("error.areaMapping.alreadyExist") );
			saveErrors(request, errors);
			
			return mapping.findForward("viewAddOfficeAreaMapping");
		} 
		
        
        return mapping.findForward("viewOfficeAreaMaster");
	}

}