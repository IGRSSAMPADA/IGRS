package com.wipro.igrs.partytypemaster.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.adminConfig.bd.PartyMasterBD;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.partytypemaster.bd.PartyTypeMasterBD;
import com.wipro.igrs.partytypemaster.dto.PartyTypeMasterDTO;
import com.wipro.igrs.partytypemaster.form.PartyTypeMasterForm;

public class PartyTypeMaster extends BaseAction {
    
	PartyTypeMasterBD partyTypeMasterBD = new PartyTypeMasterBD();

    
    public PartyTypeMaster() {
    	
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        // TODO: Write method body
    	
    	PartyTypeMasterForm bean = (PartyTypeMasterForm)form;
    	List allPartyTypeMaster = partyTypeMasterBD.viewAllPartyTypeMaster();
    	
    	request.setAttribute("ArrayOfPartyType", allPartyTypeMaster);
    	//System.out.println("view hiiiiiii"+((PartyTypeMasterDTO)allPartyTypeMaster.get(0)).getId());
    	return mapping.findForward("partyTypeMaster");
    }


}

