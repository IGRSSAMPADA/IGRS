package com.wipro.igrs.salarygrademaster.bo;

import java.util.ArrayList;

import com.wipro.igrs.salarygrademaster.dto.SalaryGradeDTO;

public interface ISalaryGradeBO {
	
	public ArrayList getComponentsIDs() throws Exception;
	public ArrayList getGradeDTOs() throws Exception;
	public void addSalaryGrade(SalaryGradeDTO salaryGrade) throws Exception;
	public void updateSalaryGrade(SalaryGradeDTO salaryGrade,SalaryGradeDTO oldSalaryGrade) throws Exception;
	public void deleteSalaryGrade(SalaryGradeDTO salaryGrade) throws Exception;
	public ArrayList viewAllSalaryGrades() throws Exception;
	public SalaryGradeDTO getSalaryGradeByID(String salaryGradeId) throws Exception;

}
