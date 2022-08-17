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

public class EditDeedParamMasterAction extends BaseAction{
	
	private Logger logger = (Logger) Logger.getLogger(EditDeedParamMasterAction.class);
    ActionErrors error=new ActionErrors();
    
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception 
	{
	
		DeedParamMasterForm deedForm = (DeedParamMasterForm)form;
		String str;
		try{
		str=session.getAttribute("UserId").toString();
		}catch(Exception e)
		{
			str =new String("supervior");
		}

		DeedParamMasterBD deedtbd = new DeedParamMasterBD();
		/* edit deed param's master */
		String id=request.getParameter("deedId");
		DeedParamMasterDTO dto=deedtbd.getDeedparamById(id);	
		deedForm.setParamid(id); 
		deedForm.setParamname(dto.getParamname());
		deedForm.setParamstatus(dto.getParamstatus());
		deedForm.setParamDesc(dto.getParamDesc());
		deedForm.setUpdatedby(str);
			return mapping.findForward("edit");

 }
}
