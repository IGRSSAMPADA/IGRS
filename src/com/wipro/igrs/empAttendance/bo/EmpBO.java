package com.wipro.igrs.empAttendance.bo;

import com.wipro.igrs.empAttendance.dao.EmpDAO;



public class EmpBO
{

	public boolean empAttendanceCreate(String param1[]) throws Exception
	 {
		EmpDAO dao = new EmpDAO();
		 	return dao.empAttendanceCreate(param1);
	 }
	
	public boolean checkEmpId(String employeeId) throws Exception
	 {
		EmpDAO dao = new EmpDAO();
		 	return dao.checkEmpId(employeeId);
	 }
}
