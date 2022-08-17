package com.wipro.igrs.expmptc28.bd;

import java.util.ArrayList;

import com.wipro.igrs.expmptc28.bao.ExpMPTC28BAO;
import com.wipro.igrs.expmptc28.dto.EmpDetailsDTO;
import com.wipro.igrs.expmptc28.dto.ExpMPTC28DTO;

public class ExpMPTC28BD {
	
	private ExpMPTC28BAO expMPTC28BAO = new ExpMPTC28BAO();
	
	public ArrayList getAllEmployees() {
		return expMPTC28BAO.getAllEmployees();
	}
	
	public ArrayList getAllDistricts() {
		return expMPTC28BAO.getAllDistricts();
	}
	
	public String getVoucherNumber () {
		return expMPTC28BAO.getVoucherNumber();
	}
	
	public EmpDetailsDTO getEmpDetails(String empId) {
		return expMPTC28BAO.getEmpDetails(empId);
	}
	
	public void addExpMPTC28(ExpMPTC28DTO dto) {
		expMPTC28BAO.addExpMPTC28(dto);
	}

}
