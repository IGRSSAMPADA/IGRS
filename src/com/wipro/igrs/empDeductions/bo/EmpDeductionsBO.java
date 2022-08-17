package com.wipro.igrs.empDeductions.bo;

import java.util.ArrayList;
import java.util.Hashtable;

import com.wipro.igrs.empDeductions.dao.EmpDeductionsDAO;
import com.wipro.igrs.empDeductions.dto.EmpDTO;


public class EmpDeductionsBO
{
	EmpDeductionsDAO empDao = null;

	
	/**
	 * for getting the details of an employee
	 * @param empId
	 * @return ArrayList
	 */
	public ArrayList getUserDetails(String empId) {
		empDao = new EmpDeductionsDAO();
		return empDao.getUserDetails(empId);
	}

	
	/**
	 * for getting the users list based on the values they given.
	 * the values are optional he can any thing in first name,middle name and last name OR
	 * he can give any combination 
	 * @param vals
	 * @return ArrayList
	 */
	public ArrayList getUsersList(Hashtable vals) {
		empDao = new EmpDeductionsDAO();
		return empDao.getUsersList(vals);
	}


	/**
	 * for getting the deductions list for an Employee
	 * @return ArrayList
	 */
	public ArrayList getDeductions() {
		empDao = new EmpDeductionsDAO();
		return empDao.getDeductions();
	}

	/**
	 * for getting the deduction details 
	 * @param empDet
	 * @return EmpDTO
	 */
	public EmpDTO getDeductionDetails(String[] empDet) {
		empDao = new EmpDeductionsDAO();
		return empDao.getDeductionDetails(empDet);
	}

	
	/**
	 * for getting the sub type of the deductions list
	 * @param deductionId
	 * @return ArrayList(deductions sub types)
	 */
	public ArrayList getDeductionSubType(String deductionId) {
		empDao = new EmpDeductionsDAO();
		return empDao.getDeductionSubType(deductionId);
	}
	
}
