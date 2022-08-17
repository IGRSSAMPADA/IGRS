package com.wipro.igrs.empreportmgr.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import com.wipro.igrs.empreportmgr.dao.empReportMgrDao;
import com.wipro.igrs.empreportmgr.dto.empReportMgrDto;

public class EmpReportMgrBO 
{

	private empReportMgrDao reportMgrDao;
	
	public EmpReportMgrBO()
	{
		reportMgrDao = new empReportMgrDao();
	}
	
	public void addEmpReportManager(empReportMgrDto dto) throws SQLException,Exception
	{
		dto.setEmpSupervisorName(reportMgrDao.getUserNameById(dto.getEmpSupervisorId()));
		
		if(!reportMgrDao.EmpHasMgr(dto.getEmpId()))
			reportMgrDao.addEmpReportManager(dto);
		else
			updateEmpReportManager(dto);
	}
	
	public void updateEmpReportManager(empReportMgrDto dto)
	{
		reportMgrDao.updateEmpReportManager(dto);
	}
	
	public ArrayList getAllRoles() throws SQLException,Exception
	{
		return reportMgrDao.getAllRoles();
	}
	
	public ArrayList getUsersForThisRole(String roleID) throws SQLException,Exception
	{
		return reportMgrDao.getUsersForThisRole(roleID);
	}
	
	public empReportMgrDto getEmpReportManager(String empId) throws SQLException,Exception
	{
		return reportMgrDao.getEmpReportManager(empId);
	}
	
	public boolean EmpHasMgr(String empId) throws SQLException,Exception
	{
		return reportMgrDao.EmpHasMgr(empId);
	}
	
}
