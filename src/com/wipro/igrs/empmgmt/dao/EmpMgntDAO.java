/**
 * Copyright (c) 2009-10 WIPRO INFOTECH. All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 *
 * Customer specific copyright notice - NA
 */
package com.wipro.igrs.empmgmt.dao;


import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.wipro.igrs.UserRegistration.action.CryptoLibrary;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.empmgmt.dto.AcademicDTO;
import com.wipro.igrs.empmgmt.dto.AssetDTO;
import com.wipro.igrs.empmgmt.dto.BankDTO;
import com.wipro.igrs.empmgmt.dto.BankMstDTO;
import com.wipro.igrs.empmgmt.dto.ChildDetailsDTO;
import com.wipro.igrs.empmgmt.dto.DeptExamDTO;
import com.wipro.igrs.empmgmt.dto.DeptTrainingDTO;
import com.wipro.igrs.empmgmt.dto.FamilyMemberDTO;
import com.wipro.igrs.empmgmt.dto.FundDTO;
import com.wipro.igrs.empmgmt.dto.NomineeDTO;
import com.wipro.igrs.empmgmt.dto.OfficalInfoDTO;
import com.wipro.igrs.empmgmt.dto.PersonalDetailsDTO;
import com.wipro.igrs.empmgmt.dto.PrevEmpDTO;
import com.wipro.igrs.empmgmt.dto.PropertyDTO;
import com.wipro.igrs.empmgmt.dto.ServiceVerificationDTO;
import com.wipro.igrs.empmgmt.sql.CommonSQL;
import com.wipro.igrs.util.CommonUtil;

/**
* 
* EmpMgntDAO.java <br>
* EmpMgntDAO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
public class EmpMgntDAO {
	
	private Logger logger = 
		(Logger) Logger.getLogger(EmpMgntDAO.class);
	
	/**
	 * @throws Exception
	 */
	public EmpMgntDAO() throws Exception {
		//dbUtil = new DBUtility();

	}

	/**
	 * @return
	 * @throws Exception
	 */
	
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getCountryNames() throws Exception {
		ArrayList list=null;
		DBUtility	dbUtil=new DBUtility();
		try{	
		dbUtil.createStatement();
		list = dbUtil
				.executeQuery("Select COUNTRY_ID, COUNTRY_NAME from IGRS_COUNTRY_MASTER order by COUNTRY_NAME");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}
	
	/**
	 * @param detailsDTO
	 * @return
	 */
	public ArrayList getReference(PersonalDetailsDTO detailsDTO){
		ArrayList list=null;
		DBUtility	dbUtil=null;
		try{	
			dbUtil=new DBUtility();
		String referencearray[]={				
				detailsDTO.getReferenceemployeeid()
				};
		dbUtil.createPreparedStatement(CommonSQL.SELECTREFERENCE);
		list = dbUtil.executeQuery(referencearray);
		}catch (Exception exception) {
			logger.error(exception.getMessage(),exception);
		}finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	/**
	 * @param countryID
	 * @return
	 * @throws Exception
	 */
	public ArrayList getStateNames(String countryID) throws Exception {
		ArrayList list=null;
		DBUtility	dbUtil=new DBUtility();
		try {		
		dbUtil.createStatement();
		list = dbUtil
				.executeQuery("Select STATE_ID, STATE_NAME from IGRS_STATE_MASTER WHERE COUNTRY_ID = '"
						+ countryID + "' ORDER BY STATE_NAME");
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}
	/**
	 * @param countryID
	 * @return
	 * @throws Exception
	 */
	public ArrayList getHomeDistrictEmp(String stateID) throws Exception {
		ArrayList list=new ArrayList();
		DBUtility	dbUtil=new DBUtility();
		try {		
		dbUtil.createStatement();
		if(!stateID.equalsIgnoreCase("")){
		list = dbUtil
				.executeQuery("Select DISTRICT_ID, "
		+"DISTRICT_NAME,H_DISTRICT_NAME from IGRS_DISTRICT_MASTER "
		+" Where STATE_ID=' "+ stateID +"' AND DISTRICT_STATUS='A' "
		+"ORDER BY DISTRICT_NAME ASC");
		}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * @param stateID
	 * @return
	 * @throws Exception
	 */
	public ArrayList getDistrictNames(String stateID) throws Exception {
		ArrayList list=null;
		DBUtility	dbUtil=new DBUtility();
		try {		
		dbUtil.createStatement();
		list = dbUtil
				.executeQuery("Select DISTRICT_ID, DISTRICT_NAME from IGRS_DISTRICT_MASTER where STATE_ID = '"
						+ stateID + "'  ORDER BY DISTRICT_NAME");
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * @return
	 * @throws Exception
	 */

	public ArrayList getReligion() throws Exception {
		ArrayList list=null;
		DBUtility	dbUtil=new DBUtility();
		try{
		dbUtil.createStatement();
		list = dbUtil
				.executeQuery("Select RELIGION_ID, RELIGION_NAME,H_RELIGION_NAME from IGRS_RELIGION_MASTER WHERE UPPER(RELIGION_STATUS) = 'A'");
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getCaste() throws Exception {
		ArrayList list=null;
		DBUtility	dbUtil=new DBUtility();
		try{
			//dbUtil.getDBConnection();
		dbUtil.createStatement();
		list = dbUtil
				.executeQuery("Select CATEGORY_ID, CATEGORY_NAME, H_CATEGORY_NAME from IGRS_PERSON_CATEGORY_MASTER WHERE UPPER(CATEGORY_STATUS) = 'A'");
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getStream() throws Exception {
		ArrayList list=null;
		DBUtility	dbUtil=new DBUtility();
		try{	//dbUtil.getDBConnection();
			dbUtil.createStatement();
		
		list = dbUtil
				.executeQuery("Select QUALIFICATION_TYPE_ID, QUALIFICATION_NAME from IGRS_EMP_QUALIFICATION_MASTER WHERE UPPER(QUALIFICATION_STATUS) = 'A'");
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getHomeDistrict() throws Exception {
		ArrayList list=null;
		DBUtility	dbUtil=new DBUtility();
		try{	//dbUtil.getDBConnection();
		dbUtil.createStatement();
		list = dbUtil
				.executeQuery("Select DISTRICT_ID, DISTRICT_NAME,H_DISTRICT_NAME from IGRS_DISTRICT_MASTER WHERE UPPER(DISTRICT_STATUS) = 'A'");
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAssetTypeList() throws Exception {
		ArrayList list=null;
		DBUtility	dbUtil=new DBUtility();
		try{
		//	dbUtil.getDBConnection();
		dbUtil.createStatement();
		list = dbUtil
				.executeQuery("Select ASSET_TYPE_ID, ASSET_TYPE_NAME from IGRS_EMP_ASSET_MASTER");
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * @param empMaster
	 * @param childsList
	 * @param personalDetailsDTO
	 * @return
	 * @throws Exception
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	/**
	 * @param trainingList
	 * @param examList
	 * @param strUserId
	 * @param empid
	 * @return
	 * @throws Exception
	 */
	public boolean addDepartment(ArrayList trainingList,ArrayList examList,String strUserId,String empid)
			throws Exception {
		DBUtility	dbUtil=new DBUtility();
		boolean b1 = false, b2 = false,b3 = false,b4 = false;
		try{	//dbUtil.getDBConnection();
		String seq=getTrainningSequenceId();
		String seq1=getExamSequenceId();
		b1 = insertTraining(trainingList,strUserId,seq);
		b2 = insertExam(examList,strUserId,seq1);
		b3=insertTraining1(trainingList,strUserId,empid,seq);
		b4 = insertExam1(examList,strUserId,empid,seq1);
		
		
		
		if(b1 && b2 && b3 && b4)
		{	try {
				dbUtil.commit();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}	
		}
		else
		{
			try {
				dbUtil.rollback();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			dbUtil.closeConnection();
		}
		return (b1 && b2 && b3 && b4);
	}

	
	
	/**
	 * @param trainingList
	 * @param strUserId
	 * @param seq
	 * @return
	 * @throws Exception
	 */
	public boolean insertTraining(ArrayList trainingList,String strUserId,String seq) throws Exception {
		DBUtility	dbUtil=new DBUtility();
		dbUtil.setAutoCommit(false);
		boolean flag = false;
		try{
		for (int i = 0; i < trainingList.size(); i++) {
			
			DeptTrainingDTO deptTrainingDTO = (DeptTrainingDTO) trainingList
					.get(i);
			if (deptTrainingDTO.getPlaceOfTraining() != null) {
				String sqlValues[] = {
						seq,
						deptTrainingDTO.getTrainingNo(), // EMP_AUTHORITY_NAME	
						deptTrainingDTO.getTrainingName(),
						deptTrainingDTO.getTrainingLevel(), // DATE_OF_VERIFICATION	
						deptTrainingDTO.getOrganizingAuthority(),
						deptTrainingDTO.getOrgainizingBody(),
						deptTrainingDTO.getPlaceOfTraining(),
						deptTrainingDTO.getFinancialYear(),
						CommonUtil.getConvertedDate(deptTrainingDTO.getTrainingStartDate()),
						CommonUtil.getConvertedDate(deptTrainingDTO.getTrainingEndDate()),
						deptTrainingDTO.getAuthorisingAuthority(),
						deptTrainingDTO.getAuthorizationDate(),
						deptTrainingDTO.getTrainingCost(),
						//deptTrainingDTO.getTrainingResult(),
						deptTrainingDTO.getTrainingComments(), 
						strUserId,
						strUserId
						

				};
				CommonSQL common = new CommonSQL();
				String sqlQuery = CommonSQL.INSERT_EMP_TRAINING_DET_DETAILS;
				dbUtil.createPreparedStatement(sqlQuery);
				flag = dbUtil.executeUpdate(sqlValues);
				if (flag == false) {
					break;
				}
			}
		}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			dbUtil.closeConnection();
		}
		return flag;
	}

	
	
	
	/**
	 * @param trainingList
	 * @param strUserId
	 * @param empid
	 * @param seq
	 * @return
	 * @throws Exception
	 */
	public boolean insertTraining1(ArrayList trainingList,String strUserId,String empid,String seq) throws Exception {
		DBUtility	dbUtil=new DBUtility();
		dbUtil.setAutoCommit(false);
		boolean flag = false;
		try{
		//HttpSession session=reguest.getSession(true);
		//String empid=session.getAttribute("empid").toString();
		for (int i = 0; i < trainingList.size(); i++) {
			
			DeptTrainingDTO deptTrainingDTO = (DeptTrainingDTO) trainingList
					.get(i);
			if (deptTrainingDTO.getTrainingResult() != null) {
				String sqlValues[] = {
					
						seq,
						empid,
						deptTrainingDTO.getTrainingResult(),
						strUserId,
						strUserId
				
				};
				CommonSQL common = new CommonSQL();
				String sqlQuery = CommonSQL.INSERT_EMP_TRAINING_MAPPING_DETAILS;
				dbUtil.createPreparedStatement(sqlQuery);
				flag = dbUtil.executeUpdate(sqlValues);
				if (flag == false) {
					break;
				}
			}
		}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			dbUtil.closeConnection();
		}
		return flag;
	}

	/**
	 * @param examgList
	 * @return
	 * @throws Exception
	 */
	
	
	/**
	 * @param examgList
	 * @param strUserId
	 * @param seq1
	 * @return
	 * @throws Exception
	 */
	public boolean insertExam(ArrayList examgList,String strUserId,String seq1) throws Exception {
		DBUtility	dbUtil=new DBUtility();
		dbUtil.setAutoCommit(false);
		boolean flag = false;
		try{
		for (int i = 0; i < examgList.size(); i++) {
			DeptExamDTO deptExamDTO = (DeptExamDTO) examgList.get(i);
			if (deptExamDTO.getNameOfExam() != null) {
				String sqlValues[] = {
						seq1,
						deptExamDTO.getNameOfExam(),
						deptExamDTO.getExamDate(),
						deptExamDTO.getExamsOrganizingAuthority(),
						deptExamDTO.getPlaceOfExam(),
						deptExamDTO.getResultDate(),
						deptExamDTO.getExamsComments(),
						strUserId,
						strUserId
				};
				String sqlQuery = CommonSQL.INSERT_EMP_DEPT_EXAM_DET_DETAILS;
				dbUtil.createPreparedStatement(sqlQuery);
				flag = dbUtil.executeUpdate(sqlValues);
				if (flag == false) {
					break;
				}
			}
		}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			dbUtil.closeConnection();
		}
		return flag;
	}
	
	/**
	 * @param examgList
	 * @param strUserId
	 * @param empid
	 * @param seq1
	 * @return
	 * @throws Exception
	 */
	public boolean insertExam1(ArrayList examgList,String strUserId,String empid,String seq1) throws Exception {
		boolean flag = false;
		DBUtility	dbUtil=new DBUtility();
		try{
		dbUtil.setAutoCommit(false);
		
		for (int i = 0; i < examgList.size(); i++) {
			DeptExamDTO deptExamDTO = (DeptExamDTO) examgList.get(i);
			if (deptExamDTO.getNameOfExam() != null) {
				String sqlValues[] = {

						seq1,
						empid,
						deptExamDTO.getResult(),
						strUserId,
						strUserId

				};
				CommonSQL common = new CommonSQL();
				String sqlQuery = CommonSQL.INSERT_EMP_DEPT_EXAM_MAPPING_DETAILS;
				dbUtil.createPreparedStatement(sqlQuery);
				flag = dbUtil.executeUpdate(sqlValues);
				if (flag == false) {
					break;
				}
			}
		}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			dbUtil.closeConnection();
		}
		return flag;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @param empMaster
	 * @param childsList
	 * @param personalDetailsDTO
	 * @param userid
	 * @param userlist
	 * @return
	 * @throws Exception
	 */
	public boolean insertEmpFamilyMaster(String empMaster[],
			ArrayList childsList, PersonalDetailsDTO personalDetailsDTO,String userid,String userlist[])
			throws Exception {
		
		boolean b1 = false, b2 = false;
		try{
		b1 = insertEmpPersonalDetails(empMaster,childsList,personalDetailsDTO,userid,userlist);
	/*	if (b1 == false)
			return false;
		else
			b2 = insertEmpChildDetails(childsList, personalDetailsDTO,userid);*/

		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		} 
		return b1;
	}

	/**
	 * @param empMaster
	 * @return
	 */
	/**
	 * @param empMaster
	 * @param childsList
	 * @param personalDetailsDTO
	 * @param userid
	 * @param userlist
	 * @return
	 */
	public boolean insertEmpPersonalDetails(String empMaster[],ArrayList childsList,
			PersonalDetailsDTO personalDetailsDTO,String userid,String[] userlist) {
		DBUtility	dbUtil=null;
		boolean flag = false;
		try {	dbUtil=new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil
					.createPreparedStatement(CommonSQL.INSERT_EMP_PERSONAL_DETAILS);
			flag = dbUtil.executeUpdate(empMaster);			
			Iterator it = childsList.iterator();
			while (it.hasNext())

			{
				ChildDetailsDTO childDetailsDTO = (ChildDetailsDTO) it.next();

				try {
					String sqlvalues[] = {
							personalDetailsDTO.getEmployeeId(),
							childDetailsDTO.getChildName(),
							childDetailsDTO.getChildDay() + "-"
									+ childDetailsDTO.getChildMonth() + "-"
									+ childDetailsDTO.getChildYear(),
							childDetailsDTO.getChildGender(),
							userid,
							userid //Craeted By
					};
					dbUtil
							.createPreparedStatement(CommonSQL.INSERT_EMP_CHILD_DETAILS);
					flag = dbUtil.executeUpdate(sqlvalues);
					
				} catch (Exception e) {
					flag = false;
					logger.error(e.getMessage(), e);
				}
				finally {
					if (!flag)
						dbUtil.closeConnection();
						break;
				}
			}
				dbUtil.createPreparedStatement(CommonSQL.INSERT_EMP_USER_DETAILS);
				flag = dbUtil.executeUpdate(userlist);	
		if (flag) {
			dbUtil.commit();			
		} else {
			dbUtil.rollback();
			
			
		}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			try{
				dbUtil.rollback();
			}
			catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
		return flag;
	}
	
	
	//Inserting data in IGRS_USER_RE table
	/**
	 * @param empMaster
	 * @param regUser
	 * @return
	 */
	public boolean insertUserRegDetails(String[] empMaster, String[] regUser) {
		boolean userDetailsSubmit = false;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug(" DAO Layer----Inside insertUserRegDetails.....AA");
		try {
			logger.debug("Before inserting user details ");
			
			dbUtil.createPreparedStatement(CommonSQL.INSERT_EMP_TO_REG_DETAILS_TABLE);
			userDetailsSubmit = dbUtil.executeUpdate(empMaster);
			logger.debug(" DAO Layer----Inside TRY....AA");
			
//			if(regUser.length > 0)
//		    {

//		     dbUtil.createPreparedStatement(CommonSQL.USER_ROLE_INSERT);
//			userDetailsSubmit = dbUtil.executeUpdate(regUser);
//				dbUtil.commit();
//		    }
		} catch (Exception x) {
			logger.debug("Exception in insertUserRegDetails() :- " + x);
			logger.error(x.getMessage(), x);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.debug(x);
			}
		}
		return userDetailsSubmit;
	}
	
	/**
	 * @return
	 */
	public ArrayList getHintQuestions() {
		logger.debug("DAO:   We are in getHintQuestions()");
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			
			 dbUtil.createStatement();
			 list = dbUtil.executeQuery("SELECT QUESTION_ID,QUESTION_TEXT " +
					 "FROM IGRS_USER_HINT_QUESTIONS WHERE QUESTION_STATUS='A'");
		} catch (Exception e) {
			logger.info("Exception in getCountry():" + e);
			logger.error(e.getMessage(), e);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				logger.error(x.getMessage(),x);
			}
		}
		return list;
	}

	
	
	//End of Method...
	/**
	 * @param childsList
	 * @param personalDetailsDTO
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param childsList
	 * @param personalDetailsDTO
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public boolean insertEmpChildDetails(ArrayList childsList,
			PersonalDetailsDTO personalDetailsDTO,String userid) throws Exception {
		DBUtility dbUtil=new DBUtility();
		Iterator it = childsList.iterator();
		int i = 1;
		boolean flag = false;
		//dbUtil.getDBConnection();
		dbUtil.setAutoCommit(false);
		while (it.hasNext())

		{
			ChildDetailsDTO childDetailsDTO = (ChildDetailsDTO) it.next();

			try {
				String sqlvalues[] = {
						personalDetailsDTO.getEmployeeId(),
						childDetailsDTO.getChildName(),
						childDetailsDTO.getChildDay() + "-"
								+ childDetailsDTO.getChildMonth() + "-"
								+ childDetailsDTO.getChildYear(),
						childDetailsDTO.getChildGender(),
						userid,
						userid //Craeted By
				};
				dbUtil
						.createPreparedStatement(CommonSQL.INSERT_EMP_CHILD_DETAILS);
				flag = dbUtil.executeUpdate(sqlvalues);
				i++;
				if (!flag)
					break;
			} catch (Exception e) {// TODO Auto-generated catch block
				logger.error(e.getMessage(), e);
			}
		}
		if (flag) {
			dbUtil.commit();
			dbUtil.closeConnection();
			
		} else {
			dbUtil.rollback();
			dbUtil.closeConnection();
			
		}
		return flag;
	}

	/**
	 * @param empId
	 * @return
	 */
	/**
	 * @param empId
	 * @return
	 */
	public String searchEmpID(String empId) {
		String value = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
		//	dbUtil.getDBConnection();
			String sqlSearch = "SELECT EMP_ID FROM IGRS_EMP_MASTER iem where iem.EMP_ID='"
					+ empId + "'";
			dbUtil.createStatement();
			value = dbUtil.executeQry(sqlSearch);
	
	}catch (Exception e) {
		logger.error(e.getMessage(), e);
	} finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		return value;
	}

	//********************************************Bank Details**************************************
	/**
	 * @return
	 * @throws Exception
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList fundDetails() throws Exception {
		ArrayList list = null;
		DBUtility dbUtil=new DBUtility();
		try{
		//dbUtil = new DBUtility();
		dbUtil.createStatement();
		String sqlQuery = CommonSQL.SELECT_EMP_FUND_TYPE;
		list = dbUtil.executeQuery(sqlQuery);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		 finally {
			 dbUtil.closeConnection();
		 }
		return list;
	}

	/**
	 * @param bankDTO
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param bankDTO
	 * @param fundlist
	 * @param nomineeslist
	 * @param userId
	 * @param employeeId
	 * @return
	 * @throws Exception
	 */
	public boolean insertBankDetails(BankDTO bankDTO,ArrayList fundlist,ArrayList nomineeslist,String userId,String employeeId) throws Exception {
		boolean flag = false;
		DBUtility dbUtil=new DBUtility();
		try {
			String sqlValues[] = {
					employeeId, bankDTO.getBankName(), bankDTO.getBankBranch(),
					bankDTO.getBankAddress(), bankDTO.getNameAsInBank(),
					bankDTO.getPanNo(), bankDTO.getBankAccountNo(),
					userId,userId,bankDTO.getBankIFSC()};

			String sqlQuery = CommonSQL.INSERT_EMP_BANK_ACCOUNT_DETAILS;
			try {
				deleteExistingBankFundRecords(employeeId);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			//dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			flag = dbUtil.executeUpdate(sqlValues);
			
			if(flag){
				flag = addFundsPlusNominees(fundlist,userId,employeeId);
			}
			
			if (flag) {
				dbUtil.commit();
			} else {
				dbUtil.rollback();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

		}

		return flag;
	}

	/**
	 * @param employeeId
	 * @throws Exception
	 */
	private void deleteExistingBankFundRecords(String employeeId)
			throws Exception {
		DBUtility dbUtil=new DBUtility();
		try {
			//dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(CommonSQL.DELETE_EMP_BANK_NOMINEES);
			dbUtil.executeUpdate(new String[] { employeeId });
			dbUtil.createPreparedStatement(CommonSQL.DELETE_EMP_BANK_FUNDDETAILS);
			dbUtil.executeUpdate(new String[] { employeeId });
			dbUtil.createPreparedStatement(CommonSQL.DELETE_EMP_BANK_DETAILS);
			dbUtil.executeUpdate(new String[] { employeeId });
			dbUtil.commit();
			dbUtil.closeConnection();
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
	}

	/**
	 * @param fundslist
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param fundslist
	 * @param userid
	 * @param employeeId
	 * @return
	 * @throws Exception
	 */
	public boolean addFundsPlusNominees(ArrayList fundslist,String userid,String employeeId) throws Exception {
		boolean flag = false;
		DBUtility dbUtil=new DBUtility();
		try{
		if(fundslist == null || fundslist.size() == 0) {
			flag = true;
		}
		for (int i = 0; i < fundslist.size(); i++) {
			FundDTO fundDTO = (FundDTO) fundslist.get(i);
			if (fundDTO.getType() != null) {
				String sqlValues[] = { employeeId, fundDTO.getType(),
						fundDTO.getAccountNo(), fundDTO.getAccountLocation(),
						userid, userid };
				String sqlQuery = CommonSQL.INSERT_EMP_FUND_DETAILS;
				//dbUtil.getDBConnection();
				dbUtil.createPreparedStatement(sqlQuery);
				flag = dbUtil.executeUpdate(sqlValues);
				if(flag) {
					if (fundDTO.getNomineeList() != null) {
						flag = addNominees(fundDTO.getNomineeList(), userid,
								employeeId, fundDTO.getAccountNo());
					}
				}
			}
		}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			
				dbUtil.closeConnection();
			} 		
		return flag;
	}

	/**
	 * 
	 * @param nomineeslist
	 * @param userId
	 * @param employeeId
	 * @param accountNo
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param nomineeslist
	 * @param userId
	 * @param employeeId
	 * @param accountNo
	 * @return
	 */
	public boolean addNominees(ArrayList nomineeslist,String userId,String employeeId, String accountNo) {
		boolean flag = false;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {	//dbUtil.getDBConnection();
				String sqlQuery = CommonSQL.INSERT_EMP_NOMINEE_DETAILS;
				dbUtil.createPreparedStatement(sqlQuery);
			for (int i = 0; i < nomineeslist.size(); i++) {
				NomineeDTO nomineeDTO = (NomineeDTO) nomineeslist.get(i);
				String sqlValues[] = { employeeId, nomineeDTO.getFundTypeId(),
						nomineeDTO.getNomineeName(),
						nomineeDTO.getRelationWithNominee(),
						nomineeDTO.getNomineeAddress(),
						nomineeDTO.getNomineeAge(), userId, userId, accountNo, nomineeDTO.getNomineePercentage() };
				
				try {
					flag = dbUtil.executeUpdate(sqlValues);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			flag = true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}finally {
			
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 	
		return flag;
	}

	//***************************************End Bank*************************************


	/**
	 * @param academicList
	 * @param prevEmpList
	 * @param employeeid
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public boolean addTalent(ArrayList academicList,ArrayList prevEmpList,String employeeid,String userid)
			throws Exception {
		DBUtility dbUtil=new DBUtility();
		boolean b1 = false, b2 = false;
		int rowCount, delCount;
		String sCount, errMsg;
		//dbUtil.getDBConnection();
		dbUtil.setAutoCommit(false);
		logger.debug("In Talent DAO");
		dbUtil.createPreparedStatement(CommonSQL.GET_EMP_ACDPRV_COUNT);
		String tmpData = dbUtil.executeQry(new String[]{employeeid, employeeid});
		rowCount = Integer.parseInt(tmpData);
		if(rowCount > 0) {
			CallableStatement callStmt = dbUtil.createCallableStatement("CALL IGRS_EMP_DELTALENT_PROC(?,?,?)");
			callStmt.setString(1, employeeid);
			callStmt.registerOutParameter(2, Types.VARCHAR);
			callStmt.registerOutParameter(3, Types.VARCHAR);
			callStmt.execute();
			sCount = callStmt.getString(2);
			errMsg = callStmt.getString(3);
			delCount = Integer.parseInt(sCount);
			logger.debug("Count : " + sCount + " ErrMsg : " + errMsg);
			logger.debug("RowCount == DelCount : " + (delCount==rowCount));
			callStmt.close();
		}
		b1 = addAcademics(academicList,employeeid,userid);
		logger.debug("Acadamics"+b1);
		if (b1 == false)
			return false;
		else
			b2 = addPrevious(prevEmpList,employeeid,userid);
		logger.debug("Previous"+b2);
		if(b1 && b2)
			{	try {
					dbUtil.commit();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}finally {
					
					dbUtil.closeConnection();
				} 		
			}
			else
			{
				try {
					dbUtil.rollback();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}finally {
					
					dbUtil.closeConnection();
				} 	
			}
		return (b1 && b2);
	}
 
	
	
	/**
	 * @param academicList
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param academicList
	 * @param employeeid
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public boolean addAcademics(ArrayList academicList,String employeeid,String userid) throws Exception {
//		dbUtil.setAutoCommit(false);
		DBUtility dbUtil=new DBUtility();
		boolean flag = false;
		if(academicList.size()==0) {
			return true;
		}
		for (int i = 0; i < academicList.size(); i++) {
			AcademicDTO academicDTO = (AcademicDTO) academicList.get(i);
			if (academicDTO.getPassingYear() != null) {
				String sqlValues[] = {
						employeeid, academicDTO.getDegree(),
						academicDTO.getStream(), academicDTO.getPassingYear(),
						academicDTO.getGrade(), userid,
						userid};
				String sqlQuery = CommonSQL.INSERT_EMP_ACADEMIC_DETAILS;
				dbUtil.createPreparedStatement(sqlQuery);
				flag = dbUtil.executeUpdate(sqlValues);
				if (flag == false) {
					break;
				}
			}
		}

		
		return flag;
	}

	/**
	 * @param prevEmpList
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param prevEmpList
	 * @param employeeid
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public boolean addPrevious(ArrayList prevEmpList,String employeeid,String userid) throws Exception {
//		dbUtil.setAutoCommit(false);
		DBUtility dbUtil=new DBUtility();
		boolean flag = false;
		int months, years, totMonths;
		if(prevEmpList.size()==0) {
			return true;
		}
		for (int i = 0; i < prevEmpList.size(); i++) {
			PrevEmpDTO prevDTO = (PrevEmpDTO) prevEmpList.get(i);
				String sqlValues[] = {
					employeeid,
					prevDTO.getOrganization(),
					prevDTO.getDesignation(),
					"",
					prevDTO.getCompensation(), 
					prevDTO.getPfAccNo(),
					prevDTO.getPfAccLocation(),
					prevDTO.getReasonForSeparation(),
					prevDTO.getTaxDeductions(),
					userid,
					userid, 
					prevDTO.getFromDate(), 
					prevDTO.getToDate()
				};
				try{
				//dbUtil.getDBConnection();
				String sqlQuery = CommonSQL.INSERT_EMP_PREVIOUSEMPLOYMENT_DETAILS;
				dbUtil.createPreparedStatement(sqlQuery);
				flag = dbUtil.executeUpdate(sqlValues);
				if (flag == false) {
					break;
				}
				}catch(Exception e){
					e.printStackTrace();
				}
					finally{
				
					 dbUtil.closeConnection();
				}
		}
		return flag;
	}

	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getExam(String empId) {
		ArrayList rows = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {//dbUtil.getDBConnection();
			//			RETRIEVING DATA as ArrayList
			String sqlSearch = "select dept_exam_name,date_of_exam,organising_authority,place_of_exam,organisin_body,date_of_result,exam_details from IGRS_EMP_DEPT_EXAM_DETAILS where dept_exam_txn_id=?";
			dbUtil.createPreparedStatement(sqlSearch);
			String sqlval[] = {
				empId };
			rows = dbUtil.executeQuery(sqlval);
			
			//dbUtil.closeConnection();
		} catch (Exception e) {
			
		}
		;
		return rows;
	}

	/**
	 * @param empId
	 * @return
	 */
	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getTraining(String empId) {
		ArrayList rows = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {//dbUtil.getDBConnection();
			//			RETRIEVING DATA as ArrayList
			String sqlSearch = "SELECT TRAINING_TXN_ID,TRAINING_NAME,TRAINEE_LEVEL,ORGANISING_AUTHORITY,ORGANISATION_BODY,PLACE_OF_TRAINING,FINANCIAL_YEAR,AUTHORISING_AUTHORITY,TRAINING_COST,TRAINING_BODY,TRAINING_COMMENTS  from igrs_emp_training_details where TRAINING_TXN_ID=?";
			dbUtil.createPreparedStatement(sqlSearch);
			String sqlval[] = {
				empId };
			rows = dbUtil.executeQuery(sqlval);
			
			//dbUtil.closeConnection();
		} catch (Exception e) {
			
		}finally{
			 try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rows;
	}
	/**
	 * @param propertyList
	 * @return
	 * @throws Exception
	 */
	
	/**
	 * @param propertyList
	 * @param assetList
	 * @param userId
	 * @param strEmployeeId
	 * @return
	 * @throws Exception
	 */
	public boolean addProperty(ArrayList propertyList, ArrayList assetList,
			String userId, String strEmployeeId) throws Exception {

		boolean b1 = false, b2 = false;
		boolean flag = false;
		DBUtility dbUtil=new DBUtility();
		try {
		//	dbUtil.getDBConnection();
			dbUtil.setAutoCommit(false);
			String sqlQuery = CommonSQL.GET_EMP_PROPASSET_COUNT;
			dbUtil.createPreparedStatement(sqlQuery);
			String rowCount = dbUtil.executeQry(new String[]{strEmployeeId});
			int count = Integer.parseInt(rowCount);
			if(count > 0) {
				sqlQuery = CommonSQL.DELETE_EMP_PROPASSET;
				dbUtil.createPreparedStatement(sqlQuery);
				boolean delFlag = dbUtil.executeUpdate(new String[]{strEmployeeId});
				logger.debug(delFlag);
			}
			b1 = insertProperty(propertyList, userId, strEmployeeId);
			if (b1) {
				b2 = insertAsset(assetList, userId, strEmployeeId);
				if (b2) {
					flag = true;
				} else {
					flag = false;
				}
			} else {
				flag = false;
			}

			if (flag) {
				try {
					dbUtil.commit();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			} else {
				try {
					dbUtil.rollback();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		} catch (Exception e) {

			dbUtil.rollback();

		} finally {
			dbUtil.closeConnection();
		}
		return flag;
	}


	
	/**
	 * @param propertyList
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param propertyList
	 * @param userId
	 * @param strEmployeeId
	 * @return
	 * @throws Exception
	 */
	public boolean insertProperty(ArrayList propertyList,String userId,String strEmployeeId) throws Exception {
		boolean flag = false;
		DBUtility dbUtil=new DBUtility();
		try{
		//dbUtil.getDBConnection();
		dbUtil.setAutoCommit(false);
		
		if(propertyList.size() == 0) {
			return true;
		}
		for (int i = 0; i < propertyList.size(); i++) {
			PropertyDTO propertyDTO = (PropertyDTO) propertyList.get(i);
			if (propertyDTO.getPropertyaddress() != null) {
				String sqlValues[] = {
						strEmployeeId,
						propertyDTO.getPropertyaddress(),
						propertyDTO.getPropertycountry(),
						propertyDTO.getPropertystate(),
						propertyDTO.getPropertydistrict(),
						propertyDTO.getPropertypostalcode(),
						propertyDTO.getPropertyshare(),
						propertyDTO.getPropertyregid(),
						CommonUtil.getConvertedDate(propertyDTO.getPropertyregdate()),userId,userId,"P" };
						String sqlQuery = CommonSQL.INSERT_EMP_PROPERTY_DETAILS;
						dbUtil.createPreparedStatement(sqlQuery);
						flag = dbUtil.executeUpdate(sqlValues);
						if (flag == false) {
							break;
						}

			}
		}
}catch(Exception e){
		
	}finally{
		 dbUtil.closeConnection();
	}
		return flag;
	}

	/**
	 * @param assetList
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param assetList
	 * @param userId
	 * @param strEmployeeId
	 * @return
	 * @throws Exception
	 */
	public boolean insertAsset(ArrayList assetList,String userId,String strEmployeeId) throws Exception {
		boolean flag = false;
		DBUtility dbUtil=new DBUtility();
	try{
		//dbUtil.getDBConnection();
		dbUtil.setAutoCommit(false);
	
		if(assetList.size() == 0) {
			return true;
		}
		for (int i = 0; i < assetList.size(); i++) {
			AssetDTO assetDTO = (AssetDTO) assetList.get(i);
			if (assetDTO.getAssestdetails() != null) {
				
				String sqlValues[] = {
						strEmployeeId, assetDTO.getAssettype(),
						assetDTO.getAssestdetails(), assetDTO.getAssetValue(),
						 userId,userId,"A" };
				CommonSQL common = new CommonSQL();
				String sqlQuery = CommonSQL.INSERT_EMP_ASSET_DETAILS;
				dbUtil.createPreparedStatement(sqlQuery);
				flag = dbUtil.executeUpdate(sqlValues);
				if (flag == false) {
					break;
				}
			}
		}
	}catch(Exception e){
		
	}finally{
		 dbUtil.closeConnection();
	}
		
		return flag;

	}

	/**
	 * @return
	 * @throws Exception
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getCountryList() throws Exception {
		ArrayList list=null;
		DBUtility dbUtil=new DBUtility();
		try{
		try {//dbUtil.getDBConnection();
			dbUtil.createStatement();
			list=dbUtil.executeQuery(CommonSQL.COUNTRY_ALL_QUERY);
//			IGRSCommon common = new IGRSCommon();
//			list = common.getCountry();
//			COUNTRY_QUERY = "Select COUNTRY_ID, "
//	        		+ "COUNTRY_NAME from IGRS_COUNTRY_MASTER "
//	        		+"WHERE COUNTRY_STATUS='A' ORDER BY COUNTRY_NAME ASC";
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	 finally {
		 dbUtil.closeConnection();
	 }
		return list;
	}

	/**
	 * @param country
	 * @return
	 * @throws Exception
	 */
	/*public ArrayList getStateList(String country) throws Exception {
		IGRSCommon common = new IGRSCommon();
		ArrayList list = common.getState(country);
		return list;
	}*/

	
	/**
	 * @return
	 */
	public ArrayList getStateList() {
		ArrayList list=null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		try{//dbUtil.getDBConnection();
			dbUtil.createStatement();
			list=dbUtil.executeQuery(CommonSQL.STATE_ALL_QUERY);
			
		}
		catch (Exception exception) {
			logger.error(exception.getMessage(),exception);
		}		
	
	 finally {
		 try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
		return list;
	}
	/**
	 * @param state
	 * @return
	 * @throws Exception
	 */
	/*public ArrayList getDistrictList(String state) throws Exception {
		IGRSCommon common = new IGRSCommon();
		ArrayList list = common.getDistrict(state);
		return list;
	}*/
	
	/**
	 * @return
	 */
	public ArrayList getDistrictList() {
		ArrayList list=null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try{//dbUtil.getDBConnection();
			dbUtil.createStatement();
			list=dbUtil.executeQuery(CommonSQL.DISTRICT_ALL_QUERY);
			
		}
		catch (Exception exception) {
			logger.error(exception.getMessage(),exception);
		}		
	
	 finally {
		 try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
		return list;
	}

	/**
	 * @param servicelist
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param servicelist
	 * @return
	 * @throws Exception
	 */
	public boolean submitService(ArrayList servicelist) throws Exception {
		DBUtility dbUtil=new DBUtility();
		dbUtil.setAutoCommit(false);
		boolean flag = false;
		try{
		for (int i = 0; i < servicelist.size(); i++) {
			ServiceVerificationDTO serviceVerificationDTO = (ServiceVerificationDTO) servicelist
					.get(i);
			String sqlValues[] = {

					"",//EMP_ID,		// // EMP_id
					serviceVerificationDTO.getVerifyingAuthority(),//VERIFYING_AUTH_NAME,//
					CommonUtil.getConvertedDate(serviceVerificationDTO.getDateOfVerivication()), //VERIFICATION_DATE,	
					serviceVerificationDTO.getComments(),//COMMENTS	
					"", //VERIFICATION_STATUS,
					"", //CREATED_BY	
					//CREATED_DATE	
					"", //UPDATE_BY
					"" //UPDATE_DATE

			};
			CommonSQL common = new CommonSQL();
			String sqlQuery = CommonSQL.INSERT_SERVICE;
			dbUtil.createPreparedStatement(sqlQuery);
			flag = dbUtil.executeUpdate(sqlValues);
			if (flag == false) {
				break;
			}

		}

		if (flag == true) {
			dbUtil.commit();
		} else {
			dbUtil.rollback();
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	 finally {
		 dbUtil.closeConnection();
	 }
		return flag;
	}

	
	
	/**
	 * @param officalInfoDTO
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param officalInfoDTO
	 * @return
	 * @throws Exception
	 */
	public boolean insertOfficalInfo(OfficalInfoDTO officalInfoDTO)
			throws Exception {
		DBUtility dbUtil=new DBUtility();
		String sqlValues[] = {
				"e001", //EMP_ID	
				officalInfoDTO.getDesignation(), //EMP_DESIGNATION_ID	
				officalInfoDTO.getClass1(), //EMP_CLASS_ID	
				"A", //OFFICIATING	
				officalInfoDTO.getEmployeeStatus(), //EMP_STATUS	
				officalInfoDTO.getSupervisorName(), //Supervisorid								//SUPERVISOR_ID	
				CommonUtil.getConvertedDate(officalInfoDTO.getDateOfJoining()), //DATE_OF_JOINING	
				CommonUtil.getConvertedDate(officalInfoDTO.getDateOfSaperation()), //DATE_OF_SEPARATION							
				officalInfoDTO.getCompEmplRefrence(), //COMP_EMP_NO	
				"A", //EMP_OFFICIAL_STATUS
				"", //CREATED_BY	
				//"",									//CREATED_DATE	
				"", //UPDATE_BY	
				"", //UPDATE_DATE	
				officalInfoDTO.getDesiOffic() //OFFICATING_ID

		};
		//dbUtil.getDBConnection();
		String sqlQuery = CommonSQL.INSERT_OFFICAL_DETAILS;
		dbUtil.setAutoCommit(false);
		dbUtil.createPreparedStatement(sqlQuery);

		boolean flag = dbUtil.executeUpdate(sqlValues);
		try{
		if (flag == true) {
			dbUtil.commit();
		} else {
			dbUtil.rollback();
		}
		
			} catch (Exception e) {
				e.printStackTrace();
			}
			 finally {
				 dbUtil.closeConnection();
			 }
		return flag;

	}

	

	/**
	 * @return
	 * @throws Exception
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAllgradeList() throws Exception {
		ArrayList list=null;
		DBUtility dbUtil=new DBUtility();
		try{//dbUtil.getDBConnection();
		dbUtil.createStatement();
		String query = CommonSQL.SELECT_ALL_GRADE;
		list = dbUtil.executeQuery(query);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		 finally {
			 dbUtil.closeConnection();
		 }
		return list;

	}

	/**
	 * @param gradeId
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param gradeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAllCadres(String gradeId) throws Exception {
		ArrayList list =null;
		DBUtility dbUtil=new DBUtility();
		try{
		String sqlvalues[] = {
			gradeId };
		//dbUtil.getDBConnection();
		dbUtil
				.createPreparedStatement(CommonSQL.SELECT_DESIGNATION_CADRE_GRADE);
		//	String query=CommonSQL.RETRIVE_ALL_CADRE_ID;
		list = dbUtil.executeQuery(sqlvalues);
	} catch (Exception e) {
		e.printStackTrace();
	}
	 finally {
		 dbUtil.closeConnection();
	 }
		return list;
	}

	/**
	 * @param empid
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param empid
	 * @return
	 * @throws Exception
	 */
	public ArrayList getReportingHirachy(String empid) throws Exception {
		ArrayList list=null;
		DBUtility dbUtil=new DBUtility();
		try{
		String sqlvalues[] = {
			empid };
		//dbUtil.getDBConnection();
		dbUtil.createPreparedStatement(CommonSQL.SELECT_REPORTING_HIRACHY);
		list = dbUtil.executeQuery(sqlvalues);
	} catch (Exception e) {
		e.printStackTrace();
	}
	 finally {
		 dbUtil.closeConnection();
	 }
		return list;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	

	/**
	 * @param empid
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param empid
	 * @return
	 * @throws Exception
	 */
	public ArrayList getRepoteeList(String empid) throws Exception {
		ArrayList list =null;
		DBUtility dbUtil=new DBUtility();
		try{
		String sqlvalues[] = {			empid };
		//dbUtil.getDBConnection();
		dbUtil.createPreparedStatement(CommonSQL.SELECT_DESIGNATION_NAME);
	list = dbUtil.executeQuery(sqlvalues);
	} catch (Exception e) {
		e.printStackTrace();
	}
	 finally {
		 dbUtil.closeConnection();
	 }
		return list;
	}

	/**
	 * @param GRADE_ID
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param GRADE_ID
	 * @return
	 * @throws Exception
	 */
	public ArrayList getGradeCadreList(String GRADE_ID) throws Exception {
		ArrayList list =null;
		DBUtility dbUtil=new DBUtility();
		try{
		String sqlvalues[] = {
			GRADE_ID };
		//dbUtil.getDBConnection();
		dbUtil
				.createPreparedStatement(CommonSQL.SELECT_DESIGNATION_CADRE_GRADE);
		list = dbUtil.executeQuery(sqlvalues);
	} catch (Exception e) {
		e.printStackTrace();
	}
	 finally {
		 dbUtil.closeConnection();
	 }
		return list;
	}

	/**
	 * @param DESIGNATION_ID
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param DESIGNATION_ID
	 * @return
	 * @throws Exception
	 */
	public ArrayList getReportingHirachyDesignation(String DESIGNATION_ID)
			throws Exception {
		DBUtility dbUtil=new DBUtility();
		ArrayList list=null;
		try{
		String sqlvalues[] = {
			DESIGNATION_ID };
		//dbUtil.getDBConnection();
		dbUtil
				.createPreparedStatement(CommonSQL.SELECT_REPORTING_HIRACHY_DESIGNATION);
		list = dbUtil.executeQuery(sqlvalues);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 finally {
				 dbUtil.closeConnection();
			 }
		return list;
	}

	
	
	/**
	 * @return
	 */
	private String getTrainningSequenceId()
	{DBUtility dbUtil=null;
	try {
		dbUtil = new DBUtility();
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		String txnId ="";
		String txnIdConst ="";
		String txnIdValue ="";
		try {
			//dbUtil = new DBUtility();
			String sqlQuery = CommonSQL.GENERATE_SEQUENCE_ID;
			dbUtil.createStatement();
			txnId =dbUtil.executeQry(sqlQuery);
			txnIdConst ="TRAN-";
			txnIdValue=txnIdConst+txnId;
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			 try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		
		return txnIdValue;
	}
	
	
	/**
	 * @return
	 */
	private String getExamSequenceId()
	{
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String txnId ="";
		String txnIdConst ="";
		String txnIdValue ="";
		try {
			//dbUtil = new DBUtility();
			String sqlQuery = CommonSQL.GENERATE_EXAM_SEQUENCE_ID;
			dbUtil.createStatement();
			txnId =dbUtil.executeQry(sqlQuery);
			txnIdConst ="EXAM-";
			txnIdValue=txnIdConst+txnId;
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			 try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		return txnIdValue;
	}

	/**
	 * @return
	 */
	public ArrayList getFundAccNo() {
		ArrayList list =null;	
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{//dbUtil.getDBConnection();
		dbUtil.createStatement();
		String query = CommonSQL.SELECT_ALL_FUNDACCNO;
		 list = dbUtil.executeQuery(query);
		
		}catch (Exception exception) {
			logger.error(exception.getMessage(),exception);
		} finally {
			 try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		return list;
		
	}
	
	/**
	 * @param empID
	 * @return
	 */
	public ArrayList getExcludedFundAccNo(String empID) {
		ArrayList list =null;	
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
		
		String query = CommonSQL.SELECT_EXCLUDED_FUNDACCNO;
		//dbUtil.getDBConnection();
		dbUtil.createPreparedStatement(query);
		list = dbUtil.executeQuery(new String[]{empID});
		
		}catch (Exception exception) {
			logger.error(exception.getMessage(),exception);
		} finally {
			 try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		return list;
		
	}

	/**
	 * @param employeeId
	 * @return
	 */
	public ArrayList getPropertyList(String employeeId) {
		ArrayList list =null;	
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{//dbUtil.getDBConnection();
			String query = CommonSQL.GET_EMP_PROPERTY_LIST;
			dbUtil.createPreparedStatement(query);			
			list = dbUtil.executeQuery(new String[]{employeeId, "P"});
//			dbUtil.closeConnection();
		}	catch (Exception exception) {
			logger.error(exception.getMessage(),exception);
		} finally {
			 try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		return list;
	}

	/**
	 * @param employeeId
	 * @return
	 */
	public ArrayList getAssetList(String employeeId) {
		ArrayList list =null;	
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{//dbUtil.getDBConnection();
			String query = CommonSQL.GET_EMP_ASSET_LIST;
			dbUtil.createPreparedStatement(query);
			list = dbUtil.executeQuery(new String[]{employeeId, "A"});
//			dbUtil.closeConnection();
		}	catch (Exception exception) {
			logger.error(exception.getMessage(),exception);
		} finally {
			 try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		return list;
	}
	
	/**
	 * @param employeeId
	 * @return
	 */
	public ArrayList getAcademicInfoList(String employeeId) {
		ArrayList list =null;	
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{//dbUtil.getDBConnection();
			String query = CommonSQL.GET_EMP_ACD_LIST;
			dbUtil.createPreparedStatement(query);			
			list = dbUtil.executeQuery(new String[]{employeeId});
//			dbUtil.closeConnection();
		}	catch (Exception exception) {
			logger.error(exception.getMessage(),exception);
		} finally {
			 try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		return list;
	}
	
	/**
	 * @param employeeId
	 * @return
	 */
	public ArrayList getPrevEmpList(String employeeId) {
		ArrayList list =null;	
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{//dbUtil.getDBConnection();
			String query = CommonSQL.GET_EMP_PRV_LIST;
			dbUtil.createPreparedStatement(query);			
			list = dbUtil.executeQuery(new String[]{employeeId});
//			dbUtil.closeConnection();
		}	catch (Exception exception) {
			logger.error(exception.getMessage(),exception);
		} finally {
			 try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		return list;
	}
	
	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getFundDetails(String empId) {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList fundlist = new ArrayList();
		String sqlValues[] = new String[]{empId};
		String sqlQuery = CommonSQL.RETRIEVE_FUND_DETAILS;
		try {

			//dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			list = dbUtil.executeQuery(sqlValues);
			
			for (int i = 0; i < list.size(); i++) {

				ArrayList fund = (ArrayList) list.get(i);
				if (list != null) {
					FundDTO fundDTO = new FundDTO();
					fundDTO.setType((String) fund.get(0));
					fundDTO.setAccountNo((String) fund.get(1));
					fundDTO.setAccountLocation((String) fund.get(2));
					fundDTO.setType((String) fund.get(3));
					fundlist.add(fundDTO);

				}

			}

			return fundlist;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {

				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return fundlist;
			
	}
	
	
	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getNomineeDetails(String empId) {
		ArrayList list = null;
		ArrayList nomineelist = new ArrayList();
		String sqlValues[] = new String[1];
		sqlValues[0] = empId;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sqlQuery = CommonSQL.RETRIEVE_NOMINEE_DETAILS;
		try {

			//dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			list = dbUtil.executeQuery(sqlValues);
			for (int i = 0; i < list.size(); i++) {

				ArrayList nominee = (ArrayList) list.get(i);
				if (list != null) {
					NomineeDTO nomineeDTO = new NomineeDTO();

					nomineeDTO.setFundTypeId((String) nominee.get(0));
					nomineeDTO.setNomineeName((String) nominee.get(1));
					nomineeDTO.setRelationWithNominee((String) nominee.get(2));
					nomineeDTO.setNomineeAddress((String) nominee.get(3));
					nomineeDTO.setNomineeAge((String) nominee.get(4));
					nomineeDTO.setStrAccountNumber((String) nominee.get(5));
					nomineeDTO.setFundTypeId((String) nominee.get(6));
					nomineelist.add(nomineeDTO);

				}
			}

			return nomineelist;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {

				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return nomineelist;

	}

	/**
	 * @param nomineeList
	 * @param userId
	 * @param employeeId
	 * @param fundAccountNo
	 * @return
	 */
	public boolean insertNomineeDetails(ArrayList nomineeList, String userId,
			String employeeId, String fundAccountNo) {
		boolean ret = false;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		//	dbUtil = new DBUtility();
			String sqlQuery;
			String[] params;
			try {
				sqlQuery = CommonSQL.DELETE_EMP_BANK_NOMINEES;
				dbUtil.createPreparedStatement(sqlQuery);
				dbUtil.executeUpdate(new String[]{employeeId});
				sqlQuery = CommonSQL.INSERT_EMP_NOMINEE_DETAILS;
				//EMP_ID,FUND_TYPE_ID,NOMINEE_NAME,NOMINEE_RELATIONSHIP,NOMINEE_ADDRESS,NOMINEE_AGE,CREATED_BY,UPDATE_BY,FUND_ACCOUNT_NO
				dbUtil.createPreparedStatement(sqlQuery);
				for (Object item : nomineeList) {
					NomineeDTO nomDTO = (NomineeDTO) item;
					params = new String[] { employeeId, nomDTO.getFundTypeId(),
							nomDTO.getNomineeName(),
							nomDTO.getRelationWithNominee(),
							nomDTO.getNomineeAddress(), nomDTO.getNomineeAge(),
							userId, userId, nomDTO.getStrAccountNumber(), nomDTO.getNomineePercentage() };
					dbUtil.executeUpdate(params);
				}
				dbUtil.commit();
				ret = true;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				dbUtil.rollback();
			}
			finally {
				
				dbUtil.closeConnection();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return ret;
		
	}

	/**
	 * @param fundAccountNo
	 * @return
	 */
	public String[] getFundTypeData(String fundAccountNo) {
		String[] data = new String[2];
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			//dbUtil = new DBUtility();
			try {
				dbUtil.createPreparedStatement(CommonSQL.GET_FUND_DATA_ACCOUNT_NO);
				ArrayList dataSet = dbUtil.executeQuery(new String[]{fundAccountNo});
				ArrayList row = (ArrayList) dataSet.get(0);
				data[0] = (String) row.get(0);
				data[1] = (String) row.get(1);
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return data;
	}

	/**
	 * @param type
	 * @return
	 */
	public String getFundTypeName(String type) {
		String fundName = "";
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			//dbUtil = new DBUtility();
			try {
				dbUtil.createPreparedStatement(CommonSQL.GET_FUND_NAME_TYPE);
				ArrayList dataSet = dbUtil.executeQuery(new String[]{type});
				ArrayList row = (ArrayList) dataSet.get(0);
				fundName  = (String) row.get(0);
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return fundName;
	}

	/**
	 * @return
	 */
	public ArrayList<BankMstDTO> getBankMasterList() {
		ArrayList<BankMstDTO> list = new ArrayList<BankMstDTO>();
		ArrayList dataSet, row;
		BankMstDTO entry;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		//	DBUtility util = new DBUtility();
			try {	//dbUtil.getDBConnection();
				dbUtil.createStatement();
				//BANK_ID, BANK_NAME, BANK_PHONE_NUMBER, BANK_ADDRESS
				dataSet = dbUtil.executeQuery(CommonSQL.GET_BANKMASTERLISTING);
				if(dataSet != null) {
					for (Object item : dataSet) {
						row = (ArrayList) item;
						entry = new BankMstDTO();
						entry.setBankID((String) row.get(0));
						entry.setBankName((String) row.get(1));
						entry.setBankPhone((String) row.get(2));
						entry.setBankAddress((String) row.get(3));
						row.clear();
						list.add(entry);
					}
					dataSet.clear();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				list.trimToSize();
				//util.closeConnection();
				dbUtil.closeConnection();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

	/**
	 * @return
	 */
	public ArrayList getRelativeMasterList() {
		ArrayList dataSet = null;
		try {
			DBUtility dbUtil=new DBUtility();
			try {	//dbUtil.getDBConnection();
				dbUtil.createStatement();
				dataSet = dbUtil.executeQuery(CommonSQL.GET_RELATIVETYPEMASTERLISTING);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				dbUtil.closeConnection();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return dataSet;
	}

	/**
	 * @param dto
	 * @param userID
	 * @return
	 */
	public boolean insertEmpFamilyData(PersonalDetailsDTO dto,
			String userID) {
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean retVal = false;
		boolean flag = false;
		try {
			String[] params;
			DBUtility util = new DBUtility();
			CryptoLibrary crypt = new CryptoLibrary();
			String password;
			try {
				util.setAutoCommit(false);
				//TODO get EMPID by Sequence
				util.createStatement();
				logger.debug("Getting New Employee ID");
				String newEmployeeID = util.executeQry(CommonSQL.GENERATE_NEW_EMP_ID);
				logger.debug("New Employee ID from Sequence : " + newEmployeeID);
				dto.setEmployeeId(newEmployeeID);
				dto.setDateOfBirth(dto.getEmpDay() + "/" + dto.getEmpMonth() + "/" + dto.getEmpYear());
				util.createPreparedStatement(CommonSQL.INSERT_EMP_PERSONAL_DETAILS);
				/**
				 public static final String INSERT_EMP_PERSONAL_DETAILS ="INSERT INTO IGRS_EMP_MASTER(EMP_ID, COMP_EMP_ID,FIRST_NAME,"+
		        "MIDDLE_NAME,LAST_NAME,GENDER,DATE_OF_BIRTH,DOB_IN_WORDS,GUARDIAN_NAME,MOTHER_NAME,EMP_MARITAL_STATUS,"+                      
		        "EMP_RELIGION_ID,HOME_DISTRICT,EMP_CASTE_ID,PHYSICALLY_CHALLANGED,PH_CHALLANGE_DESC,EMP_HEIGHT,NATIONALITY,EMP_IDENTITY_MARK,"+ 
		        "PHONE_NUMBER,MOBILE_NUMBER,EMAIL,PERM_ADDRESS,PERM_COUNTRY,PERM_STATE,PERM_DISTRICT,PERM_PINCODE,PRESENT_ADDRESS,"+ 
		        "PRESENT_COUNTRY,PRESENT_STATE,PRESENT_DISTRICT,PRESENT_PINCODE,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE)"+
		        "VALUES(?,?,?,?,?,?,TO_DATE(?,'DD/MON/YYYY'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,SYSDATE)" ; 
				 */
				
				params = new String[] {
					dto.getEmployeeId(), 
					dto.getReferenceId(),
					dto.getFirstName(),
					dto.getMiddleName(),
					dto.getLastName(),
					dto.getGender(),
					dto.getDateOfBirth(),
					dto.getDateOfBirthWords(),
					dto.getFatherGaurdName(),
					dto.getMotherName(),
					dto.getMaritalStatus(),
					dto.getReligion(),
					dto.getHomeDistrict(),
					dto.getCaste(),
					dto.getChkPhysically(),
					dto.getPhysicallyChallanged(),
					dto.getHeight(),
					dto.getNationality(),
					dto.getIdentificationMarks(),
					dto.getPhoneNo(),
					dto.getMobileNo(),
					dto.getEmailId(),
					dto.getPermAddress(),
					dto.getPermCountry(),
					dto.getPermState(),
					dto.getPermDistrict(),
					dto.getPermPin(),
					dto.getCurrAddress(),
					dto.getCurrCountry(),
					dto.getCurrState(),
					dto.getCurrDistrict(),
					dto.getCurrPin(),
					userID,
					userID,dto.getHomeState()
				};
				logger.debug("Inserting Employee Master Record");
				flag = util.executeUpdate(params);
				logger.debug("Employee Master Record flag = " + flag);
				
				if(flag == false) {
					return flag;
				}
				
				//dbUtil.getDBConnection();
				dbUtil.createPreparedStatement(CommonSQL.INSERT_EMP_USER_DETAILS);
				/**
				 public static final String INSERT_EMP_USER_DETAILS = "INSERT INTO IGRS_USERS_LIST " +
				"( USER_ID, USER_TYPE_ID, CREATED_BY, CREATED_DATE, UPDATE_BY, UDPATE_DATE, USER_STATUS ) " +
				"VALUES ( ?, ?, ?, SYSDATE, ?, SYSDATE, ? )";
				*/
				params = new String[] {
					dto.getEmployeeId(),
					"I",
					userID,
					userID,
					"A"
				};
				logger.debug("Inserting Employee User Record");
				flag = dbUtil.executeUpdate(params);
				logger.debug("Employee User Record flag = " + flag);
				if(flag == false) {
					return flag;
				}
				dbUtil.createPreparedStatement(CommonSQL.INSERT_EMP_TO_REG_DETAILS_TABLE);
				password =newEmployeeID;
				
				params = new String[] {
					dto.getFirstName(),
					dto.getMiddleName(),
					dto.getLastName(),
					dto.getGender(),
					dto.getDateOfBirth(),
					dto.getFatherGaurdName(),
					dto.getMotherName(),
					dto.getReligion(),
					dto.getCaste(),
					dto.getNationality(),
					dto.getPhoneNo(),
					dto.getMobileNo(),
					dto.getEmailId(),
					dto.getPermAddress(),
					dto.getPermCountry(),
					dto.getPermState(),
					dto.getPermDistrict(),
					dto.getPermPin(),
					dto.getEmployeeId(),
					crypt.SHAencryption(password),
					"P",
					userID,
					userID,
					dto.getHintQuestion(),
					dto.getUserHintAnswer()
				};
				/**
				public static final String INSERT_EMP_TO_REG_DETAILS_TABLE="INSERT INTO IGRS_USER_REG_DETAILS " +
						"( FIRST_NAME, MIDDLE_NAME, LAST_NAME, GENDER, DOB, GUARDIAN_NAME, MOTHER_NAME, RELIGION_ID, " +
						"CASTE_ID, NATIONALITY, PHONE_NUMBER, MOBILE_NUMBER, EMAIL_ID, ADDRESS, COUNTRY_ID, STATE_ID, " +
						"DISTRICT_ID, POSTAL_CODE, USER_ID, USER_PASSWORD, USER_STATUS, CREATED_BY, CREATED_DATE, " +
						"UPDATE_BY, UPDATE_DATE, USER_TXN_ID, USER_HINT_QUESTION_ID, USER_HINT_ANSWER ) " +
						"VALUES " +
						"( ?, ?, ?, ?, TO_DATE(?,'DD/MON/YYYY'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE, IGRS_USER_TXN_SEQ.NEXTVAL, ?, ? )";
				*/
				logger.debug("Inserting Employee User Registration Record");
				flag = dbUtil.executeUpdate(params);
				logger.debug("Employee User Registration Record flag = " + flag);
				if(flag == false) {
					return flag;
				}
				
				ArrayList<FamilyMemberDTO> familyMembers = dto.getFamilyMembers();
				if (familyMembers != null) {
					util.createPreparedStatement(CommonSQL.INSERT_EMP_FAMILY_DETAILS);
					/**
					 public static final String INSERT_EMP_FAMILY_DETAILS = "INSERT INTO IGRS_EMP_FAMILY_DETAILS " +
					"( EMP_FAMILY_ID, EMP_ID, RELATIVE_TYPE_ID, RELATIVE_NAME, RELATIVE_DOB, CREATED_BY, CREATED_DATE ) " +
					"VALUES ( IGRS_EMP_FAMILY_SEQ.NEXTVAL, ?, ?, ?, TO_DATE(?,'DD/MM/YYYY'), ?, SYSDATE )"; 
					 */
					for (FamilyMemberDTO member : familyMembers) {
						member.setEmployeeID(dto.getEmployeeId());
						params = new String[] {
							member.getEmployeeID(),
							member.getRelativeTypeID(),
							member.getRelativeName(),
							member.getRelativeDOB(),
							userID
						};
						logger.debug("Inserting Employee Family Record");
						flag = util.executeUpdate(params);
						logger.debug("Employee Family Record flag = " + flag);
						if(flag == false) {
							break;
						}
					}
				}
				if(flag == false) {
					return flag;
				}
				logger.debug("End of all Employee Reccords");
			} catch (Exception e) {
				flag = false;
				logger.error(e.getMessage(), e);
			} finally {
				if(flag) {
					util.commit();
					retVal = true;
				} else {
					util.rollback();
					retVal = false;
				}
				util.closeConnection();
				dbUtil.closeConnection();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}

	/**
	 * @param personDTO
	 * @param userID
	 * @return
	 */
	public boolean updateEmployeeData(PersonalDetailsDTO personDTO,
			String userID) {
		boolean retVal = false;
		boolean flag = false;
		try {
			String[] params;
			DBUtility util = new DBUtility();
			try {
				util.setAutoCommit(false);
				util.createPreparedStatement(CommonSQL.DELETE_EMP_FAMILY_DETAILS);
				/**
				 DELETE FROM IGRS_EMP_FAMILY_DETAILS WHERE EMP_ID = ?
				 */
				logger.debug("Deleting Employee Family Details User Registration");
				flag = util.executeUpdate(new String[]{personDTO.getEmployeeId()});
				logger.debug("Employee Family Details flag = " + flag);
				
				util.createPreparedStatement(CommonSQL.UPDATE_EMP_PERSONAL_DETAILS);
				/**
				 "UPDATE IGRS_EMP_MASTER SET " +
				"FIRST_NAME = ?, MIDDLE_NAME = ?, LAST_NAME = ?, DATE_OF_BIRTH = TO_DATE(?,'DD/MON/YYYY'), DOB_IN_WORDS = ?, EMP_RELIGION_ID = ?, " +
				"EMP_CASTE_ID = ?, EMP_HEIGHT = ?, EMP_IDENTITY_MARK = ?, PHYSICALLY_CHALLANGED = ?, PH_CHALLANGE_DESC = ?, " +
				"EMP_MARITAL_STATUS = ?, GUARDIAN_NAME = ?, MOTHER_NAME = ?, HOME_DISTRICT = ?, PERM_ADDRESS = ?, NATIONALITY = ?, " +
				"PERM_DISTRICT = ?, PERM_STATE = ?, PERM_COUNTRY = ?, PERM_PINCODE = ?, PRESENT_ADDRESS = ?, PRESENT_DISTRICT = ?, " +
				"PRESENT_STATE = ?, PRESENT_COUNTRY = ?, PRESENT_PINCODE = ?, UPDATE_BY = ?, UPDATE_DATE = SYSDATE, PHONE_NUMBER = ?, " +
				"MOBILE_NUMBER = ?, EMAIL = ?, COMP_EMP_ID = ?, GENDER = ? WHERE EMP_ID = ? ";
				 */
				personDTO.setDateOfBirth(personDTO.getEmpDay() + "/" + personDTO.getEmpMonth() + "/" + personDTO.getEmpYear());
				params  = new String[] {
					personDTO.getFirstName(),
					personDTO.getMiddleName(),
					personDTO.getLastName(),
					personDTO.getDateOfBirth(),
					personDTO.getDateOfBirthWords(),
					personDTO.getReligion(),
					personDTO.getCaste(),
					personDTO.getHeight(),
					personDTO.getIdentificationMarks(),
					personDTO.getChkPhysically(),
					personDTO.getPhysicallyChallanged(),
					personDTO.getMaritalStatus(),
					personDTO.getFatherGaurdName(),
					personDTO.getMotherName(),
					personDTO.getHomeDistrict(),
					personDTO.getPermAddress(),
					personDTO.getNationality(),
					personDTO.getPermDistrict(),
					personDTO.getPermState(),
					personDTO.getPermCountry(),
					personDTO.getPermPin(),
					personDTO.getCurrAddress(),
					personDTO.getCurrDistrict(),
					personDTO.getCurrState(),
					personDTO.getCurrCountry(),
					personDTO.getCurrPin(),
					userID,
					personDTO.getPhoneNo(),
					personDTO.getMobileNo(),
					personDTO.getEmailId(),
					personDTO.getReferenceId(),
					personDTO.getGender(),
					personDTO.getHomeState(),
					personDTO.getEmployeeId()
				};
				logger.debug("Updating Employee Master Record");
				flag = util.executeUpdate(params);
				logger.debug("Employee Master Record flag = " + flag);
				if(flag == false) {
					return flag;
				}
				util.createPreparedStatement(CommonSQL.UPDATE_EMP_REGISTRATION_DETAILS);
				/**
				 UPDATE IGRS_USER_REG_DETAILS SET " +
				"FIRST_NAME = ?, MIDDLE_NAME = ?, LAST_NAME = ?, GENDER = ?, DOB = TO_DATE(?,'DD/MON/YYYY'), NATIONALITY = ?, COUNTRY_ID = ?, " +
				"STATE_ID = ?, DISTRICT_ID = ?, ADDRESS = ?, POSTAL_CODE = ?, PHONE_NUMBER = ?, MOBILE_NUMBER = ?, EMAIL_ID = ?, " +
				"GUARDIAN_NAME = ?, MOTHER_NAME = ?, CASTE_ID = ?, RELIGION_ID = ?, UPDATE_BY = ?, UPDATE_DATE = SYSDATE " +
				"WHERE USER_ID = ?
				 */
				params  = new String[] {
					personDTO.getFirstName(),
					personDTO.getMiddleName(),
					personDTO.getLastName(),
					personDTO.getGender(),
					personDTO.getDateOfBirth(),
					personDTO.getNationality(),
					personDTO.getPermCountry(),
					personDTO.getPermState(),
					personDTO.getPermDistrict(),
					personDTO.getPermAddress(),
					personDTO.getPermPin(),
					personDTO.getPhoneNo(),
					personDTO.getMobileNo(),
					personDTO.getEmailId(),
					personDTO.getFatherGaurdName(),
					personDTO.getMotherName(),
					personDTO.getCaste(),
					personDTO.getReligion(),
					userID,
					personDTO.getEmployeeId(),
				};
				logger.debug("Updating Employee User Registration");
				flag = util.executeUpdate(params);
				logger.debug("Employee User Registration flag = " + flag);
				if(flag == false) {
					return flag;
				}
				
				ArrayList<FamilyMemberDTO> familyMembers = personDTO.getFamilyMembers();
				if (familyMembers != null) {
					util.createPreparedStatement(CommonSQL.INSERT_EMP_FAMILY_DETAILS);
					/**
					 public static final String INSERT_EMP_FAMILY_DETAILS = "INSERT INTO IGRS_EMP_FAMILY_DETAILS " +
					"( EMP_FAMILY_ID, EMP_ID, RELATIVE_TYPE_ID, RELATIVE_NAME, RELATIVE_DOB, CREATED_BY, CREATED_DATE ) " +
					"VALUES ( IGRS_EMP_FAMILY_SEQ.NEXTVAL, ?, ?, ?, TO_DATE(?,'DD/MM/YYYY'), ?, SYSDATE )"; 
					 */
					for (FamilyMemberDTO member : familyMembers) {
						member.setEmployeeID(personDTO.getEmployeeId());
						params = new String[] {
							member.getEmployeeID(),
							member.getRelativeTypeID(),
							member.getRelativeName(),
							member.getRelativeDOB(),
							userID
						};
						logger.debug("Inserting Employee Family Record");
						flag = util.executeUpdate(params);
						logger.debug("Employee Family Record flag = " + flag);
						if(flag == false) {
							break;
						}
					}
				}
				if(flag == false) {
					return flag;
				}
				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				if(flag) {
					util.commit();
					retVal = true;
				} else {
					util.rollback();
					retVal = false;
				}
				util.closeConnection();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}

	
}
