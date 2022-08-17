package com.wipro.igrs.deedparammaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.deedparammaster.bd.DeedParamMasterBD;
import com.wipro.igrs.deedparammaster.dto.DeedParamMasterDTO;
import com.wipro.igrs.deedparammaster.form.DeedParamMasterForm;

public class DeleteDeedParamMasterAction extends BaseAction{


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		DeedParamMasterForm deedForm = (DeedParamMasterForm)form;
		DeedParamMasterBD deedbd = new DeedParamMasterBD();
		/* delete deed param's master */
		String [] str=deedForm.getSelectedDeedParam();
		DeedParamMasterDTO dto=new DeedParamMasterDTO();
		for (int i=0;i<deedForm.getSelectedDeedParam().length;i++)
		{
			dto.setParamid(str[i]);
			deedbd.deleteDeedParamDetails(dto);
		}
			return mapping.findForward("delete");
		}
		

	
	}
	
