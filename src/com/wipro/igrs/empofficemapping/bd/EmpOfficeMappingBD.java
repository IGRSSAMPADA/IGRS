package com.wipro.igrs.empofficemapping.bd;

import java.util.ArrayList;

import com.wipro.igrs.empofficemapping.bao.EmpOfficeMappingBAO;
import com.wipro.igrs.empofficemapping.dto.EmpOfficeMappingDTO;

public class EmpOfficeMappingBD {
	private EmpOfficeMappingBAO empOfficeMappingBAO = new EmpOfficeMappingBAO();
	public ArrayList getAllEmpOfficeMappings() {
		return empOfficeMappingBAO.getAllEmpOfficeMappings();
	}
	
	public void deleteEmpOfficeMappings(String[] mappingsIds) {
		empOfficeMappingBAO.deleteEmpOfficeMappings(mappingsIds);
	}
	
	public boolean isEmpExist(String empId) {
		return empOfficeMappingBAO.isEmpExist(empId);
	}
	
	public boolean isMappingExist(String empId) {
		return empOfficeMappingBAO.isMappingExist(empId);
	}
	
	public String getEmpName(String empId) {
		return empOfficeMappingBAO.getEmpName(empId);
	}
	
	public ArrayList getAllDepts() {
		return empOfficeMappingBAO.getAllDepts();
	}
	
	public ArrayList getAllOffices() {
		return empOfficeMappingBAO.getAllOffices();
	}
	
	public ArrayList getAllRoles() {
		return empOfficeMappingBAO.getAllRoles();
	}
	
	public EmpOfficeMappingDTO getEmpMappingById(EmpOfficeMappingDTO bean) {
		return empOfficeMappingBAO.getEmpMappingById(bean);
	}
	
	public void updateEmpOfficeMapping(EmpOfficeMappingDTO bean) {
		empOfficeMappingBAO.updateEmpOfficeMapping(bean);
	}
	
	public void addEmpOfficeMapping(EmpOfficeMappingDTO bean) {
		empOfficeMappingBAO.addEmpOfficeMapping(bean);
	}
}
