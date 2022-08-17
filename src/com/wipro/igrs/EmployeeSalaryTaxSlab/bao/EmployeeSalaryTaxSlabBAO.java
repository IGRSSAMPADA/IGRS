/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   EmployeeSalaryTaxSlabBAO.java
 * Author      :   Sayed Taha
 * Description :   Represents the Business Access Object .
 * Created Date   :   sept 10, 2008         	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.EmployeeSalaryTaxSlab.bao;
import java.util.ArrayList;
import java.util.Date;

import com.wipro.igrs.EmployeeSalaryTaxSlab.dao.EmployeeSalaryTaxSlabDAO;
import com.wipro.igrs.EmployeeSalaryTaxSlab.dto.EmployeeSalaryTaxSlabDTO;
import com.wipro.igrs.db.DBUtility;
public class EmployeeSalaryTaxSlabBAO {
	     private EmployeeSalaryTaxSlabDAO dao;
	     /**
	      * singleton design patterns
	      **/
	     private static EmployeeSalaryTaxSlabBAO empSalTaxSlabBAO =new EmployeeSalaryTaxSlabBAO();
	     private EmployeeSalaryTaxSlabBAO(){
	    	 dao=EmployeeSalaryTaxSlabDAO.getInstance();
	     }
	     public static EmployeeSalaryTaxSlabBAO getInstance(){
	    	 return empSalTaxSlabBAO;
	     }
	     public ArrayList getAllEmployeeSalaryTaxSlabs(){
	    	 return dao.getAllEmployeeSalaryTaxSlabs();
	     }
	     public void addEmpSalTaxSlab(EmployeeSalaryTaxSlabDTO dto) {
	    	 dao.addEmpSalTaxSlab(dto);
	     }
	     public EmployeeSalaryTaxSlabDTO getSalaryTaxByID(String slabID){
	    	 return dao.getSalaryTaxByID(slabID);
	     }
	     public boolean isSalarySlabFound(EmployeeSalaryTaxSlabDTO dto){
	    	 String foundId=dao.getIdByData(dto);
	    	 String oldId=dto.getTaxID();
	    	 System.out.println("foundId   "+foundId+"........oldId  "+oldId);
	    	 if(foundId!=null && !foundId.equals(oldId)){
	    		 return true;
	    	 }else{
	    		 return false;
	    	 }
	     }
	     public boolean isSalarySlabExistForCreate(EmployeeSalaryTaxSlabDTO dto){
	    	 String oldId=dao.getIdByData(dto);
	    	 if(oldId==null ){
	    		 return false;
	    	 }else{
	    		 return true;
	    	 }
	     }
	     public void updateEmployeeSalaryTaxSlab(EmployeeSalaryTaxSlabDTO dto){
	    	 dao.updateEmployeeSalaryTaxSlab(dto);
	     }
		
}
		
		

