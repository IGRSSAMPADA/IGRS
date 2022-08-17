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
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.driver.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.empmgmt.dto.BankDTO;
import com.wipro.igrs.empmgmt.dto.FamilyMemberDTO;
import com.wipro.igrs.empmgmt.dto.FundDTO;
import com.wipro.igrs.empmgmt.dto.NomineeDTO;
import com.wipro.igrs.empmgmt.sql.CommonSQL;

/**
* 
* EmpmgmtViewDAO.java <br>
* EmpmgmtViewDAO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class EmpmgmtViewDAO {

	private DBUtility dbUtil;
	private Logger logger = (Logger) Logger.getLogger(EmpmgmtViewDAO.class);
	/**
	 * @param empId
	 * @return
	 */
	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getEmployeeID(String empId) {
		ArrayList list = null;
		try{
			String sqlValues[] =new String[]{ empId};
			String sqlQuery = CommonSQL.CHECK_EMPID;
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(sqlQuery);
				list = dbUtil.executeQuery(sqlValues);

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
	 * @param empId
	 * @return
	 */
	public ArrayList getPersonalDetails(String empId) {
		ArrayList list = null;
		String sqlValues[] = new String[1];
		sqlValues[0] = empId;
		String sqlQuery = CommonSQL.RETRIEVE_PERSONAL_DETAILS;
		try {

			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			list = dbUtil.executeQuery(sqlValues);

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
	 * @param employeeId
	 * @return
	 */
	public ArrayList getChildDetails(String employeeId) {
		ArrayList list = null;
		String sqlValues[] = new String[1];
		sqlValues[0] = employeeId;
		String sqlQuery = CommonSQL.RETRIEVE_CHILD_DETAILS;
		try {

			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			list = dbUtil.executeQuery(sqlValues);

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
	 * @param empId
	 * @return
	 */
	public ArrayList getPropertyDetails(String empId) {
		
		ArrayList list = null;
		String sqlValues[] = new String[1];
		sqlValues[0] = empId;
	    String sqlQuery = CommonSQL.RETRIEVE_PROPERTY_DETAILS;
		try {
			
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			list = dbUtil.executeQuery(sqlValues);
		}
		 catch (Exception e) {
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
	 * @param empId
	 * @return
	 */
	public ArrayList getAssetDetails(String empId) {
		ArrayList list = null;
		String sqlValues[] = new String[1];
		sqlValues[0] = empId;
		String sqlQuery = CommonSQL.RETRIEVE_ASSET_DETAILS;
     try {
			
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			list = dbUtil.executeQuery(sqlValues);
		}
		 catch (Exception e) {
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
	 * @param empId
	 * @return
	 */
	public ArrayList getAcademicDetails(String empId) {
		ArrayList list = null;
		String sqlValues[] = new String[1];
		sqlValues[0] = empId;
		String sqlQuery = CommonSQL.RETRIEVE_ACADEMIC_DETAILS;
		  try {
				
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(sqlQuery);
				list = dbUtil.executeQuery(sqlValues);
				
			}
			 catch (Exception e) {
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
	 * @param empId
	 * @return
	 */
	public ArrayList getPreviousEmploymentDetails(String empId) {
		ArrayList list = null;
		String sqlValues[] = new String[1];
		sqlValues[0] = empId;
		String sqlQuery = CommonSQL.GET_EMP_PRV_LIST;
		  try {
				
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(sqlQuery);
				list = dbUtil.executeQuery(sqlValues);
				
			}
			 catch (Exception e) {
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
	 * @param empId
	 * @return
	 */
	public ArrayList getTraingDetails(String empId) {
		ArrayList list = null;
		String sqlValues[] = new String[1];
		sqlValues[0] = empId;
		String sqlQuery = CommonSQL.RETRIEVE_TRAINING_DETAILS;
		 try {
				
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(sqlQuery);
				list = dbUtil.executeQuery(sqlValues);
			
			}
			 catch (Exception e) {
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
	 * @param empId
	 * @return
	 */
	public ArrayList getExamDetails(String empId) {
		ArrayList list = null;
		String sqlValues[] = new String[1];
		sqlValues[0] = empId;
		String sqlQuery = CommonSQL.RETRIEVE_EXAM_DETAILS;
		 try {
				
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(sqlQuery);
				list = dbUtil.executeQuery(sqlValues);
			
			}
			 catch (Exception e) {
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
	 * @param empId
	 * @return
	 */
	public ArrayList getBankDetails(String empId) {
		ArrayList list = null;
		String sqlValues[] = new String[1];
		sqlValues[0] = empId;
		String sqlQuery = CommonSQL.RETRIEVE_BANK_DETAILS;
		 try {
				
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(sqlQuery);
				list = dbUtil.executeQuery(sqlValues);
				
			}
			 catch (Exception e) {
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
	 * @throws Exception
	 */
	public ArrayList get_Empid() throws Exception {
		ArrayList list = null;
		try {

			dbUtil.createStatement();
			String sql = CommonSQL.SELECT_EMPID;
			list = dbUtil.executeQuery(sql);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}
	
	
	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getFundDetails(String empId) {
		ArrayList list = null;
		String sqlValues[] = new String[1];
		sqlValues[0] = empId;
		String sqlQuery = CommonSQL.RETRIEVE_FUND_DETAILS;
		 try {
				
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(sqlQuery);
				list = dbUtil.executeQuery(sqlValues);
			
			}
			 catch (Exception e) {
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
	 * @param empId
	 * @return
	 */
	public ArrayList getNomineeDetails(String empId) {
		ArrayList list = null;
		String sqlValues[] = new String[1];
		sqlValues[0] = empId;
		
		String sqlQuery = CommonSQL.RETRIEVE_NOMINEE_DETAILS;
		 try {
				
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(sqlQuery);
				list = dbUtil.executeQuery(sqlValues);
			
			}
			 catch (Exception e) {
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
	 * @param empId
	 * @return
	 */
	public ArrayList getOfficialDetails(String empId) {
		ArrayList list = null;
		String sqlValues[] = new String[1];
		sqlValues[0] = empId;
		
		String sqlQuery = CommonSQL.RETRIEVE_OFFICE_DETAILS;
		 try {
				
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(sqlQuery);
				list = dbUtil.executeQuery(sqlValues);
			
			}
			 catch (Exception e) {
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
	 * @param empId
	 * @return
	 */
	public ArrayList getReportingHierarchyDetails(String empId){
		
		ArrayList list = null;
		String sqlValues[] = new String[1];
		sqlValues[0] = empId;
		String sqlQuery = CommonSQL.RETRIEVE_REPORTING_DETAILS;
		 try {
				
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(sqlQuery);
				list = dbUtil.executeQuery(sqlValues);
			
			}
			 catch (Exception e) {
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
	 * @param empId
	 * @return
	 */
	public ArrayList getServiceDetails(String empId) {
		ArrayList list = null;
		String sqlValues[] = new String[1];
		sqlValues[0] = empId;
		
		String sqlQuery = CommonSQL.RETRIEVE_SERVICE_DETAILS;
		 try {
				
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(sqlQuery);
				list = dbUtil.executeQuery(sqlValues);
			
			}
			 catch (Exception e) {
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
			}
			 catch (Exception e) {
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
	 * @param docID
	 * @param res
	 * @param fileName
	 */
	public void downloadEmpDocument(String docID, HttpServletResponse res, String fileName) {
		try {
			dbUtil = new DBUtility();
			byte[] content = dbUtil.getByteArrayForBLOB(CommonSQL.RETRIEVE_EMP_DOC_OBJECT, docID);
			dbUtil.downloadByteArrayAsFile(res, content , fileName);
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
	}


	/**
	 * @param empId
	 * @return
	 */
	public BankDTO getEmpBankDetails(String empId) {
		BankDTO retVal = null;
		try {
			dbUtil = new DBUtility();
			try {
				
				
				 
				ResultSet rset;
				ArrayList dataSet, fundList, subNomineeList, nomineeList;
				String errorMsg;
				FundDTO fundDTO;
				NomineeDTO nomineeDTO;
				
				//commented by shruti---9 sep 2014
				//TODO: Incorrect objref casting. This should be OracleCallableStatement. Commenting line below
				
				OracleCallableStatement oclstmt=  (OracleCallableStatement)dbUtil.returnCallableStatement("CALL IGRS_EMP_BANK_DTLS(?,?,?,?,?)");

				//				CallableStatement oclstmt = (CallableStatement) dbUtil
				//			.returnCallableStatement("CALL IGRS_EMP_BANK_DTLS(?,?,?,?,?)");
				
				//end
				
				
				//			1 P_EMP_ID IN VARCHAR2 ,
				//		    2 P_BANK_DATA OUT TYPES.REF_CURSOR ,
				//		    3 P_FUND_DATA OUT TYPES.REF_CURSOR ,
				//		    4 P_NOMINEE_DATA OUT TYPES.REF_CURSOR ,
				//		    5 P_ERR_MSG OUT VARCHAR2
				oclstmt.setString(1, empId);
				oclstmt.registerOutParameter(2, OracleTypes.CURSOR);
				oclstmt.registerOutParameter(3, OracleTypes.CURSOR);
				oclstmt.registerOutParameter(4, OracleTypes.CURSOR);
				oclstmt.registerOutParameter(5, OracleTypes.VARCHAR);
				oclstmt.execute();
				errorMsg = oclstmt.getString(5);
				if ("".equals(errorMsg) || errorMsg == null) {
					retVal = new BankDTO();
					//Bank Data
					rset = oclstmt.getCursor(2);
					while (rset.next()) {
						retVal.setBankName(rset.getString(1));
						retVal.setBankBranch(rset.getString(2));
						retVal.setBankAddress(rset.getString(3));
						retVal.setNameAsInBank(rset.getString(4));
						retVal.setPanNo(rset.getString(5));
						retVal.setBankAccountNo(rset.getString(6));
						retVal.setBankIFSC(rset.getString(7));
					}
					rset.close();
					//Fund Data
					rset = oclstmt.getCursor(3);
					dataSet = new ArrayList();
					while (rset.next()) {
						fundDTO = new FundDTO();
						fundDTO.setFundName(rset.getString(1));
						fundDTO.setAccountNo(rset.getString(2));
						fundDTO.setAccountLocation(rset.getString(3));
						fundDTO.setType(rset.getString(4));
						dataSet.add(fundDTO);
					}
					retVal.setFundList(dataSet);
					fundList = dataSet;
					rset.close();
					rset = oclstmt.getCursor(4);
					dataSet = new ArrayList();
					while (rset.next()) {
						nomineeDTO = new NomineeDTO();
						nomineeDTO.setFundName(rset.getString(1));
						nomineeDTO.setNomineeName(rset.getString(2));
						nomineeDTO.setRelationWithNominee(rset.getString(3));
						nomineeDTO.setNomineeAddress(rset.getString(4));
						nomineeDTO.setNomineeAge(rset.getString(5));
						nomineeDTO.setStrAccountNumber(rset.getString(6));
						nomineeDTO.setFundTypeId(rset.getString(7));
						nomineeDTO.setNomineePercentage(rset.getString(8));
						dataSet.add(nomineeDTO);
					}
					nomineeList = dataSet;
					for (int iLoop = 0; iLoop < fundList.size(); iLoop++) {
						fundDTO = (FundDTO) fundList.get(iLoop);
						subNomineeList = getNomineeSubList(
								fundDTO.getAccountNo(), nomineeList);
						fundDTO.setNomineeList(subNomineeList);
					}
					oclstmt.close();
					nomineeList.clear();
					dataSet.clear();
					retVal.setBankNameLabel(getBankLabel(retVal.getBankName()));
				} else {
					logger.error("Error while calling stored procedure IGRS_EMP_BANK_DTLS : "
							+ errorMsg);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			finally {
				dbUtil.closeConnection();
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}


	/**
	 * @param accountNo
	 * @param nomineeList
	 * @return
	 */
	private ArrayList getNomineeSubList(String accountNo, ArrayList nomineeList) {
		ArrayList subNomineeList = new ArrayList();
		ArrayList filterNomineeList = new ArrayList();
		for (int iLoop = 0; iLoop < nomineeList.size(); iLoop++) {
			NomineeDTO nomineeDTO = (NomineeDTO) nomineeList.get(iLoop);
			if(accountNo.equalsIgnoreCase(nomineeDTO.getStrAccountNumber())) {
				subNomineeList.add(nomineeDTO);
			}else {
				filterNomineeList.add(nomineeDTO);
			}
		}
		nomineeList.clear();
		nomineeList.addAll(filterNomineeList);
		return subNomineeList;
	}


	/**
	 * @param homeDistrict
	 * @return
	 */
	public String getDistrictName(String homeDistrict,String locale) {
		String retVal = "";
		String sqlQuery="";
		try {
			dbUtil = new DBUtility();
			if(locale.equalsIgnoreCase("hi_IN")){
			 sqlQuery = CommonSQL.GET_DISTRICT_LABEL_HINDI;
			}else{
			sqlQuery = CommonSQL.GET_DISTRICT_LABEL;	
			}

			
			 try {
					
					dbUtil = new DBUtility();
					dbUtil.createPreparedStatement(sqlQuery);
					retVal = dbUtil.executeQry(new String[]{homeDistrict});
					
			} catch (Exception e) {
			}
			 finally {
				 dbUtil.closeConnection();
			 }
		} catch (Exception e) {
		}
		return retVal;
	}
	/**
	 * @param homeDistrict
	 * @return
	 */
	public String getHomeStateName(String homeState,String locale) {
		String retVal = "";
		String sqlQuery="";
		try {
			dbUtil = new DBUtility();
			if(locale.equalsIgnoreCase("hi_IN")){
			 sqlQuery = CommonSQL.GET_HOME_STATE_LABEL_HINDI;
			}else{
			sqlQuery = CommonSQL.GET_HOME_STATE_LABEL;	
			}

			
			 try {
					
					dbUtil = new DBUtility();
					dbUtil.createPreparedStatement(sqlQuery);
					retVal = dbUtil.executeQry(new String[]{homeState});
					
			} catch (Exception e) {
				e.printStackTrace();
			}
			 finally {
				 dbUtil.closeConnection();
			 }
		} catch (Exception e) {
		}
		return retVal;
	}


	/**
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public ArrayList searchEmployees(String firstName, String lastName) {
		ArrayList retVal = null;
		try {
			dbUtil = new DBUtility();
			firstName = firstName == null ? "" : firstName.trim();
			lastName = lastName == null ? "" : lastName.trim();
			StringBuilder strBldr = new StringBuilder();
			ArrayList<String> paramList = new ArrayList<String>();
			String sqlQuery = "";
			try {
				strBldr.append(CommonSQL.SEARCH_EMP_BASE_QUERY);
				if ("".equals(firstName) != true) {
					paramList.add("%" + firstName + "%");
					strBldr.append(CommonSQL.SEARCH_EMP_FIRSTNAME_CLAUSE);
				}
				if ("".equals(lastName) != true) {
					paramList.add("%" + lastName + "%");
					strBldr.append(CommonSQL.SEARCH_EMP_LASTNAME_CLAUSE);
				}
				sqlQuery = strBldr.toString();
				dbUtil.createPreparedStatement(sqlQuery);
				String[] params = paramList.toArray(new String[] {});
				retVal = dbUtil.executeQuery(params);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				dbUtil.closeConnection();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}
	
	/**
	 * @param bankID
	 * @return
	 */
	private String getBankLabel(String bankID) {
		String retVal = "";
		try {
			DBUtility util = new DBUtility();
			try {
				util.createPreparedStatement(CommonSQL.GET_BANKLABEL);
				retVal = util.executeQry(new String[]{bankID});
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				dbUtil.closeConnection();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}


	/**
	 * @param employeeId
	 * @return
	 */
	public ArrayList<FamilyMemberDTO> getFamilyMemberDetails(String employeeId,String locale) {
		ArrayList<FamilyMemberDTO> retVal = new ArrayList<FamilyMemberDTO>();
		ArrayList data, row;
		FamilyMemberDTO member;
		try {
			DBUtility util = new DBUtility();
			try {
				//EMP_FAMILY_ID, EMP_ID, RELATIVE_TYPE_ID, RELATIVE_NAME, RELATIVE_DOB, RELATIVE_TYPE_NAME, RELATIVE_DAY, RELATIVE_MONTH, RELATIVE_YEAR
				util.createPreparedStatement(CommonSQL.GET_FAMILYDETAILS);
				data = util.executeQuery(new String[]{employeeId});
				if(data != null) {
					for (Object item : data) {
						row = (ArrayList) item;
						member = new FamilyMemberDTO();
						member.setRelativeRowID((String) row.get(0));
						member.setEmployeeID((String) row.get(1));
						member.setRelativeTypeID((String) row.get(2));
						member.setRelativeName((String) row.get(3));
						member.setRelativeDOB((String) row.get(4));
						member.setRelativeMonth((String) row.get(7));
						if(locale.equalsIgnoreCase("hi_IN")){
							member.setRelativeTypeLabel((String) row.get(9));
							
							
							
						}else{
							member.setRelativeTypeLabel((String) row.get(5));
							
							
						}
						if(member.getRelativeMonth().equalsIgnoreCase("1")){
							member.setRelativeMonth("Jan");
							}
						if(member.getRelativeMonth().equalsIgnoreCase("2")){
							member.setRelativeMonth("Feb");
							}
						if(member.getRelativeMonth().equalsIgnoreCase("3")){
							member.setRelativeMonth("Mar");
							}
						
						if(member.getRelativeMonth().equalsIgnoreCase("4")){
							member.setRelativeMonth("Apr");
							}
						if(member.getRelativeMonth().equalsIgnoreCase("5")){
							member.setRelativeMonth("May");
							}
						if(member.getRelativeMonth().equalsIgnoreCase("6")){
							member.setRelativeMonth("Jun");
							}
						if(member.getRelativeMonth().equalsIgnoreCase("7")){
							member.setRelativeMonth("Jul");
							}
						if(member.getRelativeMonth().equalsIgnoreCase("8")){
							member.setRelativeMonth("Aug");
							}
						if(member.getRelativeMonth().equalsIgnoreCase("9")){
							member.setRelativeMonth("Sep");
							}
						if(member.getRelativeMonth().equalsIgnoreCase("10")){
							member.setRelativeMonth("Oct");
							}
						if(member.getRelativeMonth().equalsIgnoreCase("11")){
							member.setRelativeMonth("Nov");
							}
						if(member.getRelativeMonth().equalsIgnoreCase("12")){
							member.setRelativeMonth("Dec");
							}
						member.setRelativeDay((String) row.get(6));
						
						member.setRelativeYear((String) row.get(8));
						retVal.add(member);
						row.clear();
					}
					data.clear();
				}
				retVal.trimToSize();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				dbUtil.closeConnection();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}
}
	


