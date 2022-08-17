package com.wipro.igrs.castemaster.action;

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
import com.wipro.igrs.castemaster.bd.CasteBD;
import com.wipro.igrs.castemaster.dto.CasteDTO;
import com.wipro.igrs.castemaster.exceptions.CasteAlreadyExistException;
import com.wipro.igrs.castemaster.form.CasteForm;
import com.wipro.igrs.exception.IGRSException;

public class EditCasteAction extends BaseAction {
    

	CasteBD casteBD;

    
    public EditCasteAction() {
    	try {
			casteBD = new CasteBD();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
CasteForm bean = (CasteForm) form;
        
        if(isCancelled(request))
        	return mapping.findForward("viewCastMaster");;
           session = request.getSession();
  	      String roleId = (String)session.getAttribute("role");
  		  String funId = (String)session.getAttribute("functionId");
   		  String userId = (String)session.getAttribute("UserId");
        	
        	
//      UserDTO userDTO = (UserDTO)session.getAttribute(WebConstants.SES_USER_DO);
//      
//      
   		if(userId == null)
   			throw new IGRSException("User is not logged in");
        
        CasteDTO casteDTO = new CasteDTO();
        
       casteDTO.setUpdatedBy(userId);
        

//      casteDTO.setUpdatedBy("hos");
        
        casteDTO.setId(bean.getCasteId());
        casteDTO.setName(bean.getCasteName());
        casteDTO.setStatus(bean.getStatus());
        casteDTO.setCategory_id(bean.getCategoryId());
        
		
        
		try {
//			casteBD.updateCaste(casteDTO, "hos");
			casteBD.updateCaste(casteDTO,userId,roleId,funId);
		} catch (CasteAlreadyExistException ex) {
			
			ActionErrors actionErrors = new ActionErrors();
			actionErrors.add("nameError", new ActionError("error.casteNameAlreadyExists"));
  
			saveErrors(request, actionErrors);
			return mapping.findForward("viewEditCastePage");
		}
		
        
        return mapping.findForward("successUpdate");
	}

}