package com.wipro.igrs.bankmaster.bo;

import java.util.ArrayList;

import com.wipro.igrs.bankmaster.dto.BankDTO;

public interface IBankBO {

	//Method for inserting Bank details into Database
    public void addBankMaster(BankDTO bankDto, String roleId, String funId, String userId) throws Exception ;
    
    //Method for updating Bank details into Database
    public void updateBankMaster(BankDTO bankDto,String oldName, String roleId, String funId, String userId) throws Exception ;
 
    //Method for getting Bank List from Database
    public ArrayList getBankList() throws Exception;
	
    
    //Method for getting bank data by bank id from Database
    public BankDTO getBankById(String bankId) throws Exception;
  
    
    //Method for softly deleting Bank from Database
    public void deleteBankMaster(String bankId, String roleId, String funId, String userId) throws Exception;
    
}
