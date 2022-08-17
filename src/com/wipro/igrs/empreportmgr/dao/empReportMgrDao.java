package com.wipro.igrs.empreportmgr.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.empreportmgr.dto.empReportMgrDto;
import com.wipro.igrs.empreportmgr.sql.empReportMgrCommonSql;
import com.wipro.igrs.rolemaster.dto.RoleDTO;
import com.wipro.igrs.treasuryMaster.sql.TreasuryCommonSql;
import com.wipro.igrs.useracctmgmt.dto.UserProfileDTO;

public class empReportMgrDao 
{
	private UserProfileDTO userProfileDTO;
	private ArrayList empReportMgrList;
	private ArrayList roles;
	private ArrayList users;
	private RoleDTO roleDTO;
	private Logger logger = (Logger) Logger.getLogger(empReportMgrDao.class);
    DBUtility dbUtility = null;
    String sql = null;
    ArrayList columnList = null;
    empReportMgrDto dto = null;
	
	public void addEmpReportManager(empReportMgrDto dto)
	{
		 sql = empReportMgrCommonSql.INSERT_EMP_REPORT_MGR;
   	 	 String param[] = new String[4];
		 param[0] = dto.getEmpId();
		 param[1] = dto.getEmpSupervisorName();
		 param[2] = dto.getEmpSupervisorRole();
		 param[3] = dto.getEmpSupervisorId();
		
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
	
	public void updateEmpReportManager(empReportMgrDto dto)
	{
		sql = empReportMgrCommonSql.UPDATE_EMP_REPORT_MGR;
  	 	 String param[] = new String[4];
		 param[0] = dto.getEmpSupervisorName();
		 param[1] = dto.getEmpSupervisorRole();
		 param[2] = dto.getEmpSupervisorId();
		 param[3] = dto.getEmpId();
		
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
	
	public ArrayList getAllRoles() throws SQLException,Exception
	{
		 sql = empReportMgrCommonSql.SELECT_ROLE_MASTER;
		 roles = new ArrayList();
		 
			dbUtility = new DBUtility();
			dbUtility.createStatement();

			ArrayList rowList = dbUtility.executeQuery(sql);
			for (int i = 0; i < rowList.size(); i++) {
				columnList = (ArrayList) rowList.get(i);
				roleDTO = new RoleDTO();
				roleDTO.setRoleId(columnList.get(0).toString());
				roleDTO.setRoleName(columnList.get(1).toString());
				roleDTO.setRoleDesc(columnList.get(2).toString());
				roleDTO.setRoleStatus(columnList.get(3).toString());
				roles.add(roleDTO);
			}
		return roles;
	}

	public ArrayList getUsersForThisRole(String roleID) throws SQLException,Exception
	{
			sql = empReportMgrCommonSql.SELECT_USER_MASTER;
			users = new ArrayList();
		 
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = roleID;
			ArrayList rowList = dbUtility.executeQuery(sd);
			
			for (int i = 0; i < rowList.size(); i++) {
				columnList = (ArrayList) rowList.get(i);
				userProfileDTO = new UserProfileDTO();
				userProfileDTO.setUserId(columnList.get(0).toString());
				userProfileDTO.setFirstName(columnList.get(1).toString());
				userProfileDTO.setMiddleName(columnList.get(2).toString());
				userProfileDTO.setLastName(columnList.get(3).toString());
				//userProfileDTO.setLastName(columnList.get(3).toString());
				
				users.add(userProfileDTO);
			}
		return users;
		
	}

	public empReportMgrDto getEmpReportManager(String empId) throws SQLException,Exception
	{
			sql = empReportMgrCommonSql.SELECT_EMP_REPORT_MGR_BY_ID;
			//users = new ArrayList();
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = empId;
			ArrayList rowList = dbUtility.executeQuery(sd);
			columnList = (ArrayList) rowList.get(0);
			dto = new empReportMgrDto();
			dto.setEmpId(columnList.get(0).toString());
			dto.setEmpSupervisorName(columnList.get(1).toString());
			dto.setEmpSupervisorRole(columnList.get(2).toString());
			dto.setEmpSupervisorId(columnList.get(3).toString());
			
			return dto;
	}
	
	public boolean EmpHasMgr(String empId) throws SQLException,Exception
	{
		boolean result = true; 
    	sql = empReportMgrCommonSql.SELECT_EMP_REPORT_MGR_BY_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = empId;
			ArrayList list = dbUtility.executeQuery(sd);
			if(list.isEmpty())
			{
				result=false;
			}
		
		return result;
	}
	
	public String getUserNameById(String id) throws SQLException,Exception
	{
		sql = empReportMgrCommonSql.SELECT_EMP_NAME_BY_ID;
		
		dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(sql);
		String sd[] = new String[1];
		sd[0] = id;
		ArrayList rowList = dbUtility.executeQuery(sd);
		columnList = (ArrayList) rowList.get(0);
		
		return columnList.get(0).toString();
	}

	
}
