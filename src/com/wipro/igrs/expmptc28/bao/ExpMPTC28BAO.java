package com.wipro.igrs.expmptc28.bao;

import java.util.ArrayList;

import com.wipro.igrs.expmptc28.dao.ExpMPTC28DAO;
import com.wipro.igrs.expmptc28.dto.EmpDetailsDTO;
import com.wipro.igrs.expmptc28.dto.ExpMPTC28DTO;

public class ExpMPTC28BAO {
	private ExpMPTC28DAO expMPTC28DAO = new ExpMPTC28DAO();
	
	public ArrayList getAllEmployees() {
		return expMPTC28DAO.getAllEmployees();
	}
	
	public ArrayList getAllDistricts() {
		return expMPTC28DAO.getAllDistricts();
	}
	
	public String getVoucherNumber () {
		return expMPTC28DAO.getVoucherNumber();
	}
	
	public EmpDetailsDTO getEmpDetails(String empId) {
		return expMPTC28DAO.getEmpDetails(empId);
	}
	
	public void addExpMPTC28(ExpMPTC28DTO dto) {
		expMPTC28DAO.addExpMPTC28(dto);
	}
}
