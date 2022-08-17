package com.wipro.igrs.partytypemaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.baseaction.constant.WebConstants;
import com.wipro.igrs.baseaction.dto.UserDTO;
import com.wipro.igrs.partytypemaster.bd.PartyTypeMasterBD;
import com.wipro.igrs.partytypemaster.dto.PartyTypeMasterDTO;
import com.wipro.igrs.partytypemaster.exception.PartyTypeAlreadyExistException;
import com.wipro.igrs.partytypemaster.form.PartyTypeMasterForm;

public class EditPartyTypeMasterToDB extends BaseAction {
    
	PartyTypeMasterBD partyTypeMasterBD = new PartyTypeMasterBD();

    
    public EditPartyTypeMasterToDB() {
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        
    	if(isCancelled(request)){
    		return mapping.findForward("partyTypeMaster");
    	}
    	ActionErrors errors = new ActionErrors();
    	PartyTypeMasterForm bean = (PartyTypeMasterForm)form;
    	
         // session = request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
    	//userId = "Mesooooooooooo";
    	
    	PartyTypeMasterDTO partyTypeMasterDTO = new PartyTypeMasterDTO();
    	partyTypeMasterDTO.setId(bean.getId());
    	partyTypeMasterDTO.setName(bean.getName());
    	partyTypeMasterDTO.setDesc(bean.getDesc());
    	partyTypeMasterDTO.setStatus(bean.getStatus());
    	partyTypeMasterDTO.setUpdateBy(userId);
    	partyTypeMasterDTO.setFunctionId(bean.getFuncVal());
    	partyTypeMasterDTO.setDependenceId(bean.getDependVal());
    	
    	boolean b = false;
		try {
			b = partyTypeMasterBD.editPartyTypeMaster(partyTypeMasterDTO,roleId,funId,userId);
		} catch (PartyTypeAlreadyExistException e) {
			System.out.println("hiiiiiiiii can u catch me :D:D");
			errors.add("PartyAlreadyExist", new ActionError("error.PartyAlreadyExist"));
			saveErrors(request, errors);
			return mapping.findForward("editPartyTypeMasterPage");	
		}
    	
    	if(b)
    		return mapping.findForward("editSuccessPartyPage");
    	else 
    		return mapping.findForward("errorPage");
    }

}