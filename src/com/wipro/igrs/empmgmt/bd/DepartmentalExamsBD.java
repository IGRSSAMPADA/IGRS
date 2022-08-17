/**
 * DepartmentalExamsBD.java
 */


package com.wipro.igrs.empmgmt.bd;


import java.util.ArrayList;

import com.wipro.igrs.empmgmt.dao.DepartmentalExamsDAO;
import com.wipro.igrs.empmgmt.dto.DepartmentalExamsDTO;


/**
 * @author jagadish Jun 4, 2008
 * 
 */
public class DepartmentalExamsBD {

	private DepartmentalExamsDAO deptExamDAO = new DepartmentalExamsDAO();

	/**
	 * @param deptExamDTO
	 * @return boolean
	 */
	public boolean insertDepartmentalExamDetails(
			DepartmentalExamsDTO deptExamDTO, ArrayList examList, String userId) {
		boolean insert = false;
		try {
			insert = deptExamDAO.insertDepartmentalExamDetails(deptExamDTO,
					examList, userId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return insert;
	}

	/**
	 * @param empId
	 * @return boolean
	 */
	public boolean checkEmpIdAvailability(String empId) {
		boolean check = false;
		try {
			check = deptExamDAO.checkEmpIdAvailability(empId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	

}
