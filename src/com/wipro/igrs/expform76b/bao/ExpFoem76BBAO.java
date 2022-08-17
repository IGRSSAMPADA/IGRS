package com.wipro.igrs.expform76b.bao;

import java.util.ArrayList;

import com.wipro.igrs.expform76b.dao.ExpForm76BDAO;
import com.wipro.igrs.expform76b.dto.ExpForm76BSelectDTO;

public class ExpFoem76BBAO {

	ExpForm76BDAO expDAO=new ExpForm76BDAO();
	
	public ArrayList getAllDAta()
	{
		ArrayList result=new ArrayList();
		
		ArrayList bill=expDAO.getNextValueOfSeq();
		ArrayList districts =expDAO.getAllDistricts();
		ArrayList yFiscal=expDAO.getAllFiscalYear();
		ArrayList mFiscal=expDAO.getAllFiscalMonths();
		ArrayList majors=expDAO.getAllMajorHeads();
		ArrayList subMajor=expDAO.getAllSubMajorHeads();
		ArrayList account=expDAO.getAllAccountHeads();
		
		result.add(districts);
		result.add(yFiscal);
		result.add(mFiscal);
		result.add(majors);
		result.add(subMajor);
		result.add(account);
		result.add(bill);
		
		return result;
	}
	
	public ArrayList getAllMinorRelatedToSubMajor(String subMajorID)
	{
		ArrayList returned=expDAO.getAllMinorHeadsRelatedToSubMajor(subMajorID); 
		if(returned!=null)
		{
			return returned;
		}
		else
		{
			ExpForm76BSelectDTO noData=new ExpForm76BSelectDTO();
			noData.setMinorHeadName("NO DATA FOUND");
			noData.setMinorHeadID("NO DATA FOUND");
			returned=new ArrayList();
			returned.add(noData);
			return returned;
		}
		
		
	}

	public ExpForm76BSelectDTO getStatuseRelatedToEmployee(String empID)
	{
		return expDAO.getStatuseRelatedToEmployee(empID);
	}
	
	public boolean addnewDTLS(ExpForm76BSelectDTO expDTO)
	{
		System.out.println("Adding BAO");
		return expDAO.addNewDTLS(expDTO);
	}
	
}
