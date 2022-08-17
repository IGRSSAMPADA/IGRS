package com.wipro.igrs.empreportmgr.bd;

import java.sql.SQLException;
import java.util.ArrayList;

import com.wipro.igrs.empreportmgr.bo.EmpReportMgrBO;
import com.wipro.igrs.empreportmgr.dto.empReportMgrDto;

public class EmpReportMgrBD 
{

	private EmpReportMgrBO reportMgrBO;
	
	public EmpReportMgrBD()
	{
		reportMgrBO = new EmpReportMgrBO();
	}
	
	public void addEmpReportManager(empReportMgrDto dto) throws SQLException,Exception
	{
		reportMgrBO.addEmpReportManager(dto);
	}
	
	public void updateEmpReportManager(empReportMgrDto dto)
	{
		reportMgrBO.updateEmpReportManager(dto);
	}
	
	public ArrayList getAllRoles() throws SQLException,Exception
	{
		return reportMgrBO.getAllRoles();
	}
	
	public ArrayList getUsersForThisRole(String roleID) throws SQLException,Exception
	{
		return reportMgrBO.getUsersForThisRole(roleID);
	}
	
	public empReportMgrDto getEmpReportManager(String empId) throws SQLException,Exception
	{
		return reportMgrBO.getEmpReportManager(empId);
	}
	
	public boolean EmpHasMgr(String empId) throws SQLException,Exception
	{
		return reportMgrBO.EmpHasMgr(empId);
	}
	
}
