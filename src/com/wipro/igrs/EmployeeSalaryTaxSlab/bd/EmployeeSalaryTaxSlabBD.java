/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   EmployeeSalaryTaxSlabBD.java
 * Author      :   Sayed Taha
 * Description :   Represents the Business Delegate.
 * Created Date   :   sept 10, 2008         	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.EmployeeSalaryTaxSlab.bd;

import java.util.ArrayList;

import com.wipro.igrs.EmployeeSalaryTaxSlab.bao.EmployeeSalaryTaxSlabBAO;
import com.wipro.igrs.EmployeeSalaryTaxSlab.dto.EmployeeSalaryTaxSlabDTO;

public class EmployeeSalaryTaxSlabBD {
	private EmployeeSalaryTaxSlabBAO bao=EmployeeSalaryTaxSlabBAO.getInstance();
	
	/**
	 * Singleton design pattern
	 **/
	private static EmployeeSalaryTaxSlabBD empSalTaxSlabBD = new EmployeeSalaryTaxSlabBD();
	private EmployeeSalaryTaxSlabBD(){
	}
	public static EmployeeSalaryTaxSlabBD getInstance(){
		return empSalTaxSlabBD;
	}
	public ArrayList getAllEmployeeSalaryTaxSlabs(){
   	 return bao.getAllEmployeeSalaryTaxSlabs();
    }
	public void addEmpSalTaxSlab(EmployeeSalaryTaxSlabDTO dto) {
		bao.addEmpSalTaxSlab(dto);
	}
	public EmployeeSalaryTaxSlabDTO getSalaryTaxByID(String slabID){
   	 return bao.getSalaryTaxByID(slabID);
    }
	public boolean isSalarySlabFound(EmployeeSalaryTaxSlabDTO dto){
		return bao.isSalarySlabFound(dto);
	}
	public void updateEmployeeSalaryTaxSlab(EmployeeSalaryTaxSlabDTO dto){
   	 bao.updateEmployeeSalaryTaxSlab(dto);
    }
	 public boolean isSalarySlabExistForCreate(EmployeeSalaryTaxSlabDTO dto){
		 return bao.isSalarySlabExistForCreate(dto);
	 }
}
