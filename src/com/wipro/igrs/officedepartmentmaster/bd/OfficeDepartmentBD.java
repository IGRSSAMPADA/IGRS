/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   OfficeDepartmentBD.java
 * Author      :   Sayed Taha
 * Description :   Represents the Business delegate Class for Department Master.
 * Created Date   :   Aug 11, 2008         	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.officedepartmentmaster.bd;

import java.util.ArrayList;

import com.wipro.igrs.officedepartmentmaster.bao.OfficeDepartmentBAO;
import com.wipro.igrs.officedepartmentmaster.departmentexception.DepartmentNameAlreadyExists;
import com.wipro.igrs.officedepartmentmaster.dto.DeptDTO;

public class OfficeDepartmentBD {
	private OfficeDepartmentBAO bao=new OfficeDepartmentBAO();
	public ArrayList getAllDepartments() {
		return bao.getAllDepartments();
	}
	
	
	public DeptDTO getDeptByID(String deptID){
		return bao.getDeptByID(deptID);
	}
    
	
	public void deleteDepartment(String deptID){
		bao.deleteDepartment(deptID);
    }
	
	
	public void addDepartment(DeptDTO deptDTO) throws DepartmentNameAlreadyExists {
		bao.addDepartment(deptDTO);	
	}
	
    public void updateDepartment(DeptDTO deptDTO) throws DepartmentNameAlreadyExists{
    	bao.updateDepartment(deptDTO);
    }
}
