package com.wipro.igrs.branchmaster.bd;

import java.util.ArrayList;

import com.wipro.igrs.branchmaster.dto.BranchDTO;

public interface IBranchBD {

	//Method for inserting Branch details into Database
    public void addBranchMaster(BranchDTO branchDto)throws Exception;
    
    //Method for updating Branch details into Database
    public void updateBranchMaster(BranchDTO branchDto,String oldName)throws Exception;
 
    //Method for getting Branch List from Database
    public ArrayList getBranchList(String bankId)throws Exception;
	
    
    //Method for getting branch data by branch id from Database
    public BranchDTO getBranchById(String branchId)throws Exception;
  
    
    //Method for softly deleting Branch from Database
    public void deleteBranchMaster(BranchDTO branchDto)throws Exception;
    
}
