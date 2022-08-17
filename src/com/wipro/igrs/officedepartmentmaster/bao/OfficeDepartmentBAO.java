/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   OfficeDepartmentBAO.java
 * Author      :   Sayed Taha
 * Description :   Represents the Business Access Objects Class for Department Master.
 * Created Date   :   Aug 11, 2008         	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.officedepartmentmaster.bao;
import java.util.ArrayList;
import java.util.Date;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.officedepartmentmaster.dao.OfficeDepartmentDAO;
import com.wipro.igrs.officedepartmentmaster.departmentexception.DepartmentNameAlreadyExists;
import com.wipro.igrs.officedepartmentmaster.dto.DeptDTO;
import com.wipro.igrs.officedepartmentmaster.sql.OfficeDeptartmentCommonSQL;		
public class OfficeDepartmentBAO {
	     private OfficeDepartmentDAO dao;
	     public OfficeDepartmentBAO(){
	    	 dao=new OfficeDepartmentDAO();
	     }
		/**
		 * To get all Departments in the DB.
		 * @return ArrayList all of result
		 */
		public ArrayList getAllDepartments() {
			return dao.getAllDepartments();
		}
		
		
		public DeptDTO getDeptByID(String deptID){
			return dao.getDeptByID(deptID);
		}
	    
		
		public void deleteDepartment(String deptID){
	    	dao.deleteDepartment(deptID);
	    }
		
		
		public void addDepartment(DeptDTO deptDTO) throws DepartmentNameAlreadyExists {
			if(dao.isDepartmentExists(deptDTO.getDeptName())){
				throw new DepartmentNameAlreadyExists();
				
			}else{
				dao.addDepartment(deptDTO);	
			}
		}
		
	    public void updateDepartment(DeptDTO deptDTO) throws DepartmentNameAlreadyExists{
	    	if(dao.isDepartmentExists(deptDTO.getDeptName())){
	    		DeptDTO dept=dao.getDeptByName(deptDTO.getDeptName());
	    		
	    		if(dept.getDeptID().equals(deptDTO.getDeptID())){
	    			dao.updateDepartment(deptDTO);
	    		}else{
	    			throw new DepartmentNameAlreadyExists();
	    		}
	    	}else{
	    		dao.updateDepartment(deptDTO);
	    	}
	}
}
