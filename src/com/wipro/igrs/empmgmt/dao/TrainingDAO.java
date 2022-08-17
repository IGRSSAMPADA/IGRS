package com.wipro.igrs.empmgmt.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.empmgmt.dto.EmployeeDTO;
import com.wipro.igrs.empmgmt.dto.TrainingDTO;
import com.wipro.igrs.empmgmt.sql.CommonSQL;
import com.wipro.igrs.util.CommonUtil;

public class TrainingDAO {
	DBUtility dbUtil = null;
	private Logger logger = (Logger) Logger.getLogger(TrainingDAO.class);

	public TrainingDAO() throws Exception {
		dbUtil = new DBUtility();
	}

	public String submitTrainingDetails(TrainingDTO trainingDTO,
			ArrayList employeeList, String userid) throws Exception {
		boolean flag = false;
		boolean flag1 = false;
		String result = "";
		dbUtil.setAutoCommit(false);
		try {
			String txnid = this.getTrainingTxnId();
			String triningValues[] = {
					txnid,// TRAINING_TXN_ID
					trainingDTO.getOrganizingAuthority(),// ORGANISING_AUTHORITY
					CommonUtil.getConvertedDate(trainingDTO
							.getTrainingStartDate()),// TRAINING_START_DATE
					CommonUtil.getConvertedDate(trainingDTO
							.getTrainingEndtDate()),// TRAINING_END_DATE
					trainingDTO.getPlaceOfTraining(),// PLACE_OF_TRAINING
					"",// TRAINING_BODY
					trainingDTO.getAuthorizingAuthority(),// AUTHORISING_AUTHORITY
					CommonUtil.getConvertedDate(trainingDTO
							.getAuthorizationDate()),// AUTHORISATION_DATE

					trainingDTO.getLevelOfTraining(),// TRAINEE_LEVEL
					trainingDTO.getOrgainizingBody(),// ORGANISATION_BODY
					trainingDTO.getTrainingCost(),// TRAINING_COST
					userid,// CREATED_BY
					userid,// UPDATE_BY
					trainingDTO.getNameOfTraining(),// TRAINING_NAME
					trainingDTO.getComments(),// TRAINING_COMMENTS
					trainingDTO.getFinancialYear(),// FINANCIAL_YEAR
					txnid // TRAINING_NUMBER
			};
			String sqlQuery = CommonSQL.INSERT_EMPMGMT_TRAINING_DETAILS;
			dbUtil.createPreparedStatement(sqlQuery);
			flag = dbUtil.executeUpdate(triningValues);
			if (flag) {
				if (employeeList != null) {
					for (int i = 0; i < employeeList.size(); i++) {
						EmployeeDTO employeeDTO = (EmployeeDTO) employeeList
								.get(i);
						String employeeDetaislValues[] = { txnid, // TRAINING_TXN_ID
								employeeDTO.getEmployeeNumber(),// EMP_ID
								userid,// CREATED_BY
								userid,// UPDATE_BY
								employeeDTO.getResult() // TRAINING_RESULT
						};
						String empQuery = CommonSQL.INSERT_EMPMGMT_EMP_TRAINING_MAPPING_DETAILS;
						dbUtil.createPreparedStatement(empQuery);
						flag1 = dbUtil.executeUpdate(employeeDetaislValues);
					}
				}
			}
			if (flag && flag1) {
				result = txnid;
				dbUtil.commit();
				flag = true;
			} else {
				dbUtil.rollback();
				flag = false;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	private String getTrainingTxnId() throws Exception {
		String trainingTransId = "";
		String trainingTransIdConst = "";
		try {
			dbUtil.createStatement();
			trainingTransId = dbUtil
					.executeQry(CommonSQL.SELECT_TRAINING_TRANS_ID_SEQ);
			trainingTransIdConst = "TRA-";

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return trainingTransIdConst + trainingTransId;
	}

	public ArrayList getEmployeeList(String[] empid) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtil.createPreparedStatement(CommonSQL.RETRIVE_EMPLOYEE_DETAILS);

			list = dbUtil.executeQuery(empid);

		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public EmployeeDTO getEmployeeDetails(String empid) throws Exception {
		ArrayList list = new ArrayList();
		EmployeeDTO employeeDTO = null;
		try {
			dbUtil.createPreparedStatement(CommonSQL.RETRIVE_EMPLOYEE_DETAILS);
			String str[] = { empid };
			list = dbUtil.executeQuery(str);
			
			if (list.size() == 0) {
				employeeDTO.setEmployeeName("XXXXXX");

			} else {
				for (int i = 0; i < list.size(); i++) {
					ArrayList employee = (ArrayList) list.get(i);
					if (employee != null) {
						employeeDTO = new EmployeeDTO();
						employeeDTO.setEmployeeName((String) employee.get(0));
						employeeDTO.setEmployeeDesignation((String) employee
								.get(1));
						employeeDTO.setDateOfJoining((String) employee.get(2));
						employeeDTO.setPlaceOfPosting((String) employee.get(4));
						employeeDTO.setOfficalAddress((String) employee.get(5));
						employeeDTO.setResidencalAddress((String) employee
								.get(6));
						employeeDTO.setContactNumber((String) employee.get(7));
						employeeDTO.setEmail((String) employee.get(8));
						employeeDTO.setEmpClass((String) employee.get(9));
						employeeDTO.setEmpSalary((String) employee.get(10));

					}
				}
			}
			System.out.println("After settingthe value "
					+ employeeDTO.getEmployeeName());

		} catch (Exception e) {
			// System.out.println("DAO EXCEPTION "+e);
		} finally {
			dbUtil.closeConnection();
		}
		return employeeDTO;
	}

	public EmployeeDTO getEmployeeSearch(String empid) throws Exception {
		ArrayList list = new ArrayList();
		EmployeeDTO employeeDTO = new EmployeeDTO();
		try {
			dbUtil.createPreparedStatement(CommonSQL.RETRIVE_EMPLOYEE_SEARCH);
			String str[] = { empid };
			list = dbUtil.executeQuery(str);
			System.out.println("latest employee search " + list.size());
			// if(list.size()==0){
			// employeeDTO.setEmployeeNumber("XXXXXX");
			//				
			// }else{
			for (int i = 0; i < list.size(); i++) {
				ArrayList employee = (ArrayList) list.get(i);
				if (employee != null) {
					employeeDTO = new EmployeeDTO();
					employeeDTO.setEmployeeNumber((String) employee.get(0));
				}
			}
			// }
		} catch (Exception e) {
			// System.out.println("DAO EXCEPTION "+e);
		} finally {
			dbUtil.closeConnection();
		}
		// System.out.println("After setting the XXXXX value
		// "+employeeDTO.getEmployeeNumber());
		return employeeDTO;
	}

}
