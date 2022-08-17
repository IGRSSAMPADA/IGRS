package com.wipro.igrs.complaintdetails.dao;

import java.util.ArrayList;
import java.util.List;

import com.wipro.igrs.complaintdetails.dto.ComplaintDetailsCriteriaDTO;
import com.wipro.igrs.complaintdetails.dto.ComplaintDetailsDTO;
import com.wipro.igrs.complaintdetails.sql.ComplaintDetailsSQL;
import com.wipro.igrs.db.DBUtility;

import com.wipro.igrs.officemaster.dto.OfficeDTO;
import com.wipro.igrs.complaintdetails.sql.ComplaintDetailsSQL;


public class ComplaintDetailsDAO {
	
	DBUtility dbUtility = null;
	StringBuffer sql = new StringBuffer(ComplaintDetailsSQL.SELECTED_FIELDS);
	ComplaintDetailsDTO compDTO = null;
	List activityList = null;
	ArrayList subList = null;
	
	String param[] = null;
	String nparam[] = null;
	
	int criteriaSize=0;
	
	
	public void extendCriteria(){
		if(param == null )
			param = new String[criteriaSize];
		criteriaSize++;
		if(criteriaSize == 1)
			sql = sql.append("WHERE ");
		else
			sql = sql.append(" AND ");
		nparam = new String[criteriaSize];
		System.arraycopy(param, 0, nparam, 0, param.length);
		param=nparam;	
	}
		
	public List search(ComplaintDetailsCriteriaDTO dto){
		
		if(dto.getPaymentDateFrom() != null && dto.getPaymentDateTo() == null )
		{
			extendCriteria();
			param[criteriaSize-1] = dto.getPaymentDateFrom();
			sql = sql.append(ComplaintDetailsSQL.PAYMENT_EXACT_DATE);	
		}
		if(dto.getPaymentDateTo()!= null && dto.getPaymentDateFrom() != null)
		{
			extendCriteria();
			param[criteriaSize-1] = dto.getPaymentDateFrom();
	
			criteriaSize++;
			nparam = new String[criteriaSize];
			System.arraycopy(param, 0, nparam, 0, param.length);
			param=nparam;
			param[criteriaSize-1] = dto.getPaymentDateTo();
			
			sql = sql.append(ComplaintDetailsSQL.PAYMENT_RANGE_DATE);	
		}
		if(dto.getEmpCode()!= null){
			extendCriteria();
			param[criteriaSize-1] = dto.getEmpCode();	
			sql = sql.append(ComplaintDetailsSQL.EMPLOYEE_CODE);
		}
		if(dto.getEmpFirstName()!= null){
			extendCriteria();
			param[criteriaSize-1] = dto.getEmpFirstName();
			sql = sql.append(ComplaintDetailsSQL.EMP_F_NAME);
		}
		if(dto.getEmpMidName()!= null){
			extendCriteria();
			param[criteriaSize-1] = dto.getEmpMidName();
			sql = sql.append(ComplaintDetailsSQL.EMP_M_NAME);
		}
		if(dto.getEmpLastName()!= null){
			extendCriteria();
			param[criteriaSize-1] = dto.getEmpLastName();
			sql = sql.append(ComplaintDetailsSQL.EMP_L_NAME);
		}		
		if(dto.getStatus()!= null){
			extendCriteria();
			param[criteriaSize-1] = dto.getStatus();
			sql = sql.append(ComplaintDetailsSQL.STATUS);
		}
		if(dto.getLocation()!= null){
			extendCriteria();
			param[criteriaSize-1] = dto.getLocation();
			sql = sql.append(ComplaintDetailsSQL.LOCATION);
		}
		if(dto.getOfficeId()!= null){
			extendCriteria();
			param[criteriaSize-1] = dto.getOfficeId();
			sql = sql.append(ComplaintDetailsSQL.OFFICE);
		}
		
		try {
			dbUtility = new DBUtility();
			
			activityList = new ArrayList();
			ArrayList mainList1;
			if(param == null){
				dbUtility.createStatement();
				mainList1 = dbUtility.executeQuery(sql.toString());
			}
			else{
				dbUtility.createPreparedStatement(sql.toString());
			    mainList1 = dbUtility.executeQuery(param);    
			}
			for (int i = 0; i < mainList1.size(); i++) {
				
				subList = (ArrayList) mainList1.get(i);
				compDTO = new ComplaintDetailsDTO();
				
				compDTO.setEmpName(subList.get(0).toString());
				compDTO.setEmpDesignation(subList.get(1).toString());
				compDTO.setOffice(subList.get(2).toString());
				compDTO.setDdoName(subList.get(3).toString());
				compDTO.setDdoDesignation(subList.get(4).toString());
				compDTO.setHead(subList.get(5).toString());
			
				activityList.add(compDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return activityList;
	
	}
		
	public List getOfficesByType(String officeTypeId){
		ArrayList typeList = new ArrayList();
		String[] param = new String[1];
		param[0] = officeTypeId;
		String sql;
		try {
			sql = ComplaintDetailsSQL.SELECT_OFFICES_BY_TYPE;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			OfficeDTO typeDTO = null;
			ArrayList mainList1 = dbUtility.executeQuery(param);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				typeDTO = new OfficeDTO();
				typeDTO.setOfficeId(subList.get(0).toString());
				typeDTO.setOfficeName(subList.get(1).toString());

				typeList.add(typeDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return typeList;
	}
	

	public List listComplaintStatus() {
		ArrayList typeList = new ArrayList();
		String[] param = new String[1];
		String sql;
		try {
			sql = ComplaintDetailsSQL.SELECT_COMP_STAUS;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			ArrayList mainList1 = dbUtility.executeQuery(sql);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				String status =subList.get(0).toString();
                typeList.add(status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return typeList;
	}

}
