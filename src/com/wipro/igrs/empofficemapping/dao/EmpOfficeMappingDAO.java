package com.wipro.igrs.empofficemapping.dao;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.empofficemapping.dto.DeptDTO;
import com.wipro.igrs.empofficemapping.dto.EmpOfficeMappingDTO;
import com.wipro.igrs.empofficemapping.dto.OfficeDTO;
import com.wipro.igrs.empofficemapping.dto.RoleDTO;
import com.wipro.igrs.empofficemapping.sql.EmpOfficeMappingCommonSQL;
import com.wipro.igrs.hrpayroll.hrpl.dto.ArrearsDTO;
import com.wipro.igrs.municipalbodymaster.dto.MunicipalBodyDTO;
import com.wipro.igrs.municipalbodymaster.sql.MunicipalBodyCommonSQL;
import com.wipro.igrs.reconciliation.action.DetailedHeadDetails;

public class EmpOfficeMappingDAO {
	
	private DBUtility dbUtility = null;
	private Logger logger = (Logger) Logger.getLogger(EmpOfficeMappingDAO.class);
	
	public ArrayList getAllEmpOfficeMappings() {
		ArrayList result = new ArrayList();
		ArrayList subList = null;
		String empName = "";
		EmpOfficeMappingDTO dto = null;
		try {
			String sql = EmpOfficeMappingCommonSQL.SELECT_ALL_EMP_OFFICE_MAPPINGS;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			ArrayList mappingList = dbUtility.executeQuery(sql);
			for (int i = 0; i < mappingList.size(); i++) {
				subList = (ArrayList) mappingList.get(i);
				if ((subList != null) && (!subList.isEmpty())) {
					dto = new EmpOfficeMappingDTO();
					
					if (subList.get(0) != null)
						dto.setEmpId(subList.get(0).toString());
					else
						dto.setEmpId("");
					if (subList.get(1) != null )
						empName = subList.get(1).toString();					
					if (subList.get(2) != null )
						empName += (" " +subList.get(2).toString());
					if (subList.get(3) != null )
						empName += (" " +subList.get(3).toString());
					dto.setEmpName(empName);
					
					if (subList.get(4) != null)
						dto.setOfficeId(subList.get(4).toString());
					else
						dto.setOfficeId("");
					
					if (subList.get(5) != null)
						dto.setOfficeName(subList.get(5).toString());
					else
						dto.setOfficeName("");
					
//					if (subList.get(6) != null)
//						dto.setDeptId(subList.get(6).toString());
//					else
//						dto.setDeptId("");
//					
//					if (subList.get(7) != null)
//						dto.setDeptName(subList.get(7).toString());
//					else
//						dto.setDeptName("");
//					
//					if (subList.get(8) != null)
//						dto.setRoleId(subList.get(8).toString());
//					else
//						dto.setRoleId("");
//					
//					if (subList.get(9) != null)
//						dto.setRoleName(subList.get(9).toString());
//					else
//						dto.setRoleName("");
					result.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public void deleteEmpOfficeMapping(EmpOfficeMappingDTO bean) {
		String empId = bean.getEmpId();
		String sql = EmpOfficeMappingCommonSQL.DELETE_EMP_OFFICE_MAPPING;
		String param[] = new String[] {empId};

		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean queryExcuted = dbUtility.executeUpdate(param);
			
			if (queryExcuted) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
			}
				 
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteEmpOfficeMappings(String[] mappingsIds) {
		String sql = EmpOfficeMappingCommonSQL.DELETE_EMP_OFFICE_MAPPING;
		String param[] = null;
		boolean queryExcuted = false;
		try {
			dbUtility = new DBUtility();
			for (int i = 0; i < mappingsIds.length; i++) {
				dbUtility.createPreparedStatement(sql);
				param = new String[] {mappingsIds[i]};
				queryExcuted = dbUtility.executeUpdate(param);
			}
			
			if (queryExcuted) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
			}
				 
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isEmpExist(String empId) {
		
		boolean result = false;
		try {
			
			String sql = EmpOfficeMappingCommonSQL.SELECT_EMP_BY_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			ArrayList list = dbUtility.executeQuery(new String[]{empId});
			if ((list!= null) && (!list.isEmpty())) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean isMappingExist(EmpOfficeMappingDTO bean) {
		String empId = bean.getEmpId();
		
		if (empId == null) {
			return false;
		}
		boolean result = false;
		try {
			String sql = EmpOfficeMappingCommonSQL.SELECT_MAPPING_BY_EMP_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			ArrayList list = dbUtility.executeQuery(new String[]{empId});
			
			if ((list!= null) && (!list.isEmpty())) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public String getEmpName(String empId) {
		String result = "";
		ArrayList subList = null;
		try {
			String sql = EmpOfficeMappingCommonSQL.SELECT_EMP_BY_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			ArrayList list = dbUtility.executeQuery(new String[]{empId});
			if ((list!= null) && (!list.isEmpty())) {
				subList = (ArrayList) list.get(0);
					if ((subList != null) && (!subList.isEmpty())) {
						//result = true;
						if (subList.get(0) != null )
							result += subList.get(0).toString();					
						if (subList.get(1) != null )
							result += (" " + subList.get(1).toString());
						if (subList.get(2) != null )
							result += (" " + subList.get(2).toString());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public ArrayList getAllRoles() {
		ArrayList result = new ArrayList();
		ArrayList subList = null;
		RoleDTO dto = null;
		try {
			String sql = EmpOfficeMappingCommonSQL.SELECT_ALL_ROLES;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			ArrayList mappingList = dbUtility.executeQuery(sql);
			for (int i = 0; i < mappingList.size(); i++) {
				subList = (ArrayList) mappingList.get(i);
				if ((subList != null) && (!subList.isEmpty())) {
					dto = new RoleDTO();
					if (subList.get(0) != null)
						dto.setRoleId(subList.get(0).toString());
					else
						dto.setRoleId("");
					if (subList.get(1) != null)
						dto.setRoleName(subList.get(1).toString());
					else
						dto.setRoleName("");
					result.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public ArrayList getAllOffices() {
		ArrayList result = new ArrayList();
		ArrayList subList = null;
		OfficeDTO dto = null;
		try {
			String sql = EmpOfficeMappingCommonSQL.SELECT_ALL_OFFICES;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			ArrayList mappingList = dbUtility.executeQuery(sql);
			for (int i = 0; i < mappingList.size(); i++) {
				subList = (ArrayList) mappingList.get(i);
				if ((subList != null) && (!subList.isEmpty())) {
					dto = new OfficeDTO();
					if (subList.get(0) != null)
						dto.setOfficeId(subList.get(0).toString());
					else
						dto.setOfficeId("");
					if (subList.get(1) != null)
						dto.setOfficeName(subList.get(1).toString());
					else
						dto.setOfficeName("");
					result.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public EmpOfficeMappingDTO getEmpMappingById(EmpOfficeMappingDTO bean) {
		String empId = bean.getEmpId();
		EmpOfficeMappingDTO result = null;
		try {
			String sql = EmpOfficeMappingCommonSQL.SELECT_MAPPING_BY_EMP_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			ArrayList list = dbUtility.executeQuery(new String[] {empId});
			if ((list != null) && (!list.isEmpty())) {
				ArrayList subList = (ArrayList) list.get(0);
				result = new EmpOfficeMappingDTO();
				result.setEmpId(empId);
				if ((subList != null) && (!subList.isEmpty())) {
					if (subList.get(0) != null)
						result.setOfficeId(subList.get(0).toString());
					else
						result.setOfficeId("");
					if (subList.get(1) != null)
						result.setRoleId(subList.get(1).toString());
					else
						result.setRoleId("");
					if (subList.get(2) != null)
						result.setDeptId(subList.get(2).toString());
					else
						result.setDeptId("");
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public ArrayList getAllDepts() {
		ArrayList result = new ArrayList();
		ArrayList subList = null;
		DeptDTO dto = null;
		try {
			String sql = EmpOfficeMappingCommonSQL.SELECT_ALL_DEPTS;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			ArrayList mappingList = dbUtility.executeQuery(sql);
			for (int i = 0; i < mappingList.size(); i++) {
				subList = (ArrayList) mappingList.get(i);
				if ((subList != null) && (!subList.isEmpty())) {
					dto = new DeptDTO();
					if (subList.get(0) != null)
						dto.setDeptId(subList.get(0).toString());
					else
						dto.setDeptId("");
					if (subList.get(1) != null)
						dto.setDeptName(subList.get(1).toString());
					else
						dto.setDeptName("");
					result.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public void addEmpOfficeMapping(EmpOfficeMappingDTO bean) {
		String sql = EmpOfficeMappingCommonSQL.INSERT_EMP_OFFICE_MAPPING;
		String param[] = new String[2];
		param[0] = bean.getEmpId();
		param[1] = bean.getOfficeId();
//		param[2] = bean.getRoleId();
//		param[3] = bean.getDeptId();
		
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
			}
				 
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void updateEmpOfficeMapping(EmpOfficeMappingDTO bean) {
		String sql = EmpOfficeMappingCommonSQL.UPDATE_EMP_OFFICE_MAPPING;
		String param[] = new String[2];
		param[0] = bean.getOfficeId();
//		param[1] = bean.getRoleId();
//		param[2] = bean.getDeptId();
		param[1] = bean.getEmpId();
	
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean queryExcuted = dbUtility.executeUpdate(param);
			
			if (queryExcuted) {
				dbUtility.commit();
			}else {
				dbUtility.rollback();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
 