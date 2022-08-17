/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ModuleMasterDAO.java
 * Author      :   Imran Shaik
 * Description :   Represents the Master DAO Class for Registration Initiation.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 	0.0             Imran Shaik		     18th Mar, 2008	 
 *  1.0         Sayed Taha(Wipro-egypt)     27th Aug, 2008 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.deedparammaster.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.deedparammaster.dto.DeedParamMasterDTO;
import com.wipro.igrs.deedparammaster.sql.CommonSQL;

public class DeedParamMasterDAO{
	
	DBUtility dbUtility = null;
	Connection con = null;
	Statement st = null;
	public DeedParamMasterDAO()  {
		try {
        	dbUtility = new DBUtility(); 
        } catch (Exception e) {
            System.out.println("I am in DeedParamMasterDAO start"+e.getStackTrace());
        }
		
	}
	/**
	 * check the availability of the deed param's name 
	 * @param name
	 * @return
	 */
	public boolean isDeedParamExists(String name){
		String SQL = CommonSQL.IS_DEEP_PARAM_EXIST;
		boolean found = false;
    	DBUtility dbUtility=null;
		String  param[]=new String[1];
		param[0]=name;
		try {
				String sql = CommonSQL.IS_DEEP_PARAM_EXIST;
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(sql);
				
				ArrayList municipalList = dbUtility.executeQuery(param);
				ArrayList subList = (ArrayList) municipalList.get(0);
				if(subList.isEmpty()){
					found=false;
					System.out.println("Deed Param  not exist........................");
				}else{
					found=true;
					System.out.println("Deed Param exist........................");
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
	/**
	 * Inserting deed param's Details 
	 * @param params
	 * @throws SQLException 
	 */
	public void addDeedParam (DeedParamMasterDTO dto) throws Exception  {
		String SQL=CommonSQL.INSERT_DEEDPARAM_MASTER;

		
				dbUtility.createPreparedStatement(SQL);
		
			String[] params =new String[3];
			
			params[0]=dto.getParamname();
			params[1]=dto.getParamDesc();
			params[2]=dto.getCreatedby();

			dbUtility.executeUpdate(params);
			dbUtility.commit();
		
	}
	
	
	/**
	 * return all  Deed param's details
	 * @return ModuleMasterForm
	 */
	public ArrayList getAllDeedParamDetails(){
		String SQL = CommonSQL.SELECT_ALL_DEEDPARAM_MASTER;
		
		ArrayList list = new ArrayList();
		ArrayList temp = new ArrayList();
		
		try {
			dbUtility.createStatement(); 
			list= dbUtility.executeQuery(SQL);
			for(int i=0;i<list.size();i++)
			{
				ArrayList ar =(ArrayList)list.get(i);
				DeedParamMasterDTO dto = new DeedParamMasterDTO();
				
				if(ar.get(0) != null)
					dto.setParamid(ar.get(0).toString());
				if(ar.get(1) != null)
			        dto.setParamname(ar.get(1).toString());
				if(ar.get(2) != null)
				     dto.setParamDesc(ar.get(2).toString());
				if(ar.get(3) != null)
				     dto.setParamstatus(ar.get(3).toString());
				if(ar.get(4) != null)
				     dto.setCreatedby(ar.get(4).toString());
				if(ar.get(5) != null)
				     dto.setCreateddate(ar.get(5).toString());
				if(ar.get(6) != null)
				     dto.setUpdatedby(ar.get(6).toString());
				if(ar.get(7) != null)
				     dto.setUpdatedDate(ar.get(7).toString());
				
			    temp.add(dto);
			}
			} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	

	/**
	 * update the Deed Param details
	 * @param params
	 */
	public void updateDeedParamDetails(DeedParamMasterDTO dto) throws Exception {
		
		String sql=CommonSQL.UPDATE_DEEDPARAM_MASTER;
		
		String[] params =new String[5];

		params[0]=dto.getParamname();
		params[1]=dto.getParamDesc();
	    params[2]=dto.getParamstatus();
	    params[3]=dto.getUpdatedby();
	    params[4]=dto.getParamid();
		
	 
		
		DBUtility dbUtility = null;
		
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean succeed = dbUtility.executeUpdate(params);
			if (succeed) {
				dbUtility.commit();
				System.out.println(">>>>>>>>> Commit");
			} else {
				System.out.println(">>>>>>>>> Rollback");
				dbUtility.rollback();
			}
				 
	
			try {
				dbUtility.closeConnection();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	
	
	
	/**
	 * delete the Deed Param's details
	 * @param params
	 */
	public DeedParamMasterDTO getDeedparamById(String ID) {
		
		DeedParamMasterDTO  dto =new DeedParamMasterDTO();
		try {
			String sql = CommonSQL.SELECT_DEEDPARAM_BYID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = ID;
			ArrayList list = dbUtility.executeQuery(sd);
			ArrayList list1 = (ArrayList) list.get(0);
			 dto= new DeedParamMasterDTO();
			
			if(list1.get(0) != null)
			  dto.setParamid(list1.get(0).toString());
			if(list1.get(1) != null)
			  dto.setParamname(list1.get(1).toString());
			if(list1.get(2) != null)
			  dto.setParamDesc(list1.get(2).toString());
			if(list1.get(3) != null)
			  dto.setParamstatus(list1.get(3).toString());
			if(list1.get(4) != null)
			  dto.setCreatedby(list1.get(4).toString());
			if(list1.get(5) != null)
			  dto.setCreateddate(list1.get(5).toString());
			if(list1.get(6) != null)
			  dto.setUpdatedby(list1.get(6).toString());
			if(list1.get(7) != null)
			  dto.setUpdatedDate(list1.get(7).toString());
			return dto;

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return dto;
		
		
		
	}
	
	public void deleteDeedParamDetails(DeedParamMasterDTO dto){
		String sql=CommonSQL.DELETE_DEEDPARAM;
		String param[] = new String[1];
		param[0]=dto.getParamid();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean succeed = dbUtility.executeUpdate(param);
			if (succeed) {
				dbUtility.commit();
				System.out.println(">>>>>>>>> Commit");
			} else {
				System.out.println(">>>>>>>>> Rollback");
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
	


public static void main(String[] args) {
	
	DeedParamMasterDAO test =new DeedParamMasterDAO();
	
	DeedParamMasterDTO dto =new DeedParamMasterDTO();

	
	dto=test.getDeedparamById("sn7");
	System.out.println(dto.getParamname());
	//ArrayList ar=test.getAllDeedParamDetails();
	//dto =(DeedParamMasterDTO)ar.get(0);

}



}





