package com.wipro.igrs.hrandpayrollform.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.hrandpayrollform.dto.CountryMasterDTO;
import com.wipro.igrs.hrandpayrollform.dto.DistrictMasterDTO;
import com.wipro.igrs.hrandpayrollform.dto.HrAndPayrollDTO;
import com.wipro.igrs.hrandpayrollform.dto.PaymentTypeMasterDTO;
import com.wipro.igrs.hrandpayrollform.dto.StateMasterDTO;
import com.wipro.igrs.hrandpayrollform.sql.HrAndPayrollSQL;


public class HrAndPayrollDAO {

	private static Logger log = org.apache.log4j.Logger.getLogger(HrAndPayrollDAO.class);
    DBUtility dbUtil;
	public HrAndPayrollDAO(){
	
		try {
			dbUtil = new DBUtility();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}

	public List getAllCountryMaster(){
		List allCountryMaster = new ArrayList();
		CountryMasterDTO countryMasterDTO;
		try {
			dbUtil.createStatement();
			//System.out.println(HrAndPayrollSQL.SELECT_ALL_COUNTRY_MASTER);
			ArrayList list = dbUtil.executeQuery(HrAndPayrollSQL.SELECT_ALL_COUNTRY_MASTER);
			for (int i = 0; i < list.size(); i++) {
				countryMasterDTO = new CountryMasterDTO();
				countryMasterDTO.setId((String)((ArrayList)list.get(i)).get(0));
				countryMasterDTO.setName((String)((ArrayList)list.get(i)).get(1));
				allCountryMaster.add(countryMasterDTO);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return allCountryMaster;
	}	
	
	public List getAllDistrictMaster(){
		List allDistrictMaster = new ArrayList();
		DistrictMasterDTO districtMasterDTO;
		try {
			dbUtil.createStatement();
		
			ArrayList list = dbUtil.executeQuery(HrAndPayrollSQL.SELECT_ALL_DISTRICT_MASTER);
			for (int i = 0; i < list.size(); i++) {
				districtMasterDTO = new DistrictMasterDTO();
				districtMasterDTO.setId((String)((ArrayList)list.get(i)).get(0));
				districtMasterDTO.setName((String)((ArrayList)list.get(i)).get(1));
				allDistrictMaster.add(districtMasterDTO);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return allDistrictMaster;
	}
	
	public List getDistrictsByStateId(String id){
		List allDistrictMaster = new ArrayList();
		DistrictMasterDTO districtMasterDTO;
		try {
			
			dbUtil.createPreparedStatement(HrAndPayrollSQL.SELECT_DISTRICT_BY_STATE_ID);
			String[] param = new String[]{id};
			ArrayList list = dbUtil.executeQuery(param);
			
			for (int i = 0; i < list.size(); i++) {
				districtMasterDTO = new DistrictMasterDTO();
				districtMasterDTO.setId((String)((ArrayList)list.get(i)).get(0));
				districtMasterDTO.setName((String)((ArrayList)list.get(i)).get(1));
				allDistrictMaster.add(districtMasterDTO);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return allDistrictMaster;
	}
	
	public List getAllStateMaster(){
		List allStateMaster = new ArrayList();
		StateMasterDTO stateMasterDTO;
		try {
			dbUtil.createStatement();
			
			ArrayList list = dbUtil.executeQuery(HrAndPayrollSQL.SELECT_ALL_STATE_MASTER);
			for (int i = 0; i < list.size(); i++) {
				stateMasterDTO = new StateMasterDTO();
				stateMasterDTO.setId((String)((ArrayList)list.get(i)).get(0));
				stateMasterDTO.setName((String)((ArrayList)list.get(i)).get(1));
				allStateMaster.add(stateMasterDTO);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return allStateMaster;
	}
	
	public List getStatesByCountryId(String id){
		List allStateMaster = new ArrayList();
		StateMasterDTO stateMasterDTO;
		try {
			dbUtil.createPreparedStatement(HrAndPayrollSQL.SELECT_STATE_BY_COUNTRY_ID);
			String[] param = new String[]{id};
			ArrayList list = dbUtil.executeQuery(param);
		
			for (int i = 0; i < list.size(); i++) {
				stateMasterDTO = new StateMasterDTO();
				stateMasterDTO.setId((String)((ArrayList)list.get(i)).get(0));
				stateMasterDTO.setName((String)((ArrayList)list.get(i)).get(1));
				allStateMaster.add(stateMasterDTO);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return allStateMaster;
	}
	
//	public static void main(String[] args) {
//		
//		HrAndPayrollDAO hrAndPayrollDAO = new HrAndPayrollDAO();
//	
//		
//		System.out.println(((DistrictMasterDTO)(hrAndPayrollDAO.getDistrictsByStateId("MP").get(0))).getName());
//	}
//	
	public List getAllPaymentTypeMaster(){
		List allPaymentTypeMaster = new ArrayList();
		PaymentTypeMasterDTO paymentTypeMasterDTO;
		try {
			dbUtil.createStatement();
		
			ArrayList list = dbUtil.executeQuery(HrAndPayrollSQL.SELECT_ALL_PAYMENT_TYPE_MASTER);
			for (int i = 0; i < list.size(); i++) {
				paymentTypeMasterDTO = new PaymentTypeMasterDTO();
				paymentTypeMasterDTO.setId((String)((ArrayList)list.get(i)).get(0));
				paymentTypeMasterDTO.setName((String)((ArrayList)list.get(i)).get(1));
				allPaymentTypeMaster.add(paymentTypeMasterDTO);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return allPaymentTypeMaster;
	}
	
	
	
	public boolean addHrAndPayrollMaster(HrAndPayrollDTO hrAndPayrollDTO) {
        boolean successAdding = false;
        
        try
        {
           
            dbUtil.createPreparedStatement(HrAndPayrollSQL.INSERT_HR_AND_PAYROLL);
            
            String[] param = new String[]{
            		hrAndPayrollDTO.getStartdate(),
            		hrAndPayrollDTO.getLeasePeriod()+"",
            		hrAndPayrollDTO.getLeaseAmount()+"",
            		hrAndPayrollDTO.getPaymentType(),
            		hrAndPayrollDTO.getPropertyCountryId(),
            		hrAndPayrollDTO.getPropertyStateId(),
            		hrAndPayrollDTO.getPropertyDistrictId(),
            		hrAndPayrollDTO.getPropertyAddress(),
            		hrAndPayrollDTO.getPropertyPostalCode()+"",
            		hrAndPayrollDTO.getLandLordFName(),
            		hrAndPayrollDTO.getLandLordMName(),
            		hrAndPayrollDTO.getLandLordLName(),
            		hrAndPayrollDTO.getLandLordCountryId(),
            		hrAndPayrollDTO.getLandLordStateId(),
            		hrAndPayrollDTO.getLandLordDistrictId(),
            		hrAndPayrollDTO.getLandLordAddress(),
            		hrAndPayrollDTO.getLandPostalCode()+"",
            		hrAndPayrollDTO.getLandLordGender()};
            successAdding = dbUtil.executeUpdate(param);
            dbUtil.commit();  
        } catch (Exception e){
        	e.printStackTrace();
        } 
        
        return successAdding;
    }
	
}
