package com.wipro.igrs.branchmaster.bo;

import java.util.ArrayList;

import com.wipro.igrs.branchmaster.dto.BranchDTO;

public interface IBranchBO {

	//Method for inserting Branch details into Database
    public void addBranchMaster(BranchDTO bankDto) throws Exception ;
    
    //Method for updating Branch details into Database
    public void updateBranchMaster(BranchDTO bankDto,String oldName) throws Exception ;
 
    //Method for getting Branch List from Database
    public ArrayList getBranchList(String bankId) throws Exception;
	
    
    //Method for getting bank data by bank id from Database
    public BranchDTO getBranchById(String bankId) throws Exception;
  
    
    //Method for softly deleting Branch from Database
    public void deleteBranchMaster(BranchDTO brancgDto) throws Exception;
    
}
