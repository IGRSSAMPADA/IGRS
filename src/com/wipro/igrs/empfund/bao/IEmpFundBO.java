/**
 * 
 */
package com.wipro.igrs.empfund.bao;

import java.util.List;

import com.wipro.igrs.empfund.dto.EmpFundDTO;
import com.wipro.igrs.empfund.dto.FundRangeDTO;
import com.wipro.igrs.empfund.dto.FundTypeDTO;

/**
 * @author HMOHAM
 *
 */
public interface IEmpFundBO {

	List getFundTypeList();
	
	FundTypeDTO getFundType(String fundTypeId);
	
	boolean isVariableFundAmount(FundTypeDTO fundTypeDTO);
	boolean isValidFundAmount(FundTypeDTO fundTypeDTO, double fundAmount,String empId);
	
	EmpFundDTO getEmpFund(String empId, String fundTypeId);
	void editEmpFund(EmpFundDTO empFundDTO);
	FundRangeDTO getFundTypeRange(FundTypeDTO fundTypeDTO,String empId);
	
}
