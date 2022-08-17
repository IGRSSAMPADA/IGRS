package com.wipro.igrs.expmptc28.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.expmptc28.bd.ExpMPTC28BD;
import com.wipro.igrs.expmptc28.dto.ExpMPTC28DTO;
import com.wipro.igrs.expmptc28.form.ExpMPTC28Form;

public class SubmitExpMPTC28 extends BaseAction {
	
	private Logger logger = (Logger) Logger.getLogger(SubmitExpMPTC28.class);

	private ExpMPTC28BD expMPTC28BD = new ExpMPTC28BD();
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		ExpMPTC28Form bean = (ExpMPTC28Form) form;
		ExpMPTC28DTO dto = toExpMPTC28DTO(bean);
		String user = (String) session.getAttribute("UserId");
		dto.setEnteredBy(user);
		expMPTC28BD.addExpMPTC28(dto);
		return mapping.findForward("success");
	}
	
	private ExpMPTC28DTO toExpMPTC28DTO(ExpMPTC28Form bean) {
		SimpleDateFormat sourceDateFormat = new SimpleDateFormat("dd/MM/yy");
		SimpleDateFormat targetDateFormat = new SimpleDateFormat("dd-MMM-yy");
		
		ExpMPTC28DTO result = new ExpMPTC28DTO();
		
		result.setVoucherNumber(bean.getVoucherNumber());
		result.setDistrictId(bean.getDistrictId());
		result.setEmpId(bean.getEmpId());
		result.setDepartureFrom(bean.getDepartureFrom());
		
		//
		String departureDate = null;
		
		if(bean.getDepartureDate() != null && bean.getDepartureDate() != "") {
			try {
				Date date = sourceDateFormat.parse(bean.getDepartureDate());
				departureDate = targetDateFormat.format(date);
			} catch (ParseException e) {
				logger.error(e);
			}
		}
		//result.setDepartureDate(bean.getDepartureDate());
		result.setDepartureDate(departureDate);
		
		//
		result.setArrivalTo(bean.getArrivalTo());
		//
		String arrivalDate = null;
		
		if(bean.getArrivalDate() != null && bean.getArrivalDate() != "") {
			try {
				Date date = sourceDateFormat.parse(bean.getArrivalDate());
				arrivalDate = targetDateFormat.format(date);
			} catch (ParseException e) {
				logger.error(e);
			}
		}
		//result.setArrivalDate(bean.getArrivalDate());
		result.setArrivalDate(arrivalDate);
		
		result.setPurposeOfJourney(bean.getPurposeOfJourney());
		result.setExpenseDetails(bean.getExpenseDetails());
		result.setExpenseAmount(bean.getExpenseAmount());
		result.setModeOfTravel(bean.getModeOfTravel());
		result.setTravelClass(bean.getTravelClass());
		result.setNoKilometer(bean.getNoKilometer());
		result.setFaresInCaseTransfer(bean.getFaresInCaseTransfer());
		result.setDaAmount(bean.getDaAmount());
		result.setHqDaAmount(bean.getHqDaAmount());
		result.setHqExtendDa(bean.getHqExtendDa());
		result.setJourneyHours(bean.getJourneyHours());
		result.setJourneyExtendDa(bean.getJourneyExtendDa());
		result.setJourneyAmount(bean.getJourneyAmount());
		result.setHaltHours(bean.getHaltHours());
		result.setHaltExtendDa(bean.getHaltExtendDa());
		result.setHaltAmount(bean.getHaltAmount());
		result.setSpecialHaltExtendDa(bean.getSpecialHaltExtendDa());
		result.setSpecialHaltAmount(bean.getSpecialHaltAmount());
		result.setEachLineTotal(bean.getEachLineTotal());
		result.setRemarks(bean.getRemarks());
		
		return result;	
	}

}
