package com.wipro.igrs.empDeductions.bd;

import java.util.ArrayList;
import java.util.Hashtable;

import com.wipro.igrs.empDeductions.bo.EmpDeductionsBO;
import com.wipro.igrs.empDeductions.dto.EmpDTO;


public class EmpDeductionsBD
{
	EmpDeductionsBO empBo = null;
	
	/**
	 * for getting the details of an employee
	 * @param empId
	 * @return ArrayList
	 */
	public ArrayList getUserDetails(String empId) {
		empBo = new EmpDeductionsBO();
		return empBo.getUserDetails(empId);
	}
	
	/**
	 * for getting the users list based on the values they given.
	 * the values are optional he can any thing in first name,middle name and last name OR
	 * he can give any combination 
	 * @param vals
	 * @return ArrayList
	 */
	public ArrayList getUsersList(Hashtable vals) {
		empBo = new EmpDeductionsBO();
		return empBo.getUsersList(vals);
	}
	
	
	/**
	 * for getting the deductions list for an Employee
	 * @return ArrayList
	 */
	public ArrayList getDeductions() {
		empBo = new EmpDeductionsBO();
		return empBo.getDeductions();
	}
	
	/**
	 * for getting the deduction details 
	 * @param empDet
	 * @return EmpDTO
	 */
	public EmpDTO getDeductionDetails(String[] empDet) {
		empBo = new EmpDeductionsBO();
		return empBo.getDeductionDetails(empDet);
	}
	
	
	/**
	 * for getting the sub type of the deductions list
	 * @param deductionId
	 * @return ArrayList(deductions sub types)
	 */
	public ArrayList getDeductionSubType(String deductionId) {
		empBo = new EmpDeductionsBO();
		return empBo.getDeductionSubType(deductionId);
	}
	
}
