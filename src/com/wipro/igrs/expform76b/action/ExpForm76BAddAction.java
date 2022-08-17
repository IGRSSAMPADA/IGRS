package com.wipro.igrs.expform76b.action;

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

public class ExpForm76BAddAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		
		ExpForm76BForm expForm=(ExpForm76BForm)form;
		ExpForm76BSelectDTO expDTO=new ExpForm76BSelectDTO();
		ExpForm76BBD expBD=new ExpForm76BBD();
		String createdBy=(String)session.getAttribute("UserId");
		
		
		expDTO.setBillNumber(expForm.getBillNumber());
		expDTO.setDistrictID(expForm.getSelectedDistrict());
		expDTO.setFiscalYearID(expForm.getSelectedYFiscal());
		expDTO.setFiscalMonthID(expForm.getSelectedMFiscal());
		expDTO.setAccountHeadID(expForm.getSelectedAccount());
		expDTO.setMajorHeadID(expForm.getSelectedMajor());
		expDTO.setSubMajorID(expForm.getSelectedSubMajor());
		expDTO.setMinorHeadID(expForm.getSelectedMinor());
		expDTO.setGrantNumber(expForm.getGrantNumber());
		expDTO.setEmpID(expForm.getEmployeeName());
		expDTO.setAmountRequired(expForm.getAmountRequiredforPayment());
		expDTO.setSuretyTaken(expForm.getSuretyTaken());
		
		expDTO.setEmployeeJobType(expForm.getEmployeeJobType()); 
		expDTO.setPayWay(expForm.getPayWay());
		
		
		expDTO.setCreatedBy(createdBy);
		
		if(expBD.addnewDTLS(expDTO))
		{
			return mapping.findForward("successpage");
		}
		else
		{
			ActionErrors errors=new ActionErrors();
			ActionError error = new ActionError("nodatasaved");
			errors.add("nodatasaved",error);
			saveErrors(request, errors);
			return mapping.findForward("expform76bpage");
		}
		
	}

}
