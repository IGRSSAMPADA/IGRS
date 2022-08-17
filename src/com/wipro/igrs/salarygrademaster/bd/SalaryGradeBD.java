package com.wipro.igrs.salarygrademaster.bd;

import java.util.ArrayList;

import com.wipro.igrs.salarygrademaster.bo.ISalaryGradeBO;
import com.wipro.igrs.salarygrademaster.bo.SalaryGradeBO;
import com.wipro.igrs.salarygrademaster.dto.SalaryGradeDTO;

public class SalaryGradeBD implements ISalaryGradeBD{

	ISalaryGradeBO salaryGradeBO=new SalaryGradeBO();
	
	public void addSalaryGrade(SalaryGradeDTO salaryGrade) throws Exception {
		salaryGradeBO.addSalaryGrade(salaryGrade);
		
	}

	public void deleteSalaryGrade(SalaryGradeDTO salaryGrade) throws Exception {
		salaryGradeBO.deleteSalaryGrade(salaryGrade);
		
	}

	public ArrayList getComponentsIDs() throws Exception {
		return salaryGradeBO.getComponentsIDs();
	}

	public ArrayList getGradeDTOs() throws Exception {
		return salaryGradeBO.getGradeDTOs();
	}

	public void updateSalaryGrade(SalaryGradeDTO salaryGrade,SalaryGradeDTO oldSalaryGrade) throws Exception {
		salaryGradeBO.updateSalaryGrade(salaryGrade,oldSalaryGrade);
		
	}

	public ArrayList viewAllSalaryGrades() throws Exception {
		
		return salaryGradeBO.viewAllSalaryGrades();
	}

	public SalaryGradeDTO getSalaryGradeByID(String salaryGradeId)throws Exception {
		
		return salaryGradeBO.getSalaryGradeByID(salaryGradeId);
	}

}
