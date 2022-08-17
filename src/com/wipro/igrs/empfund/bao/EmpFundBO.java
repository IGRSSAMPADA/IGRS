/**
 * 
 */
package com.wipro.igrs.empfund.bao;

import java.util.List;

import com.wipro.igrs.empfund.dao.EmpFundDAO;
import com.wipro.igrs.empfund.dto.EmpFundDTO;
import com.wipro.igrs.empfund.dto.FundRangeDTO;
import com.wipro.igrs.empfund.dto.FundTypeDTO;

/**
 * @author MRAMAD
 *
 */
public class EmpFundBO implements IEmpFundBO{
	
	EmpFundDAO empFundDAO = new EmpFundDAO();
	
	public List getFundTypeList(){
		return empFundDAO.getFundTypeList();
	}
	
	public boolean isVariableFundAmount(FundTypeDTO fundTypeDTO)
	{
		return empFundDAO.isVariableFundAmount(fundTypeDTO);
	}
	public boolean isValidFundAmount(FundTypeDTO fundTypeDTO, double fundAmount,String empId){
		return empFundDAO.isValidFundAmount(fundTypeDTO,fundAmount,empId);
	}
	
	public EmpFundDTO getEmpFund(String empId, String fundTypeId){
		return empFundDAO.getEmpFund(empId, fundTypeId);
	}
	public void editEmpFund(EmpFundDTO empFundDTO)
	{
		empFundDAO.editEmpFund(empFundDTO);
	}
	
	public FundRangeDTO getFundTypeRange(FundTypeDTO fundTypeDTO,String empId)
	{
		return empFundDAO.getFundTypeRange(fundTypeDTO, empId);
	}

	public FundTypeDTO getFundType(String fundTypeId) {
		return empFundDAO.getFundType(fundTypeId);
	}

}
