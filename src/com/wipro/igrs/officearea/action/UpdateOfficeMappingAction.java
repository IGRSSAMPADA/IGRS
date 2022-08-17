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
import com.wipro.igrs.officearea.exception.OfficeAreaMappingNotFoundException;
import com.wipro.igrs.officearea.form.OfficeMappingForm;

public class UpdateOfficeMappingAction extends BaseAction {
    

	private IOfficeAreaMappingBD areaMappingBD = new OfficeAreaMappingBD();
    
    public UpdateOfficeMappingAction() {
    }
    
 

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		if(isCancelled(request))
    		return mapping.findForward("viewOfficeAreaMaster");
    	
    	OfficeMappingForm bean = (OfficeMappingForm)form;
        
        OfficeAreaMappingDTO areaMappingDTO = new OfficeAreaMappingDTO();
        areaMappingDTO.setOfficeId(bean.getOfficeId());
        areaMappingDTO.setDistrictId(bean.getDistrictId());
        areaMappingDTO.setTehsilId(bean.getTehsilId());
        areaMappingDTO.setWardPatwariId(bean.getWardPatwariId());
        areaMappingDTO.setMohallaVillageId(bean.getMohallaVillageId());
       // session = request.getSession();
        String roleId = (String)session.getAttribute("role");
        String funId = (String)session.getAttribute("functionId");
        String userId = (String)session.getAttribute("UserId");
        
        try {
			areaMappingBD.editOfficeAreaMapping(areaMappingDTO,roleId,funId,userId);
		} catch (AreaMappingAlreadyExistException ex) {
			ActionErrors errors = new ActionErrors();
			errors.add("areaMappingError", new ActionError("error.areaMapping.alreadyExist") );
			saveErrors(request, errors);
			
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
        
        return mapping.findForward("viewOfficeAreaMaster");
	}

}