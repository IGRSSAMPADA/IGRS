package com.wipro.igrs.religionmaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.religionmaster.bd.ReligionBD;
import com.wipro.igrs.religionmaster.dto.ReligionDTO;
import com.wipro.igrs.religionmaster.form.ReligionForm;

public class PreEditReligionAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		ReligionDTO dto = new ReligionDTO();
		ReligionBD religionbd = new ReligionBD();
		ArrayList religionList = religionbd.getReligionList();
		ReligionForm religionForm = (ReligionForm) form;
		
		String religionId=request.getParameter("religionId");
		dto=religionbd.getReligionId(religionId);
		
		religionForm.setReligionId(religionId);
		religionForm.setOldName(dto.getReligionName());
		religionForm.setReligionName(dto.getReligionName());
		religionForm.setReligionDesc(dto.getReligionDesc());
		religionForm.setReligionStatus(dto.getReligionStatus());
		//System.out.println("da");
		return mapping.findForward("updatereligionmaster");
	}

}
