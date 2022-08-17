package com.wipro.igrs.expform76b.action;

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
import com.wipro.igrs.expform76b.bd.ExpForm76BBD;
import com.wipro.igrs.expform76b.dto.ExpForm76BSelectDTO;
import com.wipro.igrs.expform76b.form.ExpForm76BForm;

public class ExpForm76BSearchAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		ExpForm76BBD expBD=new ExpForm76BBD();
		ExpForm76BForm expForm =(ExpForm76BForm)form;
		ExpForm76BSelectDTO expDTO=new ExpForm76BSelectDTO();
		
		
		// to fill the select item that filled before by ajax
		ArrayList minors=expBD.getAllMinorHeadRelatedToSubMajor(expForm.getSelectedSubMajor());
		expForm.setMinors(minors);
		
		
		expDTO=expBD.getStatuseRelatedToEmployee(expForm.getEmployeeName());
		
		if(expDTO==null)
		{
			ActionErrors errors=new ActionErrors();
			ActionError error = new ActionError("noempname");
			errors.add("noempname",error);
			saveErrors(request, errors);
			expForm.setSearched(null);
			expForm.setEmployeeName("");
			return mapping.findForward("expform76bpage");
		}
		else
		{
			expForm.setEmployeeJobType(expDTO.getEmpStatuse());
			expForm.setPayWay(expDTO.getEmpSalary());
			expForm.setSearched("searched");
			return mapping.findForward("expform76bpage");
		}
	}

}
