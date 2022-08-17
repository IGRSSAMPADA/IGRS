package com.wipro.igrs.bankmaster.bo;

import java.util.ArrayList;

import com.wipro.igrs.bankmaster.dao.BankDAO;
import com.wipro.igrs.bankmaster.dao.IBankDAO;
import com.wipro.igrs.bankmaster.dto.BankDTO;
import com.wipro.igrs.bankmaster.exception.BankNameAlreadyExistException;

public class BankBO implements IBankBO{

	IBankDAO bankDAO=BankDAO.getInstance();
	
	 //Singleton
    private static BankBO bankBO;
	private BankBO() {
		
	}

	public static BankBO getInstance()
	{
		if(bankBO==null)
			bankBO=new BankBO();
		
		return bankBO;
		
	}
	
	public void addBankMaster(BankDTO bankDto, String roleId, String funId, String userId) throws Exception {
		
		if(!bankDAO.isExist(bankDto))
			bankDAO.addBankMaster(bankDto,roleId,funId,userId);
		else {
			throw new BankNameAlreadyExistException();
		}
		
	}

	public void deleteBankMaster(String bankId ,String roleId, String funId, String userId) throws Exception {
		
		bankDAO.deleteBankMaster(bankId,roleId,funId,userId);
		
	}

	public BankDTO getBankById(String bankId) throws Exception {
		
		return bankDAO.getBankById(bankId);
	}

	public ArrayList getBankList() throws Exception {
		
		return bankDAO.getBankList();
	}

	public void updateBankMaster(BankDTO bankDto ,String oldName, String roleId, String funId, String userId) throws Exception  {
		
		if(!bankDAO.isExist(bankDto) || bankDto.getBankName().equals(oldName))
			bankDAO.updateBankMaster(bankDto,roleId,funId,userId);
		else {
			throw new BankNameAlreadyExistException();
		}
		
	}

}
