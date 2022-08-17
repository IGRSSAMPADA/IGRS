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

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.empmgmt.dto.EmpmgmtUploadDTO;
import com.wipro.igrs.empmgmt.dto.OfficalInfoDTO;
import com.wipro.igrs.empmgmt.dto.ServiceVerificationDTO;
import com.wipro.igrs.empmgmt.sql.CommonSQL;
import com.wipro.igrs.util.CommonUtil;

/**
* 
* OfficalInfoDAO.java <br>
* OfficalInfoDAO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class OfficalInfoDAO {
	DBUtility dbUtil;
	private Logger logger = (Logger) Logger.getLogger(OfficalInfoDAO.class);

	/**
	 * @throws Exception
	 */
	public OfficalInfoDAO() throws Exception {
		//dbUtil = new DBUtility();
	}

	/**
	 * @param servicelist
	 * @param strUserId
	 * @param strempId
	 * @return
	 * @throws Exception
	 */
	public boolean submitService(ArrayList servicelist, String strUserId,
			String strempId) throws Exception {
		boolean flag = false;

		return flag;
	}

	/**
	 * @param officalInfoDTO
	 * @param servicelist
	 * @param strUserId
	 * @param strempId
	 * @param listFileNames
	 * @param strFilePath
	 * @param docType
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public boolean insertOfficalInfo(OfficalInfoDTO officalInfoDTO,
			ArrayList servicelist, String strUserId, String strempId,
			ArrayList listFileNames, String strFilePath, String docType,
			String userid,String locale) throws Exception {
		
		boolean flag = false;
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		boolean flagFileUpload = false;
		String delQuery; 
		try {
			
			
			//dbUtil.setAutoCommit(false);
			String sqlValues[] = {
					strempId, // EMP_ID
					officalInfoDTO.getDesignation(), // EMP_DESIGNATION_ID
					officalInfoDTO.getClass1(), // EMP_CLASS_ID
					officalInfoDTO.getSubstantive(), // OFFICIATING
					officalInfoDTO.getEmployeeStatus(), // EMP_STATUS
					officalInfoDTO.getSupervisorName(), // SUPERVISOR_ID
					CommonUtil.getConvertedDate(officalInfoDTO
							.getDateOfJoining()), // DATE_OF_JOINING
					CommonUtil.getConvertedDate(officalInfoDTO
							.getDateOfSaperation()), // DATE_OF_SEPARATION
					officalInfoDTO.getCompEmplRefrence(), // COMP_EMP_NO
					"A", // EMP_OFFICIAL_STATUS
					strUserId, // CREATED_BY
					// "", //CREATED_DATE
					strUserId, // UPDATE_BY
					// UPDATE_DATE
					officalInfoDTO.getDesiOffic(), // OFFICATING_ID
					CommonUtil.getConvertedDate(officalInfoDTO
							.getDateFirstGovtService()) // DATE_JOIN_GOVT_SERV
					
			};
			//Delete existing record
		
			delQuery = CommonSQL.DELETE_EMP_OFFICE_DTLS;
			delExisting(strempId, delQuery);
			
			delQuery = CommonSQL.DELETE_EMP_GRADE_CADRE_MAP;
			delExisting(strempId, delQuery);
			
			delQuery = CommonSQL.DELETE_EMP_SERVICE;
			delExisting(strempId, delQuery);
			
			delQuery = CommonSQL.DELETE_EMP_DOCUMENT;
			delExisting(strempId, delQuery);
			
			//Delete existing record
			 dbUtil=new DBUtility();
			String sqlQuery = CommonSQL.INSERT_OFFICAL_DETAILS;

			dbUtil.createPreparedStatement(sqlQuery);

			flag1 = dbUtil.executeUpdate(sqlValues);

			String values[] = { strempId, // EMP_ID
					officalInfoDTO.getClass1(), // GRADE_ID
					officalInfoDTO.getDesignation(), // CADRE_ID
					"A" // STATUS

			};
			String sqlQueryString = CommonSQL.INSERT_OFFICAL_MAPPING;

			dbUtil.createPreparedStatement(sqlQueryString);
			flag2 = dbUtil.executeUpdate(values);

			for (int i = 0; i < servicelist.size(); i++) {
				ServiceVerificationDTO serviceVerificationDTO = (ServiceVerificationDTO) servicelist
						.get(i);
				String sqlValues1[] = {

						strempId, // EMP_ID,
						serviceVerificationDTO.getVerifyingAuthority(), // VERIFYING_AUTH_NAME,
						CommonUtil.getConvertedDate(serviceVerificationDTO
								.getDateOfVerivication()), // VERIFICATION_DATE,
						serviceVerificationDTO.getComments(), // COMMENTS
						"A", // VERIFICATION_STATUS,
						strUserId, // CREATED_BY
						// CREATED_DATE
						strUserId, // UPDATE_BY
				// UPDATE_DATE

				};
				String sqlquery1 = CommonSQL.INSERT_SERVICE;
				dbUtil.createPreparedStatement(sqlquery1);
				flag3 = dbUtil.executeUpdate(sqlValues1);
			}
//
//			dbUtil.setAutoCommit(false);
//
			for (int i = 0; i < listFileNames.size(); i++) {
				EmpmgmtUploadDTO empUpload = (EmpmgmtUploadDTO) listFileNames
						.get(i);
				String fileName = empUpload.getFileName();
				logger.debug("File Name: "+ fileName);
				String doctype = empUpload.getDocumenttype();
				doctype = getCorrectedDocType(doctype,locale);
				logger.debug("doctype  " + doctype + " fileName "
						+ fileName + "  strFilePath  " + strFilePath);
				//send the byte array somehow
//				flagFileUpload = dbUtil.attachOfficalInfo(userid, doctype,
//						fileName, strFilePath, strUserId);
				flagFileUpload = dbUtil.attachEmpOfficialDocs(userid, doctype, fileName, empUpload.getFileContents(), strUserId);
				logger.debug("flagFileUpload: "+ flagFileUpload);
			}
			if(flagFileUpload != true) {
				flagFileUpload = true;
			}
			if(flag3 != true) {
				flag3 = true;
			}
			if (flag1 && flag2 && flag3 && flagFileUpload) {
				dbUtil.commit();
				flag = true;
			} else {

				dbUtil.rollback();
				flag = false;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}finally {			
			dbUtil.closeConnection();
		} 	
		return flag;
	}

	/**
	 * @param doctype
	 * @return
	 */
	private String getCorrectedDocType(String doctype,String locale) {
		String retVal = doctype;
		
		try {
			DBUtility util = new DBUtility();
			try {
				if(locale.equalsIgnoreCase("hi_IN")){
					util.createPreparedStatement(CommonSQL.GET_DOC_TYPE_ID_HINDI);

				}else{
					util.createPreparedStatement(CommonSQL.GET_DOC_TYPE_ID);

				}
				String data = util.executeQry(new String[]{doctype});
				if("".equals(data) != true) {
					retVal = data;
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			finally {
				util.closeConnection();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}

	/**
	 * @param strempId
	 * @param delQuery
	 * @throws Exception
	 */
	private void delExisting(String strempId, String delQuery) throws Exception {
		try{
		dbUtil=new DBUtility();
		dbUtil.createPreparedStatement(delQuery);
		dbUtil.executeUpdate(new String[]{strempId});
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		finally {
			dbUtil.closeConnection();
		}
		
	}

	/**
	 * @param officalInfoDTO
	 * @param strUserId
	 * @param strempId
	 * @return
	 * @throws Exception
	 */
	public boolean insertMapping(OfficalInfoDTO officalInfoDTO,
			String strUserId, String strempId) throws Exception {
		boolean flag = false;

		return flag;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAllgradeList() throws Exception {
		ArrayList list = null;
		dbUtil=new DBUtility();
		try {
		//	dbUtil.getDBConnection();
			dbUtil.createStatement();
			String query = CommonSQL.SELECT_ALL_GRADE;
			list = dbUtil.executeQuery(query);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			dbUtil.closeConnection();
		} 	
		return list;

	}

	/**
	 * @param gradeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getCadresForGrade(String gradeId) throws Exception {
		String sqlvalues[] = { gradeId };
		dbUtil=new DBUtility();
		ArrayList list=null;
		try{
	//	dbUtil.getDBConnection();
		dbUtil
				.createPreparedStatement(CommonSQL.SELECT_DESIGNATION_CADRE_GRADE);
		list = dbUtil.executeQuery(sqlvalues);
		} catch (Exception e) {
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
	public ArrayList getAllCadres() throws Exception {
		ArrayList list=null;
		dbUtil=new DBUtility();
		try{
		//	dbUtil.getDBConnection();
		dbUtil.createStatement();
		list = dbUtil.executeQuery(CommonSQL.SELECT_ALL_CADRE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}


	/**
	 * @param empid
	 * @return
	 * @throws Exception
	 */
	public ArrayList getReportingHirachy(String empid) throws Exception {
		ArrayList list=null;
		dbUtil=new DBUtility();
		try{
		//	dbUtil.getDBConnection();
		String sqlvalues[] = { empid };
		dbUtil.createPreparedStatement(CommonSQL.SELECT_REPORTING_HIRACHY);
	list = dbUtil.executeQuery(sqlvalues);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
	} finally {
		dbUtil.closeConnection();
	}

		return list;
	}

	

	
	/**
	 * @param empid
	 * @return
	 * @throws Exception
	 */
	public ArrayList getRepoteeList(String empid) throws Exception {
		dbUtil=new DBUtility();
		String sqlvalues[] = { empid };
		ArrayList list =null;
try{	//dbUtil.getDBConnection();
		dbUtil.createPreparedStatement(CommonSQL.SELECT_DESIGNATION_NAME);
		list = dbUtil.executeQuery(sqlvalues);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
	} finally {
		dbUtil.closeConnection();
	}
		return list;
	}

	/**
	 * @param GRADE_ID
	 * @return
	 * @throws Exception
	 */
	public ArrayList getGradeCadreList(String GRADE_ID) throws Exception {
		dbUtil=new DBUtility();
		String sqlvalues[] = { GRADE_ID };
		ArrayList list =null;
try{	dbUtil.getDBConnection();
		dbUtil
				.createPreparedStatement(CommonSQL.SELECT_DESIGNATION_CADRE_GRADE);
		list = dbUtil.executeQuery(sqlvalues);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
	} finally {
		dbUtil.closeConnection();
	}
		return list;
	}

	/**
	 * @param DESIGNATION_ID
	 * @return
	 * @throws Exception
	 */
	public ArrayList getReportingHirachyDesignation(String DESIGNATION_ID)
			throws Exception {
		dbUtil=new DBUtility();
		String sqlvalues[] = { DESIGNATION_ID };
		ArrayList list =null;
		try{	//dbUtil.getDBConnection();
		dbUtil
				.createPreparedStatement(CommonSQL.SELECT_REPORTING_HIRACHY_DESIGNATION);
		list = dbUtil.executeQuery(sqlvalues);
			} catch (Exception e) {
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
	public ArrayList getAllDocumentList() throws Exception {
		dbUtil=new DBUtility();
		dbUtil.createStatement();
		ArrayList list =null;
		try{	//dbUtil.getDBConnection();
		list = dbUtil.executeQuery(CommonSQL.SELECT_ALL_DOCUMENTLIST);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
	} finally {
		dbUtil.closeConnection();
	}
		return list;
	}

	/**
	 * @param officating
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAllOfficating(String officating) throws Exception {
		String sqlvalues[] = { officating };
		ArrayList list =null;
		dbUtil=new DBUtility();
		try{	//dbUtil.getDBConnection();
		dbUtil.createPreparedStatement(CommonSQL.SELECT_DESIGNATION_OFFICIATING);

	list = dbUtil.executeQuery(sqlvalues);
		if (list.isEmpty()) {
			// Fall Back
			dbUtil.createPreparedStatement(CommonSQL.SELECT_DESIGNATION_OFFICIATING_FALLBACK);

			list = dbUtil.executeQuery(sqlvalues);
		}} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	
	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getDocumentDetails(String empId) {
		ArrayList list = null;
		String sqlValues[] = new String[1];
		sqlValues[0] = empId;
		

		String sqlQuery = CommonSQL.RETRIEVE_DOCUMENT_DETAILS;
		
		try {

			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			list = dbUtil.executeQuery(sqlValues);
			String docID;
			sqlQuery = CommonSQL.RETRIEVE_EMP_DOC_OBJECT;
			for (Object rowObj : list) {
				ArrayList row = (ArrayList) rowObj;
				docID = row.get(0).toString();
				byte[] content = dbUtil.getByteArrayForBLOB(sqlQuery, docID);
				if(content != null) {
					row.add(content);
				}
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
		return list;

	}

	
	/**
	 * @return
	 */
	public ArrayList getOfficatingSubstantingList() {
		ArrayList data = new ArrayList();
		DBUtility util=null;
		try {util=new DBUtility();
			String query = CommonSQL.GET_OFFC_SUBS_LIST;
			
			util.createStatement();
			data  = util.executeQuery(query);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}finally {
			try {
				util.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}

	/**
	 * @param attachedDocLabels
	 * @return
	 */
	public ArrayList getMissingDocLabels(ArrayList<String> attachedDocLabels) {
		ArrayList data = new ArrayList();
		DBUtility util=null;
		
		try {util=new DBUtility();
			StringBuilder strBldr = new StringBuilder();
			strBldr.append("SELECT DOCUMENT_TYPE FROM IGRS_DOCUMENT_MASTER WHERE DOCUMENT_TYPE NOT IN ");
			String temp = attachedDocLabels.toString();
			temp = temp.replaceAll(", ", "', '");
			temp = temp.replaceAll("\\[", "('");
			temp = temp.replaceAll("\\]", "')");
			strBldr.append(temp);
			
			util.createStatement();
			data  = util.executeQuery(strBldr.toString());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}finally {
			try {
				util.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}
}