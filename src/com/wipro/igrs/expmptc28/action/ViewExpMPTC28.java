package com.wipro.igrs.expmptc28.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.expmptc28.bd.ExpMPTC28BD;
import com.wipro.igrs.expmptc28.form.ExpMPTC28Form;

public class ViewExpMPTC28 extends BaseAction{
	
	private ExpMPTC28BD expMPTC28BD = new ExpMPTC28BD();

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		ExpMPTC28Form bean = (ExpMPTC28Form) form;
		bean.setVoucherNumber(expMPTC28BD.getVoucherNumber());
		bean.setAllDistricts(expMPTC28BD.getAllDistricts());
		bean.setEmployeesIds(expMPTC28BD.getAllEmployees());
		
		bean.setDistrictId(null);
		bean.setEmpId(null);
		bean.setDepartureFrom(null);
		bean.setDepartureDate(null);
		bean.setArrivalTo(null);
		bean.setArrivalDate(null);
		bean.setPurposeOfJourney(null);
		bean.setExpenseDetails(null);
		bean.setExpenseAmount(null);
		bean.setModeOfTravel("Bus");
		bean.setTravelClass(null);
		bean.setNoKilometer(null);
		bean.setFaresInCaseTransfer(null);
		bean.setDaAmount(null);
		bean.setHqExtendDa(null);
		bean.setHqDaAmount(null);
		bean.setJourneyHours(null);
		bean.setJourneyExtendDa(null);
		bean.setJourneyAmount(null);
		bean.setHaltHours(null);
		bean.setHaltExtendDa(null);
		bean.setHaltAmount(null);
		bean.setSpecialHaltExtendDa(null);
		bean.setSpecialHaltAmount(null);
		bean.setEachLineTotal(null);
		bean.setRemarks(null);
		
		return mapping.findForward("expmptc28form");
	}

}
