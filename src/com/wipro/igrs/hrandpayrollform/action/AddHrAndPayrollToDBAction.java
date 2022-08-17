package com.wipro.igrs.hrandpayrollform.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.hrandpayrollform.bd.HrAndPayrollBD;
import com.wipro.igrs.hrandpayrollform.dto.HrAndPayrollDTO;
import com.wipro.igrs.hrandpayrollform.form.AddhrAndPayrollbean;

public class AddHrAndPayrollToDBAction extends BaseAction {
    
	SimpleDateFormat sourceDateFormat = new SimpleDateFormat("dd/mm/yyyy");
	SimpleDateFormat targetDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
    
    public AddHrAndPayrollToDBAction() {
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {

    	if(isCancelled(request)){
    		mapping.findForward("first");
    	}
    	
    	AddhrAndPayrollbean bean = (AddhrAndPayrollbean)form;
    	HrAndPayrollBD hrAndPayrollBD = new HrAndPayrollBD();
    	HrAndPayrollDTO hrAndPayrollDTO = new HrAndPayrollDTO();
    	
    	
    	Date date = sourceDateFormat.parse(bean.getStartDate());
    	String dateStr = targetDateFormat.format(date);
    	
    	hrAndPayrollDTO.setStartdate(dateStr);
    	hrAndPayrollDTO.setLeasePeriod(Integer.parseInt(bean.getLeasePeriod()));
    	hrAndPayrollDTO.setLeaseAmount(Float.parseFloat(bean.getAmountPaid()));
    	hrAndPayrollDTO.setPaymentType(bean.getPaymentMode());
    	hrAndPayrollDTO.setPropertyAddress(bean.getAddressRented());
    	hrAndPayrollDTO.setPropertyCountryId(bean.getCountryRented());
    	hrAndPayrollDTO.setPropertyStateId(bean.getStateRented());
    	hrAndPayrollDTO.setPropertyDistrictId(bean.getDistrictRented());
    	hrAndPayrollDTO.setPropertyPostalCode(Integer.parseInt(bean.getPostalCodeRented()));
    	hrAndPayrollDTO.setLandLordFName(bean.getFirstName());
    	hrAndPayrollDTO.setLandLordMName(bean.getMiddleName());
    	hrAndPayrollDTO.setLandLordLName(bean.getLastName());
    	hrAndPayrollDTO.setLandLordGender(bean.getGender());
    	hrAndPayrollDTO.setLandLordAddress(bean.getAddressLandLord());
    	hrAndPayrollDTO.setLandLordCountryId(bean.getCountryLandLord());
    	hrAndPayrollDTO.setLandLordStateId(bean.getStateLandLord());
    	hrAndPayrollDTO.setLandLordDistrictId(bean.getDistrictLandLord());
    	hrAndPayrollDTO.setLandPostalCode(Integer.parseInt(bean.getPostalCodeLandLord()));
    	
    	hrAndPayrollBD.addHrAndPayrollMaster(hrAndPayrollDTO);
    	
    	
    	return mapping.findForward("success");
    }

}