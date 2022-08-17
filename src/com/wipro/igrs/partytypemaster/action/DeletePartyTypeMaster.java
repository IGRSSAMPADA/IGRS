package com.wipro.igrs.partytypemaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.baseaction.constant.WebConstants;
import com.wipro.igrs.baseaction.dto.UserDTO;
import com.wipro.igrs.partytypemaster.bd.PartyTypeMasterBD;
import com.wipro.igrs.partytypemaster.form.PartyTypeMasterForm;

public class DeletePartyTypeMaster extends BaseAction {
    
	PartyTypeMasterBD partyTypeMasterBD = new PartyTypeMasterBD();
	
    
    public DeletePartyTypeMaster() {
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        // TODO: Write method body
    	boolean b = false;
    	
    	UserDTO userObj = (UserDTO)request.getAttribute(WebConstants.SES_USER_DO);
    	//String userId =userObj.getUserId(); // to be handled
    	String userId1 = "Mesooooooooooo";
    	
    	PartyTypeMasterForm bean = (PartyTypeMasterForm) form;
    	String[] ids = bean.getSelections();
    	//  session = request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
    	//System.out.println("hiiiiiiiiiiiiiiiiii");
    	for (int i = 0; i < ids.length; i++) {
			System.out.println("id= " + ids[i]);
		}
    	
    	b = partyTypeMasterBD.deleteAllPartyTypeMaster(ids,userId1,roleId,funId,userId);
    	
    	
    	
    	if(b)
    		return mapping.findForward("partyTypeMaster");
    	else 
    		return mapping.findForward("errorPage");
    }

}