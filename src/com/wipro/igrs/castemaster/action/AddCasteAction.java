package com.wipro.igrs.castemaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.taglib.html.ErrorsTag;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.baseaction.constant.WebConstants;
import com.wipro.igrs.baseaction.dto.UserDTO;
import com.wipro.igrs.castemaster.bd.CasteBD;
import com.wipro.igrs.castemaster.dto.CasteDTO;
import com.wipro.igrs.castemaster.exceptions.CasteAlreadyExistException;
import com.wipro.igrs.castemaster.form.CasteForm;
import com.wipro.igrs.common.ACLConstants;
import com.wipro.igrs.exception.IGRSException;
public class AddCasteAction extends BaseAction {
    

	CasteBD casteBD;

    
    public AddCasteAction() {
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
		if( isCancelled(request))
			return mapping.findForward("viewCastMaster");
			
		CasteForm bean = (CasteForm)form;
		  session = request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");

		String userId1 = (String)session.getAttribute("UserId");

		if(userId1 == null)
	  	throw new IGRSException("User is not logged in");

		CasteDTO casteDTO = new CasteDTO();
	  casteDTO.setCreatedBy(userId1);
//	  casteDTO.setCreatedBy("hos");
	  casteDTO.setName(bean.getCasteName());
	  casteDTO.setCategory_id(bean.getCategoryId());
	  
	  try {
	  	casteBD.addCaste(casteDTO,roleId,funId,userId);
	  } catch (CasteAlreadyExistException ex) {      
		  
		  ActionErrors actionErrors = new ActionErrors();
		  actionErrors.add("nameError", new ActionError("error.casteNameAlreadyExists"));
	  
	  saveErrors(request, actionErrors);
	  
	  return mapping.findForward("viewAddCastePage");
	  }
	  
	  return mapping.findForward("successAdd");
	}

}