/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   EmployeeSalaryTaxSlabDAO.java
 * Author      :   Sayed Taha
 * Description :   Represents the Data Access Object class.
 * Created Date   :   sept 10, 2008         	 		 
 * --------------------------------------------------------------------------------
 */
	
package com.wipro.igrs.EmployeeSalaryTaxSlab.dao;

import java.util.ArrayList;
import java.util.Date;

import com.wipro.igrs.EmployeeSalaryTaxSlab.dto.EmployeeSalaryTaxSlabDTO;
import com.wipro.igrs.EmployeeSalaryTaxSlab.sql.EmployeeSalaryTaxSlabSQL;
import com.wipro.igrs.db.DBUtility;
public class EmployeeSalaryTaxSlabDAO {
	//Singleton Design patterns
	private static EmployeeSalaryTaxSlabDAO empSalTaxSlabDAO = new EmployeeSalaryTaxSlabDAO();
	public static EmployeeSalaryTaxSlabDAO getInstance(){
		return empSalTaxSlabDAO;
	}
	private EmployeeSalaryTaxSlabDAO(){
	}
	/**
	 * To get all EMPLOYEE SALARY TAX SLABS in the DB.
	 * @return ArrayList all of result
	 */
	public ArrayList getAllEmployeeSalaryTaxSlabs() {
		ArrayList result = new ArrayList();
		ArrayList subList = null;
		EmployeeSalaryTaxSlabDTO dto = null;
		DBUtility dbUtility = null;
		try {
			String sql = EmployeeSalaryTaxSlabSQL.SELECT_ALL_EMPLOYEE_SALARY_TAX_SLABs;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			ArrayList eventsList = dbUtility.executeQuery(sql);
			System.out.println("Size from the Db   :"+eventsList.size());
			for (int i = 0; i < eventsList.size(); i++) {
				subList = (ArrayList) eventsList.get(i);
				dto = new EmployeeSalaryTaxSlabDTO();
				
				dto.setTaxID(subList.get(0).toString());
				dto.setStartSlab(new Float(subList.get(1).toString()));
				dto.setEndSlab(new Float(subList.get(2).toString()));
				String personType=subList.get(3).toString();
				if(personType.equals("M")){
					dto.setPersonType("Male");
				}
				else{
					dto.setPersonType("Femal");
				}
				dto.setPercentApplicacle(new Float(subList.get(4).toString()));
				result.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Size From DAO      :"+result.size());
		return result;
	}
	
	/**
	 * 
	 * @param dto
	*/ 
	
	public void addEmpSalTaxSlab(EmployeeSalaryTaxSlabDTO dto) {
		String sql = EmployeeSalaryTaxSlabSQL.INSERT_EMPLOYEE_SALARY_TAX_SLAB;
		DBUtility dbUtility = null;
		String param[] = new String[4];
		
		param[0] = (dto.getStartSlab()).toString();
		param[1] = (dto.getEndSlab()).toString();
		param[2] = dto.getPersonType();
		param[3] = (dto.getPercentApplicacle()).toString();
		
		
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
	
	
	
	/**
	 * 
	 * @param mapID
	 * @return
	 */
	public EmployeeSalaryTaxSlabDTO getSalaryTaxByID(String slabID){
		EmployeeSalaryTaxSlabDTO dto = null;
		DBUtility dbUtility=null;
		String [] param=new String[1];
		param[0]=slabID;
		try {
			String sql = EmployeeSalaryTaxSlabSQL.GET_EMPLOYEE_SALARY_TAX_SLAB_BY_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			
			ArrayList municipalList = dbUtility.executeQuery(param);
			ArrayList subList = (ArrayList) municipalList.get(0);
			
			dto = new EmployeeSalaryTaxSlabDTO();
			dto.setTaxID(subList.get(0).toString());
			dto.setStartSlab(new Float(subList.get(1).toString()));
			dto.setEndSlab(new Float(subList.get(2).toString()));
			if(subList.get(3).toString().equals("M")){
				dto.setPersonType("Male");
			}else{
				dto.setPersonType("Female");
			}
			dto.setPercentApplicacle(new Float(subList.get(4).toString()));
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+dto.getPersonType());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	/**
	 * 
	 * @param dto
	 */
		public void updateEmployeeSalaryTaxSlab(EmployeeSalaryTaxSlabDTO dto){
	    	String sql = EmployeeSalaryTaxSlabSQL.UPDATE_EMPLOYEE_SALARY_TAX_SLAB;
			DBUtility dbUtility = null;
			String param[] = new String[5];
			
			param[0] = dto.getStartSlab().toString();
			param[1] = dto.getEndSlab().toString();
			param[2] = dto.getPersonType();
			param[3] = dto.getPercentApplicacle().toString();
			param[4] = dto.getTaxID();
			
			
			try {
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(sql);
				boolean succeed = dbUtility.executeUpdate(param);
				if (succeed) {
					dbUtility.commit();
					System.out.println(">>>>>>>>>>>>>commit");
				} else {
					System.out.println(">>>>>>>>>>>>>rollback");
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
	/**
	 * 
	 * @param dto
	 * @return
	 */
	
		public String getIdByData(EmployeeSalaryTaxSlabDTO dto){
			ArrayList result = new ArrayList();
			ArrayList subList = null;
			String slabId=null;
			DBUtility dbUtility = null;
			try {
				String param[] = new String[4];
				dbUtility = new DBUtility();
				param[0] = dto.getStartSlab().toString();
				param[1] = dto.getEndSlab().toString();
				param[2] = dto.getPersonType();
				param[3] = dto.getPercentApplicacle().toString();
				
				String sql = EmployeeSalaryTaxSlabSQL.IS_SLAB_EXISTS;
				
				dbUtility.createPreparedStatement(sql);
				ArrayList eventsList = dbUtility.executeQuery(param);
				
				if(!eventsList.isEmpty()){
					subList = (ArrayList) eventsList.get(0);
					
					slabId = subList.get(0).toString();
					
				}				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return slabId;
		}
	
	}