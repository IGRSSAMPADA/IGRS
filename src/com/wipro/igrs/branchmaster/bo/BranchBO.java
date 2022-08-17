package com.wipro.igrs.branchmaster.bo;

import java.util.ArrayList;

import com.wipro.igrs.branchmaster.dao.BranchDAO;
import com.wipro.igrs.branchmaster.dao.IBranchDAO;
import com.wipro.igrs.branchmaster.dto.BranchDTO;
import com.wipro.igrs.branchmaster.exception.BranchNameAlreadyExistException;

public class BranchBO implements IBranchBO{

	IBranchDAO branchDAO=BranchDAO.getInstance();
	
	 //Singleton
    private static BranchBO branchBO;
	private BranchBO() {
		
	}

	public static BranchBO getInstance()
	{
		if(branchBO==null)
			branchBO=new BranchBO();
		
		return branchBO;
		
	}
	
	public void addBranchMaster(BranchDTO branchDto) throws Exception {
		
		if(!branchDAO.isExist(branchDto))
			branchDAO.addBranchMaster(branchDto);
		else {
			throw new BranchNameAlreadyExistException();
		}
		
	}

	public void deleteBranchMaster(BranchDTO branchDto) throws Exception {
		
		branchDAO.deleteBranchMaster(branchDto);
		
	}

	public BranchDTO getBranchById(String branchId) throws Exception {
		
		return branchDAO.getBranchById(branchId);
	}

	public ArrayList getBranchList(String bankId) throws Exception {
		
		return branchDAO.getBranchList(bankId);
	}

	public void updateBranchMaster(BranchDTO branchDto ,String oldName) throws Exception  {
		
		if(!branchDAO.isExist(branchDto) || branchDto.getBranchName().equals(oldName))
			branchDAO.updateBranchMaster(branchDto);
		else {
			throw new BranchNameAlreadyExistException();
		}
		
	}

}
