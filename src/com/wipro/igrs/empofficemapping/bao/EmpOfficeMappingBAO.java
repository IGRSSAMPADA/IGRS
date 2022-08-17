package com.wipro.igrs.empofficemapping.bao;

import java.util.ArrayList;

import com.wipro.igrs.empofficemapping.dao.EmpOfficeMappingDAO;
import com.wipro.igrs.empofficemapping.dto.EmpOfficeMappingDTO;

public class EmpOfficeMappingBAO {
	private EmpOfficeMappingDAO empOfficeMappingDAO = new EmpOfficeMappingDAO();
	public ArrayList getAllEmpOfficeMappings() {
		return empOfficeMappingDAO.getAllEmpOfficeMappings();
	}
	
	public void deleteEmpOfficeMappings(String[] mappingsIds) {
		empOfficeMappingDAO.deleteEmpOfficeMappings(mappingsIds);
	}
	
	public boolean isEmpExist(String empId) {
		return empOfficeMappingDAO.isEmpExist(empId);
	}
	
	public boolean isMappingExist(String empId) {
		EmpOfficeMappingDTO bean = new EmpOfficeMappingDTO();
		bean.setEmpId(empId);
		return empOfficeMappingDAO.isMappingExist(bean);
	}
	
	public String getEmpName(String empId) {
		return empOfficeMappingDAO.getEmpName(empId);
	}
	
	public ArrayList getAllDepts() {
		return empOfficeMappingDAO.getAllDepts();
	}
	
	public ArrayList getAllOffices() {
		return empOfficeMappingDAO.getAllOffices();
	}
	
	public ArrayList getAllRoles() {
		return empOfficeMappingDAO.getAllRoles();
	}
	
	public EmpOfficeMappingDTO getEmpMappingById(EmpOfficeMappingDTO bean) {
		return empOfficeMappingDAO.getEmpMappingById(bean);
	}
	
	public void updateEmpOfficeMapping(EmpOfficeMappingDTO bean) {
		empOfficeMappingDAO.updateEmpOfficeMapping(bean);
	}
	
	public void addEmpOfficeMapping(EmpOfficeMappingDTO bean) {
		empOfficeMappingDAO.addEmpOfficeMapping(bean);
	}

}
