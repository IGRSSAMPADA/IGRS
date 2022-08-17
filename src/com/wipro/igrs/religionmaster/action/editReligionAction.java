package com.wipro.igrs.religionmaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.religionmaster.bd.ReligionBD;
import com.wipro.igrs.religionmaster.dto.ReligionDTO;
import com.wipro.igrs.religionmaster.exception.ReligionAlreadyExistsException;
import com.wipro.igrs.religionmaster.form.ReligionForm;

public class editReligionAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		ReligionDTO dto = new ReligionDTO();
		ReligionBD religionbd = new ReligionBD();
		ArrayList religionList = religionbd.getReligionList();
		ReligionForm religionForm = (ReligionForm) form;
		ActionErrors errors=new ActionErrors();
		//session = request.getSession();
		String roleId = (String)session.getAttribute("role");
		String funId = (String)session.getAttribute("functionId");
		String userId = (String)session.getAttribute("UserId");
		/* Update religion master */
		
		dto.setReligionId(religionForm.getReligionId());
		dto.setOldName(religionForm.getOldName());
		dto.setReligionName(religionForm.getReligionName());
		dto.setReligionDesc(religionForm.getReligionDesc());
		dto.setReligionStatus(religionForm.getReligionStatus());
		dto.setUpdateBy((String)session.getAttribute("UserId"));
		
		//dto.setUpdateBy("Citezen");
		try
		{
		religionbd.updateReligionMaster(dto,roleId,funId,userId);
		
		}
		catch(ReligionAlreadyExistsException e)
		{
			errors.add("religionNameAlreadyExist",new ActionError("error.religionNameAlreadyExist"));
			saveErrors(request, errors);
			 return mapping.findForward("failedupdate");
		}
		
		return mapping.findForward("successupdate");
		
	}

}
