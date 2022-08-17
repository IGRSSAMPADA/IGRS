package com.wipro.igrs.deedparammaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.deedparammaster.bd.DeedParamMasterBD;
import com.wipro.igrs.deedparammaster.dto.DeedParamMasterDTO;
import com.wipro.igrs.deedparammaster.exception.UserNameAlreadyExistException;
import com.wipro.igrs.deedparammaster.form.DeedParamMasterForm;

public class UpdateDeedParamMasterAction extends BaseAction{
	
	private Logger logger = (Logger) Logger.getLogger(UpdateDeedParamMasterAction.class);
    ActionErrors error=new ActionErrors();
    
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception 
	{
	
		
		DeedParamMasterForm deedForm = (DeedParamMasterForm)form;
		DeedParamMasterBD deedparambd = new DeedParamMasterBD();
		String str;
		try{
			str=session.getAttribute("UserId").toString();
			}catch(Exception e)
			{
				str =new String("supervior");
			}
		

		/* update deed param's master */
		DeedParamMasterDTO dto=new DeedParamMasterDTO();
        dto.setParamid(deedForm.getParamid());
		dto.setParamname(deedForm.getParamname());
		dto.setParamstatus(deedForm.getParamstatus());
		dto.setParamDesc(deedForm.getParamDesc());
		dto.setUpdatedby(str);
		try{
			deedparambd.updateDeedParamDetails(dto);
			System.out.println("i am here .....");
		}catch(Exception e){
			error.add("nameerror",new ActionError("errors.deedparamerror"));
			saveErrors(request, error);
			return mapping.findForward("add2");
		}
			return mapping.findForward("add");
		}


}
