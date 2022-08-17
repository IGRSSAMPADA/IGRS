/*
 * DepartmentalEnquiryDAO.java
 */


package com.wipro.igrs.departmentalenquiry.dao;


import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.departmentalenquiry.sql.DepartmentalEnquirySQL;


/**
 * @author oneapps
 * 
 */
public class DepartmentalEnquiryDAO {

	public DepartmentalEnquiryDAO() {

	}

	public ArrayList getUserDetails(String userId) {
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil
			        .createPreparedStatement(DepartmentalEnquirySQL.REG_USER_DETAILS);
			// System.out.println("Wipro in - getUserDetails() after create
			// statement");
			list = dbUtil.executeQueryLikeSearch(userId);
			// System.out.println("Wipro in - getUserDetails() after execute
			// query");
			// System.out.println("List Size :" + list.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getEmpDetails(String empId) {
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(DepartmentalEnquirySQL.EMP_DETAILS);
			// System.out.println("Wipro in - getUserDetails() after create
			// statement");
			list = dbUtil.executeQueryLikeSearch(empId);
			// System.out.println("Wipro in - getUserDetails() after execute
			// query");
			// System.out.println("List Size :" + list.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @param userId
	 * @return
	 */
	public ArrayList getComplainerUserDetails(String userId) {
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil
			        .createPreparedStatement(DepartmentalEnquirySQL.COMPLAINER_REG_USER_DETAILS);
			// System.out.println("Wipro in - getComplainerUserDetails() after
			// create statement");
			list = dbUtil.executeQueryLikeSearch(userId);
			// System.out.println("Wipro in - getComplainerUserDetails() after
			// execute query");
			// System.out.println("List Size :" + list.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getComplainerEmpDetails(String empId) {
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil
			        .createPreparedStatement(DepartmentalEnquirySQL.EMP_DETAILS);
			// System.out
			// .println("Wipro in - getComplainerEmpDetails() after create
			// statement");
			list = dbUtil.executeQueryLikeSearch(empId);
			// System.out
			// .println("Wipro in - getComplainerEmpDetails() after execute
			// query");
			// System.out.println("List Size :" + list.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @param arr
	 * @return
	 */
	public String submitCriminalCase(String[] arr) {

		boolean flag = false;
		boolean flag1 = false;
		String returnVal = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			ArrayList listTxnIds = getMaxCriminalCaseTxnId();
			ArrayList listTxnId = (ArrayList) listTxnIds.get(0);
			String criminalCaseTxnId = (String) listTxnId.get(0);
			arr[11] = criminalCaseTxnId;

			dbUtil.setAutoCommit(false);
			dbUtil
			        .createPreparedStatement(DepartmentalEnquirySQL.SUBMIT_CRIMINAL_CASE);
			// System.out.println("DepartmentalEnquirySQL.SUBMIT_CRIMINAL_CASE");
			flag = dbUtil.executeUpdate(arr);
			String[] arr1 = new String[3];
			arr1[0] = criminalCaseTxnId;
			arr1[1] = arr[5];
			arr1[2] = arr[4];

			dbUtil
			        .createPreparedStatement(DepartmentalEnquirySQL.SUBMIT_CRIMINAL_CASE_COMPLAINT);
			flag1 = dbUtil.executeUpdate(arr1);

			if (flag && flag1) {
				dbUtil.commit();
				returnVal = criminalCaseTxnId;
				// System.out.println("Records Inserted Successfully");
			} else {
				dbUtil.rollback();
				returnVal = "false";
				// System.out.println("Transaction rolledback");
			}

		} catch (Exception ex) {
			try {
				dbUtil.rollback();
				returnVal = "false";
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return returnVal;
	}

	/**
	 * @param complaintId
	 * @return
	 */
	public ArrayList getComplaintList(String complaintId) {
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			// System.out
			// .println("Wipro in IGRSCommon - getComplaintList() before execute
			// query");
			dbUtil
			        .createPreparedStatement(DepartmentalEnquirySQL.COMPLAINT_DETAILS);
			list = dbUtil.executeQueryLikeSearch(complaintId);

			// System.out
			// .println("Wipro in IGRSCommon - getComplaintList() after execute
			// query");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * @return
	 */
	private ArrayList getMaxCriminalCaseTxnId() {
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			// System.out
			// .println("Wipro in IGRSCommon - getComplaintNumber() after create
			// statement");

			//list = dbUtil.executeQuery("SELECT 'IGRSEMPDIST'||LPAD(IGRS_EMP_COM_DTL_SEQ.NEXTVAL,5,0) FROM DUAL");
			list = dbUtil.executeQuery("SELECT 'CCE'||LPAD(IGRS_EMP_COM_DTL_SEQ.NEXTVAL,7,0) FROM DUAL");

			// System.out
			// .println("Wipro in IGRSCommon - getComplaintNumber() after
			// execute query");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * @param complaintId
	 * @return
	 */
	public ArrayList getComplaintDetails(String complaintId) {
		ArrayList list = new ArrayList();
		String[] arr = new String[1];
		arr[0] = complaintId;
		DBUtility dbUtil = null;
		ArrayList complaineelist;
		ArrayList complainerlist;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(DepartmentalEnquirySQL.SELECT_COMPLAINEE_USERTYPE);
			ArrayList emplist = dbUtil.executeQuery(arr);
			if(emplist!=null && emplist.size()>0){
				dbUtil.createPreparedStatement(DepartmentalEnquirySQL.COMPLAINT_COMPLAINEE_EMP_DETAILS);
				complaineelist = dbUtil.executeQuery(arr);
			}else{
				dbUtil.createPreparedStatement(DepartmentalEnquirySQL.COMPLAINT_COMPLAINEE_DETAILS);
				complaineelist = dbUtil.executeQuery(arr);
			}
			dbUtil.createPreparedStatement(DepartmentalEnquirySQL.SELECT_COMPLAINER_USERTYPE);
			ArrayList empcomplainerlist = dbUtil.executeQuery(arr);
			if(empcomplainerlist!=null && empcomplainerlist.size()>0){
			dbUtil
			        .createPreparedStatement(DepartmentalEnquirySQL.COMPLAINT_COMPLAINER_EMP_DETAILS);
			complainerlist = dbUtil.executeQuery(arr);
			}else{
				dbUtil
		        .createPreparedStatement(DepartmentalEnquirySQL.COMPLAINT_COMPLAINER_DETAILS);
				complainerlist = dbUtil.executeQuery(arr);
			}
			
			list.add(complaineelist);
			list.add(complainerlist);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @param arr
	 * @return
	 */
	public boolean submitDeInitiateDetails(String[] arr) {
		boolean flag = false;
		boolean flag1 = false;
		boolean returnFalg = false;

		String[] arr1 = new String[2];
		arr1[0] = arr[2];
		arr1[1] = arr[3];
		// System.out.println("arr1[0] :"+arr1[0]);
		// System.out.println("arr1[1] :"+arr1[1]);

		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil
			        .createPreparedStatement(DepartmentalEnquirySQL.DE_INITIATE_SUBMIT);
			flag = dbUtil.executeUpdate(arr);

			dbUtil
			        .createPreparedStatement(DepartmentalEnquirySQL.DE_INITIATE_UPDATE_COMPLAINT);
			flag1 = dbUtil.executeUpdate(arr1);

			// System.out.println("Flag :"+flag);
			// System.out.println("Flag 1 :"+flag1);

			if (flag && flag1) {
				dbUtil.commit();
				returnFalg = true;
			} else {
				dbUtil.rollback();
				returnFalg = false;
			}
		} catch (Exception ex) {
			returnFalg = false;
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return returnFalg;
	}

	/**
	 * @param arr
	 * @return
	 */
	public ArrayList getPreEnquiryDetails(String[] arr) {
		ArrayList list = new ArrayList();
		DBUtility  dbUtil= null;
		ArrayList complaineelist=null;
		ArrayList complainerlist=null;
		ArrayList deperlist=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(DepartmentalEnquirySQL.SELECT_COMPLAINEE_USERTYPE);
			ArrayList emplist = dbUtil.executeQuery(arr);
			if(emplist!=null && emplist.size()>0){
				dbUtil.createPreparedStatement(DepartmentalEnquirySQL.COMPLAINT_COMPLAINEE_EMP_DETAILS);
				complaineelist = dbUtil.executeQuery(arr);
			}else{
				dbUtil.createPreparedStatement(DepartmentalEnquirySQL.COMPLAINT_COMPLAINEE_DETAILS);
				complaineelist = dbUtil.executeQuery(arr);
			}
			dbUtil.createPreparedStatement(DepartmentalEnquirySQL.SELECT_COMPLAINER_USERTYPE);
			ArrayList empcomplainerlist = dbUtil.executeQuery(arr);
			if(empcomplainerlist!=null && empcomplainerlist.size()>0){
			dbUtil
			        .createPreparedStatement(DepartmentalEnquirySQL.COMPLAINT_COMPLAINER_EMP_DETAILS);
			complainerlist = dbUtil.executeQuery(arr);
			}else{
				dbUtil
		        .createPreparedStatement(DepartmentalEnquirySQL.COMPLAINT_COMPLAINER_DETAILS);
				complainerlist = dbUtil.executeQuery(arr);
			}
			
			dbUtil.createPreparedStatement(DepartmentalEnquirySQL.SELECT_DE_USERTYPE);
			ArrayList delist = dbUtil.executeQuery(arr);
			if(delist!=null && delist.size()>0){
			dbUtil.createPreparedStatement(DepartmentalEnquirySQL.GET_DEPUTEDEMP_DETAILS);
			deperlist = dbUtil.executeQuery(arr);
			}else{
				dbUtil
		        .createPreparedStatement(DepartmentalEnquirySQL.GET_DEPUTE_DETAILS);
				deperlist = dbUtil.executeQuery(arr);
			}
			list.add(complaineelist);
			list.add(complainerlist);
			list.add(deperlist);
			System.out.println("IN DAO complaineelist"+complaineelist.size());
			System.out.println("IN DAO complainerlist" +complainerlist.size());
			System.out.println("IN DAO deperlist"+deperlist.size());
			//dbUtility    .createPreparedStatement(DepartmentalEnquirySQL.GET_PRE_ENQUIRY_DETAILS);
			//list = dbUtility.executeQuery(arr);
			// System.out.println("List Size in DAO :"+list.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		System.out.println("END DAO ");
		return list;
	}

	/**
	 * @param complaintId
	 * @return
	 */
	public ArrayList getPreEnquiryComplaintList(String complaintId) {
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			// System.out
			// .println("Wipro in IGRSCommon - getPreEnquiryComplaintList()
			// before execute query");
			dbUtil
			        .createPreparedStatement(DepartmentalEnquirySQL.PRE_ENQUIRY_COMPLAINT_DETAILS);
			list = dbUtil.executeQueryLikeSearch(complaintId);

			// System.out
			// .println("Wipro in IGRSCommon - getPreEnquiryComplaintList()
			// after execute query");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * @param list
	 * @param filePath
	 * @return
	 */
	public boolean submitEmpAcceptCharges(ArrayList list, String filePath) {
		String[] compDetails = (String[]) list.get(0);
		String[] compAction = (String[]) list.get(1);
		String[] compComment = (String[]) list.get(2);
		String[] compStatus = (String[]) list.get(3);
		String[] compDocMap = (String[]) list.get(4);
		DBUtility dbUtility = null;
		boolean returnFlag = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);

			dbUtility
			        .createPreparedStatement(DepartmentalEnquirySQL.EMP_ACCEPT_UPDATE_DETAILS);
			boolean flagCompDetails = dbUtility.executeUpdate(compDetails);

			dbUtility
			        .createPreparedStatement(DepartmentalEnquirySQL.EMP_ACCEPT_COMPLAINT_ACTION_DETAILS);
			boolean flagCompAction = dbUtility.executeUpdate(compAction);

			dbUtility
			        .createPreparedStatement(DepartmentalEnquirySQL.EMP_ACCEPT_COMPLAINT_COMMENT_DETAILS);
			boolean flagCompComment = dbUtility.executeUpdate(compComment);

			dbUtility
			        .createPreparedStatement(DepartmentalEnquirySQL.EMP_ACCEPT_COMPLAINT_STATUS_UPDATE);
			boolean flagCompStatus = dbUtility.executeUpdate(compStatus);

			// boolean flagCompDocMap = dbUtility.savePreEnquiryDoc(compDocMap,
			// filePath);
			String flagCompDocMap = dbUtility.savePreEnquiryDoc(compDocMap,
			        filePath);
			// System.out.println("flagCompDocMap "+flagCompDocMap);
			if (flagCompDetails && flagCompAction && flagCompComment
			        && flagCompStatus
			        && !flagCompDocMap.equalsIgnoreCase("false")) {
				dbUtility.commit();
				returnFlag = true;
			} else {
				returnFlag = false;
				dbUtility.rollback();
			}
		} catch (Exception ex) {
			returnFlag = false;
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return returnFlag;
	}

	public boolean submitEmpReleaseCharges(ArrayList list, String filePath) {
		String[] compDetails = (String[]) list.get(0);
		String[] compAction = (String[]) list.get(1);
		String[] compComment = (String[]) list.get(2);
		String[] compStatus = (String[]) list.get(3);
		String[] compDocMap = (String[]) list.get(4);
		DBUtility dbUtility = null;
		boolean returnFlag = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);

			dbUtility
			        .createPreparedStatement(DepartmentalEnquirySQL.EMP_ACCEPT_UPDATE_DETAILS);
			boolean flagCompDetails = dbUtility.executeUpdate(compDetails);

			dbUtility
			        .createPreparedStatement(DepartmentalEnquirySQL.EMP_ACCEPT_COMPLAINT_ACTION_DETAILS);
			boolean flagCompAction = dbUtility.executeUpdate(compAction);

			dbUtility
			        .createPreparedStatement(DepartmentalEnquirySQL.EMP_ACCEPT_COMPLAINT_COMMENT_DETAILS);
			boolean flagCompComment = dbUtility.executeUpdate(compComment);

			dbUtility
			        .createPreparedStatement(DepartmentalEnquirySQL.EMP_ACCEPT_COMPLAINT_STATUS_UPDATE);
			boolean flagCompStatus = dbUtility.executeUpdate(compStatus);

			// boolean flagCompDocMap = dbUtility.savePreEnquiryDoc(compDocMap,
			// filePath);
			String flagCompDocMap = dbUtility.savePreEnquiryDoc(compDocMap,
			        filePath);

			if (flagCompDetails && flagCompAction && flagCompComment
			        && flagCompStatus
			        && !flagCompDocMap.equalsIgnoreCase("false")) {
				dbUtility.commit();
				returnFlag = true;
			} else {
				returnFlag = false;
				dbUtility.rollback();
			}
		} catch (Exception ex) {
			returnFlag = false;
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return returnFlag;
	}

	// SAI
	public ArrayList getDeputedPEDetails(String[] arr) {
		ArrayList list = new ArrayList();
		DBUtility dbUtil= null;
		ArrayList complaineelist,complainerlist,duputedlist;
		try {
			dbUtil= new DBUtility();
			dbUtil.createPreparedStatement(DepartmentalEnquirySQL.SELECT_COMPLAINEE_USERTYPE);
			ArrayList emplist = dbUtil.executeQuery(arr);
			if(emplist!=null && emplist.size()>0){
				dbUtil.createPreparedStatement(DepartmentalEnquirySQL.COMPLAINT_COMPLAINEE_EMP_DETAILS);
				complaineelist = dbUtil.executeQuery(arr);
			}else{
				dbUtil.createPreparedStatement(DepartmentalEnquirySQL.COMPLAINT_COMPLAINEE_DETAILS);
				complaineelist = dbUtil.executeQuery(arr);
			}
			dbUtil.createPreparedStatement(DepartmentalEnquirySQL.SELECT_COMPLAINER_USERTYPE);
			ArrayList empcomplainerlist = dbUtil.executeQuery(arr);
			if(empcomplainerlist!=null && empcomplainerlist.size()>0){
			dbUtil
			        .createPreparedStatement(DepartmentalEnquirySQL.COMPLAINT_COMPLAINER_EMP_DETAILS);
			complainerlist = dbUtil.executeQuery(arr);
			}else{
				dbUtil
		        .createPreparedStatement(DepartmentalEnquirySQL.COMPLAINT_COMPLAINER_DETAILS);
				complainerlist = dbUtil.executeQuery(arr);
			}
			dbUtil.createPreparedStatement(DepartmentalEnquirySQL.SELECT_DEPUTED_USERTYPE);
			ArrayList deputedperson = dbUtil.executeQuery(arr);
			if(deputedperson!=null && deputedperson.size()>0){
			dbUtil.createPreparedStatement(DepartmentalEnquirySQL.GET_DEPUTEDEMP_DETAILS);
				duputedlist = dbUtil.executeQuery(arr);
			}else{
				dbUtil
		        .createPreparedStatement(DepartmentalEnquirySQL.GET_DEPUTE_DETAILS);
				duputedlist = dbUtil.executeQuery(arr);
			}
			list.add(complaineelist);
			list.add(complainerlist);
			list.add(duputedlist);
			 System.out.println("List Size in DAO :" + list.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList getSuspenionPEDetails(String complaintNo) {
		String arr[] = new String[1];
		arr[0] = complaintNo;
		ArrayList list = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility
			        .createPreparedStatement(DepartmentalEnquirySQL.GET_SUSP_PRE_ENQ_DETAILS);
			list = dbUtility.executeQuery(arr);
			// System.out.println("List Size in DAO :" + list.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList getSuspenionAcceptedDetails(String complaintNo) {
		String arr[] = new String[1];
		arr[0] = complaintNo;
		ArrayList list = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility
			        .createPreparedStatement(DepartmentalEnquirySQL.GET_SUSP_ACCEPTED_DETAILS);
			list = dbUtility.executeQuery(arr);
			// System.out.println("List Size in DAO :" + list.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList getComplaintListForSuspensionOrder(String complaintId) {
		String arr[] = { complaintId + "%" };
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			dbUtil
			        .createPreparedStatement(DepartmentalEnquirySQL.GET_INITIAITED_ACCEPTED_COMPLAINT_DETAILS);
			list = dbUtil.executeQuery(arr);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}

	public boolean submitPreSuspension(ArrayList list, String filePath) {
		String[] compDetails = (String[]) list.get(0);
		String[] compAction = (String[]) list.get(1);
		String[] compComment = (String[]) list.get(2);
		String[] compStatus = (String[]) list.get(3);
		String[] compDocMap = (String[]) list.get(4);
		String[] compSigDocMap = (String[]) list.get(5);
		DBUtility dbUtility = null;
		boolean returnFlag = false;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);

			dbUtility
			        .createPreparedStatement(DepartmentalEnquirySQL.EMP_ACCEPT_UPDATE_DETAILS);
			boolean flagCompDetails = dbUtility.executeUpdate(compDetails);

			dbUtility
			        .createPreparedStatement(DepartmentalEnquirySQL.PRE_SUSPENSION_COMPLAINT_ACTION_DETAILS);
			boolean flagCompAction = dbUtility.executeUpdate(compAction);

			dbUtility
			        .createPreparedStatement(DepartmentalEnquirySQL.PRE_SUSPENSION_COMPLAINT_COMMENT_DETAILS);
			boolean flagCompComment = dbUtility.executeUpdate(compComment);

			dbUtility
			        .createPreparedStatement(DepartmentalEnquirySQL.EMP_ACCEPT_COMPLAINT_STATUS_UPDATE);
			boolean flagCompStatus = dbUtility.executeUpdate(compStatus);

			// boolean flagCompDocMap = dbUtility.savePreEnquiryDoc(compDocMap,
			// filePath);
			String flagCompDocMap = dbUtility.savePreEnquiryDoc(compDocMap,
			        filePath);

			// boolean flagSigDocMap =
			// dbUtility.savePreEnquiryDoc(compSigDocMap, filePath);
			String flagSigDocMap = dbUtility.savePreEnquiryDoc(compSigDocMap,
			        filePath);

			/*
			 * System.out.println("flagCompDetails :"+flagCompDetails);
			 * System.out.println("flagCompAction :"+flagCompAction);
			 * System.out.println("flagCompComment :"+flagCompComment);
			 * System.out.println("flagCompStatus :"+flagCompStatus);
			 * System.out.println("flagCompDocMap :"+flagCompDocMap);
			 * System.out.println("flagSigDocMap :"+flagSigDocMap);
			 */

			if (flagCompDetails && flagCompAction && flagCompComment
			        && flagCompStatus
			        && !flagCompDocMap.equalsIgnoreCase("false")
			        && !flagSigDocMap.equalsIgnoreCase("false")) {
				dbUtility.commit();
				returnFlag = true;
			} else {
				returnFlag = false;
				dbUtility.rollback();
			}
		} catch (Exception ex) {
			returnFlag = false;
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return returnFlag;
	}

	// SAI
	public String checkComplaintStatus(String complaintNo) {
		String status = null;
		DBUtility dbUtility = null;
		try {
			String sqlQuery = DepartmentalEnquirySQL.GET_COMPLAINT_STATUS;
			String sqlValues[] = new String[1];
			sqlValues[0] = complaintNo;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sqlQuery);
			status = dbUtility.executeQry(sqlValues);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtility != null)
					dbUtility.closeConnection();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return status;
	}

	public String displayObjectDetails(HttpServletResponse res, String contId,String strFunctionName) {

		String status = null;
		DBUtility dbUtility = null;

		try {
			dbUtility = new DBUtility();
			dbUtility.readBLOBToFileGet(res, contId,strFunctionName);

		} catch (Exception e) {

			// System.out.println("Exception "+e);
		} finally {
			try {
				if (dbUtility != null)
					dbUtility.closeConnection();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return status;
	}

	public boolean submitSuspenseOrderInitiated(ArrayList list, String filePath) {
		// System.out.println("in DAO - submitSuspenseOrderInitiated");

		String comComments[] = (String[]) list.get(0); // IGRS_EMP_COMPLAINT_COMMENTS
		String comDetails[] = (String[]) list.get(1); // IGRS_EMP_COMPLAINT_DETAILS
		String comActionDetails[] = (String[]) list.get(2); // IGRS_EMP_COMPLAINT_ACTION_DTLS
		String comStatusDetails[] = (String[]) list.get(3); // IGRS_EMP_COMPLAINT_STATUS_DTLS
															// <--->
		String docDetails[] = (String[]) list.get(4); // IGRS_EMP_COMPLAINT_DOC_MAP
														// - Pre Enquiry
														// Document
		String signDetails[] = (String[]) list.get(5); // IGRS_EMP_COMPLAINT_DOC_MAP
														// - Suspension
														// Signature
		boolean resultFlag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			String query1 = DepartmentalEnquirySQL.INSERT_PE_COMMENTS;
			dbUtility.createPreparedStatement(query1);
			boolean flag1 = dbUtility.executeUpdate(comComments);

			String query2 = DepartmentalEnquirySQL.UPDATE_COMPLAINT_DETAILS;
			dbUtility.createPreparedStatement(query2);
			boolean flag2 = dbUtility.executeUpdate(comDetails);

			String query3 = DepartmentalEnquirySQL.INSERT_COMPLAINT_ACTION_DETAILS;
			dbUtility.createPreparedStatement(query3);
			boolean flag3 = dbUtility.executeUpdate(comActionDetails);

			String query4 = DepartmentalEnquirySQL.UPDATE_COMPLAINT_STATUS;
			dbUtility.createPreparedStatement(query4);
			boolean flag4 = dbUtility.executeUpdate(comStatusDetails);

			// boolean flag5 = dbUtility.savePreEnquiryDoc(docDetails,
			// filePath);
			String flag5 = dbUtility.savePreEnquiryDoc(docDetails, filePath);

			// boolean flag6 = dbUtility.savePreEnquiryDoc(signDetails,
			// filePath);
			String flag6 = dbUtility.savePreEnquiryDoc(signDetails, filePath);

			/*
			 * System.out.println("UPDATE_PE_COMMENTS flag1 : "+flag1);
			 * System.out.println("UPDATE_COMPLAINT_DETAILS flag2 : "+flag2);
			 * System.out.println("INSERT_COMPLAINT_ACTION_DETAILS flag3 :
			 * "+flag3); System.out.println("UPDATE_COMPLAINT_STATUS flag4 :
			 * "+flag4); System.out.println("DOC ->flag5 : "+flag5);
			 * System.out.println("IMAGE ->flag6 : "+flag6);
			 */

			if (flag1 && flag2 && flag3 && flag4
			        && !flag5.equalsIgnoreCase("false")
			        && !flag6.equalsIgnoreCase("false")) {
				dbUtility.commit();
				// System.out.println("Records Inserted Succesfully");
				resultFlag = true;
			} else {
				dbUtility.rollback();
				resultFlag = false;
				// System.out.println("Sorry! Records Not Inserted");
			}
		} catch (Exception ex) {
			try {
				dbUtility.rollback();
				resultFlag = false;
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtility != null)
					dbUtility.closeConnection();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		// System.out.println("Out ot DAO now");
		return resultFlag;
	}//

	public ArrayList getChargesheetComplaintList(String complaintId) {
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			// System.out
			// .println("Wipro in IGRSCommon - getChargesheetComplaintList()
			// before execute query");
			dbUtil
			        .createPreparedStatement(DepartmentalEnquirySQL.CHARGESHEET_COMPLAINT_DETAILS);
			list = dbUtil.executeQueryLikeSearch(complaintId);

			// System.out
			// .println("Wipro in IGRSCommon - getChargesheetComplaintList()
			// after execute query");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList getChargesheetDetails(String[] arr) {
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(DepartmentalEnquirySQL.SELECT_COMPLAINEE_USERTYPE);
			ArrayList emplist = dbUtil.executeQuery(arr);
			if(emplist!=null && emplist.size()>0){
				dbUtil.createPreparedStatement(DepartmentalEnquirySQL.COMPLAINT_COMPLAINEE_EMP_DETAILS);
				list = dbUtil.executeQuery(arr);
			}else{
				dbUtil.createPreparedStatement(DepartmentalEnquirySQL.COMPLAINT_COMPLAINEE_DETAILS);
				list = dbUtil.executeQuery(arr);
			}
			
			// System.out.println("List Size in DAO :"+list.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil!= null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}

	public boolean submitSuspenseOrderAccepted(ArrayList list, String filePath) {
		// System.out.println("in DAO - submitSuspenseOrderInitiated");

		String comComments[] = (String[]) list.get(0); // IGRS_EMP_COMPLAINT_COMMENTS
		String comDetails[] = (String[]) list.get(1); // IGRS_EMP_COMPLAINT_DETAILS
		String comActionDetails[] = (String[]) list.get(2); // IGRS_EMP_COMPLAINT_ACTION_DTLS
		String comStatusDetails[] = (String[]) list.get(3); // IGRS_EMP_COMPLAINT_STATUS_DTLS
															// <--->
		String signDetails[] = (String[]) list.get(4); // IGRS_EMP_COMPLAINT_DOC_MAP
														// - Suspension
														// Signature
		boolean resultFlag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			String query1 = DepartmentalEnquirySQL.UPDATE_PE_COMMENTS;
			dbUtility.createPreparedStatement(query1);
			boolean flag1 = dbUtility.executeUpdate(comComments);

			String query2 = DepartmentalEnquirySQL.UPDATE_COMPLAINT_DETAILS;
			dbUtility.createPreparedStatement(query2);
			boolean flag2 = dbUtility.executeUpdate(comDetails);

			String query3 = DepartmentalEnquirySQL.UPDATE_COMPLAINT_ACTION_DETAILS;
			dbUtility.createPreparedStatement(query3);
			boolean flag3 = dbUtility.executeUpdate(comActionDetails);

			String query4 = DepartmentalEnquirySQL.UPDATE_COMPLAINT_STATUS;
			dbUtility.createPreparedStatement(query4);
			boolean flag4 = dbUtility.executeUpdate(comStatusDetails);

			// boolean flag5 = dbUtility.savePreEnquiryDoc(signDetails,
			// filePath);
			String flag5 = dbUtility.savePreEnquiryDoc(signDetails, filePath);

			/*
			 * System.out.println("UPDATE_PE_COMMENTS flag1 : "+flag1);
			 * System.out.println("UPDATE_COMPLAINT_DETAILS flag2 : "+flag2);
			 * System.out.println("INSERT_COMPLAINT_ACTION_DETAILS flag3 :
			 * "+flag3); System.out.println("UPDATE_COMPLAINT_STATUS flag4 :
			 * "+flag4); System.out.println("IMAGE ->flag5 : "+flag5);
			 */

			if (flag1 && flag2 && flag3 && flag4
			        && !flag5.equalsIgnoreCase("false")) {
				dbUtility.commit();
				// System.out.println("Records Inserted Succesfully");
				resultFlag = true;
			} else {
				dbUtility.rollback();
				resultFlag = false;
				// System.out.println("Sorry! Records Not Inserted");
			}
		} catch (Exception ex) {
			try {
				dbUtility.rollback();
				resultFlag = false;
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtility != null)
					dbUtility.closeConnection();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		// System.out.println("Out ot DAO now");
		return resultFlag;
	}//

	/* Fetches all complaintIds whose status is 'Suspension' */
	public ArrayList getSuspensionComplaintsList(String complaintId) {
		String arr[] = { complaintId + "%" };
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			dbUtil
			        .createPreparedStatement(DepartmentalEnquirySQL.GET_SUSPENDED_COMPLAINT_DETAILS);
			list = dbUtil.executeQuery(arr);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}

	/*
	 * Fetches complainee, complainer, case details, deputed pe, pe details,
	 * suspension details fro a complaintId for Suspension - Revoke operation
	 */
	public ArrayList getSuspensionOrderRecord(String complaintId) {
		// System.out.println("in DAO -> getSuspensionOrderRecord");
		ArrayList list = new ArrayList();
		ArrayList complaineelist,complainerlist,duputedlist,suspensiondetailslist,suspensiondocumentlist;
		DBUtility dbUtil = null;
		try {
			String arr[] = new String[1];
			arr[0] = complaintId;
			dbUtil = new DBUtility();			
			dbUtil.createPreparedStatement(DepartmentalEnquirySQL.SELECT_COMPLAINEE_USERTYPE);
			ArrayList emplist = dbUtil.executeQuery(arr);
			if(emplist!=null && emplist.size()>0){
				dbUtil.createPreparedStatement(DepartmentalEnquirySQL.COMPLAINT_COMPLAINEE_EMP_DETAILS);
				complaineelist = dbUtil.executeQuery(arr);
			}else{
				dbUtil.createPreparedStatement(DepartmentalEnquirySQL.COMPLAINT_COMPLAINEE_DETAILS);
				complaineelist = dbUtil.executeQuery(arr);
			}
			dbUtil.createPreparedStatement(DepartmentalEnquirySQL.SELECT_COMPLAINER_USERTYPE);
			ArrayList empcomplainerlist = dbUtil.executeQuery(arr);
			if(empcomplainerlist!=null && empcomplainerlist.size()>0){
			dbUtil
			        .createPreparedStatement(DepartmentalEnquirySQL.COMPLAINT_COMPLAINER_EMP_DETAILS);
			complainerlist = dbUtil.executeQuery(arr);
			}else{
				dbUtil
		        .createPreparedStatement(DepartmentalEnquirySQL.COMPLAINT_COMPLAINER_DETAILS);
				complainerlist = dbUtil.executeQuery(arr);
			}
			dbUtil.createPreparedStatement(DepartmentalEnquirySQL.SELECT_DEPUTED_USERTYPE);
			ArrayList deputedperson = dbUtil.executeQuery(arr);
			if(deputedperson!=null && deputedperson.size()>0){
			dbUtil.createPreparedStatement(DepartmentalEnquirySQL.GET_DEPUTEDEMP_DETAILS);
				duputedlist = dbUtil.executeQuery(arr);
			}else{
				dbUtil
		        .createPreparedStatement(DepartmentalEnquirySQL.GET_DEPUTE_DETAILS);
				duputedlist = dbUtil.executeQuery(arr);
			}
			
			
			dbUtil.createStatement();
			dbUtil.createPreparedStatement(DepartmentalEnquirySQL.GET_SUSPENSDED_RECORD_FOR_REVOKE);
			suspensiondetailslist = dbUtil.executeQuery(arr);
			dbUtil.createPreparedStatement(DepartmentalEnquirySQL.GET_SUSPENSDED_DOCUMNETS);
			suspensiondocumentlist = dbUtil.executeQuery(arr);
			
			
			list.add(complaineelist);
			list.add(complainerlist);
			list.add(duputedlist);
			list.add(suspensiondetailslist);
			list.add(suspensiondocumentlist);
			
			// System.out.println("List Size :"+list.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		// System.out.println("oyut od DAO -> getSuspensionOrderRecord");
		return list;
	}

	public boolean submitChargesheet(ArrayList list, String filePath) {
		// System.out.println("in DAO - submitSuspenseOrderInitiated");

		String comDetails[] = (String[]) list.get(0); // IGRS_EMP_COMPLAINT_COMMENTS
		String comActionDetails[] = (String[]) list.get(1); // IGRS_EMP_COMPLAINT_DETAILS
		String comComments[] = (String[]) list.get(2); // IGRS_EMP_COMPLAINT_ACTION_DTLS
		String compStatus[] = (String[]) list.get(3);
		String basisOfProof[] = (String[]) list.get(4);
		ArrayList witnessDoc = (ArrayList) list.get(5); // IGRS_EMP_COMPLAINT_STATUS_DTLS
														// <--->
		ArrayList WitnessDetails = (ArrayList) list.get(6); // IGRS_EMP_COMPLAINT_DOC_MAP
															// - Suspension
															// Signature
		boolean resultFlag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			String query1 = DepartmentalEnquirySQL.UPDATE_CHARGESHEET_COMP_DETAILS;
			dbUtility.createPreparedStatement(query1);
			boolean flag1 = dbUtility.executeUpdate(comDetails);

			String query2 = DepartmentalEnquirySQL.UPDATE_CHARGESHEET_COMMENT_DETAILS;
			dbUtility.createPreparedStatement(query2);
			boolean flag2 = dbUtility.executeUpdate(comComments);

			String query3 = DepartmentalEnquirySQL.UPDATE_CHARGESHEET_ACTION_DETAILS;
			dbUtility.createPreparedStatement(query3);
			boolean flag3 = dbUtility.executeUpdate(comActionDetails);

			String query4 = DepartmentalEnquirySQL.UPDATE_CHARGESHEET_STATUS;
			dbUtility.createPreparedStatement(query4);
			boolean flag4 = dbUtility.executeUpdate(compStatus);

			// boolean flag5 = dbUtility.savePreEnquiryDoc(basisOfProof,
			// filePath);
			String flag5 = dbUtility.savePreEnquiryDoc(basisOfProof, filePath);

			// boolean flag6 = false;
			ArrayList listDocId = new ArrayList();
			String flag6 = null;
			for (int i = 0; i < witnessDoc.size(); i++) {

				String[] strWitness = (String[]) witnessDoc.get(i);
				flag6 = dbUtility.savePreEnquiryDoc(strWitness, filePath);
				listDocId.add(flag6);
			}

			boolean flag7 = false;
			for (int i = 0; i < WitnessDetails.size(); i++) {

				String[] strWitness = (String[]) WitnessDetails.get(i);
				if (listDocId.size() == 1) {
					strWitness[3] = (String) listDocId.get(0);
				} else {
					strWitness[3] = (String) listDocId.get(i);
				}
				String query7 = DepartmentalEnquirySQL.INSERT_WITNESS_DETAILS;
				dbUtility.createPreparedStatement(query7);
				flag7 = dbUtility.executeUpdate(strWitness);
			}

			/*
			 * System.out.println("flag1 : "+flag1); System.out.println("flag2 :
			 * "+flag2); System.out.println("flag3 : "+flag3);
			 * System.out.println("flag4 : "+flag4); System.out.println("flag5 :
			 * "+flag5); System.out.println("flag6 : "+flag6);
			 * System.out.println("flag7 : "+flag7);
			 */

			if (flag1 && flag2 && flag3 && flag4
			        && !flag5.equalsIgnoreCase("false")
			        && !flag6.equalsIgnoreCase("false") && flag7) {
				dbUtility.commit();
				// System.out.println("Records Inserted Succesfully");
				resultFlag = true;
			} else {
				dbUtility.rollback();
				resultFlag = false;
				// System.out.println("Sorry! Records Not Inserted");
			}
		} catch (Exception ex) {
			try {
				dbUtility.rollback();
				resultFlag = false;
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtility != null)
					dbUtility.closeConnection();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		// System.out.println("Out ot DAO now");
		return resultFlag;
	}

	public boolean updateSuspensionToRevoke(ArrayList list) {
		boolean resultFlag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);

			String actionDtls[] = (String[]) list.get(0); // IGRS_EMP_COMPLAINT_ACTION_DTLS
			String actionDtlsUpdateQuery = DepartmentalEnquirySQL.UPDATE_ACTION_DTLS_REVOKE;
			dbUtility.createPreparedStatement(actionDtlsUpdateQuery);
			boolean flag1 = dbUtility.executeUpdate(actionDtls);

			String compCom[] = (String[]) list.get(1); // IGRS_EMP_COMPLAINT_COMMENTS
			String compComUpdateQuery = DepartmentalEnquirySQL.UPDATE_COMPLAINT_COMMENTS_REVOKE;
			dbUtility.createPreparedStatement(compComUpdateQuery);
			boolean flag2 = dbUtility.executeUpdate(compCom);

			String compDtls[] = (String[]) list.get(2); // IGRS_EMP_COMPLAINT_DETAILS
			String compDtlsUpdateQuery = DepartmentalEnquirySQL.UPDATE_COMPLAINT_DTLS_REVOKE;
			dbUtility.createPreparedStatement(compDtlsUpdateQuery);
			boolean flag3 = dbUtility.executeUpdate(compDtls);

			String statusDtls[] = (String[]) list.get(3); // IGRS_EMP_COMPLAINT_STATUS_DTLS
			String statusDtlsUpdateQuery = DepartmentalEnquirySQL.UPDATE_ACTION_STATUS_REVOKE;
			dbUtility.createPreparedStatement(statusDtlsUpdateQuery);
			boolean flag4 = dbUtility.executeUpdate(statusDtls);

			if (flag1 && flag2 && flag3 && flag4) {
				dbUtility.commit();
				// System.out.println("Records Inserted Succesfully");
				resultFlag = true;
			} else {
				dbUtility.rollback();
				resultFlag = false;
				// System.out.println("Sorry! Records Not Inserted");
			}
		} catch (Exception ex) {
			try {
				dbUtility.rollback();
				resultFlag = false;
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtility != null)
					dbUtility.closeConnection();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		// System.out.println("Out ot DAO - updateSuspensionToRevoke");
		return resultFlag;
	}

	public ArrayList getChargesheetCloseComplaintList(String complaintId) {
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			// System.out
			// .println("Wipro in IGRSCommon - getChargesheetComplaintList()
			// before execute query");
			dbUtil
			        .createPreparedStatement(DepartmentalEnquirySQL.CHARGESHEET_CLOSE_COMPLAINT_DETAILS);
			list = dbUtil.executeQueryLikeSearch(complaintId);

			// System.out
			// .println("Wipro in IGRSCommon - getChargesheetComplaintList()
			// after execute query");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList getChargesheetCloseDetails(String[] arr) {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility
			        .createPreparedStatement(DepartmentalEnquirySQL.CHARGESHEET_CLOSE_DETAILS);
			ArrayList list1 = dbUtility.executeQuery(arr);

			dbUtility
			        .createPreparedStatement(DepartmentalEnquirySQL.CHARGESHEET_CLOSE_WITNESS_DETAILS);
			ArrayList list2 = dbUtility.executeQuery(arr);

			list.add(list1);
			list.add(list2);
			// System.out.println("List Size in DAO :"+list.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}

	public boolean submitChargesheetClose(ArrayList list) {
		// System.out.println("in DAO - submitChargesheetClose");

		String compDetails[] = (String[]) list.get(0); // Details Of
														// IGRS_EMP_COMPLAINT_DETAILS
		String compAction[] = (String[]) list.get(1); // Details of
														// IGRS_EMP_COMPLAINT_ACTION_DTLS
		String compComment[] = (String[]) list.get(2); // Details of
														// IGRS_EMP_COMPLAINT_COMMENTS
		String compStatus[] = (String[]) list.get(3); // Details of
														// IGRS_EMP_COMPLAINT_STATUS_DTLS
		String compWitness[] = (String[]) list.get(4); // Details of
														// IGRS_EMP_COMPLAINT_WITNES_DTLS

		boolean resultFlag = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			String query1 = DepartmentalEnquirySQL.UPDATE_CHARGESHEET_COMP_DETAILS;
			dbUtility.createPreparedStatement(query1);
			boolean flag1 = dbUtility.executeUpdate(compDetails);

			String query2 = DepartmentalEnquirySQL.UPDATE_CHARGESHEET_CLOSE_ACTION_DETAILS;
			dbUtility.createPreparedStatement(query2);
			boolean flag2 = dbUtility.executeUpdate(compAction);

			String query3 = DepartmentalEnquirySQL.UPDATE_CHARGESHEET_CLOSE_COMMENT_DETAILS;
			dbUtility.createPreparedStatement(query3);
			boolean flag3 = dbUtility.executeUpdate(compComment);

			String query4 = DepartmentalEnquirySQL.UPDATE_CHARGESHEET_STATUS;
			dbUtility.createPreparedStatement(query4);
			boolean flag4 = dbUtility.executeUpdate(compStatus);

			String query5 = DepartmentalEnquirySQL.UPDATE_WITNESS_DETAILS;
			dbUtility.createPreparedStatement(query5);
			boolean flag5 = dbUtility.executeUpdate(compWitness);

			/*
			 * System.out.println("flag1 : "+flag1); System.out.println("flag2 :
			 * "+flag2); System.out.println("flag3 : "+flag3);
			 * System.out.println("flag4 : "+flag4); System.out.println("flag5 :
			 * "+flag5);
			 */

			if (flag1 && flag2 && flag3 && flag4 && flag5) {
				dbUtility.commit();
				// System.out.println("Records Updated Succesfully");
				resultFlag = true;
			} else {
				dbUtility.rollback();
				resultFlag = false;
				//System.out.println("Sorry! Records Not Updated");
			}
		} catch (Exception ex) {
			try {
				dbUtility.rollback();
				resultFlag = false;
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtility != null)
					dbUtility.closeConnection();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		//System.out.println("Out ot DAO now");
		return resultFlag;
	}

}
