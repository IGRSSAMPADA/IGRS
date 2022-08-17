package com.wipro.igrs.expmptc28.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.expmptc28.bd.ExpMPTC28BD;
import com.wipro.igrs.expmptc28.dto.EmpDetailsDTO;
import com.wipro.igrs.expmptc28.form.ExpMPTC28Form;

public class UpdateExpMPTC28 extends BaseAction{
	private ExpMPTC28BD expMPTC28BD = new ExpMPTC28BD();
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		ExpMPTC28Form bean = (ExpMPTC28Form) form;
		EmpDetailsDTO details = expMPTC28BD.getEmpDetails(bean.getEmpId());
		bean.setEmpDetails(details);
		return mapping.findForward("expmptc28form");
	}

}
