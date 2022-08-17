package com.wipro.igrs.functiontorolemapping.bd;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wipro.igrs.empreportmgr.bo.EmpReportMgrBO;
import com.wipro.igrs.empreportmgr.dto.empReportMgrDto;
import com.wipro.igrs.functiontorolemapping.bo.FunToRoleMappingBO;
import com.wipro.igrs.functiontorolemapping.dto.FunToRoleMappingDto;

public class FunToRoleMappingBD 
{

	private FunToRoleMappingBO funToRoleMappingBO;
	
	public FunToRoleMappingBD()
	{
		funToRoleMappingBO = new FunToRoleMappingBO();
	}
	
	public void addFunToRoleMapping(FunToRoleMappingDto dto) throws SQLException,Exception
	{
		funToRoleMappingBO.addFunToRoleMapping(dto);
	}
	
	public ArrayList getFunToRoleMappingDetailed() throws SQLException,Exception
	{
		return funToRoleMappingBO.getFunToRoleMappingDetailed();
	}
	
	public void updateFunToRoleMapping(FunToRoleMappingDto dto)
	{
		funToRoleMappingBO.updateFunToRoleMapping(dto);
	}
	
	public ArrayList getAllFunToRoleMapping() throws SQLException,Exception
	{
		return funToRoleMappingBO.getAllFunToRoleMapping();
	}
	
	public FunToRoleMappingDto getFunToRoleMapping(String id) throws SQLException,Exception
	{
		return funToRoleMappingBO.getFunToRoleMapping(id);
	}
	
	public boolean isFunToRoleMappingExists(FunToRoleMappingDto dto) throws SQLException,Exception
	{
		return funToRoleMappingBO.isFunToRoleMappingExists(dto);
	}
	
	public ArrayList getAllRoles() throws SQLException,Exception
	{
		
		return funToRoleMappingBO.getAllRoles();
	}
	
	public ArrayList getAllModules() throws SQLException,Exception
	{
		return funToRoleMappingBO.getAllModules();
	}
	
	//commented by shruti in order to avoid compilation issue
	/*public ArrayList getAllActivities() throws SQLException,Exception
	{
		return funToRoleMappingBO.getAllActivities();
	}
	
	public ArrayList getAllSubModules() throws SQLException,Exception
	{
		return funToRoleMappingBO.getAllSubModules();
	}
	
	public ArrayList getAllFunctions() throws SQLException,Exception
	{
		return funToRoleMappingBO.getAllFunctions();
	}*/
	//end
	
	public ArrayList getSubModulesForThisModule(String module) throws SQLException,Exception
	{
		return funToRoleMappingBO.getSubModulesForThisModule(module);
	}
	
	public ArrayList getFunctionsForThisSubmodule(String subModule) throws SQLException,Exception
	{
		return funToRoleMappingBO.getFunctionsForThisSubmodule(subModule);
	}
	
	public void deleteFunToRoleMapping(String id) throws SQLException,Exception
	{
		funToRoleMappingBO.deleteFunToRoleMapping(id);
	}
}
