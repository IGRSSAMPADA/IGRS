package com.wipro.igrs.expmptc28.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.expmptc28.dto.DistrictDTO;
import com.wipro.igrs.expmptc28.dto.EmpDTO;
import com.wipro.igrs.expmptc28.dto.EmpDetailsDTO;
import com.wipro.igrs.expmptc28.dto.ExpMPTC28DTO;
import com.wipro.igrs.expmptc28.sql.ExpMPTC28CommonSQL;
import com.wipro.igrs.municipalbodymaster.sql.MunicipalBodyCommonSQL;



public class ExpMPTC28DAO {
	
	private DBUtility dbUtility = null;
	private Logger logger = (Logger) Logger.getLogger(ExpMPTC28DAO.class);
	
	public ArrayList getAllEmployees() {
		ArrayList result = new ArrayList();
		ArrayList subList = null;
		EmpDTO dto = null;
		try {
			String sql = ExpMPTC28CommonSQL.SELECT_ALL_EMPLOYEES;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			ArrayList empList = dbUtility.executeQuery(sql);
			
			for (int i = 0; i < empList.size(); i++) {
				subList = (ArrayList) empList.get(i);
				if ((subList != null) && (!subList.isEmpty())) {
					dto = new EmpDTO();
					if (subList.get(0) != null)
						dto.setEmpId(subList.get(0).toString());
					else
						dto.setEmpId("");				
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
	
	public String getVoucherNumber () {
		String result = "";
		try {
			String sql = ExpMPTC28CommonSQL.SELECT_IGRS_EXP_MPTC_28_SEQ_NEXT;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			ArrayList resultList = dbUtility.executeQuery(sql);		
			ArrayList subList = (ArrayList) resultList.get(0);
			if ((subList != null) && (!subList.isEmpty())) {
				if (subList.get(0) != null)
					result = subList.get(0).toString();				
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
	
	public ArrayList getAllDistricts() {
		ArrayList result = new ArrayList();
		ArrayList subList = null;
		DistrictDTO dto = null;
		try {
			String sql = ExpMPTC28CommonSQL.SELECT_ALL_DISTRICTS;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			ArrayList empList = dbUtility.executeQuery(sql);
			
			for (int i = 0; i < empList.size(); i++) {
				subList = (ArrayList) empList.get(i);
				if ((subList != null) && (!subList.isEmpty())) {
					dto = new DistrictDTO();
					if (subList.get(0) != null)
						dto.setDistrictId(subList.get(0).toString());
					else
						dto.setDistrictId("");	
					
					if (subList.get(1) != null)
						dto.setDistrictName(subList.get(1).toString());
					else
						dto.setDistrictName("");	
					
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
	
	public EmpDetailsDTO getEmpDetails(String empId) {
		ArrayList subList = null;
		EmpDetailsDTO result = null;
		String[] params = new String[]{empId};
		
		try {
			String sql = ExpMPTC28CommonSQL.SELECT_EMP_DETAILS;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			ArrayList resultList = dbUtility.executeQuery(params);
			if((resultList != null) && (!resultList.isEmpty())) {
				subList = (ArrayList) resultList.get(0);
				if ((subList != null) && (!subList.isEmpty())) {
					result = new EmpDetailsDTO();
					
					if (subList.get(0) != null)
						result.setEmpDesignationName(subList.get(0).toString());
					else
						result.setEmpDesignationName("");	
					
					if (subList.get(1) != null)
						result.setEmpOfficeName(subList.get(1).toString());
					else
						result.setEmpOfficeName("");	
					
					if (subList.get(2) != null)
						result.setEmpActualPay(subList.get(2).toString());
					else
						result.setEmpActualPay("");
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
	
	public void addExpMPTC28(ExpMPTC28DTO dto) {
		String sql1 = ExpMPTC28CommonSQL.ADD_EXP_MPTC28_DTLS;
		String sql2 = ExpMPTC28CommonSQL.ADD_EXP_MPTC28_DTLS_MAP;
		
		String param1[] = new String[4];
		param1[0] = dto.getVoucherNumber();
		param1[1] = dto.getDistrictId();
		param1[2] = dto.getEnteredBy();
		param1[3] = dto.getRemarks();
		
		String param2[] = new String[23];
		param2[0] = dto.getVoucherNumber();
		param2[1] = dto.getEmpId();
		param2[2] = dto.getDepartureFrom();
		param2[3] = dto.getDepartureDate();
		param2[4] = dto.getArrivalTo();
		param2[5] = dto.getArrivalDate();
		param2[6] = dto.getPurposeOfJourney();
		param2[7] = dto.getExpenseAmount();
		param2[8] = dto.getModeOfTravel();
		param2[9] = dto.getTravelClass();
		param2[10] = dto.getNoKilometer();
		param2[11] = dto.getFaresInCaseTransfer();
		param2[12] = dto.getDaAmount();
		param2[13] = dto.getHqExtendDa();
		param2[14] = null;
		param2[15] = dto.getJourneyExtendDa();
		param2[16] = dto.getJourneyAmount();
		param2[17] = dto.getJourneyHours();
		param2[18] = dto.getSpecialHaltExtendDa();
		param2[19] = dto.getSpecialHaltAmount();
		param2[20] = null;
		param2[21] = dto.getEachLineTotal();
		param2[22] = dto.getRemarks();
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql1);
			boolean boo = dbUtility.executeUpdate(param1);
			dbUtility.createPreparedStatement(sql2);
			boo = dbUtility.executeUpdate(param2);
			if (boo) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
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
	}
}
