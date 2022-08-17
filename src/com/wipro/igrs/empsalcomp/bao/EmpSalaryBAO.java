package com.wipro.igrs.empsalcomp.bao;

import java.util.ArrayList;

import com.wipro.igrs.empsalcomp.dao.EmpSalaryDAO;
import com.wipro.igrs.empsalcomp.dto.CompIdDTO;
import com.wipro.igrs.empsalcomp.dto.EmpSalaryDTO;

public class EmpSalaryBAO {
	
	
	EmpSalaryDAO empDAO=new EmpSalaryDAO();
	
	
	public EmpSalaryDTO getEmpData(String EmpID)
	{
		return empDAO.getEmpData(EmpID);
	}
	
	public int getCheckEmp(String EmpID)
	{
		return empDAO.getCheckEmp(EmpID);
	}
	public ArrayList getSlabList(String EmpID) throws Exception
	{
		return empDAO.getSlabList(EmpID);
	}

	public boolean SaveOrUpdateEmpSalaryBase(EmpSalaryDTO empDTO, String userId)
	{
		if(empDAO.isEmpHasBasic(empDTO.getEmpID()))
		{
			return empDAO.updateBasicVAlue(empDTO,userId);
		}
		else
		{
			//System.out.println("mesh 3andoo ay kemaaaaaa");
			return empDAO.addNewEmpSalaryBasic(empDTO);
		}
	}

}
