package com.wipro.igrs.functiontorolemapping.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.activitymaster.dao.ActivityDAO;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.empreportmgr.bd.EmpReportMgrBD;
import com.wipro.igrs.empreportmgr.dao.empReportMgrDao;
import com.wipro.igrs.empreportmgr.dto.empReportMgrDto;
import com.wipro.igrs.empreportmgr.sql.empReportMgrCommonSql;
import com.wipro.igrs.functionactivitymappingmaster.dao.FnActivityDAO;
import com.wipro.igrs.functionactivitymappingmaster.dto.FnActivityDTO;
import com.wipro.igrs.functiontorolemapping.dto.FunToRoleMappingDto;
import com.wipro.igrs.functiontorolemapping.sql.FunToRoleMappingCommonSql;
import com.wipro.igrs.rolemaster.dto.RoleDTO;
import com.wipro.igrs.treasuryMaster.sql.TreasuryCommonSql;
import com.wipro.igrs.useracctmgmt.dto.UserProfileDTO;

public class FunToRoleMappingDao 
{
	private empReportMgrDao dao;
	private ActivityDAO activityDAO;
	private FnActivityDAO fnActivityDAO;
	private FnActivityDTO fnActivityDTO;
	private FunToRoleMappingDto funToRoleMappingDto;
	private ArrayList funToRoleMappingList;
	private RoleDTO roleDTO;
	private Logger logger = (Logger) Logger.getLogger(FunToRoleMappingDao.class);
	private ArrayList list;
	DBUtility dbUtility = null;
    String sql = null;
    ArrayList columnList = null;
    empReportMgrDto dto = null;
	
	public void addFunToRoleMapping(FunToRoleMappingDto dto)
	{
		 sql = FunToRoleMappingCommonSql.INSERT_FUN_TO_ROLE_MAPPING;
   	 	 String param[] = new String[5];
		 param[0] = dto.getFunctionId();
		 param[1] = dto.getActivityId();
		 param[2] = dto.getRoleId();
		 param[3] = dto.getModuleId();
		 param[4] = dto.getSubModuleId();
		 
			try {
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(sql);
				boolean boo = dbUtility.executeUpdate(param);
				
				if (boo) {
					dbUtility.commit();
				} 
				else {
					dbUtility.rollback();
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//dbUtility.closeConnection();
	}
	
	public void updateFunToRoleMapping(FunToRoleMappingDto dto)
	{
		sql = FunToRoleMappingCommonSql.UPDATE_FUN_TO_ROLE_MAPPING;
  	 	 String param[] = new String[6];
  	 	 param[0] = dto.getFunctionId();
		 param[1] = dto.getActivityId();
		 param[2] = dto.getRoleId();
		 param[3] = dto.getModuleId();
		 param[4] = dto.getSubModuleId();
		 param[5] = dto.getFunToRoleMappingid();
		
			try {
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(sql);
				boolean boo = dbUtility.executeUpdate(param);
				
				if (boo) {
					dbUtility.commit();
				} 
				else {
					dbUtility.rollback();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public ArrayList getAllFunToRoleMapping() throws SQLException,Exception
	{
		 sql = FunToRoleMappingCommonSql.SELECT_FUN_TO_ROLE_MAPPING;
		 funToRoleMappingList = new ArrayList();
		 	
		 dbUtility = new DBUtility();
		 dbUtility.createStatement();

		 ArrayList rowList = dbUtility.executeQuery(sql);
		 for (int i = 0; i < rowList.size(); i++) {
			columnList = (ArrayList) rowList.get(i);
			funToRoleMappingDto = new FunToRoleMappingDto();
			funToRoleMappingDto.setFunctionId(columnList.get(0).toString());
			funToRoleMappingDto.setActivityId(columnList.get(1).toString());
			funToRoleMappingDto.setRoleId(columnList.get(2).toString());
			funToRoleMappingDto.setModuleId(columnList.get(3).toString());
			funToRoleMappingDto.setSubModuleId(columnList.get(4).toString());
			funToRoleMappingDto.setFunToRoleMappingid(columnList.get(5).toString());
			funToRoleMappingList.add(funToRoleMappingDto);
		 	}
		 return funToRoleMappingList;
	}
	
	public FunToRoleMappingDto getFunToRoleMapping(String id) throws SQLException,Exception
	{
			sql = FunToRoleMappingCommonSql.SELECT_FUN_TO_ROLE_MAPPING_BY_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = id;
			ArrayList rowList = dbUtility.executeQuery(sd);
			columnList = (ArrayList) rowList.get(0);
			funToRoleMappingDto = new FunToRoleMappingDto();
			funToRoleMappingDto.setFunctionId(columnList.get(0).toString());
			funToRoleMappingDto.setActivityId(columnList.get(1).toString());
			funToRoleMappingDto.setRoleId(columnList.get(2).toString());
			funToRoleMappingDto.setModuleId(columnList.get(3).toString());
			funToRoleMappingDto.setSubModuleId(columnList.get(4).toString());
			funToRoleMappingDto.setFunToRoleMappingid(columnList.get(5).toString());
			
			return funToRoleMappingDto;
	}
	// get all properties in funToRoleMapping object with id and name also 
	public ArrayList getFunToRoleMappingDetailed() throws SQLException,Exception
	{
		 sql = FunToRoleMappingCommonSql.SELECT_FUN_TO_ROLE_MAPPING_DETAILED;
		 funToRoleMappingList = new ArrayList();
		 	
		 dbUtility = new DBUtility();
		 dbUtility.createStatement();

		 ArrayList rowList = dbUtility.executeQuery(sql);
		 for (int i = 0; i < rowList.size(); i++) {
			columnList = (ArrayList) rowList.get(i);
			funToRoleMappingDto = new FunToRoleMappingDto();
			funToRoleMappingDto.setFunctionId(columnList.get(0).toString());
			funToRoleMappingDto.setActivityId(columnList.get(1).toString());
			funToRoleMappingDto.setRoleId(columnList.get(2).toString());
			funToRoleMappingDto.setModuleId(columnList.get(3).toString());
			funToRoleMappingDto.setSubModuleId(columnList.get(4).toString());
			funToRoleMappingDto.setFunToRoleMappingid(columnList.get(5).toString());
			funToRoleMappingDto.setModuleName(columnList.get(6).toString());
			funToRoleMappingDto.setActivityName(columnList.get(7).toString());
			funToRoleMappingDto.setFunctionName(columnList.get(8).toString());
			funToRoleMappingDto.setRoleName(columnList.get(9).toString());
			funToRoleMappingDto.setSubModuleName(columnList.get(10).toString());
			
			
			
			funToRoleMappingList.add(funToRoleMappingDto);
		 	}
		 return funToRoleMappingList;
	}
	
	public boolean isFunToRoleMappingExists(FunToRoleMappingDto dto) throws SQLException,Exception
	{
		 boolean result = true; 
    	 sql = FunToRoleMappingCommonSql.SELECT_FUN_TO_ROLE_MAPPING_EXISTS;
    	 String param[] = new String[5];
	 	 param[0] = dto.getFunctionId();
		 param[1] = dto.getActivityId();
		 param[2] = dto.getRoleId();
		 param[3] = dto.getModuleId();
		 param[4] = dto.getSubModuleId();
	
		dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(sql);
			
		ArrayList list = dbUtility.executeQuery(param);
		if(list.isEmpty())
		{
			result=false;
		}
		
		return result;
	}
	
	public ArrayList getAllRoles() throws SQLException,Exception
	{
		fnActivityDAO = new FnActivityDAO();
		return fnActivityDAO.getRoleList();
	}
	
	public ArrayList getAllModules() throws SQLException,Exception
	{
		fnActivityDAO = new FnActivityDAO();
		return fnActivityDAO.getModuleList();
	}
	
	//commented by shruti--in order to avoid compilation issue---9 sep 2014
	/*public ArrayList getAllActivities() throws SQLException,Exception
	{
		fnActivityDAO = new FnActivityDAO();
		return fnActivityDAO.getActivityList();
	}
	
	public ArrayList getAllSubModules() throws SQLException,Exception
	{
		fnActivityDAO = new FnActivityDAO();
		return fnActivityDAO.getSubmoduleList();
	}
	
	public ArrayList getAllFunctions() throws SQLException,Exception
	{
		fnActivityDAO = new FnActivityDAO();
		return fnActivityDAO.getFunctionList();
	}*/
	//end
	public ArrayList getSubModulesForThisModule(String module) throws SQLException,Exception
	{
		sql = FunToRoleMappingCommonSql.SELECT_SUBMODULES_TO_THIS_MODULE;
		list = new ArrayList();
	 
		dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(sql);
		String sd[] = new String[1];
		sd[0] = module;
		ArrayList rowList = dbUtility.executeQuery(sd);
		
		for (int i = 0; i < rowList.size(); i++) {
			columnList = (ArrayList) rowList.get(i);
			fnActivityDTO = new FnActivityDTO();
			fnActivityDTO.setValue(columnList.get(0).toString());
			fnActivityDTO.setName(columnList.get(1).toString());
			list.add(fnActivityDTO);
		}
		return list;
	}
	
	public ArrayList getFunctionsForThisSubmodule(String subModule) throws SQLException,Exception
	{
		sql = FunToRoleMappingCommonSql.SELECT_FUNCTIONS_TO_THIS_SUBMODULE;
		list = new ArrayList();
	 
		dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(sql);
		String sd[] = new String[1];
		sd[0] = subModule;
		ArrayList rowList = dbUtility.executeQuery(sd);
		
		for (int i = 0; i < rowList.size(); i++) {
			columnList = (ArrayList) rowList.get(i);
			fnActivityDTO = new FnActivityDTO();
			fnActivityDTO.setValue(columnList.get(0).toString());
			fnActivityDTO.setName(columnList.get(1).toString());
			list.add(fnActivityDTO);
		}
		return list;
	}
	// delete a relation between role and function
	public void deleteFunToRoleMapping(String id) throws SQLException,Exception
	{
		sql = FunToRoleMappingCommonSql.DELETE_FUN_TO_ROLE_MAPPING;
		dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(sql);
		String sd[] = new String[1];
		sd[0] = id;
		dbUtility.executeUpdate(sd);
	}
	
}
