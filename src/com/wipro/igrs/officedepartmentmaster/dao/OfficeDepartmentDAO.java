/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   OfficeDepartmentDAO.java
 * Author      :   Sayed Taha
 * Description :   Represents the DAO Class for Department Master.
 * Created Date   :   Aug 11, 2008         	 		 
 * --------------------------------------------------------------------------------
 */
	
package com.wipro.igrs.officedepartmentmaster.dao;

import java.util.ArrayList;
import java.util.Date;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.officedepartmentmaster.dto.DeptDTO;
import com.wipro.igrs.officedepartmentmaster.sql.OfficeDeptartmentCommonSQL;

public class OfficeDepartmentDAO {
	/**
	 * To get all Departments in the DB.
	 * @return ArrayList all of result
	 */
	public ArrayList getAllDepartments() {
		ArrayList result = new ArrayList();
		ArrayList subList = null;
		DeptDTO dto = null;
		DBUtility dbUtility = null;
		try {
			String sql = OfficeDeptartmentCommonSQL.SELECT_ALL_OFFICE_DEPT_MASTER;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			ArrayList depsList = dbUtility.executeQuery(sql);
			for (int i = 0; i < depsList.size(); i++) {
				subList = (ArrayList) depsList.get(i);
				dto = new DeptDTO();
				
				dto.setDeptID(subList.get(0).toString());
				dto.setDeptName(subList.get(1).toString());
				dto.setDeptDesc(subList.get(2).toString());
				dto.setDeptStatus(subList.get(3).toString());
				dto.setCreatedBy(subList.get(4).toString());
				dto.setCreatedDate(new Date(subList.get(5).toString()));
				dto.setUpdatedBy(subList.get(6).toString());
				dto.setUpdateDate(new Date(subList.get(7).toString()));
				result.add(dto);
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public DeptDTO getDeptByName(String deptName){
		DeptDTO dto = null;
		DBUtility dbUtility=null;
		String [] param=new String[1];
		param[0]=deptName;
		try {
			String sql = OfficeDeptartmentCommonSQL.SELECT_OFFICE_DEPT_BYNAME;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			
			ArrayList municipalList = dbUtility.executeQuery(param);
			ArrayList subList = (ArrayList) municipalList.get(0);
			
			dto = new DeptDTO();
			dto.setDeptID(subList.get(0).toString());
			dto.setDeptName(subList.get(1).toString());
			dto.setDeptDesc(subList.get(2).toString());
			dto.setDeptStatus(subList.get(3).toString());
			dto.setCreatedBy(subList.get(4).toString());
			dto.setCreatedDate(new Date(subList.get(5).toString()));
			dto.setUpdatedBy(subList.get(6).toString());
			dto.setUpdateDate(new Date(subList.get(7).toString()));
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	public DeptDTO getDeptByID(String deptID){
		DeptDTO dto = null;
		DBUtility dbUtility=null;
		String [] param=new String[1];
		param[0]=deptID;
		try {
			String sql = OfficeDeptartmentCommonSQL.SELECT_OFFICE_DEPT_BYID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			
			ArrayList municipalList = dbUtility.executeQuery(param);
			ArrayList subList = (ArrayList) municipalList.get(0);
			
			dto = new DeptDTO();
			dto.setDeptID(subList.get(0).toString());
			dto.setDeptName(subList.get(1).toString());
			dto.setDeptDesc(subList.get(2).toString());
			dto.setDeptStatus(subList.get(3).toString());
			dto.setCreatedBy(subList.get(4).toString());
			dto.setCreatedDate(new Date(subList.get(5).toString()));
			dto.setUpdatedBy(subList.get(6).toString());
			dto.setUpdateDate(new Date(subList.get(7).toString()));
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	public void deleteDepartment(String deptID){
    	String sql = OfficeDeptartmentCommonSQL.DELETE_OFFICE_DEPT_MASTER;
		String param[] = new String[1];
		param[0]=deptID;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean succeed = dbUtility.executeUpdate(param);
			if (succeed) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
			}
				 
		} catch (Exception e) {
			e.getStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception e) {
				e.getStackTrace();
			}
		}
    }
	public void addDepartment(DeptDTO deptDTO) {
		String sql = OfficeDeptartmentCommonSQL.INSERT_OFFICE_DEPT_MASTER;
		DBUtility dbUtility = null;
		String param[] = new String[4];
		param[0] = deptDTO.getDeptName();
		param[1] = deptDTO.getDeptDesc();
		param[2] = deptDTO.getCreatedBy();
		param[3] = deptDTO.getUpdatedBy();
		
		
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			
			boolean succeed = dbUtility.executeUpdate(param);
			if (succeed) {
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
    public boolean isDepartmentExists(String deptName){
    	boolean found = false;
    	DeptDTO dto = null;
		DBUtility dbUtility=null;
		String [] param=new String[1];
		param[0]=deptName;
		try {
				String sql = OfficeDeptartmentCommonSQL.SELECT_OFFICE_DEPT_BYNAME;
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(sql);
				
				ArrayList municipalList = dbUtility.executeQuery(param);
				ArrayList subList = (ArrayList) municipalList.get(0);
				if(subList.isEmpty()){
					found=false;
				}else{
					found=true;
				}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception e){
				e.getStackTrace();
			}
		}
		return found;
    }
    public void updateDepartment(DeptDTO deptDTO){
    	String sql = OfficeDeptartmentCommonSQL.UPDATE_OFFICE_DEPT_MASTER;
		DBUtility dbUtility = null;
		String param[] = new String[5];
		param[0] = deptDTO.getDeptName();
		param[1] = deptDTO.getDeptDesc();
		param[2] = deptDTO.getDeptStatus();
		param[3] = deptDTO.getUpdatedBy();
		param[4] = deptDTO.getDeptID();
		
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean succeed = dbUtility.executeUpdate(param);
			if (succeed) {
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
}