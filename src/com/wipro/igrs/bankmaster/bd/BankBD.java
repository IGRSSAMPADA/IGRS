package com.wipro.igrs.bankmaster.bd;

import java.util.ArrayList;

import com.wipro.igrs.bankmaster.bo.BankBO;
import com.wipro.igrs.bankmaster.bo.IBankBO;
import com.wipro.igrs.bankmaster.dto.BankDTO;

public class BankBD implements IBankBD{

	IBankBO bankBO=BankBO.getInstance();
	
	 //Singleton
    private static BankBD bankBD;
	private BankBD() {
		
	}

	public static BankBD getInstance()
	{
		if(bankBD==null)
			bankBD=new BankBD();
		
		return bankBD;
	}
	
	public void addBankMaster(BankDTO bankDto, String roleId, String funId, String userId) throws Exception{
		
			bankBO.addBankMaster(bankDto,roleId,funId,userId);

	}

	public void deleteBankMaster(String bankId , String roleId, String funId, String userId) throws Exception{
			bankBO.deleteBankMaster(bankId,roleId,funId,userId);
	}

	public BankDTO getBankById(String bankId) throws Exception{

		return bankBO.getBankById(bankId);
	}

	public ArrayList getBankList() throws Exception{
		return bankBO.getBankList();
	}

	public void updateBankMaster(BankDTO bankDto,String oldName,String roleId, String funId, String userId) throws Exception{
			bankBO.updateBankMaster(bankDto,oldName,roleId,funId,userId);
	
	}

}
