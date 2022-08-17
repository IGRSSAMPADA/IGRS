package com.wipro.igrs.expform76b.bd;

import java.util.ArrayList;

import com.wipro.igrs.expform76b.bao.ExpFoem76BBAO;
import com.wipro.igrs.expform76b.dto.ExpForm76BSelectDTO;

public class ExpForm76BBD {
	
	ExpFoem76BBAO expBAO=new ExpFoem76BBAO();
	
	public ArrayList getAllData()
	{
		return expBAO.getAllDAta();
	}
	
	public ArrayList getAllMinorHeadRelatedToSubMajor(String subMajorID)
	{
		return expBAO.getAllMinorRelatedToSubMajor(subMajorID);
	}

	public ExpForm76BSelectDTO getStatuseRelatedToEmployee(String empID)
	{
		return expBAO.getStatuseRelatedToEmployee(empID);
		
	}
	
	public boolean addnewDTLS(ExpForm76BSelectDTO expDTO)
	{
		System.out.println("Adding BD");
		return expBAO.addnewDTLS(expDTO);
	}

}
