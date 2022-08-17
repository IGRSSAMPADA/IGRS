package com.wipro.igrs.branchmaster.bd;

import java.util.ArrayList;

import com.wipro.igrs.branchmaster.bo.BranchBO;
import com.wipro.igrs.branchmaster.bo.IBranchBO;
import com.wipro.igrs.branchmaster.dto.BranchDTO;

public class BranchBD implements IBranchBD{

	IBranchBO branchBO=BranchBO.getInstance();
	
	 //Singleton
    private static BranchBD branchBD;
	private BranchBD() {
		
	}

	public static BranchBD getInstance()
	{
		if(branchBD==null)
			branchBD=new BranchBD();
		
		return branchBD;
	}
	
	public void addBranchMaster(BranchDTO branchDto) throws Exception{
		
			branchBO.addBranchMaster(branchDto);

	}

	public void deleteBranchMaster(BranchDTO branchDto) throws Exception{
			branchBO.deleteBranchMaster(branchDto);
	}

	public BranchDTO getBranchById(String branchId) throws Exception{

		return branchBO.getBranchById(branchId);
	}

	public ArrayList getBranchList(String bankId) throws Exception{
		return branchBO.getBranchList(bankId);
	}

	public void updateBranchMaster(BranchDTO branchDto,String oldName) throws Exception{
			branchBO.updateBranchMaster(branchDto,oldName);
	
	}

}
