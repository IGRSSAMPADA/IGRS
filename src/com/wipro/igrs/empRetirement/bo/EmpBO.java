package com.wipro.igrs.empRetirement.bo;

import java.util.ArrayList;

import com.wipro.igrs.empRetirement.dao.EmpDAO;


public class EmpBO
{
		
	public ArrayList empDetails(String empId) throws Exception
	 {
		EmpDAO dao = new EmpDAO();
		 	return dao.empDetails(empId);
	 }
	
	public boolean checkEmpId(String empId) throws Exception
	 {
		EmpDAO dao = new EmpDAO();
		 	return dao.checkEmpId(empId);
	 }
	public ArrayList empDesg(String empId) throws Exception
	 {
		EmpDAO dao = new EmpDAO();
		 	return dao.empDesg(empId);
	 }
	public String getPension(String empId,String typeRetir,String date)throws Exception
	{
		EmpDAO dao = new EmpDAO();
	 	return dao.getPension(empId,typeRetir,date);
	}
	
	public String getGratuity(String empId,String date,String typeRetir)throws Exception
	{
		EmpDAO dao = new EmpDAO();
	 	return dao.getGratuity(empId,date,typeRetir);
	}
	
	public ArrayList getGis(String empId) throws Exception
	 {
		EmpDAO dao = new EmpDAO();
		 	return dao.getGis(empId);
	 }
	
	public ArrayList getPf(String empId) throws Exception
	 {
		EmpDAO dao = new EmpDAO();
		 	return dao.getPf(empId);
	 }
	
	public String getEarnLeave(String empId,String date)throws Exception
	{
		EmpDAO dao = new EmpDAO();
	 	return dao.getEarnLeave(empId,date);
	}
	public String getEarnAmt(String empId,String date)throws Exception
	{
		EmpDAO dao = new EmpDAO();
	 	return dao.getEarnAmt(empId,date);
	}
	
	
	
}
