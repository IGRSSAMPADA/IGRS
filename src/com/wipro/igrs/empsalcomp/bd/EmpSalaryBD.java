package com.wipro.igrs.empsalcomp.bd;


import java.util.ArrayList;

import com.wipro.igrs.empsalcomp.bao.EmpSalaryBAO;
import com.wipro.igrs.empsalcomp.dto.EmpSalaryDTO;

public class EmpSalaryBD {
	
	EmpSalaryBAO empBAO=new EmpSalaryBAO();
	
	public EmpSalaryDTO getEmpData(String EmpID)
	{
		return empBAO.getEmpData(EmpID);
	}
	
	public int getCheckEmp(String EmpID)
	{
		return empBAO.getCheckEmp(EmpID);
	}
	public ArrayList getSlabList(String EmpID) throws Exception
	{
		return empBAO.getSlabList(EmpID);
	}

	public boolean SaveOrUpdateEmpSalaryBase(EmpSalaryDTO empDTO, String userId)
	{
		return empBAO.SaveOrUpdateEmpSalaryBase(empDTO,userId);
	}
}
