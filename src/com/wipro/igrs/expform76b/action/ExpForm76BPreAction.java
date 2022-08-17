package com.wipro.igrs.expform76b.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.expform76b.bd.ExpForm76BBD;
import com.wipro.igrs.expform76b.dto.ExpForm76BSelectDTO;
import com.wipro.igrs.expform76b.form.ExpForm76BForm;

public class ExpForm76BPreAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		ExpForm76BForm expForm=(ExpForm76BForm)form;
		ExpForm76BBD expBD=new ExpForm76BBD();
		
		
		ArrayList returned=expBD.getAllData();
		
		
		expForm.setBillNumber(((ExpForm76BSelectDTO)((ArrayList)returned.get(6)).get(0)).getBillNumber());
		expForm.setDistricts((ArrayList)returned.get(0));
		expForm.setFiscalYear((ArrayList)returned.get(1));
		expForm.setFiscalMonth((ArrayList)returned.get(2));
		expForm.setMajors((ArrayList)returned.get(3));
		expForm.setSubMajors((ArrayList)returned.get(4));
		expForm.setAccounts((ArrayList)returned.get(5));
		expForm.setMinors(new ArrayList()); // to make empty select element
		
		
		// to avoid caching of browsers 
		expForm.setSelectedDistrict(null);
		expForm.setSelectedMajor(null);
		expForm.setSelectedMFiscal(null);
		expForm.setSelectedMinor(null);
		expForm.setSelectedSubMajor(null);
		expForm.setSelectedYFiscal(null);
		expForm.setSelectedSubMajor(null);
		expForm.setSelectedAccount(null);
		expForm.setSuretyTaken("Y");
		expForm.setEmployeeName("");
		expForm.setSearched(null);
		expForm.setAmountRequiredforPayment(null);
		expForm.setGrantNumber(null);
		
		
		return mapping.findForward("expform76bpage");
			
	}

	
	
	
}
