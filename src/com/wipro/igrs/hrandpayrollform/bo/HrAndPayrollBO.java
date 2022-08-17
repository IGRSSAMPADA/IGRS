package com.wipro.igrs.hrandpayrollform.bo;

import java.util.List;

import com.wipro.igrs.hrandpayrollform.dao.HrAndPayrollDAO;
import com.wipro.igrs.hrandpayrollform.dto.HrAndPayrollDTO;

public class HrAndPayrollBO {

	HrAndPayrollDAO hrAndPayrollDAO = new HrAndPayrollDAO();
	
	public List getAllCountryMaster() {

		return hrAndPayrollDAO.getAllCountryMaster();
	}

	public List getAllDistrictMaster() {

		return hrAndPayrollDAO.getAllDistrictMaster();
	}

	public List getAllPaymentTypeMaster() {

		return hrAndPayrollDAO.getAllPaymentTypeMaster();
	}

	public List getAllStateMaster() {

		return hrAndPayrollDAO.getAllStateMaster();
	}
	
	public boolean addHrAndPayrollMaster(HrAndPayrollDTO hrAndPayrollDTO) {
	
		return hrAndPayrollDAO.addHrAndPayrollMaster(hrAndPayrollDTO);
	}
	
	public List getDistrictsByStateId(String id){
		return hrAndPayrollDAO.getDistrictsByStateId(id);
	}
		
	public List getStatesByCountryId(String id){
		return hrAndPayrollDAO.getStatesByCountryId(id);
	}
	

}
