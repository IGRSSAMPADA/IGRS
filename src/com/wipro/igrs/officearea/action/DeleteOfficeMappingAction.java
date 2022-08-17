package com.wipro.igrs.officearea.action;

import java.util.ArrayList;
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
import com.wipro.igrs.officearea.form.DeleteOfficeMappingForm;

public class DeleteOfficeMappingAction extends BaseAction {
    

	private IOfficeAreaMappingBD areaMappingBD = new OfficeAreaMappingBD();
    
    public DeleteOfficeMappingAction() {
    }


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		//session = request.getSession();
		String roleId = (String)session.getAttribute("role");
		String funId = (String)session.getAttribute("functionId");
		String userId = (String)session.getAttribute("UserId");
		
		DeleteOfficeMappingForm bean = (DeleteOfficeMappingForm)form;
    	
    	String[] officeList = bean.getOfficeList();
    	List officeMappingList = new ArrayList(officeList.length);
    	for(int i=0; i<officeList.length; i++) {
    		OfficeAreaMappingDTO areaMappingDTO = new OfficeAreaMappingDTO();
    		areaMappingDTO.setOfficeId( officeList[i] );
    		officeMappingList.add(areaMappingDTO);
    	}
    	
    	areaMappingBD.deleteOfficeAreaMapping(officeMappingList,roleId,funId,userId);
    	
    	return mapping.findForward("officeAreaMasterPage");
	}

}