package com.wipro.igrs.partytypemaster.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.partytypemaster.bd.PartyTypeMasterBD;
import com.wipro.igrs.partytypemaster.dto.PartyTypeMasterDTO;
import com.wipro.igrs.partytypemaster.form.PartyTypeMasterForm;


public class EditPartyTypeMaster extends BaseAction {
    
	PartyTypeMasterBD partyTypeMasterBD = new PartyTypeMasterBD();

    
    public EditPartyTypeMaster() {
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        
    	PartyTypeMasterForm bean = (PartyTypeMasterForm)form;
    	
    	String partyId = (String)request.getParameter("partyId");
    	//System.out.println(partyId);
    	PartyTypeMasterDTO partyTypeMasterDTO = partyTypeMasterBD.getPartyTypeMasterById(partyId);
    	bean.setId(partyTypeMasterDTO.getId());
    	bean.setName(partyTypeMasterDTO.getName());
    	bean.setDesc(partyTypeMasterDTO.getDesc());
    	bean.setStatus(partyTypeMasterDTO.getStatus());
    	bean.setFuncVal(partyTypeMasterDTO.getFunctionId());
    	System.out.println("hiiiiiiiii " + partyTypeMasterDTO.getFunctionName());
    	bean.setDependVal(partyTypeMasterDTO.getDependenceId());
    	List allFunctions = partyTypeMasterBD.getAllFunctions();
    	List allDependences = partyTypeMasterBD.getAllDependences();
    	bean.setAllFunctions(allFunctions);
    	bean.setAllDependences(allDependences);
    	
       return mapping.findForward("editPartyTypeMasterPage");
    }

}