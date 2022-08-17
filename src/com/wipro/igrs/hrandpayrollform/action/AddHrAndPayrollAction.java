package com.wipro.igrs.hrandpayrollform.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.hrandpayrollform.bd.HrAndPayrollBD;
import com.wipro.igrs.hrandpayrollform.form.AddhrAndPayrollbean;

public class AddHrAndPayrollAction extends BaseAction {
    


    
    public AddHrAndPayrollAction() {
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
        
    	AddhrAndPayrollbean bean = (AddhrAndPayrollbean)form;
    	HrAndPayrollBD hrAndPayrollBD = new HrAndPayrollBD();
    	ArrayList allCountryMaster = (ArrayList)hrAndPayrollBD.getAllCountryMaster();
    	ArrayList allDistrictMaster = (ArrayList)hrAndPayrollBD.getAllDistrictMaster();
    	ArrayList allPaymentTypeMaster = (ArrayList)hrAndPayrollBD.getAllPaymentTypeMaster();
    	ArrayList allStateMaster = (ArrayList)hrAndPayrollBD.getAllStateMaster();
    	
    	bean.setCountryCollectionLandLord(allCountryMaster);
    	bean.setCountryCollectionRented(allCountryMaster);
    	
    	bean.setDistrictCollectionLandLord(allDistrictMaster);
    	bean.setDistrictCollectionRented(allDistrictMaster);
    	
    	bean.setStateCollectionLandLord(allStateMaster);
    	bean.setStateCollectionRented(allStateMaster);
    	
    	bean.setPaymentModeCollection(allPaymentTypeMaster);
    	
    	
    	
    	
        return mapping.findForward("addHrAndPayroll");
    }

}