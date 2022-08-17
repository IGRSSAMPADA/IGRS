/**
 * 
 */
package com.wipro.igrs.empfund.bd;

import java.util.List;

import com.wipro.igrs.empfund.bao.EmpFundBO;
import com.wipro.igrs.empfund.dto.EmpFundDTO;
import com.wipro.igrs.empfund.dto.FundRangeDTO;
import com.wipro.igrs.empfund.dto.FundTypeDTO;

/**
 * @author MRAMAD
 *
 */
public class EmpFundBD implements IEmpFundBD{

	EmpFundBO empFundBO = new EmpFundBO();
	
	public List getFundTypeList(){
		return empFundBO.getFundTypeList();
	}
	
	public boolean isVariableFundAmount(FundTypeDTO fundTypeDTO){
		return empFundBO.isVariableFundAmount(fundTypeDTO);
	}
	public boolean isValidFundAmount(FundTypeDTO fundTypeDTO, double fundAmount,String empId){
		return empFundBO.isValidFundAmount(fundTypeDTO, fundAmount,empId);
	}
	
	public EmpFundDTO getEmpFund(String empId, String fundTypeId){
		return empFundBO.getEmpFund(empId, fundTypeId);
	}
	
	public void editEmpFund(EmpFundDTO empFundDTO){
		empFundBO.editEmpFund(empFundDTO);
	}
	
	public FundRangeDTO getFundTypeRange(FundTypeDTO fundTypeDTO,String empId)
	{
		return empFundBO.getFundTypeRange(fundTypeDTO,empId);
	}

	public FundTypeDTO getFundType(String fundTypeId) {
		return empFundBO.getFundType(fundTypeId);
	}
	
}
