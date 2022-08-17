package com.wipro.igrs.empfund.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.empfund.dto.EmpFundDTO;
import com.wipro.igrs.empfund.dto.FundRangeDTO;
import com.wipro.igrs.empfund.dto.FundTypeDTO;
import com.wipro.igrs.empfund.sql.EmpFundCommonSQL;

public class EmpFundDAO implements IEmpFundDAO{
	
	private ArrayList activityList = null;
	DBUtility dbUtility = null;
	String sql = null;
	ArrayList subList = null;
	String activityID = null;
	
	public List getFundTypeList()
	{
		DBUtility dbUtil = null;
		
		try {
			dbUtil = new DBUtility();
			
			dbUtil.createStatement();
			
			ArrayList resultList = dbUtil.executeQuery(EmpFundCommonSQL.GET_ALL_FUND_TYPES);
			
			ArrayList subList = null;
			
			ArrayList fundTypeList = new ArrayList(resultList.size());
			
			for (int i = 0; i < resultList.size(); i++) {
				subList = (ArrayList)resultList.get(i);
				
				int indx = 0;
				
				FundTypeDTO typeDTO = new FundTypeDTO();
				typeDTO.setId( (String)subList.get(indx++));
				typeDTO.setName( (String)subList.get(indx++));
				
				fundTypeList.add(typeDTO);
			}
			
			return fundTypeList;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return Collections.emptyList();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean isVariableFundAmount(FundTypeDTO fundTypeDTO)
	{
		if(fundTypeDTO.getName().equalsIgnoreCase("PF"))
		{
			return true;
		}
		return false;
	}
	
	public boolean isValidFundAmount(FundTypeDTO fundTypeDTO, double fundAmount,String empId)
	{
		Double basicSalary = null;
		try {
			sql = EmpFundCommonSQL.GET_EMP_BASIC_SALARY;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[2];
			sd[0] = empId;
			sd[1] = fundTypeDTO.getId();
			
			ArrayList list = dbUtility.executeQuery(sd);
			
			
			if(!list.isEmpty())
			{
			
				ArrayList list1 = (ArrayList) list.get(0);
				if(list1.get(0) != null)
					basicSalary = new Double(list1.get(0).toString());
				
			}
			
			if(fundTypeDTO.getName().equalsIgnoreCase("PF"))
			{
				Double min = new Double((basicSalary.doubleValue() * 12)/100);
				Double max = basicSalary;
				
				if( fundAmount < min.doubleValue() || fundAmount > max.doubleValue())
					return false;
				else
					return true;
			}
			else if(fundTypeDTO.getName().equalsIgnoreCase("GIS"))
			{
				Double amountValue = new Double((basicSalary.doubleValue() * 0.5) / 100);
				if(fundAmount == amountValue.doubleValue())
				{
					return true;
				}
			}
			else if(fundTypeDTO.getName().equalsIgnoreCase("GRATUITY"))
			{
				Double amountValue = new Double((basicSalary.doubleValue() * 5.32) / 100);
				if(fundAmount == amountValue.doubleValue())
				{
					return true;
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public FundRangeDTO getFundTypeRange(FundTypeDTO fundTypeDTO,String empId)
	{
		
		Double basicSalary = null;
		FundRangeDTO fundRangeDTO = null;
		try {
			sql = EmpFundCommonSQL.GET_EMP_BASIC_SALARY;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[2];
			sd[0] = empId;
			sd[1] = fundTypeDTO.getId();
			
			ArrayList list = dbUtility.executeQuery(sd);
			
			
			if(!list.isEmpty())
			{
			
				ArrayList list1 = (ArrayList) list.get(0);
				if(list1.get(0) != null)
					basicSalary = new Double(list1.get(0).toString());
				
			}
			
			if(fundTypeDTO.getName().equalsIgnoreCase("PF"))
			{
				Double min = new Double((basicSalary.doubleValue() * 12)/100);
				Double max = basicSalary;
				
				fundRangeDTO = new FundRangeDTO();
				fundRangeDTO.setMaximum(max.toString());
				fundRangeDTO.setMinimum(min.toString());
			}
			else if(fundTypeDTO.getName().equalsIgnoreCase("GIS"))
			{
				Double amountValue = new Double((basicSalary.doubleValue() * 0.5) / 100);
				fundRangeDTO = new FundRangeDTO();
				fundRangeDTO.setMaximum(amountValue.toString());
				fundRangeDTO.setMinimum(amountValue.toString());
			}
			else if(fundTypeDTO.getName().equalsIgnoreCase("GRATUITY"))
			{
				Double amountValue = new Double((basicSalary.doubleValue() * 5.32) / 100);
				fundRangeDTO = new FundRangeDTO();
				fundRangeDTO.setMaximum(amountValue.toString());
				fundRangeDTO.setMinimum(amountValue.toString());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return fundRangeDTO;
		
	}
	
	public EmpFundDTO getEmpFund(String empId, String fundTypeId)
	{
		
		EmpFundDTO dto = null;
		try {
			sql = EmpFundCommonSQL.GET_EMP_FUND;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[2];
			sd[0] = empId;
			sd[1] = fundTypeId;
			
			ArrayList list = dbUtility.executeQuery(sd);
			
			
			
			if(!list.isEmpty())
			{
				ArrayList list1 = (ArrayList) list.get(0);
				
				dto = new EmpFundDTO();
			
				if(list1.get(0) != null)
					dto.setFundAccountNo(list1.get(0).toString());
				
				if(list1.get(1) != null)
					dto.setFundLocation(list1.get(1).toString());
				
				if(list1.get(2) != null)
					dto.setComponentName(list1.get(2).toString());
				
				if(list1.get(3) != null)
					dto.setFundAmount(new Double(list1.get(3).toString()));
				
				if(list1.get(4) != null)
					dto.setEmpName(list1.get(4).toString());
				
				if(list1.get(5) != null)
					dto.setNomneeName(list1.get(5).toString());
				
				if(list1.get(6) != null)
					dto.setNomneeRelationship(list1.get(6).toString());

				if(list1.get(7) != null)
					dto.setNomneeAddress(list1.get(7).toString());
				
				if(list1.get(8) != null)
					dto.setNomneeAge(list1.get(8).toString());
				
				if(list1.get(9) != null)
					dto.setNomneeSharePCT(list1.get(9).toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	
	public void editEmpFund(EmpFundDTO empFundDTO){
		
		String param[] = new String[3];
		param[0]= empFundDTO.getFundAmount().toString();
		param[1]= empFundDTO.getEmpId();
		param[2]= empFundDTO.getFundTypeId();
		
		sql = EmpFundCommonSQL.EDIT_EMP_FUND;
		
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
			}else {
				dbUtility.rollback();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception x){
				x.getStackTrace();
			}
		}
	}
	
	public FundTypeDTO getFundType(String fundTypeId) {

		FundTypeDTO fundTypeDTO = null;
		
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(EmpFundCommonSQL.GET_FUND_TYPE);
			
			ArrayList resultList = dbUtility.executeQuery(new String[] {
					fundTypeId,
				});
			
			
			
			
			if(!resultList.isEmpty()) {
				ArrayList subList = (ArrayList)resultList.get(0);
				
				fundTypeDTO = new FundTypeDTO();
				
				int indx = 0;
				
				fundTypeDTO.setId( (String)subList.get(indx++) );
				fundTypeDTO.setName( (String)subList.get(indx++) );
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception x){
				x.getStackTrace();
			}
		}
		
		return fundTypeDTO;
	}

}
