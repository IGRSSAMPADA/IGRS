package com.wipro.igrs.functiontorolemapping.bo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.empreportmgr.dao.empReportMgrDao;
import com.wipro.igrs.empreportmgr.dto.empReportMgrDto;
import com.wipro.igrs.functionactivitymappingmaster.dao.FnActivityDAO;
import com.wipro.igrs.functiontorolemapping.dao.FunToRoleMappingDao;
import com.wipro.igrs.functiontorolemapping.dto.FunToRoleMappingDto;
import com.wipro.igrs.functiontorolemapping.sql.FunToRoleMappingCommonSql;

public class FunToRoleMappingBO 
{

	private FunToRoleMappingDao funToRoleMappingDao;
	
	public FunToRoleMappingBO()
	{
		funToRoleMappingDao = new FunToRoleMappingDao();
	}
	
	public void addFunToRoleMapping(FunToRoleMappingDto dto) throws SQLException,Exception
	{
		if(!funToRoleMappingDao.isFunToRoleMappingExists(dto))
		{
			funToRoleMappingDao.addFunToRoleMapping(dto);
		}
		else 
		return; //already exists exception will be thrown here ...	
	}
	
	public void updateFunToRoleMapping(FunToRoleMappingDto dto)
	{
		funToRoleMappingDao.updateFunToRoleMapping(dto);
	}
	
	public ArrayList getAllFunToRoleMapping() throws SQLException,Exception
	{
		return funToRoleMappingDao.getAllFunToRoleMapping();
	}
	
	public ArrayList getFunToRoleMappingDetailed() throws SQLException,Exception
	{
		return funToRoleMappingDao.getFunToRoleMappingDetailed();
	}
	
	public FunToRoleMappingDto getFunToRoleMapping(String id) throws SQLException,Exception
	{
		return funToRoleMappingDao.getFunToRoleMapping(id);
	}
	
	public boolean isFunToRoleMappingExists(FunToRoleMappingDto dto) throws SQLException,Exception
	{
		return funToRoleMappingDao.isFunToRoleMappingExists(dto);
	}
	
	public ArrayList getAllRoles() throws SQLException,Exception
	{
		
		return funToRoleMappingDao.getAllRoles();
	}
	
	public ArrayList getAllModules() throws SQLException,Exception
	{
		return funToRoleMappingDao.getAllModules();
	}
	
	//commented by shruti in order to avoid compilation issues---9 sep 2014
	/*public ArrayList getAllActivities() throws SQLException,Exception
	{
		return funToRoleMappingDao.getAllActivities();
	}
	
	public ArrayList getAllSubModules() throws SQLException,Exception
	{
		return funToRoleMappingDao.getAllSubModules();
	}
	
	public ArrayList getAllFunctions() throws SQLException,Exception
	{
		return funToRoleMappingDao.getAllFunctions();
	}*/
	//end
	
	public ArrayList getSubModulesForThisModule(String module) throws SQLException,Exception
	{
		return funToRoleMappingDao.getSubModulesForThisModule(module);
	}
	
	public ArrayList getFunctionsForThisSubmodule(String subModule) throws SQLException,Exception
	{
		return funToRoleMappingDao.getFunctionsForThisSubmodule(subModule);
	}
	
	public void deleteFunToRoleMapping(String id) throws SQLException,Exception
	{
		funToRoleMappingDao.deleteFunToRoleMapping(id);
	}
	/*
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
	*/
}
