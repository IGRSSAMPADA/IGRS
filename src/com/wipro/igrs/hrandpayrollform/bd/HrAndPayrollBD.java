package com.wipro.igrs.hrandpayrollform.bd;

import java.util.List;

import com.wipro.igrs.hrandpayrollform.bo.HrAndPayrollBO;
import com.wipro.igrs.hrandpayrollform.dto.HrAndPayrollDTO;

public class HrAndPayrollBD {

	HrAndPayrollBO hrAndPayrollBO = new HrAndPayrollBO();
	
	public List getAllCountryMaster() {

		return hrAndPayrollBO.getAllCountryMaster();
	}

	public List getAllDistrictMaster() {

		return hrAndPayrollBO.getAllDistrictMaster();
	}

	public List getAllPaymentTypeMaster() {

		return hrAndPayrollBO.getAllPaymentTypeMaster();
	}

	public List getAllStateMaster() {

		return hrAndPayrollBO.getAllStateMaster();
	}

	public boolean addHrAndPayrollMaster(HrAndPayrollDTO hrAndPayrollDTO) {
		
		return hrAndPayrollBO.addHrAndPayrollMaster(hrAndPayrollDTO);
	}
	
	public List getDistrictsByStateId(String id){
		return hrAndPayrollBO.getDistrictsByStateId(id);
	}
		
	public List getStatesByCountryId(String id){
		return hrAndPayrollBO.getStatesByCountryId(id);
	}
	
}
