package com.wipro.igrs.salarygrademaster.bo;

import java.util.ArrayList;

import com.wipro.igrs.salarygrademaster.dao.ISalaryGradeDAO;
import com.wipro.igrs.salarygrademaster.dao.SalaryGradeDAO;
import com.wipro.igrs.salarygrademaster.dto.SalaryGradeDTO;
import com.wipro.igrs.salarygrademaster.exception.SalaryGradeAlreadyExistException;

public class SalaryGradeBO implements ISalaryGradeBO{

	
	ISalaryGradeDAO salaryGradeDAO=new SalaryGradeDAO();
	
	public void addSalaryGrade(SalaryGradeDTO salaryGrade) throws Exception {
		
		if(!salaryGradeDAO.isExist(salaryGrade))
			salaryGradeDAO.addSalaryGrade(salaryGrade);
		else
		{
			System.out.println("Already exist exception");
			throw new SalaryGradeAlreadyExistException();
		}
		
	}

	public void deleteSalaryGrade(SalaryGradeDTO salaryGrade) throws Exception {
		
		salaryGradeDAO.deleteSalaryGrade(salaryGrade);
		
	}

	public ArrayList getComponentsIDs() throws Exception {
		
		return salaryGradeDAO.getComponentsIDs();
	}

	public ArrayList getGradeDTOs() throws Exception {
		
		return salaryGradeDAO.getGradeDTOs();
	}

	public void updateSalaryGrade(SalaryGradeDTO salaryGrade,SalaryGradeDTO oldSalaryGrade) throws Exception {
		
		if(!salaryGradeDAO.isExist(salaryGrade) || (salaryGrade.getGradeId().equals(oldSalaryGrade.getGradeId()) && salaryGrade.getComponentId().equals(oldSalaryGrade.getComponentId())))
			salaryGradeDAO.updateSalaryGrade(salaryGrade);
		else
		{
			System.out.println("Already exist exception");
			throw new SalaryGradeAlreadyExistException();
		}
	}

	public ArrayList viewAllSalaryGrades() throws Exception {
		
		return salaryGradeDAO.viewAllSalaryGrades();
	}

	public SalaryGradeDTO getSalaryGradeByID(String salaryGradeId) throws Exception {
		
		return salaryGradeDAO.getSalaryGradeByID(salaryGradeId);
	}

}
